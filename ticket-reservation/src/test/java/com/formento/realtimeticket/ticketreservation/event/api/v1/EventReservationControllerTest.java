package com.formento.realtimeticket.ticketreservation.event.api.v1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
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

    private static final String KEY_EVENT = "e-uuidEvent";

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
    @Ignore
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
    public void testRedis2() {
        final RedisTemplate<String, Long> redisTemplate = new RedisTemplate<String, Long>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setValueSerializer(new GenericToStringSerializer<Long>(Long.class));
        redisTemplate.afterPropertiesSet();

        redisTemplate.opsForValue().set("mykey", 15l);
        Long mykey = redisTemplate.opsForValue().get("mykey");
        assertEquals(Long.valueOf(15l), mykey);

        Long totalAfterIncrement = redisTemplate.opsForValue().increment("mykey", 10l);
        assertEquals(Long.valueOf(25l), totalAfterIncrement);
    }

    @Test
    public void testRedisEvents() {
        // https://redis.io/commands/sadd
        // https://redis.io/commands/spop

        final RedisTemplate<String, Integer> redisTemplateInteger = new RedisTemplate<String, Integer>();
        redisTemplateInteger.setConnectionFactory(jedisConnectionFactory);
        redisTemplateInteger.setValueSerializer(new GenericToStringSerializer<Integer>(Integer.class));
        redisTemplateInteger.afterPropertiesSet();

        String key = "events";
        String hash = "event123";

        List<Integer> range = IntStream.range(0, 500).boxed().collect(Collectors.toList());
        final Integer[] array = range.toArray(new Integer[range.size()]);
        final Long result = redisTemplateInteger.opsForSet().add(key, array);
        assertEquals(Long.valueOf(500L), result);
        final Set<Integer> members = redisTemplateInteger.opsForSet().members(key);
        assertEquals(500, members.size());

        redisTemplateInteger.opsForSet().pop(key);
        final Set<Integer> membersAfterPop = redisTemplateInteger.opsForSet().members(key);
        assertEquals(499, membersAfterPop.size());
    }

    @Test
    public void shouldRegisterEvent() {
        final RedisTemplate<String, Integer> redisTemplateInteger = new RedisTemplate<String, Integer>();
        redisTemplateInteger.setConnectionFactory(jedisConnectionFactory);
        redisTemplateInteger.setValueSerializer(new GenericToStringSerializer<Integer>(Integer.class));
        redisTemplateInteger.afterPropertiesSet();

        redisTemplateInteger.opsForSet().getOperations().delete(KEY_EVENT);

        final List<Integer> range = IntStream.range(1, 6).boxed().collect(Collectors.toList());

        final Integer[] array = range.toArray(new Integer[range.size()]);

        final Long result = redisTemplateInteger.opsForSet().add(KEY_EVENT, array);
        assertEquals(Long.valueOf(5L), result);
        final Set<Integer> members = redisTemplateInteger.opsForSet().members(KEY_EVENT);
        assertEquals(5, members.size());
    }

    @Test
    public void shouldBooking1Ticket() {
        shouldRegisterEvent();

        final RedisTemplate<String, Integer> redisTemplateInteger = new RedisTemplate<String, Integer>();
        redisTemplateInteger.setConnectionFactory(jedisConnectionFactory);
        redisTemplateInteger.setValueSerializer(new GenericToStringSerializer<Integer>(Integer.class));
        redisTemplateInteger.afterPropertiesSet();

        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
        final Set<Integer> membersAfterPop = redisTemplateInteger.opsForSet().members(KEY_EVENT);
        assertEquals(4, membersAfterPop.size());
    }

    @Test
    public void shouldBooking4Ticket() {
        shouldRegisterEvent();

        final RedisTemplate<String, Integer> redisTemplateInteger = new RedisTemplate<String, Integer>();
        redisTemplateInteger.setConnectionFactory(jedisConnectionFactory);
        redisTemplateInteger.setValueSerializer(new GenericToStringSerializer<Integer>(Integer.class));
        redisTemplateInteger.afterPropertiesSet();

        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);

        final Set<Integer> membersAfterPop = redisTemplateInteger.opsForSet().members(KEY_EVENT);
        assertEquals(1, membersAfterPop.size());
    }

    @Test
    public void shouldBookingAllTickets() {
        shouldRegisterEvent();

        final RedisTemplate<String, Integer> redisTemplateInteger = new RedisTemplate<String, Integer>();
        redisTemplateInteger.setConnectionFactory(jedisConnectionFactory);
        redisTemplateInteger.setValueSerializer(new GenericToStringSerializer<Integer>(Integer.class));
        redisTemplateInteger.afterPropertiesSet();

        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isNull();
        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isNull();

        final Set<Integer> membersAfterPop = redisTemplateInteger.opsForSet().members(KEY_EVENT);
        assertEquals(0, membersAfterPop.size());
    }

    @Test
    public void shouldFluxAllBooking() throws InterruptedException {
        shouldRegisterEvent();

        final RedisTemplate<String, Integer> redisTemplateInteger = new RedisTemplate<String, Integer>();
        redisTemplateInteger.setConnectionFactory(jedisConnectionFactory);
        redisTemplateInteger.setValueSerializer(new GenericToStringSerializer<Integer>(Integer.class));
        redisTemplateInteger.afterPropertiesSet();

        final RedisTemplate<String, String> redisTemplateString = new RedisTemplate<String, String>();
        redisTemplateString.setConnectionFactory(jedisConnectionFactory);
        redisTemplateString.setValueSerializer(new GenericToStringSerializer<String>(String.class));
        redisTemplateString.afterPropertiesSet();

        final Integer count = 3;
        final String userId = "userId1";

        final Long size = redisTemplateInteger.opsForSet().size(KEY_EVENT);

        for (int i = 1; i < count; i++) {
            assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
            assertThat(redisTemplateInteger.opsForSet().size(KEY_EVENT)).isEqualTo(size - i);
            final String key = KEY_EVENT + "-" + i;
            redisTemplateString.opsForValue().set(key, userId);
            redisTemplateString.expire(key, 500, TimeUnit.MILLISECONDS);
            assertThat(redisTemplateString.hasKey(key)).isTrue();
        }

        Thread.sleep(600);
        for (int i = 1; i < count; i++) {
            final String key = KEY_EVENT + "-" + i;
            assertThat(redisTemplateString.hasKey(key)).isFalse();
        }
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
