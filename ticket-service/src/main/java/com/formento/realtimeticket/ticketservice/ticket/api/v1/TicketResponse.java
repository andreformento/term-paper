package com.formento.realtimeticket.ticketservice.ticket.api.v1;

import com.formento.realtimeticket.ticketservice.ticket.Ticket;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

class TicketResponse implements Serializable {

    private static final long serialVersionUID = -1535804038626605179L;

    @NotNull
    private final String idEvent;

    @NotNull
    private final String idUser;


    TicketResponse(final Ticket ticket) {
        this.idEvent = ticket.getIdEvent();
        this.idUser = ticket.getIdUser();
    }

    public String getIdEvent() {
        return idEvent;
    }

    public String getIdUser() {
        return idUser;
    }
}
