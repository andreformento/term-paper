package com.formento.realtimeticket.ticketreservation.reservation;

import com.formento.realtimeticket.ticketreservation.repository.RedisAtomicReservation;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class TicketReservationRepositoryRedis implements TicketReservationRepository {

    private final String counterKey = "atomic-counter";
    private final RedisTemplate<String, Long> redisTemplate;
    private final ValueOperations<String, Long> valueOperations;
    private final JedisConnectionFactory jedisConnectionFactory;

    public TicketReservationRepositoryRedis(JedisConnectionFactory jedisConnectionFactory) {
        this.jedisConnectionFactory = jedisConnectionFactory;

        this.redisTemplate = new RedisTemplate<>();
        this.redisTemplate.setConnectionFactory(jedisConnectionFactory);

        this.valueOperations = redisTemplate.opsForValue();
    }

    public Long increment(final Long delta) {
        final RedisAtomicReservation redisAtomicReservation = new RedisAtomicReservation(counterKey, jedisConnectionFactory);

        return redisAtomicReservation.compareAndIncrement(delta, d -> d != null && d <= 100);
    }

    @Override
    public Long decrement(Long delta) {
        return increment(delta * (-1));
    }

}
