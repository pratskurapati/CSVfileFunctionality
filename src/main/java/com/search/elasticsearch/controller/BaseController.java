package com.search.elasticsearch.controller;

import com.search.elasticsearch.model.ErrorResponse;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BaseController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    public BaseController() {
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception exe){
        this.logErrors("handleException", exe);
        return new ResponseEntity(new ErrorResponse("E001","handleException"), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleNotReadableException(Exception exe){
        this.logErrors("handleNotReadableException", exe);
        return new ResponseEntity(new ErrorResponse("E002","handleNotReadableException"), HttpStatus.BAD_REQUEST);
    }


    private void logErrors(String method, Exception exe) {
        if(exe != null){
            String stackTrace = this.getStackTrace(exe);
            LOG.error("BaseController, {0} method, error Occurred: {1}::{2}, Stacktrace: {3}", exe, new Object[]{method, exe.getClass().getSimpleName(), exe.getMessage(), stackTrace});

        }
    }

    private String getStackTrace(Exception exe) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        exe.printStackTrace(new PrintStream(os));
        return new String(os.toByteArray());
    }
}
