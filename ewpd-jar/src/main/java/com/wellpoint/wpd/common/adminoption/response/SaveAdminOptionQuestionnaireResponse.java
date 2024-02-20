/*
 * Created on Mar 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminoption.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.question.vo.QuestionVO;

/**
 * @author U17810
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveAdminOptionQuestionnaireResponse extends WPDResponse {
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
    private QuestionVO questionVO;
}
