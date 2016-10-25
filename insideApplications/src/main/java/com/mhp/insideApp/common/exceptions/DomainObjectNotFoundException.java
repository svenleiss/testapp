package com.mhp.insideApp.common.exceptions;

public class DomainObjectNotFoundException extends RuntimeException {

    public DomainObjectNotFoundException(Class<?> domainClass, Long id) {
        super(String.format("%s with id=%s does not exist", domainClass.getSimpleName(), id));
    }

    public DomainObjectNotFoundException(String message) {
        super(message);
    }
}
