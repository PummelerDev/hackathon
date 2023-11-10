package com.hackathon.backend.exception.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.hackathon.backend.domain.response.ErrorResponse;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler
  @ResponseStatus(value = BAD_REQUEST)
  public ErrorResponse handleIllegalArgumentException(
      final IllegalArgumentException ex, final WebRequest request) {
    return new ErrorResponse(BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false));
  }
}
