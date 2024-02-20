/*
 * SPSMappingBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.blueexchange;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import com.wellpoint.wpd.common.blueexchange.request.SPSMappingCreateRequest;
import com.wellpoint.wpd.common.blueexchange.request.SPSMappingRetrieveRequest;
import com.wellpoint.wpd.common.blueexchange.request.SPSMappingUpdateRequest;
import com.wellpoint.wpd.common.blueexchange.response.SPSMappingCreateResponse;
import com.wellpoint.wpd.common.blueexchange.response.SPSMappingUpdateResponse;
import com.wellpoint.wpd.common.blueexchange.response.SPSMappingViewResponse;
import com.wellpoint.wpd.common.blueexchange.vo.SPSMappingVO;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SPSMappingBackingBean extends WPDBackingBean {

	private String spsParameter;

	private String eb01Value;

	private String eb02Value;

	private String eb06Value;

	private String eb09Value;

	private String hsd1Value;

	private String hsd2Value;

	private String hsd3Value;

	private String hsd4Value;

	private String hsd5Value;

	private String hsd6Value;
	
	private String accummulatorSPSID;	
	
	private String createdUser;
	private String lastChangedUser;
	private Date createdDate;
	private Date lastChangedDate;

	private List validationMessage;

	private boolean spsParamFlag;
	private int createValidationFlag = 0;
	private int mandateCheckValuesFlag = 1;
	private int atleastOneValueFlag = 2;
	private int hsd2ValueFlag = 3;
	private int hsd2ValidValueFlag = 4;
	private int hsd4ValidValueFlag = 5;
	private int hsd6ValidValueFlag = 6;
	private int hsd2ValidFlag = 7;
	private int hsd4ValidFlag = 8;
	private int hsd6ValidFlag = 9;
	
	
	protected static String SPS_MAPPING_SESSION_KEY = WebConstants.SPS_MAPPING;
	

	/**
	 * 
	 *  
	 */
	public SPSMappingBackingBean() {
	    intialize();
	}

	/**
	 * 
	 * @return
	 */
	public String createSPSMapping() {		
	    int validationFlag=validateSPSMapping();
	    setBreadCrumbText("Administration >> Blue Exchange >> SPS Mapping >> Create");
		// Validating the Business Object
		if (validationFlag==createValidationFlag){

			// Creating the Request Object
			SPSMappingCreateRequest mappingCreateRequest = (SPSMappingCreateRequest) this
					.getServiceRequest(ServiceManager.SPS_MAPPING_CREATE_REQUEST);
			mappingCreateRequest.setSpsMappingVO(getSPSMappingVO());
			//Executing the create service
			SPSMappingCreateResponse mappingCreateResponse = (SPSMappingCreateResponse) this
					.executeService(mappingCreateRequest);
			//Adding the validation Message
			if (mappingCreateResponse != null
					&& mappingCreateResponse.getMessages() != null) {
				validationMessage = mappingCreateResponse.getMessages();
				if(mappingCreateResponse.getMappingBO()!=null){
				  this.createdUser=mappingCreateResponse.getMappingBO().getCreatedUser();
				  this.createdDate=mappingCreateResponse.getMappingBO().getCreatedTimestamp();
				  this.lastChangedUser=mappingCreateResponse.getMappingBO().getLastUpdatedUser();
				  this.lastChangedDate=mappingCreateResponse.getMappingBO().getLastUpdatedTimestamp();
				  SPSMappingSessionObject mappingSessionObject=new SPSMappingSessionObject();
				  mappingSessionObject.setSpsParameterId((this.spsParameter!=null&&!"".equalsIgnoreCase(this.spsParameter)?this.spsParameter.split("~")[1]:""));
				  getSession().setAttribute("spsMappingSession",mappingSessionObject);
				  this.setBreadCrumbText("Administration>> Blue Exchange >> SPS Mapping ("
							+ (this.spsParameter!=null&&!"".equalsIgnoreCase(this.spsParameter)?this.spsParameter.split("~")[1]:"")+") >> Edit");
					
				}
					
			}
		} else {
		    if(validationFlag==mandateCheckValuesFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage(
						"spsMapping.create.failure.spsparam.mandatory.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);
		    }else if(validationFlag==atleastOneValueFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage(
						"spsMapping.create.failure.oneAttr.mandatory.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    
			}else if(validationFlag==hsd2ValueFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage("spsMapping.create.failure.hsd2.mandatory.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    				
			}else if(validationFlag==hsd2ValidValueFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage("spsMapping.create.failure.hsd2.value.range.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    				
			}else if(validationFlag==hsd4ValidValueFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage("spsMapping.create.failure.hsd4.value.range.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    				
			}else if(validationFlag==hsd6ValidValueFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage("spsMapping.create.failure.hsd6.value.range.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    				
			}else if(validationFlag==hsd2ValidFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage("spsMapping.create.failure.hsd2.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    				
			}else if(validationFlag==hsd4ValidFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage("spsMapping.create.failure.hsd4.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    				
			}else if(validationFlag==hsd6ValidFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage("spsMapping.create.failure.hsd6.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    				
			}
			return "";
		}
		if (validationMessage != null && !validationMessage.isEmpty()) {
		    //intialize();
			return WebConstants.UPDATE_SPS_MAPPING;
		}
		return "";
	}

	/**
	 * 
	 * @return
	 */
	public String updateSPSMapping() {
		  this.setBreadCrumbText("Administration>> Blue Exchange >> SPS Mapping ("
				+ (this.spsParameter!=null&&!"".equalsIgnoreCase(this.spsParameter)?this.spsParameter.split("~")[1]:"")+ ") >> Edit");
	    int validationFlag=validateSPSMapping();
		// Validating the Business Object
		if (validationFlag==0) {
			// Creating the Request Object
			SPSMappingUpdateRequest mappingUpdateRequest = (SPSMappingUpdateRequest) this
					.getServiceRequest(ServiceManager.SPS_MAPPING_UPDATE_REQUEST);
			mappingUpdateRequest.setSpsMappingVO(getSPSMappingVO());
			//Executing the update service
			SPSMappingUpdateResponse mappingUpdateResponse = (SPSMappingUpdateResponse) this
					.executeService(mappingUpdateRequest);
			//Adding the validation Message
			if (mappingUpdateResponse != null
					&& mappingUpdateResponse.getMessages() != null) {
				this.createdDate = mappingUpdateResponse.getMappingVO().getCreatedTimestamp();
				this.createdUser = mappingUpdateResponse.getMappingVO().getCreatedUser();
				this.lastChangedDate = mappingUpdateResponse.getMappingVO().getLastUpdatedTimestamp();
				this.lastChangedUser = mappingUpdateResponse.getMappingVO().getLastUpdatedUser();
				validationMessage = mappingUpdateResponse.getMessages();
			}
		} else {
		    if(validationFlag==1){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage(
						"spsMapping.create.failure.spsparam.mandatory.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);
		    }else if(validationFlag==2){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage(
						"spsMapping.create.failure.oneAttr.mandatory.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    
			}else if(validationFlag==hsd2ValueFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage("spsMapping.create.failure.hsd2.mandatory.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    				
			}else if(validationFlag==hsd2ValidValueFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage("spsMapping.create.failure.hsd2.value.range.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    				
			}else if(validationFlag==hsd4ValidValueFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage("spsMapping.create.failure.hsd4.value.range.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    				
			}else if(validationFlag==hsd6ValidValueFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage("spsMapping.create.failure.hsd6.value.range.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    				
			}else if(validationFlag==hsd2ValidFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage("spsMapping.create.failure.hsd2.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    				
			}else if(validationFlag==hsd4ValidFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage("spsMapping.create.failure.hsd4.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    				
			}else if(validationFlag==hsd6ValidFlag){
				List message = new ArrayList();
				ErrorMessage errorMessage = new ErrorMessage("spsMapping.create.failure.hsd6.validation");
				message.add(errorMessage);
				this.addAllMessagesToRequest(message);    				
			}
			return "";
		}
		
		return "";
	}

	/**
	 * 
	 * 
	 * @return Returns String.
	 */
	public String retrieveSPSMapping() {
		SPSMappingRetrieveRequest mappingViewRequest = (SPSMappingRetrieveRequest) this
				.getServiceRequest(ServiceManager.SPS_MAPPING_RETRIEVE_REQUEST);
		mappingViewRequest.setSpsParameter(getRequest().getParameter(
				"spsParamterId"));
		SPSMappingViewResponse mappingViewResponse = (SPSMappingViewResponse) this
				.executeService(mappingViewRequest);
//		if (null != mappingViewResponse) {
//			setValuesToBackingBean(mappingViewResponse.getMappingVO());
//		}
//		this.setBreadCrumbText("Administration>> Blue Exchange >> SPSMapping ("
//				+ this.spsParameter + " - " + this.spsParameterDesc
//				+ ") >> Edit");
		return "updateSPSMapping";
	}
	
	public String loadSPSMappingEdit(){
		SPSMappingRetrieveRequest mappingViewRequest = (SPSMappingRetrieveRequest) this
				.getServiceRequest(ServiceManager.SPS_MAPPING_RETRIEVE_REQUEST);
		Application application = FacesContext.getCurrentInstance()
        	.getApplication();

		SPSMappingSearchBackingBean mappingSearchBackingBean = ((SPSMappingSearchBackingBean) application
				.createValueBinding(
                "#{spsMappingSearchBackingBean}")
				.getValue(FacesContext.getCurrentInstance()));
		if(null != mappingSearchBackingBean.selectedSpsId){
			mappingViewRequest.setSpsParameter(mappingSearchBackingBean.selectedSpsId);
		SPSMappingSessionObject mappingSessionObject=new SPSMappingSessionObject();
		  mappingSessionObject.setSpsParameterId( mappingSearchBackingBean.selectedSpsId);
		  getSession().setAttribute("spsMappingSession",mappingSessionObject);
		}
		SPSMappingViewResponse mappingViewResponse = (SPSMappingViewResponse) this
				.executeService(mappingViewRequest);
		if (null != mappingViewResponse) {
			setValuesToBackingBean(mappingViewResponse.getMappingVO());
		}
		this.setBreadCrumbText("Administration>> Blue Exchange >> SPS Mapping ("
				+ mappingViewResponse.getMappingVO().getSpsParameter() + ") >> Edit");
		return WebConstants.UPDATE_SPS_MAPPING;
	}
	
	private void setValuesToBackingBean(SPSMappingVO mappingVO){
		this.spsParameter = mappingVO.getSpsParameterDesc() +"~" +mappingVO.getSpsParameter();
		
		if(null != mappingVO.getEb01Value() && !mappingVO.getEb01Value().equals(""))			
			this.eb01Value = mappingVO.getEb01ValueDesc() + "~" + mappingVO.getEb01Value();

		if(null != mappingVO.getEb02Value() && !mappingVO.getEb02Value().equals(""))
			this.eb02Value = mappingVO.getEb02ValueDesc() + "~" + mappingVO.getEb02Value();
	
		if(null != mappingVO.getEb06Value() && !mappingVO.getEb06Value().equals(""))
			this.eb06Value = mappingVO.getEb06ValueDesc() + "~" + mappingVO.getEb06Value();
	
		if(null != mappingVO.getEb09Value() && !mappingVO.getEb09Value().equals(""))
			this.eb09Value = mappingVO.getEb09ValueDesc() + "~" + mappingVO.getEb09Value();
	
		if(null != mappingVO.getHsd1Value() && !mappingVO.getHsd1Value().equals(""))
			this.hsd1Value = mappingVO.getHsd1ValueDesc() + "~" + mappingVO.getHsd1Value();
	
		if(null != mappingVO.getHsd2Value() && !mappingVO.getHsd2Value().equals(""))
			this.hsd2Value = mappingVO.getHsd2Value();
	
		if(null != mappingVO.getHsd3Value() && !mappingVO.getHsd3Value().equals(""))
			this.hsd3Value = mappingVO.getHsd3ValueDesc() + "~" +mappingVO.getHsd3Value();
	
		if(null != mappingVO.getHsd4Value() && !mappingVO.getHsd4Value().equals(""))
			this.hsd4Value = mappingVO.getHsd4Value();
	
		if(null != mappingVO.getHsd5Value() && !mappingVO.getHsd5Value().equals(""))
			this.hsd5Value = mappingVO.getHsd5ValueDesc() + "~" +mappingVO.getHsd5Value();
	
		if(null != mappingVO.getHsd6Value() && !mappingVO.getHsd6Value().equals(""))
			this.hsd6Value = mappingVO.getHsd6Value();
		if(null != mappingVO.getAccummulatorSPSID() && !mappingVO.getAccummulatorSPSID().equals(""))
			this.accummulatorSPSID = mappingVO.getAccummulatorSPSDesc() + "~" +mappingVO.getAccummulatorSPSID();
		
		this.createdDate = mappingVO.getCreatedTimestamp();
		
		this.createdUser = mappingVO.getCreatedUser();
		
		this.lastChangedDate = mappingVO.getLastUpdatedTimestamp();
		
		this.lastChangedUser = mappingVO.getLastUpdatedUser();
		
	}
	
	/**
	 * @return Returns the validationMessage.
	 */
	public List getValidationMessage() {
		return validationMessage;
	}

	/**
	 * @param validationMessage
	 *            The validationMessage to set.
	 */
	public void setValidationMessage(List validationMessage) {
		this.validationMessage = validationMessage;
	}

	/**
	 * @return Returns the eb01Value.
	 */
	public String getEb01Value() {
		return eb01Value;
	}

	/**
	 * @param eb01Value
	 *            The eb01Value to set.
	 */
	public void setEb01Value(String eb01Value) {
		this.eb01Value = eb01Value;
	}

	/**
	 * @return Returns the eb02Value.
	 */
	public String getEb02Value() {
		return eb02Value;
	}

	/**
	 * @param eb02Value
	 *            The eb02Value to set.
	 */
	public void setEb02Value(String eb02Value) {
		this.eb02Value = eb02Value;
	}

	/**
	 * @return Returns the eb06Value.
	 */
	public String getEb06Value() {
		return eb06Value;
	}

	/**
	 * @param eb06Value
	 *            The eb06Value to set.
	 */
	public void setEb06Value(String eb06Value) {
		this.eb06Value = eb06Value;
	}

	/**
	 * @return Returns the eb09Value.
	 */
	public String getEb09Value() {
		return eb09Value;
	}

	/**
	 * @param eb09Value
	 *            The eb09Value to set.
	 */
	public void setEb09Value(String eb09Value) {
		this.eb09Value = eb09Value;
	}

	/**
	 * @return Returns the hsd1Value.
	 */
	public String getHsd1Value() {
		return hsd1Value;
	}

	/**
	 * @param hsd1Value
	 *            The hsd1Value to set.
	 */
	public void setHsd1Value(String hsd1Value) {
		this.hsd1Value = hsd1Value;
	}

	/**
	 * @return Returns the hsd2Value.
	 */
	public String getHsd2Value() {
		return hsd2Value;
	}

	/**
	 * @param hsd2Value
	 *            The hsd2Value to set.
	 */
	public void setHsd2Value(String hsd2Value) {
		this.hsd2Value = hsd2Value;
	}

	/**
	 * @return Returns the hsd3Value.
	 */
	public String getHsd3Value() {
		return hsd3Value;
	}

	/**
	 * @param hsd3Value
	 *            The hsd3Value to set.
	 */
	public void setHsd3Value(String hsd3Value) {
		this.hsd3Value = hsd3Value;
	}

	/**
	 * @return Returns the hsd4Value.
	 */
	public String getHsd4Value() {
		return hsd4Value;
	}

	/**
	 * @param hsd4Value
	 *            The hsd4Value to set.
	 */
	public void setHsd4Value(String hsd4Value) {
		this.hsd4Value = hsd4Value;
	}

	/**
	 * @return Returns the hsd5Value.
	 */
	public String getHsd5Value() {
		return hsd5Value;
	}

	/**
	 * @param hsd5Value
	 *            The hsd5Value to set.
	 */
	public void setHsd5Value(String hsd5Value) {
		this.hsd5Value = hsd5Value;
	}

	/**
	 * @return Returns the hsd6Value.
	 */
	public String getHsd6Value() {
		return hsd6Value;
	}

	/**
	 * @param hsd6Value
	 *            The hsd6Value to set.
	 */
	public void setHsd6Value(String hsd6Value) {
		this.hsd6Value = hsd6Value;
	}

	/**
	 * @return Returns the spsParameter.
	 */
	public String getSpsParameter() {
		return spsParameter;
	}

	/**
	 * @param spsParameter
	 *            The spsParameter to set.
	 */
	public void setSpsParameter(String spsParameter) {
		this.spsParameter = spsParameter;
	}

	private int validateSPSMapping() {

	    if((this.getSpsParameter() == null || "".equalsIgnoreCase(this
				.getSpsParameter().trim()))){
	        this.setSpsParamFlag(true);
	        return mandateCheckValuesFlag;
	    }
	    if((       (this.getEb01Value() == null || ""
	            		.equalsIgnoreCase(this.getEb01Value().trim()))
				&& (this.getEb02Value() == null || ""
						.equalsIgnoreCase(this.getEb02Value().trim()))
				&& (this.getEb06Value() == null || ""
						.equalsIgnoreCase(this.getEb06Value().trim()))
				&& (this.getEb09Value() == null || ""
						.equalsIgnoreCase(this.getEb09Value().trim()))
				&& (this.getHsd1Value() == null || ""
						.equalsIgnoreCase(this.getHsd1Value().trim()))
				&& (this.getHsd2Value() == null || ""
						.equalsIgnoreCase(this.getHsd2Value().trim()))
				&& (this.getHsd3Value() == null || ""
						.equalsIgnoreCase(this.getHsd3Value().trim()))
				&& (this.getHsd4Value() == null || ""
						.equalsIgnoreCase(this.getHsd4Value().trim()))
				&& (this.getHsd5Value() == null || ""
						.equalsIgnoreCase(this.getHsd5Value().trim())) 
				&& (this.getHsd6Value() == null || ""
				        .equalsIgnoreCase(this.getHsd6Value().trim()))
         ))
			return atleastOneValueFlag;
	    if ((null!=this.getHsd1Value() && !"".equals(this.getHsd1Value()
				.trim()))
				&& (null!=this.getHsd3Value() && "".equals(this
						.getHsd2Value().trim()))) {
			return hsd2ValueFlag;
		}
	    if(null!=this.getHsd2Value() && !"".equals(this.getHsd2Value().trim())){
	    	if(!StringUtil.isInteger(this.getHsd2Value().trim()))
	    		return hsd2ValidFlag;
	    	else{
	    		int hsd2Value = Integer.parseInt(this.getHsd2Value().trim());
	    		if(hsd2Value < 1 || hsd2Value>15)
	    			return hsd2ValidValueFlag;
	    	}
	    		
	    }
	    if(null!=this.getHsd4Value() && !"".equals(this.getHsd4Value().trim())){
	    	if(!StringUtil.isInteger(this.getHsd4Value().trim()))
	    		return hsd4ValidFlag;
	    	else{
	    		int hsd4Value = Integer.parseInt(this.getHsd4Value().trim());
	    		if(hsd4Value < 1 || hsd4Value>6)
	    			return hsd4ValidValueFlag;
	    	}
	    		
	    }
	    if(null!=this.getHsd6Value() && !"".equals(this.getHsd6Value().trim())){
	    	if(!StringUtil.isInteger(this.getHsd6Value().trim()))
	    		return hsd6ValidFlag;
	    	else{
	    		int hsd6Value = Integer.parseInt(this.getHsd6Value().trim());
	    		if(hsd6Value < 1 || hsd6Value>3)
	    			return hsd6ValidValueFlag;
	    	}
	    		
	    }
	    
		    return createValidationFlag;
	}

	private String getIdFromCharSeperatedString(String value, String delim) {
		return value == null ? null : (("".equalsIgnoreCase(value) || value == null) ? null
				: value.split(delim)[1]);
	}

	/**
	 * @return Returns the spsParamFlag.
	 */
	public boolean isSpsParamFlag() {
		return spsParamFlag;
	}

	/**
	 * @param spsParamFlag
	 *            The spsParamFlag to set.
	 */
	public void setSpsParamFlag(boolean spsParamFlag) {
		this.spsParamFlag = spsParamFlag;
	}

	/**
	 * 
	 * @return
	 */
	private SPSMappingVO getSPSMappingVO() {
		SPSMappingVO mappingVO = new SPSMappingVO();
		mappingVO.setSpsParameter(getIdFromCharSeperatedString(this
				.getSpsParameter(), "~"));
		mappingVO.setEb01Value(getIdFromCharSeperatedString(
				this.getEb01Value(), "~"));
		List eb01List = new ArrayList();
		eb01List.add("C");
		eb01List.add("G");
		String eb01 = mappingVO.getEb01Value();
		if(null==eb01 ||"".equals(eb01) ||!(eb01List.contains(eb01))){
			mappingVO.setEb02Value(null);		
			this.setEb02Value(null);
		}
		else
			mappingVO.setEb02Value(getIdFromCharSeperatedString(
					this.getEb02Value(), "~"));
		mappingVO.setEb06Value(getIdFromCharSeperatedString(
				this.getEb06Value(), "~"));
		mappingVO.setEb09Value(getIdFromCharSeperatedString(
				this.getEb09Value(), "~"));
		mappingVO.setHsd1Value(getIdFromCharSeperatedString(
				this.getHsd1Value(), "~"));
		mappingVO.setHsd2Value(this.getHsd2Value());				
		mappingVO.setHsd3Value(getIdFromCharSeperatedString(
				this.getHsd3Value(), "~"));
		mappingVO.setHsd4Value(this.getHsd4Value());	
		mappingVO.setHsd5Value(getIdFromCharSeperatedString(
				this.getHsd5Value(), "~"));
		mappingVO.setHsd6Value(this.getHsd6Value());
		mappingVO.setAccummulatorSPSID(getIdFromCharSeperatedString(
				this.getAccummulatorSPSID(), "~"));
		return mappingVO;
	}
	
	private void intialize(){
		this.setSpsParameter(null);
		this.setEb01Value(null);
		this.setEb02Value(null);
		this.setEb06Value(null);
		this.setEb09Value(null);
		this.setHsd1Value(null);
		this.setHsd2Value(null);
		this.setHsd3Value(null);
		this.setHsd4Value(null);
		this.setHsd5Value(null);
		this.setHsd6Value(null);

	}
	
	 /**
     * Retrieves the SPSMappingSessionObject from session.
     * 
     * @return SPSMappingSessionObject
     */
    protected SPSMappingSessionObject getSPSMappingSessionObject() {
    	SPSMappingSessionObject spsMappingSessionObject = (SPSMappingSessionObject) getSession()
                .getAttribute(SPS_MAPPING_SESSION_KEY);

        if (spsMappingSessionObject == null) {
        	spsMappingSessionObject = new SPSMappingSessionObject();
            getSession().setAttribute(SPS_MAPPING_SESSION_KEY,
            		spsMappingSessionObject);
        }
        return spsMappingSessionObject;
    }
	
	/**
	 * @return Returns the createdDate.
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate The createdDate to set.
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the lastChangedDate.
	 */
	public Date getLastChangedDate() {
		return lastChangedDate;
	}
	/**
	 * @param lastChangedDate The lastChangedDate to set.
	 */
	public void setLastChangedDate(Date lastChangedDate) {
		this.lastChangedDate = lastChangedDate;
	}
	/**
	 * @return Returns the lastChangedUser.
	 */
	public String getLastChangedUser() {
		return lastChangedUser;
	}
	/**
	 * @param lastChangedUser The lastChangedUser to set.
	 */
	public void setLastChangedUser(String lastChangedUser) {
		this.lastChangedUser = lastChangedUser;
	}
	
	public String printStringForSPSMapping(){
	    return "printSPSMapping";
	}
	/**
	 * @return Returns the accummulatorSPSID.
	 */
	public String getAccummulatorSPSID() {
		return accummulatorSPSID;
	}
	/**
	 * @param accummulatorSPSID The accummulatorSPSID to set.
	 */
	public void setAccummulatorSPSID(String accummulatorSPSID) {
		this.accummulatorSPSID = accummulatorSPSID;
	}
}