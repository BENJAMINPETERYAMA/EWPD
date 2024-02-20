/*
 * Created on Mar 19, 2007 
 */
package com.wellpoint.wpd.web.standardbenefit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.standardbenefit.request.StandardBenefitSearchRequest;
import com.wellpoint.wpd.common.standardbenefit.response.StandardBenefitSearchResponse;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitSearchVO;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * Backing bean for viewing all version of standard benefit.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitViewAllVersionBackingBean extends WPDBackingBean{
    
	private List locateResultList = null;
    private String viewStandardBenefitKey;
	private String hiddenVar;
	public StandardBenefitViewAllVersionBackingBean(){}
	
	
    /**
     * Method will load the datas in the view page
     */
    public String getViewStandardBenefitKey() {
        ArrayList validationMessages = null;
        String keyString = (String)(getRequest().getParameter(WebConstants.BENEFIT_KEY));
        String nameString = (String)(getRequest().getParameter(WebConstants.BENEFIT_NAME));
        if (null == keyString && null == keyString)
        	return WebConstants.VIEW_ALL_VERSIONS; 
        this.getStandardBenefitSessionObject().setStandardBenefitKey(new Integer(keyString).intValue());
        this.getStandardBenefitSessionObject().setStandardBenefitName(nameString);
        StandardBenefitSearchRequest standardBenefitSearchRequest = (StandardBenefitSearchRequest) this
		.getServiceRequest(ServiceManager.SB_SEARCH_REQUEST);
	    StandardBenefitSearchVO standardBenefitSearchVO = new StandardBenefitSearchVO();
	    standardBenefitSearchVO.setBenefitKey(new Integer(keyString).intValue());
	    standardBenefitSearchVO.setName(nameString);
	    standardBenefitSearchRequest.setStandardBenefitSearchVO(standardBenefitSearchVO);
	    if(null != getSession().getAttribute(WebConstants.BENEFIT_VIEWALL_RESULT))
        	removeValueInSession(WebConstants.BENEFIT_VIEWALL_RESULT);
	    StandardBenefitSearchResponse standardBenefitSearchResponse = (StandardBenefitSearchResponse) this.executeService(standardBenefitSearchRequest);
	    
	    
	    
		 if (null != standardBenefitSearchResponse) {
		     if(null != standardBenefitSearchResponse.getSearchResultList() && standardBenefitSearchResponse.getSearchResultList().size()>0){
		         locateResultList = new ArrayList();
		         setLocateResultList(standardBenefitSearchResponse.getSearchResultList());
		         setLatestVersionFlag(locateResultList);
//		         if (locateResultList != null && locateResultList.size() > 0)
//                    this.setSearchResultToSession(locateResultList);
		         if (locateResultList != null && locateResultList.size() > 0){
//	                List formattedResultSet = new ArrayList();
	                for (int i = 0; i < locateResultList.size(); i++) {
	                    StandardBenefitVO standardBnftVO = (StandardBenefitVO) locateResultList
	                            .get(i);
	                    
	                    DomainDetail domainDetail =standardBnftVO.getDomainDetail();
	                    if (domainDetail != null) {
	                    	List lobList = null;
	                    	lobList=domainDetail.getLineOfBusiness();
	                    	if((null!=lobList)&&(lobList.size()>0)){
		                    	for(int j=0;j<lobList.size();j++){
		                    		DomainItem domainItem=(DomainItem)lobList.get(j);
		                    		standardBnftVO.setLob(domainItem.getItemDesc());
		                    		standardBnftVO.setLob(WPDStringUtil.getTildaString(domainDetail.getLineOfBusiness()));
		                    	}
	                    	}
	                    }
	                }
	                this.setSearchResultToSession(locateResultList);
	                }
		     }
	    	 else{
	 			validationMessages.add(new ErrorMessage(WebConstants.SEARCH_RESULT_NOT_FOUND));
	 			addAllMessagesToRequest(validationMessages);
	     	 }
		 }
		this.setBreadCrumbText(WebConstants.VIEW_ALL_VERSIONS_BREADCRUMB); 
        return WebConstants.VIEW_ALL_VERSIONS;
    }
    /*
     *  Function will get the session object for standard benefit.
     */
    protected StandardBenefitSessionObject getStandardBenefitSessionObject(){
	    StandardBenefitSessionObject standardBenefitSessionObject = (StandardBenefitSessionObject)getSession().getAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY);
		
		if(standardBenefitSessionObject == null) {
		    standardBenefitSessionObject = new StandardBenefitSessionObject();
			getSession().setAttribute(WebConstants.STANDARD_BENEFIT_SESSION_KEY,standardBenefitSessionObject);
		}
		return standardBenefitSessionObject;
	}
    /**
     * Sets the Latest version flag.
     * @param locateResultList.
     */
    
    private void setLatestVersionFlag(List locateResultList){
    	
		int count = -1;
		int index = 0;
		int latestVersion = 0;
		int version = 0;
    	Iterator iterator = locateResultList.iterator();
    	while (iterator.hasNext()){
    		++count;
    		version = ((StandardBenefitVO)iterator.next()).getVersion();
    		if (latestVersion < version){
    			latestVersion = version;
    			index = count;
    		}
    	}
    	((StandardBenefitVO)locateResultList.get(index)).setVersionFlag(true);
    }

	/**
	 * @param viewStandardBenefitKey The viewStandardBenefitKey to set.
	 */
	public void setViewStandardBenefitKey(String viewStandardBenefitKey) {
		this.viewStandardBenefitKey = viewStandardBenefitKey;
	}
	/**
	 * @return Returns the locateResultList.
	 */
	public List getLocateResultList() {
		return locateResultList;
	}
	/**
	 * @param locateResultList The locateResultList to set.
	 */
	public void setLocateResultList(List locateResultList) {
		this.locateResultList = locateResultList;
	}
	
    protected void setSearchResultToSession(List result){
        getSession().setAttribute(WebConstants.BENEFIT_VIEWALL_RESULT,result);
    }
    protected void removeValueInSession(String attribute) {
        if (null != getSession().getAttribute(attribute))
            getSession().removeAttribute(attribute);
    }    
    
	/**
	 * @return Returns the hiddenVar.
	 */
	public String getHiddenVar() {
	    String benefitName = (String)(ESAPI.encoder().encodeForHTML(getRequest().getParameter(WebConstants.BENEFIT_NAME)));
	    if(null!=benefitName  && benefitName.matches("^[0-9a-zA-Z_]+$")){
	    	benefitName = benefitName;
	   }else{
		   benefitName=null;
	   }
	    if(null == benefitName){
	    	benefitName = (String) getRequest().getAttribute(WebConstants.BENEFIT_NAME);
	    }
	    String bread = "Product Configuration >> Benefit ("+benefitName+") >> View All Versions";
		this.setBreadCrumbText(bread); 
		return hiddenVar;
	}
	/**
	 * @param hiddenVar The hiddenVar to set.
	 */
	public void setHiddenVar(String hiddenVar) {
		this.hiddenVar = hiddenVar;
	}
}
