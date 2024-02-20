/*
 * ProductComponentAssociationBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerPopupDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerRuleIdDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerSPSIdDisplayVO;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.framework.bo.StateFactory;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.contract.request.ContractWebServiceRequest;
import com.wellpoint.wpd.common.contract.request.DeleteContractProductAdminRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractProductAdminRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBasicInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractProductAdminRequest;
import com.wellpoint.wpd.common.contract.request.SaveDateSegmentCommentRequest;
import com.wellpoint.wpd.common.contract.response.ContractWebServiceResponse;
import com.wellpoint.wpd.common.contract.response.DeleteContractProductAdminResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractProductAdminResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBasicInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractProductAdminResponse;
import com.wellpoint.wpd.common.contract.response.SaveDateSegmentCommentResponse;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractProductAdminOptionBackingBean extends ContractBackingBean {

    private boolean adminOptionInvalid = false;

    private String state = null;

    private String status = null;

    private int version = 0;

    private List adminList = new ArrayList();

    private String adminString = null;

    private String printValue;
    
    private String printValueAdmin; 

    private boolean checkin;
   
    List validationMessages=null;
    
    private boolean securityAccess;
    
    private boolean hasValidationErrors;
    
    private String adminOptionHidden;
    
    private String deleteAdminString;
    
    private int dateSegmentId;
    
    // eBX error validation
	
	private String invokeWebService;
	
	// SSCR 16332  -START
	private List<EbxWebSerDisplayVO> ebxWebSerDisplayList = null;
	private int initEbxWs;
	private String webServiceFlag ="false";
	private int initEbxRuleId;
	private int initEbxSPSId;
	private List<EbxWebSerRuleIdDisplayVO> ebxWebSerRuleIdDisplayLst = null;
	private List<EbxWebSerSPSIdDisplayVO> ebxWebSerSPSIdDisplayLst = null;
	private String ruleId;
	private String spsId;
	EbxWebSerPopupDisplayVO ebxWebSerPopupDisplayVO;
	private String ebxProcessInterruptMsg = null;
	
	
	
	public List<EbxWebSerDisplayVO> getEbxWebSerDisplayList() {
		return ebxWebSerDisplayList;
	}

	public void setEbxWebSerDisplayList(
			List<EbxWebSerDisplayVO> ebxWebSerDisplayList) {
		this.ebxWebSerDisplayList = ebxWebSerDisplayList;
	}

	public int getInitEbxWs() {
		this.ebxWebSerDisplayList = (List<EbxWebSerDisplayVO>) getSession().getAttribute("wSErrorDisplayList");
		this.ebxWebSerPopupDisplayVO =(EbxWebSerPopupDisplayVO)getSession().getAttribute("ebxWebSerPopupDisplayVO");
		this.ebxProcessInterruptMsg = (String)getSession().getAttribute("ebxProcessInterruptMsg");
		return initEbxWs;
	}
	
	public void setInitEbxWs(int initEbxWs) {
		this.initEbxWs = initEbxWs;
	}
	
	public String getWebServiceFlag() {
		return webServiceFlag;
	}

	public void setWebServiceFlag(String webServiceFlag) {
		this.webServiceFlag = webServiceFlag;
	}
	public int getInitEbxRuleId() {
		HttpServletRequest request = getRequest();
		String ruleId = null;
		if(null!=request.getParameter("ruleId")){
			ruleId=request.getParameter("ruleId");
		}
		if(ruleId != null){	
			this.setRuleId(ruleId);
			List<ContractWebServiceVO> contractWSErrorList =(List<ContractWebServiceVO>) getSession().getAttribute("contractWSErrorList");
			this.setEbxWebSerRuleIdDisplayLst(DomainUtil.ruleIdPopupDisplay(contractWSErrorList,ruleId));
		}
		
		return initEbxRuleId;
	}

	public void setInitEbxRuleId(int initEbxRuleId) {
		this.initEbxRuleId = initEbxRuleId;
	}

	public int getInitEbxSPSId() {
		HttpServletRequest request = getRequest();
		String spsId = null;
		if(null!=request.getParameter("spsId")){
			spsId=request.getParameter("spsId");
		}
		if(spsId != null){	
			this.setSpsId(spsId);
			List<ContractWebServiceVO> contractWSErrorList =(List<ContractWebServiceVO>) getSession().getAttribute("contractWSErrorList");
			this.setEbxWebSerSPSIdDisplayLst(DomainUtil.spsIdPopupDisplay(contractWSErrorList,spsId));
		}
		return initEbxSPSId;
	}

	public void setInitEbxSPSId(int initEbxSPSId) {
		this.initEbxSPSId = initEbxSPSId;
	}

	public List<EbxWebSerRuleIdDisplayVO> getEbxWebSerRuleIdDisplayLst() {
		return ebxWebSerRuleIdDisplayLst;
	}

	public void setEbxWebSerRuleIdDisplayLst(
			List<EbxWebSerRuleIdDisplayVO> ebxWebSerRuleIdDisplayLst) {
		this.ebxWebSerRuleIdDisplayLst = ebxWebSerRuleIdDisplayLst;
	}
	
	public List<EbxWebSerSPSIdDisplayVO> getEbxWebSerSPSIdDisplayLst() {
		return ebxWebSerSPSIdDisplayLst;
	}

	public void setEbxWebSerSPSIdDisplayLst(
			List<EbxWebSerSPSIdDisplayVO> ebxWebSerSPSIdDisplayLst) {
		this.ebxWebSerSPSIdDisplayLst = ebxWebSerSPSIdDisplayLst;
	}
	
	
	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getSpsId() {
		return spsId;
	}

	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}
	
	public EbxWebSerPopupDisplayVO getEbxWebSerPopupDisplayVO() {
		return ebxWebSerPopupDisplayVO;
	}

	public void setEbxWebSerPopupDisplayVO(
			EbxWebSerPopupDisplayVO ebxWebSerPopupDisplayVO) {
		this.ebxWebSerPopupDisplayVO = ebxWebSerPopupDisplayVO;
	}

	// SSCR 16332 - END

	/**
	 * @return the invokeWebService
	 */
	public String getInvokeWebService() {
		return invokeWebService;
	}

	/**
	 * @param invokeWebService the invokeWebService to set
	 */
	public void setInvokeWebService(String invokeWebService) {
		this.invokeWebService = invokeWebService;
	}
    
   //sscr 17571
	private String vendorFlag = "false";
	
	private String closePopup = "false";
		  
	
	public String getClosePopup() {
		return closePopup;
	}
	public void setClosePopup(String closePopup) {
		this.closePopup = closePopup;
	}
	
	public String getVendorFlag() {
		return vendorFlag;
	}
	public void setVendorFlag(String vendorFlag) {
		this.vendorFlag = vendorFlag;
	}

   

    public ContractProductAdminOptionBackingBean() {
        //Setting the BreadCumb.
        this.setBreadCrumbText();
    }
	
    public String done() {
    	
        List adminKeyList = WPDStringUtil.getListFromTildaString(adminString, 2, 1, 1);
        SaveContractProductAdminRequest saveContractProductAdminRequest = (SaveContractProductAdminRequest) this
                .getServiceRequest(ServiceManager.SAVE_CONTRACT_PRODUCT_ADMIN);
        saveContractProductAdminRequest.setAction(SaveContractProductAdminRequest.SAVE_ADMIN);
        int productKey = super.getContractSession().getProductId();
        saveContractProductAdminRequest.setProductKey(productKey);
        saveContractProductAdminRequest.setAdminList(adminKeyList);
        SaveContractProductAdminResponse saveContractProductAdminResponse = null;
        List msgListOne = new ArrayList();
        List msgListTwo = new ArrayList();
        if(!(null == this.getAdminString() || "".equals(this.getAdminString().trim()) )) {
        	 saveContractProductAdminResponse = (SaveContractProductAdminResponse) executeService(saveContractProductAdminRequest);
             if (null != saveContractProductAdminResponse && saveContractProductAdminResponse.isSuccess()) {
             	msgListOne = saveContractProductAdminResponse.getMessages();
             	adminOptionInvalid = false;
             }
             else{
             	addAllMessagesToRequest(validationMessages);
             	return WebConstants.EMPTY_STRING;
             }
		}
        
    	SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
		.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
    	SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
    	ContractVO contractVO = new ContractVO();
    	contractVO.setContractSysId(this.getContractSession().getContractKeyObject().getContractSysId());
		contractVO.setContractId(this.getContractSession().getContractKeyObject().getContractId());
		contractVO.setVersion(this.getContractSession().getContractKeyObject().getVersion());
		contractVO.setStatus(this.getContractSession().getContractKeyObject().getStatus());
		saveContractBasicInfoRequest.setContractVO(contractVO);
    	saveContractBasicInfoRequest.setAction(SaveContractBasicInfoRequest.CHECKIN_CONTRACT);
    	saveContractBasicInfoRequest.setChechIn(this.checkin);
    	// SSCR 16332 -Start
		if (this.checkin && !getInvokeWebService().equals("confirm") && getInvokeWebService() != null) {
			saveContractBasicInfoRequest.setEBXWS(true);
			saveContractBasicInfoRequest.setCarvConfirm(true); // Tab impl SSCR 17571
		}
		if (null != getSession().getAttribute("contractWSErrorList")){
			getSession().removeAttribute("contractWSErrorList");
		}
		if (null != getSession().getAttribute("wSErrorDisplayList")){
			getSession().removeAttribute("wSErrorDisplayList"); 
		}
		if(null != getSession().getAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST)){
			getSession().removeAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST);
		}
		saveContractBasicInfoRequest.setEbxAndCarvErrorsByPassCmts(this.getInvokeWebService());
		// SSCR 16332 -End
    	if (null != getSession().getAttribute("AM_BENEFIT"))
            getSession().removeAttribute("AM_BENEFIT");   // clearing for adminmethod contract validation popup
    	if (null != getSession().getAttribute("AM_BC_KEY"))
            getSession().removeAttribute("AM_BC_KEY");   // clearing for adminmethod contract validation popup
    	if (null != getSession().getAttribute("DIRECT_CLICK"))
            getSession().removeAttribute("DIRECT_CLICK");   // clearing for adminmethod contract validation popup   
    	saveContractBasicInfoResponse = (SaveContractBasicInfoResponse) executeService(saveContractBasicInfoRequest);
    	if (null != saveContractBasicInfoResponse) {
    		if(saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_RESULTS){
				hasValidationErrors = true;
				setValuesForAdminMethodValidation();
				getRequest().setAttribute("RETAIN_Value", "");
				return "";
			}else if(saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.SPS_VALIDATION_ERROR){
		 		getSession().setAttribute("AM_CONTRACT_ID",getContractSession().getContractKeyObject().getContractSysId() + "");
				if (null != getSession().getAttribute("adminmethodContractCodedSPSTreeBackingBean"))
					getSession().removeAttribute("adminmethodContractCodedSPSTreeBackingBean");
				setValuesForAdminMethodValidation();
				  if(msgListOne!=null){
				  	msgListOne.addAll(saveContractBasicInfoResponse.getMessages());
					if(this.validationMessages!=null )
						this.validationMessages.addAll(msgListOne);
					else{
		    			this.validationMessages= new ArrayList();
	    			this.validationMessages.addAll(msgListOne);}
				  }
		 		return "";
			}else if(saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_WAIT){
				getSession().setAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN,
						"contractProductAdminOptionBackingBean");
				getSession().setAttribute(WebConstants.OBJECT_VALUE_FOR_CHECKIN,
						this);
				getSession().setAttribute(WebConstants.ENTITY_ID_FOR_CHECKIN, 
						new Integer(saveContractBasicInfoRequest.getContractKeyObject().getDateSegmentId()));
				getSession().setAttribute(WebConstants.CONTRACT_ID_FOR_CHECKIN,
						new Integer(saveContractBasicInfoRequest.getContractKeyObject().getContractSysId()));
				getSession().setAttribute(WebConstants.ENTITY_TYPE_FOR_CHECKIN, 
						WebConstants.ENTITY_TYPE_CONTRACT);
				getSession().setAttribute("AM_ENTITY_NAME",getContractSession().getContractKeyObject().getContractId());
				return "validationWait";
			}else if(saveContractBasicInfoResponse.getCondition() == SaveProductResponse.DO_EBX_WEBSERVICE_PROCESS || saveContractBasicInfoResponse.getCarvedOutCondition() == SaveProductResponse.DO_CARVEDOUT_PROCESS ){
				//SSCR 17571 - Tab Impl
				if(null !=saveContractBasicInfoResponse.getCarvedoutMap() && !saveContractBasicInfoResponse.getCarvedoutMap().isEmpty()){
					getSession().setAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST,saveContractBasicInfoResponse.getCarvedoutMap());
					this.setVendorFlag("true");	
				}//SSCR 17571 - Tab Impl
				if(saveContractBasicInfoResponse.getCondition() == SaveProductResponse.DO_EBX_WEBSERVICE_PROCESS ){
					  List<ContractWebServiceVO> contractWSErrorList = null;
					  List<EbxWebSerDisplayVO> wSErrorDisplayList =null;
					
					if(saveContractBasicInfoResponse.getContractWSErrorList() != null && !saveContractBasicInfoResponse.getContractWSErrorList().isEmpty()){
						contractWSErrorList =saveContractBasicInfoResponse.getContractWSErrorList();	
					}
					if(saveContractBasicInfoResponse.getWSErrorDisplayList() != null  && !saveContractBasicInfoResponse.getWSErrorDisplayList().isEmpty()){
						wSErrorDisplayList = saveContractBasicInfoResponse.getWSErrorDisplayList();
					}
					if(contractWSErrorList != null && wSErrorDisplayList !=null &&!contractWSErrorList.isEmpty() && !wSErrorDisplayList.isEmpty()){
						this.setWebServiceFlag("true");
						getSession().setAttribute("contractWSErrorList",contractWSErrorList);
						getSession().setAttribute("wSErrorDisplayList",wSErrorDisplayList);
						ContractKeyObject contractKeyObject = getContractSession().getContractKeyObject();
						this.ebxWebSerPopupDisplayVO = DomainUtil.ebxPopupDisplayValues(contractKeyObject);
						getSession().setAttribute("ebxWebSerPopupDisplayVO",ebxWebSerPopupDisplayVO);
						this.setEbxWebSerDisplayList(wSErrorDisplayList);
						
					}
					
				}else if(saveContractBasicInfoResponse.getCondition() == SaveProductResponse.DO_EBX_WEBSERVICE_PROCESS_FAILURE){
					this.setWebServiceFlag("true");
					this.ebxProcessInterruptMsg = saveContractBasicInfoResponse.getWsProcessError();
					getSession().setAttribute("ebxProcessInterruptMsg",ebxProcessInterruptMsg);
					ContractKeyObject contractKeyObject = getContractSession().getContractKeyObject();
					this.ebxWebSerPopupDisplayVO = DomainUtil.ebxPopupDisplayValues(contractKeyObject);
					getSession().setAttribute("ebxWebSerPopupDisplayVO",ebxWebSerPopupDisplayVO);
					
				}
				this.getSession().setAttribute("VendorFlag",vendorFlag);
				this.getSession().setAttribute("WebServiceFlag",webServiceFlag);
				return "displayContractAdminOption";
			}else{
	    		if (null != saveContractBasicInfoResponse.getMessages()) {
					// Rule Validation
					getSession().setAttribute(
							WebConstants.SESSION_DELETED_RULES_LIST, 
							saveContractBasicInfoResponse.getDeletedRulesList());
					getSession().setAttribute(
							WebConstants.SESSION_UNCODED_RULES_LIST, 
							saveContractBasicInfoResponse.getUnCodedRulesList());				
	    			msgListTwo = saveContractBasicInfoResponse.getMessages();
	    			msgListOne.addAll(msgListTwo);
	    			addAllMessagesToRequest(msgListOne);
	    			this.validationMessages= new ArrayList();
	    			this.validationMessages.addAll(msgListOne);
	    		}
	    		if (saveContractBasicInfoResponse.isSuccess()) {
	    			this.setAdminString("");
	    			super.clearSession();
	    			return "CONTRACTCREATE";
	    		}else if(saveContractBasicInfoResponse.isIfUncodedLineNotesExist()){
					this.setIfUncodedLineNotesExist("Yes");
				}
			}
    	}
	this.checkin = false;
	adminOptionInvalid = false;
	this.setAdminString("");
	getRequest().setAttribute("RETAIN_Value", "");
	return WebConstants.EMPTY_STRING;
  }
    
    
    public String doContractCancelAction(){ 
		this.setWebServiceFlag("false");
		this.setVendorFlag("false");
    	getSession().removeAttribute("contractWSErrorList");
    	getSession().removeAttribute("wSErrorDisplayList");
    	getSession().removeAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST);
    	getSession().removeAttribute("VendorFlag");
    	getSession().removeAttribute("WebServiceFlag");
    	getSession().removeAttribute("ebxProcessInterruptMsg");
    	return "";
    }

    /**
     * Returns the panel
     * 
     * @return HtmlPanelGrid panel.
     */

    public String displayContractAdminOption() {
    	if(getContractSession().getContractKeyObject().getContractType().equals("MANDATE")){
    		validationMessages = new ArrayList();
            //adminoption.mandatory.type=No Admin Option allowed for mandate product type.
            this.validationMessages.add(new ErrorMessage(WebConstants.ADMINOPTION_MANDATE_TYPE_MESSAGE));
            addAllMessagesToRequest(validationMessages);
    		return"";
    	}else{
    		RetrieveContractProductAdminRequest request = (RetrieveContractProductAdminRequest) this
																.getServiceRequest(ServiceManager.CONTRACT_PRODUCT_ADMIN);
        request.setAction(RetrieveContractProductAdminRequest.PRODUCT_ADMIN_ADDED);
        request.setProductKey(super.getContractSession().getProductId());
        RetrieveContractProductAdminResponse retrieveContractProductAdminResponse = (RetrieveContractProductAdminResponse) executeService(request);

        this.adminList = retrieveContractProductAdminResponse.getAdminList();
        addAllMessagesToRequest(validationMessages);
		if (super.isViewMode())
            return "displayContractAdminOptionView";
        else
            return "displayContractAdminOption";        	
    	}
    }

    /**
     * This method deletes the selected admin and returns a new list of admin.
     * 
     * @return.
     */
    public String deleteAdmin() {
        DeleteContractProductAdminRequest productAdminDeleteRequest = (DeleteContractProductAdminRequest) this
                .getServiceRequest(ServiceManager.DELETE_CONTRACT_PRODUCT_ADMIN);
        if (deleteAdminString != null) {
            productAdminDeleteRequest.setAdminKey(WPDStringUtil.getListFromTildaString(this.deleteAdminString,1,1,1));  
        }
        DeleteContractProductAdminResponse productAdminDeleteResponse = (DeleteContractProductAdminResponse) executeService(productAdminDeleteRequest);
        
        if (null != productAdminDeleteResponse) {
        	this.validationMessages = productAdminDeleteResponse.getMessages();
        	updateTreeStructure();
            this.displayContractAdminOption();
        }
        getRequest().setAttribute("RETAIN_Value", "");
        return "displayContractAdminOption";
    }

    /**
     * This method saves the selected admin and returns a new list of admin.
     * 
     * @return.
     */
    public String saveProductAdmin() {
     	int productKey = super.getContractSession().getProductId();
        List adminKeyList = WPDStringUtil.getListFromTildaString(adminString, 2, 1, 1);
        SaveContractProductAdminRequest saveContractProductAdminRequest = (SaveContractProductAdminRequest) this
                	.getServiceRequest(ServiceManager.SAVE_CONTRACT_PRODUCT_ADMIN);
        saveContractProductAdminRequest.setAction(SaveContractProductAdminRequest.SAVE_ADMIN);
        saveContractProductAdminRequest.setAdminList(adminKeyList);
        saveContractProductAdminRequest.setProductKey(productKey);        
        SaveContractProductAdminResponse saveContractProductAdminResponse = null;
        
        if(!isMandatoryFieldsValid()){
        	addAllMessagesToRequest(validationMessages);
            return "displayContractAdminOption";
        }
        saveContractProductAdminResponse = (SaveContractProductAdminResponse) executeService(saveContractProductAdminRequest);
        if (null != saveContractProductAdminResponse && saveContractProductAdminResponse.isSuccess()) {
        	updateTreeStructure();
        	this.validationMessages = saveContractProductAdminResponse.getMessages();
            this.displayContractAdminOption();
    		this.setAdminString("");
    		getRequest().setAttribute("RETAIN_Value", "");
            return "displayContractAdminOption";
        }
        return "";
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
     * This method checks whether the manadtoryfields are valid
     * @return
     */
    private boolean isMandatoryFieldsValid(){
		
		validationMessages = new ArrayList();
		boolean invalidField = false;

		if(null == this.getAdminString() || "".equals(this.getAdminString().trim()) ) {
			adminOptionInvalid = true;
			invalidField = true;
		}
		if (invalidField) {
            validationMessages.add(new ErrorMessage(WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }
        return true;
	}
    /**
     * @return securityAccess
     * 
     * Returns the securityAccess.
     */
    public boolean isSecurityAccess() {
        try{

            securityAccess = getUser().isAuthorized(WebConstants.ADMIN_OPTION_MODULE,
            StateFactory.DELETE_TASK);

        } catch (SevereException e) {
        	Logger.logError(e);
        }
        return securityAccess;
    }
    /**
     * @param securityAccess
     * 
     * Sets the securityAccess.
     */
    public void setSecurityAccess(boolean securityAccess) {
        this.securityAccess = securityAccess;
    }

    /**
     * @return Returns the state.
     */
    public String getState() {
        return getContractSession().getContractKeyObject().getState();
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
        return getContractSession().getContractKeyObject().getStatus();
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
        return getContractSession().getContractKeyObject().getVersion();
    }


    /**
     * @param version
     *            The version to set.
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * @param printValue
     *            The printValue to set.
     */
    public void setPrintValue(String printValue) {
        this.printValue = printValue;
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
     * @return Returns the adminList.
     */
    public List getAdminList() {
    	//displayContractAdminOption();
        return adminList;
    }


    /**
     * @param adminList The adminList to set.
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
     * @param adminString The adminString to set.
     */
    public void setAdminString(String adminString) {
        this.adminString = adminString;
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
	 * Returns the adminOptionInvalid
	 * @return boolean adminOptionInvalid.
	 */
	public boolean isAdminOptionInvalid() {
		return adminOptionInvalid;
	}
	/**
	 * Sets the adminOptionInvalid
	 * @param adminOptionInvalid.
	 */
	public void setAdminOptionInvalid(boolean adminOptionInvalid) {
		this.adminOptionInvalid = adminOptionInvalid;
	}
	/**
	 * Returns the printValueAdmin
	 * @return String printValueAdmin.
	 */
	public String getPrintValueAdmin() {
		  String requestForPrint = getRequest().getParameter(
          "printContractAdminOption");
  if (null != requestForPrint && !requestForPrint.equals("")) {
      printValue = requestForPrint;
  } else {
      printValue = "";
  }
  return printValue;
	}
	/**
	 * Sets the printValueAdmin
	 * @param printValueAdmin.
	 */
	public void setPrintValueAdmin(String printValueAdmin) {
		this.printValueAdmin = printValueAdmin;
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
	 * @return Returns the adminOptionHidden.
	 */
	public String getAdminOptionHidden() {
		displayContractAdminOption();
		return adminOptionHidden;
	}
	/**
	 * @param adminOptionHidden The adminOptionHidden to set.
	 */
	public void setAdminOptionHidden(String adminOptionHidden) {
		this.adminOptionHidden = adminOptionHidden;
	}
	/**
	 * @return Returns the deleteAdminString.
	 */
	public String getDeleteAdminString() {
		return deleteAdminString;
	}
	/**
	 * @param deleteAdminString The deleteAdminString to set.
	 */
	public void setDeleteAdminString(String deleteAdminString) {
		this.deleteAdminString = deleteAdminString;
	}
	/**
	 * @return Returns the dateSegmentId.
	 */
	public int getDateSegmentId() {
		dateSegmentId = getContractSession().getContractKeyObject().getDateSegmentId();
		return dateSegmentId;
	}
	/**
	 * @param dateSegmentId The dateSegmentId to set.
	 */
	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	
}