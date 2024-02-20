/*
 * SaveProductStructureRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductStructureRequest extends ProductStructureRequest {

    public static final int CREATE_PRODUCT_STRUCTURE = 1;

    public static final int UPDATE_PRODUCT_STRUCTURE = 2;

    public static final int CHECK_IN_PRODUCT_STRUCTURE = 3;

    public static final int COPY_PRODUCT_STRUCTURE = 4;

    public static final int DONE = 5;

    private int action;
    
    private int targetId;

    private ProductStructureVO productStructureVO;

    private ProductStructureVO oldKeyproductStructureBO;

    private boolean actionFromBC = false;

    private boolean deleteBenefitComponent = false;

    private boolean checkInFlag = false;
    
    private boolean domainChange = false;
    
    private boolean dateChange = false;
    
    private int checkInAction;
    
    public static final int GEN_CHECKIN = 1;
    public static final int BC_CHECKIN = 2;


    /**
     *  
     */
    public SaveProductStructureRequest() {
        super();
    }


    /**
     * Returns the productStructureVO
     * 
     * @return ProductStructureVO productStructureVO.
     */

    public ProductStructureVO getProductStructureVO() {
        return productStructureVO;
    }


    /**
     * Sets the productStructureVO
     * 
     * @param productStructureVO.
     */

    public void setProductStructureVO(ProductStructureVO productStructureVO) {
        this.productStructureVO = productStructureVO;
    }


    /**
     * Returns the action
     * 
     * @return int action.
     */

    public int getAction() {
        return action;
    }


    /**
     * Sets the action
     * 
     * @param action.
     */

    public void setAction(int action) {
        this.action = action;
    }


    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
    }


    /**
     * Returns the oldKeyproductStructureBO
     * 
     * @return ProductStructureVO oldKeyproductStructureBO.
     */

    public ProductStructureVO getOldKeyproductStructureBO() {
        return oldKeyproductStructureBO;
    }


    /**
     * Sets the oldKeyproductStructureBO
     * 
     * @param oldKeyproductStructureBO.
     */

    public void setOldKeyproductStructureBO(
            ProductStructureVO oldKeyproductStructureBO) {
        this.oldKeyproductStructureBO = oldKeyproductStructureBO;
    }


    /**
     * Returns the actionFromBC
     * 
     * @return boolean actionFromBC.
     */

    public boolean isActionFromBC() {
        return actionFromBC;
    }


    /**
     * Sets the actionFromBC
     * 
     * @param actionFromBC.
     */

    public void setActionFromBC(boolean actionFromBC) {
        this.actionFromBC = actionFromBC;
    }


    /**
     * @return deleteBenefitComponent
     * 
     * Returns the deleteBenefitComponent.
     */
    public boolean isDeleteBenefitComponent() {
        return deleteBenefitComponent;
    }


    /**
     * @param deleteBenefitComponent
     * 
     * Sets the deleteBenefitComponent.
     */
    public void setDeleteBenefitComponent(boolean deleteBenefitComponent) {
        this.deleteBenefitComponent = deleteBenefitComponent;
    }


    /**
     * @return checkInFlag
     * 
     * Returns the checkInFlag.
     */
    public boolean isCheckInFlag() {
        return checkInFlag;
    }


    /**
     * @param checkInFlag
     * 
     * Sets the checkInFlag.
     */
    public void setCheckInFlag(boolean checkInFlag) {
        this.checkInFlag = checkInFlag;
    }
	/**
	 * @return Returns the domainChange.
	 */
	public boolean isDomainChange() {
		return domainChange;
	}
	/**
	 * @param domainChange The domainChange to set.
	 */
	public void setDomainChange(boolean domainChange) {
		this.domainChange = domainChange;
	}
	/**
	 * @return Returns the dateChange.
	 */
	public boolean isDateChange() {
		return dateChange;
	}
	/**
	 * @param dateChange The dateChange to set.
	 */
	public void setDateChange(boolean dateChange) {
		this.dateChange = dateChange;
	}
    /**
     * @return targetId
     * 
     * Returns the targetId.
     */
    public int getTargetId() {
        return targetId;
    }
    /**
     * @param targetId
     * 
     * Sets the targetId.
     */
    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }
	/**
	 * @return Returns the checkInAction.
	 */
	public int getCheckInAction() {
		return checkInAction;
	}
	/**
	 * @param checkInAction The checkInAction to set.
	 */
	public void setCheckInAction(int checkInAction) {
		this.checkInAction = checkInAction;
	}
}