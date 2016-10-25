//package com.mhp.insideApp.webapp.config;
//
//import com.mhp.insideApp.webapp.controllers.WebSocketController;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//          registry.addHandler(webSocketController(), "/webSocketHandler").withSockJS();
//    }
//
//    @Bean
//    public WebSocketController webSocketController() {
//        return new WebSocketController();
//    }
//}
