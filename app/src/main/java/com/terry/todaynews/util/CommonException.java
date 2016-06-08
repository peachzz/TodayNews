package com.terry.todaynews.util;

public class CommonException extends Exception {

	private static final long serialVersionUID = 1L;

	public CommonException() {

	}

	public CommonException(String msg) {
		super(msg);
	}

	public CommonException(Throwable exception) {
		super(exception);
	}

	public CommonException(String msg, Throwable exception) {

	}
}
