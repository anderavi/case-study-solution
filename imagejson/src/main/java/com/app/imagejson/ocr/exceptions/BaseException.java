package com.app.imagejson.ocr.exceptions;

public class BaseException extends Exception {

	private static final long serialVersionUID = 4110871732414934686L;

	private String message;

	public String getMessage() {
		return message;
	}

	public BaseException(String message) {
		this.message = message;
	}

}
