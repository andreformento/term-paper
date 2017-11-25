//package com.formento.realtimeticket.ticketreservation.event.api.v1;
//
//import com.formento.realtimeticket.ticketreservation.event.EventReservation;
//import java.io.Serializable;
//import javax.validation.constraints.NotNull;
//
//class EventReservationResponse implements Serializable {
//
//    private static final long serialVersionUID = -1535804038626605179L;
//
//    @NotNull
//    private final String eventId;
//    @NotNull
//    private final Long limit;
//
//    EventReservationResponse(EventReservation model) {
//        this.eventId = model.getEventId();
//        this.limit = model.getLimit();
//    }
//
//    public String getEventId() {
//        return eventId;
//    }
//
//    public Long getLimit() {
//        return limit;
//    }
//}
