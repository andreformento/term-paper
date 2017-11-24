package com.formento.realtimeticket.ticketreservation.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisTemplateFactory {

    private final JedisConnectionFactory jedisConnectionFactory;
    private final Map<Class<?>, RedisTemplate<?, ?>> map;

    public RedisTemplateFactory(JedisConnectionFactory jedisConnectionFactory) {
        this.jedisConnectionFactory = jedisConnectionFactory;
        this.map = new HashMap<>();
    }

    public <T, U> RedisTemplate<T, U> make(final Class<U> type) {
        if (map.containsKey(type)) {
            final RedisTemplate result = map.get(type);
            return result;
        }

        final RedisTemplate<T, U> redisTemplate = new RedisTemplate<T, U>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setValueSerializer(new GenericToStringSerializer<U>(type));
        redisTemplate.afterPropertiesSet();
        map.put(type, redisTemplate);
        return redisTemplate;
    }

}
