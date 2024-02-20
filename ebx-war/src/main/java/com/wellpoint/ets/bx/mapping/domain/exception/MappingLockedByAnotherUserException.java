package com.wellpoint.ets.bx.mapping.domain.exception;

public class MappingLockedByAnotherUserException extends DomainException {

	private static final long serialVersionUID = 7578945628785407561L;

	public MappingLockedByAnotherUserException() {
		super();
	}

	public MappingLockedByAnotherUserException(String message) {
		super(message);
	}

	
}
