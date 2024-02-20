/*
 * Created on Aug 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.request;


import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyObject;
import com.wellpoint.wpd.common.migration.vo.MigrationContractSession;

/**
 * @author U11085
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MigrationContractRequest extends WPDRequest{

	private MigrationContractSession migrationContractSession;
	private LegacyObject legacyObject;
	
	
	/**
	 * @return Returns the legacyObject.
	 */
	public LegacyObject getLegacyObject() {
		return legacyObject;
	}
	/**
	 * @param legacyObject The legacyObject to set.
	 */
	public void setLegacyObject(LegacyObject legacyObject) {
		this.legacyObject = legacyObject;
	}
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
	}
	/**
	 * @return Returns the migrationContractSession.
	 */
	public MigrationContractSession getMigrationContractSession() {
		return migrationContractSession;
	}
	/**
	 * @param migrationContractSession The migrationContractSession to set.
	 */
	public void setMigrationContractSession(
			MigrationContractSession migrationContractSession) {
		this.migrationContractSession = migrationContractSession;
	}
}
