/*
 * Created on May 25, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author u14768
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveContractBenefitNoteRequest extends ContractRequest {
	
	 private int benefitComponentId;
	 private List noteList;
	 private int action;
	 private int BenefitId;
	 private List versionList;
	 private String overrideStatus;
	 private int benefitId;
	 
	 public static final int SAVE_NOTE = 1;
	 public static final int OVERRIDE_NOTE = 2;
	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the noteList.
	 */
	public List getNoteList() {
		return noteList;
	}
	/**
	 * @param noteList The noteList to set.
	 */
	public void setNoteList(List noteList) {
		this.noteList = noteList;
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
	/**
	 * @return Returns the benefitId.
	 */
	public int getBenefitId() {
		return BenefitId;
	}
	/**
	 * @param benefitId The benefitId to set.
	 */
	public void setBenefitId(int benefitId) {
		BenefitId = benefitId;
	}
	/**
	 * @return Returns the versionList.
	 */
	public List getVersionList() {
		return versionList;
	}
	/**
	 * @param versionList The versionList to set.
	 */
	public void setVersionList(List versionList) {
		this.versionList = versionList;
	}
	/**
	 * @return Returns the overrideStatus.
	 */
	public String getOverrideStatus() {
		return overrideStatus;
	}
	/**
	 * @param overrideStatus The overrideStatus to set.
	 */
	public void setOverrideStatus(String overrideStatus) {
		this.overrideStatus = overrideStatus;
	}
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
}
