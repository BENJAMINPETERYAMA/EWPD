/*
 * Created on Feb 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.benefitcomponent;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentViewRequest;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentViewResponse;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * 
 * BenefitComponent contains the getters and setters of the 
 * variables and respective functions
 */
public class BenefitComponent extends WPDBackingBean{
	
	private String bcid;
	private String bcUpdateDate;
	private String bcCreatedBy;
	
	private String lob;
	private String businessEntity;
	private String businessGroup;
	private String marketBusinessUnit;
	
	private String name;
	private String description;
	private String createdUser;
    private Date createdTimestamp;                                                                                                                                                                                                                                                                                                       
    private String lastUpdatedUser;
    private Date lastUpdatedTimestamp;
    private String effectiveDate;
    private String expiryDate; 
    
    private int viewBenefitComponentKey;
	private int benefitComponentId;
	private String state;
    private int version;
    private String status;
    
    List searchResultList = null;
    
    int count;
    private String printBreadCrumbText;
    
// ** Enhancement
    //  componentId
    private String componentType;
    //mandateId
    private String mandateType;
    //The tilds separated string for state
    private String selectedStateId;     
       
    // Tilda separated string for reference
    private String ruleId;
    private String ruleType;
    
//  To incorporate Validation for componentType = MANDATE
    private String componentTypeTab;
// ** End    
    
	/**
	 * constructor
	 *
	 */
    
    public BenefitComponent(){
    }
    
    /**
	 * @return Returns the viewBenefitComponentKey.
	 */
	public int getViewBenefitComponentKey() {
		
		int benefitKeyFromSearch;
		int benefitVersionFromSearch;
		String keyValue = null;		
		BenefitComponentVO benefitComponentVO = new BenefitComponentVO();
		
		String keyString = (String)(getRequest().getParameter("benefitcomponentkey"));
	    // String keyValue = (String)(getRequest().getParameter("benfitName"));
		String keyVersion = (String)(getRequest().getParameter("benfitVersion"));
		
		String type = (String)(getRequest().getParameter("type"));
		
		 if("view".equals(type)) {
		 	 searchResultList = (List)getSession().getAttribute("benefitComponentSearchResult");
		 }	
		 else{
		 	searchResultList = (List)getSession().getAttribute("benefitComponentViewAllSearchResult");
		 }
		 if(null != searchResultList && !searchResultList.isEmpty()){
		 	for(int i = 0 ; i < searchResultList.size() ; i++){
		 		BenefitComponentBO bcBO1 = (BenefitComponentBO)searchResultList.get(i);
		 		
		 			String bcId = new Integer(bcBO1.getBenefitComponentId()).toString();
		 			String bcVersion = new Integer(bcBO1.getVersion()).toString();
		 			
		 			if(null !=keyString && keyString.equals(bcId) && keyVersion.equals(bcVersion)){		 			  
		 				keyValue =  bcBO1.getName();
		 			}
		 			
		 	}
		 	
		 }
		 
		 
		// String keyVersion = (String)(getRequest().getParameter("benfitVersion"));
		 
		String bcType = (String)(getRequest().getParameter("bcType"));
		
		//	create the request object
		BenefitComponentViewRequest benefitComponentViewRequest = 
    		(BenefitComponentViewRequest)this.getServiceRequest(ServiceManager.RETRIEVE_BENEFIT_VIEW_REQUEST);
	 	
    	
	 	//set the values to VO
    	if (null != keyString && null != keyValue) {
            benefitKeyFromSearch = Integer.parseInt(keyString);
            benefitVersionFromSearch = Integer.parseInt(keyVersion);
            benefitComponentVO.setBenefitComponentId(benefitKeyFromSearch);
            benefitComponentVO.setBenefitComponentName(keyValue);
            benefitComponentVO.setVersion(benefitVersionFromSearch);  
            benefitComponentVO.setComponentType(bcType); 
            
            // get the benefitComponentViewAllSearchResult from session
            if((null != getSession().getAttribute("benefitComponentViewAllSearchResult"))) {
            	searchResultList = (List)getSession().getAttribute("benefitComponentViewAllSearchResult");
			
            	// get the values from searchResultList,compare and set it to VO  
            	if(null != searchResultList && !searchResultList.isEmpty()){
            		for(int i = 0; i < searchResultList.size(); i++){
            				BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList.get(i);
            				if(benefitKeyFromSearch == benefitComponentBO.getBenefitComponentId()){
            						this.getBenefitComponentSessionObject().
									setBusinessDomainList(benefitComponentBO.getBusinessDomainList());
            						List domainList = benefitComponentBO.getBusinessDomainList();
            						benefitComponentVO.setBusinessDomainList(domainList);
            						break;
            				}
            				count++;
            		}
            	} 
            	 // get the benefitComponentSearchResult from session  
            	if (count == searchResultList.size()) {
            		searchResultList = (List)getSession().getAttribute("benefitComponentSearchResult");            		
            		// get the values from searchResultList,compare and set it to VO 
            		if(null != searchResultList && !searchResultList.isEmpty()){
            				for(int i = 0; i < searchResultList.size(); i++){
            						BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList.get(i);
            						if(benefitKeyFromSearch == benefitComponentBO.getBenefitComponentId()){
            							this.getBenefitComponentSessionObject().
										setBusinessDomainList(benefitComponentBO.getBusinessDomainList());
            							List domainList = benefitComponentBO.getBusinessDomainList();
            							benefitComponentVO.setBusinessDomainList(domainList);
            							break;
            						}
            				}
            		}
            	} 
            }
            //get the benefitComponentSearchResult from session 
    	    else{
    	    	searchResultList = (List)getSession().getAttribute("benefitComponentSearchResult");    	    	
    	    	// get the values from searchResultList,compare and set it to VO 	
        	    if(null != searchResultList && !searchResultList.isEmpty()){
        	    	for(int i = 0; i < searchResultList.size(); i++){
        	    		BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList.get(i);
        	    		if(benefitKeyFromSearch == benefitComponentBO.getBenefitComponentId()){
        	    			this.getBenefitComponentSessionObject().
        						setBusinessDomainList(benefitComponentBO.getBusinessDomainList());
        	    			List domainList = benefitComponentBO.getBusinessDomainList();
        	    			benefitComponentVO.setBusinessDomainList(domainList);
        	    			break;
        	    		}
        	    	}
        	    }
    	    }
    	    
         // get the benefitComponent session object and set all the values            
            this.getBenefitComponentSessionObject().setBenefitComponentId(benefitKeyFromSearch);
            this.getBenefitComponentSessionObject().setBenefitComponentName(keyValue);
            this.getBenefitComponentSessionObject().setBenefitComponentMode("View");
            this.getBenefitComponentSessionObject().setBenefitComponentVersionNumber(benefitVersionFromSearch);
            this.getBenefitComponentSessionObject().setBcComponentType(bcType);
            if(null != getSession().getAttribute("SESSION_TREE_STATE_BC")){
            	this.getSession().removeAttribute("SESSION_TREE_STATE_BC");
            }
            
        }
    	
    	else{
    		benefitComponentVO.setBenefitComponentId(this.getBenefitComponentSessionObject().getBenefitComponentId());
    		benefitComponentVO.setBenefitComponentName(this.getBenefitComponentSessionObject().getBenefitComponentName()); 
    		benefitComponentVO.setVersion(this.getBenefitComponentSessionObject().getBenefitComponentVersionNumber());
    		benefitComponentVO.setBusinessDomainList(this.getBenefitComponentSessionObject().getBusinessDomainList());
    		benefitComponentVO.setComponentType(this.getBenefitComponentSessionObject().getBcComponentType());
    		
    	} 
    	
    	//set values to request   	
    	benefitComponentViewRequest.setBenefitComponentVO(benefitComponentVO);
    	
    	// get the response
       	BenefitComponentViewResponse benefitComponentViewResponse = 
        	(BenefitComponentViewResponse)executeService(benefitComponentViewRequest);
       	
       	// get the bo from the response and set all the required values to backingbean
       	if(null != benefitComponentViewResponse){
    		if(null != benefitComponentViewResponse.getBenefitComponentBO()){    			    	    	   	    	
	    		this.setLob(WPDStringUtil.getTildaString(benefitComponentViewResponse.getBenefitComponentBO().getLineOfBusiness()));
	       		this.setBusinessEntity(WPDStringUtil.getTildaString(benefitComponentViewResponse.getBenefitComponentBO().getBusinessEntity()));
	    		this.setBusinessGroup(WPDStringUtil.getTildaString(benefitComponentViewResponse.getBenefitComponentBO().getBusinessGroup()));
	    		this.setMarketBusinessUnit(WPDStringUtil.getTildaString(benefitComponentViewResponse.getBenefitComponentBO().getMarketBusinessUnit()));
	    		this.setDescription(benefitComponentViewResponse.getBenefitComponentBO().getDescription());
	    		this.setCreatedUser(benefitComponentViewResponse.getBenefitComponentBO().getCreatedUser());
	    		this.setLastUpdatedUser(benefitComponentViewResponse.getBenefitComponentBO().getLastUpdatedUser());
	    		this.createdTimestamp = benefitComponentViewResponse.getBenefitComponentBO().getCreatedTimestamp();
    	    	this.lastUpdatedTimestamp = benefitComponentViewResponse.getBenefitComponentBO().getLastUpdatedTimestamp();
	      		this.setStatus(benefitComponentViewResponse.getBenefitComponentBO().getStatus());
	    		this.setVersion(benefitComponentViewResponse.getBenefitComponentBO().getVersion());
	    		this.setName(benefitComponentViewResponse.getBenefitComponentBO().getName());
	    		this.setBreadCrumbText("Product Configuration >> Benefit Component" + " (" + this.name + ") >> View");
	    		this.effectiveDate = WPDStringUtil.getStringDate(benefitComponentViewResponse.getBenefitComponentBO().getEffectiveDate());
	    		this.expiryDate = WPDStringUtil.getStringDate(benefitComponentViewResponse.getBenefitComponentBO().getExpiryDate());
	    		this.setState(benefitComponentViewResponse.getBenefitComponentBO().getState().getState());
	    		this.getBenefitComponentSessionObject().setComponentState(this.getState());
	    		this.getBenefitComponentSessionObject().setStatus(this.getStatus());
	    		this.getBenefitComponentSessionObject().setVersion(this.getVersion());
	    		// ** Enhancement
	    		this.setComponentType(benefitComponentViewResponse
                        .getBenefitComponentBO().getComponentType());
	    		
                if ("MANDATE".equals(this.getComponentType())) {                        	
                    this.setMandateType((benefitComponentViewResponse
                            .getBenefitComponentBO().getMandateType()));
                   
                	
                } else {
                    this.setMandateType("");
                }                    	
              
               
                if ("MANDATE".equals(this.getComponentType())
                        && "ST".equals(this.getMandateType())
                        || "ET".equals(this.getMandateType())) {
                    // To get the state
                    String stateid = benefitComponentViewResponse
                            .getBenefitComponentBO().getStateId();
                    String statedesc = benefitComponentViewResponse
                            .getBenefitComponentBO().getStateDesc();
                    String selectedStateId = stateid + "~" + statedesc;
                    this.setSelectedStateId(selectedStateId);
                    
                } else if((WebConstants.MNDT_TYPE).equals(this.componentType) && "FED".equals(this.mandateType)){
                    String selectedStateId = "ALL" + "~" + "ALL";
                    this.setSelectedStateId(selectedStateId);
                   
                } else{
                	String selectedStateId = " " + "~" + " ";
                    this.setSelectedStateId(selectedStateId);
                 
                }
                
                //To get the reference
                List refId = benefitComponentViewResponse
                        .getBenefitComponentBO().getRuleList();
                List refNam = benefitComponentViewResponse
                        .getBenefitComponentBO().getRuleNameList();
                String reference = convertListtoTilda(refNam, refId);
                this.ruleType = (String)benefitComponentViewResponse.getBenefitComponentBO().getRuleTypeList().get(0);
                for (int i = 0; i < refNam.size(); i++) {
                    this.setRuleId(reference);
                   // this.setRuleId(refId.get(i).toString()+'-'+refNam.get(i).toString());
                  }
                if("MANDATE".equals(this.componentType)){
	                if(("ST").equals(this.mandateType)){
	     				
	     				this.mandateType = "State";
	     	        	
	     	        }
	     			else if(("ET").equals(this.mandateType)){
	     				this.mandateType = "ExtraTerritorial";
	     			}
	     			else
	     				this.mandateType = "Federal";
                } 
                
                // Set value of componentType to session
                if(null != this.componentType){
                    this.getBenefitComponentSessionObject().setBcComponentType
                											(this.componentType);
                }   
// ** End	    		
    		}    	   		
    	}    	
		return viewBenefitComponentKey;
	}
	
	/**
     * @param keyString
     * @param keyVersion
     * @return
     */
//    private BenefitComponentBO retrieveBenefitComponent(String keyString, String keyVersion) {
//        // TODO Auto-generated method stub       	
//        return null;
//    }

    public String getComponentTypeTab() {
		 if(getBenefitComponentSessionObject().getBcComponentType().equals(WebConstants.STD_TYPE))
          return "Standard Definition";
      else
          return "Mandate Definition";
	}
	
	//converts List to tilda separated string
    public String convertListtoTilda(List idlist, List nameList) {
        if (idlist == null || nameList == null)
            return "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < idlist.size(); i++) {
            if (i != 0)
                buffer.append("~");
            buffer.append(idlist.get(i) + "~" + nameList.get(i));
        }
        return buffer.toString();
    }
    
	/**
	 * 
	 * @return BenefitComponentSessionObject
	 */
	protected BenefitComponentSessionObject getBenefitComponentSessionObject(){
		BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject)getSession().getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
		
		if(benefitComponentSessionObject == null) {
			benefitComponentSessionObject = new BenefitComponentSessionObject();
			getSession().setAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY,benefitComponentSessionObject);
		}
		return benefitComponentSessionObject;
	}
	/**
	 * 
	 * @return a String for view
	 */
	public String loadGeneralInformationView(){
		
		
		return "generalInformationView";	
	}
	
	/**
	 * @param viewBenefitComponentKey The viewBenefitComponentKey to set.
	 */
	public void setViewBenefitComponentKey(int viewBenefitComponentKey) {
		this.viewBenefitComponentKey = viewBenefitComponentKey;
	}
	/**
	 * @return Returns the bcCreatedBy.
	 */
	public String getBcCreatedBy() {
		return bcCreatedBy;
	}
	/**
	 * @param bcCreatedBy The bcCreatedBy to set.
	 */
	public void setBcCreatedBy(String bcCreatedBy) {
		this.bcCreatedBy = bcCreatedBy;
	}
	/**
	 * @return Returns the bcid.
	 */
	public String getBcid() {
		return bcid;
	}
	/**
	 * @param bcid The bcid to set.
	 */
	public void setBcid(String bcid) {
		this.bcid = bcid;
	}
	/**
	 * @return Returns the bcUpdateDate.
	 */
	public String getBcUpdateDate() {
		return bcUpdateDate;
	}
	/**
	 * @param bcUpdateDate The bcUpdateDate to set.
	 */
	public void setBcUpdateDate(String bcUpdateDate) {
		this.bcUpdateDate = bcUpdateDate;
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
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
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
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return Returns the effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @return Returns the expiryDate.
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return Returns the mandateType.
	 */
	public String getMandateType() {
		return mandateType;
	}
	/**
	 * @param mandateType The mandateType to set.
	 */
	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}
	/**
	 * @return Returns the ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}
	/**
	 * @param ruleId The ruleId to set.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * @return Returns the selectedStateId.
	 */
	public String getSelectedStateId() {
		return selectedStateId;
	}
	/**
	 * @param selectedStateId The selectedStateId to set.
	 */
	public void setSelectedStateId(String selectedStateId) {
		this.selectedStateId = selectedStateId;
	}
	/**
	 * @return Returns the componentType.
	 */
	public String getComponentType() {
		return componentType;
	}
	/**
	 * @param componentType The componentType to set.
	 */
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	/**
	 * @param componentTypeTab The componentTypeTab to set.
	 */
	public void setComponentTypeTab(String componentTypeTab) {
		this.componentTypeTab = componentTypeTab;
	}
   
    /**
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Product Configuration >> Benefit Component ("+this.getBenefitComponentSessionObject().getBenefitComponentName()+") >> Print";
        return printBreadCrumbText;
    }
    /**
     * @param printBreadCrumbText
     * 
     * Sets the printBreadCrumbText.
     */
    public void setPrintBreadCrumbText(String printBreadCrumbText) {
        this.printBreadCrumbText = printBreadCrumbText;
    }
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
	/**
	 * @return Returns the ruleType.
	 */
	public String getRuleType() {
		return ruleType;
	}
	/**
	 * @param ruleType The ruleType to set.
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
}
