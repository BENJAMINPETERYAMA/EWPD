/*
 * MigrationReportGeneration.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.migration.bo;

import java.util.List;



/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationReportGeneration {
	
	private String legacyContractId;
	private String majorHeading;
	private String minorHeading;
	private String contractVariable;
	private String contractVariableText;
	private String providerArrangement;
	private String contractVariableFormat;
	private String contractVariableValue;
	private String startDate;
	
	private List mappedVariableList;
	/**
	 * Returns the contractVariable
	 * @return String contractVariable.
	 */
	public String getContractVariable() {
//		return modifyData(contractVariable,12);
		return contractVariable;
	}
	/**
	 * Sets the contractVariable
	 * @param contractVariable.
	 */
	public void setContractVariable(String contractVariable) {
		this.contractVariable = contractVariable;
	}
	/**
	 * Returns the contractVariableFormat
	 * @return String contractVariableFormat.
	 */
	public String getContractVariableFormat() {
//		return modifyData(contractVariableFormat,6);
		return contractVariableFormat;
	}
	/**
	 * Sets the contractVariableFormat
	 * @param contractVariableFormat.
	 */
	public void setContractVariableFormat(String contractVariableFormat) {
		this.contractVariableFormat = contractVariableFormat;
	}
	/**
	 * Returns the contractVariableText
	 * @return String contractVariableText.
	 */
	public String getContractVariableText() {
//		return modifyData(contractVariableText,16);
		return contractVariableText;
	}
	/**
	 * Sets the contractVariableText
	 * @param contractVariableText.
	 */
	public void setContractVariableText(String contractVariableText) {
		this.contractVariableText = contractVariableText;
	}
	/**
	 * Returns the contractVariableValue
	 * @return String contractVariableValue.
	 */
	public String getContractVariableValue() {
//		return modifyData(contractVariableValue,4);
		return contractVariableValue;
	}
	/**
	 * Sets the contractVariableValue
	 * @param contractVariableValue.
	 */
	public void setContractVariableValue(String contractVariableValue) {
		this.contractVariableValue = contractVariableValue;
	}
	/**
	 * Returns the legacyContractId
	 * @return String legacyContractId.
	 */
	public String getLegacyContractId() {
		return legacyContractId;
	}
	/**
	 * Sets the legacyContractId
	 * @param legacyContractId.
	 */
	public void setLegacyContractId(String legacyContractId) {
		this.legacyContractId = legacyContractId;
	}
	/**
	 * Returns the majorHeading
	 * @return String majorHeading.
	 */
	public String getMajorHeading() {
//		return modifyData(majorHeading,15);
		return majorHeading;
	}
	/**
	 * Sets the majorHeading
	 * @param majorHeading.
	 */
	public void setMajorHeading(String majorHeading) {
		this.majorHeading = majorHeading;
	}
	/**
	 * Returns the minorHeading
	 * @return String minorHeading.
	 */
	public String getMinorHeading() {
//		return modifyData(minorHeading,16);
		return minorHeading;
	}
	/**
	 * Sets the minorHeading
	 * @param minorHeading.
	 */
	public void setMinorHeading(String minorHeading) {
		this.minorHeading = minorHeading;
	}
	/**
	 * Returns the providerArrangement
	 * @return String providerArrangement.
	 */
	public String getProviderArrangement() {
//		return modifyData(providerArrangement,4);
		return providerArrangement;
	}
	/**
	 * Sets the providerArrangement
	 * @param providerArrangement.
	 */
	public void setProviderArrangement(String providerArrangement) {
		this.providerArrangement = providerArrangement;
	}
	/**
	 * Returns the startDate
	 * @return Date startDate.
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * Sets the startDate
	 * @param startDate.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * Returns the mappedVariableList
	 * @return List mappedVariableList.
	 */
	public List getMappedVariableList() {
		return mappedVariableList;
	}
	/**
	 * Sets the mappedVariableList
	 * @param mappedVariableList.
	 */
	public void setMappedVariableList(List mappedVariableList) {
		this.mappedVariableList = mappedVariableList;
	}
	
//	private  String modifyData(String inputData, int dataLength){
//        String outputData = inputData;
//        int  length =inputData.length(); 
//        int count = length/dataLength;
//        int index = 0;
//        for(int i=0;i<count;i++){
//	        if(!StringUtil.isEmpty(outputData)){
//
//	            index= index+dataLength;
//		        if(outputData.length()>index){
//		          
//		           String  temp1 = outputData.substring(0,index);
//		           String temp2= outputData.substring(index);		           
//		           outputData = temp1 +" "+temp2;	          
//		          
//		        }
//        	}
//        }  
//        return  outputData ;  
//	}
	
	
	
}
