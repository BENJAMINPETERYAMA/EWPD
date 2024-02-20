/*
 * ContractReportBackingBean.java
 * 
 * © 2008 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.report;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.messages.Message;
import com.wellpoint.wpd.common.report.request.ContractReportRequest;
import com.wellpoint.wpd.common.report.response.ContractReportResponse;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

public class ContractReportBackingBean extends WPDBackingBean {
	private String contractId;
	private String startDate;
	private String components;
	private String benefits;
	
	private boolean headerRule = true;
	private boolean benefitLine = true;
	private boolean question = true;
	private boolean adminMethod = true;
	private boolean notes = true;
	private boolean singleSheet = false;
	
	private String inputValidFlag = "";
	
	List validationMessages = new ArrayList();
	
	private static final int ERROR_MSG = 1;
	private static final int INFO_MSG = 2;
	private static int CONTRACT_COUNT_MAX = -1;
	private static int COMPONENT_COUNT_MAX = -1;
	private static int BENEFIT_COUNT_MAX = -1;
	
	
	private boolean informationMessage = false;
	private boolean errorMessage = false;
	
	private String systemBenefits;
	private String systemComponents;
	
	static {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("boundaries",Locale.getDefault());
		
		String contractLimit = resourceBundle.getString("contractReport.contract.max");
		CONTRACT_COUNT_MAX = Integer.parseInt(contractLimit);
		
		String componentLimit = resourceBundle.getString("contractReport.component.max");
		COMPONENT_COUNT_MAX = Integer.parseInt(componentLimit);

		String benefitLimit = resourceBundle.getString("contractReport.benefit.max");
		BENEFIT_COUNT_MAX = Integer.parseInt(benefitLimit);
	}
	
	public String initializePage(){
		
		ContractReportRequest request = (ContractReportRequest)this.getServiceRequest(ServiceManager.CONTRACT_REPORT_REQUEST);
		
		request.setAction(ContractReportRequest.LOAD_BENEFTS_AND_COMPONENTS);
		ContractReportResponse response = (ContractReportResponse)this.executeService(request);
		
		if(response != null) {
			
	        this.systemComponents = getAppendedString(response.getComponentList(), "~");

	        this.systemBenefits = getAppendedString(response.getBenefitList(), "~");
	        
			return WebConstants.CONTRACT_REPORT_PAGE;
		}
		
		return "";
	}
	
	private String getAppendedString(List list, String sperator) {
		StringBuffer buffer = new StringBuffer();

		if(list != null) {
	        for(int i=0; i<list.size() ; i++) {
	        	buffer.append((String)list.get(i)).append("~");
	        }
		}
		
        if(buffer.length() > 0)
        	buffer.setLength(buffer.length() - 1);
        return buffer.toString();
	}
	
	public String validateInputs() {
		
		this.inputValidFlag = "";
		
		List inputContractCodes = WPDStringUtil.getSplittedValues(this.contractId, ",") ;
		List inputComponents = WPDStringUtil.getSplittedValues(this.components, "\n");
		List inputBenefits = WPDStringUtil.getSplittedValues(this.benefits, "\n");
		
		List validContractCodes = null;
		List validComponents = null;
		List validBenefits = null;
		
		if(inputContractCodes.size() > 0 || inputComponents.size() > 0 || inputBenefits.size() > 0) {
			ContractReportRequest request = (ContractReportRequest)this.getServiceRequest(ServiceManager.CONTRACT_REPORT_REQUEST);
			
			request.setAction(ContractReportRequest.VALIDATE_INPUTS);
			request.setContractCodes(inputContractCodes);
			request.setBenefitComponents(inputComponents);
			request.setBenefits(inputBenefits);
			ContractReportResponse response = (ContractReportResponse)this.executeService(request);
			
			if(response == null) {
				return "";
			}
			
			validContractCodes = response.getContractList();
			validComponents = response.getComponentList();
			validBenefits = response.getBenefitList();
		}
		
		if(inputContractCodes.size() == 0) {
			addMessage("contractreport.contract.mandotry", ERROR_MSG, null);
		}
		
		if(inputContractCodes.size() > 0) {
	        String invalidContracts = getInvalidInputs(inputContractCodes, validContractCodes);
	        
	        if(!"".equals(invalidContracts)) {
	        	addMessage("contractreport.contract.invalid", ERROR_MSG, invalidContracts);
	        }
		}
		
		if(inputContractCodes.size() > CONTRACT_COUNT_MAX) {
			addMessage("contractreport.contract.excceds", ERROR_MSG, String.valueOf(CONTRACT_COUNT_MAX));
		}
		
		if(!WPDStringUtil.isValidDate(this.startDate)) {
			addMessage("contractreport.eff.invalid", ERROR_MSG, null);
		}
		
		if(!this.headerRule && !this.benefitLine && !this.question && !this.adminMethod) {
			addMessage("contractreport.output.mandatory", ERROR_MSG, null);
		}
		
		if(inputComponents.size() > 0) {
	        
	        String invalidComponents = getInvalidInputs(inputComponents, validComponents);
	        
	        if(!"".equals(invalidComponents)) {
	        	addMessage("contractreport.component.invalid", ERROR_MSG, invalidComponents);
	        }
		}
		
		if(inputBenefits.size() > 0) {
	        
	        String invalidBenefits = getInvalidInputs(inputBenefits, validBenefits);
	        
	        if(!"".equals(invalidBenefits)) {
	        	addMessage("contractreport.benefit.invalid", ERROR_MSG, invalidBenefits);
	        }
		}
		
		if(inputContractCodes.size() > 1) {
			
			if(inputComponents.size() ==0 && inputBenefits.size() == 0) {
				addMessage("contractreport.mutliplecont.bnft.mandatory", ERROR_MSG, null);
			}		

			if(inputBenefits.size() == 0 && inputComponents.size() > COMPONENT_COUNT_MAX) {
				addMessage("contractreport.component.limit", ERROR_MSG, String.valueOf(COMPONENT_COUNT_MAX));
			}
			
			if( (inputComponents.size() == 0 || inputComponents.size() > COMPONENT_COUNT_MAX) && inputBenefits.size() > BENEFIT_COUNT_MAX ) {
				addMessage("contractreport.benefit.limit", ERROR_MSG, String.valueOf(BENEFIT_COUNT_MAX));
			}
		}

		if("Y".equals(getSession().getAttribute(WebConstants.CONTRACT_REPORT_RUNNING))) {
			addMessage("contractreport.earlierrequest", ERROR_MSG, null);
		}

		if(inputComponents.size() > 0 && inputBenefits.size() > 0) {
			addMessage("contractreport.componentbenefitinvalid", ERROR_MSG, null);
		}
		
		addAllMessagesToRequest(validationMessages);
		
		if(informationMessage) {
			this.inputValidFlag = "I";
		}
		if(errorMessage) {
			this.inputValidFlag = "E";
		}
		if(!informationMessage && !errorMessage) {
			this.inputValidFlag = "Y";
		}
		
		return "";
	}

	private String getInvalidInputs(List inputValues, List validValues) {
		
		List tempInputValues = new ArrayList();
		tempInputValues.addAll(inputValues);
		
        for (Iterator iterator = tempInputValues.iterator(); iterator.hasNext();) {
			String inputValue = (String) iterator.next();
			if(validValues.contains(inputValue)) {
				iterator.remove();
			}
		}
        
        StringBuffer invalidInputs = new StringBuffer("");
        for( int i = 0; i < tempInputValues.size(); i++) {
        	if ( i > 0) {
        		invalidInputs.append(", ");
        	}
        	String val = (String)tempInputValues.get(i);
        	if(val.indexOf(",") != -1) {
        		val = "\"" + val + "\"";
        	}
        	invalidInputs.append(val);
        }
        return invalidInputs.toString();
	}
	
	private void addMessage(String messageId, int message_ind, String messageParameter) {
		Message message = null;
		if(message_ind == ERROR_MSG) {
			message = new ErrorMessage(messageId);
			errorMessage = true;
		} else if(message_ind == INFO_MSG) {
			message = new InformationalMessage(messageId);
			informationMessage = true;
		} else {
			return;
		}
		if(messageParameter != null)
			message.setParameters(new String [] {messageParameter});
		validationMessages.add(message);
	}
	
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getComponents() {
		return components;
	}
	public void setComponents(String components) {
		this.components = components;
	}
	public String getBenefits() {
		return benefits;
	}
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	public boolean isHeaderRule() {
		return headerRule;
	}
	public void setHeaderRule(boolean headerRule) {
		this.headerRule = headerRule;
	}
	public boolean isBenefitLine() {
		return benefitLine;
	}
	public void setBenefitLine(boolean benefitLine) {
		this.benefitLine = benefitLine;
	}
	public boolean isQuestion() {
		return question;
	}
	public void setQuestion(boolean question) {
		this.question = question;
	}
	public boolean isAdminMethod() {
		return adminMethod;
	}
	public void setAdminMethod(boolean adminMethod) {
		this.adminMethod = adminMethod;
	}
	public boolean isNotes() {
		return notes;
	}
	public void setNotes(boolean notes) {
		this.notes = notes;
	}
	public boolean isSingleSheet() {
		return singleSheet;
	}
	
	public void setSingleSheet(boolean singleSheet) {
		this.singleSheet = singleSheet;
	}
	
	public String getInputValidFlag() {
		return inputValidFlag;
	}
	public void setInputValidFlag(String inputValidFlag) {
		this.inputValidFlag = inputValidFlag;
	}

	public String getSystemComponents() {
		return systemComponents;
	}

	public void setSystemComponents(String systemComponents) {
		this.systemComponents = systemComponents;
	}

	public String getSystemBenefits() {
		return systemBenefits;
	}

	public void setSystemBenefits(String systemBenefits) {
		this.systemBenefits = systemBenefits;
	}

	/**
	 * @return Returns the maxContractCount.
	 */
	public String getMaxContractCount() {
		return String.valueOf(CONTRACT_COUNT_MAX);
	}

	/**
	 * @param maxContractCount The maxContractCount to set.
	 */
	public void setMaxContractCount(String maxContractCount) {
	}

	/**
	 * @return Returns the maxComponentCount.
	 */
	public String getMaxComponentCount() {
		return String.valueOf(COMPONENT_COUNT_MAX);
	}

	/**
	 * @param maxComponentCount The maxComponentCount to set.
	 */
	public void setMaxComponentCount(String maxComponentCount) {
	}

	/**
	 * @return Returns the maxBenefitCount.
	 */
	public String getMaxBenefitCount() {
		return String.valueOf(BENEFIT_COUNT_MAX);
	}

	/**
	 * @param maxBenefitCount The maxBenefitCount to set.
	 */
	public void setMaxBenefitCount(String maxBenefitCount) {
	}
}
