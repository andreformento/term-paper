package com.formento.realtimeticket.ticketreservation.reservation.api.v1;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.formento.realtimeticket.ticketreservation.reservation.TicketReservation;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
class TicketReservationMapper {

    Mono<TicketReservationResponse> mapToResponse(final Mono<TicketReservation> ticketReservation) {
        return ticketReservation.map(TicketReservationResponse::new);
    }

    TicketReservation mapFromRequest(final String idEvent, final TicketReservationRequest ticketReservationRequest) {
        return ticketReservationRequest.toModel(idEvent);
    }

}
