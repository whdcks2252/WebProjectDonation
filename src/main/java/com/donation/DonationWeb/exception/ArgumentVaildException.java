package com.donation.DonationWeb.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestControllerAdvice
public class ArgumentVaildException {
    //검증 오류처리
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

    //DB에서 조회후 값이 없을시 에러
    //회원가입 실패 후 오류처리401
    @ExceptionHandler(LoginException.class)
    public Object loginException(LoginException e){
        Map<String, Object> errormessage = new HashMap<>();
        errormessage.put("message", "로그인 인증에 실패 하였습니다");

        return new ResponseEntity<>(errormessage,HttpStatus.BAD_REQUEST);
    }


    //DB에서 조회후 값이 없을시 에러
    //회원가입 실패 후 오류처리401
    @ExceptionHandler(UserException.class)
    public Object userException(UserException e){
        Map<String, Object> errormessage = new HashMap<>();
        errormessage.put("message", e.getMessage());

        return errormessage;
    }
    //댓글 예외처리
    @ExceptionHandler(CommentException.class)
    public Object commentException(CommentException e){
        Map<String, Object> errormessage = new HashMap<>();
        errormessage.put("message", e.getMessage());

        return errormessage;
    }

    //포스트예외처리
    @ExceptionHandler(PostException.class)
    public Object postException(PostException e){
        Map<String, Object> errormessage = new HashMap<>();
        errormessage.put("message", e.getMessage());

        return errormessage;
    }

    //KaKaoPay예외처리
    @ExceptionHandler(KakaoPayException.class)
    public Object postException(KakaoPayException e){
        Map<String, Object> errormessage = new HashMap<>();
        errormessage.put("message", e.getMessage());

        return errormessage;
    }
    //PaymentReadyException 예외처리
    @ExceptionHandler(PaymentReadyException.class)
    public Object postException(PaymentReadyException e){
        Map<String, Object> errormessage = new HashMap<>();
        errormessage.put("message", e.getMessage());

        return errormessage;
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    //잘못된 요청으로 JSON 파싱이 안될때 오류 처리
    //필수 요청 변수가 없거나 요청 변수 이름이 잘못된 경우
    public Object userException(HttpMessageNotReadableException e){
        Map<String, Object> errormessage = new HashMap<>();
        errormessage.put("message","잘못된 요청입니다");
        return new ResponseEntity<>(errormessage,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NumberFormatException.class)
    //인터셉터를 스트링으로 처리하기때문에 NumberFormatException 발생함 이를 404오류로 처리
    public Object userException(NumberFormatException e){
        Map<String, Object> errormessage = new HashMap<>();
        errormessage.put("message","잘못된 요청입니다");
        return new ResponseEntity<>(errormessage,HttpStatus.BAD_REQUEST);
    }

}
