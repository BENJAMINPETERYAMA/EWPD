/*
 * SearchResultBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search;

import com.wellpoint.wpd.web.framework.WPDBackingBean;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public abstract class SearchResultBackingBean extends WPDBackingBean {
    
    private String fieldToSort;
    
    private String sortType;
    

    public abstract void viewResult();   

    public abstract String viewAssociation();
    
    public abstract void viewMoreActions();
    
    public abstract void sort();
    
    
    /**
     * @return Returns the fieldToSort.
     */
    public String getFieldToSort() {
        return fieldToSort;
    }
    /**
     * @param fieldToSort The fieldToSort to set.
     */
    public void setFieldToSort(String fieldToSort) {
        this.fieldToSort = fieldToSort;
    }
    /**
     * @return Returns the sortType.
     */
    public String getSortType() {
        return sortType;
    }
    /**
     * @param sortType The sortType to set.
     */
    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

}

