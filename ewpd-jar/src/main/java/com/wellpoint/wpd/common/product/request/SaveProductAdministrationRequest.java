/*
 * SaveProductAdministrationRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.product.vo.ProductVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductAdministrationRequest  extends ProductRequest {
    
    private List productAdministrationVOList;
	
	private ProductVO productVO;
	
	private List domainList;
	
	
	private int adminSysId;
	
	private List changedAnswerIds;
	
	private boolean answersChanged;
	

	
	/**
	 * @return Returns the answersChanged.
	 */
	public boolean isAnswersChanged() {
		return answersChanged;
	}
	/**
	 * @param answersChanged The answersChanged to set.
	 */
	public void setAnswersChanged(boolean answersChanged) {
		this.answersChanged = answersChanged;
	}
	
    /**
     * Returns the domainList
     * @return List domainList.
     */
    public List getDomainList() {
        return domainList;
    }
    /**
     * Sets the domainList
     * @param domainList.
     */
    public void setDomainList(List domainList) {
        this.domainList = domainList;
    }
    /**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	

    /**
     * Returns the adminSysId
     * @return int adminSysId.
     */
    public int getAdminSysId() {
        return adminSysId;
    }
    /**
     * Sets the adminSysId
     * @param adminSysId.
     */
    public void setAdminSysId(int adminSysId) {
        this.adminSysId = adminSysId;
    }
    /**
     * Returns the productAdministrationVOList
     * @return List productAdministrationVOList.
     */
    public List getProductAdministrationVOList() {
        return productAdministrationVOList;
    }
    /**
     * Sets the productAdministrationVOList
     * @param productAdministrationVOList.
     */
    public void setProductAdministrationVOList(List productAdministrationVOList) {
        this.productAdministrationVOList = productAdministrationVOList;
    }
    /**
     * Returns the productVO
     * @return ProductVO productVO.
     */
    public ProductVO getProductVO() {
        return productVO;
    }
    /**
     * Sets the productVO
     * @param productVO.
     */
    public void setProductVO(ProductVO productVO) {
        this.productVO = productVO;
    }
	/**
	 * @return Returns the changedAnswerIds.
	 */
	public List getChangedAnswerIds() {
		return changedAnswerIds;
	}
	/**
	 * @param changedAnswerIds The changedAnswerIds to set.
	 */
	public void setChangedAnswerIds(List changedAnswerIds) {
		this.changedAnswerIds = changedAnswerIds;
	}
}
