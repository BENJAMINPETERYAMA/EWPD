/*
 * Created on Aug 2, 2011
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.util;

import java.util.Comparator;

import com.wellpoint.wpd.common.report.bo.RuleReportBO;

/**
 * @author U23914
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RuleReportSort  implements Comparator {

	
	public int compare(Object o1, Object o2) {
		RuleReportBO ro1 = (RuleReportBO) o1;
		RuleReportBO ro2 = (RuleReportBO) o2;
		if(null == ro1.getBenefit()){
			return -1;
		}
		if(null == ro2.getBenefit()){
			return 1;
		}
		return ro1.getBenefit().compareToIgnoreCase(ro2.getBenefit());
	}
	
}
