/*
 * Created on Jul 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.util;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

//import com.ibm.wsspi.sib.exitpoint.ra.HashMap;
import com.wellpoint.wpd.common.search.util.SearchConstants;
import com.wellpoint.wpd.web.search.util.SearchUtil;

/**
 * @author U12218
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchSortSessionObject  implements Serializable{
	
	private Map sortInformationMap = new HashMap();
	
	public void setSortInfo(String objectType, SortDetail  sortDetail){
		if( null != objectType && !"".equals(objectType) && null != sortDetail){
			sortInformationMap.put(objectType, sortDetail);
		}
	}
	
	public SortDetail getSortInfo(String objectType){
		if( null != objectType && !"".equals(objectType)){
			SortDetail detail = (SortDetail)sortInformationMap.get(objectType);
			return detail;
		}
		return null;
	}
	
	public void clearAllAssociations(){
		SearchUtil searchUtil = new SearchUtil();
		List list =searchUtil.getSearchResultKeys();
		String key;
		Object value;
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			key = (String)iterator.next();
			value = sortInformationMap.get(key+SearchConstants.ASSOCIATION);
			if(null != value){
				sortInformationMap.remove(key+SearchConstants.ASSOCIATION);
			}
		}
	}
	

	public void clearAllAttachments(){
		SearchUtil searchUtil = new SearchUtil();
		List list =searchUtil.getOrderedAttachementKeys();
		String key;
		Object value;
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			key = (String)iterator.next();
			value = sortInformationMap.get(key+SearchConstants.ATTACHMENTS);
			if(null != value){
				sortInformationMap.remove(key+SearchConstants.ATTACHMENTS);
			}
		}
	}
	

}
