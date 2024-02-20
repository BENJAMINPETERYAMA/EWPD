/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.webtesttool.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;


public class TestCaseRefCreateResponse extends WPDResponse{
    //list of testcase for testsuite id
    List TestCaseRefList = null;
    
    /**
     * @return Returns the testCaseRefList.
     */
    public List getTestCaseRefList() {
        return TestCaseRefList;
    }
    /**
     * @param testCaseRefList The testCaseRefList to set.
     */
    public void setTestCaseRefList(List testCaseRefList) {
        TestCaseRefList = testCaseRefList;
    }
}
