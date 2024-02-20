/*
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.webtesttool.adapter;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.webtesttool.bo.ClaimHeaderBO;
import com.wellpoint.wpd.common.webtesttool.bo.ClaimLineBO;
import com.wellpoint.wpd.common.webtesttool.bo.TestCaseBO;
import com.wellpoint.wpd.common.webtesttool.vo.ClaimLineVO;
import com.wellpoint.wpd.common.webtesttool.vo.TestCaseVO;

public class TestCaseAdapterManager {

	public List locateTestCase(TestCaseVO testCaseVO) throws SevereException {
		HashMap criteriaMap = new HashMap();
		if ((testCaseVO.getTestCaseName() != null)
				&& (testCaseVO.getTestCaseName().length() > 0))
			criteriaMap.put("testCaseName", testCaseVO.getTestCaseName());
		if ((testCaseVO.getBenefitComponent() != null)
				&& (testCaseVO.getBenefitComponent().length() > 0))
			criteriaMap.put("benefitComponent", testCaseVO
					.getBenefitComponent());
		if ((testCaseVO.getBenefit() != null)
				&& (testCaseVO.getBenefit().length() > 0))
			criteriaMap.put("benefit", testCaseVO.getBenefit());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				TestCaseBO.class.getName(), "searchTestCase", criteriaMap);
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}

	public List locateTestCaseById(String testCaseId) throws SevereException {
		Logger.logDebug("TC ADA MANAGER: >> locateTestCaseById");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("testCaseId", testCaseId);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				TestCaseBO.class.getName(), "searchTestCaseById", criteriaMap);
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}

	public List locateTestCaseByName(String testCaseName, String testCaseId)
			throws SevereException {
		Logger.logDebug("TC ADAPTER MANAGER: >> locateTestCaseByName ###"+testCaseName);
		HashMap criteriaMap = new HashMap();
		if(testCaseName != null){
			criteriaMap.put("testCaseName", testCaseName);
		}
		if(testCaseId != null){
			criteriaMap.put("testCaseId", testCaseId);
		}
		
			SearchCriteria searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(TestCaseBO.class.getName(),
						"searchTestCaseByName", criteriaMap);
		
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
		
	}

	public List locateClaimLineByTestCaseId(String testCaseId)
			throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("testCaseId", testCaseId);
		//criteriaMap.put("claimLineId", testCaseId);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ClaimLineBO.class.getName(), "searchByTestCaseId", criteriaMap);
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}

	public List locateClaimHeaderByTestCaseId(String testCaseId)
			throws SevereException {
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("testCaseId", testCaseId);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ClaimHeaderBO.class.getName(), "searchByTestCaseId",
				criteriaMap);
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}

	public void removeTestCase(TestCaseBO testCaseBO, String user)
			throws SevereException {
		AdapterUtil.performRemove(testCaseBO, user);
	}

	public void removeClaimHeader(ClaimHeaderBO claimHeaderBO, String user)
			throws SevereException {
		AdapterUtil.performRemove(claimHeaderBO, user);
	}

	public void removeClaimLine(ClaimLineBO claimLineBO, String user)
			throws SevereException {
		AdapterUtil.performRemove(claimLineBO, user);
	}

	public List locateBenefitComponent(ClaimLineVO claimLineVO, String user)
			throws SevereException {
		HashMap criteriaMap = new HashMap();
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ClaimLineBO.class.getName(), "getAllBenefitComponents",
				criteriaMap);
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}
	public List locateBenefitComponentForPopup(ClaimLineVO claimLineVO, String user)
	throws SevereException {
		Logger.logDebug("TC ADA MGR : locate BEN COMPT POPUP");
			String benefitComptName = claimLineVO.getBenefitCmptName();
			HashMap criteriaMap = new HashMap();
			criteriaMap.put("benefitCmptName", benefitComptName);
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						ClaimLineBO.class.getName(), "searchBenefitComponentByName",
						criteriaMap);
			return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}
	public List locateBenefit(ClaimLineVO claimLineVO, String user)
			throws SevereException {
		Logger.logDebug("Inside TC Adapter Manager ####");
		String benefitType = claimLineVO.getBenefitType();
		String assignedBenefitType = null;
		if (benefitType.equals("riderBenefit")) {
			assignedBenefitType = "getRiderBenefits";
		} else if (benefitType.equals("expectedBenefit")) {
			assignedBenefitType = "getExpectedBenefits";
		} else if (benefitType.equals("basicBenefit")) {
			assignedBenefitType = "getBasicBenefits";
		}
		Logger.logDebug("Assigned Benefit Type >>>>>##"
				+ assignedBenefitType);

		String benefitComponentId = claimLineVO.getBenefitComponentId();
		Logger.logDebug("Benefit Component Id @@###" + benefitComponentId);

		HashMap criteriaMap = new HashMap();
		criteriaMap.put("benefitCmptSysId", benefitComponentId);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ClaimLineBO.class.getName(), assignedBenefitType, criteriaMap);
		return AdapterUtil.performSearch(searchCriteria).getSearchResults();
	}

	// create test suite record
	public void persistTestCase(TestCaseBO testCaseBO, String user)
			throws SevereException {
		AdapterUtil.performInsert(testCaseBO, user);
	}

	public void persistClaimHeader(ClaimHeaderBO claimHeaderBO, String user)
			throws SevereException {
		AdapterUtil.performInsert(claimHeaderBO, user);
	}

	public void persistClaimLine(List claimLineList, TestCaseBO testCaseBO,
			String user) throws SevereException {
		Logger.logDebug("TC Adapter Manager : persistClaimLine >>>>");
		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
		int claimLineListsize = claimLineList.size();
		String testCaseId = testCaseBO.getTestCaseId();
		String claimLineId = null;
		int clineId = 0;
		if (claimLineListsize > 0) {
			Logger.logDebug("TCAM !!!!!");
			for (int claimLineData = 0; claimLineData < claimLineListsize; claimLineData++) {
				Logger.logDebug("Claim Line List size" + claimLineListsize);
				Object object = claimLineList.get(claimLineData);
				clineId = sequenceAdapterManager.getNextClaimLineSequence();
				if (object instanceof ClaimLineVO) {
					ClaimLineVO claimLineVO = (ClaimLineVO) object;
					ClaimLineBO claimLineBO = getClaimLineBO(claimLineVO,
							testCaseId, clineId);
					AdapterUtil.performInsert(claimLineBO, user);
				} else if (object instanceof ClaimLineBO) {
					ClaimLineBO claimLineBO = (ClaimLineBO) object;
					claimLineBO.setTestCaseId(testCaseId);
					claimLineBO.setClaimLineId("" + clineId);
					AdapterUtil.performInsert(claimLineBO, user);
				}
			}
		}
	}

	private ClaimLineBO getClaimLineBO(ClaimLineVO claimLineVO,
			String testCaseId, int claimLineId) {
		ClaimLineBO claimLineBO = new ClaimLineBO();
		if (testCaseId != null) {
			claimLineBO.setTestCaseId(testCaseId);
		}
		claimLineBO.setClaimLineId(String.valueOf(claimLineId));
		claimLineBO.setDiagnosisCode(claimLineVO.getDiagnosisCode());
		claimLineBO.setTtCode(claimLineVO.getTtCode());
		claimLineBO.setPlaceOfService(claimLineVO.getPlaceOfService());
		claimLineBO.setHcpcCode(claimLineVO.getHcpcCode());
		claimLineBO.setRevenueCode(claimLineVO.getRevenueCode());
		claimLineBO.setTypeOfBill(claimLineVO.getTypeOfBill());
		claimLineBO.setModifierCode(claimLineVO.getModifierCode());
		claimLineBO.setExpectedBenefitComponent(claimLineVO
				.getExpectedBenefitComponent());
		claimLineBO.setExpectedBenefit(claimLineVO.getExpectedBenefit());
		claimLineBO.setExpectedBasicBenefit(claimLineVO
				.getExpectedBasicBenefit());
		claimLineBO.setExpectedRiderBenefit(claimLineVO
				.getExpectedRiderBenefit());

		return claimLineBO;
	}

	// update test case record
	public void changeTestCase(TestCaseBO testCaseBO,
			ClaimHeaderBO claimHeaderBO, List claimLineList, boolean isEdit,
			String user) throws SevereException {
		Logger.logDebug("TCAM #### update TEST CASE");
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		AdapterUtil.beginTransaction(adapterServicesAccess);

		AdapterUtil.performUpdate(testCaseBO, user);
		Logger.logDebug("AFTER UPDATION OF TEST CASE ###");
		AdapterUtil.performUpdate(claimHeaderBO, user);
		Logger.logDebug("AFTER UPDATION OF CLAIM HEADER ###");
		int claimLineListsize = claimLineList.size();
		Logger.logDebug("CLAIM LINE LIST SIZE ###" + claimLineListsize);
		String testCaseId = testCaseBO.getTestCaseId();
		Logger.logDebug("TC ID ###" + testCaseId);
		ClaimLineBO clmLineBO = new ClaimLineBO();
		clmLineBO.setTestCaseId(testCaseId);
		String claimLineId = null;
		int clineId = 0;
		boolean claimLineRemoved = false;

		SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
		Logger.logDebug("TCAM + UPDATE TC -- NEW UPDATE----");
		if (claimLineListsize > 0) {
			Logger.logDebug("TCAM + UPDATE TC -- IF LOOP----");
			for (int claimLineData = 0; claimLineData < claimLineListsize; claimLineData++) {
				Logger.logDebug("TCAM + UPDATE TC ----" + claimLineListsize);
				Object object = claimLineList.get(claimLineData);
				if (object instanceof ClaimLineVO) {
					ClaimLineVO claimLineVO = (ClaimLineVO) object;
					clineId = sequenceAdapterManager.getNextClaimLineSequence();
					ClaimLineBO claimLineBO = getClaimLineBO(claimLineVO,
							testCaseId, clineId);
					if (claimLineRemoved == false) {
						AdapterUtil.performRemove(claimLineBO, user);
						claimLineRemoved = true;
					}
					AdapterUtil.performInsert(claimLineBO, user);
				}
			}
		} else {
			Logger.logDebug("TCAM + UPDATE TC -- ELSE LOOP----");
			clmLineBO.setBenefitCmptSysId(999);
			clmLineBO.setBenefitSysId(999);
			clmLineBO.setClaimLineId("999");
			AdapterUtil.performRemove(clmLineBO, user);
		}
		claimLineRemoved = false;
		AdapterUtil.endTransaction(adapterServicesAccess);
	}
}