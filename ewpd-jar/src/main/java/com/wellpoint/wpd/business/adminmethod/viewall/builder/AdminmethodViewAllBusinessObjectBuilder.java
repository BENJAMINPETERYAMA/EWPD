/*
 * Created on Oct 5, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.adminmethod.viewall.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminmethod.viewall.adapter.AdminmethodViewAllAdapterManager;
import com.wellpoint.wpd.business.adminmethod.viewall.bo.AdminMethodViewAllFilterBO;
import com.wellpoint.wpd.common.adminmethodmapping.bo.QuestionAnswerBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.web.util.WPDStringUtil;

/**
 * @author u18739
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminmethodViewAllBusinessObjectBuilder extends
		AdminmethodViewAllAdapterManager {

	public List adminMethodSearchList(
			AdminMethodViewAllFilterBO adminMethodViewAllFilterBO)
			throws SevereException {

		Logger
				.logInfo("AdminmethodViewAllBusinessObjectBuilder - Entering method locatePopupResult(AdminMethodViewAllFilterBO)");

		AdminmethodViewAllAdapterManager adminmethodViewAllAdapterManager = new AdminmethodViewAllAdapterManager();
		List adminMethodList;
		try {
			adminMethodViewAllFilterBO.setAdminMethodDescription(WPDStringUtil.addDelimiterForAMDesc(adminMethodViewAllFilterBO.getAdminMethodDescription()));
			adminMethodList = adminmethodViewAllAdapterManager
					.getSearchResults(adminMethodViewAllFilterBO);
			adminMethodList = groupAdminMethods(adminMethodList);

			for (Iterator iter = adminMethodList.iterator(); iter.hasNext();) {
				AdminMethodViewAllFilterBO adminMethodViewAllBO = (AdminMethodViewAllFilterBO) iter
						.next();
				if(null!=adminMethodViewAllBO.getTerm() && !"".equals(adminMethodViewAllBO.getTerm())){
				String[] term=adminMethodViewAllBO.getTerm().split(",");
				String termDesc="";
				for (int i = 0; i < term.length; i++) {
					if(!"".equals(termDesc))
						termDesc=termDesc+", "+term[i];
					else 
						termDesc=term[i];
				}
				adminMethodViewAllBO.setTerm(termDesc);
				}
				List answerList = adminmethodViewAllAdapterManager
						.getAnswerList(adminMethodViewAllBO
								.getAdminMethodSysId());
				AdminMethodViewAllFilterBO filterBO= (AdminMethodViewAllFilterBO)answerList.get(0);
				if(!"null".equalsIgnoreCase(filterBO.getAnswers())){
				List comaSeperatedAnswerList = getComaSeperatedAnswerList(answerList);
				List questionAnswerList = adminmethodViewAllAdapterManager
						.getQuestionAnswerList(comaSeperatedAnswerList);
				adminMethodViewAllBO
						.setQuestionAnswerList(groupQuestionAnswers(questionAnswerList));
				}
			}
			return adminMethodList;
		} catch (AdapterException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate adminMethodSearchList method in AdminMethodViewAllBusinessObjectBuilder",
					errorParams, ad);
		}
	}
	/**
	 * Method usd to populate the question answer BO, but sorting the asnwer
	 * corresponding to a question as coma separated values. Iterate through the
	 * list and maintain a map wherein the key value is the question id, and the
	 * value is the question answer BO. Add data to the question answer BO
	 * 
	 * @param questionAnswerList
	 * @return grouped QuestionAnswerList
	 */
	private List groupQuestionAnswers(List questionAnswerList) {

		Logger
				.logInfo("AdminmethodViewAllBusinessObjectBuilder - Grouping questions and answers");

		HashMap questionAnswerMap = new HashMap();
		for (Iterator iter = questionAnswerList.iterator(); iter.hasNext();) {
			QuestionAnswerBO questionAnswerBO = (QuestionAnswerBO) iter.next();

			if (questionAnswerMap.containsKey(new Integer(questionAnswerBO
					.getQuestionId()))) {

				QuestionAnswerBO answerBO = new QuestionAnswerBO();
				// Data contained in the map
				answerBO = (QuestionAnswerBO) questionAnswerMap
						.get(new Integer(questionAnswerBO.getQuestionId()));

				// Check for matching possibe answers, if doesnot exist, append
				// the answer.
				if (!isAttributeMatch(questionAnswerBO.getPossibleAnswer(), answerBO
						.getPossibleAnswer())) {

					answerBO.setPossibleAnswer(answerBO.getPossibleAnswer()
							+ ", " + questionAnswerBO.getPossibleAnswer());
					continue;
				}

			} else
				questionAnswerMap.put(new Integer(questionAnswerBO
						.getQuestionId()), questionAnswerBO);

		}
		List questionAnswer=new ArrayList(questionAnswerMap.values());
		Collections.sort(questionAnswer);
		return new ArrayList(questionAnswer);
		
	}

	/**
	 * Method is used to split the coma separated possible answers and make it
	 * into a list without duplicates.
	 * 
	 * @param answerList
	 * @return comaSepratedAnswerList
	 */
	private List getComaSeperatedAnswerList(List answerList) {

		Logger
				.logInfo("AdminmethodViewAllBusinessObjectBuilder - Getting coma seperated answer list");

		String possibleAnswer = "";
		List comaSeperatedAnswerList = new ArrayList();
		for (Iterator iter = answerList.iterator(); iter.hasNext();) {
			AdminMethodViewAllFilterBO element = (AdminMethodViewAllFilterBO) iter
					.next();
			if(element.getAnswers() !=null){
				String[] answer = element.getAnswers().split("\\,");
				for (int i = 0; i < answer.length; i++) {
					comaSeperatedAnswerList.add(answer[i]);
				}
			}
		}
		HashSet hashSet = new HashSet(comaSeperatedAnswerList);
		comaSeperatedAnswerList.clear();
		comaSeperatedAnswerList.addAll(hashSet);

		return comaSeperatedAnswerList;
	}

	/**
	 * method is used to populate the admin method filter BO with multiple
	 * qualifiers, PVA and datatype as coma separated values.
	 * 
	 * @param adminMethodList
	 * @return Grouped adminmethod List
	 */
	private List groupAdminMethods(List adminMethodList) {

		Logger
				.logInfo("AdminmethodViewAllBusinessObjectBuilder - Grouping admin methods, combining pva, qualifier and datatype.");

		HashMap adminMethodMap = new HashMap();
		for (Iterator iter = adminMethodList.iterator(); iter.hasNext();) {
			AdminMethodViewAllFilterBO adminMethodViewAllFilterBO = (AdminMethodViewAllFilterBO) iter
					.next();

			if (adminMethodMap.containsKey(new Integer(
					adminMethodViewAllFilterBO.getAdminMethodSysId()))) {

				AdminMethodViewAllFilterBO adminMethodViewAllFilterMapBO = new AdminMethodViewAllFilterBO();
				// Data in the Map
				adminMethodViewAllFilterMapBO = (AdminMethodViewAllFilterBO) adminMethodMap
						.get(new Integer(adminMethodViewAllFilterBO
								.getAdminMethodSysId()));

				// Checking for qualifier match
				if (!isAttributeMatch(
						adminMethodViewAllFilterBO.getQualifier(),
						adminMethodViewAllFilterMapBO.getQualifier())) {
					adminMethodViewAllFilterMapBO
							.setQualifier(adminMethodViewAllFilterMapBO
									.getQualifier()
									+ ", "
									+ adminMethodViewAllFilterBO.getQualifier());
					continue;
				}
//				 Checking for datatype match
				if (!isAttributeMatch(adminMethodViewAllFilterBO.getDataType(),
						adminMethodViewAllFilterMapBO.getDataType())) {
					adminMethodViewAllFilterMapBO
							.setDataType(adminMethodViewAllFilterMapBO
									.getDataType()
									+ ", "
									+ adminMethodViewAllFilterBO.getDataType());
					continue;
				}
//				 Checking for PVA match
				if (!isAttributeMatch(adminMethodViewAllFilterBO.getPva(),
						adminMethodViewAllFilterMapBO.getPva())) {
					adminMethodViewAllFilterMapBO
							.setPva(adminMethodViewAllFilterMapBO.getPva()
									+ ", "
									+ adminMethodViewAllFilterBO.getPva());
					continue;
				}
			}

			else
				adminMethodMap.put(new Integer(adminMethodViewAllFilterBO
						.getAdminMethodSysId()), adminMethodViewAllFilterBO);

		}
		return new ArrayList(adminMethodMap.values());

	}

	/**
	 * Method used to compare two attributes. It return true if the attributes
	 * are matching. In the case of null, it returns false.
	 * 
	 * @param attr1
	 * @param attr2
	 * @return
	 */
	private boolean isAttributeMatch(String attr1, String attr2) {
		
		if (null != attr1 && !"".equals(attr1) && null != attr2
				&& !"".equals(attr2)){
			
    		String[] array = attr2.split("\\,");

		for (int i = 0; i < array.length; i++) {
					if (array[i].trim().equalsIgnoreCase(attr1))
						return true;
				}
				return false;
			}
			return true;
	}
}
