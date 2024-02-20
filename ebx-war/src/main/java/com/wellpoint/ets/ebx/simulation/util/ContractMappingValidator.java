/*
 * <ContractMappingValidator.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.simulation.util;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;

import com.wellpoint.ets.bx.mapping.domain.entity.Accumulator;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.ebx.referencedata.util.ReferenceDataUtil;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorExclusionDetailVO;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorExclusionVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractMappingVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.vo.ErrorDetailVO;
import com.wellpoint.ets.ebx.simulation.vo.MajorHeadingsVO;
import com.wellpoint.ets.ebx.simulation.vo.MinorHeadingsVO;


/**
 * @author UST-GLOBAL
 * 
 *         Super Class for Error Validator. This class will contains methods
 *         that are common for both eWPD and WPD Systems
 * 
 */
public class ContractMappingValidator {
	public static final Logger log = Logger
			.getLogger(ContractMappingValidator.class);

	/**
	 * Comment for <code>errorExclusionDetailVO</code> Reference of current
	 * error exclusion detail vo while iterating a contract.
	 */
	protected ErrorExclusionDetailVO errorExclusionDetailVO;

	/**
	 * List of Contract Mapping VOs
	 */
	protected List contractMappingVOList = new ArrayList();

	protected List e019VOList = new ArrayList();
	protected List errorListE019 = new ArrayList();
	protected List errorListE019ForCoinsurance = new ArrayList();

	// Hash set initialized for E027 - June Release
	protected Set eb03AIN = new HashSet();
	protected Set eb03AOUT = new HashSet();
	protected Set eb03AINOUT = new HashSet();
	protected Set eb03BIN = new HashSet();
	protected Set eb03BOUT = new HashSet();
	protected Set eb03BINOUT = new HashSet();
	protected Set eb03BCINOUT = new HashSet();
	protected Set eb03DWINOUT = new HashSet();
	protected Set eb03GOUT = new HashSet();
	protected Set eb03GIN = new HashSet();
	protected Set eb03GINOUT = new HashSet();
	protected Set eb03FOUT = new HashSet();
	protected Set eb03FIN = new HashSet();
	protected Set eb03FINOUT = new HashSet();
	protected Set eb03COUT = new HashSet();
	protected Set eb03CIN = new HashSet();
	protected Set eb03CINOUT = new HashSet();
	protected Map eb03Value= new HashMap();
	protected Map eb03MapValueE019= new HashMap();

	/**
	 * The method checks whether the data is Numeric.
	 * 
	 * @param value
	 * @return boolean
	 */
	protected boolean isNumeric(String value) {

		boolean isValid = false;// intialize isValid as boolean variable

		CharSequence inputString = value.trim();

		// Create a pattern to match data
		String expression = DomainConstants.NUMERIC_EXPRESSION;
		Pattern pattern = Pattern.compile(expression);

		// Create a matcher with an input string
		Matcher matcher = pattern.matcher(inputString);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}
	
	/**
	 * The method provides the exclusion list for E017
	 * 
	 * @return List
	 */
	protected List getEB03ForE017() {
		List eb03VariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EB03_VARIABLES_LIST,
				DomainConstants.PROPERTY_FILE_NAME);

		return eb03VariableList;
	}

	/**
	 * The method provides the exclusion list for E001
	 * 
	 * @return List
	 */
	protected List getEB06ForE001() {
		List eb06VariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EB06_VARIABLES_LIST,
				DomainConstants.PROPERTY_FILE_NAME);

		return eb06VariableList;
	}
	
	protected List getEB01forE001() {
		List eb01VariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EB01FORE001,
				DomainConstants.PROPERTY_FILE_NAME);

		return eb01VariableList;
	}
	
	//Added as a part of BXNI CR35
	protected List getHSD05ForE001() {
		List hsd05VariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.HSD05_VARIABLES_LIST,
				DomainConstants.PROPERTY_FILE_NAME);

		return hsd05VariableList;
	}

	/**
	 * The method provides the exclusion list for E005
	 * 
	 * @return List
	 */
	protected List getEB01ForE005() {
		List eb01VariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EB01_VARIABLES_LIST,
				DomainConstants.PROPERTY_FILE_NAME);

		return eb01VariableList;
	}
	
	/**
	 * The method provides the exclusion list for E005
	 * 
	 * @return List
	 */
	protected List getJunkCheckForE005() {
		List junkVariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.JUNK_VARIABLES_LIST,
				DomainConstants.PROPERTY_FILE_NAME);

		return junkVariableList;
	}

	/**
	 * The method provides the exclusion list for E009
	 * 
	 * @return List
	 */
	protected List getFormatsForE009() {
		List formatList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.FORMATS_E009_LIST,
				DomainConstants.PROPERTY_FILE_NAME);

		return formatList;
	}

	/**
	 * The method provides the In-Network PVA Mappings List
	 * 
	 * @return List
	 */
	protected List getInNetworkPVAMappingsForE010() {
		List pvaList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.IN_NETWORK_PVA,
				DomainConstants.PROPERTY_FILE_NAME);

		return pvaList;
	}

	/**
	 * The method provides the Out-Network PVA Mappings List
	 * 
	 * @return List
	 */
	protected List getOutNetworkPVAMappingsForE010() {
		List pvaList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.OUT_NETWORK_PVA,
				DomainConstants.PROPERTY_FILE_NAME);

		return pvaList;
	}

	/**
	 * The method provides the In-Out Network PVA Mappings List
	 * 
	 * @return List
	 */
	protected List getInOutNetworkPVAMappingsForE010() {
		List pvaList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.IN_OUT_NETWORK_PVA,
				DomainConstants.PROPERTY_FILE_NAME);

		return pvaList;
	}

	/**
	 * The method takes the HippaCodeValue objects from the List and creates a
	 * List with the actual values
	 * 
	 * @param hippaCodeValues
	 * @return List
	 */
	protected List convertHippaCodeValuesToList(List hippaCodeValues) {
		List hippaValues = new ArrayList();
		if (null != hippaCodeValues && hippaCodeValues.size() > 0) {
			Iterator hippaCodeValuesIterator = hippaCodeValues.iterator();

			while (hippaCodeValuesIterator.hasNext()) {
				HippaCodeValue hippaCodeValue = (HippaCodeValue) hippaCodeValuesIterator
						.next();
				hippaValues.add(hippaCodeValue.getValue());
			}
		}

		return hippaValues;
	}

	/**
	 * The method provides the Network PVA Mappings List for percentage range
	 * 0-40
	 * 
	 * @return List
	 */
	protected List getNetworkPVAMappingsForE021group1() {
		List pvaList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.err021_in_network_group1,
				DomainConstants.PROPERTY_FILE_NAME);

		return pvaList;
	}

	/**
	 * The method provides the Network PVA Mappings List for percentage range
	 * 0-15
	 * 
	 * @return List
	 */
	protected List getNetworkPVAMappingsForE021group2() {
		List pvaList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.err021_in_network_group2,
				DomainConstants.PROPERTY_FILE_NAME);

		return pvaList;
	}

	/**
	 * The method provides the medsup contract Mappings List for lg
	 * 
	 * @return List
	 */
	protected List getWpdMedsupProductCodeE021() {
		List pvaList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.WPD_MEDSUP_PRODUCT_CODE,
				DomainConstants.PROPERTY_FILE_NAME);

		return pvaList;
	}

	/**
	 * The method provides the medsup contract Mappings List for isg
	 * 
	 * @return List
	 */
	protected List getIsgMedsupProductCodeE021() {
		List pvaList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.ISG_MEDSUP_PRODUCT_CODE,
				DomainConstants.PROPERTY_FILE_NAME);

		return pvaList;
	}

	/**
	 * The method provides the SPS Id for the Reference desc
	 * "COPAY/SERVICE DED PER YEAR MAX"
	 * 
	 * @return List
	 */
	protected List getSPSIdForYearlyCopayMaximum() {

		List excludedVariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.SPSID_YEARLYCOPAYMAX,
				DomainConstants.PROPERTY_FILE_NAME);
		return excludedVariableList;
	}

	/**
	 * The method provides the EB03 values for the Error Code E018
	 * 
	 * @return List
	 */
	protected List getEB03ForE018() {

		List eb03ListForE017 = SimulationResourceBundle
				.getResourceBundle(DomainConstants.EB03FORE018,
						DomainConstants.PROPERTY_FILE_NAME);
		return eb03ListForE017;
	}

	/**
	 * The method provides the In-Network PVA Mappings List
	 * 
	 * @return List
	 */
	protected List getInNetworkPVAMappings() {
		List pvaList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.IN_NETWORK_PVA,
				DomainConstants.PROPERTY_FILE_NAME);

		return pvaList;
	}

	/**
	 * The method provides the Out-Network PVA Mappings List
	 * 
	 * @return List
	 */
	protected List getOutNetworkPVAMappings() {
		List pvaList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.OUT_NETWORK_PVA,
				DomainConstants.PROPERTY_FILE_NAME);

		return pvaList;
	}

	/**
	 * The method provides the In-Out Network PVA Mappings List
	 * 
	 * @return List
	 */
	protected List getInOutNetworkPVAMappings() {
		List pvaList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.IN_OUT_NETWORK_PVA,
				DomainConstants.PROPERTY_FILE_NAME);

		return pvaList;
	}

	/**
	 * The method provides the exclusion list for E006
	 * 
	 * @return List
	 */
	protected List getEB03ForE006() {
		List pvaList = SimulationResourceBundle
				.getResourceBundle(DomainConstants.EB03FORE006,
						DomainConstants.PROPERTY_FILE_NAME);

		return pvaList;
	}

	/**
	 * The method provides the exclusion list for E019
	 * 
	 * @return List
	 */
	protected List getEB03ForE019() {
		List EB03ForE019 = SimulationResourceBundle
				.getResourceBundle(DomainConstants.EB03FORE019,
						DomainConstants.PROPERTY_FILE_NAME);

		return EB03ForE019;
	}

	/**
	 * The method provides the exclusion list for E010
	 * 
	 * @return List
	 */
	protected List getEB03ForE010() {
		List pvaList = SimulationResourceBundle
				.getResourceBundle(DomainConstants.EB03FORE010,
						DomainConstants.PROPERTY_FILE_NAME);

		return pvaList;
	}

	/**
	 * The method provides the exclusion list for E025
	 * 
	 * @return List
	 */
	protected List getExclusionListForE025() {

		List excludedCatagoryList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EXCLUDED_CATAGORY_LIST,
				DomainConstants.PROPERTY_FILE_NAME);
		return excludedCatagoryList;
	}

	/**
	 * The method returns the data type format at which E025 occurs
	 * 
	 * @return List
	 */
	protected List getDataTypeFormatForE025() {
		List dataTypeFormatList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.E025_DATA_TYPE_FORMAT,
				DomainConstants.PROPERTY_FILE_NAME);

		return dataTypeFormatList;
	}

	// methods added for validation of E026 - start
	/**
	 * The method provides the EB03 values for E026 when EB06=26
	 * 
	 * @return List
	 */
	public static List getEB03ForE026_1() {
		List eb03VariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EB03_VARIABLES_LIST_E026_1,
				DomainConstants.PROPERTY_FILE_NAME);

		return eb03VariableList;
	}

	/**
	 * The method provides the EB03 values for E026 when EB06=27
	 * 
	 * @return List
	 */
	public static List getEB03ForE026_2() {
		List eb03VariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EB03_VARIABLES_LIST_E026_2,
				DomainConstants.PROPERTY_FILE_NAME);

		return eb03VariableList;
	}

	/**
	 * The method provides the EB03 values for E026 when EB06=36
	 * 
	 * @return List
	 */
	public static List getEB03ForE026_3() {
		List eb03VariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EB03_VARIABLES_LIST_E026_3,
				DomainConstants.PROPERTY_FILE_NAME);

		return eb03VariableList;
	}

	/**
	 * The method provides the EB03 values for E026 when EB06=7
	 * 
	 * @return List
	 */
	public static List getEB03ForE026_4() {
		List eb03VariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EB03_VARIABLES_LIST_E026_4,
				DomainConstants.PROPERTY_FILE_NAME);

		return eb03VariableList;
	}
	/**
	 * The method provides sensitive EB03 list
	 * @return sensitiveEB03List
	 */
	public static List getEB03Sensitive() {
		List sensitiveEB03List = SimulationResourceBundle.getResourceBundle(
				DomainConstants.SENSITIVE_EB03,
				DomainConstants.PROPERTY_FILE_NAME);

		return sensitiveEB03List;
	}
	// methods added for validation of E026 - end

	/**
	 * The method provides the EB03 values for the Error Code E034
	 * 
	 * @return List
	 */
	protected List getEB03ForE034() {

		List eb03ListForE034 = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EB03_VARIABLES_E034,
				DomainConstants.PROPERTY_FILE_NAME);
		return eb03ListForE034;
	}

	/**
	 * The method provides the EB03 values for the Error Code E034
	 * 
	 * @return List
	 */
	protected List getVariableDescription1ForE034() {

		List variableDescription1ForE034 = SimulationResourceBundle
				.getResourceBundle(DomainConstants.VARIABLE_DESC_1_E034,
						DomainConstants.PROPERTY_FILE_NAME);
		return variableDescription1ForE034;
	}

	/**
	 * The method provides the EB03 values for the Error Code E034
	 * 
	 * @return List
	 */
	protected List getVariableDescription2ForE034() {

		List variableDescription2ForE034 = SimulationResourceBundle
				.getResourceBundle(DomainConstants.VARIABLE_DESC_2_E034,
						DomainConstants.PROPERTY_FILE_NAME);
		return variableDescription2ForE034;
	}

	/**
	 * The method provides the EB03 values for the Error Code E034
	 * 
	 * @return List
	 */
	protected List getVariableDescription3ForE034() {

		List variableDescription3ForE034 = SimulationResourceBundle
				.getResourceBundle(DomainConstants.VARIABLE_DESC_3_E034,
						DomainConstants.PROPERTY_FILE_NAME);
		return variableDescription3ForE034;
	}
	/**
	 * The method provides the EB03 values for the Error Code E034
	 * 
	 * @return List
	 */
	protected List getVariableDescription4ForE034() {

		List variableDescription3ForE034 = SimulationResourceBundle
				.getResourceBundle(DomainConstants.VARIABLE_DESC_4_E034,
						DomainConstants.PROPERTY_FILE_NAME);
		return variableDescription3ForE034;
	}
	/**
	 * The method to check first set of Variable Description for the Error Code E034
	 * 
	 * @return List
	 */
	protected boolean checkVariableDescription1ForE034(String variableDesc){
		boolean stringPresent = false;
		List checkStringList = getVariableDescription1ForE034();
		Iterator it = checkStringList.iterator();
		while(it.hasNext()){
			String checkString = (String) it.next();
			if(variableDesc.indexOf(checkString) != -1){
				stringPresent = true;
			}
		}
		return stringPresent;
	}

	/**
	 * The method to check second set of Variable Description for the Error Code E034
	 * 
	 * @return List
	 */
	protected boolean checkVariableDescription2ForE034(String variableDesc){
		boolean stringPresent = false;
		List checkStringList = getVariableDescription2ForE034();
		Iterator it = checkStringList.iterator();
		while(it.hasNext()){
			String checkString = (String) it.next();
			if(variableDesc.indexOf(checkString) != -1){
				stringPresent = true;
			}
		}
		return stringPresent;
	}
	
	
	/**
	 * The method to check third set of Variable Description for the Error Code E034
	 * 
	 * @return List
	 */
	protected boolean checkVariableDescription3ForE034(String variableDesc){
		boolean stringPresent = false;
		List checkStringList = getVariableDescription3ForE034();
		Iterator it = checkStringList.iterator();
		while(it.hasNext()){
			String checkString = (String) it.next();
			if(variableDesc.indexOf(checkString) != -1){
				stringPresent = true;
			}
		}
		return stringPresent;
	}
	/**
	 * The method to check third set of Variable Description for the Error Code E034
	 * 
	 * @return List
	 */
	protected boolean checkVariableDescription4ForE034(String variableDesc){
		boolean stringPresent = false;
		List checkStringList = getVariableDescription4ForE034();
		Iterator it = checkStringList.iterator();
		while(it.hasNext()){
			String checkString = (String) it.next();
			if(variableDesc.indexOf(checkString) != -1){
				stringPresent = true;
			}
		}
		return stringPresent;
	}
	
	
	/**
	 * The method provides the EB03 values for the Error Code E034
	 * 
	 * @return List
	 */
	protected List getMessage1ForE034() {

		List message1ForE034 = SimulationResourceBundle.getResourceBundle(
				DomainConstants.MESSAGE_1_E034,
				DomainConstants.PROPERTY_FILE_NAME);
		return message1ForE034;
	}

	/**
	 * The method provides the EB03 values for the Error Code E034
	 * 
	 * @return List
	 */
	protected List getMessage2ForE034() {

		List message2ForE034 = SimulationResourceBundle.getResourceBundle(
				DomainConstants.MESSAGE_2_E034,
				DomainConstants.PROPERTY_FILE_NAME);
		return message2ForE034;
	}

	/**
	 * The method provides the EB03 values for the Error Code E034
	 * 
	 * @return List
	 */
	protected List getMessage3ForE034() {

		List message3ForE034 = SimulationResourceBundle.getResourceBundle(
				DomainConstants.MESSAGE_3_E034,
				DomainConstants.PROPERTY_FILE_NAME);
		return message3ForE034;
	}

	/**
	 * The method provides the EB03 values for the Error Code E034
	 * 
	 * @return List
	 */
	protected List getMessage4ForE034() {

		List message4ForE034 = SimulationResourceBundle.getResourceBundle(
				DomainConstants.MESSAGE_4_E034,
				DomainConstants.PROPERTY_FILE_NAME);
		return message4ForE034;
	}

	/**
	 * The method provides the EB03 values for the Error Code E034
	 * 
	 * @return List
	 */
	protected List getWPDMinorHeadingsForE008() {

		List wpdMinorHeadingsForE008 = SimulationResourceBundle
				.getResourceBundle(DomainConstants.MIN_HEAD_WPD_E008,
						DomainConstants.PROPERTY_FILE_NAME);
		return wpdMinorHeadingsForE008;
	}

	/**
	 * The method provides the EB03 values for the Error Code E034
	 * 
	 * @return List
	 */
	protected List getEWPDMinorHeadingsForE008() {

		List ewpdMinorHeadingsForE008 = SimulationResourceBundle
				.getResourceBundle(DomainConstants.MIN_HEAD_EWPD_E008,
						DomainConstants.PROPERTY_FILE_NAME);
		return ewpdMinorHeadingsForE008;
	}

	protected List getExcludedBenefitDescriptionForE025() {

		List excludedBenefitDescription = SimulationResourceBundle
				.getResourceBundle(DomainConstants.BEN_DESC_EXCLUDED_E025,
						DomainConstants.PROPERTY_FILE_NAME);
		return excludedBenefitDescription;
	}
	protected List getEWPDBenefitComponentsForE008() {

		List ewpdMinorHeadingsForE008 = SimulationResourceBundle
				.getResourceBundle(DomainConstants.MAJ_HEAD_EWPD_E008,
						DomainConstants.PROPERTY_FILE_NAME);
		return ewpdMinorHeadingsForE008;
	}

	/**
	 * The method to validate 27xBenefitAccumResponse
	 * 
	 * @return
	 */
	public void validate27xBenefitAccumResponse() {
		return;
	}

	/**
	 * @return List which contains the exclusions for E016
	 */
	protected List getEB03ForE016() {
		List eb03VariableList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EB06_VALUES_EXCLUSIONS_LIST,
				DomainConstants.PROPERTY_FILE_NAME);
		return eb03VariableList;
	}
	
	protected List getSPSForEB6AutoPopulate() {
		List spsList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.SPS_EB06_AUTOPOPULATE_EWPD,
				DomainConstants.PROPERTY_FILE_NAME);
		return spsList;
	}
	protected List getSPSValueForEB6AutoPopulateToVisit() {
		List spsValueList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.SPS_EB06_AUTOPOPULATE_VISIT_EWPD,
				DomainConstants.PROPERTY_FILE_NAME);
		return spsValueList;
	}
	protected List getSPSValueForEB6AutoPopulateToAdmission() {
		List spsValueList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.SPS_EB06_AUTOPOPULATE_ADMISSION_EWPD,
				DomainConstants.PROPERTY_FILE_NAME);
		return spsValueList;
	}
	protected List getSPSValueForEB6AutoPopulateToDay() {
		List spsValueList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.SPS_EB06_AUTOPOPULATE_DAY_EWPD,
				DomainConstants.PROPERTY_FILE_NAME);
		return spsValueList;
	}

	public List getFilterredEBStringforE019(List genericEBStringList) {
		if (null != genericEBStringList) {

			ContractMappingVO mappingVO = null;
			for (int i = 0; i < genericEBStringList.size(); i++) {
				String eBStringStarSeparated = genericEBStringList.get(i)
						.toString();
				// Null check is not perfomed here as eBStringStarSeparated
				// value will not be NULL.
				String[] splitEbKey = eBStringStarSeparated.split("\\*");
				mappingVO = new ContractMappingVO();
				// We expect a String array with 13 Values
				for (int j = 0; j < splitEbKey.length; j++) {
					if (j == 1) {
						mappingVO.setEb01(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB01_NAME, splitEbKey[1]));
					} else if (j == 2) {
						mappingVO.setEb02(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB02_NAME, splitEbKey[2]));
					} else if (j == 3) {
						mappingVO.setEb03(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB03_NAME, splitEbKey[3]));
					} else if (j == 4) {
						mappingVO.setEb04(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB04_NAME, splitEbKey[4]));
					} else if (j == 5) {
						mappingVO.setEb05(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB05_NAME, splitEbKey[5]));
					} else if (j == 6) {
						mappingVO.setEb06(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB06_NAME, splitEbKey[6]));
					} else if (j == 7) {
						mappingVO.setEb07(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB07_NAME, splitEbKey[7]));
					} else if (j == 8) {
						mappingVO.setEb08(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB08_NAME, splitEbKey[8]));
					} else if (j == 9) {
						mappingVO.setEb09(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB09_NAME, splitEbKey[9]));
					} else if (j == 10) {
						mappingVO.setEb10(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB10_NAME, splitEbKey[10]));
					} else if (j == 11) {
						mappingVO.setEb11(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB11_NAME, splitEbKey[11]));
					} else if (j == 12) {
						mappingVO.setEb12(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB12_NAME, splitEbKey[12]));
					} 
				}
				if (!getEB03ForE019().contains(
						BxUtil.getHipaaSegmentValue(mappingVO.getEb03()))) {
					if (DomainConstants.EB01_I.equalsIgnoreCase(BxUtil
							.getHipaaSegmentValue(mappingVO.getEb01()))) {
						e019VOList.add(mappingVO);
					} else if (DomainConstants.EB01_U.equalsIgnoreCase(BxUtil
							.getHipaaSegmentValue(mappingVO.getEb01()))) {
						if ((DomainConstants.EB03_33.equalsIgnoreCase(BxUtil
								.getHipaaSegmentValue(mappingVO.getEb03())))
								|| (DomainConstants.EB03_BG.equalsIgnoreCase(BxUtil
								.getHipaaSegmentValue(mappingVO.getEb03())))) {
							e019VOList.add(mappingVO);
						}
					} else if (DomainConstants.EB12_Y.equalsIgnoreCase(BxUtil
							.getHipaaSegmentValue(mappingVO.getEb12()))
							&& DomainConstants.EB01_A.equalsIgnoreCase(BxUtil
									.getHipaaSegmentValue(mappingVO.getEb01()))
							&& DomainConstants.EB08_0.equalsIgnoreCase(BxUtil
									.getHipaaSegmentValue(mappingVO.getEb08()))) {
						e019VOList.add(mappingVO);
					}

				}
			}
		}
		return e019VOList;
	}

	/**
	 * @param benefitAccumResponse
	 *            benefitAccumResponse
	 * @param errorType
	 *            errorType
	 * @return List of MQ Errors
	 * @throws XmlException
	 *             XmlException
	 * @throws EBXException
	 *             EBXException
	 * @throws Exception
	 *             Exception
	 */
	public List validate27xBenefitAccumsResponse(SimulationHelper helper,
			String errorType, String contractSystem) throws XmlException,
			EBXException, Exception {
		if (null == errorType || DomainConstants.EMPTY.equals(errorType)) {

			ContractMappingVO mappingVO = null;
			List genericEBStringList = helper.getGenericEBStringList();
			// New EBstring list for E028
			List ebStringListForE028 = new ArrayList();
			// New EBstring list for E018
			List ebStringListForE018 = new ArrayList();
			List<ContractMappingVO> ebStringListForE042 = new ArrayList<ContractMappingVO>();

			List ebStringListForE024 = helper.getEBStringListForE024();
			for (int i = 0; i < genericEBStringList.size(); i++) {
				String eBStringStarSeparated = genericEBStringList.get(i)
						.toString();
				// Null check is not perfomed here as eBStringStarSeparated
				// value will not be NULL.
				String[] splitEbKey = eBStringStarSeparated.split("\\*");
				mappingVO = new ContractMappingVO();
				// We expect a String array with 13 Values
				String varFormat = DomainConstants.EMPTY;
				for (int j = 0; j < splitEbKey.length; j++) {
					if (j == 1) {
						mappingVO.setEb01(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB01_NAME, splitEbKey[1]));
					} else if (j == 2) {
						mappingVO.setEb02(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB02_NAME, splitEbKey[2]));
					} else if (j == 3) {
						mappingVO.setEb03(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB03_NAME, splitEbKey[3]));
					} else if (j == 4) {
						mappingVO.setEb04(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB04_NAME, splitEbKey[4]));
					} else if (j == 5) {
						mappingVO.setEb05(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB05_NAME, splitEbKey[5]));
					} else if (j == 6) {
						mappingVO.setEb06(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB06_NAME, splitEbKey[6]));
					} else if (j == 7) {
						mappingVO.setEb07(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB07_NAME, splitEbKey[7]));
					} else if (j == 8) {
						mappingVO.setEb08(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB08_NAME, splitEbKey[8]));
					} else if (j == 9) {
						mappingVO.setEb09(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB09_NAME, splitEbKey[9]));
					} else if (j == 10) {
						mappingVO.setEb10(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB10_NAME, splitEbKey[10]));
					} else if (j == 11) {
						mappingVO.setEb11(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB11_NAME, splitEbKey[11]));
					} else if (j == 12) {
						mappingVO.setEb12(BxUtil.setHipaaSegmentValue(
								DomainConstants.EB12_NAME, splitEbKey[12]));
					} else if (j == 13) {
						varFormat = splitEbKey[13];
					}
					else if (j == 14) {
						mappingVO.setMessage(splitEbKey[14]);
					}else if (j == 15) {
						mappingVO.setIiiMessage(splitEbKey[15]);
					}
				}
				
				// adding the mappingVO object for E028 validation
				if (DomainConstants.EB01_I.equalsIgnoreCase(BxUtil
						.getHipaaSegmentValue(mappingVO.getEb01()))
						&& SimulationResourceValueLoader.getEB03ForE028()
								.contains(
										BxUtil.getHipaaSegmentValue(mappingVO
												.getEb03()))) {
					ebStringListForE028.add(mappingVO);
				}
				//adding the mappingVO object for E018 validation
				if(DomainConstants.EB01_I.equalsIgnoreCase(BxUtil.getHipaaSegmentValue(mappingVO.getEb01()))
						&& SimulationResourceValueLoader.getEB03ForE018().contains(BxUtil.getHipaaSegmentValue(mappingVO.getEb03()))){
					ebStringListForE018.add(mappingVO);		
				}

				if (DomainConstants.SYSTEM_EWPD
						.equalsIgnoreCase(contractSystem)) {
					validateE016(mappingVO, varFormat);
				}
				ebStringListForE042.add(mappingVO);
				// All the validations which rely on a single EB string value
				// will go here.
			}
			//validate EString for E018
			List errorE018List = validateE018(ebStringListForE018);
			if (null != errorE018List && errorE018List.size() > 0) {
				contractMappingVOList.addAll(errorE018List);
			}
			// validate EBString for E028
			List errorE028List = getE028ErrorDetails(ebStringListForE028);
			if (null != errorE028List && errorE028List.size() > 0) {
				contractMappingVOList.addAll(errorE028List);
			}
			// validating EBString for E024
			List errorE024List = getE024ErrorDetails(ebStringListForE024);
			if (null != errorE024List && errorE024List.size() > 0) {
				contractMappingVOList.addAll(errorE024List);
			}
		} else {
			ContractMappingVO mappingVO = new ContractMappingVO();
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_MQ);
			errorDetailsObject.setErrorDesc(errorType);
			mappingVO.getErrorCodesList().add(errorDetailsObject);
			contractMappingVOList.add(mappingVO);
		}
		return contractMappingVOList;
	}



	/**
	 * @param contractMappingVO
	 *            Validation of E016.
	 */
	private void validateE016(ContractMappingVO contractMappingVO,
			String variableFormat) {
		String eb01 = BxUtil.getHipaaSegmentValue(contractMappingVO.getEb01());
		String eb03 = BxUtil.getHipaaSegmentValue(contractMappingVO.getEb03());
		String eb06 = BxUtil.getHipaaSegmentValue(contractMappingVO.getEb06());

		// Checking if EB01 = F and check for EB03 and EB06 and Variable format
		// flags.
		// AND EB03 is any of the values matching the condition for E016
		// AND EB06 is lifetime
		// AND the variable format is zero or one
		if (DomainConstants.LIMITATIONS.equals(eb01)
				&& getEB03ForE016().contains(eb03)
				&& DomainConstants.EB06_VALUE_LIFETIME.equals(eb06)
				&& DomainConstants.ZERO_ONE.equalsIgnoreCase(variableFormat)) {
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			errorDetailsObject.setError(true);
			errorDetailsObject.setErrorCode(DomainConstants.ERROR_E016);
			errorDetailsObject.setErrorDesc(DomainConstants.E016_DESCRIPTION);
			contractMappingVO.getErrorCodesList().add(errorDetailsObject);
			contractMappingVOList.add(contractMappingVO);
		}
	}

	/**
	 * @param benefitAccumResponse
	 *            benefitAccumResponse
	 * @return E024ErrorDetailsList
	 * @throws XmlException
	 * @throws EBXException
	 * @throws Exception
	 *             To get E024 Error Details.
	 */
	public List getE024ErrorDetails(List ebStringListForE024) {

		// initializing variables
		List errorDataList = new ArrayList();
		List tempBenefitList = new ArrayList();
		List tempList = new ArrayList();

		log.info("e024List: " + ebStringListForE024);
		tempBenefitList.addAll(ebStringListForE024);

		Iterator benefitItr = ebStringListForE024.iterator();
		while (benefitItr.hasNext()) {
			String ebString = (String) benefitItr.next();
			Iterator tempbenefitItr = tempBenefitList.iterator();
			while (tempbenefitItr.hasNext()) {
				if (tempbenefitItr.next().equals(ebString)) {
					tempbenefitItr.remove();
				}
			}
			if (!tempList.contains(ebString)) {
				tempList.add(ebString);
				ContractMappingVO contractMapping = validateEBStringForE024(
						tempBenefitList, ebString);
				if (contractMapping != null) {
					errorDataList.add(contractMapping);
				}
			}
		}
		return errorDataList;
	}

	/**
	 * @param benefitAccumResponse
	 *            benefitAccumResponse
	 * @return E028ErrorDetailsList
	 * @throws XmlException
	 * @throws EBXException
	 * @throws Exception
	 *             To get E028 Error Details.
	 */
	public List getE028ErrorDetails(List ebStringListForE028) {
		ContractMappingVO contractmappingVO = null;
		String EB03 = "", networkIndicator = "";
		List tempEbStringList = new ArrayList();
		List errorDataList = new ArrayList();
		tempEbStringList.addAll(ebStringListForE028);
		Map EB03ForE028Map = new HashMap();
		List EB03ForE028List = SimulationResourceValueLoader.getEB03ForE028();
		for (int i = 0; i < EB03ForE028List.size(); i++) {
			EB03ForE028Map.put(EB03ForE028List.get(i), "");
		}
		for (int i = 0; i < ebStringListForE028.size(); i++) {
			contractmappingVO = (ContractMappingVO) ebStringListForE028.get(i);
			EB03 = BxUtil.getHipaaSegmentValue(contractmappingVO.getEb03());
			networkIndicator = BxUtil.getHipaaSegmentValue(contractmappingVO
					.getEb12());
			if (!EB03.equalsIgnoreCase("")) {
				Iterator EB03ForE028MapItr = EB03ForE028Map.entrySet()
						.iterator();
				while (EB03ForE028MapItr.hasNext()) {
					Entry thismap = (Entry) EB03ForE028MapItr.next();
					String key = (String) thismap.getKey();
					String value = (String) thismap.getValue();
					if (key.equals(EB03)
							&& !(value
									.equals(DomainConstants.IN_NETWORK_INDICATOR) || value
									.equals(DomainConstants.OUT_NETWORK_INDICATOR))) {
						EB03ForE028Map.remove(EB03);
						EB03ForE028Map.put(EB03, networkIndicator);
						errorDataList.add(validateEBStringForE028(
								contractmappingVO, i, tempEbStringList, EB03,
								networkIndicator));
						break;
					} else {
						continue;
					}
				}
			}
		}
		return errorDataList;
	}

	/**
	 * @param benefitList
	 * @param str
	 * @return
	 */
	private ContractMappingVO validateEBStringForE028(
			ContractMappingVO eBStringMappingVO, int currentPosition,
			List ebStringListForE028, String EB03, String networkIndicator) {

		String newEB03 = "";
		String newNetWorkIndicator = "";
		ContractMappingVO contractMappingVO = null;
		ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
		boolean errorE028Found = false;
		int i = currentPosition + 1;
		if (null != ebStringListForE028) {
			for (; i < ebStringListForE028.size(); i++) {
				contractMappingVO = (ContractMappingVO) ebStringListForE028
						.get(i);
				if(null !=contractMappingVO && null !=contractMappingVO.getEb03()){
					newEB03 = BxUtil.getHipaaSegmentValue(contractMappingVO
						.getEb03());
				}
				if(null !=contractMappingVO && null !=contractMappingVO.getEb12()){
					newNetWorkIndicator = BxUtil
					.getHipaaSegmentValue(contractMappingVO.getEb12());
				}
				
				if (null != contractMappingVO) {
					if (EB03.equalsIgnoreCase(newEB03)
							&& networkIndicator
									.equalsIgnoreCase(newNetWorkIndicator)) {
						continue;
					} else if (EB03.equalsIgnoreCase(newEB03)
							&& !networkIndicator
									.equalsIgnoreCase(newNetWorkIndicator)) {
						errorDetailsObject.setError(true);
						errorDetailsObject
								.setErrorCode(DomainConstants.ERROR_E028);
						errorDetailsObject
								.setErrorDesc(DomainConstants.E028_DESCRIPTION);
						errorDetailsObject.setNetworkDescription("ALL");
						eBStringMappingVO.getErrorCodesList().add(
								errorDetailsObject);
						errorE028Found = true;
						break;
					}
				}
			}
		}
		if (errorE028Found == false) {
			if (networkIndicator
					.equalsIgnoreCase(DomainConstants.IN_NETWORK_INDICATOR)) {
				errorDetailsObject.setError(true);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E028);
				errorDetailsObject
						.setErrorDesc(DomainConstants.E028_DESCRIPTION);
				errorDetailsObject.setNetworkDescription("PAR");
				eBStringMappingVO.getErrorCodesList().add(errorDetailsObject);
				errorE028Found = true;
			} else if (networkIndicator
					.equalsIgnoreCase(DomainConstants.OUT_NETWORK_INDICATOR)) {
				errorDetailsObject.setError(true);
				errorDetailsObject.setErrorCode(DomainConstants.ERROR_E028);
				errorDetailsObject
						.setErrorDesc(DomainConstants.E028_DESCRIPTION);
				errorDetailsObject.setNetworkDescription("NPAR");
				eBStringMappingVO.getErrorCodesList().add(errorDetailsObject);
				errorE028Found = true;
			}

		}
		return eBStringMappingVO;
	}

	/**
	 * @param benefitList
	 * @param str
	 * @return
	 */
	private ContractMappingVO validateEBStringForE024(List benefitList,
			String ebString) {

		String ebString1 = "", ebString2 = "", tempEbString1 = "", tempEbString2 = "";
		String eb01 = "", eb02 = "", eb03 = "", eb06 = "", eb09 = "", eb12 = "";
		ContractMappingVO errorData = null;
		ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
		List tempEbList = new ArrayList();
		StringTokenizer tokenizer = new StringTokenizer(ebString, "|", false);

		if (tokenizer.hasMoreTokens()) {
			ebString1 = tokenizer.nextToken();
		}
		if (tokenizer.hasMoreTokens()) {
			ebString2 = tokenizer.nextToken();
		}
		for (int i = 0; i < benefitList.size(); i++) {
			String tempEbString = (String) benefitList.get(i);
			StringTokenizer tokenizer1 = new StringTokenizer(tempEbString, "|",
					false);
			if (tokenizer1.hasMoreTokens()) {
				tempEbString1 = tokenizer1.nextToken();
			}
			if (tokenizer1.hasMoreTokens()) {
				tempEbString2 = tokenizer1.nextToken();
			}
			if (tempEbString1.equals(ebString1)
					&& !tempEbList.contains(tempEbString)) {
				if (!ebString2.equals(tempEbString2)) {
					tempEbList.add(tempEbString);
					log.debug(ebString + " Does not match with String : "
							+ tempEbString);
					String[] splitEbKey = ebString1.split("\\*");
					for (int l = 0; l < splitEbKey.length; l++) {
						if (l == 0) {
							eb01 = splitEbKey[l];
						} else if (l == 1) {
							eb02 = splitEbKey[l];
						} else if (l == 2) {
							eb03 = splitEbKey[l];
						} else if (l == 5) {
							eb06 = splitEbKey[l];
						} else if (l == 6) {
							eb09 = splitEbKey[l];
						} else if (l == 8) {
							eb12 = splitEbKey[l];
						}
					}
					// setting error Details in ContractMappingVO.
					errorData = new ContractMappingVO();
					errorDetailsObject.setError(true);
					errorDetailsObject.setErrorCode(DomainConstants.ERROR_E024);
					errorDetailsObject
							.setErrorDesc(DomainConstants.E024_DESCRIPTION); //change error message
					List errorList = new ArrayList();
					errorList.add(errorDetailsObject);
					errorData.setErrorCodesList(errorList);
					errorData
							.setEb01(BxUtil.setHipaaSegmentValue("EB01", eb01));
					errorData
							.setEb02(BxUtil.setHipaaSegmentValue("EB02", eb02));
					errorData
							.setEb03(BxUtil.setHipaaSegmentValue("EB03", eb03));
					errorData
							.setEb06(BxUtil.setHipaaSegmentValue("EB06", eb06));
					errorData
							.setEb09(BxUtil.setHipaaSegmentValue("EB09", eb09));
					errorData
							.setEb12(BxUtil.setHipaaSegmentValue("EB12", eb12));
					log.info("Error E024 exists, EB01:" + eb01 + ", EB02:"
							+ eb02 + ", EB03:" + eb03 + ", EB06:" + eb06
							+ ", EB09:" + eb09 + ", EB12:" + eb12);
					return errorData;
				}
			}
		}
		return null;
	}
	/*
	 * If the response from get27xBenefitAccum service has a EB01 = I for EB03 = 68 or BH or 80 or 81 
	 * and if the network indicator is in-network, an error will be thrown for HCR contract. 
	 */
	/**
	 * @param ebStringListForE018
	 * validate of E018
	 */

		private List validateE018(List ebStringListForE018) {
			List errorDataList = new ArrayList();
			String EB03=null;
			String networkIndicator=null;
			ContractMappingVO contractMappingVO=null;
			log.info("e018List: " + ebStringListForE018);
			if(null!=ebStringListForE018){		
				for (int i = 0; i < ebStringListForE018.size(); i++) {
					contractMappingVO= (ContractMappingVO) ebStringListForE018.get(i);
					EB03 = BxUtil.getHipaaSegmentValue(contractMappingVO.getEb03());
					networkIndicator = BxUtil.getHipaaSegmentValue(contractMappingVO.getEb12());
					if(!DomainConstants.EMPTY.equalsIgnoreCase(EB03)){// Filtered EBSTING is received containing EB01 as I and the EB03 containing 68,BH,80,81 
						if(DomainConstants.IN_NETWORK_INDICATOR.equalsIgnoreCase(networkIndicator)){	// AND check if the n/w indicator is IN_NETWORK or IN_OUT_NETWORK then report the error
							ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
							errorDetailsObject.setError(true);
							errorDetailsObject.setErrorCode(DomainConstants.ERROR_E018);
							errorDetailsObject.setErrorDesc(DomainConstants.E018_DESCRIPTION);
							contractMappingVO.getErrorCodesList().add(errorDetailsObject);
							errorDataList.add(contractMappingVO);
						}
					}
				}
			}
			return errorDataList;
		}
		/**
		 * @param contractVOList
		 * @param finalE019List
		 * validate of E019
		 */
		
		public List validateE019(List contractVOList, List finalE019List) {
			String netWorkIndicator = "";
			List netWorkIndicatorList=null;
			String EB03=null;
			String EB03FromFinalList=null;
			ContractMappingVO eBStringMappingVO =null;
			if (null != finalE019List && !(finalE019List.isEmpty())) {
				for (int j = 0; j < finalE019List.size(); j++) {
					eBStringMappingVO = (ContractMappingVO) finalE019List.get(j);
					if (null != eBStringMappingVO) {
						if (!DomainConstants.EB01_A.equalsIgnoreCase(BxUtil
								.getHipaaSegmentValue(eBStringMappingVO.getEb01()))) {

							netWorkIndicator = BxUtil.getHipaaSegmentValue(eBStringMappingVO.getEb12());
							if (null != BxUtil.getHipaaSegmentValue(eBStringMappingVO.getEb03()) 
									&& !DomainConstants.EMPTY.equals(BxUtil.getHipaaSegmentValue(eBStringMappingVO.getEb03()))) {
								EB03 = BxUtil.getHipaaSegmentValue(eBStringMappingVO.getEb03());
							}
							if (eb03MapValueE019.containsKey(EB03)) {
								List networkIndicatorList=(List)eb03MapValueE019.get(EB03);
								networkIndicatorList.add(netWorkIndicator);
								eb03MapValueE019.put(EB03, networkIndicatorList);
							}
							else {
								List networkIndicatorList= new ArrayList();
								networkIndicatorList.add(netWorkIndicator);
								eb03MapValueE019.put(EB03, networkIndicatorList);
							}
						}
					}
				}


				String system = null;
				if (null != contractVOList) {
					Iterator contractListIter = contractVOList.iterator();
					while (contractListIter.hasNext()) {
						ContractVO contractVO = (ContractVO) contractListIter.next();
						Iterator iteratorEb03Value = eb03MapValueE019.entrySet().iterator();
						while(iteratorEb03Value.hasNext()){
							Map.Entry eb03Pair = (Map.Entry)iteratorEb03Value.next(); 
							EB03=(String) eb03Pair.getKey();
							for (int j = 0; j < finalE019List.size(); j++) {
								eBStringMappingVO = (ContractMappingVO) finalE019List.get(j);
								if (null != eBStringMappingVO) {
									if (!DomainConstants.EB01_A.equalsIgnoreCase(BxUtil
											.getHipaaSegmentValue(eBStringMappingVO.getEb01()))) {
										EB03FromFinalList = BxUtil.getHipaaSegmentValue(eBStringMappingVO.getEb03());
										if(EB03.equalsIgnoreCase(EB03FromFinalList)){
											break;
										}
									}
								}
							}
							if (null != contractVO) {
								netWorkIndicatorList=(List) eb03MapValueE019.get(EB03);
								if ((contractVO.isFlagEPO()||contractVO.isFlagHMO())// Skips EPO/HMO contracts whose network indicator is OUT_NETWORK
										&& netWorkIndicatorList.contains(DomainConstants.N)) {
									continue;
								}
								system = contractVO.getSystem();
								getErrorListE019(contractVO,EB03,netWorkIndicatorList,system,eBStringMappingVO);
							}
						}
					}
				}
			}


			return errorListE019;
		}
		/**
		 * @param contractVO
		 * @param EB03
		 * @param netWorkIndicatorList
		 * @param system
		 * @param eBStringMappingVO
		 */
		private void getErrorListE019(ContractVO contractVO,String EB03,List netWorkIndicatorList, String system,ContractMappingVO eBStringMappingVO ) {
			String contractPVA = null;
			String contractEB03 = null;
			List eb03ContractValuesList = null;
			ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
			boolean eb03MappingFound = false;
			boolean pvaMappingFound=false;
			boolean inOutNetwork = false;
			boolean inNetwork = false;
			boolean outNetwork=false;
			boolean FlagBenefitCovered=false;
			boolean FlagBenefitCoveredPar=false;
			boolean FlagBenefitCoveredNpar=false;
			boolean bcQesMisMatch=false;

			// get MajorHeadings Map
			HashMap majorHeadingsMap = (HashMap) contractVO.getMajorHeadings();
			if (majorHeadingsMap.size() > 0) {
				Iterator iteratorMajor = majorHeadingsMap.keySet().iterator();
				while (iteratorMajor.hasNext()) {
					String keyMajor = (String) iteratorMajor.next();
					MajorHeadingsVO majorHeadingVO = (MajorHeadingsVO) majorHeadingsMap
					.get(keyMajor);
					if (null != majorHeadingVO) {
						if(inOutNetwork==true || (inNetwork == true && outNetwork==true)){
							break;// if IN_OUT_NETWORK is found in the contract, no further looping is required since EB03(ebstring) found a mapped in the contract and it would satisfies either Y or N (ebstring E12)
						}
						// get MinorHeadings Map
						HashMap minorHeadingsMap = (HashMap) majorHeadingVO
						.getMinorHeadings();
						if (minorHeadingsMap.size() > 0) {
							Iterator iteratorMinor = minorHeadingsMap.keySet().iterator();
							while (iteratorMinor.hasNext()) {
								String keyMinor = (String) iteratorMinor.next();
								MinorHeadingsVO minorHeadingVO = (MinorHeadingsVO) minorHeadingsMap.get(keyMinor);
								if (null != minorHeadingVO) {
									if(inOutNetwork==true|| (inNetwork == true && outNetwork==true)){
										break;// if IN_OUT_NETWORK is found in the contract, no further looping is required since EB03(ebstring) found a mapped in the contract and it would satisfies either Y or N (ebstring E12)
									}
									if (DomainConstants.SYSTEM_EWPD
											.equalsIgnoreCase(system)) {// lOGIC FOR EWPD
										if (null != minorHeadingVO.getRuleMapping()
												&& minorHeadingVO.getRuleMapping().isMapped()) {
											if (null != minorHeadingVO.getRuleMapping()) {// for E019 EWPD
												HippaSegment hippaSegmentEB03 = minorHeadingVO.getRuleMapping().getEb03();
												if (null != hippaSegmentEB03) {
													eb03ContractValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
												}
											}
											Iterator eb03Iterator = eb03ContractValuesList.iterator();
											while (eb03Iterator.hasNext()) {
												HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator.next();
												contractEB03 = hippaCodeValue03.getValue();
												// get Mappings Map for EWPD
												if (EB03.equalsIgnoreCase(contractEB03)) {
													eb03MappingFound=true;
													minorHeadingVO = setBenefitCoveredFlag(minorHeadingVO);
													FlagBenefitCovered=minorHeadingVO.isFlagBenefitCovered();
													FlagBenefitCoveredPar=minorHeadingVO.isFlagBenefitCoveredPar();
													FlagBenefitCoveredNpar=minorHeadingVO.isFlagBenefitCoveredNpar();
													//if the ebstring is IN_OUT_NETWORK and the BENEFIT COVERED is answered as Y validation is checked else
													// if the BENEFIT COVERED is answered as N , then no validation
													// if the ebstring is Y and the BENEFIT COVERED and BENEFIT COVERED PAR is Y validation is checked else
													// if the BENEFIT COVERED is Y and BENEFIT COVERED PAR is N , then no validation is checked
													// if the ebstring is N and the BENEFIT COVERED and BENEFIT COVERED NPAR is Y validation is checked else
													// if the BENEFIT COVERED is Y and BENEFIT COVERED NPAR is N , then no validation is checked
													if(netWorkIndicatorList.size()==1){
														if(DomainConstants.IN_NETWORK_INDICATOR.equalsIgnoreCase((String) netWorkIndicatorList.get(0)))	{
															if(!FlagBenefitCovered){
																bcQesMisMatch=true;
																break;
															}else if(FlagBenefitCovered && !FlagBenefitCoveredPar){
																bcQesMisMatch=true;
																break;
															}
														}else if(DomainConstants.OUT_NETWORK_INDICATOR.equalsIgnoreCase((String) netWorkIndicatorList.get(0))){
															if(!FlagBenefitCovered){
																bcQesMisMatch=true;
																break;
															}else if(FlagBenefitCovered && !FlagBenefitCoveredNpar){
																bcQesMisMatch=true;
																break;
															}
														}
													}else {
														if(!FlagBenefitCovered){
															bcQesMisMatch=true;
															break;
														} else if (FlagBenefitCovered && !FlagBenefitCoveredPar && !FlagBenefitCoveredNpar) {
															bcQesMisMatch=true;
															break;
														}
													}
													bcQesMisMatch=false;
													HashMap mappingsMap = (HashMap) minorHeadingVO.getMappings();
													if (mappingsMap.size() > 0) {
														Iterator iteratorMappings = mappingsMap.entrySet().iterator();
														while (iteratorMappings.hasNext()) {
															ContractMappingVO DBmappingVO = (ContractMappingVO) ((Map.Entry) iteratorMappings
																	.next())
																	.getValue();
															if (null != DBmappingVO && DBmappingVO.isMapped()) {
																SPSId spsIdObj = DBmappingVO.getSpsId();
																contractPVA = spsIdObj.getLinePVA();
																if(inNetwork==true && outNetwork==true){// if the the PVA is in and out_network is true, the loop is broken,since it would satisfy either both conditions Y OR N (ebstring eb12)
																	break;
																}
																if (getInOutNetworkPVAMappings().contains(contractPVA)) {// if in_ou_network is found in a contract then
																	pvaMappingFound = true;							 // mapping found flag is set as true	
																	inOutNetwork = true;							 // IN_OUT_NEWTORK flag is set as tue
																	break;	// if the the PVA is in_out_network, the loop is broken, since it would satisfy either both conditions Y or N (ebstring eb12)
																} else if (getInNetworkPVAMappings().contains(contractPVA) && inNetwork==false) {// if IN_network is found in a contract then
																	pvaMappingFound = true;														 // mapping found flag is set as true 
																	inNetwork=true;																 //	in_network is set as true	
																	continue;
																} else if (getOutNetworkPVAMappings().contains(contractPVA)&& outNetwork==false) {// if out_network is found in a contract then
																	pvaMappingFound = true;														  // mapping found flag is set as true 		
																	outNetwork=true;															  // out_network is set as true		
																	continue;
																}
															}
														}
													}
												}
											}
										}
											
									} else {
										// get Mappings Map for WPD(LG/ISG)
										HashMap mappingsMap = (HashMap) minorHeadingVO
										.getMappings();
										if (mappingsMap.size() > 0) {
											Iterator iteratorMappings = mappingsMap
											.entrySet().iterator();
											while (iteratorMappings.hasNext()) {
												if(inOutNetwork==true|| (inNetwork == true && outNetwork==true)){
													break;// if IN_OUT_NETWORK is found in the contract, no further looping is required since EB03(ebstring) found a mapped in the contract and it would satisfies either Y or N (ebstring E12)
												}
												ContractMappingVO DBmappingVO = (ContractMappingVO) ((Map.Entry) iteratorMappings
														.next()).getValue();
												if (null != DBmappingVO && DBmappingVO.isMapped()) {
													if (null != DBmappingVO.getVariable()) {
														contractPVA = DBmappingVO.getVariable().getPva();
														if (null != contractPVA) {
															contractPVA = contractPVA.trim();
														}
														if (null != DBmappingVO.getEb03()) {
															HippaSegment hippaSegmentEB03 = DBmappingVO.getEb03();
															List eb03ValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
															if (null != eb03ValuesList && eb03ValuesList.size() > 0) {
																Iterator eb03Iterator = eb03ValuesList.iterator();
																while (eb03Iterator.hasNext()) {
																	HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator.next();
																	if(!StringUtils.isEmpty(hippaCodeValue03.getValue())){
																		contractEB03 = hippaCodeValue03.getValue().trim();
																		if (EB03.equalsIgnoreCase(contractEB03)) {
																			eb03MappingFound=true;// true when encountered matching EB03
																			if(inNetwork==true && outNetwork==true){// if the the PVA is in and out_network is true, the loop is broken,since it would satisfy both conditions Y and N (ebstring eb12)
																				break;
																			}
																			if (getInOutNetworkPVAMappings().contains(contractPVA)) {
																				pvaMappingFound = true;
																				inOutNetwork = true;
																				break;	// if the the PVA is in_out_network, the loop is broken, since it would satisfy both conditions Y and N (ebstring eb12)
																			} else if (getInNetworkPVAMappings().contains(contractPVA) && inNetwork==false) {
																				pvaMappingFound = true;
																				inNetwork=true;
																				continue;
																			} else if (getOutNetworkPVAMappings().contains(contractPVA) && outNetwork==false) {
																				pvaMappingFound = true;
																				outNetwork=true;
																				continue;
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

				// Implement the error description details
				if(!(DomainConstants.EMPTY.equalsIgnoreCase(EB03))){
					if(eb03MappingFound==false || (eb03MappingFound==true && (!bcQesMisMatch||inOutNetwork||outNetwork||inNetwork))){// if the EB03 has no corresponding mapping in the contract OR if matching is found and the BENEFIT COVERED QUESTION satisfy then the E019 error is thrown
						if(netWorkIndicatorList.size()==1){
							if(DomainConstants.IN_NETWORK_INDICATOR.equalsIgnoreCase((String) netWorkIndicatorList.get(0))// if the network indicator(ebstring) is a IN_NETWORK (Y)
									&& !(inNetwork==true ||inOutNetwork==true)){										  // And if there is no IN_NETWORK OR IN_OUT_NETWORK marked in the contract
								if((pvaMappingFound==false) ||(pvaMappingFound==true && outNetwork==true)){					  // and then checks if the mapping is not found OR if the mapping found and OUT_NETWORK is mapped(E019 error for PAR is thrown)
									errorDetailsObject.setError(true);
									errorDetailsObject.setErrorCode(DomainConstants.ERROR_E019);
									errorDetailsObject.setErrorDesc(DomainConstants.E019_DESCRIPTION);
									errorDetailsObject.setNetworkDescription("PAR");
									eBStringMappingVO.getErrorCodesList().add(errorDetailsObject);
								}
							}else if(DomainConstants.OUT_NETWORK_INDICATOR.equalsIgnoreCase((String) netWorkIndicatorList.get(0))// if the network indicator(ebstring) is a OUT_NETWORK (N)
									&& !(outNetwork==true ||inOutNetwork==true)){												 // And if there is no OUT_NETWORK OR IN_OUT_NETWORK marked in the contract
								if((pvaMappingFound==false) ||(pvaMappingFound==true && inNetwork==true)){							 // Then checks if the mapping is not found OR if the mapping found and IN_NETWORK is mapped(E019 error for NPAR is thrown)
									errorDetailsObject.setError(true);
									errorDetailsObject.setErrorCode(DomainConstants.ERROR_E019);
									errorDetailsObject.setErrorDesc(DomainConstants.E019_DESCRIPTION);
									errorDetailsObject.setNetworkDescription("NPAR");
									eBStringMappingVO.getErrorCodesList().add(errorDetailsObject);
								}
							}
						}else{
							if(!inOutNetwork){
								if (DomainConstants.SYSTEM_EWPD.equalsIgnoreCase(system)) {
									if(pvaMappingFound==true && inNetwork==false && outNetwork==true && FlagBenefitCoveredPar){// if the network indicator(ebstring) is a IN_OUT_NETWORK, checks if the in_network is not mapped to the contract and there is a out_network is mapped, then E019 error is thrown as PAR
										errorDetailsObject.setError(true);
										errorDetailsObject.setErrorCode(DomainConstants.ERROR_E019);
										errorDetailsObject.setErrorDesc(DomainConstants.E019_DESCRIPTION);
										errorDetailsObject.setNetworkDescription("PAR");
										eBStringMappingVO.getErrorCodesList().add(errorDetailsObject);
									}else if(pvaMappingFound==true && outNetwork==false && inNetwork==true && FlagBenefitCoveredNpar){// if the network indicator(ebstring) is a IN_OUT_NETWORK, checks if the out_network is not mapped to the contract and there is a in_network is mapped, then E019 error is thrown as NPAR
										errorDetailsObject.setError(true);
										errorDetailsObject.setErrorCode(DomainConstants.ERROR_E019);
										errorDetailsObject.setErrorDesc(DomainConstants.E019_DESCRIPTION);
										errorDetailsObject.setNetworkDescription("NPAR");
										eBStringMappingVO.getErrorCodesList().add(errorDetailsObject);
									}else if (pvaMappingFound==false && FlagBenefitCovered && FlagBenefitCoveredNpar && !FlagBenefitCoveredPar){
										errorDetailsObject.setError(true);
										errorDetailsObject.setErrorCode(DomainConstants.ERROR_E019);
										errorDetailsObject.setErrorDesc(DomainConstants.E019_DESCRIPTION);
										errorDetailsObject.setNetworkDescription("NPAR");
										eBStringMappingVO.getErrorCodesList().add(errorDetailsObject);
									}else if (pvaMappingFound==false && FlagBenefitCovered && !FlagBenefitCoveredNpar && FlagBenefitCoveredPar){
										errorDetailsObject.setError(true);
										errorDetailsObject.setErrorCode(DomainConstants.ERROR_E019);
										errorDetailsObject.setErrorDesc(DomainConstants.E019_DESCRIPTION);
										errorDetailsObject.setNetworkDescription("PAR");
										eBStringMappingVO.getErrorCodesList().add(errorDetailsObject);
									}
										else if(pvaMappingFound==false && FlagBenefitCovered && FlagBenefitCoveredNpar && FlagBenefitCoveredPar){// if the network indicator(ebstring) is a IN_OUT_NETWORK,and there is no mapping is found , then E019 error is thrown as ALL
										errorDetailsObject.setError(true);
										errorDetailsObject.setErrorCode(DomainConstants.ERROR_E019);
										errorDetailsObject.setErrorDesc(DomainConstants.E019_DESCRIPTION);
										errorDetailsObject.setNetworkDescription("ALL");
										eBStringMappingVO.getErrorCodesList().add(errorDetailsObject);
									}   
										else if((pvaMappingFound == false)){
										errorDetailsObject.setError(true);
										errorDetailsObject.setErrorCode(DomainConstants.ERROR_E019);
										errorDetailsObject.setErrorDesc(DomainConstants.E019_DESCRIPTION);
										errorDetailsObject.setNetworkDescription("ALL");
										eBStringMappingVO.getErrorCodesList().add(errorDetailsObject);
									}
									
								} else {
									if(pvaMappingFound==true && inNetwork==false && outNetwork==true){// if the network indicator(ebstring) is a IN_OUT_NETWORK, checks if the in_network is not mapped to the contract and there is a out_network is mapped, then E019 error is thrown as PAR
										errorDetailsObject.setError(true);
										errorDetailsObject.setErrorCode(DomainConstants.ERROR_E019);
										errorDetailsObject.setErrorDesc(DomainConstants.E019_DESCRIPTION);
										errorDetailsObject.setNetworkDescription("PAR");
										eBStringMappingVO.getErrorCodesList().add(errorDetailsObject);
									}else if(pvaMappingFound==true && outNetwork==false && inNetwork==true){// if the network indicator(ebstring) is a IN_OUT_NETWORK, checks if the out_network is not mapped to the contract and there is a in_network is mapped, then E019 error is thrown as NPAR
										errorDetailsObject.setError(true);
										errorDetailsObject.setErrorCode(DomainConstants.ERROR_E019);
										errorDetailsObject.setErrorDesc(DomainConstants.E019_DESCRIPTION);
										errorDetailsObject.setNetworkDescription("NPAR");
										eBStringMappingVO.getErrorCodesList().add(errorDetailsObject);
									}else if(pvaMappingFound==false){// if the network indicator(ebstring) is a IN_OUT_NETWORK,and there is no mapping is found , then E019 error is thrown as ALL
										errorDetailsObject.setError(true);
										errorDetailsObject.setErrorCode(DomainConstants.ERROR_E019);
										errorDetailsObject.setErrorDesc(DomainConstants.E019_DESCRIPTION);
										errorDetailsObject.setNetworkDescription("ALL");
										eBStringMappingVO.getErrorCodesList().add(errorDetailsObject);
									}
								}
							}
						}
					}
				}
				errorListE019.add(eBStringMappingVO);
			}

		}
	/*
	 * setBenefitCoveredFlag is used to set the Benefit covered flag for a particular benefit
	 * if BENEFIT COVERED is Y and
	 * if the BENEFIT COVERED PAR and BENEFIT COVERED NPAR question is not answered, by default the both flags are set as true
	 * else
	 * if BENEFIT COVERED PAR is answered as N then the flag is set as false else
	 * if BENEFIT COVERED NPAR is answered as N then the flag is set as false
	 * 
	 */
	private MinorHeadingsVO setBenefitCoveredFlag(
			MinorHeadingsVO minorHeadingObj) {
		String benefitCoveredAns=null;
		String benefitCoveredParAns=null;
		String benefitCoveredNparAns=null;
		if (null != minorHeadingObj) {
			// get Mappings Map
			HashMap mappingsMap = (HashMap) minorHeadingObj.getMappings();
			Iterator iteratorMappings = mappingsMap.entrySet().iterator();
			while (iteratorMappings.hasNext()) {
				// adding the ErrorCodes to the Variable
				// object
				ContractMappingVO mappingObj = (ContractMappingVO) ((Map.Entry) iteratorMappings
						.next()).getValue();
				if (null != mappingObj && null != mappingObj.getSpsId()) {
					SPSId spsIdObj = mappingObj.getSpsId();
					if(null!=spsIdObj.getSpsDesc()){
						if(DomainConstants.BENEFITCOVERED.equalsIgnoreCase(spsIdObj.getSpsDesc())){
							benefitCoveredAns=spsIdObj.getLineValue();
						}else if(DomainConstants.BENEFITCOVEREDPAR.equalsIgnoreCase(spsIdObj.getSpsDesc())){
							benefitCoveredParAns=spsIdObj.getLineValue();
						}else  if(DomainConstants.BENEFITCOVEREDNPAR.equalsIgnoreCase(spsIdObj.getSpsDesc())){
							benefitCoveredNparAns=spsIdObj.getLineValue();
						}

					}
				}
			}
			if(DomainConstants.Y.equalsIgnoreCase(benefitCoveredAns)){ 
				minorHeadingObj.setFlagBenefitCovered(true);
				if((benefitCoveredParAns==null && benefitCoveredNparAns == null)
						||(DomainConstants.Y.equalsIgnoreCase(benefitCoveredParAns)&&benefitCoveredNparAns == null)
						||(DomainConstants.Y.equalsIgnoreCase(benefitCoveredNparAns)&&benefitCoveredParAns == null)
						||(DomainConstants.Y.equalsIgnoreCase(benefitCoveredNparAns)&&(DomainConstants.Y.equalsIgnoreCase(benefitCoveredParAns)))){
					minorHeadingObj.setFlagBenefitCoveredPar(true);
					minorHeadingObj.setFlagBenefitCoveredNpar(true);
				}else if((DomainConstants.Y.equalsIgnoreCase(benefitCoveredParAns)&& DomainConstants.N.equalsIgnoreCase(benefitCoveredNparAns))
						||(benefitCoveredParAns==null&& DomainConstants.N.equalsIgnoreCase(benefitCoveredNparAns))){
					minorHeadingObj.setFlagBenefitCoveredPar(true);
					minorHeadingObj.setFlagBenefitCoveredNpar(false);
				}else if((DomainConstants.N.equalsIgnoreCase(benefitCoveredParAns)&& DomainConstants.Y.equalsIgnoreCase(benefitCoveredNparAns))
						||(DomainConstants.N.equalsIgnoreCase(benefitCoveredParAns)&& benefitCoveredNparAns==null)){
					minorHeadingObj.setFlagBenefitCoveredPar(false);
					minorHeadingObj.setFlagBenefitCoveredNpar(true);
				}
			} else if (null == benefitCoveredAns) { // If the Benefit covered question is not answered
				minorHeadingObj.setFlagBenefitCovered(true);
				minorHeadingObj.setFlagBenefitCoveredPar(true);
				minorHeadingObj.setFlagBenefitCoveredNpar(true);
			}
		}
		return minorHeadingObj;
	}
	/**
	 * This method will throw E019 <Service type code>-PAR error, 
	 * if in EB string [EB01 = A and cost share value = 0 and In-Network], 
	 * and if there is no in-network mapping in the contract for the same EB03
	 * @param contractVOList
	 * @param eBStringForE019List
	 * @return
	 */
	public List validateE019forZeroCoinsuranceInNetwork(List contractVOList,
			List e019ListForCoinsurance) {
		ContractMappingVO eBStringMappingVOCoins =null;
		List eb03ErrorList = new ArrayList();
		if (null != e019ListForCoinsurance && !(e019ListForCoinsurance.isEmpty())) {
			for (int count = 0; count < e019ListForCoinsurance.size(); count++) {
				boolean inOrOutNetworkPresent = false;
				eBStringMappingVOCoins = (ContractMappingVO) e019ListForCoinsurance.get(count);
				if (null != eBStringMappingVOCoins) {
					if (DomainConstants.EB01_A.equalsIgnoreCase(BxUtil
							.getHipaaSegmentValue(eBStringMappingVOCoins.getEb01())) 
							&& null != BxUtil.getHipaaSegmentValue(eBStringMappingVOCoins.getEb03())
							&& !DomainConstants.EMPTY.equals(BxUtil.getHipaaSegmentValue(eBStringMappingVOCoins.getEb03()))) {
						Iterator contractVOListItr = contractVOList.iterator();
						while (contractVOListItr.hasNext()) {
							ContractVO contract = (ContractVO) contractVOListItr.next();
							// get MajorHeadings Map
							HashMap majorHeadingsMap = (HashMap) contract.getMajorHeadings();
							if (majorHeadingsMap.size() > 0) {
								Iterator iteratorMajor = majorHeadingsMap.keySet().iterator();
								while (iteratorMajor.hasNext()) {
									if (inOrOutNetworkPresent) {
										break;
									}
									String keyMajor = (String) iteratorMajor.next();
									MajorHeadingsVO majorHeadingObj = (MajorHeadingsVO) majorHeadingsMap
									.get(keyMajor);
									// Get MinorHeadings Map
									HashMap minorHeadingsMap = (HashMap) majorHeadingObj
											.getMinorHeadings();
									Iterator iteratorMinor = minorHeadingsMap.keySet().iterator();
									while (iteratorMinor.hasNext()) {
										if (inOrOutNetworkPresent) {
											break;
										}
										String keyMinor = (String) iteratorMinor.next();
										MinorHeadingsVO minorHeadingObj = (MinorHeadingsVO) minorHeadingsMap
										.get(keyMinor);
										if (DomainConstants.SYSTEM_EWPD
												.equalsIgnoreCase(contract.getSystem())) {
											//checkE019ForZeroCoinsuranceInNetworkEWPD(eBStringMappingVOCoins, minorHeadingObj, eb03ErrorList, inOrOutNetworkPresent);
											if (null != minorHeadingObj
													&& null != minorHeadingObj.getRuleMapping()
													&& null != minorHeadingObj.getRuleMapping().getVariableMappingStatus()) {
												if (!"NOT_APPLICABLE".equals(minorHeadingObj.getRuleMapping()
														.getVariableMappingStatus())) {
													HippaSegment hippaSegmentEB03 = minorHeadingObj.getRuleMapping().getEb03();
													if (null != hippaSegmentEB03) {
														List eb03ValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
														Iterator eb03Iterator = eb03ValuesList.iterator();
														while (eb03Iterator.hasNext()) {
															if (inOrOutNetworkPresent) {
																break;
															}
															HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator.next();
															String eb03FromDB = hippaCodeValue03.getValue();
															
															String eb03FromEbString = BxUtil.getHipaaSegmentValue(eBStringMappingVOCoins
																	.getEb03());
															/* Checking whether E019 already reported for the same EB03, this check is required as the EBString may have duplicates.
															Also Comparing the EB03 values from the EBString and DB*/
															if ((eb03FromEbString).equalsIgnoreCase(eb03FromDB)) {
																// get mappings map
																HashMap mappingsMap = (HashMap) minorHeadingObj.getMappings();
																Iterator iteratorMappings = mappingsMap.entrySet().iterator();
																while (iteratorMappings.hasNext()) {
																	if (inOrOutNetworkPresent) {
																		break;
																	}
																	ContractMappingVO dbMappingVO = (ContractMappingVO) ((Map.Entry) iteratorMappings.next()).getValue();
																	if (null != dbMappingVO && dbMappingVO.isMapped()) {
																		String networkIndicator = null;
																		if (null != dbMappingVO.getSpsId()
																				&& null != dbMappingVO.getSpsId().getLinePVA()) {
																			networkIndicator = dbMappingVO.getSpsId().getLinePVA();
																		}
																		// Checking whether the contract is
																		// having IN-NETWORK
																		if ((getInNetworkPVAMappings().contains(
																				networkIndicator) || getInOutNetworkPVAMappings().contains(networkIndicator))) {
																			inOrOutNetworkPresent = true;
																			break;
																		}
																	}
																}
															}
														}
													}
												}
											}
										} else {
											//checkE019ForZeroCoinsuranceInNetworkWPD(eBStringMappingVOCoins, minorHeadingObj, eb03ErrorList, inOrOutNetworkPresent);
											// get Mappings Map
											HashMap mappingsMap = (HashMap) minorHeadingObj.getMappings();
											Iterator iteratorMappings = mappingsMap.entrySet().iterator();
											while (iteratorMappings.hasNext()) {
												if (inOrOutNetworkPresent) {
													break;
												}
												ContractMappingVO dbMappingVO = (ContractMappingVO) ((Map.Entry) iteratorMappings
														.next()).getValue();
												String netWorkIndicator = null;
												if (null != dbMappingVO && dbMappingVO.isMapped()
														&& null != dbMappingVO.getEb03()) {
													// Getting the EB03 from Contract mapping
													List eb03ValuesList = dbMappingVO.getEb03()
															.getHippaCodeSelectedValues();
													Iterator eb03Iterator = eb03ValuesList.iterator();
													while (eb03Iterator.hasNext()) {
														if (inOrOutNetworkPresent) {
															break;
														}
														HippaCodeValue hippaCodeValue = (HippaCodeValue) eb03Iterator
														.next();
														String eb03FromContract = hippaCodeValue.getValue();
														// Getting EB03 from the EBString
														String eb03FromEBString = BxUtil
														.getHipaaSegmentValue(eBStringMappingVOCoins
																.getEb03());
														/* Checking whether E019 already reported for the same EB03, this check is required as the EBString may have duplicates.
														Also Comparing the EB03 values from the EBString and DB*/
														if (eb03FromEBString.equalsIgnoreCase(eb03FromContract)) {
															if ((null != dbMappingVO.getVariable())
																	&& !(DomainConstants.STATUS_NOT_APPLICABLE
																			.equalsIgnoreCase(dbMappingVO.getVariable()
																					.getVariableStatus())) && (null != dbMappingVO
																			.getVariable().getPva())) {
																netWorkIndicator = dbMappingVO.getVariable().getPva()
																		.trim();
															}
															if ((getInNetworkPVAMappings().contains(netWorkIndicator) 
																	|| getInOutNetworkPVAMappings().contains(netWorkIndicator))) {
																inOrOutNetworkPresent = true;
																break;
															}
														}
													}
												}
											}
										}
										
									}
								}
							}
							if (!eb03ErrorList.contains(eBStringMappingVOCoins) && !inOrOutNetworkPresent) {
								setE019ForZeroCoinsuranceInNetwork(eBStringMappingVOCoins);
								eb03ErrorList.add(eBStringMappingVOCoins);
							}
						}
					}
				}
			}
			
		}
		return errorListE019ForCoinsurance;
	}
	/**
	 * Method to check in WPD(LG/ISG) 1) whether the EB03 values are same in EBString and DB ContractMappingVO
	 * 2) if the ContractMappingVO is having an in network Variable mapped
	 * @param eBStringMappingVOCoins
	 * @param minorHeadingObj
	 * @param eb03ErrorList 
	 * @param isError 
	 * @param isError 
	 * @param eb03ErrorList 
	 *//*
	
	private void checkE019ForZeroCoinsuranceInNetworkWPD(ContractMappingVO eBStringMappingVOCoins,MinorHeadingsVO minorHeadingObj, List eb03ErrorList, boolean inOrOutNetworkPresent) {
		// get Mappings Map
		HashMap mappingsMap = (HashMap) minorHeadingObj.getMappings();
		Iterator iteratorMappings = mappingsMap.entrySet().iterator();
		while (iteratorMappings.hasNext()) {
			if (inOrOutNetworkPresent) {
				break;
			}
			ContractMappingVO dbMappingVO = (ContractMappingVO) ((Map.Entry) iteratorMappings
					.next()).getValue();
			String netWorkIndicator = null;
			if (null != dbMappingVO && dbMappingVO.isMapped()
					&& null != dbMappingVO.getEb03()) {
				// Getting the EB03 from Contract mapping
				List eb03ValuesList = dbMappingVO.getEb03()
						.getHippaCodeSelectedValues();
				Iterator eb03Iterator = eb03ValuesList.iterator();
				while (eb03Iterator.hasNext()) {
					if (inOrOutNetworkPresent) {
						break;
					}
					HippaCodeValue hippaCodeValue = (HippaCodeValue) eb03Iterator
					.next();
					String eb03FromContract = hippaCodeValue.getValue();
					// Getting EB03 from the EBString
					String eb03FromEBString = BxUtil
					.getHipaaSegmentValue(eBStringMappingVOCoins
							.getEb03());
					 Checking whether E019 already reported for the same EB03, this check is required as the EBString may have duplicates.
					Also Comparing the EB03 values from the EBString and DB
					if (eb03FromEBString.equalsIgnoreCase(eb03FromContract)) {
						if ((null != dbMappingVO.getVariable())
								&& !(DomainConstants.STATUS_NOT_APPLICABLE
										.equalsIgnoreCase(dbMappingVO.getVariable()
												.getVariableStatus())) && (null != dbMappingVO
										.getVariable().getPva())) {
							netWorkIndicator = dbMappingVO.getVariable().getPva()
									.trim();
						}
						if ((getInNetworkPVAMappings().contains(netWorkIndicator) 
								|| getInOutNetworkPVAMappings().contains(netWorkIndicator))) {
							inOrOutNetworkPresent = true;
							break;
						}
					}
				}
			}
		}
	}*/
	
	/**
	 * Method to check in EWPD 1) whether the EB03 values are same in EBString and DB ContractMappingVO
	 * 2) if the ContractMappingVO is having an in network SPS mapped
	 * 
	 * @param eBStringMappingVOCoins
	 * @param minorHeadingObj
	 *//*
	private void checkE019ForZeroCoinsuranceInNetworkEWPD(ContractMappingVO eBStringMappingVOCoins, MinorHeadingsVO minorHeadingObj, List eb03ErrorList, boolean inOrOutNetworkPresent) {
		if (null != minorHeadingObj
				&& null != minorHeadingObj.getRuleMapping()
				&& null != minorHeadingObj.getRuleMapping().getVariableMappingStatus()) {
			if (!"NOT_APPLICABLE".equals(minorHeadingObj.getRuleMapping()
					.getVariableMappingStatus())) {
				HippaSegment hippaSegmentEB03 = minorHeadingObj.getRuleMapping().getEb03();
				if (null != hippaSegmentEB03) {
					List eb03ValuesList = hippaSegmentEB03.getHippaCodeSelectedValues();
					Iterator eb03Iterator = eb03ValuesList.iterator();
					while (eb03Iterator.hasNext()) {
						if (inOrOutNetworkPresent) {
							break;
						}
						HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator.next();
						String eb03FromDB = hippaCodeValue03.getValue();
						
						String eb03FromEbString = BxUtil.getHipaaSegmentValue(eBStringMappingVOCoins
								.getEb03());
						 Checking whether E019 already reported for the same EB03, this check is required as the EBString may have duplicates.
						Also Comparing the EB03 values from the EBString and DB
						if ((eb03FromEbString).equalsIgnoreCase(eb03FromDB)) {
							// get mappings map
							HashMap mappingsMap = (HashMap) minorHeadingObj.getMappings();
							Iterator iteratorMappings = mappingsMap.entrySet().iterator();
							while (iteratorMappings.hasNext()) {
								if (inOrOutNetworkPresent) {
									break;
								}
								ContractMappingVO dbMappingVO = (ContractMappingVO) ((Map.Entry) iteratorMappings.next()).getValue();
								if (null != dbMappingVO && dbMappingVO.isMapped()) {
									String networkIndicator = null;
									if (null != dbMappingVO.getSpsId()
											&& null != dbMappingVO.getSpsId().getLinePVA()) {
										networkIndicator = dbMappingVO.getSpsId().getLinePVA();
									}
									// Checking whether the contract is
									// having IN-NETWORK
									if ((getInNetworkPVAMappings().contains(
											networkIndicator) || getInOutNetworkPVAMappings().contains(networkIndicator))) {
										inOrOutNetworkPresent = true;
										break;
									}
								}
							}
						}
					}
				}
			}
		}
	}*/

	/**
	 * Set the E019 error in the ErrorDetailVO object and add to List errorListE019ForCoinsurance 
	 * @param eBStringMappingVOCoins
	 */
	private void setE019ForZeroCoinsuranceInNetwork(ContractMappingVO eBStringMappingVOCoins) {
		ErrorDetailVO errorDetailVO = new ErrorDetailVO();
		errorDetailVO.setError(true);
		errorDetailVO.setErrorCode(DomainConstants.ERROR_E019);
		errorDetailVO.setErrorDesc(DomainConstants.E019_DESCRIPTION);
		errorDetailVO.setNetworkDescription(DomainConstants.PVA_PAR);
		eBStringMappingVOCoins.getErrorCodesList().add(errorDetailVO);
		errorListE019ForCoinsurance.add(eBStringMappingVOCoins);
	}

	/**
	 * @param dBContractList
	 *            DB Contract list.
	 * @param mQErrorCodesList
	 *            MQ Error codes list.
	 */
	public void combineMQAndDBErrorCodesList(List dBContractList,
			List mQErrorCodesList) {
		if (null != dBContractList) {
			for (int i = 0; i < dBContractList.size(); i++) {
				ContractVO contractVO = (ContractVO) dBContractList.get(i);
				if (null != contractVO) {
					ErrorExclusionDetailVO exclusionDetailVO = contractVO
							.getErrorExclusionDetailVO();
					if (null != mQErrorCodesList) {
						for (int j = 0; j < mQErrorCodesList.size(); j++) {
							ContractMappingVO mappingVO = (ContractMappingVO) mQErrorCodesList
									.get(j);
							if (null != mappingVO) {
								List errorList = mappingVO.getErrorCodesList();
								if (null != errorList && errorList.size() > 0) {
									// Get the first list in the error list
									// alone.
									// Exclusions obtained from the Error
									// exclusion detail vo.
									ErrorDetailVO detailVO = (ErrorDetailVO) errorList
											.get(0);
									if (null != detailVO
											&& null != detailVO.getErrorCode()) {
										if (DomainConstants.ERROR_E024
												.equals(detailVO.getErrorCode())) {
											if (null != exclusionDetailVO
													&& exclusionDetailVO
															.isE024ExclusionValidationEnableFlag()) {
												contractVO
														.getContractMappingVOList()
														.add(mappingVO);
												continue;
											}
										} else if (DomainConstants.ERROR_E016
												.equals(detailVO.getErrorCode())) {
											if (null != exclusionDetailVO
													&& exclusionDetailVO
															.isE016ExclusionValidationEnableFlag()) {
												if (contractVO
														.isFlagHCR_E016And17()) {
													contractVO
															.getContractMappingVOList()
															.add(mappingVO);
												}
												continue;
											}
										} else if (DomainConstants.ERROR_E019
												.equals(detailVO.getErrorCode())) {
											if (null != exclusionDetailVO
													&& exclusionDetailVO
															.isE019ExclusionValidationEnableFlag()) {
												contractVO
														.getContractMappingVOList()
														.add(mappingVO);
												continue;
											}

										}else if (DomainConstants.ERROR_E018
												.equals(detailVO.getErrorCode())) {
											if (null != exclusionDetailVO
													&& exclusionDetailVO
															.isE018ExclusionValidationEnableFlag()) {
												if (contractVO
														.isFlagHCR_E018()) {
													contractVO.getContractMappingVOList().add(mappingVO);
												}
												continue;
											}

										} else if (DomainConstants.ERROR_E028
												.equals(detailVO.getErrorCode())) {
											if (null != exclusionDetailVO
													&& exclusionDetailVO
															.isE028ExclusionValidationEnableFlag()) {
												contractVO
														.getContractMappingVOList()
														.add(mappingVO);
												continue;
											}
										}else if (DomainConstants.ERROR_E042
												.equals(detailVO.getErrorCode())) {
											if (null != exclusionDetailVO
													&& exclusionDetailVO
															.isE042ExclusionValidationEnableFlag()) {
												contractVO
														.getContractMappingVOList()
														.add(mappingVO);
												continue;
											}
										} else {
											// Setting the MQ exception based
											// error on ContractVO itself.
											contractVO.getErrorCodesList().add(
													detailVO);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * This method is to check the out network condition for E027. 1) If A/B
	 * coded as in network, There should be an A/B coded as out network,
	 * otherwise throw E027 2) If neither A nor B found (regardless of network
	 * indicator), then throw E027.
	 * 
	 * @param contract
	 * @param eb03Value
	 * @return
	 */
	protected boolean checkOutNetworkErrorConditionForE027(String eb03Value) {
		boolean errorIndicator = false;
		if (eb03AIN.contains(eb03Value) || eb03BIN.contains(eb03Value)) {
			if (!eb03AOUT.contains(eb03Value) && !eb03BOUT.contains(eb03Value)) {
				errorIndicator = true;
			}
		} else if (!eb03AOUT.contains(eb03Value)
				&& !eb03BOUT.contains(eb03Value)
				&& !eb03AINOUT.contains(eb03Value)
				&& !eb03BINOUT.contains(eb03Value)) {
			errorIndicator = true;
		}
		return errorIndicator;
	}

	/**
	 * This method is to check the in network condition for E027. 1) If A/B
	 * coded as out network, There should be an A/B coded as in network,
	 * otherwise throw E027 2) If neither A nor B found (regardless of network
	 * indicator), then throw E027.
	 * 
	 * @param contract
	 * @param eb03Value
	 * @return
	 */
	protected boolean checkInNetworkErrorConditionForE027(String eb03Value) {
		boolean errorIndicator = false;
		if (eb03AOUT.contains(eb03Value) || eb03BOUT.contains(eb03Value)) {
			if (!eb03AIN.contains(eb03Value) && !eb03BIN.contains(eb03Value)) {
				errorIndicator = true;
			}
		} else if (!eb03AIN.contains(eb03Value) && !eb03BIN.contains(eb03Value)
				&& !eb03AINOUT.contains(eb03Value)
				&& !eb03BINOUT.contains(eb03Value)) {
			errorIndicator = true;
		}
		return errorIndicator;
	}

	/**
	 * 1) If neither A nor B found (regardless of network indicator), then throw
	 * E027. 2) If A/B coded as in network, There should be an A/B coded as out
	 * network, otherwise throw E027 3) If A/B coded as out network, There
	 * should be an A/B coded as in network, otherwise throw E027
	 * 
	 * @param contract
	 * @param eb03Value
	 * @return
	 */
	protected boolean checkInOutNetworkErrorConditionForE027(
			ContractVO contract, String eb03Value) {
		boolean errorIndicator = false;
		if (!eb03AINOUT.contains(eb03Value) && !eb03BINOUT.contains(eb03Value)
				&& !eb03AIN.contains(eb03Value) && !eb03BIN.contains(eb03Value)
				&& !eb03AOUT.contains(eb03Value)
				&& !eb03BOUT.contains(eb03Value)) {
			errorIndicator = true;
		} else {
			return checkInOutNetworkErrorConditionForE027(eb03Value);
		}
		return errorIndicator;
	}

	/**
	 * 1) If A/B coded as in network, There should be an A/B coded as out
	 * network, otherwise throw E027 2) If A/B coded as out network, There
	 * should be an A/B coded as in network, otherwise throw E027
	 * 
	 * @param eb03Value
	 * @return
	 */
	protected boolean checkInOutNetworkErrorConditionForE027(String eb03Value) {
		boolean errorIndicator = false;
		if (eb03AIN.contains(eb03Value) || eb03BIN.contains(eb03Value)) {
			if (!eb03AOUT.contains(eb03Value) && !eb03BOUT.contains(eb03Value)) {
				errorIndicator = true;
			}
		} else if (eb03AOUT.contains(eb03Value) || eb03BOUT.contains(eb03Value)) {
			if (!eb03AIN.contains(eb03Value) && !eb03BIN.contains(eb03Value)) {
				errorIndicator = true;
			}
		}
		return errorIndicator;
	}

	/**
	 * This method is to set the contract level error for E027.
	 * 
	 * @param contract
	 * @param eb03Value
	 * @param eb01Value
	 */
	protected void setContractLevelErrorForE027(ContractVO contract,
			String eb03Value, String eb01Value) {
		ContractMappingVO errorData = new ContractMappingVO();
		ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
		errorDetailsObject.setError(true);
		errorDetailsObject.setErrorCode(DomainConstants.ERROR_E027);
		errorDetailsObject.setErrorDesc(DomainConstants.E027_DESCRIPTION);
		errorData.getErrorCodesList().add(errorDetailsObject);
		errorData.setEb01(BxUtil.setHipaaSegmentValue("EB01", eb01Value));
		errorData.setEb03(BxUtil.setHipaaSegmentValue("EB03", eb03Value));
		contract.getContractMappingVOList().add(errorData);
	}

	public List getContractMappingVOList() {
		return contractMappingVOList;
	}

	public void setContractMappingVOList(List contractMappingVOList) {
		this.contractMappingVOList = contractMappingVOList;
	}

	/**
	 * @param contractVO
	 * @param errorExclusionDetailVO
	 *            Process Exclusion for error validation.
	 */
	public void processExclusionForErrorValidation(ContractVO contractVO,
			ErrorExclusionDetailVO errorExclusionDetailVO) {
		if (null != contractVO) {
			List exclusionsList = contractVO.getErrorExclusionVOList();

			if (null != exclusionsList && exclusionsList.size() > 0) {
				for (int i = 0; i < exclusionsList.size(); i++) {
					ErrorExclusionVO errorExclusionVO = (ErrorExclusionVO) exclusionsList
							.get(i);
					if (null != errorExclusionVO) {
						// If contract is the primary exclusion,, just add the
						// error code to a master list.
						if (DomainConstants.CONTRACT_IDENTIFIER
								.equalsIgnoreCase(errorExclusionVO
										.getPrimaryExclusion())
								&& DomainConstants.EMPTY
										.equalsIgnoreCase(ReferenceDataUtil
												.trimAndNullToEmptyString(errorExclusionVO
														.getSecondaryExclusion()))) {
							errorExclusionDetailVO
									.getContractProductLevelExclusions().add(
											errorExclusionVO.getErrorCode());
						}
						// If product check for the exact product as the query
						// is a like search and add error code to a master list.
						else if (DomainConstants.PRODUCT_IDENTIFIER
								.equalsIgnoreCase(errorExclusionVO
										.getPrimaryExclusion())
								&& DomainConstants.EMPTY
										.equalsIgnoreCase(ReferenceDataUtil
												.trimAndNullToEmptyString(errorExclusionVO
														.getSecondaryExclusion()))) {
							if (checkForExactProductInExclusions(
									errorExclusionVO.getPrimaryValues(),
									contractVO.getProductCode())) {
								errorExclusionDetailVO
										.getContractProductLevelExclusions()
										.add(errorExclusionVO.getErrorCode());
							}
						}
						// Validation for WPD system alone.
						else {
							if (DomainConstants.WPD_LG
									.equalsIgnoreCase(contractVO.getSystem())
									|| DomainConstants.WPD_ISG
											.equalsIgnoreCase(contractVO
													.getSystem())) {
								// If variable add the exclusion vo to a primary
								// variable list.
								if (DomainConstants.VARIABLE_IDENTIFIER
										.equalsIgnoreCase(errorExclusionVO
												.getPrimaryExclusion())
										&& DomainConstants.EMPTY
												.equalsIgnoreCase(ReferenceDataUtil
														.trimAndNullToEmptyString(errorExclusionVO
																.getSecondaryExclusion()))) {
									errorExclusionDetailVO
											.getVariableExclusionsList().add(
													errorExclusionVO);
								}
								// If contract and variable add secondary
								// exclusion to the secondaty list.
								else if (DomainConstants.CONTRACT_IDENTIFIER
										.equalsIgnoreCase(errorExclusionVO
												.getPrimaryExclusion())
										&& DomainConstants.VARIABLE_IDENTIFIER
												.equalsIgnoreCase(ReferenceDataUtil
														.trimAndNullToEmptyString(errorExclusionVO
																.getSecondaryExclusion()))) {
									errorExclusionDetailVO
											.getVariableExclusionsList().add(
													errorExclusionVO);
								}
								// If product and variable, add secondary
								// exclusion to secondary list.
								else if (DomainConstants.PRODUCT_IDENTIFIER
										.equalsIgnoreCase(errorExclusionVO
												.getPrimaryExclusion())
										&& DomainConstants.VARIABLE_IDENTIFIER
												.equalsIgnoreCase(ReferenceDataUtil
														.trimAndNullToEmptyString(errorExclusionVO
																.getSecondaryExclusion()))) {
									if (checkForExactProductInExclusions(
											errorExclusionVO.getPrimaryValues(),
											contractVO.getProductCode())) {
										errorExclusionDetailVO
												.getVariableExclusionsList()
												.add(errorExclusionVO);
									}
								}
							} else if (DomainConstants.EWPD
									.equalsIgnoreCase(contractVO.getSystem())) {
								// If SPS id add the exclusion vo to a primary
								// variable list.
								if (DomainConstants.SPS_IDENTIFIER
										.equalsIgnoreCase(errorExclusionVO
												.getPrimaryExclusion())
										&& DomainConstants.EMPTY
												.equalsIgnoreCase(ReferenceDataUtil
														.trimAndNullToEmptyString(errorExclusionVO
																.getSecondaryExclusion()))) {
									errorExclusionDetailVO
											.getSpsExclusionsList().add(
													errorExclusionVO);
								}
								// If contract and SPS ID add secondary
								// exclusion to the secondaty list.
								else if (DomainConstants.CONTRACT_IDENTIFIER
										.equalsIgnoreCase(errorExclusionVO
												.getPrimaryExclusion())
										&& DomainConstants.SPS_IDENTIFIER
												.equalsIgnoreCase(ReferenceDataUtil
														.trimAndNullToEmptyString(errorExclusionVO
																.getSecondaryExclusion()))) {
									errorExclusionDetailVO
											.getSpsExclusionsList().add(
													errorExclusionVO);
								}
								// If product and SPS, add secondary exclusion
								// to secondary list.
								else if (DomainConstants.PRODUCT_IDENTIFIER
										.equalsIgnoreCase(errorExclusionVO
												.getPrimaryExclusion())
										&& DomainConstants.SPS_IDENTIFIER
												.equalsIgnoreCase(ReferenceDataUtil
														.trimAndNullToEmptyString(errorExclusionVO
																.getSecondaryExclusion()))) {
									if (checkForExactProductInExclusions(
											errorExclusionVO.getPrimaryValues(),
											contractVO.getProductCode())) {
										errorExclusionDetailVO
												.getSpsExclusionsList().add(
														errorExclusionVO);
									}
								}
								// If Header rule id add the exclusion vo to a
								// primary variable list.
								else if (DomainConstants.HEADERRULE_IDENTIFIER
										.equalsIgnoreCase(errorExclusionVO
												.getPrimaryExclusion())
										&& DomainConstants.EMPTY
												.equalsIgnoreCase(ReferenceDataUtil
														.trimAndNullToEmptyString(errorExclusionVO
																.getSecondaryExclusion()))) {
									errorExclusionDetailVO
											.getHeaderRuleExclusionsList().add(
													errorExclusionVO);
								}
								// If contract and header rule ID add secondary
								// exclusion to the secondaty list.
								else if (DomainConstants.CONTRACT_IDENTIFIER
										.equalsIgnoreCase(errorExclusionVO
												.getPrimaryExclusion())
										&& DomainConstants.HEADERRULE_IDENTIFIER
												.equalsIgnoreCase(ReferenceDataUtil
														.trimAndNullToEmptyString(errorExclusionVO
																.getSecondaryExclusion()))) {
									errorExclusionDetailVO
											.getHeaderRuleExclusionsList().add(
													errorExclusionVO);
								}
								// If product and Header rule, add secondary
								// exclusion to secondary list.
								else if (DomainConstants.PRODUCT_IDENTIFIER
										.equalsIgnoreCase(errorExclusionVO
												.getPrimaryExclusion())
										&& DomainConstants.HEADERRULE_IDENTIFIER
												.equalsIgnoreCase(ReferenceDataUtil
														.trimAndNullToEmptyString(errorExclusionVO
																.getSecondaryExclusion()))) {
									if (checkForExactProductInExclusions(
											errorExclusionVO.getPrimaryValues(),
											contractVO.getProductCode())) {
										errorExclusionDetailVO
												.getHeaderRuleExclusionsList()
												.add(errorExclusionVO);
									}
								}
							}
						}

					}
				}
				populateErrorCodeFlags(errorExclusionDetailVO);
			}
		}
	}

	/**
	 * @param valueComaSeparated
	 * @param productCode
	 * @return Checking for exact product. The LIKE query will fetch all similar
	 *         products. That is why validation is implemented here.
	 */
	private boolean checkForExactProductInExclusions(String valueComaSeparated,
			String productCode) {
		productCode = ReferenceDataUtil.trimAndNullToEmptyString(productCode)
				.toUpperCase();
		String[] exclusionArray = null;
		if (null != valueComaSeparated) {
			exclusionArray = valueComaSeparated.split(",");
			if (null != exclusionArray) {
				for (int i = 0; i < exclusionArray.length; i++) {
					if (null != productCode
							&& productCode
									.equalsIgnoreCase(ReferenceDataUtil
											.trimAndNullToEmptyString(exclusionArray[i]))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @param errorExclusionDetailVO
	 *            Error exclusion detail vo.
	 */
	private void populateErrorCodeFlags(
			ErrorExclusionDetailVO errorExclusionDetailVO) {
		List primaryExclusionList = errorExclusionDetailVO
				.getContractProductLevelExclusions();
		if (null != primaryExclusionList && primaryExclusionList.size() > 0) {
			if (primaryExclusionList.contains(DomainConstants.ERROR_E001)) {
				errorExclusionDetailVO
						.setE001ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E004)) {
				errorExclusionDetailVO
						.setE004ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E005)) {
				errorExclusionDetailVO
						.setE005ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E006)) {
				errorExclusionDetailVO
						.setE006ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E007)) {
				errorExclusionDetailVO
						.setE007ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E008)) {
				errorExclusionDetailVO
						.setE008ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E009)) {
				errorExclusionDetailVO
						.setE009ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E010)) {
				errorExclusionDetailVO
						.setE010ExclusionValidationEnableFlag(false);
			}
			/*if (primaryExclusionList.contains(DomainConstants.ERROR_E011)) {
				errorExclusionDetailVO
						.setE011ExclusionValidationEnableFlag(false);
			}*/
			if (primaryExclusionList.contains(DomainConstants.ERROR_E012)) {
				errorExclusionDetailVO
						.setE012ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E014)) {
				errorExclusionDetailVO
						.setE014ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E016)) {
				errorExclusionDetailVO
						.setE016ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E017)) {
				errorExclusionDetailVO
						.setE017ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E018)) {
				errorExclusionDetailVO
						.setE018ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E019)) {
				errorExclusionDetailVO
						.setE019ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E020)) {
				errorExclusionDetailVO
						.setE020ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E021)) {
				errorExclusionDetailVO
						.setE021ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E022)) {
				errorExclusionDetailVO
						.setE022ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E023)) {
				errorExclusionDetailVO
						.setE023ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E024)) {
				errorExclusionDetailVO
						.setE024ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E025)) {
				errorExclusionDetailVO
						.setE025ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E026)) {
				errorExclusionDetailVO
						.setE026ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E027)) {
				errorExclusionDetailVO
						.setE027ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E028)) {
				errorExclusionDetailVO
						.setE028ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E029)) {
				errorExclusionDetailVO
						.setE029ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E030)) {
				errorExclusionDetailVO
						.setE030ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E038)) {
				errorExclusionDetailVO
						.setE038ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E039)) {
				errorExclusionDetailVO
						.setE039ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E040)) {
				errorExclusionDetailVO
						.setE040ExclusionValidationEnableFlag(false);
			}
			if (primaryExclusionList.contains(DomainConstants.ERROR_E042)) {
				errorExclusionDetailVO
						.setE042ExclusionValidationEnableFlag(false);
			}
		}
	}

	/**
	 * @return
	 */
	public ErrorExclusionDetailVO getErrorExclusionDetailVO() {
		return errorExclusionDetailVO;
	}

	/**
	 * @param errorExclusionDetailVO
	 */
	public void setErrorExclusionDetailVO(
			ErrorExclusionDetailVO errorExclusionDetailVO) {
		this.errorExclusionDetailVO = errorExclusionDetailVO;
	}

	/**
	 * Generic method to check for Rule E030 This method checks condition If
	 * EB01 = 'G' and EB03 not in 30/60/88/48/CG/CF/CH/CE, Then rule E030 is violated.
	 * 
	 * @param hippaSegmentEB01
	 * @param eb03ValuesList
	 * @return
	 */
	public static boolean checkForRuleE030(String eb01_Val, List eb03ValuesList) {
		boolean hasE030Error = false;
		if (null != eb01_Val && null != eb03ValuesList) {
			if (!DomainConstants.EMPTY.equals(eb01_Val)) {
				// Checking if EB01 = G
				if (DomainConstants.OUT_OF_POCKET.equals(eb01_Val)) {
					Iterator eb03Iterator = eb03ValuesList.iterator();
					while (eb03Iterator.hasNext()) {
						HippaCodeValue hippaCodeValue03 = (HippaCodeValue) eb03Iterator
								.next();
						String hippaCodeValue = hippaCodeValue03.getValue();
						if ((!"".equals(hippaCodeValue))
								&& !SimulationResourceValueLoader
										.getEB03ForE030().contains(
												hippaCodeValue)) {
							hasE030Error = true;
							break;
						}
					}
				}
			}
		}
		return hasE030Error;
	}

	/**
	 * Generic method to check for Rule E026 This method checks condition for
	 * Rule - E026
	 * 
	 * If EB01=B & EB06=26 & EB03 = 98 / 99 / 68 / 80 / 81 / 82 / 83 / BH /
	 * ‘SS/SP’ / UC / 33 / A6 / AD / AE / AF / 6 / 48 / 47 / 69 / 65 / A7 / AI /
	 * 50 / 13 / 53 / 52 / 51 / 86 / A8 / 61 / 62 / 76 / 78 / 84 / 93 / A0 / A3
	 * / AG / BG / 12 / 18 / 45
	 * 
	 * If EB01=B & EB06=27 & EB03 = 48 / 47 / 65 / A7 / AG
	 * 
	 * If EB01=B & EB06=36 & EB03 = 50 / 52 / 51 / 86 / A8 / 12 / 18 / 62 / 4 /
	 * 5 / 73
	 * 
	 * If EB01=B & EB06=7 & EB03 = 50 / 52 / 51 / 86 / A8 / 62 / 4 / 5 / 73
	 */
	public static boolean checkForRuleE026(String eb01Val, List eb03ValuesList,
			String eb06Val) {
		boolean hippacodeEB03forE026 = false;
		if (null != eb06Val && null != eb01Val && null != eb03ValuesList) {
			// Checking if EB01 = B
			if (DomainConstants.COPAYMENT.equals(eb01Val)) {
				// Check if EB06 is null
				Iterator eb03Iterator = eb03ValuesList.iterator();
				HippaCodeValue hippaCodeValue03 = new HippaCodeValue();
				String hippaCodeValue = "";
				// Check if EB06=26
				if (DomainConstants.EB06_26.equals(eb06Val)) {
					while (eb03Iterator.hasNext()) {
						hippaCodeValue03 = (HippaCodeValue) eb03Iterator.next();
						hippaCodeValue = hippaCodeValue03.getValue();
						if (getEB03ForE026_1().contains(hippaCodeValue)) {
							hippacodeEB03forE026 = true;
							break;
						}
					}
				} else if (DomainConstants.EB06_27.equals(eb06Val)) { // Check
					// if
					// EB06=27
					while (eb03Iterator.hasNext()) {
						hippaCodeValue03 = (HippaCodeValue) eb03Iterator.next();
						hippaCodeValue = hippaCodeValue03.getValue();
						if (getEB03ForE026_2().contains(hippaCodeValue)) {
							hippacodeEB03forE026 = true;
							break;
						}
					}
				} else if (DomainConstants.EB06_36.equals(eb06Val)) { // Check
					// if
					// EB06=36
					while (eb03Iterator.hasNext()) {
						hippaCodeValue03 = (HippaCodeValue) eb03Iterator.next();
						hippaCodeValue = hippaCodeValue03.getValue();
						if (getEB03ForE026_3().contains(hippaCodeValue)) {
							hippacodeEB03forE026 = true;
							break;
						}
					}
				} else if (DomainConstants.EB06_7.equals(eb06Val)) { // Check if
					// EB06=7
					while (eb03Iterator.hasNext()) {
						hippaCodeValue03 = (HippaCodeValue) eb03Iterator.next();
						hippaCodeValue = hippaCodeValue03.getValue();
						if (getEB03ForE026_4().contains(hippaCodeValue)) {
							hippacodeEB03forE026 = true;
							break;
						}
					}
				}
			}
		}
		return hippacodeEB03forE026;
	}

	/**
	 * Validation for Rule E029 The rule shall be identified if the contract
	 * variable coded in the BX table has ‘worldwide’ or ‘WW’ in the variable
	 * description or major heading.
	 * 
	 * @param variableDescription
	 * @param minorHeadingDesc
	 * @return boolean
	 */
	public static boolean checkForRuleE029(String variableDescription,
			String minorHeadingDesc, String majorHeadingDesc) {
		boolean hasE029Error = false;
		if ((null != variableDescription && !DomainConstants.EMPTY
				.equals(variableDescription))) {
			if (null != minorHeadingDesc) {
				minorHeadingDesc = minorHeadingDesc.toUpperCase();
			} else {
				minorHeadingDesc = "";
			}
			if (null != majorHeadingDesc) {
				majorHeadingDesc = majorHeadingDesc.toUpperCase();
			} else {
				majorHeadingDesc = "";
			}
			variableDescription = variableDescription.toUpperCase();
			if ((variableDescription.indexOf(DomainConstants.SUBSTRING_WW) != -1)
					|| (variableDescription
							.indexOf(DomainConstants.SUBSTRING_WORLDWIDE) != -1)
					|| (minorHeadingDesc.indexOf(DomainConstants.SUBSTRING_WW) != -1)
					|| (minorHeadingDesc
							.indexOf(DomainConstants.SUBSTRING_WORLDWIDE) != -1)
					|| (majorHeadingDesc
							.indexOf(DomainConstants.MAJOR_HEADING_VAL_E029) != -1)) {
				hasE029Error = true;
			}
		}
		return hasE029Error;
	}

	// Validation of PENALTY for (10.1 ) variable description contains penalty
	// and message doesn't contain penalty
	public static boolean checkForRuleW029(String variableDescription,
			String messageText) {
		boolean hasW029Warning = false;
		if ((null != variableDescription && !DomainConstants.EMPTY
				.equals(variableDescription))) {

			variableDescription = variableDescription.toUpperCase();
			if (((variableDescription.indexOf(DomainConstants.SUBSTRING_PENALTY) != -1)||(variableDescription.indexOf(DomainConstants.SUBSTRING_PENLTY) != -1)||(variableDescription.indexOf(DomainConstants.SUBSTRING_PNLTY) != -1)) 
					&& (!(messageText
							.indexOf(DomainConstants.SUBSTRING_PENALTY) != -1) && !(messageText
							.equals("")))) {
				hasW029Warning = true;
			}
		}
		return hasW029Warning;
	}

	// Validation of PENALTY for (10.2) variable description contains penalty
	// and message is blank
	public static boolean checkForRuleWB029(String variableDescription,
			String messageText) {
		boolean hasWB029Warning = false;
		if ((null != variableDescription && !DomainConstants.EMPTY
				.equals(variableDescription))) {

			variableDescription = variableDescription.toUpperCase();
			if (((variableDescription.indexOf(DomainConstants.SUBSTRING_PENALTY) != -1)||(variableDescription.indexOf(DomainConstants.SUBSTRING_PENLTY) != -1)||(variableDescription.indexOf(DomainConstants.SUBSTRING_PNLTY) != -1))
					&& (messageText.equals(""))) {
				hasWB029Warning = true;
			}
		}
		return hasWB029Warning;
	}

	// Validation of PENALTY for (10.3 ) sps description contains penalty and
	// message doesn't contains penalty
	public static boolean checkForRuleW022(String spsDescription,
			String messageText) {
		boolean hasW022Warning = false;
		if ((null != spsDescription && !DomainConstants.EMPTY
				.equals(spsDescription))) {

			spsDescription = spsDescription.toUpperCase();

			if ((spsDescription.indexOf(DomainConstants.SUBSTRING_PENALTY1) != -1)
					&& ((null != messageText)
							&& !(messageText.trim().indexOf(
									DomainConstants.SUBSTRING_PENALTY1) != -1) && !(messageText
							.equals("")))) {
				hasW022Warning = true;
			}
		}
		return hasW022Warning;
	}

	// Validation of PENALTY for (10.3 ) sps description contains penalty and
	// message is blank
	public static boolean checkForRuleWB022(String spsDescription,
			String messageText) {
		boolean hasWB022Warning = false;
		if ((null != spsDescription && !DomainConstants.EMPTY
				.equals(spsDescription))) {

			spsDescription = spsDescription.toUpperCase();

			if ((spsDescription.indexOf(DomainConstants.SUBSTRING_PENALTY1) != -1)
					&& ((null != messageText)
							&& !(messageText.trim().indexOf(
									DomainConstants.SUBSTRING_PENALTY1) != -1) && (messageText
							.equals("")))) {
				hasWB022Warning = true;
			}
		}
		return hasWB022Warning;
	}

	/**
	 * Validation for Rule E029 The rule shall be identified for messages with
	 * substring ‘worldwide’ or ‘WW’ in the message text.
	 * 
	 * @param variableDescription
	 * @param minorHeadingDesc
	 * @return boolean
	 */

	public static boolean checkForRuleE029(String messageText) {
		boolean hasE029Error = false;
		if (null != messageText && !DomainConstants.EMPTY.equals(messageText)) {
			messageText = messageText.toUpperCase();
			if ((messageText.indexOf(DomainConstants.SUBSTRING_WW) != -1)
					|| (messageText
							.indexOf(DomainConstants.SUBSTRING_WORLDWIDE) != -1)) {
				hasE029Error = true;
			}
		}
		return hasE029Error;
	}
	/**
	 * This method checks condition for Error Code - E031 If EB06 = 22, 23, 25,
	 * 32 or null and and there no look up indicator E031 is thrown
	 * 
	 * @param mapping
	 * 
	 * @return e031ErrorFlag
	 */
	protected boolean checkForRuleE031(String eb06_Value,
			String accumValue, List accumList) {

		boolean e031ErrorFlag = false;
		for (int i = 0; i < accumList.size(); i++) {
			Accumulator accumulator = (Accumulator) accumList.get(i);
			if (accumulator.getSvcCode().equalsIgnoreCase(accumValue)&& null!=accumulator.getLookupInd()) {
				if ((DomainConstants.EB06_32).equals(eb06_Value)) {   
					if (!((DomainConstants.LT).equalsIgnoreCase(accumulator.getLookupInd()))) {
						e031ErrorFlag = true;
						break;
					}
				} else if ((DomainConstants.EB06_22).equals(eb06_Value)) {
					if (!((DomainConstants.TB).equalsIgnoreCase(accumulator.getLookupInd()))
							&& !((DomainConstants.BY).equalsIgnoreCase(accumulator.getLookupInd()))
							&& !((DomainConstants.CT).equalsIgnoreCase(accumulator.getLookupInd()))) {
						e031ErrorFlag = true;
						break;

					}
				} else if ((DomainConstants.EB06_23).equals(eb06_Value)) {
					if (!((DomainConstants.TB).equalsIgnoreCase(accumulator.getLookupInd()))
							&& !((DomainConstants.CY).equalsIgnoreCase(accumulator.getLookupInd()))
							&& !((DomainConstants.CT).equalsIgnoreCase(accumulator.getLookupInd()))) {
						e031ErrorFlag = true;
						break;

					}
				} else if ((DomainConstants.EB06_25).equals(eb06_Value)
						|| (DomainConstants.EB06_BLANK).equalsIgnoreCase(eb06_Value)||null==eb06_Value) {
					if (!((DomainConstants.TB).equalsIgnoreCase(accumulator.getLookupInd()))
							&& !((DomainConstants.CY).equalsIgnoreCase(accumulator.getLookupInd()))
							&& !((DomainConstants.CT).equalsIgnoreCase(accumulator.getLookupInd()))
							&& !((DomainConstants.BY).equalsIgnoreCase(accumulator.getLookupInd()))) {
						e031ErrorFlag = true;
						break;

					}
				}
			}
		}
		return e031ErrorFlag;

	}

	/**
	 * This method checks condition for Error Code - E032 If the variabel format
	 * and its corresponding day flag and occurs flag and monies flag E032 is
	 * thrown
	 * 
	 * @param mapping
	 * 
	 * @return e032ErrorFlag
	 */
	protected boolean checkForRuleE032(String variableFormat,
			String accumValue, List accumList) {

		boolean e032ErrorFlag = false;

		for (int i = 0; i < accumList.size(); i++) {
			Accumulator accumulator = (Accumulator) accumList.get(i);
			if (accumulator.getSvcCode().equalsIgnoreCase(accumValue)) {
				if ((DomainConstants.DAY).equalsIgnoreCase(variableFormat)
						|| (DomainConstants.DAYS).equalsIgnoreCase(variableFormat)
						|| (DomainConstants.LEN).equalsIgnoreCase(variableFormat)) {
					if (null!=accumulator.getDaysFlg()&& (DomainConstants.N).equals(accumulator.getDaysFlg())) {
						e032ErrorFlag = true;
						break;
					}
				} else if ((DomainConstants.VST).equalsIgnoreCase(variableFormat)
						|| (DomainConstants.OCRS).equalsIgnoreCase(variableFormat)
						|| (DomainConstants.OCC).equalsIgnoreCase(variableFormat)) {
					if (null!=accumulator.getOccursFlg()&& (DomainConstants.N).equals(accumulator.getOccursFlg())) {
						e032ErrorFlag = true;
						break;
					}
				} else if (variableFormat.equalsIgnoreCase(DomainConstants.DOL)) {
					if (null!=accumulator.getMoniesFlg()&& (DomainConstants.N).equals(accumulator.getMoniesFlg())) {
						e032ErrorFlag = true;
						break;
					}
				}
			}
		}
		return e032ErrorFlag;
	}

	/**
	 * This method checks condition for Error Code - E032 If the variabel format
	 * and its corresponding day flag and occurs flag and monies flag E032 is
	 * thrown
	 * 
	 * @param mapping
	 * 
	 * @return e033ErrorFlag
	 */
	protected boolean checkForRuleE033(String eb02_Value,
			String accumValue, List accumList) {

		boolean e033ErrorFlag = false;
		for (int i = 0; i < accumList.size(); i++) {
			Accumulator accumulator = (Accumulator) accumList.get(i);
			if (accumValue.equalsIgnoreCase(accumulator.getSvcCode())&& null!=accumulator.getRootMbrCde()) {
				if ((DomainConstants.IND).equalsIgnoreCase(eb02_Value)) {
					if ((DomainConstants.FAC).equalsIgnoreCase(accumulator.getRootMbrCde())) {
						e033ErrorFlag = true;
						break;
					}
				} else if ((DomainConstants.FAM).equalsIgnoreCase(eb02_Value)) {
					if ((DomainConstants.MAC).equalsIgnoreCase(accumulator.getRootMbrCde())) {
						e033ErrorFlag = true;
						break;
					}
				}
			}
		}

		return e033ErrorFlag;

	}
	/**
	 * This method does exclusion of error code - eg: In E005 error code -if
	 * E004, E006 and E007 present then E005 must be excluded.
	 * 
	 * @param contractVOList
	 * @param exclusionArray
	 * @param errorToBeExcluded
	 * @return
	 */
	protected List errorExclusion(List contractVOListForExclusion,
			String errorToBeExcluded, String exclusionArray[]) {
		if (contractVOListForExclusion != null
				&& contractVOListForExclusion.size() > 0) {
			Iterator contractIter = contractVOListForExclusion.iterator();
			while (contractIter.hasNext()) {
				ContractVO contractForExclusion = (ContractVO) contractIter
						.next();
				HashMap majorHeadingMap = (HashMap) contractForExclusion
						.getMajorHeadings();
				if (majorHeadingMap.size() > 0) {
					Iterator itrMajor = majorHeadingMap.keySet().iterator();
					while (itrMajor.hasNext()) {
						String keyMajor = (String) itrMajor.next();
						MajorHeadingsVO majorHdngObj = (MajorHeadingsVO) majorHeadingMap
								.get(keyMajor);
						HashMap minorHdngsMap = (HashMap) majorHdngObj
								.getMinorHeadings();
						Iterator itrMinor = minorHdngsMap.keySet().iterator();
						while (itrMinor.hasNext()) {
							String keyMinor = (String) itrMinor.next();
							MinorHeadingsVO minorHdngObj = (MinorHeadingsVO) minorHdngsMap
									.get(keyMinor);
							HashMap minorHdngMap = (HashMap) minorHdngObj
									.getMappings();
							Iterator itrMappings = minorHdngMap.entrySet()
									.iterator();
							while (itrMappings.hasNext()) {
								ContractMappingVO contractappingObj = (ContractMappingVO) ((Map.Entry) itrMappings
										.next()).getValue();
								List newErrorCodeList = new ArrayList();
								List errorCodeList = contractappingObj
										.getErrorCodesList();

								List errorCodeToBeExcluded = new ArrayList();
								List exclusionErrorCodeList = new ArrayList();
								if(null != errorCodeList && errorCodeList.size() > 0){
								Iterator errorCodeitr = errorCodeList
										.iterator();
								while (errorCodeitr.hasNext()) {

										ErrorDetailVO errorDetails = (ErrorDetailVO) errorCodeitr
												.next();
										if (null != errorDetails
												&& null != errorDetails
														.getErrorCode()) {
											if (errorDetails.getErrorCode()
													.equals(errorToBeExcluded)) {
												errorCodeToBeExcluded
														.add(errorDetails);
											} else {
												boolean includesExclusionList = false;
												for (int itr = 0; itr < exclusionArray.length; itr++) {
													if (errorDetails
															.getErrorCode()
															.equals(
																	exclusionArray[itr])) {
														includesExclusionList = true;
														break;
													}
												}
												if (includesExclusionList) {
													exclusionErrorCodeList
															.add(errorDetails);
												} else {
													newErrorCodeList
															.add(errorDetails);
												}
											}
										}
									}
								}
								if (errorCodeToBeExcluded != null
										&& errorCodeToBeExcluded.size() > 0) {
									if (exclusionErrorCodeList != null
											&& exclusionErrorCodeList.size() > 0) {
										Iterator exclusionListItr = exclusionErrorCodeList
												.iterator();
										while (exclusionListItr.hasNext()) {
											ErrorDetailVO errorDetailsOfExclusion = (ErrorDetailVO) exclusionListItr
													.next();
											newErrorCodeList
													.add(errorDetailsOfExclusion);
										}
									} else {
										newErrorCodeList
												.add(errorCodeToBeExcluded
														.get(0));
									}
								} else {
									if (exclusionErrorCodeList != null) {
										Iterator exclusionListItr = exclusionErrorCodeList
												.iterator();
										while (exclusionListItr.hasNext()) {
											ErrorDetailVO errorDetailsOfExclusion = (ErrorDetailVO) exclusionListItr
													.next();
											newErrorCodeList
													.add(errorDetailsOfExclusion);
										}
									}
								}
								if (newErrorCodeList != null) {
									contractappingObj
											.setErrorCodesList(newErrorCodeList);
								}

							}
						}
					}
				}
			}

		}
		return contractVOListForExclusion;
	}
/***************************SSCR 14181 April 2012 Release  START**********************************/	
	/**
	 * The method provides the Vision Minor headings for E038
	 * 
	 * @return List
	 */
	protected List getVisionMinorHeadingsForE038() {
		List minorHeadingsList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.VISION_MINOR_HEADINGS_FOR_E038,
				DomainConstants.PROPERTY_FILE_NAME);

		return minorHeadingsList;
	}
	/**
	 * The method provides the  Minor heading descriptions for E038
	 * 
	 * @return List
	 */
	protected List getMinorHeadingDescriptionsForE038() {
		List minorHeadingsDescriptionList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.MINOR_HEADING_DESC_FOR_E038,
				DomainConstants.PROPERTY_FILE_NAME);

		return minorHeadingsDescriptionList;
	}
	/**
	 * The method provides the Vision Minor headings for E038
	 * 
	 * @return List
	 */
	protected List getVisionMinorHeadingsForE039() {
		List minorHeadingsList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.DENTAL_MINOR_HEADINGS_FOR_E039,
				DomainConstants.PROPERTY_FILE_NAME);

		return minorHeadingsList;
	}
	/**
	 * The method provides the Vision Major heading for E038
	 * 
	 * @return List
	 */
	protected List getVisionMajorHeadingsForE038() {
		List majorHeadingsList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.VISION_MAJOR_HEADING_FOR_E038,
				DomainConstants.PROPERTY_FILE_NAME);

		return majorHeadingsList;
	}
	/**
	 * The method provides the Dental Major heading for E039
	 * 
	 * @return List
	 */
	protected List getDentalMajorHeadingsForE039() {
		List majorHeadingsList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.DENTAL_MINOR_HEADING_FOR_E039,
				DomainConstants.PROPERTY_FILE_NAME);

		return majorHeadingsList;
	}
	/**
	 * This method is to set the contract level error for E038.
	 * @param contract
	 */
	protected void setContractLevelErrorForE038(ContractVO contract) {
		ContractMappingVO errorData = new ContractMappingVO();
		ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
		errorDetailsObject.setError(true);
		errorDetailsObject.setErrorCode(DomainConstants.ERROR_E038);
		errorDetailsObject.setErrorDesc(DomainConstants.E038_DESCRIPTION);
		errorData.getErrorCodesList().add(errorDetailsObject);
		contract.getContractMappingVOList().add(errorData);
	}
	/**
	 * This method is to set the contract level error for E038.
	 * @param contract
	 */
	protected void setContractLevelErrorForE039(ContractVO contract) {
		ContractMappingVO errorData = new ContractMappingVO();
		ErrorDetailVO errorDetailsObject = new ErrorDetailVO();
		errorDetailsObject.setError(true);
		errorDetailsObject.setErrorCode(DomainConstants.ERROR_E039);
		errorDetailsObject.setErrorDesc(DomainConstants.E039_DESCRIPTION);
		errorData.getErrorCodesList().add(errorDetailsObject);
		contract.getContractMappingVOList().add(errorData);
	}
/***************************SSCR 14181 April 2012 Release  END**********************************/	
}
