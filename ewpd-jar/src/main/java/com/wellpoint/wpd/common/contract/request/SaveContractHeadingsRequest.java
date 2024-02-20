/*
 * Created on Aug 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author u13657
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveContractHeadingsRequest extends ContractRequest{
	
	 /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
    
    
    private List selectedBenefitLineList;
    
    private int productId;
    
    private int sourceDateSegmentId;
    
    private int targetDateSegmentId;
    
    private int sourceContractSysId;
    
    private int targetContractSysId;
    
    private String sourceContractId;
    
    private String targetContractId;
    
    private int sourceVersion;
    
    private String effectiveDate;
    
    

	/**
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return Returns the selectedBenefitLineList.
	 */
	public List getSelectedBenefitLineList() {
		return selectedBenefitLineList;
	}
	/**
	 * @param selectedBenefitLineList The selectedBenefitLineList to set.
	 */
	public void setSelectedBenefitLineList(List selectedBenefitLineList) {
		this.selectedBenefitLineList = selectedBenefitLineList;
	}
	/**
	 * @return Returns the sourceDateSegmentId.
	 */
	public int getSourceDateSegmentId() {
		return sourceDateSegmentId;
	}
	/**
	 * @param sourceDateSegmentId The sourceDateSegmentId to set.
	 */
	public void setSourceDateSegmentId(int sourceDateSegmentId) {
		this.sourceDateSegmentId = sourceDateSegmentId;
	}
	/**
	 * @return Returns the targetDateSegmentId.
	 */
	public int getTargetDateSegmentId() {
		return targetDateSegmentId;
	}
	/**
	 * @param targetDateSegmentId The targetDateSegmentId to set.
	 */
	public void setTargetDateSegmentId(int targetDateSegmentId) {
		this.targetDateSegmentId = targetDateSegmentId;
	}
	/**
	 * @return Returns the sourceContractSysId.
	 */
	public int getSourceContractSysId() {
		return sourceContractSysId;
	}
	/**
	 * @param sourceContractSysId The sourceContractSysId to set.
	 */
	public void setSourceContractSysId(int sourceContractSysId) {
		this.sourceContractSysId = sourceContractSysId;
	}
	/**
	 * @return Returns the targetContractSysId.
	 */
	public int getTargetContractSysId() {
		return targetContractSysId;
	}
	/**
	 * @param targetContractSysId The targetContractSysId to set.
	 */
	public void setTargetContractSysId(int targetContractSysId) {
		this.targetContractSysId = targetContractSysId;
	}
	/**
	 * @return Returns the sourceContractId.
	 */
	public String getSourceContractId() {
		return sourceContractId;
	}
	/**
	 * @param sourceContractId The sourceContractId to set.
	 */
	public void setSourceContractId(String sourceContractId) {
		this.sourceContractId = sourceContractId;
	}
	/**
	 * @return Returns the targetContractId.
	 */
	public String getTargetContractId() {
		return targetContractId;
	}
	/**
	 * @param targetContractId The targetContractId to set.
	 */
	public void setTargetContractId(String targetContractId) {
		this.targetContractId = targetContractId;
	}
	/**
	 * @return Returns the sourceVersion.
	 */
	public int getSourceVersion() {
		return sourceVersion;
	}
	/**
	 * @param sourceVersion The sourceVersion to set.
	 */
	public void setSourceVersion(int sourceVersion) {
		this.sourceVersion = sourceVersion;
	}
	/**
	 * @return Returns the effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}
