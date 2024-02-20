package com.wellpoint.wpd.business.contract.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.SimulationWebServiceException_Exception;
import com.wellpoint.ets.ebx.simulation.webservices.client.SimulationWebServiceImplPortProxy;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.framework.bo.BusinessObjectManagerFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectManager;
import com.wellpoint.wpd.business.framework.service.BusinessServiceController;
import com.wellpoint.wpd.business.framework.service.ValidationServiceController;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminmethod.request.AdminMethodValidationStatusRequest;
import com.wellpoint.wpd.common.adminmethod.request.GeneralBenefitAdminMethodValidationRequest;
import com.wellpoint.wpd.common.adminmethod.response.AdminMethodValidationStatusResponse;
import com.wellpoint.wpd.common.adminmethod.response.GeneralBenefitAdminMethodValidationResponse;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.contract.request.SaveContractBasicInfoRequest;
import com.wellpoint.wpd.common.contract.response.ContractWebServiceResponse;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.framework.exception.SecurityException;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;
import com.wellpoint.wpd.common.framework.security.domain.User;

public class ContractBusinessServiceHelper {
	
	
	public  ContractWebServiceResponse invokeEBXWSResponse(ContractWebServiceVO contractWebServiceVO){
		ContractWebServiceResponse contractWebServiceResponse = new ContractWebServiceResponse();
		List<ContractWebServiceVO> contractWSErrorList = null;
		long time1 = System.currentTimeMillis();
		try {
			contractWSErrorList = new SimulationWebServiceImplPortProxy().getContractInfo(contractWebServiceVO,"Test", false);
			
		} catch (SimulationWebServiceException_Exception simExcep) {
			String msg = simExcep.getMessage();
			if(msg.equals("NO MATCH FOUND")){
				contractWebServiceResponse.setContractWSErrorList(null);
			}else{
			Logger.logError(simExcep);
			contractWebServiceResponse.setWsProcessError(BusinessConstants.DO_EBX_WEBSERVICE_PROCESS_ERROR);
			}
			//throw new ServiceException(simExcep.getFaultInfo().getDisplayMessage(), null, simExcep);
		}catch(Exception e){	
			if(contractWebServiceResponse.getWsProcessError() == null){
				contractWebServiceResponse.setWsProcessError(BusinessConstants.DO_EBX_WEBSERVICE_PROCESS_ERROR);
			}
		}
		long time2 = System.currentTimeMillis();
		Logger.logInfo("********TOTAL TIME TAKEN FOR SIMULATION WEB SERVICE EXECUTION*********--->"+ (time2 - time1));
		if (contractWSErrorList != null) {
			Logger.logInfo("********FROM CLIENT -- Printing contractWSErrorList contents ********:"+ contractWSErrorList.size());
			for (ContractWebServiceVO vo : contractWSErrorList) {
				Logger.logInfo("------FROM CLIENT----" + vo.getContractId());
			}
			Logger.logInfo("***FROM CLIENT*****contractList.size()********:"+ contractWSErrorList.size());
		} else {
			Logger.logInfo("****FROM CLIENT****contractList is null********");
		}
		if(null != contractWSErrorList && !contractWSErrorList.isEmpty()){
			contractWebServiceResponse.setContractWSErrorList(contractWSErrorList);
		}	
		return contractWebServiceResponse;
	}
	
	
	/**
	 * The function retrieve the contract and update it with necessary changes.
	 * It save the updated contract in to DB and retrieve it again for further process.
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public Contract updateContract(SaveContractBasicInfoRequest request)
			throws ServiceException {
		BusinessObjectManager bom = getBusinessObjectManager();
		ContractVO contractVO = request.getContractVO();
		Contract contract = null;
		User user = request.getUser();
		contract = new Contract();
		setBasicInfoToContract(contractVO, contract);
		int action = request.getAction();
		try {
			Map params = new HashMap();
			Contract retrievedContract = new Contract();
			params.put(BusinessConstants.ACTION,
					BusinessConstants.RETREIVE_SPECIFIC);
			params.put(BusinessConstants.DATESEGMENT_ID, new Integer(contractVO
					.getDateSegmentSysId()));
			if(action == SaveContractBasicInfoRequest.UPDATE_CHECKIN_CONTRACT){
			retrievedContract = (Contract) bom.retrieve(contract, request
					.getUser(), params);
			DateSegment retrievedDateSegment = (DateSegment) retrievedContract
					.getDateSegmentList().get(0);
			retrievedDateSegment.setContractStatusBo(contractVO.getContractStatusBo());
			if(null != retrievedDateSegment.getContractStatusBo()){
				retrievedDateSegment.getContractStatusBo().setContractId(contract.getContractId());
				if(null == retrievedDateSegment.getContractStatusBo().getCreatedUserId()){
					retrievedDateSegment.getContractStatusBo().setCreatedUserId(user.getUserId());
				}
				retrievedDateSegment.getContractStatusBo().setLastChangedUserId(user.getUserId());
			}
			retrievedDateSegment.setGroupSize(contractVO.getGroupSize());
			contract.getDateSegmentList().set(0, retrievedDateSegment);
			bom.persist(contract, user, false);
			params.put(BusinessConstants.ACTION,
					BusinessConstants.RETREIVE_SPECIFIC);
			params.put(BusinessConstants.DATESEGMENT_ID, new Integer(contractVO
					.getDateSegmentSysId()));
			}
			contract = (Contract) bom.retrieve(contract, request.getUser(),
					params);
			
			contract.setCheckInDateSegmentList(new ContractBusinessObjectBuilder().retrieveCheckInDateSegments(contract.getContractSysId()));
			
			return contract;
			
		}catch (SecurityException exception) {
			ErrorMessage em = new ErrorMessage("security.invalid");
			em.setParameters(new String[] { (String) exception
					.getLogParameters().get(1) });
			// response.addMessage(em);
			List logParameters = new ArrayList(2);
			logParameters.add(request);
			ServiceException se = new ServiceException(
					"Security Exception while processing service",
					logParameters, exception);
			Logger.logException(se);

		} catch (SevereException ex) {
			Logger.logError(ex);
			throw new ServiceException("Exception occured while BOM call",
					null, ex);
		} catch (WPDException e) {
			Logger.logError(e);
			throw new ServiceException("Exception occured while Adapter call",
					null, e);
		}
		
		return null;

	}
	
	/**
	 * this method call the Admin Method service to validate whether all the admin line 
	 * in the general benefit is coded or not.
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public GeneralBenefitAdminMethodValidationResponse validateGeneralBenefitAdminMethod(
			SaveContractBasicInfoRequest request) throws ServiceException {
		GeneralBenefitAdminMethodValidationRequest adminMethodRequest = new GeneralBenefitAdminMethodValidationRequest();
		adminMethodRequest.setEntityId(request.getContractKeyObject()
				.getDateSegmentId());
		adminMethodRequest.setEntityType(BusinessConstants.ENTITY_CONTRACT);
		adminMethodRequest.setContractId(request.getContractKeyObject()
				.getContractSysId());
		adminMethodRequest.setUser(request.getUser());
		GeneralBenefitAdminMethodValidationResponse adminMethodResponse = (GeneralBenefitAdminMethodValidationResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.ADMIN_METHOD_RESPONSE);
		adminMethodResponse = (GeneralBenefitAdminMethodValidationResponse) (new ValidationServiceController()
				.execute(adminMethodRequest));
		return adminMethodResponse;
	}

	/**
	 * This method call the admin Method to check the status of the admin method.
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public AdminMethodValidationStatusResponse validateAdminMethodStatus(
			SaveContractBasicInfoRequest request) throws ServiceException {
		AdminMethodValidationStatusRequest statusRequest = new AdminMethodValidationStatusRequest();
		statusRequest.setEntityId(request.getContractKeyObject()
				.getDateSegmentId());
		statusRequest.setEntityType(BusinessConstants.ENTITY_TYPE_CONTRACT);
		statusRequest.setContractId(request.getContractKeyObject()
				.getContractSysId());

		AdminMethodValidationStatusResponse statusResponse = (AdminMethodValidationStatusResponse) new BusinessServiceController()
				.execute(statusRequest);
		return statusResponse;
	}

	/**
	 * Get the businessobject Builder.
	 * @return
	 */
	private BusinessObjectManager getBusinessObjectManager() {
		BusinessObjectManagerFactory bomf = (BusinessObjectManagerFactory) ObjectFactory
				.getFactory(ObjectFactory.BOM);
		BusinessObjectManager bom = bomf.getBusinessObjectManager();
		return bom;
	}

	/**
	 * this function set the basic information from ContractVO to Contract object.
	 * @param contractVO
	 * @param contract
	 */
	private void setBasicInfoToContract(ContractVO contractVO, Contract contract) {
		contract.setContractId(contractVO.getContractId());
		contract.setContractSysId(contractVO.getContractSysId());
		contract.setParentSysId(contractVO.getParentSysId());
		contract.setDateSegmentList(contractVO.getDateSegmentList());
		contract.setContractType(contractVO.getContractType());
		contract.setBusinessDomains(contractVO.getDomainList());
		if (BusinessConstants.MSG_CUSTOM_CONTRACT_TYPE.equals(contract
				.getContractType())) {
			contract.setBaseDateSegmentSysId(contractVO
					.getBaseDateSegmentSysId());
			contract.setBaseContractId(contractVO.getBaseContractId());
			contract.setBaseContractDate(contractVO.getBaseContractDate());
		} else if (BusinessConstants.STANDARD_TYPE.equals(contract
				.getContractType())
				&& BusinessConstants.VALUE_ZERO != contractVO
						.getBaseDateSegmentSysId()) {
			contract.setBaseDateSegmentSysId(contractVO
					.getBaseDateSegmentSysId());
			contract.setBaseContractId(contractVO.getBaseContractId());
			contract.setBaseContractDate(contractVO.getBaseContractDate());
			contract.setBaseDateSegmentSysId(contractVO
					.getBaseDateSegmentSysId());
		}
		contract.setVersion(contractVO.getVersion());
		contract.setStatus(contractVO.getStatus());
	}

}
