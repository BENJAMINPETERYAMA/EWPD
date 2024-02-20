/*
 * AdvancedAttribute.java
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
 * @version $Id: AdvancedAttribute.java 26313 2007-07-05 21:28:59Z U12046 $
 */
public class AdvancedAttribute extends Attribute {
	
	private String relation;
	
	private String sign;
	
	private boolean checked;

	
	 

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
	 * @return Returns the relation.
	 */
	public String getRelation() {
		return relation;
	}
	/**
	 * @param relation The relation to set.
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}
	/**
	 * @return Returns the sign.
	 */
	public String getSign() {
		return sign;
	}
	/**
	 * @param sign The sign to set.
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	

}
