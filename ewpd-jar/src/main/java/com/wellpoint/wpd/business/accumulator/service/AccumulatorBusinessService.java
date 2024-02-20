package com.wellpoint.wpd.business.accumulator.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.wellpoint.wpd.business.accumulator.builder.AccumulatorBuilder;
import com.wellpoint.wpd.business.accumulator.builder.AccumulatorBuilderImpl;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.accumulator.bo.Accumulator;
import com.wellpoint.wpd.common.accumulator.bo.AccumulatorImpl;
import com.wellpoint.wpd.common.accumulator.bo.SearchCriteria;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceDataSet;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.accumulator.request.AccumulatorFilterRequest;

import com.wellpoint.wpd.common.accumulator.response.AccumulatorFilterResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;

public class AccumulatorBusinessService extends WPDBusinessService {
	String existSystem = "";

	public WPDResponse execute(AccumulatorFilterRequest request)
			throws ServiceException {
		AccumulatorFilterRequest filterRequest = (AccumulatorFilterRequest) request;
		AccumulatorFilterResponse accumulatorFilterResponse = getAccumulatorFilterResponse();
		if (filterRequest.getOperationMode().equals(
				filterRequest.ACCUM_POPUP_FILTER)) {
			List errorList = new ArrayList();
			List accumList = filterAccumPopUp(filterRequest.getDescription(),
					filterRequest.getSelectedPva(), filterRequest
							.getSelectedCstTyp(),filterRequest.getQuestion(),filterRequest.getBenefit());

			if (null != accumList) {
				int searchResultCount = accumList.size();
				if (searchResultCount > 0 && searchResultCount < 50) {
					accumulatorFilterResponse.setAccumList(accumList);

				} else if (searchResultCount > 0 && searchResultCount >= 50) {
					accumulatorFilterResponse.setAccumList(accumList);
					errorList.add(new InformationalMessage(
							BusinessConstants.SEARCH_RESULT_EXCEEDS));
					accumulatorFilterResponse.setMessages(errorList);
				} else if (searchResultCount == 0) {
					errorList.add(new InformationalMessage(
							BusinessConstants.SEARCH_RESULT_NOTFOUND));
					accumulatorFilterResponse.setMessages(errorList);
				}
			}
		}
		 else if (filterRequest.REFERENCE_DATA.equals(filterRequest
	                .getOperationMode())) {
			 AccumulatorBuilder referenceDatabuilder = new AccumulatorBuilderImpl();
	            int CDCI_CD_ITM_ID = filterRequest.getPopupId();
	            String autoCompleteValue = filterRequest.getAutoCompleteValue();
	            ReferenceDataSet referenceDataSet = null;
	           /* if (autoCompleteValue == null) {
	                referenceDataSet = referenceDatabuilder.getReferenceDataSet(
	                        CDCI_CD_ITM_ID, null);
	            } else {
	                referenceDataSet = referenceDatabuilder.getReferenceDataSet(
	                        CDCI_CD_ITM_ID, autoCompleteValue + "%");
	          lder re  }*/
	          
		           
	          
	            List popupList = referenceDatabuilder
	                    .getReferenceDataSetExactOrder(CDCI_CD_ITM_ID);
	            accumulatorFilterResponse.setList(popupList);
	        } else if (filterRequest.EXCATREFERENCE_DATA.equals(filterRequest
	                .getOperationMode())) {
	        	 AccumulatorBuilder referenceDatabuilder = new AccumulatorBuilderImpl();
	           
	            int CDCI_CD_ITM_ID = filterRequest.getPopupId();
	            List popupList = referenceDatabuilder
	                    .getReferenceDataSetExactOrder(CDCI_CD_ITM_ID);
	            accumulatorFilterResponse.setList(popupList);
	        }
		return accumulatorFilterResponse;
	}

	public AccumulatorFilterResponse getAccumulatorFilterResponse() {
		AccumulatorFilterResponse accumulatorFilterResponse = (AccumulatorFilterResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.ACCUMULATOR_FILTER_RESPONSE);
		return accumulatorFilterResponse;
	}

	public List filterAccumPopUp(String description, String selectedPva,
			String selectedCstTyp,String question,String benefit) throws ServiceException {

		AccumulatorBuilder accumSearchFactory = new AccumulatorBuilderImpl();
		Accumulator accumulator = new AccumulatorImpl();
		if (description == null || description.equals("")) {
				accumulator.setName(null);
			} else {
				description = "%" + description + "%";
				accumulator.setName(description);
			}
		if(benefit.trim().equalsIgnoreCase("search")){
			accumulator.setBenefit(null);
		}
		else
		accumulator.setBenefit(benefit);
		if(question.trim().equalsIgnoreCase("search")){
			accumulator.setQuestion(null);
		}
		else
		accumulator.setQuestion(question);
		if(selectedPva!=null && !selectedPva.trim().equalsIgnoreCase(""))
		accumulator.setPva(selectedPva);
		if(selectedCstTyp!=null && !selectedCstTyp.trim().equalsIgnoreCase(""))
		accumulator.setCstTyp(selectedCstTyp);
		List accumValuesList = new ArrayList();
		accumulator.setSvcCde("%");
		accumValuesList = accumSearchFactory.filterAccumValues(
				BusinessConstants.GET_ALL_ACCUMULATOR, accumulator);

		if (null == accumValuesList) {
			accumValuesList = new ArrayList();
		}
		return accumValuesList;

	}

	
	

}
