//package com.mhp.insideApp.webapp.controllers;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mhp.insideApp.webapp.support.supportImpl.ObserverImpl;
//import com.mhp.insideApp.insideApplications.Order;
//import com.mhp.insideApp.webapp.support.Subject;
//import com.mhp.insideApp.webapp.support.supportImpl.ObserverPayLoad;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//
//@Slf4j
//public class WebSocketController  extends TextWebSocketHandler {
//
//    @Autowired
//    private Subject publisher;
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//
//        log.info("Message Received" + " - " + message.getPayload().toString());
//
//        ObjectMapper mapper = new ObjectMapper();
//        Order order = mapper.readValue(message.getPayload().toString(), Order.class);
//        ObserverPayLoad observerPayLoad = new ObserverPayLoad().withWebSocketSession(session)
//                                          .withTopic(publisher).withContract(order.getContract());
//        publisher.registerObserver(new ObserverImpl(observerPayLoad));
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        ObserverPayLoad observerPayLoad = new ObserverPayLoad().withWebSocketSession(session).withTopic(publisher);
//        log.info("Removing listener" + " - " + observerPayLoad);
//        publisher.removeObserver(new ObserverImpl(observerPayLoad));
//    }
//}
