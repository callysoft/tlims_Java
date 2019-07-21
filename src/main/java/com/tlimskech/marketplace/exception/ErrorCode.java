package com.tlimskech.marketplace.exception;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Goodluck
 */
public enum ErrorCode {
    GLOBAL(2), AUTHENTICATION(10), JWT_TOKEN_EXPIRED(11), DUPLICATE(12), DATA_NOT_FOUND(13), BAD_ARGUEMENT(14), SQL_GRAMMAR(15), MAX_FILE_LIMIT(16);

    private int errorCode;

    ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}
