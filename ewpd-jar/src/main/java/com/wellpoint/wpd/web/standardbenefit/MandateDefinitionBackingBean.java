/*
 * Created on Feb 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.standardbenefit.bo.MandateDefinition;
import com.wellpoint.wpd.common.standardbenefit.bo.MandateDefinitionImpl;
import com.wellpoint.wpd.common.standardbenefit.request.LocateMandateListRequest;
import com.wellpoint.wpd.common.standardbenefit.response.LocateMandateListResponse;
import com.wellpoint.wpd.common.util.DateUtil;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author u13259
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MandateDefinitionBackingBean extends WPDBackingBean {
    private String effectiveDate = null;

    private String expiryDate = null;

    private String mandate = null;

    private String description = null;

    List mandateList = null;

    private boolean requiredEffectiveDate;

    private boolean requiredExpiryDate;

    private boolean requiredMandate;

    private boolean requiredDescription;

    private boolean disabledSaveAndAdd;

    private boolean disabledSave;

    private MandateDefinition mandateDefinition1 = null;

    private MandateDefinition mandateDefinition2 = null;

    List mandateListView = null;
    
    // mandate popuplist
    List mandateListPopUp = null;
    

    /**
     * @return Returns the mandateListPopUp.
     */
    public List getMandateListPopUp() {
        /* connect to the database and get the list of mandates from the mandate table */
    	// create the request
        LocateMandateListRequest locateMandateListRequest = (LocateMandateListRequest)
							this.getServiceRequest(ServiceManager.LOCATE_MANDATE_LIST);
        locateMandateListRequest.setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
    
    	// get the response
        LocateMandateListResponse locateMandateListResponse = 
        	(LocateMandateListResponse)executeService(locateMandateListRequest);
    	// get the list from response and copy it to the mandate list
        List mandateListFromResponse = locateMandateListResponse.getMandateList();
        // assign the responseList to backingBean property
        mandateListPopUp = mandateListFromResponse;
    	// return the mandate list
        return mandateListPopUp;
    }
    /**
     * @param mandateListPopUp The mandateListPopUp to set.
     */
    public void setMandateListPopUp(List mandateListPopUp) {
        this.mandateListPopUp = mandateListPopUp;
    }
    public MandateDefinitionBackingBean() {
        Date date = DateUtil.getDefaultEndDate();
        String expDate = WPDStringUtil.getStringDate(date);
        this.setExpiryDate(expDate);
        mandateDefinition1 = new MandateDefinitionImpl();
        mandateDefinition2 = new MandateDefinitionImpl();

    }

    public void saveAndAdd() {
        ArrayList validationMessages = null;
        validationMessages = validationProcess(validationMessages);
        if (null == validationMessages) {
            mandateDefinition1.setDescription(this.getDescription());
            mandateDefinition1.setEffectiveDate(this.getEffectiveDate());
            mandateDefinition1.setExpiryDate(this.getExpiryDate());
            mandateDefinition1.setMandate(this.getMandate());
            mandateList = new ArrayList(1);
            mandateList.add(mandateDefinition1);
            this.setDescription(null);
            this.setEffectiveDate(null);
            this.setExpiryDate(null);
            this.setMandate(null);
        }

    }

    public ArrayList validationProcess(ArrayList validationMessages) {

        if (("".equals(this.getEffectiveDate()))
                || ("".equals(this.getExpiryDate()))
                || ("".equals(this.getMandate()))
                || ("".equals(this.getDescription()))) {
            validationMessages = new ArrayList(1);
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            addAllMessagesToRequest(validationMessages);
            if ("".equals(this.getEffectiveDate())) {
                this.setRequiredEffectiveDate(true);
            } else {
                this.setRequiredEffectiveDate(false);
            }
            if ("".equals(this.getExpiryDate())) {
                this.setRequiredExpiryDate(true);
            } else {
                this.setRequiredExpiryDate(false);
            }
            if ("".equals(this.getMandate())) {
                this.setRequiredMandate(true);
            } else {
                this.setRequiredMandate(false);
            }
            if ("".equals(this.getDescription())) {
                this.setRequiredDescription(true);

            } else {
                this.setRequiredDescription(false);

            }

            return validationMessages;
        }
        if ("".equals(this.getExpiryDate())) {
            this.setRequiredExpiryDate(true);

        } else {
            this.setRequiredExpiryDate(false);
            if (!(WPDStringUtil.isValidDate(this.getEffectiveDate()))) {
                this.setRequiredEffectiveDate(true);
                validationMessages = new ArrayList(1);
                ErrorMessage errorMessage = new ErrorMessage(
                        WebConstants.INPUT_FORMAT_INVALID);
                errorMessage.setParameters(new String[] { "Effective Date" });
                validationMessages.add(errorMessage);
                addAllMessagesToRequest(validationMessages);
                return validationMessages;

            }
            if (!(WPDStringUtil.isValidDate(this.getExpiryDate()))) {
                this.setRequiredExpiryDate(true);
                validationMessages = new ArrayList(1);
                ErrorMessage errorMessage = new ErrorMessage(
                WebConstants.INPUT_FORMAT_INVALID);
                errorMessage.setParameters(new String[] { "Expiry Date" });
                validationMessages.add(errorMessage);

                addAllMessagesToRequest(validationMessages);
                return validationMessages;
            }
            Date effDate = WPDStringUtil.getDateFromString(this
                    .getEffectiveDate());
            Date expDate = WPDStringUtil
                    .getDateFromString(this.getExpiryDate());

            if (!(expDate.after(effDate))) {
                this.setRequiredExpiryDate(true);
                validationMessages = new ArrayList(1);
                validationMessages.add(new ErrorMessage(
                        WebConstants.EXPIRY_GREATER_EFFECTIVE_DATE));
                addAllMessagesToRequest(validationMessages);
                return validationMessages;
            } else {
                this.setRequiredExpiryDate(false);
            }
        }
        if ("".equals(this.getDescription())) {
            this.setRequiredDescription(true);

        } else {

            this.setRequiredDescription(false);
            String desc = this.getDescription();
            desc = desc.trim();
            char[] charDesc = desc.toCharArray();
            if (charDesc.length < 10 || charDesc.length > 250) {
                this.setRequiredDescription(true);
                validationMessages = new ArrayList(1);
                validationMessages.add(new ErrorMessage(
                        WebConstants.INVALID_DESCRIPTION));
                addAllMessagesToRequest(validationMessages);
                return validationMessages;
            } else {
                this.setRequiredDescription(false);
            }

        }
        return validationMessages;

    }

    public void save() {
        this.setDisabledSave(true);
        this.setDisabledSaveAndAdd(false);

        mandateDefinition1.setDescription(this.getDescription());
        mandateDefinition1.setEffectiveDate(this.getEffectiveDate());
        mandateDefinition1.setExpiryDate(this.getExpiryDate());
        mandateDefinition1.setMandate(this.getMandate());
        mandateList = new ArrayList(1);
        mandateList.add(mandateDefinition1);

    }

    public void edit() {

    }

    public void delete() {

    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns the effectiveDate.
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * @param effectiveDate The effectiveDate to set.
     */
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * @return Returns the expiryDate.
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate The expiryDate to set.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return Returns the mandate.
     */
    public String getMandate() {
        return mandate;
    }

    /**
     * @param mandate The mandate to set.
     */
    public void setMandate(String mandate) {
        this.mandate = mandate;
    }

    /**
     * @return Returns the mandateList.
     */
    public List getMandateList() {
        return mandateList;
    }

    /**
     * @param mandateList The mandateList to set.
     */
    public void setMandateList(List mandateList) {

        this.mandateList = mandateList;
    }

    /**
     * @return Returns the requiredEffectiveDate.
     */
    public boolean isRequiredEffectiveDate() {
        return requiredEffectiveDate;
    }

    /**
     * @param requiredEffectiveDate The requiredEffectiveDate to set.
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
     * @param requiredExpiryDate The requiredExpiryDate to set.
     */
    public void setRequiredExpiryDate(boolean requiredExpiryDate) {
        this.requiredExpiryDate = requiredExpiryDate;
    }

    /**
     * @return Returns the requiredMandate.
     */
    public boolean isRequiredMandate() {
        return requiredMandate;
    }

    /**
     * @param requiredMandate The requiredMandate to set.
     */
    public void setRequiredMandate(boolean requiredMandate) {
        this.requiredMandate = requiredMandate;
    }

    /**
     * Returns the requiredDescription
     * @return boolean requiredDescription.
     */

    public boolean isRequiredDescription() {
        return requiredDescription;
    }

    /**
     * Sets the requiredDescription
     * @param requiredDescription.
     */

    public void setRequiredDescription(boolean requiredDescription) {
        this.requiredDescription = requiredDescription;
    }

    /**
     * Returns the disabledSave
     * @return boolean disabledSave.
     */

    public boolean isDisabledSave() {
        return disabledSave;
    }

    /**
     * Sets the disabledSave
     * @param disabledSave.
     */

    public void setDisabledSave(boolean disabledSave) {
        this.disabledSave = disabledSave;
    }

    /**
     * Returns the disabledSaveAndAdd
     * @return boolean disabledSaveAndAdd.
     */

    public boolean isDisabledSaveAndAdd() {
        return disabledSaveAndAdd;
    }

    /**
     * Sets the disabledSaveAndAdd
     * @param disabledSaveAndAdd.
     */

    public void setDisabledSaveAndAdd(boolean disabledSaveAndAdd) {
        this.disabledSaveAndAdd = disabledSaveAndAdd;
    }

    /**
     * Returns the mandateListView
     * @return List mandateListView.
     */

    public List getMandateListView() {
        mandateListView = new ArrayList(2);

        mandateDefinition1.setDescription("Hello");
        mandateDefinition1.setEffectiveDate("12/12/2000");
        mandateDefinition1.setExpiryDate("12/31/9999");
        mandateDefinition1.setMandate("State Newada");
        mandateListView.add(mandateDefinition1);

        mandateDefinition2.setDescription("How r u");
        mandateDefinition2.setEffectiveDate("1/1/2000");
        mandateDefinition2.setExpiryDate("12/31/9999");
        mandateDefinition2.setMandate("State California");
        mandateListView.add(mandateDefinition2);

        return mandateListView;
    }

    /**
     * Sets the mandateListView
     * @param mandateListView.
     */

    public void setMandateListView(List mandateListView) {
        this.mandateListView = mandateListView;
    }
    public String loadMandateDefinition(){
        return "LoadMandateDefinition";
    }
}
