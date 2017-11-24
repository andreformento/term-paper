package com.formento.realtimeticket.ticketreservation.reservation.api.v1;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.formento.realtimeticket.ticketreservation.reservation.TicketReservation;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
class TicketReservationMapper {

    HttpEntity<Resource<TicketReservationResponse>> mapToResponse(final TicketReservation ticketReservation) {
        return new ResponseEntity<>(
            new Resource<>(new TicketReservationResponse(ticketReservation), linkTo(TicketReservationController.class).withSelfRel()),
            HttpStatus.CREATED);
    }

    TicketReservation mapFromRequest(final String idEvent, final TicketReservationRequest ticketReservationRequest) {
        return ticketReservationRequest.toModel(idEvent);
    }

}
