/*
 * ContractSpecificInfoBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wellpoint.ets.ebx.simulation.webservices.client.ContractWebServiceVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerPopupDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerRuleIdDisplayVO;
import com.wellpoint.ets.ebx.simulation.webservices.client.EbxWebSerSPSIdDisplayVO;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.assocobj.bo.AffiliationObject;
import com.wellpoint.wpd.common.contract.bo.AdaptedInfoBO;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.contract.bo.ProviderSpecialityCodeBO;
import com.wellpoint.wpd.common.contract.request.ContractWebServiceRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractProductRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveContractSpecificInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractAdaptedInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBasicInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractSpecificInfoRequest;
import com.wellpoint.wpd.common.contract.request.SaveDateSegmentCommentRequest;
import com.wellpoint.wpd.common.contract.response.ContractWebServiceResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractProductResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveContractSpecificInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractAdaptedInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBasicInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractSpecificInfoResponse;
import com.wellpoint.wpd.common.contract.response.SaveDateSegmentCommentResponse;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.product.response.SaveProductResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractSpecificInfoBackingBean extends ContractBackingBean {

	private String headQuartersState;

	private String productCode;

	private String productCodeDesc;

	private String standardPlanCode;

	private String benefitPlan;

	private String groupName;

	private boolean checkin;

	private String state;

	private String status;

	private int version;

	private String product;

	private String productFamily;

	private String corporatePlanCode;

	private String fundingArrangement;

	private String brandName;

	private String printValue;

	private String printValueAdapted;

	private List validationMessages;

	private List headQuartersStateList;

	boolean requiredProductCode = false;

	boolean requiredBenefitPlan = false;

	boolean requiredProduct = false;

	private String lastUpdatedUser;

	private Date lastUpdatedTimeStamp;

	private String groupSize;

	private List lobCodeList;

	private List businessEntityCodeList;

	private List businessGroupCodeList;

	private String lineOfBusiness;

	private String businessEntity;
	/*START CARS*/
	private String marketBusinessUnit;
	
	private String mbu;
	/*END CARS*/
	private String businessGroup;

	private String effectiveDate;

	private String expiryDate;

	private boolean custom = true;

	private boolean mandate = true;

	private String hiddenProduct;

	boolean requiredCorporateCode = false;

	private String contractIdForRefData;

	private String dateSegmentType;

	// For Indicators and New Tab
	private String cobAdjudicationIndicator = "N";

	private String medicareAdjudicationIndicator = "N";

	private String itsAdjudicationIndicator = "N";

	boolean requiredCobIndicator = false;

	boolean requiredMedIndicator = false;

	boolean requiredItsIndicator = false;

	private String regulatoryAgency;

	private String complianceStatus;

	private String prodProjNameCode;

	private String contractTermDate;

	private String multiplanIndicator = "N";

	private String performanceGuarantee = "N";

	private String salesMarketDate;

	private String complianceStatusDesc;

	private boolean hasValidationErrors;

	private String lob;

	private String be;

	private String bg;

	private String provSpecCode;

	boolean requiredProductCodeDesc = false;
	
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

	
	/**	@since Start feb release 2010 -modification for specific info print 
	 ** <p>ProviderSpecialtyCodeAndDesc field will hold 
	 *provider speciality in the format <code>code-description</code></p>
	**/
	private String providerSpecialtyCodeAndDesc;

	public ContractSpecificInfoBackingBean() {
		//sets the bread crumb text
		this.setBreadCrumbText();

	}

	//This function saves the specific information
	public String save() {

		//checks for validity of the fields
		if (isValid()) {
			SaveContractSpecificInfoRequest saveContractSpecificInfoRequest = (SaveContractSpecificInfoRequest) this
					.getServiceRequest(ServiceManager.SAVE_CONTRACTSPINFOREQUEST);
			//sets values to the request
			saveContractSpecificInfoRequest = setValuesToRequest(saveContractSpecificInfoRequest);

			if (getContractSession().getContractKeyObject().getProductId() != 0)
				saveContractSpecificInfoRequest.setSave(false);
			else
				saveContractSpecificInfoRequest.setSave(true);

			SaveContractSpecificInfoResponse saveContractSpecificInfoResponse = (SaveContractSpecificInfoResponse) executeService(saveContractSpecificInfoRequest);

			if (null != saveContractSpecificInfoResponse) {
				if (saveContractSpecificInfoResponse.isSuccess()) {
					updateTreeStructure();
					//sets values to the backing bean
					setValuesToBackingBean(saveContractSpecificInfoResponse
							.getDateSegment(), saveContractSpecificInfoResponse
							.getDomainDetail());
					getContractSession().getContractKeyObject().setProductId(
							saveContractSpecificInfoResponse.getDateSegment()
									.getProductId());
					getContractSession().setProductId(
							saveContractSpecificInfoResponse.getDateSegment()
									.getProductId());
				}
			}
			getRequest().setAttribute("RETAIN_Value", "");
		} else {
			addAllMessagesToRequest(this.validationMessages);
		}
		//	    this.setBreadCrumbText("Contract Development >>Contract >> Edit");
		getRequest().setAttribute("RETAIN_Value", "");
		return "";
	}

	public String saveAdaptedInfo() {
		if (validateAdapted()) {
			SaveContractAdaptedInfoRequest saveContractAdaptedInfoRequest = (SaveContractAdaptedInfoRequest) this
					.getServiceRequest(ServiceManager.SAVE_ADAPTEDINFOREQUEST);
			saveContractAdaptedInfoRequest = setAdaptedValuesToRequest(saveContractAdaptedInfoRequest);

			SaveContractAdaptedInfoResponse saveContractAdaptedInfoResponse = (SaveContractAdaptedInfoResponse) executeService(saveContractAdaptedInfoRequest);
			if (null != saveContractAdaptedInfoResponse) {
				getRequest().setAttribute("RETAIN_Value", "");
			}
		} else {

			addAllMessagesToRequest(validationMessages);
		}
		getRequest().setAttribute("RETAIN_Value", "");
		return "";
	}

	public boolean validateAdapted() {
		boolean valid = true;
		validationMessages = new ArrayList();
		String date1 = this.contractTermDate;
		String date2 = this.salesMarketDate;
		int dateTermValid = 0;
		int dateSalesValid = 0;
		Date dateTerm = WPDStringUtil.getDateFromString(date1);
		Date dateSales = WPDStringUtil.getDateFromString(date2);

		Date dateStd = WPDStringUtil.getDateFromString("01/01/1900");
		if (null != dateTerm)
			dateTermValid = dateTerm.compareTo(dateStd);
		if (null != dateSales)
			dateSalesValid = dateSales.compareTo(dateStd);

		if (!(WPDStringUtil.isValidDate(this.contractTermDate))
				|| (dateTermValid < 0)) {
			valid = false;
			validationMessages.add(new ErrorMessage(
					WebConstants.TERM_NOT_VALID_DATE));
		}
		if (!(WPDStringUtil.isValidDate(this.salesMarketDate))
				|| (dateSalesValid < 0)) {
			valid = false;
			validationMessages.add(new ErrorMessage(
					WebConstants.SALES_NOT_VALID_DATE));
		}

		return valid;
	}

	public SaveContractAdaptedInfoRequest setAdaptedValuesToRequest(
			SaveContractAdaptedInfoRequest saveContractAdaptedInfoRequest) {
		AdaptedInfoBO adaptedInfoBO = new AdaptedInfoBO();
		if ("".equals(this.getRegulatoryAgency())
				|| (this.getRegulatoryAgency()) == null) {
			adaptedInfoBO.setRegulatoryAgency(this.getRegulatoryAgency());
		} else {
			List regulatoryAgencyList = WPDStringUtil.getListFromTildaString(
					this.regulatoryAgency, 2, 1, 2);
			adaptedInfoBO.setRegulatoryAgency((String) regulatoryAgencyList
					.get(0));
		}
		adaptedInfoBO.setComplianceStatus(this.getComplianceStatus());
		if ("".equals(this.getProdProjNameCode())
				|| (this.getProdProjNameCode()) == null) {
			adaptedInfoBO.setProdProjNameCode(this.getProdProjNameCode());
		} else {
			List nameCodeList = WPDStringUtil.getListFromTildaString(
					this.prodProjNameCode, 2, 1, 2);
			adaptedInfoBO.setProdProjNameCode((String) nameCodeList.get(0));
		}
		adaptedInfoBO.setMultiplanIndicator(this.getMultiplanIndicator());
		adaptedInfoBO.setPerformanceGuarantee(this.getPerformanceGuarantee());
		adaptedInfoBO.setContractTermDate(WPDStringUtil.getDateFromString(this
				.getContractTermDate()));
		adaptedInfoBO.setSalesMarketDate(WPDStringUtil.getDateFromString(this
				.getSalesMarketDate()));
		adaptedInfoBO.setDateSegmentSysId(this.getContractKeyObject()
				.getDateSegmentId());
		saveContractAdaptedInfoRequest.setAdaptedInfoBO(adaptedInfoBO);
		saveContractAdaptedInfoRequest.setContractKeyObject(this
				.getContractKeyObject());
		return saveContractAdaptedInfoRequest;
	}

	//sets the values to the request
	public SaveContractSpecificInfoRequest setValuesToRequest(
			SaveContractSpecificInfoRequest saveContractSpecificInfoRequest) {

		DateSegment dateSegmentVO = new DateSegment();

		if ("".equals(this.getProductCode()))
			dateSegmentVO.setProductCode(this.getProductCode());
		else {
			List productCodeList = WPDStringUtil.getListFromTildaString(
					this.productCode, 2, 2, 2);
			dateSegmentVO.setProductCode((String) productCodeList.get(0));
		}

		dateSegmentVO.setProductCodeDesc(this.getProductCodeDesc());

		if (!"".equals(this.getProvSpecCode())) {
			List productCodeList = WPDStringUtil.getListFromTildaString(
					this.provSpecCode, 2, 2, 2);
			dateSegmentVO.setProviderSpecCodeList(productCodeList);
		}

		if ("".equals(this.getStandardPlanCode()))
			dateSegmentVO.setStandardPlanCode(this.getStandardPlanCode());
		else {
			List standardPlanCodeList = WPDStringUtil.getListFromTildaString(
					this.standardPlanCode, 2, 2, 2);
			dateSegmentVO.setStandardPlanCode((String) standardPlanCodeList
					.get(0));
		}

		dateSegmentVO.setBenefitPlan(this.benefitPlan.trim().toUpperCase());

		if ("".equals(this.getProduct()))
			dateSegmentVO.setProductId(0);
		else {
			List productList = WPDStringUtil.getListFromTildaString(
					this.product, 2, 1, 1);
			Integer productId = (Integer) productList.get(0);
			dateSegmentVO.setProductId(productId.intValue());
		}

		if ("".equals(this.getCorporatePlanCode()))
			dateSegmentVO.setCorporatePlanCode(this.getCorporatePlanCode());
		else {
			List corporatePlanCodeList = WPDStringUtil.getListFromTildaString(
					this.corporatePlanCode, 2, 2, 2);
			dateSegmentVO.setCorporatePlanCode((String) corporatePlanCodeList
					.get(0));
		}

		if (null != this.getBrandName()) {
			dateSegmentVO
					.setBrandName(this.getBrandName().trim().toUpperCase());
		}
		dateSegmentVO.setDateSegmentSysId(getContractKeyObject()
				.getDateSegmentId());
		dateSegmentVO.setGroupSize(this.getGroupSize());
		dateSegmentVO.setEffectiveDate(WPDStringUtil.getDateFromString(this
				.getEffectiveDate()));
		dateSegmentVO.setExpiryDate(WPDStringUtil.getDateFromString(this
				.getExpiryDate()));
		dateSegmentVO.setDateSegmentType(this.getDateSegmentType());
		dateSegmentVO.setCobAdjudicationIndicator(this
				.getCobAdjudicationIndicator());
		dateSegmentVO.setMedicareAdjudicationIndicator(this
				.getMedicareAdjudicationIndicator());
		dateSegmentVO.setItsAdjudicationIndicator(this
				.getItsAdjudicationIndicator());
		dateSegmentVO
				.setVersion(getContractKeyObject().getDateSegmentVersion());
		dateSegmentVO.setStatus(getContractKeyObject().getDateSegmentStatus());

		saveContractSpecificInfoRequest.setDateSegmentVO(dateSegmentVO);

		//sets the copyNeeded attribute depending on whether to copy the product components
		if (!((this.product).equals(this.hiddenProduct)))
			saveContractSpecificInfoRequest.setCopyNeeded(true);
		else
			saveContractSpecificInfoRequest.setCopyNeeded(false);

		return saveContractSpecificInfoRequest;
	}

	//loads the values for the contract specific information page
	public String loadContractSpecificInfo() {
		
		this.setContractIdForRefData(new Integer(this.getContractKeyObject()
				.getContractSysId()).toString());
		RetrieveContractSpecificInfoRequest retrieveContractSpecificInfoRequest = (RetrieveContractSpecificInfoRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_CONTRACTSPINFOREQUEST);
		//sets the dateSegmentId to the request
		retrieveContractSpecificInfoRequest
				.setDateSegmentId(getContractKeyObject().getDateSegmentId());

		RetrieveContractSpecificInfoResponse retrieveContractSpecificInfoResponse = (RetrieveContractSpecificInfoResponse) executeService(retrieveContractSpecificInfoRequest);

		if (null != retrieveContractSpecificInfoResponse) {
			//sets values to the backingbean for displaying
			setValuesToBackingBean(retrieveContractSpecificInfoResponse
					.getDateSegment(), retrieveContractSpecificInfoResponse
					.getDomainDetail());
		}
		//		this.setBreadCrumbText("Contract Development >>Contract >> Edit");	
		if (super.isViewMode())
			return "displayContractSpecificInfoViewTab";
		else {
			//getRequest().setAttribute("RETAIN_Value", "");	
			return "saveContractSpecificInfo";
		}
	}

	//loads the values for the contract specific information page
	public String loadContractAdaptedInfo() {
		this.setContractIdForRefData(new Integer(this.getContractKeyObject()
				.getContractSysId()).toString());
		RetrieveContractSpecificInfoRequest retrieveContractSpecificInfoRequest = (RetrieveContractSpecificInfoRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_CONTRACTSPINFOREQUEST);
		//sets the dateSegmentId to the request
		retrieveContractSpecificInfoRequest
				.setDateSegmentId(getContractKeyObject().getDateSegmentId());
		retrieveContractSpecificInfoRequest.setAction(2);
		retrieveContractSpecificInfoRequest
				.setContractKeyObject(getContractKeyObject());
		RetrieveContractSpecificInfoResponse retrieveContractSpecificInfoResponse = (RetrieveContractSpecificInfoResponse) executeService(retrieveContractSpecificInfoRequest);

		if (null != retrieveContractSpecificInfoResponse) {
			//sets values to the backingbean for displaying
			//setValuesToBackingBean(retrieveContractSpecificInfoResponse.getDateSegment(),retrieveContractSpecificInfoResponse.getDomainDetail());
			AdaptedInfoBO adaptedInfoBO = retrieveContractSpecificInfoResponse
					.getAdaptedInfoBO();
			if (null != adaptedInfoBO.getRegulatoryAgency())
				this.setRegulatoryAgency(adaptedInfoBO.getRegulatoryAgency()
						+ '~' + adaptedInfoBO.getRegulatoryAgencyDesc());
			if (null != adaptedInfoBO.getComplianceStatus())
				this.setComplianceStatus(adaptedInfoBO.getComplianceStatus());

			if (null != adaptedInfoBO.getComplianceStatusDesc())
				this.setComplianceStatusDesc(adaptedInfoBO
						.getComplianceStatusDesc());
			if (null != adaptedInfoBO.getProdProjNameCode())
				this.setProdProjNameCode(adaptedInfoBO.getProdProjNameCode()
						+ '~' + adaptedInfoBO.getProdProjNameCodeDesc());
			if (null != adaptedInfoBO.getMultiplanIndicator())
				this.setMultiplanIndicator(adaptedInfoBO
						.getMultiplanIndicator());
			if (null != adaptedInfoBO.getPerformanceGuarantee())
				this.setPerformanceGuarantee(adaptedInfoBO
						.getPerformanceGuarantee());
			if (null != adaptedInfoBO.getContractTermDate())
				this.setContractTermDate(WPDStringUtil
						.convertDateToString(adaptedInfoBO
								.getContractTermDate()));
			if (null != adaptedInfoBO.getSalesMarketDate())
				this
						.setSalesMarketDate(WPDStringUtil
								.convertDateToString(adaptedInfoBO
										.getSalesMarketDate()));

		}
		//		this.setBreadCrumbText("Contract Development >>Contract >> Edit");	
		if (super.isViewMode())
			return "displayContractAdaptedInfoViewTab";
		else
			return "saveContractAdaptedInfo";
	}

	/**
	 * This function sets values to the backing bean for displaying in the page
	 * @param dateSegment
	 */
	private void setValuesToBackingBean(DateSegment dateSegment,
			DomainDetail domainDetail) {
		// TODO Auto-generated method stub

		if (dateSegment.getProductCodeDesc() != null)
			this.setProductCode(dateSegment.getProductCodeDesc() + '~'
					+ dateSegment.getProductCode());

		if (dateSegment.getProductCodeDesc() != null)
			this.setProductCodeDesc(dateSegment.getProductCodeDesc());

		if (dateSegment.getStandardPlanCodeDesc() != null)
			this.setStandardPlanCode(dateSegment.getStandardPlanCodeDesc()
					+ '~' + dateSegment.getStandardPlanCode());
		this.setBenefitPlan(dateSegment.getBenefitPlan());
		if (dateSegment.getProductFamily() != null)
			this.setProductFamily(dateSegment.getProductFamily());
		if (dateSegment.getCorporatePlanCodeDesc() != null)
			this.setCorporatePlanCode(dateSegment.getCorporatePlanCodeDesc()
					+ '~' + dateSegment.getCorporatePlanCode());
		if (dateSegment.getFundingArrangementDesc() != null)
			this.setFundingArrangement(dateSegment.getFundingArrangement()
					+ '~' + dateSegment.getFundingArrangementDesc());
		this.setBrandName(dateSegment.getBrandName());
		if (null != dateSegment.getProductDesc())
			this.product = Integer.toString(dateSegment.getProductId()) + '~'
					+ dateSegment.getProductDesc();
		if (null != getContractSession().getContractKeyObject().getState())
			this.state = getContractSession().getContractKeyObject().getState();
		this.version = getContractSession().getContractKeyObject().getVersion();
		if (null != getContractSession().getContractKeyObject().getStatus())
			this.status = getContractSession().getContractKeyObject()
					.getStatus();
		this.groupSize = dateSegment.getGroupSize();
		this.effectiveDate = WPDStringUtil.convertDateToString(dateSegment
				.getEffectiveDate());

		this.expiryDate = WPDStringUtil.convertDateToString(dateSegment
				.getExpiryDate());
		if (dateSegment.getHeadQuartersState() != null)
			this.setHeadQuartersState(dateSegment.getHeadQuartersState() + '~'
					+ dateSegment.getHeadQuartersStateDesc());
		//		this.headQuartersState=dateSegment.getHeadQuartersState();

		if (domainDetail != null) {
			this.lineOfBusiness = WPDStringUtil.getTildaString(domainDetail
					.getLineOfBusiness());
			this.businessEntity = WPDStringUtil.getTildaString(domainDetail
					.getBusinessEntity());
			this.businessGroup = WPDStringUtil.getTildaString(domainDetail
					.getBusinessGroup());
			/*START CARS*/
			this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
					.getMarketBusinessUnit());
			/*END CARS*/
		}
		this.hiddenProduct = product;

		getContractSession().getContractKeyObject().setProductId(
				dateSegment.getProductId());

		//checks if the contract type is CUSTOM
		/* if(getContractSession().getContractKeyObject().getContractType().equals("CUSTOM"))
		 this.custom=false;*/
		if (getContractSession().getContractKeyObject().getContractType()
				.equals("MANDATE"))
			this.mandate = false;
		if (!(this.product == null || "".equals(this.getProduct()))) {
			this.custom = false;
		}
		/* if(dateSegment.getDateSegmentType()!=null){
		 if(dateSegment.getDateSegmentType().equals("Y")){
		 if(getContractSession().getInitialTest()!=null){
		 if(getContractSession().getInitialTest().equals("Y"))
		 getContractSession().setInitialTest(null);	
		 this.custom=true;
		 }
		 }
		 }	 */

		if (dateSegment.getDateSegmentType() != null) {
			if (dateSegment.getDateSegmentType().equals("Y"))
				getContractSession().setTestContractState("SCHEDULED_FOR_TEST");
			else
				getContractSession().setTestContractState(null);
		}

		this.setDateSegmentType(dateSegment.getDateSegmentType());
		if (dateSegment.getCobAdjudicationIndicator() != null) {
			this.setCobAdjudicationIndicator(dateSegment
					.getCobAdjudicationIndicator());
		}
		if (dateSegment.getMedicareAdjudicationIndicator() != null) {
			this.setMedicareAdjudicationIndicator(dateSegment
					.getMedicareAdjudicationIndicator());
		}
		if (dateSegment.getItsAdjudicationIndicator() != null) {
			this.setItsAdjudicationIndicator(dateSegment
					.getItsAdjudicationIndicator());
		}

		//Converting the list of provider speciality codes to a string
		List providerSpecialityCodeList = dateSegment.getProviderSpecCodeList();

		if (providerSpecialityCodeList != null
				&& providerSpecialityCodeList.size() > 0) {
			StringBuffer specCode = new StringBuffer();
			StringBuffer specCodeAndDesc = new StringBuffer();
			for (Iterator iter = providerSpecialityCodeList.iterator(); iter
					.hasNext();) {
				ProviderSpecialityCodeBO providerSpecialityCodeBO = (ProviderSpecialityCodeBO) iter
						.next();
				specCode.append(
						providerSpecialityCodeBO
								.getProviderSpecialityCodeDescription())
						.append("~").append(
								providerSpecialityCodeBO.getSpecialityCode())
						.append("~");
				//start feb release 2010 -modification for specific info print 
				specCodeAndDesc.append(
						providerSpecialityCodeBO
						.getSpecialityCode())
						.append("~").append(
						providerSpecialityCodeBO.getSpecialityCodeForView())
						.append("~");
				//	end feb release 2010 -modification for specific info print 
			}
			if (specCode.length() > 1)
				specCode.deleteCharAt(specCode.length() - 1); // Delete the last ~
			this.setProvSpecCode(specCode.toString());
			//			start feb release 2010 -modification for specific info print 
			if (specCodeAndDesc.length() > 1)
				specCodeAndDesc.deleteCharAt(specCodeAndDesc.length() - 1);
			this.setProviderSpecialtyCodeAndDesc(specCodeAndDesc.toString());
			//			end feb release 2010 -modification for specific info print 
		}

	}

	//checks for the validation of the fields in the page	
	public boolean isValid() {
		boolean requiredField = false;
		this.requiredBenefitPlan = false;
		this.requiredProduct = false;
		this.requiredProductCode = false;
		this.requiredCorporateCode = false;
		this.requiredCobIndicator = false;
		this.requiredMedIndicator = false;
		this.requiredItsIndicator = false;
		this.requiredProductCodeDesc = false;
		validationMessages = new ArrayList();
		boolean providerspecCode = true;

		if (!"".equals(this.getProvSpecCode())) {
			List productCodeList = WPDStringUtil.getListFromTildaString(
					this.provSpecCode, 2, 2, 2);
			if (productCodeList.size() > 20) {
				providerspecCode = false;
				validationMessages.add(new ErrorMessage(
						WebConstants.MAX_PROVIDER_SPEC_CODE));
			}
		}

		if (!(getContractSession().getContractKeyObject().getContractType()
				.equals("SHELL"))) {
			if ("".equals(this.getProductCode())) {
				requiredProductCode = true;
				requiredField = true;
			}
			if ("".equals(this.getCobAdjudicationIndicator())) {
				requiredCobIndicator = true;
				requiredField = true;
			}
			if ("".equals(this.getMedicareAdjudicationIndicator())) {
				requiredMedIndicator = true;
				requiredField = true;
			}
			if ("".equals(this.getItsAdjudicationIndicator())) {
				requiredItsIndicator = true;
				requiredField = true;
			}
			if ("".equals(this.getProduct())) {
				requiredProduct = true;
				requiredField = true;
			}
		}

		if (!(getContractSession().getContractKeyObject().getContractType()
				.equals("SHELL"))) {
			if ("".equals(this.getBenefitPlan().trim())) {
				requiredBenefitPlan = true;
				requiredField = true;
			}

		}
		if (null != this.getBenefitPlan()
				&& !"".equals(this.getBenefitPlan().trim())) {
			if (this.getBenefitPlan().trim().length() > 15) {
				validationMessages.add(new ErrorMessage(
						WebConstants.INVALID_BENEFIT_PLAN_NAME));
				return false;
			}
		}
		if (this.getBrandName() != null
				&& !"".equals(this.getBrandName().trim())) {
			if (this.getBrandName().trim().length() > 30) {
				validationMessages.add(new ErrorMessage(
						WebConstants.INVALID_BRAND_NAME));

				return false;
			}
		}

		if (this.getProductCodeDesc() == null
				|| "".equals(this.getProductCodeDesc().trim())) {
			requiredProductCodeDesc = true;
			requiredField = true;
		} else if (this.getProductCodeDesc().trim().length() > 40) {
		    requiredProductCodeDesc = true;
			validationMessages.add(new ErrorMessage(
					WebConstants.INVALID_PRODUCT_CODE_DESC));
			
		}


		if (requiredField) {
			validationMessages.add(new ErrorMessage( 
					WebConstants.MANDATORY_FIELDS_REQUIRED));
			return false;
		}
		
		if (requiredProductCodeDesc) {
		    return false;
		}
		
		
		if (!checkProductFamily()) {

			if ("".equals(this.getCorporatePlanCode())) {
				requiredCorporateCode = true;
				validationMessages.add(new ErrorMessage(
						WebConstants.CORPORATE_PLAN_CODE_REQUIRED));
				return false;
			}

		}

		return (true && providerspecCode);
	}

	public String done() {
		if (isValid()) {
			
			SaveContractSpecificInfoRequest saveContractSpecificInfoRequest = (SaveContractSpecificInfoRequest) this
					.getServiceRequest(ServiceManager.SAVE_CONTRACTSPINFOREQUEST);
			//sets values to the request
			saveContractSpecificInfoRequest = setValuesToRequest(saveContractSpecificInfoRequest);

			if (getContractSession().getContractKeyObject().getProductId() != 0)
				saveContractSpecificInfoRequest.setSave(false);
			else
				saveContractSpecificInfoRequest.setSave(true);

			SaveContractSpecificInfoResponse saveContractSpecificInfoResponse = (SaveContractSpecificInfoResponse) executeService(saveContractSpecificInfoRequest);
			List msgListOne = new ArrayList();
			List msgListTwo = new ArrayList();
			if (null != saveContractSpecificInfoResponse) {
				if (saveContractSpecificInfoResponse.isSuccess()) {

					msgListOne = saveContractSpecificInfoResponse.getMessages();
					//sets values to the backing bean
					setValuesToBackingBean(saveContractSpecificInfoResponse
							.getDateSegment(), saveContractSpecificInfoResponse
							.getDomainDetail());
					getContractSession().getContractKeyObject().setProductId(
							saveContractSpecificInfoResponse.getDateSegment()
									.getProductId());

					SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
							.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
					SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
					ContractVO contractVO = new ContractVO();
					contractVO.setContractSysId(this.getContractSession()
							.getContractKeyObject().getContractSysId());
					contractVO.setContractId(this.getContractSession()
							.getContractKeyObject().getContractId());
					contractVO.setVersion(this.getContractSession()
							.getContractKeyObject().getVersion());
					contractVO.setStatus(this.getContractSession()
							.getContractKeyObject().getStatus());
					saveContractBasicInfoRequest.setContractVO(contractVO);
					saveContractBasicInfoRequest
							.setAction(SaveContractBasicInfoRequest.CHECKIN_CONTRACT);
					saveContractBasicInfoRequest.setChechIn(this.checkin);
					// SSCR 16332 -Start
					if (this.checkin && !getInvokeWebService().equals("confirm") && getInvokeWebService() != null) {
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
							if (null != getSession()
									.getAttribute(
											"adminmethodContractCodedSPSTreeBackingBean"))
								getSession()
										.removeAttribute(
												"adminmethodContractCodedSPSTreeBackingBean");
							setValuesForAdminMethodValidation();
							return "";
						} else if (saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_WAIT) {
							getSession().setAttribute(
									WebConstants.OBJECT_KEY_FOR_CHECKIN,
									"contractSpecificInfoBackingBean");
							getSession()
									.setAttribute(
											WebConstants.OBJECT_VALUE_FOR_CHECKIN,
											this);
							getSession().setAttribute(
									WebConstants.ENTITY_ID_FOR_CHECKIN,
									new Integer(saveContractBasicInfoRequest
											.getContractKeyObject()
											.getDateSegmentId()));
							getSession().setAttribute(
									WebConstants.CONTRACT_ID_FOR_CHECKIN,
									new Integer(saveContractBasicInfoRequest
											.getContractKeyObject()
											.getContractSysId()));
							getSession().setAttribute(
									WebConstants.ENTITY_TYPE_FOR_CHECKIN,
									WebConstants.ENTITY_TYPE_CONTRACT);
							getSession().setAttribute(
									WebConstants.ACTION_FOR_CHECKIN,
									new Integer(2));
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
							return "saveContractAdaptedInfo";
						}else {
							if (null != saveContractBasicInfoResponse
									.getMessages()) {
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
				}
			}
		} else {
			addAllMessagesToRequest(validationMessages);
		}
		this.checkin = false;
		getRequest().setAttribute("RETAIN_Value", "");
		return WebConstants.EMPTY_STRING;
	}
	
	public String checkIn() {
		
		SaveContractAdaptedInfoRequest saveContractAdaptedInfoRequest = (SaveContractAdaptedInfoRequest) this
				.getServiceRequest(ServiceManager.SAVE_ADAPTEDINFOREQUEST);
		saveContractAdaptedInfoRequest = setAdaptedValuesToRequest(saveContractAdaptedInfoRequest);

		SaveContractAdaptedInfoResponse saveContractAdaptedInfoResponse = (SaveContractAdaptedInfoResponse) executeService(saveContractAdaptedInfoRequest);
		List msgListOne = new ArrayList();
		List msgListTwo = new ArrayList();
		if (null != saveContractAdaptedInfoResponse) {
			msgListOne = saveContractAdaptedInfoResponse.getMessages();
			SaveContractBasicInfoRequest saveContractBasicInfoRequest = (SaveContractBasicInfoRequest) this
					.getServiceRequest(ServiceManager.CREATE_CONTRACT_BASICINFO_BENEFIT);
			SaveContractBasicInfoResponse saveContractBasicInfoResponse = null;
			ContractVO contractVO = new ContractVO();
			contractVO.setContractSysId(this.getContractSession()
					.getContractKeyObject().getContractSysId());
			contractVO.setContractId(this.getContractSession()
					.getContractKeyObject().getContractId());
			contractVO.setVersion(this.getContractSession()
					.getContractKeyObject().getVersion());
			contractVO.setStatus(this.getContractSession()
					.getContractKeyObject().getStatus());
			saveContractBasicInfoRequest.setContractVO(contractVO);
			saveContractBasicInfoRequest
					.setAction(SaveContractBasicInfoRequest.CHECKIN_CONTRACT);
			saveContractBasicInfoRequest.setChechIn(this.checkin);
			// SSCR 16332 -Start
			if (this.checkin && !getInvokeWebService().equals("confirm") && getInvokeWebService() != null) {
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
				} else if (saveContractBasicInfoResponse.getCondition() == SaveContractBasicInfoResponse.VALIDATION_WAIT) {
					getSession().setAttribute(
							WebConstants.OBJECT_KEY_FOR_CHECKIN,
							"contractSpecificInfoBackingBean");
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
					return "saveContractAdaptedInfo";
				} else {
					if (null != saveContractBasicInfoResponse.getMessages()) {
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
		}
		getRequest().setAttribute("RETAIN_Value", "");
		return WebConstants.EMPTY_STRING;
	}
	
	public String doContractCancelAction(){ 
		this.setWebServiceFlag("false");
    	getSession().removeAttribute("contractWSErrorList");
    	getSession().removeAttribute("wSErrorDisplayList");    
    	return "";
    }
	
	public String getStringFromTildaString(String tildaString) {
		String stringArray[] = tildaString.split("~");
		tildaString = stringArray[0];
		return tildaString;
	}

	public void clearBackingBeanValues() {
		this.setProductCode("");
		this.setStandardPlanCode("");
		this.setBenefitPlan("");
		this.setProductFamily("");
		this.setCorporatePlanCode("");
		this.setFundingArrangement("");
		this.setBrandName("");
		this.product = "";
		this.state = "";
		this.version = -1;
		this.status = "";
		this.groupSize = "";
		this.effectiveDate = "";
		this.expiryDate = "";
	}

	public boolean checkNull(AffiliationObject affiliationObject) {
		if (null != affiliationObject) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the productCode
	 * @return String productCode.
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * Sets the productCode
	 * @param productCode.
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * Returns the standardPlanCode
	 * @return String standardPlanCode.
	 */
	public String getStandardPlanCode() {
		return standardPlanCode;
	}

	/**
	 * Sets the standardPlanCode
	 * @param standardPlanCode.
	 */
	public void setStandardPlanCode(String standardPlanCode) {
		this.standardPlanCode = standardPlanCode;
	}

	/**
	 * Returns the brandName
	 * @return String brandName.
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * Sets the brandName
	 * @param brandName.
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * Returns the corporatePlanCode
	 * @return String corporatePlanCode.
	 */
	public String getCorporatePlanCode() {
		return corporatePlanCode;
	}

	/**
	 * Sets the corporatePlanCode
	 * @param corporatePlanCode.
	 */
	public void setCorporatePlanCode(String corporatePlanCode) {
		this.corporatePlanCode = corporatePlanCode;
	}

	/**
	 * Returns the fundingArrangement
	 * @return String fundingArrangement.
	 */
	public String getFundingArrangement() {
		return fundingArrangement;
	}

	/**
	 * Sets the fundingArrangement
	 * @param fundingArrangement.
	 */
	public void setFundingArrangement(String fundingArrangement) {
		this.fundingArrangement = fundingArrangement;
	}

	/**
	 * Returns the product
	 * @return String product.
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * Sets the product
	 * @param product.
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * Returns the benefitPlan
	 * @return String benefitPlan.
	 */
	public String getBenefitPlan() {
		return benefitPlan;
	}

	/**
	 * Sets the benefitPlan
	 * @param benefitPlan.
	 */
	public void setBenefitPlan(String benefitPlan) {
		this.benefitPlan = benefitPlan;
	}

	/**
	 * Returns the groupName
	 * @return String groupName.
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * Sets the groupName
	 * @param groupName.
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * Returns the headQuartersState
	 * @return String headQuartersState.
	 */
	public String getHeadQuartersState() {
		return headQuartersState;
	}

	/**
	 * Sets the headQuartersState
	 * @param headQuartersState.
	 */
	public void setHeadQuartersState(String headQuartersState) {
		this.headQuartersState = headQuartersState;
	}

	/**
	 * Returns the state
	 * @return String state.
	 */
	public String getState() {

		return getContractSession().getContractKeyObject().getState();
	}

	/**
	 * Sets the state
	 * @param state.
	 */
	public void setState(String state) {

		this.state = state;
	}

	/**
	 * Returns the status
	 * @return String status.
	 */
	public String getStatus() {
		return getContractSession().getContractKeyObject().getStatus();
	}

	/**
	 * Sets the status
	 * @param status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Returns the checkin
	 * @return boolean checkin.
	 */
	public boolean isCheckin() {
		return checkin;
	}

	/**
	 * Sets the checkin
	 * @param checkin.
	 */
	public void setCheckin(boolean checkin) {
		this.checkin = checkin;
	}

	/**
	 * Returns the version
	 * @return int version.
	 */
	public int getVersion() {
		return getContractSession().getContractKeyObject().getVersion();
	}

	/**
	 * Sets the version
	 * @param version.
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Returns the requiredBenefitPlan
	 * @return boolean requiredBenefitPlan.
	 */
	public boolean isRequiredBenefitPlan() {
		return requiredBenefitPlan;
	}

	/**
	 * Sets the requiredBenefitPlan
	 * @param requiredBenefitPlan.
	 */
	public void setRequiredBenefitPlan(boolean requiredBenefitPlan) {
		this.requiredBenefitPlan = requiredBenefitPlan;
	}

	/**
	 * Returns the requiredProduct
	 * @return boolean requiredProduct.
	 */
	public boolean isRequiredProduct() {
		return requiredProduct;
	}

	/**
	 * Sets the requiredProduct
	 * @param requiredProduct.
	 */
	public void setRequiredProduct(boolean requiredProduct) {
		this.requiredProduct = requiredProduct;
	}

	/**
	 * Returns the requiredProductCode
	 * @return boolean requiredProductCode.
	 */
	public boolean isRequiredProductCode() {
		return requiredProductCode;
	}

	/**
	 * Sets the requiredProductCode
	 * @param requiredProductCode.
	 */
	public void setRequiredProductCode(boolean requiredProductCode) {
		this.requiredProductCode = requiredProductCode;
	}

	/**
	 * Returns the productFamily
	 * @return String productFamily.
	 */
	public String getProductFamily() {
		return productFamily;
	}

	/**
	 * Sets the productFamily
	 * @param productFamily.
	 */
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}

	/**
	 * Returns the groupSize
	 * @return String groupSize.
	 */
	public String getGroupSize() {
		return groupSize;
	}

	/**
	 * Sets the groupSize
	 * @param groupSize.
	 */
	public void setGroupSize(String groupSize) {
		this.groupSize = groupSize;
	}

	/**
	 * Returns the lastUpdatedTimeStamp
	 * @return Date lastUpdatedTimeStamp.
	 */
	public Date getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}

	/**
	 * Sets the lastUpdatedTimeStamp
	 * @param lastUpdatedTimeStamp.
	 */
	public void setLastUpdatedTimeStamp(Date lastUpdatedTimeStamp) {
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}

	/**
	 * Returns the lastUpdatedUser
	 * @return String lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	/**
	 * Sets the lastUpdatedUser
	 * @param lastUpdatedUser.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	/**
	 * Returns the businessEntityCodeList
	 * @return List businessEntityCodeList.
	 */
	public List getBusinessEntityCodeList() {
		return businessEntityCodeList;
	}

	/**
	 * Sets the businessEntityCodeList
	 * @param businessEntityCodeList.
	 */
	public void setBusinessEntityCodeList(List businessEntityCodeList) {
		this.businessEntityCodeList = businessEntityCodeList;
	}

	/**
	 * Returns the businessGroupCodeList
	 * @return List businessGroupCodeList.
	 */
	public List getBusinessGroupCodeList() {
		return businessGroupCodeList;
	}

	/**
	 * Sets the businessGroupCodeList
	 * @param businessGroupCodeList.
	 */
	public void setBusinessGroupCodeList(List businessGroupCodeList) {
		this.businessGroupCodeList = businessGroupCodeList;
	}

	/**
	 * Returns the headQuartersStateList
	 * @return List headQuartersStateList.
	 */
	public List getHeadQuartersStateList() {
		return headQuartersStateList;
	}

	/**
	 * Sets the headQuartersStateList
	 * @param headQuartersStateList.
	 */
	public void setHeadQuartersStateList(List headQuartersStateList) {
		this.headQuartersStateList = headQuartersStateList;
	}

	/**
	 * Returns the lobCodeList
	 * @return List lobCodeList.
	 */
	public List getLobCodeList() {
		return lobCodeList;
	}

	/**
	 * Sets the lobCodeList
	 * @param lobCodeList.
	 */
	public void setLobCodeList(List lobCodeList) {
		this.lobCodeList = lobCodeList;
	}

	/**
	 * Returns the businessEntity
	 * @return String businessEntity.
	 */
	public String getBusinessEntity() {
		return businessEntity;
	}

	/**
	 * Sets the businessEntity
	 * @param businessEntity.
	 */
	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity;
	}

	/**
	 * Returns the businessGroup
	 * @return String businessGroup.
	 */
	public String getBusinessGroup() {
		return businessGroup;
	}

	/**
	 * Sets the businessGroup
	 * @param businessGroup.
	 */
	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}

	/**
	 * Returns the lineOfBusiness
	 * @return String lineOfBusiness.
	 */
	public String getLineOfBusiness() {
		return lineOfBusiness;
	}

	/**
	 * Sets the lineOfBusiness
	 * @param lineOfBusiness.
	 */
	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}

	/**
	 * Returns the effectiveDate
	 * @return String effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * Sets the effectiveDate
	 * @param effectiveDate.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Returns the expiryDate
	 * @return String expiryDate.
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Sets the expiryDate
	 * @param expiryDate.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * Returns the custom
	 * @return boolean custom.
	 */
	public boolean isCustom() {
		return custom;
	}

	/**
	 * Sets the custom
	 * @param custom.
	 */
	public void setCustom(boolean custom) {
		this.custom = custom;
	}

	/**
	 * Returns the hiddenProduct
	 * @return String hiddenProduct.
	 */
	public String getHiddenProduct() {
		return hiddenProduct;
	}

	/**
	 * Sets the hiddenProduct
	 * @param hiddenProduct.
	 */
	public void setHiddenProduct(String hiddenProduct) {
		this.hiddenProduct = hiddenProduct;
	}

	/**
	 * Returns the requiredCorporateCode
	 * @return boolean requiredCorporateCode.
	 */
	public boolean isRequiredCorporateCode() {
		return requiredCorporateCode;
	}

	/**
	 * Sets the requiredCorporateCode
	 * @param requiredCorporateCode.
	 */
	public void setRequiredCorporateCode(boolean requiredCorporateCode) {
		this.requiredCorporateCode = requiredCorporateCode;
	}

	/**
	 *This function retrieves the product Family for the selected product. 
	 */
	private boolean checkProductFamily() {
		// TODO Auto-generated method stub

		int productId;

		if ("".equals(this.getProduct()))
			productId = 0;
		else {
			List productList = WPDStringUtil.getListFromTildaString(
					this.product, 2, 1, 1);
			Integer productIdObj = (Integer) productList.get(0);
			productId = productIdObj.intValue();
		}

		//gets request
		RetrieveContractProductRequest request = (RetrieveContractProductRequest) getServiceRequest(ServiceManager.RETRIEVE_CONTRACT_PRODUCT);
		request.setProductKey(productId);
		//calls the service
		RetrieveContractProductResponse response = (RetrieveContractProductResponse) executeService(request);
		if (response != null && response.getProductBO() != null) {
			if (response.getProductBO().getProductFamilyId().equals("HMO")
					|| response.getProductBO().getProductFamilyId().equals(
							"POS"))
				return false;
			else
				return true;
		} else
			return true;
	}

	/**
	 * @return Returns the mandate.
	 */
	public boolean isMandate() {
		return mandate;
	}

	/**
	 * @param mandate The mandate to set.
	 */
	public void setMandate(boolean mandate) {
		this.mandate = mandate;
	}

	public void setInitViewForPrint(String temp) {

	}

	public String getInitViewForPrint() {
		RetrieveContractSpecificInfoRequest retrieveContractSpecificInfoRequest = (RetrieveContractSpecificInfoRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_CONTRACTSPINFOREQUEST);
		//sets the dateSegmentId to the request
		retrieveContractSpecificInfoRequest
				.setDateSegmentId(getContractKeyObject().getDateSegmentId());

		RetrieveContractSpecificInfoResponse retrieveContractSpecificInfoResponse = (RetrieveContractSpecificInfoResponse) executeService(retrieveContractSpecificInfoRequest);

		if (null != retrieveContractSpecificInfoResponse) {
			//sets values to the backingbean for displaying
			setValuesToBackingBean(retrieveContractSpecificInfoResponse
					.getDateSegment(), retrieveContractSpecificInfoResponse
					.getDomainDetail());
		}
		return "";
	}

	public void setInitViewForPrintForAdapted() {

	}

	public String getInitViewForPrintForAdapted() {
		loadContractAdaptedInfo();
		return "";
	}

	/**
	 * Returns the printValue
	 * @return String printValue.
	 */
	public String getPrintValue() {
		String requestForPrint = getRequest().getParameter(
				"printContractSpecificInfo");
		if (null != requestForPrint && !requestForPrint.equals("")) {
			printValue = requestForPrint;
		} else {
			printValue = "";
		}
		return printValue;
	}

	/**
	 * Sets the printValue
	 * @param printValue.
	 */
	public void setPrintValue(String printValue) {
		this.printValue = printValue;
	}

	/**
	 * @return Returns the contractIdForRefData.
	 */
	public String getContractIdForRefData() {
		return contractIdForRefData;
	}

	/**
	 * @param contractIdForRefData The contractIdForRefData to set.
	 */
	public void setContractIdForRefData(String contractIdForRefData) {
		this.contractIdForRefData = contractIdForRefData;
	}

	/**
	 * Returns the dateSegmentType
	 * @return String dateSegmentType.
	 */
	public String getDateSegmentType() {
		return dateSegmentType;
	}

	/**
	 * Sets the dateSegmentType
	 * @param dateSegmentType.
	 */
	public void setDateSegmentType(String dateSegmentType) {
		this.dateSegmentType = dateSegmentType;
	}

	/**
	 * Returns the cobAdjudicationIndicator
	 * @return String cobAdjudicationIndicator.
	 */
	public String getCobAdjudicationIndicator() {
		return cobAdjudicationIndicator;
	}

	/**
	 * Sets the cobAdjudicationIndicator
	 * @param cobAdjudicationIndicator.
	 */
	public void setCobAdjudicationIndicator(String cobAdjudicationIndicator) {
		this.cobAdjudicationIndicator = cobAdjudicationIndicator;
	}

	/**
	 * Returns the medicareAdjudicationIndicator
	 * @return String medicareAdjudicationIndicator.
	 */
	public String getMedicareAdjudicationIndicator() {
		return medicareAdjudicationIndicator;
	}

	/**
	 * Sets the medicareAdjudicationIndicator
	 * @param medicareAdjudicationIndicator.
	 */
	public void setMedicareAdjudicationIndicator(
			String medicareAdjudicationIndicator) {
		this.medicareAdjudicationIndicator = medicareAdjudicationIndicator;
	}

	/**
	 * Returns the itsAdjudicationIndicator
	 * @return String itsAdjudicationIndicator.
	 */
	public String getItsAdjudicationIndicator() {
		return itsAdjudicationIndicator;
	}

	/**
	 * Sets the itsAdjudicationIndicator
	 * @param itsAdjudicationIndicator.
	 */
	public void setItsAdjudicationIndicator(String itsAdjudicationIndicator) {
		this.itsAdjudicationIndicator = itsAdjudicationIndicator;
	}

	/**
	 * Returns the requiredCobIndicator
	 * @return boolean requiredCobIndicator.
	 */
	public boolean isRequiredCobIndicator() {
		return requiredCobIndicator;
	}

	/**
	 * Sets the requiredCobIndicator
	 * @param requiredCobIndicator.
	 */
	public void setRequiredCobIndicator(boolean requiredCobIndicator) {
		this.requiredCobIndicator = requiredCobIndicator;
	}

	/**
	 * Returns the requiredMedIndicator
	 * @return boolean requiredMedIndicator.
	 */
	public boolean isRequiredMedIndicator() {
		return requiredMedIndicator;
	}

	/**
	 * Sets the requiredMedIndicator
	 * @param requiredMedIndicator.
	 */
	public void setRequiredMedIndicator(boolean requiredMedIndicator) {
		this.requiredMedIndicator = requiredMedIndicator;
	}

	/**
	 * Returns the requiredItsIndicator
	 * @return boolean requiredItsIndicator.
	 */
	public boolean isRequiredItsIndicator() {
		return requiredItsIndicator;
	}

	/**
	 * Sets the requiredItsIndicator
	 * @param requiredItsIndicator.
	 */
	public void setRequiredItsIndicator(boolean requiredItsIndicator) {
		this.requiredItsIndicator = requiredItsIndicator;
	}

	/**
	 * Returns the regulatoryAgency
	 * @return String regulatoryAgency.
	 */
	public String getRegulatoryAgency() {
		return regulatoryAgency;
	}

	/**
	 * Sets the regulatoryAgency
	 * @param regulatoryAgency.
	 */
	public void setRegulatoryAgency(String regulatoryAgency) {
		this.regulatoryAgency = regulatoryAgency;
	}

	/**
	 * Returns the complianceStatus
	 * @return String complianceStatus.
	 */
	public String getComplianceStatus() {
		return complianceStatus;
	}

	/**
	 * Sets the complianceStatus
	 * @param complianceStatus.
	 */
	public void setComplianceStatus(String complianceStatus) {
		this.complianceStatus = complianceStatus;
	}

	/**
	 * Returns the prodProjNameCode
	 * @return String prodProjNameCode.
	 */
	public String getProdProjNameCode() {
		return prodProjNameCode;
	}

	/**
	 * Sets the prodProjNameCode
	 * @param prodProjNameCode.
	 */
	public void setProdProjNameCode(String prodProjNameCode) {
		this.prodProjNameCode = prodProjNameCode;
	}

	/**
	 * Returns the contractTermDate
	 * @return String contractTermDate.
	 */
	public String getContractTermDate() {
		return contractTermDate;
	}

	/**
	 * Sets the contractTermDate
	 * @param contractTermDate.
	 */
	public void setContractTermDate(String contractTermDate) {
		this.contractTermDate = contractTermDate.trim();
	}

	/**
	 * Returns the multiplanIndicator
	 * @return String multiplanIndicator.
	 */
	public String getMultiplanIndicator() {
		return multiplanIndicator;
	}

	/**
	 * Sets the multiplanIndicator
	 * @param multiplanIndicator.
	 */
	public void setMultiplanIndicator(String multiplanIndicator) {
		this.multiplanIndicator = multiplanIndicator;
	}

	/**
	 * Returns the performanceGuarantee
	 * @return String performanceGuarantee.
	 */
	public String getPerformanceGuarantee() {
		return performanceGuarantee;
	}

	/**
	 * Sets the performanceGuarantee
	 * @param performanceGuarantee.
	 */
	public void setPerformanceGuarantee(String performanceGuarantee) {
		this.performanceGuarantee = performanceGuarantee;
	}

	/**
	 * Returns the salesMarketDate
	 * @return String salesMarketDate.
	 */
	public String getSalesMarketDate() {
		return salesMarketDate;
	}

	/**
	 * Sets the salesMarketDate
	 * @param salesMarketDate.
	 */
	public void setSalesMarketDate(String salesMarketDate) {
		this.salesMarketDate = salesMarketDate.trim();
	}

	/**
	 * @return Returns the printValueAdapted.
	 */
	public String getPrintValueAdapted() {
		String requestForPrint = getRequest().getParameter(
				"printValueForAdapted");
		if (null != requestForPrint && !requestForPrint.equals("")) {
			printValueAdapted = requestForPrint;
		} else {
			printValueAdapted = "";
		}
		return printValueAdapted;
	}

	/**
	 * @param printValueAdapted The printValueAdapted to set.
	 */
	public void setPrintValueAdapted(String printValueAdapted) {
		this.printValueAdapted = printValueAdapted;
	}

	/**
	 * @return Returns the complianceStatusDesc.
	 */
	public String getComplianceStatusDesc() {
		return complianceStatusDesc;
	}

	/**
	 * @param complianceStatusDesc The complianceStatusDesc to set.
	 */
	public void setComplianceStatusDesc(String complianceStatusDesc) {
		this.complianceStatusDesc = complianceStatusDesc;
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
	 * @return Returns the be.
	 */
	public String getBe() {
		be = this.getSession().getAttribute("be").toString();
		return be;
	}

	/**
	 * @param be The be to set.
	 */
	public void setBe(String be) {
		this.be = be;
	}

	/**
	 * @return Returns the bg.
	 */
	public String getBg() {
		bg = this.getSession().getAttribute("bg").toString();
		return bg;
	}

	/**
	 * @param bg The bg to set.
	 */
	public void setBg(String bg) {
		this.bg = bg;
	}

	/**
	 * @return Returns the lob.
	 */
	public String getLob() {
		lob = this.getSession().getAttribute("lob").toString();
		return lob;
	}

	/**
	 * @param lob The lob to set.
	 */
	public void setLob(String lob) {
		this.lob = lob;
	}

	/**
	 * @return Returns the provSpecCode.
	 */
	public String getProvSpecCode() {
		return provSpecCode;
	}

	/**
	 * @param provSpecCode The provSpecCode to set.
	 */
	public void setProvSpecCode(String provSpecCode) {
		this.provSpecCode = provSpecCode;
	}
	/**
	 * @return Returns the marketBusinessUnit.
	 */
	public String getMarketBusinessUnit() {
		return marketBusinessUnit;
	}
	/**
	 * @param marketBusinessUnit The marketBusinessUnit to set.
	 */
	public void setMarketBusinessUnit(String marketBusinessUnit) {
		this.marketBusinessUnit = marketBusinessUnit;
	}
	/**
	 * @return Returns the mbu.
	 */
	public String getMbu() {
		if(null !=this.getSession().getAttribute("mbu"))
			mbu = this.getSession().getAttribute("mbu").toString();
		return mbu;
	}
	/**
	 * @param mbu The mbu to set.
	 */
	public void setMbu(String mbu) {
		this.mbu = mbu;
	}
	/**
	 * @return Returns the productCodeDesc.
	 */
	public String getProductCodeDesc() {
		return productCodeDesc;
	}

	/**
	 * @param productCodeDesc The productCodeDesc to set.
	 */
	public void setProductCodeDesc(String productCodeDesc) {
		this.productCodeDesc = productCodeDesc;
	}

	/**
	 * @return Returns the requiredProductCodeDesc.
	 */
	public boolean isRequiredProductCodeDesc() {
		return requiredProductCodeDesc;
	}

	/**
	 * @param requiredProductCodeDesc The requiredProductCodeDesc to set.
	 */
	public void setRequiredProductCodeDesc(boolean requiredProductCodeDesc) {
		this.requiredProductCodeDesc = requiredProductCodeDesc;
	}
	/**
	 * @return Returns the providerSpecialtyCodeAndDesc.
	 */
	public String getProviderSpecialtyCodeAndDesc() {
		return providerSpecialtyCodeAndDesc;
	}
	/**
	 * @param providerSpecialtyCodeAndDesc The providerSpecialtyCodeAndDesc to set.
	 */
	public void setProviderSpecialtyCodeAndDesc(
			String providerSpecialtyCodeAndDesc) {
		this.providerSpecialtyCodeAndDesc = providerSpecialtyCodeAndDesc;
	}

}