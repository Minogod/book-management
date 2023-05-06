package server.bookmanagement.global.error.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "해당 ID의 회원을 찾을 수 없습니다."),
    BOOK_NOT_FOUND(404,"해당 ID의 책을 찾을 수 없습니다."),
    LIBRARY_NOT_FOUND(404,"해당 ID의 도서관을 찾을 수 없습니다."),
    LIBRARY_INVENTORY_NOT_FOUND(404,"해당 ID 도서재고를 찾을 수 없습니다.");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
