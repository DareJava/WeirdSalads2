package com.code.weirdsalads.controller.handler;

import com.code.weirdsalads.exception.MenuUnvailableException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  
   //other exception handlers
  
   @ExceptionHandler(MenuUnvailableException.class)
   protected ResponseEntity<Object> handleEntityNotFound(
           MenuUnvailableException ex) {
       return ResponseEntity.status(500).body(new ApiError(ex.getMessage()));
   }
}

record ApiError(String message){};