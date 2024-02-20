/*
 * MandateListBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.bo;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public interface MandateListBO {
    /**
     * @return Returns the mandateDescription.
     */
    public String getMandateDescription();
    /**
     * @param mandateDescription The mandateDescription to set.
     */
    public void setMandateDescription(String mandateDescription);
    /**
     * @return Returns the mandateId.
     */
    public int getMandateId();
    /**
     * @param mandateId The mandateId to set.
     */
    public void setMandateId(int mandateId);
}
