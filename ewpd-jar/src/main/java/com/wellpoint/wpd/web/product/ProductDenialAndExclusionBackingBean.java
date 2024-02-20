/*
 * ProductComponentAssociationBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.myfaces.component.html.ext.HtmlSelectBooleanCheckbox;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.product.bo.ProductAdminBO;
import com.wellpoint.wpd.common.product.bo.ProductRuleAssociationBO;
import com.wellpoint.wpd.common.product.request.DeleteProductRuleRequest;
import com.wellpoint.wpd.common.product.request.ProductRuleRefDataRequest;
import com.wellpoint.wpd.common.product.request.SaveProductRequest;
import com.wellpoint.wpd.common.product.request.SaveProductRulesRequest;
import com.wellpoint.wpd.common.product.response.DeleteProductRuleResponse;
import com.wellpoint.wpd.common.product.response.ProductRuleRefDataResponse;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.common.product.response.SaveProductRulesResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductDenialAndExclusionBackingBean extends ProductBackingBean {
 	private boolean requiredRuleType = false;
 	private boolean requiredRuleID = false;
 	private boolean requiredProviderArrangement = false;
 	private boolean viewRuleListRender = false;
 	private boolean	exclusionRuleSelected = false;
 	private boolean	umRuleSelected = false;
 	private boolean	denialRuleSelected = false;
 	private boolean	pnrRuleSelected = false;
 	private boolean rulesValueCheck = true;
 	private String ruleID;
 	private String ruleType;
 	private String providerArrangement;
 	private String productRuleSysID;
 	private int prodId;
 	private String ewpdGenRuleID;
 	private String hiddenList;
 	private String hiddenInit;
 	private String ruleIds;
 	private String genIds;
 	
 	private List exclusionRuleList;
 	private List umRuleList;
 	private List denialRuleList;
 	private List pnrlRuleList;
 	private String changedRuleIds;
 	
 	private int pageno=1;
 	
 	private boolean disabledForPrevButton ;
 	
 	private boolean disabledForNextButton = false;
 	
 	private String pageStatus;
 	
 	private String ruleIdsForDelete;
 	private String genruleIdsForDelete;
 	private int productSysId;
 	private String productName;
 	
	/**
	 * @return Returns the changedRuleIds.
	 */
	public String getChangedRuleIds() {
		return changedRuleIds;
	}
	/**
	 * @param changedRuleIds The changedRuleIds to set.
	 */
	public void setChangedRuleIds(String changedRuleIds) {
		this.changedRuleIds = changedRuleIds;
	}
	/**
	 * @return Returns the genIds.
	 */
	public String getGenIds() {
		return genIds;
	}
	/**
	 * @param genIds The genIds to set.
	 */
	public void setGenIds(String genIds) {
		this.genIds = genIds;
	}
	/**
	 * @return Returns the ruleIds.
	 */
	public String getRuleIds() {
		return ruleIds;
	}
	/**
	 * @param ruleIds The ruleIds to set.
	 */
	public void setRuleIds(String ruleIds) {
		this.ruleIds = ruleIds;
	}
	/**
	 * @return Returns the prodId.
	 */
	public int getProdId() {
		return prodId;
	}
	/**
	 * @param prodId The prodId to set.
	 */
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
 	private List ruleCodeList;
 	private List providerArrangementCodeList;

 	private List ruleList = null;
    private String state = null;
    private String status = null;
    private int version = 0;
 	//private String loadProductRules;
    private HtmlInputHidden loadProductRules=new HtmlInputHidden();
 	private boolean renderFlag = true;
	
    private boolean checkin;
    private boolean higherVersion = true;    
    private String productType;
    
    List validationMessages=null;

    private List ruleTypeListForCombo;
    private List ruleIDList;
    private HtmlPanelGrid panel;
    private Map ruleCommentMap = new HashMap();
    private String printValue;
    private List ruleListForView = new ArrayList(1);
    
    private String selectedRuleType = null;
    private boolean hasValidationErrors;

    //Setting the BreadCumb.
    public ProductDenialAndExclusionBackingBean() {
    	
    	this.productSysId = getProductSessionObject().getProductKeyObject().getProductId();
    	this.productName = getProductSessionObject().getProductKeyObject().getProductName();
    	
        if (getProductSessionObject().getMode() == ProductSessionObject.VIEW_MODE) {
            this.setBreadCumbTextForView();
        } else {
            this.setBreadCumbTextForEdit();
        }
        if (null != getProductSessionObject().getProductKeyObject()) {
            if (getProductSessionObject().getProductKeyObject().getVersion() > 0)
                higherVersion = true;
            else
                higherVersion = false;
        } else {
            higherVersion = false;
        }
    }


    public String done() {
    	
    	getRequest().setAttribute("RETAIN_Value", "");
        List msgListOne = new ArrayList(2);
        List msgListTwo = new ArrayList(2);
     
        if(!this.validateRequiredFields()){
            if((this.requiredRuleID && this.requiredRuleType && this.requiredProviderArrangement)){
            	this.requiredRuleID = false;	
            	this.requiredRuleType = false;	
            	this.requiredProviderArrangement = false;	
            }else{
            	msgListOne = this.getValidationMessages();
                addAllMessagesToRequest(msgListOne);
            	if(this.exclusionRuleSelected){
            		this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION,this.pageno);
            	}else if(this.denialRuleSelected){
        			this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL,this.pageno);
            	}else if(this.umRuleSelected){
        			this.loadProductRules(BusinessConstants.RULE_TYPE_UM,this.pageno);
            	}else if(this.pnrRuleSelected){
        			this.loadProductRules(BusinessConstants.RULE_TYPE_PNR,this.pageno);
            	}
               return "productDenialAndExclusion";
            }
        }else{
        	this.addAndStoreRule();
        	msgListOne = this.getValidationMessages();        	
        }
        this.updateRulesComments();
        SaveProductRequest saveProductRequest = (SaveProductRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT);
        SaveProductResponse saveProductResponse = null;

        saveProductRequest.setAction(SaveProductRequest.CHECKIN_PRODUCT);
        
        if(null!=getSession().getAttribute("AM_BENEFIT"))
			getSession().removeAttribute("AM_BENEFIT");
		if(null!=getSession().getAttribute("AM_BC_KEY"))
			getSession().removeAttribute("AM_BC_KEY");
		if(null!=getSession().getAttribute("AM_ENTITY_KEY"))
			getSession().removeAttribute("AM_ENTITY_KEY");
		if(null!=getSession().getAttribute("DIRECT_CLICK"))
			getSession().removeAttribute("DIRECT_CLICK");
        
        saveProductRequest.setCheckIn(this.checkin);
        saveProductRequest.getProduct().setProductType(this.productType);
        saveProductRequest.getProduct().setProductKey(super.getProductKeyFromSession());
        saveProductRequest.getProduct().setProductName(
        		super.getProductNameFromSession());
        saveProductRequest.getProduct().setProductStructureKey(
        		super.getProductSessionObject().getProductStructKey());
        saveProductResponse = (SaveProductResponse) this
                .executeService(saveProductRequest);

        if (null != saveProductResponse) {
        	// Rule Validation
        	getSession().setAttribute(
					WebConstants.SESSION_DELETED_RULES_LIST, 
					saveProductResponse.getDeletedRulesList());
        	getSession().setAttribute(
					WebConstants.SESSION_UNCODED_RULES_LIST, 
					saveProductResponse.getUnCodedRulesList());
            if (null != saveProductResponse.getMessages())
                msgListTwo = saveProductResponse.getMessages();
            msgListOne.addAll(msgListTwo);
            addAllMessagesToRequest(msgListOne);
            this.setValidationMessages(msgListOne);
			if(saveProductResponse.getCondition() == SaveProductResponse.VALIDATION_RESULTS){
				hasValidationErrors = true;
				setValuesForAminMethodValidation();
				return "";
			}else if(saveProductResponse.getCondition() == SaveProductResponse.VALIDATION_WAIT){
				getSession().setAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN,
						"productDenialAndExclusionBackingBean");
				getSession().setAttribute(WebConstants.OBJECT_VALUE_FOR_CHECKIN,
						this);
				getSession().setAttribute(WebConstants.ENTITY_ID_FOR_CHECKIN, 
						new Integer(super.getProductKeyFromSession()));
				getSession().setAttribute(WebConstants.ENTITY_TYPE_FOR_CHECKIN, 
						WebConstants.PROD_TYPE);
				return "validationWait";
			}
            //if it is check in only, the following navigation happens.
            if (saveProductResponse.isSuccess()) {
                if (saveProductRequest.isCheckIn()) {
                    cleanSession();
                    return "createProduct";
                }
            }
        }
    	if(this.exclusionRuleSelected){
    		this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION,this.pageno);
    	}else if(this.denialRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL,this.pageno);
    	}else if(this.umRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_UM,this.pageno);
    	}else if(this.pnrRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_PNR,this.pageno);
    	}
       return "productDenialAndExclusion";
    }


    /**
     * Returns the panel
     * 
     * @return HtmlPanelGrid panel.
     */

    public String displayDenialAndExclusionTab() {

        if (getProductSessionObject().getMode() == ProductSessionObject.VIEW_MODE){
            return "productDenialAndExclusionView";
        }
        else
            return "productDenialAndExclusion";    	
    }



    /**
     * This method deletes the selected admin and returns a new list of admin.
     * 
     * @return.
     */
    public String deleteRule() {
    	getRequest().setAttribute("RETAIN_Value", "");
        DeleteProductRuleRequest deleteProductRuleRequest = (DeleteProductRuleRequest) this
                .getServiceRequest(ServiceManager.DELETE_PRODUCT_RULE);
        if ( null != productRuleSysID) {
        	deleteProductRuleRequest.setProductRuleSysID(Integer.parseInt(productRuleSysID));
        	deleteProductRuleRequest.setEwpdGenRuleID(this.ewpdGenRuleID);
         }
        DeleteProductRuleResponse deleteProductRuleResponse = (DeleteProductRuleResponse) executeService(deleteProductRuleRequest);
        if (null != deleteProductRuleResponse && deleteProductRuleResponse.isSuccess()) {
           	// set  save  message
       		this.setValidationMessages(deleteProductRuleResponse.getMessages());           		
        }
        productRuleSysID = WebConstants.EMPTY_STRING;
    	if(this.exclusionRuleSelected){
    		this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION,this.pageno);
    	}else if(this.denialRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL,this.pageno);
    	}else if(this.umRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_UM,this.pageno);
    	}else if(this.pnrRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_PNR,this.pageno);
    	}
        return "productDenialAndExclusion";
    }

    /**
     * This method deletes the selected admin and returns a new list of admin.
     * 
     * @return.
     */
    public String deleteRules() {
//    	String rulelds[]=this.getRuleIds()==null?null:this.getRuleIds().split("~");
//    	String genIds[]=this.getGenIds()==null?null:this.getGenIds().split("~");
    	
    	String rulelds[] =  getRuleIds(this.ruleIdsForDelete);
    	List genIds	=  getGenRuleIds(this.genruleIdsForDelete);
    	if(rulelds!=null&&genIds!=null&&rulelds.length==genIds.size()){
		 
    	for(int i=0;i<rulelds.length;i++){
    		int ruleId=0;
    		try{
    			ruleId=Integer.parseInt(String.valueOf(rulelds[i]));
    		}catch(NumberFormatException numberFormatException){
    			continue;
    		}
    		
    	getRequest().setAttribute("RETAIN_Value", "");
        DeleteProductRuleRequest deleteProductRuleRequest = (DeleteProductRuleRequest) this
                .getServiceRequest(ServiceManager.DELETE_PRODUCT_RULE);
        deleteProductRuleRequest.setProductRuleSysID(ruleId);
        deleteProductRuleRequest.setEwpdGenRuleID(genIds.get(i).toString());
        Logger.logDebug(changedRuleIds);  
        DeleteProductRuleResponse deleteProductRuleResponse = (DeleteProductRuleResponse) executeService(deleteProductRuleRequest);
        this.ruleIdsForDelete =null;
        this.genruleIdsForDelete =null;
        if (null != deleteProductRuleResponse && deleteProductRuleResponse.isSuccess()) {
            InformationalMessage message = new InformationalMessage(BusinessConstants.MSG_PRODUCT_RULES_DELETE_SUCCESS);
            List list=new ArrayList(1);
            list.add(message);
           	// set  save  message
       		this.setValidationMessages(list);           		
        }
        productRuleSysID = WebConstants.EMPTY_STRING;

    	}
    	if(this.exclusionRuleSelected){
    		this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION,this.pageno);
    	}else if(this.denialRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL,this.pageno);
    	}else if(this.umRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_UM,this.pageno);
    	}else if(this.pnrRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_PNR,this.pageno);
    	}
    	}
        return "productDenialAndExclusion";
    }
    /**
     * This method saves the selected admin and returns a new list of admin.
     * 
     * @return.
     */
    public String addAndStoreRule() {
    	getRequest().setAttribute("RETAIN_Value", "");
    	String returnString = WebConstants.EMPTY_STRING;
 	    /*
 	     * Calling the form validation check method
 	     */
 	    if (validateRequiredFields()) {
 	    	
 	    /*
 	     * Calling the function to get a list of values from a tilda string.
 	     */
 	    this.providerArrangementCodeList = WPDStringUtil.getListFromTildaString(this.getProviderArrangement(), 2, 2, 2);
 	    this.ruleCodeList = WPDStringUtil.getListFromTildaString(this.getRuleID(),2,1,2);
 	    
 	    StringBuffer genRuleID = new StringBuffer();
 	    
 	    
        SaveProductRulesRequest saveProductRulesRequest = (SaveProductRulesRequest) this
										.getServiceRequest(ServiceManager.SAVE_PRODUCT_RULES);
        saveProductRulesRequest.setAction(SaveProductRulesRequest.ADD_PRODUCT_RULES);
        saveProductRulesRequest.setRulesIdList( this.ruleCodeList);
        saveProductRulesRequest.setPvaList( this.providerArrangementCodeList);
        saveProductRulesRequest.setRuleType(this.ruleType);
        SaveProductRulesResponse saveProductRulesResponse = null;
        if (null == this.ruleCodeList || this.ruleCodeList.size() == 0 
        		||null == this.providerArrangementCodeList || this.providerArrangementCodeList.size() == 0) {
            this.validationMessages.add(new ErrorMessage(WebConstants.MANDATORY_FIELDS_REQUIRED));
        }else{
        saveProductRulesResponse = (SaveProductRulesResponse) executeService(saveProductRulesRequest);
        }
        if (null != saveProductRulesResponse
                && saveProductRulesResponse.isSuccess()) {
        	this.ruleType = WebConstants.EMPTY_STRING;
        	this.ruleID = WebConstants.EMPTY_STRING;
        	this.providerArrangement = WebConstants.EMPTY_STRING;
        }
    	this.setValidationMessages(saveProductRulesResponse.getMessages());
    	returnString = "productDenialAndExclusion";
        }//end validation if
    	if(this.exclusionRuleSelected){
    		this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION,this.pageno);
    	}else if(this.denialRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL,this.pageno);
    	}else if(this.umRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_UM,this.pageno);
    	}else if(this.pnrRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_PNR,this.pageno);
    	}
        addAllMessagesToRequest(this.validationMessages);       
        return returnString;
    }

    public String updateRulesComments(){
 	    /*
 	     * Calling the form validation check method
 	     */
    	getRequest().setAttribute("RETAIN_Value", "");
    	prepareMapForUpdate(this.changedRuleIds);
 	    if (validateRuleCommentsFields()) {
    	List updatedList = new ArrayList(1);
    	updatedList.add(this.ruleCommentMap);
    	///**********************************
        SaveProductRulesRequest saveProductRulesRequest = (SaveProductRulesRequest) this
																.getServiceRequest(ServiceManager.SAVE_PRODUCT_RULES);
        saveProductRulesRequest.setAction(SaveProductRulesRequest.UPDATE_PRODUCT_RULES);
        saveProductRulesRequest.setRulesList(updatedList);
        SaveProductRulesResponse saveProductRulesResponse = null;
        saveProductRulesResponse = (SaveProductRulesResponse) executeService(saveProductRulesRequest);
        
    	this.setValidationMessages(saveProductRulesResponse.getMessages());
	    	if(this.exclusionRuleSelected){
	    		this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION,this.pageno);
	    	}else if(this.denialRuleSelected){
				this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL,this.pageno);
	    	}else if(this.umRuleSelected){
				this.loadProductRules(BusinessConstants.RULE_TYPE_UM,this.pageno);
	    	}else if(this.pnrRuleSelected){
				this.loadProductRules(BusinessConstants.RULE_TYPE_PNR,this.pageno);
	    	}
	        return "productDenialAndExclusion";
 	    }//end if
        addAllMessagesToRequest(this.validationMessages);       
        return WebConstants.EMPTY_STRING;
    }
//------------------------------------------------ Validation method -----------------------------------------
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
    /*
     * Function to validate all the form fields 
     */
    private boolean validateRequiredFields() {
        validationMessages = new ArrayList(1);
        boolean requiredField = false;
        this.requiredRuleID = false;
        this.requiredRuleType = false;
        this.requiredProviderArrangement = false;
        
        if (this.ruleID == null || WebConstants.EMPTY_STRING.equals(this.ruleID)) {
            requiredRuleID = true;
            requiredField = true;
        }
        if (this.ruleType == null || WebConstants.EMPTY_STRING.equals(this.ruleType)) {
            requiredRuleType = true;
            requiredField = true;
        }
        if (this.providerArrangement == null || WebConstants.EMPTY_STRING.equals(this.providerArrangement)) {
            requiredProviderArrangement = true;
            requiredField = true;
        }
        if(requiredField)
        { 
        	validationMessages.add(new ErrorMessage(WebConstants.MANDATORY_FIELDS_REQUIRED));
        }
        return requiredField?false:true;
    }
//------------------------------------- panel -----------------------------
    /**
     * This method prepares the panel grid from rule list
     * 
     * @param List ruleList.
     */

    public void preparePanel(List ruleList) {
        final String DELETE_IMAGE_PATH = "../../images/delete.gif";
        final String VIEW_IMAGE_PATH = "../../images/view.gif";
        ProductAdminBO productAdminBO = new ProductAdminBO();
        ProductRuleAssociationBO productRuleAssociationBO = new ProductRuleAssociationBO();
        this.panel = new HtmlPanelGrid();
//      panel.setWidth(WebConstants.PANEL_WIDTH_PS_VIEW);
        panel.setWidth("694");
        panel.setColumns(8);
        panel.setBorder(0);
        panel.setId("panelTable");
        panel.setCellpadding("3");
        panel.setCellspacing("1");
        panel.setBgcolor("#cccccc");
        panel.setRendered(true);
        this.prodId = this.getProductKeyFromSession();
        if (null != ruleList && ruleList.size() != 0) {
            for (int i = 0; i < ruleList.size(); i++) {
                productRuleAssociationBO = (ProductRuleAssociationBO) ruleList.get(i);
        	HtmlOutputLabel td1 = new HtmlOutputLabel();
        	td1.setId("td1"+RandomStringUtils.randomAlphanumeric(15));
        	HtmlOutputLabel td2 = new HtmlOutputLabel();
        	td2.setId("td2"+RandomStringUtils.randomAlphanumeric(15));
        	HtmlOutputLabel td3 = new HtmlOutputLabel();
        	td3.setId("td3"+RandomStringUtils.randomAlphanumeric(15));
        	HtmlOutputLabel td4 = new HtmlOutputLabel();
        	td4.setId("td4"+RandomStringUtils.randomAlphanumeric(15));
        	HtmlOutputLabel td5 = new HtmlOutputLabel();
        	td5.setId("td5"+RandomStringUtils.randomAlphanumeric(15));
        	HtmlOutputLabel td6 = new HtmlOutputLabel();
        	td6.setId("td6"+RandomStringUtils.randomAlphanumeric(15));
        	HtmlOutputLabel td7 = new HtmlOutputLabel();
        	td7.setId("td7"+RandomStringUtils.randomAlphanumeric(15));

//            	create a hidden field 
        		HtmlInputHidden ruleKey = new HtmlInputHidden();
        		HtmlInputHidden genRuleKey = new HtmlInputHidden();
                HtmlOutputText outputTextColumn1 = new HtmlOutputText();
                HtmlOutputText outputTextColumn2 = new HtmlOutputText();
                HtmlOutputText outputTextColumn3 = new HtmlOutputText();
                HtmlOutputText outputTextColumn4 = new HtmlOutputText();
                HtmlOutputText outputTextColumn5 = new HtmlOutputText();
                HtmlOutputText outputTextColumn6 = new HtmlOutputText();
                HtmlInputText inputTextColumn4 = new HtmlInputText();
                HtmlInputHidden ruleId = new HtmlInputHidden();
                HtmlSelectBooleanCheckbox deleteCheckBox = new HtmlSelectBooleanCheckbox();
                HtmlCommandButton viewButton = new HtmlCommandButton();
                
                
               // HtmlOutputLabel lblType = new HtmlOutputLabel();
                
//                ValueBinding ruleComment = FacesContext.getCurrentInstance()
//                		.getApplication().createValueBinding(
//                				"#{productDenialAndExclusionBackingBean.ruleCommentMap["+ productRuleAssociationBO.getProductRuleSysID() + "]}");               

                ruleKey.setValue(new Integer(productRuleAssociationBO.getProductRuleSysID()));
                ruleKey.setId("ruleKey" + i);

                genRuleKey.setValue(productRuleAssociationBO.getGenRuleID());
                genRuleKey.setId("genRuleKey" + i);
                
                ruleId.setValue(productRuleAssociationBO.getRuleID());
                ruleId.setId("ruleId" + i);
                
                outputTextColumn1.setValue(productRuleAssociationBO.getGenRuleID());
                outputTextColumn2.setValue(productRuleAssociationBO.getRuleID());
                outputTextColumn3.setValue(productRuleAssociationBO.getRuleDescription());
                outputTextColumn4.setValue(productRuleAssociationBO.getProviderArrangement());
                outputTextColumn5.setValue(productRuleAssociationBO.getFlag());
                outputTextColumn6.setValue(" ");
                if(null==productRuleAssociationBO.getRuleComment()||WebConstants.EMPTY_STRING.equals(productRuleAssociationBO.getRuleComment())){
                    inputTextColumn4.setValue(WebConstants.EMPTY_STRING);
                }else{  //*******************
                    inputTextColumn4.setValue(new String(productRuleAssociationBO.getRuleComment()));
                }
            //    inputTextColumn4.setValueBinding("value",ruleComment);
                inputTextColumn4.setId("inputvalue"+i);
                inputTextColumn4.setOnchange("markChange(" + "'" + productRuleAssociationBO.getProductRuleSysID() + "','"+"inputvalue" +i
            										 +"');");
                
                if(this.denialRuleSelected){
                	inputTextColumn4.setMaxlength(5);
                }
                else{
                	inputTextColumn4.setMaxlength(2);
                }
                inputTextColumn4.setStyle("font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:50px;height:17px;background-color:#F4F4F4;border:solid #7f9db9 1px;color:#1762A5;");
                                
                deleteCheckBox.setValue("Delete");
                deleteCheckBox.setId("deleteBtn" + i);
                deleteCheckBox.setDisabled(false);
                deleteCheckBox.setSelected(productRuleAssociationBO.isDeleteFlag());
                
                viewButton.setValue("View");
                viewButton.setId("viewBtn" + i);
                viewButton.setDisabled(false);
                viewButton.setAlt("View");
                viewButton.setStyle("cursor: hand"); 
//                deleteCheckBox.setOnclick("enableForPanel(" + "'" + productRuleAssociationBO.getGenRuleID() + "','" 
//                		+  productRuleAssociationBO.getProductRuleSysID()+ "','" + "deleteBtn"+i
//						+"');");
                deleteCheckBox.setOnclick("enableForPanel('denialAndExclusionForm:panelTable',7,0,'denialAndExclusionForm:delButton');deleteForPagination(" + "'"+ productRuleAssociationBO.getGenRuleID() + "','" + productRuleAssociationBO.getProductRuleSysID() + "','"+"deleteBtn" +i
            										 +"');");
                		
//                		 + productRuleAssociationBO.getGenRuleID()
//						 +productRuleAssociationBO.getProductRuleSysID()
//						 +"deleteBtn"+i
//						 +"');");
               
                
                 viewButton.setOnclick("viewAction('ruleId"+i+"');return false;");
                viewButton.setImage(VIEW_IMAGE_PATH);

                td1.getChildren().add(ruleKey);
                td1.getChildren().add(genRuleKey);
                td1.getChildren().add(ruleId);
                td1.getChildren().add(outputTextColumn1);
                td2.getChildren().add(outputTextColumn2);
                td3.getChildren().add(outputTextColumn3);                
                td4.getChildren().add(outputTextColumn4);                
                td5.getChildren().add(outputTextColumn5);
                td6.getChildren().add(viewButton);
                td6.getChildren().add(outputTextColumn6);
                td7.getChildren().add(inputTextColumn4);
                panel.getChildren().add(td1);
                panel.getChildren().add(td2);
                panel.getChildren().add(td3);
                panel.getChildren().add(td4);
                panel.getChildren().add(td5);
                panel.getChildren().add(td7);
                panel.getChildren().add(td6);
                panel.getChildren().add(deleteCheckBox);
            }
        }else {
        	panel.setRendered(false);
        }
    }
    
//  ----------------------------------- Load rules according to rule type as filter -----------------------------
    public String loadExclusionRule(){
    	getRequest().setAttribute("RETAIN_Value", "");
    	this.panel = null;
    	this.pageno=1;
    	this.changedRuleIds =null;
    	this.ruleIdsForDelete = null;
    	this.genruleIdsForDelete = null;
    	this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION,this.pageno);
    	this.exclusionRuleSelected = true;
    	this.denialRuleSelected = false;
    	this.umRuleSelected 	= false;
    	this.pnrRuleSelected 	= false;
    	return this.displayDenialAndExclusionTab();
    }
    public String loadDenialRule(){
    	getRequest().setAttribute("RETAIN_Value", "");
    	this.panel = null;
    	this.exclusionRuleSelected = false;
    	this.denialRuleSelected = true;
    	this.umRuleSelected 	= false;
    	this.pnrRuleSelected 	= false;
    	this.pageno=1;
    	this.changedRuleIds =null;
    	this.ruleIdsForDelete = null;
    	this.genruleIdsForDelete = null;
    	this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL,this.pageno);
    	return this.displayDenialAndExclusionTab();
    }
    public String loadUMRule(){
    	getRequest().setAttribute("RETAIN_Value", "");
    	this.panel = null;
    	this.exclusionRuleSelected = false;
    	this.denialRuleSelected = false;
    	this.umRuleSelected 	= true;
    	this.pnrRuleSelected 	= false;
    	this.pageno=1;
    	this.changedRuleIds =null;
    	this.ruleIdsForDelete = null;
    	this.genruleIdsForDelete = null;
    	this.loadProductRules(BusinessConstants.RULE_TYPE_UM,this.pageno);
    	return this.displayDenialAndExclusionTab();
    }
    public String loadPNRRule(){
    	getRequest().setAttribute("RETAIN_Value", "");
    	this.panel = null;
    	this.exclusionRuleSelected = false;
    	this.denialRuleSelected = false;
    	this.umRuleSelected 	= false;
    	this.pnrRuleSelected 	= true;
    	this.pageno=1;
    	this.changedRuleIds =null;
    	this.ruleIdsForDelete = null;
    	this.genruleIdsForDelete = null;
    	this.loadProductRules(BusinessConstants.RULE_TYPE_PNR,this.pageno);
    	return this.displayDenialAndExclusionTab();
    }
	/**
	 * Returns the loadProductRules
	 */
	public void loadProductRules(String ruleType,int pagenumber) {
		if(ruleType==null || ruleType.equals(WebConstants.EMPTY_STRING) || !(ruleType.equals(BusinessConstants.RULE_TYPE_EXCLUSION)
														|| ruleType.equals(BusinessConstants.RULE_TYPE_DENIAL)
														|| ruleType.equals(BusinessConstants.RULE_TYPE_UM)
														|| ruleType.equals(BusinessConstants.RULE_TYPE_PNR)	)	){
			ruleType = BusinessConstants.RULE_TYPE_EXCLUSION;
		}else if(ruleType.equals(BusinessConstants.RULE_TYPE_EXCLUSION)){
			ruleType = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_EXCLUSION +"%";
		}else if(ruleType.equals(BusinessConstants.RULE_TYPE_DENIAL)){
			ruleType = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_DENIAL +"%";
		}else if(ruleType.equals(BusinessConstants.RULE_TYPE_UM)){
			ruleType = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_UM +"%";
		}else if(ruleType.equals(BusinessConstants.RULE_TYPE_PNR)){
			ruleType = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_PNR +"%";
		}
	    ProductRuleRefDataRequest productRuleRefDataRequest = (ProductRuleRefDataRequest) this.
	    												getServiceRequest(ServiceManager.PRODUCT_RULE_REF_DATA);
	    productRuleRefDataRequest.setAction(ProductRuleRefDataRequest.PRODUCT_RULES_RETRIEVE);
	    productRuleRefDataRequest.setRuleType(ruleType);
	    productRuleRefDataRequest.setPageno(this.pageno);
	    ProductRuleRefDataResponse productRuleRefDataResponse = (ProductRuleRefDataResponse) this.executeService(productRuleRefDataRequest);
	    List ruleList =productRuleRefDataResponse.getRuleList();
	    setRuleValues(ruleList);
		setRuleList(ruleList);
		pagenumber = productRuleRefDataResponse.getPageNum();
		setDisableStatus(productRuleRefDataResponse.getRuleCount(),pagenumber);
	//	setPageRecordStatus(pagenumber)
		this.productType=super.getProductTypeFromSession();
		if(null == productRuleRefDataResponse.getRuleList() || 0 == productRuleRefDataResponse.getRuleList().size()){
			this.renderFlag = false;
		}else{
			this.renderFlag = true;
		}
        addAllMessagesToRequest(this.validationMessages);       
	}
//  --------------------------------- getters/setters -----------------------
	public String loadProductRulesFromView() {
		if(this.exclusionRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION,this.pageno);
		}else if(this.denialRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL,this.pageno);
		}else if(this.pnrRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_PNR,this.pageno);
		}else if(this.umRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_UM,this.pageno);
		}
		return WebConstants.EMPTY_STRING;		
	}

	/**
	 * Returns the loadProductRules
	 * @return String loadProductRules.
	 */
	//WAS 7.0 Changes - Binding variable loadProductRules modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
	public HtmlInputHidden getLoadProductRules() {
		if(!(this.exclusionRuleSelected | this.denialRuleSelected | this.pnrRuleSelected | this.umRuleSelected))
    	{
			this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION,this.pageno);
			this.exclusionRuleSelected = true;
    	}
		 loadProductRules.setValue(WebConstants.EMPTY_STRING);
		//return WebConstants.EMPTY_STRING;
		 return loadProductRules;
	}
	/**
	 * Sets the loadProductRules
	 * @param loadProductRules.
	 */
	public void setLoadProductRules(HtmlInputHidden loadProductRules) {
		this.loadProductRules = loadProductRules;
	}
	/**
	 * Returns the ruleTypeListForCombo
	 * @return List ruleTypeListForCombo.
	 */
	public List getRuleTypeListForCombo() {
/*
 *	 as per the new requirement we have to hardcode the value rather to retrieve from rule_type table
 *
 		ruleTypeListForCombo = new ArrayList();
	    List ruleTypeList = new ArrayList();
	    ProductRuleRefDataRequest productRuleRefDataRequest = (ProductRuleRefDataRequest) this.
	    												getServiceRequest(ServiceManager.PRODUCT_RULE_REF_DATA);
	    productRuleRefDataRequest.setAction(ProductRuleRefDataRequest.RULE_TYPE);
	    ProductRuleRefDataResponse productRuleRefDataResponse = (ProductRuleRefDataResponse) this.executeService(productRuleRefDataRequest);
	    ruleTypeList = productRuleRefDataResponse.getRuleList();
	    
	    Iterator ruleTypeListIterator = ruleTypeList.iterator();
	    ruleTypeListForCombo.add(new SelectItem(WebConstants.EMPTY_STRING));
	    while(ruleTypeListIterator.hasNext()){
	    	ProductRuleBO ruleTypeResult = (ProductRuleBO)ruleTypeListIterator.next();
//	        ruleTypeListForCombo.add(new SelectItem(ruleTypeResult.getRuleCode(),ruleTypeResult.getRuleDescription()));
	        ruleTypeListForCombo.add(new SelectItem(ruleTypeResult.getRuleDescription(),ruleTypeResult.getRuleDescription()));
	    }		
*/	
 		ruleTypeListForCombo = new ArrayList(5);
        ruleTypeListForCombo.add(new SelectItem(WebConstants.EMPTY_STRING));
        ruleTypeListForCombo.add(new SelectItem(BusinessConstants.RULE_TYPE_DENIAL,BusinessConstants.RULE_TYPE_DENIAL));
        ruleTypeListForCombo.add(new SelectItem(BusinessConstants.RULE_TYPE_EXCLUSION,BusinessConstants.RULE_TYPE_EXCLUSION));
        ruleTypeListForCombo.add(new SelectItem(BusinessConstants.RULE_TYPE_UM,BusinessConstants.RULE_TYPE_UM));
        ruleTypeListForCombo.add(new SelectItem(BusinessConstants.RULE_TYPE_PNR,BusinessConstants.RULE_TYPE_PNR));
		return ruleTypeListForCombo;
	}
	/**
	 * Sets the ruleTypeListForCombo
	 * @param ruleTypeListForCombo.
	 */
	public void setRuleTypeListForCombo(List ruleTypeListForCombo) {
		this.ruleTypeListForCombo = ruleTypeListForCombo;
	}
	/**
	 * Returns the ruleIDList
	 * @return List ruleIDList.
	 */
	public List getRuleIDList() {
		ruleIDList = null;
		 final String RULE_TYPE_PARAM = "ruleType";
		String ruleTypeCode = WebConstants.EMPTY_STRING; 
	    String ruleType = (String)getRequest().getParameter(RULE_TYPE_PARAM);
	    if(null==ruleType||WebConstants.EMPTY_STRING.equals(ruleType)){
	    	return ruleIDList;
	    }else if(ruleType.equals(BusinessConstants.RULE_TYPE_DENIAL)){
	    	ruleTypeCode = BusinessConstants.RULE_TYPE_DENIAL_KEY;
	    }else if(ruleType.equals(BusinessConstants.RULE_TYPE_EXCLUSION)){
	    	ruleTypeCode = BusinessConstants.RULE_TYPE_EXCLUSION_KEY;
	    }else if(ruleType.equals(BusinessConstants.RULE_TYPE_UM)){
	    	ruleTypeCode = BusinessConstants.RULE_TYPE_UM_KEY;
	    }else if(ruleType.equals(BusinessConstants.RULE_TYPE_PNR)){
	    	ruleTypeCode = BusinessConstants.RULE_TYPE_PNR_KEY;
	    }
	    ProductRuleRefDataRequest productRuleRefDataRequest = (ProductRuleRefDataRequest) this.
	    												getServiceRequest(ServiceManager.PRODUCT_RULE_REF_DATA);
	    productRuleRefDataRequest.setRuleType(ruleTypeCode);
	    productRuleRefDataRequest.setAction(ProductRuleRefDataRequest.RULE_ID);
	    ProductRuleRefDataResponse productRuleRefDataResponse = (ProductRuleRefDataResponse) this.executeService(productRuleRefDataRequest);
	    ruleIDList = productRuleRefDataResponse.getRuleList();
		return ruleIDList;
	}
	/**
	 * Sets the ruleIDList
	 * @param ruleIDList.
	 */
	public void setRuleIDList(List ruleIDList) {
		this.ruleIDList = ruleIDList;
	}
    /**
     * @return Returns the state.
     */
    public String getState() {
        return getStateFromSession();
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
        return super.getStatusFromSession();
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

        return getVersionFromSession();
    }
    /**
     * @param version
     *            The version to set.
     */
    public void setVersion(int version) {
        this.version = version;
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
	 * @param productType The productType to set.
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
	 * @param validationMessages The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}
	/**
	 * Returns the providerArrangement
	 * @return String providerArrangement.
	 */
	public String getProviderArrangement() {
		return providerArrangement;
	}
	/**
	 * Sets the providerArrangement
	 * @param providerArrangement.
	 */
	public void setProviderArrangement(String providerArrangement) {
		this.providerArrangement = providerArrangement;
	}
	/**
	 * Returns the productRuleSysID
	 * @return String productRuleSysID.
	 */
	public String getProductRuleSysID() {
		return productRuleSysID;
	}
	/**
	 * Sets the productRuleSysID
	 * @param productRuleSysID.
	 */
	public void setProductRuleSysID(String productRuleSysID) {
		this.productRuleSysID = productRuleSysID;
	}
	/**
	 * Returns the requiredProviderArrangement
	 * @return boolean requiredProviderArrangement.
	 */
	public boolean isRequiredProviderArrangement() {
		return requiredProviderArrangement;
	}
	/**
	 * Sets the requiredProviderArrangement
	 * @param requiredProviderArrangement.
	 */
	public void setRequiredProviderArrangement(
			boolean requiredProviderArrangement) {
		this.requiredProviderArrangement = requiredProviderArrangement;
	}
	/**
	 * Returns the requiredRuleID
	 * @return boolean requiredRuleID.
	 */
	public boolean isRequiredRuleID() {
		return requiredRuleID;
	}
	/**
	 * Sets the requiredRuleID
	 * @param requiredRuleID.
	 */
	public void setRequiredRuleID(boolean requiredRuleID) {
		this.requiredRuleID = requiredRuleID;
	}
	/**
	 * Returns the requiredRuleType
	 * @return boolean requiredRuleType.
	 */
	public boolean isRequiredRuleType() {
		return requiredRuleType;
	}
	/**
	 * Sets the requiredRuleType
	 * @param requiredRuleType.
	 */
	public void setRequiredRuleType(boolean requiredRuleType) {
		this.requiredRuleType = requiredRuleType;
	}
	/**
	 * Returns the ruleID
	 * @return String ruleID.
	 */
	public String getRuleID() {
		return ruleID;
	}
	/**
	 * Sets the ruleID
	 * @param ruleID.
	 */
	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
	/**
	 * Returns the ruleList
	 * @return List ruleList.
	 */
	public List getRuleList() {
		if((this.exclusionRuleSelected || this.umRuleSelected || this.pnrRuleSelected || this.denialRuleSelected )&& null == this.ruleList){
			if(this.exclusionRuleSelected)
				this.loadExclusionRule();
			else if(this.umRuleSelected)
				this.loadUMRule();
			else if(this.denialRuleSelected)
				this.loadDenialRule();
			else if(this.pnrRuleSelected)
				this.loadPNRRule();
		}
		return ruleList;
	}
	/**
	 * Sets the ruleList
	 * @param ruleList.
	 */
	public void setRuleList(List ruleList) {
		this.ruleList = ruleList;
	}
	/**
	 * Returns the ruleType
	 * @return String ruleType.
	 */
	public String getRuleType() {
		return ruleType;
	}
	/**
	 * Sets the ruleType
	 * @param ruleType.
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
    /**
     * Returns the providerArrangementCodeList
     * @return List providerArrangementCodeList.
     */
    public List getProviderArrangementCodeList() {
        return providerArrangementCodeList;
    }
    /**
     * Sets the providerArrangementCodeList
     * @param providerArrangementCodeList.
     */
    public void setProviderArrangementCodeList(List providerArrangementCodeList) {
        this.providerArrangementCodeList = providerArrangementCodeList;
    }
	/**
	 * Returns the ruleCodeList
	 * @return List ruleCodeList.
	 */
	public List getRuleCodeList() {
		return ruleCodeList;
	}
	/**
	 * Sets the ruleCodeList
	 * @param ruleCodeList.
	 */
	public void setRuleCodeList(List ruleCodeList) {
		this.ruleCodeList = ruleCodeList;
	}
	/**
	 * Returns the renderFlag
	 * @return boolean renderFlag.
	 */
	public boolean isRenderFlag() {
		return renderFlag;
	}
	/**
	 * Sets the renderFlag
	 * @param renderFlag.
	 */
	public void setRenderFlag(boolean renderFlag) {
		this.renderFlag = renderFlag;
	}
	/**
	 * Returns the panel
	 * @return HtmlPanelGrid panel.
	 */
	public HtmlPanelGrid getPanel() {
		this.preparePanel(this.getRuleList());
		return panel;
	}
	/**
	 * Sets the panel
	 * @param panel.
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
	}
	/**
	 * Returns the ruleCommentMap
	 * @return Map ruleCommentMap.
	 */
	public Map getRuleCommentMap() {
		return ruleCommentMap;
	}
	/**
	 * Sets the ruleCommentMap
	 * @param ruleCommentMap.
	 */
	public void setRuleCommentMap(Map ruleCommentMap) {
		this.ruleCommentMap = ruleCommentMap;
	}
	/**
	 * @return Returns the printValue.
	 */
	public String getPrintValue() {
		String requestForPrint = getRequest().getParameter("printValueForDenial");
        if(null != requestForPrint && !requestForPrint.equals(WebConstants.EMPTY_STRING)){
            printValue = requestForPrint;
        }else{
            printValue = WebConstants.EMPTY_STRING;
        }
        return printValue;
	}
	/**
	 * @param printValue The printValue to set.
	 */
	public void setPrintValue(String printValue) {
		this.printValue = printValue;
	}
	/**
	 * Returns the denialRuleSelected
	 * @return boolean denialRuleSelected.
	 */
	public boolean isDenialRuleSelected() {
		return denialRuleSelected;
	}
	/**
	 * Sets the denialRuleSelected
	 * @param denialRuleSelected.
	 */
	public void setDenialRuleSelected(boolean denialRuleSelected) {
		this.denialRuleSelected = denialRuleSelected;
	}
	/**
	 * Returns the exclusionRuleSelected
	 * @return boolean exclusionRuleSelected.
	 */
	public boolean isExclusionRuleSelected() {
		return exclusionRuleSelected;
	}
	/**
	 * Sets the exclusionRuleSelected
	 * @param exclusionRuleSelected.
	 */
	public void setExclusionRuleSelected(boolean exclusionRuleSelected) {
		this.exclusionRuleSelected = exclusionRuleSelected;
	}
	/**
	 * Returns the pnrRuleSelected
	 * @return boolean pnrRuleSelected.
	 */
	public boolean isPnrRuleSelected() {
		return pnrRuleSelected;
	}
	/**
	 * Sets the pnrRuleSelected
	 * @param pnrRuleSelected.
	 */
	public void setPnrRuleSelected(boolean pnrRuleSelected) {
		this.pnrRuleSelected = pnrRuleSelected;
	}
	/**
	 * Returns the umRuleSelected
	 * @return boolean umRuleSelected.
	 */
	public boolean isUmRuleSelected() {
		return umRuleSelected;
	}
	/**
	 * Sets the umRuleSelected
	 * @param umRuleSelected.
	 */
	public void setUmRuleSelected(boolean umRuleSelected) {
		this.umRuleSelected = umRuleSelected;
	}
	/**
	 * @return Returns the ruleListForView.
	 */
	public List getRuleListForView() {
	   	return ruleListForView;
	}
	/**
	 * @param ruleListForView The ruleListForView to set.
	 */
	public void setRuleListForView(List ruleListForView) {
		this.ruleListForView = ruleListForView;
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
	 * @return Returns the selectedRuleType.
	 */
	public String getSelectedRuleType() {
		return selectedRuleType;
	}
	/**
	 * @param selectedRuleType The selectedRuleType to set.
	 */
	public void setSelectedRuleType(String selectedRuleType) {
		this.selectedRuleType = selectedRuleType;
	}
	
	/**
	 * @return Returns the viewRuleListRender.
	 */
	public boolean isViewRuleListRender() {
		return viewRuleListRender;
	}
	/**
	 * @param viewRuleListRender The viewRuleListRender to set.
	 */
	public void setViewRuleListRender(boolean viewRuleListRender) {
		this.viewRuleListRender = viewRuleListRender;
	}
	
	/**
	 * Returns the rulesValueCheck
	 * @return boolean rulesValueCheck.
	 */
	public boolean isRulesValueCheck() {
		return rulesValueCheck;
	}
	/**
	 * Sets the rulesValueCheck
	 * @param rulesValueCheck.
	 */
	public void setRulesValueCheck(boolean rulesValueCheck) {
		this.rulesValueCheck = rulesValueCheck;
	}
	/**
	 * @return Returns the hiddenList.
	 */
	public String getHiddenList() {
			loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION,this.pageno);
			this.exclusionRuleList = this.ruleList;
			loadProductRules(BusinessConstants.RULE_TYPE_UM,this.pageno);
			this.umRuleList = this.ruleList;
			loadProductRules(BusinessConstants.RULE_TYPE_DENIAL,this.pageno);
			this.denialRuleList = this.ruleList;
			loadProductRules(BusinessConstants.RULE_TYPE_PNR,this.pageno);
			this.pnrlRuleList = this.ruleList;
		return hiddenList;
	}
	/**
	 * @param hiddenList The hiddenList to set.
	 */
	public void setHiddenList(String hiddenList) {
		this.hiddenList = hiddenList;
	}
	/**
	 * @return hiddenInit
	 * 
	 * Returns the hiddenInit.
	 */
	public String getHiddenInit() {
		this.getLoadProductRules();
		return hiddenInit;
	}
	/**
	 * @param hiddenInit
	 * 
	 * Sets the hiddenInit.
	 */
	public void setHiddenInit(String hiddenInit) {
		this.hiddenInit = hiddenInit;
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
	 * @return Returns the denialRuleList.
	 */
	public List getDenialRuleList() {
		return denialRuleList;
	}
	/**
	 * @param denialRuleList The denialRuleList to set.
	 */
	public void setDenialRuleList(List denialRuleList) {
		this.denialRuleList = denialRuleList;
	}
	/**
	 * @return Returns the exclusionRuleList.
	 */
	public List getExclusionRuleList() {
		return exclusionRuleList;
	}
	/**
	 * @param exclusionRuleList The exclusionRuleList to set.
	 */
	public void setExclusionRuleList(List exclusionRuleList) {
		this.exclusionRuleList = exclusionRuleList;
	}
	/**
	 * @return Returns the pnrlRuleList.
	 */
	public List getPnrlRuleList() {
		return pnrlRuleList;
	}
	/**
	 * @param pnrlRuleList The pnrlRuleList to set.
	 */
	public void setPnrlRuleList(List pnrlRuleList) {
		this.pnrlRuleList = pnrlRuleList;
	}
	/**
	 * @return Returns the umRuleList.
	 */
	public List getUmRuleList() {
		return umRuleList;
	}
	/**
	 * @param umRuleList The umRuleList to set.
	 */
	public void setUmRuleList(List umRuleList) {
		this.umRuleList = umRuleList;
	}
	/**
	 * @return Returns the disabledForNextButton.
	 */
	public boolean isDisabledForNextButton() {
		return disabledForNextButton;
	}
	/**
	 * @param disabledForNextButton The disabledForNextButton to set.
	 */
	public void setDisabledForNextButton(boolean disabledForNextButton) {
		this.disabledForNextButton = disabledForNextButton;
	}
	/**
	 * @return Returns the disabledForPrevButton.
	 */
	public boolean isDisabledForPrevButton() {
		return disabledForPrevButton;
	}
	/**
	 * @param disabledForPrevButton The disabledForPrevButton to set.
	 */
	public void setDisabledForPrevButton(boolean disabledForPrevButton) {
		this.disabledForPrevButton = disabledForPrevButton;
	}
	/**
	 * @return Returns the pageno.
	 */
	public int getPageno() {
		return pageno;
	}
	/**
	 * @param pageno The pageno to set.
	 */
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	/*
	 * This method will call while clicking on next button of product rule page
	 * the page number will be incrimented by 1 on each click 
	 * 
	 */
		
	public String nextReuleRecords(){
		
		this.pageno =pageno+1; 
		if(this.isExclusionRuleSelected()){
			this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION,this.pageno);
			
		}else if(this.umRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_UM,this.pageno);
			
		}else if(this.denialRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL,this.pageno);
		}
		else if(this.pnrRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_PNR,this.pageno);
		}
		return this.displayDenialAndExclusionTab();
	}
	/*
	 * This method will call while clicking on previous button of product rule page 
	 * the page number will be decreased  by 1 on each click 
	 * 
	 * 
	 */
	public String prevReuleRecords(){
		
		this.pageno =pageno-1; 
		Logger.logDebug("pageno"+pageno);
		
		if(this.isExclusionRuleSelected()){
			this.loadProductRules(BusinessConstants.RULE_TYPE_EXCLUSION,this.pageno);
			
		}else if(this.umRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_UM,this.pageno);
			
		}else if(this.denialRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_DENIAL,this.pageno);
		}
		else if(this.pnrRuleSelected){
			this.loadProductRules(BusinessConstants.RULE_TYPE_PNR,this.pageno);
		}
		return this.displayDenialAndExclusionTab();
	}
	/*
	 * 
	 * This method is for disabling and enabling previous and next button according to the current page
	 * if the page number is 1 then the prev button will be disabled 
	 * calculate total pagecoutn
	 * if the totalpagecount and pagenum are equal then the next button will be disabled 
	 * 
	 */
	private void setDisableStatus(int ruleCount,int pageNum){
		int totalPageCount =1 ;
		if(pageNum==1){
			disabledForPrevButton = true;
		}else{
			disabledForPrevButton = false;
		}
		
		if(ruleCount>0){
			double tempCount = (double)ruleCount;
			double count = Math.ceil(tempCount/WebConstants.REORDS_PER_PAGE);
			totalPageCount = (int)count;
			if(pageNum == totalPageCount){
				disabledForNextButton = true;
			}else{
				disabledForNextButton = false;
			}
		}else{
			disabledForNextButton = true;
			totalPageCount=1;
		}
		
		setPageRecordStatus(pageNum,totalPageCount);
	}
	/**
	 * @return Returns the pageStatus.
	 */
	public String getPageStatus() {
		return pageStatus;
	}
	/**
	 * @param pageStatus The pageStatus to set.
	 */
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}
	/*This method is for showing the current page number of the product rule records 
	 * 
	 * 
	 * 
	 */
	private void setPageRecordStatus(int pageNum, int totalPageCount){
		
		Integer pageno = new Integer(pageNum);
		Integer totalPage = new Integer(totalPageCount);
		
		this.pageStatus = "Page :"+pageno.toString()+"/" + totalPage.toString();
	}
	/*This method is for preparing a map for upadting the rule count 
	 * the key of the map is product rule id and the value will be user entered data 
	 * 
	 * @ param ruleIdvalueTilda
	 */
	private void prepareMapForUpdate(String ruleIdvalueTilda){
		String[] ruleValuePair = ruleIdvalueTilda.split("~");
		int k = ruleValuePair.length;
		if (k>0){
			for (int i=0;i<k;i++){
				if(ruleValuePair[i]!=null&&!("").equals(ruleValuePair[i])){
					String [] ruleValue = ruleValuePair[i].split("-");
					if(ruleValue.length==1){
						if(ruleCommentMap.containsKey(ruleValue[0])){
							ruleCommentMap.remove(ruleValue[0]);
							ruleCommentMap.put(ruleValue [0],"");
						}else{
							this.ruleCommentMap.put(ruleValue [0],"");
						}
					}else{
					if(ruleCommentMap.containsKey(ruleValue[0])){
						ruleCommentMap.remove(ruleValue[0]);
						ruleCommentMap.put(ruleValue [0],ruleValue[1]);
					}else{
						this.ruleCommentMap.put(ruleValue [0],ruleValue[1]);
					}
					}
				}
			}
		}
		this.changedRuleIds = null;
	}
	
	/**
	 * @return Returns the ruleIdsForDelete.
	 */
	public String getRuleIdsForDelete() {
		return ruleIdsForDelete;
	}
	/**
	 * @param ruleIdsForDelete The ruleIdsForDelete to set.
	 */
	public void setRuleIdsForDelete(String ruleIdsForDelete) {
		this.ruleIdsForDelete = ruleIdsForDelete;
	}
	/**
	 * @return Returns the genruleIdsForDelete.
	 */
	public String getGenruleIdsForDelete() {
		return genruleIdsForDelete;
	}
	/**
	 * @param genruleIdsForDelete The genruleIdsForDelete to set.
	 */
	public void setGenruleIdsForDelete(String genruleIdsForDelete) {
		this.genruleIdsForDelete = genruleIdsForDelete;
	}
	/*
	 * This method for separatinf productrule id and generated rule ids from tilda separated strings
	 * @ param tildaRulevalePair
	 * @ param array of rule ids.
	 */
	public String[] getRuleIds(String tildaRulevalePair){
		String[] rules =null;
		if(null!=tildaRulevalePair && !("").equals(tildaRulevalePair)){
		String[] ruleValuePair = tildaRulevalePair.split("~");
		int k = ruleValuePair.length;
		Map rulemap = new HashMap();
		if (k>0){
			for (int i=0;i<k;i++){
				if(ruleValuePair[i]!=null&&!("").equals(ruleValuePair[i])){
					String [] ruleValue = ruleValuePair[i].split("-");
					if(rulemap.containsKey(ruleValue[0])){
						rulemap.remove(ruleValue[0]);
					}else{
						rulemap.put(ruleValue [0],ruleValue[1]);
					}
					
				}
			}
		}
		if(rulemap.size()>0){
			Set keyMap =(Set)rulemap.keySet();
			Object[]rulesobj = (Object[])keyMap.toArray();
			rules=new String[rulesobj.length];
			for(int i=0;i<rulesobj.length;i++){
				rules[i]= (String)rulesobj[i].toString(); 
		}
			
		}
		}
		return rules;
	}
	
	/***
	 * 
	 * Method to get generated Rule ID'S.
	 * @param tildaRulevalePair
	 * @return
	 */ 
	
	public List getGenRuleIds(String tildaRulevalePair) {
		String[] rules = null;
		List genRuleValue = new ArrayList();
		
		if (null != tildaRulevalePair && !("").equals(tildaRulevalePair)) {
			String[] ruleValuePair = tildaRulevalePair.split("~");
			int k = ruleValuePair.length;
			String[] ruleValue = null;
			
			Map rulemap = new HashMap();
			if (k > 0) {
				for (int i = 1; i < k; i++) {
					if (ruleValuePair[i] != null
							&& !("").equals(ruleValuePair[i])) {
						ruleValue = ruleValuePair[i].split("-");
						genRuleValue.add(ruleValue[0]);

					}

				}

			}

		}

		return genRuleValue;

	}
	/*
	 * this methods for setting values to rule list for preparing panel 
	 * 
	 * this will bind the rule values while navigating .
	 * 
	 * this method will bind the check box values also .
	 * 
	 */
	private List setRuleValues(List ruleList){
		List finalRuleList = new ArrayList();
		if(null!=this.changedRuleIds &&!("").equals(this.changedRuleIds)&& null!=ruleList && ruleList.size()>0 ){
		String[] ruleValuePair = this.changedRuleIds.split("~");
		int k = ruleValuePair.length;
		if (k>0){
			for (int i=0;i<k;i++){
				if(ruleValuePair[i]!=null&&!("").equals(ruleValuePair[i])){
					String [] ruleValue = ruleValuePair[i].split("-");
					if(ruleValue.length==1){
						for(int j=0;j<ruleList.size();j++){
							ProductRuleAssociationBO productRuleAssociationBO = (ProductRuleAssociationBO) ruleList.get(j);
							
							if((new Integer(productRuleAssociationBO.getProductRuleSysID()).toString()).equals(ruleValue[0])){
								((ProductRuleAssociationBO)ruleList.get(j)).setRuleComment("");
							}
						}
					}else{
						for(int j=0;j<ruleList.size();j++){
							ProductRuleAssociationBO productRuleAssociationBO = (ProductRuleAssociationBO) ruleList.get(j);
							
							if((new Integer(productRuleAssociationBO.getProductRuleSysID()).toString()).equals(ruleValue[0])){
								((ProductRuleAssociationBO)ruleList.get(j)).setRuleComment(ruleValue[1]);
							}
						}
					}
				}
			}
		}
		}
		if(null!=this.ruleIdsForDelete && !("").equals(this.ruleIdsForDelete) ){
			
			String[] ruleValuePair = this.ruleIdsForDelete.split("~");
			int k = ruleValuePair.length;
			if (k>0){
				for (int i=0;i<k;i++){
					if(ruleValuePair[i]!=null&&!("").equals(ruleValuePair[i])){
						String [] ruleValue = ruleValuePair[i].split("-");
							for(int j=0;j<ruleList.size();j++){
								ProductRuleAssociationBO productRuleAssociationBO = (ProductRuleAssociationBO) ruleList.get(j);
								
								if((new Integer(productRuleAssociationBO.getProductRuleSysID()).toString()).equals(ruleValue[0])){
									if(ruleValue[1].equals("Y")){
									((ProductRuleAssociationBO)ruleList.get(j)).setDeleteFlag(true);
									}else{
										((ProductRuleAssociationBO)ruleList.get(j)).setDeleteFlag(false);
									}
								}
							}
						
					}
				}
			}
			
		}
		return ruleList;
	}
	/**
	 * @return Returns the productSysId.
	 */
	public int getProductSysId() {
		return productSysId;
	}
	/**
	 * @param productSysId The productSysId to set.
	 */
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	}
	/**
	 * @return Returns the productName.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName The productName to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
