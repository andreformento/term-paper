package com.formento.realtimeticket.ticketreservation.exception;

import com.formento.realtimeticket.ticketreservation.reservation.TicketReservation;

public class TicketReservationFullException extends RuntimeException {

    public TicketReservationFullException(final TicketReservation ticketReservation) {
        super("EventReservation " + ticketReservation.getIdEvent() + " is full");
    }

}
