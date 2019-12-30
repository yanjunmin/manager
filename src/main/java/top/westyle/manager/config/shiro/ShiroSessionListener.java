package top.westyle.manager.config.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *  配置session监听器
 */
public class ShiroSessionListener implements SessionListener {

    private static final Logger logger = LoggerFactory.getLogger(KickoutSessionFilter.class);
    /**
     * 统计在线人数
     * 线程安全
     */
    private final static AtomicInteger sessionCount = new AtomicInteger(0);

    public ShiroSessionListener() {

    }

    /**
     * 会话创建时触发
     * @param session
     */
    @Override
    public void onStart(Session session) {
        logger.info("触发创建回话");
        //会话创建，在线人数加一
        sessionCount.incrementAndGet();
    }
    /**
     * 退出会话时触发
     * @param session
     */
    @Override
    public void onStop(Session session) {
        logger.info("触发退出回话");
        //会话退出,在线人数减一
        sessionCount.decrementAndGet();
    }

    /**
     * 会话过期时触发
     * @param session
     */
    @Override
    public void onExpiration(Session session) {
        //会话过期,在线人数减一
        sessionCount.decrementAndGet();

    }
    /**
     * 获取在线人数使用
     * @return
     */
    public static AtomicInteger getSessionCount() {
        return sessionCount;
    }
}
