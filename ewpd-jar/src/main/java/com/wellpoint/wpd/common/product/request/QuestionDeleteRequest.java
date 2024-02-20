/*
 * DeleteQuestionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.product.vo.ProductKeyObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionDeleteRequest extends ProductRequest{
    
    private String questionID;
    
    private int benefitComponentId = -1;
	
	private int adminLevelOptionAssnId;
	
	private ProductKeyObject productKeyObject;
	
	
    
    /**
     * @return productKeyObject
     * 
     * Returns the productKeyObject.
     */
    public ProductKeyObject getProductKeyObject() {
        return productKeyObject;
    }
    /**
     * @param productKeyObject
     * 
     * Sets the productKeyObject.
     */
    public void setProductKeyObject(ProductKeyObject productKeyObject) {
        this.productKeyObject = productKeyObject;
    }
    /* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	
	

    /**
     * @return questionID
     * 
     * Returns the questionID.
     */
    public String getQuestionID() {
        return questionID;
    }
    /**
     * @param questionID
     * 
     * Sets the questionID.
     */
    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }
    
    
    /**
     * @return adminLevelOptionAssnId
     * 
     * Returns the adminLevelOptionAssnId.
     */
    public int getAdminLevelOptionAssnId() {
        return adminLevelOptionAssnId;
    }
    /**
     * @param adminLevelOptionAssnId
     * 
     * Sets the adminLevelOptionAssnId.
     */
    public void setAdminLevelOptionAssnId(int adminLevelOptionAssnId) {
        this.adminLevelOptionAssnId = adminLevelOptionAssnId;
    }
    /**
     * @return benefitComponentId
     * 
     * Returns the benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * @param benefitComponentId
     * 
     * Sets the benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }
}
