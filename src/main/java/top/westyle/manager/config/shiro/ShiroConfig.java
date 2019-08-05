package top.westyle.manager.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import top.westyle.manager.utils.ShiroUtil;

/**
 * @description shiro配置类
 * @author yjm
 * @date 2019-8-5 17:08:18
 */
@Configuration
public class ShiroConfig {



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
