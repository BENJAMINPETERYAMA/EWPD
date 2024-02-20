/*
 * ContractSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.owasp.esapi.ESAPI;

import java.util.List;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.business.contract.locateresult.ContractLocateResult;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.contract.request.ContractSearchRequest;
import com.wellpoint.wpd.common.contract.request.DeleteContractRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBasicInfoRequest;
import com.wellpoint.wpd.common.contract.response.ContractSearchResponse;
import com.wellpoint.wpd.common.contract.response.DeleteContractResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBasicInfoResponse;
import com.wellpoint.wpd.common.contract.vo.ContractLocateCriteriaVO;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceDataImpl;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.product.request.SearchProductRequest;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;
/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractSearchBackingBean extends ContractBackingBean {
    
	private String selectedDateSegDelOptionFromSearch;
	private String selectedContractIDFromSearch;
	private String selectedContractKeyFromSearch;
	private String selectedContractTypeFromSearch;
	private String selectedDateSegKeyFromSearch;
	private String selectedVerionFromSearch;
	private String selectedStatusFromSearch;
	private String selectedProductFromSearch;
	private String contractId;
	private String lob;
	private String businessEntity;
	private String groupName;
	/*START CARS*/
	private String marketBusinessUnit;
	/*END CARS*/
	private String contractType;
	private String startDate;
	private String endDate;
	private String pageId;
	private String testDateSegment;
    private List allVersionsList;
    private List locateResultList;
    private ContractSearchScreenValueObject screenValueObject; 
	private List validationMessages;
	private boolean invalidDateField;
    private boolean versionListRetrieved;
    private List previousVersionList;
    private List dateSegDelOptionList;
    private boolean print;
    private String printPageLoad;
    private String breadCrumb;
    private String breadCrumbPrint;
    private String locateBreadCrumb;
    
    //Attributes added for SSCR 21516
    private List paginatedVersionList;
    private String currentPage = "1";
    private String lastPage;
    private String nextPage;
    private String firstPage;
    private String previousPage;
    private String lastPageLabel;
    private String navigateTo;

    private static int MAX_VERSION_COUNT_FOR_VIEW_ALL_VERSION = 0;
    static {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("boundaries",Locale.getDefault());
		String versionCount = resourceBundle.getString("contractCountForViewAllVersion");
		MAX_VERSION_COUNT_FOR_VIEW_ALL_VERSION = Integer.parseInt(versionCount);
		
	}
	/**
	 * 
	 *
	 */
    public ContractSearchBackingBean(){
    	screenValueObject = getContractSearchSession().getContractSearchScreenValueObject();
    	if(screenValueObject == null){
    		screenValueObject = new ContractSearchScreenValueObject();
    		getContractSearchSession().setContractSearchScreenValueObject(screenValueObject);
    		
    	}
    	validationMessages = new ArrayList();
    }
	/**
	 * Returns the previousVersionList
	 * @return List previousVersionList.
	 */

    public List getPreviousVersionList() {
       
        if( !this.versionListRetrieved) {
            String sysId= getRequest().getParameter("contractKey");
            if(null!=sysId  && sysId.matches("^[0-9a-zA-Z_]+$")){
            	sysId = sysId;
	       }else{
	    	   sysId=null;
	       }
	        String contId = ESAPI.encoder().encodeForHTML(getRequest().getParameter("contractId"));
	        if(null!=contId  && contId.matches("^[0-9a-zA-Z_]+$")){
	        	contId = contId;
	       }else{
	    	   contId=null;
	       }
	        String version = ESAPI.encoder().encodeForHTML(getRequest().getParameter("version"));
	        if(null!=version  && version.matches("^[0-9a-zA-Z_]+$")){
	        	version = version;
	       }else{
	    	   version=null;
	       }
	        String status = ESAPI.encoder().encodeForHTML(getRequest().getParameter("status"));
	        if(null!=status  && status.matches("^[0-9a-zA-Z_]+$")){
	        	status = status;
	       }else{
	    	   status=null;
	       }
	        String page = ESAPI.encoder().encodeForHTML(getRequest().getParameter("currentPage"));
	        if(null!=page  && page.matches("^[0-9a-zA-Z_]+$")){
	        	page = page;
	       }else{
	    	   page=null;
	       }
	        if(null !=  sysId && !sysId.trim().equals("")){
	        	  getContractSession().setContractSysId(Integer.parseInt(sysId));
	        	  getRequest().setAttribute("sysId",sysId);
	        }
	        if(null !=  contId && !contId.trim().equals("")){
	        	  getContractSession().setContractId(contId);
	        	  getRequest().setAttribute("contId",contId);
	        }
	        if(null !=  version && !version.trim().equals("")){
	        	  getContractSession().setVersion(Integer.parseInt(version));
	        	  getRequest().setAttribute("version",version);
	        }
	        if(null !=  status && !status.trim().equals("")){
	        	  getContractSession().setStatus(status);
	        	  getRequest().setAttribute("status",status);
	        }
	        if(null !=  page && !page.trim().equals("")){
	        	  getRequest().setAttribute("currentPage",page);
	        }
	        if(null != getContractSession().getContractId())
	        	contId = getContractSession().getContractId();
	        if(null != contId ) {
		    	ContractSearchRequest contractSearchRequest = (ContractSearchRequest) this
				.getServiceRequest(ServiceManager.SEARCH_CONTRACT_REQUEST);
		    	contractSearchRequest.setContractId(contId);
		        contractSearchRequest.setAction(SearchProductRequest.SEARCH_ALL_VERSION);
		        ContractSearchResponse contractSearchResponse = (ContractSearchResponse) executeService(contractSearchRequest);
		        if (null != contractSearchResponse) {
		            previousVersionList =  contractSearchResponse
		                    .getSearchResultList();
		        } 
		        versionListRetrieved = true;
	        }
        }
        return previousVersionList;
    }
    /**
     * 
     * @return
     */
    public String unlockContract(){
    	 SaveContractBasicInfoRequest saveContractBasicInfoRequest = 
		 	(SaveContractBasicInfoRequest)this.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
			 SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
			 saveContractBasicInfoRequest.getContractVO().setContractSysId (Integer.parseInt(this.getSelectedContractKeyFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setContractId(this.getSelectedContractIDFromSearch());
			 saveContractBasicInfoRequest.getContractVO().setVersion(Integer.parseInt(this.getSelectedVerionFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setStatus(this.getSelectedStatusFromSearch());
			 saveContractBasicInfoRequest.setAction(SaveContractBasicInfoRequest.UNLOCK_CONTRACT);
			 saveContractBasicInfoResponse = (SaveContractBasicInfoResponse)executeService(saveContractBasicInfoRequest);
		 if(saveContractBasicInfoResponse != null){
			List list = saveContractBasicInfoResponse.getMessages();
			if(("searchVersionPage").equals(this.pageId)){
   	    	    this.versionListRetrieved = false;
   	    	    this.clearViewAllVersionSession();
   	    	    this.getPaginatedVersionList();
			}
   	    	else 
   	    		this.performLocate();
			this.addAllMessagesToRequest(list);
		}
    	return WebConstants.EMPTY_STRING;
    }
    /**
     * 
     * @return
     */
    public String performLocate(){
    	//To clear resultList and currentPage set for view all versions
    		clearViewAllVersionSession();
    		
         //checks if the fields are empty or not
         if (!validateRequiredFields()) {
             addAllMessagesToRequest(validationMessages);
             return "";
         }
         //valdates the date fields
         if(!this.print){
	         if(!isDateFieldsValidForSearch()){
	             addAllMessagesToRequest(validationMessages);
	             return "";
	         }
         }
         ContractSearchResponse contractSearchResponse = (ContractSearchResponse) this.executeService(getContractSearchRequest());
	 	 if(null != contractSearchResponse  && null != contractSearchResponse.getSearchResultList() && contractSearchResponse.getSearchResultList().size()>0){
	 		locateResultList = new ArrayList();
	 		setLocateResultList(contractSearchResponse.getSearchResultList());
	 	 }
    	 return "";	
    }
    /**
     * 
     * @return
     */
    public String getInitPopup(){
		String status = getRequest().getParameter("status");
		int index = 0;
    	dateSegDelOptionList = new ArrayList();
    	if(status.equalsIgnoreCase("BUILDING")
    	    	   || (status.equalsIgnoreCase("CHECKED_OUT"))
    	){
	    	ReferenceDataImpl referenceDataImpl0 = new ReferenceDataImpl();
	    	referenceDataImpl0.setCode("selectedDateSegment");
			referenceDataImpl0.setDescription("Delete selected date segment for current version");
			dateSegDelOptionList.add(index,referenceDataImpl0);
			index++;
    	}
    	ReferenceDataImpl referenceDataImpl1 = new ReferenceDataImpl();
    	referenceDataImpl1.setCode("allDateSegments");
		referenceDataImpl1.setDescription("Delete all date segments for the current version of the contract");
		dateSegDelOptionList.add(index,referenceDataImpl1);
    	return "";
    }
    /**
     * 
     * @param temp
     */
    public void setInitPopup(String temp){
    	
    }
    /**
     * 
     * @return
     * @throws WPDException
     */
    public String deleteAction() throws WPDException {
    	try{
    		int iContractKey = Integer.parseInt(selectedContractKeyFromSearch);
    		int iDateSegmentKey= Integer.parseInt(selectedDateSegKeyFromSearch);
    		int iVersion = Integer.parseInt(selectedVerionFromSearch);
	    	DeleteContractRequest deleteContractRequest = (DeleteContractRequest) this
	         													.getServiceRequest(ServiceManager.DELETE_CONTRACT);
	    	Contract contract = new Contract();
	    	DateSegment dateSegment = new DateSegment();
	    	List dateSegmentList = new ArrayList();
	    	dateSegment.setDateSegmentSysId(iDateSegmentKey);
	    	dateSegmentList.add(0,dateSegment);
	    	contract.setContractId(selectedContractIDFromSearch);
	    	contract.setContractSysId(iContractKey);
			contract.setDateSegmentList(dateSegmentList);
			contract.setVersion(iVersion);
			contract.setStatus(this.selectedStatusFromSearch);
	    	this.setContractToSession(contract); 
	    	if(selectedDateSegDelOptionFromSearch.equals("selectedDateSegment"))
	    	{
	        	deleteContractRequest.setAction(DeleteContractRequest.SELECTED_DATE_SEGMENT);
	    	}
	    	if(selectedDateSegDelOptionFromSearch.equals("allDateSegments"))
	    	{
	        	deleteContractRequest.setAction(DeleteContractRequest.ALL_DATE_SEGMENTS);
	    	}
	    	deleteContractRequest.setContractId(iContractKey);
	    	deleteContractRequest.setContractDateSegmentSysId(iDateSegmentKey);
	    	deleteContractRequest.setContractKeyObject(getContractKeyObject());
	    	//calls the service
	    	DeleteContractResponse deleteContractResponse=(DeleteContractResponse)executeService(deleteContractRequest);
	    		if(null != deleteContractResponse)
	    		{
	   	   	    	List list = deleteContractResponse.getMessages();
	   	   	    	if(("searchVersionPage").equals(this.pageId)){
	   	   	    	    this.setVersionListRetrieved(false);
	   	   	    		this.clearViewAllVersionSession();
	   	   	    		this.getPaginatedVersionList();
	   	   	    	}
	   	   	    	else
	   	   	    	    this.performLocate();
	   	   	    	this.addAllMessagesToRequest(list);
	    		}
    	}
    	catch(NumberFormatException nfe)
		{   
    		Logger.logError(nfe);
    	
		}
    	return "";
    } 
    
    /**
     * checks for the validation of date fields
     * @return
     */
    private boolean isDateFieldsValidForSearch(){
         	//validates the effective date
            if(null!=this.startDate && !(this.startDate.trim().equals("")) && !(StringUtil.isDate(this.startDate))){        	
	            ErrorMessage errorMessage = new ErrorMessage(
	                    WebConstants.INPUT_FORMAT_INVALID);
	            errorMessage.setParameters(new String[] {"Effective Date" });
	           	validationMessages.add( errorMessage);
	           	invalidDateField=true;
	         }
            //validates the expiry date
	        if(null!=this.endDate && !(this.endDate.trim().equals("")) && !(StringUtil.isDate(this.endDate))){        	
	            ErrorMessage errorMessage = new ErrorMessage(
	                    WebConstants.INPUT_FORMAT_INVALID);
	            errorMessage.setParameters(new String[] {"Expiry Date" });
	           	validationMessages.add( errorMessage);
	           	invalidDateField=true;
	         }
	        //checks if the effective date is greater than expiry date
            if(WPDStringUtil.isValidDate(this.startDate.trim()) && WPDStringUtil.isValidDate(this.endDate.trim()) ){
		           Date  effectDate = WPDStringUtil.getDateFromString(this.startDate.trim());
		           Date  expDate = WPDStringUtil.getDateFromString(this.endDate.trim());
		           if(null!=effectDate&&null!=expDate)
		        {
		           if(effectDate.compareTo(expDate)>0){         	
		         	  validationMessages.add(new ErrorMessage(
		                    WebConstants.EXPIRY_GREATER_EFFECTIVE_DATE));
		         	 invalidDateField=true;
		         	
		          }
		        }
	        }
            if(invalidDateField){
                //will add invalid field messages to request
                return false;
            }
            else{
                return true;
            }
    }
	/**
	 * Function for performing the validations.
	 * 
	 * @return boolean
	 */
	private boolean validateRequiredFields() {
	    if(!this.print){
		    validationMessages = new ArrayList();
		    boolean valid = false;
		    if ((null == this.contractId || "".equals(this.contractId.trim()))
		            && (null == this.startDate || "".equals(this.startDate.trim()))
		            && (null == this.endDate || "".equals(this.endDate.trim()))
		            && (null == this.contractType || ""
		                    .equals(this.contractType))
		            && (null == this.lob || "".equals(this.lob.trim()))
		            && (null == this.businessEntity || ""
		                    .equals(this.businessEntity))
		            && (null == this.groupName || "".equals(this.groupName.trim()))
					/*START CARS*/
					&& (null == this.marketBusinessUnit || "".equals(this.marketBusinessUnit)))
		    	    /*END CARS*/
		        valid = true;
		    if (valid) {
		    	validationMessages.add(new ErrorMessage(WebConstants.ATLEAST_ONE_SEARCH));
		        locateResultList = null;
		        return false;
		    }
	    }
	    return true;
	}
	/**
	 * 
	 * @return
	 */
    private ContractSearchRequest getContractSearchRequest(){
    	ContractSearchRequest contractSearchRequest = (ContractSearchRequest) this
			.getServiceRequest(ServiceManager.SEARCH_CONTRACT_REQUEST);
    	contractSearchRequest.setAction(ContractSearchRequest.SEARCH_CONTRACT);
    	ContractLocateCriteriaVO contractLocateCriteriaVO = new ContractLocateCriteriaVO();
    	if(!this.print){
    	    
    	    String contractIdFinal = this.getContractId().trim();
    	    int i = contractIdFinal.indexOf("_");
    	    if(i != -1){// it contains the _
    	        contractIdFinal = contractIdFinal.concat("`");
    	    }
	    	contractLocateCriteriaVO.setContractId(contractIdFinal);
	    	 List lobList = WPDStringUtil.getListFromTildaString(
	                this.lob, 2, 2, 2);
	        List entityList = WPDStringUtil.getListFromTildaString(
	                this.businessEntity, 2, 2, 2);
	        List groupList = WPDStringUtil.getListFromTildaString(
	                this.groupName, 2, 2, 2);
	        List typeList = WPDStringUtil.getListFromTildaString(
	                this.contractType, 2, 2, 2);
	        /*START CARS*/
	        //Market Business Unit
	        List mbuList = WPDStringUtil.getListFromTildaString(
	                this.getMarketBusinessUnit(), 2, 2, 2);
	        contractLocateCriteriaVO.setMarketBusinessUnit(mbuList);
	        /*END CARS*/
	    	contractLocateCriteriaVO.setLob(lobList);
	    	contractLocateCriteriaVO.setBusinessEntity(entityList);
	    	contractLocateCriteriaVO.setContractType(typeList);
	    	contractLocateCriteriaVO.setGroupName(groupList);
	    	
	    	contractLocateCriteriaVO.setStartDate(WPDStringUtil.getDateFromString(this.startDate.trim()));
	    	contractLocateCriteriaVO.setEndDate(WPDStringUtil.getDateFromString(this.endDate.trim()));
	    	getSession().setAttribute("contractLocateVO",contractLocateCriteriaVO);
    	}
    	if(this.print){
    	    contractLocateCriteriaVO = (ContractLocateCriteriaVO)getSession().getAttribute("contractLocateVO") ;
    	}
       	contractSearchRequest.setContractLocateCriteriaVO(contractLocateCriteriaVO);
    	return contractSearchRequest;
    }

   
	/**
	 * gets the details of the product selected from the popup
	 * @return
	 */
    public String getProductDetails(){
        HashMap benefitDefinitionMap=getContractSearchSession().getContractSearchScreenValueObject().getBenefitDefinitionsMap();
        String product=getContractSearchSession().getContractSearchScreenValueObject().getProduct();
        //checks if the benefitDefinitionMap is null or not
        if(benefitDefinitionMap!=null){
	        Set bnftKeys=benefitDefinitionMap.keySet();
	        Iterator iter=bnftKeys.iterator();
	         while(iter.hasNext()){
	             Object bnftValKey = iter.next();
	         }
	         getContractSearchSession().getContractSearchScreenValueObject().setBenefitDefinitionsMap(null);
        }
        //gets the product id and product name selected
        if(!(null == product || "".equals(product.trim()))){

	        List productIdList = WPDStringUtil.getListFromTildaString(product,2,2,1);
	        List productNameList = WPDStringUtil.getListFromTildaString(product,2,1,2);
	        String productName = (String)productNameList.get(0);
	        Integer productId= (Integer)productIdList.get(0);
	        getContractSearchSession().setProductId(productId.intValue());
	        getContractSearchSession().setProductName(productName);
        }
        else
            getContractSearchSession().setProductId(0);
        return "";
    }
    
    /**
     * 
     * @return
     */
    public String loadAdvancedSearch(){
    	return "advancedSearch";
    }
    /**
     * 
     * @return
     */
    public String loadBasicSearch(){
    	return "basicSearch";
    }
  
    /**
     * Returns the allVersionsList
     * @return List allVersionsList.
     */
    public List getAllVersionsList() {
        //Just added a test list for testing
        List test=new ArrayList();
        test.add("Contract1");
        test.add("Contract2");
        return test;
    }
    /**
     * 
     * @return
     */
    public String sendToTest(){
		 SaveContractBasicInfoRequest saveContractBasicInfoRequest = 
   		(SaveContractBasicInfoRequest)this.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
		 SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
		 saveContractBasicInfoRequest.getContractVO().setContractSysId (Integer.parseInt(this.getSelectedContractKeyFromSearch()));
		 saveContractBasicInfoRequest.getContractVO().setContractId(this.getSelectedContractIDFromSearch());
		 saveContractBasicInfoRequest.getContractVO().setVersion(Integer.parseInt(this.getSelectedVerionFromSearch()));
		 saveContractBasicInfoRequest.getContractVO().setStatus(this.getSelectedStatusFromSearch());
		 saveContractBasicInfoRequest.getContractVO().setTestDateSegment(this.testDateSegment);
		 saveContractBasicInfoRequest.setAction(SaveContractBasicInfoRequest.SEND_TO_TEST);
		 saveContractBasicInfoResponse = (SaveContractBasicInfoResponse)executeService(saveContractBasicInfoRequest);
		if(saveContractBasicInfoResponse != null){
			List list = saveContractBasicInfoResponse.getMessages();
			if(("searchVersionPage").equals(this.pageId)){
   	    	    this.versionListRetrieved = false;
   	    	    this.clearViewAllVersionSession();
   	    	    this.getPaginatedVersionList();
			}
   	    	else 
   	    		this.performLocate();
   	    		
			this.addAllMessagesToRequest(list);
		}
		return WebConstants.EMPTY_STRING;
	}
    /**
     * 
     * @return
     */
	public String testPass(){
		 SaveContractBasicInfoRequest saveContractBasicInfoRequest = 
		 	(SaveContractBasicInfoRequest)this.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
			 SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
			 saveContractBasicInfoRequest.getContractVO().setContractSysId (Integer.parseInt(this.getSelectedContractKeyFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setContractId(this.getSelectedContractIDFromSearch());
			 saveContractBasicInfoRequest.getContractVO().setVersion(Integer.parseInt(this.getSelectedVerionFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setStatus(this.getSelectedStatusFromSearch());
			 saveContractBasicInfoRequest.setAction(SaveContractBasicInfoRequest.TEST_PASS_CONTRACT);
			 saveContractBasicInfoResponse = (SaveContractBasicInfoResponse)executeService(saveContractBasicInfoRequest);
		 if(saveContractBasicInfoResponse != null){
			List list = saveContractBasicInfoResponse.getMessages();
			if(("searchVersionPage").equals(this.pageId)){
   	    	    this.versionListRetrieved = false;
   	    	    this.clearViewAllVersionSession();
   	    	    this.getPaginatedVersionList();
			}
   	    	else 
   	    		this.performLocate();
			this.addAllMessagesToRequest(list);
		}
		return WebConstants.EMPTY_STRING;
	}
	/**
	 * 
	 * @return
	 */
	public String testFail(){
		 SaveContractBasicInfoRequest saveContractBasicInfoRequest = 
		 	(SaveContractBasicInfoRequest)this.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
			 SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
			 saveContractBasicInfoRequest.getContractVO().setContractSysId (Integer.parseInt(this.getSelectedContractKeyFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setContractId(this.getSelectedContractIDFromSearch());
			 saveContractBasicInfoRequest.getContractVO().setVersion(Integer.parseInt(this.getSelectedVerionFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setStatus(this.getSelectedStatusFromSearch());
			 saveContractBasicInfoRequest.setAction(SaveContractBasicInfoRequest.TEST_FAIL_CONTRACT);
			 saveContractBasicInfoResponse = (SaveContractBasicInfoResponse)executeService(saveContractBasicInfoRequest);
		 if(saveContractBasicInfoResponse != null){
			List list = saveContractBasicInfoResponse.getMessages();
			if(("searchVersionPage").equals(this.pageId)){
   	    	    this.versionListRetrieved = false;
				this.clearViewAllVersionSession();
	    	    this.getPaginatedVersionList();
			}
   	    	else 
   	    		this.performLocate();
			this.addAllMessagesToRequest(list);
		}
		return WebConstants.EMPTY_STRING;
	}
	/**
	 * 
	 * @return
	 */
	public String scheduleToApprove(){
		 SaveContractBasicInfoRequest saveContractBasicInfoRequest = 
		 	(SaveContractBasicInfoRequest)this.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
			 SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
			 saveContractBasicInfoRequest.getContractVO().setContractSysId (Integer.parseInt(this.getSelectedContractKeyFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setContractId(this.getSelectedContractIDFromSearch());
			 saveContractBasicInfoRequest.getContractVO().setVersion(Integer.parseInt(this.getSelectedVerionFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setStatus(this.getSelectedStatusFromSearch());
			 saveContractBasicInfoRequest.setAction(SaveContractBasicInfoRequest.SCHEDULE_TO_APPROVE);
			 saveContractBasicInfoResponse = (SaveContractBasicInfoResponse)executeService(saveContractBasicInfoRequest);
		 if(saveContractBasicInfoResponse != null){
			List list = saveContractBasicInfoResponse.getMessages();
			if(("searchVersionPage").equals(this.pageId)){
   	    	    this.versionListRetrieved = false;
   	    	    this.clearViewAllVersionSession();
   	    	    this.getPaginatedVersionList();
			}
   	    	else 
   	    		this.performLocate();
			this.addAllMessagesToRequest(list);
		}
		return WebConstants.EMPTY_STRING;
	
	}
	/***
	 * 
	 * @return
	 */
	public String approve(){
		 SaveContractBasicInfoRequest saveContractBasicInfoRequest = 
		 	(SaveContractBasicInfoRequest)this.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
			 SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
			 saveContractBasicInfoRequest.getContractVO().setContractSysId (Integer.parseInt(this.getSelectedContractKeyFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setContractId(this.getSelectedContractIDFromSearch());
			 saveContractBasicInfoRequest.getContractVO().setVersion(Integer.parseInt(this.getSelectedVerionFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setStatus(this.getSelectedStatusFromSearch());
			 saveContractBasicInfoRequest.setAction(SaveContractBasicInfoRequest.APPROVE_CONTRACT);
			 saveContractBasicInfoResponse = (SaveContractBasicInfoResponse)executeService(saveContractBasicInfoRequest);
		 if(saveContractBasicInfoResponse != null){
			List list = saveContractBasicInfoResponse.getMessages();
			if(("searchVersionPage").equals(this.pageId)){
   	    	    this.versionListRetrieved = false;
   	    	    this.clearViewAllVersionSession();
   	    	    this.getPaginatedVersionList();
			}
   	    	else 
   	    		this.performLocate();
			this.addAllMessagesToRequest(list);
		}
		return WebConstants.EMPTY_STRING;
	}
	/**
	 * 
	 * @return
	 */
	public String reject(){
		 SaveContractBasicInfoRequest saveContractBasicInfoRequest = 
		 	(SaveContractBasicInfoRequest)this.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
			 SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
			 saveContractBasicInfoRequest.getContractVO().setContractSysId (Integer.parseInt(this.getSelectedContractKeyFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setContractId(this.getSelectedContractIDFromSearch());
			 saveContractBasicInfoRequest.getContractVO().setVersion(Integer.parseInt(this.getSelectedVerionFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setStatus(this.getSelectedStatusFromSearch());
			 saveContractBasicInfoRequest.setAction(SaveContractBasicInfoRequest.REJECT_CONTRACT);
			 saveContractBasicInfoResponse = (SaveContractBasicInfoResponse)executeService(saveContractBasicInfoRequest);
		 if(saveContractBasicInfoResponse != null){
			List list = saveContractBasicInfoResponse.getMessages();
			if(("searchVersionPage").equals(this.pageId)){
   	    	    this.versionListRetrieved = false;
   	    	    this.clearViewAllVersionSession();
   	    	    this.getPaginatedVersionList();
			}
   	    	else 
   	    		this.performLocate();
			this.addAllMessagesToRequest(list);
		}
		return WebConstants.EMPTY_STRING;
	}
	/**
	 * 
	 * @return
	 */
	public String publish(){
		 SaveContractBasicInfoRequest saveContractBasicInfoRequest = 
		 	(SaveContractBasicInfoRequest)this.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
			 SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
			 saveContractBasicInfoRequest.getContractVO().setContractSysId (Integer.parseInt(this.getSelectedContractKeyFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setContractId(this.getSelectedContractIDFromSearch());
			 saveContractBasicInfoRequest.getContractVO().setVersion(Integer.parseInt(this.getSelectedVerionFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setStatus(this.getSelectedStatusFromSearch());
			 saveContractBasicInfoRequest.setAction(SaveContractBasicInfoRequest.PUBLISH_CONTRACT);
			 saveContractBasicInfoResponse = (SaveContractBasicInfoResponse)executeService(saveContractBasicInfoRequest);
		 if(saveContractBasicInfoResponse != null){
			List list = saveContractBasicInfoResponse.getMessages();
			if(("searchVersionPage").equals(this.pageId)){
   	    	    this.versionListRetrieved = false;
   	    	    this.clearViewAllVersionSession();
   	    	    this.getPaginatedVersionList();
			}
   	    	else 
   	    		this.performLocate();
			this.addAllMessagesToRequest(list);
		}
		return WebConstants.EMPTY_STRING;
	}
	/**
	 * 
	 * @return
	 */
	public String scheduleToProduction(){
		
		 SaveContractBasicInfoRequest saveContractBasicInfoRequest = 
		 	(SaveContractBasicInfoRequest)this.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
			 SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
			 saveContractBasicInfoRequest.getContractVO().setContractSysId (Integer.parseInt(this.getSelectedContractKeyFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setContractId(this.getSelectedContractIDFromSearch());
			 saveContractBasicInfoRequest.getContractVO().setVersion(Integer.parseInt(this.getSelectedVerionFromSearch()));
			 saveContractBasicInfoRequest.getContractVO().setStatus(this.getSelectedStatusFromSearch());
			 saveContractBasicInfoRequest.setAction(SaveContractBasicInfoRequest.SCHEDULE_FOR_PRODUCTION);
			 saveContractBasicInfoResponse = (SaveContractBasicInfoResponse)executeService(saveContractBasicInfoRequest);
			 
		 if(saveContractBasicInfoResponse != null){
			List list = saveContractBasicInfoResponse.getMessages();
			if(("searchVersionPage").equals(this.pageId)){
   	    	    this.versionListRetrieved = false;
   	    	    this.clearViewAllVersionSession();
   	    	    this.getPaginatedVersionList();
			}
   	    	else 
   	    		this.performLocate();
			this.addAllMessagesToRequest(list);
		}
		return WebConstants.EMPTY_STRING;
	}
   
    /**
     * Sets the allVersionsList
     * @param allVersionsList.
     */
    public void setAllVersionsList(List allVersionsList) {
        this.allVersionsList = allVersionsList;
    }
	/**
	 * @return Returns the locateResultList.
	 */
	public List getLocateResultList() {
		
		return locateResultList;
	}
	/**
	 * @param locateResultList The locateResultList to set.
	 */
	public void setLocateResultList(List locateResultList) {
		this.locateResultList = locateResultList;
	}
	/**
	 * @return Returns the screenValueObject.
	 */
	public ContractSearchScreenValueObject getScreenValueObject() {
		return screenValueObject;
	}
	/**
	 * @param screenValueObject The screenValueObject to set.
	 */
	public void setScreenValueObject(ContractSearchScreenValueObject screenValueObject) {
		this.screenValueObject = screenValueObject;
	}
	/**
	 * Returns the validationMessages
	 * @return List validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}
	/**
	 * Sets the validationMessages
	 * @param validationMessages.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	/**
	 * @return Returns the endDate.
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * @return Returns the invalidDateField.
	 */
	public boolean isInvalidDateField() {
		return invalidDateField;
	}
	/**
	 * @param invalidDateField The invalidDateField to set.
	 */
	public void setInvalidDateField(boolean invalidDateField) {
		this.invalidDateField = invalidDateField;
	}
	
	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return Returns the businessEntity.
	 */
	public String getBusinessEntity() {
		return businessEntity;
	}
	/**
	 * @param businessEntity The businessEntity to set.
	 */
	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}
	/**
	 * @return Returns the contractType.
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * @param contractType The contractType to set.
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	/**
	 * @return Returns the groupName.
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName The groupName to set.
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return Returns the lob.
	 */
	public String getLob() {
		return lob;
	}
	/**
	 * @param lob The lob to set.
	 */
	public void setLob(String lob) {
		this.lob = lob;
	}
	/**
	 * @return Returns the versionListRetrieved.
	 */
	public boolean isVersionListRetrieved() {
		return versionListRetrieved;
	}
	/**
	 * @param versionListRetrieved The versionListRetrieved to set.
	 */
	public void setVersionListRetrieved(boolean versionListRetrieved) {
		this.versionListRetrieved = versionListRetrieved;
	}
	/**
	 * @param previousVersionList The previousVersionList to set.
	 */
	public void setPreviousVersionList(List previousVersionList) {
		this.previousVersionList = previousVersionList;
	}

	/**
	 * Returns the selectedContractIDFromSearch
	 * @return String selectedContractIDFromSearch.
	 */
	public String getSelectedContractIDFromSearch() {
		return selectedContractIDFromSearch;
	}
	/**
	 * Sets the selectedContractIDFromSearch
	 * @param selectedContractIDFromSearch.
	 */
	public void setSelectedContractIDFromSearch(
			String selectedContractIDFromSearch) {
		this.selectedContractIDFromSearch = selectedContractIDFromSearch;
	}
	/**
	 * Returns the selectedContractKeyFromSearch
	 * @return String selectedContractKeyFromSearch.
	 */
	public String getSelectedContractKeyFromSearch() {
		return selectedContractKeyFromSearch;
	}
	/**
	 * Sets the selectedContractKeyFromSearch
	 * @param selectedContractKeyFromSearch.
	 */
	public void setSelectedContractKeyFromSearch(
			String selectedContractKeyFromSearch) {
		this.selectedContractKeyFromSearch = selectedContractKeyFromSearch;
	}
	/**
	 * Returns the selectedContractTypeFromSearch
	 * @return String selectedContractTypeFromSearch.
	 */
	public String getSelectedContractTypeFromSearch() {
		return selectedContractTypeFromSearch;
	}
	/**
	 * Sets the selectedContractTypeFromSearch
	 * @param selectedContractTypeFromSearch.
	 */
	public void setSelectedContractTypeFromSearch(
			String selectedContractTypeFromSearch) {
		this.selectedContractTypeFromSearch = selectedContractTypeFromSearch;
	}
	/**
	 * Returns the selectedDateSegKeyFromSearch
	 * @return String selectedDateSegKeyFromSearch.
	 */
	public String getSelectedDateSegKeyFromSearch() {
		return selectedDateSegKeyFromSearch;
	}
	/**
	 * Sets the selectedDateSegKeyFromSearch
	 * @param selectedDateSegKeyFromSearch.
	 */
	public void setSelectedDateSegKeyFromSearch(
			String selectedDateSegKeyFromSearch) {
		this.selectedDateSegKeyFromSearch = selectedDateSegKeyFromSearch;
	}
	/**
	 * Returns the selectedVerionFromSearch
	 * @return String selectedVerionFromSearch.
	 */
	public String getSelectedVerionFromSearch() {
		return selectedVerionFromSearch;
	}
	/**
	 * Sets the selectedVerionFromSearch
	 * @param selectedVerionFromSearch.
	 */
	public void setSelectedVerionFromSearch(String selectedVerionFromSearch) {
		this.selectedVerionFromSearch = selectedVerionFromSearch;
	}
	/**
	 * Returns the selectedStatusFromSearch
	 * @return String selectedStatusFromSearch.
	 */
	public String getSelectedStatusFromSearch() {
		return selectedStatusFromSearch;
	}
	/**
	 * Sets the selectedStatusFromSearch
	 * @param selectedStatusFromSearch.
	 */
	public void setSelectedStatusFromSearch(String selectedStatusFromSearch) {
		this.selectedStatusFromSearch = selectedStatusFromSearch;
	}

	/**
	 * Returns the pageId
	 * @return String pageId.
	 */
	public String getPageId() {
		return pageId;
	}
	/**
	 * Sets the pageId
	 * @param pageId.
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	/**
	 * Returns the selectedDateSegDelOptionFromSearch
	 * @return String selectedDateSegDelOptionFromSearch.
	 */
	public String getSelectedDateSegDelOptionFromSearch() {
		return selectedDateSegDelOptionFromSearch;
	}
	/**
	 * Sets the selectedDateSegDelOptionFromSearch
	 * @param selectedDateSegDelOptionFromSearch.
	 */
	public void setSelectedDateSegDelOptionFromSearch(
			String selectedDateSegDelOptionFromSearch) {
		this.selectedDateSegDelOptionFromSearch = selectedDateSegDelOptionFromSearch;
	}
	/**
	 * Returns the dateSegDelOptionList
	 * @return List dateSegDelOptionList.
	 */
	public List getDateSegDelOptionList() {
		return dateSegDelOptionList;
	}
	/**
	 * Sets the dateSegDelOptionList
	 * @param dateSegDelOptionList.
	 */
	public void setDateSegDelOptionList(List dateSegDelOptionList) {
		this.dateSegDelOptionList = dateSegDelOptionList;
	}

	/**
	 * Returns the testDateSegment
	 * @return String testDateSegment.
	 */
	public String getTestDateSegment() {
		return testDateSegment;
	}
	/**
	 * Sets the testDateSegment
	 * @param testDateSegment.
	 */
	public void setTestDateSegment(String testDateSegment) {
		this.testDateSegment = testDateSegment;
	}
    /**
     * @return selectedProductFromSearch
     * 
     * Returns the selectedProductFromSearch.
     */
    public String getSelectedProductFromSearch() {
        return selectedProductFromSearch;
    }
    /**
     * @param selectedProductFromSearch
     * 
     * Sets the selectedProductFromSearch.
     */
    public void setSelectedProductFromSearch(String selectedProductFromSearch) {
        this.selectedProductFromSearch = selectedProductFromSearch;
    }
    /**
     * Returns the printPageLoad
     * @return String printPageLoad.
     */
    public String getPrintPageLoad() {
        this.print = true;
        performLocate();
        return "";
    }
    /**
     * Sets the printPageLoad
     * @param printPageLoad.
     */
    public void setPrintPageLoad(String printPageLoad) {
        this.printPageLoad = printPageLoad;
    }
    /**
     * Returns the print
     * @return boolean print.
     */
    public boolean isPrint() {
        return print;
    }
    /**
     * Sets the print
     * @param print.
     */
    public void setPrint(boolean print) {
        this.print = print;
    }
    /**
     * Returns the breadCrumb
     * @return String breadCrumb.
     */
    public String getBreadCrumb() {
    	String contractId = (String) ESAPI.encoder().encodeForHTML(getRequest().getParameter("contractId"));
    	if(null!=contractId  && contractId.matches("^[0-9a-zA-Z_]+$")){
    		contractId = contractId;
    	}else{
    		contractId=null;
    	}
    	if(contractId == null){
    	   contractId = this.getContractSession().getContractId();
    	}
    	
        breadCrumb = "Locate >> Contract ("+contractId+")>> View All Versions";

        this.setBreadCrumbText(breadCrumb);
        return breadCrumb;
    }
    /**
     * Sets the breadCrumb
     * @param breadCrumb.
     */
    public void setBreadCrumb(String breadCrumb) {
        this.breadCrumb = breadCrumb;
    }
    /**
     * Returns the breadCrumbPrint
     * @return String breadCrumbPrint.
     */
    public String getBreadCrumbPrint() {

        String contractId = this.getContractSession().getContractId();
        breadCrumbPrint = "Locate >> Contract ("+contractId+")>> View All Versions >> Print";
        
        return breadCrumbPrint;
    }
    /**
     * Sets the breadCrumbPrint
     * @param breadCrumbPrint.
     */
    public void setBreadCrumbPrint(String breadCrumbPrint) {
        this.breadCrumbPrint = breadCrumbPrint;
    }
    /**
     * @return locateBreadCrumb
     * 
     * Returns the locateBreadCrumb.
     */
    public String getLocateBreadCrumb() {
        locateBreadCrumb = "Contract Development >> Contract Maintain >> Locate Criteria >> Print";
        return locateBreadCrumb;
    }
    /**
     * @param locateBreadCrumb
     * 
     * Sets the locateBreadCrumb.
     */
    public void setLocateBreadCrumb(String locateBreadCrumb) {
        this.locateBreadCrumb = locateBreadCrumb;
    }	
	/*START CARS*/
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
	/*END CARS*/

	// Added to implement Pagination for 'View All Versions': SSCR 21516
	// The method will be used to set the Next page (currentPage+1)
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	// The method will be used to set the First page (always 1)
	public void setFirstPage(String firstPage) {
		this.firstPage = "1";
	}

	// The method will be used to set the previous page (currentPage-1)
	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}

	// Last page label is set in session as it will be same for single session
	public void setLastPageLabel(String lastPageLabel) {
		if (null != getSession().getAttribute("lastPageLabel") && !getSession().getAttribute("lastPageLabel").toString().equals("")) {
			this.lastPageLabel = String.valueOf(getSession().getAttribute(
					"lastPageLabel"));
		}else{
			this.lastPageLabel = lastPageLabel;
		}
	}

	// Current page set into session based on result list of Versions
	public String getCurrentPage() {
		if (null == getSession().getAttribute("resultList")
				|| null == (ArrayList<ContractLocateResult>) getSession()
						.getAttribute("resultList")
				|| (null != (ArrayList<ContractLocateResult>) getSession()
						.getAttribute("resultList") && ((ArrayList<ContractLocateResult>) getSession()
						.getAttribute("resultList")).isEmpty())) {
			getPaginatedVersionList();

		}
		return String
				.valueOf(null != getSession().getAttribute("currentPage") ? getSession()
						.getAttribute("currentPage")
						: "");
	}

	// Current page set into session based on result list of Versions
	public void setCurrentPage(String currentPage) {
		if (null != currentPage && !currentPage.equalsIgnoreCase("null")) {
			getSession().setAttribute("currentPage", currentPage);
		} else {
			if (null == getSession().getAttribute("resultList")
					|| null == (ArrayList<ContractLocateResult>) getSession()
							.getAttribute("resultList")
					|| (null != (ArrayList<ContractLocateResult>) getSession()
							.getAttribute("resultList") && ((ArrayList<ContractLocateResult>) getSession()
							.getAttribute("resultList")).isEmpty())) {
				getPaginatedVersionList();
			}
		}
	}

	public String getNextPage() {
		if (null != getSession().getAttribute("navigateToPage")
				&& !getSession().getAttribute("navigateToPage").equals("")) {
			int pNo = Integer.parseInt(String.valueOf(getSession()
					.getAttribute("navigateToPage")));
			if (pNo < retrieveNumberOfPages()) {
				this.setCurrentPage(String.valueOf(pNo + 1));
				getSession().setAttribute("navigateToPage",
						String.valueOf(pNo + 1));
				this.setNavigateTo(String.valueOf(pNo + 1));
				
			}
		}
		return "";
	}

	public String getLastPageLabel() {
		String lastPgVal = String
				.valueOf(getSession().getAttribute("lastPageLabel"));
		if (null == lastPgVal || lastPgVal == "") {
			lastPgVal = String.valueOf(retrieveNumberOfPages());
		}
		if (null != lastPgVal && !lastPgVal.equalsIgnoreCase("")
				&& !lastPgVal.equalsIgnoreCase("0")) {
			if(null == getSession().getAttribute("lastPageLabel") || getSession().getAttribute("lastPageLabel").toString().equals("")){
				getSession().setAttribute("lastPageLabel", lastPgVal);
				this.lastPageLabel = lastPgVal;
				return this.lastPageLabel;
			}else{
				return getSession().getAttribute("lastPageLabel").toString();
			}
			
		} else {
			return "";
		}
	}

	public String getPreviousPage() {
		if (null != getSession().getAttribute("navigateToPage") 
				&& !getSession().getAttribute("navigateToPage").equals("")
				&& null != getSession().getAttribute("resultList")) {
			int pNo = Integer.parseInt(String.valueOf(getSession()
					.getAttribute("navigateToPage")));
			if (pNo > 1) {
				this.setCurrentPage(String.valueOf(pNo - 1));
				getSession().setAttribute("navigateToPage",
						String.valueOf(pNo - 1));
				this.setNavigateTo(String.valueOf(pNo - 1));
			}
		}
		return "";
	}

	public String getFirstPage() {
		getSession().setAttribute("currentPage", "1");
		getSession().setAttribute("navigateToPage", "1");
		this.setCurrentPage("1");
		this.setNavigateTo("1");
		return "1";

	}

	public int retrieveNumberOfPages() {
		int maxVersion = 0;
		int minVersion = 0;
		int numberOfPages = 0;
		if (null != getSession().getAttribute("lastPageLabel") && !getSession().getAttribute("lastPageLabel").toString().equals("")) {
			return Integer.valueOf(getSession().getAttribute("lastPageLabel")
					.toString());
		} else {
			List<ContractLocateResult> resultList = (ArrayList<ContractLocateResult>) getSession()
					.getAttribute("resultList");
			if (null == resultList) {
				resultList = getPaginatedVersionList();
			}
			maxVersion = (null != resultList.get(0) ? resultList.get(0)
					.getVersion() : 0);
			int sizeOfList = resultList.size();
			minVersion = (null != resultList.get(sizeOfList - 1) ? resultList
					.get(sizeOfList - 1).getVersion() : 0);
			numberOfPages = ((maxVersion - minVersion) / MAX_VERSION_COUNT_FOR_VIEW_ALL_VERSION) + 1;
			getSession().setAttribute("lastPage", numberOfPages);
			setLastPage(String.valueOf(numberOfPages));
			if(null == getSession().getAttribute("lastPageLabel") || getSession().getAttribute("lastPageLabel").toString().equals("")){
				getSession().setAttribute("lastPageLabel", numberOfPages);
				setLastPageLabel(String.valueOf(numberOfPages));
			}
			return numberOfPages;
		}

	}

	public String getLastPage() {
		if (null == getSession().getAttribute("resultList")
				|| null == (ArrayList<ContractLocateResult>) getSession()
						.getAttribute("resultList")
				|| (null != (ArrayList<ContractLocateResult>) getSession()
						.getAttribute("resultList") && ((ArrayList<ContractLocateResult>) getSession()
						.getAttribute("resultList")).isEmpty())) {
			getPaginatedVersionList();

		}
		List<ContractLocateResult> resultList = (ArrayList<ContractLocateResult>) getSession()
				.getAttribute("resultList");
		int maxVersion = 0;
		int minVersion = 0;
		if (null != resultList && !resultList.isEmpty()) {
			maxVersion = (null != resultList.get(0) ? resultList.get(0)
					.getVersion() : 0);
			int sizeOfList = resultList.size();
			minVersion = (null != resultList.get(sizeOfList - 1) ? resultList
					.get(sizeOfList - 1).getVersion() : 0);

		}
		int numberOfPages = ((maxVersion - minVersion) / MAX_VERSION_COUNT_FOR_VIEW_ALL_VERSION) + 1;
		getSession().setAttribute("lastPage", numberOfPages);
		setLastPage(String.valueOf(numberOfPages));
		getSession().setAttribute("currentpage", String.valueOf(numberOfPages));
		getSession().setAttribute("navigateToPage",
				String.valueOf(numberOfPages));
		this.setNavigateTo(String.valueOf(numberOfPages));
		this.setCurrentPage(String.valueOf(numberOfPages));
		return String.valueOf(numberOfPages);
	}

	public void setLastPage(String lastPage) {
		getSession().setAttribute(this.lastPage, lastPage);
	}

	public List getPaginatedVersionList() {

		int lowRange = 0;
		int maxRange = 0;
		int pageNum = 0;
		int maxVersion = 0;
		setPaginatedVersionList(new ArrayList<ContractLocateResult>());
		if (null == getSession().getAttribute("resultList") && (null == getSession().getAttribute("lastPageLabel")||
				getSession().getAttribute("lastPageLabel").toString().equals(""))) {
			this.previousVersionList = getPreviousVersionList();
			getSession().setAttribute("resultList", this.previousVersionList);
			getSession().setAttribute("currentPage", "1");
			getSession().setAttribute("navigateToPage", "1");
			this.setCurrentPage("1");
			//System.out.println("FROM Paginate List");
			Logger.logInfo("FROM Paginate List");
			this.setNavigateTo("1");
		}
		pageNum = Integer.valueOf(getSession().getAttribute("navigateToPage")
				.toString());
		List<ContractLocateResult> contractLocateResultList = (ArrayList<ContractLocateResult>) getSession()
				.getAttribute("resultList");
		if (null != contractLocateResultList
				&& !contractLocateResultList.isEmpty()) {
			maxVersion = (null != contractLocateResultList.get(0) ? contractLocateResultList
					.get(0).getVersion()
					: 0);
		}
		// To compute the versions which need to be displayed
		maxRange = (maxVersion - (MAX_VERSION_COUNT_FOR_VIEW_ALL_VERSION * (pageNum - 1)));
		lowRange = ((maxVersion - (MAX_VERSION_COUNT_FOR_VIEW_ALL_VERSION * pageNum)) + 1);

		if (null != contractLocateResultList
				&& !contractLocateResultList.isEmpty()) {
			for (ContractLocateResult contractLocateResultObject : contractLocateResultList) {
				int version = contractLocateResultObject.getVersion();
				if (version <= maxRange && version >= lowRange) {
					this.paginatedVersionList.add(contractLocateResultObject);
				}
			}
		}
		this.setCurrentPage(String.valueOf(pageNum));
		return paginatedVersionList;

	}

	public void setPaginatedVersionList(List paginatedVersionList) {
		this.paginatedVersionList = paginatedVersionList;
	}

	public void clearViewAllVersionSession() {
		getSession().setAttribute("currentPage", "");
		getSession().setAttribute("resultList", null);
		getSession().setAttribute("lastPage", "");
		getSession().setAttribute("lastPageLabel", "");
		getSession().setAttribute("navigateToPage", "");

	}

	public String getNavigateTo() {
		return this.navigateTo;
	}

	public void setNavigateTo(String navigateTo) {
			if (navigateTo != null && navigateTo.trim() != null
					&& !navigateTo.trim().equals("")) {
				if(StringUtil.isInteger(navigateTo.trim())){
					int goToPageNumber = Integer.parseInt(navigateTo.trim());
					String lastPgLbl = this.getLastPageLabel();
					int lastPgae = (null != lastPgLbl && !lastPgLbl.equals("") ? Integer
							.parseInt(lastPgLbl)
							: 0);
					if (goToPageNumber >= 1 && goToPageNumber <= lastPgae) {
						String stringValOfPage = String.valueOf(goToPageNumber);
						getSession().setAttribute("currentPage", stringValOfPage);
						getSession().setAttribute("navigateToPage", stringValOfPage);
						this.navigateTo = stringValOfPage;
						this.setCurrentPage(stringValOfPage);
					} else if (goToPageNumber > lastPgae) {
						String stringValOfPage = String.valueOf(lastPgae);
						getSession().setAttribute("currentPage", stringValOfPage);
						getSession().setAttribute("navigateToPage", stringValOfPage);
						this.navigateTo = stringValOfPage;
						this.setCurrentPage(stringValOfPage);
					} else if (goToPageNumber < 1) {
						getSession().setAttribute("currentPage", "1");
						getSession().setAttribute("navigateToPage", "1");
						this.navigateTo = "1";
						this.setCurrentPage("1");
					}
			}else{
				this.setNavigateTo("1");
			}
		}
	}
}