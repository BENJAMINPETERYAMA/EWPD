/*
 * BenefitComponentBackingBean.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.benefitcomponent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentRetrieveRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentViewAllRequest;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentViewAllResponse;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO;
import com.wellpoint.wpd.common.standardbenefit.bo.UniverseBO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * Backing bean for Benefit Component
 * 
 * This bean will bind with the jsp pages.
 * BenefitComponentBackingBean contains the getters and setters of the 
 * variables and respective functions
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentBackingBean extends WPDBackingBean{
	
	//BC General Information View
	private String lob;
	private String businessEntity;
	private String businessGroup;
	private String minorHeading;
	private String description;
	private String term;
	private String qualifier;
	private String providerArrangement;
	private String dataType;
	private String createdUser;
    private Date createdTimestamp;                                                                                                                                                                                                                                                                                                       
    private String lastUpdatedUser;
    private Date lastUpdatedTimestamp;
    private String benefitComponentName;
    private List searchResultList;
    
   /**
	 * Constructor
	 *
	 */
	public BenefitComponentBackingBean(){
		//TODO - To be Removed
		//loadBenefitComponentforView();
		}
	
	protected BenefitComponentSessionObject getBenefitComponentSessionObject(){
		BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject) getSession()
		.getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
		
		if (benefitComponentSessionObject == null) {
			benefitComponentSessionObject = new BenefitComponentSessionObject();
			getSession().setAttribute(
					WebConstants.BENEFIT_COMPONENT_SESSION_KEY,
					benefitComponentSessionObject);
		}
		return benefitComponentSessionObject;
}
	/**
	 * Method to view the Benefit Component
	 * @return String for page navigation
	 */
	public String loadBenefitComponentforView(){		
		//TODO -- Remove HardCoding
		// getBenefitComponentSessionObject().setBenefitComponentMode("View");
		//TODO --  this.getBenefitComponentSessionObject().getBenefitComponentKey();
		int retrieveKey = 768;
		int bcId = 17;
		String bcName = "C000-General Benefits";
		retrieveBenefitComponentDetails(retrieveKey,bcId,bcName);
		return "benefitComponentGenInfoView";
		}
	
	/**
	 * Method to retrieve Benefit Component
	 * @param retrieveKey
	 * @param bcId
	 * @param bcName
	 */
	private void retrieveBenefitComponentDetails(int retrieveKey,int bcId,String bcName){			
		BenefitComponentRetrieveRequest benefitComponentRetrieveRequest = (BenefitComponentRetrieveRequest)this.getServiceRequest(ServiceManager.BENEFIT_COMPONENT_RETRIEVE_REQUEST);
			
		BenefitComponentVO benefitComponentVO = new BenefitComponentVO();
		// BenefitSysId in BMST_BNFT_MSTR
		benefitComponentVO.setStandardBenefitKey(retrieveKey);
		//BC Id and Name in BCM_BNFT_CMPNT_MSTR
		benefitComponentVO.setBenefitComponentId(bcId);
		benefitComponentVO.setBenefitComponentName(bcName);
			
		benefitComponentRetrieveRequest.setBenefitComponentVO(benefitComponentVO);
	    //BenefitComponentRetrieveResponse benefitComponentRetrieveResponse = 
	   	//	(BenefitComponentRetrieveResponse)
	   	executeService(benefitComponentRetrieveRequest);
	    	
	}
		
	// TODO Methods to be put in WPDStringUtil
	
	public static String getTildaStringFromUniverseList(List universeItems){
	        
	      if(universeItems == null)
	           return "";
	        
	      StringBuffer buffer = new StringBuffer();
	      for (int i=0; i<universeItems.size(); i++) {
	           UniverseBO element = (UniverseBO) universeItems.get(i);
	           if(i!=0){
	               buffer.append("~");
	           }
	           buffer.append(element.getCodeDescText());
	           buffer.append("~" + element.getUniverseCode());
	       }
	       return buffer.toString();
	 }
	    
	 // TODO Methods to be put in WPDStringUtil
	 public static String getTildaStringFromDataTypeList(List dataTypeItems){
	        
	      if(dataTypeItems == null)
	          return "";
	        
	      StringBuffer buffer = new StringBuffer();
	      for (int i=0; i<dataTypeItems.size(); i++) {
	          StandardBenefitDatatypeBO element = (StandardBenefitDatatypeBO) dataTypeItems.get(i);
	           if(i!=0){
	              buffer.append("~");
	          }
	            buffer.append(element.getSelectedItemCode());
	            buffer.append("~" + element.getDataTypeName());
	        }
	      return buffer.toString();
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
	 * @return Returns the businessGroup.
	 */
	public String getBusinessGroup() {
		return businessGroup;
	}
	/**
	 * @param businessGroup The businessGroup to set.
	 */
	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}
	/**
	 * @return Returns the dataType.
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType The dataType to set.
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return Returns the minorHeading.
	 */
	public String getMinorHeading() {
		return minorHeading;
	}
	/**
	 * @param minorHeading The minorHeading to set.
	 */
	public void setMinorHeading(String minorHeading) {
		this.minorHeading = minorHeading;
	}
	/**
	 * @return Returns the providerArrangement.
	 */
	public String getProviderArrangement() {
		return providerArrangement;
	}
	/**
	 * @param providerArrangement The providerArrangement to set.
	 */
	public void setProviderArrangement(String providerArrangement) {
		this.providerArrangement = providerArrangement;
	}
	/**
	 * @return Returns the qualifier.
	 */
	public String getQualifier() {
		return qualifier;
	}
	/**
	 * @param qualifier The qualifier to set.
	 */
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
	/**
	 * @return Returns the term.
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param term The term to set.
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the benefitComponentName.
	 */
	public String getBenefitComponentName() {
		return benefitComponentName;
	}
	/**
	 * @param benefitComponentName The benefitComponentName to set.
	 */
	public void setBenefitComponentName(String benefitComponentName) {
		this.benefitComponentName = benefitComponentName;
	}
	/**
	 * method to view all versions of the benefit component.
	 * @return String
	 */
	public String getViewAll(){
		String compId = (String)(ESAPI.encoder().encodeForHTML(getRequest().getParameter("id")));
		if(null!=compId  && compId.matches("[0-9a-zA-Z_]+")){
			compId = compId;
		}
		BenefitComponentViewAllRequest benefitComponentViewAllRequest = (BenefitComponentViewAllRequest)
		this.getServiceRequest(ServiceManager.BENEFIT_COMPONENT_VIEW_ALL_REQUEST);
		if(null == compId){
			benefitComponentViewAllRequest.setComponentId((String)this.getSession().getAttribute("compId"));
		}
		else{
			benefitComponentViewAllRequest.setComponentId(compId);
			this.getSession().setAttribute("compId",compId);
		}
		benefitComponentViewAllRequest.setAction(1);
		BenefitComponentViewAllResponse BenefitComponentViewAllResponse = (BenefitComponentViewAllResponse)executeService(benefitComponentViewAllRequest);
		List resultList = BenefitComponentViewAllResponse.getBenefitComponentViewAllList();
		if(resultList.size()==0){
			this.setSearchResultList(null);
		}
		else {
			this.setSearchResultList(resultList);
				for (int i = 0; i < searchResultList.size(); i++) {
	            	BenefitComponentBO benefitComponentBO=(BenefitComponentBO)searchResultList.get(i);
	            	String description = "";
	                if(null != benefitComponentBO.getDescription()){
	                 if (benefitComponentBO.getDescription().length() > 25) {
	                    description = benefitComponentBO.getDescription();
	                    description = description.substring(0, 25)
	                            .concat("...");
	                    benefitComponentBO.setDescription(description);
	                  }
	                }
	                DomainDetail domainDetail =benefitComponentBO.getDomainDetail();
                    if (domainDetail != null) {
                    	List lobList=new ArrayList(0);
                    	lobList=domainDetail.getLineOfBusiness();
                    	for(int j=0;j<lobList.size();j++){
                    		DomainItem domainItem=(DomainItem)lobList.get(j);
                    		benefitComponentBO.setLob(domainItem.getItemDesc());
                    		benefitComponentBO.setLob(WPDStringUtil.getTildaString(domainDetail.getLineOfBusiness()));
                    	}
                    } 
	                
				}
			 }
		setSearchResultToSession(resultList);
		return "";
	}
	public void setViewAll(String viewAll){
		
	}
	/**
	 * @return Returns the searchResultList.
	 */
	public List getSearchResultList() {
		return searchResultList;
	}
	/**
	 * @param searchResultList The searchResultList to set.
	 */
	public void setSearchResultList(List searchResultList) {
		this.searchResultList = searchResultList;
	}
	  
    /**
     * 
     * @param result
     */
    private void setSearchResultToSession(List result) {
        getSession().setAttribute("benefitComponentViewAllSearchResult", result);
    }
    
    //Code change for benefit component tree rendering optimization
	public boolean isTreeStructureUpdated() {
		return getBenefitComponentSessionObject().isTreeStructureUpdated();
	}
	
	public void updateTreeStructure() {
		setTreeStructureUpdated(true);
	}
	
	public void setTreeStructureUpdated(boolean treeStructureUpdated) {
		getBenefitComponentSessionObject().setTreeStructureUpdated(treeStructureUpdated);
	}
}
