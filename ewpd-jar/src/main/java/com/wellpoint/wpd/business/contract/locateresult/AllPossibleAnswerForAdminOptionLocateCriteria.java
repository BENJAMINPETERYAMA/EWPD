/*
 * AllPossibleAnswerForAdminOptionLocateCriteria
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.locateresult;

import java.util.List;

import com.wellpoint.wpd.common.bo.LocateCriteria;
import com.wellpoint.wpd.common.contract.bo.ContractQuesitionnaireBO;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AllPossibleAnswerForAdminOptionLocateCriteria  extends LocateCriteria {
    
	
	private int adminOptSysId;
	
	
    /**
     * Returns the adminOptSysId
     * @return int adminOptSysId.
     */
    public int getAdminOptSysId() {
        return adminOptSysId;
    }
    /**
     * Sets the adminOptSysId
     * @param adminOptSysId.
     */
    public void setAdminOptSysId(int adminOptSysId) {
        this.adminOptSysId = adminOptSysId;
    }
   
}
