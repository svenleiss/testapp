package com.mhp.insideApp;

import org.h2.server.web.WebServlet;
import org.h2.tools.Server;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class WebConfiguration {

    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console*//*");
        registrationBean.addInitParameter("webAllowOthers", "true");

        try {
            Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8089").start();
            Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9099").start();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registrationBean;

    }
}
