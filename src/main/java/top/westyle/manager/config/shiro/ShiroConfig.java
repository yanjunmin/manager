package top.westyle.manager.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @description shiro配置类
 * @author yjm
 * @date 2019-8-5 17:08:18
 */
@Configuration
public class ShiroConfig {
    /**
     * redis hsot
     */
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring..redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    /**
     * 这是我自己的realm 我自定义了一个密码解析器，这个比较简单，稍微跟一下源码就知道这玩意
     * @param matcher
     * @return
     */
    @Bean("shiroRealm")
    @DependsOn({"hashedCredentialsMatcher"})
    public ShiroRealm shiroRealm(HashedCredentialsMatcher matcher) {
        // 配置 Realm，需自己实现
        ShiroRealm realm = new ShiroRealm();
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    /**
     * 安全管理配置各种manager,跟xml的配置很像，但是，这里有一个细节，就是各个set的次序不能乱
     * @author Super小靖
     * @date 2018/8/29
     * @param realm
     * @return
     **/
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm(createMatcher()));
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(cacheManager());

//        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
//        authorizer.setRealms(securityManager.getRealms());
//        authorizer.setPermissionResolver(new EmployeePermissionResolver());
//        authorizer.setRolePermissionResolver(rolePermissionResolver());

        // securityManager.setAuthorizer(authorizer);

        // securityManager.setRememberMeManager(rememberMeManager());

        return securityManager;
    }

    /**
     * shiro的拦截器，在spring mvc中也有相同的配置，这里不再多说
     * @author yjm
     * @date 2019-8-6 14:40:53
     * @param securityManager
     * @return
     **/
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置 securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 登录的 url
        //shiroFilterFactoryBean.setLoginUrl(shiroProperties.getLoginUrl());
        // 登录成功后跳转的 url
        //shiroFilterFactoryBean.setSuccessUrl(shiroProperties.getSuccessUrl());
        // 未授权 url
        //shiroFilterFactoryBean.setUnauthorizedUrl(shiroProperties.getUnauthorizedUrl());
        // 这里配置授权链，跟mvc的xml配置一样
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 设置免认证 url
        /*String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(shiroProperties.getAnonUrl(), ",");
        for (String url : anonUrls) {
            filterChainDefinitionMap.put(url, "anon");
        }*/

        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/add", "anon");
        filterChainDefinitionMap.put("/druid/*", "anon");
        filterChainDefinitionMap.put("/user/logout", "anon");
        // 配置退出过滤器，其中具体的退出代码 Shiro已经替我们实现了
        // filterChainDefinitionMap.put(shiroProperties.getLogoutUrl(), "logout");
        // 除上以外所有 url都必须认证通过才可以访问，未通过认证自动访问 LoginUrl
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", new ShiroAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filters);
        return shiroFilterFactoryBean;
    }
    /**
     * 生成一个RedisCacheManager 这没啥好说的
     * @author yjm
     * @date 2019-8-6 12:37:05
     * @return
     **/
    private RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //redisCacheManager.setPrincipalIdFieldName("id");
        return  redisCacheManager;
    }

    private RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setTimeout(timeout);
        return redisManager;
    }

    /**
     * session 管理对象
     *
     * @return DefaultWebSessionManager
     */
    private DefaultWebSessionManager sessionManager() {
        ShiroRedisSessionManager sessionManager = new ShiroRedisSessionManager();
        // 设置session超时时间，单位为毫秒
        sessionManager.setGlobalSessionTimeout(timeout);
        //sessionManager.setSessionIdCookie(new SimpleCookie(shiroProperties.getSessionIdName()));
        // shiro自己就自定义了一个，可以直接使用，还有其他的DAO，自行查看源码即可
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }

    /**告诉shiro加密方式
     * 密码解析器 有好几种，我这是MD5 1024次加密
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher createMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("MD5");
        matcher.setHashIterations(1024);
        return matcher;
    }

    /**
     * 注册全局异常处理
     * @return
     */
    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new ManagerExceptionHandler();
    }
}