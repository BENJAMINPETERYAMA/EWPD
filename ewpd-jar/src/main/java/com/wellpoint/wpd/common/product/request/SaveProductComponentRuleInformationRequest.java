/*
 * Created on Jul 25, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveProductComponentRuleInformationRequest extends WPDRequest{

		public void validate()throws ValidationException{			
		}
		
		private String ruleId;
		
		private String lastUpdatesUser;
		
		private int benefitComponentId;
		
		private int productId;
		
		
		/**
		 * @return Returns the ruleId.
		 */
		public String getRuleId() {
			return ruleId;
		}
		/**
		 * @param ruleId The ruleId to set.
		 */
		public void setRuleId(String ruleId) {
			this.ruleId = ruleId;
		}
		/**
		 * @return Returns the lastUpdatesUser.
		 */
		public String getLastUpdatesUser() {
			return lastUpdatesUser;
		}
		/**
		 * @param lastUpdatesUser The lastUpdatesUser to set.
		 */
		public void setLastUpdatesUser(String lastUpdatesUser) {
			this.lastUpdatesUser = lastUpdatesUser;
		}
        /**
         * Returns the benefitComponentId
         * @return int benefitComponentId.
         */
        public int getBenefitComponentId() {
            return benefitComponentId;
        }
        /**
         * Sets the benefitComponentId
         * @param benefitComponentId.
         */
        public void setBenefitComponentId(int benefitComponentId) {
            this.benefitComponentId = benefitComponentId;
        }
       
		/**
		 * @return Returns the productId.
		 */
		public int getProductId() {
			return productId;
		}
		/**
		 * @param productId The productId to set.
		 */
		public void setProductId(int productId) {
			this.productId = productId;
		}
}
