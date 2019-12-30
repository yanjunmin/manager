package top.westyle.manager.config.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.crazycake.shiro.RedisCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        String username = (String) token.getPrincipal();
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
            passwordRetryCache.remove(username);
        }
        return matches;
    }
}
