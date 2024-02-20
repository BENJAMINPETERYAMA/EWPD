package com.wellpoint.ets.bx.mapping.domain.exception;

public class EBXException extends Exception {
	
	private static final long serialVersionUID = -4139873149511790630L;
	private String displayMessage;
    private String displayDescription;
    public EBXException(String message, Throwable ex, String displayMessage, String displayDescription) {
        super(message, ex);
        this.displayMessage = displayMessage;
        this.displayDescription = displayDescription;
    }
    public EBXException(String message){
        super(message);
    }
    public String getDisplayDescription() {
        return displayDescription;
    }
    public void setDisplayDescription(String displayDescription) {
        this.displayDescription = displayDescription;
    }
    public String getDisplayMessage() {
        return displayMessage;
    }
    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

}
