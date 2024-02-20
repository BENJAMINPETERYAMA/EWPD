/*
 * Created on Jul 30, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.benefittier.popup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.component.UIColumn;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.model.SelectItem;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLevel;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierCriteria;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierCriteriaPsblValue;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;
import com.wellpoint.wpd.common.tierdefinition.request.ContractTierDefSaveRequest;
import com.wellpoint.wpd.common.tierdefinition.request.ProductTierDefSaveRequest;
import com.wellpoint.wpd.common.tierdefinition.response.ContractTierDefSaveResponse;
import com.wellpoint.wpd.common.tierdefinition.response.ProductTierDefSaveResponse;
import com.wellpoint.wpd.common.tierdefinition.vo.BenefitTierDefinitionsVO;
import com.wellpoint.wpd.common.util.BenefitTierUtil;
import com.wellpoint.wpd.web.contract.ContractBackingBean;
import com.wellpoint.wpd.web.contract.ContractSession;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.product.ProductBackingBean;
import com.wellpoint.wpd.web.product.ProductSessionObject;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u20776
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class BenefitTierBackingBean extends WPDBackingBean {

	private HtmlPanelGrid panalGrid = null;

	private List tierDefsList;

	private List tierCriteriaList;

	private List tierCriteriaPsblValueList;

	private List validationMessages;

	private String mandatoryFieldsValid;

	private List tierDefList;

	private String selectedExtClientId;

	private String optionValue;

	private String psvlLookupRecords;

	private List pbvlDefList;

	private String tierPopupHidden;

	List benefitTierDefinitionsList;

	private int crtListSize;

	private int tierDefListSize;

	private String saveString;

	private String entitySysId;

	private String benefitComponentSysId;

	private String benefitDefinitionSysId;

	private String benefitDefinitionLevelId;

	private String selectedtierDefSysId;

	private String entityType;

	private String closePage;

	private String productIdFromContract;

	private boolean submitFlag = false;

	private boolean isRangeValid = true;

	private String dataType;

	public List benefitTierSysId;

	private static final String SESSION_PRODUCT_ID = "SESSION_PRODUCT_ID";

	private static final String SESSION_BD_ID = "SESSION_BD_ID";

	private static final String SESSION_BC_ID = "SESSION_BC_ID";

	private static final String SESSION_LVL_ID = "SESSION_LVL_ID";

	private static final String SESSION_ENTTYPE_ID = "SESSION_ENTTYPE_ID";

	private static final String SESSION_CONT_PROD_ID = "SESSION_CONT_PROD_ID";

	/**
	 * @return Returns the tierPopupHidden.
	 */
	public String getTierPopupHidden() {
		if (getRequest().getAttribute("closePage") != null
				&& getRequest().getAttribute("closePage").toString()
						.equalsIgnoreCase("true")) {
			removeValuesFromSession();
			return tierPopupHidden;
		}
		setRequestValues();
		if (entityType.equalsIgnoreCase("Product")) {
			ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
					.getAttribute(ProductBackingBean.PRODUCT_SESSION_KEY);
			benefitTierDefinitionsList = productSessionObject
					.getBenefitTierPsvlList();//this.getBenefitTierDefinitionsList();
		} else if (entityType.equalsIgnoreCase("Contract")) {
			ContractSession contractSession = (ContractSession) getSession()
					.getAttribute(ContractBackingBean.CONTRACT_SESSION_KEY);
			benefitTierDefinitionsList = contractSession
					.getBenefitTierPsvlList();
		}
		if (null != benefitTierDefinitionsList) {
			breakBenefitTierDefList();
			loadTierDefinitionPage();
		}
		return tierPopupHidden;
	}

	/**
	 * This will remove all session variable from session
	 *  
	 */

	private void removeValuesFromSession() {

		getSession().removeAttribute(SESSION_PRODUCT_ID);
		getSession().removeAttribute(SESSION_BC_ID);
		getSession().removeAttribute(SESSION_BD_ID);
		getSession().removeAttribute(SESSION_LVL_ID);
		getSession().removeAttribute(SESSION_ENTTYPE_ID);
		getSession().removeAttribute(SESSION_CONT_PROD_ID);
	}

	/**
	 * This will sets the request variables into session
	 *  
	 */
	private void setRequestValues() {

		if (getRequest().getParameter("entitySysId") != null
				&& getRequest().getParameter("benefitComponentSysId") != null
				&& getRequest().getParameter("benefitDefinitionSysId") != null) {
			entitySysId = ESAPI.encoder().encodeForHTML(getRequest().getParameter("entitySysId"));
			if(null!=entitySysId  && entitySysId.matches("[0-9a-zA-Z_]+")){
				entitySysId =entitySysId;
			}
			benefitComponentSysId = ESAPI.encoder().encodeForHTML(getRequest().getParameter(
					"benefitComponentSysId"));
			if(null!=benefitComponentSysId  && benefitComponentSysId.matches("[0-9a-zA-Z_]+")){
				benefitComponentSysId =benefitComponentSysId;
			}
			benefitDefinitionSysId = ESAPI.encoder().encodeForHTML(getRequest().getParameter(
					"benefitDefinitionSysId"));
			if(null!=benefitDefinitionSysId  && benefitDefinitionSysId.matches("[0-9a-zA-Z_]+")){
				benefitDefinitionSysId =benefitDefinitionSysId;
			}
			benefitDefinitionLevelId = (benefitDefinitionLevelId == null ? ESAPI.encoder().encodeForHTML(getRequest()
					.getParameter("benefitDefinitionLevelId"))
					: benefitDefinitionLevelId);
			if(StringUtil.regExPatterValidation(benefitDefinitionLevelId)){
				benefitDefinitionLevelId=benefitDefinitionLevelId;
   			}else{
   				benefitDefinitionLevelId=null;
   			}
			entityType = (entityType == null ? getRequest().getParameter(
					"entityType") : entityType);
			if (null != getRequest().getParameter("productSysId") && getRequest().getParameter("productSysId").matches("[0-9a-zA-Z_]+")) {
				productIdFromContract = (String) ESAPI.encoder().encodeForHTML(getRequest().getParameter(
						"productSysId"));
			}
			getSession().setAttribute(SESSION_PRODUCT_ID, entitySysId);
			getSession().setAttribute(SESSION_BC_ID, benefitComponentSysId);
			getSession().setAttribute(SESSION_BD_ID, benefitDefinitionSysId);
			getSession().setAttribute(SESSION_LVL_ID, benefitDefinitionLevelId);
			getSession().setAttribute(SESSION_ENTTYPE_ID, entityType);
			getSession().setAttribute(SESSION_CONT_PROD_ID,
					productIdFromContract);
		} else {
			entitySysId = (entitySysId == null ? (String) getSession()
					.getAttribute(SESSION_PRODUCT_ID) : entitySysId);
			benefitComponentSysId = (benefitComponentSysId == null ? (String) getSession()
					.getAttribute(SESSION_BC_ID)
					: benefitComponentSysId);
			benefitDefinitionSysId = (benefitDefinitionSysId == null ? (String) getSession()
					.getAttribute(SESSION_BD_ID)
					: benefitDefinitionSysId);
			benefitDefinitionLevelId = (benefitDefinitionLevelId == null ? (String) getSession()
					.getAttribute(SESSION_LVL_ID)
					: benefitDefinitionLevelId);
			entityType = (entityType == null ? (String) getSession()
					.getAttribute(SESSION_ENTTYPE_ID) : entityType);
			productIdFromContract = (productIdFromContract == null ? (String) getSession()
					.getAttribute(SESSION_CONT_PROD_ID)
					: productIdFromContract);
		}
	}

	/**
	 * @param tierPopupHidden
	 *            The tierPopupHidden to set.
	 */
	public void setTierPopupHidden(String tierPopupHidden) {
		this.tierPopupHidden = tierPopupHidden;
	}

	/**
	 * This method will break the BO into three different Lists. TireDefs List,
	 * Criteria List and Posible values List
	 *  
	 */
	private void breakBenefitTierDefList() {

		TierDefinitionBO tierDefBO = new TierDefinitionBO();

		int tierDefIdIndicator = -1;
		int tierCtrSysIdIndicator = -1;

		BenefitTierDefinitionsVO benTierDef;
		BenefitTierCriteria benefitTierCriteria;
		BenefitTierCriteriaPsblValue benefitTierCrtPsbl;

		tierDefsList = new ArrayList();
		tierCriteriaList = new ArrayList();
		tierCriteriaPsblValueList = new ArrayList();

		int listSize = benefitTierDefinitionsList.size();
		for (int lsize = 0; lsize < listSize; lsize++) {
			tierDefBO = (TierDefinitionBO) benefitTierDefinitionsList
					.get(lsize);

			if (tierDefIdIndicator != tierDefBO.getTierDefId()) {
				tierDefIdIndicator = tierDefBO.getTierDefId();
				benTierDef = new BenefitTierDefinitionsVO();

				benTierDef.setBenefitTierDefinitionSysId(tierDefBO
						.getTierDefId());
				benTierDef
						.setBenefitTierDefSysId("" + tierDefBO.getTierDefId());
				benTierDef.setDataType(tierDefBO.getDataType());
				benTierDef
						.setBenefitTierDefinitionName(tierDefBO.getTierDesc());

				tierDefsList.add(benTierDef);
			}
			if (tierCtrSysIdIndicator != tierDefBO.getTierCriteriaSysId()) {
				tierCtrSysIdIndicator = tierDefBO.getTierCriteriaSysId();
				benefitTierCriteria = new BenefitTierCriteria();

				benefitTierCriteria.setBenefitTierCriteriaSysId(tierDefBO
						.getTierCriteriaSysId());
				benefitTierCriteria.setBenefitTierCriteriaName(tierDefBO
						.getCriteriaName());
				benefitTierCriteria.setBenefitTier(new BenefitTier(tierDefBO
						.getTierDefId()));
				benefitTierCriteria.setBenefitTierCriteriaIndicator(Integer
						.parseInt(tierDefBO.getCriteriaIndicator()));

				benefitTierCriteria.setSequence(tierDefBO.getDispSeqNo());

				tierCriteriaList.add(benefitTierCriteria);
			}

			benefitTierCrtPsbl = new BenefitTierCriteriaPsblValue();

			benefitTierCrtPsbl.setBenefitTierCtrName(tierDefBO
					.getCriteriaName());
			benefitTierCrtPsbl.setBenefitTierCtrPsblValue(tierDefBO
					.getTierCrtPsvlValue());
			benefitTierCrtPsbl.setBenefitTierCtrPsblDesc(tierDefBO
					.getTierCrtPsvlValueDesc());

			tierCriteriaPsblValueList.add(benefitTierCrtPsbl);
		}
		//getSession().setAttribute(WebConstants.TIER_CRITERIA_PSBL_VALUE_LIST,tierCriteriaPsblValueList);
	}

	/**
	 * @return Returns the psvlLookupRecords.
	 */
	public String getPsvlLookupRecords() {

		String criString;
		if (getRequest().getParameter(WebConstants.CRITERIA_STRING) != null) {
			criString = getRequest().getParameter(WebConstants.CRITERIA_STRING)
					.toString();
			pbvlDefList = getPosibleValuesList(criString);
		}
		return psvlLookupRecords;
	}

	private List getPosibleValuesList(String crString) {
		List newList = new ArrayList();
		BenefitTierCriteriaPsblValue benefitTierCrtPsbl;
		List tierCrtPsblValueList = (List) getSession().getAttribute(
				WebConstants.TIER_CRITERIA_PSBL_VALUE_LIST);
		if (tierCrtPsblValueList != null) {
			for (int j = 0; j < tierCrtPsblValueList.size(); j++) {
				benefitTierCrtPsbl = (BenefitTierCriteriaPsblValue) tierCrtPsblValueList
						.get(j);
				if (crString.equalsIgnoreCase(benefitTierCrtPsbl
						.getBenefitTierCtrName())) {
					newList.add(benefitTierCrtPsbl);
				}
			}
		}
		return newList;
	}

	/**
	 * @param psvlLookupRecords
	 *            The psvlLookupRecords to set.
	 */
	public void setPsvlLookupRecords(String psvlLookupRecords) {
		this.psvlLookupRecords = psvlLookupRecords;
	}

	/**
	 * @return Returns the optionValue.
	 */
	public String getOptionValue() {
		return optionValue;
	}

	/**
	 * @param optionValue
	 *            The optionValue to set.
	 */
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public void loadTierDefinitionPage() {
		tierCriteriaList = setPossibleValues(tierCriteriaList,
				tierCriteriaPsblValueList);
		crtListSize = tierCriteriaList.size();
		tierDefListSize = tierDefsList.size();
		if (panalGrid != null) {
			panalGrid = null;
		}
		panalGrid = new HtmlPanelGrid();
		HtmlPanelGrid innerGrid = null;
		HtmlOutputText spaceChar = null;

		UIColumn col;
		panalGrid.setColumns(1);
		panalGrid.setWidth("100%");

		for (int j = 0; j < tierDefsList.size(); j++) {

			BenefitTierDefinitionsVO benTierDef;
			benTierDef = (BenefitTierDefinitionsVO) tierDefsList.get(j);

			innerGrid = new HtmlPanelGrid();
			innerGrid.setColumns(2);
			innerGrid.getAttributes().put("columnClasses",
					"formGridColumnRight,formGridColumnLeft");
			innerGrid.setBorder(1);
			innerGrid.getAttributes().put("rowClasses",
					"dataTableEvenRow,dataTableOddRow");
			innerGrid.setId("TD" + benTierDef.getBenefitTierDefSysId());
			innerGrid.setCellpadding("2");
			innerGrid.setCellspacing("0");
			innerGrid.setStyle("display: none;");
			innerGrid.setWidth("100%");
			innerGrid.setBorder(1);

			for (int i = 0; i < tierCriteriaList.size(); i++) {
				BenefitTierCriteria crBO;
				crBO = (BenefitTierCriteria) tierCriteriaList.get(i);
				if (benTierDef.getBenefitTierDefinitionSysId() == crBO
						.getBenefitTier().getBenefitTierSysId()) {

					col = new UIColumn();
					col.getAttributes().put("width", "30px;");
					HtmlOutputText crDesc = new HtmlOutputText();
					crDesc.setId("id1" + i + j);
					crDesc.setValue(crBO.getBenefitTierCriteriaName() + "*");
					col.getChildren().add(crDesc);
					innerGrid.getChildren().add(col);

					if (crBO.getBenefitTierCriteriaIndicator() == 2) {
						col = new UIColumn();
						HtmlInputText crText = new HtmlInputText();
						crText.setStyleClass("formInputField");
						crText.setId("TD"
								+ benTierDef.getBenefitTierDefinitionSysId()
								+ "_" + i);
						if (!benTierDef.getDataType().equalsIgnoreCase("date")) {

							if (benTierDef.getDataType().equalsIgnoreCase(
									"numeric")) {
								crText.setOnkeypress("return isNum();");
								crText.setTitle("numeric");
								crText.setMaxlength(3);
							}
							col.getChildren().add(crText);

						} else if (benTierDef.getDataType().equalsIgnoreCase(
								"date")) {
							crText.setReadonly(true);
							crText.setTitle("date");
							crText.setMaxlength(10);
							HtmlCommandButton cmdBtn = new HtmlCommandButton();
							HtmlOutputText dateFormat = new HtmlOutputText();
							dateFormat.setValue("(mm/dd)");
							dateFormat.setStyleClass("dateFormat");
							dateFormat.setStyle("width : 200px;");
							spaceChar = new HtmlOutputText();
							spaceChar.setStyle("width : 20px;");
							cmdBtn.setId("calBtn" + i + j);
							String fieldId = cmdBtn.getId();
							cmdBtn
									.setOnclick("changeId(this);cal1.select('tierPopup:"
											+ crText.getId()
											+ "','"
											+ fieldId
											+ "','MM/dd'); return false;");
							cmdBtn.setImage("../../images/cal.gif");
							cmdBtn.setStyle("cursor: hand; valign: middle");
							cmdBtn.setAlt("Calender");
							col.getChildren().add(crText);
							col.getChildren().add(spaceChar);
							col.getChildren().add(cmdBtn);
							col.getChildren().add(dateFormat);
						}
						innerGrid.getChildren().add(col);
					} else if (crBO.getBenefitTierCriteriaIndicator() == 1) {
						if (crBO.getNoOfPsblValues() <= 2) {
							BenefitTierCriteriaPsblValue benefitTierCrtPsbl;
							col = new UIColumn();

							HtmlSelectOneMenu crSelect = new HtmlSelectOneMenu();
							UISelectItems items = new UISelectItems();
							crSelect.setId("TD"
									+ benTierDef
											.getBenefitTierDefinitionSysId()
									+ "_" + i);
							List list = new ArrayList();

							list.add(new SelectItem("-1", "--Select--",
									"SelectItems tag", true));
							for (int k = 0; k < tierCriteriaPsblValueList
									.size(); k++) {
								benefitTierCrtPsbl = (BenefitTierCriteriaPsblValue) tierCriteriaPsblValueList
										.get(k);
								if (benefitTierCrtPsbl
										.getBenefitTierCtrName()
										.equalsIgnoreCase(
												crBO
														.getBenefitTierCriteriaName())) {
									list
											.add(new SelectItem(
													benefitTierCrtPsbl
															.getBenefitTierCtrPsblValue(),
													benefitTierCrtPsbl
															.getBenefitTierCtrPsblValue()));
								}
							}
							items.setValue(list);

							crSelect.getChildren().add(items);
							crSelect.setStyle("width:100px;");

							col.getChildren().add(crSelect);
							innerGrid.getChildren().add(col);
						} else if (crBO.getNoOfPsblValues() > 2) {

							col = new UIColumn();
							HtmlInputText eligibilText = new HtmlInputText();
							eligibilText.setId("TD"
									+ benTierDef
											.getBenefitTierDefinitionSysId()
									+ "_" + i);
							eligibilText.setStyleClass("selectDataDisplayDiv");
							eligibilText.setReadonly(true);

							HtmlCommandButton cmdBtn = new HtmlCommandButton();
							spaceChar = new HtmlOutputText();
							spaceChar.setStyle("width : 20px;");
							cmdBtn
									.setOnclick("ewpdModalWindow_ewpd('../popups/possibleValuesPopup.jsp'+getUrl()+'?criteriaStirng="
											+ crBO.getBenefitTierCriteriaName()
											+ "','tierPopup:"
											+ eligibilText.getId()
											+ "','tierPopup:"
											+ eligibilText.getId()
											+ "',2,2);return false;");
							cmdBtn.setImage("../../images/select.gif");
							cmdBtn.setStyle("cursor: hand; valign: middle");
							cmdBtn.setAlt("Possible Values");

							col.getChildren().add(eligibilText);
							col.getChildren().add(spaceChar);
							col.getChildren().add(cmdBtn);
							innerGrid.getChildren().add(col);
						}
					}

					panalGrid.getChildren().add(innerGrid);
				}
			}
		}
	}

	/**
	 * This will set the no of possible values to criteria list
	 * 
	 * @param crList
	 * @param psblList
	 * @return
	 */
	private List setPossibleValues(List crList, List psblList) {
		BenefitTierCriteria benefitCtr;
		List newCrList = new ArrayList();
		int noPsvlValues;
		for (int i = 0; i < crList.size(); i++) {
			benefitCtr = (BenefitTierCriteria) crList.get(i);
			if (benefitCtr.getBenefitTierCriteriaIndicator() == 1) {
				noPsvlValues = getNoofPsvl(benefitCtr
						.getBenefitTierCriteriaName(), psblList);
				benefitCtr.setNoOfPsblValues(noPsvlValues);
			}
			newCrList.add(benefitCtr);
		}
		return newCrList;
	}

	private int getNoofPsvl(String crName, List pvlList) {
		int result = 0;
		BenefitTierCriteriaPsblValue psvlVO;
		for (int j = 0; j < pvlList.size(); j++) {
			psvlVO = (BenefitTierCriteriaPsblValue) pvlList.get(j);
			if (psvlVO.getBenefitTierCtrName().equalsIgnoreCase(crName)) {
				result++;
			}
		}
		return result;
	}

	private boolean isMandatoryFieldsValid() {
		validationMessages = new ArrayList();
		if (!mandatoryFieldsValid.equalsIgnoreCase(WebConstants.TRUE)) {
			validationMessages.add(new ErrorMessage(
					WebConstants.MANDATORY_FIELDS_REQUIRED));
			return false;
		} else {
			List criteriaVals = getCriteriaValueList();
			if(dataType.equalsIgnoreCase("numeric")){
				for(int i=0;i<criteriaVals.size();i++){
					if(!WPDStringUtil.isNumber(criteriaVals.get(i).toString())){
						validationMessages.add(new ErrorMessage(
								WebConstants.INVALID_CRITERIA_VALUE));
						return false;
					}
				}
			}
			if (criteriaVals.size() >= 2) {
				return isRangeValid(criteriaVals);
			}
		}
		return true;
	}

	private boolean isRangeValid(List ctrValues) {
		validationMessages = new ArrayList();
		int result = 0;
		int result_wclc = 0;
		BenefitTierCriteria benefitTierCriteria;
		List benefitTierCriteriaList = new ArrayList();
		for (int i = 0; i < ctrValues.size(); i++) {
			benefitTierCriteria = new BenefitTierCriteria();
			benefitTierCriteria.setBenefitTierCriteriaValue(ctrValues.get(i)
					.toString());
			benefitTierCriteriaList.add(benefitTierCriteria);
		}
		result = BenefitTierUtil.compareCriteria(
				(BenefitTierCriteria) benefitTierCriteriaList.get(0),
				(BenefitTierCriteria) benefitTierCriteriaList.get(1), dataType);
		if(ctrValues.size() > 2){
			result_wclc = BenefitTierUtil.compareCriteria(
					(BenefitTierCriteria) benefitTierCriteriaList.get(2),
					(BenefitTierCriteria) benefitTierCriteriaList.get(3), dataType);
		}
		if (result > 0) {
			validationMessages.add(new ErrorMessage(
					WebConstants.RANGE_GREATER_VALIDATE));
			return false;
		}
		if (result_wclc > 0) {
			validationMessages.add(new ErrorMessage(
					WebConstants.RANGE_GREATER_VALIDATE));
			return false;
		}
		return true;
	}

	public String addTierDefinition() {

		boolean statusFlag;
		if (isMandatoryFieldsValid()) {
			entityType = (String) getSession().getAttribute(SESSION_ENTTYPE_ID);
			if (entityType != null && entityType.equalsIgnoreCase("Product")) {
				if (validateRangeOverlapping(entityType)) {
					//this.submitFlag = false;
					ProductTierDefSaveRequest request = (ProductTierDefSaveRequest) this
							.getServiceRequest(ServiceManager.PRODUCT_TIER_DEF_SAVE_REQUEST);
					request.setProductSysId(Integer.parseInt(entitySysId));
					request.setBenefitComponentSysId(Integer
							.parseInt(benefitComponentSysId));
					request.setBenefitDefinitionSysId(Integer
							.parseInt(benefitDefinitionSysId));
					request.setTierDefinitionId(Integer
							.parseInt(selectedtierDefSysId));
					request.setTierDefExits(isTierExisting(entityType));
					request.setBenefitCriteriaSaveString(saveString);
					request.setBenefitDefinitionLevelId(Integer
							.parseInt(benefitDefinitionLevelId));
					ProductTierDefSaveResponse response = (ProductTierDefSaveResponse) executeService(request);
					statusFlag = response.isStatusFlag();
					if (statusFlag) {
						closePage = "true";
						getRequest().setAttribute("closePage", "true");
					} else
						closePage = "exception";
				} else {
					if (!isRangeValid)
						validationMessages.add(new ErrorMessage(
								WebConstants.RANGE_GREATER_VALIDATE));
					addAllMessagesToRequest(validationMessages);
					closePage = "false";
				}
			} else if (entityType != null
					&& entityType.equalsIgnoreCase("Contract")) {
				if (validateRangeOverlapping(entityType)) {
					//this.submitFlag = false;
					ContractTierDefSaveRequest request = (ContractTierDefSaveRequest) this
							.getServiceRequest(ServiceManager.CONTRACT_TIER_DEF_SAVE_REQUEST);
					request.setDateSegmentId(Integer.parseInt(entitySysId));
					request.setBenefitComponentSysId(Integer
							.parseInt(benefitComponentSysId));
					request.setBenefitDefinitionSysId(Integer
							.parseInt(benefitDefinitionSysId));
					request.setTierDefinitionId(Integer
							.parseInt(selectedtierDefSysId));
					request.setTierDefExits(isTierExisting(entityType));
					request.setBenefitCriteriaSaveString(saveString);
					request.setBenefitDefinitionLevelId(Integer
							.parseInt(benefitDefinitionLevelId));
					ContractTierDefSaveResponse response = (ContractTierDefSaveResponse) executeService(request);
					statusFlag = response.isStatusFlag();
					if (statusFlag) {
						closePage = "true";
						getRequest().setAttribute("closePage", "true");
					} else
						closePage = "exception";
				} else {
					if (!isRangeValid)
						validationMessages.add(new ErrorMessage(
								WebConstants.RANGE_GREATER_VALIDATE));
					addAllMessagesToRequest(validationMessages);
					closePage = "false";
				}
			}
		} else {
			addAllMessagesToRequest(validationMessages);
			closePage = "false";
			return "fail";
		}
		addAllMessagesToRequest(validationMessages);
		return "success";
	}

	private String isTierExisting(String entityType) {
		List benefitTierDefList = null;
		if (entityType.equalsIgnoreCase("Product")) {
			ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
					.getAttribute(ProductBackingBean.PRODUCT_SESSION_KEY);
			benefitTierDefList = productSessionObject
					.getBenefitTierDefinitionList();
		} else if (entityType.equalsIgnoreCase("Contract")) {
			ContractSession contractSessionObject = (ContractSession) getSession()
					.getAttribute(ContractBackingBean.CONTRACT_SESSION_KEY);
			benefitTierDefList = contractSessionObject
					.getBenefitTierDefinitionList();
		}
		if (benefitTierDefList == null || benefitTierDefList.size() == 0)
			return "F";
		for (Iterator iter = benefitTierDefList.iterator(); iter.hasNext();) {
			BenefitTierDefinition tierDefinition = (BenefitTierDefinition) iter
					.next();
			if (tierDefinition.getBenefitTierDefinitionSysId() == Integer
					.parseInt(selectedtierDefSysId)) {
				List criteriaVals = getCriteriaValueList();
				if (criteriaVals != null && criteriaVals.size() <= 2) {
					if (!tierDefinition.isTierExists((String) criteriaVals
							.get(0), (String) criteriaVals.get(criteriaVals
							.size() - 1))) {
						return "F";
					} else {
						return "T";
					}
				}
				else if(criteriaVals != null && criteriaVals.size() > 2) {
					if(!tierDefinition.isTierExists_wclc((String) criteriaVals
							.get(0), (String) criteriaVals.get(1), (String) criteriaVals.get(2), (String) criteriaVals.get(criteriaVals
							.size() - 1))){
						return "F";
					}
					else return "T";
				}
			}
		}
		return "F";
	}

	private List getCriteriaValueList() {
		List criteriaVals = new ArrayList();
		StringTokenizer stringTokenizer = new StringTokenizer(saveString, "~");
		while (stringTokenizer.hasMoreElements()) {
			criteriaVals.add(stringTokenizer.nextElement());
		}
		return criteriaVals;
	}

	/**
	 * @return Returns the tierDefList.
	 */
	public List getTierDefList() {
		return tierDefList;
	}

	/**
	 * @param tierDefList
	 *            The tierDefList to set.
	 */
	public void setTierDefList(List tierDefList) {
		this.tierDefList = tierDefList;
	}

	/**
	 * @return Returns the selectedExtClientId.
	 */
	public String getSelectedExtClientId() {
		return selectedExtClientId;
	}

	/**
	 * @param selectedExtClientId
	 *            The selectedExtClientId to set.
	 */
	public void setSelectedExtClientId(String selectedExtClientId) {
		this.selectedExtClientId = selectedExtClientId;
	}

	/**
	 * @return Returns the panalGrid.
	 */
	public HtmlPanelGrid getPanalGrid() {
		return panalGrid;
	}

	/**
	 * @param panalGrid
	 *            The panalGrid to set.
	 */
	public void setPanalGrid(HtmlPanelGrid panalGrid) {
		this.panalGrid = panalGrid;
	}

	/**
	 * @return Returns the pbvlDefList.
	 */
	public List getPbvlDefList() {
		return pbvlDefList;
	}

	/**
	 * @param pbvlDefList
	 *            The pbvlDefList to set.
	 */
	public void setPbvlDefList(List pbvlDefList) {
		this.pbvlDefList = pbvlDefList;
	}

	/**
	 * @param benefitTierDefinitionsList
	 *            The benefitTierDefinitionsList to set.
	 */
	public void setBenefitTierDefinitionsList(List benefitTierDefinitionsList) {
		this.benefitTierDefinitionsList = benefitTierDefinitionsList;
	}

	/**
	 * @return Returns the crtListSize.
	 */
	public int getCrtListSize() {
		return crtListSize;
	}

	/**
	 * @param crtListSize
	 *            The crtListSize to set.
	 */
	public void setCrtListSize(int crtListSize) {
		this.crtListSize = crtListSize;
	}

	/**
	 * @return Returns the saveString.
	 */
	public String getSaveString() {
		return saveString;
	}

	/**
	 * @param saveString
	 *            The saveString to set.
	 */
	public void setSaveString(String saveString) {
		this.saveString = saveString;
	}

	/**
	 * @return Returns the tierCriteriaList.
	 */
	public List getTierCriteriaList() {
		return tierCriteriaList;
	}

	/**
	 * @param tierCriteriaList
	 *            The tierCriteriaList to set.
	 */
	public void setTierCriteriaList(List tierCriteriaList) {
		this.tierCriteriaList = tierCriteriaList;
	}

	/**
	 * @return Returns the tierDefsList.
	 */
	public List getTierDefsList() {
		return tierDefsList;
	}

	/**
	 * @param tierDefsList
	 *            The tierDefsList to set.
	 */
	public void setTierDefsList(List tierDefsList) {
		this.tierDefsList = tierDefsList;
	}

	/**
	 * @return Returns the tierCriteriaPsblValueList.
	 */
	public List getTierCriteriaPsblValueList() {
		return tierCriteriaPsblValueList;
	}

	/**
	 * @param tierCriteriaPsblValueList
	 *            The tierCriteriaPsblValueList to set.
	 */
	public void setTierCriteriaPsblValueList(List tierCriteriaPsblValueList) {
		this.tierCriteriaPsblValueList = tierCriteriaPsblValueList;
	}

	/**
	 * @return Returns the benefitComponentSysId.
	 */
	public String getBenefitComponentSysId() {
		return benefitComponentSysId;
	}

	/**
	 * @param benefitComponentSysId
	 *            The benefitComponentSysId to set.
	 */
	public void setBenefitComponentSysId(String benefitComponentSysId) {
		this.benefitComponentSysId = benefitComponentSysId;
	}

	/**
	 * @return Returns the benefitDefinitionLevelId.
	 */
	public String getBenefitDefinitionLevelId() {
		return benefitDefinitionLevelId;
	}

	/**
	 * @param benefitDefinitionLevelId
	 *            The benefitDefinitionLevelId to set.
	 */
	public void setBenefitDefinitionLevelId(String benefitDefinitionLevelId) {
		this.benefitDefinitionLevelId = benefitDefinitionLevelId;
	}

	/**
	 * @return Returns the benefitDefinitionSysId.
	 */
	public String getBenefitDefinitionSysId() {
		return benefitDefinitionSysId;
	}

	/**
	 * @param benefitDefinitionSysId
	 *            The benefitDefinitionSysId to set.
	 */
	public void setBenefitDefinitionSysId(String benefitDefinitionSysId) {
		this.benefitDefinitionSysId = benefitDefinitionSysId;
	}

	/**
	 * @return Returns the entitySysId.
	 */
	public String getEntitySysId() {
		return entitySysId;
	}

	/**
	 * @param entitySysId
	 *            The entitySysId to set.
	 */
	public void setEntitySysId(String entitySysId) {
		this.entitySysId = entitySysId;
	}

	/**
	 * @return Returns the selectedtierDefSysId.
	 */
	public String getSelectedtierDefSysId() {
		return selectedtierDefSysId;
	}

	/**
	 * @param selectedtierDefSysId
	 *            The selectedtierDefSysId to set.
	 */
	public void setSelectedtierDefSysId(String selectedtierDefSysId) {
		this.selectedtierDefSysId = selectedtierDefSysId;
	}

	/**
	 * @return Returns the validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * @param validationMessages
	 *            The validationMessages to set.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * @param entityType
	 *            The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getClosePage() {
		return closePage;
	}

	public void setClosePage(String closePage) {
		this.closePage = closePage;
	}

	/**
	 * @return Returns the mandatoryFieldsValid.
	 */
	public String getMandatoryFieldsValid() {
		return mandatoryFieldsValid;
	}

	/**
	 * @param mandatoryFieldsValid
	 *            The mandatoryFieldsValid to set.
	 */
	public void setMandatoryFieldsValid(String mandatoryFieldsValid) {
		this.mandatoryFieldsValid = mandatoryFieldsValid;
	}

	/**
	 * @return Returns the tierDefListSize.
	 */
	public int getTierDefListSize() {
		return tierDefListSize;
	}

	/**
	 * @param tierDefListSize
	 *            The tierDefListSize to set.
	 */
	public void setTierDefListSize(int tierDefListSize) {
		this.tierDefListSize = tierDefListSize;
	}

	/**
	 * The method will compare the newly added tier ranges with the existing
	 * ones If the range values are overlapping,this will return false
	 * 
	 * @return
	 */
	private boolean validateRangeOverlapping(String entityType) {
		boolean validationSucess = true;
		int startValue = 0;
		int endValue = 0;
		boolean isLevelExistsFlag;
		//String dataType;
		List criteriaVals = getCriteriaValueList();
		List benefitTierDefList = new ArrayList();
		List benefitTierLevelList = new ArrayList();
		validationMessages = new ArrayList();

		if (entityType.equalsIgnoreCase("Product")) {
			ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
					.getAttribute(ProductBackingBean.PRODUCT_SESSION_KEY);
			benefitTierDefList = productSessionObject
					.getBenefitTierDefinitionList();
			benefitTierLevelList = productSessionObject
					.getBenefitTierLevelList();

		} else if (entityType.equalsIgnoreCase("Contract")) {
			ContractSession contractSession = (ContractSession) getSession()
					.getAttribute(ContractBackingBean.CONTRACT_SESSION_KEY);
			benefitTierDefList = contractSession.getBenefitTierDefinitionList();
			benefitTierLevelList = contractSession.getBenefitTierLevelList();
		}
		//if (benefitTierDefList == null)return true; // Temporary Fix. Analyse
		// and change the cause for null.
		for (Iterator iter = benefitTierDefList.iterator(); iter.hasNext();) {
			BenefitTierDefinition tierDefinition = (BenefitTierDefinition) iter
					.next();
			if (tierDefinition.getBenefitTierDefinitionSysId() == Integer
					.parseInt(selectedtierDefSysId)) {
				BenefitTierDefinition clonedBenefitTierDefinition = (BenefitTierDefinition) tierDefinition
						.clone();
				clonedBenefitTierDefinition.setBenefitTierDefinitionSysId(0);
				List benefitTiersListFromClone = clonedBenefitTierDefinition
						.getBenefitTiers();
				if (null != benefitTiersListFromClone
						&& benefitTiersListFromClone.size() > 0) {
					int tierListSize = benefitTiersListFromClone.size();
					String overLappingDetails = "";
					isLevelExistsFlag = isLevelExistsWithSameTierCrt(benefitTierLevelList);
					//** If criteria type is not a range
					if (criteriaVals.size() < 2) {
						if (isLevelExistsFlag) {
							for (int j = 0; j < tierListSize; j++) {
								BenefitTier benefitTier = (BenefitTier) benefitTiersListFromClone
										.get(j);
								List tierCrtList = benefitTier
										.getBenefitTierCriteriaList();
								BenefitTierCriteria benefitTierCriteria = (BenefitTierCriteria) tierCrtList
										.get(0);
								if (benefitTierCriteria
										.getBenefitTierCriteriaValue()
										.equalsIgnoreCase(
												(String) criteriaVals.get(0))) {
									overLappingDetails = " [ "
											+ overLappingDetails;
									overLappingDetails += benefitTierCriteria
											.getBenefitTierCriteriaName()
											+ ":"
											+ (String) criteriaVals.get(0)
											+ " ";
									overLappingDetails += " ] ";
									ErrorMessage errorMessage = new ErrorMessage(
											BusinessConstants.OVERLAPPING_VALUES_IN_TIER);
									errorMessage
											.setParameters(new String[] { tierDefinition
													.getBenefitTierDefinitionName()
													+ ":" + overLappingDetails });
									validationMessages.add(errorMessage);
									return false;
								}
							}
						}
					} else {
						if (isLevelExistsFlag
								&& isDuplicateWithSameCrtValues(
										benefitTiersListFromClone, criteriaVals)) {
							ErrorMessage errorMessage = new ErrorMessage(
									BusinessConstants.DUPLICATE_LEVEL_WITH_SAME_CRTVALUES);
							errorMessage
									.setParameters(new String[] { tierDefinition
											.getBenefitTierDefinitionName()
											+ ":" });
							validationMessages.add(errorMessage);
							return false;
						}
						BenefitTier benefitTierToClone = (BenefitTier) benefitTiersListFromClone
								.get(0);
						BenefitTier benefitTierFromClone = (BenefitTier) benefitTierToClone
								.clone();
						benefitTierFromClone.setBenefitTierSysId(0);
						benefitTierFromClone
								.setBenefitTierDefinition(clonedBenefitTierDefinition);
						List tierCriteriaList = benefitTierFromClone
								.getBenefitTierCriteriaList();
						List newListOfClonedCriterias = new ArrayList();
						for (int i = 0; i < criteriaVals.size(); i++) {
							BenefitTierCriteria benefitTierCriteria = (BenefitTierCriteria) tierCriteriaList
									.get(i);
							BenefitTierCriteria benefitTierCriteriaClone = (BenefitTierCriteria) benefitTierCriteria
									.clone();
							benefitTierCriteriaClone
									.setBenefitTierCriteriaSysId(0);
							benefitTierCriteriaClone
									.setBenefitTier(benefitTierFromClone);
							benefitTierCriteriaClone
									.setBenefitTierCriteriaValue((String) criteriaVals
											.get(i));
							newListOfClonedCriterias
									.add(benefitTierCriteriaClone);
						}
						benefitTierFromClone
								.setBenefitTierCriteriaList(newListOfClonedCriterias);
						benefitTiersListFromClone.add(benefitTierFromClone);
						List overLappingList = clonedBenefitTierDefinition.validateRangeOverlapping(true);
						
						//Adding overlapping error messages if any
						if (overLappingList.size() > 0) {
							Iterator overLapIterator = overLappingList
									.iterator();
							
							boolean firstCriteria = true;
							while (overLapIterator.hasNext()) {
								BenefitTier overLappingTier = (BenefitTier) overLapIterator
										.next();
								if (overLappingDetails != "")
									overLappingDetails += " / ";
								if(firstCriteria)
									overLappingDetails = " [ ";
								overLappingDetails += overLappingTier
										.getStartCriteria()
										.getBenefitTierCriteriaName()
										+ ":"
										+ overLappingTier.getStartCriteria()
												.getBenefitTierCriteriaValue()
										+ " ";
								
								overLappingDetails += overLappingTier
										.getEndCriteria()
										.getBenefitTierCriteriaName()
										+ ":"
										+ overLappingTier.getEndCriteria()
												.getBenefitTierCriteriaValue()
										+ " ";
								if(overLappingTier.getBenefitTierCriteriaList().size() > 2){
									overLappingDetails += 
										overLappingTier
										.getStartCriteria_wclc()
										.getBenefitTierCriteriaName()
										+ ":"
									+ overLappingTier.getStartCriteria_wclc()
											.getBenefitTierCriteriaValue()
									+ " ";
							
							overLappingDetails += overLappingTier
									.getEndCriteria_wclc()
									.getBenefitTierCriteriaName()
									+ ":"
									+ overLappingTier.getEndCriteria_wclc()
											.getBenefitTierCriteriaValue()
									+ " ";
								}
								firstCriteria = false;
							}
							overLappingDetails += " ] ";
							ErrorMessage errorMessage = new ErrorMessage(
									BusinessConstants.OVERLAPPING_VALUES_IN_TIER);
							errorMessage
									.setParameters(new String[] { tierDefinition
											.getBenefitTierDefinitionName()
											+ ":" + overLappingDetails });

							validationMessages.add(errorMessage);
							return false;
						}
					}
				}
			}
		}
		return validationSucess;
	}

	/**
	 * This method will check whether entered TierCriteria is already exists or
	 * not for the same level i.e Same TierCriteria should not repete for same
	 * level
	 * 
	 * @return
	 */
	private boolean isLevelExistsWithSameTierCrt(List benefitTierLevelList) {
		benefitTierSysId = new ArrayList();
		for (Iterator iter = benefitTierLevelList.iterator(); iter.hasNext();) {
			BenefitLevel benefitLevel = (BenefitLevel) iter.next();
			if (benefitLevel.getTierDefSysId() == Integer
					.parseInt(selectedtierDefSysId)
					&& benefitLevel.getLevelId() == Integer
							.parseInt(benefitDefinitionLevelId)) {
				benefitTierSysId.add("" + benefitLevel.getTierSysId());
			}
		}
		if (benefitTierSysId != null && benefitTierSysId.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * This method will check whether the same level having the same given
	 * criteria.
	 * 
	 * @param benefitTiersList
	 * @param criteriaVals
	 * @return
	 */
	private boolean isDuplicateWithSameCrtValues(List benefitTiersList,
			List criteriaVals) {
		for (Iterator iter = benefitTiersList.iterator(); iter.hasNext();) {
			BenefitTier benefittier = (BenefitTier) iter.next();
			for (int i = 0; i < benefitTierSysId.size(); i++) {
				int tierSysId = Integer.parseInt(benefitTierSysId.get(i)
						.toString());
				if (benefittier.getBenefitTierSysId() == tierSysId) {
					List tierCriteriaList = benefittier
							.getBenefitTierCriteriaList();
					BenefitTierCriteria startTierCriteria = (BenefitTierCriteria) tierCriteriaList
							.get(0);
					BenefitTierCriteria endTierCriteria = (BenefitTierCriteria) tierCriteriaList
							.get(1);
					BenefitTierCriteria startTierCriteria_wclc = null;
					BenefitTierCriteria endTierCriteria_wclc = null;
					if(tierCriteriaList.size() == 4){
						startTierCriteria_wclc = (BenefitTierCriteria) tierCriteriaList
						.get(2);
						endTierCriteria_wclc = (BenefitTierCriteria) tierCriteriaList
						.get(3);
					}
					if (startTierCriteria_wclc == null && endTierCriteria_wclc == null && startTierCriteria.getBenefitTierCriteriaValue()
							.equalsIgnoreCase((String) criteriaVals.get(0))
							&& endTierCriteria.getBenefitTierCriteriaValue()
									.equalsIgnoreCase(
											(String) criteriaVals.get(1))) {
						return true;
					}
					else if(startTierCriteria_wclc != null && endTierCriteria_wclc != null && startTierCriteria.getBenefitTierCriteriaValue()
							.equalsIgnoreCase((String) criteriaVals.get(0))
							&& endTierCriteria.getBenefitTierCriteriaValue()
									.equalsIgnoreCase(
											(String) criteriaVals.get(1)) && startTierCriteria_wclc.getBenefitTierCriteriaValue()
											.equalsIgnoreCase((String) criteriaVals.get(2)) && endTierCriteria_wclc.getBenefitTierCriteriaValue()
											.equalsIgnoreCase((String) criteriaVals.get(3))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @return Returns the submitFlag.
	 */
	public boolean isSubmitFlag() {
		return submitFlag;
	}

	/**
	 * @param submitFlag
	 *            The submitFlag to set.
	 */
	public void setSubmitFlag(boolean submitFlag) {
		this.submitFlag = submitFlag;
	}

	/**
	 * This method is written to cover a possible bug with panel renderer. The
	 * panelGrid is not gettting rendered on page refresh. Solution is to bind
	 * this method to faces context (faces-config.xml)and call this mehod on
	 * click of tier defenitions(Radio group input)
	 * 
	 * @return
	 */
	public String showTierCriteriaSection() {
		//benefitTierDefinitionsList = this.getBenefitTierDefinitionsList();
		//if (null != benefitTierDefinitionsList) {
		//breakBenefitTierDefList();
		//loadTierDefinitionPage();
		//}
		loadTierDefinitionPage();
		this.submitFlag = true;
		return "loadTierCriteriaPanel";
	}

	/**
	 * @return Returns the dataType.
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType
	 *            The dataType to set.
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}