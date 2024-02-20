/*
 * ProductStructureGeneralInfoBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.productstructure;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.http.HttpServletRequest;

import com.wellpoint.wpd.business.framework.bo.StateFactory;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureAssociatedBenefit;
import com.wellpoint.wpd.common.productstructure.request.CheckoutProductStructureRequest;
import com.wellpoint.wpd.common.productstructure.request.ProductStructureNotesRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveComponentFromTreeRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveProductStructureRequest;
import com.wellpoint.wpd.common.productstructure.request.SaveProductStructureRequest;
import com.wellpoint.wpd.common.productstructure.response.ProductStructureNotesResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveComponentFromTreeResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.response.SaveProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;
import com.wellpoint.wpd.common.util.SessionCleanUp;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing bean for product structure edit page.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureGeneralInfoBackingBean extends
        ProductStructureBackingBean {

    private ProductStructureVO productStructureVO;

    /*private BenefitComponentVO benefitComponentVO;

    private BenefitComponentBO benefitComponentBO;*/

    private List associatedBenefitComponentList;

    /*private String selectedLineOfBusiness;

    private String selectedBusinessEntity;

    private String selectedBusinessGroup;*/

    private String lob;

    private String entity;

    private String group;

    private String effectiveDate;

    private String expiryDate;

    private String name;

    private String prodStructureName;

    private String description;

    private String createdUser;

    private Date creationDate;

    private String lastUpdatedUser;

    private Date lastUpdatedDate;

    private String state;

    private String status;

    private int version;

    private boolean requiredLob = false;

    private boolean requiredEntity = false;

    private boolean requiredGroup = false;

    private boolean requiredEffectiveDate = false;

    private boolean requiredExpiryDate = false;

    private boolean requiredName = false;

    private boolean requiredDesc = false;

    private boolean requiredStructureType = false;

    private boolean requiredMandateType = false;

    private boolean requiredState = false;

    //private boolean invalidDesc = false;

    private List validationMessages;

    private int productStructureId;

    private int selectedStructureId;

    private String selectedStructureName;

    private String selectedStructureVersion;

    private int productStructureIdForEdit;

    private String actionStringFromVersion;

    private String structureType;

    private String mandateType;

    private String oldMandateType;

    private String stateCode;

    private String oldStateCode;

    private String stateId;

    private String componentType;

    private boolean checkIn = false;

    //private boolean checkout = false;

    private String benefitTypeTab;

    private String ruleId;
    
    private String ruleType;

    private List lobList;

    private List entityList;

    private List groupList;

    //Attributes to check the change in values

    private String oldLob;

    private String oldEntity;

    private String oldGroup;

    private String oldEffectiveDate;

    private String oldExpiryDate;

    private boolean dateChange = false;

    private boolean domainChange = false;

    private boolean errorFlagForNullState = false;

    private List associatedNotesList = null;

    private int benefitComponentNoteId;

    //private List standardBenefitOverrideNotesList = null;

    private int noteId;

    private String noteNameHidden;

    private String dummyVar;

    private boolean copyFlag = false;

    private List associatedNotesPrintList = null;

    private boolean securityAccess;

    private String printBreadCrumbText;
    
    private HtmlPanelGrid displayPanel;
    
    private HtmlPanelGrid panel;
    
    private HtmlPanelGrid benefitHeaderViewPanel = new HtmlPanelGrid();
    
    private Map hiddenValBenefitVisible = new HashMap();
    
    private Map hiddenValBenefitId = new HashMap();
    
    private Map hiddenValBenefitName = new HashMap();
    
    private Map hiddenPrevValBenefitVisible= new HashMap();
    
    private HtmlPanelGrid savePanel;
    
    private boolean showHidden;
    
    private List benefitDetailsList;
    
    private String benefitCompnentHideFlag;
    
    private HtmlPanelGrid printPanel;
    
    private HtmlPanelGrid printHeaderPanel;
    
    private String printProductStructureAssociatedBenefits;
    
    private boolean saveButton = true;
    
    private boolean errorMessage;
    
    private String loadAssociatedBenefits;
    
    private String productStructureType;
    
    private boolean noFieldSelected = false;
    
    private boolean hasValidationErrors;
    
    private String panelData = "";
    
    //CR-BenefitComponent Version added
    
    private int bnftCmpntVersion;
    private boolean isDataPresent = false;
    private boolean notesRetrieved = false;
    
    //September release change added new field 'Product Family'
    
    private String productFamily;
    
    private String productFamilyForView;    
	
    private boolean familyInvalid = false;
//CARS START
    private boolean requiredBusinessUnit = false;
    
    private String marketBusinessUnit;

    private List marketBusinessUnitList;

    private String oldMarketBusinessUnit;
//CARS END
    //ICD10 --getting BC ID from session
    private int benefitCompntId;
    /**
     * constructor.
     */
    public ProductStructureGeneralInfoBackingBean() {
        super();
        this.effectiveDate = "01/01/1900";
        this.expiryDate = "12/31/9999";
        setBreadCrumbTextForPS();
        this.lob = "ALL~ALL";
        this.entity = "ALL~ALL";
        this.group = "ALL~ALL";
        //CARS START
        this.marketBusinessUnit = "ALL~ALL";
        //CARS END
    }


    /**
     * To check mnadatory fields are present.
     * 
     * @return boolean.
     */
    private boolean validateMandatoryFields() {
        validationMessages = new ArrayList(9);
        boolean requiredField = false;
        if (null == this.lob || "".equals(this.lob)) {
            requiredLob = true;
            requiredField = true;
        }
        if (null == this.entity || "".equals(this.entity)) {
            requiredEntity = true;
            requiredField = true;
        }
        if (null == this.group || "".equals(this.group)) {
            requiredGroup = true;
            requiredField = true;
        }
        //CARS START
        //Validation for market business unit
        if (null == this.marketBusinessUnit || "".equals(this.marketBusinessUnit)) {
            requiredBusinessUnit = true;
            requiredField = true;
        }
        //CARS END
        if (null == this.effectiveDate || "".equals(this.effectiveDate)) {
            requiredEffectiveDate = true;
            requiredField = true;
        }
        if (null == this.expiryDate || "".equals(this.expiryDate)) {
            requiredExpiryDate = true;
            requiredField = true;
        }
        if (null == this.name || "".equals(this.name)) {
            requiredName = true;
            requiredField = true;
        }
    //start sepetember release change new field product family included
        if (null == this.productFamily || "".equals(this.productFamily)) {
        	familyInvalid = true;
            requiredField = true;
        }
    //End
        if (null == this.structureType || "".equals(this.structureType)) {
            requiredStructureType = true;
            requiredField = true;
        }
        if (this.structureType.equalsIgnoreCase("Mandate")) {
            if (null == this.mandateType || "".equals(this.mandateType)) {
                requiredMandateType = true;
                requiredField = true;
            }
        }
        if (this.structureType.equalsIgnoreCase("Mandate")
                && (this.mandateType.equals("ET") || this.mandateType
                        .equals("ST"))) {
            if (null == this.stateCode || "".equals(this.stateCode)) {
                requiredState = true;
                requiredField = true;
            }
        }
        if (null == this.description || "".equals(this.description)) {
            requiredDesc = true;
            requiredField = true;
        }
        if (this.errorFlagForNullState == true) {
            this.setStateCode("");
            this.setOldStateCode("");
            validationMessages.add(new ErrorMessage(
                    WebConstants.BUSINESS_DOMAIN_CHANGED_PLEASE_CHANGE_STATE));
            requiredField = true;
            this.setErrorFlagForNullState(false);
        }
        if (requiredField) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            addAllMessagesToRequest(validationMessages);
         
            return false;
        }
        return true;
    }


    /**
     * 
     * @param attribute
     */
    private void removeValueInSession(String attribute) {
        if (null != getSession().getAttribute(attribute)) {
            getSession().removeAttribute(attribute);
        }
    }


    /**
     * To remove value stored in session for tree.
     *  
     */
    private void removeValueInSessionForTree() {
        removeValueInSession("productStructureTreeBackingBean");
        removeValueInSession("PRODUCT_STRUCTURE_SESSION_TREE_STATE");
    }


    /**
     * Method for saving benefit components.
     * 
     * @return String.
     */
    public String saveGeneralInfo() {
    	
        getRequest().setAttribute("RETAIN_Value", "");
        
        Logger.logInfo("Entering the method for saving general info");
        String copyString = "false";
        if (this.structureType.equalsIgnoreCase("Standard")) {
            this.mandateType = "";
            this.stateCode = "";
        }
        if (null == this.mandateType || "".equals(this.mandateType)) {
            this.stateCode = "";
        }
        if (null != this.mandateType && this.mandateType.equals("FED"))
            this.stateCode = "ALL";

        if (isValidProductStructure()) {
            SaveProductStructureResponse saveProductStructureResponse = null;
            SaveProductStructureRequest saveProductStructureRequest = getSaveProductStructureRequest();
            saveProductStructureRequest.setActionFromBC(false);
            if (getActionFromSession() != null
                    && getActionFromSession().equals("COPY")) {
                copyString = getActionFromSession();
                saveProductStructureRequest
                        .setAction(SaveProductStructureRequest.COPY_PRODUCT_STRUCTURE);
                setDeleteBenefitComponentFlag(saveProductStructureRequest);
            } else {
                if (0 == getIdFromSession()) {
                    this.setActionToSession("CREATE");
                    saveProductStructureRequest
                            .setAction(SaveProductStructureRequest.CREATE_PRODUCT_STRUCTURE);
                } else {
                    saveProductStructureRequest
                            .setAction(SaveProductStructureRequest.UPDATE_PRODUCT_STRUCTURE);
                    ProductStructureVO productStructureVO = new ProductStructureVO();
                    saveProductStructureRequest
                            .setOldKeyproductStructureBO(getProductStructureFromSession(productStructureVO));
                    setDeleteBenefitComponentFlag(saveProductStructureRequest);

                }
            }

            saveProductStructureResponse = (SaveProductStructureResponse) this
                    .executeService(saveProductStructureRequest);
            if(null == saveProductStructureResponse){
                /*
                 * Checking Too many domain combinations
                 */
                    if(this.copyFlag){
                        this.setCopyFlag(true);
                    }
                    this.validationMessages.add(new ErrorMessage(
                            WebConstants.MSG_NOT_SUPPORT_MANY_DOMAIN_ATTRIBUTES));
                    addAllMessagesToRequest(this.validationMessages);
                    return "";
            }
            Logger.logInfo("Returning the method for saving general info");
            if (saveProductStructureResponse != null
                    && saveProductStructureResponse.isSuccess()) {
            	
            // 	Code change for product structure tree rendering optimization
   	         super.updateTreeStructure();

                if (copyString.equals("COPY")) {
                    setActionToSession("EDIT");
                }
                setValuesToBackingBeanFromResponse(saveProductStructureResponse);
                if (saveProductStructureResponse.getProductStructure() != null) {
                    setProductStructureSessionObject(saveProductStructureResponse
                            .getProductStructure());
                }
               List messageList= saveProductStructureResponse.getMessages();
                addAllMessagesToRequest(messageList);
                this.setBreadCrumbTextForEdit();
                if (this.mandateType.equals("FED"))
                    this.stateCode = "";
                return "success";
            }
        }
        return "";
    }


    /**
     * Method to set the Flag to delete Benefit Component which doesn't matches
     * the changes made to the Product Structure.
     * 
     * @return saveProductStructureRequest.
     */
    private SaveProductStructureRequest setDeleteBenefitComponentFlag(
            SaveProductStructureRequest saveProductStructureRequest) {
        if (!(saveProductStructureRequest.getProductStructureVO()
                .getStructureType().equals(getStructureTypeFromSession()))) {
            saveProductStructureRequest.setDeleteBenefitComponent(true);
        } else if (!("".equals(this.mandateType))){
            if (!(this.mandateType.equals(getMandateTypeFromSession()))) {
                saveProductStructureRequest.setDeleteBenefitComponent(true);
            } else if ("" != this.stateCode) {
                if (!(this.stateId.equals(getStateIdFromSession()))) {
                    saveProductStructureRequest.setDeleteBenefitComponent(true);
                }
            }
        }
        List domainList = getBusinessDomainFromSession();
        /*List lobListFromSession = BusinessUtil.getLobList(domainList);
        List busiessEntityListFromSession = BusinessUtil
                .getbusinessEntityList(domainList);
        List businessGroupListFromSession = BusinessUtil
                .getBusinessGroupList(domainList);
        List oldBusinessEntityValues = new ArrayList();
        List oldBusinessGrpValues = new ArrayList();
        List oldLOBValues = new ArrayList();*/

        //      BusinessEntityValidation
        /*
         * if (null != getBusinessDomainFromSession() &&
         * getBusinessDomainFromSession().size()!=0) { for(int i =0 ; i <
         * getBusinessDomainFromSession().size();i++){ Domain entityValues =
         * (Domain)getBusinessDomainFromSession().get(i); String businessEnity =
         * entityValues.getBusinessEntity();
         * oldBusinessEntityValues.add(businessEnity); } }
         * 
         * if(null!= oldBusinessEntityValues &&
         * oldBusinessEntityValues.size()!=0){ for(int i =0 ; i <
         * oldBusinessEntityValues.size();i++){ String
         * businessEntityValueFromSession =
         * oldBusinessEntityValues.get(i).toString(); for(int j=0; j <
         * this.entityList.size();j++){ if(!("ALL".equals(
         * this.entityList.get(j)))) {
         * if(!(businessEntityValueFromSession.equals(
         * this.entityList.get(j)))){
         * saveProductStructureRequest.setDeleteBenefitComponent(true); } } } } }
         *  // BusinessGroup Validation
         * 
         * if (null != getBusinessDomainFromSession() &&
         * getBusinessDomainFromSession().size()!=0) { for(int i =0 ; i <
         * getBusinessDomainFromSession().size();i++){ Domain busGrpValues =
         * (Domain)getBusinessDomainFromSession().get(i); String businessGrp =
         * busGrpValues.getBusinessGroup();
         * oldBusinessGrpValues.add(businessGrp); } }
         * 
         * if(null!= oldBusinessGrpValues && oldBusinessGrpValues.size()!=0){
         * for(int i =0 ; i < oldBusinessGrpValues.size();i++){ String
         * businessGrpValueFromSession = oldBusinessGrpValues.get(i).toString();
         * for(int j=0; j < this.groupList.size();j++,i++){
         * if(!("ALL".equals(this.groupList.get(j)))) {
         * if(!(businessGrpValueFromSession.equals(this.groupList.get(j)))){
         * saveProductStructureRequest.setDeleteBenefitComponent(true); } } } } }
         *  // LOB Validation
         * 
         * if (null != getBusinessDomainFromSession() &&
         * getBusinessDomainFromSession().size()!=0) { for(int i =0 ; i <
         * getBusinessDomainFromSession().size();i++){ Domain lobValues =
         * (Domain)getBusinessDomainFromSession().get(i); String lineOfBusiness =
         * lobValues.getLineOfBusiness(); oldLOBValues.add(lineOfBusiness); } }
         * 
         * if(null!= oldLOBValues && oldLOBValues.size()!=0){ for(int i =0 ; i <
         * oldLOBValues.size();i++){ String lobValueFromSession =
         * oldLOBValues.get(i).toString(); for(int j=0; j < this.lobList
         * .size();j++){ if(!("ALL".equals(this.lobList .get(j)))) {
         * if(!(lobValueFromSession.equals(this.lobList .get(j)))){
         * saveProductStructureRequest.setDeleteBenefitComponent(true); } } } } }
         */

        if (this.dateChange)
            saveProductStructureRequest.setDateChange(true);

        if (this.domainChange)
            saveProductStructureRequest.setDomainChange(true);

        return saveProductStructureRequest;

    }


    /**
     * This method calls the retrieveBenefitComponent method.
     * 
     * @return void
     */

    public String getBenefitComponentGenInfo() {
        retrieveBenefitComponent();
        return "";
    }


    /**
     * Setter method for benefitcomponentgeneralinfo.
     *  
     */
    public void setBenefitComponentGenInfo() {
    }


    /**
     * This method retrieves the benefit component from the tree for display in
     * the view page.
     * 
     * @return String
     */
    public String retrieveBenefitComponent() {
        Logger.logInfo("Entering the method for retrieving benefit component");
        RetrieveComponentFromTreeRequest retrieveComponentFromTreeRequest = new RetrieveComponentFromTreeRequest();
        RetrieveComponentFromTreeResponse retrieveComponentFromTreeResponse = null;
        String cmpntId = (String) getSession().getAttribute("BENEFIT_COMP_ID");
        int bnftCmpntId = Integer.parseInt(cmpntId);
        retrieveComponentFromTreeRequest.setBenefitComponentId(bnftCmpntId);
        ProductStructureVO productStructureVO = new ProductStructureVO();
        productStructureVO = getProductStructureFromSession(productStructureVO);
        this.prodStructureName = productStructureVO.getProductStructureName();
        this.setBenefitComponentIdToSession(bnftCmpntId);
        //int bc = this.getBenefitComponentIdFromSession();
        retrieveComponentFromTreeRequest
                .setProductStructure(productStructureVO);
        retrieveComponentFromTreeResponse = (RetrieveComponentFromTreeResponse) this
                .executeService(retrieveComponentFromTreeRequest);
        BenefitComponentBO benefitComponent = retrieveComponentFromTreeResponse
                .getBenefitComponent();
        SetValuesToBackingForBenefitComponent(benefitComponent);
        DomainDetail domainDetail = retrieveComponentFromTreeResponse
                .getDetail();
        if (domainDetail != null) {
            this.lob = WPDStringUtil.getTildaString(domainDetail
                    .getLineOfBusiness());
            this.entity = WPDStringUtil.getTildaString(domainDetail
                    .getBusinessEntity());
            this.group = WPDStringUtil.getTildaString(domainDetail
                    .getBusinessGroup());
            //CARS START
            //Set the market business unit value from business domain and set it to the backing bean value.
            this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
                    .getMarketBusinessUnit());
            //CARS END
        }
        String action = (String) getSession().getAttribute("ACTION");
        //this.setActionToSession(action);
        Logger.logInfo("Returning the method for retrieving benefit component");
        if (null != getActionFromSession()
                && (getActionFromSession().equals("VIEW"))) {
            this.setBreadCrumbTextForView();
            return action;
        } else {
            this.setBreadCrumbTextForEdit();
            return action;
        }

    }


    /**
     * @param benefitComponent
     */
    private void SetValuesToBackingForBenefitComponent(
            BenefitComponentBO benefitComponent) {
        this.name = benefitComponent.getName();
        this.componentType = benefitComponent.getComponentType();
        this.mandateType = benefitComponent.getMandateDesc();
        this.stateCode = benefitComponent.getStateDesc();
        if (null != benefitComponent.getRuleList() && !(benefitComponent.getRuleList().isEmpty())) {
            List refId = benefitComponent.getRuleList();
            if(null != benefitComponent.getRuleNameList() && !(benefitComponent.getRuleNameList().isEmpty())){
	            List refNam = benefitComponent.getRuleNameList();
	            List refType = benefitComponent.getRuleTypeList();
	            //String reference = convertListtoTilda(refNam, refId);
	            for (int i = 0; i < refId.size(); i++) {
	            	//this.setRuleId(reference);
	            	String referenceName = (String)refNam.get(i);
	            	String ruleType = (String)refType.get(i);
	            	if(null != referenceName){
		            	this.setRuleId(refId.get(i).toString() + '-'
		                        + refNam.get(i).toString());
		            	this.setRuleType(ruleType);
	            	}
	            	else{
	            		this.setRuleId(refId.get(i).toString());
	            		this.setRuleType(ruleType);
	            	}
	            }
            }
        }else{
        	this.setRuleId(null);
        }
        
        this.description = benefitComponent.getDescription();
        this.effectiveDate = WPDStringUtil.convertDateToString(benefitComponent
                .getEffectiveDate());
        this.expiryDate = WPDStringUtil.convertDateToString(benefitComponent
                .getExpiryDate());
        this.createdUser = benefitComponent.getCreatedUser();
        this.creationDate = benefitComponent.getCreatedTimestamp();
        this.lastUpdatedDate = benefitComponent.getLastUpdatedTimestamp();
        this.lastUpdatedUser = benefitComponent.getLastUpdatedUser();
        this.bnftCmpntVersion = benefitComponent.getVersion();
    }


    /**
     * converts List to tilda separated string
     * 
     * @return String.
     */
    public String convertListtoTilda(List idlist, List nameList) {
        if (idlist == null || nameList == null)
            return "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < idlist.size(); i++) {
            if (i != 0)
                buffer.append("~");
            buffer.append(idlist.get(i) + "~" + nameList.get(i));
        }
        return buffer.toString();
    }


    /**
     * 
     * @return String.
     */
    public String getGenInfoForPrint() {
        loadGeneralInfo();
        return "";
    }


    /**
     * Setter for genInfoForPrint.
     *  
     */
    public void setGenInfoForPrint() {

    }


    /**
     * 
     * @return String.
     */
    public String loadGeneralInfo() {
        Logger.logInfo("Entering the method for loading general Info");
        RetrieveProductStructureResponse retrieveProductStructureResponse = null;
        RetrieveProductStructureRequest retrieveProductStructureRequest = new RetrieveProductStructureRequest();
        ProductStructureVO productStructureVO = new ProductStructureVO();
        productStructureVO = this
                .getProductStructureFromSession(productStructureVO);
        retrieveProductStructureRequest
                .setProductStructureVO(productStructureVO);
        retrieveProductStructureResponse = (RetrieveProductStructureResponse) this
                .executeService(retrieveProductStructureRequest);
        setValuesToBackingBeanForView(retrieveProductStructureResponse);
        Logger.logInfo("Returning the method for loading general Info");
        return "success";
    }


    /**
     * 
     * @return String.
     */
    public String getGeneralInfo() {
        RetrieveProductStructureResponse retrieveProductStructureResponse = null;
        RetrieveProductStructureRequest retrieveProductStructureRequest = getRetrieveProductStructureRequest();
        if (retrieveProductStructureRequest == null) {
            return "";
        }
        this.removeValueInSessionForTree();
        retrieveProductStructureResponse = (RetrieveProductStructureResponse) this
                .executeService(retrieveProductStructureRequest);
        if (null != retrieveProductStructureResponse
                && retrieveProductStructureResponse.isSuccess()) {
            setValuesToBackingBeanForView(retrieveProductStructureResponse);
            this
                    .setProductStructureSessionObject(retrieveProductStructureResponse
                            .getProductStructureBO());        
        }
        if(null != this.getNameFromSession()){
        	this.setBreadCrumbTextForView();
        }
        return "";

    }


    /**
     * Setter for general info.
     *  
     */
    public void setGeneralInfo() {
    }


    /**
     * Method to check Date & Description
     * 
     * @return boolean.
     */
    private boolean isValidProductStructure() {
        boolean valid = false;
        Date effectiveDate = null;
        Date expiryDate = null;
        if (validateMandatoryFields()) {
            valid = true;
            if (!(WPDStringUtil.isValidDate(this.effectiveDate))) {
                ErrorMessage errorMessage = new ErrorMessage(
                        WebConstants.INPUT_FORMAT_INVALID);
                errorMessage.setParameters(new String[] { "Effective Date" });
                validationMessages.add(errorMessage);
                requiredEffectiveDate = true;
                valid = false;
            }
            if (!(WPDStringUtil.isValidDate(this.expiryDate))) {

                ErrorMessage errorMessage = new ErrorMessage(
                        WebConstants.INPUT_FORMAT_INVALID);
                errorMessage.setParameters(new String[] { "Expiry Date" });
                requiredExpiryDate = true;
                validationMessages.add(errorMessage);
                valid = false;
            }
            if (WPDStringUtil.isValidDate(this.effectiveDate)) {
                effectiveDate = WPDStringUtil
                        .getDateFromString(this.effectiveDate);
                if (effectiveDate.compareTo(WPDStringUtil
                        .getDefaultEffectiveDate()) < 0) {
                    validationMessages
                            .add(new ErrorMessage(
                                    WebConstants.EFFECTIVEDATE_NOT_GRETER_THAN_DEFAULTDATE));
                    requiredEffectiveDate = true;
                    valid = false;
                }
            }
            if (WPDStringUtil.isValidDate(this.expiryDate)) {
                expiryDate = WPDStringUtil.getDateFromString(this.expiryDate);
                if (expiryDate.compareTo(WPDStringUtil
                        .getDefaultEffectiveDate()) < 0) {
                    validationMessages
                            .add(new ErrorMessage(
                                    WebConstants.EXPIRYEDATE_NOT_GRETER_THAN_DEFAULTDATE));
                    requiredExpiryDate = true;
                    valid = false;
                }
            }
            if (valid) {
                if (effectiveDate.compareTo(expiryDate) >= 0) {
                    validationMessages.add(new ErrorMessage(
                            WebConstants.EXPIRY_GREATER_EFFECTIVE_DATE));
                    valid = false;
                }
            }
            
            String prodStructureName = this.getName().trim();
            if(prodStructureName.length() > 30){
            	validationMessages.add(new ErrorMessage(
                        WebConstants.NAME_SIZE_INVALID));
                valid = false;
            }
            
            String desc = this.getDescription();
            desc = desc.trim();
            char[] charDesc = desc.toCharArray();
            if (charDesc.length < 10 || charDesc.length > 250) {
                this.setRequiredDesc(true);
                validationMessages.add(new ErrorMessage(
                        WebConstants.INVALID_DESCRIPTION));
                valid = false;
            }
        }
        addAllMessagesToRequest(validationMessages);
        return valid;
    }


    /**
     * Method to check
     * 
     * @return
     */
    /*private boolean isDateLessThanCurrentDate(Date date, Date currentDate) {
        boolean valid = false;
        Calendar calendar = getCalendar(currentDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        GregorianCalendar newGregorianCalendar = new GregorianCalendar(year,
                month, day);
        Date currDate = newGregorianCalendar.getTime();
        if (date.before(currDate)) {
            valid = true;
        }
        return valid;
    }*/


    /**
     * @return
     */
    private Calendar getCalendar(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.clear();
        cal.setTime(date);
        return cal;
    }


    /**
     * Method to create request for Create and Edit
     * 
     * @return SaveProductStructureRequest
     */
    private SaveProductStructureRequest getSaveProductStructureRequest() {
        SaveProductStructureRequest createProductStructureRequest = (SaveProductStructureRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT_STRUCTURE);
        createProductStructureVO();
        createProductStructureRequest.setProductStructureVO(productStructureVO);
        createProductStructureRequest.setDeleteBenefitComponent(false);
        return createProductStructureRequest;
    }


    /**
     * Method to create ProductStructureVO
     * 
     * @return productStructureVO
     */
    private void createProductStructureVO() {
        productStructureVO = new ProductStructureVO();
        lobList = WPDStringUtil.getListFromTildaString(this.getLob(), 2, 2, 2);

        entityList = WPDStringUtil.getListFromTildaString(this.getEntity(), 2,
                2, 2);
        groupList = WPDStringUtil.getListFromTildaString(this.getGroup(), 2, 2,
                2);
        //CARS START
        //Converts to til da seperated to MBU list
        marketBusinessUnitList = WPDStringUtil.getListFromTildaString(this.getMarketBusinessUnit(), 2, 2,
                2);
        //Sets the MBU list to Value object.
        productStructureVO.setMarketBusinessUnit(marketBusinessUnitList);
        //CARS END
        productStructureVO.setProductStructureId(this.getIdFromSession());
        productStructureVO.setParentProductStructureId(this
                .getParentIdFromSession());
        productStructureVO.setLineOfBusiness(lobList);
        productStructureVO.setBusinessEntity(entityList);
        productStructureVO.setBusinessGroup(groupList);
        productStructureVO
                .setProductStructureName(this.getName().toUpperCase());
 //start september release
//      expects String List
		List familyList = WPDStringUtil.getListFromTildaString(
				this.productFamily, 2, 2, 2);
		productStructureVO.setProductFamilyId((String) familyList.get(0));
//end 
        productStructureVO.setDescription(this.getDescription());
        productStructureVO.setVersion(this.getVersion());
        productStructureVO.setStatus(this.getStatus());
        if (this.structureType.equalsIgnoreCase("Standard"))
            productStructureVO.setStructureType(WebConstants.STD_TYPE);
        else
            productStructureVO.setStructureType(WebConstants.MNDT_TYPE);
        productStructureVO.setMandateType(this.getMandateType());
        if (!("".equals(this.stateCode))) {
            String[] arrayTilda = this.stateCode.split("~");
            if (arrayTilda.length == 2) {
                this.stateId = (arrayTilda[0]);
            } else {
                this.stateId = this.stateCode;
            }

        }
        productStructureVO.setStateId(this.stateId);

        if (WPDStringUtil.isValidDate(this.getEffectiveDate())) {
            productStructureVO.setEffectiveDate(WPDStringUtil
                    .getDateFromString(this.getEffectiveDate()));
        }
        if (WPDStringUtil.isValidDate(this.getExpiryDate())) {
            productStructureVO.setExpiryDate(WPDStringUtil
                    .getDateFromString(this.getExpiryDate()));
        }
        productStructureVO.setAssociatedBenefitComponentList(this
                .getAssociatedBenefitComponentList());

    }


    /**
     * To set the values to Backing Bean
     * 
     * @param saveProductStructureResponse
     * @return
     */
    private void setValuesToBackingBeanFromResponse(
            SaveProductStructureResponse saveProductStructureResponse) {
        if (saveProductStructureResponse != null
                && saveProductStructureResponse.getProductStructure() != null) {
            this.version = saveProductStructureResponse.getProductStructure()
                    .getVersion();
            this.createdUser = saveProductStructureResponse
                    .getProductStructure().getCreatedUser();
            Date createdDate = saveProductStructureResponse
                    .getProductStructure().getCreatedTimestamp();
            if (createdDate != null) {
                /*String creationDateString = WPDStringUtil
                        .getStringDate(createdDate);*/
                this.creationDate = createdDate;
            }
            this.lastUpdatedUser = saveProductStructureResponse
                    .getProductStructure().getLastUpdatedUser();
            Date updatedDate = saveProductStructureResponse
                    .getProductStructure().getLastUpdatedTimestamp();
            if (updatedDate != null) {
                /*String updationDateString = WPDStringUtil
                        .getStringDate(updatedDate);*/
                this.lastUpdatedDate = updatedDate;
            }
            this.effectiveDate = WPDStringUtil
                    .convertDateToString(saveProductStructureResponse
                            .getProductStructure().getEffectiveDate());
            this.oldEffectiveDate = WPDStringUtil
                    .convertDateToString(saveProductStructureResponse
                            .getProductStructure().getEffectiveDate());
            this.expiryDate = WPDStringUtil
                    .convertDateToString(saveProductStructureResponse
                            .getProductStructure().getExpiryDate());
            this.oldExpiryDate = WPDStringUtil
                    .convertDateToString(saveProductStructureResponse
                            .getProductStructure().getExpiryDate());
            if (saveProductStructureResponse.getProductStructure().getState() != null)
                this.state = saveProductStructureResponse.getProductStructure()
                        .getState().getState();
            this.status = saveProductStructureResponse.getProductStructure()
                    .getStatus();
            this.productStructureId = saveProductStructureResponse
                    .getProductStructure().getProductStructureId();
            this.name = saveProductStructureResponse.getProductStructure()
                    .getProductStructureName();
            // Sept release -product family
            this.productFamily =  saveProductStructureResponse.getProductStructure().getProductFamilyDesc() +
			"~"+ saveProductStructureResponse.getProductStructure().getProductFamilyId();
            if (saveProductStructureResponse.getProductStructure()
                    .getStructureType().equalsIgnoreCase("STANDARD"))
                this.structureType = "Standard";
            else
                this.structureType = "Mandate";
            if (null == saveProductStructureResponse.getProductStructure()
                    .getMandateType())
                this.mandateType = "";
            else {
                this.mandateType = saveProductStructureResponse
                        .getProductStructure().getMandateType();
                this.oldMandateType = saveProductStructureResponse
                        .getProductStructure().getMandateType();

            }
            if (null == saveProductStructureResponse.getProductStructure()
                    .getStateId())
                this.stateCode = "";
            else if (this.mandateType.equals("FED"))
                this.stateCode = "ALL";
            else {
                this.stateCode = saveProductStructureResponse
                        .getProductStructure().getStateId()
                        + "~"
                        + saveProductStructureResponse.getProductStructure()
                                .getStateDesc();
            }
            this.oldStateCode = this.stateCode;
            DomainDetail domainDetail = saveProductStructureResponse
                    .getDomainDetail();
            if (domainDetail != null) {
                this.lob = WPDStringUtil.getTildaString(domainDetail
                        .getLineOfBusiness());
                this.oldLob = WPDStringUtil.getTildaString(domainDetail
                        .getLineOfBusiness());
                this.entity = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessEntity());
                this.oldEntity = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessEntity());
                this.group = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessGroup());
                this.oldGroup = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessGroup());
                //CARS START 
                this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
                        .getMarketBusinessUnit());
                this.oldMarketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
                        .getMarketBusinessUnit());
                //CARS END
            }
            this.getProductStructureSessionObject().setStructureType(
                    saveProductStructureResponse.getProductStructure()
                            .getStructureType());
        }
    }


    /**
     * This method is used to set values to backing bean for view
     * 
     * @param retrieveProductStructureResponse
     */

    private void setValuesToBackingBeanForView(
            RetrieveProductStructureResponse retrieveProductStructureResponse) {

        if (retrieveProductStructureResponse != null
                && retrieveProductStructureResponse.getProductStructureBO() != null) {

            DomainDetail domainDetail = retrieveProductStructureResponse
                    .getDomainDetail();
            if (domainDetail != null) {
                this.lob = WPDStringUtil.getTildaString(domainDetail
                        .getLineOfBusiness());
                this.oldLob = WPDStringUtil.getTildaString(domainDetail
                        .getLineOfBusiness());
                this.entity = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessEntity());
                this.oldEntity = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessEntity());
                this.group = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessGroup());
                this.oldGroup = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessGroup());
                //CARS START
                //Setting the response object to market business unit object.
                this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
                        .getMarketBusinessUnit());
                this.oldMarketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
                        .getMarketBusinessUnit());
                //CARS END
            }
            this.effectiveDate = WPDStringUtil
                    .convertDateToString(retrieveProductStructureResponse
                            .getProductStructureBO().getEffectiveDate());
            this.oldEffectiveDate = WPDStringUtil
                    .convertDateToString(retrieveProductStructureResponse
                            .getProductStructureBO().getEffectiveDate());
            this.expiryDate = WPDStringUtil
                    .convertDateToString(retrieveProductStructureResponse
                            .getProductStructureBO().getExpiryDate());
            this.oldExpiryDate = WPDStringUtil
                    .convertDateToString(retrieveProductStructureResponse
                            .getProductStructureBO().getExpiryDate());
            this.name = retrieveProductStructureResponse
                    .getProductStructureBO().getProductStructureName();
//          Sept release -product family
            this.productFamily =  retrieveProductStructureResponse.getProductStructureBO().getProductFamilyDesc() +
			"~"+ retrieveProductStructureResponse.getProductStructureBO().getProductFamilyId();
            this.productFamilyForView = retrieveProductStructureResponse.getProductStructureBO().getProductFamilyId();
            this.description = retrieveProductStructureResponse
                    .getProductStructureBO().getDescription();
            this.createdUser = retrieveProductStructureResponse
                    .getProductStructureBO().getCreatedUser();
            Date createdDate = retrieveProductStructureResponse
                    .getProductStructureBO().getCreatedTimestamp();
            if (createdDate != null) {
                /*String creationDateString = WPDStringUtil
                        .getStringDate(createdDate);*/
                this.creationDate = createdDate;
            }
            this.lastUpdatedUser = retrieveProductStructureResponse
                    .getProductStructureBO().getLastUpdatedUser();
            Date updatedDate = retrieveProductStructureResponse
                    .getProductStructureBO().getLastUpdatedTimestamp();
            if (updatedDate != null) {
                /*String updationDateString = WPDStringUtil
                        .getStringDate(updatedDate);*/
                this.lastUpdatedDate = updatedDate;
            }
            if (retrieveProductStructureResponse.getProductStructureBO()
                    .getState() != null) {
                this.state = retrieveProductStructureResponse
                        .getProductStructureBO().getState().getState();
            }
            this.status = retrieveProductStructureResponse
                    .getProductStructureBO().getStatus();
            this.version = retrieveProductStructureResponse
                    .getProductStructureBO().getVersion();
            if (retrieveProductStructureResponse.getProductStructureBO()
                    .getStructureType().equalsIgnoreCase("STANDARD"))
                this.structureType = "Standard";
            else
                this.structureType = "Mandate";
            if (null == retrieveProductStructureResponse
                    .getProductStructureBO().getMandateType())
                this.mandateType = "";
            else {
                this.mandateType = retrieveProductStructureResponse
                        .getProductStructureBO().getMandateType();
                this.oldMandateType = retrieveProductStructureResponse
                        .getProductStructureBO().getMandateType();
            }

            if (null == retrieveProductStructureResponse
                    .getProductStructureBO().getStateId())
                this.stateCode = "";
            else if (this.mandateType.equals("FED"))
                this.stateCode = "ALL";
            else {
                this.stateCode = retrieveProductStructureResponse
                        .getProductStructureBO().getStateId()
                        + "~"
                        + retrieveProductStructureResponse
                                .getProductStructureBO().getStateDesc();
            }
            this.oldStateCode = this.stateCode;
            this.getProductStructureSessionObject().setStructureType(
                    retrieveProductStructureResponse.getProductStructureBO()
                            .getStructureType());

        }
    }


    /**
     * This method is used to edit a Product Structure selected by the user
     * 
     * @return String
     */
    public String editProductStructure() {
        Logger.logInfo("Entering the method for editing product structure");
        this.removeValueInSessionForTree();
        RetrieveProductStructureResponse retrieveProductStructureResponse = null;
        RetrieveProductStructureRequest retrieveProductStructureRequest = getProductStructureRequest();
        retrieveProductStructureRequest.setEditMode(true);
        retrieveProductStructureResponse = (RetrieveProductStructureResponse) this
                .executeService(retrieveProductStructureRequest);
        Logger.logInfo("Returning the method for editing product structure");
        if(retrieveProductStructureResponse.isLockAquired()){
	        if (null != retrieveProductStructureResponse
	                && retrieveProductStructureResponse.isSuccess()) {
	            setValuesToBackingBeanForView(retrieveProductStructureResponse);
	            this
	                    .setProductStructureSessionObject(retrieveProductStructureResponse
	                            .getProductStructureBO());
	            setActionToSession("EDIT");
	            this.setBreadCrumbTextForEdit();
	        }
	        return "success";
        }else{
        	List messageList = retrieveProductStructureResponse.getMessages();
        	ProductStructureSearchBackingBean productStructureSearchBackingBean=
        			(ProductStructureSearchBackingBean)getRequest().getAttribute("productStructureSearchBackingBean");
        	productStructureSearchBackingBean.productStructureSearch();
      		addAllMessagesToRequest(messageList);
      		return WebConstants.EMPTY_STRING;
        }

    }


    /**
     * This method is used to get RetrieveProductStructureRequest
     * 
     * @return RetrieveProductStructureRequest
     */
    private RetrieveProductStructureRequest getProductStructureRequest() {
        int productStructureId = this.getProductStructureIdForEdit();
        ProductStructureVO productStructureVO = null;
        productStructureVO = this
                .getproductureKeyValuesFromSessionForViewAllVersions(productStructureId);
        if (productStructureVO == null)
            productStructureVO = this
                    .getproductureKeyValuesFromSession(productStructureId);
        RetrieveProductStructureRequest retrieveProductStructureRequest = (RetrieveProductStructureRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_STRUCTURE);
        retrieveProductStructureRequest
                .setProductStructureVO(productStructureVO);
        return retrieveProductStructureRequest;
    }


    /**
     * This method is used to delete a Product Structure selected by the user
     * 
     * @return String
     */
    public String checkoutProductStructure() {
        Logger
                .logInfo("Entering the method for checking out product structure");
        this.removeValueInSessionForTree();
        RetrieveProductStructureResponse retrieveProductStructureResponse = null;
        CheckoutProductStructureRequest checkoutProductStructureRequest = getCheckOutProductStructureRequest();
        retrieveProductStructureResponse = (RetrieveProductStructureResponse) this
                .executeService(checkoutProductStructureRequest);
        Logger
                .logInfo("Returning the method for checking out product structure");
        if (null != retrieveProductStructureResponse
                && retrieveProductStructureResponse.isSuccess()) {
            setValuesToBackingBeanForView(retrieveProductStructureResponse);
            this
                    .setProductStructureSessionObject(retrieveProductStructureResponse
                            .getProductStructureBO());
            this.setActionToSession("CHECKOUT");
            this.setBreadCrumbTextForEdit();
        }
        return "success";

    }


    /**
     * This method is used to get CheckoutProductStructureRequest
     * 
     * @return CheckoutProductStructureRequest
     */
    private CheckoutProductStructureRequest getCheckOutProductStructureRequest() {
        int productStructureId = this.getProductStructureIdForEdit();
        ProductStructureVO productStructureVO = this
                .getproductureKeyValuesFromSessionForViewAllVersions(productStructureId);
        if (productStructureVO == null)
            productStructureVO = this
                    .getproductureKeyValuesFromSession(productStructureId);
        CheckoutProductStructureRequest checkoutProductStructureRequest = (CheckoutProductStructureRequest) this
                .getServiceRequest(ServiceManager.CHECKOUT_PRODUCT_STRUCTURE);
        checkoutProductStructureRequest
                .setProductStructureVO(productStructureVO);
        return checkoutProductStructureRequest;
    }


    /**
     * Returns the productStructureVO
     * 
     * @return ProductStructureVO productStructureVO.
     */
    public ProductStructureVO getProductStructureVO() {
        return productStructureVO;
    }


    /**
     * Sets the productStructureVO
     * 
     * @param productStructureVO.
     */
    public void setProductStructureVO(ProductStructureVO productStructureVO) {
        this.productStructureVO = productStructureVO;
    }


    /**
     * Returns the entity
     * 
     * @return String entity.
     */

    public String getEntity() {
        return entity;
    }


    /**
     * Sets the entity
     * 
     * @param entity.
     */

    public void setEntity(String entity) {
        this.entity = entity;
    }


    /**
     * Returns the lob
     * 
     * @return String lob.
     */

    public String getLob() {
        return lob;
    }


    /**
     * Sets the lob
     * 
     * @param lob.
     */

    public void setLob(String lob) {
        this.lob = lob;
    }


    /**
     * Returns the group
     * 
     * @return String group.
     */

    public String getGroup() {
        return group;
    }


    /**
     * Sets the group
     * 
     * @param group.
     */

    public void setGroup(String group) {
        this.group = group;
    }


    /**
     * Returns the description
     * 
     * @return String description.
     */

    public String getDescription() {
        if (null != description) {
            return description.trim();
        }
        return null;
    }


    /**
     * Sets the description
     * 
     * @param description.
     */

    public void setDescription(String description) {
        if (null != description) {
            this.description = description.trim().toUpperCase();
        }
    }


    /**
     * Returns the effectiveDate
     * 
     * @return String effectiveDate.
     */

    public String getEffectiveDate() {
        if (null != effectiveDate) {
            return effectiveDate.trim();
        }
        return null;
    }


    /**
     * Sets the effectiveDate
     * 
     * @param effectiveDate.
     */

    public void setEffectiveDate(String effectiveDate) {
        if (effectiveDate != null) {
            this.effectiveDate = effectiveDate.trim();
        }
    }


    /**
     * Returns the expiryDate
     * 
     * @return String expiryDate.
     */

    public String getExpiryDate() {
        if (null != expiryDate) {
            return expiryDate.trim();
        }
        return null;
    }


    /**
     * Sets the expiryDate
     * 
     * @param expiryDate.
     */

    public void setExpiryDate(String expiryDate) {
        if (expiryDate != null) {
            this.expiryDate = expiryDate.trim();
        }
    }


    /**
     * Returns the name
     * 
     * @return String name.
     */

    public String getName() {
        if (null != name) {
            return name.trim();
        }
        return null;
    }


    /**
     * Sets the name
     * 
     * @param name.
     */

    public void setName(String name) {
        if (null != name) {
            this.name = name.trim().toUpperCase();
        }
    }


    /**
     * @return Returns the createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }


    /**
     * @param createdUser
     *            The createdUser to set.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }


    /**
     * @return Returns the creationDate.
     */
    public Date getCreationDate() {
        return creationDate;
    }


    /**
     * @param creationDate
     *            The creationDate to set.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * @return Returns the lastUpdatedDate.
     */
    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }


    /**
     * @param lastUpdatedDate
     *            The lastUpdatedDate to set.
     */
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }


    /**
     * @return Returns the lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }


    /**
     * @param lastUpdatedUser
     *            The lastUpdatedUser to set.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }


    /**
     * @return Returns the state.
     */
    public String getState() {
        return state;
    }


    /**
     * @param state
     *            The state to set.
     */
    public void setState(String state) {
        this.state = state;
    }


    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }


    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * @return Returns the version.
     */
    public int getVersion() {
        return version;
    }


    /**
     * @param version
     *            The version to set.
     */
    public void setVersion(int version) {
        this.version = version;
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
     * @return Returns the requiredEntity.
     */
    public boolean isRequiredEntity() {
        return requiredEntity;
    }


    /**
     * @param requiredEntity
     *            The requiredEntity to set.
     */
    public void setRequiredEntity(boolean requiredEntity) {
        this.requiredEntity = requiredEntity;
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
     * @return Returns the requiredGroup.
     */
    public boolean isRequiredGroup() {
        return requiredGroup;
    }


    /**
     * @param requiredGroup
     *            The requiredGroup to set.
     */
    public void setRequiredGroup(boolean requiredGroup) {
        this.requiredGroup = requiredGroup;
    }


    /**
     * @return Returns the requiredLob.
     */
    public boolean isRequiredLob() {
        return requiredLob;
    }


    /**
     * @param requiredLob
     *            The requiredLob to set.
     */
    public void setRequiredLob(boolean requiredLob) {
        this.requiredLob = requiredLob;
    }


    /**
     * @return Returns the requiredDesc.
     */
    public boolean isRequiredDesc() {
        return requiredDesc;
    }


    /**
     * @param requiredDesc
     *            The requiredDesc to set.
     */
    public void setRequiredDesc(boolean requiredDesc) {
        this.requiredDesc = requiredDesc;
    }


    /**
     * @return Returns the requiredName.
     */
    public boolean isRequiredName() {
        return requiredName;
    }


    /**
     * @param requiredName
     *            The requiredName to set.
     */
    public void setRequiredName(boolean requiredName) {
        this.requiredName = requiredName;
    }


    /**
     * @return Returns the associatedBenefitComponentList.
     */
    public List getAssociatedBenefitComponentList() {
        return associatedBenefitComponentList;
    }


    /**
     * @param associatedBenefitComponentList
     *            The associatedBenefitComponentList to set.
     */
    public void setAssociatedBenefitComponentList(
            List associatedBenefitComponentList) {
        this.associatedBenefitComponentList = associatedBenefitComponentList;
    }


    /**
     * @return Returns the productStructureId.
     */
    public int getProductStructureId() {
        return getIdFromSession();
    }


    /**
     * @param productStructureId
     *            The productStructureId to set.
     */
    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
    }


    /**
     * @return Returns the selectedStructureId.
     */
    public int getSelectedStructureId() {
        return selectedStructureId;
    }


    /**
     * @param selectedStructureId
     *            The selectedStructureId to set.
     */
    public void setSelectedStructureId(int selectedStructureId) {
        this.selectedStructureId = selectedStructureId;
    }


    /**
     * @return Returns the RetrieveProductStructureRequest(.
     */
    private RetrieveProductStructureRequest getRetrieveProductStructureRequest() {

        RetrieveProductStructureRequest retrieveProductStructureRequest = (RetrieveProductStructureRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_STRUCTURE);
        productStructureVO = null;
        HttpServletRequest request = getRequest();
        int id;
        if (request.getParameter("id") != null) {
            id = new Integer(request.getParameter("id")).intValue();
            setActionToSession("VIEW");
            if (this.getSearchResultFromSessionForViewAllVersions() != null) {
                productStructureVO = getproductureKeyValuesFromSessionForViewAllVersions(id);
            }
            if (productStructureVO == null) {
                productStructureVO = getproductureKeyValuesFromSession(id);
            }
            retrieveProductStructureRequest
                    .setProductStructureVO(productStructureVO);
            return retrieveProductStructureRequest;
        }
        return null;

    }


    /**
     * @return Returns the selectedStructureName.
     */
    public String getSelectedStructureName() {
        return selectedStructureName;
    }


    /**
     * @param selectedStructureName
     *            The selectedStructureName to set.
     */
    public void setSelectedStructureName(String selectedStructureName) {
        this.selectedStructureName = selectedStructureName;
    }


    /**
     * @return Returns the selectedStructureVersion.
     */
    public String getSelectedStructureVersion() {
        return selectedStructureVersion;
    }


    /**
     * @param selectedStructureVersion
     *            The selectedStructureVersion to set.
     */
    public void setSelectedStructureVersion(String selectedStructureVersion) {
        this.selectedStructureVersion = selectedStructureVersion;
    }


    /**
     * The method is used to go from the Associated BenefitComponent page to the
     * GeneralInfo Page when the tab link is clicked in the Associated
     * BenefitComponent page
     * 
     * @return String
     */
    public String displayProductStructureGeneralInfo() {
        Logger
                .logInfo("Entering the method for displaying product structure general info ");

        // Get the productStructure id from session
        //int prodStrId = getIdFromSession();

        // Create an instance of ProductStructureVO
        ProductStructureVO productStructureVO = new ProductStructureVO();
        productStructureVO = getProductStructureFromSession(productStructureVO);

        // Get the request object for getting the productStructure from db
        RetrieveProductStructureRequest productStructureRequest = (RetrieveProductStructureRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_STRUCTURE);

        // Set the VO to the request
        productStructureRequest.setProductStructureVO(productStructureVO);

        // Call the executeService method to get the response
        RetrieveProductStructureResponse productStructureResponse = (RetrieveProductStructureResponse) this
                .executeService(productStructureRequest);

        // Set the values from the response to the backing bean properties for
        // display in the page
        setValuesToBackingBeanForView(productStructureResponse);
        Logger
                .logInfo("Returning the method for displaying product structure general info ");
        if (getActionFromSession() != null
                && getActionFromSession().equals("VIEW")) {
            return "viewProductStructureGeneralIfo";
        }
        return "displayProductStructureGeneralInfo";

    }


    /**
     * Returns the productStructureIdForEdit.
     * 
     * @return int productStructureIdForEdit.
     */

    public int getProductStructureIdForEdit() {
        return productStructureIdForEdit;
    }


    /**
     * Sets the productStructureIdForEdit.
     * 
     * @param productStructureIdForEdit.
     */

    public void setProductStructureIdForEdit(int productStructureIdForEdit) {
        this.productStructureIdForEdit = productStructureIdForEdit;
    }


    /**
     * Returns the actionStringFromVersion.
     * 
     * @return String actionStringFromVersion.
     */

    public String getActionStringFromVersion() {
        return actionStringFromVersion;
    }


    /**
     * Sets the actionStringFromVersion.
     * 
     * @param actionStringFromVersion.
     */

    public void setActionStringFromVersion(String actionStringFromVersion) {
        this.actionStringFromVersion = actionStringFromVersion;
    }


    /**
     * @return Returns the checkIn.
     */
    public boolean isCheckIn() {
        return checkIn;
    }


    /**
     * @param checkIn
     *            The checkIn to set.
     */
    public void setCheckIn(boolean checkIn) {
        this.checkIn = checkIn;
    }


    /**
     * 
     * @param checkInProductStructureRequest
     * @return String
     */
    private String doCheCkIn(
            SaveProductStructureRequest checkInProductStructureRequest) {
        Logger.logInfo("Entering the method for checking in");
        boolean checkIn = this.isCheckIn();
        SaveProductStructureResponse saveProductStructureResponse = null;
        if (checkIn) {
            checkInProductStructureRequest
                    .setAction(SaveProductStructureRequest.CHECK_IN_PRODUCT_STRUCTURE);
            saveProductStructureResponse = (SaveProductStructureResponse) this
                    .executeService(checkInProductStructureRequest);
            Logger.logInfo("Returning the method for checking in");
            
            if (saveProductStructureResponse != null){
            	if(saveProductStructureResponse.getCondition() == SaveProductResponse.VALIDATION_RESULTS){
    				hasValidationErrors = true;
    				setValuesForAdminMEthodValidation();
    				return "";
    			}else if(saveProductStructureResponse.getCondition() == SaveProductResponse.VALIDATION_WAIT){
    				getSession().setAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN,
    						"productStructureGeneralInfoBackingBean");
    				getSession().setAttribute(WebConstants.OBJECT_VALUE_FOR_CHECKIN,
    						this);
    				getSession().setAttribute(WebConstants.ENTITY_ID_FOR_CHECKIN, 
    						new Integer(checkInProductStructureRequest.getProductStructureVO().getProductStructureId()));
    				getSession().setAttribute(WebConstants.ENTITY_TYPE_FOR_CHECKIN, 
    						WebConstants.ENTITY_TYPE_PRODUCT_STRUCTURE);
    				getSession().setAttribute(WebConstants.ACTION_FOR_CHECKIN,new Integer(checkInProductStructureRequest.getCheckInAction()));
    				return "validationWait";
    			}else{
	                if( saveProductStructureResponse.isSuccess()) {
		                Application application = FacesContext.getCurrentInstance()
		                        .getApplication();
		
		                ProductStructureBenefitComponentBackingBean prodCompBackingBean = ((ProductStructureBenefitComponentBackingBean) application
		                        .createValueBinding(
		                                "#{productStructureBenefitComponentBackingBean}")
		                        .getValue(FacesContext.getCurrentInstance()));
		                prodCompBackingBean.setCheckInSuccess(true);
		                SessionCleanUp.cleanUp();
		                clearValuesOfBackingBean();
		                this.setBreadCrumbTextForPS();
		                return "productStructureCheckIn";
	                }
    			}
            }
        } else {
            checkInProductStructureRequest
                    .setAction(SaveProductStructureRequest.DONE);
            saveProductStructureResponse = (SaveProductStructureResponse) this
                    .executeService(checkInProductStructureRequest);
            setValuesToBackingBeanFromResponse(saveProductStructureResponse);
            return "";
        }
        return "";
    }


    /**
     * Method for checkin product structure from benefit component tab.
     * 
     * @return String.
     */
    public String checkInGenralInfoFromBC() {
        Logger
                .logInfo("Entering the method for checking in general info from benefit component");
        productStructureVO = new ProductStructureVO();
        productStructureVO = getProductStructureFromSession(productStructureVO);
        SaveProductStructureRequest checkInProductStructureRequest = (SaveProductStructureRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT_STRUCTURE);
        checkInProductStructureRequest
                .setProductStructureVO(productStructureVO);
        checkInProductStructureRequest.setActionFromBC(true);
        checkInProductStructureRequest.setOldKeyproductStructureBO(null);
        checkInProductStructureRequest.setCheckInAction(SaveProductStructureRequest.BC_CHECKIN);
        Logger
                .logInfo("Returning the method for checking in general info from benefit component");
        return doCheCkIn(checkInProductStructureRequest);
    }


    /**
     * Method for checkin product structure.
     * 
     * @return String.
     */
    public String checkInGenralInfo() {
        Logger.logInfo("Entering the method for checking in general info");
        if (isValidProductStructure()) {
            if (this.structureType.equalsIgnoreCase("Standard")) {
                this.mandateType = "";
                this.stateCode = "";
            }
            if (null == this.mandateType || "".equals(this.mandateType)
                    || this.mandateType.equals("1")) {
                this.stateCode = "";
            }
			if (null != getSession().getAttribute("AM_BENEFIT"))
				getSession().removeAttribute("AM_BENEFIT"); // clearing
															// for
															// adminmethod
															// contract
															// validation
															// popup
			if (null != getSession().getAttribute("AM_BC_KEY"))
				getSession().removeAttribute("AM_BC_KEY"); // clearing
														   // for
														   // adminmethod
														   // contract
														   // validation
														   // popup
	    	if (null != getSession().getAttribute("DIRECT_CLICK"))
	            getSession().removeAttribute("DIRECT_CLICK");   // clearing for adminmethod contract validation popup
	    	
            createProductStructureVO();
            SaveProductStructureRequest checkInProductStructureRequest = (SaveProductStructureRequest) this
                    .getServiceRequest(ServiceManager.SAVE_PRODUCT_STRUCTURE);
            checkInProductStructureRequest
                    .setProductStructureVO(productStructureVO);
            checkInProductStructureRequest.setDeleteBenefitComponent(false);
            checkInProductStructureRequest.setActionFromBC(false);
            ProductStructureVO oldProductStructureVO = new ProductStructureVO();
            checkInProductStructureRequest
                    .setOldKeyproductStructureBO(getProductStructureFromSession(oldProductStructureVO));
            setDeleteBenefitComponentFlag(checkInProductStructureRequest);
            checkInProductStructureRequest.setCheckInAction(SaveProductStructureRequest.GEN_CHECKIN);
            Logger.logInfo("Returning the method for checking in general info");
            return doCheCkIn(checkInProductStructureRequest);
        }
        getRequest().setAttribute("RETAIN_Value", "");
        return "";
    }


    /**
     * 
     * @return String.
     */
    public String versionDo() {
        String flag;
        if (actionStringFromVersion.equals("copy")) {
            flag = copyProductStructure();
            return "copyProductStructure";
        } else if (actionStringFromVersion.equals("edit")) {
            flag = editProductStructure();
            return "editProductStructure";
        } else if (actionStringFromVersion.equals("checkout")) {
            flag = checkoutProductStructure();
            return "checkoutProductStructure";
        }
        return "";
    }


    /**
     * Method for copying product structure.
     * 
     * @return String
     */
    public String copyProductStructure() {
        Logger.logInfo("Entering the method for copying product structure");
        this.removeValueInSessionForTree();
        this.setCopyFlag(true);
        RetrieveProductStructureResponse retrieveProductStructureResponse = null;
        RetrieveProductStructureRequest retrieveProductStructureRequest = getProductStructureRequest();
        retrieveProductStructureResponse = (RetrieveProductStructureResponse) this
                .executeService(retrieveProductStructureRequest);
        Logger.logInfo("Returning the method for copying product structure");
        if (null != retrieveProductStructureResponse
                && retrieveProductStructureResponse.isSuccess()) {
            setValuesToBackingBeanForView(retrieveProductStructureResponse);            
            this
                    .setProductStructureSessionObject(retrieveProductStructureResponse
                            .getProductStructureBO());
            this.setActionToSession("COPY");
            this.setBreadCrumbTextForCopy();

        }
        return "success";

    }


    /**
     * Method for clearing fields of backing bean.
     *  
     */
    private void clearValuesOfBackingBean() {

        this.name = "";
        this.effectiveDate = "";
        this.description = "";
        this.entity = "ALL~ALL";
        this.group = "ALL~ALL";
        this.expiryDate = "12/31/9999";
        this.effectiveDate = "01/01/1900";
        this.lob = "ALL~ALL";
        this.entity = "ALL~ALL";
        this.group = "ALL~ALL";
        this.structureType = "";
        this.mandateType = "";
        this.stateCode = "";

    }


    /**
     * Returns the checkout.
     * 
     * @return boolean checkout.
     */
    public boolean isCheckout() {
        String state = getStateFromSession();
        if (state != null && state.equals("CHECKED_OUT")) {
            return true;
        }
        return false;
    }


    /**
     * Sets the checkout.
     * 
     * @param checkout.
     */

    /*public void setCheckout(boolean checkout) {

    }*/


    /**
     * @return Returns the requiredStructureType.
     */
    public boolean isRequiredStructureType() {
        return requiredStructureType;
    }


    /**
     * @param requiredStructureType
     *            The requiredStructureType to set.
     */
    public void setRequiredStructureType(boolean requiredStructureType) {
        this.requiredStructureType = requiredStructureType;
    }


    /**
     * @return Returns the structureType.
     */
    public String getStructureType() {
        return structureType;
    }


    /**
     * @param structureType
     *            The structureType to set.
     */
    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }


    /**
     * @return Returns the mandateType.
     */
    public String getMandateType() {
        return mandateType;
    }


    /**
     * @param mandateType
     *            The mandateType to set.
     */
    public void setMandateType(String mandateType) {
        this.mandateType = mandateType;
    }


    /**
     * @return Returns the requiredMandateType.
     */
    public boolean isRequiredMandateType() {
        return requiredMandateType;
    }


    /**
     * @param requiredMandateType
     *            The requiredMandateType to set.
     */
    public void setRequiredMandateType(boolean requiredMandateType) {
        this.requiredMandateType = requiredMandateType;
    }


    /**
     * @return Returns the requiredState.
     */
    public boolean isRequiredState() {
        return requiredState;
    }


    /**
     * @param requiredState
     *            The requiredState to set.
     */
    public void setRequiredState(boolean requiredState) {
        this.requiredState = requiredState;
    }


    /**
     * @return Returns the stateCode.
     */
    public String getStateCode() {
        return stateCode;
    }


    /**
     * @param stateCode
     *            The stateCode to set.
     */
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }


    /**
     * @return stateId
     * 
     * Returns the stateId.
     */
    public String getStateId() {
        return stateId;
    }


    /**
     * @param stateId
     * 
     * Sets the stateId.
     */
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }


    /**
     * @return componentType
     * 
     * Returns the componentType.
     */
    public String getComponentType() {
        return componentType;
    }


    /**
     * @param componentType
     * 
     * Sets the componentType.
     */
    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }


    /**
     * @return oldMandateType
     * 
     * Returns the oldMandateType.
     */
    public String getOldMandateType() {
        return oldMandateType;
    }


    /**
     * @param oldMandateType
     * 
     * Sets the oldMandateType.
     */
    public void setOldMandateType(String oldMandateType) {
        this.oldMandateType = oldMandateType;
    }


    /**
     * @return oldStateCode
     * 
     * Returns the oldStateCode.
     */
    public String getOldStateCode() {
        return oldStateCode;
    }


    /**
     * @param oldStateCode
     * 
     * Sets the oldStateCode.
     */
    public void setOldStateCode(String oldStateCode) {
        this.oldStateCode = oldStateCode;
    }


    /**
     * @return benefitTypeTab
     * 
     * Returns the benefitTypeTab.
     */
    public String getBenefitTypeTab() {
        if (null != this.getProductStructureSessionObject().getStructureType()) {
            if (this.getProductStructureSessionObject().getStructureType()
                    .equals(WebConstants.STD_TYPE))
                return "Standard Definition";
            else
                return "Mandate Definition";
        }

        return "";
    }


    /*
     * print
     * 
     * Methode for getting note atatched to a benefit component
     * 
     * return note list
     *  
     */
    public List loadPrintNotes() {
        return associatedNotesPrintList;
    }


    /*
     * Methode for getting note atatched to a benefit component
     * 
     * return note list
     *  
     */
    public String loadNotes() {

    	getRequest().setAttribute("RETAIN_Value", "");
        //    	 create request
        this.prodStructureName = getNameFromSession();
        return "componentNote";
    }


    /*
     * Methode for load note atatched to a benefit component
     * 
     * return note list
     *  
     */
    public String getLoadNotesValue() {
   		loadNotes();
        return "";
    }


    /*
     * Methode for getting note atatched to a benefit component
     * 
     * return note list
     *  
     */
    public String loadNotesView() {
        return "componentNote";
    }


    /*
     * Methode for loading benefit component general information
     *  
     */
    public String loadGneInfo() {

    	getRequest().setAttribute("RETAIN_Value", "");
        RetrieveComponentFromTreeRequest retrieveComponentFromTreeRequest = new RetrieveComponentFromTreeRequest();
        RetrieveComponentFromTreeResponse retrieveComponentFromTreeResponse = null;
        int bnftCmpntId = this.getBenefitComponentIdFromSession();
        retrieveComponentFromTreeRequest.setBenefitComponentId(bnftCmpntId);
        ProductStructureVO productStructureVO = new ProductStructureVO();
        productStructureVO = getProductStructureFromSession(productStructureVO);
        retrieveComponentFromTreeRequest
                .setProductStructure(productStructureVO);
        retrieveComponentFromTreeResponse = (RetrieveComponentFromTreeResponse) this
                .executeService(retrieveComponentFromTreeRequest);
        BenefitComponentBO benefitComponent = retrieveComponentFromTreeResponse
                .getBenefitComponent();
        SetValuesToBackingForBenefitComponent(benefitComponent);
        DomainDetail domainDetail = retrieveComponentFromTreeResponse
                .getDetail();
        if (domainDetail != null) {
            this.lob = WPDStringUtil.getTildaString(domainDetail
                    .getLineOfBusiness());
            this.entity = WPDStringUtil.getTildaString(domainDetail
                    .getBusinessEntity());
            this.group = WPDStringUtil.getTildaString(domainDetail
                    .getBusinessGroup());
        }
        Logger.logInfo("Returning the method for retrieving benefit component");
        return "genInfo";
    }
    
    public void getBenefitDetails() {
		 List benefitDetailsRetrievedList = null;
		 //boolean showHiddenValue = this.isShowHidden();
		 
		 RetrieveComponentFromTreeRequest retrieveComponentFromTreeRequest = new RetrieveComponentFromTreeRequest();
		 RetrieveComponentFromTreeResponse retrieveComponentFromTreeResponse = null;
		 
		 //Setting benefit component in the request
		 int bnftCmpntId = this.getBenefitComponentIdFromSession();
		 retrieveComponentFromTreeRequest.setBenefitComponentId(bnftCmpntId);
		 
		 //Ssetting productStructure in the request.
		 ProductStructureVO productStructureVO = new ProductStructureVO();
		 productStructureVO = getProductStructureFromSession(productStructureVO);
		 retrieveComponentFromTreeRequest.setProductStructure(productStructureVO);
		 
		 if(showHidden){
		 	// Setting  action to retrieve all the hidden and unhidden benefits
		 	retrieveComponentFromTreeRequest.setAction(RetrieveComponentFromTreeRequest.PRODUCT_STRUCTURE_BENEFIT_RETRIEVE_ALL_DETAILS);
		 }
		 else
		 	//Setting  action to retrieve only visible benefits
		 	retrieveComponentFromTreeRequest.setAction(RetrieveComponentFromTreeRequest.PRODUCT_STRUCTURE_BENEFIT_RETRIEVE);
		 //	Executes the service and fetches the response
		retrieveComponentFromTreeResponse =(RetrieveComponentFromTreeResponse)this.executeService(retrieveComponentFromTreeRequest);
		
		//Extracts the benefit details from the response
		if(retrieveComponentFromTreeResponse.getBenefitDetails()!=null){
			benefitDetailsRetrievedList	 = retrieveComponentFromTreeResponse.getBenefitDetails();
			
			//Setting the boolean value of checkbox from the retrieved String value of the hidden flag.
			for(int i=0;i<benefitDetailsRetrievedList.size();i++){
				ProductStructureAssociatedBenefit productStructureAssociatedBenefits = (ProductStructureAssociatedBenefit)benefitDetailsRetrievedList.get(i);
				if(productStructureAssociatedBenefits.getBenefitHideFlag().equals("T"))
					productStructureAssociatedBenefits.setBenefitVisibilityStatus(true);
				else
					productStructureAssociatedBenefits.setBenefitVisibilityStatus(false);
			}
		    this.setBenefitDetailsList(benefitDetailsRetrievedList);
		}
		this.setBreadCrumbTextForEdit();    	
    }
    
    /**
     * This method retrieves the benefit information associated to a benefit component
     * clicked from the tree
     * 
     * @return String
     */
    public String loadAssociatedBenefits(){
    	getRequest().setAttribute("RETAIN_Value", "");   
    	getBenefitDetails();
    	return "productStructureAssociatedBenefits";
    	
    }
    
   
    
    /**
     * This method retrieves the benefit information associated to a benefit component
     * clicked from the tree
     * 
     * @return String
     */
    public String viewProductStructureAssociatedBenefits(){
    	
    	 List benefitDetailsRetrievedList = null;
    	
    	 
    	 RetrieveComponentFromTreeRequest retrieveComponentFromTreeRequest = new RetrieveComponentFromTreeRequest();
         RetrieveComponentFromTreeResponse retrieveComponentFromTreeResponse = null;
    	 
    	 //Setting benefit component in the request
    	 int bnftCmpntId = this.getBenefitComponentIdFromSession();
         retrieveComponentFromTreeRequest.setBenefitComponentId(bnftCmpntId);
         
         //Ssetting productStructure in the request.
         ProductStructureVO productStructureVO = new ProductStructureVO();
         productStructureVO = getProductStructureFromSession(productStructureVO);
         retrieveComponentFromTreeRequest.setProductStructure(productStructureVO);
    	
    	 	//Setting  action to retrieve only visible benefits
    	 	retrieveComponentFromTreeRequest.setAction(RetrieveComponentFromTreeRequest.PRODUCT_STRUCTURE_BENEFIT_RETRIEVE);
    	
    	// Accessing the value from the session and setting to request.
    	
    	
    	//	Executes the service and fetches the response
    	
    	retrieveComponentFromTreeResponse =(RetrieveComponentFromTreeResponse)this.executeService(retrieveComponentFromTreeRequest);
    	
        //Extracts the benefit details from the response
        if(retrieveComponentFromTreeResponse.getBenefitDetails()!=null){
        	benefitDetailsRetrievedList	 = retrieveComponentFromTreeResponse.getBenefitDetails();
        	
        	//Setting the boolean value of checkbox from the retrieved String value of the hidden flag.
        	for(int i=0;i<benefitDetailsRetrievedList.size();i++){
        		ProductStructureAssociatedBenefit productStructureAssociatedBenefits = (ProductStructureAssociatedBenefit)benefitDetailsRetrievedList.get(i);
        		if(productStructureAssociatedBenefits.getBenefitHideFlag().equals("T"))
        			productStructureAssociatedBenefits.setBenefitVisibilityStatus(true);
        		else
        			productStructureAssociatedBenefits.setBenefitVisibilityStatus(false);
        	}
            this.setBenefitDetailsList(benefitDetailsRetrievedList);
        }
        
    	return "viewProductStructureAssociatedBenefits";
    	
    }
    
    /**
	 * Action methode to update benefit hierarchy 
	 * @return
	 */
	public String saveBenefitDetails(){
		getRequest().setAttribute("RETAIN_Value", "");   
		//Getting the values from the Screen.
		List  bnftDetailsList = getUpdatedListFromScreen();
		if(this.isErrorMessage()){
			validationMessages = new ArrayList(9);
			this.validationMessages.add(new ErrorMessage(WebConstants.MANDATORY_BENEFIT_VISIBLE_REQUIRED));
			addAllMessagesToRequest(validationMessages);
			this.setBenefitDetailsList(bnftDetailsList);
			return "productStructureAssociatedBenefits";
		}
		if(this.isNoFieldSelected()){
			this.setBenefitDetailsList(bnftDetailsList);
			return "productStructureAssociatedBenefits";
		}
		
		if(null != bnftDetailsList){
			
			 List retrievedBenefitDetailsList = null;
			 RetrieveComponentFromTreeRequest retrieveComponentFromTreeRequest = new RetrieveComponentFromTreeRequest();
	         RetrieveComponentFromTreeResponse retrieveComponentFromTreeResponse = null;
	         
	         boolean showHiddenValue = this.isShowHidden();	
	         // Accessing the value from the session and setting it to the request.
	         int bnftCmpntId = this.getBenefitComponentIdFromSession();
	         retrieveComponentFromTreeRequest.setBenefitComponentId(bnftCmpntId);
	         
	         //Ssetting productStructure in the request.
	         ProductStructureVO productStructureVO = new ProductStructureVO();
	         productStructureVO = getProductStructureFromSession(productStructureVO);
	         
	         retrieveComponentFromTreeRequest.setProductStructure(productStructureVO);
	         retrieveComponentFromTreeRequest.setAction(RetrieveComponentFromTreeRequest.PRODUCT_STRUCTURE_BENEFIT_SAVE);
	         retrieveComponentFromTreeRequest.setBenefitDetailsList(bnftDetailsList);
	         retrieveComponentFromTreeRequest.setShowHiddenStatus(showHiddenValue);
	         retrieveComponentFromTreeRequest.setBenefitComponentName((String)getSession().getAttribute("BENEFIT_COMP_NAME"));
	      //   retrieveComponentFromTreeRequest.setBenefitComponentHideFlag(this.getBenefitCompnentHideFlag());
	         //	Executes the service and fetches the response
	 
	         retrieveComponentFromTreeResponse =(RetrieveComponentFromTreeResponse)this.executeService(retrieveComponentFromTreeRequest);
	     
	         //gets the benefit component details from the response
	         if(retrieveComponentFromTreeResponse.getBenefitDetails()!=null){
	         retrievedBenefitDetailsList	 = retrieveComponentFromTreeResponse.getBenefitDetails();
	         //Code change for product structure tree rendering optimization
	         super.updateTreeStructure();
	        
	     	//Setting the boolean value of checkbox from the retrieved String value of the hidden flag.
	         	for(int i=0;i<retrievedBenefitDetailsList.size();i++){
	         		ProductStructureAssociatedBenefit productStructureAssociatedBenefit = (ProductStructureAssociatedBenefit)retrievedBenefitDetailsList.get(i);
	         		if(productStructureAssociatedBenefit.getBenefitHideFlag().equals("T"))
	         			productStructureAssociatedBenefit.setBenefitVisibilityStatus(true);
	         		else
	         			productStructureAssociatedBenefit.setBenefitVisibilityStatus(false);
	     	}
	         this.setShowHidden(showHiddenValue);
	         this.setBenefitDetailsList(retrievedBenefitDetailsList);
	      }
		}
		return "productStructureAssociatedBenefits";
	}
	
	
	/**
	 *  get the updated List 
	 * @return BenefitHierarchyVO
	 */
	private List getUpdatedListFromScreen(){
		
		
		int size = 0;
		Set keysForBnftId = null;
		//Set keysForBnftName = null;
		//Set keysForBenefitVisibility = null;
		
		if (null!= this.getHiddenValBenefitId()){
		keysForBnftId = this.getHiddenValBenefitId().keySet();
		}
        Iterator keyIterBnftId = keysForBnftId.iterator();
		Object bnftIdKey,bnftIdValue,bnftNameValue,bnftVisibilityValue,prevVisibilityValue;
		List benefitDetails = new ArrayList(keysForBnftId == null ? 0: keysForBnftId.size());
		//Variable to check if all the benefits are hidden.
		int checkVar = 0;
		int noFieldSelected = 0;
		while(keyIterBnftId.hasNext()){
			size++;
			bnftIdKey = keyIterBnftId.next();
			/*bnftNameKey = keyIterBnftName.next();
			bnftVisibilityKey = keyIterForBnftVisibility.next();*/
			
			bnftIdValue = this.getHiddenValBenefitId().get(bnftIdKey);
			//Getting the values by using the bnftIdKey, which was used in setting in getPanel
			bnftNameValue = this.getHiddenValBenefitName().get(bnftIdKey);
			bnftVisibilityValue = this.getHiddenValBenefitVisible().get(bnftIdKey);
			prevVisibilityValue = this.getHiddenPrevValBenefitVisible().get(bnftIdKey);
		
			ProductStructureAssociatedBenefit productStructureAssociatedBenefit = new ProductStructureAssociatedBenefit();
			
			if(null != bnftIdValue){
				String benefitId = (String)bnftIdValue;
				productStructureAssociatedBenefit.setStandardBenefitId(Integer.parseInt(benefitId));
			}
			if(null != bnftNameValue){
				String benefitDesc = (String)bnftNameValue;
				productStructureAssociatedBenefit.setStandardBenefitDesc(benefitDesc);
			}
			Boolean visibiltyValue = null;
			if(null != bnftVisibilityValue){ 
				 visibiltyValue = (Boolean)bnftVisibilityValue;
				productStructureAssociatedBenefit.setBenefitVisibilityStatus(visibiltyValue.booleanValue());
				
				// Validating if all the benefits are hidden.
				if(productStructureAssociatedBenefit.isBenefitVisibilityStatus()){
					checkVar++;
				}
			}
			
			if(null != prevVisibilityValue && null != visibiltyValue){
				
				if( (prevVisibilityValue.equals("false") && visibiltyValue.booleanValue())
					|| (prevVisibilityValue.equals("true") && !visibiltyValue.booleanValue())){
					
					//Only if there if a change in the checkbox add the cooresponding field for updation.
					benefitDetails.add(productStructureAssociatedBenefit);
					noFieldSelected++;
		
				}
			}
			
			//benefitDetails.add(productStructureAssociatedBenefit);
		}
		//Perform Checking for All hidden Benefits
		if(size==checkVar){
			//Setting flag for a benefit Component as true.
			this.setErrorMessage(true);
		}
		//Perform checking if the Save button is clicked without any changes. 
		if(noFieldSelected == 0){
			this.setNoFieldSelected(true);
		}
		//Returing the List of BO's
		return benefitDetails;
	}
    
    


    /**
     * @param benefitTypeTab
     * 
     * Sets the benefitTypeTab.
     */
    public void setBenefitTypeTab(String benefitTypeTab) {
        this.benefitTypeTab = benefitTypeTab;
    }


    /**
     * @return Returns the ruleId.
     */
    public String getRuleId() {
        return ruleId;
    }


    /**
     * @param ruleId
     *            The ruleId to set.
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }


    /**
     * @return Returns the oldEffectiveDate.
     */
    public String getOldEffectiveDate() {
        return oldEffectiveDate;
    }


    /**
     * @param oldEffectiveDate
     *            The oldEffectiveDate to set.
     */
    public void setOldEffectiveDate(String oldEffectiveDate) {
        this.oldEffectiveDate = oldEffectiveDate;
    }


    /**
     * @return Returns the oldEntity.
     */
    public String getOldEntity() {
        return oldEntity;
    }


    /**
     * @param oldEntity
     *            The oldEntity to set.
     */
    public void setOldEntity(String oldEntity) {
        this.oldEntity = oldEntity;
    }


    /**
     * @return Returns the oldExpiryDate.
     */
    public String getOldExpiryDate() {
        return oldExpiryDate;
    }


    /**
     * @param oldExpiryDate
     *            The oldExpiryDate to set.
     */
    public void setOldExpiryDate(String oldExpiryDate) {
        this.oldExpiryDate = oldExpiryDate;
    }


    /**
     * @return Returns the oldGroup.
     */
    public String getOldGroup() {
        return oldGroup;
    }


    /**
     * @param oldGroup
     *            The oldGroup to set.
     */
    public void setOldGroup(String oldGroup) {
        this.oldGroup = oldGroup;
    }


    /**
     * @return Returns the oldLob.
     */
    public String getOldLob() {
        return oldLob;
    }


    /**
     * @param oldLob
     *            The oldLob to set.
     */
    public void setOldLob(String oldLob) {
        this.oldLob = oldLob;
    }


    /**
     * @return Returns the dateChange.
     */
    public boolean isDateChange() {
        return dateChange;
    }


    /**
     * @param dateChange
     *            The dateChange to set.
     */
    public void setDateChange(boolean dateChange) {
        this.dateChange = dateChange;
    }


    /**
     * @return Returns the domainChange.
     */
    public boolean isDomainChange() {
        return domainChange;
    }


    /**
     * @param domainChange
     *            The domainChange to set.
     */
    public void setDomainChange(boolean domainChange) {
        this.domainChange = domainChange;
    }


    /**
     * @return Returns the errorFlagForNullState.
     */
    public boolean isErrorFlagForNullState() {
        return errorFlagForNullState;
    }


    /**
     * @param errorFlagForNullState
     *            The errorFlagForNullState to set.
     */
    public void setErrorFlagForNullState(boolean errorFlagForNullState) {
        this.errorFlagForNullState = errorFlagForNullState;
    }


    /**
     * @return Returns the associatedNotesList.
     */
    public List getAssociatedNotesList() {
    	if (!notesRetrieved) {
			ProductStructureNotesRequest productStructureNotesRequest = (ProductStructureNotesRequest) this
					.getServiceRequest(ServiceManager.PRODUCT_STRUCTURE_NOTES_REQUEST);
			// Set the various parameters to the request
			int bnftCmpnt = this.getBenefitComponentIdFromSession();
			productStructureNotesRequest.setEntityId(bnftCmpnt);
			productStructureNotesRequest.setEntityType("ATTACHCOMP");
			productStructureNotesRequest
					.setMaxResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
			productStructureNotesRequest.setAction(1);
			// create response
			ProductStructureNotesResponse productStructureNotesResponse = (ProductStructureNotesResponse) executeService(productStructureNotesRequest);
			// return the list
			if (null != productStructureNotesResponse) {
				this.associatedNotesPrintList = productStructureNotesResponse.getBenefitComponentNotesList();
				this.associatedNotesList = productStructureNotesResponse.getBenefitComponentNotesList();
			}
			
			this.notesRetrieved = true;
		}
        return associatedNotesList;
    }


    /**
     * @param associatedNotesList
     *            The associatedNotesList to set.
     */
    public void setAssociatedNotesList(List associatedNotesList) {
        this.associatedNotesList = associatedNotesList;
    }


    /**
     * @return Returns the benefitComponentNoteId.
     */
    public int getBenefitComponentNoteId() {
        return benefitComponentNoteId;
    }


    /**
     * @param benefitComponentNoteId
     *            The benefitComponentNoteId to set.
     */
    public void setBenefitComponentNoteId(int benefitComponentNoteId) {
        this.benefitComponentNoteId = benefitComponentNoteId;
    }


    /**
     * @return Returns the noteId.
     */
    public int getNoteId() {
        return noteId;
    }


    /**
     * @param noteId
     *            The noteId to set.
     */
    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }


    /**
     * @return Returns the noteNameHidden.
     */
    public String getNoteNameHidden() {
        return noteNameHidden;
    }


    /**
     * @param noteNameHidden
     *            The noteNameHidden to set.
     */
    public void setNoteNameHidden(String noteNameHidden) {
        this.noteNameHidden = noteNameHidden;
    }


    /**
     * @return Returns the dummyVar.
     */
    public String getDummyVar() {
   		loadNotes();
        return dummyVar;
    }


    /**
     * @param dummyVar
     *            The dummyVar to set.
     */
    public void setDummyVar(String dummyVar) {
        this.dummyVar = dummyVar;
    }


    /**
     * @return Returns the copyFlag.
     */
    public boolean isCopyFlag() {
        return copyFlag;
    }


    /**
     * @param copyFlag
     *            The copyFlag to set.
     */
    public void setCopyFlag(boolean copyFlag) {
        this.copyFlag = copyFlag;
    }


    /**
     * @return Returns the associatedNotesPrintList.
     */
    public List getAssociatedNotesPrintList() {
    	// create request
    	if (!notesRetrieved) {
			ProductStructureNotesRequest productStructureNotesRequest = (ProductStructureNotesRequest) this
					.getServiceRequest(ServiceManager.PRODUCT_STRUCTURE_NOTES_REQUEST);
			// Set the various parameters to the request
			int bnftCmpnt = this.getBenefitComponentIdFromSession();
			productStructureNotesRequest.setEntityId(bnftCmpnt);
			productStructureNotesRequest.setEntityType("ATTACHCOMP");
			productStructureNotesRequest
					.setMaxResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
			productStructureNotesRequest.setAction(1);
			// create response
			ProductStructureNotesResponse productStructureNotesResponse = (ProductStructureNotesResponse) executeService(productStructureNotesRequest);
			// return the list
			if (null != productStructureNotesResponse) {
				this.associatedNotesPrintList = productStructureNotesResponse.getBenefitComponentNotesList();
				this.associatedNotesList = productStructureNotesResponse.getBenefitComponentNotesList();
			}
			
			this.notesRetrieved = true;
		}

        return associatedNotesPrintList;
    }


    /**
	 * @param associatedNotesPrintList
	 *            The associatedNotesPrintList to set.
	 */
    public void setAssociatedNotesPrintList(List associatedNotesPrintList) {
        this.associatedNotesPrintList = associatedNotesPrintList;
    }


    /**
     * Returns the prodStructureName
     * 
     * @return String prodStructureName.
     */

    public String getProdStructureName() {
        return prodStructureName;
    }


    /**
     * Sets the prodStructureName
     * 
     * @param prodStructureName.
     */

    public void setProdStructureName(String prodStructureName) {
        this.prodStructureName = prodStructureName;
    }


    /**
     * 
     * @return securityAccess
     * 
     * 
     * 
     * Returns the securityAccess.
     *  
     */

    public boolean isSecurityAccess() {

        try {

            securityAccess = getUser().isAuthorized(WebConstants.NOTES_MODULE,

            StateFactory.VIEW_TASK);

        } catch (SevereException e) {

            // TODO Auto-generated catch block

        	Logger.logError(e);

        }

        return securityAccess;

    }


    /**
     * @param securityAccess
     *            The securityAccess to set.
     */
    public void setSecurityAccess(boolean securityAccess) {
        this.securityAccess = securityAccess;
    }


    /**
     * @return Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Product Configuration >>Product Structure ("
                + super.getNameFromSession() + ") >> Print";

        return printBreadCrumbText;
    }


    /**
     * @param printBreadCrumbText
     *            The printBreadCrumbText to set.
     */
    public void setPrintBreadCrumbText(String printBreadCrumbText) {
        this.printBreadCrumbText = printBreadCrumbText;
    }
    
    
	/**
	 * @return Returns the benefitHeaderViewPanel.
	 */
	public HtmlPanelGrid getBenefitHeaderViewPanel() {
		
		ProductStructureVO productStructureVO = new ProductStructureVO();
        productStructureVO = getProductStructureFromSession(productStructureVO);
        
        this.setProductStructureType(productStructureVO.getStructureType());
				
		HtmlPanelGrid viewPanel = new HtmlPanelGrid();
		viewPanel.setWidth("100%");
		if(!this.productStructureType.equals("MANDATE")){
				//if(!isGeneralBenefitFlag() ){
					viewPanel.setColumns(2);
				//}
		}
		else
		viewPanel.setColumns(1);
		
		viewPanel.setBorder(0);
		viewPanel.setBgcolor("#cccccc");
		viewPanel.setCellpadding("4");
		viewPanel.setCellspacing("1");
		viewPanel.setStyleClass("dataTableHeader");
		
		HtmlSelectBooleanCheckbox htmlcheckbox = new HtmlSelectBooleanCheckbox();
		htmlcheckbox.setId("showHiddenCheckbx");
		htmlcheckbox.setTitle("showHidden");
		htmlcheckbox.setValue(Boolean.valueOf(this.isShowHidden()));
		//Calling Javascript method when the Show Hidden checkbox is selected.
		htmlcheckbox.setOnclick("unsavedDataFinder()");
		
		HtmlOutputText otxtType = new HtmlOutputText();
		otxtType.setValue("Show Hidden");
		 //Adding the checkbox and its Label to an HtmlOutputLabel
		HtmlOutputLabel outputLbl = new HtmlOutputLabel();
		outputLbl.getChildren().add(htmlcheckbox);
		outputLbl.getChildren().add(otxtType);
		
		HtmlOutputText nameOutputText = new HtmlOutputText();
		nameOutputText.setValue("Benefit Name");
		nameOutputText.setId("Name");
		//Adding the label and benefit name heading to the Panel
		viewPanel.getChildren().add(nameOutputText);
		
		if(!this.productStructureType.equals("MANDATE")){
			//if(!isGeneralBenefitFlag()){
				viewPanel.getChildren().add(outputLbl);

		}
		
		return viewPanel;
	}
	
	
	/**
	 * @param benefitHeaderViewPanel The benefitHeaderViewPanel to set.
	 */
	public void setBenefitHeaderViewPanel(HtmlPanelGrid benefitHeaderViewPanel) {
		this.benefitHeaderViewPanel = benefitHeaderViewPanel;
	}
	/**
	 * @return Returns the displayPanel.
	 */
	public HtmlPanelGrid getDisplayPanel() {
		
		HtmlPanelGrid displayPanel = new HtmlPanelGrid();
		displayPanel.setCellpadding("0");
		displayPanel.setCellspacing("0");
		displayPanel.setWidth("100%");
		displayPanel.setColumns(8);
		displayPanel.setBorder(0);
		displayPanel.setStyle("height:8%;");
		displayPanel.setStyleClass("dataTableHeader");
		displayPanel.setBgcolor("#cccccc");
		HtmlOutputText outputText = new HtmlOutputText();
		outputText.setValue("Associated Benefits");
		displayPanel.getChildren().add(outputText);
		
		return displayPanel;
	}
	/**
	 * @param displayPanel The displayPanel to set.
	 */
	public void setDisplayPanel(HtmlPanelGrid displayPanel) {
		this.displayPanel = displayPanel;
	}
	/**
	 * @return Returns the hiddenValBenefitId.
	 */
	public Map getHiddenValBenefitId() {
		return hiddenValBenefitId;
	}
	/**
	 * @param hiddenValBenefitId The hiddenValBenefitId to set.
	 */
	public void setHiddenValBenefitId(Map hiddenValBenefitId) {
		this.hiddenValBenefitId = hiddenValBenefitId;
	}
	/**
	 * @return Returns the hiddenValBenefitName.
	 */
	public Map getHiddenValBenefitName() {
		return hiddenValBenefitName;
	}
	/**
	 * @param hiddenValBenefitName The hiddenValBenefitName to set.
	 */
	public void setHiddenValBenefitName(Map hiddenValBenefitName) {
		this.hiddenValBenefitName = hiddenValBenefitName;
	}
	/**
	 * @return Returns the hiddenValBenefitVisible.
	 */
	public Map getHiddenValBenefitVisible() {
		return hiddenValBenefitVisible;
	}
	/**
	 * @param hiddenValBenefitVisible The hiddenValBenefitVisible to set.
	 */
	public void setHiddenValBenefitVisible(Map hiddenValBenefitVisible) {
		this.hiddenValBenefitVisible = hiddenValBenefitVisible;
	}
	/**
	 * @return Returns the panel.
	 */
	public HtmlPanelGrid getPanel() {
		
		HtmlPanelGrid panel = new HtmlPanelGrid();
		ProductStructureAssociatedBenefit productStructureAssociatedBenefit = null;		
		List benefitDetailsDisplay = this.getBenefitDetailsList();
		StringBuffer rows = new StringBuffer();//For setting row style
		String benefitCompName= (String) getSession().getAttribute("BENEFIT_COMP_NAME");
		if(null != benefitDetailsDisplay && benefitDetailsDisplay.size()>0){
						
			if(!this.productStructureType.equals("MANDATE")){// && !benefitCompName.equalsIgnoreCase(WebConstants.GEN_BENEFITS)){
				//if(!isGeneralBenefitFlag() ){
					panel.setColumns(2);
				//}
			}
			else
				panel.setColumns(1);
				panel.setBorder(0);
				panel.setBgcolor("#cccccc");
				panel.setCellpadding("3");
				panel.setCellspacing("1");
			
					for(int i = 0; i < benefitDetailsDisplay.size(); i++){
					
						if(benefitDetailsDisplay.size() > 0){
							productStructureAssociatedBenefit = (ProductStructureAssociatedBenefit)benefitDetailsDisplay.get(i);
						}
						/**
						 * Setting the foreground color based upon the visibility
						 * of the benefit
						 */
						if(productStructureAssociatedBenefit.isBenefitVisibilityStatus() && i%2!=1)
							rows.append("hiddenFieldLevelDisplay");
						else if(productStructureAssociatedBenefit.isBenefitVisibilityStatus())
							rows.append("hiddenFieldDisplay");
						else if(i%2==1)
							rows.append("dataTableOddRow");
						else
							rows.append("dataTableEvenRow");
						
						if(benefitDetailsDisplay.size()!=0)
							rows.append(",");
			
					//Creating an input hidden field for Benefit Id.
					HtmlInputHidden inputHiddenBenefitId = new HtmlInputHidden();
					inputHiddenBenefitId.setId("BnftId"+i);
					inputHiddenBenefitId.setValue(new Integer(productStructureAssociatedBenefit.getStandardBenefitId()));
					// Creating a value binding for each benefit id and Setting it.
					ValueBinding valBindingForBenefitId = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{productStructureGeneralInfoBackingBean.hiddenValBenefitId[" + productStructureAssociatedBenefit.getStandardBenefitId()
									+ "]}");		
					inputHiddenBenefitId.setValueBinding("value", valBindingForBenefitId);				
					
					//Creating an output text each for a benefit name.
					HtmlOutputText outputTextBenefitName = new HtmlOutputText();
					outputTextBenefitName.setId("Name" + i);
					outputTextBenefitName.setValue(productStructureAssociatedBenefit.getStandardBenefitDesc());
					
					
					//Creating the hidden field for storing the Name.
					HtmlInputHidden inputHiddenBenefitName = new HtmlInputHidden();
					inputHiddenBenefitName.setId("BnftName"+i);
					inputHiddenBenefitName.setValue(productStructureAssociatedBenefit.getStandardBenefitDesc());
					//String bnftNames = productStructureAssociatedBenefit.getStandardBenefitDesc();
					//Creating a value binding and setting it to the benefit name output text.
					ValueBinding valBindingForBenefitName = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{productStructureGeneralInfoBackingBean.hiddenValBenefitName[" + productStructureAssociatedBenefit.getStandardBenefitId()
							+ "]}");		
					inputHiddenBenefitName.setValueBinding("value", valBindingForBenefitName);	
					
					//Creating a boolean check box each for a benefit.
					HtmlSelectBooleanCheckbox checkbox = new HtmlSelectBooleanCheckbox();
					
					checkbox.setId("checkbox"+i);
					checkbox.setValue(Boolean.valueOf(productStructureAssociatedBenefit.isBenefitVisibilityStatus()));
					//Creating and setting Value binding for each checkbox
					checkbox.setValueBinding("value",FacesContext
							.getCurrentInstance()
							.getApplication().createValueBinding(
									"#{productStructureGeneralInfoBackingBean.hiddenValBenefitVisible[" + productStructureAssociatedBenefit.getStandardBenefitId()
									+ "]}"));
					
					checkbox.setValue(Boolean.valueOf(productStructureAssociatedBenefit.isBenefitVisibilityStatus()));
					//Storing the value so that only updated fields are saved in the save functionality
					HtmlInputHidden prevCheckValue = new HtmlInputHidden();
					prevCheckValue.setId("dummyCheckboxValue"+i);
					prevCheckValue.setValue(Boolean.valueOf(productStructureAssociatedBenefit.isBenefitVisibilityStatus()));
					//Creating and setting Value binding for each checkbox
					prevCheckValue.setValueBinding("value",FacesContext
							.getCurrentInstance()
							.getApplication().createValueBinding(
									"#{productStructureGeneralInfoBackingBean.hiddenPrevValBenefitVisible[" + productStructureAssociatedBenefit.getStandardBenefitId()
									+ "]}"));
					
					prevCheckValue.setValue(Boolean.valueOf(productStructureAssociatedBenefit.isBenefitVisibilityStatus()));
					
					//Creating a label and set the benefit name component to it.
					HtmlOutputLabel lblName = new HtmlOutputLabel();
					lblName.setFor("Name" + i);
					lblName.setId("lblName" + i);
					
					//Create another label for checkbox
					HtmlOutputLabel lblcheckbox = new HtmlOutputLabel();
					lblcheckbox.setFor("checkbox" + i);
					lblcheckbox.setId("lblCheckbox" + i); 
					
					//add the components to the label along with the hidden fields.
					lblName.getChildren().add(outputTextBenefitName);
					lblName.getChildren().add(inputHiddenBenefitId);
					lblName.getChildren().add(inputHiddenBenefitName);
					lblName.getChildren().add(prevCheckValue);
					
					lblcheckbox.getChildren().add(checkbox);
					

					this.getHiddenValBenefitName();
					this.getHiddenValBenefitVisible();
					//Add the label to the panel
					panel.getChildren().add(lblName);
					panel.setRowClasses(rows.toString());//Sets the row style to panel
		
					if(!(this.productStructureType.equals("MANDATE"))){//&&!benefitCompName.equalsIgnoreCase(WebConstants.GEN_BENEFITS)){
							//if(!this.isGeneralBenefitFlag()){
									panel.getChildren().add(lblcheckbox);
									this.saveButton = true;
							//}
					}
					else
						this.saveButton = false;
					}
				}
		
		else{
						
			HtmlOutputText outputText = new HtmlOutputText();
			outputText.setValue("No Associated Standard Benefits");
			panel.getChildren().add(outputText);
			this.saveButton = false;
			return panel;
		}
		
		return panel;
	}
	
	/**
	 * @param panel The panel to set.
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
	}
	/**
	 * @return Returns the savePanel.
	 */
	public HtmlPanelGrid getSavePanel() {
		return savePanel;
	}
	/**
	 * @param savePanel The savePanel to set.
	 */
	public void setSavePanel(HtmlPanelGrid savePanel) {
		this.savePanel = savePanel;
	}
	/**
	 * @return Returns the showHidden.
	 */
	public boolean isShowHidden() {
		return showHidden;
	}
	/**
	 * @param showHidden The showHidden to set.
	 */
	public void setShowHidden(boolean showHidden) {
		this.showHidden = showHidden;
	}
	
	
	/**
	 * @return Returns the benefitDetailsList.
	 */
	public List getBenefitDetailsList() {
		return benefitDetailsList;
	}
	/**
	 * @param benefitDetailsList The benefitDetailsList to set.
	 */
	public void setBenefitDetailsList(List benefitDetailsList) {
		this.benefitDetailsList = benefitDetailsList;
	}
	
	
	/**
	 * @return Returns the benefitCompnentHideFlag.
	 */
	public String getBenefitCompnentHideFlag() {
		return benefitCompnentHideFlag;
	}
	/**
	 * @param benefitCompnentHideFlag The benefitCompnentHideFlag to set.
	 */
	public void setBenefitCompnentHideFlag(String benefitCompnentHideFlag) {
		this.benefitCompnentHideFlag = benefitCompnentHideFlag;
	}
	
	
	/**
	 * @return Returns the printHeaderPanel.
	 */
	public HtmlPanelGrid getPrintHeaderPanel() {
			
			HtmlPanelGrid viewPanel = new HtmlPanelGrid();
			viewPanel.setWidth("100%");
			viewPanel.setColumns(2);
			viewPanel.setBorder(0);
			viewPanel.setCellpadding("4");
			viewPanel.setCellspacing("1");
			
			HtmlOutputText nameOutputText = new HtmlOutputText();
			nameOutputText.setValue("Benefit Name");
			nameOutputText.setId("Name");
			nameOutputText.setStyle("font-weight:bold");
			viewPanel.getChildren().add(nameOutputText);
			return viewPanel;
	
	}
	/**
	 * @param printHeaderPanel The printHeaderPanel to set.
	 */
	public void setPrintHeaderPanel(HtmlPanelGrid printHeaderPanel) {
		this.printHeaderPanel = printHeaderPanel;
	}
	/**
	 * @return Returns the printPanel.
	 */
	public HtmlPanelGrid getPrintPanel() {

		HtmlPanelGrid panel = new HtmlPanelGrid();
		ProductStructureAssociatedBenefit productStructureAssociatedBenefit = null;		
		List benefitDetailsDisplay = this.getBenefitDetailsList();
		
		if(null != benefitDetailsDisplay && benefitDetailsDisplay.size()>0){
				panel.setWidth("100%");
				panel.setColumns(1);
				panel.setBorder(0);
				panel.setCellpadding("3");
				panel.setCellspacing("1");
			
					for(int i = 0; i < benefitDetailsDisplay.size(); i++){
					
						if(benefitDetailsDisplay.size() > 0){
							productStructureAssociatedBenefit = (ProductStructureAssociatedBenefit)benefitDetailsDisplay.get(i);
						}
					//Creating an input hidden field for Benefit Id.
					HtmlInputHidden inputHiddenBenefitId = new HtmlInputHidden();
					inputHiddenBenefitId.setId("BnftId"+i);
					inputHiddenBenefitId.setValue(new Integer(productStructureAssociatedBenefit.getStandardBenefitId()));
					// Creating a value binding for each benefit id and Setting it.
					ValueBinding valBindingForBenefitId = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{productStructureGeneralInfoBackingBean.hiddenValBenefitId[" + productStructureAssociatedBenefit.getStandardBenefitId()
									+ "]}");		
					inputHiddenBenefitId.setValueBinding("value", valBindingForBenefitId);				
					
					//Creating an output text each for a benefit name.
					HtmlOutputText outputTextBenefitName = new HtmlOutputText();
					outputTextBenefitName.setId("Name" + i);
					outputTextBenefitName.setValue(productStructureAssociatedBenefit.getStandardBenefitDesc());
					
					
					//Creating the hidden field for storing the Name.
					HtmlInputHidden inputHiddenBenefitName = new HtmlInputHidden();
					inputHiddenBenefitName.setId("BnftName"+i);
					inputHiddenBenefitName.setValue(productStructureAssociatedBenefit.getStandardBenefitDesc());
					//String bnftNames = productStructureAssociatedBenefit.getStandardBenefitDesc();
					//Creating a value binding and setting it to the benefit name output text.
					ValueBinding valBindingForBenefitName = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{productStructureGeneralInfoBackingBean.hiddenValBenefitName[" + productStructureAssociatedBenefit.getStandardBenefitId()
							+ "]}");		
					inputHiddenBenefitName.setValueBinding("value", valBindingForBenefitName);	
					
					//Creating a boolean check box each for a benefit.
					HtmlSelectBooleanCheckbox checkbox = new HtmlSelectBooleanCheckbox();
					checkbox.setId("checkbox"+i);
					checkbox.setValue(Boolean.valueOf(productStructureAssociatedBenefit.isBenefitVisibilityStatus()));
					//Creating and setting Value binding for each checkbox
					checkbox.setValueBinding("value",FacesContext
							.getCurrentInstance()
							.getApplication().createValueBinding(
									"#{productStructureGeneralInfoBackingBean.hiddenValBenefitVisible[" + productStructureAssociatedBenefit.getStandardBenefitId()
									+ "]}"));
					
					checkbox.setValue(Boolean.valueOf(productStructureAssociatedBenefit.isBenefitVisibilityStatus()));
					
					//Creating a label and set the benefit name component to it.
					HtmlOutputLabel lblName = new HtmlOutputLabel();
					lblName.setFor("Name" + i);
					lblName.setId("lblName" + i);
					
					//Create another label for checkbox
					HtmlOutputLabel lblcheckbox = new HtmlOutputLabel();
					lblcheckbox.setFor("checkbox" + i);
					lblcheckbox.setId("lblcheckbox" + i);
					
					//add the components to the label along with the hidden fields.
					lblName.getChildren().add(outputTextBenefitName);
					lblName.getChildren().add(inputHiddenBenefitId);
					lblName.getChildren().add(inputHiddenBenefitName);
				//	lblcheckbox.getChildren().add(checkbox);

					this.getHiddenValBenefitName();
					this.getHiddenValBenefitVisible();
					//Add the label to the panel
					panel.getChildren().add(lblName);
				//	panel.getChildren().add(lblcheckbox);
					
					}
				}
		
		else{
			HtmlOutputText outputText = new HtmlOutputText();
			outputText.setValue("No Associated Standard Benefits");
			panel.getChildren().add(outputText);
			return panel;
		}
		return panel;
		
	}
	/**
	 * @param printPanel The printPanel to set.
	 */
	public void setPrintPanel(HtmlPanelGrid printPanel) {
		this.printPanel = printPanel;
	}
	
	
	/**
	 * @return Returns the printProductStructureAssociatedBenefits.
	 */
	public String getPrintProductStructureAssociatedBenefits() {
		
	    	 List benefitDetailsRetrievedList = null;
	    	
	    	 
	    	 RetrieveComponentFromTreeRequest retrieveComponentFromTreeRequest = new RetrieveComponentFromTreeRequest();
	         RetrieveComponentFromTreeResponse retrieveComponentFromTreeResponse = null;
	    	 
	    	 //Setting benefit component in the request
	    	 int bnftCmpntId = this.getBenefitComponentIdFromSession();
	         retrieveComponentFromTreeRequest.setBenefitComponentId(bnftCmpntId);
	         
	         //Ssetting productStructure in the request.
	         ProductStructureVO productStructureVO = new ProductStructureVO();
	         productStructureVO = getProductStructureFromSession(productStructureVO);
	         retrieveComponentFromTreeRequest.setProductStructure(productStructureVO);
	    	
	    	 	//Setting  action to retrieve only visible benefits
	    	 	retrieveComponentFromTreeRequest.setAction(RetrieveComponentFromTreeRequest.PRODUCT_STRUCTURE_BENEFIT_RETRIEVE);
	    	
	    	// Accessing the value from the session and setting to request.
	    	
	    	
	    	//	Executes the service and fetches the response
	    	
	    	retrieveComponentFromTreeResponse =(RetrieveComponentFromTreeResponse)this.executeService(retrieveComponentFromTreeRequest);
	    	
	        //Extracts the benefit details from the response
	        if(retrieveComponentFromTreeResponse.getBenefitDetails()!=null){
	        	benefitDetailsRetrievedList	 = retrieveComponentFromTreeResponse.getBenefitDetails();
	        	
	        	//Setting the boolean value of checkbox from the retrieved String value of the hidden flag.
	        	for(int i=0;i<benefitDetailsRetrievedList.size();i++){
	        		ProductStructureAssociatedBenefit productStructureAssociatedBenefits = (ProductStructureAssociatedBenefit)benefitDetailsRetrievedList.get(i);
	        		if(productStructureAssociatedBenefits.getBenefitHideFlag().equals("T"))
	        			productStructureAssociatedBenefits.setBenefitVisibilityStatus(true);
	        		else
	        			productStructureAssociatedBenefits.setBenefitVisibilityStatus(false);
	        	}
	            this.setBenefitDetailsList(benefitDetailsRetrievedList);
	        }
	        
	    	return "printProductStructureAssociatedBenefits";
	    	
	    }
		

	/**
	 * @param printProductStructureAssociatedBenefits The printProductStructureAssociatedBenefits to set.
	 */
	public void setPrintProductStructureAssociatedBenefits(
			String printProductStructureAssociatedBenefits) {
		this.printProductStructureAssociatedBenefits = printProductStructureAssociatedBenefits;
	}
	
	
	/**
	 * @return Returns the saveButton.
	 */
	public boolean isSaveButton() {
		return saveButton;
	}
	/**
	 * @param saveButton The saveButton to set.
	 */
	public void setSaveButton(boolean saveButton) {
		this.saveButton = saveButton;
	}
	
	/**
	 * @return Returns the errorMessage.
	 */
	public boolean isErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage The errorMessage to set.
	 */
	public void setErrorMessage(boolean errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * @return Returns the loadAssociatedBenefits.
	 */
	public String getLoadAssociatedBenefits() {
		getBenefitDetails();
		return "";
		
	}
	/**
	 * @param loadAssociatedBenefits The loadAssociatedBenefits to set.
	 */
	public void setLoadAssociatedBenefits(String loadAssociatedBenefits) {
		this.loadAssociatedBenefits = loadAssociatedBenefits;
	}
	

	/**
	 * @return Returns the productStructureType.
	 */
	public String getProductStructureType() {
		return productStructureType;
	}
	/**
	 * @param productStructureType The productStructureType to set.
	 */
	public void setProductStructureType(String productStructureType) {
		this.productStructureType = productStructureType;
	}
	
	public Map getHiddenPrevValBenefitVisible() {
		return hiddenPrevValBenefitVisible;
	}
	public void setHiddenPrevValBenefitVisible(Map hiddenPrevValBenefitVisible) {
		this.hiddenPrevValBenefitVisible = hiddenPrevValBenefitVisible;
	}
	
	
	public boolean isNoFieldSelected() {
		return noFieldSelected;
	}
	public void setNoFieldSelected(boolean noFieldSelected) {
		this.noFieldSelected = noFieldSelected;
	}
	
	/**
	 * @return Returns the hasValidationErrors.
	 */
	public boolean isHasValidationErrors() {
		return hasValidationErrors;
	}
	/**
	 * @param hasValidationErrors The hasValidationErrors to set.
	 */
	public void setHasValidationErrors(boolean hasValidationErrors) {
		this.hasValidationErrors = hasValidationErrors;
	}
	/**
	 * @return Returns the bnftCmpntVersion.
	 */
	public int getBnftCmpntVersion() {
		return bnftCmpntVersion;
	}
	/**
	 * @param bnftCmpntVersion The bnftCmpntVersion to set.
	 */
	public void setBnftCmpntVersion(int bnftCmpntVersion) {
		this.bnftCmpntVersion = bnftCmpntVersion;
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
	 * @return Returns the panelData.
	 */
	public String getPanelData() {
		return panelData;
	}
	/**
	 * @param panelData The panelData to set.
	 */
	public void setPanelData(String panelData) {
		this.panelData = panelData;
	}
	/**
	 * @return Returns the productFamily.
	 */
	public String getProductFamily() {
		return productFamily;
	}
	/**
	 * @param productFamily The productFamily to set.
	 */
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	/**
	 * @return Returns the familyInvalid.
	 */
	public boolean isFamilyInvalid() {
		return familyInvalid;
	}
	/**
	 * @param familyInvalid The familyInvalid to set.
	 */
	public void setFamilyInvalid(boolean familyInvalid) {
		this.familyInvalid = familyInvalid;
	}
	/**
	 * @return Returns the productFamilyForView.
	 */
	public String getProductFamilyForView() {
		return productFamilyForView;
	}
	/**
	 * @param productFamilyForView The productFamilyForView to set.
	 */
	public void setProductFamilyForView(String productFamilyForView) {
		this.productFamilyForView = productFamilyForView;
	}
	/**
	 * @return Returns the requiredBusinessUnit.
	 */
	public boolean isRequiredBusinessUnit() {
		return requiredBusinessUnit;
	}
	/**
	 * @param requiredBusinessUnit The requiredBusinessUnit to set.
	 */
	public void setRequiredBusinessUnit(boolean requiredBusinessUnit) {
		this.requiredBusinessUnit = requiredBusinessUnit;
	}
	
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public String getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(String marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
	/**
	 * @return Returns the oldMarketBusinessUnit.
	 */
	public String getOldMarketBusinessUnit() {
		return oldMarketBusinessUnit;
	}
	/**
	 * @param oldMarketBusinessUnit The oldMarketBusinessUnit to set.
	 */
	public void setOldMarketBusinessUnit(String oldMarketBusinessUnit) {
		this.oldMarketBusinessUnit = oldMarketBusinessUnit;
	}
	/**
	 * @return Returns the ruleType.
	 */
	public String getRuleType() {
		return ruleType;
	}
	/**
	 * @param ruleType The ruleType to set.
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}


	public void setBenefitCompntId(int benefitCompntId) {
		this.benefitCompntId = benefitCompntId;
	}


	public int getBenefitCompntId() {
		return benefitCompntId;
	}
}