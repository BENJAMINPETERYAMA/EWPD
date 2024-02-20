/*
 * ContrMigratProductMappingBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.migration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import com.wellpoint.wpd.business.migration.util.MigrationContractUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyVariable;
import com.wellpoint.wpd.common.legacycontract.request.RetrieveHeadingsRequest;
import com.wellpoint.wpd.common.legacycontract.response.RetrieveHeadingsResponse;
import com.wellpoint.wpd.common.migration.bo.BenefitComponentBenefit;
import com.wellpoint.wpd.common.migration.bo.MigrationContract;
import com.wellpoint.wpd.common.migration.request.RetrieveMigVariableMappingRequest;
import com.wellpoint.wpd.common.migration.request.SaveMigVariableMappingRequest;
import com.wellpoint.wpd.common.migration.response.RetrieveMigVariableMappingResponse;
import com.wellpoint.wpd.common.migration.response.SaveMigVariableMappingResponse;
import com.wellpoint.wpd.common.migration.vo.MigProductMappingVO;
import com.wellpoint.wpd.common.migration.vo.MigrationContractSession;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContrMigratProductMappingBackingBean extends
        MigrationBaseBackingBean {

    private String productName;

    private String benefitCompName;

    private String stdBenefitName;

    private String structureName;

    private String product;

    private String structure;

    private String benComponent;

    private String majorHeading;

    private String stdBenefit;

    private String minorHeading;

    private List resultList;

    private List productMappingList;

    private String majorHeadingId;

    private String minorHeadingId;

    private String majorHeadinghidden;

    private String minorHeadinghidden;

    private List majorHeadingListForCombo;

    private List minorHeadingListForCombo;

    private List validationMessages;

    private List benefitLineList;

    private HtmlPanelGrid panel = new HtmlPanelGrid();

    private HtmlPanelGrid headingPanel = new HtmlPanelGrid();

    private Map hiddenVarValue = new HashMap();

    private Map hiddenVariable = new HashMap();

    private Map hiddenBenLineId = new HashMap();
    
    private Map oldVariable = new HashMap();
    private Map hiddenNotesFlag = new HashMap();
    private Map hideFlag = new HashMap();
	
    private List majorHeadingsList;

    private List minorHeadingsList;

    private String pageLoad;

    private String variablePopup;

    private List variableList;
//    private List variableNotesList;

    private Map hiddenVarId = new HashMap();
    
    private String hiddenBftLineId;
    private String changedFlag ;
    private String preChangedFlag ;
    private String benefitLineDescription ;
	
    private String benefitLineReference ;
    private String benefitLinePva ;
    
    private List benefitLineMappingList;
    private String variableDescForNotes;
    private String variableNotePopup;
	
    private static final String MAJOR_HEADING_ID =WebConstants.MAJOR_HEADINGID; 
    private static final String MINOR_HEADING_ID =WebConstants.MINOR_HEADINGID;
    private static final String VARIABLE =WebConstants.VARIABLE;
    private static final String PROVIDER  =WebConstants.PROVIDER;
    private static final String FORMAT =WebConstants.FORMAT;
    private static final String REFERENCE =WebConstants.REFERENCE;
    private static final String DESCRIPTION =WebConstants.DESCRIPTION;
    private static String BREAD_CRUMB_TEXT_STEP7 = WebConstants.CONTRACT_BREADCRUMB_STEP7;

    private String variableNotes;
    private String notesCheckboxFlag;
    private String benefitLineListSize;
    private String variableId;
    private String variableDesc;
    
	
    public ContrMigratProductMappingBackingBean() {
        super();
        this
                .setBreadCrumbTextLeft(BREAD_CRUMB_TEXT_STEP7);
        String contractId = this.getMigrationContractSession()
                .getMigrationContract().getContractId();
        String startDate = WPDStringUtil.convertDateToString(this
                .getMigrationContractSession().getStartDateEwpd());
        String endDate = WPDStringUtil.convertDateToString(this
                .getMigrationContractSession().getEndDate());
        this.setBreadCrumbTextRight(WebConstants.BREAD_CRUMB_CONTRACT
                + contractId + " (" + startDate + " - " + endDate + ")");
        this.changedFlag= WebConstants.FLAG_N;
        this.preChangedFlag = WebConstants.FLAG_N;
    }


    /**
     
     * * @return Returns the benefitLineList.
     */
    public List getBenefitLineList() {        
        List benefitLineList = new ArrayList();
        RetrieveMigVariableMappingRequest retrieveMigVariableMappingRequest = (RetrieveMigVariableMappingRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_MIG_VARIABLE_REQUEST);
        retrieveMigVariableMappingRequest
                .setAction(RetrieveMigVariableMappingRequest.RETRIEVE_MIG_VARIABLE_MAPPING_DATA);
        this.getMigrationContractSession().getNavigationInfo()
                .setUpdateLastAccessedPageOnly(true);

        MigrationContractSession migrationContractSession = getMigrationContractSession();

        MigrationContract migrationContract = migrationContractSession
                .getMigrationContract();
        retrieveMigVariableMappingRequest
                .setMigrationContractSession(migrationContractSession);
        MigProductMappingVO productMappingVO = new MigProductMappingVO();
        int compId = 0;
        int stdId = 0;
        if (migrationContract.getBenefitComponentId() == 0) {
            compId = 0;
        } else {
            compId = migrationContract.getBenefitComponentId();
        }
        if (migrationContract.getStdBenefitId() == 0) {
            stdId = 0;
        } else {
            stdId = migrationContract.getStdBenefitId();
        }
        productMappingVO.setBenefitComponentId(compId);
        productMappingVO.setStdBenefitId(stdId);

        if (!StringUtil.isEmpty(getMigrationContractSession()
                .getDateSegmentId())) {
            productMappingVO
                    .setDateSegmentId(Integer
                            .parseInt(getMigrationContractSession()
                                    .getDateSegmentId()));
        }
        setMigrationContractSessionObject(productMappingVO);
        retrieveMigVariableMappingRequest
                .setMigProductMappingVO(productMappingVO);

        RetrieveMigVariableMappingResponse retrieveMigVariableMappingResponse = (RetrieveMigVariableMappingResponse) this
                .executeService(retrieveMigVariableMappingRequest);
        if (null != retrieveMigVariableMappingResponse) {
            benefitLineList = retrieveMigVariableMappingResponse
                    .getBenefitLineList();

        }
        if (null == benefitLineList) {
            benefitLineList = new ArrayList();
        }
        return benefitLineList;
    }


    /**
     * @return List
     */
    public List getConflictRecordList() {
        List conflictRecordList = new ArrayList();

        RetrieveMigVariableMappingRequest retrieveMigVariableMappingRequest = (RetrieveMigVariableMappingRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_MIG_VARIABLE_REQUEST);
        retrieveMigVariableMappingRequest
                .setAction(RetrieveMigVariableMappingRequest.RETRIEVE_CONFLICTING_DATA);
        MigrationContractSession migrationContractSession = getMigrationContractSession();
        retrieveMigVariableMappingRequest
                .setMigrationContractSession(migrationContractSession);

        RetrieveMigVariableMappingResponse retrieveMigVariableMappingResponse = (RetrieveMigVariableMappingResponse) this
                .executeService(retrieveMigVariableMappingRequest);
        if (null != retrieveMigVariableMappingResponse) {

            conflictRecordList = retrieveMigVariableMappingResponse
                    .getConflictRecordList();

        }
        if (null == conflictRecordList) {
            conflictRecordList = new ArrayList();
        }
        return conflictRecordList;
    }


    /**
     * @param productMappingVO
     */
    private void setMigrationContractSessionObject(
            MigProductMappingVO productMappingVO) {

        MigrationContractSession migrationContractSession = getMigrationContractSession();
        MigrationContract migrationContract = migrationContractSession
                .getMigrationContract();
        migrationContract.setBenefitComponentId(productMappingVO
                .getBenefitComponentId());
        migrationContract.setStdBenefitId(productMappingVO.getStdBenefitId());

    }


    /**
     * @return Returns the panel.
     */
    public HtmlPanelGrid getPanel() {
    	
        HtmlPanelGrid panel = new HtmlPanelGrid();
        
        
        List benefitLineList = this.getBenefitLineMappingList();
        List conflictRecordList = getConflictRecordList();
        
        if(null==benefitLineList || benefitLineList.size()==0){        	
        	benefitLineList= this.getBenefitLineList();
        }
        
        MigProductMappingVO migProductMappingVO = null;
        if (null != benefitLineList) {
        	this.benefitLineListSize = String.valueOf(benefitLineList.size());
            panel.setWidth("100%");
            panel.setColumns(8);
            panel.setBorder(0);
            panel.setBgcolor("#cccccc");
            panel.setCellpadding("3");
            panel.setCellspacing("1");

            if (benefitLineList.size() > 0) {
                for (int i = 0; i < benefitLineList.size(); i++) {
                    migProductMappingVO = (MigProductMappingVO) benefitLineList
                            .get(i);

                    boolean isConflict = checkForConflicts(migProductMappingVO
                    					.getBftLineId(), conflictRecordList);


                    HtmlOutputText outputText1 = new HtmlOutputText();
                    outputText1.setId("description" + i);
                    outputText1.setValue(migProductMappingVO
                            .getBftLineDescription());            
                    
                    HtmlInputHidden hiddenOutputText1 = new HtmlInputHidden();
                    hiddenOutputText1.setId("hiddenDescription" + i);
                    hiddenOutputText1.setValue(""
                            + migProductMappingVO.getBftLineDescription());
                    
                    HtmlOutputText outputText2 = new HtmlOutputText();
                    outputText2.setId("pva" + i);
                    outputText2.setValue(migProductMappingVO.getBftPva());

                    HtmlInputHidden hiddenProvider = new HtmlInputHidden();
                    hiddenProvider.setId("hiddenProvider" + i);
                    hiddenProvider.setValue(""
                            + migProductMappingVO.getBftPva());

                    HtmlOutputText outputText3 = new HtmlOutputText();
                    outputText3.setId("reference" + i);
                    outputText3.setValue(migProductMappingVO.getBftReference());
                    
                    HtmlInputHidden hiddenOutputText3 = new HtmlInputHidden();
                    hiddenOutputText3.setId("hiddenReference" + i);
                    hiddenOutputText3.setValue(""
                            + migProductMappingVO.getBftReference());

                    HtmlInputText inputText1 = new HtmlInputText();
                    inputText1.setId("value" + i);
                    inputText1.setStyleClass("formInputFieldForSequenceNo");
                    inputText1.setValue("" + migProductMappingVO.getValue());

                    if (migProductMappingVO.getValue() == null
                            || migProductMappingVO.getValue().equals("null")) {
                        inputText1.setValue("");
                    }
                   // inputText1.setReadonly(true);

                    HtmlInputHidden hiddenVarValue = new HtmlInputHidden();
                    hiddenVarValue.setId("hiddenValue" + i);
                    hiddenVarValue.setValue(migProductMappingVO.getValue());

                    ValueBinding valBindingForVariableValue = FacesContext
                            .getCurrentInstance().getApplication()
                            .createValueBinding(
                                    "#{ContrMigratProductMappingBackingBean.hiddenVarValue["
                                            + i + "]}");
                    hiddenVarValue.setValueBinding("value",
                            valBindingForVariableValue);

                    HtmlOutputText outputText4 = new HtmlOutputText();
                    outputText4.setId("empty" + i);
                    outputText4.setValue("<->");

                    HtmlInputText inputText2 = new HtmlInputText();
                    inputText2.setId("variable" + i);
                    inputText2.setStyleClass("formInputField");
                    inputText2.setValue(""
                            + migProductMappingVO.getVarDetails());
                   // inputText2.setReadonly(true);

                    HtmlInputHidden hiddenVarDetails = new HtmlInputHidden();
                    hiddenVarDetails.setId("hiddenVariableDetails" + i);
                    if(migProductMappingVO.getFlag()!=1){
                        hiddenVarDetails.setValue(migProductMappingVO
                            .getVarDetails());
                    }else{
                        if (canOverride()) {
                            hiddenVarDetails.setValue(migProductMappingVO
                                    .getVarDetails());
                        }    
                    }
                    HtmlOutputText outputText5 = new HtmlOutputText();
                    outputText5.setId("emptySpace" + i);
                    outputText5.setValue(" ");                  
                  

                    ValueBinding valBindingForVariable = FacesContext
                            .getCurrentInstance().getApplication()
                            .createValueBinding(
                                    "#{ContrMigratProductMappingBackingBean.hiddenVariable["
                                            + i + "]}");
                    hiddenVarDetails.setValueBinding("value",
                            valBindingForVariable);
                    
                    
                    HtmlInputHidden oldVarDetails = new HtmlInputHidden();
                    oldVarDetails.setId("oldVariableDetails" + i);
                    oldVarDetails.setValue(migProductMappingVO
                            .getVarDetails());
                    
                    ValueBinding oldVariableValueBinding = FacesContext
                    	.getCurrentInstance().getApplication()
                    		.createValueBinding(
                    		        "#{ContrMigratProductMappingBackingBean.oldVariable["
                                    	+ i + "]}");
                    oldVarDetails.setValueBinding("value",
                            oldVariableValueBinding);
                    
                    

                    HtmlInputHidden hiddenForBenLineId = new HtmlInputHidden();
                    hiddenForBenLineId.setId("BenLineId" + i);
                    hiddenForBenLineId.setValue(new Integer(migProductMappingVO
                            .getBftLineId()));

                    ValueBinding valBindingForBenLineId = FacesContext
                            .getCurrentInstance().getApplication()
                            .createValueBinding(
                                    "#{ContrMigratProductMappingBackingBean.hiddenBenLineId["
                                            + i + "]}");
                    hiddenForBenLineId.setValueBinding("value",
                            valBindingForBenLineId);

                    HtmlCommandButton selectButton = new HtmlCommandButton();
                    selectButton.setId("filterButton" + i);
                    selectButton.setStyle("border:0;");
                    selectButton.setImage("../../images/select.gif");
                    selectButton.setTitle("Select");
                    selectButton.setOnclick("getVariableValue(" + i
                            + ");return false;");

                    
                    HtmlInputHidden hiddenVariableId = new HtmlInputHidden();
                   
                    hiddenVariableId.setId("variableHidden" + i);
                    if(migProductMappingVO.getFlag()!=1){
	                    hiddenVariableId.setValue(""
	                            + migProductMappingVO.getVariableId());
                    }else{
                        if (canOverride()) {
                            hiddenVariableId.setValue(""
    	                            + migProductMappingVO.getVariableId());
                        }    
                    }
                   
                    ValueBinding valBindingForVariableId = FacesContext
                        .getCurrentInstance().getApplication()
                        .createValueBinding(
                                "#{ContrMigratProductMappingBackingBean.hiddenVarId["
                                        + i + "]}");
                    hiddenVariableId.setValueBinding("value",
                        valBindingForVariableId);
                    
                    if (isConflict) {
                        inputText1
                                .setStyleClass("formInputFieldForValueConflict");
                        inputText2
                                .setStyleClass("formInputFieldForVariableConflict");
                    }

                    HtmlInputHidden hiddenFormat = new HtmlInputHidden();
                    hiddenFormat.setId("format" + i);
                    hiddenFormat.setValue("" + migProductMappingVO.getFormat());                 
              
                    HtmlCommandButton deleteButton = new HtmlCommandButton();
                    deleteButton.setId("deleteButton" + i);
                    deleteButton.setStyle("border:0;");
                    deleteButton.setImage("../../images/delete.gif");
                    deleteButton.setTitle("Delete");
                    deleteButton.setOnclick("deleteMapping(" + migProductMappingVO.getBftLineId()
                            + ");return false;");
                    
                    HtmlCommandButton viewButton = new HtmlCommandButton();
                    viewButton.setId("viewButton" + i);
                    viewButton.setStyle("border:0;visibility:hidden");
                    viewButton.setImage("../../images/view.gif");
                    viewButton.setTitle("View");
                    viewButton.setOnclick("getVariableNote(" + i
                            + ");return false;");
                    
                    
                    
                    HtmlSelectBooleanCheckbox  checkBox = new HtmlSelectBooleanCheckbox();
                    checkBox.setId("checkBox" + i);                   
                    checkBox.setStyle("border:0;visibility:hidden");  
                    checkBox.setOnclick("checkVarNotes('" + i + "');");
                    
                    if(null !=migProductMappingVO.getNotesFlag() && migProductMappingVO.getNotesFlag().equalsIgnoreCase("Y")){
                    	checkBox.setSelected(true);	
                    	checkBox.setValue(Boolean.TRUE);
                    }else{
                    	checkBox.setSelected(false);
                    	checkBox.setValue(Boolean.FALSE);
                    }
                   
                    if(("Y".equalsIgnoreCase(migProductMappingVO.getVarNoteFlag()))|| ("Y".equalsIgnoreCase(migProductMappingVO.getNotesFlag()))){
                    	viewButton.setStyle("border:0;visibility:visible");
                    	checkBox.setStyle("border:0;visibility:visible");
                    }
                    
                    ValueBinding valBindingForNotes = FacesContext
                            .getCurrentInstance().getApplication()
                            .createValueBinding(
                                    "#{ContrMigratProductMappingBackingBean.hiddenNotesFlag["
                                            + i + "]}");
                    checkBox.setValueBinding("value",
                    						valBindingForNotes);                
                   
                    HtmlInputHidden hiddenFlag = new HtmlInputHidden();
                    hiddenFlag.setId("hideFlag" + i);
                    
                    if(null!=migProductMappingVO.getNotesFlag())
                    	hiddenFlag.setValue("" + migProductMappingVO.getNotesFlag());
                    else
                    	hiddenFlag.setValue("" + migProductMappingVO.getVarNoteFlag());
                    
                    ValueBinding valBindingForHideFlag = FacesContext
                    	.getCurrentInstance().getApplication()
						.createValueBinding(
                            "#{ContrMigratProductMappingBackingBean.hideFlag["
                                    + i + "]}");
                    hiddenFlag.setValueBinding("value",
                    					valBindingForHideFlag);
                    
                    HtmlOutputLabel desc = new HtmlOutputLabel();
                    desc.setId("desc" + i);

                    HtmlOutputLabel pva = new HtmlOutputLabel();
                    pva.setId("provider" + i);

                    HtmlOutputLabel ref = new HtmlOutputLabel();
                    ref.setId("ref" + i);

                    HtmlOutputLabel val = new HtmlOutputLabel();
                    val.setId("val" + i);

                    HtmlOutputLabel empt = new HtmlOutputLabel();
                    empt.setId("emp" + i);

                    HtmlOutputLabel var = new HtmlOutputLabel();
                    var.setId("var" + i);

                    HtmlOutputLabel delete = new HtmlOutputLabel();
                    delete.setId("delete" + i);
                    
                    HtmlOutputLabel notes = new HtmlOutputLabel();
                    notes.setId("notes" + i);

//                    HtmlOutputLabel hiddenVar = new HtmlOutputLabel();
//                    hiddenVar.setId("hiddenVar" + i);

                    desc.getChildren().add(outputText1);
                    desc.getChildren().add(hiddenForBenLineId);
                    desc.getChildren().add(hiddenOutputText1);                    
                    pva.getChildren().add(outputText2);
                    pva.getChildren().add(hiddenProvider);
                    ref.getChildren().add(outputText3);
                    ref.getChildren().add(hiddenFormat);
                    ref.getChildren().add(hiddenOutputText3);
                    val.getChildren().add(inputText1);
                    val.getChildren().add(hiddenVarValue);
                    empt.getChildren().add(outputText4);
                    var.getChildren().add(inputText2);
                    var.getChildren().add(hiddenVarDetails);
                    var.getChildren().add(oldVarDetails);
                    var.getChildren().add(outputText5);
                    var.getChildren().add(hiddenVariableId);                    
                    
                    notes.getChildren().add(viewButton);
                    notes.getChildren().add(checkBox);
                    notes.getChildren().add(hiddenFlag);
                  
                    
                    if(migProductMappingVO.getFlag()==1){
                        if (canOverride()) {
                            var.getChildren().add(selectButton);
                            delete.getChildren().add(deleteButton);
                        }                        
                    }else {
                        var.getChildren().add(selectButton);
                    }

                    if (isConflict) {
                        var.setStyleClass("migrationWizardConflict");
                        desc.setStyleClass("migrationWizardConflict");
                        pva.setStyleClass("migrationWizardConflict");
                        ref.setStyleClass("migrationWizardConflict");

                    }
                    panel.getChildren().add(desc);
                    panel.getChildren().add(pva);
                    panel.getChildren().add(ref);
                    panel.getChildren().add(val);
                    panel.getChildren().add(empt);
                    panel.getChildren().add(var);
                    panel.getChildren().add(delete);
                    panel.getChildren().add(notes);
                }
            }
        }

        getSession().setAttribute(WebConstants.TREE_EXP_FLAG,null);
        getSession().setAttribute("MIGRATION_TREE_NEXT",null);
        getSession().setAttribute("MIGRATION_TREE_BACK",null);
        return panel;
    }


    /**
     * @param bftLineId
     * @return
     */
    private boolean checkForConflicts(int bftLineId, List conflictRecordList) {
//        List conflictRecordList = getConflictRecordList();
        MigProductMappingVO migProductMappingVO = null;
        if (conflictRecordList.size() > 0) {

            Iterator conflictRecordIter = conflictRecordList.iterator();
            while (conflictRecordIter.hasNext()) {

                migProductMappingVO = (MigProductMappingVO) conflictRecordIter
                        .next();

                if (migProductMappingVO.getBftLineId() == bftLineId) {

                    return true;
                }

            }
        }
        return false;
    }


   /**
    * 
    * @return HtmlPanelGrid
    */
    
    public HtmlPanelGrid getHeadingPanel() {
        HtmlPanelGrid headingPanel = new HtmlPanelGrid();
        headingPanel.setWidth("100%");
        headingPanel.setColumns(8);
        headingPanel.setBorder(0);
        headingPanel.setBgcolor("#cccccc");
        headingPanel.setCellpadding("3");
        headingPanel.setCellspacing("1");

        HtmlOutputText outputText1 = new HtmlOutputText();
        outputText1.setValue("Description");
        outputText1.setId("Description");
        outputText1.setStyleClass("dataTableHeader");

        HtmlOutputText outputText2 = new HtmlOutputText();
        outputText2.setValue("PVA");
        outputText2.setId("PVA");
        outputText2.setStyleClass("dataTableHeader");

        HtmlOutputText outputText3 = new HtmlOutputText();
        outputText3.setValue("Reference");
        outputText3.setId("Reference");
        outputText3.setStyleClass("dataTableHeader");

        HtmlOutputText outputText4 = new HtmlOutputText();
        outputText4.setValue("Value");
        outputText4.setId("Value");
        outputText4.setStyleClass("dataTableHeader");

        HtmlOutputText outputText5 = new HtmlOutputText();
        outputText5.setValue("Variable");
        outputText5.setId("Variable");
        outputText5.setStyleClass("dataTableHeader");

        HtmlOutputText outputText6 = new HtmlOutputText();
        outputText6.setValue(" ");
        outputText6.setId("Delete");
        outputText6.setStyleClass("dataTableHeader");

        HtmlOutputText outputText7 = new HtmlOutputText();
        outputText7.setValue(" ");
        outputText7.setId("Empt");
        outputText7.setStyleClass("dataTableHeader");
        
        HtmlOutputText outputText8 = new HtmlOutputText();
        outputText8.setValue("Notes ");
        outputText8.setId("Notes");
        outputText8.setStyleClass("dataTableHeader");

        headingPanel.getChildren().add(outputText1);
        headingPanel.getChildren().add(outputText2);
        headingPanel.getChildren().add(outputText3);
        headingPanel.getChildren().add(outputText4);
        headingPanel.getChildren().add(outputText7);
        headingPanel.getChildren().add(outputText5);
        headingPanel.getChildren().add(outputText6);
        headingPanel.getChildren().add(outputText8);

        return headingPanel;
    }


    /**
     * @param panel
     *            The panel to set.
     */
    public void setPanel(HtmlPanelGrid panel) {
        this.panel = panel;
    }


    public String saveMappingDetails() {
        this.majorHeading = this.majorHeadinghidden;
        this.minorHeading = this.minorHeadinghidden;
        return saveMappingDetailsNew(false, false);
    }


    /**
     * @return String
     */
    public String saveMappingDetailsNew(boolean setNavFlag,
            boolean navigationFlag) {
        List variableIdList = getSavedListFromScreen();
        int action =SaveMigVariableMappingRequest.SAVE_MIG_VARIABLE_MAPPING_DATA;
        return performAction(setNavFlag, navigationFlag, variableIdList, action);
    }


    /**
     * @param setNavFlag
     * @param navigationFlag
     * @param variableIdList
     * @param action
     * @return String
     */
    private String performAction(boolean setNavFlag, boolean navigationFlag, List variableIdList, int action) {
        SaveMigVariableMappingRequest saveMigVariableMappingRequest = (SaveMigVariableMappingRequest) this
                .getServiceRequest(ServiceManager.SAVE_MIG_VARIABLE_REQUEST);
        saveMigVariableMappingRequest
                .setAction(action);
        saveMigVariableMappingRequest.setVariableMappingList(variableIdList);
        if (setNavFlag) {
            this.getMigrationContractSession().getNavigationInfo()
                    .setNavigationFlag(navigationFlag);
        }
        this.setToRequest(saveMigVariableMappingRequest);
        SaveMigVariableMappingResponse saveMigVariableMappingResponse = (SaveMigVariableMappingResponse) this
                .executeService(saveMigVariableMappingRequest);
        if (null != saveMigVariableMappingResponse) {
            if (saveMigVariableMappingResponse.isSuccess()) {

                this.addAllMessagesToRequest(saveMigVariableMappingResponse
                        .getMessages());
                
                getRequest().setAttribute("RETAIN_Value", "");
            }
        }    
       
        return WebConstants.MIG_CONTRACT_STEP7;
    }


    /**
     * @return String
     */
    public String getPreviousPage() {      
//        if(WebConstants.FLAG_Y.equalsIgnoreCase(this.preChangedFlag)){
//            String savedData = saveMappingDetailsNew(true,
//                    BusinessConstants.MIGRATION_NAVIGATION_FLAG_TRUE);
//        }
        this.majorHeadingId =WebConstants.EMPTY_STRING;
        this.minorHeadingId =WebConstants.EMPTY_STRING;
        this.majorHeadinghidden =WebConstants.EMPTY_STRING;
        this.minorHeadinghidden = WebConstants.EMPTY_STRING;
		MigrationContractSession  migrationContractSession =getMigrationContractSession();
		
		MigrationContract migrationContract = migrationContractSession.getMigrationContract();
		int compId =  0;
		int stdId = 0;
		if(migrationContract.getBenefitComponentId()  == 0){
			compId= 0;
		}else{
			compId = migrationContract.getBenefitComponentId();
		}
		if(migrationContract.getStdBenefitId()  == 0){
			stdId= 0;
		}else{
			stdId = migrationContract.getStdBenefitId();
		}
		String prodSysId= migrationContract.getEwpdProductSystemId();
		int productSysId = 0; 
		if(null != prodSysId && !("".equals(prodSysId))){
		    productSysId = Integer.parseInt(prodSysId);
		}
		else{
		    productSysId= 0;
		}
		BenefitComponentBenefit benefitComponentBenefit = null;
		User user = null;
		int i =2;
        try {
        	user = this.getUser();
		    benefitComponentBenefit = MigrationContractUtil.getInfoForTree(productSysId,compId, stdId ,i, migrationContractSession);
		}
		catch (SevereException se){
		    if (se.getLogId() == null || se.getLogId().trim().length() == 0) {
                if (se.getLogParameters() == null) {
                    se.setLogParameters(new ArrayList());
                }
                se.getLogParameters().add(this.getRequest());
                se.getLogParameters().add(user);
                Logger.logException(se);
            }
            ErrorMessage em = new ErrorMessage(WebConstants.DEFAULT_ERROR_MSG,
                    se.getLogId());
            List messages = new ArrayList();
            messages.add(em);
            addAllMessagesToRequest(messages);
		}
		if(null != benefitComponentBenefit ){
		    getSession().setAttribute(WebConstants.MIGRATION_TREE_BACK,benefitComponentBenefit);
		    //settin next comp and ben ids
		    migrationContract.setBenefitComponentId(benefitComponentBenefit.getBenefitComponentId());
		    migrationContract.setStdBenefitId(benefitComponentBenefit.getBenefitId());
		    migrationContract.setBenefitCompName(benefitComponentBenefit.getBenefitComponentDesc());
		    migrationContract.setStdBenefitName(benefitComponentBenefit.getBenefitDesc());
		    
		    this.getMigrationContractSession().setMigrationContract(migrationContract);
		    return goToNextPage(WebConstants.MIG_CONTRACT_STEP7);
		}
		else{
		    getSession().setAttribute(WebConstants.MIGRATION_TREE_BACK,null);
		    return goToNextPage(WebConstants.MIG_CONTRACT_STEP6);
		}
        
        
        
    }
    

    /**
     * @return String
     */
    public String getNextPage() {
    	 this.majorHeadinghidden =WebConstants.EMPTY_STRING;
         this.minorHeadinghidden = WebConstants.EMPTY_STRING;
        
        if(WebConstants.FLAG_Y.equalsIgnoreCase(this.changedFlag)){
	        saveMappingDetailsNew(true,
	                BusinessConstants.MIGRATION_NAVIGATION_FLAG_FALSE);
        }else{
        //update Step completed.    
            SaveMigVariableMappingRequest saveMigVariableMappingRequest = (SaveMigVariableMappingRequest) this
            		.getServiceRequest(ServiceManager.SAVE_MIG_VARIABLE_REQUEST);
            saveMigVariableMappingRequest
            			.setAction(SaveMigVariableMappingRequest.UPDATE_STEP_COMPLETED);
            
            this.getMigrationContractSession().getNavigationInfo()
                .setNavigationFlag(BusinessConstants.MIGRATION_NAVIGATION_FLAG_FALSE);
            
            this.setToRequest(saveMigVariableMappingRequest);
		    SaveMigVariableMappingResponse saveMigVariableMappingResponse = (SaveMigVariableMappingResponse) this
		            .executeService(saveMigVariableMappingRequest);
		    
		    if (null != saveMigVariableMappingResponse
	                && saveMigVariableMappingResponse.isSuccess()) {
	            this.setToSession(saveMigVariableMappingResponse);
	        }
        }       
        this.majorHeadingId =WebConstants.EMPTY_STRING;
        this.minorHeadingId =WebConstants.EMPTY_STRING;
        
        getSession().setAttribute(
                WebConstants.MESSAGE_LIST_STEP3,new ArrayList());

		MigrationContractSession  migrationContractSession =getMigrationContractSession();
		
		MigrationContract migrationContract = migrationContractSession.getMigrationContract();
		int compId =  0;
		int stdId = 0;
		if(migrationContract.getBenefitComponentId()  == 0){
			compId= 0;
		}else{
			compId = migrationContract.getBenefitComponentId();
		}
		if(migrationContract.getStdBenefitId()  == 0){
			stdId= 0;
		}else{
			stdId = migrationContract.getStdBenefitId();
		}
		String prodSysId= migrationContract.getEwpdProductSystemId();
		int productSysId = 0; 
		if(null != prodSysId && !("".equals(prodSysId))){
		    productSysId = Integer.parseInt(prodSysId);
		}
		else{
		    productSysId= 0;
		}
		BenefitComponentBenefit benefitComponentBenefit = null;
		User user = null;
		int i =1;
        try {
        	user = this.getUser();
		    benefitComponentBenefit = MigrationContractUtil.getInfoForTree(productSysId,compId, stdId ,i,this.getMigrationContractSession());
		}
		catch (SevereException se){
		    if (se.getLogId() == null || se.getLogId().trim().length() == 0) {
                if (se.getLogParameters() == null) {
                    se.setLogParameters(new ArrayList());
                }
                se.getLogParameters().add(this.getRequest());
                se.getLogParameters().add(user);
                Logger.logException(se);
            }
            ErrorMessage em = new ErrorMessage(WebConstants.DEFAULT_ERROR_MSG,
                    se.getLogId());
            List messages = new ArrayList();
            messages.add(em);
            addAllMessagesToRequest(messages);
		}
		
		if(null != benefitComponentBenefit ){
		    getSession().setAttribute(WebConstants.MIGRATION_TREE_NEXT,benefitComponentBenefit);
		    //getSession().setAttribute("MIGRATION_STEP8_NEXT",null);
		    //settin next comp and ben ids
		    migrationContract.setBenefitComponentId(benefitComponentBenefit.getBenefitComponentId());
		    migrationContract.setStdBenefitId(benefitComponentBenefit.getBenefitId());
		    migrationContract.setBenefitCompName(benefitComponentBenefit.getBenefitComponentDesc());
		    migrationContract.setStdBenefitName(benefitComponentBenefit.getBenefitDesc());
		    
		    this.getMigrationContractSession().setMigrationContract(migrationContract);
		    getRequest().setAttribute("RETAIN_Value", "");
		    return goToNextPage(WebConstants.MIG_CONTRACT_STEP7);
		}
		else{
			getSession().setAttribute(WebConstants.TREE_EXP_FLAG,null);
			getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG,null);
			
		    getSession().setAttribute(WebConstants.MIGRATION_TREE_NEXT,null);
		    getSession().setAttribute(WebConstants.MIGRATION_TREE_BACK,null);
		    getSession().setAttribute(WebConstants.MIGRATION_STEP8_NEXT,null);
		    getSession().setAttribute(WebConstants.MIGRATION_STEP8_BACK,null);
		    getRequest().setAttribute("RETAIN_Value", "");
		    return goToNextPage(WebConstants.MIG_CONTRACT_STEP8);
		}
    }


    /**
     * @return String
     */
    public String done() {
        if (getMigrationContractSession().getNavigationInfo()
                .getStepCompleted() >= BusinessConstants.STEP8) {
            saveMappingDetailsNew(false, false);
            getRequest().setAttribute("RETAIN_Value", "");
            return goToNextPage(WebConstants.MIG_CONTRACT_STEP9);
        } else {
        	
            this.validationMessages = validateStepNumber();
            addAllMessagesToRequest(this.validationMessages);
            getSession().setAttribute(WebConstants.MESSAGE_LIST_STEP3,
                    this.validationMessages);
            getRequest().setAttribute("RETAIN_Value", "");
        }
        return WebConstants.MIG_CONTRACT_STEP7;
    }

    /**
     * @return List
     */
    private List getSavedListFromScreen() {

        List variableIdList = new ArrayList();
        Set keysForBenLineId = this.getHiddenBenLineId().keySet();
        Set keysForVariable = this.getHiddenVariable().keySet();
        Set keysForVarId = this.getHiddenVarId().keySet();
        Set keysForOldVariable = this.getOldVariable().keySet();
        Set keysForNotesFlag = this.getHiddenNotesFlag().keySet();

        Iterator keyIterForBenLineId = keysForBenLineId.iterator();
        Iterator keyIterForVariable = keysForVariable.iterator();
        Iterator keyIterForVarId = keysForVarId.iterator();
        Iterator keyIterForOldVar = keysForOldVariable.iterator();
        Iterator keyIterForNotes = keysForNotesFlag.iterator();
        
        Object varKey, idKey, id, variable, varIdKey, varId, oldVar, oldVarKey, notesFlag , flagKey;

        MigrationContract migrationContract = getMigrationContractSession()
                .getMigrationContract();

        while (keyIterForBenLineId.hasNext()) {
            varKey = keyIterForVariable.next();
            idKey = keyIterForBenLineId.next();
            varIdKey = keyIterForVarId.next();
            oldVarKey = keyIterForOldVar.next();
            flagKey =keyIterForNotes.next();
            
            id = this.getHiddenBenLineId().get(idKey);
            variable = this.getHiddenVariable().get(varKey);
            varId = this.getHiddenVarId().get(varIdKey);
            oldVar = this.getOldVariable().get(oldVarKey);
            notesFlag = this.getHiddenNotesFlag().get(flagKey);
            
            MigProductMappingVO migProductMappingVO = new MigProductMappingVO();
            
            migProductMappingVO.setBftLineId(Integer.parseInt((String) id));
            migProductMappingVO.setVariableId((String) varId);
           //Setting the values for Notes flag
            
            if(((Boolean)notesFlag).booleanValue()){
            	migProductMappingVO.setNotesFlag(WebConstants.FLAG_Y);
            }else{
            	migProductMappingVO.setNotesFlag(WebConstants.FLAG_N);
            }
            
            
            //To eliminate the value of variableId stored as "null"
            if (!StringUtil.isEmpty(migProductMappingVO.getVariableId())) {
                if (WebConstants.NULL
                        .equalsIgnoreCase(migProductMappingVO.getVariableId())) {
                    migProductMappingVO.setVariableId(WebConstants.EMPTY_STRING);
                }
            }
            String oldVariable =WebConstants.EMPTY_STRING;
            String newVariable= WebConstants.EMPTY_STRING;
            
            if(null!=oldVar){
                oldVariable = (String)oldVar;
            }
            if(null!=variable){
                newVariable = (String)variable;
            }
            
            if(!(oldVariable.equalsIgnoreCase(newVariable))){                   
                migProductMappingVO.setVarDetails(newVariable);
                migProductMappingVO.setDeleteFlag(WebConstants.FLAG_Y);
            }else{
                migProductMappingVO.setVarDetails(WebConstants.EMPTY_STRING);
                migProductMappingVO.setVariableId(WebConstants.EMPTY_STRING);
                migProductMappingVO.setDeleteFlag(WebConstants.FLAG_N);
            }
            migProductMappingVO.setBenefitComponentId(migrationContract
                    .getBenefitComponentId());
            migProductMappingVO.setStdBenefitId(migrationContract
                    .getStdBenefitId());
            if (!(StringUtil.isEmpty(migrationContract
                    .getMigratedProdStructureMappingSysID()))) {
                migProductMappingVO.setMappingSysId(Integer
                        .parseInt(migrationContract
                                .getMigratedProdStructureMappingSysID()));
            }
            if (!(StringUtil.isEmpty(migrationContract.getMigrationSystemId()))) {
                migProductMappingVO.setMigContractSysId(Integer
                        .parseInt(migrationContract.getMigrationSystemId()));
            }
            
            if (!(StringUtil.isEmpty(getMigrationContractSession().getDateSegmentId()))) {
                migProductMappingVO.setDateSegmentId(Integer
                        .parseInt(getMigrationContractSession().getDateSegmentId()));
            }
            variableIdList.add(migProductMappingVO);
        }
        return variableIdList;
    }


    /**
     * @return Returns the pageLoad.
     */
    public String getPageLoad() {

        MigrationContract migrationContract = getMigrationContractSession()
                .getMigrationContract();
        int action = 0;
        RetrieveHeadingsRequest headingsRequest = new RetrieveHeadingsRequest();
        if(null != getRequest().getParameter(WebConstants.ACTION))
            action = new Integer(getRequest().getParameter(WebConstants.ACTION))
                .intValue();
        if (action == 1) {
            headingsRequest.setMajorHeading(WebConstants.EMPTY_STRING);
            headingsRequest.setStructureId(migrationContract
                    .getStructreProductMappingId());
            headingsRequest.setAction(action);
            headingsRequest.setSystem(migrationContract.getSystem());
        }
        if (action == 2) {
            majorHeading = getRequest().getParameter(MAJOR_HEADING_ID);
            if (!StringUtil.isEmpty(majorHeading)) {
                headingsRequest.setMajorHeadingId(majorHeading);
            }
            headingsRequest.setMinorHeading(WebConstants.EMPTY_STRING);
            headingsRequest.setStructureId(migrationContract
                    .getStructreProductMappingId());
            headingsRequest.setSystem(migrationContract.getSystem());
            headingsRequest.setAction(action);
        }
        RetrieveHeadingsResponse retrieveHeadingsResponse = (RetrieveHeadingsResponse) executeService(headingsRequest);
        if (null != retrieveHeadingsResponse) {
            if (null != retrieveHeadingsResponse.getMajorHeadingsList()) {
                this.majorHeadingsList = retrieveHeadingsResponse
                        .getMajorHeadingsList();
            }
            if (null != retrieveHeadingsResponse.getMinorHeadingList()) {
                this.minorHeadingsList = retrieveHeadingsResponse
                        .getMinorHeadingList();
            }
        }
        return WebConstants.EMPTY_STRING;
    }


    /**
     * @param pageLoad
     *            The pageLoad to set.
     */
    public void setPageLoad(String pageLoad) {
        this.pageLoad = pageLoad;
    }


    /**
     * @return Returns the variablePopup.
     */
    public String getVariablePopup() {
        MigrationContractSession migrationContractSession = getMigrationContractSession();
        MigrationContract migrationContract = getMigrationContractSession()
                .getMigrationContract();

        RetrieveHeadingsRequest headingsRequest = (RetrieveHeadingsRequest) this
                .getServiceRequest(ServiceManager.LEGACY_HEADING_REQUEST);
        String minorHeadingId = getRequest().getParameter(MINOR_HEADING_ID);
        String majorHeadingId = getRequest().getParameter(MAJOR_HEADING_ID);
        int action = RetrieveHeadingsRequest.RETRIEVE_VARIABLE_POPUP;
        String variableId = getRequest().getParameter(WebConstants.VARIABLEID);
        String variableDesc = getRequest().getParameter(WebConstants.VARIABLE_DESC);
        if(StringUtil.isEmpty(variableId)){
            this.variableId = "";
            this.variableDescForNotes = "";
        }else{
        	this.variableId = variableId;
            this.variableDesc = variableDesc;
        }
        String provider = getRequest().getParameter(PROVIDER);
        String format = getRequest().getParameter(FORMAT);
        
        this.setBenefitLineDescription(getRequest().getParameter(DESCRIPTION));
        this.setBenefitLinePva(provider);
		this.setBenefitLineReference(getRequest().getParameter(REFERENCE));
        
        getRequest().getSession().removeAttribute(VARIABLE);
        getRequest().getSession().removeAttribute(PROVIDER);
        getRequest().getSession().removeAttribute(FORMAT);
        
        
		
		
        if (!StringUtil.isEmpty(majorHeadingId)) {
            headingsRequest.setMajorHeadingId(majorHeadingId);
        }
        if (!StringUtil.isEmpty(minorHeading)) {
            headingsRequest.setMinorHeading(minorHeading);
        }

        headingsRequest.setStructureId(migrationContract
                .getStructreProductMappingId());
        
        headingsRequest.setContractId(migrationContract.getContractId());
        headingsRequest.setFormat(format);
        headingsRequest.setMinorHeadingId(minorHeadingId);
        headingsRequest.setSystem(migrationContract.getSystem());
        headingsRequest.setPvar(provider);
        
        headingsRequest.setVariableId(WebConstants.EMPTY_STRING);
        headingsRequest.setOption(13);
       
        headingsRequest.setStartDate(migrationContractSession
                .getStartDateLegacy());
        headingsRequest.setAction(action);
        RetrieveHeadingsResponse retrieveHeadingsResponse = (RetrieveHeadingsResponse) executeService(headingsRequest);
        if (null != retrieveHeadingsResponse) {
            if (null != retrieveHeadingsResponse.getVariableList()) {        	
            	
                this.variableList = retrieveHeadingsResponse.getVariableList();
            }
        }
        return WebConstants.EMPTY_STRING;
    }
    
    /**
	 * @return Returns the variableNotePopup.
	 */
	
    public String getVariableNotePopup(){
    	
    	String variableId = getRequest().getParameter(WebConstants.VARIABLEID_FORNOTES);
    	String variableDesc = getRequest().getParameter(WebConstants.VARIABLEDESC);
    	
    	String notesCheckFlag =getRequest().getParameter(WebConstants.NOTECHECKBOXFLAG);

        RetrieveHeadingsRequest headingsRequest = (RetrieveHeadingsRequest) this
                .getServiceRequest(ServiceManager.LEGACY_HEADING_REQUEST);
        MigrationContractSession migrationContractSession = getMigrationContractSession();
        MigrationContract migrationContract = getMigrationContractSession()
                .getMigrationContract();
        headingsRequest.setContractId(migrationContract.getContractId());
        headingsRequest.setSystem(migrationContract.getSystem());
        if (!StringUtil.isEmpty(variableId)) {
            headingsRequest.setVariableId(variableId);
        }
        headingsRequest.setStartDate(migrationContractSession
                .getStartDateLegacy());
        headingsRequest.setAction(RetrieveHeadingsRequest.RETRIEVE_VARIABLE_NOTES_POPUP);
        
        RetrieveHeadingsResponse retrieveHeadingsResponse = (RetrieveHeadingsResponse) executeService(headingsRequest);
        if (null != retrieveHeadingsResponse) {
            if (null != retrieveHeadingsResponse.getVariableNotesList() && retrieveHeadingsResponse.getVariableNotesList().size()!=0) {
                
            	Iterator notesIter = retrieveHeadingsResponse.getVariableNotesList().iterator();
                while (notesIter.hasNext()){
                	LegacyVariable legacyVariable = (LegacyVariable)notesIter.next();
                	this.variableNotes = legacyVariable.getNotes();
                	this.notesCheckboxFlag = notesCheckFlag;
                }
                
            }
            else{
	
            	this.setValidationMessages(retrieveHeadingsResponse
                      .getMessages());
            }
        }
        this.variableDescForNotes =variableDesc;
        
    	return WebConstants.EMPTY_STRING;
    }


    /**
     * @return Returns the productName.
     */
    public String getProductName() {
        MigrationContract migrationContract = this
                .getMigrationContractSession().getMigrationContract();
        productName = migrationContract.getProductName();

        return productName;
    }


    /**
     * @param productName
     *            The productName to set.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }


    /**
     * @return Returns the benefitCompName.
     */
    public String getBenefitCompName() {
        MigrationContract migrationContract = this
                .getMigrationContractSession().getMigrationContract();
        benefitCompName = migrationContract.getBenefitCompName();
        return benefitCompName;
    }


    /**
     * @param benefitCompName
     *            The benefitCompName to set.
     */
    public void setBenefitCompName(String benefitCompName) {
        this.benefitCompName = benefitCompName;
    }


    /**
     * @return Returns the stdBenefitName.
     */
    public String getStdBenefitName() {
        MigrationContract migrationContract = this
                .getMigrationContractSession().getMigrationContract();
        stdBenefitName = migrationContract.getStdBenefitName();
        return stdBenefitName;
    }


    /**
     * @param stdBenefitName
     *            The stdBenefitName to set.
     */
    public void setStdBenefitName(String stdBenefitName) {
        this.stdBenefitName = stdBenefitName;
    }


    /**
     * @return Returns the structureName.
     */
    public String getStructureName() {
        MigrationContract migrationContract = this
                .getMigrationContractSession().getMigrationContract();
        structureName = migrationContract.getStructreProductMappingId();
        return structureName;
    }


    /**
     * @param benefitLineList
     *            The benefitLineList to set.
     */
    public void setBenefitLineList(List benefitLineList) {
        this.benefitLineList = benefitLineList;
    }


    public String getStandardBenefitMapping() {
        this.majorHeadingId =WebConstants.EMPTY_STRING;
        this.minorHeadingId =WebConstants.EMPTY_STRING;
        this.majorHeading =WebConstants.EMPTY_STRING;
        this.minorHeading =WebConstants.EMPTY_STRING;
        List benefitLineList = this.getBenefitLineList();
        this.setBenefitLineMappingList(benefitLineList);  
        return WebConstants.STD_BENEFIT_MAP;
    }


    /**
     * @return Returns the majorHeadingsList.
     */
    public List getMajorHeadingsList() {
        return majorHeadingsList;
    }


    /**
     * @param majorHeadingsList
     *            The majorHeadingsList to set.
     */
    public void setMajorHeadingsList(List majorHeadingsList) {
        this.majorHeadingsList = majorHeadingsList;
    }


    /**
     * @return Returns the minorHeadingsList.
     */
    public List getMinorHeadingsList() {
        return minorHeadingsList;
    }


    /**
     * @param minorHeadingsList
     *            The minorHeadingsList to set.
     */
    public void setMinorHeadingsList(List minorHeadingsList) {
        this.minorHeadingsList = minorHeadingsList;
    }


    /**
     * @param variablePopup
     *            The variablePopup to set.
     */
    public void setVariablePopup(String variablePopup) {
        this.variablePopup = variablePopup;
    }


    /**
     * @return Returns the variableList.
     */
    public List getVariableList() {
        return variableList;
    }


    /**
     * @param variableList
     *            The variableList to set.
     */
    public void setVariableList(List variableList) {
        this.variableList = variableList;
    }


    /**
     * @return Returns the majorHeadingId.
     */
    public String getMajorHeadingId() {
        return majorHeadingId;
    }


    /**
     * @param majorHeadingId
     *            The majorHeadingId to set.
     */
    public void setMajorHeadingId(String majorHeadingId) {
        this.majorHeadingId = majorHeadingId;
    }


    /**
     * @return Returns the minorHeadingId.
     */
    public String getMinorHeadingId() {
        return minorHeadingId;
    }


    /**
     * @param minorHeadingId
     *            The minorHeadingId to set.
     */
    public void setMinorHeadingId(String minorHeadingId) {
        this.minorHeadingId = minorHeadingId;
    }


    /**
     * @return Returns the majorHeadinghidden.
     */
    public String getMajorHeadinghidden() {
        return majorHeadinghidden;
    }


    /**
     * @param majorHeadinghidden
     *            The majorHeadinghidden to set.
     */
    public void setMajorHeadinghidden(String majorHeadinghidden) {
        this.majorHeadinghidden = majorHeadinghidden;
    }


    /**
     * @return Returns the minorHeadinghidden.
     */
    public String getMinorHeadinghidden() {
        return minorHeadinghidden;
    }


    /**
     * @param minorHeadinghidden
     *            The minorHeadinghidden to set.
     */
    public void setMinorHeadinghidden(String minorHeadinghidden) {
        this.minorHeadinghidden = minorHeadinghidden;
    }


    /**
     * @param structureName
     *            The structureName to set.
     */
    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }


    /**
     * @return Returns the hiddenVarId.
     */
    public Map getHiddenVarId() {
        return hiddenVarId;
    }


    /**
     * @param hiddenVarId
     *            The hiddenVarId to set.
     */
    public void setHiddenVarId(Map hiddenVarId) {
        this.hiddenVarId = hiddenVarId;
    }


    /**
     * @return Returns the hiddenBenLineId.
     */
    public Map getHiddenBenLineId() {
        return hiddenBenLineId;
    }


    /**
     * @param hiddenBenLineId
     *            The hiddenBenLineId to set.
     */
    public void setHiddenBenLineId(Map hiddenBenLineId) {
        this.hiddenBenLineId = hiddenBenLineId;
    }


    /**
     * @return Returns the hiddenVariable.
     */
    public Map getHiddenVariable() {
        return hiddenVariable;
    }


    /**
     * @param hiddenVariable
     *            The hiddenVariable to set.
     */
    public void setHiddenVariable(Map hiddenVariable) {
        this.hiddenVariable = hiddenVariable;
    }


    /**
     * @return Returns the hiddenVarValue.
     */
    public Map getHiddenVarValue() {
        return hiddenVarValue;
    }


    /**
     * @param hiddenVarValue
     *            The hiddenVarValue to set.
     */
    public void setHiddenVarValue(Map hiddenVarValue) {
        this.hiddenVarValue = hiddenVarValue;
    }


    /**
     * @return Returns the majorHeadingListForCombo.
     */
    public List getMajorHeadingListForCombo() {
        return majorHeadingListForCombo;
    }


    /**
     * @param majorHeadingListForCombo
     *            The majorHeadingListForCombo to set.
     */
    public void setMajorHeadingListForCombo(List majorHeadingListForCombo) {
        this.majorHeadingListForCombo = majorHeadingListForCombo;
    }


    /**
     * @return Returns the minorHeadingListForCombo.
     */
    public List getMinorHeadingListForCombo() {
        return minorHeadingListForCombo;
    }


    /**
     * @param minorHeadingListForCombo
     *            The minorHeadingListForCombo to set.
     */
    public void setMinorHeadingListForCombo(List minorHeadingListForCombo) {
        this.minorHeadingListForCombo = minorHeadingListForCombo;
    }


    /**
     * @return Returns the benComponent.
     */
    public String getBenComponent() {
        return benComponent;
    }


    /**
     * @param benComponent
     *            The benComponent to set.
     */
    public void setBenComponent(String benComponent) {
        this.benComponent = benComponent;
    }


    /**
     * @return Returns the majorHeading.
     */
    public String getMajorHeading() {
        return majorHeading;
    }


    /**
     * @param majorHeading
     *            The majorHeading to set.
     */
    public void setMajorHeading(String majorHeading) {
        this.majorHeading = majorHeading;
    }


    /**
     * @return Returns the minorHeading.
     */
    public String getMinorHeading() {
        return minorHeading;
    }


    /**
     * @param minorHeading
     *            The minorHeading to set.
     */
    public void setMinorHeading(String minorHeading) {
        this.minorHeading = minorHeading;
    }


    /**
     * @return Returns the product.
     */
    public String getProduct() {
        return product;
    }


    /**
     * @param product
     *            The product to set.
     */
    public void setProduct(String product) {
        this.product = product;
    }


    /**
     * @return Returns the resultList.
     */
    public List getResultList() {
        return resultList;
    }


    /**
     * @param resultList
     *            The resultList to set.
     */
    public void setResultList(List resultList) {
        this.resultList = resultList;
    }


    /**
     * @return Returns the stdBenefit.
     */
    public String getStdBenefit() {
        return stdBenefit;
    }


    /**
     * @param stdBenefit
     *            The stdBenefit to set.
     */
    public void setStdBenefit(String stdBenefit) {
        this.stdBenefit = stdBenefit;
    }


    /**
     * @return Returns the structure.
     */
    public String getStructure() {
        return structure;
    }


    /**
     * @param structure
     *            The structure to set.
     */
    public void setStructure(String structure) {
        this.structure = structure;
    }


    /**
     * @return Returns the productMappingList.
     */
    public List getProductMappingList() {
        return productMappingList;
    }


    /**
     * @param productMappingList
     *            The productMappingList to set.
     */
    public void setProductMappingList(List productMappingList) {
        this.productMappingList = productMappingList;
    }


    /**
     * Sets the validationMessages
     * 
     * @param validationMessages.
     */
    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
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
     * @param headingPanel
     *            The headingPanel to set.
     */
    public void setHeadingPanel(HtmlPanelGrid headingPanel) {
        this.headingPanel = headingPanel;
    }


    /**
     * To check whether the admin can override conflict
     * @return BOOLEAN
     */
    private boolean canOverride() {

        boolean canOverride = false;
        User user = null;
        try {
        	user = this.getUser();
            canOverride = user.isAuthorized(WebConstants.MIG_WIZARD,
                    WebConstants.OVER_RIDE_VAR);
        } catch (SevereException se) {
        	if (se.getLogId() == null || se.getLogId().trim().length() == 0) {
                if (se.getLogParameters() == null) {
                    se.setLogParameters(new ArrayList());
                }
                se.getLogParameters().add(this.getRequest());
                se.getLogParameters().add(user);
                Logger.logException(se);
            }
            ErrorMessage em = new ErrorMessage(WebConstants.DEFAULT_ERROR_MSG,
                    se.getLogId());
            List messages = new ArrayList();
            messages.add(em);
            addAllMessagesToRequest(messages);
         }
        return canOverride;
    }
    
    /**
     * Deleting the mapped record from Master table
     */
    public String deleteMappingDetails(){
        
       String strBftLineId = this.getHiddenBftLineId();
        
       MigProductMappingVO migProductMappingVO = getDeletedRecord(strBftLineId);
       List deleteRecordList = new ArrayList();
       deleteRecordList.add(migProductMappingVO);
       
       int action =SaveMigVariableMappingRequest.DELETE_MIG_VARIABLE_MAPPING_DATA;
       return performAction(false, false, deleteRecordList, action);
    
    }
    /**
     * @param strBftLineId
     */
    private MigProductMappingVO getDeletedRecord(String strBftLineId) {
        
        MigProductMappingVO migProductMappingVO = null;
        if(!StringUtil.isEmpty(strBftLineId)){ 
            
               int   intbftLineId = Integer.parseInt(strBftLineId);    
                     
               List varMapList = getSavedListFromScreen();	       
               if(varMapList.size()>0){       
                   Iterator variableMapIter = varMapList.iterator();	           
                   while(variableMapIter.hasNext()){            
                       migProductMappingVO =(MigProductMappingVO)variableMapIter.next();
                       if(migProductMappingVO.getBftLineId()==intbftLineId){
                           break;
                       }                   
                   }                  
               }
           }
        	return migProductMappingVO;
    }


    /**
     * @return hiddenBftLineId
     * 
     * Returns the hiddenBftLineId.
     */
    public String getHiddenBftLineId() {
        return hiddenBftLineId;
    }
    /**
     * @param hiddenBftLineId
     * 
     * Sets the hiddenBftLineId.
     */
    public void setHiddenBftLineId(String hiddenBftLineId) {
        this.hiddenBftLineId = hiddenBftLineId;
    }
    /**
     * @return changedFlag
     * 
     * Returns the changedFlag.
     */
    public String getChangedFlag() {
        return changedFlag;
    }
    /**
     * @param changedFlag
     * 
     * Sets the changedFlag.
     */
    public void setChangedFlag(String changedFlag) {
        this.changedFlag = changedFlag;
    }
    /**
     * @return preChangedFlag
     * 
     * Returns the preChangedFlag.
     */
    public String getPreChangedFlag() {
        return preChangedFlag;
    }
    /**
     * @param preChangedFlag
     * 
     * Sets the preChangedFlag.
     */
    public void setPreChangedFlag(String preChangedFlag) {
        this.preChangedFlag = preChangedFlag;
    }
    /**
     * @return oldVariable
     * 
     * Returns the oldVariable.
     */
    public Map getOldVariable() {
        return oldVariable;
    }
    /**
     * @param oldVariable
     * 
     * Sets the oldVariable.
     */
    public void setOldVariable(Map oldVariable) {
        this.oldVariable = oldVariable;
    }
	/**
	 * @return Returns the benefitLineMappingList.
	 */
	public List getBenefitLineMappingList() {
		return benefitLineMappingList;
	}
	/**
	 * @param benefitLineMappingList The benefitLineMappingList to set.
	 */
	public void setBenefitLineMappingList(List benefitLineMappingList) {
		this.benefitLineMappingList = benefitLineMappingList;
	}
	/**
	 * @return Returns the benefitLineDescription.
	 */
	public String getBenefitLineDescription() {
		return benefitLineDescription;
	}
	/**
	 * @param benefitLineDescription The benefitLineDescription to set.
	 */
	public void setBenefitLineDescription(String benefitLineDescription) {
		this.benefitLineDescription = benefitLineDescription;
	}
	/**
	 * @return Returns the benefitLinePva.
	 */
	public String getBenefitLinePva() {
		return benefitLinePva;
	}
	/**
	 * @param benefitLinePva The benefitLinePva to set.
	 */
	public void setBenefitLinePva(String benefitLinePva) {
		this.benefitLinePva = benefitLinePva;
	}
	/**
	 * @return Returns the benefitLineReference.
	 */
	public String getBenefitLineReference() {
		return benefitLineReference;
	}
	/**
	 * @param benefitLineReference The benefitLineReference to set.
	 */
	public void setBenefitLineReference(String benefitLineReference) {
		this.benefitLineReference = benefitLineReference;
	}

	/**
	 * @return Returns the variableDescForNotes.
	 */
	public String getVariableDescForNotes() {
		return variableDescForNotes;
	}
	/**
	 * @param variableDescForNotes The variableDescForNotes to set.
	 */
	public void setVariableDescForNotes(String variableDescForNotes) {
		this.variableDescForNotes = variableDescForNotes;
	}

	/**
	 * @param variableNotePopup The variableNotePopup to set.
	 */
	public void setVariableNotePopup(String variableNotePopup) {
		this.variableNotePopup = variableNotePopup;
	}

	/**
	 * @return Returns the variableNotes.
	 */
	public String getVariableNotes() {
		return variableNotes;
	}
	/**
	 * @param variableNotes The variableNotes to set.
	 */
	public void setVariableNotes(String variableNotes) {
		this.variableNotes = variableNotes;
	}
	
	/**
	 * @return Returns the hiddenNotesFlag.
	 */
	public Map getHiddenNotesFlag() {
		return hiddenNotesFlag;
	}
	/**
	 * @param hiddenNotesFlag The hiddenNotesFlag to set.
	 */
	public void setHiddenNotesFlag(Map hiddenNotesFlag) {
		this.hiddenNotesFlag = hiddenNotesFlag;
	}
	
	/**
	 * @return Returns the notesCheckboxFlag.
	 */
	public String getNotesCheckboxFlag() {
		return notesCheckboxFlag;
	}
	/**
	 * @param notesCheckboxFlag The notesCheckboxFlag to set.
	 */
	public void setNotesCheckboxFlag(String notesCheckboxFlag) {
		this.notesCheckboxFlag = notesCheckboxFlag;
	}
	

	/**
	 * @return Returns the benefitLineListSize.
	 */
	public String getBenefitLineListSize() {
		return benefitLineListSize;
	}
	/**
	 * @param benefitLineListSize The benefitLineListSize to set.
	 */
	public void setBenefitLineListSize(String benefitLineListSize) {
		this.benefitLineListSize = benefitLineListSize;
	}
	
	/**
	 * @return Returns the variableId.
	 */
	public String getVariableId() {
		return variableId;
	}
	/**
	 * @param variableId The variableId to set.
	 */
	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}
	/**
	 * @return Returns the variableDesc.
	 */
	public String getVariableDesc() {
		return variableDesc;
	}
	/**
	 * @param variableDesc The variableDesc to set.
	 */
	public void setVariableDesc(String variableDesc) {
		this.variableDesc = variableDesc;
	}
	/**
	 * @return Returns the hideFlag.
	 */
	public Map getHideFlag() {
		return hideFlag;
	}
	/**
	 * @param hideFlag The hideFlag to set.
	 */
	public void setHideFlag(Map hideFlag) {
		this.hideFlag = hideFlag;
	}
}