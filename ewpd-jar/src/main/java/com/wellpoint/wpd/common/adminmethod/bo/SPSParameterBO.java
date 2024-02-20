/*
 * Created on Jan 15, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SPSParameterBO extends BusinessObject {
	
	private String admnMthdReqSps;
	private String referenceDesc;
	private String value;
	private String type;
	boolean isCoded;
	
	
	

	/**
	 * @return Returns the isCoded.
	 */
	public boolean isCoded() {
		return isCoded;
	}
	/**
	 * @param isCoded The isCoded to set.
	 */
	public void setCoded(boolean isCoded) {
		this.isCoded = isCoded;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @return Returns the admnMthdReqSps.
	 */
	public String getAdmnMthdReqSps() {
		return admnMthdReqSps;
	}
	/**
	 * @param admnMthdReqSps The admnMthdReqSps to set.
	 */
	public void setAdmnMthdReqSps(String admnMthdReqSps) {
		this.admnMthdReqSps = admnMthdReqSps;
	}
	/**
	 * @return Returns the referenceDesc.
	 */
	public String getReferenceDesc() {
		return referenceDesc;
	}
	/**
	 * @param referenceDesc The referenceDesc to set.
	 */
	public void setReferenceDesc(String referenceDesc) {
		this.referenceDesc = referenceDesc;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
