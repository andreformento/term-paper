package com.formento.realtimeticket.ticketreservation.event.api.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.springframework.http.HttpStatus;

public class EventReservationControllerBDD {

    private static final String EVENT_ID = "23d21a68-db74-4fad-a22c-8fe3c103ba8a";

    private final MockMvcRequestSpecification given;

    public EventReservationControllerBDD(MockMvcRequestSpecification given) {
        this.given = given;
    }

    public EventReservationControllerBDD createEvent() {
        final String json = "{\"eventId\": \"" + EVENT_ID + "\", \"limit\": 30}";

        given.
            body(json).
            when().
            post("/event-reservations").
            then().
            statusCode(is(HttpStatus.CREATED.value())).
            content("eventId", equalTo(EVENT_ID)).
            content("limit", equalTo(30));

        return this;
    }

    public void count(final Integer availableTickets) {
        given.
            when().
            get("/event-reservations/" + EVENT_ID + "/available-tickets").
            then().
            statusCode(is(HttpStatus.OK.value())).
            content("eventId", equalTo(EVENT_ID)).
            content("availableTickets", equalTo(availableTickets));
    }

    public String getEventId() {
        return EVENT_ID;
    }
}
