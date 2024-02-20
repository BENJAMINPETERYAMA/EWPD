/*
 * SearchObject.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.criteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SearchObject.java 24689 2007-06-21 11:42:53Z u12046 $
 */
public abstract class SearchObject {
    protected String type;
    protected boolean checked;
    /**
     * @return Returns the checked.
     */
    public boolean isChecked() {
        return checked;
    }
    /**
     * @param checked The checked to set.
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
}
