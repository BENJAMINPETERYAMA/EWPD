/*
 * Created on Jan 22, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.adminmethod.locatecriteria;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.business.adminmethod.service.AdminMethodBusinessValidationService;
import com.wellpoint.wpd.common.framework.logging.Logger;



/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminMethodValidationThreadForProduct  implements Runnable {
    
    private int contractSysId;
    
    private int dateSegmentId;
    
    private boolean resultForProduct;
    
    private List messageList;
    
    private Date startDate;
    
    private Date endDate;
    
    /**
	 *  
	 */
	public void run() {
	    try{
	        this.resultForProduct=true;
	        AdminMethodBusinessValidationService adminMethodBusinessValidationService = new AdminMethodBusinessValidationService();
	        adminMethodBusinessValidationService.getProductFromContract(this.dateSegmentId,this.contractSysId,this.startDate,this.endDate);
	        this.messageList = adminMethodBusinessValidationService.getMessageListForProduct();
	        this.resultForProduct = adminMethodBusinessValidationService.isValidationForContract();
		    
		    
	    }catch(Exception e){
	        Logger.logError(e);
	    }
	    
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
     * Returns the messageList
     * @return List messageList.
     */

    public List getMessageList() {
        return messageList;
    }
    /**
     * Sets the messageList
     * @param messageList.
     */

    public void setMessageList(List messageList) {
        this.messageList = messageList;
    }
    

    /**
     * Returns the resultForProduct
     * @return boolean resultForProduct.
     */

    public boolean isResultForProduct() {
        return resultForProduct;
    }
    /**
     * Sets the resultForProduct
     * @param resultForProduct.
     */

    public void setResultForProduct(boolean resultForProduct) {
        this.resultForProduct = resultForProduct;
    }
    /**
     * Returns the contractSysId
     * @return int contractSysId.
     */

    public int getContractSysId() {
        return contractSysId;
    }
    /**
     * Sets the contractSysId
     * @param contractSysId.
     */

    public void setContractSysId(int contractSysId) {
        this.contractSysId = contractSysId;
    }
    /**
     * Returns the dateSegmentId
     * @return int dateSegmentId.
     */

    public int getDateSegmentId() {
        return dateSegmentId;
    }
    /**
     * Sets the dateSegmentId
     * @param dateSegmentId.
     */

    public void setDateSegmentId(int dateSegmentId) {
        this.dateSegmentId = dateSegmentId;
    }
}
