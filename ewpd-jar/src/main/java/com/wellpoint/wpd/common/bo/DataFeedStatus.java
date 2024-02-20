/*
 * Created on Aug 14, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.bo;

/**
 * @author u14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DataFeedStatus {
 private int feedRunFlag;
 private int feedStatusFlag;
 private String feedType;
 private String user;
 
 
/**
 * @return Returns the feedRunFlag.
 */
public int getFeedRunFlag() {
	return feedRunFlag;
}
/**
 * @param feedRunFlag The feedRunFlag to set.
 */
public void setFeedRunFlag(int feedRunFlag) {
	this.feedRunFlag = feedRunFlag;
}
/**
 * @return Returns the feedStatusFlag.
 */
public int getFeedStatusFlag() {
	return feedStatusFlag;
}
/**
 * @param feedStatusFlag The feedStatusFlag to set.
 */
public void setFeedStatusFlag(int feedStatusFlag) {
	this.feedStatusFlag = feedStatusFlag;
}
/**
 * @return Returns the feedType.
 */
public String getFeedType() {
	return feedType;
}
/**
 * @param feedType The feedType to set.
 */
public void setFeedType(String feedType) {
	this.feedType = feedType;
}
/**
 * @return Returns the user.
 */
public String getUser() {
	return user;
}
/**
 * @param user The user to set.
 */
public void setUser(String user) {
	this.user = user;
}
}
