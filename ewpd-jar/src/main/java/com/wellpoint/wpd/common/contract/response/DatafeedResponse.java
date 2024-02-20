/*
 * DatafeedResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.contract.bo.ContractSPSBO;
import com.wellpoint.wpd.common.contract.bo.RuleIdBO;
import com.wellpoint.wpd.common.contract.tree.bo.ContractTreeProducts;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DatafeedResponse extends ContractResponse {
	
	private List searchResultList;

	private boolean success;

	private ContractTreeProducts contractTreeProducts;

	private RuleIdBO ruleIdBO;

	private List rulesList;

	private ContractSPSBO contractSPSBO;
	
	private Map spsValueMap;

	/**
	 * @return Returns the contractSPSBO.
	 */
	public ContractSPSBO getContractSPSBO() {
		return contractSPSBO;
	}

	/**
	 * @param contractSPSBO The contractSPSBO to set.
	 */
	public void setContractSPSBO(ContractSPSBO contractSPSBO) {
		this.contractSPSBO = contractSPSBO;
	}

	/**
	 * 
	 * @return
	 */
	public RuleIdBO getRuleIdBO() {
		return ruleIdBO;
	}

	/**
	 * 
	 * @param ruleIdBO
	 */
	public void setRuleIdBO(RuleIdBO ruleIdBO) {
		this.ruleIdBO = ruleIdBO;
	}

	/**
	 * Returns the searchResultList
	 * @return List searchResultList.
	 */
	public List getSearchResultList() {
		return searchResultList;
	}

	/**
	 * Sets the searchResultList
	 * @param searchResultList.
	 */
	public void setSearchResultList(List searchResultList) {
		this.searchResultList = searchResultList;
	}

	/**
	 * Returns the success
	 * @return boolean success.
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Sets the success
	 * @param success.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return Returns the contractTreeProducts.
	 */
	public ContractTreeProducts getContractTreeProducts() {
		return contractTreeProducts;
	}

	/**
	 * @param contractTreeProducts The contractTreeProducts to set.
	 */
	public void setContractTreeProducts(
			ContractTreeProducts contractTreeProducts) {
		this.contractTreeProducts = contractTreeProducts;
	}

	/**
	 * @return Returns the rulesList.
	 */
	public List getRulesList() {
		return rulesList;
	}

	/**
	 * @param rulesList The rulesList to set.
	 */
	public void setRulesList(List rulesList) {
		this.rulesList = rulesList;
	}

	/**
	 * @return Returns the spsValueMap.
	 */
	public Map getSpsValueMap() {
		return spsValueMap;
	}
	/**
	 * @param spsValueMap The spsValueMap to set.
	 */
	public void setSpsValueMap(Map spsValueMap) {
		this.spsValueMap = spsValueMap;
	}
}