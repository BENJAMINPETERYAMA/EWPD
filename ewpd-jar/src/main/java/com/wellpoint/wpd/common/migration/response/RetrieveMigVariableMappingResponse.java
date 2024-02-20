/*
 * Created on Aug 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.response;

import java.util.List;

/**
 * @author U11085
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveMigVariableMappingResponse extends MigrationContractResponse{
	
	private List benefitLineList;

	private List conflictRecordList;
	/**
	 * @return Returns the conflictRecordList.
	 */
	public List getConflictRecordList() {
		return conflictRecordList;
	}
	/**
	 * @param conflictRecordList The conflictRecordList to set.
	 */
	public void setConflictRecordList(List conflictRecordList) {
		this.conflictRecordList = conflictRecordList;
	}
	/**
	 * @return Returns the benefitLineList.
	 */
	public List getBenefitLineList() {
		return benefitLineList;
	}
	/**
	 * @param benefitLineList The benefitLineList to set.
	 */
	public void setBenefitLineList(List benefitLineList) {
		this.benefitLineList = benefitLineList;
	}
}
