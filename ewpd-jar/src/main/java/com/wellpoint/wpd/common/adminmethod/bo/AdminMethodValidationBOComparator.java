/*
 * Created on Dec 13, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.bo;

import java.util.Comparator;

/**
 * @author U17066
 *
 * For AdminMethodValidationBO sorting based on the SPS Name
 To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodValidationBOComparator implements Comparator {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		String spsName1 = ((AdminMethodValidationBO )o1 ).getSpsName();
		String spsName2 = ((AdminMethodValidationBO )o2 ).getSpsName();
		
		return spsName1.compareTo(spsName2);
	}

}
