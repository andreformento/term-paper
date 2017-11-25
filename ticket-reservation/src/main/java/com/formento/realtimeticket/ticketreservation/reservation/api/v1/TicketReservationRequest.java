//package com.formento.realtimeticket.ticketreservation.reservation.api.v1;
//
//import com.formento.realtimeticket.ticketreservation.reservation.TicketReservation;
//import java.beans.ConstructorProperties;
//import java.io.Serializable;
//import javax.validation.constraints.NotNull;
//
//class TicketReservationRequest implements Serializable {
//
//    private static final long serialVersionUID = -2019568277161677204L;
//
//    @NotNull
//    private final String idUser;
//    @NotNull
//    private final Integer count;
//
//    @ConstructorProperties({"idUser", "count"})
//    TicketReservationRequest(String idUser, Integer count) {
//        this.idUser = idUser;
//        this.count = count;
//    }
//
//    public String getIdUser() {
//        return idUser;
//    }
//
//    public Integer getCount() {
//        return count;
//    }
//
//    TicketReservation toModel(final String idEvent) {
//        return new TicketReservation(idEvent, idUser);
//    }
//
//}
