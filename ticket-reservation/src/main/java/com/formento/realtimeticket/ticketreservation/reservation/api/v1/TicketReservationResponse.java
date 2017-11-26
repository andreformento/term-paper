package com.formento.realtimeticket.ticketreservation.reservation.api.v1;

import com.formento.realtimeticket.ticketreservation.reservation.TicketReservation;
import java.io.Serializable;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicketReservationResponse)) {
            return false;
        }
        TicketReservationResponse that = (TicketReservationResponse) o;
        return Objects.equals(idEvent, that.idEvent) &&
            Objects.equals(idUser, that.idUser) &&
            Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvent, idUser, count);
    }
}
