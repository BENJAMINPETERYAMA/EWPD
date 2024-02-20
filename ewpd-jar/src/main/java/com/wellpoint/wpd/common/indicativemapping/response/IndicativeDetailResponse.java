package com.wellpoint.wpd.common.indicativemapping.response;

import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.indicativemapping.vo.IndicativeDetailVO;

public class IndicativeDetailResponse extends WPDResponse{
	
	private List<IndicativeDetailVO> confirmedIndicativeDetailVOList;
	
	Map<String, List<IndicativeDetailVO>> processedDetailsForConfirmation;
	
	private List<ErrorMessage> errorMessageList;

	public List<ErrorMessage> getErrorMessageList() {
		return errorMessageList;
	}

	public void setErrorMessageList(List<ErrorMessage> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}	

	public Map<String, List<IndicativeDetailVO>> getProcessedDetailsForConfirmation() {
		return processedDetailsForConfirmation;
	}

	public void setProcessedDetailsForConfirmation(
			Map<String, List<IndicativeDetailVO>> processedDetailsForConfirmation) {
		this.processedDetailsForConfirmation = processedDetailsForConfirmation;
	}

	public List<IndicativeDetailVO> getConfirmedIndicativeDetailVOList() {
		return confirmedIndicativeDetailVOList;
	}

	public void setConfirmedIndicativeDetailVOList(
			List<IndicativeDetailVO> confirmedIndicativeDetailVOList) {
		this.confirmedIndicativeDetailVOList = confirmedIndicativeDetailVOList;
	}
}
