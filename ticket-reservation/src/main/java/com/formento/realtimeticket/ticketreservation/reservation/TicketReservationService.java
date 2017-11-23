package com.formento.realtimeticket.ticketreservation.reservation;

import com.formento.realtimeticket.ticketreservation.event.Event;
import com.formento.realtimeticket.ticketreservation.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketReservationService {

    private final TicketReservationRepository ticketReservationRepository;
    private final EventService eventService;

    @Autowired
    public TicketReservationService(TicketReservationRepository ticketReservationRepository,
        EventService eventService) {
        this.ticketReservationRepository = ticketReservationRepository;
        this.eventService = eventService;
    }

    public TicketReservation booking(final TicketReservation ticketReservation) {
        // validate if available

        final Event event = eventService.getById(ticketReservation.getIdEvent());
        final Long ticketSequence = ticketReservationRepository.increment();

        if (event.isValidSequence(ticketSequence)) {
            return new TicketReservation(ticketReservation, ticketSequence);
        } else {
            // throw
        }

//        return ticketReservationRepository.save(ticketReservation);
    }

}
