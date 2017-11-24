package com.formento.realtimeticket.ticketreservation.event.api.v1;

import com.formento.realtimeticket.ticketreservation.event.EventReservationService;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event-reservations")
public class EventReservationController {

    private final EventReservationService service;
    private final EventReservationMapper mapper;

    public EventReservationController(EventReservationService service, EventReservationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public HttpEntity<Resource<EventReservationResponse>> booking(
        @PathVariable("idEvent") String idEvent,
        @RequestBody final EventReservationRequest eventReservationRequest
    ) {

        return mapper.mapToResponse(service.createEvent(mapper.mapFromRequest(eventReservationRequest)));
    }

}
