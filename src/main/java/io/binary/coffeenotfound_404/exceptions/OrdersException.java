package io.binary.coffeenotfound_404.exceptions;

public class OrdersException {
    // 이메일 유효성 검사 예외
    public static class InvalidEmailException extends RuntimeException {
        public InvalidEmailException(String message) {
            super(message);
        }
    }

    // 주소 유효성 검사 예외
    public static class InvalidAddressException extends RuntimeException {
        public InvalidAddressException(String message) {
            super(message);
        }
    }

    // 우편번호 유효성 검사 예외
    public static class InvalidPostcodeException extends RuntimeException {
        public InvalidPostcodeException(String message) {
            super(message);
        }
    }

    // 주문 아이템 유효성 검사 예외
    public static class InvalidOrderItemsException extends RuntimeException {
        public InvalidOrderItemsException(String message) {
            super(message);
        }
    }
}
