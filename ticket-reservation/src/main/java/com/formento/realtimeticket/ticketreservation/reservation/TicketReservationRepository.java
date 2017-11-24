package com.formento.realtimeticket.ticketreservation.reservation;

import com.formento.realtimeticket.ticketreservation.repository.RedisTemplateFactory;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
class TicketReservationRepository {

    private static final int TIMEOUT_OF_RESERVATION = 20;
    private static final TimeUnit UNIT_OF_TIMEOUT_OF_RESERVATION = TimeUnit.MINUTES;

    private final RedisTemplate<String, String> redisTemplateString;

    public TicketReservationRepository(RedisTemplateFactory redisTemplateFactory) {
        this.redisTemplateString = redisTemplateFactory.make(String.class);
    }

    public void saveReservation(final TicketReservation ticketReservation) {
        ticketReservation.getReservationIds().forEach(ticketId -> {
            redisTemplateString.opsForValue().set(ticketId, ticketReservation.getIdUser());
            redisTemplateString.expire(ticketId, TIMEOUT_OF_RESERVATION, UNIT_OF_TIMEOUT_OF_RESERVATION);
        });
    }

}
