package com.formento.realtimeticket.ticketreservation.reservation.api.v1;

import com.formento.realtimeticket.ticketreservation.reservation.TicketReservationService;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/{idEvent}/tickets")
    public HttpEntity<Resource<TicketReservationResponse>> booking(
        @PathVariable("idEvent") String idEvent,
        @RequestBody final TicketReservationRequest ticketReservationRequest
    ) {

        return mapper.mapToResponse(service.booking(mapper.mapFromRequest(idEvent, ticketReservationRequest)));
    }

}
