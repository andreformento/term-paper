package com.formento.realtimeticket.ticketreservation.reservation;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;

public class TicketReservation implements Serializable {

    private static final long serialVersionUID = 2786181361391040157L;

    @NotNull
    private final String idEvent;

    @NotNull
    private final String idUser;

    @ConstructorProperties({"idEvent", "idUser"})
    public TicketReservation(String idEvent, String idUser) {
        this.idEvent = idEvent;
        this.idUser = idUser;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public String getIdUser() {
        return idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicketReservation)) {
            return false;
        }
        TicketReservation ticketReservation = (TicketReservation) o;
        return Objects.equals(idEvent, ticketReservation.idEvent) &&
            Objects.equals(idUser, ticketReservation.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvent, idUser);
    }

}
