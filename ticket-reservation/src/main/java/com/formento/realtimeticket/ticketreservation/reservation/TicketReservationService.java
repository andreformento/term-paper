package com.formento.realtimeticket.ticketreservation.reservation;

import com.formento.realtimeticket.ticketreservation.event.EventReservationService;
import com.formento.realtimeticket.ticketreservation.exception.TicketReservationFullException;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TicketReservationService {

    private final TicketReservationRepository ticketReservationRepository;
    private final EventReservationService eventReservationService;

    @Autowired
    public TicketReservationService(TicketReservationRepository ticketReservationRepository,
        EventReservationService eventReservationService) {
        this.ticketReservationRepository = ticketReservationRepository;
        this.eventReservationService = eventReservationService;
    }

    public Mono<TicketReservation> booking(final TicketReservation ticketReservationFromRequest, final Integer count) {
        final String idEvent = ticketReservationFromRequest.getIdEvent();

        return eventReservationService.
            getById(idEvent).
            map(eventReservation -> eventReservationService.
                getReservationIdsFrom(eventReservation.getEventId(), count).
                map(ticketId -> {
                    ticketReservationRepository.saveReservation(ticketId, idEvent);
                    return ticketId;
                }).
                switchIfEmpty(Mono.error(new TicketReservationFullException(ticketReservationFromRequest)))
            ).
            map(reservationIds -> reservationIds.
                reduce(ImmutableSet.<String>builder(), Builder::add).map(Builder::build)
            ).
            flatMap(reservationIds -> reservationIds.
                map(list -> new TicketReservation(ticketReservationFromRequest, list))
            );
    }

}
