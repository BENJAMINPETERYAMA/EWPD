/*
 * ProductComponentGeneralInfoBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.product.request.RetrieveProductBenefitComponentRequest;
import com.wellpoint.wpd.common.product.request.SaveProductComponentRuleInformationRequest;
import com.wellpoint.wpd.common.product.response.ProductBenefitComponentResponse;
import com.wellpoint.wpd.common.product.response.SaveProductComponentRuleInformationResponse;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeStandardBenefits;
import com.wellpoint.wpd.common.product.vo.ProductSearchCriteriaVO;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class ProductComponentGeneralInfoBackingBean extends ProductBackingBean {
    private String businessDomain;

    private String effectiveDate;

    private String expiryDate;

    private String name;

    private String description;

    private String createdBy;

    private Date createdDate;

	
    private String updatedBy;

    private Date lastUpdatedDate;

    private String lineOfBusiness;

    private String businessEntity;

    private String businessGroup;
    
    private String dummyVariable;
    
    private String valueForPrint;
 
    private String componentType;
    
    private String mandateType;
    
    private String selectedStateId;   
    
    private String ruleId;
    private String ruleType;
    
   // private Map benefitHideUnhideFlagMap = new HashMap();
    
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
    private String stateDesc;
    
    private String productType;
    
    private String printValue;
    
    private String printBreadCrumbText;
    
    private String benefitName;
    
    private List benefitDetailsList;
    
    private String benefitComponentId;
    
    private boolean benefitHidden;
    
    private HtmlPanelGrid displayPanel;
    
    private HtmlPanelGrid panel;
    
    private HtmlPanelGrid benefitHeaderViewPanel = new HtmlPanelGrid();
    
    private Map hiddenValBenefitVisible = new HashMap();
    
    private Map hiddenValBenefitId = new HashMap();
    
    private Map hiddenValBenefitName = new HashMap();
    
    private Map hiddenPrevValBenefitVisible = new HashMap();
    
    private HtmlPanelGrid savePanel;
    
    private boolean showHidden;
    
    private List validationMessages = null;
    
    private boolean errorMessage;
      
    private boolean saveButton = true;
    
    private HtmlPanelGrid printPanel;
    
    private HtmlPanelGrid printHeaderPanel;
    
    private String printProductAssociatedBenefits;
    
    private HtmlInputHidden loadAssociatedBenefits = new HtmlInputHidden();
    
   // private boolean generalBenefitFlag;
    
    private String productTypeForBenefit;
    
    private boolean noFieldSelected;
    
    private String flagStatus;
    
    // added for CR -  version 
    
    private int bnftCmpntversion;
    
    private String panelData = "";
    
    private boolean requiredRule = false;
    
    private String ruleIdNameComb;
    
    private String ruledesc;
    
    private String ruleIdForView;
    //CARS START
    private String marketBusinessUnit;
    //CARS END
    
    private Map benefitHideUnhideFlagMap = new HashMap();
    
	/**
	 * @return Returns the ruleIdNameCombtext.
	 */
	public String getRuleIdNameCombtext() {
		return ruleIdNameCombtext;
	}
	/**
	 * @param ruleIdNameCombtext The ruleIdNameCombtext to set.
	 */
	public void setRuleIdNameCombtext(String ruleIdNameCombtext) {
		this.ruleIdNameCombtext = ruleIdNameCombtext;
	}
    private String ruleIdNameCombtext;
    
    public ProductComponentGeneralInfoBackingBean(){
    	super();
    	if(!super.isViewMode()){
        	this.setBreadCumbTextForEdit();
        }
        else{
        	this.setBreadCumbTextForView();
        }
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
	 * @return Returns the savePanel.
	 */
	public HtmlPanelGrid getSavePanel() {
	
	
		HtmlPanelGrid displayPanel = new HtmlPanelGrid();
		displayPanel.setCellpadding("0");
		displayPanel.setCellspacing("0");
		HtmlCommandButton saveButton = new HtmlCommandButton();
		saveButton.setId("Save");
		saveButton.setStyleClass("wpdbutton");
		saveButton.setValue("Save");
		
		MethodBinding saveMetBind = FacesContext
		.getCurrentInstance()
		.getApplication()
		.createMethodBinding(
				"#{productComponentGeneralInfoBackingBean.saveBenefitDetails}",
				new Class[] {});
		saveButton.setAction(saveMetBind);
		displayPanel.getChildren().add(saveButton);
		return displayPanel;
	}
	/**
	 * @param savePanel The savePanel to set.
	 */
	public void setSavePanel(HtmlPanelGrid savePanel) {
		this.savePanel = savePanel;
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
	 * @return Returns the benefitHeaderViewPanel.
	 */
	public HtmlPanelGrid getBenefitHeaderViewPanel() {
		
	  this.setProductTypeForBenefit(getProductSessionObject().getProductKeyObject().getProductType());
		
		HtmlPanelGrid viewPanel = new HtmlPanelGrid();
		viewPanel.setWidth("100%");
		
		if(!this.productTypeForBenefit.equals("MANDATE")){
				viewPanel.setColumns(2);			
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
		
		if(!this.productTypeForBenefit.equals("MANDATE")){
				viewPanel.getChildren().add(outputLbl);			
		}
	
		return viewPanel;
	}
	
	 /**
     * This method retrieves the benefit information associated to a benefit component
     * clicked from the tree
     * 
     * @return String
     */
    public String loadAssociatedBenefits(){
    	
    
    	 List benefitDetailsList = null;
    	 int productId= super.getProductKeyFromSession();
    	 RetrieveProductBenefitComponentRequest retrieveProductBenefitComponentRequest = (RetrieveProductBenefitComponentRequest)
			this.getServiceRequest(ServiceManager.PRODUCT_BENEFIT_COMPONENT);
    	
    	 if(showHidden){
    	 	// Setting  action to retrieve all the hidden and unhidden benefits
    	 	retrieveProductBenefitComponentRequest.setAction(RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_RETRIEVE_ALL_DETAILS);
    	 }
    	 else
    	 	//Setting  action to retrieve only visible benefits
    	 	retrieveProductBenefitComponentRequest.setAction(RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_RETRIEVE);
    	
    	// Accessing the value from the session and setting to request.
    	String key = (String)getSession().getAttribute("BNFT_CMPNT_KEY");
    	if(null !=key){
    		retrieveProductBenefitComponentRequest.setBenefitComponentId(Integer.parseInt((key)));
    	}
    	else
    		retrieveProductBenefitComponentRequest.setBenefitComponentId(getProductSessionObject().getBenefitComponentId());
    	
    	//retrieveProductBenefitComponentRequest.setBenefitComponentId(Integer.parseInt((key)));
    	retrieveProductBenefitComponentRequest.setProductId(productId);
    	
    	if(null != getRequest().getSession().getAttribute("productSearchCriteriaVO")){
    		ProductSearchCriteriaVO productSearchCriteriaVO = 
    			(ProductSearchCriteriaVO)getRequest().getSession().getAttribute("productSearchCriteriaVO");
		}
    	
    	//	Executes the service and fetches the response
    	ProductBenefitComponentResponse productBenefitComponentResponse = null;    
    	productBenefitComponentResponse =(ProductBenefitComponentResponse)this.executeService(retrieveProductBenefitComponentRequest);
    	
        //Extracts the benefit details from the response
        if(productBenefitComponentResponse.getBenefitList()!=null){
        	benefitDetailsList	 = productBenefitComponentResponse.getBenefitList();
        	
        	//Setting the boolean value of checkbox from the retrieved String value of the hidden flag.
        	for(int i=0;i<benefitDetailsList.size();i++){
        		ProductTreeStandardBenefits productTreeStandardBenefits = (ProductTreeStandardBenefits)benefitDetailsList.get(i);
        		if(productTreeStandardBenefits.getBenefitHideFlag().equals("T"))
        			productTreeStandardBenefits.setBenefitVisibilityStatus(true);
        		else
        			productTreeStandardBenefits.setBenefitVisibilityStatus(false);
        	}
            this.setBenefitDetailsList(benefitDetailsList);
        }
        
    	return "productAssociatedBenefits";
    	
    }
    
    
    /**
     * This method retrieves the benefit information associated to a benefit component
     * clicked from the tree
     * 
     * @return String
     */
    public String viewProductAssociatedBenefits(){
    	
    	 List benefitDetailsList = null;
    	
    	 int productId= super.getProductKeyFromSession();
    	 RetrieveProductBenefitComponentRequest retrieveProductBenefitComponentRequest = (RetrieveProductBenefitComponentRequest)
			this.getServiceRequest(ServiceManager.PRODUCT_BENEFIT_COMPONENT);
    	
    	
    	 	//Setting  action to retrieve only visible benefits
    	 	retrieveProductBenefitComponentRequest.setAction(RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_RETRIEVE);
    	
    	// Accessing the value from the session and setting to request.
    	String key = (String)getSession().getAttribute("BNFT_CMPNT_KEY");
    	retrieveProductBenefitComponentRequest.setBenefitComponentId(Integer.parseInt((key)));
    	retrieveProductBenefitComponentRequest.setProductId(productId);
    	
    	if(null != getRequest().getSession().getAttribute("productSearchCriteriaVO")){
    		ProductSearchCriteriaVO productSearchCriteriaVO = 
    			(ProductSearchCriteriaVO)getRequest().getSession().getAttribute("productSearchCriteriaVO");
		}
    	
    	//	Executes the service and fetches the response
    	ProductBenefitComponentResponse productBenefitComponentResponse = null;    
    	productBenefitComponentResponse =(ProductBenefitComponentResponse)this.executeService(retrieveProductBenefitComponentRequest);
    	
        //Extracts the benefit details from the response
        if(productBenefitComponentResponse.getBenefitList()!=null){
        	benefitDetailsList	 = productBenefitComponentResponse.getBenefitList();
        	
        	//Setting the boolean value of checkbox from the retrieved String value of the hidden flag.
        	for(int i=0;i<benefitDetailsList.size();i++){
        		ProductTreeStandardBenefits productTreeStandardBenefits = (ProductTreeStandardBenefits)benefitDetailsList.get(i);
        		if(productTreeStandardBenefits.getBenefitHideFlag().equals("T"))
        			productTreeStandardBenefits.setBenefitVisibilityStatus(true);
        		else
        			productTreeStandardBenefits.setBenefitVisibilityStatus(false);
        	}
            this.setBenefitDetailsList(benefitDetailsList);
        }
        
    	return "viewProductAssociatedBenefits";
    	
    }
    
    
	/**
	 * @return printProductAssociatedBenefits.
	 */
	public String getPrintProductAssociatedBenefits() {
		 List benefitDetailsList = null;
	    	
	    	 int productId= super.getProductKeyFromSession();
	    	 RetrieveProductBenefitComponentRequest retrieveProductBenefitComponentRequest = (RetrieveProductBenefitComponentRequest)
				this.getServiceRequest(ServiceManager.PRODUCT_BENEFIT_COMPONENT);
	    	
	    	
	    	 	//Setting  action to retrieve only visible benefits
	    	 	retrieveProductBenefitComponentRequest.setAction(RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_RETRIEVE);
	    	
	    	// Accessing the value from the session and setting to request.
	    	String key = (String)getSession().getAttribute("BNFT_CMPNT_KEY");
	    	retrieveProductBenefitComponentRequest.setBenefitComponentId(Integer.parseInt((key)));
	    	retrieveProductBenefitComponentRequest.setProductId(productId);
	    	
	    	if(null != getRequest().getSession().getAttribute("productSearchCriteriaVO")){
	    		ProductSearchCriteriaVO productSearchCriteriaVO = 
	    			(ProductSearchCriteriaVO)getRequest().getSession().getAttribute("productSearchCriteriaVO");
			}
	    	
	    	//	Executes the service and fetches the response
	    	ProductBenefitComponentResponse productBenefitComponentResponse = null;    
	    	productBenefitComponentResponse =(ProductBenefitComponentResponse)this.executeService(retrieveProductBenefitComponentRequest);
	    	
	        //Extracts the benefit details from the response
	        if(productBenefitComponentResponse.getBenefitList()!=null){
	        	benefitDetailsList	 = productBenefitComponentResponse.getBenefitList();
	        	
	        	//Setting the boolean value of checkbox from the retrieved String value of the hidden flag.
	        	for(int i=0;i<benefitDetailsList.size();i++){
	        		ProductTreeStandardBenefits productTreeStandardBenefits = (ProductTreeStandardBenefits)benefitDetailsList.get(i);
	        		if(productTreeStandardBenefits.getBenefitHideFlag().equals("T"))
	        			productTreeStandardBenefits.setBenefitVisibilityStatus(true);
	        		else
	        			productTreeStandardBenefits.setBenefitVisibilityStatus(false);
	        	
	        	}
	            this.setBenefitDetailsList(benefitDetailsList);
	        }
	        
	    	return "printProductAssociatedBenefits";
	    	
		
	}
	/**
	 * @param printProductAssociatedBenefits The printProductAssociatedBenefits to set.
	 */
	public void setPrintProductAssociatedBenefits(
			String printProductAssociatedBenefits) {
		this.printProductAssociatedBenefits = printProductAssociatedBenefits;
	}
   
	
	/**
	 * Action methode to update benefit hierarchy 
	 * @return
	 */
	public String saveBenefitDetails(){
		
		getRequest().setAttribute("RETAIN_Value", "");
		//Getting the values from the Screen.
		List  bnftDetailsList = getUpdatedListFromScreen();
		//Validation to check if user tries to hide all the visible benefits.
		if(this.isErrorMessage()){
			 validationMessages = new ArrayList(1);
	            this.validationMessages.add(new ErrorMessage(
	                    WebConstants.MANDATORY_BENEFIT_VISIBLE_NEEDED));
	            addAllMessagesToRequest(validationMessages);
	            //Setting back the screen values after validation failure.
	            this.setBenefitDetailsList(bnftDetailsList);
			return "productAssociatedBenefits";
		}
		if(this.isNoFieldSelected()){
			this.setBenefitDetailsList(bnftDetailsList);
			return "productAssociatedBenefits";
		}
		//Performing updation after successful validation.
		if(null != bnftDetailsList){
			updateBenefitVisibility(bnftDetailsList);
		}
		this.setBreadCumbTextForEdit();
		return "productAssociatedBenefits";
	}
	
	
	/**
	 * Methode to update the hidden status of benefits
	 * @param productTreeStandardBenefits
	 * @return void
	 */
	private void updateBenefitVisibility(List bnftDetailsList) {
    	 RetrieveProductBenefitComponentRequest retrieveProductBenefitComponentRequest = (RetrieveProductBenefitComponentRequest)
			this.getServiceRequest(ServiceManager.PRODUCT_BENEFIT_COMPONENT);
    	 
    	 boolean showHiddenValue = this.isShowHidden();	
    	     	
    	// Accessing the value from the session and setting it to the request.
    	String key = (String)getSession().getAttribute("BNFT_CMPNT_KEY");
    	int productId= super.getProductKeyFromSession();
    	if(null!=key)
    		retrieveProductBenefitComponentRequest.setBenefitComponentId(Integer.parseInt((key)));
    	else
    		retrieveProductBenefitComponentRequest.setBenefitComponentId(getProductSessionObject().getBenefitComponentId());
    	retrieveProductBenefitComponentRequest.setProductId(productId);
    	retrieveProductBenefitComponentRequest.setAction(RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_DETAILS_UPDATE);
    	retrieveProductBenefitComponentRequest.setBenefitDetailsList(bnftDetailsList);
    	retrieveProductBenefitComponentRequest.setShowHiddenStatus(showHiddenValue);
    	retrieveProductBenefitComponentRequest.setProductType(WebConstants.PROD_TYPE);
    	/** Setting the Map containing Benefit Hide Unhide flag while loading to request object :: eWPD Stabilization 2011**/
    	retrieveProductBenefitComponentRequest.setBenefitHideUnhideFlagMap(getProductSessionObject().getBenefitHideShowFlagMap());
    	/**end :: eWPD Stabilization 2011**/
    	updateAMVForBnft(retrieveProductBenefitComponentRequest);
    	//	Executes the service and fetches the response
    	ProductBenefitComponentResponse productBenefitComponentResponse = null;    
    	productBenefitComponentResponse =(ProductBenefitComponentResponse)this.executeService(retrieveProductBenefitComponentRequest);
        
        //gets the benefit component details from the response
        if(productBenefitComponentResponse.getBenefitList()!=null){
        	benefitDetailsList	 = productBenefitComponentResponse.getBenefitList();
        	 //Code change for product tree rendering optimization
        	super.updateTreeStructure();
        	
        	//Setting the boolean value of checkbox from the retrieved String value of the hidden flag.
        	for(int i=0;i<benefitDetailsList.size();i++){
        		ProductTreeStandardBenefits productTreeStandardBenefits = (ProductTreeStandardBenefits)benefitDetailsList.get(i);
        		if(productTreeStandardBenefits.getBenefitHideFlag().equals("T"))
        			productTreeStandardBenefits.setBenefitVisibilityStatus(true);
        		else
        			productTreeStandardBenefits.setBenefitVisibilityStatus(false);
        	}
        	this.setShowHidden(showHiddenValue);
            this.setBenefitDetailsList(benefitDetailsList);
        }
	}
	
	/**
	 * @param retrieveProductBenefitComponentRequest
	 */
	private void updateAMVForBnft(RetrieveProductBenefitComponentRequest req) {
		if(req != null && req.getBenefitDetailsList() != null && req.getBenefitDetailsList().size() > 0){
			Map map = loadFlagStatus();
			boolean isChanged = false;
			List changedIds = null;
			if(null!=req.getBenefitDetailsList())
			{
				changedIds = new ArrayList(req.getBenefitDetailsList().size());
			for(Iterator i= req.getBenefitDetailsList().iterator();i.hasNext();){
				ProductTreeStandardBenefits benefits = (ProductTreeStandardBenefits)i.next();
				if(!map.get(benefits.getStandardBenefitId()+"").equals(benefits.getBenefitHideFlag())){
					isChanged = true;
					changedIds.add(new Integer(benefits.getStandardBenefitId()));
				}
			}
			}
			if(isChanged){
				req.setChanged(true);
				req.setChangedIds(changedIds);
				req.setBcompName((String) getSession().getAttribute("BENEFIT_COMP_NAME"));
			}
		}
		
	}



	/**
	 *  get the updated List 
	 * @return BenefitHierarchyVO
	 */
	private List getUpdatedListFromScreen(){
		// Iterator itr = benefitMap.keySet().iterator();
		List benefitDetails = new ArrayList();
		int size = 0;
		Set keysForBnftId = null;
		if (null!= this.getHiddenValBenefitId()){
		keysForBnftId = this.getHiddenValBenefitId().keySet();
		//keysForBnftName = this.getHiddenValBenefitName().keySet();
		//keysForBenefitVisibility = this.getHiddenValBenefitVisible().keySet();
		}
        Iterator keyIterBnftId = keysForBnftId.iterator();
       // Iterator keyIterBnftName = keysForBnftName.iterator();
        //Iterator keyIterForBnftVisibility = keysForBenefitVisibility.iterator();
		
		Object bnftIdKey,bnftIdValue,bnftNameValue,bnftVisibilityValue,prevVisibilityValue;
		
		
		
		
		//Variable to check if all the benefits are hidden.
		int checkVar = 0;
		int noFieldSelected = 0;
		while(keyIterBnftId.hasNext()){	
			size++;
			bnftIdKey = keyIterBnftId.next();
			
			
			
			
			bnftIdValue = this.getHiddenValBenefitId().get(bnftIdKey);
			//Getting the values by using the bnftIdKey, which was used in setting in getPanel
			bnftNameValue = this.getHiddenValBenefitName().get(bnftIdKey);
			bnftVisibilityValue = this.getHiddenValBenefitVisible().get(bnftIdKey);
			prevVisibilityValue = this.getHiddenPrevValBenefitVisible().get(bnftIdKey);
		
			ProductTreeStandardBenefits productTreeStandardBenefits = new ProductTreeStandardBenefits();
			//Setting the benefit Component in each BO to perform update Query.
			String key = (String)getSession().getAttribute("BNFT_CMPNT_KEY");
			if(null!=key)
				productTreeStandardBenefits.setBenefitComponentId(Integer.parseInt(key));
			else{
				productTreeStandardBenefits.setBenefitComponentId(getProductSessionObject().getBenefitComponentId());
	    		//String bnftComponentId = ((ProductTreeNode)node).getParent().getParent().getParent().getParent().getParent().getIdentifier();
			}
			
			if(null != bnftIdValue){
				String benefitId = (String)bnftIdValue;
				productTreeStandardBenefits.setStandardBenefitId(Integer.parseInt(benefitId));
			}
			if(null != bnftNameValue){
				String benefitDesc = (String)bnftNameValue;
				productTreeStandardBenefits.setStandardBenefitDesc(benefitDesc);
			}
			Boolean checkBoxValue = null;
			if(null != bnftVisibilityValue){ 
				checkBoxValue = (Boolean)bnftVisibilityValue;
				productTreeStandardBenefits.setBenefitVisibilityStatus(checkBoxValue.booleanValue());
				
				// Validating if all the benefits are hidden.
				if(productTreeStandardBenefits.isBenefitVisibilityStatus()){
					checkVar++;
				}
			}
			
			if(null != prevVisibilityValue && null != checkBoxValue){
				
				if( (prevVisibilityValue.equals("false") && checkBoxValue.booleanValue())
					|| (prevVisibilityValue.equals("true") && !checkBoxValue.booleanValue())){
					
					//Only if there if a change in the checkbox add the cooresponding field for updation.
					benefitDetails.add(productTreeStandardBenefits);
					noFieldSelected++;
		
				}
			}
			
			
			//benefitDetails.add(productTreeStandardBenefits);
		}
		//Perform Checking for All hidden Benefits
		if(size==checkVar){
			//Setting flag for a Validation failure.
			this.setErrorMessage(true);
		}
		if(noFieldSelected == 0){
			this.setNoFieldSelected(true);
		}
		//Returing the List of BO's
		return benefitDetails;
	}
	
	/**
	 * @param benefitHeaderViewPanel The benefitHeaderViewPanel to set.
	 */
	public void setBenefitHeaderViewPanel(HtmlPanelGrid benefitHeaderViewPanel) {
		this.benefitHeaderViewPanel = benefitHeaderViewPanel;
	}
	/**
	 * @return Returns the panel.
	 */
	public HtmlPanelGrid getPanel() {
		
		HtmlPanelGrid panel = new HtmlPanelGrid();
        StringBuffer rows = new StringBuffer();//For setting row style
		ProductTreeStandardBenefits productTreeStandardBenefits = null;		
		String benefitCompName= (String) getSession().getAttribute("BENEFIT_COMP_NAME");
		if(null != this.getBenefitDetailsList()&& this.getBenefitDetailsList().size()>0){
			List benefitDetails = this.getBenefitDetailsList();
			if(null != benefitDetails){
				panel.setWidth("100%");
				if(!this.productTypeForBenefit.equals("MANDATE")){
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
				storeFlagStatus(benefitDetails);
				for(int i = 0; i < benefitDetails.size(); i++){
					
					
					if(benefitDetails.size() > 0){
						productTreeStandardBenefits = (ProductTreeStandardBenefits)benefitDetails.get(i);
					}
					/**
					 * Setting the foreground color based upon the visibility
					 * of the benefit
					 */
					
					  /**Putting benefit hide unhide flag into Map :: eWPD Stabilization 2011 benefit hide/unhide**/
		            Object keyvalue=productTreeStandardBenefits.getStandardBenefitId()+"||"+getProductKeyObject().getProductId();
		            Object valueflag =productTreeStandardBenefits.getBenefitHideFlag();
		            benefitHideUnhideFlagMap.put(keyvalue,valueflag);
		            /**end :: eWPD Stabilization 2011**/
					
					if(productTreeStandardBenefits.isBenefitVisibilityStatus() && i%2!=1)
						rows.append("hiddenFieldLevelDisplay");
					else if(productTreeStandardBenefits.isBenefitVisibilityStatus())
						rows.append("hiddenFieldDisplay");
					else if(i%2==1)
						rows.append("dataTableOddRow");
					else
						rows.append("dataTableEvenRow");
					
					if(benefitDetails.size()!=0)
						rows.append(",");
					
					//Creating an input hidden field for Benefit Id.
					HtmlInputHidden inputHiddenBenefitId = new HtmlInputHidden();
					inputHiddenBenefitId.setId("BnftId"+i);
					inputHiddenBenefitId.setValue(new Integer(productTreeStandardBenefits.getStandardBenefitId()));
					// Creating a value binding for each benefit id and Setting it.
					ValueBinding valBindingForBenefitId = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{productComponentGeneralInfoBackingBean.hiddenValBenefitId[" + productTreeStandardBenefits.getStandardBenefitId()
									+ "]}");		
					inputHiddenBenefitId.setValueBinding("value", valBindingForBenefitId);				
					
					//Creating an output text each for a benefit name.
					HtmlOutputText outputTextBenefitName = new HtmlOutputText();
					outputTextBenefitName.setId("Name" + i);
					outputTextBenefitName.setValue(productTreeStandardBenefits.getStandardBenefitDesc());
					
					
					//Creating the hidden field for storing the Name.
					HtmlInputHidden inputHiddenBenefitName = new HtmlInputHidden();
					inputHiddenBenefitName.setId("BnftName"+i);
					inputHiddenBenefitName.setValue(productTreeStandardBenefits.getStandardBenefitDesc());
					//Creating a value binding and setting it to the benefit name output text.
					ValueBinding valBindingForBenefitName = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{productComponentGeneralInfoBackingBean.hiddenValBenefitName[" + productTreeStandardBenefits.getStandardBenefitId()
							+ "]}");		
					inputHiddenBenefitName.setValueBinding("value", valBindingForBenefitName);	
					
					//Creating a boolean check box each for a benefit.
					HtmlSelectBooleanCheckbox checkbox = new HtmlSelectBooleanCheckbox();
					checkbox.setId("checkbox"+i);
					checkbox.setValue(Boolean.valueOf(productTreeStandardBenefits.isBenefitVisibilityStatus()));
					//Creating and setting Value binding for each checkbox
					checkbox.setValueBinding("value",FacesContext
							.getCurrentInstance()
							.getApplication().createValueBinding(
									"#{productComponentGeneralInfoBackingBean.hiddenValBenefitVisible[" + productTreeStandardBenefits.getStandardBenefitId()
									+ "]}"));
					
					checkbox.setValue( Boolean.valueOf(productTreeStandardBenefits.isBenefitVisibilityStatus()));
					
//					Storing the value so that only updated fields are saved in the save functionality
					HtmlInputHidden hiddenPrevCheckValue = new HtmlInputHidden();
					hiddenPrevCheckValue.setId("dummyCheckboxValue"+i);
					hiddenPrevCheckValue.setValue(Boolean.valueOf(productTreeStandardBenefits.isBenefitVisibilityStatus()));
					//Creating and setting Value binding for each checkbox
					hiddenPrevCheckValue.setValueBinding("value",FacesContext
							.getCurrentInstance()
							.getApplication().createValueBinding(
									"#{productComponentGeneralInfoBackingBean.hiddenPrevValBenefitVisible[" + productTreeStandardBenefits.getStandardBenefitId()
									+ "]}"));
					
					hiddenPrevCheckValue.setValue(Boolean.valueOf(productTreeStandardBenefits.isBenefitVisibilityStatus()));
					
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
					lblName.getChildren().add(hiddenPrevCheckValue);
					lblcheckbox.getChildren().add(checkbox);

					this.getHiddenValBenefitName();
					this.getHiddenValBenefitVisible();
					//Add the label to the panel
					panel.getChildren().add(lblName);
					panel.setRowClasses(rows.toString());
					if(!this.productTypeForBenefit.equals("MANDATE")){
						//if(!this.isGeneralBenefitFlag()){
							panel.getChildren().add(lblcheckbox);
							this.saveButton = true;
						//}
					}
					else
						this.saveButton = false;
					}
				getProductSessionObject().setBenefitHideShowFlagMap(benefitHideUnhideFlagMap);	//eWPD Stabilization 2011
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
	 * @param benefitDetails
	 */
	private void storeFlagStatus(List benefitDetails) {
		if(null != benefitDetails && benefitDetails.size() > 0){
			StringBuffer buffer = new StringBuffer();
			for(Iterator i=benefitDetails.iterator();i.hasNext();){
				ProductTreeStandardBenefits benefits = (ProductTreeStandardBenefits)i.next();
				buffer.append(benefits.getStandardBenefitId());
				buffer.append("~");
				buffer.append(benefits.getBenefitHideFlag());
				if(i.hasNext())
					buffer.append(":");
			}
			flagStatus = buffer.toString();
		}
	}
	
	private Map loadFlagStatus(){
		Map map = new HashMap();
		if(flagStatus != null && !"".equals(flagStatus.trim())){
			String[] array = flagStatus.split(":");
			for(int i=0;i<array.length;i++){
				String[] idValue = array[i].split("~");
				map.put(idValue[0],idValue[1]);
			}
		}
		return map;
	}



	/**
	 * Action methode to update benefit hierarchy 
	 * @return
	 */
	public String updateBenefitHierarchy(){
		
		return "";
	}
	/**
	 * @param panel The panel to set.
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
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
		outputText.setValue("Associated Standard Benefits");
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
	 * @return Returns the benefitComponentId.
	 */
	public String getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(String benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the benefitHidden.
	 */
	public boolean isBenefitHidden() {
		return benefitHidden;
	}
	/**
	 * @param benefitHidden The benefitHidden to set.
	 */
	public void setBenefitHidden(boolean benefitHidden) {
		this.benefitHidden = benefitHidden;
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
     * This method retrieves the benefit component information of the benefit component
     * clicked from the tree
     * 
     * @return String
     */
    public String retrieveBenefitComponent(){ 
        BenefitComponentBO benefitComponent=null;
        
        //sets the required values to the request
        RetrieveProductBenefitComponentRequest retrieveProductBenefitComponentRequest = (RetrieveProductBenefitComponentRequest)
		this.getServiceRequest(ServiceManager.PRODUCT_BENEFIT_COMPONENT);
        retrieveProductBenefitComponentRequest.setAction(RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_COMPONENT_RETRIEVE);
        retrieveProductBenefitComponentRequest.setBenefitComponentId(getBenefitComponentIdFromSession());
        
        //executes the service and fetches the response
        ProductBenefitComponentResponse productBenefitComponentResponse = null;     
        productBenefitComponentResponse =(ProductBenefitComponentResponse)this.executeService(retrieveProductBenefitComponentRequest);
        
        //gets the benefit component details from the response
        if(productBenefitComponentResponse.getBenefitComponentDetails()!=null){
         benefitComponent = productBenefitComponentResponse
                .getBenefitComponentDetails();
        }
        
        //sets values for displaying to the front
        setValuesToBackingBeanForBenefitComponent(benefitComponent);
        
        //fetches the domain details
        DomainDetail domainDetail = productBenefitComponentResponse
                .getDomainDetail();
        
        if (domainDetail != null) {
            this.lineOfBusiness = WPDStringUtil.getTildaString(domainDetail
                    .getLineOfBusiness());
            this.businessEntity = WPDStringUtil.getTildaString(domainDetail
                    .getBusinessEntity());
            this.businessGroup = WPDStringUtil.getTildaString(domainDetail
                    .getBusinessGroup());
            //CARS START
            this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
                    .getMarketBusinessUnit());
            //CARS END
        }
        //checks for view mode or editmode
        if(!isViewMode()){
			return "benefitCreate";
		}else{
			 return "benefitCreateView";  
		}
    }
    

   
    
    
    
    
    /**
     * Sets values to the backing bean for displaying
     * @param benefitComponent
     */
    private void setValuesToBackingBeanForBenefitComponent(
    		BenefitComponentBO benefitComponent) {
    	this.name = benefitComponent.getName();
    	this.description = benefitComponent.getDescription();
    	this.effectiveDate = WPDStringUtil.convertDateToString(benefitComponent
    			.getEffectiveDate());
    	this.expiryDate = WPDStringUtil.convertDateToString(benefitComponent
    			.getExpiryDate());
    	this.createdBy = benefitComponent.getCreatedUser();
    	this.createdDate = benefitComponent.getCreatedTimestamp();
    	this.lastUpdatedDate = benefitComponent.getLastUpdatedTimestamp();
    	this.updatedBy = benefitComponent.getLastUpdatedUser();
    	this.componentType = benefitComponent.getComponentType();
    	this.mandateType = benefitComponent.getMandateType();
    	this.selectedStateId = benefitComponent.getStateId();
    	this.ruleId = benefitComponent.getRuleId();
    	this.setRuleType(benefitComponent.getRuleType());
    	this.ruledesc = benefitComponent.getRuleDesc();
    	
    	if(null!=ruleId && !("").equals(ruleId)&& null!=ruledesc && !("").equals(ruledesc)){
    		this.ruleIdNameComb = ruledesc+'~'+ruleId;
    		this.ruleIdForView = ruleId+'-'+ruledesc;
    	}else{
    		this.ruleIdNameComb = "";
    		this.ruleIdForView = null;
    	}
    	
    	/*List refId= benefitComponent.getRuleList();
    	 List refNam=benefitComponent.getRuleNameList();
    	 for (int i = 0; i < refNam.size(); i++) {
    	 this.setRuleId(refId.get(i).toString()+'-'+refNam.get(i).toString());
    	 // this.setRuleId(refNam.get(i).toString()+'~'+refId.get(i).toString());
    	  }*/
    	
    	//         if (null != benefitComponent.getRuleList() && !(benefitComponent.getRuleList().isEmpty())) {
    	//            List refId = benefitComponent.getRuleList();
    	//            if(null != benefitComponent.getRuleNameList() && !(benefitComponent.getRuleNameList().isEmpty())){
    	//	            List refNam = benefitComponent.getRuleNameList();
    	//	
    	//	            //String reference = convertListtoTilda(refNam, refId);
    	//	            for (int i = 0; i < refId.size(); i++) {
    	//	            	//this.setRuleId(reference);
    	//	            	String referenceName = (String)refNam.get(i);
    	//	            	if(null != referenceName){
    	//		            	this.setRuleId(refId.get(i).toString() + '-'+ refNam.get(i).toString());
    	//		            	this.setRuleIdNameComb(refNam.get(i).toString()+'~'+refId.get(i).toString());
    	//	            	}
    	//	            	else{
    	//	            		this.setRuleId(refId.get(i).toString());
    	//	            	}
    	//	            }
    	//            }
    	//        }
    	
    	
    	
    	this.stateDesc=benefitComponent.getStateDesc();
    	this.productType = super.getProductTypeFromSession();
    	
    	// CR - added  benefit component version 
    	
    	this.bnftCmpntversion =  benefitComponent.getVersion();
    	
    }

  
    /**
     * Returns the dummyVariable
     * @return String dummyVariable.
     */
    public String getDummyVariable() {
        return dummyVariable;
    }
    
    /**
     * Sets the dummyVariable
     * @param dummyVariable.
     */
    public void setDummyVariable(String dummyVariable) {
        this.dummyVariable = dummyVariable;
    }

    /**
     * 
     * Returns the businessDomain
     * 
     * @return String businessDomain.
     *  
     */

    public String getBusinessDomain() {
        return businessDomain;
    }

    /**
     * 
     * Returns the businessEntity
     * 
     * @return String businessEntity.
     *  
     */

    public String getBusinessEntity() {
        return businessEntity;
    }

    /**
     * 
     * Returns the businessGroup
     * 
     * @return String businessGroup.
     *  
     */

    public String getBusinessGroup() {
        return businessGroup;
    }

    /**
     * 
     * Returns the createdBy
     * 
     * @return String createdBy.
     *  
     */

    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 
     * Returns the createdDate
     * 
     * @return String createdDate.
     *  
     */

    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * 
     * Returns the description
     * 
     * @return String description.
     *  
     */

    public String getDescription() {
        return description;
    }

    /**
     * 
     * Returns the effectiveDate
     * 
     * @return String effectiveDate.
     *  
     */

    public String getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * 
     * Returns the expiryDate
     * 
     * @return String expiryDate.
     *  
     */

    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * 
     * Returns the lastUpdatedDate
     * 
     * @return String lastUpdatedDate.
     *  
     */

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * 
     * Returns the lineOfBusiness
     * 
     * @return String lineOfBusiness.
     *  
     */

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    /**
     * 
     * Returns the name
     * 
     * @return String name.
     *  
     */

    public String getName() {
        return name;
    }

    /**
     * 
     * Returns the updatedBy
     * 
     * @return String updatedBy.
     *  
     */

    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 
     * Sets the businessDomain
     * 
     * @param businessDomain.
     *  
     */

    public void setBusinessDomain(String businessDomain) {
        this.businessDomain = businessDomain;
    }

    /**
     * 
     * Sets the businessEntity
     * 
     * @param businessEntity.
     *  
     */

    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }

    /**
     * 
     * Sets the businessGroup
     * 
     * @param businessGroup.
     *  
     */

    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }

    /**
     * 
     * Sets the createdBy
     * 
     * @param createdBy.
     *  
     */

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 
     * Sets the createdDate
     * 
     * @param createdDate.
     *  
     */

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 
     * Sets the description
     * 
     * @param description.
     *  
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * Sets the effectiveDate
     * 
     * @param effectiveDate.
     *  
     */

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * 
     * Sets the expiryDate
     * 
     * @param expiryDate.
     *  
     */

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * 
     * Sets the lastUpdatedDate
     * 
     * @param lastUpdatedDate.
     *  
     */

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * 
     * Sets the lineOfBusiness
     * 
     * @param lineOfBusiness.
     *  
     */

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    /**
     * 
     * Sets the name
     * 
     * @param name.
     *  
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * Sets the updatedBy
     * 
     * @param updatedBy.
     *  
     */

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    
    
    /**
	 * @return Returns the valueForPrint.
	 */
	public String getValueForPrint() {
		this.retrieveBenefitComponent();
		return valueForPrint;
	}
	/**
	 * @param valueForPrint The valueForPrint to set.
	 */
	public void setValueForPrint(String valueForPrint) {
		this.valueForPrint = valueForPrint;
	}
	/**
	 * @return Returns the componentType.
	 */
	public String getComponentType() {
		return componentType;
	}
	/**
	 * @param componentType The componentType to set.
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
	 * @param mandateType The mandateType to set.
	 */
	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}
	/**
	 * @return Returns the ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * @param ruleId The ruleId to set.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * @return Returns the selectedStateId.
	 */
	public String getSelectedStateId() {
		return selectedStateId;
	}
	/**
	 * @param selectedStateId The selectedStateId to set.
	 */
	public void setSelectedStateId(String selectedStateId) {
		this.selectedStateId = selectedStateId;
	}
	/**
	 * @return Returns the stateDesc.
	 */
	public String getStateDesc() {
		return stateDesc;
	}
	/**
	 * @param stateDesc The stateDesc to set.
	 */
	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
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
	 * @return Returns the printValue.
	 */
	public String getPrintValue() {
		String requestForPrint = getRequest().getParameter(
		"printValueForGenInfo");
		if (null != requestForPrint && !requestForPrint.equals("")) {
			printValue = requestForPrint;
		} else {
			printValue = "";
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
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Product Configuration >> Product ("+getProductSessionObject().getProductKeyObject().getProductName()+") >> Print";
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
	 * @return Returns the benefitName.
	 */
	public String getBenefitName() {
		return benefitName;
	}
	/**
	 * @param benefitName The benefitName to set.
	 */
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
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
		//Adding the label and benefit name heading to the Panel
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
		ProductTreeStandardBenefits productTreeStandardBenefits = null;		
		if(null != this.getBenefitDetailsList()&& this.getBenefitDetailsList().size()>0){
			List benefitDetails = this.getBenefitDetailsList();
			if(null != benefitDetails){
				panel.setWidth("100%");
				panel.setColumns(1);
				panel.setBorder(0);
				panel.setCellpadding("3");
				panel.setCellspacing("1");
				
				for(int i = 0; i < benefitDetails.size(); i++){
					
					if(benefitDetails.size() > 0){
						productTreeStandardBenefits = (ProductTreeStandardBenefits)benefitDetails.get(i);
					}
					//Creating an input hidden field for Benefit Id.
					HtmlInputHidden inputHiddenBenefitId = new HtmlInputHidden();
					inputHiddenBenefitId.setId("BnftId"+i);
					inputHiddenBenefitId.setValue(new Integer(productTreeStandardBenefits.getStandardBenefitId()));
					// Creating a value binding for each benefit id and Setting it.
					ValueBinding valBindingForBenefitId = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{productComponentGeneralInfoBackingBean.hiddenValBenefitId[" + productTreeStandardBenefits.getStandardBenefitId()
									+ "]}");		
					inputHiddenBenefitId.setValueBinding("value", valBindingForBenefitId);				
					
					//Creating an output text each for a benefit name.
					HtmlOutputText outputTextBenefitName = new HtmlOutputText();
					outputTextBenefitName.setId("Name" + i);
					outputTextBenefitName.setValue(productTreeStandardBenefits.getStandardBenefitDesc());
					
					
					//Creating the hidden field for storing the Name.
					HtmlInputHidden inputHiddenBenefitName = new HtmlInputHidden();
					inputHiddenBenefitName.setId("BnftName"+i);
					inputHiddenBenefitName.setValue(productTreeStandardBenefits.getStandardBenefitDesc());
					//Creating a value binding and setting it to the benefit name output text.
					ValueBinding valBindingForBenefitName = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{productComponentGeneralInfoBackingBean.hiddenValBenefitName[" + productTreeStandardBenefits.getStandardBenefitId()
							+ "]}");		
					inputHiddenBenefitName.setValueBinding("value", valBindingForBenefitName);	
					
					//Creating a boolean check box each for a benefit.
					HtmlSelectBooleanCheckbox checkbox = new HtmlSelectBooleanCheckbox();
					checkbox.setId("checkbox"+i);
					checkbox.setValue(Boolean.valueOf(productTreeStandardBenefits.isBenefitVisibilityStatus()));
					//Creating and setting Value binding for each checkbox
					checkbox.setValueBinding("value",FacesContext
							.getCurrentInstance()
							.getApplication().createValueBinding(
									"#{productComponentGeneralInfoBackingBean.hiddenValBenefitVisible[" + productTreeStandardBenefits.getStandardBenefitId()
									+ "]}"));
					
					checkbox.setValue(Boolean.valueOf(productTreeStandardBenefits.isBenefitVisibilityStatus()));
					
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
					panel.getChildren().add(lblcheckbox);
					
					}
				}
			}
		else{
			panel.setWidth("100%");
			panel.setColumns(2);
			panel.setBorder(0);
			panel.setCellpadding("3");
			panel.setCellspacing("1");
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
	 * @return Returns the loadAssociatedBenefits.
	 */
	public HtmlInputHidden getLoadAssociatedBenefits() {
		
		loadAssociatedBenefits.setValue(loadAssociatedBenefits());
		return loadAssociatedBenefits;
	}
	/**
	 * @param loadAssociatedBenefits The loadAssociatedBenefits to set.
	 */
	public void setLoadAssociatedBenefits(HtmlInputHidden loadAssociatedBenefits) {
		this.loadAssociatedBenefits = loadAssociatedBenefits;
	}
	
	
	
	/**
	 * @return Returns the productTypeForBenefit.
	 */
	public String getProductTypeForBenefit() {
		return productTypeForBenefit;
	}
	/**
	 * @param productTypeForBenefit The productTypeForBenefit to set.
	 */
	public void setProductTypeForBenefit(String productTypeForBenefit) {
		this.productTypeForBenefit = productTypeForBenefit;
	}
	
	public boolean isNoFieldSelected() {
		return noFieldSelected;
	}
	public void setNoFieldSelected(boolean noFieldSelected) {
		this.noFieldSelected = noFieldSelected;
	}
	
	public Map getHiddenPrevValBenefitVisible() {
		return hiddenPrevValBenefitVisible;
	}
	public void setHiddenPrevValBenefitVisible(Map hiddenPrevValBenefitVisible) {
		this.hiddenPrevValBenefitVisible = hiddenPrevValBenefitVisible;
	}
	/**
	 * @return Returns the flagStatus.
	 */
	public String getFlagStatus() {
		return flagStatus;
	}
	/**
	 * @param flagStatus The flagStatus to set.
	 */
	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
	}
	/**
	 * @return Returns the bnftCmpntversion.
	 */
	public int getBnftCmpntversion() {
		return bnftCmpntversion;
	}
	/**
	 * @param bnftCmpntversion The bnftCmpntversion to set.
	 */
	public void setBnftCmpntversion(int bnftCmpntversion) {
		this.bnftCmpntversion = bnftCmpntversion;
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
	 * @return Returns the ruleIdNameComb.
	 */
	public String getRuleIdNameComb() {
		return ruleIdNameComb;
	}
	/**
	 * @param ruleIdNameComb The ruleIdNameComb to set.
	 */
	public void setRuleIdNameComb(String ruleIdNameComb) {
		this.ruleIdNameComb = ruleIdNameComb;
	}
	/**
	 * method for saving changed rule values .
	 * It create request object using request factory and calls executeService of business service.
	 */
	public String saveRuleInfo() {
		List validationMessages = new ArrayList();
		this.ruleId = getRuleIdFromTilda(this.ruleIdNameComb);
		
		//Mandatory rule id check removed for GB,GP & SB.
		if(WebConstants.GEN_BENEFITS.equals(this.name.toUpperCase())){
			requiredRule = false;
		}else if(WebConstants.GENERAL_PROVISIONS.equals(this.name.toUpperCase())){
			requiredRule = false;
		}else if(WebConstants.SUPPLEMENTAL_BENEFITS.equals(this.name.toUpperCase())){
			requiredRule = false;
		}else if ((null == this.ruleId) || (WebConstants.EMPTY_STRING.equals(this.ruleId))) {
			requiredRule = true;
			validationMessages
					.add(new ErrorMessage("mandatory.field.required"));
        }
				
		if(!requiredRule){
			SaveProductComponentRuleInformationRequest request = (SaveProductComponentRuleInformationRequest) this
					.getServiceRequest(ServiceManager.PRODUCT_COMPONENT_RULE_REQUEST);
			request.setProductId(this.getProductKeyFromSession());
			request.setBenefitComponentId(this
					.getBenefitComponentIdFromSession());
			request.setRuleId(ruleId);
			SaveProductComponentRuleInformationResponse response = (SaveProductComponentRuleInformationResponse) this
					.executeService(request);
			validationMessages = response.getMessages();
		}
		addAllMessagesToRequest(validationMessages);
		return "";
	}
	/**
	 * @return Returns the ruledesc.
	 */
	public String getRuledesc() {
		return ruledesc;
	}
	/**
	 * @param ruledesc The ruledesc to set.
	 */
	public void setRuledesc(String ruledesc) {
		this.ruledesc = ruledesc;
	}
	
	/**
	 * this method is for sepearting rule id from tilda separated string(combination of rule id and rule description )
	 * @param rulewithTilda
	 * @return ruleId
	 */
	private String getRuleIdFromTilda(String rulewithTilda){
		String ruleId =null;
		StringTokenizer tokenizer = new StringTokenizer(rulewithTilda,"~");
		 while(tokenizer.hasMoreTokens()){
	     	 tokenizer.nextToken();
	     	ruleId = tokenizer.nextToken();
	     } 
		return ruleId;
	}
	/**
	 * @return Returns the ruleIdForView.
	 */
	public String getRuleIdForView() {
		return ruleIdForView;
	}
	/**
	 * @param ruleIdForView The ruleIdForView to set.
	 */
	public void setRuleIdForView(String ruleIdForView) {
		this.ruleIdForView = ruleIdForView;
	}
	/**
	 * @return Returns the requiredRule.
	 */
	public boolean isRequiredRule() {
		return requiredRule;
	}
	/**
	 * @param requiredRule The requiredRule to set.
	 */
	public void setRequiredRule(boolean requiredRule) {
		this.requiredRule = requiredRule;
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
}