/*
 * Created on Jun 3, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.indicativemapping.bo;

import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class IndicativeMappingBO extends BusinessObject {

	private String indicativeSegmentCode;

	private String indicativeSegment;

	private String indicativeSegmentNumber;

	private String spsParameter;

	private String spsParameterCode;

	private String benefit;

	private String indSegDesc;

	private List indicativeList;

	private List spsList;

	private List benefitList;

	private int countIndicativeMapping;

	private String preBen;

	private int indicativeSegmentSeq;

	private String defaultIndicativeValue;
	
	private String logicIndValue;
	
	private String tierCode;
	
	private String startValue;
	
	private String endValue;
	// Indicative mapping APRIL 2015 Release chnages
	
	private String indicativeRegion;
	
	private int fieldLength;
	
	private String indComments;
	
	private String indValue;
	// end Indicative Long Term Solution
	

	/**
	 * @return Returns the countIndicativeMapping.
	 */
	public int getCountIndicativeMapping() {
		return countIndicativeMapping;
	}

	/**
	 * @param countIndicativeMapping
	 *            The countIndicativeMapping to set.
	 */
	public void setCountIndicativeMapping(int countIndicativeMapping) {
		this.countIndicativeMapping = countIndicativeMapping;
	}

	/**
	 * @return Returns the preBen.
	 */
	public String getPreBen() {
		return preBen;
	}

	/**
	 * @param preBen
	 *            The preBen to set.
	 */
	public void setPreBen(String preBen) {
		this.preBen = preBen;
	}

	/**
	 * @return Returns the indSegDesc.
	 */
	public String getIndSegDesc() {
		return indSegDesc;
	}

	/**
	 * @param indSegDesc
	 *            The indSegDesc to set.
	 */
	public void setIndSegDesc(String indSegDesc) {
		this.indSegDesc = indSegDesc;
	}

	/**
	 * @return Returns the indicativeSegmentCode.
	 */
	public String getIndicativeSegmentCode() {
		return indicativeSegmentCode;
	}

	/**
	 * @param indicativeSegmentCode
	 *            The indicativeSegmentCode to set.
	 */
	public void setIndicativeSegmentCode(String indicativeSegmentCode) {
		this.indicativeSegmentCode = indicativeSegmentCode;
	}

	/**
	 * @return Returns the spsParameterCode.
	 */
	public String getSpsParameterCode() {
		return spsParameterCode;
	}

	/**
	 * @param spsParameterCode
	 *            The spsParameterCode to set.
	 */
	public void setSpsParameterCode(String spsParameterCode) {
		this.spsParameterCode = spsParameterCode;
	}

	/**
	 * @return Returns the indicativeSegmentNumber.
	 */
	public String getIndicativeSegmentNumber() {
		return indicativeSegmentNumber;
	}

	/**
	 * @param indicativeSegmentNumber
	 *            The indicativeSegmentNumber to set.
	 */
	public void setIndicativeSegmentNumber(String indicativeSegmentNumber) {
		this.indicativeSegmentNumber = indicativeSegmentNumber;
	}

	/**
	 * @return Returns the benefitList.
	 */
	public List getBenefitList() {
		return benefitList;
	}

	/**
	 * @param benefitList
	 *            The benefitList to set.
	 */
	public void setBenefitList(List benefitList) {
		this.benefitList = benefitList;
	}

	/**
	 * @return Returns the indicativeList.
	 */
	public List getIndicativeList() {
		return indicativeList;
	}

	/**
	 * @param indicativeList
	 *            The indicativeList to set.
	 */
	public void setIndicativeList(List indicativeList) {
		this.indicativeList = indicativeList;
	}

	/**
	 * @return Returns the spsList.
	 */
	public List getSpsList() {
		return spsList;
	}

	/**
	 * @param spsList
	 *            The spsList to set.
	 */
	public void setSpsList(List spsList) {
		this.spsList = spsList;
	}

	/**
	 * @return Returns the benefit.
	 */
	public String getBenefit() {
		return benefit;
	}

	/**
	 * @param benefit
	 *            The benefit to set.
	 */
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

	/**
	 * @return Returns the indicativeSegment.
	 */
	public String getIndicativeSegment() {
		return indicativeSegment;
	}

	/**
	 * @param indicativeSegment
	 *            The indicativeSegment to set.
	 */
	public void setIndicativeSegment(String indicativeSegment) {
		this.indicativeSegment = indicativeSegment;
	}

	/**
	 * @return Returns the spsParameter.
	 */
	public String getSpsParameter() {
		return spsParameter;
	}

	/**
	 * @param spsParameter
	 *            The spsParameter to set.
	 */
	public void setSpsParameter(String spsParameter) {
		this.spsParameter = spsParameter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the indicativeSegmentSeq.
	 */
	public int getIndicativeSegmentSeq() {
		return indicativeSegmentSeq;
	}

	/**
	 * @param indicativeSegmentSeq
	 *            The indicativeSegmentSeq to set.
	 */
	public void setIndicativeSegmentSeq(int indicativeSegmentSeq) {
		this.indicativeSegmentSeq = indicativeSegmentSeq;
	}

	/**
	 * @return Returns the defaultIndicativeValue.
	 */
	public String getDefaultIndicativeValue() {
		return defaultIndicativeValue;
	}

	/**
	 * @param defaultIndicativeValue
	 *            The defaultIndicativeValue to set.
	 */
	public void setDefaultIndicativeValue(String defaultIndicativeValue) {
		this.defaultIndicativeValue = defaultIndicativeValue;
	}
	/**
	 * @return Returns the logicIndValue.
	 */
	public String getLogicIndValue() {
		return logicIndValue;
	}
	/**
	 * @param logicIndValue The logicIndValue to set.
	 */
	public void setLogicIndValue(String logicIndValue) {
		this.logicIndValue = logicIndValue;
	}
	/**
	 * @return Returns the endvalue.
	 */


	/**
	 * @return Returns the tierCode.
	 */
	public String getTierCode() {
		return tierCode;
	}
	/**
	 * @param tierCode The tierCode to set.
	 */
	public void setTierCode(String tierCode) {
		this.tierCode = tierCode;
	}
	/**
	 * @return Returns the endValue.
	 */
	public String getEndValue() {
		return endValue;
	}
	/**
	 * @param endValue The endValue to set.
	 */
	public void setEndValue(String endValue) {
		this.endValue = endValue;
	}
	/**
	 * @return Returns the startValue.
	 */
	public String getStartValue() {
		return startValue;
	}
	/**
	 * @param startValue The startValue to set.
	 */
	public void setStartValue(String startValue) {
		this.startValue = startValue;
	}
	//Getters and Setters for attributes added as part of Indicative Mapping APRIL 2015 Release changes
	public String getIndicativeRegion() {
		return indicativeRegion;
	}

	public void setIndicativeRegion(String indicativeRegion) {
		this.indicativeRegion = indicativeRegion;
	}

	public int getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(int fieldLength) {
		this.fieldLength = fieldLength;
	}

	public String getIndComments() {
		return indComments;
	}

	public void setIndComments(String indComments) {
		this.indComments = indComments;
	}
	public String getIndValue() {
		return indValue;
	}

	public void setIndValue(String indValue) {
		this.indValue = indValue;
	}
}