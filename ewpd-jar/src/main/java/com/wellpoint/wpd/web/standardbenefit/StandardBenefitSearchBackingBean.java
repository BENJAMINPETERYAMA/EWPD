/*

 * Created on Feb 19, 2007

 *

 * TODO To change the template for this generated file go to

 * Window - Preferences - Java - Code Style - Code Templates

 */

package com.wellpoint.wpd.web.standardbenefit;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.standardbenefit.request.ApproveStandardBenefitRequest;
import com.wellpoint.wpd.common.standardbenefit.request.PublishStandardBenefitRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RejectStandardBenefitRequest;
import com.wellpoint.wpd.common.standardbenefit.request.ScheduleForTestSBRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitDeleteRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitDeleteVersionsRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitSearchRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitUnLockRequest;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitVersionsLifeCycleRequest;
import com.wellpoint.wpd.common.standardbenefit.request.TestFailStandardBenefitRequest;
import com.wellpoint.wpd.common.standardbenefit.request.TestPassStandardBenefitRequest;
import com.wellpoint.wpd.common.standardbenefit.response.ApproveStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.PublishStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RejectStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.ScheduleForTestSBResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitDeleteResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitSearchResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitUnLockResponse;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitVersionsLifeCycleResponse;
import com.wellpoint.wpd.common.standardbenefit.response.TestFailStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.response.TestPassStandardBenefitResponse;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitSearchVO;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.domain.bo.DomainItem;


/**
 * Backing bean for standard benefit search
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class StandardBenefitSearchBackingBean extends WPDBackingBean {

    private String name;

    private String lob;

    private String businessEntity;

    private String businessGroup;
    
    private String marketBusinessUnit;

    private String term;

    private String qualifier;

    private String providerArrangement;

    private String dataType;

    private List locateResultList = null;

    private List lobCodeList;

    private List businessEntityCodeList;

    private List businessGroupCodeList;
    
    private List marketBusinessUnitList;

    private List providerArrangementCodeList;

    private List termCodeList;

    private List qualifierCodeList;

    private List dataTypeCodeList;

    private List validationMessages;

    private int selectedStandardBenefitKey;

    private String selectedStandardBenefitName;

    private String actionForTest;

    private String benefitType;

    private String benefitTypeCode;
    
    private String benefitSearchPrint;
    
    private boolean searchPrint;
    
    private String printBreadCrumbText;
    
    private String benefitCategory;
    /**
     * @return searchPrint
     * 
     * Returns the searchPrint.
     */
    public boolean isSearchPrint() {
        return searchPrint;
    }
    /**
     * @param searchPrint
     * 
     * Sets the searchPrint.
     */
    public void setSearchPrint(boolean searchPrint) {
        this.searchPrint = searchPrint;
    }
    /**
     * @return benefitSearchPrint
     * 
     * Returns the benefitSearchPrint.
     */
    public String getBenefitSearchPrint() {
        this.setSearchPrint(true);
        StandardBenefitSearchResponse standardBenefitSearchResponse = (StandardBenefitSearchResponse) this
        	.executeService(getStandardBenefitSearchRequest());
        if (null != standardBenefitSearchResponse) {
            if (null != standardBenefitSearchResponse.getSearchResultList()
                    && standardBenefitSearchResponse.getSearchResultList()
                            .size() > 0) {
                locateResultList = new ArrayList();
                setLocateResultList(standardBenefitSearchResponse
                        .getSearchResultList());
                if (locateResultList != null && locateResultList.size() > 0){
                //Code for formatting the description text to the length of 30
                // char.
                List formattedResultSet = new ArrayList();
                for (int i = 0; i < locateResultList.size(); i++) {
                    StandardBenefitVO standardBnftVO = (StandardBenefitVO) locateResultList
                            .get(i);
                    String description = "";
                    if(null != standardBnftVO.getDescription()){
                    if (standardBnftVO.getDescription().length() > 15) {
                        description = standardBnftVO.getDescription();
                        description = description.substring(0, 15)
                                .concat("...");
                        standardBnftVO.setDescription(description);
                        formattedResultSet.add(standardBnftVO);
                    }
                } else
                    formattedResultSet.add(standardBnftVO);
                }
                this.setSearchResultToSession(locateResultList);
                }
                
                //End of Code for formatting the description text to the length
                // of 30 char.
            } else {
                validationMessages.add(new InformationalMessage(
                        WebConstants.SEARCH_RESULT_NOT_FOUND));
                addAllMessagesToRequest(validationMessages);
            }
        }
        return benefitSearchPrint;
    }
    /**
     * @param benefitSearchPrint
     * 
     * Sets the benefitSearchPrint.
     */
    public void setBenefitSearchPrint(String benefitSearchPrint) {
        this.benefitSearchPrint = benefitSearchPrint;
    }
    //private boolean renderTable;


	/**
	 * @return Returns the renderTable.
	 */
	/*public boolean isRenderTable() {
		return renderTable;
	}*/
	/**
	 * @param renderTable The renderTable to set.
	 */
	/*public void setRenderTable(boolean renderTable) {
		this.renderTable = renderTable;
	}*/



    /**
     * @return Returns the benefitType.
     */
    public String getBenefitType() {
    	return benefitType;
    }


    /**
     * @param benefitType
     *            The benefitType to set.
     */
    public void setBenefitType(String benefitType) {
        this.benefitType = benefitType;
    }


    /*
     * Constructor
     */
    public StandardBenefitSearchBackingBean() {
        super();
        this.setBreadCrump();
        validationMessages = new ArrayList(1);
    }


    /*
     * this method searches for a particular benefit accroding to the input
     * given
     *  
     */
    public String performLocate() {
        if (null != getSession().getAttribute(
                WebConstants.BENEFIT_VIEWALL_RESULT))
            getSession().removeAttribute(WebConstants.BENEFIT_VIEWALL_RESULT);
        if (("".equals(this.getLob().trim()))
                && ("".equals(this.getBusinessEntity().trim()))
                && ("".equals(this.getBusinessGroup().trim()))
				&& ("".equals(this.getMarketBusinessUnit().trim()))
                && ("".equals(this.getName().trim()))
                && ("".equals(this.getProviderArrangement().trim()))
                && ("".equals(this.getQualifier().trim()))
                && ("".equals(this.getTerm().trim()))
                && ("".equals(this.getBenefitType().trim()))
                && ("".equals(this.getDataType().trim()))) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.ATLEAST_ONE_SEARCH));
            addAllMessagesToRequest(validationMessages);
            return "";
        } else {
            this.lobCodeList = WPDStringUtil.getListFromTildaString(this
                    .getLob(), 2, 2, 2);
            this.businessEntityCodeList = WPDStringUtil.getListFromTildaString(
                    this.getBusinessEntity(), 2, 2, 2);
            this.businessGroupCodeList = WPDStringUtil.getListFromTildaString(
                    this.getBusinessGroup(), 2, 2, 2);
            this.marketBusinessUnitList = WPDStringUtil.getListFromTildaString(
                    this.getMarketBusinessUnit(), 2, 2, 2);
            this.termCodeList = WPDStringUtil.getListFromTildaString(this
                    .getTerm(), 2, 2, 2);
            this.qualifierCodeList = WPDStringUtil.getListFromTildaString(this
                    .getQualifier(), 2, 2, 2);
            this.providerArrangementCodeList = WPDStringUtil
                    .getListFromTildaString(this.getProviderArrangement(), 2,
                            2, 2);
            this.dataTypeCodeList = WPDStringUtil.getListFromTildaString(
                    this.dataType, 2, 2, 2);
            if(benefitType.equals("ALL")){
            	this.benefitTypeCode=null;
            }
            else{
            	this.benefitTypeCode = benefitType.toUpperCase();
            }

            StandardBenefitSearchResponse standardBenefitSearchResponse = (StandardBenefitSearchResponse) this
                    .executeService(getStandardBenefitSearchRequest());
            if (null != standardBenefitSearchResponse) {
                if (null != standardBenefitSearchResponse.getSearchResultList()
                        && standardBenefitSearchResponse.getSearchResultList()
                                .size() > 0) {
                    locateResultList = new ArrayList();
                    setLocateResultList(standardBenefitSearchResponse
                            .getSearchResultList());
                    if (locateResultList != null && locateResultList.size() > 0){
                    //Code for formatting the description text to the length of 30
                    // char.
                    List formattedResultSet = new ArrayList();
                    for (int i = 0; i < locateResultList.size(); i++) {
                        StandardBenefitVO standardBnftVO = (StandardBenefitVO) locateResultList
                                .get(i);
//                        String description = "";
//                        if(null != standardBnftVO.getDescription()){
//	                        if (standardBnftVO.getDescription().length() > 25) {
//	                            description = standardBnftVO.getDescription();
//	                            description = description.substring(0, 25)
//	                                    .concat("...");
//	                            standardBnftVO.setDescription(description);
//	                            formattedResultSet.add(standardBnftVO);
//	                        } 
//	                    }
                        
                        DomainDetail domainDetail =standardBnftVO.getDomainDetail();
                        if (domainDetail != null) {
                        	List lobList=null;
                        	lobList=domainDetail.getLineOfBusiness();
                        	if((null!=lobList)&& (lobList.size()>0)){
	                        	for(int j=0;j<lobList.size();j++){
	                        		DomainItem domainItem=(DomainItem)lobList.get(j);
	                        		standardBnftVO.setLob(domainItem.getItemDesc());
	                        		standardBnftVO.setLob(WPDStringUtil.getTildaString(domainDetail.getLineOfBusiness()));
	                        	}
                        	}
                        }
                        //formattedResultSet.add(standardBnftVO);
                    }
                    this.setSearchResultToSession(locateResultList);
                    }

                } else {
                    validationMessages.add(new InformationalMessage(
                            WebConstants.SEARCH_RESULT_NOT_FOUND));
                    addAllMessagesToRequest(validationMessages);
                }
            }
        }
        this.setBreadCrump();
        return WebConstants.BENEFIT_SEARCH_SUCCESS;
    }


    /*
     * This method loads the StandardBenefitSearchRequest with the datas given
     * from jsp page
     *  
     */
    private StandardBenefitSearchRequest getStandardBenefitSearchRequest() {
        StandardBenefitSearchRequest standardBenefitSearchRequest = (StandardBenefitSearchRequest) this
                .getServiceRequest(ServiceManager.SB_SEARCH_REQUEST);
        StandardBenefitSearchVO standardBenefitSearchVO = new StandardBenefitSearchVO();
        standardBenefitSearchVO
        	.setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);

        if(!isSearchPrint()){
            standardBenefitSearchVO
            	.setBusinessEntityCodeList(businessEntityCodeList);
		    standardBenefitSearchVO.setBusinessGroupCodeList(businessGroupCodeList);
		    standardBenefitSearchVO.setMarketBusinessUnitList(marketBusinessUnitList);
		    standardBenefitSearchVO.setLobCodeList(lobCodeList);
		    standardBenefitSearchVO.setName(name.trim());
		    standardBenefitSearchVO
		            .setProviderArrangementCodeList(providerArrangementCodeList);
		    standardBenefitSearchVO.setQualifierCodeList(qualifierCodeList);
		    standardBenefitSearchVO.setTermCodeList(termCodeList);
		    standardBenefitSearchVO.setDataTypeCodeList(dataTypeCodeList);
		    standardBenefitSearchVO.setBenefitTypeCode(benefitTypeCode);
		    standardBenefitSearchVO.setBenefitCategory(benefitCategory);
		    standardBenefitSearchRequest
		    	.setStandardBenefitSearchVO(standardBenefitSearchVO);
		    getRequest().getSession().removeAttribute("standardBenefitSearchVO");
		    getRequest().getSession().setAttribute("standardBenefitSearchVO",standardBenefitSearchRequest.getStandardBenefitSearchVO());
			
        }else{
            if(null != getRequest().getSession().getAttribute("standardBenefitSearchVO")){
                standardBenefitSearchRequest.setStandardBenefitSearchVO((StandardBenefitSearchVO)getRequest().getSession().getAttribute("standardBenefitSearchVO"));
    		}
        }
        
        return standardBenefitSearchRequest;
    }


    /**
     * 
     * @return Returns the businessEntity.
     *  
     */

    public String getBusinessEntity() {

        return businessEntity;

    }


    /**
     * 
     * @param businessEntity
     *            The businessEntity to set.
     *  
     */

    public void setBusinessEntity(String businessEntity) {

        this.businessEntity = businessEntity;

    }


    /**
     * 
     * @return Returns the businessGroup.
     *  
     */

    public String getBusinessGroup() {

        return businessGroup;

    }


    /**
     * 
     * @param businessGroup
     *            The businessGroup to set.
     *  
     */

    public void setBusinessGroup(String businessGroup) {

        this.businessGroup = businessGroup;

    }


    /**
     * 
     * @return Returns the lob.
     *  
     */

    public String getLob() {

        return lob;

    }


    /**
     * 
     * @param lob
     *            The lob to set.
     *  
     */

    public void setLob(String lob) {

        this.lob = lob;

    }


    /**
     * 
     * @return Returns the providerArrangement.
     *  
     */

    public String getProviderArrangement() {

        return providerArrangement;

    }


    /**
     * 
     * @param providerArrangement
     *            The providerArrangement to set.
     *  
     */

    public void setProviderArrangement(String providerArrangement) {

        this.providerArrangement = providerArrangement;

    }


    /**
     * 
     * @return Returns the qualifier.
     *  
     */

    public String getQualifier() {

        return qualifier;

    }


    /**
     * 
     * @param qualifier
     *            The qualifier to set.
     *  
     */

    public void setQualifier(String qualifier) {

        this.qualifier = qualifier;

    }


    /**
     * 
     * @return Returns the term.
     *  
     */

    public String getTerm() {

        return term;

    }


    /**
     * 
     * @param term
     *            The term to set.
     *  
     */

    public void setTerm(String term) {

        this.term = term;

    }


    /**
     * 
     * @return Returns the locateResultList.
     *  
     */
    public List getLocateResultList() {
        return locateResultList;
    }


    /**
     * 
     * @param locateResultList
     *            The locateResultList to set.
     *  
     */
    public void setLocateResultList(List locateResultList) {
        this.locateResultList = locateResultList;
    }


    /**
     * @return Returns the businessEntityCodeList.
     */
    public List getBusinessEntityCodeList() {
        return businessEntityCodeList;
    }


    /**
     * @param businessEntityCodeList
     *            The businessEntityCodeList to set.
     */
    public void setBusinessEntityCodeList(List businessEntityCodeList) {
        this.businessEntityCodeList = businessEntityCodeList;
    }


    /**
     * @return Returns the businessGroupCodeList.
     */
    public List getBusinessGroupCodeList() {
        return businessGroupCodeList;
    }


    /**
     * @param businessGroupCodeList
     *            The businessGroupCodeList to set.
     */
    public void setBusinessGroupCodeList(List businessGroupCodeList) {
        this.businessGroupCodeList = businessGroupCodeList;
    }


    /**
     * @return Returns the lobCodeList.
     */
    public List getLobCodeList() {
        return lobCodeList;
    }


    /**
     * @param lobCodeList
     *            The lobCodeList to set.
     */
    public void setLobCodeList(List lobCodeList) {
        this.lobCodeList = lobCodeList;
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
     * @return Returns the dataType.
     */
    public String getDataType() {
        return dataType;
    }


    /**
     * @param dataType
     *            The dataType to set.
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }


    /**
     * @return Returns the dataTypeCodeList.
     */
    public List getDataTypeCodeList() {
        return dataTypeCodeList;
    }


    /**
     * @param dataTypeCodeList
     *            The dataTypeCodeList to set.
     */
    public void setDataTypeCodeList(List dataTypeCodeList) {
        this.dataTypeCodeList = dataTypeCodeList;
    }


    /**
     * @return Returns the providerArrangementCodeList.
     */
    public List getProviderArrangementCodeList() {
        return providerArrangementCodeList;
    }


    /**
     * @param providerArrangementCodeList
     *            The providerArrangementCodeList to set.
     */
    public void setProviderArrangementCodeList(List providerArrangementCodeList) {
        this.providerArrangementCodeList = providerArrangementCodeList;
    }


    /**
     * @return Returns the qualifierCodeList.
     */
    public List getQualifierCodeList() {
        return qualifierCodeList;
    }


    /**
     * @param qualifierCodeList
     *            The qualifierCodeList to set.
     */
    public void setQualifierCodeList(List qualifierCodeList) {
        this.qualifierCodeList = qualifierCodeList;
    }


    /**
     * @return Returns the termCodeList.
     */
    public List getTermCodeList() {
        return termCodeList;
    }


    /**
     * @param termCodeList
     *            The termCodeList to set.
     */
    public void setTermCodeList(List termCodeList) {
        this.termCodeList = termCodeList;
    }


    /**
     * @return Returns the validationMessages.
     */
    public List getValidationMessages() {
        return validationMessages;
    }


    /**
     * @param validationMessages
     *            The validationMessages to set.
     */
    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }


    /**
     * @return Returns the selectedStandardBenefitKey.
     */
    public int getSelectedStandardBenefitKey() {
        return selectedStandardBenefitKey;
    }


    /**
     * @param selectedStandardBenefitKey
     *            The selectedStandardBenefitKey to set.
     */
    public void setSelectedStandardBenefitKey(int selectedStandardBenefitKey) {
        this.selectedStandardBenefitKey = selectedStandardBenefitKey;
    }


    /**
     * @return Returns the selectedStandardBenefitName.
     */
    public String getSelectedStandardBenefitName() {
        return selectedStandardBenefitName;
    }


    /**
     * @param selectedStandardBenefitName
     *            The selectedStandardBenefitName to set.
     */
    public void setSelectedStandardBenefitName(
            String selectedStandardBenefitName) {
        this.selectedStandardBenefitName = selectedStandardBenefitName;
    }


    /**
     * @return Returns the actionForTest.
     */
    public String getActionForTest() {
        return actionForTest;
    }


    /**
     * @param actionForTest
     *            The actionForTest to set.
     */
    public void setActionForTest(String actionForTest) {
        this.actionForTest = actionForTest;
    }


    /**
     * This method deletes all the entries in the table given a standard benefit
     * key.
     */
    public String deleteStandardBenefit() {
        int retrieveKey = getSelectedStandardBenefitKey();
        StandardBenefitDeleteRequest standardBenefitDeleteRequest = (StandardBenefitDeleteRequest) this
                .getServiceRequest(ServiceManager.DELETE_STANDARD_BENEFIT);
        StandardBenefitVO standardBenefitVO = new StandardBenefitVO();

        this.lobCodeList = WPDStringUtil.getListFromTildaString(this.getLob(),
                2, 2, 2);
        this.businessEntityCodeList = WPDStringUtil.getListFromTildaString(this
                .getBusinessEntity(), 2, 2, 2);
        this.businessGroupCodeList = WPDStringUtil.getListFromTildaString(this
                .getBusinessGroup(), 2, 2, 2);
        this.marketBusinessUnitList = WPDStringUtil.getListFromTildaString(this
                .getMarketBusinessUnit(), 2, 2, 2);
        this.termCodeList = WPDStringUtil.getListFromTildaString(
                this.getTerm(), 2, 2, 2);
        this.qualifierCodeList = WPDStringUtil.getListFromTildaString(this
                .getQualifier(), 2, 2, 2);
        this.providerArrangementCodeList = WPDStringUtil
                .getListFromTildaString(this.getProviderArrangement(), 2, 2, 2);
        this.dataTypeCodeList = WPDStringUtil.getListFromTildaString(
                this.dataType, 2, 1, 2);

        standardBenefitVO.setStandardBenefitKey(this
                .getSelectedStandardBenefitKey());
        standardBenefitVO.setBenefitIdentifier(this
                .getSelectedStandardBenefitName());

        StandardBenefitSearchVO standardBenefitSearchVO = fillSearchCritera();
        List searchResultList = (List) getSession().getAttribute(
                WebConstants.BENEFIT_SEARCH_RESULT);
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                        .get(i);
                if (retrieveKey == standardBenefitVOTemp
                        .getStandardBenefitKey()) {
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitStatus(
                                    standardBenefitVOTemp.getStatus());
                    standardBenefitVO.setStatus(standardBenefitVOTemp
                            .getStatus());
                    standardBenefitVO
                            .setStandardBenefitParentKey(standardBenefitVOTemp
                                    .getStandardBenefitParentKey());
                    standardBenefitVO.setBusinessDomains(standardBenefitVOTemp
                            .getBusinessDomains());
                    standardBenefitVO.setVersion(standardBenefitVOTemp
                            .getVersion());
                    break;
                }
            }
        }
        standardBenefitDeleteRequest.setStandardBenefitVO(standardBenefitVO);
        standardBenefitDeleteRequest
                .setStandardBenefitSearchVO(standardBenefitSearchVO);

        StandardBenefitDeleteResponse standardBenefitDeleteResponse = (StandardBenefitDeleteResponse) executeService(standardBenefitDeleteRequest);
        if (null != standardBenefitDeleteResponse) {
            if (null != standardBenefitDeleteResponse.getSearchResultList()
                    && standardBenefitDeleteResponse.getSearchResultList()
                            .size() > 0) {
                locateResultList = new ArrayList();
                setLocateResultList(standardBenefitDeleteResponse
                        .getSearchResultList());
                if (locateResultList != null && locateResultList.size() > 0)
                    this.setSearchResultToSession(locateResultList);

                //Code for formatting the description text to the length of 30
                // char.
                List formattedResultSet = new ArrayList();
                for (int i = 0; i < locateResultList.size(); i++) {
                    StandardBenefitVO standardBnftVO = (StandardBenefitVO) locateResultList
                            .get(i);
//                    String description = "";
//	                    if(null != standardBnftVO.getDescription()){
//	                    	if (standardBnftVO.getDescription().length() > 25) {
//	                        description = standardBnftVO.getDescription();
//	                        description = description.substring(0, 25)
//	                                .concat("...");
//	                        standardBnftVO.setDescription(description);
//	                        formattedResultSet.add(standardBnftVO);
//	                    	} 
//	                    }
                    DomainDetail domainDetail =standardBnftVO.getDomainDetail();
                    if (domainDetail != null) {
                    	List lobList=null;
                    	lobList=domainDetail.getLineOfBusiness();
                    	if((null!=lobList)&& (lobList.size()>0)){
	                    	for(int j=0;j<lobList.size();j++){
	                    		DomainItem domainItem=(DomainItem)lobList.get(j);
	                    		standardBnftVO.setLob(domainItem.getItemDesc());
	                    		standardBnftVO.setLob(WPDStringUtil.getTildaString(domainDetail.getLineOfBusiness()));
	                    	}
                    	}
                    }
                        formattedResultSet.add(standardBnftVO);   
                }
                //End of Code for formatting the description text to the length
                // of 30 char.

            } else {
                validationMessages.add(new ErrorMessage(
                        WebConstants.SEARCH_RESULT_NOT_FOUND));
                validationMessages.add(new InformationalMessage(
                        WebConstants.BENEFIT_DELETE_SUCCESS));
                addAllMessagesToRequest(validationMessages);
            }
        }
        this.setBreadCrump();
        return "";
    }


    /**
     * This method deletes all the entries in the table from view all version
     * page.
     */
    public String deleteStandardBenefitFromViewAllVersions() {
        StandardBenefitDeleteVersionsRequest standardBenefitDeleteVersionsRequest = (StandardBenefitDeleteVersionsRequest) this
                .getServiceRequest(ServiceManager.DELETE_STANDARD_BENEFIT_VERSION);
        standardBenefitDeleteVersionsRequest.setStandardBenefitKey(this
                .getSelectedStandardBenefitKey());
        standardBenefitDeleteVersionsRequest.setStandardBenefitName(this
                .getSelectedStandardBenefitName());

        List searchResultList = (List) getSession().getAttribute(
                WebConstants.BENEFIT_VIEWALL_RESULT);
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                        .get(i);
                if (this.selectedStandardBenefitKey == standardBenefitVOTemp
                        .getStandardBenefitKey()) {
                    this.getStandardBenefitSessionObject()
                            .setStandardBenefitStatus(
                                    standardBenefitVOTemp.getStatus());
                    standardBenefitDeleteVersionsRequest
                            .setStatus(standardBenefitVOTemp.getStatus());
                    standardBenefitDeleteVersionsRequest
                            .setStandardBenefitParentKey(standardBenefitVOTemp
                                    .getStandardBenefitParentKey());
                    standardBenefitDeleteVersionsRequest
                            .setBusinessDomains(standardBenefitVOTemp
                                    .getBusinessDomains());
                    standardBenefitDeleteVersionsRequest
                            .setVersion(standardBenefitVOTemp.getVersion());
                    break;
                }
            }
        }

        StandardBenefitDeleteResponse standardBenefitDeleteResponse = (StandardBenefitDeleteResponse) executeService(standardBenefitDeleteVersionsRequest);
        // checks if standardBenefitDeleteResponse is null and sets the values
        // accordingly
        if (null != standardBenefitDeleteResponse) {
            if (null != standardBenefitDeleteResponse.getSearchResultList()
                    && standardBenefitDeleteResponse.getSearchResultList()
                            .size() > 0) {
                locateResultList = new ArrayList();
                Application application = FacesContext.getCurrentInstance()
                        .getApplication();
                StandardBenefitViewAllVersionBackingBean viewAllVersionBackingBean = ((StandardBenefitViewAllVersionBackingBean) application
                        .createValueBinding(
                                "#{standardBenefitViewAllVersionBackingBean}")
                        .getValue(FacesContext.getCurrentInstance()));
                viewAllVersionBackingBean
                        .setLocateResultList(standardBenefitDeleteResponse
                                .getSearchResultList());
                this
                        .setVersionsSearchResultToSession(standardBenefitDeleteResponse
                                .getSearchResultList());
                this.setLocateResultList(standardBenefitDeleteResponse
                        .getSearchResultList());
                //Code for formatting the description text to the length of 30
                // char.
                List formattedResultSet = new ArrayList();
                for (int i = 0; i < locateResultList.size(); i++) {
                    StandardBenefitVO standardBnftVO = (StandardBenefitVO) locateResultList
                            .get(i);
                    String description = "";
	                    if(null != standardBnftVO.getDescription()){
	                    	if (standardBnftVO.getDescription().length() > 30) {
	                        description = standardBnftVO.getDescription();
	                        description = description.substring(0, 30)
	                                .concat("...");
	                        standardBnftVO.setDescription(description);
	                        formattedResultSet.add(standardBnftVO);
	                    	} 
	                    }else
	                    formattedResultSet.add(standardBnftVO);
                }
                //End of Code for formatting the description text to the length
                // of 30 char.
            } else {
                validationMessages.add(new ErrorMessage(
                        WebConstants.SEARCH_RESULT_NOT_FOUND));
                validationMessages.add(new InformationalMessage(
                        WebConstants.BENEFIT_DELETE_SUCCESS));
                addAllMessagesToRequest(validationMessages);
            }
        }
        getRequest().setAttribute(WebConstants.BENEFIT_NAME,standardBenefitDeleteVersionsRequest
                .getStandardBenefitName());
//        this.setBreadCrump();
        return "";
    }


    protected void setSearchResultToSession(List result) {
        getSession().setAttribute(WebConstants.BENEFIT_SEARCH_RESULT, result);
    }


    /**
     * method to get the benefitComponentSessionObject.
     * 
     * @return benefitComponentSessionObject.
     */
    protected StandardBenefitSessionObject getStandardBenefitSessionObject() {
        StandardBenefitSessionObject standardBenefitSessionObject = (StandardBenefitSessionObject) getSession()
                .getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);

        if (standardBenefitSessionObject == null) {
            standardBenefitSessionObject = new StandardBenefitSessionObject();
            getSession().setAttribute(
                    WebConstants.STANDARD_BENEFIT_SESSION_KEY,
                    standardBenefitSessionObject);
        }
        return standardBenefitSessionObject;
    }


    /**
     * 
     * this method publishes a benefit
     */
    public String publishStandardBenefit() {
        PublishStandardBenefitRequest request = (PublishStandardBenefitRequest) this
                .getServiceRequest(ServiceManager.PUBLISH_STD_BENEFIT_REQUEST);
        request.setStandardBenefitIdentifier(this
                .getSelectedStandardBenefitName());
        request.setStandardBenefitKey(this.getSelectedStandardBenefitKey());
        StandardBenefitSearchVO standardBenefitSearchVO = fillSearchCritera();
        List searchResultList = (List) getSession().getAttribute(
                WebConstants.BENEFIT_SEARCH_RESULT);
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                        .get(i);
                if (this.selectedStandardBenefitKey == standardBenefitVOTemp
                        .getStandardBenefitKey()) {
                    request.setBusinessDomains(standardBenefitVOTemp
                            .getBusinessDomains());
                    request.setStandardBenefitParentKey(standardBenefitVOTemp
                            .getStandardBenefitParentKey());
                    request.setStandardBenefitStatus(standardBenefitVOTemp
                            .getStatus());
                    request.setStandardBenefitVersion(standardBenefitVOTemp
                            .getVersion());
                    break;
                }
            }
        }
        request.setStandardBenefitSearchVO(standardBenefitSearchVO);
        PublishStandardBenefitResponse response = (PublishStandardBenefitResponse) executeService(request);
        if (null != response) {
            if (null != response.getSearchResultList()
                    && response.getSearchResultList().size() > 0) {
                locateResultList = new ArrayList();
                setLocateResultList(response.getSearchResultList());
                if (locateResultList != null && locateResultList.size() > 0)
                    this.setSearchResultToSession(locateResultList);
            } else {
                validationMessages.add(new ErrorMessage(
                        WebConstants.SEARCH_RESULT_NOT_FOUND));
                addAllMessagesToRequest(validationMessages);
            }
        }
        this.setBreadCrump();
        return "";
    }


    /**
     * 
     * Standard Benefit Schedule For Test
     */
    public String scheduleForTestStandardBenefit() {
        //Creating request object
        ScheduleForTestSBRequest request = (ScheduleForTestSBRequest) this
                .getServiceRequest(ServiceManager.STD_BEN_SCHEDULE_FOR_TEST);
        // Setting the key values to the request
        request.setStandardBenefitKey(this.getSelectedStandardBenefitKey());
        request.setStandardBenefitName(this.getSelectedStandardBenefitName());
        // Set the required values to the StandardBenefitSearchVO
        StandardBenefitSearchVO standardBenefitSearchVO = fillSearchCritera();
        List searchResultList = (List) getSession().getAttribute(
                WebConstants.BENEFIT_SEARCH_RESULT);
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                        .get(i);
                if (this.selectedStandardBenefitKey == standardBenefitVOTemp
                        .getStandardBenefitKey()) {
                    request.setBusinessDomains(standardBenefitVOTemp
                            .getBusinessDomains());
                    request.setStandardBenefitParentKey(standardBenefitVOTemp
                            .getStandardBenefitParentKey());
                    request.setStandardBenefitStatus(standardBenefitVOTemp
                            .getStatus());
                    request.setStandardBenefitVersion(standardBenefitVOTemp
                            .getVersion());
                    break;
                }
            }
        }
        request.setStandardBenefitSearchVO(standardBenefitSearchVO);
        ScheduleForTestSBResponse response = (ScheduleForTestSBResponse) executeService(request);
        if (null != response) {
            if (null != response.getSearchResultList()
                    && response.getSearchResultList().size() > 0) {
                locateResultList = new ArrayList();
                setLocateResultList(response.getSearchResultList());
                if (locateResultList != null && locateResultList.size() > 0)
                    this.setSearchResultToSession(locateResultList);
            } else {
                validationMessages.add(new ErrorMessage(
                        WebConstants.SEARCH_RESULT_NOT_FOUND));
                addAllMessagesToRequest(validationMessages);
            }
        }
        this.setBreadCrump();
        return "";
    }


    /**
     * 
     * Standard Benefit Test Pass
     */
    public String testPassStandardBenefit() {
        TestPassStandardBenefitRequest request = (TestPassStandardBenefitRequest) this
                .getServiceRequest(ServiceManager.STD_BEN_TEST_PASS);
        request.setStandardBenefitKey(this.getSelectedStandardBenefitKey());
        request.setStandardBenefitIdentifier(this
                .getSelectedStandardBenefitName());
        StandardBenefitSearchVO standardBenefitSearchVO = fillSearchCritera();
        List searchResultList = (List) getSession().getAttribute(
                WebConstants.BENEFIT_SEARCH_RESULT);
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                        .get(i);
                if (this.selectedStandardBenefitKey == standardBenefitVOTemp
                        .getStandardBenefitKey()) {
                    request.setBusinessDomains(standardBenefitVOTemp
                            .getBusinessDomains());
                    request.setStandardBenefitParentKey(standardBenefitVOTemp
                            .getStandardBenefitParentKey());
                    request.setStandardBenefitStatus(standardBenefitVOTemp
                            .getStatus());
                    request.setStandardBenefitVersion(standardBenefitVOTemp
                            .getVersion());
                    break;
                }
            }
        }
        request.setStandardBenefitSearchVO(standardBenefitSearchVO);
        TestPassStandardBenefitResponse response = (TestPassStandardBenefitResponse) executeService(request);
        if (null != response) {
            if (null != response.getSearchResultList()
                    && response.getSearchResultList().size() > 0) {
                locateResultList = new ArrayList();
                setLocateResultList(response.getSearchResultList());
                if (locateResultList != null && locateResultList.size() > 0)
                    this.setSearchResultToSession(locateResultList);
            } else {
                validationMessages.add(new ErrorMessage(
                        WebConstants.SEARCH_RESULT_NOT_FOUND));
                addAllMessagesToRequest(validationMessages);
            }
        }
        this.setBreadCrump();
        return "";
    }


    /**
     * 
     * Approves a benefit
     */
    public String approveStandardBenefit() {
        ApproveStandardBenefitRequest request = (ApproveStandardBenefitRequest) this
                .getServiceRequest(ServiceManager.APPROVE_STD_BENEFIT_REQUEST);
        request.setStandardBenefitKey(this.getSelectedStandardBenefitKey());
        request.setStandardBenefitIdentifier(this
                .getSelectedStandardBenefitName());
        StandardBenefitSearchVO standardBenefitSearchVO = fillSearchCritera();
        List searchResultList = (List) getSession().getAttribute(
                WebConstants.BENEFIT_SEARCH_RESULT);
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                        .get(i);
                if (this.selectedStandardBenefitKey == standardBenefitVOTemp
                        .getStandardBenefitKey()) {
                    request.setBusinessDomains(standardBenefitVOTemp
                            .getBusinessDomains());
                    request.setStandardBenefitParentKey(standardBenefitVOTemp
                            .getStandardBenefitParentKey());
                    request.setStandardBenefitStatus(standardBenefitVOTemp
                            .getStatus());
                    request.setStandardBenefitVersion(standardBenefitVOTemp
                            .getVersion());
                    break;
                }
            }
        }
        request.setStandardBenefitSearchVO(standardBenefitSearchVO);
        ApproveStandardBenefitResponse response = (ApproveStandardBenefitResponse) executeService(request);
        if (null != response) {
            if (null != response.getSearchResultList()
                    && response.getSearchResultList().size() > 0) {
            	locateResultList = new ArrayList();
                setLocateResultList(response.getSearchResultList());
                if (locateResultList != null && locateResultList.size() > 0){
                  
	                //Code for formatting the description text to the length of 30
	                // char.
	                List formattedResultSet = new ArrayList();
	                for (int i = 0; i <locateResultList.size(); i++) {
	                    StandardBenefitVO standardBnftVO = (StandardBenefitVO) locateResultList.get(i);
//	                    String description = "";
//	                    if(null != standardBnftVO.getDescription()){
//		                    if (standardBnftVO.getDescription().length() > 25) {
//		                        description = standardBnftVO.getDescription();
//		                        description = description.substring(0, 25)
//		                                .concat("...");
//		                        standardBnftVO.setDescription(description);
//		                        formattedResultSet.add(standardBnftVO);
//		                    } 
//	                    } 
                        DomainDetail domainDetail =standardBnftVO.getDomainDetail();
                        if (domainDetail != null) {
                        	List lobList=null;
                        	lobList=domainDetail.getLineOfBusiness();
                        	if((null!=lobList)&& (lobList.size()>0)){
	                        	for(int j=0;j<lobList.size();j++){
	                        		DomainItem domainItem=(DomainItem)lobList.get(j);
	                        		standardBnftVO.setLob(domainItem.getItemDesc());
	                        		standardBnftVO.setLob(WPDStringUtil.getTildaString(domainDetail.getLineOfBusiness()));
	                        	}
                        	}
                        }
			                formattedResultSet.add(standardBnftVO);   
	                }
	                //End of Code for formatting the description text to the length
	                // of 30 char.
	                this.setSearchResultToSession(locateResultList);
                } 
            }else {
		                validationMessages.add(new ErrorMessage(
		                        WebConstants.SEARCH_RESULT_NOT_FOUND));
		                addAllMessagesToRequest(validationMessages);
               		}
            }
             
        this.setBreadCrump();
        return "";
    }


    /**
     * 
     * Rejects a Benefit
     */
    public String rejectStandardBenefit() {
        RejectStandardBenefitRequest request = (RejectStandardBenefitRequest) this
                .getServiceRequest(ServiceManager.REJECT_STD_BENEFIT_REQUEST);
        request.setStandardBenefitKey(this.getSelectedStandardBenefitKey());
        request.setStandardBenefitIdentifier(this
                .getSelectedStandardBenefitName());
        StandardBenefitSearchVO standardBenefitSearchVO = fillSearchCritera();
        List searchResultList = (List) getSession().getAttribute(
                WebConstants.BENEFIT_SEARCH_RESULT);
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                        .get(i);
                if (this.selectedStandardBenefitKey == standardBenefitVOTemp
                        .getStandardBenefitKey()) {
                    request.setBusinessDomains(standardBenefitVOTemp
                            .getBusinessDomains());
                    request.setStandardBenefitParentKey(standardBenefitVOTemp
                            .getStandardBenefitParentKey());
                    request.setStandardBenefitStatus(standardBenefitVOTemp
                            .getStatus());
                    request.setStandardBenefitVersion(standardBenefitVOTemp
                            .getVersion());
                    break;
                }
            }
        }
        request.setStandardBenefitSearchVO(standardBenefitSearchVO);
        RejectStandardBenefitResponse response = (RejectStandardBenefitResponse) executeService(request);
        if (null != response) {
            if (null != response.getSearchResultList()
                    && response.getSearchResultList().size() > 0) {
                locateResultList = new ArrayList();
                setLocateResultList(response.getSearchResultList());
                if (locateResultList != null && locateResultList.size() > 0){
                  

                //Code for formatting the description text to the length of 30
                // char.
                List formattedResultSet = new ArrayList();
                for (int i = 0; i < locateResultList.size(); i++) {
                    StandardBenefitVO standardBnftVO = (StandardBenefitVO) locateResultList
                            .get(i);
//                    String description = "";
//                    if(null != standardBnftVO.getDescription()){
//	                    if (standardBnftVO.getDescription().length() > 25) {
//	                        description = standardBnftVO.getDescription();
//	                        description = description.substring(0, 25)
//	                                .concat("...");
//	                        standardBnftVO.setDescription(description);
//	                        formattedResultSet.add(standardBnftVO);
//	                    } 
//                    }
                    DomainDetail domainDetail =standardBnftVO.getDomainDetail();
                    if (domainDetail != null) {
                    	List lobList=null;
                    	lobList=domainDetail.getLineOfBusiness();
                    	if((null!=lobList)&& (lobList.size()>0)){
	                    	for(int j=0;j<lobList.size();j++){
	                    		DomainItem domainItem=(DomainItem)lobList.get(j);
	                    		standardBnftVO.setLob(domainItem.getItemDesc());
	                    		standardBnftVO.setLob(WPDStringUtil.getTildaString(domainDetail.getLineOfBusiness()));
	                    	}
                    	}
                    }
		                
		            formattedResultSet.add(standardBnftVO);   
                }
                //End of Code for formatting the description text to the length
                // of 30 char.
                this.setSearchResultToSession(locateResultList);
             }
            } else {
                validationMessages.add(new ErrorMessage(
                        WebConstants.SEARCH_RESULT_NOT_FOUND));
                addAllMessagesToRequest(validationMessages);
            }
        }
        this.setBreadCrump();
        return "";
    }


    /**
     * 
     * Standard Benefit Test Fail
     */
    public String testFailStandardBenefit() {
        TestFailStandardBenefitRequest request = (TestFailStandardBenefitRequest) this
                .getServiceRequest(ServiceManager.STD_BEN_TEST_FAIL);
        request.setStandardBenefitKey(this.getSelectedStandardBenefitKey());
        request.setStandardBenefitIdentifier(this
                .getSelectedStandardBenefitName());
        StandardBenefitSearchVO standardBenefitSearchVO = fillSearchCritera();
        List searchResultList = (List) getSession().getAttribute(
                WebConstants.BENEFIT_SEARCH_RESULT);
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                        .get(i);
                if (this.selectedStandardBenefitKey == standardBenefitVOTemp
                        .getStandardBenefitKey()) {
                    request.setBusinessDomains(standardBenefitVOTemp
                            .getBusinessDomains());
                    request.setStandardBenefitParentKey(standardBenefitVOTemp
                            .getStandardBenefitParentKey());
                    request.setStandardBenefitStatus(standardBenefitVOTemp
                            .getStatus());
                    request.setStandardBenefitVersion(standardBenefitVOTemp
                            .getVersion());
                    break;
                }
            }
        }
        request.setStandardBenefitSearchVO(standardBenefitSearchVO);
        TestFailStandardBenefitResponse response = (TestFailStandardBenefitResponse) executeService(request);
        if (null != response) {
            if (null != response.getSearchResultList()
                    && response.getSearchResultList().size() > 0) {
                locateResultList = new ArrayList();
                setLocateResultList(response.getSearchResultList());
                if (locateResultList != null && locateResultList.size() > 0)
                    this.setSearchResultToSession(locateResultList);
            } else {
                validationMessages.add(new ErrorMessage(
                        WebConstants.SEARCH_RESULT_NOT_FOUND));
                addAllMessagesToRequest(validationMessages);
            }
        }
        this.setBreadCrump();
        return "";
    }

    public String unlockStandardBenefit(){
    	StandardBenefitUnLockRequest request = (StandardBenefitUnLockRequest) this
        .getServiceRequest(ServiceManager.UNLOCK_BENEFIT);
    	request.setStandardBenefitIdentifier(this
                .getSelectedStandardBenefitName());
        request.setStandardBenefitKey(this.getSelectedStandardBenefitKey());
        StandardBenefitSearchVO standardBenefitSearchVO = fillSearchCritera();
        List searchResultList = (List) getSession().getAttribute(
                WebConstants.BENEFIT_SEARCH_RESULT);
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                        .get(i);
                if (this.selectedStandardBenefitKey == standardBenefitVOTemp
                        .getStandardBenefitKey()) {
                    request.setBusinessDomains(standardBenefitVOTemp
                            .getBusinessDomains());
                    request.setStandardBenefitParentKey(standardBenefitVOTemp
                            .getStandardBenefitParentKey());
                    request.setStandardBenefitStatus(standardBenefitVOTemp
                            .getStatus());
                    request.setStandardBenefitVersion(standardBenefitVOTemp
                            .getVersion());
                    break;
                }
            }
        }
        request.setStandardBenefitSearchVO(standardBenefitSearchVO);
        StandardBenefitUnLockResponse response  = (StandardBenefitUnLockResponse) executeService(request);
        //PublishStandardBenefitResponse
        if (null != response) {
            if (null != response.getSearchResultList()
                    && response.getSearchResultList().size() > 0) {
                locateResultList = new ArrayList();
                setLocateResultList(response.getSearchResultList());
                if (locateResultList != null && locateResultList.size() > 0){
                    List formattedResultSet = new ArrayList();
                    for (int i = 0; i < locateResultList.size(); i++) {
                        StandardBenefitVO standardBnftVO = (StandardBenefitVO) locateResultList
                                .get(i);
                        String description = "";
                        if(null != standardBnftVO.getDescription()){
	                        if (standardBnftVO.getDescription().length() > 15) {
	                            description = standardBnftVO.getDescription();
	                            description = description.substring(0, 15)
	                                    .concat("...");
	                            standardBnftVO.setDescription(description);
	                            formattedResultSet.add(standardBnftVO);
	                        } 
                        }else{
                        	formattedResultSet.add(standardBnftVO);
                        }
                    }
                    this.setSearchResultToSession(locateResultList);
                }
            } else {
                validationMessages.add(new ErrorMessage(
                        WebConstants.SEARCH_RESULT_NOT_FOUND));
                addAllMessagesToRequest(validationMessages);
            }
        }
        this.setBreadCrump();
    	 
    	return "";
    }

    /**
     * fills the StandardBenefitSearchVO with the values provided from the jsp
     * page
     */
    private StandardBenefitSearchVO fillSearchCritera() {
        StandardBenefitSearchVO standardBenefitSearchVO = new StandardBenefitSearchVO();
        standardBenefitSearchVO
                .setBusinessEntityCodeList(WPDStringUtil.getListFromTildaString(
                        this.getBusinessEntity(), 2, 2, 2));
        standardBenefitSearchVO.setBusinessGroupCodeList(WPDStringUtil.getListFromTildaString(
                this.getBusinessGroup(), 2, 2, 2));
        standardBenefitSearchVO.setMarketBusinessUnitList(WPDStringUtil.getListFromTildaString(
                this.getMarketBusinessUnit(), 2, 2, 2));
        standardBenefitSearchVO.setLobCodeList(WPDStringUtil.getListFromTildaString(this
                .getLob(), 2, 2, 2));
        standardBenefitSearchVO.setName(BusinessUtil
                .escpeSpecialCharacters(name.trim()));
        standardBenefitSearchVO
                .setProviderArrangementCodeList(providerArrangementCodeList);
        
        standardBenefitSearchVO.setQualifierCodeList(qualifierCodeList);
        standardBenefitSearchVO.setTermCodeList(termCodeList);
        standardBenefitSearchVO.setDataTypeCodeList(dataTypeCodeList);
        standardBenefitSearchVO
                .setMaxSearchResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
        return standardBenefitSearchVO;
    }


    /**
     * Sets the breadcrump
     *  
     */
    protected void setBreadCrump() {
        this.setBreadCrumbText(WebConstants.STANDARD_BENEFIT_SEARCH_BREADCRUMB);
    }


    /**
     * Method to schedule test and publish
     * 
     * @return String
     */

    public String lifeCycleFromVersions() {
        StandardBenefitVersionsLifeCycleRequest request = (StandardBenefitVersionsLifeCycleRequest) this
                .getServiceRequest(ServiceManager.STD_BEN_VERSIONS_LIFECYCLE);

        request.setStandardBenefitKey(this.getSelectedStandardBenefitKey());
        request.setStandardBenefitName(this.getSelectedStandardBenefitName());
        List searchResultList = (List) getSession().getAttribute(
                WebConstants.BENEFIT_VIEWALL_RESULT);
        if (null != searchResultList && !searchResultList.isEmpty()) {
            for (int i = 0; i < searchResultList.size(); i++) {
                StandardBenefitVO standardBenefitVOTemp = (StandardBenefitVO) searchResultList
                        .get(i);
                if (this.selectedStandardBenefitKey == standardBenefitVOTemp
                        .getStandardBenefitKey()) {
                    request.setBusinessDomains(standardBenefitVOTemp
                            .getBusinessDomains());
                    request.setStandardBenefitParentKey(standardBenefitVOTemp
                            .getStandardBenefitParentKey());
                    request.setStandardBenefitStatus(standardBenefitVOTemp
                            .getStatus());
                    request.setStandardBenefitVersion(standardBenefitVOTemp
                            .getVersion());
                    break;
                }
            }
        }

        StandardBenefitVersionsLifeCycleResponse response = null;
        if (this.getActionForTest().equals(WebConstants.SCHEDULE_FOR_TEST)) {
            request
                    .setAction(StandardBenefitVersionsLifeCycleRequest.SCHEDULE_FOR_TEST_STANDARD_BENEFIT);
        } else if (this.getActionForTest().equals(WebConstants.TEST_PASS)) {
            request
                    .setAction(StandardBenefitVersionsLifeCycleRequest.TEST_PASS_STANDARD_BENEFIT);
        } else if (this.getActionForTest().equals(WebConstants.TEST_FAIL)) {
            request
                    .setAction(StandardBenefitVersionsLifeCycleRequest.TEST_FAIL_STANDARD_BENEFIT);
        } else if (this.getActionForTest().equals(WebConstants.PUBLISH)) {
            request
                    .setAction(StandardBenefitVersionsLifeCycleRequest.PUBLISH_STANDARD_BENEFIT);
        } else if (this.getActionForTest().equals(WebConstants.APPROVE)) {
            request
                    .setAction(StandardBenefitVersionsLifeCycleRequest.APPROVE_STANDARD_BENEFIT);
        } else if (this.getActionForTest().equals(WebConstants.REJECT)) {
            request
                    .setAction(StandardBenefitVersionsLifeCycleRequest.REJECT_STANDARD_BENEFIT);
        }else if (this.getActionForTest().equals(WebConstants.UNLOCK_BENEFIT)) {
            request
            .setAction(StandardBenefitVersionsLifeCycleRequest.UNLOCK_STANDARD_BENEFIT);
        }
        response = (StandardBenefitVersionsLifeCycleResponse) this
                .executeService(request);
        if (null != response) {
            if (null != response.getSearchResultList()
                    && response.getSearchResultList().size() > 0) {
                locateResultList = new ArrayList();
                Application application = FacesContext.getCurrentInstance()
                        .getApplication();
                StandardBenefitViewAllVersionBackingBean viewAllVersionBackingBean = ((StandardBenefitViewAllVersionBackingBean) application
                        .createValueBinding(
                                "#{standardBenefitViewAllVersionBackingBean}")
                        .getValue(FacesContext.getCurrentInstance()));
                viewAllVersionBackingBean.setLocateResultList(response
                        .getSearchResultList());
                getRequest().setAttribute(WebConstants.BENEFIT_NAME,this.getSelectedStandardBenefitName());
                this.setVersionsSearchResultToSession(response
                        .getSearchResultList());

            } else {
                validationMessages.add(new ErrorMessage(
                        WebConstants.SEARCH_RESULT_NOT_FOUND));
                addAllMessagesToRequest(validationMessages);
            }

        }
        return "";
    }


    protected void setVersionsSearchResultToSession(List result) {
        getSession().setAttribute(WebConstants.BENEFIT_VIEWALL_RESULT, result);
    }
    /**
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Product Configuration >> Benefit >> Locate >> Print";
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
	 * @return Returns the benefitTypeList.
	 
	public List getBenefitTypeList() {
		benefitTypeList = new ArrayList();
        SelectItem standard = new SelectItem("Standard");
        SelectItem mandate = new SelectItem("Mandate");
        SelectItem all = new SelectItem("ALL");
        benefitTypeList.add(standard);
        benefitTypeList.add(mandate);
        benefitTypeList.add(all);
		return benefitTypeList;
	}
	/**
	 * @param benefitTypeList The benefitTypeList to set.
	 
	public void setBenefitTypeList(List benefitTypeList) {
		this.benefitTypeList = benefitTypeList;
	}*/
	/**
	 * @return Returns the benefitTypeCode.
	 */
	public String getBenefitTypeCode() {
		return benefitTypeCode;
	}
	/**
	 * @param benefitTypeCode The benefitTypeCode to set.
	 */
	public void setBenefitTypeCode(String benefitTypeCode) {
		this.benefitTypeCode = benefitTypeCode;
	}
	/**
	 * @return Returns the benefitCategory.
	 */
	public String getBenefitCategory() {
		return benefitCategory;
	}
	/**
	 * @param benefitCategory The benefitCategory to set.
	 */
	public void setBenefitCategory(String benefitCategory) {
		this.benefitCategory = benefitCategory;
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