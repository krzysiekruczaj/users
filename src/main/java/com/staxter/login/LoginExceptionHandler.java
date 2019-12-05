package com.staxter.login;

import com.staxter.web.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class LoginExceptionHandler {

    @ExceptionHandler({IncorrectCredentialsException.class})
    ResponseEntity<ErrorDto> handleIncorrectCredentialsException() {
        ErrorCode errorCode = ErrorCode.INCORRECT_CREDENTIALS;
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorDto(errorCode.name(), errorCode.message()));
    }
}
