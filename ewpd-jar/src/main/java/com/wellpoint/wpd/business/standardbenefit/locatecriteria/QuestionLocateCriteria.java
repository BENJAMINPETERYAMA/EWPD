/*
 * OpenQuestionLocateCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionLocateCriteria extends LocateCriteria{
    
    private int AdminOptionsSysId;
    
    private int systemId;
    
    private int locateAction;
    
    private int standardBenefitKey;

    /**
     * @return standardBenefitKey
     * 
     * Returns the standardBenefitKey.
     */
    public int getStandardBenefitKey() {
        return standardBenefitKey;
    }
    /**
     * @param standardBenefitKey
     * 
     * Sets the standardBenefitKey.
     */
    public void setStandardBenefitKey(int standardBenefitKey) {
        this.standardBenefitKey = standardBenefitKey;
    }
    /**
     * 
     */
    public QuestionLocateCriteria() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return Returns the locateAction.
     */
    public int getLocateAction() {
        return locateAction;
    }
    /**
     * @param locateAction The locateAction to set.
     */
    public void setLocateAction(int locateAction) {
        this.locateAction = locateAction;
    }

    /**
     * @return Returns the adminOptionsSysId.
     */
    public int getAdminOptionsSysId() {
        return AdminOptionsSysId;
    }
    /**
     * @param adminOptionsSysId The adminOptionsSysId to set.
     */
    public void setAdminOptionsSysId(int adminOptionsSysId) {
        AdminOptionsSysId = adminOptionsSysId;
    }
    /**
     * @return Returns the systemId.
     */
    public int getSystemId() {
        return systemId;
    }
    /**
     * @param systemId The systemId to set.
     */
    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }
}
