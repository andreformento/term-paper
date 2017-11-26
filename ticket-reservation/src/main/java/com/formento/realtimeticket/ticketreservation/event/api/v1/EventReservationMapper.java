package com.formento.realtimeticket.ticketreservation.event.api.v1;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.formento.realtimeticket.ticketreservation.event.EventReservation;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
class EventReservationMapper {

    HttpEntity<Resource<EventReservationResponse>> mapToResponse(final EventReservation eventReservation) {
        return new ResponseEntity<>(
            new Resource<>(new EventReservationResponse(eventReservation), linkTo(EventReservationController.class).withSelfRel()),
            HttpStatus.CREATED);
    }

    HttpEntity<Resource<EventAvailableTicketsResponse>> mapToAvailableTickets(final String eventId, final Long availableTickets) {
        return new ResponseEntity<>(
            new Resource<>(new EventAvailableTicketsResponse(eventId, availableTickets), linkTo(EventReservationController.class).withSelfRel()),
            HttpStatus.OK);
    }

    EventReservation mapFromRequest(final EventReservationRequest eventReservationRequest) {
        return eventReservationRequest.toModel();
    }

}
