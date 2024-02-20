/*
 * Created on Feb 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitAdministrationBO;
import com.wellpoint.wpd.common.standardbenefit.request.CreateBenefitAdministrationRequest;
import com.wellpoint.wpd.common.standardbenefit.request.DeleteBenefitAdministrationRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateBenefitAdministrationRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RetrieveBenefitAdministrationRequest;
import com.wellpoint.wpd.common.standardbenefit.response.CreateBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveBenefitAdministrationResponse;
import com.wellpoint.wpd.common.standardbenefit.vo.BenefitAdministrationVO;
import com.wellpoint.wpd.common.util.DateUtil;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u13174
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class BenefitAdministrationBackingBean extends WPDBackingBean {

    private String effectiveDate = null;

    private String expiryDate = null;

    private String description = null;

    private boolean requiredEffectiveDate = false;

    private boolean requiredExpiryDate = false;

    private boolean requiredDescription = false;

    private boolean renderFlag = false;

    private int benefitAdministrationKey = -1;

    private int selectedBenefitAdministrationKey;

    HttpSession session = this.getHttpSession();

    ArrayList validationMessages = null;

    private List benefitAdministrationList = null;

    private boolean saveAndAddFlag = false;

    private String STANDARD_BENEFIT_SESSION_KEY = WebConstants.STANDARD;

    private String benefitAdminstrationsForDelete = null;
    
    private List benefitAdministrationListForViewandPrint = null;
    
    /*
     * Constructor
     */
    public BenefitAdministrationBackingBean() {
        super();
        Date date = DateUtil.getDefaultEndDate();
        String expDate = WPDStringUtil.getStringDate(date);
        this.setExpiryDate(expDate);
        this.setEffectiveDate(WebConstants.DATE_1900);
    }


    /*
     * This method gets the data for the benefit administration and saves it.
     */
    public String saveAndAddBenefitAdministration() {
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

        CreateBenefitAdministrationRequest createBenefitAdministrationRequest = getCreateBenefitAdministrationRequest();
        CreateBenefitAdministrationResponse createBenefitAdministrationResponse = (CreateBenefitAdministrationResponse) executeService(createBenefitAdministrationRequest);

        if (null != createBenefitAdministrationResponse) {
            if (null != createBenefitAdministrationResponse
                    .getBenefitAdministrationBO()) {
                this.benefitAdministrationKey = createBenefitAdministrationResponse
                        .getBenefitAdministrationBO()
                        .getBenefitAdministrationKey();
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
        this.setBenefitAdministrationKey(-1);
    }


    /*
     * This method validates the entered date format
     */
    public boolean validateDateFormat() {
        validationMessages = new ArrayList(1);
        boolean requiredDate = false;
        if (!(effectiveDate.trim().equals("")) && !StringUtil.isDate(effectiveDate)) {
            requiredDate = true;
            ErrorMessage errorMessage = new ErrorMessage(
                    WebConstants.INPUT_FORMAT_INVALID);
            errorMessage.setParameters(new String[] { "Effective date" });
            validationMessages.add(errorMessage);
        }

        else if (!(expiryDate.trim().equals("")) && !StringUtil.isDate(expiryDate)) {
            requiredDate = true;
            ErrorMessage errorMessage = new ErrorMessage(
                    WebConstants.INPUT_FORMAT_INVALID);
            errorMessage.setParameters(new String[] { "Expiry date" });
            validationMessages.add(errorMessage);
        }
        else if (!(effectiveDate.trim().equals("")) && !StringUtil.isDate(effectiveDate)) {
            Date effectiveDate = WPDStringUtil
                    .getDateFromString(this.effectiveDate);
            if (effectiveDate
                    .compareTo(WPDStringUtil.getDefaultEffectiveDate()) < 0) {
                requiredDate = true;
                validationMessages
                        .add(new ErrorMessage(
                                WebConstants.EFFECTIVEDATE_NOT_GRETER_THAN_DEFAULTDATE));

            }
        }

        if (requiredDate) {
            return false;
        }
        return true;
    }


    /*
     * This method is used for the validation of the entered data
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
       /* if ("".equals(this.description) || null == this.description) {
            this.setRequiredDescription(true);
            requiredField = false;
        }*/
        if (!requiredField) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
        }
        return requiredField;
    }


    /*
     * This method is used for the validation of the entered length field
     */
    public boolean validateDescriptionLength() {
        validationMessages = new ArrayList(1);
        int descLength = this.description.length();
        if (descLength < 10 || descLength > 250) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.INVALID_DESCRIPTION));
            return false;
        }
        return true;
    }


    /*
     * This method is used for the comparison of the entered effective and
     * expiry dates
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


    /*
     * This method is used for creating the CreateBenefitAdministrationRequest
     * by getting the values that are entered throught the jsp page
     */
    private CreateBenefitAdministrationRequest getCreateBenefitAdministrationRequest() {
        int benefitDefKey = 0;
        CreateBenefitAdministrationRequest createBenefitAdministrationRequest = (CreateBenefitAdministrationRequest) this
                .getServiceRequest(ServiceManager.CREATE_BENEFIT_ADMINISTRATION);
        BenefitAdministrationVO benefitAdministrationVO = new BenefitAdministrationVO();
        if (null != this.getHttpSession().getAttribute(
                WebConstants.SESSION_BNFT_DEFN_ID)) {
            benefitDefKey = Integer.parseInt((String) this.getHttpSession()
                    .getAttribute(WebConstants.SESSION_BNFT_DEFN_ID));
        }
        benefitAdministrationVO.setBenefitDefinitionKey(benefitDefKey);
        if (this.isSaveAndAddFlag()) {
            if (this.getBenefitAdministrationKey() == -1) {
                benefitAdministrationVO.setBenefitAdministrationKey(-1);
            } else {
                benefitAdministrationVO
                        .setBenefitAdministrationKey(this.benefitAdministrationKey);
            }
        } else
            benefitAdministrationVO
                    .setBenefitAdministrationKey(this.benefitAdministrationKey);
        //this.setBenefitAdministrationKey(-1);
        this.setDescription(this.getDescription().toUpperCase());
        benefitAdministrationVO.setDescription(this.getDescription());
        benefitAdministrationVO.setEffectiveDate(WPDStringUtil
                .getDateFromString(this.getEffectiveDate()));
        benefitAdministrationVO.setExpiryDate(WPDStringUtil
                .getDateFromString(this.getExpiryDate()));

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


    /*
     * This method is used to save the benefit adminstration by getting the
     * values that we entered through teh jsp page
     */
    public String saveBenefitAdministration() {
        renderFlag = false;
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
        if (!"".equals(this.description) || null != this.description){
	        if (!validateDescriptionLength()) {
	            addAllMessagesToRequest(validationMessages);
	            return "";
	        }
        }

        CreateBenefitAdministrationRequest createBenefitAdministrationRequest = getCreateBenefitAdministrationRequest();
        CreateBenefitAdministrationResponse createBenefitAdministrationResponse = (CreateBenefitAdministrationResponse) executeService(createBenefitAdministrationRequest);

        if (null != createBenefitAdministrationResponse) {
            if (null != createBenefitAdministrationResponse
                    .getBenefitAdministrationBO()) {
                this
                        .setBenefitAdministrationKey(createBenefitAdministrationResponse
                                .getBenefitAdministrationBO()
                                .getBenefitAdministrationKey());
                // resetBenefitAdministrationObject();

            }
            return WebConstants.BENEFIT_ADMINISTRATION;
        }

        return WebConstants.BENEFIT_ADMINISTRATION;
    }


    /*
     * This method is used to edit the benefit adminstration details by getting
     * the values that we entered through teh jsp page
     */
    public String editBenefitAdministration() {
        RetrieveBenefitAdministrationRequest retrieveBenefitAdministrationRequest = getRetrieveBenefitAdministrationRequest();
        RetrieveBenefitAdministrationResponse retrieveBenefitAdministrationResponse = (RetrieveBenefitAdministrationResponse) executeService(retrieveBenefitAdministrationRequest);
        if (null != retrieveBenefitAdministrationResponse) {
            BenefitAdministrationBO benefitAdministrationBO = retrieveBenefitAdministrationResponse
                    .getBenefitAdministrationBO();
            if (null != benefitAdministrationBO) {
                this.setBenefitAdministrationKey(benefitAdministrationBO
                        .getBenefitAdministrationKey());
                this.setEffectiveDate(WPDStringUtil
                        .getStringDate(benefitAdministrationBO
                                .getEffectiveDate()));
                this
                        .setExpiryDate(WPDStringUtil
                                .getStringDate(benefitAdministrationBO
                                        .getExpiryDate()));
                this.setDescription(benefitAdministrationBO.getDescription());
            }
        }
        return "";
    }


    /*
     * This method is used to delete the benefit adminstration
     */
    public String deleteBenefitAdministration() {
    	 if (null == this.effectiveDate || "".equals(this.effectiveDate) 
    	 		|| null == this.expiryDate || "".equals(this.expiryDate) 
					||null == this.description || "".equals(this.description) ) {
        	getRequest().setAttribute("RETAIN_Value", "");
        }
        DeleteBenefitAdministrationRequest deleteBenefitAdministrationRequest = getDeleteBenefitAdministrationRequest();
        DeleteBenefitAdministrationResponse deleteBenefitAdministrationResponse = (DeleteBenefitAdministrationResponse) executeService(deleteBenefitAdministrationRequest);
        this.resetBenefitAdministrationObject();
        
       
        //getRequest().setAttribute("RETAIN_Value", "");
        return "";
    }


    /*
     * This method is used to create the RetrieveBenefitAdministrationRequest by
     * getting the values that we entered through teh jsp page
     */
    public RetrieveBenefitAdministrationRequest getRetrieveBenefitAdministrationRequest() {
        int benefitDefKey = 0;
        RetrieveBenefitAdministrationRequest retrieveBenefitAdministrationRequest = (RetrieveBenefitAdministrationRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_BENEFIT_ADMINISTRATION);
        BenefitAdministrationVO benefitAdministrationVO = new BenefitAdministrationVO();

        if (null != this.getHttpSession().getAttribute(
                WebConstants.SESSION_BNFT_DEFN_ID)) {
            benefitDefKey = Integer.parseInt((String) this.getSession()
                    .getAttribute(WebConstants.SESSION_BNFT_DEFN_ID));
        }
        benefitAdministrationVO.setBenefitDefinitionKey(benefitDefKey);
        benefitAdministrationVO.setBenefitAdministrationKey(this
                .getSelectedBenefitAdministrationKey());

        benefitAdministrationVO
                .setStandardBenefitKey(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        benefitAdministrationVO
                .setStandardBenefitName(getStandardBenefitSessionObject()
                        .getStandardBenefitName());
        benefitAdministrationVO
                .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                        .getStandardBenefitParentKey());
        benefitAdministrationVO
                .setBusinessDomains(getStandardBenefitSessionObject()
                        .getBusinessDomains());
        benefitAdministrationVO
                .setStandardBenefitStatus(getStandardBenefitSessionObject()
                        .getStandardBenefitStatus());
        benefitAdministrationVO
                .setStandardBenefitVersion(getStandardBenefitSessionObject()
                        .getStandardBenefitVersionNumber());

        retrieveBenefitAdministrationRequest
                .setBenefitAdministrationVO(benefitAdministrationVO);
        return retrieveBenefitAdministrationRequest;
    }


    /*
     * This method is used to create the DeleteBenefitAdministrationRequest by
     * getting the values from session
     */
    private DeleteBenefitAdministrationRequest getDeleteBenefitAdministrationRequest() {
        int benefitDefKey = 0;
        DeleteBenefitAdministrationRequest deleteBenefitAdministrationRequest = (DeleteBenefitAdministrationRequest) this
                .getServiceRequest(ServiceManager.DELETE_BENEFIT_ADMINISTRATION);
        BenefitAdministrationVO benefitAdministrationVO = new BenefitAdministrationVO();

        if (null != this.getHttpSession().getAttribute(
                WebConstants.SESSION_BNFT_DEFN_ID)) {
            benefitDefKey = Integer.parseInt((String) this.getHttpSession()
                    .getAttribute(WebConstants.SESSION_BNFT_DEFN_ID));
            benefitAdministrationVO.setBenefitDefinitionKey(benefitDefKey);
        }
        /*benefitAdministrationVO.setBenefitAdministrationKey(this
                .getSelectedBenefitAdministrationKey());*/
        benefitAdministrationVO.setBenefitAdministrationKeysForDelete
								(this.getBenefitAdminstrationsForDelete());
        benefitAdministrationVO
                .setStandardBenefitKey(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        benefitAdministrationVO
                .setStandardBenefitName(getStandardBenefitSessionObject()
                        .getStandardBenefitName());
        benefitAdministrationVO
                .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                        .getStandardBenefitParentKey());
        benefitAdministrationVO
                .setBusinessDomains(getStandardBenefitSessionObject()
                        .getBusinessDomains());
        benefitAdministrationVO
                .setStandardBenefitStatus(getStandardBenefitSessionObject()
                        .getStandardBenefitStatus());
        benefitAdministrationVO
                .setStandardBenefitVersion(getStandardBenefitSessionObject()
                        .getStandardBenefitVersionNumber());

        deleteBenefitAdministrationRequest
                .setBenefitAdministrationVO(benefitAdministrationVO);
        return deleteBenefitAdministrationRequest;
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
     * @return Returns the effectiveDate.
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * @param effectiveDate
     *            The effectiveDate to set.
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate.trim();
    }


    /**
     * @return Returns the expiryDate.
     */
    public String getExpiryDate() {
        return expiryDate;
    }


    /**
     * @param expiryDate
     *            The expiryDate to set.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate.trim();
    }


    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description.trim();
    }


    /**
     * @return Returns the renderFlag.
     */
    public boolean isRenderFlag() {
        return renderFlag;
    }


    /**
     * @param renderFlag
     *            The renderFlag to set.
     */
    public void setRenderFlag(boolean renderFlag) {
        this.renderFlag = renderFlag;
    }


    /**
     * @return Returns the requiredEffectiveDate.
     */
    public boolean isRequiredEffectiveDate() {
        return requiredEffectiveDate;
    }


    /**
     * @param requiredEffectiveDate
     *            The requiredEffectiveDate to set.
     */
    public void setRequiredEffectiveDate(boolean requiredEffectiveDate) {
        this.requiredEffectiveDate = requiredEffectiveDate;
    }


    /**
     * @return Returns the requiredExpiryDate.
     */
    public boolean isRequiredExpiryDate() {
        return requiredExpiryDate;
    }


    /**
     * @param requiredExpiryDate
     *            The requiredExpiryDate to set.
     */
    public void setRequiredExpiryDate(boolean requiredExpiryDate) {
        this.requiredExpiryDate = requiredExpiryDate;
    }


    /**
     * For navigating from benefitLevel Tab
     */
    public String loadBenefitAdministration() {
        this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
                + " ("
                + this.getStandardBenefitSessionObject()
                        .getStandardBenefitName() + ") >> Edit");
        return WebConstants.BENEFIT_ADMINISTRATION;
    }


    /**
     * For navigating from benefitLevel View Tab
     */
    public String loadBenefitAdministrationView() {
        this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
                + " ("
                + this.getStandardBenefitSessionObject()
                        .getStandardBenefitName() + ") >> View");
        return WebConstants.BENEFIT_ADMINISTRATION_VIEW;
    }


    /**
     * This method get the benefitAdministrationList
     * 
     * @return List benefitAdministrationList.
     */

    public List getBenefitAdministrationList() {
        benefitAdministrationList = new ArrayList();
        int benefitDefKey = 0;
        LocateBenefitAdministrationRequest locateBenefitAdministrationRequest = (LocateBenefitAdministrationRequest) this
                .getServiceRequest(ServiceManager.LOCATE_BENEFIT_ADMINISTRATION);
        if (null != this.getHttpSession().getAttribute(
                WebConstants.SESSION_BNFT_DEFN_ID)) {
            benefitDefKey = Integer.parseInt((String) this.getHttpSession()
                    .getAttribute(WebConstants.SESSION_BNFT_DEFN_ID));
        }
        locateBenefitAdministrationRequest
                .setBenefitDefinitionKey(benefitDefKey);
        locateBenefitAdministrationRequest
                .setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
        LocateBenefitAdministrationResponse locateBenefitAdministrationResponse = (LocateBenefitAdministrationResponse) executeService(locateBenefitAdministrationRequest);
      
        benefitAdministrationList = locateBenefitAdministrationResponse
                .getBenefitAdministrationsList();
        
        //Code for formatting the description text to the length of 20
        // char.
        List formattedResultSet = new ArrayList();
        for (int i = 0; i < benefitAdministrationList.size(); i++) {
        	BenefitAdministrationBO  benefitAdministrationBO  = (BenefitAdministrationBO)benefitAdministrationList
                    .get(i);
            String description = "";
            if (benefitAdministrationBO.getDescription().length() > 20) {
                description = benefitAdministrationBO.getDescription();
                description = description.substring(0, 20)
                        .concat("...");
                benefitAdministrationBO.setDescription(description);
                formattedResultSet.add(benefitAdministrationBO);
            } else
                formattedResultSet.add(benefitAdministrationBO);
        }
        //End of Code for formatting the description text to the length
        // of 15 char.
        if (benefitAdministrationList.size() == 0) {
        	formattedResultSet = null;
        }
        
        
        //changes
        if (null != this.getStandardBenefitSessionObject()
                .getStandardBenefitMode()
                && WebConstants.BENEFIT_VIEW.equals(this
                        .getStandardBenefitSessionObject()
                        .getStandardBenefitMode())) {
            this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
                    + " ("
                    + this.getStandardBenefitSessionObject()
                            .getStandardBenefitName() + ") >> View");
        } else
            this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
                    + " ("
                    + this.getStandardBenefitSessionObject()
                            .getStandardBenefitName() + ") >> Edit");
        //end
        //this.setBreadCrumbText("Product Configuration >> Standard Benefit" +
        // "(" + this.getStandardBenefitSessionObject().getStandardBenefitName()
        // + ") >> Edit");
        return formattedResultSet;
    }
    /**
     * This method get the BenefitAdministrationListForViewandPrint
     * 
     * @return List BenefitAdministrationListForViewandPrint.
     */

    public List getBenefitAdministrationListForViewandPrint() {
        benefitAdministrationListForViewandPrint = new ArrayList();
        int benefitDefKey = 0;
        LocateBenefitAdministrationRequest locateBenefitAdministrationRequest = (LocateBenefitAdministrationRequest) this
                .getServiceRequest(ServiceManager.LOCATE_BENEFIT_ADMINISTRATION);
        if (null != this.getHttpSession().getAttribute(
                WebConstants.SESSION_BNFT_DEFN_ID)) {
            benefitDefKey = Integer.parseInt((String) this.getHttpSession()
                    .getAttribute(WebConstants.SESSION_BNFT_DEFN_ID));
        }
        locateBenefitAdministrationRequest
                .setBenefitDefinitionKey(benefitDefKey);
        locateBenefitAdministrationRequest
                .setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
        LocateBenefitAdministrationResponse locateBenefitAdministrationResponse = (LocateBenefitAdministrationResponse) executeService(locateBenefitAdministrationRequest);
      
        benefitAdministrationListForViewandPrint = locateBenefitAdministrationResponse
                .getBenefitAdministrationsList();
        
        //changes
        if (null != this.getStandardBenefitSessionObject()
                .getStandardBenefitMode()
                && WebConstants.BENEFIT_VIEW.equals(this
                        .getStandardBenefitSessionObject()
                        .getStandardBenefitMode())) {
            this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
                    + " ("
                    + this.getStandardBenefitSessionObject()
                            .getStandardBenefitName() + ") >> View");
        } else
            this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
                    + " ("
                    + this.getStandardBenefitSessionObject()
                            .getStandardBenefitName() + ") >> Edit");
        //end
        //this.setBreadCrumbText("Product Configuration >> Standard Benefit" +
        // "(" + this.getStandardBenefitSessionObject().getStandardBenefitName()
        // + ") >> Edit");
        return benefitAdministrationListForViewandPrint;
    }

    /**
     * Sets the benefitAdministrationList
     * 
     * @param benefitAdministrationList.
     */

    public void setBenefitAdministrationList(List benefitAdministrationList) {
        this.benefitAdministrationList = benefitAdministrationList;
    }


    /**
     * Method to return the current session
     * 
     * @return HttpSession
     */
    protected HttpSession getHttpSession() {
        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
        return (HttpSession) context.getSession(true);
    }


    /**
     * Returns the benefitAdministrationKey
     * 
     * @return int benefitAdministrationKey.
     */

    public int getBenefitAdministrationKey() {
        return benefitAdministrationKey;
    }


    /**
     * Sets the benefitAdministrationKey
     * 
     * @param benefitAdministrationKey.
     */

    public void setBenefitAdministrationKey(int benefitAdministrationKey) {
        this.benefitAdministrationKey = benefitAdministrationKey;
    }


    /**
     * Returns the selectedBenefitAdministrationKey
     * 
     * @return int selectedBenefitAdministrationKey.
     */

    public int getSelectedBenefitAdministrationKey() {
        return selectedBenefitAdministrationKey;
    }


    /**
     * Sets the selectedBenefitAdministrationKey
     * 
     * @param selectedBenefitAdministrationKey.
     */

    public void setSelectedBenefitAdministrationKey(
            int selectedBenefitAdministrationKey) {
        this.selectedBenefitAdministrationKey = selectedBenefitAdministrationKey;
    }


    /*
     * This method gets the StandardBenefitSessionObject
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
	 * @return Returns the benefitAdminstrationsForDelete.
	 */
	public String getBenefitAdminstrationsForDelete() {
		return benefitAdminstrationsForDelete;
	}
	/**
	 * @param benefitAdminstrationsForDelete The benefitAdminstrationsForDelete to set.
	 */
	public void setBenefitAdminstrationsForDelete(
			String benefitAdminstrationsForDelete) {
		this.benefitAdminstrationsForDelete = benefitAdminstrationsForDelete;
	}
	/**
	 * @param benefitAdministrationListForViewandPrint The benefitAdministrationListForViewandPrint to set.
	 */
	public void setBenefitAdministrationListForViewandPrint(
			List benefitAdministrationListForViewandPrint) {
		this.benefitAdministrationListForViewandPrint = benefitAdministrationListForViewandPrint;
	}
}