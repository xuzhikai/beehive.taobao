package com.beehive.erp.taobao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 */
public class BaseController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseController.class);

    private ObjectMapper objectMapper=new ObjectMapper();

    @ExceptionHandler
    @ResponseBody
    public void handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        logger.error("request url:" + request.getRequestURI());
        logger.error("system error-request url:" + request.getRequestURI() + ":", ex);

        try {
            response.getOutputStream().write(ex.getMessage().getBytes());
        } catch (Exception exp) {
            logger.error("json format error:", exp);
        }

    }
}
