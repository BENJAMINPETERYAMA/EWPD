/*
 * ContractPricingInfoBackingBean.java
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
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.contract.request.ContractWebServiceRequest;
import com.wellpoint.wpd.common.contract.request.DeletePricingInfoRequest;
import com.wellpoint.wpd.common.contract.request.LocatePricingInformationRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBasicInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveDateSegmentCommentRequest;
import com.wellpoint.wpd.common.contract.request.SavePricingInfoRequest;
import com.wellpoint.wpd.common.contract.response.ContractWebServiceResponse;
import com.wellpoint.wpd.common.contract.response.DeletePricingInfoResponse;
import com.wellpoint.wpd.common.contract.response.LocatePricingInformationResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBasicInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveDateSegmentCommentResponse;
import com.wellpoint.wpd.common.contract.response.SavePricingInfoResponse;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author U15236
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractPricingInfoBackingBean extends ContractBackingBean 
{
    private String coverage;   
    private String pricing;    
    private String network;
    
    private String selectedContractDSSysID;
    private String selectedCoverageID; 
    private String selectedPricingID; 
    private String selectedNetworkID;
    private String printValue;
    
    private boolean coverageInvalid = false;
    private boolean pricingInvalid = false;   
    private	boolean networkInvalid = false;

	private List validationMessages;
    
    private List pricingInformationList = null;
    
    private boolean checkIn;
	private String checkin;
	private String state;
	private String status;
	private int version;
	
    private boolean renderFlag = false;
    private String loadForPrint;
    //variable for reference data
    private String contractIdForRefData;
    
    private String hiddenLoadPricing;
    private boolean hasValidationErrors;
    
    private String lob;
    private String be;
    private String bg;
    /*START CARS*/
    private String mbu;
    /*END CARS*/
    
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
	public String getEbxProcessInterruptMsg() {
		return ebxProcessInterruptMsg;
	}

	public void setEbxProcessInterruptMsg(String ebxProcessInterruptMsg) {
		this.ebxProcessInterruptMsg = ebxProcessInterruptMsg;
	}

	// SSCR 16332 - END
	
	// sscr 17571
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
	/**
	 * @return Returns the contractIdForRefData.
	 */
	public String getContractIdForRefData() {
		return contractIdForRefData;
	}
	/**
	 * @param contractIdForRefData The contractIdForRefData to set.
	 */
	public void setContractIdForRefData(String contractIdForRefData) {
		this.contractIdForRefData = contractIdForRefData;
	}
	/**
	 * 
	 */
	public ContractPricingInfoBackingBean() {
		super();
		this.setBreadCrumbText();
	}
    /**
     * Method to display the Contract Pricing Information Tab
     * @param
     * @return String
     */	
	public String dispalyContractPricingInfoTab()
	{
		Logger.logInfo("Entering the method for displaying Contract Pricing Information Tab");
	
		this.pricingInformationList = this.retrivePricingInformationList();		
		//Setting the value for reference data
        this.setContractIdForRefData(new Integer(this.getContractKeyObject().getContractSysId()).toString());
		if (super.isViewMode())
			return "displayContractPricingInfoViewTab";
		else
		return "contractPricingInformation";
	}
	public void setInitViewForPrint(String temp){
		
	}
	public String getInitViewForPrint(){
		this.pricingInformationList = this.retrivePricingInformationList();	
		return"";
	}

//Action: Delete Pricing Information selected Record	
	public String deleteRecordPricingInfo()
	{
     	DeletePricingInfoRequest deletePricingInfoRequest = (DeletePricingInfoRequest)
											this.getServiceRequest(ServiceManager.DELETE_PRICING_INFO_REQUEST);
     	deletePricingInfoRequest.setContractCoverage(WPDStringUtil.getListFromTildaString(this.getSelectedCoverageID(),1,1,2)); 
     	deletePricingInfoRequest.setContractPricing(WPDStringUtil.getListFromTildaString(this.getSelectedPricingID(),1,1,2));
     	deletePricingInfoRequest.setContractNetwork(WPDStringUtil.getListFromTildaString(this.getSelectedNetworkID(),1,1,2));
		DeletePricingInfoResponse deletePricingInfoResponse = 
	        	(DeletePricingInfoResponse)executeService(deletePricingInfoRequest);
       	if(null != deletePricingInfoResponse && deletePricingInfoResponse.isSuccess())
       	{
       	// set  save  message
       		this.setValidationMessages(deletePricingInfoResponse.getMessages());           		
       	}
		this.pricingInformationList = this.retrivePricingInformationList();
        addAllMessagesToRequest(this.validationMessages);           
        getRequest().setAttribute("RETAIN_Value", "");
	        return "contractPricingInformation";
	}
     
//Action: Add and Save pricing information	
	public String addAndStorePricingInfo()
	{
        SavePricingInfoRequest savePricingInfoRequest = null;
        SavePricingInfoResponse savePricingInfoResponse = null;
        //it first checks for the validation of all the fields.
        if(isMandatoryFieldsValid())
        {
        	//request is created
        	savePricingInfoRequest = (SavePricingInfoRequest)this.getServiceRequest(ServiceManager.SAVE_ADD_PRICING_INFO);

        	savePricingInfoRequest = setValuesToRequest(savePricingInfoRequest);
        	//calls the service
        	savePricingInfoResponse = (SavePricingInfoResponse) this.executeService(savePricingInfoRequest);
 

           	if(null != savePricingInfoResponse)
           	{
           		if(savePricingInfoResponse.isSuccess()){
	           	    this.coverage = "";
	           	    this.pricing = "";
	           	    this.network = "" ;
	           	    getRequest().setAttribute("RETAIN_Value", "");
           		}
               	// set  message
           	 this.setValidationMessages(savePricingInfoResponse.getMessages());      
           	}       	    
       		     		
        }
		pricingInformationList = this.retrivePricingInformationList();		         	        
        addAllMessagesToRequest(this.validationMessages);   
		return "contractPricingInformation";
	}
	
	
	public String done() {
		
		SavePricingInfoRequest savePricingInfoRequest = null;
		SavePricingInfoResponse savePricingInfoResponse = null;
		
		List msgListOne = new ArrayList();
		List msgListTwo = new ArrayList();
		//it first checks for the validation of all the fields.
		if (isMandatoryFieldsValid() || this.retrivePricingInformationList().size() !=0 || ("SHELL".equals(this.getContractKeyObject().getContractType()))) {
			if(isMandatoryFieldsValid()){
			//request is created
			savePricingInfoRequest = (SavePricingInfoRequest) this
					.getServiceRequest(ServiceManager.SAVE_ADD_PRICING_INFO);

			savePricingInfoRequest = setValuesToRequest(savePricingInfoRequest);
			//calls the service
			savePricingInfoResponse = (SavePricingInfoResponse) this
					.executeService(savePricingInfoRequest);
				if (null != savePricingInfoResponse
						&& savePricingInfoResponse.isSuccess()) {
					// set save message
					msgListOne = savePricingInfoResponse.getMessages();
					this.coverage = "";
					this.pricing = "";
					this.network = "";
					getRequest().setAttribute("RETAIN_Value", "");
					pricingInformationList = this.retrivePricingInformationList();
				}
			}//When all the s values coverage, network and pricing is not selected in the page during check-in. 
			if (this.coverage.equals(WebConstants.EMPTY_STRING)
					&& this.pricing.equals(WebConstants.EMPTY_STRING)
					&& this.network.equals(WebConstants.EMPTY_STRING)) {
				coverageInvalid = false;
				pricingInvalid = false;
				networkInvalid = false;
				
				SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
						.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);

				SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
				ContractVO contractVO = new ContractVO();
				contractVO.setContractSysId(this.getContractSession().getContractKeyObject().getContractSysId());
				contractVO.setContractId(this.getContractSession().getContractKeyObject().getContractId());
				contractVO.setVersion(this.getContractSession().getContractKeyObject().getVersion());
				contractVO.setStatus(this.getContractSession().getContractKeyObject().getStatus());
				saveContractBasicInfoRequest.setContractVO(contractVO);
				saveContractBasicInfoRequest
						.setAction(SaveContractBasicInfoRequest.CHECKIN_CONTRACT);
				saveContractBasicInfoRequest.setChechIn(this.checkIn);
				// SSCR 16332 -Start
				if (this.checkIn && !getInvokeWebService().equals("confirm") && getInvokeWebService() != null) {
					saveContractBasicInfoRequest.setEBXWS(true);
					saveContractBasicInfoRequest.setCarvConfirm(true);
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
				saveContractBasicInfoRequest.setEbxAndCarvErrorsByPassCmts(this.getInvokeWebService());//Tab impl
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
				 		return "";
					}else if(saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_WAIT){
	    				getSession().setAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN,
	    						"contractPricingInfoBackingBean");
	    				getSession().setAttribute(WebConstants.OBJECT_VALUE_FOR_CHECKIN,
	    						this);
	    				getSession().setAttribute("AM_ENTITY_NAME",getContractSession().getContractKeyObject().getContractId());
	    				getSession().setAttribute(WebConstants.ENTITY_ID_FOR_CHECKIN, 
	    						new Integer(saveContractBasicInfoRequest.getContractKeyObject().getDateSegmentId()));
	    				getSession().setAttribute(WebConstants.CONTRACT_ID_FOR_CHECKIN,
	    						new Integer(saveContractBasicInfoRequest.getContractKeyObject().getContractSysId()));
	    				getSession().setAttribute(WebConstants.ENTITY_TYPE_FOR_CHECKIN, 
	    						WebConstants.ENTITY_TYPE_CONTRACT);
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
						return "contractPricingInformation";
					}else{
						if (null != saveContractBasicInfoResponse.getMessages()) {
							// Rule Validation
							getSession().setAttribute(
									WebConstants.SESSION_DELETED_RULES_LIST, 
									saveContractBasicInfoResponse.getDeletedRulesList());
							getSession().setAttribute(
									WebConstants.SESSION_UNCODED_RULES_LIST, 
									saveContractBasicInfoResponse.getUnCodedRulesList());
							msgListTwo = saveContractBasicInfoResponse
									.getMessages();
							msgListOne.addAll(msgListTwo);
							addAllMessagesToRequest(msgListOne);
							this.validationMessages= new ArrayList();
							this.validationMessages.addAll(msgListOne);
						}
						if (saveContractBasicInfoResponse.isSuccess()) {
							this.clearBackingBeanValues();
							super.clearSession();
							return "CONTRACTCREATE";
						}else if(saveContractBasicInfoResponse.isIfUncodedLineNotesExist()){
							this.setIfUncodedLineNotesExist("Yes");
						}
	    			}
				}
			}
		}			
		addAllMessagesToRequest(validationMessages);
		this.checkIn = false;
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
//---------------------------------------------helper method----------------------------------
	private SavePricingInfoRequest setValuesToRequest(SavePricingInfoRequest savePricingInfoRequest)
	{
		List coverageList;
		List pricingList;
		List networkList;
		
		coverageList = WPDStringUtil.getListFromTildaString(this.getCoverage(), 2,2, 2);
		pricingList = WPDStringUtil.getListFromTildaString(this.getPricing(), 2,2, 2);
		networkList = WPDStringUtil.getListFromTildaString(this.getNetwork(), 2,2, 2);
		
		savePricingInfoRequest.setContractCoverage(coverageList.get(0).toString());
		savePricingInfoRequest.setContractPricing(pricingList.get(0).toString());
		savePricingInfoRequest.setContractNetwork(networkList.get(0).toString());
		
		return savePricingInfoRequest;
	}
		
	public void clearBackingBeanValues(){
		this.coverage = "";
   	    this.pricing = "";
   	    this.network = "" ;
	}
	
    /**
     * This method checks whether the manadtoryfields are valid
     * @return
     */
    private boolean isMandatoryFieldsValid(){
		
		validationMessages = new ArrayList();
		boolean invalidField = false;

		if(null == this.getCoverage() || "".equals(this.getCoverage().trim()) ) {
			coverageInvalid = true;
			invalidField = true;
		}
		if(null == this.getNetwork() || "".equals(this.getNetwork().trim()) ) {
			networkInvalid = true;
			invalidField = true;
		}
		if(null == this.getPricing() || "".equals(this.getPricing().trim()) ) {
			pricingInvalid = true;
			invalidField = true;
		}
		
		if (invalidField) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }
        return true;
		
	}
    	 	
		public List retrivePricingInformationList() {
			LocatePricingInformationRequest locatePricingInformationRequest = (LocatePricingInformationRequest)
			this.getServiceRequest(ServiceManager.LOCATE_PRICING_INFO);

			LocatePricingInformationResponse locatePricingInformationResponse = 
			(LocatePricingInformationResponse)executeService(locatePricingInformationRequest);
			
	       	if(null != locatePricingInformationResponse && locatePricingInformationResponse.isSuccess())
	       	{
				pricingInformationList = locatePricingInformationResponse.getPricingInformationList();		
				if(pricingInformationList != null && pricingInformationList.size()>0)
				{
					this.renderFlag = true;  
				} 
	       	}
			return pricingInformationList;
		}
	     
//---------------------------------getters/setters-----------------------	
		
	 /**
     * Returns the loadForPrint
     * @return String loadForPrint.
     */
    public String getLoadForPrint() {
        this.pricingInformationList = this.retrivePricingInformationList();	
        return loadForPrint;
    }
    /**
     * Sets the loadForPrint
     * @param loadForPrint.
     */
    public void setLoadForPrint(String loadForPrint) {
        this.loadForPrint = loadForPrint;
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
	 * @return Returns the coverage.
	 */
	public String getCoverage() {
		return coverage;
	}
	/**
	 * @param coverage The coverage to set.
	 */
	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}
	/**
	 * @return Returns the network.
	 */
	public String getNetwork() {
		return network;
	}
	/**
	 * @param network The network to set.
	 */
	public void setNetwork(String network) {
		this.network = network;
	}
	/**
	 * @return Returns the pricing.
	 */
	public String getPricing() {
		return pricing;
	}
	/**
	 * @param pricing The pricing to set.
	 */
	public void setPricing(String pricing) {
		this.pricing = pricing;
	}
	/**
	 * Returns the pricingInformationList
	 * @return List pricingInformationList.
	 */
	public List getPricingInformationList() {
		return pricingInformationList;
	}
	/**
	 * Sets the pricingInformationList
	 * @param pricingInformationList.
	 */
	public void setPricingInformationList(List pricingInformationList) {
		this.pricingInformationList = pricingInformationList;
	}
	/**
	 * Returns the checkin
	 * @return String checkin.
	 */
	public String getCheckin() {
		return checkin;
	}
	/**
	 * Sets the checkin
	 * @param checkin.
	 */
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	/**
	 * Returns the selectedCoverageID
	 * @return String selectedCoverageID.
	 */
	public String getSelectedCoverageID() {
		return selectedCoverageID;
	}
	/**
	 * Sets the selectedCoverageID
	 * @param selectedCoverageID.
	 */
	public void setSelectedCoverageID(String selectedCoverageID) {
		this.selectedCoverageID = selectedCoverageID;
	}
	/**
	 * Returns the selectedNetworkID
	 * @return String selectedNetworkID.
	 */
	public String getSelectedNetworkID() {
		return selectedNetworkID;
	}
	/**
	 * Sets the selectedNetworkID
	 * @param selectedNetworkID.
	 */
	public void setSelectedNetworkID(String selectedNetworkID) {
		this.selectedNetworkID = selectedNetworkID;
	}
	/**
	 * Returns the selectedPricingID
	 * @return String selectedPricingID.
	 */
	public String getSelectedPricingID() {
		return selectedPricingID;
	}
	/**
	 * Sets the selectedPricingID
	 * @param selectedPricingID.
	 */
	public void setSelectedPricingID(String selectedPricingID) {
		this.selectedPricingID = selectedPricingID;
	}
	/**
	 * Returns the state
	 * @return String state.
	 */
	public String getState() {
		return getContractSession().getContractKeyObject().getState();
	}
	/**
	 * Sets the state
	 * @param state.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * Returns the status
	 * @return String status.
	 */
	public String getStatus() {
		return getContractSession().getContractKeyObject().getStatus();
	}
	/**
	 * Sets the status
	 * @param status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * Returns the version
	 * @return String version.
	 */
	public int getVersion() {
		return getContractSession().getContractKeyObject().getVersion();
	}
	/**
	 * Sets the version
	 * @param version.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	
	/**
	 * Returns the selectedContractDSSysID
	 * @return String selectedContractDSSysID.
	 */
	public String getSelectedContractDSSysID() {
		return selectedContractDSSysID;
	}
	/**
	 * Sets the selectedContractDSSysID
	 * @param selectedContractDSSysID.
	 */
	public void setSelectedContractDSSysID(String selectedContractDSSysID) {
		this.selectedContractDSSysID = selectedContractDSSysID;
	}
	
	/**
	 * Returns the coverageInvalid
	 * @return boolean coverageInvalid.
	 */
	public boolean isCoverageInvalid() {
		return coverageInvalid;
	}
	/**
	 * Sets the coverageInvalid
	 * @param coverageInvalid.
	 */
	public void setCoverageInvalid(boolean coverageInvalid) {
		this.coverageInvalid = coverageInvalid;
	}
	/**
	 * Returns the networkInvalid
	 * @return boolean networkInvalid.
	 */
	public boolean isNetworkInvalid() {
		return networkInvalid;
	}
	/**
	 * Sets the networkInvalid
	 * @param networkInvalid.
	 */
	public void setNetworkInvalid(boolean networkInvalid) {
		this.networkInvalid = networkInvalid;
	}
	/**
	 * Returns the pricingInvalid
	 * @return boolean pricingInvalid.
	 */
	public boolean isPricingInvalid() {
		return pricingInvalid;
	}
	/**
	 * Sets the pricingInvalid
	 * @param pricingInvalid.
	 */
	public void setPricingInvalid(boolean pricingInvalid) {
		this.pricingInvalid = pricingInvalid;
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
	 * Returns the checkIn
	 * @return boolean checkIn.
	 */
	public boolean isCheckIn() {
		return checkIn;
	}
	/**
	 * Sets the checkIn
	 * @param checkIn.
	 */
	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}

  
    

	
	/**
	 * Returns the printValue
	 * @return String printValue.
	 */
	public String getPrintValue() {
		String requestForPrint = getRequest().getParameter("printContractPricingInfo");
        if(null != requestForPrint && !requestForPrint.equals("")){
            printValue = requestForPrint;
        }else{
            printValue = "";
        }
        return printValue;
	}
	/**
	 * Sets the printValue
	 * @param printValue.
	 */
	public void setPrintValue(String printValue) {
		this.printValue = printValue;
	}

    /**
     * Returns the hiddenLoadPricing
     * @return String hiddenLoadPricing.
     */
    public String getHiddenLoadPricing() {
    	if(null == this.pricingInformationList){
    		this.pricingInformationList = this.retrivePricingInformationList();	
    	}
        return "";
    }
    /**
     * Sets the hiddenLoadPricing
     * @param hiddenLoadPricing.
     */
    public void setHiddenLoadPricing(String hiddenLoadPricing) {
        this.hiddenLoadPricing = hiddenLoadPricing;
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
	 * @return Returns the be.
	 */
	public String getBe() {
		be = this.getSession().getAttribute("be").toString();
		return be;
	}
	/**
	 * @param be The be to set.
	 */
	public void setBe(String be) {
		this.be = be;
	}
	/**
	 * @return Returns the bg.
	 */
	public String getBg() {
		bg = this.getSession().getAttribute("bg").toString();
		return bg;
	}
	/**
	 * @param bg The bg to set.
	 */
	public void setBg(String bg) {
		
		this.bg = bg;
	}
	/**
	 * @return Returns the lob.
	 */
	public String getLob() {
		lob = this.getSession().getAttribute("lob").toString();
		return lob;
	}
	/**
	 * @param lob The lob to set.
	 */
	public void setLob(String lob) {
		this.lob = lob;
	}
	/**
	 * @return Returns the mbu.
	 */
	public String getMbu() {
		mbu = this.getSession().getAttribute("mbu").toString();
		return mbu;
	}
	/**
	 * @param mbu The mbu to set.
	 */
	public void setMbu(String mbu) {
		this.mbu = mbu;
	}
}
