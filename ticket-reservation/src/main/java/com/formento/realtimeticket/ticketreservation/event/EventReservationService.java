package com.formento.realtimeticket.ticketreservation.event;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventReservationService {

    private final EventReservationRepository repository;

    public EventReservationService(EventReservationRepository repository) {
        this.repository = repository;
    }

    public Mono<EventReservation> createEvent(final EventReservation eventReservation) {
        return repository.
            deleteReservations(eventReservation.getEventId()).
            flatMap(f -> repository.save(eventReservation)).
            filter(v -> v).
            flatMap(f -> repository.createReservations(eventReservation)).
            filter(v -> v > 0L).
            flatMap(v -> getById(eventReservation.getEventId()));
    }

    public Mono<EventReservation> getById(final String eventId) {
        return repository.getById(eventId);
    }

    public Flux<String> getReservationIdsFrom(String eventId, Integer count) {
        return repository.getReservation(eventId, count);
    }

}
