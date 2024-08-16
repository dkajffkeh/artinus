package com.artinus.userapp.payload;

public class ApiResult<T> {

    private final MetaData meta;
    private final T data;

    public ApiResult(MetaData meta, T data) {
        this.meta = meta;
        this.data = data;
    }

    public static <T> ApiResult<T> succeed(T data) {
        return new ApiResult<>(new MetaData(200, "ok"), data);
    }

    public static <T> ApiResult<T> succeed(int code, T data) {
        return new ApiResult<>(new MetaData(code, "ok"), data);
    }

    public static <T> ApiResult<T> succeed() {
        return new ApiResult<>(new MetaData(200, "ok"), null);
    }

    public static <T> ApiResult<T> fail(int code, String message) {
        return new ApiResult<>(new MetaData(code, message), null);
    }

    public static <T> ApiResult<T> badRequest(T data, String message) {
        return new ApiResult<>(new MetaData(400, message), data);
    }

    public static class MetaData {
        private final int code;
        private final String message;

        public MetaData(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    public MetaData getMeta() {
        return meta;
    }

    public T getData() {
        return data;
    }
}
