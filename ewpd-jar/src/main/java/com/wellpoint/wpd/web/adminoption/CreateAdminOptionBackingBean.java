/*
 * CreateAdminOptionBackingBean.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.adminoption;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.common.adminoption.request.CheckInAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.CreateAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.RetrieveAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.ViewAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.response.CheckInAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.CreateAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.RetrieveAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.ViewAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * CreateAdminOptionBackingBean for admin option create.
 * 
 * This bean will bind with the jsp pages.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CreateAdminOptionBackingBean extends WPDBackingBean {

    private int adminOptionId = -1;

    private String adminName;

    private String term;

    private String qualifier;

    private String reference;

    private String termId;

    private String termDesc;

    private String qualifierId;

    private String qualifierDesc;

    private String referenceId;

    private String referenceDesc;

    private String createdUserId;
    
    private String breadCrumpText;

    private Date createdDate;

    private String updatedUserId;

    private Date updatedDate;

    private String state;

    private String status;

    private int version = -1;

    private List validationMessages = null;

    private boolean requiredAdminName = false;

    private boolean requiredTerm = false;

    private boolean checkInOpted = false;

    private String dummyVariable;
    
    private boolean checkUnlockFlag;


    public CreateAdminOptionBackingBean() {
        setBreadCrump();
    }


    /**
     * Sets the breadcrump
     *  
     */
    protected void setBreadCrump() {
        if (this.adminName == null)
            this.setBreadCrumbText(WebConstants.ADMIN_CREATE_BREADCRUMB);
    }


    /**
     * Method to display Admin option Tab
     * 
     * @return String
     */
    public String displayAdminOptionTab() {
        String returnValue = this.retrieveAdminOptionQuestion();
        return returnValue;
    }


    /**
     * Method to retrieve Admin Option Question
     * 
     * @return String
     */
    public String retrieveAdminOptionQuestion() {
        RetrieveAdminOptionRequest retrieveAdminOptionRequest = null;
        RetrieveAdminOptionResponse retrieveAdminOptionResponse = null;
        //Creating the request object
        retrieveAdminOptionRequest = (RetrieveAdminOptionRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_ADMIN_OPTION_REQUEST);
        //Copying the admin option details to the VO in the request.
        retrieveAdminOptionRequest.getAdminOptionVO().setAdminOptionId(
                this.adminOptionId);
        retrieveAdminOptionRequest.getAdminOptionVO().setAdminName(
                this.adminName);
        retrieveAdminOptionRequest.getAdminOptionVO().setVersion(this.version);

        this.getSession().setAttribute("adminId",
                new Integer(this.adminOptionId).toString());
        this.getSession().setAttribute("adminname", this.adminName);
        this.getSession().setAttribute("version",
                new Integer(this.version).toString());
        this.getSession().setAttribute("state",this.state);
        this.getSession().setAttribute("status",this.status);		
        //Creating the response object
        retrieveAdminOptionResponse = (RetrieveAdminOptionResponse) this
                .executeService(retrieveAdminOptionRequest);
        if (null != retrieveAdminOptionResponse) {
        	
        	if(retrieveAdminOptionResponse.isUnlockFlag()){
        		
        		this.setCheckUnlockFlag(true);
        		return "adminOptionEditPage";
        	}
        	else{
	            //Coping the admin option details to the backing bean
	            this.copyResponseValuesToBackingBean(retrieveAdminOptionResponse
	                    .getAdminOptionVO());
	            getCombinedStrings();
	            addAllMessagesToRequest(validationMessages);
        	}
        }
        setBreadCrumbText("Administration >> Administration Option "
                + "(" + this.adminName + ") >> Edit");
        return "adminOptionEditPage";
    }


    /**
     * Method to create Admin Option
     * 
     * @return String
     */
    public String saveAdminOption() {

        CreateAdminOptionRequest createAdminOptionRequest = null;
        CreateAdminOptionResponse createAdminOptionResponse = null;

        //Validating if all the mandotary details are available, before saving.
        // If not, then it will redirects to the same page with error message.
        if (!validateField()) {
            addAllMessagesToRequest(validationMessages);
            return WebConstants.EMPTY_STRING;
        }

        splitStringValues();

        //Creating the request object
        createAdminOptionRequest = this.getCreateAdminOptionRequest();

        //Setting that this is for creating a new Admin Option
        createAdminOptionRequest.setCreateFlag(true);
        
        //Creating the response object
        createAdminOptionResponse = (CreateAdminOptionResponse) this
                .executeService(createAdminOptionRequest);

        if (null != createAdminOptionResponse) {
            //Coping the admin option details to the backing bean
            this.copyResponseValuesToBackingBean(createAdminOptionResponse
                    .getAdminOptionVO());
            getCombinedStrings();

            this.getSession().setAttribute(
                    "adminId",
                    String.valueOf(createAdminOptionResponse.getAdminOptionVO()
                            .getAdminOptionId()));
            this.getSession()
                    .setAttribute(
                            "adminname",
                            createAdminOptionResponse.getAdminOptionVO()
                                    .getAdminName());
            this.getSession().setAttribute(
                    "version",
                    String.valueOf(createAdminOptionResponse.getAdminOptionVO()
                            .getVersion()));

            if (!createAdminOptionResponse.isErrorFlag()) {
                setBreadCrumbText("Administration >> Administration Option "
                        + "(" + this.adminName + ") >> Edit");
                return WebConstants.SUCCESS;

            }
        }

        return WebConstants.EMPTY_STRING;
    }


    /**
     * Method to save the updated Admin Option details.
     * 
     * @return String
     */
    public String updateAdminOption() {

        CreateAdminOptionRequest createAdminOptionRequest = null;
        CreateAdminOptionResponse createAdminOptionResponse = null;

        splitStringValues();

        //Creating the request object
        createAdminOptionRequest = this.getCreateAdminOptionRequest();

        //Setting that this is for creating a new Admin Option
        createAdminOptionRequest.setCreateFlag(false);

        //Creating the response object
        createAdminOptionResponse = (CreateAdminOptionResponse) this
                .executeService(createAdminOptionRequest);

        if (null != createAdminOptionResponse) {
            //Coping the admin option details to the backing bean
            this.copyResponseValuesToBackingBean(createAdminOptionResponse
                    .getAdminOptionVO());
            getCombinedStrings();
        }
        setBreadCrumbText("Administration >> Administration Option "
                + "(" + this.adminName + ") >> Edit");
        return WebConstants.SUCCESS;

    }


    /**
     * Method to check In Admin Option Details.
     * 
     * @return String
     */
    public String checkInAdminOption() {

        updateAdminOption();

        CheckInAdminOptionRequest checkInAdminOptionRequest = null;
        CheckInAdminOptionResponse checkInAdminOptionResponse = null;

        splitStringValues();

        //Creating request object
        checkInAdminOptionRequest = (CheckInAdminOptionRequest) this
                .getServiceRequest(ServiceManager.ADMIN_OPTION_CHECKIN_REQUEST);
        checkInAdminOptionRequest.getAdminOptionVO().setAdminOptionId(
                this.adminOptionId);
        checkInAdminOptionRequest.getAdminOptionVO().setAdminName(
                this.adminName);
        checkInAdminOptionRequest.getAdminOptionVO().setQualifierId(
                this.qualifierId);
        checkInAdminOptionRequest.getAdminOptionVO().setTermId(this.termId);
        checkInAdminOptionRequest.getAdminOptionVO().setReferenceId(
                this.referenceId);
        checkInAdminOptionRequest.getAdminOptionVO().setQualifierDesc(
                this.qualifierDesc);
        checkInAdminOptionRequest.getAdminOptionVO().setTermDesc(this.termDesc);
        checkInAdminOptionRequest.getAdminOptionVO().setReferenceDesc(
                this.referenceDesc);
        checkInAdminOptionRequest.getAdminOptionVO().setVersion(this.version);
        checkInAdminOptionRequest.getAdminOptionVO().setStatus(this.status);
        checkInAdminOptionRequest.getAdminOptionVO().setStateValue(this.state);
        checkInAdminOptionRequest.getAdminOptionVO().setCreatedUser(
                this.createdUserId);
        checkInAdminOptionRequest.getAdminOptionVO().setCreatedTimestamp(this.createdDate);
        checkInAdminOptionRequest.getAdminOptionVO().setLastUpdatedUser(
                this.updatedUserId);
        checkInAdminOptionRequest.getAdminOptionVO().setLastUpdatedTimestamp(this.updatedDate);

        checkInAdminOptionRequest.setCheckInOpted(this.checkInOpted);

        //Creating the response object
        checkInAdminOptionResponse = (CheckInAdminOptionResponse) this
                .executeService(checkInAdminOptionRequest);
       
        if (null != checkInAdminOptionResponse) {
            this.copyResponseValuesToBackingBean(checkInAdminOptionResponse
                    .getAdminOptionVO());
            getCombinedStrings();
            if (!checkInAdminOptionResponse.isErrorFlag()) {
                resetValues();
                return "adminOptionCreatePage";
            }
        }
        this.getRequest().getSession().setAttribute("AdminId",new Integer(this.adminOptionId));
        this.getRequest().getSession().setAttribute("AdminName",this.adminName);
        this.getRequest().getSession().setAttribute("AdminVersion",new Integer(this.version));
        setBreadCrumbText("Administration >> Administration Option"
                + "(" + this.adminName + ") >> Edit");
        return WebConstants.EMPTY_STRING;
    }


    /**
     * Validate whether all the mandatory fields are filled. If everything is
     * available, true will be returned, else return false.
     * 
     * @return boolean
     */
    private boolean validateField() {
        validationMessages = new ArrayList();
        //Checking the adminName field is filled with non blanks.
        boolean valid = true;
        if (this.getAdminName() == null
                || "".equals(this.getAdminName().trim())) {
            requiredAdminName = true;
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            valid= false;
        }
        if (this.getAdminName().trim().length() > 250) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.ADMIN_OPTION_NAME_EXCEED_LIMIT_LENGTH));
            valid= false;
        }
        if((null != this.getQualifier() && !this.getQualifier().equals(WebConstants.EMPTY_STRING))
        		&& !(null != this.getTerm() && !this.getTerm().equals(WebConstants.EMPTY_STRING))){
            requiredTerm = true;
            validationMessages.add(new ErrorMessage(
                    "adminoption.term.require"));
            valid= false;
        }
        return valid;
    }


    /**
     * Method for creating CreateAdminOptionRequest and setting the values to
     * the VO in the request.
     * 
     * @return CreateAdminOptionRequest request
     */
    private CreateAdminOptionRequest getCreateAdminOptionRequest() {
        //Creating AdminOptionRequest
        CreateAdminOptionRequest request = (CreateAdminOptionRequest) this
                .getServiceRequest(ServiceManager.CREATE_ADMIN_OPTION_REQUEST);
        //Copying the admin option details to the VO in the request.
        request.getAdminOptionVO().setAdminOptionId(this.adminOptionId);
        request.getAdminOptionVO().setAdminName(this.adminName.trim());
        this.setAdminName(this.adminName.trim());
        request.getAdminOptionVO().setQualifierId(this.qualifierId);
        request.getAdminOptionVO().setTermId(this.termId);
        //request.getAdminOptionVO().setReferenceId(this.referenceId);
        request.getAdminOptionVO().setQualifierDesc(this.qualifierDesc);
        request.getAdminOptionVO().setTermDesc(this.termDesc);
        request.getAdminOptionVO().setReferenceDesc(this.referenceDesc);
        request.getAdminOptionVO().setVersion(this.version);
       /* if(this.version <=1){
        	request.getAdminOptionVO().setAdminOptionParentSysId(this.adminOptionId);
        	this.getSession().setAttribute("adminOptionParentSysId",Integer.toString(this.adminOptionId));                    
        }*/
        request.getAdminOptionVO().setStatus(this.status);
        return request;
    }


    /**
     * gets the admin option view
     * 
     * @return String
     */
    public String getViewAdminOptions() {

        ArrayList validationMessages = null;
        ViewAdminOptionRequest viewAdminOptionRequest = (ViewAdminOptionRequest) this
                .getServiceRequest(ServiceManager.VIEW_ADMIN_OPTION_REQUEST);

        //Setting the values in the session
        String adminId = (String) (ESAPI.encoder().encodeForHTML(getRequest().getParameter("adminkey")));
        if(null!=adminId  && adminId.matches("[0-9a-zA-Z_]+")){
        	adminId = adminId;
       }
        String adminName = (String) (ESAPI.encoder().encodeForHTML(getRequest().getParameter("adminname")));
        if(null!=adminName  && adminName.matches("[0-9a-zA-Z_]+")){
        	adminName = adminName;
       }
        String version = (String) (ESAPI.encoder().encodeForHTML(getRequest().getParameter("version")));
        if(null!=version  && version.matches("[0-9a-zA-Z_]+")){
        	version = version;
       }

        if (adminId == null && adminName == null && version == null) {
            AdminOptionVO adminOptionVO = new AdminOptionVO();
            adminOptionVO.setAdminOptionId(Integer.parseInt((String) this
                    .getSession().getAttribute("adminId")));
            adminOptionVO.setAdminName((String) this.getSession().getAttribute(
                    "adminname"));
            adminOptionVO.setVersion(Integer.parseInt((String) this
                    .getSession().getAttribute("version")));
            viewAdminOptionRequest.setAdminOptionVO(adminOptionVO);
        } else {
            this.getSession().setAttribute("adminId", adminId);
            this.getSession().setAttribute("adminname", adminName);
            this.getSession().setAttribute("version", version);

            int viewAdminId = Integer.parseInt(adminId);
            int viewVersion = Integer.parseInt(version);

            AdminOptionVO adminOptionVO = new AdminOptionVO();
            adminOptionVO.setAdminOptionId(viewAdminId);
            adminOptionVO.setAdminName(adminName);
            adminOptionVO.setVersion(viewVersion);
            viewAdminOptionRequest.setAdminOptionVO(adminOptionVO);
        }

        ViewAdminOptionResponse viewAdminOptionResponse = (ViewAdminOptionResponse) executeService(viewAdminOptionRequest);

        if (null != viewAdminOptionResponse) {
            //Coping the admin option details to the backing bean
            this.copyResponseValuesToBackingBean(viewAdminOptionResponse
                    .getAdminOptionVO());
            this.getSession().setAttribute("state",this.getState());
            this.getSession().setAttribute("status",this.getStatus());
            /*This added for changing the term and qualifier in view page from input text to output text*/
            /*if (null == this.termId)
                this.termId = " ";
            if (null == this.qualifierId)
                this.qualifierId = " ";
            this.term = this.termId;
            this.qualifier = this.qualifierId;*/
            getCombinedStrings();
            /*End of change*/
            addAllMessagesToRequest(validationMessages);
        }
        setBreadCrumbText("Administration >> Administration Option "+ "(" + this.adminName + ") >>View");
        return "adminOptionViewPage";

    }


    /**
     * Method to set the default values to the fields in the backing bean.
     *  
     */
    private void resetValues() {

        this.adminOptionId = -1;
        this.adminName = null;
        this.term = null;
        this.qualifier = null;
        this.reference = null;
        this.termId = null;
        this.termDesc = null;
        this.qualifierId = null;
        this.qualifierDesc = null;
        this.referenceId = null;
        this.referenceDesc = null;
        this.createdUserId = null;
        this.createdDate = null;
        this.updatedUserId = null;
        this.updatedDate = null;
        this.state = null;
        this.status = null;
        this.version = -1;
        this.validationMessages = null;
        this.requiredAdminName = false;
        this.requiredTerm = false;
        this.checkInOpted = false;

    }


    /**
     * Splitting the term, qualifier and references from the popup window into
     * seperate Strings.
     *  
     */
    private void splitStringValues() {
        StringTokenizer st = null;
        if(null!= term && !term.equals("~")){
        st = new StringTokenizer(term, "~");
        if(null != st){
	        while (st.hasMoreTokens()) {
	            this.termDesc = st.nextToken();
	            this.termId = st.nextToken();
	        }
        }
        }
        if(null!= qualifier && !qualifier.equals("~")){
        st = new StringTokenizer(qualifier, "~");
	        if(null != st){
	        while (st.hasMoreTokens()) {
	            this.qualifierDesc = st.nextToken();
	            this.qualifierId = st.nextToken();
	        }
	    }
        }
    }


    /**
     * Combining the id and description of term, qualifier and reference into
     * one String.
     *  
     */
    private void getCombinedStrings() {
        if (null == this.termDesc)
            this.termDesc = "";
        if (null == this.qualifierDesc)
            this.qualifierDesc = "";
        if (null == this.termId)
            this.termId = "";
        if (null == this.qualifierId)
            this.qualifierId = "";
        this.term = this.termDesc + "~" + this.termId;
        this.qualifier = this.qualifierDesc + "~" + this.qualifierId;
    }


    /**
     * Copying the return values in the Response object to the VO.
     * 
     * @param adminOptionVO
     */
    private void copyResponseValuesToBackingBean(AdminOptionVO adminOptionVO) {
        if (null != adminOptionVO) {
            this.adminOptionId = adminOptionVO.getAdminOptionId();
            this.adminName = adminOptionVO.getAdminName();
            this.qualifierId = adminOptionVO.getQualifierId();
            this.termId = adminOptionVO.getTermId();
            this.qualifierDesc = adminOptionVO.getQualifierDesc();
            this.termDesc = adminOptionVO.getTermDesc();
            this.referenceDesc = adminOptionVO.getReferenceDesc();
            this.createdUserId = adminOptionVO.getCreatedUser();
            this.createdDate = adminOptionVO.getCreatedTimestamp();
            this.updatedUserId = adminOptionVO.getLastUpdatedUser();
            this.updatedDate = adminOptionVO.getLastUpdatedTimestamp();
            this.version = adminOptionVO.getVersion();
            this.status = adminOptionVO.getStatus();
            if (null != adminOptionVO.getStateObject()) {
                this.state = adminOptionVO.getStateObject().getState();
            } else {
                this.state = adminOptionVO.getStateValue();
            }
        }
    }


    /**
     * Returns the adminOptionId
     * 
     * @return int adminOptionId.
     */
    public int getAdminOptionId() {
        return adminOptionId;
    }


    /**
     * Sets the adminOptionId
     * 
     * @param adminOptionId.
     */
    public void setAdminOptionId(int adminOptionId) {
        this.adminOptionId = adminOptionId;
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
     * Sets the adminName.
     * 
     * @param adminName
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }


    /**
     * Returns the term.
     * 
     * @return String term
     */
    public String getTerm() {
        return term;
    }


    /**
     * Sets the term.
     * 
     * @param term
     */
    public void setTerm(String term) {
        if (term == null)
            this.term = "";
        else
            this.term = term;
    }


    /**
     * Returns the qualifier.
     * 
     * @return qualifier
     */
    public String getQualifier() {
        return qualifier;
    }


    /**
     * Sets the qualifier
     * 
     * @param qualifier
     */
    public void setQualifier(String qualifier) {
        if (qualifier == null)
            this.qualifier = "";
        else
            this.qualifier = qualifier;
    }


    /**
     * Returns the reference
     * 
     * @return String reference.
     */
    public String getReference() {
        return reference;
    }


    /**
     * Sets the reference
     * 
     * @param reference.
     */
    public void setReference(String reference) {
        this.reference = reference;
    }


    /**
     * Returns the createdDate
     * 
     * @return String createdDate.
     */
    public Date getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate
     * 
     * @param createdDate.
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Returns the createdUserId
     * 
     * @return String createdUserId.
     */
    public String getCreatedUserId() {
        return createdUserId;
    }


    /**
     * Sets the createdUserId
     * 
     * @param createdUserId.
     */
    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }


    /**
     * Returns the updatedDate
     * 
     * @return String updatedDate.
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }


    /**
     * Sets the updatedDate
     * 
     * @param updatedDate.
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }


    /**
     * Returns the state
     * 
     * @return String state.
     */
    public String getState() {
        return state;
    }


    /**
     * Sets the state
     * 
     * @param state.
     */
    public void setState(String state) {
        this.state = state;
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
        if (version == -1)
            return 0;
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
     * Returns the updatedUserId
     * 
     * @return String updatedUserId.
     */
    public String getUpdatedUserId() {
        return updatedUserId;
    }


    /**
     * Sets the updatedUserId
     * 
     * @param updatedUserId.
     */
    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }


    /**
     * Returns the validationMessages.
     * 
     * @return validationMessages
     */
    public List getValidationMessages() {
        return validationMessages;
    }


    /**
     * Sets the validationMessages.
     * 
     * @param validationMessages
     */
    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }


    /**
     * Returns the requiredAdminName.
     * 
     * @return requiredAdminName
     */
    public boolean isRequiredAdminName() {
        return requiredAdminName;
    }


    /**
     * Sets the requiredAdminName.
     * 
     * @param requiredAdminName
     */
    public void setRequiredAdminName(boolean requiredAdminName) {
        this.requiredAdminName = requiredAdminName;
    }


    /**
     * Returns the requiredTerm
     * 
     * @return boolean requiredTerm.
     */
    public boolean isRequiredTerm() {
        return requiredTerm;
    }


    /**
     * Sets the requiredTerm
     * 
     * @param requiredTerm.
     */
    public void setRequiredTerm(boolean requiredTerm) {
        this.requiredTerm = requiredTerm;
    }


    /**
     * Returns the checkInOpted.
     * 
     * @return checkIn
     */
    public boolean isCheckInOpted() {
        return checkInOpted;
    }


    /**
     * Sets the checkInOpted.
     * 
     * @param checkInOpted
     */
    public void setCheckInOpted(boolean checkInOpted) {
        this.checkInOpted = checkInOpted;
    }


    /**
     * Returns the dummyVariable
     * 
     * @return String dummyVariable.
     */
    public String getDummyVariable() {
        return dummyVariable;
    }


    /**
     * Sets the dummyVariable
     * 
     * @param dummyVariable.
     */
    public void setDummyVariable(String dummyVariable) {
        this.dummyVariable = dummyVariable;
    }
	/**
	 * @return Returns the breadCrumpText.
	 */
	public String getBreadCrumpText() {
		this.breadCrumpText = " Administration >> Administration Option ("+ this.adminName+")>> Print";
		return breadCrumpText;
	}
	/**
	 * @param breadCrumpText The breadCrumpText to set.
	 */
	public void setBreadCrumpText(String breadCrumpText) {
		this.breadCrumpText = breadCrumpText;
	}
	
	/**
	 * @return Returns the checkUnlockFlag.
	 */
	public boolean isCheckUnlockFlag() {
		return checkUnlockFlag;
	}
	/**
	 * @param checkUnlockFlag The checkUnlockFlag to set.
	 */
	public void setCheckUnlockFlag(boolean checkUnlockFlag) {
		this.checkUnlockFlag = checkUnlockFlag;
	}
	/**
	 * @return Returns the qualifierId.
	 */
	public String getQualifierId() {
		return qualifierId;
	}
	/**
	 * @param qualifierId The qualifierId to set.
	 */
	public void setQualifierId(String qualifierId) {
		this.qualifierId = qualifierId;
	}
	
	
	/**
	 * @return Returns the qualifierDesc.
	 */
	public String getQualifierDesc() {
		return qualifierDesc;
	}
	/**
	 * @param qualifierDesc The qualifierDesc to set.
	 */
	public void setQualifierDesc(String qualifierDesc) {
		this.qualifierDesc = qualifierDesc;
	}
	/**
	 * @return Returns the termDesc.
	 */
	public String getTermDesc() {
		return termDesc;
	}
	/**
	 * @param termDesc The termDesc to set.
	 */
	public void setTermDesc(String termDesc) {
		this.termDesc = termDesc;
	}
	/**
	 * @return Returns the termId.
	 */
	public String getTermId() {
		return termId;
	}
	/**
	 * @param termId The termId to set.
	 */
	public void setTermId(String termId) {
		this.termId = termId;
	}
}