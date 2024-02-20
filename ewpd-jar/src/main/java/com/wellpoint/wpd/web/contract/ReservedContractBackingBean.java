/*
 * ReservedContractBackingBean.java
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.contract.bo.ReserveContractId;
import com.wellpoint.wpd.common.contract.request.RetrieveReservedContractRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveSystemPoolIdRequest;
import com.wellpoint.wpd.common.contract.request.SaveReservedContractRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveReservedContractResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveSystemPoolIdResponse;
import com.wellpoint.wpd.common.contract.response.SaveReservedContractResponse;
import com.wellpoint.wpd.common.contract.vo.ReserveContractVO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.contractidpool.vo.ContractIDReservePoolRecord;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ReservedContractBackingBean extends ContractBackingBean {

	private ArrayList validationMessages = new ArrayList();
	private ArrayList messagePopup = new ArrayList();
    private boolean requiredId = false;
    private boolean disableFlag = false;

    private boolean requiredNextId = false;

    private boolean  disableFlagOne = false;
	
	private String  lineOfBusinessDiv;
	    
	private String businessEntityDiv;
	    
	private String businessGroupDiv;
	
    private String startDate;
    
    private String endDate;
    
    
    private boolean lobInvalid = false;
    
    private boolean entityInvalid = false;
    
    private boolean groupInvalid = false;
    
    private boolean commentsInvalid=false;
    
    private boolean startDateInvalid = false;
    
    private boolean endDateInvalid = false;
    
    private boolean startContractIdInvalid = false;
    
    private boolean endContractIdInvalid = false;
    
    private int contractKeyForEdit;
    private String contractIdForEdit;
    
    private String comments;
    
    private String option = WebConstants.MSG_WARN_ID;
    
    private String fromContractId;
    
    private String endContractId;
    
    private String startContractId;
    
    private String noOfIds;
    
    private String nextContractID;
    
    private String idGenerated;
    
    private String releaseExpired;
	private boolean fromIdInvalid = false;
	private boolean endIdInvalid = false;
	private boolean startIdInvalid = false;
	private boolean noOfIds_1Invalid = false;
	private boolean noOfIds_2Invalid = false;
	private boolean nextAvailableInvalid = false;
	private String noOfIds_next;
	private String noOfIds_start;
	private boolean marketInvalid = false;
	private String marketSegment;
	private List marketSegmentList;
	
	//private boolean allIdsAvailable = true;
	
	private List systemIdList;
	
	private List saveIdList;
	
	private String init;
	
	private boolean continuousChecked;
    private boolean continuousChecked1;
    private boolean continuousChecked2;
    private boolean renderConfirmButton;
    private String allIdsAvailable = "";
    
    private List newSystemList = new ArrayList();
    
    private String validation = "";
    
    private String commentsForEdit;
    private String marketSegmentDiv;
    private String reservepoolStatus;
    
    private String contractId;
    
    private String endId;
    
    private boolean continuousCheckedHidden;
    private boolean continuousChecked1Hidden;
    private boolean continuousChecked2Hidden;
    
    
    /**
     * Default Constructor.
     */
    public ReservedContractBackingBean() {
        super();
           
      
    }
    
    public String updateReserveInfo() {
		this.endDateInvalid = false;
		boolean validationPass = true;
		if (this.endDate == null || "".equals(this.endDate)) {
			endDateInvalid = true;
			this.validationMessages.add(new ErrorMessage(
					WebConstants.MANDATORY_FIELDS_REQUIRED));
			addAllMessagesToRequest(validationMessages);
			validationPass = false;
		} else if (!(WPDStringUtil.isValidDate(this.endDate))) {
			ErrorMessage errorMessage = new ErrorMessage(
					WebConstants.INPUT_FORMAT_INVALID);
			errorMessage.setParameters(new String[] { "Expiry Date" });
			validationMessages.add(errorMessage);
			endDateInvalid = true;
			addAllMessagesToRequest(validationMessages);
			validationPass = false;
		}
		if (!validationPass)
			return "";
		
		boolean boolUpdate = true;
		boolean dateInvalid = validateDates(boolUpdate);
		if (dateInvalid)
			return "";
		/*
* boolean requiredField = false; boolean dateInvalid = false; if
* (this.endDate == null || "".equals(this.endDate)) { endDateInvalid =
* true; this.validationMessages.add(new ErrorMessage(
* WebConstants.MANDATORY_FIELDS_REQUIRED));
* addAllMessagesToRequest(validationMessages); return ""; } else
* if(!(WPDStringUtil.isValidDate(this.endDate))) { ErrorMessage
* errorMessage = new ErrorMessage( WebConstants.INPUT_FORMAT_INVALID);
* errorMessage.setParameters(new String[] { "Expiry Date" });
* validationMessages.add(errorMessage); endDateInvalid = true;
* addAllMessagesToRequest(validationMessages); return ""; }
* 
* Date dateStart = WPDStringUtil.getDateFromString(this.startDate); int
* year = dateStart.getYear(); int month = dateStart.getMonth(); int day
* =dateStart.getDate();
* 
* if(month == 1 && day == 29){ day = 28; }
* 
* int yearNew = year +3; Date dateNew = new Date(yearNew, month , day);
* Date endDt= WPDStringUtil.getDateFromString(this.endDate);
* 
* if(endDt.compareTo(dateNew)>0){ //
* if(WPDStringUtil.getDateFromString(this.endDate).compareTo(WPDStringUtil.getDateFromString(this.startDate)) <=
* 0){ endDateInvalid = true; ErrorMessage errorMessage = new
* ErrorMessage( WebConstants.RESERVED_DATES_CONDITION);
* validationMessages.add(errorMessage);
* addAllMessagesToRequest(validationMessages); return ""; }
*/
		SaveReservedContractRequest saveReservedContractRequest;
		saveReservedContractRequest = (SaveReservedContractRequest) this
		.getServiceRequest(ServiceManager.SAVE__RESERVED_CONTRACT);
		saveReservedContractRequest
		.setAction(SaveReservedContractRequest.UPDATE_RESERVED_CONTRACT);
		
		ReserveContractVO reserveVO = new ReserveContractVO();
		reserveVO.setEndDate(this.endDate);
		reserveVO.setContractId(this.contractIdForEdit);
		reserveVO.setReservePoolStatus(this.reservepoolStatus);
		
		saveReservedContractRequest.setReservedContractVO(reserveVO);
		
		SaveReservedContractResponse searchResponse = (SaveReservedContractResponse) executeService(saveReservedContractRequest);
		
		if (null != searchResponse) {
			List contractList = searchResponse.getContractList();
			ContractIDReservePoolRecord record = (ContractIDReservePoolRecord) contractList
			.get(0);
			this.setMarketSegment(record.getMarketSegment());
			this.setComments(record.getComment());
			this.setStartDate(WPDStringUtil.getStringDate(record
					.getEffectiveDate()));
			this
			.setEndDate(WPDStringUtil.getStringDate(record
					.getExpiryDate()));
			
		}
		
		return "";
	}
    public String editAction() {
		
		RetrieveSystemPoolIdRequest retrieveSystemPoolIdRequest;
		retrieveSystemPoolIdRequest = (RetrieveSystemPoolIdRequest) this
		.getServiceRequest(ServiceManager.RETRIEVE_SYSTEMPOOL_CONTRACT);
		
		retrieveSystemPoolIdRequest.setStartContractId(this.contractIdForEdit);
		retrieveSystemPoolIdRequest.setNoOfIdsToGenerate_Start(1);
		retrieveSystemPoolIdRequest
		.setAction(RetrieveSystemPoolIdRequest.OPTION_TWO);
		RetrieveSystemPoolIdResponse searchResponse = (RetrieveSystemPoolIdResponse) executeService(retrieveSystemPoolIdRequest);
		
		if (null != searchResponse) {
			List contractList = searchResponse.getContractIdList();
			ContractIDReservePoolRecord contract = (ContractIDReservePoolRecord) contractList
			.get(0);
			this.contractIdForEdit = contract.getContractId();
			
			this.startDate = WPDStringUtil.getStringDate(contract
					.getEffectiveDate());
			this.endDate = WPDStringUtil
			.getStringDate(contract.getExpiryDate());
			//this.marketSegment = contract.getMarketSegment();
			this.commentsForEdit = contract.getComment();
			this.reservepoolStatus = contract.getReservePoolStatus();
			
			List domainList = contract.getBusinessDomain();
			List listLOB = BusinessUtil.getLobList(domainList);
			List listBE = BusinessUtil.getbusinessEntityList(domainList);
			List listBG = BusinessUtil.getBusinessGroupList(domainList);
			List listBU = BusinessUtil.getMarketBusinessUnitList(domainList);
			
			Set lobSet = new HashSet(listLOB);
			Set beSet = new HashSet(listBE);
			Set bgSet = new HashSet(listBG);
			Set buSet = new HashSet(listBU);
			
			List lob = new ArrayList(lobSet);
			List be = new ArrayList(beSet);
			List bg = new ArrayList(bgSet);
			List bu = new ArrayList(buSet);
			
			if (domainList != null) {
				this.lineOfBusinessDiv = WPDStringUtil
				.getTildaStringFromList(lob);
				this.businessEntityDiv = WPDStringUtil
				.getTildaStringFromList(be);
				this.businessGroupDiv = WPDStringUtil
				.getTildaStringFromList(bg);
				this.marketSegment = WPDStringUtil.getTildaStringFromList(bu);				
			}
			
			return "editReserveContract";
		}
		
		return "";
	}
    
    private void setValuesToBackingBean(ReserveContractId contract, DomainDetail domainDetail){
        
  

        //this.baseContractIdDiv = saveContractBasicInfoResponse.getContract().getb
      /*  this.createdUser = contract.getCreatedUser();
        this.creationDate = contract.getCreatedTimestamp();
        this.updatedUser = contract.getLastUpdatedUser();
        this.updationDate = contract.getLastUpdatedTimestamp();*/
       
    	this.contractIdForEdit = contract.getContractId();

        this.startDate = WPDStringUtil.getStringDate(contract.getStartDate());
        this.endDate = WPDStringUtil.getStringDate(contract.getEndDate());
        
        
        //DomainDetail domainDetail = saveContractBasicInfoResponse.getDomainDetail();
        if(domainDetail != null){
            this.lineOfBusinessDiv = WPDStringUtil
                    .getTildaString(domainDetail.getLineOfBusiness());
            this.businessEntityDiv = WPDStringUtil
                    .getTildaString(domainDetail.getBusinessEntity());
            this.businessGroupDiv = WPDStringUtil
                    .getTildaString(domainDetail.getBusinessGroup());
            this.marketSegment = WPDStringUtil.getTildaString(domainDetail.getMarketBusinessUnit());
        }
    }
    
    /**
	 * @return Returns the nextContractID.
	 */
	public String getNextContractID() {
		RetrieveReservedContractRequest retrieveReservedContractRequest;
		retrieveReservedContractRequest = (RetrieveReservedContractRequest) this
		.getServiceRequest(ServiceManager.RETRIEVE__RESERVED_CONTRACT);
		retrieveReservedContractRequest.setAction(RetrieveReservedContractRequest.RETRIEVE_CONTRACT_ID);
		RetrieveReservedContractResponse searchResponse = (RetrieveReservedContractResponse) executeService(
		 		retrieveReservedContractRequest);
	       
	        if(null != searchResponse &&  null != searchResponse.getContractId()){
	        	this.setNextContractID(searchResponse.getContractId());
	        }
		
		return nextContractID;
	}
   
	/*
	 * Method to retrieve the Contract details from System Pool
	 */
	public String retrieveContractDetails(){
		getSession().setAttribute("RENDER",null);
		if(!isRequiredReservedIdFieldsValid()){ 
	       	addAllMessagesToRequest(validationMessages);
	       	allIdsAvailable = "";
	       	return "";
        }
		String arg = "retrieve";
		RetrieveSystemPoolIdResponse retrieveResponse = getRetrieveSysPoolResponse(arg);
		
		this.saveIdList = retrieveResponse.getContractIdList();
		
		if(null != saveIdList){
			int size = saveIdList.size();
			ContractIDReservePoolRecord poolRecord = (ContractIDReservePoolRecord)saveIdList.get(size-1);
			this.endId = poolRecord.getContractId();
		}
		if (retrieveResponse.isSuccess()){
			allIdsAvailable = "available";
		}
		else{
			allIdsAvailable = "notAvailable";
		}
		getSession().setAttribute("SYSTEMPOOL_LIST",saveIdList);
		//allIdsAvailable = false;
		if(allIdsAvailable.equals("notAvailable")){
			this.systemIdList = retrieveResponse.getContractIdList();
			
		}else{
			this.systemIdList = null;
		}
   	
    	return "";
    }
   public RetrieveSystemPoolIdResponse getRetrieveSysPoolResponse(String arg){
   	RetrieveSystemPoolIdRequest retrieveSystemPoolIdRequest;
	retrieveSystemPoolIdRequest = (RetrieveSystemPoolIdRequest) this
		.getServiceRequest(ServiceManager.RETRIEVE_SYSTEMPOOL_CONTRACT);
	setValuesToRetrieveSystemPoolIdRequest(retrieveSystemPoolIdRequest, arg);
	RetrieveSystemPoolIdResponse response = (RetrieveSystemPoolIdResponse)executeService(retrieveSystemPoolIdRequest);
	return response;
   }
	public List setValuesToContractIDReservePoolRecordsInList(List systemList){
		int size = systemList.size();
		List newSystemList = new ArrayList();
		String available = null;
		String sysDomain = null;
		String effectiveDate = null;
		String expiryDate = null;
		for(int i=0;i<size;i++){
			ContractIDReservePoolRecord poolRecord = (ContractIDReservePoolRecord)systemList.get(i);
			available = poolRecord.getSystemPoolStatus().trim();
			if(available.equals("R") || available.equals("U")){
				 sysDomain = poolRecord.getSystemDomain();
				 effectiveDate = WPDStringUtil.getStringDate(poolRecord.getEffectiveDate());
				 expiryDate = WPDStringUtil.getStringDate(poolRecord.getExpiryDate());
				 available = "Not Available";
			}else{
				available = "Available";
			}
			 poolRecord.setSystemPoolStatus(available.trim());
			 newSystemList.add(poolRecord);
		}
		//this.systemIdList = newSystemList;
		return newSystemList;
	}

    /**
     * Method for saving the variable values.
     * @return String
     */
    public String saveReservedContract() {
    	List messages = new ArrayList();
       if(!isRequiredReservedIdFieldsValid()){ 
       		validation = "Invalid";
	       	addAllMessagesToRequest(validationMessages);
	       	return "";
       }
       String arg = "save";
       RetrieveSystemPoolIdResponse retrieveResponse = getRetrieveSysPoolResponse(arg);
       //List idList=this.saveIdList;
       //List idList = (List)getSession().getAttribute("SYSTEMPOOL_LIST");
       List idList = retrieveResponse.getContractIdList();
       List listToSave=new ArrayList();
       int size=idList.size();
       if(null!=idList && size>0){
       
        for(int i=0;i<size;i++){
        	ContractIDReservePoolRecord contractIDReservePoolRecord=new ContractIDReservePoolRecord();
        	contractIDReservePoolRecord=(ContractIDReservePoolRecord)idList.get(i);
        	String available=contractIDReservePoolRecord.getSystemPoolStatus().trim();
        	if(available.equals("N")||available.equals("M")||available.equals("T")){
        		
        		contractIDReservePoolRecord.setComment(this.comments);
        		//contractIDReservePoolRecord.setReservePoolStatus("R");
        		contractIDReservePoolRecord.setEffectiveDate(WPDStringUtil
        				.getDateFromString(this.startDate));
        		contractIDReservePoolRecord.setExpiryDate(WPDStringUtil
        				.getDateFromString(this.endDate));
        		
        		contractIDReservePoolRecord.setMarketSegment(WPDStringUtil
        				.getCodeFromTildaString(this.marketSegment,2,1,2));
        		listToSave.add(contractIDReservePoolRecord);
        		
        	}
        		
        }
      	
		SaveReservedContractRequest saveReservedContractRequest;
		saveReservedContractRequest = (SaveReservedContractRequest) this
		.getServiceRequest(ServiceManager.SAVE__RESERVED_CONTRACT);
		saveReservedContractRequest.setAction(SaveReservedContractRequest.CREATE_RESERVED_CONTRACT);
		setValuesToRequest(saveReservedContractRequest);
		
		saveReservedContractRequest.setContractIdList(listToSave);
				
		SaveReservedContractResponse searchResponse = (SaveReservedContractResponse) executeService(
				saveReservedContractRequest);
		 if(null != searchResponse ){
        	if(searchResponse.isSuccess()){
        		int sizeList = idList.size();
        		ContractIDReservePoolRecord poolRecord = (ContractIDReservePoolRecord)idList.get(sizeList-1);
        		this.setEndId(poolRecord.getContractId());
        		if (option.equals("0")){
	        		String[] argMsg = new String[2];
	                argMsg[0] = this.fromContractId.toUpperCase();
	                argMsg[1] = this.endContractId.toUpperCase();
	        		InformationalMessage message = new InformationalMessage("contractid.reserve.success.info");
	        		message.setParameters(new String[] {argMsg[0],argMsg[1]});
	        		this.validationMessages.add(message);
	        		if(StringUtils.contains(fromContractId.trim().toUpperCase(),"O") || StringUtils.contains(fromContractId.trim().toUpperCase(),"I") || 
	        				StringUtils.contains(endContractId.trim().toUpperCase(),"O") || StringUtils.contains(endContractId.trim().toUpperCase(),"I")){
	        			InformationalMessage message_check = new InformationalMessage("id.not.reserved");
	        			this.validationMessages.add(message_check);
	        		}
	        		
        		}else if(option.equals("1")){
        			String[] argMsg = new String[3];
	                argMsg[0] = this.noOfIds_start;
	                argMsg[1] = this.startContractId.toUpperCase();
	                argMsg[2] = this.endId.toUpperCase();
	                if(allIdsAvailable.equals("available")){
	                	InformationalMessage message = new InformationalMessage("contractid.reserve.success.continuous");
	                	message.setParameters(new String[] {argMsg[0],argMsg[1],argMsg[2]});
	                	this.validationMessages.add(message);
	                }else if(allIdsAvailable.equals("notAvailable")){
	                	InformationalMessage message = new InformationalMessage("contractid.reserve.success.noncontinuous");
		        		message.setParameters(new String[] {argMsg[0],argMsg[1],argMsg[2]});
		        		this.validationMessages.add(message);
	                }
	                if(StringUtils.contains(startContractId.trim().toUpperCase(),"O") || StringUtils.contains(startContractId.trim().toUpperCase(),"I")){
	        			InformationalMessage message_check = new InformationalMessage("id.not.reserved");
	        			this.validationMessages.add(message_check);
	        		}
            		
        		}else if(option.equals("2")){
        			String[] argMsg = new String[3];
        			argMsg[0] = this.noOfIds_next;
	                argMsg[1] = this.nextContractID.toUpperCase();
	                argMsg[2] = this.endId.toUpperCase();
	                if(allIdsAvailable.equals("available")){
	                	InformationalMessage message = new InformationalMessage("contractid.reserve.success.continuous");
	                	message.setParameters(new String[] {argMsg[0],argMsg[1],argMsg[2]});
	                	this.validationMessages.add(message);
	                }else if(allIdsAvailable.equals("notAvailable")){
	                	InformationalMessage message = new InformationalMessage("contractid.reserve.success.noncontinuous");
		        		message.setParameters(new String[] {argMsg[0],argMsg[1],argMsg[2]});
		        		this.validationMessages.add(message);
	                }
        		}
        		addAllMessagesToRequest(validationMessages);
        		getSession().setAttribute("RENDER","");
        		clearbackingbean();
        	}
		 }
	    	
        }
		this.allIdsAvailable="";
        return "createReserveContract";
    }
    
    /**
     * 
     * @return
     */
    public String releaseReservedContract(){
    	if(!isRequiredReleaseFieldsValid()){ 
	       	addAllMessagesToRequest(validationMessages);
	       	return "";
       }
    	return "";
    }
    
    
    private void clearbackingbean(){
    	
    	this.businessEntityDiv="";
    	this.businessGroupDiv="";
    	this.lineOfBusinessDiv="";
    	this.startDate ="";
    	this.endDate ="";
    	this.idGenerated="";
    	this.comments="";
    	this.fromContractId="";
    	this.endContractId="";
    	this.startContractId="";
    	this.noOfIds_start="";
    	this.noOfIds_next="";
    	this.allIdsAvailable="";
    	this.option= WebConstants.MSG_WARN_ID;    	
    	this.marketSegment=""; //Fixed bug on clearing market segment after reserving 
    }
    
    private void setValuesToRequest(SaveReservedContractRequest request){
    	
    	List lobCodeList;
		List businessEntityCodeList;
		List businessGroupCodeList;
		/*START CARS*/
		List marketBusinessUnitList;
		/*END CARS*/
	
	    lobCodeList = WPDStringUtil.getListFromTildaString(this.getLineOfBusinessDiv(), 2, 2, 2);
	    businessEntityCodeList = WPDStringUtil.getListFromTildaString(this.getBusinessEntityDiv(), 2, 2, 2);
	    businessGroupCodeList = WPDStringUtil.getListFromTildaString(this.getBusinessGroupDiv(), 2, 2, 2);
		/*START CARS*/
	    marketBusinessUnitList = WPDStringUtil.getListFromTildaString(this.getMarketSegment(), 2, 2, 2);
	    List domainList = BusinessUtil.convertToDomains(lobCodeList,businessEntityCodeList,businessGroupCodeList,marketBusinessUnitList);
		/*END CARS*/
	    ReserveContractVO reserveVO= new ReserveContractVO();
	    reserveVO.setBusinessDomains(domainList);
	    request.setReservedContractVO(reserveVO);
    	
    }
    
    private void setValuesToRetrieveSystemPoolIdRequest(RetrieveSystemPoolIdRequest request, String arg){
    	
    	List lobCodeList;
		List businessEntityCodeList;
		List businessGroupCodeList;
		/*START CARS*/
		List marketBusinessUnitList;
		/*END CARS*/
	
	    lobCodeList = WPDStringUtil.getListFromTildaString(this.getLineOfBusinessDiv(), 2, 2, 2);
	    businessEntityCodeList = WPDStringUtil.getListFromTildaString(this.getBusinessEntityDiv(), 2, 2, 2);
	    businessGroupCodeList = WPDStringUtil.getListFromTildaString(this.getBusinessGroupDiv(), 2, 2, 2);
		/*START CARS*/
		marketBusinessUnitList = WPDStringUtil.getListFromTildaString(this.getMarketSegment(), 2, 2, 2);
	   
	    List domainList = BusinessUtil.convertToDomains(lobCodeList,businessEntityCodeList,businessGroupCodeList,marketBusinessUnitList);
		/*END CARS*/

	    // ReserveContractVO reserveVO= new ReserveContractVO();
	    request.setBusinessDomains(domainList);
	    request.setStartDate(this.startDate);
	    request.setEndDate(this.endDate);
	    request.setComments(this.comments);
	    getSession().setAttribute("OPTION",this.option);
	    String rendered = "";
	    if (option.equals("0")){
	    	this.renderConfirmButton = false;
    		request.setFromContractId(this.fromContractId);
    		request.setEndContractId(this.endContractId);
    		request.setAction(RetrieveSystemPoolIdRequest.OPTION_ONE);
    	}else if(option.equals("1")){
    		if(arg.equals("retrieve")){
	    		if(this.continuousChecked1){
	    			rendered = "false";
	    		}else
	    			rendered = "true";
    			getSession().setAttribute("RENDER",rendered);
    		}else if(arg.equals("save")){
    			if(getSession().getAttribute("RENDER")=="true"){
    				this.continuousChecked1 = false;
    			}else if(getSession().getAttribute("RENDER")=="false"){
    				this.continuousChecked1 = true;
    			}
    		}
    		int count_start = Integer.parseInt(this.noOfIds_start);
    		request.setStartContractId(this.startContractId);
    		request.setNoOfIdsToGenerate_Start(count_start);
    		request.setAction(RetrieveSystemPoolIdRequest.OPTION_TWO);
    		request.setContinuous(this.continuousChecked1);
    	}else if(option.equals("2")){
    		
    		if(arg.equals("retrieve")){
	    		if(this.continuousChecked2){
	    			rendered = "false";
	    		}else
	    			rendered = "true";
    			getSession().setAttribute("RENDER",rendered);
    		}else if(arg.equals("save")){
    			if(getSession().getAttribute("RENDER")=="true"){
    				this.continuousChecked2 = false;
    			}else if(getSession().getAttribute("RENDER")=="false"){
    				this.continuousChecked2 = true;
    			}
    		}
    		
    		int count_next = Integer.parseInt(this.noOfIds_next);
    		request.setNextAvailableId(this.nextContractID);
    		request.setNoOfIdsToGenerate_Next(count_next);
    		request.setAction(RetrieveSystemPoolIdRequest.OPTION_THREE);
    		request.setContinuous(this.continuousChecked2);
    		
    	}
	        	
    }
    
    /*
     * Function to validate all the form fields 
     */
    private boolean isRequiredFieldsValid() {
        validationMessages = new ArrayList();
        boolean requiredField = false;
        boolean dateInvalid = false;
        this.lobInvalid = false;
        this.entityInvalid = false;
        this.groupInvalid = false;
        this.commentsInvalid=false;
     
        this.startDateInvalid = false;
        this.endDateInvalid = false;
             
        if (this.lineOfBusinessDiv == null || "".equals(this.lineOfBusinessDiv)) {
            lobInvalid = true;
            requiredField = true;
        }
        if (this.businessEntityDiv == null || "".equals(this.businessEntityDiv)) {
            entityInvalid = true;
            requiredField = true;
        }
        if (this.businessGroupDiv == null || "".equals(this.businessGroupDiv)) {
            groupInvalid = true;
            requiredField = true;
        }
        
        if(this.marketSegment == null || "".equals(this.marketSegment) ){
        	marketInvalid = true;
        	requiredField = true;
        }
        
        if (this.startDate == null || "".equals(this.startDate.trim())) {
            startDateInvalid = true;
            requiredField = true;
        }
       
    	else if(!(WPDStringUtil.isValidDate(this.startDate))) {
			ErrorMessage errorMessage = new ErrorMessage(
					WebConstants.INPUT_FORMAT_INVALID);
			errorMessage.setParameters(new String[] { "Effective Date" });
			validationMessages.add(errorMessage);
			dateInvalid = true;
			 // requiredField = true;
			startDateInvalid = true;
		}
        if (this.endDate == null || "".equals(this.endDate.trim())) {
            endDateInvalid = true;
            requiredField = true;
        }
        else if(!(WPDStringUtil.isValidDate(this.endDate))) {
			ErrorMessage errorMessage = new ErrorMessage(
					WebConstants.INPUT_FORMAT_INVALID);
			errorMessage.setParameters(new String[] { "Expiry Date" });
			validationMessages.add(errorMessage);
			dateInvalid = true;
			//  requiredField = true;
			endDateInvalid = true;
		}
              
        if (this.comments==null || "".equals(this.comments.trim())){
        	commentsInvalid=true;
        	requiredField = true;
        }
        else if(this.comments.trim()!=null && this.comments.length()>250 ){
        	this.validationMessages.add(new ErrorMessage(
                    WebConstants.COMMENTS_LENGTH_INVALID));
            addAllMessagesToRequest(validationMessages);
            return false;
        }
        
        if(this.option.equals("0")){
        	if (this.fromContractId==null || "".equals(this.fromContractId.trim())){
        		fromIdInvalid = true;
            	requiredField = true;
            }
        	if(this.endContractId==null || "".equals(this.endContractId.trim())){
        		endIdInvalid = true;
            	requiredField = true;
            }
        }
        if(this.option.equals("1")){
        	if (this.startContractId==null || "".equals(this.startContractId.trim())){
        		startIdInvalid = true;
            	requiredField = true;
            }
        	if(this.noOfIds_start==null || "".equals(this.noOfIds_start.trim())){
        		noOfIds_1Invalid = true;
            	requiredField = true;
            }
        }
        if(this.option.equals("2")){
        	if (this.nextContractID==null || "".equals(this.nextContractID.trim())){
        		nextAvailableInvalid = true;
            	requiredField = true;
            }
        	if(this.noOfIds_next==null || "".equals(this.noOfIds_next.trim())){
        		noOfIds_2Invalid = true;
            	requiredField = true;
            }
        }
        
        if (requiredField) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            addAllMessagesToRequest(validationMessages);
            return false;
        }
        
        if(!(endDateInvalid == true || startDateInvalid == true)){
        	dateInvalid = validateDates( false);
        }
        if(dateInvalid){
        	addAllMessagesToRequest(validationMessages);
        	return false;
        }

        return true;
    }
    
    private boolean isRequiredReleaseFieldsValid() {
        validationMessages = new ArrayList();
        boolean requiredField = false;
        
        if (this.endContractId == null || "".equals(this.endContractId.trim())){
            endContractIdInvalid = true;
           }
        if (this.endContractId != null || !"".equals(this.endContractId.trim())){
            endContractIdInvalid = false;
           }
        if (this.startContractId == null || "".equals(this.startContractId.trim())){
            startContractIdInvalid = true;
           }
        if (this.startContractId != null || !"".equals(this.startContractId.trim())){
        	startContractIdInvalid = false;
           }
        if(startContractIdInvalid==true &&endContractIdInvalid==false)
        	requiredField=true;
        if(startContractIdInvalid==false &&endContractIdInvalid==true)
        	requiredField=true;	
        
        if (requiredField) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            addAllMessagesToRequest(validationMessages);
            return false;
        }
        return true;
    }
    
   /* 
    public boolean validateFields() {
    	boolean requiredField = false;
        boolean dateInvalid = false;
        boolean idInvalid = false;
        boolean countInvalid = false;
        boolean endIdSmaller = false;
        this.lobInvalid = false;
        this.entityInvalid = false;
        this.groupInvalid = false;
        this.commentsInvalid=false;
        
        this.fromIdInvalid = false;
        this.endIdInvalid = false;
        
        this.startIdInvalid = false;
        this.noOfIds_1Invalid = false;
        
        this.nextAvailableInvalid = false;
        this.noOfIds_2Invalid = false;
        
        this.startDateInvalid = false;
        this.endDateInvalid = false;

        if (!isAllFieldsBlank()) {
            addAllMessagesToRequest(validationMessages);
        } else if (!validateDateFormat()) {
            addAllMessagesToRequest(validationMessages);
            validationStatus = false;
        } else if (!validateEffectiveDate(this.getEffectiveDate())) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.EFFECTIVE_DATE_INVALID));
            addAllMessagesToRequest(validationMessages);
            validationStatus = false;
        } else if (!isDateValid()) {
            addAllMessagesToRequest(validationMessages);
            validationStatus = false;
        } else if (!validateNameLength()) {
            addAllMessagesToRequest(validationMessages);
            validationStatus = false;
        } else if (!validateDescriptionLength()) {
            addAllMessagesToRequest(validationMessages);

        }
		else {
            validationStatus = true;
        }
        return validationStatus;
    }
    
    public boolean isAllFieldsBlank() {

    	 if (this.lineOfBusinessDiv == null || "".equals(this.lineOfBusinessDiv) || ("--Select--").equals(this.lineOfBusinessDiv)) {
            lobInvalid = true;
            //requiredField = true;
        }
        if (this.businessEntityDiv == null || "".equals(this.businessEntityDiv)||("--Select--").equals(this.businessEntityDiv)) {
            entityInvalid = true;
            //requiredField = true;
        }
        if (this.businessGroupDiv == null || "".equals(this.businessGroupDiv) || ("--Select--").equals(this.businessGroupDiv)) {
            groupInvalid = true;
            //requiredField = true;
        }
        if (this.startDate == null || "".equals(this.startDate.trim())) {
            startDateInvalid = true;
            //requiredField = true;
        }  
        if (this.endDate == null || "".equals(this.endDate.trim())) {
            endDateInvalid = true;
            //requiredField = true;
        }
        if(this.option.equals("0")){
        	if (this.fromContractId==null || "".equals(this.fromContractId.trim())){
        		fromIdInvalid = true;
            	//requiredField = true;
        	}
        	if(this.endContractId==null || "".equals(this.endContractId.trim())){
        		endIdInvalid = true;
            	//requiredField = true;
        	}
        	
        }
        if(this.option.equals("1")){
        	if (this.startContractId==null || "".equals(this.startContractId.trim())){
        		startIdInvalid = true;
            	//requiredField = true;
            }
        	if(this.noOfIds_start==null || "".equals(this.noOfIds_start.trim())){
        		noOfIds_1Invalid = true;
            	//requiredField = true;
            }
        	
        }
        if(this.option.equals("2")){
        	if(this.noOfIds_next==null || "".equals(this.noOfIds_next.trim())){
        		noOfIds_2Invalid = true;
            	//requiredField = true;
            }
        }
        
        
        if (this.comments==null || "".equals(this.comments.trim())){
        	commentsInvalid=true;
        	//requiredField = true;
        }
        
        if (lobInvalid == false && entityInvalid == false
                && groupInvalid == false && startDateInvalid == false
                && endDateInvalid == false && fromIdInvalid == false
                && endIdInvalid == false && startIdInvalid == false
                && noOfIds_1Invalid == false && noOfIds_2Invalid == false) {
            validationStatus = true;
        }        
        else {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            validationStatus = false;
        }
        return validationStatus;
    }
    */
    /*
     * Function to validate all the form fields 
     */
    private boolean isRequiredReservedIdFieldsValid() {
        validationMessages = new ArrayList();
        boolean requiredField = false;
        boolean dateInvalid = false;
        boolean idInvalid = false;
        boolean countInvalid = false;
        boolean endIdSmaller = false;
        this.lobInvalid = false;
        this.entityInvalid = false;
        this.groupInvalid = false;
        this.commentsInvalid=false;
        
        this.fromIdInvalid = false;
        this.endIdInvalid = false;
        
        this.startIdInvalid = false;
        this.noOfIds_1Invalid = false;
        
        this.nextAvailableInvalid = false;
        this.noOfIds_2Invalid = false;
        
        this.startDateInvalid = false;
        this.endDateInvalid = false;
        
        this.marketInvalid = false;
        

         
        if (this.lineOfBusinessDiv == null || "".equals(this.lineOfBusinessDiv) || ("--Select--").equals(this.lineOfBusinessDiv)) {
            lobInvalid = true;
            requiredField = true;
        }
        if (this.businessEntityDiv == null || "".equals(this.businessEntityDiv)||("--Select--").equals(this.businessEntityDiv)) {
            entityInvalid = true;
            requiredField = true;
        }
        if (this.businessGroupDiv == null || "".equals(this.businessGroupDiv) || ("--Select--").equals(this.businessGroupDiv)) {
            groupInvalid = true;
            requiredField = true;
        }
        if(this.marketSegment == null || "".equals(this.marketSegment) || ("--Select--").equals(this.marketSegment)){
        	marketInvalid = true;
        	requiredField = true;
        }
        
        if (this.startDate == null || "".equals(this.startDate.trim())) {
            startDateInvalid = true;
            requiredField = true;
        }        
        else if(!(WPDStringUtil.isValidDate(this.startDate))) {
			ErrorMessage errorMessage = new ErrorMessage(
					WebConstants.INPUT_FORMAT_INVALID);
			errorMessage.setParameters(new String[] { "Effective Date" });
			validationMessages.add(errorMessage);
			dateInvalid = true;
			 // requiredField = true;
			startDateInvalid = true;
			//return false;
		}
        
        
        if (this.endDate == null || "".equals(this.endDate.trim())) {
            endDateInvalid = true;
            requiredField = true;
        }
        else if(!(WPDStringUtil.isValidDate(this.endDate))) {
			ErrorMessage errorMessage = new ErrorMessage(
					WebConstants.INPUT_FORMAT_INVALID);
			errorMessage.setParameters(new String[] { "Expiry Date" });
			validationMessages.add(errorMessage);
			dateInvalid = true;
			//  requiredField = true;
			endDateInvalid = true;
			//return false;
		}
              
        
        
        if(this.option.equals("0")){
        	if (this.fromContractId==null || "".equals(this.fromContractId.trim())){        		
        		fromIdInvalid = true;
            	requiredField = true;
            }else if (!fromContractId.matches(WebConstants.REGEX_ID_FORMAT_STRING)) {
            	idInvalid = true;
            	fromIdInvalid = true;
               
            }
        	
        	if(this.endContractId==null || "".equals(this.endContractId.trim())){        	
        		endIdInvalid = true;
            	requiredField = true;
            }else if (!endContractId.matches(WebConstants.REGEX_ID_FORMAT_STRING)) {
            	idInvalid = true;
            	endIdInvalid = true;
          
            }
            if(this.fromContractId.toUpperCase().compareTo(this.endContractId.toUpperCase())>0){
            	endIdSmaller = true;
            	endIdInvalid = true;
            }
        }
        if(this.option.equals("1")){
        	if (this.startContractId==null || "".equals(this.startContractId.trim())){
        		startIdInvalid = true;
            	requiredField = true;
            }else if (!startContractId.matches(WebConstants.REGEX_ID_FORMAT_STRING)) {
            	idInvalid = true;
            	startIdInvalid = true;
            	
            }
        	if(this.noOfIds_start==null || "".equals(this.noOfIds_start.trim())){
        		noOfIds_1Invalid = true;
            	requiredField = true;
            }else if(!WPDStringUtil.isNumber(this.noOfIds_start)){ // || this.noOfIds_start.equals("0") ){
            	countInvalid = true;
            	noOfIds_1Invalid = true;
            	
            }else{
            	int count_start = Integer.parseInt(this.noOfIds_start);
            	if(count_start <= 0 ){
            		countInvalid = true;
                	noOfIds_1Invalid = true;
            	}
            }
        	
        }
        if(this.option.equals("2")){
        	if(this.noOfIds_next==null || "".equals(this.noOfIds_next.trim())){
        		noOfIds_2Invalid = true;
            	requiredField = true;
            }else if(!WPDStringUtil.isNumber(this.noOfIds_next)){   //|| this.noOfIds_next.equals("0")){
            	countInvalid = true;
            	noOfIds_2Invalid = true;
            	
            }else{
            	int count_start = Integer.parseInt(this.noOfIds_next);
            	if(count_start <= 0 ){
            		countInvalid = true;
                	noOfIds_2Invalid = true;
            	}
            }
        }
        
        
        if (this.comments==null || "".equals(this.comments.trim())){
        	commentsInvalid=true;
        	requiredField = true;
        }
       
      
        
        if (requiredField) {
            this.validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            addAllMessagesToRequest(validationMessages);
            return false;
        }
        if(idInvalid){
        	this.validationMessages.add(new ErrorMessage(WebConstants.CONTRACT_ID_INVALID));
            addAllMessagesToRequest(validationMessages);
            return false;
        }
        if(endIdSmaller){
        	this.validationMessages.add(new ErrorMessage(WebConstants.END_CONTRACT_ID_SMALLER));
            addAllMessagesToRequest(validationMessages);
            return false;
        }
        if(countInvalid){
        	this.validationMessages.add(new ErrorMessage(WebConstants.VALID_COUNT));
            addAllMessagesToRequest(validationMessages);
            return false;
        }
        
        if(this.comments.trim()!=null && this.comments.length()>250 ){
         	commentsInvalid=true;
        	this.validationMessages.add(new ErrorMessage(
                    WebConstants.COMMENTS_LENGTH_INVALID));
            addAllMessagesToRequest(validationMessages);
            return false;
        }
        
        if(!(endDateInvalid == true || startDateInvalid == true)){
        	dateInvalid = validateDates( false);
        }
        if(dateInvalid){
        	addAllMessagesToRequest(validationMessages);
        	return false;
        }

        return true;
    }
    
    public boolean isValidContractId(String id){
    	
    	
    	return true;	
    }
    
    public boolean validateDates(boolean boolUpdate){

        Date date = new Date();
        int year1 = date.getYear();
        int month1 = date.getMonth();
        int day1 =date.getDate(); 
        
        Date dateNewOne = new Date(year1,month1,day1);
        if(boolUpdate == false){
	        if((WPDStringUtil.getDateFromString(this.startDate)).compareTo(dateNewOne) < 0 ){
	        	this.validationMessages.add(new ErrorMessage(
	                    WebConstants.RESERVED_DATES_CONDITION01));
	            addAllMessagesToRequest(validationMessages);
	            startDateInvalid = true;
	            return true;
	        	
	        }
        }
        Date dateStart = WPDStringUtil.getDateFromString(this.startDate);
        int year = dateStart.getYear();
        int month = dateStart.getMonth();
        int day =dateStart.getDate(); 
        
        if(month == 1 && day == 29){
        	day = 28;
        }
        
        int  yearNew = year +3;
        Date dateNew = new Date(yearNew, month , day);
        Date endDt= WPDStringUtil.getDateFromString(this.endDate);
        
        if(endDt.compareTo(dateNew)>0){
        	startDateInvalid = true;
        	endDateInvalid = true;
        	this.validationMessages.add(new ErrorMessage(
                    WebConstants.RESERVED_DATES_CONDITION));
            addAllMessagesToRequest(validationMessages);
            return true;
        }
        if(WPDStringUtil.getDateFromString(this.endDate).compareTo(WPDStringUtil.getDateFromString(this.startDate)) <= 0){
        	
	       	endDateInvalid = true;
			ErrorMessage errorMessage = new ErrorMessage(
					WebConstants.EFFECIVE_GRATER_EXPIRY);
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
			return true;
       }
        return false;
    }
	/**
	 * @return Returns the idGenerated.
	 */
	public String getIdGenerated() {
		return idGenerated;
	}
	/**
	 * @param idGenerated The idGenerated to set.
	 */
	public void setIdGenerated(String idGenerated) {
		this.idGenerated = idGenerated;
	}
	
	/**
	 * @param nextContractID The nextContractID to set.
	 */
	public void setNextContractID(String nextContractID) {
		this.nextContractID = nextContractID;
	}
	/**
	 * @return Returns the requiredId.
	 */
	public boolean isRequiredId() {
		return requiredId;
	}
	/**
	 * @param requiredId The requiredId to set.
	 */
	public void setRequiredId(boolean requiredId) {
		this.requiredId = requiredId;
	}
	/**
	 * @return Returns the requiredNextId.
	 */
	public boolean isRequiredNextId() {
		return requiredNextId;
	}
	/**
	 * @param requiredNextId The requiredNextId to set.
	 */
	public void setRequiredNextId(boolean requiredNextId) {
		this.requiredNextId = requiredNextId;
	}
	/**
	 * @return Returns the validationMessages.
	 */
	public ArrayList getValidationMessages() {
		return validationMessages;
	}
	/**
	 * @param validationMessages The validationMessages to set.
	 */
	public void setValidationMessages(ArrayList validationMessages) {
		this.validationMessages = validationMessages;
	}
	/**
	 * @return Returns the disableFlag.
	 */
	public boolean isDisableFlag() {
		return disableFlag;
	}
	/**
	 * @param disableFlag The disableFlag to set.
	 */
	public void setDisableFlag(boolean disableFlag) {
		this.disableFlag = disableFlag;
	}
	/**
	 * @return Returns the disableFlagOne.
	 */
	public boolean isDisableFlagOne() {
		return disableFlagOne;
	}
	/**
	 * @param disableFlagOne The disableFlagOne to set.
	 */
	public void setDisableFlagOne(boolean disableFlagOne) {
		this.disableFlagOne = disableFlagOne;
	}
	/**
	 * @return Returns the businessEntityDiv.
	 */
	public String getBusinessEntityDiv() {
		return businessEntityDiv;
	}
	/**
	 * @param businessEntityDiv The businessEntityDiv to set.
	 */
	public void setBusinessEntityDiv(String businessEntityDiv) {
		this.businessEntityDiv = businessEntityDiv;
	}
	/**
	 * @return Returns the businessGroupDiv.
	 */
	public String getBusinessGroupDiv() {
		return businessGroupDiv;
	}
	/**
	 * @param businessGroupDiv The businessGroupDiv to set.
	 */
	public void setBusinessGroupDiv(String businessGroupDiv) {
		this.businessGroupDiv = businessGroupDiv;
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
	 * @return Returns the endDateInvalid.
	 */
	public boolean isEndDateInvalid() {
		return endDateInvalid;
	}
	/**
	 * @param endDateInvalid The endDateInvalid to set.
	 */
	public void setEndDateInvalid(boolean endDateInvalid) {
		this.endDateInvalid = endDateInvalid;
	}
	/**
	 * @return Returns the entityInvalid.
	 */
	public boolean isEntityInvalid() {
		return entityInvalid;
	}
	/**
	 * @param entityInvalid The entityInvalid to set.
	 */
	public void setEntityInvalid(boolean entityInvalid) {
		this.entityInvalid = entityInvalid;
	}
	/**
	 * @return Returns the groupInvalid.
	 */
	public boolean isGroupInvalid() {
		return groupInvalid;
	}
	/**
	 * @param groupInvalid The groupInvalid to set.
	 */
	public void setGroupInvalid(boolean groupInvalid) {
		this.groupInvalid = groupInvalid;
	}
	/**
	 * @return Returns the lineOfBusinessDiv.
	 */
	public String getLineOfBusinessDiv() {
		return lineOfBusinessDiv;
	}
	/**
	 * @param lineOfBusinessDiv The lineOfBusinessDiv to set.
	 */
	public void setLineOfBusinessDiv(String lineOfBusinessDiv) {
		this.lineOfBusinessDiv = lineOfBusinessDiv;
	}
	/**
	 * @return Returns the lobInvalid.
	 */
	public boolean isLobInvalid() {
		return lobInvalid;
	}
	/**
	 * @param lobInvalid The lobInvalid to set.
	 */
	public void setLobInvalid(boolean lobInvalid) {
		this.lobInvalid = lobInvalid;
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
	 * @return Returns the startDateInvalid.
	 */
	public boolean isStartDateInvalid() {
		return startDateInvalid;
	}
	/**
	 * @param startDateInvalid The startDateInvalid to set.
	 */
	public void setStartDateInvalid(boolean startDateInvalid) {
		this.startDateInvalid = startDateInvalid;
	}
	/**
	 * @return Returns the contractIdForEdit.
	 */
	public String getContractIdForEdit() {
		return contractIdForEdit;
	}
	/**
	 * @param contractIdForEdit The contractIdForEdit to set.
	 */
	public void setContractIdForEdit(String contractIdForEdit) {
		this.contractIdForEdit = contractIdForEdit;
	}
	/**
	 * @return Returns the contractKeyForEdit.
	 */
	public int getContractKeyForEdit() {
		return contractKeyForEdit;
	}
	/**
	 * @param contractKeyForEdit The contractKeyForEdit to set.
	 */
	public void setContractKeyForEdit(int contractKeyForEdit) {
		this.contractKeyForEdit = contractKeyForEdit;
	}
	
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * @return Returns the option.
	 */
	public String getOption() {
		return option;
	}
	/**
	 * @param option The option to set.
	 */
	public void setOption(String option) {
		this.option = option;
	}
	
	
	/**
	 * @return Returns the commentsInvalid.
	 */
	public boolean isCommentsInvalid() {
		return commentsInvalid;
	}
	/**
	 * @param commentsInvalid The commentsInvalid to set.
	 */
	public void setCommentsInvalid(boolean commentsInvalid) {
		this.commentsInvalid = commentsInvalid;
	}
	/**
	 * @return Returns the endContractId.
	 */
	public String getEndContractId() {
		return endContractId;
	}
	/**
	 * @param endContractId The endContractId to set.
	 */
	public void setEndContractId(String endContractId) {
		this.endContractId = endContractId;
	}
	/**
	 * @return Returns the fromContractId.
	 */
	public String getFromContractId() {
		return fromContractId;
	}
	/**
	 * @param fromContractId The fromContractId to set.
	 */
	public void setFromContractId(String fromContractId) {
		this.fromContractId = fromContractId;
	}
	/**
	 * @return Returns the noOfIds.
	 */
	public String getNoOfIds() {
		return noOfIds;
	}
	/**
	 * @param noOfIds The noOfIds to set.
	 */
	public void setNoOfIds(String noOfIds) {
		this.noOfIds = noOfIds;
	}
	/**
	 * @return Returns the startContractId.
	 */
	public String getStartContractId() {
		return startContractId;
	}
	/**
	 * @param startContractId The startContractId to set.
	 */
	public void setStartContractId(String startContractId) {
		this.startContractId = startContractId;
	}
	
	
	/**
	 * @return Returns the continuousChecked.
	 */
	public boolean isContinuousChecked() {
		return continuousChecked;
	}
	/**
	 * @param continuousChecked The continuousChecked to set.
	 */
	public void setContinuousChecked(boolean continuousChecked) {
		this.continuousChecked = continuousChecked;
	}
	
	
	/**
	 * @return Returns the releaseExpired.
	 */
	public String getReleaseExpired() {
		return releaseExpired;
	}
	/**
	 * @param releaseExpired The releaseExpired to set.
	 */
	public void setReleaseExpired(String releaseExpired) {
		this.releaseExpired = releaseExpired;
	}
	
	
	/**
	 * @return Returns the endIdInvalid.
	 */
	public boolean isEndIdInvalid() {
		return endIdInvalid;
	}
	/**
	 * @param endIdInvalid The endIdInvalid to set.
	 */
	public void setEndIdInvalid(boolean endIdInvalid) {
		this.endIdInvalid = endIdInvalid;
	}
	/**
	 * @return Returns the fromIdInvalid.
	 */
	public boolean isFromIdInvalid() {
		return fromIdInvalid;
	}
	/**
	 * @param fromIdInvalid The fromIdInvalid to set.
	 */
	public void setFromIdInvalid(boolean fromIdInvalid) {
		this.fromIdInvalid = fromIdInvalid;
	}
	/**
	 * @return Returns the nextAvailableInvalid.
	 */
	public boolean isNextAvailableInvalid() {
		return nextAvailableInvalid;
	}
	/**
	 * @param nextAvailableInvalid The nextAvailableInvalid to set.
	 */
	public void setNextAvailableInvalid(boolean nextAvailableInvalid) {
		this.nextAvailableInvalid = nextAvailableInvalid;
	}
	/**
	 * @return Returns the noOfIds_1Invalid.
	 */
	public boolean isNoOfIds_1Invalid() {
		return noOfIds_1Invalid;
	}
	/**
	 * @param noOfIds_1Invalid The noOfIds_1Invalid to set.
	 */
	public void setNoOfIds_1Invalid(boolean noOfIds_1Invalid) {
		this.noOfIds_1Invalid = noOfIds_1Invalid;
	}
	/**
	 * @return Returns the noOfIds_2Invalid.
	 */
	public boolean isNoOfIds_2Invalid() {
		return noOfIds_2Invalid;
	}
	/**
	 * @param noOfIds_2Invalid The noOfIds_2Invalid to set.
	 */
	public void setNoOfIds_2Invalid(boolean noOfIds_2Invalid) {
		this.noOfIds_2Invalid = noOfIds_2Invalid;
	}
	/**
	 * @return Returns the noOfIds_next.
	 */
	public String getNoOfIds_next() {
		return noOfIds_next;
	}
	/**
	 * @param noOfIds_next The noOfIds_next to set.
	 */
	public void setNoOfIds_next(String noOfIds_next) {
		this.noOfIds_next = noOfIds_next;
	}
	/**
	 * @return Returns the noOfIds_start.
	 */
	public String getNoOfIds_start() {
		return noOfIds_start;
	}
	/**
	 * @param noOfIds_start The noOfIds_start to set.
	 */
	public void setNoOfIds_start(String noOfIds_start) {
		this.noOfIds_start = noOfIds_start;
	}
	/**
	 * @return Returns the startIdInvalid.
	 */
	public boolean isStartIdInvalid() {
		return startIdInvalid;
	}
	/**
	 * @param startIdInvalid The startIdInvalid to set.
	 */
	public void setStartIdInvalid(boolean startIdInvalid) {
		this.startIdInvalid = startIdInvalid;
	}
	/**
	 * @return Returns the systemIdList.
	 */
	public List getSystemIdList() {
		return systemIdList;
	}
	/**
	 * @param systemIdList The systemIdList to set.
	 */
	public void setSystemIdList(List systemIdList) {
		this.systemIdList = systemIdList;
	}
	
	/**
	 * @return Returns the init.
	 */
	public String getInit() {
		List tempList = (List)getSession().getAttribute("SYSTEMPOOL_LIST");
		//this.saveIdList = (List)getSession().getAttribute("SYSTEMPOOL_LIST");
		if(null != tempList && tempList.size()>0)
			this.newSystemList = setValuesToContractIDReservePoolRecordsInList(tempList);
		InformationalMessage message = new InformationalMessage(BusinessConstants.CONSECUTIVE_IDS_NOT_AVAILABLE);
		messagePopup.add(message);
		addAllMessagesToRequest(messagePopup);
		return init;
	}
	/**
	 * @param init The init to set.
	 */
	public void setInit(String init) {
		this.init = init;
	}
	
	
	/**
	 * @return Returns the endContractIdInvalid.
	 */
	public boolean isEndContractIdInvalid() {
		return endContractIdInvalid;
	}
	/**
	 * @param endContractIdInvalid The endContractIdInvalid to set.
	 */
	public void setEndContractIdInvalid(boolean endContractIdInvalid) {
		this.endContractIdInvalid = endContractIdInvalid;
	}
	/**
	 * @return Returns the startContractIdInvalid.
	 */
	public boolean isStartContractIdInvalid() {
		return startContractIdInvalid;
	}
	/**
	 * @param startContractIdInvalid The startContractIdInvalid to set.
	 */
	public void setStartContractIdInvalid(boolean startContractIdInvalid) {
		this.startContractIdInvalid = startContractIdInvalid;
	}
	
	
	/**
	 * @return Returns the saveIdList.
	 */
	public List getSaveIdList() {
		return saveIdList;
	}
	/**
	 * @param saveIdList The saveIdList to set.
	 */
	public void setSaveIdList(List saveIdList) {
		this.saveIdList = saveIdList;
	}
	
	
	/**
	 * @return Returns the allIdsAvailable.
	 */
	public String getAllIdsAvailable() {
		return allIdsAvailable;
	}
	/**
	 * @param allIdsAvailable The allIdsAvailable to set.
	 */
	public void setAllIdsAvailable(String allIdsAvailable) {
		this.allIdsAvailable = allIdsAvailable;
	}
	/**
	 * @return Returns the continuousChecked1.
	 */
	public boolean isContinuousChecked1() {
		return continuousChecked1;
	}
	/**
	 * @param continuousChecked1 The continuousChecked1 to set.
	 */
	public void setContinuousChecked1(boolean continuousChecked1) {
		this.continuousChecked1 = continuousChecked1;
		/*if(null == getSession().getAttribute("RENDER") || getSession().getAttribute("RENDER").equals(""))
			this.continuousChecked1 = continuousChecked1;
		else if(getSession().getAttribute("RENDER").equals("true"))
			this.continuousChecked1 = false;
		else 
			this.continuousChecked1 = true;*/
		
	}
	/**
	 * @return Returns the continuousChecked2.
	 */
	public boolean isContinuousChecked2() {
		return continuousChecked2;
	}
	/**
	 * @param continuousChecked2 The continuousChecked2 to set.
	 */
	public void setContinuousChecked2(boolean continuousChecked2) {
		this.continuousChecked2 = continuousChecked2;
		/*if(null == getSession().getAttribute("RENDER") || getSession().getAttribute("RENDER").equals(""))
			this.continuousChecked2 = continuousChecked2;
		else if(getSession().getAttribute("RENDER").equals("true"))
			this.continuousChecked2 = false;
		else 
			this.continuousChecked2 = true;*/
	}
	/**
	 * @return Returns the newSystemList.
	 */
	public List getNewSystemList() {
		return newSystemList;
	}
	/**
	 * @param newSystemList The newSystemList to set.
	 */
	public void setNewSystemList(List newSystemList) {
		this.newSystemList = newSystemList;
	}
	/**
	 * @return Returns the renderConfirmButton.
	 */
	public boolean getRenderConfirmButton() {
		String option = getSession().getAttribute("OPTION").toString();
			
		if(option.equals("0")){
			renderConfirmButton = false;
		}else if(option.equals("1")){
			String render = getSession().getAttribute("RENDER").toString();	
			if(render.equals("false")){
				renderConfirmButton = false;
			}else 
				renderConfirmButton = true;
		}else if(option.equals("2")){
			String render = getSession().getAttribute("RENDER").toString();	
			if(render.equals("false")){
				renderConfirmButton = false;
			}else 
				renderConfirmButton = true;
		}
		
		return renderConfirmButton;
	}
	/**
	 * @param renderConfirmButton The renderConfirmButton to set.
	 */
	public void setRenderConfirmButton(boolean renderConfirmButton) {
		this.renderConfirmButton = renderConfirmButton;
	}
	
	
	/**
	 * @return Returns the marketInvalid.
	 */
	public boolean isMarketInvalid() {
		return marketInvalid;
	}
	/**
	 * @param marketInvalid The marketInvalid to set.
	 */
	public void setMarketInvalid(boolean marketInvalid) {
		this.marketInvalid = marketInvalid;
	}
	/**
	 * @return Returns the marketSegment.
	 */
	public String getMarketSegment() {
		return marketSegment;
	}
	/**
	 * @param marketSegment The marketSegment to set.
	 */
	public void setMarketSegment(String marketSegment) {
		this.marketSegment = marketSegment;
	}
	/**
	 * @return Returns the marketSegmentList.
	 */
	public List getMarketSegmentList() {
		marketSegmentList = new ArrayList();
			SelectItem sel = new SelectItem("");
	        SelectItem select = new SelectItem("National");
	        SelectItem select1 = new SelectItem("Local");
	        marketSegmentList.add(sel);
	        marketSegmentList.add(select);
	        marketSegmentList.add(select1);
	     return marketSegmentList;
	}
	/**
	 * @param marketSegmentList The marketSegmentList to set.
	 */
	public void setMarketSegmentList(List marketSegmentList) {
		this.marketSegmentList = marketSegmentList;
	}
	
	/**
	 * @return Returns the validation.
	 */
	public String getValidation() {
		return validation;
	}
	/**
	 * @param validation The validation to set.
	 */
	public void setValidation(String validation) {
		this.validation = validation;
	}
	
	
	/**
	 * @return Returns the commentsForEdit.
	 */
	public String getCommentsForEdit() {
		return commentsForEdit;
	}
	/**
	 * @param commentsForEdit The commentsForEdit to set.
	 */
	public void setCommentsForEdit(String commentsForEdit) {
		this.commentsForEdit = commentsForEdit;
	}
	
	
	/**
	 * @return Returns the marketSegmentDiv.
	 */
	public String getMarketSegmentDiv() {
		return marketSegmentDiv;
	}
	/**
	 * @param marketSegmentDiv The marketSegmentDiv to set.
	 */
	public void setMarketSegmentDiv(String marketSegmentDiv) {
		this.marketSegmentDiv = marketSegmentDiv;
	}
	
	
	/**
	 * @return Returns the reservepoolStatus.
	 */
	public String getReservepoolStatus() {
		return reservepoolStatus;
	}
	/**
	 * @param reservepoolStatus The reservepoolStatus to set.
	 */
	public void setReservepoolStatus(String reservepoolStatus) {
		this.reservepoolStatus = reservepoolStatus;
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
	 * @return Returns the messagePopup.
	 */
	public ArrayList getMessagePopup() {
		return messagePopup;
	}
	/**
	 * @param messagePopup The messagePopup to set.
	 */
	public void setMessagePopup(ArrayList messagePopup) {
		this.messagePopup = messagePopup;
	}
	/**
	 * @return Returns the endId.
	 */
	public String getEndId() {
		return endId;
	}
	/**
	 * @param endId The endId to set.
	 */
	public void setEndId(String endId) {
		this.endId = endId;
	}
	/**
	 * @return Returns the continuousChecked1Hidden.
	 */
	public boolean isContinuousChecked1Hidden() {
		return continuousChecked1Hidden;
	}
	/**
	 * @param continuousChecked1Hidden The continuousChecked1Hidden to set.
	 */
	public void setContinuousChecked1Hidden(boolean continuousChecked1Hidden) {
		this.continuousChecked1Hidden = continuousChecked1Hidden;
	}
	/**
	 * @return Returns the continuousChecked2Hidden.
	 */
	public boolean isContinuousChecked2Hidden() {
		return continuousChecked2Hidden;
	}
	/**
	 * @param continuousChecked2Hidden The continuousChecked2Hidden to set.
	 */
	public void setContinuousChecked2Hidden(boolean continuousChecked2Hidden) {
		this.continuousChecked2Hidden = continuousChecked2Hidden;
	}
	/**
	 * @return Returns the continuousCheckedHidden.
	 */
	public boolean isContinuousCheckedHidden() {
		return continuousCheckedHidden;
	}
	/**
	 * @param continuousCheckedHidden The continuousCheckedHidden to set.
	 */
	public void setContinuousCheckedHidden(boolean continuousCheckedHidden) {
		this.continuousCheckedHidden = continuousCheckedHidden;
	}
}
