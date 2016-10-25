//package com.mhp.insideApp.webapp.support.supportImpl;
//
//import com.mhp.insideApp.webapp.support.Observer;
//import com.mhp.insideApp.insideApplications.Order;
//import com.mhp.insideApp.webapp.support.Subject;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@Slf4j
//public class Publisher implements Subject {
//
//    private List<Observer> observers;
//    private boolean changed;
//    private List<Order> ordersToSend;
//    private String contract;
//    private final Object lock = new Object();
//
//    public Publisher() {
//        this.observers = new ArrayList<>();
//    }
//
//    @Override
//    public void registerObserver(Observer obj) {
//        log.info("registerObserver");
//        if (obj == null) {
//            log.error("Null Observer Received");
//            throw new NullPointerException("Null Observer");
//        }
//        synchronized (lock) {
//            if (!observers.contains(obj)) {
//                observers.add(obj);
//            }
//        }
//    }
//
//    @Override
//    public void removeObserver(Observer obj) {
//        log.info("removeObserver");
//        synchronized (lock) {
//            observers.remove(obj);
//        }
//    }
//
//    @Override
//    public void notifyObservers() {
//        log.info("notifyObservers");
//        List<Observer> observersLocal = null;
//        synchronized (lock) {
//            if (!changed) {
//                return;
//            }
//            observersLocal = new ArrayList<>(this.observers);
//            this.changed = false;
//        }
//        for (Observer obj : observersLocal) {
//            if (this.contract.equals(obj.getObserverPayLoad().getContract())) {
//                obj.sendUpdate();
//            }
//        }
//    }
//
//    public void postMessage(List<Order> order, String contract) {
//        log.info("postMessage");
//        this.ordersToSend = order;
//        this.contract = contract;
//        this.changed = true;
//        notifyObservers();
//    }
//
//    @Override
//    public Object getUpdate(Observer obj) {
//        return this.ordersToSend;
//    }
//
//}
