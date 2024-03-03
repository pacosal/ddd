package com.pacosal.bank.infraestructure.api.error;

import com.pacosal.bank.infraestructure.api.payload.ErrorResponse;
import com.pacosal.bank.infraestructure.error.ErrorCode;
import com.pacosal.bank.infraestructure.error.ErrorResultException;
import com.fasterxml.jackson.core.JsonParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RequiredArgsConstructor
@RestControllerAdvice
public class ErrorResponseControllerAdvice {

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleConstraintViolationException(final ConstraintViolationException ex) {
    return ResponseEntity.status(BAD_REQUEST)
      .body(ErrorResponse.builder()
        .code(ErrorCode.INVALID_REQUEST.name())
        .type(BAD_REQUEST.name())
        .message(ex.getMessage())
        .status(ErrorCode.INVALID_REQUEST.getStatus().value())
        .build()
      );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
    return ResponseEntity.status(BAD_REQUEST)
      .body(ErrorResponse.builder()
        .code(ErrorCode.INVALID_REQUEST.name())
        .type(BAD_REQUEST.name())
        .message(ex.getMessage())
        .status(ErrorCode.INVALID_REQUEST.getStatus().value())
        .build()
      );
  }

  @ExceptionHandler(ErrorResultException.class)
  public ResponseEntity<ErrorResponse> handleErrorResultException(final ErrorResultException ex) {
    return ResponseEntity.status(ex.getStatus())
      .body(ErrorResponse.builder()
        .code(ex.getCode())
        .type(ex.getType())
        .message(ex.getMessage())
        .status(ex.getStatus())
        .build()
      );
  }

  @ExceptionHandler(JsonParseException.class)
  public ResponseEntity<ErrorResponse> handleJsonParseExceptionException(final JsonParseException ex) {
    return ResponseEntity.status(BAD_REQUEST)
      .body(ErrorResponse.builder()
        .code(ErrorCode.INVALID_REQUEST.name())
        .type(BAD_REQUEST.name())
        .message(ex.getMessage())
        .status(ErrorCode.INVALID_REQUEST.getStatus().value())
        .build()
      );
  }

}
