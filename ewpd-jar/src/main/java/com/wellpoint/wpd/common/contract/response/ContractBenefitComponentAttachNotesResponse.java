/*
 * BenefitComponentAttachNotesResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractBenefitComponentAttachNotesResponse  extends ContractResponse  {
    private AttachedNotesBO attachedNotesBO;
	
	private List notesList;
	
	private List contractBenefitComponentNotesList = new ArrayList();
	
	

    /**
     * Returns the contractBenefitComponentNotesList
     * @return List contractBenefitComponentNotesList.
     */
    public List getContractBenefitComponentNotesList() {
        return contractBenefitComponentNotesList;
    }
    /**
     * Sets the contractBenefitComponentNotesList
     * @param contractBenefitComponentNotesList.
     */
    public void setContractBenefitComponentNotesList(
            List contractBenefitComponentNotesList) {
        this.contractBenefitComponentNotesList = contractBenefitComponentNotesList;
    }
    /**
     * Returns the attachedNotesBO
     * @return AttachedNotesBO attachedNotesBO.
     */
    public AttachedNotesBO getAttachedNotesBO() {
        return attachedNotesBO;
    }
    /**
     * Sets the attachedNotesBO
     * @param attachedNotesBO.
     */
    public void setAttachedNotesBO(AttachedNotesBO attachedNotesBO) {
        this.attachedNotesBO = attachedNotesBO;
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
}
