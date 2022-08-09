package com.example.Logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class HttpLoggingFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(HttpLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);

        String requestBody = getStringValue(contentCachingRequestWrapper.getContentAsByteArray(), contentCachingRequestWrapper.getCharacterEncoding());
        String responseBody = getStringValue(contentCachingResponseWrapper.getContentAsByteArray(), contentCachingResponseWrapper.getCharacterEncoding());
        filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);

        LOGGER.info("\nHTTP Filter Logs :\n METHOD = {}\n REQUEST URI= {}\n CALL TIME = {}\n " +
                "REQUEST BODY = {}\n RESPONSE CODE = {}\n RESPONSEBODY = {}\n",
                request.getMethod(), request.getRequestURI(),
                ZonedDateTime.now(ZoneId.of("Asia/Tehran")).format(DateTimeFormatter.ISO_DATE_TIME),
                requestBody, response.getStatus(), responseBody);

        contentCachingResponseWrapper.copyBodyToResponse();
    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {

        try {
            return new String(contentAsByteArray, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}