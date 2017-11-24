package com.formento.realtimeticket.ticketreservation.event.api.v1;

import com.formento.realtimeticket.ticketreservation.event.EventReservation;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

class EventReservationRequest implements Serializable {

    private static final long serialVersionUID = -2019568277161677204L;

    @NotNull
    private final String eventId;
    @NotNull
    private final Long limit;

    @ConstructorProperties({"eventId", "limit"})
    EventReservationRequest(String eventId, Long limit) {
        this.eventId = eventId;
        this.limit = limit;
    }

    public String getEventId() {
        return eventId;
    }

    EventReservation toModel() {
        return new EventReservation(this.eventId, limit);
    }

}
