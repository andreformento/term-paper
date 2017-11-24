package com.formento.realtimeticket.ticketreservation.reservation.api.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class TicketReservationControllerTest {

    private static final String KEY_EVENT = "e-uuidEvent";

    @Autowired
    private WebApplicationContext context;
    private MockMvcRequestSpecification given;

    @Before
    public void init() {
        final MockMvc mvc = webAppContextSetup(context).build();
        given = RestAssuredMockMvc.given().mockMvc(mvc).contentType(MediaType.APPLICATION_JSON_VALUE).accept(ContentType.JSON);
    }
    
    @Test
    @Ignore
    public void shouldBooking() {

        final String json = "{\"idUser\": \"uuid123\", \"count\": 3}";
        final String idEvent = "uuid456";

        given.
            body(json).
            when().
            post("/events/" + idEvent + "/tickets").
            then().
            statusCode(is(HttpStatus.CREATED.value())).
            content("idEvent", equalTo(idEvent)).
            content("idUser", equalTo("uuid123"));
    }

}
