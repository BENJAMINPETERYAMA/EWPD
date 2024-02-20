/*
 * StandardBenefitNoteAttachmentResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractNoteAttachmentResponse extends WPDResponse {
    
    private Contract contract;
	
	List noteList;
	
    /**
     * @return noteList
     * 
     * Returns the noteList.
     */
    public List getNoteList() {
        return noteList;
    }
    /**
     * @param noteList
     * 
     * Sets the noteList.
     */
    public void setNoteList(List noteList) {
        this.noteList = noteList;
    }
	
	/**
	 * @return Returns the contract.
	 */
	public Contract getContract() {
		return contract;
	}
	/**
	 * @param contract The contract to set.
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}
}
