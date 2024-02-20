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
import java.util.StringTokenizer;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.wellpoint.wpd.business.datatype.locatecriteria.DataTypeLocateResult;
import com.wellpoint.wpd.business.refdata.bo.AssignedRuleIdBO;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.datatype.request.DataTypeRequest;
import com.wellpoint.wpd.common.datatype.response.DataTypeResponse;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.messages.Message;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO;
import com.wellpoint.wpd.common.standardbenefit.bo.UniverseBO;
import com.wellpoint.wpd.common.standardbenefit.request.RuleRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitCheckInRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitCheckOutRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitCopyRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitCreateRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitRetrieveRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitUpdateRequest;
import com.wellpoint.wpd.common.standardbenefit.response.RuleResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitCheckInResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitCheckOutResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitCopyResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.refdata.ReferenceDataBackingBean;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing bean for standard benefit
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitBackingBean extends WPDBackingBean {

    private String lob;

    private String businessEntity;

    private String businessGroup;
    
    private String marketBusinessUnit;

    private String minorHeading;
    
    private String benefitMeaning;

    private String description;

    private String term;

    private String qualifier;

    private String providerArrangement;

    private String dataType;

    private String rule;

    private boolean requiredMinorHeading = false;

    private boolean requiredDesription = false;

    private boolean requiredLob = false;

    private boolean requiredBusinessEntity = false;

    private boolean requiredBusinessGroup = false;
    
    private boolean requiredMarketBusinessUnit = false;

    private boolean requiredTerm = false;

    private boolean requiredQualifier = false;

    private boolean requiredProviderArrangement = false;

    private boolean requiredDataType = false;

    private String state;

    private int version;

    private String status;

    private String createdUser;

    private Date createdTimestamp;
    private  String strRuleType;
    private String lastUpdatedUser;
    

    private Date lastUpdatedTimestamp;

    private boolean checkin;

    private int selectedStdBenKey;

    private int standardBenefitKey;

    private int selectedStandardBenefitKey;

    private String selectedStandardBenefitName;

    private int selectedParentSystemId;

    private int selectedStandardBenefitVersion;

    private int viewStandardBenefitKey;

    private String selectedDataType;

    private boolean copyFlag = false;

    private boolean checkout = false;

    private boolean updateFromDone = false;

    private List validationMessages;

    private List lobCodeList;

    private List businessEntityCodeList;

    private List businessGroupCodeList;
    
    private List marketBusinessUnitList;

    private List termCodeList;

    private List qualifierCodeList;

    private List providerArrangementCodeList;

    private List dataTypeCodeList;

    private int selectedStdBenefitVersion;

    private String actionForTest;

    private int printStandardBenefitKey;

    private String mandateType;

    private String benefitType;

    //private List stateCodeList;
    private List ruleCodeList;

    private List benefitTypeListForCombo;

    private String mandateDesc;

    private String stateDesc;

    private String stateId;

    private String selectedStateId = null;

    private String benefitTypeTab;

    private List ruleResultList;
    
    private String mandateTypeHidden;
    
    private boolean checkForCopy;
    
    private String benefitTypeHidden;
    
    private String dummyVar;
    
    private String ruleResultRecords;
    
    private String printBreadCrumbText;
    
    private boolean lockAcquired = true;
    
    private String benefitCategory;
    
    private List benefitCategoryListForCombo;
    
    private boolean requiredBenefitCategory = false;
    
    private String benefitCategoryHidden;
    
    private String benefitIdentifier = null;
    
    private String searchString=null;
    
    private String searchRuleIDHidden =null;
    
    private String actionHidden =null;
    
    private List notesMessageList = null;


    
	/**
	 * @return Returns the ruleResultRecords.
	 */
	public String getRuleResultRecords() {
		String searchRuleID;
		int action=0;
		 if(null!=getRequest().getParameter("action")){
		 	searchRuleID = (String)getRequest().getParameter("ruleSearchId").trim().toUpperCase();
		 	action = Integer.parseInt((String)getRequest().getParameter("action"));
		 }
        else{
        	searchRuleID ="";
        }
		RuleRequest request = (RuleRequest) this
        .getServiceRequest(ServiceManager.RULE_REQUEST);
		request.setSearchRuleID(searchRuleID);
		request.setAction(action);
		RuleResponse response = (RuleResponse) executeService(request);
		if (response != null) {
			this.setRuleResultList(response.getRuleList());
		} else
			this.setRuleResultList(null); 
		
		return ruleResultRecords;
	}
	
	public void ruleFilterPopupRecords(){
		
		String searchRuleID;
		int action=0;
		 	searchRuleID = this.searchRuleIDHidden;
		 	action = Integer.parseInt(this.actionHidden);
		RuleRequest request = (RuleRequest) this
        .getServiceRequest(ServiceManager.RULE_REQUEST);
		request.setSearchRuleID(searchRuleID);
		request.setAction(action);
		request.setSearchString(this.searchString);
		RuleResponse response = (RuleResponse) executeService(request);
		if (response != null) {
			this.setRuleResultList(response.getRuleList());
		} else
			this.setRuleResultList(null); 
		
		
	}
	/**
	 * @param ruleResultRecords The ruleResultRecords to set.
	 */
	public void setRuleResultRecords(String ruleResultRecords) {
		this.ruleResultRecords = ruleResultRecords;
	}
    /**
     * Returns the benefitMeaning
     * @return String benefitMeaning.
     */
    public String getBenefitMeaning() {
        return benefitMeaning;
    }
    /**
     * Sets the benefitMeaning
     * @param benefitMeaning.
     */
    public void setBenefitMeaning(String benefitMeaning) {
        this.benefitMeaning = benefitMeaning;
    }
    /**
     * @return benefitTypeListForCombo
     * 
     * Returns the benefitTypeListForCombo.
     */
    public List getBenefitTypeListForCombo() {
        benefitTypeListForCombo = new ArrayList();
        
        Application application = FacesContext.getCurrentInstance().getApplication();
		ReferenceDataBackingBean backingBean =  ((ReferenceDataBackingBean) application.createValueBinding("#{ReferenceDataBackingBeanCommon}").getValue(FacesContext.getCurrentInstance()));
		if(!getStandardBenefitSessionObject().isCopyFlag()){
		    benefitTypeListForCombo = backingBean.getEntityTypeListForCombo();
		    return benefitTypeListForCombo;
        }else{
            benefitTypeListForCombo.add(new SelectItem("",""));
            
        }
        return benefitTypeListForCombo;
    }


    /**
     * @param benefitTypeListForCombo
     * 
     * Sets the benefitTypeListForCombo.
     */
    public void setBenefitTypeListForCombo(List benefitTypeListForCombo) {
        this.benefitTypeListForCombo = benefitTypeListForCombo;
    }


    /**
     * @return Returns the selectedStateId.
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
     * @return Returns the mandateDesc.
     */
    public String getMandateDesc() {
        return mandateDesc;
    }


    /**
     * @param mandateDesc
     *            The mandateDesc to set.
     */
    public void setMandateDesc(String mandateDesc) {
        this.mandateDesc = mandateDesc;
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

    //The additional fields required for enhancements.
    private boolean requiredBenefitType = false;

    private boolean requiredMandateType = false;

    private boolean requiredRule = false;


    /**
     * @return Returns the requiredBenefitType.
     */
    public boolean isRequiredBenefitType() {
        return requiredBenefitType;
    }


    /**
     * @param requiredBenefitType
     *            The requiredBenefitType to set.
     */
    public void setRequiredBenefitType(boolean requiredBenefitType) {
        this.requiredBenefitType = requiredBenefitType;
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
     * @return Returns the requiredRule.
     */
    public boolean isRequiredRule() {
        return requiredRule;
    }


    /**
     * @param requiredRule
     *            The requiredRule to set.
     */
    public void setRequiredRule(boolean requiredRule) {
        this.requiredRule = requiredRule;
    }


    /**
     * @return Returns the benefitType.
     */
    public String getBenefitType() {
        return benefitType;
    }


    /**
     * @param benefitType
     *            The benefitType to set.
     */
    public void setBenefitType(String benefitType) {
        this.benefitType = benefitType;
    }


    /**
     * @return Returns the rule.
     */
    public String getRule() {
        return rule;
    }


    /**
     * @param rule
     *            The rule to set.
     */
    public void setRule(String rule) {
        this.rule = rule;
    }


    /**
     * @return Returns the mandateType.
     */
    public String getMandateType() {
        if(null == mandateType || "".equals(mandateType)){
            this.setMandateType(this.getMandateTypeHidden());
        }
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
     * @return Returns the ruleCodeList.
     */
    public List getRuleCodeList() {
        return ruleCodeList;
    }


    /**
     * @param ruleCodeList
     *            The ruleCodeList to set.
     */
    public void setRuleCodeList(List ruleCodeList) {
        this.ruleCodeList = ruleCodeList;
    }


    /**
     * @return Returns the stateCodeList.
     */
    /*
     * public List getStateCodeList() { return stateCodeList; }
     */
    /**
     * @param stateCodeList
     *            The stateCodeList to set.
     */
    /*
     * public void setStateCodeList(List stateCodeList) { this.stateCodeList =
     * stateCodeList; }
     */
    /**
     * @return Returns the actionForTest.
     */
    public String getActionForTest() {
        return actionForTest;
    }


    /**
     * @param actionForTest
     *            The actionForTest to set.
     */
    public void setActionForTest(String actionForTest) {
        this.actionForTest = actionForTest;
    }


    /**
     * constructor
     */
    public StandardBenefitBackingBean() {
        super();
        this.lob = WebConstants.ALL_99;
        this.businessEntity = WebConstants.ALL_99;
        this.businessGroup = WebConstants.ALL_99;
        this.marketBusinessUnit = WebConstants.ALL_99;
        setBreadCrump();
    }


    /**
     * Sets the breadcrump
     *  
     */
    protected void setBreadCrump() {
        if (this.getStandardBenefitSessionObject().getStandardBenefitName() == null)
            this
                    .setBreadCrumbText(WebConstants.STANDARD_BENEFIT_CREATE_BREADCRUMB);
        else if (null != this.getStandardBenefitSessionObject()
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
    }


    /**
     * Method to get the standard benefit key from jsp
     *  
     */
    public int getViewStandardBenefitKey() {
        ArrayList validationMessages = null;
        int benefitKeyFromSearch;
        int count = 0;
        String keyString = (String) (getRequest()
                .getParameter(WebConstants.BENEFIT_KEY));
        StandardBenefitRetrieveRequest standardBenefitRetrieveRequest = (StandardBenefitRetrieveRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_STANDARD_BENEFIT);
        StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
        StandardBenefitVO standardBenefitVOTemp = null;
        int i = 0;
        if (null != keyString) {
            benefitKeyFromSearch = Integer.parseInt(keyString);
            standardBenefitVO.setStandardBenefitKey(benefitKeyFromSearch);
            this.getStandardBenefitSessionObject().setStandardBenefitKey(
                    benefitKeyFromSearch);
            //getStandardBenefitSessionObject().setStandardBenefitKey(benefitKeyFromSearch);
            this.getStandardBenefitSessionObject().setStandardBenefitMode(
                    WebConstants.BENEFIT_VIEW);
            // removing the tree state from session
            if(null != getSession().getAttribute("SESSION_TREE_STATE_SB")){
            	this.getSession().removeAttribute("SESSION_TREE_STATE_SB");
            }
        } else {
            standardBenefitVO.setStandardBenefitKey(this
                    .getStandardBenefitSessionObject().getStandardBenefitKey());
        }

        List searchResultList = null;
        if (((null == getSession().getAttribute(
                WebConstants.BENEFIT_VIEWALL_RESULT)) && (null == getSession()
                .getAttribute(WebConstants.BENEFIT_SEARCH_RESULT)))
                || (this.getStandardBenefitSessionObject().isCheckout())
                || (this.getStandardBenefitSessionObject().isCopy())) {
            standardBenefitVO
                    .setBenefitIdentifier(this
                            .getStandardBenefitSessionObject()
                            .getStandardBenefitName());
            standardBenefitVO.setStandardBenefitParentKey(this
                    .getStandardBenefitSessionObject()
                    .getStandardBenefitParentKey());
            standardBenefitVO.setBusinessDomains(this
                    .getStandardBenefitSessionObject().getBusinessDomains());
            standardBenefitVO.setStatus(this.getStandardBenefitSessionObject()
                    .getStandardBenefitStatus());
            standardBenefitVO.setVersion(this.getStandardBenefitSessionObject()
                    .getStandardBenefitVersionNumber());
            standardBenefitVO.setBenefitCategory(this.getStandardBenefitSessionObject()
            		.getBenefitCategory());
        } else if ((null != getSession().getAttribute(
                WebConstants.BENEFIT_VIEWALL_RESULT))) {
            searchResultList = (List) getSession().getAttribute(
                    WebConstants.BENEFIT_VIEWALL_RESULT);

            //change
            if (null != searchResultList && !searchResultList.isEmpty()) {
                for (i = 0; i < searchResultList.size(); i++) {
                    standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                            .get(i);
                    if (standardBenefitVO.getStandardBenefitKey() == standardBenefitVOTemp
                            .getStandardBenefitKey()) {
                        standardBenefitVO
                                .setBenefitIdentifier(standardBenefitVOTemp
                                        .getBenefitIdentifier());
                        standardBenefitVO
                                .setStandardBenefitParentKey(standardBenefitVOTemp
                                        .getStandardBenefitParentKey());
                        standardBenefitVO
                                .setBusinessDomains(standardBenefitVOTemp
                                        .getBusinessDomains());
                        standardBenefitVO.setStatus(standardBenefitVOTemp
                                .getStatus());
                        standardBenefitVO.setVersion(standardBenefitVOTemp
                                .getVersion());
                        break;
                    }
                    count++;
                }
                if (count == searchResultList.size()) {
                    searchResultList = (List) getSession().getAttribute(
                            WebConstants.BENEFIT_SEARCH_RESULT);
                    if (null != searchResultList && !searchResultList.isEmpty()) {
                        for (i = 0; i < searchResultList.size(); i++) {
                            standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                                    .get(i);
                            if (standardBenefitVO.getStandardBenefitKey() == standardBenefitVOTemp
                                    .getStandardBenefitKey()) {
                                standardBenefitVO
                                        .setBenefitIdentifier(standardBenefitVOTemp
                                                .getBenefitIdentifier());
                                standardBenefitVO
                                        .setStandardBenefitParentKey(standardBenefitVOTemp
                                                .getStandardBenefitParentKey());
                                standardBenefitVO
                                        .setBusinessDomains(standardBenefitVOTemp
                                                .getBusinessDomains());
                                standardBenefitVO
                                        .setStatus(standardBenefitVOTemp
                                                .getStatus());
                                standardBenefitVO
                                        .setVersion(standardBenefitVOTemp
                                                .getVersion());
                                break;
                            }
                        }
                    }
                }
            }
            //end
        } else {
            searchResultList = (List) getSession().getAttribute(
                    WebConstants.BENEFIT_SEARCH_RESULT);
            if (null != searchResultList && !searchResultList.isEmpty()) {
                for (i = 0; i < searchResultList.size(); i++) {
                    standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                            .get(i);
                    if (standardBenefitVO.getStandardBenefitKey() == standardBenefitVOTemp
                            .getStandardBenefitKey()) {
                        standardBenefitVO
                                .setBenefitIdentifier(standardBenefitVOTemp
                                        .getBenefitIdentifier());
                        standardBenefitVO
                                .setStandardBenefitParentKey(standardBenefitVOTemp
                                        .getStandardBenefitParentKey());
                        standardBenefitVO
                                .setBusinessDomains(standardBenefitVOTemp
                                        .getBusinessDomains());
                        standardBenefitVO.setStatus(standardBenefitVOTemp
                                .getStatus());
                        standardBenefitVO.setVersion(standardBenefitVOTemp
                                .getVersion());
                        break;
                    }
                }
            }
        }
        standardBenefitRetrieveRequest.setStandardBenefitVO(standardBenefitVO);
        StandardBenefitResponse standardBenefitResponse = (StandardBenefitResponse) executeService(standardBenefitRetrieveRequest);
        //checking if the response is null and the values are set accordingly
        if (null != standardBenefitResponse) {
            this.setMinorHeading(standardBenefitResponse.getStandardBenefitBO()
                    .getBenefitIdentifier());
            this.setBenefitMeaning(standardBenefitResponse.getStandardBenefitBO()
                    .getBenefitIdentifier());
            this.setDescription(standardBenefitResponse.getStandardBenefitBO()
                    .getDescription());
            if(null!=standardBenefitResponse.getStandardBenefitBO().getBenefitCategory()){
            	this.setBenefitCategory(standardBenefitResponse.getStandardBenefitBO().getBenefitCategory());
            	this.setBenefitCategoryHidden(standardBenefitResponse.getStandardBenefitBO().getBenefitCategoryDesc());
				
            }
            this.setCreatedUser(standardBenefitResponse.getStandardBenefitBO()
                    .getCreatedUser());
            this.setCreatedTimestamp(standardBenefitResponse
                    .getStandardBenefitBO().getCreatedTimestamp());
            this.setLastUpdatedUser(standardBenefitResponse
                    .getStandardBenefitBO().getLastUpdatedUser());
            this.setLastUpdatedTimestamp(standardBenefitResponse
                    .getStandardBenefitBO().getLastUpdatedTimestamp());
            if (null != standardBenefitResponse.getStandardBenefitBO()
                    .getState()) {
                this.setState(standardBenefitResponse.getStandardBenefitBO()
                        .getState().getState());
            }
            this.setStatus(standardBenefitResponse.getStandardBenefitBO()
                    .getStatus());
            this.setVersion(standardBenefitResponse.getStandardBenefitBO()
                    .getVersion());
            this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB + " ("
                    + this.minorHeading + ") >> View");
            DomainDetail domainDetail = standardBenefitResponse
                    .getDomainDetail();
            if (domainDetail != null) {
                this.lob = WPDStringUtil.getTildaString(domainDetail
                        .getLineOfBusiness());
                this.businessEntity = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessEntity());
                this.businessGroup = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessGroup());
                this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
                        .getMarketBusinessUnit());
            }
            this.setTerm(getTildaStringFromUniverseList(standardBenefitResponse
                    .getStandardBenefitBO().getTermList()));
            this
                    .setQualifier(getTildaStringFromUniverseList(standardBenefitResponse
                            .getStandardBenefitBO().getQualifierList()));
            this
                    .setProviderArrangement(getTildaStringFromUniverseList(standardBenefitResponse
                            .getStandardBenefitBO().getPVAList()));
            this
                    .setDataType(getTildaStringFromDataTypeList(standardBenefitResponse
                            .getStandardBenefitBO().getDataTypeList()));
            getStandardBenefitSessionObject().setStandardBenefitName(
                    minorHeading);
            getStandardBenefitSessionObject().setBenefitCategory(benefitCategory);
            //Start of enhancement code
            this.setRule(getTildaStringFromRuleTypeList(standardBenefitResponse.getStandardBenefitBO().getRuleTypeList()));
            
            // Adjudication Rules : Added to get the RuleType
            this.setStrRuleType(getRuleType(standardBenefitResponse.getStandardBenefitBO().getRuleTypeList()));
            // End 
            this.setBenefitType(standardBenefitResponse.getStandardBenefitBO()
                    .getBenefitType());
            String mandateTypeCode = standardBenefitResponse
                    .getStandardBenefitBO().getMandateType();
            if ((WebConstants.MNDT_TYPE).equals(this.benefitType)) {
                //this.setMandateType(standardBenefitResponse.getStandardBenefitBO().getMandateType());
                this.setMandateType(standardBenefitResponse
                        .getStandardBenefitBO().getMandateType());
                if ((WebConstants.EXTRA_TERRITORIAL_TYPE).equals(this.getMandateType()))
                    this.setMandateType(WebConstants.EXTRA_TERRITORIAL);
                else if ((WebConstants.FEDERAL_TYPE).equals(this.getMandateType()))
                    this.setMandateType(WebConstants.FEDERAL);
                else if ((WebConstants.STATE_TYPE).equals(this.getMandateType()))
                    this.setMandateType(WebConstants.STATE_ST);

            } else {
                this.setMandateType("");
            }

            // 			if((WebConstants.MNDT_TYPE).equals(this.benefitType)&& (!
            // ("FED").equals(this.mandateType))){
            // 			// To get the state
            // 				String statedesc =
            // standardBenefitResponse.getStandardBenefitBO().getStateCode();
            // 				String stateid =
            // standardBenefitResponse.getStandardBenefitBO().getStateDesc();
            // 				String selectedStateId = statedesc + "~" + stateid;
            // 				this.setSelectedStateId(selectedStateId);
            // 			}else{
            // 				String selectedStateId = " " + "~" + " ";
            // 				this.setSelectedStateId(selectedStateId);
            // 			}
            // 			if(("2").equals(this.mandateType)){
            // 				
            // 				this.mandateType = "State";
            // 	        	
            // 	        }
            // 			else if(("3").equals(this.mandateType)){
            // 				this.mandateType = "ET";
            // 			}
            // 			else
            // 				this.mandateType = "Federal";

            //end of enhncement
            this.getStandardBenefitSessionObject().setStandardBenefitStatus(
                    this.status);
            this.getStandardBenefitSessionObject().setStandardBenefitState(
                    this.state);
            this.getStandardBenefitSessionObject()
                    .setStandardBenefitVersionNumber(this.version);
            this.getStandardBenefitSessionObject().setMandateType(
                    mandateTypeCode);
            this.getStandardBenefitSessionObject().setBusinessDomains(
                    standardBenefitResponse.getStandardBenefitBO()
                            .getBusinessDomains());
        }
        this.getStandardBenefitSessionObject().setStandardBenefitMode("View");
        this.getStandardBenefitSessionObject().setBenefitType(this.benefitType);
        return viewStandardBenefitKey;
    }


    /**
     * Sets the viewStandardBenefitKey
     * 
     * @param viewStandardBenefitKey.
     */
    public void setViewStandardBenefitKey(int viewStandardBenefitKey) {
        this.viewStandardBenefitKey = viewStandardBenefitKey;
    }


    /**
     * Returns the selectedStandardBenefitKey
     * 
     * @return int selectedStandardBenefitKey.
     */
    public int getSelectedStandardBenefitKey() {
        return selectedStandardBenefitKey;
    }


    /**
     * Sets the selectedStandardBenefitKey
     * 
     * @param selectedStandardBenefitKey.
     */
    public void setSelectedStandardBenefitKey(int selectedStandardBenefitKey) {
        this.selectedStandardBenefitKey = selectedStandardBenefitKey;
    }


    /**
     * Returns the requiredDataType
     * 
     * @return boolean requiredDataType.
     */
    public boolean isRequiredDataType() {
        return requiredDataType;
    }


    /**
     * Sets the requiredDataType
     * 
     * @param requiredDataType.
     */
    public void setRequiredDataType(boolean requiredDataType) {
        this.requiredDataType = requiredDataType;
    }


    /**
     * Returns the requiredProviderArrangement
     * 
     * @return boolean requiredProviderArrangement.
     */
    public boolean isRequiredProviderArrangement() {
        return requiredProviderArrangement;
    }


    /**
     * Sets the requiredProviderArrangement
     * 
     * @param requiredProviderArrangement.
     */
    public void setRequiredProviderArrangement(
            boolean requiredProviderArrangement) {
        this.requiredProviderArrangement = requiredProviderArrangement;
    }


    /**
     * Returns the requiredQualifier
     * 
     * @return boolean requiredQualifier.
     */
    public boolean isRequiredQualifier() {
        return requiredQualifier;
    }


    /**
     * Sets the requiredQualifier
     * 
     * @param requiredQualifier.
     */
    public void setRequiredQualifier(boolean requiredQualifier) {
        this.requiredQualifier = requiredQualifier;
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
     * @return Returns the lob.
     */
    public String getLob() {
        return lob;
    }


    /**
     * @param lob
     *            The lob to set.
     */
    public void setLob(String lob) {
        this.lob = lob;
    }


    /**
     * @return Returns the dataType.
     */
    public String getDataType() {
        List dataTypeFetchList = null;
        if (null == this.dataType) {
            dataTypeFetchList = this.getDataTypeList();
            this.dataType = getTildaStringFromDataTypeFetchList(dataTypeFetchList);
        }
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
     * @return Returns the dataTypeList.
     */
    public List getDataTypeList() {
        List dataTypeList = null;
        DataTypeRequest dataTypeRequest = null;
        DataTypeResponse dataTypeResponse = null;
        dataTypeRequest = getDataTypeRequest();
        dataTypeResponse = (DataTypeResponse) this
                .executeService(dataTypeRequest);
        if (null != dataTypeResponse) {
            dataTypeList = dataTypeResponse.getDataTypesList();
        }
        return dataTypeList;
    }


    /**
     * @return DataTypeRequest
     */
    private DataTypeRequest getDataTypeRequest() {
        DataTypeRequest dataTypeRequest = (DataTypeRequest) this
                .getServiceRequest(ServiceManager.SEARCH_DATATYPE_REQUEST);
        return dataTypeRequest;
    }


    /**
     * @return Returns the description.
     */
    public String getDescription() {
        if (null != description)
            return description.trim();
        return null;
    }


    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        if (null != description)
            this.description = description.trim().toUpperCase();
    }


    /**
     * @return Returns the minorHeading.
     */
    public String getMinorHeading() {
        if (null != minorHeading)
            return minorHeading.trim();
        return null;
    }


    /**
     * @param minorHeading
     *            The minorHeading to set.
     */
    public void setMinorHeading(String minorHeading) {
        if (null != minorHeading) {
            StringTokenizer st = new StringTokenizer(minorHeading, " \t");
            StringBuffer buf = new StringBuffer();
            while (st.hasMoreTokens()) {
                buf.append(" ").append(st.nextToken());
            }
            minorHeading = buf.toString().trim().toUpperCase();
            this.minorHeading = minorHeading;
            this.benefitMeaning = minorHeading;
        }

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
     * @return Returns the requiredBusinessEntity.
     */
    public boolean isRequiredBusinessEntity() {
        return requiredBusinessEntity;
    }


    /**
     * @param requiredBusinessEntity
     *            The requiredBusinessEntity to set.
     */
    public void setRequiredBusinessEntity(boolean requiredBusinessEntity) {
        this.requiredBusinessEntity = requiredBusinessEntity;
    }


    /**
     * @return Returns the requiredBusinessGroup.
     */
    public boolean isRequiredBusinessGroup() {
        return requiredBusinessGroup;
    }


    /**
     * @param requiredBusinessGroup
     *            The requiredBusinessGroup to set.
     */
    public void setRequiredBusinessGroup(boolean requiredBusinessGroup) {
        this.requiredBusinessGroup = requiredBusinessGroup;
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
     * @return Returns the requiredMinorHeading.
     */
    public boolean isRequiredMinorHeading() {
        return requiredMinorHeading;
    }


    /**
     * @param requiredMinorHeading
     *            The requiredMinorHeading to set.
     */
    public void setRequiredMinorHeading(boolean requiredMinorHeading) {
        this.requiredMinorHeading = requiredMinorHeading;
    }


    /**
     * @return Returns the validationMessages.
     */
    public List getValidationMessages() {
        return validationMessages;
    }


    /**
     * @param validationMessages
     *            The validationMessages to set.
     */
    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }


    /**
     * Returns the businessEntityCodeList
     * 
     * @return List businessEntityCodeList.
     */
    public List getBusinessEntityCodeList() {
        return businessEntityCodeList;
    }


    /**
     * Sets the businessEntityCodeList
     * 
     * @param businessEntityCodeList.
     */
    public void setBusinessEntityCodeList(List businessEntityCodeList) {
        this.businessEntityCodeList = businessEntityCodeList;
    }


    /**
     * Returns the businessGroupCodeList
     * 
     * @return List businessGroupCodeList.
     */
    public List getBusinessGroupCodeList() {
        return businessGroupCodeList;
    }


    /**
     * Sets the businessGroupCodeList
     * 
     * @param businessGroupCodeList.
     */
    public void setBusinessGroupCodeList(List businessGroupCodeList) {
        this.businessGroupCodeList = businessGroupCodeList;
    }


    /**
     * Returns the lobCodeList
     * 
     * @return List lobCodeList.
     */
    public List getLobCodeList() {
        return lobCodeList;
    }


    /**
     * Sets the lobCodeList
     * 
     * @param lobCodeList.
     */
    public void setLobCodeList(List lobCodeList) {
        this.lobCodeList = lobCodeList;
    }


    /**
     * Returns the dataTypeCodeList
     * 
     * @return List dataTypeCodeList.
     */
    public List getDataTypeCodeList() {
        return dataTypeCodeList;
    }


    /**
     * Sets the dataTypeCodeList
     * 
     * @param dataTypeCodeList.
     */
    public void setDataTypeCodeList(List dataTypeCodeList) {
        this.dataTypeCodeList = dataTypeCodeList;
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
     * @return Returns the checkin.
     */
    public boolean isCheckin() {
        return checkin;
    }


    /**
     * @param checkin
     *            The checkin to set.
     */
    public void setCheckin(boolean checkin) {
        this.checkin = checkin;
    }


    /**
     * @return Returns the standardBenefitKey.
     */
    public int getStandardBenefitKey() {
        return standardBenefitKey;
    }


    /**
     * @param standardBenefitKey
     *            The standardBenefitKey to set.
     */
    public void setStandardBenefitKey(int standardBenefitKey) {
        this.standardBenefitKey = standardBenefitKey;
    }


    /**
     * Returns the providerArrangementCodeList
     * 
     * @return List providerArrangementCodeList.
     */
    public List getProviderArrangementCodeList() {
        return providerArrangementCodeList;
    }


    /**
     * Sets the providerArrangementCodeList
     * 
     * @param providerArrangementCodeList.
     */
    public void setProviderArrangementCodeList(List providerArrangementCodeList) {
        this.providerArrangementCodeList = providerArrangementCodeList;
    }


    /**
     * Returns the qualifierCodeList
     * 
     * @return List qualifierCodeList.
     */
    public List getQualifierCodeList() {
        return qualifierCodeList;
    }


    /**
     * Sets the qualifierCodeList
     * 
     * @param qualifierCodeList.
     */
    public void setQualifierCodeList(List qualifierCodeList) {
        this.qualifierCodeList = qualifierCodeList;
    }


    /**
     * Returns the termCodeList
     * 
     * @return List termCodeList.
     */
    public List getTermCodeList() {
        return termCodeList;
    }


    /**
     * Sets the termCodeList
     * 
     * @param termCodeList.
     */
    public void setTermCodeList(List termCodeList) {
        this.termCodeList = termCodeList;
    }


    /**
     * Returns the selectedStandardBenefitName
     * 
     * @return String selectedStandardBenefitName.
     */
    public String getSelectedStandardBenefitName() {
        return selectedStandardBenefitName;
    }


    /**
     * Sets the selectedStandardBenefitName
     * 
     * @param selectedStandardBenefitName.
     */
    public void setSelectedStandardBenefitName(
            String selectedStandardBenefitName) {
        this.selectedStandardBenefitName = selectedStandardBenefitName;
    }


    /**
     * Returns the selectedDataType
     * 
     * @return String selectedDataType.
     */
    public String getSelectedDataType() {
        return selectedDataType;
    }


    /**
     * Sets the selectedDataType
     * 
     * @param selectedDataType.
     */
    public void setSelectedDataType(String selectedDataType) {
        this.selectedDataType = selectedDataType;
    }


    /**
     * @return Returns the requiredDesription.
     */
    public boolean isRequiredDesription() {
        return requiredDesription;
    }


    /**
     * @param requiredDesription
     *            The requiredDesription to set.
     */
    public void setRequiredDesription(boolean requiredDesription) {
        this.requiredDesription = requiredDesription;
    }
    


    /**
     * @return Returns the updateFromDone.
     */
    public boolean isUpdateFromDone() {
        return updateFromDone;
    }


    /**
     * @param updateFromDone
     *            The updateFromDone to set.
     */
    public void setUpdateFromDone(boolean updateFromDone) {
        this.updateFromDone = updateFromDone;
    }


    /**
     * Returns the copyFlag
     * 
     * @return boolean copyFlag.
     */

    public boolean isCopyFlag() {
        return copyFlag;
    }


    /**
     * Sets the copyFlag
     * 
     * @param copyFlag.
     */

    public void setCopyFlag(boolean copyFlag) {
        this.copyFlag = copyFlag;
    }


    /**
     * Returns the selectedStdBenKey
     * 
     * @return int selectedStdBenKey.
     */

    public int getSelectedStdBenKey() {
        return selectedStdBenKey;
    }


    /**
     * Sets the selectedStdBenKey
     * 
     * @param selectedStdBenKey.
     */

    public void setSelectedStdBenKey(int selectedStdBenKey) {
        this.selectedStdBenKey = selectedStdBenKey;
    }


    /**
     * @return Returns the checkout.
     */
    public boolean isCheckout() {
        if (this.getStandardBenefitSessionObject().isCheckout())
            return true;
        return false;
    }


    /**
     * @param checkout
     *            The checkout to set.
     */
    public void setCheckout(boolean checkout) {
    }


    /*
     * Method to create a standard benefit
     */
    public String createBenefit() {
        Logger
                .logInfo("StandardBenefitBackingBean - Entering createBenefit(): Standard Benefit Create/Edit/Copy");
        ArrayList validationMessages = null;

        /*
         * Calling the form validation check method
         */
        if (!validateRequiredFields()) {
            if(this.copyFlag){
                this.setCopyFlag(true);
                this.setCheckForCopy(true);
                this.setBenefitType(this.getBenefitTypeHidden());
            }
                    
            addAllMessagesToRequest(this.validationMessages);
            return "";
        }

        /*
         * Calling the function to get a list of values from a tilda string.
         */
        this.lobCodeList = WPDStringUtil.getListFromTildaString(this.getLob(),
                2, 2, 2);
        this.businessEntityCodeList = WPDStringUtil.getListFromTildaString(this
                .getBusinessEntity(), 2, 2, 2);
        this.businessGroupCodeList = WPDStringUtil.getListFromTildaString(this
                .getBusinessGroup(), 2, 2, 2);
        this.marketBusinessUnitList = WPDStringUtil.getListFromTildaString(this
                .getMarketBusinessUnit(), 2, 2, 2);
        this.termCodeList = WPDStringUtil.getListFromTildaString(
                this.getTerm(), 2, 2, 2);
        this.qualifierCodeList = WPDStringUtil.getListFromTildaString(this
                .getQualifier(), 2, 2, 2);
        this.providerArrangementCodeList = WPDStringUtil
                .getListFromTildaString(this.getProviderArrangement(), 2, 2, 2);
        this.dataTypeCodeList = WPDStringUtil.getListFromTildaString(
                this.dataType, 2, 1, 2);
        this.ruleCodeList = WPDStringUtil.getListFromTildaString(
                this.getRule(), 2, 2, 2);

        if ((WebConstants.STD_TYPE).equals(this.benefitType)) {
            this.mandateType = null;
            this.selectedStateId = null;
        } else if (("1").equals(this.mandateType)) {
            this.selectedStateId = null;

        }

        if (null != this.selectedStateId) {
            StringTokenizer st = null;
            st = new StringTokenizer(selectedStateId, "~");
            while (st.hasMoreTokens()) {
                this.stateId = st.nextToken();
                this.stateDesc = st.nextToken();
            }
        }

        if (this.isCopyFlag()) {
            this.ruleCodeList = WPDStringUtil.getListFromTildaString(this
                    .getRule(), 2, 2, 2);
            StandardBenefitCopyRequest standardBenefitCopyRequest = getStandardBenefitCopyRequest();
            StandardBenefitCopyResponse standardBenefitCopyResponse = (StandardBenefitCopyResponse) executeService(standardBenefitCopyRequest);
            if (null != standardBenefitCopyResponse
                    && standardBenefitCopyResponse.isErrorMessageInList()) {
                this.setCheckForCopy(true);
                if(null != this.getBenefitTypeHidden() || !"".equals(this.getBenefitTypeHidden())){
                    this.setBenefitType(this.getBenefitTypeHidden());
                }
                if ((WebConstants.MNDT_TYPE).equals(this.benefitTypeHidden)) {
                    this.setBenefitType(this.getBenefitTypeHidden());
                    if (standardBenefitCopyResponse.getStandardBenefitBO()
                            .getVersion() > 0) {
                        this.setMandateType(standardBenefitCopyResponse
                                .getStandardBenefitBO().getMandateType());
                        if(this.getMandateType().equals("ET")){
                            this.setMandateType(WebConstants.EXTRA_TERRITORIAL);
                        }else if(this.getMandateType().equals("FED")){
                            this.setMandateType(WebConstants.FEDERAL);
                        }else if(this.getMandateType().equals("ST")){
                            this.setMandateType(WebConstants.STATE_ST);
                        }
                        
                    } else
                        this.setMandateType(standardBenefitCopyResponse
                                .getStandardBenefitBO().getMandateType());
                } else {
                    this.setMandateType("");
                }
                return "";
            }
            //checking if the response is null and the values are set
            // accordingly

            if (null != standardBenefitCopyResponse
                    && null != standardBenefitCopyResponse
                            .getStandardBenefitBO()) {
                if (standardBenefitCopyResponse.isSuccess()) {
                    this.getStandardBenefitSessionObject().setCopyFlag(false);
                    this.standardBenefitKey = standardBenefitCopyResponse
                            .getStandardBenefitBO().getStandardBenefitKey();
                    this.setMinorHeading(standardBenefitCopyResponse
                            .getStandardBenefitBO().getBenefitIdentifier());
                    this.setBenefitCategoryHidden(standardBenefitCopyResponse
                            .getStandardBenefitBO().getBenefitCategoryDesc());//setting description for output text
                    this.setBenefitCategory(standardBenefitCopyResponse
                            .getStandardBenefitBO().getBenefitCategory());//setting code for combo
                    this.setBenefitMeaning(standardBenefitCopyResponse
                            .getStandardBenefitBO().getBenefitIdentifier());
                    this.setDescription(standardBenefitCopyResponse
                            .getStandardBenefitBO().getDescription());
                    this.setCreatedUser(standardBenefitCopyResponse
                            .getStandardBenefitBO().getCreatedUser());
                    this.setCreatedTimestamp(standardBenefitCopyResponse
                            .getStandardBenefitBO().getCreatedTimestamp());
                    this.setLastUpdatedUser(standardBenefitCopyResponse
                            .getStandardBenefitBO().getLastUpdatedUser());
                    this.setLastUpdatedTimestamp(standardBenefitCopyResponse
                            .getStandardBenefitBO().getLastUpdatedTimestamp());
                    if (null != standardBenefitCopyResponse
                            .getStandardBenefitBO().getState()) {
                        this.setState(standardBenefitCopyResponse
                                .getStandardBenefitBO().getState().getState());
                    }
                    this.setStatus(standardBenefitCopyResponse
                            .getStandardBenefitBO().getStatus());
                    this.setVersion(standardBenefitCopyResponse
                            .getStandardBenefitBO().getVersion());
                    this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
                            + " (" + this.minorHeading + ") >> Edit");
                    DomainDetail domainDetail = standardBenefitCopyResponse
                            .getDomainDetail();
                    if (domainDetail != null) {
                        this.lob = WPDStringUtil.getTildaString(domainDetail
                                .getLineOfBusiness());
                        this.businessEntity = WPDStringUtil
                                .getTildaString(domainDetail
                                        .getBusinessEntity());
                        this.businessGroup = WPDStringUtil
                                .getTildaString(domainDetail.getBusinessGroup());
                        this.marketBusinessUnit = WPDStringUtil
                        .getTildaString(domainDetail.getMarketBusinessUnit());
                    }
                    this
                            .setTerm(getTildaStringFromUniverseList(standardBenefitCopyResponse
                                    .getStandardBenefitBO().getTermList()));
                    this
                            .setQualifier(getTildaStringFromUniverseList(standardBenefitCopyResponse
                                    .getStandardBenefitBO().getQualifierList()));
                    this
                            .setProviderArrangement(getTildaStringFromUniverseList(standardBenefitCopyResponse
                                    .getStandardBenefitBO().getPVAList()));
                    this
                            .setDataType(getTildaStringFromDataTypeList(standardBenefitCopyResponse
                                    .getStandardBenefitBO().getDataTypeList()));
                    //Start of changes for enhancement
                    this
                            .setRule(getTildaStringFromRuleTypeList(standardBenefitCopyResponse
                                    .getStandardBenefitBO().getRuleTypeList()));
                    this.setBenefitType(standardBenefitCopyResponse
                            .getStandardBenefitBO().getBenefitType());
                    if ((WebConstants.MNDT_TYPE).equals(this.benefitType)) {
                        if (standardBenefitCopyResponse.getStandardBenefitBO()
                                .getVersion() > 0) {
                            this.setMandateType(standardBenefitCopyResponse
                                    .getStandardBenefitBO().getMandateDesc());
                            this.setMandateTypeHidden(this.getMandateType());
                        } else
                            this.setMandateType(standardBenefitCopyResponse
                                    .getStandardBenefitBO().getMandateType());
                        	this.setMandateTypeHidden(this.getMandateType());
                    } else {
                        this.setMandateType("");
                    }

                    if ((WebConstants.MNDT_TYPE).equals(this.benefitType)
                            && (("2").equals(this.mandateType) || ("3")
                                    .equals(this.mandateType))) {
                        // To get the state
                        String statedesc = standardBenefitCopyResponse
                                .getStandardBenefitBO().getStateCode();
                        String stateid = standardBenefitCopyResponse
                                .getStandardBenefitBO().getStateDesc();
                        String selectedStateId = statedesc + "~" + stateid;
                        this.setSelectedStateId(selectedStateId);
                    } else {
                        String selectedStateId = " " + "~" + " ";
                        this.setSelectedStateId(selectedStateId);
                    }

                    //End of changes for enhancement
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitKey(this.standardBenefitKey);
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitParentKey(
                                    standardBenefitCopyResponse
                                            .getStandardBenefitBO()
                                            .getParentSystemId());
                    this.getStandardBenefitSessionObject().setBusinessDomains(
                            standardBenefitCopyResponse.getStandardBenefitBO()
                                    .getBusinessDomains());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitName(this.minorHeading);
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitState(this.state);
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitStatus(this.status);
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitVersionNumber(this.version);
                    getStandardBenefitSessionObject().setStandardBenefitMode(
                            WebConstants.MODE);
                    this.getStandardBenefitSessionObject().setMandateType(
                            this.mandateType);
                    this.getStandardBenefitSessionObject().setBenefitType(
                            this.benefitType);
                    this.getStandardBenefitSessionObject().setBenefitCategory(
                    		this.benefitCategory);
                    if (this.getStandardBenefitSessionObject().isCheckout())
                        this.getStandardBenefitSessionObject().setCheckout(
                                false);
                    Logger
                            .logInfo("StandardBenefitBackingBean - Returning createBenefit(): Standard Benefit Create/Copy");
                    return "standardBenefitEdit";

                }
            }
            Logger
                    .logInfo("StandardBenefitBackingBean - Returning createBenefit(): Standard Benefit Create/Copy");
            return WebConstants.STANDARD_BENEFIT_EDIT;
        } else {

            StandardBenefitCreateRequest standardBenefitCreateRequest = getStandardBenefitCreateRequest();

            StandardBenefitResponse standardBenefitResponse = (StandardBenefitResponse) executeService(standardBenefitCreateRequest);
            if(null == standardBenefitResponse){
                /*
                 * Checking Too many domain combinations
                 */
                    if(this.copyFlag){
                        this.setCopyFlag(true);
                        this.setCheckForCopy(true);
                        this.setBenefitType(this.getBenefitTypeHidden());
                    }
                    this.validationMessages.add(new ErrorMessage(
                            WebConstants.MSG_NOT_SUPPORT_MANY_DOMAIN_ATTRIBUTES));
                    addAllMessagesToRequest(this.validationMessages);
                    return "";
            }
            //checking if the response is null and the values are set
            // accordingly

            if (null != standardBenefitResponse) {
                if (null != standardBenefitResponse.getStandardBenefitBO()) {
                    if (standardBenefitResponse.isSuccess()) {
                        this.standardBenefitKey = standardBenefitResponse
                                .getStandardBenefitBO().getStandardBenefitKey();
                        this.setMinorHeading(standardBenefitResponse
                                .getStandardBenefitBO().getBenefitIdentifier());
                        this.setBenefitMeaning(standardBenefitResponse.getStandardBenefitBO()
                                .getBenefitIdentifier());
                        this.setDescription(standardBenefitResponse
                                .getStandardBenefitBO().getDescription());
                        this.setCreatedUser(standardBenefitResponse
                                .getStandardBenefitBO().getCreatedUser());
                        this.setCreatedTimestamp(standardBenefitResponse
                                .getStandardBenefitBO().getCreatedTimestamp());
                        this.setLastUpdatedUser(standardBenefitResponse
                                .getStandardBenefitBO().getLastUpdatedUser());
                        this.setLastUpdatedTimestamp(standardBenefitResponse
                                .getStandardBenefitBO()
                                .getLastUpdatedTimestamp());
                        if (null != standardBenefitResponse
                                .getStandardBenefitBO().getState()) {
                            this.setState(standardBenefitResponse
                                    .getStandardBenefitBO().getState()
                                    .getState());
                        }
                        this.setStatus(standardBenefitResponse
                                .getStandardBenefitBO().getStatus());
                        this.setVersion(standardBenefitResponse
                                .getStandardBenefitBO().getVersion());
                        this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
                                + " (" + this.minorHeading + ") >> Edit");

                        DomainDetail domainDetail = standardBenefitResponse
                                .getDomainDetail();
                        if (domainDetail != null) {
                            this.lob = WPDStringUtil
                                    .getTildaString(domainDetail
                                            .getLineOfBusiness());
                            this.businessEntity = WPDStringUtil
                                    .getTildaString(domainDetail
                                            .getBusinessEntity());
                            this.businessGroup = WPDStringUtil
                                    .getTildaString(domainDetail
                                            .getBusinessGroup());
                            this.marketBusinessUnit = WPDStringUtil
                            .getTildaString(domainDetail
                                    .getMarketBusinessUnit());
                        }

                        this
                                .setTerm(getTildaStringFromUniverseList(standardBenefitResponse
                                        .getStandardBenefitBO().getTermList()));
                        this
                                .setQualifier(getTildaStringFromUniverseList(standardBenefitResponse
                                        .getStandardBenefitBO()
                                        .getQualifierList()));
                        this
                                .setProviderArrangement(getTildaStringFromUniverseList(standardBenefitResponse
                                        .getStandardBenefitBO().getPVAList()));
                        this
                                .setDataType(getTildaStringFromDataTypeList(standardBenefitResponse
                                        .getStandardBenefitBO()
                                        .getDataTypeList()));

                        // 		    	    Start of changes for enhancement
                        this
                                .setRule(getTildaStringFromRuleTypeList(standardBenefitResponse
                                        .getStandardBenefitBO()
                                        .getRuleTypeList()));
                        this.setBenefitType(standardBenefitResponse
                                .getStandardBenefitBO().getBenefitType());
                        this.setBenefitCategory(standardBenefitResponse.getStandardBenefitBO().getBenefitCategory());
                        if ((WebConstants.MNDT_TYPE).equals(this.benefitType)) {
                            this.setMandateType(standardBenefitResponse
                                    .getStandardBenefitBO().getMandateType());
                            this.setMandateTypeHidden(this.getMandateType());
                        } else {
                            this.setMandateType("");
                        }

                        if ((WebConstants.MNDT_TYPE).equals(this.benefitType)
                                && (("2").equals(this.mandateType) || ("3")
                                        .equals(this.mandateType))) {
                            // To get the state
                            String statedesc = standardBenefitResponse
                                    .getStandardBenefitBO().getStateCode();
                            String stateid = standardBenefitResponse
                                    .getStandardBenefitBO().getStateDesc();
                            String selectedStateId = statedesc + "~" + stateid;
                            this.setSelectedStateId(selectedStateId);
                        } else {
                            String selectedStateId = " " + "~" + " ";
                            this.setSelectedStateId(selectedStateId);
                        }

                        //End of changes for enhancement
                        this.getStandardBenefitSessionObject()
                                .setStandardBenefitKey(this.standardBenefitKey);
                        this.getStandardBenefitSessionObject()
                                .setStandardBenefitParentKey(
                                        standardBenefitResponse
                                                .getStandardBenefitBO()
                                                .getParentSystemId());
                        this.getStandardBenefitSessionObject()
                                .setBusinessDomains(
                                        standardBenefitResponse
                                                .getStandardBenefitBO()
                                                .getBusinessDomains());
                        this.getStandardBenefitSessionObject()
                                .setStandardBenefitName(this.minorHeading);
                        this.getStandardBenefitSessionObject()
                                .setStandardBenefitState(this.state);
                        this.getStandardBenefitSessionObject()
                                .setStandardBenefitStatus(this.status);
                        this.getStandardBenefitSessionObject()
                                .setStandardBenefitVersionNumber(this.version);
                        this.getStandardBenefitSessionObject().setMandateType(
                                this.mandateType);
                        getStandardBenefitSessionObject()
                                .setStandardBenefitMode("Mode");
                        this.getStandardBenefitSessionObject().setBenefitType(
                                this.benefitType);
                        this.getStandardBenefitSessionObject()
                        	.setBenefitCategory(this.benefitCategory);
                        return WebConstants.STANDARD_BENEFIT_EDIT;
                    }
                }
            }
        }

        return "";
    }


    /*
     * This method gets all the fields from jsp and sets them to VO which is set
     * to the standardBenefitCreateRequest.
     */
    private StandardBenefitCreateRequest getStandardBenefitCreateRequest() {
        StandardBenefitCreateRequest standardBenefitCreateRequest = (StandardBenefitCreateRequest) this
                .getServiceRequest(ServiceManager.CREATE_STANDARD_BENEFIT);
        StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
        standardBenefitVO.setBenefitIdentifier(this.getMinorHeading());
        standardBenefitVO.setDescription(this.getDescription());
        standardBenefitVO.setLobList(this.getLobCodeList());
        standardBenefitVO.setBusinessEntityList(this
                .getBusinessEntityCodeList());
        standardBenefitVO.setBusinessGroupList(this.getBusinessGroupCodeList());
        standardBenefitVO.setMarketBusinessUnitList(this.getMarketBusinessUnitList());
        standardBenefitVO.setTermList(this.getTermCodeList());
        standardBenefitVO.setQualifierList(this.getQualifierCodeList());
        standardBenefitVO.setPVAList(this.getProviderArrangementCodeList());
        standardBenefitVO.setDataTypeList(this.getDataTypeCodeList());
        //New codes for Enhancement
        standardBenefitVO.setStateId(this.getStateId());
        standardBenefitVO.setStateDesc(this.getStateDesc());
        standardBenefitVO.setBenefitCategory(this.getBenefitCategory());

        standardBenefitVO.setRuleTypeList(this.getRuleCodeList());
        if(null == this.getMandateType() || "".equals(this.getMandateType())){
            if(null != this.getMandateTypeHidden() || !"".equals(this.getMandateTypeHidden())){
                if ((WebConstants.EXTRA_TERRITORIAL).equals(this.getMandateTypeHidden()))
                    standardBenefitVO.setMandateType("ET");
                else if ((WebConstants.FEDERAL).equals(this.getMandateTypeHidden()))
                    standardBenefitVO.setMandateType("FED");
                else if ((WebConstants.STATE_ST).equals(this.getMandateTypeHidden())) {
                    standardBenefitVO.setMandateType("ST");
                }
               
            }
        }else{
            standardBenefitVO.setMandateType(this.getMandateType());
        }
        if(null == this.getBenefitType() || "".equals(this.getBenefitType())){
            if(null != this.getBenefitTypeHidden() || !"".equals(this.getBenefitTypeHidden())){
                standardBenefitVO.setBenefitType(this.getBenefitTypeHidden());
            }
        }else
            standardBenefitVO.setBenefitType(this.getBenefitType());
        standardBenefitCreateRequest.setStandardBenefitVO(standardBenefitVO);
        return standardBenefitCreateRequest;
    }

    /*
     * Function to validate all the form fields
     */
    private boolean validateRequiredFields() {
        validationMessages = new ArrayList(1);
        boolean requiredField = false;
        this.requiredLob = false;
        this.requiredBusinessGroup = false;
        this.requiredBusinessEntity = false;
        this.requiredMarketBusinessUnit = false;
        this.requiredMinorHeading = false;
        this.requiredDesription = false;
        this.requiredTerm = false;
        this.requiredQualifier = false;
        this.requiredProviderArrangement = false;
        this.requiredDataType = false;
        

        //New fields as part of enhancement

        this.requiredBenefitType = false;
        this.requiredMandateType = false;
        this.requiredRule = false;
        this.requiredBenefitCategory = false;

        //Commented to check the done functinality

        if (null == this.rule|| "".equals(this.rule)) {
            requiredRule = true;
            requiredField = true;
        }
       if(this.copyFlag){
       	this.getStandardBenefitSessionObject().setStandardBenefitStatus(null);
       }
        if(null == this.benefitCategory || "".equals(this.benefitCategory)){
        	if(null != this.getStandardBenefitSessionObject().getStandardBenefitStatus()){
        		if(!this.getStandardBenefitSessionObject().getStandardBenefitStatus().equals(WebConstants.STATUS_BUILDING)){
	        		if(null != this.getStandardBenefitSessionObject().getBenefitCategory()){
		        		this.setBenefitCategory(this.getStandardBenefitSessionObject().getBenefitCategory());
		        		requiredBenefitCategory = false;	
		        	}
        		}else{
		        	requiredBenefitCategory = true;
		        	requiredField = true;
		        }
        		
        	}else{
	        	requiredBenefitCategory = true;
	    		requiredField = true;
        	}
        }else{
        	requiredBenefitCategory = false;
        }
        if (null == this.benefitType || "".equals(this.benefitType)) {
            if(null == this.benefitTypeHidden  || "".equals(this.benefitTypeHidden) || this.benefitTypeHidden.equals("STANDARD")){
                //this.requiredMandateType = false;
            }else{
                if (null == this.mandateType || "".equals(this.mandateType)) {
                    if(null == this.mandateTypeHidden || "".equals(this.mandateTypeHidden)){
                        requiredMandateType = true;
                        requiredField = true;
                    }
                }
            }

        } else if (this.benefitType.equals(WebConstants.MNDT_TYPE)) {

            if (null == this.mandateType || "".equals(this.mandateType)) {
                if(this.mandateTypeHidden == null  || "".equals(this.mandateTypeHidden)){
                    requiredMandateType = true;
                    requiredField = true;
                }
            }
        }

        if (null == this.lob  || "".equals(this.lob)) {
            requiredLob = true;
            requiredField = true;
        }
        if (null == this.businessEntity || "".equals(this.businessEntity)) {
            requiredBusinessEntity = true;
            requiredField = true;
        }
        if (null == this.businessGroup || "".equals(this.businessGroup)) {
            requiredBusinessGroup = true;
            requiredField = true;
        }
        if(null == this.marketBusinessUnit || "".equals(this.marketBusinessUnit)){
            requiredMarketBusinessUnit = true;
            requiredField = true;        	
        }
        if (null == this.minorHeading  || "".equals(this.minorHeading)) {
            requiredMinorHeading = true;
            if (null != this.getStandardBenefitSessionObject()
                    .getStandardBenefitName()) {
            	if(this.getStandardBenefitSessionObject().getStandardBenefitVersionNumber()==0){
            		requiredMinorHeading = true;
            		requiredField = true;
            	}else{
	                this.setMinorHeading(this.getStandardBenefitSessionObject()
	                        .getStandardBenefitName());
	                this.setBenefitMeaning(this.getStandardBenefitSessionObject()
	                        .getStandardBenefitName());
	                requiredMinorHeading = false;
            	}
            } else {
                requiredMinorHeading = true;
                requiredField = true;
            }
        }
       if (null == this.description || "".equals(this.description)) {
           requiredDesription = true;
           requiredField = true;
      }

        if (null == this.term || "".equals(this.term)) {
            requiredTerm = true;
            requiredField = true;
        }
        if (null == this.providerArrangement
                || "".equals(this.providerArrangement)) {
            requiredProviderArrangement = true;
            requiredField = true;
        }
        if (null == this.dataType || "".equals(this.dataType)) {
            requiredDataType = true;
            requiredField = true;
        }
        if (requiredField) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }
        if (this.minorHeading.length() < 2 || this.minorHeading.length() > 34) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.MINOR_HEADING_SIZE));
            return false;
        }
        if (this.description.length() < 10 || this.description.length() > 250) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.DESCRIPTION_SIZE_10_250));
            return false;
        }
        return true;
    }


    /*
     * Function to edit a particular standard benefit.
     */
    public String editBenefit() {
        Logger
                .logInfo("StandardBenefitBackingBean - Entering editBenefit(): Standard Benefit Edit");
        ArrayList validationMessages = null;
        /*
         * Calling the form validation check method
         */
        if (!validateRequiredFields()) {
            addAllMessagesToRequest(this.validationMessages);
        	getRequest().setAttribute("RETAIN_Value", "");	
            return "";
        }

        if ((WebConstants.STD_TYPE).equals(this.benefitType)) {
            this.mandateType = null;
            this.selectedStateId = null;
        } else if (("1").equals(this.mandateType)) {
            this.selectedStateId = null;

        }
        if ((WebConstants.EXTRA_TERRITORIAL).equals(this.mandateType))
            this.setMandateType("ET");
        else if ((WebConstants.FEDERAL).equals(this.mandateType))
            this.setMandateType("FED");
        else if ((WebConstants.STATE_ST).equals(this.mandateType)) {
            this.setMandateType("ST");
        }
        if (null != this.selectedStateId) {
            StringTokenizer st = null;
            st = new StringTokenizer(selectedStateId, "~");
            while (st.hasMoreTokens()) {
                this.stateId = st.nextToken();
                this.stateDesc = st.nextToken();
            }
        }

        /*
         * Calling the function to get a list of values from a tilda string.
         */
        this.lobCodeList = WPDStringUtil.getListFromTildaString(this.getLob(),
                2, 2, 2);
        this.businessEntityCodeList = WPDStringUtil.getListFromTildaString(this
                .getBusinessEntity(), 2, 2, 2);
        this.businessGroupCodeList = WPDStringUtil.getListFromTildaString(this
                .getBusinessGroup(), 2, 2, 2);
        this.marketBusinessUnitList = WPDStringUtil.getListFromTildaString(this
                .getMarketBusinessUnit(), 2, 2, 2);
        this.termCodeList = WPDStringUtil.getListFromTildaString(
                this.getTerm(), 2, 2, 2);

        //New fields to be updated
        //this.stateCodeList =
        // WPDStringUtil.getListFromTildaString(this.getStateCode(),2,2,2);
        this.ruleCodeList = WPDStringUtil.getListFromTildaString(
                this.getRule(), 2, 2, 2);

        this.qualifierCodeList = WPDStringUtil.getListFromTildaString(this
                .getQualifier(), 2, 2, 2);
        this.providerArrangementCodeList = WPDStringUtil
                .getListFromTildaString(this.getProviderArrangement(), 2, 2, 2);
        this.dataTypeCodeList = WPDStringUtil.getListFromTildaString(
                this.dataType, 2, 1, 2);

        StandardBenefitUpdateRequest standardBenefitUpdateRequest = getStandardBenefitUpdateRequest();
        StandardBenefitResponse standardBenefitResponse = (StandardBenefitResponse) executeService(standardBenefitUpdateRequest);
        //checking if response is null and set values accordingly
        if (null != standardBenefitResponse) {
            if (null != standardBenefitResponse.getStandardBenefitBO()) {
                if (standardBenefitResponse.isSuccess()) {
                    this.standardBenefitKey = standardBenefitResponse
                            .getStandardBenefitBO().getStandardBenefitKey();
                    this.setMinorHeading(standardBenefitResponse
                            .getStandardBenefitBO().getBenefitIdentifier());
                    this.setBenefitMeaning(standardBenefitResponse.getStandardBenefitBO()
                            .getBenefitIdentifier());
                    this.setDescription(standardBenefitResponse
                            .getStandardBenefitBO().getDescription());
                    this.setCreatedUser(standardBenefitResponse
                            .getStandardBenefitBO().getCreatedUser());
                    this.setCreatedTimestamp(standardBenefitResponse
                            .getStandardBenefitBO().getCreatedTimestamp());
                    this.setLastUpdatedUser(standardBenefitResponse
                            .getStandardBenefitBO().getLastUpdatedUser());
                    this.setLastUpdatedTimestamp(standardBenefitResponse
                            .getStandardBenefitBO().getLastUpdatedTimestamp());
                    //Enhancement: Benefit Category
                    this.setBenefitCategoryHidden(standardBenefitResponse
                            .getStandardBenefitBO().getBenefitCategoryDesc());
                    this.setBenefitCategory(standardBenefitResponse
                            .getStandardBenefitBO().getBenefitCategory());
                    
                    // end 
                    if (null != standardBenefitResponse.getStandardBenefitBO()
                            .getState()) {
                        this.setState(standardBenefitResponse
                                .getStandardBenefitBO().getState().getState());
                    }
                    this.setStatus(standardBenefitResponse
                            .getStandardBenefitBO().getStatus());
                    this.setVersion(standardBenefitResponse
                            .getStandardBenefitBO().getVersion());
                    this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
                            + " (" + this.minorHeading + ") >> Edit");
                    DomainDetail domainDetail = standardBenefitResponse
                            .getDomainDetail();
                    if (domainDetail != null) {
                        this.lob = WPDStringUtil.getTildaString(domainDetail
                                .getLineOfBusiness());
                        this.businessEntity = WPDStringUtil
                                .getTildaString(domainDetail
                                        .getBusinessEntity());
                        this.businessGroup = WPDStringUtil
                                .getTildaString(domainDetail.getBusinessGroup());
                        this.marketBusinessUnit = WPDStringUtil
                        .getTildaString(domainDetail.getMarketBusinessUnit());
                    }
                    this
                            .setTerm(getTildaStringFromUniverseList(standardBenefitResponse
                                    .getStandardBenefitBO().getTermList()));
                    this
                            .setQualifier(getTildaStringFromUniverseList(standardBenefitResponse
                                    .getStandardBenefitBO().getQualifierList()));
                    this
                            .setProviderArrangement(getTildaStringFromUniverseList(standardBenefitResponse
                                    .getStandardBenefitBO().getPVAList()));
                    this
                            .setDataType(getTildaStringFromDataTypeList(standardBenefitResponse
                                    .getStandardBenefitBO().getDataTypeList()));

                    //     Start of changes for enhancement
                    this
                            .setRule(getTildaStringFromRuleTypeList(standardBenefitResponse
                                    .getStandardBenefitBO().getRuleTypeList()));
                    this.setBenefitType(standardBenefitResponse
                            .getStandardBenefitBO().getBenefitType());
                    if ((WebConstants.MNDT_TYPE).equals(this.benefitType)) {
                        if (standardBenefitResponse.getStandardBenefitBO()
                                .getVersion() > 0) {
                            if(null!=standardBenefitResponse
                                    .getStandardBenefitBO().getMandateDesc()){
                                this.setMandateType(standardBenefitResponse
                                    .getStandardBenefitBO().getMandateDesc());
                            }else{
                                this.setMandateType(standardBenefitResponse
                                        .getStandardBenefitBO().getMandateType());
                            }
                        } else
                            this.setMandateType(standardBenefitResponse
                                    .getStandardBenefitBO().getMandateType());

                    } else {
                        this.setMandateType("");
                    }

                    if ((WebConstants.MNDT_TYPE).equals(this.benefitType)
                            && (("2").equals(this.mandateType) || ("3")
                                    .equals(this.mandateType))) {
                        // To get the state
                        String statedesc = standardBenefitResponse
                                .getStandardBenefitBO().getStateCode();
                        String stateid = standardBenefitResponse
                                .getStandardBenefitBO().getStateDesc();
                        String selectedStateId = statedesc + "~" + stateid;
                        this.setSelectedStateId(selectedStateId);
                    } else {
                        String selectedStateId = " " + "~" + " ";
                        this.setSelectedStateId(selectedStateId);
                    }

                    //End of changes for enhancement

                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitKey(this.standardBenefitKey);
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitParentKey(
                                    standardBenefitResponse
                                            .getStandardBenefitBO()
                                            .getParentSystemId());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitName(this.minorHeading);
                    this.getStandardBenefitSessionObject().setBusinessDomains(
                            standardBenefitResponse.getStandardBenefitBO()
                                    .getBusinessDomains());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitState(this.state);
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitStatus(this.status);
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitVersionNumber(this.version);
                    this.getStandardBenefitSessionObject().setMandateType(
                            this.mandateType);
                    this.getStandardBenefitSessionObject().setBenefitCategory(
                    		this.benefitCategory);
                    getStandardBenefitSessionObject().setStandardBenefitMode(
                            WebConstants.MODE);
                    Logger
                            .logInfo("StandardBenefitBackingBean - Returning editBenefit(): Standard Benefit Edit");
                	getRequest().setAttribute("RETAIN_Value", "");	
                    return "standardBenefitEdit";
                }
            }
        }
    	getRequest().setAttribute("RETAIN_Value", "");	
        return "";
    }


    /*
     * This method gets all the fields from jsp and sets them to VO which is set
     * to the standardBenefitUpdateRequest.
     * 
     * private StandardBenefitUpdateRequest getStandardBenefitUpdateRequest() {
     * StandardBenefitUpdateRequest standardBenefitUpdateRequest =
     * (StandardBenefitUpdateRequest)this.getServiceRequest(ServiceManager.UPDATE_STANDARD_BENEFIT);
     * StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
     * 
     * if (this.getStandardBenefitSessionObject() == null)
     * standardBenefitVO.setStandardBenefitKey(this.getStandardBenefitKey());
     * else
     * standardBenefitVO.setStandardBenefitKey(this.getStandardBenefitSessionObject().getStandardBenefitKey());
     * standardBenefitVO.setBenefitIdentifier(this.getMinorHeading());
     * standardBenefitVO.setDescription(this.getDescription());
     * standardBenefitVO.setLobList(this.getLobCodeList());
     * standardBenefitVO.setBusinessEntityList(this.getBusinessEntityCodeList());
     * standardBenefitVO.setBusinessGroupList(this.getBusinessGroupCodeList());
     * standardBenefitVO.setTermList(this.getTermCodeList());
     * standardBenefitVO.setQualifierList(this.getQualifierCodeList());
     * standardBenefitVO.setPVAList(this.getProviderArrangementCodeList());
     * standardBenefitVO.setDataTypeList(this.getDataTypeCodeList());
     * //StandardBenefitVO.setState(this.state);
     * standardBenefitVO.setStatus(this.status);
     * standardBenefitVO.setVersion(this.version);
     * standardBenefitVO.setStandardBenefitParentKey(this.getStandardBenefitSessionObject().getStandardBenefitParentKey());
     * //The new updated fields
     * standardBenefitVO.setBenefitType(this.getBenefitType());
     * standardBenefitVO.setMandateType(this.getMandateType());
     * standardBenefitVO.setRuleTypeList(this.getRuleCodeList());
     * standardBenefitVO.setStateId(this.getStateId());
     * standardBenefitVO.setStateDesc(this.getStateDesc());
     * 
     * standardBenefitUpdateRequest.setStandardBenefitVO(standardBenefitVO);
     * 
     * StandardBenefitVO oldKeystandardBenefitVO = new StandardBenefitVO();
     * oldKeystandardBenefitVO.setStandardBenefitKey(this.getStandardBenefitSessionObject().getStandardBenefitKey());
     * oldKeystandardBenefitVO.setBenefitIdentifier(this.getStandardBenefitSessionObject().getStandardBenefitName());
     * oldKeystandardBenefitVO.setStandardBenefitParentKey(this.getStandardBenefitSessionObject().getStandardBenefitParentKey());
     * oldKeystandardBenefitVO.setBusinessDomains(this.getStandardBenefitSessionObject().getBusinessDomains());
     * oldKeystandardBenefitVO.setStatus(this.getStandardBenefitSessionObject().getStandardBenefitStatus());
     * oldKeystandardBenefitVO.setVersion(this.getStandardBenefitSessionObject().getStandardBenefitVersionNumber());
     * 
     * standardBenefitUpdateRequest.setOldKeystandardBenefitVO(oldKeystandardBenefitVO);
     * return standardBenefitUpdateRequest; }
     */

    /*
     * This method gets all the fields from jsp and sets them to VO which is set
     * to the standardBenefitUpdateRequest.
     */
    private StandardBenefitUpdateRequest getStandardBenefitUpdateRequest() {
        StandardBenefitUpdateRequest standardBenefitUpdateRequest = (StandardBenefitUpdateRequest) this
                .getServiceRequest(ServiceManager.UPDATE_STANDARD_BENEFIT);
        StandardBenefitVO standardBenefitVO = new StandardBenefitVO();

        if (this.getStandardBenefitSessionObject() == null)
            standardBenefitVO.setStandardBenefitKey(this
                    .getStandardBenefitKey());
        else
            standardBenefitVO.setStandardBenefitKey(this
                    .getStandardBenefitSessionObject().getStandardBenefitKey());
        if(this.version == 0)
        	standardBenefitVO.setBenefitIdentifier(this.getBenefitIdentifier().toUpperCase());
        else
        	standardBenefitVO.setBenefitIdentifier(this.getMinorHeading());
        standardBenefitVO.setDescription(this.getDescription());
        standardBenefitVO.setLobList(this.getLobCodeList());
        standardBenefitVO.setBusinessEntityList(this
                .getBusinessEntityCodeList());
        standardBenefitVO.setBusinessGroupList(this.getBusinessGroupCodeList());
        standardBenefitVO.setMarketBusinessUnitList(this.getMarketBusinessUnitList());
        standardBenefitVO.setTermList(this.getTermCodeList());
        standardBenefitVO.setQualifierList(this.getQualifierCodeList());
        standardBenefitVO.setPVAList(this.getProviderArrangementCodeList());
        standardBenefitVO.setDataTypeList(this.getDataTypeCodeList());
        //StandardBenefitVO.setState(this.state);
        standardBenefitVO.setStatus(this.status);
        standardBenefitVO.setVersion(this.version);
        standardBenefitVO.setStandardBenefitParentKey(this
                .getStandardBenefitSessionObject()
                .getStandardBenefitParentKey());
        //The new updated fields
        standardBenefitVO.setBenefitType(this.getBenefitType());
        if(null == this.getMandateType() || "".equals(this.getMandateType())){
            if(null != this.getMandateTypeHidden() || !"".equals(this.getMandateTypeHidden())){
                if ((WebConstants.EXTRA_TERRITORIAL).equals(this.getMandateTypeHidden()))
                    standardBenefitVO.setMandateType("ET");
                else if ((WebConstants.FEDERAL).equals(this.getMandateTypeHidden()))
                    standardBenefitVO.setMandateType("FED");
                else if ((WebConstants.STATE_ST).equals(this.getMandateTypeHidden())) {
                    standardBenefitVO.setMandateType("ST");
                }
               
            }
        }else{
            if ((WebConstants.EXTRA_TERRITORIAL).equals(this.getMandateTypeHidden()))
                standardBenefitVO.setMandateType("ET");
            else if ((WebConstants.FEDERAL).equals(this.getMandateTypeHidden()))
                standardBenefitVO.setMandateType("FED");
            else if ((WebConstants.STATE_ST).equals(this.getMandateTypeHidden())) {
                standardBenefitVO.setMandateType("ST");
            }
            else 
                standardBenefitVO.setMandateType(this.getMandateType());
        }
        standardBenefitVO.setRuleTypeList(this.getRuleCodeList());
        standardBenefitVO.setStateId(this.getStateId());
        standardBenefitVO.setStateDesc(this.getStateDesc());
        standardBenefitVO.setBenefitCategory(this.getBenefitCategory());

        standardBenefitUpdateRequest.setStandardBenefitVO(standardBenefitVO);

        StandardBenefitVO oldKeystandardBenefitVO = new StandardBenefitVO();
        oldKeystandardBenefitVO.setStandardBenefitKey(this
                .getStandardBenefitSessionObject().getStandardBenefitKey());
        oldKeystandardBenefitVO.setBenefitIdentifier(this
                .getStandardBenefitSessionObject().getStandardBenefitName());
        oldKeystandardBenefitVO.setStandardBenefitParentKey(this
                .getStandardBenefitSessionObject()
                .getStandardBenefitParentKey());
        oldKeystandardBenefitVO.setBusinessDomains(this
                .getStandardBenefitSessionObject().getBusinessDomains());
        oldKeystandardBenefitVO.setStatus(this
                .getStandardBenefitSessionObject().getStandardBenefitStatus());
        oldKeystandardBenefitVO.setVersion(this
                .getStandardBenefitSessionObject()
                .getStandardBenefitVersionNumber());

        standardBenefitUpdateRequest
                .setOldKeystandardBenefitVO(oldKeystandardBenefitVO);
        return standardBenefitUpdateRequest;
    }


    /*
     * While clicking standard benefit page search result -> Edit icon, datas
     * are fetched from database and are loaded to this page using this
     * function.
     */
    public String loadStandardBenefitForEdit() {
        Logger
                .logInfo("StandardBenefitBackingBean - Entering loadStandardBenefitForEdit(): Standard Benefit Edit");
        getSession().removeAttribute("SESSION_TREE_STATE_SB");
        getStandardBenefitSessionObject().setStandardBenefitMode("Mode");
        int retrieveKey = this.getSelectedStandardBenefitKey();
        String retrieveName = this.getSelectedStandardBenefitName();
        this.getStandardBenefitSessionObject().setStandardBenefitKey(
                retrieveKey);
        this.getStandardBenefitSessionObject().setStandardBenefitName(
                retrieveName);
        this.getStandardBenefitSessionObject().setStandardBenefitParentKey(
                this.selectedParentSystemId);
        List searchResultList = (List) getSession().getAttribute(
                WebConstants.BENEFIT_SEARCH_RESULT);
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                        .get(i);
                if (retrieveKey == standardBenefitVOTemp
                        .getStandardBenefitKey()) {
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitStatus(
                                    standardBenefitVOTemp.getStatus());
                    if (null != standardBenefitVOTemp.getState())
                        this.getStandardBenefitSessionObject()
                                .setStandardBenefitState(
                                        standardBenefitVOTemp.getState()
                                                .getState());
                    this.getStandardBenefitSessionObject().setBusinessDomains(
                            standardBenefitVOTemp.getBusinessDomains());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitParentKey(
                                    standardBenefitVOTemp
                                            .getStandardBenefitParentKey());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitVersionNumber(
                                    standardBenefitVOTemp.getVersion());
                    break;
                }
            }
        }
        retrieveStandardBenefitDetails(retrieveKey);
        if(!this.lockAcquired){
        	 Application application =
				FacesContext.getCurrentInstance().getApplication();
        	StandardBenefitSearchBackingBean searchBackingBean =
        		((StandardBenefitSearchBackingBean)application.createValueBinding("#{StandardBenefitSearchBackingBean}").
    	        		getValue(FacesContext.getCurrentInstance()));
        	searchBackingBean.performLocate();
        	if(this.validationMessages != null)
        		addAllMessagesToRequest(this.validationMessages);
        	return WebConstants.EMPTY_STRING;
        }
        else{
	        if(this.version > 0){
	            if ((WebConstants.EXTRA_TERRITORIAL_TYPE).equals(this.getMandateType()))
	                this.setMandateType(WebConstants.EXTRA_TERRITORIAL);
	            else if ((WebConstants.FEDERAL_TYPE).equals(this.getMandateType()))
	                this.setMandateType(WebConstants.FEDERAL);
	            else if ((WebConstants.STATE_TYPE).equals(this.getMandateType()))
	                this.setMandateType(WebConstants.STATE_ST);
	                
	        }
	        
	        if (this.getStandardBenefitSessionObject().isCheckout())
	            this.getStandardBenefitSessionObject().setCheckout(false);
	        Logger
	                .logInfo("StandardBenefitBackingBean - Returning loadStandardBenefitForEdit(): Standard Benefit Edit");
	        return WebConstants.STANDARD_BENEFIT_EDIT;
        }
    }


    /*
     * While clicking standard benefit page from other tabs, datas are fetched
     * from database and are loaded to this page using this function.
     *  
     */
    public String loadStandardBenefit() {
        Logger
                .logInfo("StandardBenefitBackingBean - Entering loadStandardBenefit(): Standard Benefit");
        getStandardBenefitSessionObject().setStandardBenefitMode("Mode");
        int retrieveKey = this.getStandardBenefitSessionObject()
                .getStandardBenefitKey();
        retrieveStandardBenefitDetails(retrieveKey);
        if(this.version > 0){
            if ((WebConstants.EXTRA_TERRITORIAL_TYPE).equals(this.getMandateType()))
                this.setMandateType(WebConstants.EXTRA_TERRITORIAL);
            else if ((WebConstants.FEDERAL_TYPE).equals(this.getMandateType()))
                this.setMandateType(WebConstants.FEDERAL);
            else if ((WebConstants.STATE_TYPE).equals(this.getMandateType()))
                this.setMandateType(WebConstants.STATE_ST);
        }
        getRequest().setAttribute("RETAIN_Value", "");
        Logger
                .logInfo("StandardBenefitBackingBean - Returning loadStandardBenefit(): Standard Benefit");
        return WebConstants.STANDARD_BENEFIT_EDIT;
    }


    private void retrieveStandardBenefitDetails(int retrieveKey) {
        Logger
                .logInfo("StandardBenefitBackingBean - Entering retrieveStandardBenefitDetails(): Standard Benefit");
        StandardBenefitRetrieveRequest standardBenefitRetrieveRequest = (StandardBenefitRetrieveRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_STANDARD_BENEFIT);
        StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
        standardBenefitVO.setStandardBenefitKey(retrieveKey);
        standardBenefitVO.setStandardBenefitParentKey(this
                .getStandardBenefitSessionObject()
                .getStandardBenefitParentKey());
        standardBenefitVO.setBenefitIdentifier(this
                .getStandardBenefitSessionObject().getStandardBenefitName());
        standardBenefitVO.setBusinessDomains(this
                .getStandardBenefitSessionObject().getBusinessDomains());
        standardBenefitVO.setStatus(this.getStandardBenefitSessionObject()
                .getStandardBenefitStatus());
        standardBenefitVO.setVersion(this.getStandardBenefitSessionObject()
                .getStandardBenefitVersionNumber());
//        standardBenefitVO.setBenefitCategory(this.getStandardBenefitSessionObject().getBenefitCategory());
        standardBenefitRetrieveRequest.setStandardBenefitVO(standardBenefitVO);
        if(null != this.getStandardBenefitSessionObject()
                .getStandardBenefitMode()&& this.getStandardBenefitSessionObject()
                .getStandardBenefitMode().equalsIgnoreCase("Mode")){
        	standardBenefitRetrieveRequest.setEdit(true);
        }
        StandardBenefitResponse standardBenefitResponse = (StandardBenefitResponse) executeService(standardBenefitRetrieveRequest);
        if (null != standardBenefitResponse) {
        	if(!standardBenefitResponse.isLockAcquired()){
        		this.setLockAcquired(standardBenefitResponse.isLockAcquired());
        		this.validationMessages = standardBenefitResponse.getMessages();
//        		addAllMessagesToRequest(validationMessages);
        	}
        	else{
            this.setSelectedStdBenKey(standardBenefitResponse
                    .getStandardBenefitBO().getStandardBenefitKey());
            this.setMinorHeading(standardBenefitResponse.getStandardBenefitBO()
                    .getBenefitIdentifier());
            this.setBenefitMeaning(standardBenefitResponse.getStandardBenefitBO()
                    .getBenefitIdentifier());
            this.setDescription(standardBenefitResponse.getStandardBenefitBO()
                    .getDescription());

            this.setCreatedUser(standardBenefitResponse.getStandardBenefitBO()
                    .getCreatedUser());
            this.setCreatedTimestamp(standardBenefitResponse
                    .getStandardBenefitBO().getCreatedTimestamp());
            this.setLastUpdatedUser(standardBenefitResponse
                    .getStandardBenefitBO().getLastUpdatedUser());
            this.setLastUpdatedTimestamp(standardBenefitResponse
                    .getStandardBenefitBO().getLastUpdatedTimestamp());
            if (null != standardBenefitResponse.getStandardBenefitBO()
                    .getState()) {
                this.setState(standardBenefitResponse.getStandardBenefitBO()
                        .getState().getState());
            }
            this.setStatus(standardBenefitResponse.getStandardBenefitBO()
                    .getStatus());
            this.setVersion(standardBenefitResponse.getStandardBenefitBO()
                    .getVersion());
            
            this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB + " ("
                    + this.minorHeading + ") >> Edit");
            DomainDetail domainDetail = standardBenefitResponse
                    .getDomainDetail();
            if (domainDetail != null) {
                this.lob = WPDStringUtil.getTildaString(domainDetail
                        .getLineOfBusiness());
                this.businessEntity = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessEntity());
                this.businessGroup = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessGroup());
                this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
                        .getMarketBusinessUnit());
            }
            this.setTerm(getTildaStringFromUniverseList(standardBenefitResponse
                    .getStandardBenefitBO().getTermList()));
            this
                    .setQualifier(getTildaStringFromUniverseList(standardBenefitResponse
                            .getStandardBenefitBO().getQualifierList()));
            this
                    .setProviderArrangement(getTildaStringFromUniverseList(standardBenefitResponse
                            .getStandardBenefitBO().getPVAList()));
            this
                    .setDataType(getTildaStringFromDataTypeList(standardBenefitResponse
                            .getStandardBenefitBO().getDataTypeList()));
            //this.setRule(getTildaStringFromUniverseList(standardBenefitResponse.getStandardBenefitBO().getRuleTypeList()));
            // 	      Start of changes for enhancement
            this.setRule(getTildaStringFromRuleTypeList(standardBenefitResponse
                    .getStandardBenefitBO().getRuleTypeList()));
            // Adjudication Rules: Code changed for Adding RuleType
            this.setStrRuleType(getRuleType(standardBenefitResponse.getStandardBenefitBO().getRuleTypeList()));
            // End 
            
            
            this.setBenefitType(standardBenefitResponse.getStandardBenefitBO()
                    .getBenefitType());

            this.setBenefitCategory(standardBenefitResponse.getStandardBenefitBO().getBenefitCategory());//1/31/08
            this.setBenefitCategoryHidden(standardBenefitResponse.getStandardBenefitBO().getBenefitCategoryDesc());
            
//            String value = standardBenefitResponse.getStandardBenefitBO().getBenefitCategory();
//            List benefitCategoryListForComboList = this.getBenefitCategoryListForCombo();
////            java.util.Map benefitCategoryMap = new HashMap();
//            if(null!= benefitCategoryListForComboList
//            	&& !benefitCategoryListForComboList.isEmpty()){
//            	SelectItem item;
//            		for(java.util.Iterator benefitCategoryListForComboListItr = benefitCategoryListForComboList.iterator();
//            		benefitCategoryListForComboListItr.hasNext();){
//            			item = (SelectItem)benefitCategoryListForComboListItr.next();
//            			if(item.getValue().equals(value)){
//            				this.setBenefitCategoryHidden(item.getLabel());
//            			}
////            			benefitCategoryMap.put(itemBO.getPrimaryCode(), itemBO.getDescription());
//            		}
//            	}
            List list = new ArrayList(2);
            list.add(new SelectItem("",""));
            list.add(new SelectItem(WebConstants.STD_TYPE,this.getBenefitType()));
            this.setBenefitTypeListForCombo(list);
            this.getStandardBenefitSessionObject().setBenefitType(
                    standardBenefitResponse.getStandardBenefitBO()
                            .getBenefitType());
            this.getStandardBenefitSessionObject().setBenefitCategory(
                    standardBenefitResponse.getStandardBenefitBO()
                            .getBenefitCategory());
            if ((WebConstants.MNDT_TYPE).equals(this.benefitType)) {
                if (standardBenefitResponse.getStandardBenefitBO().getVersion() > 0) {
                    this.setMandateType(standardBenefitResponse
                            .getStandardBenefitBO().getMandateType());
                    this.setMandateTypeHidden(this.getMandateType());
                    
                } else{
                    this.setMandateType(standardBenefitResponse
                            .getStandardBenefitBO().getMandateType());
                	this.setMandateTypeHidden(this.getMandateType());
                	
                }
            } else {
                this.setMandateTypeHidden("");
                this.setMandateType("");
            }

            if ((WebConstants.MNDT_TYPE).equalsIgnoreCase(this.benefitType)
                    && (("2").equals(this.mandateType) || ("3")
                            .equals(this.mandateType))) {
                // To get the state
                String statedesc = standardBenefitResponse
                        .getStandardBenefitBO().getStateCode();
                String stateid = standardBenefitResponse.getStandardBenefitBO()
                        .getStateDesc();
                if (stateid == null || stateid.equals("null")) {
                    stateid = "";
                }

                String selectedStateId = null;
                if (null != stateid && null != statedesc
                        && !"".equals(statedesc) && !"".equals(stateid))
                    selectedStateId = statedesc + "~" + stateid;
                this.setSelectedStateId(selectedStateId);
            } else {

                String selectedStateId = " " + "~" + " ";
                this.setSelectedStateId(selectedStateId);
            }

        	}  //End of changes for enhancement
        }
        this.getStandardBenefitSessionObject().setMandateType(
                this.getMandateType());
        Logger
                .logInfo("StandardBenefitBackingBean - Returning retrieveStandardBenefitDetails(): Standard Benefit");
    }


    //to get the tilda seperated string from universe list
    public static String getTildaStringFromUniverseList(List universeItems) {

        if (universeItems == null)
            return "";

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


    //  to get the tilda seperated string from datatype list

    public static String getTildaStringFromDataTypeList(List dataTypeItems) {

        if (dataTypeItems == null)
            return "";

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


    //  to get the tilda seperated string from rule list

    public static String getTildaStringFromRuleTypeList(List ruleTypeItems) {

        if (ruleTypeItems == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < ruleTypeItems.size(); i++) {
            AssignedRuleIdBO element = (AssignedRuleIdBO) ruleTypeItems.get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getCodeDescText());
            buffer.append("~" + element.getPrimaryCode());
        }
        return buffer.toString();
    }
    
    
    
    private static String getRuleType(List ruleTypeItems) {

        if (ruleTypeItems == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < ruleTypeItems.size(); i++) {
            AssignedRuleIdBO element = (AssignedRuleIdBO) ruleTypeItems.get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getEntityType());
             
        }
        return buffer.toString();
    }
    
    


    //  to get the tilda seperated string from datatypefetch list

    public static String getTildaStringFromDataTypeFetchList(
            List dataTypeFetchList) {

        if (dataTypeFetchList == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < dataTypeFetchList.size(); i++) {
            DataTypeLocateResult element = (DataTypeLocateResult) dataTypeFetchList
                    .get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getDataTypeId());
            //buffer.append("~" + element.getDataTypeName());
            buffer.append("~" + element.getDataTypeLgnd());
            
        }
        return buffer.toString();
    }


    /*
     * While clicking standard benefit view page from other tabs, datas are
     * fetched from database and are loaded to this page using this function.
     *  
     */
    public String loadStandardBenefitView() {
        this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB + " ("
                + this.minorHeading + ") >> View");
        return WebConstants.STANDARD_BENEFIT_VIEW;
    }

    /*
     * Function will get the session object for standard benefit.
     */
    protected StandardBenefitSessionObject getStandardBenefitSessionObject() {
        StandardBenefitSessionObject standardBenefitSessionObject = (StandardBenefitSessionObject) getSession()
                .getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);

        if (standardBenefitSessionObject == null) {
            standardBenefitSessionObject = new StandardBenefitSessionObject();
            getSession().setAttribute(
                    WebConstants.STANDARD_BENEFIT_SESSION_KEY,
                    standardBenefitSessionObject);
        }
        return standardBenefitSessionObject;
    }

    //to checkin the benefit from the tabs other than general information tab
    public String doneFromOtherTabs() {
        updateFromDone = true;
        retrieveStandardBenefitDetails(this.getStandardBenefitSessionObject()
                .getStandardBenefitKey());
        return done();
    }

    //  to checkin the benefit from the Notes tab
    public String doneFromNotesTab() {
        updateFromDone = true;
        Application application = FacesContext.getCurrentInstance().getApplication();
        StandardBenefitNotesAttachmentBackingBean notesBackingBean = (StandardBenefitNotesAttachmentBackingBean)
						application.createValueBinding("#{standardBenefitNotesBackingBean}").getValue(FacesContext.getCurrentInstance());
        notesBackingBean.setDoneFlag(true);
        
        //To set the notes addition message
        if(null!=notesBackingBean.getNoteName() && !"".equals(notesBackingBean.getNoteName())){
	        Message notesSucessMessage = new InformationalMessage(BusinessConstants.MSG_STANDARD_BENEFIT_NOTES_SAVE_SUCCESS);
	        notesMessageList = new ArrayList(1);
	        notesMessageList.add(notesSucessMessage);
        }

        notesBackingBean.attachNotesForStandardBenefit();
                
        retrieveStandardBenefitDetails(this.getStandardBenefitSessionObject()
                .getStandardBenefitKey());
       
        return done();
    }
    
    /**
     * On clicking this button, it checks whether the standard benefit to be
     * checked has atleast a benefit defenition and whether that benefit
     * defenition has a benefit level.
     */
    public String done() {
        Logger
                .logInfo("StandardBenefitBackingBean - Entering done(): Standard Benefit Check In");
        ArrayList validationMessages = null;
        if (!validateRequiredFields()) {
            addAllMessagesToRequest(this.validationMessages);
        	getRequest().setAttribute("RETAIN_Value", "");	
            return "";
        }

        if (null != this.selectedStateId) {
            StringTokenizer st = null;
            st = new StringTokenizer(selectedStateId, "~");
            while (st.hasMoreTokens()) {
                this.stateId = st.nextToken();
                this.stateDesc = st.nextToken();
            }
        }

        this.lobCodeList = WPDStringUtil.getListFromTildaString(this.getLob(),
                2, 2, 2);
        this.businessEntityCodeList = WPDStringUtil.getListFromTildaString(this
                .getBusinessEntity(), 2, 2, 2);
        this.businessGroupCodeList = WPDStringUtil.getListFromTildaString(this
                .getBusinessGroup(), 2, 2, 2);
        this.marketBusinessUnitList = WPDStringUtil.getListFromTildaString(this
                .getMarketBusinessUnit(), 2, 2, 2);
        this.termCodeList = WPDStringUtil.getListFromTildaString(
                this.getTerm(), 2, 2, 2);
        this.qualifierCodeList = WPDStringUtil.getListFromTildaString(this
                .getQualifier(), 2, 2, 2);
        this.providerArrangementCodeList = WPDStringUtil
                .getListFromTildaString(this.getProviderArrangement(), 2, 2, 2);
        this.dataTypeCodeList = WPDStringUtil.getListFromTildaString(
                this.dataType, 2, 1, 2);
        //new fields
        this.ruleCodeList = WPDStringUtil.getListFromTildaString(
                this.getRule(), 2, 2, 2);
        //end of new fields

        StandardBenefitCheckInRequest standardBenefitCheckInRequest = getStandardBenefitCheckInRequest();
        StandardBenefitCheckInResponse standardBenefitCheckInResponse = (StandardBenefitCheckInResponse) executeService(standardBenefitCheckInRequest);
        
        if (null != standardBenefitCheckInResponse) {

            if (null != standardBenefitCheckInResponse.getStandardBenefitBO()) {
                if (standardBenefitCheckInResponse.isErrorMessageInList()
                        || this.checkin == false) {

                	List messages = standardBenefitCheckInResponse.getMessages();
                    if(null!= notesMessageList  && !notesMessageList.isEmpty()){
                    	messages.add(notesMessageList.get(0));
                    }

                    //changes for updation

                    if (null != standardBenefitCheckInResponse
                            .getStandardBenefitBO()) {
                        if (standardBenefitCheckInResponse
                                .isValidationSuccess()) {
                            this.standardBenefitKey = standardBenefitCheckInResponse
                                    .getStandardBenefitBO()
                                    .getStandardBenefitKey();
                            this.setMinorHeading(standardBenefitCheckInResponse
                                    .getStandardBenefitBO()
                                    .getBenefitIdentifier());
                            this.setBenefitMeaning(standardBenefitCheckInResponse
                                    .getStandardBenefitBO()
                                    .getBenefitIdentifier());
                            this.setDescription(standardBenefitCheckInResponse
                                    .getStandardBenefitBO().getDescription());
                            this.setCreatedUser(standardBenefitCheckInResponse
                                    .getStandardBenefitBO().getCreatedUser());
                            this
                                    .setCreatedTimestamp(standardBenefitCheckInResponse
                                            .getStandardBenefitBO()
                                            .getCreatedTimestamp());
                            this
                                    .setLastUpdatedUser(standardBenefitCheckInResponse
                                            .getStandardBenefitBO()
                                            .getLastUpdatedUser());
                            this
                                    .setLastUpdatedTimestamp(standardBenefitCheckInResponse
                                            .getStandardBenefitBO()
                                            .getLastUpdatedTimestamp());
                            if (null != standardBenefitCheckInResponse
                                    .getStandardBenefitBO().getState()) {
                                this.setState(standardBenefitCheckInResponse
                                        .getStandardBenefitBO().getState()
                                        .getState());
                            }
                            this.setStatus(standardBenefitCheckInResponse
                                    .getStandardBenefitBO().getStatus());
                            this.setVersion(standardBenefitCheckInResponse
                                    .getStandardBenefitBO().getVersion());
                            this
                                    .setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
                                            + " ("
                                            + this.minorHeading
                                            + ") >> Edit");

                            DomainDetail domainDetail = standardBenefitCheckInResponse
                                    .getDomainDetail();
                            if (domainDetail != null) {
                                this.lob = WPDStringUtil
                                        .getTildaString(domainDetail
                                                .getLineOfBusiness());
                                this.businessEntity = WPDStringUtil
                                        .getTildaString(domainDetail
                                                .getBusinessEntity());
                                this.businessGroup = WPDStringUtil
                                        .getTildaString(domainDetail
                                                .getBusinessGroup());
                                this.marketBusinessUnit = WPDStringUtil
                                .getTildaString(domainDetail
                                        .getMarketBusinessUnit());
                            }

                            this
                                    .setTerm(getTildaStringFromUniverseList(standardBenefitCheckInResponse
                                            .getStandardBenefitBO()
                                            .getTermList()));
                            this
                                    .setQualifier(getTildaStringFromUniverseList(standardBenefitCheckInResponse
                                            .getStandardBenefitBO()
                                            .getQualifierList()));
                            this
                                    .setProviderArrangement(getTildaStringFromUniverseList(standardBenefitCheckInResponse
                                            .getStandardBenefitBO()
                                            .getPVAList()));
                            this
                                    .setDataType(getTildaStringFromDataTypeList(standardBenefitCheckInResponse
                                            .getStandardBenefitBO()
                                            .getDataTypeList()));
                            //enhancement-13july
                            this
                                    .setRule(getTildaStringFromRuleTypeList(standardBenefitCheckInResponse
                                            .getStandardBenefitBO()
                                            .getRuleTypeList()));
                            
                            // Did the code change for Showing Showing Different View pages depending on the HeaderID/BlazeIDs
                            this.setStrRuleType(getRuleType(standardBenefitCheckInResponse.getStandardBenefitBO().getRuleTypeList()));	
                            
                            
                            this.setStateDesc(standardBenefitCheckInResponse
                                    .getStandardBenefitBO().getStateDesc());
                            this.setStateId(standardBenefitCheckInResponse
                                    .getStandardBenefitBO().getStateCode());
                            this.setBenefitType(standardBenefitCheckInResponse
                                    .getStandardBenefitBO().getBenefitType());
                            this.setMandateType(standardBenefitCheckInResponse
                                    .getStandardBenefitBO().getMandateType());
                            this.setBenefitCategory(standardBenefitCheckInResponse.getStandardBenefitBO().getBenefitCategory());//1/31
                            this.setBenefitCategoryHidden(standardBenefitCheckInResponse.getStandardBenefitBO().getBenefitCategoryDesc());
                            //end of enhancement
                            this.getStandardBenefitSessionObject()
                                    .setStandardBenefitKey(
                                            this.standardBenefitKey);
                            this.getStandardBenefitSessionObject()
                                    .setStandardBenefitParentKey(
                                            standardBenefitCheckInResponse
                                                    .getStandardBenefitBO()
                                                    .getParentSystemId());
                            this.getStandardBenefitSessionObject()
                                    .setStandardBenefitName(this.minorHeading);
                            this.getStandardBenefitSessionObject()
                                    .setBusinessDomains(
                                            standardBenefitCheckInResponse
                                                    .getStandardBenefitBO()
                                                    .getBusinessDomains());
                            this.getStandardBenefitSessionObject()
                                    .setStandardBenefitState(this.state);
                            this.getStandardBenefitSessionObject()
                                    .setStandardBenefitStatus(this.status);
                            this.getStandardBenefitSessionObject()
                                    .setStandardBenefitVersionNumber(
                                            this.version);
                            this.getStandardBenefitSessionObject()
									.setBenefitCategory(
											this.benefitCategory);
                            getStandardBenefitSessionObject()
                                    .setStandardBenefitMode(WebConstants.MODE);
                            Logger
                                    .logInfo("StandardBenefitBackingBean - Returning done(): Standard Benefit Check In Validation Success");
                        	getRequest().setAttribute("RETAIN_Value", "");
                        	addAllMessagesToRequest(messages);                        	
                            return WebConstants.STANDARD_BENEFIT_EDIT;
                        }
                    }

                    //change ends
                } else {
                    this.lob = WebConstants.ALL_99;
                    this.businessEntity = WebConstants.ALL_99;
                    this.businessGroup = WebConstants.ALL_99;
                    this.marketBusinessUnit = WebConstants.ALL_99;
                    this.minorHeading = "";
                    this.description = "";
                    this.term = "";
                    this.qualifier = "";
                    this.providerArrangement = "";
                    this.dataType = null;

                    //new values for enhancement
                    this.stateId = "";
                    this.stateDesc = "";
                    this.benefitType = WebConstants.STD_TYPE;
                    this.mandateType = "";
                    this.rule = "";
                    this.benefitCategory  = "";

                    if (null != getSession().getAttribute(
                            WebConstants.STANDARD_BENEFIT_SESSION_KEY)) {
                        getSession().removeAttribute(
                                WebConstants.STANDARD_BENEFIT_SESSION_KEY);
                    }
                    this
                            .setBreadCrumbText(WebConstants.STANDARD_BENEFIT_CREATE_BREADCRUMB);
                    Logger
                            .logInfo("StandardBenefitBackingBean - Returning done(): Standard Benefit Check In");
                    return WebConstants.STANDARD_BENEFIT_CREATE;
                }
            }
        }
    	getRequest().setAttribute("RETAIN_Value", "");	
        return "";
    }


    // on clicking on copy button from search page, the datas are loaded for
    // copying using this method
    public String loadStandardBenefitForCopy() throws WPDException {
        Logger
                .logInfo("StandardBenefitBackingBean - Entering loadStandardBenefitForCopy(): Standard Benefit Copy");
        getSession().removeAttribute("SESSION_TREE_STATE_SB");
        int retrieveKey = this.selectedStandardBenefitKey;
        List searchResultList = (List) getSession().getAttribute(
                WebConstants.BENEFIT_SEARCH_RESULT);
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                        .get(i);
                if (retrieveKey == standardBenefitVOTemp
                        .getStandardBenefitKey()) {
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitKey(
                                    standardBenefitVOTemp
                                            .getStandardBenefitKey());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitName(
                                    standardBenefitVOTemp
                                            .getBenefitIdentifier());
                    this.getStandardBenefitSessionObject()
                    	.setBenefitCategory(
                            standardBenefitVOTemp
                                    .getBenefitCategory());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitVersionNumber(
                                    standardBenefitVOTemp.getVersion());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitStatus(
                                    standardBenefitVOTemp.getStatus());
                    this.getStandardBenefitSessionObject().setBusinessDomains(
                            standardBenefitVOTemp.getBusinessDomains());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitParentKey(
                                    standardBenefitVOTemp
                                            .getStandardBenefitParentKey());
                    break;
                }
            }
        }
        retrieveStandardBenefitDetails(retrieveKey);
        this.copyFlag = true;
        
        this.setCheckForCopy(true);
        this.getStandardBenefitSessionObject().setCopy(true);
        this.getStandardBenefitSessionObject().setCopyFlag(true);
        this.setVersion(0);
        
        if ((WebConstants.EXTRA_TERRITORIAL).equals(this.getMandateType()))
            this.setMandateType("ET");
        else if ((WebConstants.FEDERAL).equals(this.getMandateType()))
            this.setMandateType("FED");
        else if ((WebConstants.STATE_ST).equals(this.getMandateType()))
            this.setMandateType("ST");
        //this.setMandateTypeHidden(this.mandateType);   
        this.setBreadCrumbText(WebConstants.STANDARD_BENEFIT_CREATE_BREADCRUMB);
        Logger
                .logInfo("StandardBenefitBackingBean - Returning loadStandardBenefitForCopy(): Standard Benefit Copy");
        return WebConstants.STANDARD_BENEFIT_CREATE;

    }


    // this method loads the StandardBenefitCopyRequest with datas provided from
    // jsp page
    private StandardBenefitCopyRequest getStandardBenefitCopyRequest() {
        StandardBenefitCopyRequest standardBenefitCopyRequest = (StandardBenefitCopyRequest) this
                .getServiceRequest(ServiceManager.COPY_STANDARD_BENEFIT);
        StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
        standardBenefitVO.setStandardBenefitKey(this.selectedStdBenKey);
        standardBenefitVO.setStandardBenefitParentKey(this
                .getSelectedParentSystemId());
        standardBenefitVO.setBenefitIdentifier(this.minorHeading);
        standardBenefitVO.setDescription(this.getDescription());
        standardBenefitVO.setLobList(this.getLobCodeList());
        standardBenefitVO.setBusinessEntityList(this
                .getBusinessEntityCodeList());
        standardBenefitVO.setBusinessGroupList(this.getBusinessGroupCodeList());
        standardBenefitVO.setMarketBusinessUnitList(this.getMarketBusinessUnitList());
        standardBenefitVO.setTermList(this.getTermCodeList());
        standardBenefitVO.setQualifierList(this.getQualifierCodeList());
        standardBenefitVO.setPVAList(this.getProviderArrangementCodeList());
        standardBenefitVO.setDataTypeList(this.getDataTypeCodeList());
        standardBenefitVO.setStatus(this.status);
        standardBenefitVO.setVersion(this.version);
        standardBenefitVO.setBenefitCategory(this.getBenefitCategory()); //1/31/08

        standardBenefitVO.setStateId(this.getStateId());
        standardBenefitVO.setStateDesc(this.getStateId());
        standardBenefitVO.setRuleTypeList(this.getRuleCodeList());
       // if(null == this.getMandateType() || "".equals(this.getMandateType())){
//            if(null != this.getMandateTypeHidden() || !"".equals(this.getMandateTypeHidden())){
//                if ((WebConstants.EXTRA_TERRITORIAL).equals(this.getMandateTypeHidden()))
//                    standardBenefitVO.setMandateType("ET");
//                else if ((WebConstants.FEDERAL).equals(this.getMandateTypeHidden()))
//                    standardBenefitVO.setMandateType("Fed");
//                else if ((WebConstants.STATE_ST).equals(this.getMandateTypeHidden())) {
//                    standardBenefitVO.setMandateType("ST");
//                }
               
           // }
//        }else{
            standardBenefitVO.setMandateType(this.getMandateType());
//        }
        if(null == this.getBenefitType() || "".equals(this.getBenefitType())){
            if(null != this.getBenefitTypeHidden() || !"".equals(this.getBenefitTypeHidden())){
                standardBenefitVO.setBenefitType(this.getBenefitTypeHidden());
            }
        }else
            standardBenefitVO.setBenefitType(this.getBenefitType());
        //End of Code Enhancement
        standardBenefitCopyRequest.setStandardBenefitVO(standardBenefitVO);

        return standardBenefitCopyRequest;
    }


    /*
     * This method gets all the fields from jsp and sets them to VO which is set
     * to the standardBenefitCheckInRequest.
     */
    private StandardBenefitCheckInRequest getStandardBenefitCheckInRequest() {
        StandardBenefitCheckInRequest standardBenefitCheckInRequest = (StandardBenefitCheckInRequest) this
                .getServiceRequest(ServiceManager.CHECKIN_STANDARD_BENEFIT);
        StandardBenefitVO standardBenefitVO = new StandardBenefitVO();

        if (this.getStandardBenefitSessionObject() == null)
            standardBenefitVO.setStandardBenefitKey(this
                    .getStandardBenefitKey());
        else
            standardBenefitVO.setStandardBenefitKey(this
                    .getStandardBenefitSessionObject().getStandardBenefitKey());
        standardBenefitVO.setBenefitIdentifier(this.getMinorHeading());
        standardBenefitVO.setCheckIn(this.checkin);
        standardBenefitVO.setDescription(this.getDescription());
        standardBenefitVO.setLobList(this.getLobCodeList());
        standardBenefitVO.setBusinessEntityList(this
                .getBusinessEntityCodeList());
        standardBenefitVO.setBusinessGroupList(this.getBusinessGroupCodeList());
        standardBenefitVO.setMarketBusinessUnitList(this.getMarketBusinessUnitList());
        standardBenefitVO.setTermList(this.getTermCodeList());
        standardBenefitVO.setQualifierList(this.getQualifierCodeList());
        standardBenefitVO.setPVAList(this.getProviderArrangementCodeList());
        standardBenefitVO.setDataTypeList(this.getDataTypeCodeList());
        standardBenefitVO.setUpdateFromDone(this.updateFromDone);
        standardBenefitVO.setStatus(this.status);
        standardBenefitVO.setVersion(this.version);
        standardBenefitVO.setBenefitCategory(this.benefitCategory);
        
//        if(null == this.getBenefitCategory() || "".equals(this.getBenefitCategory())){
//        	if(null != this.getBenefitCategoryHidden() || !"".equals(this.getBenefitCategoryHidden())){
//                if (("BASIC BENEFIT").equals(this.getBenefitCategoryHidden()))
//                    standardBenefitVO.setBenefitCategory("BASIC");
//                else if (("MAJOR MEDICAL BENEFIT").equals(this.getMandateTypeHidden()))
//                    standardBenefitVO.setBenefitCategory("MAJMEDICAL");
//                else if (("RIDER BENEFIT").equals(this.getMandateTypeHidden())) {
//                    standardBenefitVO.setBenefitCategory("RIDER");
//                }
//        	}
//        }else{
//        	if (("BASIC BENEFIT").equals(this.getBenefitCategoryHidden()))
//                standardBenefitVO.setBenefitCategory("BASIC");
//            else if (("MAJOR MEDICAL BENEFIT").equals(this.getMandateTypeHidden()))
//                standardBenefitVO.setBenefitCategory("MAJMEDICAL");
//            else if (("RIDER BENEFIT").equals(this.getMandateTypeHidden())) {
//                standardBenefitVO.setBenefitCategory("RIDER");
//            }else{
//            	 standardBenefitVO.setBenefitCategory(this.getBenefitCategory());
//            }
//        }

        //New Code for enhancement
        standardBenefitVO.setBenefitType(this.benefitType);
        if(null == this.getMandateType() || "".equals(this.getMandateType())){
            if(null != this.getMandateTypeHidden() || !"".equals(this.getMandateTypeHidden())){
                if ((WebConstants.EXTRA_TERRITORIAL).equals(this.getMandateTypeHidden()))
                    standardBenefitVO.setMandateType("ET");
                else if ((WebConstants.FEDERAL).equals(this.getMandateTypeHidden()))
                    standardBenefitVO.setMandateType("FED");
                else if ((WebConstants.STATE_ST).equals(this.getMandateTypeHidden())) {
                    standardBenefitVO.setMandateType("ST");
                }
               
            }
        }else{
            if ((WebConstants.EXTRA_TERRITORIAL).equals(this.getMandateTypeHidden()))
                standardBenefitVO.setMandateType("ET");
            else if ((WebConstants.FEDERAL).equals(this.getMandateTypeHidden()))
                standardBenefitVO.setMandateType("FED");
            else if ((WebConstants.STATE_ST).equals(this.getMandateTypeHidden())) {
                standardBenefitVO.setMandateType("ST");
            }
            else 
                standardBenefitVO.setMandateType(this.getMandateType());
        }
        standardBenefitVO.setRuleTypeList(this.ruleCodeList);
        if (null != this.stateId && "".equals(this.stateId.trim())) {
            this.stateId = null;
        }
        standardBenefitVO.setStateId(this.stateId);
        standardBenefitVO.setStateDesc(this.stateDesc);

        standardBenefitVO.setStandardBenefitParentKey(this
                .getStandardBenefitSessionObject()
                .getStandardBenefitParentKey());
        standardBenefitCheckInRequest.setStandardBenefitVO(standardBenefitVO);

        // newly added change for update
        StandardBenefitVO oldKeystandardBenefitVO = new StandardBenefitVO();
        oldKeystandardBenefitVO.setStandardBenefitKey(this
                .getStandardBenefitSessionObject().getStandardBenefitKey());
        oldKeystandardBenefitVO.setBenefitIdentifier(this
                .getStandardBenefitSessionObject().getStandardBenefitName());
        oldKeystandardBenefitVO.setStandardBenefitParentKey(this
                .getStandardBenefitSessionObject()
                .getStandardBenefitParentKey());
        oldKeystandardBenefitVO.setBusinessDomains(this
                .getStandardBenefitSessionObject().getBusinessDomains());
        oldKeystandardBenefitVO.setStatus(this
                .getStandardBenefitSessionObject().getStandardBenefitStatus());
        oldKeystandardBenefitVO.setVersion(this
                .getStandardBenefitSessionObject()
                .getStandardBenefitVersionNumber());
        standardBenefitCheckInRequest
                .setOldKeystandardBenefitVO(oldKeystandardBenefitVO);

        // change ends
        return standardBenefitCheckInRequest;
    }


    /**
     * Returns the selectedStdBenefitVersion
     * 
     * @return int selectedStdBenefitVersion.
     */
    public int getSelectedStdBenefitVersion() {
        return selectedStdBenefitVersion;
    }


    /**
     * Sets the selectedStdBenefitVersion
     * 
     * @param selectedStdBenefitVersion.
     */
    public void setSelectedStdBenefitVersion(int selectedStdBenefitVersion) {
        this.selectedStdBenefitVersion = selectedStdBenefitVersion;
    }


    /**
     * @return Returns the selectedParentSystemId.
     */
    public int getSelectedParentSystemId() {
        return selectedParentSystemId;
    }


    /**
     * @param selectedParentSystemId
     *            The selectedParentSystemId to set.
     */
    public void setSelectedParentSystemId(int selectedParentSystemId) {
        this.selectedParentSystemId = selectedParentSystemId;
    }


    /**
     * @return Returns the selectedStandardBenefitVersion.
     */
    public int getSelectedStandardBenefitVersion() {
        return selectedStandardBenefitVersion;
    }


    /**
     * @param selectedStandardBenefitVersion
     *            The selectedStandardBenefitVersion to set.
     */
    public void setSelectedStandardBenefitVersion(
            int selectedStandardBenefitVersion) {
        this.selectedStandardBenefitVersion = selectedStandardBenefitVersion;
    }


    /**
     * Method to check out a benefit
     */
    public String checkOutStandardBenefit() throws WPDException {
        Logger
                .logInfo("StandardBenefitBackingBean - Entering checkOutStandardBenefit(): Standard Benefit CheckOut");
        StandardBenefitCheckOutRequest standardBenefitCheckOutRequest = (StandardBenefitCheckOutRequest) this
                .getServiceRequest(ServiceManager.CHECKOUT_STANDARD_BENEFIT);
        StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
        standardBenefitVO
                .setStandardBenefitKey(this.selectedStandardBenefitKey);
        standardBenefitVO
                .setBenefitIdentifier(this.selectedStandardBenefitName);
        standardBenefitVO
                .setStandardBenefitParentKey(this.selectedParentSystemId);
        List searchResultList = (List) getSession().getAttribute(
                WebConstants.BENEFIT_SEARCH_RESULT);
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                        .get(i);
                if (selectedStandardBenefitKey == standardBenefitVOTemp
                        .getStandardBenefitKey()) {
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitKey(
                                    standardBenefitVOTemp
                                            .getStandardBenefitKey());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitName(
                                    standardBenefitVOTemp
                                            .getBenefitIdentifier());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitStatus(
                                    standardBenefitVOTemp.getStatus());
                    this.getStandardBenefitSessionObject().setBusinessDomains(
                            standardBenefitVOTemp.getBusinessDomains());
                    standardBenefitVO.setBusinessDomains(standardBenefitVOTemp
                            .getBusinessDomains());
                    standardBenefitVO.setStatus(standardBenefitVOTemp
                            .getStatus());
                    standardBenefitVO.setVersion(standardBenefitVOTemp
                            .getVersion());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitParentKey(
                                    standardBenefitVOTemp
                                            .getStandardBenefitParentKey());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitVersionNumber(
                                    standardBenefitVOTemp.getVersion());
                    break;
                }
            }
        }
        standardBenefitCheckOutRequest.setStandardBenefitVO(standardBenefitVO);

        StandardBenefitCheckOutResponse standardBenefitCheckOutResponse = (StandardBenefitCheckOutResponse) executeService(standardBenefitCheckOutRequest);

        if (null != standardBenefitCheckOutResponse
                && null != standardBenefitCheckOutResponse
                        .getStandardBenefitBO()) {
            this.getStandardBenefitSessionObject().setCheckout(true);
            this.setValuesFromCheckOutResponse(standardBenefitCheckOutResponse);
            Logger
                    .logInfo("StandardBenefitBackingBean - Returning checkOutStandardBenefit(): Standard Benefit CheckOut");
            return WebConstants.STANDARD_BENEFIT_EDIT;
        }
        Logger
                .logInfo("StandardBenefitBackingBean - Returning checkOutStandardBenefit(): Standard Benefit CheckOut");
        return "";
    }


    /**
     * Method to check out a benefit from view all versions
     */
    public String checkOutStandardBenefitVersions() throws WPDException {
        Logger
                .logInfo("StandardBenefitBackingBean - Entering checkOutStandardBenefitVersions(): Standard Benefit CheckOutVersions");
        StandardBenefitCheckOutRequest standardBenefitCheckOutRequest = (StandardBenefitCheckOutRequest) this
                .getServiceRequest(ServiceManager.CHECKOUT_STANDARD_BENEFIT);
        StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
        standardBenefitVO
                .setStandardBenefitKey(this.selectedStandardBenefitKey);
        standardBenefitVO
                .setBenefitIdentifier(this.selectedStandardBenefitName);
        List searchResultList = (List) getSession().getAttribute(
                WebConstants.BENEFIT_VIEWALL_RESULT);
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                        .get(i);
                if (selectedStandardBenefitKey == standardBenefitVOTemp
                        .getStandardBenefitKey()) {
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitKey(
                                    standardBenefitVOTemp
                                            .getStandardBenefitKey());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitName(
                                    standardBenefitVOTemp
                                            .getBenefitIdentifier());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitStatus(
                                    standardBenefitVOTemp.getStatus());
                    this.getStandardBenefitSessionObject().setBusinessDomains(
                            standardBenefitVOTemp.getBusinessDomains());
                    standardBenefitVO.setBusinessDomains(standardBenefitVOTemp
                            .getBusinessDomains());
                    standardBenefitVO.setStatus(standardBenefitVOTemp
                            .getStatus());
                    standardBenefitVO
                            .setStandardBenefitParentKey(standardBenefitVOTemp
                                    .getStandardBenefitParentKey());
                    standardBenefitVO.setVersion(standardBenefitVOTemp
                            .getVersion());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitParentKey(
                                    standardBenefitVOTemp
                                            .getStandardBenefitParentKey());
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitVersionNumber(
                                    standardBenefitVOTemp.getVersion());
                    break;
                }
            }
        }
        standardBenefitCheckOutRequest.setStandardBenefitVO(standardBenefitVO);
        StandardBenefitCheckOutResponse standardBenefitCheckOutResponse = (StandardBenefitCheckOutResponse) executeService(standardBenefitCheckOutRequest);
        if (null != standardBenefitCheckOutResponse
                && null != standardBenefitCheckOutResponse
                        .getStandardBenefitBO()) {
            this.getStandardBenefitSessionObject().setCheckout(true);
            this.setValuesFromCheckOutResponse(standardBenefitCheckOutResponse);
            Logger
                    .logInfo("StandardBenefitBackingBean - Returning checkOutStandardBenefitVersions(): Standard Benefit CheckOutVersions");
            return WebConstants.STANDARD_BENEFIT_EDIT;
        }
        Logger
                .logInfo("StandardBenefitBackingBean - Returning checkOutStandardBenefitVersions(): Standard Benefit CheckOutVersions");
        return "";
    }


    /*
     * This methods gets the data from StandardBenefitCheckOutResponse and loads
     * them in to the backing bean
     */
    private void setValuesFromCheckOutResponse(
            StandardBenefitCheckOutResponse standardBenefitCheckOutResponse) {
        this.standardBenefitKey = standardBenefitCheckOutResponse
                .getStandardBenefitBO().getStandardBenefitKey();
        this.setMinorHeading(standardBenefitCheckOutResponse
                .getStandardBenefitBO().getBenefitIdentifier());
        this.setBenefitMeaning(standardBenefitCheckOutResponse
                .getStandardBenefitBO().getBenefitIdentifier());
        this.setDescription(standardBenefitCheckOutResponse
                .getStandardBenefitBO().getDescription());
        this.setCreatedUser(standardBenefitCheckOutResponse
                .getStandardBenefitBO().getCreatedUser());
        this.setCreatedTimestamp(standardBenefitCheckOutResponse
                .getStandardBenefitBO().getCreatedTimestamp());
        this.setLastUpdatedUser(standardBenefitCheckOutResponse
                .getStandardBenefitBO().getLastUpdatedUser());
        this.setLastUpdatedTimestamp(standardBenefitCheckOutResponse
                .getStandardBenefitBO().getLastUpdatedTimestamp());
        if (null != standardBenefitCheckOutResponse.getStandardBenefitBO()
                .getState()) {
            this.setState(standardBenefitCheckOutResponse
                    .getStandardBenefitBO().getState().getState());
        }
        this.setStatus(standardBenefitCheckOutResponse.getStandardBenefitBO()
                .getStatus());
        this.setVersion(standardBenefitCheckOutResponse.getStandardBenefitBO()
                .getVersion());
        this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB + " ("
                + this.minorHeading + ") >> Edit");
        this.setBenefitCategoryHidden(standardBenefitCheckOutResponse.getStandardBenefitBO().getBenefitCategoryDesc());
        this.setBenefitCategory(standardBenefitCheckOutResponse.getStandardBenefitBO().getBenefitCategory());
        DomainDetail domainDetail = standardBenefitCheckOutResponse
                .getDomainDetail();
        if (domainDetail != null) {
            this.lob = WPDStringUtil.getTildaString(domainDetail
                    .getLineOfBusiness());
            this.businessEntity = WPDStringUtil.getTildaString(domainDetail
                    .getBusinessEntity());
            this.businessGroup = WPDStringUtil.getTildaString(domainDetail
                    .getBusinessGroup());
            this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
                    .getMarketBusinessUnit());
        }
        this
                .setTerm(getTildaStringFromUniverseList(standardBenefitCheckOutResponse
                        .getStandardBenefitBO().getTermList()));
        this
                .setQualifier(getTildaStringFromUniverseList(standardBenefitCheckOutResponse
                        .getStandardBenefitBO().getQualifierList()));
        this
                .setProviderArrangement(getTildaStringFromUniverseList(standardBenefitCheckOutResponse
                        .getStandardBenefitBO().getPVAList()));
        this
                .setDataType(getTildaStringFromDataTypeList(standardBenefitCheckOutResponse
                        .getStandardBenefitBO().getDataTypeList()));

        //Start of changes for enhancement
        this
                .setRule(getTildaStringFromRuleTypeList(standardBenefitCheckOutResponse
                        .getStandardBenefitBO().getRuleTypeList()));
        this.setBenefitType(standardBenefitCheckOutResponse
                .getStandardBenefitBO().getBenefitType());
        if ((WebConstants.MNDT_TYPE).equals(this.benefitType)) {
//            if (standardBenefitCheckOutResponse.getStandardBenefitBO()
//                    .getVersion() > 0) {
//                this.setMandateType(standardBenefitCheckOutResponse
//                        .getStandardBenefitBO().getMandateDesc());
//            } else
                this.setMandateType(standardBenefitCheckOutResponse
                        .getStandardBenefitBO().getMandateType());
                if ((WebConstants.EXTRA_TERRITORIAL_TYPE).equals(this.getMandateType()))
                    this.setMandateType(WebConstants.EXTRA_TERRITORIAL);
                else if ((WebConstants.FEDERAL_TYPE).equals(this.getMandateType()))
                    this.setMandateType(WebConstants.FEDERAL);
                else if ((WebConstants.STATE_TYPE).equals(this.getMandateType()))
                    this.setMandateType(WebConstants.STATE_ST);
        } else {
            this.setMandateType("");
        }

        if ((WebConstants.MNDT_TYPE).equals(this.benefitType)
                && (("2").equals(this.mandateType) || ("3")
                        .equals(this.mandateType))) {
            // To get the state
            String statedesc = standardBenefitCheckOutResponse
                    .getStandardBenefitBO().getStateCode();
            String stateid = standardBenefitCheckOutResponse
                    .getStandardBenefitBO().getStateDesc();
            String selectedStateId = statedesc + "~" + stateid;
            this.setSelectedStateId(selectedStateId);
        } else {
            String selectedStateId = " " + "~" + " ";
            this.setSelectedStateId(selectedStateId);
        }

        //End of changes for enhancement

        this.getStandardBenefitSessionObject().setStandardBenefitKey(
                this.standardBenefitKey);
        this.getStandardBenefitSessionObject().setStandardBenefitName(
                this.minorHeading);
        this.getStandardBenefitSessionObject().setBenefitCategory(
                this.benefitCategory);
        this.getStandardBenefitSessionObject().setStandardBenefitState(
                this.state);
        this.getStandardBenefitSessionObject().setStandardBenefitStatus(
                this.status);
        this.getStandardBenefitSessionObject().setStandardBenefitVersionNumber(
                this.version);
        this.getStandardBenefitSessionObject().setBenefitType(
                standardBenefitCheckOutResponse.getStandardBenefitBO()
                        .getBenefitType());
        getStandardBenefitSessionObject().setStandardBenefitMode(
                WebConstants.MODE);
        this.getStandardBenefitSessionObject().setCheckout(true);

    }


    public String backToSearch() throws WPDException {
        if (this.getActionForTest().equals("COPY")) {
            int retrieveKey = this.selectedStandardBenefitKey;
            List searchResultList = (List) getSession().getAttribute(
                    WebConstants.BENEFIT_VIEWALL_RESULT);
            if (null != searchResultList && !searchResultList.isEmpty()) {
                for (int i = 0; i < searchResultList.size(); i++) {
                    StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                            .get(i);
                    if (retrieveKey == standardBenefitVOTemp
                            .getStandardBenefitKey()) {
                        this.getStandardBenefitSessionObject()
                                .setStandardBenefitKey(
                                        standardBenefitVOTemp
                                                .getStandardBenefitKey());
                        this.getStandardBenefitSessionObject()
                                .setStandardBenefitName(
                                        standardBenefitVOTemp
                                                .getBenefitIdentifier());
                        //Enhancement:Benefit Category
                        this.getStandardBenefitSessionObject()
                    			.setBenefitCategory(
                    					standardBenefitVOTemp
												.getBenefitCategory());
                        this.getStandardBenefitSessionObject()
                                .setStandardBenefitVersionNumber(
                                        standardBenefitVOTemp.getVersion());
                        this.getStandardBenefitSessionObject()
                                .setStandardBenefitStatus(
                                        standardBenefitVOTemp.getStatus());
                        this.getStandardBenefitSessionObject()
                                .setBusinessDomains(
                                        standardBenefitVOTemp
                                                .getBusinessDomains());
                        this.getStandardBenefitSessionObject()
                                .setStandardBenefitParentKey(
                                        standardBenefitVOTemp
                                                .getStandardBenefitParentKey());
                        break;
                    }
                }
            }
            retrieveStandardBenefitDetails(retrieveKey);
            this.copyFlag = true;
            
            this.setCheckForCopy(true);
            this.getStandardBenefitSessionObject().setCopy(true);
            this.getStandardBenefitSessionObject().setCopyFlag(true);
            this.setVersion(0);
            
            if ((WebConstants.EXTRA_TERRITORIAL).equals(this.getMandateType()))
                this.setMandateType("ET");
            else if ((WebConstants.FEDERAL).equals(this.getMandateType()))
                this.setMandateType("FED");
            else if ((WebConstants.STATE_ST).equals(this.getMandateType()))
                this.setMandateType("ST");
            this.setMandateTypeHidden(null);  
            this
                    .setBreadCrumbText(WebConstants.STANDARD_BENEFIT_CREATE_BREADCRUMB);
            return WebConstants.VIEW_ALL_VERSION_TO_COPY;

        }
        if (this.getActionForTest().equals("EDIT")) {
            getStandardBenefitSessionObject().setStandardBenefitMode("Mode");
            int retrieveKey = this.getSelectedStandardBenefitKey();
            String retrieveName = this.getSelectedStandardBenefitName();
            this.getStandardBenefitSessionObject().setStandardBenefitKey(
                    retrieveKey);
            this.getStandardBenefitSessionObject().setStandardBenefitName(
                    retrieveName);
            this.getStandardBenefitSessionObject().setStandardBenefitParentKey(
                    this.selectedParentSystemId);
            List searchResultList = (List) getSession().getAttribute(
                    WebConstants.BENEFIT_VIEWALL_RESULT);
            if (null != searchResultList && !searchResultList.isEmpty()) {
                for (int i = 0; i < searchResultList.size(); i++) {
                    StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                            .get(i);
                    if (retrieveKey == standardBenefitVOTemp
                            .getStandardBenefitKey()) {
                        this.getStandardBenefitSessionObject()
                                .setStandardBenefitStatus(
                                        standardBenefitVOTemp.getStatus());
                        if (null != standardBenefitVOTemp.getState())
                            this.getStandardBenefitSessionObject()
                                    .setStandardBenefitState(
                                            standardBenefitVOTemp.getState()
                                                    .getState());
	                        this.getStandardBenefitSessionObject()
	                        .setStandardBenefitVersionNumber(
	                                standardBenefitVOTemp.getVersion());
                        this.getStandardBenefitSessionObject()
                                .setBusinessDomains(
                                        standardBenefitVOTemp
                                                .getBusinessDomains());
                        this.getStandardBenefitSessionObject()
                                .setStandardBenefitParentKey(
                                        standardBenefitVOTemp
                                                .getStandardBenefitParentKey());
                        break;
                    }
                }
            }
            retrieveStandardBenefitDetails(retrieveKey);
            if (this.getStandardBenefitSessionObject().isCheckout())
                this.getStandardBenefitSessionObject().setCheckout(false);
            return WebConstants.VIEW_ALL_VERSION_TO_EDIT;

        }
        if (this.getActionForTest().equals("CHECKOUT")) {
            checkOutStandardBenefitVersions();
            return WebConstants.VIEW_ALL_VERSION_TO_CHECKOUT;
        }

        return "";
    }


    /**
     * @return Returns the printStandardBenefitKey.
     */
    public int getPrintStandardBenefitKey() {
        ArrayList validationMessages = null;
        int benefitKeyFromSearch;
        int count = 0;
        String keyString = (String) (getRequest()
                .getParameter(WebConstants.BENEFIT_KEY));
        StandardBenefitRetrieveRequest standardBenefitRetrieveRequest = (StandardBenefitRetrieveRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_STANDARD_BENEFIT);
        StandardBenefitVO standardBenefitVO = new StandardBenefitVO();
        StandardBenefitVO standardBenefitVOTemp = null;
        int i = 0;
        if (null != keyString) {
            benefitKeyFromSearch = Integer.parseInt(keyString);
            standardBenefitVO.setStandardBenefitKey(benefitKeyFromSearch);
            this.getStandardBenefitSessionObject().setStandardBenefitKey(
                    benefitKeyFromSearch);
            //getStandardBenefitSessionObject().setStandardBenefitKey(benefitKeyFromSearch);
            this.getStandardBenefitSessionObject().setStandardBenefitMode(
                    "View");
        } else {
            standardBenefitVO.setStandardBenefitKey(this
                    .getStandardBenefitSessionObject().getStandardBenefitKey());
        }

        List searchResultList = null;
        if (((null == getSession().getAttribute(
                WebConstants.BENEFIT_VIEWALL_RESULT)) && (null == getSession()
                .getAttribute(WebConstants.BENEFIT_SEARCH_RESULT)))
                || (this.getStandardBenefitSessionObject().isCheckout())
                || (this.getStandardBenefitSessionObject().isCopy())) {
            standardBenefitVO
                    .setBenefitIdentifier(this
                    .getStandardBenefitSessionObject()
                    .getStandardBenefitName());
            standardBenefitVO.setStandardBenefitParentKey(this
                    .getStandardBenefitSessionObject()
                    .getStandardBenefitParentKey());
            standardBenefitVO.setBusinessDomains(this
                    .getStandardBenefitSessionObject().getBusinessDomains());
            standardBenefitVO.setStatus(this.getStandardBenefitSessionObject()
                    .getStandardBenefitStatus());
            standardBenefitVO.setVersion(this.getStandardBenefitSessionObject()
                    .getStandardBenefitVersionNumber());
        } else if ((null != getSession().getAttribute(
                WebConstants.BENEFIT_VIEWALL_RESULT))) {
            searchResultList = (List) getSession().getAttribute(
                    WebConstants.BENEFIT_VIEWALL_RESULT);

            //change
            if (null != searchResultList && !searchResultList.isEmpty()) {
                for (i = 0; i < searchResultList.size(); i++) {
                    standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                            .get(i);
                    if (standardBenefitVO.getStandardBenefitKey() == standardBenefitVOTemp
                            	.getStandardBenefitKey()) {
                        standardBenefitVO
                                .setBenefitIdentifier(standardBenefitVOTemp
                                        .getBenefitIdentifier());
                        standardBenefitVO
                                .setStandardBenefitParentKey(standardBenefitVOTemp
                                        .getStandardBenefitParentKey());
                        standardBenefitVO
                                .setBusinessDomains(standardBenefitVOTemp
                                        .getBusinessDomains());
                        standardBenefitVO.setStatus(standardBenefitVOTemp
                                .getStatus());
                        standardBenefitVO.setVersion(standardBenefitVOTemp
                                .getVersion());
                        break;
                    }
                    count++;
                }
                if (count == searchResultList.size()) {
                    searchResultList = (List) getSession().getAttribute(
                            WebConstants.BENEFIT_SEARCH_RESULT);
                    if (null != searchResultList && !searchResultList.isEmpty()) {
                        for (i = 0; i < searchResultList.size(); i++) {
                            standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                                    .get(i);
                            if (standardBenefitVO.getStandardBenefitKey() == standardBenefitVOTemp
                                    .getStandardBenefitKey()) {
                                standardBenefitVO
                                        .setBenefitIdentifier(standardBenefitVOTemp
                                                .getBenefitIdentifier());
                                standardBenefitVO
                                        .setStandardBenefitParentKey(standardBenefitVOTemp
                                                .getStandardBenefitParentKey());
                                standardBenefitVO
                                        .setBusinessDomains(standardBenefitVOTemp
                                                .getBusinessDomains());
                                standardBenefitVO
                                        .setStatus(standardBenefitVOTemp
                                                .getStatus());
                                standardBenefitVO
                                        .setVersion(standardBenefitVOTemp
                                                .getVersion());
                                break;
                            }
                        }
                    }
                }
            }
            //end
        } else {
            searchResultList = (List) getSession().getAttribute(
                    WebConstants.BENEFIT_SEARCH_RESULT);
            if (null != searchResultList && !searchResultList.isEmpty()) {
                for (i = 0; i < searchResultList.size(); i++) {
                    standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                            .get(i);
                    if (standardBenefitVO.getStandardBenefitKey() == standardBenefitVOTemp
                            .getStandardBenefitKey()) {
                        standardBenefitVO
                                .setBenefitIdentifier(standardBenefitVOTemp
                                        .getBenefitIdentifier());
                        standardBenefitVO
                                .setStandardBenefitParentKey(standardBenefitVOTemp
                                        .getStandardBenefitParentKey());
                        standardBenefitVO
                                .setBusinessDomains(standardBenefitVOTemp
                                        .getBusinessDomains());
                        standardBenefitVO.setStatus(standardBenefitVOTemp
                                .getStatus());
                        standardBenefitVO.setVersion(standardBenefitVOTemp
                                .getVersion());
                        break;
                    }
                }
            }
        }

        standardBenefitRetrieveRequest.setStandardBenefitVO(standardBenefitVO);
        StandardBenefitResponse standardBenefitResponse = (StandardBenefitResponse) executeService(standardBenefitRetrieveRequest);

        if (null != standardBenefitResponse) {
            this.setMinorHeading(standardBenefitResponse.getStandardBenefitBO()
                    .getBenefitIdentifier());
            this.setBenefitMeaning(standardBenefitResponse.getStandardBenefitBO()
                    .getBenefitIdentifier());
            this.setDescription(standardBenefitResponse.getStandardBenefitBO()
                    .getDescription());
//          Enhancement:Benefit Category
        if(null!=standardBenefitResponse.getStandardBenefitBO().getBenefitCategory()){
	        this.setBenefitCategory(standardBenefitResponse.getStandardBenefitBO().getBenefitCategory());
	        this.setBenefitCategoryHidden(standardBenefitResponse.getStandardBenefitBO().getBenefitCategoryDesc());
        }
            this.setCreatedUser(standardBenefitResponse.getStandardBenefitBO()
                    .getCreatedUser());
            this.setCreatedTimestamp(standardBenefitResponse
                    .getStandardBenefitBO().getCreatedTimestamp());
            this.setLastUpdatedUser(standardBenefitResponse
                    .getStandardBenefitBO().getLastUpdatedUser());
            this.setLastUpdatedTimestamp(standardBenefitResponse
                    .getStandardBenefitBO().getLastUpdatedTimestamp());
            if (null != standardBenefitResponse.getStandardBenefitBO()
                    .getState()) {
                this.setState(standardBenefitResponse.getStandardBenefitBO()
                        .getState().getState());
            }
            this.setStatus(standardBenefitResponse.getStandardBenefitBO()
                    .getStatus());
            this.setVersion(standardBenefitResponse.getStandardBenefitBO()
                    .getVersion());
            this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB + " ("
                    + this.minorHeading + ") >> Edit");
            DomainDetail domainDetail = standardBenefitResponse
                    .getDomainDetail();
            if (domainDetail != null) {
                this.lob = WPDStringUtil.getTildaString(domainDetail
                        .getLineOfBusiness());
                this.businessEntity = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessEntity());
                this.businessGroup = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessGroup());
                this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
                        .getMarketBusinessUnit());
            }
            //enhancement
            this.setRule(getTildaStringFromRuleTypeList(standardBenefitResponse
                    .getStandardBenefitBO().getRuleTypeList()));
            this.ruleCodeList = WPDStringUtil.getListFromTildaString(this
                    .getRule(), 2, 1, 2);
            this.setBenefitType(standardBenefitResponse.getStandardBenefitBO()
                    .getBenefitType());
            String mandateTypeCode = standardBenefitResponse
                    .getStandardBenefitBO().getMandateType();
            if ((WebConstants.MNDT_TYPE ).equals(this.benefitType)) {
                if(null !=standardBenefitResponse.getStandardBenefitBO().getMandateDesc()){
                    this.setMandateType(standardBenefitResponse
                            .getStandardBenefitBO().getMandateDesc());
                }else{
                    this.setMandateType(standardBenefitResponse
                            .getStandardBenefitBO().getMandateType());
                }

            } else {
                this.setMandateType("");
            }

            if ((WebConstants.MNDT_TYPE).equals(this.benefitType)
                    && (("2").equals(this.mandateType) || ("3")
                            .equals(this.mandateType))) {
                // To get the state
                String statedesc = standardBenefitResponse
                        .getStandardBenefitBO().getStateCode();
                String stateid = standardBenefitResponse.getStandardBenefitBO()
                        .getStateDesc();
                String selectedStateId = statedesc + "~" + stateid;
                this.setSelectedStateId(selectedStateId);
            } else {
                String selectedStateId = " " + "~" + " ";
                this.setSelectedStateId(selectedStateId);
            }
            // 			if(("2").equals(this.mandateType)){
            // 				
            // 				this.mandateType = "State";
            // 	        	
            // 	        }
            // 			else if(("3").equals(this.mandateType)){
            // 				this.mandateType = "ET";
            // 			}
            // 			else
            // 				this.mandateType = "Federal";

            //end of enhncement
            this.setTerm(getTildaStringFromUniverseList(standardBenefitResponse
                    .getStandardBenefitBO().getTermList()));
            this
                    .setQualifier(getTildaStringFromUniverseList(standardBenefitResponse
                            .getStandardBenefitBO().getQualifierList()));
            this
                    .setProviderArrangement(getTildaStringFromUniverseList(standardBenefitResponse
                            .getStandardBenefitBO().getPVAList()));
            this
                    .setDataType(getTildaStringFromDataTypeList(standardBenefitResponse
                            .getStandardBenefitBO().getDataTypeList()));
            getStandardBenefitSessionObject().setStandardBenefitName(
                    minorHeading);
            this.getStandardBenefitSessionObject().setStandardBenefitStatus(
                    this.status);
            this.getStandardBenefitSessionObject().setStandardBenefitState(
                    this.state);
            this.getStandardBenefitSessionObject()
                    .setStandardBenefitVersionNumber(this.version);
            this.getStandardBenefitSessionObject().setMandateType(
                    mandateTypeCode);
//          Enhancement:Benefit Category
            this.getStandardBenefitSessionObject().setBenefitCategory(
            		benefitCategory);
        }
        //this.getStandardBenefitSessionObject().setStandardBenefitMode("Mode");
        return printStandardBenefitKey;
    }


    /**
     * @param printStandardBenefitKey
     *            The printStandardBenefitKey to set.
     */
    public void setPrintStandardBenefitKey(int printStandardBenefitKey) {
        this.printStandardBenefitKey = printStandardBenefitKey;
    }


    /**
     * @return benefitTypeTab
     * 
     * Returns the benefitTypeTab.
     */
    public String getBenefitTypeTab() {
        if (null != this.getStandardBenefitSessionObject().getBenefitType()) {
            if (this.getStandardBenefitSessionObject().getBenefitType().equals(
                    WebConstants.STD_TYPE))
                return WebConstants.STANDARD_BENEFIT_STANDARD;
            else
                return WebConstants.STANDARD_BENEFIT_MANDATE;
        }
        return "";
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
     * Returns the ruleResultList
     * 
     * @return List ruleResultList.
     */
    public List getRuleResultList() {
        return ruleResultList;
    }

    
  

    /**
     * Sets the ruleResultList
     * 
     * @param ruleResultList.
     */
    public void setRuleResultList(List ruleResultList) {
        this.ruleResultList = ruleResultList;
    }
    /**
     * @return mandateTypeHidden
     * 
     * Returns the mandateTypeHidden.
     */
    public String getMandateTypeHidden() {
        return mandateTypeHidden;
    }
    /**
     * @param mandateTypeHidden
     * 
     * Sets the mandateTypeHidden.
     */
    public void setMandateTypeHidden(String mandateTypeHidden) {
        this.mandateTypeHidden = mandateTypeHidden;
    }
    /**
     * @return benefitTypeHidden
     * 
     * Returns the benefitTypeHidden.
     */
    public String getBenefitTypeHidden() {
        return benefitTypeHidden;
    }
    /**
     * @param benefitTypeHidden
     * 
     * Sets the benefitTypeHidden.
     */
    public void setBenefitTypeHidden(String benefitTypeHidden) {
        this.benefitTypeHidden = benefitTypeHidden;
    }
    /**
     * @return checkForCopy
     * 
     * Returns the checkForCopy.
     */
    public boolean isCheckForCopy() {
        return checkForCopy;
    }
    /**
     * @param checkForCopy
     * 
     * Sets the checkForCopy.
     */
    public void setCheckForCopy(boolean checkForCopy) {
        this.checkForCopy = checkForCopy;
    }
	/**
	 * @return Returns the dummyVar.
	 */
	public String getDummyVar() {
		//loadStandardBenefit();
		return dummyVar;
	}
	/**
	 * @param dummyVar The dummyVar to set.
	 */
	public void setDummyVar(String dummyVar) {
		this.dummyVar = dummyVar;
	}

	/**
		 * @return Returns the printBreadCrumbText.
		 */
		public String getPrintBreadCrumbText() {
			   printBreadCrumbText = "Product Configuration >> Benefit ("+this.getStandardBenefitSessionObject().getStandardBenefitName()+") >> Print";

		   		return printBreadCrumbText;
		}
		/**
		 * @param printBreadCrumbText The printBreadCrumbText to set.
		 */
		public void setPrintBreadCrumbText(String printBreadCrumbText) {
			this.printBreadCrumbText = printBreadCrumbText;
		}
		
		
	
	/**
	 * @return Returns the lockAcquired.
	 */
	public boolean isLockAcquired() {
		return lockAcquired;
	}
	/**
	 * @param lockAcquired The lockAcquired to set.
	 */
	public void setLockAcquired(boolean lockAcquired) {
		this.lockAcquired = lockAcquired;
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
	 * @return Returns the benefitCategoryListForCombo.
	 */
	public List getBenefitCategoryListForCombo() {
		 benefitCategoryListForCombo = null;
	        
	        Application application = FacesContext.getCurrentInstance().getApplication();
			ReferenceDataBackingBean backingBean =  ((ReferenceDataBackingBean) application.createValueBinding("#{ReferenceDataBackingBeanCommon}").getValue(FacesContext.getCurrentInstance()));
//			if(!getStandardBenefitSessionObject().isCopyFlag()){
				benefitCategoryListForCombo = backingBean.getBenefitCategoryListForCombo();
			    return benefitCategoryListForCombo;
//	        }else{
//	        	benefitCategoryListForCombo.add(new SelectItem("",""));
//	            
//	        }
//	        return benefitCategoryListForCombo;
	}
	/**
	 * @param benefitCategoryListForCombo The benefitCategoryListForCombo to set.
	 */
	public void setBenefitCategoryListForCombo(List benefitCategoryListForCombo) {
		this.benefitCategoryListForCombo = benefitCategoryListForCombo;
	}
	
	/**
	 * @return Returns the requiredBenefitCategory.
	 */
	public boolean isRequiredBenefitCategory() {
		return requiredBenefitCategory;
	}
	/**
	 * @param requiredBenefitCategory The requiredBenefitCategory to set.
	 */
	public void setRequiredBenefitCategory(boolean requiredBenefitCategory) {
		this.requiredBenefitCategory = requiredBenefitCategory;
	}
	
	/**
	 * @return Returns the benefitCategoryHidden.
	 */
	public String getBenefitCategoryHidden() {
		return benefitCategoryHidden;
	}
	/**
	 * @param benefitCategoryHidden The benefitCategoryHidden to set.
	 */
	public void setBenefitCategoryHidden(String benefitCategoryHidden) {
		this.benefitCategoryHidden = benefitCategoryHidden;
	}
	/**
	 * @return Returns the benefitIdentifier.
	 */
	public String getBenefitIdentifier() {
		return benefitIdentifier;
	}
	/**
	 * @param benefitIdentifier The benefitIdentifier to set.
	 */
	public void setBenefitIdentifier(String benefitIdentifier) {
		this.benefitIdentifier = benefitIdentifier;
	}
	/**
	 * @return Returns the searchString.
	 */
	public String getSearchString() {
		return searchString;
	}
	/**
	 * @param searchString The searchString to set.
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	/**
	 * @return Returns the actionHidden.
	 */
	public String getActionHidden() {
		return actionHidden;
	}
	/**
	 * @param actionHidden The actionHidden to set.
	 */
	public void setActionHidden(String actionHidden) {
		this.actionHidden = actionHidden;
	}
	/**
	 * @return Returns the searchRuleIDHidden.
	 */
	public String getSearchRuleIDHidden() {
		return searchRuleIDHidden;
	}
	
	public  String getStrRuleType() {
		return strRuleType;
	}
	/**
	 * @param strRuleType The strRuleType to set.
	 */
	public  void setStrRuleType(String strRuleType) {
		this.strRuleType = strRuleType;
	}
	/**
	 * @param searchRuleIDHidden The searchRuleIDHidden to set.
	 */
	public void setSearchRuleIDHidden(String searchRuleIDHidden) {
		this.searchRuleIDHidden = searchRuleIDHidden;
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
}