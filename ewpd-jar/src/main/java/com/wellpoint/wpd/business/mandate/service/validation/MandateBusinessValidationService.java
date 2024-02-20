/*
 * MandateBusinessValidationService.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.mandate.service.validation;

import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.business.mandate.builder.MandateBusinessObjectBuilder;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.mandate.bo.MandateBO;
import com.wellpoint.wpd.common.mandate.request.CreateMandateRequest;
import com.wellpoint.wpd.common.mandate.request.DeleteMandateRequest;
import com.wellpoint.wpd.common.mandate.response.CreateMandateResponse;
import com.wellpoint.wpd.common.mandate.response.DeleteMandateResponse;
import com.wellpoint.wpd.common.mandate.vo.MandateVO;

import java.util.ArrayList;
import java.util.List;


/**
 * Validation class for Admin Option.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MandateBusinessValidationService extends WPDService {

	/**
	 * 
	 * Overrided execute method of WPDService. Do the Business processing
	 * corresponding to request and returns response.
	 * 
	 * @param request
	 *            WPDRequest.
	 * @return WPDResponse WPDRespnose.
	 * @throws ServiceException
	 */
	public WPDResponse execute(WPDRequest request) throws ServiceException {
		return null;
	}
	
	/**
	 * Validation method for DeleteMandateRequest.
	 * 
	 * @param deleteMandateRequest
	 * @return WPDResponse mandateDeleteResponse.
	 * @throws ServiceException
	 */
	public WPDResponse execute(DeleteMandateRequest deleteMandateRequest)
			throws ServiceException {

	    DeleteMandateResponse mandateDeleteResponse = new DeleteMandateResponse();

		MandateBO mandateBO = this.getMandateBusinessObject(deleteMandateRequest.getMandateVO());
		List messageList = new ArrayList();
		try {
			if (isMandateInUse(mandateBO, deleteMandateRequest
					.getUser())) {
				messageList.add(new ErrorMessage("mandate.cannot.delete.info"));
				mandateDeleteResponse.setErrorFlag(true);
			}
		} catch (WPDException e) {
        	Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(deleteMandateRequest);
			String logMessage = "Failed while processing CreateMandateRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		mandateDeleteResponse.setMessages(messageList);
		return mandateDeleteResponse;
	}
	
	/**
	 * Validation method for check Mandate is in use.
	 * 
	 * @param mandateBO
	 * @param user
	 * @return boolean
	 * @throws WPDException
	 */
	public static boolean isMandateInUse(MandateBO mandateBO, User user)
			throws WPDException {
		MandateBusinessObjectBuilder mandateObjectBuilder = new MandateBusinessObjectBuilder();
		return mandateObjectBuilder.isMandateInUse(mandateBO, user);
	}


	/**
	 * Validation method for CreateMandateRequest.
	 * 
	 * @param createMandateRequest
	 * @return WPDResponse CreateMandateResponse.
	 * @throws ServiceException
	 */
	public WPDResponse execute(CreateMandateRequest createMandateRequest)
			throws ServiceException {

		CreateMandateResponse createMandateResponse = new CreateMandateResponse();

		MandateBO mandateBO = this
				.getMandateBusinessObject(createMandateRequest.getMandateVO());
		List messageList = new ArrayList();
		try {
			if (isMandateDuplicate(mandateBO, createMandateRequest
					.getUser())) {
				messageList.add(new ErrorMessage(
						"mandate.already.exist.info"));
				createMandateResponse.setErrorFlag(true);
			}
		} catch (WPDException e) {
        	Logger.logError(e);
			List logParameters = new ArrayList();
			logParameters.add(createMandateRequest);
			String logMessage = "Failed while processing CreateMandateRequest";
			throw new ServiceException(logMessage, logParameters, e);
		}
		createMandateResponse.setMessages(messageList);
		return createMandateResponse;
	}

	
	/**
	 *  Validation method for duplicate Mandate.
	 * 
	 * @param mandateBO
	 * @param user
	 * @return boolean
	 * @throws WPDException
	 */
	public static boolean isMandateDuplicate(MandateBO mandateBO, User user)
			throws WPDException {
		MandateBusinessObjectBuilder mandateObjectBuilder = new MandateBusinessObjectBuilder();
		mandateBO = mandateObjectBuilder
				.retrieveMandateName(mandateBO, user);
		if (null == mandateBO) {
			return false;
		}
		return true;
	}

	/**
	 * To set the values from VO to BO
	 * 
	 * @param mandateVO
	 * @return mandateBO
	 */
	private MandateBO getMandateBusinessObject(MandateVO mandateVO) {
		MandateBO mandateBO = new MandateBO();
		mandateBO.setMandateId(mandateVO.getMandateId());
		mandateBO.setMandateName(mandateVO.getMandateName());
		mandateBO.setVersion(mandateVO.getVersion());
		return mandateBO;
	}
}