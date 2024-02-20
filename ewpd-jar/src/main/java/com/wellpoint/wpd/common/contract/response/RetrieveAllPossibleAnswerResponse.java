/*
 * RetrieveAllPossibleAnswerResponse 
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;
import java.util.HashMap;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveAllPossibleAnswerResponse  extends ContractResponse {
    
    private List allPossibleAnswerList;
    
    private HashMap allPossibleAnswerMap = new HashMap();
 

    /**
     * Returns the benefitAdministrationList
     * @return List benefitAdministrationList.
     */
    public List getAllPossibleAnswerList() {
        return allPossibleAnswerList;
    }
    /**
     * Sets the benefitAdministrationList
     * @param benefitAdministrationList.
     */
    public void setAllPossibleAnswerList(List allPossibleAnswerList) {
        this.allPossibleAnswerList = allPossibleAnswerList;
    }
    
	public HashMap getAllPossibleAnswerMap() {
		return allPossibleAnswerMap;
	}
	
	public void setAllPossibleAnswerMap(HashMap allPossibleAnswerMap) {
		this.allPossibleAnswerMap = allPossibleAnswerMap;
	}

    
    
}
