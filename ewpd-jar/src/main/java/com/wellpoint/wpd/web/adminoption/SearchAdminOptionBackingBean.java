/*
 * SearchAdminOptionBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.adminoption;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.common.adminoption.request.AdminOptionUnlockRequest;
import com.wellpoint.wpd.common.adminoption.request.AdminOptionViewRequest;
import com.wellpoint.wpd.common.adminoption.request.ApproveAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.CheckOutAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.DeleteAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.DeleteAllAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.PublishAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.RejectAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.ScheduleForTestAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.SearchAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.TestFailAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.TestPassAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.response.AdminOptionUnlockResponse;
import com.wellpoint.wpd.common.adminoption.response.AdminOptionViewResponse;
import com.wellpoint.wpd.common.adminoption.response.ApproveAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.CheckOutAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.DeleteAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.DeleteAllAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.PublishAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.RejectAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.ScheduleForTestAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.SearchAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.TestFailAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.TestPassAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * SearchAdminOptionBackingBean for Admin Option Search & related functionalities
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchAdminOptionBackingBean extends WPDBackingBean {

    private int adminOptionId = -1;

    private int version = -1;

    private String status;

    private String adminNameCriteria;

    private String criteriaTerm;

    private String criteriaQualifier;

    private String adminName;

    private String lastPage;

    private List adminOptionSearchResultList;

    private List validationMessages = null;

    private List benefitTermList;

    private List benefitQualifierList;

    private boolean requiredField = false;

    private List messages = null;

    private List viewAllList;

    private static String VIEW_ALL = "viewAll";

    private static final String VIEW_ALL_PAGE = "viewAllPage";

    private static final int MAX_SEARCH_RESULT_SIZE = 50;
    
    private String adminOptionSearchPrint;
    
    private boolean adminOptionPrint;
    
    private String breadCrumpText;

    private int adminOptionParentSysId;
    
  

    /**
     * @return adminOptionPrint
     * 
     * Returns the adminOptionPrint.
     */
    public boolean isAdminOptionPrint() {
        return adminOptionPrint;
    }
    /**
     * @param adminOptionPrint
     * 
     * Sets the adminOptionPrint.
     */
    public void setAdminOptionPrint(boolean adminOptionPrint) {
        this.adminOptionPrint = adminOptionPrint;
    }
    /**
     * @return adminOptionSearchPrint
     * 
     * Returns the adminOptionSearchPrint.
     */
    public String getAdminOptionSearchPrint() {
        this.setAdminOptionPrint(true);
        SearchAdminOptionRequest searchAdminOptionRequest = null;
        SearchAdminOptionResponse searchAdminOptionResponse = null;
        searchAdminOptionRequest = this.getSearchAdminOptionRequest();
        searchAdminOptionResponse = (SearchAdminOptionResponse) this
        	.executeService(searchAdminOptionRequest);
		if (null != searchAdminOptionResponse) {
		    this.setAdminOptionSearchResultList(searchAdminOptionResponse
		            .getAdminOptionSearchResultList());
		}
        return adminOptionSearchPrint;
    }
    /**
     * @param adminOptionSearchPrint
     * 
     * Sets the adminOptionSearchPrint.
     */
    public void setAdminOptionSearchPrint(String adminOptionSearchPrint) {
        this.adminOptionSearchPrint = adminOptionSearchPrint;
    }
    public SearchAdminOptionBackingBean() {
        this
                .setBreadCrumbText("Administration >> Administration Option >> Locate");
    }


    /**
     * Method to perform search on admin options
     * 
     * @return String
     */
    public String searchAdminOption() {
        if (validateRequiredFields()) {
            validationMessages = new ArrayList();
            validationMessages.add(new ErrorMessage(
                    WebConstants.ATLEAST_ONE_SEARCH));
            addAllMessagesToRequest(validationMessages);
            this
                    .setBreadCrumbText("Administration >> Administration Option >> Locate");
            return WebConstants.EMPTY_STRING;
        }
        SearchAdminOptionRequest searchAdminOptionRequest = null;
        SearchAdminOptionResponse searchAdminOptionResponse = null;
        searchAdminOptionRequest = this.getSearchAdminOptionRequest();
        searchAdminOptionResponse = (SearchAdminOptionResponse) this
                .executeService(searchAdminOptionRequest);
        if (null != searchAdminOptionResponse) {
            this.setAdminOptionSearchResultList(searchAdminOptionResponse
                    .getAdminOptionSearchResultList());
        }
        this
                .setBreadCrumbText("Administration >> Administration Option >> Locate");
        return WebConstants.EMPTY_STRING;
    }


    /**
     * This method fetches all version of an admin option selected.
     * 
     * @return String
     */
    public String getView() {
        String action = (String) (getRequest().getParameter("action"));

        if (VIEW_ALL_PAGE.equals(this.lastPage)) {
            action = VIEW_ALL;
        }
        AdminOptionViewRequest adminOptionViewRequest = (AdminOptionViewRequest) this
                .getServiceRequest(ServiceManager.ADMIN_OPTION_VIEW_REQUEST);
        AdminOptionVO adminOptionVO = new AdminOptionVO();
        if (VIEW_ALL.equals(action)) {
        	String adName=ESAPI.encoder().encodeForHTML(getRequest().getParameter("adminName"));
        	if(StringUtil.regExPatterValidation(adName)){
        		adName=adName;
    			}else{
    				adName=null;
    			}
        	 adminOptionVO.setAdminName(adName);
        }
        if (VIEW_ALL_PAGE.equals(this.lastPage)) {
            adminOptionVO.setAdminName(this.adminName);
        }
        adminOptionViewRequest.setAction(action);
        adminOptionViewRequest.setAdminOptionVO(adminOptionVO);

        AdminOptionViewResponse adminOptionViewResponse = (AdminOptionViewResponse) this
                .executeService(adminOptionViewRequest);
        if (null != adminOptionViewResponse) {
            if (null != adminOptionViewResponse.getAdminOptionResultList()
                    && adminOptionViewResponse.getAdminOptionResultList()
                            .size() > 0) {
                if ("viewAll".equals(action)) {
                    List locateList = adminOptionViewResponse
                            .getAdminOptionResultList();
                    this.setViewAllList(locateList);

                }
            }
        }
        addAllMessagesToRequest(this.messages);
        setBreadCrumbText("Administration >> Administration Option ("+adminOptionVO.getAdminName()+")  >> View All Versions");
        return "";

    }


    /**
     * Method to get SearchAdminOptionRequest
     * 
     * @return String
     */
    private SearchAdminOptionRequest getSearchAdminOptionRequest() {
        
        SearchAdminOptionRequest searchAdminOptionRequest = (SearchAdminOptionRequest) this
                .getServiceRequest(ServiceManager.ADMIN_OPTION_SEARCH_REQUEST);
        searchAdminOptionRequest.getAdminOptionVO().setMaxLocateResultSize(
                MAX_SEARCH_RESULT_SIZE);
        if(!isAdminOptionPrint()){
            searchAdminOptionRequest.getAdminOptionVO().setAdminName(
                    this.getAdminNameCriteria().trim());
            this.setAdminNameCriteria(this.getAdminNameCriteria().trim());
            searchAdminOptionRequest.getAdminOptionVO().setBenefitQualifierList(
                    this.getBenefitQualifierList());
            searchAdminOptionRequest.getAdminOptionVO().setBenefitTermList(
                    this.getBenefitTermList());
            getRequest().getSession().removeAttribute("adminOptionSearchCriteriaVO");
            getRequest().getSession().setAttribute("adminOptionSearchCriteriaVO",searchAdminOptionRequest.getAdminOptionVO());
			
        }else{
            if(null != getRequest().getSession().getAttribute("adminOptionSearchCriteriaVO")){
                searchAdminOptionRequest.setAdminOptionVO((AdminOptionVO)getRequest().getSession().getAttribute("adminOptionSearchCriteriaVO"));
    		}
        }
        
        return searchAdminOptionRequest;
    }


    /**
     * Method to returns the benefitQualifierList
     * 
     * @return List benefitQualifierList.
     */
    public List getBenefitQualifierList() {
        List qualifierList = new ArrayList();
        StringTokenizer tokenizer = new StringTokenizer(this
                .getCriteriaQualifier(), "~");
        int tokenCount = tokenizer.countTokens();
        for (int i = 0; i < tokenCount; i++) {
            String qualifierName = tokenizer.nextToken();
            if (null != qualifierName && (((i % 2) == 1) || i == 1))
                qualifierList.add(qualifierName);
        }
        return qualifierList;
    }


    /**
     * Returns the benefitTermList
     * 
     * @return List benefitTermList.
     */
    public List getBenefitTermList() {
        List termList = new ArrayList();
        StringTokenizer tokenizer = new StringTokenizer(this.getCriteriaTerm(),
                "~");
        int tokenCount = tokenizer.countTokens();
        for (int i = 0; i < tokenCount; i++) {
            String termName = tokenizer.nextToken();
            if (null != termName && (((i % 2) == 1) || i == 1))
                termList.add(termName);
        }
        return termList;
    }


    /**
     * Method to delete Admin Option
     * 
     * @return String
     */
    public String deleteAdminOption() {
        String returnMessage = "";
        DeleteAdminOptionRequest deleteAdminOptionRequest = null;
        DeleteAdminOptionResponse deleteAdminOptionResponse = null;

        // Create the Request Object
        deleteAdminOptionRequest = getDeleteAdminOptionRequest();

        // Call the service method
        deleteAdminOptionResponse = (DeleteAdminOptionResponse) this
                .executeService(deleteAdminOptionRequest);

        if (null != deleteAdminOptionResponse) {
            this.setMessages(deleteAdminOptionResponse.getMessages());
            if (VIEW_ALL_PAGE.equals(this.lastPage)) {
                returnMessage = this.getView();
            } else {
                returnMessage = this.searchAdminOption();
            }
            addAllMessagesToRequest(messages);
        }
        return returnMessage;
    }


    /**
     * Method to delete Admin Option
     * 
     * @return String
     */
    public String deleteAllVersions() {
        String returnMessage = "";
        DeleteAllAdminOptionRequest deleteAllAdminOptionRequest = null;
        DeleteAllAdminOptionResponse deleteAllAdminOptionResponse = null;

        // Create the Request Object
        deleteAllAdminOptionRequest = getDeleteAllAdminOptionRequest();

        // Call the service method
        deleteAllAdminOptionResponse = (DeleteAllAdminOptionResponse) this
                .executeService(deleteAllAdminOptionRequest);

        if (null != deleteAllAdminOptionResponse) {
            this.setMessages(deleteAllAdminOptionResponse.getMessages());
            if (VIEW_ALL_PAGE.equals(this.lastPage)) {
                returnMessage = this.getView();
            } else {
                returnMessage = this.searchAdminOption();
            }
            addAllMessagesToRequest(messages);
        }
        return returnMessage;
    }
    
    
    /**
     * Method to get DeleteAdminOptionRequest object
     * 
     * @return deleteAdminOptionRequest
     */
    private DeleteAdminOptionRequest getDeleteAdminOptionRequest() {
        DeleteAdminOptionRequest adminOptionDeleteRequest = (DeleteAdminOptionRequest) this
                .getServiceRequest(ServiceManager.ADMIN_OPTION_DELETE_REQUEST);
        adminOptionDeleteRequest.getAdminOptionVO().setAdminOptionId(
                this.getAdminOptionId());
        adminOptionDeleteRequest.getAdminOptionVO()
                .setAdminName(this.adminName);
        adminOptionDeleteRequest.getAdminOptionVO().setVersion(this.version);
        return adminOptionDeleteRequest;
    }

    
    /**
     * Method to get DeleteAllAdminOptionRequest object
     * 
     * @return deleteAllAdminOptionRequest
     */
    private DeleteAllAdminOptionRequest getDeleteAllAdminOptionRequest() {
        DeleteAllAdminOptionRequest adminOptionDeleteAllRequest = (DeleteAllAdminOptionRequest) this
                .getServiceRequest(ServiceManager.ADMIN_OPTION_DELETE_ALL_REQUEST);
        adminOptionDeleteAllRequest.getAdminOptionVO().setAdminOptionId(
                this.getAdminOptionId());
        adminOptionDeleteAllRequest.getAdminOptionVO()
                .setAdminName(this.adminName);
        adminOptionDeleteAllRequest.getAdminOptionVO().setVersion(this.version);
        return adminOptionDeleteAllRequest;
    }    

    
    /**
     * Method to validate if the required fields are given by the user.
     * 
     * @return boolean
     */
    private boolean validateRequiredFields() {
        if ((this.adminNameCriteria == null || "".equals(this.adminNameCriteria
                .trim()))
                && (this.criteriaTerm == null || "".equals(this.criteriaTerm
                        .trim()))
                && (this.criteriaQualifier == null || ""
                        .equals(this.criteriaQualifier.trim())) ) {
            requiredField = true;
        }

        return requiredField;
    }


    /**
     * Method to check Out Admin Option Details.
     * 
     * @return String
     */
    public String checkOutAdminOption() {

        CheckOutAdminOptionRequest checkOutAdminOptionRequest = null;
        CheckOutAdminOptionResponse checkOutAdminOptionResponse = null;
        String returnString;
        //Creating request object
        checkOutAdminOptionRequest = (CheckOutAdminOptionRequest) this
                .getServiceRequest(ServiceManager.ADMIN_OPTION_CHECKOUT_REQUEST);

        //Copying the admin option details to the VO in the request.
        checkOutAdminOptionRequest.getAdminOptionVO().setAdminOptionId(
                this.adminOptionId);
        checkOutAdminOptionRequest.getAdminOptionVO().setAdminOptionParentSysId(this.adminOptionParentSysId);
        checkOutAdminOptionRequest.getAdminOptionVO().setAdminName(
                this.adminName);
        checkOutAdminOptionRequest.getAdminOptionVO().setVersion(this.version);

        //Creating the response object
        checkOutAdminOptionResponse = (CheckOutAdminOptionResponse) this
                .executeService(checkOutAdminOptionRequest);

        if (null != checkOutAdminOptionResponse) {
            this.setMessages(checkOutAdminOptionResponse.getMessages());
            if (checkOutAdminOptionResponse.isErrorFlag()) {
                returnString = this.searchAdminOption();
                addAllMessagesToRequest(messages);
                return returnString;
            } else {
                AdminOptionVO adminOptionVO = checkOutAdminOptionResponse
                        .getAdminOptionVO();
                Application application = FacesContext.getCurrentInstance()
                        .getApplication();
                CreateAdminOptionBackingBean createAdminOptionBackingBean = ((CreateAdminOptionBackingBean) application
                        .createValueBinding("#{createAdminOptionBackingBean}")
                        .getValue(FacesContext.getCurrentInstance()));
                createAdminOptionBackingBean.setAdminName(adminOptionVO
                        .getAdminName());
                createAdminOptionBackingBean.setAdminOptionId(adminOptionVO
                        .getAdminOptionId());
                createAdminOptionBackingBean.setVersion(adminOptionVO
                        .getVersion());
                createAdminOptionBackingBean.setValidationMessages(messages);
                return createAdminOptionBackingBean
                        .retrieveAdminOptionQuestion();
            }
        }
        return this.searchAdminOption();

    }


    /**
     * Method to publish the selected Admin Option
     * 
     * @return String returnMessage
     */
    public String publish() {
        String returnMessage = null;
        PublishAdminOptionRequest publishAdminOptionRequest = null;
        PublishAdminOptionResponse publishAdminOptionResponse = null;
        // Create the Request Object
        publishAdminOptionRequest = getPublishAdminOptionRequest();
        // Call the service method
        publishAdminOptionResponse = (PublishAdminOptionResponse) this
                .executeService(publishAdminOptionRequest);
        if (null != publishAdminOptionResponse) {
            this.setMessages(publishAdminOptionResponse.getMessages());

            if (VIEW_ALL_PAGE.equals(this.lastPage)) {
                returnMessage = this.getView();
            } else {
                returnMessage = this.searchAdminOption();
            }
            addAllMessagesToRequest(messages);
        }
        return returnMessage;
    }


    /**
     * Method to create PublishAdminOptionRequest object and to set the admin
     * option details to the object
     * 
     * @return PublishAdminOptionRequest publishAdminOptionRequest
     */
    private PublishAdminOptionRequest getPublishAdminOptionRequest() {
        PublishAdminOptionRequest publishAdminOptionRequest = (PublishAdminOptionRequest) this
                .getServiceRequest(ServiceManager.PUBLISH_ADMINOPTION_REQUEST);
        publishAdminOptionRequest.getAdminOptionVO().setAdminName(
                this.getAdminName());
        publishAdminOptionRequest.getAdminOptionVO().setAdminOptionId(
                this.getAdminOptionId());
        publishAdminOptionRequest.getAdminOptionVO().setVersion(
                this.getVersion());
        return publishAdminOptionRequest;
    }


    /**
     * Method to shedule for test the selected Admin Option
     * 
     * @return String returnMessage
     */
    public String scheduleTest() {
        String returnMessage = null;
        ScheduleForTestAdminOptionRequest scheduleForTestAdminOptionRequest = null;
        ScheduleForTestAdminOptionResponse scheduleForTestAdminOptionResponse = null;
        // Create the Request Object
        scheduleForTestAdminOptionRequest = getScheduleForTestAdminOptionRequest();
        // Call the service method
        scheduleForTestAdminOptionResponse = (ScheduleForTestAdminOptionResponse) this
                .executeService(scheduleForTestAdminOptionRequest);

        if (null != scheduleForTestAdminOptionResponse) {
            this.setMessages(scheduleForTestAdminOptionResponse.getMessages());
            if (VIEW_ALL_PAGE.equals(this.lastPage)) {
                returnMessage = this.getView();
            } else {
                returnMessage = this.searchAdminOption();
            }
            addAllMessagesToRequest(messages);
        }
        return returnMessage;
    }


    /**
     * Method to create ScheduleForTestAdminOptionRequest object and to set the
     * admin option details to the object
     * 
     * @return ScheduleForTestAdminOptionRequest
     *         scheduleForTestAdminOptionRequest
     */
    private ScheduleForTestAdminOptionRequest getScheduleForTestAdminOptionRequest() {
        ScheduleForTestAdminOptionRequest scheduleForTestAdminOptionRequest = (ScheduleForTestAdminOptionRequest) this
                .getServiceRequest(ServiceManager.SCHEDULEFORTEST_ADMINOPTION_REQUEST);
        scheduleForTestAdminOptionRequest.getAdminOptionVO().setAdminName(
                this.getAdminName());
        scheduleForTestAdminOptionRequest.getAdminOptionVO().setAdminOptionId(
                this.getAdminOptionId());
        scheduleForTestAdminOptionRequest.getAdminOptionVO().setVersion(
                this.getVersion());
        return scheduleForTestAdminOptionRequest;
    }


    /**
     * Method to test pass the selected Admin Option
     * 
     * @return String returnMessage
     */
    public String testPass() {
        String returnMessage = null;
        TestPassAdminOptionRequest testPassAdminOptionRequest = null;
        TestPassAdminOptionResponse testPassAdminOptionResponse = null;
        // Create the Request Object
        testPassAdminOptionRequest = getTestPassAdminOptionRequest();
        // Call the service method
        testPassAdminOptionResponse = (TestPassAdminOptionResponse) this
                .executeService(testPassAdminOptionRequest);

        if (null != testPassAdminOptionResponse) {
            this.setMessages(testPassAdminOptionResponse.getMessages());
            if (VIEW_ALL_PAGE.equals(this.lastPage)) {
                returnMessage = this.getView();
            } else {
                returnMessage = this.searchAdminOption();
            }
            addAllMessagesToRequest(messages);
        }
        return returnMessage;
    }


    /**
     * Method to test fail the selected Admin Option
     * 
     * @return String returnMessage
     */
    public String testFail() {
        String returnMessage = null;
        TestFailAdminOptionRequest testFailAdminOptionRequest = null;
        TestFailAdminOptionResponse testFailAdminOptionResponse = null;
        // Create the Request Object
        testFailAdminOptionRequest = getTestFailAdminOptionRequest();
        // Call the service method
        testFailAdminOptionResponse = (TestFailAdminOptionResponse) this
                .executeService(testFailAdminOptionRequest);

        if (null != testFailAdminOptionResponse) {
            this.setMessages(testFailAdminOptionResponse.getMessages());
            if (VIEW_ALL_PAGE.equals(this.lastPage)) {
                returnMessage = this.getView();
            } else {
                returnMessage = this.searchAdminOption();
            }
            addAllMessagesToRequest(messages);
        }
        return returnMessage;
    }


    /**
     * Method to create TestPassAdminOptionRequest object and to set the admin
     * option details to the object
     * 
     * @return TestPassAdminOptionRequest testPassAdminOptionRequest
     */
    private TestPassAdminOptionRequest getTestPassAdminOptionRequest() {
        TestPassAdminOptionRequest testPassAdminOptionRequest = (TestPassAdminOptionRequest) this
                .getServiceRequest(ServiceManager.TEST_PASS_ADMINOPTION_REQUEST);
        testPassAdminOptionRequest.getAdminOptionVO().setAdminName(
                this.getAdminName());
        testPassAdminOptionRequest.getAdminOptionVO().setAdminOptionId(
                this.getAdminOptionId());
        testPassAdminOptionRequest.getAdminOptionVO().setVersion(
                this.getVersion());
        return testPassAdminOptionRequest;
    }


    /**
     * Method to create TestFailAdminOptionRequest object and to set the admin
     * option details to the object
     * 
     * @return TestFailAdminOptionRequest testFailAdminOptionRequest
     */
    private TestFailAdminOptionRequest getTestFailAdminOptionRequest() {
        TestFailAdminOptionRequest testFailAdminOptionRequest = (TestFailAdminOptionRequest) this
                .getServiceRequest(ServiceManager.TEST_FAIL_ADMINOPTION_REQUEST);
        testFailAdminOptionRequest.getAdminOptionVO().setAdminName(
                this.getAdminName());
        testFailAdminOptionRequest.getAdminOptionVO().setAdminOptionId(
                this.getAdminOptionId());
        testFailAdminOptionRequest.getAdminOptionVO().setVersion(
                this.getVersion());
        testFailAdminOptionRequest.getAdminOptionVO().setStatus(
                this.getStatus());
        return testFailAdminOptionRequest;
    }


    /**
     * Method for admin option approve
     * 
     * @return String returnMessage
     */
    public String approve() {
        String returnMessage = null;
        ApproveAdminOptionRequest approveAdminOptionRequest = null;
        ApproveAdminOptionResponse adminOptionResponse = null;
        // Create the Request Object
        approveAdminOptionRequest = getApproveAdminOptionRequest();
        // Call the service method
        adminOptionResponse = (ApproveAdminOptionResponse) this
                .executeService(approveAdminOptionRequest);
        if (null != adminOptionResponse) {
            this.setMessages(adminOptionResponse.getMessages());
            if (VIEW_ALL_PAGE.equals(this.lastPage)) {
                returnMessage = this.getView();
            } else {
                returnMessage = this.searchAdminOption();
            }
            addAllMessagesToRequest(messages);
        }
        return returnMessage;
    }
    
    
    /**
     * Method for admin option approve
     * 
     * @return String returnMessage
     */
    public String unlock() {
        String returnMessage = null;
        AdminOptionUnlockRequest adminOptionUnlockRequest = null;
        AdminOptionUnlockResponse adminOptionResponse = null;
        // Create the Request Object
        adminOptionUnlockRequest = getUnlockAdminOptionRequest();
        // Call the service method
        adminOptionResponse = (AdminOptionUnlockResponse) this
                .executeService(adminOptionUnlockRequest);
        if (null != adminOptionResponse) {
            this.setMessages(adminOptionResponse.getMessages());
            if (VIEW_ALL_PAGE.equals(this.lastPage)) {
                returnMessage = this.getView();
            } else {
                returnMessage = this.searchAdminOption();
            }
            addAllMessagesToRequest(messages);
        }
        return returnMessage;
    }



    /**
     * Method for admin option reject
     * 
     * @return String returnMessage
     */
    public String reject() {
        String returnMessage = null;
        RejectAdminOptionRequest rejectAdminOptionRequest = null;
        RejectAdminOptionResponse adminOptionResponse = null;
        // Create the Request Object
        rejectAdminOptionRequest = getRejectAdminOptionRequest();
        // Call the service method
        adminOptionResponse = (RejectAdminOptionResponse) this
                .executeService(rejectAdminOptionRequest);
        if (null != adminOptionResponse) {
            this.setMessages(adminOptionResponse.getMessages());
            if (VIEW_ALL_PAGE.equals(this.lastPage)) {
                returnMessage = this.getView();
            } else {
                returnMessage = this.searchAdminOption();
            }
            addAllMessagesToRequest(messages);
        }
        return returnMessage;
    }


    /**
     * Method to create ApproveAdminOptionRequest object and to set the admin
     * option details to the object
     * 
     * @return ApproveAdminOptionRequest approveAdminOptionRequest
     */
    private ApproveAdminOptionRequest getApproveAdminOptionRequest() {
        ApproveAdminOptionRequest approveAdminOptionRequest = (ApproveAdminOptionRequest) this
                .getServiceRequest(ServiceManager.APPROVE_ADMIN_OPTION_REQUEST);
        approveAdminOptionRequest.getAdminOptionVO().setAdminName(
                this.getAdminName());
        approveAdminOptionRequest.getAdminOptionVO().setAdminOptionId(
                this.getAdminOptionId());
        approveAdminOptionRequest.getAdminOptionVO().setVersion(
                this.getVersion());
        approveAdminOptionRequest.getAdminOptionVO()
                .setStatus(this.getStatus());
        return approveAdminOptionRequest;
    }
    
    /**
     * Method to create ApproveAdminOptionRequest object and to set the admin
     * option details to the object
     * 
     * @return ApproveAdminOptionRequest approveAdminOptionRequest
     */
    private AdminOptionUnlockRequest getUnlockAdminOptionRequest() {
    	AdminOptionUnlockRequest adminOptionUnlockRequest = (AdminOptionUnlockRequest) this
                .getServiceRequest(ServiceManager.UNLOCK_ADMIN_OPTION_REQUEST);
    	if(null!= this.getAdminName()){
    	adminOptionUnlockRequest.getAdminOptionVO().setAdminName(
                this.getAdminName());
    	}
    	adminOptionUnlockRequest.getAdminOptionVO().setAdminOptionId(
                this.getAdminOptionId());
    	adminOptionUnlockRequest.getAdminOptionVO().setVersion(
                this.getVersion());
    	adminOptionUnlockRequest.getAdminOptionVO()
                .setStatus(this.getStatus());
    
        return adminOptionUnlockRequest;
    }


    /**
     * Method to create RejectAdminOptionRequest object and to set the admin
     * option details to the object
     * 
     * @return RejectAdminOptionRequest rejectAdminOptionRequest
     */
    private RejectAdminOptionRequest getRejectAdminOptionRequest() {
        RejectAdminOptionRequest rejectAdminOptionRequest = (RejectAdminOptionRequest) this
                .getServiceRequest(ServiceManager.REJECT_ADMIN_OPTION_REQUEST);
        rejectAdminOptionRequest.getAdminOptionVO().setAdminName(
                this.getAdminName());
        rejectAdminOptionRequest.getAdminOptionVO().setAdminOptionId(
                this.getAdminOptionId());
        rejectAdminOptionRequest.getAdminOptionVO().setVersion(
                this.getVersion());
        rejectAdminOptionRequest.getAdminOptionVO().setStatus(this.getStatus());
        return rejectAdminOptionRequest;
    }


    /**
     * Returns the adminOptionId.
     * 
     * @return adminOptionId
     */
    public int getAdminOptionId() {
        return adminOptionId;

    }


    /**
     * Sets the adminOptionId.
     * 
     * @param adminOptionId
     */
    public void setAdminOptionId(int adminOptionId) {
        this.adminOptionId = adminOptionId;
    }


    /**
     * Returns the adminNameCriteria.
     * 
     * @return adminNameCriteria
     */
    public String getAdminNameCriteria() {
        return adminNameCriteria;
    }


    /**
     * The adminNameCriteria to set.
     * 
     * @param adminNameCriteria
     *  
     */
    public void setAdminNameCriteria(String adminNameCriteria) {
        this.adminNameCriteria = adminNameCriteria;
    }


    /**
     * Returns the adminOptionSearchResultList.
     * 
     * @return List adminOptionSearchResultList
     */
    public List getAdminOptionSearchResultList() {
        if (this.adminOptionSearchResultList != null
                && this.adminOptionSearchResultList.size() == 0)
            this.adminOptionSearchResultList = null;
        return adminOptionSearchResultList;
    }


    /**
     * The adminOptionSearchResultList to set.
     * 
     * @param adminOptionSearchResultList
     *  
     */
    public void setAdminOptionSearchResultList(List adminOptionSearchResultList) {
        this.adminOptionSearchResultList = adminOptionSearchResultList;
    }


    /**
     * Returns the validationMessages.
     * 
     * @return List validationMessages
     */
    public List getValidationMessages() {
        return validationMessages;
    }


    /**
     * The validationMessages to set.
     * 
     * @param validationMessages
     *  
     */
    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }


    /**
     * Returns the criteriaQualifier.
     * 
     * @return String criteriaQualifier
     */
    public String getCriteriaQualifier() {
        return criteriaQualifier;
    }


    /**
     * The criteriaQualifier to set.
     * 
     * @param criteriaQualifier
     */
    public void setCriteriaQualifier(String criteriaQualifier) {
        this.criteriaQualifier = criteriaQualifier;
    }


    /**
     * Returns the criteriaTerm.
     * 
     * @return criteriaTerm
     */
    public String getCriteriaTerm() {
        return criteriaTerm;
    }


    /**
     * The criteriaTerm to set.
     * 
     * @param criteriaTerm
     */
    public void setCriteriaTerm(String criteriaTerm) {
        this.criteriaTerm = criteriaTerm;
    }


    /**
     * Returns the adminName.
     * 
     * @return String adminName
     */
    public String getAdminName() {
        return adminName;
    }


    /**
     * The adminName to set.
     * 
     * @param adminName
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }


    /**
     * Returns the messages
     * 
     * @return List messages.
     */
    public List getMessages() {
        return messages;
    }


    /**
     * Sets the messages
     * 
     * @param messages.
     */
    public void setMessages(List messages) {
        this.messages = messages;
    }


    /**
     * Returns the status
     * 
     * @return String status.
     */
    public String getStatus() {
        return status;
    }


    /**
     * Sets the status
     * 
     * @param status.
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * Returns the version
     * 
     * @return int version.
     */
    public int getVersion() {
        return version;
    }


    /**
     * Sets the version
     * 
     * @param version.
     */
    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * Returns the viewAllList.
     * 
     * @return viewAllList
     */
    public List getViewAllList() {
        return viewAllList;
    }


    /**
     * Sets the viewAllList.
     * 
     * @param viewAllList
     */
    public void setViewAllList(List viewAllList) {
        this.viewAllList = viewAllList;
    }


    /**
     * Returns the lastPage.
     * 
     * @return lastPage
     */
    public String getLastPage() {
        return lastPage;
    }


    /**
     * Sets the lastPage.
     * 
     * @param lastPage
     */
    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }
    /**
     * @return breadCrumpText
     * 
     * Returns the breadCrumpText.
     */
    public String getBreadCrumpText() {
        breadCrumpText = "Administration >> Administration Option >> Locate >> Print";
        return breadCrumpText;
    }
    /**
     * @param breadCrumpText
     * 
     * Sets the breadCrumpText.
     */
    public void setBreadCrumpText(String breadCrumpText) {
        this.breadCrumpText = breadCrumpText;
    }
	/**
	 * @return Returns the adminOptionParentSysId.
	 */
	public int getAdminOptionParentSysId() {
		return adminOptionParentSysId;
	}
	/**
	 * @param adminOptionParentSysId The adminOptionParentSysId to set.
	 */
	public void setAdminOptionParentSysId(int adminOptionParentSysId) {
		this.adminOptionParentSysId = adminOptionParentSysId;
	}
 
}