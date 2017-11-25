package com.formento.realtimeticket.ticketreservation.reservation.api.v1;

import static org.springframework.http.HttpStatus.CREATED;

import com.formento.realtimeticket.ticketreservation.reservation.TicketReservationService;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/events")
public class TicketReservationController {

    private final TicketReservationService service;
    private final TicketReservationMapper mapper;

    public TicketReservationController(TicketReservationService service, TicketReservationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @RequestMapping("/{idEvent}/tickets")
    public Mono<TicketReservationResponse> booking(
        @PathVariable("idEvent") String idEvent,
        @RequestBody final TicketReservationRequest ticketReservationRequest
    ) {
        return mapper.mapToResponse(service.booking(mapper.mapFromRequest(idEvent, ticketReservationRequest), ticketReservationRequest.getCount()));
    }

}
