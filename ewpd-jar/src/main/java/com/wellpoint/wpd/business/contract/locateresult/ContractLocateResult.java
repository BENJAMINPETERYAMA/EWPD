/*
 * ContractLocateResult.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.locateresult;

import java.util.Date;

import com.wellpoint.wpd.common.bo.LocateResult;



/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractLocateResult extends LocateResult{
	
	private String lob;
	private int contractParentSysId;
	
	private String  description;
	private String testIndicator;
	
	private int contractKey; 
	
	private String contractId;
	
	private int dateSegmentCount;
	
	private Date startDate;
	
	private String contractType;
	
	private Date endDate;
	
	private boolean editableByUser;

	private String color ;
	
	private int dateSegmentId;
	
	private boolean validForDS;
	
	private int productSysId;

	private String dataFeedIndicator;
	
	private boolean validForCopyHere;
	
	private String dateSegmentStatus;
	
	private int dateSegmentVersion;
	

	/**
	 * @return Returns the dateSegmentStatus.
	 */
	public String getDateSegmentStatus() {
		return dateSegmentStatus;
	}
	/**
	 * @param dateSegmentStatus The dateSegmentStatus to set.
	 */
	public void setDateSegmentStatus(String dateSegmentStatus) {
		this.dateSegmentStatus = dateSegmentStatus;
	}
	/**
	 * @return Returns the dateSegmentVersion.
	 */
	public int getDateSegmentVersion() {
		return dateSegmentVersion;
	}
	/**
	 * @param dateSegmentVersion The dateSegmentVersion to set.
	 */
	public void setDateSegmentVersion(int dateSegmentVersion) {
		this.dateSegmentVersion = dateSegmentVersion;
	}
	/**
	 * Returns the contractId
	 * @return String contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * Sets the contractId
	 * @param contractId.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * Returns the contractType
	 * @return String contractType.
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * Sets the contractType
	 * @param contractType.
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	/**
	 * Returns the dateSegmentCount
	 * @return int dateSegmentCount.
	 */
	public int getDateSegmentCount() {
		return dateSegmentCount;
	}
	/**
	 * Sets the dateSegmentCount
	 * @param dateSegmentCount.
	 */
	public void setDateSegmentCount(int dateSegmentCount) {
		this.dateSegmentCount = dateSegmentCount;
	}
	/**
	 * Returns the endDate
	 * @return Date endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * Sets the endDate
	 * @param endDate.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the startDate
	 * @return Date startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * Sets the startDate
	 * @param startDate.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * Returns the contractKey
	 * @return int contractKey.
	 */
	public int getContractKey() {
		return contractKey;
	}
	/**
	 * Sets the contractKey
	 * @param contractKey.
	 */
	public void setContractKey(int contractKey) {
		this.contractKey = contractKey;
	}
	
	/**
	 * @param editableByUser The editableByUser to set.
	 */
	public void setEditableByUser(boolean editableByUser) {
		this.editableByUser = editableByUser;
	}
	
	 public boolean isValidForApproval() {
	 	// IF test date segments has been scheduled, then contract cannot 
	 	// be sent for approval. This could be checked out.
 		if("Y".equals(this.dataFeedIndicator) || "MODEL".equals(this.contractType) )
	 			return false;
        return this.getState().isValidForApproval();
		}

    public boolean isValidForCheckin() {
        return this.getState().isValidForCheckIn();
    }

    public boolean isValidForCheckOut() {
        return this.getState().isValidForCheckOut();
    }

    public boolean isValidForDeletion() {
        //return this.getState().isValidForDelete();
        //TODO
        return this.isEditableByUser() && "BUILDING".equals(this.getStatus());
    }

    public boolean isValidForPuplish() {
        return this.getState().isValidForPublish();
    }

    public boolean isValidForTesting() {
        return this.getState().isValidForTest();
    }

    public boolean isValidForTransfer() {
        return this.getState().isValidForTransfer();
    }

    public boolean isEditableByUser() {
        return this.getState().isEditableByUser();
    }

    public boolean isLocked() {
        return this.getState().isLocked();
    }
    /**
	 * 
	 * @return
	 */
	public String getDeleteMessage() {
        String deleteMessage = "Are you sure you want to delete? "+":"+"0";
        return deleteMessage;
	}	
	/**
	 * @return Returns the color.
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color The color to set.
	 */
	public void setColor(String color) {
		this.color = color;
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
	
	public boolean isValidForDS(){
		if(this.getContractType().equals("SHELL")){
			return false;
		}
		else if(this.getStatus().equals("PUBLISHED") &&  this.getState().isValidForDelete()){
			
			return true;
			
		}
		else if(this.getStatus().equals("BUILDING") && (this.getProductSysId() >0)  ){ 
			return true;
		}
		else if( (this.getStatus().equals("TEST_SUCCESSFUL")
		|| this.getStatus().equals("TEST_FAILED") || this.getStatus().equals("REJECTED")|| this.getStatus().equals("CHECKED_IN")
		|| this.getStatus().equals("SCHEDULED_FOR_TEST") || this.getStatus().equals("SCHEDULED_FOR_APPROVAL") )
		&& this.getState().isValidForDelete()){
			return true;
		}
		else if(this.getStatus().equals("CHECKED_OUT")  ){
			//code to check the whether published earlier.
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * @param validForDS The validForDS to set.
	 */
	public void setValidForDS(boolean validForDS) {
		this.validForDS = validForDS;
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
	 * @return Returns the testIndicator.
	 */
	public String getTestIndicator() {
		return testIndicator;
	}
	/**
	 * @param testIndicator The testIndicator to set.
	 */
	public void setTestIndicator(String testIndicator) {
		this.testIndicator = testIndicator;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		
		if(this.getTestIndicator().equals("Y")){
			return "Test";
		}
		else{
			return "Regular";
		}
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the dataFeedIndicator.
	 */
	public String getDataFeedIndicator() {
		return dataFeedIndicator;
	}
	/**
	 * @param dataFeedIndicator The dataFeedIndicator to set.
	 */
	public void setDataFeedIndicator(String dataFeedIndicator) {
		this.dataFeedIndicator = dataFeedIndicator;
	}
   
    /**
     * Returns the validForCopyHere
     * @return boolean validForCopyHere.
     */
    public boolean isValidForCopyHere() {
        if(this.getTestIndicator().equals("N") && this.getState().isValidForCopy()){
			return true;
		}else{
		    return false;
		}
    }
    /**
     * Sets the validForCopyHere
     * @param validForCopyHere.
     */
    public void setValidForCopyHere(boolean validForCopyHere) {
        this.validForCopyHere = validForCopyHere;
    }
	/**
	 * @return Returns the contractParentSysId.
	 */
	public int getContractParentSysId() {
		return contractParentSysId;
	}
	/**
	 * @param contractParentSysId The contractParentSysId to set.
	 */
	public void setContractParentSysId(int contractParentSysId) {
		this.contractParentSysId = contractParentSysId;
	}
	/**
	 * @return Returns the lob.
	 */
	public String getLob() {
		return lob;
	}
	/**
	 * @param lob The lob to set.
	 */
	public void setLob(String lob) {
		this.lob = lob;
	}
	
	private int version;


	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
}
