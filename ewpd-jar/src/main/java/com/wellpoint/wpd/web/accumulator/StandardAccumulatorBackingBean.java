package com.wellpoint.wpd.web.accumulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.context.FacesContext;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.accumulator.bo.SearchResultSet;
import com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator;
import com.wellpoint.wpd.common.accumulator.request.StandardAccumulatorRequest;
import com.wellpoint.wpd.common.accumulator.response.AccumulatorResponse;
import com.wellpoint.wpd.common.benefit.request.QuestionRequest;
import com.wellpoint.wpd.common.framework.request.WPDRequestFactory;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;

import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

public class StandardAccumulatorBackingBean extends WPDBackingBean {

	private final String system = "ewpd";

	private List validationMessages = null;
	StandardAccumulator standardAccumulatorBO;
	private String lineOfBusiness;
	private String be;
	private String group;
	private String mbu;
	private String byOrCy;
	private String benefitString;
	private int benefit;
	
	private boolean requiredLOB=false;
	private boolean requiredBE=false;
	private boolean requiredBG=false;
	private boolean requiredMBU=false;
	private boolean requiredBEN=false;
	private boolean requiredQUES=false;
	private boolean requiredBYCY=false;
	
	
	private List questionList;
	private String questionString;
	private String accumulatorName;
	
	private String tempQuestionString;
	private String tempBenefitString;
	private String success;
	private int question;
	public int getQuestion() {
		return question;
	}

	public void setQuestion(int question) {
		this.question = question;
	}

	private String delBusinessEntity;
	private String delAccumulatorName;
	private String delbg;
	private String dellob;
	private String delmbu;
	private int delques;
	private String deltype;
	private String delben;
	private String operation = "create";

	StandardAccumulatorRequest stdAccumulatorRequest;
	private List businessentityLst;
	private List accumList;
	

	private boolean done = false;
	private boolean done1 = false;
	private boolean anyModification = false;
	private boolean requiredBentity = false;
	private boolean requiredVar = false;
	private boolean requiredAccum = false;

	

	private String buttonId;
	
	private SearchResultSet searchResultSet;
	private List searchResultList = new ArrayList();
	private List searchStdResultList;
	private Map sessionScope;
	private String benefitDesc;
	private String quesdesc;
	private String operationInput;
	private String lobInput;
	private String beInput;
	private String bgInput;
	private String mbuInput;
	private String questInput;
	private String accumInput;
	private String typeInput;
	private String benefitInput;
	private String accumDelLstInput;
	private String beDelLstInput;
	private String benefitDelLstInput;
	private String lobDelLstInput;
	private String bgDelLstInput;
	private String mbuDelLstInput;
	private int quesDelLstInput;
	private String typeDelLstInput;

	public StandardAccumulatorBackingBean() {
		standardAccumulatorBO = new StandardAccumulator();
		this.be = null;
		this.setLineOfBusiness(null);
		this.setGroup(null);
		this.setMbu(null);
		
		this.setByOrCy(null);
		this.accumulatorName = null;
this.questionList=null;
	}

	public String newCreateStdAccumulator() {
		// SessionCleanUp.cleanUp();
		sessionScope = (Map) FacesContext.getCurrentInstance().getApplication()
				.createValueBinding("#{sessionScope}").getValue(
						FacesContext.getCurrentInstance());
		if (null != sessionScope.get("standardAccumBackingBean")) {
			sessionScope.remove("standardAccumBackingBean");
		}
		validationMessages = null;
		return "success";
	}

	public List getValidationMessages() {
		return validationMessages;
	}

	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	public String getAccumulatorName() {
		return null;
	}

	public void setAccumulatorName(String accumulatorName) {
		if (!done1) {
			done1 = true;
			this.accumulatorName = accumulatorName;
		}
		done1 = !done1;
	}

	public String getBe() {
		return be;
	}

	public void setBe(String be) {
		if (!done) {
			done = true;
			this.be = be;
		}
		done = !done;
	}

	public void getFetchFromDB() {

		StringTokenizer stringTokenizer = new StringTokenizer(this.beInput, "~");
		List selectedBEList = new ArrayList();
		String beVar = new String();
		while (stringTokenizer.hasMoreTokens()) {
			beVar = stringTokenizer.nextToken();

		}
		selectedBEList.add(beVar);
		StringTokenizer stringTokenizerlob = new StringTokenizer(this.lobInput,
				"~");
		List selectedlobInputList = new ArrayList();
		String lobVar = new String();
		while (stringTokenizerlob.hasMoreTokens()) {
			lobVar = stringTokenizerlob.nextToken();
		}
		selectedlobInputList.add(lobVar);

		StringTokenizer stringTokenizerbg = new StringTokenizer(this.bgInput,
				"~");
		List selectedbgInputList = new ArrayList();
		String lobbg = new String();
		while (stringTokenizerbg.hasMoreTokens()) {
			lobbg = stringTokenizerbg.nextToken();
		}
		selectedbgInputList.add(lobbg);
		StringTokenizer stringTokenizermbu = new StringTokenizer(this.mbuInput,
				"~");
		List selectedmbuInputList = new ArrayList();
		String lobmbu = new String();
		while (stringTokenizermbu.hasMoreTokens()) {
			lobmbu = stringTokenizermbu.nextToken();
		}
		selectedmbuInputList.add(lobmbu);
		
		
		List selectedquesList = new ArrayList();
		String quesVar = new String();
		String quesdesc= new String();
		if(!this.questInput.equalsIgnoreCase("")){
			String[] questionarray=this.questInput.split("~");
			quesdesc=questionarray[0];
			quesVar=questionarray[1];
			selectedquesList.add(quesVar);
		}
		 
		
		List selectedbenInputList = new ArrayList();
		String lobben = new String();
		String lobval = new String();
		String bendesc= new String();
		if(!this.benefitInput.equalsIgnoreCase("")){
			String[] benefitarray=this.benefitInput.split("~");
			bendesc=benefitarray[0];
			lobval=benefitarray[1];
			selectedbenInputList.add(lobval);	
		}
		
		String type = this.typeInput;
		this.tempBenefitString=lobval;
		this.tempQuestionString=quesVar;
		this.anyModification=false;
		
		if (this.operationInput.equalsIgnoreCase("FETCH")) {
			searchResultList = new ArrayList();
			this.setBusinessentityLst(selectedBEList);
			stdAccumulatorRequest = (StandardAccumulatorRequest) this
					.getServiceRequest(ServiceManager.SAVE_STDACCUM);
			stdAccumulatorRequest
					.setAction(BusinessConstants.ACCUMULATOR_SEARCH_ACTION);
			stdAccumulatorRequest.setBe(beVar);
			stdAccumulatorRequest.setLineOfBusiness(lobVar);
			stdAccumulatorRequest.setGroup(lobbg);
			stdAccumulatorRequest.setMbu(lobmbu);
			stdAccumulatorRequest.setByOrCy(type);
			stdAccumulatorRequest.setQuesLst( selectedquesList);
			stdAccumulatorRequest.setQuestion(Integer.parseInt(quesVar));
			
			stdAccumulatorRequest.setBenefit(Integer.parseInt(lobval));
			
			
			AccumulatorResponse accumulatorResponse = (AccumulatorResponse) this
					.executeService(stdAccumulatorRequest);

			if (null != accumulatorResponse ) {
				this.searchResultList = accumulatorResponse
						.getSearchResultsStandardAccum();
				validationMessages = accumulatorResponse.getMessages();
			}
		}
		if (this.operationInput.equalsIgnoreCase("ADD")) {
			List selectedAccumList = new ArrayList();
			if (this.accumInput != null && !this.accumInput.equals("")) {
				
				StringTokenizer stringTokenizer1 = new StringTokenizer(
						this.accumInput, "~");

				while (stringTokenizer1.hasMoreTokens()) {
					String accumVar = stringTokenizer1.nextToken();
					if (accumVar.indexOf("@@") <= -1) {
						selectedAccumList.add(accumVar);
					}
				}
			}
			if (null == searchResultList) {
				searchResultList = new ArrayList();
			}
			for (int i = 0; i < selectedBEList.size(); i++) {
				for (int j = 0; j < selectedAccumList.size(); j++) {
					for (int l = 0; l < selectedlobInputList.size(); l++) {
						for (int m = 0; m < selectedbgInputList.size(); m++) {
							for (int n = 0; n < selectedmbuInputList.size(); n++) {
								for (int p = 0; p < selectedquesList.size(); p++) {
									for (int q = 0; q < selectedbenInputList.size(); q++) {
									boolean checkExist = false;
									if (searchResultList != null
											&& !searchResultList.isEmpty()) {
										for (int k = 0; k < searchResultList
												.size(); k++) {
											StandardAccumulator standardAccum = (StandardAccumulator) searchResultList
													.get(k);
											if (standardAccum
													.getBe()
													.equalsIgnoreCase(
															selectedBEList.get(
																	i)
																	.toString())
													&& standardAccum
															.getLineOfBusiness()
															.equalsIgnoreCase(
																	selectedlobInputList
																			.get(
																					l)
																			.toString())
													&& standardAccum
															.getGroup()
															.equalsIgnoreCase(
																	selectedbgInputList
																			.get(
																					m)
																			.toString())
													&& standardAccum
															.getMbu()
															.equalsIgnoreCase(
																	selectedmbuInputList
																			.get(
																					n)
																			.toString()) &&
													 standardAccum
															.getQuestion() == (Integer
															.parseInt(selectedquesList
																	.get(p)
																	.toString()))
													&& standardAccum
															.getStandardAccumulatorStr()
															.equalsIgnoreCase(
																	selectedAccumList
																			.get(
																					j)
																			.toString()) &&
																			 standardAccum
																				.getBenefit()== (Integer
																				.parseInt(selectedbenInputList
																						.get(q)
																						.toString())))	{
												if("" == type || type.equalsIgnoreCase("")){
													if(null != standardAccum.getByOrCy() && !standardAccum.getByOrCy().equalsIgnoreCase("")){
														searchResultList.remove(k);
														k--;
														checkExist = false;
													}else
														checkExist = true;
												}else{
														checkExist = true;
												}
												
											}
										}
									}

									if (!checkExist) {
										StandardAccumulator standardAccum = new StandardAccumulator();
										standardAccum
												.setStandardAccumulatorStr(selectedAccumList
														.get(j).toString());
										standardAccum.setBe(selectedBEList.get(
												i).toString());
										standardAccum
												.setLineOfBusiness(selectedlobInputList
														.get(l).toString());
										standardAccum
												.setGroup(selectedbgInputList
														.get(m).toString());
										standardAccum
												.setMbu(selectedmbuInputList
														.get(n).toString());
										standardAccum
												.setQuestion(Integer.parseInt(selectedquesList
														.get(p).toString()));
										standardAccum.setStandardAccumulator(selectedAccumList);
										if(lobval.trim().equals("") ){
											standardAccum.setBenefitString(selectedbenInputList
													.get(q)
													.toString());
										}
										else
										standardAccum.setBenefitString(lobval);
										standardAccum.setQuestionString(quesVar);
										 standardAccum.setQuestionList(selectedquesList);
										 if(!type.trim().equalsIgnoreCase("")){
											 standardAccum.setByOrCy(type); 
										 } 
										 else
										 standardAccum.setByOrCy(null); 
										standardAccum.setQuestionDescription(quesdesc);
										standardAccum.setBenefitDescription(bendesc);
										if(lobval.trim().equals("") ){
											standardAccum.setBenefit(Integer.parseInt(selectedbenInputList
													.get(q)
													.toString()));
										}
										else
											standardAccum.setBenefit(Integer.parseInt(lobval));
										this.setBe(selectedBEList.get(i)
												.toString());
										
										this.setAccumList(selectedAccumList);
										this
												.setLineOfBusiness(selectedlobInputList
														.get(l).toString());
										this.setGroup(selectedbgInputList
												.get(m).toString());
										this.setMbu(selectedmbuInputList.get(n)
												.toString());
										
										this.setQuestion(Integer.parseInt(selectedquesList
												.get(p).toString()));
										this.setBenefit(Integer.parseInt(selectedbenInputList
												.get(q).toString()));
										//this.setBenefitString(selectedbenInputList
											//	.get(q).toString());
										 if(!type.trim().equalsIgnoreCase("")){
											 this.setByOrCy(type);} 
										 else
											 this.setByOrCy(null);

										
										//this.setQuestionString(selectedquesList
												//.get(p).toString());
										
										this.setQuestionString(this.questInput);
										this.setBenefitString(this.benefitInput);
										
										 if (!validateRequiredFileds()) {
											 addAllMessagesToRequest(validationMessages); 
											  }
										 else{
											 this.anyModification=true;
											 searchResultList.add(standardAccum);}
									}
								}
							}
						}
					}
				}
			}
			}
		}
		if (this.operationInput.equalsIgnoreCase("DELETE")) {
			if (this.beDelLstInput != null
					&& !this.beDelLstInput.equals("")
					&& (this.lobDelLstInput != null && !this.lobDelLstInput
							.equals(""))
					&& (this.bgDelLstInput != null && !this.bgDelLstInput
							.equals(""))
					&& (this.mbuDelLstInput != null && !this.mbuDelLstInput
							.equals(""))
					&& (this.quesDelLstInput != 0)
					
					&& (this.benefitDelLstInput != null && !this.benefitDelLstInput
							.equals("")) && this.benefitDelLstInput != null
					&& !this.accumDelLstInput.equals("")) {
				this.delBusinessEntity = this.beDelLstInput;
				this.delAccumulatorName = this.accumDelLstInput;
				this.dellob = this.lobDelLstInput;
				this.delbg = this.bgDelLstInput;
				this.delmbu = this.mbuDelLstInput;
				this.delques = this.quesDelLstInput;
				if((this.typeDelLstInput != null && !this.typeDelLstInput
						.equals("")) && this.accumDelLstInput != null){
				this.deltype = this.typeDelLstInput;}
				this.delben=this.benefitDelLstInput;

			}

			for (int k = 0; k < searchResultList.size(); k++) {
				StandardAccumulator standardAccum = (StandardAccumulator) searchResultList
						.get(k);

				// if(standardAccum.getBusinessentity().equalsIgnoreCase(this.delBusinessEntity)
				// &&
				// standardAccum.getStandardAccumulatorStr().equalsIgnoreCase(this.delAccumulatorName)){
				if (standardAccum.getBe().equalsIgnoreCase(
						this.delBusinessEntity)
						&& standardAccum.getLineOfBusiness().equalsIgnoreCase(
								this.dellob)
						&& standardAccum.getGroup().equalsIgnoreCase(
								this.bgDelLstInput)
						&& standardAccum.getMbu().equalsIgnoreCase(
								this.mbuDelLstInput)
						 &&
						standardAccum.getQuestion()==this.quesDelLstInput
						&& standardAccum.getByOrCy().equalsIgnoreCase(
								this.typeDelLstInput)
						&& standardAccum.getStandardAccumulatorStr()
								.equalsIgnoreCase(this.delAccumulatorName)
					&& standardAccum.getBenefit()
					== (Integer.parseInt(this.delben)))
				{

					searchResultList.remove(k);
					this.buttonId = "edit";
					this.anyModification=true;
				}

			}
		}

	}

	public String saveStdAccumMapping() {

		
		 if (!validateRequiredFileds()) {
		 addAllMessagesToRequest(validationMessages); return ""; }
		 
		/* StringTokenizer stringTokenizerques = new StringTokenizer(
					this.questionString, "~");
			String quesVar = new String();
			
			quesVar = stringTokenizerques.nextToken();
			quesVar = stringTokenizerques.nextToken();
			
			
			StringTokenizer stringTokenizerBen = new StringTokenizer(this.benefitInput,
					"~");
			
			String lobval = stringTokenizerBen.nextToken();*/

		stdAccumulatorRequest = (StandardAccumulatorRequest) this
				.getServiceRequest(ServiceManager.SAVE_STDACCUM);
		stdAccumulatorRequest
				.setAction(BusinessConstants.ACCUMULATOR_CREATE_ACTION);
		stdAccumulatorRequest.setBe(this.be);
		stdAccumulatorRequest.setLineOfBusiness(this.lineOfBusiness);
		
		stdAccumulatorRequest.setGroup(this.group);
		stdAccumulatorRequest.setMbu(this.mbu);
		stdAccumulatorRequest.setByOrCy(this.byOrCy);
		//stdAccumulatorRequest.setAccumulatorName(this.accumulatorName);
		stdAccumulatorRequest.setQuestion(Integer.parseInt(this.tempQuestionString));
		stdAccumulatorRequest.setAccumulatorNameLst(this.accumList);
		stdAccumulatorRequest.setBenefit(Integer.parseInt(this.tempBenefitString));
		stdAccumulatorRequest
				.setStandardAccumMappingInsertLst(searchResultList);
		
		stdAccumulatorRequest.setSystem(system);
		if (this.anyModification) {
			AccumulatorResponse accumulatorResponse = (AccumulatorResponse) this
					.executeService(stdAccumulatorRequest);

			accumulatorResponse.setSearchResultList(searchResultList);
			if (null != accumulatorResponse) {
				if (accumulatorResponse.isSuccess()) {
					this.buttonId = "update";
				}
				
			}
			this.success = "SUCCESS";
			this.accumulatorName="";
		} else {
			List messageList = new ArrayList();
			messageList.add(new InformationalMessage(
					BusinessConstants.STD_ACCUMULATOR_NOTHING_TOSAVE));
			addAllMessagesToRequest(messageList);
		}
		return "";
	}

	private boolean validateRequiredFileds() {

		validationMessages = new ArrayList();
		boolean requiredField = false;
		if (this.be == null || this.be.trim().equals("")) {
			requiredBE = true;
			requiredField = true;
		}
		if (this.lineOfBusiness == null
				|| this.lineOfBusiness.trim().equals("")) {
			requiredLOB = true;
			requiredField = true;
		}
		if (this.group == null || this.group.trim().equals("")) {
			requiredBG = true;
			requiredField = true;
		}
		if (this.mbu == null || this.mbu.trim().equals("")) {
			requiredMBU = true;
			requiredField = true;
		}
		if (this.benefitString == null || this.benefitString.trim().equals("")) {
			requiredBEN = true;
			requiredField = true;
		}
		if (this.questionString == null || this.questionString.trim().equals("")) {
			requiredQUES = true;
			requiredField = true;
		}
		
		/*
		 * if (this.accumulatorName == null || this.accumulatorName
		 * .trim().equals("")) { requiredAccum = true; requiredField = true; }
		 */

		if (requiredField) {
			validationMessages.add(new ErrorMessage(
					WebConstants.MANDATE_FIELD_REQUIRED));
			return false;
		}
		else
		{
			requiredBE = false;
			requiredLOB = false;
			requiredBG = false;
			requiredMBU = false;
			requiredBEN = false;
			requiredQUES = false;
			
		}

		// Add accum validations here

		return true;
	}

	public void setSearchResultList(List searchResultList) {
		this.searchResultList = searchResultList;
	}

	public SearchResultSet getSearchResultSet() {
		return searchResultSet;
	}

	public void setSearchResultSet(SearchResultSet searchResultSet) {
		this.searchResultSet = searchResultSet;
	}

	public List getBusinessentityLst() {
		return businessentityLst;
	}

	public void setBusinessentityLst(List businessentityLst) {
		this.businessentityLst = businessentityLst;
	}

	public String editAction() {
		
		stdAccumulatorRequest = (StandardAccumulatorRequest) this
				.getServiceRequest(ServiceManager.EDIT_STDACCUM);
		stdAccumulatorRequest.setBe(this.be);
		stdAccumulatorRequest.setLineOfBusiness(this.lineOfBusiness);
		stdAccumulatorRequest.setGroup(this.group);
		stdAccumulatorRequest.setMbu(this.mbu);
		if(this.byOrCy !=null && !this.byOrCy.trim().equalsIgnoreCase(""))
		stdAccumulatorRequest.setByOrCy(this.byOrCy);
		
		stdAccumulatorRequest.setQuestion(Integer.parseInt(this.questionString));
		stdAccumulatorRequest.setBenefit(Integer.parseInt(this.benefitString));
		tempQuestionString = this.questionString;
	    tempBenefitString = this.benefitString;
		//String benvalue=this.benefitString+"~"+this.benefitDesc;
		String benvalue=this.benefitDesc+"~"+this.benefitString;
		
		this.setBenefitString(benvalue);
	//	this.accumulatorName=null;
		String questvalue=this.quesdesc+"~"+this.questionString;
		this.setQuestionString(questvalue);
	
		stdAccumulatorRequest
				.setAction(BusinessConstants.ACCUMULATOR_SEARCH_ACTION);
		AccumulatorResponse accumulatorResponse = (AccumulatorResponse) this
				.executeService(stdAccumulatorRequest);
		if (null != accumulatorResponse) {
			searchResultList = accumulatorResponse
					.getSearchResultsStandardAccum();
			validationMessages = accumulatorResponse.getMessages();
		}
		
		/*
		 * AccumulatorImpl accImpl =
		 * (AccumulatorImpl)accumulatorResponse.getAccumulatorSet();
		 * if(accImpl.getInClaimsFlg()!= null &&
		 * "N".equalsIgnoreCase(accImpl.getInClaimsFlg())){ this.accumCreated =
		 * false; this.accumDescCreated = false; this.inClaimSysMessage =
		 * "Not available in Claims System"; }else{ this.accumCreated = true;
		 * this.accumDescCreated = true; this.inClaimSysMessage = ""; }
		 * this.accumulatorBO = accumulatorResponse.getAccumulatorSet();
		 * this.buttonId = "update"; return "createAccumulatorMenuAction";
		 * }else{ return ""; }
		 */
		this.buttonId = "edit";
		this.operation="edit";
		return "createStdAccumulatorMenuAction";
	}

	public String getButtonId() {
		return buttonId;
	}

	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	public StandardAccumulator getStandardAccumulatorBO() {
		return standardAccumulatorBO;
	}

	public void setStandardAccumulatorBO(
			StandardAccumulator standardAccumulatorBO) {
		this.standardAccumulatorBO = standardAccumulatorBO;
	}

	public List getSearchStdResultList() {
		return searchStdResultList;
	}

	public void setSearchStdResultList(List searchStdResultList) {
		this.searchStdResultList = searchStdResultList;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public boolean isRequiredAccum() {
		return requiredAccum;
	}

	public void setRequiredAccum(boolean requiredAccum) {
		this.requiredAccum = requiredAccum;
	}

	public boolean isRequiredBentity() {
		return requiredBentity;
	}

	public void setRequiredBentity(boolean requiredBentity) {
		this.requiredBentity = requiredBentity;
	}

	public boolean isRequiredVar() {
		return requiredVar;
	}

	public void setRequiredVar(boolean requiredVar) {
		this.requiredVar = requiredVar;
	}

	public String getOperationInput() {
		return operationInput;
	}

	public void setOperationInput(String operationInput) {
		this.operationInput = operationInput;
	}

	public String getBeInput() {
		return beInput;
	}

	public void setBeInput(String beInput) {
		this.beInput = beInput;
	}

	public List getSearchResultList() {
		return searchResultList;
	}

	public String getAccumInput() {
		return accumInput;
	}

	public void setAccumInput(String accumInput) {
		this.accumInput = accumInput;
	}

	public String getAccumDelLstInput() {
		return accumDelLstInput;
	}

	public void setAccumDelLstInput(String accumDelLstInput) {
		this.accumDelLstInput = accumDelLstInput;
	}

	public String getBeDelLstInput() {
		return beDelLstInput;
	}

	public void setBeDelLstInput(String beDelLstInput) {
		this.beDelLstInput = beDelLstInput;
	}

	public boolean isAnyModification() {
		return anyModification;
	}

	public void setAnyModification(boolean anyModification) {
		this.anyModification = anyModification;
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

	

	public void setLobInput(String lobInput) {
		this.lobInput = lobInput;
	}

	public String getLobInput() {
		return lobInput;
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

	public void setTypeInput(String typeInput) {
		this.typeInput = typeInput;
	}

	public String getTypeInput() {
		return typeInput;
	}

	public void setLobDelLstInput(String lobDelLstInput) {
		this.lobDelLstInput = lobDelLstInput;
	}

	public String getLobDelLstInput() {
		return lobDelLstInput;
	}

	public void setBgDelLstInput(String bgDelLstInput) {
		this.bgDelLstInput = bgDelLstInput;
	}

	public String getBgDelLstInput() {
		return bgDelLstInput;
	}

	public void setMbuDelLstInput(String mbuDelLstInput) {
		this.mbuDelLstInput = mbuDelLstInput;
	}

	public String getMbuDelLstInput() {
		return mbuDelLstInput;
	}

	public void setQuesDelLstInput(int quesDelLstInput) {
		this.quesDelLstInput = quesDelLstInput;
	}

	public int getQuesDelLstInput() {
		return quesDelLstInput;
	}

	public void setTypeDelLstInput(String typeDelLstInput) {
		this.typeDelLstInput = typeDelLstInput;
	}

	public String getTypeDelLstInput() {
		return typeDelLstInput;
	}

	public void setQuestInput(String questInput) {
		this.questInput = questInput;
	}

	public String getQuestInput() {
		return questInput;
	}

	public void setDelbg(String delbg) {
		this.delbg = delbg;
	}

	public String getDelbg() {
		return delbg;
	}

	public void setDellob(String dellob) {
		this.dellob = dellob;
	}

	public String getDellob() {
		return dellob;
	}

	public void setDelmbu(String delmbu) {
		this.delmbu = delmbu;
	}

	public String getDelmbu() {
		return delmbu;
	}

	public void setDelques(int delques) {
		this.delques = delques;
	}

	public int getDelques() {
		return delques;
	}

	public void setDeltype(String deltype) {
		this.deltype = deltype;
	}

	public String getDeltype() {
		return deltype;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

	public void setAccumList(List accumList) {
		this.accumList = accumList;
	}

	public List getAccumList() {
		return accumList;
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

	public void setBenefitString(String benefitString) {
		this.benefitString = benefitString;
	}

	public String getBenefitString() {
		return benefitString;
	}

	public void setBenefit(int benefit) {
		this.benefit = benefit;
	}

	public int getBenefit() {
		return benefit;
	}

	public void setBenefitInput(String benefitInput) {
		this.benefitInput = benefitInput;
	}

	public String getBenefitInput() {
		return benefitInput;
	}

	public void setBenefitDelLstInput(String benefitDelLstInput) {
		this.benefitDelLstInput = benefitDelLstInput;
	}

	public String getBenefitDelLstInput() {
		return benefitDelLstInput;
	}

	public void setDelben(String delben) {
		this.delben = delben;
	}

	public String getDelben() {
		return delben;
	}

	public void setRequiredLOB(boolean requiredLOB) {
		this.requiredLOB = requiredLOB;
	}

	public boolean isRequiredLOB() {
		return requiredLOB;
	}

	public void setRequiredBE(boolean requiredBE) {
		this.requiredBE = requiredBE;
	}

	public boolean isRequiredBE() {
		return requiredBE;
	}

	public void setRequiredBG(boolean requiredBG) {
		this.requiredBG = requiredBG;
	}

	public boolean isRequiredBG() {
		return requiredBG;
	}

	public void setRequiredBYCY(boolean requiredBYCY) {
		this.requiredBYCY = requiredBYCY;
	}

	public boolean isRequiredBYCY() {
		return requiredBYCY;
	}

	public void setRequiredQUES(boolean requiredQUES) {
		this.requiredQUES = requiredQUES;
	}

	public boolean isRequiredQUES() {
		return requiredQUES;
	}

	public void setRequiredBEN(boolean requiredBEN) {
		this.requiredBEN = requiredBEN;
	}

	public boolean isRequiredBEN() {
		return requiredBEN;
	}

	public void setRequiredMBU(boolean requiredMBU) {
		this.requiredMBU = requiredMBU;
	}

	public boolean isRequiredMBU() {
		return requiredMBU;
	}

	public void setBenefitDesc(String benefitDesc) {
		this.benefitDesc = benefitDesc;
	}

	public String getBenefitDesc() {
		return benefitDesc;
	}

	public void setQuesdesc(String quesdesc) {
		this.quesdesc = quesdesc;
	}

	public String getQuesdesc() {
		return quesdesc;
	}

	
}
