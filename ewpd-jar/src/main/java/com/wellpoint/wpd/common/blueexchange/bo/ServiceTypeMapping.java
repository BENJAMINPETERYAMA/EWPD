/*
 * ServiceTypeMapping.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.bo;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ServiceTypeMapping {
	private int mappingSysId;
	private String eb03Identifier;
	private String serviceCode;
	private String serviceClassLow;
	private String serviceClassHigh;
	private String placeOfService;
	private String limitClassHigh;
	private String limitClassLow;
	private String diagnosisHigh;
	private String diagnosisLow;
	private String procModifierCode;
	private String hcpHigh;
	private String hcpLow;
	private String providerSpeciality;
    private String createdUser;
    private Date createdTimestamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimestamp;	

	private String eb03IdentifierDes;
	private String serviceCodeDesc;
	private String serviceClassLowDes;
	private String serviceClassHighDesc;
	private String placeOfServiceDesc;
	private String limitClassHighDesc;
	private String limitClassLowDesc;
	private String diagnosisHighDesc;
	private String diagnosisLowDesc;
	private String procModifierCodeDesc;
	private String hcpHighDesc;
	private String hcpLowDesc;
	private String providerSpecialityDesc;
	private String sendDynamicInformation;
	
	//For DataFeed
	private String headerRuleId;
	private String headerRuleDescription;
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
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
	 * @return Returns the diagnosisHigh.
	 */
	public String getDiagnosisHigh() {
		return diagnosisHigh;
	}
	/**
	 * @param diagnosisHigh The diagnosisHigh to set.
	 */
	public void setDiagnosisHigh(String diagnosisHigh) {
		this.diagnosisHigh = diagnosisHigh;
	}
	/**
	 * @return Returns the diagnosisLow.
	 */
	public String getDiagnosisLow() {
		return diagnosisLow;
	}
	/**
	 * @param diagnosisLow The diagnosisLow to set.
	 */
	public void setDiagnosisLow(String diagnosisLow) {
		this.diagnosisLow = diagnosisLow;
	}
	/**
	 * @return Returns the eb03Identifier.
	 */
	public String getEb03Identifier() {
		return eb03Identifier;
	}
	/**
	 * @param eb03Identifier The eb03Identifier to set.
	 */
	public void setEb03Identifier(String eb03Identifier) {
		this.eb03Identifier = eb03Identifier;
	}
	/**
	 * @return Returns the hcpHigh.
	 */
	public String getHcpHigh() {
		return hcpHigh;
	}
	/**
	 * @param hcpHigh The hcpHigh to set.
	 */
	public void setHcpHigh(String hcpHigh) {
		this.hcpHigh = hcpHigh;
	}
	/**
	 * @return Returns the hcpLow.
	 */
	public String getHcpLow() {
		return hcpLow;
	}
	/**
	 * @param hcpLow The hcpLow to set.
	 */
	public void setHcpLow(String hcpLow) {
		this.hcpLow = hcpLow;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
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
	 * @return Returns the limitClassHigh.
	 */
	public String getLimitClassHigh() {
		return limitClassHigh;
	}
	/**
	 * @param limitClassHigh The limitClassHigh to set.
	 */
	public void setLimitClassHigh(String limitClassHigh) {
		this.limitClassHigh = limitClassHigh;
	}
	/**
	 * @return Returns the limitClassLow.
	 */
	public String getLimitClassLow() {
		return limitClassLow;
	}
	/**
	 * @param limitClassLow The limitClassLow to set.
	 */
	public void setLimitClassLow(String limitClassLow) {
		this.limitClassLow = limitClassLow;
	}
	/**
	 * @return Returns the mappingSysId.
	 */
	public int getMappingSysId() {
		return mappingSysId;
	}
	/**
	 * @param mappingSysId The mappingSysId to set.
	 */
	public void setMappingSysId(int mappingSysId) {
		this.mappingSysId = mappingSysId;
	}
	/**
	 * @return Returns the placeOfService.
	 */
	public String getPlaceOfService() {
		return placeOfService;
	}
	/**
	 * @param placeOfService The placeOfService to set.
	 */
	public void setPlaceOfService(String placeOfService) {
		this.placeOfService = placeOfService;
	}
	/**
	 * @return Returns the procModifierCode.
	 */
	public String getProcModifierCode() {
		return procModifierCode;
	}
	/**
	 * @param procModifierCode The procModifierCode to set.
	 */
	public void setProcModifierCode(String procModifierCode) {
		this.procModifierCode = procModifierCode;
	}
	/**
	 * @return Returns the providerSpeciality.
	 */
	public String getProviderSpeciality() {
		return providerSpeciality;
	}
	/**
	 * @param providerSpeciality The providerSpeciality to set.
	 */
	public void setProviderSpeciality(String providerSpeciality) {
		this.providerSpeciality = providerSpeciality;
	}
	/**
	 * @return Returns the serviceClassHigh.
	 */
	public String getServiceClassHigh() {
		return serviceClassHigh;
	}
	/**
	 * @param serviceClassHigh The serviceClassHigh to set.
	 */
	public void setServiceClassHigh(String serviceClassHigh) {
		this.serviceClassHigh = serviceClassHigh;
	}
	/**
	 * @return Returns the serviceClassLow.
	 */
	public String getServiceClassLow() {
		return serviceClassLow;
	}
	/**
	 * @param serviceClassLow The serviceClassLow to set.
	 */
	public void setServiceClassLow(String serviceClassLow) {
		this.serviceClassLow = serviceClassLow;
	}
	/**
	 * @return Returns the serviceCode.
	 */
	public String getServiceCode() {
		return serviceCode;
	}
	/**
	 * @param serviceCode The serviceCode to set.
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	/**
	 * @return Returns the diagnosisHighDesc.
	 */
	public String getDiagnosisHighDesc() {
		return diagnosisHighDesc;
	}
	/**
	 * @param diagnosisHighDesc The diagnosisHighDesc to set.
	 */
	public void setDiagnosisHighDesc(String diagnosisHighDesc) {
		this.diagnosisHighDesc = diagnosisHighDesc;
	}
	/**
	 * @return Returns the diagnosisLowDesc.
	 */
	public String getDiagnosisLowDesc() {
		return diagnosisLowDesc;
	}
	/**
	 * @param diagnosisLowDesc The diagnosisLowDesc to set.
	 */
	public void setDiagnosisLowDesc(String diagnosisLowDesc) {
		this.diagnosisLowDesc = diagnosisLowDesc;
	}
	/**
	 * @return Returns the eb03IdentifierDes.
	 */
	public String getEb03IdentifierDes() {
		return eb03IdentifierDes;
	}
	/**
	 * @param eb03IdentifierDes The eb03IdentifierDes to set.
	 */
	public void setEb03IdentifierDes(String eb03IdentifierDes) {
		this.eb03IdentifierDes = eb03IdentifierDes;
	}
	/**
	 * @return Returns the hcpHighDesc.
	 */
	public String getHcpHighDesc() {
		return hcpHighDesc;
	}
	/**
	 * @param hcpHighDesc The hcpHighDesc to set.
	 */
	public void setHcpHighDesc(String hcpHighDesc) {
		this.hcpHighDesc = hcpHighDesc;
	}
	/**
	 * @return Returns the hcpLowDesc.
	 */
	public String getHcpLowDesc() {
		return hcpLowDesc;
	}
	/**
	 * @param hcpLowDesc The hcpLowDesc to set.
	 */
	public void setHcpLowDesc(String hcpLowDesc) {
		this.hcpLowDesc = hcpLowDesc;
	}
	/**
	 * @return Returns the limitClassHighDesc.
	 */
	public String getLimitClassHighDesc() {
		return limitClassHighDesc;
	}
	/**
	 * @param limitClassHighDesc The limitClassHighDesc to set.
	 */
	public void setLimitClassHighDesc(String limitClassHighDesc) {
		this.limitClassHighDesc = limitClassHighDesc;
	}
	/**
	 * @return Returns the limitClassLowDesc.
	 */
	public String getLimitClassLowDesc() {
		return limitClassLowDesc;
	}
	/**
	 * @param limitClassLowDesc The limitClassLowDesc to set.
	 */
	public void setLimitClassLowDesc(String limitClassLowDesc) {
		this.limitClassLowDesc = limitClassLowDesc;
	}
	/**
	 * @return Returns the placeOfServiceDesc.
	 */
	public String getPlaceOfServiceDesc() {
		return placeOfServiceDesc;
	}
	/**
	 * @param placeOfServiceDesc The placeOfServiceDesc to set.
	 */
	public void setPlaceOfServiceDesc(String placeOfServiceDesc) {
		this.placeOfServiceDesc = placeOfServiceDesc;
	}
	/**
	 * @return Returns the procModifierCodeDesc.
	 */
	public String getProcModifierCodeDesc() {
		return procModifierCodeDesc;
	}
	/**
	 * @param procModifierCodeDesc The procModifierCodeDesc to set.
	 */
	public void setProcModifierCodeDesc(String procModifierCodeDesc) {
		this.procModifierCodeDesc = procModifierCodeDesc;
	}
	/**
	 * @return Returns the providerSpecialityDesc.
	 */
	public String getProviderSpecialityDesc() {
		return providerSpecialityDesc;
	}
	/**
	 * @param providerSpecialityDesc The providerSpecialityDesc to set.
	 */
	public void setProviderSpecialityDesc(String providerSpecialityDesc) {
		this.providerSpecialityDesc = providerSpecialityDesc;
	}
	/**
	 * @return Returns the serviceClassHighDesc.
	 */
	public String getServiceClassHighDesc() {
		return serviceClassHighDesc;
	}
	/**
	 * @param serviceClassHighDesc The serviceClassHighDesc to set.
	 */
	public void setServiceClassHighDesc(String serviceClassHighDesc) {
		this.serviceClassHighDesc = serviceClassHighDesc;
	}
	/**
	 * @return Returns the serviceClassLowDes.
	 */
	public String getServiceClassLowDes() {
		return serviceClassLowDes;
	}
	/**
	 * @param serviceClassLowDes The serviceClassLowDes to set.
	 */
	public void setServiceClassLowDes(String serviceClassLowDes) {
		this.serviceClassLowDes = serviceClassLowDes;
	}
	/**
	 * @return Returns the serviceCodeDesc.
	 */
	public String getServiceCodeDesc() {
		return serviceCodeDesc;
	}
	/**
	 * @param serviceCodeDesc The serviceCodeDesc to set.
	 */
	public void setServiceCodeDesc(String serviceCodeDesc) {
		this.serviceCodeDesc = serviceCodeDesc;
	}
	/**
	 * @return Returns the headerRuleId.
	 */
	public String getHeaderRuleId() {
		return headerRuleId;
	}
	/**
	 * @param headerRuleId The headerRuleId to set.
	 */
	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}
	/**
	 * @return Returns the sendDynamicInformation.
	 */
	public String getSendDynamicInformation() {
		return sendDynamicInformation;
	}
	/**
	 * @param sendDynamicInformation The sendDynamicInformation to set.
	 */
	public void setSendDynamicInformation(String sendDynamicInformation) {
		this.sendDynamicInformation = sendDynamicInformation;
	}
	/**
	 * @return Returns the headerRuleDescription.
	 */
	public String getHeaderRuleDescription() {
		return headerRuleDescription;
	}
	/**
	 * @param headerRuleDescription The headerRuleDescription to set.
	 */
	public void setHeaderRuleDescription(String headerRuleDescription) {
		this.headerRuleDescription = headerRuleDescription;
	}
}
