package com.example.controller_advice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

// 코드 작성 (@ControllerAdvice)
public class GlobalExceptionHandler {

  // NullPointerException, IllegalArgumentException 예외만 처리
  @ExceptionHandler(
    { NullPointerException.class, IllegalArgumentException.class }
    )
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map<String, Object> handleCommonExceptions(Exception e, WebRequest request) {
    Map<String, Object> error = new HashMap<>();

    // 코드 작성
    // 예외 메시지 e.getMessage()
    error.put("message",e.getMessage());
    error.put("status","fail");
    return error;
  }
}
