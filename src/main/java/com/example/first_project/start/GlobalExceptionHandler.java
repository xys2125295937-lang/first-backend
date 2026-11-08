package com.example.first_project.start;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handle(
            ResponseStatusException exception) {

        String message = exception.getReason();
        if (message == null) {
            message = "请求处理失败";
        }

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(Map.of(
                        "code", exception.getStatusCode().value(),
                        "message", message
                ));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatch(
            MethodArgumentTypeMismatchException exception) {

        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "code", 400,
                        "message", "参数 " + exception.getName() + " 类型不正确"
                ));
    }
}
