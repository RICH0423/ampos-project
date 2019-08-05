package com.rich.ampos.order.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String id) {
        super("Could not find entity: " + id);
    }
}
