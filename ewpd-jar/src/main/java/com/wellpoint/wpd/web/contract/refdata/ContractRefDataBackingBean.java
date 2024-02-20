/*
 * ContractRefDataBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract.refdata;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.model.SelectItem;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.accumulator.request.AccumulatorFilterRequest;
import com.wellpoint.wpd.common.accumulator.response.AccumulatorFilterResponse;

import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import java.util.Iterator;
import com.wellpoint.wpd.web.framework.service.ServiceManager;

import com.wellpoint.wpd.web.util.WebConstants;
import com.wellpoint.wpd.common.accumulator.response.AccumulatorFilterResponse;
import com.wellpoint.wpd.common.accumulator.response.AccumulatorResponse;
import com.wellpoint.wpd.common.accumulator.request.AccumulatorFilterRequest;

import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceData;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.web.refdata.ReferenceDataBackingBean;

public class ContractRefDataBackingBean extends ReferenceDataBackingBean {

	private List accumumlatorValues;
	
	private int referenceType = 0;

	private String searchText;
	
	 List provArrForVar;
	 
	 private String hiddenBenefitandQuestionForAccum="";
	 private String operationmode="";
	 
	 public String getHiddenBenefitandQuestionForAccum() {
		return hiddenBenefitandQuestionForAccum;
	}

	public void setHiddenBenefitandQuestionForAccum(
			String hiddenBenefitandQuestionForAccum) {
		this.hiddenBenefitandQuestionForAccum = hiddenBenefitandQuestionForAccum;
	}



	private String hiddenBenefitForAccum="";
	
    public void setProvArrForVar(List provArrForVar) {
		this.provArrForVar = provArrForVar;
	}

	

	private final String SEARCH_ITEM_PVA = "providerArr";

	private final String SEARCH_ITEM_CST = "costShareType";

	private final String SEARCH_ITEM_DESC = "accumDescription";

	private List searchItemValueList = new ArrayList();
	

	public void setAccumumlatorValues(List accumumlatorValues) {
		this.accumumlatorValues = accumumlatorValues;
	}

	protected HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		return request;
	}

	public List getAccumumlatorValues() {
		
		hiddenBenefitandQuestionForAccum = this.getRequest().getParameter("hiddenBenefitandQuestionForAccum");
		
		
		if(null != hiddenBenefitandQuestionForAccum){
			hiddenBenefitandQuestionForAccum = hiddenBenefitandQuestionForAccum.trim();
		}else{
			hiddenBenefitandQuestionForAccum = "";
		}
		 StringTokenizer stringTokenizer = new StringTokenizer(hiddenBenefitandQuestionForAccum ,"@"); 
		   	List selectedBEList = new ArrayList();
		   	String benefit="";
			String question="";
		   	while(stringTokenizer.hasMoreTokens()) {
		   		String beVar = stringTokenizer.nextToken();
		   		if(stringTokenizer.countTokens()==0 ){
		   			operationmode=beVar;
		   		}else if(stringTokenizer.countTokens()==1 ){
		   			String[] temp= beVar.split("~");
		   			benefit = temp [1];
		   		}else{
		   			String[] temp= beVar.split("~");
	   			    question = temp [1];
		   		}
		   		
		   		}
		   	
		   	if(!operationmode.trim().equalsIgnoreCase("edit"))
		   	{
		   		benefit="search";
		   		question="search";
		   	}
		   	
        String searchType=new String();
		String selectedSearchItem = this.getRequest().getParameter(
				"selectedSearchItem");
		String searchText = this.getRequest().getParameter("searchText");

		if (null == selectedSearchItem) {
			selectedSearchItem = "";
		} else {
			selectedSearchItem = selectedSearchItem.trim();
		}

		if (null == searchText) {
			searchText = "";
		} else {
			searchText = searchText.trim().toUpperCase();
		}

		// Filter PopUp
		if (selectedSearchItem.equals(SEARCH_ITEM_PVA)) {
			accumumlatorValues = filterAccumPopUp(searchType, searchText,searchType,question,benefit);
		} else if (selectedSearchItem.equals(SEARCH_ITEM_CST)) {
			accumumlatorValues = filterAccumPopUp(searchType, searchType, searchText,question,benefit);
		} else if (selectedSearchItem.equals(SEARCH_ITEM_DESC)) {
			accumumlatorValues = filterAccumPopUp(searchText,searchType,searchType,question,benefit);
		} else {
			accumumlatorValues = filterAccumPopUp(searchType,searchType, searchType,question,benefit);
		}

		if (null == accumumlatorValues) {
			accumumlatorValues = new ArrayList();
		}

		return accumumlatorValues;

	}

	public List filterAccumPopUp(String description, String selectedPva,
			String selectedCstTyp,String question,String benefit) {
		AccumulatorFilterRequest accumulatorFilterRequest = (AccumulatorFilterRequest) this
				.getServiceRequest(ServiceManager.FETCH_ACCUM_FORPOPUP);

		accumulatorFilterRequest.setDescription(description);
		accumulatorFilterRequest.setSelectedPva(selectedPva);
		accumulatorFilterRequest.setSelectedCstTyp(selectedCstTyp);
		accumulatorFilterRequest.setQuestion(question);
		accumulatorFilterRequest.setBenefit(benefit);
		accumulatorFilterRequest
				.setOperationMode(accumulatorFilterRequest.ACCUM_POPUP_FILTER);

		AccumulatorFilterResponse accumulatorFilterResponse = (AccumulatorFilterResponse) executeService(accumulatorFilterRequest);
		return accumulatorFilterResponse.getAccumList();
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchItemValueList(List searchItemValueList) {
		this.searchItemValueList = searchItemValueList;
	}
	public List getProvArrForVar() {
        
		 AccumulatorFilterRequest accumulatorFilterRequest = (AccumulatorFilterRequest)this
			.getServiceRequest(ServiceManager.FETCH_ACCUM_FORPOPUP);
	        setReferenceType(accumulatorFilterRequest.POPUP_LIST);
	        accumulatorFilterRequest.setPopupId(1936);
	        accumulatorFilterRequest.setOperationMode(accumulatorFilterRequest.REFERENCE_DATA);
	        //refdataRequest.setAutoCompleteValue(provider);
	        AccumulatorFilterResponse accumulatorFilterResponse = (AccumulatorFilterResponse) executeService(accumulatorFilterRequest);
	     
	        provArrForVar = accumulatorFilterResponse.getList();

			return provArrForVar;
	}
	
	public List getCstTypForComboBox() {
       
		 AccumulatorFilterRequest accumulatorFilterRequest = (AccumulatorFilterRequest)this
			.getServiceRequest(ServiceManager.FETCH_ACCUM_FORPOPUP);
	        setReferenceType(accumulatorFilterRequest.POPUP_LIST);
	        accumulatorFilterRequest.setPopupId(50171);
	        accumulatorFilterRequest.setAutoCompleteValue("");
	        accumulatorFilterRequest.setOperationMode(accumulatorFilterRequest.EXCATREFERENCE_DATA);
	        AccumulatorFilterResponse accumulatorFilterResponse = (AccumulatorFilterResponse) executeService(accumulatorFilterRequest);
	        
	        return accumulatorFilterResponse.getList();
	}
	
	public List getSearchItemValueList() {
		String searchString = this.getRequest().getParameter("selectedSearchItem");
		List tempProvArrForComboBox = new ArrayList();
		
		if(SEARCH_ITEM_PVA.equals(searchString)){
			tempProvArrForComboBox = getProvArrForVar();
		}else if(SEARCH_ITEM_CST.equals(searchString)){
			tempProvArrForComboBox = getCstTypForComboBox();
		}else{
			return tempProvArrForComboBox;
		}
        
        Iterator itr = tempProvArrForComboBox.iterator();
        searchItemValueList = new ArrayList();
        searchItemValueList.add(new SelectItem("",""));
        
       while (itr.hasNext()) {
            ReferenceData referenceData = (ReferenceData) itr.next();
            String code = referenceData.getCode().trim();
            searchItemValueList.add(new SelectItem(code,code));
        }
        
		return searchItemValueList;
	}

	public void setReferenceType(int referenceType) {
		this.referenceType = referenceType;
	}

	public int getReferenceType() {
		return referenceType;
	}

	

	public void setHiddenBenefitForAccum(String hiddenBenefitForAccum) {
		this.hiddenBenefitForAccum = hiddenBenefitForAccum;
	}

	public String getHiddenBenefitForAccum() {
		return hiddenBenefitForAccum;
	}

	public void setOperationmode(String operationmode) {
		this.operationmode = operationmode;
	}

	public String getOperationmode() {
		return operationmode;
	}

}