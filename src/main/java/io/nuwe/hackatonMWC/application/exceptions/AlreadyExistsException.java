package io.nuwe.hackatonMWC.application.exceptions;

public class AlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public AlreadyExistsException(String msg) {
		super(msg);
	}

}
