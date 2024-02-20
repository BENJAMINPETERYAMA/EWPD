/*
 * <ReferenceDataUtil.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.referencedata.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author UST-GLOBAL Util class
 * 
 */
public class ReferenceDataUtil implements Serializable {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Comment for <code>EXCLUSION_PROPERTIES</code>
	 */
	public static final String EXCLUSION_PROPERTIES = "referencedatamessages";

	/**
	 * @param source
	 * @return Trim and return the string.
	 */
	public static String trimAndNullToEmptyString(String fieldString) {
		if ((null == fieldString)) {
			fieldString = "";
		} else {
			fieldString = fieldString.trim();
		}
		return fieldString;
	}

	/**
	 * @param date
	 *            Date.
	 * @return String which contains the Date.
	 */
	public static String convertDateToString(Date date) {
		String dateString = null;
		if (null != date) {
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			dateString = df.format(date);
		}
		return dateString;
	}

}
