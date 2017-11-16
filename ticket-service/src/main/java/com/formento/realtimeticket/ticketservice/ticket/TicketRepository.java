package com.formento.realtimeticket.ticketservice.ticket;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TicketRepository extends CrudRepository<Ticket, String> {

}
