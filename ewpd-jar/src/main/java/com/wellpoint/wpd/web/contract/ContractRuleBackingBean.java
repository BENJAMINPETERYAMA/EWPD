/*
 * ContractRuleBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import javax.faces.component.html.HtmlColumn;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;

import com.sun.faces.renderkit.html_basic.HiddenRenderer;
import com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerPopupDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerRuleIdDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerSPSIdDisplayVO;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.common.util.RuleUtil;
import com.wellpoint.wpd.business.contract.locateresult.RuleCodeRanges;
import com.wellpoint.wpd.business.contract.locateresult.RuleLocateResult;
import com.wellpoint.wpd.business.contract.locateresult.RuleSequenceResults;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.GroupRule;
import com.wellpoint.wpd.common.contract.request.ContractWebServiceRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveRulesRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBasicInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveDateSegmentCommentRequest;
import com.wellpoint.wpd.common.contract.response.ContractWebServiceResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveRulesResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBasicInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveDateSegmentCommentResponse;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleClaimCodeSequenceBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleClaimSequenceBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleCodeSequenceBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleDisplayBO;
import com.wellpoint.wpd.common.standardbenefit.bo.RuleSequenceBO;
import com.wellpoint.wpd.common.standardbenefit.request.GroupRuleRequest;
import com.wellpoint.wpd.common.standardbenefit.request.RuleRequest;
import com.wellpoint.wpd.common.standardbenefit.response.GroupRuleResponse;
import com.wellpoint.wpd.common.standardbenefit.response.RuleResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class ContractRuleBackingBean extends ContractBackingBean {

	//private String loadContractRules;
	private HtmlInputHidden loadContractRules=new HtmlInputHidden();



	private boolean exclusionRuleSelected = false;

	private boolean umRuleSelected = false;

	private boolean denialRuleSelected = false;

	private boolean pnrRuleSelected = false;
	private boolean headerRuleBCselected = false;
	
	private boolean headerRuleBenefitSelected = false;

	private boolean renderFlagForPanel = false;

	private boolean requiredRuleType = false;

	private List ruleInformationList;

	private boolean renderFlag;

	private boolean checkIn;

	private String state;

	private String status;

	private String version;

	private List validationMessages;

	private boolean ruleInvalid = false;

	private String ruleHidden;

	private String selectedDateSysId;

	private String selectedRuleSysID;

	private String ruleType;

	private List ruleResultList;

	private List messageList;

	private List ruleInterList = null;

	private HtmlPanelGrid panel;

	private HtmlPanelGrid headingPanel;

	private Map ruleCommentMap = new HashMap();

	private String printValue;

	private String mapForPrint;

	private String ewpdGenRuleID;

	private boolean hasValidationErrors;

	private List ruleTypeListForCombo;

	private List ruleSequenceLists;

	//private String sequenceHidden;
	private HtmlInputHidden sequenceHidden =new HtmlInputHidden();
	
	private String ruleIdDetails;

	String filename = "rma.properties";

	private String strRmaLink;
	
	private String checkMode;
	
	private String benefitComponentName;
	
	private String benefitComponentId;
	
	private String benefitName;
	
	// eBX error validation
	
	private String invokeWebService;
	// SSCR 16332  -START
	private List<EbxWebSerDisplayVO> ebxWebSerDisplayList = null;
	private int initEbxWs;
	private String webServiceFlag ="false";
	private int initEbxRuleId;
	private int initEbxSPSId;
	private List<EbxWebSerRuleIdDisplayVO> ebxWebSerRuleIdDisplayLst = null;
	private List<EbxWebSerSPSIdDisplayVO> ebxWebSerSPSIdDisplayLst = null;
	private String ruleIdPopup;
	private String spsId;
	EbxWebSerPopupDisplayVO ebxWebSerPopupDisplayVO;
	private String ebxProcessInterruptMsg = null;
	
	
	//RMA ICD-10
	private Map<Integer, RuleSequenceBO> ruleSequenceMap;
	private RuleSequenceBO ruleSequenceBO;
	private Map<Integer, RuleCodeSequenceBO> ruleCodeSequenceMap;
	private Map<Integer, RuleClaimSequenceBO> ruleClaimSequenceMap;
	private RuleCodeSequenceBO ruleCodeSequenceBO;
	private RuleClaimSequenceBO ruleClaimSequenceBO;
	private Map<Integer, RuleClaimCodeSequenceBO> ruleClaimCodeSequenceMap;
	private RuleClaimCodeSequenceBO ruleClaimCodeSequenceBO;
	
	
	public List<EbxWebSerDisplayVO> getEbxWebSerDisplayList() {
		return ebxWebSerDisplayList;
	}

	public void setEbxWebSerDisplayList(
			List<EbxWebSerDisplayVO> ebxWebSerDisplayList) {
		this.ebxWebSerDisplayList = ebxWebSerDisplayList;
	}

	public int getInitEbxWs() {
		this.ebxWebSerDisplayList = (List<EbxWebSerDisplayVO>) getSession().getAttribute("wSErrorDisplayList");
		this.ebxWebSerPopupDisplayVO =(EbxWebSerPopupDisplayVO)getSession().getAttribute("ebxWebSerPopupDisplayVO");
		this.ebxProcessInterruptMsg = (String)getSession().getAttribute("ebxProcessInterruptMsg");
		return initEbxWs;
	}
	
	public void setInitEbxWs(int initEbxWs) {
		this.initEbxWs = initEbxWs;
	}
	
	public String getWebServiceFlag() {
		return webServiceFlag;
	}

	public void setWebServiceFlag(String webServiceFlag) {
		this.webServiceFlag = webServiceFlag;
	}
	
	public int getInitEbxRuleId() {
		HttpServletRequest request = getRequest();
		String ruleId = null;
		if(null!=request.getParameter("ruleId")){
			ruleId=request.getParameter("ruleId");
		}
		if(ruleId != null){	
			this.setRuleIdPopup(ruleId);
			List<ContractWebServiceVO> contractWSErrorList =(List<ContractWebServiceVO>) getSession().getAttribute("contractWSErrorList");
			this.setEbxWebSerRuleIdDisplayLst(DomainUtil.ruleIdPopupDisplay(contractWSErrorList,ruleId));
		}
		
		return initEbxRuleId;
	}

	public void setInitEbxRuleId(int initEbxRuleId) {
		this.initEbxRuleId = initEbxRuleId;
	}

	public int getInitEbxSPSId() {
		HttpServletRequest request = getRequest();
		String spsId = null;
		if(null!=request.getParameter("spsId")){
			spsId=request.getParameter("spsId");
		}
		if(spsId != null){	
			this.setSpsId(spsId);
			List<ContractWebServiceVO> contractWSErrorList =(List<ContractWebServiceVO>) getSession().getAttribute("contractWSErrorList");
			this.setEbxWebSerSPSIdDisplayLst(DomainUtil.spsIdPopupDisplay(contractWSErrorList,spsId));
		}
		return initEbxSPSId;
	}

	public void setInitEbxSPSId(int initEbxSPSId) {
		this.initEbxSPSId = initEbxSPSId;
	}

	public List<EbxWebSerRuleIdDisplayVO> getEbxWebSerRuleIdDisplayLst() {
		return ebxWebSerRuleIdDisplayLst;
	}

	public void setEbxWebSerRuleIdDisplayLst(
			List<EbxWebSerRuleIdDisplayVO> ebxWebSerRuleIdDisplayLst) {
		this.ebxWebSerRuleIdDisplayLst = ebxWebSerRuleIdDisplayLst;
	}
	
	public List<EbxWebSerSPSIdDisplayVO> getEbxWebSerSPSIdDisplayLst() {
		return ebxWebSerSPSIdDisplayLst;
	}

	public void setEbxWebSerSPSIdDisplayLst(
			List<EbxWebSerSPSIdDisplayVO> ebxWebSerSPSIdDisplayLst) {
		this.ebxWebSerSPSIdDisplayLst = ebxWebSerSPSIdDisplayLst;
	}
	
	
	public String getRuleIdPopup() {
		return ruleIdPopup;
	}

	public void setRuleIdPopup(String ruleIdPopup) {
		this.ruleIdPopup = ruleIdPopup;
	}
	public String getSpsId() {
		return spsId;
	}

	public void setSpsId(String spsId) {
		this.spsId = spsId;
	}
	
	public EbxWebSerPopupDisplayVO getEbxWebSerPopupDisplayVO() {
		return ebxWebSerPopupDisplayVO;
	}

	public void setEbxWebSerPopupDisplayVO(
			EbxWebSerPopupDisplayVO ebxWebSerPopupDisplayVO) {
		this.ebxWebSerPopupDisplayVO = ebxWebSerPopupDisplayVO;
	}

	// SSCR 16332 - END

	/**
	 * @return the invokeWebService
	 */
	public String getInvokeWebService() {
		return invokeWebService;
	}

	/**
	 * @param invokeWebService the invokeWebService to set
	 */
	public void setInvokeWebService(String invokeWebService) {
		this.invokeWebService = invokeWebService;
	}
	//sscr 17571
	private String vendorFlag = "false";
	
	private String closePopup = "false";
		  
	
	public String getClosePopup() {
		return closePopup;
	}
	public void setClosePopup(String closePopup) {
		this.closePopup = closePopup;
	}
	
	public String getVendorFlag() {
		return vendorFlag;
	}
	public void setVendorFlag(String vendorFlag) {
		this.vendorFlag = vendorFlag;
	}

	
	/**
	 * @return Returns the strRmaLink.
	 */
	public String getStrRmaLink() {
		return strRmaLink;
	}

	/**
	 * @param strRmaLink The strRmaLink to set.
	 */
	public void setStrRmaLink(String strRmaLink) {
		this.strRmaLink = strRmaLink;
	}

	private GroupRule groupRule;

	private boolean rulesValueCheck = true;

	private HtmlPanelGrid panelRuleSequences;

	private String printValueSequence;

	private String ruleId;

	private String ruleDescription;

	private String ruleSearchWord1;

	private String ruleSearchWord2;

	private String ruleSearchWord3;

	private int hiddenDateSegmentSysId;

	private List exclusionRuleList;

	private List denialRuleList;

	private List umRuleList;

	private List pnrRuleList;
	
	private String tag;
	
	private String ruleVersion;

	// Added for Redirecting to New JSPs Depending on the RuleType

	private String blzRuleType;
	//ICD10 change to get the Contract ID from session
	private String contractID;

	/**
	 * 
	 *
	 */
	public ContractRuleBackingBean() {
		super();
		if (null != getContractSession().getContractKeyObject()) {
			this.setContractID(getContractSession()
					.getContractKeyObject().getContractId());
		}
		this.setBreadCrumbText();
		if (null != getContractSession().getContractKeyObject()) {
			this.setHiddenDateSegmentSysId(getContractSession()
					.getContractKeyObject().getDateSegmentId());
		}
	}

	/**
	 * @return Returns the printValue.
	 */
	public String getPrintValue() {
		String requestForPrint = getRequest().getParameter(
				"printValueForGenInfo");
		if (null != requestForPrint
				&& !requestForPrint.equals(WebConstants.EMPTY_STRING)) {
			printValue = requestForPrint;
		} else {
			printValue = WebConstants.EMPTY_STRING;
		}
		return printValue;
	}

	/**
	 * @param printValue
	 *            The printValue to set.
	 */
	public void setPrintValue(String printValue) {
		this.printValue = printValue;
	}

	/**
	 * @return Returns the sequenceHidden.
	 */

	//WAS 7.0 Changes - Binding variable rootQuestionLoad modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
	
	/**
	 * 
	 * @return HtmlInputHidden
	 */
	public HtmlInputHidden  getSequenceHidden() {
		this.getRuleSequenceLists();
		//return sequenceHidden;
		sequenceHidden.setValue(sequenceHidden);
        return sequenceHidden;  
	}

	/**
	 * Sets the mappings sys id to session.
	 * @param sysId
	 */
	private void setSysIdToSession(String ruleId) {

		getSession().setAttribute("RuleIdPrint", ruleId);

	}

	/**
	 * @return Returns the ruleSequenceLists.
	 */
	public List getRuleSequenceLists() {

		if (ruleSequenceLists != null)
			return ruleSequenceLists;

		RetrieveRulesRequest retrieveRulesRequest = (RetrieveRulesRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE__RULES);
		super.setContractKeyToRequest(retrieveRulesRequest);
		retrieveRulesRequest
				.setAction(RetrieveRulesRequest.RETRIEVE_RULES_SEQUENCE);

		String ruleId = ruleIdDetails;
		String ruleIdSession = (String) getSession().getAttribute(
				"RULE_SEQUENCE_PRINT");

		if (null != ruleId) {
			getSession().setAttribute("RULE_SEQUENCE_PRINT", ruleId);
			retrieveRulesRequest.setRuleId(ruleId);
			RetrieveRulesResponse searchResponse = (RetrieveRulesResponse) executeService(retrieveRulesRequest);
			validationMessages = new ArrayList();
			if (null != searchResponse) {
				ruleSequenceLists = searchResponse.getSearchResultList();
				if (ruleSequenceLists != null && ruleSequenceLists.size() > 0) {
					RuleSequenceResults ruleSequenceResults = (RuleSequenceResults) ruleSequenceLists
							.get(0);
					if (null != ruleSequenceResults.getRuleId()
							&& !"".equals(ruleSequenceResults.getRuleId())) {

						this.ruleId = ruleSequenceResults.getRuleId();

					}

					this.strRmaLink = getLink();

					if (null != ruleSequenceResults.getRuleShortDesc()
							&& !"".equals(ruleSequenceResults
									.getRuleShortDesc())) {

						this.ruleDescription = ruleSequenceResults
								.getRuleShortDesc();

					}

					if (null != ruleSequenceResults.getRuleSearchWrd1()
							&& !"".equals(ruleSequenceResults
									.getRuleSearchWrd1())) {

						this.ruleSearchWord1 = ruleSequenceResults
								.getRuleSearchWrd1();

					}
					if (null != ruleSequenceResults.getRuleSearchWrd2()
							&& !"".equals(ruleSequenceResults
									.getRuleSearchWrd2())) {

						this.ruleSearchWord2 = ruleSequenceResults
								.getRuleSearchWrd2();

					}

					if (null != ruleSequenceResults.getRuleSearchWrd3()
							&& !"".equals(ruleSequenceResults
									.getRuleSearchWrd3())) {

						this.ruleSearchWord3 = ruleSequenceResults
								.getRuleSearchWrd3();

					}
					if(null != ruleSequenceResults.getTag() && !"".equals(ruleSequenceResults.getTag())){
						this.tag=ruleSequenceResults.getTag();
					}
					if(null != ruleSequenceResults.getRuleVersion() && !"".equals(ruleSequenceResults.getRuleVersion())){
						this.ruleVersion=ruleSequenceResults.getRuleVersion();
					}

					//this.renderFlag = true;  
				}
			}
		} else if (null != ruleIdSession) {
			//getSession().setAttribute("RULE_SEQUENCE_PRINT",ruleId);
			retrieveRulesRequest.setRuleId(ruleIdSession);
			RetrieveRulesResponse searchResponse = (RetrieveRulesResponse) executeService(retrieveRulesRequest);
			validationMessages = new ArrayList();
			if (null != searchResponse) {
				ruleSequenceLists = searchResponse.getSearchResultList();
				if (ruleSequenceLists != null && ruleSequenceLists.size() > 0) {
					RuleSequenceResults ruleSequenceResults = (RuleSequenceResults) ruleSequenceLists
							.get(0);
					if (null != ruleSequenceResults.getRuleId()
							&& !"".equals(ruleSequenceResults.getRuleId())) {

						this.ruleId = ruleSequenceResults.getRuleId();

					}

					this.strRmaLink = getLink();
					if (null != ruleSequenceResults.getRuleShortDesc()
							&& !"".equals(ruleSequenceResults
									.getRuleShortDesc())) {

						this.ruleDescription = ruleSequenceResults
								.getRuleShortDesc();

					}

					if (null != ruleSequenceResults.getRuleSearchWrd1()
							&& !"".equals(ruleSequenceResults
									.getRuleSearchWrd1())) {

						this.ruleSearchWord1 = ruleSequenceResults
								.getRuleSearchWrd1();

					}
					if (null != ruleSequenceResults.getRuleSearchWrd2()
							&& !"".equals(ruleSequenceResults
									.getRuleSearchWrd2())) {

						this.ruleSearchWord2 = ruleSequenceResults
								.getRuleSearchWrd2();

					}

					if (null != ruleSequenceResults.getRuleSearchWrd3()
							&& !"".equals(ruleSequenceResults
									.getRuleSearchWrd3())) {

						this.ruleSearchWord3 = ruleSequenceResults
								.getRuleSearchWrd3();

					}
					if(null != ruleSequenceResults.getTag() && !"".equals(ruleSequenceResults.getTag())){
						this.tag=ruleSequenceResults.getTag();
					}
					if(null != ruleSequenceResults.getRuleVersion() && !"".equals(ruleSequenceResults.getRuleVersion())){
						this.ruleVersion=ruleSequenceResults.getRuleVersion();
					}
				}
			}
		}
		return ruleSequenceLists;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getRuleVersion() {
		return ruleVersion;
	}

	public void setRuleVersion(String ruleVersion) {
		this.ruleVersion = ruleVersion;
	}

	/** 
	 * @return Returns the RMA link read from the rma.properties.
	 */
	private String getLink() {
		String strUrl = null;
		try {
			InputStream inputStream = this.getClass().getClassLoader()
					.getResourceAsStream(filename);
			Properties properties = new Properties();

			properties.load(inputStream);

			strUrl = properties.getProperty("url");
		} catch (Exception e) {
			SevereException se = new SevereException(
					"Unexpected error caught in CommonBackingBean", null, e);
			Logger.logException(se);
		}
		return strUrl;

	}

	/** 
	 * @return Returns the ruleResultList.
	 */
	public List getRuleResultList() {

		RetrieveRulesRequest retrieveRulesRequest = (RetrieveRulesRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE__RULES);
		super.setContractKeyToRequest(retrieveRulesRequest);
		int id = super.getContractSession().getContractKeyObject()
				.getProductId();
		int dsId = super.getContractSession().getContractKeyObject()
				.getDateSegmentId();
		String ruleType = getRequest().getParameter(WebConstants.RULE_TYPE);
		validationMessages = new ArrayList();
		if (id == 0) {
			validationMessages.add(new InformationalMessage(
					WebConstants.NO_PRODUCT_ATTACHED));
			this.setValidationMessages(validationMessages);
			addAllMessagesToRequest(validationMessages);
			ruleResultList = new ArrayList();
			return ruleResultList;
		}
		retrieveRulesRequest.setProductSysId(id);
		retrieveRulesRequest.setDateSegmentSysId(dsId);
		retrieveRulesRequest.setRuleType(ruleType);
		retrieveRulesRequest
				.setAction(RetrieveRulesRequest.RETRIEVE_RULES_POPUP);
		RetrieveRulesResponse searchResponse = (RetrieveRulesResponse) executeService(retrieveRulesRequest);
		if (null != searchResponse) {
			ruleResultList = searchResponse.getSearchResultList();
			if (ruleResultList != null && ruleResultList.size() > 0) {
				this.renderFlag = true;
			}
		}
		return ruleResultList;
	}

	/**
	 * 
	 * @return
	 */
	private boolean valid() {
		boolean valid = true;
		if (null == this.ruleType || "".equals(this.ruleType)) {
			this.requiredRuleType = true;
			valid = false;
		}
		if (null == this.getRuleHidden() || "".equals(this.getRuleHidden())) {
			this.ruleInvalid = true;
			valid = false;
		}
		return valid;
	}

	/**
	 * 
	 * @return
	 */
	public String addAndStoreRuleInfo() {
		if (valid()) {
			RetrieveRulesRequest retrieveRulesRequest = (RetrieveRulesRequest) this
					.getServiceRequest(ServiceManager.RETRIEVE__RULES);
			retrieveRulesRequest.setAction(RetrieveRulesRequest.ATTACH_RULES);
			super.setContractKeyToRequest(retrieveRulesRequest);
			List ruleList;
			ruleList = WPDStringUtil.getListFromTildaString(this
					.getRuleHidden(), 3, 2, 2);
			retrieveRulesRequest.setRuleList(ruleList);
			retrieveRulesRequest.setContractKeyObject(getContractSession()
					.getContractKeyObject());
			if (null != ruleList && ruleList.size() != 0) {
				RetrieveRulesResponse searchResponse = (RetrieveRulesResponse) executeService(retrieveRulesRequest);
				if (null != searchResponse
						&& null != searchResponse.getMessages()) {
					this.setValidationMessages(searchResponse.getMessages());
				}
			}
			this.ruleHidden = WebConstants.EMPTY_STRING;
			if ("Exclusion Rule".equals(this.getRuleType())) {
				this.exclusionRuleSelected = true;
				this.umRuleSelected = false;
				this.denialRuleSelected = false;
				this.pnrRuleSelected = false;

			}
			if ("UM Rule".equals(this.getRuleType())) {
				this.umRuleSelected = true;
				this.exclusionRuleSelected = false;
				this.denialRuleSelected = false;
				this.pnrRuleSelected = false;
			}
			if ("Denial Rule".equals(this.getRuleType())) {
				this.denialRuleSelected = true;
				this.umRuleSelected = false;
				this.exclusionRuleSelected = false;
				this.pnrRuleSelected = false;
			}
			if ("PNR Rule".equals(this.getRuleType())) {
				this.pnrRuleSelected = true;
				this.denialRuleSelected = false;
				this.umRuleSelected = false;
				this.exclusionRuleSelected = false;
			}
			this.ruleType = "";
			getRequest().setAttribute("RETAIN_Value", "");
		} else {
			validationMessages = new ArrayList();
			validationMessages.add(new ErrorMessage(
					WebConstants.MANDATORY_FIELDS_REQUIRED));
		}
		if (this.exclusionRuleSelected) {
			this.loadContractRules(BusinessConstants.RULE_TYPE_EXCLUSION);
		} else if (this.denialRuleSelected) {
			this.loadContractRules(BusinessConstants.RULE_TYPE_DENIAL);
		} else if (this.umRuleSelected) {
			this.loadContractRules(BusinessConstants.RULE_TYPE_UM);
		} else if (this.pnrRuleSelected) {
			this.loadContractRules(BusinessConstants.RULE_TYPE_PNR);
		}

		addAllMessagesToRequest(this.validationMessages);
		return "displayRules";
	}

	/**
	 * 
	 * @return
	 */
	public String displayRules() {
		if (super.isViewMode()) {
			return "displayRulesView";
		} else {
			return "displayRules";
		}
	}

	/**
	 * @return Returns the ruleInformationList.
	 */
	//presently used for printing 
	public List retrieveInformationList() {

		RetrieveRulesRequest retrieveRulesRequest = (RetrieveRulesRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE__RULES);
		retrieveRulesRequest
				.setAction(RetrieveRulesRequest.RETRIVE_RULES_COMPLETE);
		super.setContractKeyToRequest(retrieveRulesRequest);
		int id = super.getContractSession().getContractKeyObject()
				.getDateSegmentId();
		retrieveRulesRequest.setDateSegmentSysId(id);
		retrieveRulesRequest.setRuleType("%");
		RetrieveRulesResponse searchResponse = null;
		if (id != 0) {
			searchResponse = (RetrieveRulesResponse) executeService(retrieveRulesRequest);
		}
		if (null != searchResponse) {
			ruleInformationList = searchResponse.getSearchResultList();
			if (ruleInformationList != null && ruleInformationList.size() > 0) {
				this.renderFlag = true;
				this.renderFlagForPanel = true;
			} else {
				this.renderFlag = false;
				this.renderFlagForPanel = false;
			}
		}
		this.addAllMessagesToRequest(this.validationMessages);
		return ruleInformationList;
	}

	/**
	 * 
	 * @return
	 */
	public String done() {
		
		List msgListOne = new ArrayList();
		List msgListTwo = new ArrayList();
		//saving process
		RetrieveRulesRequest retrieveRulesRequest = (RetrieveRulesRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE__RULES);
		retrieveRulesRequest.setAction(RetrieveRulesRequest.ATTACH_RULES);
		super.setContractKeyToRequest(retrieveRulesRequest);
		List ruleList;
		ruleList = WPDStringUtil.getListFromTildaString(this.getRuleHidden(),
				3, 2, 2);
		retrieveRulesRequest.setRuleList(ruleList);
		RetrieveRulesResponse searchResponse = null;
		if (null != ruleList && ruleList.size() > 0) {
			searchResponse = (RetrieveRulesResponse) executeService(retrieveRulesRequest);
			if (null != searchResponse
					&& searchResponse.getMessages().size() > 0) {
				msgListOne = searchResponse.getMessages();
			}
		}
		if ("Exclusion Rule".equals(this.getRuleType())) {
			this.exclusionRuleSelected = true;
			this.umRuleSelected = false;
			this.denialRuleSelected = false;
			this.pnrRuleSelected = false;

		}
		if ("UM Rule".equals(this.getRuleType())) {
			this.umRuleSelected = true;
			this.exclusionRuleSelected = false;
			this.denialRuleSelected = false;
			this.pnrRuleSelected = false;
		}
		if ("Denial Rule".equals(this.getRuleType())) {
			this.denialRuleSelected = true;
			this.umRuleSelected = false;
			this.exclusionRuleSelected = false;
			this.pnrRuleSelected = false;
		}
		if ("PNR Rule".equals(this.getRuleType())) {
			this.pnrRuleSelected = true;
			this.denialRuleSelected = false;
			this.umRuleSelected = false;
			this.exclusionRuleSelected = false;
		}
		this.ruleType = "";
		if (this.exclusionRuleSelected) {
			this.loadContractRules(BusinessConstants.RULE_TYPE_EXCLUSION);
		} else if (this.denialRuleSelected) {
			this.loadContractRules(BusinessConstants.RULE_TYPE_DENIAL);
		} else if (this.umRuleSelected) {
			this.loadContractRules(BusinessConstants.RULE_TYPE_UM);
		} else if (this.pnrRuleSelected) {
			this.loadContractRules(BusinessConstants.RULE_TYPE_PNR);
		}
		this.ruleHidden = WebConstants.EMPTY_STRING;
		this.updateRules();
		//check in 
		SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
				.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);

		SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
		ContractVO contractVO = new ContractVO();
		contractVO.setContractSysId(this.getContractSession()
				.getContractKeyObject().getContractSysId());
		contractVO.setContractId(this.getContractSession()
				.getContractKeyObject().getContractId());
		contractVO.setVersion(this.getContractSession().getContractKeyObject()
				.getVersion());
		contractVO.setStatus(this.getContractSession().getContractKeyObject()
				.getStatus());
		saveContractBasicInfoRequest.setContractVO(contractVO);
		saveContractBasicInfoRequest
				.setAction(SaveContractBasicInfoRequest.CHECKIN_CONTRACT);
		saveContractBasicInfoRequest.setChechIn(this.checkIn);
		// SSCR 16332 -Start
		if (this.checkIn && !getInvokeWebService().equals("confirm") && getInvokeWebService() != null) {
			saveContractBasicInfoRequest.setEBXWS(true);
			saveContractBasicInfoRequest.setCarvConfirm(true); //Tab impl
		}
		if (null != getSession().getAttribute("contractWSErrorList")){
			getSession().removeAttribute("contractWSErrorList");
		}
		if (null != getSession().getAttribute("wSErrorDisplayList")){
			getSession().removeAttribute("wSErrorDisplayList"); 
		}
		if(null != getSession().getAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST)){
			getSession().removeAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST);
		}
		saveContractBasicInfoRequest.setEbxAndCarvErrorsByPassCmts(this.getInvokeWebService());
		// SSCR 16332 -End
		if (null != getSession().getAttribute("AM_BENEFIT"))
			getSession().removeAttribute("AM_BENEFIT"); // clearing for adminmethod contract validation popup
		if (null != getSession().getAttribute("AM_BC_KEY"))
			getSession().removeAttribute("AM_BC_KEY"); // clearing for adminmethod contract validation popup
		if (null != getSession().getAttribute("DIRECT_CLICK"))
			getSession().removeAttribute("DIRECT_CLICK"); // clearing for adminmethod contract validation popup

		saveContractBasicInfoResponse = (SaveContractBasicInfoResponse) executeService(saveContractBasicInfoRequest);
		if (null != saveContractBasicInfoResponse) {
			if (saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_RESULTS) {
				hasValidationErrors = true;
				setValuesForAdminMethodValidation();
				getRequest().setAttribute("RETAIN_Value", "");
				return "";
			} else if (saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.SPS_VALIDATION_ERROR) {
				getSession().setAttribute(
						"AM_CONTRACT_ID",
						getContractSession().getContractKeyObject()
								.getContractSysId()
								+ "");
				if (null != getSession().getAttribute(
						"adminmethodContractCodedSPSTreeBackingBean"))
					getSession().removeAttribute(
							"adminmethodContractCodedSPSTreeBackingBean");
				if (this.validationMessages != null
						&& saveContractBasicInfoResponse.getMessages() != null)
					this.validationMessages
							.addAll(saveContractBasicInfoResponse.getMessages());
				addAllMessagesToRequest(saveContractBasicInfoResponse
						.getMessages());
				setValuesForAdminMethodValidation();
				return "";
			} else if (saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_WAIT) {
				getSession().setAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN,
						"contractRuleBackingBean");
				getSession().setAttribute(
						WebConstants.OBJECT_VALUE_FOR_CHECKIN, this);
				getSession().setAttribute(
						WebConstants.ENTITY_ID_FOR_CHECKIN,
						new Integer(saveContractBasicInfoRequest
								.getContractKeyObject().getDateSegmentId()));
				getSession().setAttribute(
						WebConstants.CONTRACT_ID_FOR_CHECKIN,
						new Integer(saveContractBasicInfoRequest
								.getContractKeyObject().getContractSysId()));
				getSession().setAttribute(WebConstants.ENTITY_TYPE_FOR_CHECKIN,
						WebConstants.ENTITY_TYPE_CONTRACT);
				getSession().setAttribute(
						"AM_ENTITY_NAME",
						getContractSession().getContractKeyObject()
								.getContractId());
				return "validationWait";
			}else if(saveContractBasicInfoResponse.getCondition() == SaveProductResponse.DO_EBX_WEBSERVICE_PROCESS || saveContractBasicInfoResponse.getCarvedOutCondition() == SaveProductResponse.DO_CARVEDOUT_PROCESS ){
				//SSCR 17571 - Tab Impl
				if(null !=saveContractBasicInfoResponse.getCarvedoutMap() && !saveContractBasicInfoResponse.getCarvedoutMap().isEmpty()){
					getSession().setAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST,saveContractBasicInfoResponse.getCarvedoutMap());
					this.setVendorFlag("true");	
				}//SSCR 17571 - Tab Impl
				if(saveContractBasicInfoResponse.getCondition() == SaveProductResponse.DO_EBX_WEBSERVICE_PROCESS ){
					  List<ContractWebServiceVO> contractWSErrorList = null;
					  List<EbxWebSerDisplayVO> wSErrorDisplayList =null;
					
					if(saveContractBasicInfoResponse.getContractWSErrorList() != null && !saveContractBasicInfoResponse.getContractWSErrorList().isEmpty()){
						contractWSErrorList =saveContractBasicInfoResponse.getContractWSErrorList();	
					}
					if(saveContractBasicInfoResponse.getWSErrorDisplayList() != null  && !saveContractBasicInfoResponse.getWSErrorDisplayList().isEmpty()){
						wSErrorDisplayList = saveContractBasicInfoResponse.getWSErrorDisplayList();
					}
					if(contractWSErrorList != null && wSErrorDisplayList !=null &&!contractWSErrorList.isEmpty() && !wSErrorDisplayList.isEmpty()){
						this.setWebServiceFlag("true");
						getSession().setAttribute("contractWSErrorList",contractWSErrorList);
						getSession().setAttribute("wSErrorDisplayList",wSErrorDisplayList);
						ContractKeyObject contractKeyObject = getContractSession().getContractKeyObject();
						this.ebxWebSerPopupDisplayVO = DomainUtil.ebxPopupDisplayValues(contractKeyObject);
						getSession().setAttribute("ebxWebSerPopupDisplayVO",ebxWebSerPopupDisplayVO);
						this.setEbxWebSerDisplayList(wSErrorDisplayList);
						
					}
					
				}else if(saveContractBasicInfoResponse.getCondition() == SaveProductResponse.DO_EBX_WEBSERVICE_PROCESS_FAILURE){
					this.setWebServiceFlag("true");
					this.ebxProcessInterruptMsg = saveContractBasicInfoResponse.getWsProcessError();
					getSession().setAttribute("ebxProcessInterruptMsg",ebxProcessInterruptMsg);
					ContractKeyObject contractKeyObject = getContractSession().getContractKeyObject();
					this.ebxWebSerPopupDisplayVO = DomainUtil.ebxPopupDisplayValues(contractKeyObject);
					getSession().setAttribute("ebxWebSerPopupDisplayVO",ebxWebSerPopupDisplayVO);
					
				}
				this.getSession().setAttribute("VendorFlag",vendorFlag);
				this.getSession().setAttribute("WebServiceFlag",webServiceFlag);
				return "displayRules";
			} else {
				if (null != saveContractBasicInfoResponse.getMessages()) {
					// Rule Validation
					getSession()
							.setAttribute(
									WebConstants.SESSION_DELETED_RULES_LIST,
									saveContractBasicInfoResponse
											.getDeletedRulesList());
					getSession()
							.setAttribute(
									WebConstants.SESSION_UNCODED_RULES_LIST,
									saveContractBasicInfoResponse
											.getUnCodedRulesList());
					msgListTwo = saveContractBasicInfoResponse.getMessages();
					msgListOne.addAll(msgListTwo);
					msgListOne.addAll(this.validationMessages);
					addAllMessagesToRequest(msgListOne);
				}
				if (saveContractBasicInfoResponse.isSuccess()) {
					this.ruleHidden = WebConstants.EMPTY_STRING;
					super.clearSession();
					return "CONTRACTCREATE";
				} else if (saveContractBasicInfoResponse
						.isIfUncodedLineNotesExist()) {
					this.setIfUncodedLineNotesExist("Yes");
				}
			}
		}
		this.setValidationMessages(msgListOne);
		if (this.checkIn == false) {
			this.rulesValueCheck = true;
			return "displayRules";
		}
		this.checkIn = false;
		this.rulesValueCheck = true;
		getRequest().setAttribute("RETAIN_Value", "");
		return "displayRules";
	}
	
	public String doContractCancelAction(){ 
		this.setWebServiceFlag("false");
		this.setVendorFlag("false");
    	getSession().removeAttribute("contractWSErrorList");
    	getSession().removeAttribute("wSErrorDisplayList");
    	getSession().removeAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST);
    	getSession().removeAttribute("VendorFlag");
    	getSession().removeAttribute("WebServiceFlag");
    	getSession().removeAttribute("ebxProcessInterruptMsg");
    	return "";
    }
	
	/**
	 * 
	 * @return
	 */

	public String deleteRuleInfo() {

		RetrieveRulesRequest retrieveRulesRequest = (RetrieveRulesRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE__RULES);
		retrieveRulesRequest.setAction(RetrieveRulesRequest.DELETE_RULES);
		retrieveRulesRequest.setContractKeyObject(getContractSession()
				.getContractKeyObject());
		int dsSysId = super.getContractSession().getContractKeyObject()
				.getDateSegmentId();
		if (null != this.selectedRuleSysID
				&& !this.selectedRuleSysID.equals("")) {
			//int rulSysId= Integer.parseInt(this.getSelectedRuleSysID());

			retrieveRulesRequest.setRuleIdList(WPDStringUtil
					.getListFromTildaString(this.getSelectedRuleSysID(), 1, 1,
							1));
			retrieveRulesRequest.setDateSegmentSysId(dsSysId);
			retrieveRulesRequest.setGeneratedRuleIdList(WPDStringUtil
					.getListFromTildaString(this.ewpdGenRuleID, 1, 1, 2));
		}
		RetrieveRulesResponse searchResponse = (RetrieveRulesResponse) executeService(retrieveRulesRequest);
		if (null != searchResponse) {
			// set  save  message
			if (null != searchResponse.getMessages()) {
				this.setValidationMessages(searchResponse.getMessages());
			}
		}
		//this.ruleHidden=WebConstants.EMPTY_STRING;
		selectedRuleSysID = WebConstants.EMPTY_STRING;
		if (this.exclusionRuleSelected) {
			this.loadContractRules(BusinessConstants.RULE_TYPE_EXCLUSION);
		} else if (this.denialRuleSelected) {
			this.loadContractRules(BusinessConstants.RULE_TYPE_DENIAL);
		} else if (this.umRuleSelected) {
			this.loadContractRules(BusinessConstants.RULE_TYPE_UM);
		} else if (this.pnrRuleSelected) {
			this.loadContractRules(BusinessConstants.RULE_TYPE_PNR);
		}
		getRequest().setAttribute("RETAIN_Value", "");
		return "displayRules";
	}
	
	private String getRandomNumber(){
		Random random = new SecureRandom();
		BigInteger bg = new BigInteger(100, random);
		return bg.toString(16);
	}

	/**
	 * 
	 * @param ruleList
	 */
	public void preparePanel(List ruleInterList) {
		final String DELETE_IMAGE_PATH = "../../images/delete.gif";
		final String VIEW_IMAGE_PATH = "../../images/view.gif";
		RuleLocateResult ruleLocateResult = new RuleLocateResult();
		this.panel = new HtmlPanelGrid();
		panel.setWidth("100%");
		if (super.isViewMode()) {
			panel.setColumns(7);
		} else {
			panel.setColumns(8);
		}
		panel.setBorder(0);
		panel.setCellpadding("3");
		panel.setCellspacing("1");
		panel.setBgcolor("#cccccc");
		if (null != ruleInterList && ruleInterList.size() != 0) {
			for (int i = 0; i < ruleInterList.size(); i++) {
				ruleLocateResult = (RuleLocateResult) ruleInterList.get(i);
				HtmlOutputLabel td1 = new HtmlOutputLabel();
				td1.setId("td1"+getRandomNumber());
				HtmlOutputLabel td2 = new HtmlOutputLabel();
				td2.setId("td2"+getRandomNumber());
				HtmlOutputLabel td3 = new HtmlOutputLabel();
				td3.setId("td3"+getRandomNumber());
				HtmlOutputLabel td4 = new HtmlOutputLabel();
				td4.setId("td4"+getRandomNumber());
				HtmlOutputLabel td5 = new HtmlOutputLabel();
				td5.setId("td5"+getRandomNumber());
				HtmlOutputLabel td6 = new HtmlOutputLabel();
				td6.setId("td6"+getRandomNumber());
				HtmlOutputLabel td7 = new HtmlOutputLabel();
				td7.setId("td7"+getRandomNumber());
				//            	create a hidden field 
				HtmlInputHidden ruleKey = new HtmlInputHidden();
				HtmlInputHidden genRuleKey = new HtmlInputHidden();
				HtmlInputHidden ruleId = new HtmlInputHidden();
				HtmlOutputText outputTextColumn1 = new HtmlOutputText();
				HtmlOutputText outputTextColumn2 = new HtmlOutputText();
				HtmlOutputText outputTextColumn3 = new HtmlOutputText();
				HtmlOutputText outputTextColumn4 = new HtmlOutputText();
				HtmlOutputText outputTextColumn5 = new HtmlOutputText();
				HtmlOutputText outputTextColumn6 = new HtmlOutputText();
				HtmlOutputText outputTextColumn7 = new HtmlOutputText();
				outputTextColumn7.setValue(" ");
				HtmlInputText inputTextColumn5 = new HtmlInputText();
				inputTextColumn5.setId("inputTextColumn5"+RandomStringUtils.randomAlphanumeric(15));
				HtmlSelectBooleanCheckbox deleteButton = new HtmlSelectBooleanCheckbox();
				HtmlCommandButton viewButton = new HtmlCommandButton();
				ValueBinding ruleComment = FacesContext.getCurrentInstance()
						.getApplication().createValueBinding(
								"#{contractRuleBackingBean.ruleCommentMap["
										+ ruleLocateResult
												.getProductRuleSysId() + "]}");

				ruleKey.setValue(new Integer(ruleLocateResult
						.getProductRuleSysId()));

				ruleKey.setId("ruleKey" + i);

				genRuleKey.setValue(ruleLocateResult.getGeneratedRuleId());
				genRuleKey.setId("genRuleKey" + i);

				ruleId.setValue(ruleLocateResult.getRuleId());
				ruleId.setId("ruleId" + i);

				this.renderFlagForPanel = true;
				outputTextColumn1.setValue(ruleLocateResult
						.getGeneratedRuleId());
				outputTextColumn2.setValue(ruleLocateResult.getRuleId());
				outputTextColumn3.setValue(ruleLocateResult
						.getRuleShortDescription());//change to descn
				outputTextColumn4.setValue(ruleLocateResult.getRulePVA());
				outputTextColumn5.setValue(ruleLocateResult
						.getRuleGroupIndicator());
				if (null == ruleLocateResult.getRuleOverRideValue()
						|| "".equals(ruleLocateResult.getRuleOverRideValue())) {
					inputTextColumn5.setValue(WebConstants.EMPTY_STRING);
					outputTextColumn6.setValue(WebConstants.EMPTY_STRING);
				} else {
					inputTextColumn5.setValue(new String(ruleLocateResult
							.getRuleOverRideValue()));
					outputTextColumn6.setValue(new String(ruleLocateResult
							.getRuleOverRideValue()));
				}
				inputTextColumn5.setValueBinding("value", ruleComment);
				if (this.denialRuleSelected) {
					inputTextColumn5.setMaxlength(5);
				} else {
					inputTextColumn5.setMaxlength(2);
				}

				inputTextColumn5
						.setStyle("font-family:Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:50px;height:17px;background-color:#F4F4F4;border:solid #7f9db9 1px;color:#1762A5;");

				deleteButton.setId("deleteBtn" + i);

				viewButton.setValue("View");
				viewButton.setId("viewBtn" + i);

				if (super.isViewMode()) {
					deleteButton.setRendered(false);

				} else {
					deleteButton.setRendered(true);
					viewButton.setDisabled(false);
					viewButton.setStyle("cursor: hand");
				}

				//deleteButton.setOnclick("confirmDeletion('ruleKey" +i+"', 'genRuleKey"+i+"');return false;");
				deleteButton.setOnclick("javascript:validateDelete();");
				viewButton.setAlt("View");
				viewButton.setOnclick("viewAction('ruleId" + i
						+ "');return false;");
				viewButton.setImage(VIEW_IMAGE_PATH);
				td1.getChildren().add(ruleKey);
				td1.getChildren().add(genRuleKey);
				td1.getChildren().add(ruleId);
				td1.getChildren().add(outputTextColumn1);
				td2.getChildren().add(outputTextColumn2);
				td3.getChildren().add(outputTextColumn3);
				td4.getChildren().add(outputTextColumn4);
				td5.getChildren().add(outputTextColumn5);
				td7.getChildren().add(viewButton);
				td7.getChildren().add(outputTextColumn7);
				if (super.isViewMode()) {
					td6.getChildren().add(outputTextColumn6);
				}
				//        		}else{
				//        			td7.getChildren().add(deleteButton);
				//        		}
				panel.getChildren().add(td1);
				panel.getChildren().add(td2);
				panel.getChildren().add(td3);
				panel.getChildren().add(td4);
				panel.getChildren().add(td5);

				if (super.isViewMode()) {
					panel.getChildren().add(td6);
					//panel.getChildren().add(viewButton);

				} else {
					panel.getChildren().add(inputTextColumn5);
					panel.getChildren().add(deleteButton);
					//        		    panel.getChildren().add(viewButton);
					//        		    panel.getChildren().add(deleteButton);
				}
				panel.getChildren().add(td7);
			}//for end
		} else {
			panel.setRendered(false);
		}
	}

	/**
	 * @return Returns the headingPanel.
	 */
	public HtmlPanelGrid getHeadingPanel() {

		this.headingPanel = new HtmlPanelGrid();
		headingPanel.setColumns(6);
		headingPanel.setBorder(0);
		headingPanel.setStyleClass("outputText");
		headingPanel.setCellpadding("3");
		headingPanel.setCellspacing("1");
		headingPanel.setBgcolor("#cccccc");
		HtmlOutputText outputTextColumn1 = new HtmlOutputText();
		HtmlOutputText outputTextColumn2 = new HtmlOutputText();
		HtmlOutputText outputTextColumn3 = new HtmlOutputText();
		HtmlOutputText outputTextColumn4 = new HtmlOutputText();
		HtmlOutputText outputTextColumn5 = new HtmlOutputText();
		HtmlOutputText outputTextColumn6 = new HtmlOutputText();
		outputTextColumn1.setValue("RuleGeneratedId");
		outputTextColumn2.setValue("RuleId");
		outputTextColumn3.setValue("Rule Description");
		outputTextColumn4.setValue("RuleType");
		outputTextColumn5.setValue("Overrid Value");
		outputTextColumn6.setValue(WebConstants.EMPTY_STRING);
		headingPanel.getChildren().add(outputTextColumn1);
		headingPanel.getChildren().add(outputTextColumn2);
		headingPanel.getChildren().add(outputTextColumn3);
		headingPanel.getChildren().add(outputTextColumn4);
		headingPanel.getChildren().add(outputTextColumn5);
		headingPanel.getChildren().add(outputTextColumn6);
		return headingPanel;
	}

	/**
	 * 
	 * @param ruleList
	 */
	public void preparePanelForRuleSequence(List ruleSequenceLists) {

		RuleDisplayBO ruleDisplayBO = RuleUtil.createRuleDisplayObj(ruleSequenceLists);

		this.panelRuleSequences = new HtmlPanelGrid();
		panelRuleSequences.setWidth("100%");

		panelRuleSequences.setColumns(4);
		

		panelRuleSequences.setBorder(0);
		//panelRuleSequences.setCellpadding("3");
		//panelRuleSequences.setCellspacing("1");
		
		try{
		if (null != ruleDisplayBO) {
			this.ruleId = ruleDisplayBO.getRuleId();

			if (null != ruleDisplayBO.getRuleShortDesc()
					&& !"".equals(ruleDisplayBO.getRuleShortDesc())) {
				this.strRmaLink = getLink();
				this.ruleDescription = ruleDisplayBO.getRuleShortDesc();
			}

			
			if(null != ruleDisplayBO.getTag() && !"".equals(ruleDisplayBO.getTag())){
				this.tag=ruleDisplayBO.getTag();
			}
			
			
			
			if(null != ruleDisplayBO.getRuleVersion() && !"".equals(ruleDisplayBO.getRuleVersion())){
				this.ruleVersion=ruleDisplayBO.getRuleVersion();
			}

			ruleSequenceMap = ruleDisplayBO.getRuleSequenceMap();
			if(null != ruleSequenceMap){
			Set ruleSequenceMapKeySet = ruleSequenceMap.keySet();
			Iterator ruleSequenceMapKeySetIterator = ruleSequenceMapKeySet.iterator();
			
			while(ruleSequenceMapKeySetIterator.hasNext()){
				panelRuleSequences.setCellspacing("15");
				Object ruleSequenceIdKey = ruleSequenceMapKeySetIterator.next();                
				String ruleSequenceNumber = ruleSequenceIdKey.toString();
				ruleSequenceBO = ruleSequenceMap.get(ruleSequenceIdKey);
			
				HtmlPanelGroup group2 = new HtmlPanelGroup();
				HtmlOutputText outputTextColumn2 = new HtmlOutputText();
				
				if (0 != ruleSequenceBO.getRuleSequenceNumber()) {
					outputTextColumn2 = new HtmlOutputText();
					outputTextColumn2
							.setValue("Rule Sequence Number        : "
									+ ruleSequenceBO.getRuleSequenceNumber());
					outputTextColumn2.setStyle("font-weight:bold;white-space:nowrap;");
					group2.getChildren().add(outputTextColumn2);
					panelRuleSequences.getChildren().add(group2);

					outputTextColumn2 = new HtmlOutputText();
					outputTextColumn2.setValue("");
					group2 = new HtmlPanelGroup();
					group2.getChildren().add(outputTextColumn2);
					panelRuleSequences.getChildren().add(group2);

					outputTextColumn2 = new HtmlOutputText();
					outputTextColumn2.setValue("");
					group2 = new HtmlPanelGroup();
					group2.getChildren().add(outputTextColumn2);
					panelRuleSequences.getChildren().add(group2);
					
					outputTextColumn2 = new HtmlOutputText();
					outputTextColumn2.setValue("");
					group2 = new HtmlPanelGroup();
					group2.getChildren().add(outputTextColumn2);
					panelRuleSequences.getChildren().add(group2);
					
				}

				HtmlPanelGroup ruleSeqGroup = new HtmlPanelGroup();
				HtmlOutputText outputTextColumn1 = new HtmlOutputText();
				int count = 0;
				if (null != ruleSequenceBO.getExclsnInd()
						&& !"".equals(ruleSequenceBO.getExclsnInd())) {
					outputTextColumn1.setValue("Exclusion Indicator"
							+ "                          : "
							+ ruleSequenceBO.getExclsnInd());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getClmType()
						&& !"".equals(ruleSequenceBO.getClmType())) {
					outputTextColumn1
							.setValue("Claim Type                     : "
									+ ruleSequenceBO.getClmType());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getPlaceOfService()
						&& !"".equals(ruleSequenceBO.getPlaceOfService())) {
					outputTextColumn1
							.setValue("Place of Service Code          : "
									+ ruleSequenceBO.getPlaceOfService());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (0 != ruleSequenceBO.getPatLowAge()) {
					outputTextColumn1
							.setValue("Patient Low Age                : "
									+ new Integer(ruleSequenceBO
											.getPatLowAge()));
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (0 != ruleSequenceBO.getPatHighAge()) {
					outputTextColumn1
							.setValue("Patient High Age               : "
									+ new Integer(ruleSequenceBO
											.getPatHighAge()));
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getGenderCode()
						&& !"".equals(ruleSequenceBO.getGenderCode())) {
					outputTextColumn1
							.setValue("Gender Code                    : "
									+ ruleSequenceBO.getGenderCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getProviderId()
						&& !"".equals(ruleSequenceBO.getProviderId())) {
					outputTextColumn1
							.setValue("Provider ID                     : "
									+ ruleSequenceBO.getProviderId());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getProviderSpecialityCode()
						&& !"".equals(ruleSequenceBO
								.getProviderSpecialityCode())) {
					outputTextColumn1
							.setValue("Provider Speciality Code         : "
									+ ruleSequenceBO
											.getProviderSpecialityCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getBenefitAccmNum()
						&& !"".equals(ruleSequenceBO.getBenefitAccmNum())) {
					outputTextColumn1
							.setValue("Benefit Accumulator Name        : "
									+ ruleSequenceBO.getBenefitAccmNum());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if(null != ruleSequenceBO.getBenfitAccumLimtAmnt()){
				if (Float.parseFloat(ruleSequenceBO.getBenfitAccumLimtAmnt())>0) {
					outputTextColumn1
							.setValue("Benefit Accumulator Limit Amount : "
									+ ruleSequenceBO
											.getBenfitAccumLimtAmnt());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				}
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (0 != ruleSequenceBO.getBenefitAccumLimtNum()) {
					outputTextColumn1
							.setValue("Benefit Accumulator Limit Occurrence Number : "
									+ new Integer(ruleSequenceBO
											.getBenefitAccumLimtNum()));
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getNtfyOnlyInd()
						&& !"".equals(ruleSequenceBO.getNtfyOnlyInd())) {
					outputTextColumn1
							.setValue("Notify Only Indicator                       : "
									+ ruleSequenceBO.getNtfyOnlyInd());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getClnlRevwInd()
						&& !"".equals(ruleSequenceBO.getClnlRevwInd())) {
					outputTextColumn1
							.setValue("Clinical Review Indicator                    : "
									+ ruleSequenceBO.getClnlRevwInd());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if(null != ruleSequenceBO.getDlrLimit()){
				if (Float.parseFloat(ruleSequenceBO.getDlrLimit())>0) {
					outputTextColumn1
							.setValue("Dollar Limit                                 : "
									+ ruleSequenceBO
											.getDlrLimit());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				}
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getServiceCode()
						&& !"".equals(ruleSequenceBO.getServiceCode())) {
					outputTextColumn1
							.setValue("Service Code                                 : "
									+ ruleSequenceBO.getServiceCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getGrpSt()
						&& !"".equals(ruleSequenceBO.getGrpSt())) {
					outputTextColumn1
							.setValue("Group State                                  : "
									+ ruleSequenceBO.getGrpSt());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (0 != ruleSequenceBO.getLenOfStay()) {
					outputTextColumn1
							.setValue("Length Of stay                               : "
									+ new Integer(ruleSequenceBO
											.getLenOfStay()));
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getItsSpecialityCode()
						&& !"".equals(ruleSequenceBO
								.getItsSpecialityCode())) {
					outputTextColumn1.setValue("ITS specialty Code      : "
							+ ruleSequenceBO.getItsSpecialityCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				int flag1 = 0;
				int flag2 = 0;
				if (null != ruleSequenceBO.getServcStartDate()
						&& !"".equals(ruleSequenceBO.getServcStartDate())) {
					SimpleDateFormat sdfOne = new SimpleDateFormat("MM/dd/yyyy");
					String strOne = sdfOne.format(ruleSequenceBO
							.getServcStartDate());
					if (strOne.equals("01/01/0001")) {
						flag1 = 1;
					}

				}
				if (null != ruleSequenceBO.getServcEndDate()
						&& !"".equals(ruleSequenceBO.getServcEndDate())) {
					SimpleDateFormat sdfTwo = new SimpleDateFormat("MM/dd/yyyy");
					String strTwo = sdfTwo.format(ruleSequenceBO
							.getServcEndDate());
					if (strTwo.equals("01/01/0001")) {
						flag2 = 1;
					}
				}
				if (!(flag1 == 1)) {
					ruleSeqGroup = new HtmlPanelGroup();
					outputTextColumn1 = new HtmlOutputText();
					if (null != ruleSequenceBO.getServcStartDate()
							&& !"".equals(ruleSequenceBO
									.getServcStartDate())) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"MM/dd/yyyy");
						String str = sdf.format(ruleSequenceBO
								.getServcStartDate());
						outputTextColumn1
								.setValue("Service Start Date                            : "
										+ str);
						ruleSeqGroup.getChildren().add(outputTextColumn1);
						panelRuleSequences.getChildren().add(ruleSeqGroup);
						count++;
					}
				}

				if (!(flag2 == 1)) {
					ruleSeqGroup = new HtmlPanelGroup();
					outputTextColumn1 = new HtmlOutputText();
					if (null != ruleSequenceBO.getServcEndDate()
							&& !""
									.equals(ruleSequenceBO
											.getServcEndDate())) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"MM/dd/yyyy");
						String str = sdf.format(ruleSequenceBO
								.getServcEndDate());
						outputTextColumn1
								.setValue("Service End Date                               : "
										+ str);
						ruleSeqGroup.getChildren().add(outputTextColumn1);
						panelRuleSequences.getChildren().add(ruleSeqGroup);
						count++;
					}
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getMembrRelnCode()
						&& !"".equals(ruleSequenceBO.getMembrRelnCode())) {
					outputTextColumn1
							.setValue("Member Relashionship Code     : "
									+ ruleSequenceBO.getMembrRelnCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getPrecedrModifierCode()
						&& !"".equals(ruleSequenceBO
								.getPrecedrModifierCode())) {
					outputTextColumn1.setValue("Procedure Modifier Code : "
							+ ruleSequenceBO.getPrecedrModifierCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getProcedureModifierSecondaryCode()
						&& !"".equals(ruleSequenceBO
								.getProcedureModifierSecondaryCode())) {
					outputTextColumn1.setValue("Procedure Modifier Code2 : "
							+ ruleSequenceBO.getProcedureModifierSecondaryCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getEditCode1()
						&& !"".equals(ruleSequenceBO.getEditCode1())) {
					outputTextColumn1.setValue("Edit Code1      : "
							+ ruleSequenceBO.getEditCode1());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getEditCode2()
						&& !"".equals(ruleSequenceBO.getEditCode2())) {
					outputTextColumn1.setValue("Edit Code2      : "
							+ ruleSequenceBO.getEditCode2());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getProviderStCode()
						&& !"".equals(ruleSequenceBO.getProviderStCode())) {
					outputTextColumn1.setValue("Provider State Code : "
							+ ruleSequenceBO.getProviderStCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getBillTypeCode()
						&& !"".equals(ruleSequenceBO.getBillTypeCode())) {
					outputTextColumn1.setValue("Bill Type Code : "
							+ ruleSequenceBO.getBillTypeCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getTtCode()
						&& !"".equals(ruleSequenceBO.getTtCode())) {
					outputTextColumn1.setValue("Treatment Type Code        : "
							+ ruleSequenceBO.getTtCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getAttachmentInd()
						&& !"".equals(ruleSequenceBO.getAttachmentInd())) {
					outputTextColumn1.setValue("Attachment Indicator        : "
							+ ruleSequenceBO.getAttachmentInd());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getPatMembrCode()
						&& !"".equals(ruleSequenceBO.getPatMembrCode())) {
					outputTextColumn1.setValue("Patient Member Code     : "
							+ ruleSequenceBO.getPatMembrCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getHosptlFacilCode()
						&& !"".equals(ruleSequenceBO.getHosptlFacilCode())) {
					outputTextColumn1.setValue("Hospital Facility Code    : "
							+ ruleSequenceBO.getHosptlFacilCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (0 != ruleSequenceBO.getDaysFrmInjury()) {
					outputTextColumn1.setValue("Days From Injury   : "
							+ new Integer(ruleSequenceBO
									.getDaysFrmInjury()));
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (0 != ruleSequenceBO.getDaysFrmIllness()) {
					outputTextColumn1.setValue("Days From Illness   : "
							+ new Integer(ruleSequenceBO
									.getDaysFrmIllness()));
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getHmoClsCode()
						&& !"".equals(ruleSequenceBO.getHmoClsCode())) {
					outputTextColumn1.setValue("Class Code     : "
							+ ruleSequenceBO.getHmoClsCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				   
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getTotalChargeSign()
						&& !"".equals(ruleSequenceBO.getTotalChargeSign())) {
					outputTextColumn1.setValue("Total Charge Sign   : "
							+ ruleSequenceBO.getTotalChargeSign());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if(null != ruleSequenceBO.getTotalChargeAmnt()){
				if (Float.parseFloat(ruleSequenceBO.getTotalChargeAmnt())>0) {
					outputTextColumn1.setValue("Total Charged Amount   : "
							+ ruleSequenceBO
									.getTotalChargeAmnt());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				}
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getClmLevelDataInd()
						&& !"".equals(ruleSequenceBO.getClmLevelDataInd())) {
					outputTextColumn1.setValue("Claim Level Data Ind   : "
							+ ruleSequenceBO.getClmLevelDataInd());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getCopayIndicator()
						&& !"".equals(ruleSequenceBO.getCopayIndicator())) {
					outputTextColumn1.setValue("Copay Indicator  : "
							+ ruleSequenceBO.getCopayIndicator());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getHunPerIndicator()
						&& !"".equals(ruleSequenceBO.getHunPerIndicator())) {
					outputTextColumn1.setValue("100% Indicator  : "
							+ ruleSequenceBO.getHunPerIndicator());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getMedicareAssgnIndicator()
						&& !"".equals(ruleSequenceBO
								.getMedicareAssgnIndicator())) {
					outputTextColumn1
							.setValue("Medicare Assignment Indicator  : "
									+ ruleSequenceBO
											.getMedicareAssgnIndicator());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				//DESCRIPTION : CR 59 changes,Including health claim processing system indicator both in rule and claim screen
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getSupportHcpIndicator()
						&& !"".equals(ruleSequenceBO
								.getSupportHcpIndicator())) {
					outputTextColumn1
							.setValue("Support HCPS indicator		 	: "
									+ ruleSequenceBO
											.getSupportHcpIndicator());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getClaimSupportHcpIndicator()
						&& !"".equals(ruleSequenceBO
								.getClaimSupportHcpIndicator())) {
					outputTextColumn1
							.setValue("Claim Support HCPS indicator	    : "
									+ ruleSequenceBO
											.getClaimSupportHcpIndicator());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}				
				//CARS END
				
				//Two fields below are added for rule change (diagnosis indicator)
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getDiagnosisIndicator() 
						&& !"".equals(ruleSequenceBO.getDiagnosisIndicator()))
				{
					outputTextColumn1.setValue("Diagnosis CD Ind 	    : "
							+ ruleSequenceBO.getDiagnosisIndicator());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getPcpIndicator() 
						&& !"".equals(ruleSequenceBO.getPcpIndicator()))
				{
					outputTextColumn1.setValue("Pcp Indicator 	    : "
							+ ruleSequenceBO.getPcpIndicator());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getProvLncsId() && !"".equalsIgnoreCase(ruleSequenceBO.getProvLncsId()))
				{
					outputTextColumn1.setValue("Provider License Id  : "
							+ ruleSequenceBO.getProvLncsId());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getProvMedcrId() && !"".equalsIgnoreCase(ruleSequenceBO.getProvMedcrId()))
				{
					outputTextColumn1.setValue("Provider Medicare Id  : "
							+ ruleSequenceBO.getProvMedcrId());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getBillgProvNbr()&& !"".equalsIgnoreCase(ruleSequenceBO.getBillgProvNbr()))
				{
					outputTextColumn1.setValue("Billing Provider Number  : "
							+ ruleSequenceBO.getBillgProvNbr());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getRndrgProvNbr()&& !"".equalsIgnoreCase(ruleSequenceBO.getRndrgProvNbr()))
				{
					outputTextColumn1.setValue("Rendering Provider Number  : "
							+ ruleSequenceBO.getRndrgProvNbr());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getBillgNpiNbr()&& !"".equalsIgnoreCase(ruleSequenceBO.getBillgNpiNbr()))
				{
					outputTextColumn1.setValue("Billing NPI Number  : "
							+ ruleSequenceBO.getBillgNpiNbr());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getRndrgNpiNbr()&& !"".equalsIgnoreCase(ruleSequenceBO.getRndrgNpiNbr()))
				{
					outputTextColumn1.setValue("Rendering NPI Number  : "
							+ ruleSequenceBO.getRndrgNpiNbr());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getElgblExpansSignCode() 
						&& !"".equals(ruleSequenceBO.getElgblExpansSignCode()))
				{
					outputTextColumn1.setValue("Eligible Expns Sign Code  : "
							+ ruleSequenceBO.getElgblExpansSignCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if(null != ruleSequenceBO.getElgblExpansAmt()){
				if (Float.parseFloat(ruleSequenceBO.getElgblExpansAmt())>0)
				{
					outputTextColumn1.setValue("Eligible Expns Amt  : "
							+ ruleSequenceBO.getElgblExpansAmt());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				}
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getDrugCode() 
						&& !"".equals(ruleSequenceBO.getDrugCode()))
				{
					outputTextColumn1.setValue("Drug Code 	    : "
							+ ruleSequenceBO.getDrugCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}

				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getProviderSpecialityIndicator() 
						&& !"".equals(ruleSequenceBO.getProviderSpecialityIndicator()))
				{
					outputTextColumn1.setValue("Provider Speciality Indicator	    : "
							+ ruleSequenceBO.getProviderSpecialityIndicator());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				
				ruleSeqGroup = new HtmlPanelGroup();
				outputTextColumn1 = new HtmlOutputText();
				if (null != ruleSequenceBO.getAgeTypCode() 
						&& !"".equals(ruleSequenceBO.getAgeTypCode()))
				{
					outputTextColumn1.setValue("Age Type Code   : "
							+ ruleSequenceBO.getAgeTypCode());
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
					count++;
				}
				

				int mod = count % 4;
				for (int ij = 0; ij < (4 - mod); ij++) {
					ruleSeqGroup = new HtmlPanelGroup();
					outputTextColumn1 = new HtmlOutputText();
					outputTextColumn1.setValue("");
					ruleSeqGroup.getChildren().add(outputTextColumn1);
					panelRuleSequences.getChildren().add(ruleSeqGroup);
				}
				
				/*	TODO			
				private String wpdDelFlag;
				private String clmDrgCd;
				private String procedureModifierSecondaryCode;
				*/
				
				ruleCodeSequenceMap = ruleSequenceBO.getRuleCodeSequenceMap();
				Set ruleCodeSequenceMapKeySet = ruleCodeSequenceMap.keySet();
				Iterator ruleCodeSequenceMapKeySetIterator = ruleCodeSequenceMapKeySet.iterator();
				HtmlPanelGroup ruleCodeSequenceGroup;
				HtmlOutputText outputTextColumn3;
				HtmlColumn column = new HtmlColumn();
				boolean cdSeqFlag= false;
				
				
				int countRuleCode = 0;
				while(ruleCodeSequenceMapKeySetIterator.hasNext()){
					panelRuleSequences.setCellspacing("3");
					countRuleCode = 0;
					Object ruleCodeSequenceIdKey = ruleCodeSequenceMapKeySetIterator.next();                
					String ruleCodeSequenceNumber = ruleCodeSequenceIdKey.toString();
					ruleCodeSequenceBO = ruleCodeSequenceMap.get(ruleCodeSequenceIdKey);				
					
					if((ruleCodeSequenceBO.getCdSqncNbr()!=0) && (cdSeqFlag == false) ){
						ruleCodeSequenceGroup = new HtmlPanelGroup();
						
						outputTextColumn3 = new HtmlOutputText();
						outputTextColumn3.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
						outputTextColumn3.setStyle("visibility:hidden; ");
						ruleCodeSequenceGroup = new HtmlPanelGroup();
						ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);
						panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
						
						outputTextColumn3 = new HtmlOutputText();
						HtmlInputHidden  outputTextColumnsapce = new HtmlInputHidden();
						outputTextColumnsapce.setId("outputTextColumnsapce"+ RandomStringUtils.randomAlphanumeric(15));
						outputTextColumn3
								.setValue(" Code Sequences  : ");
						outputTextColumn3.setStyle("font-weight:bold;");
						
						ruleCodeSequenceGroup.getChildren().add(outputTextColumnsapce);
						ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);				
						panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);

						outputTextColumn3 = new HtmlOutputText();
						outputTextColumn3.setValue("");
						ruleCodeSequenceGroup = new HtmlPanelGroup();
						ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);
						panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);

						outputTextColumn3 = new HtmlOutputText();
						outputTextColumn3.setValue("");
						ruleCodeSequenceGroup = new HtmlPanelGroup();
						ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);
						panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
						
						outputTextColumn3 = new HtmlOutputText();
						outputTextColumn3.setValue("");
						ruleCodeSequenceGroup = new HtmlPanelGroup();
						ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);
						panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);	
						
						cdSeqFlag=true;
									
						}
					
					ruleCodeSequenceGroup = new HtmlPanelGroup();
					outputTextColumn3 = new HtmlOutputText();
					if (null != ruleCodeSequenceBO.getLineHcptLowVal() && !"".equals(ruleCodeSequenceBO.getLineHcptLowVal()) && 
							null != ruleCodeSequenceBO.getLineHcptHighVal() && !"".equals(ruleCodeSequenceBO.getLineHcptHighVal()))
					{
						if(countRuleCode ==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleCodeSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							
							HtmlOutputText outputTextColumnsn = new HtmlOutputText();
							outputTextColumnsn.setValue(ruleCodeSequenceBO.getCdSqncNbr()+"  ");
							outputTextColumnsn.setStyle("font-weight:bold;white-space:nowrap;");					
							ruleCodeSequenceGroup.getChildren().add(outputTextColumnsn);
							panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
						}
						
						outputTextColumn3.setValue("HCP   : "+ ruleCodeSequenceBO.getLineHcptLowVal()
								+" - "+ruleCodeSequenceBO.getLineHcptHighVal());
						outputTextColumn3.setStyle("text-align:center;white-space:nowrap;");
						ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);
						panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
						countRuleCode++;
					}
					
					//RMA ICD-10 : this section will display DIA & ICD ranges and its version
					List codeList = ruleCodeSequenceBO.getCodeList();
					if(null != codeList){
					for (int j = 0; j < codeList.size(); j++) {
						RuleCodeRanges ruleCodeRanges = (RuleCodeRanges) codeList
								.get(j);

						String type = ruleCodeRanges.getCodeType();
						String low = ruleCodeRanges.getCodeTypeLowVal();
						String high = ruleCodeRanges.getCodeTypeHighVal();

						ruleCodeSequenceGroup = new HtmlPanelGroup();
						outputTextColumn3 = new HtmlOutputText();
						if ((null != ruleCodeRanges.getCodeType() //&& !ruleCodeRanges.getCodeType().equalsIgnoreCase("DIA")
								) && !"".equals(ruleCodeRanges.getCodeType())) {
							
							if(countRuleCode ==0){
								HtmlOutputText outputTextColumnsp = new HtmlOutputText();
								outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
								outputTextColumnsp.setStyle("visibility:hidden;");						
								ruleCodeSequenceGroup.getChildren().add(outputTextColumnsp);
								panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
								
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleCodeSequenceBO.getCdSqncNbr()+"  ");
								outputTextColumnsn.setStyle("font-weight:bold;");					
								ruleCodeSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							}
							
							outputTextColumn3.setValue(ruleCodeRanges.getCodeType()
									+ " : " + ruleCodeRanges.getCodeTypeLowVal()
									+ "-" + ruleCodeRanges.getCodeTypeHighVal()
									+ "(" + ruleCodeRanges.getIcdVersionIndicator()+ ")");
							outputTextColumn3.setStyle("text-align:center;white-space:nowrap;");
							ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);
							panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							countRuleCode++;
						}

					}
				}
													
					ruleCodeSequenceGroup = new HtmlPanelGroup();
					outputTextColumn3 = new HtmlOutputText();
					if (null != ruleCodeSequenceBO.getLineRevLowVal() && !"".equals(ruleCodeSequenceBO.getLineRevLowVal()) && 
							null != ruleCodeSequenceBO.getLineRevHighVal() && !"".equals(ruleCodeSequenceBO.getLineRevHighVal()))
					{
						if(countRuleCode %4==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleCodeSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							
							if(countRuleCode ==0){
																
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleCodeSequenceBO.getCdSqncNbr()+"  ");
								outputTextColumnsn.setStyle("font-weight:bold;white-space:nowrap;");					
								ruleCodeSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							}
							
						}
						
						outputTextColumn3.setValue("REV   : "+ ruleCodeSequenceBO.getLineRevLowVal()
								+" - "+ruleCodeSequenceBO.getLineRevHighVal());
						outputTextColumn3.setStyle("text-align:center;");
						ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);
						panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
						countRuleCode++;
					}
					
					ruleCodeSequenceGroup = new HtmlPanelGroup();
					outputTextColumn3 = new HtmlOutputText();
					if (null != ruleCodeSequenceBO.getLineSrvcClsLow() && !"".equals(ruleCodeSequenceBO.getLineSrvcClsLow()) && 
							null != ruleCodeSequenceBO.getLineSrvcClsHigh() && !"".equals(ruleCodeSequenceBO.getLineSrvcClsHigh()))
					{
						if(countRuleCode %4==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleCodeSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							
							if(countRuleCode ==0){
								
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleCodeSequenceBO.getCdSqncNbr()+"  ");
								outputTextColumnsn.setStyle("font-weight:bold;");					
								ruleCodeSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							}
						}
						
						outputTextColumn3.setValue("Service Class   : "+ ruleCodeSequenceBO.getLineSrvcClsLow()
								+" - "+ruleCodeSequenceBO.getLineSrvcClsHigh());
						outputTextColumn3.setStyle("text-align:center;white-space:nowrap;");
						ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);
						panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
						countRuleCode++;
					}

					ruleCodeSequenceGroup = new HtmlPanelGroup();
					outputTextColumn3 = new HtmlOutputText();
					if (null != ruleCodeSequenceBO.getLineLmtClsLow() && !"".equals(ruleCodeSequenceBO.getLineLmtClsLow()) && 
							null != ruleCodeSequenceBO.getLineLmtClsHigh() && !"".equals(ruleCodeSequenceBO.getLineLmtClsHigh()))
					{
						if(countRuleCode %4==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleCodeSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							
							if(countRuleCode ==0){
								
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleCodeSequenceBO.getCdSqncNbr()+"  ");
								outputTextColumnsn.setStyle("font-weight:bold;");					
								ruleCodeSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							}
						}
						
						outputTextColumn3.setValue("Limit Class   : "+ ruleCodeSequenceBO.getLineLmtClsLow()
								+" - "+ruleCodeSequenceBO.getLineLmtClsHigh());
						outputTextColumn3.setStyle("text-align:center;white-space:nowrap;");
						ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);
						panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
						countRuleCode++;
					}
					

					ruleCodeSequenceGroup = new HtmlPanelGroup();
					outputTextColumn3 = new HtmlOutputText();
					if (null != ruleCodeSequenceBO.getLineIcdpCategoryCode() && !"".equals(ruleCodeSequenceBO.getLineIcdpCategoryCode()))						
					{
						if(countRuleCode %4==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleCodeSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							
							if(countRuleCode ==0){
								
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleCodeSequenceBO.getCdSqncNbr()+"  ");
								outputTextColumnsn.setStyle("font-weight:bold;");					
								ruleCodeSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							}
						}
						
						outputTextColumn3.setValue("Icd Category Code   : "+ ruleCodeSequenceBO.getLineIcdpCategoryCode());
						outputTextColumn3.setStyle("text-align:center;white-space:nowrap;");
						ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);
						panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
						countRuleCode++;
					}

					ruleCodeSequenceGroup = new HtmlPanelGroup();
					outputTextColumn3 = new HtmlOutputText();
					if (null != ruleCodeSequenceBO.getLineIcdpClsfctnId() && !"".equals(ruleCodeSequenceBO.getLineIcdpClsfctnId()))						
					{
						if(countRuleCode %4==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleCodeSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							
							if(countRuleCode ==0){
								
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleCodeSequenceBO.getCdSqncNbr()+"  ");
								outputTextColumnsn.setStyle("font-weight:bold;");					
								ruleCodeSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							}
						}
						
						outputTextColumn3.setValue("Icd Classification Id   : "+ ruleCodeSequenceBO.getLineIcdpClsfctnId());
						outputTextColumn3.setStyle("text-align:center;white-space:nowrap;");
						ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);
						panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
						countRuleCode++;
					}

					ruleCodeSequenceGroup = new HtmlPanelGroup();
					outputTextColumn3 = new HtmlOutputText();
					if (null != ruleCodeSequenceBO.getLineDiagCategoryCode() && !"".equals(ruleCodeSequenceBO.getLineDiagCategoryCode()))						
					{
						if(countRuleCode %4==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleCodeSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							
							if(countRuleCode ==0){
								
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleCodeSequenceBO.getCdSqncNbr()+"  ");
								outputTextColumnsn.setStyle("font-weight:bold;");					
								ruleCodeSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							}
						}
						
						outputTextColumn3.setValue("Diag Category Code   : "+ ruleCodeSequenceBO.getLineDiagCategoryCode());
						outputTextColumn3.setStyle("text-align:center;white-space:nowrap;");
						ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);
						panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
						countRuleCode++;
					}


					ruleCodeSequenceGroup = new HtmlPanelGroup();
					outputTextColumn3 = new HtmlOutputText();
					if (null != ruleCodeSequenceBO.getLineDiagClsfctnId() && !"".equals(ruleCodeSequenceBO.getLineDiagClsfctnId()))						
					{
						if(countRuleCode %4==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleCodeSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							
							if(countRuleCode ==0){
								
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleCodeSequenceBO.getCdSqncNbr()+"  ");
								outputTextColumnsn.setStyle("font-weight:bold;");					
								ruleCodeSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
							}
						}
						
						outputTextColumn3.setValue("Diag Classification Id  : "+ ruleCodeSequenceBO.getLineDiagClsfctnId());
						outputTextColumn3.setStyle("text-align:center;white-space:nowrap;");
						ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);
						panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
						countRuleCode++;
					}

		
		
					int mod0 = countRuleCode % 4;
					for (int ij = 0; ij < (4 - mod0); ij++) {
						ruleCodeSequenceGroup = new HtmlPanelGroup();
						outputTextColumn3 = new HtmlOutputText();
						outputTextColumn3.setValue("");
						ruleCodeSequenceGroup.getChildren().add(outputTextColumn3);
						panelRuleSequences.getChildren().add(ruleCodeSequenceGroup);
					}
					
					
					
				}//ruleCodeSequenceBO
				
				panelRuleSequences.setCellspacing("8");
				ruleClaimSequenceMap = ruleSequenceBO.getRuleClaimSequenceMap();
				Set ruleClaimSequenceMapKeySet = ruleClaimSequenceMap.keySet();
				Iterator ruleClaimSequenceMapKeySetIterator = ruleClaimSequenceMapKeySet.iterator();
				HtmlPanelGroup ruleClaimSequenceGroup;
				HtmlOutputText outputTextColumn4;
				 boolean claimLevelFlag= false;
				
				
				int countRuleClaim = 0;
				while(ruleClaimSequenceMapKeySetIterator.hasNext()){
					panelRuleSequences.setCellspacing("3");
					countRuleClaim = 0;
					Object ruleClaimSequenceIdKey = ruleClaimSequenceMapKeySetIterator.next();                
					String ruleClaimSequenceNumber = ruleClaimSequenceIdKey.toString();
					ruleClaimSequenceBO = ruleClaimSequenceMap.get(ruleClaimSequenceIdKey);
								
					if((ruleClaimSequenceBO.getClmSqncNbr() != 0) && (claimLevelFlag == false)){
						ruleClaimSequenceGroup = new HtmlPanelGroup();
						
						HtmlOutputText outputTextColumnsp = new HtmlOutputText();
						outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
						outputTextColumnsp.setStyle("visibility:hidden;");						
						ruleClaimSequenceGroup.getChildren().add(outputTextColumnsp);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
						
						outputTextColumn4 = new HtmlOutputText();
						
						outputTextColumn4
								.setValue("Claim Level Sequences: ");
						outputTextColumn4.setStyle("font-weight:bold;white-space:nowrap;");
						ruleClaimSequenceGroup.getChildren().add(outputTextColumn4);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);

						outputTextColumn4 = new HtmlOutputText();
						outputTextColumn4.setValue("");
						ruleClaimSequenceGroup = new HtmlPanelGroup();
						ruleClaimSequenceGroup.getChildren().add(outputTextColumn4);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);

						outputTextColumn4 = new HtmlOutputText();
						outputTextColumn4.setValue("");
						ruleClaimSequenceGroup = new HtmlPanelGroup();
						ruleClaimSequenceGroup.getChildren().add(outputTextColumn4);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
						
						outputTextColumn4 = new HtmlOutputText();
						outputTextColumn4.setValue("");
						ruleClaimSequenceGroup = new HtmlPanelGroup();
						ruleClaimSequenceGroup.getChildren().add(outputTextColumn4);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);		
						
						claimLevelFlag=true;
						
					}
					
					ruleClaimSequenceGroup = new HtmlPanelGroup();
					outputTextColumn4 = new HtmlOutputText();
					if (null != ruleClaimSequenceBO.getClmServiceCode() 
							&& !"".equals(ruleClaimSequenceBO.getClmServiceCode()))
					{
						
						if(countRuleClaim ==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleClaimSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
							
							HtmlOutputText outputTextColumnsn = new HtmlOutputText();
							outputTextColumnsn.setValue(ruleClaimSequenceBO.getClmSqncNbr()+"  ");
							outputTextColumnsn.setStyle("text-align:center;font-weight:bold;");				
							ruleClaimSequenceGroup.getChildren().add(outputTextColumnsn);
							panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
							
							
						}
						
						outputTextColumn4.setValue("Claim Service Code   : "+ ruleClaimSequenceBO.getClmServiceCode());
						outputTextColumn4.setStyle("text-align:center;white-space:nowrap;");
						ruleClaimSequenceGroup.getChildren().add(outputTextColumn4);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
						countRuleClaim++;
					}
					
					ruleClaimSequenceGroup = new HtmlPanelGroup();
					outputTextColumn4 = new HtmlOutputText();
					if (null != ruleClaimSequenceBO.getClmProcessModifierCode() 
							&& !"".equals(ruleClaimSequenceBO.getClmProcessModifierCode()))
					{
						if(countRuleClaim ==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleClaimSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
							
							HtmlOutputText outputTextColumnsn = new HtmlOutputText();
							outputTextColumnsn.setValue(ruleClaimSequenceBO.getClmSqncNbr()+"  ");
							outputTextColumnsn.setStyle("text-align:center;font-weight:bold;");				
							ruleClaimSequenceGroup.getChildren().add(outputTextColumnsn);
							panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
							
							
						}
						
						outputTextColumn4.setValue("Claim Process Modifier Code   : "+ ruleClaimSequenceBO.getClmProcessModifierCode());
						outputTextColumn4.setStyle("text-align:center;white-space:nowrap;");
						ruleClaimSequenceGroup.getChildren().add(outputTextColumn4);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
						countRuleClaim++;
					}
					
					ruleClaimSequenceGroup = new HtmlPanelGroup();
					outputTextColumn4 = new HtmlOutputText();
					if (null != ruleClaimSequenceBO.getClmProcessModifierSecondaryCode() 
							&& !"".equals(ruleClaimSequenceBO.getClmProcessModifierSecondaryCode()))
					{
						if(countRuleClaim ==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleClaimSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
							
							HtmlOutputText outputTextColumnsn = new HtmlOutputText();
							outputTextColumnsn.setValue(ruleClaimSequenceBO.getClmSqncNbr()+"  ");
							outputTextColumnsn.setStyle("text-align:center;font-weight:bold;");				
							ruleClaimSequenceGroup.getChildren().add(outputTextColumnsn);
							panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
							
							
						}
						
						outputTextColumn4.setValue("Claim Process Modifier Secondary Code   : "
								+ ruleClaimSequenceBO.getClmProcessModifierSecondaryCode());
						outputTextColumn4.setStyle("text-align:center;white-space:nowrap;");
						ruleClaimSequenceGroup.getChildren().add(outputTextColumn4);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
						countRuleClaim++;
					}
					
					ruleClaimSequenceGroup = new HtmlPanelGroup();
					outputTextColumn4 = new HtmlOutputText();
					if (null != ruleClaimSequenceBO.getClmttCd() 
							&& !"".equals(ruleClaimSequenceBO.getClmttCd()))
					{
						if(countRuleClaim %4==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleClaimSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
							
							if(countRuleClaim ==0){
								
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleClaimSequenceBO.getClmSqncNbr()+"  ");
								outputTextColumnsn.setStyle("text-align:center;font-weight:bold;");				
								ruleClaimSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
								
								
							}
							
						}
						
						outputTextColumn4.setValue("Claim TT Code   : "
								+ ruleClaimSequenceBO.getClmttCd());
						outputTextColumn4.setStyle("text-align:center;white-space:nowrap;");
						ruleClaimSequenceGroup.getChildren().add(outputTextColumn4);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
						countRuleClaim++;
					}
					
					ruleClaimSequenceGroup = new HtmlPanelGroup();
					outputTextColumn4 = new HtmlOutputText();
					if (null != ruleClaimSequenceBO.getClmPosCd() 
							&& !"".equals(ruleClaimSequenceBO.getClmPosCd()))
					{
						if(countRuleClaim %4==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleClaimSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
							
							if(countRuleClaim ==0){
								
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleClaimSequenceBO.getClmSqncNbr()+"  ");
								outputTextColumnsn.setStyle("text-align:center;font-weight:bold;");				
								ruleClaimSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
								
								
							}
							
						}
						
						outputTextColumn4.setValue("Claim POS Code   : "
								+ ruleClaimSequenceBO.getClmPosCd());
						outputTextColumn4.setStyle("text-align:center;white-space:nowrap;");
						ruleClaimSequenceGroup.getChildren().add(outputTextColumn4);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
						countRuleClaim++;
					}
					
					ruleClaimSequenceGroup = new HtmlPanelGroup();
					outputTextColumn4 = new HtmlOutputText();
					if (null != ruleClaimSequenceBO.getClmHMOClassCode() 
							&& !"".equals(ruleClaimSequenceBO.getClmHMOClassCode()))
					{
						if(countRuleClaim %4==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleClaimSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
							
                            if(countRuleClaim ==0){
								
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleClaimSequenceBO.getClmSqncNbr()+"  ");
								outputTextColumnsn.setStyle("text-align:center;font-weight:bold;");				
								ruleClaimSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
								
								
							}
						}
						
						outputTextColumn4.setValue("Claim HMO Class Code   : "
								+ ruleClaimSequenceBO.getClmHMOClassCode());
						outputTextColumn4.setStyle("text-align:center;white-space:nowrap;");
						ruleClaimSequenceGroup.getChildren().add(outputTextColumn4);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
						countRuleClaim++;
					}
					
					ruleClaimSequenceGroup = new HtmlPanelGroup();
					outputTextColumn4 = new HtmlOutputText();
					if (null != ruleClaimSequenceBO.getClmSameDaySrvcInd() 
							&& !"".equals(ruleClaimSequenceBO.getClmSameDaySrvcInd()))
					{
						if(countRuleClaim %4==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleClaimSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
							
                               if(countRuleClaim ==0){
								
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleClaimSequenceBO.getClmSqncNbr()+"  ");
								outputTextColumnsn.setStyle("text-align:center;font-weight:bold;");				
								ruleClaimSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
								
								
							}
							
						}
						
						outputTextColumn4.setValue("Claim Same Day Service Indicator   : "
								+ ruleClaimSequenceBO.getClmSameDaySrvcInd());
						outputTextColumn4.setStyle("text-align:center;white-space:nowrap;");
						ruleClaimSequenceGroup.getChildren().add(outputTextColumn4);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
						countRuleClaim++;
					}
					
					ruleClaimSequenceGroup = new HtmlPanelGroup();
					outputTextColumn4 = new HtmlOutputText();
					if (null != ruleClaimSequenceBO.getClmSprtgProcCdInd() 
							&& !"".equals(ruleClaimSequenceBO.getClmSprtgProcCdInd()))
					{
						if(countRuleClaim %4==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleClaimSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
							
                               if(countRuleClaim ==0){
								
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleClaimSequenceBO.getClmSqncNbr()+"  ");
								outputTextColumnsn.setStyle("text-align:center;font-weight:bold;");				
								ruleClaimSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
								
								
							}
							
						}
						
						outputTextColumn4.setValue("Claim Sprtg Proc Indicator   : "
								+ ruleClaimSequenceBO.getClmSprtgProcCdInd());
						outputTextColumn4.setStyle("text-align:center;white-space:nowrap;");
						ruleClaimSequenceGroup.getChildren().add(outputTextColumn4);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
						countRuleClaim++;
					}
					
					ruleClaimSequenceGroup = new HtmlPanelGroup();
					outputTextColumn4 = new HtmlOutputText();
					if (null != ruleClaimSequenceBO.getClaimDiagnosisIndicator() 
							&& !"".equals(ruleClaimSequenceBO.getClaimDiagnosisIndicator()))
					{
						if(countRuleClaim %4==0){
							HtmlOutputText outputTextColumnsp = new HtmlOutputText();
							outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CS");
							outputTextColumnsp.setStyle("visibility:hidden;");						
							ruleClaimSequenceGroup.getChildren().add(outputTextColumnsp);
							panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
							
                              if(countRuleClaim ==0){
								
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleClaimSequenceBO.getClmSqncNbr()+"  ");
								outputTextColumnsn.setStyle("text-align:center;font-weight:bold;");				
								ruleClaimSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
								
								
							}
							
						}
						outputTextColumn4.setValue("Claim Diagnosis Indicator   : "
								+ ruleClaimSequenceBO.getClaimDiagnosisIndicator());
						outputTextColumn4.setStyle("text-align:center;white-space:nowrap;");
						ruleClaimSequenceGroup.getChildren().add(outputTextColumn4);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
						countRuleClaim++;
					}
					
					int mod2 = countRuleClaim % 4;
					for (int ij = 0; ij < (4 - mod2); ij++) {
						ruleClaimSequenceGroup = new HtmlPanelGroup();
						outputTextColumn4 = new HtmlOutputText();
						outputTextColumn4.setValue("");
						ruleClaimSequenceGroup.getChildren().add(outputTextColumn4);
						panelRuleSequences.getChildren().add(ruleClaimSequenceGroup);
					}
					
					panelRuleSequences.setCellspacing("8");
					ruleClaimCodeSequenceMap = ruleClaimSequenceBO.getRuleClaimCodeSequenceMap();
					Set ruleClaimCodeSequenceMapKeySet = ruleClaimCodeSequenceMap.keySet();
					Iterator ruleClaimCodeSequenceMapKeySetIterator = ruleClaimCodeSequenceMapKeySet.iterator();
					HtmlPanelGroup ruleClaimCodeSequenceGroup;
					HtmlOutputText outputTextColumn5;
					boolean clmCdFlag= false;
					 
					
					int countRuleClaimCode = 0;
					while(ruleClaimCodeSequenceMapKeySetIterator.hasNext()){
						panelRuleSequences.setCellspacing("8");
						countRuleClaimCode = 0;
						Object ruleClaimCodeSequenceIdKey = ruleClaimCodeSequenceMapKeySetIterator.next();                
						String ruleClaimCodeSequenceNumber = ruleClaimCodeSequenceIdKey.toString();
						ruleClaimCodeSequenceBO = ruleClaimCodeSequenceMap.get(ruleClaimCodeSequenceIdKey);
						
						 if((ruleClaimCodeSequenceBO.getClmCdSqncNbr() > 0) && (clmCdFlag == false)){
							  
								ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
								HtmlOutputText outputTextColumnsp = new HtmlOutputText();
								outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CLMS");
								outputTextColumnsp.setStyle("visibility:hidden;");						
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsp);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
								
								
								outputTextColumn5 = new HtmlOutputText();
								outputTextColumn5
										.setValue("   Claim Code Sequences             : ");
								outputTextColumn5.setStyle("font-weight:bold ;");
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumn5);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);

								ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
								outputTextColumn5 = new HtmlOutputText();
								outputTextColumn5.setValue("");
								ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumn5);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);

								ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
								outputTextColumn5 = new HtmlOutputText();
								outputTextColumn5.setValue("");
								ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumn5);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
								
								ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
								outputTextColumn5 = new HtmlOutputText();
								outputTextColumn5.setValue("");
								ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumn5);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
								
								clmCdFlag= true;
								}
						 
							
						ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
						outputTextColumn5 = new HtmlOutputText();
						
						
						
						if (null != ruleClaimCodeSequenceBO.getClmHcptLowVal() && !"".equals(ruleClaimCodeSequenceBO.getClmHcptLowVal()) && 
								null != ruleClaimCodeSequenceBO.getClmHcptHighVal() && !"".equals(ruleClaimCodeSequenceBO.getClmHcptHighVal()))
						{
							if(countRuleClaimCode%4==0){
								HtmlOutputText outputTextColumnsp = new HtmlOutputText();
								outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CLMS");
								outputTextColumnsp.setStyle("visibility:hidden;");						
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsp);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
								
								HtmlOutputText outputTextColumnsn = new HtmlOutputText();
								outputTextColumnsn.setValue(ruleClaimCodeSequenceBO.getClmCdSqncNbr()+"  ");
								outputTextColumnsn.setStyle("font-weight:bold ;");					
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsn);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
								
							}
							outputTextColumn5.setValue("HCP   : "+ ruleClaimCodeSequenceBO.getClmHcptLowVal()
									+" - "+ruleClaimCodeSequenceBO.getClmHcptHighVal());
							ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumn5);
							panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
							countRuleClaimCode++;
						}
						
						//RMA ICD-10 : this section will display DIA & ICD ranges and its version
						List claimCodeList = ruleClaimCodeSequenceBO.getClaimCodeList();
						if(null != claimCodeList){
						for (int j = 0; j < claimCodeList.size(); j++) {
							RuleCodeRanges ruleCodeRanges = (RuleCodeRanges) claimCodeList
									.get(j);

							String type = ruleCodeRanges.getCodeType();
							String low = ruleCodeRanges.getCodeTypeLowVal();
							String high = ruleCodeRanges.getCodeTypeHighVal();

							ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
							outputTextColumn5 = new HtmlOutputText();
							if ((null != ruleCodeRanges.getCodeType() && !ruleCodeRanges
									.getCodeType().equalsIgnoreCase("DUM"))
									&& !"".equals(ruleCodeRanges.getCodeType())) {
								
								if(countRuleClaimCode%4==0){
									HtmlOutputText outputTextColumnsp = new HtmlOutputText();
									outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CLMS");
									outputTextColumnsp.setStyle("visibility:hidden;");						
									ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsp);
									panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
									
									HtmlOutputText outputTextColumnsn = new HtmlOutputText();
									outputTextColumnsn.setValue(ruleClaimCodeSequenceBO.getClmCdSqncNbr()+"  ");
									outputTextColumnsn.setStyle("font-weight:bold ;");					
									ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsn);
									panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
									
								}
								
								outputTextColumn5.setValue(ruleCodeRanges.getCodeType()
										+ " : " + ruleCodeRanges.getCodeTypeLowVal()
										+ "-" + ruleCodeRanges.getCodeTypeHighVal()
										+ "(" + ruleCodeRanges.getIcdVersionIndicator()+ ")");
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumn5);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
								countRuleClaimCode++;
							}

						}
					}
						
						ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
						outputTextColumn5 = new HtmlOutputText();
						if (null != ruleClaimCodeSequenceBO.getClmRevLowVal() && !"".equals(ruleClaimCodeSequenceBO.getClmRevLowVal()) && 
								null != ruleClaimCodeSequenceBO.getClmRevHighVal() && !"".equals(ruleClaimCodeSequenceBO.getClmRevHighVal()))
						{
							if(countRuleClaimCode%4==0){
								HtmlOutputText outputTextColumnsp = new HtmlOutputText();
								outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CLMS");
								outputTextColumnsp.setStyle("visibility:hidden;");						
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsp);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
								
								if(countRuleClaimCode ==0){
																		
									HtmlOutputText outputTextColumnsn = new HtmlOutputText();
									outputTextColumnsn.setValue(ruleClaimCodeSequenceBO.getClmCdSqncNbr()+"  ");
									outputTextColumnsn.setStyle("font-weight:bold ;");					
									ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsn);
									panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
									
								}
								
							}
							
							outputTextColumn5.setValue("REV   : "+ ruleClaimCodeSequenceBO.getClmRevLowVal()
									+" - "+ruleClaimCodeSequenceBO.getClmRevHighVal());
							ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumn5);
							panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
							countRuleClaimCode++;
						}

						
						
						
						ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
						outputTextColumn5 = new HtmlOutputText();
						if (null != ruleClaimCodeSequenceBO.getClmServiceClassLow() && !"".equals(ruleClaimCodeSequenceBO.getClmServiceClassLow()) && 
								null != ruleClaimCodeSequenceBO.getClmServiceClassHigh() && !"".equals(ruleClaimCodeSequenceBO.getClmServiceClassHigh()))
						{
							if(countRuleClaimCode%4==0){
								HtmlOutputText outputTextColumnsp = new HtmlOutputText();
								outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CLMS");
								outputTextColumnsp.setStyle("visibility:hidden;");						
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsp);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
								
								if(countRuleClaimCode ==0){
									
									HtmlOutputText outputTextColumnsn = new HtmlOutputText();
									outputTextColumnsn.setValue(ruleClaimCodeSequenceBO.getClmCdSqncNbr()+"  ");
									outputTextColumnsn.setStyle("font-weight:bold ;");					
									ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsn);
									panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
									
								}
								
							}
							
							outputTextColumn5.setValue("Service Class   : "+ ruleClaimCodeSequenceBO.getClmServiceClassLow()
									+" - "+ruleClaimCodeSequenceBO.getClmServiceClassHigh());
							ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumn5);
							panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
							countRuleClaimCode++;
						}

						ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
						outputTextColumn5 = new HtmlOutputText();
						if (null != ruleClaimCodeSequenceBO.getClmLimitClassLow() && !"".equals(ruleClaimCodeSequenceBO.getClmLimitClassLow()) && 
								null != ruleClaimCodeSequenceBO.getClmLimitClassHigh() && !"".equals(ruleClaimCodeSequenceBO.getClmLimitClassHigh()))
						{
							if(countRuleClaimCode%4==0){
								HtmlOutputText outputTextColumnsp = new HtmlOutputText();
								outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CLMS");
								outputTextColumnsp.setStyle("visibility:hidden;");						
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsp);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
								
								if(countRuleClaimCode ==0){
									
									HtmlOutputText outputTextColumnsn = new HtmlOutputText();
									outputTextColumnsn.setValue(ruleClaimCodeSequenceBO.getClmCdSqncNbr()+"  ");
									outputTextColumnsn.setStyle("font-weight:bold ;");					
									ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsn);
									panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
									
								}
								
							}
							
							outputTextColumn5.setValue("Limit Class   : "+ ruleClaimCodeSequenceBO.getClmLimitClassLow()
									+" - "+ruleClaimCodeSequenceBO.getClmLimitClassHigh());
							ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumn5);
							panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
							countRuleClaimCode++;
						}
						
						ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
						outputTextColumn5 = new HtmlOutputText();
						if (null != ruleClaimCodeSequenceBO.getClmIcdpCategoryCode() && !"".equals(ruleClaimCodeSequenceBO.getClmIcdpCategoryCode()))
						{
							if(countRuleClaimCode%4==0){
								HtmlOutputText outputTextColumnsp = new HtmlOutputText();
								outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CLMS");
								outputTextColumnsp.setStyle("visibility:hidden;");						
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsp);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);								
								if(countRuleClaimCode ==0){									
									HtmlOutputText outputTextColumnsn = new HtmlOutputText();
									outputTextColumnsn.setValue(ruleClaimCodeSequenceBO.getClmCdSqncNbr()+"  ");
									outputTextColumnsn.setStyle("font-weight:bold ;");					
									ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsn);
									panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);									
								}								
							}							
							outputTextColumn5.setValue("Icd Category Code   : "+ ruleClaimCodeSequenceBO.getClmIcdpCategoryCode());
							ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumn5);
							panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
							countRuleClaimCode++;
						}
						
						ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
						outputTextColumn5 = new HtmlOutputText();
						if (null != ruleClaimCodeSequenceBO.getClmIcdpClsfctnId() && !"".equals(ruleClaimCodeSequenceBO.getClmIcdpClsfctnId()))
						{
							if(countRuleClaimCode%4==0){
								HtmlOutputText outputTextColumnsp = new HtmlOutputText();
								outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CLMS");
								outputTextColumnsp.setStyle("visibility:hidden;");						
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsp);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);								
								if(countRuleClaimCode ==0){									
									HtmlOutputText outputTextColumnsn = new HtmlOutputText();
									outputTextColumnsn.setValue(ruleClaimCodeSequenceBO.getClmCdSqncNbr()+"  ");
									outputTextColumnsn.setStyle("font-weight:bold ;");					
									ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsn);
									panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);									
								}								
							}							
							outputTextColumn5.setValue("Icd Classification Id   : "+ ruleClaimCodeSequenceBO.getClmIcdpClsfctnId());
							ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumn5);
							panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
							countRuleClaimCode++;
						}

						ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
						outputTextColumn5 = new HtmlOutputText();
						if (null != ruleClaimCodeSequenceBO.getClmDiagCategoryCode() && !"".equals(ruleClaimCodeSequenceBO.getClmDiagCategoryCode()))
						{
							if(countRuleClaimCode%4==0){
								HtmlOutputText outputTextColumnsp = new HtmlOutputText();
								outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CLMS");
								outputTextColumnsp.setStyle("visibility:hidden;");						
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsp);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);								
								if(countRuleClaimCode ==0){									
									HtmlOutputText outputTextColumnsn = new HtmlOutputText();
									outputTextColumnsn.setValue(ruleClaimCodeSequenceBO.getClmCdSqncNbr()+"  ");
									outputTextColumnsn.setStyle("font-weight:bold ;");					
									ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsn);
									panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);									
								}								
							}							
							outputTextColumn5.setValue("Diag Category Code   : "+ ruleClaimCodeSequenceBO.getClmDiagCategoryCode());
							ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumn5);
							panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
							countRuleClaimCode++;
						}

						ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
						outputTextColumn5 = new HtmlOutputText();
						if (null != ruleClaimCodeSequenceBO.getClmDiagClsfctnId() && !"".equals(ruleClaimCodeSequenceBO.getClmDiagClsfctnId()))
						{
							if(countRuleClaimCode%4==0){
								HtmlOutputText outputTextColumnsp = new HtmlOutputText();
								outputTextColumnsp.setValue(ruleSequenceBO.getRuleSequenceNumber()+" CLMS");
								outputTextColumnsp.setStyle("visibility:hidden;");						
								ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsp);
								panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);								
								if(countRuleClaimCode ==0){									
									HtmlOutputText outputTextColumnsn = new HtmlOutputText();
									outputTextColumnsn.setValue(ruleClaimCodeSequenceBO.getClmCdSqncNbr()+"  ");
									outputTextColumnsn.setStyle("font-weight:bold ;");					
									ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumnsn);
									panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);									
								}								
							}							
							outputTextColumn5.setValue("Diag Classification Id   : "+ ruleClaimCodeSequenceBO.getClmDiagClsfctnId());
							ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumn5);
							panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
							countRuleClaimCode++;
						}
						
						int mod1 = countRuleClaimCode % 4;
						for (int ij = 0; ij < (4 - mod1); ij++) {
							ruleClaimCodeSequenceGroup = new HtmlPanelGroup();
							outputTextColumn5 = new HtmlOutputText();
							outputTextColumn5.setValue("");
							ruleClaimCodeSequenceGroup.getChildren().add(outputTextColumn5);
							panelRuleSequences.getChildren().add(ruleClaimCodeSequenceGroup);
						}						
					}//end ruleClaimCodeSequenceBO
					
					
				}//end ruleClaimSequenceBO
				
				
			}
		}
		}
		}catch(Exception e){
			Logger.logError("Exception occured at ContractRuleBackingBean.preparePanelForRuleSequence");
			Logger.logError(e);
		}
	}

	/**
	 * 
	 * @return
	 */
	public String updateRules() {
		if (validateRuleCommentsFields()) {
			List ruleUpdateList = new ArrayList();
			ruleUpdateList.add(this.getRuleCommentMap());
			RetrieveRulesRequest retrieveRulesRequest = (RetrieveRulesRequest) this
					.getServiceRequest(ServiceManager.RETRIEVE__RULES);
			retrieveRulesRequest.setAction(RetrieveRulesRequest.UPDATE_RULES);
			super.setContractKeyToRequest(retrieveRulesRequest);
			retrieveRulesRequest.setRuleList(ruleUpdateList);
			retrieveRulesRequest.setContractKeyObject(getContractSession()
					.getContractKeyObject());
			RetrieveRulesResponse searchResponse = (RetrieveRulesResponse) executeService(retrieveRulesRequest);
			this.setValidationMessages(searchResponse.getMessages());
			//this.ruleHidden=WebConstants.EMPTY_STRING;

			if (this.exclusionRuleSelected) {
				this.loadContractRules(BusinessConstants.RULE_TYPE_EXCLUSION);
			} else if (this.denialRuleSelected) {
				this.loadContractRules(BusinessConstants.RULE_TYPE_DENIAL);
			} else if (this.umRuleSelected) {
				this.loadContractRules(BusinessConstants.RULE_TYPE_UM);
			} else if (this.pnrRuleSelected) {
				this.loadContractRules(BusinessConstants.RULE_TYPE_PNR);
			}
			getRequest().setAttribute("RETAIN_Value", "");
			return "displayRules";
		}
		addAllMessagesToRequest(this.validationMessages);
		return WebConstants.EMPTY_STRING;
	}

	private boolean validateRuleCommentsFields() {
		validationMessages = new ArrayList();
		boolean requiredField = false;
		if (this.ruleCommentMap == null || ruleCommentMap.size() == 0) {
			requiredField = true;
		} else {
			//check all against rule comment, if any comment is modified than go for update	
			Iterator ruleIDIter = this.ruleCommentMap.keySet().iterator();
			String ruleComment;
			while (ruleIDIter.hasNext()) {
				Object key = ruleIDIter.next();
				ruleComment = (String) ruleCommentMap.get(key);
				//Validates name for special characters
				if (!ruleComment.trim().matches("^[\\d|a-z|A-Z|\\s]*$")) {
					this.ruleCommentMap.put(key, WebConstants.EMPTY_STRING);
					requiredField = true;
					//		                    this.validationMessages.add(new ErrorMessage(BusinessConstants.MSG_PRODUCT_RULE_INVALID));
					//		                    return false;
				}//end if                
			}//end while      
			if (requiredField) // no field modified
			{
				this.validationMessages.add(new ErrorMessage(
						BusinessConstants.MSG_CONTRACT_RULE_INVALID));
			}
		}
		this.rulesValueCheck = !requiredField;
		return requiredField ? false : true;
	}

	//	  ----------------------------------- Load rules according to rule type as filter -----------------------------
	public String loadExclusionRule() {
		this.panel = null;
		this.loadContractRules(BusinessConstants.RULE_TYPE_EXCLUSION);
		this.exclusionRuleSelected = true;
		this.denialRuleSelected = false;
		this.umRuleSelected = false;
		this.pnrRuleSelected = false;
		if (super.isViewMode()) {
			return "displayRulesView";
		} else {
			ruleType = "";
			ruleHidden = "";
			getRequest().setAttribute("RETAIN_Value", "");
			return "displayRules";
		}
	}

	public String loadDenialRule() {
		this.panel = null;
		this.exclusionRuleSelected = false;
		this.denialRuleSelected = true;
		this.umRuleSelected = false;
		this.pnrRuleSelected = false;
		this.loadContractRules(BusinessConstants.RULE_TYPE_DENIAL);
		if (super.isViewMode()) {
			return "displayRulesView";
		} else {
			ruleType = "";
			ruleHidden = "";
			getRequest().setAttribute("RETAIN_Value", "");
			return "displayRules";
		}
	}

	public String loadUMRule() {
		this.panel = null;
		this.exclusionRuleSelected = false;
		this.denialRuleSelected = false;
		this.umRuleSelected = true;
		this.pnrRuleSelected = false;
		this.loadContractRules(BusinessConstants.RULE_TYPE_UM);
		if (super.isViewMode()) {
			return "displayRulesView";
		} else {
			ruleType = "";
			ruleHidden = "";
			getRequest().setAttribute("RETAIN_Value", "");
			return "displayRules";
		}
	}

	public String loadPNRRule() {
		this.panel = null;
		this.exclusionRuleSelected = false;
		this.denialRuleSelected = false;
		this.umRuleSelected = false;
		this.pnrRuleSelected = true;
		this.loadContractRules(BusinessConstants.RULE_TYPE_PNR);
		if (super.isViewMode()) {
			return "displayRulesView";
		} else {
			ruleType = "";
			ruleHidden = "";
			getRequest().setAttribute("RETAIN_Value", "");
			return "displayRules";
		}
	}

	/*
	 * Returns the loadProductRules
	 */

	public String loadRuleViewPage() {
		/*Check whether the rule is a grouprule.
		 Ideally the group rule indicator can be made availabe here through request parameter.
		 Anyway this can be checked this way
		 */
		RuleRequest request = (RuleRequest) this
				.getServiceRequest(ServiceManager.RULE_REQUEST);

		request.setRuleId((ruleIdDetails.trim()).toUpperCase());
		RuleResponse response = (RuleResponse) executeService(request);
		if (response != null) {
			List ruleList = response.getRuleList();
			if (ruleList != null && ruleList.size() > 0) {
				RuleBO ruleBo = (RuleBO) ruleList.get(0);
				if (null != ruleBo) {
					if ("Y".equalsIgnoreCase(ruleBo.getGroupIndicator())) {
						return "viewGroupRule";
					}
				}
			}
		}

		if (blzRuleType.equalsIgnoreCase("BLZWPDAB"))
			return "newViewRules";
		else
			return "viewRules";

	}

	/*
	 * Returns the loadProductRules
	 */

	public String loadRulePrintPage() {
		return "printRules";
	}

	/**
	 * Returns the loadProductRules
	 */
	public void loadContractRules(String ruleType) {
		if (ruleType == null
				|| ruleType.equals(WebConstants.EMPTY_STRING)
				|| !(ruleType.equals(BusinessConstants.RULE_TYPE_EXCLUSION)
						|| ruleType.equals(BusinessConstants.RULE_TYPE_DENIAL)
						|| ruleType.equals(BusinessConstants.RULE_TYPE_UM) || ruleType
						.equals(BusinessConstants.RULE_TYPE_PNR))) {
			ruleType = BusinessConstants.RULE_TYPE_EXCLUSION;
		} else if (ruleType.equals(BusinessConstants.RULE_TYPE_EXCLUSION)) {
			ruleType = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_EXCLUSION + "%";
		} else if (ruleType.equals(BusinessConstants.RULE_TYPE_DENIAL)) {
			ruleType = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_DENIAL + "%";
		} else if (ruleType.equals(BusinessConstants.RULE_TYPE_UM)) {
			ruleType = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_UM + "%";
		} else if (ruleType.equals(BusinessConstants.RULE_TYPE_PNR)) {
			ruleType = BusinessConstants.SPECIAL_CHAR_RULE_TYPE_PNR + "%";
		}
		RetrieveRulesRequest retrieveRulesRequest = (RetrieveRulesRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE__RULES);
		retrieveRulesRequest
				.setAction(RetrieveRulesRequest.RETRIVE_RULES_COMPLETE);
		super.setContractKeyToRequest(retrieveRulesRequest);
		int id = super.getContractSession().getContractKeyObject()
				.getDateSegmentId();
		retrieveRulesRequest.setDateSegmentSysId(id);
		retrieveRulesRequest.setRuleType(ruleType);
		RetrieveRulesResponse searchResponse = null;
		if (id != 0) {
			searchResponse = (RetrieveRulesResponse) executeService(retrieveRulesRequest);
		}
		if (null != searchResponse) {

			List sampleList = new ArrayList();
			sampleList = searchResponse.getSearchResultList();
			if (sampleList != null && sampleList.size() > 0) {
				this.renderFlag = true;
				this.renderFlagForPanel = true;
				this.setRuleInterList(sampleList);
			} else {
				this.renderFlag = false;
				this.renderFlagForPanel = false;
				this.setRuleInterList(sampleList);
			}
		}
		this.addAllMessagesToRequest(this.validationMessages);
	}

	/**
	 * @return loadContractRules
	 * 
	 * Returns the loadContractRules.
	 */
	//WAS 7.0 Changes - Binding variable rootQuestionLoad modified to HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
	
	/**
	 * 
	 * @return HtmlInputHidden
	 */
	public HtmlInputHidden  getLoadContractRules() {
		int iFlag = 0;
		if (!(this.exclusionRuleSelected | this.denialRuleSelected
				| this.pnrRuleSelected | this.umRuleSelected)) {
			iFlag = 1;
			this.loadContractRules(BusinessConstants.RULE_TYPE_EXCLUSION);
			this.exclusionRuleSelected = true;
		}
		if (iFlag != 1) {
			if (this.exclusionRuleSelected) {
				this.loadContractRules(BusinessConstants.RULE_TYPE_EXCLUSION);
			} else if (this.denialRuleSelected) {
				this.loadContractRules(BusinessConstants.RULE_TYPE_DENIAL);
			} else if (this.umRuleSelected) {
				this.loadContractRules(BusinessConstants.RULE_TYPE_UM);
			} else if (this.pnrRuleSelected) {
				this.loadContractRules(BusinessConstants.RULE_TYPE_PNR);
			}
		}
		//return WebConstants.EMPTY_STRING;
		loadContractRules.setValue(WebConstants.EMPTY_STRING);
        return loadContractRules;  
	}

	/**
	 * @param loadContractRules
	 * 
	 * Sets the loadContractRules.
	 */
	public void setLoadContractRules(HtmlInputHidden  loadContractRules) {
		this.loadContractRules = loadContractRules;
	}

	/**
	 * @return Returns the renderFlag.
	 */
	public boolean isRenderFlag() {
		return renderFlag;
	}

	/**
	 * @param renderFlag The renderFlag to set.
	 */
	public void setRenderFlag(boolean renderFlag) {
		this.renderFlag = renderFlag;
	}

	/**
	 * @return Returns the ruleHidden.
	 */
	public String getRuleHidden() {
		return ruleHidden;
	}

	/**
	 * @param ruleHidden The ruleHidden to set.
	 */
	public void setRuleHidden(String ruleHidden) {
		this.ruleHidden = ruleHidden;
	}

	/**
	 * @param ruleInformationList The ruleInformationList to set.
	 */
	public void setRuleInformationList(List ruleInformationList) {
		this.ruleInformationList = ruleInformationList;
	}

	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return getContractSession().getContractKeyObject().getState();
	}

	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return getContractSession().getContractKeyObject().getStatus();
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return Returns the validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * @param validationMessages The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * @return Returns the version.
	 */
	public String getVersion() {
		return Integer.toString(getContractSession().getContractKeyObject()
				.getVersion());
	}

	/**
	 * @param version The version to set.
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @param ruleResultList The ruleResultList to set.
	 */
	public void setRuleResultList(List ruleResultList) {
		this.ruleResultList = ruleResultList;
	}

	/**
	 * @return Returns the checkIn.
	 */
	public boolean isCheckIn() {
		return checkIn;
	}

	/**
	 * @param checkIn The checkIn to set.
	 */
	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}

	/**
	 * @return Returns the ruleInformationList.
	 */
	public List getRuleInformationList() {
		if (ruleInformationList == null) {
			List list = retrieveInformationList();
			this.ruleInformationList = list;
		}
		return ruleInformationList;
	}

	/**
	 * @return Returns the selectedDateSysId.
	 */
	public String getSelectedDateSysId() {
		return selectedDateSysId;
	}

	/**
	 * @param selectedDateSysId The selectedDateSysId to set.
	 */
	public void setSelectedDateSysId(String selectedDateSysId) {
		this.selectedDateSysId = selectedDateSysId;
	}

	/**
	 * @return Returns the selectedRuleSysID.
	 */
	public String getSelectedRuleSysID() {
		return selectedRuleSysID;
	}

	/**
	 * @param selectedRuleSysID The selectedRuleSysID to set.
	 */
	public void setSelectedRuleSysID(String selectedRuleSysID) {
		this.selectedRuleSysID = selectedRuleSysID;
	}

	/**
	 * @return Returns the messageList.
	 */
	public List getMessageList() {
		return messageList;
	}

	/**
	 * @param messageList The messageList to set.
	 */
	public void setMessageList(List messageList) {
		this.messageList = messageList;
	}

	/**
	 * @return Returns the panel.
	 */
	public HtmlPanelGrid getPanel() {
		if (this.rulesValueCheck) {
			this.preparePanel(this.getRuleInterList());
		}
		return panel;
	}

	/**
	 * @param panel The panel to set.
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
	}

	/**
	 * @param headingPanel The headingPanel to set.
	 */
	public void setHeadingPanel(HtmlPanelGrid headingPanel) {
		this.headingPanel = headingPanel;
	}

	/**
	 * @return Returns the ruleCommentMap.
	 */
	public Map getRuleCommentMap() {
		return ruleCommentMap;
	}

	/**
	 * @param ruleCommentMap The ruleCommentMap to set.
	 */
	public void setRuleCommentMap(Map ruleCommentMap) {
		this.ruleCommentMap = ruleCommentMap;
	}

	/**
	 * @return Returns the renderFlagForPanel.
	 */
	public boolean isRenderFlagForPanel() {
		return renderFlagForPanel;
	}

	/**
	 * @param renderFlagForPanel The renderFlagForPanel to set.
	 */
	public void setRenderFlagForPanel(boolean renderFlagForPanel) {
		this.renderFlagForPanel = renderFlagForPanel;
	}

	/**
	 * @return Returns the mapForPrint.
	 */
	public String getMapForPrint() {

		this.ruleInformationList = this.retrieveInformationList();
		this.separateRuleList();
		this.renderFlag = true;

		return mapForPrint;
	}

	/**
	 * @param mapForPrint The mapForPrint to set.
	 */
	public void setMapForPrint(String mapForPrint) {
		this.mapForPrint = mapForPrint;
	}

	/**
	 * @return denialRuleSelected
	 * 
	 * Returns the denialRuleSelected.
	 */
	public boolean isDenialRuleSelected() {
		return denialRuleSelected;
	}

	/**
	 * @param denialRuleSelected
	 * 
	 * Sets the denialRuleSelected.
	 */
	public void setDenialRuleSelected(boolean denialRuleSelected) {
		this.denialRuleSelected = denialRuleSelected;
	}

	/**
	 * @return exclusionRuleSelected
	 * 
	 * Returns the exclusionRuleSelected.
	 */
	public boolean isExclusionRuleSelected() {
		return exclusionRuleSelected;
	}

	/**
	 * @param exclusionRuleSelected
	 * 
	 * Sets the exclusionRuleSelected.
	 */
	public void setExclusionRuleSelected(boolean exclusionRuleSelected) {
		this.exclusionRuleSelected = exclusionRuleSelected;
	}

	/**
	 * @return pnrRuleSelected
	 * 
	 * Returns the pnrRuleSelected.
	 */
	public boolean isPnrRuleSelected() {
		return pnrRuleSelected;
	}

	/**
	 * @param pnrRuleSelected
	 * 
	 * Sets the pnrRuleSelected.
	 */
	public void setPnrRuleSelected(boolean pnrRuleSelected) {
		this.pnrRuleSelected = pnrRuleSelected;
	}

	/**
	 * @return umRuleSelected
	 * 
	 * Returns the umRuleSelected.
	 */
	public boolean isUmRuleSelected() {
		return umRuleSelected;
	}

	/**
	 * @param umRuleSelected
	 * 
	 * Sets the umRuleSelected.
	 */
	public void setUmRuleSelected(boolean umRuleSelected) {
		this.umRuleSelected = umRuleSelected;
	}

	/**
	 * @return ruleInterList
	 * 
	 * Returns the ruleInterList.
	 */
	public List getRuleInterList() {
		return ruleInterList;
	}

	/**
	 * @param ruleInterList
	 * 
	 * Sets the ruleInterList.
	 */
	public void setRuleInterList(List ruleInterList) {
		this.ruleInterList = ruleInterList;
	}

	/**
	 * Returns the ewpdGenRuleID
	 * @return String ewpdGenRuleID.
	 */
	public String getEwpdGenRuleID() {
		return ewpdGenRuleID;
	}

	/**
	 * Sets the ewpdGenRuleID
	 * @param ewpdGenRuleID.
	 */
	public void setEwpdGenRuleID(String ewpdGenRuleID) {
		this.ewpdGenRuleID = ewpdGenRuleID;
	}

	/**
	 * @return Returns the hasValidationErrors.
	 */
	public boolean isHasValidationErrors() {
		return hasValidationErrors;
	}

	/**
	 * @param hasValidationErrors The hasValidationErrors to set.
	 */
	public void setHasValidationErrors(boolean hasValidationErrors) {
		this.hasValidationErrors = hasValidationErrors;
	}

	/**
	 * Returns the ruleTypeListForCombo
	 * @return List ruleTypeListForCombo.
	 */
	public List getRuleTypeListForCombo() {
		/*
		 *	 as per the new requirement we have to hardcode the value rather to retrieve from rule_type table
		 *
		 */
		ruleTypeListForCombo = new ArrayList();
		ruleTypeListForCombo.add(new SelectItem(WebConstants.EMPTY_STRING));
		ruleTypeListForCombo.add(new SelectItem(
				BusinessConstants.RULE_TYPE_DENIAL,
				BusinessConstants.RULE_TYPE_DENIAL));
		ruleTypeListForCombo.add(new SelectItem(
				BusinessConstants.RULE_TYPE_EXCLUSION,
				BusinessConstants.RULE_TYPE_EXCLUSION));
		ruleTypeListForCombo.add(new SelectItem(BusinessConstants.RULE_TYPE_UM,
				BusinessConstants.RULE_TYPE_UM));
		ruleTypeListForCombo.add(new SelectItem(
				BusinessConstants.RULE_TYPE_PNR,
				BusinessConstants.RULE_TYPE_PNR));
		return ruleTypeListForCombo;
	}

	/**
	 * Sets the ruleTypeListForCombo
	 * @param ruleTypeListForCombo.
	 */
	public void setRuleTypeListForCombo(List ruleTypeListForCombo) {
		this.ruleTypeListForCombo = ruleTypeListForCombo;
	}

	/**
	 * @return Returns the ruleType.
	 */
	public String getRuleType() {
		return ruleType;
	}

	/**
	 * @param ruleType The ruleType to set.
	 */
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	/**
	 * @return Returns the requiredRuleType.
	 */
	public boolean isRequiredRuleType() {
		return requiredRuleType;
	}

	/**
	 * @param requiredRuleType The requiredRuleType to set.
	 */
	public void setRequiredRuleType(boolean requiredRuleType) {
		this.requiredRuleType = requiredRuleType;
	}

	/**
	 * @return Returns the ruleInvalid.
	 */
	public boolean isRuleInvalid() {
		return ruleInvalid;
	}

	/**
	 * @param ruleInvalid The ruleInvalid to set.
	 */
	public void setRuleInvalid(boolean ruleInvalid) {
		this.ruleInvalid = ruleInvalid;
	}

	/**
	 * @return Returns the rulesValueCheck.
	 */
	public boolean isRulesValueCheck() {
		return rulesValueCheck;
	}

	/**
	 * @param rulesValueCheck The rulesValueCheck to set.
	 */
	public void setRulesValueCheck(boolean rulesValueCheck) {
		this.rulesValueCheck = rulesValueCheck;
	}

	/**
	 * @param ruleSequenceLists The ruleSequenceLists to set.
	 */
	public void setRuleSequenceLists(List ruleSequenceLists) {
		this.ruleSequenceLists = ruleSequenceLists;
	}

	/**
	 * @param sequenceHidden The sequenceHidden to set.
	 */
	public void setSequenceHidden(HtmlInputHidden  sequenceHidden) {
		this.sequenceHidden = sequenceHidden;
	}

	/**
	 * @return Returns the ruleIdDetails.
	 */
	public String getRuleIdDetails() {
		return ruleIdDetails;
	}

	/**
	 * @param ruleIdDetails The ruleIdDetails to set.
	 */
	public void setRuleIdDetails(String ruleIdDetails) {
		this.ruleIdDetails = ruleIdDetails;
	}

	/**
	 * @return Returns the panelRuleSequences.
	 */
	public HtmlPanelGrid getPanelRuleSequences() {
		this.preparePanelForRuleSequence(this.getRuleSequenceLists());
		return panelRuleSequences;
	}

	/**
	 * @param panelRuleSequences The panelRuleSequences to set.
	 */
	public void setPanelRuleSequences(HtmlPanelGrid panelRuleSequences) {
		this.panelRuleSequences = panelRuleSequences;
	}

	/**
	 * @return Returns the groupRule.
	 */
	public GroupRule getGroupRule() {
		if (groupRule == null) {
			this.groupRule = retriveGroupRule(ruleIdDetails);
		}
		return groupRule;
	}

	/**
	 * @param groupRule The groupRule to set.
	 */
	public void setGroupRule(GroupRule groupRule) {
		this.groupRule = groupRule;
	}

	private GroupRule retriveGroupRule(String ruleId) {
		GroupRuleRequest request = (GroupRuleRequest) this
				.getServiceRequest(ServiceManager.GROUP_RULE_REQUEST);
		request.setRuleId(ruleId);
		GroupRuleResponse response = (GroupRuleResponse) executeService(request);
		if (response != null) {
			return response.getRule();
		}
		return null;
	}

	public String getGroupRuleHidden() {
		if (ruleIdDetails == null) {
			//In case of print we are passing the rule id as a request parameter.
			ruleIdDetails = (getRequest().getParameter("ruleId")).trim()
					.toUpperCase();
		}
		this.groupRule = retriveGroupRule(ruleIdDetails);
		return ruleIdDetails;
	}

	public void setGroupRuleHidden(String string) {
		// Do nothing
	}

	/**
	 * @return Returns the breadCrumpText.
	 */
	public String getGrpRulePrintBreadCrumbText() {
		if (ruleIdDetails == null) {
			//In case of print we are passing the rule id as a request parameter.
			ruleIdDetails = getRequest().getParameter("ruleId");
		}
		return "Rule (" + this.ruleIdDetails + ") >> Print";

	}

	public void setGrpRulePrintBreadCrumbText(String string) {
		// do nothing
	}

	/**
	 * @return Returns the ruleDescription.
	 */
	public String getRuleDescription() {
		return ruleDescription;
	}

	/**
	 * @param ruleDescription The ruleDescription to set.
	 */
	public void setRuleDescription(String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	/**
	 * @return Returns the ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}

	/**
	 * @param ruleId The ruleId to set.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * @return Returns the ruleSearchWord1.
	 */
	public String getRuleSearchWord1() {
		return ruleSearchWord1;
	}

	/**
	 * @param ruleSearchWord1 The ruleSearchWord1 to set.
	 */
	public void setRuleSearchWord1(String ruleSearchWord1) {
		this.ruleSearchWord1 = ruleSearchWord1;
	}

	/**
	 * @return Returns the ruleSearchWord2.
	 */
	public String getRuleSearchWord2() {
		return ruleSearchWord2;
	}

	/**
	 * @param ruleSearchWord2 The ruleSearchWord2 to set.
	 */
	public void setRuleSearchWord2(String ruleSearchWord2) {
		this.ruleSearchWord2 = ruleSearchWord2;
	}

	/**
	 * @return Returns the ruleSearchWord3.
	 */
	public String getRuleSearchWord3() {
		return ruleSearchWord3;
	}

	/**
	 * @param ruleSearchWord3 The ruleSearchWord3 to set.
	 */
	public void setRuleSearchWord3(String ruleSearchWord3) {
		this.ruleSearchWord3 = ruleSearchWord3;
	}

	/**
	 * @return Returns the hiddenDateSegmentSysId.
	 */
	public int getHiddenDateSegmentSysId() {
		return hiddenDateSegmentSysId;
	}

	/**
	 * @param hiddenDateSegmentSysId The hiddenDateSegmentSysId to set.
	 */
	public void setHiddenDateSegmentSysId(int hiddenDateSegmentSysId) {
		this.hiddenDateSegmentSysId = hiddenDateSegmentSysId;
	}

	/**
	 * @return Returns the denialRuleList.
	 */
	public List getDenialRuleList() {

		return denialRuleList;
	}

	/**
	 * @param denialRuleList The denialRuleList to set.
	 */
	public void setDenialRuleList(List denialRuleList) {
		this.denialRuleList = denialRuleList;
	}

	/**
	 * @return Returns the exclusionRuleList.
	 */
	public List getExclusionRuleList() {

		return exclusionRuleList;
	}

	/**
	 * @param exclusionRuleList The exclusionRuleList to set.
	 */
	public void setExclusionRuleList(List exclusionRuleList) {
		this.exclusionRuleList = exclusionRuleList;
	}

	/**
	 * @return Returns the pnrRuleList.
	 */
	public List getPnrRuleList() {

		return pnrRuleList;
	}

	/**
	 * @param pnrRuleList The pnrRuleList to set.
	 */
	public void setPnrRuleList(List pnrRuleList) {
		this.pnrRuleList = pnrRuleList;
	}

	/**
	 * @return Returns the umRuleList.
	 */
	public List getUmRuleList() {

		return umRuleList;
	}

	/**
	 * @param umRuleList The umRuleList to set.
	 */
	public void setUmRuleList(List umRuleList) {
		this.umRuleList = umRuleList;
	}

	/*
	 * This method is for separating exclusion,um,deniel and pnr rule.
	 * ruleInformationList contains complete set of rules and above mentioned rules are filtered with the 
	 * rukle indicator.
	 * 
	 */
	private void separateRuleList() {

		List ruleList = this.ruleInformationList;
		if (null != ruleList && ruleList.size() > 0) {
			this.exclusionRuleList = new ArrayList();
			this.denialRuleList = new ArrayList();
			this.umRuleList = new ArrayList();
			this.pnrRuleList = new ArrayList();
			for (int i = 0; i < ruleList.size(); i++) {
				RuleLocateResult locateresule = (RuleLocateResult) ruleList
						.get(i);
				if (locateresule.getGeneratedRuleId().startsWith(
						WebConstants.exclusionRuleIndicator)) {

					this.exclusionRuleList.add(ruleList.get(i));
				}
				if (locateresule.getGeneratedRuleId().startsWith(
						WebConstants.denielRuleIndicator)) {
					this.denialRuleList.add(ruleList.get(i));
				}
				if (locateresule.getGeneratedRuleId().startsWith(
						WebConstants.umRuleIndicator)) {
					this.umRuleList.add(ruleList.get(i));
				}
				if (locateresule.getGeneratedRuleId().startsWith(
						WebConstants.pnrRuleIndicaot)) {
					this.pnrRuleList.add(ruleList.get(i));
				}
			}
		}
	}

	/**
	 * @return Returns the blzRuleType.
	 */
	public String getBlzRuleType() {
		return blzRuleType;
	}

	/**
	 * @param blzRuleType The blzRuleType to set.
	 */
	public void setBlzRuleType(String blzRuleType) {
		this.blzRuleType = blzRuleType;
	}

	public void setContractID(String contractID) {
		this.contractID = contractID;
	}

	public String getContractID() {
		return contractID;
	}

	public void setBenefitComponentName(String benefitComponentName) {
		this.benefitComponentName = benefitComponentName;
	}

	public String getBenefitComponentName() {
		return benefitComponentName;
	}

	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}

	public String getBenefitName() {
		return benefitName;
	}

	public void setBenefitComponentId(String benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}

	public String getBenefitComponentId() {
		return benefitComponentId;
	}
	public boolean isHeaderRuleBCselected() {
		return headerRuleBCselected;
	}

	public void setHeaderRuleBCselected(boolean headerRuleBCselected) {
		this.headerRuleBCselected = headerRuleBCselected;
	}

	public void setHeaderRuleBenefitSelected(boolean headerRuleBenefitSelected) {
		this.headerRuleBenefitSelected = headerRuleBenefitSelected;
	}

	public boolean isHeaderRuleBenefitSelected() {
		return headerRuleBenefitSelected;
	}

	public void setCheckMode(String checkMode) {
		this.checkMode = checkMode;
	}

	public String getCheckMode() {
		return checkMode;
	}
}