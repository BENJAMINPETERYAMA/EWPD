/*
 * Created on Jun 30, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Messages implements Serializable{
	private List messages ;
	

	/**
	 * @return Returns the messages.
	 */
	public List getMessages() {
		return messages;
	}
	/**
	 * @param messages The messages to set.
	 */
	public void setMessages(List messages) {
		this.messages = messages;
	}

}
