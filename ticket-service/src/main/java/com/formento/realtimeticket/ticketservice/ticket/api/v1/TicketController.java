package com.formento.realtimeticket.ticketservice.ticket.api.v1;

import com.formento.realtimeticket.ticketservice.ticket.TicketService;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events/{idEvent}/tickets")
public class TicketController {

    private final TicketService service;
    private final TicketMapper mapper;

    public TicketController(TicketService service, TicketMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public HttpEntity<Resource<TicketResponse>> booking(
        @PathVariable("idEvent") String idEvent,
        @RequestBody final TicketRequest ticketRequest
    ) {
        return mapper.mapToResponse(service.booking(mapper.mapFromRequest(idEvent, ticketRequest)));
    }

}
