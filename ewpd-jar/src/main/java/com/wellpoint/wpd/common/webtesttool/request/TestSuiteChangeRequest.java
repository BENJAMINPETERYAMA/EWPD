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
import com.wellpoint.wpd.common.webtesttool.vo.TestSuiteVO;

public class TestSuiteChangeRequest  extends WPDRequest{
    
    private TestSuiteVO testSuiteVO;
    /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }

    /**
     * @return Returns the testSuiteVO.
     */
    public TestSuiteVO getTestSuiteVO() {
        return testSuiteVO;
    }
    /**
     * @param testSuiteVO The testSuiteVO to set.
     */
    public void setTestSuiteVO(TestSuiteVO testSuiteVO) {
        this.testSuiteVO = testSuiteVO;
    }

}
