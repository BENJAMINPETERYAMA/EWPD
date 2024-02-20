/*
 * <EWPDContractInfoRepositoryImpl.java>
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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.owasp.esapi.ESAPI;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorExclusionVO;
import com.wellpoint.ets.ebx.simulation.util.ErrorValidationHelper;
import com.wellpoint.ets.ebx.simulation.vo.ContractDataObjectVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractMappingVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.vo.MajorHeadingsVO;
import com.wellpoint.ets.ebx.simulation.vo.MinorHeadingsVO;
import com.wellpoint.ets.ebx.simulation.vo.PricingInfoVO;


/**
 * @author UST-GLOBAL
 * 
 * Repository class for eWPD system. This class will create the object structure hierarchy
 * with the values from the DB. The informations will contain mapping details for coded variables.
 * 
 */
public class EWPDContractInfoRepositoryImpl implements ContractInfoRepository {

    private DataSource dataSource;

    private String ewpdSPSContractMappingInfoQuery;
    private String ewpdRuleIdContractMappingInfoQuery;
    private String ewpdMessageContractMappingInfoQuery;
    private String preventiveProvisionAppliesQuery;
    private String limitRestrictionAppliesQuery;
    private String CDHPQuery;
    private String ewpdGetContractVersions;
    private String ewpdEbxGetMaxContractVersions;
    private String ewpdGetContractAdminOptionAnswer;
    private String ewpdAdminSPSContractInfoQuery;


    private boolean hcrFlag = false;
    private boolean isHCRFlag_E016And17 = false;
    private boolean isCDHP = false;
    private String contractAdminOptionAnswer;

	/**
	 * Comment for <code>logger</code>
	 */
	private static Logger logger = Logger.getLogger(EWPDContractInfoRepositoryImpl.class);
	
    /**
     * This method is used for creating the contract object with the contract information along
     * with mapping details obtained from database for eWPD system.
     * 
     * @param ContractVO
     * @return contractVOList
     * @throws EBXException
     * 
     */
	public List getContractInfo(ContractVO contract, boolean eBxReportFlag) throws EBXException,Exception{
		SimpleDateFormat dateFormat= null;
		List contractVOList = new ArrayList();

		dateFormat= new SimpleDateFormat(DomainConstants.DATE_FORMAT);
		java.sql.Date startDate = null;
		try {
			startDate = new java.sql.Date(dateFormat.parse(contract.getEffectiveDate()).getTime());
		} catch (ParseException e) {
			throw new EBXException(DomainConstants.DATE_FORMAT_EXCEPTION);
		}
		/**
		 * Flag check for ebx contracts version numbere
		 *//*
		if(eBxReportFlag) {
			Object[] inParams = new Object[]{contract.getContractId(), startDate};
			EWPDEbxGetMaxContractVersions ebxGetMaxContractVersions = new EWPDEbxGetMaxContractVersions(dataSource);
			long ebxGetMaxContractVersionsStartTime = System.currentTimeMillis();
			List maxVersion = ebxGetMaxContractVersions.execute(inParams);
			long ebxGetMaxContractVersionsEndTime = System.currentTimeMillis();
			logger.info("Time taken for EWPDEbxGetMaxContractVersions = "+ (ebxGetMaxContractVersionsEndTime - ebxGetMaxContractVersionsStartTime));
			if(null != maxVersion )
			{
				if(null != maxVersion.get(0)) {
					Integer version = new Integer((String) maxVersion.get(0));
					int maxContractVersion = version.intValue();
					contract.setVersion(maxContractVersion);
				}
				else {
					throw new EBXException(DomainConstants.NO_MATCH_FOUND);
				}

			}
			else {
				throw new EBXException(DomainConstants.NO_MATCH_FOUND);
			}

		} */

		/**
		 * Input Parameters for the query.
		 */
		Object[] inputParams = new Object[]{contract.getContractId(),Integer.valueOf(contract.getVersion()),
				startDate};

		//to check whether the given contractID,version and date is valid
		EWPDGetContractVersions ewpdGetContractVersions  = new EWPDGetContractVersions(dataSource);
		long ewpdGetContractVersionsStartTime = System.currentTimeMillis();
		List versions = ewpdGetContractVersions.execute(inputParams);
		long ewpdGetContractVersionsEndTime = System.currentTimeMillis();
		logger.info("Time taken for EWPDGetContractVersions = "+ (ewpdGetContractVersionsEndTime - ewpdGetContractVersionsStartTime));

		int rowCount = ((Integer)versions.get(0)).intValue();
		if(rowCount==0){
			throw new EBXException(DomainConstants.NO_MATCH_FOUND);
		}



		List contractDataList= new ArrayList();


		/**
		 * Checking whether the contract is HCR for E018 error code condition
		 * and setting the flag
		 */
		PreventiveProvisionAppliesQuery preventiveProvisionAppliesQuery  = new PreventiveProvisionAppliesQuery(dataSource);
		long preventiveProvisionAppliesQueryStartTime = System.currentTimeMillis();

		List questionCountList18 = preventiveProvisionAppliesQuery.execute(inputParams);
		long preventiveProvisionAppliesQueryEndTime = System.currentTimeMillis();
		logger.info("Time taken for PreventiveProvisionAppliesQuery = "+ (preventiveProvisionAppliesQueryEndTime - preventiveProvisionAppliesQueryStartTime));
		if (((Integer)questionCountList18.get(0)).intValue() > 0) {
			hcrFlag = true;
		} else {
			hcrFlag = false;
		}
		/**
		 * Checking whether the contract is HCR for E016, E017 error code condition
		 * and setting the flag
		 */
		LimitRestrictionAppliesQuery limitRestrictionAppliesQuery  = new LimitRestrictionAppliesQuery(dataSource);
		long limitRestrictionAppliesQueryStartTime = System.currentTimeMillis();
		List questionCountList = limitRestrictionAppliesQuery.execute(inputParams);
		long limitRestrictionAppliesQueryEndTime = System.currentTimeMillis();
		logger.info("Time taken for LimitRestrictionAppliesQuery = "+ (limitRestrictionAppliesQueryEndTime - limitRestrictionAppliesQueryStartTime));

		if (((Integer)questionCountList.get(0)).intValue()>0) {
			isHCRFlag_E016And17 = true;
		} else {
			isHCRFlag_E016And17 = false;
		}
		//to check whether the contract is CDHP
		
		CDHPQuery cDHPQuery  = new CDHPQuery(dataSource);
		long cDHPQueryStartTime = System.currentTimeMillis();
		List countList = cDHPQuery.execute(inputParams);
		long cDHPQueryEndTime = System.currentTimeMillis();
		logger.info("Time taken for CDHPQuery = "+ (cDHPQueryEndTime - cDHPQueryStartTime));
		if(((Integer)countList.get(0)).intValue()>0){
			isCDHP = true;
		}
		
		//to get the admin option value "CALENDAR OR BENEFIT YEAR" at the contract level
		ContractAdminOptionsQuery contractAdminOptionsQuery  = new ContractAdminOptionsQuery(dataSource);
		long contractAdminOptionsQueryStartTime = System.currentTimeMillis();

		List answerList = contractAdminOptionsQuery.execute(inputParams);
		long contractAdminOptionsQueryEndTime = System.currentTimeMillis();
		logger.info("Time taken for ContractAdminOptionsQuery = "+ (contractAdminOptionsQueryEndTime - contractAdminOptionsQueryStartTime));

		if(null != answerList && answerList.size()>0){ 
			contractAdminOptionAnswer = (String)answerList.get(0);   

		}     

		
		//Getting Contract Info With Rule Mappings.
		EWPDRuleContractMappingInfoQuery ewpdRuleContractMappingInfoQuery  = new EWPDRuleContractMappingInfoQuery(dataSource);
		long ewpdRuleContractMappingInfoQueryStartTime = System.currentTimeMillis();

		contractDataList = ewpdRuleContractMappingInfoQuery.execute(
				new Object[]{contract.getContractId(),
						Integer.valueOf(contract.getVersion()),
						startDate,contract.getContractId(),
						Integer.valueOf(contract.getVersion()),
						startDate,contract.getContractId(),
						Integer.valueOf(contract.getVersion()),
						startDate,contract.getContractId(),
						Integer.valueOf(contract.getVersion()),
						startDate});
		long ewpdRuleContractMappingInfoQueryEndTime = System.currentTimeMillis();
		
		logger.info("Time taken for EWPDRuleContractMappingInfoQuery = "+ (ewpdRuleContractMappingInfoQueryEndTime - ewpdRuleContractMappingInfoQueryStartTime));
		logger.info(getEwpdRuleIdContractMappingInfoQuery());
		
		Map benefitRuleMap = createContractObjectRule(contractDataList); 

		//Getting Contract Info With SPS Mappings.
		Object[] inputParams_SPS = new Object[]{contract.getContractId(),Integer.valueOf(contract.getVersion()),
				startDate,contract.getContractId(),Integer.valueOf(contract.getVersion()),
				startDate,contract.getContractId(),Integer.valueOf(contract.getVersion()),
				startDate,contract.getContractId(),Integer.valueOf(contract.getVersion()),
				startDate}; 
		EWPDSPSContractMappingInfoQuery ewpdSPSContractMappingInfoQuery  = new EWPDSPSContractMappingInfoQuery(dataSource);
		long ewpdSPSContractMappingInfoQueryStartTime = System.currentTimeMillis();
		contractDataList = ewpdSPSContractMappingInfoQuery.execute(inputParams_SPS);
		long ewpdSPSContractMappingInfoQueryEndTime = System.currentTimeMillis();
		logger.info("Time taken for EWPDSPSContractMappingInfoQuery = "+ (ewpdSPSContractMappingInfoQueryEndTime - ewpdSPSContractMappingInfoQueryStartTime));
		logger.info(getEwpdSPSContractMappingInfoQuery());
		
		ContractVO contractDataObjectSPS = createContractObject(contractDataList,benefitRuleMap);
		
		// Sets HMO flag - SSCR 16331 Nov Release change.
    	List<PricingInfoVO> pricingInfoList = getPricingInfoForEWPDContract(contract);
    	contractDataObjectSPS = ErrorValidationHelper.setHMOFlagForEWPD(contractDataObjectSPS, pricingInfoList);
		logger.info("HMO Contract Flag is >>>"+ contractDataObjectSPS.isFlagHMO());

		// To Fetch the Admin method SPS for EB06 auto-population.
		EWPDAdminSPSContractInfoQuery ewpdAdminSPSContractInfo  = new EWPDAdminSPSContractInfoQuery(dataSource);
		contractDataList = ewpdAdminSPSContractInfo.execute(new Object[]{contract.getContractId(),Integer.valueOf(contract.getVersion()),
				startDate});
		logger.info("getEwpdAdminSPSContractInfoQuery="+getEwpdAdminSPSContractInfoQuery());
		 creatContractAdminSPS(contractDataObjectSPS,contractDataList);
		/**
		 * Setting contract id, system,version and date
		 */
		contractDataObjectSPS.setContractId(contract.getContractId());
		contractDataObjectSPS.setSystem(contract.getSystem());
		contractDataObjectSPS.setVersion(contract.getVersion());
		contractDataObjectSPS.setSpsCodedValue(ewpdSPSContractMappingInfoQuery.spsCodedValue); //sets the map to ContractVO
		
		SimpleDateFormat formatter = new SimpleDateFormat(DomainConstants.DATE_FORMAT_EXPIRY);
		String expiryDate = null;
		if(null != contractDataObjectSPS.getExpiryDate()) {
			Date date = formatter.parse(contractDataObjectSPS.getExpiryDate());
			expiryDate =  dateFormat.format(date);
		}
		String effectDate = null;
		if(null != contractDataObjectSPS.getEffectiveDate()) {
			Date date = formatter.parse(contractDataObjectSPS.getEffectiveDate());
			effectDate =  dateFormat.format(date);
		}
		contractDataObjectSPS.setExpiryDate(expiryDate);
		contractDataObjectSPS.setEffectiveDate(effectDate);

		//Getting contract info with Custom Message Mappings
		EWPDMessageContractMappingInfoQuery ewpdMsgContractMappingInfoQuery = new EWPDMessageContractMappingInfoQuery(dataSource);
		long ewpdMsgContractMappingInfoQueryStartTime = System.currentTimeMillis();
		contractDataList = ewpdMsgContractMappingInfoQuery.execute(new Object[]{contract.getContractId(),Integer.valueOf(contract.getVersion()),
				startDate, contract.getContractId(),Integer.valueOf(contract.getVersion()),
				startDate});
		long ewpdMsgContractMappingInfoQueryEndTime = System.currentTimeMillis();
		logger.info("Time taken for EWPDMessageContractMappingInfoQuery = "+ (ewpdMsgContractMappingInfoQueryEndTime - ewpdMsgContractMappingInfoQueryStartTime));
		logger.info(getEwpdMessageContractMappingInfoQuery());
		
		ContractVO contractDataObject = createMsgContractObject(contractDataList,contractDataObjectSPS);

		if (!eBxReportFlag) {
			long exclusionStartTime = System.currentTimeMillis();
			String ewpdContractExclusionsQuery = getEWPDContractExclusionQuery(
					contractDataObject.getContractId(), contractDataObject
							.getProductCode());
			logger
					.info("The exclusion query is:"
							+ESAPI.encoder().encodeForHTML(ewpdContractExclusionsQuery));
			//List contractExclusions = new ArrayList();
			EWPDContractErrorExclusionQuery contractErrorExclusionQuery = new EWPDContractErrorExclusionQuery(
					dataSource, ewpdContractExclusionsQuery);
			List contractExclusions = contractErrorExclusionQuery.execute();
			contractDataObject.setErrorExclusionVOList(contractExclusions);
			long exclusionEndTime = System.currentTimeMillis();
			logger.info("Time taken for EWPDContractErrorExclusionQuery = "+ (exclusionEndTime - exclusionStartTime));

			
		}
		contractVOList.add(contractDataObject);
		//contractVOList.add(contractDataObjectSPS); 
		return contractVOList;
	}

	/**
	 * This method will add the admin method SPS to minor heading object.
	 * @param contractDataObjectSPS
	 * @param contractDataList
	 */
    private void creatContractAdminSPS(ContractVO contractDataObjectSPS,
			List contractDataList) {
		
    	Map majorHeadingMap = contractDataObjectSPS.getMajorHeadings();
    	Iterator iterator = contractDataList.iterator();
    	String majorHeading;
    	String minorHeading;
    	Map minorHeadingMap;
    	while (iterator.hasNext()) {
    		ContractDataObjectVO contractDataObjectVO = (ContractDataObjectVO)iterator.next();
    		majorHeading = contractDataObjectVO.getMajorHeading();
    		if (majorHeadingMap.containsKey(majorHeading)) {
    			MajorHeadingsVO majorHeadingsVO = (MajorHeadingsVO)majorHeadingMap.get(majorHeading);
    			minorHeading = contractDataObjectVO.getMinorHeading();
    			minorHeadingMap = majorHeadingsVO.getMinorHeadings();
    			if (minorHeadingMap.containsKey(minorHeading)) {
    				 MinorHeadingsVO  minorHeadingsVO = (MinorHeadingsVO) minorHeadingMap.get(minorHeading);
    				 Map adminMethodSPS = new HashMap();    				 
    				 adminMethodSPS.put(contractDataObjectVO.getSpsId(), contractDataObjectVO.getLineValue());
    				 minorHeadingsVO.setAdminMethodSPS(adminMethodSPS);
    			}
    		}
    	}
	}
    /**
     * The method will create the Contract object structure hierarchy with the
     * SPS Mapping values for the Line objects and the Rule Mappings for the Benefit
     * @param contractDataList
     * @param benefitRuleMap
     * @return
     */
    private ContractVO createContractObject(List contractDataList,Map benefitRuleMap){
        ContractVO contract = new ContractVO();
        Map majorHeadingsMap = new HashMap();
        Map minorHeadingsMap = null;
        MajorHeadingsVO  majorHeadings = null;
        Map mappingsMap = null;
        Iterator resulSetIterator  = contractDataList.iterator();

        while (resulSetIterator.hasNext()) {
            ContractDataObjectVO contractDataObject = (ContractDataObjectVO) resulSetIterator.next();

            //setting exclusion flags in the contract object
            contract = setExclusionFlags(contractDataObject, contract);
            //setting the answer coded for the admin option "CALENDAR OR BENEFIT YEAR"
            contract.setAnswerCalYearOrBenYear(contractAdminOptionAnswer);
            //Setting the line of business for the Contract
            contract.setLineOfBusiness(contractDataObject.getLineOfBusiness());

            String majorHeadingDesc = contractDataObject.getMajorHeading();
            contract.setProductFamily(contractDataObject.getProductFamily());
            //Setting the business group for the Contract
            if(null == contract.getBusinessGroup())
            contract.setBusinessGroup(contractDataObject.getBusinessGroup());

            if (null == contract.getBusinessEntity()) {
                contract.setBusinessEntity(contractDataObject.getBusinessEntity());
            }
            if (null == contract.getExpiryDate()) {
                contract.setExpiryDate(contractDataObject.getEndDate());
            }
            if (null == contract.getEffectiveDate()) {
                contract.setEffectiveDate(contractDataObject.getStartDate());
            }
           contract.setProductCode(contractDataObject.getProductCode());
            //If this is a new Major Heading
            if (!majorHeadingsMap.containsKey(majorHeadingDesc)) {
                majorHeadings = new MajorHeadingsVO();
                minorHeadingsMap = new HashMap();
                MinorHeadingsVO  minorHeadings = new MinorHeadingsVO();
                mappingsMap = new HashMap();

                majorHeadings.setDescriptionText(majorHeadingDesc);
                majorHeadingsMap.put(majorHeadingDesc, majorHeadings);

                String minorHeadingDesc = contractDataObject.getMinorHeading();

                if (null != contractDataObject.getMappedVariableId()) { //If SPS have Mapping
                    //creating a Mapping
                    Mapping mapping = new ContractMappingVO();
                    SPSId spsObject =  new SPSId();
                    ContractMappingVO contractMappingVO = new ContractMappingVO();

                    spsObject.setSpsId(contractDataObject.getVariableId());
                    spsObject.setSpsDesc(contractDataObject.getSpsDescription());
                    spsObject.setLevelDesc(contractDataObject.getLevelDescription());
                    spsObject.setLineValue(contractDataObject.getVariableValue());
                    spsObject.setLinePVA(contractDataObject.getPva());
                    spsObject.setLineDataType(contractDataObject.getFormat());
                    mapping.setSpsId(spsObject);

                    //Populating the Mapping object with HIPAA elements
                    Mapping mappingObj = getMapping(contractDataObject, mapping, false);
                    /* Code for eBX - start*/
                    //Populating Mapping object with Accum SPSID
                    HippaSegment accum = new HippaSegment();
                    List accumSelectedValues =  new ArrayList();
					HippaCodeValue code = new HippaCodeValue();
					code.setValue(contractDataObject.getAccumrSPSParameterId());
					accumSelectedValues.add(code);
					accum.setHippaCodeSelectedValues(accumSelectedValues);
					mappingObj.setAccum(accum);
					/*Code change done for eBX - start*/
		     		if (null != contractDataObject.getMappingComplete()) {
		     			/*if(DomainConstants.Y.equalsIgnoreCase(contractDataObject.getMappingComplete()))
		     			{
		     				//Here "N" is set because the report column indicates "Not finalized"
		     				mappingObj.setMappingComplete(DomainConstants.N);
		     			}
		     			else
		     			{
		     				mappingObj.setMappingComplete(DomainConstants.Y);
		     			}*/
		     			/*Code change for enhancement requirement--Start*/
		     			mapping.setMappingComplete(contractDataObject.getMappingComplete());
		     			/*Code change for enhancement requirement--End*/
		     		}
		     		mappingObj.setSensitiveBenefitIndicator(contractDataObject.getSensitiveBenefitIndicator());
					/* Code for eBX - end*/
		     		mappingObj.setVariableMappingStatus(contractDataObject.getMappingStatus());
		     		mappingObj.setMapped(true);
		     		// Setting Tier Description - July Release
		     		if (null != contractDataObject.getTierDescription()
							&& !DomainConstants.EMPTY.equals(contractDataObject
									.getTierDescription())) {
                    	contractMappingVO.setTierDescription(
						contractDataObject.getTierDescription());
                    	mappingObj.setContractMapping(contractMappingVO);
		     			mappingsMap.put(contractDataObject.getVariableId().concat(contractDataObject.getTierDescription()), mappingObj);
		     		}
		     		else {
		     			mappingsMap.put(contractDataObject.getVariableId(), mappingObj);
		     		}
                    minorHeadings.setMappings(mappingsMap);
                } else {	//If SPS does not have any Mapping
                	//creating a Mapping
                    Mapping mapping = new ContractMappingVO();
                    SPSId spsObject =  new SPSId();
                    ContractMappingVO contractMappingVO = new ContractMappingVO();
                    spsObject.setSpsId(contractDataObject.getVariableId());
                    spsObject.setSpsDesc(contractDataObject.getSpsDescription());
                    spsObject.setLevelDesc(contractDataObject.getLevelDescription());
                    spsObject.setLineValue(contractDataObject.getVariableValue());
                    spsObject.setLinePVA(contractDataObject.getPva());
                    spsObject.setLineDataType(contractDataObject.getFormat());
                    mapping.setSpsId(spsObject);
                 // Setting Tier Description - July Release
		     		if (null != contractDataObject.getTierDescription()
							&& !DomainConstants.EMPTY.equals(contractDataObject
									.getTierDescription())) {
                    	contractMappingVO.setTierDescription(
						contractDataObject.getTierDescription());
                    	mapping.setContractMapping(contractMappingVO);
		     			mappingsMap.put(contractDataObject.getVariableId().concat(contractDataObject.getTierDescription()), mapping);
		     		}
		     		else {
		     			mappingsMap.put(contractDataObject.getVariableId(), mapping);
		     		}
                    minorHeadings.setMappings(mappingsMap);
                }
                minorHeadings.setDescriptionText(minorHeadingDesc);

                String mapKey = majorHeadingDesc + DomainConstants.TILDA_CHAR + minorHeadingDesc;
                /**
                 * Benefit Rule Map contains benefitComp~Benefit as the key
                 * and the corresponding Rule mapping object as the value
                 * If present, then add the rule mapping object to the MinorHeading/Benefit
                 *
                 */
                if (benefitRuleMap.containsKey(mapKey)) {
                    Mapping ruleMapping = (Mapping) benefitRuleMap.get(mapKey);
                    minorHeadings.setRuleMapping(ruleMapping);
                }
                minorHeadingsMap.put(minorHeadingDesc, minorHeadings);
                MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO) majorHeadingsMap.
                get(contractDataObject.getMajorHeading());
                majorHeadingFromMap.setMinorHeadings(minorHeadingsMap);
            }
            //If this is an existing Major Heading
            else {
                MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO) majorHeadingsMap.
                get(contractDataObject.getMajorHeading());
                Map minorHeadingsMp = majorHeadingFromMap.getMinorHeadings();
                //If this is a new Minor Heading
                if (!minorHeadingsMp.containsKey(contractDataObject.getMinorHeading())) {
                    MinorHeadingsVO  minorHeadings = new MinorHeadingsVO();
                    Map mappingsMapWithoutMapping = new HashMap();

                    String minorHeadingDesc = contractDataObject.getMinorHeading();

                    if (null != contractDataObject.getMappedVariableId()) { //If SPS have Mapping
                        Mapping mapping = new ContractMappingVO();
                        SPSId spsObject =  new SPSId();
                        ContractMappingVO contractMappingVO = new ContractMappingVO();
                        mappingsMap = new HashMap();

                        spsObject.setSpsId(contractDataObject.getVariableId());
                        spsObject.setLevelDesc(contractDataObject.getLevelDescription());
                        spsObject.setSpsDesc(contractDataObject.getSpsDescription());
                        spsObject.setLineValue(contractDataObject.getVariableValue());
                        spsObject.setLinePVA(contractDataObject.getPva());
                        spsObject.setLineDataType(contractDataObject.getFormat());

                        mapping.setSpsId(spsObject);
                        Mapping mappingWithHIPAA = getMapping(contractDataObject, mapping, false);
                        /* Code for eBX - start*/
                        //Populating Mapping object with Accum SPSID
                        HippaSegment accum = new HippaSegment();
                        List accumSelectedValues =  new ArrayList();
    					HippaCodeValue code = new HippaCodeValue();
    					code.setValue(contractDataObject.getAccumrSPSParameterId());
    					accumSelectedValues.add(code);
    					accum.setHippaCodeSelectedValues(accumSelectedValues);
    					mappingWithHIPAA.setAccum(accum);
    					/*Code change done for eBX - start*/
    		     		if (null != contractDataObject.getMappingComplete()) {
    		     			/*if(DomainConstants.Y.equalsIgnoreCase(contractDataObject.getMappingComplete()))
    		     			{
    		     				//Here "N" is set because the report column indicates "Not finalized"
    		     				mappingWithHIPAA.setMappingComplete(DomainConstants.N);
    		     			}
    		     			else
    		     			{
    		     				mappingWithHIPAA.setMappingComplete(DomainConstants.Y);
    		     			}*/
    		     			/*Code change for enhancement requirement--Start*/
    		     			mapping.setMappingComplete(contractDataObject.getMappingComplete());
    		     			/*Code change for enhancement requirement--End*/
    		     		}
    		     		mappingWithHIPAA.setSensitiveBenefitIndicator(contractDataObject.getSensitiveBenefitIndicator());
    					/* Code for eBX - end*/
    		     		mappingWithHIPAA.setVariableMappingStatus(contractDataObject.getMappingStatus());
    		     		mappingWithHIPAA.setMapped(true);
    		     		// Setting Tier Description - July Release
    		     		if (null != contractDataObject.getTierDescription()
    							&& !DomainConstants.EMPTY.equals(contractDataObject
    									.getTierDescription())) {
                        	contractMappingVO.setTierDescription(
    						contractDataObject.getTierDescription());
                        	mappingWithHIPAA.setContractMapping(contractMappingVO);
    		     			mappingsMap.put(contractDataObject.getVariableId().concat(contractDataObject.getTierDescription()), mappingWithHIPAA);
    		     		}
    		     		else {
    		     			mappingsMap.put(contractDataObject.getVariableId(), mappingWithHIPAA);
    		     		}
                        minorHeadings.setMappings(mappingsMap);
                    } else { //If SPS does not have any Mapping
                    	Mapping mapping = new ContractMappingVO();
                        SPSId spsObject =  new SPSId();
                        ContractMappingVO contractMappingVO =  new ContractMappingVO();
                        spsObject.setSpsId(contractDataObject.getVariableId());
                        spsObject.setSpsDesc(contractDataObject.getSpsDescription());
                        spsObject.setLevelDesc(contractDataObject.getLevelDescription());
                        spsObject.setLineValue(contractDataObject.getVariableValue());
                        spsObject.setLinePVA(contractDataObject.getPva());
                        spsObject.setLineDataType(contractDataObject.getFormat());
                        mapping.setSpsId(spsObject);
                        // Setting Tier Description - July Release
    		     		if (null != contractDataObject.getTierDescription()
    							&& !DomainConstants.EMPTY.equals(contractDataObject
    									.getTierDescription())) {
                        	contractMappingVO.setTierDescription(
    						contractDataObject.getTierDescription());
                        	mapping.setContractMapping(contractMappingVO);
                        	mappingsMapWithoutMapping.put(contractDataObject.getVariableId().concat(contractDataObject.getTierDescription()), mapping);
    		     		}
    		     		else {
    		     			mappingsMapWithoutMapping.put(contractDataObject.getVariableId(), mapping);
    		     		}
                        minorHeadings.setMappings(mappingsMapWithoutMapping);
                    }
                    String mapKey = majorHeadingFromMap.getDescriptionText() + DomainConstants.TILDA_CHAR + minorHeadingDesc;
                    if (benefitRuleMap.containsKey(mapKey)) {
                        Mapping ruleMapping = (Mapping) benefitRuleMap.get(mapKey);
                        minorHeadings.setRuleMapping(ruleMapping);
                    }
                    minorHeadings.setDescriptionText(minorHeadingDesc);
                    minorHeadingsMp.put(minorHeadingDesc, minorHeadings);
                }
                //If this is an existing Minor Heading
                else {
                    MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO) minorHeadingsMp.
                    get(contractDataObject.getMinorHeading());
                    Map mappingsMp = minorHeadingFromMap.getMappings();
                    if (null != mappingsMp){
                        if (null != contractDataObject.getMappedVariableId()) { //If SPS have Mapping
                            if (!mappingsMp.containsKey(contractDataObject.getVariableId())) { //if Mapping doesnt exist
                                Mapping mapping = new ContractMappingVO();
                                SPSId spsObject =  new SPSId();
                                ContractMappingVO contractMappingVO = new ContractMappingVO();
                                spsObject.setSpsId(contractDataObject.getVariableId());
                                spsObject.setLevelDesc(contractDataObject.getLevelDescription());
                                spsObject.setSpsDesc(contractDataObject.getSpsDescription());
                                spsObject.setLineValue(contractDataObject.getVariableValue());
                                spsObject.setLinePVA(contractDataObject.getPva());
                                spsObject.setLineDataType(contractDataObject.getFormat());
                                mapping.setSpsId(spsObject);
                                Mapping mappingObj = getMapping(contractDataObject, mapping, false);
                                /* Code for eBX - start*/
                                //Populating Mapping object with Accum SPSID
                                HippaSegment accum = new HippaSegment();
                                List accumSelectedValues =  new ArrayList();
            					HippaCodeValue code = new HippaCodeValue();
            					code.setValue(contractDataObject.getAccumrSPSParameterId());
            					accumSelectedValues.add(code);
            					accum.setHippaCodeSelectedValues(accumSelectedValues);
            					mappingObj.setAccum(accum);
            					/*Code change done for eBX - start*/
            		     		if (null != contractDataObject.getMappingComplete()) {

            		     			/*if(DomainConstants.Y.equalsIgnoreCase(contractDataObject.getMappingComplete()))
            		     			{
            		     				//Here "N" is set because the report column indicates "Not finalized"
            		     				mappingObj.setMappingComplete(DomainConstants.N);
            		     			}
            		     			else
            		     			{
            		     				mappingObj.setMappingComplete(DomainConstants.Y);
            		     			}*/
            		     			/*Code change for enhancement requirement--Start*/
            		     			mapping.setMappingComplete(contractDataObject.getMappingComplete());
            		     			/*Code change for enhancement requirement--End*/
            		     		}
            		     		mappingObj.setSensitiveBenefitIndicator(contractDataObject.getSensitiveBenefitIndicator());
            		     		mappingObj.setVariableMappingStatus(contractDataObject.getMappingStatus());
            					/* Code for eBX - end*/
            		     		mappingObj.setMapped(true);
            		     		// Setting Tier Description - July Release
            		     		if (null != contractDataObject.getTierDescription()
            							&& !DomainConstants.EMPTY.equals(contractDataObject
            									.getTierDescription())) {
                                	contractMappingVO.setTierDescription(
            						contractDataObject.getTierDescription());
                                	mappingObj.setContractMapping(contractMappingVO);
                                	mappingsMp.put(contractDataObject.getVariableId().concat(contractDataObject.getTierDescription()), mappingObj);
            		     		}
            		     		else {
            		     			mappingsMp.put(contractDataObject.getVariableId(), mappingObj);
            		     		}
                            }
                            else { //if Mapping exists in the Map
            		     		if (null != contractDataObject.getTierDescription()
            							&& !DomainConstants.EMPTY.equals(contractDataObject
            									.getTierDescription())) {
            		     			Mapping mappingFromMap = (Mapping) mappingsMp.get(contractDataObject.getVariableId().concat(contractDataObject.getTierDescription()));
            		     			if(null != mappingFromMap){
            		     				Mapping mappingObj = getMapping(contractDataObject, mappingFromMap, false);                                  
            		     				mappingObj.setMapped(true);
            		     				minorHeadingFromMap.getMappings().put(contractDataObject.getVariableId().concat(contractDataObject.getTierDescription()), mappingObj);
                                    }
            		     		}
            		     		else {
            		     			Mapping mappingFromMap = (Mapping) mappingsMp.get(contractDataObject.getVariableId());
            		     			if(null != mappingFromMap){
            		     				Mapping mappingObj = getMapping(contractDataObject, mappingFromMap, false);
            		     				mappingObj.setMapped(true);
            		     				minorHeadingFromMap.getMappings().put(contractDataObject.getVariableId(), mappingObj);
            		     			}
            		     		}
                            }
                        } else { //If SPS does not have any Mapping
                        	//creating a Mapping
                            Mapping mapping = new ContractMappingVO();
                            SPSId spsObject =  new SPSId();
                            ContractMappingVO contractMappingVO = new ContractMappingVO();

                            spsObject.setSpsId(contractDataObject.getVariableId());
                            spsObject.setSpsDesc(contractDataObject.getSpsDescription());
                            spsObject.setLevelDesc(contractDataObject.getLevelDescription());
                            spsObject.setLineValue(contractDataObject.getVariableValue());
                            spsObject.setLinePVA(contractDataObject.getPva());
                            spsObject.setLineDataType(contractDataObject.getFormat());
                            mapping.setSpsId(spsObject);
                            //minorHeadingFromMap.setMappings(mappingsMp);
                            // Setting Tier Description - July Release
        		     		if (null != contractDataObject.getTierDescription()
        							&& !DomainConstants.EMPTY.equals(contractDataObject
        									.getTierDescription())) {
                            	contractMappingVO.setTierDescription(
        						contractDataObject.getTierDescription());
                            	mapping.setContractMapping(contractMappingVO);
                            	mappingsMp.put(contractDataObject.getVariableId().concat(contractDataObject.getTierDescription()), mapping);
        		     			minorHeadingFromMap.getMappings().put(contractDataObject.getVariableId().concat(contractDataObject.getTierDescription()), mapping);
        		     		}
        		     		else {
        		     			mappingsMp.put(contractDataObject.getVariableId(), mapping);
        		     			minorHeadingFromMap.getMappings().put(contractDataObject.getVariableId(), mapping);
        		     		}
                        }
                    }
                    /**
                     * Benefit Rule Map contains benefitComp~Benefit as the key
                     * and the corresponding Rule mapping object as the value
                     * If present, then add the rule mapping object to the MinorHeading/Benefit
                     *
                     */
                    String mapKey = majorHeadingFromMap.getDescriptionText() + DomainConstants.TILDA_CHAR +
                    minorHeadingFromMap.getDescriptionText();
                    if (benefitRuleMap.containsKey(mapKey)) {
                        Mapping ruleMapping = (Mapping) benefitRuleMap.get(mapKey);
                        minorHeadingFromMap.setRuleMapping(ruleMapping);
                    }
                }
            }
        }
        contract.setMajorHeadings(majorHeadingsMap);

        return contract;
    }

    /**
     * The method will iterate through the EwpdRuleIdContractMappingInfoQuery query result and
     * create a HashMap with
     * key == BenefitComponent~Benefit and
     * value == Mapping object with EB03 Hippa Segment added.
     * @param contractDataList
     * @return
     */
    private Map createContractObjectRule(List contractDataList){

        Map benefitRuleMap = new HashMap();

        Iterator resulSetIterator  = contractDataList.iterator();
        String maj1 = "";
        String min1 = "";
        String mapKey = "";
        String ruleId = "";
        //int i=0;
        while (resulSetIterator.hasNext()){

            ContractDataObjectVO contractDataObject = (ContractDataObjectVO) resulSetIterator.next();

            if(contractDataObject.getMappedVariableId()!= null){
                if (maj1.equals(contractDataObject.getMajorHeading())) { //existing major heading
                    if (min1.equals(contractDataObject.getMinorHeading())) {
                        if (ruleId.equals(contractDataObject.getRuleId())) {
                            Mapping mappingAlreadyExisting = (Mapping) benefitRuleMap.get(maj1+DomainConstants.TILDA_CHAR + min1);
                            //Setting Sensitive Benefit Flag
                            mappingAlreadyExisting.setSensitiveBenefitIndicator(contractDataObject.getSensitiveBenefitIndicator());
                            mappingAlreadyExisting = getMapping(contractDataObject, mappingAlreadyExisting,true);
                            mappingAlreadyExisting.setMapped(true);
//                          To Set Not-Applicable
                            mappingAlreadyExisting.setVariableMappingStatus(contractDataObject.getMappingStatus());
                            mappingAlreadyExisting.setIndvdlEb03AssnIndicator(contractDataObject.getIndvdlEb03AssnIndicator());
                            benefitRuleMap.put(mapKey, mappingAlreadyExisting);
                        } else {
                            break;
                        }
                    } else {
                        min1 = contractDataObject.getMinorHeading();
                        mapKey = maj1+DomainConstants.TILDA_CHAR+contractDataObject.getMinorHeading();

                        Mapping ruleMappingObj = new ContractMappingVO();
                        //Setting Sensitive Benefit Flag
                        ruleMappingObj.setSensitiveBenefitIndicator(contractDataObject.getSensitiveBenefitIndicator());
                        Rule rule = new Rule();

                        ruleId = contractDataObject.getRuleId();
                        rule.setHeaderRuleId(ruleId);
                        rule.setNotApplicable(contractDataObject.getNotApplicable());
                        ruleMappingObj.setRule(rule);
                        ruleMappingObj = getMapping(contractDataObject, ruleMappingObj, true);
                        ruleMappingObj.setMapped(true);
                        ruleMappingObj.setVariableMappingStatus(contractDataObject.getMappingStatus());
                        ruleMappingObj.setIndvdlEb03AssnIndicator(contractDataObject.getIndvdlEb03AssnIndicator());
                        benefitRuleMap.put(mapKey, ruleMappingObj);
                    }
                }
                else { //new major heading
                    maj1 = contractDataObject.getMajorHeading();
                    min1 = contractDataObject.getMinorHeading();
                    mapKey = maj1 + DomainConstants.TILDA_CHAR + min1;
                    ruleId = contractDataObject.getRuleId();

                    Mapping ruleMappingObj = new ContractMappingVO();
                    //Setting Sensitive Benefit Flag
                    ruleMappingObj.setSensitiveBenefitIndicator(contractDataObject.getSensitiveBenefitIndicator());
                    Rule rule = new Rule();

                    rule.setHeaderRuleId(ruleId);
                    rule.setNotApplicable(contractDataObject.getNotApplicable());
                    ruleMappingObj.setRule(rule);
                    ruleMappingObj = getMapping(contractDataObject, ruleMappingObj, true);
                    ruleMappingObj.setMapped(true);
                    ruleMappingObj.setVariableMappingStatus(contractDataObject.getMappingStatus());
                    ruleMappingObj.setIndvdlEb03AssnIndicator(contractDataObject.getIndvdlEb03AssnIndicator());
                    benefitRuleMap.put(mapKey, ruleMappingObj);
                }
            } //if mappedvariable id not null
            else {
            	ruleId = contractDataObject.getRuleId();
           	 	maj1 = contractDataObject.getMajorHeading();
                min1 = contractDataObject.getMinorHeading();
                mapKey = maj1 + DomainConstants.TILDA_CHAR+min1;
           	 	Mapping ruleMappingObj = new ContractMappingVO();
                //Setting Sensitive Benefit Flag
                Rule rule = new Rule();

                rule.setHeaderRuleId(ruleId);
                ruleMappingObj.setRule(rule);
                ruleMappingObj.setIndvdlEb03AssnIndicator(contractDataObject.getIndvdlEb03AssnIndicator());
                benefitRuleMap.put(mapKey, ruleMappingObj);
           }
        }
        return benefitRuleMap;
    }

    /**
     * The method will create a HippaSegment object and add the HippaCode value to it
     * and return the HippaSegment object
     * @param element
     * @param val
     * @return
     */
    private HippaSegment addHippaSegment(String element,String val) {
        List selectedHippaSegmentValues = new ArrayList();
        HippaSegment hippaSegment = new HippaSegment(element);
        HippaCodeValue code = new HippaCodeValue();
        code.setValue(val);
        selectedHippaSegmentValues.add(code);
        hippaSegment.setHippaCodeSelectedValues(selectedHippaSegmentValues);
        return hippaSegment;
    }


    /**
     * The method will create a Mapping object  for a Line
     * @param contractDataObject
     * @param mappingObj
     * @return
     */
    private Mapping getMapping(ContractDataObjectVO contractDataObject,Mapping mappingObj,boolean isRule) {
        HippaSegment eb03 = new HippaSegment();
        HippaSegment umRule = new HippaSegment();
        HippaSegment hsd02 = new HippaSegment();
        if(!isRule){//if SPS then this code block for adding the HippaSegments
            if((null != contractDataObject) && (null != mappingObj)){
            	if (null != contractDataObject.getEB01() && !DomainConstants.EMPTY.equals(contractDataObject.getEB01())) {
                    mappingObj.setEb01(addHippaSegment(DomainConstants.EB01_NAME, contractDataObject.getEB01()));
                }
                if (null != contractDataObject.getEB02() && !DomainConstants.EMPTY.equals(contractDataObject.getEB02())) {
                    mappingObj.setEb02(addHippaSegment(DomainConstants.EB02_NAME, contractDataObject.getEB02()));
                }
                if (null != contractDataObject.getEB06() && !DomainConstants.EMPTY.equals(contractDataObject.getEB06())) {
                    mappingObj.setEb06(addHippaSegment(DomainConstants.EB06_NAME, contractDataObject.getEB06()));
                }
                if (null != contractDataObject.getEB09() && !DomainConstants.EMPTY.equals(contractDataObject.getEB09())) {
                    mappingObj.setEb09(addHippaSegment(DomainConstants.EB09_NAME, contractDataObject.getEB09()));
                }
                if (null != contractDataObject.getHSD1() &&  !DomainConstants.EMPTY.equals(contractDataObject.getHSD1())) {
                    mappingObj.setHsd01(addHippaSegment(DomainConstants.HSD01_NAME, contractDataObject.getHSD1()));
                }
                if (null != contractDataObject.getHSD2() && !DomainConstants.EMPTY.equals(contractDataObject.getHSD2())) {
					//commented sumanth code for july release
                	/*if (null != mappingObj.getHsd02()) {
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
					
					COMMENTED FOR JULY RELEASE
					mappingObj.setHsd02(addHippaSegment(contractDataObject
							.getHippaSegment(), contractDataObject
							.getHippaValue()));*/
                	mappingObj.setHsd02(addHippaSegment(DomainConstants.HSD02_NAME, contractDataObject.getHSD2()));
				}
                if (null != contractDataObject.getHSD3() && !DomainConstants.EMPTY.equals(contractDataObject.getHSD3())) {
                    mappingObj.setHsd03(addHippaSegment(DomainConstants.HSD03_NAME, contractDataObject.getHSD3()));
                }
                if (null != contractDataObject.getHSD4() && !DomainConstants.EMPTY.equals(contractDataObject.getHSD4())) {
                    mappingObj.setHsd04(addHippaSegment(DomainConstants.HSD04_NAME, contractDataObject.getHSD4()));
                }
                if (null != contractDataObject.getHSD5() && !DomainConstants.EMPTY.equals(contractDataObject.getHSD5())) {
                    mappingObj.setHsd05(addHippaSegment(DomainConstants.HSD05_NAME, contractDataObject.getHSD5()));
                }
                if (null != contractDataObject.getHSD6() && !DomainConstants.EMPTY.equals(contractDataObject.getHSD6())) {
                    mappingObj.setHsd06(addHippaSegment(DomainConstants.HSD06_NAME, contractDataObject.getHSD6()));
                }
                if (null != contractDataObject.getHSD7() && !DomainConstants.EMPTY.equals(contractDataObject.getHSD7())) {
                    mappingObj.setHsd07(addHippaSegment(DomainConstants.HSD07_NAME, contractDataObject.getHSD7()));
                }
                if (null != contractDataObject.getHSD8() && !DomainConstants.EMPTY.equals(contractDataObject.getHSD8())) {
                    mappingObj.setHsd08(addHippaSegment(DomainConstants.HSD08_NAME, contractDataObject.getHSD8()));
                }
                if (null != contractDataObject.getAccumrSPSParameterId() && !DomainConstants.EMPTY.equals(contractDataObject.getAccumrSPSParameterId())) {
                    mappingObj.setAccum(addHippaSegment(DomainConstants.ACCUM_NAME, contractDataObject.getAccumrSPSParameterId()));
                }
                /*if (null != contractDataObject.getUmRule() && !DomainConstants.EMPTY.equals(contractDataObject.getUmRule())) {
                	System.out.println("UM RULE"+contractDataObject.getUmRule());
                    mappingObj.setUtilizationMgmntRule(addHippaSegment(DomainConstants.UMRULE_NAME, contractDataObject.getUmRule()));
                }*/
            }
        }else{//if Rule then this code block for adding the HippaSegments to the Rule Mapping[EB03]
        	EB03Association eb03Assn = new EB03Association();
        	List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
            if(null != contractDataObject.getServiceTypeCode())
            {
            	List eb03SelectedValues =  new ArrayList();
                HippaCodeValue code = new HippaCodeValue();
                eb03.setName(DomainConstants.EB03_NAME);
                code.setValue(contractDataObject.getServiceTypeCode());
                eb03SelectedValues.add(code);
                eb03.setHippaCodeSelectedValues(eb03SelectedValues);
                //SSCR 19537 Begin
                eb03Assn.setEb03String(contractDataObject.getServiceTypeCode());
                eb03Assn.setEb03(code);
                //SSCR 19537 End
                      
            }
			if(null != contractDataObject.getIii02() && !DomainConstants.EMPTY.equals(contractDataObject.getIii02())) {
		    	  //mappingObj.setIi02(addHippaSegment(contractDataObject.getHippaSegment(), contractDataObject.getHippaValue()));
		    	  //SSCR15937
				List<HippaCodeValue> iii02CodeList = new ArrayList<HippaCodeValue>();
				List<String> iii02Codes = Arrays.asList(contractDataObject.getIii02().split(","));
				for(String code : iii02Codes){
					HippaCodeValue iii02Code = new HippaCodeValue();
					iii02Code.setValue(code);
					iii02CodeList.add(iii02Code);
				}
				eb03Assn.setIii02List(iii02CodeList);
			}
			if(null == mappingObj.getEb03()){
				eb03AssnList.add(eb03Assn);
                eb03.setEb03Association(eb03AssnList);
                mappingObj.setEb03(eb03);
			}else {
				String serviceTypeCode = contractDataObject.getServiceTypeCode();
                HippaCodeValue code = new HippaCodeValue();
                code.setValue(serviceTypeCode);
                mappingObj.getEb03().getHippaCodeSelectedValues().add(code);
                eb03AssnList.add(eb03Assn);
                if(null != mappingObj.getEb03().getEb03Association())
                {
                	mappingObj.getEb03().getEb03Association().addAll(eb03AssnList);
                }
                else
                {
                	mappingObj.getEb03().setEb03Association(eb03AssnList);
                }
			}
            if(null == mappingObj.getUtilizationMgmntRule()){
                List umRuleSelectedValues =  new ArrayList();
                HippaCodeValue code = new HippaCodeValue();

                umRule.setName(DomainConstants.UMRULE_NAME);
                code.setValue(contractDataObject.getUmRule());
                umRuleSelectedValues.add(code);
                umRule.setHippaCodeSelectedValues(umRuleSelectedValues);
                mappingObj.setUtilizationMgmntRule(umRule);
            }else{
                HippaCodeValue code = new HippaCodeValue();
                code.setValue(contractDataObject.getUmRule());
                mappingObj.getUtilizationMgmntRule().getHippaCodeSelectedValues().add(code);
            }
        }
        return mappingObj;
    }

    /**
     * The method sets  the exclusions in the ContractVO object
     * for the Error Validation Logic
     * @param contractDataObject
     * @param contract
     * @return
     */
    private ContractVO setExclusionFlags(ContractDataObjectVO contractDataObject,ContractVO contract){

        if(DomainConstants.EPO.equals(contractDataObject.getProductFamily())){
            contract.setFlagEPO(true);
        }
        if(hcrFlag){
            contract.setFlagHCR_E018(true);
        }
        if(isHCRFlag_E016And17){
            contract.setFlagHCR_E016And17(true);
        }
        if(isCDHP){
            contract.setFlagCDHP(true);
        }
        return contract;
    }
    
    private ContractVO createMsgContractObject(List contractDataList, ContractVO contractDataObjectSPS)
    {

    	//Map majorHeadingsMap = new HashMap();
    	Iterator resulSetIterator  = contractDataList.iterator();
        List<EB03Association> eb03AssnList = new ArrayList<EB03Association>();
        HippaSegment eb03 = new HippaSegment();
        String previousSpsId = null;
        String previousRuleId = null;
        //List<EB03Association> previousEb03AsscnList = null;

    	while (resulSetIterator.hasNext()){
    		ContractDataObjectVO contractDataObject = (ContractDataObjectVO)resulSetIterator.next();
    		//String majorHeadingDesc = contractDataObject.getMajorHeading();
    		Map	majorHeadingsMap = contractDataObjectSPS.getMajorHeadings();
    		//if the major heading is present 
    		if(majorHeadingsMap.containsKey(contractDataObject.getMajorHeading())){
    			MajorHeadingsVO majorHeadingFromMap = (MajorHeadingsVO)majorHeadingsMap.
    			get(contractDataObject.getMajorHeading());
    			Map minorHeadingsMp = majorHeadingFromMap.getMinorHeadings();
    			//If this is a new Minor Heading
    			if(minorHeadingsMp.containsKey(contractDataObject.getMinorHeading())){
    				MinorHeadingsVO minorHeadingFromMap = (MinorHeadingsVO)minorHeadingsMp.
    				get(contractDataObject.getMinorHeading());
    				Mapping ruleMapping = minorHeadingFromMap.getRuleMapping();
    				Map mappingsMp = minorHeadingFromMap.getMappings();
    				if(null!= ruleMapping && 
    						ruleMapping.getRule().getHeaderRuleId().equalsIgnoreCase(contractDataObject.getRuleId())&&
    						null != mappingsMp && null != contractDataObject.getSpsId() &&
    						mappingsMp.containsKey(contractDataObject.getSpsId()))
    				{
    					//SSCR 19537 Begin
    					if(!contractDataObject.getRuleId().equalsIgnoreCase(previousRuleId)) {
    						previousSpsId = null;
    						//previousEb03AsscnList = null;
    					}
    					previousRuleId = contractDataObject.getRuleId();
    					
    					/*List<EB03Association> eb03AsscnList = null;
    					if(null != previousEb03AsscnList) {
    						eb03AsscnList = previousEb03AsscnList;
    					}else {
    						if( null != ruleMapping.getEb03()) {
    							eb03AsscnList = ruleMapping.getEb03().getEb03Association();
    						}
    					}*/
    					List<EB03Association> eb03AsscnList = null;
    					if( null != ruleMapping.getEb03()) {
    						eb03AsscnList = ruleMapping.getEb03().getEb03Association();
						}
						Mapping mesgMapping = (Mapping )mappingsMp.get(contractDataObject.getSpsId());
    					if(null != ruleMapping && null != ruleMapping.getEb03()) {
    						List<EB03Association> finalEb03AsscnList;
    						if(contractDataObject.getSpsId().equalsIgnoreCase(previousSpsId)) {
    							 if(null != mesgMapping.getEb03()) {
    								 finalEb03AsscnList = mesgMapping.getEb03().getEb03Association();
    							 }else {
    								 finalEb03AsscnList = new ArrayList<EB03Association>();
    							 }
    						}else {
    							 finalEb03AsscnList = new ArrayList<EB03Association>();
    						}
    						previousSpsId = contractDataObject.getSpsId();
    						if(null != eb03AsscnList && eb03AsscnList.size() > 0) {
    							for(EB03Association eb03Asscn : eb03AsscnList) {
    								if (null != eb03Asscn
											&& null != eb03Asscn.getEb03()
											&& null != eb03Asscn.getEb03()
													.getValue()
											&& null != contractDataObject
											&& null != contractDataObject
													.getEb03()) {
    									if(eb03Asscn.getEb03().getValue().trim().equalsIgnoreCase(contractDataObject.getEb03().trim())) {
    										EB03Association eb03Assn = new EB03Association();
    										eb03Assn.setEb03(eb03Asscn.getEb03());
    										eb03Assn.setIii02List(eb03Asscn.getIii02List());
    										//Note Type Code
                			                HippaCodeValue noteTypeCd = new HippaCodeValue();
                			                noteTypeCd.setValue(contractDataObject.getMedssaeType());
                			                eb03Assn.setNoteType(noteTypeCd);
                			                //Message
                			                eb03Assn.setMessage(contractDataObject.getMessage());
                			                //Message Req Type
                			                eb03Assn.setMsgReqdInd(contractDataObject.getMsgReqType());
                			                finalEb03AsscnList.add(eb03Assn);
                						}
    								}
            					}
    						}
    						//previousEb03AsscnList = eb03AsscnList;
    						if(null != mesgMapping && null != mesgMapping.getEb03()) {
    							mesgMapping.getEb03().setEb03Association(finalEb03AsscnList);
    							mesgMapping.setIndvdlEb03AssnIndicator(contractDataObject.getIndvdlEb03AssnIndicator());
    							//ruleMapping.setEb03(null);
    						}else {
    							HippaSegment eb03Value = new HippaSegment();
    							eb03Value.setEb03Association(finalEb03AsscnList);
    							mesgMapping.setEb03(eb03Value);
    							mesgMapping.setIndvdlEb03AssnIndicator(contractDataObject.getIndvdlEb03AssnIndicator());
    							//ruleMapping.setEb03(null);
    						}
    						minorHeadingFromMap.getMappings().put(contractDataObject.getSpsId(),mesgMapping);
    					}
    				}
    				minorHeadingsMp.put(contractDataObject.getMinorHeading(), minorHeadingFromMap);
    			}
    			majorHeadingFromMap.setMinorHeadings(minorHeadingsMp);
    			majorHeadingsMap.put(contractDataObject.getMajorHeading(), majorHeadingFromMap);
    		}
    		contractDataObjectSPS.setMajorHeadings(majorHeadingsMap);
    	}
    	return contractDataObjectSPS;

    }
    

    /**
     * Query for
     * fetching the Line and Question Mappings
     *
     */
    private class EWPDSPSContractMappingInfoQuery extends MappingSqlQuery {

    	//Sets a map of sps id and its corresponding coded value set --July Release ErrorCodes
    	Map spsCodedValue = new HashMap(); 

        public EWPDSPSContractMappingInfoQuery(DataSource ds) {
            super(ds, getEwpdSPSContractMappingInfoQuery());
            // for lines
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));
            // for tiered lines
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));
            //for questions
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));
            //declareParameter(new SqlParameter(Types.DATE));
            // for tiered questions
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));
            compile();
        }
        /**
         * Get value from the result set and set to ContractDataObjectVO
         */
        public Object mapRow(ResultSet resultSet, int row) throws SQLException {
            ContractDataObjectVO contractDataObject = new ContractDataObjectVO();

            contractDataObject.setMappedVariableId(resultSet.getString(DomainConstants.SPS_PARAM_ID));
            contractDataObject.setEB01(resultSet.getString(DomainConstants.EB01_VALUE));
            contractDataObject.setEB02(resultSet.getString(DomainConstants.EB02_VALUE));
            contractDataObject.setEB06(resultSet.getString(DomainConstants.EB06_VALUE));
            contractDataObject.setEB09(resultSet.getString(DomainConstants.EB09_VALUE));
            contractDataObject.setMinorHeading(resultSet.getString(DomainConstants.BENEFIT_NAME));
            contractDataObject.setMajorHeading(resultSet.getString(DomainConstants.BENEFIT_COMP_NAME));
            contractDataObject.setHSD1(resultSet.getString(DomainConstants.HSD1_VALUE));
            contractDataObject.setHSD2(resultSet.getString(DomainConstants.HSD2_VALUE));
            contractDataObject.setHSD3(resultSet.getString(DomainConstants.HSD3_VALUE));
            contractDataObject.setHSD4(resultSet.getString(DomainConstants.HSD4_VALUE));
            contractDataObject.setHSD5(resultSet.getString(DomainConstants.HSD5_VALUE));
            contractDataObject.setHSD6(resultSet.getString(DomainConstants.HSD6_VALUE));
            contractDataObject.setHSD7(resultSet.getString(DomainConstants.HSD7_VALUE));
            contractDataObject.setHSD8(resultSet.getString(DomainConstants.HSD8_VALUE));
            contractDataObject.setAccumrSPSParameterId(resultSet.getString(DomainConstants.ACCUMR_SPS_ID));
            contractDataObject.setProductFamily(resultSet.getString(DomainConstants.PRODUCT_FAMILY));
            contractDataObject.setFormat(resultSet.getString(DomainConstants.DATA_TYPE_LGND));
            contractDataObject.setVariableValue(resultSet.getString(DomainConstants.BENEFIT_VAL));
            contractDataObject.setPva(resultSet.getString(DomainConstants.LINE_PVA));
            contractDataObject.setVariableId(resultSet.getString(DomainConstants.REFERENCE_ID));
            contractDataObject.setSpsDescription(resultSet.getString(DomainConstants.SPSDESC));
            contractDataObject.setLevelDescription(resultSet.getString(DomainConstants.LVLDESC));
            contractDataObject.setBusinessEntity(resultSet.getString(DomainConstants.BUSENTITY));
            contractDataObject.setEndDate(resultSet.getString(DomainConstants.EXPIRYDATE));
            contractDataObject.setStartDate(resultSet.getString(DomainConstants.EFFECTIVE_DATE));
            /*Code for eBX - start*/
            contractDataObject.setMappingComplete(resultSet.getString(DomainConstants.MAPPNG_CMP_IND));
            /*Code for eBX - end*/
            contractDataObject.setLineOfBusiness(resultSet.getString(DomainConstants.LINEOFBUSINESS));
            contractDataObject.setProductCode(resultSet.getString(DomainConstants.PRODUCT_CODE));
            contractDataObject.setMappingStatus(resultSet.getString(DomainConstants.STATUS_CD));
            /**
             *  Getting the tier description from the result set-July Release.
             */
            contractDataObject.setTierDescription(resultSet.getString(DomainConstants.TIER_DESC));
    		// setting the business group to check the system that the eWPD Contract belongs
    		contractDataObject.setBusinessGroup(resultSet.getString(DomainConstants.BUS_GRP_NM));

    		//making a map of sps id and corresponding coded value --July Release ErrorCodes
    		Set codedValueSet = new HashSet();
    		String spsID = contractDataObject.getVariableId();
    		String lineValue = contractDataObject.getVariableValue();
    		if(null != spsID ){
    		if (spsCodedValue.containsKey(spsID)) {
    			codedValueSet = (Set)spsCodedValue.get(spsID);
    			codedValueSet.add(lineValue);
    			spsCodedValue.put(spsID, codedValueSet);
    		}
    		else {
    			codedValueSet.add(lineValue);
    			spsCodedValue.put(spsID, codedValueSet);
    			}
    		//adding the accumulator values into a set --July Release ErrorCodes
    		}
            return contractDataObject;
        }
    }
    /**
     * Query for fetching the Admin method SPS
     *
     */
    private class EWPDAdminSPSContractInfoQuery extends MappingSqlQuery {

        public EWPDAdminSPSContractInfoQuery(DataSource ds) {
            super(ds, getEwpdAdminSPSContractInfoQuery());
            // for lines
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));
            
            compile();
        }
        /**
         * Get value from the result set and set to ContractDataObjectVO
         */
        public Object mapRow(ResultSet resultSet, int row) throws SQLException {
            ContractDataObjectVO contractDataObject = new ContractDataObjectVO();

            contractDataObject.setSpsId(resultSet.getString(DomainConstants.SPS_ID));
            contractDataObject.setLineValue(resultSet.getString(DomainConstants.CODED_VALUE));
            contractDataObject.setMinorHeading(resultSet.getString(DomainConstants.BENEFIT_NAME));
            contractDataObject.setMajorHeading(resultSet.getString(DomainConstants.BENEFIT_COMP_NAME));
            
            return contractDataObject;
        }
    }
    /**
     * Query for
     * fetching the ServiceType Mappings[EB03-Rule]
     *
     */
    private class EWPDRuleContractMappingInfoQuery extends MappingSqlQuery {

    	public EWPDRuleContractMappingInfoQuery(DataSource ds) {
            super(ds, getEwpdRuleIdContractMappingInfoQuery());
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));     
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));
            compile();
        }

        public Object mapRow(ResultSet resultSet, int row) throws SQLException {
            ContractDataObjectVO contractDataObject= new ContractDataObjectVO();
            contractDataObject.setMappedVariableId(resultSet.getString(DomainConstants.MAPPED_RULE_ID));
            contractDataObject.setUmRule(resultSet.getString(DomainConstants.MAPPED_UMRULE));
            contractDataObject.setServiceTypeCode(resultSet.getString(DomainConstants.SERVICE_TYPE_CODE));
            contractDataObject.setMinorHeading(resultSet.getString(DomainConstants.BENEFIT_NAME));
            contractDataObject.setMajorHeading(resultSet.getString(DomainConstants.BENEFIT_COMP_NAME));
            contractDataObject.setRuleId(resultSet.getString(DomainConstants.EWPD_RULE_ID));
            contractDataObject.setSensitiveBenefitIndicator(resultSet.getString(DomainConstants.SEND_DYNAMIC_INFO));
            contractDataObject.setNotApplicable(resultSet.getString(DomainConstants.VALID_FOR_BX));
            contractDataObject.setMappingStatus(resultSet.getString(DomainConstants.STATUS_CD));
            contractDataObject.setIii02(resultSet.getString(DomainConstants.III02_NAME));
            contractDataObject.setIndvdlEb03AssnIndicator(resultSet.getString("INDVDL_EB03_ASSN_IND"));
            return contractDataObject;
        }
    }
        
    
    /**
     * Query for
     * fetching the Message, Message Type, Message required
     *
     */
    private class EWPDMessageContractMappingInfoQuery extends MappingSqlQuery {

        public EWPDMessageContractMappingInfoQuery(DataSource ds) {
            super(ds, getEwpdMessageContractMappingInfoQuery());
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE)); 
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));  
            compile();
        }

        public Object mapRow(ResultSet resultSet, int row) throws SQLException {
            ContractDataObjectVO contractDataObject= new ContractDataObjectVO();
            contractDataObject.setRuleId(resultSet.getString(DomainConstants.EWPD_RULE_ID));
            contractDataObject.setSpsId(resultSet.getString(DomainConstants.SPS_ID));
            contractDataObject.setMedssaeType(resultSet.getString("NOTE_TYP_CD"));
            contractDataObject.setMessage(resultSet.getString("MSG_TEXT"));
            contractDataObject.setMsgReqType(resultSet.getString("MSG_RQRD_IND"));
            contractDataObject.setMinorHeading(resultSet.getString(DomainConstants.BENEFIT_NAME));
            contractDataObject.setMajorHeading(resultSet.getString(DomainConstants.BENEFIT_COMP_NAME));
            contractDataObject.setEb03(resultSet.getString("EB03"));
            contractDataObject.setIndvdlEb03AssnIndicator(resultSet.getString("INDVDL_EB03_ASSN_IND"));
            return contractDataObject;
        }
    }

    /**
     * Query for
     * checking whether the Contract is HCR
     * if question 'HCR PREVENTIVE PROVISION APPLIES' is answered, it is a HCR contract
     *
     */
    private class PreventiveProvisionAppliesQuery extends MappingSqlQuery {

        public PreventiveProvisionAppliesQuery(DataSource ds) {
            super(ds, getPreventiveProvisionAppliesQuery());
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));            
            compile();
        }

        public Object mapRow(ResultSet resultSet, int row) throws SQLException {
            return Integer.valueOf(resultSet.getString(DomainConstants.HCR_QN_E018));
        }
    }

    /**
     * Query for
     * checking whether the Contract is HCR
     * if question 'HCR LIMIT RESTRICTION APPLIES' is answered, it is a HCR Contract
     *
     */
    private class LimitRestrictionAppliesQuery extends MappingSqlQuery {

        public LimitRestrictionAppliesQuery(DataSource ds) {
            super(ds, getLimitRestrictionAppliesQuery());
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));            
            compile();
        }

        public Object mapRow(ResultSet resultSet, int row) throws SQLException {
            return Integer.valueOf(resultSet.getString(DomainConstants.HCR_QN_E018));
        }
    }

    /**
     * Query for
     * checking whether the Contract is HCR
     * if the question mapped to the indicative 'GNCCNNP-CDHP-LUMENOS-IND'
     * or 'ICLCNND-HSA-CONTRACT' is coded, it is CDHP contract
     *
     */
    private class CDHPQuery extends MappingSqlQuery {
        public CDHPQuery(DataSource ds) {
            super(ds, getCDHPQuery());
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));            
            compile();
        }

        public Object mapRow(ResultSet resultSet, int row) throws SQLException {
            return Integer.valueOf(resultSet.getString(DomainConstants.CDHPCOUNT));
        }
    }

    
    /**
     * Query for
     * checking whether the Input provided-
     *  contractID,version and datesegment is valid
     *
     */
    private class EWPDGetContractVersions extends MappingSqlQuery {
        public EWPDGetContractVersions(DataSource ds) {
            super(ds, getEwpdGetContractVersions());
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));            
            compile();
        }

        public Object mapRow(ResultSet resultSet, int row) throws SQLException {
            return Integer.valueOf(resultSet.getString(DomainConstants.ROW_COUNT));

        }
    }
    
    private class EWPDEbxGetMaxContractVersions extends MappingSqlQuery {
        public EWPDEbxGetMaxContractVersions(DataSource ds) {
            super(ds, getEwpdEbxGetMaxContractVersions());
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.DATE));            
            compile();
        }

        public Object mapRow(ResultSet resultSet, int row) throws SQLException {
        	 return resultSet.getString(DomainConstants.MAX_VERSION);

        }
    }

    public String getCDHPQuery() {
        return CDHPQuery;
    }

    public void setCDHPQuery(String CDHPQuery) {
        this.CDHPQuery = CDHPQuery;
    }

    public String getEwpdSPSContractMappingInfoQuery() {
        return ewpdSPSContractMappingInfoQuery;
    }

    public void setEwpdSPSContractMappingInfoQuery(
            String ewpdSPSContractMappingInfoQuery) {
        this.ewpdSPSContractMappingInfoQuery = ewpdSPSContractMappingInfoQuery;
    }

    /**
     * @return Returns the dataSource.
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * @param dataSource The dataSource to set.
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getEwpdRuleIdContractMappingInfoQuery() {
        return ewpdRuleIdContractMappingInfoQuery;
    }

    public void setEwpdRuleIdContractMappingInfoQuery(
            String ewpdRuleIdContractMappingInfoQuery) {
        this.ewpdRuleIdContractMappingInfoQuery = ewpdRuleIdContractMappingInfoQuery;
    }

    public String getLimitRestrictionAppliesQuery() {
        return limitRestrictionAppliesQuery;
    }

    public void setLimitRestrictionAppliesQuery(String limitRestrictionAppliesQuery) {
        this.limitRestrictionAppliesQuery = limitRestrictionAppliesQuery;
    }

    public String getPreventiveProvisionAppliesQuery() {
        return preventiveProvisionAppliesQuery;
    }

    public void setPreventiveProvisionAppliesQuery(String preventiveProvisionAppliesQuery) {
        this.preventiveProvisionAppliesQuery = preventiveProvisionAppliesQuery;
    }

    public String getEwpdGetContractVersions() {
        return ewpdGetContractVersions;
    }


    public void setEwpdGetContractVersions(String ewpdGetContractVersions) {
        this.ewpdGetContractVersions = ewpdGetContractVersions;
    }
    

	public String getEwpdMessageContractMappingInfoQuery() {
		return ewpdMessageContractMappingInfoQuery;
	}


	public void setEwpdMessageContractMappingInfoQuery(
			String ewpdMessageContractMappingInfoQuery) {
		this.ewpdMessageContractMappingInfoQuery = ewpdMessageContractMappingInfoQuery;
	}


	public String getEwpdEbxGetMaxContractVersions() {
		return ewpdEbxGetMaxContractVersions;
	}


	public void setEwpdEbxGetMaxContractVersions(
			String ewpdEbxGetMaxContractVersions) {
		this.ewpdEbxGetMaxContractVersions = ewpdEbxGetMaxContractVersions;
	}
	
	public String getEwpdGetContractAdminOptionAnswer() {
		return ewpdGetContractAdminOptionAnswer;
	}


	public void setEwpdGetContractAdminOptionAnswer(
			String ewpdGetContractAdminOptionAnswer) {
		this.ewpdGetContractAdminOptionAnswer = ewpdGetContractAdminOptionAnswer;
	}
	public String getEwpdAdminSPSContractInfoQuery() {
		return ewpdAdminSPSContractInfoQuery;
	}

	public void setEwpdAdminSPSContractInfoQuery(
			String ewpdAdminSPSContractInfoQuery) {
		this.ewpdAdminSPSContractInfoQuery = ewpdAdminSPSContractInfoQuery;
	}


	
	 /**
     * Query for getting the Answer for the 
     * Admin Option "CALENDAR OR BENEFIT YEAR" 
     * 
     */
    private class ContractAdminOptionsQuery extends MappingSqlQuery {
        public ContractAdminOptionsQuery(DataSource ds) {
            super(ds, getEwpdGetContractAdminOptionAnswer());
            declareParameter(new SqlParameter(Types.VARCHAR));
            declareParameter(new SqlParameter(Types.INTEGER));
            declareParameter(new SqlParameter(Types.DATE));            
            compile();
        }

        public Object mapRow(ResultSet resultSet, int row) throws SQLException {
            return resultSet.getString(DomainConstants.ANSWER);
        }
    }
    
    /**
     * @author UST Global - www.ust-global.com <br />
     * @version $Id: $
     */
    private class EWPDContractErrorExclusionQuery extends MappingSqlQuery {
        public EWPDContractErrorExclusionQuery(DataSource ds, String ewpdExclusionsQuery) {
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
    
	/**
	 * @param contractID
	 * @param productList
	 * @return ISG Contract exclusions fetch query.
	 */
	private String getEWPDContractExclusionQuery(String contractID,
			String productCode) {
		StringBuffer ewpdQuery = new StringBuffer("");
		ewpdQuery
				.append("SELECT ERROR_CD, EXCLUSION_TYPE_PRIMARY, EXCLUSION_VAL_PRIMARY, EXCLUSION_TYPE_SECONDARY, EXCLUSION_VAL_SECONDARY, EXCLUSION_COUNT");
		ewpdQuery.append(" FROM BX_ERROR_EXCLUSION");
		ewpdQuery.append(" WHERE");
		ewpdQuery.append(" (EXCLUSION_TYPE_PRIMARY='SPS') ");
		ewpdQuery.append(" OR (EXCLUSION_TYPE_PRIMARY='HEADERRULE') ");
		ewpdQuery
				.append(" OR (EXCLUSION_TYPE_PRIMARY ='CONTRACT' and (EXCLUSION_VAL_PRIMARY LIKE '%"
						+ contractID + "%'))");
		ewpdQuery
				.append(" OR (EXCLUSION_TYPE_PRIMARY ='PRODUCT' and (EXCLUSION_VAL_PRIMARY LIKE '%"
						+ productCode + "%'))");
		return ewpdQuery.toString();
	}
	
	public boolean isInactiveContract(String contractId) throws EBXException, Exception{
		String query = "SELECT COUNT(CNTRCT_ID) AS CNT FROM CNTRCT_STATUS WHERE UPPER(TRIM(CNTRCT_ID)) = '"+contractId.trim().toUpperCase()+"' AND TRIM(STATUS) ='INACTIVE'";
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
    
    public boolean is4010Exists() throws EBXException, Exception{
		String query = "SELECT COUNT(DATA_ELEMENT_ID) AS CNT FROM BX_HIPPA_SEGMENT WHERE DATA_ELEMENT_ID='4010'";
		Is4010ExistsQuery is4010ExistsQuery = new Is4010ExistsQuery(getDataSource(),query);
		List list = is4010ExistsQuery.execute();
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
    private class Is4010ExistsQuery extends MappingSqlQuery {
        public Is4010ExistsQuery(DataSource ds, String is4010ExistsQuery) {
            super(ds, is4010ExistsQuery);
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
    private String ewpdStartDates;
    private String ewpdMaxVersion;

	/**
	 * @return the ewpdStartDates
	 */
	public String getEwpdStartDates() {
		return ewpdStartDates;
	}

	/**
	 * @param ewpdStartDates the ewpdStartDates to set
	 */
	public void setEwpdStartDates(String ewpdStartDates) {
		this.ewpdStartDates = ewpdStartDates;
	}

	/**
	 * @return the ewpdMaxVersion
	 */
	public String getEwpdMaxVersion() {
		return ewpdMaxVersion;
	}

	/**
	 * @param ewpdMaxVersion the ewpdMaxVersion to set
	 */
	public void setEwpdMaxVersion(String ewpdMaxVersion) {
		this.ewpdMaxVersion = ewpdMaxVersion;
	}
	
	/* (non-Javadoc)
	 * @see com.wellpoint.ets.ebx.simulation.repository.ContractInfoRepository#getStartDates(java.lang.String)
	 */
	
	@Override
	public List<String> getStartDates(String contractId, String enviorment) {
		FetchEWPDStartDatesQuery fetchEWPDStartDatesQuery = new FetchEWPDStartDatesQuery(dataSource,getEwpdStartDates());
		Object[] inputParams = new Object[] { contractId };
		List<String> startDates = fetchEWPDStartDatesQuery.execute(inputParams);
		return startDates;
	}
	private static final class FetchEWPDStartDatesQuery extends MappingSqlQuery {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		public FetchEWPDStartDatesQuery(DataSource dataSource,
				String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
		}

		@Override
		protected Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			return simpleDateFormat.format(resultSet.getDate(1));
		}
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.ebx.simulation.repository.ContractInfoRepository#getLatestVersion(java.lang.String)
	 */
	@Override
	public String getLatestVersion(String contractId) {
		FetchEWPDVersionQuery fetchEWPDVersionQuery = new FetchEWPDVersionQuery(dataSource,getEwpdMaxVersion());
		Object[] inputParams = new Object[] { contractId };
		List latestVersion = fetchEWPDVersionQuery.execute(inputParams);
		String version = "";
		if (null != latestVersion && !DomainConstants.EMPTY.equals(latestVersion)) {
			if (null != latestVersion.get(0)) {
				version = (String) latestVersion.get(0);
			} 
		} 
		return version;
	}
	private static final class FetchEWPDVersionQuery extends MappingSqlQuery {
		public FetchEWPDVersionQuery(DataSource dataSource,
				String query) {
			super(dataSource, query);
			declareParameter(new SqlParameter(Types.VARCHAR));
		}

		@Override
		protected Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			return resultSet.getString(1);
		}
		
	}
	
	/**
	 * Method returns the pricing info VO list for the contract.
	 * SSCR 16331 Nov Release
	 * @param contractId
	 * @return pricingInfoVOList
	 */
	private List<PricingInfoVO> getPricingInfoForEWPDContract(ContractVO contract){

		StringBuffer pricingInfoQuery = new StringBuffer();
		pricingInfoQuery.append("SELECT DT_SGMNT_SYS_ID, CVRG_CD,NTWK_CD FROM CNTRCT_PRCG_INFO WHERE DT_SGMNT_SYS_ID IN ( ");
		pricingInfoQuery.append(" select dtsgmntMstr.dt_sgmnt_sys_id from cntrct_dt_sgmnt_assn assn, cntrct_dt_sgmnt dtsgmntMstr where cntrct_sys_id in (");
		pricingInfoQuery.append(" select cntrct_sys_id  from cmst_cntrct_mstr where cntrct_id = '");
		pricingInfoQuery.append(contract.getContractId());
		pricingInfoQuery.append("' and cntrct_vrsn_nbr = '");
		pricingInfoQuery.append(contract.getVersion());
		pricingInfoQuery.append("')");
		pricingInfoQuery.append(" and assn.dt_sgmnt_sys_id = dtsgmntMstr.dt_sgmnt_sys_id ");
		pricingInfoQuery.append(" and dtsgmntMstr.dt_sgmnt_efctv_dt = to_date('");
		pricingInfoQuery.append(contract.getEffectiveDate());
		pricingInfoQuery.append("', 'mm/dd/yyyy'))");
		
		logger.info("PricingInfoQuery >>> "+ESAPI.encoder().encodeForHTML(pricingInfoQuery.toString()));
		FetchEWPDPricingInfoQuery fetchEWPDPricingInfoQuery = new FetchEWPDPricingInfoQuery(dataSource, pricingInfoQuery.toString());
		List<PricingInfoVO> pricingInfoList = fetchEWPDPricingInfoQuery.execute();
		return pricingInfoList;
	}
	
	private static final class FetchEWPDPricingInfoQuery extends MappingSqlQuery {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		public FetchEWPDPricingInfoQuery(DataSource dataSource,
				String query) {
			super(dataSource, query);
		}

		protected Object mapRow(ResultSet resultSet, int rowCount)
				throws SQLException {
			PricingInfoVO pricingInfoVO = new PricingInfoVO();
			pricingInfoVO.setDateSegmentSysId(resultSet.getString("DT_SGMNT_SYS_ID"));
			pricingInfoVO.setCoverageCode(resultSet.getString("CVRG_CD"));
			pricingInfoVO.setNetworkCode(resultSet.getString("NTWK_CD"));
			
			return pricingInfoVO;
		}
		
	}
}
