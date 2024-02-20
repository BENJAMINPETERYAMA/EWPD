/*
 * ContractIdentifier.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.search.result;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ContractIdentifier.java 28350 2007-07-27 18:48:56Z U12046 $
 */
public class ContractIdentifier extends ObjectIdentifier {
	private int identifier;
	private int dateSegIdentifier;
	/**
	 * @return Returns the identifier.
	 */
	public int getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier The identifier to set.
	 */
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	/**
	 * @return Returns the dateSegIdentifier.
	 */
	public int getDateSegIdentifier() {
		return dateSegIdentifier;
	}
	/**
	 * @param dateSegIdentifier The dateSegIdentifier to set.
	 */
	public void setDateSegIdentifier(int dateSegIdentifier) {
		this.dateSegIdentifier = dateSegIdentifier;
	}
}
