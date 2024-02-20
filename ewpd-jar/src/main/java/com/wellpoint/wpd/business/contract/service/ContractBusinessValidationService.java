/*
 * ContractBusinessValidationService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.catalog.adapter.CatalogAdapterManager;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.contract.adapter.ContractAdapterManager;
import com.wellpoint.wpd.business.contract.builder.ContractBusinessObjectBuilder;
import com.wellpoint.wpd.business.contract.locatecriteria.DateSegmentLocateCriteria;
import com.wellpoint.wpd.business.contract.locateresult.ContractLocateResult;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.WPDBusinessValidationService;
import com.wellpoint.wpd.business.item.adapter.ItemAdapterManager;
import com.wellpoint.wpd.business.item.locatecriteria.ItemLocateCriteria;
import com.wellpoint.wpd.business.product.builder.ProductBusinessObjectBuilder;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.accumulator.bo.AccumulatorImpl;
import com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.contract.bo.AccumulatorValidationBO;
import com.wellpoint.wpd.common.contract.bo.Comment;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.contract.bo.ContractAssnQuestionnaireBO;
import com.wellpoint.wpd.common.contract.bo.ContractPricingInfo;
import com.wellpoint.wpd.common.contract.bo.ContractStatusBo;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.contract.bo.VendorInfoBO;
import com.wellpoint.wpd.common.contract.request.ContractRequest;
import com.wellpoint.wpd.common.contract.request.CreateDateSegmentRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBasicInfoRequest;
import com.wellpoint.wpd.common.contract.request.SavePricingInfoRequest;
import com.wellpoint.wpd.common.contract.response.CreateDateSegmentResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBasicInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveDateSegmentCommentResponse;
import com.wellpoint.wpd.common.contract.response.SavePricingInfoResponse;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.item.vo.ItemLocateCriteriaVO;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.web.contract.ContractKeyObject;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;
//sscr 17571
import com.wellpoint.wpd.common.item.bo.ItemBO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */


public class ContractBusinessValidationService extends WPDBusinessValidationService {

	
	public WPDResponse execute(CreateDateSegmentRequest createDateSegmentRequest)
    throws ServiceException { 
	try {
		CreateDateSegmentResponse response = (CreateDateSegmentResponse) WPDResponseFactory
		.getResponse(WPDResponseFactory.DATE_SEGMENTS_CREATE_RESPONSE);
		List messageList = new ArrayList();
	
	    boolean valid = true;
	    ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
	    
		DateSegmentLocateCriteria locateCriteria = new DateSegmentLocateCriteria();
		LocateResults locateResults = null;
	
		locateCriteria.setContractSysId(createDateSegmentRequest.getContractVO().getContractSysId());
		//gets the ds's
		locateResults = builder.locate(locateCriteria, createDateSegmentRequest.getUser());
		
		if(null != locateResults && locateResults.getLocateResults().size() > 0 ){
			for(int i = 0; i< locateResults.getLocateResults().size(); i++){
				
				ContractLocateResult locResult = (ContractLocateResult)locateResults.getLocateResults().get(i);
				if(locResult.getTestIndicator().equals("Y")){
					if(locResult.getProductSysId() == 0){
						valid = false;
						messageList.add(new ErrorMessage(BusinessConstants.MSG_CONTRACT_PRODUCT_VALIDATION));
					}
				}
				
			}
			
			
		}
		
	
		    response.setValid(valid);
		    response.setMessages(messageList);
		    return response;
		} catch (Exception ex) {
		    throw new ServiceException("Exception occured while BOM call",
		            null, ex);
		}
}
	
	
	/**
     * This method validates the contract 
     * @param SaveContractBasicInfoRequest
     * @return
     * @throws ServiceException
     */
	public WPDResponse execute(SaveContractBasicInfoRequest request)
            throws ServiceException {
        try {
            SaveContractBasicInfoResponse response = (SaveContractBasicInfoResponse) WPDResponseFactory
                    .getResponse(WPDResponseFactory.SAVE_CONTRACT_BASIC_INFO_RESPONSE);
            int action = request.getAction();
            ContractVO contractVO = request.getContractVO();
            boolean valid = true;
            boolean accumValid = true;
            Contract contract = new Contract();
            List messageList = new ArrayList();
            ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
            switch  (action){
            	case SaveContractBasicInfoRequest.CREATE_CONTRACT:
            		  ContractAdapterManager adapterManager = new ContractAdapterManager();
	            		setBasicInfoToContract(contractVO,contract);
            			if((null != contractVO.getEffectiveDate())){
	            			Date minEffectiveDate = new Date(WebConstants.DEFAULT_EFF_DATE);
	            			SimpleDateFormat fmt = new SimpleDateFormat(WebConstants.DATE_FORMAT_STRING);
	            			Date maxEffectiveDate = new Date (WebConstants.DEFAULT_EXP_DATE);
	            			int lesserDate  = minEffectiveDate.compareTo(contractVO.getEffectiveDate());
	            			int greaterDate =  maxEffectiveDate.compareTo(contractVO.getEffectiveDate());
	            			if(lesserDate == 1){
	            				valid = false;
	            				messageList.add(new ErrorMessage(BusinessConstants.EFFECTIVE_DATE_LESS));
	            				break;
	            			}
	            			else if(greaterDate < 1){
	            				valid = false;
	            				messageList.add(new ErrorMessage(BusinessConstants.EFFECTIVE_DATE_GREATER));
	            				break;
	            			}
            			}
	            		if(null == contract.getContractId() ||  "".equals(contract.getContractId())){
	            			valid = true;
	            		}
	            		else{
/*	            			String contractId = contract.getContractId();
	            			//ReserveContractId reservedContractIdInfo = new ReserveContractId();
	            			//reservedContractIdInfo.setContractId(contractId);
	            			ContractIDPoolAdapterManager contractIDPoolAdapterManager = new ContractIDPoolAdapterManager();
	            			contractIDPoolAdapterManager.
	            	        adapterManager.retrieveReserveContract(reservedContractIdInfo);
	            	        if("N".equals(reservedContractIdInfo.getStatus())){
	            	        	valid = false;
	            	        	messageList.add(new ErrorMessage(BusinessConstants.RESERVE_ID_IN_USE));
	            	        	break;
	            	        }
	            	        else{
	            	        	valid = true;
	            	        }
*/	            			
	            		}
	            		if (BusinessConstants.MSG_CUSTOM_CONTRACT_TYPE.equals(contract.getContractType())){
	            			 List baseContracts = null;
	            			 	valid = false;
	            		        try {
	            					/*START CARS*/
	            		        	baseContracts = adapterManager.getBaseContracts(contractVO.getLobList(),contractVO.getBeList(),contractVO.getBgList(),contractVO.getMbuList());
	            					/*END CARS*/
	            		        } catch(SevereException ex) {
	            		            throw new ServiceException("Adapter exception",null,ex);
	            		        }
	            		        if(null!=baseContracts && 0 != baseContracts.size()){
	            		        	for(int i=0;i<baseContracts.size();i++){
	            		        		Contract itr =(Contract) baseContracts.get(i);
	            		        		if(itr.getContractSysId() == contractVO.getBaseContractSysId())
	            		        			valid= true;
	            		        	}
	            		        	
	            		        }
	            		        else{
	            		        	valid = false;
	            		        }
	            		        if(!(valid)){
	            		        	messageList.add(new ErrorMessage(BusinessConstants.DOMAIN_MISMATCH));
	            		        }
	            		        else{
	            		        	Map params = new HashMap();
	            		        	params.put(BusinessConstants.ACTION,BusinessConstants.RETREIVE_SPECIFIC);
	            					params.put(BusinessConstants.DATESEGMENT_ID,new Integer(contractVO.getBaseDateSegmentSysId()));
	            					contract =(Contract)builder.retrieve(contract,params);
	            					DateSegment dateSegment= (DateSegment)contract.getDateSegmentList().get(0); 
	            					ProductBusinessObjectBuilder productBuilder = new ProductBusinessObjectBuilder();
	            			        ProductBO product = new ProductBO();
	            			        product.setProductKey(dateSegment.getProductId());
	            			        try {
	            			            product =(ProductBO)productBuilder.retrieve(product);
	            			            if(null!=product){
	            			            	valid = isEffectivePeriodValid(contractVO,product);
	            			            	if(!valid){
	            			            		messageList.add(new ErrorMessage(BusinessConstants.MSG_CNTRCT_EFCTV_PERIOD_INVALID));
	            			            	}
	            			            }
	            			        	//retrieves the product structure
	            			       
	            			        } catch(WPDException e) {
	            			            throw new ServiceException(null,null,e);
	            			        }
	            		        }
	            		} else if (BusinessConstants.STANDARD_TYPE.equals(contract
						.getContractType())
						&& BusinessConstants.VALUE_ZERO != contractVO
								.getBaseDateSegmentSysId()) {
					List baseContracts = null;
					valid = false;
					try {
						/*START CARS*/
						baseContracts = adapterManager.getBaseContracts(
								contractVO.getLobList(),
								contractVO.getBeList(), contractVO.getBgList(),contractVO.getMbuList());
						/*END CARS*/
					} catch (SevereException ex) {
						throw new ServiceException("Adapter exception", null,
								ex);
					}
					if (null != baseContracts && 0 != baseContracts.size()) {
						for (int i = 0; i < baseContracts.size(); i++) {
							Contract itr = (Contract) baseContracts.get(i);
							if (itr.getContractSysId() == contractVO
									.getBaseContractSysId())
								valid = true;
						}
					} else {
						valid = false;
					}
					if (!(valid)) {
						messageList.add(new ErrorMessage(
								BusinessConstants.DOMAIN_MISMATCH));
					} else {
						Map params = new HashMap();
						params.put(BusinessConstants.ACTION,
								BusinessConstants.RETREIVE_SPECIFIC);
						params.put(BusinessConstants.DATESEGMENT_ID,
								new Integer(contractVO
										.getBaseDateSegmentSysId()));
						contract = (Contract) builder
								.retrieve(contract, params);
						DateSegment dateSegment = (DateSegment) contract
								.getDateSegmentList().get(0);
						ProductBusinessObjectBuilder productBuilder = new ProductBusinessObjectBuilder(); 
						ProductBO product = new ProductBO();
						product.setProductKey(dateSegment.getProductId());
						try {
							product = (ProductBO) productBuilder
									.retrieve(product);
							if (null != product) {
								valid = isEffectivePeriodValid(contractVO,
										product);
								if (!valid) {
									messageList
											.add(new ErrorMessage(
													BusinessConstants.MSG_CNTRCT_EFCTV_PERIOD_INVALID));
								}
							}
							//retrieves the product structure

						} catch (WPDException e) {
							throw new ServiceException(null, null, e);
						}
					}
				}
	            		
	            	break;
            	case (SaveContractBasicInfoRequest.CHECKIN_CONTRACT) :
            	case (SaveContractBasicInfoRequest.UPDATE_CHECKIN_CONTRACT) :
	            		setBasicInfoToContract(contractVO,contract);
            	
            	Map globalMsgMap = new HashMap();
            	globalMsgMap = validateAccumulators(contract,request,messageList,response,globalMsgMap);
            	messageList = updateMsgList(messageList,globalMsgMap);
            			valid = isContractValid(contract,
            					request, messageList, response);

            			ErrorMessage messageUniqueRef = isUnique(contract);
            	        if(null!=messageUniqueRef){
            	            valid = false;
            	           messageList.add(messageUniqueRef);
            	        }            	       
            	        if (messageList.size() > 0 && isErrorMessageInList(messageList)){
            	              valid = false;
            	        }
	            		if(valid && !(request.isChechIn()))
	                        	messageList.add(new InformationalMessage(BusinessConstants.CHECKIN_VALID));
// End of CR Refernce Validation	            		
	            		break;	    	
            	
            	case (SaveContractBasicInfoRequest.SEND_TO_TEST) :
            		
	            		setBasicInfoToContract(contractVO,contract);
// Start of CR Check In Validation            	
	            		//valid = isContractValid(contract,request,messageList);
            			valid = isContractValid(contract,
            					request, messageList, response);
// End of CR Check In Validation	            		
	            		break;	
            	case (SaveContractBasicInfoRequest.COPY_CONTRACT ) :
	            		if((!(BusinessConstants.MSG_CUSTOM_CONTRACT_TYPE.equals(contractVO.getContractType())))|| ( contractVO.getBaseDateSegmentSysId() == request.getBaseDateIdForCopy())){
	            		setBasicInfoToContract(contractVO,contract);
	            		if(request.getProductSysId()!=0)
	            		valid = isProductValid(contract,request,messageList);
	            		}
	            		break;
            	case (SaveContractBasicInfoRequest.COPY_HEADINGS_CONTRACT ) :
            		if((!(BusinessConstants.MSG_CUSTOM_CONTRACT_TYPE.equals(contractVO.getContractType())))|| ( contractVO.getBaseDateSegmentSysId() == request.getBaseDateIdForCopy())){
            		setBasicInfoToContract(contractVO,contract);
            		if(request.getProductSysId()!=0)
            		valid = isProductValid(contract,request,messageList);
            		}
            		break;   
            	case SaveContractBasicInfoRequest.APPROVE_CONTRACT:
	            		DateSegmentLocateCriteria locateCriteriaNotes = new DateSegmentLocateCriteria();
						LocateResults locateResultsNotes = null; 
						locateCriteriaNotes.setContractSysId(contractVO.getContractSysId());
						locateResultsNotes = builder.locate(locateCriteriaNotes, request.getUser());
						int dateSegmentCount = locateResultsNotes.getLocateResults().size();
						int	testDateSegmentCount = 0;
						if (dateSegmentCount >= 0) {
							for (int i = 0; i < dateSegmentCount; i++) {
								ContractLocateResult contractLocateResult = (ContractLocateResult) locateResultsNotes
										.getLocateResults().get(i);
								if ("Y".equals(contractLocateResult.getTestIndicator())) {
									testDateSegmentCount++;
								}
							}
						}
						if(testDateSegmentCount==dateSegmentCount){
							valid= false;
							messageList.add(new ErrorMessage(BusinessConstants.NO_REGULAR_DATESEGMENTS));
						}
						break;
            			    	
            				
            }
            response.setValid(valid);
            response.setMessages(messageList);
            return response;
        } catch (Exception ex) {
            throw new ServiceException("Exception occured in Validation Service",
                    null, ex);
        }

    }
	
	 public boolean isErrorMessageInList(List messages) {
	        boolean retValue = false;
	        if (messages != null) {
	            int count = messages.size();
	            for (int j = 0; j < count; j++) {
	                if (messages.get(j) instanceof ErrorMessage) {
	                    retValue = true;
	                    break;
	                }
	            }
	        }
	        return retValue;
	    }
	 
	public List updateMsgList(List msgList, Map globalMsgMap) {
		if (null != globalMsgMap) {
			if (msgList == null) {
				msgList = new ArrayList();
			}
			ErrorMessage errorMessage = null;
			InformationalMessage infoMessage = null;
			Set keySet = globalMsgMap.keySet();
			for (Iterator itr = keySet.iterator(); itr.hasNext();) {
				String key = (String) itr.next();
				String value = (String) globalMsgMap.get(key);
				if (value.endsWith("<BR>")) {
					value = value.substring(0, value.lastIndexOf("<BR>"));
				} else if (value.endsWith(",")) {
					value = value.substring(0, value.lastIndexOf(","));
				}
				if (key
						.equalsIgnoreCase(BusinessConstants.NOT_AVAILABLE_IN_CLAIMSYSTEM)) {
					infoMessage = new InformationalMessage(key);
					infoMessage.setParameters(new String[] { value });
					msgList.add(infoMessage);
				} else {
					errorMessage = new ErrorMessage(key);
					errorMessage.setParameters(new String[] { value });
					msgList.add(errorMessage);
				}

			}
		}
		return msgList;
	}
    /**
     * This method validates the product attached to the contract 
     * @param SaveContractBasicInfoRequest, contract, List 
     * @return
     * @throws SevereException
     */
    private boolean isProductValid(Contract contract,SaveContractBasicInfoRequest request, List messages) throws SevereException {
        boolean valid = true;
        
        ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
        ProductBO product = new ProductBO();
        product.setProductKey(request.getProductSysId());
        if(BusinessConstants.MSG_MANDATE_CONTRACT_TYPE.equalsIgnoreCase(request.getContractVO().getContractType()))
            product.setProductKey(request.getContractVO().getProductSysId());
        
        try {
            product =(ProductBO)builder.retrieve(product);
            if(null!=product)
            product.setProductKey(product.getParentProductKey());
            else{
                messages.add(new ErrorMessage(BusinessConstants.MSG_CONTRACT_COPY_PRODUCT_INVALID));
                return false;
            }
               
        	//retrieves the product structure
       
        } catch(WPDException e) {
            throw new ServiceException(null,null,e);
        }
       //checks domain valid
        if(!isDomainValid(contract,product)){
            valid = false;
            messages.add(new ErrorMessage(BusinessConstants.MSG_CONTRACT_COPY_DOMAIN_INVALID));
        }
        if(!isEffectivePeriodValid(request.getContractVO(),product)){
            valid = false;
            messages.add(new ErrorMessage(BusinessConstants.MSG_CNTRCT_EFCTV_PERIOD_INVALID));
        } 
        if(BusinessConstants.MSG_MANDATE_CONTRACT_TYPE.equalsIgnoreCase(request.getContractVO().getContractType())){
        	if(!(BusinessConstants.MSG_MANDATE_CONTRACT_TYPE.equalsIgnoreCase(product.getProductType()))){
        		valid=false;
        		messages.add(new ErrorMessage(BusinessConstants.COPY_FROM_OTHER_TO_MANDATE));
        	}
        }
        else{
        	if(BusinessConstants.MSG_MANDATE_CONTRACT_TYPE.equalsIgnoreCase(product.getProductType())){
        		valid=false;
        		messages.add(new ErrorMessage(BusinessConstants.COPY_FROM_MANDATE_TO_OTHER));
        	}
        }
        return valid;
    } 
    /**
     * This method validates if the effective date and expiry date of  the contract matches with the product
     * @param ContractVO, ProductBO 
     * @return boolean
     * 
     */
       private boolean isEffectivePeriodValid(ContractVO contractVO,ProductBO product) {
    	
        Date contractEffDate = contractVO.getEffectiveDate();
        Date contractExpDate = contractVO.getExpiryDate();
        Date prodEffDate = product.getEffectiveDate();
        Date prodExpDate = product.getExpiryDate();
        boolean valid = true;
        if(null!=contractEffDate){
        if( prodEffDate.after(contractEffDate) || contractExpDate.after(prodExpDate)){
            valid = false;
        }
        }
        return valid;
    } 
       /**
        * This method validates  if the domain values of the contract matches with the product
        * @param Contract, ProductBO 
        * @return boolean
        * 
        */
    private boolean isDomainValid(Contract contract,ProductBO product) throws SevereException{
        List contractDomainItems = null;
        List prodDomainItems = null;
        
        Iterator iter1 = null;
        Iterator iter2 = null;
        String domainItem1 = null;
        String domainItem2 = null;
        boolean found = false;
        boolean valid = true;
        
        contractDomainItems = BusinessUtil.getLobList(contract.getBusinessDomains());
        prodDomainItems = DomainUtil.getLineOfBusiness("product",product.getProductKey());
        
        lobCheck:
	        for(iter1 = contractDomainItems.iterator();iter1.hasNext();){
	            domainItem1 = (String)iter1.next();
	            found = false;
	            for(iter2 = prodDomainItems.iterator(); iter2.hasNext();){
	                domainItem2 = ((DomainItem)iter2.next()).getItemId();
	                if(BusinessConstants.UNIVERSAL_DOMAIN.equals(domainItem2))
	                    break lobCheck;
	                if(domainItem1.equals(domainItem2)){
	                    found = true;
	                    break;
	                }
	            }
	            if(!found) {
	                valid = false;
	                break;
	            }
	        }
        

	        if(valid) {
	        	contractDomainItems = BusinessUtil.getbusinessEntityList(contract.getBusinessDomains());
	        	prodDomainItems = DomainUtil.getBusinessEntity("product",product.getProductKey());
		        beCheck:
			        for(iter1 = contractDomainItems.iterator();iter1.hasNext();){
			            domainItem1 = (String)iter1.next();
			            found = false;
			            for(iter2 = prodDomainItems.iterator(); iter2.hasNext();){
			                domainItem2 = ((DomainItem)iter2.next()).getItemId();
			                if(BusinessConstants.UNIVERSAL_DOMAIN.equals(domainItem2))
			                    break beCheck;
			                if(domainItem1.equals(domainItem2)){
			                    found = true;
			                    break;
			                }
			            }
			            if(!found) {
			                valid = false;
			                break;
			            }
			        }
	        }
        

	        if(valid) {
	        	contractDomainItems = BusinessUtil.getBusinessGroupList(contract.getBusinessDomains());
	            prodDomainItems = DomainUtil.getBusinessGroup("product",product.getProductKey());
			    bgCheck:
			        for(iter1 = contractDomainItems.iterator();iter1.hasNext();){
			            domainItem1 = (String)iter1.next();
			            found = false;
			            for(iter2 = prodDomainItems.iterator(); iter2.hasNext();){
			                domainItem2 = ((DomainItem)iter2.next()).getItemId();
			                if(BusinessConstants.UNIVERSAL_DOMAIN.equals(domainItem2))
			                    break bgCheck;
			                if(domainItem1.equals(domainItem2)){
			                    found = true;
			                    break;
			                }
			            }
			            if(!found) {
			                valid = false;
			                break;
			            }
			        }
	        }
        
        return valid;
    }
  
    /**
     * This method validates  if the contract contains all the madatory fields
     * @param Contract, SaveContractBasicInfoRequest, List 
     * @return boolean
     * @throws ServiceException
     */
// Start of CR Check In Validation    
    //private boolean isContractValid(Contract contract,SaveContractBasicInfoRequest request,List messageList) throws ServiceException{
    private boolean isContractValid(Contract contract,
    		SaveContractBasicInfoRequest request, 
			List messageList, SaveContractBasicInfoResponse response) 
    		throws ServiceException{
// End of CR Check In Validation    	
    	Map params=new HashMap();
		boolean valid = true;
		boolean returnvalid = true;
		//sscr 17571
		Map carvedMap=new HashMap();
		//sscr 17571-end
		ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
		ContractAdapterManager adapterManager = new ContractAdapterManager();
		try{
			contract =(Contract)builder.retrieve(contract);
			DateSegment dateSegment = new DateSegment();
			List dateSegmentList =null;
			if("BUILDING".equals(contract.getStatus()) || "CHECKED_OUT".equals(contract.getStatus())){
				dateSegmentList= builder.retrieveCheckInDateSegments(contract.getContractSysId());
			}else{
				dateSegmentList= builder.retrieveValidStatusDatesegments(contract.getContractSysId(),null);
			}
			List deletedRulesListForContract = new ArrayList();
			List unCodedRulesListForContract = new ArrayList();
			boolean ifValidRule = true;
			if(dateSegmentList != null){
				Iterator itr = dateSegmentList.iterator();
				while(itr.hasNext()){
					valid = true;
					DateSegment eachDateSegment = (DateSegment)itr.next();
					params.put(BusinessConstants.ACTION,BusinessConstants.RETREIVE_SPECIFIC);
					params.put(BusinessConstants.DATESEGMENT_ID,new Integer(eachDateSegment.getDateSegmentSysId()));
					contract =(Contract)builder.retrieve(contract,params);
					dateSegment= (DateSegment)contract.getDateSegmentList().get(0);  
					if(null == contract.getContractType() || "".equals(contract.getContractType())){
	    				valid = false;
	    			}
					else if( null == dateSegment.getEffectiveDate()){
	    				valid = false;
	    			}
	    			else if ((!(BusinessConstants.MSG_SHELL_CONTRACT_TYPE).equals(contract.getContractType())) && null == dateSegment.getExpiryDate()){
	    				valid= false;
	    			}
	    			else if((!(BusinessConstants.MSG_SHELL_CONTRACT_TYPE).equals(contract.getContractType())) && (null ==dateSegment.getGroupSize()|| "".equals(dateSegment.getGroupSize()))){
	    				valid= false;
	    			}
	    			else if((!(BusinessConstants.MSG_SHELL_CONTRACT_TYPE).equals(contract.getContractType())) &&(null == dateSegment.getProductCode() || "".equals (dateSegment.getProductCode()))){
	    				valid= false;
	    			}
	    			else if((!(BusinessConstants.MSG_SHELL_CONTRACT_TYPE).equals(contract.getContractType())) &&(null == dateSegment.getProductCodeDesc() || "".equals (dateSegment.getProductCodeDesc().trim()))){
	    				valid= false;
	    			}
	    			else if((!(BusinessConstants.MSG_SHELL_CONTRACT_TYPE).equals(contract.getContractType())) && (null == dateSegment.getBenefitPlan() || "".equals (dateSegment.getBenefitPlan()))){
	    				valid = false;
	    			}
	    			else if((!(BusinessConstants.MSG_SHELL_CONTRACT_TYPE).equals(contract.getContractType())) && 0 == dateSegment.getProductId()){
	    				valid = false;
	    			}
	    			else if((BusinessConstants.PRODUCT_FAMILY_HMO).equals(dateSegment.getProductFamily())||(BusinessConstants.PRODUCT_FAMILY_POS).equals(dateSegment.getProductFamily())){
	    					if(null == dateSegment.getCorporatePlanCode() || "".equals(dateSegment.getCorporatePlanCode())){
	    						ErrorMessage errorMessage = new ErrorMessage(WebConstants.CORPORATE_PLAN_CODE_REQUIRED_CHECKIN);
	    						errorMessage.setParameters(new String[] {  WPDStringUtil.getStringDate(dateSegment.getEffectiveDate()) });
	    						messageList.add(errorMessage);
	    						valid = false;
	    					}
	    			}
					
					ContractStatusBo contractStatus = (null != request.getContractVO()) ? request.getContractVO().getContractStatusBo():null;
					if(null == contractStatus){
						contractStatus = dateSegment.getContractStatusBo();
					}
					if(null == contractStatus || !WPDStringUtil.hasText(contractStatus.getStatusCode())){
						returnvalid = false;
	    				ErrorMessage errorMessage = new ErrorMessage
							(BusinessConstants.CONTRACT_STATUS_VALIDATION_ERROR);
			       		errorMessage.setLink(BusinessConstants.
			       				CONTRACT_STATUS_IS_NOT_AVAILABLE);
			       		messageList.add(errorMessage);
					}else if(!WebConstants.CNTRT_STATUS_ACTIVE.equals(contractStatus.getStatusCode())){
						if(!WPDStringUtil.hasText(contractStatus.getReasonCode())){
							returnvalid = false;
		    				ErrorMessage errorMessage = new ErrorMessage
								(BusinessConstants.CONTRACT_STATUS_VALIDATION_ERROR);
				       		errorMessage.setLink(BusinessConstants.
				       				CONTRACT_REASON_IS_NOT_AVAILABLE);
						}
						
						if(null == contractStatus.getStatusDate()){
							returnvalid = false;
		    				ErrorMessage errorMessage = new ErrorMessage
								(BusinessConstants.CONTRACT_STATUS_VALIDATION_ERROR);
				       		errorMessage.setLink(BusinessConstants.
				       				CONTRACT_STATUSDATE_IS_NOT_AVAILABLE);
				       		messageList.add(errorMessage);
						}
					}
					
	    			if(!valid ){
	    				ErrorMessage errorMessage =new ErrorMessage(BusinessConstants.CHECKIN_VALID_FAIL_DATE);
	    				errorMessage.setParameters(new String[] {  WPDStringUtil.getStringDate(dateSegment.getEffectiveDate()) });
	    				messageList.add(errorMessage);
	    				returnvalid = false;
	    			}
	    			
	    			if(!(BusinessConstants.MSG_SHELL_CONTRACT_TYPE).equals(contract.getContractType())){
	        			params.put(BusinessConstants.ACTION,"retrieveContractPricingInfo");
	        			params.put(BusinessConstants.DATESEGMENT_ID,new Integer(dateSegment.getDateSegmentSysId()));
	        			contract = (Contract)builder.retrieve(contract,params);
	        			DateSegment dateSegmentPricing=((DateSegment)contract.getDateSegmentList().get(0));
	        			if(null== dateSegmentPricing.getPricingInfoList() || 0 == dateSegmentPricing.getPricingInfoList().size()){
	        				valid = false;
	        				ErrorMessage errorMessage =new ErrorMessage(BusinessConstants.CHECKIN_VALID_FAIL_PRICING);
	        				errorMessage.setParameters(new String[] { WPDStringUtil.getStringDate(dateSegment.getEffectiveDate()) });
	        				messageList.add(errorMessage);
	        				returnvalid = false;
	        			}
	    			}

	    			List deletedRulesList = isValidRule(
	    					(eachDateSegment.getDateSegmentSysId()));
	    			deletedRulesListForContract.addAll(deletedRulesList);
	    			List unCodedRulesList = isValidContractRule(
	    					(eachDateSegment.getDateSegmentSysId()));
	    			unCodedRulesListForContract.addAll(unCodedRulesList);
	    			
	    			//GET STR & ENDDATE
	    			//SSCR 17571 -Start
	    			if(request.isCarvConfirm() && request.isChechIn()){	   // Modified as part of Tab implementation  				
	    				ContractBusinessObjectBuilder builderCarv = new ContractBusinessObjectBuilder();
	    				String carvedoutDate = "";
	    				List<DateSegment> dateRangeforCarved = builderCarv.retrieveDate(eachDateSegment.getDateSegmentSysId());
		    			for (int m = 0; m < dateRangeforCarved.size(); m++) {	
		    				DateSegment daterange=dateRangeforCarved.get(m);
		    				carvedoutDate = daterange.getEffectiveDate() + "-"+ daterange.getExpiryDate();
		    			}
		    			List<VendorInfoBO> VendorInfoList=checkcarvedoutQuestionInfo(eachDateSegment.getDateSegmentSysId());
		    			if(null!=VendorInfoList && !VendorInfoList.isEmpty()){	    				
		    				carvedMap.put(carvedoutDate, VendorInfoList);
		    			}	    			
	    			}//SSCR 17571 -End
	    			if(request.getAction() == 
	    				SaveContractBasicInfoRequest.CHECKIN_CONTRACT || request.getAction()== SaveContractBasicInfoRequest.UPDATE_CHECKIN_CONTRACT){
		    			if( (null != deletedRulesList 
		    					&& !deletedRulesList.isEmpty()) 
		    				|| (null != unCodedRulesList 
		    							&& !unCodedRulesList.isEmpty()) ){
		    				ifValidRule = false;
		    				
						}
		    			//sscr 17571
						if ( (null != carvedMap )&& (!carvedMap.isEmpty()) ) {
							response.setCarvedoutMap(carvedMap);  // tab implementation 
							
						}
	    			}else if(request.getAction() == 
	    				SaveContractBasicInfoRequest.SEND_TO_TEST){
	    				if(null != deletedRulesList 
		    					&& !deletedRulesList.isEmpty()){
		    				valid = false;
		    				ErrorMessage errorMessage = new ErrorMessage
								(BusinessConstants.MSG_PRDCT_RULE_VALIDATE);
		    				messageList.add(errorMessage);
		    				returnvalid = false;
		    			}
		    			if(null != unCodedRulesList 
    							&& !unCodedRulesList.isEmpty()){
		    				valid = false;
		    				ErrorMessage errorMessage =new ErrorMessage
									(BusinessConstants.
										MSG_CONTRACT_VALIDATE_RULE_CHECKIN);
		    				errorMessage.setParameters(new String[] {  
		    						WPDStringUtil.getStringDate
										(dateSegment.getEffectiveDate()) });
		    				messageList.add(errorMessage);
		    				returnvalid = false;
		    			}
	    			}	    			
// End of CR Check In Validation	
	    			//BY CY Validation
	    			List BYCYAnswerList =  builder.getBYCYAnswerList(dateSegment.getDateSegmentSysId());
	    			if(null != BYCYAnswerList && !BYCYAnswerList.isEmpty()){
	    				ContractAssnQuestionnaireBO questionnaireBO = (ContractAssnQuestionnaireBO)BYCYAnswerList.get(0);
	    				String BYCYAnswer = questionnaireBO.getSelectedAnswerDesc();
	    				if(BYCYAnswer.toUpperCase().equals("NOT ANSWERED")){
	        				valid = false;
	        				ErrorMessage errorMessage =new ErrorMessage(BusinessConstants.BY_CY_ANSWER);
	        				errorMessage.setParameters(new String[] { WPDStringUtil.getStringDate(dateSegment.getEffectiveDate()) });
	        				messageList.add(errorMessage);
	        				returnvalid = false;	    					
	    				}
	    			}
	    			
				}
				
				if(!(ifValidRule)){
    				
    				returnvalid = false;
    				ErrorMessage errorMessage = new ErrorMessage
						(BusinessConstants.PRODUCT_RULE_VALIDATION_ERROR);
		       		errorMessage.setLink(BusinessConstants.
		       				CONTRACT_RULE_VALIDATION_LINK);
		       		messageList.add(errorMessage);
    				response.setDeletedRulesList(deletedRulesListForContract);
    				response.setUnCodedRulesList(unCodedRulesListForContract);
    			}
				
			}else{
				returnvalid = false;
			}
			
			
			
		}catch(SevereException ex){
            List logParameters = new ArrayList();
            logParameters.add(request);
            String logMessage = BusinessConstants.MSG_ERROR;
            throw new ServiceException(logMessage, logParameters, ex);
        }    	
		return returnvalid;
    	
    }
    
    private Map validateAccumulators(Contract contract,
    		SaveContractBasicInfoRequest request, 
			List messageList, SaveContractBasicInfoResponse response,Map globalMsgMap) 
    throws ServiceException{
    	Map messageMap = new HashMap();
    	List codedAccumQuestionList = new ArrayList();
    	List codedAccumTieredQuestionList = new ArrayList();
    	List benefitDetailsBasedDS = new ArrayList();
    	List codedLinesList = new ArrayList();
    	List validObjLst = new ArrayList();
    	List inValidObjLst = new ArrayList();
    	
    	List validTierObjLst = new ArrayList();
    	List inValidTierObjLst = new ArrayList();
    	//List mappingWithBnftAccAssnLst = new ArrayList();
    	String BYCYAnswer = null;
    	ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
    	List dateSegmentList =null;
    	try{
    		contract =(Contract)builder.retrieve(contract);
			List busDomainList =DomainUtil.retrieveAssociatedDomains(
					BusinessConstants.ENTITY_TYPE_CONTRACT,
					contract.getParentSysId());
			contract.setBusinessDomains(busDomainList);
    		if("BUILDING".equals(contract.getStatus()) || "CHECKED_OUT".equals(contract.getStatus())){
    			dateSegmentList= builder.retrieveCheckInDateSegments(contract.getContractSysId());
    		}else{
    			dateSegmentList= builder.retrieveValidStatusDatesegments(contract.getContractSysId(),null);}
    		boolean isCompletelyReadyForImageRewrite = false;	
    		isCompletelyReadyForImageRewrite = BusinessUtil.isReadyForImageRewrite(contract);
    		if(isCompletelyReadyForImageRewrite){
    		if(dateSegmentList != null){ 
    			Iterator itr = dateSegmentList.iterator(); 
    			while(itr.hasNext()){
    				DateSegment eachDateSegment = (DateSegment)itr.next();
    				List BYCYAnswerList =  builder.getBYCYAnswerList(eachDateSegment.getDateSegmentSysId());
    				if(null != BYCYAnswerList && !BYCYAnswerList.isEmpty()){
    					ContractAssnQuestionnaireBO questionnaireBO = (ContractAssnQuestionnaireBO)BYCYAnswerList.get(0);
    					BYCYAnswer = questionnaireBO.getSelectedAnswerDesc();}
    				if(null != eachDateSegment.getImageRWDAFlag() && "Y".equalsIgnoreCase(eachDateSegment.getImageRWDAFlag())){
   						codedAccumQuestionList = builder.getCodedAccumQuestions(eachDateSegment);
   						Iterator codedAccumQuestionItr = codedAccumQuestionList.iterator();
    						while(codedAccumQuestionItr.hasNext()){
    							AccumulatorValidationBO accumValidationBO = (AccumulatorValidationBO)codedAccumQuestionItr.next();
    								List admnOptAnsweredLst = builder.checkAdminOptionAnswered(accumValidationBO);
    								if(null != admnOptAnsweredLst && !admnOptAnsweredLst.isEmpty()){ //ADMIN OPTION ANSWERED
    									List answeredQuestionList = builder.getAnsweredQuestionList(accumValidationBO);
    									if(null != answeredQuestionList && !answeredQuestionList.isEmpty()){
    										AccumulatorValidationBO accumValBo= (AccumulatorValidationBO) answeredQuestionList.get(0);
    										if(isCompletelyReadyForImageRewrite){
    											checkStandardAccumulator(accumValidationBO,contract,accumValBo.getQuestionNumber(),BYCYAnswer,messageMap, accumValBo.getPossibleAnswer());
    										}
    										checkAccumulatorDetails(accumValidationBO,accumValBo.getPossibleAnswer(),messageMap);
    										validObjLst.add(accumValidationBO);
    									}else{
    										/*String key = accumValidationBO.getBenefitCompName()+": "+accumValidationBO.getBenfitName();
    										String errorValue = accumValidationBO.getDisplayQuestion()+":"+accumValidationBO.getQuestionDesc();
    										messageMap = addToMsgMap(BusinessConstants.ACCUM_QUESTION_NOTCODED,key,errorValue,messageMap); */
    										inValidObjLst.add(accumValidationBO);
    									}
    								}else{
    									List answeredInGenBnftLst = builder.checkAnsweredInGenBenefits(accumValidationBO);
    									if(null != answeredInGenBnftLst && !answeredInGenBnftLst.isEmpty()){
    										AccumulatorValidationBO accumValBo= (AccumulatorValidationBO) answeredInGenBnftLst.get(0);	
    										if(isCompletelyReadyForImageRewrite){
    											checkStandardAccumulator(accumValidationBO,contract,accumValBo.getQuestionNumber(),BYCYAnswer,messageMap, accumValBo.getPossibleAnswer());
    											checkAccumulatorDetails(accumValidationBO,accumValBo.getPossibleAnswer(),messageMap);
    										}
    										validObjLst.add(accumValidationBO);
    									}else{
    										/*String key = accumValidationBO.getBenefitCompName()+": "+accumValidationBO.getBenfitName();
    										String errorValue = accumValidationBO.getDisplayQuestion()+":"+accumValidationBO.getQuestionDesc();
    										messageMap = addToMsgMap(BusinessConstants.ACCUM_QUESTION_NOTCODED,key,errorValue,messageMap);*/
    										inValidObjLst.add(accumValidationBO);
    									}
    								}
    						}//END codedAccumQuestionItr
    						
    						/** Tier part Begin***/
    						codedAccumTieredQuestionList = builder.getCodedTieredAccumQuestions(eachDateSegment);
    						Iterator codedAccumTieredQuestionItr = codedAccumTieredQuestionList.iterator();
    						while(codedAccumTieredQuestionItr.hasNext()){
    							AccumulatorValidationBO accumValidationBO = (AccumulatorValidationBO)codedAccumTieredQuestionItr.next();
    							List tierQstnAnsweredLst = builder.checkTierQstnAnswered(accumValidationBO);
    							if(null != tierQstnAnsweredLst && !tierQstnAnsweredLst.isEmpty()){
									AccumulatorValidationBO accumValBo= (AccumulatorValidationBO) tierQstnAnsweredLst.get(0);
									if(isCompletelyReadyForImageRewrite){
										checkStandardAccumulator(accumValidationBO,contract,accumValBo.getQuestionNumber(),BYCYAnswer,messageMap, accumValBo.getPossibleAnswer());
									}
									checkAccumulatorDetails(accumValidationBO,accumValBo.getPossibleAnswer(),messageMap);
									validTierObjLst.add(accumValidationBO);
								}else{
									/*String key = accumValidationBO.getBenefitCompName()+": "+accumValidationBO.getBenfitName()+": "+accumValidationBO.getTierDescription()+": "+accumValidationBO.getTierCriteriaNm();
									String errorValue = accumValidationBO.getDisplayQuestion()+":"+accumValidationBO.getQuestionDesc();
									messageMap = addToMsgMap(BusinessConstants.TIERED_ACCUM_QUESTION_NOTCODED,key,errorValue,messageMap); */
									inValidTierObjLst.add(accumValidationBO);
								}
    						}
    						/** Tier part End***/
    				   }
    			  } 
    		 }
    	}
    	}catch(SevereException ex){
    		List logParameters = new ArrayList();
    		logParameters.add(request);
    		String logMessage = BusinessConstants.MSG_ERROR;
    		throw new ServiceException(logMessage, logParameters, ex);
    	} 

    	/**Adding message for non tier part*****************/
    	try{
         Iterator vaildObjItr = validObjLst.iterator();
        
        while(vaildObjItr.hasNext()){
        	AccumulatorValidationBO validAccumValBo = (AccumulatorValidationBO)vaildObjItr.next();
        	String validDSId = validAccumValBo.getDateSegmentSysId().toString();
        	String validBCId = validAccumValBo.getBenefitComponentSysId().toString();
        	int validBnftId = validAccumValBo.getBenefitSysId();
        	String validQstnDesc = validAccumValBo.getQuestionDesc().toString();
        	Iterator inVaildObjItr = inValidObjLst.iterator();
        	while(inVaildObjItr.hasNext()){
        		AccumulatorValidationBO inValidAccumValBo = (AccumulatorValidationBO)inVaildObjItr.next();
        		String inValidDSId = inValidAccumValBo.getDateSegmentSysId().toString();
            	String inValidBCId = inValidAccumValBo.getBenefitComponentSysId().toString();
            	int inValidBnftId = inValidAccumValBo.getBenefitSysId();
            	String inValidQstnDesc = inValidAccumValBo.getQuestionDesc().toString();  
        	   
            	if(validDSId.equalsIgnoreCase(inValidDSId) && 
            	   validBCId.equalsIgnoreCase(inValidBCId) && 
            	   validBnftId == inValidBnftId && 
            	   validQstnDesc.equalsIgnoreCase(inValidQstnDesc)){
            		inVaildObjItr.remove();
            	}
          	}
        }
        
        Iterator inVaildObjMsgItr = inValidObjLst.iterator();
        
        while(inVaildObjMsgItr.hasNext()){
        	AccumulatorValidationBO msgAccumValBo = (AccumulatorValidationBO)inVaildObjMsgItr.next();
        	String key = msgAccumValBo.getBenefitCompName()+": "+msgAccumValBo.getBenfitName();
			String errorValue = msgAccumValBo.getDisplayQuestion()+":"+msgAccumValBo.getQuestionDesc();
			messageMap = addToMsgMap(BusinessConstants.ACCUM_QUESTION_NOTCODED,key,errorValue,messageMap); 
        }
    	}catch(Exception e){
    		List logParameters = new ArrayList();
    		logParameters.add(request);
    		String logMessage = BusinessConstants.MSG_ERROR;
    		throw new ServiceException(logMessage, logParameters, e);
    	}

        /**Adding message for non tier part Ends*****************/
        
        /**Adding message for tier part*****************/
    	try{
        Iterator vaildTierObjItr = validTierObjLst.iterator();
        
        while(vaildTierObjItr.hasNext()){
        	AccumulatorValidationBO validAccumValBo = (AccumulatorValidationBO)vaildTierObjItr.next();
        	String validDSId = validAccumValBo.getDateSegmentSysId().toString();
        	String validBCId = validAccumValBo.getBenefitComponentSysId().toString();
        	int validBnftId = validAccumValBo.getBenefitSysId();
        	String validQstnDesc = validAccumValBo.getQuestionDesc().toString();
        	int validTierSysId = validAccumValBo.getContractTierSysId();
        	Iterator inVaildTierObjItr = inValidTierObjLst.iterator();
        	while(inVaildTierObjItr.hasNext()){
        		AccumulatorValidationBO inValidAccumValBo = (AccumulatorValidationBO)inVaildTierObjItr.next();
        		String inValidDSId = inValidAccumValBo.getDateSegmentSysId().toString();
            	String inValidBCId = inValidAccumValBo.getBenefitComponentSysId().toString();
            	int inValidBnftId = inValidAccumValBo.getBenefitSysId();
            	String inValidQstnDesc = inValidAccumValBo.getQuestionDesc().toString();  
            	int inValidTierSysId = inValidAccumValBo.getContractTierSysId();
        	   
            	if(validDSId.equalsIgnoreCase(inValidDSId) && 
            	   validBCId.equalsIgnoreCase(inValidBCId) && 
            	   validBnftId == inValidBnftId && 
            	   validQstnDesc.equalsIgnoreCase(inValidQstnDesc) &&
            	   validTierSysId==inValidTierSysId){
            		inVaildTierObjItr.remove();
            	}
          	}
        }
        
        Iterator inVaildObjTierMsgItr = inValidTierObjLst.iterator();
        
        while(inVaildObjTierMsgItr.hasNext()){
        	AccumulatorValidationBO msgAccumValBo = (AccumulatorValidationBO)inVaildObjTierMsgItr.next();
        	String key = msgAccumValBo.getBenefitCompName()+": "+msgAccumValBo.getBenfitName()+": "+msgAccumValBo.getTierDescription();
			String errorValue = msgAccumValBo.getDisplayQuestion()+":"+msgAccumValBo.getQuestionDesc();
			messageMap = addToMsgMap(BusinessConstants.TIERED_ACCUM_QUESTION_NOTCODED,key,errorValue,messageMap);
        }
    	}catch(Exception e){
    		List logParameters = new ArrayList();
    		logParameters.add(request);
    		String logMessage = BusinessConstants.MSG_ERROR;
    		throw new ServiceException(logMessage, logParameters, e);
    	}
        /**Adding message for tier part Ends*****************/



    	putToGlobalMessageMap(messageMap,globalMsgMap);

    	return globalMsgMap;

    }
    
    public void checkStandardAccumulator(AccumulatorValidationBO accumValidationBO,Contract contract,int questionNumber,String BYCYAnswer,Map messageMap,String possibleAnswer)throws ServiceException {
    try{
    	ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
    	List stdAccumMapList = builder.getStandardAccumulatorSet(accumValidationBO,contract,questionNumber,BYCYAnswer);
		if(null != stdAccumMapList && !stdAccumMapList.isEmpty()){
			boolean stdAccumNotSetFlg = false;
			Iterator stdAccumItr = stdAccumMapList.iterator();
			while(stdAccumItr.hasNext()){
				StandardAccumulator standardAccum = (StandardAccumulator)stdAccumItr.next();
				if(!standardAccum.getStandardAccumulatorStr().equalsIgnoreCase(possibleAnswer)){
					stdAccumNotSetFlg = true;
				}else{
					stdAccumNotSetFlg = false;
				}
			}
			if(stdAccumNotSetFlg){
				String key = accumValidationBO.getBenefitCompName()+": "+accumValidationBO.getBenfitName();
				String errorValue = accumValidationBO.getDisplayQuestion()+":"+accumValidationBO.getQuestionDesc();
				messageMap = addToMsgMap(
						BusinessConstants.STD_ACCUM_NOT_SET,key,errorValue,
						messageMap);
			}
		}
    } catch (Exception ex) {
	    throw new ServiceException("Exception occured while BOM call",
	            null, ex);
	}
    }
    
    public void checkAccumulatorDetails(AccumulatorValidationBO accumValidationBO,String possibleAnswer,Map messageMap)throws ServiceException{
    	try{
    	ContractBusinessObjectBuilder builder = new ContractBusinessObjectBuilder();
    	List accumList = builder.getAccumulatorSet(possibleAnswer);
		if(null != accumList && !accumList.isEmpty()){
			AccumulatorImpl accImpl =(AccumulatorImpl)accumList.get(0);
			

			if("Y".equalsIgnoreCase(accImpl.getDelFlg())){
				String key = accumValidationBO.getBenefitCompName()+": "+accumValidationBO.getBenfitName();
				String errorValue = accImpl.getSvcCde();
				messageMap = addToMsgMap(
						BusinessConstants.ACCUM_DELETED,key,errorValue,
						messageMap);    																
			}

			if("N".equalsIgnoreCase(accImpl.getInClaimsFlg())){
				String key = accumValidationBO.getBenefitCompName()+": "+accumValidationBO.getBenfitName();
				String errorValue = accImpl.getSvcCde();
				messageMap = addToMsgMap(
						BusinessConstants.NOT_AVAILABLE_IN_CLAIMSYSTEM,key,errorValue,
						messageMap);    																
			}
		}
    	 } catch (Exception ex) {
    		    throw new ServiceException("Exception occured while BOM call",
    		            null, ex);
    		}
    }
    
    public void setValidationMessage(AccumulatorValidationBO accumValidationBO,Map messageMap,String message){
    	String key = accumValidationBO.getBenefitCompName()+": "+accumValidationBO.getBenfitName();
		String errorValue = accumValidationBO.getDisplayQuestion()+":"+accumValidationBO.getQuestionDesc();
		messageMap = addToMsgMap(message,key,errorValue,messageMap); 
    }
    
    public Map addToMsgMap(String key, String valueToBeAdded, Map msgMap) {
		String errorValue = "";
		if (BusinessConstants.NOT_AVAILABLE_IN_CLAIMSYSTEM.equalsIgnoreCase(key)) {
			valueToBeAdded = valueToBeAdded + "<BR>";
		} else {
			valueToBeAdded = valueToBeAdded + ",";
		}
		if (!msgMap.containsKey(key)) {
			msgMap.put(key, valueToBeAdded);
		} else {
			//isErrorMsgPresent = true;
			errorValue = (String) msgMap.get(key);
			errorValue += valueToBeAdded;
			msgMap.put(key, errorValue);
		}
		return msgMap;
	}
    
    public Map addToMsgMap(String key,String benefit, String valueToBeAdded, Map msgMap) {
		String errorValue = "";
		if (msgMap.containsKey(key)) {
			Map innerMap = (Map)msgMap.get(key);
			if(!innerMap.containsKey(benefit)){
				innerMap.put(benefit, benefit + ": <BR/>" + valueToBeAdded);
			}else{
				errorValue = (String) innerMap.get(benefit);
				errorValue = errorValue + "<BR/>" + valueToBeAdded;
				innerMap.put(benefit, errorValue);
			}
		} else {
			Map innerMap = new HashMap();
			innerMap.put(benefit, benefit + ": <BR/>" + valueToBeAdded);
			msgMap.put(key, innerMap);
		}
		return msgMap;
	}
  
    /**
     * This method validates  if the pricing info data of a contract is duplicated 
     * @param  SaveContractBasicInfoRequest
     * @return boolean
     * @throws ServiceException
     */
    public WPDResponse execute(SavePricingInfoRequest savePricingInfoRequest)
    throws ServiceException { 
	try {
		SavePricingInfoResponse response = (SavePricingInfoResponse) WPDResponseFactory
											.getResponse(WPDResponseFactory.PRICING_INFO_RESPONSE);
		List messageList = new ArrayList();
		ContractPricingInfo contractPricingInfo = new ContractPricingInfo();
	    boolean valid = true;
		//create product validation 
	    setValuesToContractPricingInfo(savePricingInfoRequest, contractPricingInfo);
		//checks product duplicate
	    if(isPricingInfoDuplicate(contractPricingInfo)) {
	        valid = false;
	        messageList.add(new ErrorMessage(BusinessConstants.MSG_CONTRACT_PRICINGINFO_SAVE_DUPLICATE));
	    }
		    response.setValid(valid);
		    response.setMessages(messageList);
		    return response;
		} catch (Exception ex) {
		    throw new ServiceException("Exception occured while BOM call",
		            null, ex);
		}
}
    
    
//----------------------------------------------------------  
    /**
     * This method is used to get the default end date 
     * @return Date
     * 
     */
    private Date getDefaultEndDate(){
        Calendar calendar = new GregorianCalendar(9999,12,31);
    	return calendar.getTime();
    }
 // to get Contract BO
    /**
     * This method returns the contract BO object
     * @return Contract
     * 
     */
    private Contract getContract(ContractRequest request) {
        ContractKeyObject contractKeyObject = request.getContractKeyObject();
        Contract contract = new Contract();
        if(contractKeyObject == null) {
            Logger.logError("Contract Key Object is null in the request");
            return null;
        }
        contract.setContractSysId(contractKeyObject.getContractSysId());
        contract.setContractId(contractKeyObject.getContractId());
        contract.setStatus(contractKeyObject.getStatus());
        return contract;
    }
   // set values from ContractVo to ContractBO
    /**
     * This method sets the values from Contract VO to BO
     * @return void
     * @param ContractVO,Contract
     * 
     */
    private void setBasicInfoToContract(ContractVO contractVO, Contract contract){
    	contract.setContractId(contractVO.getContractId());
    	contract.setContractSysId(contractVO.getContractSysId());
    	contract.setParentSysId(contractVO.getParentSysId());
    	contract.setBaseContractId(contractVO.getBaseContractId());
    	contract.setBaseContractDate(contractVO.getBaseContractDate());
        contract.setDateSegmentList(contractVO.getDateSegmentList());
        contract.setContractType(contractVO.getContractType());
        contract.setBusinessDomains(contractVO.getDomainList());
        if(BusinessConstants.MSG_CUSTOM_CONTRACT_TYPE.equals(contract.getContractType())){
        	contract.setBaseDateSegmentSysId(contractVO.getBaseDateSegmentSysId());
        } else if (BusinessConstants.STANDARD_TYPE.equals(contract
				.getContractType())
				&& BusinessConstants.VALUE_ZERO != contractVO
						.getBaseDateSegmentSysId()) {
			contract.setBaseDateSegmentSysId(contractVO
					.getBaseDateSegmentSysId());
		}
        contract.setDateSegmentList(contractVO.getDateSegmentList());
        contract.setVersion(contractVO.getVersion());
        contract.setStatus(contractVO.getStatus());
    }
    
    /**
     * This method checks a PricingInfo is duplicate or not.returns a boolean
     * @param contractPricingInfo
     * @return
     * @throws SevereException
     */
    public static boolean isPricingInfoDuplicate(ContractPricingInfo contractPricingInfo) throws SevereException{
    	ContractAdapterManager adapterManager = new ContractAdapterManager();
        List duplicateList = adapterManager.getDuplicatePricingInfo(contractPricingInfo);
        if(duplicateList != null && duplicateList.size() > 0)
            return true;
        return false;
    }
     
    /**
     * Method to Set values from SavePricingInfoRequest to ContractPricingInfo
     * @param valueObject		SavePricingInfoRequest object from which values to be taken.
     * @param businessObject	ContractPricingInfo object to which values to be put.
     */
	private void setValuesToContractPricingInfo(SavePricingInfoRequest savePricingInfoRequest,
			ContractPricingInfo businessObject) {
		businessObject.setContractDateSegmentSysId(savePricingInfoRequest
														.getContractKeyObject()
														.getDateSegmentId());
		businessObject.setCoverage(savePricingInfoRequest.getContractCoverage());
		businessObject.setPricing(savePricingInfoRequest.getContractPricing());
		businessObject.setNetwork(savePricingInfoRequest.getContractNetwork());
		businessObject.setLastUpdatedUser(savePricingInfoRequest.getUser().getUserId());
		businessObject.setLastUpdatedTimestamp(new Date());
		businessObject.setCreatedUser(savePricingInfoRequest.getUser().getUserId());
		businessObject.setCreatedTimestamp(new Date());
	}
	/**
     * Validate rule 
     * @dateSegmentId
     * return boolean
     */
// Start of CR Check In Validation	
    //private boolean isValidrule(int dateSegmentId) throws SevereException {
	private List isValidRule(int dateSegmentId) throws SevereException {
// End of CR Check In Validation    	
    	ContractAdapterManager contractAdapterManager = new ContractAdapterManager();
// Start of CR Check In Validation    	
       	/*List ruleList = contractAdapterManager.
							validateBenefitRule(dateSegmentId);
    	if(ruleList!= null && ruleList.size()>0)
    		return true;
    	else
    		return false;*/
		List ruleList = contractAdapterManager.
					getContractInvalidRules(dateSegmentId);
    	return ruleList;
// End of CR Check In Validation    		
    }
	
	//SSCR 17571 -Start
	
	private List checkcarvedoutQuestionInfo(int dateSegmentId)
			throws SevereException {
		// Start of carved out Validation
		List vendorInfo = new ArrayList();
		List carvedNotAnswerd = new ArrayList();
		try {
			ContractAdapterManager contractAdapterManager = new ContractAdapterManager();
			ItemAdapterManager itemRefAdapterManager = new ItemAdapterManager();
			
			int catlgid = 50210;
			String benefitCompName = null;
			String benefitName = null;
			String vendorRef = null;
			String carvRef = null;
			String carv = null;
			int ctlgbenefitId=50290;
			String vendorRefDesc = null;
			vendorInfo = new ArrayList<String>();			
			List<ItemBO> referenceInfoList = itemRefAdapterManager
					.locateReferenceItem(catlgid);
			
			
			VendorInfoBO vendorInfoBO;
			for (int i = 0; i < referenceInfoList.size(); i++) {
				ItemBO carvedoutQuestRef = referenceInfoList.get(i);
				vendorRef = carvedoutQuestRef.getPrimaryCode();
				carvRef = carvedoutQuestRef.getSecondaryCode();
				vendorRefDesc = carvedoutQuestRef.getDescription();
				//find benefits 
				
				List<AccumulatorValidationBO> benefitList=contractAdapterManager
				.findcarvedBenefits(ctlgbenefitId,vendorRef);
				if (!benefitList.isEmpty()) {
					for (int k = 0; k < benefitList.size(); k++){	
						AccumulatorValidationBO BenefitQuest = benefitList.get(k);
						benefitName = BenefitQuest.getBenfitName();
				List<AccumulatorValidationBO> carvedoutInfoList = contractAdapterManager.checkcarvedoutQuestionInfo(dateSegmentId,carvRef,benefitName);
				if (!carvedoutInfoList.isEmpty()) {
				
					for (int j = 0; j < carvedoutInfoList.size(); j++) {						
						AccumulatorValidationBO carvedoutQuest = carvedoutInfoList.get(j);
						benefitCompName = carvedoutQuest.getBenefitCompName();
						//benefitName = carvedoutQuest.getBenfitName();
						carv = carvedoutQuest.getQuestionDesc();
						if (carv != null && carv.equals(carvRef)) {
							
							List <AccumulatorValidationBO> questionExistsList = contractAdapterManager
									.checkQuestionExists(vendorRef);
							
							if(!questionExistsList.isEmpty()){
								
							List<AccumulatorValidationBO> vendorQuestList = contractAdapterManager
									.checkVendorQuestionInfo(dateSegmentId,
											vendorRef,benefitCompName,
											benefitName);				
							
						
							if (vendorQuestList.isEmpty()) {
								//check tier
								List<AccumulatorValidationBO> vendorQuestTierList = contractAdapterManager
										.checkVendorQuestionTierInfo(dateSegmentId,
												vendorRef,benefitCompName,
												benefitName);
								if(vendorQuestTierList.isEmpty()){
								
								vendorInfoBO = new VendorInfoBO();
								vendorInfoBO.setBenefitCompName(benefitCompName);
								vendorInfoBO.setBenefitName(benefitName);
								vendorInfoBO.setVendorRef(vendorRef);
								vendorInfoBO.setVendorRefDesc(vendorRefDesc);
								vendorInfo.add(vendorInfoBO);
								}
								
								
							}
							}
						}

					}
				}
				}
				}
				
			}
			

		} catch (AdapterException ex) {
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate (ItemLocateCriteria itemLocateCriteria, User user), in ItemBusinessObjectBuilder",
					errorParams, ex);
		}

		return vendorInfo;
		// End of carved out Check In Validation
	}
	//SSCR 17571 -End
	/**
     * Validate rule 
     * @dateSegmentId
     * return boolean
     */
// Start of CR Check In Validation	
    //private boolean isValidContractRule(int dateSegmentId) throws SevereException {
	private List isValidContractRule(int dateSegmentId) throws SevereException {
// End of CR Check In Validation     	
    	ContractAdapterManager contractAdapterManager = new ContractAdapterManager();
       	List ruleList = contractAdapterManager.
       		getContractRulesForValidation(dateSegmentId);
// Start of CR Check In Validation       	
    	/*if(ruleList!= null && ruleList.size()>0)
    		return true;
    	else
    		return false;*/
       	return ruleList;
// End of CR Check In Validation       	
    }
	
/**
 * This method checks for duplicate reference validation in Contract line and questions
 * @param contract
 * @return message
 * @throws SevereException
 */
	private ErrorMessage isUnique(Contract contract) throws SevereException
	  {    
	    boolean status =true;
	    ErrorMessage message =null; 
	    ContractBusinessObjectBuilder contractBusinessObjectBuilder = new ContractBusinessObjectBuilder(); 
	    List dateSegmentList = contractBusinessObjectBuilder.retrieveDateSegments(contract.getContractId());
	    status = contractBusinessObjectBuilder.getDuplicateReference(dateSegmentList);	    
	    if(!status){
	    	message = new ErrorMessage(BusinessConstants.MSG_CONTRACT_CHECKIN_INVALID_REFERENCE_NOT_UNIQUE);
	    	message.setLink(BusinessConstants.CONTRACT_DUPLICATE_REFERENCE);
	    }
	     return message;
	  }
	
	private Map putToGlobalMessageMap(Map messageMap,Map globalMsgMap){
		String message="";
		if (messageMap.containsKey(BusinessConstants.ACCUM_QUESTION_NOTCODED)){
			Map messages = (HashMap)messageMap.get(BusinessConstants.ACCUM_QUESTION_NOTCODED);
			Iterator messageItr = messages.values().iterator();
			while(messageItr.hasNext()){
				if(globalMsgMap.containsKey(BusinessConstants.ACCUM_QUESTION_NOTCODED)){
					message = (String)globalMsgMap.get(BusinessConstants.ACCUM_QUESTION_NOTCODED);
					globalMsgMap.put(BusinessConstants.ACCUM_QUESTION_NOTCODED, message + "<BR/>" + (String)messageItr.next());
				}else{
					globalMsgMap.put(BusinessConstants.ACCUM_QUESTION_NOTCODED, (String)messageItr.next());
				}

			}
		}
		if (messageMap.containsKey(BusinessConstants.TIERED_ACCUM_QUESTION_NOTCODED)){
			Map messages = (HashMap)messageMap.get(BusinessConstants.TIERED_ACCUM_QUESTION_NOTCODED);
			Iterator messageItr = messages.values().iterator();
			while(messageItr.hasNext()){
				if(globalMsgMap.containsKey(BusinessConstants.TIERED_ACCUM_QUESTION_NOTCODED)){
					message = (String)globalMsgMap.get(BusinessConstants.TIERED_ACCUM_QUESTION_NOTCODED);
					globalMsgMap.put(BusinessConstants.TIERED_ACCUM_QUESTION_NOTCODED, message + "<BR/>" + (String)messageItr.next());
				}else{
					globalMsgMap.put(BusinessConstants.TIERED_ACCUM_QUESTION_NOTCODED, (String)messageItr.next());
				}

			}
		}
		if (messageMap.containsKey(BusinessConstants.NOT_AVAILABLE_IN_CLAIMSYSTEM)){
			Map messages = (HashMap)messageMap.get(BusinessConstants.NOT_AVAILABLE_IN_CLAIMSYSTEM);
			Iterator messageItr = messages.values().iterator();
			while(messageItr.hasNext()){
				if(globalMsgMap.containsKey(BusinessConstants.NOT_AVAILABLE_IN_CLAIMSYSTEM)){
					message = (String)globalMsgMap.get(BusinessConstants.NOT_AVAILABLE_IN_CLAIMSYSTEM);
					globalMsgMap.put(BusinessConstants.NOT_AVAILABLE_IN_CLAIMSYSTEM, message + "<BR/>" + (String)messageItr.next());
				}else{
					globalMsgMap.put(BusinessConstants.NOT_AVAILABLE_IN_CLAIMSYSTEM, (String)messageItr.next());
				}

			}
		}
		if (messageMap.containsKey(BusinessConstants.ACCUM_DELETED)){
			Map messages = (HashMap)messageMap.get(BusinessConstants.ACCUM_DELETED);
			Iterator messageItr = messages.values().iterator();
			while(messageItr.hasNext()){
				if(globalMsgMap.containsKey(BusinessConstants.ACCUM_DELETED)){
					message = (String)globalMsgMap.get(BusinessConstants.ACCUM_DELETED);
					globalMsgMap.put(BusinessConstants.ACCUM_DELETED, message + "<BR/>" + (String)messageItr.next());
				}else{
					globalMsgMap.put(BusinessConstants.ACCUM_DELETED, (String)messageItr.next());
				}

			}
		}
		if (messageMap.containsKey(BusinessConstants.STD_ACCUM_NOT_SET)){
			Map messages = (HashMap)messageMap.get(BusinessConstants.STD_ACCUM_NOT_SET);
			Iterator messageItr = messages.values().iterator();
			while(messageItr.hasNext()){
				if(globalMsgMap.containsKey(BusinessConstants.STD_ACCUM_NOT_SET)){
					message = (String)globalMsgMap.get(BusinessConstants.STD_ACCUM_NOT_SET);
					globalMsgMap.put(BusinessConstants.STD_ACCUM_NOT_SET, message + "<BR/>" + (String)messageItr.next());
				}else{
					globalMsgMap.put(BusinessConstants.STD_ACCUM_NOT_SET, (String)messageItr.next());
				}

			}
		}
		if (messageMap.containsKey(BusinessConstants.INVALID_ACCUM_SET)){
			Map messages = (HashMap)messageMap.get(BusinessConstants.INVALID_ACCUM_SET);
			Iterator messageItr = messages.values().iterator();
			while(messageItr.hasNext()){
				if(globalMsgMap.containsKey(BusinessConstants.INVALID_ACCUM_SET)){
					message = (String)globalMsgMap.get(BusinessConstants.INVALID_ACCUM_SET);
					globalMsgMap.put(BusinessConstants.INVALID_ACCUM_SET, message + "<BR/>" + (String)messageItr.next());
				}else{
					globalMsgMap.put(BusinessConstants.INVALID_ACCUM_SET, (String)messageItr.next());
				}

			}
		}
		
		return globalMsgMap;
	}
    
}
