package com.formento.realtimeticket.ticketreservation.event;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;

public class Event implements Serializable {

    private static final long serialVersionUID = -8738912588701579817L;

    @NotNull
    private final String id;

    @NotNull
    private final Long limit;

    @ConstructorProperties({"id", "limit"})
    public Event(String id, Long limit) {
        this.id = id;
        this.limit = limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
