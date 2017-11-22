package com.formento.realtimeticket.ticketreservation.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketReservationService {

    private final TicketReservationRepository ticketReservationRepository;

    @Autowired
    public TicketReservationService(TicketReservationRepository ticketReservationRepository) {
        this.ticketReservationRepository = ticketReservationRepository;
    }

    public TicketReservation booking(final TicketReservation ticketReservation) {
        // validate if available
        return ticketReservationRepository.save(ticketReservation);
    }

}
