/*
 * Created on Nov 1, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.contract;

import java.util.List;

import com.wellpoint.wpd.common.contract.request.RetrieveExpiredContractIdRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveExpiredContractIdsResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;

/**
 * @author u19142
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractExpiredIdsBackingBean extends ContractBackingBean {
	
	private List expiredContractIds;
	
	private String contractId;
	
	private String retreiveHidden;
	
	//added for ajax implementation
	private int index;
	private String searchString =null;

	
	/**
	 * Retrieves the list of all the expired Contracts 
	 * @return Returns the expiredContractIds.
	 */
	public List getExpiredContractIds() {
		String searchString  = getRequest().getParameter("searchString");
		if(null != searchString && index==0){
			this.searchString = searchString;			    
			this.filterResult();   //this has to be coded
			index++;
		}				
		
		return expiredContractIds;
	}
	
	/**
	 *
	 * 
	 * this method call at the time of filter Search.
	 * Methos crate the  Request PopupRequest and using this request call the business service
	 * retrieve List of record for popUp and set backing bean values 
	 */
		public void filterResult(){
			
			RetrieveExpiredContractIdRequest expiredContractIdRequest = (RetrieveExpiredContractIdRequest) this
			.getServiceRequest(ServiceManager.RETRIEVE_CONTRACT_IDEXPIRED_REQUEST);
			if(null!=this.searchString&&!"".equals(this.searchString)){
			expiredContractIdRequest.setSearchString(WPDStringUtil.escapeString(this.searchString.trim()));
			}
			
			RetrieveExpiredContractIdsResponse expiredContractIdsResponse = null;
			expiredContractIdsResponse = (RetrieveExpiredContractIdsResponse) executeService(expiredContractIdRequest);
			
			if (null != expiredContractIdsResponse) {
				expiredContractIds = expiredContractIdsResponse
				.getExpiredContractIdList();
				getSession().setAttribute("EXPIREDCONTRACTS_LIST",expiredContractIds);
			}
			
			
			
//			RetrieveReservedContractIdRequest retrieveReservedContractIdRequest = (RetrieveReservedContractIdRequest)this.getServiceRequest(ServiceManager.RETREIVE_RESERVED_ID_REQUEST);
//
//			retrieveReservedContractIdRequest.setSearchString(this.searchString);
//			RetrieveReservedContractIdResponse retrieveReservedContractIdResponse = (RetrieveReservedContractIdResponse)executeService(retrieveReservedContractIdRequest);
//			
//			/*PopupRequest request = getPopupRequest();
//			request.setPopAction(WebConstants.SEARCH_ACTION);
//			this.popAction = WebConstants.SEARCH_ACTION;
//			PopupResponse response = null;
//			response = (PopupResponse)executeService(request);*/
//			if(null!=retrieveReservedContractIdResponse){
//				this.reserveIds = retrieveReservedContractIdResponse.getReservedContractIds();
//				
//		}
		
	}
	
	
	/**
	 * @param expiredContractIds The expiredContractIds to set.
	 */
	public void setExpiredContractIds(List expiredContractIds) {
		this.expiredContractIds = expiredContractIds;
	}
	
	private List retreiveExpiredContractIds(){
		RetrieveExpiredContractIdRequest expiredContractIdRequest = (RetrieveExpiredContractIdRequest) this
		.getServiceRequest(ServiceManager.RETRIEVE_CONTRACT_IDEXPIRED_REQUEST);
		RetrieveExpiredContractIdsResponse expiredContractIdsResponse = null;
		expiredContractIdsResponse = (RetrieveExpiredContractIdsResponse) executeService(expiredContractIdRequest);
		
		if (null != expiredContractIdsResponse) {
			expiredContractIds = expiredContractIdsResponse
			.getExpiredContractIdList();
			getSession().setAttribute("EXPIREDCONTRACTS_LIST",expiredContractIds);
		}
		return expiredContractIds;
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
	 * @return Returns the retreiveHidden.
	 */
	public String getRetreiveHidden() {
		List expiredCntrctIds = retreiveExpiredContractIds();
		this.setExpiredContractIds(expiredCntrctIds); 
		return "";
	}
	/**
	 * @param retreiveHidden The retreiveHidden to set.
	 */
	public void setRetreiveHidden(String retreiveHidden) {
		this.retreiveHidden = retreiveHidden;
	}
	
	/**
	 * @return Returns the searchString.
	 */
	public String getSearchString() {
		return searchString;
	}
	/**
	 * @param searchString The searchString to set.
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
}