package com.tlimskech.marketplace.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Goodluck
 *
 */
public class JwtExpiredTokenException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String token;

	public JwtExpiredTokenException(String msg) {
		super(msg);
	}

	public JwtExpiredTokenException(String token, String msg, Throwable t) {
		super(msg, t);
		this.token = token;
	}

	public String token() {
		return this.token;
	}
}
