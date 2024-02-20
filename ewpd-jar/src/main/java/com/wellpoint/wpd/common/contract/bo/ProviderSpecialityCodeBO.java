/*
 * ProviderSpecialityCodeBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProviderSpecialityCodeBO extends BusinessObject {

    private int dateSegmentSysId;

    private String specialityCode;

    private String providerSpecialityCodeDescription;
    
    //added for displaying combination of coed and description 
    private String specialityCodeForView;


    /**
     * @return Returns the dateSegmentSysId.
     */

    public int getDateSegmentSysId() {
        return dateSegmentSysId;
    }


    /**
     * @param dateSegmentSysId
     *            The dateSegmentSysId to set.
     */
    public void setDateSegmentSysId(int dateSegmentSysId) {
        this.dateSegmentSysId = dateSegmentSysId;
    }


    /**
     * @return Returns the specialityCode.
     */
    public String getSpecialityCode() {
        return specialityCode;
    }


    /**
     * @param specialityCode
     *            The specialityCode to set.
     */
    public void setSpecialityCode(String specialityCode) {
        this.specialityCode = specialityCode;
    }


    /*
     * (non-Javadoc)
     * 
     * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
     */
    public boolean equals(BusinessObject object) {
        // TODO Auto-generated method stub
        return false;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * @return Returns the providerSpecialityCodeDescription.
     */
    public String getProviderSpecialityCodeDescription() {
        return providerSpecialityCodeDescription;
    }


    /**
     * @param providerSpecialityCodeDescription
     *            The providerSpecialityCodeDescription to set.
     */
    public void setProviderSpecialityCodeDescription(
            String providerSpecialityCodeDescription) {
        this.providerSpecialityCodeDescription = providerSpecialityCodeDescription;
    }
	/**
	 * @return Returns the specialityCodeForView.
	 */
	public String getSpecialityCodeForView() {
		return specialityCodeForView;
	}
	/**
	 * @param specialityCodeForView The specialityCodeForView to set.
	 */
	public void setSpecialityCodeForView(String specialityCodeForView) {
		this.specialityCodeForView = specialityCodeForView;
	}
}