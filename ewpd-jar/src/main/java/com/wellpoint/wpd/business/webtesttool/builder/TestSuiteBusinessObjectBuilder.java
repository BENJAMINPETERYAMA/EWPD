/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.webtesttool.builder;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.webtesttool.adapter.TestSuiteAdapterManager;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.webtesttool.bo.TestSuiteBO;
import com.wellpoint.wpd.common.webtesttool.vo.TestSuiteVO;

public class TestSuiteBusinessObjectBuilder {
    /***   Test Suite operations       ***/
    public void persistTestSuite(TestSuiteBO testSuiteBO, String user) throws SevereException  {
        
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
        // update sequence id , created user id, created time
        int seqtestSuiteId = sequenceAdapterManager.getNextTestSuiteSequence();
        testSuiteBO.setTestSuiteId(""+seqtestSuiteId);
        testSuiteBO.setCreatedUser(user);
        testSuiteBO.setCreatedTimestamp(new Date());
        new TestSuiteAdapterManager().persistTestSuite(testSuiteBO, user);        
    }
    
    public void removeTestSuite(TestSuiteBO testSuiteBO, String user) throws SevereException  {
        new TestSuiteAdapterManager().removeTestSuite(testSuiteBO, user);
    }
    
    public boolean changeIdentity(TestSuiteBO testSuiteBO, String user) throws SevereException {
        new TestSuiteAdapterManager().changeIdentity(testSuiteBO, user);
        return true;
    }
    
    public List locateTestSuiteByName(TestSuiteVO testSuiteVO) throws  SevereException  {
        return new TestSuiteAdapterManager().locateTestSuiteByName(testSuiteVO);
    }
    
    public List locateTestSuiteById(TestSuiteVO testSuiteVO) throws  SevereException  {
        return new TestSuiteAdapterManager().locateTestSuiteById(testSuiteVO);
    }
    
    public List locateTestSuite(TestSuiteVO testSuiteVO)throws SevereException {
        return new TestSuiteAdapterManager().locateTestSuite(testSuiteVO);
    }
    
	/***   Test Case Referance operations       ***/
    // create test case ref record
	public void persistTestCaseRef(List testCaseRefBOList,  String user) throws SevereException {
	    new TestSuiteAdapterManager().persistTestCaseRef(testCaseRefBOList, user);
	}
	public List locateTestCaseRef(String testSuiteId)throws SevereException {
	    return new TestSuiteAdapterManager().locateTestCaseRef(testSuiteId, null);
	}
	public List locateTestCaseRef(String testSuiteId, List testCaseIdList)throws SevereException {
	    return new TestSuiteAdapterManager().locateTestCaseRef(testSuiteId, testCaseIdList);
	}
	public void removeTestCaseRef(List testCaseRefBOList, String user) throws SevereException {
	    new TestSuiteAdapterManager().removeTestCaseRef(testCaseRefBOList, user);
	}
	public List locateBeneftCompRuleID(String contractId, String startDate, String endDate)throws SevereException {
	    return new TestSuiteAdapterManager().locateBeneftCompRuleID(contractId, startDate, endDate);
	}
	public List locateBeneftRuleID(String contractId, String startDate, String endDate, List bnftCompId)throws SevereException {
	    return new TestSuiteAdapterManager().locateBeneftRuleID(contractId, startDate, endDate, bnftCompId);
	}
}
