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

    @NotNull
    private final Integer count;

    TicketReservationResponse(final TicketReservation ticketReservation) {
        this.idEvent = ticketReservation.getIdEvent();
        this.idUser = ticketReservation.getIdUser();
        this.count = ticketReservation.getCount();
    }

    public String getIdEvent() {
        return idEvent;
    }

    public String getIdUser() {
        return idUser;
    }

    public Integer getCount() {
        return count;
    }
}
