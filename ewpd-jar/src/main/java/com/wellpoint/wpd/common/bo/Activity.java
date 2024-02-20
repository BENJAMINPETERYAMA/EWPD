/*
 * Activity.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class Activity {
  private String name;
  private String taskName;
  /**
 * 
 */
public Activity(String name,String taskName) {

	this.name=name;
	this.taskName=taskName;
	// TODO Auto-generated constructor stub
}
/**
 * @return Returns the name.
 */
public String getName() {
	return name;
}
/**
 * @param name The name to set.
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @return Returns the taskName.
 */
public String getTaskName() {
	return taskName;
}
/**
 * @param taskName The taskName to set.
 */
public void setTaskName(String taskName) {
	this.taskName = taskName;
}
}
