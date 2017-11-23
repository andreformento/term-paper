package com.formento.realtimeticket.ticketreservation.reservation.api.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.google.common.collect.Range;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class TicketReservationControllerTest {

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

    // https://github.com/eugenp/tutorials/blob/master/persistence-modules/spring-data-redis/src/main/java/com/baeldung/spring/data/redis/repo/StudentRepositoryImpl.java
    @Test
    public void testRedis() {
        final RedisTemplate<String, Long> redisTemplate = new RedisTemplate<String, Long>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setValueSerializer(new GenericToStringSerializer<Long>(Long.class));

        List<Long> range = LongStream.range(0, 500).boxed().collect(Collectors.toList());
        redisTemplate.opsForList().rightPushAll("counters", range);
        redisTemplate.opsForSet().add("counters2", range.toArray(new Long[range.size()]));

        Object counters = redisTemplate.opsForList().leftPop("counters");
        assertNotNull(counters);
        Object counters2 = redisTemplate.opsForSet().pop("counters2");
        assertNotNull(counters2);
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
