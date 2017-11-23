package com.formento.realtimeticket.ticketreservation.reservation;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class TicketReservationRepositoryRedis implements TicketReservationRepository {

    private final String counterKey = "atomic-counter";
    private final RedisTemplate<String, Long> redisTemplate;
    private final ValueOperations<String, Long> valueOperations;

    public TicketReservationRepositoryRedis(JedisConnectionFactory jedisConnectionFactory) {
        this.redisTemplate = new RedisTemplate<>();
        this.redisTemplate.setConnectionFactory(jedisConnectionFactory);

        this.valueOperations = redisTemplate.opsForValue();
    }

    public Long increment(final Long delta) {
        return valueOperations.increment(counterKey, delta);
    }

}
