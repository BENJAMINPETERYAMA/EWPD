/*
 * Page.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search.pagination;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: Page.java 24689 2007-06-21 11:42:53Z u12046 $
 */
public class Page {
    Object[] objects;
    int number;
    
    /**
     * @return Returns the objects.
     */
    public Object[] getObjects() {
        return objects;
    }
    /**
     * @param objects The objects to set.
     */
    public void setObjects(Object[] objects) {
        this.objects = objects;
    }
    
    
    /**
     * @return Returns the number.
     */
    public int getNumber() {
        return number;
    }
    /**
     * @param number The number to set.
     */
    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumberOfRecords(){
        if(objects == null)return 0;
        return objects.length;
    }
}
