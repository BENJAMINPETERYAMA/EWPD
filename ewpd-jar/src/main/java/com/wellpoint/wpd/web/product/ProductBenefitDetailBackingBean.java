/*
 * ProductBenefitDetailBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

import org.apache.commons.lang.RandomStringUtils;
import org.apache.myfaces.component.html.ext.HtmlSelectBooleanCheckbox;

import com.wellpoint.wpd.business.datatype.locatecriteria.DataTypeLocateResult;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLevel;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierBindingObject;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierCriteria;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierCriteriaPsblValue;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.override.benefit.bo.EntityBenefitDefenition;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;
import com.wellpoint.wpd.common.override.benefit.vo.ProductBenefitCustomizedVO;
import com.wellpoint.wpd.common.product.bo.ProductQuestUniqueReferenceBO;
import com.wellpoint.wpd.common.product.request.RetrieveProductBenefitComponentRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductBenefitDefinitionRequest;
import com.wellpoint.wpd.common.product.request.SaveProductBenefitDefinitionRequest;
import com.wellpoint.wpd.common.product.response.ProductBenefitComponentResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductBenefitDefinitionResponse;
import com.wellpoint.wpd.common.product.response.SaveProductBenefitDefinitionResponse;
import com.wellpoint.wpd.common.tierdefinition.request.DeleteLevelFromTierRequest;
import com.wellpoint.wpd.common.tierdefinition.request.ProductTierDeleteRequest;
import com.wellpoint.wpd.common.tierdefinition.request.TierDefinitionRetrieveRequest;
import com.wellpoint.wpd.common.tierdefinition.response.DeleteLevelFromTierResponse;
import com.wellpoint.wpd.common.tierdefinition.response.ProductTierDeleteResponse;
import com.wellpoint.wpd.common.tierdefinition.response.TierDefinitionRetrieveResponse;
import com.wellpoint.wpd.common.util.BenefitTierUtil;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBenefitDetailBackingBean extends ProductBackingBean {

    private HtmlPanelGrid headerPanel = new HtmlPanelGrid();

    private HtmlPanelGrid tierHeaderPanel = new HtmlPanelGrid();
    
    private HtmlPanelGrid panel = new HtmlPanelGrid();
    
    private HtmlPanelGrid tierPanel = new HtmlPanelGrid();
       
    private Map benefitValueMap = new LinkedHashMap();
    
    private Map tierCriteriaMap = new HashMap();
    
    private Map tierLineValueMap = new LinkedHashMap();

    private EntityBenefitDefenition benefitDefinition = null;

    private List benefitLevelList;

    private List benefitLineList;

    private List benefitDefinitionsList;
    
    boolean benefitDefinitionsListRetrieved = false;

    private List deleteLevelList;

    private String printValue;

    private String dummyVar;

    private HtmlPanelGrid headerPanelForPrint = new HtmlPanelGrid();

    private HtmlPanelGrid panelForPrint = new HtmlPanelGrid();
    
    private HtmlPanelGrid panelForTierPrint = new HtmlPanelGrid();
	
    private String productType = null;

    private List validationMessages  = new ArrayList(6);

    private Map lineIdMap = new HashMap();
    
    private Map tierLineIdMap = new HashMap();
    
    private Map levelVisibleMap = new HashMap(); //for level visible check box
    
    private Map customizedSysIdMap = new HashMap();
    
    private Map customizedTierSysIdMap = new HashMap();
    
    private Map levelTierIdMap = new HashMap();    
    
    private Map tierIdMap = new HashMap();
    
    private Map hiddenLineCheckMap = new HashMap();// for line visible hidden check box 
    
    private Map hiddenLevelCheckMap = new HashMap();// for level visible hidden check box 
    
    private Map tierDefMap = new HashMap();

    private boolean printMode = false;
    
    private String benefitLevelHideFlag = null;

 	private String benefitLineHideFlag = null;
 	
    private boolean showHidden = false; 	//for show All check box
    
    private boolean benefitDisplayFlag = false;
    
    private boolean benefitFlag = false;
    
    private List duplicateBenefitLineRefList;
    
    private List duplicateQuestionRefList;
    
    private String loadDuplicateReference;
       
    private List messageList = null;
    
    private String productName;
    
    private int productVersion;
    
    private String totalTime;
    
    private String panelData = "";
    
    private String deleteTierLevelId="";
    
    private List modifiedTierLineIdList = null;
    
    private List modifiedTierCriteriaList = null;

    private int tierSysId;
    
    private String psvlLookupRecords;
	
	private List pbvlDefList;
    
    private String informationMsg;
    
    private boolean isLineValueModified = false;
    
	private Map dataHiddenValQualifier = new HashMap();

	private HashMap lineFreqValueMap = new HashMap();
	//added this field to increase the perfomance
	private HashMap hiddenLineFreqValueMap = new HashMap();
	
	private Map dataHiddenValTerm = new HashMap();
	//Hash Map for level description
	private Map dataHiddenValDesc = new HashMap();
	//CARS START
	private HashMap dataHiddenValTermTier = new HashMap();

	private HashMap dataHiddenValQualifierTier = new HashMap();	
	
	private HashMap lineFreqValueMapTier = new HashMap();
	
	private HashMap hiddenLineFreqValueMapTier = new HashMap();
	
	private HashMap dataHiddenValDescTier = new HashMap();
	
	private HashMap oldDescOutputTxtTier = new HashMap();
	
	boolean isFrequencyChanged = false;
	
	private String tieredTruncatedLvlDesc = null;
	
	boolean isTieredLvlDescTruncated = false;

    List lvlDescMessages = new ArrayList();	
	
	private HashMap dataHiddenOutputValDesc = new HashMap();
	
	private HashMap hiddenLowerLevelFreqValueMap = new HashMap();
	
	private HashMap dataHiddenLowerLevelValDesc = new HashMap();
	
	private HashMap hiddenTierLowerLevelFreqValueMap = new HashMap();
	
	private HashMap dataHiddenTierOutputValDesc = new HashMap();
	
	private HashMap dataHiddenTierLowerLevelValDesc = new HashMap();
	
	private boolean isErrorNonTierFlag = true;
	
	private boolean isErrorTierFlag = true;	
	//CARS END
    private List informationMessageToDisplayOnPage = new ArrayList();

	/**
	 * @return Returns the deleteTierLevelId.
	 */
	public String getDeleteTierLevelId() {
		return deleteTierLevelId;
	}
	/**
	 * @param deleteTierLevelId The deleteTierLevelId to set.
	 */
	public void setDeleteTierLevelId(String deleteTierLevelId) {
		this.deleteTierLevelId = deleteTierLevelId;
	}
   // private List tierDefList;
    
    private List tierLineList = new ArrayList();
    
    //private List tierLevelList = new ArrayList();
    
    private List tierDefList = null;
    private List modifiedDefList = null;
    private List lvlLineList = null;
    private List benefitTierDefinitionsList = null;
    
    private int tierIdToDelete;
    
  //  private boolean benefitHideFlag = false;

    public ProductBenefitDetailBackingBean() {
        //checks for view mode or edit mode to set the bread crumb text
        if (isViewMode()) {
            this.setBreadCumbTextForView();
        } else {
            this.setBreadCumbTextForEdit();
        }
    }


    /**
     * This method returns the value which is required for Benefit Definitions
     * page print
     * 
     * @return Returns the printValue.
     */
    public String getPrintValue() {
        String requestForPrint = getRequest().getAttribute(
                "printValueForBenDet").toString();
        if (null != requestForPrint && !requestForPrint.equals("")) {
            printValue = requestForPrint;
        } else {
            printValue = "";
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
     * @return Returns the deleteLevelList.
     */
    public List getDeleteLevelList() {
        return deleteLevelList;
    }


    /**
     * @param deleteLevelList
     *            The deleteLevelList to set.
     */
    public void setDeleteLevelList(List deleteLevelList) {
        this.deleteLevelList = deleteLevelList;
    }

    private Map levelIdMap = new LinkedHashMap();
    
    private Map checkBoxMap = new LinkedHashMap();

    private String levelsToDelete;


    /**
     * @return Returns the levelIdMap.
     */
    public Map getLevelIdMap() {
        return levelIdMap;
    }


    /**
     * @param levelIdMap
     *            The levelIdMap to set.
     */
    public void setLevelIdMap(Map levelIdMap) {
        this.levelIdMap = levelIdMap;
    }


    /**
     * @return Returns the levelsToDelete.
     */
    public String getLevelsToDelete() {
        return levelsToDelete;
    }


    /**
     * @param levelsToDelete
     *            The levelsToDelete to set.
     */
    public void setLevelsToDelete(String levelsToDelete) {
        this.levelsToDelete = levelsToDelete;
    }


    /**
     * This method fetches the benefitDefinition List which contains levels and
     * its corresponding lines Returns the benefitDefinitiosList
     * 
     * @return List benefitDefinitiosList.
     */
    public List getBenefitDefinitionsList() {
    	
    	
        Logger.logInfo("entered method getBenefitDefinitionsList");
        RetrieveProductBenefitDefinitionRequest retrieveProductBenefitDefinitionRequest = (RetrieveProductBenefitDefinitionRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_BENEFIT_DEFINITION_REQUEST);
        retrieveProductBenefitDefinitionRequest
                .setBenefitId(getProductSessionObject().getBenefitId());
        retrieveProductBenefitDefinitionRequest
                .setBenefitComponentId(getBenefitComponentIdFromSession());
        if(null!= this.getBenefitLevelHideFlag() && this.getBenefitLevelHideFlag().equals("T"))
        	retrieveProductBenefitDefinitionRequest.setBenefitLevelHideFlag(null);
        else
        	retrieveProductBenefitDefinitionRequest.setBenefitLevelHideFlag("F");
        if(null!= this.getBenefitLineHideFlag() && this.getBenefitLineHideFlag().equals("T"))
        	retrieveProductBenefitDefinitionRequest.setBenefitLineHideFlag(null);
        else
        	retrieveProductBenefitDefinitionRequest.setBenefitLineHideFlag("F");
        RetrieveProductBenefitDefinitionResponse retrieveProductBenefitDefinitionResponse = null;
        retrieveProductBenefitDefinitionResponse = (RetrieveProductBenefitDefinitionResponse) executeService(retrieveProductBenefitDefinitionRequest);
        List benefitDefinitions = new ArrayList();
     
        
        if (null != retrieveProductBenefitDefinitionResponse && null!= retrieveProductBenefitDefinitionResponse.getBenefitDefinitionsList()
        		&&!retrieveProductBenefitDefinitionResponse.getBenefitDefinitionsList().isEmpty()){
            benefitDefinitions = retrieveProductBenefitDefinitionResponse
                    .getBenefitDefinitionsList();
            if(null != retrieveProductBenefitDefinitionResponse.getCriteriaList()){
            	 tierDefList = BenefitTierUtil.getTieredList(retrieveProductBenefitDefinitionResponse.getCriteriaList());
            	 if(isEditMode()){
            	 	Map critiriaMap = getTierCriteriaValues(tierDefList);
            	 	super.getSession().setAttribute("critiriaMapInSession",critiriaMap);
            	 }
            	 super.setTierDefinitionListToSession(tierDefList);
            }
            
            if(null != retrieveProductBenefitDefinitionResponse.getLvlLineList()){
            	lvlLineList = BenefitTierUtil.getLvlLineList(retrieveProductBenefitDefinitionResponse.getLvlLineList());
            	if(isEditMode()){
            		Map lineWithLevelMap = getLinesWithLevelValues(lvlLineList,false);
            		super.getSession().setAttribute("lineWithLevelMapInSession",lineWithLevelMap);
            	}
            	super.setTierLevalListToSession(lvlLineList);
            }      
               
        }
        else{
        	return null;
        
        }
        
        return benefitDefinitions;
    }
    /**
     * This method iterate through level and lines and set level values for each lines in to a 
     * line object and will store it in a map
     * @param lvlLines
     * @return List
     */
    private Map getLinesWithLevelValues(List lvlLines,boolean fromInput){
    	Map lineMap =null;
    	
    	if(null != lvlLines){
    		BenefitTierBindingObject tierObject = null;    
    		lineMap = new LinkedHashMap();
    		for (Iterator benLevelItr = lvlLines.iterator(); benLevelItr.hasNext();) {
    			BenefitLevel benefitLevel = (BenefitLevel) benLevelItr.next();
    			
    			for (Iterator benLineItr = benefitLevel.getBenefitLines().iterator(); benLineItr.hasNext();) {
    				
    				BenefitLine benefitLine = (BenefitLine) benLineItr.next();
    				
    				tierObject = new BenefitTierBindingObject();
    				tierObject.setBenefitLineId(benefitLine.getLineSysId());
    				tierObject.setBenefitLevelId(benefitLevel.getLevelId());
    				if(fromInput)
    					tierObject.setLineValue(benefitLine.getLineValue());
    				else
    					tierObject.setLineValue(benefitLine.getBenefitValue());
    				tierObject.setBenefitTierId(benefitLevel.getTierSysId());    				
    				tierObject.setFrequencyValue(benefitLevel.getFrequencyId());
    				tierObject.setLevelDescription(benefitLevel.getLevelDesc());
    				
    				StringBuffer sb = new StringBuffer();
    				sb.append(benefitLevel.getTierSysId());
					sb.append("-");
					sb.append(tierObject.getBenefitLineId());					
    				
					String compositeKey = sb.toString();
    				/*-- Put the tierObject to a map with lineid and tier syssID as key --*/
    				lineMap.put(compositeKey,tierObject);
    			}}
    	}
    	return lineMap;
    	
    }
    /**
     * This method iterates through tier criteria values
     * @param criteriaValues
     * @return A map
     */
    private Map getTierCriteriaValues(List criteriaValues){
    	Map criteriaMap = null;
    	if(null != criteriaValues){
    		BenefitTierBindingObject tierObject = null;
    		criteriaMap =  new LinkedHashMap();
    		for (Iterator defIterator = criteriaValues.iterator(); defIterator.hasNext();) {
    			
    			BenefitTierDefinition tierDefinition = (BenefitTierDefinition) defIterator.next();
    			for (Iterator tierBoIterator = tierDefinition.getBenefitTiers().iterator(); tierBoIterator.hasNext();) {
    				BenefitTier benefitTier = (BenefitTier) tierBoIterator.next();
    				for (Iterator criteriaIterator = benefitTier.getBenefitTierCriteriaList().iterator(); criteriaIterator
    				.hasNext();) {
    					
    					BenefitTierCriteria tierCriteria = (BenefitTierCriteria) criteriaIterator.next();
    					tierObject = new BenefitTierBindingObject();
    					tierObject.setTierDefinitionId(tierDefinition.getBenefitTierDefinitionSysId());
    					tierObject.setBenefitTierId(benefitTier.getBenefitTierSysId());
    					tierObject.setTierCriteriaId(tierCriteria.getBenefitTierCriteriaSysId());
    					tierObject.setCriteriaValue(tierCriteria.getBenefitTierCriteriaValue());
    					StringBuffer sb = new StringBuffer();
    					sb.append(benefitTier.getBenefitTierSysId()+"-"+tierCriteria.getBenefitTierCriteriaSysId());
    					/*- This is the composite key created using PROD_TIER_SYS_ID and TIER_CRTRIA_SYS_ID for storing values in map-*/
    					String compositeKey = sb.toString();
    					criteriaMap.put(compositeKey,tierObject);
    					
    					
    				}				
    			}			
    		}  	
    		
    		
    	}
    	return criteriaMap;
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
     * This method returns request variable.
     * @param benefitDefList
     * @return
     */
    private TierDefinitionRetrieveRequest getTierDefinitionRetrieveRequest(List benefitDefList) {

    	BenefitLine entityBenefitLine = (BenefitLine) benefitDefList.get(0);
    	int benefitDefinitionId = entityBenefitLine.getBenefitDefinitionId();
    	
    	TierDefinitionRetrieveRequest tierDefRequest = (TierDefinitionRetrieveRequest) this
		.getServiceRequest(ServiceManager.BENEFIT_TIER_DEFINITION_REQUEST);
		tierDefRequest.setProductSysId(getProductKeyFromSession());
		tierDefRequest.setBenefitComponentSysId(getBenefitComponentIdFromSession());
		tierDefRequest.setBenefitDefinitionSysId(benefitDefinitionId);
		
		return tierDefRequest;
	}
    
   /**
 * Method to display all the hidden benefit levels on clicking the show hidden
 * @return
 */
	 public String loadHiddenLevels(){
	 		getRequest().setAttribute("RETAIN_Value", "");
	 	return "showHiddenBenefitDefinition";
	 }
    /**
     * Sets the benefitDefinitionsList
     * 
     * @param benefitDefinitionsList.
     */
    public void setBenefitDefinitionsList(List benefitDefinitionsList) {
        this.benefitDefinitionsList = benefitDefinitionsList;
    }


    /**
     * Returns the benefitDefinition
     * 
     * @return EntityBenefitDefenition benefitDefinition.
     */

    public EntityBenefitDefenition getBenefitDefinition() {
        return benefitDefinition;
    }


    /**
     * Sets the benefitDefinition
     * 
     * @param benefitDefinition.
     */
    public void setBenefitDefinition(EntityBenefitDefenition benefitDefinition) {
        this.benefitDefinition = benefitDefinition;
    }


    /**
     * Returns the benefitLevelList
     * 
     * @return List benefitLevelList.
     */
    public List getBenefitLevelList() {
        return benefitLevelList;
    }


    /**
     * Sets the benefitLevelList
     * 
     * @param benefitLevelList.
     */
    public void setBenefitLevelList(List benefitLevelList) {
        this.benefitLevelList = benefitLevelList;
    }


    /**
     * Returns the benefitLineList
     * 
     * @return List benefitLineList.
     */
    public List getBenefitLineList() {
        return benefitLineList;
    }


    /**
     * Sets the benefitLineList
     * 
     * @param benefitLineList.
     */
    public void setBenefitLineList(List benefitLineList) {
        this.benefitLineList = benefitLineList;
    }


    /**
     * Returns the headerPanel
     * 
     * @return HtmlPanelGrid headerPanel.
     */
    public HtmlPanelGrid getTierHeaderPanel() {

        Logger.logInfo("entered method getHeaderPanel");
        
        //sets the string which contains the levels to delete to null
        if (null != this.levelsToDelete) {
            this.levelsToDelete = null;
        }

        tierHeaderPanel = new HtmlPanelGrid();
        //        headerPanel.setWidth("850");

        tierHeaderPanel.setColumns(8);
        tierHeaderPanel.setWidth("100%");
        if(isEditMode()){
            tierHeaderPanel.setColumnClasses("gridColumn20,gridColumn15,gridColumn14,gridColumn6,gridColumn8,gridColumn10,gridColumn20,gridColumn7");
        }else if(isViewMode()){
            tierHeaderPanel.setColumnClasses("gridColumn16,gridColumn12,gridColumn16,gridColumn5,gridColumn8,gridColumn8,gridColumn17,gridColumn6");
        }
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

        headerText5.setValue("Benefit Value");
        headerText5.setId("bnftValue1");

        headerText9.setValue("Format");
        headerText9.setId("dataTypeLgnd1");

        headerText6.setValue("Reference");
        headerText6.setId("ref1");
        
        headerText8.setValue("Notes");
        headerText8.setId("note1");
       
        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
            headerText7.setValue(" ");
        }
        tierHeaderPanel.setStyleClass("dataTableHeader");
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
    
    public void setTierHeaderPanel(HtmlPanelGrid tierHeaderPanel) {
		this.tierHeaderPanel = tierHeaderPanel;
	}
	public HtmlPanelGrid getHeaderPanel() {

        Logger.logInfo("entered method getHeaderPanel");

        //sets the string which contains the levels to delete to null
        if (null != this.levelsToDelete) {
            this.levelsToDelete = null;
        }

        headerPanel = new HtmlPanelGrid();
        //        headerPanel.setWidth("850");

        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
            if (!isViewMode()) {
                headerPanel.setColumns(10);
                headerPanel.setWidth("100%");
            } else {
                headerPanel.setColumns(8);
                headerPanel.setWidth("100%");
            }
        } else {
            headerPanel.setColumns(7);
            headerPanel.setWidth("100%");
        }
        
        headerPanel.setBorder(0);
        headerPanel.setCellpadding("3");
        headerPanel.setCellspacing("1");
        headerPanel.setBgcolor("#cccccc");

        HtmlOutputText headerText1 = new HtmlOutputText();
        HtmlOutputText headerText2 = new HtmlOutputText();
        HtmlOutputText headerText3 = new HtmlOutputText();
        HtmlOutputText headerText4 = new HtmlOutputText();
        HtmlOutputText headerText5 = new HtmlOutputText();
        HtmlOutputText headerText6 = new HtmlOutputText();
        HtmlOutputText headerText7 = new HtmlOutputText();
      //  HtmlOutputText headerText8 = new HtmlOutputText();
        HtmlOutputText headerText9 = new HtmlOutputText();
        HtmlOutputText tierheader = new HtmlOutputText();
        
        //Set the checkbox for hiding and unhiding the benefit levels and lines
        HtmlOutputLabel checkBoxLabel = new HtmlOutputLabel();
        checkBoxLabel.setId("checkBoxLabel"+RandomStringUtils.randomAlphanumeric(15));
        HtmlOutputText checkBoxText = new HtmlOutputText();
        checkBoxText.setValue("Show Hidden");
        HtmlSelectBooleanCheckbox showHiddenCheckBox = new HtmlSelectBooleanCheckbox();
        showHiddenCheckBox.setId("showHiddenCheckBox");
        showHiddenCheckBox.setValue(Boolean.valueOf(isShowHidden()));
        showHiddenCheckBox.setOnclick("unsavedDataFinder()");
       // if(isBenefitDisplayFlag()){
        	checkBoxLabel.getChildren().add(showHiddenCheckBox);
        	checkBoxLabel.getChildren().add(checkBoxText);
        //}
        //else
        	//checkBoxLabel.getChildren().add(checkBoxText);
        
        headerText1.setValue("Description");
        headerText1.setId("desc");

        headerText2.setValue("Term");
        headerText2.setId("term");

        headerText3.setValue("Frequency - Qualifier");
        headerText3.setId("qualifier");

        headerText4.setValue("PVA");
        headerText4.setId("pva");

        headerText5.setValue("Benefit Value");
        headerText5.setId("bnftValue");

        headerText9.setValue("Format");
        headerText9.setId("dataTypeLgnd");

        headerText6.setValue("Reference");
        headerText6.setId("ref");
        
        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
            headerText7.setValue(" ");
        }
        headerPanel.setStyleClass("dataTableHeader");
        headerPanel.getChildren().add(headerText1);
        headerPanel.getChildren().add(headerText2);
        headerPanel.getChildren().add(headerText3);
        headerPanel.getChildren().add(headerText4);
        headerPanel.getChildren().add(headerText9);
        headerPanel.getChildren().add(headerText5);
        headerPanel.getChildren().add(headerText6);
        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
            headerPanel.getChildren().add(headerText7);
            if (!isViewMode()) {
                headerPanel.getChildren().add(checkBoxLabel);
                tierheader.setStyle("width:41px;");
                headerPanel.getChildren().add(tierheader);
            }
        }
        return headerPanel;
    }

    /**
     * Sets the headerPanel
     * 
     * @param headerPanel.
     */
    public void setHeaderPanel(HtmlPanelGrid headerPanel) {
        this.headerPanel = headerPanel;
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
     * 
     * @return HtmlPanelGrid Panel.
     */
    public HtmlPanelGrid getPanel() {
    	
        Logger.logInfo("entered method getPanel");
        
        if(isShowHidden()){
        	this.setBenefitLevelHideFlag("T");
        	this.setBenefitLineHideFlag("T");
        }
            	
    	List benefitDefinitonsList = this.getBenefitDefinitionsList();
        //To avoid unwanted calls from the getTierPanel method storing the values in a class variable
    	this.benefitDefinitionsList = benefitDefinitonsList;
        this.benefitDefinitionsListRetrieved = true;
        
		// Retaining the previous entered values, In case of application has any error message (Defect: 186432)
		// get the benefit defenitions list if list not null	
        
        
        if (validationMessages.isEmpty() && null != benefitDefinitonsList){
	        int rowNumber = 0;
	        int lineCount = 0;
	        //  EntityBenefitDefenition benefitDefinitionWrapper =
	        // this.getBenefitDefinition();
	       
	        //List benefitDefinitonsList = this.getBenefitDefinitionsList();
	        //This method Fetch the List of all possible values.
	        getPosibleValuesList(benefitDefinitonsList);
	        
	        //This method gets the values from the benefit definiton List and sets
	        // it to the level list and line list
	        getValuesFromDefinitonList(benefitDefinitonsList);
	        
	        panel = new HtmlPanelGrid();
	        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {                
	            if (!isViewMode() && !isPrintMode()) {
	                panel.setColumns(10);
	                panel.setWidth("100%");
	              //  headerPanel.setWidth("68%");
	            } else {
	                panel.setColumns(8);
	                panel.setWidth("100%");
	            }
	        } else {
	        	panel.setColumns(7);
	        	panel.setWidth("100%");
	        }
	        panel.setBorder(0);
	        panel.setCellpadding("1");
	        panel.setCellspacing("1");
	        panel.setBgcolor("#cccccc");
	
	        StringBuffer rows = new StringBuffer();
	
	        //setting values to benefit levels
	
	        int size = benefitLevelList.size();
	
	        //iterating to get the benefit levels
	        for (int i = 0; i < size; i++) {
	            rowNumber++;
	
	            //a benefit level is selected
	            BenefitLevel benefitLevelValues = (BenefitLevel) benefitLevelList
	                    .get(i);
	            //setting the field in light grey if hidden status is true
	            if(benefitLevelValues.isLevelHideStatus())
	            	rows.append("hiddenFieldLevelDisplay");
	            else
	            	rows.append("dataTableEvenRow");
	             
	            
	            //gets the benefit lines of a benefit level
	            List benefitLines = benefitLevelValues.getBenefitLines();
	
	            //setting the benefit level values to the panel grid
	            setBenefitLevelValuesToGrid(i, benefitLevelValues, benefitLines
	                    .size(), rowNumber,lineCount);
	
	            if (benefitLines.size() != 0)
	                rows.append(",");
	            //iterating to get the individual benefit lines
	            for (int j = 0; j < benefitLines.size(); j++) {
	                rowNumber++;
	                
	                //selects an individual benefit line
	                BenefitLine benefitLineValues = (BenefitLine) benefitLines
	                        .get(j);
	                
	                lineCount = lineCount + 1;
	                //setting the field in light grey if hidden status is true
	                if(benefitLineValues.isLineHideStatus())
	                	rows.append("hiddenFieldDisplay");
	                else
	                	rows.append("dataTableOddRow");
	                if (i < (size - 1))
	                    rows.append(",");
	                else if (j < (benefitLines.size() - 1))
	                    rows.append(",");
	
	                tierLineList.add(benefitLineValues);
	
	                //sets the benefit lines of a benefit level to the panle grid
	                setBenefitLineValuesToGrid(benefitLevelValues, j,
	                        benefitLineValues, i,benefitLines.size(),lineCount,benefitLineValues.isLineHideStatus());
	
	            }
	
	        }
	        this.productType = super.getProductTypeFromSession();
	        panel.setRowClasses(rows.toString());
	
	        //getTierDefList();
        }
      //added the messages to the request --Defect fix for WAS7 Migration
		addAllMessagesToRequest(informationMessageToDisplayOnPage);
        return panel;

    }

    /**
     * 
     * This method updates the benefit levels that are provided from the jsp
     * page
     */
	public String reloadPage(){
	
		return "benefitDefinitions";
	}

	/**
     * This method gets the values from the benefit definiton List and sets it
     * to the level list and line list
     * 
     * @param benefitDefinitionsList
     */
    private void getValuesFromDefinitonList(List benefitDefinitionsList) {

        Logger.logInfo("entered method getValuesFromDefinitionList");

        // TODO Auto-generated method stub
        benefitLevelList = new ArrayList();
        for (int i = 0; i < benefitDefinitionsList.size(); i++) {
            BenefitLine entityBenefitLine = (BenefitLine) benefitDefinitionsList
                    .get(i);
            int benefitDefinitionId = entityBenefitLine.getBenefitDefinitionId();            
            getSession().setAttribute("SESSION_BNFTDEFNID_PRODUCT",new Integer(benefitDefinitionId));
            //sets values to the benefitLevel List
            setValuesToBenefitLevel(entityBenefitLine, benefitLevelList);

        }
    }
    
    

    /**
     * This method sets values to the benefit level List
     * 
     * @param entityBenefitLine
     * @param benefitLevelList
     */
    private void setValuesToBenefitLevel(BenefitLine entityBenefitLine,
            List benefitLevelList) {

       // Logger.logInfo("entered method setValuesToBenefitLevel");

        // TODO Auto-generated method stub
        BenefitLevel benefitLevelBO = null;
        /*
         * //checks if the benefit level list size is not equal to zero
         * if(benefitLevelList.size()!= 0){
         * benefitLevelBO=(BenefitLevel)benefitLevelList.get(benefitLevelList.size()-1); }
         * 
         * //checks if the benefit LevelList size is 0 or if the previous
         * levelId is equal to the present levelId if(benefitLevelList.size()==0 ||
         * entityBenefitLine.getLevelSysId()!=benefitLevelBO.getLevelId()){
         */
        BenefitLevel tempBO = null;
        boolean checkFlag = false;
        // checks if the benefit level list size is not equal to zero
        if (benefitLevelList.size() != 0) {
            /*
             * benefitLevelBO = (BenefitLevel)benefitLevelList.
             * get(benefitLevelList.size() - 1);
             */
            for (int i = 0; i < benefitLevelList.size(); i++) {
                tempBO = (BenefitLevel) benefitLevelList.get(i);
                if (tempBO.getLevelId() == entityBenefitLine.getLevelSysId()) {
                    benefitLevelBO = (BenefitLevel) benefitLevelList.get(i);
                    checkFlag = true;
                }
            }
        }
        // checks if the benefit LevelList size is 0 or if the previous levelId
        // is equal to the present levelId
        if (benefitLevelList.size() == 0 ||
        //(entityBenefitLine.getLevelSysId()!= benefitLevelBO.getLevelId())){
                !checkFlag) {
            BenefitLevel entityBenefitLevel = new BenefitLevel();
            entityBenefitLevel.setLevelDesc(entityBenefitLine.getLevelDesc());
            entityBenefitLevel.setLevelId(entityBenefitLine.getLevelSysId());
            entityBenefitLevel.setTermDesc(entityBenefitLine.getTermDesc());
            /*START CARS */
            //Setting The Frequency Value
            entityBenefitLevel.setFrequencyDesc(entityBenefitLine.getFrequencyValue()+"");
            //Setting the frequency and description of the lower level's value.
            entityBenefitLevel.setLowerLevelFrequencyValue(entityBenefitLine.getLowerLevelFrequencyValue()+"");
            entityBenefitLevel.setLowerLevelDescValue(entityBenefitLine.getLowerLevelDescValue());
            /*END CARS */
            entityBenefitLevel.setQualifierDesc(entityBenefitLine
                    .getQualifierDesc());
            entityBenefitLevel.setIsTierDefExist(entityBenefitLine.getIsTierDefExist());
            entityBenefitLevel.setIsTierLevelExist(entityBenefitLine.getIsTierLevelExist());
            //Sets the status of level hide/unhide
            if(null!= entityBenefitLine.getLevelHide() && entityBenefitLine.getLevelHide().equals("F"))
            	entityBenefitLevel.setLevelHideStatus(false);
            else
            	entityBenefitLevel.setLevelHideStatus(true);
            // entityBenefitLevel.setReferenceDesc(entityBenefitLine.getReferenceDesc());

            //sets benefit lines to the benefit Levels
            entityBenefitLevel.setBenefitLines(new ArrayList());
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
     * 
     * @param entityBenefitLine
     * @return
     */
    private BenefitLine getBenefitLineBO(BenefitLine entityBenefitLine) {

        //Logger.logInfo("entered method getBenefitLineBO");

        BenefitLine entityBenefitLineToSet = new BenefitLine();
        entityBenefitLineToSet.setBenefitValue(entityBenefitLine
                .getBenefitValue());
        /*START CARS */
        //Setting The Frequency Value
        entityBenefitLineToSet.setFrequencyValue(entityBenefitLine.getFrequencyValue());
        /*END CARS */
        //Setting the non-overridden benefit Values as part of Enhancement
        entityBenefitLineToSet.setLineValue(entityBenefitLine.getLineValue());
        entityBenefitLineToSet.setProviderArrangementDesc(entityBenefitLine
                .getProviderArrangementDesc());
        entityBenefitLineToSet.setLineSysId(entityBenefitLine.getLineSysId());
        entityBenefitLineToSet.setReferenceDesc(entityBenefitLine
                .getReferenceDesc());
        entityBenefitLineToSet.setDataTypeLegend(entityBenefitLine
                .getDataTypeLegend());
        entityBenefitLineToSet.setProviderArrangementId(entityBenefitLine
                .getProviderArrangementId());
        entityBenefitLineToSet.setIsTierDefExist(entityBenefitLine.getIsTierDefExist());
		entityBenefitLineToSet.setIsTierLevelExist(entityBenefitLine.getIsTierLevelExist());
        //Sets the benefit customized sys id
        entityBenefitLineToSet.setCustomizedSysId(entityBenefitLine.getCustomizedSysId());
        //Sets the status of line hide/unhide 
        if(null!=entityBenefitLine.getLineHide() && entityBenefitLine.getLineHide().equals("F"))
        	entityBenefitLineToSet.setLineHideStatus(false);
        else
        	entityBenefitLineToSet.setLineHideStatus(true);
        if (null != entityBenefitLine.getDataTypeDesc()
                && !(entityBenefitLine.getDataTypeDesc()).equals("")) {
            entityBenefitLineToSet.setDataTypeDesc(entityBenefitLine
                    .getDataTypeDesc());
        }
        // **Change**
        entityBenefitLineToSet.setDataTypeId(entityBenefitLine.getDataTypeId());
        entityBenefitLineToSet.setBnftLineNotesExist(entityBenefitLine
                .getBnftLineNotesExist());
        // **End**
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
            int j, BenefitLine benefitLineValues, int i,int lineSize,int lineNum,boolean isHidden) {

        //Logger.logInfo("entered method setBenefitLineValuesToGrid");

        HtmlOutputText lineDesc = new HtmlOutputText();
        lineDesc.setValue(" ");
        HtmlOutputText lineTerm = new HtmlOutputText();
        lineTerm.setValue("");
        HtmlOutputText lineQualifier = new HtmlOutputText();
        lineQualifier.setValue("");
        HtmlOutputText linePVA = new HtmlOutputText();
        linePVA.setValue(benefitLineValues.getProviderArrangementId());
        HtmlOutputText lineDataType = new HtmlOutputText();
        lineDataType.setValue(benefitLineValues.getDataTypeDesc());
        HtmlSelectBooleanCheckbox lineCheckBox = new HtmlSelectBooleanCheckbox();
        //HtmlInputHidden hiddenLineCheckBox  = new HtmlInputHidden();
        //output text for view
        HtmlOutputText lineBnftValueView = new HtmlOutputText();
        
       
        /*HtmlInputHidden hiddenCustomizedSysId = new HtmlInputHidden();
        hiddenCustomizedSysId.setId("hiddenCustomizedSysId"+j+"_"+i+lineNum);
        hiddenCustomizedSysId.setValue(new Integer(benefitLineValues.getCustomizedSysId()));
        
        ValueBinding customizedSysIdBind =FacesContext.getCurrentInstance()
		.getApplication().createValueBinding(
					"#{productBenefitDetailBackingBean.customizedSysIdMap['"+benefitLineValues.getLineSysId()+"']}");
        hiddenCustomizedSysId.setValueBinding("value",customizedSysIdBind);*/
        
        this.getCustomizedSysIdMap().put(new Integer(benefitLineValues.getLineSysId()), new Integer(benefitLineValues.getCustomizedSysId()));
        
        /*HtmlInputHidden hiddenLineId = new HtmlInputHidden();
        hiddenLineId.setId("hiddenLineId" + j + "_"+ i+lineNum);
        hiddenLineId.setValue(new Integer(benefitLevelValues.getLevelId())+":"+new Integer(benefitLineValues.getLineSysId())
                + ":" + benefitLineValues.getBenefitValue() + ":"
                + benefitLineValues.getDataTypeId() + ":"
                + benefitLevelValues.getLevelDesc());
        
        // set the value to the map 
        ValueBinding lineIdValBind = FacesContext.getCurrentInstance()
                .getApplication().createValueBinding(
                        "#{productBenefitDetailBackingBean.lineIdMap["+benefitLineValues.getLineSysId()+"]}");
        
        hiddenLineId.setValueBinding("value", lineIdValBind);*/
        
        this.getLineIdMap().put(new Integer(benefitLineValues.getLineSysId()), new Integer(benefitLevelValues.getLevelId())+":"+new Integer(benefitLineValues.getLineSysId())
                + ":" + benefitLineValues.getBenefitValue() + ":"
                + benefitLineValues.getDataTypeId() + ":"
                + benefitLevelValues.getLevelDesc());
        
        HtmlInputText lineBnftValue = new HtmlInputText();
        HtmlOutputText lineBnftHideValue = new HtmlOutputText();
        
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
                seloneMenuForBnftValue.setId("lineBnftValue" + j + "_" + i+lineNum);
                seloneMenuForBnftValue.setDisabled(isHidden);
                object = (HtmlSelectOneMenu) seloneMenuForBnftValue;
                // set the value to the map
                ValueBinding valueItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productBenefitDetailBackingBean.benefitValueMap['"
                                // **Change**
                                        + benefitLineValues.getLineSysId()
                                        //**End**
                                        + "']}");
                seloneMenuForBnftValue.setValueBinding("value", valueItem);
                seloneMenuForBnftValue.setDisabled(isHidden);
            } else {
            	if(!benefitLineValues.isLineHideStatus()){
                lineBnftValue.setSize(6);
                lineBnftValue.setId("lineBnftValue" + j + "_" + i+lineNum);
                lineBnftValue.setValue(benefitLineValues.getBenefitValue());
                if (!benefitLineValues.getDataTypeDesc().equalsIgnoreCase(
                        "String")) {
                }
                lineBnftValue.setTitle("BenefitValue"
                        + benefitLineValues.getDataTypeDesc());
                ValueBinding valueItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productBenefitDetailBackingBean.benefitValueMap['"
                                // **Change**
                                        + benefitLineValues.getLineSysId()
                                        // **End**
                                        + "']}");
                lineBnftValue.setValueBinding("value", valueItem);
                lineBnftValue.setStyleClass("formInputField");
                lineBnftValue.setStyle("width:50px;");
            }else{
            	//lineBnftHideValue.setSize(10);
            	 lineBnftValue.setSize(6);
                 lineBnftValue.setId("lineBnftValue" + j + "_" + i+lineNum);
                 lineBnftValue.setValue(benefitLineValues.getBenefitValue());
            	 lineBnftValue.setDisabled(benefitLineValues.isLineHideStatus());
            	 if (!benefitLineValues.getDataTypeDesc().equalsIgnoreCase(
                 "String")) {
         }
         lineBnftValue.setTitle("BenefitValue"
                 + benefitLineValues.getDataTypeDesc());
         ValueBinding valueItem = FacesContext.getCurrentInstance()
                 .getApplication().createValueBinding(
                         "#{productBenefitDetailBackingBean.benefitValueMap['"
                         // **Change**
                                 + benefitLineValues.getLineSysId()
                                 // **End**
                                 + "']}");
         lineBnftValue.setValueBinding("value", valueItem);
         lineBnftValue.setStyleClass("formInputField");
         lineBnftValue.setStyle("width:50px;");
            }
        }
        }


        lineBnftValueView.setId("lineBnftValueView" + j + "_" + i+lineNum);
        lineBnftValueView.setValue(WPDStringUtil.spaceSeparatedString(benefitLineValues.getBenefitValue(),8));
        //lineBnftValueView.setStyleClass("formInputFieldReadOnly");
        //lineBnftValueView.setStyle("width:50px;");
        HtmlOutputText lineEmptyString = new HtmlOutputText();
        lineEmptyString.setValue(" ");

        HtmlOutputLabel lblBnftValue = new HtmlOutputLabel();
        lblBnftValue.setId("lblBnftValue"+RandomStringUtils.randomAlphanumeric(15));
        //lblBnftValue.setId("lblBnftValue" + j + "_" + i+lineNum);
        lblBnftValue.setFor("lineBnftValue" + j + "_" + i);
        if (null != sysDataType) {
            if (sysDataType.equals("DOLLAR")) {
                if (!WebConstants.MNDT_TYPE.equals(super
                        .getProductTypeFromSession())) {
                    if (!isViewMode()) {
                        //lblBnftValue.getChildren().add(lineDataType);
                    	//if(isBenefitDisplayFlag())
                         lblBnftValue.getChildren().add(lineBnftValue);
                    	//else
                    		//lblBnftValue.getChildren().add(lineBnftValueView);
                        lblBnftValue.getChildren().add(lineEmptyString);
                    } else {
                        //lblBnftValue.getChildren().add(lineDataType);
                        lblBnftValue.getChildren().add(lineBnftValueView);
                        lblBnftValue.getChildren().add(lineEmptyString);
                    }
                } else {
                    //lblBnftValue.getChildren().add(lineDataType);
                    lblBnftValue.getChildren().add(lineBnftValueView);
                    lblBnftValue.getChildren().add(lineEmptyString);
                }
            } else if (sysDataType.equals("PERCENTAGE")) {
                if (!WebConstants.MNDT_TYPE.equals(super
                        .getProductTypeFromSession())) {
                    if (!isViewMode()) {
                        lblBnftValue.getChildren().add(lineEmptyString);
                        //if(isBenefitDisplayFlag())
                        	lblBnftValue.getChildren().add(lineBnftValue);
                        //else
                        	//lblBnftValue.getChildren().add(lineBnftValueView);
                        //lblBnftValue.getChildren().add(lineDataType);
                    } else {
                        lblBnftValue.getChildren().add(lineEmptyString);
                        lblBnftValue.getChildren().add(lineBnftValueView);
                        //lblBnftValue.getChildren().add(lineDataType);

                    }
                } else {
                    lblBnftValue.getChildren().add(lineEmptyString);
                    lblBnftValue.getChildren().add(lineBnftValueView);
                    //lblBnftValue.getChildren().add(lineDataType);
                }
            } else if (sysDataType.equals("STRING")) {
                if (!WebConstants.MNDT_TYPE.equals(super
                        .getProductTypeFromSession())) {
                    if (!isViewMode()) {
                        lblBnftValue.getChildren().add(lineEmptyString);
                        //if(isBenefitDisplayFlag())
                        	lblBnftValue.getChildren().add(lineBnftValue);
                        //else
                        	//lblBnftValue.getChildren().add(lineBnftValueView);
                    } else {
                        lblBnftValue.getChildren().add(lineEmptyString);
                        lblBnftValue.getChildren().add(lineBnftValueView);
                    }
                } else {
                    lblBnftValue.getChildren().add(lineEmptyString);
                    lblBnftValue.getChildren().add(lineBnftValueView);
                }
            } else if (sysDataType.equals("BOOLEAN")) {
                if (!WebConstants.MNDT_TYPE.equals(super
                        .getProductTypeFromSession())) {
                    if (!isViewMode()) {
                        lblBnftValue.getChildren().add(lineEmptyString);
                        lblBnftValue.getChildren().add(object);
                        //lblBnftValue.getChildren().add(lineDataType);
                    } else {
                        lblBnftValue.getChildren().add(lineEmptyString);
                        lblBnftValue.getChildren().add(lineBnftValueView);
                    }
                } else {
                    lblBnftValue.getChildren().add(lineEmptyString);
                    lblBnftValue.getChildren().add(lineBnftValueView);
                }
            } else {
                if (!WebConstants.MNDT_TYPE.equals(super
                        .getProductTypeFromSession())) {
                    if (!isViewMode()) {
                        lblBnftValue.getChildren().add(lineEmptyString);
                        //if(isBenefitDisplayFlag())
                        	lblBnftValue.getChildren().add(lineBnftValue);
                       // else
                        	//lblBnftValue.getChildren().add(lineBnftValueView);
                    } else {
                        lblBnftValue.getChildren().add(lineEmptyString);
                        lblBnftValue.getChildren().add(lineBnftValueView);
                    }
                } else {
                    lblBnftValue.getChildren().add(lineEmptyString);
                    lblBnftValue.getChildren().add(lineBnftValueView);
                }
            }
            //lblBnftValue.getChildren().add(hiddenLineId);
            getProductSessionObject().setLineIdMap(this.getLineIdMap());
            //lblBnftValue.getChildren().add(hiddenCustomizedSysId);
            getProductSessionObject().setCustomizedSysIdMap(this.getCustomizedSysIdMap());
        }
        // **End**
        lineBnftValue.setDisabled(isHidden);
        HtmlOutputText lineReference = new HtmlOutputText();
        lineDesc.setValue(" ");
        lineReference.setValue(benefitLineValues.getReferenceDesc());
//        HtmlOutputText lineImage = new HtmlOutputText();
//        lineDesc.setValue(" ");

        HtmlOutputLabel lblRefAndNotes = new HtmlOutputLabel();
        lblRefAndNotes.setId("lblRefAndNotes"+RandomStringUtils.randomAlphanumeric(15));
        //lblBnftValue.setId("lblRefAndNotes" + j + "_" + i+lineNum);
        lblBnftValue.setFor("lblRefAndNotes" + j + "_" + i+lineNum);//Changed as part of stabilization release 03-02-2011

        HtmlOutputLabel lblNotesImage = new HtmlOutputLabel();
        lblNotesImage.setId("lblNotesImage"+RandomStringUtils.randomAlphanumeric(15));
       // lblNotesImage.setId("lblNotesImage" + j + "_" + i+lineNum);
        lblNotesImage.setFor("lblNotesImage" + j + "_" + i+lineNum);//Changed as part of stabilization release 03-02-2011

        HtmlGraphicImage noteImage = new HtmlGraphicImage();
        
        noteImage.setUrl("../../images/notes_exist.gif");
        noteImage.setStyle("cursor:hand;");
        noteImage.setId("noteImage" + j + "_" + i+lineNum);

        lineCheckBox.setId("checkBox"+"A"+i+"A"+lineSize+"A"+lineNum+"A"+"Line");
        lineCheckBox.setValue(Boolean.valueOf(benefitLineValues.isLineHideStatus()));
        
        //hiddenLineCheckBox.setId("hiddenLineCheckBox"+lineSize+lineNum+i);
        //hiddenLineCheckBox.setValue(Boolean.valueOf(benefitLineValues.isLineHideStatus()));
        //Binding the value of the checkbox 
        ValueBinding checkBoxValBind = FacesContext.getCurrentInstance()
			.getApplication().createValueBinding(
              "#{productBenefitDetailBackingBean.checkBoxMap[" + benefitLineValues.getLineSysId()
                      + "]}");
        lineCheckBox.setValueBinding("value", checkBoxValBind);
        
        /*ValueBinding hiddenLineCheckVal = FacesContext.getCurrentInstance()
			.getApplication().createValueBinding(
					"#{productBenefitDetailBackingBean.hiddenLineCheckMap[" + benefitLineValues.getLineSysId()
                    + "]}");*/
        //hiddenLineCheckBox.setValueBinding("value",hiddenLineCheckVal);
        this.getHiddenLineCheckMap().put(new Integer(benefitLineValues.getLineSysId()), Boolean.valueOf(benefitLineValues.isLineHideStatus()));
        lineCheckBox.setOnclick("checkTheCorrespondingBenefitLevel(this)");
// May 6 - Start
		HtmlInputHidden hiddenNotesStatus = new HtmlInputHidden();
		hiddenNotesStatus.setId("hiddenNotesStatus" + j + "_" + i+lineNum);
		hiddenNotesStatus.setValue("");
// May 6 - End        
        HtmlCommandButton notesButton = new HtmlCommandButton();
        notesButton.setValue("NotesButton");
        if (benefitLineValues.getBnftLineNotesExist().equals("Y"))
            notesButton.setImage("../../images/notes_exist.gif");
        else
            notesButton.setImage("../../images/page.gif");
        notesButton.setTitle("Note");
        notesButton.setStyle("border:0;");
        notesButton.setAlt("Notes");
        notesButton.setId("notesButton" + j + "_" + i+lineNum);
        //notesButton.setOnclick("ewpdModalWindow_ewpd('productBenefitLevelNotesOverridePopup.jsp?parentEntityType=ATTACHPRODUCT&lookUpAction=4&secondaryEntityId="
        // + benefitLineValues.getLineSysId() + "&temp="+Math.random()+"' ,
        // 'dummyDiv', 'benefitDefinitionForm:hidden1',2,1);return false;");

        // lgnd data type
        HtmlOutputText lgndDataType = new HtmlOutputText();
        lgndDataType.setValue(benefitLineValues.getDataTypeLegend());
        
        lgndDataType.setId("lgndDataType" + j + "_" + i);
        
        HtmlOutputLabel tierLabael = new HtmlOutputLabel();
        tierLabael.setId("tierLabael"+RandomStringUtils.randomAlphanumeric(15));
        //tierLabael.setId("tierLabel"+ j + "_" + i+lineNum);
        

        if (!isViewMode()) {
            /*notesButton.setOnclick("getUrl(" + benefitLineValues.getLineSysId()
                    + ");return false;");*/
            notesButton.setOnclick("getUrlAssigned(" + benefitLineValues.getLineSysId()
                    + ',' + j + ',' + i +','+lineNum+ ");return false;");
        } else {
            notesButton.setOnclick("getUrlAssigned(" + benefitLineValues.getLineSysId()
                    + "," + super.getBenefitComponentIdFromSession() + ","
                    + super.getProductKeyFromSession() + ");return false;");
        }
        notesButton.setDisabled(isHidden);
        lblRefAndNotes.getChildren().add(lineReference);
        if (!isPrintMode()) {
            lblNotesImage.getChildren().add(notesButton);
// May 6 - Start
            lblNotesImage.getChildren().add(hiddenNotesStatus);
// May 6 - End            
        }
        //lblBnftValue.getChildren().add(hiddenLineCheckBox);
        getProductSessionObject().setHiddenLineCheckMap(this.getHiddenLineCheckMap());
        panel.getChildren().add(lineDesc);
        panel.getChildren().add(lineTerm);
        panel.getChildren().add(lineQualifier);
        panel.getChildren().add(linePVA);
        panel.getChildren().add(lgndDataType);
        panel.getChildren().add(lblBnftValue);
        panel.getChildren().add(lblRefAndNotes);
        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
            panel.getChildren().add(lblNotesImage);
            if (!isViewMode()) {
                panel.getChildren().add(lineCheckBox);
                panel.getChildren().add(tierLabael);
            }
            //if(!isViewMode() && !isBenefitDisplayFlag())
            	}
        
    }


    /**
     * This method sets the benefitLevelValues to the PanelGrid
     * 
     * @param i
     * @param benefitLevelValues
     */
    private void setBenefitLevelValuesToGrid(int i,
            BenefitLevel benefitLevelValues, int lineSize, int rowNum,int lineCount) {
    	
        //Logger.logInfo("entered method setBenefitLevelValuesToGrid");    
        
        HtmlOutputText levelDesc = new HtmlOutputText();
        /*START CARS */
        //Frequency
		HtmlInputText levelDescInputText = new HtmlInputText();
		HtmlInputHidden hidOutputValDesc = new HtmlInputHidden();
        if (null != benefitLevelValues.getLevelDesc()) {
			//Fix for Contract View alignment Issue
        	String desc = null;
        	String description = benefitLevelValues.getLevelDesc().trim();
        	if(isEditMode()){
                if(description.length()>18){
                	String[] strTokenizerArr = description.split(" ");
                	for(int num=0;num<strTokenizerArr.length;num++){
                		if(strTokenizerArr[num].length()>18){
                			strTokenizerArr[num] = strTokenizerArr[num].substring(0,18)+" "+strTokenizerArr[num].substring(18);
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
        	}else if(isViewMode())
        	{
                if(description.length()>13){
                	String[] strTokenizerArr = description.split(" ");
                	for(int num=0;num<strTokenizerArr.length;num++){
                		if(strTokenizerArr[num].length()>26){
                			strTokenizerArr[num] = strTokenizerArr[num].substring(0,13)+" "+
                				strTokenizerArr[num].substring(13,26)+" "+strTokenizerArr[num].substring(26);
                		}else if(strTokenizerArr[num].length()>13){
                			strTokenizerArr[num] = strTokenizerArr[num].substring(0,13)+" "+strTokenizerArr[num].substring(13);
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
            levelDesc.setValue(description);
			levelDescInputText.setValue(benefitLevelValues.getLevelDesc().trim());
        } else {
            levelDesc.setValue(WebConstants.EMPTY_STRING);
            levelDescInputText.setValue(WebConstants.EMPTY_STRING);
        }
        levelDesc.setId("levelDesc" +i);
		levelDescInputText.setStyleClass("formInputField");
		levelDescInputText.setId("levelDescInputText" +i);
		levelDescInputText.setStyle("width:120px;display:none");
		levelDescInputText.setMaxlength(32);
		//Binding the value for description text 
        ValueBinding hidValItemDesc = FacesContext
                .getCurrentInstance()
                .getApplication()
                .createValueBinding(
                        "#{productBenefitDetailBackingBean.dataHiddenValDesc["
                                + benefitLevelValues.getLevelId() + 
                                "]}");
        levelDescInputText.setValueBinding("value", hidValItemDesc);
        //FIX
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
		HtmlInputHidden hiddenForOutputDescription = new HtmlInputHidden();
		hiddenForOutputDescription.setId("hidOutputValDesc" + i);        
		hiddenForOutputDescription.setValue(benefitLevelValues.getLevelDesc().trim());
        ValueBinding hidOutputValLevelDesc = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productBenefitDetailBackingBean.dataHiddenOutputValDesc["+ benefitLevelValues.getLevelId() +"]}");
        hiddenForOutputDescription.setValueBinding("value",hidOutputValLevelDesc);
        
        
		//FIX
        
        //START setting the Lower level description in the hidden 
		HtmlInputHidden hiddenLowerLevelDescription = new HtmlInputHidden();
		hiddenLowerLevelDescription.setId("hidLowerLevelValDesc" + i);        
		hiddenLowerLevelDescription.setValue(benefitLevelValues.getLowerLevelDescValue().trim());
        ValueBinding hidLowerLevelValDesc = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productBenefitDetailBackingBean.dataHiddenLowerLevelValDesc["+ benefitLevelValues.getLevelId() +"]}");
        hiddenLowerLevelDescription.setValueBinding("value",hidLowerLevelValDesc);
        
        this.getDataHiddenLowerLevelValDesc().put(new Integer(benefitLevelValues.getLevelId()), benefitLevelValues.getLowerLevelDescValue().trim());
        
		//END Lower level description in the hidden 
        HtmlOutputText levelTerm = new HtmlOutputText();
        levelTerm.setId("Term"+i);
        if (null != benefitLevelValues.getTermDesc()) {
            levelTerm.setValue(benefitLevelValues.getTermDesc().trim());
        } else {
            levelTerm.setValue(WebConstants.EMPTY_STRING);
        }

        /*HtmlInputHidden hiddenForTerm = new HtmlInputHidden();
        hiddenForTerm.setId("hiddenTerm" + i);        
        hiddenForTerm.setValue(benefitLevelValues.getTermDesc().trim().replaceAll(",",", "));
        ValueBinding hidValTermDesc = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productBenefitDetailBackingBean.dataHiddenValTerm["+ benefitLevelValues.getLevelId() +"]}");
        hiddenForTerm.setValueBinding("value",hidValTermDesc);  */ 
        
        this.getDataHiddenValTerm().put(new Integer(benefitLevelValues.getLevelId()), benefitLevelValues.getTermDesc().trim().replaceAll(",",", "));
        
        HtmlOutputText levelQualifier = new HtmlOutputText();
        levelQualifier.setId("Qualifier"+i);
        if (null != benefitLevelValues.getQualifierDesc()) {
            levelQualifier.setValue(benefitLevelValues.getQualifierDesc()
                    .trim());
        } else {
            levelQualifier.setValue(WebConstants.EMPTY_STRING);
        }
        
		/*HtmlInputHidden hiddenForQualifier = new HtmlInputHidden();
		hiddenForQualifier.setId("hiddenQualifier" + i);        
		if(null != benefitLevelValues.getQualifierDesc()){
			hiddenForQualifier.setValue(benefitLevelValues.getQualifierDesc().trim());
		}else{
			hiddenForQualifier.setValue("");
		}
        ValueBinding hidValQualifier = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productBenefitDetailBackingBean.dataHiddenValQualifier["+ benefitLevelValues.getLevelId() +"]}");
        hiddenForQualifier.setValueBinding("value",hidValQualifier);*/  
        
        this.getDataHiddenValQualifier().put(new Integer(benefitLevelValues.getLevelId()) , benefitLevelValues.getQualifierDesc()!= null ? 
        		benefitLevelValues.getQualifierDesc().trim() : "");
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
						"#{productBenefitDetailBackingBean.lineFreqValueMap["
								+ benefitLevelValues.getLevelId() + "]}");
		levelFrequency.setValueBinding("value", levelFreqValueBind);
		levelFrequency.setDisabled(benefitLevelValues.isLevelHideStatus());
		//Hidden Frequency is added to increase the perfomance
		HtmlInputHidden hiddenLevelFreqValue = new HtmlInputHidden();

		hiddenLevelFreqValue.setId("hiddenLevelFreqValue"+ i);
		hiddenLevelFreqValue.setValue(benefitLevelValues.getFrequencyDesc());
			ValueBinding valForhiddenLevelFreq = FacesContext
					.getCurrentInstance().getApplication().createValueBinding(
							"#{productBenefitDetailBackingBean.hiddenLineFreqValueMap["
									+ benefitLevelValues.getLevelId() + "]}");
			hiddenLevelFreqValue.setValueBinding("value",
					valForhiddenLevelFreq);
		
		//this.getHiddenLineFreqValueMap().put(new Integer(benefitLevelValues.getLevelId()),benefitLevelValues.getFrequencyDesc() );
			
		//START Hidden Lower level frequency value
		HtmlInputHidden hiddenLowerLevelFreqValue = new HtmlInputHidden();
		hiddenLowerLevelFreqValue.setId("hiddenLowerLevelFreqValue"+ i);
		hiddenLowerLevelFreqValue.setValue(benefitLevelValues.getLowerLevelFrequencyValue());
		ValueBinding valForhiddenLowerLevelFreq = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productBenefitDetailBackingBean.hiddenLowerLevelFreqValueMap["+ benefitLevelValues.getLevelId() + "]}");
		hiddenLowerLevelFreqValue.setValueBinding("value",valForhiddenLowerLevelFreq);
		
		//this.getHiddenLowerLevelFreqValueMap().put(new Integer(benefitLevelValues.getLevelId()), benefitLevelValues.getLowerLevelFrequencyValue());
		
		//END Hidden Lower level frequency value
		
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
   	        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession()) && isViewMode()) {
   	        	HtmlOutputText levelOutputFreqQual = new HtmlOutputText();
   	        	levelOutputFreqQual.setValue(benefitLevelValues.getFrequencyDesc()+" - "+levelQualifier.getValue());
   	        	levelFreqQualValue.getChildren().add(levelOutputFreqQual);
   	        }else{
   	   			levelFreqQualValue.getChildren().add(levelFrequency);
   	   			levelFreqQualValue.getChildren().add(blankValue);
   	    		levelFreqQualValue.getChildren().add(levelQualifier);
   	    		levelFreqQualValue.getChildren().add(hiddenLevelFreqValue);
   	    		//getProductSessionObject().setHiddenLineFreqValueMap(this.getHiddenLineFreqValueMap());
   	    		//levelFreqQualValue.getChildren().add(hiddenForQualifier);
   	    		getProductSessionObject().setDataHiddenValQualifier(this.getDataHiddenValQualifier());
   	    		levelFreqQualValue.getChildren().add(hiddenLowerLevelFreqValue);
   	    		//getProductSessionObject().setHiddenLowerLevelFreqValueMap(this.getHiddenLowerLevelFreqValueMap());
   	        }
   		}else{
   	    		levelFreqQualValue.getChildren().add(levelQualifier);
   	        }
   		// Setting the Qualifier Map to Session object to avoid the null poiter. 
   		if(getProductSessionObject().getDataHiddenValQualifier()==null){
   			getProductSessionObject().setDataHiddenValQualifier(this.getDataHiddenValQualifier());
   		}
		//End - Frequency Enhancement
		/*END CARS */

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
        ValueBinding levelIdValBind = FacesContext.getCurrentInstance()
                .getApplication().createValueBinding(
                        "#{productBenefitDetailBackingBean.levelIdMap[" +benefitLevelValues.getLevelId()+ "]}");
        hiddenLevelId.setValueBinding("value", levelIdValBind);
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
        deleteButton.setId("deleteButton" + i);
        // set the value to the map for checkbox   
        HtmlSelectBooleanCheckbox checkbox = new HtmlSelectBooleanCheckbox();
        checkbox.setId("checkBox"+"A"+i+"A"+lineSize+"A"+lineCount);
        	checkbox.setValue(Boolean.valueOf(benefitLevelValues.isLevelHideStatus()));
        	checkbox.setValueBinding("value",FacesContext.getCurrentInstance().getApplication().createValueBinding(
    				"#{productBenefitDetailBackingBean.levelVisibleMap[" +benefitLevelValues.getLevelId()+"]}"));
        	checkbox.setOnclick("checkTheCorrespondingBenefitLines(this);");
        	
        /*HtmlInputHidden hiddenLevelCheck = new HtmlInputHidden();
        hiddenLevelCheck.setId("hiddenLevelCheck"+lineSize+lineCount+i);
        hiddenLevelCheck.setValue(Boolean.valueOf(benefitLevelValues.isLevelHideStatus()));
        hiddenLevelCheck.setValueBinding("value",FacesContext.getCurrentInstance().getApplication().createValueBinding(
				"#{productBenefitDetailBackingBean.hiddenLevelCheckMap[" +benefitLevelValues.getLevelId()+"]}"));*/
        	
        this.getHiddenLevelCheckMap().put(new Integer(benefitLevelValues.getLevelId()),Boolean.valueOf(benefitLevelValues.isLevelHideStatus()) );
        
        HtmlOutputLabel lblImage = new HtmlOutputLabel();
        lblImage.setId("lblImage"+RandomStringUtils.randomAlphanumeric(15));
        lblImage.setFor("levelDesc" + i);
        //lblImage.setId("lblImage" + i);

        //sets the size to a hidden variable
        HtmlInputHidden hiddenLineSize = new HtmlInputHidden();
        hiddenLineSize.setId("hiddenLineSize" + i);
        hiddenLineSize.setValue(new Integer(lineSize));

        //      sets the size to a hidden variable
        HtmlInputHidden hiddenRowSize = new HtmlInputHidden();
        hiddenRowSize.setId("hiddenRowNum" + i);
        hiddenRowSize.setValue(new Integer(rowNum));

        HtmlOutputText lineImage = new HtmlOutputText();
        lineImage.setValue(" ");

        HtmlOutputText dummyText = new HtmlOutputText();
        levelPVA.setValue(" ");

        HtmlOutputLabel tierLabel = new HtmlOutputLabel();
        tierLabel.setId("tierLabel"+RandomStringUtils.randomAlphanumeric(15));
        tierLabel.setStyle("width:45px");
        
        
        HtmlCommandButton addTierButton = null;
        if("Y".equals(benefitLevelValues.getIsTierDefExist())){
        	addTierButton = new HtmlCommandButton();
	        addTierButton.setId("addTierButton"+i);
	        addTierButton.setImage("../../images/add.gif");
	        addTierButton.setAlt("Add To Tier");
	        int benefitDefinitionId = ((Integer)getSession()
		            .getAttribute("SESSION_BNFTDEFNID_PRODUCT")).intValue();
	        int benefitLevelId = benefitLevelValues.getLevelId();
	        String params = "temp="+Math.random()+
	        				"&entitySysId="+getProductKeyFromSession()+
	        				"&benefitComponentSysId="+getBenefitComponentIdFromSession()+
							"&benefitDefinitionSysId="+benefitDefinitionId+
							"&benefitDefinitionLevelId="+benefitLevelId+
							"&entityType=Product";
	        addTierButton.setOnmousedown("return validateErr();");
	        addTierButton.setOnclick("var retVal = ewpdModalWindow_ewpd('../popups/benefitTierDefinitionsPopup.jsp'+getUrl()+'?"+params+"'," +
	        		"'benefitDefinitionForm:dummy','benefitDefinitionForm:dummy',2,2); if( (retVal!=null) && (retVal != false) ){ submitLink('benefitDefinitionForm:doRefreshLink');return false; } else {return false; }");
	        tierLabel.getChildren().add(addTierButton);
	        HtmlOutputLabel spacelabel = null;
	        //spacelabel.setId("spacelabel"+RandomStringUtils.randomAlphanumeric(15));
	        HtmlCommandButton deleteTierButton = null;
	        if("Y".equals(benefitLevelValues.getIsTierLevelExist())){
	        	spacelabel =  new HtmlOutputLabel();
	        	spacelabel.setId("spacelabel"+RandomStringUtils.randomAlphanumeric(15));
		        //spacelabel.setId("spacelabel" + i);
		        spacelabel.setStyle("width:5px;");
		        
		        deleteTierButton = new HtmlCommandButton();
		        deleteTierButton.setId("deleteTierButton"+i);
		        deleteTierButton.setImage("../../images/delete.png");
		        deleteTierButton.setAlt("Delete From Tier");
		        deleteTierButton.setOnclick("deleteFromTier("+ benefitLevelValues.getLevelId() +"); return false;");
		        tierLabel.getChildren().add(spacelabel);
		        tierLabel.getChildren().add(deleteTierButton);
	        }
	        
	        
        }
        //checks if it is a view mode
        if (!isViewMode()) {
        	//if(isBenefitDisplayFlag())
        		lblImage.getChildren().add(checkbox);
		//else
        		//lblImage.getChildren().add(levelPVA);
        	
            lblImage.getChildren().add(hiddenLevelId);
            lblImage.getChildren().add(hiddenLineSize);
            lblImage.getChildren().add(hiddenRowSize);
            //lblImage.getChildren().add(hiddenLevelCheck);
            getProductSessionObject().setHiddenLevelCheckMap(this.getHiddenLevelCheckMap());
        }
      //  levelBnftValue.getChildren().add(hiddenLevelCheck);

        /*START CARS */
        //Frequency
		HtmlOutputLabel lblDesc = new HtmlOutputLabel();
		lblDesc.setId("lblDesc"+RandomStringUtils.randomAlphanumeric(15));
		lblDesc.getChildren().add(levelDesc);
		if(isEditMode() && !isViewMode() && !isPrintMode())
			lblDesc.getChildren().add(levelDescInputText);
        lblDesc.getChildren().add(hiddenForOutputDescription);
        lblDesc.getChildren().add(hiddenLowerLevelDescription);
        //getProductSessionObject().setDataHiddenLowerLevelValDesc(this.getDataHiddenLowerLevelValDesc());
        
		HtmlOutputLabel lblTerm = new HtmlOutputLabel();
		lblTerm.setId("lblTerm"+RandomStringUtils.randomAlphanumeric(15));
		lblTerm.getChildren().add(levelTerm);
		//lblTerm.getChildren().add(hiddenForTerm);
        getProductSessionObject().setDataHiddenValTerm(this.dataHiddenValTerm);
        panel.getChildren().add(lblDesc);
        panel.getChildren().add(lblTerm);
        panel.getChildren().add(levelFreqQualValue);
        /*END CARS */
        
        panel.getChildren().add(levelPVA);
        panel.getChildren().add(dummyText);
        panel.getChildren().add(levelBnftValue);
    //    panel.getChildren().add(hiddenLevelCheck);
        panel.getChildren().add(levelReference);
        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
        		panel.getChildren().add(lineImage);
            if (!isViewMode()) {
                panel.getChildren().add(lblImage);
                panel.getChildren().add(tierLabel);
            }
        }
        
    }

    public String refresh(){
    	return "productBenefitDefinitionUpdate";
    }

    /**
     * This method saves the benefit definitions
     * 
     * @return
     */

    //gets the value in the test box
    public String save() {
    	
    	this.dataHiddenValTerm = getProductSessionObject().getDataHiddenValTerm();
    	this.dataHiddenValQualifier = getProductSessionObject().getDataHiddenValQualifier();
    	this.hiddenLevelCheckMap = getProductSessionObject().getHiddenLevelCheckMap();
    	this.hiddenLineCheckMap = getProductSessionObject().getHiddenLineCheckMap();
    	this.lineIdMap = getProductSessionObject().getLineIdMap();
    	this.customizedSysIdMap = getProductSessionObject().getCustomizedSysIdMap();
    	
    	getRequest().setAttribute("RETAIN_Value", "");
    	boolean hiddenFlagChanged = false;
    	  List changedBenefitLevelIds = new ArrayList();
    	  Logger.logInfo("entered method save");
          boolean validationFlag = true;
          List benefitLineVOs = new ArrayList();
          Boolean levelHideFlag =  Boolean.valueOf(false);
	      Set keys = benefitValueMap.keySet();
		  Iterator valueIter = keys.iterator();
		  Set sysIdKeys = customizedSysIdMap.keySet();
		  Iterator idIterator = sysIdKeys.iterator();
		  String levelIdFromLine = null;
	      Object lineIdKey = null;
	  	  //boolean flag = true;//commented as part of stabilization release
	      List messages = new ArrayList();
	      boolean benefitLevelHideCheckFlag = false;
	      Boolean oldLevelChecked = Boolean.valueOf(false);
	      int noOfbenefitLevels = 0;
	      boolean isTierLineValueModified = false;
	      boolean isCriteriaValueModified = false;
	      boolean isValueModified = false;
	      boolean isLineHide = false;
	      
	      /*START CARS */
	      List freqLineVOs = new ArrayList();
          List levelDescLineVOs = new ArrayList();
		  Set levelFreqValKeySet = this.getLineFreqValueMap().keySet();
		  Set levelDescValKeySet = this.getDataHiddenValDesc().keySet();
		  boolean levelIdChecked = false;
		  boolean levelDescChecked = false;
		  String truncatedLvlDesc = null;
          lvlDescMessages = new ArrayList();
          /*END CARS */
		 
		  if (null != this.getLevelVisibleMap() && null!=this.getLineIdMap() && null!=this.getCustomizedSysIdMap()) {
              // get the key set from the map
              Set levelIdMapKeySet = this.getLevelVisibleMap().keySet();
              // iterate the keyset and match the corresponding key and take
              // the selected levelId
              Iterator levelIdMapKeySetIterator = levelIdMapKeySet.iterator();
              
              while (levelIdMapKeySetIterator.hasNext()) {
              	
                  Object key = levelIdMapKeySetIterator.next();
                  
                  String levelId = key.toString();

                  /*START CARS */
				  levelIdChecked = false;
				  levelDescChecked = false;
				  /*END CARS */				  
                  
                  levelHideFlag = (Boolean)this.getLevelVisibleMap().get(((key)));
                  oldLevelChecked = Boolean.valueOf((String)this.getHiddenLevelCheckMap().get(key));
                  //checking whether the the hide flag status is changed 
                  // for adminOptions validation
                  if(!levelHideFlag.equals(oldLevelChecked)){
                  		hiddenFlagChanged = true;
                  		if(!changedBenefitLevelIds.contains(levelId))
                  			changedBenefitLevelIds.add(levelId);
                  }
                  
  		          Set lineIdKeySet = this.getLineIdMap().keySet();
  		        	// lineId Iterator
  		          Iterator lineIdIterator = lineIdKeySet.iterator();
  		        

  		        // iterate the lineIdKeySet
  		          while (lineIdIterator.hasNext() && valueIter.hasNext() && idIterator.hasNext()) {
  		          		
  		          		boolean flag = true;//As part of stabilization release Jan 31 2011
	  	  	            // get the lineIdKey
	  		        	lineIdKey = (Integer)lineIdIterator.next();
	  		        	String lineId = lineIdKey.toString();
	                  	
	                  	String benefitValueLineId = lineId;
	                	if(null != this.getLineIdMap() && null != this.getLineIdMap().get(new Integer(lineId)))
	                		benefitValueLineId = (this.getLineIdMap().get(new Integer(lineId))).toString();
			        		StringTokenizer tokenizerForMap = new StringTokenizer(benefitValueLineId,":");
			        		//while(tokenizerForMap.hasMoreTokens()){
			        		if(tokenizerForMap.hasMoreTokens()){
				        			levelIdFromLine = tokenizerForMap.nextToken();
		
		  	  		         /**
		  	  		          * Checks if the level id from levelIdMap and level id from
		  	  		          * lineIdMap are equal.If equal sets the corresponding values
		  	  		          * to the VO
		  	  		          */
		  		        	if(levelIdFromLine.equals(levelId)){
		  		        		 Boolean lineChecked = (Boolean) checkBoxMap.get(new Long(lineId));
		  		        		 Boolean oldLineChecked =((Boolean)hiddenLineCheckMap.get(new Integer(lineId)));
		  		        		 //checking whether the the hide flag status is changed 
		  		                  // for adminOptions validation
		  		        		if(!isShowHidden()){ 	
			  		        		if(!lineChecked.equals(oldLineChecked)){
			  		        			hiddenFlagChanged = true;
		  		                  		if(!changedBenefitLevelIds.contains(levelId))
		  		                  			changedBenefitLevelIds.add(levelId);
			  		        		}
		  		        		}else{
		  		        			if(!lineChecked.equals(oldLineChecked)){
			  		        			hiddenFlagChanged = true;
		  		                  		if(!changedBenefitLevelIds.contains(levelId))
		  		                  			changedBenefitLevelIds.add(levelId);
			  		        		}
		  		        		}
		  		        		 
	 	  		        		 ProductBenefitCustomizedVO benefitLineValues = new ProductBenefitCustomizedVO();
		   		        		 String value = (String) benefitValueMap.get(lineId);
		  		        		 String customizedSysId = customizedSysIdMap.get(new Integer(lineId))!= null ? customizedSysIdMap.get(new Integer(lineId)).toString() : "";
		  		        		 if(null!=customizedSysId)
		  		        		 	benefitLineValues.setBenefitCustomizedSysId(Integer.parseInt(customizedSysId));
		  		        		 /**
		  		        		  * If the level is checked,sets the hide status true to the level
		  		        		  * and all the lines 
		  		        		  */
		  		        		 if(levelHideFlag.booleanValue()){
		  		  	          		benefitLineValues.setBenefitLevelHideFlag("T");
		  		  	          		benefitLineValues.setBenefitLineHideFlag("T");
		  		  	          		noOfbenefitLevels++;
		  		  	          	//	benefitFlag=true;
		  		        		 }
		  		        		 else{
		  		        		 	benefitLineValues.setBenefitLevelHideFlag("F");
		  		        		 //	benefitFlag=false;
		  		        		 	noOfbenefitLevels++;
		  		        		 }
		  		        		 //If the line alone is checked sets hide status only for line
			  		        	 if(!levelHideFlag.booleanValue()){
			  		  	  	         if(null!=lineChecked && lineChecked.booleanValue())
			  		  	  	            	benefitLineValues.setBenefitLineHideFlag("T");
			  		  	  	            else
			  		  	  	            	benefitLineValues.setBenefitLineHideFlag("F");
			  		  	         }
		  		        		if(!oldLineChecked.equals(lineChecked))
		  		        			benefitLevelHideCheckFlag = true;
		  		        		else
		  		        			benefitLevelHideCheckFlag = false;
			  		        	if (null != lineId){
				  	                benefitLineValues.setBenefitLineId((new Integer(lineId))
				  	                        .intValue());
				  	                if(null!=value && !"".equals(value)){
				  	                	benefitLineValues.setOverridedValue(value.trim());
				  	                }
			  		        	}
			  		        	Object levelVal = this.getLineIdMap().get(new Integer(lineId));
			  		        	String levelLineVal = levelVal.toString();
			  		        	String appendedBnftVal = null;
			  		        	String appendedLineVal = null;
			  		        	String appendedDataTypeId = null;
			  		        	String appendedLevelDesc = null;
			  		        	String newBenefitValue = null;
			  		        		if (null != levelLineVal && !levelLineVal.equals("")) {
				  		        		StringTokenizer tokenizer = new StringTokenizer(levelLineVal,
				  	                        ":");
				  		        		int i = 0;
				  		        		while (tokenizer.hasMoreTokens()) {
				  		        			String tokens = tokenizer.nextToken();
				  		        			if (i == 1) {
				  		        				appendedLineVal = tokens;
				  		        			} else if (i == 2) {
				  		        				appendedBnftVal = tokens;
				  		        			} else if (i == 3) {
				  		        				appendedDataTypeId = tokens;
				  		        			} else if (i == 4) {
				  		        				appendedLevelDesc = tokens;
				  		        			}
				  		        			i++;
				  	                
				  		        		}
				  		        	
				  		        /*START CARS */
				  		        		
										// levelFreqVal Iterator
										Iterator levelFreqValIterator = levelFreqValKeySet
												.iterator();
										// level Frequency Value
										while (levelFreqValIterator.hasNext()) {
											// get the levelIdFreqValKey
											Object levelIdFreqValKey = levelFreqValIterator
													.next();
											// compare the lineId key with the lineLevelFreqValKey
											if (levelId.equals(levelIdFreqValKey.toString())) {
												// check whether the override value is Valid.
												if (null != this.getLineFreqValueMap()
														.get(levelIdFreqValKey)) {
													//Frequency Validation should be checked once per level
													if(!levelIdChecked){
														if (!isValidFrequency(appendedDataTypeId, this
																.getLineFreqValueMap().get(levelIdFreqValKey).toString(), appendedLevelDesc, validationFlag)) {
															validationFlag = false;
														}
														levelIdChecked = true;
													}
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
															//updatedBenefitLine.setFrequencyValue(freqVal);
															benefitLineValues.setOverridedFreqValue(freqVal+"");
															//Added to increase the performance
															//Checking if frequency value is changed then only this would persist.
															Object oldLevelFreqVal = hiddenLineFreqValueMap.get(levelIdFreqValKey);
															if (null != oldLevelFreqVal
																	&& null != levelIdFreqValKey.toString()
																	&& !((this.getLineFreqValueMap()
																			.get(levelIdFreqValKey).toString())
																			.equals(oldLevelFreqVal.toString()))) {
																//updatedBenefitLine.setModified(true);
																benefitLineValues.setBenefitUpdateFlag(true);
																isFrequencyChanged = true;
															}
														}											
													}
												}
												break;
											}
										}
										//End -- Saving the Frequency
										//Description Change Start
										boolean isDescriptionTruncated = false;
				    		        	int noOfTokens;
										Object descriptionValue;
										Object oldFrequencyValue;
										Object newFrequencyValue;
										Object termValue;
										Object qualifierValue;
										Object lowerLevelFrequencyValue;
										Object lowerLevelDescriptionValue;
										//description value binded with input text
										descriptionValue = dataHiddenValDesc.get(new Long(levelId.toString().trim()));
										//frequency value binded with input hidden
										oldFrequencyValue = hiddenLineFreqValueMap.get(new Long(levelId.toString().trim()));
										//frequency value binded with input text
										newFrequencyValue = lineFreqValueMap.get(new Long(levelId.toString().trim()));
										//term value binded with input text
										termValue = dataHiddenValTerm.get(new Integer(levelId.toString().trim()));
										//qualifier value binded with input text
										qualifierValue = dataHiddenValQualifier.get(new Integer(levelId.toString().trim()));	
										lowerLevelFrequencyValue = hiddenLowerLevelFreqValueMap.get(new Long(levelId.toString().trim()));
										lowerLevelDescriptionValue = dataHiddenLowerLevelValDesc.get(new Long(levelId.toString().trim()));							
										//Checking null all the object(description, term, qualifier, frequency)
										if((!StringUtil.isEmpty(descriptionValue)) && (!StringUtil.isEmpty(qualifierValue)) && (!StringUtil.isEmpty(termValue)) && (!StringUtil.isEmpty(oldFrequencyValue)) && (!StringUtil.isEmpty(newFrequencyValue)) && (WPDStringUtil.isNumber(newFrequencyValue.toString().trim()))){								
											String description = descriptionValue.toString().toUpperCase().trim();
											String term = termValue.toString().trim();
											String qualifier = qualifierValue.toString().trim();
											String frequency = oldFrequencyValue.toString().trim();
											String changeDesc;
//											Fix aggregate qualifier start
											term = WPDStringUtil.commaSeparatedString(term);
											qualifier = WPDStringUtil.commaSeparatedString(qualifier);
											/*if(null != qualifier){
												StringTokenizer benefitQualifiers = new StringTokenizer(
														qualifier, BusinessConstants.COMMA);
												 noOfTokens = benefitQualifiers.countTokens();
												 boolean tokenFlag = true;
												 if(noOfTokens > 1){
												 	 for (int j = 0; j < noOfTokens; j++) {
								                        if (benefitQualifiers.hasMoreTokens()) {
								                            String benefitQualifer = benefitQualifiers.nextToken();
								                            if(tokenFlag == true){
								                            	qualifier = benefitQualifer;
							                            		//Flag is set false to restrict the entry for the second time
							                            		tokenFlag = false;
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
												 boolean tokenFlag = true;
												 if(noOfTokens > 1){
												 	 for (int j = 0; j < noOfTokens; j++) {
								                        if (benefitTerms.hasMoreTokens()) {
								                            String benefitTerm = benefitTerms.nextToken();
								                            if(tokenFlag == true){
								                            	term = benefitTerm;
							                            		//Flag is set false to restrict the entry for the second time
							                            		tokenFlag = false;
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
							                	if(!(frequency.equals(newFrequencyValue.toString().trim()))){
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
								                		isDescriptionTruncated = true;
								                	}
								                	benefitLineValues.setOverridedLvlDescValue(changeDesc);
							                	}else{
							                		description = descriptionValue.toString().toUpperCase().trim(); 
								                	//Keeping only 32 characters, while level description is more than 32 characters
								                	if(null !=description && description.length()>32){
								                		description = description.substring(0,32).trim();
								                		isDescriptionTruncated = true;
								                	}
								                	benefitLineValues.setOverridedLvlDescValue(description);
							                	}
							                }else{
							                	if(lowerLevelFrequencyValue.toString().toUpperCase().trim().equals(newFrequencyValue.toString().toUpperCase().trim())){
							                		//benefitLineValues.setOverridedFreqValue(lowerLevelFrequencyValue.toString());
							                		String lvlDescriptionValue = lowerLevelDescriptionValue.toString().toUpperCase().trim();
													if(lvlDescriptionValue.length() > 32){
														lvlDescriptionValue = lvlDescriptionValue.substring(0,32).trim();
														benefitLineValues.setOverridedLvlDescValue(lvlDescriptionValue);
														isDescriptionTruncated = true;
													}
													benefitLineValues.setOverridedLvlDescValue(lvlDescriptionValue);
							                	}else{
								                	description = descriptionValue.toString().toUpperCase().trim(); 
								                	//Keeping only 32 characters, while level description is more than 32 characters
								                	if(null !=description && description.length()>32){
								                		description = description.substring(0,32).trim();
								                		isDescriptionTruncated = true;
								                	}
								                	benefitLineValues.setOverridedLvlDescValue(description);
													benefitLineValues.setBenefitUpdateFlag(true);
							                	}
							                }
										}else{
											String lvlDescriptionValue = descriptionValue.toString().toUpperCase().trim();
						                	//Keeping only 32 characters, while level description is more than 32 characters
						                	if(descriptionValue.toString().length()>32){
						                		lvlDescriptionValue = lvlDescriptionValue.substring(0,32).trim();
						                		isDescriptionTruncated = true;
						                	}
											benefitLineValues.setOverridedLvlDescValue(lvlDescriptionValue);						                	
										}
										if(null != descriptionValue && !WebConstants.EMPTY_STRING.equalsIgnoreCase(descriptionValue.toString())){											
											if(!levelDescChecked && isDescriptionTruncated){
												truncatedLvlDesc = benefitLineValues.getOverridedLvlDescValue();
												InformationalMessage informationalMessage = new InformationalMessage(WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_LENGTH);
												informationalMessage.setParameters(new String[] { truncatedLvlDesc });
												lvlDescMessages.add(informationalMessage);
												addAllMessagesToRequest(lvlDescMessages);
												levelDescChecked = true;
											}
										}else if(validationFlag){
											if(!levelDescChecked){
												validationMessages.add(new ErrorMessage(
								                        WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_REQUIRED));
												validationFlag = false;
												levelDescChecked = true;
											}
										}
										//Description Change End					  		        		
										//Sets the flag true if the frequency value chnaged
										if((null != oldFrequencyValue) && (null != newFrequencyValue)){
											if(!(oldFrequencyValue.equals(newFrequencyValue))){
												isLineValueModified=true;
											}
										}
				  		        		
				  		        		
/*				  		  			Set levelFreqValKeySet = this.getLineFreqValueMap().keySet();
									Iterator levelFreqValIterator = levelFreqValKeySet.iterator();
				  		  			
				  		        	newFreqValue = (String) lineFreqValueMap.get(Long.parseLong(levelId));
				  		        	  newlvlDescValue = (String) dataHiddenValDesc.get(levelId);
				  		        	  appendedLevelDesc = newlvlDescValue;
				  		        		
						            // check whether the override value is Valid.
							            if (!isValidFrequency(appendedDataTypeId, newFreqValue, appendedLevelDesc)) {
							                validationFlag = false;
					  	                }
*/
										
										
					  			/*END CARS */       
					  				  
						  	          if (null != benefitValueMap && null != appendedLineVal
							                    && null != benefitValueMap.get(appendedLineVal))
						  	          		newBenefitValue = (benefitValueMap.get(appendedLineVal))
							                        .toString();
							            // check whether the override value is Valid.
								            if (!isValid(appendedDataTypeId, newBenefitValue, appendedLevelDesc)) {
								                validationFlag = false;
						  	                }
					  	            }
			  		        	
			  		        		if((null!=newBenefitValue &&  !"".equals(newBenefitValue)) || (!appendedBnftVal.equals("null") && !"".equals(appendedBnftVal)))
			  		        			flag = newBenefitValue.equalsIgnoreCase(appendedBnftVal);
			  		        		/*else
			  		        			flag = true;*/
			  		        		
					  		        if(!flag || benefitLevelHideCheckFlag)
					  		        	benefitLineValues.setBenefitUpdateFlag(true);
					  		        	benefitLineVOs.add(benefitLineValues);
						  		    	// to check whether line value modified or hided
					  		        	if(!flag){
					  		        		isTierLineValueModified = true;
					  		        	}
					  		        	if(benefitLevelHideCheckFlag && !isLineHide)
					  		        		isLineHide = true;
		  		        	}
	  		        }
  		        }
              }
              		if(!checkAvailableBenefits(benefitLineVOs,noOfbenefitLevels)){
		        		validationMessages.add(new ErrorMessage(WebConstants.MANDATORY_BENEFIT_LINE_VISIBLE_REQUIRED));
		        		validationFlag = false;
		        	}
              }
      
          
          //if Tiering is done set values and then validate
          if(isTierd()){
        	setTier();
            if (!validateTiering())
            	validationFlag = false;
            if(!validateTieredLinesValues())
            	validationFlag = false;
            if(modifiedTierLineIdList.size()>0)
            	isTierLineValueModified = true;
            if(modifiedTierCriteriaList.size()>0)
            	isCriteriaValueModified = true;
          } 
          
        if (validationFlag) {

            //the request is made
            SaveProductBenefitDefinitionRequest saveProductBenefitDefinitionRequest = (SaveProductBenefitDefinitionRequest) this
                    .getServiceRequest(ServiceManager.SAVE_PRODUCT_BENEFIT_DEFINITION_REQUEST);
            SaveProductBenefitDefinitionResponse saveProductBenefitDefinitionResponse = null;

            //Sets the benefit hide status true if all the levels are hidden
            if(benefitFlag)
            	saveProductBenefitDefinitionRequest.setBenefitHideFlag("T");
            else
            	saveProductBenefitDefinitionRequest.setBenefitHideFlag("F");
         
            saveProductBenefitDefinitionRequest.setBenefitId(getProductSessionObject().getBenefitId());
            saveProductBenefitDefinitionRequest
                    .setBenefitComponentId(getBenefitComponentIdFromSession());
            saveProductBenefitDefinitionRequest.setBenefitComponentName((String)getSession().getAttribute("BENEFIT_COMP_NAME"));
            

            //the set of LineVOs is set to the request
            if(null!=benefitLineVOs && !benefitLineVOs.isEmpty()){
            	saveProductBenefitDefinitionRequest
                    	.setBenefitLinesList(benefitLineVOs);
            if(hiddenFlagChanged){
            	saveProductBenefitDefinitionRequest.setHiddenFlagChanged(true);
            	saveProductBenefitDefinitionRequest.setProductId(getProductSessionObject().getProductKeyObject().getProductId());
            	saveProductBenefitDefinitionRequest.setChangedIds(changedBenefitLevelIds);            	
            }
 
            	saveProductBenefitDefinitionRequest.setBenefitTierDefinitionList(tierDefList);
            	saveProductBenefitDefinitionRequest.setBenefitTierLevelList(lvlLineList);
            	
            	Map valuesFromInput = getLinesWithLevelValues(lvlLineList,true);
            	Map valuesFromSession = (Map) super.getSession().getAttribute("lineWithLevelMapInSession");
            	
            	Map tierCritiriaValFromInput = getTierCriteriaValues(tierDefList);
            	Map tierCritiriaValFromSession = (Map) super.getSession().getAttribute("critiriaMapInSession");
            	
            	saveProductBenefitDefinitionRequest.setMapWithNewValues(valuesFromInput);
            	saveProductBenefitDefinitionRequest.setMpaWithOldValues(valuesFromSession);
            	
            	saveProductBenefitDefinitionRequest.setMapWithNewCriteriaValues(tierCritiriaValFromInput);
            	saveProductBenefitDefinitionRequest.setMapWithOldCriteriaValues(tierCritiriaValFromSession);
            	
            	super.getSession().removeAttribute("lineWithLevelMapInSession");
            	super.getSession().removeAttribute("critiriaMapInSession");
            	
            	super.removeTierDefinitionListFromSession();
            	super.removeTierLevalListFromSession();
            	super.removeTierDefWithPsvlListFromSession();
            	getSession().removeAttribute(WebConstants.TIER_CRITERIA_PSBL_VALUE_LIST);

            	
            //gets the response
            	saveProductBenefitDefinitionResponse = (SaveProductBenefitDefinitionResponse) executeService(saveProductBenefitDefinitionRequest);
            }
            if(null!=saveProductBenefitDefinitionResponse){
            	messages = saveProductBenefitDefinitionResponse.getMessages();         	
            	
            	
            	if(null != messages && !messages.isEmpty()){
            		InformationalMessage message = (InformationalMessage) messages.get(0);
            		if(null != message && null != message.getId()){
            			if(message.getId().trim().equals(BusinessConstants.MSG_PRDCT_BEN_DEFN_UPDATED)){
            				messages = new ArrayList();
            				if(isLineHide){
            					messages.add(new InformationalMessage("benefit.line.hide.unhide"));
            				}
            				if(isCriteriaValueModified){
            					messages.add(new InformationalMessage("benefit.criteria.value.modified"));            					
            				}
            				if(isFrequencyChanged){
            					messages.add(new InformationalMessage("benefit.level.save.success"));
            					isFrequencyChanged = false;
            				}else{
                				if(isTierLineValueModified){
                					messages.add(new InformationalMessage("benefit.line.value.modified"));
                				}
            				}
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
            			}
            		}
            	}
            }
        } else {
            addAllMessagesToRequest(validationMessages);
            return WebConstants.EMPTY_STRING;
        }
        // for loading the associated benefits page from benefit definition page 
	    // if all the levels are hidden
        List benefitDefinitionList = this.getBenefitDefinitionsList();
        addAllMessagesToRequest(messages);  
        
        if(null!=benefitDefinitionList && !benefitDefinitionList.isEmpty())
        	return "productBenefitDefinitionUpdate";
        else
        	return "productAssociatedBenefits";
    }
	/**
	 * Validating Frequency Value
	 * 
	 * @param dataTypeId
	 * @param value
	 * @param levelDesc
	 * @return
	 */
    private boolean isValidFrequency(String dataTypeId, String value,String levelDesc, boolean validationFlag){
		boolean isValid = true;
		boolean isNumber = true;
		int dataType = 0;
		//String sysDataType = null;
		String dataTypeName = null;

		if (null != dataTypeId && !"".equals(dataTypeId)) {
			dataType = Integer.parseInt(dataTypeId);
		}
		List universeDataTypeList = WPDStringUtil.getUniverseDataTypeList();
		if (dataType != 0) {
			DataTypeLocateResult dataTypeDetails = null;
			dataTypeDetails = WPDStringUtil.getDataTypeDetails(
					universeDataTypeList, dataType);
			if (null != dataTypeDetails) {
				//sysDataType = dataTypeDetails.getDataTypeDesc().toUpperCase()
				//		.trim();
				dataTypeName = dataTypeDetails.getDataTypeName().toUpperCase()
						.trim();
			}
		}
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
    	tierDefList = getProductSessionObject().getBenefitTierDefinitionList();
    	boolean ivalidRangeExist;
    	List overLappingList = new ArrayList();
    	List tmpOverLappingList;
    	boolean validationSucess=true;
    	
    	Iterator defIterator = tierDefList.iterator();
    	
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
    
    
   
    private void setTier(){ 
    	modifiedTierLineIdList = new ArrayList();
    	modifiedTierCriteriaList = new ArrayList();
    	tierDefList = getProductSessionObject().getBenefitTierDefinitionList();
    	lvlLineList = getProductSessionObject().getBenefitTierLevelList();
    	
    	
    	for (Iterator defIterator = tierDefList.iterator(); defIterator.hasNext();) {
    		BenefitTierDefinition tierDefinition = (BenefitTierDefinition) defIterator.next();
    		for (Iterator tierBoIterator = tierDefinition.getBenefitTiers().iterator(); tierBoIterator.hasNext();) {
				BenefitTier benefitTier = (BenefitTier) tierBoIterator.next();
				for (Iterator criteriaIterator = benefitTier.getBenefitTierCriteriaList().iterator(); criteriaIterator
						.hasNext();) {
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
    	
    	for (Iterator benLevelItr = lvlLineList.iterator(); benLevelItr.hasNext();) {
			BenefitLevel benefitLevel = (BenefitLevel) benLevelItr.next();
			for (Iterator benLineItr = benefitLevel.getBenefitLines().iterator(); benLineItr.hasNext();) {
				BenefitLine benefitLine = (BenefitLine) benLineItr.next();
				String keyString = formKeyforMap(benefitLevel.getLevelId(), benefitLine.getLineSysId(), benefitLevel.getTierSysId());
				String newLineVal = (String)getTierLineValueMap().get(keyString)!=null?(String)getTierLineValueMap().get(keyString):"";
				String oldLineVal = benefitLine.getBenefitValue()!= null ? benefitLine.getBenefitValue():"";
					if(!(oldLineVal.equalsIgnoreCase(newLineVal))){
						modifiedTierLineIdList.add(oldLineVal);
					}
				benefitLine.setLineValue(newLineVal);
			}
		} 	
    }
    /**
     * This method is for level validations
     * @param benefitLevel
     * @return
     */
    private BenefitLevel validateTieredLevelValues(BenefitLevel benefitLevel,String tierName){
    	ErrorMessage errorMessage = null;
    	boolean validationFlag = true;
        List tempvalidationList = new ArrayList();
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
	        				//benefitLevel.setModified(true);
	        			}else if(isErrorTierFlag){//set the error message for zero can not be a value
	        				validationFlag = false;
	        				errorMessage = new ErrorMessage(WebConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_CORRECT);
	        				//errorMessage.setParameters(new String[] {oldDescOutputTxtTier.get(concatedKey).toString().trim()+" "+tierName});
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
    	boolean isDescriptionTrunkated = false;
    	String truncatedLvlDesc = null;
    	Object newDescriptionValue;
    	Object oldDescriptionValue;
		Object oldFrequencyValue;
		Object newFrequencyValue;
		Object termValue;
		Object qualifierValue;
		Object lowerLevelFrequencyValue;
		Object lowerLevelDescriptionValue;
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
		lowerLevelFrequencyValue = hiddenTierLowerLevelFreqValueMap.get(concatedKey);
		lowerLevelDescriptionValue = dataHiddenTierLowerLevelValDesc.get(concatedKey);
		if(!StringUtil.isEmpty(oldDescriptionValue)){
			if(!StringUtil.isEmpty(newDescriptionValue)){
					if(oldDescriptionValue.toString().trim().toUpperCase().equals(newDescriptionValue.toString().trim().toUpperCase())){
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
			                }
							if(changeDesc.length() > 32){
								changeDesc = changeDesc.substring(0,32).trim();
							}
							if(description.length() > 32){
								description = description.substring(0,32).trim();
							}
							//Compares the old description and new description
			                if(description.equalsIgnoreCase(changeDesc)){
			                	if(!(frequencyVal.equalsIgnoreCase(newFrequencyValue.toString().trim()))){
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
					                	//Keeping only 32 characters, while level description is more than 32 characters				                	
					                	changeDesc = changeDesc.substring(0,32);
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
			                	benefitLevel.setLevelDesc(changeDesc);			                	
			                }else{
			                	if(lowerLevelFrequencyValue.toString().toUpperCase().trim().equals(newFrequencyValue.toString().toUpperCase().trim())){
			                		//benefitLevel.setFrequencyId(new Integer(lowerLevelFrequencyValue.toString()).intValue());
			                		String lvlDescriptionValue = lowerLevelDescriptionValue.toString().toUpperCase().trim();
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
				                		description = description.substring(0,32);
				                		isDescriptionTrunkated = true;
				                	}
				                	benefitLevel.setLevelDesc(description);
			                	}
			                }	
						}else{
							String lvlDescriptionValue = oldDescriptionValue.toString().toUpperCase().trim();
		                	//Keeping only 32 characters, while level description is more than 32 characters
		                	if(null !=oldDescriptionValue && oldDescriptionValue.toString().length()>32){
								lvlDescriptionValue = lvlDescriptionValue.substring(0,32).trim();
								isDescriptionTrunkated = true;
		                	}
							benefitLevel.setLevelDesc(lvlDescriptionValue);
		                	
						}
					}else{
						String lvlDescriptionValue = newDescriptionValue.toString().toUpperCase().trim();
	                	//Keeping only 32 characters, while level description is more than 32 characters
	                	if(null !=newDescriptionValue && newDescriptionValue.toString().length()>32){	                		
	                		lvlDescriptionValue = lvlDescriptionValue.substring(0,32).trim();
	                		isDescriptionTrunkated = true;
	                	}
						benefitLevel.setLevelDesc(lvlDescriptionValue);	                	
					}
			}else{//set error message description value can not be empty
				if(!tempvalidationList.contains(levelId.toString().trim())){
    				tempvalidationList.add(levelId.toString().trim());
					validationFlag = false;
					validationMessages.add(new ErrorMessage(WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_REQUIRED));
				}
			}
		}
		if(isDescriptionTrunkated){
			truncatedLvlDesc = benefitLevel.getLevelDesc();
			InformationalMessage informationalMessage = new InformationalMessage(WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_LENGTH);
			informationalMessage.setParameters(new String[] { truncatedLvlDesc });
			lvlDescMessages.add(informationalMessage);
			isDescriptionTrunkated = false;
		}
    	//Description End
		//Sets the flag true if the frequency value changed
		if((null != oldFrequencyValue) && (null != newFrequencyObj)){
			if(!(oldFrequencyValue.equals(newFrequencyObj))){
				isLineValueModified=true;
			}
		}
		//addAllMessagesToRequest(validationMessages);		
    	benefitLevel.setValidationFlag(validationFlag);
    	return benefitLevel;
    }
    /**
     * @return
     */
    private boolean validateTieredLinesValues()
    {
    	boolean validationFlag = true;
    	List tierDefList = getProductSessionObject().getBenefitTierDefinitionList();

    	for (Iterator benLevelItr = getLvlLineList().iterator(); benLevelItr.hasNext();) {
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
    			if(!( (null==newLineVal || "".equals(newLineVal)) && 
    					(null == benefitLine.getBenefitValue() || "".equals(benefitLine.getBenefitValue())) ) )	{
    				Object levelVal = this.getLineIdMap().get(new Long(benefitLine.getLineSysId()));
    				if(null != levelVal){
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
    						while (tokenizer.hasMoreTokens()) 
    						{
    							String tokens = tokenizer.nextToken();
    							if (i == 1)
    							{
    								appendedLineVal = tokens;
    							}
    							else if (i == 2)
    							{
    								appendedBnftVal = tokens;
    							}
    							else if (i == 3) 
    							{
    								appendedDataTypeId = tokens;
    							}
    							else if (i == 4) 
    							{
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
    								/*List benefitTierCriteriaList=benefitTier.getBenefitTierCriteriaList();
    								 for(Iterator benefitTierCriteriaListIter=benefitTierCriteriaList.iterator();benefitTierCriteriaListIter.next();)
    								 {
    								 BenefitTierCriteria benefitTierCriteria = (benefitTierCriteria)benefitTierCriteriaListIter.next();
    								 benefitTierCriteria.getBenefitTierCriteriaName()benefitTierCriteria.getBenefitTierCriteriaValue();
    								 }*/
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
   private String formKeyforTier(int tierDefId, int tierSysId) {
	   	String tierDefIdString = new Integer(tierDefId).toString();
		String tierSysIdString = new Integer(tierSysId).toString();
		return (tierDefIdString).concat(":").concat(tierSysIdString);
	}
   
   
   private String formKeyforMap(int levelId, int lineId, int tierSysId) {
	   	String levelIdString = new Integer(levelId).toString();
	   	String lineIdString = new Integer(lineId).toString();
		String tierSysIdString = new Integer(tierSysId).toString();
		return (levelIdString).concat(":").concat(lineIdString).concat(":").concat(tierSysIdString);
	}
  



    
    /**
     * Method for checking :if only one benefit is available and the
     * user selects to hide all the levels and try to hide the benefit
     * returns false status 
     * @return
     */
    private boolean checkAvailableBenefits(List benefitLineDefinitions,int noOfbenefitLevels){
    	boolean benefitStatus = true;
    //	int noOfbenefitLevels = 0;
    	int levelsChecked = 0;
    	int noOfBenefits = 0;
    	
	    	RetrieveProductBenefitComponentRequest retrieveProductBenefitComponentRequest = (RetrieveProductBenefitComponentRequest)
					this.getServiceRequest(ServiceManager.PRODUCT_BENEFIT_COMPONENT);
	    	retrieveProductBenefitComponentRequest.setAction(RetrieveProductBenefitComponentRequest.PRODUCT_BENEFIT_RETRIEVE);
	//    	 Accessing the value from the session and setting to request.
	 	    String key = (String)getSession().getAttribute("BNFT_CMPNT_KEY");
    	  	if(null !=key)
    	    		retrieveProductBenefitComponentRequest.setBenefitComponentId(Integer.parseInt((key)));
   	    	else
   	    	retrieveProductBenefitComponentRequest.setBenefitComponentId(getProductSessionObject().getBenefitComponentId());
    	  	//gets the product Id from session
    		int productId= super.getProductKeyFromSession();
    		retrieveProductBenefitComponentRequest.setProductId(productId);
  			//	Executes the service and fetches the response
    	   	ProductBenefitComponentResponse productBenefitComponentResponse = null;    
    	   	productBenefitComponentResponse =(ProductBenefitComponentResponse)this.executeService(retrieveProductBenefitComponentRequest);
    	    	
    	      //Extracts the benefit details from the response
    	    if(null!=productBenefitComponentResponse.getBenefitList()&& !productBenefitComponentResponse.getBenefitList().isEmpty()){
    	    	noOfBenefits = productBenefitComponentResponse.getBenefitList().size();//gets the no of benefits available in the component
    	    }
    	    if(null!=benefitLineDefinitions && !benefitLineDefinitions.isEmpty()){
	    	    //for getting the total number of benefit levels and the number of levels checked	
	    	    for(int i=0; i < benefitLineDefinitions.size(); i++){
	    	    	ProductBenefitCustomizedVO benefitDefinition = (ProductBenefitCustomizedVO)benefitLineDefinitions.get(i);
	    	    	//noOfbenefitLevels++;
	    	    	if(benefitDefinition.getBenefitLevelHideFlag().equals("T"))
	    	    		levelsChecked++;
	    	    }
	    	    //If the no of benefits is 1 and all the levels checked sets the status false	
	    	    if(noOfBenefits<=1 && noOfbenefitLevels==levelsChecked)
	    	    	benefitStatus = false;
	    	    if(noOfBenefits>1 && noOfbenefitLevels==levelsChecked)
	    	    	benefitFlag = true;
	    	    else
	    	    	benefitFlag = false;
    	    }
    	return benefitStatus;
    }

    //  **Change
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
        List universeDataTypeList = WPDStringUtil.getUniverseDataTypeList();
        if (dataType != 0) {
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
        ErrorMessage errorMessage = null;
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
                        /*
                         * double doubleValue = Double.parseDouble(value);
                         * if(doubleValue > 100){ isGreaterThanHundred = false; }
                         */
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


    // **End

    /**
     * Returns the benefitValueMap
     * 
     * @return Map benefitValueMap.
     */
    public Map getBenefitValueMap() {
        return benefitValueMap;
    }


    /**
     * Sets the benefitValueMap
     * 
     * @param benefitValueMap.
     */
    public void setBenefitValueMap(Map benefitValueMap) {
        this.benefitValueMap = benefitValueMap;
    }


    /**
     * Sets the panel
     * 
     * @param panel.
     */
    public void setPanel(HtmlPanelGrid panel) {
        this.panel = panel;
    }


    public String getBenefitDefinitionsPage() {
        //checks for view mode
        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
            if (!isViewMode())
                return "benefitDefinitionsPage";
            else
                return "benefitDefinitionsViewPage";
        } else {
            if (!isViewMode())
                return "benefitMandateDefinitionsPage";
            else
                return "benefitMandateDefinitionsViewPage";

        }

    }


    /**
     * Returns the dummyVar
     * 
     * @return String dummyVar.
     */

    public String getDummyVar() {
        return dummyVar;
    }


    /**
     * Sets the dummyVar
     * 
     * @param dummyVar.
     */

    public void setDummyVar(String dummyVar) {
        this.dummyVar = dummyVar;
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

        //Logger.logInfo("entered method setBenefitLineValuesToGridforprint");

        HtmlOutputText lineDesc = new HtmlOutputText();
        lineDesc.setValue(" ");
        HtmlOutputText lineTerm = new HtmlOutputText();
        lineTerm.setValue("");
        HtmlOutputText lineQualifier = new HtmlOutputText();
        lineQualifier.setValue("");
        HtmlOutputText linePVA = new HtmlOutputText();
        linePVA.setValue(benefitLineValues.getProviderArrangementId());
        HtmlOutputText lineDataType = new HtmlOutputText();
        lineDataType.setValue(benefitLineValues.getDataTypeDesc());

        HtmlInputHidden hiddenLineId = new HtmlInputHidden();
        hiddenLineId.setId("hiddenLineId" + j + "_" + i);

        hiddenLineId.setValue(new Integer(benefitLineValues.getLineSysId())
                + ":" + benefitLineValues.getBenefitValue() + ":"
                + benefitLineValues.getDataTypeId() + ":"
                + benefitLevelValues.getLevelDesc());

        // set the value to the map
        ValueBinding lineIdValBind = FacesContext.getCurrentInstance()
                .getApplication().createValueBinding(
                        "#{productBenefitDetailBackingBean.lineIdMap[" + benefitLineValues.getLineSysId()
                                + "]}");
        hiddenLineId.setValueBinding("value", lineIdValBind);
        HtmlInputText lineBnftValue = new HtmlInputText();
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
                possibleBnftVal.add(new SelectItem("Yes", "Y"));
                possibleBnftVal.add(new SelectItem("No", "N"));
                selectItems.setValue(possibleBnftVal);
                seloneMenuForBnftValue.getChildren().add(selectItems);
                if (null != benefitLineValues.getBenefitValue()
                        && !benefitLineValues.getBenefitValue().equals("")) {
                    if (benefitLineValues.getBenefitValue().toLowerCase()
                            .charAt(0) == 'y') {
                        seloneMenuForBnftValue.setValue("Y");
                    } else if (benefitLineValues.getBenefitValue()
                            .toLowerCase().charAt(0) == 'n') {
                        seloneMenuForBnftValue.setValue("N");
                    }else if (benefitLineValues.getBenefitValue()
                            .toLowerCase().equals("yes")) {
                        seloneMenuForBnftValue.setValue("Yes");
                    }else if (benefitLineValues.getBenefitValue()
                            .toLowerCase().equals("no")) {
                        seloneMenuForBnftValue.setValue("No");
                    } 
                    else
                        seloneMenuForBnftValue.setValue("");
                }
                seloneMenuForBnftValue.setId("lineBnftValue" + j +"_"+ i+"_print");
                object = (HtmlSelectOneMenu) seloneMenuForBnftValue;
                // set the value to the map
                ValueBinding valueItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productBenefitDetailBackingBean.benefitValueMap['"
                                // **Change**
                                        + benefitLineValues.getLineSysId()
                                        //**End**
                                        + "']}");
                seloneMenuForBnftValue.setValueBinding("value", valueItem);
            } else {
                lineBnftValue.setSize(10);
                lineBnftValue.setId("lineBnftValue" + j +"_"+ i + "_print");
                lineBnftValue.setValue(benefitLineValues.getBenefitValue());
                if (!benefitLineValues.getDataTypeDesc().equalsIgnoreCase(
                        "String")) {
                }
                lineBnftValue.setTitle("BenefitValue"
                        + benefitLineValues.getDataTypeDesc());
                ValueBinding valueItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productBenefitDetailBackingBean.benefitValueMap['"
                                // **Change**
                                        + benefitLineValues.getLineSysId()
                                        // **End**
                                        + "']}");
                lineBnftValue.setValueBinding("value", valueItem);
                lineBnftValue.setStyleClass("formInputField");
                lineBnftValue.setStyle("width:75px;");
            }
        }

        //output text for view
        HtmlOutputText lineBnftValueView = new HtmlOutputText();
        lineBnftValueView.setId("lineBnftValueView" + j +"_"+ i);
        lineBnftValueView.setValue(WPDStringUtil.spaceSeparatedString(benefitLineValues.getBenefitValue(),8));
        //lineBnftValueView.setStyleClass("formInputFieldReadOnly");
        //lineBnftValueView.setStyle("width:50px;");
        HtmlOutputText lineEmptyString = new HtmlOutputText();
        lineEmptyString.setValue(" ");

        HtmlOutputLabel lblBnftValue = new HtmlOutputLabel();
        lblBnftValue.setId("lblBnftValue"+RandomStringUtils.randomAlphanumeric(15));
        //lblBnftValue.setId("lblBnftValue" + j +"_"+ i);
        lblBnftValue.setFor("lineBnftValue" + j +"_"+ i);
        if (null != sysDataType) {
            if (sysDataType.equals("DOLLAR")) {
                if (!WebConstants.MNDT_TYPE.equals(super
                        .getProductTypeFromSession())) {
                    if (!isViewMode()&&!this.isPrintMode()) {
                        //lblBnftValue.getChildren().add(lineDataType);
                        lblBnftValue.getChildren().add(lineBnftValue);
                        lblBnftValue.getChildren().add(lineEmptyString);
                    } else {
                        //lblBnftValue.getChildren().add(lineDataType);
                        lblBnftValue.getChildren().add(lineBnftValueView);
                        lblBnftValue.getChildren().add(lineEmptyString);
                    }
                } else {
                    //lblBnftValue.getChildren().add(lineDataType);
                    lblBnftValue.getChildren().add(lineBnftValueView);
                    lblBnftValue.getChildren().add(lineEmptyString);
                }
            } else if (sysDataType.equals("PERCENTAGE")) {
                if (!WebConstants.MNDT_TYPE.equals(super
                        .getProductTypeFromSession())) {
                    if (!isViewMode() && !this.isPrintMode()) {
                        lblBnftValue.getChildren().add(lineEmptyString);
                        lblBnftValue.getChildren().add(lineBnftValue);
                        //lblBnftValue.getChildren().add(lineDataType);
                    } else {
                        lblBnftValue.getChildren().add(lineEmptyString);
                        lblBnftValue.getChildren().add(lineBnftValueView);
                        //lblBnftValue.getChildren().add(lineDataType);

                    }
                } else {
                    lblBnftValue.getChildren().add(lineEmptyString);
                    lblBnftValue.getChildren().add(lineBnftValueView);
                    //lblBnftValue.getChildren().add(lineDataType);
                }
            } else if (sysDataType.equals("STRING")) {
                if (!WebConstants.MNDT_TYPE.equals(super
                        .getProductTypeFromSession())) {
                    if (!isViewMode() && !this.isPrintMode()) {
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
            } else if (sysDataType.equals("BOOLEAN")) {
                if (!WebConstants.MNDT_TYPE.equals(super
                        .getProductTypeFromSession())) {
                    if (!isViewMode() && !this.isPrintMode()) {
                        lblBnftValue.getChildren().add(lineEmptyString);
                        lblBnftValue.getChildren().add(object);
                        //lblBnftValue.getChildren().add(lineDataType);
                    } else {
                        lblBnftValue.getChildren().add(lineEmptyString);
                        lblBnftValue.getChildren().add(lineBnftValueView);
                    }
                } else {
                    lblBnftValue.getChildren().add(lineEmptyString);
                    lblBnftValue.getChildren().add(lineBnftValueView);
                }
            } else {
                if (!WebConstants.MNDT_TYPE.equals(super
                        .getProductTypeFromSession())) {
                    if (!isViewMode() && !this.isPrintMode()) {
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
            lblBnftValue.getChildren().add(hiddenLineId);
        }
        // **End**
        HtmlOutputText lineReference = new HtmlOutputText();
        lineDesc.setValue(" ");
        lineReference.setValue(benefitLineValues.getReferenceDesc());
        HtmlOutputText lineImage = new HtmlOutputText();
        lineDesc.setValue(" ");

        HtmlOutputLabel lblRefAndNotes = new HtmlOutputLabel();
        lblRefAndNotes.setId("lblRefAndNotes"+RandomStringUtils.randomAlphanumeric(15));
        //lblRefAndNotes.setId("lblRefNotes" + j +"_"+ i);
        //lblBnftValue.setId("lblRefAndNotes" + j +"_"+ i);
        lblBnftValue.setId("lblBnftValue"+RandomStringUtils.randomAlphanumeric(15));
        lblBnftValue.setFor("lblRefAndNotes" + j +"_"+ i);

        HtmlOutputLabel lblNotesImage = new HtmlOutputLabel();
        lblNotesImage.setId("lblNotesImage"+RandomStringUtils.randomAlphanumeric(15));
        //lblNotesImage.setId("lblNotesImage" + j +"_"+ i);
        lblNotesImage.setFor("lblNotesImage" + j +"_"+ i);

        HtmlGraphicImage noteImage = new HtmlGraphicImage();
        noteImage.setUrl("../../images/notes_exist.gif");
        noteImage.setStyle("cursor:hand;");
        noteImage.setId("noteImage" + j + i);

        HtmlCommandButton notesButton = new HtmlCommandButton();
        notesButton.setValue("NotesButton");
        if (benefitLineValues.getBnftLineNotesExist().equals("Y"))
            notesButton.setImage("../../images/notes_exist.gif");
        else
            notesButton.setImage("../../images/page.gif");
        notesButton.setTitle("Note");
        notesButton.setStyle("border:0;");
        notesButton.setAlt("Notes");
        notesButton.setId("notesButton" + j + i);
        //notesButton.setOnclick("ewpdModalWindow_ewpd('productBenefitLevelNotesOverridePopup.jsp?parentEntityType=ATTACHPRODUCT&lookUpAction=4&secondaryEntityId="
        // + benefitLineValues.getLineSysId() + "&temp="+Math.random()+"' ,
        // 'dummyDiv', 'benefitDefinitionForm:hidden1',2,1);return false;");

        // lgnd data type
        HtmlOutputText lgndDataType = new HtmlOutputText();
        lgndDataType.setValue(benefitLineValues.getDataTypeLegend());
        lgndDataType.setId("lgndDataType" + j +"_"+ i);

        if (!isViewMode() && !this.isPrintMode()) {
            notesButton.setOnclick("getUrlAssigned(" + benefitLineValues.getLineSysId()
                    + ");return false;");
        } else {
            notesButton.setOnclick("getUrlAssigned(" + benefitLineValues.getLineSysId()
                    + "," + super.getBenefitComponentIdFromSession() + ","
                    + super.getProductKeyFromSession() + ");return false;");
        }
        lblRefAndNotes.getChildren().add(lineReference);
        if (!isPrintMode()) {
            lblNotesImage.getChildren().add(notesButton);
        }

        panel.getChildren().add(lineDesc);
        panel.getChildren().add(lineTerm);
        panel.getChildren().add(lineQualifier);
        panel.getChildren().add(linePVA);
        panel.getChildren().add(lgndDataType);
        panel.getChildren().add(lblBnftValue);
        panel.getChildren().add(lblRefAndNotes);
        if(!isPrintMode()){
        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
        	panel.getChildren().add(lblNotesImage);
            if (!isViewMode() && !this.isPrintMode()) {
               panel.getChildren().add(lineImage);
            }
        }}
    }


    /**
     * This method sets the benefitLevelValues to the PanelGrid
     * 
     * @param i
     * @param benefitLevelValues
     */
    private void setBenefitLevelValuesToGridForPrint(int i,
            BenefitLevel benefitLevelValues, int lineSize, int rowNum) {

        Logger.logInfo("entered method setBenefitLevelValuesToGridForPrint");
        HtmlOutputText levelDesc = new HtmlOutputText();
        if (null != benefitLevelValues.getLevelDesc()) {
        	String desc = null;
        	String description = benefitLevelValues.getLevelDesc().trim();
            if(description.length()>21){
            	String[] strTokenizerArr = description.split(" ");
            	for(int num=0;num<strTokenizerArr.length;num++){
            		if(strTokenizerArr[num].length()>21){
            			strTokenizerArr[num] = strTokenizerArr[num].substring(0,21)+" "+strTokenizerArr[num].substring(21);
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
        }
        levelDesc.setId("levelDesc" + benefitLevelValues.getLevelId());

        HtmlOutputText levelTerm = new HtmlOutputText();
        if (null != benefitLevelValues.getTermDesc()) {
            levelTerm.setValue(benefitLevelValues.getTermDesc().trim());
        } else {
            levelTerm.setValue(WebConstants.EMPTY_STRING);
        }

        HtmlOutputText levelQualifier = new HtmlOutputText();
        /*START CARS */
        //Frequency
        if(null != benefitLevelValues.getFrequencyDesc() 
        		&& !WebConstants.EMPTY_STRING.equalsIgnoreCase(benefitLevelValues.getFrequencyDesc())
				&& benefitLevelValues.getFrequencyDesc().length() > 0
				&& Integer.parseInt(benefitLevelValues.getFrequencyDesc())>0){
        	String qualifier = benefitLevelValues.getQualifierDesc().trim();
        	if (null != benefitLevelValues.getQualifierDesc()) {
        		levelQualifier.setValue(benefitLevelValues.getFrequencyDesc()+" - "+qualifier);
        	}else{
            	levelQualifier.setValue(benefitLevelValues.getFrequencyDesc());
        	}
        }else{
        	if (null != benefitLevelValues.getQualifierDesc()) {
        		levelQualifier.setValue(benefitLevelValues.getQualifierDesc().trim());
        	}else{
        		levelQualifier.setValue(WebConstants.EMPTY_STRING);
        	}
        }
		/*END CARS */
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
        ValueBinding levelIdValBind = FacesContext.getCurrentInstance()
                .getApplication().createValueBinding(
                        "#{productBenefitDetailBackingBean.levelIdMap[" + i
                                + "]}");
        hiddenLevelId.setValueBinding("value", levelIdValBind);
        // change end
        

        HtmlOutputLabel lblImage = new HtmlOutputLabel();
        lblImage.setFor("levelDesc" + i);
        lblImage.setId("lblImage"+RandomStringUtils.randomAlphanumeric(15));
        //lblImage.setId("lblImage" + i);

        //sets the size to a hidden variable
        HtmlInputHidden hiddenLineSize = new HtmlInputHidden();
        hiddenLineSize.setId("hiddenLineSize" + i);
        hiddenLineSize.setValue(new Integer(lineSize));

        //      sets the size to a hidden variable
        HtmlInputHidden hiddenRowSize = new HtmlInputHidden();
        hiddenRowSize.setId("hiddenRowNum" + i);
        hiddenRowSize.setValue(new Integer(rowNum));

        HtmlOutputText lineImage = new HtmlOutputText();
        lineImage.setValue(" ");
        
        HtmlOutputText dummyText = new HtmlOutputText();
        levelPVA.setValue(" ");

        //checks if it is a view mode
        if (!isViewMode()&& !this.isPrintMode()) {
            lblImage.getChildren().add(hiddenLevelId);
            lblImage.getChildren().add(hiddenLineSize);
            lblImage.getChildren().add(hiddenRowSize);
        }
        panel.getChildren().add(levelDesc);
        panel.getChildren().add(levelTerm);
        panel.getChildren().add(levelQualifier);
        panel.getChildren().add(levelPVA);
        panel.getChildren().add(dummyText);
        panel.getChildren().add(levelBnftValue);
        panel.getChildren().add(levelReference);
        if(!isPrintMode()){
        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
            panel.getChildren().add(lineImage);
            if (!isViewMode()&&!this.isPrintMode()) {
                panel.getChildren().add(lblImage);
            }
        }}

    }


    /**
     * Returns the headerPanelForPrint
     * 
     * @return HtmlPanelGrid headerPanelForPrint.
     */
    public HtmlPanelGrid getHeaderPanelForPrint() {

        Logger.logInfo("entered method getHeaderPanelfor print");
        this.setPrintMode(true);
        //sets the string which contains the levels to delete to null
        if (null != this.levelsToDelete) {
            this.levelsToDelete = null;
        }
        headerPanel = new HtmlPanelGrid();
        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
            if (!isViewMode() && !isPrintMode()) {
                headerPanel.setWidth("100%");
                headerPanel.setColumns(7);
            } else {
                headerPanel.setWidth("100%");
                headerPanel.setColumns(7);
            }
        } else {
            headerPanel.setWidth("100%");
            headerPanel.setColumns(7);
        }
        headerPanel.setBorder(0);
        headerPanel.setCellpadding("3");
        headerPanel.setCellspacing("1");
        headerPanel.setBgcolor("#cccccc");

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
        headerText1.setId("desc");

        headerText2.setValue("Term");
        headerText2.setId("term");
       
        /*Start CARS */	 
        headerText3.setValue("Frequency - Qualifier");
        /*End CARS */	 
        headerText3.setId("qualifier");

        headerText4.setValue("PVA");
        headerText4.setId("pva");

        headerText5.setValue("Benefit Value");
        headerText5.setId("bnftValue");

        headerText9.setValue("Format");
        headerText9.setId("dataTypeLgnd");

        headerText6.setValue("Reference");
        headerText6.setId("ref");
        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
            headerText7.setValue(" ");
            headerText8.setValue(" ");
        }
        headerPanel.setStyleClass("dataTableHeader");
        headerPanel.getChildren().add(headerText1);
        headerPanel.getChildren().add(headerText2);
        headerPanel.getChildren().add(headerText3);
        headerPanel.getChildren().add(headerText4);
        headerPanel.getChildren().add(headerText9);
        headerPanel.getChildren().add(headerText5);
        headerPanel.getChildren().add(headerText6);
        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
           // headerPanel.getChildren().add(headerText7);
            //if (!isViewMode()) {
            //headerPanel.getChildren().add(headerText8);
            //}
        }
        return headerPanel;

    }


    /**
     * @param headerPanelForPrint
     *            The headerPanelForPrint to set.
     */
    public void setHeaderPanelForPrint(HtmlPanelGrid headerPanelForPrint) {
        this.headerPanelForPrint = headerPanelForPrint;
    }


    /**
     * Returns the Panel for print page
     * 
     * @return HtmlPanelGrid PanelForPrint
     *  
     */
    public HtmlPanelGrid getPanelForPrint() {

        Logger.logInfo("entered method getPanelForPrint");
        this.setPrintMode(true);
        int rowNumber = 0;
        List benefitDefinitonsList = this.getBenefitDefinitionsList();
        getValuesFromDefinitonList(benefitDefinitonsList);
        panel = new HtmlPanelGrid();
        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
            panel.setWidth("100%");
            panel.setColumns(7);
        } else {
            panel.setWidth("100%");
            panel.setColumns(7);
        }
        panel.setBorder(0);
        panel.setCellpadding("3");
        panel.setCellspacing("1");
        panel.setBgcolor("#cccccc");
        StringBuffer rows = new StringBuffer();
        //setting values to benefit levels
        int size = benefitLevelList.size();
        //iterating to get the benefit levels
        for (int i = 0; i < size; i++) {
            rowNumber++;
            rows.append("dataTableOddRow");
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
        this.productType = super.getProductTypeFromSession();
        panel.setRowClasses(rows.toString());
        return panel;
    }


    /**
     * @param panelForPrint
     *            The panelForPrint to set.
     */
    public void setPanelForPrint(HtmlPanelGrid panelForPrint) {
        this.panelForPrint = panelForPrint;
    }


    /**
     * @return Returns the productType.
     */
    public String getProductType() {
        return productType;
    }


    /**
     * @param productType
     *            The productType to set.
     */
    public void setProductType(String productType) {
        this.productType = productType;
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
     * @param validationMessages.
     */

    public void setValidationMessages(List validationMessages) {
        this.validationMessages = validationMessages;
    }


    


    /**
     * @return printMode
     * 
     * Returns the printMode.
     */
    public boolean isPrintMode() {
        return printMode;
    }


    /**
     * @param printMode
     * 
     * Sets the printMode.
     */
    public void setPrintMode(boolean printMode) {
        this.printMode = printMode;
    }
	/**
	 * @return Returns the checkBoxMap.
	 */
	public Map getCheckBoxMap() {
		return checkBoxMap;
	}
	/**
	 * @param checkBoxMap The checkBoxMap to set.
	 */
	public void setCheckBoxMap(Map checkBoxMap) {
		this.checkBoxMap = checkBoxMap;
	}
	/**
	 * @return Returns the benefitLevelHideFlag.
	 */
	public String getBenefitLevelHideFlag() {
		return benefitLevelHideFlag;
	}
	/**
	 * @param benefitLevelHideFlag The benefitLevelHideFlag to set.
	 */
	public void setBenefitLevelHideFlag(String benefitLevelHideFlag) {
		this.benefitLevelHideFlag = benefitLevelHideFlag;
	}
	/**
	 * @return Returns the benefitLineHideFlag.
	 */
	public String getBenefitLineHideFlag() {
		return benefitLineHideFlag;
	}
	/**
	 * @param benefitLineHideFlag The benefitLineHideFlag to set.
	 */
	public void setBenefitLineHideFlag(String benefitLineHideFlag) {
		this.benefitLineHideFlag = benefitLineHideFlag;
	}
	/**
	 * @return Returns the showHidden.
	 */
	public boolean isShowHidden() {
		return showHidden;
	}
	/**
	 * @param showHidden The showHidden to set.
	 */
	public void setShowHidden(boolean showHidden) {
		this.showHidden = showHidden;
	}
	
//	/**
//	 * @return Returns the benefitDisplayFlag.
//	 */
//	public boolean isBenefitDisplayFlag() {
//        //getting the benefit component name from session
//        String benefitComName =(String)getSession().getAttribute("BENEFIT_COMP_NAME");
//        if(null!=benefitComName && benefitComName.equals("GENERAL BENEFITS"))
//        	benefitDisplayFlag = false;
//        else
//        	benefitDisplayFlag = true;
//		return benefitDisplayFlag;
//	}
	/**
	 * @param benefitDisplayFlag The benefitDisplayFlag to set.
	 */
	public void setBenefitDisplayFlag(boolean benefitDisplayFlag) {
		this.benefitDisplayFlag = benefitDisplayFlag;
	}
	
	/**
	 * @return Returns the duplicateBenefitLineRefList.
	 */
	public List getDuplicateBenefitLineRefList() {
		return duplicateBenefitLineRefList;
	}
	/**
	 * @param duplicateBenefitLineRefList The duplicateBenefitLineRefList to set.
	 */
	public void setDuplicateBenefitLineRefList(List duplicateBenefitLineRefList) {
		this.duplicateBenefitLineRefList = duplicateBenefitLineRefList;
	}
	/**
	 * @return Returns the duplicateQuestionRefList.
	 */
	public List getDuplicateQuestionRefList() {
		return duplicateQuestionRefList;
	}
	/**
	 * @param duplicateQuestionRefList The duplicateQuestionRefList to set.
	 */
	public void setDuplicateQuestionRefList(List duplicateQuestionRefList) {
		this.duplicateQuestionRefList = duplicateQuestionRefList;
	}
	/**
	 * @return Returns the loadDuplicateReference.
	 * 
	 * CR --This method for loading popup while duplicate reference validation
	 * 
	 * creating two list contains duplicate benefit line  reference and duplicate question reference
	 * 
	 */
	public String getLoadDuplicateReference() {
		Logger.logInfo("entered method getBenefitDefinitionsList");
		ProductQuestUniqueReferenceBO productQuestUniqueReferenceBO = null;
		int prevBenefitComponentId ;
		int prevBenefitId;
		int prevReferenceId;
		int newBenefitComponentId ;
		int newBenefitId;
		int newReferenceId;
		
		List allDuplicateReference;
        RetrieveProductBenefitDefinitionRequest retrieveProductBenefitDefinitionRequest = (RetrieveProductBenefitDefinitionRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_BENEFIT_DEFINITION_REQUEST);
        retrieveProductBenefitDefinitionRequest.setDuplicateRefPopup(true);
        RetrieveProductBenefitDefinitionResponse retrieveProductBenefitDefinitionResponse = (RetrieveProductBenefitDefinitionResponse) 
		executeService(retrieveProductBenefitDefinitionRequest);
        allDuplicateReference = (retrieveProductBenefitDefinitionResponse.getProductDuplicateReferenceList());
        if(null!=allDuplicateReference && allDuplicateReference.size()>0) {
        	this.duplicateBenefitLineRefList = (List)allDuplicateReference.get(0);
        	this.duplicateQuestionRefList = (List)allDuplicateReference.get(1);
        }
        
        messageList = new ArrayList(5);
        List modifiedList = null; 
        if(null!=duplicateBenefitLineRefList && duplicateBenefitLineRefList.size()>0){
        	modifiedList = new ArrayList();
        	int k = duplicateBenefitLineRefList.size();
        	if(k>WebConstants.MAX_SIZE){
        		k=WebConstants.MAX_SIZE;
        		InformationalMessage message = new InformationalMessage("unique.ref.list.truncated");
        		 String [] params = new String[] {"Lines"};
       		 	 message.setParameters(params);
        		 messageList.add(message);
        	}
        	productQuestUniqueReferenceBO=(ProductQuestUniqueReferenceBO)duplicateBenefitLineRefList.get(0);
        	modifiedList.add(productQuestUniqueReferenceBO);
			prevBenefitComponentId = productQuestUniqueReferenceBO.getBenefitComponentId();
			prevBenefitId = productQuestUniqueReferenceBO .getBenefitSysId();
			prevReferenceId = productQuestUniqueReferenceBO.getRefId();
			int i = 1;
    		for(;i<k;i++){
    			productQuestUniqueReferenceBO=(ProductQuestUniqueReferenceBO)duplicateBenefitLineRefList.get(i);
    			newBenefitComponentId = productQuestUniqueReferenceBO.getBenefitComponentId();
    			newBenefitId = productQuestUniqueReferenceBO.getBenefitSysId();
    			newReferenceId = productQuestUniqueReferenceBO.getRefId();
    			
    			if(prevBenefitComponentId == newBenefitComponentId){
    				productQuestUniqueReferenceBO.setBenefitComponentName(WebConstants.EMPTY_STRING);
    				if(prevBenefitId == newBenefitId){
    					productQuestUniqueReferenceBO.setBenefitSysName(WebConstants.EMPTY_STRING);
    					if(prevReferenceId == newReferenceId){
    						productQuestUniqueReferenceBO.setReferenceDesc(WebConstants.EMPTY_STRING);
    					}
    				}
    			}
    			modifiedList.add(productQuestUniqueReferenceBO);
    			prevBenefitComponentId = newBenefitComponentId;
    			prevBenefitId = newBenefitId;
    			prevReferenceId = newReferenceId;
    		}
        	this.duplicateBenefitLineRefList = modifiedList;	
        }
        
        
        // -------------------------------
        if(null!=duplicateQuestionRefList && duplicateQuestionRefList.size()>0){
        	modifiedList = new ArrayList();
        	int k = duplicateQuestionRefList.size();
        	if(k>WebConstants.MAX_RES_FOR_DUP){
        		k=WebConstants.MAX_RES_FOR_DUP;
        		InformationalMessage message = new InformationalMessage("unique.ref.list.truncated");
        		 String [] params = new String[] {"Questions"};
       		 	 message.setParameters(params);
        		 messageList.add(message);
        	}
        	productQuestUniqueReferenceBO=(ProductQuestUniqueReferenceBO)duplicateQuestionRefList.get(0);
        	modifiedList.add(productQuestUniqueReferenceBO);
			prevBenefitComponentId = productQuestUniqueReferenceBO.getBenefitComponentId();
			prevBenefitId = productQuestUniqueReferenceBO .getBenefitSysId();
			prevReferenceId = productQuestUniqueReferenceBO.getRefId();
			int i = 1;
    		for(;i<k;i++){
    			productQuestUniqueReferenceBO=(ProductQuestUniqueReferenceBO)duplicateQuestionRefList.get(i);
    			newBenefitComponentId = productQuestUniqueReferenceBO.getBenefitComponentId();
    			newBenefitId = productQuestUniqueReferenceBO.getBenefitSysId();
    			newReferenceId = productQuestUniqueReferenceBO.getRefId();
    			
    			if(prevBenefitComponentId == newBenefitComponentId){
    				productQuestUniqueReferenceBO.setBenefitComponentName(WebConstants.EMPTY_STRING);
    				if(prevBenefitId == newBenefitId){
    					productQuestUniqueReferenceBO.setBenefitSysName(WebConstants.EMPTY_STRING);
    					if(prevReferenceId == newReferenceId){
    						productQuestUniqueReferenceBO.setReferenceDesc(WebConstants.EMPTY_STRING);
    					}
    				}
    			}
    			modifiedList.add(productQuestUniqueReferenceBO);
    			prevBenefitComponentId = newBenefitComponentId;
    			prevBenefitId = newBenefitId;
    			prevReferenceId = newReferenceId;
    		}
        	this.duplicateQuestionRefList = modifiedList;	
        }
        
        // modifying duplicateQuestionRefList to display in the datatable  
        
        if(null != retrieveProductBenefitDefinitionResponse
                && null != retrieveProductBenefitDefinitionResponse.getMessages() && retrieveProductBenefitDefinitionResponse.getMessages().size() > 0){
        	messageList.addAll(retrieveProductBenefitDefinitionResponse.getMessages());
        }
        addAllMessagesToRequest(this.messageList);
        this.productName = this.getProductNameFromSession();
        this.productVersion = this.getVersionFromSession();
        
		return loadDuplicateReference;
	}
	/**
	 * @param loadDuplicateReference The loadDuplicateReference to set.
	 */
	public void setLoadDuplicateReference(String loadDuplicateReference) {
		this.loadDuplicateReference = loadDuplicateReference;
	}
	/**
	 * @return Returns the productName.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName The productName to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return Returns the productVersion.
	 */
	public int getProductVersion() {
		return productVersion;
	}
	/**
	 * @param productVersion The productVersion to set.
	 */
	public void setProductVersion(int productVersion) {
		this.productVersion = productVersion;
	}
	/**
	 * @return Returns the totalTime.
	 */
	public String getTotalTime() {
		return totalTime;
	}
	/**
	 * @param totalTime The totalTime to set.
	 */
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	/**
	 * @return Returns the panelData.
	 */
	public String getPanelData() {
		return panelData;
	}
	/**
	 * @param panelData The panelData to set.
	 */
	public void setPanelData(String panelData) {
		this.panelData = panelData;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public HtmlPanelGrid getTierPanel() {

		Logger.logInfo("entered method getTierPanel");
				
		
		if(!this.benefitDefinitionsListRetrieved || this.benefitDefinitionsList == null) {
			this.benefitDefinitionsList = this.getBenefitDefinitionsList();
			this.benefitDefinitionsListRetrieved = true;
		}
		
		// Retaining the previous entered values, In case of application has any error message (Defect: 186432)
		// get the benefit defenitions list if list not null
		
		
        if (validationMessages.isEmpty() && null != this.benefitDefinitionsList){
		int rowNumber = 0;
		int lineCount = 0;
		int tierNo = 0;

		//List benefitDefinitonsList = this.getBenefitDefinitionsList();
		// This method gets the values from the benefit definiton List and sets
		// it to the level list and line list
		//getValuesFromDefinitonList(benefitDefinitonsList);

		tierPanel = new HtmlPanelGrid();
		tierPanel.setColumns(1);
		tierPanel.setWidth("100%");

		tierPanel.setBorder(0);
		tierPanel.setCellpadding("0");
		tierPanel.setCellspacing("0");
		//            tierPanel.setBgcolor("#cccccc");

		StringBuffer rows = new StringBuffer();

		// setting values to benefit levels

		int size = 0;
		if(null != tierDefList){
			size = tierDefList.size();
			SortTiers(tierDefList);
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
		// iterating to get the benefit levels
		for(int l = 0; l < size; l++){
			tierDefinition = (BenefitTierDefinition) getTierDefList().get(l);
			rowNumber++;
			tierDefPanel = new HtmlPanelGrid();
			tierDefPanel.setColumns(1);
			tierDefPanel.setWidth("100%");

			tierDefPanel.setBorder(0);
			tierDefPanel.setCellpadding("0");
			tierDefPanel.setCellspacing("0");

			//                    tierDefPanel.setBgcolor("#cccccc");

			HtmlOutputLabel defLabel = new HtmlOutputLabel();
			defLabel.setId("defLabel"+RandomStringUtils.randomAlphanumeric(15));
			HtmlOutputText tierDef = new HtmlOutputText();
			tierDef.setStyleClass("dataTableHeader1");
			tierDef.setValue(tierDefinition.getBenefitTierDefinitionName());
			tierDef.setId("tierDef_"+tierDefinition.getBenefitTierDefinitionSysId()+"_Id");

			HtmlInputHidden hidDefId = new HtmlInputHidden();
			hidDefId.setValue(tierDefinition.getBenefitTierDefinitionName());
			hidDefId.setId("tierDef_"+tierDefinition.getBenefitTierDefinitionSysId()+"_HiddenId");

			ValueBinding defValBind = FacesContext.getCurrentInstance()
					.getApplication().createValueBinding(
							"#{productBenefitDetailBackingBean.tierDefMap["
									+ tierDefinition
											.getBenefitTierDefinitionSysId()
									+ "]}");
			hidDefId.setValueBinding("value", defValBind);
			defLabel.getChildren().add(tierDef);
			defLabel.getChildren().add(hidDefId);
			tierDefPanel.getChildren().add(defLabel);

			tierPanel.getChildren().add(tierDefPanel);

			tierList = tierDefinition.getBenefitTiers();
			BenefitTier tier = null;
			for(int m = 0; m < tierList.size(); m++){
				rows.append("dataTableOddRow");
				tier = (BenefitTier) tierList.get(m);
				critList = tier.getBenefitTierCriteriaList();
				HtmlInputHidden hiddenTierId = new HtmlInputHidden();
				hiddenTierId.setValue(new Integer(tier.getBenefitTierSysId()));
				hiddenTierId.setId("tierDef_"+tier.getBenefitTierSysId()+"_"+m);

				String keyForTierMap = formKeyforTier(tierDefinition
						.getBenefitTierDefinitionSysId(), tier
						.getBenefitTierSysId());
				keyForTierMap = "\"".concat(keyForTierMap).concat("\"");
				ValueBinding tierValBind = FacesContext.getCurrentInstance()
						.getApplication().createValueBinding(
								"#{productBenefitDetailBackingBean.tierIdMap["
										+ keyForTierMap + "]}");
				hiddenTierId.setValueBinding("value", tierValBind);

				tierCritPanel = new HtmlPanelGrid();
				tierCritPanel.setColumns(1);
				tierCritPanel.setWidth("100%");

				tierCritPanel.setBorder(0);
				tierCritPanel.setCellpadding("0");
				tierCritPanel.setStyle("height:100px;");
				tierCritPanel.setCellspacing("0");
				tierCritPanel.setBgcolor("#cccccc");
				tierCritPanel.setRowClasses("dataTableOddRow");

				tierHeaderPanel = new HtmlPanelGrid();
				tierHeaderPanel.setCellpadding("0");
				tierHeaderPanel.setCellspacing("0");
				tierHeaderPanel.setColumns(3);
				tierHeaderPanel.setWidth("100%");
				tierHeaderPanel.setBgcolor("#FFFFFF");

				tierHeaderPanel.setStyleClass("headerPanel1");

				//            tierHeaderPanel.setRowClasses("");
				//            tierHeaderPanel.setStyleClass("boxBorder");
				
				tierHeaderPanel.setColumnClasses("gridColumn40,gridColumn40,gridColumnRight20");
				
				tierHeaderPanel_wclc = new HtmlPanelGrid();
				tierHeaderPanel_wclc.setCellpadding("0");
				tierHeaderPanel_wclc.setCellspacing("0");
				tierHeaderPanel_wclc.setColumns(3);
				tierHeaderPanel_wclc.setWidth("100%");
				tierHeaderPanel_wclc.setBgcolor("#FFFFFF");
				tierHeaderPanel_wclc.setStyleClass("headerPanel1");
				tierHeaderPanel_wclc.setColumnClasses("gridColumn40,gridColumn40,gridColumnRight20");
				//tierHeaderPanel_wclc.setStyle("border-right:solid #cccccc 1px;border-left:solid #cccccc 1px;height:0px;");
				
				
				tierNo++;
				HtmlOutputLabel tierlabel = new HtmlOutputLabel();
				tierlabel.setId("tierlabel"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputLabel tierlabel1 = new HtmlOutputLabel();
				tierlabel1.setId("tierlabel1"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputText dummylabel = new HtmlOutputText();
				//dummylabel.setStyle("width:50px;");
				HtmlOutputText dummylabel1 = new HtmlOutputText();
				dummylabel1.setStyle("width:5px;");

				if(null != critList){
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
							//String tierCriteriaValue = tierCriteria.getBenefitTierCriteriaValue();
							critValueView.setValue(tierCriteria.getBenefitTierCriteriaValue());
							if (k<2){	
								tierlabel.getChildren().add(dummylabel1);
								tierlabel.getChildren().add(critValueView);
							}
							else{
								tierlabel.getChildren().add(dummylabel1);
								tierlabel1.getChildren().add(critValueView);
							}
						}else{
						HtmlInputText critVal = null;
						HtmlSelectOneMenu preAuthValue = null;
						HtmlOutputText  spaceChar = null;
						
                        if(tier.getCriteriaIndicator().equals("2")){
                            critVal = new HtmlInputText();
                            critVal.setId("critVal_" + k + "_" + m + "_" + tierNo + "label" + tier.getBenefitTierSysId());
                            critVal.setTitle("CriteriaValue");
                            critVal.setMaxlength(10);
                            // Added by Narasimha to fix PROD00055203
                            if(tierDefinition.getDataType().equalsIgnoreCase("numeric")){
                                   critVal.setOnkeypress("return isNum(event);");
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
                                                            .createValueBinding("#{productBenefitDetailBackingBean.tierCriteriaMap["
                                                            		+ keyForMap + "]}");
                            critVal.setValueBinding("value", critValBind);
                            if(tierDefinition.getDataType().equalsIgnoreCase("date")){
                            	 critVal.setOnkeydown("return false;"); 
                            	 critVal.setOnmousedown("return DisableRightClick(event);");
                                 critVal.setStyle("width : 80px;");
                                 HtmlCommandButton cmdBtn = new HtmlCommandButton();
                                 spaceChar = new HtmlOutputText();
                                 spaceChar.setStyle("width : 20px;");
                                 cmdBtn.setId("calBtn"+k+m+tierNo);
                                 String fieldId = cmdBtn.getId();
                                 cmdBtn.setOnclick("changeId(this);cal2.select('benefitDefinitionForm:"+critVal.getId()+"','"+fieldId+"','MM/dd'); return false;");
                            cmdBtn.setImage("../../images/cal.gif");
                            cmdBtn.setStyle("cursor: hand; valign: middle");
                            cmdBtn.setAlt("Calender");
                            keyForMap = formKeyforMap(tierDefinition.getBenefitTierDefinitionSysId(), tier
                                               .getBenefitTierSysId(), tierCriteria.getBenefitTierCriteriaSysId());
                            keyForMap = "\"".concat(keyForMap).concat("\"");
                            critValBind = FacesContext.getCurrentInstance().getApplication()
											.createValueBinding("#{productBenefitDetailBackingBean.tierCriteriaMap["+ keyForMap + "]}");
                            critVal.setValueBinding("value", critValBind);
                            tierlabel.getChildren().add(critVal);
                            tierlabel.getChildren().add(spaceChar);
                            tierlabel.getChildren().add(cmdBtn);
                            tierHeaderPanel.getChildren().add(tierlabel);
                    		tierlabel = new HtmlOutputLabel();
                    		tierlabel.setId("tierlabel"+RandomStringUtils.randomAlphanumeric(15));
                            }else 
                            {
                            	if(k<2){
                            		tierlabel.getChildren().add(critVal);
                            		tierHeaderPanel.getChildren().add(tierlabel);
                            		tierlabel = new HtmlOutputLabel();
                            		tierlabel.setId("tierlabe"+RandomStringUtils.randomAlphanumeric(15));
                            	}
                            	else{
                            		tierlabel1.getChildren().add(critVal);
                            		tierHeaderPanel_wclc.getChildren().add(tierlabel1);
                            		tierlabel1 = new HtmlOutputLabel();
                            		tierlabel1.setId("tierlael1"+RandomStringUtils.randomAlphanumeric(15));
                            		
                            	}
                            }
                        }else if(tier.getCriteriaIndicator().equals("1")){
                              if(fetchPosibleValuesList(tierCriteria.getBenefitTierCriteriaName()).size()>2){
                              	UIColumn col = new UIColumn();
                              	HtmlInputText eligibilText = new HtmlInputText();
                              	eligibilText.setOnmousedown("return DisableRightClick(event);");
                              	eligibilText.setTitle("CriteriaValue");
                              	eligibilText.setId("eligibility" + k + "_" + m + "_" + tierNo + "label" + tier.getBenefitTierSysId());
                              	eligibilText.setStyleClass("selectDataDisplayDiv");
                              	eligibilText.setValue(tierCriteria.getBenefitTierCriteriaValue());
                              	eligibilText.setStyle("width:50px;");
                              	eligibilText.setOnkeydown("return false");
                              	
                              	HtmlCommandButton cmdBtn = new HtmlCommandButton();
                              	cmdBtn.setId("cmdBtn"+k+m+tierNo);
                              	spaceChar = new HtmlOutputText();
                              	spaceChar.setStyle("width : 20px;");
                              	cmdBtn.setOnclick("ewpdModalWindow_ewpd('../popups/possibleValuesPopupForProductPage.jsp'+getUrl()+'?criteriaStirng="+tierCriteria.getBenefitTierCriteriaName()+"','benefitDefinitionForm:"+eligibilText.getId()+"','benefitDefinitionForm:"+eligibilText.getId()+"',2,2);return false;");
                                cmdBtn.setImage("../../images/select.gif");
                                cmdBtn.setStyle("cursor: hand; valign: middle");
                                cmdBtn.setAlt("Possible Values");
                                String keyForMap = formKeyforMap(tierDefinition.getBenefitTierDefinitionSysId(), 
                            			tier.getBenefitTierSysId(), tierCriteria.getBenefitTierCriteriaSysId());
                                keyForMap = "\"".concat(keyForMap).concat("\"");
                                ValueBinding critValBind = FacesContext.getCurrentInstance().getApplication()
                                               .createValueBinding("#{productBenefitDetailBackingBean.tierCriteriaMap["+ keyForMap + "]}");
                                eligibilText.setValueBinding("value", critValBind);
                                col.getChildren().add(eligibilText);
                                col.getChildren().add(spaceChar);
                                col.getChildren().add(cmdBtn);
                                tierlabel.getChildren().add(col);
                                tierHeaderPanel.getChildren().add(tierlabel);
                        		tierlabel = new HtmlOutputLabel();
                        		tierlabel.setId("tierlabel"+RandomStringUtils.randomAlphanumeric(15));
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
                                                   .createValueBinding("#{productBenefitDetailBackingBean.tierCriteriaMap["+ keyForMap + "]}");
                                crSelect.setValueBinding("value", critValBind);
                                col.getChildren().add(crSelect);
                                tierlabel.getChildren().add(col);
                                tierHeaderPanel.getChildren().add(tierlabel);
                        		tierlabel = new HtmlOutputLabel();
                        		tierlabel.setId("tierlbel"+RandomStringUtils.randomAlphanumeric(15));
                               }
                        }
						}
					}
					HtmlCommandButton deleteButton = new HtmlCommandButton();
					deleteButton.setId("deleteButton" + m + "_"
							+ tier.getBenefitTierSysId() + tierNo);
					deleteButton.setValue("Delete");
					deleteButton.setStyleClass("wpdButton");
					deleteButton.setOnclick("deleteTier("
							+ tier.getBenefitTierSysId() + "); return false;");
					if(critList.size() < 2){
						if(!isViewMode() && !isPrintMode()){
							tierlabel.getChildren().add(dummylabel1);
							//	tierlabel.getChildren().add(deleteButton);
						}
	
						tierlabel.getChildren().add(hiddenTierId);
						if(!isViewMode()){
							tierlabel.getChildren().add(dummylabel1);
							tierHeaderPanel.getChildren().add(tierlabel);
							tierlabel.getChildren().add(deleteButton);
						}
						tierHeaderPanel.getChildren().add(tierlabel);
	
						
						tierCritPanel.getChildren().add(tierHeaderPanel);
					}else if(critList.size() == 2){
						if(!isViewMode() && !isPrintMode()){
							tierlabel.getChildren().add(dummylabel1);
							//	tierlabel.getChildren().add(deleteButton);
						}
	
						tierlabel.getChildren().add(hiddenTierId);
						if(!isViewMode()){
							tierlabel.getChildren().add(deleteButton);
						}
						tierHeaderPanel.getChildren().add(tierlabel);
	
						
						tierCritPanel.getChildren().add(tierHeaderPanel);
					}
					else{
						if(!isViewMode() && !isPrintMode()){
							tierlabel.getChildren().add(dummylabel1);
							tierlabel1.getChildren().add(dummylabel1);
							//	tierlabel.getChildren().add(deleteButton);
						}
	
						tierlabel.getChildren().add(hiddenTierId);
						tierlabel1.getChildren().add(hiddenTierId);
						tierHeaderPanel.getChildren().add(tierlabel);
						if(!isViewMode()){
							tierlabel1.getChildren().add(deleteButton);
						}
						tierHeaderPanel_wclc.getChildren().add(tierlabel1);
						
						tierCritPanel.getChildren().add(tierHeaderPanel);
						tierCritPanel.getChildren().add(tierHeaderPanel_wclc);
						
					}
						
					int sizeOfLevelList = 0;
					if(lvlLineList != null){
						sizeOfLevelList = lvlLineList.size();
					}
					tierLevelPanel = new HtmlPanelGrid();
					tierLevelPanel.setColumns(8);
				//	tierLevelPanel.setColumnClasses("gridColumn25,gridColumn15,gridColumn10,gridColumn5,gridColumn5,gridColumn10,gridColumn25,gridColumn3");
					if(isEditMode()){
					    tierLevelPanel.setColumnClasses("gridColumn20,gridColumn15,gridColumn14,gridColumn6,gridColumn8,gridColumn10,gridColumn20,gridColumn7");  
					}else if(isViewMode()){
					    tierLevelPanel.setColumnClasses("gridColumn16,gridColumn12,gridColumn16,gridColumn5,gridColumn8,gridColumn8,gridColumn17,gridColumn6");
					}
					tierLevelPanel.setWidth("100%");
	
					tierLevelPanel.setBorder(0);
					tierLevelPanel.setCellpadding("1");
					tierLevelPanel.setCellspacing("1");
					tierLevelPanel.setBgcolor("#cccccc");
					//            tierLevelPanel.setRowClasses("dataTableEvenRow");
					rows.delete(0, rows.length());
					for(int i = 0; i < (sizeOfLevelList); i++){
	
						BenefitLevel benefitLevelValues = (BenefitLevel) getLvlLineList()
								.get(i);
	
						if(benefitLevelValues.getTierSysId() == tier
								.getBenefitTierSysId()){
	
							// gets the benefit lines of a benefit level
							List benefitLines = benefitLevelValues
									.getBenefitLines();
							if(benefitLevelValues.isLevelHideStatus())
								rows.append("hiddenFieldLevelDisplay");
							else
								rows.append("dataTableEvenRow");
	
							rows.append(",");
	
							// setting the benefit level values to the panel grid
							setBenefitLevelValuesToTierGrid(i, benefitLevelValues,
									tier, benefitLines.size(), rowNumber,
									lineCount, tierLevelPanel);
	
							// iterating to get the individual benefit lines
							for(int j = 0; j < benefitLines.size(); j++){
								rowNumber++;
	
								// selects an individual benefit line
								BenefitLine benefitLineValues = (BenefitLine) benefitLines
										.get(j);
								if(benefitLineValues.isLineHideStatus())
									rows.append("hiddenFieldDisplay");
								else
									rows.append("dataTableOddRow");
								rows.append(",");
								lineCount = lineCount + 1;
								// sets the benefit lines of a benefit level to the
								// panle grid
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

		}
       
		return tierPanel;
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
	
	public String getPsvlLookupRecords() {

        String criString;
            if(getRequest().getParameter(WebConstants.CRITERIA_STRING) !=null){
                    criString = getRequest().getParameter(WebConstants.CRITERIA_STRING).toString();
                    pbvlDefList = fetchPosibleValuesList(criString);
            }
                    return psvlLookupRecords;
    }

	public void setTierPanel(HtmlPanelGrid tierPanel) {
		this.tierPanel = tierPanel;
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

		// This method gets the values from the benefit definiton List and sets
		// it to the level list and line list
		getValuesFromDefinitonList(benefitDefinitonsList);
		panelForTierPrint = new HtmlPanelGrid();
		panelForTierPrint.setColumns(1);
		panelForTierPrint.setWidth("100%");
		panelForTierPrint.setBorder(0);
		panelForTierPrint.setCellpadding("1");
		panelForTierPrint.setCellspacing("1");
		StringBuffer rows = new StringBuffer();

		// setting values to benefit levels

		int size = 0;
		if (null != tierDefList) {
			size = tierDefList.size();
			SortTiers(tierDefList);
		}

		HtmlPanelGrid tierDefPanel = null;
		HtmlPanelGrid tierCritPanel = null;
		HtmlPanelGrid tierLevelPanel = null;
		BenefitTierDefinition tierDefinition = null;

		List tierList;
		List critList = null;
		BenefitTierCriteria tierCriteria = null;
		// iterating to get the benefit levels
		for (int l = 0; l < size; l++) {
			tierDefinition = (BenefitTierDefinition) getTierDefList().get(l);
			rowNumber++;
			tierDefPanel = new HtmlPanelGrid();
			tierDefPanel.setColumns(1);
			tierDefPanel.setWidth("100%");

			tierDefPanel.setBorder(0);
			tierDefPanel.setCellpadding("3");
			tierDefPanel.setCellspacing("1");

			HtmlOutputLabel defLabel = new HtmlOutputLabel();
			defLabel.setId("defLabel"+RandomStringUtils.randomAlphanumeric(15));
			HtmlOutputText tierDef = new HtmlOutputText();
			tierDef.setValue(tierDefinition.getBenefitTierDefinitionName());
			defLabel.getChildren().add(tierDef);
			tierDefPanel.getChildren().add(defLabel);

			panelForTierPrint.getChildren().add(tierDefPanel);

			tierList = tierDefinition.getBenefitTiers();
			BenefitTier tier = null;
			HtmlOutputText lineDesc = new HtmlOutputText();
			lineDesc.setValue(" ");
			HtmlOutputText lineTerm = new HtmlOutputText();
			lineTerm.setValue("");
			HtmlOutputText lineQualifier = new HtmlOutputText();
			lineQualifier.setValue("");
			HtmlOutputText linePVA = new HtmlOutputText();

			//output text for view
			HtmlOutputText lineBnftValueView = new HtmlOutputText();
			for(int m = 0; m < tierList.size(); m++){
				rows.append("dataTableOddRow");
				tier = (BenefitTier) tierList.get(m);
				critList = tier.getBenefitTierCriteriaList();
				tierCritPanel = new HtmlPanelGrid();
				tierCritPanel.setColumns(1);
				tierCritPanel.setWidth("100%");

				tierCritPanel.setBorder(0);
				tierCritPanel.setCellpadding("3");
				tierCritPanel.setCellspacing("1");
				tierCritPanel.setBgcolor("#cccccc");
				tierCritPanel.setRowClasses("dataTableOddRow");

				tierNo++;
				HtmlOutputLabel tierlabel = new HtmlOutputLabel();
				tierlabel.setId("tierlabel"+RandomStringUtils.randomAlphanumeric(15));
				HtmlOutputText dummylabel = new HtmlOutputText();
				dummylabel.setStyle("width:50px;");
				HtmlOutputText dummylabel1 = new HtmlOutputText();
				dummylabel1.setStyle("width:250px;");

				if(null != critList) {
					for(int k = 0; k < critList.size(); k++) {
						tierlabel.getChildren().add(dummylabel);
						tierCriteria = new BenefitTierCriteria();
						tierCriteria = (BenefitTierCriteria) critList.get(k);
						HtmlOutputText tierCrit = new HtmlOutputText();
						tierCrit.setValue(tierCriteria
								.getBenefitTierCriteriaName()+"* : ");
						tierlabel.getChildren().add(tierCrit);
						if(isViewMode() || isPrintMode()){
							HtmlOutputText critValueView = new HtmlOutputText();
							critValueView.setValue(tierCriteria.getBenefitTierCriteriaValue());
							tierlabel.getChildren().add(critValueView);
						}
					}
				}
			
				tierCritPanel.getChildren().add(tierlabel);
				int sizeOfLevelList = 0;
				if(lvlLineList != null){
					sizeOfLevelList = lvlLineList.size();
				}
				tierLevelPanel = new HtmlPanelGrid();
				tierLevelPanel.setColumns(8);
				tierLevelPanel.setWidth("100%"); 
				//tierLevelPanel.setColumnClasses("gridColumn20,gridColumn10,gridColumn10,gridColumn10,gridColumn10,gridColumn10,gridColumn30,gridColumn0");				
				tierLevelPanel.setColumnClasses("gridColumn22,gridColumn12,gridColumn12,gridColumn11,gridColumn10,gridColumn8,gridColumn25,gridColumn0");
				tierLevelPanel.setBorder(0);
				tierLevelPanel.setCellpadding("3");
				tierLevelPanel.setCellspacing("1");
				tierLevelPanel.setBgcolor("#cccccc");
				tierLevelPanel.setRowClasses("dataTableEvenPrintRow");
				for(int i = 0; i < (sizeOfLevelList); i++){

					BenefitLevel benefitLevelValues = (BenefitLevel) getLvlLineList()
							.get(i);

					if(benefitLevelValues.getTierSysId() == tier
							.getBenefitTierSysId()) {

						rows.append("dataTableEvenPrintRow");

						// gets the benefit lines of a benefit level
						List benefitLines = benefitLevelValues
								.getBenefitLines();
						// setting the benefit level values to the panel grid
						setBenefitLevelValuesToTierGrid(i, benefitLevelValues,
								tier, benefitLines.size(), rowNumber,
								lineCount, tierLevelPanel);

						if(benefitLines.size() != 0)
							rows.append(",");
						// iterating to get the individual benefit lines
						for(int j = 0; j < benefitLines.size(); j++) {
							rowNumber++;

							// selects an individual benefit line
							BenefitLine benefitLineValues = (BenefitLine) benefitLines
									.get(j);

							lineCount = lineCount + 1;
							rows.append("dataTableOddRow");
							if(i < ((size) - 1))
								rows.append(",");
							else if(j < (benefitLines.size() - 1))
								rows.append(",");
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
	/**
	 * @param panelForTierPrint
	 *            The panelForTierPrint to set.
	 */
	public void setPanelForTierPrint(HtmlPanelGrid panelForTierPrint) {
		this.panelForTierPrint = panelForTierPrint;
	}
	
	 private void setBenefitLineValuesToTierGrid(BenefitLevel benefitLevelValues,int j, BenefitLine benefitLineValues, 
	            int i,BenefitTier tier, int lineSize,int lineNum,HtmlPanelGrid tierLevelPanel,boolean isPrintMode) {

	        Logger.logInfo("entered method setBenefitLineValuesToGrid");

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
	        ValueBinding lineIdValBind = FacesContext.getCurrentInstance()
	                .getApplication().createValueBinding(
	                        "#{productBenefitDetailBackingBean.tierLineIdMap["+benefitLineValues.getLineSysId()+"."+tier.getBenefitTierSysId()+"]}");
	        
	        ValueBinding customizedSysIdBind =FacesContext.getCurrentInstance()
	        			.getApplication().createValueBinding(
	        						"#{productBenefitDetailBackingBean.customizedTierSysIdMap['"+benefitLineValues.getLineSysId()+"."+tier.getBenefitTierSysId()+"']}");

	        hiddenLineId.setValueBinding("value", lineIdValBind);
	        hiddenCustomizedSysId.setValueBinding("value",customizedSysIdBind);
	        
	        HtmlInputText lineBnftValue = new HtmlInputText();
	        HtmlOutputText lineBnftValuePrint = new HtmlOutputText();
	        
	      //  lineBnftValue.setReadonly(true);
	        HtmlSelectOneMenu seloneMenuForBnftValue = new HtmlSelectOneMenu();
	        UIComponent object = null;
	        String sysDataType = null;
	        int dataType = 0;
	        String dataTypeId = benefitLineValues.getDataTypeId();
	        if(null != dataTypeId && !"".equals(dataTypeId)){
	            dataType = Integer.parseInt(dataTypeId);
	        }
	        List universeDataTypeList = WPDStringUtil.getUniverseDataTypeList();
	        if(dataType != 0){
	            DataTypeLocateResult dataTypeDetails = null;
	            dataTypeDetails = WPDStringUtil.getDataTypeDetails(
	                    universeDataTypeList, dataType);
	            if(null != dataTypeDetails){
	                sysDataType = dataTypeDetails.getDataTypeDesc().toUpperCase()
	                        .trim();
	            }
	        }
	        if(null != sysDataType){
	            if(sysDataType.equals("BOOLEAN")){
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
	                seloneMenuForBnftValue.setId("lineBnftValue" + j + "_" + i+lineNum+tier.getBenefitTierSysId()+"_tierGrid");
	                object = (HtmlSelectOneMenu) seloneMenuForBnftValue;
	                String keyForMap = formKeyforMap(benefitLevelValues.getLevelId(), benefitLineValues.getLineSysId(), tier.getBenefitTierSysId());
	                keyForMap = "\"".concat(keyForMap).concat("\"");
	                // set the value to the map
	                ValueBinding valueItem = FacesContext.getCurrentInstance()
	                        .getApplication().createValueBinding(
	                                "#{productBenefitDetailBackingBean.tierLineValueMap["
	                                        + keyForMap
	                                        + "]}");
	                seloneMenuForBnftValue.setValueBinding("value", valueItem);
	            } else {
	            	if(!benefitLineValues.isLineHideStatus()){
	                lineBnftValue.setSize(6);
	                lineBnftValue.setId("lineBnftValue" + j + "_" + i+lineNum + tier.getBenefitTierSysId()+"_tierGrid");
	                lineBnftValue.setValue(benefitLineValues.getBenefitValue());
	                lineBnftValuePrint.setValue(benefitLineValues.getBenefitValue());
	                if(!benefitLineValues.getDataTypeDesc().equalsIgnoreCase(
	                        "String")) {
	                }
	                lineBnftValue.setTitle("BenefitValue"
	                        + benefitLineValues.getDataTypeDesc());
	                String keyForMap = formKeyforMap(benefitLevelValues.getLevelId(), benefitLineValues.getLineSysId(), tier.getBenefitTierSysId());
	                keyForMap = "\"".concat(keyForMap).concat("\"");
	                ValueBinding valueItem = FacesContext.getCurrentInstance()
	                        .getApplication().createValueBinding(
	                                "#{productBenefitDetailBackingBean.tierLineValueMap["
	                                        + keyForMap
	                                        + "]}");
	                lineBnftValue.setValueBinding("value", valueItem);
	                lineBnftValue.setStyleClass("formInputField");
	                lineBnftValue.setStyle("width:50px;");
	                lineBnftValuePrint.setStyle("width:50px;");
	            }else{
	            	//lineBnftHideValue.setSize(10);
	            	 lineBnftValue.setSize(6);
	                 lineBnftValue.setId("lineBnftValue" + j + "_" + i+lineNum + tier.getBenefitTierSysId()+"_tierGrid");
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
	                         "#{productBenefitDetailBackingBean.tierLineValueMap["
	                                 + keyForMap
	                                 + "]}");
	         lineBnftValue.setValueBinding("value", valueItem);
	         lineBnftValue.setStyleClass("formInputField");
	         lineBnftValue.setStyle("width:50px;");
	         lineBnftValuePrint.setStyle("width:50px;");
	            }
	        }
	        }
	        lineBnftValueView.setStyle("width:50px;");
	        lineBnftValueView.setId("lineBnftValueView" + j + "_" + i+lineNum + tier.getBenefitTierSysId());
	        lineBnftValueView.setValue(WPDStringUtil.spaceSeparatedString(benefitLineValues.getBenefitValue(),8));
	        //lineBnftValueView.setStyleClass("formInputFieldReadOnly");
	        //lineBnftValueView.setStyle("width:50px;");
	        HtmlOutputText lineEmptyString = new HtmlOutputText();
	        lineEmptyString.setId("lineEmptyString" + benefitLineValues.getLineSysId()+tier.getBenefitTierSysId()+lineNum);
	        lineEmptyString.setValue(" ");

	        HtmlOutputLabel lblBnftValue = new HtmlOutputLabel();
	        lblBnftValue.setId("lblBnftValue"+RandomStringUtils.randomAlphanumeric(15));
	        //lblBnftValue.setId("lblBnftValue" + j + "_" + i+lineNum + tier.getBenefitTierSysId());
	        lblBnftValue.setFor("lineBnftValue" + j + "_" + i);
	        if(null != sysDataType){
	            if (sysDataType.equals("DOLLAR")) {
	                if(!WebConstants.MNDT_TYPE.equals(super
	                        .getProductTypeFromSession())){
	                    if(!isViewMode()){
	                        //lblBnftValue.getChildren().add(lineDataType);
	                    	//if(isBenefitDisplayFlag())
	                         if(isPrintMode){
	                    	 	lblBnftValue.getChildren().add(lineBnftValuePrint);
	                    	 }else
	                         lblBnftValue.getChildren().add(lineBnftValue);
	                    	//else
	                    		//lblBnftValue.getChildren().add(lineBnftValueView);
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                    }else{
	                        //lblBnftValue.getChildren().add(lineDataType);
	                        lblBnftValue.getChildren().add(lineBnftValueView);
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                    }
	                }else{
	                    //lblBnftValue.getChildren().add(lineDataType);
	                    lblBnftValue.getChildren().add(lineBnftValueView);
	                    lblBnftValue.getChildren().add(lineEmptyString);
	                }
	            }else if(sysDataType.equals("PERCENTAGE")){
	                if(!WebConstants.MNDT_TYPE.equals(super
	                        .getProductTypeFromSession())) {
	                    if (!isViewMode()) {
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        //if(isBenefitDisplayFlag())
	                        	if(isPrintMode){
	                        		lblBnftValue.getChildren().add(lineBnftValuePrint);
	                        	}else lblBnftValue.getChildren().add(lineBnftValue);
	                        //else
	                        	//lblBnftValue.getChildren().add(lineBnftValueView);
	                        //lblBnftValue.getChildren().add(lineDataType);
	                    }else{
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        lblBnftValue.getChildren().add(lineBnftValueView);
	                        //lblBnftValue.getChildren().add(lineDataType);

	                    }
	                }else{
	                    lblBnftValue.getChildren().add(lineEmptyString);
	                    lblBnftValue.getChildren().add(lineBnftValueView);
	                    //lblBnftValue.getChildren().add(lineDataType);
	                }
	            }else if(sysDataType.equals("STRING")){
	                if(!WebConstants.MNDT_TYPE.equals(super
	                        .getProductTypeFromSession())){
	                    if(!isViewMode()){
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        //if(isBenefitDisplayFlag())
	                        	if(isPrintMode){
	                        		lblBnftValue.getChildren().add(lineBnftValuePrint);
	                        	}else lblBnftValue.getChildren().add(lineBnftValue);
	                        //else
	                        	//lblBnftValue.getChildren().add(lineBnftValueView);
	                    }else{
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        lblBnftValue.getChildren().add(lineBnftValueView);
	                    }
	                }else{
	                    lblBnftValue.getChildren().add(lineEmptyString);
	                    lblBnftValue.getChildren().add(lineBnftValueView);
	                }
	            } else if (sysDataType.equals("BOOLEAN")) {
	                if (!WebConstants.MNDT_TYPE.equals(super
	                        .getProductTypeFromSession())) {
	                    if (!isViewMode()&& !isPrintMode()) {
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        lblBnftValue.getChildren().add(object);
	                        //lblBnftValue.getChildren().add(lineDataType);
	                    }else{
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        lblBnftValue.getChildren().add(lineBnftValueView);
	                    }
	                }else{
	                    lblBnftValue.getChildren().add(lineEmptyString);
	                    lblBnftValue.getChildren().add(lineBnftValueView);
	                }
	            }else{
	                if(!WebConstants.MNDT_TYPE.equals(super
	                        .getProductTypeFromSession())){
	                    if(!isViewMode()){
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        	if(isPrintMode){
	                        		lblBnftValue.getChildren().add(lineBnftValuePrint);	                     
	                        	}else lblBnftValue.getChildren().add(lineBnftValue);
	                    }else{
	                        lblBnftValue.getChildren().add(lineEmptyString);
	                        lblBnftValue.getChildren().add(lineBnftValueView);
	                    }
	                }else{
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
	        
	        //lblRefAndNotes.setId("lblRefNotes" + j +"_"+ i+tier.getBenefitTierSysId()+lineNum);
	        lblBnftValue.setId("lblBnftValue"+RandomStringUtils.randomAlphanumeric(15));
	        //lblBnftValue.setId("lblRefAndNotes" + j + "_" + i+tier.getBenefitTierSysId()+lineNum);
	        lblBnftValue.setFor("lblRefAndNotes" + j + "_" + i);

	        HtmlOutputLabel lblNotesImage = new HtmlOutputLabel();
	        lblNotesImage.setId("lblNotesImage"+RandomStringUtils.randomAlphanumeric(15));
	        //lblNotesImage.setId("lblNotesImage" + j + "_" + i+lineNum+tier.getBenefitTierSysId());
	        lblNotesImage.setFor("lblNotesImage" + j + "_" + i);

	        HtmlGraphicImage noteImage = new HtmlGraphicImage();
	        
	        noteImage.setUrl("../../images/notes_exist.gif");
	        noteImage.setStyle("cursor:hand;");
	        noteImage.setId("noteImage" + j + "_" + i+lineNum+tier.getBenefitTierSysId());
	               
			HtmlInputHidden hiddenNotesStatus = new HtmlInputHidden();
			hiddenNotesStatus.setId("hiddenNotesStatus" + j + "_" + i+lineNum + tier.getBenefitTierSysId());
			hiddenNotesStatus.setValue("");
	        HtmlCommandButton notesButton = new HtmlCommandButton();
	        notesButton.setValue("NotesButton");
	       if(benefitLineValues.getBnftLineNotesExist().equals("Y"))
	            notesButton.setImage("../../images/notes_exist.gif");
	        else
	            notesButton.setImage("../../images/page.gif");
	        notesButton.setTitle("Note");
	        notesButton.setStyle("border:0;");
	        notesButton.setAlt("Notes");
	        notesButton.setId("notesButton" + j + "_" + i+lineNum+tier.getBenefitTierSysId());
	        //notesButton.setOnclick("ewpdModalWindow_ewpd('productBenefitLevelNotesOverridePopup.jsp?parentEntityType=ATTACHPRODUCT&lookUpAction=4&secondaryEntityId="
	        // + benefitLineValues.getLineSysId() + "&temp="+Math.random()+"' ,
	        // 'dummyDiv', 'benefitDefinitionForm:hidden1',2,1);return false;");

	        // lgnd data type
	        HtmlOutputText lgndDataType = new HtmlOutputText();
	        lgndDataType.setValue(benefitLineValues.getDataTypeLegend());
	        lgndDataType.setId("lgndDataType" + j + "_" + i+lineNum+tier.getBenefitTierSysId());
	        
	        if(!isViewMode()) {
	            notesButton.setOnclick("getUrlForTier(" + benefitLineValues.getLineSysId()
	                    + ',' + j + ',' + i + ','+benefitLevelValues.getTierSysId()+','+lineNum+ ");return false;");
	        }else {
	        	notesButton.setOnclick("getUrlForTier(" + benefitLineValues.getLineSysId()
	                    + ',' + super.getBenefitComponentIdFromSession() + ',' + super.getProductKeyFromSession()
						+ ','+benefitLevelValues.getTierSysId()+");return false;");
	        }
	        lblRefAndNotes.getChildren().add(lineReference);
	        if(!isPrintMode()) {	           
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
	            if(!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())){
	            	tierLevelPanel.getChildren().add(lblNotesImage);
	                
	            }
	        
	    }
	 
	 /**
	     * This method sets the benefitLevelValues to the PanelGrid
	     * 
	     * @param i
	     * @param benefitLevelValues
	     */
	    private void setBenefitLevelValuesToTierGrid(int i,
	            BenefitLevel benefitLevelValues, BenefitTier tier, int lineSize, int rowNum,int lineCount, HtmlPanelGrid tierLevelPanel) {
	    	
	        Logger.logInfo("entered method setBenefitLevelValuesToTierGrid");    
	        //Description Start
	        HtmlInputText levelDescInputText = new HtmlInputText();
	        HtmlOutputLabel lblDesc = new HtmlOutputLabel();
	        lblDesc.setId("lblDesc"+RandomStringUtils.randomAlphanumeric(15));
	        HtmlInputHidden hiddenForDesc = new HtmlInputHidden();
	        HtmlOutputText levelDesc = new HtmlOutputText();
	        if (null != benefitLevelValues.getLevelDesc()) {
	        	String desc = null;
	        	String description = benefitLevelValues.getLevelDesc().trim();
	        	if(isEditMode()){
		        	if(description.length()>22){
		            	String[] strTokenizerArr = description.split(" ");
		            	for(int num=0;num<strTokenizerArr.length;num++){
		            		if(strTokenizerArr[num].length()>22){
		            			strTokenizerArr[num] = strTokenizerArr[num].substring(0,22)+" "+strTokenizerArr[num].substring(22);
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
	        	}else if(isViewMode() && isPrintMode()){
		        	if(description.length()>19){
		            	String[] strTokenizerArr = description.split(" ");
		            	for(int num=0;num<strTokenizerArr.length;num++){
		            		if(strTokenizerArr[num].length()>21){
		            			strTokenizerArr[num] = strTokenizerArr[num].substring(0,21)+" "+strTokenizerArr[num].substring(21);
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
	        	}else if(isViewMode()){
		        	if(description.length()>14){
		            	String[] strTokenizerArr = description.split(" ");
		            	for(int num=0;num<strTokenizerArr.length;num++){
		            		if(strTokenizerArr[num].length()>28){
		            			strTokenizerArr[num] = strTokenizerArr[num].substring(0,14)+" "+
		            				strTokenizerArr[num].substring(14,28)+" "+strTokenizerArr[num].substring(28);
		            		}else if(strTokenizerArr[num].length()>14){
		            			strTokenizerArr[num] = strTokenizerArr[num].substring(0,14)+" "+strTokenizerArr[num].substring(14);
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
	            levelDesc.setValue(description);
	        	levelDescInputText.setValue(benefitLevelValues.getLevelDesc().trim());
	            hiddenForDesc.setValue(benefitLevelValues.getLevelDesc().trim());
	        } else {
	            levelDesc.setValue(WebConstants.EMPTY_STRING);
	            levelDescInputText.setValue(WebConstants.EMPTY_STRING);
	            hiddenForDesc.setValue(WebConstants.EMPTY_STRING);
	        }
	        levelDesc.setId("tierLevelDescription" + i);        
	        hiddenForDesc.setId("levelHidDescTier" + i);
	        ValueBinding hiddenDescOutputTxt = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productBenefitDetailBackingBean.oldDescOutputTxtTier['"+ benefitLevelValues.getLevelId()+":"+tier.getBenefitTierSysId()+"']}");        
	        hiddenForDesc.setValueBinding("value",hiddenDescOutputTxt);        
	        levelDescInputText.setStyleClass("formInputField");
			levelDescInputText.setId("levelDescInputTextTier" + i);
			levelDescInputText.setStyle("width:125px;display:none");
			levelDescInputText.setMaxlength(32);
			//Binding the value for description text
			ValueBinding hiddenDescription = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productBenefitDetailBackingBean.dataHiddenValDescTier['"+ benefitLevelValues.getLevelId()+":"+tier.getBenefitTierSysId()+"']}");        
	        levelDescInputText.setValueBinding("value", hiddenDescription);
			//DESCRIPTION FIX START
	        if(isEditMode() && !isViewMode() && !isPrintMode()){
				if (!BusinessUtil.isSystemGeneratedDescription(benefitLevelValues
						.getLevelDesc(), benefitLevelValues.getTermDesc(),
						benefitLevelValues.getQualifierDesc(), benefitLevelValues.getFrequencyId())){
					if((new Integer(benefitLevelValues.getFrequencyId()).toString().trim()).equalsIgnoreCase(benefitLevelValues.getLowerLevelFrequencyValue())){
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
			HtmlInputHidden hiddenTierOutputDescription = new HtmlInputHidden();
			hiddenTierOutputDescription.setId("hidTierOutputValDesc" + i);        
			hiddenTierOutputDescription.setValue(benefitLevelValues.getLevelDesc().trim());
	        ValueBinding hidTierOutputValLevelDesc = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productBenefitDetailBackingBean.dataHiddenTierOutputValDesc['"+ benefitLevelValues.getLevelId()+":"+tier.getBenefitTierSysId()+"']}");
	        hiddenTierOutputDescription.setValueBinding("value",hidTierOutputValLevelDesc);
			//FIX
	        //START setting the Lower level description in the hidden 
			HtmlInputHidden hiddenTierLowerLevelDescription = new HtmlInputHidden();
			hiddenTierLowerLevelDescription.setId("hidTierLowerLevelValDesc" + i);        
			hiddenTierLowerLevelDescription.setValue(benefitLevelValues.getLowerLevelDescValue().trim());
	        ValueBinding hidTierLowerLevelValDesc = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productBenefitDetailBackingBean.dataHiddenTierLowerLevelValDesc['"+ benefitLevelValues.getLevelId()+":"+tier.getBenefitTierSysId()+"']}");
	        hiddenTierLowerLevelDescription.setValueBinding("value",hidTierLowerLevelValDesc);
			//END Lower level description in the hidden 
	        
	        lblDesc.getChildren().add(levelDesc);
	        if(isEditMode() && !isViewMode() && !isPrintMode())
	        	lblDesc.getChildren().add(levelDescInputText);
	        lblDesc.getChildren().add(hiddenForDesc);
	        lblDesc.getChildren().add(hiddenTierOutputDescription);
	        lblDesc.getChildren().add(hiddenTierLowerLevelDescription);
	        //Description End
	        //Term Start
	        HtmlOutputText levelTerm = new HtmlOutputText();
	        HtmlInputHidden hiddenForTerm = new HtmlInputHidden();
	        HtmlOutputLabel lblTerm = new HtmlOutputLabel();
	        lblTerm.setId("lblTerm"+RandomStringUtils.randomAlphanumeric(15));
	        hiddenForTerm.setId("hiddenTermTier" + i);
	        levelTerm.setId("levelTerm" + benefitLevelValues.getLevelId()+i+rowNum+tier.getBenefitTierSysId());
	        if (null != benefitLevelValues.getTermDesc()) {
	            levelTerm.setValue(benefitLevelValues.getTermDesc().trim().replaceAll(",",", "));
	            hiddenForTerm.setValue(benefitLevelValues.getTermDesc().trim().replaceAll(",",", "));
	        } else {
	            levelTerm.setValue(WebConstants.EMPTY_STRING);
	            hiddenForTerm.setValue(WebConstants.EMPTY_STRING);
	        }
	        ValueBinding hiddenTermDesc = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productBenefitDetailBackingBean.dataHiddenValTermTier["+ benefitLevelValues.getLevelId() +"]}");
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
	        ValueBinding hidddenQualifier = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productBenefitDetailBackingBean.dataHiddenValQualifierTier["+ benefitLevelValues.getLevelId() +"]}");
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
						"#{productBenefitDetailBackingBean.lineFreqValueMapTier['"
								+ benefitLevelValues.getLevelId()+":"+tier.getBenefitTierSysId() +"']}");
	        	levelFrequencyInputTxt.setValueBinding("value",levelFreqValueBind);
	        	//Hidden Frequency is added to hold the frequency value
	        	HtmlInputHidden hiddenLevelFreqValue = new HtmlInputHidden();
				hiddenLevelFreqValue.setId("hiddenLevelFreqValueTier"+ i);
				hiddenLevelFreqValue.setValue(new Integer(benefitLevelValues.getFrequencyId()).toString().trim());
				ValueBinding valForhiddenLevelFreq = FacesContext
							.getCurrentInstance().getApplication().createValueBinding(
									"#{productBenefitDetailBackingBean.hiddenLineFreqValueMapTier['"
											+ benefitLevelValues.getLevelId()+":"+tier.getBenefitTierSysId()+"']}");
				hiddenLevelFreqValue.setValueBinding("value",
							valForhiddenLevelFreq);
	        	levelFrequencyInputTxt.setOnchange("return descriptionChangeForTier(this)");	        	
	        	levelFrequencyInputTxt.setDisabled(benefitLevelValues.isLevelHideStatus());
	        	//Set the frequency value to the output text 
				levelFrequencyOutputTxt.setId("FrequencyTier" +i);
	        	levelFrequencyOutputTxt.setValue(new Integer(benefitLevelValues.getFrequencyId()).toString().trim());
	        	//Check if it is for view or edit page
	        	 if (!isViewMode() && !(isPrintMode())) {
	        	 	//Sets the input text to the frequency qualifier label
	        	 	lblFreqQualPage.getChildren().add(levelFrequencyInputTxt);	        	
	        	 }else{
		            //Sets the output text to the frequency qualifier label
	        	 	lblFreqQualPage.getChildren().add(levelFrequencyOutputTxt);
	        	 }      
                //START Hidden Lower level frequency value
	     		HtmlInputHidden hiddenTierLowerLevelFreqValue = new HtmlInputHidden();
	     		hiddenTierLowerLevelFreqValue.setId("hiddenTierLowerLevelFreqValue"+ i);
	     		hiddenTierLowerLevelFreqValue.setValue(benefitLevelValues.getLowerLevelFrequencyValue());
	     		ValueBinding valForTierhiddenLowerLevelFreq = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productBenefitDetailBackingBean.hiddenTierLowerLevelFreqValueMap['"+ benefitLevelValues.getLevelId()+":"+tier.getBenefitTierSysId()+"']}");
	     		hiddenTierLowerLevelFreqValue.setValueBinding("value",valForTierhiddenLowerLevelFreq);
	     		//END Hidden Lower level frequency value
	        	 lblFreqQualPage.getChildren().add(hiddenLevelFreqValue);
	        	 lblFreqQualPage.getChildren().add(hiddenTierLowerLevelFreqValue);
	        }
	        lblFreqQualPage.getChildren().add(levelQualifier);
	        lblFreqQualPage.getChildren().add(hiddenForQualifier);
	        //Frequency End
	        //CARS END	        
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
	        ValueBinding levelIdValBind = FacesContext.getCurrentInstance()
	                .getApplication().createValueBinding(
	                        "#{productBenefitDetailBackingBean.levelTierIdMap[" +benefitLevelValues.getLevelId()+ "."+tier.getBenefitTierSysId()+"]}");
	        hiddenLevelId.setValueBinding("value", levelIdValBind);
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
	        lblImage.setId("lblImage"+RandomStringUtils.randomAlphanumeric(15));
	        //lblImage.setFor("levelDesc" + i);
	        lblImage.setId("lblImage" + i+benefitLevelValues.getLevelId()+tier.getBenefitTierSysId()+lineCount);
	
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
	        	tierLevelPanel.getChildren().add(lblDesc);
	        	tierLevelPanel.getChildren().add(lblTerm);
	        	tierLevelPanel.getChildren().add(lblFreqQualPage);
	        	tierLevelPanel.getChildren().add(levelPVA);
	        	tierLevelPanel.getChildren().add(dummyText);
	        	tierLevelPanel.getChildren().add(levelBnftValue);
	        	tierLevelPanel.getChildren().add(levelReference);
	             if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
	            	 tierLevelPanel.getChildren().add(lineImage);	                 
	             }	        
	    }


	    public String deleteBenefitTier(){
	    	 ProductTierDeleteRequest benefitTierDeleteRequest = (ProductTierDeleteRequest) this
             .getServiceRequest(ServiceManager.DELETE_BENEFIT_TIER_REQUEST);
	    	 benefitTierDeleteRequest.setProductTierSysId(tierIdToDelete);
	    	 
	    	 //amjith
	    	 benefitTierDeleteRequest.setBenefitComponentSysId(getProductSessionObject().getBenefitComponentId());
	    	 benefitTierDeleteRequest.setProductKeyObject(getProductSessionObject().getProductKeyObject());
	    	 //benefitTierDeleteRequest.setProductSysId(getProductSessionObject().getProductKeyObject().getProductId());
	    	 
	    	 ProductTierDeleteResponse productTierDeleteResponse = (ProductTierDeleteResponse)executeService(benefitTierDeleteRequest);
	    	 if(null != productTierDeleteResponse && null != productTierDeleteResponse.getMessages()){
	    		//added the message from the response to the class variable --Defect fix for WAS7 Migration
				informationMessageToDisplayOnPage = productTierDeleteResponse.getMessages();
	    		 messages = productTierDeleteResponse.getMessages();
	    	 }
	    	return "benefitDefinitionsPage";
	    	
	    }
	    
		/***
		 * Deletes a Level from all Tiers
		 * @param 
		 * 
		 */
		
	    public String deleteLevelFromBenefitTier(){
	    	DeleteLevelFromTierRequest deleteLevelFromTierRequest = (DeleteLevelFromTierRequest) this
            .getServiceRequest(ServiceManager.DELETE_LEVEL_FROM_TIER_REQUEST);
	    	
	    	if (deleteTierLevelId != null && deleteTierLevelId!="")
	    		deleteLevelFromTierRequest.setLevelSysId(Integer.parseInt(deleteTierLevelId));
	    	
	    	
	    	deleteLevelFromTierRequest.setBenefitComponentSysId(getProductSessionObject().getBenefitComponentId());
	    	deleteLevelFromTierRequest.setBenefitSysId(getProductSessionObject().getBenefitId());
	    	deleteLevelFromTierRequest.setProductKeyObject(getProductSessionObject().getProductKeyObject());
	    	deleteLevelFromTierRequest.setProductSysId(getProductSessionObject().getProductKeyObject().getProductId());
	    	
			
	    	DeleteLevelFromTierResponse deleteLevelFromTierResponse = (DeleteLevelFromTierResponse)executeService(deleteLevelFromTierRequest);
	    	 if(null != deleteLevelFromTierResponse && null != deleteLevelFromTierResponse.getMessages()){
	    		 messages = deleteLevelFromTierResponse.getMessages();
	    	 }
	    	
	    	return "benefitDefinitionsPage";
	    	
	    }	    
	    
	    public boolean isTierd(){
	    	if(null != getTierCriteriaMap() && null != getTierLineValueMap() 
            		&& null != getProductSessionObject().getBenefitTierDefinitionList()
            			&& null != getProductSessionObject().getBenefitTierLevelList()) 
	    		return true;
	    	else
	    		return false;
	    }
	     
	    
	public Map getCustomizedSysIdMap() {
		return customizedSysIdMap;
	}


	public void setCustomizedSysIdMap(Map customizedSysIdMap) {
		this.customizedSysIdMap = customizedSysIdMap;
	}


	public Map getHiddenLevelCheckMap() {
		return hiddenLevelCheckMap;
	}


	public void setHiddenLevelCheckMap(Map hiddenLevelCheckMap) {
		this.hiddenLevelCheckMap = hiddenLevelCheckMap;
	}


	public Map getHiddenLineCheckMap() {
		return hiddenLineCheckMap;
	}


	public void setHiddenLineCheckMap(Map hiddenLineCheckMap) {
		this.hiddenLineCheckMap = hiddenLineCheckMap;
	}


	public Map getLevelVisibleMap() {
		return levelVisibleMap;
	}


	public void setLevelVisibleMap(Map levelVisibleMap) {
		this.levelVisibleMap = levelVisibleMap;
	}


	public Map getLineIdMap() {
		return lineIdMap;
	}


	public void setLineIdMap(Map lineIdMap) {
		this.lineIdMap = lineIdMap;
	}


	public Map getTierLineValueMap() {
		return tierLineValueMap;
	}


	public void setTierLineValueMap(Map tierLineValueMap) {
		this.tierLineValueMap = tierLineValueMap;
	}


	public Map getTierCriteriaMap() {
		return tierCriteriaMap;
	}


	public void setTierCriteriaMap(Map tierCriteriaMap) {
		this.tierCriteriaMap = tierCriteriaMap;
	}


	public Map getTierLineIdMap() {
		return tierLineIdMap;
	}


	public void setTierLineIdMap(Map tierLineIdMap) {
		this.tierLineIdMap = tierLineIdMap;
	}


	public Map getCustomizedTierSysIdMap() {
		return customizedTierSysIdMap;
	}


	public void setCustomizedTierSysIdMap(Map customizedTierSysIdMap) {
		this.customizedTierSysIdMap = customizedTierSysIdMap;
	}


	public Map getLevelTierIdMap() {
		return levelTierIdMap;
	}


	public void setLevelTierIdMap(Map levelTierIdMap) {
		this.levelTierIdMap = levelTierIdMap;
	}


	public Map getTierDefMap() {
		return tierDefMap;
	}


	public void setTierDefMap(Map tierDefMap) {
		this.tierDefMap = tierDefMap;
	}


	


	public List getTierDefList() {
		return tierDefList;
	}


	public void setTierDefList(List tierDefList) {
		this.tierDefList = tierDefList;
	}


	public List getLvlLineList() {
		return lvlLineList;
	}


	public void setLvlLineList(List lvlLineList) {
		this.lvlLineList = lvlLineList;
	}


	public Map getTierIdMap() {
		return tierIdMap;
	}


	public void setTierIdMap(Map tierIdMap) {
		this.tierIdMap = tierIdMap;
	}


	public List getModifiedDefList() {
		return modifiedDefList;
	}


	public void setModifiedDefList(List modifiedDefList) {
		this.modifiedDefList = modifiedDefList;
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
	 * @return Returns the tierSysId.
	 */
	public int getTierSysId() {
		return tierSysId;
	}
	/**
	 * @param tierSysId The tierSysId to set.
	 */
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
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
	 * @return Returns the benefitTierDefinitionsList from DB.
	 */
	private List getTierDefinitionsList(List benefitDefinitions) {
		
		if(null!=benefitDefinitions){
			TierDefinitionRetrieveRequest request = getTierDefinitionRetrieveRequest(benefitDefinitions);
			TierDefinitionRetrieveResponse response = (TierDefinitionRetrieveResponse) executeService(request);
			if (null != response) {
				benefitTierDefinitionsList = response.getBenefitTierDefinitonsList();
			}
		}
			return benefitTierDefinitionsList;
	}
	/**
	 * @param benefitTierDefinitionsList The benefitTierDefinitionsList to set.
	 */
	public void setBenefitTierDefinitionsList(List benefitTierDefinitionsList) {
		this.benefitTierDefinitionsList = benefitTierDefinitionsList;
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
	private void SortTiers(List benefitDefinitonsList) {
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
	
	/**
	 * The method gets the Header panel for the Tiered BeneftLevel Panel for Print
	 * @return
	 */
	public HtmlPanelGrid getTierHeaderPanelForPrint() {

        Logger.logInfo("entered method getTierHeaderPanelForPrint");

        //sets the string which contains the levels to delete to null
        if (null != this.levelsToDelete) {
            this.levelsToDelete = null;
        }

        tierHeaderPanel = new HtmlPanelGrid();
        //        headerPanel.setWidth("850");

        tierHeaderPanel.setColumns(7);
        tierHeaderPanel.setWidth("100%");
        //tierHeaderPanel.setColumnClasses("gridColumn20,gridColumn15,gridColumn10,gridColumn8,gridColumn10,gridColumn10,gridColumn20,gridColumn7");
        tierHeaderPanel.setColumnClasses("gridColumn22,gridColumn12,gridColumn12,gridColumn11,gridColumn10,gridColumn7,gridColumn26");
        
        tierHeaderPanel.setBorder(0);
        tierHeaderPanel.setCellpadding("3");
        tierHeaderPanel.setCellspacing("1");
        tierHeaderPanel.setBgcolor("#cccccc");
        tierHeaderPanel.setId("tierHeaderPanelForPrintId"); 
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

        headerText3.setValue("Frequency - Qualifier");
        headerText3.setId("qualifier1");

        headerText4.setValue("PVA");
        headerText4.setId("pva1");

        headerText5.setValue("Benefit Value");
        headerText5.setId("bnftValue1");

        headerText9.setValue("Format");
        headerText9.setId("dataTypeLgnd1");

        headerText6.setValue("Reference");
        headerText6.setId("ref1");
        
        //headerText8.setValue("Notes");
        headerText8.setId("note1");
        
        if (!WebConstants.MNDT_TYPE.equals(super.getProductTypeFromSession())) {
            headerText7.setValue(" ");
        }
        tierHeaderPanel.setStyleClass("dataTableHeader");
        tierHeaderPanel.getChildren().add(headerText1);
        tierHeaderPanel.getChildren().add(headerText2);
        tierHeaderPanel.getChildren().add(headerText3);
        tierHeaderPanel.getChildren().add(headerText4);
        tierHeaderPanel.getChildren().add(headerText9);
        tierHeaderPanel.getChildren().add(headerText5);
        tierHeaderPanel.getChildren().add(headerText6);
        //tierHeaderPanel.getChildren().add(headerText8);
        
        return tierHeaderPanel;
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
	 * @return Returns the lineFreqValueMap.
	 */
	public HashMap getLineFreqValueMap() {
		return lineFreqValueMap;
	}
	/**
	 * @param lineFreqValueMap The lineFreqValueMap to set.
	 */
	public void setLineFreqValueMap(HashMap lineFreqValueMap) {
		this.lineFreqValueMap = lineFreqValueMap;
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
	 * @return Returns the lvlDescMessages.
	 */
	public List getLvlDescMessages() {
		return lvlDescMessages;
	}
	/**
	 * @param lvlDescMessages The lvlDescMessages to set.
	 */
	public void setLvlDescMessages(List lvlDescMessages) {
		this.lvlDescMessages = lvlDescMessages;
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
	public HashMap getDataHiddenOutputValDesc() {
		return dataHiddenOutputValDesc;
	}
	/**
	 * @param dataHiddenOutputValDesc The dataHiddenOutputValDesc to set.
	 */
	public void setDataHiddenOutputValDesc(HashMap dataHiddenOutputValDesc) {
		this.dataHiddenOutputValDesc = dataHiddenOutputValDesc;
	}
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
	 * @return Returns the dataHiddenTierLowerLevelValDesc.
	 */
	public HashMap getDataHiddenTierLowerLevelValDesc() {
		return dataHiddenTierLowerLevelValDesc;
	}
	/**
	 * @param dataHiddenTierLowerLevelValDesc The dataHiddenTierLowerLevelValDesc to set.
	 */
	public void setDataHiddenTierLowerLevelValDesc(
			HashMap dataHiddenTierLowerLevelValDesc) {
		this.dataHiddenTierLowerLevelValDesc = dataHiddenTierLowerLevelValDesc;
	}
	/**
	 * @return Returns the dataHiddenTierOutputValDesc.
	 */
	public HashMap getDataHiddenTierOutputValDesc() {
		return dataHiddenTierOutputValDesc;
	}
	/**
	 * @param dataHiddenTierOutputValDesc The dataHiddenTierOutputValDesc to set.
	 */
	public void setDataHiddenTierOutputValDesc(
			HashMap dataHiddenTierOutputValDesc) {
		this.dataHiddenTierOutputValDesc = dataHiddenTierOutputValDesc;
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
}