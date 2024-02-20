package com.wellpoint.ets.ebx.referencedata.vo;

import java.util.List;

/**
 * @author u23675
 *
 */
public class ServiceTypeMappingVO {

	private String lobId;
	private String lineOfBusiness;
	private String commaSeperatedMbu;
	private String ssbIndicator;
	private List<ServiceTypeCodeToEB11VO> serviceTypeCodeToEB11VOList;
	
	private String operationMessages;
	private String lastUpdatedUser;
	
	private List validationMessageList;
	private String action;
	
	private String userComments;
	
	
	public String getLobId() {
		return lobId;
	}
	public void setLobId(String lobId) {
		this.lobId = lobId;
	}
	public String getLineOfBusiness() {
		return lineOfBusiness;
	}
	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}
	public String getCommaSeperatedMbu() {
		return commaSeperatedMbu;
	}
	public void setCommaSeperatedMbu(String commaSeperatedMbu) {
		this.commaSeperatedMbu = commaSeperatedMbu;
	}
	public void setServiceTypeCodeToEB11VOList(
			List<ServiceTypeCodeToEB11VO> serviceTypeCodeToEB11VOList) {
		this.serviceTypeCodeToEB11VOList = serviceTypeCodeToEB11VOList;
	}
	public List<ServiceTypeCodeToEB11VO> getServiceTypeCodeToEB11VOList() {
		return serviceTypeCodeToEB11VOList;
	}
	
	public String getSsbIndicator() {
		return ssbIndicator;
	}
	public void setSsbIndicator(String ssbIndicator) {
		this.ssbIndicator = ssbIndicator;
	}
	public String getOperationMessages() {
		return operationMessages;
	}
	public void setOperationMessages(String operationMessages) {
		this.operationMessages = operationMessages;
	}
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	public List getValidationMessageList() {
		return validationMessageList;
	}
	public void setValidationMessageList(List validationMessageList) {
		this.validationMessageList = validationMessageList;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getUserComments() {
		return userComments;
	}
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}
	
	
}
