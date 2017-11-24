package com.formento.realtimeticket.ticketreservation.event;

import com.formento.realtimeticket.ticketreservation.exception.RepositoryNotFoundException;
import com.formento.realtimeticket.ticketreservation.repository.RedisTemplateFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import java.util.Set;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.stereotype.Repository;

@Repository
class EventReservationRepository {

    private final RedisTemplate<String, String> redisTemplateString;
    private final RedisTemplate<String, Long> redisTemplateLong;

    public EventReservationRepository(RedisTemplateFactory redisTemplateFactory) {
        this.redisTemplateString = redisTemplateFactory.make(String.class);
        this.redisTemplateLong = redisTemplateFactory.make(Long.class);
    }

    public void deleteReservations(final String eventId) {
        redisTemplateString.opsForSet().getOperations().delete(eventId);
    }

    public Long createReservations(final EventReservation eventReservation) {
        final String eventId = eventReservation.getEventId();

        final Set<String> sequence = eventReservation.getSequence();
        final String[] array = sequence.toArray(new String[sequence.size()]);

        redisTemplateString.opsForSet().add(eventId, array);
        return redisTemplateString.opsForSet().size(eventId);
    }

    public EventReservation save(final EventReservation eventReservation) {
        redisTemplateLong.opsForHash().put("events", eventReservation.getEventId(), eventReservation.getLimit());
        return eventReservation;
    }

    public EventReservation getById(final String eventId) {
        final Long limit = (Long) redisTemplateLong.opsForHash().get("events", eventId);
        if (limit == null) {
            throw new RepositoryNotFoundException(eventId);
        }
        return new EventReservation(eventId, limit);
    }

    public Set<String> getReservation(String eventId, Integer count) {
        final Builder<String> ticketIds = ImmutableSet.builder();
        for (int i = 0; i < count; i ++) {
            final String ticketId = redisTemplateString.opsForSet().pop(eventId);
            if (ticketId == null) break;
            ticketIds.add(ticketId);
        }

        return ticketIds.build();
    }

}
