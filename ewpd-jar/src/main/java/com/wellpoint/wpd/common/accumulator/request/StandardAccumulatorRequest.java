package com.wellpoint.wpd.common.accumulator.request;

import java.util.List;

import com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

;

public class StandardAccumulatorRequest extends WPDRequest {
	private List benefitStructure;
	private List beLst;
	private List lobLst;
	private List bgLst;
	private List mbuLst;
	private List quesLst;
	private List typeLst;
	private List benLst;
	private String accumulatorName;
	private List accumulatorNameLst;
	StandardAccumulator standardAccumulatorSet = null;
	private List standardAccumMappingInsertLst;
	private String be;
	private String lineOfBusiness;
	private int benefit;
	private String group;
	private String mbu;
	private String byOrCy;
	private int question;
	private List questionList;
	private String system;
	private int action;

	private String buttonId = null;

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public List getBenefitStructure() {
		return benefitStructure;
	}

	public void setBenefitStructure(List benefitStructure) {
		this.benefitStructure = benefitStructure;
	}

	public String getAccumulatorName() {
		return accumulatorName;
	}

	public void setAccumulatorName(String accumulatorName) {
		this.accumulatorName = accumulatorName;
	}

	public StandardAccumulator getStandardAccumulatorSet() {
		return standardAccumulatorSet;
	}

	public void setStandardAccumulatorSet(
			StandardAccumulator standardAccumulatorSet) {
		this.standardAccumulatorSet = standardAccumulatorSet;
	}

	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	public List getStandardAccumMappingInsertLst() {
		return standardAccumMappingInsertLst;
	}

	public void setStandardAccumMappingInsertLst(
			List standardAccumMappingInsertLst) {
		this.standardAccumMappingInsertLst = standardAccumMappingInsertLst;
	}

	public List getAccumulatorNameLst() {
		return accumulatorNameLst;
	}

	public void setAccumulatorNameLst(List accumulatorNameLst) {
		this.accumulatorNameLst = accumulatorNameLst;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}

	public String getLineOfBusiness() {
		return lineOfBusiness;
	}

	public void setBe(String be) {
		this.be = be;
	}

	public String getBe() {
		return be;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getGroup() {
		return group;
	}

	public void setMbu(String mbu) {
		this.mbu = mbu;
	}

	public String getMbu() {
		return mbu;
	}

	public void setByOrCy(String byOrCy) {
		this.byOrCy = byOrCy;
	}

	public String getByOrCy() {
		return byOrCy;
	}

	public void setQuestion(int question) {
		this.question = question;
	}

	public int getQuestion() {
		return question;
	}

	public void setBeLst(List beLst) {
		this.beLst = beLst;
	}

	public List getBeLst() {
		return beLst;
	}

	public void setLobLst(List lobLst) {
		this.lobLst = lobLst;
	}

	public List getLobLst() {
		return lobLst;
	}

	public void setBgLst(List bgLst) {
		this.bgLst = bgLst;
	}

	public List getBgLst() {
		return bgLst;
	}

	public void setMbuLst(List mbuLst) {
		this.mbuLst = mbuLst;
	}

	public List getMbuLst() {
		return mbuLst;
	}

	public void setQuesLst(List quesLst) {
		this.quesLst = quesLst;
	}

	public List getQuesLst() {
		return quesLst;
	}

	public void setTypeLst(List typeLst) {
		this.typeLst = typeLst;
	}

	public List getTypeLst() {
		return typeLst;
	}

	public void setQuestionList(List questionList) {
		this.questionList = questionList;
	}

	public List getQuestionList() {
		return questionList;
	}

	public void setBenLst(List benLst) {
		this.benLst = benLst;
	}

	public List getBenLst() {
		return benLst;
	}

	public void setBenefit(int benefit) {
		this.benefit = benefit;
	}

	public int getBenefit() {
		return benefit;
	}

}
