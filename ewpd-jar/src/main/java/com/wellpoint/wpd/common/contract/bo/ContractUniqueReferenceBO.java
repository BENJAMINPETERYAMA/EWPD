/*
 * Created on Dec 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author u19142
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractUniqueReferenceBO extends BusinessObject{

	private int dateSegmentSysId;
	private String dateRange;		
	private List duplicateList;
	
	
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the dateSegmentSysId.
	 */
	public int getDateSegmentSysId() {
		return dateSegmentSysId;
	}
	/**
	 * @param dateSegmentSysId The dateSegmentSysId to set.
	 */
	public void setDateSegmentSysId(int dateSegmentSysId) {
		this.dateSegmentSysId = dateSegmentSysId;
	}
	/**
	 * @return Returns the duplicateList.
	 */
	public List getDuplicateList() {
		return duplicateList;
	}
	/**
	 * @param duplicateList The duplicateList to set.
	 */
	public void setDuplicateList(List duplicateList) {
		this.duplicateList = duplicateList;
	}
	
   
    /**
     * @return Returns the dateRange.
     */
    public String getDateRange() {
        return dateRange;
    }
    /**
     * @param dateRange The dateRange to set.
     */
    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }
}
