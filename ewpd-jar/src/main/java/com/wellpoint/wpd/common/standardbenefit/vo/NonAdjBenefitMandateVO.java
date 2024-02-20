/*
 * Created on Mar 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.vo;

import java.util.Date;
import java.util.List;


/**
 * @author u13664
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NonAdjBenefitMandateVO {
	
	private Date effectiveDate;
	private Date expiryDate;
	private String optionalIdentifier = "";
	private String mandate = "";
	private int benefitSystemId;
	private int masterKeyUsedForUpdate; 
	private int masterVersion;
	private String benefitIdName;
	private int benefitMandateId;
	private String state;
	private String status;
	private String version;
	
	private int standardBenefitParentKey;
	private String standardBenefitStatus;
	private List businessDomains;
	
	//enhancements
	
	private String mandateCategoy;
	private String mandateType;
	private List citationNumberList;
	private String fundingArrangement;
	private String effectiveness;
	private String text;
	private List stateCode;
	
	/**
	 * @return Returns the effectiveDate.
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @return Returns the expiryDate.
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return Returns the mandate.
	 */
	public String getMandate() {
		return mandate;
	}
	/**
	 * @param mandate The mandate to set.
	 */
	public void setMandate(String mandate) {
		this.mandate = mandate;
	}
	/**
	 * @return Returns the optionalIdentifier.
	 */
	public String getOptionalIdentifier() {
		return optionalIdentifier;
	}
	/**
	 * @param optionalIdentifier The optionalIdentifier to set.
	 */
	public void setOptionalIdentifier(String optionalIdentifier) {
		this.optionalIdentifier = optionalIdentifier;
	}
	/**
	 * @return Returns the benefitIdName.
	 */
	public String getBenefitIdName() {
		return benefitIdName;
	}
	/**
	 * @param benefitIdName The benefitIdName to set.
	 */
	public void setBenefitIdName(String benefitIdName) {
		this.benefitIdName = benefitIdName;
	}
	/**
	 * @return Returns the benefitSystemId.
	 */
	public int getBenefitSystemId() {
		return benefitSystemId;
	}
	/**
	 * @param benefitSystemId The benefitSystemId to set.
	 */
	public void setBenefitSystemId(int benefitSystemId) {
		this.benefitSystemId = benefitSystemId;
	}
	/**
	 * @return Returns the masterKeyUsedForUpdate.
	 */
	public int getMasterKeyUsedForUpdate() {
		return masterKeyUsedForUpdate;
	}
	/**
	 * @param masterKeyUsedForUpdate The masterKeyUsedForUpdate to set.
	 */
	public void setMasterKeyUsedForUpdate(int masterKeyUsedForUpdate) {
		this.masterKeyUsedForUpdate = masterKeyUsedForUpdate;
	}
	/**
	 * @return Returns the masterVersion.
	 */
	public int getMasterVersion() {
		return masterVersion;
	}
	/**
	 * @param masterVersion The masterVersion to set.
	 */
	public void setMasterVersion(int masterVersion) {
		this.masterVersion = masterVersion;
	}
	/**
	 * Returns the benefitMandateId
	 * @return int benefitMandateId.
	 */
	public int getBenefitMandateId() {
		return benefitMandateId;
	}
	/**
	 * Sets the benefitMandateId
	 * @param benefitMandateId.
	 */
	public void setBenefitMandateId(int benefitMandateId) {
		this.benefitMandateId = benefitMandateId;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the version.
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * Returns the businessDomains
	 * @return List businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}
	/**
	 * Sets the businessDomains
	 * @param businessDomains.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}
	/**
	 * Returns the standardBenefitParentKey
	 * @return int standardBenefitParentKey.
	 */
	public int getStandardBenefitParentKey() {
		return standardBenefitParentKey;
	}
	/**
	 * Sets the standardBenefitParentKey
	 * @param standardBenefitParentKey.
	 */
	public void setStandardBenefitParentKey(int standardBenefitParentKey) {
		this.standardBenefitParentKey = standardBenefitParentKey;
	}
	/**
	 * Returns the standardBenefitStatus
	 * @return String standardBenefitStatus.
	 */
	public String getStandardBenefitStatus() {
		return standardBenefitStatus;
	}
	/**
	 * Sets the standardBenefitStatus
	 * @param standardBenefitStatus.
	 */
	public void setStandardBenefitStatus(String standardBenefitStatus) {
		this.standardBenefitStatus = standardBenefitStatus;
	}
	/**
	 * Returns the citationNumberList
	 * @return List citationNumberList.
	 */
	public List getCitationNumberList() {
		return citationNumberList;
	}
	/**
	 * Sets the citationNumberList
	 * @param citationNumberList.
	 */
	public void setCitationNumberList(List citationNumberList) {
		this.citationNumberList = citationNumberList;
	}
	/**
	 * Returns the effectiveness
	 * @return String effectiveness.
	 */
	public String getEffectiveness() {
		return effectiveness;
	}
	/**
	 * Sets the effectiveness
	 * @param effectiveness.
	 */
	public void setEffectiveness(String effectiveness) {
		this.effectiveness = effectiveness;
	}
	/**
	 * Returns the fundingArrangement
	 * @return String fundingArrangement.
	 */
	public String getFundingArrangement() {
		return fundingArrangement;
	}
	/**
	 * Sets the fundingArrangement
	 * @param fundingArrangement.
	 */
	public void setFundingArrangement(String fundingArrangement) {
		this.fundingArrangement = fundingArrangement;
	}
	/**
	 * Returns the text
	 * @return String text.
	 */
	public String getText() {
		return text;
	}
	/**
	 * Sets the text
	 * @param text.
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * Returns the mandateCategoy
	 * @return String mandateCategoy.
	 */
	public String getMandateCategoy() {
		return mandateCategoy;
	}
	/**
	 * Sets the mandateCategoy
	 * @param mandateCategoy.
	 */
	public void setMandateCategoy(String mandateCategoy) {
		this.mandateCategoy = mandateCategoy;
	}
	
	/**
	 * Returns the stateCode
	 * @return List stateCode.
	 */
	public List getStateCode() {
		return stateCode;
	}
	/**
	 * Sets the stateCode
	 * @param stateCode.
	 */
	public void setStateCode(List stateCode) {
		this.stateCode = stateCode;
	}
	/**
	 * Returns the mandateType
	 * @return String mandateType.
	 */
	public String getMandateType() {
		return mandateType;
	}
	/**
	 * Sets the mandateType
	 * @param mandateType.
	 */
	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}
}
