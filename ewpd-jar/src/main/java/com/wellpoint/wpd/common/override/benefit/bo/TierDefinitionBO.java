/*
 * Created on Aug 6, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.override.benefit.bo;

import java.util.List;

/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TierDefinitionBO {
	
	private int tierDefId;
	private int tierSysId;
	private int tierCriteriaSysId;
	private String tierDesc;
	private String tierName;
	private String criteriaVal;
	private String criteriaName;
	private String criteriaIndicator;
	private List tierList;
	private List criteriaList;
	private int dateSegSysId;;
	private int benCompSysId;
	private int benefitSysId;
	private int prodSysId;
	private int levelSeq;
	private int levelSysId;
	private String levelDesc;
	private String levelQualDesc;
	private String levelRefCode;
	private String levelRefDesc;
	private int lineSysId;
	private String lineDataTypeId;
	private String lineDateType;
	private String pvaDesc;
	private String lineBnftVal;
	private String lineDataTypeLgnd;
	private String linePvaCode;
	private String lineVal;
	private String bnftLvlTerm;
	private String bnftLvlQual;
	private String lvlTermDesc;
	private int dispSeqNo;
	private String dataType;
	private String tierCrtPsvlValue;
	private String tierCrtPsvlValueDesc;
	private String noteId;
	private int noteVersion;
	private String bnftCmpntName;
	private String bnftName;
	private String noteName;
	private int count;
	private String productName;
	private String dateRange;
	
	
	private String bnftLineNotesExist;
	private String questionDesc;
	private String questionNo;
	private String admnOptDesc;
	private int admnOptAssnSysId;
	//Start CARS
	//DESCRIPTION : Created integer for frequency value
	private int frequencyValue; 
	private int lowerLevelFrequencyValue;
	private String lowerLevelDescriptionValue;
	//End CARS
	
	
	/**
	 * @return Returns the tierDefId.
	 */
	public int getTierDefId() {
		return tierDefId;
	}
	/**
	 * @param tierDefId The tierDefId to set.
	 */
	public void setTierDefId(int tierDefId) {
		this.tierDefId = tierDefId;
	}
	/**
	 * @return Returns the tierDesc.
	 */
	public String getTierDesc() {
		return tierDesc;
	}
	/**
	 * @param tierDesc The tierDesc to set.
	 */
	public void setTierDesc(String tierDesc) {
		this.tierDesc = tierDesc;
	}
	/**
	 * @return Returns the tierList.
	 */
	public List getTierList() {
		return tierList;
	}
	/**
	 * @param tierList The tierList to set.
	 */
	public void setTierList(List tierList) {
		this.tierList = tierList;
	}
	/**
	 * @return Returns the cntrctTierSysId.
	 */
	public int getTierSysId() {
		return tierSysId;
	}
	/**
	 * @param cntrctTierSysId The cntrctTierSysId to set.
	 */
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
	}
	/**
	 * @return Returns the tierName.
	 */
	public String getTierName() {
		return tierName;
	}
	/**
	 * @param tierName The tierName to set.
	 */
	public void setTierName(String tierName) {
		this.tierName = tierName;
	}
	/**
	 * @return Returns the tierCriteriaSysId.
	 */
	public int getTierCriteriaSysId() {
		return tierCriteriaSysId;
	}
	/**
	 * @param tierCriteriaSysId The tierCriteriaSysId to set.
	 */
	public void setTierCriteriaSysId(int tierCriteriaSysId) {
		this.tierCriteriaSysId = tierCriteriaSysId;
	}
	/**
	 * @return Returns the criteriaVal.
	 */
	public String getCriteriaVal() {
		return criteriaVal;
	}
	/**
	 * @param criteriaVal The criteriaVal to set.
	 */
	public void setCriteriaVal(String criteriaVal) {
		this.criteriaVal = criteriaVal;
	}
	/**
	 * @return Returns the criteriaName.
	 */
	public String getCriteriaName() {
		return criteriaName;
	}
	/**
	 * @param criteriaName The criteriaName to set.
	 */
	public void setCriteriaName(String criteriaName) {
		this.criteriaName = criteriaName;
	}
	/**
	 * @return Returns the criteriaList.
	 */
	public List getCriteriaList() {
		return criteriaList;
	}
	/**
	 * @param criteriaList The criteriaList to set.
	 */
	public void setCriteriaList(List criteriaList) {
		this.criteriaList = criteriaList;
	}
	/**
	 * @return Returns the benCompSysId.
	 */
	public int getBenCompSysId() {
		return benCompSysId;
	}
	/**
	 * @param benCompSysId The benCompSysId to set.
	 */
	public void setBenCompSysId(int benCompSysId) {
		this.benCompSysId = benCompSysId;
	}
	/**
	 * @return Returns the benefitSysId.
	 */
	public int getBenefitSysId() {
		return benefitSysId;
	}
	/**
	 * @param benefitSysId The benefitSysId to set.
	 */
	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
	}
	/**
	 * @return Returns the dateSegSysId.
	 */
	public int getDateSegSysId() {
		return dateSegSysId;
	}
	/**
	 * @param dateSegSysId The dateSegSysId to set.
	 */
	public void setDateSegSysId(int dateSegSysId) {
		this.dateSegSysId = dateSegSysId;
	}
	/**
	 * @return Returns the prodSysId.
	 */
	public int getProdSysId() {
		return prodSysId;
	}
	/**
	 * @param prodSysId The prodSysId to set.
	 */
	public void setProdSysId(int prodSysId) {
		this.prodSysId = prodSysId;
	}
	/**
	 * @return Returns the bnftLvlQual.
	 */
	public String getBnftLvlQual() {
		return bnftLvlQual;
	}
	/**
	 * @param bnftLvlQual The bnftLvlQual to set.
	 */
	public void setBnftLvlQual(String bnftLvlQual) {
		this.bnftLvlQual = bnftLvlQual;
	}
	/**
	 * @return Returns the bnftLvlTerm.
	 */
	public String getBnftLvlTerm() {
		return bnftLvlTerm;
	}
	/**
	 * @param bnftLvlTerm The bnftLvlTerm to set.
	 */
	public void setBnftLvlTerm(String bnftLvlTerm) {
		this.bnftLvlTerm = bnftLvlTerm;
	}
	/**
	 * @return Returns the levelDesc.
	 */
	public String getLevelDesc() {
		return levelDesc;
	}
	/**
	 * @param levelDesc The levelDesc to set.
	 */
	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
	}
	/**
	 * @return Returns the levelQualDesc.
	 */
	public String getLevelQualDesc() {
		return levelQualDesc;
	}
	/**
	 * @param levelQualDesc The levelQualDesc to set.
	 */
	public void setLevelQualDesc(String levelQualDesc) {
		this.levelQualDesc = levelQualDesc;
	}
	/**
	 * @return Returns the levelRefCode.
	 */
	public String getLevelRefCode() {
		return levelRefCode;
	}
	/**
	 * @param levelRefCode The levelRefCode to set.
	 */
	public void setLevelRefCode(String levelRefCode) {
		this.levelRefCode = levelRefCode;
	}
	/**
	 * @return Returns the levelRefDesc.
	 */
	public String getLevelRefDesc() {
		return levelRefDesc;
	}
	/**
	 * @param levelRefDesc The levelRefDesc to set.
	 */
	public void setLevelRefDesc(String levelRefDesc) {
		this.levelRefDesc = levelRefDesc;
	}
	/**
	 * @return Returns the levelSeq.
	 */
	public int getLevelSeq() {
		return levelSeq;
	}
	/**
	 * @param levelSeq The levelSeq to set.
	 */
	public void setLevelSeq(int levelSeq) {
		this.levelSeq = levelSeq;
	}
	/**
	 * @return Returns the levelSysId.
	 */
	public int getLevelSysId() {
		return levelSysId;
	}
	/**
	 * @param levelSysId The levelSysId to set.
	 */
	public void setLevelSysId(int levelSysId) {
		this.levelSysId = levelSysId;
	}
	/**
	 * @return Returns the lineBnftVal.
	 */
	public String getLineBnftVal() {
		return lineBnftVal;
	}
	/**
	 * @param lineBnftVal The lineBnftVal to set.
	 */
	public void setLineBnftVal(String lineBnftVal) {
		this.lineBnftVal = lineBnftVal;
	}
	
	/**
	 * @return Returns the lineDataTypeId.
	 */
	public String getLineDataTypeId() {
		return lineDataTypeId;
	}
	/**
	 * @param lineDataTypeId The lineDataTypeId to set.
	 */
	public void setLineDataTypeId(String lineDataTypeId) {
		this.lineDataTypeId = lineDataTypeId;
	}
	/**
	 * @return Returns the lineDataTypeLgnd.
	 */
	public String getLineDataTypeLgnd() {
		return lineDataTypeLgnd;
	}
	/**
	 * @param lineDataTypeLgnd The lineDataTypeLgnd to set.
	 */
	public void setLineDataTypeLgnd(String lineDataTypeLgnd) {
		this.lineDataTypeLgnd = lineDataTypeLgnd;
	}
	/**
	 * @return Returns the lineDateType.
	 */
	public String getLineDateType() {
		return lineDateType;
	}
	/**
	 * @param lineDateType The lineDateType to set.
	 */
	public void setLineDateType(String lineDateType) {
		this.lineDateType = lineDateType;
	}
	/**
	 * @return Returns the linePvaCode.
	 */
	public String getLinePvaCode() {
		return linePvaCode;
	}
	/**
	 * @param linePvaCode The linePvaCode to set.
	 */
	public void setLinePvaCode(String linePvaCode) {
		this.linePvaCode = linePvaCode;
	}
	/**
	 * @return Returns the lineSysId.
	 */
	public int getLineSysId() {
		return lineSysId;
	}
	/**
	 * @param lineSysId The lineSysId to set.
	 */
	public void setLineSysId(int lineSysId) {
		this.lineSysId = lineSysId;
	}
	/**
	 * @return Returns the lineVal.
	 */
	public String getLineVal() {
		return lineVal;
	}
	/**
	 * @param lineVal The lineVal to set.
	 */
	public void setLineVal(String lineVal) {
		this.lineVal = lineVal;
	}
	/**
	 * @return Returns the lvlTermDesc.
	 */
	public String getLvlTermDesc() {
		return lvlTermDesc;
	}
	/**
	 * @param lvlTermDesc The lvlTermDesc to set.
	 */
	public void setLvlTermDesc(String lvlTermDesc) {
		this.lvlTermDesc = lvlTermDesc;
	}
	/**
	 * @return Returns the pvaDesc.
	 */
	public String getPvaDesc() {
		return pvaDesc;
	}
	/**
	 * @param pvaDesc The pvaDesc to set.
	 */
	public void setPvaDesc(String pvaDesc) {
		this.pvaDesc = pvaDesc;
	}
	/**
	 * @return Returns the dataType.
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType The dataType to set.
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	/**
	 * @return Returns the criteriaIndicator.
	 */
	public String getCriteriaIndicator() {
		return criteriaIndicator;
	}
	/**
	 * @param criteriaIndicator The criteriaIndicator to set.
	 */
	public void setCriteriaIndicator(String criteriaIndicator) {
		this.criteriaIndicator = criteriaIndicator;
	}
	
	/**
	 * @return Returns the tierCrtPsvlValue.
	 */
	public String getTierCrtPsvlValue() {
		return tierCrtPsvlValue;
	}
	/**
	 * @param tierCrtPsvlValue The tierCrtPsvlValue to set.
	 */
	public void setTierCrtPsvlValue(String tierCrtPsvlValue) {
		this.tierCrtPsvlValue = tierCrtPsvlValue;
	}
	/**
	 * @return Returns the tierCrtPsvlValueDesc.
	 */
	public String getTierCrtPsvlValueDesc() {
		return tierCrtPsvlValueDesc;
	}
	/**
	 * @param tierCrtPsvlValueDesc The tierCrtPsvlValueDesc to set.
	 */
	public void setTierCrtPsvlValueDesc(String tierCrtPsvlValueDesc) {
		this.tierCrtPsvlValueDesc = tierCrtPsvlValueDesc;
	}
	/**
	 * @return Returns the dispSeqNo.
	 */
	public int getDispSeqNo() {
		return dispSeqNo;
	}
	/**
	 * @param dispSeqNo The dispSeqNo to set.
	 */
	public void setDispSeqNo(int dispSeqNo) {
		this.dispSeqNo = dispSeqNo;
	}
	/**
	 * @return Returns the bnftLineNotesExist.
	 */
	public String getBnftLineNotesExist() {
		return bnftLineNotesExist;
	}
	/**
	 * @param bnftLineNotesExist The bnftLineNotesExist to set.
	 */
	public void setBnftLineNotesExist(String bnftLineNotesExist) {
		this.bnftLineNotesExist = bnftLineNotesExist;
	}
	/**
	 * @return Returns the noteId.
	 */
	public String getNoteId() {
		return noteId;
	}
	/**
	 * @param noteId The noteId to set.
	 */
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	/**
	 * @return Returns the bnftCmpntName.
	 */
	public String getBnftCmpntName() {
		return bnftCmpntName;
	}
	/**
	 * @param bnftCmpntName The bnftCmpntName to set.
	 */
	public void setBnftCmpntName(String bnftCmpntName) {
		this.bnftCmpntName = bnftCmpntName;
	}
	/**
	 * @return Returns the bnftName.
	 */
	public String getBnftName() {
		return bnftName;
	}
	/**
	 * @param bnftName The bnftName to set.
	 */
	public void setBnftName(String bnftName) {
		this.bnftName = bnftName;
	}
	/**
	 * @return Returns the count.
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count The count to set.
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return Returns the noteName.
	 */
	public String getNoteName() {
		return noteName;
	}
	/**
	 * @param noteName The noteName to set.
	 */
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	/**
	 * @return Returns the noteVersion.
	 */
	public int getNoteVersion() {
		return noteVersion;
	}
	/**
	 * @param noteVersion The noteVersion to set.
	 */
	public void setNoteVersion(int noteVersion) {
		this.noteVersion = noteVersion;
	}
	/**
	 * @return Returns the productName.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName The productName to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return Returns the dateRange.
	 */
	public String getDateRange() {
		return dateRange;
	}
	/**
	 * @param dateRange The dateRange to set.
	 */
	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}
	/**
	 * @return Returns the admnOptAssnSysId.
	 */
	public int getAdmnOptAssnSysId() {
		return admnOptAssnSysId;
	}
	/**
	 * @param admnOptAssnSysId The admnOptAssnSysId to set.
	 */
	public void setAdmnOptAssnSysId(int admnOptAssnSysId) {
		this.admnOptAssnSysId = admnOptAssnSysId;
	}
	/**
	 * @return Returns the admnOptDesc.
	 */
	public String getAdmnOptDesc() {
		return admnOptDesc;
	}
	/**
	 * @param admnOptDesc The admnOptDesc to set.
	 */
	public void setAdmnOptDesc(String admnOptDesc) {
		this.admnOptDesc = admnOptDesc;
	}
	/**
	 * @return Returns the questionDesc.
	 */
	public String getQuestionDesc() {
		return questionDesc;
	}
	/**
	 * @param questionDesc The questionDesc to set.
	 */
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	/**
	 * @return Returns the questionNo.
	 */
	public String getQuestionNo() {
		return questionNo;
	}
	/**
	 * @param questionNo The questionNo to set.
	 */
	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}
	/**
	 * @return Returns the frequencyValue.
	 */
	public int getFrequencyValue() {
		return frequencyValue;
	}
	/**
	 * @param frequencyValue The frequencyValue to set.
	 */
	public void setFrequencyValue(int frequencyValue) {
		this.frequencyValue = frequencyValue;
	}
	/**
	 * @return Returns the lowerLevelDescriptionValue.
	 */
	public String getLowerLevelDescriptionValue() {
		return lowerLevelDescriptionValue;
	}
	/**
	 * @param lowerLevelDescriptionValue The lowerLevelDescriptionValue to set.
	 */
	public void setLowerLevelDescriptionValue(String lowerLevelDescriptionValue) {
		this.lowerLevelDescriptionValue = lowerLevelDescriptionValue;
	}
	/**
	 * @return Returns the lowerLevelFrequencyValue.
	 */
	public int getLowerLevelFrequencyValue() {
		return lowerLevelFrequencyValue;
	}
	/**
	 * @param lowerLevelFrequencyValue The lowerLevelFrequencyValue to set.
	 */
	public void setLowerLevelFrequencyValue(int lowerLevelFrequencyValue) {
		this.lowerLevelFrequencyValue = lowerLevelFrequencyValue;
	}
}
