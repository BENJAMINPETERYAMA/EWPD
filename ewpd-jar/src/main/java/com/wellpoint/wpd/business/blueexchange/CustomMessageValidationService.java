/*
 * CustomMessageValidationService.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.blueexchange;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.business.blueexchange.builder.CustomMessageBusinessObjectBuilder;
import com.wellpoint.wpd.business.framework.service.WPDBusinessValidationService;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.blueexchange.bo.CustomMessageTextBO;
import com.wellpoint.wpd.common.blueexchange.request.CustomMessageTextCreateRequest;
import com.wellpoint.wpd.common.blueexchange.response.CustomMessageTextCreateResponse;
import com.wellpoint.wpd.common.blueexchange.vo.CustomMessageTextVO;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.framework.response.WPDResponseFactory;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CustomMessageValidationService extends
		WPDBusinessValidationService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 */
	public WPDResponse execute(WPDRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		return super.execute(request);
	}

	/**
	 * Method for validating duplicate Custom Message Text @ input
	 * CustomMessageTextCreateRequest contains CustomMessageTextVO
	 *  @ returns CustomMessageTextCreateResponse
	 *  
	 */
	public WPDResponse execute(
			CustomMessageTextCreateRequest customMessageTextCreateRequest)
			throws ServiceException {
		CustomMessageTextBO customMessageTextBO = getBOValuesFromVO(customMessageTextCreateRequest
				.getCustomMessageTextVO());
		CustomMessageTextCreateResponse customMessageTextCreateResponse = (CustomMessageTextCreateResponse) WPDResponseFactory
				.getResponse(WPDResponseFactory.CUSTOM_MESSAGE_CREATE_RESPONSE);
		if (!this.validateDuplicate(customMessageTextBO)) {
			customMessageTextCreateResponse.setValidationSuccessFlag(false);
			List message = new ArrayList();
			message.add(new ErrorMessage(
					BusinessConstants.CUSTOM_MESSAGE_DUPLICATE));
			customMessageTextCreateResponse.setMessages(message);
		}
		return customMessageTextCreateResponse;
	}

	/**
	 * This method gets the VO values and build the business object and return
	 * it to the method called
	 * 
	 * @param CustomMessageTextVO
	 * @return CustomMessageTextBO
	 */
	private CustomMessageTextBO getBOValuesFromVO(
			CustomMessageTextVO messageTextVO) {
		CustomMessageTextBO messageTextBO = new CustomMessageTextBO();
		messageTextBO.setHeaderRuleId(messageTextVO.getHeaderRuleId());
		messageTextBO.setSpsParameterId(messageTextVO.getSpsParameterId());
		messageTextBO.setMessagetext(messageTextVO.getMessagetext());
		messageTextBO.setMessageReqIndicator(messageTextVO
				.getMessageReqIndicator());
		return messageTextBO;
	}

	/**
	 * Validates custom method duplication
	 * 
	 * @param CustomMessageTextBO
	 * @return boolean
	 */
	private boolean validateDuplicate(CustomMessageTextBO customMessageTextBO) {
		CustomMessageBusinessObjectBuilder businessObjectBuilder = new CustomMessageBusinessObjectBuilder();
		boolean valid = true;
		try {

			if (!businessObjectBuilder.locateDuplicate(customMessageTextBO))
				valid = false;
		} catch (SevereException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valid;
	}
}