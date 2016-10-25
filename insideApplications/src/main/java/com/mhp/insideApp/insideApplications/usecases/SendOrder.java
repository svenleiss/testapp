//package com.mhp.insideApp.insideApplications.usecases;
//
//
//import com.mhp.insideApp.insideApplications.Order;
//import com.mhp.insideApp.insideApplications.sources.OrderService;
//import com.mhp.insideApp.insideApplications.utils.SendMailHelperBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Component
//public class SendOrder {
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private SendMailHelperBean sendMailHelperBean;
//
//    @Autowired
//    private OrderItemService orderItemService;
//
//    //[TODO] //update order item status on send // ORDER_ITEM : SENT (ORDER: WAITING)
//    // make sure this is covered by a test
//
//    @Transactional
//    public boolean run(Long id, Order.Status status, String customerEmailId,
//                       String serviceAdvisor) {
//        try {
//            Order order = orderService.update(id, status);
//            updateOrderItemStatusToSent(order.getOrderItems());
//            sendMailHelperBean.sendEmail(customerEmailId, order.getLicensePlate(),
//                    String.valueOf(order.getId()), serviceAdvisor);
//            return true;
//        } catch (Exception ex) {
//            throw new RuntimeException("Unable to send email and update OrderExtensionEntity!", ex);
//        }
//    }
//
//    public void updateOrderItemStatusToSent(List<OrderItem> orderItems) {
//        orderItems.forEach(orderItem -> {
//            if (orderItem.getStatus().equals(OrderItem.Status.VIEWED_BY_SERVICE_ADVISOR)) {
//                orderItemService.updateStatus(
//                        orderItem.getId(),
//                        OrderItem.Status.SENT);
//            }
//
//        });
//    }
//}
