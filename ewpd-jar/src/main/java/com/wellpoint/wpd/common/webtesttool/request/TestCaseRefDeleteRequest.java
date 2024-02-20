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
import com.wellpoint.wpd.common.webtesttool.vo.TestCaseRefVO;


public class TestCaseRefDeleteRequest  extends WPDRequest{
    private TestCaseRefVO testCaseRefVO;
    
    /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }
    /**
     * @return Returns the testCaseRefVO.
     */
    public TestCaseRefVO getTestCaseRefVO() {
        return testCaseRefVO;
    }
    /**
     * @param testCaseRefVO The testCaseRefVO to set.
     */
    public void setTestCaseRefVO(TestCaseRefVO testCaseRefVO) {
        this.testCaseRefVO = testCaseRefVO;
    }
}
