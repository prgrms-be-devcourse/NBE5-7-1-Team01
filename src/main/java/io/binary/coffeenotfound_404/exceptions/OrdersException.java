package io.binary.coffeenotfound_404.exceptions;

public class OrdersException {
    // 이메일 유효성 검사 예외
    public static class InvalidEmailException extends RuntimeException {
        public InvalidEmailException() {
            super("이메일 형식이 올바르지 않습니다.");
        }
    }

    // 주소 유효성 검사 예외
    public static class InvalidAddressException extends RuntimeException {
        public InvalidAddressException() {
            super("주소는 필수 입력 값입니다.");
        }
    }

    // 우편번호 유효성 검사 예외
    public static class InvalidPostcodeException extends RuntimeException {
        public InvalidPostcodeException() {
            super("우편번호는 필수 입력 값입니다.");
        }
    }

    // 주문 아이템 유효성 검사 예외
    public static class InvalidOrderItemsException extends RuntimeException {
        public InvalidOrderItemsException() {
            super("주문 아이템이 1개 이상이어야 합니다.");
        }
    }

    // 존재하는 아이템에 대한 유효성 검사 예외
    public static class ItemsNotFoundException extends RuntimeException {
        public ItemsNotFoundException() {
            super("존재하지 않는 Item ID로 Item 조회를 시도했습니다.");
        }
    }
}