package com.mhp.insideApp.insideApplications.utils;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class SendMailHelperBean {

    private static final String DEALER_NAME = "{dealership-name}";
    private static final String LINK_TO_OE = "{link-to-OE}";
    private static final String LICENSE_PLATE = "{license-plate}";
    private static final String TEMPLATE_PATH = "classpath:static/";
    private static final String TEMPLATE_NAME = "mail_template_en.html";
    private static final String LINE_SEPARATOR = "line.separator";

    @Value("${email.awsRegion}")
    private String region;

    @Value("${email.from}")
    private String from;

    @Value("${email.subject}")
    private String subject;

    @Value("${email.hostName}")
    private String hostName;

    @Value("${email.portNumber}")
    private String portNumber;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private AmazonSimpleEmailServiceClient amazonSimpleEmailServiceClient;

    public String sendEmail(String customerEmail, String licensePlate, String custOrderURL, String dealerName)
            throws Exception {
        return assembleAndSend(buildMailMessage(buildBody("http://" + hostName + ":" + portNumber + "/confirmation/"
                        +
                        custOrderURL, dealerName), licensePlate), customerEmail,
                this.from);
    }

    private String assembleAndSend(Message message, String custEmailTO, String from) throws Exception {
        log.debug("Cust Email Id:-" + custEmailTO + "  Verified Email Id is :- " + from);
        Destination destination = new Destination().withToAddresses(custEmailTO);
        // Assemble the email.
        SendEmailRequest
                request = new SendEmailRequest().withSource(from).
                withDestination(destination).withMessage(message);
        try {
            log.info("Attempting to send an email!");
            Region reg = Region.getRegion(Regions.fromName(region));
            amazonSimpleEmailServiceClient.setRegion(reg);
            // Send the email.
            SendEmailResult sendEmailResult = amazonSimpleEmailServiceClient.sendEmail(request);
            log.info("Email sent!");
            return sendEmailResult.getMessageId();
        } catch (Exception ex) {
            log.error("Error while sending the email", ex);
            throw new Exception(ex);
        }
    }

    private Message buildMailMessage(String body, String licensePlate) {
        Content subject = new Content().withData(this.subject.replace(LICENSE_PLATE, licensePlate));
        Content textBody = new Content().withData(body);

        Body msgBody = new Body().withHtml(textBody);
        return new Message().withSubject(subject).withBody(msgBody);
    }

    private String buildBody(String videoLink, String dealerName) throws Exception {
        //Set key values
        Map<String, String> input = new HashMap<>();
        input.put(LINK_TO_OE, videoLink);
        input.put(DEALER_NAME, dealerName);
        return readEmailFromHtml(input);
    }

    //Method to replace the values for keys
    protected String readEmailFromHtml(Map<String, String> input) throws Exception {
        String msg = readContentFromFile();
        try {
            Set<Map.Entry<String, String>> entries = input.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                msg = msg.replace(entry.getKey().trim(), entry.getValue().trim());
            }
        } catch (Exception ex) {
            log.error("Error while rendering email template", ex);
            throw new Exception(ex);
        }
        return msg;
    }

    //Method to read HTML file as a String
    private String readContentFromFile() throws IOException {
        StringBuilder contents = new StringBuilder();
        try {
            Resource resource = resourceLoader.getResource(TEMPLATE_PATH.
                    concat(TEMPLATE_NAME));

            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    contents.append(line);
                    contents.append(System.getProperty(LINE_SEPARATOR));
                }
            } finally {
                reader.close();
            }
        } catch (IOException ex) {
            log.error("Error while rendering email template", ex);
            throw new IOException(ex);
        }
        return contents.toString();
    }

}
