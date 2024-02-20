package com.wellpoint.ets.ebx.simulation.webservices.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MinorHeadingsWebServiceVO {
	private Map<String,ContractMappingWebServiceVO> mappings;	
	private String descriptionText;
	private ContractMappingWebServiceVO ruleMapping;
	private boolean flagBenefitCovered = false;
	private boolean flagBenefitCoveredPar = false;
	private boolean flagBenefitCoveredNpar = false;
	private Map<String, String> adminMethodSPS;
	private List<ErrorDetailWebServiceVO> errorCodesList = new ArrayList<ErrorDetailWebServiceVO>();
	
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
	
	public ContractMappingWebServiceVO getRuleMapping() {
		return ruleMapping;
	}
	public void setRuleMapping(ContractMappingWebServiceVO ruleMapping) {
		this.ruleMapping = ruleMapping;
	}
	public String getDescriptionText() {
		return descriptionText;
	}
	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}
	public Map<String,ContractMappingWebServiceVO> getMappings() {
		return mappings;
	}
	public void setMappings(Map<String,ContractMappingWebServiceVO> mappings) {
		this.mappings = mappings;
	}
	
	public List<ErrorDetailWebServiceVO> getErrorCodesList() {
		return errorCodesList;
	}

	public void setErrorCodesList(List<ErrorDetailWebServiceVO> errorCodesList) {
		this.errorCodesList = errorCodesList;
	}
	public boolean isFlagBenefitCovered() {
		return flagBenefitCovered;
	}
	public void setFlagBenefitCovered(boolean flagBenefitCovered) {
		this.flagBenefitCovered = flagBenefitCovered;
	}
	public Map<String,String> getAdminMethodSPS() {
		return adminMethodSPS;
	}
	public void setAdminMethodSPS(Map<String,String> adminMethodSPS) {
		this.adminMethodSPS = adminMethodSPS;
	}

}
