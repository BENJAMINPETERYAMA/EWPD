package com.wellpoint.ets.bx.mapping.domain.vo;

import java.util.Date;
import java.util.List;

import com.wellpoint.ets.bx.mapping.application.security.Lock;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;

/**
 * @author UST-GLOBAL
 * This is an POJO class for search result.
 * 
 */
public class SearchResult {
	
	private String EB03;
	
	private List<EB03Association> eb03Association;
	
	private String variableId;
	
	private String headerRuleId;
	
	private String messageText;
	
	private String spsId;
	
	private String EB01;
	
	private boolean unMapped;
	
	private boolean mapped;
	
	private boolean notApplicable;
	
	private boolean inProgress;
	
	private boolean notFinalized;
	
	private String description;
	
	private Date createdTmStamp;	
	
	private String status;
	
	private String lastUpdatedUser;
	
	private String EB02;
	
	private String EB06;
	
	private String EB09;
	
	private String hsd01;
	
	private String hsd02;
	
	private String hsd03;
	
	private String hsd04;
	
	private String hsd05;
	
	private String hsd06;
	
	private String hsd07;
	
	private String hsd08;
	
	private String finalizedFlag;
	
	private List dataTypesAndPva;
	
	private String accumulatorSpsID;
	
	private String accumulator;
	 
	private Date createdDate;
	
	private String formattedDate;
	
	private String system;
	
	private int rowno;
	
	private String dataType;
	
	private String majorHeading;
	
	private String contractMbu;
	
	private String businessEntity;
	
	private String businessGroup ;
	
	private String minorHeading;
	
	private String pva;
	
	private String user;
	
	private Date lastChangedTime;
	
	private boolean codedStatus;
	
	private String III02;
	
	private String noteTypeCode;
	
	private String noteTypeCodeDesc;
	
	private String headerRuleDescription;
	
	private String spsRuleDescription;
	
	private String msgReqrdIndctr;
	
	private String accumNotReqrdIndctr;
	
	private String notCompleteFlag;
	
	private Lock lock;
	
	private String checked = ""; 
	
	private String sendToTest;
	
	private String approve;
	
	private String locked;
	
	private String autorizedToUnlock;
	
	private String lockedUserId;
	
	private String userName;
	
	private String messageIndicator;
	
	private String contractId;
	
	private String wpdAccumulator;
	
	//BXNI CR35
	
	private String procedureExcludedInd;
	
	private String sensitiveBenefitIndicator;
	
	//Audit Lock Status  -- October Release
	private String auditLock;
	
	/********January Release********/
	private String contractSystem;
	
	private String contractStartDate;
	
	private String ISGContractRevisionDate;
	
	/********January Release********/
	//to display category code in hover -- BXNI
	private String categoryCode;
	//to display start age in hover -- BXNI
	private String startAge;
	//to display end age code in hover -- BXNI
	private String endAge;
	//to display Highlighted Standard Messages -- BXNI
	private String isStandardMessage;
	//to display UM Rules as comma separated in the advance search report.--BXNI November
	private String commaSeperatedUMRules;
	
	private String individualEB03AssnInd;

	public String getMessageIndicator() {
		return messageIndicator;
	}

	public void setMessageIndicator(String messageIndicator) {
		this.messageIndicator = messageIndicator;
	}

	public String getMsgReqrdIndctr() {
		return msgReqrdIndctr;
	}

	public void setMsgReqrdIndctr(String msgReqrdIndctr) {
		this.msgReqrdIndctr = msgReqrdIndctr;
	}

	public String getAccumNotReqrdIndctr() {
		return accumNotReqrdIndctr;
	}

	public void setAccumNotReqrdIndctr(String accumNotReqrdIndctr) {
		this.accumNotReqrdIndctr = accumNotReqrdIndctr;
	}

	public String getNotCompleteFlag() {
		return notCompleteFlag;
	}

	public void setNotCompleteFlag(String notCompleteFlag) {
		this.notCompleteFlag = notCompleteFlag;
	}

	public String getNoteTypeCodeDesc() {
		return noteTypeCodeDesc;
	}

	public void setNoteTypeCodeDesc(String noteTypeCodeDesc) {
		this.noteTypeCodeDesc = noteTypeCodeDesc;
	}

	public String getHeaderRuleDescription() {
		return headerRuleDescription;
	}

	public void setHeaderRuleDescription(String headerRuleDescription) {
		this.headerRuleDescription = headerRuleDescription;
	}

	public String getSpsRuleDescription() {
		return spsRuleDescription;
	}

	public void setSpsRuleDescription(String spsRuleDescription) {
		this.spsRuleDescription = spsRuleDescription;
	}

	public String getEB03() {
		return EB03;
	}

	public void setEB03(String eb03) {
		EB03 = eb03;
	}

	public String getHeaderRuleId() {
		return headerRuleId;
	}

	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getSpsId() {
		return spsId;
	}

	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}

	public String getVariableId() {
		return variableId;
	}

	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}
	
	

	/**
	 * @return Returns the eB01.
	 */
	public String getEB01() {
		return EB01;
	}
	/**
	 * @param eb01 The eB01 to set.
	 */
	public void setEB01(String eb01) {
		EB01 = eb01;
	}
	/**
	 * @return Returns the unMapped.
	 */
	public boolean isUnMapped() {
		return unMapped;
	}
	/**
	 * @param unMapped The unMapped to set.
	 */
	public void setUnMapped(boolean unMapped) {
		this.unMapped = unMapped;
	}
	/**
	 * @return Returns the mapped.
	 */
	public boolean isMapped() {
		return mapped;
	}
	/**
	 * @param mapped The mapped to set.
	 */
	public void setMapped(boolean mapped) {
		this.mapped = mapped;
	}
	/**
	 * @return Returns the notApplicable.
	 */
	public boolean isNotApplicable() {
		return notApplicable;
	}
	/**
	 * @param notApplicable The notApplicable to set.
	 */
	public void setNotApplicable(boolean notApplicable) {
		this.notApplicable = notApplicable;
	}
	/**
	 * @return Returns the inProgress.
	 */
	public boolean isInProgress() {
		return inProgress;
	}
	/**
	 * @param inProgress The inProgress to set.
	 */
	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}
	/**
	 * @return Returns the notFinalized.
	 */
	public boolean isNotFinalized() {
		return notFinalized;
	}
	/**
	 * @param notFinalized The notFinalized to set.
	 */
	public void setNotFinalized(boolean notFinalized) {
		this.notFinalized = notFinalized;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getSystem() {
		return system;
	}

	public void setRowno(int rowno) {
		this.rowno = rowno;
	}

	public int getRowno() {
		return rowno;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setCodedStatus(boolean codedStatus) {
		this.codedStatus = codedStatus;
	}

	public boolean isCodedStatus() {
		return codedStatus;
	}

	public String getMajorHeading() {
		return majorHeading;
	}

	public void setMajorHeading(String majorHeading) {
		this.majorHeading = majorHeading;
	}

	public String getMinorHeading() {
		return minorHeading;
	}

	public void setMinorHeading(String minorHeading) {
		this.minorHeading = minorHeading;
	}

	public void setPva(String pva) {
		this.pva = pva;
	}

	public String getPva() {
		return pva;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public void setLastChangedTime(Date lastChangedTime) {
		this.lastChangedTime = lastChangedTime;
	}

	public Date getLastChangedTime() {
		return lastChangedTime;
	}

	

	public String getIII02() {
		return III02;
	}

	public void setIII02(String iii02) {
		III02 = iii02;
	}

	public String getEB06() {
		return EB06;
	}

	public void setEB06(String eb06) {
		EB06 = eb06;
	}

	public String getEB09() {
		return EB09;
	}

	public void setEB09(String eb09) {
		EB09 = eb09;
	}

	public void setEB02(String eB02) {
		EB02 = eB02;
	}

	public String getEB02() {
		return EB02;
	}

	public Date getCreatedTmStamp() {
		return createdTmStamp;
	}

	public void setCreatedTmStamp(Date createdTmStamp) {
		this.createdTmStamp = createdTmStamp;
	}

	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	public String getHsd01() {
		return hsd01;
	}

	public void setHsd01(String hsd01) {
		this.hsd01 = hsd01;
	}

	public String getHsd02() {
		return hsd02;
	}

	public void setHsd02(String hsd02) {
		this.hsd02 = hsd02;
	}

	public String getHsd03() {
		return hsd03;
	}

	public void setHsd03(String hsd03) {
		this.hsd03 = hsd03;
	}

	public String getHsd04() {
		return hsd04;
	}

	public void setHsd04(String hsd04) {
		this.hsd04 = hsd04;
	}

	public String getHsd05() {
		return hsd05;
	}

	public void setHsd05(String hsd05) {
		this.hsd05 = hsd05;
	}

	public String getHsd06() {
		return hsd06;
	}

	public void setHsd06(String hsd06) {
		this.hsd06 = hsd06;
	}

	public String getHsd07() {
		return hsd07;
	}

	public void setHsd07(String hsd07) {
		this.hsd07 = hsd07;
	}

	public String getHsd08() {
		return hsd08;
	}

	public void setHsd08(String hsd08) {
		this.hsd08 = hsd08;
	}

	

	public List getDataTypesAndPva() {
		return dataTypesAndPva;
	}

	public void setDataTypesAndPva(List dataTypesAndPva) {
		this.dataTypesAndPva = dataTypesAndPva;
	}

	public String getAccumulatorSpsID() {
		return accumulatorSpsID;
	}

	public void setAccumulatorSpsID(String accumulatorSpsID) {
		this.accumulatorSpsID = accumulatorSpsID;
	}

	public void setNoteTypeCode(String noteTypeCode) {
		this.noteTypeCode = noteTypeCode;
	}

	public String getNoteTypeCode() {
		return noteTypeCode;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}

	public String getFormattedDate() {
		return formattedDate;
	}

	public void setAccumulator(String accumulator) {
		this.accumulator = accumulator;
	}

	public String getAccumulator() {
		return accumulator;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}

	public Lock getLock() {
		return lock;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	
	


	/**
	 * @return Returns the finalizedFlag.
	 */
	public String getFinalizedFlag() {
		return finalizedFlag;
	}
	/**
	 * @param finalizedFlag The finalizedFlag to set.
	 */
	public void setFinalizedFlag(String finalizedFlag) {
		this.finalizedFlag = finalizedFlag;
	}
	/**
	 * @return Returns the sendToTest.
	 */
	public String getSendToTest() {
		return sendToTest;
	}
	/**
	 * @param sendToTest The sendToTest to set.
	 */
	public void setSendToTest(String sendToTest) {
		this.sendToTest = sendToTest;
	}
	/**
	 * @return Returns the approve.
	 */
	public String getApprove() {
		return approve;
	}
	/**
	 * @param approve The approve to set.
	 */
	public void setApprove(String approve) {
		this.approve = approve;
	}
	/**
	 * @return Returns the locked.
	 */
	public String getLocked() {
		return locked;
	}
	/**
	 * @param locked The locked to set.
	 */
	public void setLocked(String locked) {
		this.locked = locked;
	}
	/**
	 * @return Returns the autorizedToUnlock.
	 */
	public String getAutorizedToUnlock() {
		return autorizedToUnlock;
	}
	/**
	 * @param autorizedToUnlock The autorizedToUnlock to set.
	 */
	public void setAutorizedToUnlock(String autorizedToUnlock) {
		this.autorizedToUnlock = autorizedToUnlock;
	}
	/**
	 * @return Returns the lockedUserId.
	 */
	public String getLockedUserId() {
		return lockedUserId;
	}
	/**
	 * @param lockedUserId The lockedUserId to set.
	 */
	public void setLockedUserId(String lockedUserId) {
		this.lockedUserId = lockedUserId;
	}

	public String getContractMbu() {
		return contractMbu;
	}

	public void setContractMbu(String contractMbu) {
		this.contractMbu = contractMbu;
	}

	public String getBusinessEntity() {
		return businessEntity;
	}

	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}

	public String getBusinessGroup() {
		return businessGroup;
	}

	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getSensitiveBenefitIndicator() {
		return sensitiveBenefitIndicator;
	}

	public void setSensitiveBenefitIndicator(String sensitiveBenefitIndicator) {
		this.sensitiveBenefitIndicator = sensitiveBenefitIndicator;
	}

	public void setAuditLock(String auditLock) {
		this.auditLock = auditLock;
	}

	public String getAuditLock() {
		return auditLock;
	}

	public void setContractSystem(String contractSystem) {
		this.contractSystem = contractSystem;
	}

	public String getContractSystem() {
		return contractSystem;
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setISGContractRevisionDate(String iSGContractRevisionDate) {
		ISGContractRevisionDate = iSGContractRevisionDate;
	}

	public String getISGContractRevisionDate() {
		return ISGContractRevisionDate;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setStartAge(String startAge) {
		this.startAge = startAge;
	}

	public String getStartAge() {
		return startAge;
	}

	public void setEndAge(String endAge) {
		this.endAge = endAge;
	}

	public String getEndAge() {
		return endAge;
	}
	
	public void setIsStandardMessage(String isStandardMessage){
		this.isStandardMessage = isStandardMessage;
	}
	
	public String getIsStandardMessage(){
		return isStandardMessage;
	}

	public String getCommaSeperatedUMRules() {
		return commaSeperatedUMRules;
	}

	public void setCommaSeperatedUMRules(String commaSeperatedUMRules) {
		this.commaSeperatedUMRules = commaSeperatedUMRules;
	}

	public String getWpdAccumulator() {
		return wpdAccumulator;
	}

	public void setWpdAccumulator(String wpdAccumulator) {
		this.wpdAccumulator = wpdAccumulator;
	}

	public String getProcedureExcludedInd() {
		return procedureExcludedInd;
	}

	public void setProcedureExcludedInd(String procedureExcludedInd) {
		this.procedureExcludedInd = procedureExcludedInd;
	}

	public List<EB03Association> getEb03Association() {
		return eb03Association;
	}

	public void setEb03Association(List<EB03Association> eb03Association) {
		this.eb03Association = eb03Association;
	}

	public String getIndividualEB03AssnInd() {
		return individualEB03AssnInd;
	}

	public void setIndividualEB03AssnInd(String individualEB03AssnInd) {
		this.individualEB03AssnInd = individualEB03AssnInd;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	
}