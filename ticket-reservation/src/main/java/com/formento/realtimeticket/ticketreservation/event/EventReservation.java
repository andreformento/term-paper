package com.formento.realtimeticket.ticketreservation.event;

import com.google.common.collect.ImmutableSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Transient;

public class EventReservation {

    @NotNull
    private final String eventId;

    @NotNull
    private final Long limit;

    @Transient
    private transient Set<String> sequence;

    public EventReservation(String eventId, Long limit) {
        this.eventId = eventId;
        this.limit = limit;
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
        if (!(o instanceof EventReservation)) {
            return false;
        }
        EventReservation eventReservation = (EventReservation) o;
        return Objects.equals(eventId, eventReservation.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }

    public Set<String> getSequence() {
        if (this.sequence == null) {
            final Set<String> results = new HashSet<>();

            while (results.size() < limit) {
                final Long timestamp = System.currentTimeMillis();
                results.add(String.valueOf(timestamp) + "-" + UUID.randomUUID().toString());
            }

            this.sequence = ImmutableSet.copyOf(results);
        }
        return sequence;
    }

}
