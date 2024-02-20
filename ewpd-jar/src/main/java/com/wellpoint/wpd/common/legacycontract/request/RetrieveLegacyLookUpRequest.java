/*
 * Created on Aug 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.legacycontract.request;

import com.wellpoint.wpd.common.legacycontract.vo.LegacyVariableMappingVO;
import com.wellpoint.wpd.common.migration.request.MigrationContractRequest;

/**
 * @author U11085
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveLegacyLookUpRequest extends MigrationContractRequest{
	
	
	private int action ;
	private LegacyVariableMappingVO legacyVariableMappingVO;
	
	public static final int RETRIEVE_LEGACY_VARIABLE_MAPPING_DATA = 1;
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
	 * @return Returns the legacyVariableMappingVO.
	 */
	public LegacyVariableMappingVO getLegacyVariableMappingVO() {
		return legacyVariableMappingVO;
	}
	/**
	 * @param legacyVariableMappingVO The legacyVariableMappingVO to set.
	 */
	public void setLegacyVariableMappingVO(
			LegacyVariableMappingVO legacyVariableMappingVO) {
		this.legacyVariableMappingVO = legacyVariableMappingVO;
	}
	
}
