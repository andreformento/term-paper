//package com.formento.realtimeticket.ticketreservation.repository;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.annotation.DirtiesContext.ClassMode;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
//public class RedisConfigurationTest {
//
//    private static final String KEY_EVENT = "e-uuidEvent";
//
//    @Autowired
//    private RedisTemplateFactory redisTemplateFactory;
//
//    private RedisTemplate<String, String> redisTemplateString;
//    private RedisTemplate<String, Integer> redisTemplateInteger;
//    private RedisTemplate<String, Long> redisTemplateLong;
//
//    @Before
//    public void init() {
//        this.redisTemplateString = redisTemplateFactory.make(String.class);
//        this.redisTemplateInteger = redisTemplateFactory.make(Integer.class);
//        this.redisTemplateLong = redisTemplateFactory.make(Long.class);
//    }
//
//    // https://github.com/eugenp/tutorials/blob/master/persistence-modules/spring-data-redis/src/main/java/com/baeldung/spring/data/redis/repo/StudentRepositoryImpl.java
//    @Test
//    public void testRedis() {
//        redisTemplateLong.opsForValue().set("mykey", 15l);
//        Long mykey = redisTemplateLong.opsForValue().get("mykey");
//        assertEquals(Long.valueOf(15l), mykey);
//
//        Long totalAfterIncrement = redisTemplateLong.opsForValue().increment("mykey", 10l);
//        assertEquals(Long.valueOf(25l), totalAfterIncrement);
//    }
//
//    @Test
//    public void testRedisEvents() {
//        // https://redis.io/commands/sadd
//        // https://redis.io/commands/spop
//        String key = "events";
//
//        List<Integer> range = IntStream.range(0, 500).boxed().collect(Collectors.toList());
//        final Integer[] array = range.toArray(new Integer[range.size()]);
//        final Long result = redisTemplateInteger.opsForSet().add(key, array);
//        assertEquals(Long.valueOf(500L), result);
//        final Set<Integer> members = redisTemplateInteger.opsForSet().members(key);
//        assertEquals(500, members.size());
//
//        redisTemplateInteger.opsForSet().pop(key);
//        final Set<Integer> membersAfterPop = redisTemplateInteger.opsForSet().members(key);
//        assertEquals(499, membersAfterPop.size());
//    }
//
//    @Test
//    public void shouldRegisterEvent() {
//        redisTemplateInteger.opsForSet().getOperations().delete(KEY_EVENT);
//
//        final List<Integer> range = IntStream.range(1, 6).boxed().collect(Collectors.toList());
//
//        final Integer[] array = range.toArray(new Integer[range.size()]);
//
//        final Long result = redisTemplateInteger.opsForSet().add(KEY_EVENT, array);
//        assertEquals(Long.valueOf(5L), result);
//        final Set<Integer> members = redisTemplateInteger.opsForSet().members(KEY_EVENT);
//        assertEquals(5, members.size());
//    }
//
//    @Test
//    public void shouldBooking1Ticket() {
//        shouldRegisterEvent();
//
//        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
//        final Set<Integer> membersAfterPop = redisTemplateInteger.opsForSet().members(KEY_EVENT);
//        assertEquals(4, membersAfterPop.size());
//    }
//
//    @Test
//    public void shouldBooking4Ticket() {
//        shouldRegisterEvent();
//
//        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
//        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
//        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
//        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
//
//        final Set<Integer> membersAfterPop = redisTemplateInteger.opsForSet().members(KEY_EVENT);
//        assertEquals(1, membersAfterPop.size());
//    }
//
//    @Test
//    public void shouldBookingAllTickets() {
//        shouldRegisterEvent();
//
//        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
//        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
//        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
//        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
//        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
//        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isNull();
//        assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isNull();
//
//        final Set<Integer> membersAfterPop = redisTemplateInteger.opsForSet().members(KEY_EVENT);
//        assertEquals(0, membersAfterPop.size());
//    }
//
//    @Test
//    public void shouldFluxAllBooking() throws InterruptedException {
//        shouldRegisterEvent();
//
//        final Integer count = 3;
//        final String userId = "userId1";
//
//        final Long size = redisTemplateInteger.opsForSet().size(KEY_EVENT);
//
//        for (int i = 1; i < count; i++) {
//            assertThat(redisTemplateInteger.opsForSet().pop(KEY_EVENT)).isGreaterThan(0);
//            assertThat(redisTemplateInteger.opsForSet().size(KEY_EVENT)).isEqualTo(size - i);
//            final String key = KEY_EVENT + "-" + i;
//            redisTemplateString.opsForValue().set(key, userId);
//            redisTemplateString.expire(key, 500, TimeUnit.MILLISECONDS);
//            assertThat(redisTemplateString.hasKey(key)).isTrue();
//        }
//
//        Thread.sleep(600);
//        for (int i = 1; i < count; i++) {
//            final String key = KEY_EVENT + "-" + i;
//            assertThat(redisTemplateString.hasKey(key)).isFalse();
//        }
//    }
//
//}
