package io.binary.coffeenotfound_404.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 전역 예외처리
// @RestControllerAdvice :@RestControllerAdvice 모든 @Controller에 대해 전역적으로 예외를 처리할 수 있게 해줌
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrdersException.InvalidEmailException.class)
    public ResponseEntity<String> handleInvalidEmailException(OrdersException.InvalidEmailException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(OrdersException.InvalidAddressException.class)
    public ResponseEntity<String> handleInvalidAddressException(OrdersException.InvalidAddressException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(OrdersException.InvalidPostcodeException.class)
    public ResponseEntity<String> handleInvalidPostcodeException(OrdersException.InvalidPostcodeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(OrdersException.InvalidOrderItemsException.class)
    public ResponseEntity<String> handleInvalidOrderItemsException(OrdersException.InvalidOrderItemsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // 그 외의 예외는 서버 오류로 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        ex.printStackTrace(); // 예외 상세 로그 출력
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
    }
}