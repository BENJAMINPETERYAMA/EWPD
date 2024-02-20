/*
 * RetrieveRulesRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveRulesRequest extends ContractRequest{

	private int productSysId;
	private int action ;
	private int dateSegmentSysId;
	private String generatedId;
	private int ruleSysId;
	private List ruleList;
	private String ruleType;
	private String ruleId;
	private List ruleIdList;
	private List generatedRuleIdList;
	
	 public static final int ATTACH_RULES = 1;
	 public static final int RETRIEVE_RULES_POPUP = 2;
	 public static final int RETRIVE_RULES_COMPLETE = 3;
	 public static final int DELETE_RULES = 4;
    public static final int DATAFEED_RETRIEVE_RULES_COMPLETE = 5;
    public static final int UPDATE_RULES = 6;
    
    public static final int RETRIEVE_RULES_SEQUENCE = 7;
	
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * 
	 */
	public RetrieveRulesRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the productSysId.
	 */
	public int getProductSysId() {
		return productSysId;
	}
	/**
	 * @param productSysId The productSysId to set.
	 */
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	}
	/**
	 * @return Returns the ruleList.
	 */
	public List getRuleList() {
		return ruleList;
	}
	/**
	 * @param ruleList The ruleList to set.
	 */
	public void setRuleList(List ruleList) {
		this.ruleList = ruleList;
	}
	/**
	 * @return Returns the dateSegmentSysId.
	 */
	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}
	/**
	 * @param dateSegmentSysId The dateSegmentSysId to set.
	 */
	public void setDateSegmentSysId(int dateSegmentSysId) {
		this.dateSegmentSysId = dateSegmentSysId;
	}
	/**
	 * @return Returns the generatedId.
	 */
	public String getGeneratedId() {
		return generatedId;
	}
	/**
	 * @param generatedId The generatedId to set.
	 */
	public void setGeneratedId(String generatedId) {
		this.generatedId = generatedId;
	}
	/**
	 * @return Returns the ruleSysId.
	 */
	public int getRuleSysId() {
		return ruleSysId;
	}
	/**
	 * @param ruleSysId The ruleSysId to set.
	 */
	public void setRuleSysId(int ruleSysId) {
		this.ruleSysId = ruleSysId;
	}
    /**
     * @return ruleType
     * 
     * Returns the ruleType.
     */
    public String getRuleType() {
        return ruleType;
    }
    /**
     * @param ruleType
     * 
     * Sets the ruleType.
     */
    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }
	/**
	 * @return Returns the ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * @param ruleId The ruleId to set.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * @return Returns the generatedRuleIdList.
	 */
	public List getGeneratedRuleIdList() {
		return generatedRuleIdList;
	}
	/**
	 * @param generatedRuleIdList The generatedRuleIdList to set.
	 */
	public void setGeneratedRuleIdList(List generatedRuleIdList) {
		this.generatedRuleIdList = generatedRuleIdList;
	}
	/**
	 * @return Returns the ruleIdList.
	 */
	public List getRuleIdList() {
		return ruleIdList;
	}
	/**
	 * @param ruleIdList The ruleIdList to set.
	 */
	public void setRuleIdList(List ruleIdList) {
		this.ruleIdList = ruleIdList;
	}
}
