package com.wellpoint.ets.ebx.simulation.vo;

import java.util.ArrayList;
import java.util.List;

public class ContractReportVO {
	
	 private String  contractId = "";
	 
	 private String revisionDate = "";
	 
	 private String effectiveDate = "";
	 
	 private String revisionDateHeader = "";
	 
	 private List contractReportList = new ArrayList();

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(String revisionDate) {
		this.revisionDate = revisionDate;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public List getContractReportList() {
		return contractReportList;
	}

	public void setContractReportList(List contractReportList) {
		this.contractReportList = contractReportList;
	}

	public String getRevisionDateHeader() {
		return revisionDateHeader;
	}

	public void setRevisionDateHeader(String revisionDateHeader) {
		this.revisionDateHeader = revisionDateHeader;
	}
}
