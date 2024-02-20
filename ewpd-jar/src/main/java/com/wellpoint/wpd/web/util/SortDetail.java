/*
 * Created on Jul 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.util;

import java.io.Serializable;

/**
 * @author U12218
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SortDetail implements Serializable{

	private int sortColumn;
	
	private String sortDirection;
	

	/**
	 * @return Returns the sortDirection.
	 */
	public String getSortDirection() {
		return sortDirection;
	}
	/**
	 * @param sortDirection The sortDirection to set.
	 */
	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
	/**
	 * @return Returns the sortColumn.
	 */
	public int getSortColumn() {
		return sortColumn;
	}
	/**
	 * @param sortColumn The sortColumn to set.
	 */
	public void setSortColumn(int sortOrder) {
		this.sortColumn = sortOrder;
	}
}
