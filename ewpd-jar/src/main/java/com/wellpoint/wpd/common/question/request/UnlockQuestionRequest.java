/*
 * Created on Jan 18, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.question.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.question.vo.QuestionVO;

/**
 * @author U14631
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UnlockQuestionRequest extends WPDRequest{
	
	 private QuestionVO questionVO;
	 

	/**
	 * @return Returns the questionVO.
	 */
	public QuestionVO getQuestionVO() {
		return questionVO;
	}
	/**
	 * @param questionVO The questionVO to set.
	 */
	public void setQuestionVO(QuestionVO questionVO) {
		this.questionVO = questionVO;
	}
	

    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {

    }
}
