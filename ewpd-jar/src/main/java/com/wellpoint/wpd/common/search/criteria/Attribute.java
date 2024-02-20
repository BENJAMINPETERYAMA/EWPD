/*
 * Attribute.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.criteria;

import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: Attribute.java 36390 2007-10-14 13:03:39Z u12218 $
 */
public abstract class Attribute {
    protected String name;
    protected String criteria;
    protected String query;
    protected String type ;
    protected boolean special;
    protected List queryList;
    /**
     * @return Returns the criteria.
     */
    public String getCriteria() {
        return criteria;
    }
    /**
     * @param criteria The criteria to set.
     */
    public void setCriteria(String criteria) {
        this.criteria = criteria;
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
     * @return Returns the query.
     */
    public String getQuery() {
        return query;
    }
    /**
     * @param query The query to set.
     */
    public void setQuery(String query) {
        this.query = query;
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
	/**
	 * @return Returns the queryList.
	 */
	public List getQueryList() {
		return queryList;
	}
	/**
	 * @param queryList The queryList to set.
	 */
	public void setQueryList(List queryList) {
		this.queryList = queryList;
	}
	/**
	 * @return Returns the special.
	 */
	public boolean isSpecial() {
		return special;
	}
	/**
	 * @param special The special to set.
	 */
	public void setSpecial(boolean special) {
		this.special = special;
	}
}
