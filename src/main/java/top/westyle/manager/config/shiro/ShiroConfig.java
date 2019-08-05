package top.westyle.manager.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;

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
     * shiro的拦截器，在spring mvc中也有相同的配置，这里不再多说
     * @author Super小靖
     * @date 2018/8/29
     * @param securityManager
     * @return
     **/
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置 securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", new ShiroAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filters);
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
        // 配置退出过滤器，其中具体的退出代码 Shiro已经替我们实现了
       // filterChainDefinitionMap.put(shiroProperties.getLogoutUrl(), "logout");
        // 除上以外所有 url都必须认证通过才可以访问，未通过认证自动访问 LoginUrl
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 配置各种manager,跟xml的配置很像，但是，这里有一个细节，就是各个set的次序不能乱
     * @author Super小靖
     * @date 2018/8/29
     * @param realm
     * @return
     **/
    @Bean
    @DependsOn({"shiroRealm"})
    public SecurityManager securityManager(ShiroRealm realm, RedisTemplate<Object, Object> template) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 配置 rememberMeCookie 查看源码可以知道，这里的rememberMeManager就仅仅是一个赋值，所以先执行
      //  securityManager.setRememberMeManager(rememberMeManager());
        // 配置 缓存管理类 cacheManager，这个cacheManager必须要在前面执行，因为setRealm 和 setSessionManage都有方法初始化了cachemanager,看下源码就知道了
        securityManager.setCacheManager(cacheManager(template));
        // 配置 SecurityManager，并注入 shiroRealm 这个跟springmvc集成很像，不多说了
        securityManager.setRealm(realm);
        // 配置 sessionManager
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }
    /**
     * 生成一个ShiroRedisCacheManager 这没啥好说的
     * @author Super小靖
     * @date 2018/8/29
     * @param template
     * @return
     **/
    private ShiroRedisCacheManager cacheManager(RedisTemplate template){
        return new ShiroRedisCacheManager(template);
    }

    /**
     * session 管理对象
     *
     * @return DefaultWebSessionManager
     */
    private DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 设置session超时时间，单位为毫秒
        sessionManager.setGlobalSessionTimeout(1800000);
        //sessionManager.setSessionIdCookie(new SimpleCookie(shiroProperties.getSessionIdName()));
        // shiro自己就自定义了一个，可以直接使用，还有其他的DAO，自行查看源码即可
        sessionManager.setSessionDAO(new RedisSessionDao());
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
     * 这是我自己的realm 我自定义了一个密码解析器，这个比较简单，稍微跟一下源码就知道这玩意
     * @param matcher
     * @return
     */
    @Bean
    @DependsOn({"hashedCredentialsMatcher"})
    public ShiroRealm shiroRealm(HashedCredentialsMatcher matcher) {
        // 配置 Realm，需自己实现
        ShiroRealm realm = new ShiroRealm();
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

}
