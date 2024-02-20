/*
 * <Mapping.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.bx.mapping.domain.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wellpoint.ets.bx.mapping.application.security.Lock;
import com.wellpoint.ets.bx.mapping.domain.exception.DomainException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentService;
import com.wellpoint.ets.bx.mapping.domain.service.HippaSegmentValidator;
import com.wellpoint.ets.bx.mapping.domain.service.ValidationUtility;
import com.wellpoint.ets.bx.mapping.domain.service.ValidatorConstants;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentValidationResult;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.bx.mapping.factory.HippaSegmentValidationFactory;
import com.wellpoint.ets.ebx.mapping.domain.service.LocateService;
import com.wellpoint.ets.ebx.mapping.domain.service.ValidationService;
import com.wellpoint.ets.ebx.simulation.util.ContractMappingValidator;
import com.wellpoint.ets.ebx.simulation.vo.ContractMappingVO;

public class Mapping implements Comparable {

	/**
	 * @uml.annotations for <code>auditTrail</code>
	 *                  collection_type="com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail"
	 */
	private List auditTrails = new ArrayList();

	private Variable variable;

	private HippaSegment hippaSegment;

	private Long variableSystemId;

	private HippaSegment eb01;

	private HippaSegment eb02;

	private HippaSegment eb03;

	private HippaSegment eb06;

	private HippaSegment eb09;

	private HippaSegment ii02;

	private HippaSegment hsd01;

	private HippaSegment hsd02;

	private HippaSegment hsd03;

	private HippaSegment hsd04;

	private HippaSegment hsd05;

	private HippaSegment hsd06;

	private HippaSegment hsd07;

	private HippaSegment hsd08;

	private HippaSegment accum;

	private HippaSegment utilizationMgmntRule;

	private List eb03HippaSegment;

	private List accumHippaSegment;

	private String msg_type_required;

	private String sensitiveBenefitIndicator;

	private HippaSegment noteTypeCode;

	private String message;
	
	private String eb01_extndMsgTxt1;
	private String eb01_extndMsgTxt2;
	private String eb01_extndMsgTxt3;	
	private String eb01_eb12ChangeInd;
	
	private String extndMsgTxt1;
	private String extndMsgTxt2;
	private String extndMsgTxt3;	
	private String eb12ChangeInd;

	private HippaSegmentValidationFactory mappingValidationFactory;

	private String variableMappingStatus;

	private String inTempTable;

	private String isMapgRequired;

	private Date lastChangedTmStamp;

	private Date createdTmStamp;

	private List variableList;
	
	private List wpdAccumList;

	private User user = new User();

	private boolean updateMstr;

	private HippaSegmentService hippaSegmentService;

	private Rule rule;

	private SPSId spsId;

	private boolean finalizedFlagModified;

	private boolean finalized;
	// SIT Fix
	private String datafeedStatus = "N";
	/**
	 * MTM CODE CHANGE
	 */
	private String mappingComplete;
	/**
	 * END
	 */
	// SIT FIX
	private boolean mapped = false;

	private ContractMappingVO contractMapping;

	// for section 3
	private LocateService locateRuleIdService;

	private ValidationService wpdValidationService;

	private ValidationService ewpdValidationService;

	// for datafeed requirement default is N
	private String dataFeedInd = "N";

	private Lock lock;

	private String sortField;

	// variable for checking the flow ,based on this varaible application
	// navigate back to adavance search and viewlanding page
	private String pageFrom;

	private String formattedStringDate;

	private String businessEntity;
	private String businessGroup;
	private String mbu;
	
	//Audit Lock Status  -- October Release
	private String auditLock;
	
	//auditStatus for audit trail --part of october release.
	private int variableStatusForAuditTrail;
	
	//Start Age/End Age --part of BXNI June2012 Release.
	private HippaSegment startAge;
	
	private HippaSegment endAge;
	
	private String startAgeCodedValue;
	
	private String endAgeCodedValue;
	
	// CR29
	private String procedureExcludedInd;
	
	// SSCR19537
	/** Attribute to hold whether the mapping initiated at Individual level*/
	private String indvdlEb03AssnIndicator;
	Map<String, String> eb03III02Map;
	
	
	// NM01 Message Segment
	private HippaSegment nm1MessageSegment;

	/**
	 * @return the variableStatusForAuditTrail
	 */
	public int getVariableStatusForAuditTrail() {
		return variableStatusForAuditTrail;
	}

	/**
	 * @param variableStatusForAuditTrail the variableStatusForAuditTrail to set
	 */
	public void setVariableStatusForAuditTrail(int variableStatusForAuditTrail) {
		this.variableStatusForAuditTrail = variableStatusForAuditTrail;
	}

	public Mapping() {

	}

	// for section 3
	/**
	 * Method to set the HippaSegment object based on whether the mapping object
	 * has a variable entity or an SPS Id
	 * 
	 * @param hippaSegmentName
	 * @return
	 */
	private HippaSegment getHippaSegmentsPossibleList(String hippaSegmentName) {

		hippaSegment = new HippaSegment();
		ValidationUtility validationUtility = new ValidationUtility();

		if (null != this.getVariable()) {

			hippaSegment = this
					.getHippaSegmentService()
					.getAvailableHippaSegmentValues(
							hippaSegmentName,
							this.getVariable(),
							DomainConstants.CONDN_TO_FETCH_HIPPACODE_PSBLE_VALUES);
		} else if (null != this.getRule() || null != this.getSpsId()) {

			Catalog catalog = new Catalog();
			catalog.setCatalogName(hippaSegmentName);

			if (DomainConstants.EB01_NAME.equals(hippaSegmentName)) {

				List spsFormats = new ArrayList();
				if (null != this.getSpsId()) {

					if (null != this.getSpsId().getSpsDetail()
							&& (!this.getSpsId().getSpsDetail().isEmpty())) {

						spsFormats = validationUtility
								.getSpsFormatsFromSPSDetail(this.getSpsId()
										.getSpsDetail());
					}
				}
				/* Reference Data Values - START */
				hippaSegment.setName(hippaSegmentName);
				hippaSegment.setHippaCodePossibleValues(this
						.getLocateRuleIdService().getItems(catalog, null,
								false, spsFormats));
				/* Reference Data Values - END */
			} else {

				/* Reference Data Values - START */
				hippaSegment.setName(hippaSegmentName);
				hippaSegment.setHippaCodePossibleValues(this
						.getLocateRuleIdService().getItems(catalog, null,
								false, null));
				/* Reference Data Values - END */
			}
		}
		return hippaSegment;
	}

	public List validateMappings() throws DomainException {
		List hippaSgmtVldnRsltLst = new ArrayList();
		List validationResult = new ArrayList();
		HippaSegmentValidationResult hippaSegmentResult = new HippaSegmentValidationResult();
		hippaSegment = new HippaSegment();
		hippaSegmentResult.setMapping(this);
		
		//Adding for SSCR 19537
		boolean individualMappingExist = false;
		if(null != this && null != this.getIndvdlEb03AssnIndicator() && this.getIndvdlEb03AssnIndicator().equalsIgnoreCase("Y")){
			if(null != this.getEb03() && null != this.getEb03().getEb03Association() && !this.getEb03().getEb03Association().isEmpty()){
				individualMappingExist = true;
			}
			
		}
		boolean isHSD02LimitationVariablePresent = isHSD02LimitationVariablePresent();
		if(!isHSD02LimitationVariablePresent){
			
		
		// Gets the hippasegment possible values for EB01 and validates EB01
		if ((null != this.getEb01())
				&& (this.getEb01().getName()
						.equalsIgnoreCase(DomainConstants.EB01_NAME))) {
			hippaSegment = getHippaSegmentsPossibleList(this.getEb01()
					.getName());
			this.getEb01().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());
			// invoking the validation for EB01
			validationResult = validateMappings(this.getEb01().getName());
			// iterate the list and add the hippaSegmentResult object to the
			// final list
			if (null != validationResult) {
				for (Iterator rsltIterator = validationResult.iterator(); rsltIterator
						.hasNext();) {
					hippaSegmentResult = (HippaSegmentValidationResult) rsltIterator
							.next();
					hippaSgmtVldnRsltLst.add(hippaSegmentResult);
				}
			}
		}

		// Gets the hippasegment possible values for EB02, validates EB02 and
		// add the result to a list
		if ((null != this.getEb02())
				&& (this.getEb02().getName()
						.equalsIgnoreCase(DomainConstants.EB02_NAME))) {
			hippaSegment = getHippaSegmentsPossibleList(this.getEb02()
					.getName());
			this.getEb02().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());

			// invoking the validation for EB02
			validationResult = validateMappings(this.getEb02().getName());

			// iterate the list and add the hippaSegmentResult object to the
			// final list
			if (null != validationResult) {
				for (Iterator rsltIterator = validationResult.iterator(); rsltIterator
						.hasNext();) {
					hippaSegmentResult = (HippaSegmentValidationResult) rsltIterator
							.next();
					hippaSgmtVldnRsltLst.add(hippaSegmentResult);
				}
			}
		}
		if ((null != this.getEb03())
				&& (this.getEb03().getName()
						.equalsIgnoreCase(DomainConstants.EB03_NAME))
						//Added for SSCR 19537-- to check that EB03 validations donot appear for Custom messages
						&& (null == this.getRule() || null == this.getSpsId())				
		
		) {
			ExtendedMessageMapping extnMsg = null;
			if(this.getEb01() != null && !this.getEb01().getExtendedMsgsForSelectedValues().isEmpty())
			extnMsg = (ExtendedMessageMapping) (this.getEb01().getExtendedMsgsForSelectedValues().size() > 0 ? this.getEb01().getExtendedMsgsForSelectedValues().get(0):null);
			List hippaCodeSelectedValues = new ValidationUtility().removeBlankfromList(this.getHippaSegmentSelectedValues(this.getEb03()));
			if(extnMsg != null && extnMsg.getValue().equals("D") && hippaCodeSelectedValues == null && 
					(extnMsg.getExtndMsg1() !=null || extnMsg.getExtndMsg2()!=null || extnMsg.getExtndMsg3() !=null)){
				//Do nothing. In this case, no validation required for EB03.
			} else {
				hippaSegment = getHippaSegmentsPossibleList(this.getEb03()
						.getName());
				this.getEb03().setHippaCodePossibleValues(
						hippaSegment.getHippaCodePossibleValues());
				// invoking the validation for EB03
				validationResult = validateMappings(this.getEb03().getName());
				// iterate the list and add the hippaSegmentResult object to the
				// final list
				if (null != validationResult) {
					for (Iterator rsltIterator = validationResult.iterator(); rsltIterator
							.hasNext();) {
						hippaSegmentResult = (HippaSegmentValidationResult) rsltIterator
								.next();
						hippaSgmtVldnRsltLst.add(hippaSegmentResult);
					}
				}
			}
		}
		if ((null != this.getEb06())
				&& (this.getEb06().getName()
						.equalsIgnoreCase(DomainConstants.EB06_NAME))) {
			hippaSegment = getHippaSegmentsPossibleList(this.getEb06()
					.getName());
			this.getEb06().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());
			// invoking the validation for EB06
			validationResult = validateMappings(this.getEb06().getName());
			// iterate the list and add the hippaSegmentResult object to the
			// final list
			if (null != validationResult) {
				for (Iterator rsltIterator = validationResult.iterator(); rsltIterator
						.hasNext();) {
					hippaSegmentResult = (HippaSegmentValidationResult) rsltIterator
							.next();
					hippaSgmtVldnRsltLst.add(hippaSegmentResult);
				}
			}
		}
		if ((null != this.getEb09())
				&& (this.getEb09().getName()
						.equalsIgnoreCase(DomainConstants.EB09_NAME))) {
			hippaSegment = getHippaSegmentsPossibleList(this.getEb09()
					.getName());
			this.getEb09().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());
			// invoking the validation for EB09
			validationResult = validateMappings(this.getEb09().getName());
			// iterate the list and add the hippaSegmentResult object to the
			// final list
			if (validationResult != null) {
				for (Iterator rsltIterator = validationResult.iterator(); rsltIterator
						.hasNext();) {
					hippaSegmentResult = (HippaSegmentValidationResult) rsltIterator
							.next();
					hippaSgmtVldnRsltLst.add(hippaSegmentResult);
				}
			}
		}
		
		
	}//END for HSD02 Limitation variable checking
		
	/*	if (this.getVariable() != null
				&& (null == this.getRule() && null == this.getSpsId()))*/ 
		if (this.getVariable() != null
				|| (null != this.getRule() && null == this.getSpsId())){
			if (((null != this.getIi02())
					&& (null != this.getIi02().getName()) && (this.getIi02().getName()
							.equalsIgnoreCase(DomainConstants.III02_NAME))) || individualMappingExist) {
				
				if(individualMappingExist){
					this.setIi02(new HippaSegment());
					this.getIi02().setName(DomainConstants.III02_NAME);
				}
				
				hippaSegment = getHippaSegmentsPossibleList(this.getIi02()
						.getName());
				this.getIi02().setHippaCodePossibleValues(
						hippaSegment.getHippaCodePossibleValues());
				// invoking the validation for III02
				validationResult = validateMappings(this.getIi02().getName());
				// iterate the list and add the hippaSegmentResult object to the
				// final list
				if (validationResult != null) {
					for (Iterator rsltIterator = validationResult.iterator(); rsltIterator
							.hasNext();) {
						hippaSegmentResult = (HippaSegmentValidationResult) rsltIterator
								.next();
						hippaSgmtVldnRsltLst.add(hippaSegmentResult);
					}
				}
			}
		}
		// changed for Reference Data Values
		
		// Note Type Validation for Custom message is called from MappingCustomMessageserviceImpl
		if (((null != this.getNoteTypeCode())
				&& (null != this.getNoteTypeCode().getName()) && ((this
				.getNoteTypeCode().getName()
				.equalsIgnoreCase(DomainConstants.NOTE_TYPE_CODE))))
				|| (individualMappingExist)) {
			
				if(individualMappingExist){
					this.setNoteTypeCode(new HippaSegment());
					this.getNoteTypeCode().setName(DomainConstants.NOTE_TYPE_CODE);
				}
				
				hippaSegment = getHippaSegmentsPossibleList(this.getNoteTypeCode()
						.getName());
				this.getNoteTypeCode().setHippaCodePossibleValues(
						hippaSegment.getHippaCodePossibleValues());
				// invoking the validation for Note Type Code
				validationResult = validateMappings(this.getNoteTypeCode()
						.getName());
				// iterate the list and add the hippaSegmentResult object to the
				// final list
				if (validationResult != null) {
					for (Iterator rsltIterator = validationResult.iterator(); rsltIterator
							.hasNext();) {
						hippaSegmentResult = (HippaSegmentValidationResult) rsltIterator
								.next();
						hippaSgmtVldnRsltLst.add(hippaSegmentResult);
					}
				
			}
		}
		if ((null != this.getHsd01())
				&& ((this.getHsd01().getName()
						.equalsIgnoreCase(DomainConstants.HSD01_NAME)))
				|| (null != this.getHsd02())
				&& ((this.getHsd02().getName()
						.equalsIgnoreCase(DomainConstants.HSD02_NAME)))
				|| (null != this.getHsd03())
				&& ((this.getHsd03().getName()
						.equalsIgnoreCase(DomainConstants.HSD03_NAME)))
				|| (null != this.getHsd04())
				&& ((this.getHsd04().getName()
						.equalsIgnoreCase(DomainConstants.HSD04_NAME)))
				|| (null != this.getHsd05())
				&& ((this.getHsd05().getName()
						.equalsIgnoreCase(DomainConstants.HSD05_NAME)))
				|| (null != this.getHsd06())
				&& ((this.getHsd06().getName()
						.equalsIgnoreCase(DomainConstants.HSD06_NAME)))
				|| (null != this.getHsd07())
				&& ((this.getHsd07().getName()
						.equalsIgnoreCase(DomainConstants.HSD07_NAME)))
				|| (null != this.getHsd08())
				&& ((this.getHsd08().getName()
						.equalsIgnoreCase(DomainConstants.HSD08_NAME)))) {

			// Getting the possible hippacode list for HSD01
			hippaSegment = new HippaSegment();
			hippaSegment = getHippaSegmentsPossibleList(this.getHsd01()
					.getName());
			this.getHsd01().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());
			// Getting the possible hippacode list for HSD02
			hippaSegment = new HippaSegment();
			hippaSegment = getHippaSegmentsPossibleList(this.getHsd02()
					.getName());
			this.getHsd02().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());
			// Getting the possible hippacode list for HSD03
			hippaSegment = new HippaSegment();
			hippaSegment = getHippaSegmentsPossibleList(this.getHsd03()
					.getName());
			this.getHsd03().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());
			// Getting the possible hippacode list for HSD04
			hippaSegment = new HippaSegment();
			hippaSegment = getHippaSegmentsPossibleList(this.getHsd04()
					.getName());
			this.getHsd04().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());
			// Getting the possible hippacode list for HSD05
			hippaSegment = new HippaSegment();
			hippaSegment = getHippaSegmentsPossibleList(this.getHsd05()
					.getName());
			this.getHsd05().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());
			// Getting the possible hippacode list for HSD06
			hippaSegment = new HippaSegment();
			hippaSegment = getHippaSegmentsPossibleList(this.getHsd06()
					.getName());
			this.getHsd06().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());
			// Getting the possible hippacode list for HSD07
			hippaSegment = new HippaSegment();
			hippaSegment = getHippaSegmentsPossibleList(this.getHsd07()
					.getName());
			this.getHsd07().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());
			// Getting the possible hippacode list for HSD08
			hippaSegment = new HippaSegment();
			hippaSegment = getHippaSegmentsPossibleList(this.getHsd08()
					.getName());
			this.getHsd08().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());
			// invoking the validation for HSD CODES
			validationResult = validateMappings(HippaSegmentValidationFactory.HSD_CODES);
			if (validationResult != null) {
				for (Iterator rsltIterator = validationResult.iterator(); rsltIterator
						.hasNext();) {
					hippaSegmentResult = (HippaSegmentValidationResult) rsltIterator
							.next();
					hippaSgmtVldnRsltLst.add(hippaSegmentResult);
				}
			}
		}

		if ((null != this.getAccum())
				&& (((this.getAccum().getName()
						.equalsIgnoreCase(DomainConstants.ACCUM_NAME))) || (this
						.getAccum().getName()
						.equalsIgnoreCase(DomainConstants.ACCUM_REF_NAME)))) {
			hippaSegment = getHippaSegmentsPossibleList(this.getAccum()
					.getName());
			this.getAccum().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());
			validationResult = validateMappings(this.getAccum().getName());
			// iterate the list and add the hippaSegmentResult object to the
			// final list
			if (validationResult != null) {
				for (Iterator rsltIterator = validationResult.iterator(); rsltIterator
						.hasNext();) {
					hippaSegmentResult = (HippaSegmentValidationResult) rsltIterator
							.next();
					hippaSgmtVldnRsltLst.add(hippaSegmentResult);
				}
			}
		}

		// Validating the selected UM rules.
		if ((null != this.getUtilizationMgmntRule() && null != this
				.getUtilizationMgmntRule().getName())
				&& (this.getUtilizationMgmntRule().getName()
						.equalsIgnoreCase(DomainConstants.UMRULE_NAME))) {

			hippaSegment = this.getHippaSegmentService().getAvailableUMRule(
					this.getUtilizationMgmntRule().getName(),
					DomainConstants.CONDN_TO_FETCH_HIPPACODE_PSBLE_VALUES);
			this.getUtilizationMgmntRule().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());
			validationResult = validateMappings(this.getUtilizationMgmntRule()
					.getName());

			// iterate the list and add the hippaSegmentResult object to the
			// final list
			if (validationResult != null) {
				for (Iterator rsltIterator = validationResult.iterator(); rsltIterator
						.hasNext();) {
					hippaSegmentResult = (HippaSegmentValidationResult) rsltIterator
							.next();
					hippaSgmtVldnRsltLst.add(hippaSegmentResult);
				}
			}
		}
		
		// Validating NM1 Message Segment
		if ((null != this.getNm1MessageSegment())
				&& (this.getNm1MessageSegment().getName()
						.equalsIgnoreCase(DomainConstants.NM1_MSG_SGMNT))) {
			hippaSegment = getHippaSegmentsPossibleList(this.getNm1MessageSegment()
					.getName());
			this.getNm1MessageSegment().setHippaCodePossibleValues(
					hippaSegment.getHippaCodePossibleValues());
			// invoking the validation for NM1 Message Segment
			validationResult = validateMappings(this.getNm1MessageSegment().getName());
			// iterate the list and add the hippaSegmentResult object to the
			// final list
			if (validationResult != null) {
				for (Iterator rsltIterator = validationResult.iterator(); rsltIterator
						.hasNext();) {
					hippaSegmentResult = (HippaSegmentValidationResult) rsltIterator
							.next();
					hippaSgmtVldnRsltLst.add(hippaSegmentResult);
				}
			}
		}
		hippaSegmentResult = validateMappingInfo();
		if (null != hippaSegmentResult) {
			hippaSgmtVldnRsltLst.add(hippaSegmentResult);
		}
		return hippaSgmtVldnRsltLst;
	}
	
	private boolean isHSD02LimitationVariablePresent(){
		if(isEBSegmentPresent()){
		hippaSegment = new HippaSegment();
		if(this.getHsd02() != null) {
		hippaSegment = getHippaSegmentsPossibleList(this.getHsd02()
				.getName());
		this.getHsd02().setHippaCodePossibleValues(
				hippaSegment.getHippaCodePossibleValues());
		
		List selectedValues = new ArrayList();
		selectedValues = this.getHsd02().getHippaCodeSelectedValues();
		HippaCodeValue hippaCodeValue = (HippaCodeValue) selectedValues.get(0);
		
		if(selectedValues.size() >1){
		//if the first value i.e the numeric value is present the limitation variables will not be coded.
			if(!hippaCodeValue.getValue().trim().isEmpty()){
				return false;
			}else{
				for(int i=1;i<7;i++){
					hippaCodeValue = (HippaCodeValue) selectedValues.get(i);
					if(!hippaCodeValue.getValue().trim().isEmpty()){
						if(hippaSegmentService.isEBSegmentPresent(hippaCodeValue.getValue().trim()))
						return true;
					}
				}
			}
		}else{
			if(!hippaCodeValue.getDescription().trim().isEmpty()){
				return true;
			}
		}
		return false;
		}
		return false;
	}
		return true;
	}
	public List validateMappings(String hippaSegmentName)
			throws DomainException {
		mappingValidationFactory = new HippaSegmentValidationFactory();
		HippaSegmentValidator hippaSegmentValidator = mappingValidationFactory
				.getValidator(hippaSegmentName);
		return hippaSegmentValidator.validate(this);
	}
	
	private boolean isEBSegmentPresent(){
		if((null != this.getEb01())&& (this.getEb01().getName().equalsIgnoreCase(DomainConstants.EB01_NAME))){
			if(this.getEB01Value() != null && !this.getEB01Value().trim().equals("")){
				return true;
			}
			
		}if((null != this.getEb02())
						&& (this.getEb02().getName()
								.equalsIgnoreCase(DomainConstants.EB02_NAME))){
			if(this.getEB02Value() != null && !this.getEB02Value().trim().equals("")){
				return true;
			}
		}if((null != this.getEb03())
						&& (this.getEb03().getName()
								.equalsIgnoreCase(DomainConstants.EB03_NAME))){
			List selectedValues = this.getEb03Values();
			if(null != selectedValues){
				for(int i=0;i<selectedValues.size();i++){
					if(!selectedValues.get(i).toString().trim().equals("")){
						return true;
					}
				}
			}
		}if((null != this.getEb06())
						&& (this.getEb06().getName()
								.equalsIgnoreCase(DomainConstants.EB06_NAME))){
			if(this.getEB06Value() != null && !this.getEB06Value().trim().equals("")){
				return true;
			}
			
		}if((null != this.getEb09())
						&& (this.getEb09().getName()
								.equalsIgnoreCase(DomainConstants.EB09_NAME))){
			if(this.getEB09Value() != null && !this.getEB09Value().trim().equals("")){
				return true;
			}
		}else{
			return false;
		}
		return false;
	}

	/**
	 * Method to validate the attributes assosiated with the mapping.
	 * 
	 * @return HippaSegmentValidationResult if validation error exist else
	 *         return a null.
	 */
	public HippaSegmentValidationResult validateMappingInfo() {
		boolean errorFlag = false;
		HippaSegmentValidationResult hippaSegmentResult = new HippaSegmentValidationResult();
		hippaSegmentResult.setMapping(this);
		hippaSegmentResult.setStatus((short) 2);
		if (null != this.getVariable()) {
			if (ContractMappingValidator.checkForRuleE029(this.getVariable()
					.getDescription(), this.getVariable().getMinorHeadings(),
					this.getVariable().getMajorHeadings())) {
				errorFlag = true;
				hippaSegmentResult
						.addWarningCode(ValidatorConstants.E029_ERROR_WARNING_WPD);
			}
		} else {
			if (ContractMappingValidator.checkForRuleE029(this.getMessage())) {
				errorFlag = true;
				hippaSegmentResult
						.addWarningCode(ValidatorConstants.E029_ERROR_WARNING_eWPD);
			}
		}
		 

		// PENALTY VALIDATION for 10.1  
		if (null != this.getVariable()) {
			if (ContractMappingValidator.checkForRuleW029(this.getVariable()
					.getDescription(), this.getMessage())) {
				errorFlag = true;
				hippaSegmentResult
						.addWarningCode(ValidatorConstants.W029_ERROR_WARNING_WPD);
			}
		}
        // PENALTY VALIDATION for 10.3
		if (null != this.getSpsId()) {
		if (ContractMappingValidator.checkForRuleW022(this.getSpsId()
				.getSpsDesc(), this.getMessage())) {
			errorFlag = true;
			hippaSegmentResult
					.addWarningCode(ValidatorConstants.W022_ERROR_WARNING_eWPD);
		}
		}
		 
		// PENALTY VALIDATION for 10.2
		if (null != this.getVariable()) {
			if (ContractMappingValidator.checkForRuleWB029(this.getVariable()
					.getDescription(), this.getMessage())) {
				errorFlag = true;
				hippaSegmentResult
						.addWarningCode(ValidatorConstants.WB029_ERROR_WARNING_WPD);
			}
		}
		// PENALTY VALIDATION for 10.3[Message Blank]
		if (null != this.getSpsId()) {
		if (ContractMappingValidator.checkForRuleWB022(this.getSpsId()
				.getSpsDesc(), this.getMessage())) {
			errorFlag = true;
			hippaSegmentResult
					.addWarningCode(ValidatorConstants.WB022_ERROR_WARNING_eWPD);
		}
		}
		// PENALTY validation ends

		/*if (null != this.getVariable()) {
			if (ContractMappingValidator.checkForRuleE029(this.getVariable()
					.getDescription(), this.getVariable().getMinorHeadings(),
					this.getVariable().getMajorHeadings())) {
				errorFlag = true;
				hippaSegmentResult
						.addWarningCode(ValidatorConstants.E100_ERROR_WARNING_eWPD);
			}
		} else {
			if (ContractMappingValidator.checkForRuleE029(this.getMessage())) {
				errorFlag = true;
				hippaSegmentResult
						.addWarningCode(ValidatorConstants.E100_ERROR_WARNING_eWPD);
			}
		}*/
		if (!errorFlag) {
			return null;
		}
		return hippaSegmentResult;
	}

	public void addValuesToHippaList(Mapping mapping) {
		new ArrayList();
	}

	/**
	 * @return Returns the mappingValidationFactory.
	 */
	public HippaSegmentValidationFactory getMappingValidationFactory() {
		return mappingValidationFactory;
	}

	/**
	 * @return Returns the auditTrail.
	 */
	public List getAuditTrails() {
		return auditTrails;
	}

	/**
	 * @param auditTrail
	 *            The auditTrail to set.
	 */
	public void setAuditTrails(List auditTrails) {
		this.auditTrails = auditTrails;
	}

	/**
	 * @return Returns the eb01.
	 */
	public HippaSegment getEb01() {
		return eb01;
	}

	/**
	 * @param eb01
	 *            The eb01 to set.
	 */
	public void setEb01(HippaSegment eb01) {
		this.eb01 = eb01;
	}

	/**
	 * @return Returns the eb02.
	 */
	public HippaSegment getEb02() {
		return eb02;
	}

	/**
	 * @param eb02
	 *            The eb02 to set.
	 */
	public void setEb02(HippaSegment eb02) {
		this.eb02 = eb02;
	}

	/**
	 * @return Returns the variable.
	 */
	public Variable getVariable() {
		return variable;
	}

	/**
	 * @param variable
	 *            The variable to set.
	 */
	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	/**
	 * @param mappingValidationFactory
	 *            The mappingValidationFactory to set.
	 */
	public void setMappingValidationFactory(
			HippaSegmentValidationFactory mappingValidationFactory) {
		this.mappingValidationFactory = mappingValidationFactory;
	}

	/**
	 * @return Returns the accum.
	 */
	public HippaSegment getAccum() {
		return accum;
	}

	/**
	 * @param accum
	 *            The accum to set.
	 */
	public void setAccum(HippaSegment accum) {
		this.accum = accum;
	}

	/**
	 * @return Returns the accumHippaSegment.
	 */
	public List getAccumHippaSegment() {
		return accumHippaSegment;
	}

	/**
	 * @param accumHippaSegment
	 *            The accumHippaSegment to set.
	 */
	public void setAccumHippaSegment(List accumHippaSegment) {
		this.accumHippaSegment = accumHippaSegment;
	}

	/**
	 * @return Returns the eb03.
	 */
	public HippaSegment getEb03() {
		return eb03;
	}

	/**
	 * @param eb03
	 *            The eb03 to set.
	 */
	public void setEb03(HippaSegment eb03) {
		this.eb03 = eb03;
	}

	/**
	 * @return Returns the eb03HippaSegment.
	 */
	public List getEb03HippaSegment() {
		return eb03HippaSegment;
	}

	/**
	 * @param eb03HippaSegment
	 *            The eb03HippaSegment to set.
	 */
	public void setEb03HippaSegment(List eb03HippaSegment) {
		this.eb03HippaSegment = eb03HippaSegment;
	}

	/**
	 * @return Returns the eb06.
	 */
	public HippaSegment getEb06() {
		return eb06;
	}

	/**
	 * @param eb06
	 *            The eb06 to set.
	 */
	public void setEb06(HippaSegment eb06) {
		this.eb06 = eb06;
	}

	/**
	 * @return Returns the eb09.
	 */
	public HippaSegment getEb09() {
		return eb09;
	}

	/**
	 * @param eb09
	 *            The eb09 to set.
	 */
	public void setEb09(HippaSegment eb09) {
		this.eb09 = eb09;
	}

	/**
	 * @return Returns the hsd01.
	 */
	public HippaSegment getHsd01() {
		return hsd01;
	}

	/**
	 * @param hsd01
	 *            The hsd01 to set.
	 */
	public void setHsd01(HippaSegment hsd01) {
		this.hsd01 = hsd01;
	}

	/**
	 * @return Returns the hsd02.
	 */
	public HippaSegment getHsd02() {
		return hsd02;
	}

	/**
	 * @param hsd02
	 *            The hsd02 to set.
	 */
	public void setHsd02(HippaSegment hsd02) {
		this.hsd02 = hsd02;
	}

	/**
	 * @return Returns the hsd03.
	 */
	public HippaSegment getHsd03() {
		return hsd03;
	}

	/**
	 * @param hsd03
	 *            The hsd03 to set.
	 */
	public void setHsd03(HippaSegment hsd03) {
		this.hsd03 = hsd03;
	}

	/**
	 * @return Returns the hsd04.
	 */
	public HippaSegment getHsd04() {
		return hsd04;
	}

	/**
	 * @param hsd04
	 *            The hsd04 to set.
	 */
	public void setHsd04(HippaSegment hsd04) {
		this.hsd04 = hsd04;
	}

	/**
	 * @return Returns the hsd05.
	 */
	public HippaSegment getHsd05() {
		return hsd05;
	}

	/**
	 * @param hsd05
	 *            The hsd05 to set.
	 */
	public void setHsd05(HippaSegment hsd05) {
		this.hsd05 = hsd05;
	}

	/**
	 * @return Returns the hsd06.
	 */
	public HippaSegment getHsd06() {
		return hsd06;
	}

	/**
	 * @param hsd06
	 *            The hsd06 to set.
	 */
	public void setHsd06(HippaSegment hsd06) {
		this.hsd06 = hsd06;
	}

	/**
	 * @return Returns the hsd07.
	 */
	public HippaSegment getHsd07() {
		return hsd07;
	}

	/**
	 * @param hsd07
	 *            The hsd07 to set.
	 */
	public void setHsd07(HippaSegment hsd07) {
		this.hsd07 = hsd07;
	}

	/**
	 * @return Returns the hsd08.
	 */
	public HippaSegment getHsd08() {
		return hsd08;
	}

	/**
	 * @param hsd08
	 *            The hsd08 to set.
	 */
	public void setHsd08(HippaSegment hsd08) {
		this.hsd08 = hsd08;
	}

	/**
	 * @return Returns the ii02.
	 */
	public HippaSegment getIi02() {
		return ii02;
	}

	/**
	 * @param ii02
	 *            The ii02 to set.
	 */
	public void setIi02(HippaSegment ii02) {
		this.ii02 = ii02;
	}

	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return Returns the msg_type_required.
	 */
	public String getMsg_type_required() {
		return msg_type_required;
	}

	/**
	 * @param msg_type_required
	 *            The msg_type_required to set.
	 */
	public void setMsg_type_required(String msg_type_required) {
		this.msg_type_required = msg_type_required;
	}

	/**
	 * @return Returns the noteTypeCode.
	 */
	public HippaSegment getNoteTypeCode() {
		return noteTypeCode;
	}

	/**
	 * @param noteTypeCode
	 *            The noteTypeCode to set.
	 */
	public void setNoteTypeCode(HippaSegment noteTypeCode) {
		this.noteTypeCode = noteTypeCode;
	}

	/**
	 * @return Returns the sensitiveBenefitIndicator.
	 */
	public String getSensitiveBenefitIndicator() {
		return sensitiveBenefitIndicator;
	}

	/**
	 * @param sensitiveBenefitIndicator
	 *            The sensitiveBenefitIndicator to set.
	 */
	public void setSensitiveBenefitIndicator(String sensitiveBenefitIndicator) {
		this.sensitiveBenefitIndicator = sensitiveBenefitIndicator;
	}

	/**
	 * @return Returns the variableMappingStatus.
	 */
	public String getVariableMappingStatus() {
		return variableMappingStatus;
	}

	/**
	 * @param variableMappingStatus
	 *            The variableMappingStatus to set.
	 */
	public void setVariableMappingStatus(String variableMappingStatus) {
		this.variableMappingStatus = variableMappingStatus;
	}

	/**
	 * @return Returns the createdTmStamp.
	 */
	public Date getCreatedTmStamp() {
		return createdTmStamp;
	}

	/**
	 * @param createdTmStamp
	 *            The createdTmStamp to set.
	 */
	public void setCreatedTmStamp(Date createdTmStamp) {
		this.createdTmStamp = createdTmStamp;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String date=sdf.format(this.createdTmStamp );
	    this.setCreatedDate(date);
	}

	/**
	 * @return Returns the lastChangedTmStamp.
	 */
	public Date getLastChangedTmStamp() {
		return lastChangedTmStamp;
	}

	/**
	 * @param lastChangedTmStamp
	 *            The lastChangedTmStamp to set.
	 */
	public void setLastChangedTmStamp(Date lastChangedTmStamp) {
		this.lastChangedTmStamp = lastChangedTmStamp;
	}

	/**
	 * @return Returns the inTempTable.
	 */
	public String getInTempTable() {
		return inTempTable;
	}

	/**
	 * @param inTempTable
	 *            The inTempTable to set.
	 */
	public void setInTempTable(String inTempTable) {
		this.inTempTable = inTempTable;
	}

	/**
	 * @return Returns the variableSystemId.
	 */
	public Long getVariableSystemId() {
		return variableSystemId;
	}

	/**
	 * @param variableSystemId
	 *            The variableSystemId to set.
	 */
	public void setVariableSystemId(Long variableSystemId) {
		this.variableSystemId = variableSystemId;
	}

	/**
	 * @return Returns the isMapgRequired.
	 */
	public String getIsMapgRequired() {
		return isMapgRequired;
	}

	/**
	 * @param isMapgRequired
	 *            The isMapgRequired to set.
	 */
	public void setIsMapgRequired(String isMapgRequired) {
		this.isMapgRequired = isMapgRequired;
	}

	/**
	 * @return Returns the variableList.
	 */
	public List getVariableList() {
		return variableList;
	}

	/**
	 * @param variableList
	 *            The variableList to set.
	 */
	public void setVariableList(List variableList) {
		this.variableList = variableList;
	}

	/**
	 * @return Returns the user.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            The user to set.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return Returns the updateMstr.
	 */
	public boolean isUpdateMstr() {
		return updateMstr;
	}

	/**
	 * @param updateMstr
	 *            The updateMstr to set.
	 */
	public void setUpdateMstr(boolean updateMstr) {
		this.updateMstr = updateMstr;
	}

	/**
	 * @return Returns the hippaSegmentService.
	 */
	public HippaSegmentService getHippaSegmentService() {
		return hippaSegmentService;
	}

	/**
	 * @param hippaSegmentService
	 *            The hippaSegmentService to set.
	 */
	public void setHippaSegmentService(HippaSegmentService hippaSegmentService) {
		this.hippaSegmentService = hippaSegmentService;
	}

	public String getEB01Value() {
		return getHippaSegmentDescreteSelectedValue(this.getEb01());
	}

	public String getEB02Value() {
		return getHippaSegmentDescreteSelectedValue(this.getEb02());
	}

	public String getEB06Value() {
		return getHippaSegmentDescreteSelectedValue(this.getEb06());
	}

	public String getEB09Value() {
		return getHippaSegmentDescreteSelectedValue(this.getEb09());
	}
	public String getStartAgeValue() {
		return getHippaSegmentDescreteSelectedValue(this.getStartAge());
	}
	public String getEndAgeValue() {
		return getHippaSegmentDescreteSelectedValue(this.getEndAge());
	}

	public String getIi02Value() {
		return getHippaSegmentDescreteSelectedValue(this.getIi02());
	}

	public String getHsd01Value() {
		return getHippaSegmentDescreteSelectedValue(this.getHsd01());
	}

	public List getHsd02Value() {
		return getHippaSegmentSelectedValues(this.getHsd02());
	}

	public String getHsd03Value() {
		return getHippaSegmentDescreteSelectedValue(this.getHsd03());
	}

	public String getHsd04Value() {
		return getHippaSegmentDescreteSelectedValue(this.getHsd04());
	}

	public String getHsd05Value() {
		return getHippaSegmentDescreteSelectedValue(this.getHsd05());
	}

	public String getHsd06Value() {
		return getHippaSegmentDescreteSelectedValue(this.getHsd06());
	}

	public String getHsd07Value() {
		return getHippaSegmentDescreteSelectedValue(this.getHsd07());
	}

	public String getHsd08Value() {
		return getHippaSegmentDescreteSelectedValue(this.getHsd08());
	}

	public String getNoteTypeCodeValue() {
		return getHippaSegmentDescreteSelectedValue(this.getNoteTypeCode());
	}

	public List getEb03Values() {
		return getHippaSegmentSelectedValues(this.getEb03());
	}

	public List getAccumValues() {
		return getHippaSegmentSelectedValues(this.getAccum());
	}

	public List getUmRuleValues() {
		return getHippaSegmentSelectedValues(this.getUtilizationMgmntRule());
	}
	
	public List getHSD02LimitationValues(){
		return getHippaSegmentSelectedValues(this.getHsd02());
	}

	public String getHippaSegmentDescreteSelectedValue(
			HippaSegment selectedHippaSegment) {
		if (selectedHippaSegment == null) {
			return null;
		}
		List selectedHippaSegmentList = selectedHippaSegment
				.getHippaCodeSelectedValues();
		if (selectedHippaSegmentList == null
				|| selectedHippaSegmentList.size() == 0) {
			return null;
		}
		HippaCodeValue hippaCodeValue = (HippaCodeValue) selectedHippaSegmentList
				.get(0);
		if (hippaCodeValue == null) {
			return null;
		}
		return hippaCodeValue.getValue();
	}

	public List getHippaSegmentSelectedValues(HippaSegment selectedHippaSegment) {
		if (selectedHippaSegment == null) {
			return null;
		}
		List selectedHippaSegmentList = selectedHippaSegment
				.getHippaCodeSelectedValues();
		List selectedValueList = new ArrayList();
		if (selectedHippaSegmentList == null
				|| selectedHippaSegmentList.size() == 0) {
			return selectedValueList;
		}
		for (Iterator iterator = selectedHippaSegmentList.iterator(); iterator
				.hasNext();) {
			HippaCodeValue hippaCodeValue = (HippaCodeValue) iterator.next();
			selectedValueList.add(hippaCodeValue.getValue());
		}
		return selectedValueList;
	}

	public void addAuditTrail(AuditTrail auditTrail) {

		auditTrails.add(auditTrail);
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

	public SPSId getSpsId() {
		return spsId;
	}

	public void setSpsId(SPSId spsId) {
		this.spsId = spsId;
	}

	/*
	 * public void setFinalized(boolean finalized) { Finalized = finalized; }
	 */

	public boolean isFinalizedFlagModified() {
		return finalizedFlagModified;
	}

	public void setFinalizedFlagModified(boolean finalizedFlagModified) {
		this.finalizedFlagModified = finalizedFlagModified;
	}

	/**
	 * @return the contractMapping
	 */
	public ContractMappingVO getContractMapping() {
		return contractMapping;
	}

	/**
	 * @param contractMapping
	 *            the contractMapping to set
	 */
	public void setContractMapping(ContractMappingVO contractMapping) {
		this.contractMapping = contractMapping;
	}

	public boolean isFinalized() {
		return finalized;
	}

	public void setFinalized(boolean finalized) {
		this.finalized = finalized;
	}

	public String getMappingComplete() {
		return mappingComplete;
	}

	public void setMappingComplete(String mappingComplete) {
		this.mappingComplete = mappingComplete;
	}

	public int compareTo(Object o) {
		if ("RULE".equals(sortField)) {
			Pattern p = Pattern.compile("^[a-zA-Z0-9]");
			// Create a matcher with an input string
			String var = ((Mapping) o).rule.getHeaderRuleId();
			Matcher m = p.matcher(var);
			if (m.find()) {
				return this.rule.getHeaderRuleId().compareTo(
						((Mapping) o).rule.getHeaderRuleId());
			} else {
				m = p.matcher(this.rule.getHeaderRuleId());
				if (m.find()) {
					return 1;
				} else {
					return this.rule.getHeaderRuleId().compareTo(
							((Mapping) o).rule.getHeaderRuleId());
				}
			}
		} else if ("Variable".equals(sortField)) {
			Pattern p = Pattern.compile("^[a-zA-Z0-9]");
			// Create a matcher with an input string
			String var = ((Mapping) o).variable.getVariableId();
			Matcher m = p.matcher(var);
			if (m.find()) {
				return this.variable.getVariableId().compareTo(
						((Mapping) o).variable.getVariableId());
			} else {
				m = p.matcher(this.variable.getVariableId());
				if (m.find()) {
					return 1;
				} else {
					return this.variable.getVariableId().compareTo(
							((Mapping) o).variable.getVariableId());
				}
			}
		} else {
			if (this.lastChangedTmStamp == ((Mapping) o).lastChangedTmStamp)
				return 0;
			else if (this.lastChangedTmStamp
					.before((((Mapping) o).lastChangedTmStamp)))
				return 1;
			else
				return -1;
		}
	}

	public HippaSegment getUtilizationMgmntRule() {
		return utilizationMgmntRule;
	}

	public void setUtilizationMgmntRule(HippaSegment utilizationMgmntRule) {
		this.utilizationMgmntRule = utilizationMgmntRule;
	}

	public boolean isMapped() {
		return mapped;
	}

	public void setMapped(boolean mapped) {
		this.mapped = mapped;
	}

	public LocateService getLocateRuleIdService() {
		return locateRuleIdService;
	}

	public void setLocateRuleIdService(LocateService locateRuleIdService) {
		this.locateRuleIdService = locateRuleIdService;
	}

	public ValidationService getEwpdValidationService() {
		return ewpdValidationService;
	}

	public void setEwpdValidationService(ValidationService ewpdValidationService) {
		this.ewpdValidationService = ewpdValidationService;
	}

	public ValidationService getWpdValidationService() {
		return wpdValidationService;
	}

	public void setWpdValidationService(ValidationService wpdValidationService) {
		this.wpdValidationService = wpdValidationService;
	}

	public String getDatafeedStatus() {
		return datafeedStatus;
	}

	public void setDatafeedStatus(String datafeedStatus) {
		this.datafeedStatus = datafeedStatus;
	}

	public String getDataFeedInd() {
		return dataFeedInd;
	}

	public void setDataFeedInd(String dataFeedInd) {
		this.dataFeedInd = dataFeedInd;
	}

	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	/**
	 * @return Returns the pageFrom.
	 */
	public String getPageFrom() {
		return pageFrom;
	}

	/**
	 * @param pageFrom
	 *            The pageFrom to set.
	 */
	public void setPageFrom(String pageFrom) {
		this.pageFrom = pageFrom;
	}

	/**
	 * @return Returns the formattedStringDate.
	 */
	public String getFormattedStringDate() {
		return formattedStringDate;
	}

	/**
	 * @param formattedStringDate
	 *            The formattedStringDate to set.
	 */
	public void setFormattedStringDate(String formattedStringDate) {
		this.formattedStringDate = formattedStringDate;
	}

	public String getBusinessEntity() {
		return businessEntity;
	}

	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}

	public String getBusinessGroup() {
		return businessGroup;
	}

	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}

	public String getMbu() {
		return mbu;
	}

	public void setMbu(String mbu) {
		this.mbu = mbu;
	}

	public void setAuditLock(String auditLock) {
		this.auditLock = auditLock;
	}

	public String getAuditLock() {
		return auditLock;
	}

	public HippaSegment getStartAge() {
		return startAge;
	}

	public void setStartAge(HippaSegment startAge) {
		this.startAge = startAge;
	}

	public HippaSegment getEndAge() {
		return endAge;
	}

	public void setEndAge(HippaSegment endAge) {
		this.endAge = endAge;
	}

	/**
	 * @return the startAgeCodedValue
	 */
	public String getStartAgeCodedValue() {
		return startAgeCodedValue;
	}

	/**
	 * @param startAgeCodedValue the startAgeCodedValue to set
	 */
	public void setStartAgeCodedValue(String startAgeCodedValue) {
		this.startAgeCodedValue = startAgeCodedValue;
	}

	/**
	 * @return the endAgeCodedValue
	 */
	public String getEndAgeCodedValue() {
		return endAgeCodedValue;
	}

	/**
	 * @param endAgeCodedValue the endAgeCodedValue to set
	 */
	public void setEndAgeCodedValue(String endAgeCodedValue) {
		this.endAgeCodedValue = endAgeCodedValue;
	}

	/**
	 * @return the procedureExcludedInd
	 */
	public String getProcedureExcludedInd() {
		return procedureExcludedInd;
	}

	/**
	 * @param procedureExcludedInd the procedureExcludedInd to set
	 */
	public void setProcedureExcludedInd(String procedureExcludedInd) {
		this.procedureExcludedInd = procedureExcludedInd;
	}

	public List getWpdAccumList() {
		return wpdAccumList;
	}

	public void setWpdAccumList(List wpdAccumList) {
		this.wpdAccumList = wpdAccumList;
	}

	/**
	 * @param indvdlEb03AssnIndicator the indvdlEb03AssnIndicator to set
	 */
	public void setIndvdlEb03AssnIndicator(String indvdlEb03AssnIndicator) {
		this.indvdlEb03AssnIndicator = indvdlEb03AssnIndicator;
	}

	public Map<String, String> getEb03III02Map() {
		return eb03III02Map;
	}

	public void setEb03III02Map(Map<String, String> eb03III02Map) {
		this.eb03III02Map = eb03III02Map;
	}

	public String getIndvdlEb03AssnIndicator() {
		return indvdlEb03AssnIndicator;
	}
	
	String messageForDisplay;

	public String getMessageForDisplay() {
		return messageForDisplay;
	}

	public void setMessageForDisplay(String messageForDisplay) {
		this.messageForDisplay = messageForDisplay;
	}
	
	private String createdDate;

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	
	public HippaSegment getNm1MessageSegment() {
		return nm1MessageSegment;
	}

	public void setNm1MessageSegment(HippaSegment nm1MessageSegment) {
		this.nm1MessageSegment = nm1MessageSegment;
	}
	
	public String getNM1MessageSegmentValue() {
		return getHippaSegmentDescreteSelectedValue(this.getNm1MessageSegment());
	}

	public String getEb01_extndMsgTxt1() {
		return eb01_extndMsgTxt1;
	}

	public void setEb01_extndMsgTxt1(String eb01_extndMsgTxt1) {
		this.eb01_extndMsgTxt1 = eb01_extndMsgTxt1;
	}

	public String getEb01_extndMsgTxt2() {
		return eb01_extndMsgTxt2;
	}

	public void setEb01_extndMsgTxt2(String eb01_extndMsgTxt2) {
		this.eb01_extndMsgTxt2 = eb01_extndMsgTxt2;
	}

	public String getEb01_extndMsgTxt3() {
		return eb01_extndMsgTxt3;
	}

	public void setEb01_extndMsgTxt3(String eb01_extndMsgTxt3) {
		this.eb01_extndMsgTxt3 = eb01_extndMsgTxt3;
	}

	public String getEb01_eb12ChangeInd() {
		return eb01_eb12ChangeInd;
	}

	public void setEb01_eb12ChangeInd(String eb01_eb12ChangeInd) {
		this.eb01_eb12ChangeInd = eb01_eb12ChangeInd;
	}

	public String getExtndMsgTxt1() {
		return extndMsgTxt1;
	}

	public void setExtndMsgTxt1(String extndMsgTxt1) {
		this.extndMsgTxt1 = extndMsgTxt1;
	}

	public String getExtndMsgTxt2() {
		return extndMsgTxt2;
	}

	public void setExtndMsgTxt2(String extndMsgTxt2) {
		this.extndMsgTxt2 = extndMsgTxt2;
	}

	public String getExtndMsgTxt3() {
		return extndMsgTxt3;
	}

	public void setExtndMsgTxt3(String extndMsgTxt3) {
		this.extndMsgTxt3 = extndMsgTxt3;
	}

	public String getEb12ChangeInd() {
		return eb12ChangeInd;
	}

	public void setEb12ChangeInd(String eb12ChangeInd) {
		this.eb12ChangeInd = eb12ChangeInd;
	}
	private List hpnMapgList;

	public List getHpnMapgList() {
		return hpnMapgList;
	}

	public void setHpnMapgList(List hpnMapgList) {
		this.hpnMapgList = hpnMapgList;
	}
	
}

