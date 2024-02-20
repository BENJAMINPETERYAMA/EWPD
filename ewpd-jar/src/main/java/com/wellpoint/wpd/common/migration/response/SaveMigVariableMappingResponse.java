/*
 * Created on Aug 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.response;

/**
 * @author U11085
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveMigVariableMappingResponse extends MigrationContractResponse{
	
	private boolean isSuccess;

	/**
	 * @return Returns the isSuccess.
	 */
	public boolean isSuccess() {
		return isSuccess;
	}
	/**
	 * @param isSuccess The isSuccess to set.
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
}
