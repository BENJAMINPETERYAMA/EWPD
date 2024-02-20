/*
 * <ReferenceDataController.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.referencedata.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.RedirectView;

import com.wellpoint.ets.bx.mapping.application.security.SecurityConstants;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.ReferenceDataResult;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.bx.mapping.web.AutoPopulateVO;
import com.wellpoint.ets.bx.mapping.web.BxResourceBundle;
import com.wellpoint.ets.bx.mapping.web.SessionMessageTray;
import com.wellpoint.ets.bx.mapping.web.WebConstants;
import com.wellpoint.ets.ebx.mapping.application.LocateFacade;
import com.wellpoint.ets.ebx.referencedata.application.ReferenceDataFacade;
import com.wellpoint.ets.ebx.referencedata.util.ReferenceDataUtil;
import com.wellpoint.ets.ebx.referencedata.vo.CategoryServiceTypeMappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.DataTypeToEB01MappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorCodeVO;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorExclusionVO;
import com.wellpoint.ets.ebx.referencedata.vo.HeaderRuleToEB03MappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.ReferenceDataValueVO;
import com.wellpoint.ets.ebx.referencedata.vo.ServiceTypeCodeToEB11VO;
import com.wellpoint.ets.ebx.referencedata.vo.ServiceTypeMappingVO;
import com.wellpoint.ets.ebx.referencedata.vo.ServiceTypeToEB11DataObject;
import com.wellpoint.ets.ebx.referencedata.vo.StandardMessageVO;
import com.wellpoint.ets.ebx.referencedata.web.ReferanceDataPopUpVO;

/**
 * @author UST-GLOBAL ReferenceDataController
 */
public class ReferenceDataController extends MultiActionController {

	/**
	 * Comment for <code>referenceDataFacade</code> Reference data facade
	 * reference.
	 */
	private ReferenceDataFacade referenceDataFacade;

	/**
	 * Comment for <code>sessionMessageTray</code> Session message tray.
	 */
	private SessionMessageTray sessionMessageTray;

	/**
	 * Comment for <code>logger</code> Logger class.
	 */
	private static Logger logger = Logger
			.getLogger(ReferenceDataController.class);

	ResourceBundle rb = ResourceBundle.getBundle(
			ReferenceDataUtil.EXCLUSION_PROPERTIES, Locale.getDefault());

	/**
	 * For auto population of the header rule.
	 */
	private LocateFacade locateRuleIdFacade;

	private static boolean isHPNRef;
	
	public LocateFacade getLocateRuleIdFacade() {
		return locateRuleIdFacade;
	}

	public void setLocateRuleIdFacade(LocateFacade locateRuleIdFacade) {
		this.locateRuleIdFacade = locateRuleIdFacade;
	}
	/**
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ModelAndView Show reference data popup.
	 */
	public ModelAndView showReferenceDataPopup(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("referenceDataPopup");
		String storePreviousModel = ESAPI.encoder().encodeForHTML(request.getParameter("key"));
		if(null!=storePreviousModel  && storePreviousModel.matches("[0-9a-zA-Z_]+")){
			storePreviousModel = storePreviousModel.toString();
		}
		setSessionScopedAttribute("previousModel", storePreviousModel, request);
		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return ModelAndView Show exclusions page.
	 */
	public ModelAndView showExclusionManagePage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("exclusionManage");
		request.setAttribute("actions", DomainConstants.EMPTY);
		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return List of matching error codes.
	 */
	public ModelAndView fetchMatchingErrorCodesForAutoComplete(
			HttpServletRequest request, HttpServletResponse response) {
		String matchValue = request.getParameter("key");
		List errorCodesList = referenceDataFacade.fetchErrorCodes(matchValue);
		Map jsonMap = new HashMap();
		if (null != errorCodesList) {
			jsonMap.put(WebConstants.ROWS, errorCodesList);
		} else {
			jsonMap.put(WebConstants.ROWS, new ArrayList());
		}
		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW,
				jsonMap);
		return modelAndView;
	}

	/**
	 * @param request
	 *            Request.
	 * @param response
	 *            Response.
	 * @return
	 */
	public ModelAndView refreshExclusionPageAction(HttpServletRequest request,
			HttpServletResponse response) {
		String errorCode = request.getParameter("errorCodeLocate");
		ErrorCodeVO errorCodeVO = referenceDataFacade
				.fetchExclusions(errorCode);
		ModelAndView modelAndView = new ModelAndView("exclusionSearchResult");
		List exclusionList = new ArrayList();
		if (null != errorCodeVO && null != errorCodeVO.getExclusionList()) {
			exclusionList = errorCodeVO.getExclusionList();
		}
		modelAndView.addObject("exclusionList", exclusionList);
		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView fetchCommentLog(HttpServletRequest request,
			HttpServletResponse response) {
		String errorCode = request.getParameter("errorCodeLocate");
		ErrorCodeVO errorCodeVO = referenceDataFacade
				.fetchAuditTrail(errorCode);
		ModelAndView modelAndView = new ModelAndView("commentsPopUp");
		String auditTrail = DomainConstants.EMPTY;
		if (null != errorCodeVO && null != errorCodeVO.getAuditTrail()) {
			auditTrail = errorCodeVO.getAuditTrail();
		}
		logger.info(ESAPI.encoder().encodeForHTML("The audit trail fetched from the DB"
				+ ReferenceDataUtil.trimAndNullToEmptyString(auditTrail)));
		modelAndView.addObject("commentsLogTrail", auditTrail);
		return modelAndView;
	}

	/**
	 * @param request
	 *            HttpServletRequest.
	 * @param response
	 *            HttpServletResponse.
	 * @return ModelAndView.
	 */
	public ModelAndView saveExclusionAction(HttpServletRequest request,
			HttpServletResponse response) {
		List successMessages = new ArrayList();
		List errorMessages = new ArrayList();
		List warnMessages = new ArrayList();

		String exceptionId = request.getParameter("exclusionIDForSave");
		String errorCode = request.getParameter("primaryErrorCode");

		String primayCategory = request.getParameter("criteriaSelectOne");
		String primaryVals = request.getParameter("valuesTextOne");

		String secondaryCategory = request.getParameter("criteriaSelectTwo");
		String secondVals = request.getParameter("valuesTextTwo");

		String comments = request.getParameter("userCommentsExclusion");

		String userId = request.getAttribute(SecurityConstants.USER_NAME)
		.toString();
		int exception = 0;
		if (!DomainConstants.EMPTY.equals(ReferenceDataUtil
				.trimAndNullToEmptyString(exceptionId))) {
			if (BxUtil.isInteger(exceptionId)) {
				exception = Integer.parseInt(exceptionId);
			}
		}
		logger.info("The save action for exclusion and exclusion id is :"
				+ exception);

		ErrorExclusionVO errorExclusionVO = new ErrorExclusionVO();
		errorExclusionVO.setErrorCode(errorCode);
		errorExclusionVO.setExclusionId(exception);
		errorExclusionVO.setErrorCode(errorCode);
		errorExclusionVO.setPrimaryExclusion(primayCategory);
		errorExclusionVO.setSecondaryExclusion(secondaryCategory);
		errorExclusionVO.setPrimaryValues(primaryVals);
		errorExclusionVO.setSecondaryValues(secondVals);
		errorExclusionVO.setCreatedUser(userId);
		errorExclusionVO.setLastUpdatedUser(userId);
		boolean duplicateNotPresent = removeDuplicatesAndSetExclusionCount(errorExclusionVO);
		if (!duplicateNotPresent) {
			warnMessages.add(rb.getString("duplicates.found.exclusions"));
		}
		int totalCountExceedingCurrent = referenceDataFacade
		.getTotalCountForExclusionForValidation(errorExclusionVO);
		logger
		.info("The total count of all exclusions except the current exclusion :"
				+ totalCountExceedingCurrent);
		if (totalCountExceedingCurrent + errorExclusionVO.getExclusionCount() >= 20000) {
			errorMessages.add(rb.getString("exclusion.count.exceeded.20000"));
		} else {
			if (totalCountExceedingCurrent
					+ errorExclusionVO.getExclusionCount() >= 19000) {
				warnMessages.add(rb.getString("exclusion.count.exceeded.19000"));
			}
			String auditTrail = null;
			ErrorCodeVO errorCodeVOForAuditTrail = referenceDataFacade
			.fetchAuditTrail(errorCode);
			if (null != errorCodeVOForAuditTrail) {
				auditTrail = errorCodeVOForAuditTrail.getAuditTrail();
			}
			String modifiedComments = modifyComments(auditTrail, comments,
					userId, "UPDATE", exception);
			ErrorCodeVO errorCodeVO = new ErrorCodeVO();
			errorCodeVO.setErrorCode(errorCode);
			errorCodeVO.setAuditTrail(modifiedComments);
			errorCodeVO.setCreatedUser(userId);
			errorCodeVO.setLastUpdatedUser(userId);
			// Setting the ErrorCodeVO with the details.
			errorCodeVO.getExclusionList().add(errorExclusionVO);

			ReferenceDataResult referenceDataResult = referenceDataFacade.saveExclusion(errorCodeVO);
			if (null != referenceDataResult && null != referenceDataResult.getWarningMsgsList() 
					&& !referenceDataResult.getWarningMsgsList().isEmpty()) {
				warnMessages.addAll(referenceDataResult.getWarningMsgsList());
			}


			// Get the Details from Saved list.
			List exclusionListSaved = errorCodeVO.getExclusionList();
			if (null != exclusionListSaved && exclusionListSaved.size() > 0) {
				errorExclusionVO = (ErrorExclusionVO) exclusionListSaved.get(0);
			}
			successMessages.add(rb.getString("exclusion.save.success"));
		}
		ModelAndView modelAndView = new ModelAndView("exclusionManage");
		modelAndView.addObject(WebConstants.INFO_MESSAGES, successMessages);
		modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		modelAndView.addObject(WebConstants.WARNING_MESSAGES, warnMessages);
		request.setAttribute("actions", "Actions");
		request.setAttribute("errorCode", errorExclusionVO.getErrorCode());
		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView deleteAllExclusions(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Inside delete all exclusions action");
		String errorCode = ESAPI.encoder().encodeForHTML(request.getParameter("errorCodeLocate"));
		if(null!=errorCode  && errorCode.matches("[0-9a-zA-Z_]+")){
			errorCode = errorCode;
		}
		ErrorCodeVO errorCodeVO = new ErrorCodeVO();
		errorCodeVO.setErrorCode(errorCode);
		errorCodeVO.setAuditTrail(DomainConstants.EMPTY);
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		errorCodeVO.setLastUpdatedUser(userId);

		List successMessages = new ArrayList();
		List errorMessages = new ArrayList();
		List warnMessages = new ArrayList();

		boolean deleteAllFlag = referenceDataFacade
				.deleteAllExclusions(errorCodeVO);
		if (deleteAllFlag) {
			if (!DomainConstants.EMPTY.equals(ReferenceDataUtil
					.trimAndNullToEmptyString(errorCodeVO
							.getOperationMessages()))) {
				warnMessages.add(errorCodeVO.getOperationMessages());
			} else {
				successMessages.add(rb.getString("exclusions.delete.success"));
			}
		} else {
			successMessages.add(rb.getString("exclusion.delete.failure"));
		}
		ModelAndView modelAndView = new ModelAndView("exclusionManage");
		modelAndView.addObject(WebConstants.INFO_MESSAGES, successMessages);
		modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		modelAndView.addObject(WebConstants.WARNING_MESSAGES, warnMessages);
		request.setAttribute("actions", "Actions");
		request.setAttribute("errorCode", errorCodeVO.getErrorCode());
		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return Delete exclusion.
	 */
	public ModelAndView deleteExclusion(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Inside delete exclusion action");
		String exceptionId = request.getParameter("exclusionIDForSave");
		String errorCode = ESAPI.encoder().encodeForHTML(request.getParameter("primaryErrorCode"));
		if(null!=errorCode && errorCode.matches("[0-9a-zA-Z_]+")){
			errorCode = errorCode;
		}

		String comments = request.getParameter("userCommentsExclusion");
		String auditTrail = null;

		ErrorCodeVO errorCodeVOForAuditTrail = referenceDataFacade
				.fetchAuditTrail(errorCode);
		if (null != errorCodeVOForAuditTrail) {
			auditTrail = errorCodeVOForAuditTrail.getAuditTrail();
		}
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		int exception = 0;
		if (!DomainConstants.EMPTY.equals(ReferenceDataUtil
				.trimAndNullToEmptyString(exceptionId))) {
			if (BxUtil.isInteger(exceptionId)) {
				exception = Integer.parseInt(exceptionId);
			}
		}
		List successMessages = new ArrayList();
		List errorMessages = new ArrayList();

		String modifiedComments = modifyComments(auditTrail, comments, userId,
				"DELETE", exception);
		ErrorCodeVO errorCodeVO = new ErrorCodeVO();
		errorCodeVO.setErrorCode(errorCode);
		errorCodeVO.setAuditTrail(modifiedComments);
		// Setting the ErrorCodeVO with the details.
		ErrorExclusionVO errorExclusionVO = new ErrorExclusionVO();
		errorExclusionVO.setErrorCode(errorCode);
		errorExclusionVO.setExclusionId(exception);
		errorExclusionVO.setErrorCode(errorCode);
		errorCodeVO.getExclusionList().add(errorExclusionVO);

		boolean deleteStatus = referenceDataFacade.deleteExclusion(errorCodeVO);
		if (deleteStatus) {
			successMessages.add(rb.getString("exclusion.delete.success"));
		} else {
			successMessages.add(rb.getString("exclusion.delete.failure"));
		}

		ModelAndView modelAndView = new ModelAndView("exclusionManage");
		modelAndView.addObject(WebConstants.INFO_MESSAGES, successMessages);
		modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessages);
		request.setAttribute("actions", "Actions");
		request.setAttribute("errorCode", errorExclusionVO.getErrorCode());
		return modelAndView;

	}

	/**
	 * @param comments
	 *            comments
	 * @param userId
	 *            userId
	 * @param action
	 *            action
	 * @param exceptionId
	 *            exceptionId
	 * @return Comments.
	 */
	private String modifyComments(String auditTrail, String comments,
			String userId, String action, int exceptionId) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("\n\n");
		if ("UPDATE".equalsIgnoreCase(action)) {
			if (0 == exceptionId) {
				stringBuffer.append("**Added by ");
			} else {
				stringBuffer.append("**Modified by ");
			}
		} else if ("DELETE".equalsIgnoreCase(action)) {
			stringBuffer.append("**Removed by ");
		}
		stringBuffer.append(userId).append(" on ");
		stringBuffer.append(ReferenceDataUtil.convertDateToString(new Date()));
		stringBuffer.append(" **");
		stringBuffer.append("\n");
		stringBuffer.append(ReferenceDataUtil
				.trimAndNullToEmptyString(comments));
		stringBuffer.append("\n\n");
		stringBuffer.append(ReferenceDataUtil
				.trimAndNullToEmptyString(auditTrail));
		return stringBuffer.toString();
	}

	/**
	 * @param errorExclusionVO
	 *            errorExclusionVO
	 * @return Boolean value. Remove duplicates.
	 */
	private boolean removeDuplicatesAndSetExclusionCount(
			ErrorExclusionVO errorExclusionVO) {
		String primaryVals = errorExclusionVO.getPrimaryValues();
		boolean noDuplicateExistsPrimary = true;
		boolean noDuplicateExistsSecondary = true;
		String secondaryVals = errorExclusionVO.getSecondaryValues();
		int exclusionCount = 0;
		LinkedHashSet linkedHashSetPrimary = new LinkedHashSet();
		LinkedHashSet linkedHashSetSecondary = new LinkedHashSet();
		noDuplicateExistsPrimary = getUniqueValuesFromString(
				linkedHashSetPrimary, primaryVals);
		noDuplicateExistsSecondary = getUniqueValuesFromString(
				linkedHashSetSecondary, secondaryVals);
		if (linkedHashSetSecondary.size() > 0) {
			exclusionCount = linkedHashSetSecondary.size();
		} else {
			exclusionCount = linkedHashSetPrimary.size();
		}
		errorExclusionVO.setExclusionCount(exclusionCount);
		errorExclusionVO
				.setPrimaryValues(generateExclusionValuesModified(linkedHashSetPrimary));
		errorExclusionVO
				.setSecondaryValues(generateExclusionValuesModified(linkedHashSetSecondary));
		return noDuplicateExistsPrimary && noDuplicateExistsSecondary;
	}

	/**
	 * @param linkedHashSet
	 * @param valueComaSeparated
	 * @return Get the unique values from coma separated string.
	 */
	private boolean getUniqueValuesFromString(LinkedHashSet linkedHashSet,
			String valueComaSeparated) {
		boolean noDuplicateExists = true;
		String[] exclusionArray = null;
		if (!DomainConstants.EMPTY.equals(ReferenceDataUtil
				.trimAndNullToEmptyString(valueComaSeparated))) {
			exclusionArray = valueComaSeparated.split(",");
			if (null != exclusionArray) {
				for (int i = 0; i < exclusionArray.length; i++) {
					// Set to false if there is duplicate.
					if (noDuplicateExists) {
						noDuplicateExists = linkedHashSet.add(ReferenceDataUtil
								.trimAndNullToEmptyString(exclusionArray[i]));
					} else {
						linkedHashSet.add(ReferenceDataUtil
								.trimAndNullToEmptyString(exclusionArray[i]));
					}

				}
			}
		}
		return noDuplicateExists;
	}

	/**
	 * @param linkedHashSet
	 * @return For generating exclusion values coma separated after
	 *         consolidating the unique values.
	 */
	private String generateExclusionValuesModified(LinkedHashSet linkedHashSet) {
		StringBuffer valsModified = new StringBuffer(DomainConstants.EMPTY);
		if (null != linkedHashSet && linkedHashSet.size() > 0) {
			Iterator iterator = linkedHashSet.iterator();
			if (null != iterator) {
				while (iterator.hasNext()) {
					String currentVal = (String) iterator.next();
					if (!DomainConstants.EMPTY
							.equalsIgnoreCase(ReferenceDataUtil
									.trimAndNullToEmptyString(currentVal))) {
						if (valsModified.length() == 0) {
							valsModified.append(currentVal);
						} else {
							valsModified.append(", " + currentVal);
						}
					}
				}

			}
		}
		return valsModified.toString();
	}

	/**
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception
	 *             Close button action
	 */
	public ModelAndView closeAction(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String previousModel = (String) getSessionScopedAttribute(
				"previousModel", request);
		logger.info(ESAPI.encoder().encodeForHTML("Inside close action" + "Previous model "
				+ ReferenceDataUtil.trimAndNullToEmptyString(previousModel)));
		String contextPath = request.getContextPath();
		RedirectView redirectView = new RedirectView(contextPath
				+ previousModel);
		redirectView.setExposeModelAttributes(false);
		ModelAndView modelAndView = new ModelAndView(redirectView);
		return modelAndView;
	}

	/**
	 * @param attributeName
	 * @param obj
	 * @param httpServletRequest
	 *            Set Attribue to Session.
	 */
	public void setSessionScopedAttribute(String attributeName, Object obj,
			HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		httpSession.setAttribute(attributeName, obj);
	}

	/**
	 * @param attributeName
	 * @param httpServletRequest
	 * @return The attribute associated in the session.
	 */
	public Object getSessionScopedAttribute(String attributeName,
			HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		return httpSession.getAttribute(attributeName);
	}

	/**
	 * @param attributeName
	 * @param httpServletRequest
	 *            To remove the session attribute.
	 */
	public void removeSessionScopedAttribute(String attributeName,
			HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		httpSession.removeAttribute(attributeName);
	}

	/**
	 * @return referenceDataFacade.
	 */
	public ReferenceDataFacade getReferenceDataFacade() {
		return referenceDataFacade;
	}

	/**
	 * @param referenceDataFacade
	 *            referenceDataFacade.
	 */
	public void setReferenceDataFacade(ReferenceDataFacade referenceDataFacade) {
		this.referenceDataFacade = referenceDataFacade;
	}

	/**
	 * @return sessionMessageTray.
	 */
	public SessionMessageTray getSessionMessageTray() {
		return sessionMessageTray;
	}

	/**
	 * @param sessionMessageTray
	 *            sessionMessageTray.
	 */
	public void setSessionMessageTray(SessionMessageTray sessionMessageTray) {
		this.sessionMessageTray = sessionMessageTray;
	}

	/**
	 * @param request
	 * @param response
	 * @return ModelAndView Show category code mapping page.
	 */
	public ModelAndView showCategoryCodeMapping(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("categorycodemapping");
		modelAndView.addObject("visibility", "none");
		request.setAttribute("actions", DomainConstants.EMPTY);
		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return ModelAndView fetch data when we click on edit mapping
	 */
	public ModelAndView editMapping(HttpServletRequest request,
			HttpServletResponse response) {
		CategoryServiceTypeMappingVO categoryServiceTypeMappingVO = new CategoryServiceTypeMappingVO();

		if (null != request.getParameter("categoryCodeedit")) {
			categoryServiceTypeMappingVO.setCategoryCode(request.getParameter(
					"categoryCodeedit").trim());
		}
		if (null != request.getParameter("systemedit")) {
			categoryServiceTypeMappingVO.setSystem(request.getParameter(
					"systemedit").trim());
		}

		List categoryCodeList = referenceDataFacade
				.fetchCategoryCodeMappingResult(categoryServiceTypeMappingVO);
		Mapping mapping = null;
		categoryServiceTypeMappingVO = (CategoryServiceTypeMappingVO) categoryCodeList
				.get(0);
		// iterate
		ModelAndView modelAndView = new ModelAndView("editcategorymapping");
		mapping = categoryServiceTypeMappingVO.getMapping();

		modelAndView.addObject("visibility", "visible");

		modelAndView.addObject("categorysave", categoryServiceTypeMappingVO
				.getCategoryCode());
		modelAndView.addObject("systemsave", categoryServiceTypeMappingVO
				.getSystem());
		if (null != request.getParameter("serviceTypesave")) {
			modelAndView.addObject("serviceTypesave", request.getParameter(
					"serviceTypesave").trim());

		}
		if (null != request.getParameter("categoryDescription")) {
			modelAndView.addObject("categoryDescription", request.getParameter(
					"categoryDescription").trim());

		}

		modelAndView.addObject("mapping", mapping);
		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return ModelAndView Show category code search result page
	 */
	public ModelAndView showCategoryCodeMappingResult(
			HttpServletRequest request, HttpServletResponse response) {

		CategoryServiceTypeMappingVO categoryServiceTypeMappingVO = new CategoryServiceTypeMappingVO();

		if (null != request.getParameter("categoryCode")) {
			categoryServiceTypeMappingVO.setCategoryCode(request.getParameter(
					"categoryCode").trim());
		}
		if (null != request.getParameter("system")) {
			categoryServiceTypeMappingVO.setSystem(request.getParameter(
					"system").trim().toUpperCase());
		}
		if (null != request.getParameter("EB03")) {
			categoryServiceTypeMappingVO.setServiceType(request.getParameter(
					"EB03").trim());
		}
		if (null != request.getParameter("categorydesc")) {
			categoryServiceTypeMappingVO.setDescription(request.getParameter(
					"categorydesc").trim());
		}
		ModelAndView modelAndView = new ModelAndView(
				"categorymappingsearchresult");
		List categoryCodeList = new ArrayList();
		if (null != categoryServiceTypeMappingVO.getSystem()) {
			if (!"".equals(categoryServiceTypeMappingVO.getSystem().trim())) {
				if (!"LG".equals(categoryServiceTypeMappingVO.getSystem()
						.trim().toUpperCase())
						&& !"ISG".equals(categoryServiceTypeMappingVO
								.getSystem().trim().toUpperCase())) {
					modelAndView.addObject("categoryCodeMappingList",
							categoryCodeList);
					return modelAndView;
				}
			}

		}

		categoryCodeList = referenceDataFacade
				.fetchCategoryCodeMappingResult(categoryServiceTypeMappingVO);

		// iterate
		modelAndView.addObject("fromaction", "SELECT");
		request.setAttribute("fromaction", "SELECT");

		modelAndView.addObject("categoryCodeMappingList", categoryCodeList);
		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return ModelAndView Get all the category codes with and without mappings
	 *         in both LG and ISG - T52
	 */
	public void getAllCategoryCodesMapping(HttpServletRequest request,
			HttpServletResponse response) {

		referenceDataFacade.fetchAllCategoryCodesMapping();

	}

	/**
	 * @param request
	 * @param response
	 * @return ModelAndView delete category code mapping
	 */
	public ModelAndView deleteCategoryCodeMapping(HttpServletRequest request,
			HttpServletResponse response) {
		CategoryServiceTypeMappingVO categoryServiceTypeMappingVO = new CategoryServiceTypeMappingVO();
		CategoryServiceTypeMappingVO categoryServiceTypeMappingVOforselect = new CategoryServiceTypeMappingVO();
		if (null != request.getParameter("categoryCode")) {
			categoryServiceTypeMappingVOforselect.setCategoryCode(request
					.getParameter("categoryCode").trim());
		}
		if (null != request.getParameter("system")) {
			categoryServiceTypeMappingVOforselect.setSystem(request
					.getParameter("system").trim());
		}
		if (null != request.getParameter("EB03")) {
			categoryServiceTypeMappingVOforselect.setServiceType(request
					.getParameter("EB03").trim());
		}
		if (null != request.getParameter("categorydesc")) {
			categoryServiceTypeMappingVOforselect.setDescription(request
					.getParameter("categorydesc").trim());

		}

		if (null != request.getParameter("categorydelete")) {
			categoryServiceTypeMappingVO.setCategoryCode(request.getParameter(
					"categorydelete").trim());
		}
		if (null != request.getParameter("systemdelete")) {
			categoryServiceTypeMappingVO.setSystem(request.getParameter(
					"systemdelete").trim());
		}

		// if(null!=request.getParameter("EB03")){
		// categoryServiceTypeMappingVO.setServiceType(request.getParameter("EB03").trim());
		// }
		// if(null!=request.getParameter("categoryDesc")){
		// categoryServiceTypeMappingVO.setDescription(request.getParameter("categoryDesc").trim());
		// }

		List categoryCodeList = referenceDataFacade.deleteCategoryCodeMapping(
				categoryServiceTypeMappingVO,
				categoryServiceTypeMappingVOforselect);

		// iterate

		ModelAndView modelAndView = new ModelAndView("categorycodemapping");

		modelAndView.addObject("categorydelete", categoryServiceTypeMappingVO
				.getCategoryCode());
		modelAndView.addObject("systemdelete", categoryServiceTypeMappingVO
				.getSystem());
		modelAndView.addObject("categoryCode",
				categoryServiceTypeMappingVOforselect.getCategoryCode());
		modelAndView.addObject("system", categoryServiceTypeMappingVOforselect
				.getSystem());
		modelAndView.addObject("EB03", categoryServiceTypeMappingVOforselect
				.getServiceType());
		modelAndView.addObject("categorydesc",
				categoryServiceTypeMappingVOforselect.getDescription());
		modelAndView.addObject("fromaction", "DELETE");
		request.setAttribute("fromaction", "DELETE");
		String message = "";
		if (null != categoryServiceTypeMappingVO.getCategoryCode()) {
			message = "Deleted the mappings for category code :"
					+ categoryServiceTypeMappingVO.getCategoryCode();
		}

		modelAndView.addObject(WebConstants.INFO_MESSAGES, message);
		modelAndView.addObject("categoryCodeMappingList", categoryCodeList);
		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return ModelAndView update category code mapping
	 */
	public ModelAndView updateCategoryCodeMapping(HttpServletRequest request,
			HttpServletResponse response) {
		CategoryServiceTypeMappingVO categoryServiceTypeMappingVO = new CategoryServiceTypeMappingVO();

		if (null != request.getParameter("categorysave")) {
			categoryServiceTypeMappingVO.setCategoryCode(request.getParameter(
					"categorysave").trim());
		}
		if (null != request.getParameter("systemsave")) {
			categoryServiceTypeMappingVO.setSystem(request.getParameter(
					"systemsave").trim());
		}
		CategoryServiceTypeMappingVO categoryServiceTypeMappingVOforselect = new CategoryServiceTypeMappingVO();
		if (null != request.getParameter("categoryCode")) {
			categoryServiceTypeMappingVOforselect.setCategoryCode(request
					.getParameter("categoryCode").trim());
		}
		if (null != request.getParameter("system")) {
			categoryServiceTypeMappingVOforselect.setSystem(request
					.getParameter("system").trim());
		}
		if (null != request.getParameter("EB03")) {
			categoryServiceTypeMappingVOforselect.setServiceType(request
					.getParameter("EB03").trim());
		}
		if (null != request.getParameter("categorydesc")) {
			categoryServiceTypeMappingVOforselect.setDescription(request
					.getParameter("categorydesc").trim());

		}
		if (null != request.getParameter("serviceTypesave")) {
			categoryServiceTypeMappingVO.setServiceType(request.getParameter(
					"serviceTypesave").trim());
		}
		// iterate
		// fetch EB03
		ModelAndView modelAndView = new ModelAndView("categorycodemapping");
		List eB03List = createMappingObject(request);
		String message = "";
		Mapping mappingObj = new Mapping();
		HippaSegment eb03 = new HippaSegment();
		Map possibleEB03Values = referenceDataFacade.getEB03Values("EB03");
		List possiblevalues = new ArrayList(possibleEB03Values.keySet());
		eb03.setName("EB03");
		eb03.setHippaCodePossibleValues(possiblevalues);
		eb03.setHippaCodeSelectedValues(eB03List);
		mappingObj.setEb03(eb03);
		if (null != eB03List) {
			if (eB03List.size() <= 50) {

				Iterator eB03Itr = eB03List.iterator();
				int i = 0;
				while (eB03Itr.hasNext()) {
					HippaCodeValue hippaCodeValue = (HippaCodeValue) eB03Itr
							.next();
					String eb03value = hippaCodeValue.getValue();
					if (!possiblevalues.contains(eb03value)) {
						if (i == 0) {
							message = "Invalid EB03 values : ";
							i++;
						}
						message = message + " " + eb03value;
					}
				}
			} else {
				message = "Please enter EB03 less than or equal to 50";
			}
		}

		// MappingResult mappingResult = new MappingResult();
		// mappingResult.setMapping(mappingObj);
		// List hippaSegmentValidationResultList =
		// mappingObj.validateMappings();
		// mappingResult = new MappingResult(hippaSegmentValidationResultList);
		// List warningMessages = new ArrayList();
		if ("".equals(message)) {
			List test = referenceDataFacade.updateCategoryCodeMapping(
					categoryServiceTypeMappingVO,
					categoryServiceTypeMappingVOforselect, eB03List);
			// modelAndView.addObject("categoryCodeMappingList", test);
			List infoMessages = new ArrayList();

			infoMessages.add(BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_SAVE_SUCCESS, null));
			modelAndView.addObject("mapping", mappingObj);
			modelAndView.addObject(WebConstants.INFO_MESSAGES,
					"Category Code � Service Type mapping saved successfully");
			modelAndView.addObject("categoryCodeMappingList", test);
			modelAndView.addObject("categorysave", categoryServiceTypeMappingVO
					.getCategoryCode());
			modelAndView.addObject("systemsave", categoryServiceTypeMappingVO
					.getSystem());
			modelAndView.addObject("categoryCode",
					categoryServiceTypeMappingVOforselect.getCategoryCode());
			modelAndView.addObject("system",
					categoryServiceTypeMappingVOforselect.getSystem());
			modelAndView.addObject("EB03",
					categoryServiceTypeMappingVOforselect.getServiceType());
			modelAndView.addObject("categorydesc",
					categoryServiceTypeMappingVOforselect.getDescription());
			modelAndView.addObject("serviceTypesave",
					categoryServiceTypeMappingVO.getServiceType());
			modelAndView.addObject("fromaction", "UPDATE");
			request.setAttribute("fromaction", "UPDATE");
			if (null != request.getParameter("categoryDescription")) {
				modelAndView.addObject("categoryDescription", request
						.getParameter("categoryDescription").trim());

			}
			// List
			// fetchlist=referenceDataFacade.fetchCategoryCodeMappingResult(categoryServiceTypeMappingVO);
			// modelAndView.addObject("categoryCodeMappingList", fetchlist);

		} else {
			List categoryCodeList = referenceDataFacade
					.fetchCategoryCodeMappingResult(categoryServiceTypeMappingVOforselect);
			modelAndView.addObject("categoryCodeMappingList", categoryCodeList);
			modelAndView.addObject("categorysave", categoryServiceTypeMappingVO
					.getCategoryCode());
			modelAndView.addObject("systemsave", categoryServiceTypeMappingVO
					.getSystem());
			modelAndView.addObject("categoryCode",
					categoryServiceTypeMappingVOforselect.getCategoryCode());
			modelAndView.addObject("system",
					categoryServiceTypeMappingVOforselect.getSystem());
			modelAndView.addObject("EB03",
					categoryServiceTypeMappingVOforselect.getServiceType());
			modelAndView.addObject("categorydesc",
					categoryServiceTypeMappingVOforselect.getDescription());
			modelAndView.addObject("mapping", mappingObj);
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, message);
			modelAndView.addObject("serviceTypesave",
					categoryServiceTypeMappingVO.getServiceType());
			if (null != request.getParameter("categoryDescription")) {
				modelAndView.addObject("categoryDescription", request
						.getParameter("categoryDescription").trim());

			}
			modelAndView.addObject("fromaction", "UPDATE");
			request.setAttribute("fromaction", "UPDATE");

		}

		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return ModelAndView create category code mapping
	 */
	public ModelAndView createCategoryCodeMapping(HttpServletRequest request,
			HttpServletResponse response) {
		CategoryServiceTypeMappingVO categoryServiceTypeMappingVO = new CategoryServiceTypeMappingVO();

		if (null != request.getParameter("categorysave")) {
			categoryServiceTypeMappingVO.setCategoryCode(request.getParameter(
					"categorysave").trim());
		}
		if (null != request.getParameter("systemsave")) {
			categoryServiceTypeMappingVO.setSystem(request.getParameter(
					"systemsave").trim());
		}
		CategoryServiceTypeMappingVO categoryServiceTypeMappingVOforselect = new CategoryServiceTypeMappingVO();
		if (null != request.getParameter("categoryCode")) {
			categoryServiceTypeMappingVOforselect.setCategoryCode(request
					.getParameter("categoryCode").trim());
		}
		if (null != request.getParameter("system")) {
			categoryServiceTypeMappingVOforselect.setSystem(request
					.getParameter("system").trim());
		}
		if (null != request.getParameter("EB03")) {
			categoryServiceTypeMappingVOforselect.setServiceType(request
					.getParameter("EB03").trim());
		}
		if (null != request.getParameter("categorydesc")) {
			categoryServiceTypeMappingVOforselect.setDescription(request
					.getParameter("categorydesc").trim());

		}
		if (null != request.getParameter("serviceTypesave")) {
			categoryServiceTypeMappingVO.setServiceType(request.getParameter(
					"serviceTypesave").trim());
		}
		// iterate
		// fetch EB03
		ModelAndView modelAndView = new ModelAndView("categorycodemapping");
		List eB03List = createMappingObject(request);

		String message = "";
		Mapping mappingObj = new Mapping();
		HippaSegment eb03 = new HippaSegment();
		Map possibleEB03Values = referenceDataFacade.getEB03Values("EB03");
		List possiblevalues = new ArrayList(possibleEB03Values.keySet());
		eb03.setName("EB03");
		eb03.setHippaCodePossibleValues(possiblevalues);
		eb03.setHippaCodeSelectedValues(eB03List);
		mappingObj.setEb03(eb03);

		if (null != eB03List) {
			if (eB03List.size() <= 50) {

				Iterator eB03Itr = eB03List.iterator();
				int i = 0;
				while (eB03Itr.hasNext()) {
					HippaCodeValue hippaCodeValue = (HippaCodeValue) eB03Itr
							.next();
					String eb03value = hippaCodeValue.getValue();
					if (!possiblevalues.contains(eb03value)) {
						if (i == 0) {
							message = "Invalid EB03 values : ";
							i++;
						}
						message = message + " " + eb03value;
					}
				}
			} else {
				message = "Please enter EB03 less than or equal to 50";
			}
		}

		// MappingResult mappingResult = new MappingResult();
		// mappingResult.setMapping(mappingObj);
		// List hippaSegmentValidationResultList =
		// mappingObj.validateMappings();
		// mappingResult = new MappingResult(hippaSegmentValidationResultList);
		// List warningMessages = new ArrayList();
		if ("".equals(message)) {
			List test = referenceDataFacade.createCategoryCodeMapping(
					categoryServiceTypeMappingVO,
					categoryServiceTypeMappingVOforselect, eB03List);
			// modelAndView.addObject("categoryCodeMappingList", test);
			List infoMessages = new ArrayList();

			infoMessages.add(BxResourceBundle.getResourceBundle(
					WebConstants.MAPPING_SAVE_SUCCESS, null));
			modelAndView.addObject("mapping", mappingObj);
			modelAndView.addObject(WebConstants.INFO_MESSAGES,
					"Category Code � Service Type mapping saved successfully");
			modelAndView.addObject("categoryCodeMappingList", test);
			modelAndView.addObject("categorysave", categoryServiceTypeMappingVO
					.getCategoryCode());
			modelAndView.addObject("systemsave", categoryServiceTypeMappingVO
					.getSystem());
			modelAndView.addObject("categoryCode",
					categoryServiceTypeMappingVOforselect.getCategoryCode());
			modelAndView.addObject("system",
					categoryServiceTypeMappingVOforselect.getSystem());
			modelAndView.addObject("EB03",
					categoryServiceTypeMappingVOforselect.getServiceType());
			modelAndView.addObject("categorydesc",
					categoryServiceTypeMappingVOforselect.getDescription());
			modelAndView.addObject("fromaction", "CREATE");
			request.setAttribute("fromaction", "CREATE");
			modelAndView.addObject("serviceTypesave", "NotEmpty");
			if (null != request.getParameter("categoryDescription")) {
				modelAndView.addObject("categoryDescription", request
						.getParameter("categoryDescription").trim());

			}
			// List
			// fetchlist=referenceDataFacade.fetchCategoryCodeMappingResult(categoryServiceTypeMappingVO);
			// modelAndView.addObject("categoryCodeMappingList", fetchlist);

		} else {
			modelAndView.addObject("mapping", mappingObj);
			List categoryCodeList = referenceDataFacade
					.fetchCategoryCodeMappingResult(categoryServiceTypeMappingVOforselect);
			modelAndView.addObject("categoryCodeMappingList", categoryCodeList);
			modelAndView.addObject("categorysave", categoryServiceTypeMappingVO
					.getCategoryCode());
			modelAndView.addObject("systemsave", categoryServiceTypeMappingVO
					.getSystem());
			modelAndView.addObject("categoryCode",
					categoryServiceTypeMappingVOforselect.getCategoryCode());
			modelAndView.addObject("system",
					categoryServiceTypeMappingVOforselect.getSystem());
			modelAndView.addObject("EB03",
					categoryServiceTypeMappingVOforselect.getServiceType());
			modelAndView.addObject("categorydesc",
					categoryServiceTypeMappingVOforselect.getDescription());
			modelAndView.addObject("fromaction", "CREATE");
			request.setAttribute("fromaction", "CREATE");
			modelAndView.addObject("serviceTypesave",
					categoryServiceTypeMappingVO.getServiceType());
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, message);
			if (null != request.getParameter("categoryDescription")) {
				modelAndView.addObject("categoryDescription", request
						.getParameter("categoryDescription").trim());

			}

		}

		return modelAndView;
	}

	/**
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ModelAndView Show reference data popup.
	 */
	public ModelAndView showDeletePopup(HttpServletRequest request,
			HttpServletResponse response) {

		// fetch category code variable relation data
		CategoryServiceTypeMappingVO categoryServiceTypeMappingVO = new CategoryServiceTypeMappingVO();
		ModelAndView modelAndView = new ModelAndView("deletecategorypopup");

		if (null != request.getParameter("categorydelete")) {
			categoryServiceTypeMappingVO.setCategoryCode(request.getParameter(
					"categorydelete").trim());
			modelAndView.addObject("categorydelete", request.getParameter(
					"categorydelete").trim());
		}
		if (null != request.getParameter("systemdelete")) {
			categoryServiceTypeMappingVO.setSystem(request.getParameter(
					"systemdelete").trim());
			modelAndView.addObject("systemdelete", request.getParameter(
					"systemdelete").trim());
		}

		if (null != request.getParameter("categoryCode")) {
			modelAndView.addObject("categoryCodefetch", request.getParameter(
					"categoryCode").trim());

		}
		if (null != request.getParameter("system")) {
			modelAndView.addObject("systemfetch", request
					.getParameter("system").trim());

		}
		if (null != request.getParameter("EB03")) {
			modelAndView.addObject("EB03fetch", request.getParameter("EB03")
					.trim());

		}
		if (null != request.getParameter("categorydesc")) {
			modelAndView.addObject("categoryDescfetch", request.getParameter(
					"categorydesc").trim());

		}
		// if(null!=request.getParameter("EB03")){
		// categoryServiceTypeMappingVO.setServiceType(request.getParameter("EB03").trim());
		// }
		// if(null!=request.getParameter("categoryDesc")){
		// categoryServiceTypeMappingVO.setDescription(request.getParameter("categoryDesc").trim());
		// }

		List categoryCodeList = referenceDataFacade
				.fetchCategoryCodeVariableMapping(categoryServiceTypeMappingVO);

		modelAndView.addObject("categoryCodeMappingList", categoryCodeList);
		// setSessionScopedAttribute("previousModel", storePreviousModel,
		// request);
		return modelAndView;
	}

	/**
	 * @param request
	 *            HttpServletRequest
	 * @return List EB03 list from web page
	 */

	private List createMappingObject(HttpServletRequest request) {

		// Mapping mapping = new Mapping();
		// User user = new User();
		// Rule rule = new Rule();
		// rule.setHeaderRuleId(request.getParameter("selectedruleId"));
		// rule.setRuleDesc(request.getParameter("ruleDesc"));

		// mapping.setRule(rule);
		// mapping.setEb03(new HippaSegment());
		List uniqueEB03List = new ArrayList();
		List hippaCodeEB03List = new ArrayList();
		HippaCodeValue hippaCodeValue;
		// set eb03 values
		if (null != request.getParameterValues(WebConstants.EB03_VAL)) {
			int sizeOfEB03 = request.getParameterValues(WebConstants.EB03_VAL).length;
			String[] eb03 = new String[sizeOfEB03];
			String[] eb03Desc = request.getParameterValues("EB03Desc");
			if (eb03Desc == null) {
				eb03Desc = new String[sizeOfEB03];
			}
			for (int i = 0; i < sizeOfEB03; i++) {

				if (!request.getParameterValues(WebConstants.EB03_VAL)[i]
						.trim().equals("")) {

					eb03[i] = request.getParameterValues(WebConstants.EB03_VAL)[i]
							.trim();
					if (!uniqueEB03List.contains(eb03[i])) {
						uniqueEB03List.add(eb03[i]);
						hippaCodeValue = new HippaCodeValue();
						hippaCodeValue.setValue(eb03[i]);
						hippaCodeValue.setDescription(eb03Desc[i]);
						hippaCodeEB03List.add(hippaCodeValue);
					}
				}
			}
		}
		return hippaCodeEB03List;
	}

/*Reference Data Values START*/
	
	/**
	 * @param request
	 * @param response
	 * @return ModelAndView 
	 * Show Reference Data Value Manage page.
	 */
	public ModelAndView showReferenceDataManagePage(HttpServletRequest request,
			HttpServletResponse response) {
		isHPNRef=false;
		ModelAndView modelAndView = new ModelAndView(
				DomainConstants.DATA_VALUE_MANAGE_PAGE);
		request.setAttribute(DomainConstants.ACTIONS, DomainConstants.EMPTY);
		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return ModelAndView 
	 * Show Reference Data Value Manage page.
	 */
	public ModelAndView showHPNReferenceDataManagePage(HttpServletRequest request,
			HttpServletResponse response) {
		isHPNRef=true;
		ModelAndView modelAndView = new ModelAndView(
				DomainConstants.HPN_DATA_VALUE_MANAGE_PAGE);
		request.setAttribute(DomainConstants.ACTIONS, DomainConstants.EMPTY);
		return modelAndView;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * List of matching error codes.
	 */
	public ModelAndView fetchCatalogNames(HttpServletRequest request,
			HttpServletResponse response) {

		String matchValue = request.getParameter("key");
		matchValue = matchValue.trim().toUpperCase();
		// fetch catalog names for auto complete
		List catalogNameList = referenceDataFacade.fetchCatalogNames(matchValue
				+ DomainConstants.PERCENTAGE,isHPNRef);
		// convert NOTE_TYPE_CODE to NOTE TYPE CODE for display
		String catalog = null;
		if (null != catalogNameList && 0 != catalogNameList.size()) {
			for (int count = 0; count < catalogNameList.size(); count++) {
				catalog = (String) catalogNameList.get(count);
				if (DomainConstants.NOTE_TYPE_CODE.equals(catalog)) {
					catalogNameList.remove(count);
					catalogNameList.add(count, DomainConstants.NOTETYPECODE);
				}
				// convert 2120_LOOP_NM1_MESSAGE_SEGMENT to 2120 LOOP NM1 MESSAGE SEGMENT for display
				if (DomainConstants.NM1_MSG_SGMNT.equals(catalog)) {
					catalogNameList.remove(count);
					catalogNameList.add(count, DomainConstants.LOOP2120NM1MESSAGESEGMENT);
				}
			}
			Iterator catalogIterator = catalogNameList.iterator();
			while (catalogIterator.hasNext()) {
				catalog = (String) catalogIterator.next();

			}
		}
		
		Map jsonMap = new HashMap();
		if (null != catalogNameList) {
			jsonMap.put(WebConstants.ROWS, catalogNameList);
		} else {
			jsonMap.put(WebConstants.ROWS, new ArrayList());
		}

		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW,
				jsonMap);

		return modelAndView;
	}

	/**
	 * @param catalogName
	 * @return String 
	 * To convert the NOTE TYPE CODE to NOTE_TYPE_CODE for
	 * invoking backend calls
	 */
	private String getAccurateCatalog(String catalogName) {
		if (DomainConstants.NOTETYPECODE.equals(catalogName)) {
			catalogName = DomainConstants.NOTE_TYPE_CODE;
		} else 
		if (DomainConstants.LOOP2120NM1MESSAGESEGMENT.equals(catalogName)) {
			catalogName = DomainConstants.NM1_MSG_SGMNT;
		}
		return catalogName;
	}

	/**
	 * @param request
	 *            Request.
	 * @param response
	 *            Response.
	 * @return ModelAndView 
	 * Locate the data values of the Catalog Name
	 */
	public ModelAndView refreshDataValuePageAction(HttpServletRequest request,
			HttpServletResponse response) {

		String catalogNameLocate = request
				.getParameter(DomainConstants.CATALOG_LOCATE);
		if(isHPNRef){
			if(!catalogNameLocate.startsWith("HPN")){
				catalogNameLocate="";
			}
		}
		else{
			if(catalogNameLocate.startsWith("HPN")){
				catalogNameLocate="";
			}
		}
		// fetch data values of the Catalog Name
		 List dataValueList = referenceDataFacade
				.fetchItems(getAccurateCatalog(catalogNameLocate));
		//Fixing Script Error in the Reference Data Value Manage page, 
		//encoding the description for single quotes  -- BXNI June Release Fix
		 if (null != dataValueList && dataValueList.size() > 0) {
				for(int i=0;i<dataValueList.size();i++){
					BxUtil.encodeCatalogDescription((ReferenceDataValueVO)dataValueList.get(i));
				}
			}
		ModelAndView modelAndView = new ModelAndView(
				DomainConstants.DATA_VALUE_RESULT_PAGE);
		modelAndView.addObject(DomainConstants.DATA_VALUE_LIST, dataValueList);
		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return ModelAndView 
	 * Add Data Value to the Catalog Name
	 */
	public ModelAndView addItem(HttpServletRequest request,
			HttpServletResponse response) {

		String catalogNameLocate = request
				.getParameter(DomainConstants.CATALOG_LOCATE);
		String primaryCode = request
				.getParameter(DomainConstants.PRMRY_CD_FOR_SAVE);
		//Saving the primary code value as upper case in db --BXNI June Defect Fix
		if(null != primaryCode && !("").equals(primaryCode)){
			primaryCode=primaryCode.toUpperCase();
		}
		// Fetch min and max lengths of Catalog
		ReferenceDataValueVO catalogLengthVO = referenceDataFacade
				.getCatalogMinMaxLengths(getAccurateCatalog(catalogNameLocate));
		
		int valueLength = primaryCode.length();
		String errorMessage = null;
		String infoMessage = null;
		ModelAndView modelAndView = new ModelAndView(
				DomainConstants.DATA_VALUE_MANAGE_PAGE);
		if(null == catalogLengthVO) {
			errorMessage = "Catalog "
					+ catalogNameLocate
					+ " is not available for managing Reference Data Values. " 
					+ "Please enter a valid Catalog Name";
			modelAndView.addObject(WebConstants.ERROR_MESSAGES,
					errorMessage);
		} else {
			// validate length of the Value
			if (valueLength < catalogLengthVO.getCatalogMinLength()
					|| valueLength > catalogLengthVO.getCatalogMaxLength()) {
	
				if (catalogLengthVO.getCatalogMaxLength() == catalogLengthVO
						.getCatalogMinLength()) {
					errorMessage = "Length of Value field should be "
							+ catalogLengthVO.getCatalogMaxLength();
				} else {
					errorMessage = "Length of Value field should be between "
							+ catalogLengthVO.getCatalogMinLength() + " and "
							+ catalogLengthVO.getCatalogMaxLength();
				}
				modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessage);
	
			} else {
				String userId = request.getAttribute(SecurityConstants.USER_NAME)
						.toString();
				// create data value entry in DB
				ReferenceDataValueVO dataValueVOforAdd = new ReferenceDataValueVO();
				dataValueVOforAdd
						.setCatalogName(getAccurateCatalog(catalogNameLocate));
				dataValueVOforAdd.setPrimaryCode(primaryCode);
				dataValueVOforAdd.setSecondaryCode(request
						.getParameter(DomainConstants.DESC_FOR_SAVE));
				dataValueVOforAdd.setDescription(request
						.getParameter(DomainConstants.DEFN_FOR_SAVE));
				dataValueVOforAdd.setUserID(userId);
				
				// call facade method to create the data value in DB and fetch the
				// new data value list
				String createStatus = referenceDataFacade
						.addItem(dataValueVOforAdd);
	
				if (DomainConstants.SUCCESS.equals(createStatus)) {
					infoMessage = DomainConstants.VALUE_STR + primaryCode
							+ " is successfully created for " + catalogNameLocate;
					modelAndView.addObject(WebConstants.INFO_MESSAGES, infoMessage);
	
				} else if (DomainConstants.DUPLICATE.equals(createStatus)) {
					// if the Value already exists in the BX_HIPPA_SEGMENT_VAL table
					errorMessage = DomainConstants.VALUE_STR + primaryCode + " is already associated to "
							+ catalogNameLocate;
					modelAndView.addObject(WebConstants.ERROR_MESSAGES,
							errorMessage);
				}
			}
		}
		List dataValueListAfterCreate = referenceDataFacade
		.fetchItems(getAccurateCatalog(catalogNameLocate));
		//Fixing Script Error in the Reference Data Value Manage page, 
		//encoding the description for single quotes  -- BXNI June Release Fix
		 if (null != dataValueListAfterCreate && dataValueListAfterCreate.size() > 0) {
				for(int i=0;i<dataValueListAfterCreate.size();i++){
					BxUtil.encodeCatalogDescription((ReferenceDataValueVO)dataValueListAfterCreate.get(i));
				}
			}
		modelAndView.addObject(DomainConstants.DATA_VALUE_LIST,
				dataValueListAfterCreate);
		modelAndView.addObject(DomainConstants.REFRESH, DomainConstants.YES);
		modelAndView.addObject(DomainConstants.CATALOG_LOCATE,
				catalogNameLocate);

		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return ModelAndView 
	 * Update Data Value of the Catalog Name
	 */
	public ModelAndView updateItem(HttpServletRequest request,
			HttpServletResponse response) {

		String catalogNameLocate = request
				.getParameter(DomainConstants.CATALOG_LOCATE);
		String primaryCode = request
				.getParameter(DomainConstants.VALUE_FOR_SAVE);
		String secondaryCode = request
				.getParameter(DomainConstants.DESC_FOR_SAVE);
		String description = request
				.getParameter(DomainConstants.DEFN_FOR_SAVE);
		String additionalComments = request
				.getParameter(DomainConstants.USER_COMMENTS);
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();

		ReferenceDataValueVO dataValueVOForUpdate = new ReferenceDataValueVO();
		dataValueVOForUpdate
				.setCatalogName(getAccurateCatalog(catalogNameLocate));
		dataValueVOForUpdate.setPrimaryCode(primaryCode);
		dataValueVOForUpdate.setSecondaryCode(secondaryCode);
		dataValueVOForUpdate.setDescription(description);
		dataValueVOForUpdate.setAdditionalComments(additionalComments);
		dataValueVOForUpdate.setUserID(userId);

		String updateStatus = referenceDataFacade
				.updateItem(dataValueVOForUpdate);

		ModelAndView modelAndView = new ModelAndView(
				DomainConstants.DATA_VALUE_MANAGE_PAGE);
		// fetch data value after update
		List dataValueListAfterUpdate = referenceDataFacade
				.fetchItems(getAccurateCatalog(catalogNameLocate));
		//Fixing Script Error in the Reference Data Value Manage page, 
		//encoding the description for single quotes  -- BXNI June Release Fix
		 if (null != dataValueListAfterUpdate && dataValueListAfterUpdate.size() > 0) {
				for(int i=0;i<dataValueListAfterUpdate.size();i++){
					BxUtil.encodeCatalogDescription((ReferenceDataValueVO)dataValueListAfterUpdate.get(i));
				}
			}
		modelAndView.addObject(DomainConstants.DATA_VALUE_LIST,
				dataValueListAfterUpdate);
		if (DomainConstants.SUCCESS.equals(updateStatus)) {
			modelAndView.addObject(WebConstants.INFO_MESSAGES, DomainConstants.VALUE_STR
					+ primaryCode + " has been updated successfully");
		}
		modelAndView.addObject(DomainConstants.REFRESH, DomainConstants.YES);
		modelAndView.addObject(DomainConstants.CATALOG_LOCATE,
				catalogNameLocate);
		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return ModelAndView 
	 * Checks if there are mappings for the data value and
	 *  retrieves the number of each mapping before deleting the data
	 *  value
	 */
	public ModelAndView checkItemMappings(HttpServletRequest request,
			HttpServletResponse response) {

		String primaryCode = request
				.getParameter(DomainConstants.VALUE_FOR_SAVE);
		String catalogNameLocate = request
				.getParameter(DomainConstants.CATALOG_LOCATE);
		ReferenceDataValueVO referenceDataValueVO = new ReferenceDataValueVO();
		referenceDataValueVO
				.setCatalogName(getAccurateCatalog(catalogNameLocate));
		referenceDataValueVO.setPrimaryCode(primaryCode);

		// Check if Data value is mapped
		ReferenceDataValueVO checkMappingVO = referenceDataFacade
				.checkItemMappings(referenceDataValueVO);
		// if there are no mappings the additional comments pop up will be
		// displayed
		ModelAndView modelAndView = new ModelAndView(
				DomainConstants.DATA_VALUE_MANAGE_PAGE);
		if (checkMappingVO.getNumberOfVariables() == 0
				&& checkMappingVO.getNumberOfSPS() == 0
				&& checkMappingVO.getNumberOfHeaderRules() == 0
				&& checkMappingVO.getNumberOfMessages() == 0) {

			modelAndView.addObject(DomainConstants.OPEN_COMMENTS_DIALOG,
					DomainConstants.YES);

		} else {
			StringBuffer errorMessage = new StringBuffer(DomainConstants.VALUE_STR
					+ primaryCode
					+ " cannot be deleted because it is mapped to ");
			StringBuffer mappings = new StringBuffer(DomainConstants.EMPTY);
			if (checkMappingVO.getNumberOfVariables() > 0) {
				mappings.append(checkMappingVO.getNumberOfVariables());
				mappings.append(" variables");
			}
			if (checkMappingVO.getNumberOfSPS() > 0) {
				mappings = getErrorMessage(mappings);
				mappings.append(checkMappingVO.getNumberOfSPS());
				mappings.append(" SPS IDs");
			}
			if (checkMappingVO.getNumberOfHeaderRules() > 0) {
				mappings = getErrorMessage(mappings);
				mappings.append(checkMappingVO.getNumberOfHeaderRules());
				mappings.append(" header rules");
			}
			if (checkMappingVO.getNumberOfMessages() > 0) {
				mappings = getErrorMessage(mappings);
				mappings.append(checkMappingVO.getNumberOfMessages());
				mappings.append(" messages");
			}

			errorMessage.append(mappings.toString());
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, errorMessage
					.toString());
		}

		// fetch data value list
		List dataValueListDuringDelete = referenceDataFacade
				.fetchItems(getAccurateCatalog(catalogNameLocate));
		//Fixing Script Error in the Reference Data Value Manage page, 
		//encoding the description for single quotes  -- BXNI June Release Fix
		 if (null != dataValueListDuringDelete && dataValueListDuringDelete.size() > 0) {
				for(int i=0;i<dataValueListDuringDelete.size();i++){
					BxUtil.encodeCatalogDescription((ReferenceDataValueVO)dataValueListDuringDelete.get(i));
				}
			}
		modelAndView.addObject(DomainConstants.DATA_VALUE_LIST,
				dataValueListDuringDelete);
		modelAndView.addObject(DomainConstants.CATALOG_LOCATE,
				catalogNameLocate);
		modelAndView.addObject(DomainConstants.REFRESH, DomainConstants.YES);
		modelAndView.addObject(DomainConstants.VALUE_FOR_SAVE, primaryCode);
		return modelAndView;
	}

	/**
	 * @param mappings
	 * @return StringBuffer 
	 * Method to check if message is empty
	 */
	private StringBuffer getErrorMessage(StringBuffer mappings) {
		String mappingsString = mappings.toString();
		if (!mappingsString.equals(DomainConstants.EMPTY)) {
			mappings.append(DomainConstants.AND);
		}
		return mappings;
	}

	/**
	 * @param request
	 * @param response
	 * @return ModelAndView 
	 * Delete Data Value of the Catalog Name
	 */
	public ModelAndView deleteItem(HttpServletRequest request,
			HttpServletResponse response) {

		String primaryCode = request
				.getParameter(DomainConstants.VALUE_FOR_SAVE);
		String catalogNameLocate = request
				.getParameter(DomainConstants.CATALOG_LOCATE);
		String additionalComments = request
				.getParameter(DomainConstants.USER_COMMENTS);
		String userId = request.getAttribute(SecurityConstants.USER_NAME)
				.toString();
		ReferenceDataValueVO dataValueVOForDelete = new ReferenceDataValueVO();
		dataValueVOForDelete
				.setCatalogName(getAccurateCatalog(catalogNameLocate));
		dataValueVOForDelete.setPrimaryCode(primaryCode);
		dataValueVOForDelete.setAdditionalComments(additionalComments);
		dataValueVOForDelete.setUserID(userId);
		
		// Deleting the data Value
		String deleteStatus = referenceDataFacade
				.deleteItem(dataValueVOForDelete);
		// fetch data value after delete
		List dataValueListAfterDelete = referenceDataFacade
				.fetchItems(getAccurateCatalog(catalogNameLocate));
		//Fixing Script Error in the Reference Data Value Manage page, 
		//encoding the description for single quotes  -- BXNI June Release Fix
		 if (null != dataValueListAfterDelete && dataValueListAfterDelete.size() > 0) {
				for(int i=0;i<dataValueListAfterDelete.size();i++){
					BxUtil.encodeCatalogDescription((ReferenceDataValueVO)dataValueListAfterDelete.get(i));
				}
			}
		ModelAndView modelAndView = new ModelAndView(
				DomainConstants.DATA_VALUE_MANAGE_PAGE);
		if (DomainConstants.SUCCESS.equals(deleteStatus)) {
			modelAndView.addObject(WebConstants.INFO_MESSAGES, DomainConstants.VALUE_STR
					+ primaryCode + " has been deleted successfully");
		}
		modelAndView.addObject(DomainConstants.CATALOG_LOCATE,
				catalogNameLocate);
		modelAndView.addObject(DomainConstants.DATA_VALUE_LIST,
				dataValueListAfterDelete);
		modelAndView.addObject(DomainConstants.REFRESH, DomainConstants.YES);

		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @return ModelAndView 
	 * Delete Data Value of the Catalog Name
	 */
	public ModelAndView viewHistoryOfCatalog(HttpServletRequest request,
			HttpServletResponse response) {

		String catalogNameLocate = request
				.getParameter(DomainConstants.CATALOG_LOCATE);
		List deletedDataValueList = referenceDataFacade
				.fetchHistoryOfCatalog(getAccurateCatalog(catalogNameLocate));

		ModelAndView modelAndView = new ModelAndView(
				DomainConstants.VIEW_HISTORY_POP_UP);
		modelAndView.addObject("deletedDataValueList", deletedDataValueList);
		
		return modelAndView;
	}
	
	/*Reference Data Values END*/	
	
/*************************Manage Header Rule EB03 Associations April 2012 Release SSCR14181 START ******************************/
	
	
	/**
	 * @param request
	 * @param response
	 * @return ModelAndView Show manageHeaderRuleToEB03 page.
	 */
	public ModelAndView showManageHeaderRuleToEB03Page (HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView(DomainConstants.MANAGE_HEADERRULE_TO_EB03_PAGE);
		modelAndView.addObject("visibility", "none");
		request.setAttribute("actions", DomainConstants.EMPTY);
		return modelAndView;
	}
	
	
	
	
	
	/**
	 * Method fetches the header Rule value from the DB for
	 *  auto population in the Header Rule Text box.
	 * @param request
	 * @param response
	 * @return ModelAndView Auto populate value
	 */
	public ModelAndView fetchHeaderRuleForAutoComplete (HttpServletRequest request,
			HttpServletResponse response) {
		String searchText = request.getParameter(DomainConstants.KEY);
		if (searchText != null && !"".equals(searchText.trim())) {
			searchText = searchText.trim().toUpperCase();
		}
		searchText = searchText + "%";
		boolean autoCompleteFlag = true;
		List headerRuleList = referenceDataFacade.fetchHeaderRuleForAutoComplete(searchText,autoCompleteFlag);
		Map jsonMap = new HashMap();
		 if (null != headerRuleList && !(DomainConstants.EMPTY.equals(headerRuleList))) {
             List hippaCodeList = new ArrayList();
             	for (Iterator itr = headerRuleList.iterator(); itr.hasNext();) {
             		HeaderRuleToEB03MappingVO mappingVO = (HeaderRuleToEB03MappingVO) itr.next();
                     AutoPopulateVO autoPopulateVO1 = new AutoPopulateVO(mappingVO.getHeaderRuleDescription(), mappingVO.getHeaderRuleValue(), mappingVO.getHeaderRuleValue());
                     hippaCodeList.add(autoPopulateVO1);
                 }
             	jsonMap.put(WebConstants.ROWS, hippaCodeList);
             
         }else {	            	
        	 jsonMap.put(WebConstants.ROWS, new ArrayList());
         }
		ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW,
				jsonMap);
		
		return modelAndView;
	}
	/**
	 * Method fetches the header rule associations from the db
	 *  Either EB03 or Header Rule based search can be achieved.
	 * @param request
	 * @param response
	 * @return ModelAndView headerRuleToEB03SearchResult page
	 */
	public ModelAndView fetchHeaderRuleToEB03Mapping(HttpServletRequest request,
			HttpServletResponse response){

		HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO = new HeaderRuleToEB03MappingVO();

		if (null != request.getParameter("EB03Id") && !("").equals(request.getParameter("EB03Id"))) {
			headerRuleToEB03MappingVO.setEb03Value(request.getParameter(
					"EB03Id").trim());
		}
		if (null != request.getParameter("headerRule") && !("").equals(request.getParameter("headerRule")) ) {
			headerRuleToEB03MappingVO.setHeaderRuleValue(request.getParameter(
					"headerRule").trim());
		}
		headerRuleToEB03MappingVO.setLastUpdatedUser(request.getAttribute(SecurityConstants.USER_NAME).toString());

		ModelAndView modelAndView = new ModelAndView(
				"headerRuleToEB03SearchResult");
		
		List headerRuleMappingList = new ArrayList();

		headerRuleMappingList = referenceDataFacade
				.fetchHeaderRuleToEB03Mapping(headerRuleToEB03MappingVO);

		/*modelAndView.addObject("fromaction", "SELECT");
		request.setAttribute("fromaction", "SELECT");*/

		modelAndView.addObject("headerRuleToEB03SearchResultList", headerRuleMappingList);
		modelAndView.addObject("EB03Id1", headerRuleToEB03MappingVO.getEb03Value());
		modelAndView.addObject("headerRule1", headerRuleToEB03MappingVO.getHeaderRuleValue());
		return modelAndView;
	}
	/**
	 * Method deletes all the header rule associations corresponding to a EB03 from the db
	 * @param request
	 * @param response
	 * @return ModelAndView headerRuleToEB03SearchResult page
	 */
	public ModelAndView deleteHeaderRuleToEB03Mapping(HttpServletRequest request,
			HttpServletResponse response){

		HeaderRuleToEB03MappingVO headerRuleToEB03MappingVODeleteCriteria = new HeaderRuleToEB03MappingVO();
		HeaderRuleToEB03MappingVO headerRuleToEB03MappingVOSearchCriteria = new HeaderRuleToEB03MappingVO();
		
		//Setting the delete criteria to the delete criteria object
		if (null != request.getParameter("deleteAllActionEB03Id") && !("").equals(request.getParameter("deleteAllActionEB03Id"))) {
			headerRuleToEB03MappingVODeleteCriteria.setEb03Value(request.getParameter("deleteAllActionEB03Id"));

		}
		if (null != request.getParameter("deleteAllUserComments") && !("").equals(request.getParameter("deleteAllUserComments"))) {
			headerRuleToEB03MappingVODeleteCriteria.setUserComments(request.getParameter("deleteAllUserComments"));

		}
		headerRuleToEB03MappingVODeleteCriteria.setLastUpdatedUser(request.getAttribute(SecurityConstants.USER_NAME).toString());
		
		//Setting the search criteria to the search criteria object
		if (null != request.getParameter("searchCriteriaEB03IdDelete") && !("").equals(request.getParameter("searchCriteriaEB03IdDelete"))) {
			headerRuleToEB03MappingVOSearchCriteria.setEb03Value(request.getParameter(
					"searchCriteriaEB03IdDelete").trim());
		}
		if (null != request.getParameter("searchCriteriaHeaderRuleIdDelete") && !("").equals(request.getParameter("searchCriteriaHeaderRuleIdDelete")) ) {
			headerRuleToEB03MappingVOSearchCriteria.setHeaderRuleValue(request.getParameter(
					"searchCriteriaHeaderRuleIdDelete").trim());
		}

		List headerRuleMappingList = referenceDataFacade.deleteHeaderRuleToEB03Mapping(headerRuleToEB03MappingVODeleteCriteria,
				headerRuleToEB03MappingVOSearchCriteria); 
		ModelAndView modelAndView = new ModelAndView(DomainConstants.MANAGE_HEADERRULE_TO_EB03_PAGE);
		
		//Setting the search criteria back to the page
		modelAndView.addObject("EB03Id1", headerRuleToEB03MappingVOSearchCriteria.getEb03Value());
		modelAndView.addObject("headerRule1", headerRuleToEB03MappingVOSearchCriteria.getHeaderRuleValue());

		modelAndView.addObject("fromaction", "DELETE");
		request.setAttribute("fromaction", "DELETE");
		
		//Setting the messages
		modelAndView.addObject(WebConstants.INFO_MESSAGES, headerRuleToEB03MappingVODeleteCriteria.getOperationMessages());
		modelAndView.addObject("headerRuleToEB03SearchResultList", headerRuleMappingList);
		return modelAndView;
		}
		/**
		 * Method deletes all the header rule associations corresponding to a EB03 from the db
		 * @param request
		 * @param response
		 * @return ModelAndView headerRuleToEB03edit page
		 */
		public ModelAndView editHeaderRuleToEB03Mapping(HttpServletRequest request,
				HttpServletResponse response){

			HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO = new HeaderRuleToEB03MappingVO();

			if (null != request.getParameter("eb03IdForEditPage")) {
				headerRuleToEB03MappingVO.setEb03Value(request.getParameter(
						"eb03IdForEditPage").trim());
			}	
			if (null != request.getParameter("eb03DescriptionForEditPage")) {
				headerRuleToEB03MappingVO.setEb03Description(request.getParameter(
						"eb03DescriptionForEditPage").trim());
			}
			if (null != request.getParameter("commaSeperatedHeaderRulesForEditPage")) {
				headerRuleToEB03MappingVO.setCommaSeperatedHeaderRules(request.getParameter(
						"commaSeperatedHeaderRulesForEditPage").trim());
			}
			
			
			List headerRuleList =referenceDataFacade.fetchHeaderRuleDetails(headerRuleToEB03MappingVO);

			ModelAndView modelAndView = new ModelAndView("headerRuleToEB03EditMapping");
			modelAndView.addObject("eb03Id",headerRuleToEB03MappingVO.getEb03Value());
			modelAndView.addObject("eb03Description",headerRuleToEB03MappingVO.getEb03Description());
			modelAndView.addObject("commaSeperatedHeaderRules",headerRuleToEB03MappingVO.getCommaSeperatedHeaderRules());
			modelAndView.addObject("headerRuleList",headerRuleList);
			return modelAndView;
		
		}
		/**
		 * Method saves the header rule associations corresponding to a EB03 to the db
		 * @param request
		 * @param response
		 * @return ModelAndView headerRuleToEB03edit page
		 */
		
		public ModelAndView saveHeaderRuleToEB03Mapping(HttpServletRequest request,
				HttpServletResponse response){
			HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO = new HeaderRuleToEB03MappingVO();
			HeaderRuleToEB03MappingVO headerRuleToEB03MappingVOForSelect = new HeaderRuleToEB03MappingVO();
			
			
			
			if (null != request.getParameter(DomainConstants.EB03_ID_FROM_EDIT_SAVE_ACTION) && !("").equals(request.getParameter(DomainConstants.EB03_ID_FROM_EDIT_SAVE_ACTION))) {
				headerRuleToEB03MappingVO.setEb03Value(request.getParameter(DomainConstants.EB03_ID_FROM_EDIT_SAVE_ACTION));

			}
			if (null != request.getParameter(DomainConstants.EB03_DESC_FROM_EDIT_SAVE_ACTION) && !("").equals(request.getParameter(DomainConstants.EB03_DESC_FROM_EDIT_SAVE_ACTION))) {
				headerRuleToEB03MappingVO.setEb03Description(request.getParameter(DomainConstants.EB03_DESC_FROM_EDIT_SAVE_ACTION));
			}
			if (null != request.getParameter("saveUserComments") && !("").equals(request.getParameter("saveUserComments"))) {
				headerRuleToEB03MappingVO.setUserComments(request.getParameter("saveUserComments"));
			}
			
			
			
			headerRuleToEB03MappingVO.setLastUpdatedUser(request.getAttribute(SecurityConstants.USER_NAME).toString());
		
			
			//Setting the search criteria to the search criteria object
			if (null != request.getParameter("searchCriteriaEB03Id") && !("").equals(request.getParameter("searchCriteriaEB03Id"))) {
				headerRuleToEB03MappingVOForSelect.setEb03Value(request.getParameter(
						"searchCriteriaEB03Id").trim());
			}
			if (null != request.getParameter("searchCriteriaHeaderRuleId") && !("").equals(request.getParameter("searchCriteriaHeaderRuleId")) ) {
				headerRuleToEB03MappingVOForSelect.setHeaderRuleValue(request.getParameter(
						"searchCriteriaHeaderRuleId").trim());

			}
			
			ModelAndView modelAndView = new ModelAndView(DomainConstants.MANAGE_HEADERRULE_TO_EB03_PAGE);
			List headerRuleObjectListFromPage = getAllHeaderRulesToSave(request);
			List headerRuleListFromPage = new ArrayList();
			if(null != headerRuleObjectListFromPage){
				Iterator headerRuleObjectListFromPageItr = headerRuleObjectListFromPage.iterator();
				while (headerRuleObjectListFromPageItr.hasNext()) {
					HeaderRuleToEB03MappingVO eb03MappingVO = (HeaderRuleToEB03MappingVO) headerRuleObjectListFromPageItr.next();
					headerRuleListFromPage.add(eb03MappingVO.getHeaderRuleValue());
				}
			}
				
				
			String message = "";
			List possibleHeaderRuleValues = new ArrayList();
			List invalidHeaderRuleList = new ArrayList();
			
			
			String CSVwithSingleQuoteHeaderRulesFromPage = BxUtil.convertArrayToCSVwithSingleQuote(headerRuleListFromPage);
			if(null != CSVwithSingleQuoteHeaderRulesFromPage && !("").equals(CSVwithSingleQuoteHeaderRulesFromPage)){
				possibleHeaderRuleValues = referenceDataFacade.possibleHeaderRuleValues(CSVwithSingleQuoteHeaderRulesFromPage);
			}
			if(null != headerRuleListFromPage){
				
				Iterator headerRuleItr = headerRuleListFromPage.iterator();
				int i = 0;
				
				for (int j = 0; j<headerRuleListFromPage.size();j++){
					String headerRulevalue = (String) headerRuleListFromPage.get(j);
					if (!possibleHeaderRuleValues.contains(headerRulevalue)) {
						if (i == 0) {
							message = "Invalid Header Rule values : ";
							i++;
						}
						message = message + " " + headerRulevalue;
						HeaderRuleToEB03MappingVO headerRuleToEB03MappingVOInvalid = new HeaderRuleToEB03MappingVO();
						headerRuleToEB03MappingVOInvalid.setEb03Value(headerRulevalue);
						invalidHeaderRuleList.add(headerRuleToEB03MappingVOInvalid);
					}
				}
			}
			
			if ("".equals(message)) {
				List headerRuleResultList = referenceDataFacade.saveHeaderRuleToEB03Mapping(headerRuleToEB03MappingVO,
						headerRuleToEB03MappingVOForSelect, headerRuleListFromPage);
				List headerRuleList = referenceDataFacade.fetchHeaderRuleDetails(headerRuleToEB03MappingVO);
				if(null != headerRuleToEB03MappingVO.getOperationMessages() && !("").equals(headerRuleToEB03MappingVO.getOperationMessages())){
					modelAndView.addObject(WebConstants.WARNING_MESSAGES,headerRuleToEB03MappingVO.getOperationMessages());
				}else{
					modelAndView.addObject(WebConstants.INFO_MESSAGES,"EB03 � Header Rule Association saved successfully");
				}
				modelAndView.addObject("eb03Id", headerRuleToEB03MappingVO.getEb03Value());
				modelAndView.addObject("eb03Description", headerRuleToEB03MappingVO.getEb03Description());
				modelAndView.addObject("headerRuleToEB03SearchResultList", headerRuleResultList);
				modelAndView.addObject("headerRuleList", headerRuleList);
				modelAndView.addObject("EB03Id1",headerRuleToEB03MappingVOForSelect.getEb03Value());
				modelAndView.addObject("headerRule1",headerRuleToEB03MappingVOForSelect.getEb03Description());
				modelAndView.addObject("fromaction", "UPDATE");
				request.setAttribute("fromaction", "UPDATE");
			}else{
				List headerRuleResultList = referenceDataFacade.fetchHeaderRuleToEB03Mapping(headerRuleToEB03MappingVOForSelect);
				//List headerRuleList = referenceDataFacade.fetchHeaderRuleDetails(headerRuleToEB03MappingVO);

				modelAndView.addObject("eb03Id", headerRuleToEB03MappingVO.getEb03Value());
				modelAndView.addObject("eb03Description", headerRuleToEB03MappingVO.getEb03Description());
				modelAndView.addObject("headerRuleToEB03SearchResultList", headerRuleResultList);
				modelAndView.addObject("headerRuleList", headerRuleObjectListFromPage);
				modelAndView.addObject("EB03Id1",headerRuleToEB03MappingVOForSelect.getEb03Value());
				modelAndView.addObject("headerRule1",headerRuleToEB03MappingVOForSelect.getEb03Description());
				modelAndView.addObject(WebConstants.ERROR_MESSAGES, message);
				modelAndView.addObject("fromaction", "UPDATE");
				request.setAttribute("fromaction", "UPDATE");
				
			}
			
			
			
			return modelAndView;
		}
		/**
		 * @param request
		 *            HttpServletRequest
		 * @return List EB03 list from web page
		 */

		private List getAllHeaderRulesToSave(HttpServletRequest request) {

			List uniqueHeaderRuleList = new ArrayList();
			List headerRuleList = new ArrayList();
			
			// set header values
			if (null != request.getParameterValues(DomainConstants.HEADER_RULE_VAL)) {
				int sizeOfHeaderRule = request.getParameterValues(DomainConstants.HEADER_RULE_VAL).length;
				String[] headerRule = new String[sizeOfHeaderRule];
				String[] headerRuleDescription = new String[sizeOfHeaderRule];
				for (int i = 0; i < sizeOfHeaderRule; i++) {

					if (!request.getParameterValues(DomainConstants.HEADER_RULE_VAL)[i]
							.trim().equals("")) {

						headerRule[i] = request.getParameterValues(DomainConstants.HEADER_RULE_VAL)[i].trim();
						headerRuleDescription[i] = request.getParameterValues(DomainConstants.HEADER_RULE_DESC)[i].trim();
						if (!uniqueHeaderRuleList.contains(headerRule[i])) {
							uniqueHeaderRuleList.add(headerRule[i]);
							HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO = new HeaderRuleToEB03MappingVO();
							headerRuleToEB03MappingVO.setHeaderRuleValue(headerRule[i]);
							headerRuleToEB03MappingVO.setHeaderRuleDescription(headerRuleDescription[i]);
							headerRuleList.add(headerRuleToEB03MappingVO);
						}
					}
				}
			}
			return headerRuleList;
		}	
		
		/**
		 * @param request
		 * @param response
		 * @return
		 */
		public ModelAndView viewHistoryOfHeaderRuleToEB03Mapping (HttpServletRequest request,
				HttpServletResponse response) {
			HeaderRuleToEB03MappingVO headerRuleToEB03MappingVO = new HeaderRuleToEB03MappingVO();
            headerRuleToEB03MappingVO.setEb03Value(request.getParameter("eb03IdFromEdit"));
            List headerRuleToEB03HistoryList = referenceDataFacade
                        .viewHistoryOfHeaderRuleToEB03Mapping(headerRuleToEB03MappingVO);

            ModelAndView modelAndView = new ModelAndView(
                        DomainConstants.VIEW_HISTORY_EB03_HEADERRULE);
            modelAndView.addObject("headerRuleToEB03HistoryList", headerRuleToEB03HistoryList);
            
            return modelAndView;
		}
	/*************************Manage Header Rule EB03 Associations April 2012 Release SSCR14181 END ******************************/
		// Manage EB01-Data Type Association -  START
		/**
		 * @param request
		 * @param response
		 * @return ModelAndView 
		 * Method to show the manage EB01 - data type page,
		 */
		public ModelAndView showManageDataTypeToEB01Page(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView modelAndView = new ModelAndView(
					DomainConstants.MANAGE_DATATYPE_TO_EB01_PAGE);
			request.setAttribute(DomainConstants.ACTIONS, DomainConstants.EMPTY);
			return modelAndView;
		}
		
		/**
		 * @param request
		 * @param response
		 * @return ModelAndView 
		 * method to get data types for auto complete
		 */
		public ModelAndView fetchDataTypeForAutoComplete(HttpServletRequest request, HttpServletResponse response) {
			String searchText = request.getParameter(DomainConstants.KEY);
			if (searchText != null && !"".equals(searchText.trim())) {
				searchText = searchText.trim().toUpperCase();
			}
			searchText = searchText + "%";
			boolean autoCompleteFlag = true;
			List dataTypeList = referenceDataFacade.fetchDataTypeForAutoComplete(searchText,autoCompleteFlag);
			Map jsonMap = new HashMap();
			 if (null != dataTypeList && !(DomainConstants.EMPTY.equals(dataTypeList))) {
	             List hippaCodeList = new ArrayList();
	             	for (Iterator itr = dataTypeList.iterator(); itr.hasNext();) {
	             		DataTypeToEB01MappingVO mappingVO = (DataTypeToEB01MappingVO) itr.next();
	                     AutoPopulateVO autoPopulateVO1 = new AutoPopulateVO(mappingVO.getDataTypeDescription(), mappingVO.getDataTypeValue(), mappingVO.getDataTypeValue());
	                     hippaCodeList.add(autoPopulateVO1);
	                 }
	             	jsonMap.put(WebConstants.ROWS, hippaCodeList);
	             
	         }else {	            	
	        	 jsonMap.put(WebConstants.ROWS, new ArrayList());
	         }
			/*Map jsonMap = new HashMap();
			if (null != dataTypeList) {
				jsonMap.put(WebConstants.ROWS, dataTypeList);
			} else {
				jsonMap.put(WebConstants.ROWS, new ArrayList());
			}*/
			ModelAndView modelAndView = new ModelAndView(WebConstants.JSON_VIEW,
					jsonMap);
			
			return modelAndView;
		}
		
		/**
		 * fetching data type values associated with EB01.
		 * @param request
		 * @param response
		 * @return ModelAndView
		 */
		public ModelAndView fetchDataTypeToEB01Mapping(HttpServletRequest request, HttpServletResponse response) {
			
			String eb01 = request.getParameter(DomainConstants.EB01);
			String dataType = request.getParameter(DomainConstants.DATATYPE);
			DataTypeToEB01MappingVO mappingVO = new DataTypeToEB01MappingVO();
			mappingVO.setEb01value(eb01);
			mappingVO.setDataTypeValue(dataType);
			
			List dataTypeVOList = referenceDataFacade.fetchDataTypeToEB01Mapping(mappingVO);
			ModelAndView modelAndView = new ModelAndView(
					DomainConstants.EB01_DATA_TYPE_RESULT_PAGE);
			modelAndView.addObject(DomainConstants.DATA_TYPE_VALUE_LIST, dataTypeVOList);
			return modelAndView;
		}
		
		/**
		 * Delete all the data types associated with EB01.
		 * @param request
		 * @param response
		 * @return ModelAndView
		 */
		public ModelAndView deleteDatatypeToEB01Mapping(HttpServletRequest request, HttpServletResponse response) {
			DataTypeToEB01MappingVO eb01MappingVODelete = new DataTypeToEB01MappingVO();
			DataTypeToEB01MappingVO eb01MappingToFetch = new DataTypeToEB01MappingVO();
			// Getting the EB01 value whose association to be deleted from the request.
			String eb01AssnToBeDeleted = request.getParameter(DomainConstants.DELETE_ACTION_EB01ID);
			String dataTypeValuesToDelete = request.getParameter(DomainConstants.DELETE_ACTION_DATATYPE);
			// Getting the user comments, the comment that user entered while deleting an EB01 association.
			String userCommentsDelete = request.getParameter(DomainConstants.USER_COMMENTS_DELETE);
			eb01MappingVODelete.setEb01value(eb01AssnToBeDeleted);
			eb01MappingVODelete.setDataTypeValue(dataTypeValuesToDelete);
			eb01MappingVODelete.setUserComments(userCommentsDelete);
			
			eb01MappingVODelete.setLastUpdatedUser(request.getAttribute(SecurityConstants.USER_NAME).toString());
			
			// Building the VO object to search after the delete action.
			String eb01Value = request.getParameter(DomainConstants.EB01_VALUE_FETCH);
			String dataTypeValue = request.getParameter(DomainConstants.DATATYPE_VALUE_FETCH);
			eb01MappingToFetch.setEb01value(eb01Value);
			eb01MappingToFetch.setDataTypeValue(dataTypeValue);
			List dataTypeVOList = referenceDataFacade.deleteDatatypeToEB01Mapping(eb01MappingVODelete, eb01MappingToFetch);
			ModelAndView modelAndView = new ModelAndView(DomainConstants.MANAGE_DATATYPE_TO_EB01_PAGE);
			
			modelAndView.addObject("EB01Id", eb01MappingToFetch.getEb01value());
			modelAndView.addObject("dataTypeId", eb01MappingToFetch.getDataTypeValue());

			modelAndView.addObject("fromaction", "DELETE");
	        request.setAttribute("fromaction", "DELETE");
	        modelAndView.addObject(WebConstants.INFO_MESSAGES, eb01MappingVODelete.getOperationMessages());
			modelAndView.addObject(DomainConstants.DATA_TYPE_VALUE_LIST, dataTypeVOList);
			return modelAndView;
		}
		/**
		 * This method provide a provision to edit the data type associations against an EB01
		 * @param request
		 * @param response
		 * @return ModelAndView
		 */
		public ModelAndView editDataTypeToEB01Mapping(HttpServletRequest request, HttpServletResponse response) {
			DataTypeToEB01MappingVO eb01MappingVOEdit = new DataTypeToEB01MappingVO();
			String eb01DescEdit = null;
			String eb01ValueEdit = request.getParameter(DomainConstants.EB01_VALUE_EDIT);
			if(null != request.getParameter(DomainConstants.EB01_DESC_EDIT)) {
				 eb01DescEdit = request.getParameter(DomainConstants.EB01_DESC_EDIT);
			}
			eb01MappingVOEdit.setEb01value(eb01ValueEdit);
			List dataTypeListForManageEdit = referenceDataFacade.editDataTypeToEB01Mapping(eb01MappingVOEdit);
			ModelAndView modelAndView = new ModelAndView(DomainConstants.EB01_DATA_TYPE_EDIT_PAGE);
			modelAndView.addObject(DomainConstants.DATA_TYPE_VO_LIST_EDIT, dataTypeListForManageEdit);
			modelAndView.addObject("eb01",eb01ValueEdit);
			modelAndView.addObject("eb01Desc",eb01DescEdit);
			return modelAndView;
			 
		}
		
		/**
		 * Save the changes in data type associations against the respective EB01
		 * @param request
		 * @param response
		 * @return ModelAndView
		 */
		public ModelAndView saveDatatypeToEB01Mapping(HttpServletRequest request, HttpServletResponse response) {
			DataTypeToEB01MappingVO eb01MappingVOToUpdate = new DataTypeToEB01MappingVO();
			DataTypeToEB01MappingVO eb01MappingVOToFetch = new DataTypeToEB01MappingVO();
			String eb01DescUpdate = "";
			String eb01ValueUpdate = "";
			if (null != request.getParameter("eb01ValueUpdateSaveAction") && !DomainConstants.EMPTY.equals(request.getParameter("eb01ValueUpdateSaveAction"))) {
				eb01ValueUpdate = request.getParameter("eb01ValueUpdateSaveAction");			
			}
			if(null != request.getParameter("eb01DescUpdateSaveAction")&& !DomainConstants.EMPTY.equals(request.getParameter("eb01DescUpdateSaveAction"))) {
				eb01DescUpdate = request.getParameter("eb01DescUpdateSaveAction");
			}
			String userCommentsSave = request.getParameter(DomainConstants.USER_COMMENTS_SAVE);
			eb01MappingVOToUpdate.setEb01value(eb01ValueUpdate);
			eb01MappingVOToUpdate.setUserComments(userCommentsSave);
			eb01MappingVOToUpdate.setLastUpdatedUser(request.getAttribute(SecurityConstants.USER_NAME).toString());
			
			String eb01Value = request.getParameter(DomainConstants.EB01_VALUE_SEARCH);
			String dataTypeValue = request.getParameter(DomainConstants.DATATYPE_VALUE_SEARCH);
			eb01MappingVOToFetch.setEb01value(eb01Value);
			eb01MappingVOToFetch.setDataTypeValue(dataTypeValue);
			
			ModelAndView modelAndView = new ModelAndView(DomainConstants.MANAGE_DATATYPE_TO_EB01_PAGE);
			List dataTypeListToInsert = getAllDataTypesToSave(request);
			List validDataTypeVOList = referenceDataFacade.fetchDataTypeForAutoComplete("%",true);
			String message = "";
			List validDataTypeList = new ArrayList();
			if (null != validDataTypeVOList) {
				Iterator validDataTypeListIterator = validDataTypeVOList.iterator();
				while(validDataTypeListIterator.hasNext()) {
					DataTypeToEB01MappingVO validDappingVO = (DataTypeToEB01MappingVO) validDataTypeListIterator.next();
					String validDataType = validDappingVO.getDataTypeValue();
					validDataTypeList.add(validDataType);
				}
			}
			if(null != dataTypeListToInsert && null != validDataTypeList) {
				Iterator dataTypeListIterator = dataTypeListToInsert.iterator();
				int i = 0;
				while (dataTypeListIterator.hasNext()) {
					DataTypeToEB01MappingVO dataTypeToEB01MappingVO = (DataTypeToEB01MappingVO) dataTypeListIterator.next();
					String dataTypeToInsert = dataTypeToEB01MappingVO.getDataTypeValue();
					if(!validDataTypeList.contains(dataTypeToInsert)) {
						if (i == 0) {
							message = "Invalid Data Type values : ";
							i++;
						}
						message = message + " " + dataTypeToInsert;	
					}
				}
			}
			if ("".equals(message)) {
				List dataTypeVOList = referenceDataFacade.saveDatatypeToEB01Mapping(eb01MappingVOToUpdate, eb01MappingVOToFetch, dataTypeListToInsert);
				List dataTypeListForManageEdit = referenceDataFacade.editDataTypeToEB01Mapping(eb01MappingVOToUpdate);
				
				
				if(null != eb01MappingVOToUpdate.getOperationMessages() && !("").equals(eb01MappingVOToUpdate.getOperationMessages())){
					modelAndView.addObject(WebConstants.WARNING_MESSAGES,eb01MappingVOToUpdate.getOperationMessages());
				}else{
					modelAndView.addObject(WebConstants.INFO_MESSAGES,"EB01 � Data Type Association saved successfully");
				}
				modelAndView.addObject("EB01Id", eb01MappingVOToFetch.getEb01value());
				modelAndView.addObject("dataTypeId", eb01MappingVOToFetch.getDataTypeValue());
				modelAndView.addObject(DomainConstants.DATA_TYPE_VALUE_LIST, dataTypeVOList);
				modelAndView.addObject(DomainConstants.DATA_TYPE_VO_LIST_EDIT, dataTypeListForManageEdit);
				modelAndView.addObject("eb01",eb01ValueUpdate);
				modelAndView.addObject("eb01Desc",eb01DescUpdate);
				modelAndView.addObject("fromaction", "UPDATE");
				request.setAttribute("fromaction", "UPDATE");
				
			} else {
				List dataTypeVOList = referenceDataFacade.fetchDataTypeToEB01Mapping(eb01MappingVOToFetch);
				//List dataTypeListForManageEdit = referenceDataFacade.editDataTypeToEB01Mapping(eb01MappingVOToUpdate);
				modelAndView.addObject("EB01Id", eb01MappingVOToFetch.getEb01value());
				modelAndView.addObject("dataTypeId", eb01MappingVOToFetch.getDataTypeValue());
				modelAndView.addObject(DomainConstants.DATA_TYPE_VALUE_LIST, dataTypeVOList);
				modelAndView.addObject(DomainConstants.DATA_TYPE_VO_LIST_EDIT, dataTypeListToInsert);
				modelAndView.addObject("eb01",eb01ValueUpdate);
				modelAndView.addObject("eb01Desc",eb01DescUpdate);
				modelAndView.addObject(WebConstants.ERROR_MESSAGES, message);
				modelAndView.addObject("fromaction", "UPDATE");
				request.setAttribute("fromaction", "UPDATE");
			}
			return modelAndView;
		}
		
		/**
		 * Method to show the audit trail history of EB01 to data to data type association
		 * @param request
		 * @param response
		 * @return ModelAndView
		 */
		public ModelAndView viewHistoryOfDatatypeToEB01Mapping(HttpServletRequest request,
				HttpServletResponse response) {
			DataTypeToEB01MappingVO eb01MappingVOLocate = new DataTypeToEB01MappingVO();
			String eb01Locate = request
					.getParameter(DomainConstants.EB01);
			eb01MappingVOLocate.setEb01value(eb01Locate);
			List dataTypeToEb01HistoryList = referenceDataFacade
					.viewHistoryOfDatatypeToEB01Mapping(eb01MappingVOLocate);

			ModelAndView modelAndView = new ModelAndView(
					DomainConstants.VIEW_HISTORY_EB01_DTYPE);
			modelAndView.addObject("dataTypeToEb01HistoryList", dataTypeToEb01HistoryList);
			
			return modelAndView;
		}
		
		/**
		 * This method will get all the data type values from the page
		 * @param request
		 * @return List
		 */
		private List getAllDataTypesToSave(HttpServletRequest request) {
			List uniqueDataTypeList = new ArrayList();
			List dataTypeList = new ArrayList();
			if (null != request.getParameterValues(WebConstants.DATA_TYPE_VAL)) {
				int sizeOfDataType = request.getParameterValues(WebConstants.DATA_TYPE_VAL).length;
				String[] dataType = new String[sizeOfDataType];
				String[] dataTypeDescription = new String[sizeOfDataType];
				for (int i = 0; i < sizeOfDataType; i++) {

					if (!request.getParameterValues(WebConstants.DATA_TYPE_VAL)[i]
							.trim().equals("")) {

						dataType[i] = request.getParameterValues(WebConstants.DATA_TYPE_VAL)[i].trim();
						dataTypeDescription[i] = request.getParameterValues(WebConstants.DATA_TYPE_DESC)[i].trim();
						if (!uniqueDataTypeList.contains(dataType[i])) {
							uniqueDataTypeList.add(dataType[i]);
							DataTypeToEB01MappingVO dataTypeToEB01MappingVO = new DataTypeToEB01MappingVO();
							dataTypeToEB01MappingVO.setDataTypeValue(dataType[i]);
							dataTypeToEB01MappingVO.setDataTypeDescription(dataTypeDescription[i]);
							dataTypeList.add(dataTypeToEB01MappingVO);
						}
					}
				}
				
			}
			return dataTypeList;
		}
		

		// Manage EB01-Data Type Association -  END
		// Pop up for Header Rule and Data type in referance data page.
		public ModelAndView populateReferanceDataPopUp(HttpServletRequest request,
				HttpServletResponse response) throws EBXException {

			List referanceDataList = new ArrayList();
			String searchText = null;
			if (request.getParameter("searchText") != null) {
				searchText = request.getParameter("searchText");
			}
			String name = request.getParameter("referanceDataName");
			Map map = new HashMap();		
			if(searchText != null && !"".equals(searchText.trim())){
				searchText = searchText.trim().toUpperCase();
				searchText = searchText + "%";		
			}
			else {
					searchText = "%";
			}
			if(name != null){
		
				if(DomainConstants.RULE_ID.equals(name)){
					referanceDataList = new ArrayList();
					boolean autoCompleteFlag = false;
					List headerRuleList = referenceDataFacade.fetchHeaderRuleForAutoComplete(searchText,autoCompleteFlag);
					if (null != headerRuleList && !(DomainConstants.EMPTY.equals(headerRuleList))) {
						for (Iterator itr = headerRuleList.iterator(); itr.hasNext();) {
							HeaderRuleToEB03MappingVO mappingVO = (HeaderRuleToEB03MappingVO) itr.next();
							ReferanceDataPopUpVO referanceDataPopUpVO = new ReferanceDataPopUpVO(
									mappingVO.getHeaderRuleValue()
									,StringEscapeUtils.unescapeHtml(mappingVO.getHeaderRuleDescription()));
							referanceDataList.add(referanceDataPopUpVO);
						}						
					}
				}
				else if (DomainConstants.DATA_TYPE.equals(name)) {
					referanceDataList = new ArrayList();
					boolean autoCompleteFlag = false;
					List dataTypeList = referenceDataFacade.fetchDataTypeForAutoComplete(searchText,autoCompleteFlag);
					if (null != dataTypeList && !dataTypeList.isEmpty()) {
						for (Iterator itr = dataTypeList.iterator(); itr.hasNext();) {
							DataTypeToEB01MappingVO mappingVO = (DataTypeToEB01MappingVO) itr.next();
							ReferanceDataPopUpVO referanceDataPopUpVO = new ReferanceDataPopUpVO(
									mappingVO.getDataTypeValue()
									,StringEscapeUtils.unescapeHtml(mappingVO.getDataTypeDescription()));
							referanceDataList.add(referanceDataPopUpVO);
						}					
					}
				}
				map.put("referanceDataList", referanceDataList);
				map.put("referanceDataName", name);

			}
			return new ModelAndView("referancedatapopup",map);
		}
	// BXNI June Release Changes - Start
	/**
	 * Method to show the manage standard message page.
	 * @param request
	 * @param response
	 * @return ModelAndView 
	 */
	public ModelAndView showManageStandardMessagePage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView(DomainConstants.MANAGE_STANDARD_MSG_PAGE);
		request.setAttribute(DomainConstants.ACTIONS, DomainConstants.EMPTY);
		return modelAndView;
	}
	
	/**
	 * Method to create a new standard message.
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	public ModelAndView createStandardMessage(HttpServletRequest request, HttpServletResponse response) {
		StandardMessageVO standardMessageToFetch = new StandardMessageVO();
		StandardMessageVO standardMessageToCreate = new StandardMessageVO();
		
		String stdMessageToFetch = request.getParameter(DomainConstants.STD_MESSAGE_TO_FETCH_CREATE);
		String stdMessageToCreate = request.getParameter(DomainConstants.STD_MESSAGE_TO_SAVE_CREATE);
		if (validateStandardMessage(stdMessageToCreate)) {
			String userCommentsCreate = request.getParameter(DomainConstants.USER_COMMENTS_SAVE);
			standardMessageToFetch.setStandardMessage(stdMessageToCreate);
			standardMessageToCreate.setStandardMessage(stdMessageToCreate);
			standardMessageToCreate.setUserComments(userCommentsCreate);
			standardMessageToCreate.setLastUpdatedUser(request.getAttribute(SecurityConstants.USER_NAME).toString());
			List<StandardMessageVO> standardMessageVOList = referenceDataFacade.saveStandardMessage(standardMessageToFetch, standardMessageToCreate, true);
			BxUtil.encodeStandardMessage(standardMessageVOList);
			if(null != standardMessageToCreate.getOperationMessages() && !("").equals(standardMessageToCreate.getOperationMessages())){
				ModelAndView modelAndView = new ModelAndView(DomainConstants.MANAGE_STANDARD_MSG_PAGE);
				modelAndView.addObject(WebConstants.INFO_MESSAGES, standardMessageToCreate.getOperationMessages());
				modelAndView.addObject("StdMsg", request.getParameter(DomainConstants.STD_MESSAGE_TO_SAVE_CREATE));
				modelAndView.addObject(DomainConstants.STD_MSG_RESULT_LIST, standardMessageVOList);
				modelAndView.addObject("fromaction", "SELECT");
				request.setAttribute("fromaction", "SELECT");
				return modelAndView;
			} else {
				StandardMessageVO standardMessage = new StandardMessageVO();
				if (null != stdMessageToFetch && !DomainConstants.EMPTY.equals(stdMessageToFetch)) {
					standardMessage.setStandardMessage(stdMessageToFetch);	
				} else {
					standardMessage.setStandardMessage(stdMessageToCreate);
				}
				List<StandardMessageVO> standardMessageVO = referenceDataFacade.fetchStandardMessage(standardMessage);
				ModelAndView modelAndView = new ModelAndView(DomainConstants.MANAGE_STANDARD_MSG_PAGE);
				modelAndView.addObject("StdMsg", request.getParameter(DomainConstants.STD_MESSAGE_TO_SAVE_CREATE));
				modelAndView.addObject("StdMsgFetch", request.getParameter(DomainConstants.STD_MESSAGE_TO_FETCH_CREATE));
				modelAndView.addObject(DomainConstants.STD_MSG_RESULT_LIST, standardMessageVO);
				modelAndView.addObject(WebConstants.ERROR_MESSAGES, "Failed to create, standard message already exists.");
				modelAndView.addObject("fromaction", "SELECT");
				request.setAttribute("fromaction", "SELECT");
				return modelAndView;
			}
		} else {
			ModelAndView modelAndView = new ModelAndView(DomainConstants.MANAGE_STANDARD_MSG_PAGE);
			modelAndView.addObject("StdMsg", request.getParameter(DomainConstants.STD_MESSAGE_TO_SAVE_CREATE));
			modelAndView.addObject("StdMsgFetch", request.getParameter(DomainConstants.STD_MESSAGE_TO_FETCH_CREATE));
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, "The message can not be more than 75 characters.");
			return modelAndView;
		}
	}
	
	/**
	 * Method to fetch all the standard messages.
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	public ModelAndView fetchStandardMessage(HttpServletRequest request, HttpServletResponse response) {
		StandardMessageVO standardMessageToFetch = new StandardMessageVO();
		
		String standardMessage = request.getParameter(DomainConstants.STD_MESSAGE);
		standardMessageToFetch.setStandardMessage(standardMessage);
		List<StandardMessageVO> stdMessageVOSearchResult = referenceDataFacade.fetchStandardMessage(standardMessageToFetch);
		BxUtil.encodeStandardMessage(stdMessageVOSearchResult);
		
		ModelAndView modelAndView = new ModelAndView(DomainConstants.STD_MSG_RESULT_PAGE);
		modelAndView.addObject(DomainConstants.STD_MSG_RESULT_LIST, stdMessageVOSearchResult);
		modelAndView.addObject("StdMsg", standardMessageToFetch.getStandardMessage());
		modelAndView.addObject("StdMsgFetch", standardMessageToFetch.getStandardMessage());
		return modelAndView;
	}
	
	/**
	 * Delete the standard message.
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	public ModelAndView deleteStandardMessage(HttpServletRequest request, HttpServletResponse response) {
		StandardMessageVO standardMessageToFetch = new StandardMessageVO();
		StandardMessageVO standardMessageToDelete = new StandardMessageVO();
		
		String standardMessageFetch = request.getParameter(DomainConstants.STD_MESSAGE_TO_FETCH_DELETE);
		String standardMessageIdDelete = request.getParameter(DomainConstants.STD_MESSAGE_TO_DELETE);
		String userCommentsDelete = request.getParameter(DomainConstants.USER_COMMENTS_DELETE);
					
		standardMessageToFetch.setStandardMessage(standardMessageFetch);
		standardMessageToDelete.setStandardMessageId(standardMessageIdDelete);
		standardMessageToDelete.setUserComments(userCommentsDelete);
		standardMessageToDelete.setLastUpdatedUser(request.getAttribute(SecurityConstants.USER_NAME).toString());
		
		List standardMessageList = referenceDataFacade.deleteStandardMessage(standardMessageToFetch, standardMessageToDelete);
		BxUtil.encodeStandardMessage(standardMessageList);
		
		ModelAndView modelAndView = new ModelAndView(DomainConstants.MANAGE_STANDARD_MSG_PAGE);
		modelAndView.addObject("StdMsg", request.getParameter(DomainConstants.STD_MESSAGE_TO_FETCH_DELETE));
		modelAndView.addObject(DomainConstants.STD_MSG_RESULT_LIST, standardMessageList);
		modelAndView.addObject(WebConstants.INFO_MESSAGES, standardMessageToDelete.getOperationMessages());
		modelAndView.addObject("fromaction", "DELETE");
        request.setAttribute("fromaction", "DELETE");
		
		return modelAndView;
	}
	/**
	 * This method provide a provision to edit the standard message.
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	public ModelAndView editStandaredMessage(HttpServletRequest request, HttpServletResponse response) {
		
		String stdMsgIdEdit = request.getParameter(DomainConstants.STD_MESSAGE);
		String stdMsgEdit  = referenceDataFacade.editStandardMessage(stdMsgIdEdit);
		ModelAndView modelAndView = new ModelAndView(DomainConstants.STD_MESSAGE_EDIT_PAGE);
		modelAndView.addObject(DomainConstants.STD_MESSAGE_EDIT, stdMsgEdit);
		modelAndView.addObject(DomainConstants.STD_MSG_EDIT, stdMsgIdEdit);
		return modelAndView;
	}
	
	/**
	 * Method to save the changes in the standard message.
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	public ModelAndView saveStandardMessage(HttpServletRequest request, HttpServletResponse response) {
		StandardMessageVO standardMessageToFetch = new StandardMessageVO();
		StandardMessageVO standardMessageToSave = new StandardMessageVO();
		
		String stdMsgToFetch = request.getParameter(DomainConstants.STD_MESSAGE_TO_FETCH);
		String stdMsgIdToBeUpdate = request.getParameter(DomainConstants.STD_MESSAGE_TO_UPDATE);
		String stdMsgToSave = request.getParameter(DomainConstants.STD_MESSAGE_TO_SAVE);
		String userCommentsUpdate = request.getParameter(DomainConstants.USER_COMMENTS_SAVE);
		ModelAndView modelAndView = new ModelAndView(DomainConstants.MANAGE_STANDARD_MSG_PAGE);
		standardMessageToFetch.setStandardMessage(stdMsgToFetch);
		// validate the standard message to save
		if (validateStandardMessage(stdMsgToSave)) {
			standardMessageToSave.setStandardMessageId(stdMsgIdToBeUpdate);
			if (null != stdMsgToSave && !(DomainConstants.EMPTY.equals(stdMsgToSave))) {
				standardMessageToSave.setStandardMessage(stdMsgToSave);	
			}
			if (null != userCommentsUpdate && !(DomainConstants.EMPTY.equals(userCommentsUpdate))) {
				standardMessageToSave.setUserComments(userCommentsUpdate);	
			}
			standardMessageToSave.setLastUpdatedUser(request.getAttribute(SecurityConstants.USER_NAME).toString());
			List standardMessageVOList = referenceDataFacade.saveStandardMessage(standardMessageToFetch, standardMessageToSave, false);
			BxUtil.encodeStandardMessage(standardMessageVOList);
			if(null != standardMessageToSave.getOperationMessages() && !("").equals(standardMessageToSave.getOperationMessages())){
				modelAndView.addObject(WebConstants.INFO_MESSAGES, standardMessageToSave.getOperationMessages());
				modelAndView.addObject("StdMsg", request.getParameter(DomainConstants.STD_MESSAGE_TO_FETCH));
				modelAndView.addObject(DomainConstants.STD_MSG_RESULT_LIST, standardMessageVOList);
				modelAndView.addObject(DomainConstants.STD_MESSAGE_EDIT, standardMessageToSave.getStandardMessage());
				modelAndView.addObject(DomainConstants.STD_MSG_EDIT, standardMessageToSave.getStandardMessageId());
				modelAndView.addObject("fromaction", "UPDATE");
				request.setAttribute("fromaction", "UPDATE");
			} else {
				List stdMessageVOSearchResult = referenceDataFacade.fetchStandardMessage(standardMessageToFetch);
				BxUtil.encodeStandardMessage(stdMessageVOSearchResult);
				modelAndView.addObject("StdMsg", request.getParameter(DomainConstants.STD_MESSAGE_TO_FETCH));
				modelAndView.addObject(DomainConstants.STD_MSG_RESULT_LIST, stdMessageVOSearchResult);
				modelAndView.addObject(DomainConstants.STD_MESSAGE_EDIT, request.getParameter(DomainConstants.STD_MESSAGE_TO_SAVE));
				modelAndView.addObject(DomainConstants.STD_MSG_EDIT, request.getParameter(DomainConstants.STD_MESSAGE_TO_UPDATE));
				modelAndView.addObject(WebConstants.ERROR_MESSAGES, "Failed to save, standard message already exists.");
				modelAndView.addObject("fromaction", "UPDATE");
				request.setAttribute("fromaction", "UPDATE");
			}
		} else {
			List stdMessageVOSearchResult = referenceDataFacade.fetchStandardMessage(standardMessageToFetch);
			BxUtil.encodeStandardMessage(stdMessageVOSearchResult);
			modelAndView.addObject("StdMsg", request.getParameter(DomainConstants.STD_MESSAGE_TO_FETCH));
			modelAndView.addObject(DomainConstants.STD_MSG_RESULT_LIST, stdMessageVOSearchResult);
			modelAndView.addObject(DomainConstants.STD_MESSAGE_EDIT, request.getParameter(DomainConstants.STD_MESSAGE_TO_SAVE));
			modelAndView.addObject(DomainConstants.STD_MSG_EDIT, request.getParameter(DomainConstants.STD_MESSAGE_TO_UPDATE));
			modelAndView.addObject(WebConstants.ERROR_MESSAGES, "The message can not be more than 75 characters.");
			modelAndView.addObject("fromaction", "UPDATE");
			request.setAttribute("fromaction", "UPDATE");
		}
		return modelAndView;
	}

	/**
	 * Method to show the audit trail history standard message.
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	public ModelAndView viewHistoryOfStandardMessage(HttpServletRequest request,
			HttpServletResponse response) {
		
		List standardMessageHistory = referenceDataFacade.viewHistoryOfStandardMessage();
		ModelAndView modelAndView = new ModelAndView(DomainConstants.STD_MESSAGE_HISTORY_PAGE);
		modelAndView.addObject(DomainConstants.STD_MESSAGE_HISTORY_LIST, standardMessageHistory);
		return modelAndView;
	}
	
	
	/**
	 * Method to validate the length of the standard message.
	 * @param stdMsgToSave
	 * @return boolean
	 */
	private boolean validateStandardMessage(String stdMsgToSave) {
		if (null != stdMsgToSave && !DomainConstants.EMPTY.equals(stdMsgToSave)) {
			if (stdMsgToSave.length() <= 75) {
				return true;
			}
		}
		return false;
	}
	// BXNI June Release Changes - End
	
	/***********************************BXNI November Release Start****************************************************/
	/**
	 * Method to show the Service Type Code - EB11 mapping page
	 * @param request
	 * @param response
	 * @return ModelAndView 
	 */
	public ModelAndView showManageServiceTypeCodeToEB11Page(HttpServletRequest request,   HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView(DomainConstants.MANAGE_SRVC_TYP_EB11_PAGE);
		request.setAttribute(DomainConstants.ACTIONS, DomainConstants.EMPTY);
		return modelAndView;
	}
	
	/**
	 * Method to fetch all the LOB-MBU Associations corresponding to the Search Criteria
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	public ModelAndView fetchServiceTypeCodeToEB11Mapping(HttpServletRequest request, HttpServletResponse response){
		//VO which holds the Search Criteria entered by the user
		ServiceTypeMappingVO VOToFetch = new ServiceTypeMappingVO();
		String lobName = request.getParameter(DomainConstants.LOB_NAME);
		String mbuName = request.getParameter(DomainConstants.MBU_NAME);

		VOToFetch.setLineOfBusiness(lobName);
		VOToFetch.setCommaSeperatedMbu(mbuName);
		
		//Flow transferred to fetch the values from the db
		List<ServiceTypeMappingVO> serviceTypeMappingVOSearchResult = referenceDataFacade.fetchServiceTypeCodeToEB11Mapping(VOToFetch);

		List<ServiceTypeCodeToEB11VO> servicetypeMappingsList = new ArrayList<ServiceTypeCodeToEB11VO>();
		if(null != serviceTypeMappingVOSearchResult && !serviceTypeMappingVOSearchResult.isEmpty()){
			servicetypeMappingsList = serviceTypeMappingVOSearchResult.get(0).getServiceTypeCodeToEB11VOList();
		}
		//declaring a new ModelAndView object which loads the search result page
		ModelAndView modelAndView = new ModelAndView(DomainConstants.SRVC_TYP_EB11_RESULT_PAGE);
		//Setting the fetch result list to the ModelAndView object
		modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_FETCH_RESULT_LIST, serviceTypeMappingVOSearchResult);
		//Setting the Search criteria to the ModelAndView object to show it after the page refresh happens while fetching
		modelAndView.addObject("lobName", VOToFetch.getLineOfBusiness());
		modelAndView.addObject("mbuName", VOToFetch.getCommaSeperatedMbu());
		
		modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_VO_LIST, servicetypeMappingsList);
		
		return modelAndView;
		
	}
	/**
	 * Method to fetch mappings corresponding to a LOB, method is called on click of View icon
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	public ModelAndView viewServiceTypeCodeToEB11Mapping(HttpServletRequest request, HttpServletResponse response){
		String lobId = request.getParameter(DomainConstants.LOB_ID);
		
		ServiceTypeMappingVO VOToView = new ServiceTypeMappingVO();
		VOToView.setLobId(lobId);
		//Flow transferred to fetch the values from the db
		List<ServiceTypeMappingVO> serviceTypeMappingVOSearchResult = referenceDataFacade.viewServiceTypeCodeToEB11Mapping(VOToView);
		ModelAndView modelAndView = new ModelAndView(DomainConstants.SRVC_TYP_EB11_VIEW_PAGE);
		modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_RESULT_LIST, serviceTypeMappingVOSearchResult);
		List<ServiceTypeCodeToEB11VO> servicetypeMappingsList = new ArrayList<ServiceTypeCodeToEB11VO>();
		if(null != serviceTypeMappingVOSearchResult && !serviceTypeMappingVOSearchResult.isEmpty()){
			servicetypeMappingsList = serviceTypeMappingVOSearchResult.get(0).getServiceTypeCodeToEB11VOList();
		}
		modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_VO_LIST, servicetypeMappingsList);
		return modelAndView;
	}
	/**
	 * Method to delete mappings corresponding to a LOB, method is called on click of Delete icon
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	public ModelAndView deleteServiceTypeCodeToEB11Mapping (HttpServletRequest request, HttpServletResponse response){
		
		String lobId= request.getParameter("lobIdWhileDelete");
		String lobName= request.getParameter("lobNameWhileDelete");
		String userComments= request.getParameter("deleteAllUserComments");
		//Creating the VO Object to delete
		ServiceTypeMappingVO vOToDelete = new ServiceTypeMappingVO();
		vOToDelete.setLobId(lobId);
		vOToDelete.setLineOfBusiness(lobName);
		vOToDelete.setUserComments(userComments);
		vOToDelete.setLastUpdatedUser(request.getAttribute(SecurityConstants.USER_NAME).toString());
		//Creating the VO Object with the Search criteria, to fetch data from DB after saving
		ServiceTypeMappingVO vOToFetch = new ServiceTypeMappingVO();
		vOToFetch.setLineOfBusiness(request.getParameter("searchActionLobNameSave"));
		vOToFetch.setCommaSeperatedMbu(request.getParameter("searchActionMbuNameSave"));
		vOToFetch.setLobId(vOToDelete.getLobId());
		
		List<ServiceTypeMappingVO> serviceTypeMappingVOSearchResult = referenceDataFacade.deleteServiceTypeCodeToEB11Mapping(vOToDelete, vOToFetch);
		List<ServiceTypeCodeToEB11VO> servicetypeMappingsList = new ArrayList<ServiceTypeCodeToEB11VO>();
		if(null != serviceTypeMappingVOSearchResult && !serviceTypeMappingVOSearchResult.isEmpty()){
			servicetypeMappingsList = serviceTypeMappingVOSearchResult.get(0).getServiceTypeCodeToEB11VOList();
		}
		
		ModelAndView modelAndView = new ModelAndView(DomainConstants.MANAGE_SRVC_TYP_EB11_PAGE);
		
		//Setting the fetch result list to the ModelAndView object
		modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_FETCH_RESULT_LIST, serviceTypeMappingVOSearchResult);
		modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_VO_LIST, servicetypeMappingsList);
		modelAndView.addObject(WebConstants.INFO_MESSAGES,"Service Type Association for LOB '"+vOToDelete.getLineOfBusiness()+"' deleted successfully.");
		
		modelAndView.addObject("lobName", vOToFetch.getLineOfBusiness());
		modelAndView.addObject("mbuName", vOToFetch.getCommaSeperatedMbu());
		
		modelAndView.addObject("fromaction", "DELETE");
		request.setAttribute("fromaction", "DELETE");
		return modelAndView;
	}
	/**
	 * Method to load the Manage section to show the mappings in Edit mode, method is called on click of Edit button
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	public ModelAndView editServiceTypeCodeToEB11Mapping(HttpServletRequest request, HttpServletResponse response){
		String lobId = request.getParameter(DomainConstants.LOB_ID);
		
		ServiceTypeMappingVO VOToView = new ServiceTypeMappingVO();
		VOToView.setLobId(lobId);
		String action = request.getParameter("action");
		VOToView.setAction(action);
		//Flow transferred to fetch the values from the; db
		List<ServiceTypeMappingVO> serviceTypeMappingVOSearchResult = referenceDataFacade.viewServiceTypeCodeToEB11Mapping(VOToView);
		ModelAndView modelAndView = new ModelAndView(DomainConstants.SRVC_TYP_EB11_EDIT_PAGE);
		modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_RESULT_LIST, serviceTypeMappingVOSearchResult);
		List<ServiceTypeCodeToEB11VO> servicetypeMappingsList = new ArrayList<ServiceTypeCodeToEB11VO>();
		if(null != serviceTypeMappingVOSearchResult && !serviceTypeMappingVOSearchResult.isEmpty()){
			servicetypeMappingsList = serviceTypeMappingVOSearchResult.get(0).getServiceTypeCodeToEB11VOList();
		}
		modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_VO_LIST, servicetypeMappingsList);
		return modelAndView;
	}
	/**
	 * Method to save mappings corresponding to a LOB, method is called on click of Save button
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	public ModelAndView saveServiceTypeCodeToEB11Mapping(HttpServletRequest request, HttpServletResponse response){
		
		String lobId= request.getParameter("lobIdhidden") ;
		String action = request.getParameter("actionToSave");
		String userComments= request.getParameter("saveUserComments");
		String saveFromPopOut = request.getParameter("saveFromPopOut");	
		String ssbValue = request.getParameter("ssbValue");	
		//Creating the VO Object to Save
		ServiceTypeMappingVO vOToSave = new ServiceTypeMappingVO();
		
		vOToSave.setLobId(lobId);
		String lobName = request.getParameter("lobValueToSave");
		if(null != lobName && !lobName.isEmpty()){
			vOToSave.setLineOfBusiness(lobName.trim().toUpperCase());
		}
		String commaSeperatedMbu = request.getParameter("mbuValueToSave");
		if(null != commaSeperatedMbu && !commaSeperatedMbu.isEmpty()){
			List commaSeperatedMbuList = BxUtil.convertCSVToArrayList(commaSeperatedMbu);
			vOToSave.setCommaSeperatedMbu(BxUtil.getAsCSV(commaSeperatedMbuList).toUpperCase());
		}
		//Creating the ServicetypeMappingsVO Object list 
		List<ServiceTypeCodeToEB11VO> servicetypeMappingsListToSave = createServiceTypeMappingsObject(request);
		
		vOToSave.setServiceTypeCodeToEB11VOList(servicetypeMappingsListToSave);
		vOToSave.setLastUpdatedUser(request.getAttribute(SecurityConstants.USER_NAME).toString());
		vOToSave.setAction(action);
		vOToSave.setUserComments(userComments);
		vOToSave.setSsbIndicator(ssbValue);
		
		//Creating the VO Object with the Search criteria, to fetch data from DB after saving
		ServiceTypeMappingVO vOToFetch = new ServiceTypeMappingVO();
		vOToFetch.setLineOfBusiness(request.getParameter("searchActionLobNameSave"));
		vOToFetch.setCommaSeperatedMbu(request.getParameter("searchActionMbuNameSave"));
		vOToFetch.setLobId(vOToSave.getLobId());
		
		
		ModelAndView modelAndView = new ModelAndView(DomainConstants.MANAGE_SRVC_TYP_EB11_PAGE);
		if(!StringUtils.isBlank(saveFromPopOut) &&  saveFromPopOut.equals("true")){
			modelAndView.addObject("saveFromPopOut", saveFromPopOut);
		}
		
		List<ServiceTypeMappingVO> serviceTypeMappingVOAfterSave = referenceDataFacade.saveServiceTypeCodeToEB11Mapping(vOToSave,vOToFetch);
		List<ServiceTypeMappingVO> serviceTypeMappingVOSearchResult = referenceDataFacade.fetchServiceTypeCodeToEB11Mapping(vOToFetch);
		vOToFetch.setLobId(vOToSave.getLobId());
		List<ServiceTypeCodeToEB11VO> servicetypeMappingsList = new ArrayList<ServiceTypeCodeToEB11VO>();
		String operationMessagesAfterSave = "";
		if(null != serviceTypeMappingVOAfterSave && !serviceTypeMappingVOAfterSave.isEmpty()){
			operationMessagesAfterSave = serviceTypeMappingVOAfterSave.get(0).getOperationMessages();
			servicetypeMappingsList = serviceTypeMappingVOAfterSave.get(0).getServiceTypeCodeToEB11VOList();
			serviceTypeMappingVOAfterSave.get(0).setAction(action);
			
		}
		if(null == vOToSave.getValidationMessageList() || vOToSave.getValidationMessageList().isEmpty()){
			
			
			if(null != operationMessagesAfterSave && !("").equals(operationMessagesAfterSave)){
				if(operationMessagesAfterSave.equals(DomainConstants.NO_MODFCN_TO_SAVE)){
					modelAndView.addObject(WebConstants.WARNING_MESSAGES,DomainConstants.NO_MODFCN_TO_SAVE);
				}else{
					modelAndView.addObject(WebConstants.INFO_MESSAGES,DomainConstants.SAVE_SERVICE_TYPE_ASSN_SUCCESS_MSG);
				}
			}else{
				modelAndView.addObject(WebConstants.INFO_MESSAGES,DomainConstants.SAVE_SERVICE_TYPE_ASSN_SUCCESS_MSG);
			}
			modelAndView.addObject("lobName", vOToFetch.getLineOfBusiness());
			modelAndView.addObject("mbuName", vOToFetch.getCommaSeperatedMbu());
			modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_RESULT_LIST, serviceTypeMappingVOAfterSave);
			modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_VO_LIST, servicetypeMappingsList);
			modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_FETCH_RESULT_LIST, serviceTypeMappingVOSearchResult);
			
			modelAndView.addObject("fromaction", "UPDATE");
			request.setAttribute("fromaction", "UPDATE");
			
			return modelAndView;
		}else{
			modelAndView.addObject(WebConstants.ERROR_MESSAGES,vOToSave.getValidationMessageList());
			modelAndView.addObject("lobName", vOToFetch.getLineOfBusiness());
			modelAndView.addObject("mbuName", vOToFetch.getCommaSeperatedMbu());
			modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_RESULT_LIST, serviceTypeMappingVOAfterSave);
			modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_VO_LIST, servicetypeMappingsList);
			modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_FETCH_RESULT_LIST, serviceTypeMappingVOSearchResult);
			
			
			
			if(null != vOToSave.getAction() && !vOToSave.getAction().isEmpty() && vOToSave.getAction().equals("create")){
				modelAndView.addObject("fromaction", "ERRORWHILECREATE");
				request.setAttribute("fromaction", "ERRORWHILECREATE");
			}else if(null != vOToSave.getAction() && !vOToSave.getAction().isEmpty() && vOToSave.getAction().equals("update")){
				modelAndView.addObject("fromaction", "ERRORWHILEUPDATE");
				request.setAttribute("fromaction", "ERRORWHILEUPDATE");
			}else{
				modelAndView.addObject("fromaction", "UPDATE");
				request.setAttribute("fromaction", "UPDATE");
			}
			return modelAndView;
		}
		
	}
	/**
	 * Private Method to create the ServiceTypeCodeToEB11VOList with the Service type mappings from the page
	 * @param request
	 * @return ServiceTypeCodeToEB11VOList
	 */
	private List<ServiceTypeCodeToEB11VO> createServiceTypeMappingsObject(HttpServletRequest request){
		
		List<ServiceTypeCodeToEB11VO> servicetypeMappingsListToSave = new ArrayList<ServiceTypeCodeToEB11VO>();
		ServiceTypeCodeToEB11VO serviceTypeCodeToEB11VOToSave = null;
		int sizeOfMappings = 0;
		if(null !=  request.getParameterValues("serviceTypeCodeVal")){
			sizeOfMappings = request.getParameterValues("serviceTypeCodeVal").length;
		}  
		for(int i = 0; i < sizeOfMappings; i++){
			if (!request.getParameterValues("serviceTypeCodeVal")[i].trim().equals("")) {
			serviceTypeCodeToEB11VOToSave = new ServiceTypeCodeToEB11VO();
			
			String serviceTypeCodeVal = request.getParameterValues("serviceTypeCodeVal")[i];
			if(!StringUtils.isBlank(serviceTypeCodeVal)){
				 serviceTypeCodeToEB11VOToSave.setServiceTypeCode(serviceTypeCodeVal.trim().toUpperCase());
			}else{
				 serviceTypeCodeToEB11VOToSave.setServiceTypeCode("");
			}
			
			String serviceTypeCodeDesc = request.getParameterValues("serviceTypeCodeDesc")[i];
			if(!StringUtils.isBlank(serviceTypeCodeDesc)){
				serviceTypeCodeToEB11VOToSave.setServiceTypeCodeDesc(serviceTypeCodeDesc.trim().toUpperCase());
			}else{
				serviceTypeCodeToEB11VOToSave.setServiceTypeCodeDesc("");
			}
			
			String eb11Val = request.getParameterValues("eb11Val")[i];
			if(!StringUtils.isBlank(eb11Val)){
				 serviceTypeCodeToEB11VOToSave.setEb11(eb11Val.trim().toUpperCase());
			}else{
				 serviceTypeCodeToEB11VOToSave.setEb11("");
			}
			
			String eb11Desc = request.getParameterValues("eb11Desc")[i];
			if(!StringUtils.isBlank(eb11Desc)){
				serviceTypeCodeToEB11VOToSave.setEb11Desc(eb11Desc.trim().toUpperCase());
			}else{
				serviceTypeCodeToEB11VOToSave.setEb11Desc("");
			}
			
			String placeOfServiceVal = request.getParameterValues("placeOfServiceVal")[i];
			if(!StringUtils.isBlank(placeOfServiceVal)){
				serviceTypeCodeToEB11VOToSave.setPlaceOfService(placeOfServiceVal.trim().toUpperCase());
			}else{
				serviceTypeCodeToEB11VOToSave.setPlaceOfService("");
			}
			
			String placeOfServiceDesc = request.getParameterValues("placeOfServiceDesc")[i];
			if(!StringUtils.isBlank(placeOfServiceDesc)){
				serviceTypeCodeToEB11VOToSave.setPlaceOfServiceDesc(placeOfServiceDesc.trim().toUpperCase());
			}else{
				serviceTypeCodeToEB11VOToSave.setPlaceOfServiceDesc("");
			}
			
			 servicetypeMappingsListToSave.add(serviceTypeCodeToEB11VOToSave);
			}
		}
		return servicetypeMappingsListToSave;
		
	}
	
	/**
	 * Method to load the Manage section to show the mappings in Edit mode, method is called on click of Create button
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	public ModelAndView createServiceTypeCodeToEB11Mapping(HttpServletRequest request, HttpServletResponse response){
		String lobId = request.getParameter(DomainConstants.LOB_ID);
		String action = request.getParameter("action");
		//VO which holds the Search Criteria entered by the user. The search criteria to be transferred to the manage section in the create page
		ServiceTypeMappingVO vOToLoadCreateSection = new ServiceTypeMappingVO();
		String lobName = request.getParameter(DomainConstants.LOB_NAME);
		String mbuName = request.getParameter(DomainConstants.MBU_NAME);

		vOToLoadCreateSection.setLineOfBusiness(lobName);
		vOToLoadCreateSection.setCommaSeperatedMbu(mbuName);
		vOToLoadCreateSection.setAction(action);
		
		List<ServiceTypeMappingVO> serviceTypeMappingVOAfterSave = new ArrayList<ServiceTypeMappingVO>();
		serviceTypeMappingVOAfterSave.add(vOToLoadCreateSection);
		ModelAndView modelAndView = new ModelAndView(DomainConstants.SRVC_TYP_EB11_EDIT_PAGE);
		modelAndView.addObject(DomainConstants.SRVC_TYP_EB11_RESULT_LIST, serviceTypeMappingVOAfterSave);
		return  modelAndView;
	}
	
	
	public ModelAndView viewHistoryOfServiceTypeCodeToEB11Mapping (HttpServletRequest request, HttpServletResponse response){

		ServiceTypeMappingVO viewHistoryVO = new ServiceTypeMappingVO();
		String lineOfBusiness = request
				.getParameter(DomainConstants.LOB_NAME);
		if(!StringUtils.isBlank(lineOfBusiness)){
			viewHistoryVO.setLineOfBusiness(lineOfBusiness.toUpperCase().trim());
		}else{
			viewHistoryVO.setLineOfBusiness("");
		}
		
		List<ServiceTypeToEB11DataObject> serviceTypeCodeMappingAudtTrlList = referenceDataFacade
				.viewHistoryOfServiceTypeCodeToEB11Mapping(viewHistoryVO);

		ModelAndView modelAndView = new ModelAndView(
				DomainConstants.SERVICE_TYPE_MAPPING_VIEW_HISTORY);
		modelAndView.addObject("serviceTypeCodeMappingAudtTrlList", serviceTypeCodeMappingAudtTrlList);
		
		return modelAndView;
	
	}
	public ModelAndView serviceTypeMappingsPopOut(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView(DomainConstants.SRVC_TYP_EB11_EDIT_PAGE_POPOUT);
	}
	
	/***********************************BXNI November Release End****************************************************/
	
}
