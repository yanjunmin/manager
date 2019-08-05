package top.westyle.manager.config.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import top.westyle.manager.utils.SystemConstant;

import java.io.Serializable;

/**
 * @Author yjm
 * @Date 2019-8-5 17:23:40
 * @Description 接口实现
 */
public class ShiroRedisCacheManager implements CacheManager, Serializable {

    private transient static Logger log = LoggerFactory.getLogger(ShiroRedisCacheManager.class);

    private transient RedisTemplate<Object, Object> redisTemplate;

    public ShiroRedisCacheManager() {
    }

    public ShiroRedisCacheManager(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("Cache name cannot be null or empty.");
        }
        log.debug("redis cache manager get cache name is :{}", name);
        Cache cache = (Cache) redisTemplate.opsForValue().get(name);
        if (cache == null) {
            cache = new ShiroRedisCache<>(redisTemplate);
            redisTemplate.opsForValue().set(SystemConstant.shiro_cache_prefix + name, cache);
        }
        return cache;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
