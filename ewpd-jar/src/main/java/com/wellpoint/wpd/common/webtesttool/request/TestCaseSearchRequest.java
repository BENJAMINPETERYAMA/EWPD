/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.webtesttool.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.webtesttool.vo.TestCaseVO;


public class TestCaseSearchRequest  extends WPDRequest{

    private TestCaseVO testCaseVO;
    private String queryName;
    
    /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }
    /**
     * @return Returns the testCaseVO.
     */
    public TestCaseVO getTestCaseVO() {
        return testCaseVO;
    }
    /**
     * @param testCaseVO The testCaseVO to set.
     */
    public void setTestCaseVO(TestCaseVO testCaseVO) {
        this.testCaseVO = testCaseVO;
    }
    /**
     * @return Returns the queryName.
     */
    public String getQueryName() {
        return queryName;
    }
    /**
     * @param queryName The queryName to set.
     */
    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }
}
