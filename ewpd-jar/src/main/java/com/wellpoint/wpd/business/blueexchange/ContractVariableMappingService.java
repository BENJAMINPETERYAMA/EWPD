/*
 * Created on May 10, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.blueexchange;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.blueexchange.builder.ContractVariableMappingBuilder;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.blueexchange.bo.ContractVariableMappingBO;
import com.wellpoint.wpd.common.blueexchange.bo.VariableMappingBO;
import com.wellpoint.wpd.common.blueexchange.request.ContractVariableMappingRequest;
import com.wellpoint.wpd.common.blueexchange.response.ContractVariableMappingResponse;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractVariableMappingService extends WPDService{


  	public static final String sysdate = getDateTime();
  	/**
	 * Service method for Retrieving and updating the contract variable mapping .
	 * 
	 * @param CContractVariableMappingRequest
	 * @return WPDResponse
	 * @throws ServiceException
	 * @throws AdapterException
	 */
	public WPDResponse execute(ContractVariableMappingRequest request) throws ServiceException {
		Logger.logInfo("Entering execute method, request = " + request.getClass().getName());
		ContractVariableMappingBuilder contractVariableMappingBuilder=new ContractVariableMappingBuilder();
	  	ContractVariableMappingResponse  contractVariableMappingResponse = (ContractVariableMappingResponse)
					WPDResponseFactory.getResponse(WPDResponseFactory.SEARCH_VARIABLE_MAPPING_RESPONSE);
	  	ContractVariableMappingBO contractVariableMappingBO = new ContractVariableMappingBO();
	  	List searchResult = new ArrayList();
	  	List finalBoList = new ArrayList();
	  	switch (request.getAction()) {
	  	case BusinessConstants.RETRIEVE_CNTRCT_VAR_MAPPING_DF:
	  		try{
	  			Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
	  			contractVariableMappingBO.setStatus(request.getStatus());  
	  			searchResult = contractVariableMappingBuilder.retriveContractVariableMapping(contractVariableMappingBO);
	  			if((! searchResult.isEmpty()) && searchResult.size()!=0){
	  			finalBoList = getFinalBoList(searchResult);
	  			if((! finalBoList.isEmpty()) && finalBoList.size()!=0){
	  			contractVariableMappingResponse.setSearchList(finalBoList);
	  			}
	  			}
	  		}catch(SevereException exp){
	  			
	  			throw new ServiceException("Exception occured while Builder call", null, exp);	  			
	  		}
	  		break;
	  			 
	  	case BusinessConstants.RETRIEVE_NA_CNTRCT_VAR_MAPPING_DF:
	  		try{
	  			Logger.logInfo("Returning  from execute method for request=" + request.getClass().getName());
	  			contractVariableMappingBO.setStatus(BusinessConstants.NOT_APPLICABLE_STATUS);
	  			searchResult = contractVariableMappingBuilder.retriveNAContractVariableMapping(contractVariableMappingBO);
	  			if((! searchResult.isEmpty()) && searchResult.size()!=0){
		  			/*finalBoList = getFinalBoList(searchResult);
		  			if((! finalBoList.isEmpty()) && finalBoList.size()!=0){
		  			contractVariableMappingResponse.setSearchList(finalBoList);
		  			}*/
	  				contractVariableMappingResponse.setSearchList(searchResult);
		  			}
	  			//contractVariableMappingResponse.setSearchList(searchResult);
	  		}catch(SevereException exp){
	  			
	  			throw new ServiceException("Exception occured while Builder call", null, exp);	  			
	  		}
	  		break;
	  	case BusinessConstants.UPDATE_STATUS_CNTRCT_VAR_MAPPING_DF:
	  		try {
	  			contractVariableMappingBO.setLastUpdatedTimestamp(request.getLastUpdatedTime());
	  			contractVariableMappingBO.setStatus(request.getStatus());	
	  			if(contractVariableMappingBO.getStatus().equals(BusinessConstants.SCHEDULED_TO_TEST)){
	  				
	  				contractVariableMappingBO.setStatus(BusinessConstants.SCHEDULED_TO_TEST);
	  			}
	  	  			contractVariableMappingResponse.setSuccess(contractVariableMappingBuilder
						.updateDataFeedStatus(contractVariableMappingBO,BusinessConstants.DATAFEED_USER));
	  			
			} catch (WPDException ex) {
				throw new ServiceException("Exception occured while BOM call",
						null, ex);
			}
			break;
	  	}
	  	
		return contractVariableMappingResponse;
	}

	/**
	 * method for getting list of variable mapping BO
	 * @param searchResult
	 * @return
	 */
	private List getFinalBoList(List searchResult) {
		List VariableMappingFinalBoList = new ArrayList();
		Iterator iter = searchResult.iterator();
		String contractVaraiable = "";
		VariableMappingBO mappingBO =  new VariableMappingBO();
		while(iter.hasNext()){
			
			ContractVariableMappingBO variableMappingBO = (ContractVariableMappingBO)iter.next();
			if((contractVaraiable.equals(variableMappingBO.getContractVaiable()))||(contractVaraiable.equals(""))){
				
				List list = new ArrayList();
				if(mappingBO.getContractVariableMappingBOList()!=null){
				list=	mappingBO.getContractVariableMappingBOList();
				list.add(variableMappingBO);
				
				}
				else{
				list.add(variableMappingBO);
				}
				mappingBO.setContractVariable(variableMappingBO.getContractVaiable());
				contractVaraiable = variableMappingBO.getContractVaiable();
				mappingBO.setContractVariableMappingBOList(list);
				
				}
			else{
				
				VariableMappingFinalBoList.add(mappingBO);
				
				contractVaraiable = variableMappingBO.getContractVaiable();
				VariableMappingBO mappingBO1 =  new VariableMappingBO();
				List list = new ArrayList();
				if(mappingBO1.getContractVariableMappingBOList()!=null){
				list = mappingBO1.getContractVariableMappingBOList();
				list.add(variableMappingBO);
				}
				else{
					list.add(variableMappingBO);
					}
				
				mappingBO1.setContractVariable(variableMappingBO.getContractVaiable());
				mappingBO1.setContractVariableMappingBOList(list);
				mappingBO=mappingBO1;
				
			}
			
						
		}
			VariableMappingFinalBoList.add(mappingBO);
		
		return VariableMappingFinalBoList;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 */
	public WPDResponse execute(WPDRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	private static String getDateTime(){
		   
               
		 String todayDate = "";
		    Calendar cal = Calendar.getInstance();
		    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		    todayDate = sdf.format(cal.getTime());
		    return todayDate;
		//dateFormat.format(date);
   } 
	
	private Date lastUpdated(String sysDate){	
		 String str_date=sysdate;
         DateFormat formatter ; 
         Date date = new Date(); 
         
	       try {
	       	formatter = new SimpleDateFormat("dd-MMM-yy");
			date = (Date)formatter.parse(str_date);
			} catch (ParseException e) {
				//System.out.println("Exception :"+e);    
				Logger.logInfo("Exception :"+e);
			}
		return date; 
	}
	

}
