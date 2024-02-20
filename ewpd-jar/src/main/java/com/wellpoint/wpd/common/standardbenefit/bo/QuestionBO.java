/*
 * QuestionBO.java
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
public interface QuestionBO {

	/**
	 * @param string
	 */
	void setQuestion(String string);

	/**
	 * @param string
	 */
	void setDataType(String string);
	
	/**
	 * @return Returns the question.
	 */
	public String getQuestion(); 
	
	public String getDataType(); 
}
