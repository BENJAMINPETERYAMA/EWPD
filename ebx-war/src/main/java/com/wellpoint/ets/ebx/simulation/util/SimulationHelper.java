/******************************************************************************
 * Program Name                : SimulationHelper
 * Description                 : This class is used to generate the XMLRequest, XMLResponse, X12 Request and Response
 * 
 * Project Name                :
 * Creation Date               : 24 Dec 2010
 * Author                      : UST Global.
 *
 * Copyright Notice
 * This file contains proprietary information of US Technology.
 * Copying or reproduction without prior written approval is prohibited.
 *
 ********************************Change History*******************************
 * Sl No.   Modified Date           Modified By       Change Description
 *
 *-------------------------------------------------------------------------------
 * 1.0     24 Dec 2010       		UST Global         Initial Draft
 *
 *------------------------------------------------------ -------------------------
 ********************************************************************************/
package com.wellpoint.ets.ebx.simulation.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.owasp.esapi.ESAPI;

import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.ebx.schemas.v5.EBSHeaderDocument;
import com.wellpoint.ets.ebx.schemas.v5.EBSResponseDocument;
import com.wellpoint.ets.ebx.schemas.v5.PayLoadDocument;
import com.wellpoint.ets.ebx.schemas.v5.EBSExceptionsDocument.EBSExceptions;
import com.wellpoint.ets.ebx.schemas.v5.EBSExceptionsDocument.EBSExceptions.EBSException;
import com.wellpoint.ets.ebx.schemas.v5.EBSHeaderDocument.EBSHeader;
import com.wellpoint.ets.ebx.schemas.v5.EBSRequestDocument.EBSRequest;
import com.wellpoint.ets.ebx.schemas.v5.EBSRequestDocument.EBSRequest.Get27XBenefitAccumsRequest;
import com.wellpoint.ets.ebx.schemas.v5.EBSRequestDocument.EBSRequest.Get27XBenefitAccumsRequest.StaticData.BenefitClass;
import com.wellpoint.ets.ebx.schemas.v5.EBSResponseDocument.EBSResponse;
import com.wellpoint.ets.ebx.schemas.v5.EBSResponseDocument.EBSResponse.Get27XBenefitAccumsResponse;
import com.wellpoint.ets.ebx.schemas.v5.EBSResponseDocument.EBSResponse.Get27XBenefitAccumsResponse.DynamicData;
import com.wellpoint.ets.ebx.schemas.v5.EBSResponseDocument.EBSResponse.Get27XBenefitAccumsResponse.StaticData;
import com.wellpoint.ets.ebx.schemas.v5.EBSResponseDocument.EBSResponse.Get27XBenefitAccumsResponse.DynamicData.Benefit;
import com.wellpoint.ets.ebx.schemas.v5.EBSResponseDocument.EBSResponse.Get27XBenefitAccumsResponse.DynamicData.Major;
import com.wellpoint.ets.ebx.schemas.v5.EBSResponseDocument.EBSResponse.Get27XBenefitAccumsResponse.DynamicData.Minor;
import com.wellpoint.ets.ebx.schemas.v5.EBSResponseDocument.EBSResponse.Get27XBenefitAccumsResponse.DynamicData.Benefit.BenefitLimitInfo;
import com.wellpoint.ets.ebx.schemas.v5.EBSResponseDocument.EBSResponse.Get27XBenefitAccumsResponse.DynamicData.Benefit.StandardizeBenefit;
import com.wellpoint.ets.ebx.schemas.v5.EBSResponseDocument.EBSResponse.Get27XBenefitAccumsResponse.DynamicData.Benefit.StandardizeBenefit.BenefitRelatedEntity;
import com.wellpoint.ets.ebx.schemas.v5.PayLoadDocument.PayLoad;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.FunctionGroup;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Interchange;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Loop2000;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Loop2100;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Loop2110;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.controlsegmentvo.Transaction;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo.AAA;
import com.wellpoint.ets.ebx.simulation.vo.ContractMappingVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.vo.HIPAA270BXVO;
import com.wellpoint.ets.ebx.simulation.vo.MajorHeadingsVO;
import com.wellpoint.ets.ebx.simulation.vo.MemberInfoVO;
import com.wellpoint.ets.ebx.simulation.vo.MinorHeadingsVO;

public class SimulationHelper {

	public static final Logger log = Logger.getLogger(SimulationHelper.class);

	private static final String NETWORK_TYPE_CODE_W = "W";
	private static final String NETWORK_TYPE_CODE_Y = "Y";
	private static final String NETWORK_TYPE_CODE_N = "N";
	private static final String EB11_STRING = ".EB11.";
	private static final String WGS = "WGS";
	/**
	 * Generic EB string list.
	 */
	private List genericEBStringList = new ArrayList();

	/**
	 * EB String List for E024
	 */
	private List eBStringListForE024 = new ArrayList();

	/**
	 * @param contract
	 *            contains the member information and other basic details.
	 * @return payLoadDocument
	 * @throws EBXException
	 */
	public String get27xBenefitAccumRequest(ContractVO contract,
			String environment) throws EBXException {/*

		ResourceBundle rb = ResourceBundle.getBundle(
				DomainConstants.TWENTY_SEVEN_X_REQUEST, Locale.getDefault());
		log.debug("Entering get27xBenefitAccumRequest Method");
		// Initializing variables
		Calendar c1 = Calendar.getInstance(); // today
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT_1);
		String currentDate = dateFormatter.format(c1.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT);
		PayLoadDocument payLoadDocument = null;
		PayLoad payLoad = null;
		EBSHeader ebsHeader = null;

		EBSRequest ebsRequest = null;
		boolean hasException = false;
		EBSRequest.Get27XBenefitAccumsRequest benefitAccumsXml = null;
		Date startDate = null;
		Date endDate = null;
		payLoad = PayLoadDocument.PayLoad.Factory.newInstance();
		payLoadDocument = PayLoadDocument.Factory.newInstance();

		ebsRequest = EBSRequest.Factory.newInstance();

		benefitAccumsXml = EBSRequest.Get27XBenefitAccumsRequest.Factory
				.newInstance();

		EBSRequest.Get27XBenefitAccumsRequest.StaticData staticData = benefitAccumsXml
				.addNewStaticData();

		log.debug("Setting Contract");
		if (!DomainConstants.ACES.equals(contract.getSystem())) {
			if (contract.getContractId() != null) {
				staticData.setProductIdentifier(contract.getContractId());
			} else {
				throw new NullPointerException(
						"ContractId is missing in the request");
			}
		} else{
			staticData.setProductIdentifier("");
		}

		if (!StringUtils.isBlank(contract.getEffectiveDate())) {
			try {
				date = formatter.parse(contract.getEffectiveDate());
				c1.setTime(date);
			} catch (ParseException e) {
				log.error("Incorrect Date is passed in the request: ", e);
				throw new EBXException(
						"Incorrect Date is passed in the request");
			}
			staticData.setEffectiveDate(dateFormatter.format(c1.getTime())
					.toString());
		} else 
		if (DomainConstants.ACES.equals(contract.getSystem())
				|| DomainConstants.FACETS.equals(contract.getSystem())) {
			staticData.setEffectiveDate(dateFormatter.format(c1.getTime())
					.toString());
		} else {
			staticData.setEffectiveDate("");
		}
		staticData.setTerminationDate(rb.getString("terminationDate"));
		
		if (!StringUtils.isBlank(contract.getStartDate())) {
			try {
				startDate = formatter.parse(contract.getStartDate());
				c1.setTime(startDate);
			} catch (ParseException e) {
				log.error("Incorrect Date is passed in the request: ", e);
				throw new EBXException(
						"Incorrect Date is passed in the request");
			}
			staticData.setSearchStartDate(dateFormatter.format(c1.getTime())
					.toString());
		}else{
			staticData.setSearchStartDate(currentDate);
		}
		
		if (!StringUtils.isBlank(contract.getEndDate())) {
			try {
				endDate = formatter.parse(contract.getEndDate());
				c1.setTime(endDate);
			} catch (ParseException e) {
				log.error("Incorrect Date is passed in the request: ", e);
				throw new EBXException(
						"Incorrect Date is passed in the request");
			}
			staticData.setSearchEndDate(dateFormatter.format(c1.getTime())
					.toString());
		}else{
			staticData.setSearchEndDate(currentDate);
		}
		
		//staticData.setSearchEndDate(currentDate);
		//staticData.setSearchStartDate(currentDate);
		if (DomainConstants.FACETS.equals(contract.getSystem())) {
			if (DomainConstants.PRODUCTION.equals(environment)) {
				staticData.setGroupId(rb.getString("groupIdForProd"));
			} else {
				staticData.setGroupId(rb.getString("groupIdForTest"));
			}
		} else {
			staticData.setGroupId(rb.getString("groupId"));
		}

		staticData.setSubgroupId(rb.getString("subgroupId"));
		staticData.setProductBenefitCode(rb.getString("productBenefitCode"));
		staticData.setEnrollmentTypeCode(rb.getString("enrollmentTypeCode"));
		if (null != contract.getServiceTypeCode()){
			staticData.setBenefitServiceCategoryTypeCode(contract
					.getServiceTypeCode());
		}else{
			staticData.setBenefitServiceCategoryTypeCode("");
		}

		if (DomainConstants.FACETS.equals(contract.getSystem())) {
			if (DomainConstants.PRODUCTION.equals(environment)) {
				staticData.setSubscriberId(rb.getString("subscriberIdForProd"));
			} else {
				staticData.setSubscriberId(rb.getString("subscriberIdForTest"));
			}
		} else {
			staticData.setSubscriberId(rb.getString("subscriberId"));
		}
		staticData.setBcbsaControlPlanId(rb.getString("bcbsaControlPlanId"));

		if (DomainConstants.ACES.equals(contract.getSystem())) {
			staticData.setMemberSequenceNumber(rb
					.getString("memberSequenceNumberForACES"));
		} else if (DomainConstants.FACETS.equals(contract.getSystem())) {
			staticData.setMemberSequenceNumber(rb
					.getString("memberSequenceNumberForFACETS"));
		} else {
			staticData.setMemberSequenceNumber(rb
					.getString("memberSequenceNumber"));
		}

		staticData.setFirstName(rb.getString("firstName"));
		staticData.setLastName(rb.getString("lastName"));
		staticData.setBirthDate(rb.getString("birthDate"));
		if (DomainConstants.ACES.equals(contract.getSystem())) {
			staticData.setPrincipalSubscriberIdentifier(rb
					.getString("principalSubscriberIdentifierForACES"));
		} else {
			staticData.setPrincipalSubscriberIdentifier(rb
					.getString("principalSubscriberIdentifier"));
		}
		staticData.setMemberLookupId(rb.getString("memberLookupId"));

		// setting source system Identifier
		if (DomainConstants.ACES.equals(contract.getSystem())) {
			staticData.setSourceSystemIdentifier(DomainConstants.ACES);
		} else if (DomainConstants.FACETS.equals(contract.getSystem())) {
			staticData.setSourceSystemIdentifier(DomainConstants.FCTCR);
		} else if (DomainConstants.STAR.equals(contract.getSystem())||DomainConstants.WPD_ISG.equals(contract.getSystem())) {
			staticData.setSourceSystemIdentifier(DomainConstants.STAR);
		}else if (DomainConstants.WGS.equals(contract.getSystem())||DomainConstants.WPD_LG.equals(contract.getSystem())) {
			staticData.setSourceSystemIdentifier(DomainConstants.WGS);
		} else if (DomainConstants.EWPD.equals(contract.getSystem())){
			staticData.setSourceSystemIdentifier(DomainConstants.EMPTY);
		}
		
		if (DomainConstants.ACES.equals(contract.getSystem())) {
			staticData.setSourceSystemIdentifier(DomainConstants.ACES);
		} else if (DomainConstants.FACETS.equals(contract.getSystem())) {
			staticData.setSourceSystemIdentifier(DomainConstants.FCTCR);
		} else {
			staticData.setSourceSystemIdentifier(DomainConstants.WGS);
		}

		EBSRequest.Get27XBenefitAccumsRequest.StaticData.BenefitClass beneficialClass = staticData
				.addNewBenefitClass();
		if (DomainConstants.ACES.equals(contract.getSystem())) {
			if (contract.getContractId() != null) {
				beneficialClass.setBenefitClassID(contract.getContractId());
			} else {
				throw new NullPointerException(
						"ContractId is missing in the request");
			}
		} else {
			beneficialClass.setBenefitClassID(rb.getString("benefitClassID"));
		}
		EBSRequest.Get27XBenefitAccumsRequest.StaticData.BenefitClass beneficialClass1 = staticData
				.addNewBenefitClass();
		beneficialClass1.setBenefitClassID(rb.getString("benefitClassID1"));

		EBSRequest.Get27XBenefitAccumsRequest.StaticData.BenefitClass beneficialClass2 = staticData
				.addNewBenefitClass();
		beneficialClass2.setBenefitClassID(rb.getString("benefitClassID2"));

		EBSRequest.Get27XBenefitAccumsRequest.StaticData.BenefitClass beneficialClass3 = staticData
				.addNewBenefitClass();
		beneficialClass3.setBenefitClassID(rb.getString("benefitClassID3"));

		EBSRequest.Get27XBenefitAccumsRequest.StaticData.BenefitClass beneficialClass4 = staticData
				.addNewBenefitClass();
		beneficialClass4.setBenefitClassID(rb.getString("benefitClassID4"));

		benefitAccumsXml.setStaticData(staticData);

		ebsRequest.setGet27XBenefitAccumsRequest(benefitAccumsXml);
		payLoad.addNewEBSRequest();

		log.debug("Creating EBSHeader.");
		payLoad.setEBSRequest(ebsRequest);
		ebsHeader = EBSHeader.Factory.newInstance();
		ebsHeader = payLoad.addNewEBSHeader();
		ebsHeader.setRequestRows(rb.getString("requestRows"));
		ebsHeader.setRequestRowLength(rb.getString("requestRowLength"));
		ebsHeader.setResponseStaticRows(rb.getString("responseStaticRows"));
		ebsHeader.setResponseStaticLength(rb.getString("responseStaticLength"));
		ebsHeader.setResponseDynamicRows(rb.getString("responseDynamicRows"));
		ebsHeader.setResponseDynamicRowLength(rb
				.getString("responseDynamicRowLength"));
		if (hasException) {
			ebsHeader.setErrorRows(rb.getString("errorRows1"));
		} else {
			ebsHeader.setErrorRows(rb.getString("errorRows0"));
		}
		// Change as part of Pagination - Start
		ebsHeader.setMoreData(contract.getMoreDataForPagination());
		// Change as part of Pagination - End
		ebsHeader.setClientRowsPerPage(rb.getString("clientRowsPerPage"));
		ebsHeader.setSecurityToken(rb.getString("securityToken"));
		ebsHeader.setClientUserID(rb.getString("clientUserID"));
		ebsHeader.setClientUserPassword(rb.getString("clientUserPassword"));
		ebsHeader.setServiceDuration(rb.getString("serviceDuration"));
		// Change as part of Pagination - Start
		ebsHeader.setSbiAdditionalPageKeys(contract.getNextPageSize());
		// Change as part of Pagination - End
		payLoad.setEBSHeader(ebsHeader);

		payLoadDocument.addNewPayLoad();
		payLoadDocument.setPayLoad(payLoad);

		XmlOptions xmlOptions = new XmlOptions();
		HashMap map = new HashMap();
		map.put("", DomainConstants.NAMESPACE);
		xmlOptions.setUseDefaultNamespace().setSaveImplicitNamespaces(map);
		xmlOptions.setCompileSubstituteNames(null);
		xmlOptions.setSavePrettyPrint();

		log.debug("Exiting get27xBenefitAccumRequest Method");
		return payLoadDocument.xmlText(xmlOptions);

													 */
		return null;
	}

	public String get27xBenefitAccumRequest5010(ContractVO contract,
			String environment) throws EBXException {

		ResourceBundle rb = ResourceBundle.getBundle(
				DomainConstants.TWENTY_SEVEN_X_REQUEST, Locale.getDefault());
		log.debug("Entering get27xBenefitAccumRequest Method");
		// Initializing variables
		Calendar c1 = Calendar.getInstance(); // today
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT_1);
		String currentDate = dateFormatter.format(c1.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT);
		PayLoadDocument payLoadDocument = null;
		PayLoad payLoad = null;
		EBSHeader ebsHeader = null;

		EBSRequest ebsRequest = null;
		boolean hasException = false;
		Get27XBenefitAccumsRequest benefitAccumsXml = null;
		Date startDate = null;
		Date endDate = null;
		Date birthDate = null;
		payLoad = PayLoadDocument.PayLoad.Factory.newInstance();
		payLoadDocument = PayLoadDocument.Factory.newInstance();

		ebsRequest = EBSRequest.Factory.newInstance();

		benefitAccumsXml = Get27XBenefitAccumsRequest.Factory
				.newInstance();

		Get27XBenefitAccumsRequest.StaticData staticData = benefitAccumsXml
				.addNewStaticData();

		log.debug("Setting Contract");
		if (!DomainConstants.ACES.equals(contract.getSystem())) {
			if (contract.getContractId() != null) {
				staticData.setProductIdentifier(contract.getContractId());
			} else {
				throw new NullPointerException(
						"ContractId is missing in the request");
			}
		} else {
			staticData.setProductIdentifier("");
		}

		if (DomainConstants.ACES.equals(contract.getSystem())
				|| DomainConstants.FACETS.equals(contract.getSystem())) {
			staticData.setEffectiveDate(dateFormatter.format(c1.getTime())
					.toString());
		} else {
			staticData.setEffectiveDate("");
		}
		staticData.setTerminationDate(rb.getString("terminationDate"));
		staticData.setSpouseDependentCode(rb.getString("spouseDependentCode"));

		if (!StringUtils.isBlank(contract.getStartDate())) {
			try {
				startDate = formatter.parse(contract.getStartDate());
				c1.setTime(startDate);
			} catch (ParseException e) {
				log.error("Incorrect Date is passed in the request: ", e);
				throw new EBXException(
						"Incorrect Date is passed in the request");
			}
			staticData.setSearchStartDate(dateFormatter.format(c1.getTime())
					.toString());
		} else {
			staticData.setSearchStartDate(currentDate);
		}

		if (!StringUtils.isBlank(contract.getEndDate())) {
			try {
				endDate = formatter.parse(contract.getEndDate());
				c1.setTime(endDate);
			} catch (ParseException e) {
				log.error("Incorrect Date is passed in the request: ", e);
				throw new EBXException(
						"Incorrect Date is passed in the request");
			}
			staticData.setSearchEndDate(dateFormatter.format(c1.getTime())
					.toString());
		} else {
			staticData.setSearchEndDate(currentDate);
		}
		/*BXNI June 2013 Release Starts*/
		if (!StringUtils.isBlank(contract.getDateOfBirth())) {
			try {
				birthDate = formatter.parse(contract.getDateOfBirth());
				c1.setTime(birthDate);
			} catch (ParseException e) {
				log.error("Incorrect Date is passed in the request: ", e);
				throw new EBXException(
						"Incorrect Date is passed in the request");
			}
			staticData.setBirthDate(dateFormatter.format(c1.getTime())
					.toString());
		} else {
			staticData.setBirthDate(rb.getString("birthDate"));
		}
		//START-Added as part of RBBPOR April release change
		staticData.setMemberStateCode(rb.getString("memberStateCode"));
		staticData.setPCPId(rb.getString("PCPId"));
		//END-Added as part of RBBPOR April release change
	
		/*BXNI June 2013 Release Ends*/
	
		// staticData.setSearchEndDate(currentDate);
		// staticData.setSearchStartDate(currentDate);
		if (DomainConstants.FACETS.equals(contract.getSystem())) {
			if (DomainConstants.PRODUCTION.equals(environment)) {
				staticData.setGroupId(rb.getString("groupIdForProd"));
			} else {
				staticData.setGroupId(rb.getString("groupIdForTest"));
			}
		} else {
			staticData.setGroupId(rb.getString("groupId"));
		}

		staticData.setSubgroupId(rb.getString("subgroupId"));
		staticData.setProductBenefitCode(rb.getString("productBenefitCode"));
		staticData.setEnrollmentTypeCode(rb.getString("enrollmentTypeCode"));
		if (null != contract.getServiceTypeCode()) {
			staticData.setBenefitServiceCategoryTypeCode(contract
					.getServiceTypeCode());
		} else {
			staticData.setBenefitServiceCategoryTypeCode("");
		}

		if (DomainConstants.FACETS.equals(contract.getSystem())) {
			if (DomainConstants.PRODUCTION.equals(environment)) {
				staticData.setSubscriberId(rb.getString("subscriberIdForProd"));
			} else {
				staticData.setSubscriberId(rb.getString("subscriberIdForTest"));
			}
		} else {
			staticData.setSubscriberId(rb.getString("subscriberId"));
		}
		staticData.setBcbsaControlPlanId(rb.getString("bcbsaControlPlanId"));

		if (DomainConstants.ACES.equals(contract.getSystem())) {
			staticData.setMemberSequenceNumber(rb
					.getString("memberSequenceNumberForACES"));
		} else if (DomainConstants.FACETS.equals(contract.getSystem())) {
			staticData.setMemberSequenceNumber(rb
					.getString("memberSequenceNumberForFACETS"));
		} else {
			staticData.setMemberSequenceNumber(rb
					.getString("memberSequenceNumber"));
		}

		staticData.setFirstName(rb.getString("firstName"));
		staticData.setLastName(rb.getString("lastName"));
		//staticData.setBirthDate(rb.getString("birthDate"));
		if (DomainConstants.ACES.equals(contract.getSystem())) {
			staticData.setPrincipalSubscriberIdentifier(rb
					.getString("principalSubscriberIdentifierForACES"));
		} else {
			staticData.setPrincipalSubscriberIdentifier(rb
					.getString("principalSubscriberIdentifier"));
		}
		staticData.setMemberLookupId(rb.getString("memberLookupId"));

		// setting source system Identifier

		if (DomainConstants.ACES.equals(contract.getSystem())) {
			staticData.setSourceSystemIdentifier(DomainConstants.ACES);
		} else if (DomainConstants.FACETS.equals(contract.getSystem())) {
			staticData.setSourceSystemIdentifier(DomainConstants.FCTCR);
		} else if (DomainConstants.STAR.equals(contract.getSystem())||DomainConstants.WPD_ISG.equals(contract.getSystem())) {
			staticData.setSourceSystemIdentifier(DomainConstants.STAR);
		}else if (DomainConstants.WGS.equals(contract.getSystem())||DomainConstants.WPD_LG.equals(contract.getSystem())) {
			staticData.setSourceSystemIdentifier(DomainConstants.WGS);
		} else if (DomainConstants.EWPD.equals(contract.getSystem())){
			staticData.setSourceSystemIdentifier(DomainConstants.EMPTY);
		}
			
			/*if (DomainConstants.ACES.equals(contract.getSystem())) {
				staticData.setSourceSystemIdentifier(DomainConstants.ACES);
			} else if (DomainConstants.FACETS.equals(contract.getSystem())) {
				staticData.setSourceSystemIdentifier(DomainConstants.FCTCR);
			} else {
				staticData.setSourceSystemIdentifier(DomainConstants.WGS);
			}		 */
		BenefitClass beneficialClass = staticData.addNewBenefitClass();
		if (DomainConstants.ACES.equals(contract.getSystem())) {
			if (contract.getContractId() != null) {
				beneficialClass.setBenefitClassID(contract.getContractId());
			} else {
				throw new NullPointerException(
						"ContractId is missing in the request");
			}
		} else {
			beneficialClass.setBenefitClassID(rb.getString("benefitClassID"));
		}
		BenefitClass beneficialClass1 = staticData.addNewBenefitClass();
		beneficialClass1.setBenefitClassID(rb.getString("benefitClassID1"));

		BenefitClass beneficialClass2 = staticData.addNewBenefitClass();
		beneficialClass2.setBenefitClassID(rb.getString("benefitClassID2"));

		BenefitClass beneficialClass3 = staticData.addNewBenefitClass();
		beneficialClass3.setBenefitClassID(rb.getString("benefitClassID3"));

		BenefitClass beneficialClass4 = staticData.addNewBenefitClass();
		beneficialClass4.setBenefitClassID(rb.getString("benefitClassID4"));

		benefitAccumsXml.setStaticData(staticData);

		ebsRequest.setGet27XBenefitAccumsRequest(benefitAccumsXml);
		payLoad.addNewEBSRequest();

		log.debug("Creating EBSHeader.");
		payLoad.setEBSRequest(ebsRequest);
		ebsHeader = EBSHeaderDocument.EBSHeader.Factory.newInstance();
		ebsHeader = payLoad.addNewEBSHeader();
		ebsHeader.setRequestRows(rb.getString("requestRows"));
		ebsHeader.setRequestRowLength(rb.getString("requestRowLength"));
		ebsHeader.setResponseStaticRows(rb.getString("responseStaticRows"));
		ebsHeader.setResponseStaticLength(rb.getString("responseStaticLength"));
		ebsHeader.setResponseDynamicRows(rb.getString("responseDynamicRows"));
		ebsHeader.setResponseDynamicRowLength(rb
				.getString("responseDynamicRowLength"));
		if (hasException) {
			ebsHeader.setErrorRows(rb.getString("errorRows1"));
		} else {
			ebsHeader.setErrorRows(rb.getString("errorRows0"));
		}
		// Change as part of Pagination - Start
		ebsHeader.setMoreData(contract.getMoreDataForPagination());
		// Change as part of Pagination - End
		// ebsHeader.setClientRowsPerPage(rb.getString("clientRowsPerPage"));
		if (DomainConstants.FACETS.equals(contract.getSystem())) {
			ebsHeader.setClientRowsPerPage(rb.getString("clientRowsPerPageForFacets"));
			ebsHeader.setSecurityToken(rb.getString("securityTokenForFacets"));
		} else {
			ebsHeader.setClientRowsPerPage(rb.getString("clientRowsPerPage"));
			ebsHeader.setSecurityToken(rb.getString("securityToken"));
		}
		// ebsHeader.setSecurityToken(rb.getString("securityToken"))
		ebsHeader.setClientUserID(rb.getString("clientUserID"));
		ebsHeader.setClientUserPassword(rb.getString("clientUserPassword"));
		ebsHeader.setServiceDuration(rb.getString("serviceDuration"));
		// Change as part of Pagination - Start
		ebsHeader.setSbiAdditionalPageKeys(contract.getNextPageSize());
		// Change as part of Pagination - End
		payLoad.setEBSHeader(ebsHeader);

		payLoadDocument.addNewPayLoad();
		payLoadDocument.setPayLoad(payLoad);

		XmlOptions xmlOptions = new XmlOptions();
		HashMap map = new HashMap();
		map.put("", DomainConstants.NAMESPACE_V4);
		xmlOptions.setUseDefaultNamespace().setSaveImplicitNamespaces(map);
		xmlOptions.setCompileSubstituteNames(null);
		xmlOptions.setSavePrettyPrint();

		//System.out.println("Exiting get27xBenefitAccumRequest Method:"+payLoadDocument.xmlText(xmlOptions));
		log.info("Exiting get27xBenefitAccumRequest Method:"+payLoadDocument.xmlText(xmlOptions));
		return payLoadDocument.xmlText(xmlOptions);

	}

	public String formatString(String value) {
		String regex_leading = "^0+";
		String regex_trailing = "\\.0*$";
		String replacement = "";
		value = value.replaceAll(regex_leading, replacement);
		value = value.replaceAll(regex_trailing, replacement);
		return (value);
	}

	/**
	 * @param benefitAccumResponse
	 *            containing the Response XML with values of contract
	 * @return Contract
	 * @throws EBXException
	 */
	public ContractVO process27xBenefitAccumResponse(
			String benefitAccumResponse, String system) throws EBXException,
			XmlException, Exception {/*
log.debug("Entering process27xBenefitAccumResponse Method");
		// Initializing Variables
		ContractVO contract = new ContractVO();
		Map majorHeadingsMap = new HashMap();
		Map minorHeadingsMap = new HashMap();
		Map mappingMap = new HashMap();
		DynamicData dynamicData = null;
		Major major = null;
		Minor minor = null;
		Benefit benefit = null;
		StandardizeBenefit stdBenefit = null;
		MajorHeadingsVO majorHeadings = null;
		MinorHeadingsVO minorHeadings = null;
		Mapping mapping = null;
		ContractMappingVO contractMapping = null;
		PayLoadDocument payLoadDoc = null;
		XmlOptions options = new XmlOptions();
		Map map = new HashMap();
		StringBuffer errorBuffer = null;
		HippaSegment hippaSegment = null;
		HippaSegment iii02 = null;
		List list = null;
		StringBuffer msgBuffer = null;
		try {
			// setting the namespace in the response.
			log.debug("setting the namespace in the response");
			map.put("", DomainConstants.NAMESPACE);
			options.setLoadSubstituteNamespaces(map);
			// Getting the PayLoadDocument
			payLoadDoc = PayLoadDocument.Factory.parse(benefitAccumResponse,
					options);
			// System.err.println("+++++++++++\n" + payLoadDoc.xmlText() +
			// "\n+++++++++++");
		} catch (XmlException e) {
			log.error("XmlException: ", e);
			throw new XmlException(e.getMessage());
		} catch (NullPointerException e) {
			log.error("NullPointerException: ", e);
			throw new EBXException("Request timed out");
		} catch (Exception e) {
			log.error("Exception: ", e);
			throw new Exception(e.getMessage());
		}

		PayLoad payLoad = payLoadDoc.getPayLoad();
		EBSResponse ebsResponse = payLoad.getEBSResponse();
		EBSExceptions ebsException = payLoad.getEBSExceptions();

		// Change as part of Defect fix done on Pagination - Start
		EBSHeader ebsHeader = payLoad.getEBSHeader();
		contract.setMoreDataForPagination(ebsHeader.getMoreData());
		contract.setNextPageSize(ebsHeader.getSbiAdditionalPageKeys());
		// Change as part of Defect fix done on Pagination - End

		// Added for error to be appended in a buffer
		errorBuffer = new StringBuffer();
		if (ebsException.getEBSExceptionArray() != null) {
			for (int e = 0; e < ebsException.getEBSExceptionArray().length; e++) {
				log.debug(""
						+ ebsException.getEBSExceptionArray(e)
								.getExceptionDetail());
				errorBuffer.append("Error Code: ")
						.append(
								ebsException.getEBSExceptionArray(e)
										.getExceptionCode());
				errorBuffer.append(", ").append("Error Detail: ").append(
						ebsException.getEBSExceptionArray(e)
								.getExceptionDetail()
								+ ", ");
			}

			if (!StringUtils.isBlank(errorBuffer.toString())) {
				throw new EBXException(errorBuffer.toString());
			}
		}
		Get27XBenefitAccumsResponse get27XBenefitAccumsResponse = ebsResponse
				.getGet27XBenefitAccumsResponse();

		StaticData staticData = get27XBenefitAccumsResponse.getStaticData();
		if (null != staticData
				&& !StringUtils.isBlank(staticData.getProductIdentifier())) {
			contract.setContractId(staticData.getProductIdentifier());
		}
		log
				.debug("Retrieving the dynamic data from the response, and creating a contract");
		DynamicData[] dynamicDatas = get27XBenefitAccumsResponse
				.getDynamicDataArray();
		if (dynamicDatas != null) {
			for (int i = 0; i < dynamicDatas.length; i++) {
				float percent = 0;
				// Getting the Details for Mapping and ContractMapping.
				mapping = new Mapping();
				contractMapping = new ContractMappingVO();

				// Getting the benefit and stdBenefit from DynamicData
				dynamicData = dynamicDatas[i];
				benefit = dynamicData.getBenefit();
				stdBenefit = benefit.getStandardizeBenefit();

				// Setting Eligibilty Benefit Information from the Response
				if (!StringUtils.isBlank(stdBenefit
						.getCostShareRuleCategoryCode())) {
					// setting EB01
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(stdBenefit.getCostShareRuleCategoryCode());
					hippaSegment.setHippaCodePossibleValues(list);
					mapping.setEb01(hippaSegment);
				}
				if (!StringUtils.isBlank(stdBenefit
						.getCostShareRuleApplicationLevelCode())) {
					// setting EB02
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(stdBenefit.getCostShareRuleApplicationLevelCode());
					hippaSegment.setHippaCodePossibleValues(list);
					mapping.setEb02(hippaSegment);
				}
				if (!StringUtils.isBlank(stdBenefit
						.getBenefitServiceCategoryTypeCode())) {
					// setting EB03
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(stdBenefit.getBenefitServiceCategoryTypeCode());
					hippaSegment.setHippaCodePossibleValues(list);
					mapping.setEb03(hippaSegment);
				}
				if (null != staticData
						&& !StringUtils.isBlank(staticData
								.getHealthcareArrangementCode())) {
					// setting EB04
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(staticData.getHealthcareArrangementCode());
					hippaSegment.setHippaCodePossibleValues(list);
					contractMapping.setEb04(hippaSegment);
				}
				if (null != staticData
						&& !StringUtils.isBlank((staticData
								.getProductDescriptionText()))) {
					// Setting EB05
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(staticData.getProductDescriptionText());
					hippaSegment.setHippaCodePossibleValues(list);
					contractMapping.setEb05(hippaSegment);
				}
				if (!StringUtils.isBlank(stdBenefit
						.getTimePeriodQualifierCode())) {
					// Setting EB06
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(stdBenefit.getTimePeriodQualifierCode());
					hippaSegment.setHippaCodePossibleValues(list);
					mapping.setEb06(hippaSegment);
				}
				if (!StringUtils.isBlank(benefit.getCostShareValueTypeCode())) {
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					 
					String costShareValueTypeCode = benefit
							.getCostShareValueTypeCode().trim();
					String benefitServiceCategoryUnitLimitValue = benefit
							.getBenefitServiceCategoryUnitLimitValue();
					
				if (costShareValueTypeCode.equals(DomainConstants.ZERO_ONE) || costShareValueTypeCode.equals(DomainConstants.ZERO_FIVE)) {
						// setting EB07
						
					if (!StringUtils
							.isBlank((benefitServiceCategoryUnitLimitValue))) {
						  String value=formatString(benefitServiceCategoryUnitLimitValue);
						if((value.trim().equals(""))){
							value="0";
						}
						list.add(value);
						
								
					}
						
						hippaSegment.setHippaCodePossibleValues(list);
						contractMapping.setEb07(hippaSegment);
					} else if (costShareValueTypeCode
							.equals(DomainConstants.ZERO_TWO)) {
						// setting EB08
						if (!StringUtils
								.isBlank((benefitServiceCategoryUnitLimitValue))) {
							// if system equals Facets, then we need not convert
							// into decimals, as the response itself is in that
							// format.
							if (DomainConstants.FACETS.equals(system)) {
								String value = benefitServiceCategoryUnitLimitValue;
								if (value.startsWith("0.")) {
									value = formatString(value.substring(1, value.length()));
									if((value.trim().equals(""))){
										value="0";
									}
								}
								list.add(value);
							} else {
								try {
									percent = Float
											.parseFloat(benefitServiceCategoryUnitLimitValue);
									percent = percent / 100;
									String value = "";
									if (percent == 0) {
										value = "0";
									} else {
										value = formatString(String.valueOf(percent));
										if((value.trim().equals(""))){
											value="0";
										}
									}
									if (value.startsWith("0.")) {
										value = formatString(value.substring(1, value
												.length()));
										if((value.trim().equals(""))){
											value="0";
										}
									}
									list.add(value);
								} catch (NumberFormatException e) {
									log
											.debug("NumberFormatException Caught for : "
													+ benefitServiceCategoryUnitLimitValue);
									
								String value=formatString(benefitServiceCategoryUnitLimitValue);
								if((value.trim().equals(""))){
									value="0";
								}
									list
											.add(value);
								}
							}
						}
						hippaSegment.setHippaCodePossibleValues(list);
						contractMapping.setEb08(hippaSegment);
					} else if (costShareValueTypeCode
							.equals(DomainConstants.ZERO_THREE)) {
						// setting EB10
						if (!StringUtils
								.isBlank((benefitServiceCategoryUnitLimitValue))) {
							  String value=formatString(benefitServiceCategoryUnitLimitValue);
							if((value.trim().equals(""))){
								value="0";
							}
							list.add(value);
							
									
						}
						
						hippaSegment.setHippaCodePossibleValues(list);
						contractMapping.setEb10(hippaSegment);
					}
				}
				if (!StringUtils.isBlank(stdBenefit
						.getBenefitServiceCategoryUnitLimitTypeCode())) {
					// setting EB09
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(stdBenefit
							.getBenefitServiceCategoryUnitLimitTypeCode());
					hippaSegment.setHippaCodePossibleValues(list);
					contractMapping.setEb09(hippaSegment);
				}
				if (!StringUtils.isBlank(stdBenefit
						.getPreAuthorizationRequiredInd())) {
					// setting EB11
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(system.concat(EB11_STRING).concat(stdBenefit.getPreAuthorizationRequiredInd()));
					hippaSegment.setHippaCodePossibleValues(list);
					contractMapping.setEb11(hippaSegment);
				}
				if (!StringUtils.isBlank(stdBenefit.getInNetworkIndicator())) {
					// setting EB12
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(stdBenefit.getInNetworkIndicator());
					hippaSegment.setHippaCodePossibleValues(list);
					contractMapping.setEb12(hippaSegment);
				}

				mapping.setContractMapping(contractMapping);

				for (int k = 0; k < stdBenefit
						.getHealthCareServicesDeliveryArray().length; k++) {

					HealthCareServicesDelivery[] healthCareServicesDelivery = stdBenefit
							.getHealthCareServicesDeliveryArray();
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getCostShareValueTypeCode())) {
						// setting HSD01
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						list.add(healthCareServicesDelivery[k]
								.getCostShareValueTypeCode());
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd01(hippaSegment);
					}
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getBenefitServiceCategoryUnitLimitValue())) {
						// setting HSD02
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						list.add(healthCareServicesDelivery[k]
								.getBenefitServiceCategoryUnitLimitValue());
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd02(hippaSegment);
					}
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getUnitBaseMeasurementCode())) {
						// setting HSD03
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						list.add(healthCareServicesDelivery[k]
								.getUnitBaseMeasurementCode());
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd03(hippaSegment);
					}
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getSampleSelectionModulusAmount())) {
						// setting HSD04
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						list.add(healthCareServicesDelivery[k]
								.getSampleSelectionModulusAmount());
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd04(hippaSegment);
					}
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getTimePeriodQualiferCode())) {
						// setting HSD05
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						list.add(healthCareServicesDelivery[k]
								.getTimePeriodQualiferCode());
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd05(hippaSegment);
					}
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getPeriodCount())) {
						// setting HSD06
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						list
								.add(healthCareServicesDelivery[k]
										.getPeriodCount());
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd06(hippaSegment);
					}
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getShipDeliveryCalendarPatternCode())) {
						// setting HSD07
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						list.add(healthCareServicesDelivery[k]
								.getShipDeliveryCalendarPatternCode());
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd07(hippaSegment);
					}
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getShipDeliveryTimePatternCode())) {
						// setting HSD08
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						list.add(healthCareServicesDelivery[k]
								.getShipDeliveryTimePatternCode());
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd08(hippaSegment);
					}

				}
				// Setting the messages separated by tilda
				msgBuffer = new StringBuffer();
				for (int msg = 0; msg < stdBenefit.getFreeFormNoteTextArray().length; msg++) {
					if (!StringUtils.isBlank(stdBenefit
							.getFreeFormNoteTextArray(msg).getNoteText())) {
						msgBuffer.append(DomainConstants.MSG
								+ stdBenefit.getFreeFormNoteTextArray(msg)
										.getNoteText()
								+ DomainConstants.TILDA_CHAR);
					}
				}
				mapping.setMessage(msgBuffer.toString());

				list = new ArrayList();
				if (stdBenefit.getDiagnosisPlaceOfService() != null) {
					DiagnosisPlaceOfService diagnosisPlaceOfService = stdBenefit
							.getDiagnosisPlaceOfService();
					if (!StringUtils.isBlank(diagnosisPlaceOfService
							.getDiagnosisPlaceofServiceCode())) {
						iii02 = new HippaSegment();
						list.add(diagnosisPlaceOfService
								.getDiagnosisPlaceofServiceCode());
						iii02.setHippaCodePossibleValues(list);
						mapping.setIi02(iii02);
					}
				}

				log.debug("Setting mapping");
				mappingMap.put(Integer.valueOf(i), mapping);

				// Setting Minor Headings
				log.debug("Setting MinorHeadings");
				minorHeadings = new MinorHeadingsVO();
				minor = dynamicData.getMinor();
				if (StringUtils.isBlank(minor.getBenefitDescriptionText())) {
					minorHeadings.setDescriptionText(minor
							.getBenefitDescriptionText());
				}
				minorHeadings.setMappings(mappingMap);
				minorHeadingsMap.put(Integer.valueOf(i), minorHeadings);

				// Setting Major Headings
				log.debug("Setting MajorHeadings");
				majorHeadings = new MajorHeadingsVO();
				major = dynamicData.getMajor();
				majorHeadings.setDescriptionText(major
						.getBenefitDescriptionText());
				majorHeadings.setMinorHeadings(minorHeadingsMap);
				majorHeadingsMap.put(Integer.valueOf(i), majorHeadings);
			}
			log.debug("Setting Contract");
			contract.setMajorHeadings(majorHeadingsMap);
		}
		log.debug("Exiting process27xBenefitAccumResponse Method");
		return contract;									 
	*/
		return null;
	}

	public ContractVO process27xBenefitAccumResponse5010(
			String benefitAccumResponse, String system) throws EBXException,
			XmlException, Exception {
		log.debug("Entering process27xBenefitAccumResponse Method");
		// Initializing Variables
		ContractVO contract = new ContractVO();
		Map majorHeadingsMap = new HashMap();
		Map minorHeadingsMap = new HashMap();
		Map mappingMap = new HashMap();
		DynamicData dynamicData = null;
		Major major = null;
		Minor minor = null;
		Benefit benefit = null;
		StandardizeBenefit stdBenefit = null;
		BenefitLimitInfo[] benefitLimitInfos = null;
		MajorHeadingsVO majorHeadings = null;
		MinorHeadingsVO minorHeadings = null;
		Mapping mapping = null;
		ContractMappingVO contractMapping = null;
		PayLoadDocument payLoadDoc = null;
		XmlOptions options = new XmlOptions();
		Map map = new HashMap();
		StringBuffer errorBuffer = null;
		HippaSegment hippaSegment = null;
		HippaSegment iii02 = null;
		List list = null;
		StringBuffer msgBuffer = null;
		HippaSegment nm1HipaaSegment = null;
		// [U20776] SSCR15637
		// boolean isNetworkTypeAsW=false;
		int insertPos = 0;
		try {
			// setting the namespace in the response.
			log.debug("setting the namespace in the response");
			map.put("", DomainConstants.NAMESPACE_V4);
			options.setLoadSubstituteNamespaces(map);
			// Getting the PayLoadDocument

			payLoadDoc = PayLoadDocument.Factory.parse(benefitAccumResponse,
					options);
			try {
				// Create file
				FileWriter fstream = new FileWriter("output.txt");
				BufferedWriter out = new BufferedWriter(fstream);
				out.write(payLoadDoc.xmlText());
				// Close the output stream
				out.close();
			} catch (Exception e) {// Catch exception if any
				//WLPRD02076161--->start
				//System.err.println("Error: " + e.getMessage());
				log.info("Error: " + e.getMessage());
				//WLPRD02076161--->end
			}

			// System.err.println("+++++++++++\n" + payLoadDoc.xmlText() +
			// "\n+++++++++++");
		} catch (XmlException e) {
			log.error("XmlException: ", e);
			throw new XmlException(e.getMessage());
		} catch (NullPointerException e) {
			log.error("NullPointerException: ", e);
			throw new EBXException("Request timed out");
		} catch (Exception e) {
			log.error("Exception: ", e);
			throw new Exception(e.getMessage());
		}

		PayLoad payLoad = payLoadDoc.getPayLoad();
		EBSResponse ebsResponse = payLoad.getEBSResponse();
		EBSExceptions ebsException = payLoad.getEBSExceptions();

		// Change as part of Defect fix done on Pagination - Start
		EBSHeader ebsHeader = payLoad.getEBSHeader();
		contract.setMoreDataForPagination(ebsHeader.getMoreData());
		contract.setNextPageSize(ebsHeader.getSbiAdditionalPageKeys());
		// Change as part of Defect fix done on Pagination - End

		// Added for error to be appended in a buffer
		errorBuffer = new StringBuffer();
		if (ebsException.getEBSExceptionArray() != null) {
			for (int e = 0; e < ebsException.getEBSExceptionArray().length; e++) {
				log.debug(""
						+ ebsException.getEBSExceptionArray(e)
								.getExceptionDetail());
				errorBuffer.append("Error Code: ")
				.append(
						ebsException.getEBSExceptionArray(e)
								.getExceptionCode());
		errorBuffer.append(", ").append("Error Detail: ").append(
				ebsException.getEBSExceptionArray(e)
						.getExceptionDetail()
						+ ", ");
			}

			if (!StringUtils.isBlank(errorBuffer.toString())) {
				throw new EBXException(errorBuffer.toString());
			}
		}
		EBSResponseDocument.EBSResponse.
		Get27XBenefitAccumsResponse get27XBenefitAccumsResponse = ebsResponse.getGet27XBenefitAccumsResponse();

		StaticData staticData = get27XBenefitAccumsResponse.getStaticData();
		if (null != staticData
				&& !StringUtils.isBlank(staticData.getProductIdentifier())) {
			contract.setContractId(staticData.getProductIdentifier());
		}
		log.debug("Retrieving the dynamic data from the response, and creating a contract");
		DynamicData[] dynamicDatas = get27XBenefitAccumsResponse
				.getDynamicDataArray();
		if (dynamicDatas != null) {
			for (int i = 0; i < dynamicDatas.length; i++) {
				float percent = 0;
				// [U20776] SSCR15637
				// isNetworkTypeAsW=false;

				// Getting the benefit and stdBenefit from DynamicData
				dynamicData = dynamicDatas[i];
				benefit = dynamicData.getBenefit();
				stdBenefit = benefit.getStandardizeBenefit();
				benefitLimitInfos = benefit.getBenefitLimitInfoArray();
				
				// [U20776] SSCR15637 - Removed for loop
				// Getting the Details for Mapping and ContractMapping.
				mapping = new Mapping();
				contractMapping = new ContractMappingVO();

				// Setting Eligibilty Benefit Information from the Response
				if (!StringUtils.isBlank(stdBenefit
						.getCostShareRuleCategoryCode())) {
					// setting EB01
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(stdBenefit.getCostShareRuleCategoryCode());
					hippaSegment.setHippaCodePossibleValues(list);
					mapping.setEb01(hippaSegment);
				}
				if (!StringUtils.isBlank(stdBenefit
						.getCostShareRuleApplicationLevelCode())) {
					// setting EB02
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(stdBenefit.getCostShareRuleApplicationLevelCode());
					hippaSegment.setHippaCodePossibleValues(list);
					mapping.setEb02(hippaSegment);
				}
				if (!StringUtils.isBlank(stdBenefit
						.getBenefitServiceCategoryTypeCode())) {
					// setting EB03
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(stdBenefit.getBenefitServiceCategoryTypeCode());
					hippaSegment.setHippaCodePossibleValues(list);
					mapping.setEb03(hippaSegment);
				}
				if (null != staticData
						&& !StringUtils.isBlank(staticData
								.getHealthcareArrangementCode())) {
					// setting EB04
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(staticData.getHealthcareArrangementCode());
					hippaSegment.setHippaCodePossibleValues(list);
					contractMapping.setEb04(hippaSegment);
				}
				if (null != staticData
						&& !StringUtils.isBlank((staticData
								.getProductDescriptionText()))) {
					// Setting EB05
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(staticData.getProductDescriptionText());
					hippaSegment.setHippaCodePossibleValues(list);
					contractMapping.setEb05(hippaSegment);
				}
				if (!StringUtils.isBlank(stdBenefit
						.getTimePeriodQualifierCode())) {
					// Setting EB06
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(stdBenefit.getTimePeriodQualifierCode());
					hippaSegment.setHippaCodePossibleValues(list);
					mapping.setEb06(hippaSegment);
				}
				if (!StringUtils.isBlank(stdBenefit
						.getAuthrznTypCd())) {
					// setting EB11
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(system.concat(EB11_STRING).concat(stdBenefit.getAuthrznTypCd()));
					hippaSegment.setHippaCodePossibleValues(list);
					contractMapping.setEb11(hippaSegment);
				}

				for (BenefitLimitInfo ben : benefitLimitInfos) {
					if (!StringUtils.isBlank(ben.getCostShareValueTypeCode())) {

						hippaSegment = new HippaSegment();
						list = new ArrayList();

						String costShareValueTypeCode = ben
								.getCostShareValueTypeCode().trim();
						String benefitServiceCategoryUnitLimitValue = ben
								.getBenefitServiceCategoryUnitLimitValue();

						if (costShareValueTypeCode.equals(DomainConstants.ZERO_ONE) || costShareValueTypeCode.equals(DomainConstants.ZERO_FIVE)) {
							// setting EB07

							if (!StringUtils
									.isBlank((benefitServiceCategoryUnitLimitValue))) {
								String value = formatString(benefitServiceCategoryUnitLimitValue);
								if ((value.trim().equals(""))) {
									value = "0";
								}
								list.add(value);

							}

							hippaSegment.setHippaCodePossibleValues(list);
							contractMapping.setEb07(hippaSegment);
						} else if (costShareValueTypeCode
								.equals(DomainConstants.ZERO_TWO)) {
							// setting EB08
							if (!StringUtils
									.isBlank((benefitServiceCategoryUnitLimitValue))) {
								// if system equals Facets, then we need not convert
								// into decimals, as the response itself is in that
								// format.
								if (DomainConstants.FACETS.equals(system)) {
									String value = benefitServiceCategoryUnitLimitValue;
									if (value.startsWith("0.")) {
										value = formatString(value.substring(1, value.length()));
									}
									list.add(value);
								} else {
									try {
										percent = Float
												.parseFloat(benefitServiceCategoryUnitLimitValue);
										percent = percent / 100;
										String value = "";
										if (percent == 0) {
											value = "0";
										} else {
											value = formatString(String.valueOf(percent));
										}
										if (value.startsWith("0.")) {
											value = formatString(value.substring(1, value
													.length()));
										}
										list.add(value);
									} catch (NumberFormatException e) {
										log
												.debug("NumberFormatException Caught for : "
														+ benefitServiceCategoryUnitLimitValue);
										String value=formatString(benefitServiceCategoryUnitLimitValue);
										list
												.add(value);
									}
								}
							}
							hippaSegment.setHippaCodePossibleValues(list);
							contractMapping.setEb08(hippaSegment);
						} else if (costShareValueTypeCode
								.equals(DomainConstants.ZERO_THREE)) {
							// setting EB10
							if (!StringUtils
									.isBlank((benefitServiceCategoryUnitLimitValue))) {
								String value = formatString(benefitServiceCategoryUnitLimitValue);
								if ((value.trim().equals(""))) {
									value = "0";
								}
								list.add(value);

							}

							hippaSegment.setHippaCodePossibleValues(list);
							contractMapping.setEb10(hippaSegment);
							if (!StringUtils.isBlank(ben
											.getBenefitServiceCategoryUnitLimitTypeCode())) {
								// setting EB09
								hippaSegment = new HippaSegment();
								list = new ArrayList();
								list.add(ben
										.getBenefitServiceCategoryUnitLimitTypeCode());
								hippaSegment.setHippaCodePossibleValues(list);
								contractMapping.setEb09(hippaSegment);
							}
						}

					}
				}

				if (!StringUtils.isBlank(stdBenefit.getNetworkTypCd())) {
					// setting EB12
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					if(NETWORK_TYPE_CODE_W.equals(stdBenefit.getNetworkTypCd())){
						// [U20776] SSCR15637 - Removed Split logic
						list.add(NETWORK_TYPE_CODE_W);
					} else {
						list.add(stdBenefit.getNetworkTypCd());
					}
					hippaSegment.setHippaCodePossibleValues(list);
					contractMapping.setEb12(hippaSegment);
				}

				if (!StringUtils.isBlank(benefit.getEffectiveDate())) {
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(benefit.getEffectiveDate());
					hippaSegment.setHippaCodePossibleValues(list);
					contractMapping.setBenefitEffectiveStartDate(hippaSegment);
				}
				if (!StringUtils.isBlank(benefit.getTerminationDate())) {
					hippaSegment = new HippaSegment();
					list = new ArrayList();
					list.add(benefit.getTerminationDate());
					hippaSegment.setHippaCodePossibleValues(list);
					contractMapping.setBenefitEffectiveEndDate(hippaSegment);
				}
				mapping.setContractMapping(contractMapping);

				for (int k = 0; k < stdBenefit
						.getHealthCareServicesDeliveryArray().length; k++) {

					EBSResponseDocument.EBSResponse.
					Get27XBenefitAccumsResponse.DynamicData.Benefit.StandardizeBenefit.
					HealthCareServicesDelivery[] healthCareServicesDelivery = stdBenefit
							.getHealthCareServicesDeliveryArray();
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getCostShareValueTypeCode())) {
						// setting HSD01
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						list.add(healthCareServicesDelivery[k]
								.getCostShareValueTypeCode());
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd01(hippaSegment);
					}
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getBenefitServiceCategoryUnitLimitValue())) {
						// setting HSD02
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						Integer convertedValue = 0;
						try {
							convertedValue = Integer.parseInt(healthCareServicesDelivery[k].getBenefitServiceCategoryUnitLimitValue());
							list.add(String.valueOf(convertedValue));
						} catch (NumberFormatException e) {
							list.add(healthCareServicesDelivery[k]
									.getBenefitServiceCategoryUnitLimitValue());
						}

						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd02(hippaSegment);
					}
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getUnitBaseMeasurementCode())) {
						// setting HSD03
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						list.add(healthCareServicesDelivery[k]
								.getUnitBaseMeasurementCode());
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd03(hippaSegment);
					}
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getSampleSelectionModulusAmount())) {
						// setting HSD04
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						Integer convertedValue = 0;
						try {
							convertedValue = Integer.parseInt(healthCareServicesDelivery[k].getSampleSelectionModulusAmount());
							list.add(String.valueOf(convertedValue));
						} catch (NumberFormatException e) {
							list.add(healthCareServicesDelivery[k]
									.getSampleSelectionModulusAmount());
						}
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd04(hippaSegment);
					}
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getTimePeriodQualifierCode())) {
						// setting HSD05
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						list.add(healthCareServicesDelivery[k]
								.getTimePeriodQualifierCode());
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd05(hippaSegment);
					}
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getPeriodCount())) {
						// setting HSD06
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						Integer convertedValue = 0;
						try {
							convertedValue = Integer.parseInt(healthCareServicesDelivery[k].getPeriodCount());
							list.add(String.valueOf(convertedValue));
						}catch(NumberFormatException e){
						list
							.add(healthCareServicesDelivery[k]
									.getPeriodCount());
						}
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd06(hippaSegment);
					}
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getShipDeliveryCalendarPatternCode())) {
						// setting HSD07
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						list.add(healthCareServicesDelivery[k]
								.getShipDeliveryCalendarPatternCode());
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd07(hippaSegment);
					}
					if (!StringUtils.isBlank(healthCareServicesDelivery[k]
							.getShipDeliveryTimePatternCode())) {
						// setting HSD08
						hippaSegment = new HippaSegment();
						list = new ArrayList();
						list.add(healthCareServicesDelivery[k]
								.getShipDeliveryTimePatternCode());
						hippaSegment.setHippaCodePossibleValues(list);
						mapping.setHsd08(hippaSegment);
					}

				}
				// Setting the messages separated by tilda
				msgBuffer = new StringBuffer();
				//if the next note type contains the string "CONTSA", then it is appended with the prev msg
				for (int msg = 0; msg < stdBenefit.getFreeFormNoteTextArray().length; msg++) {
					String msgText = stdBenefit.getFreeFormNoteTextArray(msg).getNoteText();
					if (!StringUtils.isBlank(msgText)) {
						String noteTypeCode = stdBenefit.getFreeFormNoteTextArray(msg).getNoteTypeCode();
						if (!StringUtils.isBlank(noteTypeCode) && 
								(DomainConstants.MSG_CONTSA).equals(noteTypeCode)){
							msgBuffer.append(msgText);

						}else{
							if(0 == msgBuffer.length()){
								msgBuffer.append(DomainConstants.MSG
										+ msgText);
							}else{
								msgBuffer.append(DomainConstants.TILDA_CHAR)
								.append(DomainConstants.MSG)
								.append(msgText);
							}
						}
					}
				}
				if(0 != msgBuffer.length()){
					msgBuffer.append(DomainConstants.TILDA_CHAR);
				}
				mapping.setMessage(msgBuffer.toString());

				list = new ArrayList();
				if (stdBenefit.getDiagnosisPlaceOfService() != null) {
					EBSResponseDocument.EBSResponse.
					Get27XBenefitAccumsResponse.DynamicData.Benefit.StandardizeBenefit.
					DiagnosisPlaceOfService diagnosisPlaceOfService = stdBenefit
							.getDiagnosisPlaceOfService();
					if (!StringUtils.isBlank(diagnosisPlaceOfService
							.getDiagnosisPlaceofServiceCode())) {
						iii02 = new HippaSegment();
						list.add(diagnosisPlaceOfService
								.getDiagnosisPlaceofServiceCode());
						iii02.setHippaCodePossibleValues(list);
						mapping.setIi02(iii02);
					}
				}
				
				//Added to display NM1 Loop Message Segment
				
				list = new ArrayList();
				String nm1MessageSegment = "";
				for (int k = 0; k < stdBenefit
				.getBenefitRelatedEntityArray().length; k++) {

					BenefitRelatedEntity[] benefitRelatedEntityArray = stdBenefit
					.getBenefitRelatedEntityArray();
					if (!StringUtils.isBlank(benefitRelatedEntityArray[k].getLastName())) {
					  String nm1Segment = benefitRelatedEntityArray[k].getLastName();
					  if (!StringUtils.isEmpty(nm1Segment)) {
						  
						  if (DomainConstants.BLUE_DISTINCTION_PLUS_SIGN.equalsIgnoreCase(nm1Segment.trim())) {
							  nm1MessageSegment = DomainConstants.BLUE_DISTINCTION_PLUS;
							  break;
						  } else if (DomainConstants.BLUE_DISTINCTION.equalsIgnoreCase(nm1Segment.trim())) {
							  nm1MessageSegment = DomainConstants.BLUE_DISTINCTION;
							  break;
						  }
					  }
				   }
				}
				if (!StringUtils.isEmpty(nm1MessageSegment)) {
					nm1HipaaSegment = new HippaSegment();
					list.add(nm1MessageSegment);
					nm1HipaaSegment.setHippaCodePossibleValues(list);
					mapping.setNm1MessageSegment(nm1HipaaSegment);
				}
				 
				  
				log.debug("Setting mapping");
				mappingMap.put(Integer.valueOf(insertPos), mapping);

				// Setting Minor Headings
				log.debug("Setting MinorHeadings");
				minorHeadings = new MinorHeadingsVO();
				minor = dynamicData.getMinor();
				if (StringUtils.isBlank(minor.getBenefitDescriptionText())) {
					minorHeadings.setDescriptionText(minor
							.getBenefitDescriptionText());
				}
				minorHeadings.setMappings(mappingMap);
				minorHeadingsMap.put(Integer.valueOf(insertPos), minorHeadings);

				// Setting Major Headings
				log.debug("Setting MajorHeadings");
				majorHeadings = new MajorHeadingsVO();
				major = dynamicData.getMajor();
				majorHeadings.setDescriptionText(major
						.getBenefitDescriptionText());
				majorHeadings.setMinorHeadings(minorHeadingsMap);
				majorHeadingsMap.put(Integer.valueOf(insertPos), majorHeadings);
				insertPos++;
				// [U20776] SSCR15637
				/*
				 * if(!isNetworkTypeAsW){ break; }
				 */
			}
			log.debug("Setting Contract");
			contract.setMajorHeadings(majorHeadingsMap);
		}
		log.debug("Exiting process27xBenefitAccumResponse Method");
		return contract;
	}

	/*
	 * Input environment in method get27xHIPAABXRequest is not required, as the
	 * indicator will be 'T' for both test and production transactions via eBX.
	 * Removed as part of August Release Requirement T39 Keeping the method
	 * signature as it for future reference.
	 */
	public String get27xHIPAABXRequest(HIPAA270BXVO hipaa27xBX,
			String environment, String responseFormat, String senderID)
			throws EBXException {
		log.debug("Exiting get27xHIPAABXRequest Method");
		MemberInfoVO memberInfo = null;
		String firstName = "";
		String lastName = "";
		String birthDate = "";
		String alphaPrefix = null;
		String memberId = null;
		boolean isDependentInformation = false;
		String x12Request = null;
		String serviceTypeCode = null;
		String startDate = "";
		String endDate = "";
		Calendar c1 = Calendar.getInstance(); // today
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT_1);
		SimpleDateFormat formatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT);
		Date sDate = null;
		Date eDate = null;
		Date bDate = null;
		String request = "";
		try {
			if (hipaa27xBX != null) {
				if (!StringUtils.isBlank(hipaa27xBX.getServiceTypeCode())) {
					serviceTypeCode = hipaa27xBX.getServiceTypeCode();
				}
				if (!StringUtils.isBlank(hipaa27xBX.getStartDate())) {
					startDate = hipaa27xBX.getStartDate();
				}/*
				 * else if(!StringUtils.isBlank(hipaa27xBX.getEndDate())) {
				 * startDate = hipaa27xBX.getEndDate();}
				 */
				else {
					startDate = formatter.format(c1.getTime());
				}
				if (!StringUtils.isBlank(hipaa27xBX.getEndDate())) {
					endDate = hipaa27xBX.getEndDate();
				} else if (!StringUtils.isBlank(hipaa27xBX.getStartDate())) {
					endDate = startDate;
				} else {
					endDate = formatter.format(c1.getTime());
				}
				try {
					sDate = formatter.parse(startDate);
					eDate = formatter.parse(endDate);
				} catch (ParseException e) {
					log.debug("Caught Date ParseException");
				}
				if (hipaa27xBX.getMemberInfo() != null) {
					memberInfo = hipaa27xBX.getMemberInfo();
					if (!StringUtils.isBlank(memberInfo.getAlphaPrefix())) {
						alphaPrefix = memberInfo.getAlphaPrefix();
					}
					if (!StringUtils.isBlank(memberInfo.getMemberId())) {
						memberId = memberInfo.getMemberId();
					}
					if (!StringUtils.isBlank(memberInfo.getFirstName())) {
						firstName = memberInfo.getFirstName();
					}
					if (!StringUtils.isBlank(memberInfo.getLastName())) {
						lastName = memberInfo.getLastName();
					}
					if (!StringUtils.isBlank(memberInfo.getDateOfBirth())) {
						birthDate = memberInfo.getDateOfBirth();
						try {
							bDate = formatter.parse(birthDate);
						} catch (ParseException e) {
							log.debug("Caught Date ParseException");
						}
					}

					if (memberInfo.isDependentInformation() == true) {
						isDependentInformation = true;
					}
				}
			}

			if (StringUtils.isBlank(serviceTypeCode)) {
				serviceTypeCode = "30";
			}
			if (StringUtils.isBlank(alphaPrefix)
					|| StringUtils.isBlank(memberId)) {
				throw new NullPointerException(
						"Mandatory MemberInfo fields missing.");
			}
			if (StringUtils.isBlank(firstName) && StringUtils.isBlank(lastName)
					&& StringUtils.isBlank(birthDate)) {
				throw new NullPointerException(
						"Mandatory MemberInfo fields missing.");
			}
		} catch (NullPointerException e) {
			throw new EBXException("Mandatory Fields Missing", e,
					"Mandatory Fields Missing",
					"Please enter all the mandatory values");
		}
		c1.setTime(sDate);
		startDate = dateFormatter.format(c1.getTime()).toString();
		c1.setTime(eDate);
		endDate = dateFormatter.format(c1.getTime()).toString();
		if (null != bDate) {
			c1.setTime(bDate);
			birthDate = dateFormatter.format(c1.getTime()).toString();
		}

		// Removed as part of August Release Requirement T39
		/*
		 * if (DomainConstants.PRODUCTION.equals(environment)) { environment =
		 * DomainConstants.T; } else { environment = DomainConstants.T; }
		 */

		if (DomainConstants.RESPONSE_FORMAT_4010.equals(responseFormat)) {
			if (isDependentInformation) {
				log.info("Format selected : 4010-dep");
				request = DomainConstants.REQUEST_WITH_DEPENDENT_INFORMATION;
			} else {
				log.info("Format selected : 4010-withoutdep");
				request = DomainConstants.REQUEST_WITHOUT_DEPENDENT_INFORMATION;
			}
		} else {
			if (isDependentInformation) {
				log.info("Format selected : 5010-dep");
				if (birthDate == "") {
					log.info("Format selected : 5010-dep without date of birth");
					request = DomainConstants.REQUEST_WITH_DEPENDENT_INFORMATION_5010_WITHOUT_DOB;
				} else {
					log.info("Format selected : 5010-dep with date of birth");
					request = DomainConstants.REQUEST_WITH_DEPENDENT_INFORMATION_5010;
				}

			} else {
				log.info("Format selected : 5010-withoutdep");
				if (birthDate == "") {
					log.info("Format selected : 5010-withoutdep without date of birth");
					request = DomainConstants.REQUEST_WITHOUT_DEPENDENT_INFORMATION_5010_WITHOUT_DOB;
				} else {
					log.info("Format selected : 5010-withoutdep with date of birth");
					request = DomainConstants.REQUEST_WITHOUT_DEPENDENT_INFORMATION_5010;
				}
			}
		}
		x12Request = createX12Request(lastName, firstName, alphaPrefix,
				memberId, birthDate, serviceTypeCode, startDate, endDate,
				DomainConstants.T, request, senderID);
		log.debug("Exiting get27xHIPAABXRequest Method");
		return x12Request;

	}

	public String createX12Request(String lastName, String firstName,
			String alphaPrefix, String memberId, String birthDate,
			String serviceTypeCode, String startDate, String endDate,
			String environment, String request, String senderID) {

		log.debug("Entering createX12Request Method");

		ResourceBundle rb = ResourceBundle.getBundle(DomainConstants.X12,
				Locale.getDefault());

		String value = rb.getString(request);
		log.debug("Request from Property File is: " + value);
		Calendar c1 = Calendar.getInstance(); // today
		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT_1);
		String currentDate = dateFormatter.format(c1.getTime());
		value = value
				.replaceAll(DomainConstants.FIRSTNAME, firstName)
				.replaceAll(DomainConstants.LASTNAME, lastName)
				.replaceAll(DomainConstants.ALPHAPREFIX, alphaPrefix)
				.replaceAll(DomainConstants.MEMBERID, memberId)
				.replaceAll(DomainConstants.DATEOFBIRTH, birthDate)
				.replaceAll(DomainConstants.SERVICETYPECODE, serviceTypeCode)
				.replaceAll(DomainConstants.ENVIRONMENT, environment)
				.replaceAll(DomainConstants.STARTDATE, startDate)
				.replaceAll(DomainConstants.ENDDATE, endDate)
				.replaceAll(DomainConstants.CURRDATE, currentDate)
				.replace(DomainConstants.SENDER_ID,
						String.format("%-" + 15 + "s", senderID))
				.replace(DomainConstants.SRCLGCL, senderID);
		log.debug("Request is: " +ESAPI.encoder().encodeForHTML(value));
		log.debug("Exiting createX12Request Method");
		return value;
	}

	public String format27xHIPAABXResponse(String x12Response) {
		log.debug("Entering format27xHIPAABXResponse Method");

		StringBuffer formattedX12String = new StringBuffer();
		StringTokenizer st = new StringTokenizer(x12Response, "~", true);
		StringBuffer buffer = new StringBuffer();
		while (st.hasMoreTokens()) {
			String key = st.nextToken();
			if (key.equals("\r\n")) {
				break;
			}
			if (key.startsWith("\r\n")) {
				key = key.replaceAll("^\\s+", "");
			}
			if (key.startsWith(DomainConstants.GS)
					|| key.startsWith(DomainConstants.GE)) {
				buffer = new StringBuffer("     ").append(key);
				key = buffer.toString();
			} else if (key.startsWith(DomainConstants.ST)
					|| key.startsWith(DomainConstants.SE)) {
				buffer = new StringBuffer("          ").append(key);
				key = buffer.toString();
			} else if (key.startsWith(DomainConstants.HL)
					|| key.startsWith(DomainConstants.BHT)) {
				buffer = new StringBuffer("               ").append(key);
				key = buffer.toString();
			} else if (!key.startsWith(DomainConstants.ISA)
					&& !key.startsWith(DomainConstants.IEA)) {
				buffer = new StringBuffer("                    ").append(key);
				key = buffer.toString();
			}
			String val = st.nextToken();
			String separator = System.getProperty("line.separator");

			formattedX12String.append((key + val + separator));
		}

		log.debug("Entering format27xHIPAABXResponse Method");
		return formattedX12String.toString();

	}

	public String format27xHIPAABXResponse5010(String formattedx12Response) {
		log.debug("Entering format27xHIPAABXResponse5010 Method");

		StringBuffer buffer = new StringBuffer();
		String seg5010;
		String ebStr;
		String serviceTypeCode;
		int totTokents;
		int tokenCount;

		if ((formattedx12Response.indexOf("*W~") >= 0)
				|| (formattedx12Response.indexOf("{") > 107)) {
			StringTokenizer st = new StringTokenizer(
					formattedx12Response.trim(), "~", false);
			ArrayList segList = new ArrayList();
			while (st.hasMoreTokens()) {
				segList.add(st.nextToken());
			}

			for (int segPos = 0; segPos < segList.size(); segPos++) {
				seg5010 = (String) segList.get(segPos) + "~";
				// SSCR15637 - Removed the Split logic of 'W' as 'Y' & 'N' -
				// Start
				if ((seg5010.indexOf("EB*") >= 0)
						&& ((seg5010.indexOf("{") >= 0))) {
					if (seg5010.indexOf("{") >= 0) {
						String tempStr1=seg5010.substring(0,seg5010.indexOf("{"));							
						String beg=tempStr1.substring(0,tempStr1.lastIndexOf("*")+1);							
						String tempStr2 = seg5010.substring(seg5010.indexOf("{"));					
					int pos;
						String end;
						if (tempStr2.indexOf("*") >= 0) {
							end = tempStr2.substring(tempStr2.indexOf("*"));
							pos = seg5010.substring(beg.length()).indexOf("*");
						} else {
							end = tempStr2.substring(tempStr2.indexOf("~"));
							pos = seg5010.substring(beg.length()).indexOf("~");
						}
						String serviceTypeCodes = seg5010.substring(beg.length(),beg.length()+pos);					
						StringTokenizer st1 = new StringTokenizer(serviceTypeCodes, "{", false);
						totTokents = st1.countTokens();
						tokenCount = 0;
						while (st1.hasMoreTokens()) {
							tokenCount++;
							serviceTypeCode = st1.nextToken();
							ebStr = beg + serviceTypeCode + end;
							buffer.append(ebStr);
							if (tokenCount != totTokents) {
								for (int i=segPos+1; ((String)segList.get(i)).indexOf("MSG*")>=0 || 
								((String)segList.get(i)).indexOf("III*")>=0; i++) {										
									buffer.append(((String)segList.get(i))+"~");
								}
							}
						}
					} else {
						buffer.append(seg5010);
						for (int i=segPos+1; ((String)segList.get(i)).indexOf("MSG*")>=0 || 
						((String)segList.get(i)).indexOf("III*")>=0; i++) {							
							buffer.append(((String) segList.get(i)) + "~");
						}
						// SSCR15637 - Removed the Split logic of 'W' as 'Y' &
						// 'N' - End
					}
				} else {
					buffer.append(seg5010);
				}
			}

		} else {
			buffer.append(formattedx12Response);
		}
		log.debug("Entering format27xHIPAABXResponse5010 Method");
		return buffer.toString();
	}

	public void process27xBenefitAccumResponseForErrorValidation(
			List benefitAccumResponseList) throws EBXException, XmlException,
			Exception {

		ResourceBundle rb = ResourceBundle.getBundle(
				DomainConstants.TWENTY_SEVEN_X_REQUEST, Locale.getDefault());

		log.debug("Entering process27xBenefitAccumResponseForErrorValidation Method");
		// Initializing Variables

		genericEBStringList.clear();
		eBStringListForE024.clear();
		StringBuffer errorBuffer = null;
		if (null != benefitAccumResponseList) {
			for (int cnt = 0; cnt < benefitAccumResponseList.size(); cnt++) {
				String benefitAccumResponse = (String) benefitAccumResponseList
						.get(cnt);
				if (null != benefitAccumResponse) {
					DynamicData dynamicData = null;
					Benefit benefit = null;
					StandardizeBenefit stdBenefit = null;
					BenefitLimitInfo[] benefitLimitInfos = null;
					PayLoadDocument payLoadDoc = null;
					XmlOptions options = new XmlOptions();
					Map map = new HashMap();
					String error = "";
					try {
						// setting the name space in the response.
						log.debug("setting the namespace in the response");
						map.put("", DomainConstants.NAMESPACE_V4);
						options.setLoadSubstituteNamespaces(map);

						// Getting the PayLoadDocument
						payLoadDoc = PayLoadDocument.Factory.parse(
								benefitAccumResponse, options);
					} catch (XmlException e) {
						throw new XmlException(e.getMessage());
					} catch (Exception e) {
						throw new Exception(e.getMessage());
					}

					PayLoad payLoad = payLoadDoc.getPayLoad();
					EBSResponse ebsResponse = payLoad.getEBSResponse();
					EBSExceptions ebsException = payLoad.getEBSExceptions();

					errorBuffer = new StringBuffer();
					if (ebsException.getEBSExceptionArray() != null) {
						for (int e = 0; e < ebsException.getEBSExceptionArray().length; e++) {
							log.debug(""
									+ ebsException.getEBSExceptionArray(e)
											.getExceptionDetail());
							log.debug(""
									+ ebsException.getEBSExceptionArray(e)
											.getExceptionDetail());
							errorBuffer.append("Error Code: ").append(
									ebsException.getEBSExceptionArray(e)
											.getExceptionCode());
							errorBuffer
									.append(", ")
									.append("Error Detail: ")
									.append(ebsException
											.getEBSExceptionArray(e)
											.getExceptionDetail()
											+ ", ");
						}

						if (!StringUtils.isBlank(error)) {
							throw new EBXException(error);
						}
					}
					Get27XBenefitAccumsResponse get27XBenefitAccumsResponse = ebsResponse
							.getGet27XBenefitAccumsResponse();

					DynamicData[] dynamicDatas = get27XBenefitAccumsResponse
							.getDynamicDataArray();
					if (dynamicDatas != null) {
						List eb03ErrorValidationlist = getEB03ListForErrorValidation();
						for (int i = 0; i < dynamicDatas.length; i++) {
							String EB01 = "", EB02 = "", EB03 = "", EB04 = "", EB05 = "",
									EB06 = "", EB07 = "", EB08 = "", EB09 = "", EB10 = "", 
									EB11 = "", EB12 = "", msgForE024 = "",III = "", msg = "",
									HSD01 = "", HSD02 = "", HSD03 = "", HSD04 = "", HSD05 = "",
									HSD06 = "", HSD07 = "", HSD08 = "";
							String variableFormat = "";
							StringBuffer msgBuffer = new StringBuffer();
							StringBuffer message = new StringBuffer();
							// Getting the benefit and stdBenefit from
							// DynamicData
							dynamicData = dynamicDatas[i];
							benefit = dynamicData.getBenefit();
							stdBenefit = benefit.getStandardizeBenefit();
							benefitLimitInfos = benefit
									.getBenefitLimitInfoArray();
							// Setting Eligibility Benefit Information from the
							// Response
							if (!StringUtils.isBlank(stdBenefit
									.getCostShareRuleCategoryCode())) {
								// setting EB01
								EB01 = stdBenefit
										.getCostShareRuleCategoryCode();
							}
							if (!StringUtils.isBlank(stdBenefit
									.getCostShareRuleApplicationLevelCode())) {
								// setting EB02
								EB02 = stdBenefit
										.getCostShareRuleApplicationLevelCode();
							}
							if (!StringUtils.isBlank(benefit
									.getBenefitServiceCategoryTypeCode())) {
								// setting EB03
								EB03 = benefit
										.getBenefitServiceCategoryTypeCode();
							}
							if (null != get27XBenefitAccumsResponse
									.getStaticData()
									&& !StringUtils
											.isBlank(get27XBenefitAccumsResponse
													.getStaticData()
													.getHealthcareArrangementCode())) {
								// setting EB04
								EB04 = get27XBenefitAccumsResponse
										.getStaticData()
										.getHealthcareArrangementCode();
							}
							if (null != get27XBenefitAccumsResponse
									.getStaticData()
									&& !StringUtils
											.isBlank((get27XBenefitAccumsResponse
													.getStaticData()
													.getProductDescriptionText()))) {
								// Setting EB05
								EB05 = get27XBenefitAccumsResponse
										.getStaticData()
										.getProductDescriptionText();
							}
							if (!StringUtils.isBlank(stdBenefit
									.getTimePeriodQualifierCode())) {
								// Setting EB06
								EB06 = stdBenefit.getTimePeriodQualifierCode();
							}

							for (BenefitLimitInfo ben : benefitLimitInfos) {
								if (!StringUtils.isBlank(ben
										.getCostShareValueTypeCode())) {

									String costShareValueTypeCode = ben
											.getCostShareValueTypeCode().trim();
									variableFormat = costShareValueTypeCode;
									String benefitServiceCategoryUnitLimitValue = ben
											.getBenefitServiceCategoryUnitLimitValue();
									if ((costShareValueTypeCode
											.equals(DomainConstants.ZERO_ONE))
											|| (costShareValueTypeCode
													.equals(DomainConstants.ZERO_FIVE))) {
										// setting EB07
										if (!StringUtils
												.isBlank((benefitServiceCategoryUnitLimitValue))) {
											String value = formatString(benefitServiceCategoryUnitLimitValue);
											if ((value.trim().equals(""))) {
												value = "0";
											}
											EB07 = value;

										}

										// EB07 =
										// benefitServiceCategoryUnitLimitValue;
									} else if (costShareValueTypeCode
											.equals(DomainConstants.ZERO_TWO)) {
										// setting EB08
										String value = formatString(benefitServiceCategoryUnitLimitValue);
										if ((value.trim().equals(""))) {
											value = "0";
										}
										EB08 = value;
									} else if (costShareValueTypeCode
											.equals(DomainConstants.ZERO_THREE)) {
										if (!StringUtils
												.isBlank((benefitServiceCategoryUnitLimitValue))) {
											String value = formatString(benefitServiceCategoryUnitLimitValue);
											if ((value.trim().equals(""))) {
												value = "0";
											}
											EB10 = value;
										}
										// EB10 =
										// benefitServiceCategoryUnitLimitValue;
									}

								}

								if (!StringUtils
										.isBlank(ben
												.getBenefitServiceCategoryUnitLimitTypeCode())) {
									// setting EB09
									EB09 = ben
											.getBenefitServiceCategoryUnitLimitTypeCode();
								}
							}

							if (!StringUtils.isBlank(stdBenefit
									.getAuthrznTypCd())) {
								// setting EB11
								try {
									EB11 = rb.getString(WGS.concat(EB11_STRING)
											+ stdBenefit.getAuthrznTypCd());
								} catch (MissingResourceException e) {
									EB11 = stdBenefit.getAuthrznTypCd();
								}
							}
							if (!StringUtils.isBlank(stdBenefit
									.getNetworkTypCd())) {
								// setting EB12
								EB12 = stdBenefit.getNetworkTypCd();
							}

							for (int m = 0; m < stdBenefit
									.getFreeFormNoteTextArray().length; m++) {
								if (!StringUtils.isBlank(stdBenefit
										.getFreeFormNoteTextArray(m)
										.getNoteText())) {
									// SSCR 14181 - messages of note type code
									// ‘ACMDESC1’
									// will be ignored from the EB string
									// matching logic of E024
									if (!"ACMDESC1".equals(stdBenefit
											.getFreeFormNoteTextArray(m)
											.getNoteTypeCode())) {
										msgBuffer.append(stdBenefit
												.getFreeFormNoteTextArray(m)
												.getNoteText());
										msgBuffer.append(",");
									} else
										message.append(stdBenefit
												.getFreeFormNoteTextArray(m)
												.getNoteText());
									/*
									 * MSG = MSG + stdBenefit
									 * .getFreeFormNoteTextArray(m)
									 * .getNoteText() + ",";
									 */
								}

							}
							msgForE024 = msgBuffer.toString();
							msg = message.toString();

							if (stdBenefit.getDiagnosisPlaceOfService() != null) {
								EBSResponseDocument.EBSResponse.Get27XBenefitAccumsResponse.DynamicData.Benefit.StandardizeBenefit.DiagnosisPlaceOfService diagnosisPlaceOfService = stdBenefit
										.getDiagnosisPlaceOfService();
								if (!StringUtils
										.isBlank(diagnosisPlaceOfService
												.getDiagnosisPlaceofServiceCode())) {
									III = diagnosisPlaceOfService
											.getDiagnosisPlaceofServiceCode();
								}
							}
							
							//BXNI December Release Changes: Additional HippaSegments included
							
							//Object added as part of BXNI December Release
							for (int k = 0; k < stdBenefit
									.getHealthCareServicesDeliveryArray().length; k++) {

								EBSResponseDocument.EBSResponse.Get27XBenefitAccumsResponse.DynamicData.Benefit.StandardizeBenefit.HealthCareServicesDelivery[] healthCareServicesDelivery = stdBenefit
										.getHealthCareServicesDeliveryArray();
								
								if (!StringUtils.isBlank(healthCareServicesDelivery[k]
										.getCostShareValueTypeCode())) {
									// setting HSD01
									HSD01 = healthCareServicesDelivery[k]
											.getCostShareValueTypeCode();
								}
								if (!StringUtils.isBlank(healthCareServicesDelivery[k]
										.getBenefitServiceCategoryUnitLimitValue())) {
									// setting HSD02
									HSD02 = healthCareServicesDelivery[k].getBenefitServiceCategoryUnitLimitValue();
								}
								if (!StringUtils.isBlank(healthCareServicesDelivery[k]
										.getUnitBaseMeasurementCode())) {
									// setting HSD03
									HSD03 = healthCareServicesDelivery[k]
											.getUnitBaseMeasurementCode();
								}
								if (!StringUtils.isBlank(healthCareServicesDelivery[k]
										.getSampleSelectionModulusAmount())) {
									// setting HSD04
									HSD04 = healthCareServicesDelivery[k]
											.getSampleSelectionModulusAmount();
								}
								if (!StringUtils.isBlank(healthCareServicesDelivery[k]
										.getTimePeriodQualifierCode())) {
									// setting HSD05
									HSD05 = healthCareServicesDelivery[k]
											.getTimePeriodQualifierCode();
								}
								if (!StringUtils.isBlank(healthCareServicesDelivery[k]
										. getPeriodCount())) {
									// setting HSD06
									HSD06 = healthCareServicesDelivery[k]
											. getPeriodCount();
								}
								if (!StringUtils.isBlank(healthCareServicesDelivery[k]
										. getShipDeliveryCalendarPatternCode())) {
									// setting HSD07
									HSD07 = healthCareServicesDelivery[k]
											. getShipDeliveryCalendarPatternCode();
								}
								if (!StringUtils.isBlank(healthCareServicesDelivery[k]
										.getShipDeliveryTimePatternCode())) {
									// setting HSD08
									HSD08 = healthCareServicesDelivery[k]
											.getShipDeliveryTimePatternCode();
								}
							}
							
							// For E024
							if (eb03ErrorValidationlist.contains(EB03)
									|| EB03.equals("")) {
								if ("W".equals(EB12)) {
									eBStringListForE024.add(EB01
											+ DomainConstants.STAR_CHAR + EB02
											+ DomainConstants.STAR_CHAR + EB03
											+ DomainConstants.STAR_CHAR + EB04
											+ DomainConstants.STAR_CHAR + EB05
											+ DomainConstants.STAR_CHAR + EB06
											+ DomainConstants.STAR_CHAR + EB09
											+ DomainConstants.STAR_CHAR + "Y"
											+ DomainConstants.STAR_CHAR
											+ msgForE024
											+ DomainConstants.STAR_CHAR + III
											+ DomainConstants.STAR_CHAR + HSD01
											+ DomainConstants.STAR_CHAR + HSD03
											+ DomainConstants.STAR_CHAR + HSD05
											+ DomainConstants.STAR_CHAR + HSD07
											+ "|" + EB07
											+ DomainConstants.STAR_CHAR + EB08
											+ DomainConstants.STAR_CHAR + EB10
											+ DomainConstants.STAR_CHAR + EB11
											+ DomainConstants.STAR_CHAR + HSD02
											+ DomainConstants.STAR_CHAR + HSD03
											+ DomainConstants.STAR_CHAR + HSD06
											+ DomainConstants.STAR_CHAR + HSD08);
									eBStringListForE024.add(EB01
											+ DomainConstants.STAR_CHAR + EB02
											+ DomainConstants.STAR_CHAR + EB03
											+ DomainConstants.STAR_CHAR + EB04
											+ DomainConstants.STAR_CHAR + EB05
											+ DomainConstants.STAR_CHAR + EB06
											+ DomainConstants.STAR_CHAR + EB09
											+ DomainConstants.STAR_CHAR + "N"
											+ DomainConstants.STAR_CHAR
											+ msgForE024
											+ DomainConstants.STAR_CHAR + III
											+ DomainConstants.STAR_CHAR + HSD01
											+ DomainConstants.STAR_CHAR + HSD03
											+ DomainConstants.STAR_CHAR + HSD05
											+ DomainConstants.STAR_CHAR + HSD07
											+ "|" + EB07
											+ DomainConstants.STAR_CHAR + EB08
											+ DomainConstants.STAR_CHAR + EB10
											+ DomainConstants.STAR_CHAR + EB11
											+ DomainConstants.STAR_CHAR + HSD02
											+ DomainConstants.STAR_CHAR + HSD03
											+ DomainConstants.STAR_CHAR + HSD06
											+ DomainConstants.STAR_CHAR + HSD08);
								} else {
									eBStringListForE024.add(EB01
											+ DomainConstants.STAR_CHAR + EB02
											+ DomainConstants.STAR_CHAR + EB03
											+ DomainConstants.STAR_CHAR + EB04
											+ DomainConstants.STAR_CHAR + EB05
											+ DomainConstants.STAR_CHAR + EB06
											+ DomainConstants.STAR_CHAR + EB09
											+ DomainConstants.STAR_CHAR + EB12
											+ DomainConstants.STAR_CHAR
											+ msgForE024
											+ DomainConstants.STAR_CHAR + III
											+ DomainConstants.STAR_CHAR + HSD01
											+ DomainConstants.STAR_CHAR + HSD03
											+ DomainConstants.STAR_CHAR + HSD05
											+ DomainConstants.STAR_CHAR + HSD07
											+ "|" + EB07
											+ DomainConstants.STAR_CHAR + EB08
											+ DomainConstants.STAR_CHAR + EB10
											+ DomainConstants.STAR_CHAR + EB11
											+ DomainConstants.STAR_CHAR + HSD02
											+ DomainConstants.STAR_CHAR + HSD04
											+ DomainConstants.STAR_CHAR + HSD06
											+ DomainConstants.STAR_CHAR + HSD08);
								}
							}
							// For generic error codes -- This was earlier
							// implemented for E028 alone, but it is retained
							// because this can be reused for other error
							// codes as well.
							if ("W".equals(EB12)) {
								genericEBStringList.add(DomainConstants.EB
										+ EB01 + DomainConstants.STAR_CHAR
										+ EB02 + DomainConstants.STAR_CHAR
										+ EB03 + DomainConstants.STAR_CHAR
										+ EB04 + DomainConstants.STAR_CHAR
										+ EB05 + DomainConstants.STAR_CHAR
										+ EB06 + DomainConstants.STAR_CHAR
										+ EB07 + DomainConstants.STAR_CHAR
										+ EB08 + DomainConstants.STAR_CHAR
										+ EB09 + DomainConstants.STAR_CHAR
										+ EB10 + DomainConstants.STAR_CHAR
										
										+ "Y" + DomainConstants.STAR_CHAR
										+ variableFormat
										+ DomainConstants.STAR_CHAR
										+ msgForE024
										+ DomainConstants.STAR_CHAR + III);
								genericEBStringList.add(DomainConstants.EB
										+ EB01 + DomainConstants.STAR_CHAR
										+ EB02 + DomainConstants.STAR_CHAR
										+ EB03 + DomainConstants.STAR_CHAR
										+ EB04 + DomainConstants.STAR_CHAR
										+ EB05 + DomainConstants.STAR_CHAR
										+ EB06 + DomainConstants.STAR_CHAR
										+ EB07 + DomainConstants.STAR_CHAR
										+ EB08 + DomainConstants.STAR_CHAR
										+ EB09 + DomainConstants.STAR_CHAR
										+ EB10 + DomainConstants.STAR_CHAR
										+ EB11 + DomainConstants.STAR_CHAR
										+ "N" + DomainConstants.STAR_CHAR
										+ variableFormat
										+ DomainConstants.STAR_CHAR
										+ msgForE024
										+ DomainConstants.STAR_CHAR + III);
							} else {
								genericEBStringList.add(DomainConstants.EB
										+ EB01 + DomainConstants.STAR_CHAR
										+ EB02 + DomainConstants.STAR_CHAR
										+ EB03 + DomainConstants.STAR_CHAR
										+ EB04 + DomainConstants.STAR_CHAR
										+ EB05 + DomainConstants.STAR_CHAR
										+ EB06 + DomainConstants.STAR_CHAR
										+ EB07 + DomainConstants.STAR_CHAR
										+ EB08 + DomainConstants.STAR_CHAR
										+ EB09 + DomainConstants.STAR_CHAR
										+ EB10 + DomainConstants.STAR_CHAR
										+ EB11 + DomainConstants.STAR_CHAR
										+ EB12 + DomainConstants.STAR_CHAR
										+ variableFormat
										+ DomainConstants.STAR_CHAR
										+ msgForE024
										+ DomainConstants.STAR_CHAR + III);
							}
						}
					}
				}
			}
		}
		log.debug("Exiting process27xBenefitAccumResponseForErrorValidation Method");
	}

	/**
	 * The method provides the EB03 list for error validation
	 * 
	 * @return
	 */
	private List getEB03ListForErrorValidation() {
		List eb03ListForErrorValidation = SimulationResourceBundle
				.getResourceBundle(
						DomainConstants.EB03LIST_FOR_ERROR_VALIDATION,
						DomainConstants.PROPERTY_FILE_NAME);

		return eb03ListForErrorValidation;
	}

	/**
	 * @param benefitAccumResponse
	 *            containing the Response XML with values of contract
	 * @return void
	 * @throws EBXException
	 *             , XmlException,Exception
	 */
	public ContractVO checkForMQExceptionAndHeader(String benefitAccumResponse,
			String contractSystem) throws EBXException, XmlException {
		log.debug("Entering process27xBenefitAccumResponse Method");

		Map map = new HashMap();
		XmlOptions options = new XmlOptions();
		PayLoadDocument payLoadDoc = null;
		// setting the name space in the response.
		log.debug("setting the namespace in the response");
		map.put("", DomainConstants.NAMESPACE_V4);
		options.setLoadSubstituteNamespaces(map);
		payLoadDoc = PayLoadDocument.Factory.parse(benefitAccumResponse,
				options);
		PayLoadDocument.PayLoad payLoad = payLoadDoc.getPayLoad();

		// Change as part of Defect fix done on Pagination - Start
		EBSHeaderDocument.EBSHeader ebsHeader = payLoad.getEBSHeader();
		ContractVO contract = new ContractVO();
		contract.setMoreDataForPagination(ebsHeader.getMoreData());
		contract.setNextPageSize(ebsHeader.getSbiAdditionalPageKeys());
		// Change as part of Defect fix done on Pagination - End

		EBSExceptions ebsException = payLoad
				.getEBSExceptions();
		StringBuffer error = new StringBuffer();

		if (ebsException.getEBSExceptionArray() != null) {

			int exceptionCount = ebsException.getEBSExceptionArray().length;
			if (exceptionCount > 0) {
				error.append("Error Validation against 27x static response failed, as Exception occurred (");
			}
			for (int e = 0; e < exceptionCount; e++) {
				log.debug(""
						+ ebsException.getEBSExceptionArray(e)
								.getExceptionDetail());
				error.append(ebsException.getEBSExceptionArray(e)
						.getExceptionCode());
				error.append(":");
				error.append(ebsException.getEBSExceptionArray(e)
						.getExceptionDetail());
				if (exceptionCount > 1 && e < exceptionCount)
					error.append(", ");
			}
			if (exceptionCount > 0) {
				error.append(")");
			}
			if (null != error) {
				if (!StringUtils.isBlank(error.toString())) {
					throw new EBXException(error.toString());
				}
			}
		}
		return contract;
	}

	public List getGenericEBStringList() {
		return genericEBStringList;
	}

	public void setGenericEBStringList(List genericEBStringList) {
		this.genericEBStringList = genericEBStringList;
	}

	public List getEBStringListForE024() {
		return eBStringListForE024;
	}

	public void setEBStringListForE024(List stringListForE024) {
		eBStringListForE024 = stringListForE024;
	}

	public List<Loop2110> extractLoop2110C(Interchange interchange) {
		List<Loop2110> searchingList = null;

		Loop2100 loop2100 = extractLoop2100(interchange, "SUBSCRIBER");

		if (null != loop2100 && null != loop2100.getLoop2110()
				&& !loop2100.getLoop2110().isEmpty()) {
			searchingList = loop2100.getLoop2110();
		}
		return searchingList;
	}

	public List<Loop2110> extractLoop2110D(Interchange interchange) {
		List<Loop2110> searchingList = null;

		Loop2100 loop2100 = extractLoop2100(interchange, "DEPENDENT");

		if (null != loop2100 && null != loop2100.getLoop2110()
				&& !loop2100.getLoop2110().isEmpty()) {
			searchingList = loop2100.getLoop2110();
		}
		return searchingList;
	}

	/*
	 * Method checks for AAA in all the 2000 and 2100 loops. BXNI Nov Bug Fix
	 */
	public List<AAA> extractAllAAACodes(Interchange interchange) {
        List<AAA> aaaList = new ArrayList<AAA>();
        if (null != interchange.getFunctionGroup() &&  
        		!interchange.getFunctionGroup().isEmpty()) {
        	FunctionGroup functionGroup = interchange.getFunctionGroup().get(0);
        	if (null != functionGroup.getTransaction() && !functionGroup.getTransaction().isEmpty()) {
        		Transaction transaction = functionGroup.getTransaction().get(0);
        		if (null != transaction.getLoop2000() && !transaction.getLoop2000().isEmpty()) {
        			List<Loop2000> loop2000List = transaction.getLoop2000();
        			for (Loop2000 loop2000 : loop2000List) {
        				if (null != loop2000 && null != loop2000.getAaa() && !loop2000.getAaa().isEmpty()) {
        					aaaList.addAll(loop2000.getAaa());
        				}
        				if (null != loop2000 && null != loop2000.getLoop2100()){
        					List<Loop2100> loop2100List = loop2000.getLoop2100();
        					for(Loop2100 loop2100 : loop2100List){
        						if (null != loop2100 && null != loop2100.getAaa() && !loop2100.getAaa().isEmpty()) {
        							aaaList.addAll(loop2100.getAaa());
        						}
        					}
        				}
        			}
        		}
        	}
        }
        return aaaList;
  }


	public Loop2100 extractLoop2100(Interchange interchange,
			String type) {
		Loop2100 loop2100 = null;
		if (null != interchange.getFunctionGroup() &&  
				!interchange.getFunctionGroup().isEmpty()) {
			FunctionGroup functionGroup = interchange.getFunctionGroup().get(0);
			if (null != functionGroup.getTransaction() && !functionGroup.getTransaction().isEmpty()) {
				Transaction transaction = functionGroup.getTransaction().get(0);
				if (null != transaction.getLoop2000() && !transaction.getLoop2000().isEmpty()) {
					List<Loop2000> loop2000 = transaction.getLoop2000();
					if (null != loop2000 && !loop2000.isEmpty()) {
						if("SUBSCRIBER".equals(type) 
								&& loop2000.size() >= 3 
								&& null != loop2000.get(2).getLoop2100()) {
							loop2100 = loop2000.get(2).getLoop2100().get(0);
						}
						if("DEPENDENT".equals(type)
								&& loop2000.size() >= 4 
								&& null != loop2000.get(3).getLoop2100()) {
							loop2100 = loop2000.get(3).getLoop2100().get(0);
						}
					}
				}
			}
		}
		return loop2100;
	}
	public String identifyResponseType(Interchange interchange) {
		String responseType = null;
		if (null != interchange && null != interchange.getFunctionGroup() &&  
				!interchange.getFunctionGroup().isEmpty()) {
			FunctionGroup functionGroup = interchange.getFunctionGroup().get(0);
			if (null != functionGroup.getTransaction() && !functionGroup.getTransaction().isEmpty()) {
				Transaction transaction = functionGroup.getTransaction().get(0);
				if (null != transaction.getLoop2000() && !transaction.getLoop2000().isEmpty()) {
					if (transaction.getLoop2000().size() == 3) {
						responseType = "SUBSCRIBER";
					}
					if (transaction.getLoop2000().size() == 4) {
						responseType = "DEPENDENT";
					}
				}
			}
		}
		return responseType;
	}
}
