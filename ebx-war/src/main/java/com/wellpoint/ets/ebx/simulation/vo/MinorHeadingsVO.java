/*
 * <MinorHeadingsVO.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.ebx.simulation.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;

/**
 * @author UST-GLOBAL
 * 
 * Value Object Class for storing MinorHeadings/Benefit
 * details.
 * 
 */
public class MinorHeadingsVO {
	
	private Map mappings;	
	private String descriptionText;
	private Mapping ruleMapping;
	private boolean flagBenefitCovered = false;
	private boolean flagBenefitCoveredPar = false;
	private boolean flagBenefitCoveredNpar = false;
	private Map adminMethodSPS;
	
	public boolean isFlagBenefitCoveredPar() {
		return flagBenefitCoveredPar;
	}
	public void setFlagBenefitCoveredPar(boolean flagBenefitCoveredPar) {
		this.flagBenefitCoveredPar = flagBenefitCoveredPar;
	}
	public boolean isFlagBenefitCoveredNpar() {
		return flagBenefitCoveredNpar;
	}
	public void setFlagBenefitCoveredNpar(boolean flagBenefitCoveredNpar) {
		this.flagBenefitCoveredNpar = flagBenefitCoveredNpar;
	}
	private List errorCodesList = new ArrayList();
	
	public Mapping getRuleMapping() {
		return ruleMapping;
	}
	public void setRuleMapping(Mapping ruleMapping) {
		this.ruleMapping = ruleMapping;
	}
	public String getDescriptionText() {
		return descriptionText;
	}
	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}
	public Map getMappings() {
		return mappings;
	}
	public void setMappings(Map mappings) {
		this.mappings = mappings;
	}
	
	public List getErrorCodesList() {
		return errorCodesList;
	}

	public void setErrorCodesList(List errorCodesList) {
		this.errorCodesList = errorCodesList;
	}
	public boolean isFlagBenefitCovered() {
		return flagBenefitCovered;
	}
	public void setFlagBenefitCovered(boolean flagBenefitCovered) {
		this.flagBenefitCovered = flagBenefitCovered;
	}
	public Map getAdminMethodSPS() {
		return adminMethodSPS;
	}
	public void setAdminMethodSPS(Map adminMethodSPS) {
		this.adminMethodSPS = adminMethodSPS;
	}
	
}