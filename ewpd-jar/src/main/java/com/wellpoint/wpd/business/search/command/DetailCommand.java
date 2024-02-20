/*
 * DetailCommand.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.search.command;

import java.util.List;

import com.wellpoint.wpd.business.search.builder.SearchBuilder;
import com.wellpoint.wpd.common.framework.exception.SevereException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: DetailCommand.java 42077 2008-01-25 11:52:19Z u13547 $
 */
public class DetailCommand extends Command {
	private List objectIdentifiers;
	private String columnName; 
	private String orderDirection;
	private boolean attachment;
	private String objectType;
	private int recordCountLimit;
	
	/**
	 * @return Returns the objectIdentifiers.
	 */
	public List getObjectIdentifiers() {
		return objectIdentifiers;
	}
	/**
	 * @param objectIdentifier The objectIdentifier to set.
	 */
	public void setObjectIdentifiers(List objectIdentifier) {
		this.objectIdentifiers = objectIdentifier;
	}
    /**
     * @throws SevereException
     * @see com.wellpoint.wpd.business.search.command.Command#execute()
     * 
     */
    public void execute() throws SevereException {
    	SearchBuilder builder = new SearchBuilder();
    	if(attachment){
    		searchResult = builder.retrieveAttachmentForNotes(this);
    	}else{
        	searchResult = builder.retrieve(this);
    	}
    }
	/**
	 * @return Returns the columnName.
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnName The columnName to set.
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	/**
	 * @return Returns the orderDirection.
	 */
	public String getOrderDirection() {
		return orderDirection;
	}
	/**
	 * @param orderDirection The orderDirection to set.
	 */
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	/**
	 * @return Returns the attachment.
	 */
	public boolean isAttachment() {
		return attachment;
	}
	/**
	 * @param attachment The attachment to set.
	 */
	public void setAttachment(boolean attachment) {
		this.attachment = attachment;
	}
	/**
	 * @return Returns the objectType.
	 */
	public String getObjectType() {
		return objectType;
	}
	/**
	 * @param objectType The objectType to set.
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	/**
	 * @return Returns the recordCountLimit.
	 */
	public int getRecordCountLimit() {
		return recordCountLimit;
	}
	/**
	 * @param recordCountLimit The recordCountLimit to set.
	 */
	public void setRecordCountLimit(int recordCountLimit) {
		this.recordCountLimit = recordCountLimit;
	}
}
