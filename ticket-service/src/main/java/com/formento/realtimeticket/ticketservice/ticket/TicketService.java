package com.formento.realtimeticket.ticketservice.ticket;

import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket booking(final Ticket ticket) {
        // validate if available
        return ticketRepository.save(ticket);
    }

}
