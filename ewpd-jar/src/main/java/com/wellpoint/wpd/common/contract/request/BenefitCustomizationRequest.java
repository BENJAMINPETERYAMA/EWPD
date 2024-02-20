/*
 * Created on Dec 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author U14609
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitCustomizationRequest extends ContractRequest{
	
	private List benefitList;
	
	private boolean showHidden;
	
	private int benefitComponentId;
	
	private int dateSegmentId;
	
	private String benefitComponentDesc;
	
	private boolean changed;
	
	private List changedIds;
	
	private Map benefitHiddenFlagMap=new HashMap();
	
	
	/**
	 * @return Returns the benefitList.
	 */
	public List getBenefitList() {
		return benefitList;
	}
	/**
	 * @param benefitList The benefitList to set.
	 */
	public void setBenefitList(List benefitList) {
		this.benefitList = benefitList;
	}
	
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * @return Returns the showHidden.
	 */
	public boolean isShowHidden() {
		return showHidden;
	}
	/**
	 * @param showHidden The showHidden to set.
	 */
	public void setShowHidden(boolean showHidden) {
		this.showHidden = showHidden;
	}
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
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * @return Returns the benefitComponentDesc.
	 */
	public String getBenefitComponentDesc() {
		return benefitComponentDesc;
	}
	/**
	 * @param benefitComponentDesc The benefitComponentDesc to set.
	 */
	public void setBenefitComponentDesc(String benefitComponentDesc) {
		this.benefitComponentDesc = benefitComponentDesc;
	}
	/**
	 * @return Returns the changed.
	 */
	public boolean isChanged() {
		return changed;
	}
	/**
	 * @param changed The changed to set.
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	/**
	 * @return Returns the changedIds.
	 */
	public List getChangedIds() {
		return changedIds;
	}
	/**
	 * @param changedIds The changedIds to set.
	 */
	public void setChangedIds(List changedIds) {
		this.changedIds = changedIds;
	}
	public Map getBenefitHiddenFlagMap() {
		return benefitHiddenFlagMap;
	}
	public void setBenefitHiddenFlagMap(Map benefitHiddenFlagMap) {
		this.benefitHiddenFlagMap = benefitHiddenFlagMap;
	}
	
	
}
