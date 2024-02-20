/*
 * <ContractServiceImpl.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.simulation.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import org.owasp.esapi.ESAPI;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.repository.HippaSegmentRepository;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.ebx.simulation.repository.ContractInfoRepository;
import com.wellpoint.ets.ebx.simulation.util.ContractMappingValidator;
import com.wellpoint.ets.ebx.simulation.util.EWPDContractMappingValidator;
import com.wellpoint.ets.ebx.simulation.util.SimulationHelper;
import com.wellpoint.ets.ebx.simulation.util.WPDContractMappingValidator;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

/**
 * @author UST-GLOBAL
 * 
 *         Contract Service Implementation Class. This class will invoke the
 *         Repository layer for Error Validation of Contract and make the MQ
 *         call for getting X12 Response.
 * 
 */
public class ContractServiceImpl implements ContractService {

	private ContractInfoRepository lgContractInfoRepository;

	private ContractInfoRepository isgContractInfoRepository;

	private ContractInfoRepository ewpdContractInfoRepository;

	private HippaSegmentRepository hippaSegmentRepository;

	private Set accumHippaSegmentList = null;
	
	public HippaSegmentRepository getHippaSegmentRepository() {
		return hippaSegmentRepository;
	}

	public void setHippaSegmentRepository(
			HippaSegmentRepository hippaSegmentRepository) {
		this.hippaSegmentRepository = hippaSegmentRepository;
	}

	private MQAdapter mqAdapter;

	public static final Logger log = Logger
			.getLogger(ContractServiceImpl.class);

	/**
	 * Method for Error Validation of Contract for EWPD and WPD system
	 * 
	 * @throws EBXException
	 *             ,Exception
	 */
	public List getContractInfo(ContractVO contract,String environment, boolean eBxReportFlag, SystemConfigurationVO systemConfigurationVO)
			throws EBXException, Exception {
		List accumIndList = new ArrayList(); //Moved the class variable 'accumIndList' to the method for defect fix : IN215729 : log id in report
		List contractVOList = null;
		EWPDContractMappingValidator eWPDContractMappingValidator = null;
		WPDContractMappingValidator wpdContractMappingValidator = null;
		MQCommand mqCommand = null;
		Thread mqThread = null;
		String system = null;
		long lEndTime = 0;
		long lStartTime = System.currentTimeMillis();
		
		/**Added for BXNI requirement T6.1.1.2.WGSSTAR.eBX.6**/
		//boolean productionFlag = false;
		
		if (DomainConstants.PRODUCTION.equalsIgnoreCase(environment)) {
			contract.setProductionFlag(true);
			//productionFlag = true;
		} else {
			contract.setProductionFlag(false);
			//productionFlag = false;
		}
		/**End BXNI requirement T6.1.1.2.WGSSTAR.eBX.6**/
		
		if (!eBxReportFlag) {
			// Creates and Starts the MQ Thread
			mqCommand = new MQCommand();
			mqCommand.setContract(contract);
			mqCommand.setMqAdapter(mqAdapter);
			mqCommand.setSystemConfigurationVO(systemConfigurationVO);
			mqThread = new Thread(mqCommand);
			if(null != systemConfigurationVO){
			log.info("systemConfigurationVO STARTS HERE");
			log.info("systemConfigurationVO.getBackEndRegion() --> "+ESAPI.encoder().encodeForHTML(systemConfigurationVO.getBackEndRegion()));
			log.info("systemConfigurationVO.getEnvironment() --> "+ESAPI.encoder().encodeForHTML(systemConfigurationVO.getEnvironment()));
			log.info("systemConfigurationVO.getFunctionality() --> "+ESAPI.encoder().encodeForHTML(systemConfigurationVO.getFunctionality()));
			log.info("systemConfigurationVO.getSourceEnvironment() --> "+ESAPI.encoder().encodeForHTML(systemConfigurationVO.getSourceEnvironment()));
			log.info("systemConfigurationVO.getDestinationEnvironment() --> "+ESAPI.encoder().encodeForHTML(systemConfigurationVO.getDestinationEnvironment()));
			log.info("systemConfigurationVO.getSystem() --> "+ESAPI.encoder().encodeForHTML(systemConfigurationVO.getSystem()));
			log.info("systemConfigurationVO.getSystemConfigurationID() --> "+ESAPI.encoder().encodeForHTML(systemConfigurationVO.getSystemConfigurationID()));
			log.info("systemConfigurationVO ENDS HERE");
			}
			mqThread.start();
			log.debug("after starting the thread");
		}
		
		ContractMappingValidator mappingValidator = new ContractMappingValidator();
		// For LG System
		if (contract.getSystem().equalsIgnoreCase(DomainConstants.SYSTEM_LG)) {
			accumHippaSegmentList=new HashSet();
			// Repository Invocation
			contractVOList = getLgContractInfoRepository().getContractInfo(
					contract, eBxReportFlag);
			wpdContractMappingValidator = new WPDContractMappingValidator();
			log.debug("after taking the DB data");
			// Copy below if condition to the thread.
			if (!eBxReportFlag) {				
				// Error Validation
				Iterator contractListIter = contractVOList.iterator();
				while(contractListIter.hasNext()){
					ContractVO contractVO = (ContractVO) contractListIter.next();					
					accumHippaSegmentList.addAll(contractVO.getAccumHippaSegmentList());
				}
				accumIndList = getHippaSegmentRepository().getAccumulators(accumHippaSegmentList, DomainConstants.SYSTEM_LG);
				wpdContractMappingValidator.setAccumIndList(accumIndList);
				contractVOList = wpdContractMappingValidator
						.validate(contractVOList);
			}
		}
		// For ISG System
		else if (contract.getSystem().equalsIgnoreCase(
				DomainConstants.SYSTEM_ISG)) {
			accumHippaSegmentList=new HashSet();
			// Repository Invokation
			contractVOList = getIsgContractInfoRepository().getContractInfo(
					contract, eBxReportFlag);
			wpdContractMappingValidator = new WPDContractMappingValidator();
			log.debug("after taking the DB data");
			if (!eBxReportFlag) {
				// Error Validation
				Iterator contractListIter = contractVOList.iterator();
				while(contractListIter.hasNext()){
					ContractVO contractVO = (ContractVO) contractListIter.next();
					accumHippaSegmentList.addAll(contractVO.getAccumHippaSegmentList());
				}
				accumIndList = getHippaSegmentRepository().getAccumulators(accumHippaSegmentList,DomainConstants.SYSTEM_ISG);
				wpdContractMappingValidator.setAccumIndList(accumIndList);
				contractVOList = wpdContractMappingValidator
						.validate(contractVOList);
			}
		}
		// For EWPD System
		else if (contract.getSystem().equalsIgnoreCase(
				DomainConstants.SYSTEM_EWPD)) {
			accumHippaSegmentList=new HashSet();
			// Repository Invocation
			contractVOList = getEwpdContractInfoRepository().getContractInfo(
					contract, eBxReportFlag);
			eWPDContractMappingValidator = new EWPDContractMappingValidator();
			log.debug("after taking the DB data");
			if (!eBxReportFlag) {
				// Error Validation
				Iterator contractListIter = contractVOList.iterator();
				while(contractListIter.hasNext()){
					ContractVO contractVO = (ContractVO) contractListIter.next();
					if((DomainConstants.SYSTEM_ALL).equalsIgnoreCase(contractVO.getBusinessGroup()) || (DomainConstants.SYSTEM_LG).equalsIgnoreCase(contractVO.getBusinessGroup()))
						system = DomainConstants.SYSTEM_LG;
					else if((DomainConstants.SYSTEM_IND).equalsIgnoreCase(contractVO.getBusinessGroup()) || (DomainConstants.SYSTEM_SG).equalsIgnoreCase(contractVO.getBusinessGroup()))
						system = DomainConstants.SYSTEM_ISG;
					Set value= new HashSet(contractVO.getSpsCodedValue().values());
					Iterator itrValue = value.iterator();
					while(itrValue.hasNext())
						accumHippaSegmentList.addAll((Collection) itrValue.next());
				}
				accumIndList = getHippaSegmentRepository().getAccumulators(accumHippaSegmentList, system);
				eWPDContractMappingValidator.setAccumIndList(accumIndList);
				contractVOList = eWPDContractMappingValidator
						.validate(contractVOList);
			}
		}
		if (!eBxReportFlag && null != mqThread) {
			mqThread.join();
			EBXException validationException = mqCommand
					.getValidationException();
			if (null != validationException) {
				throw validationException;
			}
			// Fetches the MQ Error codelist
			List mQErrorCodesList = mqCommand.getMQErrorCodesList();
			// retrieve the generic EBstring list from MQ
			List genericEBStringList = mqCommand.getGenericEBStringList();
			// Filters EB01= I from the generic list
			List eBStringForE019List = mappingValidator
					.getFilterredEBStringforE019(genericEBStringList);
			// Validated Error E019 details
			List validatedE019List = mappingValidator.validateE019(
					contractVOList, eBStringForE019List);
			List validateE019ListForCoinsurance = mappingValidator
					.validateE019forZeroCoinsuranceInNetwork(contractVOList,
							eBStringForE019List);
			
			mQErrorCodesList.addAll(validatedE019List);
			mQErrorCodesList.addAll(validateE019ListForCoinsurance);

			mappingValidator.combineMQAndDBErrorCodesList(contractVOList,
					mQErrorCodesList);
		}
		lEndTime = System.currentTimeMillis();
		long difference = lEndTime - lStartTime;
		log.debug("Time for DB call and MQ " + difference);
		return contractVOList;
	}

	public List get27xBenefitAccumInfo(ContractVO contract, String environment, String responseFormat,
			SystemConfigurationVO systemConfigurationVO)
			throws EBXException, XmlException, Exception {
		long time;
		String benefitAccumResponse = null;
		ContractVO contractInfo = null;
		SimulationHelper helper = new SimulationHelper();
		// Change as part of Defect fix done on Pagination - Start
		List pagedResponseContracts = new ArrayList();

		ResourceBundle rb = ResourceBundle.getBundle(
				DomainConstants.TWENTY_SEVEN_X_REQUEST, Locale.getDefault());
		String maxPageSize = rb.getString("maxPagesSupported");
		int maximumPagsSize = 1;
		if (BxUtil.isInteger(maxPageSize)) {
			maximumPagsSize = Integer.parseInt(maxPageSize);
		}
		// Change as part of Defect fix done on Pagination - End

		boolean productionFlag = false;
		String system = "";
		if (null != contract.getSystem()) {
			system = contract.getSystem();
		}
		if (DomainConstants.PRODUCTION.equals(environment)) {
			productionFlag = true;
		} else {
			productionFlag = false;
		}
		int pageCount = 0;
		do {
			if (pageCount == 0) {
				contract.setMoreDataForPagination(rb.getString("moreData"));
				contract.setNextPageSize(rb.getString("sbiAdditionalPageKeys"));
			}
			if (pageCount != 0 && null != contractInfo) {
				if (!("Y".equalsIgnoreCase(contractInfo
						.getMoreDataForPagination()))) {
					break;
				}
				contract.setMoreDataForPagination(contractInfo
						.getMoreDataForPagination());
				contract.setNextPageSize(contractInfo.getNextPageSize());
			}
			// Creating the request
			log.info("Creating 27XBenefitAccumRequest");
			String benefitAccumRequest="";
			if (DomainConstants.RESPONSE_FORMAT_4010.equals(responseFormat)) {
				benefitAccumRequest = helper.get27xBenefitAccumRequest(
						contract, environment);
			}
			else if (DomainConstants.RESPONSE_FORMAT_5010.equals(responseFormat)) {
				benefitAccumRequest = helper.get27xBenefitAccumRequest5010(
						contract, environment);
			}
			

			// Appending the xmlText to the Request
			String xmlText = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			benefitAccumRequest = xmlText + "\n" + benefitAccumRequest;
			//log.info("Request is " + benefitAccumRequest);
			// Invoking Service using MQCall
			time = System.currentTimeMillis();
			mqAdapter.setSrcLgcl(systemConfigurationVO.getSenderID());
			mqAdapter.setSourceEnvironment(systemConfigurationVO.getSourceEnvironment());
			mqAdapter.setDestinationEnvironment(systemConfigurationVO.getDestinationEnvironment());
			String id = mqAdapter.put27xBenefitRequest(benefitAccumRequest,
					productionFlag, system, responseFormat);
			log.error("Request is>>>"+benefitAccumRequest);
			benefitAccumResponse = mqAdapter.get27xBenefitResponse(id,
					productionFlag);
			log.error("Response is>>>"+benefitAccumResponse);
			time = System.currentTimeMillis() - time;
			log.info(ESAPI.encoder().encodeForHTML(" The MQ turn around time for Static Benefit Information is "
							+ time
							+ " milliseconds for Contract Id ="
							+ contract.getContractId()
							+ ", System ="
							+ contract.getSystem()));

			// Processing response
			try {
				if (DomainConstants.RESPONSE_FORMAT_4010.equals(responseFormat)) {
					contractInfo = helper.process27xBenefitAccumResponse(
							benefitAccumResponse, system);
				}
				else if(DomainConstants.RESPONSE_FORMAT_5010.equals(responseFormat)){
					contractInfo = helper.process27xBenefitAccumResponse5010(
							benefitAccumResponse, system);
				}
				pagedResponseContracts.add(contractInfo);
				log.debug("Contract is " + contract);
			} catch (EBXException e) {
				throw new EBXException(e.getMessage(), e, e.getMessage(),
						"Request is not having valid Data");
			}
			pageCount++;
		} while (pageCount < maximumPagsSize);
		log.info("Size of Paged List is " + pagedResponseContracts.size());
		return pagedResponseContracts;
	}
	
	 public boolean isInactiveContract(String system, String contractId) throws EBXException, Exception{
		 
		 if(!BxUtil.hasText(system) || !BxUtil.hasText(contractId)){
			 return false;
		 }
		
		 if("ALL".equalsIgnoreCase(system.trim())){
			 return ewpdContractInfoRepository.isInactiveContract(contractId)||
			 isgContractInfoRepository.isInactiveContract(contractId) ||
			 lgContractInfoRepository.isInactiveContract(contractId);
		 }else if("LG".equalsIgnoreCase(system.trim())){
			 return lgContractInfoRepository.isInactiveContract(contractId);
		 }else if("ISG".equalsIgnoreCase(system.trim())){
			 return isgContractInfoRepository.isInactiveContract(contractId);
		 }else if("EWPD".equalsIgnoreCase(system.trim())){
			 return ewpdContractInfoRepository.isInactiveContract(contractId);
		 }
		 
		 return false;
	 }

	public ContractInfoRepository getEwpdContractInfoRepository() {
		return ewpdContractInfoRepository;
	}

	public void setEwpdContractInfoRepository(
			ContractInfoRepository ewpdContractInfoRepository) {
		this.ewpdContractInfoRepository = ewpdContractInfoRepository;
	}

	public ContractInfoRepository getIsgContractInfoRepository() {
		return isgContractInfoRepository;
	}

	public void setIsgContractInfoRepository(
			ContractInfoRepository isgContractInfoRepository) {
		this.isgContractInfoRepository = isgContractInfoRepository;
	}

	public ContractInfoRepository getLgContractInfoRepository() {
		return lgContractInfoRepository;
	}

	public void setLgContractInfoRepository(
			ContractInfoRepository lgContractInfoRepository) {
		this.lgContractInfoRepository = lgContractInfoRepository;
	}

	public MQAdapter getMqAdapter() {
		return mqAdapter;
	}

	public void setMqAdapter(MQAdapter mqAdapter) {
		this.mqAdapter = mqAdapter;
	}
	// BXNI June Release change - Start
	   
    /* (non-Javadoc)
     * @see com.wellpoint.ets.ebx.simulation.domain.service.ContractService#getStartDates(java.lang.String, java.lang.String)
     */
    public List<String> getStartDates(String system, String contractId, String enviornment) {
    	
    	if(DomainConstants.SYSTEM_LG.equalsIgnoreCase(system)){
			 return lgContractInfoRepository.getStartDates(contractId, enviornment);
		 }else if(DomainConstants.SYSTEM_ISG.equalsIgnoreCase(system)){
			 return isgContractInfoRepository.getStartDates(contractId, enviornment);
		 }else if(DomainConstants.SYSTEM_EWPD.equalsIgnoreCase(system)) {
			 return ewpdContractInfoRepository.getStartDates(contractId, enviornment);
		 } else {
			 return new ArrayList<String>();
		 }
    }
       
    /* (non-Javadoc)
     * @see com.wellpoint.ets.ebx.simulation.domain.service.ContractService#getLatestVersion(java.lang.String)
     */
    public String getLatestVersion(String contractId) {
    	return ewpdContractInfoRepository.getLatestVersion(contractId);
    }
    // BXNI June Release change - End

}
