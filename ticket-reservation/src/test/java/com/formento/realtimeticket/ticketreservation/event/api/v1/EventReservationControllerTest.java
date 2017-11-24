package com.formento.realtimeticket.ticketreservation.event.api.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
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
public class EventReservationControllerTest {

    private static final String EVENT_ID = "23d21a68-db74-4fad-a22c-8fe3c103ba8a";

    @Autowired
    private WebApplicationContext context;
    private MockMvcRequestSpecification given;
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Before
    public void init() {
        final MockMvc mvc = webAppContextSetup(context).build();
        given = RestAssuredMockMvc.given().mockMvc(mvc).contentType(MediaType.APPLICATION_JSON_VALUE).accept(ContentType.JSON);
    }

    @Test
    public void shouldCreateEvent() {

        final String json = "{\"eventId\": \"" + EVENT_ID + "\", \"limit\": 3}";

        given.
            body(json).
            when().
            post("/event-reservations").
            then().
            statusCode(is(HttpStatus.CREATED.value())).
            content("eventId", equalTo(EVENT_ID)).
            content("limit", equalTo(3));
    }

}
