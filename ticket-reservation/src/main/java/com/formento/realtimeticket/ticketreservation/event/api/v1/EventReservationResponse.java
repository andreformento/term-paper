package com.formento.realtimeticket.ticketreservation.event.api.v1;

import com.formento.realtimeticket.ticketreservation.event.EventReservation;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;

class EventReservationResponse implements Serializable {

    private static final long serialVersionUID = -1535804038626605179L;

    @NotNull
    private final String eventId;
    @NotNull
    private final Long limit;

    EventReservationResponse(EventReservation model) {
        this.eventId = model.getEventId();
        this.limit = model.getLimit();
    }

    public String getEventId() {
        return eventId;
    }

    public Long getLimit() {
        return limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventReservationResponse)) {
            return false;
        }
        EventReservationResponse that = (EventReservationResponse) o;
        return Objects.equals(eventId, that.eventId) &&
            Objects.equals(limit, that.limit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, limit);
    }
}
