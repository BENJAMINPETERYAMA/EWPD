/*
 * Created on Dec 1, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.util;

/**
 * @author U23914
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TimeHandler {
	
	private long startTime;
	private String functionalityName;
	private String componenetName;
	private String methodName;
	private String logString;	
	
	/**
	 * @return Returns the startTime.
	 */
	public long getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime The startTime to set.
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * Method for finding total time
	 * @return
	 */
	public float getTotalExecutionTime(){
		float totalExecutionTime = 0;
		long endTime =System.currentTimeMillis();
		totalExecutionTime =  (float) ((endTime -startTime)/1000.00);
		return totalExecutionTime;
	}
	/**
	 * @return Returns the functionalityName.
	 */
	public String getFunctionalityName() {
		return functionalityName;
	}
	/**
	 * @param functionalityName The functionalityName to set.
	 */
	public void setFunctionalityName(String functionalityName) {
		this.functionalityName = functionalityName;
	}
	/**
	 * @return Returns the componenetName.
	 */
	public String getComponenetName() {
		return componenetName;
	}
	/**
	 * @param componenetName The componenetName to set.
	 */
	public void setComponenetName(String componenetName) {
		this.componenetName = componenetName;
	}
	/**
	 * @return Returns the methodName.
	 */
	public String getMethodName() {
		return methodName;
	}
	/**
	 * @param methodName The methodName to set.
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	/**
	 * Method for appending log
	 * @param functionalityName
	 * @param componenetName
	 * @param methodName
	 * @return Appened string 
	 */
	public String startPerfLogging(String functionalityName,String componenetName,String methodName ){
		
		this.startTime = System.currentTimeMillis();
		
		StringBuffer sb = new StringBuffer();
		sb.append("Performance_Check:");
		sb.append(functionalityName);
		sb.append(":");
		sb.append(componenetName);
		sb.append(":");
		sb.append(methodName);
		
		this.logString = sb.toString();
		String  beginLog = this.logString+", begin";
		return beginLog;
		
	}
	/**
	 * Method for getting end log string
	 * @return
	 */
	public String endPerfLogging(){
		String endLogString = "";
		endLogString = this.logString+","+"took:"+getTotalExecutionTime()+" seconds";
		return endLogString;
	}
	/**
	 * @return Returns the logString.
	 */
	public String getLogString() {
		return logString;
	}
	/**
	 * @param logString The logString to set.
	 */
	public void setLogString(String logString) {
		this.logString = logString;
	}
}
