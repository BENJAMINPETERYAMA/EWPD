/*
 * DeleteQuestionResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.product.response;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionDeleteResponse extends WPDResponse {
    
    private List messageList = new ArrayList();
    
  
    /**
     * @return messageList
     * 
     * Returns the messageList.
     */
    public List getMessageList() {
        return messageList;
    }
    /**
     * @param messageList
     * 
     * Sets the messageList.
     */
    public void setMessageList(List messageList) {
        this.messageList = messageList;
    }
}
