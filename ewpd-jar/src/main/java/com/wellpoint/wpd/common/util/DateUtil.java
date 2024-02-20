/*
 * DateUtil.java
 * Created on Jul 13, 2006
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */

package com.wellpoint.wpd.common.util;

import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Class for Date utility functions.
 */
public class DateUtil {

    /**
     * Create & Returns Default Effective Date.
     * @return Default Effective Date.
     */
    public static Date getDefaultEffectiveDate() {
        return (new GregorianCalendar(1900, 00, 01).getTime());
    }

    /**
     * Create & Returns Default End Date.
     * @return Default Effective Date.
     */
    public static Date getDefaultEndDate() {
        return (new GregorianCalendar(9999, 11, 31).getTime());
    }

}