package com.formento.realtimeticket.ticketreservation.event.api.v1;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;

class EventAvailableTicketsResponse implements Serializable {

    private static final long serialVersionUID = -1535804038626605179L;

    @NotNull
    private final String eventId;
    @NotNull
    private final Long availableTickets;

    public EventAvailableTicketsResponse(@NotNull String eventId, @NotNull Long availableTickets) {
        this.eventId = eventId;
        this.availableTickets = availableTickets;
    }

    public String getEventId() {
        return eventId;
    }

    public Long getAvailableTickets() {
        return availableTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventAvailableTicketsResponse)) {
            return false;
        }
        EventAvailableTicketsResponse response = (EventAvailableTicketsResponse) o;
        return Objects.equals(eventId, response.eventId) &&
            Objects.equals(availableTickets, response.availableTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, availableTickets);
    }
}
