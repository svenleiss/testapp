package com.mhp.insideApp;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.mhp.insideApp.webapp.filters.RequestResponseLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
public class WebappApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebappApplication.class, args);
    }

    @Bean
    @Order(1)
    public RequestResponseLogger requestResponseLogger() {
        return new RequestResponseLogger();
    }

//    @Bean
//    @Order(2)
//    @Profile("!gr-country-code")
//    public CountryCodeResolver countryCodeResolver() {
////        return new CountryCodeResolver();
////    }
//
//    @Bean
//    @Order(3)
//    @Profile("gr-country-code")
//    public CountryCodeResolver testCountryCodeResolver() {
//        return new CountryCodeResolver() {
//            @Override
//            protected void doFilterInternal(
//                    HttpServletRequest request,
//                    HttpServletResponse response,
//                    FilterChain filterChain
//            ) throws ServletException, IOException {
//
//                request.setAttribute(GlobalControllerAdvisor.COUNTRY_CODE, "de");
//
//                filterChain.doFilter(request, response);
//            }
//        };
//    }
//
//    @Bean
//    public AmazonSimpleEmailServiceClient getAmazonSimpleEmailServiceClient(BasicAWSCredentials basicAWSCredentials) {
//        return new AmazonSimpleEmailServiceClient(basicAWSCredentials);
//    }
//
//    @Bean
//    public BasicAWSCredentials getBasicAWSCredentials(@Value("${AWS_ACCESS_KEY_ID}") String awsKey,
//                                                      @Value("${AWS_SECRET_ACCESS_KEY}") String awsSecret) {
//        return new BasicAWSCredentials(awsKey, awsSecret);
//    }
//
}
