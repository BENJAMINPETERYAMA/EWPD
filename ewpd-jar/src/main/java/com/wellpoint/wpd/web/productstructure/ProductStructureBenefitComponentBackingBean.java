/*
 * ProductStructureBenefitComponentBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or 
 * use Confidential Information without the express written
 *  agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.productstructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureAssociatedBenefit;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureAssociatedBenefitComponent;
import com.wellpoint.wpd.common.productstructure.request.DeleteProductStructureRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveBenefitComponentRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveProdStructureComponentHierarchyRequest;
import com.wellpoint.wpd.common.productstructure.request.SaveProductStructureBenefitComponentRequest;
import com.wellpoint.wpd.common.productstructure.response.DeleteProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveBenefitComponentResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveProdStructureComponentHierarchyResponse;
import com.wellpoint.wpd.common.productstructure.response.SaveProductStructureResponse;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.framework.util.SequenceUtil;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing bean for benefitcomponent tab.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBenefitComponentBackingBean extends
        ProductStructureBackingBean {

    //private ProductStructureVO productStructureVO;

    //private int id;

    private String name;

    private int benefitComponentId;

    private List benefitComponentList = null;

    private int deleteBenefitComponentId;

    private String selectedBenefitComponents;

    private List selectBenefitComponents;

    private String Status;

    private String State;

    private int version;

    private HtmlPanelGrid panel = null;

    private Map datafieldMapForSequenceNum = new LinkedHashMap();

    private Map datafieldMapForId = new LinkedHashMap();

    private Map datafieldMapForName = new LinkedHashMap();

    private HtmlPanelGrid headerPanel = null;

    private String title = "Associated Benefit Components";

    private String loadComponent;

    private List validationMessages = null;

    private String printBreadCrumbText;

    private boolean checkInSuccess = false;
    
    private List messageList = new ArrayList(4);
    
    private boolean hasValidationErrors;
    
    private String primaryCode;
    
    private String componentHierarchy;
    
    private List hierarchyList = null;
    
    private int hiddenBenefitComponentId;
    
    private boolean checkInFlag;

//  added for mulitiple adminoption delete
	private Map hiddenValDeleteBnftCmpntId = new HashMap();
	
	private List saveMessages = null;
	
	private List updatedMessages = null;
	
	private boolean updateStatus = true;

    /**
     * Constructor.
     *  
     */
    public ProductStructureBenefitComponentBackingBean() {

        this.setBreadCrumbTextForPS();
        hiddenBenefitComponentId = getProductStructureSessionObject().getId();
    }


    /**
     * For displying benefit component tab.
     * 
     * @return String
     */
    public String load() {
        Logger.logInfo("Entering the method for loading benefit component");
        loadBenefitComponent();
        if (benefitComponentList != null && benefitComponentList.size() == 0) {
            benefitComponentList = null;
        }
        Logger.logInfo("Returning the method for loading benefit component");
        return "success";
    }


    /**
     * Sets selectBenefitComponents.
     * 
     * @param selectBenefitComponents
     *  
     */
    public void setSelectBenefitComponents(List selectBenefitComponents) {
        this.selectBenefitComponents = selectBenefitComponents;
    }


    /**
     * Returns the selectedBenefitComponents.
     * 
     * @return String
     */
    public String getSelectedBenefitComponents() {
        return selectedBenefitComponents;
    }


    /**
     * Sets the selectedBenefitComponents.
     * 
     * @param selectedBenefitComponents
     *  
     */
    public void setSelectedBenefitComponents(String selectedBenefitComponents) {
        this.selectedBenefitComponents = selectedBenefitComponents;
    }


    /**
     * Returns the benefitComponentList.
     * 
     * @return List
     */
    public List getBenefitComponentList() {
        return benefitComponentList;
    }


    /**
     * Sets the benefitComponentList.
     * 
     * @param benefitComponentList
     *  
     */
    public void setBenefitComponentList(List benefitComponentList) {
        this.benefitComponentList = benefitComponentList;
    }


    /**
     * This method is used to get the Benefit Components of selected Product
     * Structure.
     * 
     * @return success String
     */

    public String loadBenefitComponent() {  
        Logger.logInfo("Entering the method for loading benefit component");
        RetrieveBenefitComponentResponse retrieveBenefitComponentResponse = null;
        RetrieveBenefitComponentRequest retrieveBenefitComponentRequest = getRetrieveBenefitComponentRequest();
        retrieveBenefitComponentResponse = (RetrieveBenefitComponentResponse) this
                .executeService(retrieveBenefitComponentRequest);
        if (null != retrieveBenefitComponentResponse) {
        	
        	if(null != validationMessages && !validationMessages.isEmpty())
        		messageList.addAll(validationMessages);
            if(null != messageList && !messageList.isEmpty()){
            	addAllMessagesToRequest(messageList);
            }
            this.selectBenefitComponents = retrieveBenefitComponentResponse
                    .getProductStructureBO()
                    .getAssociatedBenefitComponentList();
            this.benefitComponentList = this.selectBenefitComponents;
            SequenceUtil util = new SequenceUtil();
            util.registerObjects(benefitComponentList, "benefitComponentId",
                    "sequenceNum");
            getValues();
            this.name = retrieveBenefitComponentResponse
                    .getProductStructureBO().getProductStructureName();
        }
        
        Logger.logInfo("Returning the method for loading benefit component");
        return "success";
    }


    /**
     * For getting state,status and version from session.
     * 
     * @return String
     */
    public String getValues() {
        this.State = this.getStateFromSession();
        this.Status = this.getStatusFromSession();
        this.version = this.getVersionFromSession();
        this.name = this.getNameFromSession();
        return "";
    }


    /**
     * Setter of values.
     *  
     */
    public void setValues() {

    }


    /**
     * This method is used to get the RetrieveBenefitComponentRequest.
     * 
     * @return RetrieveBenefitComponentRequest
     */

    private RetrieveBenefitComponentRequest getRetrieveBenefitComponentRequest() {
        RetrieveBenefitComponentRequest retrieveBenefitComponentRequest = (RetrieveBenefitComponentRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_BENEFIT_COMPONENT_PS);
        ProductStructureVO productStructureVO = new ProductStructureVO();
        productStructureVO = getProductStructureFromSession(productStructureVO);
        retrieveBenefitComponentRequest
                .setProductStructureVO(productStructureVO);
        return retrieveBenefitComponentRequest;
    }


    /**
     * gets the DeleteProductStructureRequest.
     * 
     * @return DeleteProductStructureRequest
     */
    private DeleteProductStructureRequest getDeleteProductStructureRequest() {
        DeleteProductStructureRequest deleteProductStructureRequest = (DeleteProductStructureRequest) this
                .getServiceRequest(ServiceManager.DELETE_PRODUCT_STRUCTURE);
        deleteProductStructureRequest
                .setProductStructureVO(getDeleteProductStructureVO());
        deleteProductStructureRequest
                .setBenefitComponentId(this.deleteBenefitComponentId);
        deleteProductStructureRequest
                .setAction(DeleteProductStructureRequest.DELETE_BENEFIT_COMPONENT);
        return deleteProductStructureRequest;
    }


    /**
     * Returns the productStructureVO.
     * 
     * @return ProductStructureVO productStructureVO.
     */

    public ProductStructureVO getDeleteProductStructureVO() {
        ProductStructureVO productStructureVO = new ProductStructureVO();
        productStructureVO = getProductStructureFromSession(productStructureVO);
        return productStructureVO;
    }


    /**
     * Retrurns the deleteBenefitComponentVO.
     * 
     * @return ProductStructureAssociatedBenefitComponent
     */
    /*private ProductStructureAssociatedBenefitComponent getDeleteBenefitComponentVO() {
        ProductStructureAssociatedBenefitComponent productStructureAssociatedBenefitComponent = new ProductStructureAssociatedBenefitComponent();
        productStructureAssociatedBenefitComponent
                .setBenefitComponentId(this.deleteBenefitComponentId);
        productStructureAssociatedBenefitComponent.setProductStructureId(this
                .getIdFromSession());
        return productStructureAssociatedBenefitComponent;
    }*/


    /**
     * This method is used to delete the selected Benefit Component from the
     * existing Product Structure Benefit Component.
     * 
     * @return success String
     */
    public String deleteBenefitComponent() {

    	if (null == this.selectedBenefitComponents || "".equals(this.selectedBenefitComponents)) {
        	getRequest().setAttribute("RETAIN_Value", "");
        }
        Logger.logInfo("Entering the method for deleting benefit component");
        /*
		 * added for multile delete
		 */
        Map map=this.getHiddenValDeleteBnftCmpntId();
		Map bnftCmpntIdMap=this.getHiddenValDeleteBnftCmpntId();
		List bnftCmpntIdList = new ArrayList(bnftCmpntIdMap == null ? 0: bnftCmpntIdMap.size());
		Iterator bnftCmpntIdIt = bnftCmpntIdMap.entrySet().iterator();
		 while (bnftCmpntIdIt.hasNext()) {
	        Map.Entry pairs = (Map.Entry)bnftCmpntIdIt.next();
	        if((Boolean)(pairs.getValue())==Boolean.TRUE){
	        	bnftCmpntIdList.add(pairs.getKey()) ;
	        }
	    }	
        DeleteProductStructureRequest deleteProductStructureRequest = getDeleteProductStructureRequest();
        DeleteProductStructureResponse deleteProductStructureResponse = null;
        /*
		 * modified for multile delete
		 */
        if(null!=bnftCmpntIdList && bnftCmpntIdList.size()>0){
        deleteProductStructureRequest.setBenefitComponentList(bnftCmpntIdList);
        deleteProductStructureResponse = (DeleteProductStructureResponse) this
                .executeService(deleteProductStructureRequest);
       
        if (deleteProductStructureResponse != null) {
            this.benefitComponentList = deleteProductStructureResponse
                    .getProductStructureBO()
                    .getAssociatedBenefitComponentList();
            //Code change for product structure tree rendering optimization
        	super.updateTreeStructure();
        	
            if (benefitComponentList != null
                    && benefitComponentList.size() == 0) {
                benefitComponentList = null;
            }
            messageList = deleteProductStructureResponse.getMessages();
            addAllMessagesToRequest(messageList);
        }
        }
        Logger.logInfo("Returning the method for deleting benefit component");
        return "deletedBenefitComponent";
    }


    /**
     * This method is used to save the newly selected set of Benefit Components
     * to the existing Product Structure Benefit Components.
     * 
     * @return success String
     */
    public String saveBenefitComponentList() {

    	getRequest().setAttribute("RETAIN_Value", "");   
        Logger.logInfo("Entering the method for saving benefit component list");
        if (selectedBenefitComponents == null
                || selectedBenefitComponents.equals("")
                || selectedBenefitComponents.trim().length() == 0) {
        	if(!this.checkInFlag){
	            validationMessages = new ArrayList(1);
	            this.validationMessages.add(new ErrorMessage(
	                    WebConstants.COMPONENT_NOT_ATTACHED));
	            addAllMessagesToRequest(this.validationMessages);
        	}
            return "success";
        }
        ProductStructureVO productStructureVO = getProductStructureVO();
        SaveProductStructureBenefitComponentRequest saveProductStructureRequest = (SaveProductStructureBenefitComponentRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT_STRUCTURE_COMPONENT);
        saveProductStructureRequest.setProductStructureVO(productStructureVO);
        saveProductStructureRequest
                .setAction(SaveProductStructureBenefitComponentRequest.CREATE_PRODUCT_STRUCTURE_BENEFIT);
        SaveProductStructureResponse saveProductStructureResponse = (SaveProductStructureResponse) this
                .executeService(saveProductStructureRequest);
        Logger.logInfo("Returning the method for saving benefit component "
                + "list");
        if (saveProductStructureResponse != null) {
            this.selectedBenefitComponents = "";
            this.benefitComponentList = saveProductStructureResponse
                    .getProductStructure().getAssociatedBenefitComponentList();
            //Code change for product structure tree rendering optimization
        	super.updateTreeStructure();
        	
            SequenceUtil util = new SequenceUtil();
            util.registerObjects(benefitComponentList, "benefitComponentId",
                    "sequenceNum");
            this.name = saveProductStructureResponse.getProductStructure()
                    .getProductStructureName();
            messageList = saveProductStructureResponse.getMessages();
            
            if(null!=messageList && !messageList.isEmpty()){
            	saveMessages = new ArrayList();
            	saveMessages = messageList;
            }
            
            addAllMessagesToRequest(messageList);

        }
        return "success";
    }


    /**
     * Creating ProductStructureVo object.
     * 
     * @return ProductStructureVO.
     */
    private ProductStructureVO getProductStructureVO() {
        ProductStructureVO productStructureVO = new ProductStructureVO();
        List componentName = WPDStringUtil.getListFromTildaString(
                selectedBenefitComponents, 2, 2, 2);
        List componentId = WPDStringUtil.getListFromTildaString(
                selectedBenefitComponents, 2, 1, 1);
        List benefitCOmpnentList = new ArrayList(componentName == null ? 0:componentName.size());
        ProductStructureAssociatedBenefitComponent associatedBenefitComponent = null;
        int productStructureId = getIdFromSession();
        productStructureVO = getProductStructureFromSession(productStructureVO);
        for (int i = 0; i < componentName.size(); i++) {
            String name = (String) componentName.get(i);
            List locationList = getLocation(componentName, name);
            if (locationList != null) {
                for (int j = 0; j < locationList.size(); j++) {
                    int location = ((Integer) locationList.get(j)).intValue();
                    componentName.remove(location);
                    componentId.remove(location);
                }
            }
            associatedBenefitComponent = new ProductStructureAssociatedBenefitComponent();
            associatedBenefitComponent
                    .setBenefitComponentId(((Integer) componentId.get(i))
                            .intValue());
            associatedBenefitComponent
                    .setProductStructureId(productStructureId);
            associatedBenefitComponent.setName((String) componentName.get(i));
            benefitCOmpnentList.add(associatedBenefitComponent);
        }
        productStructureVO.setBenefitComponentList(benefitCOmpnentList);
        return productStructureVO;
    }


    /**
     * 
     * @param componentList
     * @param name
     * @return locationList
     */
    private List getLocation(List componentList, String name) {
        List locationList = new ArrayList(componentList == null ? 0:componentList.size());
        if (componentList != null) {
            int count = 0;
            for (int i = 0; i < componentList.size(); i++) {
                String listName = (String) componentList.get(i);
                if (count > 0 && listName.equals(name)) {
                    locationList.add(new Integer(i));
                }
                if (listName.equals(name)) {
                    count++;
                }
            }
        }
        return locationList;
    }


    /**
     * This method is used to update the sequence of display of Benefit
     * Components of the existing Product Structure Benefit Components.
     * 
     * @return success String
     */
    public String updateBenefitComponentList() {
    	Logger.logInfo("Entering the method for updating benefit "
                + "component list");
        benefitComponentList = createBenefitComponentList();
        if (null == this.selectedBenefitComponents || "".equals(this.selectedBenefitComponents)) {
        	getRequest().setAttribute("RETAIN_Value", "");
        }
        if (benefitComponentList != null) {
        	SequenceUtil util = new SequenceUtil();
            util.reOrderObjects(benefitComponentList);
            ProductStructureVO productStructureVO = new ProductStructureVO();
            productStructureVO = getProductStructureFromSession(productStructureVO);
            productStructureVO.setBenefitComponentList(benefitComponentList);
            SaveProductStructureBenefitComponentRequest saveProductStructureBnftRequest = (SaveProductStructureBenefitComponentRequest) this
                    .getServiceRequest(ServiceManager.SAVE_PRODUCT_STRUCTURE_COMPONENT);
            saveProductStructureBnftRequest
                    .setAction(SaveProductStructureBenefitComponentRequest.UPDATE_PRODUCT_STRUCTURE_BENEFIT);
            saveProductStructureBnftRequest
                    .setProductStructureVO(productStructureVO);
            SaveProductStructureResponse saveProductStructureResponse = (SaveProductStructureResponse) this
                    .executeService(saveProductStructureBnftRequest);
            benefitComponentList = saveProductStructureResponse
                    .getProductStructure().getAssociatedBenefitComponentList();
            
            messageList = saveProductStructureResponse.getMessages();
            
            if(null!=messageList && !messageList.isEmpty()){
            	updatedMessages = new ArrayList();
            	updatedMessages = messageList;
            }
            
            addAllMessagesToRequest(messageList);
            /*String name = saveProductStructureResponse.getProductStructure()
                    .getProductStructureName();*/
            Logger.logInfo("Returning the method for updating benefit "
                    + "component list");
            return "success";
        }
        return "success";

    }


    /**
     * @return List.
     */
    private List createBenefitComponentList() {
    	Logger.logInfo("Entering the method for creating benefit "
            + "component list");
	    List benefitComponentList = new ArrayList(datafieldMapForId == null ? 0: datafieldMapForId.size());
	  
	    Set idSet = datafieldMapForId.keySet();
	    
	    Iterator idIterator = idSet.iterator();
	    Set sequenceSet = datafieldMapForSequenceNum.keySet();
	    boolean generalBenefitFlag = false;
	    boolean generalProvisionFlag = false;
	    boolean supBenefitFlag = false;
	    boolean sequenceValid = true;
	    int sequenceNumber = 0;
	    int validSeqNum = 0;

	    int productStructureId = getIdFromSession();
	    
	    //The loop for getting maximum seq number.
        //Also checks for the occurence of GB,GP or SB.
	    while(idIterator.hasNext()){
	        Long identifier = (Long) idIterator.next();
	        String componentName = (String) datafieldMapForName.get(identifier);
	         if (componentName.equals(WebConstants.GENERAL_BENEFITS)){ 
	            generalBenefitFlag = true;
	            validSeqNum = 1;
	         }
	         if (componentName.equals(WebConstants.GENERAL_PROVISIONS)){
	         	generalProvisionFlag = true;
	         	if(generalBenefitFlag){
	         		validSeqNum = 2;
	         	}else{
	         		validSeqNum = 1;
	         	}
	         }
	         if (componentName.equals(WebConstants.SUPPLEMENTAL_BENEFITS)){ 
                supBenefitFlag = true;
                if(generalBenefitFlag && generalProvisionFlag){
                	validSeqNum = 3;
                }else if(generalBenefitFlag || generalProvisionFlag){
                	validSeqNum = 2;
                }
                else{
                	validSeqNum = 1;
                }
	         }
	    }
	    //Getting max sequence value.
	    int maxSequence =  datafieldMapForSequenceNum.size();

	    //Creating BO for updating sequence.
	    idIterator = idSet.iterator();
	    while (idIterator.hasNext()) {
	        ProductStructureAssociatedBenefitComponent productStructureAssociatedBenefitComponent = new ProductStructureAssociatedBenefitComponent();
	        
	        Long id = (Long) idIterator.next();
	        String componentId = (String) datafieldMapForId.get(id);
	        productStructureAssociatedBenefitComponent
	                .setBenefitComponentId(new Integer(componentId).intValue());
	        
	        String name = (String) datafieldMapForName.get(id);
	        if (name.equals(WebConstants.GENERAL_BENEFITS)) {
	            sequenceNumber = 1;
    	        productStructureAssociatedBenefitComponent.setSequenceNum(sequenceNumber);
    	        productStructureAssociatedBenefitComponent.setProductStructureId(productStructureId);
    	        benefitComponentList.add(productStructureAssociatedBenefitComponent);	                	
	        }else if(name.equals(WebConstants.GENERAL_PROVISIONS)){
	        	if(generalBenefitFlag){
	        		sequenceNumber = 2;
	        	}
	        	else{
	        		sequenceNumber = 1;
	        	}
	        	productStructureAssociatedBenefitComponent.setSequenceNum(sequenceNumber);
    	        productStructureAssociatedBenefitComponent.setProductStructureId(productStructureId);
    	        benefitComponentList.add(productStructureAssociatedBenefitComponent);
	        }else if(name.equals(WebConstants.SUPPLEMENTAL_BENEFITS)){
	        	if(generalBenefitFlag && generalProvisionFlag){
	        		sequenceNumber = 3;
	        	}else if (generalBenefitFlag || generalProvisionFlag){
	        		sequenceNumber = 2 ;
	        	}else{
	        		sequenceNumber = 1;
	        	}
	        	productStructureAssociatedBenefitComponent.setSequenceNum(sequenceNumber);
    	        productStructureAssociatedBenefitComponent.setProductStructureId(productStructureId);
    	        benefitComponentList.add(productStructureAssociatedBenefitComponent);
	        }
	        else {
	            try {
	            	 //Validating the sequence number updation
	                String sequenceNum =  datafieldMapForSequenceNum.get(id).toString();
	                sequenceNumber = new Integer(sequenceNum).intValue();
	                	
	                   if(sequenceNumber == 0 || sequenceNumber > maxSequence){
	                   		sequenceValid = false;
	                   		
	                   		ErrorMessage message = new ErrorMessage(BusinessConstants.ENTERED_SEQUENCE_INVALID);
	                    	message.setParameters(new String[] {"1",(new Integer(maxSequence)).toString()});
	                   		
	                   		messageList.add(message);
	                   		break;
	                   }
		               else if(sequenceNumber <= validSeqNum){
		               	sequenceValid = false;
		            	//Validate when GB,GP & SB is there.
		               	if(validSeqNum == 3){
		               		messageList.add(getMessageGB("1"));
		               		messageList.add(getMessageGP("2"));
		               		messageList.add(getMessageSB("3"));
		               	}else if (validSeqNum == 2){
		               		//Validate when GB & GP is there.
		               		if(generalBenefitFlag && generalProvisionFlag){
		               			messageList.add(getMessageGB("1"));
		               			messageList.add(getMessageGP("2"));
		               		}
		               		//Validate when GB & SB is there.
		               		else if(generalBenefitFlag && supBenefitFlag){
		               			messageList.add(getMessageGB("1"));
		               			messageList.add(getMessageSB("2"));
		               		}else if(generalProvisionFlag && supBenefitFlag){
		               			//Validate when GP & SB is there.
		               			messageList.add(getMessageGP("1"));
		               			messageList.add(getMessageSB("2"));
		               		}
		               	}else if(validSeqNum == 1){
		               		//Validate when GB is there.
		               		if(generalBenefitFlag){
		               			messageList.add(getMessageGB("1"));
		               		}
		               		//Validate when GP is there.
		               		else if(generalProvisionFlag){
		               			messageList.add(getMessageGP("1"));
		               		}
		               		//Validate when SB is there.
		               		else if(supBenefitFlag){
		               			messageList.add(getMessageSB("1"));
		               		}
		               	}
		               	break;
	               }else{
	                	productStructureAssociatedBenefitComponent.setSequenceNum(sequenceNumber);
	                	productStructureAssociatedBenefitComponent.setProductStructureId(productStructureId);
	                	benefitComponentList.add(productStructureAssociatedBenefitComponent);	      
	                }
	            } catch (NumberFormatException exception) {
	                messageList.add(new ErrorMessage("sequence.number.input"+ ".validation"));
	                sequenceValid = false;
	                break;
	            }
	        }
	    }
        if(sequenceValid){
        	Logger.logInfo("Returning the method for creating benefit "+ "component list");
        	addAllMessagesToRequest(messageList);
        	return benefitComponentList;
        }
        else{
        	addAllMessagesToRequest(messageList);
        	return null;
        }
    }

    /*
     * Creating error message for GB
     */
    private ErrorMessage getMessageGB(String sequence){
    	ErrorMessage message = new ErrorMessage(BusinessConstants.ALERT_MESSAGE_GENERAL_BENEFITS);
    	message.setParameters(new String[] {sequence});
    	return message;
    }
    /*
     * Creating error message for GP
     */
    private ErrorMessage getMessageGP(String sequence){
    	ErrorMessage message = new ErrorMessage(BusinessConstants.ALERT_MESSAGE_GENERAL_PROVISIONS);
    	message.setParameters(new String[] {sequence});
    	return message;
    }
    /*
     * Creating error message for SB
     */
    private ErrorMessage getMessageSB(String sequence){
    	ErrorMessage message = new ErrorMessage(BusinessConstants.ALERT_MESSAGE_SUPPLEMENTAL_BENEFITS);
    	message.setParameters(new String[] {sequence});
    	return message;
    }
    /**
     * @return Returns the panel.
     */
    public HtmlPanelGrid getPanel() {
        
    	panel = new HtmlPanelGrid();
    	loadBenefitComponent();
        if (benefitComponentList != null) {
            if (getActionFromSession() != null
                    && (!getActionFromSession().equals("PRINT") && !getActionFromSession()
                            .equals("VIEW"))) {
                panel.setWidth(WebConstants.PANEL_WIDTH_PS);
            } else {
                panel.setWidth(WebConstants.PANEL_WIDTH_PS_VIEW);
            }
            panel.setColumns(3);
            panel.setBorder(0);
            panel.setWidth("650");
            panel.setStyleClass("outputText");
            panel.setCellpadding("3");
            panel.setCellspacing("1");
            panel.setBgcolor("#cccccc");
            for (int i = 0; i < benefitComponentList.size(); i++) {

                ProductStructureAssociatedBenefitComponent benefitComponent = (ProductStructureAssociatedBenefitComponent) benefitComponentList
                        .get(i);

                HtmlOutputLabel lbl = new HtmlOutputLabel();
                lbl.setFor("lblSeq" + i);
                lbl.setId("lblSeq" + i);
                lbl.setTabindex("4");

                HtmlInputHidden hidden = new HtmlInputHidden();
                hidden.setId("id" + i);
                hidden.setValue(new Integer(benefitComponent
                        .getBenefitComponentId()));
                ValueBinding idItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productStructureBenefitComponentBackingBean"
                                        + ".datafieldMapForId[" + i + "]}");
                hidden.setValueBinding("value", idItem);
                HtmlInputText htmlinputText = new HtmlInputText();
                htmlinputText.setId("sequence" + i);
                if ((benefitComponent.getName().equals(WebConstants.GENERAL_BENEFITS))
                		|| ((benefitComponent.getName().equals(WebConstants.GENERAL_PROVISIONS)))
						|| ((benefitComponent.getName().equals(WebConstants.SUPPLEMENTAL_BENEFITS)))){
                    htmlinputText.setStyleClass("sequenceNumberReadOnly");
                } else {
                    htmlinputText.setStyleClass("formInputFieldForSequenceNo");
                }
                
                htmlinputText.setValue(new Integer(benefitComponent
                        .getSequenceNum()));
                ValueBinding seqItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productStructureBenefitComponentBackingBean"
                                        + ".datafieldMapForSequenceNum[" + i
                                        + "]}");
                htmlinputText.setValueBinding("value", seqItem);
                htmlinputText.setSize(1);
                htmlinputText.setMaxlength(3);
                htmlinputText.setOnkeydown("return isNumberKey(event);return "
                        + "false;");
                //htmlinputText.setTabindex("4");
                HtmlOutputText htmlOutputText2 = new HtmlOutputText();
                htmlOutputText2.setStyleClass("mandatoryNormal");
                htmlOutputText2.setId("name" + i);
                htmlOutputText2.setValue(benefitComponent.getName());

                HtmlInputHidden hiddenName = new HtmlInputHidden();
                hiddenName.setId("hiddenName" + i);
                hiddenName.setValue(benefitComponent.getName());
                ValueBinding hiddenNameItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productStructureBenefitComponentBackingBean"
                                        + ".datafieldMapForName[" + i + "]}");
                hiddenName.setValueBinding("value", hiddenNameItem);

                HtmlOutputLabel lbl1 = new HtmlOutputLabel();
                lbl1.setId("lbl1" + i);
                lbl1.setFor("lbl1" + i);

                /*
        		 * modifeid  for multile benefit component  delete
        		 */
                HtmlSelectBooleanCheckbox deleteCheckBox = new HtmlSelectBooleanCheckbox();
                deleteCheckBox.setId("deleteBtn" + i);
                ValueBinding valBindingForDeleteBnftCmpntId = FacesContext
				.getCurrentInstance().getApplication()
				.createValueBinding(
						"#{productStructureBenefitComponentBackingBean.hiddenValDeleteBnftCmpntId["
								+ benefitComponent.getBenefitComponentId() + "]}");
//				added for mulitiple benefit component  delete		
				deleteCheckBox.setValueBinding("value",valBindingForDeleteBnftCmpntId);
				deleteCheckBox.setOnclick("enableDisableDelete('prodStructureForm:panelTable', 2, 0, 'prodStructureForm:deleteButton')");
				deleteCheckBox.setTabindex("4");
                lbl.getChildren().add(htmlinputText);
                lbl.getChildren().add(hidden);
                lbl1.getChildren().add(htmlOutputText2);
                lbl1.getChildren().add(hiddenName);
                panel.getChildren().add(lbl);
                panel.getChildren().add(lbl1);
                panel.getChildren().add(deleteCheckBox);

            }

        } else {
            panel.setRendered(false);
        }

        return panel;
    }


    /**
     * @param panel
     *            The panel to set.
     */
    public void setPanel(HtmlPanelGrid panel) {
        this.panel = panel;
    }


    /**
     * @return Returns the headerPanel.
     */
    public HtmlPanelGrid getHeaderPanel() {
        headerPanel = new HtmlPanelGrid();
        if (benefitComponentList != null) {
            if (getActionFromSession() != null
                    && (!getActionFromSession().equals("PRINT") && !getActionFromSession()
                            .equals("VIEW"))) {
                headerPanel.setWidth(WebConstants.PANEL_WIDTH_PS);
            } else {
                headerPanel.setWidth(WebConstants.PANEL_WIDTH_PS_VIEW);
            }
            headerPanel.setColumns(3);
            headerPanel.setBorder(0);
            headerPanel.setWidth("650");
            headerPanel.setCellpadding("3");
            headerPanel.setCellspacing("1");
            headerPanel.setBgcolor("#cccccc");
            headerPanel.setStyleClass("dataTableHeader");
            HtmlOutputText otxtType1 = new HtmlOutputText();
            otxtType1.setValue("Benefit Component");
            otxtType1.setId("BenefitComponent");
            HtmlCommandButton htmlCommandButton = new HtmlCommandButton();
            htmlCommandButton.setId("Update");
            htmlCommandButton.setOnmousedown("javascript:savePageAction(this.id);");
            htmlCommandButton.setValue("Update");
            htmlCommandButton.setTabindex("3");
            MethodBinding mb = FacesContext.getCurrentInstance()
                    .getApplication().createMethodBinding(
                            "#{productStructureBenefitComponentBackingBean"
                                    + ".updateBenefitComponentList}",
                            new Class[] {});
            htmlCommandButton.setAction(mb);
            htmlCommandButton.setDisabled(false);
            htmlCommandButton.setStyleClass("wpdButton");
            HtmlOutputText otxtType2 = new HtmlOutputText();
            otxtType2.setId("delete");
            otxtType2.setValue("Delete");
            headerPanel.getChildren().add(htmlCommandButton);
            headerPanel.getChildren().add(otxtType1);
            headerPanel.getChildren().add(otxtType2);
        }
        return headerPanel;

    }


    /**
     * @param headerPanel
     *            The headerPanel to set.
     */
    public void setHeaderPanel(HtmlPanelGrid headerPanel) {
        this.headerPanel = headerPanel;
    }


    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }


    /**
     * @param title
     *            The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Returns the datafieldMapForId.
     * 
     * @return Map datafieldMapForId.
     */

    public Map getDatafieldMapForId() {
        return datafieldMapForId;
    }


    /**
     * Sets the datafieldMapForId.
     * 
     * @param datafieldMapForId.
     */

    public void setDatafieldMapForId(Map datafieldMapForId) {
        this.datafieldMapForId = datafieldMapForId;
    }


    /**
     * Returns the datafieldMapForName.
     * 
     * @return Map datafieldMapForName.
     */

    public Map getDatafieldMapForName() {
        return datafieldMapForName;
    }


    /**
     * Sets the datafieldMapForName.
     * 
     * @param datafieldMapForName.
     */

    public void setDatafieldMapForName(Map datafieldMapForName) {
        this.datafieldMapForName = datafieldMapForName;
    }


    /**
     * Returns the datafieldMapForSequenceNum.
     * 
     * @return Map datafieldMapForSequenceNum.
     */

    public Map getDatafieldMapForSequenceNum() {
        return datafieldMapForSequenceNum;
    }


    /**
     * Sets the datafieldMapForSequenceNum.
     * 
     * @param datafieldMapForSequenceNum.
     */

    public void setDatafieldMapForSequenceNum(Map datafieldMapForSequenceNum) {
        this.datafieldMapForSequenceNum = datafieldMapForSequenceNum;
    }


    /**
     * Returns the benefitComponentId.
     * 
     * @return int benefitComponentId.
     */

    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * Sets the benefitComponentId.
     * 
     * @param benefitComponentId.
     */

    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }


    /**
     * Returns the deleteBenefitComponentId.
     * 
     * @return int deleteBenefitComponentId.
     */

    public int getDeleteBenefitComponentId() {
        return deleteBenefitComponentId;
    }


    /**
     * Sets the deleteBenefitComponentId.
     * 
     * @param deleteBenefitComponentId.
     */

    public void setDeleteBenefitComponentId(int deleteBenefitComponentId) {
        this.deleteBenefitComponentId = deleteBenefitComponentId;
    }


    /**
     * Returns the selectBenefitComponents.
     * 
     * @return List selectBenefitComponents.
     */

    public List getSelectBenefitComponents() {
        Logger.logInfo("Entering the method for getting select benefit"
                + " component");
        RetrieveBenefitComponentResponse retrieveBenefitComponentResponse = null;
        RetrieveBenefitComponentRequest retrieveBenefitComponentRequest = getRetrieveBenefitComponentRequest();
        retrieveBenefitComponentResponse = (RetrieveBenefitComponentResponse) this
                .executeService(retrieveBenefitComponentRequest);
        List benefitComponentList = null;
        if (null != retrieveBenefitComponentResponse) {

            if ((null != retrieveBenefitComponentResponse
                    .getProductStructureBO()
                    .getAssociatedBenefitComponentList())
                    && (retrieveBenefitComponentResponse
                            .getProductStructureBO()
                            .getAssociatedBenefitComponentList().size() != 0)) {
                this.selectBenefitComponents = retrieveBenefitComponentResponse
                        .getProductStructureBO()
                        .getAssociatedBenefitComponentList();

            } else {
                this.selectBenefitComponents = null;
            }
            benefitComponentList = this.selectBenefitComponents;
        }
        Logger.logInfo("Returning the method for getting select benefit "
                + "component");
        return benefitComponentList;
    }


    /**
     * Returns the state.
     * 
     * @return String state.
     */

    public String getState() {
        return State;
    }


    /**
     * Sets the state.
     * 
     * @param state.
     */

    public void setState(String state) {
        State = state;
    }


    /**
     * Returns the status.
     * 
     * @return String status.
     */

    public String getStatus() {
        return Status;
    }


    /**
     * Sets the status.
     * 
     * @param status.
     */

    public void setStatus(String status) {
        Status = status;
    }


    /**
     * Returns the version.
     * 
     * @return int version.
     */

    public int getVersion() {
        return version;
    }


    /**
     * Sets the version.
     * 
     * @param version.
     */

    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * @return Returns the loadComponent.
     */
    public String getLoadComponent() {
        loadBenefitComponent();
        return loadComponent;
    }


    /**
     * @param loadComponent
     *            The loadComponent to set.
     */
    public void setLoadComponent(String loadComponent) {
        this.loadComponent = loadComponent;
    }


    /**
     * Returns the name
     * 
     * @return String name.
     */

    public String getName() {
        return name;
    }


    /**
     * Sets the name
     * 
     * @param name.
     */

    public void setName(String name) {
        this.name = name;
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
     * Method to update the order of the sequence & check in the product
     * Structure
     * 
     * @return String
     */
    public String checkIn() {
    	getRequest().setAttribute("RETAIN_Value", "");  
    	this.setCheckInFlag(true);
    	
    	if(selectedBenefitComponents != null
                && !selectedBenefitComponents.equals("")){
    		saveBenefitComponentList();    		
    		updateStatus = false;
    	}else if (updateStatus){
    		updateBenefitComponentList();
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
    	
        Application application = FacesContext.getCurrentInstance()
                .getApplication();

        ProductStructureGeneralInfoBackingBean genInfoBackingBean = ((ProductStructureGeneralInfoBackingBean) application
                .createValueBinding("#{productStructureGeneralInfoBackingBean}")
                .getValue(FacesContext.getCurrentInstance()));
        String returnValue = genInfoBackingBean.checkInGenralInfoFromBC();
        if("validationWait".equals(returnValue)){
			getSession().setAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN,
					"productStructureBenefitComponentBackingBean");
			getSession().setAttribute(WebConstants.OBJECT_VALUE_FOR_CHECKIN,
					this);
			getSession().setAttribute("productStructureGeneralInfoBackingBean",genInfoBackingBean);
        	return returnValue;
        }else if(genInfoBackingBean.isHasValidationErrors()){
        	hasValidationErrors = true;
        	setValuesForAdminMEthodValidation();
        	return "";
        }
        if((null != genInfoBackingBean.getMessages()&& (!genInfoBackingBean.getMessages().isEmpty()))){
        	messageList = genInfoBackingBean.getMessages();     
        	if(null!=saveMessages && !saveMessages.isEmpty()){
        		Iterator iter = saveMessages.iterator();
        		while(iter.hasNext()){
        			messageList.add(iter.next());
        		}
        	}
        	if(null!=updatedMessages && !updatedMessages.isEmpty()){
        		Iterator iter = updatedMessages.iterator();
        		while(iter.hasNext()){
        			messageList.add(iter.next());
        		}
        	}
        	addAllMessagesToRequest(messageList);
        }
        if (checkInSuccess) {
        	getRequest().removeAttribute("RETAIN_Value");
            return "productStructureCheckIn";
        } else
            return "associatedBenefitComponent";
    }


    /**
     * @return checkInSuccess
     * 
     * Returns the checkInSuccess.
     */
    public boolean isCheckInSuccess() {
        return checkInSuccess;
    }


    /**
     * @param checkInSuccess
     * 
     * Sets the checkInSuccess.
     */
    public void setCheckInSuccess(boolean checkInSuccess) {
        this.checkInSuccess = checkInSuccess;
    }
	/**
	 * @return Returns the messageList.
	 */
	public List getMessageList() {
		return messageList;
	}
	/**
	 * @param messageList The messageList to set.
	 */
	public void setMessageList(List messageList) {
		this.messageList = messageList;
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
	 * @return Returns the hiddenValDeleteBnftCmpntId.
	 */
	public Map getHiddenValDeleteBnftCmpntId() {
		return hiddenValDeleteBnftCmpntId;
	}
	/**
	 * @param hiddenValDeleteBnftCmpntId The hiddenValDeleteBnftCmpntId to set.
	 */
	public void setHiddenValDeleteBnftCmpntId(Map hiddenValDeleteBnftCmpntId) {
		this.hiddenValDeleteBnftCmpntId = hiddenValDeleteBnftCmpntId;
	}
	/**
	 * @return Returns the primaryCode.
	 */
	public String getPrimaryCode() {
		return primaryCode;
	}
	/**
	 * @param primaryCode The primaryCode to set.
	 */
	public void setPrimaryCode(String primaryCode) {
		this.primaryCode = primaryCode;
	}
	
	/**
	 * @return Returns the componentHierarchy.
	 */
	public String getComponentHierarchy() {
		RetrieveProdStructureComponentHierarchyRequest request = (RetrieveProdStructureComponentHierarchyRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_COMPONENT_HIERARCHY);
		ProductStructureVO productStructureVO = new ProductStructureVO();
		productStructureVO = this
				.getProductStructureFromSession(productStructureVO);
		request.setProductStructureVO(productStructureVO);

		RetrieveProdStructureComponentHierarchyResponse response = (RetrieveProdStructureComponentHierarchyResponse) this
				.executeService(request);
		if (null != response && null != response.getComponentHierarchyList() && !response.getComponentHierarchyList().isEmpty()) {
			hierarchyList = response.getComponentHierarchyList();
			if (null != hierarchyList && !hierarchyList.isEmpty()
					&& hierarchyList.size() != 0) {
				this.changeList(hierarchyList);
			}
		}

		return componentHierarchy;
	}

	/**
	 * Method to customize the list for print preview page
	 * 
	 * @param hierarchyList
	 */
	private void changeList(List hierarchyList) {
		Map componentMap = new HashMap();
		ProductStructureAssociatedBenefit associatedBenefit = null;
		for (int i = 0; i < hierarchyList.size(); i++) {
			associatedBenefit = (ProductStructureAssociatedBenefit) hierarchyList
					.get(i);
			if (null != associatedBenefit
					&& null != associatedBenefit.getBenefitCmpntDesc()) {
				if (!componentMap.containsValue(associatedBenefit
						.getBenefitCmpntDesc())) {
					componentMap.put(new Integer(associatedBenefit
							.getComponentSeqNo()), associatedBenefit
							.getBenefitCmpntDesc());
				} else {
					associatedBenefit.setBenefitCmpntDesc("");
				}
			}
		}
	}

	/**
	 * @param componentHierarchy
	 *            The componentHierarchy to set.
	 */
	public void setComponentHierarchy(String componentHierarchy) {
		this.componentHierarchy = componentHierarchy;
	}

	/**
	 * @return Returns the hierarchyList.
	 */
	public List getHierarchyList() {
		return hierarchyList;
	}

	/**
	 * @param hierarchyList
	 *            The hierarchyList to set.
	 */
	public void setHierarchyList(List hierarchyList) {
		this.hierarchyList = hierarchyList;
	}
	/**
	 * @return Returns the hiddenBenefitComponentId.
	 */
	public int getHiddenBenefitComponentId() {
		return hiddenBenefitComponentId;
	}
	/**
	 * @param hiddenBenefitComponentId The hiddenBenefitComponentId to set.
	 */
	public void setHiddenBenefitComponentId(int hiddenBenefitComponentId) {
		this.hiddenBenefitComponentId = hiddenBenefitComponentId;
	}
	/**
	 * @return Returns the checkInFlag.
	 */
	public boolean isCheckInFlag() {
		return checkInFlag;
	}
	/**
	 * @param checkInFlag The checkInFlag to set.
	 */
	public void setCheckInFlag(boolean checkInFlag) {
		this.checkInFlag = checkInFlag;
	}
}