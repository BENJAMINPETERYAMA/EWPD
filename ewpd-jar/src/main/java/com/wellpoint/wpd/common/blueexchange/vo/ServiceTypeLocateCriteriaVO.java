/*
 * ServiceTypeLocateCriteriaVO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.vo;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ServiceTypeLocateCriteriaVO {
	
	private List eb03Identifier;
	private List serviceCode;
	private List serviceClassLow;
	private List serviceClassHigh;
	private List placeOfService;
	private List limitClassHigh;
	private List limitClassLow;
	private List diagnosisHigh;
	private List diagnosisLow;
	private List procModifierCode;
	private List hcpHigh;
	private List hcpLow;
	private List providerSpeciality;
	private String placeOfServicePOS;
	private String eb03IdentifierSTC;
	private String headerRuleId;
	private String sendDirectInformation;
	/**
	 * @return Returns the diagnosisHigh.
	 */
	public List getDiagnosisHigh() {
		return diagnosisHigh;
	}
	/**
	 * @param diagnosisHigh The diagnosisHigh to set.
	 */
	public void setDiagnosisHigh(List diagnosisHigh) {
		this.diagnosisHigh = diagnosisHigh;
	}
	/**
	 * @return Returns the diagnosisLow.
	 */
	public List getDiagnosisLow() {
		return diagnosisLow;
	}
	/**
	 * @param diagnosisLow The diagnosisLow to set.
	 */
	public void setDiagnosisLow(List diagnosisLow) {
		this.diagnosisLow = diagnosisLow;
	}
	/**
	 * @return Returns the eb03Identifier.
	 */
	public List getEb03Identifier() {
		return eb03Identifier;
	}
	/**
	 * @param eb03Identifier The eb03Identifier to set.
	 */
	public void setEb03Identifier(List eb03Identifier) {
		this.eb03Identifier = eb03Identifier;
	}
	/**
	 * @return Returns the hcpHigh.
	 */
	public List getHcpHigh() {
		return hcpHigh;
	}
	/**
	 * @param hcpHigh The hcpHigh to set.
	 */
	public void setHcpHigh(List hcpHigh) {
		this.hcpHigh = hcpHigh;
	}
	/**
	 * @return Returns the hcpLow.
	 */
	public List getHcpLow() {
		return hcpLow;
	}
	/**
	 * @param hcpLow The hcpLow to set.
	 */
	public void setHcpLow(List hcpLow) {
		this.hcpLow = hcpLow;
	}
	/**
	 * @return Returns the limitClassHigh.
	 */
	public List getLimitClassHigh() {
		return limitClassHigh;
	}
	/**
	 * @param limitClassHigh The limitClassHigh to set.
	 */
	public void setLimitClassHigh(List limitClassHigh) {
		this.limitClassHigh = limitClassHigh;
	}
	/**
	 * @return Returns the limitClassLow.
	 */
	public List getLimitClassLow() {
		return limitClassLow;
	}
	/**
	 * @param limitClassLow The limitClassLow to set.
	 */
	public void setLimitClassLow(List limitClassLow) {
		this.limitClassLow = limitClassLow;
	}
	/**
	 * @return Returns the placeOfService.
	 */
	public List getPlaceOfService() {
		return placeOfService;
	}
	/**
	 * @param placeOfService The placeOfService to set.
	 */
	public void setPlaceOfService(List placeOfService) {
		this.placeOfService = placeOfService;
	}
	/**
	 * @return Returns the procModifierCode.
	 */
	public List getProcModifierCode() {
		return procModifierCode;
	}
	/**
	 * @param procModifierCode The procModifierCode to set.
	 */
	public void setProcModifierCode(List procModifierCode) {
		this.procModifierCode = procModifierCode;
	}
	/**
	 * @return Returns the providerSpeciality.
	 */
	public List getProviderSpeciality() {
		return providerSpeciality;
	}
	/**
	 * @param providerSpeciality The providerSpeciality to set.
	 */
	public void setProviderSpeciality(List providerSpeciality) {
		this.providerSpeciality = providerSpeciality;
	}
	/**
	 * @return Returns the serviceClassHigh.
	 */
	public List getServiceClassHigh() {
		return serviceClassHigh;
	}
	/**
	 * @param serviceClassHigh The serviceClassHigh to set.
	 */
	public void setServiceClassHigh(List serviceClassHigh) {
		this.serviceClassHigh = serviceClassHigh;
	}
	/**
	 * @return Returns the serviceClassLow.
	 */
	public List getServiceClassLow() {
		return serviceClassLow;
	}
	/**
	 * @param serviceClassLow The serviceClassLow to set.
	 */
	public void setServiceClassLow(List serviceClassLow) {
		this.serviceClassLow = serviceClassLow;
	}
	/**
	 * @return Returns the serviceCode.
	 */
	public List getServiceCode() {
		return serviceCode;
	}
	/**
	 * @param serviceCode The serviceCode to set.
	 */
	public void setServiceCode(List serviceCode) {
		this.serviceCode = serviceCode;
	}
	/**
	 * @return Returns the eb03IdentifierSTC.
	 */
	public String getEb03IdentifierSTC() {
		return eb03IdentifierSTC;
	}
	/**
	 * @param eb03IdentifierSTC The eb03IdentifierSTC to set.
	 */
	public void setEb03IdentifierSTC(String eb03IdentifierSTC) {
		this.eb03IdentifierSTC = eb03IdentifierSTC;
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
	 * @return Returns the placeOfServicePOS.
	 */
	public String getPlaceOfServicePOS() {
		return placeOfServicePOS;
	}
	/**
	 * @param placeOfServicePOS The placeOfServicePOS to set.
	 */
	public void setPlaceOfServicePOS(String placeOfServicePOS) {
		this.placeOfServicePOS = placeOfServicePOS;
	}
	/**
	 * @return Returns the sendDirectInformation.
	 */
	public String getSendDirectInformation() {
		return sendDirectInformation;
	}
	/**
	 * @param sendDirectInformation The sendDirectInformation to set.
	 */
	public void setSendDirectInformation(String sendDirectInformation) {
		this.sendDirectInformation = sendDirectInformation;
	}
}
