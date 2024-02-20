/*
 * ReferenceDataImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.refdata.domain;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ReferenceDataImpl.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public class ReferenceDataImpl implements ReferenceData {

    private int id;
    private String code;
    private String name;
    private String description;
    /**
     * 
     */
    public ReferenceDataImpl() {
        super();
    }
    
    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code The code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
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
    
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("ID:").append(id).append(" ");
        buffer.append("CODE:").append(code).append(" ");
        buffer.append("DESCRIPTION:").append(description);
        return buffer.toString();
    }

    /**
     * @param obj
     */
	public int compareTo(Object obj) {
		ReferenceDataImpl referenceDataImpl = (ReferenceDataImpl)obj;
		int compareFlag = name.compareTo(referenceDataImpl.name);
		return compareFlag;
	}

}
