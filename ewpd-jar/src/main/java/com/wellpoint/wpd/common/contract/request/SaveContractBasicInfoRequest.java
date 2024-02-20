/*
 * SaveContractBasicInfoRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.request;

import java.util.List;

import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveContractBasicInfoRequest extends ContractRequest {
    
    public static final int CREATE_CONTRACT = 1;
    public static final int UPDATE_CONTRACT = 2;
    public static final int CHECKIN_CONTRACT = 3;
    public static final int UPDATE_CHECKIN_CONTRACT = 22;
    public static final int SEND_TO_TEST = 4;
    public static final int TEST_PASS_CONTRACT = 5;
    public static final int TEST_FAIL_CONTRACT = 6;
    public static final int SCHEDULE_TO_APPROVE = 7;
    public static final int APPROVE_CONTRACT = 8;
    public static final int REJECT_CONTRACT = 9;
    public static final int CHECKOUT_CONTRACT = 10;
    public static final int PUBLISH_CONTRACT = 11;
    public static final int SCHEDULE_FOR_PRODUCTION = 12;
    public static final int COPY_CONTRACT = 13;
    public static final int COPYTEST = 14;
    public static final int CHECKOUT_PROCESS = 17;
    public static final int CHECKOUT_PROCESS_CONT = 16;
    public static final int COPY_HEADINGS_CONTRACT = 15;
    public static final int SEND_TO_TEST_PROCESS = 18;
    public static final int COPY_PROCESS = 19;
    public static final int COPY_PROCESS_CONT = 20;
    public static final int UNLOCK_CONTRACT =21;
    
    private int action;

    private boolean isChechIn;

    private ContractVO contractVO;
    
    private int baseDateIdForCopy;
    
    private int dateSegmentIdForCopy;

    
    private List selectedLineList;
    
    private List unSelectedLineList;
   
    private int productSysId;
    
    private String productStatusForCopy;
    
    private String noteStatusForCopy;
	
    private boolean requestFromMigrationWizard;
    
    private boolean copyLegacyNotes;
    
    private Contract contract;
    //sscr 17571
    private String carvedConfirm;
    
    private boolean carvConfirm=false;
    
    public String getCarvedConfirm() {
		return carvedConfirm;
	}
	public void setCarvedConfirm(String carvedConfirm) {
		this.carvedConfirm = carvedConfirm;
	}
	public boolean isCarvConfirm() {
		return carvConfirm;
	}
	public void setCarvConfirm(boolean carvConfirm) {
		this.carvConfirm = carvConfirm;
	}
    
    //sscr 17571-End
    
	private boolean eBXWS =false;
    
    private String ebxAndCarvErrorsByPassCmts;
    
	public boolean isEBXWS() {
		return eBXWS;
	}
	public void setEBXWS(boolean ebxws) {
		eBXWS = ebxws;
	}
	public String getEbxAndCarvErrorsByPassCmts() {
		return ebxAndCarvErrorsByPassCmts;
	}
	public void setEbxAndCarvErrorsByPassCmts(String ebxAndCarvErrorsByPassCmts) {
		this.ebxAndCarvErrorsByPassCmts = ebxAndCarvErrorsByPassCmts;
	}
	
	/**
	 * @return the contract
	 */
	public Contract getContract() {
		return contract;
	}
	/**
	 * @param contract the contract to set
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	/**
	 * @return Returns the productSysId.
	 */
	public int getProductSysId() {
		return productSysId;
	}
	/**
	 * @param productSysId The productSysId to set.
	 */
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	} 
    
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

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
     * Returns the action
     * @return int action.
     */
    public int getAction() {
        return action;
    }
    /**
     * Sets the action
     * @param action.
     */
    public void setAction(int action) {
        this.action = action;
    }
	/**
	 * Returns the isChechIn
	 * @return boolean isChechIn.
	 */
	public boolean isChechIn() {
		return isChechIn;
	}
	/**
	 * Sets the isChechIn
	 * @param isChechIn.
	 */
	public void setChechIn(boolean isChechIn) {
		this.isChechIn = isChechIn;
	}
    /**
     * Returns the baseDateIdForCopy
     * @return int baseDateIdForCopy.
     */
    public int getBaseDateIdForCopy() {
        return baseDateIdForCopy;
    }
    /**
     * Sets the baseDateIdForCopy
     * @param baseDateIdForCopy.
     */
    public void setBaseDateIdForCopy(int baseDateIdForCopy) {
        this.baseDateIdForCopy = baseDateIdForCopy;
    }
    /**
     * Returns the dateSegmentIdForCopy
     * @return int dateSegmentIdForCopy.
     */
    public int getDateSegmentIdForCopy() {
        return dateSegmentIdForCopy;
    }
    /**
     * Sets the dateSegmentIdForCopy
     * @param dateSegmentIdForCopy.
     */
    public void setDateSegmentIdForCopy(int dateSegmentIdForCopy) {
        this.dateSegmentIdForCopy = dateSegmentIdForCopy;
    }

	/**
	 * @return Returns the selectedLineList.
	 */
	public List getSelectedLineList() {
		return selectedLineList;
	}
	/**
	 * @param selectedLineList The selectedLineList to set.
	 */
	public void setSelectedLineList(List selectedLineList) {
		this.selectedLineList = selectedLineList;
	}

    /**
     * @return noteStatusForCopy
     * 
     * Returns the noteStatusForCopy.
     */
    public String getNoteStatusForCopy() {
        return noteStatusForCopy;
    }
    /**
     * @param noteStatusForCopy
     * 
     * Sets the noteStatusForCopy.
     */
    public void setNoteStatusForCopy(String noteStatusForCopy) {
        this.noteStatusForCopy = noteStatusForCopy;
    }
    /**
     * @return productStatusForCopy
     * 
     * Returns the productStatusForCopy.
     */
    public String getProductStatusForCopy() {
        return productStatusForCopy;
    }
    /**
     * @param productStatusForCopy
     * 
     * Sets the productStatusForCopy.
     */
    public void setProductStatusForCopy(String productStatusForCopy) {
        this.productStatusForCopy = productStatusForCopy;
    }
	/**
	 * @return Returns the requestFromMigrationWizard.
	 */
	public boolean isRequestFromMigrationWizard() {
		return requestFromMigrationWizard;
	}
	/**
	 * @param requestFromMigrationWizard The requestFromMigrationWizard to set.
	 */
	public void setRequestFromMigrationWizard(boolean requestFromMigrationWizard) {
		this.requestFromMigrationWizard = requestFromMigrationWizard;
	}
	/**
	 * @return Returns the copyLegacyNotes.
	 */
	public boolean isCopyLegacyNotes() {
		return copyLegacyNotes;
	}
	/**
	 * @param copyLegacyNotes The copyLegacyNotes to set.
	 */
	public void setCopyLegacyNotes(boolean copyLegacyNotes) {
		this.copyLegacyNotes = copyLegacyNotes;
	}
	/**
	 * @return Returns the unSelectedLineList.
	 */
	public List getUnSelectedLineList() {
		return unSelectedLineList;
	}
	/**
	 * @param unSelectedLineList The unSelectedLineList to set.
	 */
	public void setUnSelectedLineList(List unSelectedLineList) {
		this.unSelectedLineList = unSelectedLineList;
	}
}
