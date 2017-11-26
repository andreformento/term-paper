package com.formento.realtimeticket.ticketreservation.event;

import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class EventReservationService {

    private final EventReservationRepository repository;

    public EventReservationService(EventReservationRepository repository) {
        this.repository = repository;
    }

    public EventReservation createEvent(final EventReservation eventReservation) {
        repository.deleteReservations(eventReservation.getEventId());
        repository.save(eventReservation);
        repository.createReservations(eventReservation);

        return getById(eventReservation.getEventId());
    }

    public EventReservation getById(final String eventId) {
        return repository.getById(eventId);
    }

    public Set<String> getReservationIdsFrom(String eventId, Integer count) {
        return repository.getReservation(eventId, count);
    }

    public Long getAvailableTickets(final String eventId) {
        return repository.getAvailableTickets(eventId);
    }
}
