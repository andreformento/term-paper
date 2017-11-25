package com.formento.realtimeticket.ticketreservation.reservation;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
class TicketReservationRepository {

    private static final int TIMEOUT_OF_RESERVATION = 20;
    private static final ChronoUnit UNIT_OF_TIMEOUT_OF_RESERVATION = ChronoUnit.MINUTES;

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public TicketReservationRepository(ReactiveRedisTemplate<String, String> reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    public void saveReservation(final String ticketId, final String idUser) {
        reactiveRedisTemplate.opsForValue().set(ticketId, idUser);
        reactiveRedisTemplate.expire(ticketId, Duration.of(TIMEOUT_OF_RESERVATION, UNIT_OF_TIMEOUT_OF_RESERVATION));
    }

}
