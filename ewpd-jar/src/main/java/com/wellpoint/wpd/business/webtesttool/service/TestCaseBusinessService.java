/* 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.webtesttool.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.webtesttool.builder.TestCaseBusinessObjectBuilder;

import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.webtesttool.bo.ClaimHeaderBO;
import com.wellpoint.wpd.common.webtesttool.bo.ClaimLineBO;
import com.wellpoint.wpd.common.webtesttool.bo.TestCaseBO;
import com.wellpoint.wpd.common.webtesttool.request.BenefitComponentPopupRequest;
import com.wellpoint.wpd.common.webtesttool.request.BenefitComponentSearchRequest;
import com.wellpoint.wpd.common.webtesttool.request.BenefitPopupRequest;
import com.wellpoint.wpd.common.webtesttool.request.TestCaseChangeRequest;
import com.wellpoint.wpd.common.webtesttool.request.TestCaseCreateRequest;
import com.wellpoint.wpd.common.webtesttool.request.TestCaseDeleteRequest;
import com.wellpoint.wpd.common.webtesttool.request.TestCaseEditRequest;
import com.wellpoint.wpd.common.webtesttool.request.TestCaseSearchRequest;

import com.wellpoint.wpd.common.webtesttool.response.BenefitComponentPopupResponse;
import com.wellpoint.wpd.common.webtesttool.response.BenefitComponentSearchResponse;
import com.wellpoint.wpd.common.webtesttool.response.BenefitPopupResponse;
import com.wellpoint.wpd.common.webtesttool.response.TestCaseChangeResponse;
import com.wellpoint.wpd.common.webtesttool.response.TestCaseCreateResponse;
import com.wellpoint.wpd.common.webtesttool.response.TestCaseDeleteResponse;
import com.wellpoint.wpd.common.webtesttool.response.TestCaseEditResponse;
import com.wellpoint.wpd.common.webtesttool.response.TestCaseSearchResponse;


import com.wellpoint.wpd.common.webtesttool.vo.ClaimHeaderVO;
import com.wellpoint.wpd.common.webtesttool.vo.ClaimLineVO;
import com.wellpoint.wpd.common.webtesttool.vo.TestCaseVO;

public class TestCaseBusinessService  extends WPDBusinessService{

	ClaimLineVO claimLineVO;
	public WPDResponse execute(TestCaseSearchRequest testCaseSearchRequest)throws ServiceException {
		Logger.logDebug("TC BUS SERVICE: ## locate TEST CASE METHOD");
        List results = null;
        TestCaseSearchResponse testCaseSearchResponse = new TestCaseSearchResponse();        
        try {
            results = new TestCaseBusinessObjectBuilder().locateTestCase(testCaseSearchRequest.getTestCaseVO());
        } catch (WPDException e) {
            e.printStackTrace();
            throw new ServiceException("Unexpected error",null,e);
        }        
        testCaseSearchResponse.setTestCaseResultList(results);
        return testCaseSearchResponse;
    }
	
    public WPDResponse execute(TestCaseEditRequest testCaseEditRequest)throws ServiceException {
    	Logger.logDebug("TC BUS SERVICE: >> EDIT TEST CASE METHOD");
    	ClaimHeaderBO claimHeaderBO = new ClaimHeaderBO();
        TestCaseBO testCaseBO = new TestCaseBO();
        TestCaseEditResponse testCaseEditResponse = new TestCaseEditResponse();
        String testCaseId = testCaseEditRequest.getTestCaseVO().getTestCaseId();
        try {
        	testCaseBO = new TestCaseBusinessObjectBuilder().locateTestCaseById(testCaseId);
        	changeBOtoVO(testCaseBO);
        } catch (WPDException e) {
            e.printStackTrace();
            throw new ServiceException("Unexpected error",null,e);
        }
      
        // get claim line bo and put claim line vo
        
        testCaseEditResponse.setTestCaseBO(testCaseBO);
        
        return testCaseEditResponse;
    }
    
    public WPDResponse execute(TestCaseDeleteRequest testCaseDeleteRequest) throws ServiceException{
        
    	boolean result;
    	TestCaseDeleteResponse testCaseDeleteResponse = new TestCaseDeleteResponse();
        TestCaseBO testCaseBO = new TestCaseBO();
        TestCaseVO testCaseVO = testCaseDeleteRequest.getTestCaseVO();
        testCaseBO.setTestCaseId(testCaseVO.getTestCaseId());
        testCaseBO.setTestCaseName(testCaseVO.getTestCaseName());
        try{
        	result = new TestCaseBusinessObjectBuilder().removeTestCase(testCaseBO, testCaseDeleteRequest.getUser().getUserId());
        	testCaseDeleteResponse.setSuccess(result);
        }catch(WPDException e){
            e.printStackTrace();
            throw new ServiceException("Unexpected error",null,e);
        }
        return testCaseDeleteResponse;
    }
    public WPDResponse execute(BenefitComponentPopupRequest benefitComponentPopupRequest)throws ServiceException {
    	
        List results = null;
        BenefitComponentPopupResponse benefitComponentPopupResponse = new BenefitComponentPopupResponse();        
        try {
            results = new TestCaseBusinessObjectBuilder().locateBenefitComponent(benefitComponentPopupRequest.getClaimLineVO(),benefitComponentPopupRequest.getUser().getUserId());
        } catch (WPDException e) {
            e.printStackTrace();
            throw new ServiceException("Unexpected error",null,e);
        }        
        benefitComponentPopupResponse.setExpectedBenefitComponentList(results);
        return benefitComponentPopupResponse;
    }
    public WPDResponse execute(BenefitComponentSearchRequest benefitComponentSearchRequest)throws ServiceException {
    	Logger.logDebug("TC BUS SERVICE : BEN COMPT SEARCH REQUEST");
        List results = null;
        BenefitComponentSearchResponse benefitComponentSearchResponse = new BenefitComponentSearchResponse();        
        try {
            results = new TestCaseBusinessObjectBuilder().locateBenefitComponentForPopup(benefitComponentSearchRequest.getClaimLineVO(),benefitComponentSearchRequest.getUser().getUserId());
            Logger.logDebug("TC BUS SERVICE : return LIST SIZE"+results.size());
        } catch (WPDException e) {
            e.printStackTrace();
            throw new ServiceException("Unexpected error",null,e);
        }        
        benefitComponentSearchResponse.setBenefitComponentResultList(results);
        return benefitComponentSearchResponse;
    }
    public WPDResponse execute(BenefitPopupRequest benefitPopupRequest)throws ServiceException {
    	Logger.logDebug("Inside TC Business service >>>");
    	claimLineVO = new ClaimLineVO();
    	claimLineVO.setBenefitComponentId(benefitPopupRequest.getBenefitComponentId());
    	claimLineVO.setBenefitType(benefitPopupRequest.getBenefitType());
    	
        List results = null;
        BenefitPopupResponse benefitPopupResponse = new BenefitPopupResponse();        
        try {
          results = new TestCaseBusinessObjectBuilder().locateBenefit(claimLineVO,benefitPopupRequest.getUser().getUserId());
          
        } catch (WPDException e) {
           e.printStackTrace();
           throw new ServiceException("Unexpected error",null,e);
        }        
        benefitPopupResponse.setExpectedBenefitList(results);
        return benefitPopupResponse;
    }
    public WPDResponse execute(TestCaseCreateRequest testCaseCreateRequest)throws ServiceException {
    	Logger.logDebug("business service TEST CASE CREATION");
    	 TestCaseBO testCaseBO = getTestCaseBO(testCaseCreateRequest.getTestCaseVO());
    	 ClaimHeaderBO claimHeaderBO = getClaimHeaderBO(testCaseCreateRequest.getClaimHeaderVO());
    	 List claimLineList = testCaseCreateRequest.getClaimLineList();
    	 TestCaseCreateResponse resp = new TestCaseCreateResponse();
    	 Logger.logDebug("test case name >>>>>"+testCaseBO.getTestCaseName());
         try{
         	 List testCaseList = new TestCaseBusinessObjectBuilder().locateTestCaseByName(testCaseBO.getTestCaseName(), null);
         	 int tcCount = ((TestCaseBO)testCaseList.get(0)).getTestCaseCount();
         	 Logger.logDebug("TC BUS SERVICE LAYER -- LIST SIZE"+testCaseList.size());
         	 if(tcCount>0){
         	 	Logger.logDebug("TC BUS SERVICE LAYER -- TC ALREADY EXISTS");
         	 	resp.setSuccess(false);        
         	 }else{
         	 	Logger.logDebug("TC BUS SERVICE LAYER -- TC NOT EXISTS ALREADY");
         	 	new TestCaseBusinessObjectBuilder().persistTestCase(testCaseBO,claimHeaderBO,claimLineList, testCaseCreateRequest.getUser().getUserId());
         	 	testCaseBO = new TestCaseBusinessObjectBuilder().locateTestCaseById(testCaseBO.getTestCaseId());
         	 	changeBOtoVO(testCaseBO);
         	 	resp.setSuccess(true);
                resp.setTestCaseId(testCaseBO.getTestCaseId());
                resp.setTestCaseBO(testCaseBO);
         	 	        
         	 }
         }catch(WPDException e){
             e.printStackTrace();
             throw new ServiceException("Unexpected error",null,e);
         }
         
         
         return resp;
    }

    public WPDResponse execute(TestCaseChangeRequest testCaseChangeRequest) throws ServiceException{
    	Logger.logDebug("TC BUSINESS SERVICE UPDATE >>>>>>");
        TestCaseBO testCaseBO = getTestCaseBO(testCaseChangeRequest.getTestCaseVO());
        ClaimHeaderBO claimHeaderBO = getClaimHeaderBO(testCaseChangeRequest.getClaimHeaderVO());
        List claimLineList = testCaseChangeRequest.getClaimLineList();
        TestCaseChangeResponse resp = new TestCaseChangeResponse();
        boolean isEdit = testCaseChangeRequest.isEdit();
        Logger.logDebug("test case name >>>>>"+testCaseBO.getTestCaseName());
        try{
        	 List testCaseList = new TestCaseBusinessObjectBuilder().locateTestCaseByName(testCaseBO.getTestCaseName(), testCaseBO.getTestCaseId());
             Logger.logDebug("TC BUS SERVICE LAYER UPDATE-- LIST SIZE"+testCaseList.size());
             int tcCount = ((TestCaseBO)testCaseList.get(0)).getTestCaseCount();
             if(tcCount>0){
            	Logger.logDebug("TC BUS SERVICE LAYER -- TC ALREADY EXISTS");
         	 	resp.setSuccess(false);    
             }else{
             	
             	new TestCaseBusinessObjectBuilder().changeTestCase(testCaseBO,claimHeaderBO,claimLineList,isEdit, testCaseChangeRequest.getUser().getUserId());
             	resp.setSuccess(true);
             }
             
        }catch(WPDException e){
            e.printStackTrace();
            throw new ServiceException("Unexpected error",null,e);
        }
        return resp;        
    }
    private TestCaseBO getTestCaseBO(TestCaseVO testCaseVO){
        TestCaseBO testCaseBO = new TestCaseBO();
        testCaseBO.setTestCaseId(testCaseVO.getTestCaseId());
        testCaseBO.setTestCaseName(testCaseVO.getTestCaseName());
        testCaseBO.setTestCaseDesc(testCaseVO.getTestCaseDesc());
        return testCaseBO;
    }
    private ClaimHeaderBO getClaimHeaderBO(ClaimHeaderVO claimHeaderVO){
    	ClaimHeaderBO claimHeaderBO = new ClaimHeaderBO();
    	
    	claimHeaderBO.setClaimType(claimHeaderVO.getClaimType());
    	claimHeaderBO.setItsProvSpec(claimHeaderVO.getItsProvSpec());
    	claimHeaderBO.setEdit(claimHeaderVO.getEdit());
    	claimHeaderBO.setProviderId(claimHeaderVO.getProviderId());
    	claimHeaderBO.setHospitalFacilityCode(claimHeaderVO.getHospitalFacilityCode());
    	claimHeaderBO.setDaysFromInjury(claimHeaderVO.getDaysFromInjury());
    	claimHeaderBO.setMedAssignIndicator(claimHeaderVO.getMedAssignIndicator());
    	claimHeaderBO.setGender(claimHeaderVO.getGender());
    	claimHeaderBO.setMemberRelationshipCode(claimHeaderVO.getMemberRelationshipCode());
    	claimHeaderBO.setAge(claimHeaderVO.getAge());
    	claimHeaderBO.setGroupState(claimHeaderVO.getGroupState());
    	claimHeaderBO.setItsClaim(claimHeaderVO.getItsClaim());
        return claimHeaderBO;
    }
    private TestCaseVO getTestCaseVO(TestCaseBO testCaseBO){
    	TestCaseVO testCaseVO = new TestCaseVO();
    	testCaseVO.setTestCaseId(testCaseBO.getTestCaseId());
    	testCaseVO.setTestCaseName(testCaseBO.getTestCaseName());
    	testCaseVO.setTestCaseDesc(testCaseBO.getTestCaseDesc());
        return testCaseVO;
    }
    private ClaimHeaderVO getClaimHeaderVO(ClaimHeaderBO claimHeaderBO){
    	
    	ClaimHeaderVO claimHeaderVO = new ClaimHeaderVO();
    	
    	claimHeaderVO.setClaimType(claimHeaderBO.getClaimType());
    	claimHeaderVO.setItsProvSpec(claimHeaderBO.getItsProvSpec());
    	claimHeaderVO.setEdit(claimHeaderBO.getEdit());
    	claimHeaderVO.setProviderId(claimHeaderBO.getProviderId());
    	claimHeaderVO.setHospitalFacilityCode(claimHeaderBO.getHospitalFacilityCode());
    	claimHeaderVO.setDaysFromInjury(claimHeaderBO.getDaysFromInjury());
    	claimHeaderVO.setMedAssignIndicator(claimHeaderBO.getMedAssignIndicator());
    	claimHeaderVO.setGender(claimHeaderBO.getGender());
    	claimHeaderVO.setMemberRelationshipCode(claimHeaderBO.getMemberRelationshipCode());
    	claimHeaderVO.setAge(claimHeaderBO.getAge());
    	claimHeaderVO.setGroupState(claimHeaderBO.getGroupState());
    	claimHeaderVO.setItsClaim(claimHeaderBO.getItsClaim());
    	
        return claimHeaderVO;
    }
    private void changeBOtoVO(TestCaseBO testCaseBO){
    	List tempList = new ArrayList();
    	ClaimHeaderBO claimHeaderBO=null;
    	ClaimHeaderVO claimHeaderVO= null;
    	TestCaseVO testCaseVO =null;
    	ClaimLineBO claimLineBO = null;
    	ClaimLineVO claimLineVO = null;
    	for(Iterator i = testCaseBO.getClaimLineDetailList().iterator(); i.hasNext();){
    		claimLineBO = (ClaimLineBO)i.next();
    		tempList.add(getClaimLineVO(claimLineBO));
    	}
    	testCaseBO.setClaimLineDetailList(tempList);
    	// populating claim header VO and testcase VO
    	claimHeaderVO = getClaimHeaderVO(testCaseBO.getClaimHeaderBO());
    	testCaseBO.setClaimHeaderVO(claimHeaderVO);
    	testCaseVO = getTestCaseVO(testCaseBO);
    	testCaseBO.setTestCaseVO(testCaseVO);
    	
    }
    private ClaimLineVO getClaimLineVO(ClaimLineBO claimLineBO){
    	ClaimLineVO claimLineVO = new ClaimLineVO();
    	claimLineVO.setTestCaseId(claimLineBO.getTestCaseId());
    	claimLineVO.setClaimLineId(claimLineBO.getClaimLineId());
    	claimLineVO.setDiagnosisCode(claimLineBO.getDiagnosisCode());
    	claimLineVO.setTtCode(claimLineBO.getTtCode());
    	claimLineVO.setPlaceOfService(claimLineBO.getPlaceOfService());
    	claimLineVO.setHcpcCode(claimLineBO.getHcpcCode());
    	claimLineVO.setRevenueCode(claimLineBO.getRevenueCode());
    	claimLineVO.setTypeOfBill(claimLineBO.getTypeOfBill());
    	claimLineVO.setModifierCode(claimLineBO.getModifierCode());
    	claimLineVO.setExpectedBenefitComponent(claimLineBO.getExpectedBenefitComponent());
    	claimLineVO.setExpectedBenefit(claimLineBO.getExpectedBenefit());
    	claimLineVO.setExpectedBasicBenefit(claimLineBO.getExpectedBasicBenefit());
    	claimLineVO.setExpectedRiderBenefit(claimLineBO.getExpectedRiderBenefit());
    	return claimLineVO;
	 }
}
