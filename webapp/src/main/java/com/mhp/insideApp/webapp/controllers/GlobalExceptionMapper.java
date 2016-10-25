package com.mhp.insideApp.webapp.controllers;

import com.mhp.insideApp.common.exceptions.DomainObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.mhp.insideApp.webapp.controllers.ApplicationController.INDEX_PAGE;

@ControllerAdvice
public class GlobalExceptionMapper {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DomainObjectNotFoundException.class)
    public String handleDomainObjectNotFoundException() {
        return INDEX_PAGE;
    }
}
