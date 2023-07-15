package com.donation.DonationWeb.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestControllerAdvice
public class ArgumentVaildException {
   @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
       Map<String, Object> errormessage = new HashMap<>();

       e.getBindingResult().getAllErrors()
               .forEach((error) -> {
                   String fieldName = ((FieldError) error).getField();
                   String errorMessage = error.getDefaultMessage();
                   errormessage.put(fieldName, errorMessage);
               });


       return errormessage;
    }
    @ExceptionHandler(UserException.class)
    public Object userException(UserException e){
        Map<String, Object> errormessage = new HashMap<>();
        errormessage.put("message", e.getMessage());

        return errormessage;
    }

}
