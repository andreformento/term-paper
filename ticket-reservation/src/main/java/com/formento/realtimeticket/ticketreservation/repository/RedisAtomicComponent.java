package com.formento.realtimeticket.ticketreservation.repository;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisAtomicComponent {

    private final JedisConnectionFactory jedisConnectionFactory;

    public RedisAtomicComponent(JedisConnectionFactory jedisConnectionFactory) {
        this.jedisConnectionFactory = jedisConnectionFactory;
    }

    public RedisAtomicReservation redisAtomicReservation(final String counterKey) {
        return new RedisAtomicReservation(counterKey, jedisConnectionFactory, 0);
    }

}
