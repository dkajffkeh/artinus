package com.artinus.userapp.exception;

import com.artinus.userapp.exception.code.ArtinusErrorCode;
import com.artinus.userapp.payload.ApiResult;

public class ArtinusException extends RuntimeException {

    private final ArtinusErrorCode artinusErrorCode;

    public ArtinusException(ArtinusErrorCode artinusErrorCode) {
        super(artinusErrorCode.getMessage());
        this.artinusErrorCode = artinusErrorCode;
    }

    public ArtinusErrorCode getErrorCode() {
        return artinusErrorCode;
    }

    public String getMessage() {
        return this.artinusErrorCode.getMessage();
    }

    public int httpStatusValue() {
        return this.artinusErrorCode.getHttpStatusValue();
    }

    public ApiResult<String> toApiResult() {
        return ApiResult.fail(artinusErrorCode.getHttpStatusValue(), artinusErrorCode.getMessage());
    }
}
