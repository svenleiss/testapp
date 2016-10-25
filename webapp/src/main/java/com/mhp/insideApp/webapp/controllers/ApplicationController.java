package com.mhp.insideApp.webapp.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ApplicationController {
    public static final String INDEX_PAGE = "index";

    @RequestMapping({
            "/",
            "/inside_app_dashboard/*",
            "/greeting"
    })

    public String applicationPages() {
        log.info("return applicationPages");
        return INDEX_PAGE;
    }

}
