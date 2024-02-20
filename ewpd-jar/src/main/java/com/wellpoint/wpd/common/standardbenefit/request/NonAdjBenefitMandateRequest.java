/*
 * Created on Mar 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.vo.NonAdjBenefitMandateVO;


/**
 * @author u13664
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NonAdjBenefitMandateRequest extends WPDRequest {
	
	private int entityId;
	private String entityType;
	
	/**
     * @return entityId
     * 
     * Returns the entityId.
     */
    public int getEntityId() {
        return entityId;
    }
    /**
     * @param entityId
     * 
     * Sets the entityId.
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
    /**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	
	private NonAdjBenefitMandateVO nonAdjBenefitMandateVO; 
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @return Returns the nonAdjBenefitMandateVO.
	 */
	public NonAdjBenefitMandateVO getNonAdjBenefitMandateVO() {
		return nonAdjBenefitMandateVO;
	}
	/**
	 * @param nonAdjBenefitMandateVO The nonAdjBenefitMandateVO to set.
	 */
	public void setNonAdjBenefitMandateVO(NonAdjBenefitMandateVO nonAdjBenefitMandateVO) {
		this.nonAdjBenefitMandateVO = nonAdjBenefitMandateVO;
	}
}
