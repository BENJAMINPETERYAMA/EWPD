/*
 * OpenQuestionLocateResult.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.standardbenefit.locateresult;

import com.wellpoint.wpd.common.bo.LocateResult;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class OpenQuestionLocateResult extends LocateResult{

    private int AdminOptionsSysId;
    private int sequenceNumber;
    private String questionDesc;
    private int questionNumber;

    /**
     * 
     */
    public OpenQuestionLocateResult() {
        super();
        // TODO Auto-generated constructor stub
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
     * @return Returns the questionDesc.
     */
    public String getQuestionDesc() {
        return questionDesc;
    }
    /**
     * @param questionDesc The questionDesc to set.
     */
    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }
    /**
     * @return Returns the questionNumber.
     */
    public int getQuestionNumber() {
        return questionNumber;
    }
    /**
     * @param questionNumber The questionNumber to set.
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
    /**
     * @return Returns the sequenceNumber.
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }
    /**
     * @param sequenceNumber The sequenceNumber to set.
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
