/*
 * Created on Jun 4, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.exception;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DomainException extends RuntimeException{
    
	private static final long serialVersionUID = -7189461149750035870L;
	private String message;
    
    public DomainException() {
        super();
    }
    
    /**
     * @param message
     */
    public DomainException(String message) {
        super();
        this.message = message;
    }
    
    
    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
