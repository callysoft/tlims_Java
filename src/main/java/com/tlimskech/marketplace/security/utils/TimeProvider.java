package com.tlimskech.marketplace.security.utils;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeProvider implements Serializable{

	private static final long serialVersionUID = 6764813483731579134L;

	public Date now() {
        return new Date();
    }
}
