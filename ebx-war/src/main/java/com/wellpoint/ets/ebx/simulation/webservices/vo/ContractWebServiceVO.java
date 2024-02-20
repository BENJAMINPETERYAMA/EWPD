package com.wellpoint.ets.ebx.simulation.webservices.vo;

import java.util.List;
import java.util.Map;

public class ContractWebServiceVO { 

	private String contractId;
	private String system;
	private String effectiveDate;
	private String expiryDate;
	private int version;
	private String businessEntity;
	private String groupStateHQ;
	private String backEndRegion;
	
	private List<ErrorDetailWebServiceVO> errorCodesList;
	
	private ErrorExclusionDetailWebServiceVO errorExclusionDetailVO;
	
	private List<ErrorExclusionWebServiceVO> errorExclusionList;
	
	private List<ContractMappingWebServiceVO> contractMappingVOList;
	
	private Map<String,MajorHeadingsWebServiceVO> majorHeadings;
	
	/**
	 * @return the backEndRegion
	 */
	public String getBackEndRegion() {
		return backEndRegion;
	}
	/**
	 * @param backEndRegion the backEndRegion to set
	 */
	public void setBackEndRegion(String backEndRegion) {
		this.backEndRegion = backEndRegion;
	}
	/**
	 * @return the errorExclusionList
	 */
	public List<ErrorExclusionWebServiceVO> getErrorExclusionList() {
		return errorExclusionList;
	}
	/**
	 * @param errorExclusionList the errorExclusionList to set
	 */
	public void setErrorExclusionList(
			List<ErrorExclusionWebServiceVO> errorExclusionList) {
		this.errorExclusionList = errorExclusionList;
	}
	public ErrorExclusionDetailWebServiceVO getErrorExclusionDetailVO() {
		return errorExclusionDetailVO;
	}
	public void setErrorExclusionDetailVO(
			ErrorExclusionDetailWebServiceVO errorExclusionDetailVO) {
		this.errorExclusionDetailVO = errorExclusionDetailVO;
	}
	public List<ErrorExclusionWebServiceVO> getErrorExclusionDetailList() {
		return errorExclusionList;
	}
	public void setErrorExclusionDetailList(
			List<ErrorExclusionWebServiceVO> errorExclusionList) {
		this.errorExclusionList = errorExclusionList;
	}
	public List<ContractMappingWebServiceVO> getContractMappingVOList() {
		return contractMappingVOList;
	}
	public void setContractMappingVOList(
			List<ContractMappingWebServiceVO> contractMappingVOList) {
		this.contractMappingVOList = contractMappingVOList;
	}
	public Map<String, MajorHeadingsWebServiceVO> getMajorHeadings() {
		return majorHeadings;
	}
	public void setMajorHeadings(
			Map<String, MajorHeadingsWebServiceVO> majorHeadings) {
		this.majorHeadings = majorHeadings;
	}
	public List<ErrorDetailWebServiceVO> getErrorCodesList() {
		return errorCodesList;
	}
	public void setErrorCodesList(List<ErrorDetailWebServiceVO> errorCodesList) {
		this.errorCodesList = errorCodesList;
	}
	public String getGroupStateHQ() {
		return groupStateHQ;
	}
	public void setGroupStateHQ(String groupStateHQ) {
		this.groupStateHQ = groupStateHQ;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getBusinessEntity() {
		return businessEntity;
	}
	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}

	
}
