/*
 * Created on May 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.productstructure.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.productstructure.vo.ProdStructNotesToQuestionAttachmentVO;

/**
 * @author US Technology
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AttachNotesToQuestionRequestForPS extends WPDRequest {

    private int requestType;
	private ProdStructNotesToQuestionAttachmentVO notesAttachVO;
	
	
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}	
	
  
    /**
     * @return Returns the notesAttachVO.
     */
    public ProdStructNotesToQuestionAttachmentVO getNotesAttachVO() {
        return notesAttachVO;
    }
    /**
     * @param notesAttachVO The notesAttachVO to set.
     */
    public void setNotesAttachVO(
            ProdStructNotesToQuestionAttachmentVO notesAttachVO) {
        this.notesAttachVO = notesAttachVO;
    }
   
    /**
     * @return Returns the requestType.
     */
    public int getRequestType() {
        return requestType;
    }
    /**
     * @param requestType The requestType to set.
     */
    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }
}
