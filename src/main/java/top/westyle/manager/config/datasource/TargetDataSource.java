package top.westyle.manager.config.datasource;

import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.METHOD,
        ElementType.TYPE
})
@Documented
public @interface TargetDataSource {
    String name();
}
