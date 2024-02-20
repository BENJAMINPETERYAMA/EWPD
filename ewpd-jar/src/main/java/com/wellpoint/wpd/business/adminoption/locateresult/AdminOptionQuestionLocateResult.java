/*
 * AdminOptionQuestionLocateResult.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.adminoption.locateresult;

import com.wellpoint.wpd.common.bo.LocateResult;

/**
 * Locate Result class for Admin Option Question.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionQuestionLocateResult extends LocateResult {

    private int adminOptionId;

    private String adminOptionName;

    private int associatedQuestionId;

    private int seqNumber;


    /**
     * Returns the adminOptionId
     * 
     * @return int adminOptionId.
     */
    public int getAdminOptionId() {
        return adminOptionId;
    }


    /**
     * Sets the adminOptionId
     * 
     * @param adminOptionId.
     */
    public void setAdminOptionId(int adminOptionId) {
        this.adminOptionId = adminOptionId;
    }


    /**
     * Returns the adminOptionName
     * 
     * @return String adminOptionName.
     */
    public String getAdminOptionName() {
        return adminOptionName;
    }


    /**
     * Sets the adminOptionName
     * 
     * @param adminOptionName.
     */
    public void setAdminOptionName(String adminOptionName) {
        this.adminOptionName = adminOptionName;
    }


    /**
     * Returns the associatedQuestionId
     * 
     * @return int associatedQuestionId.
     */
    public int getAssociatedQuestionId() {
        return associatedQuestionId;
    }


    /**
     * Sets the associatedQuestionId
     * 
     * @param associatedQuestionId.
     */
    public void setAssociatedQuestionId(int associatedQuestionId) {
        this.associatedQuestionId = associatedQuestionId;
    }


    /**
     * Returns the seqNumber
     * 
     * @return int seqNumber.
     */
    public int getSeqNumber() {
        return seqNumber;
    }


    /**
     * Sets the seqNumber
     * 
     * @param seqNumber.
     */
    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }
}