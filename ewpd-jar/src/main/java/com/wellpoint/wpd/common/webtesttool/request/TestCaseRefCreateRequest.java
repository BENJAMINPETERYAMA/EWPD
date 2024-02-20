/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.webtesttool.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

public class TestCaseRefCreateRequest extends WPDRequest{

    private List testCaseRefVOList;
  
    /**
     * @return Returns the testCaseRefVOList.
     */
    public List getTestCaseRefVOList() {
        return testCaseRefVOList;
    }
    /**
     * @param testCaseRefVOList The testCaseRefVOList to set.
     */
    public void setTestCaseRefVOList(List testCaseRefVOList) {
        this.testCaseRefVOList = testCaseRefVOList;
    }
    /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

}
