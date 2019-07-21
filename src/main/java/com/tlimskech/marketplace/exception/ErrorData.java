package com.tlimskech.marketplace.exception;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorData {

    private String field;
    private String value;
    private String message;
    private static ErrorCode errorCode;
    private int code;

    public ErrorData(String message) {
        this.message = message;
    }

    public ErrorData(ErrorCode errorCode, String message) {
        ErrorData.setErrorCode(errorCode);
        this.message = message;
        this.code = errorCode.getErrorCode();
    }

    public ErrorData(String field, String value, String message) {
        this.field = field;
        this.value = value;
        this.message = message;
    }

    public static ErrorCode getErrorCode() {
        return errorCode;
    }

    public static void setErrorCode(ErrorCode errorCode) {
        ErrorData.errorCode = errorCode;
    }
}
