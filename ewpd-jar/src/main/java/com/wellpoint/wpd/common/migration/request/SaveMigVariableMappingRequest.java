/*
 * Created on Aug 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.request;

import java.util.List;

/**
 * @author U11085
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveMigVariableMappingRequest extends MigrationContractRequest{
	private int action ;
	private List variableMappingList;
	public static final int SAVE_MIG_VARIABLE_MAPPING_DATA = 1;
	public static final int DELETE_MIG_VARIABLE_MAPPING_DATA = 2;
	public static final int UPDATE_STEP_COMPLETED = 3;

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
