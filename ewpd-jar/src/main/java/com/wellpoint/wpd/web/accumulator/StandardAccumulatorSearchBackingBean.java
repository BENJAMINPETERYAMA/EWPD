package com.wellpoint.wpd.web.accumulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.context.FacesContext;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.accumulator.request.StandardAccumulatorRequest;
import com.wellpoint.wpd.common.accumulator.response.AccumulatorResponse;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;

import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

public class StandardAccumulatorSearchBackingBean extends WPDBackingBean {

	StandardAccumulatorRequest stdAccumRequest = null;
	private List searchResultList;
	private List validationMessages = null;
	private String lineOfBusiness;
	private String be;
	private String group;
	private String mbu;
	private String byOrCy;
	private String question;
	private String benefitString;
	private String benefit;
	
	private List questionList;
	private String lobInput;
	private String beInput;
	private String bgInput;
	private String mbuInput;
	private String questInput;
	private String accumInput;
	private String typeInput;
	private String benefitInput;
	
	public String getBenefitInput() {
		return benefitInput;
	}

	public void setBenefitInput(String benefitInput) {
		this.benefitInput = benefitInput;
	}

	private String businessEntity;
	private String variableId;
	private String accumulatorName;
	private Map sessionScope;

	public String newStdAccumulatorSearchCriteria() {
		// SessionCleanUp.cleanUp();
		sessionScope = (Map) FacesContext.getCurrentInstance().getApplication()
				.createValueBinding("#{sessionScope}").getValue(
						FacesContext.getCurrentInstance());
		if (null != sessionScope.get("searchStdAccumBackingBean")) {
			sessionScope.remove("searchStdAccumBackingBean");
		}
		validationMessages = null;
		return "success";
	}
	
	public List splitValue(String val) {
		StringTokenizer stringTokenizer = new StringTokenizer(val,
		"~");
		String tokenVar = new String();
		List selectedTokenList = new ArrayList();
		int tokenCount=1;
		while (stringTokenizer.hasMoreTokens()) {
			tokenVar = stringTokenizer.nextToken();
           if(tokenCount%2==0){
        	   selectedTokenList.add(tokenVar);
           }
           tokenCount++;
		}
		return  selectedTokenList;
	}
	private boolean validateRequiredFileds() {

		validationMessages = new ArrayList();
		
		if ((this.beInput == null || this.beInput.trim().equals("")) 
				&& (this.lobInput == null || this.lobInput.trim().equals(""))
				&& (this.bgInput == null || this.bgInput.trim().equals(""))
				&& (this.mbuInput == null || this.mbuInput.trim().equals(""))
				&& (this.questInput == null || this.questInput.trim().equals(""))
				&& (this.benefitInput == null || this.benefitInput.trim().equals(""))
				&& (this.accumInput == null || this.accumInput.trim().equals(""))
				&& (this.typeInput == null || this.typeInput.trim().equals(""))
				) {
			
		validationMessages.add(new ErrorMessage(
				"all.fields.blank"));
			return false;
		}
	

		// Add accum validations here

		return true;
	}
	public String performStdAccumSearch() {
		
		 if (!validateRequiredFileds()) {
			 addAllMessagesToRequest(validationMessages); 
			 searchResultList = null;		
			 return ""; 
		 }
		
		Logger.logInfo("Invoked search structure");
		List selectedBEList1= splitValue(this.beInput);
		List selectedlobInputList = splitValue(this.lobInput);
		List selectedbgInputList = splitValue(this.bgInput);
		List selectedmbuInputList = splitValue(this.mbuInput);
		List selectedquesList =splitValue(this.questInput);
		List selectedbenInputList=splitValue(this.benefitInput);
		this.accumulatorName = this.accumInput;
		StringTokenizer stringTokenizeraccum = new StringTokenizer(
				this.accumulatorName, "~");
		List selectedAccumList = new ArrayList();
		while (stringTokenizeraccum.hasMoreTokens()) {
			String accumVar = stringTokenizeraccum.nextToken();
			if (accumVar.indexOf("@@") <= -1) {
				selectedAccumList.add(accumVar);
			}
		}
		stdAccumRequest = (StandardAccumulatorRequest) this
				.getServiceRequest(ServiceManager.SEARCH_STDACCUM1);
		if(selectedBEList1 !=null && !selectedBEList1.isEmpty()){
			stdAccumRequest.setBeLst(selectedBEList1);
		}
		if(selectedlobInputList!=null && !selectedlobInputList.isEmpty()){
			stdAccumRequest.setLobLst(selectedlobInputList)	;
		}
		if(selectedbgInputList!=null && !selectedbgInputList.isEmpty()){
			stdAccumRequest.setBgLst(selectedbgInputList);
		}
		if(selectedmbuInputList!=null && !selectedmbuInputList.isEmpty()){
			stdAccumRequest.setMbuLst(selectedmbuInputList);
		}
		if(selectedquesList!=null && !selectedquesList.isEmpty()){
			stdAccumRequest.setQuesLst(selectedquesList);
		}
		if(selectedbenInputList!=null && !selectedbenInputList.isEmpty()){
			stdAccumRequest.setBenLst(selectedbenInputList);
		}
		if(selectedAccumList!=null && !selectedAccumList.isEmpty()){
			stdAccumRequest.setAccumulatorNameLst(selectedAccumList);
		}
		if(this.typeInput !=null && !this.typeInput.trim().equalsIgnoreCase(""))
		stdAccumRequest.setByOrCy(this.typeInput.trim());
		
		stdAccumRequest
				.setAction(BusinessConstants.MAPPED_ACCUMULATOR_SEARCH_ACTION);
		AccumulatorResponse accumulatorResponse = (AccumulatorResponse) this
				.executeService(stdAccumRequest);

		if (null != accumulatorResponse) {
			searchResultList = accumulatorResponse
					.getSearchResultsStandardAccum();
			validationMessages = accumulatorResponse.getMessages();
		}

		return WebConstants.SEARCH_STD_ACCUMULATOR_MENU_ACTION;
	}

	public String getAccumulatorName() {
		return accumulatorName;
	}

	public void setAccumulatorName(String accumulatorName) {
		this.accumulatorName = accumulatorName;
	}

	public String getBusinessEntity() {
		return businessEntity;
	}

	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}

	public String getVariableId() {
		return variableId;
	}

	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}

	public List getSearchResultList() {
		return searchResultList;
	}

	public void setSearchResultList(List searchResultList) {
		this.searchResultList = searchResultList;
	}

	public List getValidationMessages() {
		return validationMessages;
	}

	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
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

	public void setByOrCy(String contractType) {
		this.byOrCy = contractType;
	}

	public String getByOrCy() {
		return byOrCy;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	public void setLobInput(String lobInput) {
		this.lobInput = lobInput;
	}

	public String getLobInput() {
		return lobInput;
	}

	public void setBeInput(String beInput) {
		this.beInput = beInput;
	}

	public String getBeInput() {
		return beInput;
	}

	public void setBgInput(String bgInput) {
		this.bgInput = bgInput;
	}

	public String getBgInput() {
		return bgInput;
	}

	public void setMbuInput(String mbuInput) {
		this.mbuInput = mbuInput;
	}

	public String getMbuInput() {
		return mbuInput;
	}

	public void setQuestInput(String questInput) {
		this.questInput = questInput;
	}

	public String getQuestInput() {
		return questInput;
	}

	public void setAccumInput(String accumInput) {
		this.accumInput = accumInput;
	}

	public String getAccumInput() {
		return accumInput;
	}

	public void setTypeInput(String typeInput) {
		this.typeInput = typeInput;
	}

	public String getTypeInput() {
		return typeInput;
	}

	public void setQuestionList(List questionList) {
		this.questionList = questionList;
	}

	public List getQuestionList() {
		return questionList;
	}

	public void setBenefitString(String benefitString) {
		this.benefitString = benefitString;
	}

	public String getBenefitString() {
		return benefitString;
	}

	public void setBenefit(String benefit) {
		this.benefit = benefit;
	}

	public String getBenefit() {
		return benefit;
	}

}
