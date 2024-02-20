/*
 * BenefitComponentHierarchiesBackingBean.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.benefitcomponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;

import org.apache.myfaces.component.html.ext.HtmlSelectBooleanCheckbox;

import com.wellpoint.wpd.common.benefitcomponent.request.BenefitHierarchyCreateRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitHierarchyDeleteRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitHierarchySearchRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitHierarchyUpdateRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitRetrieveRequest;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitHierarchyDeleteResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitHierarchyResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitHierarchySearchResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitHierarchyUpdateResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitRetrieveResponse;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitHierarchyAssociationVO;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitHierarchyVO;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.framework.util.SequenceUtil;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * Backing bean for Benefit Hierarchy.
 * 
 * This bean will bind with the jsp pages.
 * BenefitComponentHierarchiesBackingBean contains the getters and setters of the 
 * variables and respective functions
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentHierarchiesBackingBean extends BenefitComponentBackingBean{	
	
	private String benefit= null;
	
	boolean validationStatus = false;
	
	boolean benefitValdn = true;
	
	List validationMessages = null;
	
	List benefitsList = null;
	
	private BenefitHierarchyVO benefitHierarchyVO = null;
	
	private int version;
	
	private String status;
	
	private String state;
	
	private boolean createFlag = true;
	
	private HtmlPanelGrid panel = new HtmlPanelGrid();
	
	private HtmlPanelGrid displayPanel = new HtmlPanelGrid();
	
	private Map hiddenValMapSeq = new HashMap();
	
	private Map hiddenValHirAssnId = new HashMap();
	
	private Map hiddenValHirerarchyId = new HashMap();
	
	private Map hiddenValDeleteBnftSysId = new HashMap();
	
	private List benefitHierarchies;
	
	private String benefitComponentName;
	
	BenefitComponentCreateBackingBean benefitComponentCreateBackingBean = new BenefitComponentCreateBackingBean();
	
	private int benefitHierarchyId;
	
	private boolean doneFlag;
	
	private boolean checkInOpted;

	private String returnString = "";
	
	private boolean notesFlag;
	
	private HtmlPanelGrid benefitHeaderViewPanel = new HtmlPanelGrid();
	
	private String retrieveAllBenefitRecords;
	
	private String printBreadCrumbText;
	
	//private String loadBenefitHierarchy;
	private HtmlInputHidden loadBenefitHierarchy;
	
	private String panelData = "";
	
	private int benefitComponentSysId;
	
	private List informationMessageToDisplayOnPage = new ArrayList();
	
	
	/**
	 * @return Returns the hiddenValDeleteBnftSysId.
	 */
	public Map getHiddenValDeleteBnftSysId() {
		return hiddenValDeleteBnftSysId;
	}
	/**
	 * @param hiddenValDeleteBnftSysId The hiddenValDeleteBnftSysId to set.
	 */
	public void setHiddenValDeleteBnftSysId(Map hiddenValDeleteBnftSysId) {
		this.hiddenValDeleteBnftSysId = hiddenValDeleteBnftSysId;
	}
	/**
	 * Constuctor
	 */
	public BenefitComponentHierarchiesBackingBean(){	
		validationMessages = new ArrayList(10);
		
		benefitComponentSysId = getBenefitComponentSessionObject().getBenefitComponentId();	
	}	
	
	
	
	/**
	 * @return Returns the retrieveAllBenefitRecords.
	 */
	public String getRetrieveAllBenefitRecords() {
		
		benefitsList =null;
		BenefitRetrieveRequest benefitRetrieveRequest = (BenefitRetrieveRequest)
		this.getServiceRequest(ServiceManager.BENEFIT_RETRIEVE_REQUEST);
		benefitRetrieveRequest.setBenefitComponentId(getBenefitComponentSessionObject().getBenefitComponentId());
		benefitRetrieveRequest.setBusinessDomainList(getBenefitComponentSessionObject().getBusinessDomainList());
		BenefitRetrieveResponse benefitRetrieveResponse = (BenefitRetrieveResponse)
		this.executeService(benefitRetrieveRequest);
		if(null != benefitRetrieveResponse){
			benefitsList = benefitRetrieveResponse.getBenefits();
		}
		else
			benefitsList = null;
		
		this.setBenefitsList(benefitsList);
		return retrieveAllBenefitRecords;
	}
	/**
	 * @param retrieveAllBenefitRecords The retrieveAllBenefitRecords to set.
	 */
	public void setRetrieveAllBenefitRecords(String retrieveAllBenefitRecords) {
		this.retrieveAllBenefitRecords = retrieveAllBenefitRecords;
	}
	/**
	 * @return Returns the checkInOpted.
	 */
	public boolean isCheckInOpted() {
		return checkInOpted;
	}
	/**
	 * @param checkInOpted The checkInOpted to set.
	 */
	public void setCheckInOpted(boolean checkInOpted) {
		this.checkInOpted = checkInOpted;
	}
	/**
	 * @return Returns the doneFlag.
	 */
	public boolean isDoneFlag() {
		return doneFlag;
	}
	/**
	 * @param doneFlag The doneFlag to set.
	 */
	public void setDoneFlag(boolean doneFlag) {
		this.doneFlag = doneFlag;
	}
	/**
	 * @return Returns the benefitHierarchyId.
	 */
	public int getBenefitHierarchyId() {
		return benefitHierarchyId;
	}
	/**
	 * @param benefitHierarchyId The benefitHierarchyId to set.
	 */
	public void setBenefitHierarchyId(int benefitHierarchyId) {
		this.benefitHierarchyId = benefitHierarchyId;
	}
	/**
	 * @return Returns the createFlag.
	 */
	public boolean isCreateFlag() {
		return createFlag;
	}
	/**
	 * @param createFlag The createFlag to set.
	 */
	public void setCreateFlag(boolean createFlag) {
		this.createFlag = createFlag;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
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
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return getBenefitComponentSessionObject().getBenefitComponentVersionNumber();
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return Returns the hiddenValHirerarchyId.
	 */
	public Map getHiddenValHirerarchyId() {
		return hiddenValHirerarchyId;
	}
	/**
	 * @param hiddenValHirerarchyId The hiddenValHirerarchyId to set.
	 */
	public void setHiddenValHirerarchyId(Map hiddenValHirerarchyId) {
		this.hiddenValHirerarchyId = hiddenValHirerarchyId;
	}

	/**
	 * Action method to associate Benefit Hirarchies
	 * @return String 
	 */
	public String create(){
		BenefitHierarchyVO benefitHierarchyVO = null;
		validationStatus = validateAllFields();
		if(!validationStatus){
			this.searchBenefitHierarchies("benefitHierarchy");
			addAllMessagesToRequest(validationMessages);
			getRequest().setAttribute("RETAIN_Value", "");
			return this.returnString;
		}else{
			getRequest().setAttribute("RETAIN_Value", "");
			return createBenefitHierarchies(benefitHierarchyVO);
		}
	}
	/**
	 * Method to associate Benefit Hirarchies
	 * @return String
	 */
	private String createBenefitHierarchies(BenefitHierarchyVO benefitHierarchyVO) {
		benefitHierarchyVO = generateBenefitHierarchyVO();
		BenefitHierarchyCreateRequest benefitHierarchyCreateRequest = (BenefitHierarchyCreateRequest)
		this.getServiceRequest(ServiceManager.BENEFIT_HIERARCHY_CREATE_REQUEST);
		benefitHierarchyCreateRequest.setBenefitHierarchyVO(benefitHierarchyVO);
		benefitHierarchyCreateRequest.setDoneFlag(this.doneFlag);
		benefitHierarchyCreateRequest.setCheckInFlag(this.checkInOpted);
		benefitHierarchyCreateRequest.setNotesFlag(this.notesFlag);
		BenefitHierarchyResponse benefitHierarchyResponse = (BenefitHierarchyResponse)
		this.executeService(benefitHierarchyCreateRequest);
		if(null != benefitHierarchyResponse){
			this.setBenefitHierarchyVO(benefitHierarchyResponse.getBenefitHierarchyVO());
			if(null != this.getBenefitHierarchyVO() && null != this.getBenefitHierarchyVO().getBenefitHierarchiesList()){
				super.updateTreeStructure();
				registerSequence(this.getBenefitHierarchyVO().getBenefitHierarchiesList());
				this.setBenefitHierarchyId(this.getBenefitHierarchyVO().getBenefitHierarchyId());
				this.getBenefitComponentSessionObject().setBenefitHierarchyId(this.getBenefitHierarchyVO().getBenefitHierarchyId());
				this.setCreateFlag(false);
				this.setBenefit(null);
				this.returnString = "benefitHierarchy";
			}
			if(benefitHierarchyResponse.isSuccessFlag()){
				 if(benefitHierarchyCreateRequest.isDoneFlag() && benefitHierarchyCreateRequest.isCheckInFlag()){
	    	    	this.setBreadCrumbText("Product Configuration >> Benefit Component >> Create");
	    	    	this.returnString = "benefitComponentCreate";
	    	    }
	    	    else if(benefitHierarchyCreateRequest.isDoneFlag() && !benefitHierarchyCreateRequest.isCheckInFlag()){
	    	    	if(!this.isNotesFlag()){
		    	    	this.returnString = "benefitHierarchy";	    	    		
	    	    	}else{
	    	    		getRequest().setAttribute("RETAIN_Value", "");
	    	    		return "";
	    	    	}

	    	    }				
			}
		}
		//added the message from the response to the class variable --Defect fix for WAS7 Migration
		informationMessageToDisplayOnPage = benefitHierarchyResponse.getMessages();
		getRequest().setAttribute("RETAIN_Value", "");
		return this.returnString;
	}
	/**
	 * Method to set values into BenefitHierarchyVO
	 * @return BenefitHierarchyVO
	 */
	private BenefitHierarchyVO generateBenefitHierarchyVO() {
		List benefitIds = null;
		List benefitNames = null;
		List benefits = null;
		BenefitHierarchyVO benefitHierarchyVO = new BenefitHierarchyVO();
		String selectedBenefit = this.getBenefit();
		if(null != selectedBenefit){
			benefitIds = WPDStringUtil.getListFromTildaString(selectedBenefit,2,1,1);
			benefitNames = WPDStringUtil.getListFromTildaString(selectedBenefit,2,2,2);
		}
		if(null != benefitIds && null != benefitNames && benefitIds.size() > 0 && benefitNames.size() > 0){
			benefits = new ArrayList(benefitIds.size());
			for(int i = 0; i < benefitIds.size();i++){
				BenefitHierarchyAssociationVO benefitHierarchyAssociationVO = new BenefitHierarchyAssociationVO();
				benefitHierarchyAssociationVO.setBenefitId(((Integer)benefitIds.get(i)).intValue());
				benefitHierarchyAssociationVO.setBenefitName((String)benefitNames.get(i));
				benefits.add(benefitHierarchyAssociationVO);
			}
		}
		if(!this.isNotesFlag()){
			benefitHierarchyVO.setBenefitHierarchiesList(benefits);			
		}
		benefitHierarchyVO.setBenefitComponentId(getBenefitComponentSessionObject().getBenefitComponentId());
		benefitHierarchyVO.setBenefitComponentParentId(getBenefitComponentSessionObject().getBenefitComponentParentId());
		benefitHierarchyVO.setName(getBenefitComponentSessionObject().getBenefitComponentName());
		benefitHierarchyVO.setMasterVersion(getBenefitComponentSessionObject().getBenefitComponentVersionNumber());
		benefitHierarchyVO.setStatus(getBenefitComponentSessionObject().getStatus());
		benefitHierarchyVO.setBusinessDomainList(benefitComponentCreateBackingBean.getBenefitComponentSessionObject().getBusinessDomainList());
		if(this.isCreateFlag())
			benefitHierarchyVO.setInsertFlag(true);
		else{
			benefitHierarchyVO.setInsertFlag(false);
			benefitHierarchyVO.setBenefitHierarchyId(this.getBenefitHierarchyId());
		}	
		return benefitHierarchyVO;
	}
	/**
	 * Methode for validation
	 * @return boolean
	 * 
	 */
	private boolean validateAllFields(){
		benefitValdn = false;
		if((null!= this.getBenefit().trim())  && !("".equals(this.getBenefit()))){
			benefitValdn = true;
			return true;
		}else{
			ErrorMessage errorMessage = new ErrorMessage(WebConstants.MANDATORY_FIELDS_REQUIRED);
			validationMessages.add(errorMessage);
			return false;	
			}
		
		}
	
	
	/**
	 * @return Returns the benefit.
	 */
	public String getBenefit() {
		return benefit;
	}
	/**
	 * @param benefit The benefit to set.
	 */
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	
	
	/**
	 * @return Returns the benefitValdn.b
	 */
	public boolean isBenefitValdn() {
		return benefitValdn;
	}
	/**
	 * @param benefitValdn The benefitValdn to set.
	 */
	public void setBenefitValdn(boolean benefitValdn) {
		this.benefitValdn = benefitValdn;
	}
	/**
	 * Methode to get the benefit List
	 * @return Returns the benefitsList.
	 */
	public List getBenefitsList() {
		return benefitsList;
	}
	/**
	 * @param benefitsList The benefitsList to set.
	 */
	public void setBenefitsList(List benefitsList) {
		this.benefitsList = benefitsList;
	}
	
	/**
	 * Method to render the panel which has the list of the benefit lines
	 * created.
	 * @return HtmlPanelGrid
	 */
	public HtmlPanelGrid getPanel() {
		HtmlPanelGrid panel = new HtmlPanelGrid();
		//searchBenefitHierarchies("benefitHierarchy");
		BenefitHierarchyAssociationVO benefitHierarchyAssociationVO = null;		
		if(null != this.getBenefitHierarchyVO()){
			List benefitHierarchies = this.getBenefitHierarchyVO().getBenefitHierarchiesList();
			
			if(null != benefitHierarchies){
				//getBenefitHeaderViewPanel(panel);
				panel.setWidth("100%");
				panel.setColumns(3);
				panel.setBorder(0);
				panel.setBgcolor("#cccccc");
				panel.setCellpadding("3");
				panel.setCellspacing("1");
				Collections.sort(benefitHierarchies);
				for(int i = 0; i < benefitHierarchies.size(); i++){
					//int levelId = 0;
					if(benefitHierarchies.size() > 0){
						benefitHierarchyAssociationVO = (BenefitHierarchyAssociationVO)benefitHierarchies.get(i);
					}
					HtmlInputText inputText = new HtmlInputText();
					inputText.setId("SeqNo" + i);
					inputText.setStyleClass("formInputField");
					inputText.setStyle("width:75px;");
					inputText.setMaxlength(3);
					inputText.setSize(1);
					inputText.setValue("" +benefitHierarchyAssociationVO.getSequenceNumber());
					inputText.setOnkeypress("isNum();");
					inputText.setTabindex("4");
					ValueBinding valBindingForSeq = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{BenefitComponentHierarchiesBackingBean.hiddenValMapSeq[" + benefitHierarchyAssociationVO.
							getBenefitHierarchyAssociationId()
									+ "]}");		
					inputText.setValueBinding("value", valBindingForSeq);
					
					HtmlInputHidden hiddenForHierarchyAssnId = new HtmlInputHidden();
					hiddenForHierarchyAssnId.setId("HirAssnId"+i);
					hiddenForHierarchyAssnId.setValue(new Integer(benefitHierarchyAssociationVO.getBenefitHierarchyAssociationId()));
					
					ValueBinding valBindingForHirAssnId = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{BenefitComponentHierarchiesBackingBean.hiddenValHirAssnId[" + benefitHierarchyAssociationVO.
							getBenefitHierarchyAssociationId()
									+ "]}");		
					hiddenForHierarchyAssnId.setValueBinding("value", valBindingForHirAssnId);
					
					HtmlInputHidden hiddenForHierarchyId = new HtmlInputHidden();
					hiddenForHierarchyId.setId("HirId"+i);
					hiddenForHierarchyId.setValue(new Integer(benefitHierarchyAssociationVO.getBenefitHierarchyId()));
					
					ValueBinding valBindingForHirerarchyId = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{BenefitComponentHierarchiesBackingBean.hiddenValHirerarchyId[" + benefitHierarchyAssociationVO.
							getBenefitHierarchyAssociationId()
									+ "]}");		
					hiddenForHierarchyId.setValueBinding("value", valBindingForHirerarchyId);
					
					HtmlOutputText outputText1 = new HtmlOutputText();
					outputText1.setId("Name" + i);
					outputText1.setValue(benefitHierarchyAssociationVO.getBenefitName());
					outputText1.setStyleClass(benefitHierarchyAssociationVO.isValidBenefit()?"outputText":"outputTextRed");
					
					HtmlSelectBooleanCheckbox deleteCheckBox = new HtmlSelectBooleanCheckbox();
					deleteCheckBox.setOnclick("test(this)");
					ValueBinding valBindingForDeleteBenefits = FacesContext
					.getCurrentInstance().getApplication()
					.createValueBinding(
							"#{BenefitComponentHierarchiesBackingBean.hiddenValDeleteBnftSysId["
									+ "\"B"+benefitHierarchyAssociationVO.
									getBenefitHierarchyAssociationId()+"B"+benefitHierarchyAssociationVO.getBenefitHierarchyId()+"B"+benefitHierarchyAssociationVO.getBenefitId() + "\"]}");
	
					deleteCheckBox.setValueBinding("value",valBindingForDeleteBenefits);
					
							
					deleteCheckBox.setId("Delete" + i);
				
					HtmlOutputLabel lblDesc = new HtmlOutputLabel();
					lblDesc.setFor("Update" + i);
					lblDesc.setId("lblDesc" + i);
	
					HtmlOutputLabel lblName = new HtmlOutputLabel();
					lblName.setFor("Name" + i);
					lblName.setId("lblName" + i);
	
					HtmlOutputLabel lblDelete = new HtmlOutputLabel();
					lblDelete.setFor("Delete" + i);
					lblDelete.setId("lblDelete" + i);
					lblDelete.setTabindex("4");
					lblDesc.getChildren().add(inputText);
					lblName.getChildren().add(outputText1);
					lblName.getChildren().add(hiddenForHierarchyAssnId);
					lblName.getChildren().add(hiddenForHierarchyId);					
					//try {
						//if(getUser().isAuthorized(WebConstants.BENEFIT_MODULE,StateFactory.DELETE_TASK)){
							lblDelete.getChildren().add(deleteCheckBox);	
						//}
					//} catch (SevereException e) {
						// TODO Auto-generated catch block
					//Logger.logError(e);
					//}
					
					
					panel.getChildren().add(lblDesc);
					panel.getChildren().add(lblName);
					panel.getChildren().add(lblDelete);
					}
			}
			}
		//added the messages to the request --Defect fix for WAS7 Migration
		addAllMessagesToRequest(informationMessageToDisplayOnPage);
		return panel;
	}
	/**
	 * Method to delete the admin options.
	 * 
	 * @return String Returns the navigation path.
	 */
	public String deleteBenefits() {
		List messageList = new ArrayList(1);
		List benList = new ArrayList(this.getHiddenValDeleteBnftSysId()==null?0:this.getHiddenValDeleteBnftSysId().size());
		Map map=this.getHiddenValDeleteBnftSysId();
		Iterator it = map.entrySet().iterator();
		 while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        if((Boolean)(pairs.getValue())==Boolean.TRUE){
	        	benList.add(pairs.getKey()) ;
	        }
	    }	
			List messages = null;
			String hierarchKeyToBeDeleted = null;
			BenefitHierarchyVO benefitHierarchyVO = new BenefitHierarchyVO();
			int hierarchyIdToBeDeleted = 0;
			int hierarchyAssnIdToBeDeleted = 0;
			int benefitIdToBeDeleted = 0;
			BenefitHierarchyDeleteResponse benefitHierarchyDeleteResponse = null;
			for(int i=0;i<benList.size();i++ ){
				hierarchKeyToBeDeleted = (String)benList.get(i);
			if(null != hierarchKeyToBeDeleted && !"".equals(hierarchKeyToBeDeleted)){
				StringTokenizer tokenizer = new StringTokenizer(hierarchKeyToBeDeleted,"B");
				if(tokenizer.hasMoreTokens()){
					Object hirId = tokenizer.nextToken();
					Object hirAssnId = tokenizer.nextToken();
					// changed Nov 26th
					Object benefitId = tokenizer.nextToken();
					
					if(null != hirId && !"".equals(hirId))
						hierarchyAssnIdToBeDeleted = Integer.parseInt((String)hirId);
					if(null != hirAssnId && !"".equals(hirAssnId))
						hierarchyIdToBeDeleted = Integer.parseInt((String)hirAssnId); 
					if(null != benefitId && !"".equals(benefitId))
						benefitIdToBeDeleted = Integer.parseInt((String)benefitId); 
				}
			}
			
			BenefitHierarchyDeleteRequest benefitHierarchyDeleteRequest = (BenefitHierarchyDeleteRequest)
			this.getServiceRequest(ServiceManager.BENEFIT_HIERARCHY_DELETE_REQUEST);
			
			
			
			benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setBenefitHierarchyAssociationId(hierarchyAssnIdToBeDeleted);
			benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setBenefitHierarchyId(hierarchyIdToBeDeleted);
			benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setBenefitId(benefitIdToBeDeleted);
			// change ends
			benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setBenefitComponentId(getBenefitComponentSessionObject().getBenefitComponentId());
			benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setBenefitComponentParentId(getBenefitComponentSessionObject().getBenefitComponentParentId());
			benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setName(getBenefitComponentSessionObject().getBenefitComponentName());
			benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setMasterVersion(getBenefitComponentSessionObject().getBenefitComponentVersionNumber());
			benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setBusinessDomainList(getBenefitComponentSessionObject().getBusinessDomainList());
			benefitHierarchyDeleteResponse = (BenefitHierarchyDeleteResponse)
			this.executeService(benefitHierarchyDeleteRequest);
			}
			/**eWPD Stabilization release
			 * Moving the sequence number updation code outside the loop to avoid iteration of updates
			  **/
			//Bug fixed for showing updated successfully message while deleting
			if(null != benefitHierarchyDeleteResponse){
				super.updateTreeStructure();
				messages = new ArrayList(benefitHierarchyDeleteResponse.getMessages()==null?0:benefitHierarchyDeleteResponse.getMessages().size());
				messages.addAll(benefitHierarchyDeleteResponse.getMessages());
				
				//added the message from the response to the class variable --Defect fix for WAS7 Migration
				informationMessageToDisplayOnPage = benefitHierarchyDeleteResponse.getMessages();
				
				this.setBenefitHierarchyVO(benefitHierarchyDeleteResponse.getBenefitHierarchyVO());
				benefitHierarchyVO = this.getBenefitHierarchyVO();
				if(null != benefitHierarchyVO){
					if(null != benefitHierarchyVO.getBenefitHierarchiesList()){
						registerSequence(benefitHierarchyVO.getBenefitHierarchiesList());
						benefitHierarchyVO.setBenefitComponentId(getBenefitComponentSessionObject().getBenefitComponentId());
						benefitHierarchyVO.setBenefitComponentParentId(getBenefitComponentSessionObject().getBenefitComponentParentId());
						benefitHierarchyVO.setName(getBenefitComponentSessionObject().getBenefitComponentName());
						benefitHierarchyVO.setMasterVersion(getBenefitComponentSessionObject().getBenefitComponentVersionNumber());
						benefitHierarchyVO.setBusinessDomainList(getBenefitComponentSessionObject().getBusinessDomainList());
						this.updateSequence(benefitHierarchyVO);
					}
				}
				addAllMessagesToRequest(messages);
			 }
			
			
			if (null == this.benefit || "".equals(this.benefit)) {
	        	getRequest().setAttribute("RETAIN_Value", "");
	        }
			return "benefitHierarchy";
	}
	/**
	 * @param viewPanel
	 */
	private void getBenefitHeaderViewPanel(HtmlPanelGrid viewPanel) {
		viewPanel.setWidth("100%");
		viewPanel.setColumns(3);
		viewPanel.setBorder(0);
		viewPanel.setBgcolor("#cccccc");
		viewPanel.setCellpadding("10");
		viewPanel.setCellspacing("1");
		//HtmlOutputText updateOutputText = new HtmlOutputText();
		HtmlCommandButton updateButton = new HtmlCommandButton();
		updateButton.setId("Update");
		updateButton.setStyleClass("wpdbutton");
		updateButton.setOnmousedown("javascript:savePageAction(this.id);");
		updateButton.setValue("Update");
		
		MethodBinding updateMetBind = FacesContext
		.getCurrentInstance()
		.getApplication()
		.createMethodBinding(
				"#{BenefitComponentHierarchiesBackingBean.updateBenefitHierarchy}",
				new Class[] {});
		updateButton.setAction(updateMetBind);
			
		HtmlOutputText nameOutputText = new HtmlOutputText();
		HtmlOutputText deleteOutputText = new HtmlOutputText();
		
		nameOutputText.setValue("Name");
		nameOutputText.setId("Name");
		nameOutputText.setStyleClass("dataTableHeader");
	
		deleteOutputText.setValue(" ");
		deleteOutputText.setId("Delete");
		deleteOutputText.setStyleClass("dataTableHeader");
		
		viewPanel.getChildren().add(updateButton);
		viewPanel.getChildren().add(nameOutputText);
		viewPanel.getChildren().add(deleteOutputText);
	}
	/**
	 * @param panel The panel to set.
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
	}
	/**
	 * Method to delete Benefit Hierarchy
	 * @return String
	 */
	public String deleteBenefitHierarchy(){
		
		List messages = null;
		String hierarchKeyToBeDeleted = null;
		BenefitHierarchyVO benefitHierarchyVO = new BenefitHierarchyVO();
		int hierarchyIdToBeDeleted = 0;
		int hierarchyAssnIdToBeDeleted = 0;
		int benefitIdToBeDeleted = 0;
		if(null != this.getSession().getAttribute("HierarchyToBeDeleted"))
			hierarchKeyToBeDeleted = (String)this.getSession().getAttribute("HierarchyToBeDeleted");
		if(null != hierarchKeyToBeDeleted && !"".equals(hierarchKeyToBeDeleted)){
			StringTokenizer tokenizer = new StringTokenizer(hierarchKeyToBeDeleted,":");
			if(tokenizer.hasMoreTokens()){
				Object hirId = tokenizer.nextToken();
				Object hirAssnId = tokenizer.nextToken();
				// changed Nov 26th
				Object benefitId = tokenizer.nextToken();
				
				if(null != hirId && !"".equals(hirId))
					hierarchyAssnIdToBeDeleted = Integer.parseInt((String)hirId);
				if(null != hirAssnId && !"".equals(hirAssnId))
					hierarchyIdToBeDeleted = Integer.parseInt((String)hirAssnId); 
				if(null != benefitId && !"".equals(benefitId))
					benefitIdToBeDeleted = Integer.parseInt((String)benefitId); 
			}
		}
		BenefitHierarchyDeleteRequest benefitHierarchyDeleteRequest = (BenefitHierarchyDeleteRequest)
		this.getServiceRequest(ServiceManager.BENEFIT_HIERARCHY_DELETE_REQUEST);
		benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setBenefitHierarchyAssociationId(hierarchyAssnIdToBeDeleted);
		benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setBenefitHierarchyId(hierarchyIdToBeDeleted);
		benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setBenefitId(benefitIdToBeDeleted);
		// change ends
		benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setBenefitComponentId(getBenefitComponentSessionObject().getBenefitComponentId());
		benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setBenefitComponentParentId(getBenefitComponentSessionObject().getBenefitComponentParentId());
		benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setName(getBenefitComponentSessionObject().getBenefitComponentName());
		benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setMasterVersion(getBenefitComponentSessionObject().getBenefitComponentVersionNumber());
		benefitHierarchyDeleteRequest.getBenefitHierarchyVO().setBusinessDomainList(getBenefitComponentSessionObject().getBusinessDomainList());
		BenefitHierarchyDeleteResponse benefitHierarchyDeleteResponse = (BenefitHierarchyDeleteResponse)
		this.executeService(benefitHierarchyDeleteRequest);
		if(null != benefitHierarchyDeleteResponse){
			messages = new ArrayList(benefitHierarchyDeleteResponse.getMessages()==null?0:benefitHierarchyDeleteResponse.getMessages().size());
			messages.addAll(benefitHierarchyDeleteResponse.getMessages());
			this.setBenefitHierarchyVO(benefitHierarchyDeleteResponse.getBenefitHierarchyVO());
			benefitHierarchyVO = this.getBenefitHierarchyVO();
			if(null != benefitHierarchyVO){
				if(null != benefitHierarchyVO.getBenefitHierarchiesList()){
					registerSequence(benefitHierarchyVO.getBenefitHierarchiesList());
					benefitHierarchyVO.setBenefitComponentId(getBenefitComponentSessionObject().getBenefitComponentId());
					benefitHierarchyVO.setBenefitComponentParentId(getBenefitComponentSessionObject().getBenefitComponentParentId());
					benefitHierarchyVO.setName(getBenefitComponentSessionObject().getBenefitComponentName());
					benefitHierarchyVO.setMasterVersion(getBenefitComponentSessionObject().getBenefitComponentVersionNumber());
					benefitHierarchyVO.setBusinessDomainList(getBenefitComponentSessionObject().getBusinessDomainList());
					this.updateSequence(benefitHierarchyVO);
				}
			}
			addAllMessagesToRequest(messages);
		}
		getRequest().setAttribute("RETAIN_Value", "");
		return "benefitHierarchy";
	}
	/**
	 * Action methode to update benefit hierarchy 
	 * @return
	 */
	public String updateBenefitHierarchy(){
		BenefitHierarchyVO benefitHierarchyVO = getUpdatedListFromScreen();
		if(null != benefitHierarchyVO){
			updateSequence(benefitHierarchyVO);
		}
		if (null == this.benefit || "".equals(this.benefit)) {
        	getRequest().setAttribute("RETAIN_Value", "");
        }
		return "benefitHierarchy";
	}
	
	/**
	 * Methode to update the sequence of benefit hierarchies
	 * @param benefitHierarchyVO
	 * @return void
	 */
	private void updateSequence(BenefitHierarchyVO benefitHierarchyVO) {
		benefitHierarchyVO.setInsertFlag(false);
		SequenceUtil sequenceUtil = new SequenceUtil();
		benefitHierarchyVO.setBenefitHierarchiesList(
				sequenceUtil.reOrderObjects(benefitHierarchyVO.getBenefitHierarchiesList()));
		
		BenefitHierarchyUpdateRequest benefitHierarchyUpdateRequest = (BenefitHierarchyUpdateRequest)
		this.getServiceRequest(ServiceManager.BENEFIT_HIERARCHY_UPDATE_REQUEST);
		benefitHierarchyUpdateRequest.setBenefitHierarchyVO(benefitHierarchyVO);
		BenefitHierarchyUpdateResponse benefitHierarchyUpdateResponse = (BenefitHierarchyUpdateResponse)
		this.executeService(benefitHierarchyUpdateRequest);
		if(null != benefitHierarchyUpdateResponse){
			this.setBenefitHierarchyVO(benefitHierarchyUpdateResponse.getBenefitHierarchyVO());
			if(null != this.getBenefitHierarchyVO() && null != this.getBenefitHierarchyVO().getBenefitHierarchiesList()){
				registerSequence(this.getBenefitHierarchyVO().getBenefitHierarchiesList());
			}
		}
	}
	/**
	 * Method to render the title displayed above the results table in the page.
	 * @return HtmlPanelGrid
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
	 * @return Returns the benefitHierarchyVO.
	 */
	public BenefitHierarchyVO getBenefitHierarchyVO() {
		return benefitHierarchyVO;
	}
	/**
	 * @param benefitHierarchyVO The benefitHierarchyVO to set.
	 */
	public void setBenefitHierarchyVO(BenefitHierarchyVO benefitHierarchyVO) {
		this.benefitHierarchyVO = benefitHierarchyVO;
	}
	/**
	 * @return Returns the hiddenValHirAssnId.
	 */
	public Map getHiddenValHirAssnId() {
		return hiddenValHirAssnId;
	}
	/**
	 * @param hiddenValHirAssnId The hiddenValHirAssnId to set.
	 */
	public void setHiddenValHirAssnId(Map hiddenValHirAssnId) {
		this.hiddenValHirAssnId = hiddenValHirAssnId;
	}
	/**
	 * @return Returns the hiddenValMapSeq.
	 */
	public Map getHiddenValMapSeq() {
		return hiddenValMapSeq;
	}
	/**
	 * @param hiddenValMapSeq The hiddenValMapSeq to set.
	 */
	public void setHiddenValMapSeq(Map hiddenValMapSeq) {
		this.hiddenValMapSeq = hiddenValMapSeq;
	}
	/**
	 *  get the updated List 
	 * @return BenefitHierarchyVO
	 */
	private BenefitHierarchyVO getUpdatedListFromScreen(){
		BenefitHierarchyVO benefitHierarchyVO = new BenefitHierarchyVO();
		
		Set keysForAssnId = this.getHiddenValHirAssnId().keySet();
        Set keysForSeq = this.getHiddenValMapSeq().keySet();
        Set keysForHirId = this.getHiddenValHirerarchyId().keySet();
        
        Iterator keyIterForSeq = keysForSeq.iterator();
        Iterator keyIterForAssnId = keysForAssnId.iterator();
        Iterator keyIterForHirId = keysForHirId.iterator();
        
        benefitHierarchyVO.setBenefitComponentId(getBenefitComponentSessionObject().getBenefitComponentId());
        benefitHierarchyVO.setBenefitComponentParentId(getBenefitComponentSessionObject().getBenefitComponentParentId());
		benefitHierarchyVO.setName(getBenefitComponentSessionObject().getBenefitComponentName());
		benefitHierarchyVO.setMasterVersion(getBenefitComponentSessionObject().getBenefitComponentVersionNumber());
		benefitHierarchyVO.setBusinessDomainList(getBenefitComponentSessionObject().getBusinessDomainList());
		List benefitHierarchies = new ArrayList(keysForAssnId==null?0:keysForAssnId.size());
		
		Object assnIdKey,sequenceKey,assnIdValue,sequenceValue,hirIdKey,hirIdValue;
		
		while(keyIterForAssnId.hasNext()){
			assnIdKey = keyIterForAssnId.next();
			sequenceKey = keyIterForSeq.next();
			hirIdKey = keyIterForHirId.next();
			assnIdValue = this.getHiddenValHirAssnId().get(assnIdKey);
			sequenceValue = this.getHiddenValMapSeq().get(sequenceKey);
			String seqNum = (String)sequenceValue;
			hirIdValue = this.getHiddenValHirerarchyId().get(hirIdKey);
			BenefitHierarchyAssociationVO benefitHierarchyAssociationVO = new BenefitHierarchyAssociationVO();
			benefitHierarchyAssociationVO.setBenefitComponentId(benefitHierarchyVO.getBenefitComponentId());
			if(null != assnIdValue)
				benefitHierarchyAssociationVO.setBenefitHierarchyAssociationId((new Integer((String)assnIdValue)).intValue());
			if(null != sequenceValue && seqNum.matches("[\\d]+")){ 
				benefitHierarchyAssociationVO.setSequenceNumber((new Integer((String)sequenceValue)).intValue());
			}else{
				benefitHierarchyAssociationVO.setSequenceNumber(0);
			}		
			if(null != hirIdValue){
				benefitHierarchyAssociationVO.setBenefitHierarchyId((new Integer((String)hirIdValue)).intValue());
			}
			benefitHierarchies.add(benefitHierarchyAssociationVO);
		}		
		benefitHierarchyVO.setBenefitHierarchiesList(benefitHierarchies);
		return benefitHierarchyVO;
	}
	/**
	 * Method to search Benefit hierarchies
	 * @param identifier
	 * @return
	 */
	 public String searchBenefitHierarchies(String identifier){
	 	BenefitHierarchySearchResponse benefitHierarchySearchResponse = null;
    	BenefitHierarchySearchRequest benefitHierarchySearchRequest = (BenefitHierarchySearchRequest)
			this.getServiceRequest(ServiceManager.SEARCH_BENEFIT_HIEARARCHY_REQUEST);
    	benefitHierarchySearchRequest.getBenefitHierarchyVO().setBenefitComponentId(getBenefitComponentSessionObject().getBenefitComponentId());
    	benefitHierarchySearchRequest.getBenefitHierarchyVO().setBusinessDomainList(getBenefitComponentSessionObject().getBusinessDomainList());
    	benefitHierarchySearchRequest.setAction(identifier);
    	benefitHierarchySearchResponse = (BenefitHierarchySearchResponse)this.executeService(benefitHierarchySearchRequest);
    	if(null != benefitHierarchySearchResponse){
    		if("benefitHierarchy".equals(identifier)){
    			benefitHierarchySearchResponse.setMessages(null);
    			this.getRequest().setAttribute("messages", null);
    			this.setBenefitHierarchyVO(benefitHierarchySearchResponse.getBenefitHierarchyVO());
    			if(null != this.getBenefitHierarchyVO()){
    				if(null != this.getBenefitHierarchyVO().getBenefitHierarchiesList()){
    					registerSequence(this.getBenefitHierarchyVO().getBenefitHierarchiesList());
    					this.setCreateFlag(false);
    				}
    				this.setBenefitHierarchyId(this.getBenefitHierarchyVO().getBenefitHierarchyId());
    			}
    		}
    		else if("benefitHierarchyView".equals(identifier)){
    			String name = getBenefitComponentSessionObject().getBenefitComponentName();
				this.setBreadCrumbText("Product Configuration >> Benefit Component " + "(" + name + ") >> View");
    		}
    		else if("print".equals(identifier))
    		{
    			String name = getBenefitComponentSessionObject().getBenefitComponentName();
    			this.setBreadCrumbText("Product Configuration >> Benefit Component " + "(" + name + ") >> View");
    			 			
    		}
    		this.setStatus(this.getBenefitComponentSessionObject().getStatus());
			this.setVersion(this.getBenefitComponentSessionObject().getVersion());
			this.setState(this.getBenefitComponentSessionObject().getComponentState());
			if(benefitHierarchySearchResponse.getBenefitHierarchyVO()!= null){
				this.setBenefitHierarchies(benefitHierarchySearchResponse.getBenefitHierarchyVO().getBenefitHierarchiesList());
				this.setBenefitHierarchyVO(benefitHierarchySearchResponse.getBenefitHierarchyVO());
				Collections.sort(benefitHierarchies);   
			}
    	//	this.setBenefitHierarchyVO(benefitHierarchySearchResponse.getBenefitHierarchyVO());
    	}	
    	return identifier;
    }
	 
	 /**
	  * method to get the benefitComponentSessionObject.
	  * @return benefitComponentSessionObject.
	  */
    protected BenefitComponentSessionObject getBenefitComponentSessionObject(){
    	BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject)
		getSession().getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
		if(benefitComponentSessionObject == null) {
			benefitComponentSessionObject = new BenefitComponentSessionObject();
			getSession().setAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY,benefitComponentSessionObject);
		}
		return benefitComponentSessionObject;
	}
    
    /**
      *Method to load benefit hierarchy tab
      *  
      */
    public String loadBenefitHierarchyTab(){
    	return this.searchBenefitHierarchies("benefitHierarchy");
    }
    /**
     *Method to load benefit hierarchy tab
     *  
     */
   public String loadBenefitHierarchyTabPrint(){
   	return this.searchBenefitHierarchies("print");
   }   
    /**
     *Method to load benefit hierarchy tab
     *  
     */
    public String loadBenefitHierarchyTabView(){
    	String identifier = this.searchBenefitHierarchies("benefitHierarchyView");
    	return identifier;
    }
    
	/**
	 * @return Returns the benefit hierarchies.
	 */
	public List getBenefitHierarchies() {
		loadBenefitHierarchyTabPrint();		
		return benefitHierarchies;
	}
	
	/**
	 * @param benefitHierarchies The benefit hierarchies to set.
	 */
	public void setBenefitHierarchies(List benefitHierarchies) {
		this.benefitHierarchies = benefitHierarchies;
	}
	
	/**
	 * @return Returns String
	 */
	public String getBenefitComponentName() {
		return this.getBenefitComponentSessionObject().getBenefitComponentName();
	}
	
	/**
	 * @param benefitComponentName The benefitComponentName to set.
	 */
	public void setBenefitComponentName(String benefitComponentName) {
		this.benefitComponentName = benefitComponentName;
	}
	
	/**
	 * @return Returns the benefitHierarchies.
	 */
	public List getBenefitHierarchiesForPrint() {
		benefitHierarchies = new ArrayList(1);		
 		this.searchBenefitHierarchies("print");	
		return benefitHierarchies;
	}
	
	/**
	 * 
	 * @param benefitComponentName The benefitHierarchiesForPrint to set.
	 */
	public void setBenefitHierarchiesForPrint(){
		
	}
	
	/**
	 * Action Method for Checkin and validation 
	 * @return String
	 * 
	 */
	public String done(){
		updateBenefitHierarchy();
		BenefitHierarchyVO benefitHierarchyVO = null;
		this.doneFlag = true;
		if(this.isNotesFlag()){
			this.setCreateFlag(false);
		}
	    return this.createBenefitHierarchies(benefitHierarchyVO);
	}
	
	/**
	 * Action Method for Checkin and validation from notes tab
	 * @return String
	 * 
	 */
	public String doneFromNotesTab(){
		Application application = FacesContext.getCurrentInstance().getApplication();
		BenefitComponentNotesBackingBean backingBean =  ((BenefitComponentNotesBackingBean) application.createValueBinding("#{BenefitComponentNotesBackingBean}").getValue(FacesContext.getCurrentInstance()));
		backingBean.AttachNotesForBenefitComponent();
		backingBean.setRequiredNoteName(false);
		//updateBenefitHierarchy();
		BenefitHierarchyVO benefitHierarchyVO = null;
		this.doneFlag = true;
		if(this.isNotesFlag()){
			this.setCreateFlag(false);
		}
	    String returnString = this.createBenefitHierarchies(benefitHierarchyVO);
	    return returnString;
	}
		
	/**
	 * @return Returns the returnString.
	 */
	public String getReturnString() {
		return returnString;
	}
	
	/**
	 * @param returnString The returnString to set.
	 */
	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	/**
     * 
     * @param benefitHierarchies
     */
    private void registerSequence(List benefitHierarchies) {
        SequenceUtil sequenceUtil = new SequenceUtil();
        sequenceUtil.registerObjects(benefitHierarchies,
                "benefitHierarchyAssociationId", "sequenceNumber");
    }
    
	/**
	 * Returns the notesFlag
	 * @return boolean notesFlag.
	 */
	public boolean isNotesFlag() {
		return notesFlag;
	}
	/**
	 * Sets the notesFlag
	 * @param notesFlag.
	 */
	public void setNotesFlag(boolean notesFlag) {
		this.notesFlag = notesFlag;
	}
	/**
	 * @return Returns the benefitHeaderViewPanel.
	 */
	public HtmlPanelGrid getBenefitHeaderViewPanel() {
		HtmlPanelGrid viewPanel = new HtmlPanelGrid();
		viewPanel.setWidth("100%");
		viewPanel.setColumns(3);
		viewPanel.setBorder(0);
		viewPanel.setBgcolor("#cccccc");
		viewPanel.setCellpadding("2");
		viewPanel.setCellspacing("1");
		//new

		HtmlCommandButton updateButton = new HtmlCommandButton();
		updateButton.setId("Update");
		updateButton.setStyleClass("wpdbutton");
		updateButton.setValue("Update");
		updateButton.setOnmousedown("javascript:savePageAction(this.id);");
		updateButton.setTabindex("3");
		
		MethodBinding updateMetBind = FacesContext
		.getCurrentInstance()
		.getApplication()
		.createMethodBinding(
				"#{BenefitComponentHierarchiesBackingBean.updateBenefitHierarchy}",
				new Class[] {});
		updateButton.setAction(updateMetBind);
			
		HtmlOutputText nameOutputText = new HtmlOutputText();
		HtmlOutputText deleteOutputText = new HtmlOutputText();
		
		nameOutputText.setValue("Name");
		nameOutputText.setId("Name");
		nameOutputText.setStyleClass("dataTableHeader");
	
		deleteOutputText.setValue("Delete");
		deleteOutputText.setId("Delete");
		deleteOutputText.setStyleClass("dataTableHeader");
		
		viewPanel.getChildren().add(updateButton);
		viewPanel.getChildren().add(nameOutputText);
		viewPanel.getChildren().add(deleteOutputText);
		return viewPanel;
	}
	/**
	 * @param benefitHeaderViewPanel The benefitHeaderViewPanel to set.
	 */
	public void setBenefitHeaderViewPanel(HtmlPanelGrid benefitHeaderViewPanel) {
		this.benefitHeaderViewPanel = benefitHeaderViewPanel;
	}
	
	
    /**
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Product Configuration >> Benefit Component ("+this.getBenefitComponentSessionObject().getBenefitComponentName()+") >> Print";
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
	 * @return Returns the loadBenefitHierarchy.
	 */
  //WAS 7.0 Changes - Binding variable rootQuestionLoad modified to HtmlInputHidden instead of String, Since getter method call failed,

 // while the variable defined in String in WAS 7.0


	public HtmlInputHidden getLoadBenefitHierarchy() {
		loadBenefitHierarchyTab();
		return loadBenefitHierarchy;
	}
	/**
	 * @param loadBenefitHierarchy The loadBenefitHierarchy to set.
	 */
	public void setLoadBenefitHierarchy(HtmlInputHidden loadBenefitHierarchy) {
		this.loadBenefitHierarchy = loadBenefitHierarchy;
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
	 * @return Returns the benefitComponentSysId.
	 */
	public int getBenefitComponentSysId() {
		return benefitComponentSysId;
	}
	/**
	 * @param benefitComponentSysId The benefitComponentSysId to set.
	 */
	public void setBenefitComponentSysId(int benefitComponentSysId) {
		this.benefitComponentSysId = benefitComponentSysId;
	}
}
