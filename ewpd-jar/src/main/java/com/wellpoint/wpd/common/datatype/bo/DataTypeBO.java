/*
 * Created on Mar 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.datatype.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DataTypeBO extends BusinessObject{
	
	private int dataTypeId;
	
	private String dataTypeName;
	
	private String dataTypeDesc;
	
	private String dataTypeLgnd;
	
    /**
     * Returns the dataTypeDesc
     * @return String dataTypeDesc.
     */

    public String getDataTypeDesc() {
        return dataTypeDesc;
    }
    /**
     * Sets the dataTypeDesc
     * @param dataTypeDesc.
     */

    public void setDataTypeDesc(String dataTypeDesc) {
        this.dataTypeDesc = dataTypeDesc;
    }
	/**
	 * @return Returns the dataTypeId.
	 */
	public int getDataTypeId() {
		return dataTypeId;
	}
	/**
	 * @param dataTypeId The dataTypeId to set.
	 */
	public void setDataTypeId(int dataTypeId) {
		this.dataTypeId = dataTypeId;
	}
	/**
	 * @return Returns the dataTypeName.
	 */
	public String getDataTypeName() {
		return dataTypeName;
	}
	/**
	 * @param dataTypeName The dataTypeName to set.
	 */
	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}
	
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
     * Returns the dataTypeLgnd
     * @return String dataTypeLgnd.
     */

    public String getDataTypeLgnd() {
        return dataTypeLgnd;
    }
    /**
     * Sets the dataTypeLgnd
     * @param dataTypeLgnd.
     */

    public void setDataTypeLgnd(String dataTypeLgnd) {
        this.dataTypeLgnd = dataTypeLgnd;
    }
}
