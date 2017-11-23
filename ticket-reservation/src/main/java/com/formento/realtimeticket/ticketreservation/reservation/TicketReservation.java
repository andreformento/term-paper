package com.formento.realtimeticket.ticketreservation.reservation;

import java.util.Objects;
import javax.validation.constraints.NotNull;

public class TicketReservation {

    @NotNull
    private final String idEvent;

    @NotNull
    private final String idUser;

    @NotNull
    private final Long count;

    @NotNull
    private final Status status;

    enum Status {
        PRE_RESERVE, RESERVED, FULL
    }

    public TicketReservation(String idEvent, String idUser, Long count) {
        this.idEvent = idEvent;
        this.idUser = idUser;
        this.count = count;
        this.status = Status.PRE_RESERVE;
    }

    public TicketReservation(TicketReservation ticketReservation, Status status) {
        this.idEvent = ticketReservation.idEvent;
        this.idUser = ticketReservation.idUser;
        this.count = ticketReservation.count;
        this.status = status;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public String getIdUser() {
        return idUser;
    }

    public Long getCount() {
        return count;
    }

    public Status getStatus() {
        return status;
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
