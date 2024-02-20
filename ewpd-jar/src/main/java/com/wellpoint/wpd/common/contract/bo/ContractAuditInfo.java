/*
 * Created on Jun 9, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U14611
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractAuditInfo extends BusinessObject{
	
	private int contractSysID;
	private String contractID;
	
	 public boolean equals(BusinessObject object) {
        // TODO Auto-generated method stub
        return false;
    }

    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }   
	/**
	 * @return Returns the contractSysID.
	 */
	public int getContractSysID() {
		return contractSysID;
	}
	/**
	 * @param contractSysID The contractSysID to set.
	 */
	public void setContractSysID(int contractSysID) {
		this.contractSysID = contractSysID;
	}
	
	/**
	 * @return Returns the contractID.
	 */
	public String getContractID() {
		return contractID;
	}
	/**
	 * @param contractID The contractID to set.
	 */
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
}

