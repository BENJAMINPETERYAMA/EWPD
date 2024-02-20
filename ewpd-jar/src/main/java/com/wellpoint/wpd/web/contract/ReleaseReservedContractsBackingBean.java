/*
 * Created on Nov 5, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.contract;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.contract.request.ReleaseReservedContractRequest;
import com.wellpoint.wpd.common.contract.response.ReleaseReservedContractResponse;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.contractidpool.vo.ContractIDReservePoolRecord;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u13832
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReleaseReservedContractsBackingBean extends ContractBackingBean {
	
	
	private String startContractId;
	
	private String endContractId;
	
	private List contractIdList;
	
	private List reserveIdList;
	
	private List listToRelease;
	
	private List listTodisplay;
	
	private String contractId;
	
	private String status;
	
	private String  valid="";
	
	private boolean startContractIdInvalid = false;
	    
	private boolean endContractIdInvalid = false;
	
	private ArrayList validationMessages = new ArrayList();
	
	private String option = WebConstants.MSG_WARN_ID;
	
	public static final int RETRIEVE_RESERVED_CONTRACT = 1;
	
	public static final int RELEASE_RESERVED_CONTRACT = 2;
	
	private String  releaseExpiredDiv;
	
	private boolean  releaseExpiredDivInvalid=false;
	
	private boolean  contractIdInvalid=false;
	
	private boolean  contractIdFormatInvalid=false;	
	
	private String systemPoolList;

	private boolean optionOneFieldInvalid=false;

	private boolean optionTwoFieldInvalid=false;
	
	private String displayContractDetails;
	
	private String retrieveContractDetails;
	
	private boolean idList;
	
	private boolean expiredIdInvalid = false;
	private boolean endIdInvalid = false;
	private boolean startIdInvalid = false;
	/**
     * Default Constructor.
     */
    public ReleaseReservedContractsBackingBean() {
        super();
           
      
    }

	
	private void setValuesToRetrieveSystemPoolIdRequest(ReleaseReservedContractRequest request){
		request.setStartContractId(this.getStartContractId());
		request.setEndContractId(this.getEndContractId());
		request.setAction(RETRIEVE_RESERVED_CONTRACT);
	}
	
	/**
	 * Method for retrieving the reserved contracts
	 * @return String
	 */
	public String retrieveContractDetails(){
		if(!isRequiredReleaseIdFieldsValid()){ 
			this.valid = "";
	       	addAllMessagesToRequest(validationMessages);
	       	return "";
		}
		else{
			ReleaseReservedContractRequest releaseReservedContractRequest = new ReleaseReservedContractRequest();
			releaseReservedContractRequest = (ReleaseReservedContractRequest) this
				.getServiceRequest(ServiceManager.RELEASE__RESERVED_CONTRACT);
			releaseReservedContractRequest.setAction(1);
			releaseReservedContractRequest.setStartContractId(this.startContractId);
			releaseReservedContractRequest.setEndContractId(this.endContractId);
			ReleaseReservedContractResponse releaseReservedContractResponse = (ReleaseReservedContractResponse)executeService(releaseReservedContractRequest);
			if(null!=releaseReservedContractResponse){
				if(null!=releaseReservedContractResponse.getContractIdList()&&releaseReservedContractResponse.getContractIdList().size()>0){
					this.idList=true;	
			    	this.contractIdList=releaseReservedContractResponse.getContractIdList();
			    	
			    	List idList=this.contractIdList;
			    	List saveList=new ArrayList();
			    	List reservePoolList=new ArrayList();
			    	int size=idList.size();
			    	for(int i=0;i<size;i++){
			    		ContractIDReservePoolRecord contractIDReservePoolRecord=(ContractIDReservePoolRecord) idList.get(i);
		    			if(contractIDReservePoolRecord.getSystemPoolStatus().trim().equals("R")){
			    			reservePoolList.add(contractIDReservePoolRecord);
			    		}
			    	}
			    	int reservedContractSize=reservePoolList.size();
			    	for(int i=0;i<reservedContractSize;i++){
			    		ContractIDReservePoolRecord contractIDReservePoolRecord=(ContractIDReservePoolRecord) reservePoolList.get(i);
			    		if(null!=contractIDReservePoolRecord.getReservePoolStatus()){
				    		if(contractIDReservePoolRecord.getReservePoolStatus().trim().equals("N")){
				    			saveList.add(contractIDReservePoolRecord);
				    		}
			    		}
			    	}
			    	
			    	if(null!=reservePoolList && reservePoolList.size()>0){
			    		valid="valid";
				    	this.reserveIdList=reservePoolList;
				    	
				    	List list1=new ArrayList();
				    	
				    	if(null!=saveList){
					    	getSession().setAttribute("FREE_LIST",saveList);
					    	}
					    	if(null!=reservePoolList){
					    	getSession().setAttribute("RESERVEPOOL_LIST",reserveIdList);
					    	}
			    	}
			    	else{
			    		this.valid="";
			    		this.validationMessages.add(new InformationalMessage(
			                     WebConstants.NO_RESERVED_CONTRACTS));
			             addAllMessagesToRequest(validationMessages);
			    	}
			    	
				  }
				  else if(null==releaseReservedContractResponse.getContractIdList()){
				  	this.valid="";
				  	this.validationMessages.add(new InformationalMessage(
		                     WebConstants.NO_RESERVED_CONTRACTS_IN_RANGE));
		             addAllMessagesToRequest(validationMessages);
		             
		             
				  }
				  //clearbackingbean();
		    	}
		     }
		return "";
	}
	/**
	 * Method for displaying reserved contracts in the pop up
	 * @return Strings
	 */
	public String getDisplayContractDetails() {
		this.valid="";
		String lobString="";
		String beString="";
		String bgString="";
		
		List list1=new ArrayList();
    	this.reserveIdList=(List) getSession().getAttribute("RESERVEPOOL_LIST");
    	
    	if(null!=reserveIdList &&reserveIdList.size()>0){
	    	int size1 = reserveIdList.size();
			for(int i=0;i<size1;i++){
				ContractIDReservePoolRecord poolRecord1 =new ContractIDReservePoolRecord();
				ContractIDReservePoolRecord poolRecord = (ContractIDReservePoolRecord)reserveIdList.get(i);
				poolRecord1.setBusinessDomain(poolRecord.getBusinessDomain());
				poolRecord1.setComment(poolRecord.getComment());
				poolRecord1.setContractId(poolRecord.getContractId());
				poolRecord1.setMarketSegment(poolRecord.getMarketSegment());
				poolRecord1.setEffectiveDate(poolRecord.getEffectiveDate());
				poolRecord1.setExpiryDate(poolRecord.getExpiryDate());
				poolRecord1.setCreatedUser(poolRecord.getCreatedUser());
				poolRecord1.setPrivilege(poolRecord.getPrivilege());
				String status = poolRecord.getReservePoolStatus().trim();
				
				if(status.equals("U") || status.equals("C")){
					poolRecord1.setReservePoolStatus("InUse");
				}else if(status.equals("N")){
					poolRecord1.setReservePoolStatus("Free");
					
				}
				List domainList = poolRecord.getBusinessDomain();
				List listLOB = BusinessUtil.getLobList(domainList);
				List listBE = BusinessUtil.getbusinessEntityList(domainList);
				List listBG = BusinessUtil.getBusinessGroupList(domainList);
				
				Set lobSet = new HashSet(listLOB);
				Set beSet = new HashSet(listBE);
				Set bgSet = new HashSet(listBG);
				
				//Map beMap=new HashMap(listBE);
				
				List lob = new ArrayList(lobSet);
				List be = new ArrayList(beSet);
				List bg = new ArrayList(bgSet);
				
				StringBuffer lobForDisplay=new StringBuffer();
				StringBuffer beForDisplay=new StringBuffer();
				StringBuffer bgForDisplay=new StringBuffer();
				
				for(int j=0;j<lob.size();j++){
					lobString=(String) lob.get(j);
					if(j>0&&j<=lob.size()-1)
						lobForDisplay.append(", ");
						lobForDisplay.append(lobString);
				}
				
				for(int j=0;j<be.size();j++){
					
					beString=(String) be.get(j);
					if(j>0&&j<=be.size()-1)
						beForDisplay=beForDisplay.append(", ");
					    beForDisplay=beForDisplay.append(beString);
				}
				
				for(int k=0;k<bg.size();k++){
					bgString=(String) bg.get(k);
					if(k>0&&k<=bg.size()-1)
						bgForDisplay.append(", ");
						bgForDisplay.append(bgString);
				}
				
				poolRecord1.setLineOfBusiness(lobForDisplay.toString());
				poolRecord1.setBusinessEntity(beForDisplay.toString());
				poolRecord1.setBusinessGroup(bgForDisplay.toString());
				list1.add(poolRecord1);
			}
    	}
    	this.setListTodisplay(list1);
    		
	
	return "";
	}
	 /**
     * method for releasing the reserved contract which are not in use
     * @return String
     */
	public String releaseContractDetails() {
		this.valid="";
		List idList=new ArrayList();
		this.listToRelease=(List) getSession().getAttribute("FREE_LIST");
    	if(this.option.equals("0")){
	    	 List reservedContractsList=(List) getSession().getAttribute("RESERVEPOOL_LIST");
	         if(reservedContractsList.size()==0){
	         	this.validationMessages.add(new ErrorMessage(
	                     WebConstants.NO_RESERVED_CONTRACTS));
	             addAllMessagesToRequest(validationMessages);
	         }
	         else if(reservedContractsList.size()>0 && this.listToRelease.size()==0){
	         	this.validationMessages.add(new ErrorMessage(
	                     WebConstants.ALL_RESERVED_CONTRACTS_USE));
	             addAllMessagesToRequest(validationMessages);
	         }
	         else{
			    	this.listToRelease=(List) getSession().getAttribute("FREE_LIST");
			    	if(null!=this.listToRelease)
			    	idList=this.listToRelease;
			     }
	    }	
		      
    	else if(this.option.equals("1")){
    		if(!isRequiredReleaseIdFieldsValid()){
    			this.valid = "";
    	       	addAllMessagesToRequest(validationMessages);
    	       	return "releaseContract";
    		}
    		else{
		    		List expiredIdList=new ArrayList();
		    		List selectedIds=new ArrayList();
		    		List expiredIds=(List) getSession().getAttribute("EXPIREDCONTRACTS_LIST");
		    		if(null!=expiredIds){
			    		 Iterator iterator = expiredIds.iterator();
			             while (iterator.hasNext()) {
			             	ContractIDReservePoolRecord contractIDReservePoolRecord=(ContractIDReservePoolRecord) iterator.next();
			             	String status=contractIDReservePoolRecord.getReservePoolStatus().trim();
			             	if(status.equals("N")){
			             		expiredIdList.add(contractIDReservePoolRecord);
			             	}
			             }
			             
			             String[] releaseIds=this.releaseExpiredDiv.split("~");
			     		 if(releaseIds != null && releaseIds.length > 0) {
			         		
			         		for(int i=0; i<releaseIds.length; i++) {
			         			selectedIds.add(releaseIds[i]);
			         		}
			         	 }
			     		 
			     		 if(null!=expiredIdList && null!=selectedIds){
				     		 for(int i=0;i<expiredIdList.size();i++){
				     		 	ContractIDReservePoolRecord contractIDReservePoolRecord=(ContractIDReservePoolRecord) expiredIdList.get(i);
				     		 	for(int j=0;j<selectedIds.size();j++){
				     		 		String selectedId=(String) selectedIds.get(j);
				     		 		if(contractIDReservePoolRecord.getContractId().equals(selectedId)){
				     		 			idList.add(contractIDReservePoolRecord);
				     		 			break;
				     		 		}
				     		 		
				     		 	}
				     		 }
			     		 }
			             
			             /*if(null!=idList && idList.size()==0){
			             	this.validationMessages.add(new ErrorMessage(
				                     WebConstants.ALL_EXPIRED_CONTRACTS_INUSE));
				             addAllMessagesToRequest(validationMessages);
			             }*/
			    		
		    		}
		    	}
    	}
    	
    	   	if(null!=idList &&idList.size()>0){
		    	ReleaseReservedContractRequest releaseReservedContractRequest;
		        releaseReservedContractRequest = (ReleaseReservedContractRequest) this
				.getServiceRequest(ServiceManager.RELEASE__RESERVED_CONTRACT);
		        releaseReservedContractRequest.setAction(2);
		        releaseReservedContractRequest.setContractIdList(idList);
		        ReleaseReservedContractResponse releaseResponse = (ReleaseReservedContractResponse) executeService(
						releaseReservedContractRequest);
		        if(null != releaseResponse ){
			       	if(releaseResponse.isSuccess()){
			       		this.validationMessages=new ArrayList();
			       		InformationalMessage message = new InformationalMessage("free.contractid.release.success.info");
			       		this.validationMessages.add(message);
			       		addAllMessagesToRequest(validationMessages);
			       		clearbackingbean();
			       	}else{
			       		addAllMessagesToRequest(releaseResponse.getMessages());
			       	}
		        }
    	}
         return "releaseContract";
    }
    
    /**
     * method for clearing backing bean
     *
     */
    private void clearbackingbean(){
    	this.startContractId ="";
    	this.endContractId ="";
    	this.releaseExpiredDiv="";
    	this.option="0";
    }
    
    /**
     * Method for validating the release fields
     * @return boolean
     */
    private boolean isRequiredReleaseFieldsValid() {
    	
        validationMessages = new ArrayList();
        boolean requiredField = true;
        boolean optionOneFormatInvalid=false;
       
        if(this.option.equals("0")){
		        if (this.endContractId == null || "".equals(this.endContractId)){
		        	this.contractIdInvalid=true;
		           }
		        
		        if (this.startContractId == null || "".equals(this.startContractId)){
		        	this.contractIdInvalid=true;
		           }
		       
		        if (!"".equals(this.endContractId) && !endContractId.matches(WebConstants.REGEX_ID_FORMAT_STRING)) {
		        	this.contractIdFormatInvalid=true;
		        }
		        
		        if (!"".equals(this.startContractId) && !startContractId.matches(WebConstants.REGEX_ID_FORMAT_STRING)) {
		        	this.contractIdFormatInvalid=true;
		        }
        }
        if(this.option.equals("1")){
	        if(this.releaseExpiredDiv== null || "".equals(this.releaseExpiredDiv)){
	        	this.releaseExpiredDivInvalid=true;
	        }
        }
      
        if(this.option.equals("0")&&contractIdInvalid){
        	optionOneFieldInvalid=true;
        	requiredField=false;
        	
        }
        
        if(this.option.equals("1")&& releaseExpiredDivInvalid){
        	optionTwoFieldInvalid=true;
        	requiredField=false;
        }
        
        if(this.option.equals("0")&&contractIdFormatInvalid){
        	optionOneFormatInvalid=true;
        	requiredField=false;
        	
        }
        
        if(optionOneFormatInvalid){
        	this.validationMessages.add(new ErrorMessage(
                    WebConstants.ENTER_VALID_CONTRACT_ID));
            addAllMessagesToRequest(validationMessages);
        }
        
        if(optionOneFieldInvalid){
        	this.validationMessages.add(new ErrorMessage(
                    WebConstants.CONTRACT_ID_TO_RELEASE));
            addAllMessagesToRequest(validationMessages);
        }
        
        if(optionTwoFieldInvalid){
        	this.validationMessages.add(new ErrorMessage(
                    WebConstants.NO_EXPIRED_CONTRACT_IDS));
            addAllMessagesToRequest(validationMessages);
        }
      // clearbackingbean();
        return requiredField;
    }
    
    /*
     * Function to validate all the form fields 
     */
    private boolean isRequiredReleaseIdFieldsValid() {
        validationMessages = new ArrayList();
        boolean requiredField = false;
        boolean dateInvalid = false;
        boolean idInvalid = false;
        boolean countInvalid = false;
        boolean endIdSmaller = false;
       
        
        this.startIdInvalid = false;
        this.endIdInvalid = false;
        this.expiredIdInvalid = false;
        
       
        if(this.option.equals("0")){
        	if (this.startContractId==null || "".equals(this.startContractId.trim())){
        		startIdInvalid = true;
            	requiredField = true;
            }else if (!startContractId.matches(WebConstants.REGEX_ID_FORMAT_STRING)) {
            	idInvalid = true;
            	startIdInvalid = true;
            }
        	
        	if(this.endContractId==null || "".equals(this.endContractId.trim())){
        		endIdInvalid = true;
            	requiredField = true;
            }else if (!endContractId.matches(WebConstants.REGEX_ID_FORMAT_STRING)) {
            	idInvalid = true;
            	endIdInvalid = true;
          
            }
            if(this.startContractId.toUpperCase().compareTo(this.endContractId.toUpperCase())>0){
            	endIdSmaller = true;
            	endIdInvalid = true;
            }
        }
        if(this.option.equals("1")){
        	if (this.releaseExpiredDiv==null || "".equals(this.releaseExpiredDiv.trim())){
        		expiredIdInvalid = true;
            	requiredField = true;
            }
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

        return true;
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
	 * @return Returns the contractIdList.
	 */
	public List getContractIdList() {
		return contractIdList;
	}
	/**
	 * @param contractIdList The contractIdList to set.
	 */
	public void setContractIdList(List contractIdList) {
		this.contractIdList = contractIdList;
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
	 * @return Returns the valid.
	 */
	public String getValid() {
		return valid;
	}
	/**
	 * @param valid The valid to set.
	 */
	public void setValid(String valid) {
		this.valid = valid;
	}
	/**
	 * @return Returns the listToRelease.
	 */
	public List getListToRelease() {
		return listToRelease;
	}
	/**
	 * @param listToRelease The listToRelease to set.
	 */
	public void setListToRelease(List listToRelease) {
		this.listToRelease = listToRelease;
	}
	
	/**
	 * @return Returns the releaseExpiredDiv.
	 */
	public String getReleaseExpiredDiv() {
		return releaseExpiredDiv;
	}
	/**
	 * @param releaseExpiredDiv The releaseExpiredDiv to set.
	 */
	public void setReleaseExpiredDiv(String releaseExpiredDiv) {
		this.releaseExpiredDiv = releaseExpiredDiv;
	}
	
	
	/**
	 * @return Returns the contractIdInvalid.
	 */
	public boolean isContractIdInvalid() {
		return contractIdInvalid;
	}
	/**
	 * @param contractIdInvalid The contractIdInvalid to set.
	 */
	public void setContractIdInvalid(boolean contractIdInvalid) {
		this.contractIdInvalid = contractIdInvalid;
	}
	/**
	 * @return Returns the releaseExpiredDivInvalid.
	 */
	public boolean isReleaseExpiredDivInvalid() {
		return releaseExpiredDivInvalid;
	}
	/**
	 * @param releaseExpiredDivInvalid The releaseExpiredDivInvalid to set.
	 */
	public void setReleaseExpiredDivInvalid(boolean releaseExpiredDivInvalid) {
		this.releaseExpiredDivInvalid = releaseExpiredDivInvalid;
	}
	
	
	/**
	 * @param systemPoolList The systemPoolList to set.
	 */
	public void setSystemPoolList(String systemPoolList) {
		this.systemPoolList = systemPoolList;
	}
	
	/**
	 * @return Returns the reserveIdList.
	 */
	public List getReserveIdList() {
		return reserveIdList;
	}
	/**
	 * @param reserveIdList The reserveIdList to set.
	 */
	public void setReserveIdList(List reserveIdList) {
		this.reserveIdList = reserveIdList;
	}
	
	
	/**
	 * @param retrieveContractDetails The retrieveContractDetails to set.
	 */
	public void setRetrieveContractDetails(String retrieveContractDetails) {
		this.retrieveContractDetails = retrieveContractDetails;
	}
	
	
	/**
	 * @return Returns the listTodisplay.
	 */
	public List getListTodisplay() {
		return listTodisplay;
	}
	/**
	 * @param listTodisplay The listTodisplay to set.
	 */
	public void setListTodisplay(List listTodisplay) {
		this.listTodisplay = listTodisplay;
	}
	
	
	/**
	 * @return Returns the idList.
	 */
	public boolean isIdList() {
		return idList;
	}
	/**
	 * @param idList The idList to set.
	 */
	public void setIdList(boolean idList) {
		this.idList = idList;
	}
	
	
	/**
	 * @return Returns the contractIdFormatInvalid.
	 */
	public boolean isContractIdFormatInvalid() {
		return contractIdFormatInvalid;
	}
	/**
	 * @param contractIdFormatInvalid The contractIdFormatInvalid to set.
	 */
	public void setContractIdFormatInvalid(boolean contractIdFormatInvalid) {
		this.contractIdFormatInvalid = contractIdFormatInvalid;
	}
	
	
	/**
	 * @param displayContractDetails The displayContractDetails to set.
	 */
	public void setDisplayContractDetails(String displayContractDetails) {
		this.displayContractDetails = displayContractDetails;
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
	 * @return Returns the expiredIdInvalid.
	 */
	public boolean isExpiredIdInvalid() {
		return expiredIdInvalid;
	}
	/**
	 * @param expiredIdInvalid The expiredIdInvalid to set.
	 */
	public void setExpiredIdInvalid(boolean expiredIdInvalid) {
		this.expiredIdInvalid = expiredIdInvalid;
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
}
