package com.example.myproject.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class MyErrorController implements ErrorController {


    private static Logger logger = LoggerFactory.getLogger(MyErrorController.class);

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {

        String errorPage = "error"; // default

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        logger.info("error code : " + status);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // handle HTTP 404 Not Found error
                errorPage = "error-404";

            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                // handle HTTP 403 Forbidden error
                errorPage = "error-403";

            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // handle HTTP 500 Internal Server error
                errorPage = "error-500";
            } else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
                // handle HTTP 405 Internal Server error
                errorPage = "error-405";
            }
        }

        return errorPage;
    }
}
