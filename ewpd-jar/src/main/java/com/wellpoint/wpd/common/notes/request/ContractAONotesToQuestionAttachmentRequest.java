/*
 * Created on Jun 18, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.notes.request;

import com.wellpoint.wpd.common.contract.request.ContractRequest;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.notes.vo.NotesToQuestionAttachmentVO;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractAONotesToQuestionAttachmentRequest extends ContractRequest {

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	NotesToQuestionAttachmentVO notesToQuestionAttachmentVO = new NotesToQuestionAttachmentVO();
	private int requestType;
	public NotesToQuestionAttachmentVO getNotesToQuestionAttachmentVO() {
		return notesToQuestionAttachmentVO;
	}
	public void setNotesToQuestionAttachmentVO(
			NotesToQuestionAttachmentVO notesToQuestionAttachmentVO) {
		this.notesToQuestionAttachmentVO = notesToQuestionAttachmentVO;
	}
	public int getRequestType() {
		return requestType;
	}
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
}
