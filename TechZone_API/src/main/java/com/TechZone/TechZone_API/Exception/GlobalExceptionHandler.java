package com.TechZone.TechZone_API.Exception;

import com.TechZone.TechZone_API.Common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Map<String, String>> handlerValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errorrs = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(errorr -> {
            errorrs.put(errorr.getField(), errorr.getDefaultMessage());
        });

        return ApiResponse.errorrs(999, "list of errorr column", errorrs);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handlerRuntimeException(RuntimeException ex) {
        ApiResponse<Object> apiResponse = ApiResponse.errorrs(9999, ex.getMessage(), null);
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
