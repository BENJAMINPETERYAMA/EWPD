/*
 * Created on Jun 3, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.indicativemapping.response;

import java.util.Date;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveIndicativeMappingResponse extends WPDResponse {
	
	private String indicativeSegment;
	
	private String indicativeSegmentDesc;
	
	private String segmentNumber;
	
	private String spsParameter;
	
	private String spsParameterDesc;

	private String benefit;
	
	private String indMapDesc;
	
	private String createdUser;

	private String lastUpdatedUser;

	private Date createdDate;

	private Date lastUpdatedDate;
	

	/**
	 * @return Returns the segmentNumber.
	 */
	public String getSegmentNumber() {
		return segmentNumber;
	}
	/**
	 * @param segmentNumber The segmentNumber to set.
	 */
	public void setSegmentNumber(String segmentNumber) {
		this.segmentNumber = segmentNumber;
	}
	/**
	 * @return Returns the benefit.
	 */
	public String getBenefit() {
		return benefit;
	}
	/**
	 * @param benefit The benefit to set.
	 */
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	/**
	 * @return Returns the createdDate.
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate The createdDate to set.
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the indicativeSegment.
	 */
	public String getIndicativeSegment() {
		return indicativeSegment;
	}
	/**
	 * @param indicativeSegment The indicativeSegment to set.
	 */
	public void setIndicativeSegment(String indicativeSegment) {
		this.indicativeSegment = indicativeSegment;
	}
	/**
	 * @return Returns the indicativeSegmentDesc.
	 */
	public String getIndicativeSegmentDesc() {
		return indicativeSegmentDesc;
	}
	/**
	 * @param indicativeSegmentDesc The indicativeSegmentDesc to set.
	 */
	public void setIndicativeSegmentDesc(String indicativeSegmentDesc) {
		this.indicativeSegmentDesc = indicativeSegmentDesc;
	}
	/**
	 * @return Returns the indMapDesc.
	 */
	public String getIndMapDesc() {
		return indMapDesc;
	}
	/**
	 * @param indMapDesc The indMapDesc to set.
	 */
	public void setIndMapDesc(String indMapDesc) {
		this.indMapDesc = indMapDesc;
	}
	/**
	 * @return Returns the lastUpdatedDate.
	 */
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	/**
	 * @param lastUpdatedDate The lastUpdatedDate to set.
	 */
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the spsParameter.
	 */
	public String getSpsParameter() {
		return spsParameter;
	}
	/**
	 * @param spsParameter The spsParameter to set.
	 */
	public void setSpsParameter(String spsParameter) {
		this.spsParameter = spsParameter;
	}
	/**
	 * @return Returns the spsParameterDesc.
	 */
	public String getSpsParameterDesc() {
		return spsParameterDesc;
	}
	/**
	 * @param spsParameterDesc The spsParameterDesc to set.
	 */
	public void setSpsParameterDesc(String spsParameterDesc) {
		this.spsParameterDesc = spsParameterDesc;
	}
}
