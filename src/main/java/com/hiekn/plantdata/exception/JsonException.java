package com.hiekn.plantdata.exception;

public class JsonException extends BaseException {
    private static final long serialVersionUID = 1L;

    public JsonException(Integer code) {
        super(code);
    }

    public static JsonException newInstance() {
        return newInstance(Integer.valueOf(300));
    }

    public static JsonException newInstance(Integer code) {
        return new JsonException(code);
    }
} 