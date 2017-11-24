package com.formento.realtimeticket.ticketreservation.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class EventReservationTest {

    @Test
    public void shouldGenereteSequence() {
        final EventReservation eventReservation = new EventReservation("myId", 10L);
        assertThat(eventReservation.getSequence()).hasSize(10);
    }

}
