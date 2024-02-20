/*
 * Created on Aug 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.search;

import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.search.request.DomainFetchRequest;
import com.wellpoint.wpd.common.search.response.DomainFetchResponse;
import com.wellpoint.wpd.common.search.result.BenefitIdentifier;
import com.wellpoint.wpd.common.search.result.ObjectIdentifier;
import com.wellpoint.wpd.common.search.util.SearchConstants;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.search.pagination.MultipageSearchResult;

/**
 * @author U12218
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchToLocateBackingBean extends WPDBackingBean {
	
	private String lob;
	
	private String businessEntity;
	
	private String businessGroup;
	
	private String name;
	
	private String objectType;
	
	private int clickedIndex;
	
	private int locate= 0;
	
	public String performLocate(){
		DomainFetchResponse response = null;
		DomainFetchRequest request = (DomainFetchRequest)getServiceRequest(ServiceManager.DOMAIN_FECTH_REQUEST);
		if(SearchConstants.BENEFIT.equals(objectType)){
			BenefitIdentifier benefitIdentifier = (BenefitIdentifier)getCurrentIdentifier();
			request.setIdentifier(benefitIdentifier);
			response = (DomainFetchResponse)executeService(request);
			updateDomains(response, false);
		}
		locate = 1;
		return "";
	}
	
	
	private void updateDomains(DomainFetchResponse response, boolean codeFirst) {
		lob = getAppendedBusinessDomains( response.getLob(),codeFirst);
		businessEntity = getAppendedBusinessDomains( response.getBusinessEntity(),codeFirst);
		businessGroup = getAppendedBusinessDomains( response.getBusinessGroup(),codeFirst);
	}

	private String getAppendedBusinessDomains(List domains, boolean codeFirst){
		Iterator iterator = domains.iterator();
		boolean enteredFlag = false;
		DomainItem domain = null;
		String entry = "";
		while(iterator.hasNext()){
			domain = (DomainItem)iterator.next();
			String first;
			String second;
			if(codeFirst){
				first = domain.getItemId();
				second = domain.getItemDesc();
			}else{
				second = domain.getItemId();
				first = domain.getItemDesc();
			}
			entry = entry +  first + "~";
			entry = entry +  second + "~";
			enteredFlag = true;
		}
		if(enteredFlag){
			entry = entry.substring(0,entry.length() - 1);
		}
		return entry;
	}


	
	private ObjectIdentifier getCurrentIdentifier(){
		String objType = objectType;
		if(getViewAssociationFromSession()){
			objType += SearchConstants.ASSOCIATION;
		}else if(getViewAttachment()){
			objType += SearchConstants.ATTACHMENTS;
		}
		int pageNumber = getCurrentPageNumber();
		MultipageSearchResult searchResult = (MultipageSearchResult)getSession().getAttribute(objType);
		return (ObjectIdentifier)searchResult.getPage(pageNumber).getObjects()[clickedIndex];
	}
	

	private int getCurrentPageNumber(){
		int pno = Integer.valueOf(
				String.valueOf(getSession().getAttribute(
						SearchConstants.PAGE_NUMBER))).intValue();
		return pno;
	}

	private boolean getViewAssociationFromSession() {
		String association = (String) getSession().getAttribute(
				SearchConstants.VIEW_ASSOCIATION);
		return SearchConstants.VIEW_ASSOCIATION_TRUE.equals(association);
	}

	private boolean getViewAttachment() {
		String attachment = (String)getSession().
				getAttribute(SearchConstants.VIEW_ATTACHMENT);
		if(SearchConstants.VIEW_ATTACHMENT_TRUE.equals(attachment)){
			return true;
		}
		return false;
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
	 * @return Returns the objectType.
	 */
	public String getObjectType() {
		return objectType;
	}
	/**
	 * @param objectType The objectType to set.
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	/**
	 * @return Returns the clickedIndex.
	 */
	public int getClickedIndex() {
		return clickedIndex;
	}
	/**
	 * @param clickedIndex The clickedIndex to set.
	 */
	public void setClickedIndex(int clickedIndex) {
		this.clickedIndex = clickedIndex;
	}
	/**
	 * @return Returns the locate.
	 */
	public int getLocate() {
		return locate;
	}
	/**
	 * @param locate The locate to set.
	 */
	public void setLocate(int locate) {
		this.locate = locate;
	}
}
