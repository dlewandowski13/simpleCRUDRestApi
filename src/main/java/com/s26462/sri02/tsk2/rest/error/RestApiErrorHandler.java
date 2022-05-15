package com.s26462.sri02.tsk2.rest.error;

import com.s26462.sri02.tsk2.dto.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestApiErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers,
                                 HttpStatus status, WebRequest request) {
        HttpStatus responseStatus = HttpStatus.BAD_REQUEST;

        Map<String, List<String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(FieldError::getField,
                        Collectors.mapping(f -> f.getDefaultMessage(),Collectors.toList())));

        ErrorMessage errorMessage = ErrorMessage.builder()
                .httpStatus(responseStatus)
                .errors(errors)
                .build();
        return new ResponseEntity<>(errorMessage,responseStatus);
    }

}
