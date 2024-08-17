package com.artinus.userapp.exception.code;

import org.springframework.http.HttpStatus;

public enum ArtinusErrorCode {

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST,"회원정보를 찾을수 없습니다."),
    CHANNEL_NOT_FOUND(HttpStatus.BAD_REQUEST,"채널정보를 찾을수 없습니다."),

    SUBSCRIPTION_NOT_FOUND(HttpStatus.BAD_REQUEST,"기존 구독정보를 찾을수 없습니다."),

    CANCEL_ONLY(HttpStatus.BAD_REQUEST,"해당 채널은 구독 취소만 가능합니다."),

    SUBSCRIBE_ONLY(HttpStatus.BAD_REQUEST,"해당 채널은 구독만 가능합니다."),

    NOT_UPPER_SUBSCRIBE_REQUEST(HttpStatus.BAD_REQUEST, "구독은 현재보다 상위 요청만 가능합니다."),

    UPPER_SUBSCRIBE_REQUEST(HttpStatus.BAD_REQUEST, "구독 취소는 현재 구독 단계보다 낮아야합니다.");


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
