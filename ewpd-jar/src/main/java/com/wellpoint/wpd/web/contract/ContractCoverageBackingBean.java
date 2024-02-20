/*
 * ContractCoverageBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;

import com.wellpoint.wpd.business.datatype.locatecriteria.DataTypeLocateResult;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.contract.request.RetrieveContractBenefitDefinitionRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBenefitDefinitionRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveContractBenefitDefinitionResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBenefitDefinitionResponse;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.messages.Message;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLevel;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierCriteria;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierCriteriaPsblValue;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.override.benefit.bo.EntityBenefitDefenition;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;
import com.wellpoint.wpd.common.override.benefit.vo.BenefitLineVO;
import com.wellpoint.wpd.common.productstructure.vo.BenefitDefinitionPrintVO;
import com.wellpoint.wpd.common.tierdefinition.request.ContractTierDeleteRequest;
import com.wellpoint.wpd.common.tierdefinition.request.TierDefinitionRetrieveRequest;
import com.wellpoint.wpd.common.tierdefinition.response.ContractTierDeleteResponse;
import com.wellpoint.wpd.common.tierdefinition.response.TierDefinitionRetrieveResponse;
import com.wellpoint.wpd.common.util.BenefitTierUtil;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractCoverageBackingBean extends ContractBackingBean {
	private HtmlPanelGrid headerPanel = new HtmlPanelGrid();

	private HtmlPanelGrid panel = new HtmlPanelGrid();
	
	private HtmlPanelGrid tierPanel = null;
	
	private HtmlPanelGrid tierHeaderPanel = null;
			
	private HtmlPanelGrid panelForTierPrint = new HtmlPanelGrid();	

	private Map benefitValueMap = new LinkedHashMap();
	
	private Map tierCriteriaMap = new HashMap();
	
	private Map tierLineValueMap = new HashMap();

	private EntityBenefitDefenition benefitDefinition = null;

	List benefitLevelList;

	List benefitLineList;

	List benefitDefinitionsList;
	boolean benefitDefinitionsListRetrieved = false;

	List deleteLevelList;

	private String printValue;

	private String dummyVar;

	private String forPrint;

	private boolean mandate = true;

	private HtmlPanelGrid headerPanelForPrint = new HtmlPanelGrid();

	private HtmlPanelGrid panelForPrint = new HtmlPanelGrid();

	private HashMap lineIdMap = new HashMap();

	private HashMap levelIdsMap = new HashMap();
	
	private boolean printMode = false;

	//added

	private int lineSysId;

	private List printBenftDefnList;

	private List validationMessages = new ArrayList();

	private String benefitTypeTab;

	//private Map levelIdMap = new LinkedHashMap();

	private String levelsToDelete;
	
	private List tierDefinitionList = null;
	
	private List lvlLineList = null;
	
	private int tierIdToDelete;
	
	private List modifiedTierLineIdList;
	
	private List modifiedTierCriteriaList;
	
	private String informationMsg;
	
    private boolean isLineValueModified = false;
    
    private List benefitTierDefinitionsList = null;
    
    private String psvlLookupRecords;
	
	private List pbvlDefList;
	
	private String benefitLevelForPrint;
	
	/*START CARS */
	private HashMap dataHiddenValTermTier = new HashMap();
    
	private HashMap dataHiddenValQualifierTier = new HashMap();	
	
	private HashMap lineFreqValueMapTier = new HashMap();
	
	private HashMap hiddenLineFreqValueMapTier = new HashMap();
	
	private HashMap dataHiddenValDescTier = new HashMap();
	
	private HashMap oldDescOutputTxtTier = new HashMap();

	private String tieredTruncatedLvlDesc = null;
	
	boolean isTieredLvlDescTruncated = false;
	
	private List modifiedLevelIdList;
	
	private List changedTierSysIds = new ArrayList();
	
	private List changedTierLineIds = new ArrayList();
	
	private HashMap tierLineIdMap = new HashMap();
	
	private HashMap tierLevelIdsMap = new HashMap();	
	/* END CARS */
	
	List changedTierLevelLineList=new ArrayList();
	
	Map tierSysIdLineIdMap=new HashMap();

	private HtmlPanelGrid tierHeaderPanelForPrint;
	
	private Map oldCodedLines = new LinkedHashMap();
	private Map modifiedCodedLines = new LinkedHashMap();
	/*private List linesToBeModified = new ArrayList();
	private List linesToBeInserted  = new ArrayList();*/
	
	private boolean submitFlag = false;	
	
	private List informationMessageToDisplayOnPage = new ArrayList();
	/**
	 * @return Returns the submitFlag.
	 */
	public boolean isSubmitFlag() {
		return submitFlag;
	}
	/**
	 * @param submitFlag The submitFlag to set.
	 */
	public void setSubmitFlag(boolean submitFlag) {
		this.submitFlag = submitFlag;
	}

	/*START CARS */
	private Map dataHiddenValQualifier = new HashMap();
	
	private Map lineFreqValueMap = new HashMap();
	//added this field to increase the perfomance
	private HashMap hiddenLineFreqValueMap = new HashMap();
	
	private Map dataHiddenValTerm = new HashMap();
	//Hash Map for level description
	private Map dataHiddenValDesc = new HashMap();
	
	private Map levelLineIdMap = new LinkedHashMap();
	
	boolean isFrequencyChanged = false;
	
	private List messagesList;
	
	private HashMap hiddenLowerLevelFreqValueMap = new HashMap();
	
	private HashMap dataHiddenLowerLevelValDesc = new HashMap();  
	
	//private HashMap dataHiddenOutputValDesc = new HashMap();
	
	private HashMap dataTierHiddenLowerLevelValDesc = new HashMap();  
	
	private HashMap hiddenTierLowerLevelFreqValueMap = new HashMap();
	
	private boolean isErrorNonTierFlag = true;
	
	private boolean isErrorTierFlag = true;
	/*END CARS */
	public ContractCoverageBackingBean() {
		//checks for view mode or edit mode to set the bread crumb text
		setBreadCrumbText();
	}

	/**
	 * @return Returns the pbvlDefList.
	 */
	public List getPbvlDefList() {
		return pbvlDefList;
	}
	/**
	 * @param pbvlDefList The pbvlDefList to set.
	 */
	public void setPbvlDefList(List pbvlDefList) {
		this.pbvlDefList = pbvlDefList;
	}
	/**
	 * @param psvlLookupRecords The psvlLookupRecords to set.
	 */
	public void setPsvlLookupRecords(String psvlLookupRecords) {
		this.psvlLookupRecords = psvlLookupRecords;
	}
	/**
	 * Returns the mandate
	 * @return boolean mandate.
	 */
	public boolean isMandate() {
		return mandate;
	}

	/**
	 * Sets the mandate
	 * @param mandate.
	 */
	public void setMandate(boolean mandate) {
		this.mandate = mandate;
	}

	/**
	 * Returns the forPrint
	 * @return String forPrint.
	 */
	public String getForPrint() {
		return forPrint;
	}

	/**
	 * Sets the forPrint
	 * @param forPrint.
	 */
	public void setForPrint(String forPrint) {
		this.forPrint = forPrint;
	}

	/**
	 * Returns the lineSysId
	 * @return int lineSysId.
	 */
	public int getLineSysId() {
		return lineSysId;
	}

	/**
	 * Sets the lineSysId
	 * @param lineSysId.
	 */
	public void setLineSysId(int lineSysId) {
		this.lineSysId = lineSysId;
	}

	/**
	 * This method returns the value which is required for Benefit Definitions page print
	 * @return Returns the printValue.
	 */
	public String getPrintValue() {

		Logger.logInfo("entered method getPrintValue");

		String requestForPrint = (String) getRequest().getAttribute(
				"printValueForBenDet");
		if (null != requestForPrint && !requestForPrint.equals("")) {
			printValue = requestForPrint;
		} else {
			printValue = "";
		}
		return printValue;
	}

	/**
	 * @param printValue The printValue to set.
	 */
	public void setPrintValue(String printValue) {
		this.printValue = printValue;
	}

	/**
	 * @return Returns the deleteLevelList.
	 */
	public List getDeleteLevelList() {
		return deleteLevelList;
	}

	/**
	 * @param deleteLevelList The deleteLevelList to set.
	 */
	public void setDeleteLevelList(List deleteLevelList) {
		this.deleteLevelList = deleteLevelList;
	}

	/**
	 * @return Returns the levelIdMap.
	 */
	/*public Map getLevelIdMap() {
		return levelIdMap;
	}

	*//**
	 * @param levelIdMap The levelIdMap to set.
	 *//*
	public void setLevelIdMap(Map levelIdMap) {
		this.levelIdMap = levelIdMap;
	}*/

	/**
	 * @return Returns the levelsToDelete.
	 */
	public String getLevelsToDelete() {
		return levelsToDelete;
	}

	/**
	 * @param levelsToDelete The levelsToDelete to set.
	 */
	public void setLevelsToDelete(String levelsToDelete) {
		this.levelsToDelete = levelsToDelete;
	}

	/** This method fetches the benefitDefinition List which contains levels and its corresponding lines
	 * Returns the benefitDefinitiosList
	 * @return List benefitDefinitiosList.
	 */
	public List getBenefitDefinitionsList() {
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","ContractCoverageBackingBean","getBenefitDefinitionsList()"));
		
		
		RetrieveContractBenefitDefinitionRequest retrieveContractBenefitDefinitionRequest = (RetrieveContractBenefitDefinitionRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_CONTRACT_BENEFIT_DEFINITION_REQUEST);

		retrieveContractBenefitDefinitionRequest
				.setBenefitComponentId(getBenefitComponentIdFromSession());
		retrieveContractBenefitDefinitionRequest
				.setBenefitId(getContractSession().getBenefitId());

		// retrieveContractBenefitDefinitionRequest.setContractKeyObject(getContractSession().getContractKeyObject());

		RetrieveContractBenefitDefinitionResponse retrieveContractBenefitDefinitionResponse = null;
		retrieveContractBenefitDefinitionResponse = (RetrieveContractBenefitDefinitionResponse) executeService(retrieveContractBenefitDefinitionRequest);

		List benefitDefinitions = new ArrayList();
		
		if (null != retrieveContractBenefitDefinitionResponse){
			benefitDefinitions = retrieveContractBenefitDefinitionResponse
			.getBenefitDefinitionsList();
			/*-- If its edit mode then only store the value in session -- */
			if(isEditMode()&& null != benefitDefinitions){
				List codeLines = null;
				codeLines = retriveCodedLinesFromList(benefitDefinitions);
				getSession().setAttribute("oldBenefitLineList",codeLines);
			}
		}

		List criteriaListFrmDB = retrieveContractBenefitDefinitionResponse
				.getTierCriteriaList();
		List benefitLvlLineList = retrieveContractBenefitDefinitionResponse
				.getBenefitLvlLineList();

		if (null != criteriaListFrmDB && !criteriaListFrmDB.isEmpty()) {
			tierDefinitionList = BenefitTierUtil.getTieredList(criteriaListFrmDB);
		}else {
			tierDefinitionList = new ArrayList();
		}

		if (null != benefitLvlLineList && !benefitLvlLineList.isEmpty()) {
			lvlLineList = BenefitTierUtil.getLvlLineList(benefitLvlLineList);
		} else {
			lvlLineList = new ArrayList();
		}
		
		super.setTierLevalListToSession(lvlLineList);
		super.setTierDefinitionListToSession(tierDefinitionList);
		
		Logger.logInfo(th.endPerfLogging());
		return benefitDefinitions;
	}

	/**
	 * Sets the benefitDefinitionsList
	 * @param benefitDefinitionsList.
	 */
	public void setBenefitDefinitionsList(List benefitDefinitionsList) {
		this.benefitDefinitionsList = benefitDefinitionsList;
	}

	/**
	 * Returns the benefitDefinition
	 * @return EntityBenefitDefenition benefitDefinition.
	 */

	public EntityBenefitDefenition getBenefitDefinition() {
		return benefitDefinition;
	}

	/**
	 * Sets the benefitDefinition
	 * @param benefitDefinition.
	 */
	public void setBenefitDefinition(EntityBenefitDefenition benefitDefinition) {
		this.benefitDefinition = benefitDefinition;
	}

	/**
	 * Returns the benefitLevelList
	 * @return List benefitLevelList.
	 */
	public List getBenefitLevelList() {
		return benefitLevelList;
	}

	/**
	 * Sets the benefitLevelList
	 * @param benefitLevelList.
	 */
	public void setBenefitLevelList(List benefitLevelList) {
		this.benefitLevelList = benefitLevelList;
	}

	/**
	 * Returns the benefitLineList
	 * @return List benefitLineList.
	 */
	public List getBenefitLineList() {
		return benefitLineList;
	}

	/**
	 * Sets the benefitLineList
	 * @param benefitLineList.
	 */
	public void setBenefitLineList(List benefitLineList) {
		this.benefitLineList = benefitLineList;
	}

	/**
	 * @param benefitTierDefinitionsList The benefitTierDefinitionsList to set.
	 */
	public void setBenefitTierDefinitionsList(List benefitTierDefinitionsList) {
		this.benefitTierDefinitionsList = benefitTierDefinitionsList;
	}
	/**
	 * Returns the headerPanel
	 * @return HtmlPanelGrid headerPanel.
	 */
	public HtmlPanelGrid getHeaderPanel() {

		Logger.logInfo("entered method getHeaderPanel");

		headerPanel = new HtmlPanelGrid();
		headerPanel.setWidth("100%");
		if(isViewMode()){
			headerPanel.setColumns(8);
		}else{
			headerPanel.setColumns(9);
		}
		headerPanel.setBorder(0);
		headerPanel.setCellpadding("3");
		headerPanel.setCellspacing("1");
		headerPanel.setBgcolor("#cccccc");
		headerPanel.setId("headerPanel"+RandomStringUtils.randomAlphanumeric(15));
		
		HtmlOutputText headerText1 = new HtmlOutputText();
		HtmlOutputText headerText2 = new HtmlOutputText();
		HtmlOutputText headerText3 = new HtmlOutputText();
		HtmlOutputText headerText4 = new HtmlOutputText();
		HtmlOutputText headerText5 = new HtmlOutputText();
		HtmlOutputText headerText6 = new HtmlOutputText();
		HtmlOutputText headerText7 = new HtmlOutputText();
		HtmlOutputText headerText8 = new HtmlOutputText();
		HtmlOutputText headerText9 = new HtmlOutputText();

		headerText1.setValue("Description");
		//headerText1.setId("desc");
		headerText1.setId("desc"+RandomStringUtils.randomAlphanumeric(15));
		
		headerText2.setValue("Term");
		//headerText2.setId("term");
		headerText2.setId("term"+RandomStringUtils.randomAlphanumeric(15));
		/*Start CARS-Frequency Qualifier*/
		headerText3.setValue("Frequency - Qualifier");
		headerText3.setId("qualifier"+RandomStringUtils.randomAlphanumeric(15));
		//headerText3.setId("qualifier");
		/*End CARS-Frequency Qualifier*/
		
		headerText4.setValue("PVA");
		//headerText4.setId("pva");
		headerText4.setId("pva"+RandomStringUtils.randomAlphanumeric(15));

		headerText8.setValue("Format");
		//headerText8.setId("dataTypeLgnd");
		headerText8.setId("dataTypeLgnd"+RandomStringUtils.randomAlphanumeric(15));
		
		headerText5.setValue("Benefit Value");
		//headerText5.setId("bnftValue");
		headerText5.setId("bnftValue"+RandomStringUtils.randomAlphanumeric(15));
		
		headerText6.setValue("Reference");
		//headerText6.setId("ref");
		headerText6.setId("ref"+RandomStringUtils.randomAlphanumeric(15));
		
		headerText7.setValue("Notes");
		//headerText7.setId("Notes");
		headerText7.setId("Notes"+RandomStringUtils.randomAlphanumeric(15));

		headerPanel.setStyleClass("dataTableHeader");
		
		headerPanel.getChildren().add(headerText1);
		headerPanel.getChildren().add(headerText2);
		headerPanel.getChildren().add(headerText3);
		headerPanel.getChildren().add(headerText4);
		headerPanel.getChildren().add(headerText8);
		headerPanel.getChildren().add(headerText5);
		headerPanel.getChildren().add(headerText6);
		headerPanel.getChildren().add(headerText7);
		if(!isViewMode()){
			headerPanel.getChildren().add(headerText9);
		}

		return headerPanel;
	}

	/**
	 * Sets the headerPanel
	 * @param headerPanel.
	 */
	public void setHeaderPanel(HtmlPanelGrid headerPanel) {
		this.headerPanel = headerPanel;
	}

	public String getCoverage() {
		String contractType = getContractSession().getContractKeyObject().contractType;
		if (contractType.equals("MANDATE")) {
			mandate = false;
		}
		if ((!isViewMode())) {
			return "contractCoverage";
		} else
			return "contractCoverageView";

	}
	
	/**
	 * This will give posible values list corresponding to Criteria value
	 * @param crString
	 * @return
	 */
	private List fetchPosibleValuesList(String crString){
		List newList = new ArrayList();
		BenefitTierCriteriaPsblValue benefitTierCrtPsbl;
		List tierCrtPsblValueList = (List)getSession().getAttribute(WebConstants.TIER_CRITERIA_PSBL_VALUE_LIST);
		if(tierCrtPsblValueList!=null){
		for(int j=0;j<tierCrtPsblValueList.size();j++){
			benefitTierCrtPsbl = (BenefitTierCriteriaPsblValue) tierCrtPsblValueList.get(j);
			if(crString.equalsIgnoreCase(benefitTierCrtPsbl.getBenefitTierCtrName())){
				newList.add(benefitTierCrtPsbl);
			}
		}
		}
		return newList;
	}
	/**
     * This method returns request variable.
     * @param benefitDefList
     * @return
     */
    private TierDefinitionRetrieveRequest getTierDefinitionRetrieveRequest(List benefitDefList) {

    	BenefitLine entityBenefitLine = (BenefitLine) benefitDefList.get(0);
    	int benefitDefinitionId = entityBenefitLine.getBenefitDefinitionId();
    	
    	TierDefinitionRetrieveRequest tierDefRequest = (TierDefinitionRetrieveRequest) this
		.getServiceRequest(ServiceManager.BENEFIT_TIER_DEFINITION_REQUEST);
		tierDefRequest.setProductSysId(getContractKeyObject().getProductId());
		tierDefRequest.setBenefitComponentSysId(getBenefitComponentIdFromSession());
		tierDefRequest.setBenefitDefinitionSysId(benefitDefinitionId);
		
		return tierDefRequest;
	}
	/**
	 * @return Returns the benefitTierDefinitionsList from DB.
	 */
	private List getTierDefinitionsList(List benefitDefinitions) {
		TierDefinitionRetrieveRequest request = getTierDefinitionRetrieveRequest(benefitDefinitions);
		TierDefinitionRetrieveResponse response = (TierDefinitionRetrieveResponse) executeService(request);
		if (null != response) {
			benefitTierDefinitionsList = response.getBenefitTierDefinitonsList();
		}
			return benefitTierDefinitionsList;
	}
	/**
	 * This method will set the possible value list for posible value popup 
	 * @return
	 */
	public String getPsvlLookupRecords() {
        String criString;
            if(getRequest().getParameter(WebConstants.CRITERIA_STRING) !=null){
                    criString = getRequest().getParameter(WebConstants.CRITERIA_STRING).toString();
                    pbvlDefList = getPosibleValuesList(criString);
            }
                    return psvlLookupRecords;
    }
    
    private List getPosibleValuesList(String crString){
        List newList = new ArrayList();
        BenefitTierCriteriaPsblValue benefitTierCrtPsbl;
        List tierCrtPsblValueList = (List)getSession().getAttribute(WebConstants.TIER_CRITERIA_PSBL_VALUE_LIST);
        if(tierCrtPsblValueList!=null){
        	for(int j=0;j<tierCrtPsblValueList.size();j++){
              benefitTierCrtPsbl = (BenefitTierCriteriaPsblValue) tierCrtPsblValueList.get(j);
              	if(crString.equalsIgnoreCase(benefitTierCrtPsbl.getBenefitTierCtrName())){
                    newList.add(benefitTierCrtPsbl);
               }
        	}
        }
        return newList;
    }

	/**
     * Thsi method sets all posible values list in session object. 
     * @param benefitTierDefinitionsList
     */
    private void setPosibleValuesListToSession(List benefitTierDefinitionsList){
    	TierDefinitionBO tierDefBO = new TierDefinitionBO();
    	BenefitTierCriteriaPsblValue benefitTierCrtPsbl;
    	List tierCriteriaPsblValueList = new ArrayList();
    	int listSize = benefitTierDefinitionsList.size();
		for(int lsize = 0;lsize<listSize;lsize++){
			tierDefBO = (TierDefinitionBO) benefitTierDefinitionsList.get(lsize);
			benefitTierCrtPsbl = new BenefitTierCriteriaPsblValue();
			
			benefitTierCrtPsbl.setBenefitTierCtrName(tierDefBO.getCriteriaName());
			benefitTierCrtPsbl.setBenefitTierCtrPsblValue(tierDefBO.getTierCrtPsvlValue());
			benefitTierCrtPsbl.setBenefitTierCtrPsblDesc(tierDefBO.getTierCrtPsvlValueDesc());
			
			tierCriteriaPsblValueList.add(benefitTierCrtPsbl);
		}
		getSession().setAttribute(WebConstants.TIER_CRITERIA_PSBL_VALUE_LIST,tierCriteriaPsblValueList);
    	
    }
	
	/**
     * This mothod put the benefitTierDefinitionsList list into session.
     * This list is useful for AddTier popup.
     * @param benefitDefinitions
     */
    private void getPosibleValuesList(List benefitDefinitions){
    	
    	benefitTierDefinitionsList = this.getTierDefinitionsList(benefitDefinitions);
        if(null!=benefitTierDefinitionsList){
        	super.setTierDefWithPsvlListToSession(benefitTierDefinitionsList);
        	setPosibleValuesListToSession(benefitTierDefinitionsList);
        }
    }
	/**
	 * This method returns the panel for benefit definitions.
	 * 
	 * Returns the Panel
	 * @return HtmlPanelGrid Panel.
	 */
	public HtmlPanelGrid getPanel() {
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","ContractCoverageBackingBean","getPanel()"));

		// Retaining the previous entered values, In case of application has any error message (Defect: 186432)
		// get the benefit defenitions list if list not null
		List benefitDefinitonsList = this.getBenefitDefinitionsList();
		
		// The result is stored in class variable to avoid Unwanted calls from getTierPanel.
		// Also the flag will be set once the information is retrieved.
		this.benefitDefinitionsList = benefitDefinitonsList;
		this.benefitDefinitionsListRetrieved = true;
		
		if (validationMessages.isEmpty() && null != benefitDefinitonsList) {		
			int rowNumber = 0;
			//  EntityBenefitDefenition benefitDefinitionWrapper = this.getBenefitDefinition();
		
	
			//This method gets the values from the benefit definiton List and sets it to the level list and line list
			getValuesFromDefinitonList(benefitDefinitonsList);
			
	        //This method Fetch the List of all possible values.
	        getPosibleValuesList(benefitDefinitonsList);
	
			panel = new HtmlPanelGrid();
			if(isViewMode()){
				panel.setColumns(8);
			}else{
				panel.setColumns(9);
			}
			panel.setWidth("100%");
	
			panel.setBorder(0);
			panel.setCellpadding("3");
			panel.setCellspacing("1");
			panel.setBgcolor("#cccccc");
			panel.setId("panel"+RandomStringUtils.randomAlphanumeric(15));
			StringBuffer rows = new StringBuffer();
	
			//setting values to benefit levels
			int size = benefitLevelList.size();
	
			//iterating to get the benefit levels
			for (int i = 0; i < size; i++) {
				rowNumber++;
				rows.append("dataTableEvenRow");
	
				//a benefit level is selected
				BenefitLevel benefitLevelValues = (BenefitLevel) benefitLevelList
						.get(i);
	
				//gets the benefit lines of a benefit level
				List benefitLines = benefitLevelValues.getBenefitLines();
	
				//setting the benefit level values to the panel grid
				setBenefitLevelValuesToGrid(i, benefitLevelValues, benefitLines
						.size(), rowNumber);
	
				if (benefitLines.size() != 0)
					rows.append(",");
				//iterating to get the individual benefit lines
				for (int j = 0; j < benefitLines.size(); j++) {
					rowNumber++;
	
					rows.append("dataTableOddRow");
	
					if (i < (size - 1))
						rows.append(",");
					else if (j < (benefitLines.size() - 1))
						rows.append(",");
	
					//selects an individual benefit line
					BenefitLine benefitLineValues = (BenefitLine) benefitLines
							.get(j);
	
					//sets the benefit lines of a benefit level to the panle grid
					
					setBenefitLineValuesToGrid(benefitLevelValues, j,
							benefitLineValues, i);
	
				}
	
			}
			getContractSession().setDataHiddenLowerLevelValDescMap(dataHiddenLowerLevelValDesc);
			getContractSession().setDataHiddenValTermMap(dataHiddenValTerm);
			getContractSession().setDataHiddenValQualifierMap(dataHiddenValQualifier);
			getContractSession().setHiddenLineFreqValueMap(hiddenLineFreqValueMap);
			getContractSession().setHiddenLowerLevelFreqValueMap(hiddenLowerLevelFreqValueMap);
			getContractSession().setLineIdMap(lineIdMap);
			getContractSession().setLevelIdsMapFromSession(levelIdsMap);
			
			panel.setRowClasses(rows.toString());
		}
		Logger.logInfo(th.endPerfLogging());
		//added the messages to the request --Defect fix for WAS7 Migration
		addAllMessagesToRequest(informationMessageToDisplayOnPage);
		return panel;

	}

	/**
	 * 
	 * This method updates the benefit levels that are provided from the jsp
	 * page
	 */
	public String reloadPage() {
		return "contractCoverage";
	}

	/**
	 * Returns the benefitLevelForPrint
	 * @return String benefitLevelForPrint.
	 */
	public String benefitLevelForPrint() {
		this.hiddenBenefitLevelForPrint();
		return "benefitLevelForPrint";
	}

	public String benefitLevelForPrintDetail() {
		this.hiddenBenefitLevelForPrint();
		return "benefitLevelForPrintDetail";
	}
	
	private String hiddenBenefitLevelForPrint() {

		int rowNumber = 0;
		//  EntityBenefitDefenition benefitDefinitionWrapper = this.getBenefitDefinition();
		List benefitDefinitonsList = this.getBenefitDefinitionsList();

		List listForPrint = new ArrayList();
		//This method gets the values from the benefit definiton List and sets it to the level list and line list
		getValuesFromDefinitonList(benefitDefinitonsList);
		StringBuffer rows = new StringBuffer();

		//setting values to benefit levels
		int size = benefitLevelList.size();

		//iterating to get the benefit levels
		for (int i = 0; i < size; i++) {
			rowNumber++;
			rows.append("dataTableEvenRow");

			//a benefit level is selected
			BenefitLevel benefitLevelValues = (BenefitLevel) benefitLevelList
					.get(i);

			BenefitDefinitionPrintVO benefitDefinitionPrintVO = new BenefitDefinitionPrintVO();

			if (null != benefitLevelValues.getLevelDesc()) {
				benefitDefinitionPrintVO.setLevelDesc(benefitLevelValues
						.getLevelDesc());
			} else {
				benefitDefinitionPrintVO.setLevelDesc(" ");
			}

			/*START CARS */
	        //Frequency
	        if(null != benefitLevelValues.getFrequencyDesc() 
	        		&& !WebConstants.EMPTY_STRING.equalsIgnoreCase(benefitLevelValues.getFrequencyDesc())
					&& benefitLevelValues.getFrequencyDesc().length() > 0
					&& Integer.parseInt(benefitLevelValues.getFrequencyDesc())>0){
	        	String qualifier = benefitLevelValues.getQualifierDesc().trim();
	        	if (null != benefitLevelValues.getQualifierDesc()) {		        		
	        		benefitDefinitionPrintVO.setQualifierDesc(benefitLevelValues.getFrequencyDesc()+" - "+qualifier);
	        	}else{
	        		benefitDefinitionPrintVO.setQualifierDesc(benefitLevelValues.getFrequencyDesc());
	        	}
	        }else{
	        	if (null != benefitLevelValues.getQualifierDesc()) {
					benefitDefinitionPrintVO.setQualifierDesc(benefitLevelValues
							.getQualifierDesc());
				} else {
					benefitDefinitionPrintVO.setQualifierDesc(" ");
				}
	        }
			
	        /*END CARS */
			
			if (null != benefitLevelValues.getTermDesc()) {
				benefitDefinitionPrintVO.setTermDesc(benefitLevelValues
						.getTermDesc());
			} else {
				benefitDefinitionPrintVO.setTermDesc(" ");
			}

			benefitDefinitionPrintVO.setProviderArrangementDesc(" ");

			benefitDefinitionPrintVO.setBenefitValue(" ");

			listForPrint.add(benefitDefinitionPrintVO);
			List benefitLines = benefitLevelValues.getBenefitLines();
			for (int j = 0; j < benefitLines.size(); j++) {

				BenefitDefinitionPrintVO benefitLinePrintVO = new BenefitDefinitionPrintVO();

				// Get the individual lines from the list
				BenefitLine individualLine = (BenefitLine) benefitLines.get(j);

				if (null != individualLine.getProviderArrangementDesc()) {
					benefitLinePrintVO
							.setProviderArrangementDesc(individualLine
									.getProviderArrangementDesc());
				} else {
					benefitLinePrintVO.setProviderArrangementDesc(" ");
				}

				if (null != individualLine.getProviderArrangementId()) {
					benefitLinePrintVO.setProviderArrangementId(individualLine
							.getProviderArrangementId());
				} else {
					benefitLinePrintVO.setProviderArrangementId(" ");
				}

				if (null != benefitLevelValues.getTermDesc()) {
					benefitLinePrintVO.setTermDesc(benefitLevelValues
							.getTermDesc());
				} else {
					benefitLinePrintVO.setTermDesc(" ");
				}

				benefitLinePrintVO.setReferenceDesc(" ");

	        	if (null != benefitLevelValues.getQualifierDesc()) {
						benefitLinePrintVO.setQualifierDesc(benefitLevelValues
								.getQualifierDesc());
					} else {
						benefitLinePrintVO.setQualifierDesc(" ");
					}		       
				
				String benftVal = " ";
				if (null != individualLine.getBenefitValue()) {
					benftVal = individualLine.getBenefitValue();
					benefitLinePrintVO.setBenefitValue(benftVal);
				} else {
					benftVal = " ";
					benefitLinePrintVO.setBenefitValue(benftVal);
				}

				if (null != individualLine.getDataTypeLegend()) {
					benefitLinePrintVO.setDataTypeDesc(individualLine
							.getDataTypeLegend());
				} else {
					benefitLinePrintVO.setDataTypeDesc(" ");
				}

				benefitLinePrintVO.setLevelDesc(" ");
				/*START CARS*/
				benefitLinePrintVO.setQualifierDesc(" ");
				benefitLinePrintVO.setTermDesc(" ");
				/*END CARS*/

				if (null != individualLine.getReferenceDesc()) {
					benefitLinePrintVO.setReferenceDesc(individualLine
							.getReferenceDesc());
				} else {
					benefitLinePrintVO.setReferenceDesc(" ");
				}

				listForPrint.add(benefitLinePrintVO);
			}
			//gets the benefit lines of a benefit level
			//setting the benefit level values to the panel grid
		}
		this.printBenftDefnList = listForPrint;
		return "";
	}

	/**
	 * Returns the printBenftDefnList
	 * @return List printBenftDefnList.
	 */
	public List getPrintBenftDefnList() {
		return printBenftDefnList;
	}

	/**
	 * Sets the printBenftDefnList
	 * @param printBenftDefnList.
	 */
	public void setPrintBenftDefnList(List printBenftDefnList) {
		this.printBenftDefnList = printBenftDefnList;
	}

	/** This method gets the values from the benefit definiton List and sets it to the level list and line list
	 * @param benefitDefinitionsList
	 */

	private void getValuesFromDefinitonList(List benefitDefinitionsList) {
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","ContractCoverageBackingBean","getValuesFromDefinitonList()"));

		Logger.logInfo("entered method getValuesFromDefinitionList");

		// TODO Auto-generated method stub
		benefitLevelList = new ArrayList();
		for (int i = 0; i < benefitDefinitionsList.size(); i++) {
			BenefitLine entityBenefitLine = (BenefitLine) benefitDefinitionsList
					.get(i);
			int benefitDefinitionId = entityBenefitLine.getBenefitDefinitionId();            
            getSession().setAttribute("SESSION_BNFT_DEFN_ID_CONTRACT",new Integer(benefitDefinitionId));
			//sets values to the benefitLevel List
			setValuesToBenefitLevel(entityBenefitLine, benefitLevelList);

		}
		Logger.logInfo(th.endPerfLogging());
	}

	/**This method sets values to the benefit level List
	 * @param entityBenefitLine
	 * @param benefitLevelList
	 */
	private void setValuesToBenefitLevel(BenefitLine entityBenefitLine,
			List benefitLevelList) {

		//Logger.logInfo("entered method setValuesToBenefitLevel");
				
		// TODO Auto-generated method stub
		BenefitLevel benefitLevelBO = null;

		/* //checks if the benefit level list size is not equal to zero
		 if(benefitLevelList.size()!= 0){
		 benefitLevelBO=(BenefitLevel)benefitLevelList.get(benefitLevelList.size()-1);
		 }
		 
		 //checks if the benefit LevelList size is 0 or if the previous levelId is equal to the present levelId
		 if(benefitLevelList.size()==0 || entityBenefitLine.getLevelSysId()!=benefitLevelBO.getLevelId()){*/
		BenefitLevel tempBO = null;
		boolean checkFlag = false;
		// checks if the benefit level list size is not equal to zero
		if (benefitLevelList.size() != 0) {
			/* benefitLevelBO = (BenefitLevel)benefitLevelList.
			 get(benefitLevelList.size() - 1);*/
			for (int i = 0; i < benefitLevelList.size(); i++) {
				tempBO = (BenefitLevel) benefitLevelList.get(i);
				if (tempBO.getLevelId() == entityBenefitLine.getLevelSysId()) {
					benefitLevelBO = (BenefitLevel) benefitLevelList.get(i);
					
					/*- Check if its a coded line then the decription and frequency value of the 
					 * benefit level should be the description and frequency value of this coded line
					 *  -*/
					if(null != entityBenefitLine.getBenefitValue() && !entityBenefitLine.getBenefitValue().equals("")){
						benefitLevelBO.setLevelDesc(entityBenefitLine.getLevelDesc());
						benefitLevelBO.setFrequencyDesc(entityBenefitLine.getFrequencyValue()+"");
						
					}
					checkFlag = true;
				}
			}
		}
		// checks if the benefit LevelList size is 0 or if the previous levelId is equal to the present levelId
		if (benefitLevelList.size() == 0 ||
		//(entityBenefitLine.getLevelSysId()!= benefitLevelBO.getLevelId())){
				!checkFlag) {
			BenefitLevel entityBenefitLevel = new BenefitLevel();
			String description = null;
			/*if(null != entityBenefitLine.getLevelDesc()){
				if(!isPrintMode()){
	            	String desc = null;
	            	description = entityBenefitLine.getLevelDesc().trim();
	                if(description.length()>18){
	                	String[] strTokenizerArr = description.split(" ");
	                	for(int num=0;num<strTokenizerArr.length;num++){
	                		if(strTokenizerArr[num].length()>18){
	                			strTokenizerArr[num] = strTokenizerArr[num].substring(0,18)+" "+
	                				strTokenizerArr[num].substring(18);
	                		}
	                	}
	                	for(int num=0;num<strTokenizerArr.length;num++){
	                		if(null==desc)
	                			desc = strTokenizerArr[num];
	                		else
	                			desc = desc +" "+ strTokenizerArr[num];
	                	}
	                	description = desc;
	                }
				}else if(isPrintMode()){
	            	String desc = null;
	            	description = entityBenefitLine.getLevelDesc().trim();
	                if(description.length()>20){
	                	String[] strTokenizerArr = description.split(" ");
	                	for(int num=0;num<strTokenizerArr.length;num++){
	                		if(strTokenizerArr[num].length()>20){
	                			strTokenizerArr[num] = strTokenizerArr[num].substring(0,20)+" "+
	                				strTokenizerArr[num].substring(20);
	                		}
	                	}
	                	for(int num=0;num<strTokenizerArr.length;num++){
	                		if(null==desc)
	                			desc = strTokenizerArr[num];
	                		else
	                			desc = desc +" "+ strTokenizerArr[num];
	                	}
	                	description = desc;
	                }
				}
			}else {
				description = WebConstants.EMPTY_STRING;
	        }*/
			entityBenefitLevel.setLevelDesc(entityBenefitLine.getLevelDesc());
			entityBenefitLevel.setLevelId(entityBenefitLine.getLevelSysId());
			entityBenefitLevel.setTermDesc(entityBenefitLine.getTermDesc());
			/* Start CARS */
			entityBenefitLevel.setFrequencyDesc(entityBenefitLine.getFrequencyValue()+"");
			entityBenefitLevel.setLowerLevelFrequencyValue(entityBenefitLine.getLowerLevelFrequencyValue()+"");
			entityBenefitLevel.setLowerLevelDescValue(entityBenefitLine.getLowerLevelDescValue());
			/*End CARS */
			entityBenefitLevel.setQualifierDesc(entityBenefitLine
					.getQualifierDesc());
			/*START CARS*/
			String freqValue=Integer.toString(entityBenefitLine.getFrequencyValue());
			entityBenefitLevel.setFrequencyDesc(freqValue.trim());
			/*END CARS*/
			entityBenefitLevel.setReferenceDesc(entityBenefitLine
					.getReferenceDesc());
			if (0 != entityBenefitLevel.getTierSysId()) {
				entityBenefitLevel.setTierSysId(entityBenefitLevel
						.getTierSysId());
			}
			//sets benefit lines to the benefit Levels
			entityBenefitLevel.setBenefitLines(new ArrayList());
			entityBenefitLevel.setIsTierDefExist(entityBenefitLine.getIsTierDefExist());
			entityBenefitLevel.setIsTierLevelExist(entityBenefitLine.getIsTierLevelExist());
			entityBenefitLevel.getBenefitLines().add(
					getBenefitLineBO(entityBenefitLine));
			benefitLevelList.add(entityBenefitLevel);

		} else {
			//add benefit lines to the existing benefit level
			benefitLevelBO.getBenefitLines().add(
					getBenefitLineBO(entityBenefitLine));

		}

	}

	/**
	 * This method returns the benefit line bo
	 * @param entityBenefitLine
	 * @return
	 */
	private BenefitLine getBenefitLineBO(BenefitLine entityBenefitLine) {

		//Logger.logInfo("entered method getBenefitLineBO");

		BenefitLine entityBenefitLineToSet = new BenefitLine();
		entityBenefitLineToSet.setBenefitValue(entityBenefitLine
				.getBenefitValue());
		entityBenefitLineToSet.setProviderArrangementDesc(entityBenefitLine
				.getProviderArrangementDesc());
		entityBenefitLineToSet.setLineSysId(entityBenefitLine.getLineSysId());
		entityBenefitLineToSet.setLineValue(entityBenefitLine.getLineValue());
		/* Start CARS */
		entityBenefitLineToSet.setFrequencyValue(entityBenefitLine.getFrequencyValue());
		entityBenefitLineToSet.setLevelDesc(entityBenefitLine.getLevelDesc());
		/*End CARS */

		if (0 != entityBenefitLine.getTierSysId()) {
			entityBenefitLineToSet.setTierSysId(entityBenefitLine
					.getTierSysId());
		}
		if (null != entityBenefitLine.getDataTypeDesc()
				&& !(entityBenefitLine.getDataTypeDesc()).equals("")) {
			entityBenefitLineToSet.setDataTypeDesc(entityBenefitLine
					.getDataTypeDesc());

			//changes for validation

			entityBenefitLineToSet.setDataTypeId(entityBenefitLine
					.getDataTypeId());
			entityBenefitLineToSet.setReferenceDesc(entityBenefitLine
					.getReferenceDesc());
			entityBenefitLineToSet.setBnftLineNotesExist(entityBenefitLine
					.getBnftLineNotesExist());
			entityBenefitLineToSet.setDataTypeLegend(entityBenefitLine
					.getDataTypeLegend());
			entityBenefitLineToSet.setProviderArrangementId(entityBenefitLine
					.getProviderArrangementId());
			entityBenefitLineToSet.setIsTierDefExist(entityBenefitLine.getIsTierDefExist());
			entityBenefitLineToSet.setIsTierLevelExist(entityBenefitLine.getIsTierLevelExist());

		}
		return entityBenefitLineToSet;
	}

	/**
	 * This method sets the benefitLineValues to the panel Grid
	 * 
	 * @param benefitLevelValues
	 * @param j
	 * @param benefitLineValues
	 * @param i
	 */
	private void setBenefitLineValuesToGrid(BenefitLevel benefitLevelValues,
			int j, BenefitLine benefitLineValues, int i) {

		//Logger.logInfo("entered method setBenefitLineValuesToGrid");

		HtmlOutputText lineDesc = new HtmlOutputText();
		lineDesc.setValue(" ");

		HtmlOutputText lineTerm = new HtmlOutputText();
		lineTerm.setValue(benefitLevelValues.getTermDesc());

		HtmlOutputText lineQualifier = new HtmlOutputText();
		lineQualifier.setValue(benefitLevelValues.getQualifierDesc());

		HtmlOutputText linePVA = new HtmlOutputText();
		linePVA.setValue(benefitLineValues.getProviderArrangementId());

		HtmlOutputText ref = new HtmlOutputText();
		ref.setValue(benefitLineValues.getReferenceDesc());

		HtmlOutputText lineDataType = new HtmlOutputText();
		lineDataType.setValue(benefitLineValues.getDataTypeDesc());

		//      hidden field for storing the benefitLineSysId
		HtmlInputHidden hiddenLineIds = new HtmlInputHidden();
		hiddenLineIds.setId("hiddenLineId" + j + "_" + i);

		hiddenLineIds.setValue(new Integer(benefitLineValues.getLineSysId())
				+ ":" + benefitLineValues.getBenefitValue() + ":"
				+ benefitLineValues.getDataTypeId() + ":"
				+ benefitLevelValues.getLevelDesc());

		// set the value to the map
		//Commented as part of stabilization release
		
		/*ValueBinding lineIdValBind = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{contractCoverageBackingBean.lineIdMap["
								+ benefitLineValues.getLineSysId() + "]}");
		hiddenLineIds.setValueBinding("value", lineIdValBind);*/
		this.lineIdMap.put(new Long(benefitLineValues.getLineSysId()),new Integer(benefitLineValues.getLineSysId())
				+ ":" + benefitLineValues.getBenefitValue() + ":"
				+ benefitLineValues.getDataTypeId() + ":"
				+ benefitLevelValues.getLevelDesc());
		
		HtmlInputHidden hiddenLevelIds = new HtmlInputHidden();
		hiddenLevelIds.setId("hiddenLevelIds" + j + "_" + i);

		hiddenLevelIds.setValue(new Integer(benefitLevelValues.getLevelId())
				+ ":" + new Integer(benefitLineValues.getLineSysId()));

		// set the value to the map
		//Commented as part of stabilization release
		/*ValueBinding levelIdsValBind = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{contractCoverageBackingBean.levelIdsMap["
								+ benefitLineValues.getLineSysId() + "]}");
		hiddenLevelIds.setValueBinding("value", levelIdsValBind);*/
		// **End**  
		this.levelIdsMap.put(new Long(benefitLineValues.getLineSysId()),new Integer(benefitLevelValues.getLevelId())
				+ ":" + new Integer(benefitLineValues.getLineSysId()));
		
		HtmlOutputText lineReference = new HtmlOutputText();

		if (null != benefitLineValues.getReferenceDesc()) {
			lineReference.setValue(benefitLineValues.getReferenceDesc().trim());
		} else {
			lineReference.setValue(WebConstants.EMPTY_STRING);
		}

		//to b removed
		HtmlInputHidden hiddenLevelId = new HtmlInputHidden();
		hiddenLevelId.setId("hiddenLevelId" + i);
		hiddenLevelId.setValue(new Integer(benefitLevelValues.getLevelId()));

		//for line - added
		HtmlInputHidden hiddenLineId = new HtmlInputHidden();
		hiddenLineId.setId("hiddenLineId" + i + "_" + j);
		hiddenLineId.setValue(new Integer(benefitLineValues.getLineSysId()));

		// Change start
		// set the value to the map
		
		//Commented as part of stabilization release
		/*ValueBinding levelIdValBind = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{contractCoverageBackingBean.levelIdMap[" + i + "]}");
		hiddenLevelId.setValueBinding("value", levelIdValBind);*/

		//      line BenefitValue
		HtmlInputText lineBnftValue = new HtmlInputText();
		HtmlSelectOneMenu seloneMenuForBnftValue = new HtmlSelectOneMenu();
		UIComponent object = null;

		//new change
		String sysDataType = null;
		int dataType = 0;
		String dataTypeId = benefitLineValues.getDataTypeId();
		if (null != dataTypeId && !"".equals(dataTypeId)) {
			dataType = Integer.parseInt(dataTypeId);
		}
		List universeDataTypeList = WPDStringUtil.getUniverseDataTypeList();
		if (dataType != 0) {
			// verify
			DataTypeLocateResult dataTypeDetails = null;
			dataTypeDetails = WPDStringUtil.getDataTypeDetails(
					universeDataTypeList, dataType);
			if (null != dataTypeDetails) {
				sysDataType = dataTypeDetails.getDataTypeDesc().toUpperCase()
						.trim();
			}
		}
		//output text for view
		HtmlOutputText lineBnftValueView = new HtmlOutputText();
		lineBnftValueView.setId("lineBnftValueView" + j + "_" + i);
		lineBnftValueView.setValue(benefitLineValues.getBenefitValue());
		String contractType = getContractSession().getContractKeyObject().contractType;
		if (null != sysDataType) {
			if (sysDataType.equals("BOOLEAN")) {
				
				//System.out.println("Inside seelect menu");
				Logger.logInfo("Inside seelect menu");
				UISelectItems selectItems = new UISelectItems();
				List possibleBnftVal = new ArrayList();
				possibleBnftVal.add(new SelectItem("", ""));
				//				 Code changed as part of the Enhancement to display the benefit values same as 
				//that of the benefit value in the benefit
				if (null != benefitLineValues.getBenefitValue()
						&& !benefitLineValues.getBenefitValue().equals("")) {

					if (benefitLineValues.getBenefitValue().equalsIgnoreCase(
							"Y")
							|| benefitLineValues.getBenefitValue()
									.equalsIgnoreCase("N")) {

						possibleBnftVal.add(new SelectItem("Y", "Y"));
						possibleBnftVal.add(new SelectItem("N", "N"));

					} else {
						possibleBnftVal.add(new SelectItem("YES", "YES"));
						possibleBnftVal.add(new SelectItem("NO", "NO"));
					}
				} else {
					if (null != benefitLineValues.getLineValue()
							&& !benefitLineValues.getLineValue().equals("")) {

						if (benefitLineValues.getLineValue().equalsIgnoreCase(
								"Y")
								|| benefitLineValues.getLineValue()
										.equalsIgnoreCase("N")) {

							possibleBnftVal.add(new SelectItem("Y", "Y"));
							possibleBnftVal.add(new SelectItem("N", "N"));

						} else {
							possibleBnftVal.add(new SelectItem("YES", "YES"));
							possibleBnftVal.add(new SelectItem("NO", "NO"));
						}
					}

					else {
						possibleBnftVal.add(new SelectItem("YES", "YES"));
						possibleBnftVal.add(new SelectItem("NO", "NO"));
					}

				}

				selectItems.setValue(possibleBnftVal);
				seloneMenuForBnftValue.getChildren().add(selectItems);

				if (null != benefitLineValues.getBenefitValue()
						&& !benefitLineValues.getBenefitValue().equals("")) {
					if (benefitLineValues.getBenefitValue().equalsIgnoreCase(
							"YES")) {
						if (!(contractType).equals("MANDATE"))
							seloneMenuForBnftValue.setValue("YES");
						else
							lineBnftValueView.setValue("YES");

					} else if (benefitLineValues.getBenefitValue()
							.equalsIgnoreCase("NO")) {
						if (!(contractType).equals("MANDATE"))
							seloneMenuForBnftValue.setValue("NO");
						else
							lineBnftValueView.setValue("NO");
					} else if (benefitLineValues.getBenefitValue()
							.equalsIgnoreCase("Y")) {
						if (!(contractType).equals("MANDATE"))
							seloneMenuForBnftValue.setValue("Y");
						else
							lineBnftValueView.setValue("Y");
					} else if (benefitLineValues.getBenefitValue()
							.equalsIgnoreCase("N")) {
						if (!(contractType).equals("MANDATE"))
							seloneMenuForBnftValue.setValue("N");
						else
							lineBnftValueView.setValue("N");
					} else {
						if (!(contractType).equals("MANDATE"))
							seloneMenuForBnftValue.setValue("");
						else
							lineBnftValueView.setValue("");
					}
				}

				seloneMenuForBnftValue.setId("lineBnftValue" + j + "_" + i);
				object = (HtmlSelectOneMenu) seloneMenuForBnftValue;
				//				 set the value to the map
				ValueBinding valueItem = FacesContext.getCurrentInstance()
						.getApplication().createValueBinding(
								"#{contractCoverageBackingBean.benefitValueMap['"

								+ benefitLineValues.getLineSysId()
				
										+ "']}");
				seloneMenuForBnftValue.setValueBinding("value", valueItem);
			} else {
				lineBnftValue.setSize(6);
				lineBnftValue.setId("lineBnftValue" + j + "_" + i);
				lineBnftValue.setValue(benefitLineValues.getBenefitValue());
				if (!benefitLineValues.getDataTypeDesc().equalsIgnoreCase(
						"String")) {
					//	        	lineBnftValue.setOnkeypress("return isNumberKey(event);");
				}
				lineBnftValue.setTitle("BenefitValue"
						+ benefitLineValues.getDataTypeDesc());
				ValueBinding valueItem = FacesContext.getCurrentInstance()
						.getApplication().createValueBinding(
								"#{contractCoverageBackingBean.benefitValueMap['"

								+ benefitLineValues.getLineSysId()
								//+ benefitValueMapKey

										+ "']}");
				lineBnftValue.setValueBinding("value", valueItem);
				lineBnftValue.setStyleClass("formInputField");
				lineBnftValue.setStyle("width:50px;");
			}

		}

		HtmlOutputText lineEmptyString = new HtmlOutputText();
		lineEmptyString.setValue(" ");

		HtmlOutputLabel lblBnftValue = new HtmlOutputLabel();
		lblBnftValue.setId("lblBnftValue"+RandomStringUtils.randomAlphanumeric(15));
		//lblBnftValue.setId("lblBnftValue" + j + "_" + i);
		lblBnftValue.setFor("lineBnftValue" + j + "_" + i);

		//change**

		if (null != sysDataType) {
			if (sysDataType.equals("DOLLAR")) {
				if (!isViewMode() && !contractType.equals("MANDATE")) {
					lblBnftValue.getChildren().add(lineBnftValue);
					lblBnftValue.getChildren().add(lineEmptyString);
				} else {
					lblBnftValue.getChildren().add(lineBnftValueView);
					lblBnftValue.getChildren().add(lineEmptyString);
				}
			} else if (sysDataType.equals("PERCENTAGE")) {
				if (!isViewMode() && !contractType.equals("MANDATE")) {
					lblBnftValue.getChildren().add(lineEmptyString);
					lblBnftValue.getChildren().add(lineBnftValue);
				} else {
					lblBnftValue.getChildren().add(lineEmptyString);
					lblBnftValue.getChildren().add(lineBnftValueView);
				}
			} else if (sysDataType.equals("STRING")) {
				if (!isViewMode() && !contractType.equals("MANDATE")) {
					lblBnftValue.getChildren().add(lineEmptyString);
					lblBnftValue.getChildren().add(lineBnftValue);
				} else {
					lblBnftValue.getChildren().add(lineEmptyString);
					lblBnftValue.getChildren().add(lineBnftValueView);
				}

			} else if (sysDataType.equals("BOOLEAN")) {

				if (!isViewMode() && !contractType.equals("MANDATE")) {
					lblBnftValue.getChildren().add(lineEmptyString);
					lblBnftValue.getChildren().add(object);
				} else {
					lblBnftValue.getChildren().add(lineEmptyString);
					lblBnftValue.getChildren().add(lineBnftValueView);
				}

			} else {
				if (!contractType.equals("MANDATE")) {
					if (!isViewMode()) {
						lblBnftValue.getChildren().add(lineEmptyString);
						lblBnftValue.getChildren().add(lineBnftValue);
					} else {
						lblBnftValue.getChildren().add(lineEmptyString);
						lblBnftValue.getChildren().add(lineBnftValueView);
					}
				} else {
					lblBnftValue.getChildren().add(lineEmptyString);
					lblBnftValue.getChildren().add(lineBnftValueView);
				}
			}
			lblBnftValue.getChildren().add(hiddenLineIds);
			lblBnftValue.getChildren().add(hiddenLevelIds);

		}

		//start frn here

		BenefitLine benLine = (BenefitLine) benefitLevelValues
				.getBenefitLines().get(j);

		HtmlGraphicImage noteImage = new HtmlGraphicImage();
		noteImage.setUrl("../../images/notes_exist.gif");
		noteImage.setStyle("cursor:hand;");
		noteImage.setId("noteImage" + j + "_" + i);
		// May 6 - Start
		HtmlInputHidden hiddenNotesStatus = new HtmlInputHidden();
		hiddenNotesStatus.setId("hiddenNotesStatus" + j + "_" + i);
		hiddenNotesStatus.setValue("");
		// May 6 - End	        
		HtmlCommandButton notesButton = new HtmlCommandButton();
		notesButton.setId("notesButton"+RandomStringUtils.randomAlphanumeric(15));
		//notesButton.setId("noteImage" + j + "_" + i);
		notesButton.setValue("NotesButton");
		if ("Y".equals(benLine.getBnftLineNotesExist()))
			notesButton.setImage("../../images/notes_exist.gif");
		else
			notesButton.setImage("../../images/page.gif");
		notesButton.setTitle("Notes");
		notesButton.setStyle("border:0;");
		notesButton.setAlt("Note");
		notesButton.setOnclick("getUrlAssigned(" + benLine.getLineSysId() + "," + j
				+ "," + i + ");return false;");

		HtmlGraphicImage noteImageView = new HtmlGraphicImage();
		noteImageView.setUrl("../../images/notes_exist.gif");
		noteImageView.setStyle("cursor:hand;");
		HtmlCommandButton notesViewButton = new HtmlCommandButton();
		notesViewButton.setId("notesViewButton"+RandomStringUtils.randomAlphanumeric(15));
		notesViewButton.setValue("NotesViewButton");
		if ("Y".equals(benLine.getBnftLineNotesExist()))
			notesViewButton.setImage("../../images/notes_exist.gif");
		else
			notesViewButton.setImage("../../images/page.gif");
		notesViewButton.setTitle("Notes");
		notesViewButton.setStyle("border:0;");
		notesViewButton.setAlt("Note");
		
		if(!isViewMode()){
		notesViewButton
				.setOnclick("ewpdModalWindow_ewpd('benefitLineNotesOverridePopupView.jsp'+getUrl()+'?parentEntityType=ATTACHCONTRACT&lookUpAction=4&secondaryEntityId="
						+ benLine.getLineSysId()
						+ "&temp="
						+ Math.random()
						+ "', 'dummyDiv', 'contractCoverageForm:hidden1',2,1);return false;");
		}else{
			notesViewButton
			.setOnclick("ewpdModalWindow_ewpd('benefitLineNotesOverridePopupView.jsp'+getUrl()+'?parentEntityType=ATTACHCONTRACT&lookUpAction=4&secondaryEntityId="
					+ benLine.getLineSysId()
					+ "&temp="
					+ Math.random()
					+ "', 'dummyDiv', 'contractCoverageForm:hidden1',2,1);return false;");
		}

		//      line image
		HtmlOutputText lineImage = new HtmlOutputText();
		
		lineImage.setId("lineImage" + j + "_" + i);
		if (!isViewMode() && !contractType.equals("MANDATE")) {
			lineImage.getChildren().add(notesButton);
			// May 6 - Start
			lineImage.getChildren().add(hiddenNotesStatus);
			// May 6 - End 	            
		} else
			lineImage.getChildren().add(notesViewButton);

		HtmlOutputLabel lblImage = new HtmlOutputLabel();
		lblImage.setId("lblImage"+RandomStringUtils.randomAlphanumeric(15));
		lblImage.setFor("levelDesc" + i);
		//lblImage.setId("lblImage" + i);

		// lgnd data type
		HtmlOutputText lgndDataType = new HtmlOutputText();
		lgndDataType.setValue(benefitLineValues.getDataTypeLegend());
		lgndDataType.setId("lgndDataType" + j + "_" + i);

		HtmlOutputText dummyText = new HtmlOutputText();

		dummyText.setValue("");

		HtmlOutputText dummyText1 = new HtmlOutputText();

		dummyText1.setValue("");

		HtmlOutputLabel tierLabael = new HtmlOutputLabel();
		tierLabael.setId("tierLabel"+RandomStringUtils.randomAlphanumeric(15));
        //tierLabael.setId("tierLabel"+ j + "_" + i+benefitLineValues.getLineSysId());
        
		panel.getChildren().add(lineDesc);
		panel.getChildren().add(dummyText);
		panel.getChildren().add(dummyText1);
		panel.getChildren().add(linePVA);
		panel.getChildren().add(lgndDataType);
		panel.getChildren().add(lblBnftValue);
		panel.getChildren().add(ref);
		panel.getChildren().add(lineImage);
		if(!isViewMode()){
			panel.getChildren().add(tierLabael);
		}
		//        }
	}

	/** This method sets the benefitLevelValues to the PanelGrid
	 * @param i
	 * @param benefitLevelValues
	 */
	private void setBenefitLevelValuesToGrid(int i,
			BenefitLevel benefitLevelValues, int lineSize, int rowNum) {

		//Logger.logInfo("entered method setBenefitLevelValuesToGrid");

		HtmlOutputText levelDesc = new HtmlOutputText();
		//levelDesc.setStyle("width:170px;");
		
		/*Start CARS*/
		//Frequency
		HtmlInputText levelDescInputText = new HtmlInputText();
		if (null != benefitLevelValues.getLevelDesc()) {
			//Fix for Contract View alignment Issue
				levelDescInputText.setValue(benefitLevelValues.getLevelDesc().trim());
            	String desc = null;
            	String description = benefitLevelValues.getLevelDesc().trim();
                if(description.length()>18){
                	String[] strTokenizerArr = description.split(" ");
                	for(int num=0;num<strTokenizerArr.length;num++){
                		if(strTokenizerArr[num].length()>18){
                			strTokenizerArr[num] = strTokenizerArr[num].substring(0,18)+" "+
                				strTokenizerArr[num].substring(18);
                		}
                	}
                	for(int num=0;num<strTokenizerArr.length;num++){
                		if(null==desc)
                			desc = strTokenizerArr[num];
                		else
                			desc = desc +" "+ strTokenizerArr[num];
                	}
                	description = desc;
                }
				levelDesc.setValue(description);
		} else {
			levelDesc.setValue(WebConstants.EMPTY_STRING);
			levelDescInputText.setValue(WebConstants.EMPTY_STRING);
		}
		
		levelDesc.setId("levelDesc" +i);
		levelDescInputText.setStyleClass("formInputField");
		levelDescInputText.setId("levelDescInputText" +i);
		levelDescInputText.setMaxlength(32);
		//Binding the value for description text `
        ValueBinding hidValItemDesc = FacesContext
                .getCurrentInstance()
                .getApplication()
                .createValueBinding(
                        "#{contractCoverageBackingBean.dataHiddenValDesc["
                                + benefitLevelValues.getLevelId() + 
                                "]}");
        levelDescInputText.setValueBinding("value", hidValItemDesc);
      
        //DESCRIPTION FIX START
        if(isEditMode() && !isViewMode() && !isPrintMode()){
	        if (!BusinessUtil.isSystemGeneratedDescription(benefitLevelValues
					.getLevelDesc(), benefitLevelValues.getTermDesc(),
					benefitLevelValues.getQualifierDesc(), Integer.parseInt(benefitLevelValues.getFrequencyDesc()))){
				if ((benefitLevelValues.getFrequencyDesc()).equalsIgnoreCase(benefitLevelValues
						.getLowerLevelFrequencyValue())){
					levelDescInputText.setStyle("width:125px;display:none");
				}else{
					levelDesc.setStyle("display:none");
					levelDescInputText.setStyle("width:125px");
				}
			}else{
				levelDescInputText.setStyle("width:125px;display:none");
			}
        }
        //DESCRIPTION FIX END        
        //FIX
        
		HtmlInputHidden hiddenForOutputDescription = new HtmlInputHidden();
		hiddenForOutputDescription.setId("hidOutputValDesc" + i);        
		hiddenForOutputDescription.setValue(benefitLevelValues.getLevelDesc().trim());
		
		//Commented as part of stabilization release 1
        /*ValueBinding hidOutputValLevelDesc = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{contractCoverageBackingBean.dataHiddenOutputValDesc["+ benefitLevelValues.getLevelId() +"]}");
        hiddenForOutputDescription.setValueBinding("value",hidOutputValLevelDesc);*/
		
		
		
		//FIX
        //START setting the Lower level description in the hidden 
		HtmlInputHidden hiddenLowerLevelDescription = new HtmlInputHidden();
		hiddenLowerLevelDescription.setId("hidLowerLevelValDesc" + i);        
		hiddenLowerLevelDescription.setValue(benefitLevelValues.getLowerLevelDescValue().trim());
		
		//Commented as part of stabilization release 2
		/*ValueBinding hidLowerLevelValDesc = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{contractCoverageBackingBean.dataHiddenLowerLevelValDesc["+ benefitLevelValues.getLevelId() +"]}");
        hiddenLowerLevelDescription.setValueBinding("value",hidLowerLevelValDesc);*/
        
        this.dataHiddenLowerLevelValDesc.put(String.valueOf(benefitLevelValues.getLevelId()),benefitLevelValues.getLowerLevelDescValue().trim());
		//END Lower level description in the hidden 
		HtmlOutputText levelTerm = new HtmlOutputText();
		//levelTerm.setStyle("width:130px;");
		levelTerm.setId("Term"+i);
		if (null != benefitLevelValues.getTermDesc()) {
			levelTerm.setValue(benefitLevelValues.getTermDesc().trim());
		} else {
			levelTerm.setValue(WebConstants.EMPTY_STRING);
		}
		
        HtmlInputHidden hiddenForTerm = new HtmlInputHidden();
        hiddenForTerm.setId("hiddenTerm" + i);        
        hiddenForTerm.setValue(benefitLevelValues.getTermDesc().trim().replaceAll(",",", "));
        
        //Commented as part of stabilization release 3
        /*ValueBinding hidValTermDesc = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{contractCoverageBackingBean.dataHiddenValTerm["
						+ benefitLevelValues.getLevelId() +
						"]}");
        hiddenForTerm.setValueBinding("value",hidValTermDesc);*/ 
        
        this.dataHiddenValTerm.put(new Long(benefitLevelValues.getLevelId()),benefitLevelValues.getTermDesc().trim().replaceAll(",",", "));
		
		HtmlOutputText levelQualifier = new HtmlOutputText();
		//levelQualifier.setStyle("width:130px;");
		levelQualifier.setId("Qualifier"+i);
		if (null != benefitLevelValues.getQualifierDesc()) {
			levelQualifier.setValue(benefitLevelValues.getQualifierDesc()
					.trim());
		} else {
			levelQualifier.setValue(WebConstants.EMPTY_STRING);
		}
		
		HtmlInputHidden hiddenForQualifier = new HtmlInputHidden();
		hiddenForQualifier.setId("hiddenQualifier" + i);        
		if(null != benefitLevelValues.getQualifierDesc()){
			hiddenForQualifier.setValue(benefitLevelValues.getQualifierDesc().trim());
		}else{
			hiddenForQualifier.setValue("");
		}
		
		//  Commented as part of stabilization release 4
        /*ValueBinding hidValQualifier = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{contractCoverageBackingBean.dataHiddenValQualifier["
						+ benefitLevelValues.getLevelId() +
						"]}");
        hiddenForQualifier.setValueBinding("value",hidValQualifier);*/
		if(null != benefitLevelValues.getQualifierDesc()){
			this.dataHiddenValQualifier.put(new Long(benefitLevelValues.getLevelId()),benefitLevelValues.getQualifierDesc().trim());
		}else{
			this.dataHiddenValQualifier.put(new Long(benefitLevelValues.getLevelId()),"");
		}
        //Level Frequency Value
        HtmlInputText levelFrequency = new HtmlInputText();
		levelFrequency.setSize(3);
		levelFrequency.setMaxlength(3);
		levelFrequency.setId("lineFreqValue" + i);
		levelFrequency.setStyleClass("formInputField");
		levelFrequency.setStyle("width:30px;");
		levelFrequency.setValue(benefitLevelValues.getFrequencyDesc());
		levelFrequency.setOnkeypress("return isNum(event);");
		levelFrequency.setOnchange("return descriptionChange(this)");
		//Set the value to the map
		ValueBinding levelFreqValueBind = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{contractCoverageBackingBean.lineFreqValueMap["
								+ benefitLevelValues.getLevelId() + 
								"]}");
		levelFrequency.setValueBinding("value", levelFreqValueBind);
		levelFrequency.setDisabled(benefitLevelValues.isLevelHideStatus());
		//Hidden Frequency is added to increase the perfomance
		HtmlInputHidden hiddenLevelFreqValue = new HtmlInputHidden();

		hiddenLevelFreqValue.setId("hiddenLevelFreqValue"+ i);
		hiddenLevelFreqValue.setValue(benefitLevelValues.getFrequencyDesc());
		//Commented as part of stabilization release to avoid binding 5
			/*ValueBinding valForhiddenLevelFreq = FacesContext
					.getCurrentInstance().getApplication().createValueBinding(
							"#{contractCoverageBackingBean.hiddenLineFreqValueMap["
									+ benefitLevelValues.getLevelId() + 
									"]}");
			hiddenLevelFreqValue.setValueBinding("value",
					valForhiddenLevelFreq);*/
		this.hiddenLineFreqValueMap.put(new Long(benefitLevelValues.getLevelId()),benefitLevelValues.getFrequencyDesc());
		//START Hidden Lower level frequency value
		HtmlInputHidden hiddenLowerLevelFreqValue = new HtmlInputHidden();
		hiddenLowerLevelFreqValue.setId("hiddenLowerLevelFreqValue"+ i);
		hiddenLowerLevelFreqValue.setValue(benefitLevelValues.getLowerLevelFrequencyValue());
		//Commented as part of stabilization release to avoid binding 6
		/*ValueBinding valForhiddenLowerLevelFreq = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{contractCoverageBackingBean.hiddenLowerLevelFreqValueMap["+ benefitLevelValues.getLevelId() + "]}");
		hiddenLowerLevelFreqValue.setValueBinding("value",valForhiddenLowerLevelFreq);
		//END Hidden Lower level frequency value
		 */		
		this.hiddenLowerLevelFreqValueMap.put(new Long(benefitLevelValues.getLevelId()),benefitLevelValues.getLowerLevelFrequencyValue());
		
		//Adding a blank space with "-"
		HtmlOutputLabel blankValue = new HtmlOutputLabel();
		blankValue.setId("blankValue"+RandomStringUtils.randomAlphanumeric(15));
		blankValue.setValue(" - ");
		//Adding Frequency & Qualifier to the Label
		HtmlOutputLabel levelFreqQualValue = new HtmlOutputLabel();
		levelFreqQualValue.setId("levelFreqQualValue"+RandomStringUtils.randomAlphanumeric(15));
   		//In case, If Frequency is zero, Frequency Level would not display
   		if(null !=benefitLevelValues.getFrequencyDesc()
   				&& !WebConstants.EMPTY_STRING.equalsIgnoreCase(benefitLevelValues.getFrequencyDesc())
   				&& Integer.parseInt(benefitLevelValues.getFrequencyDesc())>0){
   			//Checking whether mode is Edit or View to display Frequency   			
   	        if (!WebConstants.MNDT_TYPE.equals(getContractSession().getContractKeyObject().contractType) && isViewMode()) {
   	        	HtmlOutputText levelOutputFreqQual = new HtmlOutputText();
   	        	levelOutputFreqQual.setValue(benefitLevelValues.getFrequencyDesc()+" - "+levelQualifier.getValue());
   	        	levelFreqQualValue.getChildren().add(levelOutputFreqQual);
   	        }else{
   	   			levelFreqQualValue.getChildren().add(levelFrequency);
   	   			levelFreqQualValue.getChildren().add(blankValue);
   	    		levelFreqQualValue.getChildren().add(levelQualifier);
   	    		levelFreqQualValue.getChildren().add(hiddenLevelFreqValue);
   	    		levelFreqQualValue.getChildren().add(hiddenForQualifier);
   	    		levelFreqQualValue.getChildren().add(hiddenLowerLevelFreqValue);
   	        }
   		}else{
   	    		levelFreqQualValue.getChildren().add(levelQualifier);
   	        }
		//End - Frequency Enhancement
			
		/*End CARS*/
		HtmlOutputText levelPVA = new HtmlOutputText();
		//levelPVA.setStyle("width:70px;");
		levelPVA.setValue(" ");

		//        HtmlOutputText levelDataType = new HtmlOutputText();
		//        levelDataType.setStyle("width:90px;");
		//        levelDataType.setValue(" ");

		HtmlOutputText levelBnftValue = new HtmlOutputText();
		levelBnftValue.setValue(" ");
		//levelBnftValue.setStyle("width:80px;");

		HtmlOutputText levelReference = new HtmlOutputText();
		levelReference.setValue(" ");
		//levelReference.setStyle("width:175px;");

        HtmlInputHidden hiddenLevelId = new HtmlInputHidden();
        hiddenLevelId.setId("hiddenLevelId" + i);
        hiddenLevelId.setValue(new Integer(benefitLevelValues.getLevelId()));
        // Change start
        // set the value to the map
        
        //Commented as part of stabilization release to avoid binding 7
        /*ValueBinding levelIdValBind = FacesContext.getCurrentInstance()
                .getApplication().createValueBinding(
                        "#{contractCoverageBackingBean.levelIdMap[" +benefitLevelValues.getLevelId()+ "]}");
        hiddenLevelId.setValueBinding("value", levelIdValBind);*/
        // change end
		
		HtmlOutputText noteImg = new HtmlOutputText();
		noteImg.setValue(" ");
		//noteImg.setStyle("width:65px;");

		HtmlOutputText dummyText = new HtmlOutputText();
		//dummyText.setStyle("width:90px;");
		levelPVA.setValue(" ");
		
        /*HtmlOutputLabel lblImage = new HtmlOutputLabel();*/
        HtmlOutputLabel label = new HtmlOutputLabel(); 
        label.setId("lblImage"+RandomStringUtils.randomAlphanumeric(15));
        label.setFor("levelDesc" + i);
       // label.setId("lblImage" + i);
		
		
		HtmlCommandButton addTierButton = new HtmlCommandButton();
		if("Y".equals(benefitLevelValues.getIsTierDefExist())){
			//addTierButton.setId("addTierButton"+i);
			addTierButton.setId("addTierButton"+RandomStringUtils.randomAlphanumeric(15));
			addTierButton.setImage("../../images/add.gif");
	        addTierButton.setAlt("Add To Tier");
	        addTierButton.setTitle("Add To Tier");
	        	//if("Y".equals(benefitLevelValues.getIsTierDefExist())){
				
				addTierButton.setOnclick("return false;");
		        //addTierButton.setStyle("width:55px;");
		        int benefitDefinitionId = ((Integer)getSession()
			            .getAttribute("SESSION_BNFT_DEFN_ID_CONTRACT")).intValue();
		        String params = "temp="+Math.random()+ 
						"&entitySysId="+getContractKeyObject().getDateSegmentId()+
						"&productSysId="+getContractKeyObject().getProductId()+
						"&benefitComponentSysId="+getBenefitComponentIdFromSession()+
						"&benefitDefinitionSysId="+benefitDefinitionId+
						"&benefitDefinitionLevelId="+benefitLevelValues.getLevelId()+
						"&entityType=Contract";
		        addTierButton.setOnmousedown("return validateErr();");
				addTierButton.setOnclick("var retVal = contractTierDefinition_ewpd('../popups/benefitTierDefinitionsPopup.jsp'+getUrl()+'?"+params+"'," +
				"'contractCoverageForm:dummy','contractCoverageForm:dummy',2,2); return false;");
		       /* if(!isViewMode()){
		        	panel.getChildren().add(addTierButton);
				}*/
			//}
	
	        //addTierButton.setStyle("width:55px;");//
		}
		
		
		 if (!isViewMode()) {
        	
		 	label.getChildren().add(hiddenLevelId);

        }
		
		/*START CARS */
        //Frequency
		 HtmlOutputLabel lblDesc = new HtmlOutputLabel();
		 lblDesc.setId("lblDesc"+RandomStringUtils.randomAlphanumeric(15));
		lblDesc.getChildren().add(levelDesc);
		if(isEditMode() && !isViewMode() && !isPrintMode())
			lblDesc.getChildren().add(levelDescInputText);
		lblDesc.getChildren().add(hiddenForOutputDescription);
		lblDesc.getChildren().add(hiddenLowerLevelDescription);
		
		HtmlOutputLabel lblTerm = new HtmlOutputLabel();
		lblTerm.setId("lblTerm"+RandomStringUtils.randomAlphanumeric(15));
		lblTerm.getChildren().add(levelTerm);
		lblTerm.getChildren().add(hiddenForTerm);
		panel.getChildren().add(lblDesc);
		panel.getChildren().add(lblTerm);
		panel.getChildren().add(levelFreqQualValue);
		/*END CARS*/
		
		panel.getChildren().add(levelPVA);
		panel.getChildren().add(dummyText);
		panel.getChildren().add(levelBnftValue);
		panel.getChildren().add(levelReference);
		panel.getChildren().add(noteImg);
		if("Y".equals(benefitLevelValues.getIsTierDefExist())){
			if(!isViewMode()){
	        	panel.getChildren().add(addTierButton);
			}
		}else{
			if(!isViewMode()){
				
				/*panel.getChildren().add(lblImage);*/
				panel.getChildren().add(label);
			}
		}
	}
	
	public String refresh(){
		return "contractBenefitDefinitionUpdate";
	}
	/**
	 * This mothod will set the updated/given value to List
	 *
	 */
	private void setTier(){ 
    	modifiedTierLineIdList = new ArrayList();
    	modifiedTierCriteriaList = new ArrayList();
    	messagesList = new ArrayList();
    	tierDefinitionList = getContractSession().getBenefitTierDefinitionList();
    	lvlLineList = getContractSession().getBenefitTierLevelList();

    	
		/* START CARS */    
    	modifiedLevelIdList = new ArrayList();
		for (Iterator benLevelItr = lvlLineList.iterator(); benLevelItr.hasNext();) {
			BenefitLevel benefitLevel = (BenefitLevel) benLevelItr.next();
    		String levelId = new Integer(benefitLevel.getLevelId()).toString().trim();
    		String tierSysId = new Integer(benefitLevel.getTierSysId()).toString().trim();
    		String concatedKey = levelId.concat(WebConstants.COLON).concat(tierSysId);
    		//Frequency Start
    		Object oldFrequencyObj = hiddenLineFreqValueMapTier.get(concatedKey);
        	Object newFrequencyObj = lineFreqValueMapTier.get(concatedKey);
        	boolean isFrequencyValid = false;
        	boolean isLvlDescValid = false;
        	if(null !=newFrequencyObj && !WebConstants.EMPTY_STRING.equalsIgnoreCase(newFrequencyObj.toString())){
            	benefitLevel.setFrequencyDesc(newFrequencyObj.toString());
				boolean isNumber = WPDStringUtil.isNumber(newFrequencyObj.toString());
				if(isNumber){
	            	benefitLevel.setFrequencyId(new Integer(newFrequencyObj.toString()).intValue());
	            	isFrequencyValid = true;
				}
        	}
    		//New description value binded with input text
    		Object newDescriptionValue = dataHiddenValDescTier.get(concatedKey);
    		
    			//Description Change Start
    			int noOfTokens;
    			boolean isDescriptionTrunkated = false;
    			String truncatedLvlDesc = null;
            	Object oldDescriptionValue;
        		Object oldFrequencyValue;
        		Object newFrequencyValue;
        		Object termValue;
        		Object qualifierValue;
				Object lowerLevelTierFrequencyValue;
				Object lowerLevelTierDescriptionValue;
        		//old description value binded with outpur text
        		oldDescriptionValue = oldDescOutputTxtTier.get(concatedKey);
        		//frequency value binded with input hidden
        		oldFrequencyValue = hiddenLineFreqValueMapTier.get(concatedKey);
        		//frequency value binded with input text
        		newFrequencyValue = lineFreqValueMapTier.get(concatedKey);
        		//term value binded with input text
        		termValue = dataHiddenValTermTier.get(new Long(levelId.toString().trim()));
        		//qualifier value binded with input text
        		qualifierValue = dataHiddenValQualifierTier.get(new Long(levelId.toString().trim()));
        		lowerLevelTierFrequencyValue = hiddenTierLowerLevelFreqValueMap.get(concatedKey);
        		lowerLevelTierDescriptionValue = dataTierHiddenLowerLevelValDesc.get(concatedKey);
        		if(!StringUtil.isEmpty(newDescriptionValue)){
    			//Checking null all the object(description, term, qualifier, frequency)
    			if((!StringUtil.isEmpty(newDescriptionValue)) && (!StringUtil.isEmpty(qualifierValue)) && (!StringUtil.isEmpty(termValue)) 
    					&& (!StringUtil.isEmpty(oldFrequencyValue)) && (!StringUtil.isEmpty(newFrequencyValue)) && (WPDStringUtil.isNumber(newFrequencyValue.toString().trim()))){								
    				String description = newDescriptionValue.toString().toUpperCase().trim();
    				String term = termValue.toString().trim();
    				String qualifier = qualifierValue.toString().trim();
    				String frequency = oldFrequencyValue.toString().trim();
    				String changeDesc;
					//Fix aggregate qualifier start
    				term = WPDStringUtil.commaSeparatedString(term);
    				qualifier = WPDStringUtil.commaSeparatedString(qualifier);
					/*if(null != qualifier){
						StringTokenizer benefitQualifiers = new StringTokenizer(
								qualifier, BusinessConstants.COMMA);
						 noOfTokens = benefitQualifiers.countTokens();
						 boolean flag = true;
						 if(noOfTokens > 1){
						 	 for (int j = 0; j < noOfTokens; j++) {
		                        if (benefitQualifiers.hasMoreTokens()) {
		                            String benefitQualifer = benefitQualifiers.nextToken();
		                            if(flag == true){
		                            	qualifier = benefitQualifer.trim();
	                            		//Flag is set false to restrict the entry for the second time
	                            		flag = false;
	                            	}else{
	                            		//Setting the term description value after the comma seperation
	                            		qualifier = qualifier+WebConstants.SPACE_STRING+ benefitQualifer.trim();
	                            	}
		                        }
						 	 }   
						 }
					}
					//Fix aggregate qualifier End
					//Fix for aggregate term start
					if(null != term){
						StringTokenizer benefitTerms = new StringTokenizer(
								term, BusinessConstants.COMMA);
						 noOfTokens = benefitTerms.countTokens();
						 boolean flag = true;
						 if(noOfTokens > 1){
						 	 for (int j = 0; j < noOfTokens; j++) {
		                        if (benefitTerms.hasMoreTokens()) {
		                            String benefitTerm = benefitTerms.nextToken();
		                            if(flag == true){
		                            	term = benefitTerm.trim();
	                            		//Flag is set false to restrict the entry for the second time
	                            		flag = false;
	                            	}else{
	                            		//Setting the term description value after the comma seperation
	                            		term = term+WebConstants.SPACE_STRING+ benefitTerm.trim();
	                            	}
		                        }
						 	 }   
						 }
					}*/
					//Fix for aggregate term end
    				//checking if the frequency value is 1
    				if(frequency.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)){									
    					//description is combination of term qualifier and frequency
    	            	changeDesc  = term+WebConstants.PER_STRING+qualifier;
    	            }else{
    	            	changeDesc  = term+WebConstants.PER_STRING+frequency+WebConstants.SPACE_STRING+qualifier;
    	            }
					if(changeDesc.length() > 32){
						changeDesc = changeDesc.substring(0,32).trim();
					}
					if(description.length() > 32){
						description = description.substring(0,32).trim();
					}
    				//Compares the old description and new description
    	            if(description.equalsIgnoreCase(changeDesc)){
    	            	if(!(frequency.equalsIgnoreCase(newFrequencyValue.toString().trim()))){
    	            		int frequencyValue = new Integer(newFrequencyValue.toString().trim()).intValue();
    	            		frequency = frequencyValue+WebConstants.EMPTY_STRING;
	    	            	//frequency = newFrequencyValue.toString().trim();
	    	            	if(frequency.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)){									
	    						//description is combination of term qualifier and frequency
	    	                	changeDesc  = term+WebConstants.PER_STRING+qualifier;
	    	                }else{
	    	                	changeDesc  = term+WebConstants.PER_STRING+frequency+WebConstants.SPACE_STRING+qualifier;
	    	                }//Compares the old description and new description
		                	//Keeping only 32 characters, while level description is more than 32 characters
		                	if(null !=changeDesc && changeDesc.length()>32){
		                		changeDesc = changeDesc.substring(0,32).trim();
		                		isDescriptionTrunkated = true;
		                	}
	    	        		benefitLevel.setLevelDesc(changeDesc);
    	            	}else{
    	            		description = newDescriptionValue.toString().toUpperCase().trim();
    	                	//Keeping only 32 characters, while level description is more than 32 characters
    	                	if(null !=description && description.length()>32){
    	                		description = description.substring(0,32).trim();
    	                		isDescriptionTrunkated = true;
    	                	}
        	        		benefitLevel.setLevelDesc(description);
    	            	}
    	            }else{
    	            	if(lowerLevelTierFrequencyValue.toString().toUpperCase().trim().equals(newFrequencyValue.toString().toUpperCase().trim())){
    	            		String lvlDescriptionValue = lowerLevelTierDescriptionValue.toString().toUpperCase().trim();
							if(lvlDescriptionValue.length() > 32){
								lvlDescriptionValue = lvlDescriptionValue.substring(0,32).trim();
								benefitLevel.setLevelDesc(lvlDescriptionValue);
		                		isDescriptionTrunkated = true;
							}
							benefitLevel.setLevelDesc(lvlDescriptionValue);
    	            	}else{
	    	            	description = newDescriptionValue.toString().toUpperCase().trim();
		                	//Keeping only 32 characters, while level description is more than 32 characters
		                	if(null !=description && description.length()>32){
		                		description = description.substring(0,32).trim();
		                		isDescriptionTrunkated = true;
		                	}
	    	        		benefitLevel.setLevelDesc(description);
    	            	}
    	            }
    			}else{
                	//Keeping only 32 characters, while level description is more than 32 characters
    				String custmLvlDesc = newDescriptionValue.toString().toUpperCase().trim();
                	if(null !=newDescriptionValue.toString() && newDescriptionValue.toString().length()>32){
                		custmLvlDesc = custmLvlDesc.substring(0,32).trim();
                		isDescriptionTrunkated = true;
                	}
	        		benefitLevel.setLevelDesc(custmLvlDesc);
    			}
        		isLvlDescValid = true;
        		if(isDescriptionTrunkated){
        			truncatedLvlDesc = benefitLevel.getLevelDesc();
        			InformationalMessage informationalMessage = new InformationalMessage(WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_LENGTH);
        			informationalMessage.setParameters(new String[] { truncatedLvlDesc });
        			messagesList.add(informationalMessage);
        			isDescriptionTrunkated = false;
        		}
    		}    		
    		//BenefitLine benefitLine = (BenefitLine) benLineItr.next();
    		List benefitLines = new ArrayList(benefitLevel.getBenefitLines().size());
    		for(int i=0; i<benefitLevel.getBenefitLines().size();i++){
    			BenefitLine benefitLine = (BenefitLine) benefitLevel.getBenefitLines().get(i);
    			if(isLvlDescValid){
        			//benefitLine.setLevelDesc(newDescriptionValue.toString());
    				benefitLine.setLevelDesc(benefitLevel.getLevelDesc());
    			}
    			if(isFrequencyValid){
    				benefitLine.setFrequencyValue(new Integer(newFrequencyObj.toString()).intValue());
    			}
    			benefitLines.add(benefitLine);
    		}
    		benefitLevel.setBenefitLines(benefitLines);
    		//Message shown on saving benefit level by updating the frequency value for tiered benefit level from Contract 
    		if(null != oldFrequencyObj && null !=newFrequencyObj
					&& !WebConstants.EMPTY_STRING.equalsIgnoreCase(newFrequencyObj.toString())
					&& WPDStringUtil.isNumber(newFrequencyObj.toString())){
    			//isNumber = WPDStringUtil.isNumber(newFrequencyObj.toString());
    			int newFreq = Integer.parseInt(newFrequencyObj.toString());
    			int oldFreq = Integer.parseInt(oldFrequencyObj.toString());
    			if(oldFreq != newFreq || !((benefitLevel.getLevelDesc()).equalsIgnoreCase(oldDescriptionValue.toString().trim())))
    				modifiedLevelIdList.add(benefitLevel);
    		}
		}
		/* END CARS */
    	
    	for (Iterator defIterator = tierDefinitionList.iterator(); defIterator.hasNext();) {
    		BenefitTierDefinition tierDefinition = (BenefitTierDefinition) defIterator.next();
    		for (Iterator tierBoIterator = tierDefinition.getBenefitTiers().iterator(); tierBoIterator.hasNext();) {
				BenefitTier benefitTier = (BenefitTier) tierBoIterator.next();
				for (Iterator criteriaIterator = benefitTier.getBenefitTierCriteriaList().iterator(); criteriaIterator.hasNext();) {
					BenefitTierCriteria tierCriteria = (BenefitTierCriteria) criteriaIterator.next();
					String key = formKeyforMap(tierDefinition.getBenefitTierDefinitionSysId(), benefitTier.getBenefitTierSysId(), tierCriteria.getBenefitTierCriteriaSysId());
					String inputCriteriaValue = (String)getTierCriteriaMap().get(key)!=null ? (String)getTierCriteriaMap().get(key) :"";
					String oldCriteriaValue = tierCriteria.getBenefitTierCriteriaValue()!=null ? tierCriteria.getBenefitTierCriteriaValue() : "";
					if(! (tierCriteria.getBenefitTierCriteriaValue().equalsIgnoreCase(inputCriteriaValue))){
						modifiedTierCriteriaList.add(tierCriteria.getBenefitTierCriteriaValue());
					}
					tierCriteria.setBenefitTierCriteriaValue(inputCriteriaValue);
				}				
			}			
		}  	
    	
    	for (Iterator benLevelItr = getLvlLineList().iterator(); benLevelItr.hasNext();) {
			BenefitLevel benefitLevel = (BenefitLevel) benLevelItr.next();
			List tierLineIdList = new ArrayList();
			for (Iterator benLineItr = benefitLevel.getBenefitLines().iterator(); benLineItr.hasNext();) {				
				BenefitLine benefitLine = (BenefitLine) benLineItr.next();
				
				String keyString = formKeyforMap(benefitLevel.getLevelId(), benefitLine.getLineSysId(), benefitLevel.getTierSysId());
				String newLineVal = (String)getTierLineValueMap().get(keyString)!=null?(String)getTierLineValueMap().get(keyString):"";
				String oldLineVal = benefitLine.getBenefitValue()!= null ? benefitLine.getBenefitValue():"";
					if(!(oldLineVal.equalsIgnoreCase(newLineVal))){
						modifiedTierLineIdList.add(oldLineVal);
						if(benefitLevel.getTierSysId()== benefitLine.getTierSysId()){
							tierLineIdList.add(benefitLine.getLineSysId()+"");
						}
						//changedTierLevelLineList.add(benefitLine.getTierSysId()+","+benefitLine.getLineSysId()+",");
					}
				benefitLine.setLineValue(newLineVal);
			}
			if(tierLineIdList.size()>0){
			tierSysIdLineIdMap.put(new Integer(benefitLevel.getTierSysId()), tierLineIdList);
			}
			
		}
    		
    }
	/**
	 * This method saves the benefit definitions
	 * @return
	 */

	//gets the value in the test box
	public String save() {		
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","ContractCoverageBackingBean","save()"));
		
		//gets the level ids to delete which contains the ids with ~ appended
		boolean validationFlag = true;
		String levelDesc = null;
		boolean isTierLineValueModified = false;
		boolean isTierLevelValueModified = false;
		boolean isCriteriaValueModified = false;
		List messages = new ArrayList();
		boolean descriptionValidationFlag = true;
	   /*START CARS */
		List benefitLineFreqVOs = new ArrayList();
		Set levelFreqValKeySet = this.getLineFreqValueMap().keySet();
		boolean levelIdChecked = false;
		boolean levelDescChecked = false;
		String truncatedLvlDesc = null;
        List lvlDescMessages = new ArrayList();
       /*END CARS */
		Map changedLineTier=new HashMap();
		
		//As part of stabilization release		
		lineIdMap = (HashMap) getContractSession().getLineIdMap();		
		levelIdsMap = (HashMap) getContractSession().getLevelIdsMapFromSession();		
		
		
		//For Admin Method validation 
		List changedLines = new ArrayList(this.getLineIdMap() == null ? 0
				: this.getLineIdMap().size());
		
		List changedTierLineIds = new ArrayList(this.getTierLineIdMap() == null ? 0
				: this.getLineIdMap().size());
		
		List changedTierIds = new ArrayList();
		
		//    	 **Change
		Set lineIdKeySet = this.getLineIdMap().keySet();

		// lineId Iterator
		Iterator lineIdIterator = lineIdKeySet.iterator();
		// iterate the lineIdKeySet
		while (lineIdIterator.hasNext()) {
			// get the lineIdKey
			// get the levelId separately form the lineLevelVal
			Object lineIdKey = lineIdIterator.next();
			String levelLineVal = this.getLineIdMap().get(lineIdKey).toString();
			String appendedBnftVal = null;
			String appendedLineVal = null;
			String appendedDataTypeId = null;
			String appendedLevelDesc = null;
			if (null != levelLineVal && !levelLineVal.equals("")) {
				StringTokenizer tokenizer = new StringTokenizer(levelLineVal,
						":");
				int i = 0;
				while (tokenizer.hasMoreTokens()) {
					String tokens = tokenizer.nextToken();
					if (i == 0) {
						appendedLineVal = tokens;
					} else if (i == 1) {
						appendedBnftVal = tokens;
					} else if (i == 2) {
						appendedDataTypeId = tokens;
					} else if (i == 3) {
						appendedLevelDesc = tokens;
					}
					i++;
				}
			}

			if (null != benefitValueMap && null != appendedLineVal
					&& null != benefitValueMap.get(appendedLineVal)) {

				String prevVal = appendedBnftVal;
				appendedBnftVal = (benefitValueMap.get(appendedLineVal)).toString();
				if(prevVal == null || prevVal.trim().length() == 0 || "null".equalsIgnoreCase(prevVal.trim())){
					prevVal = "";
				}
				if(appendedBnftVal == null || appendedBnftVal.trim().length() == 0 && "null".equalsIgnoreCase(appendedBnftVal.trim())){
					appendedBnftVal = "";
				}
				if(!(prevVal.equalsIgnoreCase(appendedBnftVal))) {
					isTierLineValueModified= true;					
				}
				
				
				if (((prevVal == null || "".equalsIgnoreCase(prevVal.trim()) || "null".equalsIgnoreCase(prevVal.trim()))
						&& appendedBnftVal != null && appendedBnftVal.trim().length() > 0)
						|| ((appendedBnftVal == null || "".equalsIgnoreCase(appendedBnftVal.trim()))
								&& prevVal != null && prevVal.trim().length() > 0 && !"null".equalsIgnoreCase(prevVal.trim()))){
				if (this.getLevelIdsMap() != null
							&& this.getLevelIdsMap().get(
									new Long(appendedLineVal)) != null) {
						String val[] = ("" + this.getLevelIdsMap().get(
								new Long(appendedLineVal))).split(":");
						if (!changedLines.contains(val[0]))
							changedLines.add(val[0]);
					}
				}

			}
			// check whether the override value is Valid.
			if (!isValid(appendedDataTypeId, appendedBnftVal, appendedLevelDesc)) {
				//return "componentBenefitDefinition";
				validationFlag = false;
			}
		}

		/*START CARS */
		Set tierLineIdKeySet = this.getTierLineIdMap().keySet();
		Iterator tierLineIdIterator = tierLineIdKeySet.iterator();	
		
	
		Map tierLineMap = new HashMap();
		String prevBenefitTierSysId=null;

		while (tierLineIdIterator.hasNext()) {
			// get the lineIdKey
			Object lineIdKey = tierLineIdIterator.next();
			String levelLineVal = this.getTierLineIdMap().get(lineIdKey).toString();
			String appendedBnftVal = null;
			String appendedLevelVal = null;
			String appendedLineVal = null;
			String appendedDataTypeId = null;
			String appendedLevelDesc = null;
			String benefitTierSysId = null;
			if (null != levelLineVal && !levelLineVal.equals("")) {
				StringTokenizer tokenizer = new StringTokenizer(levelLineVal,":");
				int i = 0;
				while (tokenizer.hasMoreTokens()) {
					String tokens = tokenizer.nextToken();
					if (i == 0) {
						appendedLevelVal = tokens;
						
					} else if (i == 1) {
					    appendedLineVal = tokens;
					    
					} else if (i == 2) {
						appendedBnftVal = tokens;
					} else if (i == 3) {
						appendedDataTypeId = tokens;
					} else if (i == 4) {
						appendedLevelDesc = tokens;
					} else if (i == 5) {
					    benefitTierSysId = tokens;
					}
					i++;
				}
			}
			
			//Compare previously code value with current value
			if (null != tierLineValueMap && null != appendedLineVal
					&& null != tierLineValueMap.get(appendedLevelVal + ":" + appendedLineVal + ":" + benefitTierSysId)) {

				String prevVal = appendedBnftVal;
				appendedBnftVal = (tierLineValueMap.get(appendedLevelVal + ":" + appendedLineVal + ":" + benefitTierSysId)).toString();

				if(prevVal == null || prevVal.trim().length() == 0 || "null".equalsIgnoreCase(prevVal.trim())){
					prevVal = "";
				}
				if(appendedBnftVal == null || appendedBnftVal.trim().length() == 0 && "null".equalsIgnoreCase(appendedBnftVal.trim())){
					appendedBnftVal = "";
				}
				if(!(prevVal.equalsIgnoreCase(appendedBnftVal))) {
					isTierLineValueModified= true;					
				}
				
				
				if (((prevVal == null || "".equalsIgnoreCase(prevVal.trim()) || "null".equalsIgnoreCase(prevVal.trim()))
						&& appendedBnftVal != null && appendedBnftVal.trim().length() > 0)
						|| ((appendedBnftVal == null || "".equalsIgnoreCase(appendedBnftVal.trim()))
								&& prevVal != null && prevVal.trim().length() > 0 && !"null".equalsIgnoreCase(prevVal.trim()))){
				    //transfer the changed tier data and tiered level id to the list.
					
					if (!changedTierLineIds.contains(appendedLevelVal))
					    changedTierLineIds.add(appendedLevelVal); 
					if (!changedTierIds.contains(benefitTierSysId)){
					    changedTierIds.add(benefitTierSysId);
					   // benefitTierSysIdList.add(benefitTierSysId+","+appendedLineVal);
					}
				}

				}	
			
		}
	/*END CARS*/			
	/*START CARS*/
		//Stabilization release
		dataHiddenValTerm = getContractSession().getDataHiddenValTermMap();		
		hiddenLineFreqValueMap = (HashMap) getContractSession().getHiddenLineFreqValueMap();
		hiddenLowerLevelFreqValueMap =(HashMap) getContractSession().getHiddenLowerLevelFreqValueMap();
				
		Set levelIdKeySet = this.getDataHiddenValTerm().keySet();
		// lineId Iterator
		Iterator levelIdIterator = levelIdKeySet.iterator();
		// iterate the lineIdKeySet
		HashMap changeLvlDescMap = new HashMap();
		
		while(levelIdIterator.hasNext()){
			// get the levelId separately form the lineLevelVal
			Object levelIdKey = levelIdIterator.next();
			
			String appendedLevelDesc = this.getDataHiddenValDesc().get(new Long(levelIdKey.toString().trim())).toString();
			
			BenefitLineVO benefitLineValues = new BenefitLineVO();
			// levelFreqVal Iterator
			Iterator levelFreqValIterator = levelFreqValKeySet
					.iterator();
			// level Frequency Value
			while (levelFreqValIterator.hasNext()) {
				// get the levelIdFreqValKey
				Object levelIdFreqValKey = levelFreqValIterator
						.next();
				//Compare the lineId key with the lineLevelFreqValKey
				if (levelIdKey.toString().equals(levelIdFreqValKey.toString())) {
					//Check whether the override value is Valid.
					if (null != this.getLineFreqValueMap()
							.get(levelIdFreqValKey)) {
						//Frequency Validation should be checked once per level
							if (!isValidFrequency(this.getLineFreqValueMap().get(levelIdFreqValKey).toString(), 
									appendedLevelDesc, validationFlag)) {
								validationFlag = false;
							}
							levelIdChecked = true;
						String val = this.getLineFreqValueMap().get(
								levelIdFreqValKey).toString();
						if(null != val && !"".equals(val)){
							boolean isNumber = WPDStringUtil.isNumber(this
									.getLineFreqValueMap().get(
											levelIdFreqValKey).toString());
							if(isNumber){
								int freqVal = Integer.parseInt(this
										.getLineFreqValueMap().get(
												levelIdFreqValKey).toString());
								benefitLineValues.setOverridedFreqValue(freqVal+"");
								//frequency value binded with input hidden
								
								int oldFreqVal = Integer.parseInt(this
										.getHiddenLineFreqValueMap().get(
												levelIdFreqValKey).toString());
								
								if(oldFreqVal != freqVal){
									isTierLevelValueModified= true;									
								}
							}											
						}
					}
					break;
				}
			}
			//End -- Saving the Frequency			

//			Description Change Start
			int noOfTokens;
	    	boolean isDescriptionTrunkated = false;
			Object descriptionValue;
			Object oldFrequencyValue;
			Object newFrequencyValue;
			Object termValue;
			Object qualifierValue;
			Object lowerLevelFrequencyValue;
			Object lowerLevelDescriptionValue;
			//description value binded with input text
			descriptionValue = dataHiddenValDesc.get(new Long(levelIdKey.toString().trim()));
			//frequency value binded with input hidden
			oldFrequencyValue = hiddenLineFreqValueMap.get(new Long(levelIdKey.toString().trim()));
			//frequency value binded with input text
			newFrequencyValue = lineFreqValueMap.get(new Long(levelIdKey.toString().trim()));
			//term value binded with input text
			//stabilization release change
						
			
			termValue = dataHiddenValTerm.get(new Long(levelIdKey.toString().trim()));
						
			//qualifier value binded with input text
			dataHiddenValQualifier = getContractSession().getDataHiddenValQualifierMap();
			
			
			qualifierValue = dataHiddenValQualifier.get(new Long(levelIdKey.toString().trim()));
			
			
			lowerLevelFrequencyValue = hiddenLowerLevelFreqValueMap.get(new Long(levelIdKey.toString().trim()));
			
			//Stabilization release 
			dataHiddenLowerLevelValDesc = (HashMap) getContractSession().getDataHiddenLowerLevelValDescMap();
						
			lowerLevelDescriptionValue = dataHiddenLowerLevelValDesc.get(new Long(levelIdKey.toString().trim()));							
			//Checking null all the object(description, term, qualifier, frequency)
			if((!StringUtil.isEmpty(descriptionValue)) && (!StringUtil.isEmpty(qualifierValue)) && (!StringUtil.isEmpty(termValue)) 
					&& (!StringUtil.isEmpty(oldFrequencyValue)) && (!StringUtil.isEmpty(newFrequencyValue)) && (WPDStringUtil.isNumber(newFrequencyValue.toString().trim()))){								
				String description = descriptionValue.toString().toUpperCase().trim();
				String term = termValue.toString().trim();
				String qualifier = qualifierValue.toString().trim();
				String frequency = oldFrequencyValue.toString().trim();
				String changeDesc;
				//Fix aggregate qualifier start
				term = WPDStringUtil.commaSeparatedString(term);
				qualifier = WPDStringUtil.commaSeparatedString(qualifier);
				/*if(null != qualifier){
					StringTokenizer benefitQualifiers = new StringTokenizer(
							qualifier, BusinessConstants.COMMA);
					 noOfTokens = benefitQualifiers.countTokens();
					 boolean flag = true;
					 if(noOfTokens > 1){
					 	 for (int j = 0; j < noOfTokens; j++) {
	                        if (benefitQualifiers.hasMoreTokens()) {
	                            String benefitQualifer = benefitQualifiers.nextToken();
	                            if(flag == true){
	                            	qualifier = benefitQualifer.trim();
                            		//Flag is set false to restrict the entry for the second time
                            		flag = false;
                            	}else{
                            		//Setting the term description value after the comma seperation
                            		qualifier = qualifier+WebConstants.SPACE_STRING+ benefitQualifer.trim();
                            	}
	                        }
					 	 }   
					 }
				}
				//Fix aggregate qualifier End
				//Fix for aggregate term start
				if(null != term){
					StringTokenizer benefitTerms = new StringTokenizer(
							term, BusinessConstants.COMMA);
					 noOfTokens = benefitTerms.countTokens();
					 boolean flag = true;
					 if(noOfTokens > 1){
					 	 for (int j = 0; j < noOfTokens; j++) {
	                        if (benefitTerms.hasMoreTokens()) {
	                            String benefitTerm = benefitTerms.nextToken();
	                            if(flag == true){
	                            	term = benefitTerm.trim();
                            		//Flag is set false to restrict the entry for the second time
                            		flag = false;
                            	}else{
                            		//Setting the term description value after the comma seperation
                            		term = term+WebConstants.SPACE_STRING+ benefitTerm.trim();
                            	}
	                        }
					 	 }   
					 }
				}*/
				//Fix for aggregate term end
				//checking if the frequency value is 1
				if(frequency.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)){									
					//description is combination of term qualifier and frequency
	            	changeDesc  = term+WebConstants.PER_STRING+qualifier;
	            }else{
	            	changeDesc  = term+WebConstants.PER_STRING+frequency+WebConstants.SPACE_STRING+qualifier;
	            }
				if(description.length() > 32){
					description = description.substring(0,32).trim();
				}
				if(changeDesc.length() > 32){
					changeDesc = changeDesc.substring(0,32).trim();
				}
				//Compares the old description and new description
	            if(description.equalsIgnoreCase(changeDesc)){
	            	if(!(frequency.equalsIgnoreCase(newFrequencyValue.toString().trim()))){
	            		int frequencyValue = new Integer(newFrequencyValue.toString().trim()).intValue();
	            		frequency = frequencyValue+WebConstants.EMPTY_STRING;
		            	//frequency = newFrequencyValue.toString().trim();
		            	if(frequency.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)){									
							//description is combination of term qualifier and frequency
		                	changeDesc  = term+WebConstants.PER_STRING+qualifier;
		                }else{
		                	changeDesc  = term+WebConstants.PER_STRING+frequency+WebConstants.SPACE_STRING+qualifier;
		                }//Compares the old description and new description			
	                	//Keeping only 32 characters, while level description is more than 32 characters
	                	if(null !=changeDesc && changeDesc.length()>32){
	                		changeDesc = changeDesc.substring(0,32);
	                		isDescriptionTrunkated = true;
	                	}
		            	benefitLineValues.setOverridedLvlDescValue(changeDesc);
	            	}else{
		            	description = descriptionValue.toString().toUpperCase().trim();
	                	//Keeping only 32 characters, while level description is more than 32 characters
	                	if(null !=description && description.length()>32){
	                		description = description.substring(0,32);
	                		isDescriptionTrunkated = true;
	                	}
		            	benefitLineValues.setOverridedLvlDescValue(description);	            		
	            	}
	            }else{
	            	if(null != lowerLevelDescriptionValue && (lowerLevelFrequencyValue.toString().toUpperCase().trim().equals(newFrequencyValue.toString().toUpperCase().trim()))){
	            		String lvlDescriptionValue = lowerLevelDescriptionValue.toString().toUpperCase().trim();
						if(lvlDescriptionValue.length() > 32){
							lvlDescriptionValue = lvlDescriptionValue.substring(0,32).trim();
							benefitLineValues.setOverridedLvlDescValue(lvlDescriptionValue);
	                		isDescriptionTrunkated = true;
						}
						benefitLineValues.setOverridedLvlDescValue(lvlDescriptionValue);
	            	}else{
		            	description = descriptionValue.toString().toUpperCase().trim();
	                	//Keeping only 32 characters, while level description is more than 32 characters
	                	if(null !=description && description.length()>32){
	                		description = description.substring(0,32);
	                		isDescriptionTrunkated = true;
	                	}
		            	benefitLineValues.setOverridedLvlDescValue(description);
		            	//isTierLevelValueModified= true;		            	
	            	}
	            }
			}else{
				String lvlDescriptionValue = descriptionValue.toString().toUpperCase().trim();
            	//Keeping only 32 characters, while level description is more than 32 characters
            	if(lvlDescriptionValue.length() > 32){
					lvlDescriptionValue = lvlDescriptionValue.substring(0,32);
            		isDescriptionTrunkated = true;
            	}
				benefitLineValues.setOverridedLvlDescValue(lvlDescriptionValue);
			}
			if(null != descriptionValue && !WebConstants.EMPTY_STRING.equalsIgnoreCase(descriptionValue.toString())){				
				if(isDescriptionTrunkated){
					truncatedLvlDesc = benefitLineValues.getOverridedLvlDescValue();
					InformationalMessage informationalMessage = new InformationalMessage(WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_LENGTH);
					informationalMessage.setParameters(new String[] { truncatedLvlDesc });
					lvlDescMessages.add(informationalMessage);
					addAllMessagesToRequest(lvlDescMessages);
					levelDescChecked = true;
				}
			}else if(descriptionValidationFlag){//set error message description value can not be empty
				descriptionValidationFlag = false;
				validationMessages.add(new ErrorMessage(WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_REQUIRED));
				validationFlag = false;
			}
			//Description Change End						  		        		
			//Sets the flag true if the frequency value chnaged
			if((null != oldFrequencyValue) && (null != newFrequencyValue)){
				if(!(oldFrequencyValue.equals(newFrequencyValue))){
					isLineValueModified=true;
				}
			}
			//adding frequency & level description to the benefitLineFreqVOs
			if(null!=benefitLineValues.getOverridedLvlDescValue()
					&& !"".equalsIgnoreCase(benefitLineValues.getOverridedLvlDescValue()))
				changeLvlDescMap.put(levelIdKey.toString().trim(),benefitLineValues.getOverridedLvlDescValue());
			
			benefitLineFreqVOs.add(benefitLineValues);
		}
		
		/*END CARS*/
			
		// **End    	
		List benefitLineVOs = new ArrayList();
		Set keys = benefitValueMap.keySet();
		Iterator valueIter = keys.iterator();
		while (valueIter.hasNext()) {
			String valueElement = (String) valueIter.next();

			String valueOne = (String) benefitValueMap.get(valueElement);
			String value = valueOne.toUpperCase();
			//creates a new benefit Line VO to get the overridden values
			BenefitLineVO benefitLineValues = new BenefitLineVO();

			/*START CARS*/
			Iterator bnftLineFreqItr = benefitLineFreqVOs.iterator();
			while(bnftLineFreqItr.hasNext()){
				BenefitLineVO bnftLineVal = (BenefitLineVO) bnftLineFreqItr.next();
				if (null != valueElement){
					int benefitLineId = (new Integer(valueElement)).intValue();
					//Getting Coresponding LineId against a levelId
					if (this.getLevelIdsMap() != null
							&& this.getLevelIdsMap().get(
									new Long(benefitLineId)) != null) {
						String val[] = ("" + this.getLevelIdsMap().get(
								new Long(benefitLineId))).split(":");
						
						if (null != val && val.length>1 && !WebConstants.EMPTY_STRING.equalsIgnoreCase(val[1])
								&& WPDStringUtil.isNumber(val[1]) && WPDStringUtil.isNumber(val[0])){
							int bnftLineId = Integer.parseInt(val[1]);
														
							//Fetching the coresponding lineId from the benefitLineVOs list to set benefitValue
							benefitLineValues.setBenefitLvlId(Integer.parseInt(val[0]));
							if(benefitLineId==bnftLineId){
								//Getting the Level ID
								int levelId = Integer.parseInt(val[0]);
								
								if(null != this.getLineFreqValueMap() && null != this.getLineFreqValueMap().get(
										new Long(levelId))){
									String freqVal = this.getLineFreqValueMap().get(
											new Long(levelId)).toString();
									//Setting the frequency
									benefitLineValues.setOverridedFreqValue(freqVal);
								}
								if(null != this.getDataHiddenValDesc()&& null != this.getDataHiddenValDesc().get(
										new Long(levelId))){
									String lvlDesc = this.getDataHiddenValDesc().get(
											new Long(levelId)).toString();
									//Setting the level Description
									String lvlIdKey = levelId+"";
									String freqLvlDesc =null;
									if(changeLvlDescMap.containsKey(lvlIdKey))
										freqLvlDesc = (String) changeLvlDescMap.get(lvlIdKey);
									if(null==freqLvlDesc){
										benefitLineValues.setOverridedLvlDescValue(lvlDesc);
									}else{
										benefitLineValues.setOverridedLvlDescValue(freqLvlDesc);
									}
								}
								break;
							}//End
						}
					}					
					//End
				}
			}
			/*END CARS*/
			
			if (null != valueElement)
				benefitLineValues.setBenefitLineId((new Integer(valueElement))
						.intValue());
			benefitLineValues.setOverridedValue(value.trim());

			//adds the benefitLineVO to the BenefitLine List
			benefitLineVOs.add(benefitLineValues);
		}
			
		if(null != getTierCriteriaMap()
			&& getTierCriteriaMap().size() != 0 
			&& null != getTierLineValueMap() && getTierLineValueMap().size() != 0
        	&& null != getContractSession().getBenefitTierDefinitionList()
        	&& null != getContractSession().getBenefitTierLevelList()){
			setTier();
        	if(!validateTiersForDatatype()){
        		validationFlag=false;	
        	}
        	if (!validateTiering())
            	validationFlag = false;
        	if(modifiedTierLineIdList.size()>0){
            	isTierLineValueModified = true;
            	
        	}
            if(modifiedTierCriteriaList.size()>0)
            	isCriteriaValueModified = true;
            /*START CARS*/
            if(modifiedLevelIdList.size()>0){
            	isTierLevelValueModified = true;            	
            }
            /*END CARS*/            

        	
		}
		if (validationFlag) {
			
			
			int benefitDefinitionId = ((Integer)getSession()
		            .getAttribute("SESSION_BNFT_DEFN_ID_CONTRACT")).intValue();			
			
			List oldBenefitList = (List)getSession().getAttribute("oldBenefitLineList");
			/*- Setting values from session to map */
			setValueToOldHashMap(oldBenefitList);
			/*- Setting values from the current details to map */
			setValueToNewHashMap(benefitLineVOs);	
			/*- Remove the list from session -*/
			getSession().removeAttribute("oldBenefitLineList");			
			
			//the request is made
			SaveContractBenefitDefinitionRequest saveContractBenefitDefinitionRequest = (SaveContractBenefitDefinitionRequest) this
					.getServiceRequest(ServiceManager.SAVE_CONTRACT_BENEFIT_DEFINITION_REQUEST);

			saveContractBenefitDefinitionRequest.setNotChangedLines(oldCodedLines);
			saveContractBenefitDefinitionRequest.setChangedLines(modifiedCodedLines);
			//the set of LineVOs is set to the request
			saveContractBenefitDefinitionRequest
					.setBenefitLinesList(benefitLineVOs);

			//        saveContractBenefitDefinitionRequest.setBenefitComponentId(2434);
			saveContractBenefitDefinitionRequest
					.setBenefitComponentId(getBenefitComponentIdFromSession());

			saveContractBenefitDefinitionRequest
					.setDateSegmentId(getContractKeyObject().getDateSegmentId());
			saveContractBenefitDefinitionRequest.setProductSysId(getContractKeyObject().getProductId());
			saveContractBenefitDefinitionRequest.setBenefitDefnSysId(benefitDefinitionId);
			
			saveContractBenefitDefinitionRequest.setChangedBenefitLines(appendChangedTieredLineIds(changedLines));
			
			if(null != getTierCriteriaMap() && getTierCriteriaMap().size() != 0 
					&& null != getTierLineValueMap() && getTierLineValueMap().size() != 0
            		&& null != getContractSession().getBenefitTierDefinitionList()
            			&& null != getContractSession().getBenefitTierLevelList()){
            	//saveTier(); 
            	if (modifiedTierLineIdList != null && modifiedTierLineIdList.size() > 0 &&
            			null != changedLines && changedLines.size()>0)
            	{
            		saveContractBenefitDefinitionRequest.setChangedBenefitLines(appendChangedTieredLineIds(changedLines));
    			}   

            	saveContractBenefitDefinitionRequest.setBenefitTierDefinitionList(tierDefinitionList);
            	saveContractBenefitDefinitionRequest.setBenefitTierLevelList(lvlLineList);

            	/* START CARS */
    	
            	saveContractBenefitDefinitionRequest.setChangedTierLineIds(changedTierLineIds);
            	saveContractBenefitDefinitionRequest.setChangedTierSysIds(changedTierIds);
            	//Added Krishnakumar 
            	//saveContractBenefitDefinitionRequest.setTierSysLineIdMap(tierSysIdLineIdMap);
            	/* END CARS */

            	super.removeTierDefinitionListFromSession();
            	super.removeTierLevalListFromSession();
            	super.removeTierDefWithPsvlListFromSession();
            	getSession().removeAttribute(WebConstants.TIER_CRITERIA_PSBL_VALUE_LIST);
            }
			
			if (saveContractBenefitDefinitionRequest.getChangedBenefitLines() != null 
					&& saveContractBenefitDefinitionRequest.getChangedBenefitLines().size() > 0) {
				saveContractBenefitDefinitionRequest
						.setBenefitComponentName((String) getSession()
								.getAttribute("BENEFIT_COMP_NAME"));
				saveContractBenefitDefinitionRequest
						.setBenefitSysId(getContractSession().getBenefitId());
			}
			//gets the response
			SaveContractBenefitDefinitionResponse saveContractBenefitDefinitionResponse = (SaveContractBenefitDefinitionResponse) executeService(saveContractBenefitDefinitionRequest);
			getRequest().setAttribute("RETAIN_Value", "");
			
			if(null!=saveContractBenefitDefinitionResponse){
				messages = saveContractBenefitDefinitionResponse.getMessages();         	
            	
            	
            	if(null != messages && !messages.isEmpty()){
            		InformationalMessage message = (InformationalMessage) messages.get(0);
            		if(null != message && null != message.getId()){
            			if(message.getId().trim().equals(BusinessConstants.MSG_PRDCT_BEN_DEFN_UPDATED)){
            				messages = new ArrayList();
            				/*START CARS*/
            				if(isCriteriaValueModified){
            					messages.add(new InformationalMessage("benefit.criteria.value.modified"));
            				}
            				if(isTierLevelValueModified ){
            					messages.add(new InformationalMessage("benefit.level.save.success"));
            				}
            				else{            				
	            				//if(isTierLineValueModified){
	            					messages.add(new InformationalMessage("benefit.line.value.modified"));
	            				//}
            				}
            				/*END CARS*/
            				/*if(isCriteriaValueModified){
            					messages.add(new InformationalMessage("benefit.level.save.success"));
            				}*/
            				//Adding Frequency/Level description messages
            				if(null != lvlDescMessages && lvlDescMessages.size()>0){
            					messages.addAll(lvlDescMessages);
            				}
            				//Displaying Information Message, If Level Description having more than 32 characters
            				/*if(isTieredLvlDescTruncated){
								lvlDescMessages = new ArrayList();
								InformationalMessage informationalMessage = new InformationalMessage(WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_LENGTH);
								informationalMessage.setParameters(new String[] { tieredTruncatedLvlDesc });
								lvlDescMessages.add(informationalMessage);
								messages.addAll(lvlDescMessages);
            				}*/
            				if(null != messagesList && messagesList.size()>0){
            					messages.addAll(messagesList);
            				}
            				//END	
            			}
            		}
            	}
            	addAllMessagesToRequest(messages);
			}
			
		} else {
			addAllMessagesToRequest(validationMessages);
			return "";
		}
		addAllMessagesToRequest(messages);
		/*WAS 6.0 migration changes - fix for avoiding duplicate message*/
		HttpServletRequest request = getRequest();
		List messagesList = (List)request.getAttribute("messages");
		 if(messagesList != null && !messagesList.isEmpty()) {
	        	for (Iterator iterator = messagesList.iterator(); iterator.hasNext();) {
					Message message = (Message) iterator.next();
					if(message.getId().equals(BusinessConstants.MSG_PRDCT_BEN_DEFN_UPDATED))
						iterator.remove();						
				}
	        }
		Logger.logInfo(th.endPerfLogging());
		return "contractBenefitDefinitionUpdate";
	
    }
	/**
	 * Retriving coded lines to save in session
	 * @param list
	 * @return list
	 */
	private List retriveCodedLinesFromList(List list){
		BenefitLine  benefitLine = null;
		List codedLines = new ArrayList();
		
		for(int i=0;i<list.size();i++){
			benefitLine = (BenefitLine) list.get(i);
			if(null != benefitLine.getBenefitValue() && !benefitLine.getBenefitValue().equals(""))
				codedLines.add(benefitLine);
		}
		return codedLines;
	}
	/**
	 * Setting values from list to a map
	 * @param list List from session
	 */
	private void setValueToOldHashMap(List list){
		BenefitLine  benefitLine = null;
		for(int i=0;i<list.size();i++){
			benefitLine = (BenefitLine) list.get(i);
			oldCodedLines.put(String.valueOf(benefitLine.getLineSysId()),benefitLine);
		}
	}
	/**
	 * Setting values from list to a map
	 * @param list List after modifing the lines
	 */
	private void setValueToNewHashMap(List list){
		BenefitLineVO benefitLineVO = null;
		for(int i=0;i<list.size();i++){
			benefitLineVO = (BenefitLineVO) list.get(i);
			modifiedCodedLines.put(String.valueOf(benefitLineVO.getBenefitLineId()),benefitLineVO);
		}
	}
	
	private boolean isValidFrequency(String value,String levelDesc,boolean validationFlag){
		boolean isValid = true;
		boolean isNumber = true;
		int dataType = 0;
		//String sysDataType = null;
		ErrorMessage errorMessage = null;
		
		if(null != value && !"".equals(value)){
			isNumber = WPDStringUtil.isNumber(value);

			//Checking that frequency value should not be less than 1
			if(isNumber){
				//Checking that frequency value should not be less than 1
				int freqVal = Integer.parseInt(value);

				if(freqVal==0 && isErrorNonTierFlag)
				{
					errorMessage = new ErrorMessage(
					WebConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_CORRECT);
					//if (null != levelDesc)
					//	errorMessage.setParameters(new String[] { dataTypeName,levelDesc });
					validationMessages.add(errorMessage);
					addAllMessagesToRequest(validationMessages);
					isValid = false;
					isErrorNonTierFlag = false;
				}
			}else{
				errorMessage = new ErrorMessage(
						BusinessConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_NUMBER);
            	//Checking null for the description. Sets the description to the 
            	//error message to state validation happen for that description
				if (null != levelDesc)
					errorMessage.setParameters(new String[] { levelDesc });
				validationMessages.add(errorMessage);
				addAllMessagesToRequest(validationMessages);
				isValid = false;				
			}
		}else{
			//Checking that Frequency can not be empty.
			errorMessage = new ErrorMessage(
					BusinessConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_EMPTY);
			if (null != levelDesc)
				errorMessage.setParameters(new String[] { levelDesc });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
			isValid = false;
		}		
		
		return isValid;
	}
	
	
	
	private boolean validateTiering(){    	
		tierDefinitionList = getContractSession().getBenefitTierDefinitionList();
    	boolean ivalidRangeExist;
    	List overLappingList = new ArrayList();
    	List tmpOverLappingList;
    	boolean validationSucess=true;
    	
    	Iterator defIterator = tierDefinitionList.iterator();
    	
    	while (defIterator.hasNext()) {
    		BenefitTierDefinition tierDefinition = (BenefitTierDefinition) defIterator.next();

    		ivalidRangeExist= false;
    		Iterator benefitTierItr = tierDefinition.getBenefitTiers().iterator();
    		BenefitTier benefitTier;
    		while (benefitTierItr.hasNext()){
    			benefitTier=(BenefitTier)benefitTierItr.next();
    			if(benefitTier.getCriteriaIndicator().equals("2")){
    				if(tierDefinition.getDataType().equalsIgnoreCase("numeric")){
        				String startCrt = benefitTier.getStartCriteria().getBenefitTierCriteriaValue();
        				String endCrt = benefitTier.getEndCriteria().getBenefitTierCriteriaValue();
        				String startCrt_wclc = null; 
        				String endCrt_wclc = null;
        				if(benefitTier.getBenefitTierCriteriaList().size() > 2){
        					startCrt_wclc = benefitTier.getStartCriteria_wclc().getBenefitTierCriteriaValue();
        					endCrt_wclc = benefitTier.getEndCriteria_wclc().getBenefitTierCriteriaValue();
        				}
        				if(!WPDStringUtil.isNumber(startCrt) || !WPDStringUtil.isNumber(endCrt) ||  (startCrt_wclc != null && !WPDStringUtil.isNumber(startCrt_wclc)) || (endCrt_wclc != null && !WPDStringUtil.isNumber(endCrt_wclc))){
        					ErrorMessage errorMessage = new ErrorMessage(BusinessConstants.INVALID_NUMBER_IN_TIER);
        				       errorMessage.setParameters(new String[] {tierDefinition.getBenefitTierDefinitionName().trim()});
        				       validationMessages.add(errorMessage);
        				       validationSucess=false;
        				}
        			}
    			if(validationSucess && !benefitTier.isRangeValid()){
    				ErrorMessage errorMessage;
    				if(benefitTier.getBenefitTierCriteriaList().size()<= 2){
    				errorMessage = new ErrorMessage(
    						BusinessConstants.INVALID_RANGE_IN_TIER);
    				errorMessage.setParameters(new String[] { 
    						tierDefinition.getBenefitTierDefinitionName(),
							benefitTier.getStartCriteria().getBenefitTierCriteriaName()+":" + 
							benefitTier.getStartCriteria().getBenefitTierCriteriaValue(),
							benefitTier.getEndCriteria().getBenefitTierCriteriaName() +":"+ 
							benefitTier.getEndCriteria().getBenefitTierCriteriaValue()});
    				}
    				else{
    					errorMessage = new ErrorMessage(
        						BusinessConstants.INVALID_RANGE_IN_TIER_WCLC);
    					errorMessage.setParameters(new String[] { 
        						tierDefinition.getBenefitTierDefinitionName(),
    							benefitTier.getStartCriteria().getBenefitTierCriteriaName()+":" + 
    							benefitTier.getStartCriteria().getBenefitTierCriteriaValue(),
    							benefitTier.getEndCriteria().getBenefitTierCriteriaName() +":"+ 
    							benefitTier.getEndCriteria().getBenefitTierCriteriaValue(), 
								benefitTier.getStartCriteria_wclc().getBenefitTierCriteriaName()+":" + 
    							benefitTier.getStartCriteria_wclc().getBenefitTierCriteriaValue(),
    							benefitTier.getEndCriteria_wclc().getBenefitTierCriteriaName() +":"+ 
    							benefitTier.getEndCriteria_wclc().getBenefitTierCriteriaValue()});
    				}
    				validationMessages.add(errorMessage);
    				validationSucess=false;
    				ivalidRangeExist= true;
    			}
    		  }
    		}
    		//If all ranges are valid then check for overlapping in the definition
    		if (!ivalidRangeExist && validationSucess){
    			
    			overLappingList=tierDefinition.validateRangeOverlapping();
    			String overLappingDetails="";
    	    	//Adding overlapping error messages if any
    			if (overLappingList.size()>0){
	    	    	Iterator overLapIterator = overLappingList.iterator();
	    	    	String bracketFlag="open";
	    	    	while (overLapIterator.hasNext()) {
	    	    		BenefitTier overLappingTier = (BenefitTier) overLapIterator.next();
	    	    		if (overLappingDetails!="")
	    	    			if(bracketFlag=="open")
	    	    				overLappingDetails+=" , ";
	    	    			else
	    	    				overLappingDetails+=" / ";
	    	    		if (bracketFlag=="open")
	    	    			overLappingDetails+="[";
	    	    		
	    	    		//Checking discrete or range value
	    	    		if(overLappingTier.isRangeTier()){
		    	    		overLappingDetails+=overLappingTier.getStartCriteria().getBenefitTierCriteriaName() +":"+
		    	    		overLappingTier.getStartCriteria().getBenefitTierCriteriaValue()+" "+
		    	    		overLappingTier.getEndCriteria().getBenefitTierCriteriaName() +":"+ 
		    	    		overLappingTier.getEndCriteria().getBenefitTierCriteriaValue();
	    	    		}else{
		    	    		overLappingDetails+=overLappingTier.getStartCriteria().getBenefitTierCriteriaName() +":"+
		    	    		overLappingTier.getStartCriteria().getBenefitTierCriteriaValue();	
		    	    		bracketFlag="close";
	    	    		}
	    	    		if (bracketFlag=="close"){
	    	    			overLappingDetails+="]";
	    	    			bracketFlag="open";
	    	    		}else
	    	    			bracketFlag="close";
	    	    		
	    	    		//to avoid repeating the tier criteria value if the tier type is discrete
	    	    		if(!overLappingTier.isRangeTier())
	    	    			break;
	    	    	}
    	    		ErrorMessage errorMessage = new ErrorMessage(
    	    				BusinessConstants.OVERLAPPING_VALUES_IN_TIER);
    	    		errorMessage.setParameters(new String[] { 
    	    				tierDefinition.getBenefitTierDefinitionName()+ ": " + overLappingDetails});
    	    		
    	    		validationMessages.add(errorMessage);
       				validationSucess=false;
    			}
    		}
    	}  	
    	


    	return validationSucess;
    }

	/*START CARS */
	
    /**
     * This method is for level validations
     * @param benefitLevel
     * @return
     */
	private BenefitLevel validateTieredLevelValues(BenefitLevel benefitLevel,String tierName){
    	ErrorMessage errorMessage = null;
    	boolean validationFlag = true;
		String levelId = new Integer(benefitLevel.getLevelId()).toString().trim();
		String tierSysId = new Integer(benefitLevel.getTierSysId()).toString().trim();
		String concatedKey = levelId.concat(WebConstants.COLON).concat(tierSysId);
		//Frequency Start
		Object oldFrequencyObj = hiddenLineFreqValueMapTier.get(concatedKey);
    	Object newFrequencyObj = lineFreqValueMapTier.get(concatedKey);
    	int frequency;
    	int noOfTokens;
    	boolean isNumber = false;
    	//Null checking the old freqeuncy value
    	if(null != oldFrequencyObj && !(oldFrequencyObj.equals(WebConstants.EMPTY_STRING))){
    		//Checking if the old frequency value is 0
    		if(WebConstants.ZERO_STRING != oldFrequencyObj.toString()){
        		//Checking whether frequency value is empty
        		if(!(WebConstants.EMPTY_STRING.equalsIgnoreCase(newFrequencyObj.toString().trim()))){
	        		isNumber = WPDStringUtil.isNumber(newFrequencyObj.toString());
	        		//Checking frequency value is a number
	        		if(isNumber){
	        			frequency = new Integer(newFrequencyObj.toString()).intValue();
	        			//Checkin whether frequency value is set 0
	        			if(0 != frequency){
	        				benefitLevel.setFrequencyId(new Integer(newFrequencyObj.toString()).intValue());
	        				benefitLevel.setFrequencyDesc(newFrequencyObj.toString());
	        				//benefitLevel.setModified(true);
	        			}else if(isErrorTierFlag){//set the error message for zero can not be a value
	        				validationFlag = false;
	        				errorMessage = new ErrorMessage(WebConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_CORRECT);
	        				errorMessage.setParameters(new String[] {oldDescOutputTxtTier.get(concatedKey).toString().trim()+" "+tierName});
	        				validationMessages.add(errorMessage);
	        				isErrorTierFlag = false;
	        			}
	        		}else{//set the error message for not a number
	        			validationFlag = false;
	        			errorMessage = new ErrorMessage(BusinessConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_NUMBER);
	        			errorMessage.setParameters(new String[] {oldDescOutputTxtTier.get(concatedKey).toString().trim()+" "+tierName});
	        			validationMessages.add(errorMessage);
	        		}
        		}else{//set error message frequency value is empty
        			validationFlag = false;
        			errorMessage = new ErrorMessage(BusinessConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_EMPTY);
        			errorMessage.setParameters(new String[] {oldDescOutputTxtTier.get(concatedKey).toString().trim()+" "+tierName});
        			validationMessages.add(errorMessage);
        		}
    		}
    	}    	
    	if((null != oldFrequencyObj) && (null != newFrequencyObj)&& !(newFrequencyObj.toString().trim().equals(oldFrequencyObj.toString().trim()))){
			isFrequencyChanged = true;
    	}
    	//Frequency End
    	//Description Start
    	Object newDescriptionValue;
    	Object oldDescriptionValue;
		Object oldFrequencyValue;
		Object newFrequencyValue;
		Object termValue;
		Object qualifierValue;
		Object lowerLevelTierFrequencyValue;
		Object lowerLevelTierDescriptionValue;
		//old description value binded with outpur text
		oldDescriptionValue = oldDescOutputTxtTier.get(concatedKey);
		//new description value binded with input text
		newDescriptionValue = dataHiddenValDescTier.get(concatedKey);
		//frequency value binded with input hidden
		oldFrequencyValue = hiddenLineFreqValueMapTier.get(concatedKey);
		//frequency value binded with input text
		newFrequencyValue = lineFreqValueMapTier.get(concatedKey);
		//term value binded with input text
		termValue = dataHiddenValTermTier.get(new Long(levelId.toString().trim()));
		//qualifier value binded with input text
		qualifierValue = dataHiddenValQualifierTier.get(new Long(levelId.toString().trim()));
		lowerLevelTierFrequencyValue = hiddenTierLowerLevelFreqValueMap.get(concatedKey);
		lowerLevelTierDescriptionValue = dataTierHiddenLowerLevelValDesc.get(concatedKey);	
		if(!StringUtil.isEmpty(newDescriptionValue)){
				//if(oldDescriptionValue.toString().trim().toUpperCase().equals(newDescriptionValue.toString().trim().toUpperCase())){
					//Checking null all the object(description, term, qualifier, frequency)						
					if((!StringUtil.isEmpty(qualifierValue)) && (!StringUtil.isEmpty(termValue)) && (!StringUtil.isEmpty(oldFrequencyValue)) && (!StringUtil.isEmpty(newFrequencyValue)) && (WPDStringUtil.isNumber(newFrequencyValue.toString().trim()))){
						String description = newDescriptionValue.toString().toUpperCase().trim();
						String term = termValue.toString().trim().toUpperCase();
						String qualifier = qualifierValue.toString().trim().toUpperCase();
						//Fix aggregate qualifier start
						term = WPDStringUtil.commaSeparatedString(term);
						qualifier = WPDStringUtil.commaSeparatedString(qualifier);
						/*if(null != qualifier){
							StringTokenizer benefitQualifiers = new StringTokenizer(
									qualifier, BusinessConstants.COMMA);
							 noOfTokens = benefitQualifiers.countTokens();
							 boolean flag = true;
							 if(noOfTokens > 1){
							 	 for (int j = 0; j < noOfTokens; j++) {
			                        if (benefitQualifiers.hasMoreTokens()) {
			                            String benefitQualifer = benefitQualifiers.nextToken();
			                            if(flag == true){
			                            	qualifier = benefitQualifer.trim();
		                            		//Flag is set false to restrict the entry for the second time
		                            		flag = false;
		                            	}else{
		                            		//Setting the term description value after the comma seperation
		                            		qualifier = qualifier+WebConstants.SPACE_STRING+ benefitQualifer.trim();
		                            	}
			                        }
							 	 }   
							 }
						}
						//Fix aggregate qualifier End
						//Fix for aggregate term start
						if(null != term){
							StringTokenizer benefitTerms = new StringTokenizer(
									term, BusinessConstants.COMMA);
							 noOfTokens = benefitTerms.countTokens();
							 boolean flag = true;
							 if(noOfTokens > 1){
							 	 for (int j = 0; j < noOfTokens; j++) {
			                        if (benefitTerms.hasMoreTokens()) {
			                            String benefitTerm = benefitTerms.nextToken();
			                            if(flag == true){
			                            	term = benefitTerm.trim();
		                            		//Flag is set false to restrict the entry for the second time
		                            		flag = false;
		                            	}else{
		                            		//Setting the term description value after the comma seperation
		                            		term = term+WebConstants.SPACE_STRING+ benefitTerm.trim();
		                            	}
			                        }
							 	 }   
							 }
						}*/
						//Fix for aggregate term end
						String frequencyVal = oldFrequencyValue.toString().trim();
						String changeDesc;
						//checking if the frequency value is 1
						if(frequencyVal.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)){									
							//description is combination of term qualifier and frequency
		                	changeDesc  = term+WebConstants.PER_STRING+qualifier;
		                }else{
		                	changeDesc  = term+WebConstants.PER_STRING+frequencyVal+WebConstants.SPACE_STRING+qualifier;
		                }//Compares the old description and new description
		                if(description.equalsIgnoreCase(changeDesc)){
		                	int frequencyValue = new Integer(newFrequencyValue.toString().trim()).intValue();
		                	frequencyVal = frequencyValue+WebConstants.EMPTY_STRING;
		                	//frequencyVal = newFrequencyValue.toString().trim();
		                	if(frequencyVal.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)){									
								//description is combination of term qualifier and frequency
			                	changeDesc  = term+WebConstants.PER_STRING+qualifier;
			                }else{
			                	changeDesc  = term+WebConstants.PER_STRING+frequencyVal+WebConstants.SPACE_STRING+qualifier;
			                }
		                	if(changeDesc.length() > 32){
		                		//Storing the truncated level description
		        				if(null == tieredTruncatedLvlDesc){
		        					tieredTruncatedLvlDesc = changeDesc;
		        				}else{
		        					tieredTruncatedLvlDesc = tieredTruncatedLvlDesc + ", " + changeDesc;
		        				}
			                	//Keeping only 32 characters, while level description is more than 32 characters
			                	if(null !=changeDesc && changeDesc.length()>32){
			                		changeDesc = changeDesc.substring(0,32);
			                	}
		                		//benefitLevel.setLevelDesc(changeDesc);
		                		isTieredLvlDescTruncated = true;
		                	}else{
		                		//benefitLevel.setLevelDesc(changeDesc);
		                	}
		                }else{
		                	if(lowerLevelTierFrequencyValue.toString().toUpperCase().trim().equals(newFrequencyValue.toString().toUpperCase().trim())){
		                		String lvlDescriptionValue = lowerLevelTierDescriptionValue.toString().toUpperCase().trim();
								if(lvlDescriptionValue.length() > 32){
									lvlDescriptionValue = lvlDescriptionValue.substring(0,32).trim();
									//benefitLevel.setLevelDesc(lvlDescriptionValue);
									isTieredLvlDescTruncated = true;
								}
								//benefitLevel.setLevelDesc(lvlDescriptionValue);
		                	}else{
			                	//Keeping only 32 characters, while level description is more than 32 characters
			                	if(null !=description && description.length()>32){
			                		//Storing the truncated level description
			        				if(null == tieredTruncatedLvlDesc){
			        					tieredTruncatedLvlDesc = description;
			        				}else{
			        					tieredTruncatedLvlDesc = tieredTruncatedLvlDesc + ", " + description;
			        				}
			                		description = description.substring(0,32);
			                		isTieredLvlDescTruncated = true;
			                	}
			                	//benefitLevel.setLevelDesc(description);
		                	}
		                }	
					}else{
	                	//Keeping only 32 characters, while level description is more than 32 characters
	                	if(null !=oldDescriptionValue && oldDescriptionValue.toString().length()>32){
	                		//Storing the truncated level description
	        				if(null == tieredTruncatedLvlDesc){
	        					tieredTruncatedLvlDesc = oldDescriptionValue.toString();
	        				}else{
	        					tieredTruncatedLvlDesc = tieredTruncatedLvlDesc + ", " + oldDescriptionValue.toString();
	        				}
							String lvlDescriptionValue = oldDescriptionValue.toString().toUpperCase().trim().substring(0,32);
							//benefitLevel.setLevelDesc(lvlDescriptionValue);
							isTieredLvlDescTruncated = true;
	                	}else{
							//benefitLevel.setLevelDesc(oldDescriptionValue.toString().toUpperCase().toUpperCase().trim());
	                	}
					}
				/*}else{
                	//Keeping only 32 characters, while level description is more than 32 characters
                	if(null !=newDescriptionValue && newDescriptionValue.toString().length()>32){
                		//Storing the truncated level description
        				if(null == tieredTruncatedLvlDesc){
        					tieredTruncatedLvlDesc = newDescriptionValue.toString();
        				}else{
        					tieredTruncatedLvlDesc = tieredTruncatedLvlDesc + ", " + newDescriptionValue.toString();
        				}
						String lvlDescriptionValue = newDescriptionValue.toString().toUpperCase().trim().substring(0,32);
						benefitLevel.setLevelDesc(lvlDescriptionValue);
						isTieredLvlDescTruncated = true;
                	}else{
						benefitLevel.setLevelDesc(newDescriptionValue.toString().toUpperCase().trim());
                	}
				}*/
		}else if(validationFlag){//set error message description value can not be empty
			validationFlag = false;
			validationMessages.add(new ErrorMessage(WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_REQUIRED));
		}
    	//Description End
		//Sets the flag true if the frequency value changed
		if((null != oldFrequencyValue) && (null != newFrequencyObj)){
			if(!(oldFrequencyValue.equals(newFrequencyObj))){
				isLineValueModified=true;
			}
		}
		addAllMessagesToRequest(validationMessages);
    	benefitLevel.setValidationFlag(validationFlag);
    	return benefitLevel;
    }
    
    /*START CARS */
	
	/**
	 * 
	 * @return
	 */
	private boolean isValid(String dataTypeId, String value, String levelDesc) {
		//List validationMessages = null;
		boolean isValid = true;
		String sysDataType = null;
		String dataTypeName = null;
		int dataType = 0;
		if (null != dataTypeId && !"".equals(dataTypeId)) {
			dataType = Integer.parseInt(dataTypeId);
		}
		ErrorMessage errorMessage = null;
		List universeDataTypeList = WPDStringUtil.getUniverseDataTypeList();
		if (dataType != 0) {
			//sysDataType = WPDStringUtil.getSysDatatype(universeDataTypeList, dataType);
			//dataTypeName = WPDStringUtil.getDataTypeName(universeDataTypeList, dataType);
			// verify
			DataTypeLocateResult dataTypeDetails = null;
			dataTypeDetails = WPDStringUtil.getDataTypeDetails(
					universeDataTypeList, dataType);
			if (null != dataTypeDetails) {
				sysDataType = dataTypeDetails.getDataTypeDesc().toUpperCase()
						.trim();
				dataTypeName = dataTypeDetails.getDataTypeName().toUpperCase()
						.trim();
			}
		}
		boolean isNumber = true;
		boolean isDecimalNumber = true;
		boolean isGreaterThanHundred = true;
		boolean isBooleanFlag = true;
		boolean isMaxInteger = false;
		boolean isValidPrecision = true;

		if (null != value && !"".equals(value) && null != sysDataType
				&& !"".equals(sysDataType)) {
			if ("BOOLEAN".equals(sysDataType.toUpperCase()))
				isBooleanFlag = WPDStringUtil.isValidBoolean(value);
			else if ("DOLLAR".equals(sysDataType.toUpperCase()))
				isNumber = WPDStringUtil.isNumber(value);
			else if ("PERCENTAGE".equals(sysDataType.toUpperCase())) {
				isDecimalNumber = WPDStringUtil.isDecimalNumber(value);
				if (isDecimalNumber) {
					isValidPrecision = WPDStringUtil.isValidPrecision(value,
							WebConstants.ALLOWED_NUMBER_OF_PRECISION);
					if (isValidPrecision) {
						/*double doubleValue = Double.parseDouble(value);
						 if(doubleValue > 100){
						 isGreaterThanHundred = false;
						 }*/
						isGreaterThanHundred = WPDStringUtil
								.isGreaterThanHundred(value);
					}
				}
			} else if ("INTEGER".equals(sysDataType.toUpperCase())) {
				isNumber = WPDStringUtil.isNumber(value);
				if (isNumber) {
					isMaxInteger = WPDStringUtil.isMaxInteger(value);
				}
			} else if ("FLOAT".equals(sysDataType.toUpperCase())) {
				isDecimalNumber = WPDStringUtil.isDecimalNumber(value);
				if (isDecimalNumber) {
					isValidPrecision = WPDStringUtil.isValidPrecision(value,
							WebConstants.ALLOWED_NUMBER_OF_PRECISION);
				}
			}
		}
		if (!isBooleanFlag || !isNumber || !isDecimalNumber
				|| !isGreaterThanHundred || isMaxInteger || !isValidPrecision) {
			//validationMessages = new ArrayList();
			isValid = false;
		}
		//validationMessages = new ArrayList();
		if (!isBooleanFlag) {
			errorMessage = new ErrorMessage(
					WebConstants.DATA_TYPE_MISMATCH_BENEFIT_LEVEL);
			if (null != dataTypeName && null != levelDesc)
				errorMessage.setParameters(new String[] { dataTypeName,
						levelDesc });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
		}
		if (!isNumber) {
			errorMessage = new ErrorMessage(
					"benefitlevel.benefit.value.not.number");
			if (null != dataTypeName && null != levelDesc)
				errorMessage.setParameters(new String[] { dataTypeName,
						levelDesc });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
		}
		if (isMaxInteger) {
			errorMessage = new ErrorMessage(
					"benefitlevel.benefit.value.max.integer");
			if (null != dataTypeName && null != levelDesc)
				errorMessage.setParameters(new String[] { dataTypeName,
						levelDesc });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
		}
		if (!isDecimalNumber) {
			errorMessage = new ErrorMessage(
					"benefitlevel.benefit.value.not.decimalnumber");
			if (null != dataTypeName && null != levelDesc)
				errorMessage.setParameters(new String[] { dataTypeName,
						levelDesc });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
		}
		if (!isValidPrecision) {
			errorMessage = new ErrorMessage(
					"benefitlevel.benefit.value.valid.precision");
			if (null != dataTypeName && null != levelDesc)
				errorMessage
						.setParameters(new String[] {
								String
										.valueOf(WebConstants.ALLOWED_NUMBER_OF_PRECISION),
								dataTypeName, levelDesc });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
		}
		if (!isGreaterThanHundred) {
			errorMessage = new ErrorMessage(
					"benefitlevel.benefit.value.greater.hundred");
			if (null != dataTypeName && null != levelDesc)
				errorMessage.setParameters(new String[] { dataTypeName,
						levelDesc });
			validationMessages.add(errorMessage);
			addAllMessagesToRequest(validationMessages);
		}
		return isValid;
	}
	/**
	 * @return
	 */
	private boolean validateTiersForDatatype()
	{
		boolean validationFlag = true;
		isLineValueModified=false;
		List tierDefList = getContractSession().getBenefitTierDefinitionList();
		List tierLevelLineList = getContractSession().getBenefitTierLevelList();
		
		for (Iterator benLevelItr = tierLevelLineList.iterator(); benLevelItr.hasNext();) {
			BenefitLevel benefitLevel = (BenefitLevel) benLevelItr.next();
    		//CARS START    	
    		int tierSysIdFrequency = benefitLevel.getTierSysId();
			String tierNameFrequency = null;
			for (Iterator defIterator = tierDefList.iterator(); defIterator.hasNext();) {
				BenefitTierDefinition tierDefinition = (BenefitTierDefinition) defIterator.next();
				for (Iterator tierBoIterator = tierDefinition.getBenefitTiers().iterator(); tierBoIterator.hasNext();){
					BenefitTier benefitTier = (BenefitTier) tierBoIterator.next();
					if(benefitTier.getBenefitTierSysId()==tierSysIdFrequency){
						tierNameFrequency=tierDefinition.getBenefitTierDefinitionName();
						break;
					}
				}				
			}
    		benefitLevel = validateTieredLevelValues(benefitLevel,tierNameFrequency);
    		if(!benefitLevel.isValidationFlag()){
    			validationFlag = benefitLevel.isValidationFlag();
    		}
    		//CARS END
			for (Iterator benLineItr = benefitLevel.getBenefitLines().iterator(); benLineItr.hasNext();) 
			{
				BenefitLine benefitLine = (BenefitLine) benLineItr.next();
				String keyString = formKeyforMap(benefitLevel.getLevelId(), benefitLine.getLineSysId(), benefitLevel.getTierSysId());
				String newLineVal = (String)getTierLineValueMap().get(keyString);
				if(!( (null==newLineVal || "".equals(newLineVal)) && (null == benefitLine.getBenefitValue() || "".equals(benefitLine.getBenefitValue())) ) )
				{									
					if(!newLineVal.equals(benefitLine.getBenefitValue()))
					{
						if(!isLineValueModified)
						{
							isLineValueModified=true;
						}
						Object levelVal = this.getLineIdMap().get(new Long(benefitLine.getLineSysId()));
						String levelLineVal = levelVal.toString();
						String appendedBnftVal = null;
						String appendedLineVal = null;
						String appendedDataTypeId = null;
						String appendedLevelDesc = null;
						String newBenefitValue = null;
						if (null != levelLineVal && !levelLineVal.equals(""))
						{
							StringTokenizer tokenizer = new StringTokenizer(levelLineVal,":");
							int i = 0;
							while (tokenizer.hasMoreTokens()) {
								String tokens = tokenizer.nextToken();
								if (i == 0) {
									appendedLineVal = tokens;
								} else if (i == 1) {
									appendedBnftVal = tokens;
								} else if (i == 2) {
									appendedDataTypeId = tokens;
								} else if (i == 3) {
									appendedLevelDesc = tokens;
								}
								i++;
							}	  		        				 
							int tierId = benefitLine.getTierSysId();
							String tierName="";
							for (Iterator defIterator = tierDefList.iterator(); defIterator.hasNext();) {
								BenefitTierDefinition tierDefinition = (BenefitTierDefinition) defIterator.next();
								for (Iterator tierBoIterator = tierDefinition.getBenefitTiers().iterator(); tierBoIterator.hasNext();) 
								{
									BenefitTier benefitTier = (BenefitTier) tierBoIterator.next();
									if(benefitTier.getBenefitTierSysId()==tierId)
									{
										tierName=tierDefinition.getBenefitTierDefinitionName();
									}	
									
								}				
							}	
							//check whether the override value is Valid.
							if (!isValid(appendedDataTypeId, newLineVal, appendedLevelDesc+" in "+tierName)) 
							{
								validationFlag = false;
							}
						}  
					}
				}   
			}
			
		}	    	      	
		return validationFlag;
	}  
	// **End        

	/**
	 * Returns the benefitValueMap
	 * @return Map benefitValueMap.
	 */
	public Map getBenefitValueMap() {
		return benefitValueMap;
	}

	/**
	 * Sets the benefitValueMap
	 * @param benefitValueMap.
	 */
	public void setBenefitValueMap(Map benefitValueMap) {
		this.benefitValueMap = benefitValueMap;
	}

	/**
	 * Sets the panel
	 * @param panel.
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
	}

	public String getBenefitDefinitionsPage() {
		//checks for view mode
		if (!isViewMode())
			return "benefitDefinitionsPage";
		else
			return "benefitDefinitionsViewPage";
	}

	/**
	 * Returns the dummyVar
	 * @return String dummyVar.
	 */

	public String getDummyVar() {
		return dummyVar;
	}

	/**
	 * Sets the dummyVar
	 * @param dummyVar.
	 */

	public void setDummyVar(String dummyVar) {
		this.dummyVar = dummyVar;
	}

	/**
	 * Returns the Panel for print page
	 * @return HtmlPanelGrid PanelForPrint
	 * 
	 */
	public HtmlPanelGrid getPanelForPrint() {

		Logger.logInfo("entered method getPanelForPrint");

		int rowNumber = 0;
		//  EntityBenefitDefenition benefitDefinitionWrapper = this.getBenefitDefinition();
		List benefitDefinitonsList = this.getBenefitDefinitionsList();

		//This method gets the values from the benefit definiton List and sets it to the level list and line list
		getValuesFromDefinitonList(benefitDefinitonsList);

		panelForPrint = new HtmlPanelGrid();
		panelForPrint.setWidth("100%");
		panelForPrint.setColumns(7);
		panelForPrint.setColumnClasses("gridColumn22,gridColumn12,gridColumn12,gridColumn11,gridColumn10,gridColumn8,gridColumn25");
		panelForPrint.setBorder(0);
		panelForPrint.setCellpadding("3");
		panelForPrint.setCellspacing("1");
		panelForPrint.setBgcolor("#cccccc");
		panelForPrint.setId("panelForPrint"+RandomStringUtils.randomAlphanumeric(15));
		
		//panel.setBgcolor("#cccccc");

		StringBuffer rows = new StringBuffer();

		//setting values to benefit levels

		int size = benefitLevelList.size();

		//iterating to get the benefit levels
		for (int i = 0; i < size; i++) {
			rowNumber++;
			rows.append("dataTableEvenRow");

			//a benefit level is selected
			BenefitLevel benefitLevelValues = (BenefitLevel) benefitLevelList
					.get(i);

			//gets the benefit lines of a benefit level
			List benefitLines = benefitLevelValues.getBenefitLines();

			//setting the benefit level values to the panel grid
			setBenefitLevelValuesToGridForPrint(i, benefitLevelValues,
					benefitLines.size(), rowNumber);

			if (benefitLines.size() != 0)
				rows.append(",");
			//iterating to get the individual benefit lines
			for (int j = 0; j < benefitLines.size(); j++) {
				rowNumber++;

				rows.append("dataTableOddRow");

				if (i < (size - 1))
					rows.append(",");
				else if (j < (benefitLines.size() - 1))
					rows.append(",");

				//selects an individual benefit line
				BenefitLine benefitLineValues = (BenefitLine) benefitLines
						.get(j);

				//sets the benefit lines of a benefit level to the panle grid
				setBenefitLineValuesToGridForPrint(benefitLevelValues, j,
						benefitLineValues, i);

			}

		}

		//panel.setRowClasses(rows.toString());
		Logger.logInfo("PanelChildCount in getPanelForPrint...."
				+ panelForPrint.getChildCount());
		return panelForPrint;

	}

	/**
	 * This method sets the benefitLineValues to the panel Grid
	 * 
	 * @param benefitLevelValues
	 * @param j
	 * @param benefitLineValues
	 * @param i
	 */
	private void setBenefitLineValuesToGridForPrint(
			BenefitLevel benefitLevelValues, int j,
			BenefitLine benefitLineValues, int i) {

		Logger.logInfo("entered method setBenefitLineValuesToGridForPrint");

		HtmlOutputText lineDesc = new HtmlOutputText();
		lineDesc.setValue(" ");

		HtmlOutputText lineTerm = new HtmlOutputText();
		lineTerm.setValue(benefitLevelValues.getTermDesc());

		HtmlOutputText lineQualifier = new HtmlOutputText();
		lineQualifier.setValue(benefitLevelValues.getQualifierDesc());

		HtmlOutputText linePVA = new HtmlOutputText();
		linePVA.setValue(benefitLineValues.getProviderArrangementDesc());
		linePVA.setId(benefitLineValues.getProviderArrangementId());

		HtmlOutputText lineDataType = new HtmlOutputText();
		lineDataType.setValue(benefitLineValues.getDataTypeDesc());
		lineDataType.setId(benefitLineValues.getDataTypeId());

		//      line BenefitValue
		HtmlOutputText lineBnftValue = new HtmlOutputText();
		HtmlSelectOneMenu seloneMenuForBnftValue = new HtmlSelectOneMenu();
		UIComponent object = null;
		if (null != benefitLineValues.getDataTypeDesc()
				&& !(benefitLineValues.getDataTypeDesc()).equals("")) {
			if (benefitLineValues.getDataTypeDesc().equals("$")
					|| benefitLineValues.getDataTypeDesc().equals("%")) {
				//lineBnftValue.setSize(10);
				lineBnftValue.setId("lineBnftValue" + j + i);
				lineBnftValue.setValue(benefitLineValues.getBenefitValue());
				lineBnftValue.setStyleClass("outputText");
				lineBnftValue.setStyle("width:75px;");
			} else {
				UISelectItems selectItems = new UISelectItems();
				List possibleBnftVal = new ArrayList();
				possibleBnftVal.add(new SelectItem("Yes", "Yes"));
				possibleBnftVal.add(new SelectItem("No", "No"));
				selectItems.setValue(possibleBnftVal);
				seloneMenuForBnftValue.getChildren().add(selectItems);
				seloneMenuForBnftValue.setValue(benefitLineValues
						.getBenefitValue());
				seloneMenuForBnftValue.setId("lineBnftValue" + j + i + "_print" );
				object = (HtmlSelectOneMenu) seloneMenuForBnftValue;

			}
		}

		//output text for view
		HtmlOutputText lineBnftValueView = new HtmlOutputText();
		lineBnftValueView.setId("lineBnftValueView" + j + i);
		lineBnftValueView.setValue(benefitLineValues.getBenefitValue());
		lineBnftValueView.setStyleClass("formInputFieldReadOnly");

		HtmlOutputText lineEmptyString = new HtmlOutputText();
		lineEmptyString.setValue(" ");

		HtmlOutputLabel lblBnftValue = new HtmlOutputLabel();
		lblBnftValue.setId("lblBnftVal"+RandomStringUtils.randomAlphanumeric(15));
		//lblBnftValue.setId("lblBnftValue" + j + i);
		lblBnftValue.setFor("lineBnftValue" + j + i);

		if (null != benefitLineValues.getDataTypeDesc()
				&& !(benefitLineValues.getDataTypeDesc()).equals("")) {
			if (benefitLineValues.getDataTypeDesc().equals("$")) {

				lblBnftValue.getChildren().add(lineDataType);
				lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(lineEmptyString);

			} else if (benefitLineValues.getDataTypeDesc().equals("%")) {

				lblBnftValue.getChildren().add(lineEmptyString);
				lblBnftValue.getChildren().add(lineBnftValueView);
				lblBnftValue.getChildren().add(lineDataType);

			} else {

				lblBnftValue.getChildren().add(lineEmptyString);
				lblBnftValue.getChildren().add(lineBnftValueView);

			}
		}

		HtmlOutputText lineReference = new HtmlOutputText();
		lineDesc.setValue(" ");

		HtmlOutputText lineImage = new HtmlOutputText();
		lineDesc.setValue(" ");

		panelForPrint.getChildren().add(lineDesc);
		panelForPrint.getChildren().add(lineTerm);
		panelForPrint.getChildren().add(lineQualifier);
		panelForPrint.getChildren().add(linePVA);
		panelForPrint.getChildren().add(lblBnftValue);
		panelForPrint.getChildren().add(lineReference);
		//panel.getChildren().add(lineImage);
	}

	/** This method sets the benefitLevelValues to the PanelGrid
	 * @param i
	 * @param benefitLevelValues
	 */
	private void setBenefitLevelValuesToGridForPrint(int i,
			BenefitLevel benefitLevelValues, int lineSize, int rowNum) {

		Logger.logInfo("entered method setBenefitLevelValuesToGridForPrint");

		HtmlOutputText levelDesc = new HtmlOutputText();
		if (null != benefitLevelValues.getLevelDesc()) {
			levelDesc.setValue(benefitLevelValues.getLevelDesc().trim());
		} else {
			levelDesc.setValue(WebConstants.EMPTY_STRING);
		}
		levelDesc.setId("levelDesc" + benefitLevelValues.getLevelId());

		HtmlOutputText levelTerm = new HtmlOutputText();
		if (null != benefitLevelValues.getTermDesc()) {
			levelTerm.setValue(benefitLevelValues.getTermDesc().trim());
		} else {
			levelTerm.setValue(WebConstants.EMPTY_STRING);
		}

		HtmlOutputText levelQualifier = new HtmlOutputText();
	
		if (null != benefitLevelValues.getQualifierDesc()) {
    			levelQualifier.setValue(benefitLevelValues.getQualifierDesc()
    					.trim());
    	} else {
    			levelQualifier.setValue(WebConstants.EMPTY_STRING);
    	}
      
		HtmlOutputText levelPVA = new HtmlOutputText();
		levelPVA.setValue(" ");

		HtmlOutputText levelBnftValue = new HtmlOutputText();
		levelBnftValue.setValue(" ");

		HtmlOutputText levelReference = new HtmlOutputText();
		if (null != benefitLevelValues.getReferenceDesc()) {
			levelReference.setValue(benefitLevelValues.getReferenceDesc()
					.trim());
		} else {
			levelReference.setValue(WebConstants.EMPTY_STRING);
		}

		HtmlInputHidden hiddenLevelId = new HtmlInputHidden();
		hiddenLevelId.setId("hiddenLevelId" + i);
		hiddenLevelId.setValue(new Integer(benefitLevelValues.getLevelId()));
		// Change start
		// set the value to the map
		/*ValueBinding levelIdValBind = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{contractCoverageBackingBean.levelIdMap[" + i
								+ "]}");
		hiddenLevelId.setValueBinding("value", levelIdValBind);*/
		// change end

		HtmlGraphicImage deleteImage = new HtmlGraphicImage();
		deleteImage.setUrl("../../images/delete.gif");
		deleteImage.setStyle("cursor:hand;");
		deleteImage.setId("deleteImage" + i);

		HtmlCommandButton deleteButton = new HtmlCommandButton();

		deleteButton.setValue("DeleteButton");
		deleteButton.setImage("../../images/delete.gif");
		deleteButton.setTitle("Delete");
		deleteButton.setStyle("border:0;");
		deleteButton.setAlt("Delete");
		deleteButton.setOnclick("changeColour(\'" + i + "','" + lineSize
				+ "','" + rowNum + "\');return false;");
		//deleteButton.setId("deleteButton" + i);
		deleteButton.setId("deleteButton"+RandomStringUtils.randomAlphanumeric(15));
		HtmlOutputLabel lblImage = new HtmlOutputLabel();
		lblImage.setFor("levelDesc" + i);
		//lblImage.setId("lblImage" + i);
		lblImage.setId("lblIma"+RandomStringUtils.randomAlphanumeric(15));
		//sets the size to a hidden variable
		HtmlInputHidden hiddenLineSize = new HtmlInputHidden();
		hiddenLineSize.setId("hiddenLineSize" + i);
		hiddenLineSize.setValue(new Integer(lineSize));

		//      sets the size to a hidden variable
		HtmlInputHidden hiddenRowSize = new HtmlInputHidden();
		hiddenRowSize.setId("hiddenRowNum" + i);
		hiddenRowSize.setValue(new Integer(rowNum));

		//checks if it is a view mode

		lblImage.getChildren().add(hiddenLevelId);
		lblImage.getChildren().add(hiddenLineSize);
		lblImage.getChildren().add(hiddenRowSize);

		panelForPrint.getChildren().add(levelDesc);
		panelForPrint.getChildren().add(levelTerm);
		panelForPrint.getChildren().add(levelQualifier);
		panelForPrint.getChildren().add(levelPVA);
		panelForPrint.getChildren().add(levelBnftValue);
		panelForPrint.getChildren().add(levelReference);
		//panel.getChildren().add(lblImage);

	}

	/**
	 * Returns the headerPanelForPrint
	 * @return HtmlPanelGrid headerPanelForPrint.
	 */
	public HtmlPanelGrid getHeaderPanelForPrint() {

		Logger.logInfo("entered method getHeaderPanelForPrint");

		//sets the string which contains the levels to delete to null
		if (null != this.levelsToDelete) {
			this.levelsToDelete = null;
		}

		headerPanel = new HtmlPanelGrid();
		headerPanel.setWidth("100%");
		headerPanel.setColumns(7);
		headerPanel.setBorder(0);
		headerPanel.setCellpadding("3");
		headerPanel.setCellspacing("1");
		headerPanel.setBgcolor("#cccccc");
		headerPanel.setId("headerPanel"+RandomStringUtils.randomAlphanumeric(15));
		
		HtmlOutputText headerText1 = new HtmlOutputText();
		HtmlOutputText headerText2 = new HtmlOutputText();
		HtmlOutputText headerText3 = new HtmlOutputText();
		HtmlOutputText headerText4 = new HtmlOutputText();
		HtmlOutputText headerText5 = new HtmlOutputText();
		HtmlOutputText headerText6 = new HtmlOutputText();
		HtmlOutputText headerText7 = new HtmlOutputText();

		headerText1.setValue("Description");
		headerText1.setId("desc");

		headerText2.setValue("Term");
		headerText2.setId("term");

		headerText3.setValue("Qualifier");
		headerText3.setId("qualifier");

		headerText4.setValue("PVA");
		headerText4.setId("pva");
		
		headerText5.setValue("Format");
		headerText5.setId("dataTypeLgnd");

		headerText6.setValue("Benefit Value");
		headerText6.setId("bnftValue");

		headerText7.setValue("Reference");
		headerText7.setId("ref");
		
		headerPanel.setStyleClass("dataTableHeader");
		headerPanel.getChildren().add(headerText1);
		headerPanel.getChildren().add(headerText2);
		headerPanel.getChildren().add(headerText3);
		headerPanel.getChildren().add(headerText4);
		headerPanel.getChildren().add(headerText5);
		headerPanel.getChildren().add(headerText6);		
		headerPanel.getChildren().add(headerText7);	
		headerPanel.setColumnClasses("gridColumn20,gridColumn16,gridColumn14,gridColumn7,gridColumn9,gridColumn9,gridColumn25");
		//headerPanel.getChildren().add(headerText7);

		return headerPanel;
	}

	/**
	 * @param headerPanelForPrint The headerPanelForPrint to set.
	 */
	public void setHeaderPanelForPrint(HtmlPanelGrid headerPanelForPrint) {
		this.headerPanelForPrint = headerPanelForPrint;
	}

	/**
	 * @param panelForPrint The panelForPrint to set.
	 */
	public void setPanelForPrint(HtmlPanelGrid panelForPrint) {
		this.panelForPrint = panelForPrint;
	}

	/**
	 * Returns the validationMessages
	 * @return List validationMessages.
	 */

	public List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * Sets the validationMessages
	 * @param validationMessages.
	 */

	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	public HashMap getLineIdMap() {
		return lineIdMap;
	}

	public void setLineIdMap(HashMap lineIdMap) {
		this.lineIdMap = lineIdMap;
	}

	/**
	 * @param benefitTypeTab
	 * 
	 * Sets the benefitTypeTab.
	 */
	public void setBenefitTypeTab(String benefitTypeTab) {
		this.benefitTypeTab = benefitTypeTab;
	}

	public String getBenefitTypeTab() {
		String contractType = getContractSession().getContractKeyObject().contractType;
		if (null != contractType) {
			if (contractType.equalsIgnoreCase(WebConstants.MNDT_TYPE))
				return WebConstants.STANDARD_BENEFIT_MANDATE;
			else
				return WebConstants.CONTRACT_COVERAGE;

		}
		return "";
	}

	public HtmlPanelGrid getTierPanel() {
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","ContractCoverageBackingBean","getTierPanel()"));
		
		if(!this.benefitDefinitionsListRetrieved || this.benefitDefinitionsList == null) {
			this.benefitDefinitionsList = this.getBenefitDefinitionsList();
			this.benefitDefinitionsListRetrieved = true;
		}
		
		// Retaining the previous entered values, In case of application has any error message (Defect: 186432)
		// get the benefit defenitions list if list not null
		if (validationMessages.isEmpty() && null != this.benefitDefinitionsList) {		
			int rowNumber = 0;
			int lineCount = 0;
			int tierNo = 0;
			
			List benefitDefinitonsList = this.benefitDefinitionsList;
	
			//This method gets the values from the benefit definiton List and sets
			// it to the level list and line list
			getValuesFromDefinitonList(benefitDefinitonsList);
	
			tierPanel = new HtmlPanelGrid();
			tierPanel.setColumns(1);
			tierPanel.setWidth("100%");
			tierPanel.setId("tierPanel"+RandomStringUtils.randomAlphanumeric(15));
			tierPanel.setBorder(0);
			tierPanel.setCellpadding("0");
			tierPanel.setCellspacing("0");
		//	tierPanel.setBgcolor("#cccccc");
	
			StringBuffer rows = new StringBuffer();
	
			//setting values to benefit levels
			
			int size = 0;
			if(null != tierDefinitionList ){
				size = tierDefinitionList.size();
				sortTiers(tierDefinitionList);
			}
	
			HtmlPanelGrid tierDefPanel = null;
			HtmlPanelGrid tierCritPanel = null;
			HtmlPanelGrid tierHeaderPanel = null;
			HtmlPanelGrid tierHeaderPanel_wclc = null;
			HtmlPanelGrid tierLevelPanel = null;
	
			BenefitTierDefinition tierDefinition = null;
	
			List tierList;
			List critList = null;
			BenefitTierCriteria tierCriteria = null;
			//iterating to get the benefit levels
			for (int l = 0; l < size; l++) {
				tierDefinition = (BenefitTierDefinition) getTierDefinitionList().get(l);
				rowNumber++;
				tierDefPanel = new HtmlPanelGrid();
				tierDefPanel.setColumns(1);
				tierDefPanel.setWidth("100%");
				//tierDefPanel.setId("tierDefPanel"+RandomStringUtils.randomAlphanumeric(15));
				tierDefPanel.setBorder(0);
				tierDefPanel.setCellpadding("0");
				tierDefPanel.setCellspacing("0");//cccccccc
			//	tierDefPanel.setBgcolor("#cccccc");
	
				HtmlOutputLabel defLabel = new HtmlOutputLabel();
				defLabel.setId("defLabel"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputText tierDef = new HtmlOutputText();
				tierDef.setStyleClass("dataTableHeader1");
				tierDef.setValue(tierDefinition.getBenefitTierDefinitionName() );
				
				tierDef.setId("tierDef_"+tierDefinition.getBenefitTierDefinitionSysId()+"_Id");

			//	tierDef.setStyle("border-right:solid #cccccc 1px;"); //Ch
				
				HtmlInputHidden hidDefId = new HtmlInputHidden();
				hidDefId.setValue(tierDefinition.getBenefitTierDefinitionName());
				hidDefId.setId("tierDef_"+tierDefinition.getBenefitTierDefinitionSysId()+"_HiddenId");
				
				defLabel.getChildren().add(tierDef);
				defLabel.getChildren().add(hidDefId);
				tierDefPanel.getChildren().add(defLabel);
	
				tierPanel.getChildren().add(tierDefPanel);
	
				tierList = tierDefinition.getBenefitTiers();
				BenefitTier tier = null;
				for (int m = 0; m < tierList.size(); m++) {
					rows.append("dataTableOddRow");
					tier = (BenefitTier) tierList.get(m);
					critList = tier.getBenefitTierCriteriaList();
					HtmlInputHidden hiddenTierId = new HtmlInputHidden();
					hiddenTierId.setValue(new Integer(tier.getBenefitTierSysId()));
					hiddenTierId.setId("tierDef_"+tier.getBenefitTierSysId()+"_"+m);
					
					tierCritPanel = new HtmlPanelGrid();
					tierCritPanel.setColumns(1);
					tierCritPanel.setWidth("100%");
					//tierCritPanel.setId("tierCritPanel"+RandomStringUtils.randomAlphanumeric(15));
					tierCritPanel.setBorder(0);
				//	tierCritPanel.setStyleClass("headerPanel1");
					tierCritPanel.setCellpadding("0");
					tierCritPanel.setCellspacing("0");
					tierCritPanel.setBgcolor("#cccccc");
					tierCritPanel.setRowClasses("dataTableOddRow"); //ss
					
					tierHeaderPanel = new HtmlPanelGrid();
					tierHeaderPanel.setColumns(3);
					tierHeaderPanel.setWidth("100%");
					tierHeaderPanel.setColumnClasses("gridColumn40,gridColumn40,gridColumnRight20");
					tierHeaderPanel.setStyle("border-right:solid #cccccc 1px;border-left:solid #cccccc 1px;height:30px;");
					tierHeaderPanel.setId("tierHeaderPanel"+RandomStringUtils.randomAlphanumeric(15));
					tierHeaderPanel_wclc = new HtmlPanelGrid();
					tierHeaderPanel_wclc.setColumns(3);
					tierHeaderPanel_wclc.setWidth("100%");
					tierHeaderPanel_wclc.setColumnClasses("gridColumn40,gridColumn40,gridColumnRight20");
					tierHeaderPanel_wclc.setStyle("border-right:solid #cccccc 1px;border-left:solid #cccccc 1px;height:0px;");
	
					tierNo++;
					HtmlOutputLabel tierlabel = new HtmlOutputLabel();
					tierlabel.setId("tierl"+RandomStringUtils.randomAlphanumeric(15));
					HtmlOutputLabel tierlabel1 = new HtmlOutputLabel();
					tierlabel1.setId("tierlab1"+RandomStringUtils.randomAlphanumeric(15));
					HtmlOutputText dummylabel = new HtmlOutputText();
					//dummylabel.setStyle("width:50px;");
					HtmlOutputText dummylabel1 = new HtmlOutputText();
					//dummylabel1.setStyle("width:250px;");
					
					
					if (null != critList) {
						for (int k = 0; k < critList.size(); k++) {
							if(k<2)
								tierlabel.getChildren().add(dummylabel);
							else
								tierlabel1.getChildren().add(dummylabel);
								
							tierCriteria = new BenefitTierCriteria();
							tierCriteria = (BenefitTierCriteria) critList.get(k);
							HtmlOutputText tierCrit = new HtmlOutputText();
							tierCrit.setStyle("color:blue");
							tierCrit.setValue(tierCriteria
									.getBenefitTierCriteriaName()+"* : ");
							tierCrit.setId("critVal_"+ k + "_" + m + "_" + tierNo);
							if(k<2)
								tierlabel.getChildren().add(tierCrit);
							else
								tierlabel1.getChildren().add(tierCrit);

							if(isViewMode() || isPrintMode()){
								HtmlOutputText critValueView = new HtmlOutputText();
								critValueView.setValue(tierCriteria.getBenefitTierCriteriaValue());
								if (k<2)	
									tierlabel.getChildren().add(critValueView);
								else
									tierlabel1.getChildren().add(critValueView);
							}else{
							HtmlInputText critVal = null;
							HtmlSelectOneMenu preAuthValue = null;
							HtmlOutputText  spaceChar = null;
							
							if (tier.getCriteriaIndicator().equals("2")){
	                            critVal = new HtmlInputText();
	                            critVal.setId("critVal_" + k + "_" + m + "_" + tierNo + "label" + tier.getBenefitTierSysId());
	                            critVal.setTitle("CriteriaValue");
	                            critVal.setMaxlength(10);
	                            // Added by Narasimha to fix PROD00055203
	                            if(tierDefinition.getDataType().equalsIgnoreCase("numeric")){
	                                   critVal.setOnkeypress("return isNum();");
	                                   critVal.setMaxlength(3);
	                            }
	                            critVal.setStyleClass("formInputField");
	                            critVal.setStyle("width:50px;");
	                            critVal.setValue(tierCriteria.getBenefitTierCriteriaValue());
	                            String keyForMap = formKeyforMap(tierDefinition
	                            					.getBenefitTierDefinitionSysId(), tier
	                                                .getBenefitTierSysId(), tierCriteria
													.getBenefitTierCriteriaSysId());
	                            keyForMap = "\"".concat(keyForMap).concat("\"");
	                            ValueBinding critValBind = FacesContext.getCurrentInstance().getApplication()
	                                                            .createValueBinding("#{contractCoverageBackingBean.tierCriteriaMap["
	                                                            		+ keyForMap + "]}");
	                            critVal.setValueBinding("value", critValBind);
	                            if(tierDefinition.getDataType().equalsIgnoreCase("date")){
	                            	 critVal.setOnmousedown("return DisableRightClick(event);");
	                            	 critVal.setOnkeydown("return false;");
	                                 critVal.setStyle("width : 80px;");
	                                 HtmlCommandButton cmdBtn = new HtmlCommandButton();
	                                 spaceChar = new HtmlOutputText();
	                                 spaceChar.setStyle("width : 20px;");
	                                 cmdBtn.setId("calBtn"+RandomStringUtils.randomAlphanumeric(15));
	                                 //cmdBtn.setId("calBtn"+k+m+tierNo);
	                                 String fieldId = cmdBtn.getId();
	                                 cmdBtn.setOnclick("changeId(this);cal3.select('contractCoverageForm:"+critVal.getId()+"','"+fieldId+"','MM/dd'); return false;");
	                            cmdBtn.setImage("../../images/cal.gif");
	                            cmdBtn.setStyle("cursor: hand; valign: middle");
	                            cmdBtn.setAlt("Calender");
	                            keyForMap = formKeyforMap(tierDefinition.getBenefitTierDefinitionSysId(), tier
	                                               .getBenefitTierSysId(), tierCriteria.getBenefitTierCriteriaSysId());
	                            keyForMap = "\"".concat(keyForMap).concat("\"");
	                            critValBind = FacesContext.getCurrentInstance().getApplication()
												.createValueBinding("#{contractCoverageBackingBean.tierCriteriaMap["+ keyForMap + "]}");
	                            critVal.setValueBinding("value", critValBind);
	                            tierlabel.getChildren().add(critVal);
	                            tierlabel.getChildren().add(spaceChar);
	                            tierlabel.getChildren().add(cmdBtn);
	                            tierHeaderPanel.getChildren().add(tierlabel);
                        		tierlabel = new HtmlOutputLabel();
                        		tierlabel.setId("tierlab"+RandomStringUtils.randomAlphanumeric(15));
	                            }else 
	                            {
	                            	if(k<2){
	                            		tierlabel.getChildren().add(critVal);
	                            		tierHeaderPanel.getChildren().add(tierlabel);
	                            		tierlabel = new HtmlOutputLabel();
	                            		tierlabel.setId("tierla"+RandomStringUtils.randomAlphanumeric(15));
	                            	}
	                            	else{
	                            		tierlabel1.getChildren().add(critVal);
	                            		tierHeaderPanel_wclc.getChildren().add(tierlabel1);
	                            		tierlabel1 = new HtmlOutputLabel();
	                            		tierlabel1.setId("tierlabl1"+RandomStringUtils.randomAlphanumeric(15));
	                            	}
	                            }
	                        }else if (tier.getCriteriaIndicator().equals("1")) {
	                              if(fetchPosibleValuesList(tierCriteria.getBenefitTierCriteriaName()).size()>2){
	                              	UIColumn col = new UIColumn();
	                              	HtmlInputText eligibilText = new HtmlInputText();
	                              	eligibilText.setOnmousedown("return DisableRightClick(event);");
	                              	eligibilText.setTitle("CriteriaValue");
	                              	eligibilText.setId("eligibility" + k + "_" + m + "_" + tierNo + "label" + tier.getBenefitTierSysId());
	                              	eligibilText.setStyleClass("selectDataDisplayDiv");
	                              	eligibilText.setValue(tierCriteria.getBenefitTierCriteriaValue());
	                              	eligibilText.setStyle("width:50px;");
	                              	eligibilText.setOnkeydown("return false;");
	                              	
	                              	HtmlCommandButton cmdBtn = new HtmlCommandButton();
	                              	spaceChar = new HtmlOutputText();
	                              	spaceChar.setStyle("width : 20px;");
	                              	cmdBtn.setOnclick("ewpdModalWindow_ewpd('../popups/possibleValuesPopupForContractPage.jsp'+getUrl()+'?criteriaStirng="+tierCriteria.getBenefitTierCriteriaName()+"','contractCoverageForm:"+eligibilText.getId()+"','contractCoverageForm:"+eligibilText.getId()+"',2,2);return false;");
	                                cmdBtn.setImage("../../images/select.gif");
	                                cmdBtn.setStyle("cursor: hand; valign: middle");
	                                cmdBtn.setAlt("Possible Values");
	                                cmdBtn.setTitle("Possible Values");
	                                cmdBtn.setId("calBtn"+RandomStringUtils.randomAlphanumeric(15));
	                                String keyForMap = formKeyforMap(tierDefinition.getBenefitTierDefinitionSysId(), 
	                            			tier.getBenefitTierSysId(), tierCriteria.getBenefitTierCriteriaSysId());
	                                keyForMap = "\"".concat(keyForMap).concat("\"");
	                                ValueBinding critValBind = FacesContext.getCurrentInstance().getApplication()
	                                               .createValueBinding("#{contractCoverageBackingBean.tierCriteriaMap["+ keyForMap + "]}");
	                                eligibilText.setValueBinding("value", critValBind);
	                                col.getChildren().add(eligibilText);
	                                col.getChildren().add(spaceChar);
	                                col.getChildren().add(cmdBtn);
	                                tierlabel.getChildren().add(col);
	                                tierHeaderPanel.getChildren().add(tierlabel);
                            		tierlabel = new HtmlOutputLabel();
                            		tierlabel.setId("tierl"+RandomStringUtils.randomAlphanumeric(15));
	                            }else if(fetchPosibleValuesList(tierCriteria.getBenefitTierCriteriaName()).size()<=2){
	                                UIColumn col = new UIColumn();
	                                BenefitTierCriteriaPsblValue benefitTierCrtPsbl;
	                                List tierCriteriaPsblList = fetchPosibleValuesList(tierCriteria.getBenefitTierCriteriaName());
	                                HtmlSelectOneMenu crSelect = new HtmlSelectOneMenu();
	                                UISelectItems items = new UISelectItems();
	                                crSelect.setId("crSelect_" + k + "_" + m + "_" + tierNo + "label" + tier.getBenefitTierSysId());
	                                List list = new ArrayList(); 
	                                for(int z=0;z<tierCriteriaPsblList.size();z++){
	                                	benefitTierCrtPsbl = (BenefitTierCriteriaPsblValue) tierCriteriaPsblList.get(z);
	                                	list.add(new SelectItem(benefitTierCrtPsbl.getBenefitTierCtrPsblValue(),benefitTierCrtPsbl.getBenefitTierCtrPsblValue()));   
	                                }
	                                items.setValue(list);
	                                crSelect.setValue(tierCriteria.getBenefitTierCriteriaValue());
	                                crSelect.getChildren().add(items);
	                                crSelect.setStyle("width:100px;");
	                                String keyForMap = formKeyforMap(tierDefinition.getBenefitTierDefinitionSysId(), 
	                                			tier.getBenefitTierSysId(), tierCriteria.getBenefitTierCriteriaSysId());
	                                keyForMap = "\"".concat(keyForMap).concat("\"");
	                                ValueBinding critValBind = FacesContext.getCurrentInstance().getApplication()
	                                                   .createValueBinding("#{contractCoverageBackingBean.tierCriteriaMap["+ keyForMap + "]}");
	                                crSelect.setValueBinding("value", critValBind);
	                                col.getChildren().add(crSelect);
	                                tierlabel.getChildren().add(col);
	                                tierHeaderPanel.getChildren().add(tierlabel);
                            		tierlabel = new HtmlOutputLabel();
                            		tierlabel.setId("tier"+RandomStringUtils.randomAlphanumeric(15));
	                               }
	                        }
							}
						}
					}
					HtmlCommandButton deleteButton = new HtmlCommandButton();
					//deleteButton.setId("deleteButton" + m + "_"
							//+ tier.getBenefitTierSysId() + tierNo);
					deleteButton.setId("deleteButton"+RandomStringUtils.randomAlphanumeric(15));
					deleteButton.setValue("Delete");
					deleteButton.setStyleClass("wpdButton");
					deleteButton.setOnclick("deleteTier("
							+ tier.getBenefitTierSysId() + "); return false;");
					if(!isViewMode() ){
						if(critList.size() < 2)
						{ 	
							tierlabel.getChildren().add(dummylabel1);
							tierHeaderPanel.getChildren().add(tierlabel);
							tierlabel.getChildren().add(deleteButton);
							tierHeaderPanel.getChildren().add(tierlabel);
							//tierHeaderPanel.getChildren().add(deleteButton);
							tierCritPanel.getChildren().add(tierHeaderPanel);
						}
						else if(critList.size() == 2){
							
							tierlabel.getChildren().add(deleteButton);
							tierHeaderPanel.getChildren().add(tierlabel);
							//tierHeaderPanel.getChildren().add(deleteButton);
							tierCritPanel.getChildren().add(tierHeaderPanel);
							
						}else
						{
							tierlabel.getChildren().add(dummylabel1);
							tierHeaderPanel.getChildren().add(tierlabel);
							//tierHeaderPanel.getChildren().add(deleteButton);
							tierCritPanel.getChildren().add(tierHeaderPanel);
							
							tierlabel1.getChildren().add(deleteButton);
							tierHeaderPanel_wclc.getChildren().add(tierlabel1);
							//tierHeaderPanel_wclc.getChildren().add(deleteButton);
							tierCritPanel.getChildren().add(tierHeaderPanel_wclc);
							//tierCritPanel.getChildren().add(deleteButton);
						}
					}else{
						tierlabel.getChildren().add(hiddenTierId);
						tierlabel.setStyle("width:100%;border-right:solid #cccccc 1px;border-left:solid #cccccc 1px;height:30px;");
						tierCritPanel.getChildren().add(tierlabel);
						if(critList.size() > 2){
							tierlabel1.getChildren().add(hiddenTierId);
							tierlabel1.setStyle("width:100%;border-right:solid #cccccc 1px;border-left:solid #cccccc 1px;height:30px;");
							tierCritPanel.getChildren().add(tierlabel1);
						}
						
					}
						int sizeOfLevelList = 0;
						if (lvlLineList != null) {
							sizeOfLevelList = lvlLineList.size();
						}
						tierLevelPanel = new HtmlPanelGrid();
						tierLevelPanel.setColumns(8);
	
						/* START CARS */
						if(isViewMode() ){
							tierLevelPanel.setColumnClasses("gridColumn17,gridColumn14,gridColumn19,gridColumn7,gridColumn8,gridColumn10,gridColumn17,gridColumn8");
						}else{
							tierLevelPanel.setColumnClasses("gridColumn19,gridColumn12,gridColumn15,gridColumn5,gridColumn7,gridColumn12,gridColumn22,gridColumn8");
						}
						/* END CARS */
						
						tierLevelPanel.setWidth("100%");
						tierLevelPanel.setId("tierLevelPanel"+RandomStringUtils.randomAlphanumeric(15));
						tierLevelPanel.setBorder(0);
						tierLevelPanel.setCellpadding("3");
						tierLevelPanel.setCellspacing("1");
						tierLevelPanel.setBgcolor("#cccccc");
					//	tierLevelPanel.setRowClasses("dataTableEvenRow");
						rows.delete(0, rows.length());         
						for (int i = 0; i < (sizeOfLevelList ); i++) {
	
							BenefitLevel benefitLevelValues = (BenefitLevel) getLvlLineList()
									.get(i);
	
							if (benefitLevelValues.getTierSysId() == tier
									.getBenefitTierSysId()) {
								
							
	
								//gets the benefit lines of a benefit level
								List benefitLines = benefitLevelValues
										.getBenefitLines();
								 if(benefitLevelValues.isLevelHideStatus()) {
			                        	rows.append("hiddenFieldLevelDisplay");	                        	
			                        	}
			                        else {		                            
			                            rows.append("dataTableEvenRow"); }
			                        rows.append(",");
								
								//setting the benefit level values to the panel grid
								setBenefitLevelValuesToTierGrid(i, benefitLevelValues, tier,
										benefitLines.size(), rowNumber, lineCount,
										tierLevelPanel);
									
								 
								//	rows.append(",");
								//iterating to get the individual benefit lines
								for (int j = 0; j < benefitLines.size(); j++) {
									rowNumber++;
	
									//selects an individual benefit line
									BenefitLine benefitLineValues = (BenefitLine) benefitLines
											.get(j);
										
			                          
									lineCount = lineCount + 1;								
									 if(benefitLineValues.isLineHideStatus())
						                	rows.append("hiddenFieldDisplay");
						                else
						                	rows.append("dataTableOddRow");
									 rows.append(",");
									setBenefitLineValuesToTierGrid(benefitLevelValues,
											j, benefitLineValues, i, tier, benefitLines
													.size(), lineCount, tierLevelPanel,false);
	
								}
							}
						}					
						tierLevelPanel.setRowClasses(rows.toString());
						tierCritPanel.getChildren().add(tierLevelPanel);
						tierDefPanel.getChildren().add(tierCritPanel);			
	
				}
				
	
			}
		}
		Logger.logInfo(th.endPerfLogging());
		return tierPanel;
	}
	
	
	/**
	 * This method is written to cover a possible bug with panel renderer.
	 * The panelGrid is not getting rendered on page refresh.
	 * Solution is to bind this method to faces context (faces-config.xml)and call this
	 * method on load based on flag value.
	 * @return
	 */
	public String showTieredLevelSection(){
		this.submitFlag = true;
		return "loadTieredLevelPanel";
	}
	/**
	 * @return Returns the panelForTierPrint.
	 */
	public HtmlPanelGrid getPanelForTierPrint() {
		Logger.logInfo("entered method getPanelForTierPrint");
		this.setPrintMode(true);	
		int rowNumber = 0;
		int lineCount = 0;
		int tierNo = 0;
		
		List benefitDefinitonsList = this.getBenefitDefinitionsList();

		//This method gets the values from the benefit definiton List and sets
		// it to the level list and line list
		getValuesFromDefinitonList(benefitDefinitonsList);

		panelForTierPrint = new HtmlPanelGrid();
		panelForTierPrint.setColumns(1);
		panelForTierPrint.setWidth("100%");
		panelForTierPrint.setBorder(0);
		panelForTierPrint.setCellpadding("1");
		panelForTierPrint.setCellspacing("1");
		panelForTierPrint.setId("panelForTierPrint"+RandomStringUtils.randomAlphanumeric(15));
		StringBuffer rows = new StringBuffer();
		//setting values to benefit levels
		
		int size = 0;
		if(null != tierDefinitionList ){
			size = tierDefinitionList.size();
			sortTiers(tierDefinitionList);
		}

		HtmlPanelGrid tierDefPanel = null;
		
		HtmlPanelGrid tierCritPanel = null;
		
		HtmlPanelGrid tierLevelPanel = null;

		BenefitTierDefinition tierDefinition = null;

		List tierList;
		List critList = null;
		BenefitTierCriteria tierCriteria = null;
		//iterating to get the benefit levels
		for (int l = 0; l < size; l++) {
			tierDefinition = (BenefitTierDefinition) getTierDefinitionList().get(l);
			rowNumber++;
			tierDefPanel = new HtmlPanelGrid();
			tierDefPanel.setColumns(1);
			tierDefPanel.setWidth("100%");
			tierDefPanel.setId("tierDefPanel"+RandomStringUtils.randomAlphanumeric(15));
			tierDefPanel.setBorder(0);
			tierDefPanel.setCellpadding("3");
			tierDefPanel.setCellspacing("1"); 

			HtmlOutputLabel defLabel = new HtmlOutputLabel();
			defLabel.setId("defLabel"+RandomStringUtils.randomAlphanumeric(15));
			HtmlOutputText tierDef = new HtmlOutputText();
			tierDef.setValue(tierDefinition.getBenefitTierDefinitionName() );
			HtmlInputHidden hidDefId = new HtmlInputHidden();
			hidDefId.setId("hidDefId"+l);
			hidDefId.setValue(tierDefinition.getBenefitTierDefinitionName());			
			defLabel.getChildren().add(tierDef);
			defLabel.getChildren().add(hidDefId);
			tierDefPanel.getChildren().add(defLabel);

			panelForTierPrint.getChildren().add(tierDefPanel);

			tierList = tierDefinition.getBenefitTiers();
			BenefitTier tier = null;
			for (int m = 0; m < tierList.size(); m++) {
				rows.append("dataTableOddRow");
				tier = (BenefitTier) tierList.get(m);
				critList = tier.getBenefitTierCriteriaList();
				HtmlInputHidden hiddenTierId = new HtmlInputHidden();
				hiddenTierId.setId("hiddenTierId"+l+m);
				hiddenTierId.setValue(new Integer(tier.getBenefitTierSysId()));								
				tierCritPanel = new HtmlPanelGrid();
				tierCritPanel.setColumns(1);
				tierCritPanel.setWidth("100%");
				tierCritPanel.setId("tierCritPanel"+RandomStringUtils.randomAlphanumeric(15));
				tierCritPanel.setBorder(0);
				tierCritPanel.setCellpadding("3");
				tierCritPanel.setCellspacing("1"); 
				tierCritPanel.setBgcolor("#cccccc");
				tierCritPanel.setRowClasses("dataTableOddRow"); 

				tierNo++;
				tierNo++;
				HtmlOutputLabel tierlabel = new HtmlOutputLabel();
				tierlabel.setId("tierlbe"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputText dummylabel = new HtmlOutputText();
				dummylabel.setStyle("width:50px;");
				HtmlOutputText dummylabel1 = new HtmlOutputText();
				dummylabel1.setStyle("width:250px;");

				if (null != critList) {
					for (int k = 0; k < critList.size(); k++) {
						tierlabel.getChildren().add(dummylabel);
						tierCriteria = new BenefitTierCriteria();
						tierCriteria = (BenefitTierCriteria) critList.get(k);
						HtmlOutputText tierCrit = new HtmlOutputText();
						tierCrit.setValue(tierCriteria
								.getBenefitTierCriteriaName()+"* : ");
						tierlabel.getChildren().add(tierCrit);
						HtmlOutputText critValueView = new HtmlOutputText();	
							critValueView.setValue(tierCriteria.getBenefitTierCriteriaValue());
						tierlabel.getChildren().add(critValueView);
					}
				}				
				tierlabel.getChildren().add(hiddenTierId);
				tierCritPanel.getChildren().add(tierlabel);
				int sizeOfLevelList = 0;
				if (lvlLineList != null) {
					sizeOfLevelList = lvlLineList.size();
				}
				tierLevelPanel = new HtmlPanelGrid();
				tierLevelPanel.setColumns(8);
				tierLevelPanel.setWidth("100%");
				tierLevelPanel.setColumnClasses("gridColumn22,gridColumn12,gridColumn16,gridColumn8,gridColumn10,gridColumn10,gridColumn22,gridColumn0");
				tierLevelPanel.setId("tierLevelPanel"+RandomStringUtils.randomAlphanumeric(15));
				tierLevelPanel.setBorder(0);
				tierLevelPanel.setCellpadding("3");
				tierLevelPanel.setCellspacing("1");
				tierLevelPanel.setBgcolor("#cccccc");
				tierLevelPanel.setRowClasses("dataTableEvenPrintRow");
				
				for (int i = 0; i < (sizeOfLevelList ); i++) {
					BenefitLevel benefitLevelValues = (BenefitLevel) getLvlLineList()
							.get(i);
					if (benefitLevelValues.getTierSysId() == tier
							.getBenefitTierSysId()) {						
						rows.append("dataTableEvenPrintRow");
						//gets the benefit lines of a benefit level
						List benefitLines = benefitLevelValues
								.getBenefitLines();
						//setting the benefit level values to the panel grid
						setBenefitLevelValuesToTierGrid(i, benefitLevelValues, tier,
								benefitLines.size(), rowNumber, lineCount,
								tierLevelPanel);

						if (benefitLines.size() != 0)
							rows.append(",");
						//iterating to get the individual benefit lines
						for (int j = 0; j < benefitLines.size(); j++) {
							rowNumber++;
							//selects an individual benefit line
							BenefitLine benefitLineValues = (BenefitLine) benefitLines
									.get(j);
							lineCount = lineCount + 1;
							rows.append("dataTableOddRow");
							if (i < ((size) - 1))
								rows.append(",");
							else if (j < (benefitLines.size() - 1))
								rows.append(",");
							//sets the benefit lines of a benefit level to the panle grid
							setBenefitLineValuesToTierGrid(benefitLevelValues,
									j, benefitLineValues, i, tier, benefitLines
											.size(), lineCount, tierLevelPanel,true);

						}
					}
				}
				tierCritPanel.getChildren().add(tierLevelPanel);
				tierDefPanel.getChildren().add(tierCritPanel);			

			}
			

		}

		return panelForTierPrint;
	}

	public void setTierPanel(HtmlPanelGrid tierPanel) {
		this.tierPanel = tierPanel;
	}
	
	 private void setBenefitLineValuesToTierGrid(BenefitLevel benefitLevelValues,int j, BenefitLine benefitLineValues, 
	            int i,BenefitTier tier, int lineSize,int lineNum,HtmlPanelGrid tierLevelPanel,boolean isPrintMode) {

	        Logger.logInfo("entered method setBenefitLineValuesToTierGrid");

	        HtmlOutputText lineDesc = new HtmlOutputText();
	        lineDesc.setId("lineDesc" + benefitLineValues.getLineSysId()+tier.getBenefitTierSysId()+lineNum);
	        tierLevelPanel.setId("notesColumnId" + benefitLineValues.getLineSysId()+tier.getBenefitTierSysId()+lineNum);
	        
	        lineDesc.setValue(" ");
	        HtmlOutputText lineTerm = new HtmlOutputText();
	        lineTerm.setId("lineTerm" + benefitLineValues.getLineSysId()+tier.getBenefitTierSysId()+lineNum);
	        lineTerm.setValue("");
	        HtmlOutputText lineQualifier = new HtmlOutputText();
	        lineQualifier.setId("lineQualifier" + benefitLineValues.getLineSysId()+tier.getBenefitTierSysId()+lineNum);
	        lineQualifier.setValue("");
	        HtmlOutputText linePVA = new HtmlOutputText();
	        linePVA.setId("linePVA" + benefitLineValues.getLineSysId()+tier.getBenefitTierSysId()+lineNum);
	        linePVA.setValue(benefitLineValues.getProviderArrangementId());
	        //HtmlOutputText lineDataType = new HtmlOutputText();
	        //lineDataType.setValue(benefitLineValues.getDataTypeDesc());
	        //output text for view
	        HtmlOutputText lineBnftValueView = new HtmlOutputText();
	        
	        HtmlInputHidden hiddenLineId = new HtmlInputHidden();
	        HtmlInputHidden hiddenCustomizedSysId = new HtmlInputHidden();
	        
	        hiddenLineId.setId("hiddenTierLineId" + j + "_"+ i+lineNum + tier.getBenefitTierSysId());
	        hiddenCustomizedSysId.setId("hiddenTierCustomizedSysId"+j+"_"+i+lineNum + tier.getBenefitTierSysId());

	        hiddenLineId.setValue(new Integer(benefitLevelValues.getLevelId())+":"+new Integer(benefitLineValues.getLineSysId())
	                + ":" + benefitLineValues.getBenefitValue() + ":"
	                + benefitLineValues.getDataTypeId() + ":"
	                + benefitLevelValues.getLevelDesc() + ":"+ tier.getBenefitTierSysId());
	        hiddenCustomizedSysId.setValue(new Integer(benefitLineValues.getCustomizedSysId()));
	        // set the value to the map 
	        /*CARS START*/
	        ValueBinding lineIdValBind = FacesContext.getCurrentInstance()
	                .getApplication().createValueBinding(
	                        "#{contractCoverageBackingBean.tierLineIdMap["+benefitLineValues.getLineSysId()+"."+tier.getBenefitTierSysId()+"]}");
	        
	        hiddenLineId.setValueBinding("value", lineIdValBind);
	        HtmlInputHidden hiddenTierLevelIds = new HtmlInputHidden();
	        hiddenTierLevelIds.setId("hiddenTierLevelIds" + j + "_" + i + lineNum + tier.getBenefitTierSysId());

	        hiddenTierLevelIds.setValue(new Integer(benefitLevelValues.getLevelId())
					+ ":" + new Integer(benefitLineValues.getLineSysId()));
			// set the value to the map	      
	        String keyForTierMap = formKeyforMap(benefitLevelValues.getLevelId(), benefitLineValues.getLineSysId(), tier.getBenefitTierSysId());
	        keyForTierMap = "\"".concat(keyForTierMap).concat("\"");
			ValueBinding levelTierIdsValBind = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{contractCoverageBackingBean.tierLevelIdsMap["+keyForTierMap+"]}");			
			
			hiddenTierLevelIds.setValueBinding("value", levelTierIdsValBind);
	        
	        /*CARS END*/	
	        HtmlInputText lineBnftValue = new HtmlInputText();
	        HtmlOutputText lineBnftValuePrint = new HtmlOutputText();
	        
	      //  lineBnftValue.setReadonly(true);
	        HtmlSelectOneMenu seloneMenuForBnftValue = new HtmlSelectOneMenu();
	        UIComponent object = null;
	        String sysDataType = null;
	        int dataType = 0;
	        String dataTypeId = benefitLineValues.getDataTypeId();
	        if (null != dataTypeId && !"".equals(dataTypeId)) {
	            dataType = Integer.parseInt(dataTypeId);
	        }
	        List universeDataTypeList = WPDStringUtil.getUniverseDataTypeList();
	        if (dataType != 0) {
	            DataTypeLocateResult dataTypeDetails = null;
	            dataTypeDetails = WPDStringUtil.getDataTypeDetails(
	                    universeDataTypeList, dataType);
	            if (null != dataTypeDetails) {
	                sysDataType = dataTypeDetails.getDataTypeDesc().toUpperCase()
	                        .trim();
	            }
	        }
	        if (null != sysDataType) {
	            if (sysDataType.equals("BOOLEAN")) {
	                UISelectItems selectItems = new UISelectItems();
	                List possibleBnftVal = new ArrayList();
	                possibleBnftVal.add(new SelectItem("", ""));
					// Code changed as part of the Enhancement to display the benefit values same as 
					//that of the benefit value in the benefit
					if(null != benefitLineValues.getBenefitValue() && 
							!benefitLineValues.getBenefitValue().equals("")){
						if(benefitLineValues.getBenefitValue().equals("Y") || benefitLineValues.getBenefitValue().equals("N")){
								possibleBnftVal.add(new SelectItem("Y", "Y"));
								possibleBnftVal.add(new SelectItem("N", "N"));
						}else{
								possibleBnftVal.add(new SelectItem("YES", "YES"));
								possibleBnftVal.add(new SelectItem("NO", "NO"));
						}
					}else{
						if(null != benefitLineValues.getLineValue() && 
								!benefitLineValues.getLineValue().equals("")){
							if(benefitLineValues.getLineValue().equals("Y") || benefitLineValues.getLineValue().equals("N")){
										possibleBnftVal.add(new SelectItem("Y", "Y"));
										possibleBnftVal.add(new SelectItem("N", "N"));
							}else{
										possibleBnftVal.add(new SelectItem("YES", "YES"));
										possibleBnftVal.add(new SelectItem("NO", "NO"));
							}
						}else{
							possibleBnftVal.add(new SelectItem("YES", "YES"));
							possibleBnftVal.add(new SelectItem("NO", "NO"));
						}
					}	
	                selectItems.setValue(possibleBnftVal);
	                seloneMenuForBnftValue.getChildren().add(selectItems);
	                if(null != benefitLineValues.getBenefitValue() && 
							!benefitLineValues.getBenefitValue().equals("")){
						if(benefitLineValues.getBenefitValue().equals("YES")){
								seloneMenuForBnftValue.setValue("YES");
								lineBnftValueView.setValue("YES");					
						}else if(benefitLineValues.getBenefitValue().equals("NO")){
								seloneMenuForBnftValue.setValue("NO");
								lineBnftValueView.setValue("NO");	
						}
						else if(benefitLineValues.getBenefitValue().equals("Y")){
								seloneMenuForBnftValue.setValue("Y");
								lineBnftValueView.setValue("Y");	
						}
						else if(benefitLineValues.getBenefitValue().equals("N")){
								seloneMenuForBnftValue.setValue("N");
								lineBnftValueView.setValue("N");	
						}else{
								seloneMenuForBnftValue.setValue("");
								lineBnftValueView.setValue("");	
						}
					}
	                seloneMenuForBnftValue.setId("lineBnftValue" + j + "_" + i+lineNum+tier.getBenefitTierSysId() + "_tierGrid" );
	                object = (HtmlSelectOneMenu) seloneMenuForBnftValue;
	                String keyForMap = formKeyforMap(benefitLevelValues.getLevelId(), benefitLineValues.getLineSysId(), tier.getBenefitTierSysId());
	                keyForMap = "\"".concat(keyForMap).concat("\"");
	                // set the value to the map
	                ValueBinding valueItem = FacesContext.getCurrentInstance()
	                        .getApplication().createValueBinding(
	                                "#{contractCoverageBackingBean.tierLineValueMap["
	                                        + keyForMap
	                                        + "]}");
	                seloneMenuForBnftValue.setValueBinding("value", valueItem);
	            } else {
	            	if(!benefitLineValues.isLineHideStatus()){
	                lineBnftValue.setSize(6);
	                lineBnftValue.setId("lineBnftValue" + j + "_" + i+lineNum + tier.getBenefitTierSysId() + "_tierGrid" );
	                lineBnftValue.setValue(benefitLineValues.getBenefitValue());
	                lineBnftValuePrint.setValue(benefitLineValues.getBenefitValue());
	                if (!benefitLineValues.getDataTypeDesc().equalsIgnoreCase(
	                        "String")) {
	                }
	                lineBnftValue.setTitle("BenefitValue"
	                        + benefitLineValues.getDataTypeDesc());
	                String keyForMap = formKeyforMap(benefitLevelValues.getLevelId(), benefitLineValues.getLineSysId(), tier.getBenefitTierSysId());
	                keyForMap = "\"".concat(keyForMap).concat("\"");
	                ValueBinding valueItem = FacesContext.getCurrentInstance()
	                        .getApplication().createValueBinding(
	                                "#{contractCoverageBackingBean.tierLineValueMap["
	                                        + keyForMap
	                                        + "]}");
	                lineBnftValue.setValueBinding("value", valueItem);
	                lineBnftValue.setStyleClass("formInputField");
	                lineBnftValue.setStyle("width:50px;");
	                lineBnftValuePrint.setStyle("width:50px;");
	            }else{
	            	//lineBnftHideValue.setSize(10);
	            	 lineBnftValue.setSize(6);
	                 lineBnftValue.setId("lineBnftValue" + j + "_" + i+lineNum + tier.getBenefitTierSysId() + "_tierGrid" );
	                 lineBnftValue.setValue(benefitLineValues.getBenefitValue());
	                 lineBnftValuePrint.setValue(benefitLineValues.getBenefitValue());
	            	 lineBnftValue.setDisabled(benefitLineValues.isLineHideStatus());
	            	 if (!benefitLineValues.getDataTypeDesc().equalsIgnoreCase(
	                 "String")) {
	         }
	         lineBnftValue.setTitle("BenefitValue"
	                 + benefitLineValues.getDataTypeDesc());
	         String keyForMap = formKeyforMap(benefitLevelValues.getLevelId(), benefitLineValues.getLineSysId(), tier.getBenefitTierSysId());
             keyForMap = "\"".concat(keyForMap).concat("\"");
	         ValueBinding valueItem = FacesContext.getCurrentInstance()
	                 .getApplication().createValueBinding(
	                         "#{contractCoverageBackingBean.tierLineValueMap["
	                                 + keyForMap
	                                 + "]}");
	         lineBnftValue.setValueBinding("value", valueItem);
	         lineBnftValue.setStyleClass("formInputField");
	         lineBnftValue.setStyle("width:50px;");
	         lineBnftValuePrint.setStyle("width:50px;");
	            }
	        }
	        }
	        lineBnftValueView.setId("lineBnftValueView" + j + "_" + i+lineNum + tier.getBenefitTierSysId());
	        lineBnftValueView.setValue(benefitLineValues.getBenefitValue());
	        //lineBnftValueView.setStyleClass("formInputFieldReadOnly");
	        //lineBnftValueView.setStyle("width:50px;");
	        HtmlOutputText lineEmptyString = new HtmlOutputText();
	        lineEmptyString.setId("lineEmptyString" + benefitLineValues.getLineSysId()+tier.getBenefitTierSysId()+lineNum);
	        lineEmptyString.setValue(" ");

	        HtmlOutputLabel lblBnftValue = new HtmlOutputLabel();
	        lblBnftValue.setId("lblBnftVal"+RandomStringUtils.randomAlphanumeric(15));
	        //lblBnftValue.setId("lblBnftValue" + j + "_" + i+lineNum + tier.getBenefitTierSysId());
	        lblBnftValue.setFor("lineBnftValue" + j + "_" + i);
	        if (null != sysDataType) {
	            if (sysDataType.equals("DOLLAR")) {
	                    if (!isViewMode()) {
	                        //lblBnftValue.getChildren().add(lineDataType);
	                    	//if(isBenefitDisplayFlag())
	                    	if(isPrintMode){
	                    		lblBnftValue.getChildren().add(lineBnftValuePrint);
	                    	}else lblBnftValue.getChildren().add(lineBnftValue);
	                    	//else
	                    		//lblBnftValue.getChildren().add(lineBnftValueView);
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                    } else {
	                        //lblBnftValue.getChildren().add(lineDataType);
	                        lblBnftValue.getChildren().add(lineBnftValueView);
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                    }
	                
	            } else if (sysDataType.equals("PERCENTAGE")) {
	                    if (!isViewMode()) {
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        //if(isBenefitDisplayFlag())
	                        	if(isPrintMode) {
	                        		lblBnftValue.getChildren().add(lineBnftValuePrint);
	                        	}else lblBnftValue.getChildren().add(lineBnftValue);
	                        //else
	                        	//lblBnftValue.getChildren().add(lineBnftValueView);
	                        //lblBnftValue.getChildren().add(lineDataType);
	                    } else {
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        lblBnftValue.getChildren().add(lineBnftValueView);
	                        //lblBnftValue.getChildren().add(lineDataType);

	                    }
	                
	            } else if (sysDataType.equals("STRING")) {
	                    if (!isViewMode()) {
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        //if(isBenefitDisplayFlag())
	                        if(isPrintMode) {
	                        	lblBnftValue.getChildren().add(lineBnftValuePrint);
	                        }else lblBnftValue.getChildren().add(lineBnftValue);
	                        //else
	                        	//lblBnftValue.getChildren().add(lineBnftValueView);
	                    } else {
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        lblBnftValue.getChildren().add(lineBnftValueView);
	                    }
	            } else if (sysDataType.equals("BOOLEAN")) {
	                    if (!isViewMode() && !isPrintMode()) {
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        lblBnftValue.getChildren().add(object);
	                        //lblBnftValue.getChildren().add(lineDataType);
	                    } else {
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        lblBnftValue.getChildren().add(lineBnftValueView);
	                    }
	            } else {
	                    if (!isViewMode()) {
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        	if(isPrintMode){
	                        		lblBnftValue.getChildren().add(lineBnftValuePrint);	                     
	                        	}else lblBnftValue.getChildren().add(lineBnftValue);
	                    } else {
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        lblBnftValue.getChildren().add(lineBnftValueView);
	                    }
	            }
	            lblBnftValue.getChildren().add(hiddenLineId);
	            lblBnftValue.getChildren().add(hiddenCustomizedSysId);
	        }
	        
	        HtmlOutputText lineReference = new HtmlOutputText();
	        lineDesc.setValue(" ");
	        lineReference.setValue(benefitLineValues.getReferenceDesc());
	        lineReference.setId("lblRef" + j +"_"+ i+tier.getBenefitTierSysId()+lineNum);

	        HtmlOutputLabel lblRefAndNotes = new HtmlOutputLabel();
	        lblRefAndNotes.setId("lblRefAndNotes"+RandomStringUtils.randomAlphanumeric(15));
	       // lblRefAndNotes.setId("lblRefNotes" + j +"_"+ i+tier.getBenefitTierSysId()+lineNum);
	        //lblBnftValue.setId("lblRe"+RandomStringUtils.randomAlphanumeric(15));
	        //lblBnftValue.setId("lblRefAndNotes" + j + "_" + i+tier.getBenefitTierSysId()+lineNum);
	        //lblBnftValue.setFor("lblRef" + j +"_"+ i+tier.getBenefitTierSysId()+lineNum);

	        HtmlOutputLabel lblNotesImage = new HtmlOutputLabel();
	        lblNotesImage.setId("lblNotesImage"+RandomStringUtils.randomAlphanumeric(15));
	        //lblNotesImage.setId("lblNotesImage" + j + "_" + i+lineNum+tier.getBenefitTierSysId());
	        lblNotesImage.setFor("lblRef" + j +"_"+ i+tier.getBenefitTierSysId()+lineNum);

	        HtmlGraphicImage noteImage = new HtmlGraphicImage();
	        
	        noteImage.setUrl("../../images/notes_exist.gif");
	        noteImage.setStyle("cursor:hand;");
	        noteImage.setId("noteImage" + j + "_" + i+lineNum+tier.getBenefitTierSysId());
	               
			HtmlInputHidden hiddenNotesStatus = new HtmlInputHidden();
			hiddenNotesStatus.setId("hiddenNotesStatus" + j + "_" + i+lineNum + tier.getBenefitTierSysId());
			hiddenNotesStatus.setValue("");
	        HtmlCommandButton notesButton = new HtmlCommandButton();
	        notesButton.setValue("NotesButton");
	        if (benefitLineValues.getBnftLineNotesExist().equals("Y"))
	            notesButton.setImage("../../images/notes_exist.gif");
	        else
	            notesButton.setImage("../../images/page.gif");
	        notesButton.setTitle("Note");
	        notesButton.setStyle("border:0;");
	        notesButton.setAlt("Notes");
	        notesButton.setId("notesButton"+RandomStringUtils.randomAlphanumeric(15));
	        //notesButton.setId("notesButton" + j + "_" + i+lineNum+tier.getBenefitTierSysId());
	        //notesButton.setOnclick("ewpdModalWindow_ewpd('productBenefitLevelNotesOverridePopup.jsp?parentEntityType=ATTACHPRODUCT&lookUpAction=4&secondaryEntityId="
	        // + benefitLineValues.getLineSysId() + "&temp="+Math.random()+"' ,
	        // 'dummyDiv', 'benefitDefinitionForm:hidden1',2,1);return false;");

	        // lgnd data type
	        HtmlOutputText lgndDataType = new HtmlOutputText();
	        lgndDataType.setValue(benefitLineValues.getDataTypeLegend());
	        lgndDataType.setId("lgndDataType" + j + "_" + i+lineNum+tier.getBenefitTierSysId());
	        
	        if (!isViewMode()) {
	            /*notesButton.setOnclick("getUrl(" + benefitLineValues.getLineSysId()
	                    + ");return false;");*/
	            notesButton.setOnclick("getUrlForTier(" + benefitLineValues.getLineSysId()
	                    + ',' + j + ',' + i + ','+benefitLevelValues.getTierSysId()+","+lineNum+ ");return false;");
	        } else {
	        	notesButton.setOnclick("getUrlForTier(" + benefitLineValues.getLineSysId()
	                    + ',' + super.getBenefitComponentIdFromSession() + ',' + super.getContractKeyFromSession() + ','+benefitLevelValues.getTierSysId()+","+lineNum+ ");return false;");
	        }
	        lblRefAndNotes.getChildren().add(lineReference);
	        if (!isPrintMode()) {
	        	//if (!isViewMode()) {
	            lblNotesImage.getChildren().add(notesButton);
	            lblNotesImage.getChildren().add(hiddenNotesStatus);            
	        }	        
	        	tierLevelPanel.getChildren().add(lineDesc);
	        	tierLevelPanel.getChildren().add(lineTerm);
	        	tierLevelPanel.getChildren().add(lineQualifier);
	        	tierLevelPanel.getChildren().add(linePVA);
	        	tierLevelPanel.getChildren().add(lgndDataType);
	        	tierLevelPanel.getChildren().add(lblBnftValue);
	        	tierLevelPanel.getChildren().add(lblRefAndNotes);
            	tierLevelPanel.getChildren().add(lblNotesImage); 
            	tierLevelPanel.setId("notesColumnId" + benefitLineValues.getLineSysId()+tier.getBenefitTierSysId()+lineNum);
	            
	    }
	 
	 /**
	     * This method sets the benefitLevelValues to the PanelGrid
	     * 
	     * @param i
	     * @param benefitLevelValues
	     */
	   private void setBenefitLevelValuesToTierGrid(int i,
            BenefitLevel benefitLevelValues, BenefitTier tier, int lineSize, 
			int rowNum,int lineCount, HtmlPanelGrid tierLevelPanel) {
 
        Logger.logInfo("entered method setBenefitLevelValuesToGrid");
        
	        /*START CARS */
	        //Frequency Description Start
	        HtmlInputText levelDescInputText = new HtmlInputText();
	        HtmlOutputLabel lblDesc = new HtmlOutputLabel();
	        lblDesc.setId("lblDesc"+RandomStringUtils.randomAlphanumeric(15));
	        HtmlInputHidden hiddenForDesc = new HtmlInputHidden();
	        HtmlOutputText levelDesc = new HtmlOutputText();
	        if (null != benefitLevelValues.getLevelDesc()) {
	        	if(!isPrintMode()){
	            	String desc = null;
	            	String description = benefitLevelValues.getLevelDesc().trim();
	                if(description.length()>17){
	                	String[] strTokenizerArr = description.split(" ");
	                	for(int num=0;num<strTokenizerArr.length;num++){
	                		if(strTokenizerArr[num].length()>16){
	                			strTokenizerArr[num] = strTokenizerArr[num].substring(0,16)+" "+
	                				strTokenizerArr[num].substring(16);
	                		}
	                	}
	                	for(int num=0;num<strTokenizerArr.length;num++){
	                		if(null==desc)
	                			desc = strTokenizerArr[num];
	                		else
	                			desc = desc +" "+ strTokenizerArr[num];
	                	}
	                	description = desc;
	                }
		            levelDesc.setValue(description);
		            levelDescInputText.setValue(benefitLevelValues.getLevelDesc().trim());
		            hiddenForDesc.setValue(benefitLevelValues.getLevelDesc().trim());
	        	}else if(isPrintMode()){
	            	String desc = null;
	            	String description = benefitLevelValues.getLevelDesc().trim();
	                if(description.length()>18){
	                	String[] strTokenizerArr = description.split(" ");
	                	for(int num=0;num<strTokenizerArr.length;num++){
	                		if(strTokenizerArr[num].length()>18){
	                			strTokenizerArr[num] = strTokenizerArr[num].substring(0,18)+" "+
	                				strTokenizerArr[num].substring(18);
	                		}
	                	}
	                	for(int num=0;num<strTokenizerArr.length;num++){
	                		if(null==desc)
	                			desc = strTokenizerArr[num];
	                		else
	                			desc = desc +" "+ strTokenizerArr[num];
	                	}
	                	description = desc;
	                }
		            levelDesc.setValue(description);
		            levelDescInputText.setValue(benefitLevelValues.getLevelDesc().trim());
		            hiddenForDesc.setValue(benefitLevelValues.getLevelDesc().trim());
	        	}
	        } else {
	            levelDesc.setValue(WebConstants.EMPTY_STRING);
	            levelDescInputText.setValue(WebConstants.EMPTY_STRING);
	            hiddenForDesc.setValue(WebConstants.EMPTY_STRING);
	        }
	        levelDesc.setId("tierLevelDescription" + i);        
	        hiddenForDesc.setId("levelHidDescTier" + i);
	        ValueBinding hiddenDescOutputTxt = FacesContext
						.getCurrentInstance()
						.getApplication()
						.createValueBinding(
								"#{contractCoverageBackingBean.oldDescOutputTxtTier['"
								+ benefitLevelValues.getLevelId()+":"+tier.getBenefitTierSysId()+
								"']}");        
	        hiddenForDesc.setValueBinding("value",hiddenDescOutputTxt);        
	        levelDescInputText.setStyleClass("formInputField");
			levelDescInputText.setId("levelDescInputTextTier" + i);
			levelDescInputText.setStyle("width:125px;display:none");
			levelDescInputText.setMaxlength(32);
			//Binding the value for description text
			ValueBinding hiddenDescription = FacesContext
						.getCurrentInstance()
						.getApplication()
						.createValueBinding(
								"#{contractCoverageBackingBean.dataHiddenValDescTier['"
								+ benefitLevelValues.getLevelId()+":"+tier.getBenefitTierSysId()+
								"']}");        
	        levelDescInputText.setValueBinding("value", hiddenDescription);
	        //DESCRIPTION FIX START
	        if(isEditMode() && !isPrintMode() && !isViewMode()){
	       // }else if(isViewMode()){
	        //}else if(isEditMode()){
		        if (!BusinessUtil.isSystemGeneratedDescription(benefitLevelValues
						.getLevelDesc(), benefitLevelValues.getTermDesc(),
						benefitLevelValues.getQualifierDesc(), benefitLevelValues.getFrequencyId())){
					if ((new Integer(benefitLevelValues.getFrequencyId()).toString().trim()).equalsIgnoreCase(benefitLevelValues
							.getLowerLevelFrequencyValue())){
						levelDescInputText.setStyle("width:125px;display:none");
					}else{
						levelDesc.setStyle("display:none");
						levelDescInputText.setStyle("width:125px");
					}
				}else{
					levelDescInputText.setStyle("width:125px;display:none");
				}
	   		}
	        //DESCRIPTION FIX END	        
			//START setting the Lower level description in the hidden 
			HtmlInputHidden hiddenTierLowerLevelDescription = new HtmlInputHidden();
			hiddenTierLowerLevelDescription.setId("hidTierLowerLevelValDesc" + i);        
			hiddenTierLowerLevelDescription.setValue(benefitLevelValues.getLowerLevelDescValue().trim());
	        ValueBinding hidTierLowerLevelValDesc = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{contractCoverageBackingBean.dataTierHiddenLowerLevelValDesc['"+ benefitLevelValues.getLevelId()+":"+tier.getBenefitTierSysId()+"']}");
	        hiddenTierLowerLevelDescription.setValueBinding("value",hidTierLowerLevelValDesc);
			//END Lower level description in the hidden 
	        lblDesc.getChildren().add(levelDesc);
	        if(isEditMode() && !isPrintMode() && !isViewMode())
	        	lblDesc.getChildren().add(levelDescInputText);
	        lblDesc.getChildren().add(hiddenForDesc);
	        lblDesc.getChildren().add(hiddenTierLowerLevelDescription);
	        //Description End
	        //Term Start
	        HtmlOutputText levelTerm = new HtmlOutputText();
	        HtmlInputHidden hiddenForTerm = new HtmlInputHidden();
	        HtmlOutputLabel lblTerm = new HtmlOutputLabel();
	        lblTerm.setId("lblTerm"+RandomStringUtils.randomAlphanumeric(15));
	        hiddenForTerm.setId("hiddenTermTier" + i);
	        levelTerm.setId("levelTerm"+RandomStringUtils.randomAlphanumeric(15));
	       // levelTerm.setId("levelTerm" + benefitLevelValues.getLevelId()+i+rowNum+tier.getBenefitTierSysId());
	        if (null != benefitLevelValues.getTermDesc()) {
	            levelTerm.setValue(benefitLevelValues.getTermDesc().trim().replaceAll(",",", "));
	            hiddenForTerm.setValue(benefitLevelValues.getTermDesc().trim().replaceAll(",",", "));
	        } else {
	            levelTerm.setValue(WebConstants.EMPTY_STRING);
	            hiddenForTerm.setValue(WebConstants.EMPTY_STRING);
	        }
	        ValueBinding hiddenTermDesc = FacesContext
						.getCurrentInstance()
						.getApplication()
						.createValueBinding(
								"#{contractCoverageBackingBean.dataHiddenValTermTier["
								+ benefitLevelValues.getLevelId() +
								"]}");
	        hiddenForTerm.setValueBinding("value",hiddenTermDesc);
	        lblTerm.getChildren().add(levelTerm);
	        lblTerm.getChildren().add(hiddenForTerm);
	        //Term End
	        //Qualifier Start
	        HtmlOutputText levelQualifier = new HtmlOutputText();
	        levelQualifier.setId("levelQualifier" + benefitLevelValues.getLevelId()+i+rowNum+tier.getBenefitTierSysId());
	        HtmlInputHidden hiddenForQualifier = new HtmlInputHidden();
			hiddenForQualifier.setId("hiddenQualifierTier" + i);
	        if (null != benefitLevelValues.getQualifierDesc()) {
	        	//Check if the frequency value is not 0 
	        	if(0 != benefitLevelValues.getFrequencyId()){
	        		//Concat the "-" with the  qualifier value
	        		levelQualifier.setValue(" - "+benefitLevelValues.getQualifierDesc()
		                    .trim().replaceAll(",",", "));
	        	}else{	
		            levelQualifier.setValue(benefitLevelValues.getQualifierDesc()
		                    .trim().replaceAll(",",", "));
	        	}
	            hiddenForQualifier.setValue(benefitLevelValues.getQualifierDesc().trim().replaceAll(",",", "));
	        } else {
	            levelQualifier.setValue(WebConstants.EMPTY_STRING);
	            hiddenForQualifier.setValue(WebConstants.EMPTY_STRING);
	        }
	        ValueBinding hidddenQualifier = FacesContext
						.getCurrentInstance()
						.getApplication().createValueBinding(
								"#{contractCoverageBackingBean.dataHiddenValQualifierTier["
								+ benefitLevelValues.getLevelId() +
								"]}");
	        hiddenForQualifier.setValueBinding("value",hidddenQualifier);
	        //Frequency Start
	        //created lable for frequnecy and qualifier
	        HtmlOutputLabel lblFreqQualPage = new HtmlOutputLabel();
	        lblFreqQualPage.setId("lblFreqQualPage"+RandomStringUtils.randomAlphanumeric(15));
	        HtmlInputText levelFrequencyInputTxt = new HtmlInputText();
	        HtmlOutputText levelFrequencyOutputTxt = new HtmlOutputText();
	        //Check if the frequency value is 0
	        if(0 != benefitLevelValues.getFrequencyId()){
	        	//Set the frequnecy value to the input text
	        	levelFrequencyInputTxt.setValue(new Integer(benefitLevelValues.getFrequencyId()).toString().trim());
	        	levelFrequencyInputTxt.setId("levelFreqValueTier" + i);
	        	levelFrequencyInputTxt.setStyleClass("formInputField");
	        	levelFrequencyInputTxt.setStyle("width:30px;");
	        	levelFrequencyInputTxt.setMaxlength(3);
	        	levelFrequencyInputTxt.setOnkeypress("return isNum(event);");
	        	ValueBinding levelFreqValueBind = FacesContext.getCurrentInstance()
				.getApplication().createValueBinding(
						"#{contractCoverageBackingBean.lineFreqValueMapTier['"
								+ benefitLevelValues.getLevelId()+":"+tier.getBenefitTierSysId() +"']}");
	        	levelFrequencyInputTxt.setValueBinding("value",levelFreqValueBind);
	        	//Hidden Frequency is added to hold the frequency value
	        	HtmlInputHidden hiddenLevelFreqValue = new HtmlInputHidden();
				hiddenLevelFreqValue.setId("hiddenLevelFreqValueTier"+ i);
				hiddenLevelFreqValue.setValue(new Integer(benefitLevelValues.getFrequencyId()).toString().trim());
				ValueBinding valForhiddenLevelFreq = FacesContext
							.getCurrentInstance().getApplication().createValueBinding(
									"#{contractCoverageBackingBean.hiddenLineFreqValueMapTier['"
											+ benefitLevelValues.getLevelId()+":"+tier.getBenefitTierSysId()+"']}");
				hiddenLevelFreqValue.setValueBinding("value",
							valForhiddenLevelFreq);
	        	levelFrequencyInputTxt.setOnchange("return descriptionChangeForTier(this)");	        	
	        	levelFrequencyInputTxt.setDisabled(benefitLevelValues.isLevelHideStatus());
	        	//Set the frequency value to the output text 
				levelFrequencyOutputTxt.setId("FrequencyTier" +i);
	        	levelFrequencyOutputTxt.setValue(new Integer(benefitLevelValues.getFrequencyId()).toString().trim());
				//START Hidden Lower level frequency value
				HtmlInputHidden hiddenTierLowerLevelFreqValue = new HtmlInputHidden();
				hiddenTierLowerLevelFreqValue.setId("hiddenTierLowerLevelFreqValue"+ i);
				hiddenTierLowerLevelFreqValue.setValue(benefitLevelValues.getLowerLevelFrequencyValue());
				ValueBinding valForTierhiddenLowerLevelFreq = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{contractCoverageBackingBean.hiddenTierLowerLevelFreqValueMap['"+ benefitLevelValues.getLevelId()+":"+tier.getBenefitTierSysId()+"']}");
				hiddenTierLowerLevelFreqValue.setValueBinding("value",valForTierhiddenLowerLevelFreq);
				//END Hidden Lower level frequency value
	        	//Check if it is for view or edit page
	        	 if (!isViewMode() && !(isPrintMode())) {
	        	 	//Sets the input text to the frequency qualifier label
	        	 	lblFreqQualPage.getChildren().add(levelFrequencyInputTxt);	        	
	        	 }else{
		            //Sets the output text to the frequency qualifier label
	        	 	lblFreqQualPage.getChildren().add(levelFrequencyOutputTxt);
	        	 }        	 
	        	 lblFreqQualPage.getChildren().add(hiddenLevelFreqValue);
	        	 lblFreqQualPage.getChildren().add(hiddenTierLowerLevelFreqValue);
	        }
	        lblFreqQualPage.getChildren().add(levelQualifier);
	        lblFreqQualPage.getChildren().add(hiddenForQualifier);
	        //End - Frequency Enhancement
			/*END CARS */

/*	        HtmlOutputText levelTerm = new HtmlOutputText();
        levelTerm.setId("levelTerm" + benefitLevelValues.getLevelId()+i+rowNum+tier.getBenefitTierSysId());
        if (null != benefitLevelValues.getTermDesc()) {
            levelTerm.setValue(benefitLevelValues.getTermDesc().trim());
        } else {
            levelTerm.setValue(WebConstants.EMPTY_STRING);
        }

        HtmlOutputText levelQualifier = new HtmlOutputText();
        levelQualifier.setId("levelQualifier" + benefitLevelValues.getLevelId()+i+rowNum+tier.getBenefitTierSysId());
        if (null != benefitLevelValues.getQualifierDesc()) {
            levelQualifier.setValue(benefitLevelValues.getQualifierDesc()
                    .trim());
        } else {
            levelQualifier.setValue(WebConstants.EMPTY_STRING);
        }
*/
        HtmlOutputText levelPVA = new HtmlOutputText();
        levelPVA.setId("levelPVA" + benefitLevelValues.getLevelId()+i+rowNum+tier.getBenefitTierSysId());
        levelPVA.setValue(" ");

        HtmlOutputText levelBnftValue = new HtmlOutputText();
        levelBnftValue.setId("levelBnftValue" + benefitLevelValues.getLevelId()+i+rowNum+tier.getBenefitTierSysId());
        levelBnftValue.setValue(" ");

        HtmlOutputText levelReference = new HtmlOutputText();
        levelReference.setId("levelReference" + benefitLevelValues.getLevelId()+i+rowNum+tier.getBenefitTierSysId());
        if (null != benefitLevelValues.getReferenceDesc()) {
            levelReference.setValue(benefitLevelValues.getReferenceDesc()
                    .trim());
        } else {
            levelReference.setValue(WebConstants.EMPTY_STRING);
        }

        HtmlInputHidden hiddenLevelId = new HtmlInputHidden();
        hiddenLevelId.setId("hiddenLevelId" + i+benefitLevelValues.getLevelId()+tier.getBenefitTierSysId());
        hiddenLevelId.setValue(new Integer(benefitLevelValues.getLevelId()));
        // set the value to the map
       /* ValueBinding levelIdValBind = FacesContext.getCurrentInstance()
                .getApplication().createValueBinding(
                        "#{contractCoverageBackingBean.levelTierIdMap[" +benefitLevelValues.getLevelId()+ "."+tier.getBenefitTierSysId()+"]}");
        hiddenLevelId.setValueBinding("value", levelIdValBind);*/
        /*HtmlGraphicImage deleteImage = new HtmlGraphicImage();
        deleteImage.setUrl("../../images/delete.gif");
        deleteImage.setStyle("cursor:hand;");
        deleteImage.setId("deleteImage" + i);

        HtmlCommandButton deleteButton = new HtmlCommandButton();
        deleteButton.setValue("DeleteButton");
        deleteButton.setImage("../../images/delete.gif");
        deleteButton.setTitle("Delete");
        deleteButton.setStyle("border:0;");
        deleteButton.setAlt("Delete");
        deleteButton.setOnclick("changeColour(\'" + i + "','" + lineSize
                + "','" + rowNum + "\');return false;");
        deleteButton.setId("deleteButton" + i);*/	               	
               
        HtmlOutputLabel lblImage = new HtmlOutputLabel();
        lblImage.setFor("levelDesc" + i);
        //lblImage.setId("lblImage" + i+benefitLevelValues.getLevelId()+tier.getBenefitTierSysId()+lineCount);
        lblImage.setId("lblImage"+RandomStringUtils.randomAlphanumeric(15));
        //sets the size to a hidden variable
        HtmlInputHidden hiddenLineSize = new HtmlInputHidden();
        hiddenLineSize.setId("hiddenLineSize" + i + benefitLevelValues.getLevelId()+tier.getBenefitTierSysId()+lineCount);
        hiddenLineSize.setValue(new Integer(lineSize));

        //      sets the size to a hidden variable
        HtmlInputHidden hiddenRowSize = new HtmlInputHidden();
        hiddenRowSize.setId("hiddenRowNum" + i + benefitLevelValues.getLevelId()+tier.getBenefitTierSysId()+lineCount);
        hiddenRowSize.setValue(new Integer(rowNum));

        HtmlOutputText lineImage = new HtmlOutputText();
        lineImage.setId("lineImage" + i + benefitLevelValues.getLevelId()+tier.getBenefitTierSysId()+lineCount);
        lineImage.setValue(" ");

        HtmlOutputText dummyText = new HtmlOutputText();
        dummyText.setId("dummyText" + i + benefitLevelValues.getLevelId()+tier.getBenefitTierSysId()+lineCount);
        levelPVA.setValue(" ");

        //checks if it is a view mode
        if (!isViewMode()) {	        
        	
            lblImage.getChildren().add(hiddenLevelId);
            lblImage.getChildren().add(hiddenLineSize);
            lblImage.getChildren().add(hiddenRowSize);
        }	      
        	//tierLevelPanel.getChildren().add(levelDesc);
        	//tierLevelPanel.getChildren().add(levelTerm);
        	//tierLevelPanel.getChildren().add(levelQualifier);
        	
            /*START CARS */
            //Frequency
        	tierLevelPanel.getChildren().add(lblDesc);
        	tierLevelPanel.getChildren().add(lblTerm);
        	tierLevelPanel.getChildren().add(lblFreqQualPage);
            /*END CARS */
        	
        	tierLevelPanel.getChildren().add(levelPVA);
        	tierLevelPanel.getChildren().add(dummyText);
        	tierLevelPanel.getChildren().add(levelBnftValue);
        	tierLevelPanel.getChildren().add(levelReference);
            tierLevelPanel.getChildren().add(lineImage);	                 
    }
	    
	    
	    private String formKeyforTier(int tierDefId, int tierSysId) {
		   	String tierDefIdString = new Integer(tierDefId).toString();
			String tierSysIdString = new Integer(tierSysId).toString();
			return (tierDefIdString).concat(":").concat(tierSysIdString);
		}
	    public String deleteBenefitTier(){
	    	 ContractTierDeleteRequest contractTierDeleteRequest = (ContractTierDeleteRequest) this
            .getServiceRequest(ServiceManager.DELETE_CONTRACT_TIER_REQUEST);
	    	 contractTierDeleteRequest.setContractTierSysId(tierIdToDelete);
	    	 
	    	 
	    	 //amjith
	    	 contractTierDeleteRequest.setBenefitId(getContractSession().getBenefitId());
	    	 contractTierDeleteRequest.setBenefitComponentSysId(getContractSession().getBenefitComponentId());
	    	 contractTierDeleteRequest.setContractKeyObject(getContractSession().getContractKeyObject());
	    	 contractTierDeleteRequest.setProductSysId(getContractSession().getProductId());
			 
	    	 ContractTierDeleteResponse contractTierDeleteResponse = (ContractTierDeleteResponse)executeService(contractTierDeleteRequest);
	    	 if(null != contractTierDeleteResponse && null != contractTierDeleteResponse.getMessages()){
	    		 messages = contractTierDeleteResponse.getMessages();
	    		//added the message from the response to the class variable --Defect fix for WAS7 Migration
	    		informationMessageToDisplayOnPage = contractTierDeleteResponse.getMessages();
	    	 }
	    	return "contractBenefitDefinitionUpdate";
	    	
	    }
	   
	   private String formKeyforMap(int levelId, int lineId, int tierSysId) {
		   	String levelIdString = new Integer(levelId).toString();
		   	String lineIdString = new Integer(lineId).toString();
			String tierSysIdString = new Integer(tierSysId).toString();
			return (levelIdString).concat(":").concat(lineIdString).concat(":").concat(tierSysIdString);
		}
	   private List appendChangedTieredLineIds(List changedLines)
	   {/**
		 *   This method is for appending the Ids of the modified 'tiered' lines with Ids of modified 'general' lines to generate combined Id list.
		 *   resultant list can be used for admin method validation.   
		 */	   	
	   	
		if(null != getTierCriteriaMap() && getTierCriteriaMap().size() != 0 
				&& null != getTierLineValueMap() && getTierLineValueMap().size() != 0
        		&& null != getContractSession().getBenefitTierDefinitionList()
        			&& null != getContractSession().getBenefitTierLevelList()){
        	//saveTier();            	         
        	if (modifiedTierLineIdList != null && modifiedTierLineIdList.size() > 0){
	   	  	
	       Set totalSet=new HashSet(modifiedTierLineIdList);
	       totalSet.addAll(changedLines);
	        return new ArrayList(totalSet);
        	}
        }
		return changedLines;
	   }	
	/**
	 * @return Returns the levelIdsMap.
	 */
	public HashMap getLevelIdsMap() {
		return levelIdsMap;
	}

	/**
	 * @param levelIdsMap The levelIdsMap to set.
	 */
	public void setLevelIdsMap(HashMap levelIdsMap) {
		this.levelIdsMap = levelIdsMap;
	}	

	public List getLvlLineList() {
		return lvlLineList;
	}

	public void setLvlLineList(List lvlLineList) {
		this.lvlLineList = lvlLineList;
	}

	public List getTierDefinitionList() {
		return tierDefinitionList;
	}

	public void setTierDefinitionList(List tierDefinitionList) {
		this.tierDefinitionList = tierDefinitionList;
	}

	public Map getTierCriteriaMap() {
		return tierCriteriaMap;
	}

	public void setTierCriteriaMap(Map tierCriteriaMap) {
		this.tierCriteriaMap = tierCriteriaMap;
	}

	public Map getTierLineValueMap() {
		return tierLineValueMap;
	}

	public void setTierLineValueMap(Map tierLineValueMap) {
		this.tierLineValueMap = tierLineValueMap;
	}

	public int getTierIdToDelete() {
		return tierIdToDelete;
	}

	public void setTierIdToDelete(int tierIdToDelete) {
		this.tierIdToDelete = tierIdToDelete;
	}

	public List getModifiedTierLineIdList() {
		return modifiedTierLineIdList;
	}

	public void setModifiedTierLineIdList(List modifiedTierLineIdList) {
		this.modifiedTierLineIdList = modifiedTierLineIdList;
	}
	
	/**
	 * @return Returns the modifiedTierCriteriaList.
	 */
	public List getModifiedTierCriteriaList() {
		return modifiedTierCriteriaList;
	}
	/**
	 * @param modifiedTierCriteriaList The modifiedTierCriteriaList to set.
	 */
	public void setModifiedTierCriteriaList(List modifiedTierCriteriaList) {
		this.modifiedTierCriteriaList = modifiedTierCriteriaList;
	}
	/**
	 * @return Returns the informationMsg.
	 */
	public String getInformationMsg() {
		if(informationMsg != null && informationMsg != "" && informationMsg.equals("sucsess")){
			List messages = new ArrayList();
			messages.add(new InformationalMessage(WebConstants.BENEFIT_TIER_ADEDD));
			addAllMessagesToRequest(messages); 
			informationMsg = "";
		}
		return informationMsg;
	}
	/**
	 * @param informationMsg The informationMsg to set.
	 */
	public void setInformationMsg(String informationMsg) {
		this.informationMsg = informationMsg;
	}

	/**
	 * @param panelForTierPrint The panelForTierPrint to set.
	 */
	public void setPanelForTierPrint(HtmlPanelGrid panelForTierPrint) {
		this.panelForTierPrint = panelForTierPrint;
	}
	
	/**
	 * @return Returns the printMode.
	 */
	public boolean isPrintMode() {
		return printMode;
	}
	/**
	 * @param printMode The printMode to set.
	 */
	public void setPrintMode(boolean printMode) {
		this.printMode = printMode;
	}
	private void sortTiers(List benefitDefinitonsList) {
		if(null!=benefitDefinitonsList){
		   //Collections.sort(benefitDefinitonsList);
		   for (Iterator iter = benefitDefinitonsList.iterator(); iter.hasNext();) {
			BenefitTierDefinition tierDef = (BenefitTierDefinition) iter.next();
			 if(null != tierDef) {
			 	Collections.sort(tierDef.getBenefitTiers());
			 }
			
		}
		}
	}
	 public HtmlPanelGrid getTierHeaderPanel() {

        Logger.logInfo("entered method getTierHeaderPanel");

        //sets the string which contains the levels to delete to null
        if (null != this.levelsToDelete) {
            this.levelsToDelete = null;
        }

        tierHeaderPanel = new HtmlPanelGrid();
        tierHeaderPanel.setColumns(8);
        tierHeaderPanel.setWidth("100%");
        /* START CARS */
		if(isViewMode() ){
			tierHeaderPanel.setColumnClasses("gridColumn17,gridColumn14,gridColumn19,gridColumn7,gridColumn8,gridColumn10,gridColumn17,gridColumn8");				
		}else{
			tierHeaderPanel.setColumnClasses("gridColumn19,gridColumn12,gridColumn15,gridColumn5,gridColumn7,gridColumn12,gridColumn22,gridColumn8");
		}
        /* END CARS */
        tierHeaderPanel.setBorder(0);
        tierHeaderPanel.setCellpadding("3");
        tierHeaderPanel.setCellspacing("1");
        tierHeaderPanel.setBgcolor("#cccccc");
        tierHeaderPanel.setStyle("font-weight:bold;");
        tierHeaderPanel.setId("tierHeaderPanel"+RandomStringUtils.randomAlphanumeric(15));
        HtmlOutputText headerText1 = new HtmlOutputText();
        HtmlOutputText headerText2 = new HtmlOutputText();
        HtmlOutputText headerText3 = new HtmlOutputText();
        HtmlOutputText headerText4 = new HtmlOutputText();
        HtmlOutputText headerText5 = new HtmlOutputText();
        HtmlOutputText headerText6 = new HtmlOutputText();
        HtmlOutputText headerText7 = new HtmlOutputText();
        HtmlOutputText headerText8 = new HtmlOutputText();
        HtmlOutputText headerText9 = new HtmlOutputText();
        
        headerText1.setValue("Description");
        headerText1.setId("desc1");

        headerText2.setValue("Term");
        headerText2.setId("term1");
		/*START CARS */
        headerText3.setValue("Frequency - Qualifier");
        /*END CARS */
        headerText3.setId("qualifier1");

        headerText4.setValue("PVA");
        headerText4.setId("pva1");

        headerText5.setValue("Benefit Value");
        headerText5.setId("bnftValue1");

        headerText9.setValue("Format");
        headerText9.setId("dataTypeLgnd1");

        headerText6.setValue("Reference");
        headerText6.setId("ref1");
        
        headerText8.setValue("Notes");
        headerText8.setId("notes1");	        

        tierHeaderPanel.getChildren().add(headerText1);
        tierHeaderPanel.getChildren().add(headerText2);
        tierHeaderPanel.getChildren().add(headerText3);
        tierHeaderPanel.getChildren().add(headerText4);
        tierHeaderPanel.getChildren().add(headerText9);
        tierHeaderPanel.getChildren().add(headerText5);
        tierHeaderPanel.getChildren().add(headerText6);
        tierHeaderPanel.getChildren().add(headerText8);
        return tierHeaderPanel;
    }
	 
	 public HtmlPanelGrid getTierHeaderPanelForPrint() {
	 	 
        Logger.logInfo("entered method getTierHeaderPanelForPrint");

        //sets the string which contains the levels to delete to null
        if (null != this.levelsToDelete) {
            this.levelsToDelete = null;
        }

        tierHeaderPanel = new HtmlPanelGrid();	       

        tierHeaderPanel.setColumns(7);
        tierHeaderPanel.setWidth("100%");
/*	         START CARS 
         tierHeaderPanel.setColumnClasses("gridColumn22,gridColumn12,gridColumn16,gridColumn8,gridColumn10,gridColumn10,gridColumn22");
         END CARS */
        
        tierHeaderPanel.setColumnClasses("gridColumn22,gridColumn12,gridColumn16,gridColumn8,gridColumn10,gridColumn9,gridColumn23");
        tierHeaderPanel.setId("tierHeaderPanel"+RandomStringUtils.randomAlphanumeric(15));
        tierHeaderPanel.setBorder(0);
        tierHeaderPanel.setCellpadding("3");
        tierHeaderPanel.setCellspacing("1");
        tierHeaderPanel.setBgcolor("#cccccc");

        HtmlOutputText headerText1 = new HtmlOutputText();
        HtmlOutputText headerText2 = new HtmlOutputText();
        HtmlOutputText headerText3 = new HtmlOutputText();
        HtmlOutputText headerText4 = new HtmlOutputText();
        HtmlOutputText headerText5 = new HtmlOutputText();
        HtmlOutputText headerText6 = new HtmlOutputText();
        HtmlOutputText headerText7 = new HtmlOutputText();
        HtmlOutputText headerText8 = new HtmlOutputText();
        HtmlOutputText headerText9 = new HtmlOutputText();
        
        headerText1.setValue("Description");
        headerText1.setId("desc1");

        headerText2.setValue("Term");
        headerText2.setId("term1");

        /*Start CARS */	 
        headerText3.setValue("Frequency - Qualifier");
        /*End CARS */	 
        headerText3.setId("qualifier1");

        headerText4.setValue("PVA");
        headerText4.setId("pva1");	       

        headerText5.setValue("Format");
        headerText5.setId("dataTypeLgnd1");
        
        headerText6.setValue("Benefit Value");
        headerText6.setId("bnftValue1");

        headerText7.setValue("Reference");
        headerText7.setId("ref1");	        
       
        tierHeaderPanel.getChildren().add(headerText1);
        tierHeaderPanel.getChildren().add(headerText2);
        tierHeaderPanel.getChildren().add(headerText3);
        tierHeaderPanel.getChildren().add(headerText4);
        tierHeaderPanel.getChildren().add(headerText5);
        tierHeaderPanel.getChildren().add(headerText6);
        tierHeaderPanel.getChildren().add(headerText7);	     
        tierHeaderPanel.setStyleClass("dataTableHeader");
        return tierHeaderPanel;
    }
	    
    public void setTierHeaderPanel(HtmlPanelGrid tierHeaderPanel) {
		this.tierHeaderPanel = tierHeaderPanel;
		}
	/**
	 * @return Returns the benefitLevelForPrint.
	 */
	public String getBenefitLevelForPrint() {
		this.hiddenBenefitLevelForPrint();
		return benefitLevelForPrint;
	}
	/**
	 * @param benefitLevelForPrint The benefitLevelForPrint to set.
	 */
	public void setBenefitLevelForPrint(String benefitLevelForPrint) {
		this.benefitLevelForPrint = benefitLevelForPrint;
	}
	
	/*START CARS */
	
	/**
	 * @return Returns the dataHiddenValDescTier.
	 */
	public HashMap getDataHiddenValDescTier() {
		return dataHiddenValDescTier;
	}
	/**
	 * @param dataHiddenValDescTier The dataHiddenValDescTier to set.
	 */
	public void setDataHiddenValDescTier(HashMap dataHiddenValDescTier) {
		this.dataHiddenValDescTier = dataHiddenValDescTier;
	}
	/**
	 * @return Returns the dataHiddenValQualifierTier.
	 */
	public HashMap getDataHiddenValQualifierTier() {
		return dataHiddenValQualifierTier;
	}
	/**
	 * @param dataHiddenValQualifierTier The dataHiddenValQualifierTier to set.
	 */
	public void setDataHiddenValQualifierTier(HashMap dataHiddenValQualifierTier) {
		this.dataHiddenValQualifierTier = dataHiddenValQualifierTier;
	}
	/**
	 * @return Returns the dataHiddenValTermTier.
	 */
	public HashMap getDataHiddenValTermTier() {
		return dataHiddenValTermTier;
	}
	/**
	 * @param dataHiddenValTermTier The dataHiddenValTermTier to set.
	 */
	public void setDataHiddenValTermTier(HashMap dataHiddenValTermTier) {
		this.dataHiddenValTermTier = dataHiddenValTermTier;
	}
	/**
	 * @return Returns the hiddenLineFreqValueMapTier.
	 */
	public HashMap getHiddenLineFreqValueMapTier() {
		return hiddenLineFreqValueMapTier;
	}
	/**
	 * @param hiddenLineFreqValueMapTier The hiddenLineFreqValueMapTier to set.
	 */
	public void setHiddenLineFreqValueMapTier(HashMap hiddenLineFreqValueMapTier) {
		this.hiddenLineFreqValueMapTier = hiddenLineFreqValueMapTier;
	}
	/**
	 * @return Returns the lineFreqValueMapTier.
	 */
	public HashMap getLineFreqValueMapTier() {
		return lineFreqValueMapTier;
	}
	/**
	 * @param lineFreqValueMapTier The lineFreqValueMapTier to set.
	 */
	public void setLineFreqValueMapTier(HashMap lineFreqValueMapTier) {
		this.lineFreqValueMapTier = lineFreqValueMapTier;
	}
	/**
	 * @return Returns the oldDescOutputTxtTier.
	 */
	public HashMap getOldDescOutputTxtTier() {
		return oldDescOutputTxtTier;
	}
	/**
	 * @param oldDescOutputTxtTier The oldDescOutputTxtTier to set.
	 */
	public void setOldDescOutputTxtTier(HashMap oldDescOutputTxtTier) {
		this.oldDescOutputTxtTier = oldDescOutputTxtTier;
	}
	/**
	 * @return Returns the isFrequencyChanged.
	 */
	public boolean isFrequencyChanged() {
		return isFrequencyChanged;
	}
	/**
	 * @param isFrequencyChanged The isFrequencyChanged to set.
	 */
	public void setFrequencyChanged(boolean isFrequencyChanged) {
		this.isFrequencyChanged = isFrequencyChanged;
	}
	
	/*END CARS */
	
	/**
	 * @return Returns the dataHiddenValDesc.
	 */
	public Map getDataHiddenValDesc() {
		return dataHiddenValDesc;
	}
	/**
	 * @param dataHiddenValDesc The dataHiddenValDesc to set.
	 */
	public void setDataHiddenValDesc(Map dataHiddenValDesc) {
		this.dataHiddenValDesc = dataHiddenValDesc;
	}
	/**
	 * @return Returns the dataHiddenValTerm.
	 */
	public Map getDataHiddenValTerm() {
		return dataHiddenValTerm;
	}
	/**
	 * @param dataHiddenValTerm The dataHiddenValTerm to set.
	 */
	public void setDataHiddenValTerm(Map dataHiddenValTerm) {
		this.dataHiddenValTerm = dataHiddenValTerm;
	}
	/**
	 * @return Returns the hiddenLineFreqValueMap.
	 */
	public HashMap getHiddenLineFreqValueMap() {
		return hiddenLineFreqValueMap;
	}
	/**
	 * @param hiddenLineFreqValueMap The hiddenLineFreqValueMap to set.
	 */
	public void setHiddenLineFreqValueMap(HashMap hiddenLineFreqValueMap) {
		this.hiddenLineFreqValueMap = hiddenLineFreqValueMap;
	}
	/**
	 * @return Returns the lineFreqValueMap.
	 */
	public Map getLineFreqValueMap() {
		return lineFreqValueMap;
	}
	/**
	 * @param lineFreqValueMap The lineFreqValueMap to set.
	 */
	public void setLineFreqValueMap(Map lineFreqValueMap) {
		this.lineFreqValueMap = lineFreqValueMap;
	}
	/**
	 * @return Returns the dataHiddenValQualifier.
	 */
	public Map getDataHiddenValQualifier() {
		return dataHiddenValQualifier;
	}
	/**
	 * @param dataHiddenValQualifier The dataHiddenValQualifier to set.
	 */
	public void setDataHiddenValQualifier(Map dataHiddenValQualifier) {
		this.dataHiddenValQualifier = dataHiddenValQualifier;
	}
	/**
	 * @return Returns the levelLineIdMap.
	 */
	public Map getLevelLineIdMap() {
		return levelLineIdMap;
	}
	/**
	 * @param levelLineIdMap The levelLineIdMap to set.
	 */
	public void setLevelLineIdMap(Map levelLineIdMap) {
		this.levelLineIdMap = levelLineIdMap;
	}
	
	/**
	 * Sets the headerPanel
	 * @param headerPanel.
	 */
	public void setTierHeaderPanelForPrint(HtmlPanelGrid tierHeaderPanelForPrint) {
		this.tierHeaderPanelForPrint = tierHeaderPanelForPrint;
	}
	// START CARS
	/**
	 * @return the changedTierLineIds
	 */
	public List getChangedTierLineIds() {
		return changedTierLineIds;
	}
	/**
	 * @param changedTierLineIds the changedTierLineIds to set
	 */
	public void setChangedTierLineIds(List changedTierLineIds) {
		this.changedTierLineIds = changedTierLineIds;
	}
	/**
	 * @return the changedTierSysIds
	 */
	public List getChangedTierSysIds() {
		return changedTierSysIds;
	}
	/**
	 * @param changedTierSysIds the changedTierSysIds to set
	 */
	public void setChangedTierSysIds(List changedTierSysIds) {
		this.changedTierSysIds = changedTierSysIds;
	}
	// END CARS
	/**
	 * @return Returns the modifiedLevelIdList.
	 */
	public List getModifiedLevelIdList() {
		return modifiedLevelIdList;
	}
	/**
	 * @param modifiedLevelIdList The modifiedLevelIdList to set.
	 */
	public void setModifiedLevelIdList(List modifiedLevelIdList) {
		this.modifiedLevelIdList = modifiedLevelIdList;
	}
	/**
	 * @return Returns the messagesList.
	 */
	public List getMessagesList() {
		return messagesList;
	}
	/**
	 * @param messagesList The messagesList to set.
	 */
	public void setMessagesList(List messagesList) {
		this.messagesList = messagesList;
	}
	
	/**
	 * @return Returns the dataHiddenLowerLevelValDesc.
	 */
	public HashMap getDataHiddenLowerLevelValDesc() {
		return dataHiddenLowerLevelValDesc;
	}
	/**
	 * @param dataHiddenLowerLevelValDesc The dataHiddenLowerLevelValDesc to set.
	 */
	public void setDataHiddenLowerLevelValDesc(
			HashMap dataHiddenLowerLevelValDesc) {
		this.dataHiddenLowerLevelValDesc = dataHiddenLowerLevelValDesc;
	}
	/**
	 * @return Returns the dataHiddenOutputValDesc.
	 */
	//Commented as part of stabilization release
	/*
	public HashMap getDataHiddenOutputValDesc() {
		return dataHiddenOutputValDesc;
	}
	*//**
	 * @param dataHiddenOutputValDesc The dataHiddenOutputValDesc to set.
	 *//*
	public void setDataHiddenOutputValDesc(HashMap dataHiddenOutputValDesc) {
		this.dataHiddenOutputValDesc = dataHiddenOutputValDesc;
	}*/
	/**
	 * @return Returns the hiddenLowerLevelFreqValueMap.
	 */
	public HashMap getHiddenLowerLevelFreqValueMap() {
		return hiddenLowerLevelFreqValueMap;
	}
	/**
	 * @param hiddenLowerLevelFreqValueMap The hiddenLowerLevelFreqValueMap to set.
	 */
	public void setHiddenLowerLevelFreqValueMap(
			HashMap hiddenLowerLevelFreqValueMap) {
		this.hiddenLowerLevelFreqValueMap = hiddenLowerLevelFreqValueMap;
	}
	/**
	 * @return Returns the dataTierHiddenLowerLevelValDesc.
	 */
	public HashMap getDataTierHiddenLowerLevelValDesc() {
		return dataTierHiddenLowerLevelValDesc;
	}
	/**
	 * @param dataTierHiddenLowerLevelValDesc The dataTierHiddenLowerLevelValDesc to set.
	 */
	public void setDataTierHiddenLowerLevelValDesc(
			HashMap dataTierHiddenLowerLevelValDesc) {
		this.dataTierHiddenLowerLevelValDesc = dataTierHiddenLowerLevelValDesc;
	}
	/**
	 * @return Returns the hiddenTierLowerLevelFreqValueMap.
	 */
	public HashMap getHiddenTierLowerLevelFreqValueMap() {
		return hiddenTierLowerLevelFreqValueMap;
	}
	/**
	 * @param hiddenTierLowerLevelFreqValueMap The hiddenTierLowerLevelFreqValueMap to set.
	 */
	public void setHiddenTierLowerLevelFreqValueMap(
			HashMap hiddenTierLowerLevelFreqValueMap) {
		this.hiddenTierLowerLevelFreqValueMap = hiddenTierLowerLevelFreqValueMap;
	}
    /**
     * @return Returns the tierLineIdMap.
     */
    public HashMap getTierLineIdMap() {
        return tierLineIdMap;
    }
    /**
     * @param tierLineIdMap The tierLineIdMap to set.
     */
    public void setTierLineIdMap(HashMap tierLineIdMap) {
        this.tierLineIdMap = tierLineIdMap;
    }
    
    /*START CARS*/
    /**
     * @return Returns the tierLevelIdsMap.
     */
    public HashMap getTierLevelIdsMap() {
        return tierLevelIdsMap;
    }
    /**
     * @param tierLevelIdsMap The tierLevelIdsMap to set.
     */
    public void setTierLevelIdsMap(HashMap tierLevelIdsMap) {
        this.tierLevelIdsMap = tierLevelIdsMap;
    }
    /*END CARS*/	
}
