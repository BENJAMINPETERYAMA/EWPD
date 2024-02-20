/*
 * SaveProductRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.request;

import com.wellpoint.wpd.common.product.vo.ProductVO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductRequest extends ProductRequest{
    
    public static final int CREATE_PRODUCT = 1;
    
    public static final int UPDATE_PRODUCT = 2;
    
    public static final int CHECKIN_PRODUCT = 3;
    
    public static final int COPY_PRODUCT = 4;
    
    public static final int SEND_TO_TEST_PRODUCT = 5;
    
    public static final int PUBLISH_PRODUCT = 6;
    
    public static final int SCHEDULE_FOR_APPROVAL_PRODUCT = 7;
    
    public static final int TEST_PASS_PRODUCT = 9;
    
    public static final int TEST_FAIL_PRODUCT = 10;
    
    public static final int CHECKOUT_PRODUCT = 8;
    
    public static final int SCHEDULE_TO_PRODUCTION = 11;
    
    public static final int APPROVE_PRODUCT = 12;
    
    public static final int REJECT_PRODUCT = 13; 
    
    public static final int UNLOCK_PRODUCT = 14;
    
    
    
    private int action;
    
    private ProductVO product;
    private boolean checkIn;

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("productKeyObject = { ").append(this.getProductKeyObject()).append(" }");
        buffer.append(", action = ");
        switch(this.getAction()) {
        	case CREATE_PRODUCT 				: buffer.append("CREATE_PRODUCT"); break;
            case  UPDATE_PRODUCT 				: buffer.append("UPDATE_PRODUCT"); break;
            case  CHECKIN_PRODUCT 				: buffer.append("CHECKIN_PRODUCT"); break;
            case  COPY_PRODUCT 					: buffer.append("COPY_PRODUCT"); break;
            case  SEND_TO_TEST_PRODUCT 			: buffer.append("SEND_TO_TEST_PRODUCT"); break;
            case  PUBLISH_PRODUCT 				: buffer.append("PUBLISH_PRODUCT"); break;
            case  SCHEDULE_FOR_APPROVAL_PRODUCT : buffer.append("SCHEDULE_FOR_APPROVAL_PRODUCT"); break;
            case  TEST_PASS_PRODUCT 			: buffer.append("TEST_PASS_PRODUCT"); break;
            case  TEST_FAIL_PRODUCT				: buffer.append("TEST_FAIL_PRODUCT"); break;
            case  CHECKOUT_PRODUCT 				: buffer.append("CHECKOUT_PRODUCT"); break;
            case  SCHEDULE_TO_PRODUCTION 		: buffer.append("SCHEDULE_TO_PRODUCTION"); break;
            case  APPROVE_PRODUCT 				: buffer.append("APPROVE_PRODUCT"); break;
            case  REJECT_PRODUCT 				: buffer.append("REJECT_PRODUCT");  break;
        }
        buffer.append(", ProductVO = { ").append(this.getProduct()).append(" }");
        buffer.append(" , checkIn = ").append(this.isCheckIn());
        return buffer.toString();
    }
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
   /* public void validate() throws ValidationException {
        
    	
    	if(null == this.product.getProductDesc() || "".equals(this.product.getProductDesc()) ) 
    		throw new ValidationException("Product description is null",null,null);
    	
		if(null == this.product.getProductFamilyId() || "".equals(this.product.getProductFamilyId()) ) 
			throw new ValidationException("Product family is null",null,null);
		
		if(null == this.product.getProductName() || "".equals(this.product.getProductName()) )
			throw new ValidationException("Product name is null",null,null);
			
		if(1 > this.product.getProductStructureKey() ) 
			throw new ValidationException("Product Structure is zero",null,null);
			
//		if(null == this.product.getBusinessEntityList() || this.product.getBusinessEntityList().size()==0 ) 
//			throw new ValidationException("BusinessEntity is null",null,null);
//   
//		if(null == this.product.getBusinessGroupList() || this.product.getBusinessGroupList().size()==0 ) 
//			throw new ValidationException("BusinessGroup is null",null,null);
//		
//		if(null == this.product.getLineOfBusinessList() || this.product.getLineOfBusinessList().size()==0 ) 
//			throw new ValidationException("getLineOfBusiness is null",null,null);

		if(null == this.product.getEffectiveDate() ) 
			throw new ValidationException("Effective Date is null",null,null);
		
		if(null == this.product.getExpiryDate() ) 
			throw new ValidationException("ExpiryDate Date is null",null,null);
		
		 if(this.product.getEffectiveDate().compareTo(this.product.getExpiryDate())>0)
		 	throw new ValidationException("Effective Date is greater than ExpiryDate Date",null,null);
		
    }*/
    
    /**
     * Returns the product
     * @return ProductVO product.
     */
    public ProductVO getProduct() {
        return product;
    }
    
    /**
     * Sets the product
     * @param product.
     */
    public void setProduct(ProductVO product) {
        this.product = product;
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
	 * Returns the checkIn
	 * @return boolean checkIn.
	 */
	public boolean isCheckIn() {
		return checkIn;
	}
	/**
	 * Sets the checkIn
	 * @param checkIn.
	 */
	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}
}
