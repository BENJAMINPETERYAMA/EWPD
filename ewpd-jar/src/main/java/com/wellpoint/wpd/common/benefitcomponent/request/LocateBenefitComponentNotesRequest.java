/*
 * Created on May 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitcomponent.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author u13592
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LocateBenefitComponentNotesRequest extends WPDRequest{
	
	private int entityId;
	private String entityType;
	private int maxResultSize;
	
	//For Override
	private int secEntityId;
	private String secEntityType;
	private int benefitComponentId;
	private int action;
	
	
    /**
     * @return entityId
     * 
     * Returns the entityId.
     */
    public int getEntityId() {
        return entityId;
    }
    /**
     * @param entityId
     * 
     * Sets the entityId.
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
	/**
	 * @return Returns the maxResultSize.
	 */
	public int getMaxResultSize() {
		return maxResultSize;
	}
	/**
	 * @param maxResultSize The maxResultSize to set.
	 */
	public void setMaxResultSize(int maxResultSize) {
		this.maxResultSize = maxResultSize;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
    
    /**
     * @return benefitComponentId
     * 
     * Returns the benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * @param benefitComponentId
     * 
     * Sets the benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
    /**
     * @return secEntityId
     * 
     * Returns the secEntityId.
     */
    public int getSecEntityId() {
        return secEntityId;
    }
    /**
     * @param secEntityId
     * 
     * Sets the secEntityId.
     */
    public void setSecEntityId(int secEntityId) {
        this.secEntityId = secEntityId;
    }
    /**
     * @return secEntityType
     * 
     * Returns the secEntityType.
     */
    public String getSecEntityType() {
        return secEntityType;
    }
    /**
     * @param secEntityType
     * 
     * Sets the secEntityType.
     */
    public void setSecEntityType(String secEntityType) {
        this.secEntityType = secEntityType;
    }
    /**
     * @return action
     * 
     * Returns the action.
     */
    public int getAction() {
        return action;
    }
    /**
     * @param action
     * 
     * Sets the action.
     */
    public void setAction(int action) {
        this.action = action;
    }
}
