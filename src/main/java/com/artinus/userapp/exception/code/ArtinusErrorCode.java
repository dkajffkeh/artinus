package com.artinus.userapp.exception.code;

import org.springframework.http.HttpStatus;

public enum ArtinusErrorCode {

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST,"회원정보를 찾을수 없습니다."),
    CHANNEL_NOT_FOUND(HttpStatus.BAD_REQUEST,"채널정보를 찾을수 없습니다."),

    CANCEL_ONLY_CHANNEL(HttpStatus.BAD_REQUEST,"해당 채널은 구독 취소만 가능합니다.")

    ;

    private final HttpStatus httpStatus;
    private final String message;

    ArtinusErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public int getHttpStatusValue() {
        return httpStatus.value();
    }

    public String getMessage() {
        return message;
    }
}
