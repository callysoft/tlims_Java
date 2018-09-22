package com.tlimskech.marketplace.exception;

import lombok.Data;

@Data
public class MessageDTO {

	private String message;
	private MessageType type;
	
	public enum MessageType {
		  SUCCESS, INFO, WARNING, ERROR
	}
}
