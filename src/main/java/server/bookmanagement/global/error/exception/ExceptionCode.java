package server.bookmanagement.global.error.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "해당 ID의 회원을 찾을 수 없습니다."),
    BOOK_NOT_FOUND(404,"해당 ID의 책을 찾을 수 없습니다."),
    LIBRARY_NOT_FOUND(404,"해당 ID의 도서관을 찾을 수 없습니다."),
    LIBRARY_INVENTORY_NOT_FOUND(404,"해당 ID 도서재고를 찾을 수 없습니다."),
    LOAN_NOT_FOUND(404,"해당 ID의 대여를 찾을 수 없습니다"),
    EXCEEDS_MAXIMUM_QUANTITY(403,"책을 빌릴 권한이 없습니다. (최대 5권까지 대여가능)"),
    MEMBER_HAS_PENALTY(403,"해당 회원은 연체로 인해 대여 불가능 합니다."),
    LOAN_NOT_ALLOW(403,"도서관에 해당 책이 모두 대여중입니다.")
    ;

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
