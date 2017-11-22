package com.formento.realtimeticket.ticketreservation.reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TicketReservationRepository extends CrudRepository<TicketReservation, String> {

}
