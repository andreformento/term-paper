package com.formento.realtimeticket.ticketreservation.reservation;

import static java.util.Optional.empty;

import java.util.Objects;
import java.util.Optional;
import javax.validation.constraints.NotNull;

public class TicketReservation {

    @NotNull
    private final String idEvent;

    @NotNull
    private final String idUser;

    @NotNull
    private final Long count;

    @NotNull
    private final Optional<Long> sequence;

    public TicketReservation(String idEvent, String idUser) {
        this.idEvent = idEvent;
        this.idUser = idUser;
        this.sequence = empty();
    }

    public TicketReservation(TicketReservation ticketReservation, Long sequence) {
        this(ticketReservation, Optional.of(sequence));
    }

    public TicketReservation(TicketReservation ticketReservation, Optional<Long> sequence) {
        this.idEvent = ticketReservation.getIdEvent();
        this.idUser = ticketReservation.getIdUser();
        this.sequence = sequence;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public String getIdUser() {
        return idUser;
    }

    public Optional<Long> getSequence() {
        return sequence;
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
