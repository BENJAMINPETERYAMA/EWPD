/*
 * RetrieveReservedContractIdRequest.java
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
public class RetrieveSystemPoolIdRequest extends ContractRequest{

	public static final int OPTION_ONE = 1;
	public static final int OPTION_TWO = 2;
	public static final int OPTION_THREE = 3;
    
    private String fromContractId;
    
    private String endContractId; 
    
    private String startContractId;
    
    private int noOfIdsToGenerate_Start;
    
    private String nextAvailableId;
    
    private int noOfIdsToGenerate_Next;
    
    private boolean isContinuous = true;
    
    private List businessDomains;
    
    private String comments;
    
    private String startDate;
    
	private String endDate;
	
	private int action;
	
	   public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the endContractId.
	 */
	public String getEndContractId() {
		return endContractId;
	}
	/**
	 * @param endContractId The endContractId to set.
	 */
	public void setEndContractId(String endContractId) {
		this.endContractId = endContractId;
	}
	/**
	 * @return Returns the fromContractId.
	 */
	public String getFromContractId() {
		return fromContractId;
	}
	/**
	 * @param fromContractId The fromContractId to set.
	 */
	public void setFromContractId(String fromContractId) {
		this.fromContractId = fromContractId;
	}
	/**
	 * @return Returns the nextAvailableId.
	 */
	public String getNextAvailableId() {
		return nextAvailableId;
	}
	/**
	 * @param nextAvailableId The nextAvailableId to set.
	 */
	public void setNextAvailableId(String nextAvailableId) {
		this.nextAvailableId = nextAvailableId;
	}
	
	/**
	 * @return Returns the noOfIdsToGenerate_Next.
	 */
	public int getNoOfIdsToGenerate_Next() {
		return noOfIdsToGenerate_Next;
	}
	/**
	 * @param noOfIdsToGenerate_Next The noOfIdsToGenerate_Next to set.
	 */
	public void setNoOfIdsToGenerate_Next(int noOfIdsToGenerate_Next) {
		this.noOfIdsToGenerate_Next = noOfIdsToGenerate_Next;
	}
	/**
	 * @return Returns the noOfIdsToGenerate_Start.
	 */
	public int getNoOfIdsToGenerate_Start() {
		return noOfIdsToGenerate_Start;
	}
	/**
	 * @param noOfIdsToGenerate_Start The noOfIdsToGenerate_Start to set.
	 */
	public void setNoOfIdsToGenerate_Start(int noOfIdsToGenerate_Start) {
		this.noOfIdsToGenerate_Start = noOfIdsToGenerate_Start;
	}
	/**
	 * @return Returns the startContractId.
	 */
	public String getStartContractId() {
		return startContractId;
	}
	/**
	 * @param startContractId The startContractId to set.
	 */
	public void setStartContractId(String startContractId) {
		this.startContractId = startContractId;
	}
	
	
	/**
	 * @return Returns the isContinuous.
	 */
	public boolean isContinuous() {
		return isContinuous;
	}
	/**
	 * @param isContinuous The isContinuous to set.
	 */
	public void setContinuous(boolean isContinuous) {
		this.isContinuous = isContinuous;
	}
	
	
	/**
	 * @return Returns the businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}
	/**
	 * @param businessDomains The businessDomains to set.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}
	
	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the endDate.
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
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
}
