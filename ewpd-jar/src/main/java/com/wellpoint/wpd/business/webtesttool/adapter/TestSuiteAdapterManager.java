/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.webtesttool.adapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.webtesttool.bo.BenefitComponentRuleIDBO;
import com.wellpoint.wpd.common.webtesttool.bo.BenefitRuleIDBO;
import com.wellpoint.wpd.common.webtesttool.bo.TestCaseRefBO;
import com.wellpoint.wpd.common.webtesttool.bo.TestSuiteBO;
import com.wellpoint.wpd.common.webtesttool.vo.TestSuiteVO;

public class TestSuiteAdapterManager {

    /***   Test Suite data operations       ***/
    // create test suite record
	public void persistTestSuite(TestSuiteBO testSuiteBO,  String user) throws SevereException {
	    AdapterServicesAccess adapterServicesAccess= AdapterUtil.getAdapterService();
	    try{
	        AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil.performInsert(testSuiteBO, user, adapterServicesAccess);
			TestCaseRefBO testCaseRefBO = null;
			if(testSuiteBO.getTestCaseList() != null)
	        for(Iterator i = testSuiteBO.getTestCaseList().iterator(); i.hasNext();){
	            testCaseRefBO = (TestCaseRefBO)i.next();
	            testCaseRefBO.setTestSuiteId(testSuiteBO.getTestSuiteId());
	            AdapterUtil.performInsert(testCaseRefBO, user, adapterServicesAccess);
	        }
	        AdapterUtil.endTransaction(adapterServicesAccess);
	    }catch(Exception ex){
	        AdapterUtil.abortTransaction(adapterServicesAccess);
	        throw new SevereException("Unhandled Exception occured", null, ex);
	    }
	}
	
	//delete test suite record
	public void removeTestSuite(TestSuiteBO testSuiteBO, String user) throws SevereException {
		
		List testCaseRefList = locateTestCaseRef(testSuiteBO.getTestSuiteId(), null);
		removeTestCaseRef(testCaseRefList, user);
	    AdapterUtil.performRemove(testSuiteBO, user);
	}
	
	// update test suite record
	public void changeIdentity(TestSuiteBO testSuiteBO, String user) throws SevereException {
	    AdapterUtil.performUpdate(testSuiteBO, user);
	}
	
	// match for exact test suit name
	public List locateTestSuiteById(TestSuiteVO testSuiteVO)throws SevereException {
	    HashMap criteriaMap = new HashMap();
	    criteriaMap.put("testSuiteId", testSuiteVO.getTestSuiteId());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        TestSuiteBO.class.getName(), "searchTestSuiteById", criteriaMap); 
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}
	
	// count test suit which has same name for duplication avoid
	public List locateTestSuiteByName(TestSuiteVO testSuiteVO)throws SevereException {
	    HashMap criteriaMap = new HashMap();
	    criteriaMap.put("testSuiteId", testSuiteVO.getTestSuiteId());
	    criteriaMap.put("testSuiteName", testSuiteVO.getTestSuiteName());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        TestSuiteBO.class.getName(), "searchTestSuiteByName", criteriaMap); 
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}
	
	//match for any part of test suite name
	public List locateTestSuite(TestSuiteVO testSuiteVO)throws SevereException {
		List results;
		TestSuiteBO testSuiteBO;
		TestCaseRefBO testCaseRefBO;
		List testCaseRefList;
		String testCaseNameStr;
		
	    HashMap criteriaMap = new HashMap();
	    criteriaMap.put("testSuiteName", testSuiteVO.getTestSuiteName());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        TestSuiteBO.class.getName(), "locateTestSuite", criteriaMap);
		searchCriteria.setMaxSearchResultSize(50);
		results = AdapterUtil.performSearch(searchCriteria).getSearchResults();
		
		//populate test case names
		for(Iterator i = results.iterator(); i.hasNext();){
			testSuiteBO = (TestSuiteBO)i.next();
			testCaseRefList = locateTestCaseRef(testSuiteBO.getTestSuiteId(), null);
			testCaseNameStr = "";
			for(Iterator ii = testCaseRefList.iterator(); ii.hasNext();){
				testCaseRefBO = (TestCaseRefBO)ii.next();
				testCaseNameStr= testCaseNameStr + testCaseRefBO.getTestCaseName() + ", ";
			}
			if(testCaseNameStr.length()>2)
				testCaseNameStr = testCaseNameStr.substring(0, (testCaseNameStr.length()-2));
			testSuiteBO.setTestCaseRefStr(testCaseNameStr);
		}
		return results;
	}
	
	/***   Test Case Referance data operations       ***/
    // create test case ref record
	public void persistTestCaseRef(List testCaseRefBOList,  String user) throws SevereException {
	    AdapterServicesAccess adapterServicesAccess= AdapterUtil.getAdapterService();
	    try{
		    AdapterUtil.beginTransaction(adapterServicesAccess);
		    TestCaseRefBO testCaseRefBO = null;
		    for(Iterator i = testCaseRefBOList.iterator(); i.hasNext();){
		        testCaseRefBO = (TestCaseRefBO) i.next();
		        AdapterUtil.performInsert(testCaseRefBO, user, adapterServicesAccess);
		    }
		    AdapterUtil.endTransaction(adapterServicesAccess);
	    }catch(Exception ex){
	        AdapterUtil.abortTransaction(adapterServicesAccess);
	        throw new SevereException("Unhandled Exception occured", null, ex);
	    }
	}
	
	public List locateTestCaseRef(String testSuiteId, List testCaseIdList)throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("testSuiteId", testSuiteId);
		criteriaMap.put("testCaseIdList", testCaseIdList);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        TestCaseRefBO.class.getName(), "getTestCaseRef", criteriaMap);
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}

	//delete test suite record
	public void removeTestCaseRef(List testCaseRefBOList, String user) throws SevereException {
	    AdapterServicesAccess adapterServicesAccess= AdapterUtil.getAdapterService();
	    try{
		    AdapterUtil.beginTransaction(adapterServicesAccess);
		    TestCaseRefBO testCaseRefBO = null;
		    for(Iterator i = testCaseRefBOList.iterator(); i.hasNext();){
		        testCaseRefBO = (TestCaseRefBO) i.next();
		        AdapterUtil.performRemove(testCaseRefBO, user, adapterServicesAccess);
		    }
		    AdapterUtil.endTransaction(adapterServicesAccess);
	    }catch(Exception ex){
	        AdapterUtil.abortTransaction(adapterServicesAccess);
	        throw new SevereException("Unhandled Exception occured", null, ex);
	    }
	}
	
	public List locateBeneftCompRuleID(String contractId, String startDate, String endDate)throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("contractId", contractId);
		criteriaMap.put("startDate", startDate);
		criteriaMap.put("endDate", endDate);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        BenefitComponentRuleIDBO.class.getName(), "getBeneftCompRuleId", criteriaMap);
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}
	public List locateBeneftRuleID(String contractId, String startDate, String endDate, List bnftCompId)throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("contractId", contractId);
		criteriaMap.put("startDate", startDate);
		criteriaMap.put("endDate", endDate);
		criteriaMap.put("bnftCompId", bnftCompId);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
		        BenefitRuleIDBO.class.getName(), "getBeneftRuleId", criteriaMap);
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}
}
