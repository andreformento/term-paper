package com.formento.realtimeticket.ticketreservation.exception;

public class RepositoryNotFoundException extends RuntimeException {

    public RepositoryNotFoundException(final String id) {
        super("Resource with id " + id + " not found");
    }

}
