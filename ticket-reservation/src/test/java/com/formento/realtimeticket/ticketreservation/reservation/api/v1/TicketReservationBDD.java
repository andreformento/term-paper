package com.formento.realtimeticket.ticketreservation.reservation.api.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.formento.realtimeticket.ticketreservation.event.api.v1.EventReservationControllerBDD;
import io.restassured.module.mockmvc.response.ValidatableMockMvcResponse;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.springframework.http.HttpStatus;

public class TicketReservationBDD {

    private final MockMvcRequestSpecification given;
    private final EventReservationControllerBDD eventBDD;

    public TicketReservationBDD(MockMvcRequestSpecification given) {
        this.given = given;
        this.eventBDD = new EventReservationControllerBDD(given);
    }

    public TicketReservationBDD withEvent() {
        eventBDD.createEvent();
        return this;
    }

    public When whenCount(final Integer count) {
        return new When(count);
    }

    class When {

        private final String json;

        private When(final Integer count) {
            this.json = "{\"idUser\": \"uuid123\", \"count\": " + count + "}";
        }

        public Then then() {
            return new Then(json);
        }
    }

    class Then {

        private final ValidatableMockMvcResponse response;

        private Then(final String json) {
            this.response = given.
                body(json).
                when().
                post("/events/" + eventBDD.getEventId() + "/tickets").
                then();
        }

        public ValidatableMockMvcResponse resultOk() {
            response.
                statusCode(is(HttpStatus.CREATED.value())).
                content("idEvent", equalTo(eventBDD.getEventId())).
                content("idUser", equalTo("uuid123"));
            return response;
        }

        public Then andEventMustHave(final Integer availableTickets) {
            eventBDD.count(availableTickets);
            return this;
        }
    }

}
