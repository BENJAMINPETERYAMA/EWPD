/*
 * Created on Sep 29, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.contract;

import java.util.Date;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U19129
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractTransferLogBO extends BusinessObject{

	
	private String user;
	private int version;
	private String userAction;
	private Date timeStamp;
	private String noDateSegment;
	private int contractSysId;    
    private int dateSegmentId;
	private String dateSegmentTestProdnInd;
	private String mftfIndicator;
	private String effectiveDate;
	/**
	 * @return Returns the contractSysId.
	 */
	public int getContractSysId() {
		return contractSysId;
	}
	/**
	 * @param contractSysId The contractSysId to set.
	 */
	public void setContractSysId(int contractSysId) {
		this.contractSysId = contractSysId;
	}
	/**
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
    /**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	
	
	
	
	/**
	 * @return Returns the noDateSegment.
	 */
	public String getNoDateSegment() {
		return noDateSegment;
	}
	/**
	 * @param noDateSegment The noDateSegment to set.
	 */
	public void setNoDateSegment(String noDateSegment) {
		this.noDateSegment = noDateSegment;
	}
	
	
	/**
	 * @return Returns the timeStamp.
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}
	/**
	 * @param timeStamp The timeStamp to set.
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	/**
	 * @return Returns the user.
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user The user to set.
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return Returns the userAction.
	 */
	public String getUserAction() {
		return userAction;
	}
	/**
	 * @param userAction The userAction to set.
	 */
	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * @return Returns the dateSegmentTestProdnInd.
	 */
	public String getDateSegmentTestProdnInd() {
		return dateSegmentTestProdnInd;
	}
	/**
	 * @param dateSegmentTestProdnInd The dateSegmentTestProdnInd to set.
	 */
	public void setDateSegmentTestProdnInd(String dateSegmentTestProdnInd) {
		this.dateSegmentTestProdnInd = dateSegmentTestProdnInd;
	}
	/**
	 * @return Returns the mftfIndicator.
	 */
	public String getMftfIndicator() {
		return mftfIndicator;
	}
	/**
	 * @param mftfIndicator The mftfIndicator to set.
	 */
	public void setMftfIndicator(String mftfIndicator) {
		this.mftfIndicator = mftfIndicator;
	}
	
	
	/**
	 * @return Returns the effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}
