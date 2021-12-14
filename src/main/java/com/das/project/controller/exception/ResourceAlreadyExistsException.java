package com.das.project.controller.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String detailMessage) {
        super(detailMessage);
    }

}
