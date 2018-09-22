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

	public ErrorData(String message) {
		this.message = message;
	}
	
	public ErrorData(ErrorCode errorCode,String message) {
		ErrorData.setErrorCode(errorCode);
		this.message = message;
	}

	public static ErrorCode getErrorCode() {
		return errorCode;
	}

	public static void setErrorCode(ErrorCode errorCode) {
		ErrorData.errorCode = errorCode;
	}
}
