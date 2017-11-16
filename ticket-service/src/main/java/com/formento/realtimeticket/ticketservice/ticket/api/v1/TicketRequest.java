package com.formento.realtimeticket.ticketservice.ticket.api.v1;

import com.formento.realtimeticket.ticketservice.ticket.Ticket;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

class TicketRequest implements Serializable {

    private static final long serialVersionUID = -2019568277161677204L;

    @NotNull
    private final String idUser;

    @ConstructorProperties({"idUser"})
    TicketRequest(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    Ticket toModel(final String idEvent) {
        return new Ticket(idEvent, getIdUser());
    }

}
