//package com.formento.realtimeticket.ticketreservation.reservation;
//
//import com.formento.realtimeticket.ticketreservation.event.EventReservationService;
//import com.formento.realtimeticket.ticketreservation.exception.TicketReservationFullException;
//import java.util.Set;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TicketReservationService {
//
//    private final TicketReservationRepository ticketReservationRepository;
//    private final EventReservationService eventReservationService;
//
//    @Autowired
//    public TicketReservationService(TicketReservationRepository ticketReservationRepository,
//        EventReservationService eventReservationService) {
//        this.ticketReservationRepository = ticketReservationRepository;
//        this.eventReservationService = eventReservationService;
//    }
//
//    public TicketReservation booking(final TicketReservation ticketReservation, final Integer count) {
//        final String eventId = ticketReservation.getIdEvent();
//
//        eventReservationService.getById(eventId);
//
//        final Set<String> reservationIds = eventReservationService.getReservationIdsFrom(eventId, count);
//
//        if (reservationIds.isEmpty()) {
//            throw new TicketReservationFullException(ticketReservation);
//        }
//
//        final TicketReservation result = new TicketReservation(ticketReservation, reservationIds);
//        ticketReservationRepository.saveReservation(result);
//
//        // colocar na fila para adicionar novamente
//
//        return result;
//    }
//
//}
