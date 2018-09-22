package com.tlimskech.marketplace.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * @author Goodluck
 *
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthMethodNotSupportedException(String msg) {
		super(msg);
	}

}
