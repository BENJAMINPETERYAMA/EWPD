package com.wellpoint.wpd.common.accumulator.bo;

import java.util.List;

public class StandardAccumulator {
	private List standardAccumulator;

	private String system;
	private int businessdomain;
	private String lineOfBusiness;
	private String questionString;
	private String group;
	private String mbu;
	private String byOrCy;
	private int question;
	private int benefit;
	private String benefitString;
	private String benefitDescription;
	private String questionDescription;
	private List benefitDescriptionList;
	private List questionDescriptionList;
	private List questionList;
	private List standardAccumMappingInsertLst;

	private List beList;
	private List lobList;
	private List groupList;
	private List mbuList;
	private List quesList;
	private List benList;
	

	private String be;

	private String standardAccumulatorStr;

	private String id;

	public void setStandardAccumulator(List standardAccumulator) {
		this.standardAccumulator = standardAccumulator;
	}

	public List getStandardAccumulator() {
		return standardAccumulator;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getSystem() {
		return system;
	}

	public String getStandardAccumulatorStr() {
		return standardAccumulatorStr;
	}

	public void setStandardAccumulatorStr(String standardAccumulatorStr) {
		this.standardAccumulatorStr = standardAccumulatorStr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setBe(String be) {
		this.be = be;
	}

	public String getBe() {
		return be;
	}

	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}

	public String getLineOfBusiness() {
		return lineOfBusiness;
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

	public void setBusinessdomain(int businessdomain) {
		this.businessdomain = businessdomain;
	}

	public int getBusinessdomain() {
		return businessdomain;
	}

	public void setQuestionString(String questionString) {
		this.questionString = questionString;
	}

	public String getQuestionString() {
		return questionString;
	}

	public void setQuestionList(List questionList) {
		this.questionList = questionList;
	}

	public List getQuestionList() {
		return questionList;
	}

	public void setBeList(List beList) {
		this.beList = beList;
	}

	public List getBeList() {
		return beList;
	}

	public void setLobList(List lobList) {
		this.lobList = lobList;
	}

	public List getLobList() {
		return lobList;
	}

	public void setGroupList(List groupList) {
		this.groupList = groupList;
	}

	public List getGroupList() {
		return groupList;
	}

	public void setMbuList(List mbuList) {
		this.mbuList = mbuList;
	}

	public List getMbuList() {
		return mbuList;
	}

	public void setStandardAccumMappingInsertLst(
			List standardAccumMappingInsertLst) {
		this.standardAccumMappingInsertLst = standardAccumMappingInsertLst;
	}

	public List getStandardAccumMappingInsertLst() {
		return standardAccumMappingInsertLst;
	}

	public void setBenefit(int benefit) {
		this.benefit = benefit;
	}

	public int getBenefit() {
		return benefit;
	}

	public void setBenefitString(String benefitString) {
		this.benefitString = benefitString;
	}

	public String getBenefitString() {
		
		return benefitString;
	}

	public void setBenefitDescription(String benefitDescription) {
		this.benefitDescription = benefitDescription;
	}

	public String getBenefitDescription() {
		return benefitDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuesList(List quesList) {
		this.quesList = quesList;
	}

	public List getQuesList() {
		return quesList;
	}

	public void setBenList(List benList) {
		this.benList = benList;
	}

	public List getBenList() {
		return benList;
	}

	public void setQuestionDescriptionList(List questionDescriptionList) {
		this.questionDescriptionList = questionDescriptionList;
	}

	public List getQuestionDescriptionList() {
		return questionDescriptionList;
	}

	public void setBenefitDescriptionList(List benefitDescriptionList) {
		this.benefitDescriptionList = benefitDescriptionList;
	}

	public List getBenefitDescriptionList() {
		return benefitDescriptionList;
	}

}
