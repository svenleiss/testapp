package com.mhp.insideApp.webapp.filters;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

public class RequestResponseLogger extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        Date startTime = new Date();
        logIncomingRequest(request);

        filterChain.doFilter(request, response);

        Date endTime = new Date();
        logRequestFinished(response, getDuration(startTime, endTime));
    }

    private void logIncomingRequest(HttpServletRequest request) {
        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectNode requestLog = factory.objectNode();

        requestLog.put("method", request.getMethod());
        requestLog.put("path", request.getRequestURI());
        requestLog.set("parameters", sanitizeParameters(request));

        if (logger.isDebugEnabled()) {
            requestLog.set("headers", sanitizeHeaders(request));
        }

        logger.info(requestLog);
    }

    private void logRequestFinished(HttpServletResponse response, Long duration) {
        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectNode finishedRequestLog = factory.objectNode();

        finishedRequestLog.put("status", String.valueOf(response.getStatus()));
        finishedRequestLog.put("duration", String.valueOf(duration) + "ms");

        logger.info("Finished processing request: " + finishedRequestLog);
    }

    private Long getDuration(Date start, Date end) {
        return end.getTime() - start.getTime();
    }

    private ObjectNode sanitizeHeaders(HttpServletRequest request) {
        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectNode newMap = factory.objectNode();

        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            newMap.put(headerName, request.getHeader(headerName));
        }

        return newMap;
    }

    private ObjectNode sanitizeParameters(HttpServletRequest request) {
        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectNode jsonNode = factory.objectNode();

        request.getParameterMap().forEach((key, values) -> {
            StringBuilder builder = new StringBuilder();

            for (String value : values) {
                builder.append(value);
            }

            jsonNode.put(key, builder.toString());
        });
        return jsonNode;
    }
}
