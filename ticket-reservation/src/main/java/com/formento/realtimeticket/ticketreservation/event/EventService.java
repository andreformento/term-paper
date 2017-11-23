package com.formento.realtimeticket.ticketreservation.event;

import org.springframework.stereotype.Service;

@Service
public class EventService {

    public Event getById(final String eventId) {
        return new Event(eventId, 100L);
    }

}
