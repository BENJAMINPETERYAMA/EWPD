/*
 * TestCaseBackingBean.java 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.webtesttool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.component.html.HtmlDataTable;

import com.wellpoint.wpd.business.util.BusinessConstants;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.messages.Message;
import com.wellpoint.wpd.common.webtesttool.bo.ClaimHeaderBO;
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
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;


// retrives all benefits for given contract
public class TestCaseBackingBean  extends WPDBackingBean{

	public static String EDIT_TEST_CASE 			   = "editTestCase";
	public static String COPY_TEST_CASE 			   = "copyTestCase";
	public static String CREATE_TEST_CASE   		   = "createTestCase";
	public static String MAINTAIN_TEST_CASE 		   = "maintainTestCase";
	public static String POPUP_TEST_CASE			   = "testCasePopup";
	public static String BENEFIT_CMPNT_POPUP_TEST_CASE = "benefitComponentPopup";
	
	private int lineNumberRef;
	// Test case properties
	private int testCaseId;
	private String testCaseName;
	private String testCaseDesc;
	private List testCaseList;
	private String testCaseIdRef;// for edit operation
	private String testCaseNameRef; // for delete operation
	
	private List validationMessages; // Validation Messages
	
	// Claim Header Properties
	private String claimType;
	private String providerId;
	private String medAssignIndicator;
	private String age;
	private String itsProvSpec;
	private String hospitalFacilityCode;
	private String gender;
	private String groupState;
	private String edit;
	private String daysFromInjury;
	private String memberRelationshipCode;
	private boolean itsClaim;
	
	// Claim Line Properties
	private String claimLineId;
	private String diagnosisCode;
	private String hcpcCode;
	private String modifierCode;
	private String ttCode;
	private String revenueCode;
	private String typeOfBill;
	private String placeOfService;
	private String expectedBenefitComponent;
	private String expectedBenefit;
	private String expectedBasicBenefit;
	private String expectedRiderBenefit;
	
	// locate testcase
	private String benefitComponent;
	private String benefit;
	
	// Popup List
	private List expBenefitCompntList;
	private List expectedBenefitList=new ArrayList();
	
	// Populating combobox List
	private List claimTypeList;
	private List medAssignIndicatorList;
	private List hospitalFacilityCodeList;
	private List genderList;
	private List groupStateList;
	private List memberRelationshipCodeList;
	private List placeOfServiceList;
	private List typeOfBillList;
	
	private List claimLineList = new ArrayList();
	
	 private HtmlDataTable dataTable;
	 private boolean isEdit=false;
	 private boolean isClaimLineEdit;
	 private boolean isCreate;
	 
	 private boolean requiredField ;
	 private boolean testCaseNameEntry;
	 private boolean testCaseDescEntry;
	 private boolean exptdBnftCompEntry;
	 private boolean checkTestCaseExist;
	 private boolean diagnoscodeEntry;
	
	public String createTestCase() {
		
		clearTestCaseEntryFileds();
		if(!validateRequiredFields()) {
		
			TestCaseVO testCaseVO = new TestCaseVO();
			ClaimHeaderVO claimHeaderVO = new ClaimHeaderVO();
			
			testCaseVO = populateTestCaseVO();
			claimHeaderVO = populateClaimHeaderVO();
			populateClaimLineVO();
		
			TestCaseCreateRequest testCaseCreateRequest = (TestCaseCreateRequest)this.getServiceRequest(ServiceManager.TESTCASE_CREATE_REQUEST);
			testCaseCreateRequest.setTestCaseVO(testCaseVO);
			testCaseCreateRequest.setClaimHeaderVO(claimHeaderVO);
			testCaseCreateRequest.setClaimLineList(claimLineList);
			
			TestCaseCreateResponse testCaseCreateResponse = (TestCaseCreateResponse)this.executeService(testCaseCreateRequest);
			checkTestCaseExist = testCaseCreateResponse.isSuccess();
			if(checkTestCaseExist){
				
				TestCaseBO testCaseBO = testCaseCreateResponse.getTestCaseBO();
				ClaimHeaderBO claimHeaderBO = testCaseBO.getClaimHeaderBO();
				List claimLineDetailList=testCaseBO.getClaimLineDetailList();
				TestCaseVO tcVO = testCaseBO.getTestCaseVO();
				claimHeaderVO = testCaseBO.getClaimHeaderVO();
		
				if(testCaseBO != null){
					populateTestCaseInfo(tcVO);
				}				
				if(claimHeaderBO !=null){
					populateClaimHeaderInfo(claimHeaderVO);
				}
				if(claimLineDetailList!=null){
					this.claimLineList=claimLineDetailList;
				}
				InformationalMessage successMsg = new InformationalMessage(BusinessConstants.MSG_TESTCASE_SAVE_SUCCESS);
				addMessageToRequest( successMsg );

				try{
					this.testCaseId =Integer.parseInt(testCaseCreateResponse.getTestCaseId());
				}catch(Exception e){
					this.testCaseId = 0;
				}
				isEdit = true;
				isCreate=true;
				setBreadCrumbText("Benefit Selection >> Test Case (" + testCaseName +") >> Edit");
				return CREATE_TEST_CASE;
			}else{
				addMessageToRequest( new ErrorMessage(BusinessConstants.MSG_TESTCASE_ALREADY_EXISTS));
				setBreadCrumbText("Benefit Selection >> Test Case >> Create");
				return CREATE_TEST_CASE;
			}
			
		}
		return "";
		
	}
	
	private boolean validateRequiredFields(){
		requiredField = false;
		if(testCaseName == null || "".equals(testCaseName.trim())){
			requiredField = true;
			testCaseNameEntry = true;
		}
		if(requiredField){
			Message errMessage = new ErrorMessage("contract.rule.mandatory.valid");
			addMessageToRequest(errMessage);
		}
		if( (testCaseDesc != null) && (testCaseDesc.length()>299)){
			Logger.logDebug("INSIDE TEST CASE DESC VALIDATION");
			testCaseDescEntry = true;
			requiredField = true;
			addMessageToRequest(new ErrorMessage("wtt.description.invalid.length"));
		}
		setBreadCrumbText("Benefit Selection >> Test Case >> Create");
		return requiredField;
		
	}
	public String copyTestCase(){
		isEdit = true;
		isCreate=false;
		Logger.logDebug(testCaseIdRef + " COPY TEST CASE --- testCaseIdRef ........................... ");
		TestCaseEditRequest testCaseEditRequest = (TestCaseEditRequest)this.getServiceRequest(ServiceManager.TESTCASE_EDIT_REQUEST);
	    
		TestCaseVO testCaseVO = new TestCaseVO();
		ClaimHeaderVO claimHeaderVO = new ClaimHeaderVO();
		testCaseVO.setTestCaseId(this.getTestCaseIdRef());
		testCaseVO.setTestCaseName(this.getTestCaseNameRef());
		testCaseEditRequest.setTestCaseVO(testCaseVO);
		
		TestCaseEditResponse testCaseEditResponse = (TestCaseEditResponse)this.executeService(testCaseEditRequest);
		TestCaseBO testCaseBO = testCaseEditResponse.getTestCaseBO();
		ClaimHeaderBO claimHeaderBO = testCaseBO.getClaimHeaderBO();
		List claimLineDetailList=testCaseBO.getClaimLineDetailList();
		
		// new code for VO object addition
		
		TestCaseVO tcVO = testCaseBO.getTestCaseVO();
		claimHeaderVO = testCaseBO.getClaimHeaderVO();
	
		if(claimHeaderBO !=null){
			populateClaimHeaderInfo(claimHeaderVO);
		}
		if(claimLineDetailList!=null){
			this.claimLineList=claimLineDetailList;
		}
		clearTestCaseDetail();
		setBreadCrumbText("Benefit Selection >> Test Case >> Copy");
	    return CREATE_TEST_CASE;	
	}
	public String editTestCase(){
			isEdit = true;
			isCreate=true;
			Logger.logDebug(testCaseIdRef + " EDIT TEST CASE : testCaseIdRef ........................... ");
			TestCaseEditRequest testCaseEditRequest = (TestCaseEditRequest)this.getServiceRequest(ServiceManager.TESTCASE_EDIT_REQUEST);
		    // create Value Object for Test Case Request
			TestCaseVO testCaseVO = new TestCaseVO();
			ClaimHeaderVO claimHeaderVO = new ClaimHeaderVO();
			testCaseVO.setTestCaseId(this.getTestCaseIdRef());
			testCaseVO.setTestCaseName(this.getTestCaseNameRef());
			testCaseEditRequest.setTestCaseVO(testCaseVO);
			//call service layer and process the request
			TestCaseEditResponse testCaseEditResponse = (TestCaseEditResponse)this.executeService(testCaseEditRequest);
			TestCaseBO testCaseBO = testCaseEditResponse.getTestCaseBO();
			ClaimHeaderBO claimHeaderBO = testCaseBO.getClaimHeaderBO();
			List claimLineDetailList=testCaseBO.getClaimLineDetailList();
			
			// new code for VO object addition
			TestCaseVO tcVO = testCaseBO.getTestCaseVO();
			claimHeaderVO = testCaseBO.getClaimHeaderVO();
	
			if(testCaseBO != null){
				populateTestCaseInfo(tcVO);
			}				
			if(claimHeaderBO !=null){
				populateClaimHeaderInfo(claimHeaderVO);
			}
			if(claimLineDetailList!=null){
				this.claimLineList=claimLineDetailList;
			}
			setBreadCrumbText("Benefit Selection >> Test Case (" + testCaseName +") >> Edit");
		    return CREATE_TEST_CASE;		    
	}
	public String updateTestCase(){
	    isEdit = true;
		clearTestCaseEntryFileds();
	    if(!validateRequiredFields()) {
		    TestCaseVO testCaseVO = new TestCaseVO();
		    ClaimHeaderVO claimHeaderVO =new ClaimHeaderVO();
		    
		    int testCaseId = this.getTestCaseId();
		    if(testCaseId !=0){
		    	testCaseVO.setTestCaseId(String.valueOf(testCaseId));
		    	testCaseVO.setTestCaseName(this.getTestCaseName());
		    	testCaseVO.setTestCaseDesc(this.getTestCaseDesc());
		    }	
		    claimHeaderVO = populateClaimHeaderVO();
		    claimHeaderVO.setTestCaseId(testCaseId);
		    TestCaseChangeRequest testCaseChangeRequest = (TestCaseChangeRequest)this.getServiceRequest(ServiceManager.TESTCASE_CHANGE_REQUEST);
		    testCaseChangeRequest.setTestCaseVO(testCaseVO);
		    testCaseChangeRequest.setClaimHeaderVO(claimHeaderVO);
		    testCaseChangeRequest.setEdit(true);
		    testCaseChangeRequest.setClaimLineList(claimLineList);
		    
			TestCaseChangeResponse testCaseChangeResponse = (TestCaseChangeResponse)this.executeService(testCaseChangeRequest);
			checkTestCaseExist = testCaseChangeResponse.isSuccess();
			if(checkTestCaseExist){
				InformationalMessage successMsg = new InformationalMessage(BusinessConstants.MSG_TESTCASE_UPDATE_SUCCESS);
				addMessageToRequest( successMsg );
			}else{
				addMessageToRequest( new ErrorMessage(BusinessConstants.MSG_TESTCASE_ALREADY_EXISTS));				
				
			}
			
	    }
		
		isCreate=true; // It is in edit mode and save button should be shown.
		setBreadCrumbText("Benefit Selection >> Test Case (" + testCaseName +") >> Edit");
	    return "";
	}
	private ClaimHeaderVO populateClaimHeaderVO(){
		ClaimHeaderVO claimHeaderVO = new ClaimHeaderVO();
		claimHeaderVO.setClaimType(this.getClaimType());
		claimHeaderVO.setProviderId(this.getProviderId());
		claimHeaderVO.setMedAssignIndicator(this.getMedAssignIndicator());
		claimHeaderVO.setAge(this.getAge());
		claimHeaderVO.setItsProvSpec(this.getItsProvSpec());
		claimHeaderVO.setHospitalFacilityCode(this.getHospitalFacilityCode());
		claimHeaderVO.setGender(this.getGender());
		claimHeaderVO.setGroupState(this.getGroupState());
		claimHeaderVO.setEdit(this.getEdit());
		claimHeaderVO.setDaysFromInjury(this.getDaysFromInjury());
		claimHeaderVO.setMemberRelationshipCode(this.getMemberRelationshipCode());
		if(this.isItsClaim()){
			claimHeaderVO.setItsClaim("Y");
		}else{
			claimHeaderVO.setItsClaim("N");
		}
		return claimHeaderVO;
	}
	private void populateClaimLineVO(){
		TestCaseVO testCaseVO = new TestCaseVO();
		testCaseVO.setClaimLineDetailList(claimLineList);
	}
	private TestCaseVO populateTestCaseVO(){
		TestCaseVO testCaseVO = new TestCaseVO();
		testCaseVO.setTestCaseName(this.getTestCaseName());
		testCaseVO.setTestCaseDesc(this.getTestCaseDesc());
		return testCaseVO;
	}
	private void populateTestCaseInfo(TestCaseVO testCaseVO){
		if(testCaseVO != null){
			this.setTestCaseId(Integer.parseInt(testCaseVO.getTestCaseId()));			
		}	
		this.setTestCaseName(testCaseVO.getTestCaseName());
		this.setTestCaseDesc(testCaseVO.getTestCaseDesc());
	}
	private void populateClaimHeaderInfo(ClaimHeaderVO claimHeaderVO){
		
		this.setClaimType(claimHeaderVO.getClaimType());
		this.setProviderId(claimHeaderVO.getProviderId());
		this.setMedAssignIndicator(claimHeaderVO.getMedAssignIndicator());
		this.setAge(claimHeaderVO.getAge());
		this.setItsProvSpec(claimHeaderVO.getItsProvSpec());
		this.setHospitalFacilityCode(claimHeaderVO.getHospitalFacilityCode());
		this.setGender(claimHeaderVO.getGender());
		this.setGroupState(claimHeaderVO.getGroupState());
		this.setEdit(claimHeaderVO.getEdit());
		this.setDaysFromInjury(claimHeaderVO.getDaysFromInjury());
		this.setMemberRelationshipCode(claimHeaderVO.getMemberRelationshipCode());
		if(claimHeaderVO.getItsClaim().equals("Y")){
			this.setItsClaim(true);
		}else{
			this.setItsClaim(false);
		}
		
	}
	public String addClaimLine(){
		if(	isEdit == true){
			setBreadCrumbText("Benefit Selection >> Test Case  (" + testCaseName +") >> Edit");
		}else{
			setBreadCrumbText("Benefit Selection >> Test Case >> Create");
		}
		
		Logger.logDebug("inside add claim line >>>> I am here"); 
		ClaimLineVO claimLineVO = new ClaimLineVO();
		int lineNo = getClaimLineRowId();
		
		// Calls Validation for Manadatory field
		if(validateMandatoryFieldsForClaimLine()){
			
			claimLineVO.setDiagnosisCode(this.getDiagnosisCode());
			claimLineVO.setHcpcCode(this.getHcpcCode());
			claimLineVO.setModifierCode(this.getModifierCode());
			claimLineVO.setTtCode(this.getTtCode());
			claimLineVO.setRevenueCode(this.getRevenueCode());
			claimLineVO.setPlaceOfService(this.getPlaceOfService());
			claimLineVO.setTypeOfBill(this.getTypeOfBill());
			claimLineVO.setExpectedBenefitComponent(this.getExpectedBenefitComponent());
			claimLineVO.setExpectedBenefit(this.getExpectedBenefit());
			claimLineVO.setExpectedBasicBenefit(this.getExpectedBasicBenefit());
			claimLineVO.setExpectedRiderBenefit(this.getExpectedRiderBenefit());		
			claimLineList.add(claimLineVO);
			
			String tempBill="";
			if( (this.getTypeOfBill()!= null) && !(this.getTypeOfBill().equals("")) )
				tempBill = this.getTypeOfBill().split("~")[0];
			
			claimLineVO.setTypeOfBillCode(tempBill);  
				
			String tempBenftComp = "";
			if( (this.getExpectedBenefitComponent()!= null) && !(this.getExpectedBenefitComponent().equals("")) )
					tempBenftComp = this.getExpectedBenefitComponent().split("~")[1];
			claimLineVO.setExpectedBenefitComponentDesc(tempBenftComp);
			
			String tempMajDesc ="";
			if( (this.getExpectedBenefit()!= null) && !(this.getExpectedBenefit().equals("")) )
				tempMajDesc = this.getExpectedBenefit().split("~")[1];
			claimLineVO.setExpectedMajBenefitDesc(tempMajDesc);
			
			String tempBasicDesc ="";
			if( (this.getExpectedBasicBenefit()!= null) && !(this.getExpectedBasicBenefit().equals("")) )
				tempBasicDesc = this.getExpectedBasicBenefit().split("~")[1];
			claimLineVO.setExpectedBasicBenefitDesc(tempBasicDesc);
			
			String tempRiderDesc ="";
			if( (this.getExpectedRiderBenefit()!= null) && !(this.getExpectedRiderBenefit().equals("")) )
				tempRiderDesc = this.getExpectedRiderBenefit().split("~")[1];
			claimLineVO.setExpectedRiderBenefitDesc(tempRiderDesc);
				
			clearClaimLineDetail();
			return CREATE_TEST_CASE;
		}
		
		return "";
	}
	
	private boolean validateMandatoryFieldsForClaimLine(){
		boolean reqFldEntered=true;
		if(this.getExpectedBenefitComponent()==null||"".equals(this.getExpectedBenefitComponent().trim())){
			reqFldEntered=false;
			exptdBnftCompEntry=true;
		}
		if(!reqFldEntered){
			Message errMessage = new ErrorMessage("contract.rule.mandatory.valid");
			addMessageToRequest(errMessage);
		}
		
		ClaimLineVO claimLineVO = null;
		Set tempSet = new HashSet();
		for(Iterator i = claimLineList.iterator(); i.hasNext(); ){
			claimLineVO = (ClaimLineVO)i.next();
			if(claimLineVO.getDiagnosisCode() != null)
				tempSet.add(claimLineVO.getDiagnosisCode());
		}
		if(getDiagnosisCode() != null)
			tempSet.add(getDiagnosisCode());
		
		if(tempSet.size()>5){
			reqFldEntered=false;
			Message errMessage = new ErrorMessage("testcase.diagnosiscode");
			addMessageToRequest(errMessage);
			diagnoscodeEntry = true;
		}
		
		return reqFldEntered;
	}
	private int getClaimLineRowId(){
		 int lno = this.dataTable.getRowCount();
		 Logger.logDebug("line no @@@@"+lno);
		 return lno;
	}
	public String editClaimLine(){
		isClaimLineEdit = true;
		int lineNumber = this.dataTable.getRowIndex();
		this.lineNumberRef = lineNumber;
		if(	isEdit == true){			
			ClaimLineVO claimLineVO = (ClaimLineVO)claimLineList.get(lineNumber);
			this.setDiagnosisCode(claimLineVO.getDiagnosisCode());
			this.setHcpcCode(claimLineVO.getHcpcCode());
			this.setModifierCode(claimLineVO.getModifierCode());
			this.setTtCode(claimLineVO.getTtCode());
			this.setRevenueCode(claimLineVO.getRevenueCode());
			this.setPlaceOfService(claimLineVO.getPlaceOfService());
			this.setTypeOfBill(claimLineVO.getTypeOfBill());
			this.setExpectedBenefitComponent(claimLineVO.getExpectedBenefitComponent());
			this.setExpectedBenefit(claimLineVO.getExpectedBenefit());
			this.setExpectedBasicBenefit(claimLineVO.getExpectedBasicBenefit());
			this.setExpectedRiderBenefit(claimLineVO.getExpectedRiderBenefit());
			setBreadCrumbText("Benefit Selection >> Test Case (" + testCaseName +")  >> Edit");
			
		}else{	
			ClaimLineVO claimLineVO = (ClaimLineVO)claimLineList.get(lineNumber);
			this.setDiagnosisCode(claimLineVO.getDiagnosisCode());
			this.setHcpcCode(claimLineVO.getHcpcCode());
			this.setModifierCode(claimLineVO.getModifierCode());
			this.setTtCode(claimLineVO.getTtCode());
			this.setRevenueCode(claimLineVO.getRevenueCode());
			this.setPlaceOfService(claimLineVO.getPlaceOfService());
			this.setTypeOfBill(claimLineVO.getTypeOfBill());	
			this.setExpectedBenefitComponent(claimLineVO.getExpectedBenefitComponent());
			this.setExpectedBenefit(claimLineVO.getExpectedBenefit());
			this.setExpectedBasicBenefit(claimLineVO.getExpectedBasicBenefit());
			this.setExpectedRiderBenefit(claimLineVO.getExpectedRiderBenefit());
			setBreadCrumbText("Benefit Selection >> Test Case >> Create");
			
		}	
		
		return CREATE_TEST_CASE;
	}
	public String deleteClaimLine(){
		int lno = this.dataTable.getRowIndex();
		if(	isEdit == true){
			ClaimLineVO claimLineVO = (ClaimLineVO)claimLineList.remove(lno);
			setBreadCrumbText("Benefit Selection >> Test Case >> Edit");
		}else{
			ClaimLineVO claimLineVO = (ClaimLineVO)claimLineList.remove(lno);
			setBreadCrumbText("Benefit Selection >> Test Case (" + testCaseName +") >> Create");
		}			
			// added code for claim line deletion
			clearClaimLineDetail();
			return CREATE_TEST_CASE;
	}
	public String updateClaimLine(){
		if(	isEdit == true){
			if(validateMandatoryFieldsForClaimLine()){
				ClaimLineVO claimLineVO = (ClaimLineVO)claimLineList.get(this.lineNumberRef);
				claimLineVO.setDiagnosisCode(this.getDiagnosisCode());
				claimLineVO.setHcpcCode(this.getHcpcCode());
				claimLineVO.setModifierCode(this.getModifierCode());
				claimLineVO.setTtCode(this.getTtCode());
				claimLineVO.setRevenueCode(this.getRevenueCode());
				claimLineVO.setPlaceOfService(this.getPlaceOfService());	
				claimLineVO.setTypeOfBill(this.getTypeOfBill());	
				claimLineVO.setExpectedBenefitComponent(this.getExpectedBenefitComponent());
				claimLineVO.setExpectedBenefit(this.getExpectedBenefit());
				claimLineVO.setExpectedBasicBenefit(this.getExpectedBasicBenefit());
				claimLineVO.setExpectedRiderBenefit(this.getExpectedRiderBenefit());
				
				// Setting the description to the BO so that code will not be shown in page
				
				String tempBill="";
				if( (this.getTypeOfBill()!= null) && (this.getTypeOfBill().length() > 0) )
					tempBill = this.getTypeOfBill().split("~")[0];
				
				claimLineVO.setTypeOfBillCode(tempBill);  
					
				String tempBenftComp = "";
				if( (this.getExpectedBenefitComponent()!= null) && (this.getExpectedBenefitComponent().length() > 0) )
						tempBenftComp = this.getExpectedBenefitComponent().split("~")[1];
				claimLineVO.setExpectedBenefitComponentDesc(tempBenftComp);
				
				String tempMajDesc ="";
				if( (this.getExpectedBenefit()!= null) && (this.getExpectedBenefit().length() > 0) )
					tempMajDesc = this.getExpectedBenefit().split("~")[1];
				claimLineVO.setExpectedMajBenefitDesc(tempMajDesc);
				
				String tempBasicDesc ="";
				if( (this.getExpectedBasicBenefit()!= null) && (this.getExpectedBasicBenefit().length() > 0) )
					tempBasicDesc = this.getExpectedBasicBenefit().split("~")[1];
				claimLineVO.setExpectedBasicBenefitDesc(tempBasicDesc);
				
				String tempRiderDesc ="";
				if( (this.getExpectedRiderBenefit()!= null) && (this.getExpectedRiderBenefit().length() > 0) )
					tempRiderDesc = this.getExpectedRiderBenefit().split("~")[1];
				claimLineVO.setExpectedRiderBenefitDesc(tempRiderDesc);
				setBreadCrumbText("Benefit Selection >> Test Case (" + testCaseName +") >> Edit");
				clearClaimLineDetail();
				isClaimLineEdit = false;
			}
			
			
		}else{
			if(validateMandatoryFieldsForClaimLine()){
				ClaimLineVO claimLineVO = (ClaimLineVO)claimLineList.get(this.lineNumberRef);
				claimLineVO.setDiagnosisCode(this.getDiagnosisCode());
				claimLineVO.setHcpcCode(this.getHcpcCode());
				claimLineVO.setModifierCode(this.getModifierCode());
				claimLineVO.setTtCode(this.getTtCode());
				claimLineVO.setRevenueCode(this.getRevenueCode());
				claimLineVO.setPlaceOfService(this.getPlaceOfService());		
				claimLineVO.setExpectedBenefitComponent(this.getExpectedBenefitComponent());
				claimLineVO.setExpectedBenefit(this.getExpectedBenefit());
				claimLineVO.setExpectedBasicBenefit(this.getExpectedBasicBenefit());
				claimLineVO.setExpectedRiderBenefit(this.getExpectedRiderBenefit());
				claimLineVO.setTypeOfBill(this.getTypeOfBill());	
				
				String tempBill="";
				if( (this.getTypeOfBill()!= null) && (this.getTypeOfBill().length() > 0) )
					tempBill = this.getTypeOfBill().split("~")[0];
				
				claimLineVO.setTypeOfBillCode(tempBill);  
					
				String tempBenftComp = "";
				if( (this.getExpectedBenefitComponent()!= null) && (this.getExpectedBenefitComponent().length() > 0) )
						tempBenftComp = this.getExpectedBenefitComponent().split("~")[1];
				claimLineVO.setExpectedBenefitComponentDesc(tempBenftComp);
				
				String tempMajDesc ="";
				if( (this.getExpectedBenefit()!= null) && (this.getExpectedBenefit().length() > 0) )
					tempMajDesc = this.getExpectedBenefit().split("~")[1];
				claimLineVO.setExpectedMajBenefitDesc(tempMajDesc);
				
				String tempBasicDesc ="";
				if( (this.getExpectedBasicBenefit()!= null) && (this.getExpectedBasicBenefit().length() > 0) )
					tempBasicDesc = this.getExpectedBasicBenefit().split("~")[1];
				claimLineVO.setExpectedBasicBenefitDesc(tempBasicDesc);
				
				String tempRiderDesc ="";
				if( (this.getExpectedRiderBenefit()!= null) && (this.getExpectedRiderBenefit().length() > 0) )
					tempRiderDesc = this.getExpectedRiderBenefit().split("~")[1];
				claimLineVO.setExpectedRiderBenefitDesc(tempRiderDesc);
				setBreadCrumbText("Benefit Selection >> Test Case >> Create");
				clearClaimLineDetail();
				isClaimLineEdit = false;
			}
			
		}
		
		
		return CREATE_TEST_CASE;
	}

	private void clearClaimHeaderDetail(){
		this.claimType="";
		this.providerId="";
		this.medAssignIndicator="";
		this.age="";
		this.itsProvSpec="";
		this.hospitalFacilityCode="";
		this.gender="";
		this.groupState="";
		this.edit="";
		this.daysFromInjury="";
		this.memberRelationshipCode="";
	}
	private void clearClaimLineDetail(){
		this.diagnosisCode="";
		this.hcpcCode="";
		this.modifierCode="";
		this.ttCode="";
		this.revenueCode="";
		this.placeOfService="";
		this.typeOfBill="";
		this.expectedBenefitComponent="";
		this.expectedBenefit="";
		this.expectedBasicBenefit="";
		this.expectedRiderBenefit="";
		this.exptdBnftCompEntry=false;
		this.diagnoscodeEntry=false;
	}
	private void clearTestCaseDetail(){
		this.testCaseName="";
		this.testCaseDesc="";
		this.testCaseNameEntry = false;
	}
	
	// Test Case methods
	public String locateTestCase(){	 
	    if( ( (testCaseName== null) || (testCaseName.length()<=0) ) && 
	    	((benefitComponent== null) ||  (benefitComponent.length()<=0))&&
	    	((benefit==null) || (benefit.length()<=0)) ){
	    	addMessageToRequest(new ErrorMessage("all.fields.blank"));
	    	setBreadCrumbText("Benefit Selection >> Test Case >> Locate");
	    	return MAINTAIN_TEST_CASE;
	    }
	    	
	    TestCaseSearchRequest testCaseSearchRequest = (TestCaseSearchRequest)this.getServiceRequest(ServiceManager.TESTCASE_SEARCH_REQUEST);
		TestCaseVO testCaseVO = new TestCaseVO();
		testCaseVO.setTestCaseName(this.getTestCaseName());
		testCaseVO.setBenefitComponent(this.getBenefitComponent());
		testCaseVO.setBenefit(this.getBenefit());
		testCaseSearchRequest.setTestCaseVO(testCaseVO);
		TestCaseSearchResponse testCaseSearchResponse = (TestCaseSearchResponse)this.executeService(testCaseSearchRequest);
		testCaseList = testCaseSearchResponse.getTestCaseResultList();
		if(testCaseList.size()<=0){
			testCaseList = null;
			addMessageToRequest( new InformationalMessage("search.results.zero") );
		}
		setBreadCrumbText("Benefit Selection >> Test Case >> Locate");
	    return MAINTAIN_TEST_CASE;
	}
	//	Benefit component popup methods
	public String locateBenefitComponent(){	 
	    String benefitComponent = this.getExpectedBenefitComponent();
	    if( (benefitComponent== null) ||  (benefitComponent.length()<=0)){
	    	addMessageToRequest(new ErrorMessage("all.fields.blank"));
	    	setBreadCrumbText("Benefit Selection >> Test Case >> Locate");
	    	return CREATE_TEST_CASE;
	    }	    	
	    BenefitComponentSearchRequest benefitComponentSearchRequest = (BenefitComponentSearchRequest)this.getServiceRequest(ServiceManager.TESTCASE_BENEFIT_COMPNT_POPUP_SEARCH_REQUEST);
		ClaimLineVO claimLineVO = new ClaimLineVO();
		claimLineVO.setBenefitCmptName(this.getExpectedBenefitComponent());
		claimLineVO.setExpectedBenefitComponent(this.getExpectedBenefitComponent());
		
		benefitComponentSearchRequest.setClaimLineVO(claimLineVO);
		BenefitComponentSearchResponse benefitComponentSearchResponse = (BenefitComponentSearchResponse)this.executeService(benefitComponentSearchRequest);
		List benefitComponentList = benefitComponentSearchResponse.getBenefitComponentResultList();
		if(benefitComponentList !=null && benefitComponentList.size()>0){
			this.expBenefitCompntList = benefitComponentList;			
		}	else if(benefitComponentList.size()<=0){
			benefitComponentList = null;
			addMessageToRequest( new InformationalMessage("search.results.zero") );
		}
		setBreadCrumbText("Benefit Selection >> Test Case >> Create");
	    return CREATE_TEST_CASE;
	}
	public String deleteTestCase(){
	    
	    TestCaseDeleteRequest testCaseDeleteRequest = (TestCaseDeleteRequest)this.getServiceRequest(ServiceManager.TESTCASE_DELETE_REQUEST);
		TestCaseVO testCaseVO = new TestCaseVO();
		testCaseVO.setTestCaseName(this.getTestCaseNameRef());
		if(this.getTestCaseIdRef() !=null){
			testCaseVO.setTestCaseId(this.getTestCaseIdRef());
		}	
		testCaseDeleteRequest.setTestCaseVO(testCaseVO);
		TestCaseDeleteResponse testCaseDeleteResponse = (TestCaseDeleteResponse) this.executeService(testCaseDeleteRequest);
		locateTestCase();
		setBreadCrumbText("Benefit Selection >> Test Case >> Locate");
		if(!testCaseDeleteResponse.isSuccess()){
			addMessageToRequest( new ErrorMessage("testsuite.ref"));
		}
		return MAINTAIN_TEST_CASE;
	}
	public String testCasePopup(){
	    locateTestCase();
	    return POPUP_TEST_CASE;
	}
	public String benefitComponentPopup(){
	    locateBenefitComponent();
	    return BENEFIT_CMPNT_POPUP_TEST_CASE;
	}

    /**
     * @return Returns the testCaseIdRef.
     */
    public String getTestCaseIdRef() {
        return testCaseIdRef;
    }
    /**
     * @param testCaseIdRef The testCaseIdRef to set.
     */
    public void setTestCaseIdRef(String testCaseIdRef) {
        this.testCaseIdRef = testCaseIdRef;
    }
    /**
     * @return Returns the testCaseList.
     */
    public List getTestCaseList() {
        return testCaseList;
    }
    /**
     * @param testCaseList The testCaseList to set.
     */
    public void setTestCaseList(List testCaseList) {
        this.testCaseList = testCaseList;
    }
    /**
     * @return Returns the testCaseName.
     */
    public String getTestCaseName() {
        return testCaseName;
    }
    /**
     * @param testCaseName The testCaseName to set.
     */
    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }
    /**
     * @return Returns the testCaseNameRef.
     */
    public String getTestCaseNameRef() {
        return testCaseNameRef;
    }
    /**
     * @param testCaseNameRef The testCaseNameRef to set.
     */
    public void setTestCaseNameRef(String testCaseNameRef) {
        this.testCaseNameRef = testCaseNameRef;
    }
	
   
	/**
	 * @return Returns the age.
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age The age to set.
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return Returns the claimType.
	 */
	public String getClaimType() {
		return claimType;
	}
	/**
	 * @param claimType The claimType to set.
	 */
	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}
	/**
	 * @return Returns the daysFromInjury.
	 */
	public String getDaysFromInjury() {
		return daysFromInjury;
	}
	/**
	 * @param daysFromInjury The daysFromInjury to set.
	 */
	public void setDaysFromInjury(String daysFromInjury) {
		this.daysFromInjury = daysFromInjury;
	}
	/**
	 * @return Returns the diagnosisCode.
	 */
	public String getDiagnosisCode() {
		return diagnosisCode;
	}
	/**
	 * @param diagnosisCode The diagnosisCode to set.
	 */
	public void setDiagnosisCode(String diagnosisCode) {
		this.diagnosisCode = diagnosisCode;
	}
	/**
	 * @return Returns the edit.
	 */
	public String getEdit() {
		return edit;
	}
	/**
	 * @param edit The edit to set.
	 */
	public void setEdit(String edit) {
		this.edit = edit;
	}

	/**
	 * @return Returns the expBenefitCompntList.
	 */
	public List getExpBenefitCompntList() {
		if(expBenefitCompntList == null){
			BenefitComponentPopupRequest benefitComponentPopupRequest = (BenefitComponentPopupRequest)this.getServiceRequest(ServiceManager.BENEFITCOMPONENT_POPUP_REQUEST);
	   		BenefitComponentPopupResponse benefitComponentPopupResponse = (BenefitComponentPopupResponse)this.executeService(benefitComponentPopupRequest);
	   		expBenefitCompntList = benefitComponentPopupResponse.getExpectedBenefitComponentList();
	   }
		
		return expBenefitCompntList;
	}
	/**
	 * @param expBenefitCompntList The expBenefitCompntList to set.
	 */
	public void setExpBenefitCompntList(List expBenefitCompntList) {
		this.expBenefitCompntList = expBenefitCompntList;
	}
	
	/**
	 * @return Returns the expectedBasicBenefit.
	 */
	public String getExpectedBasicBenefit() {
		return expectedBasicBenefit;
	}
	/**
	 * @param expectedBasicBenefit The expectedBasicBenefit to set.
	 */
	public void setExpectedBasicBenefit(String expectedBasicBenefit) {
		this.expectedBasicBenefit = expectedBasicBenefit;
	}
	/**
	 * @return Returns the expectedBenefit.
	 */
	public String getExpectedBenefit() {
		return expectedBenefit;
	}
	/**
	 * @param expectedBenefit The expectedBenefit to set.
	 */
	public void setExpectedBenefit(String expectedBenefit) {
		this.expectedBenefit = expectedBenefit;
	}
	/**
	 * @return Returns the expectedBenefitComponent.
	 */
	public String getExpectedBenefitComponent() {
		return expectedBenefitComponent;
	}
	/**
	 * @param expectedBenefitComponent The expectedBenefitComponent to set.
	 */
	public void setExpectedBenefitComponent(String expectedBenefitComponent) {
		this.expectedBenefitComponent = expectedBenefitComponent;
	}
	/**
	 * @return Returns the expectedRiderBenefit.
	 */
	public String getExpectedRiderBenefit() {
		return expectedRiderBenefit;
	}
	/**
	 * @param expectedRiderBenefit The expectedRiderBenefit to set.
	 */
	public void setExpectedRiderBenefit(String expectedRiderBenefit) {
		this.expectedRiderBenefit = expectedRiderBenefit;
	}
	
	/**
	 * @return Returns the expectedBenefitList.expectedBenefitList
	 */
	public List getExpectedBenefitList() {

		String benefitComponent = getRequest().getParameter("benefitCompId");
		String ben[] = benefitComponent.split("~");
		String benefitCompId = null;
		String benefitCompName = null;
		if(ben !=null){
			benefitCompId 	= ben[0];
			benefitCompName = ben[1];
		}		
		String benefitType = getRequest().getParameter("benefitType");
		BenefitPopupRequest benefitPopupRequest = (BenefitPopupRequest)this.getServiceRequest(ServiceManager.BENEFIT_POPUP_REQUEST);		
		benefitPopupRequest.setBenefitComponentId(benefitCompId);		
		benefitPopupRequest.setBenefitType(benefitType);		
		BenefitPopupResponse benefitPopupResponse = (BenefitPopupResponse)this.executeService(benefitPopupRequest);
		List benefitList = benefitPopupResponse.getExpectedBenefitList();
		
		return benefitList;
	}
	/**
	 * @param expectedBenefitList The expectedBenefitList to set.
	 */
	public void setExpectedBenefitList(List expectedBenefitList) {
		this.expectedBenefitList = expectedBenefitList;
	}
	/**
	 * @return Returns the gender.
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender The gender to set.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return Returns the groupState.
	 */
	public String getGroupState() {
		return groupState;
	}
	/**
	 * @param groupState The groupState to set.
	 */
	public void setGroupState(String groupState) {
		this.groupState = groupState;
	}
	/**
	 * @return Returns the hcpcCode.
	 */
	public String getHcpcCode() {
		return hcpcCode;
	}
	/**
	 * @param hcpcCode The hcpcCode to set.
	 */
	public void setHcpcCode(String hcpcCode) {
		this.hcpcCode = hcpcCode;
	}
	/**
	 * @return Returns the hospitalFacilityCode.
	 */
	public String getHospitalFacilityCode() {
		return hospitalFacilityCode;
	}
	/**
	 * @param hospitalFacilityCode The hospitalFacilityCode to set.
	 */
	public void setHospitalFacilityCode(String hospitalFacilityCode) {
		this.hospitalFacilityCode = hospitalFacilityCode;
	}
	/**
	 * @return Returns the itsProvSpec.
	 */
	public String getItsProvSpec() {
		return itsProvSpec;
	}
	/**
	 * @param itsProvSpec The itsProvSpec to set.
	 */
	public void setItsProvSpec(String itsProvSpec) {
		this.itsProvSpec = itsProvSpec;
	}
	/**
	 * @return Returns the medAssignIndicator.
	 */
	public String getMedAssignIndicator() {
		return medAssignIndicator;
	}
	/**
	 * @param medAssignIndicator The medAssignIndicator to set.
	 */
	public void setMedAssignIndicator(String medAssignIndicator) {
		this.medAssignIndicator = medAssignIndicator;
	}
	/**
	 * @return Returns the memberRelationshipCode.
	 */
	public String getMemberRelationshipCode() {
		return memberRelationshipCode;
	}
	/**
	 * @param memberRelationshipCode The memberRelationshipCode to set.
	 */
	public void setMemberRelationshipCode(String memberRelationshipCode) {
		this.memberRelationshipCode = memberRelationshipCode;
	}
	/**
	 * @return Returns the modifierCode.
	 */
	public String getModifierCode() {
		return modifierCode;
	}
	/**
	 * @param modifierCode The modifierCode to set.
	 */
	public void setModifierCode(String modifierCode) {
		this.modifierCode = modifierCode;
	}
	/**
	 * @return Returns the providerId.
	 */
	public String getProviderId() {
		return providerId;
	}
	/**
	 * @param providerId The providerId to set.
	 */
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	/**
	 * @return Returns the revenueCode.
	 */
	public String getRevenueCode() {
		return revenueCode;
	}
	/**
	 * @param revenueCode The revenueCode to set.
	 */
	public void setRevenueCode(String revenueCode) {
		this.revenueCode = revenueCode;
	}
	/**
	 * @return Returns the testCaseDesc.
	 */
	public String getTestCaseDesc() {
		return testCaseDesc;
	}
	/**
	 * @param testCaseDesc The testCaseDesc to set.
	 */
	public void setTestCaseDesc(String testCaseDesc) {
		this.testCaseDesc = testCaseDesc;
	}
	/**
	 * @return Returns the testCaseId.
	 */
	public int getTestCaseId() {
		return testCaseId;
	}
	/**
	 * @param testCaseId The testCaseId to set.
	 */
	public void setTestCaseId(int testCaseId) {
		this.testCaseId = testCaseId;
	}
	/**
	 * @return Returns the ttCode.
	 */
	public String getTtCode() {
		return ttCode;
	}
	/**
	 * @param ttCode The ttCode to set.
	 */
	public void setTtCode(String ttCode) {
		this.ttCode = ttCode;
	}
	/**
	 * @return Returns the typeOfBill.
	 */
	public String getTypeOfBill() {
		return typeOfBill;
	}
	/**
	 * @param typeOfBill The typeOfBill to set.
	 */
	public void setTypeOfBill(String typeOfBill) {
		this.typeOfBill = typeOfBill;
	}
	
	/**
	 * @return Returns the claimTypeList.
	 */
	public List getClaimTypeList() {
		return claimTypeList;
	}
	/**
	 * @param claimTypeList The claimTypeList to set.
	 */
	public void setClaimTypeList(List claimTypeList) {
		this.claimTypeList = claimTypeList;
	}
	
	/**
	 * @return Returns the genderList.
	 */
	public List getGenderList() {
		return genderList;
	}
	/**
	 * @param genderList The genderList to set.
	 */
	public void setGenderList(List genderList) {
		this.genderList = genderList;
	}
	/**
	 * @return Returns the groupStateList.
	 */
	public List getGroupStateList() {
		return groupStateList;
	}
	/**
	 * @param groupStateList The groupStateList to set.
	 */
	public void setGroupStateList(List groupStateList) {
		this.groupStateList = groupStateList;
	}
	/**
	 * @return Returns the hospitalFacilityCodeList.
	 */
	public List getHospitalFacilityCodeList() {
		return hospitalFacilityCodeList;
	}
	/**
	 * @param hospitalFacilityCodeList The hospitalFacilityCodeList to set.
	 */
	public void setHospitalFacilityCodeList(List hospitalFacilityCodeList) {
		this.hospitalFacilityCodeList = hospitalFacilityCodeList;
	}
	/**
	 * @return Returns the medAssignIndicatorList.
	 */
	public List getMedAssignIndicatorList() {
		return medAssignIndicatorList;
	}
	/**
	 * @param medAssignIndicatorList The medAssignIndicatorList to set.
	 */
	public void setMedAssignIndicatorList(List medAssignIndicatorList) {
		this.medAssignIndicatorList = medAssignIndicatorList;
	}
	/**
	 * @return Returns the memberRelationshipCodeList.
	 */
	public List getMemberRelationshipCodeList() {
		return memberRelationshipCodeList;
	}
	/**
	 * @param memberRelationshipCodeList The memberRelationshipCodeList to set.
	 */
	public void setMemberRelationshipCodeList(List memberRelationshipCodeList) {
		this.memberRelationshipCodeList = memberRelationshipCodeList;
	}
	/**
	 * @return Returns the placeOfServiceList.
	 */
	public List getPlaceOfServiceList() {
		return placeOfServiceList;
	}
	/**
	 * @param placeOfServiceList The placeOfServiceList to set.
	 */
	public void setPlaceOfServiceList(List placeOfServiceList) {
		this.placeOfServiceList = placeOfServiceList;
	}
	/**
	 * @return Returns the placeOfService.
	 */
	public String getPlaceOfService() {
		return placeOfService;
	}
	/**
	 * @param placeOfService The placeOfService to set.
	 */
	public void setPlaceOfService(String placeOfService) {
		this.placeOfService = placeOfService;
	}
	/**
	 * @return Returns the typeOfBillList.
	 */
	public List getTypeOfBillList() {
		return typeOfBillList;
	}
	/**
	 * @param typeOfBillList The typeOfBillList to set.
	 */
	public void setTypeOfBillList(List typeOfBillList) {
		this.typeOfBillList = typeOfBillList;
	}
	/**
	 * @return Returns the claimLineList.
	 */
	public List getClaimLineList() {
		return claimLineList;
	}
	/**
	 * @param claimLineList The claimLineList to set.
	 */
	public void setClaimLineList(List claimLineList) {
		this.claimLineList = claimLineList;
	}
	/**
	 * @return Returns the dataTable.
	 */
	public HtmlDataTable getDataTable() {
		return dataTable;
	}
	/**
	 * @param dataTable The dataTable to set.
	 */
	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}
	/**
	 * @return Returns the lineNumberRef.
	 */
	public int getLineNumberRef() {
		return lineNumberRef;
	}
	/**
	 * @param lineNumberRef The lineNumberRef to set.
	 */
	public void setLineNumberRef(int lineNumberRef) {
		this.lineNumberRef = lineNumberRef;
	}
	/**
	 * @return Returns the claimLineId.
	 */
	public String getClaimLineId() {
		return claimLineId;
	}
	/**
	 * @param claimLineId The claimLineId to set.
	 */
	public void setClaimLineId(String claimLineId) {
		this.claimLineId = claimLineId;
	}
	/**
	 * @return Returns the isClaimLineEdit.
	 */
	public boolean getIsClaimLineEdit() {
		return isClaimLineEdit;
	}
	/**
	 * @param isClaimLineEdit The isClaimLineEdit to set.
	 */
	public void setIsClaimLineEdit(boolean isClaimLineEdit) {
		this.isClaimLineEdit = isClaimLineEdit;
	}
	/**
	 * @return Returns the isClaimLineEdit.
	 */
	public boolean getIsCreate() {
		return isCreate;
	}
	/**
	 * @param isClaimLineEdit The isClaimLineEdit to set.
	 */
	public void setIsCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}
	/**
	 * @return Returns the testCaseNameEntry.
	 */
	public boolean isTestCaseNameEntry() {
		return testCaseNameEntry;
	}
	/**
	 * @param testCaseNameEntry The testCaseNameEntry to set.
	 */
	public void setTestCaseNameEntry(boolean testCaseNameEntry) {
		this.testCaseNameEntry = testCaseNameEntry;
	}
	/**
	 * @return Returns the exptdBnftCompEntry.
	 */
	public boolean isExptdBnftCompEntry() {
		return exptdBnftCompEntry;
	}
	/**
	 * @param exptdBnftCompEntry The exptdBnftCompEntry to set.
	 */
	public void setExptdBnftCompEntry(boolean exptdBnftCompEntry) {
		this.exptdBnftCompEntry = exptdBnftCompEntry;
	}

	/**
	 * @return Returns the benefit.
	 */
	public String getBenefit() {
		return benefit;
	}
	/**
	 * @param benefit The benefit to set.
	 */
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	/**
	 * @return Returns the benefitComponent.
	 */
	public String getBenefitComponent() {
		return benefitComponent;
	}
	/**
	 * @param benefitComponent The benefitComponent to set.
	 */
	public void setBenefitComponent(String benefitComponent) {
		this.benefitComponent = benefitComponent;
	}
	private void clearTestCaseEntryFileds(){
		requiredField = false;
		testCaseNameEntry = false;
		testCaseDescEntry = false;
		exptdBnftCompEntry = false;
	}

	/**
	 * @return Returns the itsClaim.
	 */
	public boolean isItsClaim() {
		return itsClaim;
	}
	/**
	 * @param itsClaim The itsClaim to set.
	 */
	public void setItsClaim(boolean itsClaim) {
		this.itsClaim = itsClaim;
	}
	/**
	 * @return Returns the diagnoscodeEntry.
	 */
	public boolean getDiagnoscodeEntry() {
		return diagnoscodeEntry;
	}
	/**
	 * @param diagnoscodeEntry The diagnoscodeEntry to set.
	 */
	public void setDiagnoscodeEntry(boolean diagnoscodeEntry) {
		this.diagnoscodeEntry = diagnoscodeEntry;
	}
}
