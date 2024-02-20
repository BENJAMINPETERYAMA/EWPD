/*
 * Created on Jun 4, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.SPSDetail;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class ValidationUtility {

    /**
     * (BTRD)T2.1.22	HSD01 and HSD02 codes will only come together. If one is used other is also needed.
     * @param hippaSegmentValidationResult
     * @param hsd1
     * @param hsd2
     * @return
     */
	
	//BXNI Change
    public boolean validateHSDComparison(String hsd1, String hsd2){
        boolean pass = true;
        if(null == hsd1 || "".equalsIgnoreCase(hsd1.trim())){
            if(null != hsd2 && !"".equalsIgnoreCase(hsd2.trim())){
                pass = false;
            }
        }
        return pass;
    }
    //BXNI COde ends 
    
    public boolean compare(String hsd1, String hsd2){
        boolean pass = true;
        if(null == hsd1 || "".equalsIgnoreCase(hsd1.trim())){
            if(null != hsd2 && !"".equalsIgnoreCase(hsd2.trim())){
                pass = false;
            }
        }        
        else{
            if(null != hsd2 && "".equalsIgnoreCase(hsd2.trim())){
                    pass = false;
            }
        }
        return pass;
    }
    
    public boolean compare(double min, double max, double value){
        if (value < min)
            return false;       
        
        return true;
    }
    
    //remove blank entries from the list
    public List removeBlankfromList(List list){
        List newList = new ArrayList();
        if(list !=null){
            for(Iterator itr = list.iterator();itr.hasNext();){
                String obj = (String)itr.next();
                if(!obj.trim().equals("")){
                    newList.add((String)obj.trim());
                }
            }
            if(newList != null && !newList.isEmpty()){
                return newList;
            }
        }
        return null;
    }
    
    /**
     * check duplicates in the hippacodeselected list
     * @param list
     * @return
     */
    public boolean checkDuplicate(List list) {
    	 HashSet set = new HashSet();
    	 for (int i = 0; i < list.size(); i++) {
    	  boolean val = set.add(list.get(i));
    	  if (val == false) {
    	  	return val;
    	  }
    	 }
    	 return true;
    	}
    /**
     * Remove duplicates from list
     * @param arlList
     */
    public List removeDuplicate(List arlList){    
	     HashSet h = new HashSet(arlList);
	     arlList.clear();
	     arlList.addAll(h);
	     return arlList;
    }
    /**
     * set the list of SPS formats from the SPS detail object
     * @param spsDetail
     * @return
     */
    public List getSpsFormatsFromSPSDetail(List spsDetails){
    	
    	List spsFormats = new ArrayList();
    	 for(int i=0;i<spsDetails.size();i++){
    		 
    		 SPSDetail spsDetail = (SPSDetail) spsDetails.get(i);
    		 if(null != spsDetail){
    			 
    			 if(null != spsDetail.getSpsDataType()){
    				 
    				 spsFormats.add(spsDetail.getSpsDataType());
    			 }
    		 }
    	 }    	 
    	return removeDuplicate(spsFormats);
    }
    
public List getSpsDescriptionsFromSPSDetail(List spsDetails){
    	
    	List spsDescriptions = new ArrayList();
    	 for(int i=0;i<spsDetails.size();i++){
    		 
    		 SPSDetail spsDetail = (SPSDetail) spsDetails.get(i);
    		 if(null != spsDetail){
    			 
    			 if(null != spsDetail.getSpsDescription()){
    				 
    				 spsDescriptions.add(spsDetail.getSpsDescription());
    			 }
    		 }
    	 }    	 
    	return removeDuplicate(spsDescriptions);
    }
    
    /**
     * Check whether the String value has a empty value
     * @param value
     * @return
     */
    public boolean isEmpty(String value) {
		if (value != null && !value.trim().equals("")) {
			return false;
		}
		return true;
	}
    /**
     * Check whether the List as empty values
     * @param list
     * @return
     */
    public boolean isEmpty(List list){       
        if(list !=null){
            for(Iterator itr = list.iterator();itr.hasNext();){
                String obj = (String)itr.next();
                if(obj != null && !obj.trim().equals("")){
                    return false;
                }
                return true;
            }
        }
        return false;
    }
}
