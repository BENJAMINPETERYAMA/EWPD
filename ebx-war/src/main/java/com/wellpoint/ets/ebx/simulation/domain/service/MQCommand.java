package com.wellpoint.ets.ebx.simulation.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import org.owasp.esapi.ESAPI;
import org.springframework.jms.JmsException;

import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.service.DomainConstants;
import com.wellpoint.ets.bx.mapping.util.BxUtil;
import com.wellpoint.ets.ebx.simulation.util.ContractMappingValidator;
import com.wellpoint.ets.ebx.simulation.util.SimulationHelper;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

public class MQCommand implements Runnable {

	public static final Logger log = Logger.getLogger(MQCommand.class);

	protected String errorType;

	protected ContractVO contract;

	protected MQAdapter mqAdapter;

	protected String benefitAccumResponse;

	protected List mQResponseXMLList;
	
	protected SystemConfigurationVO systemConfigurationVO;

	public List getGenericEBStringList() {
		return genericEBStringList;
	}

	public void setGenericEBStringList(List genericEBStringList) {
		this.genericEBStringList = genericEBStringList;
	}

	protected List genericEBStringList;
	/**
	 * The log for Validation Response due to an exception.
	 */
	private EBXException validationException;

	/**
	 * List of MQ Error codes after validations performed.
	 */
	private List mQErrorContractMappingVOList;

	public String getBenefitAccumResponse() {
		return benefitAccumResponse;
	}

	public void setBenefitAccumResponse(String benefitAccumResponse) {
		this.benefitAccumResponse = benefitAccumResponse;
	}

	public MQAdapter getMqAdapter() {
		return mqAdapter;
	}

	public void setMqAdapter(MQAdapter mqAdapter) {
		this.mqAdapter = mqAdapter;
	}

	public ContractVO getContract() {
		return contract;
	}

	public void setContract(ContractVO contract) {
		this.contract = contract;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public SystemConfigurationVO getSystemConfigurationVO() {
		return systemConfigurationVO;
	}

	public void setSystemConfigurationVO(SystemConfigurationVO systemConfigurationVO) {
		this.systemConfigurationVO = systemConfigurationVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run() Thread run method.
	 */
	public void run() {
		ContractMappingValidator mappingValidator = new ContractMappingValidator();
		boolean mQError = false;
		SimulationHelper helper = new SimulationHelper();
		try {
			// Change as part of Defect fix done on Pagination - Start
			mQResponseXMLList = new ArrayList();
			ResourceBundle rb = ResourceBundle
					.getBundle(DomainConstants.TWENTY_SEVEN_X_REQUEST, Locale
							.getDefault());
			String maxPageSize = rb.getString("maxPagesSupported");
			int maximumPagsSize = 1;
			if (BxUtil.isInteger(maxPageSize)) {
				maximumPagsSize = Integer.parseInt(maxPageSize);
			}
			int pageCount = 0;
			ContractVO contractInfo = null;
			do {
				if (pageCount == 0) {
					contract.setMoreDataForPagination(rb.getString("moreData"));
					contract.setNextPageSize(rb
							.getString("sbiAdditionalPageKeys"));
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
				String benefitAccumResponse = getBenefitAccumInfoForErrorValidation(contract);
				if (null == benefitAccumResponse) {
					mQError = true;
					break;
				} else if (null != benefitAccumResponse) {
					contractInfo = helper.checkForMQExceptionAndHeader(
							benefitAccumResponse, contract.getSystem());
					mQResponseXMLList.add(benefitAccumResponse);
				}
				pageCount++;
			} while (pageCount < maximumPagsSize);
		} catch (JmsException jmsEx) {
			log.error("JmsException: " + jmsEx.getMessage());
			mQError = true;
		} catch (JMSException e) {
			log.error("JMSException: " + e.getMessage());
			mQError = true;
		} catch (EBXException e) {
			log.error("EBXException: " + e.getMessage());
			errorType = e.getMessage();
		} catch (XmlException e) {
			log.error("XmlException: " + e.getMessage());
			mQError = true;
		} catch (MissingResourceException e) {
			log.error("MissingResourceException: " + e.getMessage());
			mQError = true;
		} finally {
			try {
				if (mQError) {
						errorType = DomainConstants.MQ_EXCEPTION_GENERAL_ERROR;
				}
				setValidationException(null);
				helper
						.process27xBenefitAccumResponseForErrorValidation(mQResponseXMLList);
				genericEBStringList = helper.getGenericEBStringList();
				mQErrorContractMappingVOList = mappingValidator
						.validate27xBenefitAccumsResponse(helper, errorType,
								contract.getSystem());
			} catch (EBXException ex) {
				setValidationException(ex);
			} catch (Exception ex) {
				setValidationException(new EBXException(
						"Exception during validation of 27X Benefit Accums response",
						ex,
						"Exception during validation of 27X Benefit Accums response",
						ex.getMessage()));
			}
		}
	}

	/**
	 * @param contract
	 *            contract
	 * @return String
	 * @throws JMSException
	 *             JMSException Benefit Accums info being fetched.
	 * @throws EBXException
	 */
	private String getBenefitAccumInfoForErrorValidation(ContractVO contract)
			throws JMSException, EBXException {
		long time;
		/**Modified for BXNI requirement T6.1.1.2.WGSSTAR.eBX.6**/
		boolean productionFlag = contract.isProductionFlag();
		/**Ends Modification for BXNI requirement T6.1.1.2.WGSSTAR.eBX.6**/
		String benefitAccumResponse = "";
		String system = "WGS";
		String environment = "";
		SimulationHelper helper = new SimulationHelper();
		// Invoking Service using MQCall
		time = System.currentTimeMillis();
		if(systemConfigurationVO == null){
			throw new EBXException("Error Validation against 27x static response failed, as configuration does not exist for this functionality");
		}else{
			mqAdapter.setSrcLgcl(systemConfigurationVO.getSenderID());
			mqAdapter.setSourceEnvironment(systemConfigurationVO.getSourceEnvironment());
			mqAdapter.setDestinationEnvironment(systemConfigurationVO.getDestinationEnvironment());
		}
		String benefitAccumRequest = helper.get27xBenefitAccumRequest5010(contract,
				environment);
		String id = mqAdapter.put27xBenefitRequest(benefitAccumRequest,
				productionFlag, system, DomainConstants.RESPONSE_FORMAT_5010);
		if(null != mqAdapter){
		log.info("mqAdapter. Starts HERE");
		log.info("mqAdapter.getEBSVersion5010() -- > "+mqAdapter.getEBSVersion5010());
		log.info("mqAdapter.getDestinationEnvironment() -- > "+ESAPI.encoder().encodeForHTML(mqAdapter.getDestinationEnvironment()));
		log.info("mqAdapter.getEBSVersion5010() -- > "+mqAdapter.getEBSVersion5010());
		log.info("mqAdapter.getLgclEnvrnmt() -- > "+ESAPI.encoder().encodeForHTML(mqAdapter.getLgclEnvrnmt()));
		log.info("mqAdapter.getSourceEnvironment() -- > "+ESAPI.encoder().encodeForHTML(mqAdapter.getSourceEnvironment()));
		log.info("mqAdapter. ENDS HERE");
		}
		
		benefitAccumResponse = mqAdapter.get27xBenefitResponse(id,
				productionFlag);
		time = System.currentTimeMillis() - time;
		log.info(ESAPI.encoder().encodeForHTML(" The MQ turn around time for Contract mapping validation is "
				+ time + " milliseconds for Contract Id ="
				+ contract.getContractId()));

		return benefitAccumResponse;
	}

	/**
	 * @return
	 */
	public List getMQErrorCodesList() {
		return mQErrorContractMappingVOList;
	}

	/**
	 * @param errorCodesList
	 */
	public void setMQErrorCodesList(List errorCodesList) {
		mQErrorContractMappingVOList = errorCodesList;
	}

	/**
	 * @return EBXException
	 */
	public EBXException getValidationException() {
		return validationException;
	}

	/**
	 * @param validationException
	 */
	public void setValidationException(EBXException validationException) {
		this.validationException = validationException;
	}

	public List getMQResponseXMLList() {
		return mQResponseXMLList;
	}

	public void setMQResponseXMLList(List responseXMLList) {
		mQResponseXMLList = responseXMLList;
	}

}
