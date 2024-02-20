package com.wellpoint.ets.ebx.simulation.webservices.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.wellpoint.ets.bx.mapping.domain.entity.AuditTrail;
import com.wellpoint.ets.bx.mapping.domain.entity.EB03Association;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaCodeValue;
import com.wellpoint.ets.bx.mapping.domain.entity.HippaSegment;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.Rule;
import com.wellpoint.ets.bx.mapping.domain.entity.SPSId;
import com.wellpoint.ets.bx.mapping.domain.entity.Variable;
import com.wellpoint.ets.bx.mapping.domain.exception.EBXException;
import com.wellpoint.ets.bx.mapping.domain.vo.HippaSegmentMappingVO;
import com.wellpoint.ets.bx.mapping.domain.vo.User;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorExclusionDetailVO;
import com.wellpoint.ets.ebx.referencedata.vo.ErrorExclusionVO;
import com.wellpoint.ets.ebx.simulation.application.SimulationFacade;
import com.wellpoint.ets.ebx.simulation.vo.ContractMappingVO;
import com.wellpoint.ets.ebx.simulation.vo.ContractVO;
import com.wellpoint.ets.ebx.simulation.vo.ErrorDetailVO;
import com.wellpoint.ets.ebx.simulation.vo.HIPAA270BXVO;
import com.wellpoint.ets.ebx.simulation.vo.MajorHeadingsVO;
import com.wellpoint.ets.ebx.simulation.vo.MinorHeadingsVO;
import com.wellpoint.ets.ebx.simulation.webservices.exception.SimulationWebServiceException;
import com.wellpoint.ets.ebx.simulation.webservices.vo.AuditTrailWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.ContractMappingWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.ContractWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.EB03AssociationWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.ErrorDetailWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.ErrorExclusionDetailWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.ErrorExclusionWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.HippaCodeValueWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.HippaSegmentMappingWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.HippaSegmentWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.MajorHeadingsWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.MappingWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.MinorHeadingsWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.RuleWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.SPSIdWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.UserWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.vo.VariableWebServiceVO;
import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;

public class SimulationWebServiceImpl implements SimulationWebService {

	private SimulationFacade simulationFacade;

	private static final String EWPD_SYSTEM = "EWPD";

	private final static Logger logger = Logger
			.getLogger(SimulationWebServiceImpl.class.getName());

	public SimulationWebServiceImpl() {
	}

	public SimulationFacade getSimulationFacade() {
		return simulationFacade;
	}

	public void setSimulationFacade(SimulationFacade simulationFacade) {
		this.simulationFacade = simulationFacade;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContractWebServiceVO> getContractInfo(
			ContractWebServiceVO contractWebServiceVO, String environment,
			boolean isEBxReportFlag) throws SimulationWebServiceException {
		// TODO Auto-generated method stub
		logger.info("Inside getContractInfo() method");
		logger.info("simulationFacade-->" + simulationFacade);

		List<ContractWebServiceVO> contractList = null;

		SystemConfigurationVO systemConfigurationVO = null;

		String functionality = "STATIC REPORT";
		String systemSelected = "WGS/STAR";

		try {
			long time1 = System.currentTimeMillis();
			List<SystemConfigurationVO> systemConfigurationVOList = simulationFacade
					.loadBackEndRegionBasedOnSystem(functionality,
							systemSelected, environment);
			long time2 = System.currentTimeMillis();
			logger
					.info("***********TIME TAKEN IN MILLI SECONDS TO FETCH SYSTEM CONFIGURATION DATA***********:"
							+ (time2 - time1));
			if (systemConfigurationVOList != null
					&& systemConfigurationVOList.size() > 0) {
				systemConfigurationVO = systemConfigurationVOList.get(0);
			} else {
				throw new SimulationWebServiceException(
						"************SystemConfigurationVO List is null OR the List is empty!************");
			}
			logger.info("systemConfigurationVO-->" + systemConfigurationVO);
		} catch (EBXException ebxExcep) {
			logger.fatal(ebxExcep.getMessage(), ebxExcep);
			ebxExcep.printStackTrace();
			systemConfigurationVO = null;
			SimulationWebServiceException simExcep = new SimulationWebServiceException(
					ebxExcep.getMessage());
			simExcep.setDisplayDescription(ebxExcep.getDisplayDescription());
			simExcep.setDisplayMessage(ebxExcep.getDisplayMessage());
			simExcep.setStackTrace(ebxExcep.getStackTrace());
			throw simExcep;
		} catch (SimulationWebServiceException simExcep) {
			logger.fatal(simExcep.getMessage(), simExcep);
			simExcep.printStackTrace();
			systemConfigurationVO = null;
			throw simExcep;
		} catch (Throwable t) {
			logger.fatal(t.getMessage(), t);
			t.printStackTrace();
			systemConfigurationVO = null;
			SimulationWebServiceException simExcep = new SimulationWebServiceException(
					t.getMessage());
			simExcep.setStackTrace(t.getStackTrace());
			throw simExcep;
		}
		try {
			List<ContractVO> listFromFacade = null;
			ContractVO contractVO = new ContractVO();
			contractVO.setSystem(contractWebServiceVO.getSystem());
			contractVO.setContractId(contractWebServiceVO.getContractId());
			contractVO
					.setEffectiveDate(contractWebServiceVO.getEffectiveDate());
			if (contractWebServiceVO != null
					&& contractWebServiceVO.getSystem() != null
					&& contractWebServiceVO.getSystem().equalsIgnoreCase(
							EWPD_SYSTEM)) {
				contractVO.setVersion(contractWebServiceVO.getVersion());
			}
			long time1 = System.currentTimeMillis();

			listFromFacade = simulationFacade.getContractInfo(contractVO,
					environment, isEBxReportFlag, systemConfigurationVO);

			long time2 = System.currentTimeMillis();
			logger
					.info("***********TIME TAKEN IN MILLI SECONDS TO PROCESS SIMULATION WEB SERVICE***********:"
							+ (time2 - time1));

			if (listFromFacade != null && listFromFacade.size() > 0) {

				contractList = populateContractWebServiceList(listFromFacade,
						systemConfigurationVO);
			}
		} catch (EBXException ebxExcep) {
			SimulationWebServiceException simExcep = new SimulationWebServiceException(
					ebxExcep.getMessage());
			simExcep.setDisplayDescription(ebxExcep.getDisplayDescription());
			simExcep.setDisplayMessage(ebxExcep.getDisplayMessage());
			simExcep.setStackTrace(ebxExcep.getStackTrace());
			throw simExcep;
		} catch (Throwable t) {
			logger.fatal(t.getMessage(), t);
			t.printStackTrace();
			systemConfigurationVO = null;
			SimulationWebServiceException simExcep = new SimulationWebServiceException(
					t.getMessage());
			simExcep.setStackTrace(t.getStackTrace());
			throw simExcep;
		}
		if (contractList != null) {
			logger.info("********Printing contractList contents ********:"
					+ contractList.size());
			for (ContractWebServiceVO vo : contractList) {
				logger.info("Contract Id----->" +ESAPI.encoder().encodeForHTML(vo.getContractId()));
			logger
					.info("********contractList.size()-->:"
							+ contractList.size());
			}
		} else {
			logger.info("********contractList is null********");
		}
		return contractList;
	}

	@SuppressWarnings("unchecked")
	private List<ContractWebServiceVO> populateContractWebServiceList(
			List<ContractVO> listFromFacade,
			SystemConfigurationVO systemConfigurationVO) {
		List<ContractWebServiceVO> contractWebServiceList = null;
		if (listFromFacade != null && listFromFacade.size() > 0) {
			contractWebServiceList = new ArrayList<ContractWebServiceVO>(
					listFromFacade != null ? listFromFacade.size() : 0);
			for (ContractVO contractVO : listFromFacade) {
				contractWebServiceList
						.add(populateContractWebService(contractVO));
			}
		}
		if (contractWebServiceList != null && contractWebServiceList.size() > 0) {
			ContractWebServiceVO contractWebService = contractWebServiceList
					.get(0);
			contractWebService.setBackEndRegion(systemConfigurationVO
					.getBackEndRegion());
			contractWebServiceList.set(0, contractWebService);
		}
		return contractWebServiceList;
	}

	private ContractWebServiceVO populateContractWebService(
			ContractVO contractVO) {
		ContractWebServiceVO contractWebServiceVO = new ContractWebServiceVO();
		if (contractVO != null) {
			contractWebServiceVO.setBusinessEntity(contractVO
					.getBusinessEntity());
			contractWebServiceVO.setContractId(contractVO.getContractId());
			contractWebServiceVO
					.setContractMappingVOList(populateContractMappingWebServiceList(contractVO
							.getContractMappingVOList()));
			contractWebServiceVO
					.setEffectiveDate(contractVO.getEffectiveDate());
			contractWebServiceVO
					.setErrorCodesList(populateErrorDetailWebServiceList(contractVO
							.getErrorCodesList()));

			contractWebServiceVO
					.setErrorExclusionDetailVO(populateErrorExclusionDetailWebService(contractVO
							.getErrorExclusionDetailVO()));

			contractWebServiceVO
					.setErrorExclusionDetailList(populateErrorExclusionWebServiceList(contractVO
							.getErrorExclusionVOList()));

			contractWebServiceVO.setExpiryDate(contractVO.getExpiryDate());
			contractWebServiceVO.setGroupStateHQ(contractVO.getGroupStateHQ());
			contractWebServiceVO
					.setMajorHeadings(populateMajorHeadingsWebServiceMap(contractVO
							.getMajorHeadings()));
			contractWebServiceVO.setSystem(contractVO.getSystem());
			contractWebServiceVO.setVersion(contractVO.getVersion());
		}
		return contractWebServiceVO;
	}

	private HippaSegmentMappingWebServiceVO populateHippaSegementMappingWebService(
			HippaSegmentMappingVO hippaSegmentMappingVO) {
		HippaSegmentMappingWebServiceVO hippaSegmentMappingWebServiceVO = new HippaSegmentMappingWebServiceVO();
		if (hippaSegmentMappingVO != null) {
			hippaSegmentMappingWebServiceVO.setAccum(hippaSegmentMappingVO
					.getAccum());
			hippaSegmentMappingWebServiceVO.setEb01(hippaSegmentMappingVO
					.getEb01());
			hippaSegmentMappingWebServiceVO.setEb02(hippaSegmentMappingVO
					.getEb02());
			hippaSegmentMappingWebServiceVO.setEb03(hippaSegmentMappingVO
					.getEb03());
			hippaSegmentMappingWebServiceVO.setEb06(hippaSegmentMappingVO
					.getEb06());
			hippaSegmentMappingWebServiceVO.setEb09(hippaSegmentMappingVO
					.getEb09());
			hippaSegmentMappingWebServiceVO.setHsd01(hippaSegmentMappingVO
					.getHsd01());
			hippaSegmentMappingWebServiceVO.setHsd02(hippaSegmentMappingVO
					.getHsd02());
			hippaSegmentMappingWebServiceVO.setHsd03(hippaSegmentMappingVO
					.getHsd03());
			hippaSegmentMappingWebServiceVO.setHsd04(hippaSegmentMappingVO
					.getHsd04());
			hippaSegmentMappingWebServiceVO.setHsd05(hippaSegmentMappingVO
					.getHsd05());
			hippaSegmentMappingWebServiceVO.setHsd06(hippaSegmentMappingVO
					.getHsd06());
			hippaSegmentMappingWebServiceVO.setHsd07(hippaSegmentMappingVO
					.getHsd07());
			hippaSegmentMappingWebServiceVO.setHsd08(hippaSegmentMappingVO
					.getHsd08());
			/*hippaSegmentMappingWebServiceVO.setIi02(hippaSegmentMappingVO
					.getIi02());
			hippaSegmentMappingWebServiceVO.setMessage(hippaSegmentMappingVO
					.getMessage());
			hippaSegmentMappingWebServiceVO
					.setMessageTypeRequired(hippaSegmentMappingVO
							.getMessageTypeRequired());
			hippaSegmentMappingWebServiceVO
					.setNoteTypeCode(hippaSegmentMappingVO.getNoteTypeCode());*/
			hippaSegmentMappingWebServiceVO
					.setSensitiveBenefitIndicator(hippaSegmentMappingVO
							.getSensitiveBenefitIndicator());
			hippaSegmentMappingWebServiceVO
					.setUtilizationMgmntRule(hippaSegmentMappingVO
							.getUtilizationMgmntRule());
			hippaSegmentMappingWebServiceVO
					.setVariableMappingStatus(hippaSegmentMappingVO
							.getVariableMappingStatus());
		}
		return hippaSegmentMappingWebServiceVO;
	}

	@SuppressWarnings("unchecked")
	private HippaSegmentWebServiceVO populateHippaSegementWebService(
			HippaSegment hippaSegment) {
		HippaSegmentWebServiceVO hippaSegmentWebServiceVO = new HippaSegmentWebServiceVO();

		if (hippaSegment != null) {
			hippaSegmentWebServiceVO.setDescription(hippaSegment
					.getDescription());

			List<HippaCodeValueWebServiceVO> listForPossibleValuesForEwpd = null;
			List<String> listForPossibleValuesForLgIsg = null;
			if (hippaSegment.getHippaCodePossibleValues() != null
					&& hippaSegment.getHippaCodePossibleValues().size() > 0) {
				List<Object> list = hippaSegment.getHippaCodePossibleValues();
				listForPossibleValuesForEwpd = new ArrayList<HippaCodeValueWebServiceVO>();
				listForPossibleValuesForLgIsg = new ArrayList<String>();
				if (list != null && list.size() > 0) {
					for (Object object : list) {
						if (object instanceof HippaCodeValue) {
							listForPossibleValuesForEwpd
									.add(populateHippaCodeValueWebService((HippaCodeValue) object));
						} else if (object instanceof String) {
							listForPossibleValuesForLgIsg.add((String) object);
						}
					}
				}
				hippaSegmentWebServiceVO
						.setHippaCodePossibleValuesForLgIsg(listForPossibleValuesForLgIsg);
				hippaSegmentWebServiceVO
						.setHippaCodePossibleValuesForEwpd(listForPossibleValuesForEwpd);
			}

			List<HippaCodeValueWebServiceVO> listForSelectedValuesForEwpd = null;
			List<String> listForSelectedValuesForLgIsg = null;
			if (hippaSegment.getHippaCodeSelectedValues() != null
					&& hippaSegment.getHippaCodeSelectedValues().size() > 0) {
				List<Object> list = hippaSegment.getHippaCodeSelectedValues();
				listForSelectedValuesForEwpd = new ArrayList<HippaCodeValueWebServiceVO>();
				listForSelectedValuesForLgIsg = new ArrayList<String>();
				if (list != null && list.size() > 0) {
					for (Object object : list) {
						if (object instanceof HippaCodeValue) {
							listForSelectedValuesForEwpd
									.add(populateHippaCodeValueWebService((HippaCodeValue) object));
						} else if (object instanceof String) {
							listForSelectedValuesForLgIsg.add((String) object);
						}
					}
				}
				hippaSegmentWebServiceVO
						.setHippaCodeSelectedValuesForLgIsg(listForSelectedValuesForLgIsg);
				hippaSegmentWebServiceVO
						.setHippaCodeSelectedValuesForEwpd(listForSelectedValuesForEwpd);
			}

			hippaSegmentWebServiceVO.setHippaSegmentDefinition(hippaSegment
					.getHippaSegmentDefinition());
			hippaSegmentWebServiceVO.setHippaSegmentName(hippaSegment
					.getHippaSegmentName());
			hippaSegmentWebServiceVO.setName(hippaSegment.getName());
			List<EB03AssociationWebServiceVO> eb03AssnWsList = populateEb03AssociationWebService(hippaSegment);
			hippaSegmentWebServiceVO.setEb03Association(eb03AssnWsList);
		}
		return hippaSegmentWebServiceVO;
	}

	/**
	 * @param hippaSegment
	 */
	private List<EB03AssociationWebServiceVO> populateEb03AssociationWebService(HippaSegment hippaSegment) {
		List<EB03AssociationWebServiceVO> eb03AssnWsList = new ArrayList<EB03AssociationWebServiceVO>();
		if(null != hippaSegment.getEb03Association() && hippaSegment.getEb03Association().size() > 0) {
			List<String> assnList = getDistinctEB03Values(hippaSegment);
			if(null != assnList && assnList.size() != 0) {
				for(String assnStr : assnList)
				{
					EB03AssociationWebServiceVO eb03AssnWs = new EB03AssociationWebServiceVO();
					for(EB03Association eb03Assn : hippaSegment.getEb03Association()) {
						if(assnStr.equals(eb03Assn.getEb03().getValue())) {
							if(null != eb03Assn.getEb03()) {
								HippaCodeValueWebServiceVO hippaEb03Code = new HippaCodeValueWebServiceVO();
								hippaEb03Code.setValue(eb03Assn.getEb03().getValue());
								eb03AssnWs.setEb03(hippaEb03Code);
								eb03AssnWs.setEb03String(eb03Assn.getEb03().getValue());
							}
							if(null != eb03Assn.getNoteType()) {
								HippaCodeValueWebServiceVO hippaNoteTypeCode = new HippaCodeValueWebServiceVO();
								hippaNoteTypeCode.setValue(eb03Assn.getNoteType().getValue());
								eb03AssnWs.setNoteType(hippaNoteTypeCode);
							}
							if(null != eb03Assn.getIii02List() && eb03Assn.getIii02List().size() > 0) {
								List<HippaCodeValueWebServiceVO> hippaIii02List = new ArrayList<HippaCodeValueWebServiceVO>();
								for(HippaCodeValue iii02Code : eb03Assn.getIii02List()) {
									HippaCodeValueWebServiceVO hippaIii02Code = new HippaCodeValueWebServiceVO();
									hippaIii02Code.setValue(iii02Code.getValue());
									hippaIii02List.add(hippaIii02Code);
								}
								eb03AssnWs.setIii02List(hippaIii02List);
							}
							eb03AssnWs.setMessage(eb03Assn.getMessage());
							eb03AssnWs.setMsgReqdInd(eb03Assn.getMsgReqdInd());
						}
					}
					eb03AssnWsList.add(eb03AssnWs);
				}
			}	
		}
		return eb03AssnWsList;
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

	private HippaCodeValueWebServiceVO populateHippaCodeValueWebService(
			HippaCodeValue hippaCodeValue) {
		HippaCodeValueWebServiceVO hippaCodeValueWebServiceVO = new HippaCodeValueWebServiceVO();
		if (hippaCodeValue != null) {
			hippaCodeValueWebServiceVO.setDescription(hippaCodeValue
					.getDescription());
			hippaCodeValueWebServiceVO.setHippaCodeValueSysId(hippaCodeValue
					.getHippaCodeValueSysId());
			hippaCodeValueWebServiceVO.setSeq_num(hippaCodeValue.getSeq_num());
			hippaCodeValueWebServiceVO.setValue(hippaCodeValue.getValue());
		}
		return hippaCodeValueWebServiceVO;
	}

	private List<AuditTrailWebServiceVO> populatateAuditTrailWebServiceList(
			List<AuditTrail> auditTrailList) {
		List<AuditTrailWebServiceVO> listForWebService = new ArrayList<AuditTrailWebServiceVO>(
				auditTrailList != null ? auditTrailList.size() : 0);
		if (auditTrailList != null && auditTrailList.size() > 0) {
			for (AuditTrail auditTrail : auditTrailList) {
				listForWebService
						.add(populatateAuditTrailWebService(auditTrail));
			}
		}
		return listForWebService;
	}

	private AuditTrailWebServiceVO populatateAuditTrailWebService(
			AuditTrail auditTrail) {
		AuditTrailWebServiceVO auditTrailWebServiceVO = new AuditTrailWebServiceVO();
		if (auditTrail != null) {
			auditTrailWebServiceVO.setCreatedAuditDate(auditTrail
					.getCreatedAuditDate());
			auditTrailWebServiceVO.setCreatedTmStamp(auditTrail
					.getCreatedTmStamp());
			auditTrailWebServiceVO.setCreatedUser(auditTrail.getCreatedUser());
			auditTrailWebServiceVO
					.setHippaSegmentMappingVO(populateHippaSegementMappingWebService(auditTrail
							.getHippaSegmentMappingVO()));
			auditTrailWebServiceVO.setMappingStatus(auditTrail
					.getMappingStatus());
			auditTrailWebServiceVO.setRuleId(auditTrail.getRuleId());
			auditTrailWebServiceVO.setSpsId(auditTrail.getSpsId());
			auditTrailWebServiceVO.setSystemComments(auditTrail
					.getSystemComments());
			auditTrailWebServiceVO
					.setUserComments(auditTrail.getUserComments());
			auditTrailWebServiceVO.setVariableAuditStatus(auditTrail
					.getVariableAuditStatus());
			auditTrailWebServiceVO
					.setVariableDesc(auditTrail.getVariableDesc());
			auditTrailWebServiceVO.setVariableId(auditTrail.getVariableId());
		}
		return auditTrailWebServiceVO;
	}

	private ContractMappingWebServiceVO populateContractMappingWebService(
			ContractMappingVO contractMappingVO) {
		ContractMappingWebServiceVO contractMappingWebServiceVO = new ContractMappingWebServiceVO();
		if (contractMappingVO != null) {
			contractMappingWebServiceVO
					.setAccum(populateHippaSegementWebService(contractMappingVO
							.getAccum()));
			contractMappingWebServiceVO.setAuditLock(contractMappingVO
					.getAuditLock());
			contractMappingWebServiceVO
					.setAuditTrails(populatateAuditTrailWebServiceList(contractMappingVO
							.getAuditTrails()));
			contractMappingWebServiceVO
					.setBenefitEffectiveEndDate(populateHippaSegementWebService(contractMappingVO
							.getBenefitEffectiveEndDate()));
			contractMappingWebServiceVO
					.setBenefitEffectiveStartDate(populateHippaSegementWebService(contractMappingVO
							.getBenefitEffectiveStartDate()));
			contractMappingWebServiceVO.setBusinessEntity(contractMappingVO
					.getBusinessEntity());
			contractMappingWebServiceVO.setBusinessGroup(contractMappingVO
					.getBusinessGroup());
			contractMappingWebServiceVO
					.setContractMapping(populateContractMappingWebService(contractMappingVO
							.getContractMapping()));
			contractMappingWebServiceVO.setCreatedTmStamp(contractMappingVO
					.getCreatedTmStamp());
			contractMappingWebServiceVO.setDataFeedInd(contractMappingVO
					.getDataFeedInd());
			contractMappingWebServiceVO.setDatafeedStatus(contractMappingVO
					.getDatafeedStatus());
			contractMappingWebServiceVO
					.setEb01(populateHippaSegementWebService(contractMappingVO
							.getEb01()));
			contractMappingWebServiceVO
					.setEb02(populateHippaSegementWebService(contractMappingVO
							.getEb02()));
			contractMappingWebServiceVO
					.setEb03(populateHippaSegementWebService(contractMappingVO
							.getEb03()));
			contractMappingWebServiceVO
					.setEb04(populateHippaSegementWebService(contractMappingVO
							.getEb04()));
			contractMappingWebServiceVO
					.setEb05(populateHippaSegementWebService(contractMappingVO
							.getEb05()));
			contractMappingWebServiceVO
					.setEb06(populateHippaSegementWebService(contractMappingVO
							.getEb06()));
			contractMappingWebServiceVO
					.setEb07(populateHippaSegementWebService(contractMappingVO
							.getEb07()));
			contractMappingWebServiceVO
					.setEb08(populateHippaSegementWebService(contractMappingVO
							.getEb08()));
			contractMappingWebServiceVO
					.setEb09(populateHippaSegementWebService(contractMappingVO
							.getEb09()));
			contractMappingWebServiceVO
					.setEb10(populateHippaSegementWebService(contractMappingVO
							.getEb10()));
			contractMappingWebServiceVO
					.setEb11(populateHippaSegementWebService(contractMappingVO
							.getEb11()));
			contractMappingWebServiceVO
					.setEb12(populateHippaSegementWebService(contractMappingVO
							.getEb12()));
			contractMappingWebServiceVO
					.setEndAge(populateHippaSegementWebService(contractMappingVO
							.getEndAge()));
			contractMappingWebServiceVO.setEndAgeCodedValue(contractMappingVO
					.getEndAgeValue());
			contractMappingWebServiceVO
					.setErrorCodesList(populateErrorDetailWebServiceList(contractMappingVO
							.getErrorCodesList()));
			contractMappingWebServiceVO.setFinalized(contractMappingVO
					.isFinalized());
			contractMappingWebServiceVO
					.setFinalizedFlagModified(contractMappingVO
							.isFinalizedFlagModified());
			contractMappingWebServiceVO
					.setFormattedStringDate(contractMappingVO
							.getFormattedStringDate());
			/*
			 * contractMappingWebServiceVO.setHippaSegment(populateHippaSegementWebService
			 * ());
			 */
			contractMappingWebServiceVO
					.setHsd01(populateHippaSegementWebService(contractMappingVO
							.getHsd01()));
			contractMappingWebServiceVO
					.setHsd02(populateHippaSegementWebService(contractMappingVO
							.getHsd02()));
			contractMappingWebServiceVO
					.setHsd03(populateHippaSegementWebService(contractMappingVO
							.getHsd03()));
			contractMappingWebServiceVO
					.setHsd04(populateHippaSegementWebService(contractMappingVO
							.getHsd04()));
			contractMappingWebServiceVO
					.setHsd05(populateHippaSegementWebService(contractMappingVO
							.getHsd05()));
			contractMappingWebServiceVO
					.setHsd06(populateHippaSegementWebService(contractMappingVO
							.getHsd06()));
			contractMappingWebServiceVO
					.setHsd07(populateHippaSegementWebService(contractMappingVO
							.getHsd07()));
			contractMappingWebServiceVO
					.setHsd08(populateHippaSegementWebService(contractMappingVO
							.getHsd08()));
			/*contractMappingWebServiceVO
					.setIi02(populateHippaSegementWebService(contractMappingVO
							.getIi02()));*/
			contractMappingWebServiceVO.setIiiMessage(contractMappingVO
					.getIiiMessage());
			contractMappingWebServiceVO.setInTempTable(contractMappingVO
					.getInTempTable());
			contractMappingWebServiceVO.setIsMapgRequired(contractMappingVO
					.getIsMapgRequired());
			contractMappingWebServiceVO.setLastChangedTmStamp(contractMappingVO
					.getLastChangedTmStamp());
			contractMappingWebServiceVO.setMapped(contractMappingVO.isMapped());
			contractMappingWebServiceVO.setMappingComplete(contractMappingVO
					.getMappingComplete());
			contractMappingWebServiceVO.setMbu(contractMappingVO.getMbu());
			/*contractMappingWebServiceVO.setMessage(contractMappingVO
					.getMessage());
			contractMappingWebServiceVO.setMsg_type_required(contractMappingVO
					.getMsg_type_required());
			contractMappingWebServiceVO
					.setNoteTypeCode(populateHippaSegementWebService(contractMappingVO
							.getNoteTypeCode()));*/
			contractMappingWebServiceVO.setPageFrom(contractMappingVO
					.getPageFrom());
			contractMappingWebServiceVO
					.setRule(populateRuleWebService(contractMappingVO.getRule()));
			contractMappingWebServiceVO
					.setSensitiveBenefitIndicator(contractMappingVO
							.getSensitiveBenefitIndicator());
			contractMappingWebServiceVO.setSortField(contractMappingVO
					.getSortField());
			contractMappingWebServiceVO
					.setSpsId(populateSPSIdWebService(contractMappingVO
							.getSpsId()));
			contractMappingWebServiceVO
					.setStartAge(populateHippaSegementWebService(contractMappingVO
							.getStartAge()));
			contractMappingWebServiceVO.setStartAgeCodedValue(contractMappingVO
					.getStartAgeCodedValue());
			contractMappingWebServiceVO.setTierDescription(contractMappingVO
					.getTierDescription());
			contractMappingWebServiceVO.setUpdateMstr(contractMappingVO
					.isUpdateMstr());
			contractMappingWebServiceVO
					.setUser(populateUserWebService(contractMappingVO.getUser()));
			contractMappingWebServiceVO
					.setUtilizationMgmntRule(populateHippaSegementWebService(contractMappingVO
							.getUtilizationMgmntRule()));
			contractMappingWebServiceVO
					.setVariable(populateVariableWebService(contractMappingVO
							.getVariable()));
			contractMappingWebServiceVO
					.setVariableList(populateVariableWebServiceList(contractMappingVO
							.getVariableList()));
			contractMappingWebServiceVO
					.setVariableMappingStatus(contractMappingVO
							.getVariableMappingStatus());
			contractMappingWebServiceVO
					.setVariableStatusForAuditTrail(contractMappingVO
							.getVariableStatusForAuditTrail());
			contractMappingWebServiceVO.setVariableSystemId(contractMappingVO
					.getVariableSystemId());
		}
		return contractMappingWebServiceVO;
	}

	private List<ErrorDetailWebServiceVO> populateErrorDetailWebServiceList(
			List<ErrorDetailVO> errorDetailVOList) {
		List<ErrorDetailWebServiceVO> listForWebService = new ArrayList<ErrorDetailWebServiceVO>(
				errorDetailVOList != null ? errorDetailVOList.size() : 0);
		if (errorDetailVOList != null && errorDetailVOList.size() > 0) {
			for (ErrorDetailVO errorDetailVO : errorDetailVOList) {
				listForWebService
						.add(populateErrorDetailWebService(errorDetailVO));
			}
		}
		return listForWebService;
	}

	private ErrorDetailWebServiceVO populateErrorDetailWebService(
			ErrorDetailVO errorDetailVO) {
		ErrorDetailWebServiceVO errorDetailWebServiceVO = new ErrorDetailWebServiceVO();
		if (errorDetailVO != null) {
			errorDetailWebServiceVO.setError(errorDetailVO.isError());
			errorDetailWebServiceVO.setErrorCode(errorDetailVO.getErrorCode());
			errorDetailWebServiceVO.setErrorDesc(errorDetailVO.getErrorDesc());
			errorDetailWebServiceVO.setNetworkDescription(errorDetailVO
					.getNetworkDescription());
			errorDetailWebServiceVO.setAssociatedEb03(errorDetailVO.getAssociatedEb03());
		}
		return errorDetailWebServiceVO;
	}

	private RuleWebServiceVO populateRuleWebService(Rule rule) {
		RuleWebServiceVO ruleWebServiceVO = new RuleWebServiceVO();
		if (rule != null) {
			ruleWebServiceVO.setHeaderRuleId(rule.getHeaderRuleId());
			ruleWebServiceVO.setNotApplicable(rule.getNotApplicable());
			ruleWebServiceVO.setRuleDesc(rule.getRuleDesc());
		}
		return ruleWebServiceVO;
	}

	private SPSIdWebServiceVO populateSPSIdWebService(SPSId sPSId) {
		SPSIdWebServiceVO sPSIdWebServiceVO = new SPSIdWebServiceVO();
		if (sPSId != null) {
			sPSIdWebServiceVO.setLevelDesc(sPSId.getLevelDesc());
			sPSIdWebServiceVO.setSpsDesc(sPSId.getSpsDesc());
			sPSIdWebServiceVO.setSpsId(sPSId.getSpsId());
			sPSIdWebServiceVO.setSpsType(sPSId.getSpsType());
			sPSIdWebServiceVO.setLineDataType(sPSId.getLineDataType());
			sPSIdWebServiceVO.setLinePVA(sPSId.getLinePVA());
			sPSIdWebServiceVO.setLineValue(sPSId.getLineValue());
			sPSIdWebServiceVO.setSpsDetail(sPSId.getSpsDetail());
		}
		return sPSIdWebServiceVO;
	}

	private UserWebServiceVO populateUserWebService(User user) {
		UserWebServiceVO userWebServiceVO = new UserWebServiceVO();
		if (user != null) {
			userWebServiceVO.setCreatedUserName(user.getCreatedUserName());
			userWebServiceVO.setLastUpdatedUserName(user
					.getLastUpdatedUserName());
		}
		return userWebServiceVO;
	}

	private VariableWebServiceVO populateVariableWebService(Variable variable) {
		VariableWebServiceVO variableWebServiceVO = new VariableWebServiceVO();
		if (variable != null) {
			variableWebServiceVO.setAuditLock(variable.isAuditLock());
			variableWebServiceVO.setCodedStatus(variable.isCodedStatus());
			variableWebServiceVO.setCreatedDate(variable.getCreatedDate());
			variableWebServiceVO.setDataType(variable.getDataType());
			variableWebServiceVO.setDescription(variable.getDescription());
			variableWebServiceVO.setIsAdvanceSearch(variable
					.getIsAdvanceSearch());
			variableWebServiceVO.setIsgCatagory(variable.getIsgCatagory());
			variableWebServiceVO.setLgCatagory(variable.getLgCatagory());
			variableWebServiceVO.setMajorHeadings(variable.getMajorHeadings());
			variableWebServiceVO.setMappedVariable(variable.isMappedVariable());
			variableWebServiceVO.setMappingRequired(variable
					.isMappingRequired());
			variableWebServiceVO.setMessage(variable.getMessage());
			variableWebServiceVO.setMinorHeadings(variable.getMinorHeadings());
			variableWebServiceVO.setNotApplicable(variable.isNotApplicable());
			variableWebServiceVO.setPva(variable.getPva());
			variableWebServiceVO.setSensitiveBenefitIndicator(variable
					.getSensitiveBenefitIndicator());
			variableWebServiceVO.setUnmappedVariable(variable
					.isUnmappedVariable());
			variableWebServiceVO.setVariableDescription(variable
					.getVariableDescription());
			variableWebServiceVO
					.setVariableFormat(variable.getVariableFormat());
			variableWebServiceVO.setVariableId(variable.getVariableId());
			variableWebServiceVO
					.setVariableStatus(variable.getVariableStatus());
			variableWebServiceVO
					.setVariableSystem(variable.getVariableSystem());
			variableWebServiceVO.setVariableValue(variable.getVariableValue());
		}
		return variableWebServiceVO;
	}

	private List<VariableWebServiceVO> populateVariableWebServiceList(
			List<Variable> variableVOList) {
		List<VariableWebServiceVO> listForWebService = new ArrayList<VariableWebServiceVO>(
				variableVOList != null ? variableVOList.size() : 0);
		if (variableVOList != null && variableVOList.size() > 0) {
			for (Variable variable : variableVOList) {
				listForWebService.add(populateVariableWebService(variable));
			}
		}
		return listForWebService;

	}

	private Map<String, MajorHeadingsWebServiceVO> populateMajorHeadingsWebServiceMap(
			Map<String, MajorHeadingsVO> majorHeadingsMap) {
		Map<String, MajorHeadingsWebServiceVO> mapForWebService = new HashMap<String, MajorHeadingsWebServiceVO>(
				majorHeadingsMap != null ? majorHeadingsMap.size() : 0);
		if (majorHeadingsMap != null && majorHeadingsMap.size() > 0) {
			Iterator<Map.Entry<String, MajorHeadingsVO>> it = majorHeadingsMap
					.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, MajorHeadingsVO> pairs = (Map.Entry<String, MajorHeadingsVO>) it
						.next();
				// pairs.getValue());
				mapForWebService.put(pairs.getKey(),
						populateMajorHeadingsWebService(pairs.getValue()));
				// it.remove(); // avoids a ConcurrentModificationException
			}
		}
		return mapForWebService;
	}

	@SuppressWarnings("unchecked")
	private MajorHeadingsWebServiceVO populateMajorHeadingsWebService(
			MajorHeadingsVO majorHeadingsVO) {
		MajorHeadingsWebServiceVO majorHeadingsWebServiceVO = new MajorHeadingsWebServiceVO();
		if (majorHeadingsVO != null) {
			majorHeadingsWebServiceVO.setDescriptionText(majorHeadingsVO
					.getDescriptionText());
			majorHeadingsWebServiceVO
					.setMinorHeadings(populateMinorHeadingsMap(majorHeadingsVO
							.getMinorHeadings()));
		}
		return majorHeadingsWebServiceVO;
	}

	@SuppressWarnings("unchecked")
	private MinorHeadingsWebServiceVO populateMinorHeadingsWebService(
			MinorHeadingsVO minorHeadingsVO) {
		MinorHeadingsWebServiceVO minorHeadingsWebServiceVO = new MinorHeadingsWebServiceVO();
		if (minorHeadingsVO != null) {
			minorHeadingsWebServiceVO.setAdminMethodSPS(minorHeadingsVO
					.getAdminMethodSPS());
			minorHeadingsWebServiceVO.setDescriptionText(minorHeadingsVO
					.getDescriptionText());
			minorHeadingsWebServiceVO
					.setErrorCodesList(populateErrorDetailWebServiceList(minorHeadingsVO
							.getErrorCodesList()));
			minorHeadingsWebServiceVO.setFlagBenefitCovered(minorHeadingsVO
					.isFlagBenefitCovered());
			minorHeadingsWebServiceVO.setFlagBenefitCoveredNpar(minorHeadingsVO
					.isFlagBenefitCoveredNpar());
			minorHeadingsWebServiceVO.setFlagBenefitCoveredPar(minorHeadingsVO
					.isFlagBenefitCoveredPar());
			// Kritika CHanged
			/*
			 * minorHeadingsWebServiceVO
			 * .setMappings(populateMappingWebServiceMap(minorHeadingsVO
			 * .getMappings()));
			 */
			minorHeadingsWebServiceVO
					.setMappings(populateContractMappingWebServiceMap(minorHeadingsVO
							.getMappings()));

			minorHeadingsWebServiceVO
					.setRuleMapping(populateContractMappingWebService(minorHeadingsVO
							.getRuleMapping() != null ? (ContractMappingVO) minorHeadingsVO
							.getRuleMapping()
							: null));
		}
		return minorHeadingsWebServiceVO;
	}

	private List<ContractMappingWebServiceVO> populateContractMappingWebServiceList(
			List<ContractMappingVO> contractMappingVOList) {
		List<ContractMappingWebServiceVO> listForWebService = new ArrayList<ContractMappingWebServiceVO>(
				contractMappingVOList != null ? contractMappingVOList.size()
						: 0);
		if (contractMappingVOList != null && contractMappingVOList.size() > 0) {
			for (ContractMappingVO contractMappingVO : contractMappingVOList) {
				listForWebService
						.add(populateContractMappingWebService(contractMappingVO));
			}
		}
		return listForWebService;
	}

	/**
	 * @param mapping
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private MappingWebServiceVO populateMappingWebService(Mapping mapping) {
		MappingWebServiceVO mappingWebServiceVO = new MappingWebServiceVO();
		if (mapping != null) {
			mappingWebServiceVO
					.setAccum(populateHippaSegementWebService(mapping
							.getAccum()));
			mappingWebServiceVO.setAuditLock(mapping.getAuditLock());
			mappingWebServiceVO
					.setAuditTrails(populatateAuditTrailWebServiceList(mapping
							.getAuditTrails()));
			// mappingWebServiceVO.setBenefitEffectiveEndDate(populateHippaSegementWebService(mapping.getBenefitEffectiveEndDate()));
			// mappingWebServiceVO.setBenefitEffectiveStartDate(populateHippaSegementWebService(mapping.getBenefitEffectiveStartDate()));
			mappingWebServiceVO.setBusinessEntity(mapping.getBusinessEntity());
			mappingWebServiceVO.setBusinessGroup(mapping.getBusinessGroup());
			mappingWebServiceVO
					.setContractMapping(populateContractMappingWebService(mapping
							.getContractMapping()));
			mappingWebServiceVO.setCreatedTmStamp(mapping.getCreatedTmStamp());
			mappingWebServiceVO.setDataFeedInd(mapping.getDataFeedInd());
			mappingWebServiceVO.setDatafeedStatus(mapping.getDatafeedStatus());

			mappingWebServiceVO.setEb01(populateHippaSegementWebService(mapping
					.getEb01()));
			mappingWebServiceVO.setEb02(populateHippaSegementWebService(mapping
					.getEb02()));
			mappingWebServiceVO.setEb03(populateHippaSegementWebService(mapping
					.getEb03()));
			mappingWebServiceVO.setEb06(populateHippaSegementWebService(mapping
					.getEb06()));
			mappingWebServiceVO.setEb09(populateHippaSegementWebService(mapping
					.getEb09()));
			mappingWebServiceVO
					.setEndAge(populateHippaSegementWebService(mapping
							.getEndAge()));
			mappingWebServiceVO.setEndAgeCodedValue(mapping.getEndAgeValue());
			if (null != mappingWebServiceVO.getContractMapping()
					&& mapping != null && mapping.getContractMapping() != null) {
				mappingWebServiceVO.getContractMapping().setErrorCodesList(
						populateErrorDetailWebServiceList(mapping
								.getContractMapping().getErrorCodesList()));
			}

			mappingWebServiceVO.setFinalized(mapping.isFinalized());
			mappingWebServiceVO.setFinalizedFlagModified(mapping
					.isFinalizedFlagModified());
			mappingWebServiceVO.setFormattedStringDate(mapping
					.getFormattedStringDate());

			mappingWebServiceVO
					.setHsd01(populateHippaSegementWebService(mapping
							.getHsd01()));
			mappingWebServiceVO
					.setHsd02(populateHippaSegementWebService(mapping
							.getHsd02()));
			mappingWebServiceVO
					.setHsd03(populateHippaSegementWebService(mapping
							.getHsd03()));
			mappingWebServiceVO
					.setHsd04(populateHippaSegementWebService(mapping
							.getHsd04()));
			mappingWebServiceVO
					.setHsd05(populateHippaSegementWebService(mapping
							.getHsd05()));
			mappingWebServiceVO
					.setHsd06(populateHippaSegementWebService(mapping
							.getHsd06()));
			mappingWebServiceVO
					.setHsd07(populateHippaSegementWebService(mapping
							.getHsd07()));
			mappingWebServiceVO
					.setHsd08(populateHippaSegementWebService(mapping
							.getHsd08()));
			mappingWebServiceVO.setIi02(populateHippaSegementWebService(mapping
					.getIi02()));
			// mappingWebServiceVO.setIiiMessage(mapping.getIiiMessage());
			mappingWebServiceVO.setInTempTable(mapping.getInTempTable());
			mappingWebServiceVO.setIsMapgRequired(mapping.getIsMapgRequired());
			mappingWebServiceVO.setLastChangedTmStamp(mapping
					.getLastChangedTmStamp());
			mappingWebServiceVO.setMapped(mapping.isMapped());
			mappingWebServiceVO
					.setMappingComplete(mapping.getMappingComplete());
			mappingWebServiceVO.setMbu(mapping.getMbu());
			mappingWebServiceVO.setMessage(mapping.getMessage());
			mappingWebServiceVO.setMsg_type_required(mapping
					.getMsg_type_required());
			mappingWebServiceVO
					.setNoteTypeCode(populateHippaSegementWebService(mapping
							.getNoteTypeCode()));
			mappingWebServiceVO.setPageFrom(mapping.getPageFrom());
			mappingWebServiceVO.setRule(populateRuleWebService(mapping
					.getRule()));
			mappingWebServiceVO.setSensitiveBenefitIndicator(mapping
					.getSensitiveBenefitIndicator());
			mappingWebServiceVO.setSortField(mapping.getSortField());
			mappingWebServiceVO.setSpsId(populateSPSIdWebService(mapping
					.getSpsId()));
			mappingWebServiceVO
					.setStartAge(populateHippaSegementWebService(mapping
							.getStartAge()));
			mappingWebServiceVO.setStartAgeCodedValue(mapping
					.getStartAgeCodedValue());
			// mappingWebServiceVO.setTierDescription(mapping.getTierDescription());
			mappingWebServiceVO.setUpdateMstr(mapping.isUpdateMstr());
			mappingWebServiceVO.setUser(populateUserWebService(mapping
					.getUser()));
			mappingWebServiceVO
					.setUtilizationMgmntRule(populateHippaSegementWebService(mapping
							.getUtilizationMgmntRule()));
			mappingWebServiceVO.setVariable(populateVariableWebService(mapping
					.getVariable()));
			mappingWebServiceVO
					.setVariableList(populateVariableWebServiceList(mapping
							.getVariableList()));
			mappingWebServiceVO.setVariableMappingStatus(mapping
					.getVariableMappingStatus());
			mappingWebServiceVO.setVariableStatusForAuditTrail(mapping
					.getVariableStatusForAuditTrail());
			mappingWebServiceVO.setVariableSystemId(mapping
					.getVariableSystemId());
		}
		return mappingWebServiceVO;
	}

	private Map<String, MappingWebServiceVO> populateMappingWebServiceMap(
			Map<String, Mapping> contractMappingVOMap) {
		Map<String, MappingWebServiceVO> mapForWebService = new HashMap<String, MappingWebServiceVO>(
				contractMappingVOMap != null ? contractMappingVOMap.size() : 0);
		if (contractMappingVOMap != null && contractMappingVOMap.size() > 0) {
			Iterator<Map.Entry<String, Mapping>> it = contractMappingVOMap
					.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Mapping> pairs = (Map.Entry<String, Mapping>) it
						.next();
				mapForWebService.put(pairs.getKey(),
						populateMappingWebService(pairs.getValue()));

				// it.remove(); // avoids a ConcurrentModificationException
			}
		}
		return mapForWebService;
	}

	private Map<String, MinorHeadingsWebServiceVO> populateMinorHeadingsMap(
			Map<String, MinorHeadingsVO> minorHeadingsMap) {
		Map<String, MinorHeadingsWebServiceVO> mapForWebService = new HashMap<String, MinorHeadingsWebServiceVO>(
				minorHeadingsMap != null ? minorHeadingsMap.size() : 0);
		if (minorHeadingsMap != null && minorHeadingsMap.size() > 0) {
			Iterator<Map.Entry<String, MinorHeadingsVO>> it = minorHeadingsMap
					.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, MinorHeadingsVO> pairs = (Map.Entry<String, MinorHeadingsVO>) it
						.next();
				mapForWebService.put(pairs.getKey(),
						populateMinorHeadingsWebService(pairs.getValue()));
				// it.remove(); // avoids a ConcurrentModificationException
			}
		}
		return mapForWebService;

	}

	@SuppressWarnings("unchecked")
	private ErrorExclusionDetailWebServiceVO populateErrorExclusionDetailWebService(
			ErrorExclusionDetailVO errorExclusionDetailVO) {
		ErrorExclusionDetailWebServiceVO errorExclusionDetailWebServiceVO = new ErrorExclusionDetailWebServiceVO();
		if (errorExclusionDetailVO != null) {
			errorExclusionDetailWebServiceVO
					.setContractProductLevelExclusions(errorExclusionDetailVO
							.getContractProductLevelExclusions());
			errorExclusionDetailWebServiceVO
					.setE001ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE001ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE002ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE002ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE003ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE003ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE004ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE004ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE005ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE005ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE006ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE006ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE007ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE007ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE008ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE008ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE009ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE009ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE010ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE010ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE011ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE011ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE012ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE012ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE013ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE013ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE014ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE014ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE016ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE016ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE017ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE017ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE018ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE018ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE019ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE019ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE020ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE020ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE021ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE021ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE022ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE022ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE023ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE023ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE024ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE024ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE025ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE025ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE026ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE026ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE027ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE027ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE028ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE028ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE029ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE029ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE030ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE030ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE031ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE031ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE032ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE032ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE033ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE033ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE034ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE034ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE035ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE035ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE036ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE036ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE038ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE038ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE039ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE039ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE040ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE040ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE041ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE041ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE042ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE042ExclusionValidationEnableFlag());
			errorExclusionDetailWebServiceVO
					.setE043ExclusionValidationEnableFlag(errorExclusionDetailVO
							.isE043ExclusionValidationEnableFlag());

			errorExclusionDetailWebServiceVO
					.setContractProductLevelExclusions(errorExclusionDetailVO
							.getContractProductLevelExclusions());
			errorExclusionDetailWebServiceVO
					.setHeaderRuleExclusionsList(populateErrorExclusionWebServiceList(errorExclusionDetailVO
							.getHeaderRuleExclusionsList()));
			errorExclusionDetailWebServiceVO
					.setSpsExclusionsList(populateErrorExclusionWebServiceList(errorExclusionDetailVO
							.getSpsExclusionsList()));
			errorExclusionDetailWebServiceVO
					.setVariableExclusionsList(populateErrorExclusionWebServiceList(errorExclusionDetailVO
							.getVariableExclusionsList()));
		}
		return errorExclusionDetailWebServiceVO;
	}

	private List<ErrorExclusionDetailWebServiceVO> populateErrorExclusionDetailWebServiceList(
			List<ErrorExclusionDetailVO> errorExclusionDetailVOList) {
		List<ErrorExclusionDetailWebServiceVO> listForWebService = new ArrayList<ErrorExclusionDetailWebServiceVO>(
				errorExclusionDetailVOList != null ? errorExclusionDetailVOList
						.size() : 0);
		if (errorExclusionDetailVOList != null
				&& errorExclusionDetailVOList.size() > 0) {
			for (ErrorExclusionDetailVO errorExclusionDetailVO : errorExclusionDetailVOList) {
				listForWebService
						.add(populateErrorExclusionDetailWebService(errorExclusionDetailVO));
			}
		}
		return listForWebService;
	}

	@SuppressWarnings("unchecked")
	private ErrorExclusionWebServiceVO populateErrorExclusionWebService(
			ErrorExclusionVO errorExclusionVO) {
		ErrorExclusionWebServiceVO errorExclusionWebServiceVO = new ErrorExclusionWebServiceVO();
		if (errorExclusionVO != null) {
			errorExclusionWebServiceVO.setCreatedDate(errorExclusionVO
					.getCreatedDate());
			errorExclusionWebServiceVO.setCreatedUser(errorExclusionVO
					.getCreatedUser());
			errorExclusionWebServiceVO.setErrorCode(errorExclusionVO
					.getErrorCode());
			errorExclusionWebServiceVO.setExclusionCount(errorExclusionVO
					.getExclusionCount());
			errorExclusionWebServiceVO.setExclusionId(errorExclusionVO
					.getExclusionId());
			errorExclusionWebServiceVO.setLastUpdatedDate(errorExclusionVO
					.getLastUpdatedDate());
			errorExclusionWebServiceVO.setLastUpdatedUser(errorExclusionVO
					.getLastUpdatedUser());
			errorExclusionWebServiceVO.setPrimaryExclusion(errorExclusionVO
					.getPrimaryExclusion());
			errorExclusionWebServiceVO.setPrimaryValues(errorExclusionVO
					.getPrimaryValues());
			errorExclusionWebServiceVO.setSecondaryExclusion(errorExclusionVO
					.getSecondaryExclusion());
			errorExclusionWebServiceVO.setSecondaryValues(errorExclusionVO
					.getSecondaryValues());
		}
		return errorExclusionWebServiceVO;
	}

	private List<ErrorExclusionWebServiceVO> populateErrorExclusionWebServiceList(
			List<ErrorExclusionVO> errorExclusionVOList) {
		List<ErrorExclusionWebServiceVO> listForWebService = new ArrayList<ErrorExclusionWebServiceVO>(
				errorExclusionVOList != null ? errorExclusionVOList.size() : 0);
		if (errorExclusionVOList != null && errorExclusionVOList.size() > 0) {
			for (ErrorExclusionVO errorExclusionVO : errorExclusionVOList) {
				listForWebService
						.add(populateErrorExclusionWebService(errorExclusionVO));
			}
		}
		return listForWebService;
	}

	// Kritika Added

	private Map<String, ContractMappingWebServiceVO> populateContractMappingWebServiceMap(
			Map<String, ContractMappingVO> contractMappingVOMap) {
		Map<String, ContractMappingWebServiceVO> mapForWebService = new HashMap<String, ContractMappingWebServiceVO>(
				contractMappingVOMap != null ? contractMappingVOMap.size() : 0);
		if (contractMappingVOMap != null && contractMappingVOMap.size() > 0) {
			Iterator<Map.Entry<String, ContractMappingVO>> it = contractMappingVOMap
					.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, ContractMappingVO> pairs = (Map.Entry<String, ContractMappingVO>) it
						.next();
				mapForWebService.put(pairs.getKey(),
						populateContractMappingWebService(pairs.getValue()));
				// it.remove(); // avoids a ConcurrentModificationException
			}
		}
		return mapForWebService;
	}

}
