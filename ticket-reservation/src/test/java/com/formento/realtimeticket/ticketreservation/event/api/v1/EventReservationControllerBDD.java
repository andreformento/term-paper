package com.formento.realtimeticket.ticketreservation.event.api.v1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.formento.realtimeticket.ticketreservation.event.EventReservation;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

public class EventReservationControllerBDD {

    private static final String EVENT_ID = "23d21a68-db74-4fad-a22c-8fe3c103ba8a";

    private final MockMvcRequestSpecification given;
    private final WebTestClient client;

    public EventReservationControllerBDD(MockMvcRequestSpecification given) {
        this.client = WebTestClient.bindToServer().build();
        this.given = given;
    }

    public EventReservationControllerBDD createEvent() {
        final String json = "{\"eventId\": \"" + EVENT_ID + "\", \"limit\": 30}";
        final EventReservationRequest request = new EventReservationRequest(EVENT_ID, 30L);
        final EventReservationResponse response = new EventReservationResponse(new EventReservation(EVENT_ID, 30L));

        final EntityExchangeResult<EventReservationResponse> result = client.
            post().
            uri("http://localhost:8080/event-reservations").
            syncBody(request).
            exchange().
            expectStatus().isOk().
            expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8).
            expectBody(EventReservationResponse.class).
            returnResult();

        assertThat(result).
            isNotNull().
            hasFieldOrPropertyWithValue("responseBody", response);

//        StepVerifier.create(result)
//            .expectNext(new EventAvailableTicketsResponse("Jane"), new EventAvailableTicketsResponse("Jason"))
//            .expectNextCount(3)
//            .consumeNextWith(p -> assertEquals("John", p.getName()))
//            .thenCancel()
//            .verify();

//        given.
//            body(json).
//            when().
//            post("/event-reservations").
//            then().
//            statusCode(is(HttpStatus.CREATED.value())).
//            content("eventId", equalTo(EVENT_ID)).
//            content("limit", equalTo(30));

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
