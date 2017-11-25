package com.formento.realtimeticket.ticketreservation.event.api.v1;

import com.formento.realtimeticket.ticketreservation.event.EventReservation;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
class EventReservationMapper {

    Mono<EventReservationResponse> mapToResponse(final Mono<EventReservation> eventReservation) {
        return eventReservation.map(EventReservationResponse::new);
    }

    EventReservation mapFromRequest(final EventReservationRequest eventReservationRequest) {
        return eventReservationRequest.toModel();
    }

}
