/*
 * Created on Jul 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.search.result;

import java.util.List;

/**
 * @author u12218
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TermIdDetail {
	
	private List termIds;
	
	private List description;

	/**
	 * @return Returns the description.
	 */
	public List getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(List description) {
		this.description = description;
	}
	/**
	 * @return Returns the termIds.
	 */
	public List getTermIds() {
		return termIds;
	}
	/**
	 * @param termIds The termIds to set.
	 */
	public void setTermIds(List termIds) {
		this.termIds = termIds;
	}
}
