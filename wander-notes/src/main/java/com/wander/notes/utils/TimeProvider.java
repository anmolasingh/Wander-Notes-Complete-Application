package com.wander.notes.utils;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeProvider implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6981942055677716045L;

	public Date now() {
        return new Date();
    }
}
