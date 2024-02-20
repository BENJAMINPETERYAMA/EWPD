package com.wellpoint.wpd.business.accumulator.service;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.accumulator.builder.AccumulatorBuilder;
import com.wellpoint.wpd.business.accumulator.builder.AccumulatorBuilderImpl;
import com.wellpoint.wpd.business.framework.service.WPDBusinessService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.accumulator.bo.SearchResultSet;
import com.wellpoint.wpd.common.accumulator.bo.StandardAccumulator;
import com.wellpoint.wpd.common.accumulator.request.StandardAccumulatorRequest;
import com.wellpoint.wpd.common.accumulator.response.AccumulatorResponse;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;

public class StandardAccumulatorBusinessService extends WPDBusinessService {

	public WPDResponse execute(WPDRequest request) {

		StandardAccumulator standardAccumulator = null;

		if (request instanceof StandardAccumulatorRequest) {
			List searchResultList = null;
			int searchResultCount = 0;
			List errorList = new ArrayList();
			StandardAccumulatorRequest stdAccumRequest = (StandardAccumulatorRequest) request;
			AccumulatorResponse accumulatorResponse = new AccumulatorResponse();

			AccumulatorBuilder builder = new AccumulatorBuilderImpl();
			if (stdAccumRequest.getAction() == BusinessConstants.ACCUMULATOR_SEARCH_ACTION) {
				SearchResultSet searchResultSet = null;

				// if(stdAccumRequest.getAccumulatorName()!=null){
				if (stdAccumRequest.getBeLst() == null) {
					List tempBE = new ArrayList();
					tempBE.add(stdAccumRequest.getBe());

					List tempAcc = new ArrayList();
					// tempAcc.add(stdAccumRequest.getAccumulatorName());

					try {
						searchResultSet = builder.searchStandardAccumulator(
								stdAccumRequest.getBenefit(),
								stdAccumRequest.getQuestion(), stdAccumRequest
										.getLineOfBusiness(), tempBE,
								stdAccumRequest.getGroup(), stdAccumRequest
										.getMbu(), stdAccumRequest.getByOrCy(),
								tempAcc);
					} catch (ServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} 

				if (null != searchResultSet) {
					searchResultList = searchResultSet.getSearchResult();
					searchResultCount = searchResultList.size();
					List newsearchResultList = new ArrayList();
					for (int count = 0; count < searchResultCount; count++) {
						StandardAccumulator stdAccImpl = (StandardAccumulator) searchResultList
								.get(count);
						newsearchResultList.add(stdAccImpl);
					}
					searchResultSet.setSearchResult(newsearchResultList);
				}

				if (searchResultCount > 0 && searchResultCount < 50) {
					accumulatorResponse.setSearchResultSet(searchResultSet);

				} else if (searchResultCount > 0 && searchResultCount >= 50) {
					accumulatorResponse.setSearchResultSet(searchResultSet);
					errorList.add(new InformationalMessage(
							BusinessConstants.SEARCH_RESULT_EXCEEDS));
					accumulatorResponse.setMessages(errorList);
				} else if (searchResultCount == 0) {
					errorList.add(new InformationalMessage(
							BusinessConstants.MAPPED_RESULT_NOTFOUND));
					accumulatorResponse.setMessages(errorList);
				}

				if (searchResultList.size() > 0) {
					accumulatorResponse
							.setSearchResultsStandardAccum(searchResultList);
				}
				return accumulatorResponse;
			} else if (stdAccumRequest.getAction() == BusinessConstants.ACCUMULATOR_CREATE_ACTION) {
				standardAccumulator = new StandardAccumulator();
				standardAccumulator.setLineOfBusiness(stdAccumRequest
						.getLineOfBusiness());
				standardAccumulator.setBe(stdAccumRequest.getBe());
				standardAccumulator.setGroup(stdAccumRequest.getGroup());
				standardAccumulator.setMbu(stdAccumRequest.getMbu());
				standardAccumulator
						.setByOrCy(stdAccumRequest.getByOrCy());
				standardAccumulator.setStandardAccumulatorStr(stdAccumRequest
						.getAccumulatorName());
				standardAccumulator.setQuestion(stdAccumRequest.getQuestion());
				standardAccumulator.setStandardAccumulator(stdAccumRequest
						.getStandardAccumMappingInsertLst());
				standardAccumulator.setSystem(stdAccumRequest.getSystem());
				standardAccumulator.setStandardAccumulator(stdAccumRequest
						.getAccumulatorNameLst());
				standardAccumulator.setBeList(stdAccumRequest.getBeLst());
				standardAccumulator.setBenefit(stdAccumRequest.getBenefit());
				 standardAccumulator.setStandardAccumMappingInsertLst(stdAccumRequest.getStandardAccumMappingInsertLst());

				if (stdAccumRequest.getButtonId() == null) {
					try {
						builder.createStdAccumulator(standardAccumulator);
					} catch (ServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SevereException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				accumulatorResponse.addMessage(new InformationalMessage(
						BusinessConstants.STD_ACCUMULATOR_SAVE_SUCCESS_INFO));
				// accumulatorResponse.setStandardAccumulatorSet(standardAccumulator);
				accumulatorResponse.setSuccess(true);
				return accumulatorResponse;

			} else if (stdAccumRequest.getAction() == BusinessConstants.MAPPED_ACCUMULATOR_SEARCH_ACTION) {
				
				SearchResultSet searchResultSet = null;
				try {
					searchResultSet = builder
							.searchMappedStandardAccumulator(stdAccumRequest.getBenLst(),stdAccumRequest
									.getQuesLst(), stdAccumRequest
									.getLobLst(),stdAccumRequest.getBeLst(), stdAccumRequest.getBgLst(),
									stdAccumRequest.getMbuLst(), stdAccumRequest
											.getByOrCy(), stdAccumRequest
											.getAccumulatorNameLst());
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (null != searchResultSet) {
					searchResultList = searchResultSet.getSearchResult();
					searchResultCount = searchResultList.size();
					List newsearchResultList = new ArrayList();
					for (int count = 0; count < searchResultCount; count++) {
						StandardAccumulator stdAccImpl = (StandardAccumulator) searchResultList
								.get(count);
						newsearchResultList.add(stdAccImpl);
					}
					searchResultSet.setSearchResult(newsearchResultList);
				}

				if (searchResultCount > 0 && searchResultCount < 50) {
					accumulatorResponse.setSearchResultSet(searchResultSet);

				} else if (searchResultCount > 0 && searchResultCount >= 50) {
					accumulatorResponse.setSearchResultSet(searchResultSet);
					errorList.add(new InformationalMessage(
							BusinessConstants.SEARCH_RESULT_EXCEEDS));
					accumulatorResponse.setMessages(errorList);
				} else if (searchResultCount == 0) {
					errorList.add(new InformationalMessage(
							BusinessConstants.SEARCH_RESULT_NOTFOUND));
					accumulatorResponse.setMessages(errorList);
				}

				if (searchResultList.size() > 0) {
					accumulatorResponse
							.setSearchResultsStandardAccum(searchResultList);
				}
				return accumulatorResponse;

			}
		}

		return null;
	}

}
