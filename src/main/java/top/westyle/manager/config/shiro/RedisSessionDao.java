package top.westyle.manager.config.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description 为了分布式实现session共享把shiro的session写到redis缓存中
 * @author yjm
 * @Date 2019-8-4 16:25:48
 */
public class RedisSessionDao extends AbstractSessionDAO {
    /**
     *定义shiro的session存入redis时sessionid的key前缀
     */
    private static final String sessionIdPrefix = "shiro-session-";
    /**
     *便于获取以shiro-session开头的所有key的session以及获取所有以此开头的key等
     */
    private static final String sessionIdPrefix_keys = "shiro-session-*";
    /**
     * 超时时间
     */
    private static final long timeout = 2592000;
    /**
     * 操作redis需要使用的类
     */
    @Autowired
    private RedisTemplate<Serializable, Session> redisTemplate;
    /**
     * 日志打印
     */
    private transient static Logger log = LoggerFactory.getLogger(RedisSessionDao.class);

    /**
     * 重写shiro创建session的方法
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = sessionIdPrefix + UUID.randomUUID().toString();
        //重置session的sessionID为自定义id
        assignSessionId(session ,sessionId);
        redisTemplate.opsForValue().set(sessionId ,session, timeout, TimeUnit.SECONDS);
        log.info("create shiro session, sessionId is :{} ", sessionId.toString());
        return sessionId;
    }

    /**
     * 重写从redis中读取指定sessionid的session
     * @param serializable
     * @return
     */
    @Override
    protected Session doReadSession(Serializable serializable) {
        log.info("read shiro session ,sessionId is :{}", serializable.toString());
        return redisTemplate.opsForValue().get(serializable);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        log.info("update shiro session ,sessionId is :{}", session.getId().toString());
        redisTemplate.opsForValue().set(session.getId(), session, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void delete(Session session) {
        log.info("delete shiro session ,sessionId is :{}", session.getId().toString());
        redisTemplate.opsForValue().getOperations().delete(session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Serializable> keys = redisTemplate.keys(sessionIdPrefix_keys);
        if (keys.size() == 0){
           return Collections.emptySet();
        }
        List<Session> sessions = redisTemplate.opsForValue().multiGet(keys);
        return Collections.unmodifiableCollection(sessions);
    }
}
