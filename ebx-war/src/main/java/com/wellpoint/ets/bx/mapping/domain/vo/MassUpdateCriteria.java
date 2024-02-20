package com.wellpoint.ets.bx.mapping.domain.vo;

import java.io.Serializable;

/**
 * @author UST-GLOBAL
 * This is an POJO class for Mass update.
 * 
 */
public class MassUpdateCriteria implements Serializable{
	
	/**
	 * Default Searial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * attribules starting with nv stands for new value
	 * attributes starting with cf stands for criteria field
	 * attributes starting with updated stores updated values which is used for generating xl.
	 */
	
	private String spsId;
	private String ruleId;
	private String userId;
	private String variableId;
	
	private String[] cfEb01;
	private String nvEb01;
	private String updatedEb01;
	
	private String[] cfEb02;
	private String nvEb02;
	private String updatedEb02;
	
	private String[] cfEb06;
	private String nvEb06;
	private String updatedEb06;
	
	private String[] cfEb09;
	private String nvEb09;
	private String updatedEb09;
	
	private String[] cfEb03;
	private String[] nvEb03;
	private String updatedEb03;
	
	private String[] cfHsd01;
	private String nvHsd01;
	private String updatedHsd01;
	
	private String[] cfHsd02;
	private String nvHsd02;
	private String updatedHsd02;
	
	private String[] cfHsd03;
	private String nvHsd03;
	private String updatedHsd03;
	
	private String[] cfHsd04;
	private String nvHsd04;
	private String updatedHsd04;
	
	private String[] cfHsd05;
	private String nvHsd05;
	private String updatedHsd05;
	
	private String[] cfHsd06;
	private String nvHsd06;
	private String updatedHsd06;
	
	private String[] cfHsd07;
	private String nvHsd07;
	private String updatedHsd07;
	
	private String[] cfHsd08;
	private String nvHsd08;
	private String updatedHsd08;
	
	private String[] cfIII02;
	private String nvIII02;
	private String updatedIII02;
	
	private String[] cfNoteType;
	private String nvNoteType;
	private String updatedNoteType;
	
	private String[] cfMessage;
	private String nvMessage;
	private String updatedMessage;	
	
	private String updatedStatus;
	
	public String[] getCfMessage() {
		return cfMessage;
	}
	public void setCfMessage(String[] cfMessage) {
		this.cfMessage = cfMessage;
	}
	public String getNvMessage() {
		return nvMessage;
	}
	public void setNvMessage(String nvMessage) {
		this.nvMessage = nvMessage;
	}
	public String getUpdatedMessage() {
		return updatedMessage;
	}
	public void setUpdatedMessage(String updatedMessage) {
		this.updatedMessage = updatedMessage;
	}
	
	public String[] getCfEb01() {
		return cfEb01;
	}
	public void setCfEb01(String[] cfEb01) {
		this.cfEb01 = cfEb01;
	}
	public String[] getCfEb02() {
		return cfEb02;
	}
	public void setCfEb02(String[] cfEb02) {
		this.cfEb02 = cfEb02;
	}
	public String[] getCfEb03() {
		return cfEb03;
	}
	public void setCfEb03(String[] cfEb03) {
		this.cfEb03 = cfEb03;
	}
	public String[] getCfEb06() {
		return cfEb06;
	}
	public void setCfEb06(String[] cfEb06) {
		this.cfEb06 = cfEb06;
	}
	public String[] getCfEb09() {
		return cfEb09;
	}
	public void setCfEb09(String[] cfEb09) {
		this.cfEb09 = cfEb09;
	}
	public String[] getCfHsd01() {
		return cfHsd01;
	}
	public void setCfHsd01(String[] cfHsd01) {
		this.cfHsd01 = cfHsd01;
	}
	public String[] getCfHsd02() {
		return cfHsd02;
	}
	public void setCfHsd02(String[] cfHsd02) {
		this.cfHsd02 = cfHsd02;
	}
	public String[] getCfHsd03() {
		return cfHsd03;
	}
	public void setCfHsd03(String[] cfHsd03) {
		this.cfHsd03 = cfHsd03;
	}
	public String[] getCfHsd04() {
		return cfHsd04;
	}
	public void setCfHsd04(String[] cfHsd04) {
		this.cfHsd04 = cfHsd04;
	}
	public String[] getCfHsd05() {
		return cfHsd05;
	}
	public void setCfHsd05(String[] cfHsd05) {
		this.cfHsd05 = cfHsd05;
	}
	public String[] getCfHsd06() {
		return cfHsd06;
	}
	public void setCfHsd06(String[] cfHsd06) {
		this.cfHsd06 = cfHsd06;
	}
	public String[] getCfHsd07() {
		return cfHsd07;
	}
	public void setCfHsd07(String[] cfHsd07) {
		this.cfHsd07 = cfHsd07;
	}
	public String[] getCfHsd08() {
		return cfHsd08;
	}
	public void setCfHsd08(String[] cfHsd08) {
		this.cfHsd08 = cfHsd08;
	}
	public String[] getCfIII02() {
		return cfIII02;
	}
	public void setCfIII02(String[] cfIII02) {
		this.cfIII02 = cfIII02;
	}
	public String[] getCfNoteType() {
		return cfNoteType;
	}
	public void setCfNoteType(String[] cfNoteType) {
		this.cfNoteType = cfNoteType;
	}
	public String getNvEb01() {
		return nvEb01;
	}
	public void setNvEb01(String nvEb01) {
		this.nvEb01 = nvEb01;
	}
	public String getNvEb02() {
		return nvEb02;
	}
	public void setNvEb02(String nvEb02) {
		this.nvEb02 = nvEb02;
	}
	public String[] getNvEb03() {
		return nvEb03;
	}
	public void setNvEb03(String[] nvEb03) {
		this.nvEb03 = nvEb03;
	}
	public String getNvEb06() {
		return nvEb06;
	}
	public void setNvEb06(String nvEb06) {
		this.nvEb06 = nvEb06;
	}
	public String getNvEb09() {
		return nvEb09;
	}
	public void setNvEb09(String nvEb09) {
		this.nvEb09 = nvEb09;
	}
	public String getNvHsd01() {
		return nvHsd01;
	}
	public void setNvHsd01(String nvHsd01) {
		this.nvHsd01 = nvHsd01;
	}
	public String getNvHsd02() {
		return nvHsd02;
	}
	public void setNvHsd02(String nvHsd02) {
		this.nvHsd02 = nvHsd02;
	}
	public String getNvHsd03() {
		return nvHsd03;
	}
	public void setNvHsd03(String nvHsd03) {
		this.nvHsd03 = nvHsd03;
	}
	public String getNvHsd04() {
		return nvHsd04;
	}
	public void setNvHsd04(String nvHsd04) {
		this.nvHsd04 = nvHsd04;
	}
	public String getNvHsd05() {
		return nvHsd05;
	}
	public void setNvHsd05(String nvHsd05) {
		this.nvHsd05 = nvHsd05;
	}
	public String getNvHsd06() {
		return nvHsd06;
	}
	public void setNvHsd06(String nvHsd06) {
		this.nvHsd06 = nvHsd06;
	}
	public String getNvHsd07() {
		return nvHsd07;
	}
	public void setNvHsd07(String nvHsd07) {
		this.nvHsd07 = nvHsd07;
	}
	public String getNvHsd08() {
		return nvHsd08;
	}
	public void setNvHsd08(String nvHsd08) {
		this.nvHsd08 = nvHsd08;
	}
	public String getNvIII02() {
		return nvIII02;
	}
	public void setNvIII02(String nvIII02) {
		this.nvIII02 = nvIII02;
	}
	public String getNvNoteType() {
		return nvNoteType;
	}
	public void setNvNoteType(String nvNoteType) {
		this.nvNoteType = nvNoteType;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getSpsId() {
		return spsId;
	}
	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getVariableId() {
		return variableId;
	}
	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}
	public String getUpdatedEb01() {
		return updatedEb01;
	}
	public void setUpdatedEb01(String updatedEb01) {
		this.updatedEb01 = updatedEb01;
	}
	public String getUpdatedEb02() {
		return updatedEb02;
	}
	public void setUpdatedEb02(String updatedEb02) {
		this.updatedEb02 = updatedEb02;
	}
	public String getUpdatedEb03() {
		return updatedEb03;
	}
	public void setUpdatedEb03(String updatedEb03) {
		this.updatedEb03 = updatedEb03;
	}
	public String getUpdatedEb06() {
		return updatedEb06;
	}
	public void setUpdatedEb06(String updatedEb06) {
		this.updatedEb06 = updatedEb06;
	}
	public String getUpdatedEb09() {
		return updatedEb09;
	}
	public void setUpdatedEb09(String updatedEb09) {
		this.updatedEb09 = updatedEb09;
	}
	public String getUpdatedHsd01() {
		return updatedHsd01;
	}
	public void setUpdatedHsd01(String updatedHsd01) {
		this.updatedHsd01 = updatedHsd01;
	}
	public String getUpdatedHsd02() {
		return updatedHsd02;
	}
	public void setUpdatedHsd02(String updatedHsd02) {
		this.updatedHsd02 = updatedHsd02;
	}
	public String getUpdatedHsd03() {
		return updatedHsd03;
	}
	public void setUpdatedHsd03(String updatedHsd03) {
		this.updatedHsd03 = updatedHsd03;
	}
	public String getUpdatedHsd04() {
		return updatedHsd04;
	}
	public void setUpdatedHsd04(String updatedHsd04) {
		this.updatedHsd04 = updatedHsd04;
	}
	public String getUpdatedHsd05() {
		return updatedHsd05;
	}
	public void setUpdatedHsd05(String updatedHsd05) {
		this.updatedHsd05 = updatedHsd05;
	}
	public String getUpdatedHsd06() {
		return updatedHsd06;
	}
	public void setUpdatedHsd06(String updatedHsd06) {
		this.updatedHsd06 = updatedHsd06;
	}
	public String getUpdatedHsd07() {
		return updatedHsd07;
	}
	public void setUpdatedHsd07(String updatedHsd07) {
		this.updatedHsd07 = updatedHsd07;
	}
	public String getUpdatedHsd08() {
		return updatedHsd08;
	}
	public void setUpdatedHsd08(String updatedHsd08) {
		this.updatedHsd08 = updatedHsd08;
	}
	public String getUpdatedIII02() {
		return updatedIII02;
	}
	public void setUpdatedIII02(String updatedIII02) {
		this.updatedIII02 = updatedIII02;
	}
	public String getUpdatedNoteType() {
		return updatedNoteType;
	}
	public void setUpdatedNoteType(String updatedNoteType) {
		this.updatedNoteType = updatedNoteType;
	}
	public String getUpdatedStatus() {
		return updatedStatus;
	}
	public void setUpdatedStatus(String updatedStatus) {
		this.updatedStatus = updatedStatus;
	}
	
}
