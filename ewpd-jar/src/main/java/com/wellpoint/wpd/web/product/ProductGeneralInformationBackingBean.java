/*
 * ProductGeneralInformationBackingBean.java
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
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.model.SelectItem;


import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.product.bo.ProductStructureBO;
import com.wellpoint.wpd.common.product.request.RetrieveProductRequest;
import com.wellpoint.wpd.common.product.request.RetrieveValidProductStructuresRequest;
import com.wellpoint.wpd.common.product.request.SaveProductRequest;
import com.wellpoint.wpd.common.product.response.RetrieveProductResponse;
import com.wellpoint.wpd.common.product.response.RetrieveValidProductStructuresResponse;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.common.product.vo.ProductVO;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class ProductGeneralInformationBackingBean extends ProductBackingBean {

	private String productKey;

	private String businessDomDiv;

	private String lineOfBusinessDiv = WebConstants.ALL_99;

	private String businessEntityDiv = WebConstants.ALL_99;

	private String businessGroupDiv = WebConstants.ALL_99;

	private String productFamilyDiv;

	private String productName;

	private String productDescription;

	private String productStructDiv;

	private String businessDomain;

	private String productStructure;

	private String effectiveDate = WebConstants.DATE_1900;

	private String expiryDate = WebConstants.DEFAULT_EXP_DATE;

	private Date creationDate;

	private String createdBy;

	private Date updationDate;

	private String updatedBy;

	private List productStructureList;

	private String status;

	private String state;

	private String version;

	private List validationMessages;

	//new fields
	private String productType;

	private String mandateType=null;

	private String stateCode=null;

	private List productTypeListForCombo;

	//  state Id
	private String stateId;

	// State Desc
	private String stateDesc;

	private boolean requiredProductType = false;

	private boolean requiredMandateType = false;

	private boolean requiredState = false;

	private boolean lobInvalid = false;

	private boolean entityInvalid = false;

	private boolean groupInvalid = false;

	private boolean familyInvalid = false;

	private boolean nameInvalid = false;

	private boolean descInvalid = false;

	private boolean structureInvalid = false;

	private boolean effectiveDateInvalid = false;

	private boolean expiryDateInvalid = false;

	private boolean descFiledNotValid = false;

	private boolean invalidFieldMessage = false;

	private ProductStructureBO productStructureBo1 = null;

	private ProductStructureBO productStructureBo2 = null;

	private boolean productStructureListRetrieved = false;

	private String selectedIdFromSearch;

	private boolean checkin;

	private boolean higherVersion = false;

	private boolean rendered;

	private String printValue;

	private String hiddenInit;
	
	private String hiddenProductType;

	private boolean copyFlag = false;

	private String printBreadCrumbText;
	
	private boolean hasValidationErrors;
	
	private List deletedRulesList = null;
	
	private List unCodedRulesList =  null;

	private String loadRuleValidationPopUp = null;
	
	private String productStructureVersion;
	
	//new fild added for septembe release
	
	private String productFamily;

	//CARS START 
	private String marketBusinessUnitDiv = WebConstants.ALL_99;

	private boolean marketBusinessUnitInvalid = false;	
	//CARS END
	
	public ProductGeneralInformationBackingBean() {
		if (!super.isViewMode()) {
			this.setBreadCumbTextForEdit();
		} else {
			this.setBreadCumbTextForView();
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
	
	/**
	 * This method is used for checkout process
	 * 
	 * @return
	 */

	public String checkOutAction() {
		//gets request
		SaveProductRequest saveProductRequest = (SaveProductRequest) this
				.getServiceRequest(ServiceManager.SAVE_PRODUCT);
		saveProductRequest.setAction(SaveProductRequest.CHECKOUT_PRODUCT);
		int key = Integer.parseInt(selectedIdFromSearch);
		super.setKeyObjectToSessionForEdit(key);

		SaveProductResponse saveProductResponse = null;
		//calls service
		saveProductResponse = (SaveProductResponse) this
				.executeService(saveProductRequest);

		if (saveProductResponse != null
				&& saveProductResponse.getProductBO() != null) {
			setValuesToBackingBean(saveProductResponse.getProductBO(),
					saveProductResponse.getDomainDetail());
		}
		return "editProductGenInfo";
	}

	/**
	 * This function is called when the action is made on the Done button in the
	 * productGeneralInformation.jsp
	 * 
	 * @return doneProduct String
	 */
	public String done() {
		//validates all fields
		getRequest().setAttribute("RETAIN_Value", "");
		if (isAllFieldsValid()) {
			// COMPLETES THE UPDATE BEFORE CHECKIN
			SaveProductResponse saveProductResponse = null;
			//gets request
			SaveProductRequest saveProductRequest = (SaveProductRequest) this
					.getServiceRequest(ServiceManager.SAVE_PRODUCT);

			saveProductRequest.setAction(SaveProductRequest.UPDATE_PRODUCT);

			saveProductRequest = setValuesToRequest(saveProductRequest);
			saveProductRequest.getProduct().setProductKey(
					super.getProductKeyFromSession());
			//calls service
			saveProductResponse = (SaveProductResponse) this
					.executeService(saveProductRequest);

			List msgListOne = new ArrayList(2);
			List msgListTwo = new ArrayList(2);

			if (null != saveProductResponse) {				
				if (null != saveProductResponse.getMessages())
					msgListOne = saveProductResponse.getMessages();
				if (saveProductResponse.isSuccess()) {
					if (null != saveProductResponse.getProductBO()) {
						setValuesToBackingBean(saveProductResponse
								.getProductBO(), saveProductResponse
								.getDomainDetail());
					}
					//setting action 1 for saving new details.can be done after
					// checking with change in data for edit action.
					// DOES CHECK IN
					saveProductRequest
							.setAction(SaveProductRequest.CHECKIN_PRODUCT);
					saveProductRequest.setCheckIn(this.checkin);
					
				if(null!=getSession().getAttribute("AM_BENEFIT"))
					getSession().removeAttribute("AM_BENEFIT");
				if(null!=getSession().getAttribute("AM_BC_KEY"))
					getSession().removeAttribute("AM_BC_KEY");
				if(null!=getSession().getAttribute("AM_ENTITY_KEY"))
					getSession().removeAttribute("AM_ENTITY_KEY");
				if(null!=getSession().getAttribute("DIRECT_CLICK"))
					getSession().removeAttribute("DIRECT_CLICK");
			       
//					
//					dsfdsfsdfsdfsdfsdf
					//saveProductRequest = setValuesToRequest(
					// saveProductRequest );
					saveProductRequest.getProduct().setProductKey(
							super.getProductKeyFromSession());

					saveProductResponse = (SaveProductResponse) this
							.executeService(saveProductRequest);

					if (null != saveProductResponse) {
						if (null != saveProductResponse.getMessages())
							msgListTwo = saveProductResponse.getMessages();
						msgListOne.addAll(msgListTwo);
						// Rule Validation
						this.deletedRulesList = saveProductResponse.
													getDeletedRulesList();
						this.unCodedRulesList = saveProductResponse.
													getUnCodedRulesList();
						getSession().setAttribute(
									WebConstants.SESSION_DELETED_RULES_LIST, 
									saveProductResponse.getDeletedRulesList());
						getSession().setAttribute(
									WebConstants.SESSION_UNCODED_RULES_LIST, 
									saveProductResponse.getUnCodedRulesList());
						if(saveProductResponse.getCondition() == SaveProductResponse.VALIDATION_RESULTS){
							hasValidationErrors = true;
							setValuesForAminMethodValidation();
							return "";
						}else if(saveProductResponse.getCondition() == SaveProductResponse.VALIDATION_WAIT){
							getSession().setAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN,
									"productGeneralInformationBackingBean");
							getSession().setAttribute(WebConstants.OBJECT_VALUE_FOR_CHECKIN,
									this);
							getSession().setAttribute(WebConstants.ENTITY_ID_FOR_CHECKIN, 
									new Integer(super.getProductKeyFromSession()));
							getSession().setAttribute(WebConstants.ENTITY_TYPE_FOR_CHECKIN, 
									WebConstants.PROD_TYPE);
							return "validationWait";
						}
						addAllMessagesToRequest(msgListOne);
						if (saveProductResponse.isSuccess()) {
							if (saveProductRequest.isCheckIn()) {
								clearBackingBeanValues();
								cleanSession();
								return "createProduct";
							}
						}
					}
				}

			}
		} else {
			addAllMessagesToRequest(validationMessages);
		}
		
		return "";
	}

	/**
	 * This method updates the product information
	 * 
	 * @return
	 */
	public String saveEdited() {
		getRequest().setAttribute("RETAIN_Value", "");
		//    	checking manadate
		if (this.productType.equals(WebConstants.STD_TYPE)) {
			this.mandateType = null;
			this.stateCode = null;
		}
		if (null == this.mandateType || "".equals(this.mandateType)
				|| this.mandateType.equals("1")) {
			this.stateCode = null;
		}

		//it first checks for the validation of all the fields.
		if (isAllFieldsValid()) {
			//request is created
			SaveProductRequest saveProductRequest = (SaveProductRequest) this
					.getServiceRequest(ServiceManager.SAVE_PRODUCT);

			saveProductRequest.setAction(SaveProductRequest.UPDATE_PRODUCT);

			saveProductRequest = setValuesToRequest(saveProductRequest);
			saveProductRequest.getProduct().setProductKey(
					super.getProductKeyFromSession());
			//this.setProductKeyObjectToRequest(saveProductRequest);
			SaveProductResponse saveProductResponse = null;
			//calls the service
			saveProductResponse = (SaveProductResponse) this
					.executeService(saveProductRequest);

			if (null != saveProductResponse) {
				if (saveProductResponse.isSuccess()) {
					if (null != saveProductResponse.getProductBO()) {
						setValuesToBackingBean(saveProductResponse
								.getProductBO(), saveProductResponse
								.getDomainDetail());
						
						 //Code change for product tree rendering optimization
						super.updateTreeStructure();
					}
				}
			}
		} else {
			addAllMessagesToRequest(validationMessages);
		}
		return "";
	}

	/**
	 * This method saves the information and creates a product.
	 * 
	 * @return
	 */
	public String saveGeneralInfo() {

		//    	checking manadate
		if (this.productType.equals(WebConstants.STANDARD_BENEFIT_SESSION_KEY)) {
			this.mandateType = null;
			this.stateCode = null;
		}
		if (null == this.mandateType || "".equals(this.mandateType)) {
			this.stateCode = null;
		}
		if(WebConstants.FEDERAL_TYPE.equals(this.mandateType))
		{
			this.stateCode = WebConstants.ALL_99;
		}
		/*
		 * String sta = this.stateCode; if( !("".equals(this.stateCode))){
		 * String[] arrayTilda = this.stateCode.split("~"); if(arrayTilda.length
		 * ==2){ this.stateCode = new String (arrayTilda[1] ); } }
		 */

		//it first checks for the validation of all the fields.
		if (isAllFieldsValid()) {
			//request is created
			SaveProductRequest saveProductRequest = (SaveProductRequest) this
					.getServiceRequest(ServiceManager.SAVE_PRODUCT);

			//sets action to the request based on the mode
			if (getProductSessionObject().getMode() == ProductSessionObject.COPY_MODE)
				saveProductRequest.setAction(SaveProductRequest.COPY_PRODUCT);
			else
				saveProductRequest.setAction(SaveProductRequest.CREATE_PRODUCT);

			//setting the values to request
			saveProductRequest = setValuesToRequest(saveProductRequest);

			SaveProductResponse saveProductResponse = null;
			//calls the service
			saveProductResponse = (SaveProductResponse) this
					.executeService(saveProductRequest);
            if(null == saveProductResponse){
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

			if (null != saveProductResponse) {
				if (saveProductResponse.isSuccess()) {
					if (null != saveProductResponse.getProductBO()) {
						//removes the copy mode from session
						if (getProductSessionObject().getMode() == ProductSessionObject.COPY_MODE) {
							getProductSessionObject().setMode(0);
						}
						//sets the values back to the backing bean.
						super.setMode(ProductSessionObject.EDIT_MODE);
						setValuesToBackingBean(saveProductResponse
								.getProductBO(), saveProductResponse
								.getDomainDetail());
						
						return "editProductGenInfo";
					}
				}
			}
		} else {
			//if any of the fileds to be saved is not valid, this returns the
			// message back.
			addAllMessagesToRequest(validationMessages);
		}
		return "";
	}

	/**
	 * Thius method clears the backing bean and sets to initial values.
	 *  
	 */
	private void clearBackingBeanValues() {
		this.lineOfBusinessDiv = WebConstants.ALL_99;
		this.businessEntityDiv = WebConstants.ALL_99;
		this.businessGroupDiv = WebConstants.ALL_99;
		this.marketBusinessUnitDiv = WebConstants.ALL_99;

		this.businessDomDiv = null;

		this.effectiveDate = WebConstants.DEFAULT_EFF_DATE;
		this.expiryDate = WebConstants.DEFAULT_EXP_DATE;

		this.productDescription = null;
		this.productFamilyDiv = null;
		this.productName = null;
		this.productStructDiv = null;
		this.productType = WebConstants.STD_TYPE_DISPLAY;
		this.mandateType=null;
		this.stateCode=null;

	}

	public String getInitView() {
		String productKey = getRequest().getParameter("productKey");
		String type = getRequest().getParameter("type");
		if (productKey != null) {
			int key = Integer.parseInt(productKey);
			if(WebConstants.VIEW_VERSION_TYPE.equals(type)){
			    super.setKeyObjectToSessionForEdit(key);
			    super.getProductSessionObject().setMode(ProductSessionObject.VIEW_MODE);
			}else{
			    super.setKeyObjectToSessionForView(key);   
			}
			RetrieveProductRequest request = (RetrieveProductRequest) getServiceRequest(ServiceManager.RETRIEVE_PRODUCT);
			request.setProductKey(key);
			RetrieveProductResponse response = (RetrieveProductResponse) executeService(request);
			if (response != null && response.getProductBO() != null) {
				setValuesToBackingBean(response.getProductBO(), response
						.getDomainDetail());
			}
		}
		return "abcd";
	}

	public void setInitView(String initView) {
	}

	/**
	 * this method works on the edit click on any search result of product
	 * 
	 * @return
	 */
	public String editAction() {
		int key = Integer.parseInt(selectedIdFromSearch);
		super.setKeyObjectToSessionForEdit(key);
		//gets request
		RetrieveProductRequest request = (RetrieveProductRequest) getServiceRequest(ServiceManager.RETRIEVE_PRODUCT);
		request.setEditMode(true);
		//calls the service
		RetrieveProductResponse response = (RetrieveProductResponse) executeService(request);
		if(response.isLockAquired()){
			if (response != null && response.getProductBO() != null) {
			
				setValuesToBackingBean(response.getProductBO(), response
					.getDomainDetail());
			
			}
			return "editProductGenInfo";
		}else {
			List messageList = response.getMessages();
			ProductSearchBackingBean productSearchBackingBean=(ProductSearchBackingBean)getRequest().getAttribute("productSearchBackingBean");
			productSearchBackingBean.productSearch();
			addAllMessagesToRequest(messageList);
			return "";
		}
		
	}
	/**
	 * loads the general information once the page is navigated from component
	 * view page to edit page
	 * 
	 * @return
	 */
	public String loadGeneralInfo() {
		//gets request
		RetrieveProductRequest request = (RetrieveProductRequest) getServiceRequest(ServiceManager.RETRIEVE_PRODUCT);
		request.setProductKey(super.getProductKeyFromSession());
		//calls the service
		RetrieveProductResponse response = (RetrieveProductResponse) executeService(request);
		if (response != null && response.getProductBO() != null) {
			setValuesToBackingBean(response.getProductBO(), response
					.getDomainDetail());
		}
		if (super.isViewMode())
			return "productGenInfoView";
		else
			return "editProductGenInfo";

	}

	/**
	 * this method is called when we click on view of the porduct
	 * 
	 * @return
	 */
	public String viewAction() {
		//gets request
		RetrieveProductRequest request = (RetrieveProductRequest) getServiceRequest(ServiceManager.RETRIEVE_PRODUCT);
		int key = Integer.parseInt(selectedIdFromSearch);
		super.setKeyObjectToSessionForEdit(key);
		//calls the service
		RetrieveProductResponse response = (RetrieveProductResponse) executeService(request);
		super.getProductSessionObject().setMode(ProductSessionObject.VIEW_MODE);
		if (response != null && response.getProductBO() != null) {
			setValuesToBackingBean(response.getProductBO(), response
					.getDomainDetail());
		}
		return "productGenInfoView";
	}

	/**
	 * This method sets the method back to backing bean
	 * 
	 * @param productBO
	 * @param domainDetail
	 */
	private void setValuesToBackingBean(ProductBO productBO,
			DomainDetail domainDetail) {
		this.productStructureVersion =String.valueOf(productBO.getProductStructureVersion());
		this.version = String.valueOf(productBO.getVersion());
		this.createdBy = productBO.getCreatedUser();

		Date createdDate = productBO.getCreatedTimestamp();

		String creationDateString = WPDStringUtil.getStringDate(createdDate);
		this.creationDate = createdDate;

		this.updatedBy = productBO.getLastUpdatedUser();

		Date updatedDate = productBO.getLastUpdatedTimestamp();
		String updationDateString = WPDStringUtil.getStringDate(updatedDate);
		this.updationDate = updatedDate;
		if (productBO.getState() != null) {
			this.state = productBO.getState().getState();
		}
		this.status = productBO.getStatus();

		//hidden field mapping
		this.productKey = Integer.toString(super.getProductKeyFromSession());

		this.productName = productBO.getProductName();
		this.effectiveDate = WPDStringUtil.getStringDate(productBO
				.getEffectiveDate());
		this.expiryDate = WPDStringUtil
				.getStringDate(productBO.getExpiryDate());
		this.productDescription = productBO.getProductDesc();
		if (productBO.getProductType() != null) {
			this.productType = productBO.getProductType().toUpperCase();
			this.hiddenProductType=productBO.getProductType().toUpperCase();
		}
		if (productBO.getMandateType() != null) {
			this.mandateType = productBO.getMandateType();
		}

		if (null == productBO.getStateId())
			this.stateCode = "";
		else
			this.stateCode = productBO.getStateId() + "~"
					+ productBO.getStateDesc();

		if (productBO.getProductStructureName() != null) {
			this.productStructDiv = "" + productBO.getProductStructureName()
					+ "~" + productBO.getProductStructureKey();
		}
//		if (productBO.getProductName() != null) {
//			this.productFamilyDiv = productBO.getProductFamilyDesc() + "~"
//					+ productBO.getProductFamilyId();
//		}
		this.productFamily = productBO.getProductFamilyId();
		if (productBO.getProductFamilyId() != null) {
			super.getProductSessionObject().setProductFamily(productBO.getProductFamilyId());
		}
		this.lineOfBusinessDiv = WPDStringUtil.getTildaString(domainDetail
				.getLineOfBusiness());
		this.businessEntityDiv = WPDStringUtil.getTildaString(domainDetail
				.getBusinessEntity());
		this.businessGroupDiv = WPDStringUtil.getTildaString(domainDetail
				.getBusinessGroup());
		//CARS START
		//Separation the tilda separated string from market business unit. 
		this.marketBusinessUnitDiv = WPDStringUtil.getTildaString(domainDetail
				.getMarketBusinessUnit());		
		//CARS END
		if (getProductSessionObject().getProductKeyObject().getVersion() > 0)
			higherVersion = true;
		else
			higherVersion = false;
		if (super.isViewMode()) {
			super.setBreadCumbTextForView();
		} else if (super.getProductSessionObject().getMode() == ProductSessionObject.COPY_MODE) {
			super.setBreadCumbTextForCreate();
		} else if (super.isEditMode()) {
			super.setBreadCumbTextForEdit();
		}
		if (productBO.getProductStructureKey() != null) {
			super.getProductSessionObject().setProductStructKey(Integer.parseInt(productBO.getProductStructureKey()));
		}
	}

	/**
	 * This method is used for setting hte values to the request
	 * 
	 * @param saveProductRequest
	 * @return
	 */
	private SaveProductRequest setValuesToRequest(
			SaveProductRequest saveProductRequest) {
		//saving new data

		//expects Integer list
		List lobList = WPDStringUtil.getListFromTildaString(
				this.lineOfBusinessDiv, 2, 2, 2);
		List entityList = WPDStringUtil.getListFromTildaString(
				this.businessEntityDiv, 2, 2, 2);
		List groupList = WPDStringUtil.getListFromTildaString(
				this.businessGroupDiv, 2, 2, 2);
		//CARS START
		//Converting the tilda separated string to a list.
		List marketBusinessUnitList = WPDStringUtil.getListFromTildaString(
				this.marketBusinessUnitDiv, 2, 2, 2);
		List domainList = BusinessUtil.convertToDomains(lobList, entityList,
				groupList, marketBusinessUnitList);
		//CARS END
		List structureList = WPDStringUtil.getListFromTildaString(
				this.productStructDiv, 2, 2, 1);
		/*sepetmber relase changes
		//expects String List
		List familyList = WPDStringUtil.getListFromTildaString(
				this.productFamilyDiv, 2, 2, 2); */
		if(null!= stateCode){
		//    		To split the stateId values
		StringTokenizer st = null;
		st = new StringTokenizer(stateCode, "~");
		while (st.hasMoreTokens()) {
			this.stateId = st.nextToken();
			this.stateDesc = st.nextToken();
		}
		}
		ProductVO productVO = new ProductVO();
		productVO.setBusinessDomains(domainList);

		productVO.setEffectiveDate(WPDStringUtil
				.getDateFromString(this.effectiveDate));
		productVO.setExpiryDate(WPDStringUtil
				.getDateFromString(this.expiryDate));
		productVO.setProductDesc(this.productDescription.trim().toUpperCase());
		productVO.setProductName(this.productName.trim().toUpperCase());

		//mandate
		productVO.setProductType(this.productType.trim().toUpperCase());
		if (null != mandateType) {
			productVO.setMandateType(this.mandateType.trim().toUpperCase());
		}
		productVO.setHiddenProductType(this.hiddenProductType);
		String stateId = new String();
		if (null != this.stateCode) {
			if (!("".equals(this.stateCode))) {
				String[] arrayTilda = this.stateCode.split("~");
				if (arrayTilda.length == 2) {
					stateId = (arrayTilda[0]);
				}
			}
		}
		productVO.setStateId(stateId.trim().toUpperCase());

		if (null != this.stateDesc)
			productVO.setStateDesc(this.stateDesc.trim().toUpperCase());
		if (null != this.stateId)
			productVO.setStateId(this.stateId.trim().toUpperCase());

		/*sepetmber relase changes 
		productVO.setProductFamilyId((String) familyList.get(0));
		*/
		productVO.setProductFamilyId(this.productFamily);
		Integer iStructureId = (Integer) structureList.get(0);
		productVO.setProductStructureKey(iStructureId.intValue());

		saveProductRequest.setProduct(productVO);
		return saveProductRequest;

	}

	/**
	 * This method is used for checking whether all fields to be saved are
	 * valid.
	 * 
	 * @return
	 */
	private boolean isAllFieldsValid() {

		Date cutOffDate = WPDStringUtil.getDateFromString("01/01/1900");
		if (isMandatoryFieldsValid()) {
			//checks whether the effective date is valid
			if (!(WPDStringUtil.isValidDate(this.effectiveDate))) {
				ErrorMessage errorMessage = new ErrorMessage(
						WebConstants.INPUT_FORMAT_INVALID);
				errorMessage.setParameters(new String[] { "Effective Date" });
				validationMessages.add(errorMessage);
				invalidFieldMessage = true;
				effectiveDateInvalid = true;
			}
			//checks whether the expiryDate is valid
			if (!(WPDStringUtil.isValidDate(this.expiryDate))) {

				ErrorMessage errorMessage = new ErrorMessage(
						WebConstants.INPUT_FORMAT_INVALID);
				errorMessage.setParameters(new String[] { "Expiry Date" });
				validationMessages.add(errorMessage);
				invalidFieldMessage = true;
				expiryDateInvalid = true;
			}
			//compares effectiveDate and expiryDate
			if ((WPDStringUtil.isValidDate(this.effectiveDate))) {
				if (WPDStringUtil.getDateFromString(this.effectiveDate)
						.compareTo(cutOffDate) < 0) {
					validationMessages.add(new ErrorMessage(
							WebConstants.INVALID_EFFECTIVE_DATE));
					invalidFieldMessage = true;
					effectiveDateInvalid = true;
				}
			}
			//compares expiryDate with cut-off date
			if ((WPDStringUtil.isValidDate(this.expiryDate))) {
				if (WPDStringUtil.getDateFromString(this.expiryDate).compareTo(
						cutOffDate) < 0) {
					validationMessages.add(new ErrorMessage(
							WebConstants.INVALID_EXPIRY_DATE));
					invalidFieldMessage = true;
					expiryDateInvalid = true;
				}
			}
			//compares the dates are equal
			if (WPDStringUtil.isValidDate(this.effectiveDate)
					&& WPDStringUtil.isValidDate(this.expiryDate)) {
				Date effectiveDate = WPDStringUtil
						.getDateFromString(this.effectiveDate);
				Date expiryDate = WPDStringUtil
						.getDateFromString(this.expiryDate);
				if (null != effectiveDate && null != expiryDate) {
					if (effectiveDate.compareTo(expiryDate) > 0
							|| effectiveDate.compareTo(expiryDate) == 0) {
						validationMessages.add(new ErrorMessage(
								WebConstants.EXPIRY_GREATER_EFFECTIVE_DATE));
						invalidFieldMessage = true;
						effectiveDateInvalid = true;
						expiryDateInvalid = true;
					}
				}
			}

			//checks whether description is in between 10 and 250 characters

			if (this.descInvalid == false) {

				String desc = this.getProductDescription();
				desc = desc.trim();
				char[] charDesc = desc.toCharArray();
				if (charDesc.length < 10 || charDesc.length > 250) {
					descFiledNotValid = true;
					invalidFieldMessage = true;
					this.setDescInvalid(true);
				} else {
					this.setDescInvalid(false);
				}
				if (descFiledNotValid) {
					validationMessages.add(new ErrorMessage(
							WebConstants.INVALID_DESCRIPTION));
				}
			}
			//checks whether description is in between 1 and 30 characters
			if (this.nameInvalid == false) {
				descFiledNotValid = false;
				String name = this.getProductName();
				name = name.trim();
				char[] charName = name.toCharArray();
				if (charName.length < 1 || charName.length > 30) {
					descFiledNotValid = true;
					invalidFieldMessage = true;
					this.setNameInvalid(true);
				} else {
					this.setNameInvalid(false);
				}
				if (descFiledNotValid) {
					validationMessages.add(new ErrorMessage(
							WebConstants.NAME_SIZE_INVALID));
				}
			}

			if (invalidFieldMessage) {
				//will add invalid field messages to request
				return false;
			} else {
				return true;
			}

		}
		//if any of the mandatoryfields not valid
		return false;
	}

	/**
	 * This method checks whether the manadtoryfields are valid
	 * 
	 * @return
	 */
	private boolean isMandatoryFieldsValid() {

		validationMessages = new ArrayList(1);
		boolean invalidField = false;
		if (null == this.lineOfBusinessDiv
				|| "".equals(this.lineOfBusinessDiv.trim())) {

			lobInvalid = true;
			invalidField = true;
		}
		if (null == this.businessEntityDiv
				|| "".equals(this.businessEntityDiv.trim())) {

			entityInvalid = true;
			invalidField = true;
		}
		if (null == this.businessGroupDiv
				|| "".equals(this.businessGroupDiv.trim())) {

			groupInvalid = true;
			invalidField = true;
		}
		//CARS START
		//Validation for market business unit.
		if (null == this.marketBusinessUnitDiv
				|| "".equals(this.marketBusinessUnitDiv.trim())) {

			marketBusinessUnitInvalid = true;
			invalidField = true;
		}
		//CARS END
//		Commneted for sepetember Cr :-product family commented
//		if (null == this.productFamilyDiv
//				|| "".equals(this.productFamilyDiv.trim())) {
//
//			familyInvalid = true;
//			invalidField = true;
//		}

		if (null == this.effectiveDate || "".equals(this.effectiveDate.trim())) {

			effectiveDateInvalid = true;
			invalidField = true;
		}
		if (null == this.expiryDate || "".equals(this.expiryDate.trim())) {

			expiryDateInvalid = true;
			invalidField = true;
		}
		if (null == this.productName || "".equals(this.productName.trim())) {

			nameInvalid = true;
			invalidField = true;
		}
		//checks for the fields with mandate

		if (null == this.productType || "".equals(this.productType)) {
			requiredProductType = true;
			invalidField = true;
		}

		if (this.productType.equals(WebConstants.MNDT_TYPE)) {
			if ((null == this.mandateType) || ("".equals(this.mandateType))) {
				requiredMandateType = true;
				invalidField = true;
			}
		}
		if (this.productType.equals(WebConstants.MNDT_TYPE)
				&& "ET".equals(this.mandateType)
				|| "ST".equals(this.mandateType)) {
			if ((null == this.stateCode) || ("".equals(this.stateCode))) {
				requiredState = true;
				invalidField = true;
			}
		}

		if (null == this.productDescription
				|| "".equals(this.productDescription.trim())) {

			descInvalid = true;
			invalidField = true;
		} else {
			descInvalid = false;
		}
		if (null == this.productStructDiv
				|| "".equals(this.productStructDiv.trim())) {

			structureInvalid = true;
			invalidField = true;
		}

		if (invalidField) {
			validationMessages.add(new ErrorMessage(
					WebConstants.MANDATORY_FIELDS_REQUIRED));
			return false;
		}

		return true;

	}

	/**
	 * @return Returns the productTypeListForCombo.
	 */
	public List getProductTypeListForCombo() {
		productTypeListForCombo = new ArrayList(2);

		productTypeListForCombo.add(new SelectItem(WebConstants.STD_TYPE,
				WebConstants.STD_TYPE_DISPLAY));
		productTypeListForCombo.add(new SelectItem(WebConstants.MNDT_TYPE,
				WebConstants.MNDT_TYPE_DISPLAY));
		return productTypeListForCombo;
	}

	public String getViewAction() {
		int key = Integer.parseInt(selectedIdFromSearch);
		//ProductSearchResult productSearchResult =
		// super.getLocateObjectFromSession(key);
		//cleanSession();
		super.setKeyObjectToSessionForView(key);
		getProductSessionObject().setMode(ProductSessionObject.VIEW_MODE);
		RetrieveProductRequest request = (RetrieveProductRequest) getServiceRequest(ServiceManager.RETRIEVE_PRODUCT);
		request.setProductKey(key);
		RetrieveProductResponse response = (RetrieveProductResponse) executeService(request);
		if (response != null && response.getProductBO() != null) {
			setValuesToBackingBean(response.getProductBO(), response
					.getDomainDetail());
		}
		return "productGenInfoView";
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
	 * Returns the creationDate
	 * 
	 * @return String creationDate.
	 *  
	 */

	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * 
	 * Sets the creationDate
	 * 
	 * @param creationDate.
	 *  
	 */

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
	 * Returns the productDescription
	 * 
	 * @return String productDescription.
	 *  
	 */

	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * 
	 * Sets the productDescription
	 * 
	 * @param productDescription.
	 *  
	 */

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * 
	 * Returns the productName
	 * 
	 * @return String productName.
	 *  
	 */

	public String getProductName() {
		return productName;
	}

	/**
	 * 
	 * Sets the productName
	 * 
	 * @param productName.
	 *  
	 */

	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * 
	 * Returns the productStructure
	 * 
	 * @return String productStructure.
	 *  
	 */

	public String getProductStructure() {
		return productStructure;
	}

	/**
	 * 
	 * Sets the productStructure
	 * 
	 * @param productStructure.
	 *  
	 */

	public void setProductStructure(String productStructure) {
		this.productStructure = productStructure;
	}

	/**
	 * 
	 * Returns the productStructureList
	 * 
	 * @return List productStructureList.
	 *  
	 */

	public List getProductStructureList() {
		final String LOB_REQ_PARAM = WebConstants.LOB_URL_PARM_NAME;
		final String BE_REQ_PARAM = WebConstants.BE_REQ_PARAM_NAME;
		final String BG_REQ_PARAM = WebConstants.BG_REQ_PARAM_NAME;
		/*START CARS*/
		final String MBU_REQ_PARAM = WebConstants.MBU_REQ_PARAM;
		/*END CARS*/
		final String EFF_DATE_REQ_PARAM = WebConstants.EFF_DATE_REQ_PARAM_NAME;
		final String EXP_DATE_REQ_PARAM = WebConstants.EXP_DATE_REQ_PARAM_NAME;
		final String PRODUCT_TYPE = WebConstants.PRODUCT_TYPE;
		final String MANDATE_TYPE = WebConstants.MANDATE_TYPE_NAME;
		final String STATE_CODE = WebConstants.STATE_CODE;

		if (!this.productStructureListRetrieved) {
			List lineOfBusiness = WPDStringUtil.getListFromTildaString(
					(String) getRequest().getParameter(LOB_REQ_PARAM), 2, 2, 2);
			List businessEntity = WPDStringUtil.getListFromTildaString(
					(String) getRequest().getParameter(BE_REQ_PARAM), 2, 2, 2);
			List businessGroup = WPDStringUtil.getListFromTildaString(
					(String) getRequest().getParameter(BG_REQ_PARAM), 2, 2, 2);
			/*START CARS*/
			List marketBusinessUnit = WPDStringUtil.getListFromTildaString(
					(String) getRequest().getParameter(MBU_REQ_PARAM), 2, 2, 2);
			/*END CARS*/
			Date effectiveDate = WPDStringUtil
					.getDateFromString((String) getRequest().getParameter(
							EFF_DATE_REQ_PARAM));
			Date expiryDate = WPDStringUtil
					.getDateFromString((String) getRequest().getParameter(
							EXP_DATE_REQ_PARAM));
			this.productType = getRequest().getParameter(PRODUCT_TYPE);
			if (WebConstants.MNDT_TYPE.equals(productType)) {
				this.mandateType = getRequest().getParameter(MANDATE_TYPE);
				if (!("1").equals(mandateType)) {
					this.stateCode = getRequest().getParameter(STATE_CODE);
				}
			}
			if (null != stateCode && !("").equals(stateCode)) {
				//    		To split the stateId values
				StringTokenizer st = null;
				st = new StringTokenizer(stateCode, "~");
				while (st.hasMoreTokens()) {
					this.stateId = st.nextToken();
					this.stateDesc = st.nextToken();
				}
			}
			RetrieveValidProductStructuresRequest request = (RetrieveValidProductStructuresRequest) getServiceRequest(ServiceManager.RETRIEVE_VALID_PROD_STRS);
			request.setLineOfBusiness(lineOfBusiness);
			request.setBusinessGroup(businessGroup);
			request.setBusinessEntity(businessEntity);
	        /*START CARS*/
			request.setMarketBusinessUnit(marketBusinessUnit);
	        /*END CARS*/
			request.setEffectiveDate(effectiveDate);
			request.setExpiryDate(expiryDate);
			request.setProductType(productType);
			request.setMandateType(mandateType);
			request.setStateCode(stateId);
			RetrieveValidProductStructuresResponse response = (RetrieveValidProductStructuresResponse) executeService(request);
			if (response != null) {
				List list = response.getValidProductStructureList();
				if (null == list || list.size() == 0) {
					this.productStructureList = null;
				} else {
					this.productStructureList = response
							.getValidProductStructureList();
				}
				this.productStructureListRetrieved = true;
			}
		}

		return productStructureList;
	}

	/**
	 * 
	 * Sets the productStructureList
	 * 
	 * @param productStructureList.
	 *  
	 */

	public void setProductStructureList(List productStructureList) {
		this.productStructureList = productStructureList;
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
	 * Sets the updatedBy
	 * 
	 * @param updatedBy.
	 *  
	 */

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * 
	 * Returns the updationDate
	 * 
	 * @return String updationDate.
	 *  
	 */

	public Date getUpdationDate() {
		return updationDate;
	}

	/**
	 * 
	 * Sets the updationDate
	 * 
	 * @param updationDate.
	 *  
	 */

	public void setUpdationDate(Date updationDate) {
		this.updationDate = updationDate;
	}

	/**
	 * This function is called when the action is made on the Create button in
	 * the createProduct.jsp
	 * 
	 * @return createProduct String
	 */
	public String create() {
		return "createProduct";
	}

	/**
	 * This function is called when the action is made on the Save button in the
	 * productGeneralInformation.jsp
	 * 
	 * @return saveProduct String
	 */
	public String save() {
		return "saveProduct";
	}

	/**
	 * 
	 * Returns the businessDomDiv
	 * 
	 * @return String businessDomDiv.
	 *  
	 */

	public String getBusinessDomDiv() {
		return businessDomDiv;
	}

	/**
	 * 
	 * Sets the businessDomDiv
	 * 
	 * @param businessDomDiv.
	 *  
	 */

	public void setBusinessDomDiv(String businessDomDiv) {
		this.businessDomDiv = businessDomDiv;
	}

	/**
	 * 
	 * Returns the businessEntityDiv
	 * 
	 * @return String businessEntityDiv.
	 *  
	 */

	public String getBusinessEntityDiv() {
		return businessEntityDiv;
	}

	/**
	 * 
	 * Sets the businessEntityDiv
	 * 
	 * @param businessEntityDiv.
	 *  
	 */

	public void setBusinessEntityDiv(String businessEntityDiv) {
		this.businessEntityDiv = businessEntityDiv;
	}

	/**
	 * 
	 * Returns the businessGroupDiv
	 * 
	 * @return String businessGroupDiv.
	 *  
	 */

	public String getBusinessGroupDiv() {
		return businessGroupDiv;
	}

	/**
	 * 
	 * Sets the businessGroupDiv
	 * 
	 * @param businessGroupDiv.
	 *  
	 */

	public void setBusinessGroupDiv(String businessGroupDiv) {
		this.businessGroupDiv = businessGroupDiv;
	}

	/**
	 * 
	 * Returns the lineOfBusinessDiv
	 * 
	 * @return String lineOfBusinessDiv.
	 *  
	 */

	public String getLineOfBusinessDiv() {
		return lineOfBusinessDiv;
	}

	/**
	 * 
	 * Sets the lineOfBusinessDiv
	 * 
	 * @param lineOfBusinessDiv.
	 *  
	 */

	public void setLineOfBusinessDiv(String lineOfBusinessDiv) {
		this.lineOfBusinessDiv = lineOfBusinessDiv;
	}

	/**
	 * 
	 * Returns the productFamilyDiv
	 * 
	 * @return String productFamilyDiv.
	 *  
	 */

	public String getProductFamilyDiv() {
		return productFamilyDiv;
	}

	/**
	 * 
	 * Sets the productFamilyDiv
	 * 
	 * @param productFamilyDiv.
	 *  
	 */

	public void setProductFamilyDiv(String productFamilyDiv) {
		this.productFamilyDiv = productFamilyDiv;
	}

	/**
	 * 
	 * Returns the productStructDiv
	 * 
	 * @return String productStructDiv.
	 *  
	 */

	public String getProductStructDiv() {
		return productStructDiv;
	}

	/**
	 * 
	 * Sets the productStructDiv
	 * 
	 * @param productStructDiv.
	 *  
	 */

	public void setProductStructDiv(String productStructDiv) {
		this.productStructDiv = productStructDiv;
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
	 * Returns the version
	 * 
	 * @return String version.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the version
	 * 
	 * @param version.
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 
	 * Returns the validationMessages
	 * 
	 * @return List validationMessages.
	 *  
	 */

	public List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * 
	 * Sets the validationMessages
	 * 
	 * @param validationMessages.
	 *  
	 */

	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * 
	 * Returns the descInvalid
	 * 
	 * @return boolean descInvalid.
	 *  
	 */

	public boolean isDescInvalid() {
		return descInvalid;
	}

	/**
	 * 
	 * Sets the descInvalid
	 * 
	 * @param descInvalid.
	 *  
	 */

	public void setDescInvalid(boolean descInvalid) {
		this.descInvalid = descInvalid;
	}

	/**
	 * 
	 * Returns the effectiveDateInvalid
	 * 
	 * @return boolean effectiveDateInvalid.
	 *  
	 */

	public boolean isEffectiveDateInvalid() {
		return effectiveDateInvalid;
	}

	/**
	 * 
	 * Sets the effectiveDateInvalid
	 * 
	 * @param effectiveDateInvalid.
	 *  
	 */

	public void setEffectiveDateInvalid(boolean effectiveDateInvalid) {
		this.effectiveDateInvalid = effectiveDateInvalid;
	}

	/**
	 * 
	 * Returns the entityInvalid
	 * 
	 * @return boolean entityInvalid.
	 *  
	 */

	public boolean isEntityInvalid() {
		return entityInvalid;
	}

	/**
	 * 
	 * Sets the entityInvalid
	 * 
	 * @param entityInvalid.
	 *  
	 */

	public void setEntityInvalid(boolean entityInvalid) {
		this.entityInvalid = entityInvalid;
	}

	/**
	 * 
	 * Returns the expiryDateInvalid
	 * 
	 * @return boolean expiryDateInvalid.
	 *  
	 */

	public boolean isExpiryDateInvalid() {
		return expiryDateInvalid;
	}

	/**
	 * 
	 * Sets the expiryDateInvalid
	 * 
	 * @param expiryDateInvalid.
	 *  
	 */

	public void setExpiryDateInvalid(boolean expiryDateInvalid) {
		this.expiryDateInvalid = expiryDateInvalid;
	}

	/**
	 * 
	 * Returns the familyInvalid
	 * 
	 * @return boolean familyInvalid.
	 *  
	 */

	public boolean isFamilyInvalid() {
		return familyInvalid;
	}

	/**
	 * 
	 * Sets the familyInvalid
	 * 
	 * @param familyInvalid.
	 *  
	 */

	public void setFamilyInvalid(boolean familyInvalid) {
		this.familyInvalid = familyInvalid;
	}

	/**
	 * 
	 * Returns the groupInvalid
	 * 
	 * @return boolean groupInvalid.
	 *  
	 */

	public boolean isGroupInvalid() {
		return groupInvalid;
	}

	/**
	 * 
	 * Sets the groupInvalid
	 * 
	 * @param groupInvalid.
	 *  
	 */

	public void setGroupInvalid(boolean groupInvalid) {
		this.groupInvalid = groupInvalid;
	}

	/**
	 * 
	 * Returns the lobInvalid
	 * 
	 * @return boolean lobInvalid.
	 *  
	 */

	public boolean isLobInvalid() {
		return lobInvalid;
	}

	/**
	 * 
	 * Sets the lobInvalid
	 * 
	 * @param lobInvalid.
	 *  
	 */

	public void setLobInvalid(boolean lobInvalid) {
		this.lobInvalid = lobInvalid;
	}

	/**
	 * 
	 * Returns the nameInvalid
	 * 
	 * @return boolean nameInvalid.
	 *  
	 */

	public boolean isNameInvalid() {
		return nameInvalid;
	}

	/**
	 * 
	 * Sets the nameInvalid
	 * 
	 * @param nameInvalid.
	 *  
	 */

	public void setNameInvalid(boolean nameInvalid) {
		this.nameInvalid = nameInvalid;
	}

	/**
	 * 
	 * Returns the productStructureBo1
	 * 
	 * @return ProductStructureBO productStructureBo1.
	 *  
	 */

	public ProductStructureBO getProductStructureBo1() {
		return productStructureBo1;
	}

	/**
	 * 
	 * Sets the productStructureBo1
	 * 
	 * @param productStructureBo1.
	 *  
	 */

	public void setProductStructureBo1(ProductStructureBO productStructureBo1) {
		this.productStructureBo1 = productStructureBo1;
	}

	/**
	 * 
	 * Returns the productStructureBo2
	 * 
	 * @return ProductStructureBO productStructureBo2.
	 *  
	 */

	public ProductStructureBO getProductStructureBo2() {
		return productStructureBo2;
	}

	/**
	 * 
	 * Sets the productStructureBo2
	 * 
	 * @param productStructureBo2.
	 *  
	 */

	public void setProductStructureBo2(ProductStructureBO productStructureBo2) {
		this.productStructureBo2 = productStructureBo2;
	}

	/**
	 * 
	 * Returns the structureInvalid
	 * 
	 * @return boolean structureInvalid.
	 *  
	 */

	public boolean isStructureInvalid() {
		return structureInvalid;
	}

	/**
	 * 
	 * Sets the structureInvalid
	 * 
	 * @param structureInvalid.
	 *  
	 */

	public void setStructureInvalid(boolean structureInvalid) {
		this.structureInvalid = structureInvalid;
	}

	/**
	 * @return Returns the productKey.
	 */
	public String getProductKey() {
		return productKey;
	}

	/**
	 * @param productKey
	 *            The productKey to set.
	 */
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	/**
	 * Returns the selectedIdFromSearch
	 * 
	 * @return String selectedIdFromSearch.
	 */
	public String getSelectedIdFromSearch() {
		return selectedIdFromSearch;
	}

	/**
	 * Sets the selectedIdFromSearch
	 * 
	 * @param selectedIdFromSearch.
	 */
	public void setSelectedIdFromSearch(String selectedIdFromSearch) {
		this.selectedIdFromSearch = selectedIdFromSearch;
	}

	/**
	 * this method is for copying a product
	 * 
	 * @return
	 */
	public String copyAction() {
		int key = Integer.parseInt(selectedIdFromSearch);
		super.setKeyObjectToSessionForEdit(key);
		this.setCopyFlag(true);
		getProductSessionObject().setMode(ProductSessionObject.COPY_MODE);
		//gets request
		RetrieveProductRequest request = (RetrieveProductRequest) getServiceRequest(ServiceManager.RETRIEVE_PRODUCT);
		RetrieveProductResponse response = (RetrieveProductResponse) executeService(request);
		if (response != null && response.getProductBO() != null) {
			setValuesToBackingBean(response.getProductBO(), response
					.getDomainDetail());
			//            setProductKeyObject(response.getProductBO());
		}
		return "copyProduct";
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
	 * Returns the descFiledNotValid
	 * 
	 * @return boolean descFiledNotValid.
	 */
	public boolean isDescFiledNotValid() {
		return descFiledNotValid;
	}

	/**
	 * Sets the descFiledNotValid
	 * 
	 * @param descFiledNotValid.
	 */
	public void setDescFiledNotValid(boolean descFiledNotValid) {
		this.descFiledNotValid = descFiledNotValid;
	}

	/**
	 * Returns the invalidFieldMessage
	 * 
	 * @return boolean invalidFieldMessage.
	 */
	public boolean isInvalidFieldMessage() {
		return invalidFieldMessage;
	}

	/**
	 * Sets the invalidFieldMessage
	 * 
	 * @param invalidFieldMessage.
	 */
	public void setInvalidFieldMessage(boolean invalidFieldMessage) {
		this.invalidFieldMessage = invalidFieldMessage;
	}

	/**
	 * Returns the productStructureListRetrieved
	 * 
	 * @return boolean productStructureListRetrieved.
	 */
	public boolean isProductStructureListRetrieved() {
		return productStructureListRetrieved;
	}

	/**
	 * Sets the productStructureListRetrieved
	 * 
	 * @param productStructureListRetrieved.
	 */
	public void setProductStructureListRetrieved(
			boolean productStructureListRetrieved) {
		this.productStructureListRetrieved = productStructureListRetrieved;
	}

	public String getInitViewForPrint() {
		RetrieveProductRequest request = (RetrieveProductRequest) getServiceRequest(ServiceManager.RETRIEVE_PRODUCT);
		RetrieveProductResponse response = (RetrieveProductResponse) executeService(request);
		if (null != response)
			setValuesToBackingBean(response.getProductBO(), response
					.getDomainDetail());
		return "";
	}

	public void setInitViewForPrint(String initViewForPrint) {
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
	 * Returns the rendered
	 * 
	 * @return boolean rendered.
	 */
	public boolean isRendered() {
		return rendered;
	}

	/**
	 * Sets the rendered
	 * 
	 * @param rendered.
	 */
	public void setRendered(boolean rendered) {
		this.rendered = rendered;
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
	 * @param printValue
	 *            The printValue to set.
	 */
	public void setPrintValue(String printValue) {
		this.printValue = printValue;
	}

	/**
	 * Returns the hiddenInit
	 * 
	 * @return String hiddenInit.
	 */
	public String getHiddenInit() {
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
	 * @return Returns the requiredProductType.
	 */
	public boolean isRequiredProductType() {
		return requiredProductType;
	}

	/**
	 * @param requiredProductType
	 *            The requiredProductType to set.
	 */
	public void setRequiredProductType(boolean requiredProductType) {
		this.requiredProductType = requiredProductType;
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
	 * @param productTypeListForCombo
	 *            The productTypeListForCombo to set.
	 */
	public void setProductTypeListForCombo(List productTypeListForCombo) {
		this.productTypeListForCombo = productTypeListForCombo;
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
	 * @return Returns the hiddenProductType.
	 */
	public String getHiddenProductType() {
		return hiddenProductType;
	}

	/**
	 * @param hiddenProductType
	 *            The hiddenProductType to set.
	 */
	public void setHiddenProductType(String hiddenProductType) {
		this.hiddenProductType = hiddenProductType;
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
	 * @return printBreadCrumbText
	 * 
	 * Returns the printBreadCrumbText.
	 */
	public String getPrintBreadCrumbText() {
		printBreadCrumbText = "Product Configuration >> Product ("
				+ getProductSessionObject().getProductKeyObject()
						.getProductName() + ") >> Print";
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
	 * @return Returns the deletedRulesList.
	 */
	public List getDeletedRulesList() {
		return deletedRulesList;
	}
	/**
	 * @param deletedRulesList The deletedRulesList to set.
	 */
	public void setDeletedRulesList(List deletedRulesList) {
		this.deletedRulesList = deletedRulesList;
	}
	/**
	 * @return Returns the unCodedRulesList.
	 */
	public List getUnCodedRulesList() {		
		return unCodedRulesList;
	}
	/**
	 * @param unCodedRulesList The unCodedRulesList to set.
	 */
	public void setUnCodedRulesList(List unCodedRulesList) {
		this.unCodedRulesList = unCodedRulesList;
	}
	/**
	 * @return Returns the loadRuleValidationPopUp.
	 */
	public String getLoadRuleValidationPopUp() {
		if(null != getSession()){
			if(null != getSession().getAttribute
					(WebConstants.SESSION_DELETED_RULES_LIST))
				this.deletedRulesList = (List) getSession().
								getAttribute(WebConstants.
										SESSION_DELETED_RULES_LIST);
			if(null != getSession().getAttribute
					(WebConstants.SESSION_UNCODED_RULES_LIST))
				this.unCodedRulesList = (List) getSession().
								getAttribute(WebConstants.
										SESSION_UNCODED_RULES_LIST);
			this.productName = getProductNameFromSession();
			this.version = getVersionFromSession() + "";
		}
		return loadRuleValidationPopUp;
	}
	/**
	 * @param loadRuleValidationPopUp The loadRuleValidationPopUp to set.
	 */
	public void setLoadRuleValidationPopUp(String loadRuleValidationPopUp) {
		this.loadRuleValidationPopUp = loadRuleValidationPopUp;
	}
	
	/**
	 * @return Returns the productStructureVersion.
	 */
	public String getProductStructureVersion() {
		return productStructureVersion;
	}
	/**
	 * @param productStructureVersion The productStructureVersion to set.
	 */
	public void setProductStructureVersion(String productStructureVersion) {
		this.productStructureVersion = productStructureVersion;
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
	 * @return Returns the marketBusinessUnitDiv.
	 */
	public String getMarketBusinessUnitDiv() {
		return marketBusinessUnitDiv;
	}
	/**
	 * @param marketBusinessUnitDiv The marketBusinessUnitDiv to set.
	 */
	public void setMarketBusinessUnitDiv(String marketBusinessUnitDiv) {
		this.marketBusinessUnitDiv = marketBusinessUnitDiv;
	}
	/**
	 * @return Returns the marketBusinessUnitInvalid.
	 */
	public boolean isMarketBusinessUnitInvalid() {
		return marketBusinessUnitInvalid;
	}
	/**
	 * @param marketBusinessUnitInvalid The marketBusinessUnitInvalid to set.
	 */
	public void setMarketBusinessUnitInvalid(boolean marketBusinessUnitInvalid) {
		this.marketBusinessUnitInvalid = marketBusinessUnitInvalid;
	}
}