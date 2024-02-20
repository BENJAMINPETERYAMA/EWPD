/*
 * Created on Aug 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.request;

import com.wellpoint.wpd.common.migration.vo.MigProductMappingVO;

/**
 * @author U11085
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveMigVariableMappingRequest extends MigrationContractRequest{
	private int action ;
	
	
	private MigProductMappingVO migProductMappingVO;	

	public static final int RETRIEVE_MIG_VARIABLE_MAPPING_DATA = 1;
	public static final int RETRIEVE_CONFLICTING_DATA = 2;
		
	

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
	 * @return Returns the migProductMappingVO.
	 */
	public MigProductMappingVO getMigProductMappingVO() {
		return migProductMappingVO;
	}
	/**
	 * @param migProductMappingVO The migProductMappingVO to set.
	 */
	public void setMigProductMappingVO(MigProductMappingVO migProductMappingVO) {
		this.migProductMappingVO = migProductMappingVO;
	}
}
