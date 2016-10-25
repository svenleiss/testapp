//package com.mhp.insideApp.webapp.support.supportImpl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mhp.insideApp.insideApplications.Order;
//import com.mhp.insideApp.webapp.support.Observer;
//import com.mhp.insideApp.webapp.support.Subject;
//import lombok.AllArgsConstructor;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.TextMessage;
//import java.io.IOException;
//import java.util.List;
//
//@Slf4j
//@Component
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode(of = "observerPayLoad")
//public class ObserverImpl implements Observer {
//
//    private ObserverPayLoad observerPayLoad;
//
//    @Override
//    public void sendUpdate() {
//        log.info(":: sendUpdate");
//
//        List<Order> orders = (List) observerPayLoad.getTopic().getUpdate(this);
//
//        if (orders == null) {
//            log.info(":: No new message");
//        } else {
//            log.info(":: Consuming message:: " + orders);
//            try {
//                if (observerPayLoad.getWebSocketSession().isOpen()) {
//                    ObjectMapper mapper = new ObjectMapper();
//                    String jsonInString = mapper.writeValueAsString(orders);
//                    observerPayLoad.getWebSocketSession().sendMessage(new TextMessage(jsonInString));
//                }
//            } catch (IOException ex) {
//                //Check what should we do if publish to client fails
//                log.error("Unable to Send Messages to Client", ex);
//
//            }
//        }
//    }
//
//
//    @Override
//    public void setSubject(Subject sub) {
//      this.observerPayLoad.setTopic(sub);
//    }
//
//    @Override
//    public ObserverPayLoad getObserverPayLoad() {
//        return this.observerPayLoad;
//    }
//}
