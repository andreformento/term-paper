package com.formento.realtimeticket.ticketreservation.event.api.v1;

import static org.springframework.http.HttpStatus.CREATED;

import com.formento.realtimeticket.ticketreservation.event.EventReservationService;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
    @ResponseStatus(CREATED)
    public Mono<EventReservationResponse> createEvent(@RequestBody final EventReservationRequest eventReservationRequest) {
        return mapper.mapToResponse(service.createEvent(mapper.mapFromRequest(eventReservationRequest)));
    }

    @GetMapping(value = "/{eventId}/available-tickets")
    public Mono<EventAvailableTicketsResponse> getAvailableTickets(@PathVariable("eventId") final String eventId) {
        return mapper.mapToAvailableTickets(eventId, service.getAvailableTickets(eventId));
    }

}
