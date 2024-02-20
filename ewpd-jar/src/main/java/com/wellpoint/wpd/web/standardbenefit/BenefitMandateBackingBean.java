/*
 * Created on Feb 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentRetrieveRequest;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentRetrieveResponse;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.standardbenefit.bo.CitationNumberBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StateBO;
import com.wellpoint.wpd.common.standardbenefit.request.DeleteBenefitMandateRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateBenefitMandateRequest;
import com.wellpoint.wpd.common.standardbenefit.request.LocateMandateListRequest;
import com.wellpoint.wpd.common.standardbenefit.request.NonAdjBenefitMandateRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RetrieveNonAdjMandateRequest;
import com.wellpoint.wpd.common.standardbenefit.response.DeleteBenefitMandateResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateBenefitMandateResponse;
import com.wellpoint.wpd.common.standardbenefit.response.LocateMandateListResponse;
import com.wellpoint.wpd.common.standardbenefit.response.NonAdjBenefitMandateResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RetrieveNonAdjMandateResponse;
import com.wellpoint.wpd.common.standardbenefit.vo.NonAdjBenefitMandateVO;
import com.wellpoint.wpd.common.util.DateUtil;
import com.wellpoint.wpd.web.benefitcomponent.BenefitComponentSessionObject;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing bean for benefit mandate.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitMandateBackingBean extends WPDBackingBean {

    private String effectiveDate = null;

    private String expiryDate = null;

    private String mandate = "";

    private String optionalIdentifier = null;

    private int benefitMandateMasterKey;

    private boolean requiredMandate;

    private boolean requiredOptionalIdentifier;

    private boolean requiredEffectiveDate;

    private boolean requiredExpiryDate;

    private String state = WebConstants.STATE;

    private String status = WebConstants.STATUS_BUILDING;

    private int version = WebConstants.VERSION;

    private List associatedBenefitMandateList = null;

    private String STANDARD_BENEFIT_SESSION_KEY = WebConstants.STANDARD_BENEFIT_SESSION_KEY;

    private int masterKeyUsedForUpdate = -1;

    private boolean saveAndAddFlag = false;

    List validationMessages = null;

    /** for getting the edit details */
    private String hiddenMandateId;

    private int mandateId;

    /* For MandateList popUp */
    private String mandateEffDate;

    private String mandateExpDate;

    //enhancement

    private String mandateCategory;

    private String citationNumber;

    private String fundingArrangement;

    private String effectiveness;

    private String text;

    private List citationNumberList;

    private String fundingArrangementId;

    private String fundingArrangementDesc;

    private String mandateCategoryId;

    private String mandateCategoryDesc;

    private List stateDescList;

    private String stateCode;

    private String stateDesc;

    private String flag;

    private int viewBenefitMandate;

    private boolean viewFlag = false;

    private int printBenefitMandate;

    private String formatedText;

    private String loadMandateInfo;

    private boolean flagForSaveandCheckin = true;

    private String printComponentBenefitMandate;
    
    private String printBreadCrumbText;
    /**
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = (WebConstants.HEADER
                + " ("
                + this.getStandardBenefitSessionObject()
                        .getStandardBenefitName() + ") >> Print");
        return printBreadCrumbText;
    }
    /**
     * @param printBreadCrumbText
     * 
     * Sets the printBreadCrumbText.
     */
    public void setPrintBreadCrumbText(String printBreadCrumbText) {
        this.printBreadCrumbText = printBreadCrumbText;
    }
    /**
     * @return flagForSaveandCheckin
     * 
     * Returns the flagForSaveandCheckin.
     */
    public boolean isFlagForSaveandCheckin() {
        return flagForSaveandCheckin;
    }


    /**
     * @param flagForSaveandCheckin
     * 
     * Sets the flagForSaveandCheckin.
     */
    public void setFlagForSaveandCheckin(boolean flagForSaveandCheckin) {
        this.flagForSaveandCheckin = flagForSaveandCheckin;
    }


    /**
     * @return validationMessages
     * 
     * Returns the validationMessages.
     */
    public List getValidationMessages() {
        return validationMessages;
    }


    /**
     * @param validationMessages
     * 
     * Sets the validationMessages.
     */
    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }


    /**
     * @return loadMandateInfo
     * 
     * Returns the loadMandateInfo.
     */
    public String getLoadMandateInfo() {
        loadBenefitMandate();

        return loadMandateInfo;
    }


    /**
     * @param loadMandateInfo
     * 
     * Sets the loadMandateInfo.
     */
    public void setLoadMandateInfo(String loadMandateInfo) {
        this.loadMandateInfo = loadMandateInfo;
    }


    /**
     * Returns the fundingArrangementDesc
     * 
     * @return String fundingArrangementDesc.
     */
    public String getFundingArrangementDesc() {
        return fundingArrangementDesc;
    }


    /**
     * Sets the fundingArrangementDesc
     * 
     * @param fundingArrangementDesc.
     */
    public void setFundingArrangementDesc(String fundingArrangementDesc) {
        this.fundingArrangementDesc = fundingArrangementDesc;
    }


    /**
     * Returns the fundingArrangementId
     * 
     * @return String fundingArrangementId.
     */
    public String getFundingArrangementId() {
        return fundingArrangementId;
    }


    /**
     * Sets the fundingArrangementId
     * 
     * @param fundingArrangementId.
     */
    public void setFundingArrangementId(String fundingArrangementId) {
        this.fundingArrangementId = fundingArrangementId;
    }

    private boolean requiredMandateCategory = false;

    private boolean requiredFundingArrangement = false;

    private boolean requiredEffectiveness = false;

    private boolean requiredDesription = false;

    private boolean requiredStateCode = false;


    /**
     * Returns the requiredStateCode
     * 
     * @return boolean requiredStateCode.
     */
    public boolean isRequiredStateCode() {
        return requiredStateCode;
    }


    /**
     * Sets the requiredStateCode
     * 
     * @param requiredStateCode.
     */
    public void setRequiredStateCode(boolean requiredStateCode) {
        this.requiredStateCode = requiredStateCode;
    }


    /**
     * Returns the stateCode
     * 
     * @return String stateCode.
     */
    public String getStateCode() {
        return stateCode;
    }


    /**
     * Sets the stateCode
     * 
     * @param stateCode.
     */
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }


    /**
     * Returns the stateDesc
     * 
     * @return String stateDesc.
     */
    public String getStateDesc() {
        return stateDesc;
    }


    /**
     * Sets the stateDesc
     * 
     * @param stateDesc.
     */
    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }


    /**
     * Returns the citationNumber
     * 
     * @return String citationNumber.
     */
    public String getCitationNumber() {
        return citationNumber;
    }


    /**
     * Sets the citationNumber
     * 
     * @param citationNumber.
     */
    public void setCitationNumber(String citationNumber) {
        this.citationNumber = citationNumber;
    }


    /**
     * Returns the citationNumberList
     * 
     * @return List citationNumberList.
     */
    public List getCitationNumberList() {
        return citationNumberList;
    }


    /**
     * Sets the citationNumberList
     * 
     * @param citationNumberList.
     */
    public void setCitationNumberList(List citationNumberList) {
        this.citationNumberList = citationNumberList;
    }


    /**
     * Returns the effectiveness
     * 
     * @return String effectiveness.
     */
    public String getEffectiveness() {
        return effectiveness;
    }


    /**
     * Sets the effectiveness
     * 
     * @param effectiveness.
     */
    public void setEffectiveness(String effectiveness) {
        this.effectiveness = effectiveness;
    }


    /**
     * Returns the fundingArrangement
     * 
     * @return String fundingArrangement.
     */
    public String getFundingArrangement() {
        return fundingArrangement;
    }


    /**
     * Sets the fundingArrangement
     * 
     * @param fundingArrangement.
     */
    public void setFundingArrangement(String fundingArrangement) {
        this.fundingArrangement = fundingArrangement;
    }


    /**
     * Returns the requiredEffectiveness
     * 
     * @return boolean requiredEffectiveness.
     */
    public boolean isRequiredEffectiveness() {
        return requiredEffectiveness;
    }


    /**
     * Sets the requiredEffectiveness
     * 
     * @param requiredEffectiveness.
     */
    public void setRequiredEffectiveness(boolean requiredEffectiveness) {
        this.requiredEffectiveness = requiredEffectiveness;
    }


    /**
     * Returns the requiredFundingArrangement
     * 
     * @return boolean requiredFundingArrangement.
     */
    public boolean isRequiredFundingArrangement() {
        return requiredFundingArrangement;
    }


    /**
     * Sets the requiredFundingArrangement
     * 
     * @param requiredFundingArrangement.
     */
    public void setRequiredFundingArrangement(boolean requiredFundingArrangement) {
        this.requiredFundingArrangement = requiredFundingArrangement;
    }


    /*
     * Constructor
     */
    public BenefitMandateBackingBean() {
        Date date = DateUtil.getDefaultEndDate();
        String expDate = WPDStringUtil.getStringDate(date);
        this.setExpiryDate(expDate);

    }

    // mandate popuplist
    List mandateListPopUp = null;


    /**
     * @return Returns the mandateListPopUp.
     */
    public List getMandateListPopUp() {
        /*
         * connect to the database and get the list of mandates from the mandate
         * table
         */
        // create the request
        LocateMandateListRequest locateMandateListRequest = (LocateMandateListRequest) this
                .getServiceRequest(ServiceManager.LOCATE_MANDATE_LIST);

        locateMandateListRequest
                .setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
        locateMandateListRequest.setEffectiveDate(getRequest().getParameter(
                "effDate"));
        locateMandateListRequest.setExpiryDate(getRequest().getParameter(
                "expDate"));
        // get the response
        LocateMandateListResponse locateMandateListResponse = (LocateMandateListResponse) executeService(locateMandateListRequest);
        // get the list from response and copy it to the mandate list
        List mandateListFromResponse = locateMandateListResponse
                .getMandateList();
        // assign the responseList to backingBean property
        mandateListPopUp = mandateListFromResponse;
        // return the mandate list
        return mandateListPopUp;
    }


    /**
     * @param mandateListPopUp
     *            The mandateListPopUp to set.
     */
    public void setMandateListPopUp(List mandateListPopUp) {
        this.mandateListPopUp = mandateListPopUp;
    }


    /**
     * LOCATE:Method to show the selected values of the associated
     * BenefitMandate in the dataTable Method called from
     * benefitMandateCreate.jsp The method creates a request
     * ,LocateBenefitMandateRequest and sets the StandardBenefitId to the
     * request(BNFT_SYS_ID in BMST_BNFT_MSTR). The executeService method in turn
     * calls the execute(LocateBenefitMandateRequest request) in the
     * StandardBenefitBusinessService. Returns the associatedBenefitMandateList
     * to be displayed in the datatable.
     *  
     */

    public List getAssociatedBenefitMandateList() {
        associatedBenefitMandateList = new ArrayList();
        LocateBenefitMandateRequest locateBenefitMandateRequest = (LocateBenefitMandateRequest) this
                .getServiceRequest(ServiceManager.LOCATE_BENEFIT_MANDATE);
        locateBenefitMandateRequest
                .setBenefitMasterSystemId(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        locateBenefitMandateRequest
                .setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
        LocateBenefitMandateResponse locateBenefitMandateResponse = (LocateBenefitMandateResponse) executeService(locateBenefitMandateRequest);
        this.setVersion(getStandardBenefitSessionObject()
                .getStandardBenefitVersionNumber());
        this.setState(getStandardBenefitSessionObject()
                .getStandardBenefitState());
        this.setStatus(getStandardBenefitSessionObject()
                .getStandardBenefitStatus());
        return locateBenefitMandateResponse.getBenefitMandateList();

    }


    /**
     * DELETE: To delete a particular entry for a MandateBenefit in the
     * AssociatedMandateBenefitTable Method called from benefitMandateCreate.jsp
     * The method creates a request ,DeleteBenefitMandateRequest and sets
     * benefitMandateMasterKey( BNFT_MNDT_SYS_ID in
     * BMNDT_BNFT_MNDT)),StandardBenefitKey(BNFT_SYS_ID in BMST_BNFT_MSTR),
     * StandardBenefitVersionNumber,StandardBenefitName. The executeService
     * method in turn calls the execute(DeleteBenefitMandateRequest request) in
     * the StandardBenefitBusinessService. The resetBenefitMandateObject method
     * resets all the fields in the page.
     */
    public String deleteBenefitMandate() {
        DeleteBenefitMandateRequest deleteBenefitMandateRequest = (DeleteBenefitMandateRequest) this
                .getServiceRequest(ServiceManager.DELETE_BENEFIT_MANDATE);
        deleteBenefitMandateRequest
                .setMandateMasterKey(this.benefitMandateMasterKey);
        deleteBenefitMandateRequest
                .setStandardBenefitMasterKey(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        deleteBenefitMandateRequest
                .setVersion(getStandardBenefitSessionObject()
                        .getStandardBenefitVersionNumber());
        deleteBenefitMandateRequest
                .setMandateIdentifier(getStandardBenefitSessionObject()
                        .getStandardBenefitName());
        deleteBenefitMandateRequest
                .setBusinessDomains(getStandardBenefitSessionObject()
                        .getBusinessDomains());
        deleteBenefitMandateRequest
                .setStandardBenefitStatus(getStandardBenefitSessionObject()
                        .getStandardBenefitStatus());
        deleteBenefitMandateRequest
                .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                        .getStandardBenefitParentKey());
        DeleteBenefitMandateResponse deleteBenefitMandateResponse = (DeleteBenefitMandateResponse) executeService(deleteBenefitMandateRequest);
        this.resetBenefitMandateObject();
        return "";
    }


    /**
     * For NonAdjBenefitMandate save Method called from benefitMandateCreate.jsp
     * The method creates a request NonAdjBenefitMandateRequest and sets all the
     * values required for the insertion into the BMNDT_BNFT_MNDT table This
     * includes validation of all fields.
     */
    public String saveBenefitMandate() {
        validationMessages = new ArrayList(1);
        this.setFlagForSaveandCheckin(false);
        //		if ("".equals(this.getText()) || null == this.getText()) {
        //			this.setText((String)getStandardBenefitSessionObject().getNoteText());
        //		}
        if (!validateRequiredFileds()) {
            /*
             * this.setFormatedText((String) getStandardBenefitSessionObject()
             * .getNoteText());
             */
            addAllMessagesToRequest(validationMessages);
            if ("FED".equals(this.getStandardBenefitSessionObject()
                    .getMandateType())) {
                this.setStateCode(WebConstants.DEFAULT_FEDERAL_FIELD);
            }
            return "";
        }
        NonAdjBenefitMandateRequest nonAdjBenefitMandateRequest = getNonAdjBenefitMandateRequest();
        NonAdjBenefitMandateResponse nonAdjBenefitMandateResponse = (NonAdjBenefitMandateResponse) executeService(nonAdjBenefitMandateRequest);
        if (nonAdjBenefitMandateResponse != null
                && !nonAdjBenefitMandateResponse.isErrorMessageInList()) {
            //this.setMasterKeyUsedForUpdate(nonAdjBenefitMandateResponse.getBenefitMandateBO().getBenefitMandateId());
            //mandate =
            // nonAdjBenefitMandateResponse.getBenefitMandateBO().getMandateMasterId()+"~"+nonAdjBenefitMandateResponse.getBenefitMandateBO().getMandate();
            //this.setMandate(mandate);
            this
                    .setStateCode(getTildaStringForStateCode(nonAdjBenefitMandateResponse
                            .getBenefitMandateBO().getBenefitStateCodeList()));
            this.setMandateCategory(nonAdjBenefitMandateResponse
                    .getBenefitMandateBO().getMandateCategory());
            this
                    .setCitationNumber(getTildaStringForCitationNumber(nonAdjBenefitMandateResponse
                            .getBenefitMandateBO().getCitationNumberList()));
            this.setFundingArrangement(nonAdjBenefitMandateResponse
                    .getBenefitMandateBO().getFundingArrangement()
                    + "~"
                    + nonAdjBenefitMandateResponse.getBenefitMandateBO()
                            .getFundingArrangementDesc());
            this.setEffectiveness(nonAdjBenefitMandateResponse
                    .getBenefitMandateBO().getEffectiveness());
            this.setText(nonAdjBenefitMandateResponse.getBenefitMandateBO()
                    .getText());
            this.setFormatedText(this.text);
            getStandardBenefitSessionObject().setNoteText(
                    this.getFormatedText());
            getStandardBenefitSessionObject().setBenefitMandateId(
                    nonAdjBenefitMandateResponse.getBenefitMandateBO()
                            .getBenefitMandateId());
        } else if (nonAdjBenefitMandateResponse != null
                && nonAdjBenefitMandateResponse.isErrorMessageInList()) {
            this.setRequiredEffectiveDate(true);
            this.setRequiredExpiryDate(true);
        }
        this.setState(getStandardBenefitSessionObject()
                .getStandardBenefitState());
        this.setVersion(getStandardBenefitSessionObject()
                .getStandardBenefitVersionNumber());
        this.setStatus(getStandardBenefitSessionObject()
                .getStandardBenefitStatus());
        //this.getStandardBenefitSessionObject().setStateCode("");

        //    	Setting the header
        getStandardBenefitSessionObject().setBenefitMandateId(
                nonAdjBenefitMandateResponse.getBenefitMandateBO()
                        .getBenefitMandateId());
        return "";
    }


    /**
     * For NonAdjBenefitMandate save Method called from benefitMandateCreate.jsp
     * The method creates a request NonAdjBenefitMandateRequest and sets all the
     * values required for the insertion into the BMNDT_BNFT_MNDT table This
     * includes validation of all fields. The resetBenefitMandateObject method
     * resets all the fields in the page.
     */
    public String saveAndAddBenefitMandate() {
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
        NonAdjBenefitMandateRequest nonAdjBenefitMandateRequest = getNonAdjBenefitMandateRequest();
        NonAdjBenefitMandateResponse nonAdjBenefitMandateResponse = (NonAdjBenefitMandateResponse) executeService(nonAdjBenefitMandateRequest);
        if (nonAdjBenefitMandateResponse != null
                && !nonAdjBenefitMandateResponse.isErrorMessageInList()) {
            resetBenefitMandateObject();
        } else if (nonAdjBenefitMandateResponse != null
                && nonAdjBenefitMandateResponse.isErrorMessageInList()) {
            this.setRequiredEffectiveDate(true);
            this.setRequiredExpiryDate(true);
        }
        return "";
    }


    /**
     * Resets the fields in benefitMandateCreate.jsp MasterKeyUsedForUpdate is
     * set to -1 to make sure that persist happens and not update.
     */
    private void resetBenefitMandateObject() {
        this.setEffectiveDate("");
        this.setExpiryDate(WebConstants.DEFAULT_EXP_DATE);
        this.setMandate("");
        this.setOptionalIdentifier("");
        this.setMasterKeyUsedForUpdate(-1);
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
     * Validation function to check whether date entering is a valid one or not
     *  
     */
    public boolean validateDateFormat() {
        validationMessages = new ArrayList(1);
        boolean requiredDate = false;
        if (!WPDStringUtil.isValidDate(this.effectiveDate)) {
            requiredDate = true;
            ErrorMessage errorMessage = new ErrorMessage(
                    WebConstants.INPUT_FORMAT_INVALID);
            errorMessage
                    .setParameters(new String[] { WebConstants.EFFECTIVE_DATE });
            validationMessages.add(errorMessage);
        }
        if (!WPDStringUtil.isValidDate(this.expiryDate)) {
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
     * Validation function to check whether all All Mandatory fields are being
     * entered
     *  
     */
    public boolean validateRequiredFileds() {
        validationMessages = new ArrayList(1);
        boolean requiredField = true;
        //New fields as part of enhancement

        this.requiredFundingArrangement = false;
        this.requiredEffectiveness = false;
        this.requiredDesription = false;
        this.requiredMandateCategory = false;
        this.requiredStateCode = false;

        if (this.mandateCategory == null || "".equals(this.mandateCategory)) {
            requiredMandateCategory = true;
            requiredField = false;
        }
        if (this.fundingArrangement == null
                || "".equals(this.fundingArrangement)) {
            requiredFundingArrangement = true;
            requiredField = false;
        }
        if (this.effectiveness == null || "".equals(this.effectiveness)) {
            requiredEffectiveness = true;
            requiredField = false;
        }

        if (this.text == null || "".equals(this.text.trim())) {
            if (null == this.getStandardBenefitSessionObject().getNoteText()
                    || "".equals(this.getStandardBenefitSessionObject()
                            .getNoteText())) {
                if (null == this.getFormatedText()
                        || "".equals(this.getFormatedText())) {
                    requiredDesription = true;
                    requiredField = false;

                } else {
                    this.setText(this.getFormatedText());
                }
            } else {
                this.setText((String) getStandardBenefitSessionObject()
                        .getNoteText());
            }
        }

        if (!((WebConstants.DEFAULT_FEDERAL_FIELD).equals(this
                .getStandardBenefitSessionObject().getStateCode()))) {
            if (this.stateCode == null || "".equals(this.stateCode)) {
                requiredStateCode = true;
                requiredField = false;
            }
        } else if ("FED".equals(this.getStandardBenefitSessionObject()
                .getMandateType())
                && !requiredField) {
            this.setStateCode(WebConstants.DEFAULT_FEDERAL_FIELD);
            this.getStandardBenefitSessionObject().setStateCode(
                    this.getStateCode());
        }
        if (null != this.text) {
            if (this.text.length() > 3000) {
                requiredDesription = true;
                validationMessages.add(new ErrorMessage(
                        WebConstants.MANDATE_TEXT));
                return false;
            }
        }

        if (!requiredField) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
        }
        return requiredField;
    }


    /**
     * For NonAdjBenefitMandate check in Method called from
     * benefitMandateCreate.jsp
     * 
     * This includes validation of all fields.
     *  
     */
    public String doneForCheckin() {
        String returnString = "";
        this.setFlagForSaveandCheckin(false);
        validationMessages = new ArrayList(1);
        if (!validateRequiredFileds()) {
            addAllMessagesToRequest(validationMessages);
            return returnString;
        }
        saveBenefitMandate();
        Application application = FacesContext.getCurrentInstance()
                .getApplication();
        StandardBenefitBackingBean backingBean = ((StandardBenefitBackingBean) application
                .createValueBinding("#{standardBenefitBackingBean}").getValue(
                        FacesContext.getCurrentInstance()));
        returnString = backingBean.doneFromOtherTabs();
        return returnString;
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
        int effectiveMonth = Integer.parseInt(effectiveDate.substring(0,
                effectiveDate.indexOf("/")));
        int effectiveDay = Integer
                .parseInt(effectiveDate.substring(
                        effectiveDate.indexOf("/") + 1, effectiveDate
                                .lastIndexOf("/")));
        int effectiveYear = Integer.parseInt(effectiveDate.substring(
                effectiveDate.lastIndexOf("/") + 1, effectiveDate.length()));

        int expiryMonth = Integer.parseInt(expiryDate.substring(0, expiryDate
                .indexOf("/")));
        int expiryDay = Integer.parseInt(expiryDate.substring(expiryDate
                .indexOf("/") + 1, expiryDate.lastIndexOf("/")));
        int expiryYear = Integer.parseInt(expiryDate.substring(expiryDate
                .lastIndexOf("/") + 1, expiryDate.length()));

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        cal1.set(effectiveYear, effectiveMonth, effectiveDay);
        cal2.set(expiryYear, expiryMonth, expiryDay);
        if (cal2.before(cal1) || cal2.equals(cal1))
            return true;

        return false;

    }


    /**
     * Sets the associatedBenefitMandateList
     * 
     * @param associatedBenefitMandateList.
     */

    public void setAssociatedBenefitMandateList(
            List associatedBenefitMandateList) {
        this.associatedBenefitMandateList = associatedBenefitMandateList;
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
        this.effectiveDate = effectiveDate;
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
        this.expiryDate = expiryDate;
    }


    /**
     * Returns the mandate
     * 
     * @return String mandate.
     */

    public String getMandate() {
        return mandate;
    }


    /**
     * Sets the mandate
     * 
     * @param mandate.
     */

    public void setMandate(String mandate) {
        this.mandate = mandate;
    }


    /**
     * Returns the optionalIdentifier
     * 
     * @return String optionalIdentifier.
     */

    public String getOptionalIdentifier() {
        return optionalIdentifier;
    }


    /**
     * Sets the optionalIdentifier
     * 
     * @param optionalIdentifier.
     */

    public void setOptionalIdentifier(String optionalIdentifier) {
        this.optionalIdentifier = optionalIdentifier;
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
     * Returns the requiredMandate
     * 
     * @return boolean requiredMandate.
     */

    public boolean isRequiredMandate() {
        return requiredMandate;
    }


    /**
     * Sets the requiredMandate
     * 
     * @param requiredMandate.
     */

    public void setRequiredMandate(boolean requiredMandate) {
        this.requiredMandate = requiredMandate;
    }


    /**
     * Returns the requiredOptionalIdentifier
     * 
     * @return boolean requiredOptionalIdentifier.
     */

    public boolean isRequiredOptionalIdentifier() {
        return requiredOptionalIdentifier;
    }


    /**
     * Sets the requiredOptionalIdentifier
     * 
     * @param requiredOptionalIdentifier.
     */

    public void setRequiredOptionalIdentifier(boolean requiredOptionalIdentifier) {
        this.requiredOptionalIdentifier = requiredOptionalIdentifier;
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


    /***************************************************************************
     * @return Returns the benefitMandateMasterKey.
     */
    public int getBenefitMandateMasterKey() {
        return benefitMandateMasterKey;
    }


    /**
     * @param benefitMandateMasterKey
     *            The benefitMandateMasterKey to set.
     */
    public void setBenefitMandateMasterKey(int benefitMandateMasterKey) {
        this.benefitMandateMasterKey = benefitMandateMasterKey;
    }


    public String redirectToBenefitMandate() {
        return WebConstants.LOAD_BENEFIT_MANDATE;
    }


    /*
     * This method loads all the values of the corresponding mandate benefit for
     * editing
     *  
     */
    public String loadBenefitMandate() {
        RetrieveNonAdjMandateRequest retrieveNonAdjMandateRequest = (RetrieveNonAdjMandateRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_NON_ADJ_MANDATE);
        retrieveNonAdjMandateRequest
                .setMainObjectKey(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        retrieveNonAdjMandateRequest
                .setMainObjectIdentifier(getStandardBenefitSessionObject()
                        .getStandardBenefitName());
        retrieveNonAdjMandateRequest
                .setBusinessDomains(getStandardBenefitSessionObject()
                        .getBusinessDomains());
        retrieveNonAdjMandateRequest
                .setMainObjectVersion(getStandardBenefitSessionObject()
                        .getStandardBenefitVersionNumber());
        retrieveNonAdjMandateRequest
                .setStandardBenefitStatus(getStandardBenefitSessionObject()
                        .getStandardBenefitStatus());

        RetrieveNonAdjMandateResponse retrieveNonAdjMandateResponse = (RetrieveNonAdjMandateResponse) executeService(retrieveNonAdjMandateRequest);

        if (retrieveNonAdjMandateResponse != null
                && !retrieveNonAdjMandateResponse.isErrorMessageInList()) {
            //this.setMasterKeyUsedForUpdate(nonAdjBenefitMandateResponse.getBenefitMandateBO().getBenefitMandateId());
            //mandate =
            // nonAdjBenefitMandateResponse.getBenefitMandateBO().getMandateMasterId()+"~"+nonAdjBenefitMandateResponse.getBenefitMandateBO().getMandate();
            //this.setMandate(mandate);
            if (null != retrieveNonAdjMandateResponse.getBenefitMandateBO()
                    .getBenefitStateCodeList()) {
                this
                        .setStateCode(getTildaStringForStateCode(retrieveNonAdjMandateResponse
                                .getBenefitMandateBO()
                                .getBenefitStateCodeList()));
                if (null != this.getStandardBenefitSessionObject()
                        .getMandateType()) {
                    if (this.getStandardBenefitSessionObject().getMandateType()
                            .equals("FED")
                            || this.getStandardBenefitSessionObject()
                                    .getMandateType().equals("Federal")) {
                        this.setStateCode(WebConstants.DEFAULT_FEDERAL_FIELD);
                        this.getStandardBenefitSessionObject().setStateCode(
                                this.getStateCode());
                    }
                }
            }
            if (!this.viewFlag)
                this.setMandateCategory(retrieveNonAdjMandateResponse
                        .getBenefitMandateBO().getMandateCategory());
            else
                this.setMandateCategory(retrieveNonAdjMandateResponse
                        .getBenefitMandateBO().getMandateCategoryDesc());
            this
                    .setCitationNumber(getTildaStringForCitationNumber(retrieveNonAdjMandateResponse
                            .getBenefitMandateBO().getCitationNumberList()));

            if (null != retrieveNonAdjMandateResponse.getBenefitMandateBO()
                    .getFundingArrangementDesc())
                this.setFundingArrangement(retrieveNonAdjMandateResponse
                        .getBenefitMandateBO().getFundingArrangement()
                        + "~"
                        + retrieveNonAdjMandateResponse.getBenefitMandateBO()
                                .getFundingArrangementDesc());
            if (!viewFlag)
                this.setEffectiveness(retrieveNonAdjMandateResponse
                        .getBenefitMandateBO().getEffectiveness());
            else
                this.setEffectiveness(retrieveNonAdjMandateResponse
                        .getBenefitMandateBO().getMandateEffectivenessDesc());
            this.setText(retrieveNonAdjMandateResponse.getBenefitMandateBO()
                    .getText());
            this.setFormatedText(this.getText());
            getStandardBenefitSessionObject().setNoteText(
                    this.getFormatedText());
        } else if (retrieveNonAdjMandateResponse != null
                && retrieveNonAdjMandateResponse.isErrorMessageInList()) {
            this.setMandateCategory("");
            this.setCitationNumber("");
            this.setFundingArrangement("");
            this.setEffectiveness("");
            this.setText("");
            this.setStateCode("");
        }

        //      modified for refdata
        this.setBenefitMandateMasterKey(getStandardBenefitSessionObject()
                .getStandardBenefitParentKey());

        this.setState(getStandardBenefitSessionObject()
                .getStandardBenefitState());
        this.setVersion(getStandardBenefitSessionObject()
                .getStandardBenefitVersionNumber());
        this.setStatus(getStandardBenefitSessionObject()
                .getStandardBenefitStatus());
        //    	Setting the header
        this.setBreadCrumbText(WebConstants.HEADER
                + " ("
                + this.getStandardBenefitSessionObject()
                        .getStandardBenefitName() + ") >> Edit");
        getStandardBenefitSessionObject().setBenefitMandateId(
                retrieveNonAdjMandateResponse.getBenefitMandateBO()
                        .getBenefitMandateId());
        return WebConstants.LOAD_BENEFIT_MANDATE;
    }


    /*
     * This method loads all the values of the corresponding mandate benefit for
     * viewing
     *  
     */
    public String loadBenefitMandateView() {
        this.setBenefitMandateMasterKey(getStandardBenefitSessionObject()
                .getStandardBenefitKey());
        //    	Setting the header
        this.setBreadCrumbText(WebConstants.HEADER
                + " ("
                + this.getStandardBenefitSessionObject()
                        .getStandardBenefitName() + ") >> View");
        return WebConstants.LOAD_BENEFIT_MANDATE_VIEW;
    }


    /**
     * Setting the values to nonAdjBenefitMandateVO and From
     * nonAdjBenefitMandateVO to NonAdjBenefitMandateRequest Calling the mathod
     * from Save and Save&Add method
     */
    private NonAdjBenefitMandateRequest getNonAdjBenefitMandateRequest() {
        NonAdjBenefitMandateRequest nonAdjBenefitMandateRequest = (NonAdjBenefitMandateRequest) this
                .getServiceRequest(ServiceManager.CREATE_NON_ADJ_BENEFIT_MANDATE);
        NonAdjBenefitMandateVO nonAdjBenefitMandateVO = new NonAdjBenefitMandateVO();

        /** Getting the values from session */
        nonAdjBenefitMandateVO
                .setBenefitSystemId(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        nonAdjBenefitMandateVO
                .setMasterVersion(getStandardBenefitSessionObject()
                        .getStandardBenefitVersionNumber());
        nonAdjBenefitMandateVO
                .setBenefitIdName(getStandardBenefitSessionObject()
                        .getStandardBenefitName());
        nonAdjBenefitMandateVO
                .setBusinessDomains(getStandardBenefitSessionObject()
                        .getBusinessDomains());
        nonAdjBenefitMandateVO
                .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                        .getStandardBenefitParentKey());
        nonAdjBenefitMandateVO
                .setStandardBenefitStatus(getStandardBenefitSessionObject()
                        .getStandardBenefitStatus());
        nonAdjBenefitMandateVO
                .setBenefitMandateId(getStandardBenefitSessionObject()
                        .getBenefitMandateId());
        //		nonAdjBenefitMandateVO.setBenefitMandateId(this.getMasterKeyUsedForUpdate());

        nonAdjBenefitMandateVO.setStateCode(getStateListFromTildaString(this
                .getStateCode(), 3, 2));
        nonAdjBenefitMandateVO.setMandateCategoy(this.getMandateCategory());
        nonAdjBenefitMandateVO.setCitationNumberList(WPDStringUtil
                .getListFromTildaString(this.getCitationNumber(), 2, 2, 2));
        //	nonAdjBenefitMandateVO.setFundingArrangement(WPDStringUtil.getListFromTildaString(this.getFundingArrangement(),2,2,2));

        StringTokenizer st = null;
        st = new StringTokenizer(fundingArrangement, "~");
        while (st.hasMoreTokens()) {
            this.fundingArrangementId = st.nextToken();
            this.fundingArrangementDesc = st.nextToken();
        }
        nonAdjBenefitMandateVO.setFundingArrangement(this
                .getFundingArrangementId());

        nonAdjBenefitMandateVO.setEffectiveness(this.getEffectiveness());
        nonAdjBenefitMandateVO.setText(this.getText());
        nonAdjBenefitMandateRequest
                .setNonAdjBenefitMandateVO(nonAdjBenefitMandateVO);
        return nonAdjBenefitMandateRequest;
    }


    /**
     * Returns List corresponding to the type we pass
     * 
     * @param tildaString
     *            String to be converted to list.
     * @param attrCount
     *            int the count of repeating tidas.
     * @param type
     *            int the type of list it returns.
     * @return List List of the type specified.
     */
    public List getStateListFromTildaString(String tildaString, int attrCount,
            int type) {
        List list = new ArrayList();
        if (tildaString == null || "".equals(tildaString.trim()))
            return list;

        String[] arrayTilda = tildaString.split("~");

        int count = arrayTilda.length;

        if (count > 0) {
            if (count % attrCount > 0) {
                throw new IllegalArgumentException(
                        "In correct pattern found in the given string "
                                + tildaString);
            }

            //            if (attrPosition > attrCount || attrPosition < 0) {
            //                throw new IllegalArgumentException(
            //                        "In correct pattern needed from the the given string "
            //                                + tildaString);
            //
            //            }
            list = getList(arrayTilda, count, attrCount, type, 1, 3);

        }
        return list;
    }


    /*
     * to get the list of StateBOs in a list
     */
    public List getList(String[] arrayTilda, int count, int attrCount,
            int type, int attrPosition, int repeatPosition) {

        List list = new ArrayList();
        for (int i = 0; i < count; i += attrCount) {

            if (type == 1) {
                // type= integer
                list.add(new Integer(arrayTilda[i + attrPosition - 1]));
            } else if (type == 2) {
                //type = String
                StateBO stateBO = new StateBO();
                stateBO.setBenefitStateCode(arrayTilda[i + attrPosition - 1]);
                stateBO.setFlag(arrayTilda[i + repeatPosition - 1]);
                list.add(stateBO);
            } else if (type == 3) {
                //type = Float
                list.add(new Float(arrayTilda[i + attrPosition - 1]));
            }

        }
        return list;
    }


    /*
     * to get the tilda seperated string for citation no
     */
    public static String getTildaStringForCitationNumber(List domainItems) {

        if (domainItems == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < domainItems.size(); i++) {
            CitationNumberBO element = (CitationNumberBO) domainItems.get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getCitationNumber());
            buffer.append("~");
            buffer.append(element.getCitationNumber());

        }
        return buffer.toString();
    }


    /*
     * to get the tilda seperated string for state code
     */

    public static String getTildaStringForStateCode(List domainItems) {

        if (domainItems == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < domainItems.size(); i++) {
            StateBO element = (StateBO) domainItems.get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getBenefitStateCode());
            buffer.append("~");
            if (("ALL").equals(element.getBenefitStateCode())) {
                element.setBenefitStateDesc("ALL");
            }
            buffer.append(element.getBenefitStateDesc().toUpperCase());
            buffer.append("~");
            buffer.append(element.getFlag());

        }
        return buffer.toString();
    }


    /*
     * to get the tilda seperated string for state code for viewing
     */
    public static String getTildaStringForStateCodeView(List domainItems) {

        if (domainItems == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < domainItems.size(); i++) {
            StateBO element = (StateBO) domainItems.get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getBenefitStateCode());
            buffer.append("~");
            buffer.append(element.getBenefitStateDesc());
        }
        return buffer.toString();
    }


    /**
     * This method for retrieving the selected MandateDetails for the selected
     * row in the datatable and displaying that in the page for editing it.
     * 
     * @return
     */
    public String editMandateDetails() {
        /*
         * connect to the database and get the list of mandates from the mandate
         * table
         */
        // create the request
        RetrieveNonAdjMandateRequest retrieveNonAdjMandateRequest = (RetrieveNonAdjMandateRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_NON_ADJ_MANDATE);
        // set the mandate id in the request
        //retrieveNonAdjMandateRequest.setMandateId(123);
        retrieveNonAdjMandateRequest.setMandateId(this.benefitMandateMasterKey);
        // set the version
        retrieveNonAdjMandateRequest
                .setMainObjectIdentifier(getStandardBenefitSessionObject()
                        .getStandardBenefitName());
        retrieveNonAdjMandateRequest
                .setMainObjectKey(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        retrieveNonAdjMandateRequest
                .setMainObjectVersion(getStandardBenefitSessionObject()
                        .getStandardBenefitVersionNumber());
        retrieveNonAdjMandateRequest
                .setBusinessDomains(getStandardBenefitSessionObject()
                        .getBusinessDomains());
        retrieveNonAdjMandateRequest
                .setStandardBenefitStatus(getStandardBenefitSessionObject()
                        .getStandardBenefitStatus());
        retrieveNonAdjMandateRequest
                .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                        .getStandardBenefitParentKey());
        // get the response
        RetrieveNonAdjMandateResponse retrieveNonAdjMandateResponse = (RetrieveNonAdjMandateResponse) executeService(retrieveNonAdjMandateRequest);
        // get the list from response and copy it to the mandate list
        //BenefitMandateBO benefitMandateBO =
        // retrieveNonAdjMandateResponse.getBenefitMandateBO();
        // assign the responseList to backingBean property
        // formating the date
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                WebConstants.DATE_FORMAT_STRING);
        effectiveDate = dateFormat.format(retrieveNonAdjMandateResponse
                .getBenefitMandateBO().getEffectiveDate());
        expiryDate = dateFormat.format(retrieveNonAdjMandateResponse
                .getBenefitMandateBO().getExpiryDate());
        optionalIdentifier = retrieveNonAdjMandateResponse
                .getBenefitMandateBO().getOptionalIdentifier();
        mandate = retrieveNonAdjMandateResponse.getBenefitMandateBO()
                .getMandateMasterId()
                + "~"
                + retrieveNonAdjMandateResponse.getBenefitMandateBO()
                        .getMandate();
        this.setMasterKeyUsedForUpdate(retrieveNonAdjMandateResponse
                .getBenefitMandateBO().getBenefitMandateId());
        return "";
    }


    /**
     * This method for viewing the MandateDetails for the selected mandate
     * benefit
     * 
     * @return
     */
    public String loadBenefitMandateForView() {
        // Get StdBenefitId
        int retrieveKey = Integer.parseInt(getSession().getAttribute(
                WebConstants.BENEFIT_COMPONENT_ID).toString());
        // Get bcId
        int bcId = getBenefitComponentSessionObject().getBenefitComponentId();
        // Get bcName
        String bcName = getBenefitComponentSessionObject()
                .getBenefitComponentName();

        retrieveBenefitComponentDetailsforMandateView(retrieveKey, bcId, bcName);
        // to be changed WebConstants.LOAD_COMPONENT_BENEFIT_MANDATE_VIEW
        return WebConstants.LOAD_COMPONENT_BENEFIT_MANDATE_VIEW;

    }


    public String loadMandateInformationForView() {
        //      Get StdBenefitId
        int retrieveKey = Integer.parseInt(getSession().getAttribute(
                WebConstants.BENEFIT_COMPONENT_ID).toString());
        // Get bcId
        int bcId = getBenefitComponentSessionObject().getBenefitComponentId();
        // Get bcName
        String bcName = getBenefitComponentSessionObject()
                .getBenefitComponentName();

        retrieveBenefitComponentDetailsforMandateView(retrieveKey, bcId, bcName);
        return "loadMandateInformationForView";
    }


    /**
     * This method for retrieving the benefitcomponent details for view
     * 
     * 
     * @return
     */
    private void retrieveBenefitComponentDetailsforMandateView(int retrieveKey,
            int bcId, String bcName) {

        BenefitComponentRetrieveRequest benefitComponentRetrieveRequest = (BenefitComponentRetrieveRequest) this
                .getServiceRequest(ServiceManager.BENEFIT_COMPONENT_RETRIEVE_REQUEST);

        BenefitComponentVO benefitComponentVO = new BenefitComponentVO();
        // BenefitSysId in BMST_BNFT_MSTR
        benefitComponentVO.setStandardBenefitKey(retrieveKey);
        //BC Id and Name in BCM_BNFT_CMPNT_MSTR
        benefitComponentVO.setBenefitComponentId(bcId);
        benefitComponentVO.setBenefitComponentName(bcName);
        benefitComponentRetrieveRequest
                .setMainObjVersion(getBenefitComponentSessionObject()
                        .getBenefitComponentVersionNumber());
        benefitComponentRetrieveRequest
                .setBenefitComponentVO(benefitComponentVO);
        benefitComponentVO
                .setBusinessDomainList(getBenefitComponentSessionObject()
                        .getBusinessDomainList());
        benefitComponentVO.setAction("MANDATE_INFO_VIEW");
        BenefitComponentRetrieveResponse benefitComponentRetrieveResponse = (BenefitComponentRetrieveResponse) executeService(benefitComponentRetrieveRequest);
        //changes
       
        if (null != benefitComponentRetrieveResponse
                && !benefitComponentRetrieveResponse.isErrorMessageInList()) {
            this.setMandateCategory(benefitComponentRetrieveResponse
                    .getBenefitMandateBO().getMandateCategoryDesc());
            this
                    .setCitationNumber(getTildaStringForCitationNumber(benefitComponentRetrieveResponse
                            .getBenefitMandateBO().getCitationNumberList()));
            if (null != benefitComponentRetrieveResponse.getBenefitMandateBO()
                    .getFundingArrangementDesc())
                this.setFundingArrangement(benefitComponentRetrieveResponse
                        .getBenefitMandateBO().getFundingArrangement()
                        + "~"
                        + benefitComponentRetrieveResponse
                                .getBenefitMandateBO()
                                .getFundingArrangementDesc());
            this.setEffectiveness(benefitComponentRetrieveResponse
                    .getBenefitMandateBO().getMandateEffectivenessDesc());
            this.setText(benefitComponentRetrieveResponse.getBenefitMandateBO()
                    .getText());

            if ("FED".equals(benefitComponentRetrieveResponse
                    .getStandardBenefitBO().getMandateType())) {
                this.setStateCode("ALL~ALL");
            } else {
                if (null != benefitComponentRetrieveResponse
                        .getBenefitMandateBO().getBenefitStateCodeList())
                    this
                            .setStateCode(getTildaStringForStateCodeView(benefitComponentRetrieveResponse
                                    .getBenefitMandateBO()
                                    .getBenefitStateCodeList()));
            }
        } else if (benefitComponentRetrieveResponse != null
                && benefitComponentRetrieveResponse.isErrorMessageInList()) {
            this.setMandateCategory("");
            this.setCitationNumber("");
            this.setFundingArrangement("");
            this.setEffectiveness("");
            this.setText("");
            this.setStateCode("");
        }

    }


    /**
     * method to get the benefitComponentSessionObject.
     * 
     * @return benefitComponentSessionObject.
     */
    protected BenefitComponentSessionObject getBenefitComponentSessionObject() {
        BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
                .getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);

        if (benefitComponentSessionObject == null) {
            benefitComponentSessionObject = new BenefitComponentSessionObject();
            getSession().setAttribute(
                    WebConstants.BENEFIT_COMPONENT_SESSION_KEY,
                    benefitComponentSessionObject);
        }
        return benefitComponentSessionObject;
    }


    /**
     * @return Returns the mandateId.
     */
    public int getMandateId() {
        return mandateId;
    }


    /**
     * @param mandateId
     *            The mandateId to set.
     */
    public void setMandateId(int mandateId) {
        this.mandateId = mandateId;
    }


    /**
     * @return Returns the hiddenMandateId.
     */
    public String getHiddenMandateId() {
        return hiddenMandateId;
    }


    /**
     * @param hiddenMandateId
     *            The hiddenMandateId to set.
     */
    public void setHiddenMandateId(String hiddenMandateId) {
        this.hiddenMandateId = hiddenMandateId;
    }


    /**
     * @return Returns the saveAndAddFlag.
     */
    public boolean isSaveAndAddFlag() {
        return saveAndAddFlag;
    }


    /**
     * @param saveAndAddFlag
     *            The saveAndAddFlag to set.
     */
    public void setSaveAndAddFlag(boolean saveAndAddFlag) {
        this.saveAndAddFlag = saveAndAddFlag;
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
     * @return Returns the mandateEffDate.
     */
    public String getMandateEffDate() {
        return mandateEffDate;
    }


    /**
     * @param mandateEffDate
     *            The mandateEffDate to set.
     */
    public void setMandateEffDate(String mandateEffDate) {
        this.mandateEffDate = mandateEffDate;
    }


    /**
     * @return Returns the mandateExpDate.
     */
    public String getMandateExpDate() {
        return mandateExpDate;
    }


    /**
     * @param mandateExpDate
     *            The mandateExpDate to set.
     */
    public void setMandateExpDate(String mandateExpDate) {
        this.mandateExpDate = mandateExpDate;
    }


    /**
     * Returns the requiredDesription
     * 
     * @return boolean requiredDesription.
     */
    public boolean isRequiredDesription() {
        return requiredDesription;
    }


    /**
     * Sets the requiredDesription
     * 
     * @param requiredDesription.
     */
    public void setRequiredDesription(boolean requiredDesription) {
        this.requiredDesription = requiredDesription;
    }


    /**
     * Returns the text
     * 
     * @return String text.
     */
    public String getText() {
        if (null != text) {
            return text.trim();
        }
        return null;
    }


    /**
     * Sets the text
     * 
     * @param text.
     */
    public void setText(String text) {
        this.text = text;
    }


    /**
     * Returns the mandateCategory
     * 
     * @return String mandateCategory.
     */
    public String getMandateCategory() {
        return mandateCategory;
    }


    /**
     * Sets the mandateCategory
     * 
     * @param mandateCategory.
     */
    public void setMandateCategory(String mandateCategory) {
        this.mandateCategory = mandateCategory;
    }


    /**
     * Returns the mandateCategoryDesc
     * 
     * @return String mandateCategoryDesc.
     */
    public String getMandateCategoryDesc() {
        return mandateCategoryDesc;
    }


    /**
     * Sets the mandateCategoryDesc
     * 
     * @param mandateCategoryDesc.
     */
    public void setMandateCategoryDesc(String mandateCategoryDesc) {
        this.mandateCategoryDesc = mandateCategoryDesc;
    }


    /**
     * Returns the mandateCategoryId
     * 
     * @return String mandateCategoryId.
     */
    public String getMandateCategoryId() {
        return mandateCategoryId;
    }


    /**
     * Sets the mandateCategoryId
     * 
     * @param mandateCategoryId.
     */
    public void setMandateCategoryId(String mandateCategoryId) {
        this.mandateCategoryId = mandateCategoryId;
    }


    /**
     * Returns the requiredMandateCategory
     * 
     * @return boolean requiredMandateCategory.
     */
    public boolean isRequiredMandateCategory() {
        return requiredMandateCategory;
    }


    /**
     * Sets the requiredMandateCategory
     * 
     * @param requiredMandateCategory.
     */
    public void setRequiredMandateCategory(boolean requiredMandateCategory) {
        this.requiredMandateCategory = requiredMandateCategory;
    }


    /**
     * @return Returns the stateDescList.
     */
    public List getStateDescList() {
        return stateDescList;
    }


    /**
     * @param stateDescList
     *            The stateDescList to set.
     */
    public void setStateDescList(List stateDescList) {
        this.stateDescList = stateDescList;
    }


    /**
     * Returns the flag
     * 
     * @return String flag.
     */
    public String getFlag() {
        flag = "E";
        return flag;
    }


    /**
     * Sets the flag
     * 
     * @param flag.
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }


    /**
     * Returns the viewBenefitMandate
     * 
     * @return int viewBenefitMandate.
     */
    public int getViewBenefitMandate() {
        this.setViewFlag(true);
        this.loadBenefitMandate();
        this.setViewFlag(false);
        this.setBreadCrumbText(WebConstants.HEADER
                + "("
                + this.getStandardBenefitSessionObject()
                        .getStandardBenefitName() + ") >> View");
        return viewBenefitMandate;
    }


    /**
     * Sets the viewBenefitMandate
     * 
     * @param viewBenefitMandate.
     */
    public void setViewBenefitMandate(int viewBenefitMandate) {
        this.viewBenefitMandate = viewBenefitMandate;
    }


    /**
     * Returns the viewFlag
     * 
     * @return boolean viewFlag.
     */
    public boolean isViewFlag() {
        return viewFlag;
    }


    /**
     * Sets the viewFlag
     * 
     * @param viewFlag.
     */
    public void setViewFlag(boolean viewFlag) {
        this.viewFlag = viewFlag;
    }


    /**
     * method for printing the benefit mandate
     * 
     * @return int printBenefitMandate.
     */
    public int getPrintBenefitMandate() {
        this.setViewFlag(true);
        this.loadBenefitMandate();
        this.setViewFlag(false);
        return printBenefitMandate;
    }


    /**
     * Sets the printBenefitMandate
     * 
     * @param printBenefitMandate.
     */
    public void setPrintBenefitMandate(int printBenefitMandate) {
        this.printBenefitMandate = printBenefitMandate;
    }


    /**
     * Returns the formatedText
     * 
     * @return String formatedText.
     */
    public String getFormatedText() {
        return formatedText;
    }


    /**
     * Sets the formatedText
     * 
     * @param formatedText.
     */
    public void setFormatedText(String formatedText) {
        this.formatedText = WPDStringUtil.processNoteText(this.text);
    }
	/**
	 * @return Returns the printComponentBenefitMandate.
	 */
	public String getPrintComponentBenefitMandate() {
		this.loadBenefitMandateForView();
		return printComponentBenefitMandate;
	}
	/**
	 * @param printComponentBenefitMandate The printComponentBenefitMandate to set.
	 */
	public void setPrintComponentBenefitMandate(
			String printComponentBenefitMandate) {
		this.printComponentBenefitMandate = printComponentBenefitMandate;
	}
}