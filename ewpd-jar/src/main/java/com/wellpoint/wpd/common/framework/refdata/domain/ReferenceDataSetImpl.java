/*
 * ReferenceDataSetImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.refdata.domain;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ReferenceDataSetImpl.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public class ReferenceDataSetImpl implements ReferenceDataSet{
    private int id;
    private String name;
    private List referenceData;
    /**
     * @return Returns the id.
     */
    public int getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return Returns the referenceData.
     */
    public List getReferenceData() {
        return referenceData;
    }
    /**
     * @param referenceData The referenceData to set.
     */
    public void setReferenceData(List referenceData) {
        this.referenceData = referenceData;
    }
    
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("ID:").append(id).append(" ");
        buffer.append("NAME:").append(name).append(" ");
        if(referenceData != null){
            buffer.append("REFERENCE DATA:").append(referenceData);
        }
        return buffer.toString();
    }
}
