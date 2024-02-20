/*
 * SaveSubTaskAssociationResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.security.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.security.bo.TaskConfigBO;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveSubTaskAssociationResponse extends WPDResponse {
    private TaskConfigBO taskConfigBO;
    
    /**
     * @return Returns the taskConfigBO.
     * @return TaskConfigBO taskConfigBO
     */
    public TaskConfigBO getTaskConfigBO() {
        return taskConfigBO;
    }
    /**
     * Sets the taskConfigBO
     * @param taskConfigBO
     */
    public void setTaskConfigBO(TaskConfigBO taskConfigBO) {
        this.taskConfigBO = taskConfigBO;
    }
}
