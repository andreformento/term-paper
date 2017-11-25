package com.formento.realtimeticket.ticketreservation.event.api.v1;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Autowired
    private WebApplicationContext context;
    private EventReservationControllerBDD eventBDD;

    @Before
    public void init() {
        final MockMvc mvc = webAppContextSetup(context).build();
        this.eventBDD = new EventReservationControllerBDD(
            RestAssuredMockMvc.given().mockMvc(mvc).contentType(MediaType.APPLICATION_JSON_VALUE).accept(ContentType.JSON));
    }

    @Test
    public void shouldCreateEvent() {
        eventBDD.createEvent();
    }

}
