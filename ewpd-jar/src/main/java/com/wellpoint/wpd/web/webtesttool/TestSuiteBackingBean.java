/*
 * ItemBackingBean.java 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.webtesttool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlDataTable;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
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
import com.wellpoint.wpd.common.webtesttool.vo.TestSuiteVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

public class TestSuiteBackingBean  extends WPDBackingBean{

    public static String SELECT_TEST_CASE = "selectTestCase";
    public static String CREATE_TEST_SUITE = "createTestSuite";
    public static String EDIT_TEST_SUITE = "editTestSuite";
    public static String MAINTAIN_TEST_SUITE = "maintainTestSuite";
    public static String SELECTED_TESTCASE_LIST = "selectedTestCaseList";
    
    //  create screen variables
    private String testSuiteId;
	private String testSuiteName;
	private String testSuiteDesc;
	private String contractId;
	private String startDate;
	private String endDate;
	
    //  mandatory screen variables
	private boolean testSuiteNameEntry;
	private boolean testSuiteDescEntry;
	private boolean contractIdEntry;
	private boolean startDateEntry;
	private boolean endDateEntry;
	private boolean selectedTestCaseValEntry;

	//	 maintain screen variables
	private List testSuiteList;
    private String testSuiteIdRef;
	private String testSuiteNameRef;
	private String testCaseIdRef;
	
	//	test case selection variables
	private String selectedTestCaseVal;
	
	private List testSuiteResults;
	private boolean executeTestSute;
	
	// behavior variables
	private boolean isCopy;
	
	private HtmlDataTable dataTable;
	
	private boolean validate(){
		boolean isValid = true;
		if( (testSuiteName == null) || ("".equals(testSuiteName.trim())) ){
			testSuiteNameEntry = true;
			isValid = false;
		}
		if( (contractId == null) || ("".equals(contractId.trim())) ){
			contractIdEntry = true;
			isValid = false;
		}
		if( (startDate == null) || ("".equals(startDate.trim())) ){
			startDateEntry = true;
			isValid = false;
		}
		if((endDate == null) || ("".equals(endDate.trim()))){
			endDateEntry = true;
			isValid = false;
		}
		if(!isValid)
			addMessageToRequest(new ErrorMessage("contract.rule.mandatory.valid"));
		
		if( (testSuiteDesc != null) && (testSuiteDesc.length()>299)){
			testSuiteDescEntry = true;
			isValid = false;
			addMessageToRequest(new ErrorMessage("wtt.description.invalid.length"));
		}
		
		if( !WPDStringUtil.isValidDate(startDate) ){
			ErrorMessage errorMessage = new ErrorMessage(WebConstants.INPUT_FORMAT_INVALID);
			errorMessage.setParameters(new String[] { "Start Date format" });
			addMessageToRequest(errorMessage);				
			startDateEntry = true; 
			isValid = false;
		}
		if( !WPDStringUtil.isValidDate(endDate) ){
			ErrorMessage errorMessage = new ErrorMessage(WebConstants.INPUT_FORMAT_INVALID);
			errorMessage.setParameters(new String[] { "End Date format" });
			addMessageToRequest(errorMessage);
			endDateEntry = true;
			isValid = false;
		}

		return isValid;
	}
	
	public String createTestSuite() {
		String returnStr = "";
		TestSuiteCreateRequest testSuiteCreateRequest = (TestSuiteCreateRequest)this.getServiceRequest(ServiceManager.TESTSUITE_CREATE_REQUEST);
		if(!validate()){
			if(isCopy){
				returnStr =  EDIT_TEST_SUITE;
				setBreadCrumbText("Benefit Selection >> Test Suite >> Copy");
			}else{
				setBreadCrumbText("Benefit Selection >> Test Suite >> Create");
				returnStr =  CREATE_TEST_SUITE;
			}
			return returnStr;
		}
		//create Value Object for Test Suite Request
		TestSuiteVO testSuiteVO = new TestSuiteVO();
		testSuiteVO.setTestSuiteName(testSuiteName);
		testSuiteVO.setTestSuiteDesc(testSuiteDesc);
		testSuiteVO.setContractId(contractId);
		testSuiteVO.setStartDate( WPDStringUtil.getDateFromString(startDate) );
		testSuiteVO.setEndDate( WPDStringUtil.getDateFromString(endDate) );
		if(isCopy){
		    testSuiteVO.setSelectedTestCaseList(getSelectedTestCaseList());
		}
		testSuiteCreateRequest.setTestSuiteVO(testSuiteVO);
		//call service layer and process the request
		TestSuiteCreateResponse testSuiteCreateResponse = (TestSuiteCreateResponse)this.executeService(testSuiteCreateRequest);
		if(testSuiteCreateResponse.isSuccess()){
			TestSuiteBO testSuiteBO = testSuiteCreateResponse.getTestSuiteBO();
			this.setTestSuiteId(testSuiteBO.getTestSuiteId());
			this.setSelectedTestCaseList(testSuiteBO.getTestCaseList());
			InformationalMessage successMsg = new InformationalMessage(BusinessConstants.MSG_TESTSUITE_SAVE_SUCCESS);
			addMessageToRequest(successMsg );
			setBreadCrumbText("Benefit Selection >> Test Suite (" + testSuiteName+ ") >> Edit");
			returnStr = SELECT_TEST_CASE;
			isCopy = false;
		}else{
			addMessageToRequest(testSuiteCreateResponse.getMessage());
			if(isCopy){
				returnStr =  EDIT_TEST_SUITE;
				setBreadCrumbText("Benefit Selection >> Test Suite >> Copy");
			}else{
				setBreadCrumbText("Benefit Selection >> Test Suite >> Create");
				returnStr =  CREATE_TEST_SUITE;
			}
		}
		return returnStr;
	}
	public String copyTestSuite(){
	    TestSuiteSearchRequest testSuiteSearchRequest = (TestSuiteSearchRequest)this.getServiceRequest(ServiceManager.TESTSUITE_SEARCH_REQUEST);
	    // create Value Object for Test Suite Request
		TestSuiteVO testSuiteVO = new TestSuiteVO();
		testSuiteVO.setTestSuiteId(this.getTestSuiteIdRef());
		testSuiteVO.setTestSuiteName(this.getTestSuiteNameRef());
		testSuiteSearchRequest.setTestSuiteVO(testSuiteVO);
		//call service layer and process the request
		TestSuiteSearchResponse testSuiteSearchResponse = (TestSuiteSearchResponse)this.executeService(testSuiteSearchRequest);
		TestSuiteBO testSuiteBO = (TestSuiteBO) testSuiteSearchResponse.getTestSuiteResultList().get(0);
		reset();
		isCopy = true;
		copyBOToBean(testSuiteBO);
		//clear test suite id
		if(getSelectedTestCaseList() != null)
		for(Iterator i = getSelectedTestCaseList().iterator(); i.hasNext();){
		    ((TestCaseRefBO)i.next()).setTestSuiteId("");
		}
		setBreadCrumbText("Benefit Selection >> Test Suite >> Copy");
	    return EDIT_TEST_SUITE;
	}
	public String editTestSuite(){	    
	    TestSuiteSearchRequest testSuiteSearchRequest = (TestSuiteSearchRequest)this.getServiceRequest(ServiceManager.TESTSUITE_SEARCH_REQUEST);
	    // create Value Object for Test Suite Request
		TestSuiteVO testSuiteVO = new TestSuiteVO();
		testSuiteVO.setTestSuiteId(this.getTestSuiteIdRef());
		testSuiteVO.setTestSuiteName(this.getTestSuiteNameRef());
		testSuiteSearchRequest.setTestSuiteVO(testSuiteVO);
		//call service layer and process the request
		TestSuiteSearchResponse testSuiteSearchResponse = (TestSuiteSearchResponse)this.executeService(testSuiteSearchRequest);
		// reset the instance variables to response values
		TestSuiteBO testSuiteBO = (TestSuiteBO) testSuiteSearchResponse.getTestSuiteResultList().get(0);
		reset();
		this.setTestSuiteId(testSuiteBO.getTestSuiteId());
		this.setTestSuiteName(testSuiteBO.getTestSuiteName());
		setSelectedTestCaseList(testSuiteBO.getTestCaseList());
		copyBOToBean(testSuiteBO);
		setBreadCrumbText("Benefit Selection >> Test Suite (" + testSuiteName+ ") >> Edit");
	    return EDIT_TEST_SUITE;
	}
	public String updateTestSuite(){
		if(!validate())
			return EDIT_TEST_SUITE;
	    TestSuiteChangeRequest testSuiteChangeRequest = (TestSuiteChangeRequest)this.getServiceRequest(ServiceManager.TESTSUITE_CHANGE_REQUEST);
	    TestSuiteVO testSuiteVO = new TestSuiteVO();
	    testSuiteVO.setTestSuiteId(testSuiteId);
		testSuiteVO.setTestSuiteName(testSuiteName);
		testSuiteVO.setTestSuiteDesc(testSuiteDesc);
		testSuiteVO.setContractId(contractId);
		testSuiteVO.setStartDate( WPDStringUtil.getDateFromString(startDate) );
		testSuiteVO.setEndDate( WPDStringUtil.getDateFromString(endDate) );
		testSuiteChangeRequest.setTestSuiteVO(testSuiteVO);
		// call service layer and process the request
		TestSuiteChangeResponse testSuiteChangeResponse = (TestSuiteChangeResponse)this.executeService(testSuiteChangeRequest);
		if(testSuiteChangeResponse.isSuccess()){
			InformationalMessage successMsg = new InformationalMessage(BusinessConstants.MSG_TESTSUITE_UPDATE_SUCCESS);
			addMessageToRequest(successMsg );
		}
		else
			addMessageToRequest(testSuiteChangeResponse.getMessage());
		setBreadCrumbText("Benefit Selection >> Test Suite (" + testSuiteName+ ") >> Edit");
	    return EDIT_TEST_SUITE;
	}
	
	public String deleteTestSuite(){
	    TestSuiteDeleteRequest testSuiteDeleteRequest = (TestSuiteDeleteRequest)this.getServiceRequest(ServiceManager.TESTSUITE_DELETE_REQUEST);
	    //  create Value Object for Test Suite Request
	    TestSuiteVO testSuiteVO = new TestSuiteVO();
		testSuiteVO.setTestSuiteId(testSuiteIdRef);
		testSuiteVO.setTestSuiteName(this.getTestSuiteNameRef());
		testSuiteDeleteRequest.setTestSuiteVO(testSuiteVO);
		//	call service layer and process the request
	    TestSuiteDeleteResponse testSuiteDeleteResponse = (TestSuiteDeleteResponse)this.executeService(testSuiteDeleteRequest);
	    locateTestSuite();
	    return MAINTAIN_TEST_SUITE;
	}
	
	public String locateTestSuite(){	    
		if( (testSuiteName == null) || ("".equals(testSuiteName.trim())) ){
			addMessageToRequest(new ErrorMessage("all.fields.blank"));
			testSuiteNameEntry = true;
			setBreadCrumbText("Benefit Selection >> Test Suite >> Locate");
			return MAINTAIN_TEST_SUITE;
		}
	    TestSuiteSearchRequest testSuiteSearchRequest = (TestSuiteSearchRequest)this.getServiceRequest(ServiceManager.TESTSUITE_SEARCH_REQUEST);
		TestSuiteVO testSuiteVO = new TestSuiteVO();
		testSuiteVO.setTestSuiteName(this.getTestSuiteName());
		testSuiteSearchRequest.setTestSuiteVO(testSuiteVO);
		testSuiteSearchRequest.setLocate(true);
		TestSuiteSearchResponse testSuiteSearchResponse = (TestSuiteSearchResponse)this.executeService(testSuiteSearchRequest);
		testSuiteList = testSuiteSearchResponse.getTestSuiteResultList();
		if(testSuiteList.size()<=0){
			testSuiteList = null;
			addMessageToRequest( new InformationalMessage("search.results.zero") );
		}
		setBreadCrumbText("Benefit Selection >> Test Suite >> Locate");
	    return MAINTAIN_TEST_SUITE;
	}
	
	public void executeTestSuite(){
	    String testCaseIdStr = getRequest().getParameter("testCaseIds");
	    List testCaseIdList = null;
	    if( (testCaseIdStr != null ) && (testCaseIdStr.length()>0))
	        testCaseIdList = Arrays.asList(testCaseIdStr.split("~"));
	    
	    TestSuiteExecuteRequest testSuiteExecuteRequest = (TestSuiteExecuteRequest)this.getServiceRequest(ServiceManager.TESTSUITE_EXECUTE_REQUEST);
	    testSuiteExecuteRequest.setTestSuiteId(getRequest().getParameter("testSuiteId"));
	    testSuiteExecuteRequest.setTestCaseIdList(testCaseIdList);
	    
	    TestSuiteExecuteResponse testSuiteExecuteResponse = (TestSuiteExecuteResponse) this.executeService(testSuiteExecuteRequest);
	    TestSuiteBO testSuiteBO= testSuiteExecuteResponse.getTestSuiteBO();
	    this.setTestSuiteId(testSuiteBO.getTestSuiteId());
	    this.setTestSuiteName(testSuiteBO.getTestSuiteName());
	    this.setTestSuiteDesc(testSuiteBO.getTestSuiteDesc());
	    this.setContractId(testSuiteBO.getContractId());
	    if(testSuiteBO.getStartDate() != null) 
	        this.setStartDate( WPDStringUtil.getStringDate(testSuiteBO.getStartDate()) );
	    if(testSuiteBO.getEndDate() != null)
	        this.setEndDate( WPDStringUtil.getStringDate(testSuiteBO.getEndDate()) );
	    if(testSuiteExecuteResponse.isStatus()){
		    this.testSuiteResults = testSuiteExecuteResponse.getTestResults();
	    }else{
	    	addMessageToRequest(testSuiteExecuteResponse.getMessage());
	    }
	    setBreadCrumbText("Benefit Selection >> Test Suite (" + testSuiteName +")>> Result");
	}
	
	public String addTestCase(){
	    TestCaseRefCreateRequest testCaseRefCreateRequest = (TestCaseRefCreateRequest)this.getServiceRequest(ServiceManager.TESTCASE_REF_CREATE_REQUEST);
	    
	    //validate input
		if( (selectedTestCaseVal == null) || ("".equals(selectedTestCaseVal.trim())) ){
			selectedTestCaseValEntry = true;
			addMessageToRequest(new ErrorMessage("please.enter.all.mandatory.fields"));
			setBreadCrumbText("Benefit Selection >> Test Suite (" + testSuiteName+ ") >> Edit");
			return SELECT_TEST_CASE;
		}
	    
	    String[] selectedTestCaseVal = getSelectedTestCaseVal().split("~");
	    List testCaseRefVOList = new ArrayList();
	    TestCaseRefVO testCaseRefVO;
	    for(int i=0; i<selectedTestCaseVal.length; i+=2){
	        testCaseRefVO = new TestCaseRefVO();
	        testCaseRefVO.setTestSuiteId(getTestSuiteId());
	        testCaseRefVO.setTestCaseId(selectedTestCaseVal[i]);
	        testCaseRefVO.setTestCaseName(selectedTestCaseVal[i+1]);
	        testCaseRefVOList.add(testCaseRefVO);
	    }
	    testCaseRefCreateRequest.setTestCaseRefVOList(testCaseRefVOList);
	    TestCaseRefCreateResponse testCaseRefCreateResponse= (TestCaseRefCreateResponse) this.executeService(testCaseRefCreateRequest);
	    List selectedTestCaseList = testCaseRefCreateResponse.getTestCaseRefList();
	    getSession().setAttribute(SELECTED_TESTCASE_LIST, selectedTestCaseList);
	    setSelectedTestCaseVal("");
	    setBreadCrumbText("Benefit Selection >> Test Suite (" + testSuiteName+ ") >> Edit");
	    return SELECT_TEST_CASE;
	}
	
	public String testCaseRefDelete(){	        
	    TestCaseRefDeleteRequest testCaseRefDeleteRequest = (TestCaseRefDeleteRequest) this.getServiceRequest(ServiceManager.TESTCASE_REF_DELETE_REQUEST);
	    TestCaseRefVO testCaseRefVO = new TestCaseRefVO();
	    testCaseRefVO.setTestCaseId(getTestCaseIdRef());
	    testCaseRefVO.setTestSuiteId(getTestSuiteId());
	    testCaseRefDeleteRequest.setTestCaseRefVO(testCaseRefVO);
	    TestCaseRefDeleteResponse testCaseRefDeleteResponse = (TestCaseRefDeleteResponse)this.executeService(testCaseRefDeleteRequest);
	    List selectedTestCaseList = testCaseRefDeleteResponse.getTestCaseRefList();
	    getSession().setAttribute(SELECTED_TESTCASE_LIST, selectedTestCaseList);
	    setBreadCrumbText("Benefit Selection >> Test Suite (" + testSuiteName+ ") >> Edit");
	    return SELECT_TEST_CASE;
	}
	private void copyBOToBean(TestSuiteBO testSuiteBO){
		this.setTestSuiteDesc(testSuiteBO.getTestSuiteDesc());
		this.setContractId(testSuiteBO.getContractId());
		this.setStartDate(WPDStringUtil.getStringDate(testSuiteBO.getStartDate()));
		this.setEndDate(WPDStringUtil.getStringDate(testSuiteBO.getEndDate()));
		this.setSelectedTestCaseList(testSuiteBO.getTestCaseList());
	}
	private void reset(){
	    // create screen variables
	    this.testSuiteId="";
		this.testSuiteName="";
		this.testSuiteDesc="";
		this.contractId="";
		this.startDate="";
		this.endDate="";
		// maintain screen variables
		this.testSuiteList=null;
	    this.testSuiteIdRef="";
		this.testSuiteNameRef="";
		//test case selection variables
		this.selectedTestCaseVal="";
		// behavior variables
		isCopy=false;
	}
	/*** Navigation operations ***/
	public String goToEditTestSuite(){
		setBreadCrumbText("Benefit Selection >> Test Suite (" + testSuiteName+ ") >> Edit");
	    return EDIT_TEST_SUITE;
	}
	public String goToSelectTestCase(){
		setBreadCrumbText("Benefit Selection >> Test Suite (" + testSuiteName+ ") >> Edit");
	    return SELECT_TEST_CASE;
	}
    /**
     * @return Returns the contractId.
     */
    public String getContractId() {
        return contractId;
    }
    /**
     * @param contractId The contractId to set.
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    /**
     * @return Returns the endDate.
     */
    public String getEndDate() {
        return endDate;
    }
    /**
     * @param endDate The endDate to set.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    /**
     * @return Returns the startDate.
     */
    public String getStartDate() {
        return startDate;
    }
    /**
     * @param startDate The startDate to set.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    /**
     * @return Returns the testSuiteDesc.
     */
    public String getTestSuiteDesc() {
        return testSuiteDesc;
    }
    /**
     * @param testSuiteDesc The testSuiteDesc to set.
     */
    public void setTestSuiteDesc(String testSuiteDesc) {
        this.testSuiteDesc = testSuiteDesc;
    }
    /**
     * @return Returns the testSuiteName.
     */
    public String getTestSuiteName() {
        return testSuiteName;
    }
    /**
     * @param testSuiteName The testSuiteName to set.
     */
    public void setTestSuiteName(String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }
    /**
     * @return Returns the testSuiteList.
     */
    public List getTestSuiteList() {
        return testSuiteList;
    }
    /**
     * @param testSuiteList The testSuiteList to set.
     */
    public void setTestSuiteList(List testSuiteList) {
        this.testSuiteList = testSuiteList;
    }
    /**
     * @return Returns the testSuiteId.
     */
    public String getTestSuiteId() {
        return testSuiteId;
    }
    /**
     * @param testSuiteId The testSuiteId to set.
     */
    public void setTestSuiteId(String testSuiteId) {
        this.testSuiteId = testSuiteId;
    }
    /**
     * @return Returns the testSuiteIdRef.
     */
    public String getTestSuiteIdRef() {
        return testSuiteIdRef;
    }
    /**
     * @param testSuiteIdRef The testSuiteIdRef to set.
     */
    public void setTestSuiteIdRef(String testSuiteIdRef) {
        this.testSuiteIdRef = testSuiteIdRef;
    }
    /**
     * @return Returns the testSuiteNameRef.
     */
    public String getTestSuiteNameRef() {
        return testSuiteNameRef;
    }
    /**
     * @param testSuiteNameRef The testSuiteNameRef to set.
     */
    public void setTestSuiteNameRef(String testSuiteNameRef) {
        this.testSuiteNameRef = testSuiteNameRef;
    }
    /**
     * @return Returns the selectedTestCaseList.
     */
    public List getSelectedTestCaseList() {
        return (List)getSession().getAttribute(SELECTED_TESTCASE_LIST);
    }
    /**
     * @param selectedTestCaseList The selectedTestCaseList to set.
     */
    public void setSelectedTestCaseList(List selectedTestCaseList) {
        getSession().setAttribute(SELECTED_TESTCASE_LIST, selectedTestCaseList);
    }
    /**
     * @return Returns the selectedTestCaseVal.
     */
    public String getSelectedTestCaseVal() {
        return selectedTestCaseVal;
    }
    /**
     * @param selectedTestCaseVal The selectedTestCaseVal to set.
     */
    public void setSelectedTestCaseVal(String selectedTestCaseVal) {
        this.selectedTestCaseVal = selectedTestCaseVal;
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
     * @return Returns the isCopy.
     */
    public boolean getIsCopy() {
        return isCopy;
    }
    /**
     * @param isCopy The isCopy to set.
     */
    public void setIsCopy(boolean isCopy) {
        this.isCopy = isCopy;
    }
	/**
	 * @return Returns the testSuiteResults.
	 */
	public List getTestSuiteResults() {
		return testSuiteResults;
	}
	/**
	 * @param testSuiteResults The testSuiteResults to set.
	 */
	public void setTestSuiteResults(List testSuiteResults) {
		this.testSuiteResults = testSuiteResults;
	}
	/**
	 * @return Returns the contractIdEntry.
	 */
	public boolean isContractIdEntry() {
		return contractIdEntry;
	}
	/**
	 * @param contractIdEntry The contractIdEntry to set.
	 */
	public void setContractIdEntry(boolean contractIdEntry) {
		this.contractIdEntry = contractIdEntry;
	}
	/**
	 * @return Returns the endDateEntry.
	 */
	public boolean isEndDateEntry() {
		return endDateEntry;
	}
	/**
	 * @param endDateEntry The endDateEntry to set.
	 */
	public void setEndDateEntry(boolean endDateEntry) {
		this.endDateEntry = endDateEntry;
	}
	/**
	 * @return Returns the startDateEntry.
	 */
	public boolean isStartDateEntry() {
		return startDateEntry;
	}
	/**
	 * @param startDateEntry The startDateEntry to set.
	 */
	public void setStartDateEntry(boolean startDateEntry) {
		this.startDateEntry = startDateEntry;
	}
	/**
	 * @return Returns the testSuiteNameEntry.
	 */
	public boolean isTestSuiteNameEntry() {
		return testSuiteNameEntry;
	}
	/**
	 * @param testSuiteNameEntry The testSuiteNameEntry to set.
	 */
	public void setTestSuiteNameEntry(boolean testSuiteNameEntry) {
		this.testSuiteNameEntry = testSuiteNameEntry;
	}
	/**
	 * @return Returns the selectedTestCaseValEntry.
	 */
	public boolean isSelectedTestCaseValEntry() {
		return selectedTestCaseValEntry;
	}
	/**
	 * @param selectedTestCaseValEntry The selectedTestCaseValEntry to set.
	 */
	public void setSelectedTestCaseValEntry(boolean selectedTestCaseValEntry) {
		this.selectedTestCaseValEntry = selectedTestCaseValEntry;
	}
	/**
	 * @return Returns the testSuiteDescEntry.
	 */
	public boolean isTestSuiteDescEntry() {
		return testSuiteDescEntry;
	}
	/**
	 * @param testSuiteDescEntry The testSuiteDescEntry to set.
	 */
	public void setTestSuiteDescEntry(boolean testSuiteDescEntry) {
		this.testSuiteDescEntry = testSuiteDescEntry;
	}
	/**
	 * @return Returns the executeTestSute.
	 */
	public boolean isExecuteTestSute() {
		executeTestSuite();
		return executeTestSute;
	}
	/**
	 * @param executeTestSute The executeTestSute to set.
	 */
	public void setExecuteTestSute(boolean executeTestSute) {
		this.executeTestSute = executeTestSute;
	}
	public String getDate(){
		return WPDStringUtil.getStringDate(new Date());
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
}