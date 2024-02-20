/*
 * Created on Apr 8, 2008
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
public class SaveMigrationNotesRequest extends MigrationContractRequest{
	
	private int action ;
	private List noteMigrationList;
	public static final int SAVE_MIGRATION_NOTES = 1;

	/**
	 * @return Returns the noteMigrationList.
	 */
	public List getNoteMigrationList() {
		return noteMigrationList;
	}
	/**
	 * @param noteMigrationList The noteMigrationList to set.
	 */
	public void setNoteMigrationList(List noteMigrationList) {
		this.noteMigrationList = noteMigrationList;
	}
	
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
}
