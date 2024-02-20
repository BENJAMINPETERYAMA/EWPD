/*
 * PossibleAnswerBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.question.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.framework.util.StringUtil;

/**
 * Business object for possible answers
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class PossibleAnswerBO extends BusinessObject implements Comparable{

    private int questionNumber;

    private int possibleAnswerId;

    private String possibleAnswerDesc;
    
    private String accumQuestion;


    /**
     * Returns the possibleAnswerDesc
     * 
     * @return String possibleAnswerDesc.
     */
    public String getPossibleAnswerDesc() {
        return possibleAnswerDesc;
    }


    /**
     * Sets the possibleAnswerDesc
     * 
     * @param possibleAnswerDesc.
     */
    public void setPossibleAnswerDesc(String possibleAnswerDesc) {
        this.possibleAnswerDesc = possibleAnswerDesc;
    }


    /**
     * Returns the possibleAnswerId
     * 
     * @return int possibleAnswerId.
     */
    public int getPossibleAnswerId() {
        return possibleAnswerId;
    }


    /**
     * Sets the possibleAnswerId
     * 
     * @param possibleAnswerId.
     */
    public void setPossibleAnswerId(int possibleAnswerId) {
        this.possibleAnswerId = possibleAnswerId;
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
        StringBuffer buffer = new StringBuffer();
        buffer.append("questionNumber = ").append(this.questionNumber);
        buffer.append(", possibleAnswerId = ").append(this.possibleAnswerId);
        buffer.append(", possibleAnswerDesc = ")
                .append(this.possibleAnswerDesc);
        return buffer.toString();
    }
    
    /* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		if (!(obj instanceof PossibleAnswerBO))
			throw new ClassCastException("PossibleAnswer object Expected.");

		PossibleAnswerBO possibleAnswerBO = (PossibleAnswerBO) obj;
		if (StringUtil.isInteger(this.possibleAnswerDesc)
				&& StringUtil.isInteger(possibleAnswerBO.getPossibleAnswerDesc())) {
			
			int val1 = Integer.parseInt(this.possibleAnswerDesc);
			int val2 = Integer.parseInt(possibleAnswerBO.getPossibleAnswerDesc());
			return (val1 - val2);
			
		} else if (!StringUtil.isInteger(this.possibleAnswerDesc)
				&& StringUtil.isInteger(possibleAnswerBO.getPossibleAnswerDesc())) {
			
			return 1;
			
		} else if (StringUtil.isInteger(this.possibleAnswerDesc)
				&& !StringUtil.isInteger(possibleAnswerBO.getPossibleAnswerDesc())) {
			
			return -1;
			
		} else if (!StringUtil.isInteger(this.possibleAnswerDesc)
				&& !StringUtil.isInteger(possibleAnswerBO.getPossibleAnswerDesc())) {
			
			int val = this.possibleAnswerDesc.compareTo(possibleAnswerBO.getPossibleAnswerDesc());
			return val;
		}
		return 0;
	}


	public String getAccumQuestion() {
		return accumQuestion;
	}


	public void setAccumQuestion(String accumQuestion) {
		this.accumQuestion = accumQuestion;
	}


	
}