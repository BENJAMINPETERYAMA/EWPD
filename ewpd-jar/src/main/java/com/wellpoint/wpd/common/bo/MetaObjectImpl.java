/*
 * MetaObjectImpl.java
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
 * @version $Id: MetaObjectImpl.java 49165 2008-09-29 10:43:41Z u18600 $
 */
public class MetaObjectImpl implements MetaObject {

	private String name;
	private String type;
	private String module;
	private int checkOutDuration;
	private String builderClassName;
	private List locateCriteriae;
	private List keyAttributes;
	private Map transitions;
	private Map activities;
	private String loggerName;
	
	/**
	 * @return Returns the loggerName.
	 */
	public String getLoggerName() {
		return loggerName;
	}
	/**
	 * @param loggerName The loggerName to set.
	 */
	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return Returns the builderClassName.
	 */
	public String getBuilderClassName() {
		return builderClassName;
	}
	
	/**
	 * @return Returns the locate criteria class name.
	 */
	public List getLocateCriteriae(){
	    return locateCriteriae;
	}
	/**
	 * @return Returns the checkOutDuration.
	 */
	public int getCheckOutDuration() {
		return checkOutDuration;
	}
	/**
	 * @return Returns the keyAttributes.
	 */
	public List getKeyAttributes() {
		return keyAttributes;
	}
	/**
	 * @return Returns the transitions.
	 */
	public Map getTransitions() {
		return transitions;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}	
	/**
	 * @param builderClassName The builderClassName to set.
	 */
	public void setBuilderClassName(String builderClassName) {
		this.builderClassName = builderClassName;
	}
	
	/**
	 * @param locateCriteria The locateCriteria class name to set.
	 */
	public void setLocateCriteriae(List locateCriteriae) {
		this.locateCriteriae = locateCriteriae;
	}	
	/**
	 * @param checkOutDuration The checkOutDuration to set.
	 */
	public void setCheckOutDuration(int checkOutDuration) {
		this.checkOutDuration = checkOutDuration;
	}
	/**
	 * @param keyAttributes The keyAttributes to set.
	 */
	public void setKeyAttributes(List keyAttributes) {
		this.keyAttributes = keyAttributes;
	}
	/**
	 * @param transitions The transitions to set.
	 */
	public void setTransitions(Map transitions) {
		this.transitions = transitions;
	}
	/**
	 * @return Returns the module.
	 */
	public String getModule() {
		return module;
	}
	/**
	 * @param module The module to set.
	 */
	public void setModule(String module) {
		this.module = module;
	}
	/**
	 * @return Returns the activities.
	 */
	public Map getActivities() {
		return activities;
	}
	/**
	 * @param activities The activities to set.
	 */
	public void setActivities(Map activities) {
		this.activities = activities;
	}
}
