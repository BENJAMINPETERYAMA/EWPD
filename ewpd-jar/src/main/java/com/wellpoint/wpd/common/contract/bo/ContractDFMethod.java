package com.wellpoint.wpd.common.contract.bo;

public class ContractDFMethod {
	public static final int NON_TIERED_METHOD = 1;
	public static final int TIERED_METHOD = 2;
	
	private int dateSegmentId;
	private int type;
	private int rowNumber;
	private String prodFamily;
	private int componentSysId;
	private int benefitSysId;
	private int benefitDefnSysId;
	private int benefitAdmnSysId;
	private int contractTierSysId;
	private String tierCode;
	private int adminMethodNo;
	// Super Process Step Name
	private String spsDescription;
	private String spsId;
	private int tierIndicator;
	private String tierTypeIndicator;
	
	private String contractProdFamily;

	public int getContractTierSysId() {
		return contractTierSysId;
	}
	public void setContractTierSysId(int contractTierSysId) {
		this.contractTierSysId = contractTierSysId;
		if(contractTierSysId == 0) {
			this.type =  NON_TIERED_METHOD;
		}else {
			this.type = TIERED_METHOD;
		}
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getProdFamily() {
		return prodFamily;
	}
	public void setProdFamily(String prodFamily) {
		this.prodFamily = prodFamily;
	}
	public int getComponentSysId() {
		return componentSysId;
	}
	public void setComponentSysId(int componentSysId) {
		this.componentSysId = componentSysId;
	}
	public int getBenefitSysId() {
		return benefitSysId;
	}
	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
	}
	public int getBenefitDefnSysId() {
		return benefitDefnSysId;
	}
	public void setBenefitDefnSysId(int benefitDefnSysId) {
		this.benefitDefnSysId = benefitDefnSysId;
	}
	public int getBenefitAdmnSysId() {
		return benefitAdmnSysId;
	}
	public void setBenefitAdmnSysId(int benefitAdmnSysId) {
		this.benefitAdmnSysId = benefitAdmnSysId;
	}
	public String getTierCode() {
		return tierCode;
	}
	public void setTierCode(String tierCode) {
		this.tierCode = tierCode;
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public String getSpsId() {
		return spsId;
	}
	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}
	public int getAdminMethodNo() {
		return adminMethodNo;
	}
	public void setAdminMethodNo(int adminMethodNo) {
		this.adminMethodNo = adminMethodNo;
	}

	public int getDateSegmentId() {
		return dateSegmentId;
	}
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	public String getTierTypeIndicator() {
		return tierTypeIndicator;
	}
	public void setTierTypeIndicator(String tierTypeIndicator) {
		this.tierTypeIndicator = tierTypeIndicator;
	}
	public int getTierIndicator() {
		return tierIndicator;
	}
	public void setTierIndicator(int tierIndicator) {
		this.tierIndicator = tierIndicator;
	}
	public String getSpsDescription() {
		return spsDescription;
	}
	public void setSpsDescription(String spsDescription) {
		this.spsDescription = spsDescription;
	}
	public String getContractProdFamily() {
		return contractProdFamily;
	}
	public void setContractProdFamily(String contractProdFamily) {
		this.contractProdFamily = contractProdFamily;
	}
}
