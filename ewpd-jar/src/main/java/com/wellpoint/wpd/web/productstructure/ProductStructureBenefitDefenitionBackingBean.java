/*
 * ProductStructureBenefitDefinitionBackingBean.java
 *  © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.productstructure;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

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
import com.wellpoint.wpd.business.framework.bo.StateFactory;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLevel;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.override.benefit.vo.ProductStructureBenefitCustomizedVO;
import com.wellpoint.wpd.common.productstructure.request.ProductStructureNotesRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveBenefitDefenitionRequest;
import com.wellpoint.wpd.common.productstructure.request.RetrieveComponentFromTreeRequest;
import com.wellpoint.wpd.common.productstructure.request.SaveProductStructureBenefitDefinitionRequest;
import com.wellpoint.wpd.common.productstructure.response.ProductStructureNotesResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveBenefitDefenitionResponse;
import com.wellpoint.wpd.common.productstructure.response.RetrieveComponentFromTreeResponse;
import com.wellpoint.wpd.common.productstructure.response.SaveProductStructureBenefitDefinitionResponse;
import com.wellpoint.wpd.common.productstructure.vo.BenefitDefinitionPrintVO;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO;
import com.wellpoint.wpd.common.standardbenefit.bo.UniverseBO;
import com.wellpoint.wpd.common.standardbenefit.request.GetBenefitTierDefinitionForDetailPrintRequest;
import com.wellpoint.wpd.common.standardbenefit.request.GetBenefitTierDefinitionRequest;
import com.wellpoint.wpd.common.standardbenefit.response.GetBenefitTierDefinitionResponse;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

public class ProductStructureBenefitDefenitionBackingBean extends
        ProductStructureBackingBean {

    private HtmlPanelGrid panel = new HtmlPanelGrid();

    private Map benefitValueMap = new LinkedHashMap();

    private ProductStructureVO productStructureVO;

    private HtmlPanelGrid headerPanel = new HtmlPanelGrid();

    private Map datafieldMap = new LinkedHashMap();

    private Map datafieldMapForREF = new LinkedHashMap();

    private String lob;

    private String entity;

    private String group;

    private String effectiveDate;

    private String expiryDate;

    private String minorHeading;

    private String description;

    private String createdBy;

    private Date creationDate;

    private String updatedBy;

    private Date updatedDate;

    private String term;

    private String qualifier;

    private String providerArrangement;

    private String dataType;

    private List benefitDefinitonsList = null;

    private List benefitLevelList;

    private List benefitLineList;

    private List printBenftDefnList;

    private List deleteLevelList;

    private String levelsToDelete;

    private String benefitType;
    
    private String benefitCategory;

    private String mandateType;

    private String stateCode;

    private String ruleId;
    
    private String ruleType;

    private Map levelIdMap = new LinkedHashMap();

    private int standardBenefitKey;

    private List validationMessages = new ArrayList(7);

    private HashMap lineIdMap = new HashMap();
    
    private List associatedNotesList = null;
    
    private List benefitLineNotes = null;
    
    private String dummyVar;
    
    private boolean securityAccess;
    
    private String benefitLevelHideFlag;
    
    private String benefitLineHideFlag;
    
    private Map checkBoxMap = new LinkedHashMap();//for line visible check box
    
    private HashMap levelVisibleMap = new HashMap(); //for level visible check box
    
    private boolean showHidden = false; 	//for show All check box
    
    private HashMap customizedSysIdMap = new HashMap(); // for customized sys id
    
    private boolean benefitEditStatusFlag = false;//for making general benefits non editable
    
    private HashMap hiddenBenefitValueMap = new HashMap(); //Hidden value for Performance issue
    
    private HashMap hiddenLevelFlagMap = new HashMap(); // Hidden level flag for performance issue
    
    private HashMap hiddenLineFlagMap = new HashMap(); // Hidden line flag for performance issue
    
    private boolean benefitFlag = false;
    
    private String breadCrumbText;
    
    private String storedStates;
    //added for version CR
    
    private int benefitVersion;
    
    private List benefitLineNotesObj;

	private boolean notesRetrieved;
	
	private String panelData = "";
	
	private String tierProductStructure;
	//CARS START
	private HashMap dataHiddenValTerm = new HashMap();
    
	private HashMap dataHiddenValQualifier = new HashMap();	
	
	private HashMap lineFreqValueMap = new HashMap();
	
	private HashMap hiddenLineFreqValueMap = new HashMap();
	
	private HashMap dataHiddenValDesc = new HashMap();
	
	private HashMap oldDescOutputTxt = new HashMap();

    private String marketBusinessUnit;
	
	private HashMap hiddenLowerLevelFreqValueMap = new HashMap();
	
	private HashMap dataHiddenLowerLevelValDesc = new HashMap();		
    //CARS END
    
    
	/**
	 * @return Returns the benefitLineNotesObj.
	 */
	public List getBenefitLineNotesObj() {
		return benefitLineNotes;
	}
	/**
	 * @param benefitLineNotesObj The benefitLineNotesObj to set.
	 */
	public void setBenefitLineNotesObj(List benefitLineNotesObj) {
		this.benefitLineNotes = benefitLineNotesObj;
	}
	/**
	 * @return Returns the associatedNotesList.
	 */
	public List getAssociatedNotesList() {
		if(!this.notesRetrieved) {
			// create request
	    	ProductStructureNotesRequest productStructureNotesRequest = (ProductStructureNotesRequest)
				this.getServiceRequest(ServiceManager.PRODUCT_STRUCTURE_NOTES_REQUEST);
			 // Set the various parameters to the request
	    	  	int bnftCmpnt = this.getBenefitComponentIdFromSession();
	    	  	int benefitId = this.getBenefitIdFromSession();
	    	  	productStructureNotesRequest.setEntityId(bnftCmpnt);
	    	  	productStructureNotesRequest.setEntityType("ATTACHCOMP");
	    	  	productStructureNotesRequest.setMaxResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
	    	  	productStructureNotesRequest.setSecEntityId(benefitId);
	    	  	productStructureNotesRequest.setSecEntityType("ATTACHBENEFIT");
	    	  	productStructureNotesRequest.setBenefitComponentId(bnftCmpnt);
	    	  	productStructureNotesRequest.setAction(2);
			 // create response
	    		ProductStructureNotesResponse productStructureNotesResponse = 
	    			(ProductStructureNotesResponse)executeService(productStructureNotesRequest);
			 // return the list 
	    		if(null != productStructureNotesResponse)
	    			associatedNotesList = productStructureNotesResponse.getBenefitComponentNotesList();	
	    		notesRetrieved = true;
		}

		return associatedNotesList;
	}
	/**
	 * @param associatedNotesList The associatedNotesList to set.
	 */
	public void setAssociatedNotesList(List associatedNotesList) {
		this.associatedNotesList = associatedNotesList;
	}
    


    /**
     * @return Returns the printBenftDefnList.
     */
    public List getPrintBenftDefnList() {
        return printBenftDefnList;
    }


    /**
     * @param printBenftDefnList
     *            The printBenftDefnList to set.
     */
    public void setPrintBenftDefnList(List printBenftDefnList) {
        this.printBenftDefnList = printBenftDefnList;
    }


    /**
     * @return Returns the benefitDefinitonsList.
     */
    public List getBenefitDefinitonsList() {
        return benefitDefinitonsList;
    }


    /**
     * @return Returns the standardBenefitKey.
     */
    public int getStandardBenefitKey() {
        return standardBenefitKey;
    }


    /**
     * @param standardBenefitKey
     *            The standardBenefitKey to set.
     */
    public void setStandardBenefitKey(int standardBenefitKey) {
        this.standardBenefitKey = standardBenefitKey;
    }


    /**
     *  
     */
    public ProductStructureBenefitDefenitionBackingBean() {
        super();
        this.setBreadCrumbTextForPS();
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
     * @return Returns the benefitValueMap.
     */
    public Map getBenefitValueMap() {
        return benefitValueMap;
    }


    /**
     * @param benefitValueMap
     *            The benefitValueMap to set.
     */
    public void setBenefitValueMap(Map benefitValueMap) {
        this.benefitValueMap = benefitValueMap;
    }


    /**
     * @return Returns the benefitLineList.
     */
    public List getBenefitLineList() {
        return benefitLineList;
    }


    /**
     * @param benefitLineList
     *            The benefitLineList to set.
     */
    public void setBenefitLineList(List benefitLineList) {
        this.benefitLineList = benefitLineList;
    }


    /**
     * @return Returns the benefitLevelList.
     */
    public List getBenefitLevelList() {
        return benefitLevelList;
    }


    /**
     * @param benefitLevelList
     *            The benefitLevelList to set.
     */
    public void setBenefitLevelList(List benefitLevelList) {
        this.benefitLevelList = benefitLevelList;
    }


    /**
     * @return Returns the benefitDefinitonsList.
     */
    public List getBenefitDefinitonsListValues() {
        Logger.logInfo("Entering the method for getting benefit"
                + " defenition list values");
        
        TimeHandler th = new TimeHandler();
        Logger.logInfo(th.startPerfLogging("U23914_Product_Structre_Coverage","ProductStructureBenefitDefenitionBackingBean","getBenefitDefinitonsListValues"));
        
        
        // Get the request object
        RetrieveBenefitDefenitionRequest retrieveBenefitDefenitionRequest = 
            getRetrieveBenefitDefenitionRequest(RetrieveBenefitDefenitionRequest.GET_BENEFIT_DEFINITION);

        // Get the response by calling the executeService()
        RetrieveBenefitDefenitionResponse benefitDefenitionResponse = 
            (RetrieveBenefitDefenitionResponse) this
                .executeService(retrieveBenefitDefenitionRequest);

        // Get the output list from the response
        List benefitDefnList = null;

        benefitDefnList = benefitDefenitionResponse
                .getBenefitDefinitionList();
        Logger.logInfo("Returning the method for getting benefit defenition "
                + "list values");
        // Check whether the list is null
        if (null != benefitDefnList && !(benefitDefnList.size() == 0)) {
            // Set the list to the benefitDefinitionList
            this.setBenefitDefinitonsList(benefitDefnList);
            
           // for retrieving TierDefinition for a particular BenefitDefinition, 
            //BnftDefnId is required ,so set in session
           BenefitLine  benefitLine = (BenefitLine)benefitDefnList.get(0);
           int benefitDefinitionId = benefitLine.getBenefitDefinitionId();           
           getSession().setAttribute("SESSION_BNFTDEFINITION_ID",new Integer(benefitDefinitionId));
            
        } else {
            this.setBenefitDefinitonsList(null);
        }
        Logger.logInfo(th.endPerfLogging());
        // Return the list
        return benefitDefinitonsList;
    }


    /**
     * @param benefitDefinitonsList
     *            The benefitDefinitonsList to set.
     */
    public void setBenefitDefinitonsList(List benefitDefinitonsList) {
        this.benefitDefinitonsList = benefitDefinitonsList;
    }


    /**
     * This method is used to get the Benefit Definition value for Print
     * 
     * @return String
     */
    public String getPrintBenefitDefValues() {
    	printBenefitDefenition();
        return "";
    }


    public void setPrintBenefitDefValues() {

    }


    /**
     * This method is used to get the General Information of selected Std
     * Benefit
     * 
     * @return String
     */
    public String viewBenefitDefenition() {
        Logger.logInfo("Entering the method for viewing benefit defenition");
        RetrieveBenefitDefenitionResponse retrieveBenefitDefenitionResponse = null;
        RetrieveBenefitDefenitionRequest retrieveBenefitDefenitionRequest = 
            getRetrieveBenefitDefenitionRequest(RetrieveBenefitDefenitionRequest.VIEW_BENEFIT_DEFINITION);
        retrieveBenefitDefenitionResponse = (RetrieveBenefitDefenitionResponse) this
                .executeService(retrieveBenefitDefenitionRequest);
        if (null != retrieveBenefitDefenitionResponse) {
            setValuesToBackingBeanForView(retrieveBenefitDefenitionResponse);
            // for retrieving tier definitions associated and set in backing bean
                      
            int benefitId = this.getBenefitIdFromSession();
            String tier = getAssociatedTier(benefitId);             
            setTierProductStructure(tier);
        }
        if (null!=getActionFromSession() 
                && getActionFromSession().equals("VIEW")) {
            this.setBreadCrumbTextForView();
            return "viewBenefitDefn";
        } else {
            this.setBreadCrumbTextForEdit();
            return "editBenefitDefn";
        }       
    }
    /**
     * This method is used to get the General Information of selected Std
     * Benefit
     * 
     * @return String
     */
    public String printBenefitDefenition() {
        Logger.logInfo("Entering the method for viewing benefit defenition");
        RetrieveBenefitDefenitionResponse retrieveBenefitDefenitionResponse = null;
        RetrieveBenefitDefenitionRequest retrieveBenefitDefenitionRequest = 
            getRetrieveBenefitDefenitionRequest(RetrieveBenefitDefenitionRequest.VIEW_BENEFIT_DEFINITION);
        retrieveBenefitDefenitionResponse = (RetrieveBenefitDefenitionResponse) this
                .executeService(retrieveBenefitDefenitionRequest);
        if (null != retrieveBenefitDefenitionResponse) {
            setValuesToBackingBeanForView(retrieveBenefitDefenitionResponse);
            // for retrieving tier definitions associated and set in backing bean
                      
            int benefitId = this.getBenefitIdFromSession();
            String tier = getAssociatedTierForPrint(benefitId);             
            setTierProductStructure(tier);
        }
        if (null!=getActionFromSession() 
                && getActionFromSession().equals("VIEW")) {
            this.setBreadCrumbTextForView();
            return "viewBenefitDefn";
        } else {
            this.setBreadCrumbTextForEdit();
            return "editBenefitDefn";
        }       
    }

    /**
     * This method is used to get the Benefit Definition for print
     * 
     * @return String
     */
    public String getPrintBenefitDefinition() {
        Logger.logInfo("Entering the method for printing benefit defenition");

        benefitDefinitonsList = getBenefitDefinitonsListValues();
        if (null != benefitDefinitonsList) {
            // Get the new list with separated line and level values
            getValuesFromDefinitonList(benefitDefinitonsList);

            // Call the method to set the line and level list to a new list
            getBenefitDefinifitonValuesForPrint();

            return "";
        } else {
            return null;
        }
    }


    /**
     * 
     * @return String
     */
    private void getBenefitDefinifitonValuesForPrint() {
        // Get the benefitDefinition List

        // Create a list
        List listForPrint = new ArrayList(benefitLevelList == null ? 0:benefitLevelList.size());

        // Check whether the benefitDefinition List is null or empty
        if (null != benefitLevelList && benefitLevelList.size() != 0) {

            // Iterate through the list
            for (int i = 0; i < benefitLevelList.size(); i++) {

                // Get the individual benefit levels
                BenefitLevel individualLevel = (BenefitLevel) benefitLevelList
                        .get(i);

                // Create an instance of the VO
                BenefitDefinitionPrintVO benefitDefinitionPrintVO = new BenefitDefinitionPrintVO();

                // Set the level values to the VO

                if (null != individualLevel.getLevelDesc()) {
    	        	String desc = null;
    	        	String description = individualLevel.getLevelDesc().trim();                	
		        	if(description.length()>11){
		            	String[] strTokenizerArr = description.split(" ");
		            	for(int num=0;num<strTokenizerArr.length;num++){
		            		if(strTokenizerArr[num].length()>22){
		            			strTokenizerArr[num] = strTokenizerArr[num].substring(0,11)+" "+
		            				strTokenizerArr[num].substring(11,22)+" "+strTokenizerArr[num].substring(22);
		            		}else if(strTokenizerArr[num].length()>11){
		            			strTokenizerArr[num] = strTokenizerArr[num].substring(0,11)+" "+strTokenizerArr[num].substring(11);
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
                    benefitDefinitionPrintVO.setLevelDesc(description);
                } else {
                    benefitDefinitionPrintVO.setLevelDesc(" ");
                }
                //CARS START
                //Term Start
                if (null != individualLevel.getTermDesc()) {
                    benefitDefinitionPrintVO.setTermDesc(individualLevel
                            .getTermDesc().replaceAll(",",", "));
                } else {
                    benefitDefinitionPrintVO.setTermDesc(WebConstants.SPACE_STRING);
                }
                //Term End
                //Frequency Start
                if (0 != individualLevel.getFrequencyId()) {
                    benefitDefinitionPrintVO.setBenefitFrequency(new Integer(individualLevel
                            .getFrequencyId()).toString()+WebConstants.HIPHAN_STRING);
                } else {
                    benefitDefinitionPrintVO.setBenefitFrequency(WebConstants.EMPTY_STRING);
                }
                //Frequency End
                //Qualifier Start
                if (null != individualLevel.getQualifierDesc()) {
                    benefitDefinitionPrintVO.setQualifierDesc(individualLevel
                            .getQualifierDesc().replaceAll(",",", "));
                } else {
                    benefitDefinitionPrintVO.setQualifierDesc(WebConstants.SPACE_STRING);
                }
                //Qualifier End
                //CARS END
                benefitDefinitionPrintVO.setProviderArrangementDesc(" ");

                benefitDefinitionPrintVO.setBenefitValue(" ");

                if (null != individualLevel.getReferenceDesc()) {
                //    benefitDefinitionPrintVO.setReferenceDesc(individualLevel
                //            .getReferenceDesc());
                //} else {
                    benefitDefinitionPrintVO.setReferenceDesc(" ");
                }

                // Set the level to the new list
                listForPrint.add(benefitDefinitionPrintVO);

                // Get the benefitLine list
                List linesList = individualLevel.getBenefitLines();

                // Iterate through the benefitLine list
                for (int j = 0; j < linesList.size(); j++) {

                    //					 Create an instance of the VO
                    BenefitDefinitionPrintVO benefitLinePrintVO = new BenefitDefinitionPrintVO();

                    // Get the individual lines from the list
                    BenefitLine individualLine = (BenefitLine) linesList.get(j);

                    if (null != individualLine.getProviderArrangementDesc()) {
                        benefitLinePrintVO
                                .setProviderArrangementDesc(individualLine
                                        .getProviderArrangementDesc());
                    } else {
                        benefitLinePrintVO.setProviderArrangementDesc(" ");
                    }
                    
                    if (null != individualLine.getProviderArrangementId()) {
                        benefitLinePrintVO
                                .setProviderArrangementId(individualLine
                                        .getProviderArrangementId());
                    } else {
                        benefitLinePrintVO.setProviderArrangementId(" ");
                    }

                    if (null != individualLine.getReferenceDesc()) {
                        benefitLinePrintVO.setReferenceDesc(individualLine
                                .getReferenceDesc());
                    } else {
                        benefitLinePrintVO.setReferenceDesc(" ");
                    }

                    //String benftVal = " ";
                    if (null != individualLine.getBenefitValue()) {
                        benefitLinePrintVO.setBenefitValue
                        			(WPDStringUtil.spaceSeparatedString(individualLine.getBenefitValue(),5));
                    } else {
                        //benftVal = " ";
                        benefitLinePrintVO.setBenefitValue(" ");
                    }
                    
                    if (null != individualLine.getDataTypeLegend()) {
                        benefitLinePrintVO.setDataTypeDesc(individualLine
                                .getDataTypeLegend());
                    } else {
                        benefitLinePrintVO.setDataTypeDesc(" ");
                    }
                    benefitLinePrintVO.setLevelDesc(" ");

                    // Set the line to the new list
                    listForPrint.add(benefitLinePrintVO);
                }
            }
            this.printBenftDefnList = listForPrint;
        } else {
            this.printBenftDefnList = null;
        }

    }


    /**
     * This method is used to get the Standard Benefit Detrail
     * 
     * @return String
     */
    public String displayStandardBenefit() {
        if (!getActionFromSession().equals("VIEW")){
            setActionToSession("EDIT");
            this.setBreadCrumbTextForEdit();
        }else
        	this.setBreadCrumbTextForView();
        benefitDefinitonsList = getBenefitDefinitonsListValues();
        String action = (String) getSession().getAttribute("ACTION");
        return action;

    }


    /**
     * To set the values to backing bean for view
     * 
     * @param retrieveBenefitDefenitionResponse
     * @return void
     */
    private void setValuesToBackingBeanForView(
            RetrieveBenefitDefenitionResponse retrieveBenefitDefenitionResponse) {

        if (retrieveBenefitDefenitionResponse != null
                && retrieveBenefitDefenitionResponse.getStandardBenefitBO() != null) {
            DomainDetail domainDetail = retrieveBenefitDefenitionResponse
                    .getDomainDetail();
            if (domainDetail != null) {
                this.lob = WPDStringUtil.getTildaString(domainDetail
                        .getLineOfBusiness());
                this.entity = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessEntity());
                this.group = WPDStringUtil.getTildaString(domainDetail
                        .getBusinessGroup());
                //CARS START
                this.marketBusinessUnit = WPDStringUtil.getTildaString(domainDetail
                        .getMarketBusinessUnit());
                //CARS END
            }
            this.minorHeading = retrieveBenefitDefenitionResponse
                    .getStandardBenefitBO().getBenefitIdentifier();
            this.description = retrieveBenefitDefenitionResponse
                    .getStandardBenefitBO().getDescription();
            List terms = retrieveBenefitDefenitionResponse
                    .getStandardBenefitBO().getTermList();
            this.term = getTildaStringFromUniverseList(terms);
            List qualifiers = retrieveBenefitDefenitionResponse
                    .getStandardBenefitBO().getQualifierList();
            this.qualifier = getTildaStringFromUniverseList(qualifiers);
            List pva = retrieveBenefitDefenitionResponse.getStandardBenefitBO()
                    .getPVAList();
            this.providerArrangement = getTildaStringFromUniverseList(pva);
            List dataType = retrieveBenefitDefenitionResponse
                    .getStandardBenefitBO().getDataTypeList();
            this.dataType = getTildaStringFromDataTypeList(dataType);
            this.benefitType = retrieveBenefitDefenitionResponse
                    .getStandardBenefitBO().getBenefitType();
            this.benefitCategory = retrieveBenefitDefenitionResponse
					.getStandardBenefitBO().getBenefitCategoryDesc();
            if (null != retrieveBenefitDefenitionResponse
                    .getStandardBenefitBO().getRuleIdList()) {
                List refId = retrieveBenefitDefenitionResponse
                        .getStandardBenefitBO().getRuleIdList();
                List refNam = retrieveBenefitDefenitionResponse
                        .getStandardBenefitBO().getRuleNameList();
                List refType = retrieveBenefitDefenitionResponse
                .getStandardBenefitBO().getRuleTypeList();
                //String reference = convertListtoTilda(refNam, refId);
                for (int i = 0; i < refNam.size(); i++) {
                    //this.setRuleId(reference);
                    this.setRuleId(refId.get(i).toString()+'-'+refNam.get(i).toString());
                    this.setRuleType(refType.get(i).toString());
                    }
            }

            if (null != retrieveBenefitDefenitionResponse
                    .getStandardBenefitBO().getMandateType())
                this.mandateType = retrieveBenefitDefenitionResponse
                        .getStandardBenefitBO().getMandateDesc();
            if (null != retrieveBenefitDefenitionResponse
                    .getStandardBenefitBO().getStateCode())
                this.stateCode = retrieveBenefitDefenitionResponse
                        .getStandardBenefitBO().getStateDesc();
            this.createdBy = retrieveBenefitDefenitionResponse
                    .getStandardBenefitBO().getCreatedUser();
            Date createdDate = retrieveBenefitDefenitionResponse
                    .getStandardBenefitBO().getCreatedTimestamp();
            if (null!=createdDate) {
                /*String creationDateString = WPDStringUtil
                        .getStringDate(createdDate);*/
                this.creationDate = createdDate;
            }
            this.updatedBy = retrieveBenefitDefenitionResponse
                    .getStandardBenefitBO().getLastUpdatedUser();
            Date updatedDate = retrieveBenefitDefenitionResponse
                    .getStandardBenefitBO().getLastUpdatedTimestamp();
            if (updatedDate != null) {
                /*String updationDateString = WPDStringUtil
                        .getStringDate(updatedDate);*/
                this.updatedDate = updatedDate;
            }
            //added for benefit version
            this.setBenefitVersion(retrieveBenefitDefenitionResponse.
            		getStandardBenefitBO().getVersion());
            this.setBreadCrumbTextForEdit();
        }
    }


    /**
     * Method to convert List to tilda separated string
     * 
     * @param List idlist
     * @param List nameList
     * @return String
     */
    public String convertListtoTilda(List idlist, List nameList) {
        if ( null == idlist ||  null == nameList)
            return "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < idlist.size(); i++) {
            if (i != 0)
                buffer.append("~");
            buffer.append(idlist.get(i) + "~" + nameList.get(i));
        }
        return buffer.toString();
    }


    /**
     * Method to convert List to tilda separated string
     * 
     * @param List universeItems
     * @return String
     */
    private String getTildaStringFromUniverseList(List universeItems) {

        if (universeItems == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < universeItems.size(); i++) {
            UniverseBO element = (UniverseBO) universeItems.get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getCodeDescText());
            buffer.append("~" + element.getUniverseCode());
        }
        return buffer.toString();
    }


    /**
     * Method to convert List to tilda separated string
     * 
     * @param List dataTypeItems
     * @return String
     */
    private String getTildaStringFromDataTypeList(List dataTypeItems) {

        if (dataTypeItems == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < dataTypeItems.size(); i++) {
            StandardBenefitDatatypeBO element = (StandardBenefitDatatypeBO) dataTypeItems
                    .get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getSelectedItemCode());
            buffer.append("~" + element.getDataTypeName());

        }
        return buffer.toString();
    }


    /**
     * gets the RetrieveBenefitDefenitionRequest
     * 
     * @return RetrieveBenefitDefenitionRequest
     */
    private RetrieveBenefitDefenitionRequest getRetrieveBenefitDefenitionRequest(
            int action) {

        RetrieveBenefitDefenitionRequest retrieveBenefitDefenitionRequest = (RetrieveBenefitDefenitionRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_BENEFIT_DEFENITION);
        String standardKey = (String) getSession().getAttribute(
                "STANDARD_BNFT_KEY");
        int key = Integer.parseInt(standardKey);
        String benefitComponentId = (String) getSession().getAttribute(
                "BNFT_CMPNT_KEY");
        int bnftCompId = Integer.parseInt(benefitComponentId);
        this.setBenefitComponentIdToSession(bnftCompId);
        this.setBenefitToSession(key);
        retrieveBenefitDefenitionRequest = new RetrieveBenefitDefenitionRequest();
        retrieveBenefitDefenitionRequest.setStandardBenefitKey(key);
        retrieveBenefitDefenitionRequest.setBenefitComponentId(bnftCompId);
        ProductStructureVO productStructureVO = new ProductStructureVO();
        productStructureVO = this
                .getProductStructureFromSession(productStructureVO);
        retrieveBenefitDefenitionRequest
                .setProductStructureVO(productStructureVO);
        retrieveBenefitDefenitionRequest.setAction(action);
        // If the show hidden checkbox selected set hide flag to retrieve all (hide/unhide) the values
        if(isShowHidden()){
        	this.setBenefitLevelHideFlag("T");
        	this.setBenefitLineHideFlag("T");
        }
        // Sets the flag to the request based upon the selection of show hidden check box
        if(null!= this.getBenefitLevelHideFlag() && this.getBenefitLevelHideFlag().equals("T"))
        	retrieveBenefitDefenitionRequest.setBenefitLevelHideFlag(null);
        else
        	retrieveBenefitDefenitionRequest.setBenefitLevelHideFlag("F");
        if(null!= this.getBenefitLineHideFlag() && this.getBenefitLineHideFlag().equals("T"))
        	retrieveBenefitDefenitionRequest.setBenefitLineHideFlag(null);
        else
        	retrieveBenefitDefenitionRequest.setBenefitLineHideFlag("F");
        
        return retrieveBenefitDefenitionRequest;
    }
    /**
     * Method to display all the hidden benefit levels on clicking the show hidden
     * @return
     */
    	 public String loadHiddenLevels(){

    	 	return "showHiddenBenefitDefinitionForProdStructure";
    	 }

    /**
     * Returns the headerPanel
     * 
     * @return HtmlPanelGrid headerPanel.
     */
    public HtmlPanelGrid getHeaderPanel() {
        headerPanel = new HtmlPanelGrid();
        ProductStructureGeneralInfoBackingBean productStructureGeneralInfoBackingBean = new ProductStructureGeneralInfoBackingBean();
        if (null!=getActionFromSession()
                && !getActionFromSession().equals("VIEW")&&productStructureGeneralInfoBackingBean
                .getBenefitTypeTab().equals("Standard Definition")) {               
        	headerPanel.setColumns(9);
        }else if ( null!=getActionFromSession()
                && getActionFromSession().equals("VIEW")&&productStructureGeneralInfoBackingBean
                .getBenefitTypeTab().equals("Standard Definition")){
        	headerPanel.setColumns(8);
        }else {                
        	headerPanel.setColumns(5);
        }
        
        headerPanel.setWidth("100%");
        headerPanel.setBorder(0);
        headerPanel.setCellpadding("0");
        headerPanel.setCellspacing("1");
        headerPanel.setBgcolor("#cccccc");    	
        HtmlOutputText otxtType2 = new HtmlOutputText();
        HtmlOutputText otxtType3 = new HtmlOutputText();
        HtmlOutputText otxtType4 = new HtmlOutputText();
        HtmlOutputText otxtType5 = new HtmlOutputText();
        HtmlOutputText otxtType6 = new HtmlOutputText();
        HtmlOutputText otxtType7 = new HtmlOutputText();
        HtmlOutputText otxtType8 = new HtmlOutputText();
        HtmlOutputText otxtType9 = new HtmlOutputText();
        HtmlOutputText otxtTerm = new HtmlOutputText();
        //Set the checkbox for hiding and unhiding the benefit levels and lines
        HtmlOutputLabel checkBoxLabel = new HtmlOutputLabel();
        checkBoxLabel.setId("checkBoxLabel"+RandomStringUtils.randomAlphanumeric(15));
        HtmlOutputText checkBoxText = new HtmlOutputText();
        checkBoxText.setValue("Show Hidden");
        HtmlSelectBooleanCheckbox showHiddenCheckBox = new HtmlSelectBooleanCheckbox();
        showHiddenCheckBox.setId("showHiddenCheckBox");
        showHiddenCheckBox.setValue(Boolean.valueOf(isShowHidden()));
        showHiddenCheckBox.setOnclick("unsavedDataFinder();return false;");
        showHiddenCheckBox.setTabindex("1");
       	checkBoxLabel.getChildren().add(showHiddenCheckBox);
       	checkBoxLabel.getChildren().add(checkBoxText);
        otxtType2.setValue("Description");
        otxtType2.setId("desc");

        otxtType3.setValue("Notes");
        otxtType3.setId("noteRow");
        //CARS START
        //HtmlOutputText for term.
        
        //Setting term as value
        otxtTerm.setValue("Term");
        //Setting term as Id
        otxtTerm.setId("term");
        //Frequency and Qualifier combined in a single column.
        otxtType4.setValue("Frequency - Qualifier");
        //CARS END
        otxtType4.setId("qualifier");

        otxtType5.setValue("PVA");
        otxtType5.setId("pva");

        otxtType6.setValue("Benefit Value");
        otxtType6.setId("value");

        otxtType7.setValue("Reference");
        otxtType7.setId("ref");

        otxtType8.setValue(" ");
        otxtType8.setId("deleteRow");
        
        otxtType9.setValue("Format");
        otxtType9.setId("dataTypeLGND");

        headerPanel.setStyleClass("dataTableHeader");

        if (getActionFromSession() != null
                && !getActionFromSession().equals("VIEW")
                && productStructureGeneralInfoBackingBean.getBenefitTypeTab()
                        .equals("Standard Definition")) {

        	headerPanel.getChildren().add(otxtType2);
        	headerPanel.getChildren().add(otxtTerm);
        	headerPanel.getChildren().add(otxtType4);
        	headerPanel.getChildren().add(otxtType5);
        	headerPanel.getChildren().add(otxtType9);
        	headerPanel.getChildren().add(otxtType6);
        	headerPanel.getChildren().add(otxtType7);
        	headerPanel.getChildren().add(otxtType3);
        	headerPanel.getChildren().add(checkBoxLabel);
        } else {
            headerPanel.getChildren().add(otxtType2);
            headerPanel.getChildren().add(otxtTerm);
            headerPanel.getChildren().add(otxtType4);
            headerPanel.getChildren().add(otxtType5);
            headerPanel.getChildren().add(otxtType9);
            headerPanel.getChildren().add(otxtType6);
            headerPanel.getChildren().add(otxtType7);
            headerPanel.getChildren().add(otxtType3);
            
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
     * Returns the panel
     * 
     * @return HtmlPanelGrid panel.
     */
    public HtmlPanelGrid getPanel() {
    	TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23914_Product_Structre_Coverage","ProductStructureBenefitDefenitionBackingBean","getPanel"));
    	
		// Retaining the previous entered values, In case of application has any error message (Defect: 186432)
		// get the benefit defenitions list if list not null
    	if(null == benefitDefinitonsList || benefitDefinitonsList.isEmpty()){
    		/*stabilization Fix if benefitDefinitonsList is null then only we invoke the method once again
    		 * as we have storing the value to benefitDefinitonsList in displayStandardBenefit() method */
    		benefitDefinitonsList = getBenefitDefinitonsListValues();
    	}
		if (validationMessages.isEmpty() && null != benefitDefinitonsList) {    	
	        int rowNumber = 0;
	        int lineCount = 0;
	        //benefitDefinitonsList = getBenefitDefinitonsListValues();
	        storeBenefitLevelHideFlags();
	        panel = new HtmlPanelGrid();
	        ProductStructureGeneralInfoBackingBean productStructureGeneralInfoBackingBean = new ProductStructureGeneralInfoBackingBean();
	        if (null != benefitDefinitonsList && benefitDefinitonsList.size() != 0) {
	            //This method gets the values from the benefit definiton List and
	            // sets it to the level list and line list
	            getValuesFromDefinitonList(benefitDefinitonsList);
	
	            if (null!=getActionFromSession()
	                    && !getActionFromSession().equals("VIEW")&&productStructureGeneralInfoBackingBean
	                    .getBenefitTypeTab().equals("Standard Definition")) {               
	                panel.setColumns(9);
	            }else if ( null!=getActionFromSession()
	                    && getActionFromSession().equals("VIEW")&&productStructureGeneralInfoBackingBean
	                    .getBenefitTypeTab().equals("Standard Definition")){
	            	panel.setColumns(8);
	            }else {                
	                panel.setColumns(5);
	            }
	            panel.setWidth("100%");
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
	                //Setting the hidden level in grey color
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
	                	
	                    //selects an individual benefit line
	                    BenefitLine benefitLineValues = (BenefitLine) benefitLines
	                            .get(j);
	                    
	                    rowNumber++;
	                    lineCount = lineCount + 1;
	                    //Setting the hidden line display in grey color
	                    if(benefitLineValues.isLineHideStatus())
	                    	rows.append("hiddenFieldDisplay");
	                    else
	                    	rows.append("dataTableOddRow");
	                    if (i < (size - 1))
	                        rows.append(",");
	                    else if (j < (benefitLines.size() - 1))
	                        rows.append(",");
	
	                    //sets the benefit lines of a benefit level to the panle
	                    // grid
	                    setBenefitLineValuesToGrid(benefitLevelValues, j,
	                            benefitLineValues, i,benefitLines.size(),lineCount,benefitLineValues.isLineHideStatus());
	
	                }
	
	            }
//	          code change for performance improvement - setting the following Maps in session
//	          instead of value binding.   
	            getProductStructureSessionObject().setDataHiddenLowerLevelValDescMap(dataHiddenLowerLevelValDesc);
	            getProductStructureSessionObject().setDataHiddenValTermMap(dataHiddenValTerm);
	            getProductStructureSessionObject().setDataHiddenValQualifierMap(dataHiddenValQualifier);
	            getProductStructureSessionObject().setHiddenLineFreqValueMap(hiddenLineFreqValueMap);
	            getProductStructureSessionObject().setHiddenLowerLevelFreqValueMap(hiddenLowerLevelFreqValueMap);
	            //getProductStructureSessionObject().setLineIdMap(lineIdMap);
	            getProductStructureSessionObject().setHiddenLevelFlagMap(hiddenLevelFlagMap);
	            getProductStructureSessionObject().setHiddenLineFlagMap(hiddenLineFlagMap);
	            getProductStructureSessionObject().setHiddenBenefitValueMap(hiddenBenefitValueMap);
	            getProductStructureSessionObject().setOldDescOutputTxtMap(oldDescOutputTxt);
	            //getProductStructureSessionObject().setLevelIdsMapFromSession(levelIdsMap);
				
	            panel.setRowClasses(rows.toString());
	        } else {
	
	            panel.setRendered(false);
	        }
		}
		Logger.logInfo(th.endPerfLogging());
		return panel;

    }

    /**
     * @return
     */
    private Map loadBenefitLevelHideFlags(){
    	Map temp = new HashMap();
    	if(null !=storedStates  && !"".equals(storedStates.trim())){
    		String[] benefitLevels = storedStates.split(";");
    		for(int i=0;i<benefitLevels.length;i++){
    			String bl = benefitLevels[i];
    			String[] idValues = bl.split(":");
    			String bLid = idValues[0];
    			String[] flagAndBlines =  idValues[1].split("~");
    			String bFlag = flagAndBlines[0];
    			String blines = flagAndBlines[1];
    			Object[] objects = new Object[2];
    			objects[0] = bFlag;
    			objects[1] = new HashMap();
    			temp.put(new Integer(bLid),objects);
    			
    			if(null != blines && !"".equals(blines.trim())){
	    			String[] blinesArray = blines.split(",");
	    			for(int j=0;j<blinesArray.length;j++){
	    				String bLineid = blinesArray[j].split("-")[0];
	    				String blineFlag = blinesArray[j].split("-")[1];
	    				((Map)((Object[])temp.get(new Integer(bLid)))[1]).put(new Integer(bLineid),blineFlag);
	    			}
    			}
				
				
    			
    		}
    	}
    	
    	return temp;
    }
    

    /**
	 * @return
	 */
	private void storeBenefitLevelHideFlags() {
		
		List benefitLines = benefitDefinitonsList;
		Map bLevelMap = new HashMap();
		Map bLineMap = null;
		Object[] objects = null;
		Iterator iterator = benefitLines.iterator();
		while(iterator.hasNext()){
			BenefitLine benefitLine = (BenefitLine)iterator.next();
			objects = (Object[])bLevelMap.get(new Integer(benefitLine.getBenefitSysId()));
			if(objects == null){
				objects = new Object[2];
				objects[0] = benefitLine.getBenefitHide();
				objects[1] = new HashMap();
				bLevelMap.put(new Integer(benefitLine.getBenefitSysId()), objects);
			}
			bLineMap = (Map) objects[1];
			bLineMap.put( new Integer( benefitLine.getLineSysId() ), benefitLine.getLineHide());
		}
		
		Set keys = bLevelMap.keySet();
		Iterator keysIter = keys.iterator();
		StringBuffer buffer = new StringBuffer();
		while(keysIter.hasNext()){
			Integer bLevelId = (Integer)keysIter.next();
			Object[] temps = (Object[])bLevelMap.get(bLevelId);
			buffer.append(bLevelId);
			buffer.append(":");
			buffer.append(temps[0]);
			buffer.append("~");
			Map tempMap = (Map)temps[1];
			Iterator kIter = tempMap.keySet().iterator();
			while(kIter.hasNext()){
				Integer bLineId = (Integer)kIter.next();
				buffer.append(bLineId);
				buffer.append("-");
				buffer.append(tempMap.get(bLineId));
				if(kIter.hasNext())
					buffer.append(",");
			}
			if(keysIter.hasNext())
				buffer.append(";");			
		}
		storedStates = buffer.toString();
	}
	/**
     * This method gets the values from the benefit definiton List and sets it
     * to the level list and line list
     * 
     * @param benefitDefinitionsList
     */
    private void getValuesFromDefinitonList(List benefitDefinitionsList) {
        benefitLevelList = new ArrayList(0);
        for (int i = 0; i < benefitDefinitionsList.size(); i++) {
            BenefitLine entityBenefitLine = (BenefitLine) benefitDefinitionsList
                    .get(i);
            if (null != entityBenefitLine.getDataTypeDesc())
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
        BenefitLevel benefitLevelBO = null;

        
         BenefitLevel tempBO = null;
         boolean checkFlag = false;
         // checks if the benefit level list size is not equal to zero
         if (benefitLevelList.size() != 0) {
             
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
                 !checkFlag) {
             BenefitLevel entityBenefitLevel = new BenefitLevel();
             entityBenefitLevel.setLevelDesc(entityBenefitLine.getLevelDesc());
             entityBenefitLevel.setLevelId(entityBenefitLine.getLevelSysId());
             entityBenefitLevel.setTermDesc(entityBenefitLine.getTermDesc());
             entityBenefitLevel.setQualifierDesc(entityBenefitLine
                     .getQualifierDesc());
             entityBenefitLevel.setReferenceDesc(entityBenefitLine
                     .getReferenceDesc());
             //CARS START
             //Setting the frequency value from the BenefitLine BO to BenefitLevel BO
             entityBenefitLevel.setFrequencyId(entityBenefitLine.getFrequencyValue());
             entityBenefitLevel.setLowerLevelDescValue(entityBenefitLine.getLowerLevelDescValue());
             entityBenefitLevel.setLowerLevelFrequencyValue(entityBenefitLine.getLowerLevelFrequencyValue()+"");
             //CARS END            
             //Set the retrieved status flag to the checkbox
             if(null!= entityBenefitLine.getLevelHide() && entityBenefitLine.getLevelHide().equals("F"))
             	entityBenefitLevel.setLevelHideStatus(false);
             else
             	entityBenefitLevel.setLevelHideStatus(true);
             //Set the retrieved status of line checkbox
             if(null!=entityBenefitLine.getLineHide() && entityBenefitLine.getLineHide().equals("F"))
             	entityBenefitLevel.setLineHideStatus(false);
             else
             	entityBenefitLevel.setLineHideStatus(true);

             //sets benefit lines to the benefit Levels
             entityBenefitLevel.setBenefitLines(new ArrayList(1));
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
     * This mehtod returns the benefit line bo
     * 
     * @param entityBenefitLine
     * @return
     */
    private BenefitLine getBenefitLineBO(BenefitLine entityBenefitLine) {
        BenefitLine entityBenefitLineToSet = new BenefitLine();
        entityBenefitLineToSet.setBenefitValue(entityBenefitLine
                .getBenefitValue());
        //Setting the non-overridden benefit Values as part of Enhancement
        entityBenefitLineToSet.setLineValue(entityBenefitLine.getLineValue());
        entityBenefitLineToSet.setProviderArrangementDesc(entityBenefitLine
                .getProviderArrangementDesc());
        entityBenefitLineToSet.setLineSysId(entityBenefitLine.getLineSysId());
        if (null != entityBenefitLine.getDataTypeDesc()
                && !(entityBenefitLine.getDataTypeDesc()).equals("")) {
            entityBenefitLineToSet.setDataTypeDesc(entityBenefitLine
                    .getDataTypeDesc());
        }
        entityBenefitLineToSet.setDataTypeId(entityBenefitLine.getDataTypeId());
        entityBenefitLineToSet.setReferenceDesc(entityBenefitLine
                .getReferenceDesc());
        //Set the retrieved status of line checkbox
        if(null!=entityBenefitLine.getLineHide() && entityBenefitLine.getLineHide().equals("F"))
        	entityBenefitLineToSet.setLineHideStatus(false);
        else
        	entityBenefitLineToSet.setLineHideStatus(true);
        //Set the benefit customized sys id 
        entityBenefitLineToSet.setCustomizedSysId(entityBenefitLine.getCustomizedSysId());
        entityBenefitLineToSet.setDataTypeLegend
        					(entityBenefitLine.getDataTypeLegend());
        entityBenefitLineToSet.setProviderArrangementId
        					(entityBenefitLine.getProviderArrangementId());
        entityBenefitLineToSet.setBnftLineNotesExist
			(entityBenefitLine.getBnftLineNotesExist());
       // entityBenefitLineToSet.setLineHideStatus(new Boolean(entityBenefitLine.getLineHide()).booleanValue());
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
    	int benefitComponentId =  this.getBenefitComponentIdFromSession();
        HtmlSelectBooleanCheckbox lineCheckBox = new HtmlSelectBooleanCheckbox();
        HtmlInputHidden hiddenCustomizedSysId = new HtmlInputHidden();
    	//int benefitLineid = benefitLineValues.getLineSysId();
        HtmlOutputText lineDesc = new HtmlOutputText();
        //CARS START
        //HtmlOutputText for term
        HtmlOutputText termOutputTxt = new HtmlOutputText();
        //HtmlOutputText for Frequency and Qualifier.
        HtmlOutputText freqQualifierOutputTxt = new HtmlOutputText();
        //CARS END
        HtmlInputHidden hiddenLineFlag = new HtmlInputHidden();//Hidden field for old line flag
        HtmlInputHidden hiddenBenefitValue = new HtmlInputHidden(); // Hidden field for old benefit value
        lineDesc.setValue(" ");

        HtmlOutputText lineTerm = new HtmlOutputText();
        lineTerm.setValue(benefitLevelValues.getTermDesc());

        HtmlOutputText lineQualifier = new HtmlOutputText();
        lineQualifier.setValue(benefitLevelValues.getQualifierDesc());

        HtmlOutputText linePVA = new HtmlOutputText();
        //linePVA.setValue(benefitLineValues.getProviderArrangementDesc());
        linePVA.setValue(benefitLineValues.getProviderArrangementId());
        //linePVA.setId(benefitLineValues.getProviderArrangementId());

        HtmlOutputText lineDataType = new HtmlOutputText();
        lineDataType.setValue(benefitLineValues.getDataTypeDesc());
        //      hidden field for storing the benefitLineSysId
        HtmlInputHidden hiddenLineId = new HtmlInputHidden();
        hiddenLineId.setId("hiddenLineId" + + j + "_" + i);
        hiddenCustomizedSysId.setId("hiddenCustomizedSysId"+j+"_"+i);

        hiddenLineId.setValue(new Integer(benefitLevelValues.getLevelId()) + ":" +new Integer( benefitLineValues.getLineSysId())
                + ":" + benefitLineValues.getBenefitValue() + ":"
                + benefitLineValues.getDataTypeId() + ":"
                + benefitLevelValues.getLevelDesc());
        hiddenCustomizedSysId.setValue(new Integer(benefitLineValues.getCustomizedSysId()));
        // set the value to the map
        //String test = benefitLineValues.getLineSysId()+"";
        ValueBinding lineIdValBind = FacesContext.getCurrentInstance()
                .getApplication().createValueBinding(
                        "#{productStructureBenefitDefenitionBackingBean.lineIdMap["+benefitLineValues.getLineSysId()+"]}");
        hiddenLineId.setValueBinding("value", lineIdValBind);
        
        ValueBinding customizedSysIdBind =FacesContext.getCurrentInstance()
		.getApplication().createValueBinding(
					"#{productStructureBenefitDefenitionBackingBean.customizedSysIdMap['"+benefitLineValues.getLineSysId()+"']}");
        hiddenCustomizedSysId.setValueBinding("value",customizedSysIdBind);
        
        //Start of modification for performance issue
        hiddenLineFlag.setId("hiddenLineFlag"+"A"+i+"A"+lineSize+"A"+lineNum+"A"+"Line");
        hiddenLineFlag.setValue(""+benefitLineValues.isLineHideStatus());
        
//      code change for performance improvement
        /*ValueBinding oldLineFlag = FacesContext.getCurrentInstance().getApplication().createValueBinding
							("#{productStructureBenefitDefenitionBackingBean.hiddenLineFlagMap["+benefitLineValues.getLineSysId()+"]}");
        hiddenLineFlag.setValueBinding("value", oldLineFlag);
        */
        hiddenLineFlagMap.put(new Long(benefitLineValues.getLineSysId()), new Boolean(benefitLineValues.isLineHideStatus()));
        //End of modification
        
        HtmlInputText lineBnftValue = new HtmlInputText();
        ProductStructureGeneralInfoBackingBean productStructureGeneralInfoBackingBean = new ProductStructureGeneralInfoBackingBean();

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
//      output text for view
        HtmlOutputText lineBnftValueView = new HtmlOutputText();
        lineBnftValueView.setId("lineBnftValueView"  + j + "_" + i);
        lineBnftValueView.setValue(WPDStringUtil.spaceSeparatedString(benefitLineValues.getBenefitValue(),9));        
        if (null != sysDataType) {
        	//Start of modification for performace issue
        	hiddenBenefitValue.setId("hiddenBenefitValue"+j+"_"+i);
        	hiddenBenefitValue.setValue(benefitLineValues.getBenefitValue());
        	
//        	code change for performance improvement
        	/*ValueBinding oldBenefitValue = FacesContext.getCurrentInstance().getApplication().createValueBinding
				("#{productStructureBenefitDefenitionBackingBean.hiddenBenefitValueMap["+benefitLineValues.getLineSysId()+"]}");
        	hiddenBenefitValue.setValueBinding("value", oldBenefitValue);
        	*/
        	hiddenBenefitValueMap.put(new Long(benefitLineValues.getLineSysId()), null == benefitLineValues.getBenefitValue()?"":benefitLineValues.getBenefitValue() );
        	//end of modification
            if (sysDataType.equals("BOOLEAN")) {
                UISelectItems selectItems = new UISelectItems();
                List possibleBnftVal = new ArrayList(3);
                possibleBnftVal.add(new SelectItem("", ""));
//              Code changed as part of the Enhancement to display the benefit values same as 
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
					}else if(benefitLineValues.getBenefitValue().equals("Y")){
							seloneMenuForBnftValue.setValue("Y");
							lineBnftValueView.setValue("Y");	
					}else if(benefitLineValues.getBenefitValue().equals("N")){
							seloneMenuForBnftValue.setValue("N");
							lineBnftValueView.setValue("N");	
					}else{
							seloneMenuForBnftValue.setValue("");
							lineBnftValueView.setValue("");	
					}
				}
                seloneMenuForBnftValue.setId("lineBnftValue" + j + "_" + i);
                seloneMenuForBnftValue.setDisabled(isHidden);
                object = (HtmlSelectOneMenu) seloneMenuForBnftValue;
                // set the value to the map
                ValueBinding bnftValueBinding = FacesContext
                        .getCurrentInstance().getApplication()
                        .createValueBinding(
                                "#{productStructureBenefitDefenitionBackingBean.benefitValueMap["
                                        + benefitLineValues.getLineSysId()
                                        + "]}");
                seloneMenuForBnftValue.setValueBinding("value",
                        bnftValueBinding);
                seloneMenuForBnftValue.setDisabled(isHidden);
            } else {
            	if(!benefitLineValues.isLineHideStatus()){
                lineBnftValue.setSize(10);
                lineBnftValue.setId("lineBnftValue" + j + "_" + i);
                lineBnftValue.setValue(benefitLineValues.getBenefitValue());

                if (!benefitLineValues.getDataTypeDesc().trim()
                        .equalsIgnoreCase("String")) {
                }
                lineBnftValue.setTitle("BenefitValue"
                        + benefitLineValues.getDataTypeDesc());

                ValueBinding valueItem = FacesContext.getCurrentInstance()
                        .getApplication().createValueBinding(
                                "#{productStructureBenefitDefenitionBackingBean.benefitValueMap["
                                        + benefitLineValues.getLineSysId()
                                        + "]}");
                lineBnftValue.setValueBinding("value", valueItem);
                lineBnftValue.setStyleClass("formInputField");
                lineBnftValue.setStyle("width:75px;");
            	}else{
            		lineBnftValue.setSize(10);
                    lineBnftValue.setId("lineBnftValue" + j + "_" + i);
                    lineBnftValue.setValue(benefitLineValues.getBenefitValue());
                    lineBnftValue.setDisabled(benefitLineValues.isLineHideStatus());

                    if (!benefitLineValues.getDataTypeDesc().trim()
                            .equalsIgnoreCase("String")) {
                    }
                    lineBnftValue.setTitle("BenefitValue"
                            + benefitLineValues.getDataTypeDesc());

                    ValueBinding valueItem = FacesContext.getCurrentInstance()
                            .getApplication().createValueBinding(
                                    "#{productStructureBenefitDefenitionBackingBean.benefitValueMap["
                                            + benefitLineValues.getLineSysId()
                                            + "]}");
                    lineBnftValue.setValueBinding("value", valueItem);
                    lineBnftValue.setStyle("width:75px;");
            	}
            }
        }
        

        HtmlOutputText lineEmptyString = new HtmlOutputText();
        lineEmptyString.setValue(" ");

        HtmlOutputLabel lblBnftValue = new HtmlOutputLabel();
        lblBnftValue.setId("lblBnftValue"+RandomStringUtils.randomAlphanumeric(15));
        //lblBnftValue.setId("lblBnftValue" + j + "_" + i);
        lblBnftValue.setFor("lineBnftValue" + j + "_" + i);
        if (null != sysDataType) {
            if (sysDataType.equals("DOLLAR")) {
                if ( null != getActionFromSession() 
                        && !getActionFromSession().equals("VIEW")
                        && productStructureGeneralInfoBackingBean
                                .getBenefitTypeTab().equals(
                                        "Standard Definition")) {
                    //lblBnftValue.getChildren().add(lineDataType);
                	//if(isBenefitEditStatusFlag())
                		lblBnftValue.getChildren().add(lineBnftValue);
                    //else
                    	//lblBnftValue.getChildren().add(lineBnftValueView);
                    lblBnftValue.getChildren().add(lineEmptyString);
                } else {
                    //lblBnftValue.getChildren().add(lineDataType);
                    lblBnftValue.getChildren().add(lineEmptyString);
                    lblBnftValue.getChildren().add(lineBnftValueView);

                }
            } else if (sysDataType.equals("PERCENTAGE")) {
                if (null != getActionFromSession()
                        && !getActionFromSession().equals("VIEW")
                        && productStructureGeneralInfoBackingBean
                                .getBenefitTypeTab().equals(
                                        "Standard Definition")) {
                    lblBnftValue.getChildren().add(lineEmptyString);
                    //if(isBenefitEditStatusFlag())
                    	lblBnftValue.getChildren().add(lineBnftValue);
                    //else
                    	//lblBnftValue.getChildren().add(lineBnftValueView);
                    //lblBnftValue.getChildren().add(lineDataType);

                } else {

                    lblBnftValue.getChildren().add(lineBnftValueView);
                    lblBnftValue.getChildren().add(lineEmptyString);
                    //lblBnftValue.getChildren().add(lineDataType);
                }
            } else if (sysDataType.equals("STRING")) {
                if (null != getActionFromSession()
                        && !getActionFromSession().equals("VIEW")
                        && productStructureGeneralInfoBackingBean
                                .getBenefitTypeTab().equals(
                                        "Standard Definition")) {
                    lblBnftValue.getChildren().add(lineEmptyString);
                   // if(isBenefitEditStatusFlag())
                    	lblBnftValue.getChildren().add(lineBnftValue);
                    //else
                    	//lblBnftValue.getChildren().add(lineBnftValueView);
                    
                } else {
                    lblBnftValue.getChildren().add(lineEmptyString);
                    lblBnftValue.getChildren().add(lineBnftValueView);
                }
            } else if (sysDataType.equals("BOOLEAN")) {
                if (null != getActionFromSession()
                        && !getActionFromSession().equals("VIEW")
                        && productStructureGeneralInfoBackingBean
                                .getBenefitTypeTab().equals(
                                        "Standard Definition")) {
                    lblBnftValue.getChildren().add(lineEmptyString);
                    lblBnftValue.getChildren().add(object);
                } else {
                    lblBnftValue.getChildren().add(lineEmptyString);
                    lblBnftValue.getChildren().add(lineBnftValueView);
                }
            } else {
                if (null != getActionFromSession()
                        && !getActionFromSession().equals("VIEW")
                        && productStructureGeneralInfoBackingBean
                                .getBenefitTypeTab().equals(
                                        "Standard Definition")) {
                    lblBnftValue.getChildren().add(lineEmptyString);
                    //if(isBenefitEditStatusFlag())
                    	lblBnftValue.getChildren().add(lineBnftValue);
                   // else
                    	//lblBnftValue.getChildren().add(lineBnftValueView);
                                lblBnftValue.getChildren().add(hiddenLineId);
                                lblBnftValue.getChildren().add(hiddenCustomizedSysId);
                } else {
                    lblBnftValue.getChildren().add(lineEmptyString);
                    lblBnftValue.getChildren().add(lineBnftValueView);
                }
            }
            lblBnftValue.getChildren().add(hiddenLineId);
            lblBnftValue.getChildren().add(hiddenCustomizedSysId);
        }
        lineBnftValue.setDisabled(isHidden);
        lblBnftValue.getChildren().add(hiddenLineFlag);
        lblBnftValue.getChildren().add(hiddenBenefitValue);

        HtmlOutputText lineReference = new HtmlOutputText();
        lineReference.setId("lineReference" + j + "_" + i);
        lineReference.setValue(benefitLineValues.getReferenceDesc());

        //HtmlOutputText lineImage = new HtmlOutputText();
        lineDesc.setValue(" ");
        //CARS START
        //Setting empty string to term.       
        termOutputTxt.setValue(" ");
        //Setting empty string to Frequency and Qualifier.
        freqQualifierOutputTxt.setValue(" ");
        //CARS END        
        lineCheckBox.setId("checkBox"+"A"+i+"A"+lineSize+"A"+lineNum+"A"+"Line");
        lineCheckBox.setValue(Boolean.valueOf(benefitLineValues.isLineHideStatus()));
        
        //Binding the value of the checkbox 
        ValueBinding checkBoxValBind = FacesContext.getCurrentInstance()
			.getApplication().createValueBinding(
              "#{productStructureBenefitDefenitionBackingBean.checkBoxMap[" + benefitLineValues.getLineSysId()
                      + "]}");
        lineCheckBox.setValueBinding("value", checkBoxValBind);
        lineCheckBox.setOnclick("checkTheCorrespondingBenefitLevel(this)");
    
        HtmlOutputLabel lblNotesImage = new HtmlOutputLabel();
        lblNotesImage.setId("lblNotesImage"+RandomStringUtils.randomAlphanumeric(15));
        //lblNotesImage.setId("lblNotesImage" + j + "_" + i);
        lblNotesImage.setFor("lblNotesImage" + j + "_" + i);

        HtmlGraphicImage noteImage = new HtmlGraphicImage();
        noteImage.setUrl("../../images/notes_exist.gif");
        noteImage.setStyle("cursor:hand;");
        noteImage.setId("noteImage" + j + "_" + i);

        HtmlCommandButton notesButton = new HtmlCommandButton();
        notesButton.setValue("NotesButton");
        if (benefitLineValues.getBnftLineNotesExist().equals("Y"))
            notesButton.setImage("../../images/notes_exist.gif");
        else
            notesButton.setImage("../../images/page.gif");
        notesButton.setTitle("Note");
        notesButton.setStyle("border:0;");
        notesButton.setAlt("Notes");
        notesButton.setId("notesButton" + j + "_" + i);
        notesButton.setOnclick("getUrlAssigned(" + benefitLineValues.getLineSysId()
                + "," + benefitComponentId + ");return false;");
//        if (!isViewMode()) {
//            notesButton.setOnclick("getUrl(" + benefitLineValues.getLineSysId()
//                    + ");return false;");
//        } else {
//            notesButton.setOnclick("getUrl(" + benefitLineValues.getLineSysId()
//                    + "," + super.getBenefitComponentIdFromSession() + ","
//                    + super.getProductsFromSession() + ");return false;");
//        }
//        lblRefAndNotes.getChildren().add(lineReference);
//        if (!isPrintMode()) {
//            lblNotesImage.getChildren().add(notesButton);
//        }

        // lgnd data type
        notesButton.setDisabled(isHidden);
		HtmlOutputText lgndDataType = new HtmlOutputText();
		lgndDataType.setValue(benefitLineValues.getDataTypeLegend());
		lgndDataType.setId("lgndDataType"+ j + "_" + i);
		 
        if (null != getActionFromSession()
                && !getActionFromSession().equals("VIEW")
                && productStructureGeneralInfoBackingBean.getBenefitTypeTab()
                        .equals("Standard Definition")) {
            panel.getChildren().add(lineDesc);
            panel.getChildren().add(termOutputTxt);
            panel.getChildren().add(freqQualifierOutputTxt);
            panel.getChildren().add(linePVA);
            panel.getChildren().add(lgndDataType);
            panel.getChildren().add(lblBnftValue);
            panel.getChildren().add(lineReference);


        } else {
            panel.getChildren().add(lineDesc);
            panel.getChildren().add(termOutputTxt);
            panel.getChildren().add(freqQualifierOutputTxt);
            panel.getChildren().add(linePVA);
            panel.getChildren().add(lgndDataType);
            panel.getChildren().add(lblBnftValue);
            panel.getChildren().add(lineReference);
        }
        if (null != getActionFromSession()
                && !getActionFromSession().equals("VIEW")
                && productStructureGeneralInfoBackingBean.getBenefitTypeTab()
                        .equals("Standard Definition")) {
          //  panel.getChildren().add(lineImage);
            //panel.getChildren().add(notesButton);
            //headerPanel.getChildren().add(otxtType3);headerPanel.getChildren().add(otxtType3);panel.getChildren().add();
        }
        if(productStructureGeneralInfoBackingBean.getBenefitTypeTab()
                .equals("Standard Definition")){
        	panel.getChildren().add(notesButton);
        }
        if (null != getActionFromSession()
                && !getActionFromSession().equals("VIEW")
                && productStructureGeneralInfoBackingBean.getBenefitTypeTab()
                        .equals("Standard Definition") ) {
            panel.getChildren().add(lineCheckBox);
        }
//        else if(null != getActionFromSession()
//                && !getActionFromSession().equals("VIEW")
//                && productStructureGeneralInfoBackingBean.getBenefitTypeTab()
//                        .equals("Standard Definition") && !isBenefitEditStatusFlag())
//        	panel.getChildren().add(lineEmptyString);
        
    }


    /**
     * This method sets the benefitLevelValues to the PanelGrid
     * 
     * @param i
     * @param benefitLevelValues
     */
    private void setBenefitLevelValuesToGrid(int i,
            BenefitLevel benefitLevelValues, int lineSize, int rowNum,int lineCount) {
    	ProductStructureGeneralInfoBackingBean productStructureGeneralInfoBackingBean = new ProductStructureGeneralInfoBackingBean(); 
        HtmlOutputText levelDesc = new HtmlOutputText();
        HtmlOutputText levelPVA = new HtmlOutputText();
        HtmlOutputText levelTerm = new HtmlOutputText();
        HtmlOutputText levelQualifier = new HtmlOutputText();
        //CARS START
        HtmlInputText levelFrequencyInputTxt = new HtmlInputText();
        HtmlOutputText levelFrequencyOutputTxt = new HtmlOutputText();
        //CARS END
        HtmlOutputText levelBnftValue = new HtmlOutputText();
        HtmlOutputText levelReference = new HtmlOutputText();
        HtmlInputHidden hiddenLevelId = new HtmlInputHidden();
        HtmlGraphicImage deleteImage = new HtmlGraphicImage();
        HtmlCommandButton deleteButton = new HtmlCommandButton();
        HtmlSelectBooleanCheckbox checkbox = new HtmlSelectBooleanCheckbox();
        HtmlOutputLabel lblImage = new HtmlOutputLabel();
        lblImage.setId("lblImage"+RandomStringUtils.randomAlphanumeric(15));
        HtmlOutputText dummyText = new HtmlOutputText();
        HtmlOutputText dummyText2 = new HtmlOutputText();
        //sets the size to a hidden variable
        HtmlInputHidden hiddenLineSize = new HtmlInputHidden();
        //sets the size to a hidden variable
        HtmlInputHidden hiddenRowSize = new HtmlInputHidden();
        //sets the old benefit level flag for a hidden variable
        HtmlInputHidden hiddenLevelFlag = new HtmlInputHidden();
        //CARS START
        HtmlInputText levelDescInputText = new HtmlInputText();
        HtmlOutputLabel lblDesc = new HtmlOutputLabel();
        HtmlInputHidden hiddenForDesc = new HtmlInputHidden();
        HtmlInputHidden hiddenLowerLevelDescription = new HtmlInputHidden();
        lblDesc.setId("lblDesc"+RandomStringUtils.randomAlphanumeric(15));
       // levelDesc.setId("levelDesc" + i);
        hiddenLowerLevelDescription.setId("hidLowerLevelValDesc" + i); 
        if (null != benefitLevelValues.getLevelDesc()) {
			//Fix for Contract View alignment Issue
        	String desc = null;
        	String description = benefitLevelValues.getLevelDesc().trim();
                if(description.length()>17){
                	String[] strTokenizerArr = description.split(" ");
                	for(int num=0;num<strTokenizerArr.length;num++){
                		if(strTokenizerArr[num].length()>17){
                			strTokenizerArr[num] = strTokenizerArr[num].substring(0,17)+" "+
                				strTokenizerArr[num].substring(17);
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
            //levelDesc.setValue(benefitLevelValues.getLevelDesc().trim());
            levelDescInputText.setValue(benefitLevelValues.getLevelDesc().trim());
            hiddenForDesc.setValue(benefitLevelValues.getLevelDesc().trim());
            hiddenLowerLevelDescription.setValue(benefitLevelValues.getLowerLevelDescValue().trim());
        } else {
            levelDesc.setValue(WebConstants.EMPTY_STRING);
            levelDescInputText.setValue(WebConstants.EMPTY_STRING);
            hiddenForDesc.setValue(WebConstants.EMPTY_STRING);
            hiddenLowerLevelDescription.setValue(WebConstants.EMPTY_STRING);
        }
        //Binding the value for description text         
        hiddenForDesc.setId("levelHidDesc" + i);
        
        //code change for performance improvement - put value in Map instead of ValueBinding
        //ValueBinding hiddenDescOutputTxt = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productStructureBenefitDefenitionBackingBean.oldDescOutputTxt["+ benefitLevelValues.getLevelId() +"]}");        
        //hiddenForDesc.setValueBinding("value",hiddenDescOutputTxt); 
        oldDescOutputTxt.put(new Long(benefitLevelValues.getLevelId()),new String(benefitLevelValues.getLevelDesc()));
        
        
        levelDescInputText.setStyleClass("formInputField");
		levelDescInputText.setId("levelDescInputText" + i);
		//levelDescInputText.setStyle("width:125px;display: none");
		levelDescInputText.setMaxlength(32);
		//Binding the value for description text
		
		ValueBinding hiddenDescription = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productStructureBenefitDefenitionBackingBean.dataHiddenValDesc["+ benefitLevelValues.getLevelId() +"]}");        
        levelDescInputText.setValueBinding("value", hiddenDescription);
		
		
        //DESCRIPTION FIX START
        if (null != getActionFromSession()
                && !getActionFromSession().equals("VIEW")
                && productStructureGeneralInfoBackingBean.getBenefitTypeTab()
                        .equals("Standard Definition")) {
	        if (!BusinessUtil.isSystemGeneratedDescription(benefitLevelValues
					.getLevelDesc(), benefitLevelValues.getTermDesc(),
					benefitLevelValues.getQualifierDesc(), benefitLevelValues.getFrequencyId())){
				if ((new Integer(benefitLevelValues.getFrequencyId()).toString()
						.trim()).equalsIgnoreCase(benefitLevelValues
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
        
//      code change for performance improvement - put value in Map instead of ValueBinding
        /*ValueBinding hidLowerLevelValDesc = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productStructureBenefitDefenitionBackingBean.dataHiddenLowerLevelValDesc["+ benefitLevelValues.getLevelId() +"]}");
        hiddenLowerLevelDescription.setValueBinding("value",hidLowerLevelValDesc);*/
        dataHiddenLowerLevelValDesc.put(new Long(benefitLevelValues.getLevelId()), benefitLevelValues.getLowerLevelDescValue());
        
		//END Lower level description in the hidden
        
        lblDesc.getChildren().add(levelDesc);
        lblDesc.getChildren().add(hiddenForDesc);
        if (null != getActionFromSession()
                && !getActionFromSession().equals("VIEW")
                && productStructureGeneralInfoBackingBean.getBenefitTypeTab()
                        .equals("Standard Definition")) {
        	lblDesc.getChildren().add(levelDescInputText);
        }
        lblDesc.getChildren().add(hiddenLowerLevelDescription);
        //CARS END
        //CARS START
        //Term Start
        HtmlInputHidden hiddenForTerm = new HtmlInputHidden();
        hiddenForTerm.setId("hiddenTerm" + i);        
        if (null != benefitLevelValues.getTermDesc()) {
            levelTerm.setValue(benefitLevelValues.getTermDesc().trim().replaceAll(",",", "));
            hiddenForTerm.setValue(benefitLevelValues.getTermDesc().trim().replaceAll(",",", "));
        } else {
            levelTerm.setValue(WebConstants.EMPTY_STRING);
            hiddenForTerm.setValue(WebConstants.EMPTY_STRING);
        }
        levelTerm.setId("Term" +i);
        
//      code change for performance improvement - put value in Map instead of ValueBinding
        /*ValueBinding hiddenTermDesc = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productStructureBenefitDefenitionBackingBean.dataHiddenValTerm["+ benefitLevelValues.getLevelId() +"]}");
        hiddenForTerm.setValueBinding("value",hiddenTermDesc);*/
        this.dataHiddenValTerm.put(new Long(benefitLevelValues.getLevelId()), benefitLevelValues.getTermDesc());
        
        HtmlOutputLabel lblTerm = new HtmlOutputLabel();
        lblTerm.setId("lblTerm"+RandomStringUtils.randomAlphanumeric(15));
        lblTerm.getChildren().add(levelTerm);
        lblTerm.getChildren().add(hiddenForTerm);
        //Term End
        //Qualifier Start
        HtmlInputHidden hiddenForQualifier = new HtmlInputHidden();
		hiddenForQualifier.setId("hiddenQualifier" + i);
		levelQualifier.setId("Qualifier" +i);
        if (null != benefitLevelValues.getQualifierDesc()) {
        	//Check if the frequency value is not 0 
        	if(0 != benefitLevelValues.getFrequencyId()){
        		//Concat the "-" with the  qualifier value 
	            levelQualifier.setValue(" - "+benefitLevelValues.getQualifierDesc()
	                    .trim().replaceAll(",",", "));
        	}else{
        		//Sets the qualifier value alone.
	            levelQualifier.setValue(benefitLevelValues.getQualifierDesc()
	                    .trim().replaceAll(",",", "));        		
        	}
            hiddenForQualifier.setValue(benefitLevelValues.getQualifierDesc().trim());
        } else {
            levelQualifier.setValue(WebConstants.EMPTY_STRING);
            hiddenForQualifier.setValue(WebConstants.EMPTY_STRING);
        } 
        
//      code change for performance improvement - put value in Map instead of ValueBinding
        /*ValueBinding hidddenQualifier = FacesContext.getCurrentInstance().getApplication().createValueBinding("#{productStructureBenefitDefenitionBackingBean.dataHiddenValQualifier["+ benefitLevelValues.getLevelId() +"]}");
        hiddenForQualifier.setValueBinding("value",hidddenQualifier);*/
        this.dataHiddenValQualifier.put(new Long(benefitLevelValues.getLevelId()), benefitLevelValues.getQualifierDesc());
        
        //Frequency Start
        //created lable for frequnecy and qualifier
        HtmlOutputLabel lblFreqQualPage = new HtmlOutputLabel();
        lblFreqQualPage.setId("lblFreqQualPage"+RandomStringUtils.randomAlphanumeric(15));
        //Check if the frequency value is 0
        if(0 != benefitLevelValues.getFrequencyId()){
        	//Set the frequnecy value to the input text
        	levelFrequencyInputTxt.setValue(new Integer(benefitLevelValues.getFrequencyId()).toString().trim());
        	levelFrequencyInputTxt.setId("levelFreqValue" + i);
        	levelFrequencyInputTxt.setStyleClass("formInputField");
        	levelFrequencyInputTxt.setStyle("width:30px;");
        	levelFrequencyInputTxt.setOnkeypress("return isNumber(this);");
        	levelFrequencyInputTxt.setMaxlength(3);
        	ValueBinding levelFreqValueBind = FacesContext.getCurrentInstance()
			.getApplication().createValueBinding("#{productStructureBenefitDefenitionBackingBean.lineFreqValueMap[" + benefitLevelValues.getLevelId() + "]}");
        	levelFrequencyInputTxt.setValueBinding("value",levelFreqValueBind);
        	//Hidden Frequency is added to hold the frequency value
        	HtmlInputHidden hiddenLevelFreqValue = new HtmlInputHidden();
			hiddenLevelFreqValue.setId("hiddenLevelFreqValue"+ i);
			hiddenLevelFreqValue.setValue(new Integer(benefitLevelValues.getFrequencyId()).toString().trim());
			
//			code change for performance improvement - put value in Map instead of ValueBinding
			/*ValueBinding valForhiddenLevelFreq = FacesContext
						.getCurrentInstance().getApplication().createValueBinding(
								"#{productStructureBenefitDefenitionBackingBean.hiddenLineFreqValueMap["
										+ benefitLevelValues.getLevelId() + "]}");
			hiddenLevelFreqValue.setValueBinding("value",
						valForhiddenLevelFreq); 
			*/
			this.hiddenLineFreqValueMap.put(new Long(benefitLevelValues.getLevelId()),new Integer(benefitLevelValues.getFrequencyId()) );


			
        	levelFrequencyInputTxt.setOnchange("return descriptionChange(this)");
        	//if(benefitLevelValues.isLevelHideStatus()){
        		levelFrequencyInputTxt.setDisabled(benefitLevelValues.isLevelHideStatus());
        	//}
        	//Set the frequency value to the output text 
			levelFrequencyOutputTxt.setId("Frequency" +i);
        	levelFrequencyOutputTxt.setValue(new Integer(benefitLevelValues.getFrequencyId()).toString().trim());
        	//Check if it is for view or edit page
        	 if (null != getActionFromSession()
                    && !getActionFromSession().equals("VIEW")
                    && productStructureGeneralInfoBackingBean.getBenefitTypeTab()
                            .equals("Standard Definition")) {
        	 	//Sets the input text to the frequency qualifier label
        	 	lblFreqQualPage.getChildren().add(levelFrequencyInputTxt);	        	
        	 }else{
	            //Sets the output text to the frequency qualifier label
        	 	lblFreqQualPage.getChildren().add(levelFrequencyOutputTxt);
        	 }
        	 //START Hidden Lower level frequency value
        	 HtmlInputHidden hiddenLowerLevelFreqValue = new HtmlInputHidden();
        	 hiddenLowerLevelFreqValue.setId("hiddenLowerLevelFreqValue"+ i);
        	 hiddenLowerLevelFreqValue.setValue(benefitLevelValues.getLowerLevelFrequencyValue());
        	 
//        	code change for performance improvement - put value in Map instead of ValueBinding
        	 /*ValueBinding valForhiddenLowerLevelFreq = FacesContext.getCurrentInstance().getApplication().
			 		createValueBinding("#{productStructureBenefitDefenitionBackingBean.hiddenLowerLevelFreqValueMap["+ benefitLevelValues.getLevelId() + "]}");
        	 hiddenLowerLevelFreqValue.setValueBinding("value",valForhiddenLowerLevelFreq);*/
        	 this.hiddenLowerLevelFreqValueMap.put(new Long(benefitLevelValues.getLevelId()),benefitLevelValues.getLowerLevelFrequencyValue());
			//END Hidden Lower level frequency value	
        	 
        	 
        	 lblFreqQualPage.getChildren().add(hiddenLevelFreqValue);
        	 lblFreqQualPage.getChildren().add(hiddenLowerLevelFreqValue);
        }
        lblFreqQualPage.getChildren().add(levelQualifier);
        lblFreqQualPage.getChildren().add(hiddenForQualifier);
        //Frequency End
        //CARS END
             
        levelPVA.setValue(" ");        
        levelBnftValue.setValue(" ");     
        
        levelReference.setValue(WebConstants.EMPTY_STRING);

        
        hiddenLevelId.setId("hiddenLevelId" + i);
        hiddenLevelId.setValue(new Integer(benefitLevelValues.getLevelId()));
        
        // set the value to the map
        ValueBinding levelIdValBind = FacesContext.getCurrentInstance()
                .getApplication().createValueBinding(
                        "#{productStructureBenefitDefenitionBackingBean.levelIdMap["
                                + i + "]}");
        hiddenLevelId.setValueBinding("value", levelIdValBind);
        

        //Start of modification for performance issue
        hiddenLevelFlag.setId("hiddenLevelFlag"+"A"+i+"A"+lineSize+"A"+lineCount);
        hiddenLevelFlag.setValue(""+benefitLevelValues.isLevelHideStatus());
        
//      code change for performance improvement - put value in Map instead of ValueBinding
        /*ValueBinding oldLevelFlag = FacesContext.getCurrentInstance().getApplication().
					createValueBinding("#{productStructureBenefitDefenitionBackingBean." +
							"hiddenLevelFlagMap["+benefitLevelValues.getLevelId()+"]}");
        hiddenLevelFlag.setValueBinding("value",oldLevelFlag);*/
        hiddenLevelFlagMap.put(new Long(benefitLevelValues.getLevelId()), new Boolean(benefitLevelValues.isLevelHideStatus()));
        
        
        //End of modification
        deleteImage.setUrl("../../images/delete.gif");
        deleteImage.setStyle("cursor:hand;");
        deleteImage.setId("deleteImage" + i);        

        deleteButton.setValue("DeleteButton");
        deleteButton.setImage("../../images/delete.gif");
        deleteButton.setTitle("Delete");
        deleteButton.setStyle("border:0;");
        deleteButton.setAlt("Delete");
        deleteButton.setOnclick("changeColour(\'" + i + "','" + lineSize
                + "','" + rowNum + "\');return false;");
        deleteButton.setId("deleteButton" + i);
        
        checkbox.setId("checkBox"+"A"+i+"A"+lineSize+"A"+lineCount);
    	checkbox.setValue(Boolean.valueOf(benefitLevelValues.isLevelHideStatus()));
    	checkbox.setValueBinding("value",FacesContext.getCurrentInstance().getApplication().createValueBinding(
				"#{productStructureBenefitDefenitionBackingBean.levelVisibleMap[" +benefitLevelValues.getLevelId()+"]}"));
    	checkbox.setOnclick("checkTheCorrespondingBenefitLines(this);");
        //checkbox.setId("checkBox"+i);        
        
        lblImage.setFor("levelDesc" + i);
        //lblImage.setId("lblImage" + i);
        
        hiddenLineSize.setId("hiddenLineSize" + i);
        hiddenLineSize.setValue(new Integer(lineSize));
        
        hiddenRowSize.setId("hiddenRowNum" + i);
        hiddenRowSize.setValue(new Integer(rowNum));
        //ProductStructureGeneralInfoBackingBean productStructureGeneralInfoBackingBean = new ProductStructureGeneralInfoBackingBean();       
        
        levelPVA.setValue(" ");
        
        levelPVA.setValue(" ");
        lblImage.getChildren().add(hiddenLevelFlag);
        //checks if it is a view mode
        if (null != getActionFromSession()
                && !getActionFromSession().equals("VIEW")
                && productStructureGeneralInfoBackingBean.getBenefitTypeTab()
                        .equals("Standard Definition")) {
            //lblImage.getChildren().add(deleteButton);

            lblImage.getChildren().add(hiddenLevelId);
            lblImage.getChildren().add(hiddenLineSize);
            lblImage.getChildren().add(hiddenRowSize);
            


            panel.getChildren().add(lblDesc);
            //Setting the term label in the panel
            panel.getChildren().add(lblTerm);
            //Setting the frequency and qualifier label in the panel
            panel.getChildren().add(lblFreqQualPage);
            panel.getChildren().add(levelPVA);
          //  panel.getChildren().add(dummyText);
           // lblImage.getChildren().add(checkbox);
            panel.getChildren().add(levelBnftValue);
            panel.getChildren().add(levelReference);
        } else {
            lblImage.getChildren().add(hiddenLevelId);
            lblImage.getChildren().add(hiddenLineSize);
            lblImage.getChildren().add(hiddenRowSize);

            panel.getChildren().add(lblDesc);
            //Setting the term label in the panel
            panel.getChildren().add(lblTerm);
            //Setting the frequency and qualifier label in the panel
            panel.getChildren().add(lblFreqQualPage);
            panel.getChildren().add(levelPVA);
            panel.getChildren().add(dummyText);
            panel.getChildren().add(levelBnftValue);
            panel.getChildren().add(levelReference);
        }
        if (null != getActionFromSession()
                && !getActionFromSession().equals("VIEW")
                && productStructureGeneralInfoBackingBean.getBenefitTypeTab()
                        .equals("Standard Definition")) {
            panel.getChildren().add(lblImage);
            
        }
        if(productStructureGeneralInfoBackingBean.getBenefitTypeTab()
                .equals("Standard Definition")){
        	panel.getChildren().add(dummyText2);
        }
        
        if (null != getActionFromSession()
                && !getActionFromSession().equals("VIEW")
                && productStructureGeneralInfoBackingBean.getBenefitTypeTab()
                        .equals("Standard Definition")) {
        	panel.getChildren().add(checkbox);
        }
        else if(null != getActionFromSession()
                && !getActionFromSession().equals("VIEW")
                && productStructureGeneralInfoBackingBean.getBenefitTypeTab()
                        .equals("Standard Definition") ){
      		HtmlOutputText dummyText1 = new HtmlOutputText();
      		dummyText.setValue(" ");
        	panel.getChildren().add(dummyText1);
      }        
    }


    /**
     * Method gets the value in the test box
     * 
     * @param
     * @return String
     */
    public String save() {
    	TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23914_Product_Structre_Coverage","ProductStructureBenefitDefenitionBackingBean","save"));
    	
    	ErrorMessage errorMessage = null;
    	String chkLevelId  = null;
    	getRequest().setAttribute("RETAIN_Value", "");
    	Logger.logInfo("Entering the method for saving benefit defenition changes");
        //gets the level ids to delete which contains the ids with ~ appended
        boolean validationFlag = true;
        boolean isFlagChanged = false;
		boolean levelDescChecked = false;
		boolean isFreqErrorChecked = false;
		boolean notOneFlag = true;
        List changedIds = new ArrayList(1);
        List tempvalidationList = new ArrayList();
        //String dataTypeId = null;
        String levelDesc = null;
        String levelIds = this.getLevelsToDelete();
        Boolean levelHideFlag = Boolean.FALSE;
	    Set keys = benefitValueMap.keySet();
		Iterator valueIter = keys.iterator();
		Long lineIdKeyForValue = null;
    	String levelIdFromLine = null;
		List messages = new ArrayList();
    	//String lineIdFromMap = null;
       List benefitLineVOs = new ArrayList(1);
       int noOfBenefitLevels = 0;
       // boolean benefitFlag = false;
		Set sysIdKeys = customizedSysIdMap.keySet();
		Iterator idIterator = sysIdKeys.iterator();
		String benefitValueLineId = null;
		String truncatedLvlDesc = null;
		
//		code change for performance improvement - getting Maps from session
		dataHiddenValTerm = (HashMap) getProductStructureSessionObject().getDataHiddenValTermMap();		
		hiddenLineFreqValueMap = (HashMap) getProductStructureSessionObject().getHiddenLineFreqValueMap();
		hiddenLowerLevelFreqValueMap =(HashMap) getProductStructureSessionObject().getHiddenLowerLevelFreqValueMap();
		hiddenLevelFlagMap = (HashMap) getProductStructureSessionObject().getHiddenLevelFlagMap();
		hiddenLineFlagMap = (HashMap) getProductStructureSessionObject().getHiddenLineFlagMap();
		hiddenBenefitValueMap = (HashMap) getProductStructureSessionObject().getHiddenBenefitValueMap();
		oldDescOutputTxt = (HashMap) getProductStructureSessionObject().getOldDescOutputTxtMap();
		
		
        List levelIdsList = new ArrayList(1);
        if (null != levelIds && !levelIds.equals("")) {
            // check whether ~ is there
            int indexOfTilda = levelIds.indexOf("~");
            if (indexOfTilda != -1) {
                StringTokenizer tokenizer = new StringTokenizer(levelIds, "~");
                while (tokenizer.hasMoreTokens()) {
                    levelIdsList.add(tokenizer.nextToken());
                }
            } else {
                levelIdsList.add(levelIds);
            }
        }
        if (null != this.getLevelVisibleMap() && null!=this.getLineIdMap() && null!=this.getCustomizedSysIdMap()) {
            // get the key set from the map

            Set levelIdMapKeySet = this.getLevelVisibleMap().keySet();
            // iterate the keyset and match the corresponding key and take
            // the selected levelId
            Iterator levelIdMapKeySetIterator = levelIdMapKeySet.iterator();
            
            while (levelIdMapKeySetIterator.hasNext()) {
            	
                Object key = levelIdMapKeySetIterator.next();
                
                String levelId = key.toString();
				levelDescChecked = false;
                
                
                levelHideFlag = (Boolean)this.getLevelVisibleMap().get(((key)));
                Set lineIdKeySet = this.getLineIdMap().keySet();
		        // lineId Iterator
		        Iterator lineIdIterator = lineIdKeySet.iterator();
		        isFreqErrorChecked = false;

		        // iterate the lineIdKeySet
		        while (lineIdIterator.hasNext() && valueIter.hasNext() && idIterator.hasNext()) {
		        	
	  	            // get the lineIdKey
		        	lineIdKeyForValue = (Long)lineIdIterator.next();
		        	String lineId = lineIdKeyForValue.toString();
                	if(null != this.getLineIdMap() && null != this.getLineIdMap().get(new Long(lineId)))
                		benefitValueLineId = (this.getLineIdMap().get(new Long(lineId))).toString();
		        	StringTokenizer tokenizerForMap = new StringTokenizer(benefitValueLineId,":");
		        	while(tokenizerForMap.hasMoreTokens()){
		        		levelIdFromLine = tokenizerForMap.nextToken();
		        	if(levelIdFromLine.equals(levelId)){
		        		 Boolean lineChecked = (Boolean) checkBoxMap.get(new Long(lineId));
		        		ProductStructureBenefitCustomizedVO benefitLineValues = new ProductStructureBenefitCustomizedVO();
		        		String value = (String) benefitValueMap.get(new Long(lineId));
		        		String customizedSysId = ((String)customizedSysIdMap.get(lineId));
  		        		 benefitLineValues.setBenefitCustomizedSysId(Integer.parseInt(customizedSysId));
		        		 if(levelHideFlag.booleanValue()){
		        		 	benefitLineValues.setBenefitLevelHideFlag("T");
		  	          		benefitLineValues.setBenefitLineHideFlag("T");
		  	          		noOfBenefitLevels++;
		  	          		//benefitFlag = true;
		        		 }
		        		 else{
		        		 	benefitLineValues.setBenefitLevelHideFlag("F");
		        		 	noOfBenefitLevels++;
		        		 	//benefitFlag = false;
		        		 }
		        		 
	  		        		if(!levelHideFlag.booleanValue()){
	  		  	  	            if(null!=lineChecked && lineChecked.booleanValue())
	  		  	  	            	benefitLineValues.setBenefitLineHideFlag("T");
	  		  	  	            else
	  		  	  	            	benefitLineValues.setBenefitLineHideFlag("F");
	  		  	            }
	  		        		 // get the levelId separately form the lineLevelVal
	  		              String levelLineVal = this.getLineIdMap().get(new Long(lineId)).toString();
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
	  		              }
	  		              if (null != benefitValueMap && null != appendedLineVal
	  		                      && null != benefitValueMap.get(new Long(appendedLineVal)))
	  		                  appendedBnftVal = (benefitValueMap
	  		                          .get(new Long(appendedLineVal))).toString();
	  		              // check whether the override value is Valid.
	  		              if (!isValid(appendedDataTypeId, appendedBnftVal, appendedLevelDesc)) {
	  		                  //return "componentBenefitDefinition";
	  		                  validationFlag = false;
	  		              }
		        		
	  		        	if (null != lineId){
	  		        		
		  	                benefitLineValues.setBenefitLineId((new Integer(lineId))
		  	                        .intValue());
		  	                if(null!= value )
		  	                	benefitLineValues.setOverridedValue(value.trim());
	  		        	}
	  		        	//Start of modification for performace issue 
	  		        	Object oldLevelFlag = this.getHiddenLevelFlagMap().get(new Long (levelId));
                    	boolean lineFlag = true;
                    	if(levelHideFlag.booleanValue()){
                        	if(null != oldLevelFlag  
                        			&& oldLevelFlag.toString().equals("false")){
                        		isFlagChanged = true;
                        		if(!changedIds.contains(levelId))
                        			changedIds.add(levelId);
                        		benefitLineValues.setModified(true);
                        	}
                    	}else{
                    		                  	    Object oldLineFlagValue = hiddenLineFlagMap.get(new Long (lineId));
                        	    if(null!=oldLineFlagValue && oldLineFlagValue.toString().equals("false")){
                        	        lineFlag = false;
                        	    }else{
                        	        lineFlag = true;
                        	    }
                        	    if( !(lineChecked.booleanValue() == lineFlag)){
                            		isFlagChanged = true;
                            		if(!changedIds.contains(levelId))
                            			changedIds.add(levelId);
                        	    	benefitLineValues.setModified(true);
                            	}
                    	}
                    	
                    	Object oldBenefitValue = hiddenBenefitValueMap.get(new Long(lineId));
                    	
                    	if(null != oldBenefitValue && null != value 
                            			&& !(oldBenefitValue.toString().equals(value))){
                    		benefitLineValues.setModified(true);
                    		//isSeqChanged = true;
                    	}
                    	  //end of modification
                    	//CARS START
                    	//Frequency Start
                    	Object oldFrequencyObj = hiddenLineFreqValueMap.get(new Long(levelId));
    		        	
    		        	Object newFrequencyObj = lineFreqValueMap.get(new Long(levelId));
    		        	
    		        	int frequency;
    		        	int oldFrequency;//Changed as part of stabilization release on 07-02-2011
    		        	int noOfTokens;
    		        	boolean isNumber = false;
    		        	boolean isFrequencyChanged = false;
    		        	//Null checking the old freqeuncy value
    		        	if(null != oldFrequencyObj && !(oldFrequencyObj.equals(""))){
    		        		oldFrequency = new Integer(oldFrequencyObj.toString()).intValue();
    		        		//Checking if the old frequency value is 0
    		        		if("0" != oldFrequencyObj.toString()){
	    		        		//Checking whether frequency value is empty
	    		        		if(!(WebConstants.EMPTY_STRING.equalsIgnoreCase(newFrequencyObj.toString().trim()))){
		    		        		isNumber = WPDStringUtil.isNumber(newFrequencyObj.toString());
		    		        		//Checking frequency value is a number
		    		        		if(isNumber){
		    		        			frequency = new Integer(newFrequencyObj.toString()).intValue();
		    		        			//Checking whether frequency value is set 0 and if there a change in frequency value
		    		        			if(0 != frequency ){
		    		        				benefitLineValues.setBenefitLevelFrequency(new Integer(newFrequencyObj.toString()).intValue());
		    		        				if(!(oldFrequency == frequency)){
		    		        					benefitLineValues.setModified(true);
		    		        					isFrequencyChanged = true;
		    		        				}
		    		        			}else if(notOneFlag){//set the error message for zero can not be a value
		    		        				if(!tempvalidationList.contains(levelId.toString().trim())){
			    		        				tempvalidationList.add(levelId.toString().trim());
			    		        				validationFlag = false;
			    		        				errorMessage = new ErrorMessage(WebConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_CORRECT);
			    		        				//errorMessage.setParameters(new String[] {oldDescOutputTxt.get(new Long(levelId.toString().trim())).toString().trim()});
			    		        				validationMessages.add(errorMessage);
			    		        				notOneFlag = false;
		    		        				}
		    		        			}
		    		        		}else if(!levelId.equals(chkLevelId)){//set the error message for not a number
		    		        			validationFlag = false;
		    		        			errorMessage = new ErrorMessage(BusinessConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_NUMBER);
		    		        			errorMessage.setParameters(new String[] {oldDescOutputTxt.get(new Long(levelId.toString().trim())).toString().trim()});
		    		        			validationMessages.add(errorMessage);
		    		        			chkLevelId = levelId;
		    		        		}
	    		        		}else if(!levelId.equals(chkLevelId)){//set error message frequency value is empty
	    		        			validationFlag = false;
	    		        			errorMessage = new ErrorMessage(BusinessConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_EMPTY);
	    		        			errorMessage.setParameters(new String[] {oldDescOutputTxt.get(new Long(levelId.toString().trim())).toString().trim()});
	    		        			if(!isFreqErrorChecked)
	    		        				validationMessages.add(errorMessage);
	    		        			isFreqErrorChecked = true;
	    		        			chkLevelId = levelId;
	    		        		}
    		        		}
    		        	}
    		        	//Frequency End
    		        	//Description Start
						boolean isDescriptionTrunkated = false;
    		        	Object newDescriptionValue;
    		        	Object oldDescriptionValue;
						Object oldFrequencyValue;
						Object newFrequencyValue;
						Object termValue;
						Object qualifierValue;
						String newDescription;
						Object lowerLevelFrequencyValue;
						Object lowerLevelDescriptionValue;
						//old description value binded with outpur text
						oldDescriptionValue = oldDescOutputTxt.get(new Long(levelId.toString().trim()));
						//new description value binded with input text
						newDescriptionValue = dataHiddenValDesc.get(new Long(levelId.toString().trim()));
						//frequency value binded with input hidden
						oldFrequencyValue = hiddenLineFreqValueMap.get(new Long(levelId.toString().trim()));
						//frequency value binded with input text
						newFrequencyValue = lineFreqValueMap.get(new Long(levelId.toString().trim()));
						//term value binded with input text
												
						termValue = dataHiddenValTerm.get(new Long(levelId.toString().trim()));
						
//						code change for performance improvement
						dataHiddenValQualifier = (HashMap) getProductStructureSessionObject()
							.getDataHiddenValQualifierMap();
						
						
						//qualifier value binded with input text
						qualifierValue = dataHiddenValQualifier.get(new Long(levelId.toString().trim()));	
						lowerLevelFrequencyValue = hiddenLowerLevelFreqValueMap.get(new Long(levelId.toString().trim()));
//						code change for performance improvement		
						dataHiddenLowerLevelValDesc = (HashMap) getProductStructureSessionObject()
							.getDataHiddenLowerLevelValDescMap();
						
						
						lowerLevelDescriptionValue = dataHiddenLowerLevelValDesc.get(new Long(levelId.toString().trim()));
						if(!StringUtil.isEmpty(newDescriptionValue)){
								if(oldDescriptionValue.toString().trim().toUpperCase().equals(newDescriptionValue.toString().trim().toUpperCase())){
									//Checking null all the object(description, term, qualifier, frequency)						
										if((!StringUtil.isEmpty(qualifierValue)) && (!StringUtil.isEmpty(termValue)) && (!StringUtil.isEmpty(oldFrequencyValue)) && (!StringUtil.isEmpty(newFrequencyValue)) && (WPDStringUtil.isNumber(newFrequencyObj.toString().trim()))){
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
									                            	qualifier = benefitQualifer;
								                            		//Flag is set false to restrict the entry for the second time
								                            		flag = false;
								                            	}else{
								                            		//Setting the term description value after the comma seperation
								                            		qualifier = qualifier+WebConstants.SPACE_STRING+ benefitQualifer;
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
									                            	term = benefitTerm;
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
												//Fix Start
												if(changeDesc.length() >= 32){
													changeDesc= changeDesc.substring(0,32).trim();
							                		//isDescriptionTrunkated = true;
												}	
												//Fix End
								                if(description.equalsIgnoreCase(changeDesc)){
								                	if(!(frequencyVal.equalsIgnoreCase(newFrequencyValue.toString().trim()))){
									                	int frequencyValue = new Integer(newFrequencyValue.toString().trim()).intValue();
									                	frequencyVal = frequencyValue+WebConstants.EMPTY_STRING;
									                	if(frequencyVal.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)){									
															//description is combination of term qualifier and frequency
										                	changeDesc  = term+WebConstants.PER_STRING+qualifier;
										                }else{
										                	changeDesc  = term+WebConstants.PER_STRING+frequencyVal+WebConstants.SPACE_STRING+qualifier;
										                }
									                	if(changeDesc.length() > 32){
									                		changeDesc= changeDesc.substring(0,32).trim();
									                		isDescriptionTrunkated = true;
									                		//validationFlag = false;
									                		//validationMessages.add(new ErrorMessage(WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_LENGTH));
									                	}
									                	benefitLineValues.setBenefitLevelDescription(changeDesc);
								                	}else{
								                		if(description.length() > 32){
								                			description = description.substring(0,32).trim();
								                		}
								                		benefitLineValues.setBenefitLevelDescription(description);
								                	}
								                }else{
								                	//CUSTOM START
								                	if(null != lowerLevelDescriptionValue){
								                		if(lowerLevelFrequencyValue.toString().toUpperCase().trim().equals(newFrequencyValue.toString().toUpperCase().trim())){
															//benefitLineValues.setBenefitLevelFrequency(new Integer(lowerLevelFrequencyValue.toString()).intValue());
															String lvlDescriptionValue = lowerLevelDescriptionValue.toString().toUpperCase().trim();
															if(lvlDescriptionValue.length() > 32){
																lvlDescriptionValue = lvlDescriptionValue.substring(0,32).trim();
																benefitLineValues.setBenefitLevelDescription(lvlDescriptionValue);
										                		isDescriptionTrunkated = true;
															}
															benefitLineValues.setBenefitLevelDescription(lvlDescriptionValue);								                		
														}else{
									                	//CUSTOM END
										                	if(description.length() > 32){
									                			description = description.substring(0,32).trim();
									                		}
									                		benefitLineValues.setBenefitLevelDescription(description);
														}
								                	}
								                }
									}else{
										description = oldDescriptionValue.toString().toUpperCase().trim();
										if(description.length() > 32){
											description = description.substring(0,32).trim();
					                		isDescriptionTrunkated = true;											
										}
										benefitLineValues.setBenefitLevelDescription(oldDescriptionValue.toString().toUpperCase().trim());
									}
								}else{
									if(null != newDescriptionValue.toString().toUpperCase().trim()){
										newDescription = newDescriptionValue.toString().toUpperCase().trim();
										if(newDescription.length() > 32){
											newDescription = newDescription.substring(0,32).trim();
											isDescriptionTrunkated = true;
										}
										benefitLineValues.setBenefitLevelDescription(newDescription);
									}
									benefitLineValues.setModified(true);
								}
								if(isDescriptionTrunkated && (!levelDescChecked)){
									truncatedLvlDesc = benefitLineValues.getBenefitLevelDescription();
									InformationalMessage informationalMessage = new InformationalMessage(WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_LENGTH);
									informationalMessage.setParameters(new String[] { truncatedLvlDesc });
									messages.add(informationalMessage);
									isDescriptionTrunkated = false;
									levelDescChecked = true;
									addAllMessagesToRequest(messages);
								}
							
						}else if(validationFlag){//set error message description value can not be empty
							validationFlag = false;
		        			validationMessages.add(new ErrorMessage(WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_REQUIRED));
						}
    		        	//Description End
    		        	//CARS END
	  		        	benefitLineVOs.add(benefitLineValues);
		        	}
		        	}
		        	
		        	
		        }
            }
            
                   
        	if(!checkForAllLevelsSelected(benefitLineVOs,noOfBenefitLevels)){
        		validationMessages.add(new InformationalMessage(WebConstants.MANDATORY_BENEFIT_VISIBLE_REQUIRED));
        		validationFlag = false;
        	}
        }
        
        if (validationFlag) {
            //the request is made
            SaveProductStructureBenefitDefinitionRequest saveProductStructureBenefitDefinitionRequest = (SaveProductStructureBenefitDefinitionRequest) this
                    .getServiceRequest(ServiceManager.SAVE_PRODUCT_STRUCTURE_BENEFIT_DEFINITION_REQUEST);

            String standardKey = (String) getSession().getAttribute(
                    "STANDARD_BNFT_KEY");
            int key = Integer.parseInt(standardKey);
            saveProductStructureBenefitDefinitionRequest
                    .setStandardBenefitKey(key);
            if(isFlagChanged){
            	saveProductStructureBenefitDefinitionRequest.setFlagChanged(true);
            	saveProductStructureBenefitDefinitionRequest.setChangedIds(changedIds);
            	saveProductStructureBenefitDefinitionRequest.setBCompName((String)getSession().getAttribute("BENEFIT_COMP_NAME"));
            }

            String benefitComponentKey = (String) getSession().getAttribute(
                    "BNFT_CMPNT_KEY");
            int bnftCompkey = Integer.parseInt(benefitComponentKey);
            saveProductStructureBenefitDefinitionRequest
                    .setBenefitComponentId(bnftCompkey);

            ProductStructureVO productStructureVO = new ProductStructureVO();
            productStructureVO = this
                    .getProductStructureFromSession(productStructureVO);
            saveProductStructureBenefitDefinitionRequest
                    .setProductStructureVO(productStructureVO);
            
            //Setting the benefit hide status true if all the level hide
            //status is true
            if(benefitFlag)
            	saveProductStructureBenefitDefinitionRequest.setBenefitHideFlag("T");
            else
            	saveProductStructureBenefitDefinitionRequest.setBenefitHideFlag("F");
            //the set of LineVOs is set to the request
            saveProductStructureBenefitDefinitionRequest
                    .setBenefitLinesList(benefitLineVOs);

            if (null != this.deleteLevelList)
                saveProductStructureBenefitDefinitionRequest
                        .setDeleteBenefitLevelList(this.deleteLevelList);

            //gets the response
            SaveProductStructureBenefitDefinitionResponse saveProductBenefitDefinitionResponse = (SaveProductStructureBenefitDefinitionResponse) executeService(saveProductStructureBenefitDefinitionRequest);
            if(null != saveProductBenefitDefinitionResponse){
	            this.setBenefitDefinitonsList(saveProductBenefitDefinitionResponse
	                    .getBenefitDefiniitonList());
	            messages.addAll(saveProductBenefitDefinitionResponse
		                   .getMessages());	            
            }
			addAllMessagesToRequest(messages);
        } else {  
        	addAllMessagesToRequest(validationMessages);
            return WebConstants.EMPTY_STRING;
        }
        Logger
                .logInfo("Returning the method for saving benefit defenition changes");
        Logger.logInfo(th.endPerfLogging());
        
        List benefitDefnList = this.getBenefitDefinitonsList();
        if(null != benefitDefnList && !benefitDefnList.isEmpty())
        	return "displayPSBenftDefnPage";
        else
        	return "productStructureAssociatedBenefits";
    }

    public boolean checkForAllLineSelected(int noOfLinesChanged){
    	boolean allLineHideStatus = false;
    	int noOfLines = 0;
    	int noOfHidLines = 0;
    	
    	if(!isShowHidden()){
    		if(null!=this.getLineIdMap() && !this.getLineIdMap().isEmpty()){
    			noOfLines = this.getLineIdMap().size();
    		}
		}else{
		 	if(null!=this.getLineIdMap() && !this.getLineIdMap().isEmpty()){
			  	noOfLines = this.getLineIdMap().size();
			}
		  	Set lineIdSet = this.getLineIdMap().keySet();
		  	Iterator lineIter = lineIdSet.iterator();
		  	while(lineIter.hasNext()){
		  		Long id = (Long)lineIter.next();
		  		String lineId = id.toString();
		  		if(null != this.getLineIdMap() && null != this.getLineIdMap().get(new Long(lineId))){
	          		Boolean lineChecked = (Boolean) checkBoxMap.get(new Long(lineId));
	          		if(lineChecked.booleanValue()){
	          			noOfHidLines++;
	          		}
		  		}
		  	}
		  }
    	
    	if(noOfLines == noOfLinesChanged){
    		allLineHideStatus = true;
  		}else if(noOfLines == noOfHidLines){
  			allLineHideStatus = true;
  		}
  		
    	return allLineHideStatus;
    }
    
    public boolean checkForAllLevelsSelected(List benefitDefinitions,int noOfbenefitLevels){
    	 boolean benefitHideStatus = true;
     	// int noOfbenefitLevels = 0;
    	 int levelsChecked = 0;
    	 int noOfBenefits = 0;
    	
    	 RetrieveComponentFromTreeRequest retrieveComponentFromTreeRequest = new RetrieveComponentFromTreeRequest();
         RetrieveComponentFromTreeResponse retrieveComponentFromTreeResponse = null;
         
    	 //Setting benefit component in the request
    	 int bnftCmpntId = this.getBenefitComponentIdFromSession();
         retrieveComponentFromTreeRequest.setBenefitComponentId(bnftCmpntId);
         
         //Setting productStructure in the request.
         ProductStructureVO productStructureVO = new ProductStructureVO();
         productStructureVO = getProductStructureFromSession(productStructureVO);
         retrieveComponentFromTreeRequest.setProductStructure(productStructureVO);
         
 	 	retrieveComponentFromTreeRequest.setAction(RetrieveComponentFromTreeRequest.PRODUCT_STRUCTURE_BENEFIT_RETRIEVE);
        //	Executes the service and fetches the response
 	 	retrieveComponentFromTreeResponse =(RetrieveComponentFromTreeResponse)this.executeService(retrieveComponentFromTreeRequest);
 	 	
 	 	// 	 Extracts the benefit details from the response
        if(null!= retrieveComponentFromTreeResponse.getBenefitDetails() && !retrieveComponentFromTreeResponse.getBenefitDetails().isEmpty())
        	noOfBenefits =  retrieveComponentFromTreeResponse.getBenefitDetails().size();
        
	    //for getting the total number of benefit levels and the number of levels checked	
	    for(int i=0; i < benefitDefinitions.size(); i++){
	    	ProductStructureBenefitCustomizedVO benefitDefinition = (ProductStructureBenefitCustomizedVO)benefitDefinitions.get(i);
	    //	noOfbenefitLevels++;
	    	if(benefitDefinition.getBenefitLevelHideFlag().equals("T"))
	    		levelsChecked++;
	    }
	    //If the no of benefits is 1 and all the levels checked sets the status false	
	    if(noOfBenefits<=1 && noOfbenefitLevels==levelsChecked)
	    	benefitHideStatus = false;
	    if(noOfBenefits>1 && noOfbenefitLevels==levelsChecked)
	    	benefitFlag = true;
	    else
	    	benefitFlag = false;
	    
    	return benefitHideStatus;
    }

    /**
     * 
     * @return boolean
     */
    private boolean isValid(String dataTypeId, String value, String levelDesc) {
        //List validationMessages = null;
        boolean isValid = true;
        String sysDataType = null;
        String dataTypeName = null;
        int dataType = 0;
        ErrorMessage errorMessage = null;
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

        if (!"null".equals(value) && null != value && !"".equals(value) && null != sysDataType
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
            isValid = false;
        }
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

    /*
     * notes Associated to a benefit 
     *  return list of notes
     * 
     */
    public String loadStandardBenefitNotes()
    {
   		 if (null != getActionFromSession()
                   && !getActionFromSession().equals("VIEW"))
    		return "benefitNotes";
  		 else
    		return "benefitNotesView";
    		 
    }
       /**
     * Sets the panel
     * 
     * @param panel.
     */
    public void setPanel(HtmlPanelGrid panel) {
        this.panel = panel;
    }


    /**
     * Returns the datafieldMap
     * 
     * @return Map datafieldMap.
     */

    public Map getDatafieldMap() {
        return datafieldMap;
    }


    /**
     * Sets the datafieldMap
     * 
     * @param datafieldMap.
     */
    public void setDatafieldMap(Map datafieldMap) {
        this.datafieldMap = datafieldMap;
    }


    /**
     * Returns the datafieldMapForREF
     * 
     * @return Map datafieldMapForREF.
     */

    public Map getDatafieldMapForREF() {
        return datafieldMapForREF;
    }


    /**
     * Sets the datafieldMapForREF
     * 
     * @param datafieldMapForREF.
     */
    public void setDatafieldMapForREF(Map datafieldMapForREF) {
        this.datafieldMapForREF = datafieldMapForREF;
    }


    /**
     * Returns the createdBy
     * 
     * @return String createdBy.
     */

    public String getCreatedBy() {
        return createdBy;
    }


    /**
     * Sets the createdBy
     * 
     * @param createdBy.
     */

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    /**
     * Returns the creationDate
     * 
     * @return String creationDate.
     */

    public Date getCreationDate() {
        return creationDate;
    }


    /**
     * Sets the creationDate
     * 
     * @param creationDate.
     */

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * Returns the description
     * 
     * @return String description.
     */

    public String getDescription() {
        return description;
    }


    /**
     * Sets the description
     * 
     * @param description.
     */

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Returns the effectiveDate
     * 
     * @return String effectiveDate.
     */

    public String getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * Sets the effectiveDate
     * 
     * @param effectiveDate.
     */

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * Returns the entity
     * 
     * @return String entity.
     */

    public String getEntity() {
        return entity;
    }


    /**
     * Sets the entity
     * 
     * @param entity.
     */

    public void setEntity(String entity) {
        this.entity = entity;
    }


    /**
     * Returns the expiryDate
     * 
     * @return String expiryDate.
     */

    public String getExpiryDate() {
        return expiryDate;
    }


    /**
     * Sets the expiryDate
     * 
     * @param expiryDate.
     */

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }


    /**
     * Returns the group
     * 
     * @return String group.
     */

    public String getGroup() {
        return group;
    }


    /**
     * Sets the group
     * 
     * @param group.
     */

    public void setGroup(String group) {
        this.group = group;
    }


    /**
     * Returns the lob
     * 
     * @return String lob.
     */

    public String getLob() {
        return lob;
    }


    /**
     * Sets the lob
     * 
     * @param lob.
     */

    public void setLob(String lob) {
        this.lob = lob;
    }


    /**
     * Returns the minorHeading
     * 
     * @return String minorHeading.
     */

    public String getName() {
        return minorHeading;
    }


    /**
     * Sets the name
     * 
     * @param name.
     */

    public void setName(String name) {
        this.minorHeading = name;
    }


    /**
     * Returns the updatedBy
     * 
     * @return String updatedBy.
     */

    public String getUpdatedBy() {
        return updatedBy;
    }


    /**
     * Sets the updatedBy
     * 
     * @param updatedBy.
     */

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }


    /**
     * Returns the updatedDate
     * 
     * @return String updatedDate.
     */

    public Date getUpdatedDate() {
        return updatedDate;
    }


    /**
     * Sets the updatedDate
     * 
     * @param updatedDate.
     */

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }


    /**
     * Returns the productStructureVO
     * 
     * @return ProductStructureVO productStructureVO.
     */

    public ProductStructureVO getProductStructureVO() {
        return productStructureVO;
    }


    /**
     * Sets the productStructureVO
     * 
     * @param productStructureVO.
     */

    public void setProductStructureVO(ProductStructureVO productStructureVO) {
        this.productStructureVO = productStructureVO;
    }


    /**
     * Returns the minorHeading
     * 
     * @return String minorHeading.
     */

    public String getMinorHeading() {
        return minorHeading;
    }


    /**
     * Sets the minorHeading
     * 
     * @param minorHeading.
     */

    public void setMinorHeading(String minorHeading) {
        this.minorHeading = minorHeading;
    }


    /**
     * Returns the providerArrangement
     * 
     * @return String providerArrangement.
     */

    public String getProviderArrangement() {
        return providerArrangement;
    }


    /**
     * Sets the providerArrangement
     * 
     * @param providerArrangement.
     */

    public void setProviderArrangement(String providerArrangement) {
        this.providerArrangement = providerArrangement;
    }


    /**
     * Returns the qualifier
     * 
     * @return String qualifier.
     */

    public String getQualifier() {
        return qualifier;
    }


    /**
     * Sets the qualifier
     * 
     * @param qualifier.
     */

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }


    /**
     * Returns the term
     * 
     * @return String term.
     */

    public String getTerm() {
        return term;
    }


    /**
     * Sets the term
     * 
     * @param term.
     */

    public void setTerm(String term) {
        this.term = term;
    }


    /**
     * Returns the dataType
     * 
     * @return String dataType.
     */

    public String getDataType() {
        return dataType;
    }


    /**
     * Sets the dataType
     * 
     * @param dataType.
     */

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }


    /**
     * @return benefitType
     * 
     * Returns the benefitType.
     */
    public String getBenefitType() {
        return benefitType;
    }


    /**
     * @param benefitType
     * 
     * Sets the benefitType.
     */
    public void setBenefitType(String benefitType) {
        this.benefitType = benefitType;
    }


    /**
     * @return mandateType
     * 
     * Returns the mandateType.
     */
    public String getMandateType() {
        return mandateType;
    }


    /**
     * @param mandateType
     * 
     * Sets the mandateType.
     */
    public void setMandateType(String mandateType) {
        this.mandateType = mandateType;
    }


    /**
     * @return stateCode
     * 
     * Returns the stateCode.
     */
    public String getStateCode() {
        return stateCode;
    }


    /**
     * @param stateCode
     * 
     * Sets the stateCode.
     */
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }


    /**
     * @return ruleId
     * 
     * Returns the ruleId.
     */
    public String getRuleId() {
        return ruleId;
    }


    /**
     * @param ruleId
     * 
     * Sets the ruleId.
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
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
     * Returns the lineIdMap
     * 
     * @return List lineIdMap.
     */
    public HashMap getLineIdMap() {
        return lineIdMap;
    }


    /**
     * Sets the lineIdMap
     * 
     * @param lineIdMap.
     */
    public void setLineIdMap(HashMap lineIdMap) {
        this.lineIdMap = lineIdMap;
    }
	/**
	 * @return Returns the benefitLineNotes.
	 */
	public List getBenefitLineNotes() {
		
		// create request
	    	ProductStructureNotesRequest productStructureNotesRequest = (ProductStructureNotesRequest)
				this.getServiceRequest(ServiceManager.PRODUCT_STRUCTURE_NOTES_REQUEST);
			 // Set the various parameters to the request
	    	  	int bnftCmpnt = this.getBenefitComponentIdFromSession();
	    	  	String  benefitId =  getRequest().getParameter("secondaryEntityId");
	    	  	productStructureNotesRequest.setEntityId(bnftCmpnt);
	    	  	productStructureNotesRequest.setEntityType("ATTACHCOMP");
	    	  	productStructureNotesRequest.setMaxResultSize(WebConstants.MAX_SEARCH_RESULT_SIZE);
	    	  	productStructureNotesRequest.setSecEntityId(Integer.parseInt(benefitId));
	    	  	productStructureNotesRequest.setSecEntityType(WebConstants.ATTACH_BENEFIT_LINE);
	    	  	productStructureNotesRequest.setBenefitComponentId(bnftCmpnt);
	    	  	productStructureNotesRequest.setAction(2);
			 // create response
	    		ProductStructureNotesResponse productStructureNotesResponse = 
	    			(ProductStructureNotesResponse)executeService(productStructureNotesRequest);
			 // return the list 
	    		if(null != productStructureNotesResponse)
	    			benefitLineNotes = productStructureNotesResponse.getBenefitComponentNotesList();
	    return benefitLineNotes;
	}
	/**
	 * @param benefitLineNotes The benefitLineNotes to set.
	 */
	public void setBenefitLineNotes(List benefitLineNotes) {
		this.benefitLineNotes = benefitLineNotes;
	}
	/**
	 * @return Returns the dummyVar.
	 */
	public String getDummyVar() {
		this.setBreadCrumbTextForPS();
		loadStandardBenefitNotes();
		return dummyVar;
	}
	/**
	 * @param dummyVar The dummyVar to set.
	 */
	public void setDummyVar(String dummyVar) {
		this.dummyVar = dummyVar;
	}
	
	/**

     * @return securityAccess

     * 

     * Returns the securityAccess.

     */


	   public boolean isSecurityAccess() {

        try{

            securityAccess = getUser().isAuthorized(WebConstants.NOTES_MODULE,

                StateFactory.VIEW_TASK);

        } catch (SevereException e) {

            // TODO Auto-generated catch block

        	Logger.logError(e);

        }

        return securityAccess;

    }
	/**
	 * @param securityAccess The securityAccess to set.
	 */
	public void setSecurityAccess(boolean securityAccess) {
		this.securityAccess = securityAccess;
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
	 * @return Returns the levelVisibleMap.
	 */
	public HashMap getLevelVisibleMap() {
		return levelVisibleMap;
	}
	/**
	 * @param levelVisibleMap The levelVisibleMap to set.
	 */
	public void setLevelVisibleMap(HashMap levelVisibleMap) {
		this.levelVisibleMap = levelVisibleMap;
	}
	/**
	 * @return Returns the showHidden.
	 */
	public boolean isShowHidden() {
		return this.showHidden;
	}
	/**
	 * @param showHidden The showHidden to set.
	 */
	public void setShowHidden(boolean showHidden) {
		this.showHidden = showHidden;
	}
	/**
	 * @return Returns the customizedSysIdMap.
	 */
	public HashMap getCustomizedSysIdMap() {
		return customizedSysIdMap;
	}
	/**
	 * @param customizedSysIdMap The customizedSysIdMap to set.
	 */
	public void setCustomizedSysIdMap(HashMap customizedSysIdMap) {
		this.customizedSysIdMap = customizedSysIdMap;
	}
//	/**
//	 * @return Returns the benefitEditStatusFlag.
//	 */
//	public boolean isBenefitEditStatusFlag() {
//	       String benefitCompName =(String)getSession().getAttribute("BENEFIT_COMP_NAME"); 
//	       
//	       if(null!=benefitCompName && !"".equals(benefitCompName) && benefitCompName.equals("GENERAL BENEFITS"))
//	       		benefitEditStatusFlag = false;
//	       else
//	       		benefitEditStatusFlag = true;
//		return benefitEditStatusFlag;
//	}
	/**
	 * @param benefitEditStatusFlag The benefitEditStatusFlag to set.
	 */
	public void setBenefitEditStatusFlag(boolean benefitEditStatusFlag) {
		this.benefitEditStatusFlag = benefitEditStatusFlag;
	}
	/**
	 * @return Returns the hiddenBenefitValueMap.
	 */
	public HashMap getHiddenBenefitValueMap() {
		return hiddenBenefitValueMap;
	}
	/**
	 * @param hiddenBenefitValueMap The hiddenBenefitValueMap to set.
	 */
	public void setHiddenBenefitValueMap(HashMap hiddenBenefitValueMap) {
		this.hiddenBenefitValueMap = hiddenBenefitValueMap;
	}
	/**
	 * @return Returns the hiddenLevelFlagMap.
	 */
	public HashMap getHiddenLevelFlagMap() {
		return hiddenLevelFlagMap;
	}
	/**
	 * @param hiddenLevelFlagMap The hiddenLevelFlagMap to set.
	 */
	public void setHiddenLevelFlagMap(HashMap hiddenLevelFlagMap) {
		this.hiddenLevelFlagMap = hiddenLevelFlagMap;
	}
	/**
	 * @return Returns the hiddenLineFlagMap.
	 */
	public HashMap getHiddenLineFlagMap() {
		return hiddenLineFlagMap;
	}
	/**
	 * @param hiddenLineFlagMap The hiddenLineFlagMap to set.
	 */
	public void setHiddenLineFlagMap(HashMap hiddenLineFlagMap) {
		this.hiddenLineFlagMap = hiddenLineFlagMap;
	}
	
	/**
	 * @return Returns the benefitCategory.
	 */
	public String getBenefitCategory() {
		return benefitCategory;
	}
	/**
	 * @param benefitCategory The benefitCategory to set.
	 */
	public void setBenefitCategory(String benefitCategory) {
		this.benefitCategory = benefitCategory;
	}
	/**
	 * @return Returns the storedStates.
	 */
	public String getStoredStates() {
		return storedStates;
	}
	/**
	 * @param storedStates The storedStates to set.
	 */
	public void setStoredStates(String storedStates) {
		this.storedStates = storedStates;
	}
	/**
	 * @return Returns the benefitVersion.
	 */
	public int getBenefitVersion() {
		return benefitVersion;
	}
	/**
	 * @param benefitVersion The benefitVersion to set.
	 */
	public void setBenefitVersion(int benefitVersion) {
		this.benefitVersion = benefitVersion;
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
	 * The method retrieves the Tier Definitions associated
     * with a particular benefit
	 * @param benefitId
	 * @return
	 */
	public String getAssociatedTier(int benefitId) {	    
        GetBenefitTierDefinitionRequest benefitTierDefinitionRequest = 
            (GetBenefitTierDefinitionRequest)
		this.getServiceRequest(ServiceManager.GET_BENEFIT_TIERDEFN_ASSN_REQUEST);
        benefitTierDefinitionRequest.setBenefitId(benefitId);
        
        int benefitDefinitionId = 0;
        
        if(null != getSession().getAttribute("SESSION_BNFTDEFINITION_ID")){
            benefitDefinitionId = ((Integer)getSession().getAttribute("SESSION_BNFTDEFINITION_ID"))
            						.intValue();
        }
        benefitTierDefinitionRequest.setBenefitDefinitionId(benefitDefinitionId);        
        GetBenefitTierDefinitionResponse benefitTierDefinitionResponse = 
            		(GetBenefitTierDefinitionResponse) this.
            		executeService(benefitTierDefinitionRequest);
        return benefitTierDefinitionResponse.getTier(); 
    } 
	/**
	 * The method retrieves the Tier Definitions associated
     * with a particular benefit
	 * @param benefitId
	 * @return
	 */
	public String getAssociatedTierForPrint(int benefitId) {	    
		GetBenefitTierDefinitionForDetailPrintRequest benefitTierDefinitionRequest = 
            (GetBenefitTierDefinitionForDetailPrintRequest)
		this.getServiceRequest(ServiceManager.GET_BENEFIT_TIERDEFN_ASSN_DETAIL_PRINT_REQUEST);
        benefitTierDefinitionRequest.setBenefitId(benefitId);        
        GetBenefitTierDefinitionResponse benefitTierDefinitionResponse = 
            		(GetBenefitTierDefinitionResponse) this.
            		executeService(benefitTierDefinitionRequest);
        return benefitTierDefinitionResponse.getTier(); 
    }
    /**
     * @return Returns the tierProductStructure.
     */
    public String getTierProductStructure() {
        return tierProductStructure;
    }
    /**
     * @param tierProductStructure The tierProductStructure to set.
     */
    public void setTierProductStructure(String tierProductStructure) {
        this.tierProductStructure = tierProductStructure;
    }
	/**
	 * @return Returns the dataHiddenValDesc.
	 */
	public HashMap getDataHiddenValDesc() {
		return dataHiddenValDesc;
	}
	/**
	 * @param dataHiddenValDesc The dataHiddenValDesc to set.
	 */
	public void setDataHiddenValDesc(HashMap dataHiddenValDesc) {
		this.dataHiddenValDesc = dataHiddenValDesc;
	}
	/**
	 * @return Returns the dataHiddenValQualifier.
	 */
	public HashMap getDataHiddenValQualifier() {
		return dataHiddenValQualifier;
	}
	/**
	 * @param dataHiddenValQualifier The dataHiddenValQualifier to set.
	 */
	public void setDataHiddenValQualifier(HashMap dataHiddenValQualifier) {
		this.dataHiddenValQualifier = dataHiddenValQualifier;
	}
	/**
	 * @return Returns the dataHiddenValTerm.
	 */
	public HashMap getDataHiddenValTerm() {
		return dataHiddenValTerm;
	}
	/**
	 * @param dataHiddenValTerm The dataHiddenValTerm to set.
	 */
	public void setDataHiddenValTerm(HashMap dataHiddenValTerm) {
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
	 * @return Returns the oldDescOutputTxt.
	 */
	public HashMap getOldDescOutputTxt() {
		return oldDescOutputTxt;
	}
	/**
	 * @param oldDescOutputTxt The oldDescOutputTxt to set.
	 */
	public void setOldDescOutputTxt(HashMap oldDescOutputTxt) {
		this.oldDescOutputTxt = oldDescOutputTxt;
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
}