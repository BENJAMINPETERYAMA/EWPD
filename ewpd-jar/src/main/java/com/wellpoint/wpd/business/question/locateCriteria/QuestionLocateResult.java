/*
 * QuestionLocateResult.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.question.locateCriteria;

import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.LocateResult;

/**
 * Locate results for question
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionLocateResult extends LocateResult {

    private int questionNumber;

    private String questionDesc;

    private int dataTypeId;

    private String dataTypeName;

    private boolean versionFlag;


    /**
     * Returns the versionFlag.
     * 
     * @return versionFlag
     */
    public boolean getVersionFlag() {
        return versionFlag;
    }


    /**
     * Sets version flsg
     * 
     * @param versionFlag
     */
    public void setVersionFlag(boolean versionFlag) {
        this.versionFlag = versionFlag;
    }


    /**
     * Returns the dataTypeId
     * 
     * @return int dataTypeId.
     */
    public int getDataTypeId() {
        return dataTypeId;
    }


    /**
     * Sets the dataTypeId
     * 
     * @param dataTypeId.
     */
    public void setDataTypeId(int dataTypeId) {
        this.dataTypeId = dataTypeId;
    }


    /**
     * Returns the questionDesc
     * 
     * @return String questionDesc.
     */
    public String getQuestionDesc() {
        return questionDesc;
    }


    /**
     * Sets the questionDesc
     * 
     * @param questionDesc.
     */
    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }


    /**
     * Returns the questionNumber
     * 
     * @return int questionNumber.
     */
    public int getQuestionNumber() {
        return questionNumber;
    }


    /**
     * Sets the questionNumber
     * 
     * @param questionNumber.
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }


    /**
     * Returns the dataTypeName.
     * 
     * @return dataTypeName
     */
    public String getDataTypeName() {
        return dataTypeName;
    }


    /**
     * The dataTypeName to set.
     * 
     * @param dataTypeName
     */
    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }


    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
     * @param object
     * @return boolean
     */
    public boolean equals(BusinessObject object) {
        return true;
    }


    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
     * @return int
     */
    public int hashCode() {
        return 1;
    }


    /**
     * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
     * @return String
     */
    public String toString() {
        return "";
    }

}