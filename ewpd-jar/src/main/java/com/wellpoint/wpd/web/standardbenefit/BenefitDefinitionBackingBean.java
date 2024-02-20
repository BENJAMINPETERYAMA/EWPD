/*
 * Created on Feb 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitDefinitionBO;
import com.wellpoint.wpd.common.standardbenefit.request.BenefitDefinitionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.CreateBenefitAdministrationRequest;
import com.wellpoint.wpd.common.standardbenefit.request.CreateBenefitTierDefinitionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.DeleteBenefitDefinitionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateBenefitDefinitionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RetrieveBenefitDefinitionRequest;
import com.wellpoint.wpd.common.standardbenefit.request.TierLookUpRequest;
import com.wellpoint.wpd.common.standardbenefit.response.BenefitDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.CreateBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.response.CreateBenefitTierDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteBenefitDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateBenefitDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveBenefitDefinitionResponse;
import com.wellpoint.wpd.common.standardbenefit.response.TierLookUpResponse;
import com.wellpoint.wpd.common.standardbenefit.vo.BenefitAdministrationVO;
import com.wellpoint.wpd.common.standardbenefit.vo.BenefitDefinitionVO;
import com.wellpoint.wpd.common.standardbenefit.vo.BenefitTierDefinitionVO;
import com.wellpoint.wpd.common.util.DateUtil;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u13274
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class BenefitDefinitionBackingBean extends WPDBackingBean {

    private String effectiveDate = null;

    private String expiryDate = null;

    private String description = "";

    private int benefitMasterSystemId;

    private boolean requiredEffectiveDate;

    private boolean requiredExpiryDate;

    private boolean requiredDescription;

    private String state = WebConstants.STATE;

    private String status = WebConstants.STATUS_BUILDING;

    private int version = WebConstants.VERSION;

    private List associatedBenefitDefinitionsList = null;

    private int benefitDefinitionMasterKey;

    private int masterKeyUsedForUpdate = -1;

    ArrayList validationMessages = null;

    private String STANDARD_BENEFIT_SESSION_KEY = WebConstants.STANDARD_BENEFIT_SESSION_KEY;

    private int masterVersion;

    private boolean saveAndAddFlag = false;
    
    private int listSize;
    
    private String benefitDefenitionsForDelete;
    
    private boolean isDataPresent = false;
    
    private int benefitAdminId = -1;
    
    private List tierLookUpList;
    
    private String tier;
    
    private List tierCodeList;


    /*
     * Constructor
     */
    public BenefitDefinitionBackingBean() {
        Date date = DateUtil.getDefaultEndDate();
        String expDate = WPDStringUtil.getStringDate(date);
        this.setExpiryDate(expDate);
        this.setEffectiveDate(WebConstants.DATE_1900);
    }


    /**
     * This method gets the associatedBenefitDefinitionsList using
     * LocateBenefitDefinitionRequest
     */
    public List getAssociatedBenefitDefinitionsList() {
    	if(!isDataPresent){
            associatedBenefitDefinitionsList = new ArrayList();

            LocateBenefitDefinitionRequest locateBenefitDefinitionRequest = 
                (LocateBenefitDefinitionRequest) this
                    .getServiceRequest(ServiceManager.LOCATE_BENEFIT_DEFINITION);
            if (null != this.getStandardBenefitSessionObject()
                    .getStandardBenefitMode()
                    && (WebConstants.BENEFIT_VIEW).equals(this
                            .getStandardBenefitSessionObject()
                            .getStandardBenefitMode())) {
            	locateBenefitDefinitionRequest.setFlag(true);
            }else{
            	locateBenefitDefinitionRequest.setFlag(false);
            }
            locateBenefitDefinitionRequest
                    .setBenefitMasterSystemId(getStandardBenefitSessionObject()
                            .getStandardBenefitKey());
            locateBenefitDefinitionRequest
                    .setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
            LocateBenefitDefinitionResponse locateBenefitDefinitionResponse = 
                (LocateBenefitDefinitionResponse) executeService(locateBenefitDefinitionRequest);
            this.setState(getStandardBenefitSessionObject()
                    .getStandardBenefitState());
            this.setVersion(getStandardBenefitSessionObject()
                    .getStandardBenefitVersionNumber());
            this.setStatus(getStandardBenefitSessionObject()
                    .getStandardBenefitStatus());
            //change
            if (null != this.getStandardBenefitSessionObject()
                    .getStandardBenefitMode()
                    && (WebConstants.BENEFIT_VIEW).equals(this
                            .getStandardBenefitSessionObject()
                            .getStandardBenefitMode())) {
                this.setBreadCrumbText(WebConstants.HEADER
                        + " ("
                        + this.getStandardBenefitSessionObject()
                                .getStandardBenefitName() + ") >> View");
            } else
                this.setBreadCrumbText(WebConstants.HEADER
                        + " ("
                        + this.getStandardBenefitSessionObject()
                                .getStandardBenefitName() + ") >> Edit");
            //end
            if(null != locateBenefitDefinitionResponse.getBenefitDefinitionList())
            	this.setListSize(locateBenefitDefinitionResponse.getBenefitDefinitionList().size());
            this.setAssociatedBenefitDefinitionsList(locateBenefitDefinitionResponse
                    .getBenefitDefinitionList());
            this.isDataPresent = true;
    	}
        return associatedBenefitDefinitionsList;
    } 

 
    /**
     * For Standard Definition save Method called from
     * benefitDefinitionCreate.jsp The method creates a request
     * BenefitDefinitionRequest and sets all the values required
     */
    public String saveBenefitDefinition() {
        validationMessages = new ArrayList(1);

        if (!validateRequiredFileds()) {
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if (!validateDateFormat()) {
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if (!validateEffectiveDate(this.getEffectiveDate())) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.EFFECTIVE_DATE_INVALID));
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if (this.dateComparison(this.getEffectiveDate(), this.getExpiryDate())) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.EXPIRY_GREATER_EFFECTIVE_DATE));
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if (!validateDescriptionLength()) {
            addAllMessagesToRequest(validationMessages);
            return "";
        }

        BenefitDefinitionRequest benefitDefinitionRequest = getBenefitDefinitionRequest();
        BenefitDefinitionResponse benefitDefinitionResponse = (BenefitDefinitionResponse) 
        		executeService(benefitDefinitionRequest);
        if (benefitDefinitionResponse != null
                && !benefitDefinitionResponse.isErrorMessageInList()) {
            this.setMasterKeyUsedForUpdate(benefitDefinitionResponse
                    .getBenefitDefinitionBO().getBenefitDefinitionMasterKey());
           // resetBenefitDefinitionObject();
        } else if (benefitDefinitionResponse != null
                && benefitDefinitionResponse.isErrorMessageInList()) {
            this.setRequiredEffectiveDate(true);
            this.setRequiredExpiryDate(true);
        }
        return "";
    }


    /**
     * For Standard Definition save Method called from
     * benefitDefinitionCreate.jsp The method creates a request
     * BenefitDefinitionRequest and sets all the values required
     */
    public String saveAndAddBenefitDefinition() {

        this.setSaveAndAddFlag(true);
        validationMessages = new ArrayList(1);

        if (!validateRequiredFileds()) {
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if (!validateDateFormat()) {
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if (!validateEffectiveDate(this.getEffectiveDate())) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.EFFECTIVE_DATE_INVALID));
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if (this.dateComparison(this.getEffectiveDate(), this.getExpiryDate())) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.EXPIRY_GREATER_EFFECTIVE_DATE));
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if (!validateDescriptionLength()) {
            addAllMessagesToRequest(validationMessages);
            return "";
        }

        BenefitDefinitionRequest benefitDefinitionRequest = getBenefitDefinitionRequest();
        BenefitDefinitionResponse benefitDefinitionResponse = (BenefitDefinitionResponse)
        		executeService(benefitDefinitionRequest);
        if (benefitDefinitionResponse != null
                && !benefitDefinitionResponse.isErrorMessageInList()) {
            resetBenefitDefinitionObject();
            saveAndAddBenefitAdministration(benefitDefinitionResponse.getBenefitDefinitionBO());
            saveBenefitTierDefinition(benefitDefinitionResponse.getBenefitDefinitionBO());
            addAllMessagesToRequest(benefitDefinitionResponse.getMessages());
        } else if (benefitDefinitionResponse != null 
                && benefitDefinitionResponse.isErrorMessageInList()) {
            this.setRequiredEffectiveDate(true);
            this.setRequiredExpiryDate(true);
        }
        getRequest().setAttribute("RETAIN_Value", "");
        this.isDataPresent = false;
        setTier("");
        return "";
    }

    /*
     * This method gets the data for the benefit administration and saves it.
     */
    public String saveAndAddBenefitAdministration(BenefitDefinitionBO benefitDefinitionBO) {
        this.setSaveAndAddFlag(true);
        validationMessages = new ArrayList(1);

        if (!validateRequiredFileds()) {
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if (!validateDateFormat()) {
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if (this.dateComparison(this.getEffectiveDate(), this.getExpiryDate())) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.EXPIRY_GREATER_EFFECTIVE_DATE));
            addAllMessagesToRequest(validationMessages);
            return "";
        }
        if(null!=this.description&&!"".equals(this.description)){
	        if (!validateDescriptionLength()) {
	            addAllMessagesToRequest(validationMessages);
	            return "";
	        }
        }

        CreateBenefitAdministrationRequest createBenefitAdministrationRequest = getCreateBenefitAdministrationRequest(benefitDefinitionBO);
        CreateBenefitAdministrationResponse createBenefitAdministrationResponse = (CreateBenefitAdministrationResponse) executeService(createBenefitAdministrationRequest);

        if (null != createBenefitAdministrationResponse) {
            if (null != createBenefitAdministrationResponse
                    .getBenefitAdministrationBO()) {
                /*this.benefitAdministrationKey = createBenefitAdministrationResponse
                        .getBenefitAdministrationBO()
                        .getBenefitAdministrationKey();*/
                resetBenefitAdministrationObject();
            }
            getRequest().setAttribute("RETAIN_Value", "");
            return WebConstants.BENEFIT_ADMINISTRATION;
        }
        getRequest().setAttribute("RETAIN_Value", "");
        return "";
    }
    
    

    /*
     * This method clears all the entered data so as to enter the fresh data
     */
    private void resetBenefitAdministrationObject() {
        this.setEffectiveDate(WebConstants.DATE_1900);
        Date date = DateUtil.getDefaultEndDate();
        String expDate = WPDStringUtil.getStringDate(date);
        this.setExpiryDate(expDate);
        this.setDescription("");
        /*this.setBenefitAdministrationKey(-1);*/
    }

    /*
     * This method is used for creating the CreateBenefitAdministrationRequest
     * by getting the values that are entered throught the jsp page
     */
    private CreateBenefitAdministrationRequest getCreateBenefitAdministrationRequest(BenefitDefinitionBO benefitDefinitionBO) {
        int benefitDefKey = 0;
        CreateBenefitAdministrationRequest createBenefitAdministrationRequest = (CreateBenefitAdministrationRequest) this
                .getServiceRequest(ServiceManager.CREATE_BENEFIT_ADMINISTRATION);
        BenefitAdministrationVO benefitAdministrationVO = new BenefitAdministrationVO();
        
        benefitAdministrationVO.setBenefitDefinitionKey(benefitDefinitionBO.getBenefitDefinitionMasterKey());
        if (this.isSaveAndAddFlag()) {
          if (this.getBenefitAdminId() == -1) {
                benefitAdministrationVO.setBenefitAdministrationKey(-1);
            } else {
                benefitAdministrationVO
                        .setBenefitAdministrationKey(benefitDefinitionBO.getBenefitAdminId());
            }
        } else{
            benefitAdministrationVO
                    .setBenefitAdministrationKey(benefitDefinitionBO.getBenefitAdminId());
        	this.setBenefitAdminId(-1);
        }
        this.setDescription(benefitDefinitionBO.getDescription());
        benefitAdministrationVO.setDescription(this.getDescription());
        benefitAdministrationVO.setEffectiveDate(benefitDefinitionBO.getEffectiveDate());
        benefitAdministrationVO.setExpiryDate(benefitDefinitionBO.getExpiryDate());

        benefitAdministrationVO
                .setStandardBenefitKey(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        benefitAdministrationVO
                .setStandardBenefitName(getStandardBenefitSessionObject()
                        .getStandardBenefitName());
        benefitAdministrationVO
                .setStandardBenefitStatus(getStandardBenefitSessionObject()
                        .getStandardBenefitStatus());
        //changes
        benefitAdministrationVO
                .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                        .getStandardBenefitParentKey());
        benefitAdministrationVO
                .setBusinessDomains(getStandardBenefitSessionObject()
                        .getBusinessDomains());
        benefitAdministrationVO
                .setStandardBenefitVersion(getStandardBenefitSessionObject()
                        .getStandardBenefitVersionNumber());
        // cahnges
        createBenefitAdministrationRequest
                .setBenefitAdministrationVO(benefitAdministrationVO);
        return createBenefitAdministrationRequest;
    }

    /**
     * Validation function to check whether date entering is a valid one or not
     *  
     */
    public boolean validateDateFormat() {
        validationMessages = new ArrayList(1);
        boolean requiredDate = false;
        
        if (!effectiveDate.trim().equals("") && !WPDStringUtil.isValidDate(this.effectiveDate)) {
            requiredDate = true;
            ErrorMessage errorMessage = new ErrorMessage(
                    WebConstants.INPUT_FORMAT_INVALID);
            errorMessage
                    .setParameters(new String[] { WebConstants.EFFECTIVE_DATE });
            validationMessages.add(errorMessage);
        }
        else if (!expiryDate.trim().equals("") && !WPDStringUtil.isValidDate(this.expiryDate)) {
            requiredDate = true;
            ErrorMessage errorMessage = new ErrorMessage(
                    WebConstants.INPUT_FORMAT_INVALID);
            errorMessage
                    .setParameters(new String[] { WebConstants.EXPIRY_DATE });
            validationMessages.add(errorMessage);
        }

        if (requiredDate) {
            return false;
        }
        return true;
    }


    /**
     * Validation function to check whether All Mandatory fields are being
     * entered
     *  
     */
    public boolean validateRequiredFileds() {
        validationMessages = new ArrayList(1);
        boolean requiredField = true;
        if ("".equals(this.effectiveDate) || null == this.effectiveDate) {
            this.setRequiredEffectiveDate(true);
            requiredField = false;
        }
        if ("".equals(this.expiryDate) || null == this.expiryDate) {
            this.setRequiredExpiryDate(true);
            requiredField = false;
        }
//        if ("".equals(this.description) || null == this.description) {
//            this.setRequiredDescription(true);
//            requiredField = false;
//        }
        if (!requiredField) {
            validationMessages.add(new ErrorMessage(WebConstants.INVALID_DATA));
        }
        return requiredField;
    }


    /**
     * Validation function for description field
     *  
     */
  public boolean validateDescriptionLength() {
        validationMessages = new ArrayList(1);
         if(null!=this.description&&!(this.description.equals("")))
        {
	        int descLength = this.description.length();
	        //modified on 18th Dec 2007 to check if description field is blank or not 
	        if(!("".equals(this.description)) && null != this.description){
	            if (descLength < 10 || descLength > 250) {
	                  validationMessages.add(new ErrorMessage(WebConstants.DESCRIPTION_SIZE_10_250));
	                  return false;
	              }
	         //modfication ends
	         }
        }
        return true;
    }


    /**
     * Validation function for expiry date
     *  
     */
    public boolean validateEffectiveDate(String effectiveDate) {
        validationMessages = new ArrayList(1);
        Date effectiveDateForCheck = WPDStringUtil
                .getDateFromString(effectiveDate);
        Date boundaryDate = WPDStringUtil
                .getDateFromString(WebConstants.DATE_1900);
        if (effectiveDateForCheck.compareTo(boundaryDate) < 0)
            return false;
        return true;
    }


    /**
     * Validation function for checking whether expiry date is greater than
     * effective date
     * 
     * @param effectiveDate
     * @param expiryDate
     * @return boolean
     */
    public boolean dateComparison(String effectiveDate, String expiryDate) {
        Date effectiveDateForComparison = WPDStringUtil
                .getDateFromString(effectiveDate);
        Date expiryDateForComparison = WPDStringUtil
                .getDateFromString(expiryDate);
        if (effectiveDateForComparison.compareTo(expiryDateForComparison) >= 0) {
            return true;
        }
        return false;

    }


    /**
     * Returns BenefitDefinition Request object setting VO to the request.
     * 
     * @return benefitDefinitionRequest
     */
    private BenefitDefinitionRequest getBenefitDefinitionRequest() {
        BenefitDefinitionRequest benefitDefinitionRequest = (BenefitDefinitionRequest) this
                .getServiceRequest(ServiceManager.CREATE_BENEFIT_DEFINITION);
        BenefitDefinitionVO benefitDefinitionVO = new BenefitDefinitionVO();
        this.setDescription(this.getDescription().trim().toUpperCase());
        
        String desc = this.getDescription().trim();
        if(desc.equals(WebConstants.EMPTY_STRING)){
        	desc = " ";
        }
        benefitDefinitionVO.setDescription(desc);        
//        benefitDefinitionVO.setDescription(this.getDescription().trim());
        benefitDefinitionVO.setEffectiveDate(WPDStringUtil
                .getDateFromString(this.effectiveDate));
        benefitDefinitionVO.setExpiryDate(WPDStringUtil.getDateFromString(this
                .getExpiryDate()));
        
        benefitDefinitionVO.setTierDefinitionAndId(this.getTier());
        
        
        
        
        if (this.isSaveAndAddFlag()) {
            if (this.getMasterKeyUsedForUpdate() == -1) {
                benefitDefinitionVO.setMasterKeyUsedForUpdate(-1);
            } else {
                benefitDefinitionVO
                        .setMasterKeyUsedForUpdate(this.masterKeyUsedForUpdate);
            }
        } else{
            benefitDefinitionVO
                    .setMasterKeyUsedForUpdate(this.masterKeyUsedForUpdate);
        	this.setMasterKeyUsedForUpdate(-1);
        }
        //this.setMasterKeyUsedForUpdate(-1);
        benefitDefinitionVO
                .setBenefitMasterSystemId(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        benefitDefinitionVO.setMasterVersion(getStandardBenefitSessionObject()
                .getStandardBenefitVersionNumber());
        benefitDefinitionVO
                .setBenefitIdentifier(getStandardBenefitSessionObject()
                        .getStandardBenefitName());
        benefitDefinitionVO
                .setBusinessDomains(getStandardBenefitSessionObject()
                        .getBusinessDomains());
        benefitDefinitionVO
                .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                        .getStandardBenefitParentKey());
        benefitDefinitionVO
                .setStandardBenefitStatus(getStandardBenefitSessionObject()
                        .getStandardBenefitStatus());

        benefitDefinitionRequest.setBenefitDefinitionVO(benefitDefinitionVO);

        return benefitDefinitionRequest;
    }


    /**
     * This method for retrieving the selected Standard Definiton Details for
     * the selected row in the datatable and displaying that in the page for
     * editing it.
     * 
     * @return
     */
    public String retrieveBenefitDefinition() {

        RetrieveBenefitDefinitionRequest retrieveBenefitDefinitionRequest = 
            (RetrieveBenefitDefinitionRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_BENEFIT_DEFINITION);
        retrieveBenefitDefinitionRequest
                .setBenefitDefinitionMasterKey(this.benefitDefinitionMasterKey);
        retrieveBenefitDefinitionRequest
                .setBenefitMasterSystemId(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        retrieveBenefitDefinitionRequest
                .setMainObjectVersion(getStandardBenefitSessionObject()
                        .getStandardBenefitVersionNumber());
        retrieveBenefitDefinitionRequest
                .setBenefitIdentifier(getStandardBenefitSessionObject()
                        .getStandardBenefitName());
        retrieveBenefitDefinitionRequest
                .setBusinessDomains(getStandardBenefitSessionObject()
                        .getBusinessDomains());
        retrieveBenefitDefinitionRequest
                .setStandardBenefitStatus(getStandardBenefitSessionObject()
                        .getStandardBenefitStatus());
        retrieveBenefitDefinitionRequest
                .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                        .getStandardBenefitParentKey());

        RetrieveBenefitDefinitionResponse benefitDefinitionResponse = 
            (RetrieveBenefitDefinitionResponse) executeService(retrieveBenefitDefinitionRequest);

        this.setMasterKeyUsedForUpdate(benefitDefinitionResponse
                .getBenefitDefinitionBO().getBenefitDefinitionMasterKey());
        this.setDescription(benefitDefinitionResponse.getBenefitDefinitionBO()
                .getDescription());
        this.setEffectiveDate(WPDStringUtil
                .getStringDate(benefitDefinitionResponse
                        .getBenefitDefinitionBO().getEffectiveDate()));
        this.setExpiryDate(WPDStringUtil
                .getStringDate(benefitDefinitionResponse
                        .getBenefitDefinitionBO().getExpiryDate()));
        this.setTier(benefitDefinitionResponse
                        .getBenefitDefinitionBO().getTierDefinitions());
        this.setBenefitAdminId(benefitDefinitionResponse.getBenefitDefinitionBO().getBenefitAdminId());
        getRequest().setAttribute("RETAIN_Value", "");
        return WebConstants.LOAD_BENEFIT_DEFINITION;
    }


    /**
     * This method is for deleting the selected Standard Definiton Details .
     * 
     * @return
     */
    public String deleteBenefitDefinition() {

        DeleteBenefitDefinitionRequest deleteBenefitDefinitionRequest = (DeleteBenefitDefinitionRequest) this
                .getServiceRequest(ServiceManager.DELETE_BENEFIT_DEFINITION);
        /*deleteBenefitDefinitionRequest
                .setBenefitDefinitionMasterKey(this.benefitDefinitionMasterKey);*/
        deleteBenefitDefinitionRequest
				.setBenefitDefenitionKeys(this.getBenefitDefenitionsForDelete());
        deleteBenefitDefinitionRequest
                .setStandardBenefitMasterKey(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        deleteBenefitDefinitionRequest
                .setVersion(getStandardBenefitSessionObject()
                        .getStandardBenefitVersionNumber());
        deleteBenefitDefinitionRequest
                .setBenefitIdentifier(getStandardBenefitSessionObject()
                        .getStandardBenefitName());
        deleteBenefitDefinitionRequest
                .setBusinessDomains(getStandardBenefitSessionObject()
                        .getBusinessDomains());
        deleteBenefitDefinitionRequest
                .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                        .getStandardBenefitParentKey());
        deleteBenefitDefinitionRequest
                .setStandardBenefitStatus(getStandardBenefitSessionObject()
                        .getStandardBenefitStatus());

        DeleteBenefitDefinitionResponse benefitDefinitionResponse = (DeleteBenefitDefinitionResponse) executeService(deleteBenefitDefinitionRequest);
        this.resetBenefitDefinitionObject();
        getRequest().setAttribute("RETAIN_Value", "");
        this.isDataPresent = false;
        return "";
    }


    /*
     * This method is for clearing the rows so as to enter new data.
     *  
     */
    private void resetBenefitDefinitionObject() {
        this.setEffectiveDate(WebConstants.DATE_1900);
        this.setExpiryDate(WebConstants.DEFAULT_EXP_DATE);
        this.setDescription("");
        this.setMasterKeyUsedForUpdate(-1);
    }


    /*
     * This method is for loading the benefitdefn details for edit
     *  
     */
    public String loadBenefitDefinition() {
        this.setState(getStandardBenefitSessionObject()
                .getStandardBenefitState());
        this.setVersion(getStandardBenefitSessionObject()
                .getStandardBenefitVersionNumber());
        this.setStatus(getStandardBenefitSessionObject()
                .getStandardBenefitStatus());
        this.setBenefitMasterSystemId(getStandardBenefitSessionObject()
                .getStandardBenefitKey());
        this.setBreadCrumbText(WebConstants.HEADER
                + " ("
                + this.getStandardBenefitSessionObject()
                        .getStandardBenefitName() + ") >> Edit");
        return WebConstants.LOAD_BENEFIT_DEFINITION;
    }


    /*
     * This method is for loading the benefitdefn details for view @return
     */
    public String loadBenefitDefinitionView() {
        this.setBenefitMasterSystemId(getStandardBenefitSessionObject()
                .getStandardBenefitKey());
        this.setBreadCrumbText(WebConstants.HEADER
                + " ("
                + this.getStandardBenefitSessionObject()
                        .getStandardBenefitName() + ") >> View");
        return WebConstants.LOAD_BENEFIT_DEFINITION_VIEW;
    }


    /*
     * This method is getting the StandardBenefitSessionObject @return
     */
    protected StandardBenefitSessionObject getStandardBenefitSessionObject() {
        StandardBenefitSessionObject standardBenefitSessionObject = (StandardBenefitSessionObject) getSession()
                .getAttribute(STANDARD_BENEFIT_SESSION_KEY);

        if (standardBenefitSessionObject == null) {
            standardBenefitSessionObject = new StandardBenefitSessionObject();
            getSession().setAttribute(STANDARD_BENEFIT_SESSION_KEY,
                    standardBenefitSessionObject);
        }
        return standardBenefitSessionObject;
    }


    /**
     * Returns the description
     * 
     * @return String description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * Sets the description
     * 
     * @param description.
     */
    public void setDescription(String description) {
        this.description = description.trim();
    }


    /**
     * Returns the effectiveDate
     * 
     * @return String effectiveDate.
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * Sets the effectiveDate
     * 
     * @param effectiveDate.
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate.trim();
    }


    /**
     * Returns the expiryDate
     * 
     * @return String expiryDate.
     */
    public String getExpiryDate() {
        return expiryDate;
    }


    /**
     * Sets the expiryDate
     * 
     * @param expiryDate.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate.trim();
    }


    /**
     * Returns the requiredDescription
     * 
     * @return boolean requiredDescription.
     */
    public boolean isRequiredDescription() {
        return requiredDescription;
    }


    /**
     * Sets the requiredDescription
     * 
     * @param requiredDescription.
     */
    public void setRequiredDescription(boolean requiredDescription) {
        this.requiredDescription = requiredDescription;
    }


    /**
     * Returns the requiredEffectiveDate
     * 
     * @return boolean requiredEffectiveDate.
     */
    public boolean isRequiredEffectiveDate() {
        return requiredEffectiveDate;
    }


    /**
     * Sets the requiredEffectiveDate
     * 
     * @param requiredEffectiveDate.
     */
    public void setRequiredEffectiveDate(boolean requiredEffectiveDate) {
        this.requiredEffectiveDate = requiredEffectiveDate;
    }


    /**
     * Returns the requiredExpiryDate
     * 
     * @return boolean requiredExpiryDate.
     */
    public boolean isRequiredExpiryDate() {
        return requiredExpiryDate;
    }


    /**
     * Sets the requiredExpiryDate
     * 
     * @param requiredExpiryDate.
     */
    public void setRequiredExpiryDate(boolean requiredExpiryDate) {
        this.requiredExpiryDate = requiredExpiryDate;
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
     * Returns the validationMessages
     * 
     * @return ArrayList validationMessages.
     */
    public ArrayList getValidationMessages() {
        return validationMessages;
    }


    /**
     * Sets the validationMessages
     * 
     * @param validationMessages.
     */
    public void setValidationMessages(ArrayList validationMessages) {
        this.validationMessages = validationMessages;
    }


    /**
     * Returns the version
     * 
     * @return String version.
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
     * Sets the associatedBenefitDefinitionsList
     * 
     * @param associatedBenefitDefinitionsList.
     */
    public void setAssociatedBenefitDefinitionsList(
            List associatedBenefitDefinitionsList) {
        this.associatedBenefitDefinitionsList = associatedBenefitDefinitionsList;
    }


    /**
     * Returns the benefitMasterSystemId
     * 
     * @return int benefitMasterSystemId.
     */
    public int getBenefitMasterSystemId() {
        return benefitMasterSystemId;
    }


    /**
     * Sets the benefitMasterSystemId
     * 
     * @param benefitMasterSystemId.
     */
    public void setBenefitMasterSystemId(int benefitMasterSystemId) {
        this.benefitMasterSystemId = benefitMasterSystemId;
    }


    /**
     * Returns the benefitDefinitionMasterKey
     * 
     * @return int benefitDefinitionMasterKey.
     */
    public int getBenefitDefinitionMasterKey() {
        return benefitDefinitionMasterKey;
    }


    /**
     * Sets the benefitDefinitionMasterKey
     * 
     * @param benefitDefinitionMasterKey.
     */
    public void setBenefitDefinitionMasterKey(int benefitDefinitionMasterKey) {
        this.benefitDefinitionMasterKey = benefitDefinitionMasterKey;
    }


    /**
     * Returns the masterKeyUsedForUpdate
     * 
     * @return int masterKeyUsedForUpdate.
     */
    public int getMasterKeyUsedForUpdate() {
        return masterKeyUsedForUpdate;
    }


    /**
     * Sets the masterKeyUsedForUpdate
     * 
     * @param masterKeyUsedForUpdate.
     */
    public void setMasterKeyUsedForUpdate(int masterKeyUsedForUpdate) {
        this.masterKeyUsedForUpdate = masterKeyUsedForUpdate;
    }


    /**
     * Returns the masterVersion
     * 
     * @return int masterVersion.
     */
    public int getMasterVersion() {
        return masterVersion;
    }


    /**
     * Sets the masterVersion
     * 
     * @param masterVersion.
     */
    public void setMasterVersion(int masterVersion) {
        this.masterVersion = masterVersion;
    }


    /**
     * Returns the saveAndAddFlag
     * 
     * @return boolean saveAndAddFlag.
     */
    public boolean isSaveAndAddFlag() {
        return saveAndAddFlag;
    }


    /**
     * Sets the saveAndAddFlag
     * 
     * @param saveAndAddFlag.
     */
    public void setSaveAndAddFlag(boolean saveAndAddFlag) {
        this.saveAndAddFlag = saveAndAddFlag;
    }
    /**
     * Returns the listSize
     * @return int listSize.
     */

    public int getListSize() {
        return listSize;
    }
    /**
     * Sets the listSize
     * @param listSize.
     */

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }
	/**
	 * @return Returns the benefitDefenitionsForDelete.
	 */
	public String getBenefitDefenitionsForDelete() {
		return benefitDefenitionsForDelete;
	}
	/**
	 * @param benefitDefenitionsForDelete The benefitDefenitionsForDelete to set.
	 */
	public void setBenefitDefenitionsForDelete(
			String benefitDefenitionsForDelete) {
		this.benefitDefenitionsForDelete = benefitDefenitionsForDelete;
	}
	/**
	 * @return Returns the isDataPresent.
	 */
	public boolean isDataPresent() {
		return isDataPresent;
	}
	/**
	 * @param isDataPresent The isDataPresent to set.
	 */
	public void setDataPresent(boolean isDataPresent) {
		this.isDataPresent = isDataPresent;
	}
	/**
	 * @return Returns the benefitAdminId.
	 */
	public int getBenefitAdminId() {
		return benefitAdminId;
	}
	/**
	 * @param benefitAdminId The benefitAdminId to set.
	 */
	public void setBenefitAdminId(int benefitAdminId) {
		this.benefitAdminId = benefitAdminId;
	}
	
	
	/**
	 * The method retrieves the tier definitions from the TIER_DEFN table
	 * to be displayed in the Tier popup.
	 * @return
	 */
	public String getTierLookupRecords() {		
	    TierLookUpRequest tierLookupRequest = (TierLookUpRequest)
					this.getServiceRequest(ServiceManager.TIER_LOOKUP_REQUEST);	
	    tierLookupRequest.setAction("benefit");
		TierLookUpResponse tierLookupResponse = (TierLookUpResponse) this
				.executeService(tierLookupRequest);

		this.tierLookUpList = tierLookupResponse
				.getTierList();			
		return WebConstants.EMPTY_STRING;
	}
    /**
     * @return Returns the tierLookUpList.
     */
    public List getTierLookUpList() {
        return tierLookUpList;
    } 
    /**
     * @param tierLookUpList The tierLookUpList to set.
     */
    public void setTierLookUpList(List tierLookUpList) {
        this.tierLookUpList = tierLookUpList;
    }

    /**
     * @return Returns the tier.
     */
    public String getTier() {
        return tier;
    }
    /**
     * @param tier The tier to set.
     */
    public void setTier(String tier) {
        this.tier = tier;
    }
    
    /**
     * @return Returns the tierCodeList.
     */
    public List getTierCodeList() {
        return tierCodeList;
    }
    /**
     * @param tierCodeList The tierCodeList to set.
     */
    public void setTierCodeList(List tierCodeList) {
        this.tierCodeList = tierCodeList;
    }
    
 
    /**
     * The method saves the Tier for the Benfit Definition in the 
     * BNFT_TIER_DEFN_ASSN table
     * @param benefitDefinitionBO
     * @return
     */
    public String saveBenefitTierDefinition(BenefitDefinitionBO 
            benefitDefinitionBO){               
        this.tierCodeList = WPDStringUtil.getListFromTildaString(
                this.getTier(), 2, 2, 2);        
        benefitDefinitionBO.setTierDefinitionIdList(convertStringToIntList
                (tierCodeList));    
        CreateBenefitTierDefinitionRequest createBenefitTierDefinitionRequest = 
            getCreateBenefitTierDefnRequest(benefitDefinitionBO);             
        CreateBenefitTierDefinitionResponse createBenefitTierDefinitionResponse = 
            (CreateBenefitTierDefinitionResponse) executeService
            	(createBenefitTierDefinitionRequest);       
        getRequest().setAttribute("RETAIN_Value", "");
        return WebConstants.EMPTY_STRING;
    }
    

    /**
     * The method creates a CreateBenefitTierDefinitionRequest and sets 
     * the necessary parameters in it.
     * @param benefitDefinitionBO
     * @return
     */
    private CreateBenefitTierDefinitionRequest getCreateBenefitTierDefnRequest
    (BenefitDefinitionBO benefitDefinitionBO) {       
        CreateBenefitTierDefinitionRequest createBenefitTierDefinitionRequest = 
            (CreateBenefitTierDefinitionRequest) this
                .getServiceRequest(ServiceManager.CREATE_BENEFIT_TIERDEFN_ASSN_REQUEST);
        BenefitTierDefinitionVO benefitTierDefinitionVO = new BenefitTierDefinitionVO();
        benefitTierDefinitionVO.setTierDefinitionsList(benefitDefinitionBO
                .getTierDefinitionIdList());
        benefitTierDefinitionVO.setBenefitDefinitionId(benefitDefinitionBO
                .getBenefitDefinitionMasterKey());
        benefitTierDefinitionVO.setBenefitName(getStandardBenefitSessionObject()
                        .getStandardBenefitName());
        benefitTierDefinitionVO.setBusinessDomains(getStandardBenefitSessionObject()
                .getBusinessDomains());
        benefitTierDefinitionVO.setBenefitVersion(getStandardBenefitSessionObject()
                .getStandardBenefitVersionNumber());
        createBenefitTierDefinitionRequest.setBenefitTierDefinitionVO(benefitTierDefinitionVO);        
        return createBenefitTierDefinitionRequest;
    }
    
    /**
     * The method converts a List containing String objects 
     * to a List of Integers.
     * @param stringList
     * @return
     */
    private List convertStringToIntList(List stringList){
        List intList = new ArrayList();
        for(int i=0;i<stringList.size();i++){
           String strValue = (String) stringList.get(i);
           int intValue = Integer.parseInt(strValue);
           intList.add(new Integer(intValue));
        }
        return intList;
    }  
}