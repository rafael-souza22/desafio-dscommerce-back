package com.mdorici.dscommerce.services.exceptions;

public class ResourceNotFindException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ResourceNotFindException(String msg) {
		super(msg);
	}
}
