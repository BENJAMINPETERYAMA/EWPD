/*
 * Created on Aug 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;

/**
 * @author u13657
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveBenefitLinesRequest extends ContractRequest{
	    
	
	    private int dateSegmentId;
	    
	    private int benefitComponentId;
	    
	    private int standardBenefitId;
	    
	    public void validate() throws ValidationException {
	        // TODO Auto-generated method stub

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
		/**
		 * @return Returns the benefitComponentId.
		 */
		public int getBenefitComponentId() {
			return benefitComponentId;
		}
		/**
		 * @param benefitComponentId The benefitComponentId to set.
		 */
		public void setBenefitComponentId(int benefitComponentId) {
			this.benefitComponentId = benefitComponentId;
		}
		/**
		 * @return Returns the standardBenefitId.
		 */
		public int getStandardBenefitId() {
			return standardBenefitId;
		}
		/**
		 * @param standardBenefitId The standardBenefitId to set.
		 */
		public void setStandardBenefitId(int standardBenefitId) {
			this.standardBenefitId = standardBenefitId;
		}
	}

