/*
 * <LGContractInfoRepositoryImpl.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.simulation.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.owasp.esapi.ESAPI;
import javax.sql.DataSource;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.util.MappingUtil;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorExclusionVO;
import com.wellpoint.ets.ebx.simulation.util.ErrorValidationHelper;
import com.wellpoint.ets.ebx.simulation.util.SimulationResourceBundle;
import com.wellpoint.ets.ebx.simulation.vo.ContractDataObjectVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractMappingVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.vo.HIPAA270BXVO;
import com.wellpoint.ets.ebx.simulation.vo.MajorHeadingsVO;
import com.wellpoint.ets.ebx.simulation.vo.MinorHeadingsVO;

/**
 * @author UST-GLOBAL
 * 
 *         Repository class for WPD - LG system. This class will create the
 *         object structure hierarchy with the values from the DB. The
 *         informations will contain mapping details for coded variables.
 * 
 */
public class LGContractInfoRepositoryImpl implements ContractInfoRepository {

	private DataSource dataSource;

	private String lgContractMappingInfoQuery;
	private String lgGetContractVersions;
	private String lgGetContractTestVersions;
	private String lgContractTestMappingInfoQuery;

	private Category log;


	private static Logger logger = Logger
	.getLogger(LGContractInfoRepositoryImpl.class);

	/**
	 * This method is used for creating the contract object with the contract
	 * information along with mapping details obtained from database for LG
	 * system.
	 * 
	 * @param ContractVO
	 * @return contractVOList
	 * 
	 */
	public List getContractInfo(ContractVO contract, boolean eBxReportFlag)
	throws EBXException, Exception {

		List contractVOList = new ArrayList();
		SimpleDateFormat dateFormat = null;
		dateFormat = new SimpleDateFormat(DomainConstants.DATE_FORMAT);
		java.sql.Date startDate = null;
		//System.out.println("Date in consideration is : "+contract.getEffectiveDate());
		logger.info("Date in consideration is : "+ESAPI.encoder().encodeForHTML(contract.getEffectiveDate()));
		try {
			startDate = new java.sql.Date(dateFormat.parse(
					contract.getEffectiveDate()).getTime());
		} catch (ParseException e) {
			throw new EBXException(DomainConstants.DATE_FORMAT_EXCEPTION);
		}


		/**
		 * Input Parameters for the query.
		 */
		Object[] inputParams = new Object[] { contract.getContractId(),
				startDate };
		long lgGetContractVersionsStartTime = System.currentTimeMillis();
		/***Modified for BXNI requirements T6.1.1.2.WGSSTAR.eBX.6**/
		List versions = null;
		List contractDataList = null;
		/*** Executing the LG query */
		if(contract.isProductionFlag()){
			/** to check whether the given contractID and date is valid**/
			LGGetContractVersions lgGetContractVersions = new LGGetContractVersions(dataSource);
			versions = lgGetContractVersions.execute(inputParams);
			checkMatchFound(versions);
			/*** Getting data source connection for Mapping information*/
			long lgContractMappingInfoQueryStartTime = System.currentTimeMillis();
			LGContractMappingInfoQuery lgContractMappingInfoQuery = new LGContractMappingInfoQuery(
					dataSource);
			
			contractDataList = (ArrayList) lgContractMappingInfoQuery
															.execute(new Object[] { contract.getContractId(), startDate,
																				contract.getContractId(), startDate });
			long lgContractMappingInfoQueryEndTime = System.currentTimeMillis();
			logger
			.info("Time taken for LGContractMappingInfoQuery = "
					+ (lgContractMappingInfoQueryEndTime - lgContractMappingInfoQueryStartTime));
		}else{
			//ADD version query FOR TEST
			LGGetContractTestVersions lgGetContractTestVersions = new LGGetContractTestVersions(dataSource);
			versions = lgGetContractTestVersions.execute(inputParams); //get from test tables
			checkMatchFound(versions);
			//ADD Mapping query FOR TEST
			LGContractTestMappingInfoQuery lgContractTestMappingInfoQuery = new LGContractTestMappingInfoQuery(dataSource);
			contractDataList = (ArrayList) lgContractTestMappingInfoQuery.execute(new Object[] { contract.getContractId(), startDate,
															contract.getContractId(), startDate });//get from test tables
			
		}
		/***Ends Modification for BXNI requirements T6.1.1.2.WGSSTAR.eBX.6**/
		
		long lgGetContractVersionsEndTime = System.currentTimeMillis();
		logger
		.info("Time taken for LGGetContractVersions = "
				+ (lgGetContractVersionsEndTime - lgGetContractVersionsStartTime));

		/*int rowCount = ((Integer) versions.get(0)).intValue();
		if (rowCount == 0) {
			throw new EBXException(DomainConstants.NO_MATCH_FOUND);
		}*/

		/**
		 * Getting datasource connection
		 */
		/*LGContractMappingInfoQuery lgContractMappingInfoQuery = new LGContractMappingInfoQuery(
				dataSource);*/
		/**
		 * Executing the LG query
		 */

		//long lgContractMappingInfoQueryStartTime = System.currentTimeMillis();

		/*List contractDataList = (ArrayList) lgContractMappingInfoQuery
		.execute(new Object[] { contract.getContractId(), startDate,
				contract.getContractId(), startDate });*/

		//long lgContractMappingInfoQueryEndTime = System.currentTimeMillis();
		/*logger
		.info("Time taken for LGContractMappingInfoQuery = "
				+ (lgContractMappingInfoQueryEndTime - lgContractMappingInfoQueryStartTime));
*/
		/**
		 * Creating the contract object with contract information obtained from
		 * database
		 */
		ContractVO contractDataObject = createContractObject(contractDataList);
		/**
		 * Setting contract details
		 */
		contractDataObject.setContractId(contract.getContractId());
		contractDataObject.setSystem(contract.getSystem());
				
		SimpleDateFormat formatter = new SimpleDateFormat(
				DomainConstants.DATE_FORMAT_EXPIRY);
		String expiryDate = null;
		if (null != contractDataObject.getExpiryDate()) {
			Date date = formatter.parse(contractDataObject.getExpiryDate());
			expiryDate = dateFormat.format(date);
		}
		String effectDate = null;
		if (null != contractDataObject.getEffectiveDate()) {
			Date date = formatter.parse(contractDataObject.getEffectiveDate());
			effectDate = dateFormat.format(date);
		}
		

		if (!eBxReportFlag) {
			long exclusionStartTime = System.currentTimeMillis();
			List exclusionList = null;
			/**
			 * Input Parameters for the LGContractErrorExclusionQuery.
			 */
			String lgExclusionsQuery = getLGContractExclusionQuery(
					contractDataObject.getContractId(), contractDataObject
							.getProductCode());
			logger.info("LG COntract fetch query" +ESAPI.encoder().encodeForHTML(lgExclusionsQuery));
			LGContractErrorExclusionQuery lgContractErrorExclusionQuery = new LGContractErrorExclusionQuery(
					dataSource, lgExclusionsQuery);
			exclusionList = lgContractErrorExclusionQuery.execute();
			contractDataObject.setErrorExclusionVOList(exclusionList);
			long exclusionEndTime = System.currentTimeMillis();
			logger.info("Time taken for LGContractErrorExclusionQuery = "+ (exclusionEndTime - exclusionStartTime));
		}
		

		contractDataObject.setExpiryDate(expiryDate);
		contractDataObject.setEffectiveDate(effectDate);
		
		
		
		contractVOList.add(contractDataObject);
		return contractVOList;
	}
	
	
	private void checkMatchFound(List versions)throws EBXException{
		int rowCount = ((Integer) versions.get(0)).intValue();
		if (rowCount == 0) {
			throw new EBXException(DomainConstants.NO_MATCH_FOUND);
		}
	}

	/**
	 * The method will create the object structure hierarchy with the values
	 * from the DB
	 * 
	 * @param contractDataList
	 * @return
	 */
	private ContractVO createContractObject(List contractDataList) {
		ContractVO contract = new ContractVO();
		MajorHeadingsVO majorHeadings = null;
		Map majorHeadingsMap = new HashMap();
		Set accumHippaSegmentList= new HashSet();
		Map variableMap = new HashMap();
		Map minorHeadingsMap = null;
		Map mappingsMap = null;
		Map mappingsMajorExists = null;
		Map mappingsMinorExists = null;

		Iterator resulSetIterator = contractDataList.iterator();
		while (resulSetIterator.hasNext()) {
			ContractDataObjectVO contractDataObject = (ContractDataObjectVO) resulSetIterator
			.next();
			/**
			 * setting accum value list for LG
			 */
			if (null!=contractDataObject
					.getHippaSegment()&&DomainConstants.ACCUM_NAME.equals(contractDataObject
					.getHippaSegment())) {
				accumHippaSegmentList.add(contractDataObject.getHippaValue());
			}
			/**
			 * variableMap to check the condition for HMO - September release.
			 */
			if (null != contractDataObject.getVariableId()
					&& !DomainConstants.EMPTY.equals(contractDataObject
							.getVariableId())) {
				if (!variableMap.containsKey(contractDataObject.getVariableId())) {
					String variableValue = contractDataObject.getVariableValue();
					variableMap.put(contractDataObject.getVariableId(),	variableValue);
				}
			}
			contract.setContractId(contractDataObject.getContractId());
			/**
        	 * Sets the corporate plan to ContractVO - September release (HMO logic)
        	 */
        	if (null != contractDataObject.getCorporatePlan()) {
        		contract.setCorporatePlan(contractDataObject.getCorporatePlan());
        	}
        	
        	
        	/**
        	 * Sets the benefit structure to ContractVO - September release (HMO logic)
        	 */
        	if (null != contractDataObject.getBenefitStructure()) {
        		contract.setBenefitStructure(contractDataObject.getBenefitStructure());
        	}
        	//Setting group name for contract -- SSCR 16331 Nov Release
        	if (null != contractDataObject.getGroupName()) {
        		contract.setGroupName(contractDataObject.getGroupName());
        	}
        	
			
			/**
			 * setting exclusion flags in the contract object
			 */
			contract = setExclusionFlags(contractDataObject, contract);
			//Setting flags on contract object, for E034
        	contract = setCopayIndicatorFlag(contractDataObject,contract);   		
        	contract = setDedIndicatorFlag(contractDataObject,contract); 
        	
			String majorHeadingDesc = contractDataObject.getMajorHeading();
			contract.setProductFamily(contractDataObject.getProductFamily());
			
			// setting product code
			contract.setProductCode(contractDataObject.getProductCode());
			//setting HPN product code
			contract.setHpnProduct(contractDataObject.getHpnProduct());
			contract.setHpnProductSDesc(contractDataObject.getHpnProductSDesc());
			
			if (null != contractDataObject.getProductCode()
					&& getProductCodesToBeExcludedForE022().contains(
							contractDataObject.getProductCode())) {
				contract.setMedSUPContract(true);
			}
			// setting Contract Administration
			contract.setAnswerCalYearOrBenYear(contractDataObject
					.getContractAdministration());
			if (null == contract.getBusinessEntity()) {
				contract.setBusinessEntity(contractDataObject
						.getBusinessEntity());
			}
			if (null == contract.getGroupStateHQ()) {
				contract.setGroupStateHQ(contractDataObject.getGroupStateHQ());
			}
			if (null == contract.getExpiryDate()) {
				contract.setExpiryDate(contractDataObject.getEndDate());
			}
			if (null == contract.getEffectiveDate()) {
				contract.setEffectiveDate(contractDataObject.getStartDate());
			}
			
			/**
			 * Checking if its a new Major Heading
			 */
			if (!majorHeadingsMap.containsKey(majorHeadingDesc)) {
				majorHeadings = new MajorHeadingsVO();
				minorHeadingsMap = new HashMap();
				mappingsMap = new HashMap();
				MinorHeadingsVO minorHeadings = new MinorHeadingsVO();

				majorHeadings.setDescriptionText(majorHeadingDesc);
				majorHeadingsMap.put(majorHeadingDesc, majorHeadings);

				String minorHeadingDesc = contractDataObject.getMinorHeading();

				if (null != contractDataObject.getMappedVariableId()) {
					Mapping mapping = new ContractMappingVO();
					Variable variable = new Variable();

					variable.setVariableId(contractDataObject.getVariableId());
					variable.setDescription(contractDataObject
							.getVariableDescription());
					variable.setVariableValue(contractDataObject
							.getVariableValue());
					variable.setSensitiveBenefitIndicator(contractDataObject
							.getSensitiveBenefitIndicator());
					variable.setPva(contractDataObject.getPva());
					variable.setVariableFormat(contractDataObject.getFormat());
					variable.setVariableStatus(contractDataObject
							.getMappingStatus());
					/* Code change for E025 error validation - Start */
					variable
					.setLgCatagory(contractDataObject.getCategoryCode());
					//BXNI CR35
					variable.setWpdAccumulator(contractDataObject.getWPDAccumulator());
					/* Code change for E025 error validation - End */
					/* Code change done for eBX - start */
					if (null != contractDataObject.getMappingStatus()
							&& !DomainConstants.STATUS_NOT_APPLICABLE
							.equalsIgnoreCase(contractDataObject
									.getMappingStatus())) {
						variable.setNotApplicable(false);
					}
					/* Code change done for eBX - end */

					mapping.setVariable(variable);
					/* Code change done for eBX - start */
					if (null != contractDataObject.getMappingComplete()) {
						/*
						 * if(DomainConstants.Y.equalsIgnoreCase(contractDataObject
						 * .getMappingComplete())) { //Here "N" is set because
						 * the report column indicates "Not finalized"
						 * mappingObj.setMappingComplete(DomainConstants.N); }
						 * else {
						 * mappingObj.setMappingComplete(DomainConstants.Y); }
						 */
						/* Code change for enhancement requirement--Start */
						mapping.setMappingComplete(contractDataObject
								.getMappingComplete());
						/* Code change for enhancement requirement--End */
					}
					mapping.setVariableMappingStatus(contractDataObject
							.getMappingStatus());
					//SSCR 19537 change. Msg and Msg_Type is no longer a part of Mapping.
					mapping.setMessage(contractDataObject.getMessage());
					mapping.setMsg_type_required(contractDataObject
							.getMsgReqType());
					mapping.setIndvdlEb03AssnIndicator(contractDataObject.getIndvdlEb03AssnIndicator());
					/* Code change done for eBX - end */
					Mapping mappingObj = getMapping(contractDataObject,
							mapping, contract);
					mappingObj.setMapped(true);
					
					mappingsMap.put(contractDataObject.getVariableId(),
							mappingObj);
					minorHeadings.setMappings(mappingsMap);
				} else {
					Mapping mapping = new ContractMappingVO();
					Variable variable = new Variable();

					variable.setVariableId(contractDataObject.getVariableId());
					variable.setDescription(contractDataObject
							.getVariableDescription());
					variable.setVariableValue(contractDataObject
							.getVariableValue());
					variable.setSensitiveBenefitIndicator(contractDataObject
							.getSensitiveBenefitIndicator());
					variable.setPva(contractDataObject.getPva());
					variable.setVariableFormat(contractDataObject.getFormat());
					/* Code change for E025 error validation - Start */
					variable
					.setLgCatagory(contractDataObject.getCategoryCode());
					//BXNI CR35
					variable.setWpdAccumulator(contractDataObject.getWPDAccumulator());
					/* Code change for E025 error validation - End */
					mapping.setVariable(variable);
					mapping.setIndvdlEb03AssnIndicator(contractDataObject.getIndvdlEb03AssnIndicator());
					mappingsMap
					.put(contractDataObject.getVariableId(), mapping);
					minorHeadings.setMappings(mappingsMap);
				}
				minorHeadings.setDescriptionText(minorHeadingDesc);
				minorHeadingsMap.put(minorHeadingDesc, minorHeadings);
				MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO) majorHeadingsMap
				.get(contractDataObject.getMajorHeading());
				majorHeadingFromMap.setMinorHeadings(minorHeadingsMap);
			}
			// If this is an existing Major Heading
			else {
				MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO) majorHeadingsMap
				.get(contractDataObject.getMajorHeading());
				Map minorHeadingsMp = majorHeadingFromMap.getMinorHeadings();
				// If this is a new Minor Heading
				if (!minorHeadingsMp.containsKey(contractDataObject
						.getMinorHeading())) {
					// minorHeadingsMap = new HashMap();
					MinorHeadingsVO minorHeadings = new MinorHeadingsVO();
					mappingsMajorExists = new HashMap();
					mappingsMinorExists = new HashMap();
					String minorHeadingDesc = contractDataObject
					.getMinorHeading();
					if (null != contractDataObject.getMappedVariableId()) {
						Mapping mapping = new ContractMappingVO();
						Variable variable = new Variable();
						variable.setVariableId(contractDataObject
								.getVariableId());
						variable.setDescription(contractDataObject
								.getVariableDescription());
						variable.setVariableValue(contractDataObject
								.getVariableValue());
						variable
						.setSensitiveBenefitIndicator(contractDataObject
								.getSensitiveBenefitIndicator());
						variable.setPva(contractDataObject.getPva());
						variable.setVariableFormat(contractDataObject
								.getFormat());
						variable.setVariableStatus(contractDataObject
								.getMappingStatus());
						/* Code change for E025 error validation - Start */
						variable.setLgCatagory(contractDataObject
								.getCategoryCode());
						//BXNI CR35
						variable.setWpdAccumulator(contractDataObject.getWPDAccumulator());
						/* Code change for E025 error validation - End */
						mapping.setVariable(variable);
						/* Code change done for eBX - Start */
						if (null != contractDataObject.getMappingStatus()
								&& !DomainConstants.STATUS_NOT_APPLICABLE
								.equalsIgnoreCase(contractDataObject
										.getMappingStatus())) {
							variable.setNotApplicable(false);
						}
						/* Code change done for eBX - end */
						mapping.setVariable(variable);
						/* Code change done for eBX - start */
						if (null != contractDataObject.getMappingComplete()) {
							/*
							 * if(DomainConstants.Y.equalsIgnoreCase(contractDataObject
							 * .getMappingComplete())) { //Here "N" is set
							 * because the report column indicates
							 * "Not finalized"
							 * mappingObj.setMappingComplete(DomainConstants.N);
							 * } else {
							 * mappingObj.setMappingComplete(DomainConstants.Y);
							 * }
							 */
							/* Code change for enhancement requirement--Start */
							mapping.setMappingComplete(contractDataObject
									.getMappingComplete());
							/* Code change for enhancement requirement--End */
						}
						mapping.setVariableMappingStatus(contractDataObject
								.getMappingStatus());
						mapping.setMessage(contractDataObject.getMessage());
						mapping.setMsg_type_required(contractDataObject
								.getMsgReqType());
						mapping.setIndvdlEb03AssnIndicator(contractDataObject.getIndvdlEb03AssnIndicator());
						/* Code change done for eBX - end */

						Mapping mappingObj = getMapping(contractDataObject,
								mapping, contract);
						mappingObj.setMapped(true);
						mappingsMinorExists.put(contractDataObject
								.getVariableId(), mappingObj);
						minorHeadings.setMappings(mappingsMinorExists);
					} else {
						Mapping mapping = new ContractMappingVO();
						Variable variable = new Variable();
						variable.setVariableId(contractDataObject
								.getVariableId());
						variable.setDescription(contractDataObject
								.getVariableDescription());
						variable.setVariableValue(contractDataObject
								.getVariableValue());
						variable
						.setSensitiveBenefitIndicator(contractDataObject
								.getSensitiveBenefitIndicator());
						variable.setPva(contractDataObject.getPva());
						variable.setVariableFormat(contractDataObject
								.getFormat());
						/* Code change for E025 error validation - Start */
						variable.setLgCatagory(contractDataObject
								.getCategoryCode());
						//BXNI CR35
						variable.setWpdAccumulator(contractDataObject.getWPDAccumulator());
						/* Code change for E025 error validation - End */
						mapping.setVariable(variable);
						mapping.setIndvdlEb03AssnIndicator(contractDataObject.getIndvdlEb03AssnIndicator());
						// mappingsMinorExists.put(contractDataObject.getVariableId(),
						// mapping);
						// minorHeadings.setMappings(mappingsMinorExists);
						mappingsMajorExists.put(contractDataObject
								.getVariableId(), mapping);
						minorHeadings.setMappings(mappingsMajorExists);
					}
					minorHeadings.setDescriptionText(minorHeadingDesc);
					minorHeadingsMp.put(minorHeadingDesc, minorHeadings);
				}
				// If this is an existing Minor Heading
				else {
					MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO) minorHeadingsMp
					.get(contractDataObject.getMinorHeading());
					Map mappingsMp = minorHeadingFromMap.getMappings();

					if (null != mappingsMp) {
						if (null != contractDataObject.getMappedVariableId()) {
							if (!mappingsMp.containsKey(contractDataObject
									.getVariableId())) {// if Mapping doesnt
								// exist
								Mapping mapping = new ContractMappingVO();
								Variable variable = new Variable();

								variable
								.setSensitiveBenefitIndicator(contractDataObject
										.getSensitiveBenefitIndicator());
								variable.setVariableId(contractDataObject
										.getVariableId());
								variable.setDescription(contractDataObject
										.getVariableDescription());
								variable.setVariableValue(contractDataObject
										.getVariableValue());

								variable.setPva(contractDataObject.getPva());
								variable.setVariableFormat(contractDataObject
										.getFormat());
								variable.setVariableStatus(contractDataObject
										.getMappingStatus());
								/* Code change for E025 error validation - Start */
								variable.setLgCatagory(contractDataObject
										.getCategoryCode());
								//BXNI CR35
								variable.setWpdAccumulator(contractDataObject.getWPDAccumulator());
								/* Code change for E025 error validation - End */
								mapping.setVariable(variable);
								/* Code change done for eBX - start */
								if (null != contractDataObject
										.getMappingStatus()
										&& !DomainConstants.STATUS_NOT_APPLICABLE
										.equalsIgnoreCase(contractDataObject
												.getMappingStatus())) {
									variable.setNotApplicable(false);
								}
								/* Code change done for eBX - end */
								mapping.setVariable(variable);
								/* Code change done for eBX - start */
								if (null != contractDataObject
										.getMappingComplete()) {
									/*
									 * if(DomainConstants.Y.equalsIgnoreCase(contractDataObject
									 * .getMappingComplete())) { //Here "N" is
									 * set because the report column indicates
									 * "Not finalized"
									 * mappingObj.setMappingComplete
									 * (DomainConstants.N); } else {
									 * mappingObj.setMappingComplete
									 * (DomainConstants.Y); }
									 */
									/*
									 * Code change for enhancement
									 * requirement--Start
									 */
									mapping
									.setMappingComplete(contractDataObject
											.getMappingComplete());
									/*
									 * Code change for enhancement
									 * requirement--End
									 */
								}
								mapping
								.setVariableMappingStatus(contractDataObject
										.getMappingStatus());
								mapping.setMessage(contractDataObject
										.getMessage());
								mapping.setMsg_type_required(contractDataObject
										.getMsgReqType());
								mapping.setIndvdlEb03AssnIndicator(contractDataObject.getIndvdlEb03AssnIndicator());
								/* Code change done for eBX - end */

								Mapping mappingObj = getMapping(
										contractDataObject, mapping, contract);
								mappingObj.setMapped(true);
								mappingsMp.put(contractDataObject
										.getVariableId(), mappingObj);
							} else {
								Mapping mappingFromMap = (Mapping) mappingsMp
								.get(contractDataObject.getVariableId());
								mappingFromMap.setIndvdlEb03AssnIndicator(contractDataObject.getIndvdlEb03AssnIndicator());
								Mapping mappingObj = getMapping(
										contractDataObject, mappingFromMap,
										contract);
								//SSCR 19537 Start
								List<EB03Association> eb03AssnList = getDistinctEb03AssociationList(mappingObj);
								HippaSegment updatedEb03 = new HippaSegment();
								if(null != mappingObj.getEb03()) {
									HippaSegment presentEb03 = mappingObj.getEb03();
									updatedEb03.setDescription(presentEb03.getDescription());
									updatedEb03.setHippaCodePossibleValues(presentEb03.getHippaCodePossibleValues());
									updatedEb03.setHippaCodeSelectedValues(presentEb03.getHippaCodeSelectedValues());
									updatedEb03.setHippaSegmentDefinition(presentEb03.getHippaSegmentDefinition());
									updatedEb03.setHippaSegmentName(presentEb03.getHippaSegmentName());
									updatedEb03.setName(presentEb03.getName());
									updatedEb03.setEb03Association(eb03AssnList);
								}
								mappingObj.setEb03(updatedEb03);
								//SSCR 19537 End
								mappingObj.setMapped(true);
								minorHeadingFromMap.getMappings().put(
										contractDataObject.getVariableId(),
										mappingObj);
							}
						} else {

							Mapping mapping = new ContractMappingVO();
							Variable variable = new Variable();

							variable
							.setSensitiveBenefitIndicator(contractDataObject
									.getSensitiveBenefitIndicator());
							variable.setVariableId(contractDataObject
									.getVariableId());
							variable.setDescription(contractDataObject
									.getVariableDescription());
							variable.setVariableValue(contractDataObject
									.getVariableValue());

							variable.setPva(contractDataObject.getPva());
							variable.setVariableFormat(contractDataObject
									.getFormat());
							/* Code change for E025 error validation - Start */
							variable.setLgCatagory(contractDataObject
									.getCategoryCode());
							//BXNI CR35
							variable.setWpdAccumulator(contractDataObject.getWPDAccumulator());
							/* Code change for E025 error validation - End */
							mapping.setVariable(variable);
							mapping.setIndvdlEb03AssnIndicator(contractDataObject.getIndvdlEb03AssnIndicator());
							mappingsMp.put(contractDataObject.getVariableId(),
									mapping);
							// minorHeadingFromMap.setMappings(mappingsMp);
							minorHeadingFromMap.getMappings()
							.put(contractDataObject.getVariableId(),
									mapping);
						}
					}
				}
			}
		}
		contract.setMajorHeadings(majorHeadingsMap);
		contract.setAccumHippaSegmentList(accumHippaSegmentList);
		// Sets HMO flag - September release change.
		//Removing the existing logic to identify HMO contract as part of SSCR 16331 Nov Release
		/*contract = ErrorValidationHelper.setHMOFlag(contract, variableMap);*/
		// Sets HMO flag - SSCR 16331 Nov Release change.
		contract = ErrorValidationHelper.setHMOFlagForLG(contract);
		// Sets EPO flag - September release change.
		contract = ErrorValidationHelper.setEPOFlag(contract, DomainConstants.SYSTEM_LG);
		// Sets HCR flag for E016 and E017
		contract = ErrorValidationHelper.setFlagHCRForE016And17(contract, variableMap);
		// Sets HCR flag for E018
    	contract = ErrorValidationHelper.setFlagHCRForE018(contract, variableMap);
    	// Sets MCSTPA Flag flag for E011, E012 -- SSCR 16331 Nov Release
    	String codedValueofACLMSTPA = MappingUtil.getVariableCodedValueInContract(contract, DomainConstants.VARIABLE_ACLMSTPA);
    	contract = ErrorValidationHelper.setMCSTPAFlagForLG(contract, codedValueofACLMSTPA);
    	logger.info("MCS TPA Contract Flag is >>>"+ contract.isContractMCSTPA());

		return contract;
	}
	
	/**
	 * @param mappingObj
	 * @return
	 */
	private List<EB03Association> getDistinctEb03AssociationList(
			Mapping mappingObj) {
		List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
		if(null != mappingObj.getEb03() && null != mappingObj.getEb03().getEb03Association() 
				&& mappingObj.getEb03().getEb03Association().size() > 0) {
			List<String> assnList = getDistinctEB03Values(mappingObj.getEb03());
			if(null != assnList && assnList.size() != 0) {
				for(String assnStr : assnList)
				{
					EB03Association eb03Assn = new EB03Association();
					for(EB03Association eb03AssnObj : mappingObj.getEb03().getEb03Association()) {
						if(assnStr.equals(eb03AssnObj.getEb03().getValue())) {
							if(null != eb03AssnObj.getEb03()) {
								HippaCodeValue hippaEb03Code = new HippaCodeValue();
								hippaEb03Code.setValue(eb03AssnObj.getEb03().getValue());
								eb03Assn.setEb03(hippaEb03Code);
								eb03Assn.setEb03String(eb03AssnObj.getEb03().getValue());
							}
							if(null != eb03AssnObj.getNoteType()) {
								HippaCodeValue hippaNoteTypeCode = new HippaCodeValue();
								hippaNoteTypeCode.setValue(eb03AssnObj.getNoteType().getValue());
								eb03Assn.setNoteType(hippaNoteTypeCode);
							}
							if(null != eb03AssnObj.getIii02List() && eb03AssnObj.getIii02List().size() > 0) {
								List<HippaCodeValue> hippaIii02List = new ArrayList<HippaCodeValue>();
								for(HippaCodeValue iii02Code : eb03AssnObj.getIii02List()) {
									HippaCodeValue hippaIii02Code = new HippaCodeValue();
									hippaIii02Code.setValue(iii02Code.getValue());
									hippaIii02List.add(hippaIii02Code);
								}
								eb03Assn.setIii02List(hippaIii02List);
							}
							if(null != eb03AssnObj.getMessage()) {
								eb03Assn.setMessage(eb03AssnObj.getMessage());
							}
							if(null != eb03AssnObj.getMsgReqdInd()) {
								eb03Assn.setMsgReqdInd(eb03AssnObj.getMsgReqdInd());
							}
							if(null != eb03AssnObj.getExtndMsg1()) {
								eb03Assn.setExtndMsg1(eb03AssnObj.getExtndMsg1());
							}
							if(null != eb03AssnObj.getExtndMsg2()) {
								eb03Assn.setExtndMsg2(eb03AssnObj.getExtndMsg2());
							}
							if(null != eb03AssnObj.getExtndMsg3()) {
								eb03Assn.setExtndMsg3(eb03AssnObj.getExtndMsg3());
							}
							if(null != eb03AssnObj.getNetworkInd()) {
								eb03Assn.setNetworkInd(eb03AssnObj.getNetworkInd());
							}
							if(null != eb03AssnObj.getHighPrfrmnNonTierdMsg()) {
								eb03Assn.setHighPrfrmnNonTierdMsg(eb03AssnObj.getHighPrfrmnNonTierdMsg());
							}
							if(null != eb03AssnObj.getHighPrfrmnTierdMsg()) {
								eb03Assn.setHighPrfrmnTierdMsg(eb03AssnObj.getHighPrfrmnTierdMsg());
							}
						}
					}
					eb03AssnList.add(eb03Assn);
				
				}
			}
		}
		return eb03AssnList;
	}
	
	/**
	 * @param hippaSegment
	 */
	private List<String> getDistinctEB03Values(HippaSegment hippaSegment) {
		List<String> assnList = new ArrayList<String>();
		for(EB03Association eb03Assn : hippaSegment.getEb03Association()) {
			if(null != eb03Assn.getEb03() && (null != eb03Assn.getEb03().getValue() || "".equals(eb03Assn.getEb03().getValue())))
			{
				 if (!assnList.contains(eb03Assn.getEb03().getValue())) {
				    	assnList.add(eb03Assn.getEb03().getValue());
				    }
			}
		}
		return assnList;
	}


	/**
	 * The method will create a HippaSegment object and add the HippaCode value
	 * to it and return the HippaSegment object
	 * 
	 * @param element
	 * @param val
	 * @return
	 */
	private HippaSegment addHippaSegment(String element, String val) {
		List selectedHippaSegmentValues = new ArrayList();
		
		HippaSegment hippaSegment = new HippaSegment(element);
		HippaCodeValue code = new HippaCodeValue();
		code.setValue(val);
		selectedHippaSegmentValues.add(code);
		hippaSegment.setHippaCodeSelectedValues(selectedHippaSegmentValues);
		
		return hippaSegment;
	}
	
	/**
	 * The method will create a Mapping object for a Variable
	 * 
	 * @param contractDataObject
	 * @param mappingObj
	 * @return
	 */
	private Mapping getMapping(ContractDataObjectVO contractDataObject,
			Mapping mappingObj, ContractVO contract) {
		if (null != contractDataObject) {
			HippaSegment eb03 = new HippaSegment();
			HippaSegment accum = new HippaSegment();
			HippaSegment umRules = new HippaSegment();
			HippaSegment hsd02 = new HippaSegment();
			List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
			
			if (null != contractDataObject.getHippaSegment()) {

				if (DomainConstants.EB01_NAME.equals(contractDataObject
						.getHippaSegment())) {
					mappingObj.setEb01(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));
					if(contractDataObject
							.getHippaValue().equals(DomainConstants.EB01_D)){
						if(null != contractDataObject.getExtndMsgTxt1()){
							mappingObj.setEb01_extndMsgTxt1(contractDataObject.getExtndMsgTxt1());
						}
						if (null != contractDataObject.getExtndMsgTxt2()){
							mappingObj.setEb01_extndMsgTxt2(contractDataObject.getExtndMsgTxt2());
						}
						if (null != contractDataObject.getExtndMsgTxt3()){
							mappingObj.setEb01_extndMsgTxt3(contractDataObject.getExtndMsgTxt3());
						}
						if(null != contractDataObject.getEb12ChangeInd()){
							mappingObj.setEb01_eb12ChangeInd(contractDataObject.getEb12ChangeInd());
						}
					}
				} else if (DomainConstants.EB02_NAME.equals(contractDataObject
						.getHippaSegment())) {
					mappingObj.setEb02(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));
				} else if (DomainConstants.EB03_NAME.equals(contractDataObject
						.getHippaSegment())) {
					// for checking E010 in Validator
					if (null != contractDataObject.getHippaValue()
							&& DomainConstants.EB03_60
							.equals(contractDataObject.getHippaValue())) {
						contract.getListOfEB0360forE010().add(
								mappingObj.getVariable());
						if (getInNetworkPVAMappingsForE010().contains(
								mappingObj.getVariable().getPva())) {
							contract.setFlagEB0360_In(true);
						}
						if (getOutNetworkPVAMappingsForE010().contains(
								mappingObj.getVariable().getPva())) {
							contract.setFlagEB0360_Out(true);
						}
					}
					/*if (null != mappingObj.getEb03()) {
						HippaCodeValue code = new HippaCodeValue();

						code.setValue(contractDataObject.getHippaValue());
						mappingObj.getEb03().getHippaCodeSelectedValues().add(
								code);
					} else {
						List eb03SelectedValues = new ArrayList();
						HippaCodeValue code = new HippaCodeValue();

						eb03.setName(contractDataObject.getHippaSegment());
						code.setValue(contractDataObject.getHippaValue());
						eb03SelectedValues.add(code);
						eb03.setHippaCodeSelectedValues(eb03SelectedValues);
						mappingObj.setEb03(eb03);
					}*/
					//SSCR 19537
					if(null != contractDataObject.getEb03Association())
					{
						EB03Association eb03Assn = new EB03Association();
						HippaCodeValue code = new HippaCodeValue();
						eb03Assn.setEb03String(contractDataObject.getHippaValue());
						code.setValue(contractDataObject.getHippaValue());
						eb03Assn.setEb03(code);
						if(null != contractDataObject.getExtndMsgTxt1()){
							eb03Assn.setExtndMsg1(contractDataObject.getExtndMsgTxt1());
						}
						if (null != contractDataObject.getExtndMsgTxt2()){
							eb03Assn.setExtndMsg2(contractDataObject.getExtndMsgTxt2());
						}
						if (null != contractDataObject.getExtndMsgTxt3()){
							eb03Assn.setExtndMsg3(contractDataObject.getExtndMsgTxt3());
						}
						if (null != contractDataObject.getEb12ChangeInd()){
							eb03Assn.setNetworkInd(contractDataObject.getEb12ChangeInd());
						}
						if( null != contractDataObject.getHighPrfrmnNonTierdMsg()){
							eb03Assn.setHighPrfrmnNonTierdMsg(contractDataObject.getHighPrfrmnNonTierdMsg());
						}
						if( null != contractDataObject.getHighPrfrmnTierdMsg()){
							eb03Assn.setHighPrfrmnTierdMsg(contractDataObject.getHighPrfrmnTierdMsg());
						}
						eb03AssnList.add(eb03Assn);
					}
					if(null == mappingObj.getEb03())
					{
						HippaSegment eb03Code = new HippaSegment();
						HippaCodeValue code = new HippaCodeValue();
						List eb03SelectedValues = new ArrayList();
						eb03Code.setName(contractDataObject.getHippaSegment());
						code.setValue(contractDataObject.getHippaValue());
						eb03SelectedValues.add(code);
						eb03Code.setHippaCodeSelectedValues(eb03SelectedValues);
						eb03Code.setEb03Association(eb03AssnList);
						mappingObj.setEb03(eb03Code);
					}else {
						HippaCodeValue code = new HippaCodeValue();

						code.setValue(contractDataObject.getHippaValue());
						mappingObj.getEb03().getHippaCodeSelectedValues().add(
								code);
						mappingObj.getEb03().getEb03Association().addAll(eb03AssnList);
					}
				} else if (DomainConstants.EB06_NAME.equals(contractDataObject
						.getHippaSegment())) {
					mappingObj.setEb06(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));
				} else if (DomainConstants.EB09_NAME.equals(contractDataObject
						.getHippaSegment())) {
					mappingObj.setEb09(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));
				} else if (DomainConstants.III02_NAME.equals(contractDataObject
						.getHippaSegment())) {
					/*mappingObj.setIi02(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));*/
					//SSCR15937
					if(null != contractDataObject.getEb03Association())
					{
						EB03Association eb03Assn = new EB03Association();
						HippaCodeValue eb03AssnCode = new HippaCodeValue();
						HippaCodeValue iii02Code = new HippaCodeValue();
						eb03Assn.setEb03String(contractDataObject.getEb03Association());
						eb03AssnCode.setValue(contractDataObject.getEb03Association());
						iii02Code.setValue(contractDataObject.getHippaValue());
						eb03Assn.setEb03(eb03AssnCode);
						eb03Assn.getIii02List().add(iii02Code);
						eb03AssnList.add(eb03Assn);
					}
					if(null == mappingObj.getEb03())
					{
						HippaSegment eb03Code = new HippaSegment();
						eb03Code.setEb03Association(eb03AssnList);
						mappingObj.setEb03(eb03Code);
					}else {
						mappingObj.getEb03().getEb03Association().addAll(eb03AssnList);
					}
				} else if (DomainConstants.HSD01_NAME.equals(contractDataObject
						.getHippaSegment())) {
					mappingObj.setHsd01(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));
				} else if (DomainConstants.HSD02_NAME.equals(contractDataObject
						.getHippaSegment())) {
					if (null != mappingObj.getHsd02()) {
						HippaCodeValue code = new HippaCodeValue();
						code.setValue(contractDataObject.getHippaValue());
						mappingObj.getHsd02().getHippaCodeSelectedValues().add(
								code);
					} else {
						hsd02.setName(contractDataObject.getHippaSegment());
						List hsd02SelectedValues = new ArrayList();
						HippaCodeValue code = new HippaCodeValue();
						code.setValue(contractDataObject.getHippaValue());
						hsd02SelectedValues.add(code);
						hsd02.setHippaCodeSelectedValues(hsd02SelectedValues);
						mappingObj.setHsd02(hsd02);
					}
					
					/*
					mappingObj.setHsd02(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));
				*/} else if (DomainConstants.HSD03_NAME.equals(contractDataObject
						.getHippaSegment())) {
					mappingObj.setHsd03(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));
				} else if (DomainConstants.HSD04_NAME.equals(contractDataObject
						.getHippaSegment())) {
					mappingObj.setHsd04(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));
				} else if (DomainConstants.HSD05_NAME.equals(contractDataObject
						.getHippaSegment())) {
					mappingObj.setHsd05(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));
				} else if (DomainConstants.HSD06_NAME.equals(contractDataObject
						.getHippaSegment())) {
					mappingObj.setHsd06(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));
				} else if (DomainConstants.HSD07_NAME.equals(contractDataObject
						.getHippaSegment())) {
					mappingObj.setHsd07(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));
				} else if (DomainConstants.HSD08_NAME.equals(contractDataObject
						.getHippaSegment())) {
					mappingObj.setHsd08(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));
				} else if (DomainConstants.ACCUM_NAME.equals(contractDataObject
						.getHippaSegment())) {
					if (null != mappingObj.getAccum()) {
						HippaCodeValue code = new HippaCodeValue();
						code.setValue(contractDataObject.getHippaValue());
						mappingObj.getAccum().getHippaCodeSelectedValues().add(
								code);
					} else {
						accum.setName(contractDataObject.getHippaSegment());
						List accumSelectedValues = new ArrayList();
						HippaCodeValue code = new HippaCodeValue();
						code.setValue(contractDataObject.getHippaValue());
						accumSelectedValues.add(code);
						accum.setHippaCodeSelectedValues(accumSelectedValues);
						mappingObj.setAccum(accum);
					}
				} else if (DomainConstants.NOTE_TYPE_CODE
						.equals(contractDataObject.getHippaSegment())) {
					/*mappingObj.setNoteTypeCode(addHippaSegment(
							contractDataObject.getHippaSegment(),
							contractDataObject.getHippaValue()));*/
					//SSCR15937
					if(null != contractDataObject.getEb03Association())
					{
						EB03Association eb03Assn = new EB03Association();
						HippaCodeValue eb03AssnCode = new HippaCodeValue();
						HippaCodeValue noteTypeCode = new HippaCodeValue();
						eb03Assn.setEb03String(contractDataObject.getEb03Association());
						eb03AssnCode.setValue(contractDataObject.getEb03Association());
						noteTypeCode.setValue(contractDataObject.getHippaValue());
						eb03Assn.setEb03(eb03AssnCode);
						eb03Assn.setNoteType(noteTypeCode);
						eb03AssnList.add(eb03Assn);
					}
					if(null == mappingObj.getEb03())
					{
						HippaSegment eb03Code = new HippaSegment();
						eb03Code.setEb03Association(eb03AssnList);
						mappingObj.setEb03(eb03Code);
					}else {
						mappingObj.getEb03().getEb03Association().addAll(eb03AssnList);
					}
				}else if(DomainConstants.UMRULE_NAME.equals(contractDataObject.getHippaSegment())) {
					
					if (null != mappingObj.getUtilizationMgmntRule()) {
						HippaCodeValue code = new HippaCodeValue();

						code.setValue(contractDataObject.getHippaValue());
						mappingObj.getUtilizationMgmntRule().getHippaCodeSelectedValues().add(
								code);
					} else {
						List umRuleSelectedValues = new ArrayList();
						HippaCodeValue code = new HippaCodeValue();

						umRules.setName(contractDataObject.getHippaSegment());
						code.setValue(contractDataObject.getHippaValue());
						umRuleSelectedValues.add(code);
						umRules.setHippaCodeSelectedValues(umRuleSelectedValues);
						mappingObj.setUtilizationMgmntRule(umRules);
					}
				} else if (DomainConstants.START_AGE_NAME.equals(contractDataObject
						.getHippaSegment())) {
					mappingObj.setStartAge(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));
				} else if (DomainConstants.END_AGE_NAME.equals(contractDataObject
						.getHippaSegment())) {
					mappingObj.setEndAge(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));
				}else if (DomainConstants.VAR_MSG.equals(contractDataObject.getHippaSegment())) {
					EB03Association eb03Association;
					if(null != contractDataObject.getEb03Association() 
							&& !"".equalsIgnoreCase(contractDataObject.getEb03Association())){
							eb03Association = new EB03Association();
							HippaCodeValue eb03AssnCode = new HippaCodeValue();
							eb03Association.setEb03String(contractDataObject.getEb03Association());
							eb03AssnCode.setValue(contractDataObject.getEb03Association());
							eb03Association.setEb03(eb03AssnCode);
							eb03Association.setMessage(contractDataObject.getHippaValue());
							eb03AssnList.add(eb03Association);
						}
					if(null == mappingObj.getEb03())
					{
						HippaSegment eb03Code = new HippaSegment();
						eb03Code.setEb03Association(eb03AssnList);
						mappingObj.setEb03(eb03Code);
					}else {
						mappingObj.getEb03().getEb03Association().addAll(eb03AssnList);
					}
				}else if (DomainConstants.MESG_REQD_IND.equals(contractDataObject.getHippaSegment())) {
					EB03Association eb03Association;
					if(null != contractDataObject.getEb03Association() 
							&& !"".equalsIgnoreCase(contractDataObject.getEb03Association())){
							eb03Association = new EB03Association();
							HippaCodeValue eb03AssnCode = new HippaCodeValue();
							eb03Association.setEb03String(contractDataObject.getEb03Association());
							eb03AssnCode.setValue(contractDataObject.getEb03Association());
							eb03Association.setEb03(eb03AssnCode);
							eb03Association.setMsgReqdInd(contractDataObject.getHippaValue());
							eb03AssnList.add(eb03Association);
						}
					if(null == mappingObj.getEb03())
					{
						HippaSegment eb03Code = new HippaSegment();
						eb03Code.setEb03Association(eb03AssnList);
						mappingObj.setEb03(eb03Code);
					}else {
						mappingObj.getEb03().getEb03Association().addAll(eb03AssnList);
					}
				}
			}

		}
		return mappingObj;

	}

	/**
	 * The method sets the exclusions in the ContractVO object for the Error
	 * Validation Logic
	 * 
	 * @param contractDataObject
	 * @param contract
	 * @return
	 */
	private ContractVO setExclusionFlags(
			ContractDataObjectVO contractDataObject, ContractVO contract) {
		if ((DomainConstants.LG_CDHP_VARIABLE1.equals(contractDataObject
				.getVariableId()) || DomainConstants.LG_CDHP_VARIABLE2
				.equals(contractDataObject.getVariableId()))) {
			contract.setFlagCDHP(true);
		}
		/*if (DomainConstants.HMO.equals(contractDataObject.getProductFamily())) {
			contract.setFlagHMO(true);
		}
		if (DomainConstants.EPO.equals(contractDataObject.getProductFamily())) {
			contract.setFlagEPO(true);
		}*/
		/*if (DomainConstants.HCR_VARIABLE.equals(contractDataObject
				.getVariableId())) {
			if (DomainConstants.Y.equals(contractDataObject.getVariableValue())) {
				contract.setFlagHCR_E016And17(true);
			}
		}
		if (DomainConstants.HCR_VARIABLE_E018.equals(contractDataObject
				.getVariableId())) {
			if (DomainConstants.Y.equals(contractDataObject.getVariableValue())) {
				contract.setFlagHCR_E018(true);
			}
		}*/
		if (DomainConstants.Y.equals(contractDataObject
				.getSensitiveBenefitIndicator())) {
			contract.setFlagSensitiveBenefit(true);
		}
		return contract;
	}

	/**
	 * The method sets CopayIndicatorFlag in the ContractVO object 
	 * for the Error Validation Logic
	 * @param contractDataObject
	 * @param contract
	 * @return
	 */
	private ContractVO setCopayIndicatorFlag(ContractDataObjectVO contractDataObject,ContractVO contract){
		
    	if(getCopayIndicatorVariables().contains(contractDataObject.getVariableId().toUpperCase())){
    		
    		if(DomainConstants.Y.equalsIgnoreCase(contractDataObject.getVariableValue())){
    			contract.setCopayIndicatorFlag(DomainConstants.Y);
        	}else if(DomainConstants.N.equalsIgnoreCase(contractDataObject.getVariableValue())){
        		if(!DomainConstants.Y.equalsIgnoreCase(contract.getCopayIndicatorFlag())){
        			contract.setCopayIndicatorFlag(DomainConstants.N);
        		}
        	}else if(DomainConstants.EMPTY.equalsIgnoreCase(contractDataObject.getVariableValue())){
        		if((!DomainConstants.Y.equalsIgnoreCase(contract.getCopayIndicatorFlag())) && 
        				(!DomainConstants.N.equalsIgnoreCase(contract.getCopayIndicatorFlag()))){
        			contract.setCopayIndicatorFlag(DomainConstants.EMPTY);
        		}
        	}
    		
    		
    	}
    	
    	return contract;
	}
	/**
	 * The method sets DedIndicatorFlag in the ContractVO object 
	 * for the Error Validation Logic
	 * @param contractDataObject
	 * @param contract
	 * @return
	 */
	private ContractVO setDedIndicatorFlag(ContractDataObjectVO contractDataObject,ContractVO contract){
		
    	if(getDedIndicatorVariables().contains(contractDataObject.getVariableId().toUpperCase())){
    		
    		if(DomainConstants.Y.equalsIgnoreCase(contractDataObject.getVariableValue())){
    			contract.setDedIndicatorFlag(DomainConstants.Y);
        	}else if(DomainConstants.N.equalsIgnoreCase(contractDataObject.getVariableValue())){
        		if(!DomainConstants.Y.equalsIgnoreCase(contract.getDedIndicatorFlag())){
        			contract.setDedIndicatorFlag(DomainConstants.N);
        		}
        	}else if(DomainConstants.EMPTY.equalsIgnoreCase(contractDataObject.getVariableValue())){
        		if((!DomainConstants.Y.equalsIgnoreCase(contract.getDedIndicatorFlag())) && 
        				(!DomainConstants.N.equalsIgnoreCase(contract.getDedIndicatorFlag()))){
        			contract.setDedIndicatorFlag(DomainConstants.EMPTY);
        		}
        	}
    		
    	}
    	return contract;
	}
	
	
	/**
	 * Query for fetching the Variable Mappings for the Input Contract
	 * 
	 */
	private class LGContractMappingInfoQuery extends MappingSqlQuery {

		public LGContractMappingInfoQuery(DataSource ds) {
			super(ds, getLgContractMappingInfoQuery());
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.DATE));
			compile();
		}

		/**
		 * Setting the values from the result set to ContractDataObjectVO
		 */
		public Object mapRow(ResultSet resultSet, int row) throws SQLException {
			ContractDataObjectVO contractDataObject = new ContractDataObjectVO();
			contractDataObject.setMappedVariableId(resultSet
					.getString(DomainConstants.MAPPED_VARID));
			/* Code change for eBX - start */
			contractDataObject.setMappingStatus(resultSet
					.getString(DomainConstants.VAR_MAPG_STTS_CD));
			contractDataObject.setMappingComplete(resultSet
					.getString(DomainConstants.MAPPNG_CMP_IND));
			
			/*
			 * Msg and Msg_Type are now EB03 Associations and not Mapping Variables as per change for SSCR 19537
			 */
			contractDataObject.setMessage(resultSet
					.getString(DomainConstants.VAR_MSG));
			contractDataObject.setMsgReqType(resultSet
					.getString(DomainConstants.MSG_TYPE));
			contractDataObject.setExtndMsgTxt1(resultSet.getString(DomainConstants.EXTND_MSG1));
			contractDataObject.setExtndMsgTxt2(resultSet.getString(DomainConstants.EXTND_MSG2));
			contractDataObject.setExtndMsgTxt3(resultSet.getString(DomainConstants.EXTND_MSG3));
			contractDataObject.setEb12ChangeInd(resultSet.getString(DomainConstants.EB12_IND));
			contractDataObject.setHighPrfrmnNonTierdMsg(resultSet.getString(DomainConstants.HIGH_PRFRMN_NON_TIERD_MSG));	
			contractDataObject.setHighPrfrmnTierdMsg(resultSet.getString(DomainConstants.HIGH_PRFRMN_TIERD_MSG));	
			
			//Code change for eBX - end 
			contractDataObject.setVariableId(resultSet
					.getString(DomainConstants.CONTRACT_VAR));
			contractDataObject.setHippaSegment(resultSet
					.getString(DomainConstants.DATA_ELEMENT_ID));
			contractDataObject.setHippaValue(resultSet
					.getString(DomainConstants.DATA_ELEMENT_VAL));
			contractDataObject.setMajorHeading(resultSet
					.getString(DomainConstants.MAJOR_TEXT));
			contractDataObject.setMinorHeading(resultSet
					.getString(DomainConstants.MINOR_TEXT));
			contractDataObject.setFormat(resultSet
					.getString(DomainConstants.CONTRACT_VAR_FORMAT));
			contractDataObject.setVariableValue(resultSet
					.getString(DomainConstants.VAR_VALUE));
			contractDataObject.setPva(resultSet
					.getString(DomainConstants.PROV_ARNG));
			contractDataObject.setProductFamily(resultSet
					.getString(DomainConstants.PRODUCT_FAMILY));
			contractDataObject.setSensitiveBenefitIndicator(resultSet
					.getString(DomainConstants.SENSITIVE_BENEFIT_IND));
			contractDataObject.setBusinessEntity(resultSet
					.getString(DomainConstants.BUSINESS_ENTITY_LG));
			contractDataObject.setGroupStateHQ(resultSet
					.getString(DomainConstants.GROUP_STATE_HQ));
			contractDataObject.setEndDate(resultSet
					.getString(DomainConstants.END_DT_LG));
			contractDataObject.setStartDate(resultSet
					.getString(DomainConstants.START_DT_LG));
			contractDataObject.setVariableDescription(resultSet
					.getString(DomainConstants.CONT_VAR_TEXT));
			// contractDataObject.setVariableStatus(resultSet.getString(DomainConstants.VARIABLE_STATUS));
			contractDataObject.setProductCode(resultSet
					.getString(DomainConstants.PRODUCT_CODE));
			contractDataObject.setContractAdministration(resultSet
					.getString(DomainConstants.BENEFIT_YEAR_ACCUM_IND));
			/* Code change for E025 error validation- Start */
			contractDataObject.setCategoryCode(resultSet
					.getString(DomainConstants.CATAGORY_CODE));
			/* Code change for E025 error validation- End */
			// September release code change for HMO logic - Start
			contractDataObject.setContractId(resultSet
					.getString(DomainConstants.CONTRACTID));
			contractDataObject.setCorporatePlan(resultSet
					.getString(DomainConstants.CORPORATE_PLAN));
			contractDataObject.setBenefitStructure(resultSet
					.getString(DomainConstants.BENEFIT_STRUCTURE));
			// September release code change for HMO logic - End
			
			//Setting group name for contract -- SSCR 16331 Nov Release
			contractDataObject.setGroupName(resultSet
					.getString(DomainConstants.CONRACT_GROUP_NM));
			//BXNI CR35
			contractDataObject.setWPDAccumulator(resultSet.getString("WPDACCUM"));
			
			//SSCR 19537
			contractDataObject.setEb03Association(resultSet.getString("EB03_ASSN"));
			contractDataObject.setIndvdlEb03AssnIndicator(resultSet.getString("INDVDL_EB03_ASSN_IND"));
			
			//HPN BX report changes
			contractDataObject.setHpnProduct(resultSet.getString("PRD_CDE"));
			contractDataObject.setHpnProductSDesc(resultSet.getString("PRD_SDESC"));
			return contractDataObject;
		}
	}

	/*** FOR TEST ***/
	private class LGContractTestMappingInfoQuery extends MappingSqlQuery {

		public LGContractTestMappingInfoQuery(DataSource ds) {
			super(ds, getLgContractTestMappingInfoQuery());
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.DATE));
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.DATE));
			compile();
		}

		/**
		 * Setting the values from the result set to ContractDataObjectVO
		 */
		public Object mapRow(ResultSet resultSet, int row) throws SQLException {
			ContractDataObjectVO contractDataObject = new ContractDataObjectVO();
			contractDataObject.setMappedVariableId(resultSet
					.getString(DomainConstants.MAPPED_VARID));
			/* Code change for eBX - start */
			contractDataObject.setMappingStatus(resultSet
					.getString(DomainConstants.VAR_MAPG_STTS_CD));
			contractDataObject.setMappingComplete(resultSet
					.getString(DomainConstants.MAPPNG_CMP_IND));
			contractDataObject.setMessage(resultSet
					.getString(DomainConstants.VAR_MSG));
			contractDataObject.setMsgReqType(resultSet
					.getString(DomainConstants.MSG_TYPE));
			contractDataObject.setExtndMsgTxt1(resultSet.getString(DomainConstants.EXTND_MSG1));
			contractDataObject.setExtndMsgTxt2(resultSet.getString(DomainConstants.EXTND_MSG2));
			contractDataObject.setExtndMsgTxt3(resultSet.getString(DomainConstants.EXTND_MSG3));
			contractDataObject.setEb12ChangeInd(resultSet.getString(DomainConstants.EB12_IND));
			contractDataObject.setHighPrfrmnNonTierdMsg(resultSet.getString(DomainConstants.HIGH_PRFRMN_NON_TIERD_MSG));	
			contractDataObject.setHighPrfrmnTierdMsg(resultSet.getString(DomainConstants.HIGH_PRFRMN_TIERD_MSG));	
				
			/* Code change for eBX - end */
			contractDataObject.setVariableId(resultSet
					.getString(DomainConstants.CONTRACT_VAR));
			contractDataObject.setHippaSegment(resultSet
					.getString(DomainConstants.DATA_ELEMENT_ID));
			contractDataObject.setHippaValue(resultSet
					.getString(DomainConstants.DATA_ELEMENT_VAL));
			contractDataObject.setMajorHeading(resultSet
					.getString(DomainConstants.MAJOR_TEXT));
			contractDataObject.setMinorHeading(resultSet
					.getString(DomainConstants.MINOR_TEXT));
			contractDataObject.setFormat(resultSet
					.getString(DomainConstants.CONTRACT_VAR_FORMAT));
			contractDataObject.setVariableValue(resultSet
					.getString(DomainConstants.VAR_VALUE));
			contractDataObject.setPva(resultSet
					.getString(DomainConstants.PROV_ARNG));
			contractDataObject.setProductFamily(resultSet
					.getString(DomainConstants.PRODUCT_FAMILY));
			contractDataObject.setSensitiveBenefitIndicator(resultSet
					.getString(DomainConstants.SENSITIVE_BENEFIT_IND));
			contractDataObject.setBusinessEntity(resultSet
					.getString(DomainConstants.BUSINESS_ENTITY_LG));
			contractDataObject.setGroupStateHQ(resultSet
					.getString(DomainConstants.GROUP_STATE_HQ));
			contractDataObject.setEndDate(resultSet
					.getString(DomainConstants.END_DT_LG));
			contractDataObject.setStartDate(resultSet
					.getString(DomainConstants.START_DT_LG));
			contractDataObject.setVariableDescription(resultSet
					.getString(DomainConstants.CONT_VAR_TEXT));
			// contractDataObject.setVariableStatus(resultSet.getString(DomainConstants.VARIABLE_STATUS));
			contractDataObject.setProductCode(resultSet
					.getString(DomainConstants.PRODUCT_CODE));
			contractDataObject.setContractAdministration(resultSet
					.getString(DomainConstants.BENEFIT_YEAR_ACCUM_IND));
			/* Code change for E025 error validation- Start */
			contractDataObject.setCategoryCode(resultSet
					.getString(DomainConstants.CATAGORY_CODE));
			/* Code change for E025 error validation- End */
			// September release code change for HMO logic - Start
			contractDataObject.setContractId(resultSet
					.getString(DomainConstants.CONTRACTID));
			contractDataObject.setCorporatePlan(resultSet
					.getString(DomainConstants.CORPORATE_PLAN));
			contractDataObject.setBenefitStructure(resultSet
					.getString(DomainConstants.BENEFIT_STRUCTURE));
			// September release code change for HMO logic - End
			
			//Setting group name for contract -- SSCR 16331 Nov Release
			contractDataObject.setGroupName(resultSet
					.getString(DomainConstants.CONRACT_GROUP_NM));
			//BXNI CR35
			contractDataObject.setWPDAccumulator(resultSet.getString("WPDACCUM"));
			
			//SSCR 19537
			contractDataObject.setEb03Association(resultSet.getString("EB03_ASSN"));
			contractDataObject.setIndvdlEb03AssnIndicator(resultSet.getString("INDVDL_EB03_ASSN_IND"));
			
			contractDataObject.setHpnProduct(resultSet.getString("PRD_CDE"));
			contractDataObject.setHpnProductSDesc(resultSet.getString("PRD_SDESC"));
			return contractDataObject;
		}
	}
	
	
	/** FOR TEST ENDS***/
	/**
	 * Query for checking whether the Input provided- contractID and datesegment
	 * is valid
	 * 
	 */
	private class LGGetContractVersions extends MappingSqlQuery {
		public LGGetContractVersions(DataSource ds) {
			super(ds, getLgGetContractVersions());
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.DATE));
			compile();
		}

		public Object mapRow(ResultSet resultSet, int row) throws SQLException {
			return Integer.valueOf(resultSet.getString(DomainConstants.ROW_COUNT));
		}
	}
	
	private class LGGetContractTestVersions extends MappingSqlQuery {
		public LGGetContractTestVersions(DataSource ds) {
			super(ds, getLgGetContractTestVersions());
			declareParameter(new SqlParameter(Types.VARCHAR));
			declareParameter(new SqlParameter(Types.DATE));
			compile();
		}

		public Object mapRow(ResultSet resultSet, int row) throws SQLException {
			return Integer.valueOf(resultSet.getString(DomainConstants.ROW_COUNT));
		}
	}

	/**
	 * Query for getting the exclusion list for LG LG Contract Error Exclusion
	 * Query.
	 */
	private class LGContractErrorExclusionQuery extends MappingSqlQuery {
		public LGContractErrorExclusionQuery(DataSource ds,
				String lgContractQuery) {
			super(ds, lgContractQuery);
			compile();
		}

		/**
		 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet,
		 *      int)
		 * @param resultSet
		 * @param row
		 * @return
		 * @throws SQLException
		 */
		public Object mapRow(ResultSet resultSet, int row) throws SQLException {
			ErrorExclusionVO errorExclusionVO = new ErrorExclusionVO();
			errorExclusionVO.setErrorCode(resultSet.getString(1));
			errorExclusionVO.setPrimaryExclusion(resultSet.getString(2));
			errorExclusionVO.setPrimaryValues(resultSet.getString(3));
			errorExclusionVO.setSecondaryExclusion(resultSet.getString(4));
			errorExclusionVO.setSecondaryValues(resultSet.getString(5));
			errorExclusionVO.setExclusionCount(resultSet.getInt(6));
			return errorExclusionVO;
		}
	}

	public String getLgContractMappingInfoQuery() {
		return lgContractMappingInfoQuery;
	}

	public String getLgContractTestMappingInfoQuery() {
		return lgContractTestMappingInfoQuery;
	}

	public String getLgGetContractVersions() { 
		return lgGetContractVersions;
	}

	
	/*public String getLGGetContractTestVersions() {
		return lgGetContractTestVersions;
	}*/
	
	
	public String getLgGetContractTestVersions() {
		return lgGetContractTestVersions;
	}

	public void setLgGetContractTestVersions(String lgGetContractTestVersions) {
		this.lgGetContractTestVersions = lgGetContractTestVersions;
	}

	public void setLgContractTestMappingInfoQuery(
			String lgContractTestMappingInfoQuery) {
		this.lgContractTestMappingInfoQuery = lgContractTestMappingInfoQuery;
	}

	public void setLgGetContractVersions(String lgGetContractVersions) {
		this.lgGetContractVersions = lgGetContractVersions;
	}

	public void setLgContractMappingInfoQuery(String lgContractMappingInfoQuery) {
		this.lgContractMappingInfoQuery = lgContractMappingInfoQuery;
	}

	/**
	 * @return Returns the dataSource.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 *            The dataSource to set.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * The method provides the In-Network PVA Mappings List
	 * 
	 * @return
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
	 * @return
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
	 * @return
	 */
	protected List getInOutNetworkPVAMappingsForE010() {
		List pvaList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.IN_OUT_NETWORK_PVA,
				DomainConstants.PROPERTY_FILE_NAME);

		return pvaList;
	}

	/**
	 * The method provides the In-Network PVA Mappings List
	 * 
	 * @return
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
	 * @return
	 */
	protected List getOutNetworkPVAMappings() {
		List pvaList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.OUT_NETWORK_PVA,
				DomainConstants.PROPERTY_FILE_NAME);

		return pvaList;
	}

	/**
	 * The method provides the exclusions list for E022 productCodes
	 * 
	 * @return
	 */
	protected List getProductCodesToBeExcludedForE022() {
		List productCodesList = SimulationResourceBundle.getResourceBundle(
				DomainConstants.EXCUSIONS_PRODCODES_WPD,
				DomainConstants.PROPERTY_FILE_NAME);

		return productCodesList;
	}


	
	/**
	 * @param contractID
	 * @param productList
	 * @return ISG Contract exclusions fetch query.
	 */
	private String getLGContractExclusionQuery(String contractID, String productCode) {
		StringBuffer isgQuery = new StringBuffer("");
		isgQuery.append("SELECT ERROR_CD, EXCLUSION_TYPE_PRIMARY, EXCLUSION_VAL_PRIMARY, EXCLUSION_TYPE_SECONDARY, " +
				"EXCLUSION_VAL_SECONDARY, EXCLUSION_COUNT");
		isgQuery.append(" FROM BX_ERROR_EXCLUSION");
		isgQuery.append(" WHERE");
		isgQuery.append(" (EXCLUSION_TYPE_PRIMARY='VARIABLE') ");
		isgQuery.append(" OR (EXCLUSION_TYPE_PRIMARY ='CONTRACT' and (EXCLUSION_VAL_PRIMARY LIKE '%" + contractID +"%'))");
		isgQuery.append(" OR (EXCLUSION_TYPE_PRIMARY ='PRODUCT' and (EXCLUSION_VAL_PRIMARY LIKE '%" + productCode +"%'))");
		return isgQuery.toString();
	}
	public List getCopayIndicatorVariables() {
		List copayIndicatorVariablesList = SimulationResourceBundle.getResourceBundle(DomainConstants.COP_IND_VAR_LG_E034,
				DomainConstants.PROPERTY_FILE_NAME);
		return copayIndicatorVariablesList;
	}

	public List getDedIndicatorVariables() {
		List dedIndicatorVariablesList = SimulationResourceBundle.getResourceBundle(DomainConstants.DED_IND_VAR_LG_E034,
				DomainConstants.PROPERTY_FILE_NAME);
		return dedIndicatorVariablesList;
	}
	
	public boolean isInactiveContract(String contractId) throws EBXException, Exception{
		String query = "SELECT COUNT(CNTRCT_ID) AS CNT FROM CONTRACT_STATUS WHERE UPPER(TRIM(CNTRCT_ID)) = '"+contractId.trim().toUpperCase()+"' AND TRIM(STATUS) ='INACTIVE'";
		InactiveContractQuery inactiveContractQuery = new InactiveContractQuery(getDataSource(),query);
		List list = inactiveContractQuery.execute();
		Integer count = (Integer)list.get(0);
		if(count.intValue() == 0){
			return false;
		}
		return true;
	}
	 /**
     * @author UST Global - www.ust-global.com <br />
     * @version $Id: $
     */
    private class InactiveContractQuery extends MappingSqlQuery {
        public InactiveContractQuery(DataSource ds, String ewpdExclusionsQuery) {
            super(ds, ewpdExclusionsQuery);
            compile();
        }

        /**
         * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet, int)
         * @param resultSet
         * @param row
         * @return
         * @throws SQLException
         * Map row.
         */
        public Object mapRow(ResultSet resultSet, int row) throws SQLException {
			 Integer count = Integer.valueOf(resultSet.getInt("CNT"));//Integer.parseInt(s)
			   return count;
        }
    }
    // BXNI 
    
    private String lgStartDatesProduction;
    private String lgStartDatesTest;

	/**
	 * @return the lgStartDatesProduction
	 */
	public String getLgStartDatesProduction() {
		return lgStartDatesProduction;
	}


	/**
	 * @param lgStartDatesProduction the lgStartDatesProduction to set
	 */
	public void setLgStartDatesProduction(String lgStartDatesProduction) {
		this.lgStartDatesProduction = lgStartDatesProduction;
	}


	/**
	 * @return the lgStartDatesTest
	 */
	public String getLgStartDatesTest() {
		return lgStartDatesTest;
	}


	/**
	 * @param lgStartDatesTest the lgStartDatesTest to set
	 */
	public void setLgStartDatesTest(String lgStartDatesTest) {
		this.lgStartDatesTest = lgStartDatesTest;
	}


	/* (non-Javadoc)
	 * @see com.wellpoint.ets.ebx.simulation.repository.ContractInfoRepository#getStartDates(java.lang.String)
	 */
	@Override
	public List<String> getStartDates(String contractId, String enviornment) {
		if (enviornment.equalsIgnoreCase(DomainConstants.PRODUCTION)) {
			return getStartDatesForProduction(contractId);
		} else {
			return getStartDatesForTest(contractId);
		}
	}

	/**
	 *  Returns start dates of LG on test environment.
	 * @param contractId
	 * @return
	 */
	private List<String> getStartDatesForTest(String contractId) {
		FetchLGStartDatesQueryTest fetchLGStartDatesQueryTest = new FetchLGStartDatesQueryTest(dataSource,getLgStartDatesTest());
		Object[] inputParams = new Object[] { contractId };
		List<String> startDates = fetchLGStartDatesQueryTest.execute(inputParams);
		return startDates;
	}
	private static final class FetchLGStartDatesQueryTest extends MappingSqlQuery {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		public FetchLGStartDatesQueryTest(DataSource dataSource,
				String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
		}

		@Override
		protected Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			return simpleDateFormat.format(resultSet.getDate("START_DT"));
		}
		
	}


	/**
	 * Returns start dates of LG on production environment.
	 * @param contractId
	 * @return
	 */
	private List<String> getStartDatesForProduction(String contractId) {
		FetchLGStartDatesQueryProduction fetchLGStartDatesQueryProduction = new FetchLGStartDatesQueryProduction(dataSource,getLgStartDatesProduction());
		Object[] inputParams = new Object[] { contractId };
		List<String> startDates = fetchLGStartDatesQueryProduction.execute(inputParams);
		return startDates;
	}


	private static final class FetchLGStartDatesQueryProduction extends MappingSqlQuery {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		public FetchLGStartDatesQueryProduction(DataSource dataSource,
				String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
		}

		@Override
		protected Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			return simpleDateFormat.format(resultSet.getDate("START_DT"));
		}
		
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.ebx.simulation.repository.ContractInfoRepository#getLatestVersion(java.lang.String)
	 */
	@Override
	public String getLatestVersion(String contractId) {
		return null;
	}

}
