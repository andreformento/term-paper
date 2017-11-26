package com.formento.realtimeticket.ticketreservation.event.api.v1;

import com.formento.realtimeticket.ticketreservation.event.EventReservation;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
class EventReservationMapper {

    Mono<EventReservationResponse> mapToResponse(final Mono<EventReservation> eventReservation) {
        return eventReservation.map(EventReservationResponse::new);
    }

    Mono<EventAvailableTicketsResponse> mapToAvailableTickets(final String eventId, final Mono<Long> availableTickets) {
        return availableTickets.map(at -> new EventAvailableTicketsResponse(eventId, at));
    }

    EventReservation mapFromRequest(final EventReservationRequest eventReservationRequest) {
        return eventReservationRequest.toModel();
    }

}
