package com.formento.realtimeticket.ticketreservation.reservation;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import javax.validation.constraints.NotNull;

public class TicketReservation {

    @NotNull
    private final String idEvent;

    @NotNull
    private final String idUser;

    @NotNull
    private final Collection<String> reservationIds;

    private TicketReservation(String idEvent, String idUser, Collection<String> reservationIds) {
        this.idEvent = idEvent;
        this.idUser = idUser;
        this.reservationIds = reservationIds;
    }

    public TicketReservation(String idEvent, String idUser) {
        this(idEvent, idUser, Collections.emptyList());
    }

    public TicketReservation(TicketReservation ticketReservation, Collection<String> reservationIds) {
        this(ticketReservation.idEvent, ticketReservation.idUser, reservationIds);
    }

    public String getIdEvent() {
        return idEvent;
    }

    public String getIdUser() {
        return idUser;
    }

    public Collection<String> getReservationIds() {
        return reservationIds;
    }

    public Integer getCount() {
        return getReservationIds().size();
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
