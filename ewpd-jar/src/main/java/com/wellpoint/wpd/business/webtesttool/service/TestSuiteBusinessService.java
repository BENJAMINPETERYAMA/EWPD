/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.webtesttool.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.webtesttool.builder.TestCaseBusinessObjectBuilder;
import com.wellpoint.wpd.business.webtesttool.builder.TestSuiteBusinessObjectBuilder;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.webtesttool.bo.BenefitComponentRuleIDBO;
import com.wellpoint.wpd.common.webtesttool.bo.BenefitRuleIDBO;
import com.wellpoint.wpd.common.webtesttool.bo.TestCaseBO;
import com.wellpoint.wpd.common.webtesttool.bo.TestCaseRefBO;
import com.wellpoint.wpd.common.webtesttool.bo.TestSuiteBO;
import com.wellpoint.wpd.common.webtesttool.request.TestCaseRefCreateRequest;
import com.wellpoint.wpd.common.webtesttool.request.TestCaseRefDeleteRequest;
import com.wellpoint.wpd.common.webtesttool.request.TestSuiteChangeRequest;
import com.wellpoint.wpd.common.webtesttool.request.TestSuiteCreateRequest;
import com.wellpoint.wpd.common.webtesttool.request.TestSuiteDeleteRequest;
import com.wellpoint.wpd.common.webtesttool.request.TestSuiteExecuteRequest;
import com.wellpoint.wpd.common.webtesttool.request.TestSuiteSearchRequest;
import com.wellpoint.wpd.common.webtesttool.response.TestCaseRefCreateResponse;
import com.wellpoint.wpd.common.webtesttool.response.TestCaseRefDeleteResponse;
import com.wellpoint.wpd.common.webtesttool.response.TestSuiteChangeResponse;
import com.wellpoint.wpd.common.webtesttool.response.TestSuiteCreateResponse;
import com.wellpoint.wpd.common.webtesttool.response.TestSuiteDeleteResponse;
import com.wellpoint.wpd.common.webtesttool.response.TestSuiteExecuteResponse;
import com.wellpoint.wpd.common.webtesttool.response.TestSuiteSearchResponse;
import com.wellpoint.wpd.common.webtesttool.vo.TestCaseRefVO;
import com.wellpoint.wpd.common.webtesttool.vo.TestCaseVO;
import com.wellpoint.wpd.common.webtesttool.vo.TestSuiteVO;
import com.wellpoint.wpd.web.util.WPDStringUtil;

public class TestSuiteBusinessService  extends WPDBusinessService{

    public WPDResponse execute(TestSuiteCreateRequest testSuiteCreateRequest)throws ServiceException {
        TestSuiteCreateResponse testSuiteCreateResponse = new TestSuiteCreateResponse();
        TestSuiteBO testSuiteBO = getTestSuiteBO(testSuiteCreateRequest.getTestSuiteVO());
        TestSuiteBusinessObjectBuilder testSuiteBusinessObjectBuilder = new TestSuiteBusinessObjectBuilder();
        try{
        	TestSuiteBO countBO= (TestSuiteBO) testSuiteBusinessObjectBuilder.locateTestSuiteByName(testSuiteCreateRequest.getTestSuiteVO()).get(0);
        	if(countBO.getTestSuiteCount()>0){
        		testSuiteCreateResponse.setMessage(new ErrorMessage("testsuite.exists"));
        	}else{
	            // insert record
	            testSuiteBusinessObjectBuilder.persistTestSuite(testSuiteBO, testSuiteCreateRequest.getUser().getUserId());
	            // fetch record for further process like add test cases to test suite
	            TestSuiteVO testSuiteVO= new TestSuiteVO();
	            testSuiteVO.setTestSuiteName(testSuiteBO.getTestSuiteName());
	            testSuiteVO.setTestSuiteId(testSuiteBO.getTestSuiteId());
	            testSuiteBO = (TestSuiteBO)testSuiteBusinessObjectBuilder.locateTestSuiteById(testSuiteVO).get(0);
	            testSuiteBO.setTestCaseList(new TestSuiteBusinessObjectBuilder().locateTestCaseRef(testSuiteBO.getTestSuiteId()));
	            testSuiteCreateResponse.setTestSuiteBO(testSuiteBO);
        		testSuiteCreateResponse.setSuccess(true);
        	}
        }catch(WPDException e){
            e.printStackTrace();
            throw new ServiceException("Unexpected error",null,e);
        }
        return testSuiteCreateResponse;
    }
    
    public WPDResponse execute(TestSuiteChangeRequest testSuiteChangeRequest) throws ServiceException{
    	TestSuiteChangeResponse testSuiteChangeResponse = new TestSuiteChangeResponse();
        TestSuiteBO testSuiteBO = getTestSuiteBO(testSuiteChangeRequest.getTestSuiteVO());
        testSuiteBO.setTestSuiteId(testSuiteChangeRequest.getTestSuiteVO().getTestSuiteId());
        testSuiteBO.setLastUpdatedUser(testSuiteChangeRequest.getUser().getUserId());
        testSuiteBO.setLastUpdatedTimestamp(new Date());
        try{
        	TestSuiteBO countBO= (TestSuiteBO) new TestSuiteBusinessObjectBuilder().locateTestSuiteByName(testSuiteChangeRequest.getTestSuiteVO()).get(0);
        	if(countBO.getTestSuiteCount()>0){
        		testSuiteChangeResponse.setMessage(new ErrorMessage("testsuite.exists"));
        	}else{
        		new TestSuiteBusinessObjectBuilder().changeIdentity(testSuiteBO, testSuiteChangeRequest.getUser().getUserId());
        		testSuiteChangeResponse.setSuccess(true);
        	}
        }catch(WPDException e){
            e.printStackTrace();
            throw new ServiceException("Unexpected error",null,e);
        }
        return testSuiteChangeResponse;        
    }
    
    public WPDResponse execute(TestSuiteSearchRequest testSuiteSearchRequest)throws ServiceException {
        List results = null;
        TestSuiteSearchResponse testSuiteSearchResponse = new TestSuiteSearchResponse();
        try {
            if(testSuiteSearchRequest.isLocate())
                results = new TestSuiteBusinessObjectBuilder().locateTestSuite(testSuiteSearchRequest.getTestSuiteVO());
            else{
                results = new TestSuiteBusinessObjectBuilder().locateTestSuiteById(testSuiteSearchRequest.getTestSuiteVO());
                TestSuiteBO testSuiteBO = (TestSuiteBO) results.get(0);
                testSuiteBO.setTestCaseList(new TestSuiteBusinessObjectBuilder().locateTestCaseRef(testSuiteBO.getTestSuiteId()));
            }
        } catch (WPDException e) {
            e.printStackTrace();
            throw new ServiceException("Unexpected error",null,e);
        }      
        testSuiteSearchResponse.setTestSuiteResultList(results);
        return testSuiteSearchResponse;
    }
    
    public WPDResponse execute(TestSuiteDeleteRequest testSuiteDeleteRequest) throws ServiceException{
        
        TestSuiteBO testSuiteBO = new TestSuiteBO();
        TestSuiteVO testSuiteVO = testSuiteDeleteRequest.getTestSuiteVO();
        testSuiteBO.setTestSuiteId(testSuiteVO.getTestSuiteId());
        testSuiteBO.setTestSuiteName(testSuiteVO.getTestSuiteName());
        try{
	        new TestSuiteBusinessObjectBuilder().removeTestSuite(testSuiteBO, testSuiteDeleteRequest.getUser().getUserId());
        }catch(WPDException e){
            e.printStackTrace();
            throw new ServiceException("Unexpected error",null,e);
        }
        return new TestSuiteDeleteResponse();
    }
 
    private TestSuiteBO getTestSuiteBO(TestSuiteVO testSuiteVO){
        TestSuiteBO testSuiteBO = new TestSuiteBO();
        testSuiteBO.setTestSuiteName(testSuiteVO.getTestSuiteName());
        testSuiteBO.setTestSuiteDesc(testSuiteVO.getTestSuiteDesc());
        testSuiteBO.setContractId(testSuiteVO.getContractId());
        testSuiteBO.setStartDate(testSuiteVO.getStartDate());
        testSuiteBO.setEndDate(testSuiteVO.getEndDate());
        testSuiteBO.setTestCaseList(testSuiteVO.getSelectedTestCaseList());
        return testSuiteBO;
    }
    
    public WPDResponse execute(TestCaseRefCreateRequest testCaseRefCreateRequest)throws ServiceException {
        TestCaseRefCreateResponse testCaseRefCreateResponse = new TestCaseRefCreateResponse();
        List testCaseRefBOList = getTestCaseRefBOList(testCaseRefCreateRequest.getTestCaseRefVOList());
        try{
	        new TestSuiteBusinessObjectBuilder().persistTestCaseRef(testCaseRefBOList, testCaseRefCreateRequest.getUser().getUserId());
	        String testSuiteId = ((TestCaseRefBO)testCaseRefBOList.get(0)).getTestSuiteId();
	        testCaseRefCreateResponse.setTestCaseRefList(new TestSuiteBusinessObjectBuilder().locateTestCaseRef(testSuiteId));
        }catch(WPDException e){
            e.printStackTrace();
            throw new ServiceException("Unexpected error",null,e);
        }
        return testCaseRefCreateResponse;
    }
    public WPDResponse execute(TestCaseRefDeleteRequest testCaseRefDeleteRequest)throws ServiceException {
        TestCaseRefDeleteResponse testCaseRefDeleteResponse = new TestCaseRefDeleteResponse();
        TestCaseRefBO testCaseRefBO = getTestCaseRefBO(testCaseRefDeleteRequest.getTestCaseRefVO());
        try{
        	List testCaseRefBOList = new ArrayList();
        	testCaseRefBOList.add(testCaseRefBO);
	        new TestSuiteBusinessObjectBuilder().removeTestCaseRef(testCaseRefBOList, testCaseRefDeleteRequest.getUser().getUserId());
	        String testSuiteId = testCaseRefBO.getTestSuiteId();
	        testCaseRefDeleteResponse.setTestCaseRefList(new TestSuiteBusinessObjectBuilder().locateTestCaseRef(testSuiteId));
        }catch(WPDException e){
            e.printStackTrace();
            throw new ServiceException("Unexpected error",null,e);
        }
        return testCaseRefDeleteResponse;
    }
    private TestCaseRefBO getTestCaseRefBO(TestCaseRefVO testCaseRefVO){
        TestCaseRefBO testCaseRefBO = new TestCaseRefBO();
        testCaseRefBO.setTestCaseId(testCaseRefVO.getTestCaseId());
        testCaseRefBO.setTestSuiteId(testCaseRefVO.getTestSuiteId());
        return testCaseRefBO;
    }
    private List getTestCaseRefBOList(List testCaseRefVOList){
        List testCaseRefBOList = new ArrayList();
        TestCaseRefBO testCaseRefBO = null; 
        TestCaseRefVO testCaseRefVO = null;
        for(Iterator i = testCaseRefVOList.iterator(); i.hasNext();){
            testCaseRefVO = (TestCaseRefVO) i.next();
            testCaseRefBO = new TestCaseRefBO();
            testCaseRefBO.setTestSuiteId(testCaseRefVO.getTestSuiteId());
            testCaseRefBO.setTestCaseId(testCaseRefVO.getTestCaseId());
            testCaseRefBOList.add(testCaseRefBO);
        }
        return testCaseRefBOList;
    }

    public WPDResponse execute(TestSuiteExecuteRequest testSuiteExecuteRequest) throws ServiceException{
    	
    	List testResults = new ArrayList();
        TestSuiteExecuteResponse testSuiteExecuteResponse = new TestSuiteExecuteResponse();
        testSuiteExecuteResponse.setTestResults(testResults);
        TestSuiteBusinessObjectBuilder testSuiteBusinessObjectBuilder = new TestSuiteBusinessObjectBuilder();
        TestCaseBusinessObjectBuilder testCaseBusinessObjectBuilder = new TestCaseBusinessObjectBuilder();
        TestSuiteBO testSuiteBO = null;
        TestCaseVO testCaseVO = null;
        
        List benftCompRuleIdList;
        Map benefitIdsMap = Collections.synchronizedMap( new HashMap() );
        List accidentBenefitRuleIdList = Collections.synchronizedList( new ArrayList() );
        
        try {
        	List testCaseRefList = null;
        	List testCaseIdList = testSuiteExecuteRequest.getTestCaseIdList();
            //get test case ref ids -- TestCaseRefBO
        	testCaseRefList = testSuiteBusinessObjectBuilder.locateTestCaseRef(testSuiteExecuteRequest.getTestSuiteId(), testCaseIdList);

            //get test suite bo for further purpose
            TestSuiteVO testSuiteVO= new TestSuiteVO();
            testSuiteVO.setTestSuiteId(testSuiteExecuteRequest.getTestSuiteId());
            testSuiteBO =  (TestSuiteBO)testSuiteBusinessObjectBuilder.locateTestSuiteById(testSuiteVO).get(0);
            testSuiteExecuteResponse.setTestSuiteBO(testSuiteBO);
            
            // get benefit component rule id list
            String contractId = testSuiteBO.getContractId();
            String startDate =  WPDStringUtil.getStringDate(testSuiteBO.getStartDate());
            String endDate = WPDStringUtil.getStringDate(testSuiteBO.getEndDate());
            benftCompRuleIdList = Collections.synchronizedList(
            							testSuiteBusinessObjectBuilder.locateBeneftCompRuleID(contractId, startDate, endDate)
									);
            String supplementalBenftCompId ="";
            if(benftCompRuleIdList.size()<=0){
            	//testSuiteExecuteResponse.setMessage(new ErrorMessage("questionnaire.add.mandatory.fields"));
            	testSuiteExecuteResponse.setStatus(true);
            	return testSuiteExecuteResponse;
            }
            // get benefit rule id list
            BenefitComponentRuleIDBO benefitCompRuleID;
            List benefitCompIds;
            List supBenefitCompIds = new ArrayList();
            for(Iterator i = benftCompRuleIdList.iterator(); i.hasNext();){
            	benefitCompRuleID = (BenefitComponentRuleIDBO)i.next();
            	benefitCompIds = new ArrayList();
            	benefitCompIds.add(benefitCompRuleID.getBeneftCompId());
            	benefitIdsMap.put(benefitCompRuleID.getBeneftCompId(),
            			testSuiteBusinessObjectBuilder.locateBeneftRuleID(contractId, startDate, endDate, benefitCompIds)
								 );
            	if("SUPPLEMENTAL BENEFITS".equals(benefitCompRuleID.getBeneftCompName())){
            		supBenefitCompIds.add(benefitCompRuleID.getBeneftCompId());
            	}
            }
            // Check for benefit which has name like "ACCIDENT RIDER"
            if(supBenefitCompIds.size()>0){
	            List supBenefitRuleIdList = testSuiteBusinessObjectBuilder.locateBeneftRuleID(contractId, startDate, endDate, supBenefitCompIds);
	            for(Iterator i = supBenefitRuleIdList.iterator(); i.hasNext(); ){
	            	BenefitRuleIDBO benefitRuleID = (BenefitRuleIDBO)i.next();
	            	if( "ACCIDENT RIDER".equals(benefitRuleID.getBenefitName()) ){
	            		accidentBenefitRuleIdList.add(benefitRuleID);
	            	}
	            }
            }
            BlazeServiceUtil service = null;
            List threadGroup = new ArrayList();
            for(Iterator i = testCaseRefList.iterator(); i.hasNext();){
                TestCaseRefBO testCaseRefBO = (TestCaseRefBO) i.next();
                TestCaseBO testCaseBO = testCaseBusinessObjectBuilder.locateTestCaseById(testCaseRefBO.getTestCaseId());
                service = new BlazeServiceUtil(testCaseBO, benftCompRuleIdList, accidentBenefitRuleIdList, benefitIdsMap);
                service.start();
                //service.process();
                threadGroup.add(service);
                testResults.add(testCaseBO);
            }
            long start = System.currentTimeMillis();
            long span;
            //All threads are finished their job
            for(Iterator i = threadGroup.iterator(); i.hasNext();){
            	service = (BlazeServiceUtil)i.next();
            	while(service.isAlive()){
            		span = (System.currentTimeMillis() -start);
            		//wait until 5 min to finish blaze service jobs
            		if(span < 300000){
            			service.join(3000);
            		} else{
            			service.interrupt();
            			//service.stop();
            			break;
            		}
            	}
            }
            for(Iterator i = threadGroup.iterator(); i.hasNext();){
            	service = (BlazeServiceUtil)i.next();
            	if(!service.isStatus()){
            		testSuiteExecuteResponse.setMessage(new ErrorMessage("common.default"));
            		testSuiteExecuteResponse.setStatus(false);
            		break;
            		//throw new ServiceException("Unexpected error",null, service.getException());
            	}
            	testSuiteExecuteResponse.setStatus(true);
            }
            if(threadGroup.size()<=0)
            	testSuiteExecuteResponse.setStatus(true);
        } catch (SevereException e) {
            e.printStackTrace();
            throw new ServiceException("Unexpected error",null,e);
        }catch(InterruptedException e1){
            e1.printStackTrace();
            throw new ServiceException("Unexpected error",null,e1);
        }
        return testSuiteExecuteResponse;
    }
}
