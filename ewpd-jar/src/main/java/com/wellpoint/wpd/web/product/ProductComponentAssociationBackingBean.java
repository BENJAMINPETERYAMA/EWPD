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
import java.util.TreeSet;

import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import org.apache.commons.lang.RandomStringUtils;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.product.bo.ProductAssociatedBenefit;
import com.wellpoint.wpd.common.product.bo.ProductComponentBO;
import com.wellpoint.wpd.common.product.request.DeleteProductBenefitComponentRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductBenefitComponentRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductComponentHierarchyRequest;
import com.wellpoint.wpd.common.product.request.SaveProductComponentRequest;
import com.wellpoint.wpd.common.product.request.SaveProductRequest;
import com.wellpoint.wpd.common.product.response.ProductBenefitComponentDeleteResponse;
import com.wellpoint.wpd.common.product.response.ProductBenefitComponentResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductComponentHierarchyResponse;
import com.wellpoint.wpd.common.product.response.SaveProductComponentResponse;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.common.product.vo.ProductComponentVO;
import com.wellpoint.wpd.common.product.vo.ProductKeyObject;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.framework.util.SequenceUtil;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductComponentAssociationBackingBean extends ProductBackingBean {

    private String component = null;

    private String stateOfObject = null;

    private String statusOfObject = null;

    private int versionOfObject = 0;

    private HtmlPanelGrid panel = new HtmlPanelGrid();

    private List benefitComponentList = null;

    private int rowId = 0;

    private Map componentKeyMap = new HashMap();

    private Map sequenceNumberMap = new HashMap();

    private boolean benefitComponentDeleted;

    private String benefitComponentString = null;

    private boolean componentAssociationModified = false;

    private boolean emptyList = true;

    private String printValue;

    private String dummyVar;

    private boolean checkin;

    //private String hiddenInit;
    private HtmlInputHidden hiddenInit= new HtmlInputHidden();
    
    private boolean higherVersion = true;

    private Map componentDescMap = new HashMap();

    private String mandateType = null;
    
    private String hiddenComponent;
    
    private List messageList = new ArrayList();

	private boolean hasValidationErrors;
	
//  added for mulitiple adminoption delete
	private Map hiddenValDeleteBnftCmpntId = new HashMap();
	
	private String componentHierarchy;
	
	private List hierarchyList = null;
	
	private int productEntityId;



    //Setting the BreadCumb.
    public ProductComponentAssociationBackingBean() {
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
        this.productEntityId =super.getProductKeyFromSession();
    }


    /*
     * 
     * Methode for associating benefit component to product
     * 
     *  
     */

    public String done() {
    	getRequest().setAttribute("RETAIN_Value", "");
    	//updateBenefitComponents();
        List componentKeyList = WPDStringUtil.getListFromTildaString(
                benefitComponentString, 2, 1, 1);
        List compNameList = WPDStringUtil.getListFromTildaString(
                benefitComponentString, 2, 2, 2);

        benefitComponentString = "";
        SaveProductComponentRequest saveProductComponentRequest = (SaveProductComponentRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT_COMPONENT);
        saveProductComponentRequest
                .setAction(SaveProductComponentRequest.SAVE_BENEFIT_COMPONENT);
        saveProductComponentRequest.setComponentList(componentKeyList);
        saveProductComponentRequest.setCompNameList(compNameList);
        SaveProductComponentResponse saveProductComponentResponse = null;
        List msgListOne = new ArrayList();
        List msgListTwo = new ArrayList();
        if (null != componentKeyList && componentKeyList.size() != 0) {

            saveProductComponentResponse = (SaveProductComponentResponse) executeService(saveProductComponentRequest);
            if (null != saveProductComponentResponse) {
                this.benefitComponentList = saveProductComponentResponse
                        .getProductComponentList();
            }
            this.componentAssociationModified = true;

            //return "";
        }
        SaveProductRequest saveProductRequest = (SaveProductRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT);
        SaveProductResponse saveProductResponse = null;

        //  if(null != saveProductComponentResponse ){

        if (null != saveProductComponentResponse
                && null != saveProductComponentResponse.getMessages()){
            msgListOne = saveProductComponentResponse.getMessages();
            messageList.addAll(msgListOne);
        }
        //	if(saveProductComponentResponse.isSuccess()){

        //setting action 1 for saving new details.can be done after checking
        // with change in data for edit action.
        // DOES CHECK IN
        
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
        int productStructureId=super.getProductSessionObject().getProductStructKey();
        saveProductRequest.getProduct().setProductKey(
                super.getProductKeyFromSession());
        saveProductRequest.getProduct().setProductName(super.getProductNameFromSession());
        saveProductRequest.getProduct().setProductStructureKey(super.getProductSessionObject().getProductStructKey());
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
            if(null != msgListTwo && msgListTwo.size() != 0)
            messageList.addAll(msgListTwo);
            addAllMessagesToRequest(messageList);
            if(saveProductResponse.getCondition() == SaveProductResponse.VALIDATION_RESULTS){
				hasValidationErrors = true;
				setValuesForAminMethodValidation();
				return "";
			}else if(saveProductResponse.getCondition() == SaveProductResponse.VALIDATION_WAIT){
				getSession().setAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN,
						"productComponentAssociationBackingBean");
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
        
          return "productComponentAssociation";

    }


    /**
     * Returns the component
     * 
     * @return String component.
     */

    public String getComponent() {
        return component;
    }


    /**
     * Sets the component
     * 
     * @param component.
     */

    public void setComponent(String component) {
        this.component = component;
    }


    /**
     * Returns the panel
     * 
     * @return HtmlPanelGrid panel.
     */

    public String loadComponent() {
        RetrieveProductBenefitComponentRequest request = (RetrieveProductBenefitComponentRequest) this
                .getServiceRequest(ServiceManager.PRODUCT_BENEFIT_COMPONENT);
        request
                .setAction(RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_ADDED);
        ProductBenefitComponentResponse productBenefitComponentResponse = (ProductBenefitComponentResponse) executeService(request);
        benefitComponentList = productBenefitComponentResponse
                .getBenefitComponentList();
        this.componentAssociationModified = true;
        this.mandateType = super.getProductTypeFromSession();
//        this.messages = (List)this.getRequest().getAttribute("messages");
        if(null != messageList && !messageList.isEmpty()){
        	addAllMessagesToRequest(messageList);
        }
        if (getProductSessionObject().getMode() == ProductSessionObject.VIEW_MODE)
            return "productComponentAssociationView";
        else 
            return "productComponentAssociation";
    }


    public HtmlPanelGrid getPanel() {
        if (componentAssociationModified) {
            preparePanel(this.getBenefitComponentList());
            register(this.benefitComponentList);
        }
        return panel;
    }


    private void register(List componentBOList) {
        SequenceUtil sequenceUtil = new SequenceUtil();
        sequenceUtil.registerObjects(componentBOList, "componentKey",
                "sequence");
    }


    private List reorder(List componentVOList) {
        SequenceUtil sequenceUtil = new SequenceUtil();
        return sequenceUtil.reOrderObjects(componentVOList);
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
     * This method prepares the panel grid from benefit component list
     * 
     * @param benefitComponentList.
     *            
     */

    public void preparePanel(List benefitComponentList) {
        final String DELETE_IMAGE_PATH = "../../images/delete.gif";
        ProductComponentBO productComponentBO = new ProductComponentBO();
        this.panel = new HtmlPanelGrid();
        if (null != benefitComponentList && benefitComponentList.size() != 0) {
            for (int i = 0; i < benefitComponentList.size(); i++) {
            	
            	productComponentBO = (ProductComponentBO) benefitComponentList.get(i);
            	
                HtmlInputText htmlInputText = new HtmlInputText();
                HtmlOutputText outputText = new HtmlOutputText();
                HtmlInputHidden hidden = new HtmlInputHidden();
                //HtmlCommandButton deleteButton = new HtmlCommandButton();
                HtmlInputHidden componentKey = new HtmlInputHidden();
                componentKey.setId("componentKey"+RandomStringUtils.randomAlphanumeric(15));
                HtmlOutputLabel lblType = new HtmlOutputLabel();
                lblType.setId("lblType"+RandomStringUtils.randomAlphanumeric(15));
                HtmlOutputLabel lblseqTxt = new HtmlOutputLabel();
                lblseqTxt.setId("lblseqTxt"+RandomStringUtils.randomAlphanumeric(15));
                ValueBinding myItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productComponentAssociationBackingBean.componentKeyMap["
                                        + i + "]}");
                componentKey.setValue(new Integer(productComponentBO
                        .getComponentKey()));
                componentKey.setValueBinding("value", myItem);
                
                ValueBinding seqNum = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productComponentAssociationBackingBean.sequenceNumberMap["
                                        + i + "]}");
           //     deleteButton.setValue("Delete");
                
                 
                htmlInputText.setId("sequence" + i);
                if((productComponentBO.getComponentDesc().equals(WebConstants.GENERAL_BENEFITS)) ||
                		(productComponentBO.getComponentDesc().equals(WebConstants.GENERAL_PROVISIONS))
						|| (productComponentBO.getComponentDesc().equals(WebConstants.SUPPLEMENTAL_BENEFITS))){
                	htmlInputText.setStyleClass("sequenceNumberReadOnly");
                   
                } else {
                	htmlInputText.setStyleClass("formInputFieldForSequenceNo");
                }
                
                // changes done for sequence issue while expands tree 
                htmlInputText
                        .setValue(new Integer(productComponentBO.getSequence()));
                htmlInputText.setValueBinding("value", seqNum);
                htmlInputText.setSize(1);
                htmlInputText.setMaxlength(3);
                htmlInputText.setOnkeydown("return isNumberKey(event);return "
                        + "false;");
			
                //hidden.setId("id" + i);
                hidden.setId("hidden"+RandomStringUtils.randomAlphanumeric(15));
                hidden.setValue(new Integer(productComponentBO.getSequence()));
                
                

                
                
                //create a hidden field for the benefit component name
                HtmlInputHidden hiddenBnftComponentName = new HtmlInputHidden();
                hiddenBnftComponentName.setId("hiddenBnftComponentName"+RandomStringUtils.randomAlphanumeric(15));
                //hiddenBnftComponentName.setId("hiddenBnftComponentName" + i);
                ValueBinding hiddenNameItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productComponentAssociationBackingBean.componentDescMap["
                                        + i + "]}");
//              creates a binding for the component name
                hiddenBnftComponentName.setValue(productComponentBO
                        .getComponentDesc());
                hiddenBnftComponentName
                        .setValueBinding("value", hiddenNameItem);
                

                outputText.setValue(productComponentBO.getComponentDesc());
                
//                deleteButton.setOnclick("if(deleteConfirm())getRow('" + i
//                        + "');return false;");
//                deleteButton.setImage(DELETE_IMAGE_PATH);
                
                /*
        		 * modifeid  for multile benefit component  delete
        		 */
                HtmlSelectBooleanCheckbox deleteCheckBox = new HtmlSelectBooleanCheckbox();
                deleteCheckBox.setId("deleteBtn" + i);
                ValueBinding valBindingForDeleteBnftCmpntId = FacesContext
				.getCurrentInstance().getApplication()
				.createValueBinding(
						"#{productComponentAssociationBackingBean.hiddenValDeleteBnftCmpntId["
								+ productComponentBO.getComponentKey() + "]}");
////				added for mulitiple benefit component  delete		
                deleteCheckBox.setValueBinding("value",valBindingForDeleteBnftCmpntId);
				deleteCheckBox.setOnclick("enableDisableDelete('formName:panelTable', 2, 0, 'formName:deleteButton')");
                
                //panel.getChildren().add(htmlInputText);
                lblseqTxt.getChildren().add(hidden);
                lblseqTxt.getChildren().add(htmlInputText);
                panel.getChildren().add(lblseqTxt);
                lblType.getChildren().add(componentKey);
                lblType.getChildren().add(outputText);
                lblType.getChildren().add(hiddenBnftComponentName);
                panel.getChildren().add(lblType);
                panel.getChildren().add(deleteCheckBox);
            }
        }
    }


    /**
     * This method deletes the selected benefit component and returns a new list
     * of components.
     * 
     * @return.
     */
    public String deleteBenefitComponent() {
    	if (null == this.benefitComponentString || "".equals(this.benefitComponentString)) {
        	getRequest().setAttribute("RETAIN_Value", "");
        }
    	 Map map=this.getHiddenValDeleteBnftCmpntId();
         List bnftCmpntIdList = new ArrayList();
 		Map bnftCmpntIdMap=this.getHiddenValDeleteBnftCmpntId();
 		Iterator bnftCmpntIdIt = bnftCmpntIdMap.entrySet().iterator();
 		 while (bnftCmpntIdIt.hasNext()) {
 	        Map.Entry pairs = (Map.Entry)bnftCmpntIdIt.next();
 	        if((Boolean)(pairs.getValue())==Boolean.valueOf(true)){
 	        	bnftCmpntIdList.add(pairs.getKey()) ;
 	        }
 	    }	
        String benefitDeleted = null;
        int rowId = getRowId();
        String componentKey = (String) componentKeyMap.get(new Long(rowId));
        DeleteProductBenefitComponentRequest productBenefitComponentDeleteRequest = (DeleteProductBenefitComponentRequest) this
                .getServiceRequest(ServiceManager.PRODUCT_BENEFIT_COMPONENT_DELETE);
        if(null!=bnftCmpntIdList && bnftCmpntIdList.size()>0){
        	productBenefitComponentDeleteRequest.setBenefitComponentList(bnftCmpntIdList);
        ProductBenefitComponentDeleteResponse productBenefitComponentDeleteResponse = (ProductBenefitComponentDeleteResponse) executeService(productBenefitComponentDeleteRequest);
        if (null != productBenefitComponentDeleteResponse) {
            this.benefitComponentList = productBenefitComponentDeleteResponse
                    .getBenefitComponentsList();
            //Code change for product tree rendering optimization
            super.updateTreeStructure();
            messageList = productBenefitComponentDeleteResponse.getMessages();
            addAllMessagesToRequest(messageList);
        }
        }
        this.componentAssociationModified = true;
        benefitDeleted = "deleted";
        setBenefitComponentDeleted(true);
        return "productComponentAssociation";
    }


    /**
     * This method saves the selected benefit components and returns a new list
     * of components.
     * 
     * @return.
     */
    public String saveBenefitComponents() {
    	getRequest().setAttribute("RETAIN_Value","");
        List componentKeyList = WPDStringUtil.getListFromTildaString(
                benefitComponentString, 2, 1, 1);
        List compNameList = WPDStringUtil.getListFromTildaString(
                benefitComponentString, 2, 2, 2);
        benefitComponentString = "";
        SaveProductComponentRequest saveProductComponentRequest = (SaveProductComponentRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT_COMPONENT);
        saveProductComponentRequest
                .setAction(SaveProductComponentRequest.SAVE_BENEFIT_COMPONENT);
        saveProductComponentRequest.setCompNameList(compNameList);
        saveProductComponentRequest.setComponentList(componentKeyList);
        saveProductComponentRequest.setProductFamily(getProductSessionObject().getProductFamily());
        SaveProductComponentResponse saveProductComponentResponse = null;
        if (null == componentKeyList || componentKeyList.size() == 0) {
        	messageList.add(new ErrorMessage("select.component"));
        	addAllMessagesToRequest(messageList);
            return "productComponentAssociation";
        }
        saveProductComponentResponse = (SaveProductComponentResponse) executeService(saveProductComponentRequest);
     
        if (null != saveProductComponentResponse
                && saveProductComponentResponse.isSuccess()) {
            this.benefitComponentList = saveProductComponentResponse
                    .getProductComponentList();
            
            //Code change for product tree rendering optimization
            super.updateTreeStructure();
            
            messageList = saveProductComponentResponse.getMessages();
            addAllMessagesToRequest(messageList);
            this.componentAssociationModified = true;
            return "productComponentAssociation";
        }
        return "";
    }


    /**
     * This method updates the sequence number associated with benefit
     * components and returns a new list of components.It checks whether entered
     * value for sequence number is numeric.
     * 
     * @return String.
     */
    /** Start new method send from onsite */
    public String updateBenefitComponents() {
    	if (null == this.benefitComponentString || "".equals(this.benefitComponentString)) {
        	getRequest().setAttribute("RETAIN_Value", "");
        }
        List productComponentVOList = new ArrayList();
        
        int sequenceNumber = 0;
        int lastSeqNumber = 0;

        Set valueKeys= sequenceNumberMap.keySet();
        TreeSet valueKeySet = new TreeSet();
        valueKeySet.addAll(valueKeys);
        
        if (!valueKeySet.isEmpty()) {
        	
            // Checking whether General Benefit Attached.
            boolean generalBenefitFlag = false;
            boolean generalProvisionFlag = false;
            boolean supBenefitFlag = false;
            int validSeqNo = 0;
            
            boolean updateFlag = false;
            boolean genBenefitSeqValidation = false;
            boolean genProvisionSeqValidation = false;
            boolean genGBandGPSeqValidation = false;
            
            //The loop for getting maximum seq number.
            //Also checks for the occurence of GB,GP or SB.
            for (Iterator iter = valueKeySet.iterator(); iter.hasNext();) {
                Long rowNum = (Long) iter.next();
                String componentName = (String) componentDescMap.get(rowNum);
                if (componentName != null && componentName.equals(WebConstants.GENERAL_BENEFITS)) {
                	generalBenefitFlag = true;
                	validSeqNo = 1;
                }
                if (componentName != null && componentName.equals(WebConstants.GENERAL_PROVISIONS )) {
                	generalProvisionFlag = true;
                	if(generalBenefitFlag){
                		validSeqNo = 2;
    	         	}else{
    	         		validSeqNo = 1;
    	         	}
                }
                if (componentName != null && componentName.equals(WebConstants.SUPPLEMENTAL_BENEFITS )) {
                	supBenefitFlag = true;
                    if(generalBenefitFlag && generalProvisionFlag){
                    	validSeqNo = 3;
                    }else if(generalBenefitFlag || generalProvisionFlag){
                    	validSeqNo = 2;
                    }
                    else{
                    	validSeqNo = 1;
                    }
                }
            }

            //Creating BO for updating sequence.
            boolean sequenceValid = true;
            
            int maxSeq = sequenceNumberMap.size();
            
            for (Iterator iter = valueKeySet.iterator(); iter.hasNext();) {
                Long rowNum = (Long) iter.next();
                String seqNum =  sequenceNumberMap.get(rowNum).toString();
                int seqNumInt = 0;
                String componentName =  componentDescMap.get(rowNum).toString();
                
                ProductComponentVO productComponentVO = new ProductComponentVO();
                productComponentVO.setComponentKey(Integer.valueOf((String) componentKeyMap.get(rowNum)).intValue());
                       
                if (componentName.equals(WebConstants.GENERAL_BENEFITS)) {
    	            sequenceNumber = 1;
    	            productComponentVO.setSequence(sequenceNumber);
    	            productComponentVOList.add(productComponentVO);	                	
        	        
    	        }else if(componentName.equals(WebConstants.GENERAL_PROVISIONS)){
    	        	if(generalBenefitFlag){
    	        		sequenceNumber = 2;
    	        	}
    	        	else{
    	        		sequenceNumber = 1;
    	        	}
    	        	productComponentVO.setSequence(sequenceNumber);
    	        	productComponentVOList.add(productComponentVO);
        	        
    	        }else if(componentName.equals(WebConstants.SUPPLEMENTAL_BENEFITS)){
    	        	if(generalBenefitFlag && generalProvisionFlag){
    	        		sequenceNumber = 3;
    	        	}else if (generalBenefitFlag || generalProvisionFlag){
    	        		sequenceNumber = 2 ;
    	        	}else{
    	        		sequenceNumber = 1;
    	        	}
    	        	productComponentVO.setSequence(sequenceNumber);
    	        	productComponentVOList.add(productComponentVO);
    	        }else {
    	            try {
    	                String sequenceNum =  sequenceNumberMap.get(rowNum).toString();
    	                sequenceNumber = new Integer(sequenceNum).intValue();
    	               
    	                //Validating the sequence number updation
    	                if(sequenceNumber == 0 || sequenceNumber > maxSeq){
	                   		sequenceValid = false;
	                   		
	                   		ErrorMessage message = new ErrorMessage(BusinessConstants.ENTERED_SEQUENCE_INVALID);
	                    	message.setParameters(new String[] {"1",(new Integer(maxSeq)).toString()});
	                   		
	                   		messageList.add(message);
	                   		break;
	                   }
    	                if(sequenceNumber <= validSeqNo){
    		               	sequenceValid = false;
    		               	//Validate when GB,GP & SB is there.
    		               	if(validSeqNo == 3){
    		               		messageList.add(getMessageGB("1"));
    		               		messageList.add(getMessageGP("2"));
    		               		messageList.add(getMessageSB("3"));
    		               	}else if (validSeqNo == 2){
    		               		//Validate when GB & GP is there.
    		               		if(generalBenefitFlag && generalProvisionFlag){
    		               			messageList.add(getMessageGB("1"));
    		               			messageList.add(getMessageGP("2"));
    		               		}
    		               		//Validate when GB & SB is there.
    		               		else if(generalBenefitFlag && supBenefitFlag){
    		               			messageList.add(getMessageGB("1"));
    		               			messageList.add(getMessageSB("2"));
    		               		}
    		               		//Validate when GP & SB is there.
								else if(generalProvisionFlag && supBenefitFlag){
    		               			messageList.add(getMessageGP("1"));
    		               			messageList.add(getMessageSB("2"));
    		               		}
    		               	}else if(validSeqNo == 1){
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
    	                	productComponentVO.setSequence(sequenceNumber);
    	                	productComponentVOList.add(productComponentVO);	      
    	                }
    	             }
    	            catch(NumberFormatException exception){
    		                messageList.add(new ErrorMessage("sequence.number.input"
    		                        + ".validation"));
    		                productComponentVOList = null;
    		                sequenceValid =false;
    		                break;
    	            }
    	        }
            }
            if (sequenceValid) {
                List orderedList = this.reorder(productComponentVOList);
                SaveProductComponentRequest saveProductComponentRequest = (SaveProductComponentRequest) this
                        .getServiceRequest(ServiceManager.SAVE_PRODUCT_COMPONENT);
                saveProductComponentRequest
                        .setAction(SaveProductComponentRequest.UPDATE_SEQUENCE);
                saveProductComponentRequest.setComponentList(orderedList);
                SaveProductComponentResponse saveProductComponentResponse = (SaveProductComponentResponse) executeService(saveProductComponentRequest);
                if (null != saveProductComponentResponse) {
                    this.benefitComponentList = saveProductComponentResponse
                            .getProductComponentList();
                    this.componentAssociationModified = true;
                    messageList = saveProductComponentResponse.getMessages();
                    addAllMessagesToRequest(messageList);
                    return "productComponentAssociation";
                }
            } else {
            	addAllMessagesToRequest(messageList);
                //this.messages.addAll(messageList);
                
               
             
               // this.getRequest().setAttribute("messages", messageList);
                return "productComponentAssociation";
            }
            
        }
        return "";
    }

    private ErrorMessage getMessageGB(String sequence){
    	ErrorMessage message = new ErrorMessage(BusinessConstants.ALERT_MESSAGE_GENERAL_BENEFITS);
    	message.setParameters(new String[] {sequence});
    	return message;
    }
    
    private ErrorMessage getMessageGP(String sequence){
    	ErrorMessage message = new ErrorMessage(BusinessConstants.ALERT_MESSAGE_GENERAL_PROVISIONS);
    	message.setParameters(new String[] {sequence});
    	return message;
    }
    
    private ErrorMessage getMessageSB(String sequence){
    	ErrorMessage message = new ErrorMessage(BusinessConstants.ALERT_MESSAGE_SUPPLEMENTAL_BENEFITS);
    	message.setParameters(new String[] {sequence});
    	return message;
    }
    
    /** End new method send from onsite */

    /**
     * @return Returns the state.
     */
    public String getStateOfObject() {
        return getStateFromSession();
    }


    /**
     * @param state
     *            The state to set.
     */
    public void setStateOfObject(String stateOfObject) {
        this.stateOfObject = stateOfObject;
    }


    /**
     * @return Returns the status.
     */
    public String getStatusOfObject() {

        return super.getStatusFromSession();

    }


    /**
     * @param status
     *            The status to set.
     */
    public void setStatusOfObject(String statusOfObject) {
        this.statusOfObject = statusOfObject;
    }


    /**
     * @return Returns the version.
     */
    public int getVersionOfObject() {

        return getVersionFromSession();
    }


    /**
     * @param version
     *            The version to set.
     */
    public void setVersionOfObject(int versionOfObject) {
        this.versionOfObject = versionOfObject;
    }


    /**
     * Returns the rowId
     * 
     * @return int rowId.
     */
    public int getRowId() {
        return rowId;
    }


    /**
     * Sets the rowId
     * 
     * @param rowId.
     */
    public void setRowId(int rowId) {
        this.rowId = rowId;
    }


    /**
     * Returns the componentKey
     * 
     * @return String componentKey.
     */

    /**
     * @return Returns the componentKeyMap.
     */
    public Map getComponentKeyMap() {
        return componentKeyMap;
    }


    /**
     * @param componentKeyMap
     *            The componentKeyMap to set.
     */
    public void setComponentKeyMap(Map componentKeyMap) {
        this.componentKeyMap = componentKeyMap;
    }


    /**
     * @return Returns the sequenceNumberMap.
     */
    public Map getSequenceNumberMap() {
        return sequenceNumberMap;
    }


    /**
     * @param sequenceNumberMap
     *            The sequenceNumberMap to set.
     */
    public void setSequenceNumberMap(Map sequenceNumberMap) {
        this.sequenceNumberMap = sequenceNumberMap;
    }


    /**
     * @return Returns the benefitComponentList.
     */
    public List getBenefitComponentList() {
       
        return benefitComponentList;
    }


    /**
     * @param benefitComponentList
     *            The benefitComponentList to set.
     */
    public void setBenefitComponentList(List benefitComponentList) {
        this.benefitComponentList = benefitComponentList;
    }


    /**
     * @return Returns the benefitComponentDeleted.
     */
    public boolean isBenefitComponentDeleted() {
        return benefitComponentDeleted;
    }


    /**
     * @param benefitComponentDeleted
     *            The benefitComponentDeleted to set.
     */
    public void setBenefitComponentDeleted(boolean benefitComponentDeleted) {
        this.benefitComponentDeleted = benefitComponentDeleted;
    }


    /**
     * @return Returns the benefitComponentString.
     */
    public String getBenefitComponentString() {
        return benefitComponentString;
    }


    /**
     * @param benefitComponentString
     *            The benefitComponentString to set.
     */
    public void setBenefitComponentString(String benefitComponentString) {
        this.benefitComponentString = benefitComponentString;
    }


    /**
     * Returns the emptyList
     * 
     * @return boolean emptyList.
     */

    public boolean isEmptyList() {
        return emptyList;
    }


    /**
     * Sets the emptyList
     * 
     * @param emptyList.
     */

    public void setEmptyList(boolean emptyList) {
        this.emptyList = emptyList;
    }


    /**
     * @return Returns the printValue.
     */
    public String getPrintValue() {
        String requestForPrint = getRequest().getParameter(
                "printValueForComAss");
        if (null != requestForPrint && !requestForPrint.equals("")) {
            printValue = requestForPrint;
        } else {
            printValue = "";
        }
        return printValue;
    }


    /**
     * @param printValue
     *            The printValue to set.
     */
    public void setPrintValue(String printValue) {
        this.printValue = printValue;
    }


    /**
     * Returns the dummyVar
     * 
     * @return String dummyVar.
     */

    public String getDummyVar() {
        return dummyVar;
    }


    /**
     * Sets the dummyVar
     * 
     * @param dummyVar.
     */

    public void setDummyVar(String dummyVar) {
        this.dummyVar = dummyVar;
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
     * Returns the hiddenInit
     * 
     * @return String hiddenInit.
     */
  //WAS 7.0 Changes - Binding variable hiddenInit modified to HtmlInputHidden instead of String, Since getter method call failed,
 // while the variable defined in String in WAS 7.0


    public HtmlInputHidden getHiddenInit() {   
    	this.loadComponent();
    	hiddenInit.setValue("productComponentAssociation");
    	hiddenInit.setId("hiddenInit"+RandomStringUtils.randomAlphanumeric(15));
        return hiddenInit;
    }


    /**
     * Sets the hiddenInit
     * 
     * @param hiddenInit.
     */
    public void setHiddenInit(HtmlInputHidden hiddenInit) {
        this.hiddenInit = hiddenInit;
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
     * Returns the componentDescMap
     * 
     * @return Map componentDescMap.
     */
    public Map getComponentDescMap() {
        return componentDescMap;
    }


    /**
     * Sets the componentDescMap
     * 
     * @param componentDescMap.
     */
    public void setComponentDescMap(Map componentDescMap) {
        this.componentDescMap = componentDescMap;
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
	 * @return Returns the hiddenComponent.
	 */
	public String getHiddenComponent() {
		 RetrieveProductBenefitComponentRequest productBenefitComponentRequest = (RetrieveProductBenefitComponentRequest) this
         .getServiceRequest(ServiceManager.PRODUCT_BENEFIT_COMPONENT);
		 productBenefitComponentRequest
         .setAction(RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_ADDED);
		 ProductBenefitComponentResponse productBenefitComponentResponse = (ProductBenefitComponentResponse) executeService(productBenefitComponentRequest);
		 if (null != productBenefitComponentResponse) {
		 	benefitComponentList = productBenefitComponentResponse
             .getBenefitComponentList();
 }
		return hiddenComponent;
	}
	/**
	 * @param hiddenComponent The hiddenComponent to set.
	 */
	public void setHiddenComponent(String hiddenComponent) {
		this.hiddenComponent = hiddenComponent;
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
	 * @return Returns the componentHierarchy.
	 */
	public String getComponentHierarchy() {
		RetrieveProductComponentHierarchyRequest request = 
			(RetrieveProductComponentHierarchyRequest)this.getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_COMPONENT_HIERARCHY);
		ProductKeyObject keyObject = new ProductKeyObject();
		keyObject.setProductId(this.getProductKeyFromSession());
		keyObject.setState(this.getStateFromSession());
		keyObject.setVersion(this.getVersionFromSession());
		keyObject.setStatus(this.getStatusFromSession());
		
		request.setProductKeyObject(keyObject);
		
		RetrieveProductComponentHierarchyResponse response = (RetrieveProductComponentHierarchyResponse)this.executeService(request);
		if(null != response){
			this.hierarchyList = response.getComponentHierarchyList();
			if(null != this.hierarchyList && !this.hierarchyList.isEmpty() && this.hierarchyList.size() != 0){
				this.changeList(this.hierarchyList);
			}
		}
		return componentHierarchy;
	}
	private void changeList(List hierarchyList){
        Map componentMap = new HashMap();
        ProductAssociatedBenefit associatedBenefit = null;
        for (int i = 0; i < hierarchyList.size(); i++){
            associatedBenefit = (ProductAssociatedBenefit)hierarchyList.get(i);
            if(null != associatedBenefit && null != associatedBenefit.getComponentDesc()){
                if(!componentMap.containsValue(associatedBenefit.getComponentDesc())){                                             
                	componentMap.put(new Integer(associatedBenefit.getBenCompSeqNo()), associatedBenefit.getComponentDesc());
                }else{
                    associatedBenefit.setComponentDesc("");
                }
            }
        }                       
	}

	/**
	 * @param componentHierarchy The componentHierarchy to set.
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
	 * @param hierarchyList The hierarchyList to set.
	 */
	public void setHierarchyList(List hierarchyList) {
		this.hierarchyList = hierarchyList;
	}
	/**
	 * @return Returns the productEntityId.
	 */
	public int getProductEntityId() {
		return productEntityId;
	}
	/**
	 * @param productEntityId The productEntityId to set.
	 */
	public void setProductEntityId(int productEntityId) {
		this.productEntityId = productEntityId;
	}
}