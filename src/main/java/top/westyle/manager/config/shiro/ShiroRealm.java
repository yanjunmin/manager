package top.westyle.manager.config.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import top.westyle.manager.entity.common.User;
import top.westyle.manager.service.UserService;

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
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
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
        //进行用户名信息查询(数据库用户名与密码都加密了)
        User user  = userService.findByUserName(loginName);
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(loginName, user.getPassword(), ByteSource.Util.bytes(user.getPasswordSalt()),getName());
        return authenticationInfo;
    }
}
