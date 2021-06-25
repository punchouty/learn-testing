package com.racloop.learn.testing.api;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {
	private final Date timestamp;
	private final String message;
	private final String details;

	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

}