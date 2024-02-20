/*
 * <Catalog.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
*/
package com.wellpoint.ets.bx.mapping.domain.entity;

/**
 * @author UST-GLOBAL
 * This is an entity class to represent a catalog i.e a hippacode name and description
 */
public class Catalog {

	private	String catalogDesc;
	private String catalogName;
	
	/**
	 * @return 
	 */
	public String getCatalogDesc() {
		return catalogDesc;
	}

	/**
	 * @param catalogDesc
	 */
	public void setCatalogDesc(String catalogDesc) {
		this.catalogDesc = catalogDesc;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}		
		
}
