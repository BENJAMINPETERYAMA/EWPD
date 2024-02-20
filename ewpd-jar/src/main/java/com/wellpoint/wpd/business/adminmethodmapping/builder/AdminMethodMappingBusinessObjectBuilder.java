/*
 * Created on Oct 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.adminmethodmapping.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminmethod.viewall.adapter.AdminmethodViewAllAdapterManager;
import com.wellpoint.wpd.business.adminmethod.viewall.bo.AdminMethodViewAllFilterBO;
import com.wellpoint.wpd.business.adminmethodmapping.adapter.AdminMethodMappingAdapterManager;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.common.adminmethodmapping.bo.AdminMethodMappingBO;
import com.wellpoint.wpd.common.adminmethodmapping.bo.AdminMethodMappingEntriesBO;
import com.wellpoint.wpd.common.adminmethodmapping.bo.QuestionAnswerBO;
import com.wellpoint.wpd.common.adminmethodmapping.bo.QuestionAnswerGroupBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.web.util.WPDStringUtil;

/**
 * @author U17066
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodMappingBusinessObjectBuilder {

	private AdminMethodMappingAdapterManager adminmethodMappingAdapterManager = new AdminMethodMappingAdapterManager();

	private SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();

	/**
	 * Method to create admin method mapping.
	 * 
	 * @param AdminMethodMaintainBO
	 * @return
	 * @throws SevereException
	 */
	public boolean createAdminMethodMapping(
			AdminMethodMappingBO adminmethodMappingBO) throws SevereException {

		Logger
				.logInfo("AdminMethodMaintainBusinessObjectBuilder - Create Admin Method Mapping");

		AdminMethodMappingEntriesBO adminMethodMappingEntriesBO = new AdminMethodMappingEntriesBO();

		List qualifierList = adminmethodMappingBO.getQualifierList();
		List pvaList = adminmethodMappingBO.getPvaList();
		List datatypeList = adminmethodMappingBO.getDatatypeList();
		List adminMethodSysIdList = new ArrayList();
		List processMethodList = new ArrayList();

		boolean status = false;
		AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		try {
			AdapterUtil.beginTransaction(serviceAccessEWPDB);
			AdapterUtil.logBeginTranstn(transactionId, this,
					"adminMethodMappingEntriesBO ");

			adminMethodMappingEntriesBO = populateBO(
					adminMethodMappingEntriesBO, adminmethodMappingBO);

			//Method to admin method sys id and sps id
			List adminMethodList = adminmethodMappingAdapterManager
					.getAdminMethodSysId(adminmethodMappingBO);

			for (Iterator iter = adminMethodList.iterator(); iter.hasNext();) {
				AdminMethodMappingBO adminMethodMappingDummyBo = (AdminMethodMappingBO) iter
						.next();
				adminMethodSysIdList.add(adminMethodMappingDummyBo
						.getAdminMethodSysId());
				processMethodList.add(adminMethodMappingDummyBo
						.getProcessMethod());
				adminmethodMappingBO.setProcessMethodList(processMethodList);
			}

			//If qualifierList,pvaList,datatypeList are null then create admin
			// method mapping.
			if (isAdminMethodAttributeNotPresent(qualifierList, pvaList,
					datatypeList)) {
				status = insertIfAllAdminMethodAttributeNotPresent(
						adminMethodMappingEntriesBO, adminMethodSysIdList,
						processMethodList);
			}
			//Create admin method mapping if qualifier list is empty ,pvalist
			// and datatypelist is not empty
			if (ifQualifierListEmpty(qualifierList, pvaList, datatypeList)) {
				status = insertIfQualifierListEmpty(
						adminMethodMappingEntriesBO, adminMethodSysIdList,
						processMethodList, pvaList, datatypeList);

			}
			//Create admin method mapping if pvalist list is empty
			// ,qualifierList and datatypelist is not empty
			if (ifPvaListEmpty(qualifierList, pvaList, datatypeList)) {
				status = insertIfPvaListEmpty(adminMethodMappingEntriesBO,
						adminMethodSysIdList, processMethodList, qualifierList,
						datatypeList);

			}
			//Create admin method mapping if datatypelist list is empty
			// ,qualifierList and pvaList is not empty
			if (ifDataTypeListEmpty(qualifierList, pvaList, datatypeList)) {
				status = insertIfdataTypeListEmpty(adminMethodMappingEntriesBO,
						adminMethodSysIdList, processMethodList, qualifierList,
						pvaList);
			}
			//Create admin method mapping if datatypelist and pvaList list is
			// empty ,qualifierList is not empty
			if (ifQualifierListNotEmpty(qualifierList, pvaList, datatypeList)) {
				status = insertIfQualifierListNotEmpty(
						adminMethodMappingEntriesBO, adminMethodSysIdList,
						processMethodList, qualifierList);

			}
			//Create admin method mapping if datatypelist and qualifierList
			// list is empty ,pvaList is not empty
			if (ifPvaListNotEmpty(qualifierList, pvaList, datatypeList))

			{
				status = insertIfPvaListNotEmpty(adminMethodMappingEntriesBO,
						adminMethodSysIdList, processMethodList, pvaList);

			}
			//Create admin method mapping if pvaList and qualifierList list is
			// empty ,datatypeList is not empty
			if (ifDataTypeListNotEmpty(qualifierList, pvaList, datatypeList)) {
				status = insertIfDataTypeListNotEmpty(
						adminMethodMappingEntriesBO, adminMethodSysIdList,
						processMethodList, datatypeList);

			}
			//Create admin method mapping if pvaList,qualifierList,datatypeList
			// is not empty

			if (ifAllAdminMethodAttributesPresent(qualifierList, pvaList,
					datatypeList)) {
				status = insertAllAdminMethodAttributesPresent(
						adminMethodMappingEntriesBO, adminMethodSysIdList,
						processMethodList, datatypeList, pvaList, qualifierList);
			}

			//Method to update is_admn_mthd_mapped flag to Y if questions are
			// answered.
			if (status == true) {
				if (adminMethodMappingEntriesBO.getQuestionNbrList() != null) {
					for (int i = 0; i < adminMethodMappingEntriesBO
							.getQuestionNbrList().size(); i++) {
						adminMethodMappingEntriesBO
								.setQuestionNumber(Integer
										.parseInt(adminMethodMappingEntriesBO
												.getQuestionNbrList().get(i)
												.toString()));
						status = adminmethodMappingAdapterManager
								.updateIsAdminMethdMappedFlag(adminMethodMappingEntriesBO);
					}
				}
			}

			//Method to insert possible answer list
			if (status == true) {
				if (adminmethodMappingBO.getProcessMethodList() != null
						&& adminmethodMappingBO.getQuestionAnswerList() != null) {
					for (int processMethodIdCounter = 0; processMethodIdCounter < adminmethodMappingBO
							.getProcessMethodList().size(); processMethodIdCounter++) {
						for (int questionAnswerCounter = 0; questionAnswerCounter < adminmethodMappingBO
								.getQuestionAnswerList().size(); questionAnswerCounter++) {
							adminmethodMappingBO
									.setAnswers(adminmethodMappingBO
											.getQuestionAnswerList().get(
													questionAnswerCounter)
											.toString());
							adminmethodMappingBO
									.setProcessMethod(adminmethodMappingBO
											.getProcessMethodList().get(
													processMethodIdCounter)
											.toString());
							status = adminmethodMappingAdapterManager
									.insertPossibleAnswer(adminmethodMappingBO);
						}
					}
				}

			}

			Logger
					.logInfo("AdminMethodMaintainBusinessObjectBuilder - Return from Create Admin Method Mapping");
			AdapterUtil.endTransaction(serviceAccessEWPDB);

			AdapterUtil.logTheEndTransaction(transactionId, this,
					"adminMethodMappingEntriesBO");
		} catch (AdapterException ex) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil.logAbortTxn(transactionId, this,
					"adminMethodMappingEntriesBO");
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in createAdminMethodMapping method , for creating the BusinessObject adminMethodMappingEntriesBO, in AdminMethodMappingBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception e) {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil.logAbortTxn(transactionId, this,
					"adminMethodMappingEntriesBO");
			throw new SevereException("Unhandled exception is caught", null, e);
		}

		return true;
	}

	/**
	 * Method used to populate AdminMethodMappingEntriesBO
	 * 
	 * @param adminMethodMappingEntriesBO
	 * @param adminmethodMappingBO
	 * @return
	 * @throws AdapterException
	 */
	private AdminMethodMappingEntriesBO populateBO(
			AdminMethodMappingEntriesBO adminMethodMappingEntriesBO,
			AdminMethodMappingBO adminmethodMappingBO) throws AdapterException {
		//adminmethodMappingAdapterManager.getAdminMethodSysId();

		adminMethodMappingEntriesBO.setCreatedUser(adminmethodMappingBO
				.getCreatedUser());
		adminMethodMappingEntriesBO.setLastUpdatedUser(adminmethodMappingBO
				.getLastUpdatedUser());
		adminMethodMappingEntriesBO.setCreatedDate(adminmethodMappingBO
				.getCreatedTimestamp());

		adminMethodMappingEntriesBO.setLastUpdatedDate(adminmethodMappingBO
				.getLastUpdatedTimestamp());
		adminMethodMappingEntriesBO.setComments(adminmethodMappingBO
				.getComments());

		adminMethodMappingEntriesBO.setPossibleAnswer(adminmethodMappingBO
				.getAnswers());
		adminMethodMappingEntriesBO.setQuestionNbrList(adminmethodMappingBO
				.getQuestionNbrList());
		adminMethodMappingEntriesBO.setTerm(adminmethodMappingBO.getTerm());
		return adminMethodMappingEntriesBO;
	}

	/**
	 * Create admin method mapping if pvaList,qualifierList,datatypeList is not
	 * empty
	 * 
	 * @param adminMethodMappingEntriesBO
	 * @param adminMethodSysIdList
	 * @param processMethodList
	 * @param datatypeList
	 * @param pvaList
	 * @param qualifierList
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	private boolean insertAllAdminMethodAttributesPresent(
			AdminMethodMappingEntriesBO adminMethodMappingEntriesBO,
			List adminMethodSysIdList, List processMethodList,
			List datatypeList, List pvaList, List qualifierList)
			throws SevereException, AdapterException {
		boolean status = false;
		for (int adminMethodSysIdCounter = 0; adminMethodSysIdCounter < adminMethodSysIdList
				.size(); adminMethodSysIdCounter++) {

			for (int qualifierCounter = 0; qualifierCounter < qualifierList
					.size(); qualifierCounter++) {

				for (int pvaCounter = 0; pvaCounter < pvaList.size(); pvaCounter++) {

					for (int dataTypeCounter = 0; dataTypeCounter < datatypeList
							.size(); dataTypeCounter++) {

						if (!datatypeList.isEmpty()) {
							adminMethodMappingEntriesBO
									.setDatatype(datatypeList.get(
											dataTypeCounter).toString());
						}
						if (!pvaList.isEmpty()) {
							adminMethodMappingEntriesBO.setPva(pvaList.get(
									pvaCounter).toString());
						}
						if (!qualifierList.isEmpty()) {
							adminMethodMappingEntriesBO
									.setQualifier(qualifierList.get(
											qualifierCounter).toString());
						}
						int filterCriteriaId = sequenceAdapterManager
								.getNextFilterCriteriaIDSequence();
						adminMethodMappingEntriesBO
								.setFilterCriteriaSysId(filterCriteriaId);
						adminMethodMappingEntriesBO
								.setAdminMethodSysId(adminMethodSysIdList.get(
										adminMethodSysIdCounter).toString());
						adminMethodMappingEntriesBO
								.setProcessMethod(processMethodList.get(
										adminMethodSysIdCounter).toString());

						status = adminmethodMappingAdapterManager
								.createAdminMethodMapping(adminMethodMappingEntriesBO);

					}

				}

			}
		}
		return status;
	}

	/**
	 * This Method return true if datatypeList,qualifierList,pvaList is not
	 * empty.
	 * 
	 * @param qualifierList
	 * @param pvaList
	 * @param datatypeList
	 * @return
	 */
	private boolean ifAllAdminMethodAttributesPresent(List qualifierList,
			List pvaList, List datatypeList) {
		return (!datatypeList.isEmpty() && !qualifierList.isEmpty() && !pvaList
				.isEmpty());
	}

	/**
	 * Create admin method mapping if pvaList and qualifierList list is empty
	 * ,datatypeList is not empty
	 * 
	 * @param adminMethodMappingEntriesBO
	 * @param adminMethodSysIdList
	 * @param processMethodList
	 * @param datatypeList
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	private boolean insertIfDataTypeListNotEmpty(
			AdminMethodMappingEntriesBO adminMethodMappingEntriesBO,
			List adminMethodSysIdList, List processMethodList, List datatypeList)
			throws SevereException, AdapterException {
		boolean status = false;
		for (int adminMethodSysIdCounter = 0; adminMethodSysIdCounter < adminMethodSysIdList
				.size(); adminMethodSysIdCounter++) {

			for (int dataTypeCounter = 0; dataTypeCounter < datatypeList.size(); dataTypeCounter++) {

				adminMethodMappingEntriesBO.setDatatype(datatypeList.get(
						dataTypeCounter).toString());

				int filterCriteriaId = sequenceAdapterManager
						.getNextFilterCriteriaIDSequence();
				adminMethodMappingEntriesBO
						.setFilterCriteriaSysId(filterCriteriaId);
				adminMethodMappingEntriesBO
						.setAdminMethodSysId(adminMethodSysIdList.get(
								adminMethodSysIdCounter).toString());
				adminMethodMappingEntriesBO.setProcessMethod(processMethodList
						.get(adminMethodSysIdCounter).toString());

				status = adminmethodMappingAdapterManager
						.createAdminMethodMapping(adminMethodMappingEntriesBO);

			}

		}
		return status;

	}

	/**
	 * This Method return true if pvaList and qualifierList is
	 * empty,datatypeList is not empty.
	 * 
	 * @param qualifierList
	 * @param pvaList
	 * @param datatypeList
	 * @return
	 */
	private boolean ifDataTypeListNotEmpty(List qualifierList, List pvaList,
			List datatypeList) {
		return (qualifierList.size() == 0 && pvaList.size() == 0 && datatypeList
				.size() != 0);
	}

	/**
	 * Create admin method mapping if datatypelist and qualifierList list is
	 * empty ,pvaList is not empty
	 * 
	 * @param adminMethodMappingEntriesBO
	 * @param adminMethodSysIdList
	 * @param processMethodList
	 * @param pvaList
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	private boolean insertIfPvaListNotEmpty(
			AdminMethodMappingEntriesBO adminMethodMappingEntriesBO,
			List adminMethodSysIdList, List processMethodList, List pvaList)
			throws SevereException, AdapterException {
		boolean status = false;
		for (int adminMethodSysIdCounter = 0; adminMethodSysIdCounter < adminMethodSysIdList
				.size(); adminMethodSysIdCounter++) {

			for (int pvaCounter = 0; pvaCounter < pvaList.size(); pvaCounter++) {

				adminMethodMappingEntriesBO.setPva(pvaList.get(pvaCounter)
						.toString());

				int filterCriteriaId = sequenceAdapterManager
						.getNextFilterCriteriaIDSequence();
				adminMethodMappingEntriesBO
						.setFilterCriteriaSysId(filterCriteriaId);
				adminMethodMappingEntriesBO
						.setAdminMethodSysId(adminMethodSysIdList.get(
								adminMethodSysIdCounter).toString());
				adminMethodMappingEntriesBO.setProcessMethod(processMethodList
						.get(adminMethodSysIdCounter).toString());

				status = adminmethodMappingAdapterManager
						.createAdminMethodMapping(adminMethodMappingEntriesBO);

			}

		}
		return status;

	}

	/**
	 * This Method return true if datatypeList and qualifierList is
	 * empty,pvaList is not empty.
	 * 
	 * @param qualifierList
	 * @param pvaList
	 * @param datatypeList
	 * @return
	 */
	private boolean ifPvaListNotEmpty(List qualifierList, List pvaList,
			List datatypeList) {
		return (qualifierList.size() == 0 && pvaList.size() != 0 && datatypeList
				.size() == 0);
	}

	/**
	 * Create admin method mapping if datatypelist and pvaList list is empty
	 * ,qualifierList is not empty
	 * 
	 * @param adminMethodMappingEntriesBO
	 * @param adminMethodSysIdList
	 * @param processMethodList
	 * @param qualifierList
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	private boolean insertIfQualifierListNotEmpty(
			AdminMethodMappingEntriesBO adminMethodMappingEntriesBO,
			List adminMethodSysIdList, List processMethodList,
			List qualifierList) throws SevereException, AdapterException {
		boolean status = false;
		for (int adminMethodSysIdCounter = 0; adminMethodSysIdCounter < adminMethodSysIdList
				.size(); adminMethodSysIdCounter++) {

			for (int qualifierCounter = 0; qualifierCounter < qualifierList
					.size(); qualifierCounter++) {

				adminMethodMappingEntriesBO.setQualifier(qualifierList.get(
						qualifierCounter).toString());

				int filterCriteriaId = sequenceAdapterManager
						.getNextFilterCriteriaIDSequence();
				adminMethodMappingEntriesBO
						.setFilterCriteriaSysId(filterCriteriaId);
				adminMethodMappingEntriesBO
						.setAdminMethodSysId(adminMethodSysIdList.get(
								adminMethodSysIdCounter).toString());
				adminMethodMappingEntriesBO.setProcessMethod(processMethodList
						.get(adminMethodSysIdCounter).toString());

				status = adminmethodMappingAdapterManager
						.createAdminMethodMapping(adminMethodMappingEntriesBO);

			}
		}
		return status;
	}

	/**
	 * This Method return true if datatypeList and pvaList is
	 * empty,qualifierList is not empty.
	 * 
	 * @param qualifierList
	 * @param pvaList
	 * @param datatypeList
	 * @return
	 */
	private boolean ifQualifierListNotEmpty(List qualifierList, List pvaList,
			List datatypeList) {
		return (qualifierList.size() != 0 && pvaList.size() == 0 && datatypeList
				.size() == 0);

	}

	/**
	 * Create admin method mapping if datatypelist list is empty ,qualifierList
	 * and pvaList is not empty
	 * 
	 * @param adminMethodMappingEntriesBO
	 * @param adminMethodSysIdList
	 * @param processMethodList
	 * @param qualifierList
	 * @param datatypeList
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	private boolean insertIfdataTypeListEmpty(
			AdminMethodMappingEntriesBO adminMethodMappingEntriesBO,
			List adminMethodSysIdList, List processMethodList,
			List qualifierList, List pvaList) throws SevereException,
			AdapterException {
		boolean status = false;
		for (int adminMethodSysIdCounter = 0; adminMethodSysIdCounter < adminMethodSysIdList
				.size(); adminMethodSysIdCounter++) {

			for (int qualifierCounter = 0; qualifierCounter < qualifierList
					.size(); qualifierCounter++) {

				for (int pvaCounter = 0; pvaCounter < pvaList.size(); pvaCounter++) {

					adminMethodMappingEntriesBO.setPva(pvaList.get(pvaCounter)
							.toString());

					adminMethodMappingEntriesBO.setQualifier(qualifierList.get(
							qualifierCounter).toString());

					int filterCriteriaId = sequenceAdapterManager
							.getNextFilterCriteriaIDSequence();
					adminMethodMappingEntriesBO
							.setFilterCriteriaSysId(filterCriteriaId);
					adminMethodMappingEntriesBO
							.setAdminMethodSysId(adminMethodSysIdList.get(
									adminMethodSysIdCounter).toString());
					adminMethodMappingEntriesBO
							.setProcessMethod(processMethodList.get(
									adminMethodSysIdCounter).toString());

					status = adminmethodMappingAdapterManager
							.createAdminMethodMapping(adminMethodMappingEntriesBO);

				}

			}
		}
		return status;
	}

	/**
	 * This Method return true if datatypeList is empty,qualifierList and
	 * pvaList is not empty.
	 * 
	 * @param qualifierList
	 * @param pvaList
	 * @param datatypeList
	 * @return
	 */
	private boolean ifDataTypeListEmpty(List qualifierList, List pvaList,
			List datatypeList) {
		return (qualifierList.size() != 0 && pvaList.size() != 0 && datatypeList
				.size() == 0);

	}

	/**
	 * Create admin method mapping if pvalist list is empty ,qualifierList and
	 * datatypelist is not empty
	 * 
	 * @param adminMethodMappingEntriesBO
	 * @param adminMethodSysIdList
	 * @param processMethodList
	 * @param pvaList
	 * @param datatypeList
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	private boolean insertIfPvaListEmpty(
			AdminMethodMappingEntriesBO adminMethodMappingEntriesBO,
			List adminMethodSysIdList, List processMethodList,
			List qualifierList, List datatypeList) throws SevereException,
			AdapterException {
		boolean status = false;
		for (int adminMethodSysIdCounter = 0; adminMethodSysIdCounter < adminMethodSysIdList
				.size(); adminMethodSysIdCounter++) {

			for (int qualifierCounter = 0; qualifierCounter < qualifierList
					.size(); qualifierCounter++) {

				for (int dataTypeCounter = 0; dataTypeCounter < datatypeList
						.size(); dataTypeCounter++) {

					adminMethodMappingEntriesBO.setDatatype(datatypeList.get(
							dataTypeCounter).toString());

					adminMethodMappingEntriesBO.setQualifier(qualifierList.get(
							qualifierCounter).toString());

					int filterCriteriaId = sequenceAdapterManager
							.getNextFilterCriteriaIDSequence();
					adminMethodMappingEntriesBO
							.setFilterCriteriaSysId(filterCriteriaId);
					adminMethodMappingEntriesBO
							.setAdminMethodSysId(adminMethodSysIdList.get(
									adminMethodSysIdCounter).toString());
					adminMethodMappingEntriesBO
							.setProcessMethod(processMethodList.get(
									adminMethodSysIdCounter).toString());

					status = adminmethodMappingAdapterManager
							.createAdminMethodMapping(adminMethodMappingEntriesBO);

				}

			}
		}
		return status;

	}

	/**
	 * This Method return true if pvaList is empty,qualifierList and
	 * datatypeList is not empty.
	 * 
	 * @param qualifierList
	 * @param pvaList
	 * @param datatypeList
	 * @return
	 */
	private boolean ifPvaListEmpty(List qualifierList, List pvaList,
			List datatypeList) {
		return (qualifierList.size() != 0 && pvaList.size() == 0 && datatypeList
				.size() != 0);

	}
/**
	 * Create admin method mapping if qualifier list is empty ,pvalist and
	 * datatypelist is not empty
	 * 
	 * @param sequenceAdapterManager
	 * @param adminMethodMappingEntriesBO
	 * @param adminMethodSysIdList
	 * @param processMethodList
	 * @param pvaList
	 * @param datatypeList
	 * @throws SevereException
	 * @throws AdapterException
	 */
	private boolean insertIfQualifierListEmpty(

	AdminMethodMappingEntriesBO adminMethodMappingEntriesBO,
			List adminMethodSysIdList, List processMethodList, List pvaList,
			List datatypeList) throws SevereException, AdapterException {

		boolean status = false;
		for (int adminMethodSysIdCounter = 0; adminMethodSysIdCounter < adminMethodSysIdList
				.size(); adminMethodSysIdCounter++) {

			for (int pvaCounter = 0; pvaCounter < pvaList.size(); pvaCounter++) {

				for (int dataTypeCounter = 0; dataTypeCounter < datatypeList
						.size(); dataTypeCounter++) {

					adminMethodMappingEntriesBO.setDatatype(datatypeList.get(
							dataTypeCounter).toString());

					adminMethodMappingEntriesBO.setPva(pvaList.get(pvaCounter)
							.toString());

					int filterCriteriaId = sequenceAdapterManager
							.getNextFilterCriteriaIDSequence();
					adminMethodMappingEntriesBO
							.setFilterCriteriaSysId(filterCriteriaId);
					adminMethodMappingEntriesBO
							.setAdminMethodSysId(adminMethodSysIdList.get(
									adminMethodSysIdCounter).toString());
					adminMethodMappingEntriesBO
							.setProcessMethod(processMethodList.get(
									adminMethodSysIdCounter).toString());

					status = adminmethodMappingAdapterManager
							.createAdminMethodMapping(adminMethodMappingEntriesBO);

				}

			}
		}
		return status;
	}

	/**
	 * This Method return true if qualifierList is empty,pvaList and
	 * datatypeList is not empty.
	 * 
	 * @param qualifierList
	 * @param pvaList
	 * @param datatypeList
	 * @return
	 */
	private boolean ifQualifierListEmpty(List qualifierList, List pvaList,
			List datatypeList) {
		return (qualifierList.size() == 0 && pvaList.size() != 0 && datatypeList
				.size() != 0);

	}

	/**
	 * Create admin method mapping if pvalist,qualifierlist,datatypelist is
	 * empty.
	 * 
	 * @param sequenceAdapterManager
	 * @param adminMethodMappingEntriesBO
	 * @param processMethodList
	 * @param adminMethodSysIdList
	 * @throws AdapterException
	 * @throws SevereException
	 */
	private boolean insertIfAllAdminMethodAttributeNotPresent(

	AdminMethodMappingEntriesBO adminMethodMappingEntriesBO,
			List adminMethodSysIdList, List processMethodList)
			throws AdapterException, SevereException {
		boolean status = false;
		for (int adminMethodSysIdCounter = 0; adminMethodSysIdCounter < adminMethodSysIdList
				.size(); adminMethodSysIdCounter++) {
			int filterCriteriaId = sequenceAdapterManager
					.getNextFilterCriteriaIDSequence();
			adminMethodMappingEntriesBO
					.setFilterCriteriaSysId(filterCriteriaId);
			adminMethodMappingEntriesBO
					.setAdminMethodSysId(adminMethodSysIdList.get(
							adminMethodSysIdCounter).toString());
			adminMethodMappingEntriesBO.setProcessMethod(processMethodList.get(
					adminMethodSysIdCounter).toString());

			status = adminmethodMappingAdapterManager
					.createAdminMethodMapping(adminMethodMappingEntriesBO);
		}
		return status;
	}

	/**
	 * Method returns true if all three lists will be null
	 * 
	 * @param qualifierList
	 * @param pvaList
	 * @param datatypeList
	 * @return
	 */
	private boolean isAdminMethodAttributeNotPresent(List qualifierList,
			List pvaList, List datatypeList) {
		return (qualifierList.size() == 0 && pvaList.size() == 0 && datatypeList
				.size() == 0);

	}
	/**
	 * @param AdminMethodMaintainBO
	 * @throws SevereException
	 */
	public void deleteAdminMethodMapping(
			AdminMethodMappingBO adminmethodMappingBO, User user)
			throws SevereException {
		try {
			adminmethodMappingAdapterManager.deleteAdminMethodMapping(
					adminmethodMappingBO, user);
		} catch (AdapterException ex) {

			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in getAdminMethodSysId method , for adminmethodMappingBO,in AdminMethodMappingBusinessObjectBuilder ",
					errorParams, ex);
		} catch (Exception e) {
			throw new SevereException("Unhandled exception is caught", null, e);
		}
	}

	/**Method to search Question Answer
	 * @param searchCriteria
	 * @return
	 */
	public List searchQuesAnswer(String searchCriteria) throws SevereException {
		// TODO Auto-generated method stub
		AdminMethodMappingAdapterManager adminMethodMappingAdapterManager = new AdminMethodMappingAdapterManager();
		List searchResults = new ArrayList();
		List searchResultsNew = new ArrayList();
		Logger
				.logInfo("AdminMethodMaintainBusinessObjectBuilder - Search Question Answer");
		try {
			searchResults = adminMethodMappingAdapterManager
					.retrieveQuesAnswer(searchCriteria);

			Logger.logDebug(searchResultsNew);

		} catch (AdapterException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate Search Question Answer method in AdminMethodMaintainBusinessObjectBuilder",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate Search Question Answer method in AdminMethodMaintainBusinessObjectBuilder",
					null, ex);
		}
		return searchResults;
	}

	/**
	 * Method to Search Admin Method Mapping.
	 * 
	 * @param adminMethodMappingBO
	 * @return
	 * @throws SevereException
	 */
	public List adminMethodMappingSearchList(
			AdminMethodMappingBO adminMethodMappingBO) throws SevereException {
		Logger
				.logInfo("AdminMethodMappingBusinessObjectBuilder - Entering Search Admin Method Mapping List");
		AdminMethodMappingAdapterManager adminMethodMappingAdapterManager = new AdminMethodMappingAdapterManager();
		try {
			adminMethodMappingBO.setDescription((WPDStringUtil.addDelimiterForAMDesc(adminMethodMappingBO.getDescription())));
			List searchResults = (List) adminMethodMappingAdapterManager
					.searchAdminMethodMapping(adminMethodMappingBO);
			if (!adminMethodMappingBO.isSearchDuplicateFlag()) {
				if (!((null == searchResults) || searchResults.size() <= 0)) {
					if (adminMethodMappingBO.isEditFlag()) {

						searchResults = groupAdminMethodsForEdit(searchResults);

					} else {
						searchResults = groupAdminMethods(searchResults);
					}
				}
				Logger
						.logInfo("AdminMethodMappingBusinessObjectBuilder - Return from Search Admin Method Mapping List");

			}
			return searchResults;
		} catch (AdapterException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate searchAdminMethod Mapping List in AdminMethodMappingBusinessObjectBuilder",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate searchAdminMethod Mapping List in AdminMethodMappingBusinessObjectBuilder",
					null, ex);
		}

	}
	/**
	 * Method is used to populate the admin method filter BO with multiple
	 * qualifiers, PVA and datatype as com separated values.
	 * 
	 * @param adminMethodList
	 * @return Grouped adminmethod List
	 */
	private List groupAdminMethods(List adminMethodList) {

		Logger
				.logInfo("AdminMethodMappingBusinessObjectBuilder - Grouping admin methods, combining pva, qualifier and datatype.");

		HashMap adminMethodMap = new HashMap();

		for (Iterator iter = adminMethodList.iterator(); iter.hasNext();) {
			AdminMethodMappingBO adminMethodMappingBO = (AdminMethodMappingBO) iter
					.next();
			if (adminMethodMappingBO.getTerm() != null) {
				String[] term = adminMethodMappingBO.getTerm().split("~");
				String termComma = getCommaSeparated(term);
				adminMethodMappingBO.setTerm(termComma);
			}
			if (adminMethodMap.containsKey(new Integer(adminMethodMappingBO
					.getAdminMethodSysId()))) {

				AdminMethodMappingBO adminMethodMappingMapBO = new AdminMethodMappingBO();
				// Data in the Map
				adminMethodMappingMapBO = (AdminMethodMappingBO) adminMethodMap
						.get(new Integer(adminMethodMappingBO
								.getAdminMethodSysId()));

				if (!isAttributeMatch(adminMethodMappingBO.getQualifier(),
						adminMethodMappingMapBO.getQualifier())) {
					if (null != adminMethodMappingMapBO.getQualifier()
							&& null != adminMethodMappingBO.getQualifier()
							&& !"".equals(adminMethodMappingMapBO
									.getQualifier())
							&& !"".equals(adminMethodMappingBO.getQualifier()))
						adminMethodMappingMapBO
								.setQualifier(adminMethodMappingMapBO
										.getQualifier()
										+ ", "
										+ adminMethodMappingBO.getQualifier());
					continue;
				}
				if (!isAttributeMatch(adminMethodMappingBO.getDatatype(),
						adminMethodMappingMapBO.getDatatype())) {
					if (null != adminMethodMappingMapBO.getDatatype()
							&& null != adminMethodMappingBO.getDatatype()
							&& !""
									.equals(adminMethodMappingMapBO
											.getDatatype())
							&& !"".equals(adminMethodMappingBO.getDatatype()))
						adminMethodMappingMapBO
								.setDatatype(adminMethodMappingMapBO
										.getDatatype()
										+ ", "
										+ adminMethodMappingBO.getDatatype());
					continue;
				}
				if (!isAttributeMatch(adminMethodMappingBO.getPvaid(),
						adminMethodMappingMapBO.getPvaid())) {
					if (null != adminMethodMappingMapBO.getPvaid()
							&& null != adminMethodMappingBO.getPvaid()
							&& !"".equals(adminMethodMappingMapBO.getPvaid())
							&& !"".equals(adminMethodMappingBO.getPvaid()))
						adminMethodMappingMapBO
								.setPvaid(adminMethodMappingMapBO.getPvaid()
										+ ", " + adminMethodMappingBO.getPvaid());
					continue;
				}
			}

			else
				adminMethodMap.put(new Integer(adminMethodMappingBO
						.getAdminMethodSysId()), adminMethodMappingBO);

		}
		Logger
				.logInfo("AdminMethodMappingBusinessObjectBuilder - Return From Grouping admin methods, combining pva, qualifier and datatype.");
		return new ArrayList(adminMethodMap.values());

	}

	/**
	 * Method to group admin methods For Edit
	 * 
	 * @param adminMethodList
	 * @return
	 */
	private List groupAdminMethodsForEdit(List adminMethodList) {
		//TODO

		Logger
				.logInfo("AdminMethodMappingBusinessObjectBuilder - Grouping admin methods, combining pva, qualifier and datatype.");
		ArrayList list = new ArrayList();
		AdminMethodMappingBO adminMethodMappingnewBO = new AdminMethodMappingBO();
		HashSet qualifier = new HashSet();
		HashSet term = new HashSet();
		HashSet pva = new HashSet();
		HashSet datatype = new HashSet();
		HashMap adminMethodSysIdMap = new HashMap();

		for (Iterator iter = adminMethodList.iterator(); iter.hasNext();) {
			AdminMethodMappingBO adminMethodMappingBO = (AdminMethodMappingBO) iter
					.next();

			if (adminMethodMappingBO.getQualifierid() != null
					&& adminMethodMappingBO.getQualifier() != null) {
				qualifier.add(adminMethodMappingBO.getQualifierid() + "~"
						+ adminMethodMappingBO.getQualifier());
				adminMethodMappingnewBO.setQualifier(getTildaString(qualifier));
			}
			if (adminMethodMappingBO.getTerm() != null) {
				term.add(adminMethodMappingBO.getTerm());
				adminMethodMappingnewBO.setTerm(getTildaStringForTerm(term));
			}
			if (adminMethodMappingBO.getPvaid() != null && adminMethodMappingBO.getPva() != null) {
				pva.add(adminMethodMappingBO.getPva()+ "~" + adminMethodMappingBO.getPvaid());
				adminMethodMappingnewBO.setPva(getTildaString(pva));
			}
			if (adminMethodMappingBO.getDatatypeid() != null
					&& adminMethodMappingBO.getDatatype() != null) {
				datatype.add(adminMethodMappingBO.getDatatypeid() + "~"
						+ adminMethodMappingBO.getDatatype());
				adminMethodMappingnewBO.setDatatype(getTildaString(datatype));

			}

			adminMethodMappingnewBO.setCreatedUser(adminMethodMappingBO
					.getCreatedUser());
			adminMethodMappingnewBO.setCreatedDate(adminMethodMappingBO
					.getCreatedDate());
			adminMethodMappingnewBO.setLastUpdatedDate(adminMethodMappingBO
					.getLastUpdatedDate());
			adminMethodMappingnewBO.setLastUpdatedUser(adminMethodMappingBO
					.getLastUpdatedUser());
			adminMethodMappingnewBO.setComments(adminMethodMappingBO
					.getComments());
			adminMethodMappingnewBO.setProcessMethodDesc(adminMethodMappingBO
					.getProcessMethodDesc());
			adminMethodMappingnewBO.setProcessMethod(adminMethodMappingBO
					.getProcessMethod());
			adminMethodMappingnewBO.setAdminMethodNo(adminMethodMappingBO
					.getAdminMethodNo()
					+ '~'
					+ adminMethodMappingBO.getDescription()
					+ '~'
					+ adminMethodMappingBO.getAdminMethodSysId());
			adminMethodMappingnewBO.setAdminMethodSysId(adminMethodMappingBO
					.getAdminMethodSysId());

		}
		list.add(adminMethodMappingnewBO);
		return list;

	}

	/**
	 * Method to get tilda separeted String.
	 * 
	 * @return
	 */
	private String getTildaString(HashSet item) {
		// TODO Auto-generated method stub

		Iterator i = item.iterator();
		StringBuffer sb = new StringBuffer();

		while (i.hasNext()) {
			sb.append((i.next()).toString()).append('~');
		}

		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	/**
	 * Method to get tilda separeted String for Term
	 * 
	 * @return
	 */
	private String getTildaStringForTerm(HashSet item) {
		// TODO Auto-generated method stub

		Iterator i = item.iterator();
		StringBuffer sb = new StringBuffer();

		while (i.hasNext()) {
			sb.append((i.next()).toString());
		}

		return sb.toString();
	}

	/**
	 * @param Method
	 *            to View Admin Method Mapping
	 * @return
	 * @throws SevereException
	 */
	public List viewAdminMethodMapping(AdminMethodMappingBO adminMethodMappingBO)
			throws SevereException {
		Logger
				.logInfo("AdminMethodMappingBusinessObjectBuilder - View Admin Method Mapping");
		AdminMethodMappingAdapterManager adminMethodMappingAdapterManager = new AdminMethodMappingAdapterManager();
		try {
			List searchResults = (List) adminMethodMappingAdapterManager
					.viewAdminMethodMapping(adminMethodMappingBO);
			if (((null != searchResults) || searchResults.size() > 0)) {
				searchResults = groupAdminMethods(searchResults);
			}
			for (Iterator iter = searchResults.iterator(); iter.hasNext();) {

				AdminMethodMappingBO adminMethodMappingMapBO = (AdminMethodMappingBO) iter
						.next();
				List answerList = adminMethodMappingAdapterManager
						.getAnswerList(Integer.parseInt(adminMethodMappingMapBO
								.getAdminMethodSysId()));
				List comaSeperatedAnswerList = getComaSeperatedAnswerList(answerList);
				if (comaSeperatedAnswerList != null
						&& comaSeperatedAnswerList.size() > 0) {
					List questionAnswerList = adminMethodMappingAdapterManager
							.getQuestionAnswerList(comaSeperatedAnswerList);
					adminMethodMappingMapBO
							.setQuestionAnswerList(groupQuestionAnswers(questionAnswerList));
				}
			}
			return searchResults;

		} catch (AdapterException ad) {
			List errorParams = new ArrayList(2);
			String obj = ad.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in viewAdminMethodMapping Method in AdminMethodMappingBusinessObjectBuilder",
					errorParams, ad);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate viewAdminMethodMapping Method  in AdminMethodMappingBusinessObjectBuilder",
					null, ex);
		}

	}

	/**
	 * Method is used to split the coma separated possible answers and make it
	 * into a list without duplicates.
	 * 
	 * @param answerList
	 * @return comaSepratedAnswerList
	 */
	private List getComaSeperatedAnswerList(List answerList) {
		List comaSeperatedAnswerList = null;
		if (null != answerList && !answerList.isEmpty()) {
			Logger
					.logInfo("AdminMethodMappingBusinessObjectBuilder - Getting coma seperated answer list");

			String possibleAnswer = "";
			comaSeperatedAnswerList = new ArrayList();
			for (Iterator iter = answerList.iterator(); iter.hasNext();) {
				AdminMethodMappingBO element = (AdminMethodMappingBO) iter
						.next();
				if (!("".equals(element.getAnswers()))
						&& element.getAnswers() != null) {
					String[] answer = element.getAnswers().split("\\,");

					for (int i = 0; i < answer.length; i++) {
						if (!"null".equals(answer[i]))
							comaSeperatedAnswerList.add(answer[i]);
					}
				}
			}
			Set hashSet = new HashSet(comaSeperatedAnswerList);
			//HashSet hashSet = new HashSet(comaSeperatedAnswerList);
			comaSeperatedAnswerList.clear();
			comaSeperatedAnswerList.addAll(hashSet);

		}
		return comaSeperatedAnswerList;
	}

	
	
	private List getComaSeperatedAnswerListForEdit(String answers) {

		Logger
				.logInfo("AdminmethodViewAllBusinessObjectBuilder - Getting coma seperated answer list");

		String possibleAnswer = "";
		List comaSeperatedAnswerList = new ArrayList();
		
			if(answers !=null){
				String[] answer = answers.split("\\,");
				for (int i = 0; i < answer.length; i++) {
					comaSeperatedAnswerList.add(answer[i]);
				}
			}
		
		HashSet hashSet = new HashSet(comaSeperatedAnswerList);
		comaSeperatedAnswerList.clear();
		comaSeperatedAnswerList.addAll(hashSet);

		return comaSeperatedAnswerList;
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

		Logger
				.logInfo("AdminMethodMappingBusinessObjectBuilder - Comparing two attributes");

		if (null != attr1 && !"".equals(attr1) && null != attr2
				&& !"".equals(attr2)) {
			//			if (attr1.equalsIgnoreCase(attr2))
			String[] array = attr2.split("\\,");

			for (int i = 0; i < array.length; i++) {
				if (array[i].trim().equalsIgnoreCase(attr1))
					return true;
			}
			return false;
		}
		return true;

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
				if (!isAttributeMatch(answerBO.getPossibleAnswer(), questionAnswerBO
						.getPossibleAnswer())) {

					answerBO.setPossibleAnswer(answerBO.getPossibleAnswer()
							+ "," + questionAnswerBO.getPossibleAnswer());
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

	private String getCommaSeparated(String[] term) {

		if (null != term && term.length > 0) {

			StringBuffer termComma = new StringBuffer();
			for (int i = 0; i < term.length; i = i + 2) {
				termComma.append(term[i]).append(", ");
			}
			termComma.deleteCharAt(termComma.length() - 2);
			return termComma.toString();

		}
		return "";
	}



	/**
	 * @param adminMethodSysId
	 * @return
	 */
	public Map getQuestionList(String adminMethodSysId) {
		
		AdminMethodMappingAdapterManager adminMethodMappingAdapterManager = new AdminMethodMappingAdapterManager();
		AdminmethodViewAllAdapterManager adminmethodViewAllAdapterManager= new AdminmethodViewAllAdapterManager();
		QuestionAnswerGroupBO questionAnswerGroupBO= new QuestionAnswerGroupBO();
		Map questionAnswerMap = new HashMap();
			List questionList = (List) adminMethodMappingAdapterManager
					.searchAdminMethodQuestionList(adminMethodSysId);
		
	
			
			AdminMethodViewAllFilterBO adminMethodViewAllBO = (AdminMethodViewAllFilterBO)questionList.get(0);
			List answerList;
			try {
				if(!"null".equalsIgnoreCase(adminMethodViewAllBO.getAnswers())){
				List comaSeperatedAnswerList = getComaSeperatedAnswerListForEdit(adminMethodViewAllBO.getAnswers());
				List questionAnswerList = adminmethodViewAllAdapterManager
						.getQuestionAnswerList(comaSeperatedAnswerList);
				List questionAnswerGroupBOList=mapViewAllBOToQuestionBO(questionAnswerList);
				 questionAnswerMap=setValuesToMap(questionAnswerGroupBOList);
				}

			} catch (AdapterException e) {
				e.printStackTrace();
			}
			
		
		
		return questionAnswerMap;
	}

	/**
	 * @param questionAnswerGroupBOList
	 * @return
	 */
	private Map setValuesToMap(List questionAnswerGroupBOList) {

		Map questionAnswerMap= new HashMap();
		
		for (Iterator iter = questionAnswerGroupBOList.iterator(); iter
				.hasNext();) {
			QuestionAnswerGroupBO questionAnswerGroupBO = (QuestionAnswerGroupBO) iter.next();
			
			questionAnswerMap.put(questionAnswerGroupBO.getQuestionId(),questionAnswerGroupBO);
			
		}
		return questionAnswerMap;
	}



	/**
	 * @param questionList
	 * @return
	 */
	private List mapViewAllBOToQuestionBO(List questionList) {
		
		//Map selectedQuestionAnswerMap= new HashMap();
		 
		HashMap questionAnswerMap = new HashMap();
		
	//	QuestionAnswerGroupBO questionAnswerGroupBO= new QuestionAnswerGroupBO();
		
		for (Iterator iter = questionList.iterator(); iter.hasNext();) {
			
			QuestionAnswerBO questionAnswerBO= (QuestionAnswerBO) iter.next();
			
				if (questionAnswerMap.containsKey(new Integer(questionAnswerBO
						.getQuestionId()))) {

					QuestionAnswerGroupBO questionAnswerGroupBOInMap= new QuestionAnswerGroupBO(); 
					// Data contained in the map
					questionAnswerGroupBOInMap = (QuestionAnswerGroupBO) questionAnswerMap
							.get(new Integer(questionAnswerBO.getQuestionId()));

					// Check for matching possibe answers, if doesnot exist, append
					// the answer.
					for (Iterator iterator = questionAnswerGroupBOInMap.getPossibleAnswerIdList()
							.iterator(); iterator.hasNext();) {
						
						String  answerId = (String) iterator.next();
						
						if (!isAttributeMatch(answerId, questionAnswerBO
								.getPossibleAnswer())) {
							
							List list= questionAnswerGroupBOInMap.getPossibleAnswerIdList();
							list.add(String.valueOf(questionAnswerBO.getPossibleAnswerId()));
							questionAnswerGroupBOInMap.setPossibleAnswerIdList(list);
							
							list=questionAnswerGroupBOInMap.getPossibleAnswerDescList();
							list.add(questionAnswerBO.getPossibleAnswer());
							questionAnswerGroupBOInMap.setPossibleAnswerDescList(list);
							break;
						}
						
					}

				} else{
					
					QuestionAnswerGroupBO questionAnswerGroupBO= new QuestionAnswerGroupBO(); 
					List answerId = new ArrayList();
					answerId.add(String.valueOf(questionAnswerBO.getPossibleAnswerId()));
					questionAnswerGroupBO.setPossibleAnswerIdList(answerId);
					
					List answerDesc = new ArrayList();
					answerDesc.add(questionAnswerBO.getPossibleAnswer());
					questionAnswerGroupBO.setPossibleAnswerDescList(answerDesc);
					
					questionAnswerGroupBO.setQuestionId(String.valueOf(questionAnswerBO.getQuestionId()));
					questionAnswerGroupBO.setQuestionDesc(questionAnswerBO.getQuestionDesc());
				
					questionAnswerMap.put(new Integer(questionAnswerBO
							.getQuestionId()), questionAnswerGroupBO);
				}

			}
			return new ArrayList(questionAnswerMap.values());
	}

}