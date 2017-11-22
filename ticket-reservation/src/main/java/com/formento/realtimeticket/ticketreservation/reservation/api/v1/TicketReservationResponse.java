package com.formento.realtimeticket.ticketreservation.reservation.api.v1;

import com.formento.realtimeticket.ticketreservation.reservation.TicketReservation;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

class TicketReservationResponse implements Serializable {

    private static final long serialVersionUID = -1535804038626605179L;

    @NotNull
    private final String idEvent;

    @NotNull
    private final String idUser;


    TicketReservationResponse(final TicketReservation ticketReservation) {
        this.idEvent = ticketReservation.getIdEvent();
        this.idUser = ticketReservation.getIdUser();
    }

    public String getIdEvent() {
        return idEvent;
    }

    public String getIdUser() {
        return idUser;
    }
}
