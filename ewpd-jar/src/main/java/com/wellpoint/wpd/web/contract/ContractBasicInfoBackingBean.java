/*
 * ContractBasicInfoBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.owasp.esapi.ESAPI;

import com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerPopupDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerRuleIdDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerSPSIdDisplayVO;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.contract.bo.Contract;
import com.wellpoint.wpd.common.contract.bo.ContractBenefitComponentHeadings;
import com.wellpoint.wpd.common.contract.bo.ContractBenefitHeadings;
import com.wellpoint.wpd.common.contract.bo.ContractQuestUniqueReferenceBO;
import com.wellpoint.wpd.common.contract.bo.ContractStatusBo;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.contract.request.ContractTransferLogRequest;
import com.wellpoint.wpd.common.contract.request.ContractUncodedNotesRequest;
import com.wellpoint.wpd.common.contract.request.MembershipInfoRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveBasicInfoRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveBenefitHeadingsRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveBenefitLinesRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveCodedNonCodedBenefitHeadingsRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractBenefitDefinitionRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBasicInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractHeadingsRequest;
import com.wellpoint.wpd.common.contract.request.SaveTestDataRequest;
import com.wellpoint.wpd.common.contract.response.ContractTransferLogResponse;
import com.wellpoint.wpd.common.contract.response.ContractUncodedNotesResponse;
import com.wellpoint.wpd.common.contract.response.MembershipInfoResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBasicInfoResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBenefitHeadingsResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveBenefitLinesResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveCodedNonCodedBenefitHeadingsResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractBenefitDefinitionResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBasicInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractHeadingsResponse;
import com.wellpoint.wpd.common.contract.response.SaveTestDataResponse;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceDataImpl;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierCriteria;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.common.util.BenefitTierUtil;
import com.wellpoint.wpd.common.util.SessionCleanUp;
import com.wellpoint.wpd.common.util.WebServiceExcelGenerator;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class ContractBasicInfoBackingBean extends ContractBackingBean {

	private int version;
	List daterange;

	private String selectedIdFromSearch;

	private String lineOfBusinessDiv;

	private String businessEntityDiv;

	private String businessGroupDiv;

	/* START CARS */
	private String marketBusinessUnit;
	/* END CARS */

	private String contractIdDiv;

	private String startDate;

	private String endDate;

	private String contractType;

	private String baseContractIdDiv;

	private String baseContractDtDiv;

	private String groupSizeDiv;

	private String testDate;

	private String headQuartersState;

	private String product;

	private String printValue;

	private String checkoutValues;

	private String createdUser;

	private String updatedUser;

	private String state;

	private String status;

	private String selectedContractKeyFromSearch;

	private String selectedContractIdFromSearch;

	private String selectedVersionFromSearch;

	private String selectedStatusFromSearch;

	private String selectedDateSegmentIdFromSearch;

	private String hiddenDateSegmentIdForCopy;

	private String hiddenContractKeyForCopy;

	private String hiddenVersionForCopy;

	private String hiddenContractIdForCopy;

	private String hiddenStatusForCopy;

	private String productStatus;

	private String noteStatus;

	private String hiddenProductStatusForCopy;

	private String hiddenNoteStatusForCopy;

	private String hiddenLatestCopyStatus;

	private String selectedProductSysIdFromSearch;

	private String hiddenProductIdForCopy;

	private String contractIdExistingDiv;

	private HtmlInputHidden hiddenDateSegmentType = new HtmlInputHidden();

	private Date selectedEffectiveDate;

	private String loadList;

	private String copy = WebConstants.FALSE;

	private String printValueMembership;

	private String valueToMembership;

	private String breadCrumpText;

	private String logPageStatus;

	private List noteCheckoutList;

	private List productCheckoutList;

	private List validationMessages;

	private List testDateSegmentOptionList;

	private List benefitHeadingsList;

	private List benefitComponentList;

	private List productCopyList;

	private List noteCopyList;

	private List membershipList;

	private Date creationDate;

	private Date updationDate;

	private HtmlPanelGrid headerPanel = new HtmlPanelGrid();

	private HtmlPanelGrid panel = null;

	private Map benefitComponentMap = new HashMap();

	private Map standardBenefitMap = new HashMap();

	private boolean lobInvalid = false;

	private boolean entityInvalid = false;

	private boolean groupInvalid = false;

	private boolean contractIdInvalid = false;

	private boolean startDateInvalid = false;

	private boolean endDateInvalid = false;

	private boolean baseContractIdInvalid = false;

	private boolean contractTypeInvalid = false;

	private boolean baseContractDtInvalid = false;

	private boolean baseContractDtStandardInvalid = false;

	private boolean groupSizeInvalid = false;

	private boolean dateNotValid = false;

	private boolean testDateInvalid = false;

	private boolean requiredHeadQuarters = false;

	private boolean requiredProduct = false;

	private boolean existingContractIdInvalid;

	private boolean existingContractDtInvalid;

	private boolean setExistingTrue = false;

	private boolean forCopy = false;

	private boolean checkIn;

	private boolean notMandate = true;

	private boolean migCompletFlag = false;

	private String disableCheckBox = WebConstants.FALSE;

	private List codedNonCodedBenefitHeadingsList;

	private String pageLoadBasedOnContractType;

	boolean requestFromMigrationWizard = false;

	private boolean hasValidationErrors;

	private List deletedRulesList = null;

	private List unCodedRulesList = null;

	private String loadRuleValidationPopUp = null;
	//sscr 17571
	
	private String loadVendorPopUp = null;
	private String vendorFlag = "false";
	private String vendorMemberFlag = "false";

	private int init;
	private List searchDateSegmentList;
	private List searchVendorResultList;
	private String checkVendorInfoPopup="false";
	private String carvEffectiveDate;
	private String carvEndDate;
	  
	public String getCarvEffectiveDate() {
		return carvEffectiveDate;
	}
	public void setCarvEffectiveDate(String carvEffectiveDate) {
		this.carvEffectiveDate = carvEffectiveDate;
	}
	public String getCarvEndDate() {
		return carvEndDate;
	}
	public void setCarvEndDate(String carvEndDate) {
		this.carvEndDate = carvEndDate;
	}
	public List getSearchDateSegmentList() {
		return searchDateSegmentList;
	}
	public void setSearchDateSegmentList(List searchDateSegmentList) {
		this.searchDateSegmentList = searchDateSegmentList;
	}
	public String getCheckVendorInfoPopup() {
		return checkVendorInfoPopup;
	}
	public void setCheckVendorInfoPopup(String checkVendorInfoPopup) {
		this.checkVendorInfoPopup = checkVendorInfoPopup;
	}
	public List getSearchVendorResultList() {
		return searchVendorResultList;
	}
	public void setSearchVendorResultList(List searchVendorResultList) {
		this.searchVendorResultList = searchVendorResultList;
	}
	
	public String getVendorMemberFlag() {
		return vendorMemberFlag;
	}
	public void setVendorMemberFlag(String vendorMemberFlag) {
		this.vendorMemberFlag = vendorMemberFlag;
	}

	private String closePopup = "false";
	  private String dateSegment ;

	public String getDateSegment() {
		return dateSegment;
	}
	public void setDateSegment(String dateSegment) {
		this.dateSegment = dateSegment;
	}
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

	private Map<String,List> vendorMap;
	//sscr 17571-end
	// TransferLog Initialization
	private boolean viewMoreFlag = false;

	private List contractTransferLogFullList;

	private List contractTransferResultList;

	private String transferLog;

	private List contractTransferLogHalfList;

	private String breadCrumpTextForTransferLogPrint;

	private String transferLogPageStatus;

	private String isViewTwenty = "false";

	private String lob;

	private String be;

	private String bg;
	/* START CARS */
	private String mbu;
	/* END CARS */

	// STANDARD
	private String baseContractIdDivNonMaditory;

	private String baseContractDtDivNonMaditory;

	// CR##### Contract Refrence validation popup
	private String loadContractDuplicateReference;

	private List duplicateBenefitLevelReferenceList = new ArrayList();

	private List duplicateQuestionReferenceList = new ArrayList();

	private String contractId;

	private List messageListForUniqueReference = new ArrayList();

	private String loadUncodedNotes;

	private List lineNotesList = new ArrayList();

	private List questionNotesList = new ArrayList();

	private List prdQuestNotesList = new ArrayList();

	private HtmlPanelGrid tierNotePanel = null;

	private HtmlPanelGrid headerPanelForTierLines = null;

	private HtmlPanelGrid tierQuestNotePanel = null;

	private HtmlPanelGrid headerPanelForTierQuestLines = null;

	private String oldCriteria = WebConstants.EMPTY_STRING;
	private String oldTierDesc = WebConstants.EMPTY_STRING;

	// private String loadUncodedNotesForPrint = WebConstants.EMPTY_STRING;
	private HtmlInputHidden loadUncodedNotesForPrint = new HtmlInputHidden();

	private boolean isPrintMode = false;

	/* START CARS */
	private boolean requiredMarketBusinessUnit = false;
	/* END CARS */

	// added for status change
	private String contractStatus;

	private int contractStatusInedx;

	private String contractStatusDate;

	private String contractStatusReasonCode;

	private boolean isNewContractStatus = false;

	private String contractStatusReasonCodeDesc;

	private String termedContractId;

	private boolean statusResonCodeInvalid = false;

	private boolean contractStatusDateInvalid = false;

	private boolean contractStatusInvalid = false;

	// eBX error validation

	private String invokeWebService="";
	
	
	// SSCR 16332  -START
	private List<EbxWebSerDisplayVO> ebxWebSerDisplayList = null;
	private int initEbxWs;
	private String webServiceFlag ="false";
	private int initEbxRuleId;
	private int initEbxSPSId;
	private List<EbxWebSerRuleIdDisplayVO> ebxWebSerRuleIdDisplayLst = null;
	private List<EbxWebSerSPSIdDisplayVO> ebxWebSerSPSIdDisplayLst = null;
	private String ruleId;
	private String spsId;
	EbxWebSerPopupDisplayVO ebxWebSerPopupDisplayVO;
	private String ebxProcessInterruptMsg = null;
	
	
	
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
		// SSCR 17571 - Tab impl ebx or vendor info tab hiding
        if(null != getSession().getAttribute("VendorFlag")){
                this.vendorFlag = (String)getSession().getAttribute("VendorFlag");
        }
        if(null != getSession().getAttribute("WebServiceFlag")){
            this.webServiceFlag = (String)getSession().getAttribute("WebServiceFlag");
		 }
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
			this.setRuleId(ruleId);
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
	
	
	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
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
	public String getEbxProcessInterruptMsg() {
		return ebxProcessInterruptMsg;
	}
	public void setEbxProcessInterruptMsg(String ebxProcessInterruptMsg) {
		this.ebxProcessInterruptMsg = ebxProcessInterruptMsg;
	}

	// SSCR 16332 - END
	/**
	 * @return the invokeWebService
	 */
	public String getInvokeWebService() {
		return invokeWebService;
	}

	/**
	 * @param invokeWebService
	 *            the invokeWebService to set
	 */
	public void setInvokeWebService(String invokeWebService) {
		this.invokeWebService = invokeWebService;
	}
	
	public String getTermedContractId() {
		return termedContractId;
	}

	public void setTermedContractId(String termedContractId) {
		this.termedContractId = termedContractId;
	}

	public String getContractStatusReasonCodeDesc() {
		return contractStatusReasonCodeDesc;
	}

	public void setContractStatusReasonCodeDesc(
			String contractStatusReasonCodeDesc) {
		this.contractStatusReasonCodeDesc = contractStatusReasonCodeDesc;
	}

	public boolean isNewContractStatus() {
		return isNewContractStatus;
	}

	public void setNewContractStatus(boolean isNewContractStatus) {
		this.isNewContractStatus = isNewContractStatus;
	}

	/**
	 * 
	 * Constructor
	 */
	public ContractBasicInfoBackingBean() {
		super();
		this.lineOfBusinessDiv = WebConstants.MEDICAL;
		this.startDate = WebConstants.DATE_1900;
		this.endDate = WebConstants.DEFAULT_EXP_DATE;
		this.setBreadCrumbText();

	}

	/**
	 * This Method is used to download excel sheet
	 * 
	 */

	public void downloadExcel() throws IOException {
		ContractKeyObject contractKeyObject = getContractSession()
				.getContractKeyObject();
		
		ContractWebServiceVO contract = new ContractWebServiceVO();
		contract.setSystem("EWPD");
		contract.setContractId(contractKeyObject.getContractId());
		contract.setEffectiveDate(contractKeyObject.getEffectiveDate());
		contract.setVersion(contractKeyObject.getVersion());
		
		List<ContractWebServiceVO> ebxWebSerDisplayVO = new ArrayList<ContractWebServiceVO>();
		ebxWebSerDisplayVO = (List<ContractWebServiceVO>) getSession().getAttribute("contractWSErrorList");
		
		//WebServiceExcelGeneratorWPD builder = new WebServiceExcelGeneratorWPD();

		//XSSFWorkbook wb = null;
		
		WebServiceExcelGenerator builder = new WebServiceExcelGenerator();
		XSSFWorkbook wb = null;
		if(null != ebxWebSerDisplayVO && !ebxWebSerDisplayVO.isEmpty() && null != ebxWebSerDisplayVO.get(0)){
			try {
				wb = builder.buildExcelDocument(ebxWebSerDisplayVO.get(0), "Test");
				//wb = builder.buildExcelDocument(ebxWebSerDisplayVO.get(0), targetSystem);
			}catch (Exception e) {e.printStackTrace();}
		}
		//DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
       // Date date = new Date();
        String newDate = contractKeyObject.getEffectiveDate().replaceAll("/", "-");
        String fileName = "Error Report_" + contract.getContractId()+ "_"+ newDate + ".xlsx";
 
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        HttpServletResponse response = (HttpServletResponse)ec.getResponse();

		response.reset(); // Some JSF component library or some Filter might have set some 
							//headers in the buffer beforehand. We want to get rid of them, 
								//else it may collide.
        
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");// Check http://www.w3schools.com/media/media_mimeref.asp for all types. 
        																								//Use if necessary ExternalContext#getMimeType() for auto-detection based on filename. 

        // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.
        //response.setContentLength(contentLength); 

        // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); 
        OutputStream output = null;
        try{
	        output = response.getOutputStream();
	        wb.write(output); 
	        output.flush();
	        output.close();
        }catch(Exception e){
        	Logger.logDebug(e);
        }finally {
        	if(output != null){
        		output.close();
        	}
        }
        fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
        //System.out.println("END");
        Logger.logInfo("END");
	}

	/**
	 * This Method is used to save the Test Effective Date
	 * 
	 * @return
	 */
	public String saveTestInfo() {
		validationMessages = new ArrayList<ErrorMessage>();
		Date dateStd = WPDStringUtil.getDateFromString("01/01/1900");
		Date dateTestDt = WPDStringUtil.getDateFromString(this.testDate);
		int dateTestValid = 0;
		if (null != dateTestDt)
			dateTestValid = dateTestDt.compareTo(dateStd);
		if (this.testDate == null
				|| WebConstants.EMPTY_STRING.equals(this.testDate)) {
			this.testDateInvalid = true;
			validationMessages.add(new ErrorMessage(
					WebConstants.MANDATORY_FIELDS_REQUIRED));
			addAllMessagesToRequest(this.validationMessages);
		} else if ((!(WPDStringUtil.isValidDate(this.testDate)))
				|| (dateTestValid < 0)) {
			ErrorMessage errorMessage = new ErrorMessage(
					WebConstants.INPUT_FORMAT_INVALID);
			errorMessage
					.setParameters(new String[] { WebConstants.TEST_EFFECTIVE_DATE });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(this.validationMessages);
		} else {

			SaveTestDataRequest saveTestDataRequest = (SaveTestDataRequest) this
					.getServiceRequest(ServiceManager.SAVE_TEST_DATA_REQUEST);
			saveTestDataRequest.setTestDate(dateTestDt);
			ContractKeyObject keyObject = new ContractKeyObject();
			keyObject.setDateSegmentId(getContractSession()
					.getContractKeyObject().getDateSegmentId());
			keyObject.setContractId(getContractSession().getContractKeyObject()
					.getContractId());
			keyObject.setVersion(getContractSession().getContractKeyObject()
					.getVersion());
			keyObject.setStatus(getContractSession().getContractKeyObject()
					.getStatus());
			saveTestDataRequest
					.setAction(SaveTestDataRequest.PERSIST_TEST_DATA);
			saveTestDataRequest.setContractKeyObject(keyObject);
			if (!(getContractSession().getContractKeyObject()
					.isTestDateupdate())) {
				saveTestDataRequest.setInsertFlag(true);
			} else {
				saveTestDataRequest.setInsertFlag(false);
			}
			SaveTestDataResponse saveTestDataResponse = (SaveTestDataResponse) executeService(saveTestDataRequest);

			if ((null != saveTestDataResponse)
					&& (saveTestDataResponse.isSuccess())) {
				getContractSession().getContractKeyObject().setTestDateupdate(
						true);

			}
		}
		return WebConstants.EMPTY_STRING;
	}

	/**
	 * This Methode is used to retrieve the Membership Information of a Contract
	 * 
	 * @return
	 */
	public String getMembershipInfo() {
		if (super.isViewMode()) {
			return "MEMBERSHIPINFOVIEW";
		}
		return "MEMBERSHIPINFO";
	}

	/**
	 * This Methode is used to Save the Basic Information of a Contract
	 * 
	 * @return
	 */
	public String saveBasicInfo() {
		// it first checks for the validation of all the fields.
		if (!super.isEditMode()
		// && getContractSession().getMode() != ContractSession.COPY_MODE
		// && getContractSession().getMode() !=
		// ContractSession.COPY_HEADINGS_MODE
		) {
			// Setting Active for new Contract
			contractStatus = WebConstants.CNTRT_STATUS_ACTIVE;
		}
		if (isRequiredFieldsValid()) {
			// request is created
			SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
					.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);

			// sets action to the request based on the mode
			if (super.isEditMode()) {
				saveContractBasicInfoRequest
						.setAction(SaveContractBasicInfoRequest.UPDATE_CONTRACT);
			} else if (getContractSession().getMode() == ContractSession.COPY_MODE) {
				saveContractBasicInfoRequest
						.setAction(SaveContractBasicInfoRequest.COPY_CONTRACT);
				int baseDateId = ((Integer) getSession().getAttribute(
						WebConstants.BASE_DATE_ID)).intValue();
				saveContractBasicInfoRequest.setBaseDateIdForCopy(baseDateId);
				String productId = getSession().getAttribute(
						WebConstants.PRODUCTID).toString();
				saveContractBasicInfoRequest.setProductSysId(Integer
						.parseInt(productId));
				saveContractBasicInfoRequest
						.setDateSegmentIdForCopy(getContractSession()
								.getContractKeyObject().getDateSegmentId());
				saveContractBasicInfoRequest
						.setProductStatusForCopy((String) getSession()
								.getAttribute(WebConstants.PRODUCT_STATUS));
				saveContractBasicInfoRequest
						.setNoteStatusForCopy((String) getSession()
								.getAttribute(WebConstants.NOTE_STATUS));
			} else if (getContractSession().getMode() == ContractSession.COPY_HEADINGS_MODE) {
				saveContractBasicInfoRequest
						.setAction(SaveContractBasicInfoRequest.COPY_HEADINGS_CONTRACT);
				int baseDateId = ((Integer) getSession().getAttribute(
						WebConstants.BASE_DATE_ID)).intValue();
				saveContractBasicInfoRequest.setBaseDateIdForCopy(baseDateId);
				String productId = getSession().getAttribute(
						WebConstants.PRODUCTID).toString();
				saveContractBasicInfoRequest.setProductSysId(Integer
						.parseInt(productId));
				saveContractBasicInfoRequest
						.setDateSegmentIdForCopy(getContractSession()
								.getContractKeyObject().getDateSegmentId());
				saveContractBasicInfoRequest
						.setSelectedLineList((List) getSession().getAttribute(
								WebConstants.SELECTED_LINE_LIST));
				saveContractBasicInfoRequest
						.setProductStatusForCopy((String) getSession()
								.getAttribute(WebConstants.PRODUCT_STATUS));
				saveContractBasicInfoRequest
						.setNoteStatusForCopy((String) getSession()
								.getAttribute(WebConstants.NOTE_STATUS));
				saveContractBasicInfoRequest
						.setUnSelectedLineList((List) getSession()
								.getAttribute(WebConstants.UNSELECTED_LINE_LIST));

			} else
				saveContractBasicInfoRequest
						.setAction(SaveContractBasicInfoRequest.CREATE_CONTRACT);

			// setting the values to request
			saveContractBasicInfoRequest = setValuesToRequest(saveContractBasicInfoRequest);

			SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
			// calls the service
			saveContractBasicInfoResponse = (SaveContractBasicInfoResponse) executeService(saveContractBasicInfoRequest);
			updateTreeStructure();
			if (null != saveContractBasicInfoResponse
					&& null != saveContractBasicInfoResponse.getContract()) {
				if (saveContractBasicInfoResponse.isSuccess()) {
					// Setting the key values of contract to session.
					super.setEditMode();
					// sets the values back to the backing bean
					setValuesToBackingBean(saveContractBasicInfoResponse
							.getContract(), saveContractBasicInfoResponse
							.getDomainDetail());

					super.setContractToSession(saveContractBasicInfoResponse
							.getContract());
					getRequest().setAttribute("RETAIN_Value", "");
					return WebConstants.EDIT_PAGE;
				}
			}

		} else {
			addAllMessagesToRequest(this.validationMessages);
		}
		getRequest().setAttribute("RETAIN_Value", "");
		return WebConstants.EMPTY_STRING;
	}

	/**
	 * This method is used for setting the values to the request
	 * 
	 * @param saveContractBasicInfoRequest
	 * @return
	 */
	private SaveContractBasicInfoRequest setValuesToRequest(
			SaveContractBasicInfoRequest saveContractBasicInfoRequest) {
		List lobCodeList;
		List businessEntityCodeList;
		List businessGroupCodeList;
		// List contractTypeList;

		// Saving new data

		// Calling the function to get a list of values from a tilda string.

		lobCodeList = WPDStringUtil.getListFromTildaString(this
				.getLineOfBusinessDiv(), 2, 2, 2);
		businessEntityCodeList = WPDStringUtil.getListFromTildaString(this
				.getBusinessEntityDiv(), 2, 2, 2);
		businessGroupCodeList = WPDStringUtil.getListFromTildaString(this
				.getBusinessGroupDiv(), 2, 2, 2);
		/* START CARS */
		List marketBusinessUnitCodeList = WPDStringUtil.getListFromTildaString(
				this.getMarketBusinessUnit(), 2, 2, 2);

		List domainList = BusinessUtil.convertToDomains(lobCodeList,
				businessEntityCodeList, businessGroupCodeList,
				marketBusinessUnitCodeList);
		/* END CARS */

		ContractVO contractVO = new ContractVO();
		contractVO.setContractType(this.contractType);
		if (!(super.isEditMode())) {
			List contractIdList = WPDStringUtil.getListFromTildaString(
					this.contractIdDiv, 2, 1, 2);
			if (0 != contractIdList.size())
				contractVO.setContractId(contractIdList.get(0).toString());
		}
		if (WebConstants.CUSTOM.equals(this.contractType)
				&& (!(super.isEditMode()))) {
			List baseContractIdList = WPDStringUtil.getListFromTildaString(
					this.baseContractIdDiv, 2, 1, 2);
			List baseContractSysIdList = WPDStringUtil.getListFromTildaString(
					this.baseContractIdDiv, 2, 2, 2);
			List baseDateSegmentDateList = WPDStringUtil
					.getListFromTildaString(this.baseContractDtDiv, 2, 1, 2);
			List baseDateSegmentList = WPDStringUtil.getListFromTildaString(
					this.baseContractDtDiv, 2, 2, 2);
			contractVO.setBaseContractId(baseContractIdList.get(0).toString());
			contractVO.setBaseContractSysId(Integer
					.parseInt(baseContractSysIdList.get(0).toString()));
			contractVO.setBaseDateSegmentSysId(Integer
					.parseInt(baseDateSegmentList.get(0).toString()));
			contractVO.setBaseContractDate(WPDStringUtil
					.getDateFromString(baseDateSegmentDateList.get(0)
							.toString()));
			contractVO.setLobList(lobCodeList);
			contractVO.setBeList(businessEntityCodeList);
			contractVO.setBgList(businessGroupCodeList);
			/* START CARS */
			contractVO.setMbuList(marketBusinessUnitCodeList);
			/* END CARS */
		} else if (WebConstants.STANDAR.equals(this.contractType)
				&& (!(super.isEditMode()))
				&& (!(this.baseContractDtDivNonMaditory)
						.equals(WebConstants.EMPTY_STRING))
				&& (!(this.baseContractIdDivNonMaditory)
						.equals(WebConstants.EMPTY_STRING))) {
			List baseContractIdList = WPDStringUtil.getListFromTildaString(
					this.baseContractIdDivNonMaditory, 2, 1, 2);
			List baseContractSysIdList = WPDStringUtil.getListFromTildaString(
					this.baseContractIdDivNonMaditory, 2, 2, 2);
			List baseDateSegmentDateList = WPDStringUtil
					.getListFromTildaString(this.baseContractDtDivNonMaditory,
							2, 1, 2);
			List baseDateSegmentList = WPDStringUtil.getListFromTildaString(
					this.baseContractDtDivNonMaditory, 2, 2, 2);
			contractVO.setBaseContractId(baseContractIdList.get(0).toString());
			contractVO.setBaseContractSysId(Integer
					.parseInt(baseContractSysIdList.get(0).toString()));
			contractVO.setBaseDateSegmentSysId(Integer
					.parseInt(baseDateSegmentList.get(0).toString()));
			contractVO.setBaseContractDate(WPDStringUtil
					.getDateFromString(baseDateSegmentDateList.get(0)
							.toString()));
			contractVO.setLobList(lobCodeList);
			contractVO.setBeList(businessEntityCodeList);
			contractVO.setBgList(businessGroupCodeList);
			/* START CARS */
			contractVO.setMbuList(marketBusinessUnitCodeList);
			/* END CARS */
		}

		contractVO.setEffectiveDate(WPDStringUtil
				.getDateFromString(this.startDate));
		contractVO.setExpiryDate(WPDStringUtil.getDateFromString(this.endDate));
		contractVO.setDomainList(domainList);
		if (!(this.groupSizeDiv == null || WebConstants.EMPTY_STRING
				.equals(this.groupSizeDiv))) {
			List groupSizeList = WPDStringUtil.getListFromTildaString(
					this.groupSizeDiv, 2, 1, 2);
			List groupSizeDescList = WPDStringUtil.getListFromTildaString(
					this.groupSizeDiv, 2, 2, 2);
			contractVO.setGroupSize(groupSizeList.get(0).toString());
			contractVO.setGroupSizeDesc(groupSizeDescList.get(0).toString());
		}
		if (WebConstants.MANDATE.equalsIgnoreCase(this.contractType)
				&& (!(super.isEditMode()))) {
			List headquartersDescList = WPDStringUtil.getListFromTildaString(
					this.headQuartersState, 2, 2, 2);
			List headquartersCodeList = WPDStringUtil.getListFromTildaString(
					this.headQuartersState, 2, 1, 2);
			contractVO.setHeadQuartersStateDesc(headquartersDescList.get(0)
					.toString());
			contractVO.setHeadQuartersStateCode(headquartersCodeList.get(0)
					.toString());
			List productCodeList = WPDStringUtil.getListFromTildaString(
					this.product, 2, 1, 1);
			List productDescList = WPDStringUtil.getListFromTildaString(
					this.product, 2, 2, 2);
			contractVO.setProduct(productDescList.get(0).toString());
			contractVO.setProductSysId(((Integer) productCodeList.get(0))
					.intValue());
		}
		ContractKeyObject contractKeyObject = getContractSession()
				.getContractKeyObject();
		if (!(isCopyMode() || isCopyHeadingsMode())) {
			if (null != contractKeyObject) {
				contractVO.setContractId(contractKeyObject.getContractId());
				contractVO.setContractSysId(contractKeyObject
						.getContractSysId());
				contractVO.setParentSysId(contractKeyObject
						.getContractParentSysId());
				contractVO.setVersion(contractKeyObject.getVersion());
				contractVO.setStatus(contractKeyObject.getStatus());
				contractVO.setDateSegmentSysId(contractKeyObject
						.getDateSegmentId());
			}
		}
		if (null != contractKeyObject && (isCopyMode() || isCopyHeadingsMode())) {
			contractVO.setContractIdForCopy(contractKeyObject.getContractId());
		}
		// ArrayList dateSegmentList = new ArrayList();

		ContractStatusBo contractStatusBo = new ContractStatusBo();
		contractStatusBo.setContractId(contractIdDiv);
		contractStatusBo.setNewlyCreated(isNewContractStatus);
		if (SaveContractBasicInfoRequest.CREATE_CONTRACT == saveContractBasicInfoRequest
				.getAction()) {
			contractStatusBo.setStatusCode(WebConstants.CNTRT_STATUS_ACTIVE);
		} else {
			contractStatusBo.setStatusCode(WPDStringUtil
					.hasText(contractStatus) ? contractStatus
					: WebConstants.CNTRT_STATUS_ACTIVE);
		}
		if (WebConstants.CNTRT_STATUS_ACTIVE.equals(contractStatusBo
				.getStatusCode())) {
			contractStatusBo.setReasonCode(null);
			contractStatusBo.setStatusDate(null);
		} else {
			contractStatusBo.setReasonCode(contractStatusReasonCode.trim());
			Date sDate = null;
			try {
				if (WPDStringUtil.hasText(contractStatusDate)) {
					sDate = new SimpleDateFormat("MM/dd/yyyy")
							.parse(contractStatusDate);
				}
			} catch (java.text.ParseException e) {

			}
			contractStatusBo.setStatusDate(sDate);
		}
		contractStatusBo.setTermedContractId(getTermedContractIdString());
		contractStatusBo.setPersist(true);
		contractVO.setContractStatusBo(contractStatusBo);
		saveContractBasicInfoRequest.setContractVO(contractVO);
		return saveContractBasicInfoRequest;

	}

	private String getTermedContractIdString() {

		if (!WPDStringUtil.hasText(termedContractId)) {
			return null;
		}
		return termedContractId.trim();
		// String[] tCntrtId = termedContractId.split("~");
		// return WPDStringUtil.hasText(tCntrtId[1])? tCntrtId[1] :null;
	}

	/**
	 * This methode is used to set Values to Backing Bean
	 * 
	 * @return
	 */
	private void setValuesToBackingBean(Contract contract,
			DomainDetail domainDetail) {

		if (!(isCopyMode() || isCopyHeadingsMode()))
			this.contractIdDiv = contract.getContractId();
		this.contractType = contract.getContractType();
		if (WebConstants.MNDT_TYPE.equals(contract.getContractType())) {
			notMandate = false;
		}

		if (null != contract.getState()) {
			this.setState(contract.getState().getState());
		}
		this.setStatus(contract.getStatus());
		this.setVersion(contract.getVersion());

		DateSegment dateSegment = (DateSegment) contract.getDateSegmentList()
				.get(0);

		if (!(isCopyMode() || isCopyHeadingsMode())) {
			this.baseContractIdDiv = dateSegment.getBaseContractId();
			this.baseContractIdDivNonMaditory = dateSegment.getBaseContractId();
		} else {
			if (null != dateSegment.getBaseContractId()) {
				this.baseContractIdDiv = dateSegment.getBaseContractId()
						+ WebConstants.TILDA
						+ dateSegment.getBaseContractSysId();
				this.baseContractIdDivNonMaditory = dateSegment
						.getBaseContractId()
						+ WebConstants.TILDA
						+ dateSegment.getBaseContractSysId();
			} else {
				this.baseContractIdDiv = WebConstants.EMPTY_STRING;
				this.baseContractIdDivNonMaditory = WebConstants.EMPTY_STRING;
			}
		}
		this.createdUser = dateSegment.getCreatedUser();
		this.creationDate = dateSegment.getCreatedTimestamp();
		this.updatedUser = dateSegment.getLastUpdatedUser();
		this.updationDate = dateSegment.getLastUpdatedTimestamp();
		if (!(isCopyMode() || isCopyHeadingsMode())) {
			this.baseContractDtDiv = WPDStringUtil.getStringDate(dateSegment
					.getBaseContractDate());
			this.baseContractDtDivNonMaditory = WPDStringUtil
					.getStringDate(dateSegment.getBaseContractDate());
		} else {
			if (null != dateSegment.getBaseContractDate()) {
				this.baseContractDtDiv = WPDStringUtil
						.getStringDate(dateSegment.getBaseContractDate())
						+ WebConstants.TILDA
						+ dateSegment.getBaseDateSegmentSysId();
				this.baseContractDtDivNonMaditory = WPDStringUtil
						.getStringDate(dateSegment.getBaseContractDate())
						+ WebConstants.TILDA
						+ dateSegment.getBaseDateSegmentSysId();
			} else {
				this.baseContractDtDiv = WebConstants.EMPTY_STRING;
				this.baseContractIdDivNonMaditory = WebConstants.EMPTY_STRING;
			}
		}

		if (null != dateSegment.getGroupSize())
			this.groupSizeDiv = dateSegment.getGroupSize() + WebConstants.TILDA
					+ dateSegment.getGroupSizeDesc();
		if (null != dateSegment.getProductDesc())
			this.product = Integer.toString(dateSegment.getProductId())
					+ WebConstants.TILDA + dateSegment.getProductDesc();
		if (dateSegment.getHeadQuartersState() != null)
			if (super.isEditMode() || super.isViewMode()) {
				this.setHeadQuartersState(dateSegment
						.getHeadQuartersStateDesc());
			} else {
				this.setHeadQuartersState(dateSegment.getHeadQuartersState()
						+ WebConstants.TILDA
						+ dateSegment.getHeadQuartersStateDesc());
			}
		if (!(isCopyMode() || isCopyHeadingsMode()))
			this.startDate = WPDStringUtil.getStringDate(dateSegment
					.getEffectiveDate());
		else
			this.startDate = WebConstants.DATE_1900;

		if (!(isCopyMode() || isCopyHeadingsMode()))
			this.endDate = WPDStringUtil.getStringDate(dateSegment
					.getExpiryDate());
		else
			this.endDate = WebConstants.DEFAULT_EXP_DATE;

		if (domainDetail != null) {
			this.lineOfBusinessDiv = WPDStringUtil.getTildaString(domainDetail
					.getLineOfBusiness());
			this.businessEntityDiv = WPDStringUtil.getTildaString(domainDetail
					.getBusinessEntity());
			this.businessGroupDiv = WPDStringUtil.getTildaString(domainDetail
					.getBusinessGroup());
			/* START CARS */
			this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
					.getMarketBusinessUnit());
			/* END CARS */
		}

		setContractStatusDetails(dateSegment);
	}

	private void setContractStatusDetails(DateSegment dateSegment) {
		// TODO:
		if (null == dateSegment || null == dateSegment.getContractStatusBo()) {
			this.setContractStatus(WebConstants.CNTRT_STATUS_ACTIVE);
			this.setContractStatusInedx(0);
			this.setContractStatusReasonCode("");
			this.setContractStatusDate("");
			this.setNewContractStatus(true);
			this.setContractStatusReasonCodeDesc("");
			this.setTermedContractId("");
			return;
		}
		ContractStatusBo statusBo = dateSegment.getContractStatusBo();

		this.setContractStatus(statusBo.getStatusCode());
		this.setContractStatusInedx(WebConstants.CNTRT_STATUS_ACTIVE
				.equals(statusBo.getStatusCode()) ? 0
				: WebConstants.CNTRT_STATUS_INACTIVE.equals(statusBo
						.getStatusCode()) ? 1 : 2);
		this.setContractStatusReasonCode(WPDStringUtil.hasText(statusBo
				.getReasonCode()) ? statusBo.getReasonCode() : "");
		this
				.setContractStatusDate(null != statusBo.getStatusDate() ? WPDStringUtil
						.convertDateToString(statusBo.getStatusDate())
						: "");
		this.setNewContractStatus(false);
		this.setContractStatusReasonCodeDesc(WPDStringUtil.hasText(statusBo
				.getReasonCodeDescription()) ? statusBo
				.getReasonCodeDescription() : "");
		this.setTermedContractId(WPDStringUtil.hasText(statusBo
				.getTermedContractId()) ? statusBo.getTermedContractId() : "");

	}

	/**
	 * Action Methode for Contract Checkout
	 * 
	 * @return
	 */
	public String checkOut() {
		SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
				.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
		SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
		saveContractBasicInfoRequest.getContractVO().setContractId(
				this.getSelectedContractIdFromSearch());
		saveContractBasicInfoRequest.getContractVO().setContractSysId(
				Integer.parseInt(this.getSelectedContractKeyFromSearch()));
		saveContractBasicInfoRequest.getContractVO().setVersion(
				Integer.parseInt(this.getSelectedVersionFromSearch()));
		saveContractBasicInfoRequest.getContractVO().setStatus(
				this.getSelectedStatusFromSearch());
		saveContractBasicInfoRequest.getContractVO().setProductSysId(
				Integer.parseInt(this.getSelectedProductSysIdFromSearch()));
		saveContractBasicInfoRequest.getContractVO().setProductStatus(
				this.productStatus);
		// saveContractBasicInfoRequest.getContractVO().setDateEntered(this.selectedEffectiveDate);
		saveContractBasicInfoRequest.getContractVO().setDateSegmentSysId(
				Integer.parseInt(this.selectedDateSegmentIdFromSearch));
		String noteArray[] = noteStatus.split(WebConstants.TILDA);
		int size = noteArray.length;
		List dateSegmentList = new ArrayList();
		for (int i = 1; i < size; i++) {
			String effectiveDate = noteArray[i];
			if (WPDStringUtil.isValidDate(effectiveDate)) {
				DateSegment dateSegment = new DateSegment();
				dateSegment.setEffectiveDate(WPDStringUtil
						.getDateFromString(noteArray[i]));
				dateSegmentList.add(dateSegment);
				saveContractBasicInfoRequest.getContractVO().setNoteStatus(
						WebConstants.REPLACE_NOTE);
			}
		}
		saveContractBasicInfoRequest.getContractVO().setDateSegmentList(
				dateSegmentList);
		saveContractBasicInfoRequest
				.setAction(SaveContractBasicInfoRequest.CHECKOUT_CONTRACT);
		saveContractBasicInfoResponse = (SaveContractBasicInfoResponse) executeService(saveContractBasicInfoRequest);
		updateTreeStructure();
		if (saveContractBasicInfoResponse != null) {
			if (saveContractBasicInfoResponse.isSuccess()) {
				super.setEditMode();
				setValuesToBackingBean(saveContractBasicInfoResponse
						.getContract(), saveContractBasicInfoResponse
						.getDomainDetail());
				super.setContractToSession(saveContractBasicInfoResponse
						.getContract());
				return WebConstants.EDITCONTRACT;
			}
			List messageList = saveContractBasicInfoResponse.getMessages();
			ContractSearchBackingBean contractSearchBackingBean = (ContractSearchBackingBean) getRequest()
					.getAttribute("contractSearchBackingBean");
			contractSearchBackingBean.performLocate();
			addAllMessagesToRequest(messageList);
			return WebConstants.EMPTY_STRING;
		}
		return WebConstants.EMPTY_STRING;

	}

	// kritika Added
	private String newComments;
	private List msgListOne;
	
	public String getNewComments() {
		return newComments;
	}
	/**
	 * Sets the newComments
	 * @param newComments.
	 */
	public void setNewComments(String newComments) {
		this.newComments = newComments;
	}
	
	public List getMsgListOne() {
		return msgListOne;
	}
	/**
	 * @param msgListOne The msgListOne to set.
	 */
	public void setMsgListOne(List msgListOne) {
		this.msgListOne = msgListOne;
	}
	
	
	
	/**
	 * Action methode for Contract Checkin
	 * 
	 * @return
	 */
	public String done() {
		if (isRequiredFieldsValid()) {
			
			SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
					.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
			saveContractBasicInfoRequest = setValuesToRequest(saveContractBasicInfoRequest);
			// SSCR 16332 -Start
			if (this.checkIn && !getInvokeWebService().equals("confirm") && getInvokeWebService() != null) {
				saveContractBasicInfoRequest.setEBXWS(true);
				saveContractBasicInfoRequest.setCarvConfirm(true);
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
			SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;

			List msgListOne = new ArrayList();
			List msgListTwo = new ArrayList();

			saveContractBasicInfoRequest
					.setAction(SaveContractBasicInfoRequest.UPDATE_CHECKIN_CONTRACT);
			saveContractBasicInfoRequest.setChechIn(this.checkIn);
			if (null != getSession().getAttribute("AM_BENEFIT"))
				getSession().removeAttribute("AM_BENEFIT"); // clearing
			// for
			// adminmethod
			// contract
			// validation
			// popup
			if (null != getSession().getAttribute("AM_BC_KEY"))
				getSession().removeAttribute("AM_BC_KEY"); // clearing
			// for
			// adminmethod
			// contract
			// validation
			// popup
			if (null != getSession().getAttribute("DIRECT_CLICK"))
				getSession().removeAttribute("DIRECT_CLICK"); // clearing for
			// adminmethod
			// contract
			// validation
			saveContractBasicInfoResponse = (SaveContractBasicInfoResponse) executeService(saveContractBasicInfoRequest);
			if (null != saveContractBasicInfoResponse) {
				if (saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_RESULTS) {
					hasValidationErrors = true;
					setValuesForAdminMethodValidation();
					getRequest().setAttribute("RETAIN_Value", "");
					return "";
				} else if (saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.SPS_VALIDATION_ERROR) {
					setValuesForAdminMethodValidation();
					if (null != getSession().getAttribute(
							"adminmethodContractCodedSPSTreeBackingBean"))
						getSession().removeAttribute(
								"adminmethodContractCodedSPSTreeBackingBean");
					return "";
				} else if (saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_WAIT) {
					getSession().setAttribute(
							WebConstants.OBJECT_KEY_FOR_CHECKIN,
							"contractBasicInfoBackingBean");
					getSession().setAttribute(
							WebConstants.OBJECT_VALUE_FOR_CHECKIN, this);
					getSession()
							.setAttribute(
									WebConstants.ENTITY_ID_FOR_CHECKIN,
									new Integer(saveContractBasicInfoRequest
											.getContractKeyObject()
											.getDateSegmentId()));
					getSession()
							.setAttribute(
									WebConstants.CONTRACT_ID_FOR_CHECKIN,
									new Integer(saveContractBasicInfoRequest
											.getContractKeyObject()
											.getContractSysId()));
					getSession().setAttribute(
							WebConstants.ENTITY_TYPE_FOR_CHECKIN,
							WebConstants.ENTITY_TYPE_CONTRACT);
					getSession().setAttribute(WebConstants.ACTION_FOR_CHECKIN,
							new Integer(1));
					getSession().setAttribute(
							"AM_ENTITY_NAME",
							getContractSession().getContractKeyObject()
									.getContractId());
					return "validationWait";
				} else if(saveContractBasicInfoResponse.getCondition() == SaveProductResponse.DO_EBX_WEBSERVICE_PROCESS || saveContractBasicInfoResponse.getCarvedOutCondition() == SaveProductResponse.DO_CARVEDOUT_PROCESS ){
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
					return "EDITCONTRACT";
				}else {
					if (null != saveContractBasicInfoResponse.getMessages()) {
						// Rule Validation
						getSession().setAttribute(
								WebConstants.SESSION_DELETED_RULES_LIST,
								saveContractBasicInfoResponse
										.getDeletedRulesList());
						getSession().setAttribute(
								WebConstants.SESSION_UNCODED_RULES_LIST,
								saveContractBasicInfoResponse
										.getUnCodedRulesList());
						msgListTwo = saveContractBasicInfoResponse
								.getMessages();
						msgListOne.addAll(msgListTwo);
						addAllMessagesToRequest(msgListOne);
						
					}
					if (saveContractBasicInfoResponse.isSuccess()) {
						this.clearBackingBeanValues();
						super.clearSession();
						return "CONTRACTCREATE";
					} else if (saveContractBasicInfoResponse
							.isIfUncodedLineNotesExist()) {
						this.setIfUncodedLineNotesExist("Yes");
					}
				}
			}
		} else {
			addAllMessagesToRequest(validationMessages);
		}
		this.checkIn = false;
		getRequest().setAttribute("RETAIN_Value", "");
		return WebConstants.EMPTY_STRING;
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
	 * Action method for direct Contract Checkin after deleting uncoded
	 * line/question notes
	 * 
	 * @return
	 */
	public String directCheckin() {
		ContractUncodedNotesRequest request = (ContractUncodedNotesRequest) this
				.getServiceRequest(ServiceManager.CONTRACT_UNCODED_NOTES_REQUEST);
		request.setContractId(getContractSession().getContractKeyObject()
				.getContractId());
		request
				.setAction(ContractUncodedNotesRequest.DELETE_UNCODED_NOTES_CHECKIN);
		ContractUncodedNotesResponse response = (ContractUncodedNotesResponse) this
				.executeService(request);
		if (null != response && response.isSuccess()) {
			this.clearBackingBeanValues();
			super.clearSession();
			return "CONTRACTCREATE";
		}
		return WebConstants.EMPTY_STRING;
	}

	/**
	 * Action method for Contract Checkin from membership information page
	 * 
	 * @return
	 */
	public String doneMember() {
		
		List msgListTwo = new ArrayList();
		SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
				.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
		SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
		saveContractBasicInfoRequest
				.setAction(SaveContractBasicInfoRequest.CHECKIN_CONTRACT);
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
		saveContractBasicInfoRequest.setChechIn(this.checkIn);
		
		//SSCR 16332 -START
		if (this.checkIn && !getInvokeWebService().equals("confirm") && getInvokeWebService() != null) {
			saveContractBasicInfoRequest.setEBXWS(true);
			saveContractBasicInfoRequest.setCarvConfirm(true);
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
		//SSCR 16332 - END
		if (null != getSession().getAttribute("AM_BENEFIT"))
			getSession().removeAttribute("AM_BENEFIT"); // clearing for
		// adminmethod contract
		// validation popup
		if (null != getSession().getAttribute("AM_BC_KEY"))
			getSession().removeAttribute("AM_BC_KEY"); // clearing for
		// adminmethod contract
		// validation popup
		if (null != getSession().getAttribute("DIRECT_CLICK"))
			getSession().removeAttribute("DIRECT_CLICK"); // clearing for
		// adminmethod
		// contract
		// validation popup
		saveContractBasicInfoResponse = (SaveContractBasicInfoResponse) executeService(saveContractBasicInfoRequest);
		if (null != saveContractBasicInfoResponse) {
			if (saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_RESULTS) {
				hasValidationErrors = true;
				setValuesForAdminMethodValidation();
				getRequest().setAttribute("RETAIN_Value", "");
				return "";
			} else if (saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_WAIT) {
				getSession().setAttribute(WebConstants.OBJECT_KEY_FOR_CHECKIN,
						"contractBasicInfoBackingBean");
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
				getSession().setAttribute(WebConstants.ACTION_FOR_CHECKIN,
						new Integer(2));
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
				return "EDITCONTRACT";
			}else{
				if (null != saveContractBasicInfoResponse.getMessages()) {
					msgListTwo = saveContractBasicInfoResponse.getMessages();
					getSession()
							.setAttribute("MESSAGE_LIST_MEMBER", msgListTwo);
				}
				if (saveContractBasicInfoResponse.isSuccess()) {
					this.clearBackingBeanValues();
					super.clearSession();
					return "CONTRACTCREATE";
				} else if (saveContractBasicInfoResponse
						.isIfUncodedLineNotesExist()) {
					this.setIfUncodedLineNotesExist("Yes");
				}
			}

		}
		getRequest().setAttribute("RETAIN_Value", "");
		return "";
	}
	
	//SSCR 17571 -Tab impl
	public String doProcessSimulationWebServices() {
		String retVal = "";
		if (null != getSession().getAttribute("wSErrorDisplayList")) {
			retVal = WebConstants.RETVALUE_EBX_SUCCESS;;
		}else{
			if (null != getSession().getAttribute("ebxProcessInterruptMsg")) {
				retVal = WebConstants.RETVALUE_EBX_SUCCESS;;
			}
		} 
		
		return retVal;
	}
	public String getVendorInfo() {
		
		return "navigateVendor";
	}
	//SSCR 17571 -Tab impl End
	/**
	 * Methode for Validating the required foelds
	 * 
	 * @return
	 */
	private boolean isRequiredFieldsValid() {
		validationMessages = new ArrayList();
		boolean requiredField = false;
		this.lobInvalid = false;
		this.entityInvalid = false;
		this.groupInvalid = false;
		/* START CARS */
		this.requiredMarketBusinessUnit = false;
		/* END CARS */
		this.contractIdInvalid = false;
		this.startDateInvalid = false;
		this.endDateInvalid = false;
		this.contractTypeInvalid = false;
		this.baseContractIdInvalid = false;
		this.baseContractDtInvalid = false;
		this.baseContractDtStandardInvalid = false;
		this.groupSizeInvalid = false;
		this.dateNotValid = false;
		this.requiredHeadQuarters = false;
		this.requiredProduct = false;

		if ((!(WebConstants.SHELL.equals(this.contractType)))
				&& (this.lineOfBusinessDiv == null || WebConstants.EMPTY_STRING
						.equals(this.lineOfBusinessDiv))) {
			lobInvalid = true;
			requiredField = true;
		}
		if ((!(WebConstants.SHELL.equals(this.contractType)))
				&& (this.businessEntityDiv == null || WebConstants.EMPTY_STRING
						.equals(this.businessEntityDiv))) {
			entityInvalid = true;
			requiredField = true;
		}
		if ((!(WebConstants.SHELL.equals(this.contractType)))
				&& (this.businessGroupDiv == null || WebConstants.EMPTY_STRING
						.equals(this.businessGroupDiv))) {
			groupInvalid = true;
			requiredField = true;
		}
		/* START CARS */
		if (!WebConstants.SHELL.equals(this.contractType)
				&& (null == this.marketBusinessUnit || WebConstants.EMPTY_STRING
						.equals(this.marketBusinessUnit))) {
			requiredMarketBusinessUnit = true;
			requiredField = true;
		}
		/* END CARS */

		if (this.startDate == null
				|| WebConstants.EMPTY_STRING.equals(this.startDate.trim())) {
			startDateInvalid = true;
			requiredField = true;
		} else if (!(WPDStringUtil.isValidDate(this.startDate))) {
			ErrorMessage errorMessage = new ErrorMessage(
					WebConstants.INPUT_FORMAT_INVALID);
			errorMessage
					.setParameters(new String[] { WebConstants.EFFECTIVE_DATE });
			validationMessages.add(errorMessage);
			dateNotValid = true;
			startDateInvalid = true;
		}

		if (this.contractType == null
				|| WebConstants.EMPTY_STRING.equals(this.contractType)) {
			contractTypeInvalid = true;
			requiredField = true;
		}
		if (!(super.isEditMode())) {
			if ((WebConstants.CUSTOM.equalsIgnoreCase(this.contractType))
					&& ((this.baseContractIdDiv == null) || (WebConstants.EMPTY_STRING
							.equals(this.baseContractIdDiv)))) {
				baseContractIdInvalid = true;
				requiredField = true;
			}
			if ((WebConstants.CUSTOM.equalsIgnoreCase(this.contractType))
					&& ((this.baseContractDtDiv == null) || (WebConstants.EMPTY_STRING
							.equals(this.baseContractDtDiv)))) {
				baseContractDtInvalid = true;
				requiredField = true;
			}// Validation check for STANDARD contract if it has the base
			// contract and making base effective date manditory
			if ((WebConstants.STANDAR.equalsIgnoreCase(this.contractType))
					&& (null != this.baseContractIdDivNonMaditory)
					&& (!(WebConstants.EMPTY_STRING)
							.equals(this.baseContractIdDivNonMaditory))) {
				if ((WebConstants.STANDAR.equalsIgnoreCase(this.contractType))
						&& ((this.baseContractDtDivNonMaditory == null) || (WebConstants.EMPTY_STRING
								.equals(this.baseContractDtDivNonMaditory)))) {
					baseContractDtStandardInvalid = true;
					requiredField = true;
				}
			}
		}
		if ((WebConstants.MANDATE.equalsIgnoreCase(this.contractType))
				&& ((this.headQuartersState == null) || (WebConstants.EMPTY_STRING
						.equals(this.headQuartersState)))
				&& (!(super.isEditMode()))) {
			requiredHeadQuarters = true;
			requiredField = true;
		}
		if ((WebConstants.MANDATE.equalsIgnoreCase(this.contractType))
				&& ((this.product == null) || (WebConstants.EMPTY_STRING
						.equals(this.product))) && (!(super.isEditMode()))) {
			requiredProduct = true;
			requiredField = true;
		}
		if ((!(WebConstants.SHELL.equalsIgnoreCase(this.contractType)))
				&& (this.groupSizeDiv == null || WebConstants.EMPTY_STRING
						.equals(this.groupSizeDiv))) {
			groupSizeInvalid = true;
			requiredField = true;
		}

		if (!WPDStringUtil.hasText(this.contractStatus)) {
			requiredField = true;
			contractStatusInvalid = true;
		}

		if (WPDStringUtil.hasText(this.contractStatus)
				&& !WebConstants.CNTRT_STATUS_ACTIVE
						.equals(this.contractStatus)) {
			if (!WPDStringUtil.hasText(this.contractStatusReasonCode)) {
				requiredField = true;
				statusResonCodeInvalid = true;
			}

			if (!WPDStringUtil.hasText(this.contractStatusDate)) {
				requiredField = true;
				contractStatusDateInvalid = true;
			}
		}

		if (requiredField) {
			this.validationMessages.add(new ErrorMessage(
					WebConstants.MANDATORY_FIELDS_REQUIRED));
			return false;
		} else if (dateNotValid) {
			return false;
		}

		return true;
	}

	/**
	 * Methode for clearing the Backing Bean Values
	 * 
	 * @return
	 */
	public void clearBackingBeanValues() {
		this.contractIdDiv = WebConstants.EMPTY_STRING;
		this.contractType = WebConstants.EMPTY_STRING;
		this.baseContractIdDiv = WebConstants.EMPTY_STRING;
		this.baseContractDtDiv = WebConstants.EMPTY_STRING;
		this.baseContractIdDivNonMaditory = WebConstants.EMPTY_STRING;
		this.baseContractDtDivNonMaditory = WebConstants.EMPTY_STRING;
		this.groupSizeDiv = WebConstants.EMPTY_STRING;
		this.startDate = WebConstants.EMPTY_STRING;
		this.endDate = WebConstants.EMPTY_STRING;
		this.lineOfBusinessDiv = WebConstants.MEDICAL;
		this.businessEntityDiv = WebConstants.ALL_99;
		this.businessGroupDiv = WebConstants.ALL_99;
		/* START CARS */
		this.marketBusinessUnit = WebConstants.ALL_99;
		/* END CARS */
	}

	/**
	 * Methode for retreiving the basic Information of a contract
	 * 
	 * @return
	 */
	public String getBasicInfo() {
		RetrieveBasicInfoRequest retrieveBasicInfoRequest = (RetrieveBasicInfoRequest) this
				.getServiceRequest(ServiceManager.RETREIVE_CONTRACT_BASICINFO_REQUEST);
		ContractKeyObject contractKeyObject = getContractSession()
				.getContractKeyObject();
		retrieveBasicInfoRequest.setContractKeyObject(contractKeyObject);
		retrieveBasicInfoRequest.setDateSegmentId(contractKeyObject
				.getDateSegmentId());
		if (super.isEditMode()) {
			retrieveBasicInfoRequest.setEditMode(true);
		}
		retrieveBasicInfoRequest.setMigCompletFlag(this.migCompletFlag);
		RetrieveBasicInfoResponse retrieveBasicInfoResponse = (RetrieveBasicInfoResponse) executeService(retrieveBasicInfoRequest);
		if (retrieveBasicInfoResponse.isLockAquired()) {
			setValuesToBackingBean(retrieveBasicInfoResponse.getContract(),
					retrieveBasicInfoResponse.getDomainDetail());
			super.setContractToSession(retrieveBasicInfoResponse.getContract());
		}
		List messages = new ArrayList();
		List messages1 = (List) getSession().getAttribute("MESSAGE_LIST");
		List messages2 = retrieveBasicInfoResponse.getMessages();
		if (null != messages1) {
			messages.addAll(messages1);
		}
		if (null != messages2) {
			messages.addAll(messages2);
		}
		addAllMessagesToRequest(messages);
		getSession().removeAttribute("MESSAGE_LIST");
		if (retrieveBasicInfoResponse.isLockAquired()) {
			if (super.isViewMode())
				return "displayContractBasicInfoViewTab";
			else {
				// getRequest().setAttribute("RETAIN_Value", "");
				return WebConstants.EDIT_PAGE;
			}
		} else {
			List messageList = retrieveBasicInfoResponse.getMessages();
			ContractSearchBackingBean contractSearchBackingBean = (ContractSearchBackingBean) getRequest()
					.getAttribute("contractSearchBackingBean");
			contractSearchBackingBean.performLocate();
			addAllMessagesToRequest(messageList);
			return WebConstants.EMPTY_STRING;
		}
	}

	/**
	 * Methode for retreiving the basic Information of a contractfor View
	 * 
	 * @return
	 */
	public String getInitView() {
		String contractID = getRequest().getParameter("contractID");
		String contractSegmentId = getRequest().getParameter("contractSysId");
		String contractDateSegmentSysId = getRequest().getParameter(
				"contractDateSegmentSysId");
		String status = getRequest().getParameter("status");
		String version = getRequest().getParameter("version");
		String migFlag = getRequest().getParameter("migCompletFlag");
		if (null != migFlag && migFlag.equalsIgnoreCase("true")) {
			this.migCompletFlag = true;
		}
		if (contractID != null && contractDateSegmentSysId != null) {

			int contractSysId = 0;
			if (null != contractSegmentId)
				contractSysId = Integer.parseInt(contractSegmentId);
			int contractDateSegSysId = 0;
			if (null != contractDateSegmentSysId)
				contractDateSegSysId = Integer
						.parseInt(contractDateSegmentSysId);
			int iVersion = 0;
			if (null != version)
				iVersion = Integer.parseInt(version);

			ContractKeyObject keyObject = new ContractKeyObject();
			keyObject.setContractSysId(contractSysId);
			keyObject.setContractId(contractID);
			keyObject.setDateSegmentId(contractDateSegSysId);
			keyObject.setVersion(iVersion);
			keyObject.setStatus(status);
			this.getContractSession().setContractKeyObject(keyObject);
			super.setMode(ContractSession.VIEW_MODE);
			if (null != getSession()
					.getAttribute("SESSION_TREE_STATE_CONTRACT"))
				getSession().removeAttribute("SESSION_TREE_STATE_CONTRACT");
			this.getBasicInfo();

		}
		return WebConstants.EMPTY_STRING;
	}

	/**
	 * This setter is needed as JSF will call the setter method of init view
	 * while submiting the page.
	 * 
	 * @return
	 */
	public void setInitView(String temp) {

	}

	/**
	 * 
	 * @return
	 */
	public String getInitViewForPrint() {
		ContractKeyObject keyObject = this.getContractKeyObject();
		RetrieveBasicInfoRequest retrieveBasicInfoRequest = (RetrieveBasicInfoRequest) this
				.getServiceRequest(ServiceManager.RETREIVE_CONTRACT_BASICINFO_REQUEST);
		retrieveBasicInfoRequest.setContractKeyObject(keyObject);
		retrieveBasicInfoRequest.setDateSegmentId(keyObject.getDateSegmentId());
		RetrieveBasicInfoResponse retrieveBasicInfoResponse = (RetrieveBasicInfoResponse) executeService(retrieveBasicInfoRequest);
		setValuesToBackingBean(retrieveBasicInfoResponse.getContract(),
				retrieveBasicInfoResponse.getDomainDetail());
		return WebConstants.EMPTY_STRING;
	}

	/**
	 * @return pageLoadBasedOnContractType
	 * 
	 *         Returns the pageLoadBasedOnContractType.
	 */
	public String getPageLoadBasedOnContractType() {
		pageLoadBasedOnContractType = getContractSession()
				.getContractKeyObject().contractType;
		return pageLoadBasedOnContractType;
	}

	/**
	 * @param pageLoadBasedOnContractType
	 * 
	 *            Sets the pageLoadBasedOnContractType.
	 */
	public void setPageLoadBasedOnContractType(
			String pageLoadBasedOnContractType) {
		this.pageLoadBasedOnContractType = pageLoadBasedOnContractType;
	}

	/**
	 * Returns the baseContractIdDiv
	 * 
	 * @return String baseContractIdDiv.
	 */
	public String getBaseContractIdDiv() {
		return baseContractIdDiv;
	}

	/**
	 * Sets the baseContractIdDiv
	 * 
	 * @param baseContractIdDiv
	 *            .
	 */
	public void setBaseContractIdDiv(String baseContractIdDiv) {
		this.baseContractIdDiv = baseContractIdDiv;
	}

	/**
	 * Returns the businessEntityDiv
	 * 
	 * @return String businessEntityDiv.
	 */

	public String getBusinessEntityDiv() {
		return businessEntityDiv;
	}

	/**
	 * Sets the businessEntityDiv
	 * 
	 * @param businessEntityDiv
	 *            .
	 */

	public void setBusinessEntityDiv(String businessEntityDiv) {
		this.businessEntityDiv = businessEntityDiv;
	}

	/**
	 * Returns the businessGroupDiv
	 * 
	 * @return String businessGroupDiv.
	 */

	public String getBusinessGroupDiv() {
		return businessGroupDiv;
	}

	/**
	 * Sets the businessGroupDiv
	 * 
	 * @param businessGroupDiv
	 *            .
	 */

	public void setBusinessGroupDiv(String businessGroupDiv) {
		this.businessGroupDiv = businessGroupDiv;
	}

	/**
	 * Returns the contractIdDiv
	 * 
	 * @return String contractIdDiv.
	 */

	public String getContractIdDiv() {
		return contractIdDiv;
	}

	/**
	 * Sets the contractIdDiv
	 * 
	 * @param contractIdDiv
	 *            .
	 */

	public void setContractIdDiv(String contractIdDiv) {
		this.contractIdDiv = contractIdDiv;
	}

	/**
	 * Returns the contractType
	 * 
	 * @return String contractType.
	 */

	public String getContractType() {
		return contractType;
	}

	/**
	 * Sets the contractType
	 * 
	 * @param contractType
	 *            .
	 */

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	/**
	 * Returns the lineOfBusinessDiv
	 * 
	 * @return String lineOfBusinessDiv.
	 */

	public String getLineOfBusinessDiv() {
		return lineOfBusinessDiv;
	}

	/**
	 * Sets the lineOfBusinessDiv
	 * 
	 * @param lineOfBusinessDiv
	 *            .
	 */

	public void setLineOfBusinessDiv(String lineOfBusinessDiv) {
		this.lineOfBusinessDiv = lineOfBusinessDiv;
	}

	/**
	 * Returns the endDate
	 * 
	 * @return String endDate.
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Sets the endDate
	 * 
	 * @param endDate
	 *            .
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the startDate
	 * 
	 * @return String startDate.
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Sets the startDate
	 * 
	 * @param startDate
	 *            .
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the contractIdInvalid
	 * 
	 * @return boolean contractIdInvalid.
	 */

	public boolean isContractIdInvalid() {
		return contractIdInvalid;
	}

	/**
	 * Sets the contractIdInvalid
	 * 
	 * @param contractIdInvalid
	 *            .
	 */

	public void setContractIdInvalid(boolean contractIdInvalid) {
		this.contractIdInvalid = contractIdInvalid;
	}

	/**
	 * Returns the contractTypeInvalid
	 * 
	 * @return boolean contractTypeInvalid.
	 */

	public boolean isContractTypeInvalid() {
		return contractTypeInvalid;
	}

	/**
	 * Sets the contractTypeInvalid
	 * 
	 * @param contractTypeInvalid
	 *            .
	 */

	public void setContractTypeInvalid(boolean contractTypeInvalid) {
		this.contractTypeInvalid = contractTypeInvalid;
	}

	/**
	 * Returns the endDateInvalid
	 * 
	 * @return boolean endDateInvalid.
	 */

	public boolean isEndDateInvalid() {
		return endDateInvalid;
	}

	/**
	 * Sets the endDateInvalid
	 * 
	 * @param endDateInvalid
	 *            .
	 */

	public void setEndDateInvalid(boolean endDateInvalid) {
		this.endDateInvalid = endDateInvalid;
	}

	/**
	 * Returns the entityInvalid
	 * 
	 * @return boolean entityInvalid.
	 */

	public boolean isEntityInvalid() {
		return entityInvalid;
	}

	/**
	 * Sets the entityInvalid
	 * 
	 * @param entityInvalid
	 *            .
	 */

	public void setEntityInvalid(boolean entityInvalid) {
		this.entityInvalid = entityInvalid;
	}

	/**
	 * Returns the groupInvalid
	 * 
	 * @return boolean groupInvalid.
	 */

	public boolean isGroupInvalid() {
		return groupInvalid;
	}

	/**
	 * Sets the groupInvalid
	 * 
	 * @param groupInvalid
	 *            .
	 */

	public void setGroupInvalid(boolean groupInvalid) {
		this.groupInvalid = groupInvalid;
	}

	/**
	 * Returns the lobInvalid
	 * 
	 * @return boolean lobInvalid.
	 */

	public boolean isLobInvalid() {
		return lobInvalid;
	}

	/**
	 * Sets the lobInvalid
	 * 
	 * @param lobInvalid
	 *            .
	 */

	public void setLobInvalid(boolean lobInvalid) {
		this.lobInvalid = lobInvalid;
	}

	/**
	 * Returns the startDateInvalid
	 * 
	 * @return boolean startDateInvalid.
	 */

	public boolean isStartDateInvalid() {
		return startDateInvalid;
	}

	/**
	 * Sets the startDateInvalid
	 * 
	 * @param startDateInvalid
	 *            .
	 */

	public void setStartDateInvalid(boolean startDateInvalid) {
		this.startDateInvalid = startDateInvalid;
	}

	/**
	 * Returns the baseContractIdInvalid
	 * 
	 * @return boolean baseContractIdInvalid.
	 */

	public boolean isBaseContractIdInvalid() {
		return baseContractIdInvalid;
	}

	/**
	 * Sets the baseContractIdInvalid
	 * 
	 * @param baseContractIdInvalid
	 *            .
	 */

	public void setBaseContractIdInvalid(boolean baseContractIdInvalid) {
		this.baseContractIdInvalid = baseContractIdInvalid;
	}

	/**
	 * Returns the creationDate
	 * 
	 * @return Date creationDate.
	 */

	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the creationDate
	 * 
	 * @param creationDate
	 *            .
	 */

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Returns the createdUser
	 * 
	 * @return String createdUser.
	 */

	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * Sets the createdUser
	 * 
	 * @param createdUser
	 *            .
	 */

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * Returns the updationDate
	 * 
	 * @return Date updationDate.
	 */

	public Date getUpdationDate() {
		return updationDate;
	}

	/**
	 * Sets the updationDate
	 * 
	 * @param updationDate
	 *            .
	 */

	public void setUpdationDate(Date updationDate) {
		this.updationDate = updationDate;
	}

	/**
	 * Returns the updatedUser
	 * 
	 * @return String updatedUser.
	 */

	public String getUpdatedUser() {
		return updatedUser;
	}

	/**
	 * Sets the updatedUser
	 * 
	 * @param updatedUser
	 *            .
	 */

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	/**
	 * Returns the checkIn
	 * 
	 * @return boolean checkIn.
	 */

	public boolean isCheckIn() {
		return checkIn;
	}

	/**
	 * Sets the checkIn
	 * 
	 * @param checkIn
	 *            .
	 */

	public void setCheckIn(boolean checkIn) {
		this.checkIn = checkIn;
	}

	/**
	 * Returns the state
	 * 
	 * @return String state.
	 */

	public String getState() {
		return getContractSession().getContractKeyObject().getState();
	}

	/**
	 * Sets the state
	 * 
	 * @param state
	 *            .
	 */

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Returns the status
	 * 
	 * @return String status.
	 */

	public String getStatus() {
		return getContractSession().getContractKeyObject().getStatus();
	}

	/**
	 * Sets the status
	 * 
	 * @param status
	 *            .
	 */

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Returns the version
	 * 
	 * @return int version.
	 */

	public int getVersion() {
		return getContractSession().getContractKeyObject().getVersion();
	}

	/**
	 * Sets the version
	 * 
	 * @param version
	 *            .
	 */

	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Returns the validationMessages
	 * 
	 * @return List validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * Sets the validationMessages
	 * 
	 * @param validationMessages
	 *            .
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * Returns the baseContractDtDiv
	 * 
	 * @return String baseContractDtDiv.
	 */
	public String getBaseContractDtDiv() {
		return baseContractDtDiv;
	}

	/**
	 * Sets the baseContractDtDiv
	 * 
	 * @param baseContractDtDiv
	 *            .
	 */
	public void setBaseContractDtDiv(String baseContractDtDiv) {
		this.baseContractDtDiv = baseContractDtDiv;
	}

	/**
	 * Returns the baseContractDtInvalid
	 * 
	 * @return boolean baseContractDtInvalid.
	 */
	public boolean isBaseContractDtInvalid() {
		return baseContractDtInvalid;
	}

	/**
	 * Sets the baseContractDtInvalid
	 * 
	 * @param baseContractDtInvalid
	 *            .
	 */
	public void setBaseContractDtInvalid(boolean baseContractDtInvalid) {
		this.baseContractDtInvalid = baseContractDtInvalid;
	}

	/**
	 * Returns the groupSizeDiv
	 * 
	 * @return String groupSizeDiv.
	 */
	public String getGroupSizeDiv() {
		return groupSizeDiv;
	}

	/**
	 * Sets the groupSizeDiv
	 * 
	 * @param groupSizeDiv
	 *            .
	 */
	public void setGroupSizeDiv(String groupSizeDiv) {
		this.groupSizeDiv = groupSizeDiv;
	}

	/**
	 * Returns the groupSizeInvalid
	 * 
	 * @return boolean groupSizeInvalid.
	 */
	public boolean isGroupSizeInvalid() {
		return groupSizeInvalid;
	}

	/**
	 * Sets the groupSizeInvalid
	 * 
	 * @param groupSizeInvalid
	 *            .
	 */
	public void setGroupSizeInvalid(boolean groupSizeInvalid) {
		this.groupSizeInvalid = groupSizeInvalid;
	}

	/**
	 * @return Returns the selectedIdFromSearch.
	 */
	public String getSelectedIdFromSearch() {
		return selectedIdFromSearch;
	}

	/**
	 * @param selectedIdFromSearch
	 *            The selectedIdFromSearch to set.
	 */
	public void setSelectedIdFromSearch(String selectedIdFromSearch) {
		this.selectedIdFromSearch = selectedIdFromSearch;
	}

	/**
	 * Returns the selectedContractKeyFromSearch
	 * 
	 * @return String selectedContractKeyFromSearch.
	 */
	public String getSelectedContractKeyFromSearch() {
		return selectedContractKeyFromSearch;
	}

	/**
	 * Sets the selectedContractKeyFromSearch
	 * 
	 * @param selectedContractKeyFromSearch
	 *            .
	 */
	public void setSelectedContractKeyFromSearch(
			String selectedContractKeyFromSearch) {
		this.selectedContractKeyFromSearch = selectedContractKeyFromSearch;
	}

	/**
	 * Returns the selectedContractIdFromSearch
	 * 
	 * @return String selectedContractIdFromSearch.
	 */
	public String getSelectedContractIdFromSearch() {
		return selectedContractIdFromSearch;
	}

	/**
	 * Sets the selectedContractIdFromSearch
	 * 
	 * @param selectedContractIdFromSearch
	 *            .
	 */
	public void setSelectedContractIdFromSearch(
			String selectedContractIdFromSearch) {
		this.selectedContractIdFromSearch = selectedContractIdFromSearch;
	}

	/**
	 * Returns the selectedStatusFromSearch
	 * 
	 * @return String selectedStatusFromSearch.
	 */
	public String getSelectedStatusFromSearch() {
		return selectedStatusFromSearch;
	}

	/**
	 * Sets the selectedStatusFromSearch
	 * 
	 * @param selectedStatusFromSearch
	 *            .
	 */
	public void setSelectedStatusFromSearch(String selectedStatusFromSearch) {
		this.selectedStatusFromSearch = selectedStatusFromSearch;
	}

	/**
	 * Returns the selectedVersionFromSearch
	 * 
	 * @return String selectedVersionFromSearch.
	 */
	public String getSelectedVersionFromSearch() {
		return selectedVersionFromSearch;
	}

	/**
	 * Sets the selectedVersionFromSearch
	 * 
	 * @param selectedVersionFromSearch
	 *            .
	 */
	public void setSelectedVersionFromSearch(String selectedVersionFromSearch) {
		this.selectedVersionFromSearch = selectedVersionFromSearch;
	}

	/**
	 * Returns the selectedDateSegmentIdFromSearch
	 * 
	 * @return String selectedDateSegmentIdFromSearch.
	 */
	public String getSelectedDateSegmentIdFromSearch() {
		return selectedDateSegmentIdFromSearch;
	}

	/**
	 * Sets the selectedDateSegmentIdFromSearch
	 * 
	 * @param selectedDateSegmentIdFromSearch
	 *            .
	 */
	public void setSelectedDateSegmentIdFromSearch(
			String selectedDateSegmentIdFromSearch) {
		this.selectedDateSegmentIdFromSearch = selectedDateSegmentIdFromSearch;
	}

	/**
	 * This method returns the benefit headings of the contract used for copy
	 * operation
	 * 
	 * @return
	 */
	public List getBenefitHeadingsList() {
		List benefitHeadingsList = new ArrayList();
		RetrieveBenefitHeadingsRequest retrieveBenefitHeadingsRequest = (RetrieveBenefitHeadingsRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_BENEFIT_HEADINGS_REQUEST);

		// gets the request parameters for the retrieve
		String dateSegmentId = this.getHiddenDateSegmentIdForCopy();
		String contractKey = this.getHiddenContractKeyForCopy();
		String version = this.getHiddenVersionForCopy();
		String contractId = this.getHiddenContractIdForCopy();
		String status = this.getHiddenStatusForCopy();
		String productId = this.getHiddenProductIdForCopy();

		// sets the request parameters to Session
		if (null != dateSegmentId
				&& !dateSegmentId.equals(WebConstants.EMPTY_STRING)) {
			ContractKeyObject keyObject = new ContractKeyObject();
			keyObject.setContractSysId(Integer.parseInt(contractKey));
			keyObject.setContractId(contractId);
			keyObject.setDateSegmentId(Integer.parseInt(dateSegmentId));
			keyObject.setVersion(Integer.parseInt(version));
			keyObject.setStatus(status);
			this.getContractSession().setContractKeyObject(keyObject);
			// sets the dateSegmentId to request
			retrieveBenefitHeadingsRequest.setDateSegmentId(Integer
					.parseInt(dateSegmentId));

			retrieveBenefitHeadingsRequest.setProductId(Integer
					.parseInt(productId));
		} else {
			retrieveBenefitHeadingsRequest
					.setDateSegmentId(getContractSession()
							.getContractKeyObject().getDateSegmentId());
			this.setSetExistingTrue(true);
		}

		// retrieves the coded benefit component and its benefits
		RetrieveBenefitHeadingsResponse retrieveBenefitHeadingsResponse = (RetrieveBenefitHeadingsResponse) this
				.executeService(retrieveBenefitHeadingsRequest);
		if (null != retrieveBenefitHeadingsResponse)
			benefitHeadingsList = retrieveBenefitHeadingsResponse
					.getBenefitHeadingsList();
		return benefitHeadingsList;
	}

	/**
	 * Sets the benefitHeadingsList
	 * 
	 * @param benefitHeadingsList
	 *            .
	 */
	public void setBenefitHeadingsList(List benefitHeadingsList) {
		this.benefitHeadingsList = benefitHeadingsList;
	}

	/**
	 * Sets the headerPanel
	 * 
	 * @param headerPanel
	 *            .
	 */
	public void setHeaderPanel(HtmlPanelGrid headerPanel) {
		this.headerPanel = headerPanel;
	}

	/**
	 * This method renders the panel which consists of benefit headings used for
	 * copy operation Returns the panel
	 * 
	 * @return HtmlPanelGrid panel.
	 */
	public HtmlPanelGrid getPanel() {
		if (null == panel) {
			panel = new HtmlPanelGrid();
			Logger.logInfo("entered method getPanel");
			int rowNumber = 0;
			getSession().setAttribute(WebConstants.PRODUCT_STATUS,
					this.hiddenProductStatusForCopy);
			getSession().setAttribute(WebConstants.NOTE_STATUS,
					this.hiddenNoteStatusForCopy);

			// sets a flag if we choose to replace the product
			if (WebConstants.REPLACE_PROCUCT
					.equals(this.hiddenProductStatusForCopy))
				this.hiddenLatestCopyStatus = "true";

			// The benefit headings are retrieved
			List benefitHeadingsList = this.getBenefitHeadingsList();

			// This method gets the values from the benefit Heaidngs List and
			// sets
			// it to the benefit component list and benefit list
			getValuesFromBenefitHeadingsList(benefitHeadingsList);

			panel = new HtmlPanelGrid();
			panel.setColumns(2);
			panel.setWidth("100%");

			panel.setBorder(0);
			panel.setCellpadding("3");
			panel.setCellspacing("1");
			panel.setBgcolor("#cccccc");
			StringBuffer rows = new StringBuffer();
			// setting values to benefit Components
			int size = benefitComponentList.size();
			// iterating to get the benefit Components
			for (int i = 0; i < size; i++) {
				rowNumber++;
				rows.append("dataTableEvenRow");

				// a benefit Component is selected
				ContractBenefitComponentHeadings benefitComponentValues = (ContractBenefitComponentHeadings) benefitComponentList
						.get(i);
				// HtmlOutputText levelDesc;
				// HtmlOutputText levelTerm;
				// HtmlOutputText levelQualifier;
				// HtmlOutputText levelPVA;
				// HtmlOutputText levelBnftValue;
				// HtmlOutputText levelReference;
				// HtmlInputHidden hiddenLevelId;
				// HtmlGraphicImage deleteImage;
				// HtmlOutputLabel lblImage;

				// gets the standard benefits of a benefit Component
				List standardBenefits = benefitComponentValues
						.getStandardBenefitList();

				// setting the benefit Component values to the panel grid
				setBenefitComponentValuesToGrid(i, benefitComponentValues,
						standardBenefits.size(), rowNumber);

				if (standardBenefits.size() != 0)
					rows.append(WebConstants.COMMA);

				// iterating to get the individual standard benefits
				for (int j = 0; j < standardBenefits.size(); j++) {
					rowNumber++;
					rows.append("dataTableOddRow");
					if (i < (size - 1))
						rows.append(WebConstants.COMMA);
					else if (j < (standardBenefits.size() - 1))
						rows.append(WebConstants.COMMA);

					// selects an individual standard benefit
					ContractBenefitHeadings standardBenefitValues = (ContractBenefitHeadings) standardBenefits
							.get(j);
					//                  
					// HtmlOutputText lineDesc;
					// HtmlOutputText lineTerm;
					// HtmlOutputText lineQualifier;
					// HtmlOutputText linePVA;
					// HtmlOutputText lineDataType;
					// HtmlInputText lineBnftValue;
					// ValueBinding valueItem;
					// HtmlOutputLabel lblBnftValue;
					// HtmlOutputText lineReference;
					// HtmlOutputText lineImage;

					// sets the standard benefits of a benefit component to the
					// panel grid
					setStandardBenefitValuesToGrid(benefitComponentValues, j,
							standardBenefitValues, i);
				}
			}
			panel.setRowClasses(rows.toString());
		}
		return panel;
	}

	/**
	 * This method gets the values from the benefit Headings List and sets it to
	 * the bnft component list and standard bnft list
	 * 
	 * @param benefitHeadingsList
	 */
	private void getValuesFromBenefitHeadingsList(List benefitHeadingsList) {

		Logger.logInfo("entered method getValuesFromDefinitionList");

		// TODO Auto-generated method stub
		benefitComponentList = new ArrayList();
		for (int i = 0; i < benefitHeadingsList.size(); i++) {
			ContractBenefitHeadings contractBenefitHeadings = (ContractBenefitHeadings) benefitHeadingsList
					.get(i);
			// sets values to the benefitComponent List
			setValuesToBenefitComponent(contractBenefitHeadings,
					benefitComponentList);
		}
	}

	/**
	 * This method sets values to the benefit Component List
	 * 
	 * @param bnftHeadings
	 * @param benefitComponentList
	 */
	private void setValuesToBenefitComponent(
			ContractBenefitHeadings bnftHeadings, List benefitComponentList) {

		Logger.logInfo("entered method setValuesToBenefitLevel");

		// TODO Auto-generated method stub
		ContractBenefitComponentHeadings benefitComponentBO = null;

		// checks if the benefit component list size is not equal to zero
		if (benefitComponentList.size() != 0) {
			benefitComponentBO = (ContractBenefitComponentHeadings) benefitComponentList
					.get(benefitComponentList.size() - 1);
		}
		// checks if the benefit ComponentList size is 0 or if the previous
		// bnftComponentId is equal to the present bnftComponentId
		if (benefitComponentList.size() == 0
				|| (!(bnftHeadings.getBenefitComponentName()
						.equals(benefitComponentBO.getBenefitComponentName())))) {
			ContractBenefitComponentHeadings benefitComponentLevel = new ContractBenefitComponentHeadings();
			benefitComponentLevel.setBenefitComponentId(bnftHeadings
					.getBenefitComponentId());
			benefitComponentLevel.setBenefitComponentName(bnftHeadings
					.getBenefitComponentName());

			// sets standard benefits to the benefit components
			benefitComponentLevel.setStandardBenefitList(new ArrayList());
			benefitComponentLevel.getStandardBenefitList().add(
					getStandardBenefitBO(bnftHeadings));
			benefitComponentList.add(benefitComponentLevel);
		} else {
			// add benefit to the existing benefit Component
			benefitComponentBO.getStandardBenefitList().add(
					getStandardBenefitBO(bnftHeadings));
		}
	}

	/**
	 * This method returns the ContractBenefitHeadings bo
	 * 
	 * @param benefitHeadings
	 * @return
	 */
	private ContractBenefitHeadings getStandardBenefitBO(
			ContractBenefitHeadings benefitHeadings) {

		Logger.logInfo("entered method getBenefitLineBO");
		ContractBenefitHeadings entityBenefitLineToSet = new ContractBenefitHeadings();
		entityBenefitLineToSet.setStandardBenefitName(benefitHeadings
				.getStandardBenefitName());
		entityBenefitLineToSet.setStandardBenefitId(benefitHeadings
				.getStandardBenefitId());
		return entityBenefitLineToSet;
	}

	/**
	 * This method sets the standardBenefitValues to the panel Grid
	 * 
	 * @param benefitComponentValues
	 * @param j
	 * @param standardBenefitValues
	 * @param i
	 */
	private void setStandardBenefitValuesToGrid(
			ContractBenefitComponentHeadings benefitComponentValues, int j,
			ContractBenefitHeadings standardBenefitValues, int i) {
		Logger.logInfo("entered method setBenefitLineValuesToGrid");
		HtmlSelectBooleanCheckbox htmlCheckbox = new HtmlSelectBooleanCheckbox();
		htmlCheckbox.setValue(Boolean.FALSE);
		htmlCheckbox.setId("StdBnft" + i + "_" + j);
		String key = Integer.toString(standardBenefitValues
				.getStandardBenefitId())
				+ '.'
				+ Integer.toString(benefitComponentValues
						.getBenefitComponentId());
		ValueBinding valueItem = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{contractBasicInfoBackingBean.standardBenefitMap['"
								+ key + "']}");
		htmlCheckbox.setValueBinding("value", valueItem);
		HtmlOutputText benefitDesc = new HtmlOutputText();
		benefitDesc.setValue(standardBenefitValues.getStandardBenefitName());
		htmlCheckbox.setStyle("width:40px");
		panel.getChildren().add(htmlCheckbox);
		panel.getChildren().add(benefitDesc);

	}

	/**
	 * This method sets the benefitComponentValues to the PanelGrid
	 * 
	 * @param i
	 * @param benefitComponentValues
	 *            ,lineSize,rowNum
	 */
	private void setBenefitComponentValuesToGrid(int i,
			ContractBenefitComponentHeadings benefitComponentValues,
			int lineSize, int rowNum) {

		Logger.logInfo("entered method setBenefitComponentValuesToGrid");
		HtmlSelectBooleanCheckbox htmlCheckbox = new HtmlSelectBooleanCheckbox();
		htmlCheckbox.setId("BnftCmpnt" + i);
		htmlCheckbox.setValue(Boolean.FALSE);
		ValueBinding valueItem = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{contractBasicInfoBackingBean.benefitComponentMap["
								+ i + "]}");
		htmlCheckbox.setValueBinding("value", valueItem);
		htmlCheckbox.setOnclick("checkStndBnfts(\'" + i + "','" + lineSize
				+ "\')");
		HtmlOutputText benefitComponentName = new HtmlOutputText();
		if (null != benefitComponentValues.getBenefitComponentName()) {
			benefitComponentName.setValue(benefitComponentValues
					.getBenefitComponentName().trim());
		} else {
			benefitComponentName.setValue(WebConstants.EMPTY_STRING);
		}

		HtmlInputHidden hiddenBenefitComponentId = new HtmlInputHidden();
		hiddenBenefitComponentId.setId("hiddenBenefitComponentId" + i);
		hiddenBenefitComponentId.setValue(new Integer(benefitComponentValues
				.getBenefitComponentId()));
		panel.getChildren().add(htmlCheckbox);
		panel.getChildren().add(benefitComponentName);
	}

	/**
	 * Sets the panel
	 * 
	 * @param panel
	 *            .
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
	}

	/**
	 * Returns the benefitComponentList
	 * 
	 * @return List benefitComponentList.
	 */
	public List getBenefitComponentList() {
		return benefitComponentList;
	}

	/**
	 * Sets the benefitComponentList
	 * 
	 * @param benefitComponentList
	 *            .
	 */
	public void setBenefitComponentList(List benefitComponentList) {
		this.benefitComponentList = benefitComponentList;
	}

	/**
	 * This method performs the copy operation
	 * 
	 * @return
	 */
	public String copyAction() {
		RetrieveBasicInfoRequest retrieveBasicInfoRequest = (RetrieveBasicInfoRequest) this
				.getServiceRequest(ServiceManager.RETREIVE_CONTRACT_BASICINFO_REQUEST);
		ContractKeyObject contractKeyObject = getContractSession()
				.getContractKeyObject();
		if (null != contractKeyObject) {
			retrieveBasicInfoRequest.setContractKeyObject(contractKeyObject);
			retrieveBasicInfoRequest.setDateSegmentId(contractKeyObject
					.getDateSegmentId());
		}
		RetrieveBasicInfoResponse retrieveBasicInfoResponse = (RetrieveBasicInfoResponse) executeService(retrieveBasicInfoRequest);
		updateTreeStructure();
		super.setCopyMode();
		if (null != retrieveBasicInfoResponse)
			setValuesToBackingBean(retrieveBasicInfoResponse.getContract(),
					retrieveBasicInfoResponse.getDomainDetail());
		super.setContractToSession(retrieveBasicInfoResponse.getContract());

		// gets the dateSegmentId of the base contract
		int basedateid = retrieveBasicInfoResponse.getContract()
				.getBaseDateSegmentSysId();
		getSession().setAttribute(WebConstants.BASE_DATE_ID,
				new Integer(basedateid));

		if (null != getSession().getAttribute(
				WebConstants.SESSION_TREE_STATE_CONTRACT))
			getSession().removeAttribute(
					WebConstants.SESSION_TREE_STATE_CONTRACT);

		return "createContract";
	}

	/**
	 * This method set the values from search to Session
	 * 
	 * @return
	 */
	// private void setSearchValuesToSession(){
	//        
	// ContractKeyObject keyObject = new ContractKeyObject();
	// keyObject.setContractSysId(Integer.parseInt(this.getSelectedContractKeyFromSearch()));
	// keyObject.setContractId(this.getSelectedContractIdFromSearch());
	// keyObject.setDateSegmentId(Integer.parseInt(this.getSelectedDateSegmentIdFromSearch()));
	// keyObject.setVersion(Integer.parseInt(this.getSelectedVersionFromSearch()));
	// keyObject.setStatus(this.getSelectedStatusFromSearch());
	// this.getContractSession().setContractKeyObject(keyObject);
	//        
	// }
	/**
	 * Returns the hiddenContractIdForCopy
	 * 
	 * @return String hiddenContractIdForCopy.
	 */
	public String getHiddenContractIdForCopy() {

		String contractId = getRequest().getParameter("contractId");
		if (null != contractId)
			this.hiddenContractIdForCopy = contractId;

		return hiddenContractIdForCopy;
	}

	/**
	 * Sets the hiddenContractIdForCopy
	 * 
	 * @param hiddenContractIdForCopy
	 *            .
	 */
	public void setHiddenContractIdForCopy(String hiddenContractIdForCopy) {
		this.hiddenContractIdForCopy = hiddenContractIdForCopy;
	}

	/**
	 * Returns the hiddenContractKeyForCopy
	 * 
	 * @return String hiddenContractKeyForCopy.
	 */
	public String getHiddenContractKeyForCopy() {

		String contractKey = getRequest().getParameter(
				WebConstants.CONTRACT_KEY);
		if (null != contractKey)
			this.hiddenContractKeyForCopy = contractKey;

		return hiddenContractKeyForCopy;
	}

	/**
	 * Sets the hiddenContractKeyForCopy
	 * 
	 * @param hiddenContractKeyForCopy
	 *            .
	 */
	public void setHiddenContractKeyForCopy(String hiddenContractKeyForCopy) {
		this.hiddenContractKeyForCopy = hiddenContractKeyForCopy;
	}

	/**
	 * Returns the hiddenDateSegmentIdForCopy
	 * 
	 * @return String hiddenDateSegmentIdForCopy.
	 */
	public String getHiddenDateSegmentIdForCopy() {

		String dateSegmentId = getRequest().getParameter("dateSegmentId");
		if (null != dateSegmentId)
			this.hiddenDateSegmentIdForCopy = dateSegmentId;

		return hiddenDateSegmentIdForCopy;
	}

	/**
	 * Sets the hiddenDateSegmentIdForCopy
	 * 
	 * @param hiddenDateSegmentIdForCopy
	 *            .
	 */
	public void setHiddenDateSegmentIdForCopy(String hiddenDateSegmentIdForCopy) {
		this.hiddenDateSegmentIdForCopy = hiddenDateSegmentIdForCopy;
	}

	/**
	 * Returns the hiddenVersionForCopy
	 * 
	 * @return String hiddenVersionForCopy.
	 */
	public String getHiddenVersionForCopy() {

		String version = getRequest().getParameter("version");
		if (null != version)
			this.hiddenVersionForCopy = version;

		return hiddenVersionForCopy;
	}

	/**
	 * Sets the hiddenVersionForCopy
	 * 
	 * @param hiddenVersionForCopy
	 *            .
	 */
	public void setHiddenVersionForCopy(String hiddenVersionForCopy) {
		this.hiddenVersionForCopy = hiddenVersionForCopy;
	}

	/**
	 * @return Returns the hiddenProductIdForCopy.
	 */
	public String getHiddenProductIdForCopy() {
		String productId = ESAPI.encoder().encodeForHTML(getRequest().getParameter("productId"));
		if (null != productId && productId.matches("[0-9a-zA-Z_]+"))
			this.hiddenProductIdForCopy = productId;
		return hiddenProductIdForCopy;
	}

	/**
	 * @param hiddenProductIdForCopy
	 *            The hiddenProductIdForCopy to set.
	 */
	public void setHiddenProductIdForCopy(String hiddenProductIdForCopy) {
		this.hiddenProductIdForCopy = hiddenProductIdForCopy;
	}

	/**
	 * @return Returns the selectedProductSysIdFromSearch.
	 */
	public String getSelectedProductSysIdFromSearch() {
		return selectedProductSysIdFromSearch;
	}

	/**
	 * @param selectedProductSysIdFromSearch
	 *            The selectedProductSysIdFromSearch to set.
	 */
	public void setSelectedProductSysIdFromSearch(
			String selectedProductSysIdFromSearch) {
		this.selectedProductSysIdFromSearch = selectedProductSysIdFromSearch;
	}

	/**
	 * Returns the hiddenStatusForCopy
	 * 
	 * @return String hiddenStatusForCopy.
	 */
	public String getHiddenStatusForCopy() {

		String status = getRequest().getParameter("status");
		if (null != status)
			this.hiddenStatusForCopy = status;

		return hiddenStatusForCopy;
	}

	/**
	 * Sets the hiddenStatusForCopy
	 * 
	 * @param hiddenStatusForCopy
	 *            .
	 */
	public void setHiddenStatusForCopy(String hiddenStatusForCopy) {
		this.hiddenStatusForCopy = hiddenStatusForCopy;
	}

	/**
	 * 
	 * @return
	 */
	public String copyPage() {
		this.getSession().setAttribute(WebConstants.PRODUCTID,
				this.getHiddenProductIdForCopy());
		return "forwardCopyPage";
	}

	/**
	 * Returns the dateNotValid
	 * 
	 * @return boolean dateNotValid.
	 */
	public boolean isDateNotValid() {
		return dateNotValid;
	}

	/**
	 * Sets the dateNotValid
	 * 
	 * @param dateNotValid
	 *            .
	 */
	public void setDateNotValid(boolean dateNotValid) {
		this.dateNotValid = dateNotValid;
	}

	/**
	 * Returns the testDate
	 * 
	 * @return String testDate.
	 */
	public String getTestDate() {
		SaveTestDataRequest saveTestDataRequest = (SaveTestDataRequest) this
				.getServiceRequest(ServiceManager.SAVE_TEST_DATA_REQUEST);
		saveTestDataRequest.setAction(SaveTestDataRequest.RETRIEVE_TEST_DATA);
		saveTestDataRequest.setContractKeyObject(this.getContractSession()
				.getContractKeyObject());
		SaveTestDataResponse saveTestDataResponse = (SaveTestDataResponse) executeService(saveTestDataRequest);
		if (saveTestDataResponse != null
				&& null != saveTestDataResponse.getTestDate())
			this.setTestDate(WPDStringUtil.getStringDate(saveTestDataResponse
					.getTestDate()));
		return testDate;
	}

	/**
	 * Sets the testDate
	 * 
	 * @param testDate
	 *            .
	 */
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	/**
	 * Returns the testDateInvalid
	 * 
	 * @return boolean testDateInvalid.
	 */
	public boolean isTestDateInvalid() {
		return testDateInvalid;
	}

	/**
	 * Sets the testDateInvalid
	 * 
	 * @param testDateInvalid
	 *            .
	 */
	public void setTestDateInvalid(boolean testDateInvalid) {
		this.testDateInvalid = testDateInvalid;
	}

	/**
	 * Returns the productCheckoutList
	 * 
	 * @return List productCheckoutList.
	 */
	public List getProductCheckoutList() {

		return productCheckoutList;
	}

	/**
	 * Sets the productCheckoutList
	 * 
	 * @param productCheckoutList
	 *            .
	 */
	public void setProductCheckoutList(List productCheckoutList) {
		this.productCheckoutList = productCheckoutList;
	}

	/**
	 * Returns the noteCheckoutList
	 * 
	 * @return List noteCheckoutList.
	 */
	public List getNoteCheckoutList() {

		return noteCheckoutList;
	}

	/**
	 * Sets the noteCheckoutList
	 * 
	 * @param noteCheckoutList
	 *            .
	 */
	public void setNoteCheckoutList(List noteCheckoutList) {
		this.noteCheckoutList = noteCheckoutList;
	}

	/**
	 * @return Returns the benefitComponentMap.
	 */
	public Map getBenefitComponentMap() {
		return benefitComponentMap;
	}

	/**
	 * @param benefitComponentMap
	 *            The benefitComponentMap to set.
	 */
	public void setBenefitComponentMap(Map benefitComponentMap) {
		this.benefitComponentMap = benefitComponentMap;
	}

	/**
	 * @return Returns the forCopy.
	 */
	public boolean isForCopy() {
		return forCopy;
	}

	/**
	 * @param forCopy
	 *            The forCopy to set.
	 */
	public void setForCopy(boolean forCopy) {
		this.forCopy = forCopy;
	}

	/**
	 * @return Returns the copy.
	 */
	public String getCopy() {
		return copy;
	}

	/**
	 * @param copy
	 *            The copy to set.
	 */
	public void setCopy(String copy) {
		this.copy = copy;
	}

	/**
	 * This method gets the list of benefit headings selected by the user for
	 * copy operation
	 * 
	 * @return String
	 */
	public String getSelectedHeadingsAction() {

		Set keys = this.getBenefitComponentMap().keySet();
		Iterator valueIter = keys.iterator();
		while (valueIter.hasNext()) {
			Long valueElement = (Long) valueIter.next();

			// Boolean value = (Boolean) benefitComponentMap.get(valueElement);
		}
		this.forCopy = true;
		this.copy = "true";

		// gets the selected binded values to the map and adds it to a list
		Set keysForStandardBenefit = this.getStandardBenefitMap().keySet();
		Iterator valueStd = keysForStandardBenefit.iterator();
		List selectedList = new ArrayList();
		while (valueStd.hasNext()) {
			String valueStandard = (String) valueStd.next();

			Boolean stdValue = (Boolean) standardBenefitMap.get(valueStandard);

			if (stdValue.booleanValue()) {
				selectedList.add(valueStandard);
			}
		}

		valueStd = keysForStandardBenefit.iterator();
		List unSelectedList = new ArrayList();
		while (valueStd.hasNext()) {
			String valueStandard = (String) valueStd.next();

			Boolean stdValue = (Boolean) standardBenefitMap.get(valueStandard);

			if (!stdValue.booleanValue()) {
				unSelectedList.add(valueStandard);
			}
		}

		List lineList = new ArrayList();

		// adds the selected benefit and its corresponding benefit component to
		// the linelist
		for (int k = 0; k < selectedList.size(); k++) {

			String appendedString = (String) selectedList.get(k);
			String[] appendedList = appendedString.split("\\.");

			int standrdBnftId = Integer.parseInt(appendedList[0]);
			int bnftComponentId = Integer.parseInt(appendedList[1]);

			lineList.add(this
					.getBenefitLineList(standrdBnftId, bnftComponentId));

		}
		getSession().setAttribute(WebConstants.SELECTED_LINE_LIST, lineList);
		getSession().setAttribute(WebConstants.UNSELECTED_LINE_LIST,
				unSelectedList);
		return WebConstants.EMPTY_STRING;
	}

	/**
	 * This method copies the contract with headings to an existing contract
	 * 
	 * @return String
	 */
	public String getExistingContractsAction() {

		this.setSetExistingTrue(true);
		List lineList = new ArrayList();

		// checks for valid data
		if (fieldsValidForCopy()) {
			String dateSegmentId = this.getHiddenDateSegmentIdForCopy();
			String contractKey = this.getHiddenContractKeyForCopy();
			String version = this.getHiddenVersionForCopy();
			String contractId = this.getHiddenContractIdForCopy();
			String status = this.getHiddenStatusForCopy();

			// sets the request parameters to Session
			ContractKeyObject keyObject = new ContractKeyObject();
			keyObject.setContractSysId(Integer.parseInt(contractKey));
			keyObject.setContractId(contractId);
			keyObject.setDateSegmentId(Integer.parseInt(dateSegmentId));
			keyObject.setVersion(Integer.parseInt(version));
			keyObject.setStatus(status);
			this.getContractSession().setContractKeyObject(keyObject);
			if (this.getDisableCheckBox().equals(WebConstants.FALSE)) {
				// gets the benefit headings selected and adds them to a list
				Set keys = this.getBenefitComponentMap().keySet();
				Iterator valueIter = keys.iterator();
				while (valueIter.hasNext()) {
					Long valueElement = (Long) valueIter.next();

					// Boolean value = (Boolean)
					// benefitComponentMap.get(valueElement);
				}
				Set keysForStandardBenefit = this.getStandardBenefitMap()
						.keySet();
				Iterator valueStd = keysForStandardBenefit.iterator();
				List selectedList = new ArrayList();
				while (valueStd.hasNext()) {
					String valueStandard = (String) valueStd.next();

					Boolean stdValue = (Boolean) standardBenefitMap
							.get(valueStandard);

					if (stdValue.booleanValue()) {
						selectedList.add(valueStandard);
					}
				}

				for (int k = 0; k < selectedList.size(); k++) {
					String appendedString = (String) selectedList.get(k);
					String[] appendedList = appendedString.split("\\.");
					int standrdBnftId = Integer.parseInt(appendedList[0]);
					int bnftComponentId = Integer.parseInt(appendedList[1]);
					ContractBenefitHeadings contractBenefitHeadings = new ContractBenefitHeadings();
					contractBenefitHeadings
							.setBenefitComponentId(bnftComponentId);
					contractBenefitHeadings.setStandardBenefitId(standrdBnftId);
					lineList.add(contractBenefitHeadings);
					// lineList.add(this.getBenefitLineList(standrdBnftId,
					// bnftComponentId));
				}
			} else {
				List checkList = new ArrayList();
				checkList = this.getCodedNonCodedBenefitHeadingsList();

				for (int k = 0; k < checkList.size(); k++) {
					ContractBenefitHeadings contractBenefitHeadings = (ContractBenefitHeadings) checkList
							.get(k);

					int standrdBnftId = contractBenefitHeadings
							.getStandardBenefitId();
					int bnftComponentId = contractBenefitHeadings
							.getBenefitComponentId();
					lineList.add(contractBenefitHeadings);
					// lineList.add(this.getBenefitLineList(standrdBnftId,
					// bnftComponentId));
				}

			}
			// getSession()
			// .setAttribute(WebConstants.SELECTED_LINE_LIST, lineList);

			// checks if there any headings selected for copy
			if (lineList != null && lineList.size() == 0) {
				List infoMessage = new ArrayList();
				infoMessage
						.add(new ErrorMessage(WebConstants.NO_HEADINGS_FOUND));
				addAllMessagesToRequest(infoMessage);
				// return WebConstants.EMPTY_STRING;
				return "forwardCopyPage";
			}

			SaveContractHeadingsRequest saveContractHeadingsRequest = (SaveContractHeadingsRequest) this
					.getServiceRequest(ServiceManager.SAVE_CONTRACT_HEADINGS);
			String productId = getSession()
					.getAttribute(WebConstants.PRODUCTID).toString();
			saveContractHeadingsRequest.setProductId(Integer
					.parseInt(productId));
			saveContractHeadingsRequest
					.setSourceDateSegmentId(getContractSession()
							.getContractKeyObject().getDateSegmentId());
			saveContractHeadingsRequest.setSelectedBenefitLineList(lineList);
			saveContractHeadingsRequest
					.setSourceContractSysId(getContractSession()
							.getContractKeyObject().getContractSysId());
			saveContractHeadingsRequest
					.setSourceContractId(getContractSession()
							.getContractKeyObject().getContractId());
			saveContractHeadingsRequest.setSourceVersion(getContractSession()
					.getContractKeyObject().getVersion());

			List baseContractIdList = WPDStringUtil.getListFromTildaString(
					this.contractIdExistingDiv, 2, 1, 2);
			List baseContractSysIdList = WPDStringUtil.getListFromTildaString(
					this.contractIdExistingDiv, 2, 2, 2);
			List baseDateSegmentDateList = WPDStringUtil
					.getListFromTildaString(this.baseContractDtDiv, 2, 1, 2);
			List baseDateSegmentList = WPDStringUtil.getListFromTildaString(
					this.baseContractDtDiv, 2, 2, 2);
			if (null != baseContractSysIdList
					&& baseContractSysIdList.size() > 0) {
				saveContractHeadingsRequest.setTargetContractSysId(Integer
						.parseInt(baseContractSysIdList.get(0).toString()));
			}
			if (null != baseDateSegmentList && baseDateSegmentList.size() > 0) {
				saveContractHeadingsRequest.setTargetDateSegmentId(Integer
						.parseInt(baseDateSegmentList.get(0).toString()));
			}
			if (null != baseContractIdList && baseContractIdList.size() > 0) {
				saveContractHeadingsRequest
						.setTargetContractId(baseContractIdList.get(0)
								.toString());
			}
			if (null != baseDateSegmentDateList
					&& baseDateSegmentDateList.size() > 0) {
				saveContractHeadingsRequest
						.setEffectiveDate(baseDateSegmentDateList.get(0)
								.toString());
			}
			SaveContractHeadingsResponse saveContractHeadingsResponse = null;
			// calls the service
			saveContractHeadingsResponse = (SaveContractHeadingsResponse) executeService(saveContractHeadingsRequest);
		} else {
			addAllMessagesToRequest(this.validationMessages);
		}
		// return WebConstants.EMPTY_STRING;
		return "forwardCopyPage";
	}

	/**
	 * This method validates for the mandatory fields in the copy screen
	 * 
	 * @return
	 */
	private boolean fieldsValidForCopy() {

		validationMessages = new ArrayList();
		boolean requiredFieldForCopy = false;
		this.existingContractIdInvalid = false;
		this.existingContractDtInvalid = false;

		if (this.contractIdExistingDiv == null
				|| WebConstants.EMPTY_STRING.equals(this.contractIdExistingDiv)) {
			existingContractIdInvalid = true;
			requiredFieldForCopy = true;
		}
		if (this.baseContractDtDiv == null
				|| WebConstants.EMPTY_STRING.equals(this.baseContractDtDiv)) {
			existingContractDtInvalid = true;
			requiredFieldForCopy = true;
		}
		if (requiredFieldForCopy) {
			this.validationMessages.add(new ErrorMessage(
					WebConstants.MANDATORY_FIELDS_REQUIRED));
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public List getBenefitLineList(int standardBenefitId, int benefitComponentId) {
		List benefitLineList = new ArrayList();
		RetrieveBenefitLinesRequest retrieveBenefitLinesRequest = (RetrieveBenefitLinesRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_BENEFIT_LINES_REQUEST);

		// gets the request parameters for the retrieve
		String dateSegmentId = this.getHiddenDateSegmentIdForCopy();
		String contractKey = this.getHiddenContractKeyForCopy();
		String version = this.getHiddenVersionForCopy();
		String contractId = this.getHiddenContractIdForCopy();
		String status = this.getHiddenStatusForCopy();

		// sets the request parameters to Session
		ContractKeyObject keyObject = new ContractKeyObject();
		keyObject.setContractSysId(Integer.parseInt(contractKey));
		keyObject.setContractId(contractId);
		keyObject.setDateSegmentId(Integer.parseInt(dateSegmentId));
		keyObject.setVersion(Integer.parseInt(version));
		keyObject.setStatus(status);
		this.getContractSession().setContractKeyObject(keyObject);
		// sets the dateSegmentId to request
		retrieveBenefitLinesRequest.setDateSegmentId(Integer
				.parseInt(dateSegmentId));
		retrieveBenefitLinesRequest.setStandardBenefitId(standardBenefitId);
		retrieveBenefitLinesRequest.setBenefitComponentId(benefitComponentId);
		// retrieves the coded benefit component and its benefits
		RetrieveBenefitLinesResponse retrieveBenefitLinesResponse = (RetrieveBenefitLinesResponse) this
				.executeService(retrieveBenefitLinesRequest);
		if (null != retrieveBenefitLinesResponse)
			benefitLineList = retrieveBenefitLinesResponse
					.getBenefitLinesList();
		return benefitLineList;
	}

	/**
	 * @return Returns the standardBenefitMap.
	 */
	public Map getStandardBenefitMap() {
		return standardBenefitMap;
	}

	/**
	 * @param standardBenefitMap
	 *            The standardBenefitMap to set.
	 */
	public void setStandardBenefitMap(Map standardBenefitMap) {
		this.standardBenefitMap = standardBenefitMap;
	}

	/**
	 * This method performs the copy operation
	 * 
	 * @return
	 */
	public String copyHeadingsAction() {

		RetrieveBasicInfoRequest retrieveBasicInfoRequest = (RetrieveBasicInfoRequest) this
				.getServiceRequest(ServiceManager.RETREIVE_CONTRACT_BASICINFO_REQUEST);
		ContractKeyObject contractKeyObject = getContractSession()
				.getContractKeyObject();
		if (null != contractKeyObject) {
			retrieveBasicInfoRequest.setContractKeyObject(contractKeyObject);
			retrieveBasicInfoRequest.setDateSegmentId(contractKeyObject
					.getDateSegmentId());
		}
		RetrieveBasicInfoResponse retrieveBasicInfoResponse = (RetrieveBasicInfoResponse) executeService(retrieveBasicInfoRequest);
		updateTreeStructure();
		super.setCopyHeadingsMode();

		if (null != retrieveBasicInfoResponse)
			setValuesToBackingBean(retrieveBasicInfoResponse.getContract(),
					retrieveBasicInfoResponse.getDomainDetail());
		super.setContractToSession(retrieveBasicInfoResponse.getContract());

		// gets the dateSegmentId of the base contract
		int basedateid = retrieveBasicInfoResponse.getContract()
				.getBaseDateSegmentSysId();
		getSession().setAttribute(WebConstants.BASE_DATE_ID,
				new Integer(basedateid));

		if (null != getSession().getAttribute(
				WebConstants.SESSION_TREE_STATE_CONTRACT))
			getSession().removeAttribute(
					WebConstants.SESSION_TREE_STATE_CONTRACT);

		return "createContract";
	}

	/**
	 * Returns the requiredHeadQuarters
	 * 
	 * @return boolean requiredHeadQuarters.
	 */
	public boolean isRequiredHeadQuarters() {
		return requiredHeadQuarters;
	}

	/**
	 * Sets the requiredHeadQuarters
	 * 
	 * @param requiredHeadQuarters
	 *            .
	 */
	public void setRequiredHeadQuarters(boolean requiredHeadQuarters) {
		this.requiredHeadQuarters = requiredHeadQuarters;
	}

	/**
	 * Returns the headQuartersState
	 * 
	 * @return String headQuartersState.
	 */
	public String getHeadQuartersState() {
		return headQuartersState;
	}

	/**
	 * Sets the headQuartersState
	 * 
	 * @param headQuartersState
	 *            .
	 */
	public void setHeadQuartersState(String headQuartersState) {
		this.headQuartersState = headQuartersState;
	}

	/**
	 * Returns the product
	 * 
	 * @return String product.
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * Sets the product
	 * 
	 * @param product
	 *            .
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * Returns the requiredProduct
	 * 
	 * @return boolean requiredProduct.
	 */
	public boolean isRequiredProduct() {
		return requiredProduct;
	}

	/**
	 * Sets the requiredProduct
	 * 
	 * @param requiredProduct
	 *            .
	 */
	public void setRequiredProduct(boolean requiredProduct) {
		this.requiredProduct = requiredProduct;
	}

	/**
	 * Returns the contractIdExistingDiv
	 * 
	 * @return String contractIdExistingDiv.
	 */
	public String getContractIdExistingDiv() {
		return contractIdExistingDiv;
	}

	/**
	 * Sets the contractIdExistingDiv
	 * 
	 * @param contractIdExistingDiv
	 *            .
	 */
	public void setContractIdExistingDiv(String contractIdExistingDiv) {
		this.contractIdExistingDiv = contractIdExistingDiv;
	}

	/**
	 * Returns the existingContractIdInvalid
	 * 
	 * @return boolean existingContractIdInvalid
	 * 
	 */
	public boolean isExistingContractIdInvalid() {
		return existingContractIdInvalid;
	}

	/**
	 * Sets the existingContractIdInvalid
	 * 
	 * @param existingContractIdInvalid
	 *            .
	 */
	public void setExistingContractIdInvalid(boolean existingContractIdInvalid) {
		this.existingContractIdInvalid = existingContractIdInvalid;
	}

	/**
	 * Returns the existingContractDtInvalid
	 * 
	 * @return boolean existingContractDtInvalid.
	 */
	public boolean isExistingContractDtInvalid() {
		return existingContractDtInvalid;
	}

	/**
	 * Sets the existingContractDtInvalid
	 * 
	 * @param existingContractDtInvalid
	 *            .
	 */
	public void setExistingContractDtInvalid(boolean existingContractDtInvalid) {
		this.existingContractDtInvalid = existingContractDtInvalid;
	}

	/**
	 * Returns the noteStatus
	 * 
	 * @return String noteStatus.
	 */
	public String getNoteStatus() {
		return noteStatus;
	}

	/**
	 * Sets the noteStatus
	 * 
	 * @param noteStatus
	 *            .
	 */
	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}

	/**
	 * Returns the productStatus
	 * 
	 * @return String productStatus.
	 */
	public String getProductStatus() {
		return productStatus;
	}

	/**
	 * Sets the productStatus
	 * 
	 * @param productStatus
	 *            .
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	/**
	 * @return Returns the setExistingTrue.
	 */
	public boolean isSetExistingTrue() {
		return setExistingTrue;
	}

	/**
	 * @param setExistingTrue
	 *            The setExistingTrue to set.
	 */
	public void setSetExistingTrue(boolean setExistingTrue) {
		this.setExistingTrue = setExistingTrue;
	}

	/**
	 * Returns the testDateSegmentOptionList
	 * 
	 * @return List testDateSegmentOptionList.
	 */
	public List getTestDateSegmentOptionList() {
		testDateSegmentOptionList = new ArrayList();
		SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
				.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
		SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
		String contractSysId = getRequest().getParameter("contractkey");
		this.getContractSession().setContractSysId(
				Integer.parseInt(contractSysId));
		saveContractBasicInfoRequest.getContractVO().setContractSysId(
				Integer.parseInt(contractSysId));
		saveContractBasicInfoRequest
				.setAction(SaveContractBasicInfoRequest.SEND_TO_TEST_PROCESS);
		saveContractBasicInfoResponse = (SaveContractBasicInfoResponse) executeService(saveContractBasicInfoRequest);
		if (saveContractBasicInfoResponse != null) {
			if (saveContractBasicInfoResponse.isTestDateSegments()) {
				ReferenceDataImpl referenceDataImpl0 = new ReferenceDataImpl();
				referenceDataImpl0.setCode("regularDateSegment");
				referenceDataImpl0
						.setDescription("Schedule regular date segments for test");
				testDateSegmentOptionList.add(0, referenceDataImpl0);
				ReferenceDataImpl referenceDataImpl1 = new ReferenceDataImpl();
				referenceDataImpl1.setCode("testDateSegment");
				referenceDataImpl1
						.setDescription("Schedule test date segments for test");
				testDateSegmentOptionList.add(0, referenceDataImpl1);
			} else {
				testDateSegmentOptionList = null;
			}
		}

		return testDateSegmentOptionList;
	}

	/**
	 * Sets the testDateSegmentOptionList
	 * 
	 * @param testDateSegmentOptionList
	 *            .
	 */
	public void setTestDateSegmentOptionList(List testDateSegmentOptionList) {
		this.testDateSegmentOptionList = testDateSegmentOptionList;
	}

	/**
	 * Returns the printValue
	 * 
	 * @return String printValue.
	 */
	public String getPrintValue() {

		String requestForPrint = getRequest().getParameter(
				"printContractBasicInfo");
		if (null != requestForPrint
				&& !requestForPrint.equals(WebConstants.EMPTY_STRING)) {
			printValue = requestForPrint;
		} else {
			printValue = WebConstants.EMPTY_STRING;
		}
		return printValue;
	}

	/**
	 * Sets the printValue
	 * 
	 * @param printValue
	 *            .
	 */
	public void setPrintValue(String printValue) {
		this.printValue = printValue;
	}

	/**
	 * Returns the productCheckoutList
	 * 
	 * @return List productCheckoutList.
	 */
	public List getProductCopyList() {

		return productCopyList;
	}

	/**
	 * Returns the noteList
	 * 
	 * @return List noteList.
	 */
	public List getNoteCopyList() {
		return noteCopyList;
	}

	/**
	 * @param productCopyList
	 * 
	 *            Sets the productCopyList.
	 */
	public void setProductCopyList(List productCopyList) {
		this.productCopyList = productCopyList;
	}

	/**
	 * @return hiddenNoteStatusForCopy
	 * 
	 *         Returns the hiddenNoteStatusForCopy.
	 */
	public String getHiddenNoteStatusForCopy() {
		String noteStatus = getRequest().getParameter(WebConstants.NOTE_STATUS);
		if (null != noteStatus)
			this.hiddenNoteStatusForCopy = noteStatus;

		return hiddenNoteStatusForCopy;
	}

	/**
	 * @param hiddenNoteStatusForCopy
	 * 
	 *            Sets the hiddenNoteStatusForCopy.
	 */
	public void setHiddenNoteStatusForCopy(String hiddenNoteStatusForCopy) {
		this.hiddenNoteStatusForCopy = hiddenNoteStatusForCopy;
	}

	/**
	 * @return hiddenProductStatusForCopy
	 * 
	 *         Returns the hiddenProductStatusForCopy.
	 */
	public String getHiddenProductStatusForCopy() {

		String productStatus = getRequest().getParameter(
				WebConstants.PRODUCT_STATUS);
		if (null != productStatus)
			this.hiddenProductStatusForCopy = productStatus;

		return hiddenProductStatusForCopy;
	}

	/**
	 * @param hiddenProductStatusForCopy
	 * 
	 *            Sets the hiddenProductStatusForCopy.
	 */
	public void setHiddenProductStatusForCopy(String hiddenProductStatusForCopy) {
		this.hiddenProductStatusForCopy = hiddenProductStatusForCopy;
	}

	/**
	 * @return hiddenLatestCopyStatus
	 * 
	 *         Returns the hiddenLatestCopyStatus.
	 */
	public String getHiddenLatestCopyStatus() {
		return hiddenLatestCopyStatus;
	}

	/**
	 * @param hiddenLatestCopyStatus
	 * 
	 *            Sets the hiddenLatestCopyStatus.
	 */
	public void setHiddenLatestCopyStatus(String hiddenLatestCopyStatus) {
		this.hiddenLatestCopyStatus = hiddenLatestCopyStatus;
	}

	/**
	 * @param noteCopyList
	 * 
	 *            Sets the noteCopyList.
	 */
	public void setNoteCopyList(List noteCopyList) {
		this.noteCopyList = noteCopyList;
	}

	/**
	 * @return hiddenDateSegmentType
	 * 
	 *         Returns the hiddenDateSegmentType.
	 */
	public HtmlInputHidden getHiddenDateSegmentType() {
		return hiddenDateSegmentType;
	}

	/**
	 * @param hiddenDateSegmentType
	 * 
	 *            Sets the hiddenDateSegmentType.
	 */
	public void setHiddenDateSegmentType(HtmlInputHidden hiddenDateSegmentType) {
		this.hiddenDateSegmentType = hiddenDateSegmentType;
	}

	/**
	 * 
	 * @return
	 */
	private List retrieveNoteList() {
		noteCheckoutList = new ArrayList();
		DateSegment dateSegment = null;
		SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
				.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
		SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
		String contractSysId = getRequest().getParameter(
				WebConstants.CONTRACT_KEY);
		String dateSegmentId = getRequest().getParameter("dateId");
		String Prodid = getRequest().getParameter("productid");
		this.getContractSession().setContractSysId(
				Integer.parseInt(contractSysId));
		saveContractBasicInfoRequest.getContractVO().setContractSysId(
				Integer.parseInt(contractSysId));
		saveContractBasicInfoRequest.getContractVO().setDateSegmentSysId(
				Integer.parseInt(dateSegmentId));
		saveContractBasicInfoRequest.getContractVO().setProductSysId(
				getContractSession().getProductId());
		saveContractBasicInfoRequest.getContractVO().setOldProductSysId(
				Integer.parseInt(Prodid));
		saveContractBasicInfoRequest
				.setAction(SaveContractBasicInfoRequest.CHECKOUT_PROCESS_CONT);
		saveContractBasicInfoResponse = (SaveContractBasicInfoResponse) executeService(saveContractBasicInfoRequest);
		if (saveContractBasicInfoResponse != null
				&& saveContractBasicInfoResponse.getContract() != null
				&& saveContractBasicInfoResponse.getContract()
						.getDateSegmentList() != null) {
			List dateSegmentList = saveContractBasicInfoResponse.getContract()
					.getDateSegmentList();
			int dateSegmentCount = dateSegmentList.size();
			for (int i = 0; i < dateSegmentCount; i++) {
				dateSegment = (DateSegment) dateSegmentList.get(i);
				ReferenceDataImpl referenceDataImpl0 = new ReferenceDataImpl();
				String effDate = WPDStringUtil.convertDateToString(dateSegment
						.getEffectiveDate());
				String expDate = WPDStringUtil.convertDateToString(dateSegment
						.getExpiryDate());
				referenceDataImpl0.setCode(effDate);
				referenceDataImpl0.setDescription(effDate + " - " + expDate);
				noteCheckoutList.add(referenceDataImpl0);
			}
		} else {
			noteCheckoutList = null;
		}
		return noteCheckoutList;
	}

	/**
	 * 
	 * @return
	 */
	private List retreieveProductList() {
		productCheckoutList = new ArrayList();
		SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
				.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
		SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
		String contractSysId = getRequest().getParameter(
				WebConstants.CONTRACT_KEY);

		if (!this.requestFromMigrationWizard) {
			String dateSegmentIdString = getRequest().getParameter("dateId");

			// the process is create DS.check from session
			if (null != (Integer) getSession().getAttribute(
					"CHECKOUT_DATESEGMENT_ID")) {
				Integer dateSegmentId = (Integer) getSession().getAttribute(
						"CHECKOUT_DATESEGMENT_ID");
				saveContractBasicInfoRequest.getContractVO()
						.setDateSegmentSysId(dateSegmentId.intValue());
			} else {
				saveContractBasicInfoRequest.getContractVO()
						.setDateSegmentSysId(
								Integer.parseInt(dateSegmentIdString));
			}
		} else {
			saveContractBasicInfoRequest
					.setRequestFromMigrationWizard(this.requestFromMigrationWizard);
			saveContractBasicInfoRequest.getContractVO().setContractId(
					this.contractIdDiv);
			saveContractBasicInfoRequest.getContractVO().setProductSysId(
					Integer.parseInt(this.product));
		}

		this.getContractSession().setContractSysId(
				Integer.parseInt(contractSysId));

		saveContractBasicInfoRequest.getContractVO().setContractSysId(
				Integer.parseInt(contractSysId));

		saveContractBasicInfoRequest
				.setAction(SaveContractBasicInfoRequest.CHECKOUT_PROCESS);
		saveContractBasicInfoResponse = (SaveContractBasicInfoResponse) executeService(saveContractBasicInfoRequest);
		if (saveContractBasicInfoResponse != null) {
			int productNewVersion = saveContractBasicInfoResponse
					.getIfLatestProductExist();
			this.getContractSession().setProductId(productNewVersion);
			if (0 != productNewVersion) {
				ReferenceDataImpl referenceDataImpl0 = new ReferenceDataImpl();
				referenceDataImpl0
						.setCode(this.requestFromMigrationWizard ? productNewVersion
								+ ""
								: WebConstants.REPLACE_PROCUCT);
				referenceDataImpl0
						.setDescription("Replace the current version of the Product with latest version");
				productCheckoutList.add(0, referenceDataImpl0);
				ReferenceDataImpl referenceDataImpl1 = new ReferenceDataImpl();
				referenceDataImpl1.setCode(WebConstants.CONTINUE_PRODUCT);
				referenceDataImpl1
						.setDescription("Continue with the current version of Product");
				productCheckoutList.add(0, referenceDataImpl1);
			} else {
				productCheckoutList = null;
			}
		}
		return productCheckoutList;
	}

	/**
	 * @return checkoutValues
	 * 
	 *         Returns the checkoutValues.
	 */
	public String getCheckoutValues() {
		String checkoutIndicator = (String) getSession().getAttribute(
				"CHECKOUT_DATESEGMENT_INDICATOR");
		String testDateSegment = getRequest().getParameter("dsType");
		if ((!("TEST".equals(checkoutIndicator)))
				&& (!"Y".equals(testDateSegment))) {
			String migrationRequest = getRequest().getParameter("migRequest");
			if (null == migrationRequest) {
				this.noteCheckoutList = new ArrayList();// retrieveNoteList();
			} else {
				this.noteCheckoutList = new ArrayList();
				this.contractIdDiv = getRequest().getParameter("contractId");
				this.product = getRequest().getParameter("productId");
				this.requestFromMigrationWizard = true;
			}
			this.productCheckoutList = retreieveProductList();
		} else {
			this.noteCheckoutList = new ArrayList();
		}
		return checkoutValues;
	}

	/**
	 * @param checkoutValues
	 * 
	 *            Sets the checkoutValues.
	 */
	public void setCheckoutValues(String checkoutValues) {
		this.checkoutValues = checkoutValues;
	}

	/**
	 * @return loadList
	 * 
	 *         Returns the loadList.
	 */
	public String getLoadList() {

		this.productCopyList = retrieveProductCopyList();
		this.noteCopyList = retrieveNoteCopyList();
		return loadList;
	}

	/**
	 * @param loadList
	 * 
	 *            Sets the loadList.
	 */
	public void setLoadList(String loadList) {
		this.loadList = loadList;
	}

	/**
	 * 
	 * @return
	 */
	private List retrieveProductCopyList() {
		productCopyList = new ArrayList();
		SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
				.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
		SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
		String contractSysId = getRequest().getParameter(
				WebConstants.CONTRACT_KEY);
		this.getContractSession().setContractSysId(
				Integer.parseInt(contractSysId));
		saveContractBasicInfoRequest.getContractVO().setContractSysId(
				Integer.parseInt(contractSysId));
		String productId = this.getHiddenProductIdForCopy();
		if (null != productId)
			saveContractBasicInfoRequest.setProductSysId(Integer
					.parseInt(productId));

		// added for enhancement & versioning
		String dateSegmentId = this.getHiddenDateSegmentIdForCopy();
		if (null != dateSegmentId)
			saveContractBasicInfoRequest.setDateSegmentIdForCopy(Integer
					.parseInt(dateSegmentId));
		saveContractBasicInfoRequest
				.setAction(SaveContractBasicInfoRequest.COPY_PROCESS);
		saveContractBasicInfoResponse = (SaveContractBasicInfoResponse) executeService(saveContractBasicInfoRequest);
		if (saveContractBasicInfoResponse != null) {
			this.getContractSession().setProductId(
					saveContractBasicInfoResponse.getIfLatestProductExist());
			if (0 != saveContractBasicInfoResponse.getIfLatestProductExist()) {
				ReferenceDataImpl referenceDataImpl0 = new ReferenceDataImpl();
				referenceDataImpl0.setCode(WebConstants.REPLACE_PROCUCT);
				referenceDataImpl0
						.setDescription("Replace the new Contract to be created with the latest version of Product");
				productCopyList.add(0, referenceDataImpl0);
				ReferenceDataImpl referenceDataImpl1 = new ReferenceDataImpl();
				referenceDataImpl1.setCode(WebConstants.CONTINUE_PRODUCT);
				referenceDataImpl1
						.setDescription("Continue with the current version of Product for the new Contract to be created ");
				productCopyList.add(0, referenceDataImpl1);
			} else {
				productCopyList = null;
			}
		}
		return productCopyList;

	}

	/**
	 * 
	 * @return
	 */
	private List retrieveNoteCopyList() {

		noteCopyList = new ArrayList();
		SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
				.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
		SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
		String contractSysId = getRequest().getParameter(
				WebConstants.CONTRACT_KEY);
		String dateSegmentId = this.getHiddenDateSegmentIdForCopy();
		String productId = this.getHiddenProductIdForCopy();
		this.getContractSession().setContractSysId(
				Integer.parseInt(contractSysId));
		saveContractBasicInfoRequest.getContractVO().setContractSysId(
				Integer.parseInt(contractSysId));
		saveContractBasicInfoRequest.getContractVO().setDateSegmentSysId(
				Integer.parseInt(dateSegmentId));
		saveContractBasicInfoRequest.getContractVO().setProductSysId(
				getContractSession().getProductId());
		saveContractBasicInfoRequest.getContractVO().setOldProductSysId(
				Integer.parseInt(productId));
		saveContractBasicInfoRequest
				.setAction(SaveContractBasicInfoRequest.COPY_PROCESS_CONT);
		saveContractBasicInfoResponse = (SaveContractBasicInfoResponse) executeService(saveContractBasicInfoRequest);
		if (saveContractBasicInfoResponse != null) {
			if (saveContractBasicInfoResponse.isIfLatestNoteExist()) {
				ReferenceDataImpl referenceDataImpl0 = new ReferenceDataImpl();
				referenceDataImpl0.setCode(WebConstants.REPLACE_NOTE);
				referenceDataImpl0
						.setDescription("Replace the new Contract to be created with the latest version of Note");
				noteCopyList.add(0, referenceDataImpl0);
				ReferenceDataImpl referenceDataImpl1 = new ReferenceDataImpl();
				referenceDataImpl1.setCode(WebConstants.CONTINUE_NOTE);
				referenceDataImpl1
						.setDescription("Continue with the current version of Note for the new Contract to be created");
				noteCopyList.add(0, referenceDataImpl1);
			} else {
				noteCopyList = null;
			}
		}

		return noteCopyList;
	}

	/**
	 * @return Returns the membershipList.
	 */
	public List getMembershipList() {
		return membershipList;
	}

	/**
	 * @param membershipList
	 *            The membershipList to set.
	 */
	public void setMembershipList(List membershipList) {
		this.membershipList = membershipList;
	}

	public void setPrintValueMembership(String printValueMembership) {
		this.printValueMembership = printValueMembership;
	}

	/**
	 * @return Returns the printValueMembership.
	 */
	public String getPrintValueMembership() {
		String requestForPrint = getRequest().getParameter(
				"printValueForMembership");
		if (null != requestForPrint
				&& !requestForPrint.equals(WebConstants.EMPTY_STRING)) {
			printValueMembership = requestForPrint;
		} else {
			printValueMembership = WebConstants.EMPTY_STRING;
		}

		return printValueMembership;
	}

	/**
	 * @return Returns the valueToMaxResult.
	 */
	public String getValueToMembership() {
		MembershipInfoRequest membershipInfoRequest = (MembershipInfoRequest) this
				.getServiceRequest(ServiceManager.MEMBERSHIP_INFO);
		membershipInfoRequest.setContractKeyObject(getContractSession()
				.getContractKeyObject());
		MembershipInfoResponse membershipInfoResponse = null;
		membershipInfoResponse = (MembershipInfoResponse) executeService(membershipInfoRequest);
		if (null != membershipInfoResponse)
			this.membershipList = membershipInfoResponse.getMembershipList();
		List messages = (List) getSession().getAttribute("MESSAGE_LIST_MEMBER");
		if (null != messages && messages.size() != 0) {
			addAllMessagesToRequest(messages);
			getSession().removeAttribute("MESSAGE_LIST_MEMBER");
		}
		return "MEMBERSHIPINFOVIEW";

	}

	/**
	 * @param valueToMembership
	 *            The valueToMembership to set.
	 */
	public void setValueToMembership(String valueToMembership) {
		this.valueToMembership = valueToMembership;
	}

	/**
	 * @return Returns the notMandate.
	 */
	public boolean isNotMandate() {
		return notMandate;
	}

	/**
	 * @param notMandate
	 *            The notMandate to set.
	 */
	public void setNotMandate(boolean notMandate) {
		this.notMandate = notMandate;
	}

	/**
	 * Returns the disableCheckBox
	 * 
	 * @return String disableCheckBox.
	 */
	public String getDisableCheckBox() {
		return disableCheckBox;
	}

	/**
	 * Sets the disableCheckBox
	 * 
	 * @param disableCheckBox
	 *            .
	 */
	public void setDisableCheckBox(String disableCheckBox) {
		this.disableCheckBox = disableCheckBox;
	}

	/**
	 * Returns the codedNonCodedBenefitHeadingsList
	 * 
	 * @return List codedNonCodedBenefitHeadingsList.
	 */
	public List getCodedNonCodedBenefitHeadingsList() {

		List codedNonCodedHeadingsList = new ArrayList();
		RetrieveCodedNonCodedBenefitHeadingsRequest retrieveCodedNonCodedHeadingsRequest = (RetrieveCodedNonCodedBenefitHeadingsRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_CODED_NONCODED_BENEFIT_HEADINGS_REQUEST);

		// gets the request parameters for the retrieve
		String dateSegmentId = this.getHiddenDateSegmentIdForCopy();
		String contractKey = this.getHiddenContractKeyForCopy();
		String version = this.getHiddenVersionForCopy();
		String contractId = this.getHiddenContractIdForCopy();
		String status = this.getHiddenStatusForCopy();
		String productId = this.getHiddenProductIdForCopy();

		// sets the request parameters to Session
		if (null != dateSegmentId
				&& !dateSegmentId.equals(WebConstants.EMPTY_STRING)) {
			ContractKeyObject keyObject = new ContractKeyObject();
			keyObject.setContractSysId(Integer.parseInt(contractKey));
			keyObject.setContractId(contractId);
			keyObject.setDateSegmentId(Integer.parseInt(dateSegmentId));
			keyObject.setVersion(Integer.parseInt(version));
			keyObject.setStatus(status);
			this.getContractSession().setContractKeyObject(keyObject);
			// sets the dateSegmentId to request
			retrieveCodedNonCodedHeadingsRequest.setDateSegmentId(Integer
					.parseInt(dateSegmentId));

			retrieveCodedNonCodedHeadingsRequest.setProductId(Integer
					.parseInt(productId));
		} else {
			retrieveCodedNonCodedHeadingsRequest
					.setDateSegmentId(getContractSession()
							.getContractKeyObject().getDateSegmentId());
			this.setSetExistingTrue(true);
		}

		// retrieves the coded benefit component and its benefits
		RetrieveCodedNonCodedBenefitHeadingsResponse retrieveCodedNonCodedBenefitHeadingsResponse = (RetrieveCodedNonCodedBenefitHeadingsResponse) this
				.executeService(retrieveCodedNonCodedHeadingsRequest);
		if (null != retrieveCodedNonCodedBenefitHeadingsResponse)
			codedNonCodedHeadingsList = retrieveCodedNonCodedBenefitHeadingsResponse
					.getCodedNonCodedBenefitHeadingsList();
		return codedNonCodedHeadingsList;

	}

	/**
	 * Sets the codedNonCodedBenefitHeadingsList
	 * 
	 * @param codedNonCodedBenefitHeadingsList
	 *            .
	 */
	public void setCodedNonCodedBenefitHeadingsList(
			List codedNonCodedBenefitHeadingsList) {
		this.codedNonCodedBenefitHeadingsList = codedNonCodedBenefitHeadingsList;
	}

	/**
	 * @return Returns the breadCrumpText.
	 */
	public String getBreadCrumpText() {
		this.breadCrumpText = "Contract Development >> Contract ("
				+ getContractNameFromSession() + ") >> Print";
		return breadCrumpText;
	}

	/**
	 * @param breadCrumpText
	 *            The breadCrumpText to set.
	 */
	public void setBreadCrumpText(String breadCrumpText) {
		this.breadCrumpText = breadCrumpText;
	}

	/**
	 * @return Returns the selectedEffectiveDate.
	 */
	public Date getSelectedEffectiveDate() {
		return selectedEffectiveDate;
	}

	/**
	 * @param selectedEffectiveDate
	 *            The selectedEffectiveDate to set.
	 */
	public void setSelectedEffectiveDate(Date selectedEffectiveDate) {
		this.selectedEffectiveDate = selectedEffectiveDate;
	}

	/**
	 * @return Returns the hasValidationErrors.
	 */
	public boolean isHasValidationErrors() {
		return hasValidationErrors;
	}

	/**
	 * @param hasValidationErrors
	 *            The hasValidationErrors to set.
	 */
	public void setHasValidationErrors(boolean hasValidationErrors) {
		this.hasValidationErrors = hasValidationErrors;
	}

	/**
	 * @return Returns the deletedRulesList.
	 */
	public List getDeletedRulesList() {
		return deletedRulesList;
	}

	/**
	 * @param deletedRulesList
	 *            The deletedRulesList to set.
	 */
	public void setDeletedRulesList(List deletedRulesList) {
		this.deletedRulesList = deletedRulesList;
	}

	/**
	 * @return Returns the loadRuleValidationPopUp.
	 */
	public String getLoadRuleValidationPopUp() {
		if (null != getSession()) {
			if (null != getSession().getAttribute(
					WebConstants.SESSION_DELETED_RULES_LIST))
				this.deletedRulesList = (List) getSession().getAttribute(
						WebConstants.SESSION_DELETED_RULES_LIST);
			if (null != getSession().getAttribute(
					WebConstants.SESSION_UNCODED_RULES_LIST))
				this.unCodedRulesList = (List) getSession().getAttribute(
						WebConstants.SESSION_UNCODED_RULES_LIST);
			this.contractIdDiv = getContractSession().getContractKeyObject()
					.getContractId();
			this.version = getContractSession().getContractKeyObject()
					.getVersion();
		}
		return loadRuleValidationPopUp;
	}
	
	
	

	
	 public int getInit() {
		// System.out.println("Inside Init");
		 Logger.logInfo("Inside Init");
	        String contractIdFromRequest = getRequest().getParameter(WebConstants.CONTRACT_ID);
	        String beanName = "contractTreeBackingBean";
	        //viewContractAction();
	        if(contractIdFromRequest != null) {
	            SessionCleanUp.removeManagedBean(beanName);
	        }
	        //System.out.println("Ending Init");
	        Logger.logInfo("Ending Init");
	        return init;
	 }
	    
	 public void setInitVendor(int i) {
        this.init = i;
     }
	                
	  //sscr 17571
	    public int getInitVendor() {
	    	if(null!=this.getSession().getAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST)){
	    		this.contractIdDiv = getContractSession().getContractKeyObject()
						.getContractId();
	    		this.version = getContractSession().getContractKeyObject()
						.getVersion();
	    		this.carvEffectiveDate = getContractSession().getContractKeyObject().getEffectiveDate();
		    	this.carvEndDate = getContractSession().getContractKeyObject().getExpiryDate();
            	this.vendorMap =  (Map<String, List>) getSession().getAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST);
                            Set dateSegmentKeys = vendorMap.keySet();
                            Iterator itrDateSegment = dateSegmentKeys.iterator();
                            List dateSegmentList = new ArrayList();
                            while(itrDateSegment.hasNext()){
                                 DateSegment dateSegment = new DateSegment();
                                 String dateRan = itrDateSegment.next().toString();
                                 dateSegment.setDaterange(dateRan);
                                 
                                 dateSegmentList.add(dateSegment);
                            	 
                            }
                            
                            this.searchDateSegmentList = dateSegmentList;                                       
            }
	    	// SSCR 17571 - Tab impl ebx or vendor info tab hiding
	    	
            if(null != getSession().getAttribute("VendorFlag")){
                    this.vendorFlag = (String)getSession().getAttribute("VendorFlag");
            }
            if(null != getSession().getAttribute("WebServiceFlag")){
                this.webServiceFlag = (String)getSession().getAttribute("WebServiceFlag");
  		  	}
	    	return init;
	}
	    

	  public String fetchVendorInfo(){           
          
         
          setCheckVendorInfoPopup("true");
          String dateSegment = getDateSegment();
          
          this.vendorMap =  (Map<String, List>) getSession().getAttribute(WebConstants.SESSION_NOTANS_VENDOR_LIST);
          this.searchVendorResultList = (List)vendorMap.get(dateSegment);
           return "";
      }
	public void setLoadVendorPopUp(String loadVendorPopUp) {
		this.loadVendorPopUp = loadVendorPopUp;
	}

//sscr 17571-end
	/**
	 * @param loadRuleValidationPopUp
	 *            The loadRuleValidationPopUp to set.
	 */
	public void setLoadRuleValidationPopUp(String loadRuleValidationPopUp) {
		this.loadRuleValidationPopUp = loadRuleValidationPopUp;
	}

	/**
	 * @return Returns the unCodedRulesList.
	 */
	public List getUnCodedRulesList() {
		return unCodedRulesList;
	}

	/**
	 * @param unCodedRulesList
	 *            The unCodedRulesList to set.
	 */
	public void setUnCodedRulesList(List unCodedRulesList) {
		this.unCodedRulesList = unCodedRulesList;
	}

	/**
	 * @return Returns the contractTransferLogList.
	 */
	/*
	 * public List getContractTransferLogList() { return
	 * contractTransferLogList; }
	 *//**
	 * @param contractTransferLogList
	 *            The contractTransferLogList to set.
	 */
	/*
	 * public void setContractTransferLogList(List contractTransferLogList) {
	 * this.contractTransferLogList = contractTransferLogList; }
	 */

	/**
	 * @return Returns the viewMoreFlag.
	 */
	public boolean isViewMoreFlag() {
		return viewMoreFlag;
	}

	/**
	 * @param viewMoreFlag
	 *            The viewMoreFlag to set.
	 */
	public void setViewMoreFlag(boolean viewMoreFlag) {
		this.viewMoreFlag = viewMoreFlag;
	}

	/**
	 * This method sets the viewMoreFlag to true
	 * 
	 * @return
	 */
	public String viewMoreInList() {
		viewMoreFlag = true;
		return " ";
	}

	/**
	 * This method sets the viewMoreFlag to false
	 * 
	 * @return
	 */
	public String viewLatestRecordFromList() {
		viewMoreFlag = false;
		return " ";
	}

	/**
	 * @return Returns the logPageStatus.
	 */
	public String getLogPageStatus() {
		return logPageStatus;
	}

	/**
	 * @param logPageStatus
	 *            The logPageStatus to set.
	 */
	public void setLogPageStatus(String logPageStatus) {
		this.logPageStatus = logPageStatus;
	}

	/**
	 * This method checks for the flag and sends the respective list to the
	 * page.
	 * 
	 * @return Returns the contractTransferResultList.
	 */
	public List getContractTransferResultList() {

		contractTransferLogHalfList = new ArrayList();

		if (null != contractTransferLogFullList
				&& !(contractTransferLogFullList.isEmpty())) {
			if (viewMoreFlag == false) {
				if (contractTransferLogFullList.size() > 20) {
					for (int i = 0; i < 20; i++) {
						contractTransferLogHalfList
								.add(contractTransferLogFullList.get(i));
					}
					return contractTransferLogHalfList;
				} else {
					return contractTransferLogFullList;
				}
			}
		}
		return contractTransferLogFullList;
	}

	/**
	 * @param contractTransferResultList
	 *            The contractTransferResultList to set.
	 */
	public void setContractTransferResultList(List contractTransferResultList) {
		this.contractTransferResultList = contractTransferResultList;
	}

	/**
	 * Sets value for contractTransferLogFullList from response.
	 * 
	 * @return Returns the transferLog.
	 */
	public String getTransferLog() {
		ContractTransferLogBO contractTransferLogBO = new ContractTransferLogBO();
		ContractTransferLogRequest contractTransferLogRequest = (ContractTransferLogRequest) this
				.getServiceRequest(ServiceManager.CONTRACT_TRANSFER_LOG_REQUEST);
		if (null != getContractSession()
				&& null != getContractSession().getContractKeyObject()) {
			contractTransferLogBO.setDateSegmentId(getContractSession()
					.getContractKeyObject().getDateSegmentId());
			contractTransferLogBO.setContractSysId(getContractSession()
					.getContractKeyObject().getContractSysId());
			contractTransferLogBO.setVersion(getContractSession()
					.getContractKeyObject().getVersion());
			contractTransferLogBO.setEffectiveDate(this.getContractSession()
					.getContractKeyObject().getEffectiveDate());
			this.setStartDate(this.getContractSession().getContractKeyObject()
					.getEffectiveDate());
			this.setEndDate(this.getContractSession().getContractKeyObject()
					.getExpiryDate());
		}
		contractTransferLogRequest
				.setContractTransferLogBO(contractTransferLogBO);
		ContractTransferLogResponse contractTransferLogResponse = (ContractTransferLogResponse) this
				.executeService(contractTransferLogRequest);
		contractTransferLogFullList = contractTransferLogResponse
				.getContractTransferLogList();
		if (null != contractTransferLogFullList
				&& contractTransferLogFullList.size() <= 20) {
			this.isViewTwenty = "true";
		}
		String contractId = getContractNameFromSession();
		this.setBreadCrumbText(WebConstants.BREADCRUMB_FIRST_STRING
				+ contractId + WebConstants.BREADCRUMB_SECOND_STRING);
		return WebConstants.TRANSFERLOG_RETURN;
	}

	/**
	 * @param transferLog
	 *            The transferLog to set.
	 */
	public void setTransferLog(String transferLog) {
		this.transferLog = transferLog;
	}

	/**
	 * @return Returns the contractTransferLogFullList.
	 */
	public List getContractTransferLogFullList() {
		return contractTransferLogFullList;
	}

	/**
	 * @param contractTransferLogFullList
	 *            The contractTransferLogFullList to set.
	 */
	public void setContractTransferLogFullList(List contractTransferLogFullList) {
		this.contractTransferLogFullList = contractTransferLogFullList;
	}

	/**
	 * @return Returns the breadCrumpTextForTransferLogPrint.
	 */
	public String getBreadCrumpTextForTransferLogPrint() {
		this.breadCrumpTextForTransferLogPrint = "Contract Development >> Contract ("
				+ getContractNameFromSession()
				+ ") >> View Date Segment Transfer Log >> Print";
		return breadCrumpTextForTransferLogPrint;
	}

	/**
	 * @param breadCrumpTextForTransferLogPrint
	 *            The breadCrumpTextForTransferLogPrint to set.
	 */
	public void setBreadCrumpTextForTransferLogPrint(
			String breadCrumpTextForTransferLogPrint) {
		this.breadCrumpTextForTransferLogPrint = breadCrumpTextForTransferLogPrint;
	}

	/**
	 * @return Returns the transferLogPageStatus.
	 */
	public String getTransferLogPageStatus() {
		if (null != getRequest().getParameter("status"))
			this.transferLogPageStatus = getRequest().getParameter("status")
					.toString();
		if ("all".equals(transferLogPageStatus))
			viewMoreFlag = true;
		else
			viewMoreFlag = false;
		return transferLogPageStatus;
	}

	/**
	 * @param transferLogPageStatus
	 *            The transferLogPageStatus to set.
	 */
	public void setTransferLogPageStatus(String transferLogPageStatus) {
		this.transferLogPageStatus = transferLogPageStatus;
	}

	/**
	 * @return Returns the isViewTwenty.
	 */
	public String getIsViewTwenty() {
		return isViewTwenty;
	}

	/**
	 * @param isViewTwenty
	 *            The isViewTwenty to set.
	 */
	public void setIsViewTwenty(String isViewTwenty) {
		this.isViewTwenty = isViewTwenty;
	}

	/**
	 * @return Returns the be.
	 */
	public String getBe() {
		return be;
	}

	/**
	 * @param be
	 *            The be to set.
	 */
	public void setBe(String be) {
		this.getSession().setAttribute("be", be);
		this.be = be;
	}

	/**
	 * @return Returns the bg.
	 */
	public String getBg() {
		return bg;
	}

	/**
	 * @param bg
	 *            The bg to set.
	 */
	public void setBg(String bg) {
		this.getSession().setAttribute("bg", bg);
		this.bg = bg;
	}

	/**
	 * @return Returns the lob.
	 */
	public String getLob() {
		return lob;
	}

	/**
	 * @param lob
	 *            The lob to set.
	 */
	public void setLob(String lob) {
		this.getSession().setAttribute("lob", lob);
		this.lob = lob;
	}

	/**
	 * @return Returns the baseContractDtDivNonMaditory.
	 */
	public String getBaseContractDtDivNonMaditory() {
		return baseContractDtDivNonMaditory;
	}

	/**
	 * @param baseContractDtDivNonMaditory
	 *            The baseContractDtDivNonMaditory to set.
	 */
	public void setBaseContractDtDivNonMaditory(
			String baseContractDtDivNonMaditory) {
		this.baseContractDtDivNonMaditory = baseContractDtDivNonMaditory;
	}

	/**
	 * @return Returns the baseContractIdDivNonMaditory.
	 */
	public String getBaseContractIdDivNonMaditory() {
		return baseContractIdDivNonMaditory;
	}

	/**
	 * @param baseContractIdDivNonMaditory
	 *            The baseContractIdDivNonMaditory to set.
	 */
	public void setBaseContractIdDivNonMaditory(
			String baseContractIdDivNonMaditory) {
		this.baseContractIdDivNonMaditory = baseContractIdDivNonMaditory;
	}

	/**
	 * @return Returns the baseContractDtStandardInvalid.
	 */
	public boolean isBaseContractDtStandardInvalid() {
		return baseContractDtStandardInvalid;
	}

	/**
	 * @param baseContractDtStandardInvalid
	 *            The baseContractDtStandardInvalid to set.
	 */
	public void setBaseContractDtStandardInvalid(
			boolean baseContractDtStandardInvalid) {
		this.baseContractDtStandardInvalid = baseContractDtStandardInvalid;
	}

	/**
	 * Method to get the list of benefit level duplicate reference and question
	 * duplicate reference
	 * 
	 * @return string
	 */
	public String getLoadContractDuplicateReference() {
		Logger.logInfo("entered method getBenefitDefinitionsList");

		List allDuplicateReference = new ArrayList();
		// Gets the contractid, contract Date segment, productid and version
		// from the session
		if (null != getContractSession()
				&& null != getContractSession().getContractKeyObject()) {
			this.setContractId(getContractSession().getContractKeyObject()
					.getContractId());
			int contractDateSegment = getContractSession()
					.getContractKeyObject().getDateSegmentId();
			this.setVersion(getContractKeyObject().getVersion());
		}
		RetrieveContractBenefitDefinitionRequest retrieveContractBenefitDefinitionRequest = (RetrieveContractBenefitDefinitionRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_CONTRACT_BENEFIT_DEFINITION_REQUEST);
		// Setting value to the request
		// This flag is set since this request is being used
		retrieveContractBenefitDefinitionRequest.setDuplicateRefPopup(true);
		retrieveContractBenefitDefinitionRequest.setContractId(this
				.getContractId());
		RetrieveContractBenefitDefinitionResponse retrieveContractBenefitDefinitionResponse = (RetrieveContractBenefitDefinitionResponse) executeService(retrieveContractBenefitDefinitionRequest);
		// Checking whether response is Null
		if (null != retrieveContractBenefitDefinitionResponse) {
			// Gets the list contains all the duplicate reference benefit level
			// and question from the response
			allDuplicateReference = retrieveContractBenefitDefinitionResponse
					.getContractDuplicateReferenceList();
			// Gets the benefit level duplicate reference and store it in a list
			this.duplicateBenefitLevelReferenceList = (List) allDuplicateReference
					.get(0);
			// Gets the question duplicate reference and store it in a list
			this.duplicateQuestionReferenceList = (List) allDuplicateReference
					.get(1);
			// Calls a method to send this list and format this list for
			// displaying in the page
			if ((!this.getDuplicateBenefitLevelReferenceList().isEmpty())
					&& (null != this.getDuplicateBenefitLevelReferenceList())) {
				this.duplicateBenefitLevelReferenceList = getDuplicateReferenceListForDisplay(
						this.duplicateBenefitLevelReferenceList,
						WebConstants.BENEFIT_LINE);
			}
			if ((!this.getDuplicateQuestionReferenceList().isEmpty())
					&& (null != this.getDuplicateQuestionReferenceList())) {
				this.duplicateQuestionReferenceList = getDuplicateReferenceListForDisplay(
						this.duplicateQuestionReferenceList,
						WebConstants.QUESTION);
			}
			this.product = retrieveContractBenefitDefinitionResponse
					.getProductName();
			// Null check for message list from response
			if (null != retrieveContractBenefitDefinitionResponse.getMessages()
					&& retrieveContractBenefitDefinitionResponse.getMessages()
							.size() > 0) {
				this.messageListForUniqueReference
						.addAll(retrieveContractBenefitDefinitionResponse
								.getMessages());
			}
			addAllMessagesToRequest(this.messageListForUniqueReference);
		}
		return WebConstants.EMPTY_STRING;
	}

	/**
	 * This method is to get the list according to which it has to be displayed
	 * in the jsp page to avoid some detail getting displayed in the jsp page
	 * 
	 * @param referenceList
	 * @param type
	 * @return referenceListForDisplay
	 */
	private List getDuplicateReferenceListForDisplay(List referenceList,
			String type) {
		int listSize;

		String oldDateRange;
		String oldBenefitComponentName;
		String oldBenefitName;
		String oldReferenceName;
		String newDateRange;
		String newBenefitComponentName;
		String newBenefitName;
		String newReferenceName;
		String oldAdminOptionName;
		String newAdminOptionName;
		String oldProductName;
		String newProductName;

		List referenceListForDisplay = null;

		// Checking whether the list is empty
		if (!(referenceList.isEmpty())) {
			listSize = referenceList.size();

			// Checking if the list size is greater than 500
			if (listSize > WebConstants.MAX_RES_FOR_DUP) {
				listSize = listSize - 1;
				InformationalMessage message = new InformationalMessage(
						WebConstants.UNIQUE_REFERENCE_LIST_TRUNCATED);
				String[] params = new String[] { type };
				message.setParameters(params);
				this.messageListForUniqueReference.add(message);
			}

			referenceListForDisplay = new ArrayList();
			ContractQuestUniqueReferenceBO dateSegments = (ContractQuestUniqueReferenceBO) referenceList
					.get(0);

			// oldXXX refers to previous row data.
			oldDateRange = dateSegments.getDateRange();
			oldBenefitComponentName = dateSegments.getBenefitComponentName();
			oldBenefitName = dateSegments.getBenefitSysName();
			oldReferenceName = dateSegments.getReferenceDesc();
			oldAdminOptionName = dateSegments.getAdminDesc();
			oldProductName = dateSegments.getProductName();

			referenceListForDisplay.add(dateSegments);
			for (int index = 1; index < listSize; index++) {
				ContractQuestUniqueReferenceBO newDateSegments = (ContractQuestUniqueReferenceBO) referenceList
						.get(index);

				ContractQuestUniqueReferenceBO displayDateSegment = new ContractQuestUniqueReferenceBO();

				newDateRange = newDateSegments.getDateRange();
				newBenefitComponentName = newDateSegments
						.getBenefitComponentName();
				newBenefitName = newDateSegments.getBenefitSysName();
				newReferenceName = newDateSegments.getReferenceDesc();
				newAdminOptionName = newDateSegments.getAdminDesc();
				newProductName = newDateSegments.getProductName();

				// Logic to display first entry
				if (oldDateRange.equals(newDateRange)) {
					newDateSegments.setDateRange(WebConstants.EMPTY_STRING);
					if (oldProductName.equals(newProductName)) {
						newDateSegments
								.setProductName(WebConstants.EMPTY_STRING);
						if (oldBenefitComponentName
								.equals(newBenefitComponentName)) {
							newDateSegments
									.setBenefitComponentName(WebConstants.EMPTY_STRING);
							if (oldBenefitName.equals(newBenefitName)) {
								newDateSegments
										.setBenefitSysName(WebConstants.EMPTY_STRING);
								if (oldReferenceName.equals(newReferenceName)) {
									newDateSegments
											.setReferenceDesc(WebConstants.EMPTY_STRING);
									if (type.equals(WebConstants.QUESTION)) {
										// Not to display repetation of Admin
										// option
										if (oldAdminOptionName
												.equals(newAdminOptionName)) {
											newDateSegments
													.setAdminDesc(WebConstants.EMPTY_STRING);
										}
									}
								}
							}
						}
					}
				}
				referenceListForDisplay.add(newDateSegments);
				oldDateRange = newDateRange;
				oldBenefitComponentName = newBenefitComponentName;
				oldBenefitName = newBenefitName;
				oldReferenceName = newReferenceName;
				oldAdminOptionName = newAdminOptionName;
			}
		}
		return referenceListForDisplay;

	}

	/**
	 * @param loadContractDuplicateReference
	 *            The loadContractDuplicateReference to set.
	 */
	public void setLoadContractDuplicateReference(
			String loadContractDuplicateReference) {
		this.loadContractDuplicateReference = loadContractDuplicateReference;
	}

	/**
	 * @return Returns the duplicateBenefitLevelReferenceList.
	 */
	public List getDuplicateBenefitLevelReferenceList() {
		return duplicateBenefitLevelReferenceList;
	}

	/**
	 * @param duplicateBenefitLevelReferenceList
	 *            The duplicateBenefitLevelReferenceList to set.
	 */
	public void setDuplicateBenefitLevelReferenceList(
			List duplicateBenefitLevelReferenceList) {
		this.duplicateBenefitLevelReferenceList = duplicateBenefitLevelReferenceList;
	}

	/**
	 * @return Returns the duplicateQuestionReferenceList.
	 */
	public List getDuplicateQuestionReferenceList() {
		return duplicateQuestionReferenceList;
	}

	/**
	 * @param duplicateQuestionReferenceList
	 *            The duplicateQuestionReferenceList to set.
	 */
	public void setDuplicateQuestionReferenceList(
			List duplicateQuestionReferenceList) {
		this.duplicateQuestionReferenceList = duplicateQuestionReferenceList;
	}

	/**
	 * @return Returns the contractName.
	 */
	public String getContractId() {
		return contractId;
	}

	/**
	 * @param contractName
	 *            The contractName to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	/**
	 * @return Returns the messageListForUniqueReference.
	 */
	public List getMessageListForUniqueReference() {
		return messageListForUniqueReference;
	}

	/**
	 * @param messageListForUniqueReference
	 *            The messageListForUniqueReference to set.
	 */
	public void setMessageListForUniqueReference(
			List messageListForUniqueReference) {
		this.messageListForUniqueReference = messageListForUniqueReference;
	}

	/**
	 * @return Returns the loadUncodedNotes.
	 */
	public String getLoadUncodedNotes() {
		oldCriteria = WebConstants.EMPTY_STRING;
		oldTierDesc = WebConstants.EMPTY_STRING;

		Logger.logInfo("entered method getLoadUncodedNotes");

		List allNotesList = new ArrayList();
		List allTierNotesList = new ArrayList();

		List lineNotesList = new ArrayList();
		List tierLineNotesList = new ArrayList();
		List tierDetailList = new ArrayList();

		List questNotesList = new ArrayList();
		List prdQuestNotesList = new ArrayList();

		List newLineNotesList = new ArrayList();
		List newQuestNotesList = new ArrayList();
		List newPrdQuestNotesList = new ArrayList();
		// Gets the contractid, contract Date segment, productid and version
		// from the session
		if (null != getContractSession()
				&& null != getContractSession().getContractKeyObject()) {
			this.setContractId(getContractSession().getContractKeyObject()
					.getContractId());
			int contractDateSegment = getContractSession()
					.getContractKeyObject().getDateSegmentId();
			this.setVersion(getContractKeyObject().getVersion());
		}
		ContractUncodedNotesRequest contractUncodedNotesRequest = (ContractUncodedNotesRequest) this
				.getServiceRequest(ServiceManager.CONTRACT_UNCODED_NOTES_REQUEST);
		// Setting value to the request
		// This flag is set since this request is being used
		contractUncodedNotesRequest
				.setAction(ContractUncodedNotesRequest.RETREIVE_UNCODED_NOTES);
		contractUncodedNotesRequest.setContractId(this.getContractId());
		ContractUncodedNotesResponse contractUncodedNotesResponse = (ContractUncodedNotesResponse) executeService(contractUncodedNotesRequest);
		// Checking whether response is Null
		if (null != contractUncodedNotesResponse) {

			allNotesList = contractUncodedNotesResponse
					.getUncodedAllNotesList();
			if (null != allNotesList && !allNotesList.isEmpty()) {
				for (int index = 0; index < allNotesList.size(); index++) {

					List dsNoteList = (List) allNotesList.get(index);
					lineNotesList = (List) dsNoteList.get(0);
					if (null != lineNotesList && !lineNotesList.isEmpty()) {
						newLineNotesList = getNotesListForDisplay(
								lineNotesList, WebConstants.BENEFIT_LINE);
						this.lineNotesList.addAll(newLineNotesList);
					}
					questNotesList = (List) dsNoteList.get(1);
					if (null != questNotesList && !questNotesList.isEmpty()) {
						newQuestNotesList = getNotesListForDisplay(
								questNotesList, WebConstants.QUESTION);
						this.questionNotesList.addAll(newQuestNotesList);
					}
					prdQuestNotesList = (List) dsNoteList.get(2);
					if (null != prdQuestNotesList
							&& !prdQuestNotesList.isEmpty()) {
						newPrdQuestNotesList = getNotesListForDisplay(
								prdQuestNotesList,
								WebConstants.PRODUCT_QUESTION);
						this.prdQuestNotesList.addAll(newPrdQuestNotesList);
					}
				}
			}

			allTierNotesList = contractUncodedNotesResponse
					.getUncodedTierNotesList();
			if (null != allTierNotesList && !allTierNotesList.isEmpty()) {
				setPanelUncodedNotes(allTierNotesList);
			}

			this.product = contractUncodedNotesResponse.getProductName();
			// Null check for message list from response
			if (null != contractUncodedNotesResponse.getMessages()
					&& contractUncodedNotesResponse.getMessages().size() > 0) {
				this.messageListForUniqueReference
						.addAll(contractUncodedNotesResponse.getMessages());
			}
			addAllMessagesToRequest(this.messageListForUniqueReference);
		}
		return WebConstants.EMPTY_STRING;
	}

	private void setPanelUncodedNotes(List allTierNotesList) {

		int tierNoteListSize = allTierNotesList.size();
		tierNotePanel = new HtmlPanelGrid();
		tierQuestNotePanel = new HtmlPanelGrid();
		for (int i = 0; i < tierNoteListSize; i++) {
			List tierNoteList = (List) allTierNotesList.get(i);

			List tierLineNoteList = (List) tierNoteList.get(0);
			tierLineNoteList = processTierNoteList(tierLineNoteList,
					"LineNotes");

			if (null != tierLineNoteList && !tierLineNoteList.isEmpty()) {

				List tierDetailListLineNotes = BenefitTierUtil
						.getTieredList((List) tierNoteList.get(1));
				tierDetailListLineNotes = sortTiers(tierDetailListLineNotes);

				for (int j = 0; j < tierDetailListLineNotes.size(); j++) {

					BenefitTierDefinition tierDef = (BenefitTierDefinition) tierDetailListLineNotes
							.get(j);
					String tierDesc = tierDef.getBenefitTierDefinitionName();

					List tierList = tierDef.getBenefitTiers();
					for (int k = 0; k < tierList.size(); k++) {
						BenefitTier benefitTier = (BenefitTier) tierList.get(k);
						int tierSysId = benefitTier.getBenefitTierSysId();

						for (int m = 0; m < tierLineNoteList.size(); m++) {
							TierDefinitionBO tierDefinitionBO = (TierDefinitionBO) tierLineNoteList
									.get(m);
							int sysId = tierDefinitionBO.getTierSysId();

							if (tierSysId == sysId) {
								setPanel(benefitTier, tierDefinitionBO,
										tierDesc);
							}
						}
					}
				}
			}

			List tierQuestNoteList = (List) tierNoteList.get(2);
			tierQuestNoteList = processTierNoteList(tierQuestNoteList,
					"QuestionNotes");

			if (null != tierQuestNoteList && !tierQuestNoteList.isEmpty()) {

				oldTierDesc = WebConstants.EMPTY_STRING;
				oldCriteria = WebConstants.EMPTY_STRING;

				List tierDetailListQuestNotes = BenefitTierUtil
						.getTieredList((List) tierNoteList.get(3));
				tierDetailListQuestNotes = sortTiers(tierDetailListQuestNotes);

				for (int j = 0; j < tierDetailListQuestNotes.size(); j++) {

					BenefitTierDefinition tierDef = (BenefitTierDefinition) tierDetailListQuestNotes
							.get(j);
					String tierDesc = tierDef.getBenefitTierDefinitionName();

					List tierList = tierDef.getBenefitTiers();
					for (int k = 0; k < tierList.size(); k++) {
						BenefitTier benefitTier = (BenefitTier) tierList.get(k);
						int tierSysId = benefitTier.getBenefitTierSysId();

						for (int m = 0; m < tierQuestNoteList.size(); m++) {
							TierDefinitionBO tierDefinitionBO = (TierDefinitionBO) tierQuestNoteList
									.get(m);
							int sysId = tierDefinitionBO.getTierSysId();

							if (tierSysId == sysId) {

								setPanelForQuestionNotes(benefitTier,
										tierDefinitionBO, tierDesc);
							}
						}
					}
				}
			}
		}
	}

	private void setPanel(BenefitTier benefitTier,
			TierDefinitionBO tierDefinitionBO, String newtierDesc) {

		int tierSysId = benefitTier.getBenefitTierSysId();
		boolean isTierDefPanel = true;
		boolean isTierDescPanel = true;
		tierNotePanel.setCellpadding("1");
		tierNotePanel.setCellspacing("0");
		tierNotePanel.setColumns(1);
		tierNotePanel.setWidth("100%");
		tierNotePanel.setBgcolor("#cccccc");

		HtmlPanelGrid tierDefPanel = new HtmlPanelGrid();
		HtmlPanelGrid tierDescPanel = new HtmlPanelGrid();
		HtmlPanelGrid tierLevelPanel = new HtmlPanelGrid();
		HtmlPanelGrid headerPanel = new HtmlPanelGrid();

		tierDefPanel.setColumns(1);
		tierDefPanel.setWidth("100%");
		tierDefPanel.setBorder(0);
		tierDefPanel.setCellpadding("1");
		tierDefPanel.setCellspacing("0");
		tierDefPanel.setBgcolor("#cccccc");

		if ("".equals(oldTierDesc)) {
			HtmlOutputText tierDefTxt = new HtmlOutputText();
			tierDefTxt.setValue(newtierDesc);
			tierDefPanel.getChildren().add(tierDefTxt);
		} else if (!oldTierDesc.equals(newtierDesc)) {
			HtmlOutputText tierDefTxt = new HtmlOutputText();
			tierDefTxt.setValue(newtierDesc);
			tierDefPanel.getChildren().add(tierDefTxt);
		} else {
			isTierDefPanel = false;
		}

		tierDescPanel.setColumns(1);
		tierDescPanel.setWidth("100%");
		tierDescPanel.setBorder(0);
		tierDescPanel.setCellpadding("1");
		tierDescPanel.setCellspacing("0");
		tierDescPanel.setBgcolor("#cccccc");
		tierDescPanel.setRowClasses("dataTableOddRow");
		List tierCriterialList = benefitTier.getBenefitTierCriteriaList();
		String criteria = "";
		for (int i = 0; i < tierCriterialList.size(); i++) {
			BenefitTierCriteria benefitTierCriteria = (BenefitTierCriteria) tierCriterialList
					.get(i);
			criteria += benefitTierCriteria.getBenefitTierCriteriaName();
			criteria += ":";
			criteria += benefitTierCriteria.getBenefitTierCriteriaValue();
			criteria += " ";
		}

		String tierDef = newtierDesc + " " + criteria;
		if ("".equals(oldCriteria)) {
			HtmlOutputText tierDescTxt = new HtmlOutputText();
			tierDescTxt.setValue(criteria);
			tierDescPanel.getChildren().add(tierDescTxt);
		} else if (!oldCriteria.equals(tierDef)) {
			HtmlOutputText tierDescTxt = new HtmlOutputText();
			tierDescTxt.setValue(criteria);
			tierDescPanel.getChildren().add(tierDescTxt);
		} else {
			isTierDescPanel = false;
		}

		if (!isPrintMode()) {
			tierLevelPanel.setColumns(8);
		} else {
			tierLevelPanel.setColumns(7);
		}

		if (!isPrintMode()) {
			tierLevelPanel.setRowClasses("dataTableEvenRow");
			tierLevelPanel.setBgcolor("#cccccc");
			tierLevelPanel
					.setColumnClasses("gridColumn13,gridColumn16,gridColumn17,gridColumn19,gridColumn22,gridColumn5,gridColumn5,gridColumn3");
		} else {
			tierLevelPanel.setRowClasses("dataTableOddRow");
			tierLevelPanel
					.setColumnClasses("gridColumn17,gridColumn16,gridColumn17,gridColumn20,gridColumn19,gridColumn5,gridColumn6");
		}
		tierLevelPanel.setWidth("100%");
		tierLevelPanel.setCellpadding("0");
		tierLevelPanel.setCellspacing("1");

		HtmlOutputText dsTag = new HtmlOutputText();
		dsTag.setValue(tierDefinitionBO.getDateRange());
		// dsTag.setStyle("width:23%");

		HtmlOutputText productTag = new HtmlOutputText();
		productTag.setValue(tierDefinitionBO.getProductName());
		// productTag.setStyle("width:10%");

		HtmlOutputText bcTag = new HtmlOutputText();
		bcTag.setValue(tierDefinitionBO.getBnftCmpntName());
		// bcTag.setStyle("width:15%");

		HtmlOutputText benefitTag = new HtmlOutputText();
		benefitTag.setValue(tierDefinitionBO.getBnftName());
		// benefitTag.setStyle("width:15%");

		HtmlOutputText lvlDescTag = new HtmlOutputText();
		lvlDescTag.setValue(tierDefinitionBO.getLevelDesc());
		// dsTag.setStyle("width:15%");

		HtmlOutputText lineDescTag = new HtmlOutputText();
		lineDescTag.setValue(tierDefinitionBO.getPvaDesc());
		// lineDescTag.setStyle("width:15%");

		HtmlOutputText noteIdTag = new HtmlOutputText();
		noteIdTag.setValue(tierDefinitionBO.getNoteId());
		// noteIdTag.setStyle("width:4%");

		HtmlCommandButton noteViewButton = new HtmlCommandButton();
		if (!isPrintMode()) {
			noteViewButton.setImage("../../images/view.gif");
			noteViewButton.setOnclick("viewActionTierLine(" + "'"
					+ tierDefinitionBO.getNoteId() + "'" + ","
					+ tierDefinitionBO.getNoteVersion() + "," + "'"
					+ tierDefinitionBO.getNoteName() + "'" + ");return false");
			noteViewButton.setValue("NotesButton");
			noteViewButton.setTitle("Note");
			noteViewButton.setAlt("Notes View");
		}

		tierLevelPanel.getChildren().add(dsTag);
		tierLevelPanel.getChildren().add(productTag);
		tierLevelPanel.getChildren().add(bcTag);
		tierLevelPanel.getChildren().add(benefitTag);
		tierLevelPanel.getChildren().add(lvlDescTag);
		tierLevelPanel.getChildren().add(lineDescTag);
		tierLevelPanel.getChildren().add(noteIdTag);
		if (!isPrintMode()) {
			tierLevelPanel.getChildren().add(noteViewButton);
		}
		if (isTierDefPanel)
			tierNotePanel.getChildren().add(tierDefPanel);
		if (isTierDescPanel)
			tierNotePanel.getChildren().add(tierDescPanel);

		tierNotePanel.getChildren().add(tierLevelPanel);

		oldCriteria = tierDef;
		oldTierDesc = newtierDesc;
	}

	private void setPanelForQuestionNotes(BenefitTier benefitTier,
			TierDefinitionBO tierDefinitionBO, String newtierDesc) {

		int tierSysId = benefitTier.getBenefitTierSysId();
		boolean isTierDefPanel = true;
		boolean isTierDescPanel = true;

		tierQuestNotePanel.setColumns(1);
		tierQuestNotePanel.setCellpadding("1");
		tierQuestNotePanel.setCellspacing("0");
		tierQuestNotePanel.setWidth("100%");
		tierQuestNotePanel.setBgcolor("#cccccc");

		HtmlPanelGrid tierDefPanel = new HtmlPanelGrid();
		HtmlPanelGrid tierDescPanel = new HtmlPanelGrid();
		HtmlPanelGrid tierQuestPanel = new HtmlPanelGrid();
		HtmlPanelGrid headerPanel = new HtmlPanelGrid();

		tierDefPanel.setColumns(1);
		tierDefPanel.setWidth("100%");
		tierDefPanel.setBorder(0);
		tierDefPanel.setCellpadding("1");
		tierDefPanel.setCellspacing("0");
		tierDefPanel.setBgcolor("#cccccc");

		if ("".equals(oldTierDesc)) {
			HtmlOutputText tierDefTxt = new HtmlOutputText();
			tierDefTxt.setValue(newtierDesc);
			tierDefPanel.getChildren().add(tierDefTxt);
		} else if (!oldTierDesc.equals(newtierDesc)) {
			HtmlOutputText tierDefTxt = new HtmlOutputText();
			tierDefTxt.setValue(newtierDesc);
			tierDefPanel.getChildren().add(tierDefTxt);
		} else {
			isTierDefPanel = false;
		}

		tierDescPanel.setColumns(1);
		tierDescPanel.setWidth("100%");
		tierDescPanel.setBorder(0);
		tierDescPanel.setCellpadding("1");
		tierDescPanel.setCellspacing("0");
		tierDescPanel.setBgcolor("#cccccc");
		tierDescPanel.setRowClasses("dataTableOddRow");
		List tierCriterialList = benefitTier.getBenefitTierCriteriaList();
		String criteria = "";
		for (int i = 0; i < tierCriterialList.size(); i++) {
			BenefitTierCriteria benefitTierCriteria = (BenefitTierCriteria) tierCriterialList
					.get(i);
			criteria += benefitTierCriteria.getBenefitTierCriteriaName();
			criteria += ":";
			criteria += benefitTierCriteria.getBenefitTierCriteriaValue();
			criteria += " ";
		}

		String tierDef = newtierDesc + " " + criteria;
		if ("".equals(oldCriteria)) {
			HtmlOutputText tierDescTxt = new HtmlOutputText();
			tierDescTxt.setValue(criteria);
			tierDescPanel.getChildren().add(tierDescTxt);
		} else if (!oldCriteria.equals(tierDef)) {
			HtmlOutputText tierDescTxt = new HtmlOutputText();
			tierDescTxt.setValue(criteria);
			tierDescPanel.getChildren().add(tierDescTxt);
		} else {
			isTierDescPanel = false;
		}

		if (!isPrintMode()) {
			tierQuestPanel.setColumns(8);
		} else {
			tierQuestPanel.setColumns(7);
		}

		if (!isPrintMode()) {
			tierQuestPanel.setRowClasses("dataTableEvenRow");
			tierQuestPanel.setBgcolor("#cccccc");
			tierQuestPanel
					.setColumnClasses("gridColumn13,gridColumn16,gridColumn17,gridColumn15,gridColumn17,gridColumn15,gridColumn5,gridColumn3");
		} else {
			tierQuestPanel.setRowClasses("dataTableOddRow");
			tierQuestPanel
					.setColumnClasses("gridColumn18,gridColumn15,gridColumn15,gridColumn15,gridColumn15,gridColumn15,gridColumn7");
		}
		tierQuestPanel.setWidth("100%");
		tierQuestPanel.setCellpadding("0");
		tierQuestPanel.setCellspacing("1");

		HtmlOutputText dsTag = new HtmlOutputText();
		dsTag.setValue(tierDefinitionBO.getDateRange());
		// dsTag.setStyle("width:23%");

		HtmlOutputText productTag = new HtmlOutputText();
		productTag.setValue(tierDefinitionBO.getProductName());
		// productTag.setStyle("width:10%");

		HtmlOutputText bcTag = new HtmlOutputText();
		bcTag.setValue(tierDefinitionBO.getBnftCmpntName());
		// bcTag.setStyle("width:15%");

		HtmlOutputText benefitTag = new HtmlOutputText();
		benefitTag.setValue(tierDefinitionBO.getBnftName());
		// benefitTag.setStyle("width:15%");

		HtmlOutputText admnOptionTag = new HtmlOutputText();
		admnOptionTag.setValue(tierDefinitionBO.getAdmnOptDesc());
		// dsTag.setStyle("width:15%");

		HtmlOutputText questDescTag = new HtmlOutputText();
		questDescTag.setValue(tierDefinitionBO.getQuestionDesc());
		// lineDescTag.setStyle("width:15%");

		HtmlOutputText noteIdTag = new HtmlOutputText();
		noteIdTag.setValue(tierDefinitionBO.getNoteId());
		// noteIdTag.setStyle("width:4%");

		HtmlCommandButton noteViewButton = new HtmlCommandButton();
		if (!isPrintMode()) {
			noteViewButton.setImage("../../images/view.gif");
			noteViewButton.setOnclick("viewActionTierQuesiion(" + "'"
					+ tierDefinitionBO.getNoteId() + "'" + ","
					+ tierDefinitionBO.getNoteVersion() + "," + "'"
					+ tierDefinitionBO.getNoteName() + "'" + ");return false");
			noteViewButton.setValue("NotesButton");
			noteViewButton.setTitle("Note");
			noteViewButton.setAlt("Notes View");
		}
		tierQuestPanel.getChildren().add(dsTag);
		tierQuestPanel.getChildren().add(productTag);
		tierQuestPanel.getChildren().add(bcTag);
		tierQuestPanel.getChildren().add(benefitTag);
		tierQuestPanel.getChildren().add(admnOptionTag);
		tierQuestPanel.getChildren().add(questDescTag);
		tierQuestPanel.getChildren().add(noteIdTag);
		if (!isPrintMode()) {
			tierQuestPanel.getChildren().add(noteViewButton);
		}

		if (isTierDefPanel)
			tierQuestNotePanel.getChildren().add(tierDefPanel);
		if (isTierDescPanel)
			tierQuestNotePanel.getChildren().add(tierDescPanel);

		tierQuestNotePanel.getChildren().add(tierQuestPanel);

		oldCriteria = tierDef;
		oldTierDesc = newtierDesc;
	}

	private List processTierNoteList(List tierNoteList, String type) {

		List tierLineNoteList = null;
		if (null != tierNoteList && !tierNoteList.isEmpty()) {
			tierLineNoteList = new ArrayList();

			String oldDateRange;
			String oldBenefitComponentName;
			String oldBenefitName;
			String oldLineDesc;
			String oldNoteId;
			int oldNoteVersion;
			String oldAdmnoption;
			String oldQuestion;
			String oldProdName;
			int oldTierSysId;

			String newDateRange;
			String newBenefitComponentName;
			String newBenefitName;
			String newLineDesc;
			String newNoteId;
			int newNoteVersion;
			String newAdmnoption;
			String newQuestion;
			String newProdName;
			int newTierSysId;

			TierDefinitionBO dateSegments = (TierDefinitionBO) tierNoteList
					.get(0);

			// oldXXX refers to previous row data.
			oldDateRange = dateSegments.getDateRange();
			oldBenefitComponentName = dateSegments.getBnftCmpntName();
			oldBenefitName = dateSegments.getBnftName();
			oldLineDesc = dateSegments.getLevelDesc();
			oldNoteId = dateSegments.getNoteId();
			oldNoteVersion = dateSegments.getNoteVersion();
			oldAdmnoption = dateSegments.getAdmnOptDesc();
			oldQuestion = dateSegments.getQuestionDesc();
			oldProdName = dateSegments.getProductName();
			oldTierSysId = dateSegments.getTierSysId();
			tierLineNoteList.add(dateSegments);

			for (int index = 1; index < tierNoteList.size(); index++) {

				TierDefinitionBO newDateSegments = (TierDefinitionBO) tierNoteList
						.get(index);

				newDateRange = newDateSegments.getDateRange();
				newBenefitComponentName = newDateSegments.getBnftCmpntName();
				newBenefitName = newDateSegments.getBnftName();
				newLineDesc = newDateSegments.getLevelDesc();
				newNoteId = newDateSegments.getNoteId();
				newNoteVersion = newDateSegments.getNoteVersion();
				newAdmnoption = newDateSegments.getAdmnOptDesc();
				newQuestion = newDateSegments.getQuestionDesc();
				newProdName = newDateSegments.getProductName();
				newTierSysId = newDateSegments.getTierSysId();
				if (oldTierSysId == newTierSysId) {
					if (oldDateRange.equals(newDateRange)) {
						newDateSegments.setDateRange(WebConstants.EMPTY_STRING);

						if (type.equals("LineNotes")) {
							if (oldProdName.equals(newProdName)) {
								newDateSegments
										.setProductName(WebConstants.EMPTY_STRING);
								if (oldBenefitComponentName
										.equals(newBenefitComponentName)) {
									newDateSegments
											.setBnftCmpntName(WebConstants.EMPTY_STRING);
									if (oldBenefitName.equals(newBenefitName)) {
										newDateSegments
												.setBnftCmpntName(WebConstants.EMPTY_STRING);
									}
								}
							}
						}

						if (type.equals("QuestionNotes")) {
							if (oldProdName.equals(newProdName)) {
								newDateSegments
										.setProductName(WebConstants.EMPTY_STRING);
								if (oldBenefitComponentName
										.equals(newBenefitComponentName)) {
									newDateSegments
											.setBnftCmpntName(WebConstants.EMPTY_STRING);
									if (oldBenefitName.equals(newBenefitName)) {
										newDateSegments
												.setBnftName(WebConstants.EMPTY_STRING);
										if (oldAdmnoption.equals(newAdmnoption)) {
											newDateSegments
													.setAdmnOptDesc(WebConstants.EMPTY_STRING);
											if (oldQuestion.equals(newQuestion)) {
												newDateSegments
														.setQuestionDesc(WebConstants.EMPTY_STRING);
											}
										}
									}
								}
							}
						}

					}

				}

				tierLineNoteList.add(newDateSegments);

				oldTierSysId = newTierSysId;
				oldDateRange = newDateRange;
				oldBenefitComponentName = newBenefitComponentName;
				oldBenefitName = newBenefitName;
				oldLineDesc = newLineDesc;
				oldNoteId = newNoteId;
				oldNoteVersion = newNoteVersion;
				oldAdmnoption = newAdmnoption;
				oldQuestion = newQuestion;
			}
			return tierLineNoteList;
		}
		return null;
	}

	public String loadUncodedNotes() {
		this.getLoadUncodedNotes();
		return "uncodedNotes";
	}

	public String loadUncodedNotesPrint() {
		// this.getLoadUncodedNotes();
		return "uncodedNotesPrint";
	}

	private List sortTiers(List benefitDefinitonsList) {
		if (null != benefitDefinitonsList) {
			// Collections.sort(benefitDefinitonsList);
			for (Iterator iter = benefitDefinitonsList.iterator(); iter
					.hasNext();) {
				BenefitTierDefinition tierDef = (BenefitTierDefinition) iter
						.next();
				if (null != tierDef) {
					Collections.sort(tierDef.getBenefitTiers());
				}

			}
			return benefitDefinitonsList;
		} else
			return null;

	}

	/**
	 * This method is to get the list according to which it has to be displayed
	 * in the jsp page to avoid some detail getting displayed in the jsp page
	 * 
	 * @param referenceList
	 * @param type
	 * @return referenceListForDisplay
	 */
	private List getNotesListForDisplay(List referenceList, String type) {

		int listSize;

		String oldDateRange;
		String oldBenefitComponentName;
		String oldBenefitName;
		String oldLineDesc;
		String oldNoteId;
		int oldNoteVersion;
		String oldAdmnoption;
		String oldQuestion;
		String oldProdName;

		String newDateRange;
		String newBenefitComponentName;
		String newBenefitName;
		String newLineDesc;
		String newNoteId;
		int newNoteVersion;
		String newAdmnoption;
		String newQuestion;
		String newProdName;

		String oldAdminOptionName;
		String newAdminOptionName;

		List notesListForDisplay = null;

		// Checking whether the list is empty
		if (!(referenceList.isEmpty())) {
			listSize = referenceList.size();

			// Checking if the list size is greater than 500
			if (listSize > WebConstants.MAX_RES_FOR_DUP) {
				listSize = listSize - 1;
				InformationalMessage message = new InformationalMessage(
						WebConstants.UNIQUE_REFERENCE_LIST_TRUNCATED);
				String[] params = new String[] { type };
				message.setParameters(params);
				this.messageListForUniqueReference.add(message);
			}

			notesListForDisplay = new ArrayList();
			ContractQuestUniqueReferenceBO dateSegments = (ContractQuestUniqueReferenceBO) referenceList
					.get(0);

			// oldXXX refers to previous row data.
			oldDateRange = dateSegments.getDateRange();
			oldBenefitComponentName = dateSegments.getBenefitComponentName();
			oldBenefitName = dateSegments.getBenefitSysName();
			oldLineDesc = dateSegments.getBenefitLevelDesc();
			oldNoteId = dateSegments.getNoteID();
			oldNoteVersion = dateSegments.getNoteVersion();
			oldAdmnoption = dateSegments.getAdminDesc();
			oldQuestion = dateSegments.getQuestionDesc();
			oldProdName = dateSegments.getProductName();

			notesListForDisplay.add(dateSegments);
			for (int index = 1; index < listSize; index++) {
				ContractQuestUniqueReferenceBO newDateSegments = (ContractQuestUniqueReferenceBO) referenceList
						.get(index);

				newDateRange = newDateSegments.getDateRange();
				newBenefitComponentName = newDateSegments
						.getBenefitComponentName();
				newBenefitName = newDateSegments.getBenefitSysName();
				newLineDesc = newDateSegments.getBenefitLevelDesc();
				newNoteId = newDateSegments.getNoteID();
				newNoteVersion = newDateSegments.getNoteVersion();
				newAdmnoption = newDateSegments.getAdminDesc();
				newQuestion = newDateSegments.getQuestionDesc();
				newProdName = newDateSegments.getProductName();

				// Logic to display first entry
				if (oldDateRange.equals(newDateRange)) {
					newDateSegments.setDateRange(WebConstants.EMPTY_STRING);

					if (type.equals(WebConstants.BENEFIT_LINE)) {
						if (oldProdName.equals(newProdName)) {
							newDateSegments
									.setProductName(WebConstants.EMPTY_STRING);
							if (oldBenefitComponentName
									.equals(newBenefitComponentName)) {
								newDateSegments
										.setBenefitComponentName(WebConstants.EMPTY_STRING);
								if (oldBenefitName.equals(newBenefitName)) {
									newDateSegments
											.setBenefitSysName(WebConstants.EMPTY_STRING);
								}
							}
						}
					}
					if (type.equals(WebConstants.QUESTION)) {
						if (oldProdName.equals(newProdName)) {
							newDateSegments
									.setProductName(WebConstants.EMPTY_STRING);
							if (oldBenefitComponentName
									.equals(newBenefitComponentName)) {
								newDateSegments
										.setBenefitComponentName(WebConstants.EMPTY_STRING);
								if (oldBenefitName.equals(newBenefitName)) {
									newDateSegments
											.setBenefitSysName(WebConstants.EMPTY_STRING);
									if (oldAdmnoption.equals(newAdmnoption)) {
										newDateSegments
												.setAdminDesc(WebConstants.EMPTY_STRING);
										if (oldQuestion.equals(newQuestion)) {
											newDateSegments
													.setQuestionDesc(WebConstants.EMPTY_STRING);
										}
									}
								}
							}
						}
					}
					if (type.equals(WebConstants.PRODUCT_QUESTION)) {
						if (oldProdName.equals(newProdName)) {
							newDateSegments
									.setProductName(WebConstants.EMPTY_STRING);
							if (oldAdmnoption.equals(newAdmnoption)) {
								newDateSegments
										.setAdminDesc(WebConstants.EMPTY_STRING);
								if (oldQuestion.equals(newQuestion)) {
									newDateSegments
											.setQuestionDesc(WebConstants.EMPTY_STRING);
								}
							}
						}
					}
				}
				notesListForDisplay.add(newDateSegments);

				oldDateRange = newDateRange;
				oldBenefitComponentName = newBenefitComponentName;
				oldBenefitName = newBenefitName;
				oldLineDesc = newLineDesc;
				oldNoteId = newNoteId;
				oldNoteVersion = newNoteVersion;
				oldAdmnoption = newAdmnoption;
				oldQuestion = newQuestion;
			}
		}
		return notesListForDisplay;

	}

	/**
	 * @param loadUncodedNotes
	 *            The loadUncodedNotes to set.
	 */
	public void setLoadUncodedNotes(String loadUncodedNotes) {
		this.loadUncodedNotes = loadUncodedNotes;
	}

	/**
	 * @return Returns the lineNotesList.
	 */
	public List getLineNotesList() {
		return lineNotesList;
	}

	/**
	 * @param lineNotesList
	 *            The lineNotesList to set.
	 */
	public void setLineNotesList(List lineNotesList) {
		this.lineNotesList = lineNotesList;
	}

	/**
	 * @return Returns the prdQuestNotesList.
	 */
	public List getPrdQuestNotesList() {
		return prdQuestNotesList;
	}

	/**
	 * @param prdQuestNotesList
	 *            The prdQuestNotesList to set.
	 */
	public void setPrdQuestNotesList(List prdQuestNotesList) {
		this.prdQuestNotesList = prdQuestNotesList;
	}

	/**
	 * @return Returns the questionNotesList.
	 */
	public List getQuestionNotesList() {
		return questionNotesList;
	}

	/**
	 * @param questionNotesList
	 *            The questionNotesList to set.
	 */
	public void setQuestionNotesList(List questionNotesList) {
		this.questionNotesList = questionNotesList;
	}

	/**
	 * @return Returns the tierNotePanel.
	 */
	public HtmlPanelGrid getTierNotePanel() {
		return tierNotePanel;
	}

	/**
	 * @param tierNotePanel
	 *            The tierNotePanel to set.
	 */
	public void setTierNotePanel(HtmlPanelGrid tierNotePanel) {
		this.tierNotePanel = tierNotePanel;
	}

	/**
	 * @return Returns the headerPanelForTierLines.
	 */
	public HtmlPanelGrid getHeaderPanelForTierLines() {
		return headerPanelForTierLines;
	}

	/**
	 * @param headerPanelForTierLines
	 *            The headerPanelForTierLines to set.
	 */
	public void setHeaderPanelForTierLines(HtmlPanelGrid headerPanelForTierLines) {
		this.headerPanelForTierLines = headerPanelForTierLines;
	}

	/**
	 * @return Returns the headerPanelForTierQuestLines.
	 */
	public HtmlPanelGrid getHeaderPanelForTierQuestLines() {
		return headerPanelForTierQuestLines;
	}

	/**
	 * @param headerPanelForTierQuestLines
	 *            The headerPanelForTierQuestLines to set.
	 */
	public void setHeaderPanelForTierQuestLines(
			HtmlPanelGrid headerPanelForTierQuestLines) {
		this.headerPanelForTierQuestLines = headerPanelForTierQuestLines;
	}

	/**
	 * @return Returns the tierQuestNotePanel.
	 */
	public HtmlPanelGrid getTierQuestNotePanel() {
		return tierQuestNotePanel;
	}

	/**
	 * @param tierQuestNotePanel
	 *            The tierQuestNotePanel to set.
	 */
	public void setTierQuestNotePanel(HtmlPanelGrid tierQuestNotePanel) {
		this.tierQuestNotePanel = tierQuestNotePanel;
	}

	/**
	 * @return Returns the loadUncodedNotesForPrint.
	 */
	// WAS 7.0 Changes - Binding variable rootQuestionLoad modified to
	// HtmlInputHidden instead of String, Since getter method call failed,
	// while the variable defined in String in WAS 7.0
	/**
	 * 
	 * @return HtmlInputHidden
	 */
	public HtmlInputHidden getLoadUncodedNotesForPrint() {
		this.setPrintMode(true);
		this.getLoadUncodedNotes();
		// return WebConstants.LOAD_UNCODED_NOTES_PRINT;
		loadUncodedNotesForPrint
				.setValue(WebConstants.LOAD_UNCODED_NOTES_PRINT);
		return loadUncodedNotesForPrint;
	}

	/**
	 * @param loadUncodedNotesForPrint
	 *            The loadUncodedNotesForPrint to set.
	 */
	public void setLoadUncodedNotesForPrint(
			HtmlInputHidden loadUncodedNotesForPrint) {
		this.loadUncodedNotesForPrint = loadUncodedNotesForPrint;
	}

	/**
	 * @return Returns the isPrintMode.
	 */
	public boolean isPrintMode() {
		return isPrintMode;
	}

	/**
	 * @param isPrintMode
	 *            The isPrintMode to set.
	 */
	public void setPrintMode(boolean isPrintMode) {
		this.isPrintMode = isPrintMode;
	}

	/* START CARS */

	/**
	 * @return Returns the requiredMarketBusinessUnit.
	 */
	public boolean isRequiredMarketBusinessUnit() {
		return requiredMarketBusinessUnit;
	}

	/**
	 * @param requiredMarketBusinessUnit
	 *            The requiredMarketBusinessUnit to set.
	 */
	public void setRequiredMarketBusinessUnit(boolean requiredMarketBusinessUnit) {
		this.requiredMarketBusinessUnit = requiredMarketBusinessUnit;
	}

	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public String getMarketBusinessUnit() {
		return marketBusinessUnit;
	}

	/**
	 * @param marketBusinessUnit
	 *            The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(String marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}

	/**
	 * @return Returns the mbu.
	 */
	public String getMbu() {
		return mbu;
	}

	/**
	 * @param mbu
	 *            The mbu to set.
	 */
	public void setMbu(String mbu) {
		this.getSession().setAttribute("mbu", bg);
		this.mbu = mbu;
	}

	/* END CARS */

	public String getContractStatusDate() {
		return contractStatusDate;
	}

	public void setContractStatusDate(String contractStatuDate) {
		this.contractStatusDate = contractStatuDate;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public int getContractStatusInedx() {
		return contractStatusInedx;
	}

	public void setContractStatusInedx(int contractStatusInedx) {
		this.contractStatusInedx = contractStatusInedx;
	}

	public String getContractStatusReasonCode() {
		if (null != contractStatusReasonCode) {
			return contractStatusReasonCode.trim();
		}
		return contractStatusReasonCode;
	}

	public void setContractStatusReasonCode(String contractStatusReasonCode) {
		this.contractStatusReasonCode = contractStatusReasonCode;
	}

	public boolean isContractStatusDateInvalid() {
		return contractStatusDateInvalid;
	}

	public void setContractStatusDateInvalid(boolean contractStatusDateInvalid) {
		this.contractStatusDateInvalid = contractStatusDateInvalid;
	}

	public boolean isStatusResonCodeInvalid() {
		return statusResonCodeInvalid;
	}

	public void setStatusResonCodeInvalid(boolean statusResonCodeInvalid) {
		this.statusResonCodeInvalid = statusResonCodeInvalid;
	}

	public boolean isContractStatusInvalid() {
		return contractStatusInvalid;
	}

	public void setContractStatusInvalid(boolean contractStatusInvalid) {
		this.contractStatusInvalid = contractStatusInvalid;
	}

}