/*
 * ProductDetail.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.result;

import java.util.Date;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ProductDetail.java 24880 2007-06-22 05:49:55Z u12046 $
 */
public class ProductDetail extends ObjectDetail {
	 	private Date effectiveDate;	    
	    private Date expiryDate;
	    private String productName;
	    private int productVersion;
	    private String status;
	    private ProductIdentifier pi;
	    
		/**
		 * @return Returns the effectiveDate.
		 */
		public Date getEffectiveDate() {
			return effectiveDate;
		}
		/**
		 * @param effectiveDate The effectiveDate to set.
		 */
		public void setEffectiveDate(Date effectiveDate) {
			this.effectiveDate = effectiveDate;
		}
		/**
		 * @return Returns the expiryDate.
		 */
		public Date getExpiryDate() {
			return expiryDate;
		}
		/**
		 * @param expiryDate The expiryDate to set.
		 */
		public void setExpiryDate(Date expiryDate) {
			this.expiryDate = expiryDate;
		}
		/**
		 * @return Returns the productName.
		 */
		public String getProductName() {
			return productName;
		}
		/**
		 * @param productName The productName to set.
		 */
		public void setProductName(String productName) {
			this.productName = productName;
		}
		/**
		 * @return Returns the productVersion.
		 */
		public int getProductVersion() {
			return productVersion;
		}
		/**
		 * @param productVersion The productVersion to set.
		 */
		public void setProductVersion(int productVersion) {
			this.productVersion = productVersion;
		}
        /**
         * @see com.wellpoint.wpd.common.search.result.ObjectDetail#getIdentifier()
         * @return
         */
        public ObjectIdentifier getIdentifier() {
            return pi;
        }
        /**
         * @see com.wellpoint.wpd.common.search.result.ObjectDetail#setIdentifier(com.wellpoint.wpd.common.search.result.ObjectIdentifier)
         * @param identifier
         */
        public void setIdentifier(ObjectIdentifier identifier) {
            if(identifier != null && identifier instanceof ProductIdentifier){
                pi = (ProductIdentifier)identifier;
            }else{
                throw new IllegalArgumentException(
                        "setIdentifier method in ProductDetail.  Parameter is null or of incorrect type "
                                + identifier);
            }
            
        }
		/**
		 * @return Returns the status.
		 */
		public String getStatus() {
			return status;
		}
		/**
		 * @param status The status to set.
		 */
		public void setStatus(String status) {
			this.status = status;
		}
}
