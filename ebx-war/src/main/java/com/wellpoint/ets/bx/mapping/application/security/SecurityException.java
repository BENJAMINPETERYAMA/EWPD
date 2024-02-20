/*
 * Created on Jun 2, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.application.security;

/**
 * @author u20622
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SecurityException extends Exception {

	private static final long serialVersionUID = 4581648602207696017L;

	public SecurityException() {
		super();
	}
	/**
	 * @param message
	 */
	public SecurityException(String message) {
		super(message);
	}
	/**
	 * @param message
	 * @param cause
	 */
	public SecurityException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * @param cause
	 */
	public SecurityException(Throwable cause) {
		super(cause);
	}
}
