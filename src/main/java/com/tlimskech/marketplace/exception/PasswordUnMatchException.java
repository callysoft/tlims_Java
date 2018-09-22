package com.tlimskech.marketplace.exception;

public class PasswordUnMatchException extends RuntimeException {

	private static final long serialVersionUID = -8324116463444368386L;

	public PasswordUnMatchException(String message) {
		super(message);
	}

}
