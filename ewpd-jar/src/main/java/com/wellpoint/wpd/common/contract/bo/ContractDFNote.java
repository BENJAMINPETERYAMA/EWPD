package com.wellpoint.wpd.common.contract.bo;

public class ContractDFNote {
	
	public static final int COMPONENT_NOTE = 1;
	public static final int BENEFIT_NOTE = 2;
	public static final int BENEFIT_LINE_NOTE = 3;
	public static final int QUESTION_NOTE = 4;
	public static final int INVALID_NOTE = -1;
	
	private int rowNum;
	private int noteType;
	private int dateSegmentId;
	private int benefitCompId;
	private int benefitId;
	private int benefitDefnId;
	private int questionNumber;
	private String noteId;
	private String noteText;	
	
	private String scndryEntityTpe;
	private int scndryEntityId;
	
	private int questionnaireId;
	
	// Attributes for Feed file
	private int majorHeadingId;
	private int minorHeadingId;
	private String spsId;
	private boolean noteValidForDatafeed;


//	public int getNoteType() {
//		if("ATTACHQUESTION".equals(scndryEntityTpe)) {
//			return  QUESTION_NOTE;
//		}else if ("ATTACHBNFTLINE".equals(scndryEntityTpe)) {
//			return BENEFIT_LINE_NOTE;
//		}else if ("ATTACHBENEFIT".equals(scndryEntityTpe)) {
//			return BENEFIT_NOTE;
//		}else if ("ATTACHCOMP".equals(scndryEntityTpe)) {
//			return COMPONENT_NOTE;
//		}else {
//			return INVALID_NOTE;
//		}
//	}

	public int getBenefitLineId() {
		if(noteType == BENEFIT_LINE_NOTE)
			return scndryEntityId;
		else 
			return -1;
	}
	
	public int getAdminOptionId() {
		if(noteType == QUESTION_NOTE)
			return scndryEntityId;
		else 
			return -1;
	}


	public int getQuestionNumber() {
		return questionNumber;
	}
	
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber; 
	}

	public String getScndryEntityTpe() {
		return scndryEntityTpe;
	}
	
	public void setScndryEntityTpe(String scndryEntityTpe) {
		this.scndryEntityTpe = scndryEntityTpe;
	}

	public int getBenefitCompId() {
		return benefitCompId;
	}

	public void setBenefitCompId(int benefitCompId) {
		this.benefitCompId = benefitCompId;
	}

	public int getBenefitId() {
		return benefitId;
	}

	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}

	public int getBenefitDefnId() {
		return benefitDefnId;
	}

	public void setBenefitDefnId(int benefitDefnId) {
		this.benefitDefnId = benefitDefnId;
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	public int getScndryEntityId() {
		return scndryEntityId;
	}

	public void setScndryEntityId(int scndryEntityId) {
		this.scndryEntityId = scndryEntityId;
	}

	public boolean isNoteValidForDatafeed() {
		return noteValidForDatafeed;
	}

	public void setNoteValidForDatafeed(boolean noteValidForDatafeed) {
		this.noteValidForDatafeed = noteValidForDatafeed;
	}

	public int getMajorHeadingId() {
		return majorHeadingId;
	}

	public void setMajorHeadingId(int majorHeadingId) {
		this.majorHeadingId = majorHeadingId;
	}

	public int getMinorHeadingId() {
		return minorHeadingId;
	}

	public void setMinorHeadingId(int minorHeadingId) {
		this.minorHeadingId = minorHeadingId;
	}

	public String getSpsId() {
		return spsId;
	}

	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}

	public int getDateSegmentId() {
		return dateSegmentId;
	}

	public void setDateSegmentId(int dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public void setNoteType(int noteType) {
		this.noteType = noteType;
	}

	public int getNoteType() {
		return noteType;
	}

	public int getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(int questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

}
