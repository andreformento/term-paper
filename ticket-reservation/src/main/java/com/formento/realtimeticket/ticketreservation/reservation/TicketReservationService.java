package com.formento.realtimeticket.ticketreservation.reservation;

import com.formento.realtimeticket.ticketreservation.event.Event;
import com.formento.realtimeticket.ticketreservation.event.EventService;
import com.formento.realtimeticket.ticketreservation.exception.TicketReservationFullException;
import com.formento.realtimeticket.ticketreservation.reservation.TicketReservation.Status;
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
        final Long count = ticketReservationRepository.increment(ticketReservation.getCount());

        if (event.isValidSequence(count)) {
            return new TicketReservation(ticketReservation, Status.RESERVED);
        } else {
            ticketReservationRepository.decrement(ticketReservation.getCount());

            throw new TicketReservationFullException(new TicketReservation(ticketReservation, Status.FULL));
        }
    }

}
