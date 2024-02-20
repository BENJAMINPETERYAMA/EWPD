/*
 * InformationElement.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */

package com.wellpoint.wpd.common.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: InformationElement.java 10591 2007-02-15 23:23:05Z U10567 $
 */
public class InformationElement {
	/**
	 * @return Returns the attributeType.
	 */
	public String getAttributeType() {
		return attributeType;
	}
	/**
	 * @return Returns the dataType.
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @return Returns the elementName.
	 */
	public String getElementName() {
		return elementName;
	}
	/**
	 * @param attributeType The attributeType to set.
	 */
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}
	/**
	 * @param dataType The dataType to set.
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * @param elementName The elementName to set.
	 */
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	private String elementName;
	private String dataType;
	private String attributeType;
}
