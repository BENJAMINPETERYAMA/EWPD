/*
 * SearchUtil.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

//import com.ibm.wsspi.sib.exitpoint.ra.HashMap;
import com.wellpoint.wpd.common.search.result.BenefitComponentDetail;
import com.wellpoint.wpd.common.search.result.BenefitComponentIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitDetail;
import com.wellpoint.wpd.common.search.result.BenefitIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitLevelDetail;
import com.wellpoint.wpd.common.search.result.BenefitLevelIdentifier;
import com.wellpoint.wpd.common.search.result.ContractDetail;
import com.wellpoint.wpd.common.search.result.ContractIdentifier;
import com.wellpoint.wpd.common.search.result.NotesDetail;
import com.wellpoint.wpd.common.search.result.NotesIdentifier;
import com.wellpoint.wpd.common.search.result.ProductDetail;
import com.wellpoint.wpd.common.search.result.ProductIdentifier;
import com.wellpoint.wpd.common.search.result.ProductStructureDetail;
import com.wellpoint.wpd.common.search.result.ProductStructureIdentifier;
import com.wellpoint.wpd.common.search.util.SearchConstants;
import com.wellpoint.wpd.web.search.pagination.MultipageSearchResult;
import com.wellpoint.wpd.web.search.pagination.Page;
import com.wellpoint.wpd.web.util.SearchSortSessionObject;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchUtil {
    
    public  HttpSession getSession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
        	.getExternalContext().getSession(true);
        return session;
	}
    
    public List getPageListForObjectType(String objectType, int pageNumber){
    	List pageList = null;
    	if( null != objectType && !"".equals(objectType) ){
    		MultipageSearchResult multipageSearchResult=(MultipageSearchResult)getSession().
				getAttribute(objectType);
    		pageList = Arrays.asList(multipageSearchResult.getPage(pageNumber).getObjects());
    	}
    	return pageList;
    }
    
    public List getWholeResultForObjectType(String objectType){
    	
    	if( null != objectType && !"".equals(objectType) ){
    		MultipageSearchResult multipageSearchResult=(MultipageSearchResult)getSession().
				getAttribute(objectType);
    		List results = new ArrayList();
    		Page[] pages = multipageSearchResult.getPages();
    		for(int i=0;i< pages.length;i++){
    			results.addAll(Arrays.asList(pages[i].getObjects()));
    		}
    		return results;
    	}
    	return null;
    }
    
    public int getQueryResultForObjectType(String objectType){
    	
    	if( null != objectType && !"".equals(objectType) ){
    		MultipageSearchResult multipageSearchResult=(MultipageSearchResult)getSession().
				getAttribute(objectType);
    		return multipageSearchResult.getQueryResultCount();
    	}
    	return 0;
    }
    

	public String getBreadCrumbForObjectType(String objectType){
		return getMenuNameForObjectType(objectType);
	}
    
    
    public Map getSearchResults() {
    	Map searchResultsMap = new HashMap();
		HttpSession session = getSession();
		List keys = getSearchResultKeys();
		Iterator keyIterator = keys.iterator();
		String key;
		MultipageSearchResult searchResult = null;
		while(keyIterator.hasNext()){
			key = (String)keyIterator.next();
			searchResult = (MultipageSearchResult)session.getAttribute(key);
			if(searchResult != null && searchResult.getTotalNumberOfResults() > 0){
				searchResultsMap.put(key, searchResult);
			}
		}
		return searchResultsMap;
	}

	public String getNameForObjectType(String type){
		if(SearchConstants.BENEFIT.equals(type)){
			return "Benefit";
		}
		else if(SearchConstants.BENEFIT_COMPONENTS.equals(type)){
			return "Benefit Component";
		}
		else if(SearchConstants.BENEFIT_LEVEL.equals(type)){
			return "BenefitL evel";
		}
		else if(SearchConstants.CONTRACT.equals(type)){
			return "Contract";
		}
		else if(SearchConstants.NOTES.equals(type)){
			return "Note";
		}
		else if(SearchConstants.PRODUCT.equals(type)){
			return "Product";
		}
		else if(SearchConstants.PRODUCT_STRUCTURES.equals(type)){
			return "Product Structure";
		}
		return null;
	}
	
	public String getNameForObject(Object object){
		if(object == null){
		    return "";
		}
	    
	    if(object instanceof BenefitIdentifier || object instanceof BenefitDetail){
			return "Benefit";
		}
		else if(object instanceof BenefitComponentIdentifier || object instanceof BenefitComponentDetail){
			return "Benefit Component";
		}
		else if(object instanceof BenefitLevelIdentifier || object instanceof BenefitLevelDetail){
			return "Benefit Level";
		}
		else if(object instanceof ContractIdentifier || object instanceof ContractDetail){
			return "Contract";
		}
		else if(object instanceof NotesIdentifier || object instanceof NotesDetail){
			return "Note";
		}
		else if(object instanceof ProductIdentifier || object instanceof ProductDetail){
			return "Product";
		}
		else if(object instanceof ProductStructureIdentifier || object instanceof ProductStructureDetail){
			return "Product Structure";
		}
		return "";
	}
	
	public String getKeyValueForObject(Object object){
		if(object == null){
		    return "";
		}
	    if(object instanceof BenefitDetail){
			return "Benefit ("+((BenefitDetail)object).getName()+")";
		}
		else if(object instanceof BenefitComponentDetail){
			return "Benefit Component ("+((BenefitComponentDetail)object).getName()+")";
		}
		else if(object instanceof BenefitLevelDetail){
			return "Benefit Level("+((BenefitLevelDetail)object).getDescription()+")";
		}
		else if(object instanceof ContractDetail){
			return "Contract ("+((ContractDetail)object).getContractId()+")";
		}
		else if(object instanceof NotesDetail){
			return "Note ("+((NotesDetail)object).getNoteName()+")";
		}
		else if(object instanceof ProductDetail){
			return "Product ("+((ProductDetail)object).getProductName()+")";
		}
		else if(object instanceof ProductStructureDetail){
			return "Product Structure ("+((ProductStructureDetail)object).getName()+")";
		}
	    return "";
	}
	
	public String getMenuNameForObjectType(String type){
		if(SearchConstants.BENEFIT.equals(type)){
			return "Benefits";
		}
		else if(SearchConstants.BENEFIT_COMPONENTS.equals(type)){
			return "Benefit Components";
		}
		else if(SearchConstants.BENEFIT_LEVEL.equals(type)||SearchConstants.BENEFIT_LINE.equals(type)){
			return "Benefit Levels";
		}
		else if(SearchConstants.CONTRACT.equals(type)){
			return "Contracts";
		}
		else if(SearchConstants.NOTES.equals(type)){
			return "Notes";
		}
		else if(SearchConstants.PRODUCT.equals(type)){
			return "Products";
		}
		else if(SearchConstants.PRODUCT_STRUCTURES.equals(type)){
			return "Product Structures";
		}
		return null;
	}

	public List getSearchResultKeys(){
		List keys = new ArrayList();
		keys.add(SearchConstants.CONTRACT);
		keys.add(SearchConstants.PRODUCT);
		keys.add(SearchConstants.PRODUCT_STRUCTURES);
		keys.add(SearchConstants.BENEFIT_COMPONENTS);
		keys.add(SearchConstants.BENEFIT);
		keys.add(SearchConstants.BENEFIT_LEVEL);
		keys.add(SearchConstants.NOTES);
		
		return keys;
	}
	
	public List getOrderedAttachementKeys(){
		List keys = new ArrayList();
		keys.add(SearchConstants.CONTRACT);
		keys.add(SearchConstants.PRODUCT);
		keys.add(SearchConstants.PRODUCT_STRUCTURES);
		keys.add(SearchConstants.BENEFIT_COMPONENTS);
		keys.add(SearchConstants.BENEFIT);
		keys.add(SearchConstants.BENEFIT_LEVEL);
		keys.add(SearchConstants.BENEFIT_LINE);
		keys.add(SearchConstants.NOTES);
		
		return keys;
	}

	/**
	 * This will clear all the association object mutlipart search result in session
	 */
	public void clearAllAssociations() {
		HttpSession session = getSession();
		session.removeAttribute(SearchConstants.PRODUCT + SearchConstants.ASSOCIATION);
		session.removeAttribute(SearchConstants.PRODUCT_STRUCTURES + SearchConstants.ASSOCIATION);
		session.removeAttribute(SearchConstants.BENEFIT_COMPONENTS + SearchConstants.ASSOCIATION);
		session.removeAttribute(SearchConstants.BENEFIT + SearchConstants.ASSOCIATION);
		session.removeAttribute(SearchConstants.BENEFIT_LEVEL + SearchConstants.ASSOCIATION);
		
		getSession().removeAttribute(SearchConstants.VIEW_ASSOCIATION);

		SearchSortSessionObject object = (SearchSortSessionObject)getSession().getAttribute(SearchConstants.SORT_SESSION_OBJECT);
		if(null != object){
			object.clearAllAssociations();
		}
	}
	
	public String getAssociationModule(String type){
		if(SearchConstants.BENEFIT.equals(type)){
			return WebConstants.BENEFIT_COMPONENTS_MODULE;
		}
		else if(SearchConstants.BENEFIT_COMPONENTS.equals(type)){
			return WebConstants.PRODUCT_STRUCTURES_MODULE;
		}
		else if(SearchConstants.BENEFIT_LEVEL.equals(type)||SearchConstants.BENEFIT_LINE.equals(type)){
			return WebConstants.BENEFIT_MODULE;
		}
		else if(SearchConstants.BENEFIT_LINE.equals(type)||SearchConstants.BENEFIT_LINE.equals(type)){
			return WebConstants.BENEFIT_MODULE;
		}
		else if(SearchConstants.PRODUCT.equals(type)){
			return WebConstants.CONTRACT_MODULE;
		}
		else if(SearchConstants.PRODUCT_STRUCTURES.equals(type)){
			return WebConstants.PRODUCT_MODULE;
		}
		return null;
	}
	

	public String getModuleForType(String type){
		if(SearchConstants.BENEFIT.equals(type)){
			return WebConstants.BENEFIT_MODULE;
		}
		else if(SearchConstants.BENEFIT_COMPONENTS.equals(type)){
			return WebConstants.BENEFIT_COMPONENTS_MODULE;
		}
		else if(SearchConstants.BENEFIT_LEVEL.equals(type)||SearchConstants.BENEFIT_LINE.equals(type)){
			return WebConstants.BENEFIT_MODULE;
		}
		else if(SearchConstants.CONTRACT.equals(type)){
			return WebConstants.CONTRACT_MODULE;
		}
		else if(SearchConstants.NOTES.equals(type)){
			return WebConstants.NOTES_MODULE;
		}
		else if(SearchConstants.PRODUCT.equals(type)){
			return WebConstants.PRODUCT_MODULE;
		}
		else if(SearchConstants.PRODUCT_STRUCTURES.equals(type)){
			return WebConstants.PRODUCT_STRUCTURES_MODULE;
		}
		return null;
	}
	

	/**
	 * This will clear all the association object mutlipart search result in session
	 */
	public void clearAllAttachments() {
		HttpSession session = getSession();
		session.removeAttribute(SearchConstants.PRODUCT + SearchConstants.ATTACHMENTS);
		session.removeAttribute(SearchConstants.PRODUCT_STRUCTURES + SearchConstants.ATTACHMENTS);
		session.removeAttribute(SearchConstants.BENEFIT_COMPONENTS + SearchConstants.ATTACHMENTS);
		session.removeAttribute(SearchConstants.BENEFIT + SearchConstants.ATTACHMENTS);
		session.removeAttribute(SearchConstants.BENEFIT_LEVEL + SearchConstants.ATTACHMENTS);	
		session.removeAttribute(SearchConstants.BENEFIT_LINE + SearchConstants.ATTACHMENTS);		

		getSession().removeAttribute(SearchConstants.VIEW_ATTACHMENT);
		getSession().removeAttribute(SearchConstants.ATTACHMENT_RESULT_SUMMARY);
		
		SearchSortSessionObject object = (SearchSortSessionObject)getSession().getAttribute(SearchConstants.SORT_SESSION_OBJECT);
		if(null != object){
			object.clearAllAttachments();
		}
		
		
	}
	
	public void clearAllDetails(){
		getSession().removeAttribute(SearchConstants.BENEFIT+SearchConstants.OBJECT_DETAIL);
		getSession().removeAttribute(SearchConstants.BENEFIT_COMPONENTS+SearchConstants.OBJECT_DETAIL);
		getSession().removeAttribute(SearchConstants.BENEFIT_LEVEL+SearchConstants.OBJECT_DETAIL);
		getSession().removeAttribute(SearchConstants.BENEFIT_LINE+SearchConstants.OBJECT_DETAIL);
		getSession().removeAttribute(SearchConstants.CONTRACT+SearchConstants.OBJECT_DETAIL);
		getSession().removeAttribute(SearchConstants.NOTES+SearchConstants.OBJECT_DETAIL);
		getSession().removeAttribute(SearchConstants.PRODUCT+SearchConstants.OBJECT_DETAIL);
		getSession().removeAttribute(SearchConstants.PRODUCT_STRUCTURES+SearchConstants.OBJECT_DETAIL);
	}
	
	public void resetCache() {
		getSession().removeAttribute(SearchConstants.BENEFIT);
		getSession().removeAttribute(SearchConstants.BENEFIT_COMPONENTS);
		getSession().removeAttribute(SearchConstants.BENEFIT_LEVEL);
		getSession().removeAttribute(SearchConstants.CONTRACT);
		getSession().removeAttribute(SearchConstants.NOTES);
		getSession().removeAttribute(SearchConstants.PRODUCT);
		getSession().removeAttribute(SearchConstants.PRODUCT_STRUCTURES);
		
		getSession().removeAttribute(SearchConstants.SORT_SESSION_OBJECT);
		getSession().removeAttribute(SearchConstants.BREAD_CRUMB);
		getSession().removeAttribute(SearchConstants.OBJECT_TYPE);
		getSession().removeAttribute(SearchConstants.PAGE_NUMBER);
		getSession().removeAttribute(SearchConstants.ASSOCIATION_OBJECT_TYPE);
		getSession().removeAttribute(SearchConstants.VIEW_ASSOCIATION);
		getSession().removeAttribute(SearchConstants.OLD_BREAD_CRUMB);
		
		getSession().removeAttribute(SearchConstants.BENEFIT+SearchConstants.ASSOCIATION);
		getSession().removeAttribute(SearchConstants.BENEFIT_COMPONENTS+SearchConstants.ASSOCIATION);
		getSession().removeAttribute(SearchConstants.BENEFIT_LEVEL+SearchConstants.ASSOCIATION);
		getSession().removeAttribute(SearchConstants.CONTRACT+SearchConstants.ASSOCIATION);
		getSession().removeAttribute(SearchConstants.NOTES+SearchConstants.ASSOCIATION);
		getSession().removeAttribute(SearchConstants.PRODUCT+SearchConstants.ASSOCIATION);
		getSession().removeAttribute(SearchConstants.PRODUCT_STRUCTURES+SearchConstants.ASSOCIATION);

		getSession().removeAttribute(SearchConstants.BENEFIT+SearchConstants.ATTACHMENTS);
		getSession().removeAttribute(SearchConstants.BENEFIT_COMPONENTS+SearchConstants.ATTACHMENTS);
		getSession().removeAttribute(SearchConstants.BENEFIT_LEVEL+SearchConstants.ATTACHMENTS);
		getSession().removeAttribute(SearchConstants.BENEFIT_LINE+SearchConstants.ATTACHMENTS);
		getSession().removeAttribute(SearchConstants.CONTRACT+SearchConstants.ATTACHMENTS);
		getSession().removeAttribute(SearchConstants.NOTES+SearchConstants.ATTACHMENTS);
		getSession().removeAttribute(SearchConstants.PRODUCT+SearchConstants.ATTACHMENTS);
		getSession().removeAttribute(SearchConstants.PRODUCT_STRUCTURES+SearchConstants.ATTACHMENTS);

		getSession().removeAttribute(SearchConstants.BENEFIT+SearchConstants.OBJECT_DETAIL);
		getSession().removeAttribute(SearchConstants.BENEFIT_COMPONENTS+SearchConstants.OBJECT_DETAIL);
		getSession().removeAttribute(SearchConstants.BENEFIT_LEVEL+SearchConstants.OBJECT_DETAIL);
		getSession().removeAttribute(SearchConstants.BENEFIT_LINE+SearchConstants.OBJECT_DETAIL);
		getSession().removeAttribute(SearchConstants.CONTRACT+SearchConstants.OBJECT_DETAIL);
		getSession().removeAttribute(SearchConstants.NOTES+SearchConstants.OBJECT_DETAIL);
		getSession().removeAttribute(SearchConstants.PRODUCT+SearchConstants.OBJECT_DETAIL);
		getSession().removeAttribute(SearchConstants.PRODUCT_STRUCTURES+SearchConstants.OBJECT_DETAIL);

		getSession().removeAttribute(SearchConstants.VIEW_ATTACHMENT);
		getSession().removeAttribute(SearchConstants.ATTACHMENT_RESULT_SUMMARY);
		getSession().removeAttribute(SearchConstants.VIEW_ASSOCIATION);
		
		
	}
	public static String removeRelational(String searchWord){
		searchWord = searchWord.trim();
		if(searchWord.endsWith("AND")){
			int index = searchWord.lastIndexOf("AND");
			searchWord = searchWord.substring(0,index);
		}else if(searchWord.endsWith("OR")){
			int index = searchWord.lastIndexOf("OR");
			searchWord = searchWord.substring(0,index);
		}
		return searchWord.trim();
	}
		
	public static String createQueryString(List itemList,String searchWord){
		searchWord = searchWord.trim();
		String relation = "";
		if(searchWord.endsWith("AND")){
			relation = " AND ";
		}else if(searchWord.endsWith("OR")){
			relation = " OR ";
		}
		if(itemList==null ||itemList.size()==0){
			return " ( COLUMN_NAME = '-1' )"+relation;
		}
		StringBuffer queryString = new StringBuffer(" ( ");
		for(int i=0;i<itemList.size();i++){
			if(i < itemList.size()-1)
				queryString.append(" COLUMN_NAME = upper('"+itemList.get(i).toString()+"')  OR ");
			else
				queryString.append(" COLUMN_NAME = upper('"+itemList.get(i).toString()+"')");
		}
		queryString.append(" ) ");
		queryString.append(relation);
		return queryString.toString();
	}	
	
	public static String createQueryWithInteger(List itemList){

		if(itemList==null ||itemList.size()==0){
			return " ( COLUMN_NAME = '-1' )";
		}
		StringBuffer queryString = new StringBuffer(" ( ");
		for(int i=0;i<itemList.size();i++){
			if(i < itemList.size()-1)
				queryString.append(" COLUMN_NAME = '"+itemList.get(i).toString()+"'  OR ");
			else
			    queryString.append(" COLUMN_NAME = '"+itemList.get(i).toString()+"'");
		}
		queryString.append(" ) ");
		return queryString.toString();
	}	
		
	
	public static String createQueryStringForTerm(List itemList, boolean equalSign){
		if(itemList==null ||itemList.size()==0){
			return " ( COLUMN_NAME = '-1' )";
		}
		StringBuffer queryString = new StringBuffer(" ( ");
		for(int i=0;i<itemList.size();i++){
			if(equalSign){
				if(i < itemList.size()-1)
					queryString.append(" COLUMN_NAME = upper('"+itemList.get(i).toString()+"')  OR ");
				else
					queryString.append(" COLUMN_NAME = upper('"+itemList.get(i).toString()+"')");
			}else{
				if(i < itemList.size()-1)
					queryString.append(" COLUMN_NAME like upper('%"+itemList.get(i).toString()+"%')  OR ");
				else
					queryString.append(" COLUMN_NAME like upper('%"+itemList.get(i).toString()+"%')");
			}
		}
		queryString.append(" ) ");
		return queryString.toString();
	}

	/**
	 * @param type
	 * @return
	 */
	public String getModule(String type) {

		if(SearchConstants.BENEFIT.equals(type)){
			return WebConstants.BENEFIT_MODULE;
		}
		else if(SearchConstants.BENEFIT_COMPONENTS.equals(type)){
			return WebConstants.BENEFIT_COMPONENTS_MODULE;
		}
		else if(SearchConstants.PRODUCT.equals(type)){
			return WebConstants.PRODUCT_MODULE;
		}
		else if(SearchConstants.PRODUCT_STRUCTURES.equals(type)){
			return WebConstants.PRODUCT_STRUCTURES_MODULE;
		}else if(SearchConstants.CONTRACT.equals(type)){
			return WebConstants.CONTRACT_MODULE;
		}else if(SearchConstants.NOTES.equals(type)){
			return WebConstants.NOTES_MODULE;
		}
		return null;
	}

	public static String createQueryStringForSelectedPsblAnswers(List listvalue){

		StringBuffer queryString = new StringBuffer(" ( ");
        for(int i=0;i<listvalue.size();i++){
	        String psblAnswrSelected=(String)listvalue.get(i);
	        /*queryString.append(" COLUMN_NAME = upper('"+psblAnswrSelected+"')  OR ");
			queryString.append(" COLUMN_NAME like upper('"+psblAnswrSelected+",%')  OR ");
			queryString.append(" COLUMN_NAME like upper('%,"+psblAnswrSelected+",%') OR");*/
		    if(i==listvalue.size()-1)
		        queryString.append(" COLUMN_NAME like '%"+psblAnswrSelected+"%'");
		    else
		    queryString.append(" COLUMN_NAME like '%"+psblAnswrSelected+"%' OR ");	
        }
        if(listvalue.size()==0)
        	   queryString.append(" COLUMN_NAME like '%' ");	
		queryString.append(" ) ");
		return queryString.toString();
	}
	public static String createQueryStringForAdminMethodsTerm(List listvalue){

		StringBuffer queryString = new StringBuffer(" ( ");
        for(int i=0;i<listvalue.size();i++){
	        String term=(String)listvalue.get(i);
		    if(i==listvalue.size()-1)
		    queryString.append(" COLUMN_NAME = '"+term+"'");
		    else
		    queryString.append(" COLUMN_NAME = '"+term+"' OR ");	
        }
        if(listvalue.size()==0)
        	   queryString.append(" COLUMN_NAME like '%' ");	
		queryString.append(" ) ");
		return queryString.toString();
	}
	public static String createQueryStringForSelectedPsblAnswer(String psblAnswrSelected){

		StringBuffer queryString = new StringBuffer(" ( ");

		queryString.append(" COLUMN_NAME like '"+psblAnswrSelected+",%'  OR ");
		queryString.append(" COLUMN_NAME like '%,"+psblAnswrSelected+",%' OR");
		queryString.append(" COLUMN_NAME = '"+psblAnswrSelected+"' OR");
		queryString.append(" COLUMN_NAME = 'null' OR");
		queryString.append(" COLUMN_NAME = '' OR");
		queryString.append(" COLUMN_NAME like '%,"+psblAnswrSelected+"'");
		queryString.append(" ) ");
		return queryString.toString();
	}

}
