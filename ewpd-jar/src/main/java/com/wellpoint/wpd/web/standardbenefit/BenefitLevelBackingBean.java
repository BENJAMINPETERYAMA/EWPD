/*
 * BenefitLevelBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.standardbenefit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;

import com.ibm.ws.webservices.engine.types.holders.TimeHolder;
import com.wellpoint.wpd.business.datatype.locatecriteria.DataTypeLocateResult;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelBO;
import com.wellpoint.wpd.common.benefitlevel.request.BenefitLevelPopUpRequest;
import com.wellpoint.wpd.common.benefitlevel.request.BenefitLevelRequest;
import com.wellpoint.wpd.common.benefitlevel.request.DataTypeRetrieveRequest;
import com.wellpoint.wpd.common.benefitlevel.request.DeleteBenefitLevelRequest;
import com.wellpoint.wpd.common.benefitlevel.request.DeleteBenefitLineRequest;
import com.wellpoint.wpd.common.benefitlevel.request.SaveBenefitLevelRequest;
import com.wellpoint.wpd.common.benefitlevel.request.SearchBenefitLevelRequest;
import com.wellpoint.wpd.common.benefitlevel.request.SearchBenefitLevelTermRequest;
import com.wellpoint.wpd.common.benefitlevel.response.BenefitLevelPopUpResponse;
import com.wellpoint.wpd.common.benefitlevel.response.BenefitLevelResponse;
import com.wellpoint.wpd.common.benefitlevel.response.DataTypeRetrieveResponse;
import com.wellpoint.wpd.common.benefitlevel.response.DeleteBenefitLevelResponse;
import com.wellpoint.wpd.common.benefitlevel.response.DeleteBenefitLineResponse;
import com.wellpoint.wpd.common.benefitlevel.response.SaveBenefitLevelResponse;
import com.wellpoint.wpd.common.benefitlevel.response.SearchBenefitLevelResponse;
import com.wellpoint.wpd.common.benefitlevel.response.SearchBenefitLevelTermResponse;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitLevelForViewVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitLevelVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitLineForViewVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitLineVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitQualifierVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitTermVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitWrapperVO;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.productstructure.vo.BenefitDefinitionPrintVO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.framework.util.SequenceUtil;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * Backing Bean for standard benefit level create.
 */
public class BenefitLevelBackingBean extends WPDBackingBean {

    private String benefitDefinition;
    
    private String selectedTermFromCombo;

    private String benefitTerm;

    private String benefitTermQualifier;

    private String providerArrangement;

    private List benefitTermsList;

    private List benefitTermQualifiersList;

    private List providerArrangementsList;

    private List benefitTermsCodeList;

    private List benefitTermQualifiersCodeList;

    private List providerArrangementsCodeList;

    private boolean renderPanel = true;

    private Map dataHiddenValSeq = new HashMap();

    private HtmlPanelGrid displayPanel = new HtmlPanelGrid();

    private HtmlPanelGrid headerPanel = new HtmlPanelGrid();

    private List benefitLevelsList;

    private List validationMessages = null;

    private boolean requiredBenefitTerm;

    private boolean requiredBenefitQualifier;

    private boolean requiredPVA;

    private BenefitWrapperVO benefitWrapperVO = null;
    
    private Map termHidden = new HashMap();

    private Map dataHiddenValData = new HashMap();

    private Map dataHiddenValDesc = new HashMap();

    private Map dataHiddenValBenefitValue = new HashMap();

    private Map dataHiddenValReference = new HashMap();

    private Map dataHiddenValReferenceCode = new HashMap();

    private Map dataHiddenValLevelId = new HashMap();

    private Map dataHiddenValTerm = new HashMap();

    private Map dataHiddenValQualifier = new HashMap();

    private Map dataHiddenValPVA = new HashMap();

    private Map dataHiddenValTermCode = new HashMap();

    private Map dataHiddenValQualifierCode = new HashMap();

    private Map dataHiddenValPVACode = new HashMap();

    //private Map dataHiddenValPvaCode = new HashMap();

    private Map dataHiddenValLineId = new HashMap();

    private Map dataHiddenValForLevel = new HashMap();

    private Map dataHiddenValForBenefitValue = new HashMap();

    private List sequenceList;

    private Map dataHiddenValDataForLevel = new HashMap();

    private String STANDARD_BENEFIT_SESSION_KEY = WebConstants.STANDARD;

    private HtmlPanelGrid panel = new HtmlPanelGrid();

    private HtmlPanelGrid viewPanel = new HtmlPanelGrid();

    private List termResultList = null;

    private List termQualifiersList = null;

    private List pvasList = null;

    private List printBenftDefnList;

    private String printBenefitLevelHiddenId;
    
	private String selectedTerm;

    // **Change**
    private boolean aggregateTerm;
    
//Change: Aggregate qualifier
    private boolean aggregateQualifier;

    private HtmlPanelGrid displayViewPanel = new HtmlPanelGrid();

    private Map dataHiddenValNotesExist = new HashMap();

    private int standardBenefitIdForRefData;
    
    private String levelId;
   
    private String retrieveTermRecords;
   
    private String retrieveTermQualifierRecords;
    
    private String retrievePVARecords;
    	
// change for performance
    private HashMap hiddenDescMap = new HashMap();
    
    private HashMap hiddenDataTypeMap = new HashMap();
    
    private HashMap hiddenBnftValMap = new HashMap();
    
    private HashMap hiddenSequenceMap = new HashMap();
    //added new field to solve performance issue
    private HashMap hiddenValReferenceMap = new HashMap();
    //added for reseting the map on 18th Dec 2007
    private boolean errorFlag = false;
    
    private boolean mandate = false;
    
    private String loadBenefitLevelView;
    
    private HashMap levelDeleteMap = new HashMap();
    
    private HashMap lineDeleteMap = new HashMap(); 
    
    private int numOfLevels = 0;
    
    private String txtData = "";
    
    private String panelData = "";
    
    private String panelDataOnlyDel ="";
	
    private String panelDataNoDel = "";
    
    private String addButtonInvoked;  
    //CARS START
    private Map dataHiddenValFreq = new HashMap();
    
    private HashMap hiddenFreqMap = new HashMap();
    
    private List messageList = null;
    
    private List dateTypeList = null;

    /**
	 * @return the dateTypeList
	 */
	public List getDateTypeList() {
		return dateTypeList;
	}
	/**
	 * @param dateTypeList the dateTypeList to set
	 */
	public void setDateTypeList(List dateTypeList) {
		this.dateTypeList = dateTypeList;
	}
	//CASR END
	/**
	 * @return Returns the panelDataNoDel.
	 */
	public String getPanelDataNoDel() {
		return panelDataNoDel;
	}
	/**
	 * @param panelDataNoDel The panelDataNoDel to set.
	 */
	public void setPanelDataNoDel(String panelDataNoDel) {
		this.panelDataNoDel = panelDataNoDel;
	}
	/**
	 * @return Returns the panelDataOnlyDel.
	 */
	public String getPanelDataOnlyDel() {
		return panelDataOnlyDel;
	}
	/**
	 * @param panelDataOnlyDel The panelDataOnlyDel to set.
	 */
	public void setPanelDataOnlyDel(String panelDataOnlyDel) {
		this.panelDataOnlyDel = panelDataOnlyDel;
	}
    /**
     * @return hiddenValReferenceMap
     * 
     * Returns the hiddenValReferenceMap.
     */
    public HashMap getHiddenValReferenceMap() {
        return hiddenValReferenceMap;
    }
    /**
     * @param hiddenValReferenceMap
     * 
     * Sets the hiddenValReferenceMap.
     */
    public void setHiddenValReferenceMap(HashMap hiddenValReferenceMap) {
        this.hiddenValReferenceMap = hiddenValReferenceMap;
    }
// end
    
	/**
	 * @return Returns the retrievePVARecords.
	 */
	public String getRetrievePVARecords() {
		
		 List pvasList = null;
	        BenefitLevelPopUpRequest benefitLevelPopUpRequest = (BenefitLevelPopUpRequest) this
	                .getServiceRequest(ServiceManager.BENEFIT_LEVEL_POPUP_REQUEST);
	        benefitLevelPopUpRequest
	                .setStandardBenfefitId(getStandardBenefitSessionObject()
	                        .getStandardBenefitKey());
	        benefitLevelPopUpRequest.setPopUpId(1936);
	        BenefitLevelPopUpResponse benefitLevelPopUpResponse = (BenefitLevelPopUpResponse) this
	                .executeService(benefitLevelPopUpRequest);
	        if (null != benefitLevelPopUpResponse)
	            pvasList = benefitLevelPopUpResponse.getSearchResultList();
	        else 
	        	pvasList = null;
	        this.setPvasList(pvasList);
		return retrievePVARecords;
	}
	/**
	 * @param retrievePVARecords The retrievePVARecords to set.
	 */
	public void setRetrievePVARecords(String retrievePVARecords) {
		this.retrievePVARecords = retrievePVARecords;
	}
	/**
	 * @return Returns the retrieveTermQualifierRecords.
	 */
	public String getRetrieveTermQualifierRecords() {
		
		 List termQualifiersList = null;
	        BenefitLevelPopUpRequest benefitLevelPopUpRequest = (BenefitLevelPopUpRequest) this
	                .getServiceRequest(ServiceManager.BENEFIT_LEVEL_POPUP_REQUEST);
	        benefitLevelPopUpRequest
	                .setStandardBenfefitId(getStandardBenefitSessionObject()
	                        .getStandardBenefitKey());
	        benefitLevelPopUpRequest.setPopUpId(1935);
	        BenefitLevelPopUpResponse benefitLevelPopUpResponse = (BenefitLevelPopUpResponse) this
	                .executeService(benefitLevelPopUpRequest);
	        if (null != benefitLevelPopUpResponse)
	            termQualifiersList = benefitLevelPopUpResponse
	                    .getSearchResultList();
	        else 
	        	termQualifiersList =null;
	        
	        this.setTermQualifiersList(termQualifiersList);
		return retrieveTermQualifierRecords;
	}
	/**
	 * @param retrieveTermQualifierRecords The retrieveTermQualifierRecords to set.
	 */
	public void setRetrieveTermQualifierRecords(String retrieveTermQualifierRecords) {
		this.retrieveTermQualifierRecords = retrieveTermQualifierRecords;
	}
	/**
	 * @return Returns the retrieveTermRecords.
	 */
	public String getRetrieveTermRecords() {
		
		 List termResultList = null;
	     BenefitLevelPopUpRequest benefitLevelPopUpRequest = (BenefitLevelPopUpRequest) this
	             .getServiceRequest(ServiceManager.BENEFIT_LEVEL_POPUP_REQUEST);
	     benefitLevelPopUpRequest
	             .setStandardBenfefitId(getStandardBenefitSessionObject()
	                     .getStandardBenefitKey());
	     benefitLevelPopUpRequest.setPopUpId(1934);
	     BenefitLevelPopUpResponse benefitLevelPopUpResponse = (BenefitLevelPopUpResponse) this
	             .executeService(benefitLevelPopUpRequest);
	     if (null != benefitLevelPopUpResponse)
	         termResultList = benefitLevelPopUpResponse.getSearchResultList();
	     else
	     	termResultList = null;
	     
	     this.setTermResultList(termResultList);
	     addAllMessagesToRequest(benefitLevelPopUpResponse.getMessages());
	     
	return retrieveTermRecords;
}
	/**
	 * @param retrieveTermRecords The retrieveTermRecords to set.
	 */
	public void setRetrieveTermRecords(String retrieveTermRecords) {
		this.retrieveTermRecords = retrieveTermRecords;
	}
	/**
	 * @return levelId
	 * 
	 * Returns the levelId.
	 */
	public String getLevelId() {
	    return levelId;
	}
	/**
	 * @param levelId
	 * 
	 * Sets the levelId.
	 */
	public void setLevelId(String levelId) {
	    this.levelId = levelId;
	}
	/**
	 * @return Returns the standardBenefitIdForRefData.
	 */
	public int getStandardBenefitIdForRefData() {
		return standardBenefitIdForRefData;
	}
	/**
	 * @param standardBenefitIdForRefData The standardBenefitIdForRefData to set.
	 */
	public void setStandardBenefitIdForRefData(int standardBenefitIdForRefData) {
		this.standardBenefitIdForRefData = standardBenefitIdForRefData;
	}
    // **End**
    /*
     * Constructor
     *  
     */
    public BenefitLevelBackingBean() {
        super();
        if (null != this.getStandardBenefitSessionObject()
                .getStandardBenefitMode()
                && (WebConstants.BENEFIT_VIEW).equals(this
                        .getStandardBenefitSessionObject()
                        .getStandardBenefitMode())) {
            this.setBreadCrumbText(WebConstants.BENEFIT_LEVEL_BREADCRUMB
                    + getStandardBenefitSessionObject()
                            .getStandardBenefitName() + ") >> View");
        } else
            this.setBreadCrumbText(WebConstants.BENEFIT_LEVEL_BREADCRUMB
                    + getStandardBenefitSessionObject()
                            .getStandardBenefitName() + ") >> Edit");
    }


    /**
     * Sets the printBenefitLevelHiddenId
     * 
     * @param printBenefitLevelHiddenId.
     */

    public void setPrintBenefitLevelHiddenId(String printBenefitLevelHiddenId) {
        this.printBenefitLevelHiddenId = printBenefitLevelHiddenId;
    }


    /**
     * Returns the printBenftDefnList
     * 
     * @return List printBenftDefnList.
     */

    public List getPrintBenftDefnList() {
        return printBenftDefnList;
    }


    /**
     * Sets the printBenftDefnList
     * 
     * @param printBenftDefnList.
     */

    public void setPrintBenftDefnList(List printBenftDefnList) {
        this.printBenftDefnList = printBenftDefnList;
    }


    public Map getDataHiddenValDataForLevel() {
        return dataHiddenValDataForLevel;
    }


    public void setDataHiddenValDataForLevel(Map dataHiddenValDataForLevel) {
        this.dataHiddenValDataForLevel = dataHiddenValDataForLevel;
    }


    /**
     * @return Returns the benefitWrapperBO.
     */
    public BenefitWrapperVO getBenefitWrapperVO() {
        return benefitWrapperVO;
    }


    /**
     * @param benefitWrapperBO
     *            The benefitWrapperBO to set.
     */
    public void setBenefitWrapperVO(BenefitWrapperVO benefitWrapperVO) {
        this.benefitWrapperVO = benefitWrapperVO;
    }


    /**
     * @return Returns the requiredBenefitQualifier.
     */
    public boolean isRequiredBenefitQualifier() {
        return requiredBenefitQualifier;
    }


    /**
     * @param requiredBenefitQualifier
     *            The requiredBenefitQualifier to set.
     */
    public void setRequiredBenefitQualifier(boolean requiredBenefitQualifier) {
        this.requiredBenefitQualifier = requiredBenefitQualifier;
    }


    /**
     * @return Returns the requiredBenefitTerm.
     */
    public boolean isRequiredBenefitTerm() {
        return requiredBenefitTerm;
    }


    /**
     * @param requiredBenefitTerm
     *            The requiredBenefitTerm to set.
     */
    public void setRequiredBenefitTerm(boolean requiredBenefitTerm) {
        this.requiredBenefitTerm = requiredBenefitTerm;
    }


    /**
     * @return Returns the requiredPVA.
     */
    public boolean isRequiredPVA() {
        return requiredPVA;
    }


    /**
     * @param requiredPVA
     *            The requiredPVA to set.
     */
    public void setRequiredPVA(boolean requiredPVA) {
        this.requiredPVA = requiredPVA;
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
     * @return Returns the dataHiddenVal.
     */
    public Map getDataHiddenValSeq() {
        return dataHiddenValSeq;
    }


    /**
     * @param dataHiddenVal
     *            The dataHiddenVal to set.
     */
    public void setDataHiddenValSeq(Map dataHiddenValSeq) {
        this.dataHiddenValSeq = dataHiddenValSeq;
    }


    /**
     * @return Returns the renderPanel.
     */
    public boolean isRenderPanel() {
        return renderPanel;
    }


    /**
     * @param renderPanel
     *            The renderPanel to set.
     */
    public void setRenderPanel(boolean renderPanel) {
        this.renderPanel = renderPanel;
    }


    /**
     * @return Returns the benefitTermQualifiersList.
     */
    public List getBenefitTermQualifiersList() {
        return benefitTermQualifiersList;
    }


    /**
     * @param benefitTermQualifiersList
     *            The benefitTermQualifiersList to set.
     */
    public void setBenefitTermQualifiersList(List benefitTermQualifiersList) {
        this.benefitTermQualifiersList = benefitTermQualifiersList;
    }


    /**
     * @return Returns the benefitTermsList.
     */
    public List getBenefitTermsList() {
        return benefitTermsList;
    }


    /**
     * @param benefitTermsList
     *            The benefitTermsList to set.
     */
    public void setBenefitTermsList(List benefitTermsList) {
        this.benefitTermsList = benefitTermsList;
    }


    /**
     * @return Returns the providerArrangementsList.
     */
    public List getProviderArrangementsList() {
        return providerArrangementsList;
    }


    /**
     * @param providerArrangementsList
     *            The providerArrangementsList to set.
     */
    public void setProviderArrangementsList(List providerArrangementsList) {
        this.providerArrangementsList = providerArrangementsList;
    }


    /**
     * @return Returns the benefitDefinition.
     */
    public String getBenefitDefinition() {
        String benefitDefinition = (String) this.getSession().getAttribute(
                WebConstants.SESSION_BNFT_DEFN_DESC);
        return benefitDefinition;
    }


    /**
     * @param benefitDefinition
     *            The benefitDefinition to set.
     */
    public void setBenefitDefinition(String benefitDefinition) {
        this.benefitDefinition = benefitDefinition;
    }


    /**
     * @return Returns the benefitTerm.
     */
    public String getBenefitTerm() {
        return benefitTerm;
    }


    /**
     * @param benefitTerm
     *            The benefitTerm to set.
     */
    public void setBenefitTerm(String benefitTerm) {
        this.benefitTerm = benefitTerm;
    }


    /**
     * @return Returns the benefitTermQualifier.
     */
    public String getBenefitTermQualifier() {
        return benefitTermQualifier;
    }


    /**
     * @param benefitTermQualifier
     *            The benefitTermQualifier to set.
     */
    public void setBenefitTermQualifier(String benefitTermQualifier) {
        this.benefitTermQualifier = benefitTermQualifier;
    }


    /**
     * @return Returns the providerArrangement.
     */
    public String getProviderArrangement() {
        return providerArrangement;
    }


    /**
     * @param providerArrangement
     *            The providerArrangement to set.
     */
    public void setProviderArrangement(String providerArrangement) {
        this.providerArrangement = providerArrangement;
    }


    /**
     * Returns the printBenefitLevelHiddenId
     * 
     * @return String printBenefitLevelHiddenId.
     */

    public String getPrintBenefitLevelHiddenId() {
        String identifier = WebConstants.BENEFIT_LEVEL_PRINT;

        this.searchBenefitLevels(identifier,"page");
        getBenefitDefinifitonValuesForPrint();
        return "";

    }


    /**
     * Method to create the benefit level for the particular combinaion of
     * Benefit Term, Benefit Qualifier and Provider Arrangement.
     * 
     * @return
     */
    public String createBenefitLevel() {
        int tokenCount = 0;
        if (isValidatedRequiredFields()) {
            setUpdatedTermsList();
            // **Change**
            boolean isValidatedMaxTerm = true;
            validationMessages = new ArrayList(1);

            if (isAggregateTerm()) {
                isValidatedMaxTerm = validateForMaxTerm();
            }
 //Change: Aggregate qualifier
            setUpdatedQualifiersList();
            boolean isValidatedMaxQualifier = true;
            
            if (isAggregateQualifier()){
                isValidatedMaxQualifier = validateForMaxQualifier();
            }
            
            if (isValidatedMaxTerm && isValidatedMaxQualifier) {
// **End**               
             setUpdatedPVAsList();
            this.setBenefitWrapperVO(getUpdatedListFromScreen());
         	if(this.getBenefitWrapperVO().getBenefitLevelsList().size()==0){
         		Set set = new TreeSet(this.getBenefitTermsList());
         		Iterator iterator = set.iterator();
         		if(iterator.hasNext()){
         			String term = String.valueOf(iterator.next());
         			this.setSelectedTerm(term);
         		}
         	}
            this.setBenefitWrapperVO(null);
            getStandardBenefitSessionObject().setBenefitLevelForViewVOList(null);
            getStandardBenefitSessionObject().setDateAvailable(false);
                BenefitLevelRequest benefitLevelRequest = (BenefitLevelRequest) this
                        .getServiceRequest(ServiceManager.CREATE_BENEFIT_LEVEL_REQUEST);
                BenefitLevelResponse benefitLevelResponse = new BenefitLevelResponse();
                BenefitWrapperVO benefitWrapperVO = new BenefitWrapperVO();
                benefitWrapperVO.setBenefitLevelsList(this
                        .getBenefitLevelsList());
                benefitWrapperVO
                        .setBenefitDefinitionId(getBenefitDefintionId());
                benefitWrapperVO
                        .setBenefitIdentifier(getStandardBenefitSessionObject()
                                .getStandardBenefitName());
                benefitWrapperVO
                        .setMasterVersion(getStandardBenefitSessionObject()
                                .getStandardBenefitVersionNumber());
                benefitWrapperVO
                        .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                                .getStandardBenefitParentKey());
                benefitWrapperVO
                        .setStandardBenefitKey(getStandardBenefitSessionObject()
                                .getStandardBenefitKey());
                benefitWrapperVO
                        .setMasterStatus(getStandardBenefitSessionObject()
                                .getStandardBenefitStatus());
                benefitWrapperVO
                        .setBusinessDomains(getStandardBenefitSessionObject()
                                .getBusinessDomains());
                if(null != this.getSelectedTerm()){
                	if(this.getSelectedTerm().equals("ALL THE TERMS"))
                		benefitWrapperVO.setSelectedBenefitTerm("%");
                	else
                		benefitWrapperVO.setSelectedBenefitTerm("%"+this.getSelectedTerm()+"%");                		
            	}else{
            		benefitWrapperVO.setSelectedBenefitTerm("%");
            	}
                benefitLevelRequest.setBenefitWrapperVO(benefitWrapperVO);
                benefitLevelResponse = (BenefitLevelResponse) this
                        .executeService(benefitLevelRequest);
                if (null != benefitLevelResponse) {
                    this.setBenefitWrapperVO(benefitLevelResponse
                            .getBenefitWrapperVO());
                    if (null != this.getBenefitWrapperVO()
                            .getBenefitLevelsList()) {
                        registerSequence(this.getBenefitWrapperVO()
                                .getBenefitLevelsList());
                        this.setRenderPanel(true);
                    } else
                        this.setRenderPanel(false);
                }
                this.setBenefitTerm("");
                this.setBenefitTermQualifier("");
                this.setProviderArrangement("");
            } else {
                this.setBenefitWrapperVO(populateBenefitWrapperVO(this.getBenefitLevelViewList()));
                this.setRenderPanel(true);
                addAllMessagesToRequest(validationMessages);
            }
        } else {
            this.setBenefitWrapperVO(populateBenefitWrapperVO(this.getBenefitLevelViewList()));
            this.setRenderPanel(true);
            addAllMessagesToRequest(validationMessages);
        }
        this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
                + " ("
                + this.getStandardBenefitSessionObject()
                        .getStandardBenefitName() + ") >> Edit");
        getRequest().setAttribute("RETAIN_Value", "");
        return WebConstants.SUCCESS;
    }


    /**
     * Method to get the updated provider arrangement list
     */
    private void setUpdatedPVAsList() {
        List benefitPVAsList = null;
        List benefitPVAsCodeList = null;
        benefitPVAsList = WPDStringUtil.getListFromTildaString(this
                .getProviderArrangement(), 2, 1, 2);
        benefitPVAsCodeList = WPDStringUtil.getListFromTildaString(this
                .getProviderArrangement(), 2, 2, 2);
        this.setProviderArrangementsList(benefitPVAsList);
        this.setProviderArrangementsCodeList(benefitPVAsCodeList);
    }


    /**
     * Method to get the updated qualifier list
     */
    private void setUpdatedQualifiersList() {
        List benefitQualifiersList = new ArrayList();
        List benefitQualifiersCodeList = new ArrayList();
        if (null != this.getBenefitTermQualifier()
                && !"".equals(this.getBenefitTermQualifier()))
            benefitQualifiersList = WPDStringUtil.getListFromTildaString(this
                    .getBenefitTermQualifier(), 2, 1, 2);
        else
            benefitQualifiersList.add("");
        if (null != this.getBenefitTermQualifier()
                && !"".equals(this.getBenefitTermQualifier()))
            benefitQualifiersCodeList = WPDStringUtil.getListFromTildaString(
                    this.getBenefitTermQualifier(), 2, 2, 2);
        else
            benefitQualifiersCodeList.add("");
        this.setBenefitTermQualifiersList(benefitQualifiersList);
        this.setBenefitTermQualifiersCodeList(benefitQualifiersCodeList);
    }


    /**
     * Method to get the updated terms list
     */
    private void setUpdatedTermsList() {
        List benefitTermsList = null;
        List benefitTermsCodeList = null;
        benefitTermsCodeList = WPDStringUtil.getListFromTildaString(this
                .getBenefitTerm(), 2, 2, 2);
        benefitTermsList = WPDStringUtil.getListFromTildaString(this
                .getBenefitTerm(), 2, 1, 2);
        this.setBenefitTermsList(benefitTermsList);
        this.setBenefitTermsCodeList(benefitTermsCodeList);
    }


    /**
     * Method to validate the required fields needed for the benefit line
     * creation.
     */
    private boolean isValidatedRequiredFields() {
        validationMessages = new ArrayList(1);
        boolean requiredField = false;
        if (this.getBenefitTerm() == null
                || this.getBenefitTerm().trim().equals("")) {
            this.setRequiredBenefitTerm(true);
            requiredField = true;
        }
        if (this.getProviderArrangement() == null
                || this.getProviderArrangement().trim().equals("")) {
            this.setRequiredPVA(true);
            requiredField = true;
        }
        if (requiredField) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }
        return true;
    }


    // **Change**
    /**
     * Method to validate the maximum number of terms allowed creation.
     */
    private boolean validateForMaxTerm() {
       
        // get the term list
        List termsList = this.getBenefitTermsList();
        if (null != this.getBenefitTermsList()
                && this.getBenefitTermsList().size() > 2) {
            validationMessages.add(new ErrorMessage(
                    BusinessConstants.MSG_BENEFIT_LEVEL_MAX_TERM_CHECK));
            return false;
        }
        return true;
    }


    // **End**
    
//  Change: Aggregate Qualifier
    /**
     * Method to validate the maximum number of terms allowed creation.
     */
    private boolean validateForMaxQualifier() {
        // get the term list
        List qualifierList = this.getBenefitTermQualifiersList();
        if (null != qualifierList
                && qualifierList.size() > 2) {
            validationMessages.add(new ErrorMessage(
                    BusinessConstants.MSG_BENEFIT_LEVEL_MAX_QUALIFIER_CHECK));
            return false;
        }else if(null == qualifierList) return false;
        return true;
    }
//**end
    /**
     * 
     */
    private void clearMapBindings() {
       this.hiddenValReferenceMap.clear();
       this.hiddenSequenceMap.clear();
       this.hiddenDataTypeMap.clear();
       this.hiddenBnftValMap.clear();
       this.hiddenDescMap.clear();
       //CARS START
       this.hiddenFreqMap.clear();
       //CARS END
       if(this.termHidden!=null)
       this.termHidden.clear();
    }
    /**
     * Method to render the panel which has the list of the benefit lines
     * created.
     */
 public HtmlPanelGrid getPanel() {
	 TimeHandler th = new TimeHandler();
	 Logger.logInfo(th.startPerfLogging("U23057 : Save/Load Benefit Coverage", "Benefit Level Backing Bean", "getPanel()"));
    this.getSession().setAttribute("LevelIds", null);
    this.getSession().setAttribute("LineIds", null);
    if (null == this.getBenefitWrapperVO())
        this.searchBenefitLevels("benefitLevel","page");
    BenefitWrapperVO benefitWrapperVO = this.getBenefitWrapperVO();
    HtmlPanelGrid panel = new HtmlPanelGrid();
    int idCount = 0;
    // Modified for refdata integration
    StandardBenefitSessionObject sessionObject = (StandardBenefitSessionObject)
		getSession().getAttribute("standard");
    this.standardBenefitIdForRefData = sessionObject.getStandardBenefitKey();
    // Modification ends
    
    if (this.renderPanel && null != benefitWrapperVO
            && null != benefitWrapperVO.getBenefitLevelsList()) {
    	List items = null;
    	if(sessionObject.getDateTypeList() == null) {
    		items = getDataTypeList();
    		sessionObject.setDateTypeList(items);
    	} else {
    		items = sessionObject.getDateTypeList();
    	}
        panel.setWidth("100%");
        panel.setColumns(9);
        panel.setBorder(0);
        panel.setBgcolor("#cccccc");
        panel.setCellpadding("1");
        panel.setCellspacing("1");
        BenefitLineVO benefitLineVO = null;
        BenefitLevelVO benefitLevelVO = null;
        //getBenefitHeaderPanel(panel);
        //modified on 18th Dec 2007
        if(!errorFlag)
            clearMapBindings();
        List benefitLevelsList = benefitWrapperVO.getBenefitLevelsList();
        if (null != benefitLevelsList) {
            this.numOfLevels = benefitLevelsList.size();
            Collections.sort(benefitLevelsList);
            for (int i = 0; i < benefitLevelsList.size(); i++) {
                int levelId = 0;
                if (benefitLevelsList.size() > 0) {
                    benefitLevelVO = (BenefitLevelVO) benefitLevelsList
                            .get(i);
                    levelId = benefitLevelVO.getBenefitLevelId();
                }
                List benefitLines = benefitLevelVO.getBenefitLines();
                if (null != benefitLines) {
                    //Collections.sort(benefitLines);
                    int lineCount = 0;
                    for (int j = 0; j < (benefitLines.size() + 1); j++) {
                        int lineId = 0;
                        if (benefitLines.size() > 0) {
                            benefitLineVO = (BenefitLineVO) benefitLines
                                    .get(lineCount);
                            lineId = benefitLineVO.getBenefitLineId();
                        }
                        String keyForLineComponent = levelId + ":" + lineId;
                        
                        HtmlInputText inputText = new HtmlInputText();
                        inputText.setId("SeqNo" + idCount);
                        //							inputText.setStyle("width:75px;");
                        inputText.setStyle("width:50px;");
                        inputText.setStyleClass("formInputField");
                        inputText.setValue(""
                                + benefitLevelVO.getBenefitLevelSeq());
                        // **Change**
                        inputText.setOnkeypress("isNum1(this.id);");
                        inputText.setOnblur("dummy();");
                        inputText.setMaxlength(7); 
                        /*if(null!= this.getSelectedTerm()){ 
                        	if(!(this.getSelectedTerm().equals("ALL THE TERMS"))){
                        		inputText.setDisabled(true);
                        	}
                        }else{
                        	inputText.setDisabled(true);
                        }*/
                        // **End**
                        
                        //Change for Performance
                        HtmlInputHidden hiddenSequence = new HtmlInputHidden();
                        hiddenSequence.setId("hiddenSequence" + idCount);
                        //modified on 18th Dec 2007
                        if(!errorFlag)
                            hiddenSequence.setValue(""
                                    + benefitLevelVO.getBenefitLevelSeq());
                        ValueBinding valForhiddenSequence = FacesContext
                        .getCurrentInstance()
                        .getApplication()
                        .createValueBinding(
                                "#{benefitLevelBackingBean.hiddenSequenceMap["
                                        + benefitLevelVO
                                                .getBenefitLevelId()
                                        + "]}");
                        hiddenSequence.setValueBinding("value", valForhiddenSequence);
                       // end 
                        
                        
                        HtmlInputHidden hiddenLevelId = new HtmlInputHidden();
                        hiddenLevelId.setId("hiddenLevelId" + i);
                        hiddenLevelId.setValue(new Integer(benefitLevelVO
                                .getBenefitLevelId()));

                        ValueBinding hidValItemHidden1 = FacesContext
                                .getCurrentInstance()
                                .getApplication()
                                .createValueBinding(
                                        "#{benefitLevelBackingBean.dataHiddenValLevelId["
                                                + benefitLevelVO
                                                        .getBenefitLevelId()
                                                + "]}");
                        hiddenLevelId.setValueBinding("value",
                                hidValItemHidden1);

                        HtmlInputText inputText1 = new HtmlInputText();
                        HtmlOutputText outputDescription = new HtmlOutputText();
                        outputDescription.setId("outputDescription" + i);
                        outputDescription.setStyle("display:none");
                        outputDescription.setValue(benefitLevelVO.getBenefitLevelDesc());
                        inputText1.setId("Description" + i);
                        inputText1.setStyleClass("formInputField");
                        inputText1.setValue(benefitLevelVO
                                .getBenefitLevelDesc());
                        String toolTipForDesc=benefitLevelVO.getBenefitLevelDesc();
                        //							inputText1.setStyle("width:115px;");
                        inputText1.setStyle("width:120px;");
                        inputText1.setMaxlength(32);
                        inputText1.setTitle(toolTipForDesc);
                        ValueBinding hidValItem1 = FacesContext
                                .getCurrentInstance()
                                .getApplication()
                                .createValueBinding(
                                        "#{benefitLevelBackingBean.dataHiddenValSeq["
                                                + benefitLevelVO
                                                        .getBenefitLevelId()
                                                + "]}");
                        inputText.setValueBinding("value", hidValItem1);

                        ValueBinding hidValItem2 = FacesContext
                                .getCurrentInstance()
                                .getApplication()
                                .createValueBinding(
                                        "#{benefitLevelBackingBean.dataHiddenValDesc["
                                                + benefitLevelVO
                                                        .getBenefitLevelId()
                                                + "]}");
                        inputText1.setValueBinding("value", hidValItem2);
                       
                        
                        //Change for Performance
                        HtmlInputHidden hiddenDesc = new HtmlInputHidden();
                        hiddenDesc.setId("hiddenDesc" + i);
                        //modified on 18th Dec 2007
                        if(!errorFlag)
                            hiddenDesc.setValue(benefitLevelVO
                                    .getBenefitLevelDesc());
                        ValueBinding valForhiddenDesc = FacesContext
                        .getCurrentInstance()
                        .getApplication()
                        .createValueBinding(
                                "#{benefitLevelBackingBean.hiddenDescMap["
                                        + benefitLevelVO
                                                .getBenefitLevelId()
                                        + "]}");
                        hiddenDesc.setValueBinding("value", valForhiddenDesc);
                       // end 
                    
                        HtmlOutputText outputText1 = new HtmlOutputText();
                        outputText1.setId("Term" + idCount);
                        // **Change**
                        //outputText1.setValue(benefitLevelVO.getBenefitTerm());
                        List benefitTerms = benefitLevelVO
                                .getBenefitTerms();
                       
                        String benefitTerm = "";
                        String benfitTermCode = "";
                        
                        if (null != benefitTerms && !benefitTerms.isEmpty()) {
                            for (int k = 0; k < benefitTerms.size(); k++) {
                                BenefitTermVO benefitTermVO = (BenefitTermVO) benefitTerms
                                        .get(k);
                                benefitTerm = benefitTerm
                                        + benefitTermVO.getBenefitTerm();
//**Chnage for term display Start                                   
                                /*benfitTermCode = benfitTermCode
                                        + benefitTermVO
                                                .getBenefitTermCode();*/
                                benfitTermCode = benfitTermCode
                                			+ benefitTermVO
                                					.getBenefitTermCode()
                                			+ "~" 
                                			+ benefitTermVO.getBenefitTerm();
//**Chnage for term display End                                   
                                if (k < (benefitTerms.size() - 1)) {
                                    benefitTerm = benefitTerm + ", ";
                                    benfitTermCode = benfitTermCode + ",";
                                }
                            }
                        }
                        outputText1.setValue(benefitTerm.trim());
                        // **End**

                        HtmlInputHidden hiddenForTerm = new HtmlInputHidden();
                        hiddenForTerm.setId("hiddenTerm" + idCount);
                        // **Change**
                        //hiddenForTerm.setValue(benefitLevelVO.getBenefitTerm());
                        hiddenForTerm.setValue(benefitTerm.trim());
                        // **End**
                        if (j == 0) {
                            ValueBinding hidValTermDesc = FacesContext
                                    .getCurrentInstance()
                                    .getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValTerm["
                                                    + benefitLevelVO
                                                            .getBenefitLevelId()
                                                    + "]}");
                            hiddenForTerm.setValueBinding("value",
                                    hidValTermDesc);
                        }

                        HtmlInputHidden hiddenForTermCode = new HtmlInputHidden();
                        hiddenForTermCode.setId("hiddenTermCode" + idCount);
                        // **Change**
                        //hiddenForTermCode.setValue(benefitLevelVO.getBenefitTermCode());
                        hiddenForTermCode.setValue(benfitTermCode);
                        // **End**
                        if (j == 0) {
                            ValueBinding hidValTermCode = FacesContext
                                    .getCurrentInstance()
                                    .getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValTermCode["
                                                    + benefitLevelVO
                                                            .getBenefitLevelId()
                                                    + "]}");
                            hiddenForTermCode.setValueBinding("value",
                                    hidValTermCode);
                        }
                        //CARS START	
                        //Frequency Start
                        //Description : Frequency field is included in the panel
                        //Binding the frequency value to the HtmlInputText HtmlInputHidden
                        HtmlInputText inputText10 = null;
                        HtmlInputHidden hiddenFreq = null;
                        	inputText10 = new HtmlInputText();
                        	//Setting Id to the frequency field
                            inputText10.setId("Frequency" + i);
                            //Setting style class to the frequency field
                            inputText10.setStyleClass("formInputField");
                            //Setting the value to the frequency field
                            inputText10.setValue(new Integer(benefitLevelVO
                                    .getBenefitFrequency()));
                            if(0 == benefitLevelVO.getBenefitFrequency()){
                            	 outputDescription.setStyle("display:''");
                            	 inputText1.setStyle("display:none");
                            }
                            //Setting the style for the frequency field
                            inputText10.setStyle("width:40px;");
                            //Setting the length for the frequency field
                            inputText10.setMaxlength(3);
                            inputText10.setOnkeypress("return isNum(event);");
                            inputText10.setOnchange("return descriptionChange(this)");
                            
                            if (j == 0) {
                            //Binding the value to the inputText10(HtmlInputText)
                            ValueBinding hidValItemFreq = FacesContext
                                    .getCurrentInstance()
                                    .getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValFreq["
                                                    + benefitLevelVO.getBenefitLevelId()
                                                    + "]}");
                            //Setting the value binding to the frequency field HtmlInputText
                            inputText10.setValueBinding("value", hidValItemFreq);
                            }                            	                            
                            hiddenFreq = new HtmlInputHidden();
                            //Setting ID for the hidden frequency field
                            hiddenFreq.setId("hiddenFreq" + idCount);	                            
                            if(!errorFlag)
                            	hiddenFreq.setValue(new Integer(benefitLevelVO
	                                    .getBenefitFrequency()));
                            if (j == 0) {
                            //Binding the value to the hiddenFreq HtmlInputHidden	
                            ValueBinding valForhiddenFreq = FacesContext
                            .getCurrentInstance()
                            .getApplication()
                            .createValueBinding(
                                    "#{benefitLevelBackingBean.hiddenFreqMap["
                                            + benefitLevelVO
                                                    .getBenefitLevelId()
                                            + "]}");
                            //Setting value binding to the frequency field HtmlInputHidden
                            hiddenFreq.setValueBinding("value", valForhiddenFreq);
                            }
                        //Frequency End
                        //CARS END    
                        HtmlOutputText outputText2 = new HtmlOutputText();
                        outputText2.setId("Qualifier" + idCount);
                        //Change: Aggregate Qualifier
                      
                        List benefitQualifiers = benefitLevelVO
                                .getBenefitQualifiers();
                       
                        String benefitQualifier = "";
                        String benefitQualifierCode = "";
                        
                        if (null != benefitQualifiers && !benefitQualifiers.isEmpty()) {
                            for (int l = 0; l < benefitQualifiers.size(); l++) {
                                BenefitQualifierVO benefitQualifierVO = (BenefitQualifierVO) benefitQualifiers
                                        .get(l);
                                benefitQualifier = benefitQualifier + benefitQualifierVO.getBenefitQualifier();
                                        
//**Change for qualifier display Start                                   
                                /*benfitTermCode = benfitTermCode
                                        + benefitTermVO
                                                .getBenefitTermCode();*/
                                benefitQualifierCode = benefitQualifierCode
											+benefitQualifierVO.getBenefitQualifierCode()
                                			+"~"
											+benefitQualifierVO.getBenefitQualifier();
                                
//**Change for qualifier display End                                   
                                if (l < (benefitQualifiers.size() - 1)) {
                                	benefitQualifier = benefitQualifier + ", ";
                                	benefitQualifierCode = benefitQualifierCode + ",";
                                }
                            }
                        }
                        if(benefitLevelVO.getBenefitFrequency() != 0){
                        	outputText2.setValue(" - "+benefitQualifier.trim());
                        }else{
                        	outputText2.setValue(benefitQualifier.trim());
                        }	

                        
                        //** End


                        HtmlInputHidden hiddenForQualifier = new HtmlInputHidden();
                        hiddenForQualifier.setId("hiddenQualifier"
                                + idCount);
//Change: Aggregate Qualifier                            
                        hiddenForQualifier.setValue(benefitQualifier.trim());
//**end                            
                        if (j == 0) {
                            ValueBinding hidValQualifierDesc = FacesContext
                                    .getCurrentInstance()
                                    .getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValQualifier["
                                                    + benefitLevelVO
                                                            .getBenefitLevelId()
                                                    + "]}");
                            hiddenForQualifier.setValueBinding("value",
                                    hidValQualifierDesc);
                        }

                        HtmlInputHidden hiddenForQualifierCode = new HtmlInputHidden();
                        hiddenForQualifierCode.setId("hiddenQualifierCode"
                                + idCount);
//Change: Aggregate Qualifier
//                      
                        hiddenForQualifierCode.setValue(benefitQualifierCode);
//**end                          
                        if (j == 0) {
                            ValueBinding hidValQualifierCode = FacesContext
                                    .getCurrentInstance()
                                    .getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValQualifierCode["
                                                    + benefitLevelVO
                                                            .getBenefitLevelId()
                                                    + "]}");
                            hiddenForQualifierCode.setValueBinding("value",
                                    hidValQualifierCode);
                        }

                        HtmlOutputText outputText3 = new HtmlOutputText();
                        outputText3.setId("PVA" + idCount);
                        if (null != benefitLineVO)
                            outputText3.setValue(benefitLineVO.getPvaCode());

                        HtmlInputHidden hiddenForPVA = new HtmlInputHidden();
                        hiddenForPVA.setId("hiddenPVA" + idCount);
                        if (null != benefitLineVO) {
                            hiddenForPVA.setValue(benefitLineVO.getPvaCode());
                        }
                        if (j != 0) {
                            ValueBinding hidValItemPVA = FacesContext
                                    .getCurrentInstance()
                                    .getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValPVA["
                                                    + benefitLineVO
                                                            .getBenefitLineId()
                                                    + "]}");
                            hiddenForPVA.setValueBinding("value",
                                    hidValItemPVA);
                        }

                        HtmlInputHidden hiddenForPVACode = new HtmlInputHidden();
                        hiddenForPVACode.setId("hiddenPVACode" + idCount);
                        if (null != benefitLineVO) {
                            hiddenForPVACode.setValue(benefitLineVO
                                    .getPvaCode());
                        }
                        if (j != 0) {
                            ValueBinding hidValItemPVACode = FacesContext
                                    .getCurrentInstance()
                                    .getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValPVACode["
                                                    + benefitLineVO
                                                            .getBenefitLineId()
                                                    + "]}");
                            hiddenForPVACode.setValueBinding("value",
                                    hidValItemPVACode);
                        }

                        HtmlInputHidden hiddenLineId = new HtmlInputHidden();
                        hiddenLineId.setId("hiddenLineId" + idCount);
                        hiddenLineId.setValue(keyForLineComponent);

                        ValueBinding hidValItemHidden2 = FacesContext
                                .getCurrentInstance().getApplication()
                                .createValueBinding(
                                        "#{benefitLevelBackingBean.dataHiddenValLineId["
                                                + idCount + "]}");
                        hiddenLineId.setValueBinding("value",
                                hidValItemHidden2);

                        HtmlSelectOneMenu selectOneMenu = new HtmlSelectOneMenu();
                        //Modified code For Production Fix - Added number
                        // of lines in a level to the id of the component.
                        selectOneMenu.setId("DataType" + "A" + i + "A"
                                + benefitLevelVO.getBenefitLines().size()
                                + "A" + idCount);

                        UISelectItems sis = new UISelectItems();
                        sis.setValue(items);
                        selectOneMenu.getChildren().add(sis);
                        selectOneMenu
                                .setOnchange("return selectValuesForCorrespondingBenefitLine(this)");
                        if (null != benefitLineVO && j != 0)
                            selectOneMenu.setValue(new Integer(
                                    benefitLineVO.getDataTypeId())
                                    .toString());
                        else
                            selectOneMenu.setValue(new Integer(
                                    benefitLevelVO.getDataTypeId())
                                    .toString());
                        if (j != 0) {
                            ValueBinding hidValItem3 = FacesContext
                                    .getCurrentInstance()
                                    .getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValData["
                                                    + benefitLineVO
                                                            .getBenefitLineId()
                                                    + "]}");
                            selectOneMenu.setValueBinding("value",
                                    hidValItem3);
                        } else {
                            ValueBinding hidValItemForLevel = FacesContext
                                    .getCurrentInstance()
                                    .getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValDataForLevel["
                                                    + benefitLevelVO
                                                            .getBenefitLevelId()
                                                    + "]}");
                            selectOneMenu.setValueBinding("value",
                                    hidValItemForLevel);
                        }
                        
                        //Change for Performance
                        HtmlInputHidden hiddenDataType = new HtmlInputHidden();
                        hiddenDataType.setId("hiddenDataType" + idCount);
                        //modified on 18th Dec 2007
                        if(!errorFlag){
                            if (null != benefitLineVO && j != 0)
                                hiddenDataType.setValue(new Integer(
	                            		benefitLineVO.getDataTypeId())
	                                    .toString());
                            else
                                hiddenDataType.setValue(new Integer(
                                        benefitLevelVO.getDataTypeId())
                                        .toString());
                        }
                        if(j != 0){
                            ValueBinding valForhiddenDataType = FacesContext
                            .getCurrentInstance()
                            .getApplication()
                            .createValueBinding(
                                    "#{benefitLevelBackingBean.hiddenDataTypeMap["
                                            +  benefitLineVO
                                            .getBenefitLineId()
                                            + "]}");
                            hiddenDataType.setValueBinding("value", valForhiddenDataType);
                        }else{
                            ValueBinding valForhiddenDataType = FacesContext
                            .getCurrentInstance()
                            .getApplication()
                            .createValueBinding(
                                    "#{benefitLevelBackingBean.hiddenDataTypeMap["
                                            +  benefitLevelVO
                                            .getBenefitLevelId()
                                            + "]}");
                            hiddenDataType.setValueBinding("value", valForhiddenDataType);
                        }
                        
                       // end 
                        
                        HtmlInputText inputText2 = new HtmlInputText();
                        inputText2.setStyleClass("formInputField");
                        inputText2.setStyle("width:50px;");
                        //Modified code For Production Fix - Added number
                        // of lines in a level to the id of the component.
                        inputText2.setId("BenefitValue" + "A" + i + "A"
                                + benefitLevelVO.getBenefitLines().size()
                                + "A" + idCount);
                        inputText2
                                .setOnchange("return selectValuesForCorrespondingBenefitLine(this)");
                        // **Change**
                        inputText2.setMaxlength(250);
                        // **End**
                        if (j != 0) {
                            inputText2.setValue(benefitLineVO
                                    .getBenefitValue());
                            if (null == benefitLineVO.getBenefitValue())
                                inputText2.setValue("");
                            ValueBinding hidValItem4 = FacesContext
                                    .getCurrentInstance()
                                    .getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValBenefitValue["
                                                    + benefitLineVO
                                                            .getBenefitLineId()
                                                    + "]}");
                            inputText2
                                    .setValueBinding("value", hidValItem4);
                        } else {
                            // **Change**
                            inputText2.setValue("");
                            // **End**
                            ValueBinding hidValItemForBenefitValue = FacesContext
                                    .getCurrentInstance()
                                    .getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValForBenefitValue["
                                                    + benefitLevelVO
                                                            .getBenefitLevelId()
                                                    + "]}");
                            inputText2.setValueBinding("value",
                                    hidValItemForBenefitValue);
                        }
                        
                        //Change for Performance
                        HtmlInputHidden hiddenBnftVal = new HtmlInputHidden();
                        hiddenBnftVal.setId("hiddenBnftVal" + idCount);
                        //modified on 18th Dec 2007
                        if(!errorFlag){
                            if (null != benefitLineVO && j != 0){
                                hiddenBnftVal.setValue(benefitLineVO
	                                    .getBenefitValue());
                            }else{
                                hiddenBnftVal.setValue(benefitLevelVO
	                                    .getBenefitValue());
                            }
                        }
                        if (j != 0) {
                            ValueBinding valForhiddenBnftVal = FacesContext
                            .getCurrentInstance()
                            .getApplication()
                            .createValueBinding(
                                    "#{benefitLevelBackingBean.hiddenBnftValMap["
                                            +  benefitLineVO
                                            .getBenefitLineId()
                                            + "]}");
                            hiddenBnftVal.setValueBinding("value", valForhiddenBnftVal);
                        }else{
                            ValueBinding valForhiddenBnftVal = FacesContext
                            .getCurrentInstance()
                            .getApplication()
                            .createValueBinding(
                                    "#{benefitLevelBackingBean.hiddenBnftValMap["
                                            +  benefitLevelVO
                                            .getBenefitLevelId()
                                            + "]}");
                            hiddenBnftVal.setValueBinding("value", valForhiddenBnftVal);
                        }
                       
                       // end 
                        
                        HtmlInputText inputText3 = new HtmlInputText();
                        inputText3.setStyleClass("formInputField");
                        // **Change**
                        //inputText3.setId("Reference" + i);
                        //inputText3.setValue(benefitLevelVO.getReference());
                        inputText3.setId("Reference" + idCount);
                        if(null != benefitLineVO){
                            inputText3.setValue(benefitLineVO.getReference());
                        }else{
                            inputText3.setValue(benefitLevelVO.getReference());
                        }
                        //inputText3.setValue(benefitLineVO.getReference());
                        // **End**
                        inputText3.setStyle("width:120px;");
                        inputText3.setReadonly(true);
                        inputText3.setTitle(benefitLineVO.getReference());
                        inputText3.setOnmouseover("setTitle()");
                        HtmlInputHidden hiddenReference = new HtmlInputHidden();
                        // **Chnage**
                        /*
                         * hiddenReference.setId("TxtReference" + i);
                         * hiddenReference.setValue(benefitLevelVO.getReference());
                         * ValueBinding hidValItemRef =
                         * FacesContext.getCurrentInstance()
                         * .getApplication().createValueBinding(
                         * "#{benefitLevelBackingBean.dataHiddenValReference[" +
                         * benefitLevelVO.getBenefitLevelId() + "]}");
                         */
                        hiddenReference.setId("TxtReference" + idCount);
                        if(null != benefitLineVO){
                            hiddenReference.setValue(benefitLineVO
                                    .getReference());
                            ValueBinding hidValItemRef = FacesContext
                                    .getCurrentInstance().getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValReference["
                                                    + benefitLineVO
                                                            .getBenefitLineId()
                                                    + "]}");
                            // **End**
                            hiddenReference.setValueBinding("value",
                                    hidValItemRef);

                        }else{
                            hiddenReference.setValue(benefitLevelVO
                                    .getReference());
                            ValueBinding hidValItemRef = FacesContext
                                    .getCurrentInstance().getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValReference["
                                                    + benefitLevelVO
                                                            .getBenefitLevelId()
                                                    + "]}");
                            // **End**
                            hiddenReference.setValueBinding("value",
                                    hidValItemRef);


                        }
                        
                        //Change for Performance on 13thDec
                        HtmlInputHidden hiddenValReference = new HtmlInputHidden();
                        hiddenValReference.setId("hiddenValReference" + idCount);
                        //modified on 18th Dec 2007
                        if(!errorFlag){
                            if (null != benefitLineVO && j != 0){
                                hiddenValReference.setValue(benefitLineVO
	                                    .getReferenceCode());
                            }else{
                                hiddenValReference.setValue(benefitLevelVO
	                                    .getReferenceCode());
                            }
                        }
                        if (j != 0) {
                            ValueBinding valForhiddenReference = FacesContext
                            .getCurrentInstance()
                            .getApplication()
                            .createValueBinding(
                                    "#{benefitLevelBackingBean.hiddenValReferenceMap["
                                            +  benefitLineVO
                                            .getBenefitLineId()
                                            + "]}");
                            hiddenValReference.setValueBinding("value", valForhiddenReference);
                        }else{
                            ValueBinding valForhiddenReference = FacesContext
                            .getCurrentInstance()
                            .getApplication()
                            .createValueBinding(
                                    "#{benefitLevelBackingBean.hiddenValReferenceMap["
                                            +  benefitLevelVO
                                            .getBenefitLevelId()
                                            + "]}");
                            hiddenValReference.setValueBinding("value", valForhiddenReference);
                        }
                        
                       // end 
                        HtmlInputHidden hiddenNotesExist = new HtmlInputHidden();
                        hiddenNotesExist.setId("TxtNotesExist" + idCount);
                        if(null != benefitLineVO){
                            hiddenNotesExist.setValue(benefitLineVO
                                    .getBnftLineNotesExist());
                            ValueBinding hidValItemNotesExist = FacesContext
                                    .getCurrentInstance().getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValNotesExist["
                                                    + benefitLineVO
                                                            .getBenefitLineId()
                                                    + "]}");
                            hiddenNotesExist.setValueBinding("value",
                                    hidValItemNotesExist);
                        }                        


                        HtmlInputHidden hiddenReferenceCode = new HtmlInputHidden();
                        // **Change**
                        /*
                         * hiddenReferenceCode.setId("TxtReferenceCode" +
                         * i);
                         * hiddenReferenceCode.setValue(benefitLevelVO.getReference()+"~"+benefitLevelVO.getReferenceCode());
                         * ValueBinding hidValItemRefCode =
                         * FacesContext.getCurrentInstance()
                         * .getApplication().createValueBinding(
                         * "#{benefitLevelBackingBean.dataHiddenValReferenceCode[" +
                         * benefitLevelVO.getBenefitLevelId() + "]}");
                         */
                        hiddenReferenceCode.setId("TxtReferenceCode"
                                + idCount);
                        // **Change for including notes exist Start
                        //hiddenReferenceCode.setValue(benefitLineVO.getReference()+"~"+benefitLineVO.getReferenceCode());
                        if(null != benefitLineVO){
                            hiddenReferenceCode.setValue( benefitLineVO.getReferenceCode()+ "~" +benefitLineVO
                                    .getReference()
                                    );
                            // **Change for including notes exist End
                            ValueBinding hidValItemRefCode = FacesContext
                                    .getCurrentInstance().getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValReferenceCode["
                                                    + benefitLineVO
                                                            .getBenefitLineId()
                                                    + "]}");
                            // **End**


                            hiddenReferenceCode.setValueBinding("value",
                                    hidValItemRefCode); 
                        }else{
                            hiddenReferenceCode.setValue(benefitLevelVO
                                    .getReference()
                                    + "~" + benefitLevelVO.getReferenceCode());
                            // **Change for including notes exist End
                            ValueBinding hidValItemRefCode = FacesContext
                                    .getCurrentInstance().getApplication()
                                    .createValueBinding(
                                            "#{benefitLevelBackingBean.dataHiddenValReferenceCode["
                                                    + benefitLevelVO
                                                            .getBenefitLevelId()
                                                    + "]}");
                            // **End**


                            hiddenReferenceCode.setValueBinding("value",
                                    hidValItemRefCode); 
                        }
                        

                        HtmlOutputText dummytext = new HtmlOutputText();
                        dummytext.setValue("   ");

                        HtmlGraphicImage selectImage = new HtmlGraphicImage();
                        selectImage.setUrl("../../images/select.gif");
                        selectImage.setStyle("cursor:hand;");
                        selectImage.setId("selectImage" + idCount);
                        // **Change**
                        /*
                         * selectImage .setOnclick("ewpdModalWindow_ewpd(
                         * '../adminoptionspopups/selectOneReferencePopup.jsp',
                         * 'benefitLevelForm:Reference" + i +
                         * "','benefitLevelForm:TxtReferenceCode" + i +
                         * "',2,1)");
                         */
//                        selectImage
//                                .setOnclick("ewpdModalWindow_ewpd( '../adminoptionspopups/selectOneReferencePopup.jsp', 'benefitLevelForm:Reference"
//                                        + idCount
//                                        + "','benefitLevelForm:TxtReferenceCode"
//                                        + idCount + "',2,1)");
//** Modified for refdata
						String entityType = "stdbenefit";
						String lookUpAction = "5";
						String parentCatalog ="reference";
//						selectImage.setOnclick("return selectValues(this) ");
//						selectImage
//						.setOnclick("ewpdModalWindow_ewpd( '../adminoptionspopups/selectOneReferencePopupFilterSearch.jsp?lookUpAction=" + lookUpAction
//								+"&parentCatalog=" + parentCatalog + "&entityId=" + this.standardBenefitIdForRefData + "&entityType=" + entityType +"&term=" + benefitTerm +"&pva=" + outputText3.getValue() + "', 'benefitLevelForm:Reference"
//								+ idCount
//                                + "','benefitLevelForm:TxtReferenceCode"
//								+ idCount + "',2,1)");
						String qualVal="";
						String termVal="";
						if(benefitQualifierCode != null && !"".equalsIgnoreCase(benefitQualifierCode)){
							String qualCodes[]=benefitQualifierCode.split(",");
							for(int k=0;k<qualCodes.length;k++){

								if(qualCodes[k].split("~").length==2)
									qualVal+=qualCodes[k].split("~")[0].trim();
								if(k!=qualCodes.length-1)
									qualVal+=",";
							}
							
						}
						
						if(benfitTermCode != null && !"".equalsIgnoreCase(benfitTermCode)){
							String termCodes[]=benfitTermCode.split(",");
							for(int k=0;k<termCodes.length;k++){

								if(termCodes[k].split("~").length==2)
									termVal+=termCodes[k].split("~")[0].trim();
								if(k!=termCodes.length-1)
									termVal+=",";
							}
							
						}
						
						selectImage
						.setOnclick("setDataType('DataType" + "A" + i + "A"
                                + benefitLevelVO.getBenefitLines().size()
                                + "A" + idCount+"');ewpdModalWindow_ewpd( '../popups/SearchSPSForLinesAndQuestions.jsp?" +
								"queryName=searchSPSForMapping&headerName=SPS Parameters&titleName=" +
								"SPS Parameter Popup&titleName=SPS Parameter Popup&spsType=LINE&term="+termVal 
								+"&qualifier=" + qualVal + "&pva=" + outputText3.getValue() + "&dataType='+document.getElementById('benefitLevelForm:hiddenDt').value+'&temp='+Math.random(),'benefitLevelForm:Reference"
								+ idCount
                                + "','benefitLevelForm:TxtReferenceCode"
								+ idCount + "',2,2)");

						
//              ** Modification ends							
						
										
										
//May 6 - Start
						HtmlInputHidden hiddenNotesStatus = new HtmlInputHidden();
						hiddenNotesStatus.setId("hiddenNotesStatus" + idCount);
						hiddenNotesStatus.setValue("");
//May 6 - End							

                        //Notes Attachment Image
                        HtmlGraphicImage notesAttachmentImage = new HtmlGraphicImage();
                        if (null != benefitLineVO && null != benefitLineVO.getBnftLineNotesExist()) {
                            if (benefitLineVO.getBnftLineNotesExist()
                                    .equals("Y")) {
                                notesAttachmentImage
                                        .setUrl("../../images/notes_exist.gif");                                  
                            } else {
                                notesAttachmentImage
                                        .setUrl("../../images/page.gif");
                            }
                        }
                        notesAttachmentImage.setStyle("cursor:hand;");
                        notesAttachmentImage.setId("notesAttachmentImage"
                                + idCount);
                        if(null != benefitLineVO){
                            notesAttachmentImage
                            .setOnclick("notesAttachmentPopup('../standardBenefit/benefitLevelNotesAttachmentPopUp.jsp','"
                                    + benfitTermCode
                                    + "','"
                                    + benefitLevelVO
                                            .getBenefitQualifierCode()
                                    + "','"
                                    + benefitLineVO.getBenefitLineId()
                                    + "','"
									+ idCount +"');return false;");
                        }else{
                            notesAttachmentImage
                            .setOnclick("notesAttachmentPopup('../standardBenefit/benefitLevelNotesAttachmentPopUp.jsp','"
                                    + benfitTermCode
                                    + "','"
                                    + benefitLevelVO
                                            .getBenefitQualifierCode()
                                    + "','"
                                    + benefitLevelVO.getBenefitLevelId()
                                    + "','"
									+ idCount +"');return false;");
                        }
                        
                        // **End**
                        /*HtmlCommandButton deleteButton = new HtmlCommandButton();
                        deleteButton.setValue("DeleteButton");
                        deleteButton.setImage("../../images/delete.gif");
                        deleteButton.setTitle("Delete");
                        deleteButton.setStyle("border:0;");
                        MethodBinding deleteMetBind = null;
                        if (j == 0) {
                            deleteButton.setId("deleteButton" + "ALevel"
                                    + "A"
                                    + benefitLevelVO.getBenefitLevelId());
                            deleteButton
                                    .setOnclick("return deleteBenefitLevel()");
                            deleteMetBind = FacesContext
                                    .getCurrentInstance()
                                    .getApplication()
                                    .createMethodBinding(
                                            "#{benefitLevelBackingBean.deleteBenefitLevel}",
                                            new Class[] {});

                        } else {
                            deleteButton.setId("deleteButton" + "ALine"
                                    + "A"
                                    + benefitLineVO.getBenefitLevelId()
                                    + "A"
                                    + benefitLineVO.getBenefitLineId());
                            deleteButton

                                    .setOnclick("return deleteBenefitLine()");
                            deleteMetBind = FacesContext
                                    .getCurrentInstance()
                                    .getApplication()
                                    .createMethodBinding(
                                            "#{benefitLevelBackingBean.deleteBenefitLine}",
                                            new Class[] {});

                                    .addActionListener(new BenefitListener());
                            deleteButton.setAction(deleteMetBind);*/
                            
                            
                            
                            // Multiple Delete
                    		HtmlSelectBooleanCheckbox levelCheckBox = new HtmlSelectBooleanCheckbox();
                    		levelCheckBox.setValue(Boolean.FALSE);                   		
                    		levelCheckBox.setTitle("Delete");
                    		if( j == 0 ){
                    			// For Levels
	                    		levelCheckBox.setId("levelCheckBox" + i);
	                    		// Set the level id in the levelDeleteMap
	                    		levelCheckBox.setValueBinding("value",
	                    										FacesContext.getCurrentInstance().
																getApplication().
																createValueBinding(
																	"#{benefitLevelBackingBean." +
																	"levelDeleteMap[" 
																	+ benefitLevelVO.getBenefitLevelId()+
																	"]}")
															); 
	                    		// Script to select/deselect all the benefit lines
	                    		levelCheckBox.setOnclick("selectTheCorrespondingBenefitLines("
	                    										+ i + "," 
																+ benefitLevelVO.getBenefitLines().size() +");");  
                    		}else {    
                    			// For Lines
                    			levelCheckBox.setId("lineCheckBox" + i + "A" + j);
                    			// Set the levelid and lineid
	                            levelCheckBox.setValueBinding("value",
	                            								FacesContext.getCurrentInstance().
																getApplication().
																createValueBinding(
																	"#{benefitLevelBackingBean." +
																	"lineDeleteMap['" 
																	+ benefitLevelVO.getBenefitLevelId() 
																	+ "." +
																	benefitLineVO.getBenefitLineId() +
																	"']}")
															);
	                            // Script to select/deselect the corresponding level
	                            levelCheckBox.setOnclick("selectTheCorrespondingBenefitLevel("
	                            								+ i + "," + j + "," 
																+ benefitLevelVO.getBenefitLines().size() + ");");
                    		}
                            // End
                        
                        //** Performance Fix start
                        //** Main change is that setFor was set to
                        // lblSpace. Now its changed to lblSeq.
                        //** while creating the panel, if you check the
                        // console, you can find lot
                        //** of info printed by jsf. This change will
                        // prevent that and save considerable amount
                        //** of time
                        HtmlOutputLabel lblSeq = new HtmlOutputLabel();
                        lblSeq.setFor("lblSeq" + idCount);
                        lblSeq.setId("lblSeq" + idCount);

                        HtmlOutputLabel lblDesc = new HtmlOutputLabel();
                        lblDesc.setFor("lblDesc" + idCount);
                        lblDesc.setId("lblDesc" + idCount);

                        HtmlOutputLabel lblTerm = new HtmlOutputLabel();
                        lblTerm.setFor("lblTerm" + idCount);
                        lblTerm.setId("lblTerm" + idCount);

                        HtmlOutputLabel lblFrequency = new HtmlOutputLabel();
                        lblFrequency.setFor("lblFrequency" + idCount);
                        lblFrequency.setId("lblFrequency" + idCount);

                       /* HtmlOutputLabel lblQualifier = new HtmlOutputLabel();
                        lblQualifier.setFor("lblQualifier" + idCount);
                        lblQualifier.setId("lblQualifier" + idCount);*/

                        HtmlOutputLabel lblPVA = new HtmlOutputLabel();
                        lblPVA.setFor("lblPVA" + idCount);
                        lblPVA.setId("lblPVA" + idCount);

                        HtmlOutputLabel lblDataType = new HtmlOutputLabel();
                        lblDataType.setFor("lblDataType" + idCount);
                        lblDataType.setId("lblDataType" + idCount);

                        HtmlOutputLabel lblbenefitValue = new HtmlOutputLabel();
                        lblbenefitValue.setFor("lblbenefitValue" + idCount);
                        lblbenefitValue.setId("lblbenefitValue" + idCount);

                        HtmlOutputLabel lblReference = new HtmlOutputLabel();
                        lblReference.setFor("lblReference" + idCount);
                        lblReference.setId("lblReference" + idCount);

                        HtmlOutputLabel lblSpace = new HtmlOutputLabel();
                        lblSpace.setFor("lblSpace" + idCount);
                        lblSpace.setId("lblSpace" + idCount);
                        //** Performance Fix End
                        if (j == 0) {
                        	lblTerm.getChildren().add(outputText1);
                            //lblQualifier.getChildren().add(outputText2);
                        	//Start Frequency
                        	//Check for frequency value not equal to 0 
                        	if(benefitLevelVO.getBenefitFrequency() != 0){
                            	//Then sets the frequency input text field to the frequency label
                        		lblFrequency.getChildren().add(inputText10);
                        	}
                        	//Sets the qualifier output text field to the frequency label
                            lblFrequency.getChildren().add(outputText2);
                        	//End Frequency
                            lblSeq.getChildren().add(inputText);
                            lblSeq.getChildren().add(hiddenLevelId);
                            lblSeq.getChildren().add(hiddenSequence);
                            lblDesc.getChildren().add(inputText1);
                            lblDesc.getChildren().add(hiddenDesc);
                            lblDesc.getChildren().add(outputDescription);
                            lblPVA.getChildren().add(dummytext);
                            // **Change**
                            /*
                             * lblReference.getChildren().add(inputText3);
                             * lblReference.getChildren().add(hiddenReference);
                             * lblReference.getChildren().add(hiddenReferenceCode);
                             * lblReference.getChildren().add(dummytext);
                             * lblReference.getChildren().add(selectImage);
                             */
                            // **End**
                        } else {
                        	lblTerm.getChildren().add(dummytext);
                            //lblQualifier.getChildren().add(dummytext);
                            lblFrequency.getChildren().add(dummytext);
                            lblFrequency.getChildren().add(dummytext);
                            lblSeq.getChildren().add(dummytext);
                            lblDesc.getChildren().add(dummytext);
                            lblPVA.getChildren().add(outputText3);
                            lblPVA.getChildren().add(hiddenForPVA);
                            lblPVA.getChildren().add(hiddenForPVACode);
                            lblPVA.getChildren().add(hiddenLineId);
                            // **Change**
                            //lblReference.getChildren().add(dummytext);
                            lblReference.getChildren().add(inputText3);
                            lblReference.getChildren().add(hiddenReference);
                            lblReference.getChildren().add(hiddenValReference);
                            lblReference.getChildren()
                                    .add(hiddenNotesExist);
                            lblReference.getChildren().add(
                                    hiddenReferenceCode);
                            lblReference.getChildren().add(dummytext);
                            lblReference.getChildren().add(selectImage);
                            lblReference.getChildren().add(dummytext);
                            // get the benefit type from the session
                            String benefitType = this
                                    .getStandardBenefitSessionObject()
                                    .getBenefitType();
                            if (null != benefitType
                                    && !"".equals(benefitType)) {
                                if (benefitType
                                        .equals(WebConstants.STD_TYPE)) {
                                    lblReference.getChildren().add(
                                            notesAttachmentImage);
//May 6 - Start
                                    lblReference.getChildren().add(
                                    		hiddenNotesStatus);
//May 6 - End                                        
                                } else if (benefitType
                                        .equals(WebConstants.MNDT_TYPE)) {
                                    lblReference.getChildren().add(
                                            dummytext);
                                }
                            }
                            //lblReference.getChildren().add(notesAttachmentImage);
                            // **End**
                        }
                        
                        //lblTerm.getChildren().add(outputText1);
                        lblTerm.getChildren().add(hiddenForTermCode);
                        lblTerm.getChildren().add(hiddenForTerm);
                        //CARS START
                        //lblQualifier.getChildren().add(outputText2);
                        //Setting the hidden object of frequency and qualifier
                        //to lblFrequency label
                        lblFrequency.getChildren().add(hiddenFreq);                            	
                        lblFrequency.getChildren().add(hiddenForQualifier);
                        lblFrequency.getChildren().add(hiddenForQualifierCode);
                        //CARS END
                        //lblQualifier.getChildren().add(hiddenForQualifier);
                        //lblQualifier.getChildren().add(
                        //        hiddenForQualifierCode);
                        lblDataType.getChildren().add(selectOneMenu);
                        lblDataType.getChildren().add(hiddenDataType);
                        lblbenefitValue.getChildren().add(inputText2);
                        lblbenefitValue.getChildren().add(hiddenBnftVal);
                        //lblSpace.getChildren().add(deleteButton);
                        lblSpace.getChildren().add(levelCheckBox);
                        
                        panel.getChildren().add(lblSeq);
                        panel.getChildren().add(lblDesc);
                        panel.getChildren().add(lblTerm);
                        panel.getChildren().add(lblFrequency);
                       // panel.getChildren().add(lblQualifier);
                        panel.getChildren().add(lblPVA);
                        panel.getChildren().add(lblDataType);
                        panel.getChildren().add(lblbenefitValue);
                        panel.getChildren().add(lblReference);
                        panel.getChildren().add(levelCheckBox);
                        idCount++;

                        if (j >= 1)
                            lineCount++;
                        else
                            lineCount = 0;

                    }
                }
            }

        }
    }
    this.setBreadCrumbText("Product Configuration >> Benefit"
            + " ("
            + this.getStandardBenefitSessionObject()
                    .getStandardBenefitName() + ") >> Edit");

    this.getRequest().getSession().setAttribute("numOfLevels",new Integer(this.numOfLevels));
    Logger.logInfo(th.endPerfLogging());
    return panel;
    }

 private List benefitLevelForPage;
 
 /**
  * @return Returns the benefitLevelForPage.
  */
 public List getBenefitLevelForPage() {
 	BenefitWrapperVO benefitWrapperVO = this.getBenefitWrapperVO();
    if(benefitWrapperVO == null){
    	 this.searchBenefitLevels("benefitLevel","page");
    	 benefitWrapperVO = this.getBenefitWrapperVO();
    }
    
    if(dateTypeList == null) {
    	dateTypeList = getDataTypeList();
    }
    List BenefitLevelList = benefitWrapperVO.getBenefitLevelsList();
 	return BenefitLevelList;
 }
 /**
  * @param benefitLevelForPage The benefitLevelForPage to set.
  */
 public void setBenefitLevelForPage(List benefitLevelForPage) {
 	this.benefitLevelForPage = benefitLevelForPage;
 }
 
private List benefitLevelsListForView;

/**
 * @return Returns the benefitLevelsListForView.
 */
public List getBenefitLevelsListForView() {
	TimeHandler th = new TimeHandler();
	Logger.logInfo(th.startPerfLogging("U23057 : Benefit Coverage", "BenefitLevelBackingBean", "getBenefitLevelsListForView"));
	this.benefitLevelsListForView = new ArrayList();
	BenefitLineForViewVO benefitLineForViewVO;
	
    BenefitWrapperVO benefitWrapperVO = this.getBenefitWrapperVO();
    if(benefitWrapperVO == null){
    	 this.searchBenefitLevels("benefitLevel","page");
    	 benefitWrapperVO = this.getBenefitWrapperVO();
    }
    if(dateTypeList == null) {
    	dateTypeList = getDataTypeList();
    }
    int idCount = 0;
    if (this.renderPanel && null != benefitWrapperVO
            && null != benefitWrapperVO.getBenefitLevelsList()) {
    	
        BenefitLineVO benefitLineVO = null;
        BenefitLevelVO benefitLevelVO = null;
        
        List benefitLevelsList = benefitWrapperVO.getBenefitLevelsList();
        
        if (null != benefitLevelsList && !benefitLevelsList.isEmpty()) {
            Collections.sort(benefitLevelsList);
            
         /*   benefitLineForViewVO = new BenefitLineForViewVO();
            benefitLineForViewVO.setSeq("Seq"); 
            benefitLineForViewVO.setDescription("Description"); 
            benefitLineForViewVO.setTerm("Term"); 
            benefitLineForViewVO.setQualifier("Qualifier"); 
            benefitLineForViewVO.setPva("PVA");
            benefitLineForViewVO.setFormat("Format"); 
            benefitLineForViewVO.setBenefitValue("Benefit Value"); 
            benefitLineForViewVO.setReference("Reference"); 
            this.benefitLevelsListForView.add(benefitLineForViewVO);
         */
            for (int i = 0; i < benefitLevelsList.size(); i++) {
                int levelId = 0;
                
                if (benefitLevelsList.size() > 0) {
                    benefitLevelVO = (BenefitLevelVO) benefitLevelsList.get(i);
                }
                List benefitLines = benefitLevelVO.getBenefitLines();
                
                if (null != benefitLines && !benefitLines.isEmpty()) {
                	
                    //Collections.sort(benefitLines);
                    int lineCount = 0;
                    for (int j = 0; j < (benefitLines.size() + 1); j++) {
                    	
                    	int benefitLineId = 0;
                    	String seq = WebConstants.EMPTY_STRING; 
                    	String description= WebConstants.EMPTY_STRING;  
                    	String term= WebConstants.EMPTY_STRING;
                    	//setting empty string to the frequency value
                    	String frequency= WebConstants.EMPTY_STRING;  
                    	String qualifier = WebConstants.EMPTY_STRING;  
                    	String pva = WebConstants.EMPTY_STRING;  
                    	String format = WebConstants.EMPTY_STRING;  
                    	String benefitValue = WebConstants.EMPTY_STRING;  
                    	String reference = WebConstants.EMPTY_STRING; 
                    	boolean notesExist = false; 
                    	boolean renderNotesAttachmentImage = false;
                    	
                    	int lineId = 0;
                        if (benefitLines.size() > 0) {
                            benefitLineVO = (BenefitLineVO) benefitLines.get(lineCount);
                        }
                         
                        if (null != benefitLineVO){
              
                        List benefitTerms = benefitLevelVO.getBenefitTerms();
                        String benefitTerm = "";
                        String benfitTermCode = "";                      
                        if (null != benefitTerms && !benefitTerms.isEmpty()) {
                            for (int k = 0; k <benefitTerms.size(); k++) {
                                BenefitTermVO benefitTermVO = (BenefitTermVO) benefitTerms.get(k);
                                
                                benefitTerm = benefitTerm+ benefitTermVO.getBenefitTerm();
                                benfitTermCode = benfitTermCode+ benefitTermVO.getBenefitTermCode();
                                if (k <(benefitTerms.size() - 1)) {
                                    benefitTerm = benefitTerm + ", ";
                                    benfitTermCode = benfitTermCode + ",";
                                }
                            }
                        }
                        
		                List benefitQualifiers = benefitLevelVO.getBenefitQualifiers();		               
		                String benefitQualifier = "";
		                String benefitQualifierCode = "";		               
		                if (null != benefitQualifiers && !benefitQualifiers.isEmpty()) {
		                    for (int l = 0; l < benefitQualifiers.size(); l++) {
		                        BenefitQualifierVO benefitQualifierVO = (BenefitQualifierVO) benefitQualifiers
		                                .get(l);
		                        benefitQualifier = benefitQualifier + benefitQualifierVO.getBenefitQualifier();
		                        benefitQualifierCode = benefitQualifierCode
											+benefitQualifierVO.getBenefitQualifierCode()
		                        			+"~"
											+benefitQualifierVO.getBenefitQualifier();
		                        if (l < (benefitQualifiers.size() - 1)) {
		                        	benefitQualifier = benefitQualifier + ", ";
		                        	benefitQualifierCode = benefitQualifierCode + ",";
		                        }
		                    }
		                }
                
	                        seq = ""+ benefitLevelVO.getBenefitLevelSeq();
	                        description = benefitLevelVO.getBenefitLevelDesc();
			                term = benefitTerm.trim();
			                //Setting the frequency value to the frequency object.
			                frequency = new Integer(benefitLevelVO.getBenefitFrequency()).toString();
			                //Checks if the fequency values is 0 if 0  then 			               
			                if(frequency == WebConstants.ZERO_STRING){
			                	//sets the empty string to the frequency value
			                	frequency = WebConstants.EMPTY_STRING;
			                }else{
			                	frequency = frequency + " - ";
			                }
			                qualifier = benefitQualifier.trim();
                            pva = benefitLineVO.getPvaCode();
                            format = benefitLineVO.getDataTypeName();
                            //START CARS
                            benefitValue = WPDStringUtil.spaceSeparatedString(benefitLineVO.getBenefitValue(),7);
                            //END CARS
                        	reference = benefitLineVO.getReference();
                        	benefitLineId  = benefitLineVO.getBenefitLineId();
                            if (null != benefitLineVO.getBnftLineNotesExist()) {
                            	notesExist = benefitLineVO.getBnftLineNotesExist().toUpperCase().equals("Y");
                            }
                        }


                        if (j == 0) {
							pva = WebConstants.EMPTY_STRING; 
							format = WebConstants.EMPTY_STRING; 
							benefitValue = WebConstants.EMPTY_STRING; 
							reference = WebConstants.EMPTY_STRING; 
							renderNotesAttachmentImage = false;
  
                        } else {
                        	seq = WebConstants.EMPTY_STRING;  
							description = WebConstants.EMPTY_STRING;  
							term = WebConstants.EMPTY_STRING;  
							frequency = WebConstants.EMPTY_STRING;
							qualifier = WebConstants.EMPTY_STRING;
							
                            String benefitType = this
                                    .getStandardBenefitSessionObject()
                                    .getBenefitType();
                            
                            if (null != benefitType&& !"".equals(benefitType)) {
                                if (benefitType.equals(WebConstants.STD_TYPE)) {
                                	renderNotesAttachmentImage = true;
                                	mandate = false;
                                } else if (benefitType.equals(WebConstants.MNDT_TYPE)) {
                                	renderNotesAttachmentImage = false;
                                	mandate = true;
                                }
                            }
                        }
                        
                        benefitLineForViewVO = new BenefitLineForViewVO();
                        benefitLineForViewVO.setSeq(seq);
                        /*START CARS*/
                    	String desc = null;
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
                        /*END CARS*/
                        benefitLineForViewVO.setDescription(description); 
                        benefitLineForViewVO.setTerm(term);
                        //Setting the frequency value to the VO object.
                        benefitLineForViewVO.setFrequency(frequency); 
                        benefitLineForViewVO.setQualifier(qualifier); 
                        benefitLineForViewVO.setPva(pva);
                        benefitLineForViewVO.setFormat(format); 
                        benefitLineForViewVO.setBenefitValue(benefitValue); 
                        benefitLineForViewVO.setReference(reference); 
						benefitLineForViewVO.setBenefitLineId(""+benefitLineId);
						benefitLineForViewVO.setNotesExist(notesExist);
						benefitLineForViewVO.setRenderNotesAttachmentImage(renderNotesAttachmentImage);
						
                        this.benefitLevelsListForView.add(benefitLineForViewVO);
                        
                        idCount++;
                        if (j >= 1)
                           lineCount++;
                        else
                            lineCount = 0;
                    }
                }
            }
        }
    }
    Logger.logInfo(th.endPerfLogging());
	return benefitLevelsListForView;
}


/**
 * @param benefitLevelsListForView The benefitLevelsListForView to set.
 */
public void setBenefitLevelsListForView(List benefitLevelsListForView) {
	this.benefitLevelsListForView = benefitLevelsListForView;
}
    /**
     * Method to render the panel which has the list of the benefit lines
     * created.
     */
    public HtmlPanelGrid getViewPanel() {
        BenefitWrapperVO benefitWrapperVO = this.getBenefitWrapperVO();
        HtmlPanelGrid viewPanel = new HtmlPanelGrid();
        int idCount = 0;
        if (this.renderPanel && null != benefitWrapperVO
                && null != benefitWrapperVO.getBenefitLevelsList()) {
            viewPanel.setWidth("100%");
            viewPanel.setColumns(9);
            viewPanel.setBorder(0);
            viewPanel.setBgcolor("#cccccc");
            viewPanel.setCellpadding("1");
            viewPanel.setCellspacing("1");
            BenefitLineVO benefitLineVO = null;
            BenefitLevelVO benefitLevelVO = null;
            getBenefitHeaderViewPanel(viewPanel);
            List benefitLevelsList = benefitWrapperVO.getBenefitLevelsList();
            if (null != benefitLevelsList) {
                Collections.sort(benefitLevelsList);
                for (int i = 0; i < benefitLevelsList.size(); i++) {
                    int levelId = 0;
                    if (benefitLevelsList.size() > 0) {
                        benefitLevelVO = (BenefitLevelVO) benefitLevelsList
                                .get(i);
                    }
                    List benefitLines = benefitLevelVO.getBenefitLines();
                    if (null != benefitLines) {
                        //Collections.sort(benefitLines);
                        int lineCount = 0;
                        for (int j = 0; j < (benefitLines.size() + 1); j++) {
                            int lineId = 0;
                            if (benefitLines.size() > 0) {
                                benefitLineVO = (BenefitLineVO) benefitLines
                                        .get(lineCount);
                            }

                            HtmlOutputText inputText = new HtmlOutputText();
                            inputText.setId("SeqNo" + idCount);
                            inputText.setValue(""
                                    + benefitLevelVO.getBenefitLevelSeq());

                            HtmlOutputText inputText1 = new HtmlOutputText();
                            inputText1.setId("Description" + i);
                            inputText1.setValue(benefitLevelVO
                                    .getBenefitLevelDesc());

                            HtmlOutputText outputText1 = new HtmlOutputText();
                            outputText1.setId("Term" + idCount);
                            // **Change**
                            //outputText1.setValue(benefitLevelVO.getBenefitTerm());
                            List benefitTerms = benefitLevelVO
                                    .getBenefitTerms();
                            String benefitTerm = "";
                            String benfitTermCode = "";
                          
                            if (null != benefitTerms && !benefitTerms.isEmpty()) {
                                for (int k = 0; k <benefitTerms.size(); k++) {
                                    BenefitTermVO benefitTermVO = (BenefitTermVO) benefitTerms
                                            .get(k);
                                    
                                    benefitTerm = benefitTerm
                                            + benefitTermVO.getBenefitTerm();
                                    benfitTermCode = benfitTermCode
                                            + benefitTermVO
                                                   .getBenefitTermCode();
                                    if (k <(benefitTerms.size() - 1)) {
                                        benefitTerm = benefitTerm + ", ";
                                        benfitTermCode = benfitTermCode + ",";
                                    }
                                }
                                
                            }
                            outputText1.setValue(benefitTerm.trim());
                            
                            // **End**

                            HtmlOutputText outputText2 = new HtmlOutputText();
                            outputText2.setId("Qualifier" + idCount);
                            
// Change: Aggregate Qualifier
                       
                            List benefitQualifiers = benefitLevelVO
                            .getBenefitQualifiers();
                   
                    String benefitQualifier = "";
                    String benefitQualifierCode = "";
                    
                    if (null != benefitQualifiers && !benefitQualifiers.isEmpty()) {
                        for (int l = 0; l < benefitQualifiers.size(); l++) {
                            BenefitQualifierVO benefitQualifierVO = (BenefitQualifierVO) benefitQualifiers
                                    .get(l);
                            benefitQualifier = benefitQualifier + benefitQualifierVO.getBenefitQualifier();
                            benefitQualifierCode = benefitQualifierCode
										+benefitQualifierVO.getBenefitQualifierCode()
                            			+"~"
										+benefitQualifierVO.getBenefitQualifier();
                            if (l < (benefitQualifiers.size() - 1)) {
                            	benefitQualifier = benefitQualifier + ", ";
                            	benefitQualifierCode = benefitQualifierCode + ",";
                            }
                        }
                    }
                    outputText2.setValue(benefitQualifier.trim());
                            // **End**
                            
                    		HtmlOutputText outputText3 = new HtmlOutputText();
                            outputText3.setId("PVA" + idCount);
                            if (null != benefitLineVO){
                                outputText3.setValue(benefitLineVO.getPvaCode());
                            }

                            HtmlOutputText inputTextForDataType = new HtmlOutputText();
                            inputTextForDataType.setId("DataType" + idCount);
                            if (null != benefitLineVO)
                                inputTextForDataType.setValue(benefitLineVO
                                        .getDataTypeName());

                            HtmlOutputText inputText2 = new HtmlOutputText();
                            inputText2.setId("BenefitValue" + idCount);
                            if (null != benefitLineVO)
                                inputText2.setValue(benefitLineVO
                                        .getBenefitValue());

                            HtmlOutputText inputText3 = new HtmlOutputText();
                            // **Change**
                            inputText3.setId("Reference" + idCount);
                            inputText3.setValue(benefitLineVO.getReference());
                            // **End**

                            HtmlOutputText dummytext = new HtmlOutputText();
                            dummytext.setValue("   ");
                            HtmlOutputLabel lblSeq = new HtmlOutputLabel();
                            lblSeq.setFor("SeqNo" + idCount);
                            lblSeq.setId("lblSeq" + idCount);

                            HtmlOutputLabel lblDesc = new HtmlOutputLabel();
                            lblDesc.setFor("Description" + idCount);
                            lblDesc.setId("lblDesc" + idCount);

                            HtmlOutputLabel lblTerm = new HtmlOutputLabel();
                            lblTerm.setFor("Term" + idCount);
                            lblTerm.setId("lblTerm" + idCount);

                            HtmlOutputLabel lblQualifier = new HtmlOutputLabel();
                            lblQualifier.setFor("Qualifier" + idCount);
                            lblQualifier.setId("lblQualifier" + idCount);

                            HtmlOutputLabel lblPVA = new HtmlOutputLabel();
                            lblPVA.setFor("PVA" + idCount);
                            lblPVA.setId("lblPVA" + idCount);

                            HtmlOutputLabel lblDataType = new HtmlOutputLabel();
                            lblDataType.setFor("DataType" + idCount);
                            lblDataType.setId("lblDataType" + idCount);

                            HtmlOutputLabel lblbenefitValue = new HtmlOutputLabel();
                            lblbenefitValue.setFor("BenefitValue" + idCount);
                            lblbenefitValue.setId("lblbenefitValue" + idCount);

                            HtmlOutputLabel lblReference = new HtmlOutputLabel();
                            lblReference.setFor("Reference" + idCount);
                            lblReference.setId("lblReference" + idCount);

                            // **Change**
                            HtmlOutputLabel lblNotesAttachment = new HtmlOutputLabel();
                            lblReference.setFor("NotesAttachment" + idCount);
                            lblReference.setId("NotesAttachment" + idCount);

                            // Notes Attachment Image
                            HtmlGraphicImage notesAttachmentImage = new HtmlGraphicImage();
                            if (null != benefitLineVO.getBnftLineNotesExist()) {
                                if (benefitLineVO.getBnftLineNotesExist().toUpperCase()
                                        .equals("Y")) {
                                    notesAttachmentImage
                                            .setUrl("../../images/notes_exist.gif");
                                } else {
                                    notesAttachmentImage
                                            .setUrl("../../images/page.gif");
                                }
                            }
                            notesAttachmentImage.setStyle("cursor:hand;");
                            notesAttachmentImage.setId("notesAttachmentImage"
                                    + idCount);
                            notesAttachmentImage
                                    .setOnclick("viewAssociatedNotesPopup('../standardBenefit/benefitLineAssociatedNotesPopUp.jsp','"
                                            + benefitLineVO.getBenefitLineId()
                                            + "');return false;");
                            // **End**

                            if (j == 0) {
                            	
                            	lblTerm.getChildren().add(outputText1);
                                lblQualifier.getChildren().add(outputText2);

                                lblSeq.getChildren().add(inputText);
                                lblDesc.getChildren().add(inputText1);
                                lblPVA.getChildren().add(dummytext);
                                // **Change**
                                //lblReference.getChildren().add(inputText3);
                                lblReference.getChildren().add(dummytext);
                                // **End**
                                lblDataType.getChildren().add(dummytext);
                                lblbenefitValue.getChildren().add(dummytext);
                                // **Change**
                                lblNotesAttachment.getChildren().add(dummytext);
                                // **End**

                            } else {
                            	lblTerm.getChildren().add(dummytext);
                                lblQualifier.getChildren().add(dummytext);

                                lblSeq.getChildren().add(dummytext);
                                lblDesc.getChildren().add(dummytext);
                                lblPVA.getChildren().add(outputText3);
                                // **Change**
                                //lblReference.getChildren().add(dummytext);
                                lblReference.getChildren().add(inputText3);
                                // **End**
                                lblDataType.getChildren().add(
                                        inputTextForDataType);
                                lblbenefitValue.getChildren().add(inputText2);
                                // **Change**
                                // Get the benefit type from the session
                                String benefitType = this
                                        .getStandardBenefitSessionObject()
                                        .getBenefitType();
                                if (null != benefitType
                                        && !"".equals(benefitType)) {
                                    if (benefitType
                                            .equals(WebConstants.STD_TYPE)) {
                                        lblNotesAttachment.getChildren().add(
                                                notesAttachmentImage);
                                    } else if (benefitType
                                            .equals(WebConstants.MNDT_TYPE)) {
                                        lblNotesAttachment.getChildren().add(
                                                dummytext);
                                    }
                                }
                                //lblNotesAttachment.getChildren().add(notesAttachmentImage);
                                // **End**
                            }

                            /*lblTerm.getChildren().add(outputText1);
                            lblQualifier.getChildren().add(outputText2);
*/
                            viewPanel.getChildren().add(lblSeq);
                            viewPanel.getChildren().add(lblDesc);
                            viewPanel.getChildren().add(lblTerm);
                            viewPanel.getChildren().add(lblQualifier);
                            viewPanel.getChildren().add(lblPVA);
                            viewPanel.getChildren().add(lblDataType);
                            viewPanel.getChildren().add(lblbenefitValue);
                            viewPanel.getChildren().add(lblReference);
                            // **Change**
                            viewPanel.getChildren().add(lblNotesAttachment);
                            // **End**

                            idCount++;

                            if (j >= 1)
                               lineCount++;
                            
                            else
                                lineCount = 0;

                        }
                    }
                }
            }
        }

        return viewPanel;
    }


    // ** Change
    /**
     * to get the datatype list
     */

    private List getDataTypeList() {
        List dataTypeList = null;
        List selectItemsList = null;
        DataTypeRetrieveRequest dataTypeRetrieveRequest = (DataTypeRetrieveRequest) this
                .getServiceRequest(ServiceManager.DATA_TYPE_RETRIEVE_REQUEST);
        dataTypeRetrieveRequest
                .setStandardBenefitKey(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        DataTypeRetrieveResponse dataTypeRetrieveResponse = (DataTypeRetrieveResponse) this
                .executeService(dataTypeRetrieveRequest);
        if (null != dataTypeRetrieveResponse) {
            dataTypeList = dataTypeRetrieveResponse.getDataTypeList();
            selectItemsList = getSelectItemsList(dataTypeList);
        }
        
        return selectItemsList;
    }
    
    public List getSelectedTermList() {
    	TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23057 : Save/Load Benefit Coverage", "BenefitLevelBackingbean", "getSelectedTermList()"));
        List selectedTermList = null;
        List selectItemsList = null;

        SearchBenefitLevelTermRequest searchBenefitLevelTermRequest =(SearchBenefitLevelTermRequest)this
				.getServiceRequest(ServiceManager.SEARCH_BENEFIT_LEVEL_TERM_REQUEST);
        searchBenefitLevelTermRequest.setBenefitDefinitionId(this.getBenefitDefintionId());
        SearchBenefitLevelTermResponse searchBenefitLevelTermResponse = (SearchBenefitLevelTermResponse)this
				.executeService(searchBenefitLevelTermRequest);

        if (null != searchBenefitLevelTermResponse) {
        	selectedTermList = searchBenefitLevelTermResponse.getTermList();
        	if(selectedTermList.size() >0){
	        	if(getTermList(selectedTermList).size() >0){
	        		selectItemsList = getTermList(selectedTermList);
	        	}
        	}
        }
        Logger.logInfo(th.endPerfLogging());
        return selectItemsList;
    }



    /**
     * to get the SelectItemsList
     */
    private List getSelectItemsList(List dataTypeList) {
        List selectItemsList = new ArrayList();
        SelectItem defaultSelectItem = new SelectItem("0", "--Select--");
        selectItemsList.add(defaultSelectItem);
        if (null != dataTypeList && !dataTypeList.isEmpty()) {
            for (int i = 0; i < dataTypeList.size(); i++) {
                StandardBenefitDatatypeBO standardBenefitDatatypeBO = (StandardBenefitDatatypeBO) dataTypeList
                        .get(i);
                SelectItem selectItem = new SelectItem(
                        standardBenefitDatatypeBO.getSelectedItemCode(),
                        standardBenefitDatatypeBO.getDataTypeName());
                selectItemsList.add(selectItem);
            }
        }
        return selectItemsList;
    }

    private List getTermList(List termList) {
        List selectItemsList = new ArrayList();
        List selectedList = new ArrayList();
        if (null != termList && !termList.isEmpty()) {
            for (int i = 0; i < termList.size(); i++) {
                BenefitLevelBO benefitLevelBO = (BenefitLevelBO) termList
                        .get(i);
                String term =benefitLevelBO.getBenefitTerm();
                if(null!=term){
                	StringTokenizer stringTokenizer = new StringTokenizer(term,",");
                	while(stringTokenizer.hasMoreTokens()){
                		selectedList.add(stringTokenizer.nextToken());
                	}
                }
            }
            Set set = new TreeSet(selectedList);
            Iterator iterator = set.iterator();
            while(iterator.hasNext()){
            	SelectItem selectItem = new SelectItem(iterator.next());
            	selectItemsList.add(selectItem);
            }
            SelectItem lastItem = new SelectItem("ALL THE TERMS");
            selectItemsList.add(lastItem);
        }
        return selectItemsList;
    }
    
    
    // **End
    /**
     * @param panel
     */
    private void getBenefitHeaderPanel(HtmlPanelGrid panel) {

        HtmlOutputText seqOutputText = new HtmlOutputText();
        HtmlOutputText descOutputText = new HtmlOutputText();
        HtmlOutputText qualifierOutputText = new HtmlOutputText();
        HtmlOutputText termOutputText = new HtmlOutputText();
        HtmlOutputText pvaOutputText = new HtmlOutputText();
        HtmlOutputText dataTypeOutputText = new HtmlOutputText();
        HtmlOutputText benefitValueOutputText = new HtmlOutputText();
        HtmlOutputText referenceOutputText = new HtmlOutputText();
        HtmlOutputText emptyOutputText = new HtmlOutputText();

        seqOutputText.setValue("Sequence");
        seqOutputText.setId("Sequence");
        seqOutputText.setStyleClass("dataTableHeader");

        descOutputText.setValue("Description");
        descOutputText.setId("Description");
        descOutputText.setStyleClass("dataTableHeader");

        qualifierOutputText.setValue("Qualifier");
        qualifierOutputText.setId("qualifier");
        qualifierOutputText.setStyleClass("dataTableHeader");

        termOutputText.setValue("Term");
        termOutputText.setId("term");
        termOutputText.setStyleClass("dataTableHeader");

        pvaOutputText.setValue("PVA");
        pvaOutputText.setId("pva");
        pvaOutputText.setStyleClass("dataTableHeader");

        dataTypeOutputText.setValue("Format");
        dataTypeOutputText.setId("dataType");
        dataTypeOutputText.setStyleClass("dataTableHeader");

        benefitValueOutputText.setValue("Benefit Value");
        benefitValueOutputText.setId("benefitValue");
        benefitValueOutputText.setStyleClass("dataTableHeader");

        referenceOutputText.setValue("Reference");
        referenceOutputText.setId("reference");
        referenceOutputText.setStyleClass("dataTableHeader");

        emptyOutputText.setValue(" ");
        emptyOutputText.setId("button");

        panel.getChildren().add(seqOutputText);
        panel.getChildren().add(descOutputText);
        panel.getChildren().add(termOutputText);
        panel.getChildren().add(qualifierOutputText);
        panel.getChildren().add(pvaOutputText);
        panel.getChildren().add(dataTypeOutputText);
        panel.getChildren().add(benefitValueOutputText);
        panel.getChildren().add(referenceOutputText);
        panel.getChildren().add(emptyOutputText);
    }


    /*
     * This method get the benefit defn values for printing them
     */

    private void getBenefitDefinifitonValuesForPrint() {
        // Get the benefitDefinition List
        List benefitLevelsList = this.getBenftDefnList();
        // Create a list
        List listForPrint = new ArrayList();

        // Check whether the benefitDefinition List is null or empty
        if (null != benefitLevelsList && benefitLevelsList.size() != 0) {

            // Iterate through the list
            for (int i = 0; i < benefitLevelsList.size(); i++) {

                // Get the individual benefit levels
                BenefitLevelVO individualLevel = (BenefitLevelVO) benefitLevelsList
                        .get(i);

                // Create an instance of the VO
                BenefitDefinitionPrintVO benefitDefinitionPrintVO = new BenefitDefinitionPrintVO();

                // Set the level values to the VO

                benefitDefinitionPrintVO.setSequenceNo(new Integer(individualLevel.getBenefitLevelSeq()).toString());
                if (null != individualLevel.getBenefitLevelDesc()) {
                	/*START CARS*/
                	String desc = null;
                	String description = individualLevel.getBenefitLevelDesc();
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
                    benefitDefinitionPrintVO.setLevelDesc(description);
                    /*END CARS*/
                } else {
                    benefitDefinitionPrintVO.setLevelDesc(" ");
                }

                if (null != individualLevel.getBenefitQualifier()) {
                    benefitDefinitionPrintVO.setQualifierDesc(individualLevel
                            .getBenefitQualifier());
                } else {
                    benefitDefinitionPrintVO.setQualifierDesc(" ");
                }

                if (null != individualLevel.getBenefitTerm()) {
                    benefitDefinitionPrintVO.setTermDesc(individualLevel
                            .getBenefitTerm());
                } else {
                    benefitDefinitionPrintVO.setTermDesc(" ");
                }

                if (0 != individualLevel.getBenefitFrequency()) {
                    benefitDefinitionPrintVO.setBenefitFrequency(new Integer(individualLevel
                            .getBenefitFrequency()).toString()+" - ");
                } else {
                    benefitDefinitionPrintVO.setBenefitFrequency("");
                }
                // **Change**
             
                List benefitTerms = individualLevel
                        .getBenefitTerms();
                String benefitTerm = "";
                String benfitTermCode = "";
                if (null != benefitTerms && !benefitTerms.isEmpty()) {
                    for (int k = 0; k < benefitTerms.size(); k++) {
                        BenefitTermVO benefitTermVO = (BenefitTermVO) benefitTerms
                                .get(k);
                        benefitTerm = benefitTerm
                                + benefitTermVO.getBenefitTerm();
                        benfitTermCode = benfitTermCode
                                + benefitTermVO
                                        .getBenefitTermCode();
                        if (k < (benefitTerms.size() - 1)) {
                            benefitTerm = benefitTerm + ", ";
                            benfitTermCode = benfitTermCode + ",";
                        }
                    }
                }
                benefitDefinitionPrintVO.setTermDesc(benefitTerm.trim());
                
                // **End**

                               
                //  **Change 11/19/07**
                List benefitQualifiers = individualLevel.getBenefitQualifiers();
       
		        String benefitQualifier = "";
		        String benefitQualifierCode = "";
		        
		        if (null != benefitQualifiers && !benefitQualifiers.isEmpty()) {
		            for (int l = 0; l < benefitQualifiers.size(); l++) {
		                BenefitQualifierVO benefitQualifierVO = (BenefitQualifierVO) benefitQualifiers
		                        .get(l);
		                benefitQualifier = benefitQualifier + benefitQualifierVO.getBenefitQualifier();
		                benefitQualifierCode = benefitQualifierCode
									+benefitQualifierVO.getBenefitQualifierCode()
		                			+"~"
									+benefitQualifierVO.getBenefitQualifier();
		                
		                if (l < (benefitQualifiers.size() - 1)) {
		                	benefitQualifier = benefitQualifier + ", ";
		                	benefitQualifierCode = benefitQualifierCode + ",";
		                }
		            }
		        }
		        benefitDefinitionPrintVO.setQualifierDesc(benefitQualifier.trim());
      
                // **End**
                
                
                
                benefitDefinitionPrintVO.setProviderArrangementDesc(" ");

                benefitDefinitionPrintVO.setBenefitValue(" ");

                if (null != individualLevel.getReference()) {
                    benefitDefinitionPrintVO.setReferenceDesc(individualLevel
                            .getReference());
                } else {
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
                    BenefitLineVO individualLine = (BenefitLineVO) linesList
                            .get(j);

                    if (null != individualLine.getPVA()) {
                        benefitLinePrintVO
                                .setProviderArrangementDesc(individualLine
                                        .getPVA());
                    } else {
                        benefitLinePrintVO.setProviderArrangementDesc(" ");
                    }
                    
                    if (null != individualLine.getPvaCode()) {
                        benefitLinePrintVO
                                .setProviderArrangementId(individualLine
                                        .getPvaCode());
                    } else {
                        benefitLinePrintVO.setProviderArrangementId(" ");
                    }
//**Change: Aggregate Qualifier
//                    if (null != benefitTerms && !benefitTerms.isEmpty()) {
//                        benefitLinePrintVO.setTermDesc(benefitTerm.trim());
//                    }else if("".equals( individualLevel.getBenefitTerm() )){
//                    	benefitLinePrintVO.setTermDesc(individualLevel.getBenefitTerm());
//                    }else{
                        benefitLinePrintVO.setTermDesc(" ");
                   // }
//end                    
                    if (null != individualLine.getReferenceCode()) {
                        benefitLinePrintVO.setReferenceDesc(individualLine
                                .getReference());
                    } else {
                        benefitLinePrintVO.setReferenceDesc(" ");
                    }
//Change: Aggregate Qualifier
//                    if (null != benefitQualifiers && !benefitQualifiers.isEmpty()) {
//                        benefitLinePrintVO.setQualifierDesc(benefitQualifier.trim());
//                    } else if ("".equals( individualLevel.getBenefitQualifier())){
//                    	benefitLinePrintVO.setQualifierDesc(individualLevel.getBenefitQualifier());
//                    }else{
                        benefitLinePrintVO.setQualifierDesc(" ");
                    //}
//end                    
                    //String benftVal = " ";
                    if (null != individualLine.getBenefitValue()) {
                        //benftVal = individualLine.getBenefitValue();
                        benefitLinePrintVO.setBenefitValue
                        			(WPDStringUtil.spaceSeparatedString(individualLine.getBenefitValue(),15));
                    } else {
                        //benftVal = " ";
                        benefitLinePrintVO.setBenefitValue
            						(" ");
                    }
                    benefitLinePrintVO.setDataTypeDesc(individualLine
                            .getDataTypeName());
                    /*StringBuffer dataDesc = new StringBuffer();
                    if (null != individualLine.getDataTypeName()) {
                        if (individualLine.getDataTypeName().equals("$")) {
                            dataDesc.append(individualLine.getDataTypeName())
                                    .append(" ").append(benftVal);
                        } else if (individualLine.getDataTypeName().equals("%")) {
                            dataDesc.append(benftVal).append(" ").append(
                                    individualLine.getDataTypeName());
                        } else if (individualLine.getDataTypeName().equals(
                                WebConstants.BOOLEAN)) {
                            dataDesc.append(benftVal);
                        }
                        benefitLinePrintVO.setBenefitValue(dataDesc.toString());
                    } else {
                        benefitLinePrintVO.setBenefitValue(" ");
                    }*/

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
     * Method for retrieving header panel for viewing benefit.
     * @param viewPanel
     */
    private void getBenefitHeaderViewPanel(HtmlPanelGrid viewPanel) {

        HtmlOutputText seqOutputText = new HtmlOutputText();
        HtmlOutputText descOutputText = new HtmlOutputText();
        HtmlOutputText qualifierOutputText = new HtmlOutputText();
        HtmlOutputText termOutputText = new HtmlOutputText();
        HtmlOutputText pvaOutputText = new HtmlOutputText();
        HtmlOutputText dataTypeOutputText = new HtmlOutputText();
        HtmlOutputText benefitValueOutputText = new HtmlOutputText();
        HtmlOutputText referenceOutputText = new HtmlOutputText();
        HtmlOutputText emptyOutputText = new HtmlOutputText();

        seqOutputText.setValue("Sequence");
        seqOutputText.setId("Sequence");
        seqOutputText.setStyleClass("dataTableHeader");

        descOutputText.setValue("Description");
        descOutputText.setId("Description");
        descOutputText.setStyleClass("dataTableHeader");

        qualifierOutputText.setValue("Qualifier");
        qualifierOutputText.setId("qualifier");
        qualifierOutputText.setStyleClass("dataTableHeader");

        termOutputText.setValue("Term");
        termOutputText.setId("term");
        termOutputText.setStyleClass("dataTableHeader");

        pvaOutputText.setValue("PVA");
        pvaOutputText.setId("pva");
        pvaOutputText.setStyleClass("dataTableHeader");

        dataTypeOutputText.setValue("Format");
        dataTypeOutputText.setId("DataType");
        dataTypeOutputText.setStyleClass("dataTableHeader");

        benefitValueOutputText.setValue("Benefit Value");
        benefitValueOutputText.setId("benefitValue");
        benefitValueOutputText.setStyleClass("dataTableHeader");

        referenceOutputText.setValue("Reference");
        referenceOutputText.setId("reference");
        referenceOutputText.setStyleClass("dataTableHeader");

        viewPanel.getChildren().add(seqOutputText);
        viewPanel.getChildren().add(descOutputText);
        viewPanel.getChildren().add(termOutputText);
        viewPanel.getChildren().add(qualifierOutputText);
        viewPanel.getChildren().add(pvaOutputText);
        viewPanel.getChildren().add(dataTypeOutputText);
        viewPanel.getChildren().add(benefitValueOutputText);
        viewPanel.getChildren().add(referenceOutputText);
        // **Change**
        viewPanel.getChildren().add(emptyOutputText);
        // **End**
    }


    /**
     * @param panel
     *            The panel to set.
     */
    public void setPanel(HtmlPanelGrid panel) {
        this.panel = panel;
    }


    /**
     * Method for setting panel for view.
     * @param viewPanel
     */
    public void setViewPanel(HtmlPanelGrid viewPanel) {
        this.viewPanel = viewPanel;
    }


    /**
     * 
     * This method saves the benefit levels that are provided from the jsp page
     */
    public String saveBenefitLevels() {
    	TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23057 : Save benefit Coverage", "BenefirtLevelBackingBean", "searchBenefitLevel()"));
    	messageList = new ArrayList();
        BenefitWrapperVO benefitWrapperVO = this.populateBenefitWrapperVO(this.benefitLevelViewList);
        
        benefitWrapperVO.setBenefitDefinitionId(getBenefitDefintionId());
        benefitWrapperVO.setBenefitIdentifier(getStandardBenefitSessionObject()
                .getStandardBenefitName());
        benefitWrapperVO.setMasterVersion(getStandardBenefitSessionObject()
                .getStandardBenefitVersionNumber());
        benefitWrapperVO
                .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                        .getStandardBenefitParentKey());
        benefitWrapperVO
                .setStandardBenefitKey(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        benefitWrapperVO.setMasterStatus(getStandardBenefitSessionObject()
                .getStandardBenefitStatus());
        benefitWrapperVO.setBusinessDomains(getStandardBenefitSessionObject()
                .getBusinessDomains());
        
        List benefitLevels = benefitWrapperVO.getBenefitLevelsList();
        if (validateEditableFields(benefitLevels)) {
            SaveBenefitLevelResponse saveBenefitLevelResponse = updateBenefitLevels(
                    benefitWrapperVO, benefitLevels);
            if (null != saveBenefitLevelResponse)
                this.setBenefitWrapperVO(saveBenefitLevelResponse
                        .getBenefitWrapperVO());
            if (null != this.getBenefitWrapperVO()) {
                if (null != this.getBenefitWrapperVO().getBenefitLevelsList()) {
                    registerSequence(this.getBenefitWrapperVO().getBenefitLevelsList());
                    this.setRenderPanel(true);
                    getStandardBenefitSessionObject().setDateAvailable(true);
                    getStandardBenefitSessionObject().setBenefitLevelForViewVOList(populateBenefitLevelForViewVO(this.getBenefitWrapperVO()));
                    getStandardBenefitSessionObject().setBenefitWrapperVO(this.getBenefitWrapperVO());
                } else {
                    this.setRenderPanel(false);
                    getStandardBenefitSessionObject().setDateAvailable(false);
                    getStandardBenefitSessionObject().setBenefitLevelForViewVOList(null);
                    getStandardBenefitSessionObject().setBenefitWrapperVO(null);
                }
            } else{
            	getStandardBenefitSessionObject().setDateAvailable(false);
                getStandardBenefitSessionObject().setBenefitLevelForViewVOList(null);
                getStandardBenefitSessionObject().setBenefitWrapperVO(null);
                this.setRenderPanel(false);
            }
        } else {
            addAllMessagesToRequest(validationMessages);
            return WebConstants.FAILURE;
            //benefitWrapperVO = getUpdatedListFromScreen();
            //this.setBenefitWrapperVO(benefitWrapperVO);
            //this.setRenderPanel(true);
        }
        this.setAddButtonInvoked("N");
        if(null== this.benefitTerm || "".equals(this.benefitTerm) ||
        		null== this.benefitTermQualifier || "".equals(this.benefitTermQualifier) ||
				null== this.providerArrangement || "".equals(this.providerArrangement) )
        			getRequest().setAttribute("RETAIN_Value", "");
        Logger.logInfo(th.endPerfLogging());
        return WebConstants.SUCCESS;
    }
    
    /**
     * 
     * This method updates the benefit levels that are provided from the jsp
     * page
     */
	public String reloadPage(){
		return "success";
	}

    /**
     * 
     * This method updates the benefit levels that are provided from the jsp
     * page
     */
    private SaveBenefitLevelResponse updateBenefitLevels(
            BenefitWrapperVO benefitWrapperVO, List benefitLevels) {
        benefitWrapperVO.setBenefitLevelsList(benefitLevels);
        SaveBenefitLevelRequest saveBenefitLevelRequest = (SaveBenefitLevelRequest) this
                .getServiceRequest(ServiceManager.SAVE_BENEFIT_LEVEL_REQUEST);
        // modified for the performance issue on 12thDec 2007
        List newLineList = null;
        List newLevelList = new ArrayList();
        if(null != benefitWrapperVO.getBenefitLevelsList()){
        //to update the sequence no after deleting a benefit level or line
            if(benefitWrapperVO.isDeleteFlag()){
                Iterator iterator = benefitWrapperVO.getBenefitLevelsList().iterator();
                
                while(iterator.hasNext()){
                    BenefitLevelVO benefitLevelVO = (BenefitLevelVO) iterator.next();
                    benefitLevelVO.setModified(true); 
                    newLineList = benefitLevelVO.getBenefitLines();
                    if((null != newLineList)&& (newLineList.size()>0)){
	                    Iterator lineIterator = newLineList.iterator();
	                    while(lineIterator.hasNext()){
	                        BenefitLineVO benefitLineVO = (BenefitLineVO) lineIterator.next();
	                        benefitLineVO.setModified(true); 
	                    }
	                    newLevelList.add(benefitLevelVO);
                    }
                }
            }else{
                newLevelList = benefitWrapperVO.getBenefitLevelsList();
            }
        }else{
            newLevelList = benefitWrapperVO.getBenefitLevelsList();
        }
        List updatedBenefitLevelsList = null;
        if (null != newLevelList) {
			SequenceUtil sequenceUtil = new SequenceUtil();
			updatedBenefitLevelsList = null;
			if (this.getSelectedTerm().equals("ALL THE TERMS"))
				updatedBenefitLevelsList = sequenceUtil
						.reOrderObjects(newLevelList);
			else
				updatedBenefitLevelsList = sequenceUtil
						.reOrderFilterObjects(newLevelList);
			//modification ends
		}
        benefitWrapperVO.setBenefitLevelsList(updatedBenefitLevelsList);
        if(this.getSelectedTerm().equals("ALL THE TERMS"))
        	benefitWrapperVO.setSelectedBenefitTerm("%");
        else
        	benefitWrapperVO.setSelectedBenefitTerm("%"+this.getSelectedTerm()+"%");

        saveBenefitLevelRequest.setBenefitWrapperVO(benefitWrapperVO);
        SaveBenefitLevelResponse saveBenefitLevelResponse = new SaveBenefitLevelResponse();
        saveBenefitLevelResponse = (SaveBenefitLevelResponse) this
                .executeService(saveBenefitLevelRequest);
        if(null != saveBenefitLevelResponse && null != saveBenefitLevelResponse.getMessages()){
        	messageList.addAll(saveBenefitLevelResponse.getMessages());
        }
        addAllMessagesToRequest(messageList);
        return saveBenefitLevelResponse;
    }


    /**
     * This method validate the fields that are entered from jsp page
     */
    private boolean validateEditableFields(List benefitLevels) {
        validationMessages = new ArrayList(1);
        boolean validateEditableFieldsFlag = true;
        boolean validateDataTypeFlag = true;
        boolean validateDescriptionFlag = true;
        boolean validateFrequencyFlag = true;
        boolean validateDescriptionLength = true;
        boolean isNumber = true;
        boolean isMaxInteger = false;
        boolean isDecimalNumber = true;
        boolean isGreaterThanHundred = true;
        boolean isBooleanFlag = true;
        boolean isValidPrecision = true;
        boolean greaterThanHundredFlag = false;
        boolean validPrecisionFlag = false;
        boolean decimalNumberFlag = false;
        boolean maxIntegerFlag = false;
        boolean numberFlag = false;
        boolean boolFlag = false;
        boolean isFrequencyNumber = true;
        boolean isMaxIntegerFrequency = false;
        // **Change**
        ErrorMessage errorMessage = null;
        String sysDataType = null;
        String dataTypeName = null;
        List universeDataTypeList = WPDStringUtil.getUniverseDataTypeList();
        // **End**
        if (null != benefitLevels) {
            for (int i = 0; i < benefitLevels.size(); i++) {
                BenefitLevelVO benefitLevelVO = (BenefitLevelVO) benefitLevels
                        .get(i);
                List benefitLines = benefitLevelVO.getBenefitLines();
                //Collections.sort(benefitLines);
                if (null != benefitLines) {
                    for (int k = 0; k < benefitLines.size(); k++) {
                        BenefitLineVO benefitLineVO = (BenefitLineVO) benefitLines
                                .get(k);
                        int dataTypeId = benefitLineVO.getDataTypeId();
                        if (dataTypeId == 0) {
                            validateEditableFieldsFlag = false;
                            validateDataTypeFlag = false;
                        } else {
                            // verify
                            DataTypeLocateResult dataTypeDetails = null;
                            dataTypeDetails = WPDStringUtil.getDataTypeDetails(
                                    universeDataTypeList, dataTypeId);
                            //sysDataType =
                            // WPDStringUtil.getSysDatatype(universeDataTypeList,
                            // dataTypeId);
                            //dataTypeName =
                            // WPDStringUtil.getDataTypeName(universeDataTypeList,
                            // dataTypeId);
                            if (null != dataTypeDetails) {
                                sysDataType = dataTypeDetails.getDataTypeDesc()
                                        .toUpperCase().trim();
                                dataTypeName = dataTypeDetails
                                        .getDataTypeName().toUpperCase().trim();
                            }
                                   if (null != benefitLineVO.getBenefitValue()
                                    && !"".equals(benefitLineVO
                                            .getBenefitValue())) {
                                if ((WebConstants.BOOLEAN_NEW)
                                        .equals(sysDataType.toUpperCase()))
                                    isBooleanFlag = WPDStringUtil
                                            .isValidBoolean(benefitLineVO
                                                    .getBenefitValue());
                                else if ((WebConstants.DOLLAR)
                                        .equals(sysDataType.toUpperCase())){
                                    isNumber = WPDStringUtil
                                            .isNumber(benefitLineVO
                                                    .getBenefitValue());
                                    if(isNumber){
                                    	benefitLineVO.setBenefitValue
											(WPDStringUtil.removeUnwantedZeroes
												(benefitLineVO.getBenefitValue()));
                                    }
                                }
                                else if ((WebConstants.PERCENTAGE)
                                        .equals(sysDataType.toUpperCase())) {
                                	
                                  String benefitPercentValue = benefitLineVO.getBenefitValue(); 
                                	if (benefitPercentValue.equals(".")) {
            							isDecimalNumber = false;
            						}
                                	else {
                                		if (benefitPercentValue.charAt(0) == '.') {
                                			benefitPercentValue = "0".concat(benefitPercentValue);
                                	}
                                    isDecimalNumber = WPDStringUtil.isDecimalNumber(benefitPercentValue);
                                    if (isDecimalNumber) {
                                        isValidPrecision = WPDStringUtil.isValidPrecision(
                                                		benefitPercentValue,
                                                        WebConstants.ALLOWED_NUMBER_OF_PRECISION);
                                        benefitPercentValue = WPDStringUtil
										.compareDecimal(benefitPercentValue);
                                        if (isValidPrecision) {
                                            /*
                                             * double value =
                                             * Double.parseDouble(benefitLineVO.getBenefitValue());
                                             * if(value > 100){
                                             * isGreaterThanHundred = false; }
                                             */
                                            isGreaterThanHundred = WPDStringUtil
                                                    .isGreaterThanHundred(benefitPercentValue);
                                            if(isGreaterThanHundred){
                                            	benefitLineVO.setBenefitValue
        											(WPDStringUtil.removeUnwantedZeroes
        												(benefitPercentValue));
                                            }
                                        }
                                    	}
                                	}
                                	benefitLineVO.setBenefitValue(benefitPercentValue);
                                } else if ((WebConstants.INTEGER)
                                        .equals(sysDataType.toUpperCase())) {
                                    isNumber = WPDStringUtil
                                            .isNumber(benefitLineVO
                                                    .getBenefitValue());
                                    if (isNumber) {
                                        isMaxInteger = WPDStringUtil
                                                .isMaxInteger(benefitLineVO
                                                        .getBenefitValue());
                                        if(!isMaxInteger){
                                        	benefitLineVO.setBenefitValue
    											(WPDStringUtil.removeUnwantedZeroes
    												(benefitLineVO.getBenefitValue()));
                                        }
                                    }
                                } else if ((WebConstants.FLOAT)
                                        .equals(sysDataType.toUpperCase())) {
                                    isDecimalNumber = WPDStringUtil
                                            .isDecimalNumber(benefitLineVO
                                                    .getBenefitValue());
                                    if (isDecimalNumber) {
                                        isValidPrecision = WPDStringUtil
                                                .isValidPrecision(
                                                        benefitLineVO
                                                                .getBenefitValue(),
                                                        WebConstants.ALLOWED_NUMBER_OF_PRECISION);
                                        if(isDecimalNumber){
                                        	benefitLineVO.setBenefitValue
    											(WPDStringUtil.removeUnwantedZeroes
    												(benefitLineVO.getBenefitValue()));
                                        }
                                    }
                                }
                            }
                        }
                        // **Change**
                        /*
                         * if(!isNumber || !isDecimalNumber ||
                         * !isGreaterThanHundred || !isBooleanFlag){ break; }
                         */
                        // **End**
                        if ("".equals(benefitLevelVO.getBenefitLevelDesc())
                                || null == benefitLevelVO.getBenefitLevelDesc()) {
                            validateEditableFieldsFlag = false;
                            validateDescriptionFlag = false;
                        }
                        /*if (benefitLevelVO.getBenefitLevelDesc().length() > 32) {
                            validateEditableFieldsFlag = false;
                            validateDescriptionLength = false;
                        }*/
                        //Validation for Frequency Start
                        // if the frequency value is 0 then that qualifier doesn't hasve frequency
                        //validation won't happen for those frequncy whose value is 0
                        if(0 != benefitLevelVO.getOldFrequencyValue()){
                        	//Checks whether frequency is a number.
                        	if(benefitLevelVO.isFrequencyValueEmpty()){                        		
		                        if (benefitLevelVO.isFrequencyNumber()) {
		                        	//Check whether the screen value for frequency is set to 0
									if (0 == benefitLevelVO.getBenefitFrequency()) {
										validateEditableFieldsFlag = false;
										validateFrequencyFlag = false;
									}
									isMaxIntegerFrequency = WPDStringUtil
											.isMaxInteger(new Integer(
													benefitLevelVO
															.getBenefitFrequency())
													.toString());
									if (!isMaxIntegerFrequency) {
										benefitLevelVO
												.setBenefitFrequency(new Integer(
														WPDStringUtil
																.removeUnwantedZeroes(new Integer(
																		benefitLevelVO
																				.getBenefitFrequency())
																		.toString()))
														.intValue());
									}
								}else{//if the frqunecy is not a number error message would be initiated.
		                        	errorMessage = new ErrorMessage(
		                                    BusinessConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_NUMBER);
		                        	//Checking null for the description. Sets the description to the 
		                        	//error message to state validation happen for that description
		                        	 if (null != benefitLevelVO
		                                    .getBenefitLevelDesc())
		                        	 	errorMessage.setParameters(new String[] {benefitLevelVO.getBenefitLevelDesc()});
		                        	if(!(numberFlag = removeDuplicateErrorMessage(errorMessage)))
		                        		validationMessages.add(errorMessage);
		                        	validateEditableFieldsFlag = false;
		                        	isFrequencyNumber = true;
		                        }
                        	}else{
                        		errorMessage = new ErrorMessage(
	                                    BusinessConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_EMPTY);
	                        	//Checking null for the description. Sets the description to the 
	                        	//error message to state validation happen for that description
	                        	 if (null != benefitLevelVO
	                                    .getBenefitLevelDesc())
	                        	 	errorMessage.setParameters(new String[] {benefitLevelVO.getBenefitLevelDesc()});
	                        	if(!(numberFlag = removeDuplicateErrorMessage(errorMessage)))
	                        		validationMessages.add(errorMessage);
	                        	validateEditableFieldsFlag = false;
	                        	isFrequencyNumber = true;
                        	}
                        }
                        //Validation for Frequency End
                        
                        if (!isBooleanFlag) {
                            errorMessage = new ErrorMessage(
                                    WebConstants.DATA_TYPE_MISMATCH_BENEFIT_LEVEL);
                            if (null != dataTypeName
                                    && null != benefitLevelVO
                                            .getBenefitLevelDesc())
                                errorMessage.setParameters(new String[] {
                                        dataTypeName,
                                        benefitLevelVO.getBenefitLevelDesc() });
                            if(!(boolFlag = removeDuplicateErrorMessage(errorMessage)))
                                validationMessages.add(errorMessage);
                            validateEditableFieldsFlag = false;
                            isBooleanFlag = true;
                        }
                        //				 **Change**
                        if (!isNumber) {
                            errorMessage = new ErrorMessage(
                                    BusinessConstants.MSG_BENEFIT_LEVEL_VALUE_NOT_NUMBER);
                            if (null != dataTypeName
                                    && null != benefitLevelVO
                                            .getBenefitLevelDesc())
                                errorMessage.setParameters(new String[] {
                                        dataTypeName,
                                        benefitLevelVO.getBenefitLevelDesc() });
                          if(!(numberFlag = removeDuplicateErrorMessage(errorMessage)))
                                 validationMessages.add(errorMessage);
                            validateEditableFieldsFlag = false;
                            isNumber = true;
                        }
                        if (isMaxInteger) {
                            errorMessage = new ErrorMessage(
                                    BusinessConstants.MSG_BENEFIT_LEVEL_VALUE_MAX_INTEGER);
                            if (null != dataTypeName
                                    && null != benefitLevelVO
                                            .getBenefitLevelDesc())
                                errorMessage.setParameters(new String[] {
                                        dataTypeName,
                                        benefitLevelVO.getBenefitLevelDesc() });
                            if(!(maxIntegerFlag = removeDuplicateErrorMessage(errorMessage)))
                                 validationMessages.add(errorMessage);
                            validateEditableFieldsFlag = false;
                            isMaxInteger = false;
                        }
                        if (!isDecimalNumber) {
                            errorMessage = new ErrorMessage(
                                    BusinessConstants.MSG_BENEFIT_LEVEL_VALUE_NOT_DECIMAL_NO);
                            if (null != dataTypeName
                                    && null != benefitLevelVO
                                            .getBenefitLevelDesc())
                                errorMessage.setParameters(new String[] {
                                        dataTypeName,
                                        benefitLevelVO.getBenefitLevelDesc() });
                            if(!(decimalNumberFlag = removeDuplicateErrorMessage(errorMessage)))
                                 validationMessages.add(errorMessage);
                            validateEditableFieldsFlag = false;
                            isDecimalNumber = true;
                        }
                        if (!isValidPrecision) {
                            errorMessage = new ErrorMessage(
                                    BusinessConstants.MSG_BENEFIT_LEVEL_VALUE_PRECISION);
                            if (null != dataTypeName
                                    && null != benefitLevelVO
                                            .getBenefitLevelDesc())
                                errorMessage
                                        .setParameters(new String[] {
                                                String
                                                        .valueOf(WebConstants.ALLOWED_NUMBER_OF_PRECISION),
                                                dataTypeName,
                                                benefitLevelVO
                                                        .getBenefitLevelDesc() });
                            if(!(validPrecisionFlag = removeDuplicateErrorMessage(errorMessage)))
                                 validationMessages.add(errorMessage);
                            validateEditableFieldsFlag = false;
                            isValidPrecision = true;
                        }
                        if (!isGreaterThanHundred) {
                            errorMessage = new ErrorMessage(
                                    BusinessConstants.MSG_BENEFIT_LEVEL_VALUE_GREATER_HUNDRED);
                            if (null != dataTypeName
                                    && null != benefitLevelVO
                                            .getBenefitLevelDesc())
                                errorMessage.setParameters(new String[] {
                                        dataTypeName,
                                        benefitLevelVO.getBenefitLevelDesc() });
                            if(!(greaterThanHundredFlag = removeDuplicateErrorMessage(errorMessage)))
                                 validationMessages.add(errorMessage);
                            validateEditableFieldsFlag = false;
                            isGreaterThanHundred = true;
                        }
                        // **End**
                    }
                    greaterThanHundredFlag = false;
                    validPrecisionFlag = false;
                    decimalNumberFlag = false;
                    maxIntegerFlag = false;
                    numberFlag = false;
                    boolFlag = false;
                }
                // **Change**
                /*
                 * if(!isNumber){ break; } if(!isDecimalNumber ||
                 * !isGreaterThanHundred){ break; }
                 */
                // **End**
            }
        }
        if (!validateEditableFieldsFlag) {
        	//modified on 18th Dec 2007
            errorFlag = true;
            if (!validateDataTypeFlag)
                validationMessages.add(new ErrorMessage(
                        WebConstants.MANDATE_DATATYPE_REQUIRED));
            if (!validateDescriptionFlag)
                validationMessages
                        .add(new ErrorMessage(
                                WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_REQUIRED));
           /* if (!validateDescriptionLength)
                validationMessages.add(new ErrorMessage(
                        WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_LENGTH));*/
            if (!validateFrequencyFlag)
                validationMessages
                        .add(new ErrorMessage(
                                WebConstants.MSG_BENEFIT_LEVEL_FREQUENCY_NOT_CORRECT));
        }

        return validateEditableFieldsFlag;
    }


    /**
     * Method for retrieving deleted benefit level BO
     * @return BenefitLevelVO
     */
    private BenefitLevelVO getDeletedBenefitLevelVO() {
        List levelIds = null;
        if (null != this.getSession().getAttribute("LevelIds")) {
            levelIds = (List) this.getSession().getAttribute("LevelIds");
        }
        BenefitLevelVO deletedBenefitLevelVO = new BenefitLevelVO();
        if (null != levelIds) {
            if (levelIds.size() > 0) {
                int levelId = (new Integer(levelIds.get(0).toString()))
                        .intValue();
                deletedBenefitLevelVO.setBenefitLevelId(levelId);
            }
        }
        //this.getSession().setAttribute("LevelIds",null);
        return deletedBenefitLevelVO;
    }


    /**
     * Method for retrieving deleted benefit line BO
     * @return BenefitLineVO
     */
    private BenefitLineVO getDeletedBenefitLineVO() {
        List lineIds = null;
        if (null != this.getSession().getAttribute("LineIds")) {
            lineIds = (List) this.getSession().getAttribute("LineIds");
        }
        BenefitLineVO deletedBenefitLineVO = new BenefitLineVO();
        if (null != lineIds) {
            if (lineIds.size() > 0) {
                int lineId = (new Integer(lineIds.get(0).toString()))
                        .intValue();
                deletedBenefitLineVO.setBenefitLineId(lineId);
            }
        }
        this.getSession().setAttribute("LineIds", null);
        return deletedBenefitLineVO;
    }


    /**
     * this method returns the list along with the newly added ones
     */
    private BenefitWrapperVO getUpdatedListFromScreen() {
    	boolean isSeqChanged = false;
    	boolean isDescriptionTruncated = false;
    	messageList = new ArrayList();
        Set keysForLevel = dataHiddenValLevelId.keySet();

        Iterator keyIterLevel = keysForLevel.iterator();

        BenefitWrapperVO benefitWrapperVO = new BenefitWrapperVO();
        benefitWrapperVO.setBenefitDefinitionId(getBenefitDefintionId());
        benefitWrapperVO.setBenefitIdentifier(getStandardBenefitSessionObject()
                .getStandardBenefitName());
        benefitWrapperVO.setMasterVersion(getStandardBenefitSessionObject()
                .getStandardBenefitVersionNumber());
        benefitWrapperVO
                .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                        .getStandardBenefitParentKey());
        benefitWrapperVO
                .setStandardBenefitKey(getStandardBenefitSessionObject()
                        .getStandardBenefitKey());
        benefitWrapperVO.setMasterStatus(getStandardBenefitSessionObject()
                .getStandardBenefitStatus());
        benefitWrapperVO.setBusinessDomains(getStandardBenefitSessionObject()
                .getBusinessDomains());
        List benefitLevels = new ArrayList();
        Object keyLevel, keyLine, levelIdValue, lineIdValue, descValue, frequencyValue, dataTypeValue, benefitValue, termValue, qualifierValue, pvaValue, termCodeValue, qualifierCodeValue, pvaCodeValue, seqValue, referenceValueCode, notesExist;
        while (keyIterLevel.hasNext()) {
            keyLevel = keyIterLevel.next();
            levelIdValue = dataHiddenValLevelId.get(keyLevel);

            if (null != levelIdValue) {
                String levelId = null;
                String lineId = null;
                int oldFreq = 0;
                int newFreq = 0; 
                boolean isNumber = false;
                boolean isFreqeuncyValueEmpty = false;
                List benefitLines = new ArrayList();
                isDescriptionTruncated = false;
                BenefitLevelVO benefitLevelVO = new BenefitLevelVO();
                benefitLevelVO.setBenefitLevelId((new Integer(levelIdValue
                        .toString()).intValue()));
                benefitLevelVO.setBenefitDefinitionId(getBenefitDefintionId());
                
                
                seqValue = dataHiddenValSeq.get(new Long(levelIdValue
                        .toString()));
                String seqNum = (String)seqValue;
                if(null != seqValue && seqNum.matches("[\\d]+")){ 
    				benefitLevelVO.setBenefitLevelSeq((new Integer((String)seqValue)).intValue());
    			}else{
    			    benefitLevelVO.setBenefitLevelSeq(1);
    			}
                
                //change for performace
                Object oldSeqValue = hiddenSequenceMap.get(new Long(levelIdValue
                        .toString()));
                if(null != oldSeqValue && null != seqNum && !(seqNum.equals(oldSeqValue.toString()))){
                	benefitLevelVO.setModified(true);
                	isSeqChanged = true;
                }
                //end
                
                descValue = dataHiddenValDesc.get(new Long(levelIdValue
                        .toString().trim()));
                descValue=descValue.toString().trim();
                
                // **Change**
                //benefitLevelVO.setBenefitLevelDesc(descValue.toString().trim());
                //benefitLevelVO.setBenefitLevelDesc(descValue.toString().trim() ""  
                //        .toUpperCase());
                // **End**
                
                //change for performace
                Object oldDescValue = hiddenDescMap.get(new Long(levelIdValue
                        .toString()));
                
                //Frequency Start
                //Getting the frequency  value from the Hash map and Set it in the frequency object
                frequencyValue = dataHiddenValFreq.get(new Long(levelIdValue.toString().trim()));
                //Getting the previous value from the Hash map and set it in the oldFrequencyValue object
                Object oldFrequencyValue = hiddenFreqMap.get(new Long(levelIdValue
                        .toString()));
                if(null != frequencyValue && !(frequencyValue.equals(""))){
                //Checks whether the frequency value is a number
                //if mot then sets the isNumber flag false
                	isNumber = WPDStringUtil.isNumber(frequencyValue.toString());
                	isFreqeuncyValueEmpty = true;                	
                }else{
                	isFreqeuncyValueEmpty = false;
                }                 
                benefitLevelVO.setFrequencyValueEmpty(isFreqeuncyValueEmpty);
                //Settting the flag to the flag to the benefitLevelVO' flag           		
        		benefitLevelVO.setFrequencyNumber(isNumber);  
                //Null check for the oldFrequencyValue
            	if(null != oldFrequencyValue){ 
            		//Converting the old frequency value to integer
                	oldFreq = new Integer(oldFrequencyValue.toString()).intValue();
                	//Setting the old frequenct value to the BenefitLevelVO
            		benefitLevelVO.setOldFrequencyValue(oldFreq);               	
            	}else{
            		//If the oldFrequencyValue is null then it would set the value to 0
            		benefitLevelVO.setOldFrequencyValue(0);
            	}
                //Enters the loop only when the isNumber flag is true                
                if(isNumber){
                	//Gets the value from the frequency object converts it integer
                	//Set it back to the benefitLevelVO's frequency value
	                benefitLevelVO.setBenefitFrequency((new Integer(frequencyValue
	                        .toString()).intValue()));
	                //Converting the page frequency value to integer
	                newFreq = new Integer(frequencyValue.toString()).intValue();
	                //Checks whether the frequency value has been changed.
	                if(newFreq != oldFreq){                	
	                	benefitLevelVO.setModified(true);
	                }
                }
                //Frequency End
                
                if((isSeqChanged)|| (null != oldDescValue && null != descValue && !(descValue.equals(oldDescValue.toString().trim())))){
                	benefitLevelVO.setModified(true);
                }
                //end
                
                // **Change
                /*
                 * referenceValueCode = dataHiddenValReferenceCode.get(new
                 * Long(levelIdValue.toString())); List referenceList =
                 * WPDStringUtil.getListFromTildaString(referenceValueCode.toString(),2,2,2);
                 * if(null != referenceList && referenceList.size() > 0 && null !=
                 * referenceList.get(0))
                 * benefitLevelVO.setReferenceCode(((String)referenceList.get(0)).trim());
                 * referenceValue = dataHiddenValReference.get(new
                 * Long(levelIdValue.toString()));
                 * benefitLevelVO.setReference(referenceValue.toString());
                 */
                benefitLevelVO.setReferenceCode("");
                benefitLevelVO.setReference("");
                // **End**
                termValue = dataHiddenValTerm.get(new Long(levelIdValue
                        .toString()));
                benefitLevelVO.setBenefitTerm(termValue.toString());
                termCodeValue = dataHiddenValTermCode.get(new Long(levelIdValue
                        .toString()));
                benefitLevelVO.setBenefitTermCode(termCodeValue.toString());
// Change made for term display Start
                List benefitTerms = new ArrayList();
                StringTokenizer tokenizer = new StringTokenizer(termCodeValue.toString().trim(), ",");
                while(tokenizer.hasMoreTokens()){
                    String termCodeDesc = tokenizer.nextToken();
                    StringTokenizer termCodeTokenizer = new StringTokenizer(termCodeDesc, "~");
                    if(termCodeTokenizer.countTokens() == 2){
                        BenefitTermVO benefitTermVO = new BenefitTermVO();
                        benefitTermVO.setBenefitTermCode(termCodeTokenizer.nextToken());
                        benefitTermVO.setBenefitTerm(termCodeTokenizer.nextToken());
                        benefitTerms.add(benefitTermVO);
                    }
                }
                benefitLevelVO.setBenefitTerms(benefitTerms);
// End**    
                
                qualifierValue = dataHiddenValQualifier.get(new Long(
                        levelIdValue.toString()));
                benefitLevelVO.setBenefitQualifier(qualifierValue.toString());
                qualifierCodeValue = dataHiddenValQualifierCode.get(new Long(
                        levelIdValue.toString()));
                benefitLevelVO.setBenefitQualifierCode(qualifierCodeValue
                        .toString());
                
// Change made for qualifier display Start Change: Aggregate Qualifier
                List benefitQualifiers = new ArrayList();
                StringTokenizer qualTokenizer = new StringTokenizer(qualifierCodeValue.toString().trim(),",");
                while(qualTokenizer.hasMoreTokens()){
                    String qualCodeDesc = qualTokenizer.nextToken();
                    StringTokenizer qualCodeTokenizer = new StringTokenizer(qualCodeDesc,"~");
                    if(qualCodeTokenizer.countTokens() == 2){
                        BenefitQualifierVO benefitQualVO = new BenefitQualifierVO();
                        benefitQualVO.setBenefitQualifierCode(qualCodeTokenizer.nextToken());
                        benefitQualVO.setBenefitQualifier(qualCodeTokenizer.nextToken());
                        benefitQualifiers.add(benefitQualVO);
                    }
                }
                benefitLevelVO.setBenefitQualifiers(benefitQualifiers);
//End qual display 
                String description = null; 
                
                //Start : Change for Description including if the frequency value is changed
                //Null check for description,frequency,term,qualifier objects
                if((null != oldDescValue) && (null != oldFrequencyValue) && (null != qualifierValue) && (null != termValue) && isNumber && (0 != newFreq)){
                	//Converts the old description value to string and Sets the old description value to a string
	                String oldDescription = oldDescValue.toString().trim().toUpperCase();
	                //Converts the old description value to string and Sets the old description value to a string
	                String newDescription = descValue.toString().trim().toUpperCase();
	                //Checks if the oldDescription is equal to new Description.
	                if(newDescription.equalsIgnoreCase(oldDescription)){
	                	//Converts the frequency value to string and Sets the frequency value to the string
		                String qualifierFreq = oldFrequencyValue.toString().trim().toUpperCase();
		                //Converts the qualifier value to string and Sets the qualifier value to the string
		                String qualify = qualifierValue.toString().trim().toUpperCase();
		                //Converts the term value to string and Sets the term value to the string.
		                String term = termValue.toString().trim().toUpperCase();
		                //For Aggregate qualifier
		                qualify = qualify.replaceAll(WebConstants.COMMA,WebConstants.EMPTY_STRING);
		                //For Aggregate term
		                term = term.replaceAll(WebConstants.COMMA,WebConstants.EMPTY_STRING);		                               
		                String changeDesc;
		                if(newFreq != oldFreq){
			                //Checks if the frequency value is 1
			                if(qualifierFreq.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)){			                	
			                	changeDesc  = term+WebConstants.PER_STRING+qualify;
			                }else{
			                	changeDesc  = term+WebConstants.PER_STRING+qualifierFreq+WebConstants.SPACE_STRING+qualify;
			                }
			                if(changeDesc.length() > 32){
			                	changeDesc = changeDesc.substring(0,32).trim();			                	
			                }
		                	if(oldDescription.length() > 32){
		                		oldDescription = oldDescription.substring(0,32).trim();	
			                }
			                //Compares the old description and new description
			                if(oldDescription.equalsIgnoreCase(changeDesc)){			                		
				                	//qualifierFreq = frequencyValue.toString();
			                		qualifierFreq = newFreq+WebConstants.EMPTY_STRING;
				                	if(qualifierFreq.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)){			                	
					                	changeDesc  = term+WebConstants.PER_STRING+qualify;
					                }else{
					                	changeDesc  = term+WebConstants.PER_STRING+qualifierFreq+WebConstants.SPACE_STRING+qualify;
					                }
				                	if(changeDesc.length() > 32){
					                	changeDesc = changeDesc.substring(0,32).trim();
					                	isDescriptionTruncated = true;
					                }
				                	benefitLevelVO.setBenefitLevelDesc(changeDesc);			                	
			                }else{
			                	if(oldDescription.length() > 32){
			                		oldDescription = oldDescription.substring(0,32).trim();
			                		isDescriptionTruncated = true;			                	
				                }
			                	benefitLevelVO.setBenefitLevelDesc(oldDescription);
			                }
		                }else{
		                	if(newDescription.length() > 32){
		                		newDescription = newDescription.substring(0,32).trim();
		                		isDescriptionTruncated = true;
		                	}
		                	benefitLevelVO.setBenefitLevelDesc(newDescription);
		                }	
	                }else{
	                	if(newDescription.length() > 32){
	                		newDescription = newDescription.substring(0,32).trim();
	                		isDescriptionTruncated = true;
	                	}
	                	benefitLevelVO.setModified(true);
	                	benefitLevelVO.setBenefitLevelDesc(newDescription);
	                }	                
                }else{
                	description = descValue.toString().toUpperCase().trim();
                	if(description.length() > 32){
                		description = description.substring(0,32).trim();
                		isDescriptionTruncated = true;
                		isDescriptionTruncated = true;
                	}                	
                	benefitLevelVO.setBenefitLevelDesc(description);                	
                }
	            if(isDescriptionTruncated){
	            	benefitLevelVO.setModified(true);
					InformationalMessage informationalMessage = new InformationalMessage(WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_LENGTH);
					informationalMessage.setParameters(new String[] { benefitLevelVO.getBenefitLevelDesc()});
					messageList.add(informationalMessage);
                }
                //End : Change for Description including if the frequency value is changed
                dataTypeValue = dataHiddenValDataForLevel.get(new Long(
                        levelIdValue.toString()));
                benefitLevelVO.setDataTypeId((new Integer(dataTypeValue
                        .toString()).intValue()));
                
                benefitValue = dataHiddenValForBenefitValue.get(new Long(
                        levelIdValue.toString()));
                //Saving the values in teh database with capital letters.
                if (null != benefitValue && !"".equals(benefitValue))
                    benefitLevelVO.setBenefitValue((benefitValue.toString()).toUpperCase()
                            .trim());
                
                Set keysForLine = dataHiddenValLineId.keySet();
                Iterator keyIterLine = keysForLine.iterator();
                while (keyIterLine.hasNext()) {
                    keyLine = keyIterLine.next();
                    lineIdValue = dataHiddenValLineId.get(keyLine);
                    StringTokenizer tokenizerForLineId = new StringTokenizer(
                            lineIdValue.toString(), ":");
                    if (tokenizerForLineId.hasMoreTokens()) {
                        levelId = tokenizerForLineId.nextToken();
                        lineId = tokenizerForLineId.nextToken();
                    }
                    if (levelIdValue.equals(levelId)) {
                        BenefitLineVO benefitLineVO = new BenefitLineVO();
                        benefitLineVO.setBenefitLevelId((new Integer(levelId
                                .toString()).intValue()));
                        benefitLineVO.setBenefitLineId((new Integer(lineId
                                .toString()).intValue()));
                        //Changed for enhancement 
                        benefitLineVO.setBenefitDefenitionId(getBenefitDefintionId());
                        //Change ends
                        dataTypeValue = dataHiddenValData.get(new Long(lineId));
                        benefitLineVO.setDataTypeId((new Integer(dataTypeValue
                                .toString()).intValue()));
                        
                        //change for performace
                        Object oldDataTypeValue = hiddenDataTypeMap.get(new Long(lineId));
                        if(null != oldDataTypeValue && null != dataTypeValue && !(dataTypeValue.equals(oldDataTypeValue.toString())))
                        	benefitLineVO.setModified(true);
                        //end
                        
                        benefitValue = dataHiddenValBenefitValue.get(new Long(
                                lineId));
                        //Saving to make it in upper case.
                        benefitLineVO.setBenefitValue((benefitValue.toString().toUpperCase()
                                .trim()));
                        
                        //change for performace
                        Object oldBnftValue = hiddenBnftValMap.get(new Long(
                                lineId));
                        if(null != oldBnftValue && null != benefitValue && !(oldBnftValue.equals(benefitValue.toString())))
                        	benefitLineVO.setModified(true);
                        //end
                        
                        pvaValue = dataHiddenValPVA.get(new Long(lineId));
                        benefitLineVO.setPVA(pvaValue.toString());
                        pvaCodeValue = dataHiddenValPVACode
                                .get(new Long(lineId));
                        benefitLineVO.setPvaCode(pvaCodeValue.toString());
                        benefitLines.add(benefitLineVO);
                        // **Change**
                        referenceValueCode = dataHiddenValReferenceCode
                                .get(new Long(lineId));
                        List referenceList = WPDStringUtil
                                .getListFromTildaString(referenceValueCode
                                        .toString(), 2,2,2);
                        if (null != referenceList && referenceList.size() > 0
                                && null != referenceList.get(0)){
                            if(!"null".equals(referenceList.get(0).toString().trim()))
	                            benefitLineVO
	                                    .setReference(((String) referenceList
	                                            .get(0)).trim());
                        }
                        
                        
                        List referenceCodeList = WPDStringUtil
                        		.getListFromTildaString(referenceValueCode
                        		        .toString(),2,1,2);
	                    if(null != referenceCodeList && referenceCodeList.size() > 0
	                            && null != referenceCodeList.get(0)){
	                        if(!"null".equals(referenceCodeList.get(0).toString().trim()))
		                    	benefitLineVO.setReferenceCode(((String)
		                    	        referenceCodeList.get(0)).trim());
	                    }
	                    
	                    //change for performance
                        Object oldRefValue = 
                            hiddenValReferenceMap.get(new Long(lineId));
                        
                       
                        if(null == benefitLineVO.getReferenceCode()){
                            if(!oldRefValue.equals("")){
                            	benefitLineVO.setModified(true);
                            }
                        }else if(!(oldRefValue.equals(benefitLineVO.getReferenceCode()))){
                        	benefitLineVO.setModified(true);
                        }
                        //end
                        /*referenceValue = dataHiddenValReference.get(new Long(
                                lineId));
                        benefitLineVO.setReference(referenceValue.toString());*/
                        // **Change for including notes exist Start
                        notesExist = dataHiddenValNotesExist.get(new Long(
                                lineId));
                        benefitLineVO.setBnftLineNotesExist(notesExist
                                .toString());
                        // **Change for including notes exist End
                        // **End**
                    }
                }
                benefitLevelVO.setBenefitLines(benefitLines);
                benefitLevels.add(benefitLevelVO);
            }
        }
        //modified on 11 Dec 2007
        List newLevelList = new ArrayList();
        List newLineList = null;
        if(isSeqChanged){
            Iterator iterator = benefitLevels.iterator();
            //updatedQuestionList.clear();
            while(iterator.hasNext()){
                BenefitLevelVO benefitLevelVO = (BenefitLevelVO) iterator.next();
                benefitLevelVO.setModified(true); 
                newLineList = benefitLevelVO.getBenefitLines();
                if((null!=newLineList)&&(newLineList.size()>0)){
	                Iterator lineIterator = newLineList.iterator();
	                while(lineIterator.hasNext()){
	                    BenefitLineVO benefitLineVO = (BenefitLineVO) lineIterator.next();
	                    benefitLineVO.setModified(true); 
	                }
	                newLevelList.add(benefitLevelVO);
                }
            }
            benefitWrapperVO.setBenefitLevelsList(newLevelList);
        }else{
            benefitWrapperVO.setBenefitLevelsList(benefitLevels);
        }
        //modification ends
        return benefitWrapperVO;
    }


    /**
     * Method to delete any selected benefit line from the database.
     * 
     * @return
     */
    public String deleteBenefitLevel() {
        this.renderPanel = true;
        List messages = null;
        DeleteBenefitLevelRequest deleteBenefitLevelRequest = (DeleteBenefitLevelRequest) this
                .getServiceRequest(ServiceManager.DELETE_BENEFIT_LEVEL_REQUEST);
        deleteBenefitLevelRequest
                .setBenefitWrapperVO(getUpdatedListFromScreen());
        // modified for the performance issue on 12thDec 2007         
        
        List newLineList = null;
        List newLevelList = new ArrayList();
        if(null != deleteBenefitLevelRequest.getBenefitWrapperVO().getBenefitLevelsList()){
            Iterator iterator = deleteBenefitLevelRequest.getBenefitWrapperVO().getBenefitLevelsList().iterator();
            
            while(iterator.hasNext()){
                BenefitLevelVO benefitLevelVO = (BenefitLevelVO) iterator.next();
                benefitLevelVO.setModified(true); 
                newLineList = benefitLevelVO.getBenefitLines();
                if((null!=newLineList)&&(newLineList.size()>0)){
	                Iterator lineIterator = newLineList.iterator();
	                while(lineIterator.hasNext()){
	                    BenefitLineVO benefitLineVO = (BenefitLineVO) lineIterator.next();
	                    benefitLineVO.setModified(true); 
	                }
	                newLevelList.add(benefitLevelVO);
                }
            }
            
            deleteBenefitLevelRequest.getBenefitWrapperVO().setBenefitLevelsList(newLevelList);            
        }
        //modification ends
        int levelsAvailable = this.getDataHiddenValSeq().size();     
        if(levelsAvailable==1){
        	List termList =getSelectedTermList();
        	if (null != termList && !termList.isEmpty()) {
	        	SelectItem selectItem = (SelectItem)termList.get(0);	
	        	String defaultTerm = String.valueOf(selectItem.getLabel());
	        	if(defaultTerm.equals(this.getSelectedTerm())){
	        		selectItem =(SelectItem)termList.get(1);
	        		defaultTerm =String.valueOf(selectItem.getLabel());
	        	}
	        	this.setSelectedTerm(defaultTerm);
        	}
        }
        if(this.getSelectedTerm().equals("ALL THE TERMS"))
        	deleteBenefitLevelRequest.getBenefitWrapperVO().setSelectedBenefitTerm("%");
        else
        deleteBenefitLevelRequest.getBenefitWrapperVO().setSelectedBenefitTerm("%"+this.getSelectedTerm()+"%");
        
        deleteBenefitLevelRequest.setBenefitLevelVO(getDeletedBenefitLevelVO());
        DeleteBenefitLevelResponse deleteBenefitLevelResponse = new DeleteBenefitLevelResponse();
        deleteBenefitLevelResponse = (DeleteBenefitLevelResponse) this
                .executeService(deleteBenefitLevelRequest);
        if (null != deleteBenefitLevelResponse) {
            messages = deleteBenefitLevelResponse.getMessages();
            this.setBenefitWrapperVO(deleteBenefitLevelResponse
                    .getBenefitWrapperVO());
        }
        if (null != this.getBenefitWrapperVO()) {
            //this.searchAfterDelete();
        	registerSequence(this.getBenefitWrapperVO().getBenefitLevelsList());
        	this.setRenderPanel(true);
        } else
            this.setRenderPanel(false);
        addAllMessagesToRequest(messages);
        getRequest().setAttribute("RETAIN_Value", "");
        return "success";
    }

    /**
     * Method to delete any selected benefit line from the database.
     * 
     * @return
     */
    public String deleteBenefitLine() {
        this.renderPanel = true;
        List messages = null;
        DeleteBenefitLineRequest deleteBenefitLineRequest = (DeleteBenefitLineRequest) this
                .getServiceRequest(ServiceManager.DELETE_BENEFIT_LINE_REQUEST);
        deleteBenefitLineRequest
                .setBenefitWrapperVO(getUpdatedListFromScreen());
        
        int levelsAvailable = this.getDataHiddenValSeq().size();     
        if(levelsAvailable==1){
        	List termList =getSelectedTermList();
        	if (null != termList && !termList.isEmpty()) {
	        	SelectItem selectItem = (SelectItem)termList.get(0);	
	        	String defaultTerm = String.valueOf(selectItem.getLabel());
	        	if(defaultTerm.equals(this.getSelectedTerm())){
	        		selectItem =(SelectItem)termList.get(1);
	        		defaultTerm =String.valueOf(selectItem.getLabel());
	        	}
	        	this.setSelectedTerm(defaultTerm);
        	}
        }
        if(this.getSelectedTerm().equals("ALL THE TERMS"))
        	deleteBenefitLineRequest.getBenefitWrapperVO().setSelectedBenefitTerm("%");
        else
        	deleteBenefitLineRequest.getBenefitWrapperVO().setSelectedBenefitTerm("%"+this.getSelectedTerm()+"%");
        
        deleteBenefitLineRequest.setBenefiLineVO(getDeletedBenefitLineVO());
        DeleteBenefitLineResponse deleteBenefitLineResponse = new DeleteBenefitLineResponse();
        deleteBenefitLineResponse = (DeleteBenefitLineResponse) this
                .executeService(deleteBenefitLineRequest);
        if (null != deleteBenefitLineResponse) {
            messages = deleteBenefitLineResponse.getMessages();
            this.setBenefitWrapperVO(deleteBenefitLineResponse
                    .getBenefitWrapperVO());
        }
        if (null != this.getBenefitWrapperVO()) {
            //Changes For Performance Fix Start
            if (this.getBenefitWrapperVO().isDeleteFlag())
                //this.searchAfterDelete();
            	registerSequence(this.getBenefitWrapperVO().getBenefitLevelsList());
            	this.setRenderPanel(true);
            //Changes For Performance Fix End
        } else
            this.setRenderPanel(false);
        addAllMessagesToRequest(messages);
        getRequest().setAttribute("RETAIN_Value", "");
        return WebConstants.SUCCESS;
    }


    /*
     * This method returns the list eliminating the one selected for deletion
     */
    public void searchAfterDelete() {
        BenefitWrapperVO newBenefitWrapperVO = new BenefitWrapperVO();
        if (null != this.getBenefitWrapperVO().getBenefitLevelsList()) {
            registerSequence(this.getBenefitWrapperVO().getBenefitLevelsList());
            newBenefitWrapperVO = this.getBenefitWrapperVO();
            newBenefitWrapperVO.setBenefitDefinitionId(getBenefitDefintionId());
            newBenefitWrapperVO
                    .setBenefitIdentifier(getStandardBenefitSessionObject()
                            .getStandardBenefitName());
            newBenefitWrapperVO
                    .setMasterVersion(getStandardBenefitSessionObject()
                            .getStandardBenefitVersionNumber());
            newBenefitWrapperVO
                    .setStandardBenefitParentKey(getStandardBenefitSessionObject()
                            .getStandardBenefitParentKey());
            newBenefitWrapperVO
                    .setStandardBenefitKey(getStandardBenefitSessionObject()
                            .getStandardBenefitKey());
            newBenefitWrapperVO
                    .setMasterStatus(getStandardBenefitSessionObject()
                            .getStandardBenefitStatus());
            newBenefitWrapperVO
                    .setBusinessDomains(getStandardBenefitSessionObject()
                            .getBusinessDomains());
            //Changes For Performance Fix Start
            newBenefitWrapperVO.setDeleteFlag(true);
            //Changes For Performance Fix End
            SaveBenefitLevelResponse saveBenefitLevelResponse = this
                    .updateBenefitLevels(newBenefitWrapperVO,
                            newBenefitWrapperVO.getBenefitLevelsList());
            if (null != saveBenefitLevelResponse)
                this.setBenefitWrapperVO(saveBenefitLevelResponse
                        .getBenefitWrapperVO());
            if (null != this.getBenefitWrapperVO()) {
                if (null != this.getBenefitWrapperVO().getBenefitLevelsList()) {
                    registerSequence(this.getBenefitWrapperVO().getBenefitLevelsList());
                    this.setRenderPanel(true);
                } else
                    this.setRenderPanel(false);
            }
        }
    }


    /**
     * Method to render the title displayed above the results table in the page.
     */
    public HtmlPanelGrid getDisplayPanel() {
        HtmlPanelGrid displayPanel = new HtmlPanelGrid();
        if (this.renderPanel) {
            displayPanel.setCellpadding("0");
            displayPanel.setCellspacing("0");
            displayPanel.setWidth("100%");
            displayPanel.setColumns(8);
            displayPanel.setBorder(0);
            displayPanel.setStyle("height:20px");
            displayPanel.setStyleClass("dataTableHeader");
            displayPanel.setBgcolor("#cccccc");
            HtmlOutputText outputText = new HtmlOutputText();
            outputText.setValue("Associated Benefit Levels");
            HtmlSelectOneMenu htmlSelectOneMenu = new HtmlSelectOneMenu();
            htmlSelectOneMenu.setId("selectTerm");
            int key = 123;
            ValueBinding hideVal = FacesContext.getCurrentInstance()
            					.getApplication().createValueBinding(
                    "#{benefitLevelBackingBean.termHidden["
                            + key+ "]}");
            htmlSelectOneMenu.setValueBinding("value",hideVal);            
            htmlSelectOneMenu.setOnchange("return retrieveByTerm();");  
            if(null != this.getSelectedTerm())
            	htmlSelectOneMenu.setValue(this.getSelectedTerm());
            UISelectItems uiSelectItems = new UISelectItems();
            List termList = getSelectedTermList();
            if(null !=termList){
            	uiSelectItems.setValue(termList);
            }else{
                SelectItem selectItem = new SelectItem("ALL THE TERMS");
            	List list = new ArrayList(1);
            	list.add(selectItem);
            	uiSelectItems.setValue(list);
            }
            htmlSelectOneMenu.getChildren().add(uiSelectItems);
            displayPanel.getChildren().add(outputText);
            displayPanel.getChildren().add(htmlSelectOneMenu);
        }
        return displayPanel;
    }


    /**
     * @param displayPanel
     *            The displayPanel to set.
     */
    public void setDisplayPanel(HtmlPanelGrid displayPanel) {
        this.displayPanel = displayPanel;
    }


    /**
     * Method to render the header for the table in the page.
     */
    public HtmlPanelGrid getHeaderPanel() {
        HtmlPanelGrid headerPanel = new HtmlPanelGrid();
        if (this.renderPanel) {
            headerPanel.setCellpadding("1");
            headerPanel.setCellspacing("1");
            headerPanel.setWidth("100%");
            headerPanel.setColumns(9);
            headerPanel.setBgcolor("#ffffff");
            headerPanel.setBorder(0);
           // headerPanel.setStyleClass("dataTableColumnHeader");
            HtmlOutputText otxtType0 = new HtmlOutputText();
            HtmlOutputText otxtType1 = new HtmlOutputText();
            HtmlOutputText otxtType2 = new HtmlOutputText();
            HtmlOutputText otxtType3 = new HtmlOutputText();
            HtmlOutputText otxtType4 = new HtmlOutputText();
            HtmlOutputText otxtType5 = new HtmlOutputText();
            HtmlOutputText otxtType6 = new HtmlOutputText();
            HtmlOutputText otxtType7 = new HtmlOutputText();
            HtmlOutputText otxtType8 = new HtmlOutputText();

            otxtType0.setValue("Seq");
            otxtType0.setId("sequence");
            //otxtType0.setStyle("width:50");
           // otxtType0.setStyleClass("dataTableColumnHeader");
            
            otxtType1.setValue("Description");
            otxtType1.setId("Description");
            //otxtType1.setStyleClass("dataTableColumnHeader");

            otxtType3.setValue("Frequency - Qualifier");
            otxtType3.setId("qualifier");

            otxtType2.setValue("Term");
            otxtType2.setId("term");
            //otxtType2.setStyleClass("dataTableColumnHeader");

            otxtType4.setValue("PVA");
            otxtType4.setId("pva");
            //otxtType4.setStyleClass("dataTableColumnHeader");

            otxtType5.setValue("Format");
            otxtType5.setId("dataType");
           // otxtType5.setStyleClass("dataTableColumnHeader");

            otxtType6.setValue("Benefit Value");
            otxtType6.setId("benefitValue");
           // otxtType6.setStyleClass("dataTableColumnHeader");

            otxtType7.setValue("Reference");
            otxtType7.setId("reference");
            //otxtType7.setStyleClass("dataTableColumnHeader");

            otxtType8.setValue("Delete");
            otxtType8.setId("button");
            //otxtType8.setStyleClass("dataTableColumnHeader");

            headerPanel.setStyleClass("dataTableHeader");
            headerPanel.getChildren().add(otxtType0);
            headerPanel.getChildren().add(otxtType1);
            headerPanel.getChildren().add(otxtType2);
            headerPanel.getChildren().add(otxtType3);
            headerPanel.getChildren().add(otxtType4);
            headerPanel.getChildren().add(otxtType5);
            headerPanel.getChildren().add(otxtType6);
            headerPanel.getChildren().add(otxtType7);
            headerPanel.getChildren().add(otxtType8);
        }
        return headerPanel;
    }


    /**
     * @param headerPanel
     *            The headerPanel to set.
     */
    public void setHeaderPanel(HtmlPanelGrid headerPanel) {
        this.headerPanel = headerPanel;
    }


    /**
     * Method to display the list of benefit lines available for a particular
     * benefit definition.
     */
    public List getBenefitLevelsList() {
        List benefitLevelsListCreated = new ArrayList();
        // **Change**
        boolean termAggregate = this.isAggregateTerm();
        //Change: Aggregate Qualifier
        boolean qualifierAggregate = this.isAggregateQualifier();
        
        if (!termAggregate) {
            if(!qualifierAggregate){ //Change: Aggregate Qualifier
            // **End**
            if (null != this.getBenefitTermsList()) {
                for (int i = 0; i < this.getBenefitTermsList().size(); i++) {
                    if (null != this.getBenefitTermQualifiersList()) {
                        for (int j = 0; j < this.getBenefitTermQualifiersList()
                                .size(); j++) {
                            BenefitLevelVO benefitLevelVO = new BenefitLevelVO();
                            benefitLevelVO
                                    .setBenefitDefinitionId(getBenefitDefintionId());
                            benefitLevelVO.setBenefitTerm((String) this
                                    .getBenefitTermsList().get(i));
                            benefitLevelVO.setBenefitTermCode((String) this
                                    .getBenefitTermsCodeList().get(i));
                            benefitLevelVO.setBenefitQualifier((String) this
                                    .getBenefitTermQualifiersList().get(j));
                            benefitLevelVO
                                    .setBenefitQualifierCode((String) this
                                            .getBenefitTermQualifiersCodeList()
                                            .get(j));
                            benefitLevelVO.setBenefitLevelDesc(((String) this
                                    .getBenefitTermsList().get(i))
                                    + " "
                                    + ((String) this
                                            .getBenefitTermQualifiersList()
                                            .get(j)));
                            List benefitLines = new ArrayList();
                            if (null != this.getProviderArrangementsList()) {
                                for (int k = 0; k < this
                                        .getProviderArrangementsList().size(); k++) {
                                    BenefitLineVO benefitLineVO = new BenefitLineVO();
                                    benefitLineVO.setDataTypeId(0);
                                    benefitLineVO.setPVA((String) this
                                            .getProviderArrangementsList().get(
                                                    k));
                                    benefitLineVO.setPvaCode((String) this
                                            .getProviderArrangementsCodeList()
                                            .get(k));
                                    benefitLines.add(benefitLineVO);
                                }
                            }
                            benefitLevelVO.setBenefitLines(benefitLines);
                            // **Change**
                            // get the benfit term and id and add to the list
                            List benefitTerms = new ArrayList();
                            if (null != this.getBenefitTermsCodeList().get(i)) {
                                BenefitTermVO benefitTermVO = new BenefitTermVO();
                                benefitTermVO.setBenefitTerm(this
                                        .getBenefitTermsList().get(i)
                                        .toString());
                                benefitTermVO.setBenefitTermCode(this
                                        .getBenefitTermsCodeList().get(i)
                                        .toString());
                                benefitTerms.add(benefitTermVO);
                            }
                            benefitLevelVO.setBenefitTerms(benefitTerms);
                            // **End**
                            
//Change: Aggregate Qualifier
                            // get the benefit qualifier and id and add to the list
                            List benefitQualifiers = new ArrayList();
                            if (null != this.getBenefitTermQualifiersCodeList().get(j)) {
                                BenefitQualifierVO benefitQualVO = new BenefitQualifierVO();
                                benefitQualVO.setBenefitQualifier(this.getBenefitTermQualifiersList()
                                        .get(j)
                                        .toString());
                                benefitQualVO.setBenefitQualifierCode(this
                                        .getBenefitTermQualifiersCodeList().get(j)
                                        .toString());
                                benefitQualifiers.add(benefitQualVO);
                            }
                            benefitLevelVO.setBenefitQualifiers(benefitQualifiers);
 // **End**
                            
                            benefitLevelsListCreated.add(benefitLevelVO);
                        }
                    }
                }
            }
            //Change: Aggregate Qualifier
           }else{
               if(null != this.getBenefitTermQualifiersList()){
                   
                  
                   for (int i = 0; i < this.getBenefitTermsList().size(); i++) {
                    String benefitLevelDesc = "";
                    List benefitQualifiers = new ArrayList();
                    int listSize = this.getBenefitTermQualifiersList().size();
                    if (null != this.getBenefitTermQualifiersList()) {
                        for (int j = 0; j < listSize; j++) {
                            benefitLevelDesc = benefitLevelDesc+" "
                                    + this.getBenefitTermQualifiersList().get(j) ;
                            BenefitQualifierVO benefitQualVO = new BenefitQualifierVO();
                            benefitQualVO.setBenefitQualifier(this
                                    .getBenefitTermQualifiersList().get(j).toString());
                            benefitQualVO.setBenefitQualifierCode(this
                                    .getBenefitTermQualifiersCodeList().get(j)
                                    .toString());
                            benefitQualifiers.add(benefitQualVO);
                        }
                    }
                    
                       BenefitLevelVO benefitLevelVO = new BenefitLevelVO();
                       benefitLevelVO
                               .setBenefitDefinitionId(getBenefitDefintionId());
                       benefitLevelVO.setBenefitTerm((String)this.getBenefitTermsList().get(i));
                       
                       benefitLevelVO.setBenefitTermCode((String) this
                               .getBenefitTermsCodeList().get(i));
                       
                       benefitLevelDesc = this.getBenefitTermsList().get(i) + benefitLevelDesc;
                       benefitLevelVO.setBenefitLevelDesc(benefitLevelDesc.trim());
                       benefitLevelVO.setBenefitQualifiers(benefitQualifiers);
                       benefitLevelVO.setBenefitQualifier((String) this
                               .getBenefitTermQualifiersList().get(0));
                       benefitLevelVO.setBenefitQualifierCode((String) this
                               .getBenefitTermQualifiersCodeList().get(0));
                       List benefitLines = new ArrayList();
                       if (null != this.getProviderArrangementsList()) {
                           for (int k = 0; k < this.getProviderArrangementsList()
                                   .size(); k++) {
                               BenefitLineVO benefitLineVO = new BenefitLineVO();
                               benefitLineVO.setDataTypeId(0);
                               benefitLineVO.setPVA((String) this
                                       .getProviderArrangementsList().get(k));
                               benefitLineVO.setPvaCode((String) this
                                       .getProviderArrangementsCodeList().get(k));
                               benefitLines.add(benefitLineVO);
                           }
                       }
                       benefitLevelVO.setBenefitLines(benefitLines);
                       
                       // **Change 11/17/07**
                       // get the benfit term and id and add to the list
                       List benefitTerms = new ArrayList();
                       if (null != this.getBenefitTermsCodeList().get(i)) {
                           BenefitTermVO benefitTermVO = new BenefitTermVO();
                           benefitTermVO.setBenefitTerm(this
                                   .getBenefitTermsList().get(i)
                                   .toString());
                           benefitTermVO.setBenefitTermCode(this
                                   .getBenefitTermsCodeList().get(i)
                                   .toString());
                           benefitTerms.add(benefitTermVO);
                       }
                       benefitLevelVO.setBenefitTerms(benefitTerms);
                       // **End**
                       
                       benefitLevelsListCreated.add(benefitLevelVO);
                   }
                   
                   
                   
                   
               }
           }
            // **Change**
        } else {
           
            if (null != this.getBenefitTermQualifiersList()) {
                
                
              if(!qualifierAggregate){
                for (int i = 0; i < this.getBenefitTermQualifiersList().size(); i++) {
                	String benefitDesc = "";
                    List benefitTerms = new ArrayList();
                    if (null != this.getBenefitTermsList()) {
                        for (int j = 0; j < this.getBenefitTermsList().size(); j++) {
                            benefitDesc = benefitDesc
                                    + this.getBenefitTermsList().get(j) + " ";
                            BenefitTermVO benefitTermVO = new BenefitTermVO();
                            benefitTermVO.setBenefitTerm(this
                                    .getBenefitTermsList().get(j).toString());
                            benefitTermVO.setBenefitTermCode(this
                                    .getBenefitTermsCodeList().get(j)
                                    .toString());
                            benefitTerms.add(benefitTermVO);
                        }
                    }
//                    Change: Aggregate Qualifier
                    BenefitLevelVO benefitLevelVO = new BenefitLevelVO();
                    benefitLevelVO
                            .setBenefitDefinitionId(getBenefitDefintionId());
                    benefitLevelVO.setBenefitQualifier((String) this
                            .getBenefitTermQualifiersList().get(i));
                    benefitLevelVO.setBenefitQualifierCode((String) this
                            .getBenefitTermQualifiersCodeList().get(i));
                    
//end                    
                    benefitDesc = benefitDesc
                            + this.getBenefitTermQualifiersList().get(i);
                    benefitLevelVO.setBenefitLevelDesc(benefitDesc.trim());
                    benefitLevelVO.setBenefitTerms(benefitTerms);
                    benefitLevelVO.setBenefitTerm((String) this
                            .getBenefitTermsList().get(0));
                    benefitLevelVO.setBenefitTermCode((String) this
                            .getBenefitTermsCodeList().get(0));
                    List benefitLines = new ArrayList();
                    if (null != this.getProviderArrangementsList()) {
                        for (int k = 0; k < this.getProviderArrangementsList()
                                .size(); k++) {
                            BenefitLineVO benefitLineVO = new BenefitLineVO();
                            benefitLineVO.setDataTypeId(0);
                            benefitLineVO.setPVA((String) this
                                    .getProviderArrangementsList().get(k));
                            benefitLineVO.setPvaCode((String) this
                                    .getProviderArrangementsCodeList().get(k));
                            benefitLines.add(benefitLineVO);
                        }
                    }
                    benefitLevelVO.setBenefitLines(benefitLines);
                    
//                  **Change Qualifier 11/17/07**
                    // get the benefit qualifier and id and add to the list
                    List benefitQualifiers = new ArrayList();
                    if (null != this.getBenefitTermQualifiersCodeList().get(i)) {
                        BenefitQualifierVO benefitQualVO = new BenefitQualifierVO();
                        benefitQualVO.setBenefitQualifier(this.getBenefitTermQualifiersList()
                                .get(i)
                                .toString());
                        benefitQualVO.setBenefitQualifierCode(this
                                .getBenefitTermQualifiersCodeList().get(i)
                                .toString());
                        benefitQualifiers.add(benefitQualVO);
                    }
                    benefitLevelVO.setBenefitQualifiers(benefitQualifiers);
// **End**
                    benefitLevelsListCreated.add(benefitLevelVO);
                }
            }else{ //**Change 11/13/2007
            	String benefitDesc = "";
                List benefitTerms = new ArrayList();
                if (null != this.getBenefitTermsList()) {
                    for (int j = 0; j < this.getBenefitTermsList().size(); j++) {
                        benefitDesc = benefitDesc
                                + this.getBenefitTermsList().get(j) + " ";
                        BenefitTermVO benefitTermVO = new BenefitTermVO();
                        benefitTermVO.setBenefitTerm(this
                                .getBenefitTermsList().get(j).toString());
                        benefitTermVO.setBenefitTermCode(this
                                .getBenefitTermsCodeList().get(j)
                                .toString());
                        benefitTerms.add(benefitTermVO);
                    }
                }
                BenefitLevelVO benefitLevelVO = new BenefitLevelVO();
                String benefitLevelDesc = " ";
                List benefitQualifiers = new ArrayList();
                int listSize = this.getBenefitTermQualifiersList().size();
                if (null != this.getBenefitTermQualifiersList()) {
                    for (int j = 0; j < listSize; j++) {
                        benefitLevelDesc = benefitLevelDesc
                                + this.getBenefitTermQualifiersList().get(j) + " ";
                        BenefitQualifierVO benefitQualVO = new BenefitQualifierVO();
                        benefitQualVO.setBenefitQualifier(this
                                .getBenefitTermQualifiersList().get(j).toString());
                        benefitQualVO.setBenefitQualifierCode(this
                                .getBenefitTermQualifiersCodeList().get(j)
                                .toString());
                        benefitQualifiers.add(benefitQualVO);
                    }
                }
                benefitDesc = benefitDesc + benefitLevelDesc;
                benefitLevelVO.setBenefitLevelDesc(benefitDesc.trim());
                benefitLevelVO.setBenefitTerms(benefitTerms);
                benefitLevelVO.setBenefitTerm((String) this
                        .getBenefitTermsList().get(0));
                benefitLevelVO.setBenefitTermCode((String) this
                        .getBenefitTermsCodeList().get(0));
                benefitLevelVO.setBenefitQualifiers(benefitQualifiers);
                benefitLevelVO.setBenefitQualifier((String) this
                        .getBenefitTermQualifiersList().get(0));
                benefitLevelVO.setBenefitQualifierCode((String) this
                        .getBenefitTermQualifiersCodeList().get(0));
                List benefitLines = new ArrayList();
                if (null != this.getProviderArrangementsList()) {
                    for (int k = 0; k < this.getProviderArrangementsList()
                            .size(); k++) {
                        BenefitLineVO benefitLineVO = new BenefitLineVO();
                        benefitLineVO.setDataTypeId(0);
                        benefitLineVO.setPVA((String) this
                                .getProviderArrangementsList().get(k));
                        benefitLineVO.setPvaCode((String) this
                                .getProviderArrangementsCodeList().get(k));
                        benefitLines.add(benefitLineVO);
                    }
                }
                benefitLevelVO.setBenefitLines(benefitLines);
                benefitLevelsListCreated.add(benefitLevelVO);
            }
        }
    }
//      **End** 
        this.setAggregateTerm(false);
        //** Change 11/19/07**
        this.setAggregateQualifier(false);
        //*end**
        
        
        this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
                + " ("
                + this.getStandardBenefitSessionObject()
                        .getStandardBenefitName() + ") >> Edit");
        return benefitLevelsListCreated;
    }


    /**
     * @param benefitLevelsList
     *            The benefitLevelsList to set.
     */
    public void setBenefitLevelsList(List benefitLevelsList) {
        this.benefitLevelsList = benefitLevelsList;
    }


    /**
     * @return Returns the dataHiddenValDesc.
     */
    public Map getDataHiddenValDesc() {
        return dataHiddenValDesc;
    }


    /**
     * @param dataHiddenValDesc
     *            The dataHiddenValDesc to set.
     */
    public void setDataHiddenValDesc(Map dataHiddenValDesc) {
        this.dataHiddenValDesc = dataHiddenValDesc;
    }


    /**
     * @return Returns the dataHiddenValData.
     */
    public Map getDataHiddenValData() {
        return dataHiddenValData;
    }


    /**
     * @param dataHiddenValData
     *            The dataHiddenValData to set.
     */
    public void setDataHiddenValData(Map dataHiddenValData) {
        this.dataHiddenValData = dataHiddenValData;
    }


    /**
     * @return Returns the dataHiddenValLevelId.
     */
    public Map getDataHiddenValLevelId() {
        return dataHiddenValLevelId;
    }


    /**
     * @param dataHiddenValLevelId
     *            The dataHiddenValLevelId to set.
     */
    public void setDataHiddenValLevelId(Map dataHiddenValLevelId) {
        this.dataHiddenValLevelId = dataHiddenValLevelId;
    }


    /**
     * @return Returns the dataHiddenValLineId.
     */
    public Map getDataHiddenValLineId() {
        return dataHiddenValLineId;
    }


    /**
     * @param dataHiddenValLineId
     *            The dataHiddenValLineId to set.
     */
    public void setDataHiddenValLineId(Map dataHiddenValLineId) {
        this.dataHiddenValLineId = dataHiddenValLineId;
    }


    /**
     * @return Returns the dataHiddenValReference.
     */
    public Map getDataHiddenValReference() {
        return dataHiddenValReference;
    }


    /**
     * @param dataHiddenValReference
     *            The dataHiddenValReference to set.
     */
    public void setDataHiddenValReference(Map dataHiddenValReference) {
        this.dataHiddenValReference = dataHiddenValReference;
    }


    /**
     * @return Returns the dataHiddenValBenefitValue.
     */
    public Map getDataHiddenValBenefitValue() {
        return dataHiddenValBenefitValue;
    }


    /**
     * @param dataHiddenValBenefitValue
     *            The dataHiddenValBenefitValue to set.
     */
    public void setDataHiddenValBenefitValue(Map dataHiddenValBenefitValue) {
        this.dataHiddenValBenefitValue = dataHiddenValBenefitValue;
    }


    public Map getDataHiddenValPVA() {
        return dataHiddenValPVA;
    }


    public void setDataHiddenValPVA(Map dataHiddenValPVA) {
        this.dataHiddenValPVA = dataHiddenValPVA;
    }


    public Map getDataHiddenValQualifier() {
        return dataHiddenValQualifier;
    }


    public void setDataHiddenValQualifier(Map dataHiddenValQualifier) {
        this.dataHiddenValQualifier = dataHiddenValQualifier;
    }


    public Map getDataHiddenValTerm() {
        return dataHiddenValTerm;
    }


    public void setDataHiddenValTerm(Map dataHiddenValTerm) {
        this.dataHiddenValTerm = dataHiddenValTerm;
    }


    public Map getDataHiddenValForBenefitValue() {
        return dataHiddenValForBenefitValue;
    }


    public void setDataHiddenValForBenefitValue(Map dataHiddenValForBenefitValue) {
        this.dataHiddenValForBenefitValue = dataHiddenValForBenefitValue;
    }


    public Map getDataHiddenValForLevel() {
        return dataHiddenValForLevel;
    }


    public void setDataHiddenValForLevel(Map dataHiddenValForLevel) {
        this.dataHiddenValForLevel = dataHiddenValForLevel;
    }


    public List getSequenceList() {
        return sequenceList;
    }


    public void setSequenceList(List sequenceList) {
        this.sequenceList = sequenceList;
    }


    protected StandardBenefitSessionObject getStandardBenefitSessionObject() {
        StandardBenefitSessionObject standardBenefitSessionObject = (StandardBenefitSessionObject) getSession()
                .getAttribute(STANDARD_BENEFIT_SESSION_KEY);

        if (standardBenefitSessionObject == null) {
            standardBenefitSessionObject = new StandardBenefitSessionObject();
            getSession().setAttribute(STANDARD_BENEFIT_SESSION_KEY,
                    standardBenefitSessionObject);
        }
        return standardBenefitSessionObject;
    }

    private List benftDefnList;


    /**
     * Returns the benftDefnList
     * 
     * @return List benftDefnList.
     */

    public List getBenftDefnList() {
        return benftDefnList;
    }


    /**
     * Sets the benftDefnList
     * 
     * @param benftDefnList.
     */

    public void setBenftDefnList(List benftDefnList) {
        this.benftDefnList = benftDefnList;
    }

    public String dummySearchBenefitLevels(String identifier,String scope){
    	if(scope.equals("tree")){
    		this.setSelectedTerm(null);
    		this.termHidden.clear();
    		getStandardBenefitSessionObject().setDateAvailable(false);
    		getStandardBenefitSessionObject().setBenefitLevelForViewVOList(null);
    		getStandardBenefitSessionObject().setBenefitWrapperVO(null);
    	}
    	return identifier;
    }
    
    public String searchBenefitLevels(String identifier,String scope) {  
    	TimeHandler th = new TimeHandler();
   	 	Logger.logInfo(th.startPerfLogging("U23057 : Save/Load Benefit Coverage", "Benefit Level Backing Bean", "searchBenefitLevels()"));
    	if(identifier.equals("benefitLevel")){
	    	if(null!= this.getSelectedTerm()){    	
	    		if(this.getSelectedTerm().equals("ALL THE TERMS")){
	    			this.setSelectedTermFromCombo("%");
	    		}else{
	    			this.setSelectedTermFromCombo("%"+this.getSelectedTerm()+"%");
	    		}
	    	}else{
	    		List termList =getSelectedTermList();
		        if(null != termList){
		        	SelectItem selectItem = (SelectItem)termList.get(0);	
		        	this.setSelectedTermFromCombo("%"+String.valueOf(selectItem.getLabel())+"%");
		    	}
	    	}
    	}else{
    		this.setSelectedTermFromCombo(null);
    	}
        SearchBenefitLevelResponse searchBenefitLevelResponse = null;
        SearchBenefitLevelRequest searchBenefitLevelRequest = (SearchBenefitLevelRequest) this
                .getServiceRequest(ServiceManager.SEARCH_BENEFIT_LEVEL_REQUEST);
        searchBenefitLevelRequest.getBenefitWrapperVO().setBenefitDefinitionId(
                getBenefitDefintionId());
        if(null!=getSelectedTermFromCombo()){
        	searchBenefitLevelRequest.getBenefitWrapperVO().setSelectedBenefitTerm(getSelectedTermFromCombo());
        }else{
        	searchBenefitLevelRequest.getBenefitWrapperVO().setSelectedBenefitTerm("%");
        }
        searchBenefitLevelResponse = (SearchBenefitLevelResponse) this
                .executeService(searchBenefitLevelRequest);
        if (null != searchBenefitLevelResponse) {
            this.setBenefitWrapperVO(searchBenefitLevelResponse
                    .getBenefitWrapperVO());
            if (null != this.getBenefitWrapperVO()) {
                this.setBenftDefnList(this.getBenefitWrapperVO()
                        .getBenefitLevelsList());
                if (null != this.getBenefitWrapperVO().getBenefitLevelsList()) {
                    registerSequence(this.getBenefitWrapperVO()
                            .getBenefitLevelsList());
// Change: Aggregate Qualifier                            
                    this.setBenftDefnList(searchBenefitLevelResponse
                            .getBenefitWrapperVO()
                            .getBenefitLevelsList());
                }
            }
//end            
            if ("benefitLevel".equals(identifier)) {
                searchBenefitLevelResponse.setMessages(null);
                this.getRequest().setAttribute("messages", null);
            }
        }
        getRequest().setAttribute("RETAIN_Value", "");
        Logger.logInfo(th.endPerfLogging());
        return identifier;
    }
    
    /**
     * Method to delete the selected levels and lines.
     * @return
     */
    public String deleteLevelsAndLines(){
    	
    	BenefitLevelForViewVO benefitLevelForViewVO = null;
    	List lineListForDelete=new ArrayList();
    	List levelListForDelete = new ArrayList();
    	messageList = new ArrayList();
    	if(benefitLevelViewList != null && benefitLevelViewList.size() > 0) {
    		for(int i = 0; i < benefitLevelViewList.size(); i++) {
    			benefitLevelForViewVO = (BenefitLevelForViewVO) benefitLevelViewList.get(i);
    			if(benefitLevelForViewVO.isBenefitLine() && benefitLevelForViewVO.isSelectForDelete()) {
    				lineListForDelete.add(new Integer(benefitLevelForViewVO.getBenefitLineId()).toString());
    			}
    			if(!benefitLevelForViewVO.isBenefitLine() && benefitLevelForViewVO.isSelectForDelete()) {
    				levelListForDelete.add(new Integer(benefitLevelForViewVO.getBenefitLevelId()).toString());
    			}
    		}
    	}
    	DeleteBenefitLevelRequest deleteBenefitLevelRequest = (DeleteBenefitLevelRequest) this.getServiceRequest
										(ServiceManager.DELETE_BENEFIT_LEVEL_REQUEST);
    	
    	if(null != levelListForDelete && !levelListForDelete.isEmpty()){
    		deleteBenefitLevelRequest.setBenefitLevels
				(WPDStringUtil.getTildaStringFromList(levelListForDelete));
    	}
    	
    	if(null != lineListForDelete && !lineListForDelete.isEmpty()){
    		deleteBenefitLevelRequest.setBenefitLines
				(WPDStringUtil.getTildaStringFromList(lineListForDelete));
    	}
        
        if(this.getSelectedTerm().equals("ALL THE TERMS"))
        	deleteBenefitLevelRequest.setSelectedTerm("%");
        else
        	deleteBenefitLevelRequest.setSelectedTerm("%" + this.getSelectedTerm() + "%");
        
        deleteBenefitLevelRequest.setBenefitWrapperVO(this.populateBenefitWrapperVO(benefitLevelViewList));
        
        deleteBenefitLevelRequest.setBenefitDefnId(getBenefitDefintionId());
  	
        List messages = null;
    	
        this.renderPanel = true;

        DeleteBenefitLevelResponse deleteBenefitLevelResponse = 
        		(DeleteBenefitLevelResponse) this
                	.executeService(deleteBenefitLevelRequest);
        
        if (null != deleteBenefitLevelResponse) {
            messages = deleteBenefitLevelResponse.getMessages();
            this.setBenefitWrapperVO
					(deleteBenefitLevelResponse.getBenefitWrapperVO());
            
        }
        
        if (null != this.getBenefitWrapperVO()) {
        	registerSequence(this.
        			getBenefitWrapperVO().getBenefitLevelsList());
        	this.setRenderPanel(true);
        	getStandardBenefitSessionObject().setDateAvailable(false);
        } else{
            this.setRenderPanel(false);
            getStandardBenefitSessionObject().setDateAvailable(false);
        }
        
        addAllMessagesToRequest(messages);
    	
    	// Return to the Level Page.

        if(null== this.benefitTerm || "".equals(this.benefitTerm) ||
        		null== this.benefitTermQualifier || "".equals(this.benefitTermQualifier) ||
				null== this.providerArrangement || "".equals(this.providerArrangement) )
        			getRequest().setAttribute("RETAIN_Value", "");
    	return "success";    	
    }
    
    private DeleteBenefitLevelRequest getDeleteBenefitLevelRequest(){
    	
    	
    	// Variable Declarations
    	DeleteBenefitLevelRequest request = null;
    	List levelsToDeleted = null;
    	List linesToDeleted = null;
    	String selectedTerm = null;
    	
    	// Get the selected levels from the map.
    	levelsToDeleted = getSelectedLevelsForDelete();
    	
    	// Get the selected lines from the map.
    	linesToDeleted = getSelectedLinesForDelete(levelsToDeleted);
    	
    	// Set the selected term
    	getSelectedTermForDelete(levelsToDeleted);
    	
    	// get the request
    	request = (DeleteBenefitLevelRequest) this.getServiceRequest
							(ServiceManager.DELETE_BENEFIT_LEVEL_REQUEST);
    	
    	// set the required datas to the request
    	if(null != levelsToDeleted && !levelsToDeleted.isEmpty()){
    		request.setBenefitLevels
				(WPDStringUtil.getTildaStringFromList(levelsToDeleted));
    	}
    	
    	if(null != linesToDeleted && !linesToDeleted.isEmpty()){
    		request.setBenefitLines
				(WPDStringUtil.getTildaStringFromList(linesToDeleted));
    	}
        
        if(this.getSelectedTerm().equals("ALL THE TERMS"))
        	request.setSelectedTerm("%");
        else
        	request.setSelectedTerm("%" + this.getSelectedTerm() + "%");
        
        request.setBenefitWrapperVO(getUpdatedListFromScreen());
        
        request.setBenefitDefnId(getBenefitDefintionId());
    	
    	// Return the request
    	return request;
    }
    
    /**
     * Method to get the selected levels.
     * @return
     */
    private List getSelectedLevelsForDelete(){
    	// variable declarations
    	List levelsToDeleted = null;
    	Set levelIdKeySet = null;
    	Iterator levelIdIterator = null;
    	Object levelIdKey = null;
    	Boolean isChecked = null;
    	
    	if(null != this.getLevelDeleteMap()){
    		// get the key set from the map
    		levelIdKeySet = this.getLevelDeleteMap().keySet();
    		// Iterate the key set
    		levelIdIterator = levelIdKeySet.iterator();
    		levelsToDeleted = new ArrayList();
    		while(levelIdIterator.hasNext()){
    			// get the key
    			levelIdKey = levelIdIterator.next();
    			// get the value whether it is checked or not.
    			isChecked = (Boolean) this.getLevelDeleteMap().get(levelIdKey);
    			if(isChecked.booleanValue()){
    				// add the level id to the list
    				levelsToDeleted.add(levelIdKey.toString());
    			}
    		}
    	}
    	
    	// return the selected levels to be deleted.
    	return levelsToDeleted;
    }
    
    /**
     * Method to get the selected lines.
     * @return
     */
    private List getSelectedLinesForDelete(List levelsToDeleted){
    	// varaible declarations
    	List linesToDeleted = null;
    	Set lineIdKeySetSet =  null;
    	Iterator lineIdIterator =  null;
    	Object lineIdKey = null;
    	String levelId = null;
    	String lineId = null;
    	StringTokenizer tokenizer = null;
    	String tokens = null;
    	int i = 0;
    	Boolean isChecked = null;
    	
    	if(null != this.getLineDeleteMap()){
    		// get the key set from the map
    		lineIdKeySetSet = this.getLineDeleteMap().keySet();
    		// iterate the key set
    		lineIdIterator = lineIdKeySetSet.iterator();
    		linesToDeleted = new ArrayList();
    		while(lineIdIterator.hasNext()){
    			// get the key
    			lineIdKey = lineIdIterator.next();
    			// split the lineid and level id
    			tokenizer = new StringTokenizer(lineIdKey.toString(), ".");
    			i = 0;
    			while(tokenizer.hasMoreTokens()){
    				tokens = tokenizer.nextToken();
    				if(i == 0){
    					// get the level id
    					levelId = tokens;
    				}else if(i == 1){
    					// get the line id
    					lineId = tokens;
    				}
    				i++;
    			}
    			// if level id is not in the levelsDeleteList then 
    			// include that line else neglect that line
    			if( null != levelsToDeleted && !levelsToDeleted.isEmpty()){
    				if(!levelsToDeleted.contains(levelId)){
    					// get the value of the key
    					isChecked = (Boolean) 
								this.getLineDeleteMap().get(lineIdKey);
    					if(isChecked.booleanValue()){
    						// add the line id to the list.
    						linesToDeleted.add(lineId);
    					}
    				}
    			}else{
    				// get the value of the key
					isChecked = (Boolean) 
							this.getLineDeleteMap().get(lineIdKey);
					if(isChecked.booleanValue()){
						// add the line id to the list.
						linesToDeleted.add(lineId);
					}
    			}
    		}
    	}
    	
    	// return the lines to be deleted.
    	return linesToDeleted;
    }
    
    /**
     * Method to get the selected term.
     * @return
     */
    private void getSelectedTermForDelete(List levelsToDeleted){
    	// Variable Declarations.  	
    	int levelsAvailable = 0;
    	int numOfLevelsToDelete = 0;
    	List termList = null;
    	SelectItem selectItem = null;
    	String defaultTerm = null;
    	
    	// get the total number of levels at present
    	levelsAvailable = this.getDataHiddenValSeq().size();
    	
    	// get the total number of levels to be deleted
    	if(null != levelsToDeleted && !levelsToDeleted.isEmpty()){
    		numOfLevelsToDelete = levelsToDeleted.size();
    	}
    	
    	// if all the levels are getting deleted then set the selected term as default.
        if(levelsAvailable == numOfLevelsToDelete){
        	termList = getSelectedTermList();
        	if((null != termList)&&(termList.size()>0)){
	        	selectItem = (SelectItem)termList.get(0);	
	        	defaultTerm = String.valueOf(selectItem.getLabel());
	        	if(defaultTerm.equals(this.getSelectedTerm())){
	        		selectItem =(SelectItem)termList.get(1);
	        		defaultTerm = String.valueOf(selectItem.getLabel());
	        	}
	        	this.setSelectedTerm(defaultTerm);
        	}
        }
    }

    /**
     * For navigating from Benefit Administration tab
     */
    public String loadBenefitLevelFromBenefitAdministrationTab() {
        this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
                + " ("
                + this.getStandardBenefitSessionObject()
                        .getStandardBenefitName() + ") >> Edit");
        String identifier = "benefitLevel";
        getStandardBenefitSessionObject().setDateAvailable(false);
        getStandardBenefitSessionObject().setBenefitLevelForViewVOList(null);
        return this.searchBenefitLevels(identifier,"page");
    }


    /**
     * For navigating from Benefit Administration View tab
     */
    public String loadBenefitLevelFromBenefitAdministrationViewTab() {
        this.setBreadCrumbText(WebConstants.BENEFIT_BREADCRUMB
                + " ("
                + this.getStandardBenefitSessionObject()
                        .getStandardBenefitName() + ") >> View");
        String identifier = "benefitLevelView";
        return this.searchBenefitLevels(identifier,"page");
    }


    /**
     *  
     */
    private int getBenefitDefintionId() {
        int benefitDefinitionId = 0;
        if (null != this.getSession().getAttribute(
                WebConstants.SESSION_BNFT_DEFN_ID)
                && !"".equals(this.getSession().getAttribute(
                        WebConstants.SESSION_BNFT_DEFN_ID))) {
            benefitDefinitionId = Integer.parseInt((String) (this.getSession()
                    .getAttribute(WebConstants.SESSION_BNFT_DEFN_ID)));
        }
        return benefitDefinitionId;
    }


    /**
     * @return Returns the pvasList.
     */
    public List getPvasList() {
       
        return pvasList;
    }


    /**
     * @param pvasList
     *            The pvasList to set.
     */
    public void setPvasList(List pvasList) {
        this.pvasList = pvasList;
    }


    /**
     * @return Returns the termQualifiersList.
     */
    public List getTermQualifiersList() {
       
        return termQualifiersList;
    }


    /**
     * @param termQualifiersList
     *            The termQualifiersList to set.
     */
    public void setTermQualifiersList(List termQualifiersList) {
        this.termQualifiersList = termQualifiersList;
    }


    /**
     * @return Returns the termResultList.
     */
    public List getTermResultList() {
        return termResultList;
    }


    /**
     * @param termResultList
     *            The termResultList to set.
     */
    public void setTermResultList(List termResultList) {
        this.termResultList = termResultList;
    }


    /**
     * @return Returns the benefitTermQualifiersCodeList.
     */
    public List getBenefitTermQualifiersCodeList() {
        return benefitTermQualifiersCodeList;
    }


    /**
     * @param benefitTermQualifiersCodeList
     *            The benefitTermQualifiersCodeList to set.
     */
    public void setBenefitTermQualifiersCodeList(
            List benefitTermQualifiersCodeList) {
        this.benefitTermQualifiersCodeList = benefitTermQualifiersCodeList;
    }


    /**
     * @return Returns the benefitTermsCodeList.
     */
    public List getBenefitTermsCodeList() {
        return benefitTermsCodeList;
    }


    /**
     * @param benefitTermsCodeList
     *            The benefitTermsCodeList to set.
     */
    public void setBenefitTermsCodeList(List benefitTermsCodeList) {
        this.benefitTermsCodeList = benefitTermsCodeList;
    }


    /**
     * @return Returns the providerArrangementsCodeList.
     */
    public List getProviderArrangementsCodeList() {
        return providerArrangementsCodeList;
    }


    /**
     * @param providerArrangementsCodeList
     *            The providerArrangementsCodeList to set.
     */
    public void setProviderArrangementsCodeList(
            List providerArrangementsCodeList) {
        this.providerArrangementsCodeList = providerArrangementsCodeList;
    }


    /**
     * @return Returns the dataHiddenValPVACode.
     */
    public Map getDataHiddenValPVACode() {
        return dataHiddenValPVACode;
    }


    /**
     * @param dataHiddenValPVACode
     *            The dataHiddenValPVACode to set.
     */
    public void setDataHiddenValPVACode(Map dataHiddenValPVACode) {
        this.dataHiddenValPVACode = dataHiddenValPVACode;
    }


    /**
     * @return Returns the dataHiddenValQualifierCode.
     */
    public Map getDataHiddenValQualifierCode() {
        return dataHiddenValQualifierCode;
    }


    /**
     * @param dataHiddenValQualifierCode
     *            The dataHiddenValQualifierCode to set.
     */
    public void setDataHiddenValQualifierCode(Map dataHiddenValQualifierCode) {
        this.dataHiddenValQualifierCode = dataHiddenValQualifierCode;
    }


    /**
     * @return Returns the dataHiddenValTermCode.
     */
    public Map getDataHiddenValTermCode() {
        return dataHiddenValTermCode;
    }


    /**
     * @param dataHiddenValTermCode
     *            The dataHiddenValTermCode to set.
     */
    public void setDataHiddenValTermCode(Map dataHiddenValTermCode) {
        this.dataHiddenValTermCode = dataHiddenValTermCode;
    }


    /**
     * 
     * @param benefitLevels
     */
    private void registerSequence(List benefitLevels) {
        SequenceUtil sequenceUtil = new SequenceUtil();
        sequenceUtil.registerObjects(benefitLevels, "benefitLevelId",
                "benefitLevelSeq");
    }


    /**
     * @return Returns the dataHiddenValReferenceCode.
     */
    public Map getDataHiddenValReferenceCode() {
        return dataHiddenValReferenceCode;
    }


    /**
     * @param dataHiddenValReferenceCode
     *            The dataHiddenValReferenceCode to set.
     */
    public void setDataHiddenValReferenceCode(Map dataHiddenValReferenceCode) {
        this.dataHiddenValReferenceCode = dataHiddenValReferenceCode;
    }


    /**
     * @return Returns the aggregateTerm.
     */
    public boolean isAggregateTerm() {
        return aggregateTerm;
    }


    /**
     * @param aggregateTerm
     *            The aggregateTerm to set.
     */
    public void setAggregateTerm(boolean aggregateTerm) {
        this.aggregateTerm = aggregateTerm;
    }


    /**
     * Returns the displayViewPanel
     * 
     * @return HtmlPanelGrid displayViewPanel.
     */

    public HtmlPanelGrid getDisplayViewPanel() {
        HtmlPanelGrid displayViewPanel = new HtmlPanelGrid();
        if (this.renderPanel) {
            displayViewPanel.setCellpadding("0");
            displayViewPanel.setCellspacing("0");
            displayViewPanel.setWidth("100%");
            displayViewPanel.setColumns(9);
            displayViewPanel.setBorder(0);
            displayViewPanel.setStyle("outputText");
            displayViewPanel.setStyleClass("dataTableHeader");
            displayViewPanel.setBgcolor("#cccccc");
            HtmlOutputText outputText = new HtmlOutputText();
            outputText.setValue("Associated Benefit Levels");
            displayViewPanel.getChildren().add(outputText);
        }
        return displayViewPanel;
    }


    /**
     * Sets the displayViewPanel
     * 
     * @param displayViewPanel.
     */

    public void setDisplayViewPanel(HtmlPanelGrid displayViewPanel) {
        this.displayViewPanel = displayViewPanel;
    }


    /**
     * Returns the dataHiddenValNotesExist
     * 
     * @return Map dataHiddenValNotesExist.
     */

    public Map getDataHiddenValNotesExist() {
        return dataHiddenValNotesExist;
    }


    /**
     * Sets the dataHiddenValNotesExist
     * 
     * @param dataHiddenValNotesExist.
     */

    public void setDataHiddenValNotesExist(Map dataHiddenValNotesExist) {
        this.dataHiddenValNotesExist = dataHiddenValNotesExist;
    }
//Change: Aggregate Qualifier   
    
/**
 * Returns the aggregateQualifier
 * @return boolean aggregateQualifier.
 */
public boolean isAggregateQualifier() {
    return aggregateQualifier;
}
/**
 * Sets the aggregateQualifier
 * @param aggregateQualifier.
 */
public void setAggregateQualifier(boolean aggregateQualifier) {
    this.aggregateQualifier = aggregateQualifier;
}
//end

//change for performance

/**
 * @return Returns the hiddenBnftValMap.
 */
public HashMap getHiddenBnftValMap() {
	return hiddenBnftValMap;
}
/**
 * @param hiddenBnftValMap The hiddenBnftValMap to set.
 */
public void setHiddenBnftValMap(HashMap hiddenBnftValMap) {
	this.hiddenBnftValMap = hiddenBnftValMap;
}
/**
 * @return Returns the hiddenDataTypeMap.
 */
public HashMap getHiddenDataTypeMap() {
	return hiddenDataTypeMap;
}
/**
 * @param hiddenDataTypeMap The hiddenDataTypeMap to set.
 */
public void setHiddenDataTypeMap(HashMap hiddenDataTypeMap) {
	this.hiddenDataTypeMap = hiddenDataTypeMap;
}
/**
* @return Returns the hiddenDescMap.
*/
public HashMap getHiddenDescMap() {
	return hiddenDescMap;
}
/**
* @param hiddenDescMap The hiddenDescMap to set.
*/
public void setHiddenDescMap(HashMap hiddenDescMap) {
	this.hiddenDescMap = hiddenDescMap;
}
/**
 * @return Returns the hiddenSequenceMap.
 */
public HashMap getHiddenSequenceMap() {
	return hiddenSequenceMap;
}
/**
 * @param hiddenSequenceMap The hiddenSequenceMap to set.
 */
public void setHiddenSequenceMap(HashMap hiddenSequenceMap) {
	this.hiddenSequenceMap = hiddenSequenceMap;
}
//end

	/**
	 * @return Returns the termHidden.
	 */
	public Map getTermHidden() {
		return termHidden;
	}
	/**
	 * @param termHidden The termHidden to set.
	 */
	public void setTermHidden(Map termHidden) {
		this.termHidden = termHidden;
	}
	/**
	 * @return Returns the selectedTermFromCombo.
	 */
	public String getSelectedTermFromCombo() {
		return selectedTermFromCombo;
	}
	/**
	 * @param selectedTermFromCombo The selectedTermFromCombo to set.
	 */
	public void setSelectedTermFromCombo(String selectedTermFromCombo) {
		this.selectedTermFromCombo = selectedTermFromCombo;
	}
	/**
	 * @return Returns the selectedTerm.
	 */
	public String getSelectedTerm() {
		return selectedTerm;
	}
	/**
	 * @param selectedTerm The selectedTerm to set.
	 */
	public void setSelectedTerm(String selectedTerm) {
		this.selectedTerm = selectedTerm;
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
	/**
	 * @return Returns the loadBenefitLevelView.
	 */
	public String getLoadBenefitLevelView() {
		this.loadBenefitLevelView = loadBenefitLevelFromBenefitAdministrationViewTab();
		return loadBenefitLevelView;
	}
	/**
	 * @param loadBenefitLevelView The loadBenefitLevelView to set.
	 */
	public void setLoadBenefitLevelView(String loadBenefitLevelView) {
		this.loadBenefitLevelView = loadBenefitLevelView;
	}
	/**
	 * @return Returns the levelDeleteMap.
	 */
	public HashMap getLevelDeleteMap() {
		return levelDeleteMap;
	}
	/**
	 * @param levelDeleteMap The levelDeleteMap to set.
	 */
	public void setLevelDeleteMap(HashMap levelDeleteMap) {
		this.levelDeleteMap = levelDeleteMap;
	}
	/**
	 * @return Returns the lineDeleteMap.
	 */
	public HashMap getLineDeleteMap() {
		return lineDeleteMap;
	}
	/**
	 * @param lineDeleteMap The lineDeleteMap to set.
	 */
	public void setLineDeleteMap(HashMap lineDeleteMap) {
		this.lineDeleteMap = lineDeleteMap;
	}
	
	// method to remove the duplicate error messages.
	
	public boolean removeDuplicateErrorMessage(ErrorMessage errorMessage ) {
		
		int count;
		 for(int msgCount=0;msgCount<validationMessages.size();msgCount++) {
        	ErrorMessage temp = (ErrorMessage)validationMessages.get(msgCount);
        	count = 0;
       	if(temp.getId().equals(errorMessage.getId())) {
       		String tempArray[] = temp.getParameters();
       		String errArray[] = errorMessage.getParameters();
       		for(int c=0;c<tempArray.length;c++) {
       			if(tempArray[c].equals(errArray[c]))
       				count++;
           		}
       		if(count == temp.getParameters().length) {
       			return true;
       		}
        	}
        }
		 return false;
	}
	/**
	 * @return Returns the numOfLevels.
	 */
	public int getNumOfLevels() {
		return numOfLevels;
	}
	/**
	 * @param numOfLevels The numOfLevels to set.
	 */
	public void setNumOfLevels(int numOfLevels) {
		this.numOfLevels = numOfLevels;
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
	 * @return Returns the txtData.
	 */
	public String getTxtData() {
		return txtData;
	}
	/**
	 * @param txtData The txtData to set.
	 */
	public void setTxtData(String txtData) {
		this.txtData = txtData;
	}
    /**
     * @return Returns the addButtonInvoked.
     */
    public String getAddButtonInvoked() {
        return addButtonInvoked;
    }
    /**
     * @param addButtonInvoked The addButtonInvoked to set.
     */
    public void setAddButtonInvoked(String addButtonInvoked) {
        this.addButtonInvoked = addButtonInvoked;
    }
	/**
	 * @return Returns the dataHiddenValFreq.
	 */
	public Map getDataHiddenValFreq() {
		return dataHiddenValFreq;
	}
	/**
	 * @param dataHiddenValFreq The dataHiddenValFreq to set.
	 */
	public void setDataHiddenValFreq(Map dataHiddenValFreq) {
		this.dataHiddenValFreq = dataHiddenValFreq;
	}
	/**
	 * @return Returns the hiddenFreqMap.
	 */
	public HashMap getHiddenFreqMap() {
		return hiddenFreqMap;
	}
	/**
	 * @param hiddenFreqMap The hiddenFreqMap to set.
	 */
	public void setHiddenFreqMap(HashMap hiddenFreqMap) {
		this.hiddenFreqMap = hiddenFreqMap;
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

	private List benefitLevelViewList;
	
	/**
	 * @return Returns the benefitLevelViewList.
	 */
	public List getBenefitLevelViewList() {
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23057 : Benefit Save/Load","BenefitLeve;BackingBean","getBenefitLevelViewList()"));
		StandardBenefitSessionObject sessionObj = getStandardBenefitSessionObject();
		if(!sessionObj.isDateAvailable()){
				BenefitWrapperVO benefitWrapperVO = this.getBenefitWrapperVO();
				if(benefitWrapperVO == null){
			    	 this.searchBenefitLevels("benefitLevel","page");
			    	 benefitWrapperVO = this.getBenefitWrapperVO();
			    	 sessionObj.setBenefitWrapperVO(benefitWrapperVO);
			    	 benefitLevelViewList = this.populateBenefitLevelForViewVO(benefitWrapperVO);
			    	 sessionObj.setDateAvailable(true);
			    	 sessionObj.setBenefitLevelForViewVOList(benefitLevelViewList);
			    } else {
			    	benefitLevelViewList = this.populateBenefitLevelForViewVO(benefitWrapperVO);
			    	 sessionObj.setDateAvailable(true);
			    	 sessionObj.setBenefitWrapperVO(benefitWrapperVO);
			    	 sessionObj.setBenefitLevelForViewVOList(benefitLevelViewList);
			    }
		} else {
			benefitLevelViewList = sessionObj.getBenefitLevelForViewVOList();
		}
		if(dateTypeList == null) {
	    	dateTypeList = getDataTypeList();
	    }
	    Logger.logInfo(th.endPerfLogging());
	    return benefitLevelViewList;
	}
	/**
	 * @param benefitLevelViewList The benefitLevelViewList to set.
	 */
	public void setBenefitLevelViewList(List benefitLevelViewList) {
		this.benefitLevelViewList = benefitLevelViewList;
	}
	private List populateBenefitLevelForViewVO(BenefitWrapperVO benefitWrapperVO) {
		if(benefitWrapperVO == null) {
			return null;
		}
		BenefitLineVO benefitLineVO = null;
        BenefitLevelVO benefitLevelVO = null;
        boolean renderNotesAttachmentImage =false;
		List benefitLevelViewList = new ArrayList();
		List benefitLevelsList = benefitWrapperVO.getBenefitLevelsList();
		if(benefitLevelsList != null && benefitLevelsList.size() >0) {
			this.numOfLevels = benefitLevelsList.size();
			String benefitType = this
            .getStandardBenefitSessionObject()
            .getBenefitType();
    
			if (null != benefitType&& !"".equals(benefitType)) {
				if (benefitType.equals(WebConstants.STD_TYPE)) {
					renderNotesAttachmentImage = true;
					mandate = false;
				} else if (benefitType.equals(WebConstants.MNDT_TYPE)) {
					renderNotesAttachmentImage = false;
					mandate = true;
				}
			}
			int couter = 0;
			BenefitLevelForViewVO benefitLevelForViewVO = null;
			StringBuffer noteURLBuffer = null;
			StringBuffer refPopupURLBuffer = null;
			
			 for (int i = 0; i < benefitLevelsList.size(); i++) {
			 	 benefitLevelVO = (BenefitLevelVO) benefitLevelsList.get(i);
			 	 benefitLevelForViewVO = new BenefitLevelForViewVO();
			 	/**
			 	 * Set Wrapper object Values
			 	 */
			 	benefitLevelForViewVO.setStandardBenefitKey(benefitWrapperVO.getStandardBenefitKey());
			 	benefitLevelForViewVO.setMasterVersion(benefitWrapperVO.getMasterVersion());
			 	benefitLevelForViewVO.setBenefitIdentifier(benefitWrapperVO.getBenefitIdentifier());
				benefitLevelForViewVO.setMasterStatus(benefitWrapperVO.getMasterStatus());
				benefitLevelForViewVO.setBusinessDomains(benefitWrapperVO.getBusinessDomains());
				benefitLevelForViewVO.setSelectedBenefitTerm(benefitWrapperVO.getSelectedBenefitTerm());
			 	benefitLevelForViewVO.setDescriptionFlag(benefitWrapperVO.isDeleteFlag());
			 	benefitLevelForViewVO.setDescriptionValue(benefitWrapperVO.getDescriptionValue());
			 	benefitLevelForViewVO.setStandardBenefitParentKey(benefitWrapperVO.getStandardBenefitParentKey());
			 	benefitLevelForViewVO.setDeleteFlag(benefitWrapperVO.isDeleteFlag());
			 	benefitLevelForViewVO.setWrapperBenefitDefinitionId(benefitWrapperVO.getBenefitDefinitionId());
			 	 /**
			 	  * Set Velues from Benefit Levels to Display VO
			 	  */
			 	 benefitLevelForViewVO.setBenefitTerm(benefitLevelVO.getBenefitTerm());
			 	 benefitLevelForViewVO.setBenefitQualifier(benefitLevelVO.getBenefitQualifier());
				 //benefitLevelForViewVO.setBenefitQualifierCode(benefitLevelVO.getBenefitQualifierCode());
				 benefitLevelForViewVO.setBenefitQualifiers(benefitLevelVO.getBenefitQualifiers());
				 benefitLevelForViewVO.setBenefitDefinitionId(benefitLevelVO.getBenefitDefinitionId());
				 benefitLevelForViewVO.setFreequencyAsString(benefitLevelVO.getBenefitFrequency() != 0 ? 
				 		String.valueOf(benefitLevelVO.getBenefitFrequency()) : "");
				 if(benefitLevelVO.getBenefitFrequency() != 0){
				 	benefitLevelForViewVO.setOldFrequencyValue(benefitLevelVO.getBenefitFrequency());
				 }
				 benefitLevelForViewVO.setBenefitFrequency(benefitLevelVO.getBenefitFrequency());
				 benefitLevelForViewVO.setBenefitLevelDesc(benefitLevelVO.getBenefitLevelDesc());
				 benefitLevelForViewVO.setBenefitLevelId(benefitLevelVO.getBenefitLevelId());
				 benefitLevelForViewVO.setBenefitLevelSeq(benefitLevelVO.getBenefitLevelSeq());
				 benefitLevelForViewVO.setBenefitLines(benefitLevelVO.getBenefitLines());
				 //benefitLevelForViewVO.setBenefitTermCode(benefitLevelVO.getBenefitTermCode());
				 benefitLevelForViewVO.setBenefitTerms(benefitLevelVO.getBenefitTerms());
				 benefitLevelForViewVO.setBenefitValue(benefitLevelVO.getBenefitValue());
				 benefitLevelForViewVO.setBenefitTermsAsString(benefitLevelVO.getBenefitTermsAsString());
				 benefitLevelForViewVO.setBenefitQualifiersAsString(benefitLevelVO.getBenefitQualifiersAsString());
				 benefitLevelForViewVO.setDataTypeId(benefitLevelVO.getDataTypeId());
				 benefitLevelForViewVO.setFrequencyNumber(benefitLevelVO.isFrequencyNumber());
				 benefitLevelForViewVO.setFrequencyValueEmpty(benefitLevelVO.isFrequencyValueEmpty());
				 benefitLevelForViewVO.setReference(benefitLevelVO.getReference());
				 benefitLevelForViewVO.setReferenceCode(benefitLevelVO.getReferenceCode());
				 benefitLevelForViewVO.setBenefitLine(false);
				 benefitLevelForViewVO.setRenderNotesAttachmentImage(renderNotesAttachmentImage);
				 
				 String benefitTermCode = this.getBenefitTermCode(benefitLevelVO.getBenefitTerms());
				 String benefitQualCode = this.getBenefitQualifierCode(benefitLevelVO.getBenefitQualifiers());
				 
				 benefitLevelForViewVO.setBenefitTermCode(benefitTermCode);
				 benefitLevelForViewVO.setBenefitQualifierCode(benefitQualCode);
				 
				 noteURLBuffer = new StringBuffer();
					noteURLBuffer.append("notesAttachmentPopup('../standardBenefit/benefitLevelNotesAttachmentPopUp.jsp','");
					noteURLBuffer.append(benefitTermCode);
					noteURLBuffer.append("','");
					noteURLBuffer.append(benefitLevelForViewVO.getBenefitQualifierCode());
					noteURLBuffer.append("','");
					noteURLBuffer.append(benefitLevelForViewVO.getBenefitLevelId());
					noteURLBuffer.append("','");
					noteURLBuffer.append("benefitLevelForm:panelTable:"+couter+":attachNote");
					noteURLBuffer.append("');return false;");
					couter+=1;	
				benefitLevelForViewVO.setNotePopUpURl(noteURLBuffer.toString());	
				 
				
				benefitLevelViewList.add(benefitLevelForViewVO);
				 
				 List benefitLineList = benefitLevelVO.getBenefitLines();
				 if(benefitLineList != null && benefitLineList.size() > 0){
				 	
				 	benefitLevelForViewVO.setNumberOfLines(benefitLineList.size());
				 	
				 	for(int k = 0; k < benefitLineList.size(); k++) {
				 		
				 		benefitLineVO = (BenefitLineVO) benefitLineList.get(k);
				 		/**
				 		 * Set wrapper object values
				 		 */ 
					 	benefitLevelForViewVO = new BenefitLevelForViewVO();
					 	benefitLevelForViewVO.setStandardBenefitKey(benefitWrapperVO.getStandardBenefitKey());
					 	benefitLevelForViewVO.setMasterVersion(benefitWrapperVO.getMasterVersion());
					 	benefitLevelForViewVO.setBenefitIdentifier(benefitWrapperVO.getBenefitIdentifier());
						benefitLevelForViewVO.setMasterStatus(benefitWrapperVO.getMasterStatus());
						benefitLevelForViewVO.setBusinessDomains(benefitWrapperVO.getBusinessDomains());
						benefitLevelForViewVO.setSelectedBenefitTerm(benefitWrapperVO.getSelectedBenefitTerm());
					 	benefitLevelForViewVO.setDescriptionFlag(benefitWrapperVO.isDeleteFlag());
					 	benefitLevelForViewVO.setDescriptionValue(benefitWrapperVO.getDescriptionValue());
					 	benefitLevelForViewVO.setStandardBenefitParentKey(benefitWrapperVO.getStandardBenefitParentKey());
					 	benefitLevelForViewVO.setDeleteFlag(benefitWrapperVO.isDeleteFlag());
					 	benefitLevelForViewVO.setWrapperBenefitDefinitionId(benefitWrapperVO.getBenefitDefinitionId());
					 	
					 	/**
					 	  * Set Velues from Benefit Lines to Display VO
					 	  */
					 	benefitLevelForViewVO.setBenefitLineLevelId(benefitLineVO.getBenefitLevelId());
					 	benefitLevelForViewVO.setBenefitDefenitionId(benefitLineVO.getBenefitDefenitionId());
						benefitLevelForViewVO.setBenefitLine(true);
						benefitLevelForViewVO.setBenefitLineBenefitValue(benefitLineVO.getBenefitValue());
						benefitLevelForViewVO.setBenefitLineDataTypeId(benefitLineVO.getDataTypeId());
						benefitLevelForViewVO.setBenefitLineId(benefitLineVO.getBenefitLineId());
						benefitLevelForViewVO.setBenefitLineIsModified(benefitLineVO.isModified());
						benefitLevelForViewVO.setBenefitLineReference(benefitLineVO.getReference());
						benefitLevelForViewVO.setBenefitLineReferenceCode(benefitLineVO.getReferenceCode());
						benefitLevelForViewVO.setBnftLineNotesExist(benefitLineVO.getBnftLineNotesExist());
						benefitLevelForViewVO.setDataTypeName(benefitLineVO.getDataTypeName());
						benefitLevelForViewVO.setPVA(benefitLineVO.getPVA());
						benefitLevelForViewVO.setPvaCode(benefitLineVO.getPvaCode());
						benefitLevelForViewVO.setRenderNotesAttachmentImage(renderNotesAttachmentImage);
						benefitLevelForViewVO.setHiddenReference(benefitLevelForViewVO.getBenefitLineReferenceCode() + "~"+benefitLevelForViewVO.getBenefitLineReference());
						if (null != benefitLineVO.getBnftLineNotesExist()) {
							benefitLevelForViewVO.setNoteExist(benefitLineVO.getBnftLineNotesExist().toUpperCase().equals("Y"));
		                }
						noteURLBuffer = new StringBuffer();
						noteURLBuffer.append("notesAttachmentPopup('../standardBenefit/benefitLevelNotesAttachmentPopUp.jsp','");
						noteURLBuffer.append(benefitTermCode);
						noteURLBuffer.append("','");
						noteURLBuffer.append(benefitLevelForViewVO.getBenefitQualifierCode());
						noteURLBuffer.append("','");
						noteURLBuffer.append(benefitLevelForViewVO.getBenefitLineId());
						noteURLBuffer.append("','");
						noteURLBuffer.append("benefitLevelForm:panelTable:"+couter+":attachNote");
						noteURLBuffer.append("');return false;");
						//System.out.println("Note Url : " + noteURLBuffer.toString());
						
						benefitLevelForViewVO.setNotePopUpURl(noteURLBuffer.toString());	
						
						
						String termVal = "";
						if(benefitTermCode != null && !"".equalsIgnoreCase(benefitTermCode)){
							String termCodes[]=benefitTermCode.split(",");
							for(int l=0;l<termCodes.length;l++){

								if(termCodes[l].split("~").length==2)
									termVal+=termCodes[l].split("~")[0].trim();
								if(l!=termCodes.length-1)
									termVal+=",";
							}
							
						}
						String qualVal ="";
						if(benefitQualCode != null && !"".equalsIgnoreCase(benefitQualCode)){
							String qualCodes[]=benefitQualCode.split(",");
							for(int m=0;m<qualCodes.length;m++){

								if(qualCodes[m].split("~").length==2)
									qualVal+=qualCodes[m].split("~")[0].trim();
								if(m!=qualCodes.length-1)
									qualVal+=",";
							}
							
						}
						
						refPopupURLBuffer = new StringBuffer();
						refPopupURLBuffer.append("setDataType('" );
						refPopupURLBuffer.append("benefitLevelForm:panelTable:"+couter+":lineFormat");
						refPopupURLBuffer.append("');ewpdModalWindow_ewpd( '../popups/SearchSPSForLinesAndQuestions.jsp'+getUrl()+'?");
						refPopupURLBuffer.append("queryName=searchSPSForMapping&headerName=SPS Parameters&titleName=");
						refPopupURLBuffer.append("SPS Parameter Popup&titleName=SPS Parameter Popup&spsType=LINE&term="+termVal);
						refPopupURLBuffer.append("&qualifier=" + qualVal + "&pva=" + benefitLineVO.getPvaCode() + "&dataType='+document.getElementById('benefitLevelForm:hiddenDt').value+'&temp='+Math.random(),'");
						refPopupURLBuffer.append("benefitLevelForm:panelTable:"+couter+":reference");
						refPopupURLBuffer.append("','benefitLevelForm:panelTable:"+couter+":hiddenReferenceCode");
						refPopupURLBuffer.append("',2,2);return false;");
						benefitLevelForViewVO.setReferencePopUpUrl(refPopupURLBuffer.toString());
						couter+=1;
						benefitLevelViewList.add(benefitLevelForViewVO);
						
				 	}
				 }
			 }
		}
		return benefitLevelViewList;
	}
	
	private BenefitLevelForViewVO getBenefitLevelForViewVO(List benefitLevelForViewVOs, int index) {
		try{
			return (BenefitLevelForViewVO)benefitLevelForViewVOs.get(index);
		} catch (Exception e){
			return new BenefitLevelForViewVO();
		}
	}
	
	private BenefitWrapperVO populateBenefitWrapperVO(List benefitLevelForViewVOs){
		BenefitWrapperVO seesionWrapper = getStandardBenefitSessionObject().getBenefitWrapperVO();
		if(benefitLevelForViewVOs != null && benefitLevelForViewVOs.size() >0 ){
			BenefitWrapperVO benefitWrapperVO = new BenefitWrapperVO();
			BenefitLevelForViewVO benefitLevelForViewVO = null;
			BenefitLevelForViewVO benefitLevelForViewVO2 = null;
			BenefitLevelForViewVO sessionBenefitLevelForViewVO = null;
			List referenceCodeList = null;
			List sessionBenefitLevelForViewVOs = this.populateBenefitLevelForViewVO(seesionWrapper);
			BenefitLineVO benefitLineVO = null;
			BenefitLevelVO benefitLevelVO = null;
			List benefitLinelist = null;
			boolean isDescriptionTruncated = false;
			List benefitLevelList = benefitLevelList = new ArrayList();
			int freequency = 0;
			for(int i = 0;i < benefitLevelForViewVOs.size(); i++) {
				
				benefitLevelForViewVO = new BenefitLevelForViewVO();
				benefitLevelForViewVO = (BenefitLevelForViewVO) benefitLevelForViewVOs.get(i);
				if(i == 0){
					benefitWrapperVO.setBenefitDefinitionId(benefitLevelForViewVO.getWrapperBenefitDefinitionId());
					benefitWrapperVO.setBenefitIdentifier(benefitLevelForViewVO.getBenefitIdentifier());
					benefitWrapperVO.setBusinessDomains(benefitLevelForViewVO.getBusinessDomains());
					benefitWrapperVO.setDeleteFlag(benefitLevelForViewVO.isDeleteFlag());
					benefitWrapperVO.setDescriptionFlag(benefitLevelForViewVO.isDescriptionFlag());
					benefitWrapperVO.setDescriptionValue(benefitLevelForViewVO.getDescriptionValue());
					benefitWrapperVO.setMasterStatus(benefitLevelForViewVO.getMasterStatus());
					benefitWrapperVO.setMasterVersion(benefitLevelForViewVO.getMasterVersion());
					benefitWrapperVO.setSelectedBenefitTerm(benefitLevelForViewVO.getSelectedBenefitTerm());
					benefitWrapperVO.setStandardBenefitKey(benefitLevelForViewVO.getStandardBenefitKey());
					benefitWrapperVO.setStandardBenefitParentKey(benefitLevelForViewVO.getStandardBenefitParentKey());
				}
				benefitLevelVO = new BenefitLevelVO();
				if(!benefitLevelForViewVO.isBenefitLine()){
					sessionBenefitLevelForViewVO = this.getBenefitLevelForViewVO(sessionBenefitLevelForViewVOs, i);
					/*if(benefitLevelForViewVO.getBenefitDefinitionId() != sessionBenefitLevelForViewVO.getBenefitDefinitionId()){
						benefitLevelVO.setModified(true);
					}*/
					benefitLevelVO.setBenefitDefinitionId(benefitLevelForViewVO.getBenefitDefinitionId());
					if(benefitLevelForViewVO.getFreequencyAsString() != null && benefitLevelForViewVO.getFreequencyAsString().trim().length() >0){
						benefitLevelVO.setFrequencyValueEmpty(true);
						benefitLevelVO.setFrequencyNumber(WPDStringUtil.isNumber(benefitLevelForViewVO.getFreequencyAsString().toString()));
						freequency = Integer.parseInt(benefitLevelForViewVO.getFreequencyAsString());
						benefitLevelVO.setBenefitFrequency(freequency);
					} else {
						benefitLevelVO.setFrequencyValueEmpty(false);
					}
					if(!StringUtil.compateString(benefitLevelForViewVO.getFreequencyAsString(),sessionBenefitLevelForViewVO.getFreequencyAsString())){
						benefitLevelVO.setModified(true);
					}
					
					/*if(benefitLevelForViewVO.getBenefitLevelId() != sessionBenefitLevelForViewVO.getBenefitLevelId()){
						benefitLevelVO.setModified(true);
					}*/
					benefitLevelVO.setBenefitLevelId(benefitLevelForViewVO.getBenefitLevelId());
					
					if(benefitLevelForViewVO.getBenefitLevelSeq() != sessionBenefitLevelForViewVO.getBenefitLevelSeq()){
						benefitLevelVO.setModified(true);
					}
					benefitLevelVO.setBenefitLevelSeq(benefitLevelForViewVO.getBenefitLevelSeq());
					
					/*if(benefitLevelForViewVO.getBenefitLines() != sessionBenefitLevelForViewVO.getBenefitLines()){
						benefitLevelVO.setModified(true);
					}
					benefitLevelVO.setBenefitLines(benefitLevelForViewVO.getBenefitLines());*/
					
					/*if(benefitLevelForViewVO.getBenefitQualifier() != sessionBenefitLevelForViewVO.getBenefitQualifier()){
						benefitLevelVO.setModified(true);
					}*/
					benefitLevelVO.setBenefitQualifier(benefitLevelForViewVO.getBenefitQualifier());
					
					/*if(benefitLevelForViewVO.getBenefitQualifiers() != sessionBenefitLevelForViewVO.getBenefitQualifiers()){
						benefitLevelVO.setModified(true);
					}*/
					benefitLevelVO.setBenefitQualifiers(benefitLevelForViewVO.getBenefitQualifiers());
					
					benefitLevelVO.setBenefitQualifierCode(benefitLevelForViewVO.getBenefitQualifierCode());
					/*if(benefitLevelForViewVO.getBenefitTerm() != sessionBenefitLevelForViewVO.getBenefitTerm()){
						benefitLevelVO.setModified(true);
					}*/
					benefitLevelVO.setBenefitTerm(benefitLevelForViewVO.getBenefitTerm());
					
					/*if(benefitLevelForViewVO.getBenefitTermCode() != sessionBenefitLevelForViewVO.getBenefitTermCode()){
						benefitLevelVO.setModified(true);
					}*/
					benefitLevelVO.setBenefitTermCode(benefitLevelForViewVO.getBenefitTermCode());
					
					/*if(benefitLevelForViewVO.getBenefitTerms() != sessionBenefitLevelForViewVO.getBenefitTerms()){
						benefitLevelVO.setModified(true);
					}*/
					benefitLevelVO.setBenefitTerms(benefitLevelForViewVO.getBenefitTerms());
					
					if(!StringUtil.compateString(benefitLevelForViewVO.getBenefitValue(),sessionBenefitLevelForViewVO.getBenefitValue())){
						benefitLevelVO.setModified(true);
					}
					benefitLevelVO.setBenefitValue(benefitLevelForViewVO.getBenefitValue());
					
					if(!StringUtil.compateString(benefitLevelForViewVO.getDataTypeAsString(),sessionBenefitLevelForViewVO.getDataTypeAsString())){
						benefitLevelVO.setModified(true);
					}
					benefitLevelVO.setDataTypeId(Integer.parseInt(benefitLevelForViewVO.getDataTypeAsString()));
										
					if(benefitLevelForViewVO.getOldFrequencyValue() != sessionBenefitLevelForViewVO.getOldFrequencyValue()){
						benefitLevelVO.setModified(true);
					}
					//benefitLevelVO.setModified(benefitLevelForViewVO.isModified());
					benefitLevelVO.setOldFrequencyValue(benefitLevelForViewVO.getOldFrequencyValue());
					
					if(!StringUtil.compateString(benefitLevelForViewVO.getReferenceCode(),sessionBenefitLevelForViewVO.getReferenceCode())){
						benefitLevelVO.setModified(true);
					}
					benefitLevelVO.setReferenceCode(benefitLevelForViewVO.getReferenceCode());
					
					if(!StringUtil.compateString(benefitLevelForViewVO.getReference(),sessionBenefitLevelForViewVO.getReference())){
						benefitLevelVO.setModified(true);
					}
					benefitLevelVO.setReference(benefitLevelForViewVO.getReference());
					String newDescription = "";
					if(sessionBenefitLevelForViewVO.getBenefitLevelDesc() != null &&
							sessionBenefitLevelForViewVO.getBenefitFrequency() != 0 &&
							benefitLevelForViewVO.getBenefitQualifier() != null &&
							benefitLevelForViewVO.getBenefitTerm() != null && 
							benefitLevelVO.getBenefitFrequency() != 0){
						newDescription = this.descriptionCreater(sessionBenefitLevelForViewVO.getBenefitFrequency(), 
								benefitLevelVO.getBenefitFrequency(),benefitLevelForViewVO.getBenefitQualifier(),
								benefitLevelForViewVO.getBenefitTerm(),benefitLevelForViewVO.getBenefitLevelDesc(), 
								sessionBenefitLevelForViewVO.getBenefitLevelDesc()
								);
						if(newDescription.length() > 32) {
							newDescription = newDescription.substring(0, 32).trim().toUpperCase();
							isDescriptionTruncated = true;
				            benefitLevelVO.setModified(true);
								
						} 
						benefitLevelVO.setBenefitLevelDesc(newDescription);
					} else {
						newDescription = benefitLevelForViewVO.getBenefitLevelDesc();
						if(newDescription.length() > 32) {
							benefitLevelVO.setBenefitLevelDesc(newDescription.substring(0, 32).trim().toUpperCase());
							isDescriptionTruncated = true;
						} else {
							benefitLevelVO.setBenefitLevelDesc(benefitLevelForViewVO.getBenefitLevelDesc().toUpperCase());
						}
					}
					
					if(!StringUtil.compateString(newDescription,sessionBenefitLevelForViewVO.getBenefitLevelDesc())){
						benefitLevelVO.setModified(true);
					}
					
					if(isDescriptionTruncated) {
						InformationalMessage informationalMessage = new InformationalMessage(WebConstants.MANDATE_BENEFIT_LEVEL_DESCRIPTION_LENGTH);
						informationalMessage.setParameters(new String[] {benefitLevelVO.getBenefitLevelDesc()});
						messageList.add(informationalMessage);
					}
					
					benefitLinelist = new ArrayList();
					for(int j = 0; j< benefitLevelForViewVOs.size(); j++){
						benefitLevelForViewVO2 = new BenefitLevelForViewVO();
						benefitLevelForViewVO2 = (BenefitLevelForViewVO)benefitLevelForViewVOs.get(j);
						if(benefitLevelForViewVO2.isBenefitLine()
								&& benefitLevelVO.getBenefitLevelId() == benefitLevelForViewVO2.getBenefitLineLevelId()){
							
							sessionBenefitLevelForViewVO = this.getBenefitLevelForViewVO(sessionBenefitLevelForViewVOs, j);
							
							benefitLineVO = new BenefitLineVO();
							if(benefitLevelForViewVO2.getBenefitDefenitionId() != sessionBenefitLevelForViewVO.getBenefitDefenitionId()){
								benefitLineVO.setModified(true);
							}
							benefitLineVO.setBenefitDefenitionId(benefitLevelForViewVO2.getBenefitDefenitionId());
							
							if(benefitLevelForViewVO2.getBenefitLevelId() != sessionBenefitLevelForViewVO.getBenefitLevelId()){
								benefitLineVO.setModified(true);
							}
							benefitLineVO.setBenefitLevelId(benefitLevelForViewVO2.getBenefitLevelId());
							
							if(benefitLevelForViewVO2.getBenefitLineId() != sessionBenefitLevelForViewVO.getBenefitLineId()){
								benefitLineVO.setModified(true);
							}
							benefitLineVO.setBenefitLineId(benefitLevelForViewVO2.getBenefitLineId());
							
							if(!StringUtil.compateString(benefitLevelForViewVO2.getBenefitLineBenefitValue(),sessionBenefitLevelForViewVO.getBenefitLineBenefitValue())){
								benefitLineVO.setModified(true);
							}
							benefitLineVO.setBenefitValue(benefitLevelForViewVO2.getBenefitLineBenefitValue());
							
							if(!StringUtil.compateString(benefitLevelForViewVO2.getBnftLineNotesExist(),sessionBenefitLevelForViewVO.getBnftLineNotesExist())){
								benefitLineVO.setModified(true);
							}
							benefitLineVO.setBnftLineNotesExist(benefitLevelForViewVO2.getBnftLineNotesExist());
							
							if(!StringUtil.compateString(benefitLevelForViewVO2.getBenefitLineDataTypeIdAsString(),sessionBenefitLevelForViewVO.getBenefitLineDataTypeIdAsString())){
								benefitLineVO.setModified(true);
							}
							benefitLineVO.setDataTypeId(Integer.parseInt(benefitLevelForViewVO2.getBenefitLineDataTypeIdAsString()));
							
							if(!StringUtil.compateString(benefitLevelForViewVO2.getDataTypeName(),sessionBenefitLevelForViewVO.getDataTypeName())){
								benefitLineVO.setModified(true);
							}
							benefitLineVO.setDataTypeName(benefitLevelForViewVO2.getDataTypeName());
							
							if(!StringUtil.compateString(benefitLevelForViewVO2.getPVA(),sessionBenefitLevelForViewVO.getPVA())){
								benefitLineVO.setModified(true);
							}
							benefitLineVO.setPVA(benefitLevelForViewVO2.getPVA());
							
							if(!StringUtil.compateString(benefitLevelForViewVO2.getPvaCode(),sessionBenefitLevelForViewVO.getPvaCode())){
								benefitLineVO.setModified(true);
							}
							benefitLineVO.setPvaCode(benefitLevelForViewVO2.getPvaCode());
							
							if(!StringUtil.compateString(benefitLevelForViewVO2.getBenefitLineReference(),sessionBenefitLevelForViewVO.getBenefitLineReference())){
								benefitLineVO.setModified(true);
							}
							benefitLineVO.setReference(benefitLevelForViewVO2.getBenefitLineReference());
							referenceCodeList = WPDStringUtil
		            		.getListFromTildaString(benefitLevelForViewVO2.getHiddenReference()
		            		        .toString(),2,1,2);
								if(null != referenceCodeList && referenceCodeList.size() > 0
											&& null != referenceCodeList.get(0)){
								if(!"null".equals(referenceCodeList.get(0).toString().trim()))
										benefitLineVO.setReferenceCode(((String)
													referenceCodeList.get(0)).trim());
								}
							benefitLinelist.add(benefitLineVO);
						} 
					}
					benefitLevelVO.setBenefitLines(benefitLinelist);
					benefitLevelList.add(benefitLevelVO);
				}
			}
			benefitWrapperVO.setBenefitLevelsList(benefitLevelList);
			
			benefitWrapperVO.setBenefitDefinitionId(getBenefitDefintionId());
	        benefitWrapperVO.setBenefitIdentifier(getStandardBenefitSessionObject()
	                .getStandardBenefitName());
	        benefitWrapperVO.setMasterVersion(getStandardBenefitSessionObject()
	                .getStandardBenefitVersionNumber());
	        benefitWrapperVO
	                .setStandardBenefitParentKey(getStandardBenefitSessionObject()
	                        .getStandardBenefitParentKey());
	        benefitWrapperVO
	                .setStandardBenefitKey(getStandardBenefitSessionObject()
	                        .getStandardBenefitKey());
	        benefitWrapperVO.setMasterStatus(getStandardBenefitSessionObject()
	                .getStandardBenefitStatus());
	        benefitWrapperVO.setBusinessDomains(getStandardBenefitSessionObject()
	                .getBusinessDomains());
			return benefitWrapperVO;
		} else {
			return null;
		}
		
	}
	
	private String getBenefitQualifierCode(List benefitQualifiers) {
		String benefitQualifierCode="";
		if (null != benefitQualifiers && !benefitQualifiers.isEmpty()) {
            for (int l = 0; l < benefitQualifiers.size(); l++) {
                BenefitQualifierVO benefitQualifierVO = (BenefitQualifierVO) benefitQualifiers
                        .get(l);
                benefitQualifierCode = benefitQualifierCode
							+benefitQualifierVO.getBenefitQualifierCode()
                			+"~"
							+benefitQualifierVO.getBenefitQualifier();
                                                
                if (l < (benefitQualifiers.size() - 1)) {
                	benefitQualifierCode = benefitQualifierCode + ",";
                }
            }
        }
		return benefitQualifierCode;
	}
	
	public String getBenefitTermCode(List benefitTerms) {
		StringBuffer termCodeBuffer = new StringBuffer();
		if (null != benefitTerms && !benefitTerms.isEmpty()) {
			BenefitTermVO benefitTermVO = null;
            for (int k = 0; k <benefitTerms.size(); k++) {
                benefitTermVO = (BenefitTermVO) benefitTerms.get(k);
                termCodeBuffer.append(benefitTermVO
    					.getBenefitTermCode());
                termCodeBuffer.append("~");
                termCodeBuffer.append(benefitTermVO.getBenefitTerm());
                if(k < benefitTerms.size()-1){
                	termCodeBuffer.append(",");
                }
            }
		}
		return termCodeBuffer.toString();
	}
	private String descriptionCreater(int oldFreequency, int newFreequency, String qualifier, String term, String newDesc, String oldDesc) {
		String qualFree = new Integer(oldFreequency).toString().trim().toUpperCase();
		String qualify = qualifier.toString().trim().toUpperCase();
		String termVal = term.toString().trim().toUpperCase();
		qualify = qualify.replaceAll(WebConstants.COMMA,WebConstants.SPACE_STRING).trim();
		termVal = termVal.replaceAll(WebConstants.COMMA,WebConstants.SPACE_STRING).trim();
		String changeDesc = "";
		String tempOld=oldDesc;
		if(oldFreequency != newFreequency) {
			if(qualFree.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)){
				changeDesc = termVal + WebConstants.PER_STRING + qualify;
			} else {
				changeDesc = termVal + WebConstants.PER_STRING + qualFree + WebConstants.SPACE_STRING + qualify;
			}
			if(changeDesc.length() > 32) {
				changeDesc = changeDesc.substring(0, 32).trim().toUpperCase();
			}
			if(tempOld.length() > 32) {
				tempOld = oldDesc.substring(0, 32).trim().toUpperCase();
			}
			if(tempOld.equalsIgnoreCase(changeDesc)) {
				qualFree = newFreequency + WebConstants.EMPTY_STRING;
				if(qualFree.equalsIgnoreCase(WebConstants.ACTION_BENEFIT)){
					changeDesc = termVal + WebConstants.PER_STRING + qualify;
				} else {
					changeDesc = termVal + WebConstants.PER_STRING + qualFree + WebConstants.SPACE_STRING + qualify;
				}
				return changeDesc;
			} else {
				return oldDesc;	
			}
		} else {
			return newDesc;
		}
	}

	
}

