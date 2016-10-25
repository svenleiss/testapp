package com.mhp.insideApp.webapp.controllers;

import com.mhp.insideApp.insideApplications.Greetings;
import com.mhp.insideApp.insideApplications.usecases.CreateGreeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@RestController
@RequestMapping("/api/greetings")
public class GreetingController {

    @Autowired
    private CreateGreeting createGreeting;

//    @Autowired
//    private SendOrder sendOrder;
//
//    @Autowired
//    private Publisher publisher;
//
//    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)"
//            + "*(\\.[A-Za-z]{2,})$";


    @RequestMapping(method = POST, value= "/greeting")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity create(@RequestBody Greetings createdGreeting) throws IOException {
        try {
            return ResponseEntity.ok().body(createGreeting.run(createdGreeting));
        } catch (Exception exp) {
            log.error("Error with creating Greeting"):
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp);
        }
    }

//    @RequestMapping(method = GET, value = "/sendEmail")
//    public
//    @ResponseBody
//    ResponseEntity sendEmail(@RequestParam("customerEmail") String customerEmail,
//                             @RequestParam("id") String id,
//                             @RequestParam("serviceAdvisor") String serviceAdvisor) {
//        log.info("Start sendEmail");
//        if (validateEmail(customerEmail)) {
//            try {
//                sendOrder.run(Long.valueOf(id), Order.Status.WAITING, customerEmail, serviceAdvisor);
//            } catch (Exception exp) {
//                log.error("Error while sending the email", exp);
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            }
//            log.info("End sendEmail");
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }

//    private boolean validateEmail(String custMail) {
//        try {
//            return custMail.matches(EMAIL_REGEX);
//        } catch (Exception exp) {
//            log.error("Invalid email Id!", exp);
//        }
//        return false;
//    }
//
//
//
//    private void publishOrderUpdate(Order updatedOrder) {
//        try {
//        publisher.postMessage(getOrder.run(updatedOrder.getContract()), updatedOrder.getContract());
//         } catch (Exception ex) {
//            // Check if exception can ever come and if yes, do nothing on exception
//            log.error("Unable to publish changes", ex);
//        }
//    }
}
