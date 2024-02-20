package com.wellpoint.ets.ebx.simulation.webservices.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class MappingWebServiceVO implements Comparable<Object>{

	/**
	 * @uml.annotations for <code>auditTrail</code>
	 *                  collection_type="com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail"
	 */
	private List<AuditTrailWebServiceVO> auditTrails = new ArrayList<AuditTrailWebServiceVO>();

	private VariableWebServiceVO variable;

	private HippaSegmentWebServiceVO hippaSegment;

	private Long variableSystemId;

	private HippaSegmentWebServiceVO eb01;

	private HippaSegmentWebServiceVO eb02;

	private HippaSegmentWebServiceVO eb03;

	private HippaSegmentWebServiceVO eb06;

	private HippaSegmentWebServiceVO eb09;

	private HippaSegmentWebServiceVO ii02;

	private HippaSegmentWebServiceVO hsd01;

	private HippaSegmentWebServiceVO hsd02;

	private HippaSegmentWebServiceVO hsd03;

	private HippaSegmentWebServiceVO hsd04;

	private HippaSegmentWebServiceVO hsd05;

	private HippaSegmentWebServiceVO hsd06;

	private HippaSegmentWebServiceVO hsd07;

	private HippaSegmentWebServiceVO hsd08;

	private HippaSegmentWebServiceVO accum;

	private HippaSegmentWebServiceVO utilizationMgmntRule;

	//private List eb03HippaSegment;

	//private List accumHippaSegment;

	private String msg_type_required;

	private String sensitiveBenefitIndicator;

	private HippaSegmentWebServiceVO noteTypeCode;

	private String message;

	private String variableMappingStatus;

	private String inTempTable;

	private String isMapgRequired;

	private Date lastChangedTmStamp;

	private Date createdTmStamp;

	private List<VariableWebServiceVO> variableList;

	private UserWebServiceVO user = new UserWebServiceVO();

	private boolean updateMstr;

	private RuleWebServiceVO rule;

	private SPSIdWebServiceVO spsId;

	private boolean finalizedFlagModified;

	private boolean finalized;
	// SIT Fix
	private String datafeedStatus = "N";
	/**
	 * MTM CODE CHANGE
	 */
	private String mappingComplete;
	/**
	 * END
	 */
	// SIT FIX
	private boolean mapped = false;

	private ContractMappingWebServiceVO contractMapping;


	// for datafeed requirement default is N
	private String dataFeedInd = "N";


	private String sortField;

	// variable for checking the flow ,based on this varaible application
	// navigate back to adavance search and viewlanding page
	private String pageFrom;

	private String formattedStringDate;

	private String businessEntity;
	private String businessGroup;
	private String mbu;
	
	//Audit Lock Status  -- October Release
	private String auditLock;
	
	//auditStatus for audit trail --part of october release.
	private int variableStatusForAuditTrail;
	
	//Start Age/End Age --part of BXNI June2012 Release.
	private HippaSegmentWebServiceVO startAge;
	
	private HippaSegmentWebServiceVO endAge;
	
	private String startAgeCodedValue;
	
	private String endAgeCodedValue;

	public List<AuditTrailWebServiceVO> getAuditTrails() {
		return auditTrails;
	}

	public void setAuditTrails(List<AuditTrailWebServiceVO> auditTrails) {
		this.auditTrails = auditTrails;
	}

	public VariableWebServiceVO getVariable() {
		return variable;
	}

	public void setVariable(VariableWebServiceVO variable) {
		this.variable = variable;
	}

	public HippaSegmentWebServiceVO getHippaSegment() {
		return hippaSegment;
	}

	public void setHippaSegment(HippaSegmentWebServiceVO hippaSegment) {
		this.hippaSegment = hippaSegment;
	}

	public Long getVariableSystemId() {
		return variableSystemId;
	}

	public void setVariableSystemId(Long variableSystemId) {
		this.variableSystemId = variableSystemId;
	}

	public HippaSegmentWebServiceVO getEb01() {
		return eb01;
	}

	public void setEb01(HippaSegmentWebServiceVO eb01) {
		this.eb01 = eb01;
	}

	public HippaSegmentWebServiceVO getEb02() {
		return eb02;
	}

	public void setEb02(HippaSegmentWebServiceVO eb02) {
		this.eb02 = eb02;
	}

	public HippaSegmentWebServiceVO getEb03() {
		return eb03;
	}

	public void setEb03(HippaSegmentWebServiceVO eb03) {
		this.eb03 = eb03;
	}

	public HippaSegmentWebServiceVO getEb06() {
		return eb06;
	}

	public void setEb06(HippaSegmentWebServiceVO eb06) {
		this.eb06 = eb06;
	}

	public HippaSegmentWebServiceVO getEb09() {
		return eb09;
	}

	public void setEb09(HippaSegmentWebServiceVO eb09) {
		this.eb09 = eb09;
	}

	public HippaSegmentWebServiceVO getIi02() {
		return ii02;
	}

	public void setIi02(HippaSegmentWebServiceVO ii02) {
		this.ii02 = ii02;
	}

	public HippaSegmentWebServiceVO getHsd01() {
		return hsd01;
	}

	public void setHsd01(HippaSegmentWebServiceVO hsd01) {
		this.hsd01 = hsd01;
	}

	public HippaSegmentWebServiceVO getHsd02() {
		return hsd02;
	}

	public void setHsd02(HippaSegmentWebServiceVO hsd02) {
		this.hsd02 = hsd02;
	}

	public HippaSegmentWebServiceVO getHsd03() {
		return hsd03;
	}

	public void setHsd03(HippaSegmentWebServiceVO hsd03) {
		this.hsd03 = hsd03;
	}

	public HippaSegmentWebServiceVO getHsd04() {
		return hsd04;
	}

	public void setHsd04(HippaSegmentWebServiceVO hsd04) {
		this.hsd04 = hsd04;
	}

	public HippaSegmentWebServiceVO getHsd05() {
		return hsd05;
	}

	public void setHsd05(HippaSegmentWebServiceVO hsd05) {
		this.hsd05 = hsd05;
	}

	public HippaSegmentWebServiceVO getHsd06() {
		return hsd06;
	}

	public void setHsd06(HippaSegmentWebServiceVO hsd06) {
		this.hsd06 = hsd06;
	}

	public HippaSegmentWebServiceVO getHsd07() {
		return hsd07;
	}

	public void setHsd07(HippaSegmentWebServiceVO hsd07) {
		this.hsd07 = hsd07;
	}

	public HippaSegmentWebServiceVO getHsd08() {
		return hsd08;
	}

	public void setHsd08(HippaSegmentWebServiceVO hsd08) {
		this.hsd08 = hsd08;
	}

	public HippaSegmentWebServiceVO getAccum() {
		return accum;
	}

	public void setAccum(HippaSegmentWebServiceVO accum) {
		this.accum = accum;
	}

	public HippaSegmentWebServiceVO getUtilizationMgmntRule() {
		return utilizationMgmntRule;
	}

	public void setUtilizationMgmntRule(
			HippaSegmentWebServiceVO utilizationMgmntRule) {
		this.utilizationMgmntRule = utilizationMgmntRule;
	}

	public String getMsg_type_required() {
		return msg_type_required;
	}

	public void setMsg_type_required(String msg_type_required) {
		this.msg_type_required = msg_type_required;
	}

	public String getSensitiveBenefitIndicator() {
		return sensitiveBenefitIndicator;
	}

	public void setSensitiveBenefitIndicator(String sensitiveBenefitIndicator) {
		this.sensitiveBenefitIndicator = sensitiveBenefitIndicator;
	}

	public HippaSegmentWebServiceVO getNoteTypeCode() {
		return noteTypeCode;
	}

	public void setNoteTypeCode(HippaSegmentWebServiceVO noteTypeCode) {
		this.noteTypeCode = noteTypeCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getVariableMappingStatus() {
		return variableMappingStatus;
	}

	public void setVariableMappingStatus(String variableMappingStatus) {
		this.variableMappingStatus = variableMappingStatus;
	}

	public String getInTempTable() {
		return inTempTable;
	}

	public void setInTempTable(String inTempTable) {
		this.inTempTable = inTempTable;
	}

	public String getIsMapgRequired() {
		return isMapgRequired;
	}

	public void setIsMapgRequired(String isMapgRequired) {
		this.isMapgRequired = isMapgRequired;
	}

	public Date getLastChangedTmStamp() {
		return lastChangedTmStamp;
	}

	public void setLastChangedTmStamp(Date lastChangedTmStamp) {
		this.lastChangedTmStamp = lastChangedTmStamp;
	}

	public Date getCreatedTmStamp() {
		return createdTmStamp;
	}

	public void setCreatedTmStamp(Date createdTmStamp) {
		this.createdTmStamp = createdTmStamp;
	}

	public List<VariableWebServiceVO> getVariableList() {
		return variableList;
	}

	public void setVariableList(List<VariableWebServiceVO> variableList) {
		this.variableList = variableList;
	}

	public UserWebServiceVO getUser() {
		return user;
	}

	public void setUser(UserWebServiceVO user) {
		this.user = user;
	}

	public boolean isUpdateMstr() {
		return updateMstr;
	}

	public void setUpdateMstr(boolean updateMstr) {
		this.updateMstr = updateMstr;
	}

	public RuleWebServiceVO getRule() {
		return rule;
	}

	public void setRule(RuleWebServiceVO rule) {
		this.rule = rule;
	}

	public SPSIdWebServiceVO getSpsId() {
		return spsId;
	}

	public void setSpsId(SPSIdWebServiceVO spsId) {
		this.spsId = spsId;
	}

	public boolean isFinalizedFlagModified() {
		return finalizedFlagModified;
	}

	public void setFinalizedFlagModified(boolean finalizedFlagModified) {
		this.finalizedFlagModified = finalizedFlagModified;
	}

	public boolean isFinalized() {
		return finalized;
	}

	public void setFinalized(boolean finalized) {
		this.finalized = finalized;
	}

	public String getDatafeedStatus() {
		return datafeedStatus;
	}

	public void setDatafeedStatus(String datafeedStatus) {
		this.datafeedStatus = datafeedStatus;
	}

	public String getMappingComplete() {
		return mappingComplete;
	}

	public void setMappingComplete(String mappingComplete) {
		this.mappingComplete = mappingComplete;
	}

	public boolean isMapped() {
		return mapped;
	}

	public void setMapped(boolean mapped) {
		this.mapped = mapped;
	}

	public ContractMappingWebServiceVO getContractMapping() {
		return contractMapping;
	}

	public void setContractMapping(ContractMappingWebServiceVO contractMapping) {
		this.contractMapping = contractMapping;
	}

	public String getDataFeedInd() {
		return dataFeedInd;
	}

	public void setDataFeedInd(String dataFeedInd) {
		this.dataFeedInd = dataFeedInd;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getPageFrom() {
		return pageFrom;
	}

	public void setPageFrom(String pageFrom) {
		this.pageFrom = pageFrom;
	}

	public String getFormattedStringDate() {
		return formattedStringDate;
	}

	public void setFormattedStringDate(String formattedStringDate) {
		this.formattedStringDate = formattedStringDate;
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

	public String getMbu() {
		return mbu;
	}

	public void setMbu(String mbu) {
		this.mbu = mbu;
	}

	public String getAuditLock() {
		return auditLock;
	}

	public void setAuditLock(String auditLock) {
		this.auditLock = auditLock;
	}

	public int getVariableStatusForAuditTrail() {
		return variableStatusForAuditTrail;
	}

	public void setVariableStatusForAuditTrail(int variableStatusForAuditTrail) {
		this.variableStatusForAuditTrail = variableStatusForAuditTrail;
	}

	public HippaSegmentWebServiceVO getStartAge() {
		return startAge;
	}

	public void setStartAge(HippaSegmentWebServiceVO startAge) {
		this.startAge = startAge;
	}

	public HippaSegmentWebServiceVO getEndAge() {
		return endAge;
	}

	public void setEndAge(HippaSegmentWebServiceVO endAge) {
		this.endAge = endAge;
	}

	public String getStartAgeCodedValue() {
		return startAgeCodedValue;
	}

	public void setStartAgeCodedValue(String startAgeCodedValue) {
		this.startAgeCodedValue = startAgeCodedValue;
	}

	public String getEndAgeCodedValue() {
		return endAgeCodedValue;
	}

	public void setEndAgeCodedValue(String endAgeCodedValue) {
		this.endAgeCodedValue = endAgeCodedValue;
	}


	public int compareTo(Object o) {
		if ("RULE".equals(sortField)) {
			Pattern p = Pattern.compile("^[a-zA-Z0-9]");
			// Create a matcher with an input string
			String var = ((MappingWebServiceVO) o).rule.getHeaderRuleId();
			Matcher m = p.matcher(var);
			if (m.find()) {
				return this.rule.getHeaderRuleId().compareTo(
						((MappingWebServiceVO) o).rule.getHeaderRuleId());
			} else {
				m = p.matcher(this.rule.getHeaderRuleId());
				if (m.find()) {
					return 1;
				} else {
					return this.rule.getHeaderRuleId().compareTo(
							((MappingWebServiceVO) o).rule.getHeaderRuleId());
				}
			}
		} else if ("Variable".equals(sortField)) {
			Pattern p = Pattern.compile("^[a-zA-Z0-9]");
			// Create a matcher with an input string
			String var = ((MappingWebServiceVO) o).variable.getVariableId();
			Matcher m = p.matcher(var);
			if (m.find()) {
				return this.variable.getVariableId().compareTo(
						((MappingWebServiceVO) o).variable.getVariableId());
			} else {
				m = p.matcher(this.variable.getVariableId());
				if (m.find()) {
					return 1;
				} else {
					return this.variable.getVariableId().compareTo(
							((MappingWebServiceVO) o).variable.getVariableId());
				}
			}
		} else {
			if (this.lastChangedTmStamp == ((MappingWebServiceVO) o).lastChangedTmStamp)
				return 0;
			else if (this.lastChangedTmStamp
					.before((((MappingWebServiceVO) o).lastChangedTmStamp)))
				return 1;
			else
				return -1;
		}
	}

	
}
