package com.wellpoint.ets.bx.mapping.domain.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.ValidatorConstants;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;

public class HippaSegmentValidationResult {

	private short status;
	
	private List  successStatusCode = new ArrayList();
	private List failureStatusCode = new ArrayList();
	private List warningStatusCode = new ArrayList();
	
	private Mapping mapping;
	/**
	 * @return Returns the mapping.
	 */
	public Mapping getMapping() {
		return mapping;
	}
	/**
	 * @param mapping The mapping to set.
	 */
	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}
	
	/**Method to add the status code in the successStatusCode list
	 * @param statusCode
	 */
	public void addSuccessCode(String statusCode) {
		Map map = new HashMap();
		map.put(statusCode, null);
		successStatusCode.add(map);
	}
	
	/**Method to add the status code in the successStatusCode list
	 * as List<Map<statusCode, placeHoldValues>>.
	 * @param statusCode
	 */
	public void addSuccessCode(String statusCode, String[] placeHoldValues) {
		Map map = new HashMap();
		map.put(statusCode, placeHoldValues);
		successStatusCode.add(map);
	}
	
	/**Method to add the status code in the warningStatusCode list
	 * @param statusCode
	 */
	public void addWarningCode(String statusCode) {
		Map map = new HashMap();
		map.put(statusCode, null);
		warningStatusCode.add(map);
	}
	
	/**Method to add the status code in the warningStatusCode list
	 * as List<Map<statusCode, placeHoldValues>>.
	 * @param statusCode
	 */
	public void addWarningCode(String statusCode, String[] placeHoldValues) {
		Map map = new HashMap();
		map.put(statusCode, placeHoldValues);
		warningStatusCode.add(map);
	}
	
	/**Method to add the status code in the failureStatusCode list
	 * @param statusCode
	 */
	public void addFailureCode(String statusCode) {
		Map map = new HashMap();
		map.put(statusCode, null);
		failureStatusCode.add(map);
	}
	/**
	 *  Adds failure codes as List<Map<statusCode, placeHoldValues>>.
	 * @param statusCode
	 * @param placeHoldValues
	 */
	public void addFailureCode(String statusCode, String[] placeHoldValues) {
		Map map = new HashMap();
		map.put(statusCode, placeHoldValues);
		failureStatusCode.add(map);
	}
	
	public boolean isSuccess(){
		if(failureStatusCode.size() > 0) return false;
		return true;
	}		
	
	/**
	 * @return Returns the failureStatusCode.
	 */
	public List getFailureStatusCodes() {
		return getCodeKeyList(failureStatusCode);
	}
	/**
	 * @return Returns the successStatusCode.
	 */
	public List getSuccessStatusCode() {
		return getCodeKeyList(successStatusCode);
	}
	
	/**
	 * @return Returns the successStatusCode.
	 */
	public List getWarningStatusCode() {
		return getCodeKeyList(warningStatusCode);
	}
	
	public List getFailureStatusCodeWithPlaceValues() {
		return failureStatusCode;
	}
	/**
	 * @return Returns the successStatusCode.
	 */
	public List getSuccessStatusCodeWithPlaceValues() {
		return successStatusCode;
	}
	
	private List getCodeKeyList(List statusCodes) {
		List codes = new ArrayList();
		if(null != statusCodes && !statusCodes.isEmpty()){
			for (Iterator iterator = statusCodes.iterator(); iterator.hasNext();) {
				Object obj = (Object) iterator.next();
				Map map = (Map)obj;
				Set mapKeys = map.keySet();
				for (Iterator iterator2 = mapKeys.iterator(); iterator2.hasNext();) {
					String key = (String) iterator2.next();
					codes.add((String)key);
				}
			}
		}
		return codes;
	}
	
	
	/**
	 * @return Returns the status.
	 */
	public short getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(short status) {
		this.status = status;
	}
	
	public List getFailureMessages() {
		return getMessages(failureStatusCode);
    }
	
	public List getSucessMessages() {
		return getMessages(successStatusCode);
    }
	
	public List getWarningMessages() {
		return getMessages(warningStatusCode);
    }
	
	private List getMessages(List statusCodes) {
		List messages = new ArrayList();
		List list = new ArrayList();
		if(null != statusCodes && !statusCodes.isEmpty()){
		for (Iterator iterator = statusCodes.iterator(); iterator
			.hasNext();) {
		Map	keyParams = (Map) iterator.next();
		if(null != keyParams){
			String[] placeHolders = (String[])keyParams.get(ValidatorConstants.INVALID_HIPPACODE_VALUE);
			if(placeHolders != null){
				for (int i = 0; i < placeHolders.length; i++) {
					
					if(placeHolders[i].equals(DomainConstants.NOTE_TYPE_CODE)){
						
						placeHolders[i] = placeHolders[i].replaceAll(DomainConstants.NOTE_TYPE_CODE, "Note Type");
						keyParams.put(ValidatorConstants.INVALID_HIPPACODE_VALUE, placeHolders);
					}
				}
			}	
		}
		
		if(null != keyParams && keyParams.size()!= 0 && !keyParams.isEmpty()){
			list = BxResourceBundle.getResourceBundle(keyParams);
		}
		messages.addAll(list);
		}
		}
    	return messages;
	}
	/**
	 * @return true if accum not required checkbox should be set.
	 */
	//Removed as part of July release
	/*public boolean isAccumNotRequired(){
	    boolean notReqd = false;
	    List warningStatusCodeList = getWarningStatusCode();
	    if(warningStatusCodeList!=null && warningStatusCodeList.contains((String)ValidatorConstants.ACCUM_NOT_REQD_INDICATOR_WARNING)){
	        notReqd = true;
	    }
	    return notReqd;
	}*/
    
}
