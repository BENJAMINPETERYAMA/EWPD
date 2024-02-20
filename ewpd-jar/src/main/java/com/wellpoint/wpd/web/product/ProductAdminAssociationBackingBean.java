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

import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import org.apache.commons.lang.RandomStringUtils;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.product.bo.ProductAdminBO;
import com.wellpoint.wpd.common.product.request.DeleteProductAdminRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductAdminRequest;
import com.wellpoint.wpd.common.product.request.SaveProductAdminRequest;
import com.wellpoint.wpd.common.product.request.SaveProductComponentRequest;
import com.wellpoint.wpd.common.product.request.SaveProductRequest;
import com.wellpoint.wpd.common.product.response.ProductAdminDeleteResponse;
import com.wellpoint.wpd.common.product.response.ProductAdminResponse;
import com.wellpoint.wpd.common.product.response.SaveProductAdminResponse;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.common.product.vo.ProductAdminVO;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.framework.util.SequenceUtil;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductAdminAssociationBackingBean extends ProductBackingBean {

    private String admin = null;

    private String state = null;

    private String status = null;

    private int version = 0;

    private HtmlPanelGrid panel = new HtmlPanelGrid();

    private List adminList = new ArrayList();

    private int rowId = 0;

    private Map adminKeyMap = new HashMap();

    private Map sequenceNumberMap = new HashMap();

    private boolean adminDeleted;

    private String adminString;

    private boolean adminAssociationModified = false;

    private boolean emptyList = true;

    private String printValue;

    private String dummyVar;

    private boolean checkin;

    private String hiddenInit;

    private boolean higherVersion = true;

    private Map adminDescMap = new HashMap();

    private String productType;
    
    private String hiddenAdmin;
    
    private boolean hasValidationErrors;
    
    private Map adminDeleteKeyMap = new HashMap();
    
    private String panelData = "";

    //private List adminListForPrint = new ArrayList();

    List validationMessages = null;
    
    private int productEntityId;


    //Setting the BreadCumb.
    public ProductAdminAssociationBackingBean() {
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
        this.productEntityId = super.getProductKeyFromSession();
    }


    /*
     * To Attach admin option to product
     * 
     * adminKeyList contains list of admin option to save
     * 
     * returns associated admin list
     */

    public String done() {
    	getRequest().setAttribute("RETAIN_Value", "");
        List adminKeyList = WPDStringUtil.getListFromTildaString(adminString,
                2, 1, 1);
        adminString = "";
        SaveProductAdminRequest saveProductAdminRequest = (SaveProductAdminRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT_ADMIN);
        saveProductAdminRequest.setAction(SaveProductAdminRequest.SAVE_ADMIN);
        saveProductAdminRequest.setAdminList(adminKeyList);
        SaveProductAdminResponse saveProductAdminResponse = null;
        List msgListOne = new ArrayList(3);
        List msgListTwo = new ArrayList(1);
        if (null != adminKeyList && adminKeyList.size() != 0) {

            saveProductAdminResponse = (SaveProductAdminResponse) executeService(saveProductAdminRequest);
            if (null != saveProductAdminResponse) {
                this.adminList = saveProductAdminResponse.getProductAdminList();
            }
            this.adminAssociationModified = true;
        }
        SaveProductRequest saveProductRequest = (SaveProductRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT);
        SaveProductResponse saveProductResponse = null;

        if (null != saveProductAdminResponse
                && null != saveProductAdminResponse.getMessages())
            msgListOne = saveProductAdminResponse.getMessages();

        saveProductRequest.setAction(SaveProductRequest.CHECKIN_PRODUCT);
        saveProductRequest.setCheckIn(this.checkin);
        
        if(null!=getSession().getAttribute("AM_BENEFIT"))
			getSession().removeAttribute("AM_BENEFIT");
		if(null!=getSession().getAttribute("AM_BC_KEY"))
			getSession().removeAttribute("AM_BC_KEY");
		if(null!=getSession().getAttribute("AM_ENTITY_KEY"))
			getSession().removeAttribute("AM_ENTITY_KEY");
		if(null!=getSession().getAttribute("DIRECT_CLICK"))
			getSession().removeAttribute("DIRECT_CLICK");

        saveProductRequest.getProduct().setProductKey(
                super.getProductKeyFromSession());
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
            if(null!=msgListOne && msgListOne.size() >0){
            validationMessages = new ArrayList(msgListOne.size());
            validationMessages.addAll(msgListOne);
            }
            addAllMessagesToRequest(validationMessages);
            if(saveProductResponse.getCondition() == SaveProductResponse.VALIDATION_RESULTS){
				hasValidationErrors = true;
				setValuesForAminMethodValidation();
				return "";
			}else if(saveProductResponse.getCondition() == SaveProductResponse.VALIDATION_WAIT){
				getSession().setAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN,
						"productAdminAssociationBackingBean");
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

        return "productAdminAssociation";

    }


    /**
     * Returns the panel
     * 
     * @return HtmlPanelGrid panel.
     */

    public String loadComponent() {

        String checkType = super.getProductTypeFromSession();
        if ((WebConstants.MANDATE_TYPE).equals(checkType)) {
            ProductGeneralInformationBackingBean productGeneralInformationBackingBean = new ProductGeneralInformationBackingBean();
            validationMessages = new ArrayList(2);
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.ADMINOPTION_MANDATE_TYPE_MESSAGE));
            productGeneralInformationBackingBean
                    .setValidationMessages(validationMessages);
            addAllMessagesToRequest(validationMessages);

            return "";

        } else {
            RetrieveProductAdminRequest request = (RetrieveProductAdminRequest) this
                    .getServiceRequest(ServiceManager.PRODUCT_ADMIN);
            request.setAction(RetrieveProductAdminRequest.PRODUCT_ADMIN_ADDED);
            ProductAdminResponse productAdminResponse = (ProductAdminResponse) executeService(request);

            adminList = productAdminResponse.getAdminList();
            if(null!=validationMessages)
            	addAllMessagesToRequest(validationMessages);
            
            this.adminAssociationModified = true;

            if (getProductSessionObject().getMode() == ProductSessionObject.VIEW_MODE)
                return "productAdminAssociationView";
            else
                return "productAdminAssociation";
        }
    }


    public HtmlPanelGrid getPanel() {
        if (adminAssociationModified) {
            preparePanel(this.adminList);
            register(this.adminList);
        }
        return panel;
    }


    private void register(List adminBOList) {
        SequenceUtil sequenceUtil = new SequenceUtil();
        sequenceUtil.registerObjects(adminBOList, "adminKey", "sequence");
    }


    private List reorder(List adminVOList) {
        SequenceUtil sequenceUtil = new SequenceUtil();
        return sequenceUtil.reOrderObjects(adminVOList);
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
     * This method prepares the panel grid from admin list
     * 
     * @param List
     *            adminList.
     */

    public void preparePanel(List adminList) {
        ProductAdminBO productAdminBO = new ProductAdminBO();
        this.panel = new HtmlPanelGrid();
        if (null != adminList && adminList.size() != 0) {
            for (int i = 0; i < adminList.size(); i++) {
                HtmlInputText inputText = new HtmlInputText();
                HtmlInputHidden inputTextHidden = new HtmlInputHidden();
                HtmlOutputText outputText = new HtmlOutputText();
                HtmlInputHidden adminKey = new HtmlInputHidden();
                HtmlOutputLabel lblType = new HtmlOutputLabel();
                lblType.setId("lblType"+RandomStringUtils.randomAlphanumeric(15));
                HtmlOutputLabel lblText = new HtmlOutputLabel();
                lblText.setId("lblText"+RandomStringUtils.randomAlphanumeric(15));
                HtmlSelectBooleanCheckbox checkBox = new HtmlSelectBooleanCheckbox();
                checkBox.setValue(Boolean.valueOf(false));                   		
                checkBox.setTitle("Delete"); 		
    			checkBox.setId("deleteCheckBox" + i);    			
                ValueBinding myItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productAdminAssociationBackingBean.adminKeyMap["
                                        + i + "]}");
                inputTextHidden.setId("inputTextHidden"+i);
                ValueBinding seqNum = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productAdminAssociationBackingBean.sequenceNumberMap["
                                        + i + "]}");
                inputText.setStyleClass("formInputFieldForSequenceNo");
                inputText.setValueBinding("value", seqNum);
                inputText.setOnkeydown("return isNumberKey(event);");

                //create a hidden field for the admin name
                HtmlInputHidden hiddenAdmOptionName = new HtmlInputHidden();
                hiddenAdmOptionName.setId("hiddenAdmOptionName" + i);
                ValueBinding hiddenNameItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productAdminAssociationBackingBean.adminDescMap["
                                        + i + "]}");
                productAdminBO = (ProductAdminBO) getAdminList().get(i);
                inputText.setValue(new Integer(productAdminBO.getSequence()));
                inputText.setId("inputText" + i);
                outputText.setValue(productAdminBO.getAdminDesc());
                adminKey.setValue(new Integer(productAdminBO.getAdminKey()));
                adminKey.setId("adminKey" +i);
                adminKey.setValueBinding("value", myItem);
                checkBox.setValueBinding("value",
										FacesContext.getCurrentInstance().
										getApplication().
										createValueBinding(
											"#{productAdminAssociationBackingBean.adminDeleteKeyMap[" 
											+ new Integer(productAdminBO.getAdminKey())+
											"]}")
									);   
				checkBox.setOnclick("changeButton();");               

                //creates a binding for the Admin name
                hiddenAdmOptionName.setValue(productAdminBO.getAdminDesc());
                hiddenAdmOptionName.setValueBinding("value", hiddenNameItem);
                lblText.getChildren().add(inputText);
                lblText.getChildren().add(inputTextHidden);
                panel.getChildren().add(lblText);
                lblType.getChildren().add(adminKey);
                lblType.getChildren().add(outputText);
                lblType.getChildren().add(hiddenAdmOptionName);
                panel.getChildren().add(lblType);
                panel.getChildren().add(checkBox);
            }
        }
    }


    /**
     * This method deletes the selected admin and returns a new list of admin.
     * 
     * @return.
     */
    /*public String deleteAdmin() {
    	getRequest().setAttribute("RETAIN_Value", "");
        String adminDeleted = null;
        int productId = super.getProductKeyFromSession();        
        int rowId = getRowId();
        String adminKey = (String) adminKeyMap.get(new Long(rowId));
        DeleteProductAdminRequest deleteProductAdminRequest = (DeleteProductAdminRequest) this
                .getServiceRequest(ServiceManager.PRODUCT_ADMIN_DELETE);
        if (null != adminKey) {
            deleteProductAdminRequest.setAdminKey(new Integer(adminKey)
                    .intValue());
        }
        ProductAdminDeleteResponse productAdminDeleteResponse = (ProductAdminDeleteResponse) executeService(deleteProductAdminRequest);
        if (null != productAdminDeleteResponse) {
        	 
        	loadComponent();

        }
        validationMessages=productAdminDeleteResponse.getMessages();
        addAllMessagesToRequest(validationMessages);

        this.adminAssociationModified = true;
        adminString = "";
        adminDeleted = "deleted";
        setAdminDeleted(true);
        return "productAdminAssociation";
    }*/
    
    /**
     * This method deletes the selected admin options.
     * 
     * @return String
     */
    public String deleteAdminOption(){
    	List deleteList = this.getSelectedAdminOptionsForDelete();
    	getRequest().setAttribute("RETAIN_Value", "");       
        DeleteProductAdminRequest deleteProductAdminRequest = (DeleteProductAdminRequest) this
                .getServiceRequest(ServiceManager.PRODUCT_ADMIN_DELETE);
        ProductAdminDeleteResponse productAdminDeleteResponse = null;
        if(null != deleteList){
	        for (int i=0; i < deleteList.size(); i++) {
				String adminKey = (String) deleteList.get(i);
	
				if (null != adminKey) {
					deleteProductAdminRequest.setAdminKey(new Integer(adminKey)
							.intValue());
				}
				productAdminDeleteResponse = (ProductAdminDeleteResponse) executeService(deleteProductAdminRequest);
			}
        }
        if (null != productAdminDeleteResponse) {  
        	 //Code change for product tree rendering optimization
            super.updateTreeStructure();
        	loadComponent();
        }
        validationMessages=productAdminDeleteResponse.getMessages();
        addAllMessagesToRequest(validationMessages);

        this.adminAssociationModified = true;
        adminString = "";
        setAdminDeleted(true);
        return "productAdminAssociation";
    }

    /**
     * Method to get the selected Admin Options.
     * @return
     */
    private List getSelectedAdminOptionsForDelete(){
    	// variable declarations
    	List adminOptionDeleteList = null;
    	Set adminOptionKeySet = null;
    	Iterator adminOptionIdIterator = null;
    	Object adminOptionId = null;
    	Boolean isChecked = null;
    	
    	if(null != this.getAdminDeleteKeyMap()){
    		// get the key set from the map
    		adminOptionKeySet = this.getAdminDeleteKeyMap().keySet();
    		// Iterate the key set
    		adminOptionIdIterator = adminOptionKeySet.iterator();
    		adminOptionDeleteList = new ArrayList();
    		while(adminOptionIdIterator.hasNext()){
    			// get the key
    			adminOptionId = adminOptionIdIterator.next();
    			// get the value whether it is checked or not.
    			isChecked = (Boolean) this.getAdminDeleteKeyMap().get(adminOptionId);
    			if(isChecked.booleanValue()){
    				// add the level id to the list
    				adminOptionDeleteList.add(adminOptionId.toString());
    			}
    		}
    	}
    	
    	// return the selected levels to be deleted.
    	return adminOptionDeleteList;
    }

    /**
     * This method saves the selected admin and returns a new list of admin.
     * 
     * @return string .
     */
    public String saveProductAdmin() {
    	getRequest().setAttribute("RETAIN_Value", "");
        List adminKeyList = WPDStringUtil.getListFromTildaString(adminString,
                2, 1, 1);
        List messageList =null;
        adminString = "";
        SaveProductAdminRequest saveProductAdminRequest = (SaveProductAdminRequest) this
                .getServiceRequest(ServiceManager.SAVE_PRODUCT_ADMIN);
        saveProductAdminRequest.setAction(SaveProductAdminRequest.SAVE_ADMIN);
        saveProductAdminRequest.setAdminList(adminKeyList);
        SaveProductAdminResponse saveProductAdminResponse = null;
        if (null == adminKeyList || adminKeyList.size() == 0) {
            validationMessages = new ArrayList(1);
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_ADMINOPTION_FIELDS_REQUIRED));
            addAllMessagesToRequest(validationMessages);
            return "productAdminAssociation";
        }
        saveProductAdminResponse = (SaveProductAdminResponse) executeService(saveProductAdminRequest);
        if (null != saveProductAdminResponse
                && saveProductAdminResponse.isSuccess()) {
            this.adminList = saveProductAdminResponse.getProductAdminList();
            //Code change for product tree rendering optimization
            super.updateTreeStructure();
            this.adminAssociationModified = true;
            messageList = saveProductAdminResponse.getMessages();
            if(null!= messageList && 0<messageList.size()){
            validationMessages = new ArrayList(messageList.size());
            validationMessages.addAll(messageList);
            addAllMessagesToRequest(validationMessages);
            }
            return "productAdminAssociation";
        }
        loadComponent();

        return "";
    }


    /**
     * @return Returns the benefitComponentList.
     */
    public List getBenefitComponentList() {
        RetrieveProductAdminRequest productAdminRequest = (RetrieveProductAdminRequest) this
                .getServiceRequest(ServiceManager.PRODUCT_ADMIN);
        productAdminRequest
                .setAction(RetrieveProductAdminRequest.PRODUCT_ADMIN_ADDED);
        ProductAdminResponse productAdminResponse = (ProductAdminResponse) executeService(productAdminRequest);
        if (null != productAdminResponse) {
            adminList = productAdminResponse.getAdminList();
        }
        return adminList;
    }


    /**
     * Method for updating manually changed sequence numbers
     * @return String
     */
    public String updateAdminOptions() {
		List productAdminVOList = new ArrayList(1);
		List messageList = new ArrayList(4);
		boolean valSeqNum = true;
		Set valueKeys = sequenceNumberMap.keySet();
		if (!valueKeys.isEmpty()) {
		boolean sequenceValid = true;
			for (Iterator iter = valueKeys.iterator(); iter.hasNext()
				&& sequenceValid;) {
				Long rowNum = (Long) iter.next();
				String seqNum = (String) sequenceNumberMap.get(rowNum);
				int seqNumInt = 0;
				String componentName = (String) adminDescMap.get(rowNum);
				// Validating whether Sequence is blank.
				if (seqNum == null || seqNum.trim().equals("")) {
					messageList.add(new ErrorMessage("product.seq.empty"));
					sequenceValid = false;
					break;
				}
				// Validating whether sequence is integer.
				if (!seqNum.matches("[\\d]+")) {
					messageList.add(new ErrorMessage("product.seq.invalid"));
					sequenceValid = false;
					break;
				}
				seqNumInt = Integer.valueOf(seqNum).intValue();
				if (seqNumInt <= 0) {
					messageList.add(new ErrorMessage("product.seq.invalid"));
					sequenceValid = false;
					break;
				}
				// If everything valid
				ProductAdminVO productAdminVO = new ProductAdminVO();
				productAdminVO.setAdminKey(Integer.valueOf(
				(String) adminKeyMap.get(rowNum)).intValue());
				productAdminVO.setSequence(seqNumInt);
				productAdminVOList.add(productAdminVO);
			}
			if (sequenceValid) {
				List orderedList = this.reorder(productAdminVOList);
				SaveProductAdminRequest saveProductAdminRequest = (SaveProductAdminRequest) this
					.getServiceRequest(ServiceManager.SAVE_PRODUCT_ADMIN);
				saveProductAdminRequest
					.setAction(SaveProductComponentRequest.UPDATE_SEQUENCE);
				saveProductAdminRequest.setAdminList(orderedList);
				SaveProductAdminResponse saveProductAdminResponse = (SaveProductAdminResponse) executeService(saveProductAdminRequest);
				if (null != saveProductAdminResponse) {
					this.adminAssociationModified=true;
					this.adminList = saveProductAdminResponse
						.getProductAdminList();
					return "productAdminAssociation";
				}
			} else {
			    addAllMessagesToRequest(messageList);
			    return "";
			}
		}
		return "";
	}


    /**
     * @return Returns the printValue.
     */
    public String getPrintValue() {
        String requestForPrint = getRequest().getParameter(
                "printValueForAdminOption");
        if (null != requestForPrint && !requestForPrint.equals("")) {
            printValue = requestForPrint;
        } else {
            printValue = "";
        }
        return printValue;
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
    public String getHiddenInit() {
    	this.loadComponent();
        return hiddenInit;
    }


    /**
     * Sets the hiddenInit
     * 
     * @param hiddenInit.
     */
    public void setHiddenInit(String hiddenInit) {
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
     * @return Returns the admin.
     */
    public String getAdmin() {
        return admin;
    }


    /**
     * @param admin
     *            The admin to set.
     */
    public void setAdmin(String admin) {
        this.admin = admin;
    }


    /**
     * @return Returns the adminAssociationModified.
     */
    public boolean isAdminAssociationModified() {
        return adminAssociationModified;
    }


    /**
     * @param adminAssociationModified
     *            The adminAssociationModified to set.
     */
    public void setAdminAssociationModified(boolean adminAssociationModified) {
        this.adminAssociationModified = adminAssociationModified;
    }


    /**
     * @return Returns the adminDeleted.
     */
    public boolean isAdminDeleted() {
        return adminDeleted;
    }


    /**
     * @param adminDeleted
     *            The adminDeleted to set.
     */
    public void setAdminDeleted(boolean adminDeleted) {
        this.adminDeleted = adminDeleted;
    }


    /**
     * @return Returns the adminDescMap.
     */
    public Map getAdminDescMap() {
        return adminDescMap;
    }


    /**
     * @param adminDescMap
     *            The adminDescMap to set.
     */
    public void setAdminDescMap(Map adminDescMap) {
        this.adminDescMap = adminDescMap;
    }


    /**
     * @return Returns the adminKeyMap.
     */
    public Map getAdminKeyMap() {
        return adminKeyMap;
    }


    /**
     * @param adminKeyMap
     *            The adminKeyMap to set.
     */
    public void setAdminKeyMap(Map adminKeyMap) {
        this.adminKeyMap = adminKeyMap;
    }


    /**
     * @return Returns the adminList.
     */
    public List getAdminList() {
       
        return adminList;
    }


    /**
     * @param adminList
     *            The adminList to set.
     */
    public void setAdminList(List adminList) {
        this.adminList = adminList;
    }


    /**
     * @return Returns the adminString.
     */
    public String getAdminString() {
        return adminString;
    }


    /**
     * @param adminString
     *            The adminString to set.
     */
    public void setAdminString(String adminString) {
        this.adminString = adminString;
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
	 * @return Returns the hiddenAdmin.
	 */
	public String getHiddenAdmin() {
		 loadComponent();
		return hiddenAdmin;
	}
	/**
	 * @param hiddenAdmin The hiddenAdmin to set.
	 */
	public void setHiddenAdmin(String hiddenAdmin) {
		this.hiddenAdmin = hiddenAdmin;
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
	 * @return Returns the adminDeleteKeyMap.
	 */
	public Map getAdminDeleteKeyMap() {
		return adminDeleteKeyMap;
	}
	/**
	 * @param adminDeleteKeyMap The adminDeleteKeyMap to set.
	 */
	public void setAdminDeleteKeyMap(Map adminDeleteKeyMap) {
		this.adminDeleteKeyMap = adminDeleteKeyMap;
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
