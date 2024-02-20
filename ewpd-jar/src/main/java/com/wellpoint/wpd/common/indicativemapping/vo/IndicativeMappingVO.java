/*
 * Created on Jun 3, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.indicativemapping.vo;

/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class IndicativeMappingVO {

	private String indicativeSegment;

	private String spsParameter;

	private String benefit;
	
	private String prevInd;

	private String prevSps;

	private String prevBen;
	
	private String description;
	

	// Added for Indicative Long Term Solution
	private String indicativeSegmentCode;

	private String indicativeSegmentNumber;

	private String indSegDesc;

	private int indicativeSegmentSeq;

	private String defaultIndicativeValue;

	private String logicIndValue;

	private String comment;

	private String indicativeRegion;

	private int indicativeSegLength;

	private String indValue;

	// end Indicative Long Term Solution
	
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
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return Returns the prevBen.
	 */
	public String getPrevBen() {
		return prevBen;
	}
	/**
	 * @param prevBen The prevBen to set.
	 */
	public void setPrevBen(String prevBen) {
		this.prevBen = prevBen;
	}
	/**
	 * @return Returns the prevInd.
	 */
	public String getPrevInd() {
		return prevInd;
	}
	/**
	 * @param prevInd The prevInd to set.
	 */
	public void setPrevInd(String prevInd) {
		this.prevInd = prevInd;
	}
	/**
	 * @return Returns the prevSps.
	 */
	public String getPrevSps() {
		return prevSps;
	}
	/**
	 * @param prevSps The prevSps to set.
	 */
	public void setPrevSps(String prevSps) {
		this.prevSps = prevSps;
	}

	// Added for indicative long term solution
	public String getIndicativeSegmentCode() {
		return indicativeSegmentCode;
	}

	public void setIndicativeSegmentCode(String indicativeSegmentCode) {
		this.indicativeSegmentCode = indicativeSegmentCode;
	}

	public String getIndicativeSegmentNumber() {
		return indicativeSegmentNumber;
	}

	public void setIndicativeSegmentNumber(String indicativeSegmentNumber) {
		this.indicativeSegmentNumber = indicativeSegmentNumber;
	}

	public String getIndSegDesc() {
		return indSegDesc;
	}

	public void setIndSegDesc(String indSegDesc) {
		this.indSegDesc = indSegDesc;
	}

	public int getIndicativeSegmentSeq() {
		return indicativeSegmentSeq;
	}

	public void setIndicativeSegmentSeq(int indicativeSegmentSeq) {
		this.indicativeSegmentSeq = indicativeSegmentSeq;
	}

	public String getDefaultIndicativeValue() {
		return defaultIndicativeValue;
	}

	public void setDefaultIndicativeValue(String defaultIndicativeValue) {
		this.defaultIndicativeValue = defaultIndicativeValue;
	}

	public String getLogicIndValue() {
		return logicIndValue;
	}

	public void setLogicIndValue(String logicIndValue) {
		this.logicIndValue = logicIndValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIndicativeRegion() {
		return indicativeRegion;
	}

	public void setIndicativeRegion(String indicativeRegion) {
		this.indicativeRegion = indicativeRegion;
	}

	public int getIndicativeSegLength() {
		return indicativeSegLength;
	}

	public void setIndicativeSegLength(int indicativeSegLength) {
		this.indicativeSegLength = indicativeSegLength;
	}

	public String getIndValue() {
		return indValue;
	}

	public void setIndValue(String indValue) {
		this.indValue = indValue;
	}
}