package com.julu.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * <pre>
 * <b>Redis配置类.</b>
 * <b>Description:
 * 1、注入 RedisTemplate 对象
 * 2、注入 HashOperations 对象
 * 3、注入 ValueOperations 对象
 * 4、注入 ListOperations 对象
 * 5、注入 SetOperations 对象
 * 6、注入 ZSetOperations 对象</b>
 * <pre>
 */
@Configuration
@ConditionalOnClass
public class RedisConfiguration {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    /**
     * <pre>
     * <b>注入 RedisTemplate 对象.</b>
     * <b>Description:</b>
     *
     * @return RedisTemplate
     * <pre>
     */
    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }


    /**
     * <pre>
     * <b>设置数据存入 redis 的序列化方式.</b>
     * <b>Description:</b>
     *
     * @param redisTemplate RedisTemplate
     * @param factory RedisConnectionFactory
     * <pre>
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer<Object>(Object.class));
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<Object>(Object.class));
        redisTemplate.setConnectionFactory(factory);
    }

    /**
     * <pre>
     * <b>注入 HashOperations 对象,可以使用 Hash 类型操作.</b>
     * <b>Description:</b>
     * @return redisTemplate RedisTemplate
     * @return HashOperations HashOperations
     * <pre>
     */
    @Bean
    public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    /**
     * <pre>
     * <b>注入 ValueOperations 对象,可以使用 String 操作.</b>
     * <b>Description:</b>
     *
     * @return redisTemplate RedisTemplate
     * @return ValueOperations ValueOperations
     * <pre>
     */
    @Bean
    public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * <pre>
     * <b>注入 ListOperations 对象,可以使用 List 操作.</b>
     * <b>Description:</b>
     *
     * @return redisTemplate RedisTemplate
     * @return ListOperations ListOperations
     * <pre>
     */
    @Bean
    public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    /**
     * <pre>
     * <b>注入 SetOperations 对象,可以使用 Set 操作.</b>
     * <b>Description:</b>
     *
     * @return redisTemplate RedisTemplate
     * @return SetOperations SetOperations
     * <pre>
     */
    @Bean
    public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    /**
     * <pre>
     * <b>注入 ZSetOperations 对象,可以使用 ZSet 操作.</b>
     * <b>Description:</b>
     * @return redisTemplate RedisTemplate
     * @return ZSetOperations ZSetOperations
     * <pre>
     */
    @Bean
    public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }
}
