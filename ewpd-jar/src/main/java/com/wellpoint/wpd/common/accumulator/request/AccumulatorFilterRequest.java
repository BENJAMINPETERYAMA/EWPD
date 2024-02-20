package com.wellpoint.wpd.common.accumulator.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


public class AccumulatorFilterRequest extends WPDRequest{
	
	private String variableFormat;
	private String description;
	private String selectedPva;
	private String selectedCstTyp;
	private String operationMode;
	private String question;
	private String benefit;
	
	private int popupId;
	private String autoCompleteValue;
	 public static final String EXCATREFERENCE_DATA = "exactReferenceData";
	 public static final String REFERENCE_DATA = "referenceData";
	 public static final String ACCUM_POPUP_FILTER = "ACCUM_POPUP_FILTER";
	 public static final int POPUP_LIST = 3;

	
	//Getters and Setters
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOperationMode() {
		return operationMode;
	}
	public void setOperationMode(String operationMode) {
		this.operationMode = operationMode;
	}
	public String getSelectedCstTyp() {
		return selectedCstTyp;
	}
	public void setSelectedCstTyp(String selectedCstTyp) {
		this.selectedCstTyp = selectedCstTyp;
	}
	public String getSelectedPva() {
		return selectedPva;
	}
	public void setSelectedPva(String selectedPva) {
		this.selectedPva = selectedPva;
	}
	public String getVariableFormat() {
		return variableFormat;
	}
	public void setVariableFormat(String variableFormat) {
		this.variableFormat = variableFormat;
	}
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	public void setPopupId(int popupId) {
		this.popupId = popupId;
	}
	public int getPopupId() {
		return popupId;
	}
	public void setAutoCompleteValue(String autoCompleteValue) {
		this.autoCompleteValue = autoCompleteValue;
	}
	public String getAutoCompleteValue() {
		return autoCompleteValue;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestion() {
		return question;
	}
	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}
	public String getBenefit() {
		return benefit;
	}
	
	
}
