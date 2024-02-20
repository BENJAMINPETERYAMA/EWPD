/*
 * Created on Apr 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.legacycontract.request;

import com.wellpoint.wpd.common.legacycontract.vo.LegacyContractNotesVO;
import com.wellpoint.wpd.common.migration.request.MigrationContractRequest;

/**
 * @author U13083
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveLegacyContarctNotesRequest extends MigrationContractRequest  {

	LegacyContractNotesVO legacyContractNotesVO;
	
	public int action;
	
	public static final int LEGACY_RETRIEVE_CONTRACT_NOTES =1;
	
	public static final int LEGACY_RETRIEVE_MAJOR_HEADING = 2;
	
	public static final int LEGACY_RETRIEVE_MINOR_HEADING = 3;
	
	public static final int LEGACY_RETRIEVE_MAJOR_NOTES = 4;
	
	public static final int LEGACY_RETRIEVE_MINOR_NOTES= 5;
	
	public static final int LEGACY_RETRIEVE_ALL_MAJOR_HEADING =6;
	
	public static final int LEGACY_OLD_MINOR_HEADING = 7;
	
	/**
	 * @return Returns the legacyContractNotesVO.
	 */
	public LegacyContractNotesVO getLegacyContractNotesVO() {
		return legacyContractNotesVO;
	}
	/**
	 * @param legacyContractNotesVO The legacyContractNotesVO to set.
	 */
	public void setLegacyContractNotesVO(
			LegacyContractNotesVO legacyContractNotesVO) {
		this.legacyContractNotesVO = legacyContractNotesVO;
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
