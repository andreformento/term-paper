//package com.formento.realtimeticket.ticketreservation.exception;
//
//import com.google.common.collect.ImmutableMap;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ControllerAdvice
//public class TicketReservationExceptionHandler {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(TicketReservationExceptionHandler.class);
//
//    @ExceptionHandler(TicketReservationFullException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResult ticketReservationFullException(TicketReservationFullException e) {
//        LOGGER.error("Bad request: {}", e);
//        return new ErrorResult(ImmutableMap.<String, String>builder().put("application error", e.getMessage()).build());
//    }
//
//    @ExceptionHandler(RepositoryNotFoundException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorResult ticketReservationNotFoundException(RepositoryNotFoundException e) {
//        LOGGER.error("Not found by id: {}", e);
//        return new ErrorResult(ImmutableMap.<String, String>builder().put("application error", e.getMessage()).build());
//    }
//
//}
