package com.staxter.register;

import com.staxter.userrepository.UserAlreadyExistsException;
import com.staxter.web.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class RegisterExceptionHandler {

    @ExceptionHandler({UserAlreadyExistsException.class})
    ResponseEntity<ErrorDto> handleUserAlreadyExistsException() {
        ErrorCode errorCode = ErrorCode.USER_ALREADY_EXISTS;
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorDto(errorCode.name(), errorCode.message()));
    }
}
