package com.formento.realtimeticket.ticketservice.ticket.api.v1;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.formento.realtimeticket.ticketservice.ticket.Ticket;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
class TicketMapper {

    HttpEntity<Resource<TicketResponse>> mapToResponse(final Ticket ticket) {
        return new ResponseEntity<>(new Resource<>(new TicketResponse(ticket), linkTo(TicketController.class).withSelfRel()), HttpStatus.CREATED);
    }

    Ticket mapFromRequest(final String idEvent, final TicketRequest ticketRequest) {
        return ticketRequest.toModel(idEvent);
    }

}
