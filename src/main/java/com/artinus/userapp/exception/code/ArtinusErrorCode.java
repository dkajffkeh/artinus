package com.artinus.userapp.exception.code;

import org.springframework.http.HttpStatus;

public enum ArtinusErrorCode {

    PASSWORD_NOT_MATCH(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    CLIENT_ALREADY_EXIST(HttpStatus.CONFLICT, "이미 등록된 번호입니다."),
    CLIENT_NOT_FOUND(HttpStatus.UNAUTHORIZED,"유저 정보가 존재하지 않습니다."),
    INVALID_TOKEN_REQUEST(HttpStatus.UNAUTHORIZED,"인증정보가 유효하지 않습니다."),
    IS_LOG_OUT(HttpStatus.UNAUTHORIZED,"로그아웃 유저 입니다. 다시 로그인 해주세요."),

    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED,"만료된 토큰입니다. 다시 로그인 해주세요."),
    PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST,"상품 정보를 찾을 수 없습니다."),

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
