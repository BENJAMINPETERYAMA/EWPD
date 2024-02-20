/*
 * CopyBenefitHeadingsBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CopyBenefitHeadingsBO {
	
	private int trgtDateSegmentId;
	
	private int benefitsysId;
	
	private int componentId;
	

	/**
	 * @return Returns the benefitsysId.
	 */
	public int getBenefitsysId() {
		return benefitsysId;
	}
	/**
	 * @param benefitsysId The benefitsysId to set.
	 */
	public void setBenefitsysId(int benefitsysId) {
		this.benefitsysId = benefitsysId;
	}
	/**
	 * @return Returns the componentId.
	 */
	public int getComponentId() {
		return componentId;
	}
	/**
	 * @param componentId The componentId to set.
	 */
	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}
	/**
	 * @return Returns the trgtDateSegmentId.
	 */
	public int getTrgtDateSegmentId() {
		return trgtDateSegmentId;
	}
	/**
	 * @param trgtDateSegmentId The trgtDateSegmentId to set.
	 */
	public void setTrgtDateSegmentId(int trgtDateSegmentId) {
		this.trgtDateSegmentId = trgtDateSegmentId;
	}
}
