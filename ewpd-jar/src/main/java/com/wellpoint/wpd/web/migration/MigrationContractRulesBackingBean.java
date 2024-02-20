/*
 * MigrationContractRulesBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.migration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.migration.bo.MigrationContractRulesBO;
import com.wellpoint.wpd.common.migration.request.MigrationContractRulesRequest;
import com.wellpoint.wpd.common.migration.response.MigrationContractRulesResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationContractRulesBackingBean extends MigrationBaseBackingBean {

    private boolean requiredRuleType = false;

    private boolean requiredRuleID = false;

    private boolean requiredProviderArrangement = false;

    private boolean exclusionRuleSelected = false;

    private boolean umRuleSelected = false;

    private boolean denialRuleSelected = false;

    private boolean pnrRuleSelected = false;

    private String ruleID;

    private String ruleType;

    private String providerArrangement;

    private String productRuleSysID;

    private List ruleCodeList;

    private List providerArrangementCodeList;

    private List ruleList = null;

    //private String loadProductRules;
    private HtmlInputHidden loadProductRules=new HtmlInputHidden();

    private boolean renderFlag = true;

    private boolean checkin;

    private boolean higherVersion = true;

    private String productType;

    List validationMessages = null;

    private List ruleTypeListForCombo;

    private List ruleIDList;

    private HtmlPanelGrid panel;

    private Map ruleCommentMap = new HashMap();

    private int dateSegmentKey;
    
 	private String ewpdGenRuleID;

    private static String BREAD_CRUMB_TEXT_STEP6 = "Administration >> Contract Migration Wizard >> Rules (Step 6)";
    
 	private boolean rulesValueCheck = true;


    /**
     * cONSTRUCTOR Setting the BreadCumb.
     */
    public MigrationContractRulesBackingBean() {

        super();
        this.setBreadCrumbTextLeft(BREAD_CRUMB_TEXT_STEP6);
        String contractId = getMigrationContractSession()
                .getMigrationContract().getContractId();
        String startDate = WPDStringUtil.convertDateToString(this
                .getMigrationContractSession().getStartDateEwpd());
        String endDate = WPDStringUtil.convertDateToString(this
                .getMigrationContractSession().getEndDate());
        this.setBreadCrumbTextRight(WebConstants.BREAD_CRUMB_CONTRACT
                + contractId + " (" + startDate + " - " + endDate + ")");

        if(!StringUtil.isEmpty(this
                    .getMigrationContractSession().getDateSegmentId())){
            dateSegmentKey = Integer.parseInt(this
                    .getMigrationContractSession().getDateSegmentId());
        }
        
    }


    /**
     * Returns the view page
     *  
     */

    public String displayMigrationContractRules() {
        List list = (List) getSession().getAttribute(
                WebConstants.MESSAGE_LIST_STEP3);

        if (null != list && list.size() > 0) {
            addAllMessagesToRequest(list);
        }
        return WebConstants.MIG_CONTRACT_STEP6;
    }


    public String next() {
        updateNavigationInfo(BusinessConstants.MIGRATION_NAVIGATION_FLAG_FALSE,
                false);
        if (validateRuleCommentsFields()) {
	        this.saveRuleComments();
	        if(null != this.validationMessages 
	        		&& !this.validationMessages.isEmpty()
					&& this.validationMessages.get(0) instanceof InformationalMessage
					){
	        	this.validationMessages.clear();
	        }
	        return this.goToNextPage(WebConstants.MIG_CONTRACT_STEP7);
        }if(this.validationMessages.size() == 0){
        	return this.goToNextPage(WebConstants.MIG_CONTRACT_STEP7);
        }
        return "";
    }


    public String back() {
    	
        updateNavigationInfo(BusinessConstants.MIGRATION_NAVIGATION_FLAG_TRUE,
                true);
        return this.goToNextPage(WebConstants.MIG_CONTRACT_STEP5);
    }


    public String cancel() {
        return WebConstants.MIG_CONTRACT_STEP6;
    }

    public void updateNavigationInfo(boolean navigationFlag, boolean navFlag) {
        MigrationContractRulesRequest migrationContractRulesRequest = null;
        MigrationContractRulesResponse migrationContractRulesResponse = null;
        if (navFlag)
            this.getMigrationContractSession().getNavigationInfo()
                    .setUpdateLastAccessedPageOnly(true);
        migrationContractRulesRequest = (MigrationContractRulesRequest) this
                .getServiceRequest(ServiceManager.MIGRATION_CONTRACT_RULES_REQ);
        migrationContractRulesRequest.setMigrationContractSession(this
                .getMigrationContractSession());
        migrationContractRulesRequest.setAction(BusinessConstants.STEP7);
        this.getMigrationContractSession().getNavigationInfo()
                .setNavigationFlag(navigationFlag);
        migrationContractRulesResponse = (MigrationContractRulesResponse) this
                .executeService(migrationContractRulesRequest);

        if (null != migrationContractRulesResponse
                && migrationContractRulesResponse.isSuccess()) {
            this.setToSession(migrationContractRulesResponse);
        }
    }


    public String done() {
    	if (validateRuleCommentsFields()) {
	    	this.saveRuleComments();
    	}
    	if (getMigrationContractSession().getNavigationInfo()
                .getStepCompleted() >= BusinessConstants.STEP7) {
        	return goToNextPage(WebConstants.MIG_CONTRACT_STEP9);
        } else {
            this.validationMessages = validateStepNumber();
        }
    	if(this.validationMessages.size() == 0){
    		return goToNextPage(WebConstants.MIG_CONTRACT_STEP9);
        }
        getRequest().setAttribute("RETAIN_Value", "");
        addAllMessagesToRequest(this.validationMessages);
        return WebConstants.MIG_CONTRACT_STEP6;
    }


    /**
     * This method deletes the selected admin and returns a new list of admin.
     * 
     * @return.
     */
    public String deleteRule() {

        if (null != productRuleSysID) {
            this.ruleCodeList = new ArrayList();
            try {
                this.ruleCodeList.add(new Integer(productRuleSysID));
            } catch (NumberFormatException nfe) {
                Logger.logInfo(nfe);
            }
        } else {
            return WebConstants.EMPTY_STRING;
        }

        MigrationContractRulesRequest migrationContractRulesRequest = (MigrationContractRulesRequest) this
                .getServiceRequest(ServiceManager.MIGRATION_CONTRACT_RULES_REQ);
        migrationContractRulesRequest
                .setAction(MigrationContractRulesRequest.DELETE_CONTRACT_RULES);
        migrationContractRulesRequest.setRulesList(ruleCodeList);
        migrationContractRulesRequest.setDateSegmentKey(this.dateSegmentKey);
        migrationContractRulesRequest.setEwpdGenRuleID(this.ewpdGenRuleID);
        this.setToRequest(migrationContractRulesRequest);
        MigrationContractRulesResponse migrationContractRulesResponse = (MigrationContractRulesResponse) this
                .executeService(migrationContractRulesRequest);

        if (null != migrationContractRulesResponse
                && migrationContractRulesResponse.isSuccess()) {
            this.setValidationMessages(migrationContractRulesResponse
                    .getMessages());
            this.setToSession(migrationContractRulesResponse);
        }
        addAllMessagesToRequest(this.validationMessages);
        productRuleSysID = WebConstants.EMPTY_STRING;
        if (this.exclusionRuleSelected) {
            this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION);
        } else if (this.denialRuleSelected) {
            this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL);
        } else if (this.umRuleSelected) {
            this.loadProductRules(BusinessConstants.RULE_TYPE_UM);
        } else if (this.pnrRuleSelected) {
            this.loadProductRules(BusinessConstants.RULE_TYPE_PNR);
        }
        getRequest().setAttribute("RETAIN_Value", "");
        return WebConstants.MIG_CONTRACT_STEP6;
    }


    /**
     * This method saves the selected admin and returns a new list of admin.
     * 
     * @return.
     */
    public String addAndStoreRule() {
        /*
         * Calling the form validation check method
         */
        if (!validateRequiredFields()) {
            addAllMessagesToRequest(this.validationMessages);
            return WebConstants.EMPTY_STRING;
        }
        /*
         * Calling the function to get a list of values from a tilda string.
         */
        this.ruleCodeList = WPDStringUtil.getListFromTildaString(this
                .getRuleID(), 2, 1, 1);

        MigrationContractRulesRequest migrationContractRulesRequest = (MigrationContractRulesRequest) this
                .getServiceRequest(ServiceManager.MIGRATION_CONTRACT_RULES_REQ);
        this.setToRequest(migrationContractRulesRequest);
        MigrationContractRulesResponse migrationContractRulesResponse = null;
        if (null == ruleCodeList || ruleCodeList.size() == 0) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
        } else {
            migrationContractRulesRequest
                    .setAction(MigrationContractRulesRequest.ADD_CONTRACT_RULES);
            migrationContractRulesRequest.setRulesList(ruleCodeList);
            migrationContractRulesRequest
                    .setDateSegmentKey(this.dateSegmentKey);
            migrationContractRulesResponse = (MigrationContractRulesResponse) this
                    .executeService(migrationContractRulesRequest);
        }

        if (null != migrationContractRulesResponse
                && migrationContractRulesResponse.isSuccess()) {
        	
     	    if(this.ruleType.startsWith(BusinessConstants.RULE_TYPE_DENIAL)){
     	    	this.setRuleSelected(false, true, false, false);
     	    }else if(this.ruleType.startsWith(BusinessConstants.RULE_TYPE_EXCLUSION)){ 
     	    	this.setRuleSelected(true, false, false, false);
    	    }else if(this.ruleType.startsWith(BusinessConstants.RULE_TYPE_PNR)){ 
     	    	this.setRuleSelected(false, false, false, true);
     	    }else if(this.ruleType.startsWith(BusinessConstants.RULE_TYPE_UM )){ 
     	    	this.setRuleSelected(false, false, true, false);
     	    }else{
     	    	this.setRuleSelected(true, false, false, false);
     	    }
             	
            this.ruleType = WebConstants.EMPTY_STRING;
            this.ruleID = WebConstants.EMPTY_STRING;
            this.setToSession(migrationContractRulesResponse);
        }
        this
                .setValidationMessages(migrationContractRulesResponse
                        .getMessages());
        

        if (this.exclusionRuleSelected) {
            this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION);
        } else if (this.denialRuleSelected) {
            this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL);
        } else if (this.umRuleSelected) {
            this.loadProductRules(BusinessConstants.RULE_TYPE_UM);
        } else if (this.pnrRuleSelected) {
            this.loadProductRules(BusinessConstants.RULE_TYPE_PNR);
        }
        addAllMessagesToRequest(this.validationMessages);
        getRequest().setAttribute("RETAIN_Value", "");
        return WebConstants.MIG_CONTRACT_STEP6;
    }
    /**
     * 
     * @param exclusionRuleSelected
     * @param denialRuleSelected
     * @param umRuleSelected
     * @param pnrRuleSelected
     */
	private void setRuleSelected(boolean exclusionRuleSelected, 
								boolean denialRuleSelected, 
								boolean umRuleSelected,
								boolean pnrRuleSelected){
	    	this.exclusionRuleSelected = exclusionRuleSelected;
 	    	this.denialRuleSelected = denialRuleSelected;
 	    	this.umRuleSelected = umRuleSelected;
 	    	this.pnrRuleSelected = pnrRuleSelected;		
	}
	
	private void saveRuleComments(){
	    if(this.ruleCommentMap.isEmpty()) return;
	    
        List updatedList = new ArrayList();
        updatedList.add(this.ruleCommentMap);

        MigrationContractRulesRequest migrationContractRulesRequest = (MigrationContractRulesRequest) this
                .getServiceRequest(ServiceManager.MIGRATION_CONTRACT_RULES_REQ);
        migrationContractRulesRequest
                .setAction(MigrationContractRulesRequest.UPDATE_CONTRACT_RULES);
        migrationContractRulesRequest.setRulesList(updatedList);
        migrationContractRulesRequest.setDateSegmentKey(this.dateSegmentKey);
        this.setToRequest(migrationContractRulesRequest);
        MigrationContractRulesResponse migrationContractRulesResponse = (MigrationContractRulesResponse) this
                .executeService(migrationContractRulesRequest);
        this.setToSession(migrationContractRulesResponse);
        this
                .setValidationMessages(migrationContractRulesResponse
                        .getMessages());	    
	}
	
    public String updateRulesComments() {
    	if (validateRuleCommentsFields()) {
            this.saveRuleComments();
            if (this.exclusionRuleSelected) {
                this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION);
            } else if (this.denialRuleSelected) {
                this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL);
            } else if (this.umRuleSelected) {
                this.loadProductRules(BusinessConstants.RULE_TYPE_UM);
            } else if (this.pnrRuleSelected) {
                this.loadProductRules(BusinessConstants.RULE_TYPE_PNR);
            }
    	}
        addAllMessagesToRequest(this.validationMessages);
        getRequest().setAttribute("RETAIN_Value", "");
        return WebConstants.EMPTY_STRING;
    }


    /*
     * Function to validate all the form fields 
     */
    private boolean validateRuleCommentsFields() {
        validationMessages = new ArrayList(1);
        boolean requiredField = false;
        if (this.ruleCommentMap == null || ruleCommentMap.size()==0) {
            requiredField = true;
        }else{
        //check all against rule comment, if any comment is modified than go for update	
            Iterator ruleIDIter = this.ruleCommentMap.keySet().iterator();
            String ruleComment;
            while (ruleIDIter.hasNext()) {
                Object key = ruleIDIter.next();
                ruleComment = (String) ruleCommentMap.get(key);
                //Validates name for special characters
                if (!ruleComment.trim().matches("^[\\d|a-z|A-Z|\\s]*$")) {
                	this.ruleCommentMap.put(key, WebConstants.EMPTY_STRING);
                    requiredField = true;
                }//end if                      
            }//end while       
            if(requiredField)		// no field modified
            {
                this.validationMessages.add(new ErrorMessage(BusinessConstants.MSG_PRODUCT_RULE_INVALID));
            }
        }
    	this.rulesValueCheck = !requiredField;
        return requiredField?false:true;        
    }    


	//------------------------------------------------ Validation method
    // -----------------------------------------
    /*
     * Function to validate all the form fields
     */
    private boolean validateRequiredFields() {
        validationMessages = new ArrayList();
        boolean requiredField = false;
        this.requiredRuleID = false;
        this.requiredRuleType = false;

        if (this.ruleID == null
                || WebConstants.EMPTY_STRING.equals(this.ruleID)) {
            requiredRuleID = true;
            requiredField = true;
        }
        if (this.ruleType == null
                || WebConstants.EMPTY_STRING.equals(this.ruleType)) {
            requiredRuleType = true;
            requiredField = true;
        }
        if (requiredField) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
        }

        return requiredField ? false : true;
    }


    //------------------------------------- panel -----------------------------
    /**
     * This method prepares the panel grid from rule list
     * 
     * @param ruleList
     */

    public void preparePanel(List ruleList) {
        final String DELETE_IMAGE_PATH = "../../images/delete.gif";
        MigrationContractRulesBO migrationContractRulesBO = new MigrationContractRulesBO();
        this.panel = new HtmlPanelGrid();
        panel.setWidth("694");
        panel.setColumns(7);
        panel.setBorder(0);
        panel.setId("panelTable");
        panel.setCellpadding("3");
        panel.setCellspacing("1");
        panel.setBgcolor("#cccccc");
        panel.setRendered(true);

        if (null != ruleList && ruleList.size() != 0) {
            for (int i = 0; i < ruleList.size(); i++) {
                migrationContractRulesBO = (MigrationContractRulesBO) ruleList
                        .get(i);

                HtmlOutputLabel td1 = new HtmlOutputLabel();
                HtmlOutputLabel td2 = new HtmlOutputLabel();
                HtmlOutputLabel td3 = new HtmlOutputLabel();
                HtmlOutputLabel td4 = new HtmlOutputLabel();
                HtmlOutputLabel td5 = new HtmlOutputLabel();

                //            	create a hidden field
                HtmlInputHidden ruleKey = new HtmlInputHidden();
        		HtmlInputHidden genRuleKey = new HtmlInputHidden();
                HtmlOutputText outputTextColumn1 = new HtmlOutputText();
                HtmlOutputText outputTextColumn2 = new HtmlOutputText();
                HtmlOutputText outputTextColumn3 = new HtmlOutputText();
                HtmlOutputText outputTextColumn4 = new HtmlOutputText();
                HtmlOutputText outputTextColumn5 = new HtmlOutputText();
                HtmlInputText inputTextColumn4 = new HtmlInputText();

                HtmlCommandButton deleteButton = new HtmlCommandButton();

                ValueBinding ruleComment = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{migrationContractRulesBackingBean.ruleCommentMap["
                                        + migrationContractRulesBO
                                                .getProductRuleSysID() + "]}");

                ruleKey.setValue(new Integer(migrationContractRulesBO.getProductRuleSysID()));
                ruleKey.setId("ruleKey" + i);

                genRuleKey.setValue(migrationContractRulesBO.getGenRuleID());
                genRuleKey.setId("genRuleKey" + i);
                
                outputTextColumn1.setValue(migrationContractRulesBO
                        .getGenRuleID());
                outputTextColumn2.setValue(migrationContractRulesBO.getRuleID());
                outputTextColumn3.setValue(migrationContractRulesBO.getRuleDescription());
                outputTextColumn4.setValue(migrationContractRulesBO.getProviderArrangement());
                outputTextColumn5.setValue(migrationContractRulesBO.getFlag());
                if (null == migrationContractRulesBO.getRuleComment()
                        || WebConstants.EMPTY_STRING.equals(migrationContractRulesBO
                                        			.getRuleComment())) {
                    inputTextColumn4.setValue(WebConstants.EMPTY_STRING);
                } else {
                    inputTextColumn4.setValue(new String(migrationContractRulesBO.getRuleComment()));
                }
                inputTextColumn4.setValueBinding("value", ruleComment);
                if(this.denialRuleSelected){
                	inputTextColumn4.setMaxlength(5);
                }
                else{
                	inputTextColumn4.setMaxlength(2);
                }
                inputTextColumn4.setStyle("font-family:Verdana, Arial, " +
                		"Helvetica, sans-serif;font-size:11px;width:50px;height:" +
                		"17px;background-color:#F4F4F4;border:solid #7f9db9 1px;" +
                		"color:#1762A5;");

                deleteButton.setValue("Delete");
                deleteButton.setId("deleteBtn" + i);
                deleteButton.setDisabled(false);
                deleteButton.setAlt("Delete");
                deleteButton.setStyle("cursor: hand");

                deleteButton.setOnclick("confirmDeletion('ruleKey" +i+"', 'genRuleKey"+i+"');return false;");
                deleteButton.setImage(DELETE_IMAGE_PATH);

                td1.getChildren().add(ruleKey);
                td1.getChildren().add(genRuleKey);
                td1.getChildren().add(outputTextColumn1);
                td2.getChildren().add(outputTextColumn2);
                td3.getChildren().add(outputTextColumn3);
                td4.getChildren().add(outputTextColumn4);
                td5.getChildren().add(outputTextColumn5);

                panel.getChildren().add(td1);
                panel.getChildren().add(td2);
                panel.getChildren().add(td3);
                panel.getChildren().add(td4);
                panel.getChildren().add(td5);
                panel.getChildren().add(inputTextColumn4);
                panel.getChildren().add(deleteButton);
            }
        } else {
            panel.setRendered(false);
        }
    }


    //  ----------------------------------- Load rules according to rule type as
    // filter -----------------------------
    public String loadExclusionRule() {
        this.exclusionRuleSelected = true;
        this.denialRuleSelected = false;
        this.umRuleSelected = false;
        this.pnrRuleSelected = false;
        this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION);
        getRequest().setAttribute("RETAIN_Value", "");
        ruleType ="";
		ruleID ="";
        return WebConstants.MIG_CONTRACT_STEP6;
    }


    public String loadDenialRule() {
        this.exclusionRuleSelected = false;
        this.denialRuleSelected = true;
        this.umRuleSelected = false;
        this.pnrRuleSelected = false;
        this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL);
        getRequest().setAttribute("RETAIN_Value", "");
        ruleType ="";
		ruleID ="";
        return WebConstants.MIG_CONTRACT_STEP6;
    }


    public String loadUMRule() {
        this.exclusionRuleSelected = false;
        this.denialRuleSelected = false;
        this.umRuleSelected = true;
        this.pnrRuleSelected = false;
        this.loadProductRules(BusinessConstants.RULE_TYPE_UM);
        getRequest().setAttribute("RETAIN_Value", "");
        ruleType ="";
		ruleID ="";
        return WebConstants.MIG_CONTRACT_STEP6;
    }


    public String loadPNRRule() {
        this.exclusionRuleSelected = false;
        this.denialRuleSelected = false;
        this.umRuleSelected = false;
        this.pnrRuleSelected = true;
        this.loadProductRules(BusinessConstants.RULE_TYPE_PNR);
        getRequest().setAttribute("RETAIN_Value", "");
        ruleType ="";
		ruleID ="";
        return WebConstants.MIG_CONTRACT_STEP6;
    }


    /**
     * Returns the loadProductRules
     */
    public void loadProductRules(String ruleType) {
        if (ruleType == null
                || ruleType.equals(WebConstants.EMPTY_STRING)
                || !(ruleType.equals(BusinessConstants.RULE_TYPE_EXCLUSION)
                        || ruleType.equals(BusinessConstants.RULE_TYPE_DENIAL)
                        || ruleType.equals(BusinessConstants.RULE_TYPE_UM) || ruleType
                        .equals(BusinessConstants.RULE_TYPE_PNR))) {
            ruleType = BusinessConstants.RULE_TYPE_EXCLUSION;
        } else if (ruleType.equals(BusinessConstants.RULE_TYPE_EXCLUSION)) {
            ruleType = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_EXCLUSION + "%";
        } else if (ruleType.equals(BusinessConstants.RULE_TYPE_DENIAL)) {
            ruleType = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_DENIAL + "%";
        } else if (ruleType.equals(BusinessConstants.RULE_TYPE_UM)) {
            ruleType = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_UM + "%";
        } else if (ruleType.equals(BusinessConstants.RULE_TYPE_PNR)) {
            ruleType = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_PNR + "%";
        }
        MigrationContractRulesRequest migrationContractRulesRequest = (MigrationContractRulesRequest) this
                .getServiceRequest(ServiceManager.MIGRATION_CONTRACT_RULES_REQ);
        migrationContractRulesRequest
                .setAction(MigrationContractRulesRequest.RETRIEVE_CONTRACT_RULES);
        migrationContractRulesRequest.setRuleType(ruleType);
        migrationContractRulesRequest.setDateSegmentKey(this.dateSegmentKey);

        MigrationContractRulesResponse migrationContractRulesResponse = (MigrationContractRulesResponse) this
                .executeService(migrationContractRulesRequest);

        if (null == migrationContractRulesResponse.getRuleList()
                || 0 == migrationContractRulesResponse.getRuleList().size()) {
            this.renderFlag = false;
        } else {
            setRuleList(migrationContractRulesResponse.getRuleList());
            this.renderFlag = true;
        }
        addAllMessagesToRequest(this.validationMessages);

    }


    //  --------------------------------- getters/setters -----------------------
	//WAS 7.0 Changes - Binding variable LoadProductRules modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
    /**
     * Returns the loadProductRules
     * 
     * @return String loadProductRules.
     */
    public HtmlInputHidden getLoadProductRules() {
        updateNavigationInfo(false, true);
        if (!(this.exclusionRuleSelected | this.denialRuleSelected
                | this.pnrRuleSelected | this.umRuleSelected)) {
            this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION);
            this.exclusionRuleSelected = true;
        }

        if (this.exclusionRuleSelected) {
            this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION);
        } else if (this.denialRuleSelected) {
            this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL);
        } else if (this.umRuleSelected) {
            this.loadProductRules(BusinessConstants.RULE_TYPE_UM);
        } else if (this.pnrRuleSelected) {
            this.loadProductRules(BusinessConstants.RULE_TYPE_PNR);
        }
        List list = (List) getSession().getAttribute(
                WebConstants.MESSAGE_LIST_STEP3);

        if (null != list && list.size() > 0) {
            addAllMessagesToRequest(list);
        }
        if (null != getSession().getAttribute(WebConstants.MESSAGE_LIST_STEP3))
            getSession().removeAttribute(WebConstants.MESSAGE_LIST_STEP3);
      //  return WebConstants.EMPTY_STRING;
        loadProductRules.setValue(WebConstants.EMPTY_STRING);
        return loadProductRules;
    }


    /**
     * Sets the loadProductRules
     * 
     * @param loadProductRules.
     */
    public void setLoadProductRules(HtmlInputHidden loadProductRules) {
        this.loadProductRules = loadProductRules;
    }


    /**
     * Returns the ruleTypeListForCombo
     * 
     * @return List ruleTypeListForCombo.
     */
    public List getRuleTypeListForCombo() {
        ruleTypeListForCombo = new ArrayList();
        ruleTypeListForCombo.add(new SelectItem(WebConstants.EMPTY_STRING));
        ruleTypeListForCombo.add(new SelectItem(
                BusinessConstants.RULE_TYPE_DENIAL,
                BusinessConstants.RULE_TYPE_DENIAL));
        ruleTypeListForCombo.add(new SelectItem(
                BusinessConstants.RULE_TYPE_EXCLUSION,
                BusinessConstants.RULE_TYPE_EXCLUSION));
        ruleTypeListForCombo.add(new SelectItem(BusinessConstants.RULE_TYPE_UM,
                BusinessConstants.RULE_TYPE_UM));
        ruleTypeListForCombo.add(new SelectItem(
                BusinessConstants.RULE_TYPE_PNR,
                BusinessConstants.RULE_TYPE_PNR));
        return ruleTypeListForCombo;
    }


    /**
     * Sets the ruleTypeListForCombo
     * 
     * @param ruleTypeListForCombo.
     */
    public void setRuleTypeListForCombo(List ruleTypeListForCombo) {
        this.ruleTypeListForCombo = ruleTypeListForCombo;
    }


    /**
     * Returns the ruleIDList
     * 
     * @return List ruleIDList.
     */
    public List getRuleIDList() {
        ruleIDList = new ArrayList();
        final String RULE_TYPE_PARAM = "ruleType";
        String ruleTypeCode = WebConstants.EMPTY_STRING;
        String ruleType = (String) getRequest().getParameter(RULE_TYPE_PARAM);
        if (null == ruleType || WebConstants.EMPTY_STRING.equals(ruleType)) {
            return ruleIDList;
        } else if (ruleType.equals(BusinessConstants.RULE_TYPE_DENIAL)) {
            ruleTypeCode = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_DENIAL + "%";
//            ruleTypeCode = BusinessConstants.RULE_TYPE_DENIAL_KEY;
        } else if (ruleType.equals(BusinessConstants.RULE_TYPE_EXCLUSION)) {
            ruleTypeCode = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_EXCLUSION + "%";
        } else if (ruleType.equals(BusinessConstants.RULE_TYPE_UM)) {
            ruleTypeCode = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_UM + "%";
        } else if (ruleType.equals(BusinessConstants.RULE_TYPE_PNR)) {
            ruleTypeCode = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_PNR + "%";
        }
        MigrationContractRulesRequest migrationContractRulesRequest = (MigrationContractRulesRequest) this
                .getServiceRequest(ServiceManager.MIGRATION_CONTRACT_RULES_REQ);
        migrationContractRulesRequest.setRuleType(ruleTypeCode);
        migrationContractRulesRequest
                .setAction(MigrationContractRulesRequest.RULE_ID);
        migrationContractRulesRequest.setDateSegmentKey(this.dateSegmentKey);

        MigrationContractRulesResponse migrationContractRulesResponse = (MigrationContractRulesResponse) this
                .executeService(migrationContractRulesRequest);
        ruleIDList = migrationContractRulesResponse.getRuleList();
        return ruleIDList;
    }


    /**
     * Sets the ruleIDList
     * 
     * @param ruleIDList.
     */
    public void setRuleIDList(List ruleIDList) {
        this.ruleIDList = ruleIDList;
    }


    /**
     * Returns the checkin
     * 
     * @return boolean checkin.
     */
    public boolean isCheckin() {
        return checkin;
    }


    /**
     * Sets the checkin
     * 
     * @param checkin.
     */
    public void setCheckin(boolean checkin) {
        this.checkin = checkin;
    }


    /**
     * Returns the higherVersion
     * 
     * @return boolean higherVersion.
     */
    public boolean isHigherVersion() {
        return higherVersion;
    }


    /**
     * Sets the higherVersion
     * 
     * @param higherVersion.
     */
    public void setHigherVersion(boolean higherVersion) {
        this.higherVersion = higherVersion;
    }


    /**
     * @return Returns the productType.
     */
    public String getProductType() {
        return productType;
    }


    /**
     * @param productType
     *            The productType to set.
     */
    public void setProductType(String productType) {
        this.productType = productType;
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
     * Returns the providerArrangement
     * 
     * @return String providerArrangement.
     */
    public String getProviderArrangement() {
        return providerArrangement;
    }


    /**
     * Sets the providerArrangement
     * 
     * @param providerArrangement.
     */
    public void setProviderArrangement(String providerArrangement) {
        this.providerArrangement = providerArrangement;
    }


    /**
     * Returns the productRuleSysID
     * 
     * @return String productRuleSysID.
     */
    public String getProductRuleSysID() {
        return productRuleSysID;
    }


    /**
     * Sets the productRuleSysID
     * 
     * @param productRuleSysID.
     */
    public void setProductRuleSysID(String productRuleSysID) {
        this.productRuleSysID = productRuleSysID;
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
     * Returns the requiredRuleID
     * 
     * @return boolean requiredRuleID.
     */
    public boolean isRequiredRuleID() {
        return requiredRuleID;
    }


    /**
     * Sets the requiredRuleID
     * 
     * @param requiredRuleID.
     */
    public void setRequiredRuleID(boolean requiredRuleID) {
        this.requiredRuleID = requiredRuleID;
    }


    /**
     * Returns the requiredRuleType
     * 
     * @return boolean requiredRuleType.
     */
    public boolean isRequiredRuleType() {
        return requiredRuleType;
    }


    /**
     * Sets the requiredRuleType
     * 
     * @param requiredRuleType.
     */
    public void setRequiredRuleType(boolean requiredRuleType) {
        this.requiredRuleType = requiredRuleType;
    }


    /**
     * Returns the ruleID
     * 
     * @return String ruleID.
     */
    public String getRuleID() {
        return ruleID;
    }


    /**
     * Sets the ruleID
     * 
     * @param ruleID.
     */
    public void setRuleID(String ruleID) {
        this.ruleID = ruleID;
    }


    /**
     * Returns the ruleList
     * 
     * @return List ruleList.
     */
    public List getRuleList() {
        return ruleList;
    }


    /**
     * Sets the ruleList
     * 
     * @param ruleList.
     */
    public void setRuleList(List ruleList) {
        this.ruleList = ruleList;
    }


    /**
     * Returns the ruleType
     * 
     * @return String ruleType.
     */
    public String getRuleType() {
        return ruleType;
    }


    /**
     * Sets the ruleType
     * 
     * @param ruleType.
     */
    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
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
     * Returns the ruleCodeList
     * 
     * @return List ruleCodeList.
     */
    public List getRuleCodeList() {
        return ruleCodeList;
    }


    /**
     * Sets the ruleCodeList
     * 
     * @param ruleCodeList.
     */
    public void setRuleCodeList(List ruleCodeList) {
        this.ruleCodeList = ruleCodeList;
    }


    /**
     * Returns the renderFlag
     * 
     * @return boolean renderFlag.
     */
    public boolean isRenderFlag() {
        return renderFlag;
    }


    /**
     * Sets the renderFlag
     * 
     * @param renderFlag.
     */
    public void setRenderFlag(boolean renderFlag) {
        this.renderFlag = renderFlag;
    }


    /**
     * Returns the panel
     * 
     * @return HtmlPanelGrid panel.
     */
    public HtmlPanelGrid getPanel() {
    	if(this.rulesValueCheck){
    		this.preparePanel(this.getRuleList());
    	}
        return panel;
    }


    /**
     * Sets the panel
     * 
     * @param panel.
     */
    public void setPanel(HtmlPanelGrid panel) {
        this.panel = panel;
    }


    /**
     * Returns the ruleCommentMap
     * 
     * @return Map ruleCommentMap.
     */
    public Map getRuleCommentMap() {
        return ruleCommentMap;
    }


    /**
     * Sets the ruleCommentMap
     * 
     * @param ruleCommentMap.
     */
    public void setRuleCommentMap(Map ruleCommentMap) {
        this.ruleCommentMap = ruleCommentMap;
    }


    /**
     * Returns the denialRuleSelected
     * 
     * @return boolean denialRuleSelected.
     */
    public boolean isDenialRuleSelected() {
        return denialRuleSelected;
    }


    /**
     * Sets the denialRuleSelected
     * 
     * @param denialRuleSelected.
     */
    public void setDenialRuleSelected(boolean denialRuleSelected) {
        this.denialRuleSelected = denialRuleSelected;
    }


    /**
     * Returns the exclusionRuleSelected
     * 
     * @return boolean exclusionRuleSelected.
     */
    public boolean isExclusionRuleSelected() {
        return exclusionRuleSelected;
    }


    /**
     * Sets the exclusionRuleSelected
     * 
     * @param exclusionRuleSelected.
     */
    public void setExclusionRuleSelected(boolean exclusionRuleSelected) {
        this.exclusionRuleSelected = exclusionRuleSelected;
    }


    /**
     * Returns the pnrRuleSelected
     * 
     * @return boolean pnrRuleSelected.
     */
    public boolean isPnrRuleSelected() {
        return pnrRuleSelected;
    }


    /**
     * Sets the pnrRuleSelected
     * 
     * @param pnrRuleSelected.
     */
    public void setPnrRuleSelected(boolean pnrRuleSelected) {
        this.pnrRuleSelected = pnrRuleSelected;
    }


    /**
     * Returns the umRuleSelected
     * 
     * @return boolean umRuleSelected.
     */
    public boolean isUmRuleSelected() {
        return umRuleSelected;
    }


    /**
     * Sets the umRuleSelected
     * 
     * @param umRuleSelected.
     */
    public void setUmRuleSelected(boolean umRuleSelected) {
        this.umRuleSelected = umRuleSelected;
    }
	/**
	 * Returns the ewpdGenRuleID
	 * @return String ewpdGenRuleID.
	 */
	public String getEwpdGenRuleID() {
		return ewpdGenRuleID;
	}
	/**
	 * Sets the ewpdGenRuleID
	 * @param ewpdGenRuleID.
	 */
	public void setEwpdGenRuleID(String ewpdGenRuleID) {
		this.ewpdGenRuleID = ewpdGenRuleID;
	}
	/**
	 * @return Returns the rulesValueCheck.
	 */
	public boolean isRulesValueCheck() {
		return rulesValueCheck;
	}
	/**
	 * @param rulesValueCheck The rulesValueCheck to set.
	 */
	public void setRulesValueCheck(boolean rulesValueCheck) {
		this.rulesValueCheck = rulesValueCheck;
	}
}