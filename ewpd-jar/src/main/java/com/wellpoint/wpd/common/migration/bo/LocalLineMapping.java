/*
 * Created on Sep 25, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.migration.bo;

import java.util.Date;

/**
 * @author ab24713
 *
 * @
 */
public class LocalLineMapping {
	private int migrationSysId;
	private int dateSegSysId;
	private int prodMappingSysId;
	private int benCompSysId;
	private int benefitSysId;
	private int prodSysId;
	private int lineSysId;
	private String variableId;
	private String codedValue;
	private String conflictFlag;
	private String variableDetail;
	private String lineDataTypeLegend;
	private String lineDesc;
	private String lineProvArr;
	private String lineReference;
	private String createdUser;
	private Date createdTimeStamp;
	private String lastUpdatedUser;
	private Date lastUpdatedTimeStamp;
	private String masterMappingVariable;
	private String benefitName;
	private String benCompName;

	/**
	 * Returns the benCompSysId.
	 * @return int benCompSysId.
	 */
	public int getBenCompSysId() {
		return benCompSysId;
	}
	/**
	 * Sets the benCompSysId.
	 * @param benCompSysId.
	 */

	public void setBenCompSysId(int benCompSysId) {
		this.benCompSysId = benCompSysId;
	}
	/**
	 * Returns the benefitSysId.
	 * @return int benefitSysId.
	 */
	public int getBenefitSysId() {
		return benefitSysId;
	}
	/**
	 * Sets the benefitSysId.
	 * @param benefitSysId.
	 */

	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
	}
	/**
	 * Returns the codedValue.
	 * @return String codedValue.
	 */
	public String getCodedValue() {
		return codedValue;
	}
	/**
	 * Sets the codedValue.
	 * @param codedValue.
	 */

	public void setCodedValue(String codedValue) {
		this.codedValue = codedValue;
	}
	/**
	 * Returns the conflictFlag.
	 * @return String conflictFlag.
	 */
	public String getConflictFlag() {
		return conflictFlag;
	}
	/**
	 * Sets the conflictFlag.
	 * @param conflictFlag.
	 */

	public void setConflictFlag(String conflictFlag) {
		this.conflictFlag = conflictFlag;
	}
	/**
	 * Returns the createdTimeStamp.
	 * @return Date createdTimeStamp.
	 */
	public Date getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	/**
	 * Sets the createdTimeStamp.
	 * @param createdTimeStamp.
	 */

	public void setCreatedTimeStamp(Date createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	/**
	 * Returns the createdUser.
	 * @return String createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * Sets the createdUser.
	 * @param createdUser.
	 */

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * Returns the dateSegSysId.
	 * @return int dateSegSysId.
	 */
	public int getDateSegSysId() {
		return dateSegSysId;
	}
	/**
	 * Sets the dateSegSysId.
	 * @param dateSegSysId.
	 */

	public void setDateSegSysId(int dateSegSysId) {
		this.dateSegSysId = dateSegSysId;
	}
	/**
	 * Returns the lastUpdatedTimeStamp.
	 * @return Date lastUpdatedTimeStamp.
	 */
	public Date getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}
	/**
	 * Sets the lastUpdatedTimeStamp.
	 * @param lastUpdatedTimeStamp.
	 */

	public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}
	/**
	 * Returns the lastUpdatedUser.
	 * @return String lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * Sets the lastUpdatedUser.
	 * @param lastUpdatedUser.
	 */

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * Returns the lineDataTypeLegend.
	 * @return String lineDataTypeLegend.
	 */
	public String getLineDataTypeLegend() {
		return lineDataTypeLegend;
	}
	/**
	 * Sets the lineDataTypeLegend.
	 * @param lineDataTypeLegend.
	 */

	public void setLineDataTypeLegend(String lineDataTypeLegend) {
		this.lineDataTypeLegend = lineDataTypeLegend;
	}
	/**
	 * Returns the lineDesc.
	 * @return String lineDesc.
	 */
	public String getLineDesc() {
//		return modifyData(lineDesc,19);
		return lineDesc;
	}
	/**
	 * Sets the lineDesc.
	 * @param lineDesc.
	 */

	public void setLineDesc(String lineDesc) {
		this.lineDesc = lineDesc;
	}
	/**
	 * Returns the lineProvArr.
	 * @return String lineProvArr.
	 */
	public String getLineProvArr() {
//		return modifyData(lineProvArr,5);
		return lineProvArr;
	}
	/**
	 * Sets the lineProvArr.
	 * @param lineProvArr.
	 */

	public void setLineProvArr(String lineProvArr) {
		this.lineProvArr = lineProvArr;
	}
	/**
	 * Returns the lineReference.
	 * @return String lineReference.
	 */
	public String getLineReference() {
		return lineReference;
	}
	/**
	 * Sets the lineReference.
	 * @param lineReference.
	 */

	public void setLineReference(String lineReference) {
		this.lineReference = lineReference;
	}
	/**
	 * Returns the lineSysId.
	 * @return int lineSysId.
	 */
	public int getLineSysId() {
		return lineSysId;
	}
	/**
	 * Sets the lineSysId.
	 * @param lineSysId.
	 */

	public void setLineSysId(int lineSysId) {
		this.lineSysId = lineSysId;
	}
	/**
	 * Returns the migrationSysId.
	 * @return int migrationSysId.
	 */
	public int getMigrationSysId() {
		return migrationSysId;
	}
	/**
	 * Sets the migrationSysId.
	 * @param migrationSysId.
	 */

	public void setMigrationSysId(int migrationSysId) {
		this.migrationSysId = migrationSysId;
	}
	/**
	 * Returns the prodMappingSysId.
	 * @return int prodMappingSysId.
	 */
	public int getProdMappingSysId() {
		return prodMappingSysId;
	}
	/**
	 * Sets the prodMappingSysId.
	 * @param prodMappingSysId.
	 */

	public void setProdMappingSysId(int prodMappingSysId) {
		this.prodMappingSysId = prodMappingSysId;
	}
	/**
	 * Returns the variableDetail.
	 * @return String variableDetail.
	 */
	public String getVariableDetail() {
		return variableDetail;
	}
	/**
	 * Sets the variableDetail.
	 * @param variableDetail.
	 */

	public void setVariableDetail(String variableDetail) {
		this.variableDetail = variableDetail;
	}
	/**
	 * Returns the variableId.
	 * @return String variableId.
	 */
	public String getVariableId() {
//		return modifyData(variableId,15);
		return variableId;
	}
	/**
	 * Sets the variableId.
	 * @param variableId.
	 */

	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}
	/**
	 * Returns the prodSysId.
	 * @return int prodSysId.
	 */
	public int getProdSysId() {
		return prodSysId;
	}
	/**
	 * Sets the prodSysId.
	 * @param prodSysId.
	 */

	public void setProdSysId(int prodSysId) {
		this.prodSysId = prodSysId;
	}

	/**
	 * Returns the masterMappingVariable.
	 * @return String masterMappingVariable.
	 */
	public String getMasterMappingVariable() {
//		return modifyData(masterMappingVariable,12);
		return masterMappingVariable;
	}
	/**
	 * Sets the masterMappingVariable.
	 * @param masterMappingVariable.
	 */

	public void setMasterMappingVariable(String masterMappingVariable) {
		this.masterMappingVariable = masterMappingVariable;
	}
	/**
	 * Returns the benCompName.
	 * @return String benCompName.
	 */
	public String getBenCompName() {
//		return modifyData(benCompName,19);
		return benCompName;
	}
	/**
	 * Sets the benCompName.
	 * @param benCompName.
	 */

	public void setBenCompName(String benCompName) {
		this.benCompName = benCompName;
	}
	/**
	 * Returns the benefitName.
	 * @return String benefitName.
	 */
	public String getBenefitName() {
//		return modifyData(benefitName,19);
		return benefitName;
	}
	/**
	 * Sets the benefitName.
	 * @param benefitName.
	 */

	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
	
//	private  String modifyData(String inputData, int dataLength){
//        String outputData = inputData;
//        int  length =inputData.length(); 
//        int count = length/dataLength;
//        int index = 0;
//        for(int i=0;i<count;i++){
//	        if(!StringUtil.isEmpty(outputData)){
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
