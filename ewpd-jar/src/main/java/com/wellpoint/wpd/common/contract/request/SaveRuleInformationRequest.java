/*
 * Created on Jul 25, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveRuleInformationRequest extends WPDRequest{

		public void validate()throws ValidationException{			
		}
		
		private String ruleId;
		
		private String benefitMeaning;
		
		private int benefitId;
		
		private String lastUpdatesUser;
		
		private int benefitComponentId;
		
		private int dateSegmentId;
		
		/**
		 * @return Returns the benefitMeaning.
		 */
		public String getBenefitMeaning() {
			return benefitMeaning;
		}
		/**
		 * @param benefitMeaning The benefitMeaning to set.
		 */
		public void setBenefitMeaning(String benefitMeaning) {
			this.benefitMeaning = benefitMeaning;
		}
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
		 * @return Returns the benefitId.
		 */
		public int getBenefitId() {
			return benefitId;
		}
		/**
		 * @param benefitId The benefitId to set.
		 */
		public void setBenefitId(int benefitId) {
			this.benefitId = benefitId;
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
