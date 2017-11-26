package com.formento.realtimeticket.ticketreservation.event.api.v1;

import java.io.Serializable;
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
}
