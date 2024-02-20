/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.webtesttool.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.webtesttool.adapter.TestCaseAdapterManager;
import com.wellpoint.wpd.business.webtesttool.adapter.TestSuiteAdapterManager;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.webtesttool.bo.ClaimHeaderBO;
import com.wellpoint.wpd.common.webtesttool.bo.ClaimLineBO;
import com.wellpoint.wpd.common.webtesttool.bo.TestCaseBO;
import com.wellpoint.wpd.common.webtesttool.response.TestCaseEditResponse;
import com.wellpoint.wpd.common.webtesttool.vo.ClaimLineVO;
import com.wellpoint.wpd.common.webtesttool.vo.TestCaseVO;

public class TestCaseBusinessObjectBuilder {
    
    public List locateTestCase(TestCaseVO testCaseVO) throws  SevereException  {
        return new TestCaseAdapterManager().locateTestCase(testCaseVO);
    }
    public List locateTestCaseByName(String testCaseName, String testCaseId) throws  SevereException  {
        return new TestCaseAdapterManager().locateTestCaseByName(testCaseName, testCaseId);
    }
    public TestCaseBO locateTestCaseById(String testCaseId) throws  SevereException  {
    	TestCaseEditResponse testCaseEditResponse = new TestCaseEditResponse();
    	Logger.logDebug("TC BOB : inside locateTestCase 1111");
        TestCaseAdapterManager testCaseAdapterManager = new TestCaseAdapterManager();
        //locate testcase bo
        TestCaseBO testCaseBO = (TestCaseBO)testCaseAdapterManager.locateTestCaseById(testCaseId).get(0);
        
        //locate claim line bo list related to the selected test case id // testCaseBO.getTestCaseId()
        Logger.logDebug("TC BOB : inside locateClaimLine 2222"+testCaseId);
        
        List claimLineDetailList = testCaseAdapterManager.locateClaimLineByTestCaseId(testCaseId);
        
        Iterator it = claimLineDetailList.iterator();
        while(it.hasNext()){
        	ClaimLineBO claimLineBO = (ClaimLineBO)it.next();
			String tempBill="";
			if( (claimLineBO.getTypeOfBill()!= null) && !(claimLineBO.getTypeOfBill().equals("")) )
				tempBill = claimLineBO.getTypeOfBill().split("~")[0];
			
			claimLineBO.setTypeOfBillCode(tempBill);  
				
			String tempBenftComp = "";
			if( (claimLineBO.getExpectedBenefitComponent()!= null) && (claimLineBO.getExpectedBenefitComponent().length()>0) )
					tempBenftComp = claimLineBO.getExpectedBenefitComponent().split("~")[1];
			claimLineBO.setExpectedBenefitComponentDesc(tempBenftComp);
			
			String tempMajDesc ="";
			if( (claimLineBO.getExpectedBenefit()!= null) && (claimLineBO.getExpectedBenefit().length()>0) )
				tempMajDesc = claimLineBO.getExpectedBenefit().split("~")[1];
			claimLineBO.setExpectedMajBenefitDesc(tempMajDesc);
			
			String tempBasicDesc ="";
			if( (claimLineBO.getExpectedBasicBenefit()!= null) && (claimLineBO.getExpectedBasicBenefit().length()>0) )
				tempBasicDesc = claimLineBO.getExpectedBasicBenefit().split("~")[1];
			claimLineBO.setExpectedBasicBenefitDesc(tempBasicDesc);
			
			String tempRiderDesc ="";
			if( (claimLineBO.getExpectedRiderBenefit()!= null) && (claimLineBO.getExpectedRiderBenefit().length()>0) )
				tempRiderDesc = claimLineBO.getExpectedRiderBenefit().split("~")[1];
			claimLineBO.setExpectedRiderBenefitDesc(tempRiderDesc);
        }
        
        testCaseBO.setClaimLineDetailList(claimLineDetailList);
        Logger.logDebug("list size #### "+claimLineDetailList.size());
        //locate claim header bo related to the selected test case id
        Logger.logDebug("TC BOB : inside locateClaimheader 3333");
        List claimHeaderList = testCaseAdapterManager.locateClaimHeaderByTestCaseId(testCaseId);
        Logger.logDebug("list size "+claimHeaderList.size());
        testCaseBO.setClaimHeaderBO((ClaimHeaderBO)claimHeaderList.get(0));
        testCaseEditResponse.setTestCaseBO(testCaseBO);
        return testCaseBO;
    }

    public boolean removeTestCase(TestCaseBO testCaseBO, String user) throws SevereException  {
    	Logger.logDebug("TEST CASE BOB :remove Test Case");
    	AdapterServicesAccess adapterServicesAccess= AdapterUtil.getAdapterService();
    	SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();    
        
       try{
       		String testCaseId = testCaseBO.getTestCaseId();
       		List testCaseIdList = new ArrayList();
       		testCaseIdList.add(testCaseId);
       		List testCaseRefList = new TestSuiteAdapterManager().locateTestCaseRef(null, testCaseIdList);
       		if(testCaseRefList.size()>0)
       			return false;
            int seqtestCaseId = sequenceAdapterManager.getNextTestCaseSequence();
       		if(testCaseId != null){
       			ClaimHeaderBO claimHeaderBO = new ClaimHeaderBO();
       			claimHeaderBO.setTestCaseId(testCaseId);
       			ClaimLineBO claimLineBO = new ClaimLineBO();
       			claimLineBO.setTestCaseId(testCaseId);
       			claimLineBO.setClaimLineId("AAA");// Hardcoding for AdapterManager compliance
       			// BEGIN TRANSACTION
       			AdapterUtil.beginTransaction(adapterServicesAccess);
       			new TestCaseAdapterManager().removeClaimHeader(claimHeaderBO, user);
       			new TestCaseAdapterManager().removeClaimLine(claimLineBO, user);
       			new TestCaseAdapterManager().removeTestCase(testCaseBO, user);
       			AdapterUtil.endTransaction(adapterServicesAccess);
       			// END TRANSACTION
       		}	 	
       }catch(Exception ex){
    	AdapterUtil.abortTransaction(adapterServicesAccess);
    	throw new SevereException("Unhandled Exception occured", null, ex);
       }
       return true;
    }


    public List locateBenefitComponent(ClaimLineVO claimLineVO, String user) throws SevereException  {
        return new TestCaseAdapterManager().locateBenefitComponent(claimLineVO, user);
    }

    public List locateBenefitComponentForPopup(ClaimLineVO claimLineVO, String user) throws SevereException  {
    	return new TestCaseAdapterManager().locateBenefitComponentForPopup(claimLineVO, user);
    }
    public List locateBenefit(ClaimLineVO claimLineVO, String user) throws SevereException  {
    	Logger.logDebug("Inside TC Business Object Builder ####");
        return new TestCaseAdapterManager().locateBenefit(claimLineVO, user);
    }  
    public void persistTestCase(TestCaseBO testCaseBO,ClaimHeaderBO claimHeaderBO,List claimLineList, String user) throws SevereException  {
    
    	AdapterServicesAccess adapterServicesAccess= AdapterUtil.getAdapterService();
        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
        int seqtestCaseId = sequenceAdapterManager.getNextTestCaseSequence();    
        testCaseBO.setTestCaseId(""+seqtestCaseId);
        claimHeaderBO.setTestCaseId(""+seqtestCaseId);
        
        try{
        	AdapterUtil.beginTransaction(adapterServicesAccess);
        	new TestCaseAdapterManager().persistTestCase(testCaseBO, user);
        	new TestCaseAdapterManager().persistClaimHeader(claimHeaderBO, user);
        	new TestCaseAdapterManager().persistClaimLine(claimLineList,testCaseBO, user);
        	AdapterUtil.endTransaction(adapterServicesAccess);
        }catch(Exception ex){
        	AdapterUtil.abortTransaction(adapterServicesAccess);
        	throw new SevereException("Unhandled Exception occured", null, ex);
        }
    }
    public boolean changeTestCase(TestCaseBO testCaseBO,ClaimHeaderBO claimHeaderBO,List claimLineList,boolean isEdit, String user) throws SevereException {
    	Logger.logDebug("Inside TC BOB ####");
    	String testCaseId = testCaseBO.getTestCaseId();
    	Logger.logDebug("TC BOB ----- TC ID"+testCaseId);
    	if(testCaseId != null){
    		claimHeaderBO.setTestCaseId(testCaseId);
    		new TestCaseAdapterManager().changeTestCase(testCaseBO,claimHeaderBO,claimLineList,isEdit, user);
    	}	
        return true;
    }
    
}