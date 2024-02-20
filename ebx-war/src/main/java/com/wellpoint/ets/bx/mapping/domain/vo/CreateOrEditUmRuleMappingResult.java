package com.wellpoint.ets.bx.mapping.domain.vo;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMappingData;

public class CreateOrEditUmRuleMappingResult {

	private int status;

	private List statusCodes = new ArrayList();//Will set this if mapping already exists.

	private SpiderUMRuleMapping spiderUMRuleMapping;
	
	private List<SpiderUMRuleMappingData> spiderList = new ArrayList<SpiderUMRuleMappingData>();
	
    private List errorMsgsList = new ArrayList();
	
	private List warningMsgsList = new ArrayList();
	
	private List errorMsgsWthPlaceHoldersList = new ArrayList();
	
	public CreateOrEditUmRuleMappingResult()
	{
		
	}

	public List<SpiderUMRuleMappingData> getSpiderList() {
		return spiderList;
	}

	public void setSpiderList(List<SpiderUMRuleMappingData> spiderList) {
		this.spiderList = spiderList;
	}

	public CreateOrEditUmRuleMappingResult(SpiderUMRuleMapping spiderUMRuleMapping) {
		this.spiderUMRuleMapping = spiderUMRuleMapping;
	}
	
	public SpiderUMRuleMapping getSpiderUMRuleMapping() {
		return spiderUMRuleMapping;
	}

	public void setSpiderUMRuleMapping(SpiderUMRuleMapping spiderUMRuleMapping) {
		this.spiderUMRuleMapping = spiderUMRuleMapping;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List getStatusCodes() {
		return statusCodes;
	}

	public void setStatusCodes(List statusCodes) {
		this.statusCodes = statusCodes;
	}

	public List getErrorMsgsList() {
		return errorMsgsList;
	}

	public void setErrorMsgsList(List errorMsgsList) {
		this.errorMsgsList = errorMsgsList;
	}

	public List getWarningMsgsList() {
		return warningMsgsList;
	}

	public void setWarningMsgsList(List warningMsgsList) {
		this.warningMsgsList = warningMsgsList;
	}

	public List getErrorMsgsWthPlaceHoldersList() {
		return errorMsgsWthPlaceHoldersList;
	}

	public void setErrorMsgsWthPlaceHoldersList(List errorMsgsWthPlaceHoldersList) {
		this.errorMsgsWthPlaceHoldersList = errorMsgsWthPlaceHoldersList;
	}
	
	public boolean isValidationSucess() {
		// TODO Auto-generated method stub
		if (status == 1) {
			return true;
		}
		return false;
	}
		
}
