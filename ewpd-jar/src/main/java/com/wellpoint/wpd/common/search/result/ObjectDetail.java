/*
 * ObjectDetail.java
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
 * @version $Id: ObjectDetail.java 24689 2007-06-21 11:42:53Z u12046 $
 */
public abstract class ObjectDetail {
    public abstract ObjectIdentifier getIdentifier();
    public abstract void setIdentifier(ObjectIdentifier identifier);
}
