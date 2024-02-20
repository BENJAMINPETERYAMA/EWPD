/*
 * ContractPopupBackingBean.java
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
import java.util.List;

import com.wellpoint.wpd.common.contract.request.LocateProductRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveBaseContractDateRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveBaseContractRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveExistingContractIdRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveReservedContractIdRequest;
import com.wellpoint.wpd.common.contract.response.LocateProductResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBaseContractDateResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBaseContractResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveExistingContractIdResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveReservedContractIdResponse;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContract;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractPopupBackingBean extends ContractBackingBean{
	
	private List productList;
	private boolean valueRetrieved = false;
	private List baseContractDate;
	private List baseContractCode;
	private List baseContractCodeForMigration;
	private boolean renderedIfBaseContractNotAvailable;
	private List reserveIds;
	private String reserve;
	private	LegacyContract legacyContract;
	
	//added for ajax implementation
	private String lob;
	private String be;
	private String bg;
   /*START CARS*/
	private String mbu;
	/*END CARS*/
	private int index;
	private String searchString =null;

//	added for copy contract to existing
    private List contractIdExistingList;
    
    
    
    public ContractPopupBackingBean(){
    	
    	super();
    }
	/**
	 * Returns the productList
	 * @return List productList.
	 */
	public List getProductList() {
	    
	    final String LOB_REQ_PARAM = "lob";
        final String BE_REQ_PARAM = "be";
        final String BG_REQ_PARAM = "bg";
        final String EFF_DATE_REQ_PARAM = "effDate";
        final String EXP_DATE_REQ_PARAM = "expDate";
        final String EXP_STATE_PARAM = "state";
        /*START CARS*/
        final String MBU_REQ_PARAM = WebConstants.MBU_REQ_PARAM;
        /*END CARS*/
        if(!valueRetrieved) {
            List lineOfBusiness = WPDStringUtil.getListFromTildaString((String)getRequest().getParameter(LOB_REQ_PARAM),2,2,2);
            List businessEntity = WPDStringUtil.getListFromTildaString((String)getRequest().getParameter(BE_REQ_PARAM),2,2,2);
            List businessGroup = WPDStringUtil.getListFromTildaString((String)getRequest().getParameter(BG_REQ_PARAM),2,2,2);
            /*START CARS*/
            List marketBusinessUnit = WPDStringUtil.getListFromTildaString((String)getRequest().getParameter(MBU_REQ_PARAM),2,2,2);
            /*END CARS*/
            Date effectiveDate = WPDStringUtil.getDateFromString((String)getRequest().getParameter(EFF_DATE_REQ_PARAM));
            Date expiryDate = WPDStringUtil.getDateFromString((String)getRequest().getParameter(EXP_DATE_REQ_PARAM));
            List state = WPDStringUtil.getListFromTildaString((String)getRequest().getParameter(EXP_STATE_PARAM),2,1,2);
            String stateId ="";
            if(null != state && state.size()>0)
            {
                stateId =(String)state.get(0);
            }
            String cntrctType=null;
            if(getContractSession().getContractKeyObject()!=null)
            	cntrctType=getContractSession().getContractKeyObject().getContractType();
            else
            	cntrctType="MANDATE";
            
            String testContractState=null;
            if(getContractSession()!=null)
            	testContractState=getContractSession().getTestContractState();
            	
  
            LocateProductRequest request = (LocateProductRequest) getServiceRequest(ServiceManager.LOCATE_PRODUCT_REQUEST);
            request.setLineOfBusiness(lineOfBusiness);
            request.setBusinessEntity(businessEntity);
            request.setBusinessGroup(businessGroup);
            /*START CARS*/
            request.setMarketBusinessUnit(marketBusinessUnit);            
            /*END CARS*/
            if(null != this.legacyContract){
                request.setEffectiveDate(legacyContract.getStartDate());
                request.setExpiryDate(legacyContract.getEndDate());            	
            }else{
            request.setEffectiveDate(effectiveDate);
            request.setExpiryDate(expiryDate);
            }
            request.setState(stateId);
            if(cntrctType.equals("MANDATE"))
            	request.setProductType(cntrctType);
            else
            	request.setProductType("STANDARD");
            request.setTestContractState(testContractState);
	
            LocateProductResponse response = (LocateProductResponse)executeService(request);
	        
            if(response!=null){
                List list=response.getProducts();
                if(null == list || list.size() ==0) {
                    this.productList = null;
                } else {
                    this.productList = response.getProducts();
                }
                this.valueRetrieved = true;
                
            }
            
        }
		return productList;

	}
	/**
	 * Sets the productList
	 * @param productList.
	 */
	public void setProductList(List productList) {
		this.productList = productList;
	}

	/**
	 * Returns the baseContractDate
	 * @return List baseContractDate.
	 */
	public List getBaseContractDate() {
		List baseContractDate = new ArrayList();
		RetrieveBaseContractDateRequest retrieveBaseContractDateRequest =(RetrieveBaseContractDateRequest)this.getServiceRequest(ServiceManager.RETREIVE_BASE_CONTRACT_DATE_REQUEST);
		String ContractId = (String) (getRequest().getParameter("sysId"));
		if(!("".equals(ContractId))){
			int ContractSysId = Integer.parseInt((String)WPDStringUtil.getListFromTildaString(ContractId,2,2,2).get(0));
			retrieveBaseContractDateRequest.setContractSysId(ContractSysId);
			retrieveBaseContractDateRequest.setDatafeedIndicator(WebConstants.FLAG_N);
			retrieveBaseContractDateRequest.setAction(1);
			RetrieveBaseContractDateResponse retrieveBaseContractDateResponse =(RetrieveBaseContractDateResponse)executeService(retrieveBaseContractDateRequest);
			baseContractDate =retrieveBaseContractDateResponse.getBaseContractDateList();
		}
		return baseContractDate;
	}
	/**
	 * Sets the baseContractDate
	 * @param baseContractDate.
	 */
	public void setBaseContractDate(List baseContractDate) {
		this.baseContractDate = baseContractDate;
	}

	private String baseContractCodeForMigrationInit;
	
	/**
	 * @return Returns the baseContractCodeForMigrationInit.
	 */
	public String getBaseContractCodeForMigrationInit() {
		final  String LEGACY_CONTRACT_ID = "legacyContractId";
		this.baseContractCodeForMigration = this.getBaseContract(RetrieveBaseContractRequest.BASECONTRACTS_FOR_MIGRATION,
				(String)getRequest().getParameter(LEGACY_CONTRACT_ID));
		return null;
	}
	/**
	 * @param baseContractCodeForMigrationInit The baseContractCodeForMigrationInit to set.
	 */
	public void setBaseContractCodeForMigrationInit(
			String baseContractCodeForMigrationInit) {
//		this.baseContractCodeForMigrationInit = baseContractCodeForMigrationInit;
	}
	/**
	 * @return Returns the baseContractCodeForMigration.
	 */
	public List getBaseContractCodeForMigration() {
		
		return baseContractCodeForMigration;
//		return this.getBaseContract(RetrieveBaseContractRequest.BASECONTRACTS_FOR_MIGRATION,
//				(String)getRequest().getParameter(LEGACY_CONTRACT_ID));
	}
	/**
	 * @param baseContractCodeForMigration The baseContractCodeForMigration to set.
	 */
	public void setBaseContractCodeForMigration(
			List baseContractCodeForMigration) {
		this.baseContractCodeForMigration = baseContractCodeForMigration;
	}
	/**
	 * Returns the baseContractCode
	 * @return List baseContractCode.
	 */
	public List getBaseContractCode() {
		return this.getBaseContract(RetrieveBaseContractRequest.BASECONTRACTS_FOR_ETAB, null);
	}
	/**
	 * Returns the baseContractCode
	 * @return List baseContractCode.
	 */
	private List getBaseContract(int action, String legacyContractId) {
		 final String LOB_REQ_PARAM = "lob";
	     final String BE_REQ_PARAM = "be";
	     final String BG_REQ_PARAM = "bg";
		/*START CARS*/
	     final String MBU_REQ_PARAM = WebConstants.MBU_REQ_PARAM;
	     /*END CARS*/
	     final String PROD_PAR_SYS_ID = "productParentSysId";
	     
		
	    List lineOfBusiness = WPDStringUtil.getListFromTildaString((String)getRequest().getParameter(LOB_REQ_PARAM),2,2,2);
        List businessEntity = WPDStringUtil.getListFromTildaString((String)getRequest().getParameter(BE_REQ_PARAM),2,2,2);
        List businessGroup = WPDStringUtil.getListFromTildaString((String)getRequest().getParameter(BG_REQ_PARAM),2,2,2);
		/*START CARS*/
        List marketBusinessUnit = WPDStringUtil.getListFromTildaString((String)getRequest().getParameter(MBU_REQ_PARAM),2,2,2);
		/*END CARS*/
        int productParentSysId = Integer.parseInt((String)getRequest().getParameter(PROD_PAR_SYS_ID));
        
        if(lineOfBusiness.size() == 0 ||businessEntity.size() == 0||businessGroup.size() == 0 ){
        	return null;
        }
         else{
			RetrieveBaseContractRequest retrieveBaseContractRequest = (RetrieveBaseContractRequest)this.getServiceRequest(ServiceManager.RETREIVE_BASE_CONTRACT_REQUEST);
			retrieveBaseContractRequest.setLineOfBusiness(lineOfBusiness);
			retrieveBaseContractRequest.setBusinessEntity(businessEntity);
			retrieveBaseContractRequest.setBusinessGroup(businessGroup);
			/*START CARS*/
			retrieveBaseContractRequest.setMarketBusinessUnit(marketBusinessUnit);
			/*END CARS*/
			retrieveBaseContractRequest.setAction(action);
			if(null != legacyContractId){
				retrieveBaseContractRequest.setContractId(legacyContractId);
			}
			retrieveBaseContractRequest.setProductParentSysId(productParentSysId);
			
			RetrieveBaseContractResponse retrieveBaseContractResponse = (RetrieveBaseContractResponse)executeService(retrieveBaseContractRequest);
			if(null == retrieveBaseContractResponse.getBaseContractList() || retrieveBaseContractResponse.getBaseContractList().isEmpty())
				return null;
			else
				return retrieveBaseContractResponse.getBaseContractList();
         }
	}
	/**
	 * Sets the baseContractCode
	 * @param baseContractCode.
	 */
	public void setBaseContractCode(List baseContractCode) {
		this.baseContractCode = baseContractCode;
	}

	/**
	 * Returns the reserveIds
	 * @return List reserveIds.
	 */
	public List getReserveIds() {
		String searchString  = getRequest().getParameter("searchString");
		
		
		String lob  = this.getRequest().getParameter("lob");
		String be  = this.getRequest().getParameter("be");
		String bg  = this.getRequest().getParameter("bg");	
		String mbu = this.getRequest().getParameter("mbu");
		
		if( null != lob ){
			this.lob = lob;
		}
		if(null != be){
			this.be = be;
		}
		if(null != bg){
			this.bg = bg;
		}
		if(null != mbu){
			this.mbu = mbu;
		}
			
		if(null != searchString && index==0){
			this.searchString = searchString;
			/*this.lob = lob;
			this.be = be;
			this.bg = bg;    */       
			this.filterResult();   //this has to be coded
			index++;
		}		
		
		return reserveIds;
	}
	/**
	 *
	 * 
	 * this method call at the time of filter Search.
	 * Methos crate the  Request PopupRequest and using this request call the business service
	 * retrieve List of record for popUp and set backing bean values 
	 */
		public void filterResult(){
			
			 List lineOfBusiness = WPDStringUtil.getListFromTildaString(this.lob,2,2,2);
		     List businessEntity = WPDStringUtil.getListFromTildaString(this.be,2,2,2);
		     List businessGroup = WPDStringUtil.getListFromTildaString(this.bg,2,2,2);
		     List marketBusinessUnit = WPDStringUtil.getListFromTildaString(this.mbu,2,2,2);
		     
			RetrieveReservedContractIdRequest retrieveReservedContractIdRequest = (RetrieveReservedContractIdRequest)this.getServiceRequest(ServiceManager.RETREIVE_RESERVED_ID_REQUEST);
			retrieveReservedContractIdRequest.setLineOfBusiness(lineOfBusiness);
			retrieveReservedContractIdRequest.setBusinessEntity(businessEntity);
			retrieveReservedContractIdRequest.setBusinessGroup(businessGroup);
			retrieveReservedContractIdRequest.setMarketBusinessUnit(marketBusinessUnit);
			
			if(null!=this.searchString&&!"".equals(this.searchString)){
			retrieveReservedContractIdRequest.setSearchString(WPDStringUtil.escapeString(this.searchString.trim()));
			}
			RetrieveReservedContractIdResponse retrieveReservedContractIdResponse = (RetrieveReservedContractIdResponse)executeService(retrieveReservedContractIdRequest);
			
			/*PopupRequest request = getPopupRequest();
			request.setPopAction(WebConstants.SEARCH_ACTION);
			this.popAction = WebConstants.SEARCH_ACTION;
			PopupResponse response = null;
			response = (PopupResponse)executeService(request);*/
			if(null!=retrieveReservedContractIdResponse){
				this.reserveIds = retrieveReservedContractIdResponse.getReservedContractIds();
				
		}
		
	}
	
	
	/**
	 * Sets the reserveIds
	 * @param reserveIds.
	 */
	public void setReserveIds(List reserveIds) {
		this.reserveIds = reserveIds;
	}
	
	     
    
    /**
     * Returns the contractIdExistingList
     * @return List contractIdExistingList.
     */
    public List getContractIdExistingList() {
        String productId =getSession().getAttribute("PRODUCTID").toString();
        String contractId = getRequest().getParameter("contractId");
        RetrieveExistingContractIdRequest existingContractRequest = (RetrieveExistingContractIdRequest)this.getServiceRequest(ServiceManager.RETRIEVE_CONTRACT_IDEXISTING_REQUEST);
      //  ContractKeyObject contractKeyObject = getContractSession().getContractKeyObject();
                       
        existingContractRequest.setProductId(Integer.parseInt(productId));
        //setValuesToRequest(existingContractRequest);
        if(null!=contractId)
        	existingContractRequest.setContractId(contractId);
        RetrieveExistingContractIdResponse existingContractIdResponse = null;
        existingContractIdResponse = (RetrieveExistingContractIdResponse)executeService(existingContractRequest);
        
        if(null!=existingContractIdResponse){
            contractIdExistingList = existingContractIdResponse.getContractIdExistingList();
          
        }
	               
        return contractIdExistingList;
    }
    
        /**
     * Sets the contractIdExistingList
     * @param contractIdExistingList.
     */
    public void setContractIdExistingList(List contractIdExistingList) {
        this.contractIdExistingList = contractIdExistingList;
    }

    /**
     * @return Returns the reserve.
     */
    //FIXME delete not in use 0 reference in workspace
    public String getReserve() {
        final String LOB_REQ_PARAM = "lineOfBusiness";
	     final String BE_REQ_PARAM = "businessEntity";
	     final String BG_REQ_PARAM = "businessGroup";
	     /*START CARS*/
	     final String MBU_REQ_PARAM = "marketBusinessUnit";
	     /*END CARS*/
	        /*
		     * Calling the function to get a list of values from a tilda string.
		     */		
	     String passedlob =(String)getRequest().getParameter(LOB_REQ_PARAM);
	     String passedbe =(String)getRequest().getParameter(BE_REQ_PARAM).trim();
	     String passedbg =(String)getRequest().getParameter(BG_REQ_PARAM);
	     /*START CARS*/
	     String passedmbu =(String)getRequest().getParameter(MBU_REQ_PARAM);
	     /*END CARS*/
	     
	     List lineOfBusiness = WPDStringUtil.getListFromTildaString((String)getRequest().getParameter(LOB_REQ_PARAM),2,2,2);
	     List businessEntity = WPDStringUtil.getListFromTildaString((String)getRequest().getParameter(BE_REQ_PARAM),2,2,2);
	     List businessGroup = WPDStringUtil.getListFromTildaString((String)getRequest().getParameter(BG_REQ_PARAM),2,2,2);
	     /*START CARS*/
	     List marketBusinessUnit = WPDStringUtil.getListFromTildaString((String)getRequest().getParameter(MBU_REQ_PARAM),2,2,2);
	     /*END CARS*/
			this.lob=passedlob;
			this.be=passedbe;
			this.bg=passedbg;
			/*START CARS*/
			this.mbu=passedmbu;
			/*END CARS*/
			
	     /*String passedlob =WPDStringUtil.getTildaStringFromList(lineOfBusiness);
	     String passedbe =WPDStringUtil.getTildaStringFromList(businessEntity);
	     String passedbg =WPDStringUtil.getTildaStringFromList(businessGroup);*/
	     
	     	/*if(lineOfBusiness.contains("ALL")){
	     		lineOfBusiness = new ArrayList();
	     		lineOfBusiness.add("ALL");
	     	}
	     	if(businessEntity.contains("ALL")){
	     		businessEntity = new ArrayList();
	     		businessEntity.add("ALL");
	     	}
	     	if(businessGroup.contains("ALL")){
	     		businessGroup = new ArrayList();
	     		businessGroup.add("ALL");
	     	}*/
			RetrieveReservedContractIdRequest retrieveReservedContractIdRequest = (RetrieveReservedContractIdRequest)this.getServiceRequest(ServiceManager.RETREIVE_RESERVED_ID_REQUEST);
			retrieveReservedContractIdRequest.setLineOfBusiness(lineOfBusiness);
			retrieveReservedContractIdRequest.setBusinessEntity(businessEntity);
			retrieveReservedContractIdRequest.setBusinessGroup(businessGroup);
			/*START CARS*/
			retrieveReservedContractIdRequest.setMarketBusinessUnit(marketBusinessUnit);
			/*END CARS*/
			RetrieveReservedContractIdResponse retrieveReservedContractIdResponse = (RetrieveReservedContractIdResponse)executeService(retrieveReservedContractIdRequest);
			this.reserveIds = retrieveReservedContractIdResponse.getReservedContractIds();
        return reserve;
    }
    /**
     * @param reserve The reserve to set.
     */
    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
    
    
	/**
	 * @return Returns the renderedIfBaseContractNotAvailable.
	 */
	public boolean isRenderedIfBaseContractNotAvailable() {
		if(null != this.baseContractCodeForMigration && !this.baseContractCodeForMigration.isEmpty()){
			return false;
		}
		return true;
	}
	/**
	 * @param renderedIfBaseContractNotAvailable The renderedIfBaseContractNotAvailable to set.
	 */
	public void setRenderedIfBaseContractNotAvailable(
			boolean renderedIfBaseContractNotAvailable) {
		this.renderedIfBaseContractNotAvailable = renderedIfBaseContractNotAvailable;
	}
	
	
	/**
	 * @return Returns the be.
	 */
	public String getBe() {
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
		return lob;
	}
	/**
	 * @param lob The lob to set.
	 */
	public void setLob(String lob) {
		this.lob = lob;
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
   /*START CARS*/
	/**
	 * @return Returns the mbu.
	 */
	public String getMbu() {
		return mbu;
	}
	/**
	 * @param mbu The mbu to set.
	 */
	public void setMbu(String mbu) {
		this.mbu = mbu;
	}
   /*END CARS*/
}
