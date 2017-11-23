package com.formento.realtimeticket.ticketreservation.reservation;

interface TicketReservationRepository {

    Long increment(final Long delta);
    Long decrement(final Long delta);

}
