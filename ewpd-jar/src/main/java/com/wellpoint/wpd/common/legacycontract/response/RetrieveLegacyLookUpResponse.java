/*
 * Created on Aug 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.legacycontract.response;

import java.util.List;

import com.wellpoint.wpd.common.migration.response.MigrationContractResponse;

/**
 * @author U11085
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveLegacyLookUpResponse extends MigrationContractResponse{
	
	private List variableMappingList;
	

	/**
	 * @return Returns the variableMappingList.
	 */
	public List getVariableMappingList() {
		return variableMappingList;
	}
	/**
	 * @param variableMappingList The variableMappingList to set.
	 */
	public void setVariableMappingList(List variableMappingList) {
		this.variableMappingList = variableMappingList;
	}
}
