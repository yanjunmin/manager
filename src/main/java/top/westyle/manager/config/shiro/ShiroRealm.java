package top.westyle.manager.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import top.westyle.manager.entity.common.User;
import top.westyle.manager.service.UserService;

import java.util.Collection;

/**
 * @description 自定义shiro的realm实现
 * @author yjm
 * @date 2019-8-4 17:31:56
 */
public class ShiroRealm extends AuthorizingRealm {
    private static Logger log = LoggerFactory.getLogger(ShiroRealm.class);
    /**
     * 登录验证需要操作redis
     */
    /*@Autowired
    private RedisTemplate<Object, Object> redisTemplate;*/
    /**
     * 用户登录信息操作
     */
    @Autowired
    private UserService userService;
    /**
     * 鉴权信息验证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登录验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug("登录验证");
        UsernamePasswordToken upToken = (UsernamePasswordToken)authenticationToken;
        String loginName = upToken.getUsername();
        DefaultWebSecurityManager defaultWebSecurityManager = (DefaultWebSecurityManager)SecurityUtils.getSecurityManager();
        ShiroRedisSessionManager shiroRedisSessionManager = (ShiroRedisSessionManager)defaultWebSecurityManager.getSessionManager();
        Collection<Session> sessions = shiroRedisSessionManager.getSessionDAO().getActiveSessions();//获取当前已登录用户的sesion列表
        for (Session session : sessions) {
            log.info("session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)){}", session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
            if(loginName.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
                //删除用户之前登录的session
                shiroRedisSessionManager.getSessionDAO().delete(session);
            }
        }
        //进行用户名信息查询
        User user  = userService.findByUserName(loginName);
        if(user == null) {
            return null;
        }
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getPasswordSalt()),getName());
        return authenticationInfo;
    }
}
