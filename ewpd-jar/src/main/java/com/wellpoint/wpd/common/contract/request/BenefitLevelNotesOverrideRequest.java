/*
 * BenefitLevelNotesOverrideRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.notes.vo.NotesAttachmentOverrideVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLevelNotesOverrideRequest extends ContractRequest{
    
    
    private List notesList = null;
    
    private int benefitLevelId;
    
    
    private ContractVO contractVO;    
    
    private NotesAttachmentOverrideVO overrideVO;
    
    
    // For benefitComponentOverride Notes
    private BenefitComponentVO benefitComponentVO ;
    
    private int stdBenefitId ;
    
    private String action ;
    
    
    
    
    
 
    /**
     * Returns the overrideVO
     * @return NotesAttachmentOverrideVO overrideVO.
     */
    public NotesAttachmentOverrideVO getOverrideVO() {
        return overrideVO;
    }
    /**
     * Sets the overrideVO
     * @param overrideVO.
     */
    public void setOverrideVO(NotesAttachmentOverrideVO overrideVO) {
        this.overrideVO = overrideVO;
    }
    /**
     * Returns the contractVO
     * @return ContractVO contractVO.
     */
    public ContractVO getContractVO() {
        return contractVO;
    }
    /**
     * Sets the contractVO
     * @param contractVO.
     */
    public void setContractVO(ContractVO contractVO) {
        this.contractVO = contractVO;
    }
    /**
     * Returns the notesList
     * @return List notesList.
     */
    public List getNotesList() {
        return notesList;
    }
    /**
     * Sets the notesList
     * @param notesList.
     */
    public void setNotesList(List notesList) {
        this.notesList = notesList;
    }
    
    
   
    /**
     * Returns the benefitLevelId
     * @return int benefitLevelId.
     */
    public int getBenefitLevelId() {
        return benefitLevelId;
    }
    /**
     * Sets the benefitLevelId
     * @param benefitLevelId.
     */
    public void setBenefitLevelId(int benefitLevelId) {
        this.benefitLevelId = benefitLevelId;
    }
    
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }
    

	/**
	 * @return Returns the benefitComponentVO.
	 */
	public BenefitComponentVO getBenefitComponentVO() {
		return benefitComponentVO;
	}
	/**
	 * @param benefitComponentVO The benefitComponentVO to set.
	 */
	public void setBenefitComponentVO(BenefitComponentVO benefitComponentVO) {
		this.benefitComponentVO = benefitComponentVO;
	}
	/**
	 * @return Returns the stdBenefitId.
	 */
	public int getStdBenefitId() {
		return stdBenefitId;
	}
	/**
	 * @param stdBenefitId The stdBenefitId to set.
	 */
	public void setStdBenefitId(int stdBenefitId) {
		this.stdBenefitId = stdBenefitId;
	}
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
}
