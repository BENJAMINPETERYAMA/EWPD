/*
 * Created on Aug 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.search.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U12218
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DomainFetchResponse extends WPDResponse{

	private List lob;
	
	private List businessEntity;
	
	private List businessGroup;
	
	/**
	 * @return Returns the businessEntity.
	 */
	public List getBusinessEntity() {
		return businessEntity;
	}
	/**
	 * @param businessEntity The businessEntity to set.
	 */
	public void setBusinessEntity(List businessEntity) {
		this.businessEntity = businessEntity;
	}
	/**
	 * @return Returns the businessGroup.
	 */
	public List getBusinessGroup() {
		return businessGroup;
	}
	/**
	 * @param businessGroup The businessGroup to set.
	 */
	public void setBusinessGroup(List businessGroup) {
		this.businessGroup = businessGroup;
	}
	/**
	 * @return Returns the lob.
	 */
	public List getLob() {
		return lob;
	}
	/**
	 * @param lob The lob to set.
	 */
	public void setLob(List lob) {
		this.lob = lob;
	}
}
