package top.westyle.manager.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.westyle.manager.entity.common.User;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义密码匹配器
 */
public class MyCredentialsMatcher extends HashedCredentialsMatcher {
    private static final Logger log = LoggerFactory.getLogger(MyCredentialsMatcher.class);

    /**
     * 限制登录次数 默认5次
     */
    private int maxRetryNum = 5;
    private RedisCacheManager redisCacheManager;

    public void setMaxRetryNum(int maxRetryNum) {
        this.maxRetryNum = maxRetryNum;
    }

    public MyCredentialsMatcher() {
        super();
    }
    public MyCredentialsMatcher (String hashAlgorithmName, RedisCacheManager redisCacheManager){
        super(hashAlgorithmName);
        this.redisCacheManager = redisCacheManager;
    }

    public MyCredentialsMatcher(RedisCacheManager redisCacheManager) {
        this.redisCacheManager = redisCacheManager;
    }
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        Cache<String, AtomicInteger> passwordRetryCache = redisCacheManager.getCache("passwordRetryCache");

        //retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if(Integer.valueOf(retryCount.toString()) <= 5) {
            retryCount.incrementAndGet();
            passwordRetryCache.put(username, retryCount);
        }
        if (Integer.valueOf(retryCount.toString()) > maxRetryNum) {
            log.warn("用户[{}]进行登录验证..失败验证超过{}次", username, maxRetryNum);
            throw new ExcessiveAttemptsException("username: " + username + " tried to login more than 5 times in period");
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            passwordRetryCache.remove(username);// 清除用户登录失败的次数

            //登录成功进行用户名登录记录，便于同一个用户连续登录踢出先前登录的
            //存入当前登录成功的用户
            Subject subject = SecurityUtils.getSubject();
            Cache<String, Serializable> kickOutUser = redisCacheManager.getCache("kickOutUser");
            Serializable oldSessionId = kickOutUser.get(username);
            if (null == oldSessionId) {
                kickOutUser.put(username, subject.getSession().getId());//没有说明是第一次登录
                log.info("用户名： {} 第一次登录成功", username);
            }else {//否则进行踢出前面登入的同一个用户
               DefaultWebSecurityManager defaultWebSecurityManager = (DefaultWebSecurityManager)SecurityUtils.getSecurityManager();
                ShiroRedisSessionManager shiroRedisSessionManager = (ShiroRedisSessionManager)defaultWebSecurityManager.getSessionManager();
                SessionKey key = new SessionKey() {
                    @Override
                    public Serializable getSessionId() {
                        return oldSessionId;
                    }
                };
                Session session = SecurityUtils.getSecurityManager().getSession(key);
                new ShiroSessionListener().onStop(session);//删掉之前旧的session,显示调用，主要为了触发统计在线人数
                shiroRedisSessionManager.getSessionDAO().delete(session);
                kickOutUser.put(username, subject.getSession().getId());
                log.info("用户名： {} 非第一次登录成功并且没有上一次登录后没有退出，还在有效状态", username);
            }
        }
        return matches;
    }
}
