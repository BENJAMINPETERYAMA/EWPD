/*
 * BenefitComponentCreateBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.benefitcomponent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentCopyRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentRetrieveRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentRetrieveRequestForEdit;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentSaveRequest;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentCopyResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentRetrieveResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentRetrieveResponseForEdit;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentSaveResponse;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.bo.StateImpl;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO;
import com.wellpoint.wpd.common.standardbenefit.bo.UniverseBO;
import com.wellpoint.wpd.common.standardbenefit.request.GetBenefitTierDefinitionForDetailPrintRequest;
import com.wellpoint.wpd.common.standardbenefit.request.GetBenefitTierDefinitionRequest;
import com.wellpoint.wpd.common.standardbenefit.response.GetBenefitTierDefinitionResponse;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Bean to create Benefit Component
 * This bean will bind with the jsp pages.
 * BenefitComponentCreateBackingBean contains the getters and setters of the 
 * variables and respective functions
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class BenefitComponentCreateBackingBean extends BenefitComponentBackingBean {

    private int benefitComponentId;

    private int benefitComponentParentId;

    private int version;

    private String lineOfBusiness;

    private String businessEntity;

    private String businessGroup;
    
    private String marketBusinessUnit;

    private String benefitComponentName;

    private String effectiveDate;

    private String expiryDate;

    private String description;

    private String status;

    private String state;

    private String createdUser;

    private String lastUpdatedUser;

    private Date createdTimestamp;

    private Date lastUpdatedTimestamp;

    private List lineOfBusinessCodeList;

    private List businessEntityCodeList;

    private List businessGroupCodeList;
    
    private List marketBusinessUnitList;
    
// Enhancements
    private String oldLineOfBusinessCode;

    private String oldBusinessEntityCode;

    private String oldBusinessGroupCode;
    
    private String oldmarketBusinessUnit;
    
// End - Enhancements    

    private boolean editFlag;

    private boolean checkInOpted;

    boolean validationStatus = false;

    boolean allfieldsvalid = false;

    boolean lobValdn = false;

    boolean businessentityValdn = false;

    boolean businessgroupValdn = false;
    
    boolean requiredMarketBusinessUnit = false;

    boolean nameValdn = false;

    boolean effdateValdn = false;

    boolean expdateValdn = false;

    boolean descValdn = false;
    
    boolean editMode = false;
    
    boolean lockStatus = true;
    

// Enhancements

    // componentId
    private String componentType;

    //mandateId
    private String mandateType;

    //The tilda separated string for state
    private String selectedStateId;
    
    //Variables to display the validation message 
    //in the jsp while changing the  mandate
    // and state fields
    
    private String oldMandateType;
    private String oldSelectedStateId;
    
    //List of ruleIds
    private List ruleIdList;

    // Tilda separated string for reference
    private String ruleId;
    private	String strRuleType;
    // State Id
    private String stateId;

    // State Desc
    private String stateDesc;

    // fields for checking valdn
    boolean compTypeValdn = false;

    boolean mandateTypeValdn = false;

    boolean stateIdValdn = false;

    boolean ruleIdValdn = false;

    // To be removed
    private List bcComponentTypeListForCombo;

    // To incorporate Validation for componentType = MANDATE
    private String componentTypeTab;
    
   
   private boolean  domainChange = false;
   private boolean errorFlagForNullState;
   

// End - Enhancements

    private boolean doneFlag = false;

    private String oldEffectiveDate;

    private String oldExpiryDate;

    private String term;

    private String qualifier;

    private String providerArrangement;

    private String dataType;

    private String stdBenefitDescription;

    private String minorHeading;

    private boolean dateChanged = false;
    

    List validationMessages;

// for Edit
    private String selectedBenefitComponentKey;

    private String selectedBenefitComponentName;

    private String selectedBenefitComponentVersion;

    private String selectedBenefitComponentParentId;

    private String selectedBenefitComponentType;

    private List selectedDomainList;

//for Print
    private int loadBenefitComponentforPrint;

    boolean createFlag = true;
    
    boolean copyFlag = false;

// For stdBnftView
    private String sbBenType;

    private String sbMandateType;

    private String sbRule;

    private String sbSelState;
    
    private String benefitCategory;
    
    private int benefitVersion;
    
    private String tierBenefitComp;
  //ICD10 --getting BC ID from session
    private int benefitCompntId;


    /**
     * Contructor
     */
    public BenefitComponentCreateBackingBean() {
        super();
        //TODO TO be Removed
        this.lineOfBusiness = WebConstants.ALL_99;
        this.businessEntity = WebConstants.ALL_99;
        this.businessGroup = WebConstants.ALL_99;
        this.marketBusinessUnit = WebConstants.ALL_99;
        this.expiryDate = WebConstants.DEFAULT_EXP_DATE;
        this.effectiveDate = WebConstants.DEFAULT_EFF_DATE;       
        validationMessages = new ArrayList(10);
        checkInOpted = false;
        setBreadCrump();
        if(0 !=getBenefitComponentSessionObject().getBenefitComponentId()){
        this.setBenefitCompntId(getBenefitComponentSessionObject().getBenefitComponentId());
        }
    }


    /**
     * Returns the copyFlag
     * @return boolean copyFlag.
     */
    public boolean isCopyFlag() {
        return copyFlag;
    }
    /**
     * Sets the copyFlag
     * @param copyFlag.
     */
    public void setCopyFlag(boolean copyFlag) {
        this.copyFlag = copyFlag;
    }
    /**
     * Returns the dateChanged
     * 
     * @return boolean dateChanged.
     */
    public boolean isDateChanged() {
        return dateChanged;
    }


    /**
     * Sets the dateChanged
     * 
     * @param dateChanged.
     */
    public void setDateChanged(boolean dateChanged) {
        this.dateChanged = dateChanged;
    }


    /**
     * Returns the oldEffectiveDate
     * 
     * @return String oldEffectiveDate.
     */
    public String getOldEffectiveDate() {
        return oldEffectiveDate;
    }


    /**
     * Sets the oldEffectiveDate
     * 
     * @param oldEffectiveDate.
     */
    public void setOldEffectiveDate(String oldEffectiveDate) {
        this.oldEffectiveDate = oldEffectiveDate;
    }


    /**
     * Returns the oldExpiryDate
     * 
     * @return String oldExpiryDate.
     */
    public String getOldExpiryDate() {
        return oldExpiryDate;
    }


    /**
     * Sets the oldExpiryDate
     * 
     * @param oldExpiryDate.
     */
    public void setOldExpiryDate(String oldExpiryDate) {
        this.oldExpiryDate = oldExpiryDate;
    }


    /**
     * @return Returns the createFlag.
     */
    public boolean isCreateFlag() {
        return createFlag;
    }


    /**
     * @param createFlag
     *            The createFlag to set.
     */
    public void setCreateFlag(boolean createFlag) {
        this.createFlag = createFlag;
    }


    /**
     * Sets the breadcrump
     *  
     */
    protected void setBreadCrump() {
        if (null == this.getBenefitComponentSessionObject()
                .getBenefitComponentName()){
            this
                    .setBreadCrumbText(WebConstants.BENEFIT_COMPONENT_CREATE_BREADCRUMB);
        }
        else {
        	if(getBenefitComponentSessionObject().getBenefitComponentMode().equalsIgnoreCase("View")){
        		this.setBreadCrumbText(WebConstants.BENEFIT_COMPONENT_BREADCRUMB
                        + " ("
                        + this.getBenefitComponentSessionObject()
                                .getBenefitComponentName() + ") >> View");
        	}else{
	            this.setBreadCrumbText(WebConstants.BENEFIT_COMPONENT_BREADCRUMB
	                    + " ("
	                    + this.getBenefitComponentSessionObject()
	                            .getBenefitComponentName() + ") >> Edit");
	        	}
        }
    }


    /**
     * While clicking benefit component page search result -> Edit icon, datas
     * are fetched from database and are loaded to this page using this
     * function.
     * 
     * @return String
     */
    public String loadBenefitComponentForEdit() {
        // get the key and name
    	
    	
        int retrieveKey = 0;
        int versionNumber = 0;
        int parentId = 0;
        String componentType = null;
        boolean viewAllFlag = true;
        getSession().removeAttribute("SESSION_TREE_STATE_BC");
        if (null != this.selectedBenefitComponentKey
                && !this.selectedBenefitComponentKey.equals(WebConstants.EMPTY_STRING)) {
            retrieveKey = Integer.parseInt(this.selectedBenefitComponentKey);
        }
        if (null != this.selectedBenefitComponentVersion
                && !this.selectedBenefitComponentVersion.equals(WebConstants.EMPTY_STRING)) {
            versionNumber = Integer
                    .parseInt(this.selectedBenefitComponentVersion);
        }
        if (null != this.selectedBenefitComponentParentId
                && !this.selectedBenefitComponentParentId.equals(WebConstants.EMPTY_STRING)) {
            parentId = Integer.parseInt(this.selectedBenefitComponentParentId);
        }

// Enhancement
        if (null != this.selectedBenefitComponentType
                && !this.selectedBenefitComponentType.equals(WebConstants.EMPTY_STRING)) {
            componentType = this.selectedBenefitComponentType;
        }
// End - Enhancement
        String retrieveName = this.getSelectedBenefitComponentName();
        List domainList = new ArrayList(1);
        
        // get the benefitComponent session object and set all the values
        this.getBenefitComponentSessionObject().setBenefitComponentMode("Mode");
        this.getBenefitComponentSessionObject().setBenefitComponentId(
                retrieveKey);
        this.getBenefitComponentSessionObject().setBenefitComponentName(
                retrieveName);
        this.getBenefitComponentSessionObject()
                .setBenefitComponentVersionNumber(versionNumber);
        this.getBenefitComponentSessionObject().setBenefitComponentParentId(
                parentId);
 // Enhancement
        // Set componentType in session
        this.getBenefitComponentSessionObject().setBcComponentType(componentType);
//        Added for lock-unlock
        this.setEditMode(true);
        
        
// End - Enhancement
        this.setBreadCrumbText(WebConstants.BENEFIT_COMPONENT_CREATE_BREADCRUMB
                + " ("
                + this.getBenefitComponentSessionObject()
                        .getBenefitComponentName() + ") >> Edit");
        List searchResultList = (List) getSession().getAttribute(
                "benefitComponentSearchResult");
        removeValueInSession("benefitComponentSearchResult");
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList
                        .get(i);
                if (retrieveKey == benefitComponentBO.getBenefitComponentId()) {
                    this.getBenefitComponentSessionObject()
                            .setBusinessDomainList(
                                    benefitComponentBO.getBusinessDomainList());
                    this.getBenefitComponentSessionObject()
                            .setBenefitComponentVersionNumber(
                                    benefitComponentBO.getVersion());
                    // set the status & state in the session
                    StateImpl impl = (StateImpl) benefitComponentBO.getState();
                    if(null != impl){
                    	this.getBenefitComponentSessionObject().setComponentState(impl.getState());
                    }
                    this.getBenefitComponentSessionObject().setStatus(benefitComponentBO.getStatus());
                    this.getBenefitComponentSessionObject().setState(benefitComponentBO.getState());
                    domainList = benefitComponentBO.getBusinessDomainList();
                    viewAllFlag = false;
                    break;
                }
            }
        }

        if (viewAllFlag) {
            List searchResultListForViewAll = (List) getSession().getAttribute(
                    "benefitComponentViewAllSearchResult");
            if (null != searchResultListForViewAll
                    && !searchResultListForViewAll.isEmpty()) {
                for (int i = 0; i < searchResultListForViewAll.size(); i++) {
                    BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultListForViewAll
                            .get(i);
                    if (retrieveKey == benefitComponentBO
                            .getBenefitComponentId()) {
                        this.getBenefitComponentSessionObject()
                                .setBusinessDomainList(
                                        benefitComponentBO
                                                .getBusinessDomainList());
                        this.getBenefitComponentSessionObject()
                                .setBenefitComponentVersionNumber(
                                        benefitComponentBO.getVersion());
                        this.getBenefitComponentSessionObject()
                                .setBenefitComponentName(
                                        benefitComponentBO.getName());
                        this.getBenefitComponentSessionObject()
                                .setBenefitComponentId(
                                        benefitComponentBO
                                                .getBenefitComponentId());
                        retrieveKey = this.getBenefitComponentSessionObject()
                                .getBenefitComponentId();
                        versionNumber = this.getBenefitComponentSessionObject()
                                .getBenefitComponentVersionNumber();
                        domainList = benefitComponentBO.getBusinessDomainList();
                        break;
                    }
                }
            }
        }
        // retrieve the selectedBenefitComponentDetails
        retrieveBenefitComponentDetails(retrieveKey, retrieveName,
                versionNumber, domainList,editMode);
        // forward to the 'benefitComponentEdit.jsp' page
        if(isLockStatus()){
        	return WebConstants.BENEFIT_COMPONENT_EDIT;
        }
        else{
        	return WebConstants.EMPTY_STRING;
        }
        
    }


    /**
     * Method used to set the values to the Session and retrieve the related
     * information after check out
     * 
     * @return String
     */
    public String loadBenefitComponentAfterCheckOut() {

        this.getBenefitComponentSessionObject().setBenefitComponentMode("Mode");
        this.getBenefitComponentSessionObject().setBenefitComponentId(
                this.benefitComponentId);
        this.getBenefitComponentSessionObject().setBenefitComponentParentId(
                this.benefitComponentParentId);
        this.getBenefitComponentSessionObject().setBenefitComponentName(
                this.benefitComponentName);
        this.getBenefitComponentSessionObject()
                .setBenefitComponentVersionNumber(this.version);
        this.getBenefitComponentSessionObject().setBusinessDomainList(
                this.selectedDomainList);
// Enhancement
        this.getBenefitComponentSessionObject().setBcComponentType(
                this.componentType);
        this.getBenefitComponentSessionObject().setBcMandateType(
                this.mandateType);
        this.getBenefitComponentSessionObject()
                .setBcRuleIdList(this.ruleIdList);
        this.getBenefitComponentSessionObject().setBcStateId(this.stateId);
//End - Enhancement
        retrieveBenefitComponentDetails(this.benefitComponentId,
                this.benefitComponentName, this.version,
                this.selectedDomainList,this.editMode);
        addAllMessagesToRequest(this.validationMessages);
        this.setBreadCrumbText("Product >> BenefitComponent"
                + " ("
                + this.getBenefitComponentSessionObject()
                        .getBenefitComponentName() + ") >> Edit");
        return WebConstants.BENEFIT_COMPONENT_EDIT;
    }


    /**
     * This method to retrieve the selected benefitComponentDetails
     * 
     * @param retrieveKey,retreiveName,versionNumber,domainList
     * @return void
     */
    private void retrieveBenefitComponentDetails(int retrieveKey,
            String retrieveName, int versionNumber, List domainList,boolean editMode) {
        // create the request
        BenefitComponentRetrieveRequestForEdit requestForEdit = (BenefitComponentRetrieveRequestForEdit) this
                .getServiceRequest(ServiceManager.BENEFIT_COMPONENT_RETRIEVE_REQUEST_FOR_EDIT);
        // set the retrieve key and name in the request
        requestForEdit.setRetrieveKey(retrieveKey);
        requestForEdit.setRetrieveName(retrieveName);
        requestForEdit.setVersionNumber(versionNumber);
        requestForEdit.setDomainList(domainList);
        requestForEdit.setEditMode(editMode);
        
        // get the response
        BenefitComponentRetrieveResponseForEdit responseForEdit = (BenefitComponentRetrieveResponseForEdit) executeService(requestForEdit);
        // get the bo from the response and set all the required values
        if(responseForEdit.isLockAquired()){
	        if (null != responseForEdit
	                && null != responseForEdit.getBenefitComponentBO()) {
	        	this.setLockStatus(responseForEdit.isLockAquired());
	            this.setBenefitComponentId(responseForEdit.getBenefitComponentBO()
	                    .getBenefitComponentId());
	            this.setBenefitComponentParentId(responseForEdit
	                    .getBenefitComponentBO().getBenefitComponentParentId());
	            this.setBenefitComponentName(responseForEdit
	                    .getBenefitComponentBO().getName());
	            this.setEffectiveDate(WPDStringUtil
	                    .convertDateToString(responseForEdit
	                            .getBenefitComponentBO().getEffectiveDate()));
	            this.setExpiryDate(WPDStringUtil
	                    .convertDateToString(responseForEdit
	                            .getBenefitComponentBO().getExpiryDate()));
	            this.setOldEffectiveDate(WPDStringUtil
	                    .convertDateToString(responseForEdit
	                            .getBenefitComponentBO().getEffectiveDate()));
	            this.setOldExpiryDate(WPDStringUtil
	                    .convertDateToString(responseForEdit
	                            .getBenefitComponentBO().getExpiryDate()));
	            this.setDescription(responseForEdit.getBenefitComponentBO()
	                    .getDescription());
	            //Enhancement
	            this.setComponentType(responseForEdit.getBenefitComponentBO()
	                    .getComponentType());
	
	            if (WebConstants.MANDATE_TYPE.equals(this.getComponentType())) {
	                this.setMandateType(responseForEdit.getBenefitComponentBO()
	                        .getMandateType());
	                this.setOldMandateType(responseForEdit.getBenefitComponentBO()
	                        .getMandateType());
	            } else {
	                this.setMandateType(WebConstants.EMPTY_STRING);
	                
	            }
	            //            if ("MANDATE".equals(this.getComponentType())
	            //                    && "2".equals(this.getMandateType())
	            //                    || "3".equals(this.getMandateType())) {
	
	            if (WebConstants.MANDATE_TYPE.equals(this.getComponentType())
	                    && !this.getMandateType().equals(WebConstants.FEDERAL_TYPE)) {
	                // To get the state
	                String stateid = responseForEdit.getBenefitComponentBO()
	                        .getStateId();
	                String statedesc = responseForEdit.getBenefitComponentBO()
	                        .getStateDesc();
	                String selectedStateId = stateid + "~" + statedesc;
	                this.setSelectedStateId(selectedStateId);
	                this.setOldSelectedStateId(selectedStateId);
	            } else {
	                String selectedStateId = " " ;
	                this.setSelectedStateId(selectedStateId);
	                this.setOldSelectedStateId(selectedStateId);
	            }
	            //To get the reference
	            List refId = responseForEdit.getBenefitComponentBO().getRuleList();
	            List refNam = responseForEdit.getBenefitComponentBO().getRuleNameList();
	            // Did the code  change to get the RuleType
	            List refType = responseForEdit.getBenefitComponentBO().getRuleTypeList();
	            String reference = convertListtoTilda(refNam, refId);
	            this.setStrRuleType(getType(refType));
	            this.setRuleId(reference);
	            //End - enhancement
	            this.setCreatedTimestamp(responseForEdit
	                            .getBenefitComponentBO().getCreatedTimestamp());
	            this.setCreatedUser(responseForEdit.getBenefitComponentBO()
	                    .getCreatedUser());
	            this
	                    .setLastUpdatedTimestamp(responseForEdit
	                                    .getBenefitComponentBO()
	                                    .getLastUpdatedTimestamp());
	            this.setState(responseForEdit.getBenefitComponentBO().getState()
	                    .getState());
	            this.setStatus(responseForEdit.getBenefitComponentBO().getStatus());
	            this.setLastUpdatedUser(responseForEdit.getBenefitComponentBO()
	                    .getLastUpdatedUser());
	            this.setVersion(responseForEdit.getBenefitComponentBO()
	                    .getVersion());
	            if (null != responseForEdit.getBenefitComponentBO()
	                    .getLineOfBusiness()
	                    && !responseForEdit.getBenefitComponentBO()
	                            .getLineOfBusiness().isEmpty()) {
	                this.setLineOfBusiness(WPDStringUtil
	                        .getTildaString(responseForEdit.getBenefitComponentBO()
	                                .getLineOfBusiness()));
	// Enhancement                
	                this.setOldLineOfBusinessCode(WPDStringUtil
	                        .getTildaString(responseForEdit.getBenefitComponentBO()
	                                .getLineOfBusiness()));
	// End - Enhancement                
	                
	            }
	            if (null != responseForEdit.getBenefitComponentBO()
	                    .getBusinessEntity()
	                    && !responseForEdit.getBenefitComponentBO()
	                            .getBusinessEntity().isEmpty()) {
	                this.setBusinessEntity(WPDStringUtil
	                        .getTildaString(responseForEdit.getBenefitComponentBO()
	                                .getBusinessEntity()));
	                
	//Enhancement                
	                this.setOldBusinessEntityCode(WPDStringUtil
	                        .getTildaString(responseForEdit.getBenefitComponentBO()
	                                .getBusinessEntity()));
	// End - Enhancement                 
	            }
	            
	            if (null != responseForEdit.getBenefitComponentBO()
	                    .getBusinessGroup()
	                    && !responseForEdit.getBenefitComponentBO()
	                            .getBusinessGroup().isEmpty()) {
	                this.setBusinessGroup(WPDStringUtil
	                        .getTildaString(responseForEdit.getBenefitComponentBO()
	                                .getBusinessGroup()));
	//Enhancement                
	                this.setOldBusinessGroupCode(WPDStringUtil
	                        .getTildaString(responseForEdit.getBenefitComponentBO()
	                                .getBusinessGroup()));
	// End - Enhancement                 
	                
	            }
	            if (null != responseForEdit.getBenefitComponentBO().getMarketBusinessUnit()
						&& !responseForEdit.getBenefitComponentBO().getMarketBusinessUnit().isEmpty()) 
	            {
					this.setMarketBusinessUnit(WPDStringUtil.getTildaString(responseForEdit.getBenefitComponentBO().getMarketBusinessUnit()));
					//Enhancement
					this.setOldmarketBusinessUnit(WPDStringUtil.getTildaString(responseForEdit.getBenefitComponentBO().getMarketBusinessUnit()));
					// End - Enhancement
				}
	        }
        
        // Enhancement
        // set the status & state in the session
        StateImpl impl = (StateImpl) responseForEdit.getBenefitComponentBO().getState();
        if(null != impl){
            this.getBenefitComponentSessionObject().setComponentState(impl.getState());
        }
        this.getBenefitComponentSessionObject().setStatus(responseForEdit.getBenefitComponentBO().getStatus());
        this.getBenefitComponentSessionObject().setState(responseForEdit.getBenefitComponentBO().getState());
        this.getBenefitComponentSessionObject().setBcComponentType(
                responseForEdit.getBenefitComponentBO().getComponentType());
        this.getBenefitComponentSessionObject().setBcMandateType(
                responseForEdit.getBenefitComponentBO().getMandateType());
        this.getBenefitComponentSessionObject().setBcStateId(
                responseForEdit.getBenefitComponentBO().getStateId());
        }else{
        	this.setLockStatus(responseForEdit.isLockAquired());
        	List messageList = responseForEdit.getMessages();
        	BenefitComponentSearchBackingBean benefitComponentSearchBackingBean =
        		(BenefitComponentSearchBackingBean)getRequest().getAttribute("BenefitComponentSearchBackingBean");
        	benefitComponentSearchBackingBean.performSearch();
        	addAllMessagesToRequest(messageList);
        	
        }
       
        // End - Enhancement
    }


    /**
     * Methode to save the benefit Component(Create/Update)
     * 
     * @return String
     */
    public String saveBenefitComponent() {
        validationStatus = validateFields();
        if (validationStatus) {

            this.lineOfBusinessCodeList = WPDStringUtil.getListFromTildaString(
                    this.getLineOfBusiness(), 2, 2, 2);
            this.businessEntityCodeList = WPDStringUtil.getListFromTildaString(
                    this.getBusinessEntity(), 2, 2, 2);
            this.businessGroupCodeList = WPDStringUtil.getListFromTildaString(
                    this.getBusinessGroup(), 2, 2, 2);
            this.marketBusinessUnitList = WPDStringUtil.getListFromTildaString(
                    this.getMarketBusinessUnit(), 2, 2, 2);
            this.ruleIdList = WPDStringUtil.getListFromTildaString(this
                    .getRuleId(), 2, 2, 2);
            // Modification
            if (WebConstants.MANDATE_TYPE.equals(this.componentType)
                    && WebConstants.FEDERAL_TYPE.equals(this.mandateType)) {
                selectedStateId = WebConstants.ALL_99;
            }

            // End - Modification
            //To split the stateId values
            StringTokenizer st = null;
            if(null != selectedStateId && !"".equals(selectedStateId))
                st = new StringTokenizer(selectedStateId, "~");
            if (null != st && st.hasMoreTokens()) { 
                if(st.countTokens() > 1){
	                this.stateId = st.nextToken();
	                this.stateDesc = st.nextToken();   
                }
            }

            //            if (this.componentType.equals("STANDARD")) {
            //                this.mandateType = WebConstants.EMPTY_STRING;
            //                this.selectedStateId = WebConstants.EMPTY_STRING;
            //            }
            //            if (null == this.mandateType || WebConstants.EMPTY_STRING.equals(this.mandateType)
            //                    || this.mandateType.equals("FED")) {
            //                this.selectedStateId = WebConstants.EMPTY_STRING;
            //            }

            BenefitComponentSaveRequest benefitComponentSaveRequest = getBenefitComponentSaveRequest();

            if (editFlag) {
                benefitComponentSaveRequest
                        .setAction(BenefitComponentSaveRequest.UPDATE_BENEFIT_COMPONENT);
                benefitComponentSaveRequest.setBenefitHierarchyId(this
                        .getBenefitComponentSessionObject()
                        .getBenefitHierarchyId());
                benefitComponentSaveRequest.setOldBenefitComponentVO(this
                        .getOldBenefitComponent());
                // To delete the benefit attached to the benefitComponent if the
                // component Type is changed to a different one.Only the
                // benefits
                // corresponding the the selected componentType need be
                // displayed.
                setDeleteBenefitFlag(benefitComponentSaveRequest);
            } else {
                benefitComponentSaveRequest
                        .setAction(BenefitComponentSaveRequest.CREATE_BENEFIT_COMPONENT);
            }
            benefitComponentSaveRequest.setDateChanged(this.isDateChanged());
            BenefitComponentSaveResponse benefitComponentSaveResponse = (BenefitComponentSaveResponse) executeService(benefitComponentSaveRequest);
            if(null == benefitComponentSaveResponse){
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

            if (null != benefitComponentSaveResponse) {
                if (null != benefitComponentSaveResponse
                        .getBenefitComponentBO()) {
                    if (benefitComponentSaveResponse.isSuccessFlagForSave()) {
                    	
                    	//Code change for benefit component tree rendering optimization
                    	this.updateTreeStructure();
                        // Enhancements set for ref-- TODO
                        this.benefitComponentId = benefitComponentSaveResponse
                                .getBenefitComponentBO()
                                .getBenefitComponentId();
                        this.benefitComponentParentId = benefitComponentSaveResponse
                                .getBenefitComponentBO()
                                .getBenefitComponentParentId();
                        this.benefitComponentName = benefitComponentSaveResponse
                                .getBenefitComponentBO().getName();
                        this.setDescription(benefitComponentSaveResponse
                                .getBenefitComponentBO().getDescription());
                        // **********Enhancements - componentType to be changed
                        // TODO
                        this.setComponentType(benefitComponentSaveResponse
                                .getBenefitComponentBO().getComponentType());
                        if (WebConstants.MANDATE_TYPE.equals(this.getComponentType())) {
                            this.setMandateType((benefitComponentSaveResponse
                                    .getBenefitComponentBO().getMandateType()));
                            this.setOldMandateType((benefitComponentSaveResponse
                                    .getBenefitComponentBO().getMandateType()));
                        } else {
                            this.setMandateType(WebConstants.EMPTY_STRING);
                        }
                        // To display the value in the edit page

                        if (WebConstants.MANDATE_TYPE.equals(this.getComponentType())
                                && !this.getMandateType().equals(WebConstants.FEDERAL_TYPE)) {
                            // To get the state
                            String stateid = benefitComponentSaveResponse
                                    .getBenefitComponentBO().getStateId();
                            String statedesc = benefitComponentSaveResponse
                                    .getBenefitComponentBO().getStateDesc();
                            String selectedStateId = stateid + "~" + statedesc;
                            this.setSelectedStateId(selectedStateId);
                            this.setOldSelectedStateId(selectedStateId);
                        } else {
                            String selectedStateId = "";
                            this.setSelectedStateId(selectedStateId);
                            this.setOldSelectedStateId(selectedStateId);
                        }
                        //To get the reference
                        List refId = benefitComponentSaveResponse
                                .getBenefitComponentBO().getRuleList();
                        List refNam = benefitComponentSaveResponse
                                .getBenefitComponentBO().getRuleNameList();
                        String reference = convertListtoTilda(refNam, refId);
                        this.setRuleId(reference);
                        // ****************End - Enhancements
                        this.setCreatedUser(benefitComponentSaveResponse
                                .getBenefitComponentBO().getCreatedUser());
                        this
                                .setCreatedTimestamp(benefitComponentSaveResponse
                                                .getBenefitComponentBO()
                                                .getCreatedTimestamp());
                        this.setLastUpdatedUser(benefitComponentSaveResponse
                                .getBenefitComponentBO().getLastUpdatedUser());
                        this
                                .setLastUpdatedTimestamp(benefitComponentSaveResponse
                                                .getBenefitComponentBO()
                                                .getLastUpdatedTimestamp());
                        if (null != benefitComponentSaveResponse
                                .getBenefitComponentBO().getState()) {
                            this.setState(benefitComponentSaveResponse
                                    .getBenefitComponentBO().getState()
                                    .getState());
                        }
                        this.setStatus(benefitComponentSaveResponse
                                .getBenefitComponentBO().getStatus());
                        this.setVersion(benefitComponentSaveResponse
                                .getBenefitComponentBO().getVersion());
                        this
                                .setLineOfBusiness(WPDStringUtil
                                        .getTildaString(benefitComponentSaveResponse
                                                .getDomainDetail()
                                                .getLineOfBusiness()));
                        this
                                .setBusinessEntity(WPDStringUtil
                                        .getTildaString(benefitComponentSaveResponse
                                                .getDomainDetail()
                                                .getBusinessEntity()));
                        this.setBusinessGroup(WPDStringUtil
                                .getTildaString(benefitComponentSaveResponse
                                        .getDomainDetail().getBusinessGroup()));
                        this.setMarketBusinessUnit(WPDStringUtil
                                .getTildaString(benefitComponentSaveResponse
                                        .getDomainDetail().getMarketBusinessUnit()));
// Enhancement
                        this
                        		.setOldLineOfBusinessCode(WPDStringUtil
                                .getTildaString(benefitComponentSaveResponse
                                        .getDomainDetail()
                                        .getLineOfBusiness()));
		                this
		                        .setOldBusinessEntityCode(WPDStringUtil
		                                .getTildaString(benefitComponentSaveResponse
		                                        .getDomainDetail()
		                                        .getBusinessEntity()));
		                this.setOldBusinessGroupCode(WPDStringUtil
		                        .getTildaString(benefitComponentSaveResponse
		                                .getDomainDetail().getBusinessGroup()));
		                this.setOldmarketBusinessUnit(WPDStringUtil
		                        .getTildaString(benefitComponentSaveResponse
		                                .getDomainDetail().getMarketBusinessUnit()));
// End - Enhancement                        
                        // Set values to session
                        this.getBenefitComponentSessionObject()
                                .setBenefitComponentId(benefitComponentId);
                        this.getBenefitComponentSessionObject()
                                .setBenefitComponentParentId(
                                        benefitComponentSaveResponse
                                                .getBenefitComponentBO()
                                                .getBenefitComponentParentId());
                        this.getBenefitComponentSessionObject()
                                .setBenefitComponentName(
                                        benefitComponentSaveResponse
                                                .getBenefitComponentBO()
                                                .getName());
                        // Enhancement
                        this.getBenefitComponentSessionObject()
                                .setBcComponentType(
                                        benefitComponentSaveResponse
                                                .getBenefitComponentBO()
                                                .getComponentType());
                        this.getBenefitComponentSessionObject()
                                .setBcMandateType(
                                        benefitComponentSaveResponse
                                                .getBenefitComponentBO()
                                                .getMandateType());
                        
// Added for bug fix -- State and Status was not being shown in the notes tab print page
                        this.getBenefitComponentSessionObject().setStatus(benefitComponentSaveResponse.getBenefitComponentBO().getStatus());
                        if (null != benefitComponentSaveResponse.
                        		getBenefitComponentBO().getState()) 
                        	this.getBenefitComponentSessionObject().
								setComponentState(
										benefitComponentSaveResponse.
											getBenefitComponentBO().getState().
												getState());
// End for bug fix.                        
                        this.getBenefitComponentSessionObject()
                                .setBcRuleIdList(
                                        benefitComponentSaveResponse
                                                .getBenefitComponentBO()
                                                .getRuleList());
                        this.getBenefitComponentSessionObject().setBcStateId(
                                benefitComponentSaveResponse
                                        .getBenefitComponentBO().getStateId());
                        // End - Enhancement
                        this.getBenefitComponentSessionObject()
                                .setBenefitComponentVersionNumber(
                                        benefitComponentSaveResponse
                                                .getBenefitComponentBO()
                                                .getVersion());
                        this.getBenefitComponentSessionObject()
                                .setBusinessDomainList(
                                        benefitComponentSaveResponse
                                                .getDomainDetail()
                                                .getDomainList());
                        this.getBenefitComponentSessionObject()
                                .setEffectiveDate(
                                        benefitComponentSaveResponse
                                                .getBenefitComponentBO()
                                                .getEffectiveDate());
                        this.getBenefitComponentSessionObject().setExpiryDate(
                                benefitComponentSaveResponse
                                        .getBenefitComponentBO()
                                        .getExpiryDate());
                        this.getBenefitComponentSessionObject()
                                .setBenefitComponentMode(WebConstants.MODE);
                        this
                                .setBreadCrumbText(WebConstants.BENEFIT_COMPONENT_BREADCRUMB
                                        + " ("
                                        + this
                                                .getBenefitComponentSessionObject()
                                                .getBenefitComponentName()
                                        + ") >> Edit");
                        this
                                .setOldEffectiveDate(WPDStringUtil
                                        .convertDateToString(benefitComponentSaveResponse
                                                .getBenefitComponentBO()
                                                .getEffectiveDate()));
                        this
                                .setOldExpiryDate(WPDStringUtil
                                        .convertDateToString(benefitComponentSaveResponse
                                                .getBenefitComponentBO()
                                                .getExpiryDate()));
                        if (benefitComponentSaveRequest.isDoneFlag()
                                && benefitComponentSaveRequest.isCheckInFlag()&& benefitComponentSaveResponse.isSuccessFlag()) {
                            clearPageValues();
                            this
                                    .setBreadCrumbText(WebConstants.BENEFIT_COMPONENT_CREATE_BREADCRUMB);
                            return WebConstants.BENEFIT_COMPONENT_CREATE;
                        } else if (benefitComponentSaveRequest.isDoneFlag()
                                && !benefitComponentSaveRequest.isCheckInFlag()) {
                        	getRequest().setAttribute("RETAIN_Value", "");
                            return WebConstants.BENEFIT_COMPONENT_EDIT;
                        } else{
                        	getRequest().setAttribute("RETAIN_Value", "");
                            return WebConstants.BENEFIT_COMPONENT_EDIT;
                        }
                    }
                } else {
                    if (benefitComponentSaveRequest.isDoneFlag()
                            && benefitComponentSaveRequest.isCheckInFlag()) {
                        clearPageValues();
                        this
                                .setBreadCrumbText(WebConstants.BENEFIT_COMPONENT_CREATE_BREADCRUMB);
                        return WebConstants.BENEFIT_COMPONENT_CREATE;
                    } else if (benefitComponentSaveRequest.isDoneFlag()
                            && !benefitComponentSaveRequest.isCheckInFlag()) {
                    	getRequest().setAttribute("RETAIN_Value", "");
                        return WebConstants.BENEFIT_COMPONENT_EDIT;
                    }
                }
            }

        }
        getRequest().setAttribute("RETAIN_Value", "");
        return WebConstants.EMPTY_STRING;
    }


    /**
     * Method to set the Flag to delete Benefit which doesn't matches the
     * changes made to the BenefitComponent.
     * 
     * @return benefitComponentSaveRequest.
     */
    private BenefitComponentSaveRequest setDeleteBenefitFlag(
            BenefitComponentSaveRequest benefitComponentSaveRequest) {
        BenefitComponentSessionObject benefitComponentSessionObject = getBenefitComponentSessionObject();
        if (null != benefitComponentSessionObject.getBcComponentType()) {
            if (!(this.componentType.equals(benefitComponentSessionObject
                    .getBcComponentType()))) {
                benefitComponentSaveRequest.setDeleteBenefit(true);
            }
        }
        if (null != benefitComponentSessionObject.getBcMandateType()) {
            if (WebConstants.EMPTY_STRING != this.mandateType && null!= this.mandateType) {
                if (!(this.mandateType.equals(benefitComponentSessionObject
                        .getBcMandateType()))) {
                    benefitComponentSaveRequest.setDeleteBenefit(true);
                }
            }
        }
        if (null != benefitComponentSessionObject.getBcStateId()) {
            if (WebConstants.EMPTY_STRING != this.stateId && null != this.stateId) {
                if (!(this.stateId.equals(benefitComponentSessionObject
                        .getBcStateId()))) {
                    benefitComponentSaveRequest.setDeleteBenefit(true);
                }
            }
        }
// Enhancement - For Business Domains
//        List oldBusinessEntityValues = new ArrayList();
//        List oldBusinessGrpValues = new ArrayList();
//        List oldLOBValues = new ArrayList();
//        boolean isALLpresentForbusEnty = false;
//        boolean isALLpresentForLOB = false;
//        boolean isALLpresentForbusGrp = false;
       
       
        
        // BusinessEntityValidation 
//        if (null != benefitComponentSessionObject.getBusinessDomainList() &&  benefitComponentSessionObject.getBusinessDomainList().size()!=0) {
//            for(int i =0 ; i < benefitComponentSessionObject.getBusinessDomainList().size();i++){
//                Domain entityValues = (Domain)benefitComponentSessionObject.getBusinessDomainList().get(i);
//                String businessEnity = entityValues.getBusinessEntity();
//                oldBusinessEntityValues.add(businessEnity);
//            }
//        }
//        
//        if(null!= oldBusinessEntityValues && oldBusinessEntityValues.size()!=0){
//            for(int i =0 ; i < oldBusinessEntityValues.size();i++){
//                String businessEntityValueFromSession = oldBusinessEntityValues.get(i).toString();
//// To check if any value in the newList is ALL
//                for(int k=0; k<this.businessEntityCodeList.size() ; k++){
//                	if("ALL".equals( this.businessEntityCodeList.get(k))){
//                		isALLpresentForbusEnty = true;
//                	}
//                }
//// End - check      
//                if(!isALLpresentForbusEnty){
//	                for(int j=0; j<  this.businessEntityCodeList.size();j++){	                   
//	                    if(!(businessEntityValueFromSession.equals( this.businessEntityCodeList.get(j)))){
//	                        benefitComponentSaveRequest.setDeleteBenefit(true);
//	                    }	                      
//	                }
//	            }
//           }  
//        }
//        
//        // BusinessGroup Validation
//        
//        if (null != benefitComponentSessionObject.getBusinessDomainList() &&  benefitComponentSessionObject.getBusinessDomainList().size()!=0) {
//            for(int i =0 ; i < benefitComponentSessionObject.getBusinessDomainList().size();i++){
//                Domain busGrpValues = (Domain)benefitComponentSessionObject.getBusinessDomainList().get(i);
//                String businessGrp = busGrpValues.getBusinessGroup();
//                oldBusinessGrpValues.add(businessGrp);
//            }
//        }
//        
//        if(null!= oldBusinessGrpValues && oldBusinessGrpValues.size()!=0){
//            for(int i =0 ; i < oldBusinessGrpValues.size();i++){
//                String businessGrpValueFromSession = oldBusinessGrpValues.get(i).toString();
////To check if any value in the newList is ALL
//                for(int k=0; k<this.businessGroupCodeList.size() ; k++){
//                	if("ALL".equals( this.businessGroupCodeList.get(k))){
//                		isALLpresentForbusGrp = true;
//                	}
//                }
//// End - check  
//                if(!isALLpresentForbusGrp){
//	                for(int j=0; j< this.businessGroupCodeList.size();j++){	                    
//	                    if(!(businessGrpValueFromSession.equals(this.businessGroupCodeList.get(j)))){
//	                        benefitComponentSaveRequest.setDeleteBenefit(true);
//		                   
//	                    }   
//	                }
//                }    
//            }
//        }
//        
//        // LOB Validation
//        
//        if (null != benefitComponentSessionObject.getBusinessDomainList() &&  benefitComponentSessionObject.getBusinessDomainList().size()!=0) {
//            for(int i =0 ; i < benefitComponentSessionObject.getBusinessDomainList().size();i++){
//                Domain lobValues = (Domain)benefitComponentSessionObject.getBusinessDomainList().get(i);
//                String lineOfBusiness = lobValues.getLineOfBusiness();
//                oldLOBValues.add(lineOfBusiness);
//            }
//        }
//        
//        if(null!= oldLOBValues && oldLOBValues.size()!=0){
//            for(int i =0 ; i < oldLOBValues.size();i++){
//                String lobValueFromSession = oldLOBValues.get(i).toString();
////To check if any value in the newList is ALL
//                for(int k=0; k<this.lineOfBusinessCodeList.size() ; k++){
//                	if("ALL".equals( this.lineOfBusinessCodeList.get(k))){
//                		isALLpresentForLOB = true;
//                	}
//                }
//// End - check    
//                if(!isALLpresentForLOB){
//	                for(int j=0; j< this.lineOfBusinessCodeList .size();j++){	                   
//	                    if(!(lobValueFromSession.equals(this.lineOfBusinessCodeList .get(j)))){
//	                        benefitComponentSaveRequest.setDeleteBenefit(true);
//	                    }
//	                      
//	                }
//                }    
//            }
//        }
// End - Enhancement        
        
        if(this.domainChange)
        	benefitComponentSaveRequest.setDomainChange(true);
        
        return benefitComponentSaveRequest;
    }


    /**
     * Methode used to clear the page values
     * 
     * @return void
     */
    private void clearPageValues() {
        this.lineOfBusiness = WebConstants.ALL_99;
        this.businessEntity = WebConstants.ALL_99;
        this.businessGroup = WebConstants.ALL_99;
        this.marketBusinessUnit = WebConstants.ALL_99;
        this.benefitComponentName = WebConstants.EMPTY_STRING;
        this.description = WebConstants.EMPTY_STRING;
        this.effectiveDate = WebConstants.DEFAULT_EFF_DATE;
        this.expiryDate = WebConstants.DEFAULT_EXP_DATE;
        this.setBreadCrumbText(WebConstants.BENEFIT_COMPONENT_CREATE_BREADCRUMB);
        //to clear rule on refreshing
        this.ruleId = WebConstants.EMPTY_STRING;
        this.componentType = "";
        this.mandateType = "";
        this.selectedStateId = "";

        if (null != getSession().getAttribute(
                WebConstants.BENEFIT_COMPONENT_SESSION_KEY)) {
            getSession().removeAttribute(
                    WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
        }
    }


    /**
     * Method to retrieve BeneffitComponent Information and display the
     * BCGeneralInformation View page
     * 
     * @return String
     */
    public String loadBenefitComponentforView() {
        //TODO -- Remove HardCoding
        // getBenefitComponentSessionObject().setBenefitComponentMode("View");
        //TODO --
        // this.getBenefitComponentSessionObject().getBenefitComponentKey();
        //this.getBenefitComponentSessionObject().getBenefitComponentName();
        //Refers to the StandardBenefitId
        int retrieveKey = Integer.parseInt(getSession().getAttribute(
                "SESSION_BNFT_ID").toString());
        int bcId = getBenefitComponentSessionObject().getBenefitComponentId();
        String bcName = getBenefitComponentSessionObject()
                .getBenefitComponentName();
        retrieveBenefitComponentDetailsforView(retrieveKey, bcId, bcName);
      
        String tier = getAssociatedTier(retrieveKey);
        this.setTierBenefitComp(tier);
        
        
        return "benefitComponentGenInfoView";
    }


    /**
     * Method to retrieve Benefit Information and display the
     * BCGeneralInformation View page
     * 
     * @return void
     */
    private void retrieveBenefitComponentDetailsforView(int retrieveKey,
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
        BenefitComponentRetrieveResponse benefitComponentRetrieveResponse = (BenefitComponentRetrieveResponse) executeService(benefitComponentRetrieveRequest);
        //changes
        DomainDetail domainDetail = benefitComponentRetrieveResponse
                .getDomainDetail();

        if (null != benefitComponentRetrieveResponse) {
            this.setMinorHeading(benefitComponentRetrieveResponse
                    .getStandardBenefitBO().getBenefitIdentifier());
            this.setStdBenefitDescription(benefitComponentRetrieveResponse
                    .getStandardBenefitBO().getDescription());

            // changed
            this.setLineOfBusiness(WPDStringUtil.getTildaString(domainDetail
                    .getLineOfBusiness()));
            this.setBusinessEntity(WPDStringUtil.getTildaString(domainDetail
                    .getBusinessEntity()));
            this.setBusinessGroup(WPDStringUtil.getTildaString(domainDetail
                    .getBusinessGroup()));
            this.setMarketBusinessUnit(WPDStringUtil.getTildaString(domainDetail
                    .getMarketBusinessUnit()));

            this
                    .setTerm(getTildaStringFromUniverseList(benefitComponentRetrieveResponse
                            .getStandardBenefitBO().getTermList()));
            this
                    .setQualifier(getTildaStringFromUniverseList(benefitComponentRetrieveResponse
                            .getStandardBenefitBO().getQualifierList()));
            this
                    .setProviderArrangement(getTildaStringFromUniverseList(benefitComponentRetrieveResponse
                            .getStandardBenefitBO().getPVAList()));
            this
                    .setDataType(getTildaStringFromDataTypeList(benefitComponentRetrieveResponse
                            .getStandardBenefitBO().getDataTypeList()));

            // Enhancements - Start *******

            List refId = benefitComponentRetrieveResponse
                    .getStandardBenefitBO().getRuleIdList();
            List refNam = benefitComponentRetrieveResponse
                    .getStandardBenefitBO().getRuleNameList();
            List refType = benefitComponentRetrieveResponse
            .getStandardBenefitBO().getRuleTyepLst();
            String reference = convertListtoTilda(refNam, refId);
            
            for (int i = 0; i < refNam.size(); i++) {
                this.setSbRule(reference);
                this.setStrRuleType(refType.get(i).toString());
                //this.setSbRule(refId.get(i).toString()+'-'+refNam.get(i).toString());
               }

            this.setSbBenType(benefitComponentRetrieveResponse
                    .getStandardBenefitBO().getBenefitType());
            if(null != benefitComponentRetrieveResponse
                    .getStandardBenefitBO().getBenefitCategoryDesc()){
	            this.setBenefitCategory(benefitComponentRetrieveResponse
	                    .getStandardBenefitBO().getBenefitCategoryDesc());
            }
            if ((WebConstants.MNDT_TYPE).equals(this.sbBenType)) {
                this.setSbMandateType(benefitComponentRetrieveResponse
                        .getStandardBenefitBO().getMandateType());

            } else {
                this.setSbMandateType(WebConstants.EMPTY_STRING);
            }

            if ((WebConstants.MNDT_TYPE).equals(this.sbBenType)
                    && (("ST").equals(this.sbMandateType) || ("ET")
                            .equals(this.sbMandateType))) {
                // To get the state
                //String statedesc =
                // benefitComponentRetrieveResponse.getStandardBenefitBO().getStateCode();
                //String stateid =
                // benefitComponentRetrieveResponse.getStandardBenefitBO().getStateDesc();
                //String selectedStateId = statedesc + "~" + stateid;
                this.setSbSelState(benefitComponentRetrieveResponse
                        .getStandardBenefitBO().getStateDesc());
            } else if ((WebConstants.MNDT_TYPE).equals(this.sbBenType)
                    && "FED".equals(this.sbMandateType)) {
                String selectedStateId = "ALL" + "~" + "ALL";
                this.setSbSelState(selectedStateId);
            } else {
                String selectedStateId = "";
                this.setSbSelState(selectedStateId);
            }
            if (("ST").equals(this.sbMandateType)) {

                this.sbMandateType = "State";

            } else if (("ET").equals(this.sbMandateType)) {
                this.sbMandateType = "ExtraTerritorial";
            } else
                this.sbMandateType = "Federal";
            // Enhancements - End ********

            this.setCreatedUser(benefitComponentRetrieveResponse
                    .getStandardBenefitBO().getCreatedUser());
            this.setLastUpdatedUser(benefitComponentRetrieveResponse
                    .getStandardBenefitBO().getLastUpdatedUser());
            this.setCreatedTimestamp(benefitComponentRetrieveResponse
                            .getStandardBenefitBO().getCreatedTimestamp());
            this.setLastUpdatedTimestamp(benefitComponentRetrieveResponse
                            .getStandardBenefitBO().getLastUpdatedTimestamp());
            //Changes for CR
            
            this.setBenefitVersion(benefitComponentRetrieveResponse.
            		getStandardBenefitBO().getVersion());
        }
        
       
    }


    // converts List to tilda separated string
    public String convertListtoTilda(List idlist, List nameList) {
        if (idlist == null || nameList == null)
            return WebConstants.EMPTY_STRING;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < idlist.size(); i++) {
            if (i != 0)
                buffer.append("~");
            buffer.append(idlist.get(i) + "~" + nameList.get(i));
        }
        return buffer.toString();
    }

    private String getType(List typeList) {
        if(typeList == null)
            return WebConstants.EMPTY_STRING;
        StringBuffer buffer = new StringBuffer();
        
        for (int i = 0; i < typeList.size(); i++) {
          buffer.append(typeList.get(i));
        	}
        return buffer.toString();
    }
    
    
    
    

    //	 TODO Methods to be put in WPDStringUtil
    public static String getTildaStringFromUniverseList(List universeItems) {

        if (universeItems == null)
            return WebConstants.EMPTY_STRING;

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < universeItems.size(); i++) {
            UniverseBO element = (UniverseBO) universeItems.get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getCodeDescText());
            buffer.append("~" + element.getUniverseCode());
        }
        return buffer.toString();
    }


    // TODO Methods to be put in WPDStringUtil
    public static String getTildaStringFromDataTypeList(List dataTypeItems) {

        if (dataTypeItems == null)
            return WebConstants.EMPTY_STRING;

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < dataTypeItems.size(); i++) {
            StandardBenefitDatatypeBO element = (StandardBenefitDatatypeBO) dataTypeItems
                    .get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getSelectedItemCode());
            buffer.append("~" + element.getDataTypeName());
        }
        return buffer.toString();
    }


    /**
     * Methode to form and return BenefitComponentSaveRequest
     * 
     * @return BenefitComponentSaveRequest
     */
    private BenefitComponentSaveRequest getBenefitComponentSaveRequest() {
        BenefitComponentSaveRequest benefitComponentSaveRequest = (BenefitComponentSaveRequest) this
                .getServiceRequest(ServiceManager.SAVE_BENEFIT_COMPONENT_REQUEST);
        BenefitComponentVO benefitComponentVO = new BenefitComponentVO();
        benefitComponentVO.setBenefitComponentId(this.getBenefitComponentId());
        benefitComponentVO.setBenefitComponentParentId(this
                .getBenefitComponentParentId());
        benefitComponentVO.setBenefitComponentName(this
                .getBenefitComponentName());
        benefitComponentVO.setBusinessEntityCodeList(this
                .getBusinessEntityCodeList());
        benefitComponentVO.setBusinessGroupCodeList(this
                .getBusinessGroupCodeList());
        benefitComponentVO.setMarketBusinessUnitCodeList( this
        		.getMarketBusinessUnitList() );
        benefitComponentVO.setLineOfBusinessCodeList(this
                .getLineOfBusinessCodeList());
        benefitComponentVO.setEffectivedate(WPDStringUtil
                .getDateFromString(this.effectiveDate));
        benefitComponentVO.setExpirydate(WPDStringUtil
                .getDateFromString(this.expiryDate));
        benefitComponentVO.setDescription(this.getDescription());
        // enhancements

        benefitComponentVO.setComponentType(this.componentType);

        if ("MANDATE".equals(benefitComponentVO.getComponentType()))
            benefitComponentVO.setMandateType(this.getMandateType());
        else
            benefitComponentVO.setMandateType(null);
        //        if ("MANDATE".equals(benefitComponentVO.getComponentType())
        //                && "2".equals(benefitComponentVO.getMandateType())
        //                || "3".equals(benefitComponentVO.getMandateType())) {
        if ("MANDATE".equals(benefitComponentVO.getComponentType())
                && !benefitComponentVO.getMandateType().equals("FED")) {
            benefitComponentVO.setStateId(this.getStateId());
            benefitComponentVO.setStateDesc(this.getStateDesc());
        } else {
            // Modification
            if (!"STANDARD".equals(benefitComponentVO.getComponentType())
                    && "FED".equals(benefitComponentVO.getMandateType())) {
                benefitComponentVO.setStateId("ALL");
                benefitComponentVO.setStateDesc("ALL");
            } else {
                benefitComponentVO.setStateId(null);
                benefitComponentVO.setStateDesc(null);
            }
            // End - Modification
        }
        benefitComponentVO.setRuleIdList(this.getRuleIdList());
        // end - enhancements
        benefitComponentVO.setStatus(this.getStatus());
        benefitComponentVO.setVersion(this.getVersion());
        benefitComponentSaveRequest.setBenefitComponentVO(benefitComponentVO);
        benefitComponentSaveRequest.setDoneFlag(this.doneFlag);
        benefitComponentSaveRequest.setCheckInFlag(this.checkInOpted);
        return benefitComponentSaveRequest;

    }


    /**
     * to get the old Benefit Component
     * 
     * @return BenefitComponentVO
     */
    private BenefitComponentVO getOldBenefitComponent() {
        BenefitComponentVO oldBenefitComponentVO = new BenefitComponentVO();
        //**********enhancements
        oldBenefitComponentVO.setComponentType(this
                .getBenefitComponentSessionObject().getBcComponentType());
        oldBenefitComponentVO.setMandateType(this
                .getBenefitComponentSessionObject().getBcMandateType());
        oldBenefitComponentVO.setStateId(this
                .getBenefitComponentSessionObject().getBcStateId());
        oldBenefitComponentVO.setRuleIdList(this
                .getBenefitComponentSessionObject().getBcRuleIdList());

        //**************** end - enhancements
        oldBenefitComponentVO.setBenefitComponentId(this
                .getBenefitComponentSessionObject().getBenefitComponentId());
        oldBenefitComponentVO.setBenefitComponentParentId(this
                .getBenefitComponentSessionObject()
                .getBenefitComponentParentId());
        oldBenefitComponentVO.setBenefitComponentName(this
                .getBenefitComponentSessionObject().getBenefitComponentName());
        oldBenefitComponentVO.setBusinessDomainList(this
                .getBenefitComponentSessionObject().getBusinessDomainList());
        oldBenefitComponentVO.setVersion(this
                .getBenefitComponentSessionObject()
                .getBenefitComponentVersionNumber());
        oldBenefitComponentVO.setEffectivedate(this
                .getBenefitComponentSessionObject().getEffectiveDate());
        oldBenefitComponentVO.setExpirydate(this
                .getBenefitComponentSessionObject().getExpiryDate());
        return oldBenefitComponentVO;

    }


    /**
     * Action method for Updating benefit component
     * 
     * @return String
     *  
     */
    public String editBenefitComponent() {
        this.editFlag = true;

        return this.saveBenefitComponent();
    }


    /**
     * Action Method for Checkin and validation
     * 
     * @return String
     *  
     */

    public String done() {
        if (!validateFields()) {
            addAllMessagesToRequest(this.validationMessages);
            return WebConstants.EMPTY_STRING;
        } else {
            this.editFlag = true;
            this.doneFlag = true;
            return this.saveBenefitComponent();
        }
    }


    /**
     * Methode for validating input values
     * 
     * @return boolean
     *  
     */
    public boolean validateFields() {
        lobValdn = false;
        businessentityValdn = false;
        businessgroupValdn = false;
        requiredMarketBusinessUnit = false;
        nameValdn = false;
        effdateValdn = false;
        expdateValdn = false;
        descValdn = false;
        compTypeValdn = false;
        mandateTypeValdn = false;
        ruleIdValdn = false;
        stateIdValdn = false;
        allfieldsvalid = false;

        if (!isAllFieldsBlank()) {
            addAllMessagesToRequest(validationMessages);
        } else if (!validateDateFormat()) {
            addAllMessagesToRequest(validationMessages);
            validationStatus = false;
        } else if (!validateEffectiveDate(this.getEffectiveDate())) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.EFFECTIVE_DATE_INVALID));
            addAllMessagesToRequest(validationMessages);
            validationStatus = false;
        } else if (!isDateValid()) {
            addAllMessagesToRequest(validationMessages);
            validationStatus = false;
        } else if (!validateNameLength()) {
            addAllMessagesToRequest(validationMessages);
            validationStatus = false;
        } else if (!validateDescriptionLength()) {
            addAllMessagesToRequest(validationMessages);

        }
		else {
            validationStatus = true;
        }
        return validationStatus;
    }


    /**
     * Methode for validating the name length
     * 
     * @return boolean
     */
    private boolean validateNameLength() {
        validationMessages = new ArrayList(10);
        if (null != this.benefitComponentName) {
            int nameLength = this.benefitComponentName.trim().length();
            if (nameLength > 34 || nameLength < 2) {
                validationMessages.add(new ErrorMessage(
                        WebConstants.BENEFIT_COMPONENT_INVALID_NAME));
                validationStatus = false;
                nameValdn = true;
            } else {
                validationStatus = true;
            }
        } else {
            return false;
        }
        return validationStatus;
    }
    
 

    /**
     * Methode for validating the effective and expiry dates
     * 
     * @return boolean
     */
    public boolean isDateValid() {
        Date effDate = WPDStringUtil.getDateFromString(this.getEffectiveDate());
        Date expDate = WPDStringUtil.getDateFromString(this.getExpiryDate());

        if (!(expDate.after(effDate))) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.EFFECIVE_GRATER_EXPIRY));
            effdateValdn = true;
            expdateValdn = true;
            validationStatus = false;
        }
        return validationStatus;
    }


    /**
     * Methode for validating date format
     * 
     * @return boolean
     */
    public boolean validateDateFormat() {
    	
    	if ( !(effectiveDate.trim().equals("")) && !StringUtil.isDate(effectiveDate)) {
    		effdateValdn = true;
		 	ErrorMessage errorMessage = new ErrorMessage(
                    WebConstants.INPUT_FORMAT_INVALID);
            errorMessage.setParameters(new String[] { "Effective Date" });
            validationMessages.add(errorMessage);
		 }else if (!(expiryDate.trim().equals("")) && !StringUtil.isDate(expiryDate)) {
		 	expdateValdn = true;
		 	 ErrorMessage errorMessage = new ErrorMessage(
                    WebConstants.INPUT_FORMAT_INVALID);
            errorMessage.setParameters(new String[] { "Expiry Date" });
            validationMessages.add(errorMessage);
		 }
  
        if (effdateValdn == true || expdateValdn == true) {
            return false;
        }
        return true;
    }


    /**
     * Methode for validating the mandatory fields
     * 
     * @return boolean
     */
    public boolean isAllFieldsBlank() {

        if ((null == this.lineOfBusiness) || (WebConstants.EMPTY_STRING.equals(this.lineOfBusiness))) {
            lobValdn = true;
        }
        if ((null == this.businessEntity) || (WebConstants.EMPTY_STRING.equals(this.businessEntity))) {
            businessentityValdn = true;
        }
        if ((null == this.businessGroup) || (WebConstants.EMPTY_STRING.equals(this.businessGroup))) {
            businessgroupValdn = true;
        }
        if((null == this.marketBusinessUnit) || (WebConstants.EMPTY_STRING.equals(this.marketBusinessUnit))){
        	requiredMarketBusinessUnit = true;
        }
        if ((null == this.benefitComponentName)
                || (WebConstants.EMPTY_STRING.equals(this.benefitComponentName))) {
            nameValdn = true;
        }
        if ((null == this.effectiveDate)
                || (WebConstants.EMPTY_STRING.equals(this.effectiveDate.trim()))) {
            effdateValdn = true;
        }
        if ((null == this.expiryDate) || (WebConstants.EMPTY_STRING.equals(this.expiryDate.trim()))) {
            expdateValdn = true;
        }

        if ((null == this.description) || (WebConstants.EMPTY_STRING.equals(this.description))) {
            descValdn = true;
        }
        if ((null == this.componentType) || (WebConstants.EMPTY_STRING.equals(this.componentType))) {
            compTypeValdn = true;
        }
        if ("MANDATE".equals(this.getComponentType())) {
            if ((null == this.mandateType) || (WebConstants.EMPTY_STRING.equals(this.mandateType)))
                mandateTypeValdn = true;
        }
        if ("MANDATE".equals(this.getComponentType())
                && ("ST".equals(this.getMandateType()) || "ET".equals(this
                        .getMandateType()))) {
            if ((null == this.selectedStateId)
                    || (WebConstants.EMPTY_STRING.equals(this.selectedStateId) || (" ~ "
                            .equals(this.selectedStateId) || (" ".equals(this.selectedStateId)))))
                stateIdValdn = true;
        }
        
        //Mandatory check removed for GB,GP & Supplemental Benefits.
       if(WebConstants.GEN_BENEFITS.equals(this.benefitComponentName.toUpperCase())){
       		ruleIdValdn = false;
       }else if(WebConstants.GENERAL_PROVISIONS.equals(this.benefitComponentName.toUpperCase())){
   			ruleIdValdn = false;
       }else if(WebConstants.SUPPLEMENTAL_BENEFITS.equals(this.benefitComponentName.toUpperCase())){
			ruleIdValdn = false;
       }else if ((null == this.ruleId) || (WebConstants.EMPTY_STRING.equals(this.ruleId))) {
            ruleIdValdn = true;
        }
        if (lobValdn == false && businessentityValdn == false
                && businessgroupValdn == false 
				&& requiredMarketBusinessUnit == false
				&& nameValdn == false
                && effdateValdn == false && expdateValdn == false
                && descValdn == false && compTypeValdn == false
                && mandateTypeValdn == false && stateIdValdn == false
                && ruleIdValdn == false) {
            validationStatus = true;
        }        
        else {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            validationStatus = false;
        }
        return validationStatus;
    }
//    else if(errorFlagForNullState == true){   
//        this.setSelectedStateId("");
//        this.setOldSelectedStateId("");
//        validationMessages.add(new ErrorMessage(
//                WebConstants.BUSINESS_DOMAIN_CHANGED_PLEASE_CHANGE_STATE));
//        validationStatus = false;
//        
//    }

    /**
     * Method to validate the Mandate description given from the JSP page.
     * 
     * @return boolean
     */
    public boolean validateDescriptionLength() {
        validationMessages = new ArrayList(10);
        int descLength = this.description.trim().length();
        if (descLength < 10 || descLength > 250) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.DESCRIPTION_SIZE_2_250));
            validationStatus = false;
            descValdn = true;
            return false;
        }
        return true;
    }


    /**
     * Validation function for expiry date
     * 
     * @return boolean
     */
    public boolean validateEffectiveDate(String effectiveDate) {
        validationMessages = new ArrayList(10);
        Date effectiveDateForCheck = WPDStringUtil
                .getDateFromString(effectiveDate);
        Date boundaryDate = WPDStringUtil.getDateFromString("01/01/1900");
        if (effectiveDateForCheck.compareTo(boundaryDate) < 0)
            return false;
        return true;
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
     * Method to load the benefit hierarchy tab
     * 
     * @return String
     */
    public String loadBenefitHierarchyTab() {

        return "benefitHierarchyPage";
    }


    /**
     * Method to retreive Benefit Component for GenInfo tab
     * 
     * @return String for page navigation
     */
    public String loadUpdateTab() {
        int retrieveKey = this.getBenefitComponentSessionObject()
                .getBenefitComponentId();
        String retrieveName = this.getBenefitComponentSessionObject()
                .getBenefitComponentName();
        int retrieveVersionNumber = this.getBenefitComponentSessionObject()
                .getBenefitComponentVersionNumber();
        List domainList = this.getBenefitComponentSessionObject()
                .getBusinessDomainList();
        retrieveBenefitComponentDetails(retrieveKey, retrieveName,
                retrieveVersionNumber, domainList,this.editMode);
        return WebConstants.BENEFIT_COMPONENT_EDIT;
    }


    /**
     * benefit component View
     * 
     * @return String
     */
    public String loadComponentBenefitforView() {
        int retrieveKey = Integer.parseInt(getSession().getAttribute(
                WebConstants.BENEFIT_COMPONENT_ID).toString());
        int bcId = getBenefitComponentSessionObject().getBenefitComponentId();
        String bcName = getBenefitComponentSessionObject()
                .getBenefitComponentName();
        retrieveBenefitComponentDetailsforView(retrieveKey, bcId, bcName);
        this.setBreadCrumbText(WebConstants.BENEFIT_COMPONENT_BREADCRUMB
                + " (" + bcName + ") >> View");
        getBenefitComponentSessionObject().setBenefitComponentMode("View");
        
        //for retrieving the tier for a benefit definition
      
        String tier = getAssociatedTier(retrieveKey);
        this.setTierBenefitComp(tier);
        
        return "componentBenefitGenInfoforView";
    }


    /**
     * 
     * @return BenefitComponentCheckInRequest
     */
//    private BenefitComponentCheckInRequest getBenefitComponentCheckInRequest() {
//        BenefitComponentCheckInRequest benefitComponentCheckInRequest = (BenefitComponentCheckInRequest) this
//                .getServiceRequest(ServiceManager.CHECKIN_BENEFIT_COMPONENT);
//        BenefitComponentVO benefitComponentVO = new BenefitComponentVO();
//        benefitComponentVO.setBenefitComponentId(this.getBenefitComponentId());
//        benefitComponentVO.setBenefitComponentParentId(this
//                .getBenefitComponentParentId());
//        benefitComponentVO.setBenefitComponentName(this
//                .getBenefitComponentName());
//        benefitComponentVO.setBusinessEntityCodeList(this
//                .getBusinessEntityCodeList());
//        benefitComponentVO.setBusinessGroupCodeList(this
//                .getBusinessGroupCodeList());
//        benefitComponentVO.setLineOfBusinessCodeList(this
//                .getLineOfBusinessCodeList());
//        benefitComponentVO.setBusinessDomainList(this
//                .getBenefitComponentSessionObject().getBusinessDomainList());
//        benefitComponentVO.setEffectivedate(WPDStringUtil
//                .getDateFromString(this.effectiveDate));
//        benefitComponentVO.setExpirydate(WPDStringUtil
//                .getDateFromString(this.expiryDate));
//        benefitComponentVO.setDescription(this.getDescription());
//        benefitComponentVO.setStatus(this.getStatus());
//        benefitComponentVO.setVersion(this.getVersion());
//        benefitComponentVO.setCheckInOpted(this.checkInOpted);
//        benefitComponentCheckInRequest
//                .setBenefitComponentVO(benefitComponentVO);
//        return benefitComponentCheckInRequest;
//
//    }


    /**
     * Returns the benefitComponentId
     * 
     * @return int benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * Sets the benefitComponentId
     * 
     * @param benefitComponentId.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }


    /**
     * @return Returns the benefitComponentName.
     */
    public String getBenefitComponentName() {
        return benefitComponentName;
    }


    /**
     * @param benefitComponentName
     *            The benefitComponentName to set.
     */
    public void setBenefitComponentName(String benefitComponentName) {
        StringTokenizer st = new StringTokenizer(benefitComponentName, " \t");
        StringBuffer buf = new StringBuffer();
        while (st.hasMoreTokens()) {
            buf.append(" ").append(st.nextToken());
        }
        benefitComponentName = buf.toString().trim();
        this.benefitComponentName = benefitComponentName;
    }


    /**
     * @return Returns the businessEntity.
     */
    public String getBusinessEntity() {
        return businessEntity;
    }


    /**
     * @param businessEntity
     *            The businessEntity to set.
     */
    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }


    /**
     * @return Returns the businessGroup.
     */
    public String getBusinessGroup() {
        return businessGroup;
    }


    /**
     * @param businessGroup
     *            The businessGroup to set.
     */
    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }


    /**
     * @return Returns the description.
     */
    public String getDescription() {
        if (null != description) {
            return description.trim();
        }
        return null;
    }


    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        StringTokenizer st = new StringTokenizer(description, " \t");
        StringBuffer buf = new StringBuffer();
        while (st.hasMoreTokens()) {
            buf.append(" ").append(st.nextToken());
        }
        description = buf.toString().trim().toUpperCase();
        this.description = description;
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
        this.expiryDate = expiryDate;
    }


    /**
     * @return Returns the lineOfBusiness.
     */
    public String getLineOfBusiness() {
        return lineOfBusiness;
    }


    /**
     * @param lineOfBusiness
     *            The lineOfBusiness to set.
     */
    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }


    /**
     * @return Returns the businessEntityCodeList.
     */
    public List getBusinessEntityCodeList() {
        return businessEntityCodeList;
    }


    /**
     * @param businessEntityCodeList
     *            The businessEntityCodeList to set.
     */
    public void setBusinessEntityCodeList(List businessEntityCodeList) {
        this.businessEntityCodeList = businessEntityCodeList;
    }


    /**
     * @return Returns the businessGroupCodeList.
     */
    public List getBusinessGroupCodeList() {
        return businessGroupCodeList;
    }


    /**
     * @param businessGroupCodeList
     *            The businessGroupCodeList to set.
     */
    public void setBusinessGroupCodeList(List businessGroupCodeList) {
        this.businessGroupCodeList = businessGroupCodeList;
    }


    /**
     * @return Returns the lineOfBusinessCodeList.
     */
    public List getLineOfBusinessCodeList() {
        return lineOfBusinessCodeList;
    }


    /**
     * @param lineOfBusinessCodeList
     *            The lineOfBusinessCodeList to set.
     */
    public void setLineOfBusinessCodeList(List lineOfBusinessCodeList) {
        this.lineOfBusinessCodeList = lineOfBusinessCodeList;
    }


    /**
     * @return Returns the businessentityValdn.
     */
    public boolean isBusinessentityValdn() {
        return businessentityValdn;
    }


    /**
     * @param businessentityValdn
     *            The businessentityValdn to set.
     */
    public void setBusinessentityValdn(boolean businessentityValdn) {
        this.businessentityValdn = businessentityValdn;
    }


    /**
     * @return Returns the businessgroupValdn.
     */
    public boolean isBusinessgroupValdn() {
        return businessgroupValdn;
    }


    /**
     * @param businessgroupValdn
     *            The businessgroupValdn to set.
     */
    public void setBusinessgroupValdn(boolean businessgroupValdn) {
        this.businessgroupValdn = businessgroupValdn;
    }


    /**
     * @return Returns the descValdn.
     */
    public boolean isDescValdn() {
        return descValdn;
    }


    /**
     * @param descValdn
     *            The descValdn to set.
     */
    public void setDescValdn(boolean descValdn) {
        this.descValdn = descValdn;
    }


    /**
     * @return Returns the effdateValdn.
     */
    public boolean isEffdateValdn() {
        return effdateValdn;
    }


    /**
     * @param effdateValdn
     *            The effdateValdn to set.
     */
    public void setEffdateValdn(boolean effdateValdn) {
        this.effdateValdn = effdateValdn;
    }


    /**
     * @return Returns the expdateValdn.
     */
    public boolean isExpdateValdn() {
        return expdateValdn;
    }


    /**
     * @param expdateValdn
     *            The expdateValdn to set.
     */
    public void setExpdateValdn(boolean expdateValdn) {
        this.expdateValdn = expdateValdn;
    }


    /**
     * @return Returns the lobValdn.
     */
    public boolean isLobValdn() {
        return lobValdn;
    }


    /**
     * @param lobValdn
     *            The lobValdn to set.
     */
    public void setLobValdn(boolean lobValdn) {
        this.lobValdn = lobValdn;
    }


    /**
     * @return Returns the nameValdn.
     */
    public boolean isNameValdn() {
        return nameValdn;
    }


    /**
     * @param nameValdn
     *            The nameValdn to set.
     */
    public void setNameValdn(boolean nameValdn) {
        this.nameValdn = nameValdn;
    }


    /**
     * Returns the createdUser
     * 
     * @return String createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }


    /**
     * Sets the createdUser
     * 
     * @param createdUser.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }


    /**
     * @return Returns the createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }


    /**
     * @param createdTimestamp
     *            The createdTimestamp to set.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }


    /**
     * @return Returns the lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }


    /**
     * @param lastUpdatedTimestamp
     *            The lastUpdatedTimestamp to set.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }


    /**
     * Returns the lastUpdatedUser
     * 
     * @return String lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }


    /**
     * Sets the lastUpdatedUser
     * 
     * @param lastUpdatedUser.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
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
     * @return Returns the selectedBenefitComponentKey.
     */
    public String getSelectedBenefitComponentKey() {
        return selectedBenefitComponentKey;
    }


    /**
     * @param selectedBenefitComponentKey
     *            The selectedBenefitComponentKey to set.
     */
    public void setSelectedBenefitComponentKey(
            String selectedBenefitComponentKey) {
        this.selectedBenefitComponentKey = selectedBenefitComponentKey;
    }


    /**
     * @return Returns the selectedBenefitComponentName.
     */
    public String getSelectedBenefitComponentName() {
        return selectedBenefitComponentName;
    }


    /**
     * @param selectedBenefitComponentName
     *            The selectedBenefitComponentName to set.
     */
    public void setSelectedBenefitComponentName(
            String selectedBenefitComponentName) {
        this.selectedBenefitComponentName = selectedBenefitComponentName;
    }


    /**
     * @return Returns the selectedBenefitComponentVersion.
     */
    public String getSelectedBenefitComponentVersion() {
        return selectedBenefitComponentVersion;
    }


    /**
     * @param selectedBenefitComponentVersion
     *            The selectedBenefitComponentVersion to set.
     */
    public void setSelectedBenefitComponentVersion(
            String selectedBenefitComponentVersion) {
        this.selectedBenefitComponentVersion = selectedBenefitComponentVersion;
    }


    /**
     * @return Returns the dataType.
     */
    public String getDataType() {
        return dataType;
    }


    /**
     * @param dataType
     *            The dataType to set.
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }


    /**
     * @return Returns the minorHeading.
     */
    public String getMinorHeading() {
        return minorHeading;
    }


    /**
     * @param minorHeading
     *            The minorHeading to set.
     */
    public void setMinorHeading(String minorHeading) {
        this.minorHeading = minorHeading;
    }


    /**
     * @return Returns the providerArrangement.
     */
    public String getProviderArrangement() {
        return providerArrangement;
    }


    /**
     * @param providerArrangement
     *            The providerArrangement to set.
     */
    public void setProviderArrangement(String providerArrangement) {
        this.providerArrangement = providerArrangement;
    }


    /**
     * @return Returns the qualifier.
     */
    public String getQualifier() {
        return qualifier;
    }


    /**
     * @param qualifier
     *            The qualifier to set.
     */
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }


    /**
     * @return Returns the stdBenefitDescription.
     */
    public String getStdBenefitDescription() {
        return stdBenefitDescription;
    }


    /**
     * @param stdBenefitDescription
     *            The stdBenefitDescription to set.
     */
    public void setStdBenefitDescription(String stdBenefitDescription) {
        this.stdBenefitDescription = stdBenefitDescription;
    }


    /**
     * @return Returns the term.
     */
    public String getTerm() {
        return term;
    }


    /**
     * @param term
     *            The term to set.
     */
    public void setTerm(String term) {
        this.term = term;
    }


    /**
     * Returns the benefitComponentParentId
     * 
     * @return int benefitComponentParentId.
     */
    public int getBenefitComponentParentId() {
        return benefitComponentParentId;
    }


    /**
     * Sets the benefitComponentParentId
     * 
     * @param benefitComponentParentId.
     */
    public void setBenefitComponentParentId(int benefitComponentParentId) {
        this.benefitComponentParentId = benefitComponentParentId;
    }


    /**
     * @return Returns the selectedDomainList.
     */
    public List getSelectedDomainList() {
        return selectedDomainList;
    }


    /**
     * @param selectedDomainList
     *            The selectedDomainList to set.
     */
    public void setSelectedDomainList(List selectedDomainList) {
        this.selectedDomainList = selectedDomainList;
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
     * While clicking benefit component page search result -> Edit icon, datas
     * are fetched from database and are loaded to this page using this
     * function.
     * 
     * @return String
     */
    public String loadBenefitComponentForCopy() {
        // get the key and name
        int retrieveKey = 0;
        int versionNumber = 0;
        boolean viewAllFlag = true;
        getSession().removeAttribute("SESSION_TREE_STATE_BC");
        if (null != this.selectedBenefitComponentKey
                && !this.selectedBenefitComponentKey.equals(WebConstants.EMPTY_STRING)) {
            retrieveKey = Integer.parseInt(this.selectedBenefitComponentKey);
        }
        if (null != this.selectedBenefitComponentVersion
                && !this.selectedBenefitComponentVersion.equals(WebConstants.EMPTY_STRING)) {
            versionNumber = Integer
                    .parseInt(this.selectedBenefitComponentVersion);
        }
        String retrieveName = this.getSelectedBenefitComponentName();
        List domainList = new ArrayList(1);
        // get the benefitComponent session object and set all the values
        this.getBenefitComponentSessionObject().setBenefitComponentMode(WebConstants.MODE);
        this.getBenefitComponentSessionObject().setBenefitComponentId(
                retrieveKey);
        this.getBenefitComponentSessionObject().setBenefitComponentName(
                retrieveName);
        this.getBenefitComponentSessionObject()
                .setBenefitComponentVersionNumber(versionNumber);
        List searchResultList = (List) getSession().getAttribute(
                "benefitComponentSearchResult");
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList
                        .get(i);
                if (retrieveKey == benefitComponentBO.getBenefitComponentId()) {
                    this.getBenefitComponentSessionObject()
                            .setBusinessDomainList(
                                    benefitComponentBO.getBusinessDomainList());
                    this.getBenefitComponentSessionObject()
                            .setBenefitComponentVersionNumber(
                                    benefitComponentBO.getVersion());
                    domainList = benefitComponentBO.getBusinessDomainList();
                    viewAllFlag = false;
                    break;
                }
            }
        }

        if (viewAllFlag) {
            List searchResultListForViewAll = (List) getSession().getAttribute(
                    "benefitComponentViewAllSearchResult");
            if (null != searchResultListForViewAll
                    && !searchResultListForViewAll.isEmpty()) {
                for (int i = 0; i < searchResultListForViewAll.size(); i++) {
                    BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultListForViewAll
                            .get(i);
                    if (retrieveKey == benefitComponentBO
                            .getBenefitComponentId()) {
                        this.getBenefitComponentSessionObject()
                                .setBusinessDomainList(
                                        benefitComponentBO
                                                .getBusinessDomainList());
                        this.getBenefitComponentSessionObject()
                                .setBenefitComponentVersionNumber(
                                        benefitComponentBO.getVersion());
                        this.getBenefitComponentSessionObject()
                                .setBenefitComponentName(
                                        benefitComponentBO.getName());
                        this.getBenefitComponentSessionObject()
                                .setBenefitComponentId(
                                        benefitComponentBO
                                                .getBenefitComponentId());
                        retrieveKey = this.getBenefitComponentSessionObject()
                                .getBenefitComponentId();
                        versionNumber = this.getBenefitComponentSessionObject()
                                .getBenefitComponentVersionNumber();
                        domainList = benefitComponentBO.getBusinessDomainList();
                        break;
                    }
                }
            }
        }
        // retrieve the selectedBenefitComponentDetails
        retrieveBenefitComponentDetails(retrieveKey, retrieveName,
                versionNumber, domainList,editMode);
        // TODO
        // forward to the '' page
        this.setCreateFlag(false);
        this.setCopyFlag(true);
        // forward to the '' page
        this.setBreadCrumbText("Product Configuration >> Benefit Component ("
                + this.getBenefitComponentSessionObject().getBenefitComponentName() + ") >> Copy");
        return WebConstants.BENEFIT_COMPONENT_COPY;
    }


    /**
     * Action method for copy benefit Component
     * 
     * @return String
     *  
     */
    public String copyBenefitComponent() {
        validationStatus = validateFields();
        if (validationStatus) {
            BenefitComponentCopyRequest benefitComponentCopyRequest = (BenefitComponentCopyRequest) this
                    .getServiceRequest(ServiceManager.BENEFIT_COMPONENT_COPY_REQUEST);
            this.lineOfBusinessCodeList = WPDStringUtil.getListFromTildaString(
                    this.getLineOfBusiness(), 2, 2, 2);
            this.businessEntityCodeList = WPDStringUtil.getListFromTildaString(
                    this.getBusinessEntity(), 2, 2, 2);
            this.businessGroupCodeList = WPDStringUtil.getListFromTildaString(
                    this.getBusinessGroup(), 2, 2, 2);
            this.marketBusinessUnitList = WPDStringUtil.getListFromTildaString(
            		this.getMarketBusinessUnit(), 2, 2, 2);
            BenefitComponentVO benefitComponentVO = new BenefitComponentVO();
            benefitComponentVO.setBenefitComponentId(this
                    .getBenefitComponentId());
            benefitComponentVO.setBenefitComponentParentId(this
                    .getBenefitComponentParentId());
            benefitComponentVO.setBenefitComponentName(this
                    .getBenefitComponentName());
            benefitComponentVO.setBusinessEntityCodeList(this
                    .getBusinessEntityCodeList());
            benefitComponentVO.setBusinessGroupCodeList(this
                    .getBusinessGroupCodeList());
            benefitComponentVO.setMarketBusinessUnitCodeList(this
            		.getMarketBusinessUnitList());
            benefitComponentVO.setLineOfBusinessCodeList(this
                    .getLineOfBusinessCodeList());
            benefitComponentVO.setEffectivedate(WPDStringUtil
                    .getDateFromString(this.effectiveDate));
            benefitComponentVO.setExpirydate(WPDStringUtil
                    .getDateFromString(this.expiryDate));
            benefitComponentVO.setDescription(this.getDescription());
            benefitComponentVO.setStatus(this.getStatus());
            benefitComponentVO.setVersion(this.getVersion());
            benefitComponentVO.setComponentType(this.getComponentType());

            //To split the stateId values
            StringTokenizer st = null;
            if(null != selectedStateId && !"".equals(selectedStateId))
                st = new StringTokenizer(selectedStateId, "~");
            if (null != st && st.hasMoreTokens()) { 
                if(st.countTokens() > 1){
	                this.stateId = st.nextToken();
	                this.stateDesc = st.nextToken();   
                }
            }

            if (WebConstants.MANDATE_TYPE.equals(benefitComponentVO.getComponentType()))
                benefitComponentVO.setMandateType(this.getMandateType());
            else
                benefitComponentVO.setMandateType(null);
            if (WebConstants.MANDATE_TYPE.equals(benefitComponentVO.getComponentType())
                    && WebConstants.STATE_TYPE.equals(benefitComponentVO.getMandateType())
                    || WebConstants.EXTRA_TERRITORIAL_TYPE.equals(benefitComponentVO.getMandateType())) {
                benefitComponentVO.setStateId(this.getStateId());
                benefitComponentVO.setStateDesc(this.getStateDesc());
            } else {
                benefitComponentVO.setStateId(null);
                benefitComponentVO.setStateDesc(null);
            }

            this.ruleIdList = WPDStringUtil.getListFromTildaString(this
                    .getRuleId(), 2, 2, 2);

            benefitComponentVO.setRuleIdList(this.getRuleIdList());
            benefitComponentCopyRequest
                    .setBenefitComponentVO(benefitComponentVO);
            benefitComponentCopyRequest.setOldBenefitComponentVO(this
                    .getOldBenefitComponent());
            // Enhancement
            setDeleteBenefitFlag(benefitComponentCopyRequest);
            // End - Enhancement
            BenefitComponentCopyResponse benefitComponentCopyResponse = (BenefitComponentCopyResponse) this
                    .executeService(benefitComponentCopyRequest);
            if (null != benefitComponentCopyResponse) {
                if (null != benefitComponentCopyResponse
                        .getBenefitComponentBO()) {
                    this.benefitComponentId = benefitComponentCopyResponse
                            .getBenefitComponentBO().getBenefitComponentId();
                    this.benefitComponentName = benefitComponentCopyResponse
                            .getBenefitComponentBO().getName();
// Enhancement
                    this.benefitComponentParentId = benefitComponentCopyResponse
                    .getBenefitComponentBO().getBenefitComponentParentId();
// End - Enhancement                    
                    this.setDescription(benefitComponentCopyResponse
                            .getBenefitComponentBO().getDescription());
                    this.setCreatedUser(benefitComponentCopyResponse
                            .getBenefitComponentBO().getCreatedUser());
                    this.setCreatedTimestamp(benefitComponentCopyResponse
                                    .getBenefitComponentBO()
                                    .getCreatedTimestamp());
                    this.setLastUpdatedUser(benefitComponentCopyResponse
                            .getBenefitComponentBO().getLastUpdatedUser());
                    this.setLastUpdatedTimestamp(benefitComponentCopyResponse
                                    .getBenefitComponentBO()
                                    .getLastUpdatedTimestamp());
                    if (null != benefitComponentCopyResponse
                            .getBenefitComponentBO().getState()) {
                        this.setState(benefitComponentCopyResponse
                                .getBenefitComponentBO().getState().getState());
                    }
                    this.setStatus(benefitComponentCopyResponse
                            .getBenefitComponentBO().getStatus());
                    this.setVersion(benefitComponentCopyResponse
                            .getBenefitComponentBO().getVersion());
                    this.setLineOfBusiness(WPDStringUtil
                            .getTildaString(benefitComponentCopyResponse
                                    .getDomailDetail().getLineOfBusiness()));
                    this.setBusinessEntity(WPDStringUtil
                            .getTildaString(benefitComponentCopyResponse
                                    .getDomailDetail().getBusinessEntity()));
                    this.setBusinessGroup(WPDStringUtil
                            .getTildaString(benefitComponentCopyResponse
                                    .getDomailDetail().getBusinessGroup()));
                    this.setMarketBusinessUnit(WPDStringUtil
                    		.getTildaString(benefitComponentCopyResponse
                                    .getDomailDetail().getMarketBusinessUnit()));
                    this
            		.setOldLineOfBusinessCode(WPDStringUtil
                    .getTildaString(benefitComponentCopyResponse
                            .getDomailDetail().getLineOfBusiness()));
		            this
		                    .setOldBusinessEntityCode(WPDStringUtil
		                            .getTildaString(benefitComponentCopyResponse
		                                    .getDomailDetail().getBusinessEntity()));
		            this.setOldBusinessGroupCode(WPDStringUtil
		                    .getTildaString(benefitComponentCopyResponse
                                    .getDomailDetail().getBusinessGroup()));
		            this.setOldmarketBusinessUnit(WPDStringUtil
		                    .getTildaString(benefitComponentCopyResponse
                                    .getDomailDetail().getMarketBusinessUnit()));
//Added
 					if (WebConstants.MANDATE_TYPE.equals(this.getComponentType())) {
                            this.setMandateType((benefitComponentCopyResponse
                                    .getBenefitComponentBO().getMandateType()));
                            this.setOldMandateType((benefitComponentCopyResponse
                                    .getBenefitComponentBO().getMandateType()));
                        } else {
                            this.setMandateType(WebConstants.EMPTY_STRING);
                        }
 					
 					if (WebConstants.MANDATE_TYPE.equals(this.getComponentType())
                            && !this.getMandateType().equals(WebConstants.FEDERAL_TYPE)) {
                        // To get the state
                        String stateid = benefitComponentCopyResponse
                                .getBenefitComponentBO().getStateId();
                        String statedesc = benefitComponentCopyResponse
                                .getBenefitComponentBO().getStateDesc();
                        String selectedStateId = stateid + "~" + statedesc;
                        this.setSelectedStateId(selectedStateId);
                        this.setOldSelectedStateId(selectedStateId);
                    } else {
                        String selectedStateId = "";
                        this.setSelectedStateId(selectedStateId);
                        this.setOldSelectedStateId(selectedStateId);
                    }
 											
 //End		            
                    this.getBenefitComponentSessionObject()
                            .setBenefitComponentId(benefitComponentId);
                    this.getBenefitComponentSessionObject()
                            .setBenefitComponentName(
                                    benefitComponentCopyResponse
                                            .getBenefitComponentBO().getName());
                    this.getBenefitComponentSessionObject()
                            .setBenefitComponentVersionNumber(
                                    benefitComponentCopyResponse
                                            .getBenefitComponentBO()
                                            .getVersion());
                    //Set the status and state to the session
                    this.getBenefitComponentSessionObject()
                    .setStatus(
                            benefitComponentCopyResponse
                                    .getBenefitComponentBO()
                                    .getStatus());
                    StateImpl impl = (StateImpl) benefitComponentCopyResponse.getBenefitComponentBO().getState();
                    if(null != impl){
                    	this.getBenefitComponentSessionObject().setComponentState(impl.getState());
                    }
//                    this.getBenefitComponentSessionObject()
//                    .setState(
//                            benefitComponentCopyResponse
//                                    .getBenefitComponentBO()
//                                    .getState().getState());
                    this.getBenefitComponentSessionObject()
                            .setBusinessDomainList(
                                    benefitComponentCopyResponse
                                            .getDomailDetail().getDomainList());
                    this.getBenefitComponentSessionObject()
                            .setBenefitComponentMode(WebConstants.MODE);
                    this
                            .setOldEffectiveDate(WPDStringUtil
                                    .convertDateToString(benefitComponentCopyResponse
                                            .getBenefitComponentBO()
                                            .getEffectiveDate()));
                    this.setOldExpiryDate(WPDStringUtil
                            .convertDateToString(benefitComponentCopyResponse
                                    .getBenefitComponentBO().getExpiryDate()));

                    //Enhancement
                    this.getBenefitComponentSessionObject()
                            .setBcComponentType(
                                    benefitComponentCopyResponse
                                            .getBenefitComponentBO()
                                            .getComponentType());
                    this.getBenefitComponentSessionObject().setBenefitComponentParentId( benefitComponentCopyResponse
                                            .getBenefitComponentBO()
                                            .getBenefitComponentParentId());
                    this
                    .setBreadCrumbText(WebConstants.BENEFIT_COMPONENT_BREADCRUMB
                            + "("
                            + this
                                    .getBenefitComponentSessionObject()
                                    .getBenefitComponentName()
                            + ") >> Edit");
                    //End - Enhancement

                    return WebConstants.BENEFIT_COMPONENT_EDIT;
                }
                return WebConstants.BENEFIT_COMPONENT_COPY;
            }
        }
        return WebConstants.EMPTY_STRING;
    }

    /**
     * Method to delete the benefits attached to the benefitComponent
     * in case the type doesnt match
     * @param benefitComponentCopyRequest
     * @return benefitComponentCopyRequest
     */
    private BenefitComponentCopyRequest setDeleteBenefitFlag(
            BenefitComponentCopyRequest benefitComponentCopyRequest) {
        BenefitComponentSessionObject benefitComponentSessionObject = getBenefitComponentSessionObject();
        if (null != benefitComponentSessionObject.getBcComponentType()) {
            if (!(this.componentType.equals(benefitComponentSessionObject
                    .getBcComponentType()))) {
                benefitComponentCopyRequest.setDeleteBenefit(true);
            }
        }
        if (null != benefitComponentSessionObject.getBcMandateType()) {
            if (WebConstants.EMPTY_STRING != this.mandateType) {
                if (!(this.mandateType.equals(benefitComponentSessionObject
                        .getBcMandateType()))) {
                    benefitComponentCopyRequest.setDeleteBenefit(true);
                }
            }
        }
        if (null != benefitComponentSessionObject.getBcStateId()) {
            if (WebConstants.EMPTY_STRING != this.stateId && null!=this.stateId) {
                if (!(this.stateId.equals(benefitComponentSessionObject
                        .getBcStateId()))) {
                    benefitComponentCopyRequest.setDeleteBenefit(true);
                }
            }
        }
        
        if(this.domainChange)
        	benefitComponentCopyRequest.setDomainChange(true);
        
        return benefitComponentCopyRequest;
    }


    /**
     * Action methode for Creating the Benefit Component
     * 
     * @return String
     *  
     */
    public String createBenefitComponent() {
        if (isCreateFlag())
            return this.saveBenefitComponent();
        else
            return this.copyBenefitComponent();
    }


    /**
     * Returns the checkInOpted
     * 
     * @return boolean checkInOpted.
     */
    public boolean isCheckInOpted() {
        return checkInOpted;
    }


    /**
     * Sets the checkInOpted
     * 
     * @param checkInOpted.
     */
    public void setCheckInOpted(boolean checkInOpted) {
        this.checkInOpted = checkInOpted;
    }


    /**
     * @return Returns the loadBenefitComponentforPrint. Method for print the
     *         page
     */
    public int getLoadBenefitComponentforPrint() {
        int retrieveKey = Integer.parseInt(getSession().getAttribute(
                WebConstants.BENEFIT_COMPONENT_ID).toString());
        int bcId = getBenefitComponentSessionObject().getBenefitComponentId();
        String bcName = getBenefitComponentSessionObject()
                .getBenefitComponentName();
        retrieveBenefitComponentDetailsforView(retrieveKey, bcId, bcName);
        
        //for retrieving the tier for a benefit definition        
            
        String tier = getAssociatedTierForDetailPrint(retrieveKey);
        this.setTierBenefitComp(tier);
        
        return loadBenefitComponentforPrint;
    }


    /**
     * @param loadBenefitComponentforPrint
     *            The loadBenefitComponentforPrint to set.
     *  
     */
    public void setLoadBenefitComponentforPrint(int loadBenefitComponentforPrint) {
        this.loadBenefitComponentforPrint = loadBenefitComponentforPrint;
    }


    /**
     * removing session values
     */
    private static void removeValueInSession(String attribute) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);

        if (null != session.getAttribute(attribute))
            session.removeAttribute(attribute);
    }


    /**
     * @return Returns the selectedBenefitComponentParentId.
     */
    public String getSelectedBenefitComponentParentId() {
        return selectedBenefitComponentParentId;
    }


    /**
     * @param selectedBenefitComponentParentId
     *            The selectedBenefitComponentParentId to set.
     */
    public void setSelectedBenefitComponentParentId(
            String selectedBenefitComponentParentId) {
        this.selectedBenefitComponentParentId = selectedBenefitComponentParentId;
    }


    /**
     * @return Returns the bcComponentTypeForCombo.
     */
    public List getBcComponentTypeListForCombo() {
        bcComponentTypeListForCombo = new ArrayList(2);
        bcComponentTypeListForCombo.add(new SelectItem(WebConstants.STD_TYPE,
                WebConstants.STD_TYPE_DISPLAY));
        bcComponentTypeListForCombo.add(new SelectItem(WebConstants.MNDT_TYPE,
                WebConstants.MNDT_TYPE_DISPLAY));
        return bcComponentTypeListForCombo;
    }


    /**
     * @param bcComponentTypeListForCombo
     *            The bcComponentTypeListForCombo to set.
     */
    public void setBcComponentTypeListForCombo(List bcComponentTypeListForCombo) {
        this.bcComponentTypeListForCombo = bcComponentTypeListForCombo;
    }


    /**
     * @return Returns the compTypeValdn.
     */
    public boolean isCompTypeValdn() {
        return compTypeValdn;
    }


    /**
     * @param compTypeValdn
     *            The compTypeValdn to set.
     */
    public void setCompTypeValdn(boolean compTypeValdn) {
        this.compTypeValdn = compTypeValdn;
    }


    /**
     * @return Returns the mandateTypeValdn.
     */
    public boolean isMandateTypeValdn() {
        return mandateTypeValdn;
    }


    /**
     * @param mandateTypeValdn
     *            The mandateTypeValdn to set.
     */
    public void setMandateTypeValdn(boolean mandateTypeValdn) {
        this.mandateTypeValdn = mandateTypeValdn;
    }


    /**
     * @return Returns the ruleIdValdn.
    */
    public boolean isRuleIdValdn() {
        return ruleIdValdn;
    }

    /**
    *@param ruleIdValdn
    */
    public void setRuleIdValdn(boolean ruleIdValdn) {
        this.ruleIdValdn = ruleIdValdn;
    }
    

    /**
     * @return Returns the stateIdValdn.
     */
    public boolean isStateIdValdn() {
        return stateIdValdn;
    }


    /**
     * @param stateIdValdn
     *            The stateIdValdn to set.
     */
    public void setStateIdValdn(boolean stateIdValdn) {
        this.stateIdValdn = stateIdValdn;
    }


    /**
     * @return Returns the componentType.
     */
    public String getComponentType() {
        return componentType;
    }


    /**
     * @param componentType
     *            The componentType to set.
     */
    public void setComponentType(String componentType) {
        this.componentType = componentType;
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
     * @return Returns the ruleIdList.
     */
    public List getRuleIdList() {
        return ruleIdList;
    }


    /**
     * @param ruleIdList
     *            The ruleIdList to set.
     */
    public void setRuleIdList(List ruleIdList) {
        this.ruleIdList = ruleIdList;
    }


    /**
     * @return Returns the selsectedStateId.
     */
    public String getSelectedStateId() {
        return selectedStateId;
    }


    /**
     * @param selectedStateId
     *            The selectedStateId to set.
     */
    public void setSelectedStateId(String selectedStateId) {
        this.selectedStateId = selectedStateId;
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
     * @return Returns the stateDesc.
     */
    public String getStateDesc() {
        return stateDesc;
    }


    /**
     * @param stateDesc
     *            The stateDesc to set.
     */
    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }


    /**
     * @return Returns the stateId.
     */
    public String getStateId() {
        return stateId;
    }


    /**
     * @param stateId
     *            The stateId to set.
     */
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }


    /**
     * @return Returns the sbBenType.
     */
    public String getSbBenType() {
        return sbBenType;
    }


    /**
     * @param sbBenType
     *            The sbBenType to set.
     */
    public void setSbBenType(String sbBenType) {
        this.sbBenType = sbBenType;
    }


    /**
     * @return Returns the sbMandateType.
     */
    public String getSbMandateType() {
        return sbMandateType;
    }


    /**
     * @param sbMandateType
     *            The sbMandateType to set.
     */
    public void setSbMandateType(String sbMandateType) {
        this.sbMandateType = sbMandateType;
    }


    /**
     * @return Returns the sbRule.
     */
    public String getSbRule() {
        return sbRule;
    }


    /**
     * @param sbRule
     *            The sbRule to set.
     */
    public void setSbRule(String sbRule) {
        this.sbRule = sbRule;
    }


    /**
     * @return Returns the sbSelState.
     */
    public String getSbSelState() {
        return sbSelState;
    }


    /**
     * @param sbSelState
     *            The sbSelState to set.
     */
    public void setSbSelState(String sbSelState) {
        this.sbSelState = sbSelState;
    }


    /**
     * Method to check if the componentType is Std or mandate and display the
     * notes tab only if the ttype is std.
     */
    public String getComponentTypeTab() {
        if ((WebConstants.STD_TYPE).equals(this.getBenefitComponentSessionObject().getBcComponentType())
                )
            return WebConstants.STANDARD_DEFINITION;
        else
            return WebConstants.MANDATE_DEFINITION;
    }


    /**
     * @param componentTypeTab
     *            The componentTypeTab to set.
     */
    public void setComponentTypeTab(String componentTypeTab) {
        this.componentTypeTab = componentTypeTab;
    }


    /**
     * @return Returns the selectedBenefitComponentType.
     */
    public String getSelectedBenefitComponentType() {
        return selectedBenefitComponentType;
    }


    /**
     * @param selectedBenefitComponentType
     *            The selectedBenefitComponentType to set.
     */
    public void setSelectedBenefitComponentType(
            String selectedBenefitComponentType) {
        this.selectedBenefitComponentType = selectedBenefitComponentType;
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
     * @return oldSelectedStateId
     * 
     * Returns the oldSelectedStateId.
     */
    public String getOldSelectedStateId() {
        return oldSelectedStateId;
    }
    /**
     * @param oldSelectedStateId
     * 
     * Sets the oldSelectedStateId.
     */
    public void setOldSelectedStateId(String oldSelectedStateId) {
        this.oldSelectedStateId = oldSelectedStateId;
    }
   
    /**
     * @return oldBusinessEntityCode
     * 
     * Returns the oldBusinessEntityCode.
     */
    public String getOldBusinessEntityCode() {
        return oldBusinessEntityCode;
    }
    /**
     * @param oldBusinessEntityCode
     * 
     * Sets the oldBusinessEntityCode.
     */
    public void setOldBusinessEntityCode(String oldBusinessEntityCode) {
        this.oldBusinessEntityCode = oldBusinessEntityCode;
    }
    /**
     * @return oldBusinessGroupCode
     * 
     * Returns the oldBusinessGroupCode.
     */
    public String getOldBusinessGroupCode() {
        return oldBusinessGroupCode;
    }
    /**
     * @param oldBusinessGroupCode
     * 
     * Sets the oldBusinessGroupCode.
     */
    public void setOldBusinessGroupCode(String oldBusinessGroupCode) {
        this.oldBusinessGroupCode = oldBusinessGroupCode;
    }
/**
 * @return oldLineOfBusinessCode
 * 
 * Returns the oldLineOfBusinessCode.
 */
public String getOldLineOfBusinessCode() {
    return oldLineOfBusinessCode;
}
/**
 * @param oldLineOfBusinessCode
 * 
 * Sets the oldLineOfBusinessCode.
 */
public void setOldLineOfBusinessCode(String oldLineOfBusinessCode) {
    this.oldLineOfBusinessCode = oldLineOfBusinessCode;
}
    
    public boolean isValidationStatus() {
        return validationStatus;
    }
    /**
     * @param validationStatus
     * 
     * Sets the validationStatus.
     */
    public void setValidationStatus(boolean validationStatus) {
        this.validationStatus = validationStatus;
    }

/**
 * @return Returns the domainChange.
 */
public boolean isDomainChange() {
	return domainChange;
}
/**
 * @param domainChange The domainChange to set.
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
 * @param errorFlagForNullState The errorFlagForNullState to set.
 */
public void setErrorFlagForNullState(boolean errorFlagForNullState) {
	this.errorFlagForNullState = errorFlagForNullState;
}

	/**
	 * @return editMode
	 * 
	 * Returns the editMode.
	 */
	public boolean isEditMode() {
		return editMode;
	}
	/**
	 * @param editMode
	 * 
	 * Sets the editMode.
	 */
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	/**
	 * @return lockStatus
	 * 
	 * Returns the lockStatus.
	 */
	public boolean isLockStatus() {
		return lockStatus;
	}
	/**
	 * @param lockStatus
	 * 
	 * Sets the lockStatus.
	 */
	public void setLockStatus(boolean lockStatus) {
		this.lockStatus = lockStatus;
	}
	
	/**
	 * @return Returns the benefitCategory.
	 */
	public String getBenefitCategory() {
		return benefitCategory;
	}
	/**
	 * @param benefitCategory The benefitCategory to set.
	 */
	public void setBenefitCategory(String benefitCategory) {
		this.benefitCategory = benefitCategory;
	}

	/**
	 * @return Returns the benefitVersion.
	 */
	public int getBenefitVersion() {
		return benefitVersion;
	}
	/**
	 * @param benefitVersion The benefitVersion to set.
	 */
	public void setBenefitVersion(int benefitVersion) {
		this.benefitVersion = benefitVersion;
	}
  
    /**
     * The method retrieves the Tier Definitions associated
     * with a particular benefitdefinition
     * @param benefitDefinitionId
     * @return
     */
    public String getAssociatedTier(int benefitId) {
        GetBenefitTierDefinitionRequest benefitTierDefinitionRequest = 
            (GetBenefitTierDefinitionRequest)
		this.getServiceRequest(ServiceManager.GET_BENEFIT_TIERDEFN_ASSN_REQUEST);
        int benefitDefinitionId = 0;
        if(null != (Integer)getSession().getAttribute("SESSION_BNFTDEFINITION_ID")){
            benefitDefinitionId = ((Integer)getSession().getAttribute("SESSION_BNFTDEFINITION_ID"))
			.intValue();
        }        
        benefitTierDefinitionRequest.setBenefitDefinitionId(benefitDefinitionId);        
        //benefitTierDefinitionRequest.setBenefitId(benefitId);
        GetBenefitTierDefinitionResponse benefitTierDefinitionResponse = 
            		(GetBenefitTierDefinitionResponse) this.
            		executeService(benefitTierDefinitionRequest);
        return benefitTierDefinitionResponse.getTier(); 
    }  
    
    /**
     * The method retrieves the Tier Definitions associated
     * with a particular benefitdefinition
     * @param benefitDefinitionId
     * @return
     */
    public String getAssociatedTierForDetailPrint(int benefitId) {
    	GetBenefitTierDefinitionForDetailPrintRequest benefitTierDefinitionRequest = 
            (GetBenefitTierDefinitionForDetailPrintRequest)
		this.getServiceRequest(ServiceManager.GET_BENEFIT_TIERDEFN_ASSN_DETAIL_PRINT_REQUEST);
        benefitTierDefinitionRequest.setBenefitId(benefitId);        
        //benefitTierDefinitionRequest.setBenefitId(benefitId);
        GetBenefitTierDefinitionResponse benefitTierDefinitionResponse = 
            		(GetBenefitTierDefinitionResponse) this.
            		executeService(benefitTierDefinitionRequest);
        return benefitTierDefinitionResponse.getTier(); 
    }   
    
    /**
     * @return Returns the tierBenefitComp.
     */
    public String getTierBenefitComp() {
        return tierBenefitComp;
    }
    /**
     * @param tierBenefitComp The tierBenefitComp to set.
     */
    public void setTierBenefitComp(String tierBenefitComp) {
        this.tierBenefitComp = tierBenefitComp;
    }    

	/**
	 * @return Returns the strRuleType.
	 */
	public String getStrRuleType() {
		return strRuleType;
	}
	/**
	 * @param strRuleType The strRuleType to set.
	 */
	public void setStrRuleType(String strRuleType) {
		this.strRuleType = strRuleType;
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
	 * @return Returns the requiredMarketBusinessUnit.
	 */
	public boolean isRequiredMarketBusinessUnit() {
		return requiredMarketBusinessUnit;
	}
	/**
	 * @param requiredMarketBusinessUnit The requiredMarketBusinessUnit to set.
	 */
	public void setRequiredMarketBusinessUnit(boolean requiredMarketBusinessUnit) {
		this.requiredMarketBusinessUnit = requiredMarketBusinessUnit;
	}
	/**
	 * @return Returns the marketBusinessUnitList.
	 */
	public List getMarketBusinessUnitList() {
		return marketBusinessUnitList;
	}
	/**
	 * @param marketBusinessUnitList The marketBusinessUnitList to set.
	 */
	public void setMarketBusinessUnitList(List marketBusinessUnitList) {
		this.marketBusinessUnitList = marketBusinessUnitList;
	}
	/**
	 * @return Returns the oldmarketBusinessUnit.
	 */
	public String getOldmarketBusinessUnit() {
		return oldmarketBusinessUnit;
	}
	/**
	 * @param oldmarketBusinessUnit The oldmarketBusinessUnit to set.
	 */
	public void setOldmarketBusinessUnit(String oldmarketBusinessUnit) {
		this.oldmarketBusinessUnit = oldmarketBusinessUnit;
	}


	public void setBenefitCompntId(int benefitCompntId) {
		this.benefitCompntId = benefitCompntId;
	}


	public int getBenefitCompntId() {
		return benefitCompntId;
	}
}