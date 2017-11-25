package com.formento.realtimeticket.ticketreservation.event;

import com.formento.realtimeticket.ticketreservation.exception.RepositoryNotFoundException;
import java.time.Duration;
import java.util.Objects;
import java.util.Set;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
class EventReservationRepository {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    EventReservationRepository(ReactiveRedisTemplate<String, String> reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    public Mono<Boolean> deleteReservations(final String eventId) {
        return reactiveRedisTemplate.opsForSet().delete(eventId);
    }

    public Mono<Long> createReservations(final EventReservation eventReservation) {
        final String eventId = eventReservation.getEventId();

        final Set<String> sequence = eventReservation.getSequence();
        final String[] array = sequence.toArray(new String[sequence.size()]);

        return reactiveRedisTemplate.opsForSet().add(eventId, array);
    }

    public Mono<Boolean> save(final EventReservation eventReservation) {
        final Mono<Boolean> events = reactiveRedisTemplate.
            opsForHash().
            put("events", eventReservation.getEventId(), eventReservation.getLimit().toString());
        final Boolean block = events.block(Duration.ofSeconds(1));
        System.out.println(block);
        return events;
    }

    public Mono<EventReservation> getById(final String eventId) {
        return reactiveRedisTemplate.
            opsForHash().
            get("events", eventId).
            filter(Objects::nonNull).
            map(limit -> Long.valueOf((String) limit)).
            map(limit -> new EventReservation(eventId, limit)).
            switchIfEmpty(Mono.error(new RepositoryNotFoundException(eventId)));
    }

    public Flux<String> getReservation(String eventId, Integer count) {
        return reactiveRedisTemplate.
            opsForSet().
            pop(eventId, count);
    }

}
