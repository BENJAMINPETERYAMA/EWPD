/*
 * Created on Jul 1, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.contract.model.Message;
import com.wellpoint.wpd.common.contract.request.ContractRuleValidationRequest;
import com.wellpoint.wpd.common.contract.request.ContractRulesetRequest;
import com.wellpoint.wpd.common.contract.response.ContractRuleValidationResponse;
import com.wellpoint.wpd.common.contract.response.ContractRulesetResponse;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author U14659
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ContractRuleValidationBackingBean extends WPDBackingBean {

	private String contractId;

	private String contractIdCriteria;

	private String effectiveDate;

	private String ruleCriteria;

	private List ruleSet;

	private String contractvalidationCriteria;

	private List searchList;

	private boolean contractIdreq;

	private boolean effectiveDatereq;

	private boolean contractRulereq;

	/**
	 * @param ruleSet
	 *            The ruleSet to set.
	 */
	public void setRuleSet(List ruleSet) {
		this.ruleSet = ruleSet;
	}

	/**
	 * @return Returns the contractIdreq.
	 */
	public boolean isContractIdreq() {
		return contractIdreq;
	}

	/**
	 * @param contractIdreq
	 *            The contractIdreq to set.
	 */
	public void setContractIdreq(boolean contractIdreq) {
		this.contractIdreq = contractIdreq;
	}

	/**
	 * @return Returns the contractRulereq.
	 */
	public boolean isContractRulereq() {
		return contractRulereq;
	}

	/**
	 * @param contractRulereq
	 *            The contractRulereq to set.
	 */
	public void setContractRulereq(boolean contractRulereq) {
		this.contractRulereq = contractRulereq;
	}

	/**
	 * @return Returns the effectiveDatereq.
	 */
	public boolean isEffectiveDatereq() {
		return effectiveDatereq;
	}

	/**
	 * @param effectiveDatereq
	 *            The effectiveDatereq to set.
	 */
	public void setEffectiveDatereq(boolean effectiveDatereq) {
		this.effectiveDatereq = effectiveDatereq;
	}

	/**
	 * @return Returns the searchList.
	 */
	public List getSearchList() {
		return this.searchList;
	}

	/**
	 * @param searchList
	 *            The searchList to set.
	 */
	public void setSearchList(List searchList) {
		this.searchList = searchList;
	}

	/**
	 * @return Returns the contractvalidationCriteria.
	 */

	public String getContractvalidationCriteria() {
		return contractvalidationCriteria;
	}

	/**
	 * @param contractvalidationCriteria
	 *            The contractvalidationCriteria to set.
	 */
	public void setContractvalidationCriteria(String contractvalidationCriteria) {
		this.contractvalidationCriteria = contractvalidationCriteria;
	}
	
	/**
	 * 
	 * Method to get the Rule List
	 * @return
	 */
	public List getRuleSet() {
		ContractRulesetRequest rulesetRequest = (ContractRulesetRequest) this
				.getServiceRequest(ServiceManager.CONTRACT_RULE_SET_REQUEST);
		ContractRulesetResponse contractRulesetResponse = (ContractRulesetResponse) this
				.executeService(rulesetRequest);
		this.setSearchList(contractRulesetResponse.getSearchResults());
		return null;
	}

	public List validate() {
		return null;
	}
	/**
	 * Method for validating the Contract
	 * 
	 * @return
	 */
	public String validateContract() {

		if (isValid()) {

			if (!(StringUtil.isDate(this.effectiveDate.trim()))) {
				ErrorMessage errorMessage = new ErrorMessage(
						WebConstants.INPUT_FORMAT_INVALID);
				errorMessage.setParameters(new String[] { "Effective Date" });
				addMessageToRequest(errorMessage);
				this.setEffectiveDatereq(true);
				return "";
			}
			List contractIdList = WPDStringUtil.getListFromTildaString(
					this.contractIdCriteria, 2, 1, 2);
			int contractSysId = Integer
					.parseInt((String) contractIdList.get(0));
			Date effDate = WPDStringUtil.getDateFromString(this.effectiveDate
					.trim());
			List contractRuleList = WPDStringUtil.getListFromTildaString(
					this.contractvalidationCriteria, 2, 1, 2);
			List contractRuleValList = WPDStringUtil.getListFromTildaString(
					this.contractvalidationCriteria, 2, 2, 2);
			ContractRuleValidationRequest contractRuleValidationRequest = (ContractRuleValidationRequest) this
					.getServiceRequest(ServiceManager.CONTRACT_RULE_VALIDATION_REQUEST);
			contractRuleValidationRequest.setContractSysId(contractSysId);
			contractRuleValidationRequest.setEffDate(effDate);
			contractRuleValidationRequest.setContractRuleList(contractRuleList);

			ContractRuleValidationResponse contractRuleValidationResponse = (ContractRuleValidationResponse) this
					.executeService(contractRuleValidationRequest);
			if (contractRuleValidationResponse != null && contractRuleValidationResponse.isValid())
				// Setting the validation messages and success messages
				this.setSearchList(getAllMessages(contractRuleValList,
						contractRuleValidationResponse.getSearchResults()));
			if (!contractRuleValidationResponse.isValid()) {
				this.setContractIdreq(true);
				this.setEffectiveDatereq(true);

			}
		}

		return "";
	}

	/**
	 * @return Returns the contractIdCriteria.
	 */
	public String getContractIdCriteria() {
		return contractIdCriteria;
	}

	/**
	 * @param contractIdCriteria
	 *            The contractIdCriteria to set.
	 */
	public void setContractIdCriteria(String contractIdCriteria) {
		this.contractIdCriteria = contractIdCriteria;
	}

	/**
	 * @return Returns the effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate
	 *            The effectiveDate to set.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}

	/**
	 * @param contractId
	 *            The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	/**
	 * @return Returns the ruleCriteria.
	 */
	public String getRuleCriteria() {
		return ruleCriteria;
	}

	/**
	 * @param ruleCriteria
	 *            The ruleCriteria to set.
	 */
	public void setRuleCriteria(String ruleCriteria) {
		this.ruleCriteria = ruleCriteria;
	}
	/**
	 * 
	 * Method to validate the criteria
	 * @return
	 */
	public boolean isValid() {

		boolean valid = true;
		if (null == this.getContractIdCriteria()
				|| "".equals(this.getContractIdCriteria())) {
			this.setContractIdreq(true);
			valid = false;
		}
		if (null == this.getEffectiveDate()
				|| "".equals(this.getEffectiveDate())) {
			this.setEffectiveDatereq(true);
			valid = false;
		}
		if (null == this.getContractvalidationCriteria()
				|| "".equals(this.getContractvalidationCriteria())) {
			this.setContractRulereq(true);
			valid = false;
		}

		if (!valid) {
			addMessageToRequest(new ErrorMessage(
					WebConstants.CONTRACT_RULE_VALID));
		}
		return valid;
	}
	/**
	 * 
	 * Method to get the success Messages
	 * @param contractRuleValList
	 * @param searchresults
	 * @return
	 */
	private List getAllMessages(List contractRuleValList, List searchresults) {
		List results = new ArrayList();
		for (int i = 0; i < contractRuleValList.size(); i++) {
			boolean flg = false;
			String ruleVal = (String) contractRuleValList.get(i);
			if (searchresults != null && searchresults.size() > 0) {
				for (int j = 0; j < searchresults.size(); j++) {
					Message message = (Message) searchresults.get(j);
					if (ruleVal.equalsIgnoreCase(message.getRuleCategoryName())) {
						flg = true;
						results.add(message);
					}
				}
			}
			if (!flg) {
				Message message = new Message();
				message.setRuleCategoryName(ruleVal);
				message.setValidationMessage("The " + ruleVal + " "
						+ "is successful");
				results.add(message);
			}

		}
		return results;
	}
}