/*
 * BenefitComponentSearchBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */

package com.wellpoint.wpd.web.benefitcomponent;

import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentApproveRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentCheckOutRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentDeleteRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentPublishRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentRejectRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentScheduleForTestRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentSearchRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentTestFailRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentTestPassRequest;
import com.wellpoint.wpd.common.benefitcomponent.request.BenefitComponentUnlockRequest;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentApproveResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentCheckOutResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentDeleteResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentPublishResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentRejectResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentScheduleForTestResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentSearchResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentTestFailResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentTestPassResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.BenefitComponentUnlockResponse;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentLocateCriteriaVO;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.benefitcomponent.vo.BenefitComponentVO;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.owasp.esapi.ESAPI;
import com.wellpoint.wpd.common.framework.util.StringUtil;

/**
 * Bean to search Benefit Component
 * This bean will bind with the jsp pages.
 * BenefitComponentSearchBackingBean contains the getters and setters of the 
 * variables and respective functions
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentSearchBackingBean extends WPDBackingBean {

    private int benefitComponentId = -1;
    
    private int benefitComponentParentId;

    private int version = -1;

    private String benefitComponentName;    
    

    private List searchResultList;

    private String name;

    private String lob;

    private String businessEntity;

    private String businessGroup;
    
    private String marketBusinessUnit;

    private String effectiveDate;

    private String expiryDate;
    
    private String fromPage;

    private List lobCodeList;

    private List businessEntityCodeList;

    private List businessGroupCodeList;
    
    private List marketBusinessUnitList;

    private List validationMessages;

    private List BusinessDomainList;
    
    private boolean checkOutFlag;
    
    private String breadCrumpViewAll;
    
    private String selectedBenefitComponentKey;
	private String selectedBenefitComponentName;
	private String selectedBenefitComponentVersion;
	
	private String componentType;
	
	private String benefitComponentSearchPrint;
	
	private boolean benefitComponentPrint;
	
	private String printBreadCrumbText;

    /**
     * @return benefitComponentPrint
     * 
     * Returns the benefitComponentPrint.
     */
    public boolean isBenefitComponentPrint() {
        return benefitComponentPrint;
    }
    /**
     * @param benefitComponentPrint
     * 
     * Sets the benefitComponentPrint.
     */
    public void setBenefitComponentPrint(boolean benefitComponentPrint) {
        this.benefitComponentPrint = benefitComponentPrint;
    }
    /**
     * @return benefitComponentSearchPrint
     * 
     * Returns the benefitComponentSearchPrint.
     */
    public String getBenefitComponentSearchPrint() {
        
        this.setBenefitComponentPrint(true);
        BenefitComponentSearchResponse benefitComponentSearchResponse = (BenefitComponentSearchResponse) this
		        .executeService(getBenefitComponentSearchRequest());
		if (null != benefitComponentSearchResponse) {
		    if (null != benefitComponentSearchResponse
		            .getBenefitComponentList()
		            && benefitComponentSearchResponse
		                    .getBenefitComponentList().size() > 0) {
		        this.setSearchResultList(benefitComponentSearchResponse
		                .getBenefitComponentList());
		        if (searchResultList != null && searchResultList.size() > 0) {
		            this.setSearchResultToSession(searchResultList);
		        }
		    } else {
		        validationMessages.add(new InformationalMessage(
		                WebConstants.SEARCH_RESULT_NOT_FOUND));
		        addAllMessagesToRequest(validationMessages);
		    }
		}
        return benefitComponentSearchPrint;
    }
    /**
     * @param benefitComponentSearchPrint
     * 
     * Sets the benefitComponentSearchPrint.
     */
    public void setBenefitComponentSearchPrint(
            String benefitComponentSearchPrint) {
        this.benefitComponentSearchPrint = benefitComponentSearchPrint;
    }
	/**
	 * Constructor
	 *
	 */
    public BenefitComponentSearchBackingBean() {
        super();
        this.setBreadCrump();
        validationMessages = new ArrayList(10);
    }    
    
    /**
     * DELETE:Method to delete Benefit Component 
	 * From: benefitComponentSearch.jsp 
	 * To:execute(BenefitComponentDeleteRequest) in benefitComponentBusinessService
	 * Create BenefitComponentDeleteRequest and set the key values( and domain values) to the request
	 * and get the response.After obtaining the response, call the performSearch method to refresh the 
	 * searchResults page.
	 */
	public String deleteBenefitComponent(){		
		BenefitComponentDeleteRequest benefitComponentDeleteRequest =(BenefitComponentDeleteRequest)this.getServiceRequest(ServiceManager.DELETE_BENEFIT_COMPONENT_REQUEST);
		
		int retrieveKey = Integer.parseInt(this.selectedBenefitComponentKey);
		benefitComponentDeleteRequest.setBenefitComponentKey(retrieveKey);
		benefitComponentDeleteRequest.setBenefitComponentName(this.selectedBenefitComponentName);
		benefitComponentDeleteRequest.setBenefitComponentVersion(Integer.parseInt(this.selectedBenefitComponentVersion));
				
		   List searchResultList = (List)getSession().getAttribute("benefitComponentSearchResult");
		    if(null != searchResultList && !searchResultList.isEmpty()){
		    	for(int i = 0; i < searchResultList.size(); i++){
		    		BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList.get(i);
		    		if(retrieveKey == benefitComponentBO.getBenefitComponentId()){
		    			this.getBenefitComponentSessionObject().
							setBusinessDomainList(benefitComponentBO.getBusinessDomainList());
		    			List domainList = benefitComponentBO.getBusinessDomainList();
		    			benefitComponentDeleteRequest.setDomainList(domainList);
		    			break;
		    		}
		    	}
		    }

		BenefitComponentDeleteResponse benefitComponentDeleteResponse = (BenefitComponentDeleteResponse)executeService(benefitComponentDeleteRequest);
		if (null != benefitComponentDeleteResponse) {
				if(!(WebConstants.VIEW.equals(this.fromPage))){
   	 	    		this.performSearch();
				}
    			addAllMessagesToRequest(benefitComponentDeleteResponse.getMessages());
        	}  	 	
		
		return "";
	}
	/**
     * DELETE:Method to delete Benefit Component 
	 * From: benefitComponentSearch.jsp 
	 * To:execute(BenefitComponentDeleteRequest) in benefitComponentBusinessService
	 * Create BenefitComponentDeleteRequest and set the key values( and domain values) to the request
	 * and get the response.After obtaining the response, call the performSearch method to refresh the 
	 * searchResults page.
	 */
	public String deleteBenefitComponentVersion(){		
		BenefitComponentDeleteRequest benefitComponentDeleteRequest =(BenefitComponentDeleteRequest)this.getServiceRequest(ServiceManager.DELETE_BENEFIT_COMPONENT_REQUEST);
		
		int retrieveKey = Integer.parseInt(this.selectedBenefitComponentKey);
		benefitComponentDeleteRequest.setBenefitComponentKey(retrieveKey);
		benefitComponentDeleteRequest.setBenefitComponentName(this.selectedBenefitComponentName);
		benefitComponentDeleteRequest.setBenefitComponentVersion(Integer.parseInt(this.selectedBenefitComponentVersion));
				
		   List searchResultList = (List)getSession().getAttribute("benefitComponentViewAllSearchResult");
		    if(null != searchResultList && !searchResultList.isEmpty()){
		    	for(int i = 0; i < searchResultList.size(); i++){
		    		BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList.get(i);
		    		if(retrieveKey == benefitComponentBO.getBenefitComponentId()){
		    			this.getBenefitComponentSessionObject().
							setBusinessDomainList(benefitComponentBO.getBusinessDomainList());
		    			List domainList = benefitComponentBO.getBusinessDomainList();
		    			benefitComponentDeleteRequest.setDomainList(domainList);
		    			break;
		    		}
		    	}
		    }
		getRequest().setAttribute("benefitComName",this.benefitComponentName);
		BenefitComponentDeleteResponse benefitComponentDeleteResponse = (BenefitComponentDeleteResponse)executeService(benefitComponentDeleteRequest);
		if (null != benefitComponentDeleteResponse) {
				if(!(WebConstants.VIEW.equals(this.fromPage))){
   	 	    		this.performSearch();
				}
    			addAllMessagesToRequest(benefitComponentDeleteResponse.getMessages());
        	}  	 	
		
		return "";
	}
	
	/**
	 * Method to search Benefit Component
	 * @return String
	 */
	public String performSearch() {
    	//checks if all fields are blank
        if (!isAllFieldsBlank()) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.ATLEAST_ONE_SEARCH));
            addAllMessagesToRequest(validationMessages);
        }
        //checks for date format
		 else if ( !(effectiveDate.trim().equals("")) && !StringUtil.isDate(effectiveDate)) {
		 	ErrorMessage errorMessage = new ErrorMessage(
                    WebConstants.INPUT_FORMAT_INVALID);
            errorMessage.setParameters(new String[] { "Effective Date" });
            validationMessages.add(errorMessage);
            addAllMessagesToRequest(validationMessages);
		 }
        //checks for effective date boundary
		 else if (!(expiryDate.trim().equals("")) && !StringUtil.isDate(expiryDate)) {
		 	 ErrorMessage errorMessage = new ErrorMessage(
                    WebConstants.INPUT_FORMAT_INVALID);
            errorMessage.setParameters(new String[] { "Expiry Date" });
            validationMessages.add(errorMessage);
            addAllMessagesToRequest(validationMessages);
        }else {
            //extractTildtoList();
            this.lobCodeList = WPDStringUtil.getListFromTildaString(this
                    .getLob(), 2, 2, 2);
            this.businessEntityCodeList = WPDStringUtil.getListFromTildaString(
                    this.getBusinessEntity(), 2, 2, 2);
            this.businessGroupCodeList = WPDStringUtil.getListFromTildaString(
                    this.getBusinessGroup(), 2, 2, 2);
            this.marketBusinessUnitList =  WPDStringUtil.getListFromTildaString(
                    this.getMarketBusinessUnit(), 2, 2, 2);            
            //performs search
            BenefitComponentSearchResponse benefitComponentSearchResponse = (BenefitComponentSearchResponse) this
                    .executeService(getBenefitComponentSearchRequest());
            if (null != benefitComponentSearchResponse) {
                if (null != benefitComponentSearchResponse
                        .getBenefitComponentList()
                        && benefitComponentSearchResponse
                                .getBenefitComponentList().size() > 0) {
                    this.setSearchResultList(benefitComponentSearchResponse
                            .getBenefitComponentList());
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
	                        	List lobList=null;
	                        	lobList=domainDetail.getLineOfBusiness();
	                        	for(int j=0;j<lobList.size();j++){
	                        		DomainItem domainItem=(DomainItem)lobList.get(j);
	                        		benefitComponentBO.setLob(domainItem.getItemDesc());
	                        		benefitComponentBO.setLob(WPDStringUtil.getTildaString(domainDetail.getLineOfBusiness()));
	                        	}
	                        } 
	                  }
                        
               if (searchResultList != null && searchResultList.size() > 0) {
	                        this.setSearchResultToSession(searchResultList);
                        	}                  
                } else {
                    validationMessages.add(new InformationalMessage(
                            WebConstants.SEARCH_RESULT_NOT_FOUND));
                    addAllMessagesToRequest(validationMessages);
                }
            }
        }
        this.setBreadCrump();
        return "";
    }


    /**
     * Method to check Out Benefit Component
     * 
     * @return
     */
    public String checkOutBenefitComponentVersion() {
    	checkOutFlag = true;
        String returnString;        
        BenefitComponentCheckOutRequest benefitComponentCheckOutRequest = null;
        BenefitComponentCheckOutResponse benefitComponentCheckOutResponse = null;

        //Creating request object
        benefitComponentCheckOutRequest = (BenefitComponentCheckOutRequest) this
                .getServiceRequest(ServiceManager.CHECKOUT_BENEFIT_COMPONENT_REQUEST);

        List searchResultList = (List) getSession().getAttribute(
                "benefitComponentViewAllSearchResult");
        removeValueInSession("benefitComponentViewAllSearchResult");
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList
                        .get(i);
                if (this.benefitComponentId == benefitComponentBO
                        .getBenefitComponentId()) {
                    this.setBusinessDomainList(benefitComponentBO
                            .getBusinessDomainList());
                    break;
                }
            }
        }
        //Copying the Benefit Component details to the VO in the request.
        benefitComponentCheckOutRequest.getBenefitComponentVO()
                .setBenefitComponentId(this.benefitComponentId);
        benefitComponentCheckOutRequest.getBenefitComponentVO()
                .setBenefitComponentName(this.benefitComponentName);
        benefitComponentCheckOutRequest.getBenefitComponentVO().setVersion(
                this.version);
        benefitComponentCheckOutRequest.getBenefitComponentVO()
                .setBusinessDomainList(this.BusinessDomainList);
       
        benefitComponentCheckOutRequest.getBenefitComponentVO().setBenefitComponentParentId(this.benefitComponentParentId);

        //Creating the response object
        benefitComponentCheckOutResponse = (BenefitComponentCheckOutResponse) this
                .executeService(benefitComponentCheckOutRequest);
        
// Modified code - Jan 14 2007
        if (null != benefitComponentCheckOutResponse) {
            this.setValidationMessages(benefitComponentCheckOutResponse
                    .getMessages());
            if (benefitComponentCheckOutResponse.isErrorFlag()) {
                returnString = this.performSearch();
                addAllMessagesToRequest(validationMessages);
                return returnString;
            } else {
                BenefitComponentVO benefitComponentVO = benefitComponentCheckOutResponse
                        .getBenefitComponentVO();
                Application application = FacesContext.getCurrentInstance()
                        .getApplication();
                BenefitComponentCreateBackingBean benefitComponentCreateBackingBean = ((BenefitComponentCreateBackingBean) application
                        .createValueBinding(
                                "#{benefitComponentCreateBackingBean}")
                        .getValue(FacesContext.getCurrentInstance()));
                benefitComponentCreateBackingBean
                        .setBenefitComponentName(benefitComponentVO
                                .getBenefitComponentName());
                benefitComponentCreateBackingBean
                        .setBenefitComponentId(benefitComponentVO
                                .getBenefitComponentId());
                benefitComponentCreateBackingBean.setVersion(benefitComponentVO
                        .getVersion());
                benefitComponentCreateBackingBean.setBenefitComponentParentId(this.benefitComponentParentId);
                benefitComponentCreateBackingBean
                        .setSelectedDomainList(benefitComponentVO
                                .getBusinessDomainList());                
//Enhancement
                benefitComponentCreateBackingBean.setComponentType(benefitComponentVO.getComponentType());
                
                if ("MANDATE".equals(benefitComponentCreateBackingBean.getComponentType())) {            	
                	benefitComponentCreateBackingBean.setMandateType(benefitComponentVO
                            .getMandateType());
                } else {
                	benefitComponentCreateBackingBean.setMandateType("");
                }
                
                if ("MANDATE".equals(benefitComponentCreateBackingBean.getComponentType())
                        && "ST".equals(benefitComponentCreateBackingBean.getMandateType())
                        || "ET".equals(benefitComponentCreateBackingBean.getMandateType())) {
                    // To get the state
                    String stateid = benefitComponentVO.getStateId();
                    String statedesc = benefitComponentVO.getStateDesc();
                    
                    String selectedStateId = stateid + "~" + statedesc;
                    benefitComponentCreateBackingBean.setSelectedStateId(selectedStateId);
                } else {
                    String selectedStateId = " " + "~" + " ";
                    benefitComponentCreateBackingBean.setSelectedStateId(selectedStateId);
                }
                
                List refId = benefitComponentVO.getRuleIdList();
                List refNam = benefitComponentVO
                        .getRuleNameList();
                String reference = convertListtoTilda(refNam, refId);
                benefitComponentCreateBackingBean.setRuleId(reference);
//End - Enhancement                
                benefitComponentCreateBackingBean
                        .setValidationMessages(validationMessages);
                return benefitComponentCreateBackingBean
                        .loadBenefitComponentAfterCheckOut();
            }

        }
   //     validationMessageForCheckOut.add("")
        return this.performSearch();
        
    }

    /**
     * Method to check Out Benefit Component
     * 
     * @return
     */
    public String checkOutBenefitComponent() {
    	checkOutFlag = true;
        String returnString;
// Added -  Jan 14 2008         
        List validationMessagesList;
        BenefitComponentCheckOutRequest benefitComponentCheckOutRequest = null;
        BenefitComponentCheckOutResponse benefitComponentCheckOutResponse = null;

        //Creating request object
        benefitComponentCheckOutRequest = (BenefitComponentCheckOutRequest) this
                .getServiceRequest(ServiceManager.CHECKOUT_BENEFIT_COMPONENT_REQUEST);

        List searchResultList = (List) getSession().getAttribute(
                "benefitComponentSearchResult");
        removeValueInSession("benefitComponentSearchResult");
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList
                        .get(i);
                if (this.benefitComponentId == benefitComponentBO
                        .getBenefitComponentId()) {
                    this.setBusinessDomainList(benefitComponentBO
                            .getBusinessDomainList());
                    break;
                }
            }
        }
        //Copying the Benefit Component details to the VO in the request.
        benefitComponentCheckOutRequest.getBenefitComponentVO()
                .setBenefitComponentId(this.benefitComponentId);
        benefitComponentCheckOutRequest.getBenefitComponentVO()
                .setBenefitComponentName(this.benefitComponentName);
        benefitComponentCheckOutRequest.getBenefitComponentVO().setVersion(
                this.version);
        benefitComponentCheckOutRequest.getBenefitComponentVO()
                .setBusinessDomainList(this.BusinessDomainList);
       
        benefitComponentCheckOutRequest.getBenefitComponentVO().setBenefitComponentParentId(this.benefitComponentParentId);

        //Creating the response object
        benefitComponentCheckOutResponse = (BenefitComponentCheckOutResponse) this
                .executeService(benefitComponentCheckOutRequest);

        if (null != benefitComponentCheckOutResponse) {
            this.setValidationMessages(benefitComponentCheckOutResponse
                    .getMessages());
            if (benefitComponentCheckOutResponse.isErrorFlag()) {
                returnString = this.performSearch();
                addAllMessagesToRequest(validationMessages);
                return returnString;
            } else {
                BenefitComponentVO benefitComponentVO = benefitComponentCheckOutResponse
                        .getBenefitComponentVO();
                Application application = FacesContext.getCurrentInstance()
                        .getApplication();
                BenefitComponentCreateBackingBean benefitComponentCreateBackingBean = ((BenefitComponentCreateBackingBean) application
                        .createValueBinding(
                                "#{benefitComponentCreateBackingBean}")
                        .getValue(FacesContext.getCurrentInstance()));
                benefitComponentCreateBackingBean
                        .setBenefitComponentName(benefitComponentVO
                                .getBenefitComponentName());
                benefitComponentCreateBackingBean
                        .setBenefitComponentId(benefitComponentVO
                                .getBenefitComponentId());
                benefitComponentCreateBackingBean.setVersion(benefitComponentVO
                        .getVersion());
                benefitComponentCreateBackingBean.setBenefitComponentParentId(this.benefitComponentParentId);
                benefitComponentCreateBackingBean
                        .setSelectedDomainList(benefitComponentVO
                                .getBusinessDomainList());                
//Enhancement
                benefitComponentCreateBackingBean.setComponentType(benefitComponentVO.getComponentType());
                
                if ("MANDATE".equals(benefitComponentCreateBackingBean.getComponentType())) {            	
                	benefitComponentCreateBackingBean.setMandateType(benefitComponentVO
                            .getMandateType());
                } else {
                	benefitComponentCreateBackingBean.setMandateType("");
                }
                
                if ("MANDATE".equals(benefitComponentCreateBackingBean.getComponentType())
                        && "ST".equals(benefitComponentCreateBackingBean.getMandateType())
                        || "ET".equals(benefitComponentCreateBackingBean.getMandateType())) {
                    // To get the state
                    String stateid = benefitComponentVO.getStateId();
                    String statedesc = benefitComponentVO.getStateDesc();
                    
                    String selectedStateId = stateid + "~" + statedesc;
                    benefitComponentCreateBackingBean.setSelectedStateId(selectedStateId);
                } else {
                    String selectedStateId = " " + "~" + " ";
                    benefitComponentCreateBackingBean.setSelectedStateId(selectedStateId);
                }
                
                List refId = benefitComponentVO.getRuleIdList();
                List refNam = benefitComponentVO
                        .getRuleNameList();
                String reference = convertListtoTilda(refNam, refId);
                benefitComponentCreateBackingBean.setRuleId(reference);
//End - Enhancement                
                benefitComponentCreateBackingBean
                        .setValidationMessages(validationMessages);
                return benefitComponentCreateBackingBean
                        .loadBenefitComponentAfterCheckOut();
            }

        }
 // addition - Jan 14 2008   
        ErrorMessage message = new ErrorMessage(WebConstants.BENEFIT_COMPONENT_CHECKEDOUT_FAILURE);
        validationMessages.add(message) ;        
        this.performSearch();
        addAllMessagesToRequest(validationMessages);
        return "";
// end - addition        

    }
    // converts List to tilda separated string
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
	 * Returns the checkOutFlag
	 * @return boolean checkOutFlag.
	 */
	public boolean isCheckOutFlag() {
		return checkOutFlag;
	}
	/**
	 * Sets the checkOutFlag
	 * @param checkOutFlag.
	 */
	public void setCheckOutFlag(boolean checkOutFlag) {
		this.checkOutFlag = checkOutFlag;
	}
    /**
     * Method to Publish Benefit Component.
     * 
     * @return
     */
    public String publishBenefitComponent() {

        String returnString = WebConstants.EMPTY_STRING;
        BenefitComponentPublishRequest benefitComponentPublishRequest = null;
        BenefitComponentPublishResponse benefitComponentPublishResponse = null;

        //Creating request object
        benefitComponentPublishRequest = (BenefitComponentPublishRequest) this
                .getServiceRequest(ServiceManager.PUBLISH_BENEFIT_COMPONENT_REQUEST);

        //Getting the Busiess Domain details from the session
        List searchResultList = (List) getSession().getAttribute(
                "benefitComponentSearchResult");
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList
                        .get(i);
                if (this.benefitComponentId == benefitComponentBO
                        .getBenefitComponentId()) {
                    this.setBusinessDomainList(benefitComponentBO
                            .getBusinessDomainList());
                    break;
                }
            }
        }
        //Copying the Benefit Component details to the VO in the request.
        benefitComponentPublishRequest.getBenefitComponentVO()
                .setBenefitComponentId(this.benefitComponentId);
        benefitComponentPublishRequest.getBenefitComponentVO()
                .setBenefitComponentName(this.benefitComponentName);
        benefitComponentPublishRequest.getBenefitComponentVO().setVersion(
                this.version);
        benefitComponentPublishRequest.getBenefitComponentVO()
                .setBusinessDomainList(this.BusinessDomainList);

        //Creating the response object
        benefitComponentPublishResponse = (BenefitComponentPublishResponse) this
                .executeService(benefitComponentPublishRequest);

        if (null != benefitComponentPublishResponse) {
            this.setValidationMessages(benefitComponentPublishResponse
                    .getMessages());
            if (!(WebConstants.VIEW.equals(this.fromPage))){
            	returnString = this.performSearch();
            }
            addAllMessagesToRequest(validationMessages);
            return returnString;

        }
        return this.performSearch();

    }


    /**
     * Method to Schedule For Test.
     * 
     * @return
     */
    public String scheduleForTestBenefitComponent() {

        String returnString = WebConstants.EMPTY_STRING;
        BenefitComponentScheduleForTestRequest benefitComponentScheduleForTestRequest = null;
        BenefitComponentScheduleForTestResponse benefitComponentScheduleForTestResponse = null;

        //Creating request object
        benefitComponentScheduleForTestRequest = (BenefitComponentScheduleForTestRequest) this
                .getServiceRequest(ServiceManager.SCHEDULE_TEST_BENEFIT_COMPONENT_REQUEST);

        //Getting the Busiess Domain details from the session
        List searchResultList = (List) getSession().getAttribute(
                "benefitComponentSearchResult");
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList
                        .get(i);
                if (this.benefitComponentId == benefitComponentBO
                        .getBenefitComponentId()) {
                    this.setBusinessDomainList(benefitComponentBO
                            .getBusinessDomainList());
                    break;
                }
            }
        }
        //Copying the Benefit Component details to the VO in the request.
        benefitComponentScheduleForTestRequest.getBenefitComponentVO()
                .setBenefitComponentId(this.benefitComponentId);
        benefitComponentScheduleForTestRequest.getBenefitComponentVO()
                .setBenefitComponentName(this.benefitComponentName);
        benefitComponentScheduleForTestRequest.getBenefitComponentVO()
                .setVersion(this.version);
        benefitComponentScheduleForTestRequest.getBenefitComponentVO()
                .setBusinessDomainList(this.BusinessDomainList);

        //Creating the response object
        benefitComponentScheduleForTestResponse = (BenefitComponentScheduleForTestResponse) this
                .executeService(benefitComponentScheduleForTestRequest);

        if (null != benefitComponentScheduleForTestResponse) {
            this.setValidationMessages(benefitComponentScheduleForTestResponse
                    .getMessages());
            if(!(WebConstants.VIEW.equals(this.fromPage))){
            returnString = this.performSearch();
            }
            addAllMessagesToRequest(validationMessages);
            return returnString;

        }
        return this.performSearch();

    }


    /**
     * Method to Test Pass.
     * 
     * @return
     */
    public String testPassBenefitComponent() {

        String returnString = WebConstants.EMPTY_STRING;
        BenefitComponentTestPassRequest benefitComponentTestPassRequest = null;
        BenefitComponentTestPassResponse benefitComponentTestPassResponse = null;

        //Creating request object
        benefitComponentTestPassRequest = (BenefitComponentTestPassRequest) this
                .getServiceRequest(ServiceManager.TEST_PASS_BENEFIT_COMPONENT_REQUEST);

        //Getting the Busiess Domain details from the session
        List searchResultList = (List) getSession().getAttribute(
                "benefitComponentSearchResult");
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList
                        .get(i);
                if (this.benefitComponentId == benefitComponentBO
                        .getBenefitComponentId()) {
                    this.setBusinessDomainList(benefitComponentBO
                            .getBusinessDomainList());
                    break;
                }
            }
        }
        //Copying the Benefit Component details to the VO in the request.
        benefitComponentTestPassRequest.getBenefitComponentVO()
                .setBenefitComponentId(this.benefitComponentId);
        benefitComponentTestPassRequest.getBenefitComponentVO()
                .setBenefitComponentName(this.benefitComponentName);
        benefitComponentTestPassRequest.getBenefitComponentVO().setVersion(
                this.version);
        benefitComponentTestPassRequest.getBenefitComponentVO()
                .setBusinessDomainList(this.BusinessDomainList);

        //Creating the response object
        benefitComponentTestPassResponse = (BenefitComponentTestPassResponse) this
                .executeService(benefitComponentTestPassRequest);

        if (null != benefitComponentTestPassResponse) {
            this.setValidationMessages(benefitComponentTestPassResponse
                    .getMessages());
            if(!(WebConstants.VIEW.equals(this.fromPage))){
            	returnString = this.performSearch();
            }
            addAllMessagesToRequest(validationMessages);
            return returnString;

        }
        return this.performSearch();

    }

    
    /**
     * Method to Test Fail.
     * 
     * @return String
     */
    public String testFailBenefitComponent() {

        String returnString = WebConstants.EMPTY_STRING;
        BenefitComponentTestFailRequest benefitComponentTestFailRequest = null;
        BenefitComponentTestFailResponse benefitComponentTestFailResponse = null;

        //Creating request object
        benefitComponentTestFailRequest = (BenefitComponentTestFailRequest) this
                .getServiceRequest(ServiceManager.TEST_FAIL_BENEFIT_COMPONENT_REQUEST);

        //Getting the Busiess Domain details from the session
        List searchResultList = (List) getSession().getAttribute(
                "benefitComponentSearchResult");
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList
                        .get(i);
                if (this.benefitComponentId == benefitComponentBO
                        .getBenefitComponentId()) {
                    this.setBusinessDomainList(benefitComponentBO
                            .getBusinessDomainList());
                    break;
                }
            }
        }
        //Copying the Benefit Component details to the VO in the request.
        benefitComponentTestFailRequest.getBenefitComponentVO()
                .setBenefitComponentId(this.benefitComponentId);
        benefitComponentTestFailRequest.getBenefitComponentVO()
                .setBenefitComponentName(this.benefitComponentName);
        benefitComponentTestFailRequest.getBenefitComponentVO().setVersion(
                this.version);
        benefitComponentTestFailRequest.getBenefitComponentVO()
                .setBusinessDomainList(this.BusinessDomainList);

        //Creating the response object
        benefitComponentTestFailResponse = (BenefitComponentTestFailResponse) this
                .executeService(benefitComponentTestFailRequest);

        if (null != benefitComponentTestFailResponse) {
            this.setValidationMessages(benefitComponentTestFailResponse
                    .getMessages());
            if(!(WebConstants.VIEW.equals(this.fromPage))){
            	returnString = this.performSearch();
            }
            addAllMessagesToRequest(validationMessages);
            return returnString;

        }
        return this.performSearch();

    }

    /**
     * Method to check is any one search criteria entered.
     * @return boolean
     */
    private boolean isAllFieldsBlank() {
        if ((null == name || "".equals(name.trim()))
                && (null == businessGroup || "".equals(businessGroup.trim()))
				&& (null == marketBusinessUnit || "".equals(marketBusinessUnit.trim()))
                && (null == effectiveDate || "".equals(effectiveDate.trim()))
                && (null == expiryDate || "".equals(expiryDate.trim()))
                && (null == businessEntity || "".equals(businessEntity.trim()))
                && (null == lob || "".equals(lob.trim())))
            return false;
        return true;
    }

    /**
     * gets benefitcomponent search
     * @return benefitComponentSearchRequest
     */
    private BenefitComponentSearchRequest getBenefitComponentSearchRequest() {
    	//gets request object
        BenefitComponentSearchRequest benefitComponentSearchRequest = (BenefitComponentSearchRequest) this
                .getServiceRequest(ServiceManager.SEARCH_BENEFIT_COMPONENT_REQUEST);
        //gets BenefitComponentLocateCriteriaVO object and sets values to it
        BenefitComponentLocateCriteriaVO benefitComponentLocateCriteriaVO = new BenefitComponentLocateCriteriaVO();
        benefitComponentLocateCriteriaVO.setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
        if(!isBenefitComponentPrint()){
            benefitComponentLocateCriteriaVO
		            .setBusinessEntity(businessEntityCodeList);
		    benefitComponentLocateCriteriaVO
		            .setBusinessGroup(businessGroupCodeList);
		    benefitComponentLocateCriteriaVO.setMarketBusinessUnit(marketBusinessUnitList);
		    benefitComponentLocateCriteriaVO.setLob(lobCodeList);
		    benefitComponentLocateCriteriaVO.setEffeciveDate(effectiveDate.trim());
		    benefitComponentLocateCriteriaVO.setExpiryDate(expiryDate.trim());
		    benefitComponentLocateCriteriaVO.setName(name.trim());
		    benefitComponentSearchRequest
		    		.setBenefitComponentLocateCriteriaVO(benefitComponentLocateCriteriaVO);
		    getRequest().getSession().removeAttribute("benefitComponentSearchCriteriaVO");
            getRequest().getSession().setAttribute("benefitComponentSearchCriteriaVO",benefitComponentSearchRequest.getBenefitComponentLocateCriteriaVO());
			
        }else{
            if(null != getRequest().getSession().getAttribute("benefitComponentSearchCriteriaVO")){
                benefitComponentSearchRequest.setBenefitComponentLocateCriteriaVO((BenefitComponentLocateCriteriaVO)getRequest().getSession().getAttribute("benefitComponentSearchCriteriaVO"));
    		}
        }
        return benefitComponentSearchRequest;
    }
    
    /**
     * Method to Test Pass.
     * 
     * @return
     */
    public String approveBenefitComponent() {

    	 String returnString = WebConstants.EMPTY_STRING;
         BenefitComponentApproveRequest benefitComponentApproveRequest = null;
         BenefitComponentApproveResponse benefitComponentApproveResponse = null;

         //Creating request object
         benefitComponentApproveRequest = (BenefitComponentApproveRequest) this
                 .getServiceRequest(ServiceManager.APPROVE_BENEFIT_COMPONENT_REQUEST);

         //Getting the Busiess Domain details from the session
         List searchResultList = (List) getSession().getAttribute(
                 "benefitComponentSearchResult");
         if (null != searchResultList && !searchResultList.isEmpty()) {
             for (int i = 0; i < searchResultList.size(); i++) {
                 BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList
                         .get(i);
                	if (this.benefitComponentId == benefitComponentBO
                        .getBenefitComponentId()) {
                     this.setBusinessDomainList(benefitComponentBO
                             .getBusinessDomainList());
                	break;
                 }
             }
         }
         //Copying the Benefit Component details to the VO in the request.
         benefitComponentApproveRequest.getBenefitComponentVO()
                 .setBenefitComponentId(this.benefitComponentId);
         benefitComponentApproveRequest.getBenefitComponentVO()
                 .setBenefitComponentName(this.benefitComponentName);
         benefitComponentApproveRequest.getBenefitComponentVO()
                 .setVersion(this.version);
         benefitComponentApproveRequest.getBenefitComponentVO()
                 .setBusinessDomainList(this.BusinessDomainList);

         //Creating the response object
         benefitComponentApproveResponse = (BenefitComponentApproveResponse) this
                 .executeService(benefitComponentApproveRequest);

         if (null != benefitComponentApproveResponse) {
             this.setValidationMessages(benefitComponentApproveResponse
                     .getMessages());
             if(!(WebConstants.VIEW.equals(this.fromPage))){
             returnString = this.performSearch();
             }
             addAllMessagesToRequest(validationMessages);
             return returnString;

         }
         return this.performSearch();

    }
    /**
     * Method to Test Pass from view All vwesion list.
     * 
     * @return
     */
    public String approveBenefitComponentFromViewAll() {

    	 String returnString = WebConstants.EMPTY_STRING;
         BenefitComponentApproveRequest benefitComponentApproveRequest = null;
         BenefitComponentApproveResponse benefitComponentApproveResponse = null;

         //Creating request object
         benefitComponentApproveRequest = (BenefitComponentApproveRequest) this
                 .getServiceRequest(ServiceManager.APPROVE_BENEFIT_COMPONENT_REQUEST);

         //Getting the Busiess Domain details from the session
         List searchResultList = (List) getSession().getAttribute(
                 "benefitComponentViewAllSearchResult");
         if (null != searchResultList && !searchResultList.isEmpty()) {
             for (int i = 0; i < searchResultList.size(); i++) {
                 BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList
                         .get(i);
                	if (this.benefitComponentId == benefitComponentBO
                        .getBenefitComponentId()) {
                     this.setBusinessDomainList(benefitComponentBO
                             .getBusinessDomainList());
                	break;
                 }
             }
         }
         //Copying the Benefit Component details to the VO in the request.
         benefitComponentApproveRequest.getBenefitComponentVO()
                 .setBenefitComponentId(this.benefitComponentId);
         benefitComponentApproveRequest.getBenefitComponentVO()
                 .setBenefitComponentName(this.benefitComponentName);
         benefitComponentApproveRequest.getBenefitComponentVO()
                 .setVersion(this.version);
         benefitComponentApproveRequest.getBenefitComponentVO()
                 .setBusinessDomainList(this.BusinessDomainList);

         //Creating the response object
         benefitComponentApproveResponse = (BenefitComponentApproveResponse) this
                 .executeService(benefitComponentApproveRequest);
         getRequest().setAttribute("benefitComName",this.benefitComponentName);
         if (null != benefitComponentApproveResponse) {
             this.setValidationMessages(benefitComponentApproveResponse
                     .getMessages());
             if(!(WebConstants.VIEW.equals(this.fromPage) || WebConstants.VIEW_ALL.equals(this.fromPage))){
             returnString = this.performSearch();
             }
             addAllMessagesToRequest(validationMessages);
             return returnString;

         }
         return this.performSearch();

    }
    /**
     * Method to Test Pass.
     * 
     * @return
     */
    public String rejectBenefitComponent() {

    	 String returnString = WebConstants.EMPTY_STRING;
         BenefitComponentRejectRequest benefitComponentRejectRequest = null;
         BenefitComponentRejectResponse benefitComponentRejectResponse = null;

         //Creating request object
         benefitComponentRejectRequest = (BenefitComponentRejectRequest) this
                 .getServiceRequest(ServiceManager.REJECT_BENEFIT_COMPONENT_REQUEST);

         //Getting the Busiess Domain details from the session
         List searchResultList = (List) getSession().getAttribute(
                 "benefitComponentSearchResult");
         if (null != searchResultList && !searchResultList.isEmpty()) {
             for (int i = 0; i < searchResultList.size(); i++) {
                 BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList
                         .get(i);
                 if (this.benefitComponentId == benefitComponentBO
                         .getBenefitComponentId()) {
                     this.setBusinessDomainList(benefitComponentBO
                             .getBusinessDomainList());
                     break;
                 }
             }
         }
         //Copying the Benefit Component details to the VO in the request.
         benefitComponentRejectRequest.getBenefitComponentVO()
                 .setBenefitComponentId(this.benefitComponentId);
         benefitComponentRejectRequest.getBenefitComponentVO()
                 .setBenefitComponentName(this.benefitComponentName);
         benefitComponentRejectRequest.getBenefitComponentVO()
                 .setVersion(this.version);
         benefitComponentRejectRequest.getBenefitComponentVO()
                 .setBusinessDomainList(this.BusinessDomainList);

         //Creating the response object
         benefitComponentRejectResponse = (BenefitComponentRejectResponse) this
                 .executeService(benefitComponentRejectRequest);

         if (null != benefitComponentRejectResponse) {
             this.setValidationMessages(benefitComponentRejectResponse
                     .getMessages());
             if(!(WebConstants.VIEW.equals(this.fromPage))){
             returnString = this.performSearch();
             }
             addAllMessagesToRequest(validationMessages);
             return returnString;

         }
         return this.performSearch();


    }
    /**
     * Method to Test reject form View All version.
     * 
     * @return
     */
    public String rejectBenefitComponentViewAll() {

    	 String returnString = WebConstants.EMPTY_STRING;
         BenefitComponentRejectRequest benefitComponentRejectRequest = null;
         BenefitComponentRejectResponse benefitComponentRejectResponse = null;

         //Creating request object
         benefitComponentRejectRequest = (BenefitComponentRejectRequest) this
                 .getServiceRequest(ServiceManager.REJECT_BENEFIT_COMPONENT_REQUEST);

         //Getting the Busiess Domain details from the session
         List searchResultList = (List) getSession().getAttribute(
                 "benefitComponentViewAllSearchResult");
         if (null != searchResultList && !searchResultList.isEmpty()) {
             for (int i = 0; i < searchResultList.size(); i++) {
                 BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultList
                         .get(i);
                 if (this.benefitComponentId == benefitComponentBO
                         .getBenefitComponentId()) {
                     this.setBusinessDomainList(benefitComponentBO
                             .getBusinessDomainList());
                     break;
                 }
             }
         }
         //Copying the Benefit Component details to the VO in the request.
         benefitComponentRejectRequest.getBenefitComponentVO()
                 .setBenefitComponentId(this.benefitComponentId);
         benefitComponentRejectRequest.getBenefitComponentVO()
                 .setBenefitComponentName(this.benefitComponentName);
         benefitComponentRejectRequest.getBenefitComponentVO()
                 .setVersion(this.version);
         benefitComponentRejectRequest.getBenefitComponentVO()
                 .setBusinessDomainList(this.BusinessDomainList);
         getRequest().setAttribute("benefitComName",this.benefitComponentName);
         //Creating the response object
         benefitComponentRejectResponse = (BenefitComponentRejectResponse) this
                 .executeService(benefitComponentRejectRequest);

         if (null != benefitComponentRejectResponse) {
             this.setValidationMessages(benefitComponentRejectResponse
                     .getMessages());
             if(!(WebConstants.VIEW.equals(this.fromPage) || WebConstants.VIEW_ALL.equals(this.fromPage))){
             returnString = this.performSearch();
             }
             addAllMessagesToRequest(validationMessages);
             return returnString;

         }
         return this.performSearch();


    }
    
    /**
     * 
     * @return
     */
    public String unLockAction()throws WPDException {
    	
    	List searchResultList = (List) getSession().getAttribute(
        "benefitComponentSearchResult");
    	
    	
    	BenefitComponentBO benefitComponentBO = null;
    	
    	if (null != searchResultList && !searchResultList.isEmpty()) {
    		benefitComponentBO = new BenefitComponentBO();
            for (int i = 0; i < searchResultList.size(); i++) {
                benefitComponentBO = (BenefitComponentBO) searchResultList
                        .get(i);
                if (Integer.parseInt(selectedBenefitComponentKey) == benefitComponentBO
                        .getBenefitComponentId()) {
                    this.setBusinessDomainList(benefitComponentBO
                            .getBusinessDomainList());
                    break;
                }
            }
        }
    	
    	benefitComponentBO.setBenefitComponentId(Integer.parseInt(selectedBenefitComponentKey));
    	benefitComponentBO.setName(selectedBenefitComponentName);
    	benefitComponentBO.setBusinessDomainList(this.BusinessDomainList);
    	
    	BenefitComponentUnlockRequest benefitComponentUnlockRequest = (BenefitComponentUnlockRequest)
		this.getServiceRequest(ServiceManager.BC_UNLOCK);
    	benefitComponentUnlockRequest.setBenefitComponentBO(benefitComponentBO);
    	BenefitComponentUnlockResponse benefitComponentUnlockResponse=null;
    	//calls the service
    	benefitComponentUnlockResponse =(BenefitComponentUnlockResponse)this.executeService( benefitComponentUnlockRequest );
    	
    	if(null != benefitComponentUnlockResponse ){
   	    	
   	   	    	List list = benefitComponentUnlockResponse.getMessages();
   	    	    this.performSearch();
   		    	super.addAllMessagesToRequest(list);
   	    	
    	}
    	return "";
    }
    /**
     * @return Returns the businessEntity.
     */
    public String getBusinessEntity() {
        return businessEntity;
    }


    /**
     * @param businessEntity
     *            The businessEntity to set.
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
     * @param businessGroup
     *            The businessGroup to set.
     */
    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }


    /**
     * @return Returns the lob.
     */
    public String getLob() {
        return lob;
    }


    /**
     * @param lob
     *            The lob to set.
     */
    public void setLob(String lob) {
        this.lob = lob;
    }


    /**
     * @return Returns the searchResultList.
     */
    public List getSearchResultList() {
        return searchResultList;
    }


    /**
     * @param searchResultList
     *            The searchResultList to set.
     */
    public void setSearchResultList(List searchResultList) {
        this.searchResultList = searchResultList;
    }


    /**
     * @return Returns the effectiveDate.
     */
    public String getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * @param effectiveDate
     *            The effectiveDate to set.
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
     * @param expiryDate
     *            The expiryDate to set.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }


    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }


    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets search results to session
     * @param searchresultlist
     */
    protected void setSearchResultToSession(List result) {
        getSession().setAttribute("benefitComponentSearchResult", result);
    }


    /**
     * Returns the benefitComponentId.
     * @return benefitComponentId
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }


    /**
     * Sets the benefitComponentId.
     * @param benefitComponentId
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
    }


    /**
     * Returns the benefitComponentName.
     * @return benefitComponentName
     */
    public String getBenefitComponentName() {
        return benefitComponentName;
    }


    /**
     * Sets the benefitComponentName.
     * @param benefitComponentName
     */
    public void setBenefitComponentName(String benefitComponentName) {
        this.benefitComponentName = benefitComponentName;
    }


    /**
     * Returns the validationMessages.
     * @return validationMessages
     */
    public List getValidationMessages() {
        return validationMessages;
    }


    /**
     * Sets the validationMessages.
     * @param validationMessages
     */
    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }


    /**
     * Returns the version.
     * @return version
     */
    public int getVersion() {
        return version;
    }


    /**
     * Sets the version.
     * @param version
     */
    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * Returns the businessDomainList.
     * @return businessDomainList
     */
    public List getBusinessDomainList() {
        return BusinessDomainList;
    }


    /**
     * Sets the businessDomainList.
     * @param businessDomainList
     */
    public void setBusinessDomainList(List businessDomainList) {
        BusinessDomainList = businessDomainList;
    }
    
	 /** Sets the breadcrump
	 * 
	 */
	protected void setBreadCrump(){
       	 this.setBreadCrumbText("Product Configuration >> Benefit Component >> Locate");
	}
	
	/**
	 * @return Returns the selectedBenefitComponentKey.
	 */
	public String getSelectedBenefitComponentKey() {
		return selectedBenefitComponentKey;
	}
	/**
	 * @param selectedBenefitComponentKey The selectedBenefitComponentKey to set.
	 */
	public void setSelectedBenefitComponentKey(
			String selectedBenefitComponentKey) {
		this.selectedBenefitComponentKey = selectedBenefitComponentKey;
	}
	/**
	 * @return Returns the selectedBenefitComponentName.
	 */
	public String getSelectedBenefitComponentName() {
		return selectedBenefitComponentName;
	}
	/**
	 * @param selectedBenefitComponentName The selectedBenefitComponentName to set.
	 */
	public void setSelectedBenefitComponentName(
			String selectedBenefitComponentName) {
		this.selectedBenefitComponentName = selectedBenefitComponentName;
	}
	/**
	 * @return Returns the selectedBenefitComponentVersion.
	 */
	public String getSelectedBenefitComponentVersion() {
		return selectedBenefitComponentVersion;
	}
	/**
	 * @param selectedBenefitComponentVersion The selectedBenefitComponentVersion to set.
	 */
	public void setSelectedBenefitComponentVersion(
			String selectedBenefitComponentVersion) {
		this.selectedBenefitComponentVersion = selectedBenefitComponentVersion;
	}
	
	/**
	 * method to get the benefitComponentSessionObject.
	 * @return benefitComponentSessionObject.
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
	 * Returns the breadCrumpViewAll
	 * @return String breadCrumpViewAll.
	 */
	public String getBreadCrumpViewAll() {
	    String nameString = (String)(ESAPI.encoder().encodeForHTML(getRequest().getParameter("benefitCName")));
	    if(StringUtil.regExPatterValidation(nameString)){
	    	nameString = nameString;
	   }else{
		   nameString=null;
	   }
	    if(null == nameString){
	    	nameString = (String) getRequest().getAttribute("benefitComName");
	    }
	    if(null!= nameString && !"".equals(nameString))
	    	getRequest().getSession().setAttribute("nameString",nameString);
	    else
	    	nameString = (String)getRequest().getSession().getAttribute("nameString");
		setBreadCrumbText("Product Configuration >> Benefit Component ("+nameString +") >> View All Versions");
		return breadCrumpViewAll;
	}
	
	/**
	 * Sets the breadCrumpViewAll
	 * @param breadCrumpViewAll.
	 */
	public void setBreadCrumpViewAll(String breadCrumpViewAll) {
		this.breadCrumpViewAll = breadCrumpViewAll;
	}
    /**
     *  removing session values
     */
    private static void removeValueInSession(String attribute) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
        .getExternalContext().getSession(true);

        if (null != session.getAttribute(attribute))
        	session.removeAttribute(attribute);
    }
	/**
	 * @return Returns the fromPage.
	 */
	public String getFromPage() {
		return fromPage;
	}
	/**
	 * @param fromPage The fromPage to set.
	 */
	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}
	/**
	 * @return Returns the benefitComponentParentId.
	 */
	public int getBenefitComponentParentId() {
		return benefitComponentParentId;
	}
	/**
	 * @param benefitComponentParentId The benefitComponentParentId to set.
	 */
	public void setBenefitComponentParentId(int benefitComponentParentId) {
		this.benefitComponentParentId = benefitComponentParentId;
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
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Product Configuration >> Benefit Component>> Locate >> Print";
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
	 * @return Returns the marketBusinessUnitList.
	 */
	public List getMarketBusinessUnitList() {
		return marketBusinessUnitList;
	}
	/**
	 * @param marketBusinessUnitList The marketBusinessUnitList to set.
	 */
	public void setMarketBusinessUnitList(List marketBusinessUnitList) {
		this.marketBusinessUnitList = marketBusinessUnitList;
	}
}