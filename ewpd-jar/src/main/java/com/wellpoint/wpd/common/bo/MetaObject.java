/*
 * MetaObject.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */

package com.wellpoint.wpd.common.bo;

import java.util.List;
import java.util.Map;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: MetaObject.java 49164 2008-09-29 10:42:58Z u18600 $
 */
public interface MetaObject {
    /**
     * @return Returns the name.
     */
    public String getName();

    /**
     * @return Returns the type.
     */
    public String getType();

    /**
     * @return Returns the builderClassName.
     */
    public String getBuilderClassName();
    
    /**
     * @return Returns the Locate Criteria class name
     */
    public List getLocateCriteriae();

    /**
     * @return Returns the checkOutDuration.
     */
    public int getCheckOutDuration();

    /**
     * @return Returns the keyAttributes.
     */
    public List getKeyAttributes();

    /**
     * @return Returns the transitions.
     */
    public Map getTransitions();
    /**
	 * @return Returns the module.
	 */
	public String getModule() ;
	/**
	 * @param module The module to set.
	 */
	public void setModule(String module) ;
	
	public Map getActivities() ;
	
	public String getLoggerName();

}