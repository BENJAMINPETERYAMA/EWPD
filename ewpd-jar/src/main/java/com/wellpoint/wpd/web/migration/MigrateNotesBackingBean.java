/*
 * Created on Apr 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.migration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import com.wellpoint.wpd.business.migration.util.MigrationContractUtil;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.migration.bo.BenefitComponentBenefit;
import com.wellpoint.wpd.common.migration.bo.BenefitComponentNote;
import com.wellpoint.wpd.common.migration.bo.MigrateNotesBO;
import com.wellpoint.wpd.common.migration.bo.MigrationContract;
import com.wellpoint.wpd.common.migration.bo.MigrationDateSegment;
import com.wellpoint.wpd.common.migration.request.RetrieveBenefitDetailsRequest;
import com.wellpoint.wpd.common.migration.request.SaveMigrationNotesRequest;
import com.wellpoint.wpd.common.migration.response.RetrieveBenefitDetailsResponse;
import com.wellpoint.wpd.common.migration.response.SaveMigrationNotesResponse;
import com.wellpoint.wpd.common.migration.vo.MigrateNotesVO;
import com.wellpoint.wpd.common.migration.vo.MigrationContractSession;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;



/**
 * @author U13259
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MigrateNotesBackingBean extends MigrationBaseBackingBean{
	
    private String majorheadingId;
    private List minorheadingId;
    private String contractNotesFlag;
    private String benefitComponentNotesFlag;
    private List benefitNotesFlag;
    private List validationMessages;
//    private List benefitComponentList;
    private HtmlPanelGrid benefitHeadingPanel = new HtmlPanelGrid();
    private HtmlPanelGrid benefitPanel = new HtmlPanelGrid();
    private HashMap minorHeadingValueMap = new HashMap();
    private HashMap minorHeadingNotesFlagMap = new HashMap();
    private String contractName;
    private HashMap minorHeadingIdMap = new HashMap();
    private String benefitCompName;
    private List benefitList;
    private BenefitComponentNote benefitComponentNote ;
    private static String BREAD_CRUMB_TEXT_STEP8 = "Administration >> Contract Migration Wizard >> Migrate Notes (Step 8)";
    
    public MigrateNotesBackingBean() {
        super();
        this
                .setBreadCrumbTextLeft(BREAD_CRUMB_TEXT_STEP8);
        String contractId = this.getMigrationContractSession()
                .getMigrationContract().getContractId();
        String startDate = WPDStringUtil.convertDateToString(this
                .getMigrationContractSession().getStartDateEwpd());
        String endDate = WPDStringUtil.convertDateToString(this
                .getMigrationContractSession().getEndDate());
        this.setBreadCrumbTextRight(WebConstants.BREAD_CRUMB_CONTRACT
                + contractId + " (" + startDate + " - " + endDate + ")");
        getRequest().getSession().removeAttribute(WebConstants.MINOR_HEADING_FROM_SCREEN);
       
    }

	/**
	 * To get the heading panel of the listed Benefits
	 * @return Returns the benefitHeadingPanel.
	 */
	public HtmlPanelGrid getBenefitHeadingPanel() {
		HtmlPanelGrid headingPanel = new HtmlPanelGrid();
        headingPanel.setWidth("80%");
        headingPanel.setColumns(4);
        headingPanel.setBorder(0);
        headingPanel.setBgcolor("#cccccc");
        headingPanel.setCellpadding("3");
        headingPanel.setCellspacing("1");

        HtmlOutputText outputText1 = new HtmlOutputText();
        outputText1.setValue("Benefit");
        outputText1.setId("benefit");
        outputText1.setStyleClass("dataTableHeader");

        HtmlOutputText outputText2 = new HtmlOutputText();
        outputText2.setValue("  Minor Heading Id ");
        outputText2.setId("minorHeadingId");
        outputText2.setStyleClass("dataTableHeader");

        HtmlOutputText outputText3 = new HtmlOutputText();
        
        outputText3.setStyleClass("dataTableHeader");

        HtmlOutputText outputText4 = new HtmlOutputText();
        outputText4.setValue("  Notes");
        outputText4.setId("notes_benefit");
        outputText4.setStyleClass("dataTableHeader");

                
        headingPanel.getChildren().add(outputText1);
        headingPanel.getChildren().add(outputText2);
        headingPanel.getChildren().add(outputText3);
        headingPanel.getChildren().add(outputText4);

		return headingPanel;
	}
	/**
	 * @param benefitHeadingPanel The benefitHeadingPanel to set.
	 */
	public void setBenefitHeadingPanel(HtmlPanelGrid benefitHeadingPanel) {
		this.benefitHeadingPanel = benefitHeadingPanel;
	}
	
	/**
	 * This method load the details of contract, benefit component and benefit in the page
	 * @param benefitHeadingPanel The benefitHeadingPanel to set.
	 * returns string
	 */
	public String loadMigrationNotesPage() {
		
		RetrieveBenefitDetailsRequest benefitDetailsRequest = getRetrieveBenefitDetailsRequest();
		
		this.getBenefitComponentNotesInfo(benefitDetailsRequest);
		this.getContractInfo(benefitDetailsRequest);
		
		this.benefitList = getBenefitInfo(benefitDetailsRequest);

		return WebConstants.NOTES_MIGRATION_PAGE;
	}
	
	
	 
	/**
	 * This method gets the benefit details in the request
	 * @return RetrieveBenefitDetailsRequest
	 */
	private RetrieveBenefitDetailsRequest getRetrieveBenefitDetailsRequest() {
		RetrieveBenefitDetailsRequest benefitDetailsRequest = (RetrieveBenefitDetailsRequest)this.getServiceRequest(ServiceManager.RETRIEVE_BENEFIT_DETAILS_REQUEST);
		
		this.getMigrationContractSession().getNavigationInfo()
						.setUpdateLastAccessedPageOnly(true);
		
		MigrationContractSession migrationContractSession = getMigrationContractSession();
		
		MigrationContract migrationContract = migrationContractSession
				.getMigrationContract();
		
		benefitDetailsRequest
				.setMigrationContractSession(migrationContractSession);	
		benefitDetailsRequest.setMigratedDateSegmentId(this.getMigrationContractSession().getDateSegmentId());
		return benefitDetailsRequest;
	}
	/**
	 * This method gets the benefit compoennt details in the request
	 * @param benefitDetailsRequest
	 * returns List
	 * 
	 */
	private List getBenefitInfo(RetrieveBenefitDetailsRequest benefitDetailsRequest) {

		benefitDetailsRequest.setBenefitComponentId(this.getMigrationContractSession().getMigrationContract().getBenefitComponentId());

		benefitDetailsRequest.setContractSysId(this.getMigrationContractSession().getMigrationContract().getMigrationSystemId());
		benefitDetailsRequest.setAction(WebConstants.BENEFIT);
		
		RetrieveBenefitDetailsResponse retrieveBenefitDetailsResponse = (RetrieveBenefitDetailsResponse) this
    			.executeService(benefitDetailsRequest);
		return retrieveBenefitDetailsResponse.getList();
	}
	/**
	 * To get the details of the benefits in the panel
	 * @return the HtmlPanelGrid.
	 */
	public HtmlPanelGrid getBenefitPanel() {
        HtmlPanelGrid panel = new HtmlPanelGrid();
    	RetrieveBenefitDetailsRequest benefitDetailsRequest = getRetrieveBenefitDetailsRequest();
    	this.benefitList = getBenefitInfo(benefitDetailsRequest);
        if(null == this.benefitList || this.benefitList.size() == 0){
        	/*RetrieveBenefitDetailsRequest benefitDetailsRequest = getRetrieveBenefitDetailsRequest();
        	this.benefitList = getBenefitInfo(benefitDetailsRequest);*/
        }
        this.minorHeadingNotesFlagMap.clear();
        this.minorHeadingIdMap.clear();
        this.minorHeadingValueMap.clear();
        MigrateNotesBO migrateNotesBO = null;
        if (null != this.benefitList) {

            panel.setWidth("80%");
            panel.setColumns(4);
            panel.setBorder(0);
            panel.setBgcolor("#cccccc");
            panel.setCellpadding("3");
            panel.setCellspacing("1");
            
            HtmlInputHidden size = new HtmlInputHidden();
            size.setId("size");
            size.setValue(new Integer(this.benefitList.size()));
        	
            if (this.benefitList.size() > 0) {
                for (int i = 0; i < this.benefitList.size(); i++) {
                	migrateNotesBO = (MigrateNotesBO) this.benefitList
                            .get(i);
                	
                	//for getting the key field
                	HtmlInputHidden minorHeadingIdHidden = new HtmlInputHidden();
                	minorHeadingIdHidden.setId("benefitIdhidden" + i);
                	minorHeadingIdHidden.setValue(new Integer(migrateNotesBO.getBenefitId()));
                    ValueBinding valBindingForMinorHeadingIdHidden = FacesContext
		                    .getCurrentInstance().getApplication()
		                    .createValueBinding(
		                            "#{migrateNotesBackingBean.minorHeadingIdMap["
		                                    + migrateNotesBO.getBenefitId() + "]}");
                    minorHeadingIdHidden.setValueBinding("value",
                    		valBindingForMinorHeadingIdHidden);
                    
                    
                    
                    
                    //for getting the benefit name
                    HtmlOutputText outputText1 = new HtmlOutputText();
                    outputText1.setId("benefitName" + i);
                    outputText1.setValue(migrateNotesBO
                            .getBenefitName());            
                    
                    // for inputting space
                    HtmlOutputText outputText3 = new HtmlOutputText();
                    outputText3.setValue("");
                    outputText3.setId("space" + i);
                    
                    // for getting the minor heading id
                    HtmlInputText inputText1 = new HtmlInputText();
                    inputText1.setId("minorHeading" + i);
                    inputText1.setStyleClass("formInputFieldForReference");
                    inputText1.setValue(migrateNotesBO.getMinorheadingId());
                    inputText1.setReadonly(true);
                    inputText1.setStyle("align:center;");
                    
                    HtmlInputHidden inputTextHidden = new HtmlInputHidden();
                    inputTextHidden.setId("inputTextHidden" + i);
                    inputTextHidden.setValue(migrateNotesBO.getMinorheadingId());                 
                    
                    ValueBinding valBindingForMinorHeading = FacesContext
		                    .getCurrentInstance().getApplication()
		                    .createValueBinding(
		                            "#{migrateNotesBackingBean.minorHeadingValueMap["
		                                    + migrateNotesBO.getBenefitId() + "]}");
                    inputTextHidden.setValueBinding("value",
		            		valBindingForMinorHeading);
                    
                    

                    HtmlGraphicImage selectMinorHeading = new HtmlGraphicImage();
                    selectMinorHeading.setUrl("../../images/select.gif");
                    selectMinorHeading.setStyle("cursor:hand;");
                    selectMinorHeading.setId("selectImage" + i);
                    selectMinorHeading.setAlt("Select");
                    selectMinorHeading.setOnclick("getMinorHeading('" + i
                             + "');return false;");
                    
                    HtmlGraphicImage selectImage = new HtmlGraphicImage();
                    selectImage.setUrl("../../images/view.gif");
                    selectImage.setStyle("cursor:hand;");
                    selectImage.setId("viewImage" + i);
                    selectImage.setAlt("View");
                    selectImage.setOnclick("getMinorNotes(" + i
                            + ");return false;");


                    
                    HtmlSelectBooleanCheckbox noteCheckBox = new HtmlSelectBooleanCheckbox();
                    noteCheckBox.setId("noteCheckBox" + i);
                    noteCheckBox.setValue(migrateNotesBO.getMigrateNotesFlag());
                    noteCheckBox.setOnclick("checkMinorHeadingNotes('" + i + "');");
                    
                    
                    if(null!= migrateNotesBO.getMigrateNotesFlag() && migrateNotesBO.getMigrateNotesFlag().equalsIgnoreCase("Y")){
                    	noteCheckBox.setSelected(true);
                    }
                    else{
                    	noteCheckBox.setSelected(false);	
                    }
                    
//                    HtmlInputHidden noteCheckBoxHidden = new HtmlInputHidden();
//                    noteCheckBoxHidden.setId("noteCheckBoxHidden" + i);
//                    noteCheckBoxHidden.setValue(migrateNotesBO.getMigrateNotesFlag());
                    
                    
                    HtmlInputHidden noteCheckBoxHidden = new HtmlInputHidden();
                    noteCheckBoxHidden.setId("noteCheckBoxHidden" + i);
                    noteCheckBoxHidden.setValue(migrateNotesBO.getMigrateNotesFlag());
                    
                    ValueBinding valBindingForMinorHeadingNotes = FacesContext
		                    .getCurrentInstance().getApplication()
		                    .createValueBinding(
		                            "#{migrateNotesBackingBean.minorHeadingNotesFlagMap["
		                                    + migrateNotesBO.getBenefitId() + "]}");
                    noteCheckBoxHidden.setValueBinding("value",
		            		valBindingForMinorHeadingNotes);
                    
                    
                    HtmlOutputLabel minorHeadingDesc = new HtmlOutputLabel();
                    minorHeadingDesc.setId("minorHeadingDesc" + i);

                    HtmlOutputLabel emptyValue1 = new HtmlOutputLabel();
                    emptyValue1.setId("emptyValue1" + i);
                    
                    HtmlOutputLabel emptyValue2 = new HtmlOutputLabel();
                    emptyValue2.setId("emptyValue2" + i);

                    HtmlOutputLabel minorHeadingValue1 = new HtmlOutputLabel();
                    minorHeadingValue1.setId("value" + i);

                    minorHeadingDesc.getChildren().add(outputText1);
                    
                    emptyValue1.getChildren().add(outputText3);
                    emptyValue1.getChildren().add(selectMinorHeading);
                    
                    emptyValue2.getChildren().add(outputText3);
                    emptyValue2.getChildren().add(selectImage);
                    emptyValue2.getChildren().add(outputText3);
                    emptyValue2.getChildren().add(noteCheckBox);
                    emptyValue2.getChildren().add(noteCheckBoxHidden);                    
                    
                    minorHeadingValue1.getChildren().add(outputText3);
                    minorHeadingValue1.getChildren().add(inputText1);
                    minorHeadingValue1.getChildren().add(minorHeadingIdHidden);
                    minorHeadingValue1.getChildren().add(inputTextHidden);
                    minorHeadingValue1.getChildren().add(size);
                    
//                    minorHeadingValue1.getChildren().add(noteCheckBoxHidden);
                     
                    panel.getChildren().add(minorHeadingDesc);
                    panel.getChildren().add(minorHeadingValue1);
                    panel.getChildren().add(emptyValue1);
                    panel.getChildren().add(emptyValue2);
                    
                }
            }
        }
        getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG,null);
		return panel;
	}
	
	/**
	 * To get the details of the previous page from the current page
	 * @return String
	 */	
	public String getPreviousPage() {
   	 User user = null;
   	 MigrationContractSession  migrationContractSession =getMigrationContractSession();
		MigrationContract migrationContract = migrationContractSession.getMigrationContract();
		int compId =  0;
		int stdId = 0;
		BenefitComponentBenefit benefitComponentBenefit = null;
		String prodSysId= migrationContract.getEwpdProductSystemId();
		int productSysId = 0; 
		
		if(null != prodSysId && !("".equals(prodSysId))){
		    productSysId = Integer.parseInt(prodSysId);
		}
		else{
		    productSysId= 0;
		}
		if(migrationContract.getBenefitComponentId()  == 0){
			compId= 0;
		}else{
			compId = migrationContract.getBenefitComponentId();
		}
		int i =2;
		try{
			user = this.getUser();
			 benefitComponentBenefit = MigrationContractUtil.getInfoForTreeForStep8(productSysId,compId, stdId ,i,migrationContract);
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
			// may be needed to change to a tree of the step 8
			//also replace the following code with the code to open a step8 node.
			getSession().setAttribute(WebConstants.MIGRATION_STEP8_BACK,benefitComponentBenefit);
		    getSession().setAttribute(WebConstants.MIGRATION_TREE_BACK,null);
		    getSession().setAttribute(WebConstants.MIGRATION_STEP8_NEXT,null);
		    getSession().setAttribute(WebConstants.TREE_EXP_FLAG,null);
		    getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG,null);
		    
		    //settin next comp and ben ids
		    migrationContract.setBenefitComponentId(benefitComponentBenefit.getBenefitComponentId());
		    migrationContract.setStdBenefitId(benefitComponentBenefit.getBenefitId());
		    migrationContract.setBenefitCompName(benefitComponentBenefit.getBenefitComponentDesc());
		    migrationContract.setStdBenefitName(benefitComponentBenefit.getBenefitDesc());
		    
		    this.getMigrationContractSession().setMigrationContract(migrationContract);
			getRequest().setAttribute("RETAIN_Value", "");
		    return goToNextPage(WebConstants.MIG_CONTRACT_STEP8);
		}
		else{
		    //there is no previous bc in step 8. so need to go to  last node of the step7
			int ij =3;
			try {
	        	user = this.getUser();
			    benefitComponentBenefit = MigrationContractUtil.getInfoForTree(productSysId,compId, stdId ,ij,migrationContractSession);
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
				//going to the step 7
			    getSession().setAttribute(WebConstants.MIGRATION_TREE_BACK,benefitComponentBenefit);
			    getSession().setAttribute(WebConstants.MIGRATION_TREE_NEXT,null);
			    getSession().setAttribute(WebConstants.MIGRATION_STEP8_BACK,null);
			    getSession().setAttribute(WebConstants.MIGRATION_STEP8_NEXT,null);
			    getSession().setAttribute(WebConstants.TREE_EXP_FLAG,null);
			    getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG,null);
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
	}
	
	
	
	
	/**
	 * @param benefitPanel The benefitPanel to set.
	 */
	public void setBenefitPanel(HtmlPanelGrid benefitPanel) {
		this.benefitPanel = benefitPanel;
	}
	


	/**
	 * To get the details of the next page from the current page
	 * @return String
	 */	
	public String getNextPage() {

				performSaveAction(true, true);
				
				/******************DO THE NEEDED STEP  PAGE UPDATION ,COMPLETION*********/
				
				
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
				    benefitComponentBenefit = MigrationContractUtil.getInfoForTreeForStep8(productSysId,compId, stdId ,i,migrationContract);
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
					//MAKE other sessions null.
					
					//means there is next ben components.
				    getSession().setAttribute(WebConstants.MIGRATION_STEP8_NEXT,benefitComponentBenefit);
				    getSession().setAttribute(WebConstants.MIGRATION_TREE_NEXT,null);
				    getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG,null);
				    //settin next comp and ben ids
				    migrationContract.setBenefitComponentId(benefitComponentBenefit.getBenefitComponentId());
				    migrationContract.setStdBenefitId(benefitComponentBenefit.getBenefitId());
				    migrationContract.setBenefitCompName(benefitComponentBenefit.getBenefitComponentDesc());
				    migrationContract.setStdBenefitName(benefitComponentBenefit.getBenefitDesc());
				    
				    this.getMigrationContractSession().setMigrationContract(migrationContract);
					getRequest().setAttribute("RETAIN_Value", "");
				    return this.loadMigrationNotesPage();
				}
				else{
					//no ben components. so go to step 9
				    getSession().setAttribute(WebConstants.MIGRATION_TREE_NEXT,null);
				    getSession().setAttribute(WebConstants.MIGRATION_STEP8_NEXT,null);
				    getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG,null);
				    return goToNextPage(WebConstants.MIG_CONTRACT_STEP9);
				}
				
		
	}
	
	/**
	 * This method saves the current page and loads the next page.
	 * @return String
	 */
	public  String done() {
		performSaveAction(true, true);
		getRequest().setAttribute("RETAIN_Value", "");
		return goToNextPage(WebConstants.MIG_CONTRACT_STEP9);
	}

	/**
	 * @return Returns the majorheadingId.
	 */
	public String getMajorheadingId() {		
		
		return majorheadingId;
	}
	/**
	 * @param majorheadingId The majorheadingId to set.
	 */
	public void setMajorheadingId(String majorheadingId) {
		this.majorheadingId = majorheadingId;
	}
	/**
	 * @return Returns the benefitNotesFlag.
	 */
	public List getBenefitNotesFlag() {
		return benefitNotesFlag;
	}
	/**
	 * @param benefitNotesFlag The benefitNotesFlag to set.
	 */
	public void setBenefitNotesFlag(List benefitNotesFlag) {
		this.benefitNotesFlag = benefitNotesFlag;
	}
	/**
	 * @return Returns the minorheadingId.
	 */
	public List getMinorheadingId() {
		return minorheadingId;
	}
	/**
	 * @param minorheadingId The minorheadingId to set.
	 */
	public void setMinorheadingId(List minorheadingId) {
		this.minorheadingId = minorheadingId;
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
	 * To get the details of the benefit component
	 *
	 * @param benefitDetailsRequest
	 */
	public void getBenefitComponentNotesInfo(RetrieveBenefitDetailsRequest benefitDetailsRequest) {

		benefitDetailsRequest.setBenefitComponentId(this.getMigrationContractSession().getMigrationContract().getBenefitComponentId());

		benefitDetailsRequest.setAction(WebConstants.BENEFITCOMPONENT);
		
		
		RetrieveBenefitDetailsResponse retrieveBenefitDetailsResponse = (RetrieveBenefitDetailsResponse) this
    			.executeService(benefitDetailsRequest);
		BenefitComponentNote benefitCompontNote = retrieveBenefitDetailsResponse.getBenefitComponentNote();

		if(null!=benefitCompontNote){
			
			this.benefitComponentNote = benefitCompontNote;
		 	this.majorheadingId = benefitCompontNote.getMajorHeadingId();
		 	if(null!=benefitCompontNote.getComponentNoteMigrateFlag()){
		 		this.benefitComponentNotesFlag =benefitCompontNote.getComponentNoteMigrateFlag();
		 	}else{
		 		this.benefitComponentNotesFlag = WebConstants.FLAG_N;
		 	}
		 }else{
		 	this.majorheadingId = "";
		 	this.benefitComponentNotesFlag = WebConstants.FLAG_N;
//		 	this.benefitCompName = getBenefitCompName();
		 }
	}
	/**
	 * @param benefitComponentList The benefitComponentList to set.
	 */
//	public void setBenefitComponentList(List benefitComponentList) {
//		this.benefitComponentList = benefitComponentList;
//	}

	/**
	 * @return Returns the minorHeadingNotesFlagMap.
	 */
	public HashMap getMinorHeadingNotesFlagMap() {
		return minorHeadingNotesFlagMap;
	}
	/**
	 * @param minorHeadingNotesFlagMap The minorHeadingNotesFlagMap to set.
	 */
	public void setMinorHeadingNotesFlagMap(HashMap minorHeadingNotesFlagMap) {
		this.minorHeadingNotesFlagMap = minorHeadingNotesFlagMap;
	}
	/**
	 * @return Returns the minorHeadingValueMap.
	 */
	public HashMap getMinorHeadingValueMap() {
		return minorHeadingValueMap;
	}
	/**
	 * @param minorHeadingValueMap The minorHeadingValueMap to set.
	 */
	public void setMinorHeadingValueMap(HashMap minorHeadingValueMap) {
		this.minorHeadingValueMap = minorHeadingValueMap;
	}
	/**
	 * @return Returns the contractName.
	 */
	public String getContractName() {
		contractName = this.getMigrationContractSession().getMigrationContract().getContractId();
		return contractName;
	}
	/**
	 * @param contractName The contractName to set.
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	/**
	 * This method saves the details of the current page
	 *
	 * @return String
	 */
	public String saveNotesMigrationInfo(){
		
		performSaveAction(false,false);
		getRequest().setAttribute("RETAIN_Value", "");
		return "notesMigrationPage";
	}

	/**
	 * This method saves the details of the current page
	 *@param setNavFlag The navigation flag to set.
	 *@param navigationFlag 
	 */
	private void performSaveAction(boolean setNavFlag, boolean navigationFlag){
		
		List noteMigrationList = getSavedListFromScreen();
		SaveMigrationNotesRequest saveMigrationNotesRequest = (SaveMigrationNotesRequest) this
        					.getServiceRequest(ServiceManager.SAVE_MIGRATION_NOTES_REQUEST);
		saveMigrationNotesRequest.setAction(SaveMigrationNotesRequest.SAVE_MIGRATION_NOTES);		
		saveMigrationNotesRequest.setNoteMigrationList(noteMigrationList);
			
		
		if (setNavFlag) {
            this.getMigrationContractSession().getNavigationInfo()
                    .setNavigationFlag(navigationFlag);
        }
		this.setToRequest(saveMigrationNotesRequest);
        SaveMigrationNotesResponse saveMigrationNotesResponse = (SaveMigrationNotesResponse) this
                			.executeService(saveMigrationNotesRequest);
        List list = saveMigrationNotesResponse.getMessages();
        this.benefitList = null;
        addAllMessagesToRequest(list);
	
	}
	
	

	/**
	 * This method gets the details of the benefit, benefit component and contract from the page
	 *@return List 
	 *
	 */
	private List getSavedListFromScreen() {
		 List updatedNotesList = new ArrayList();
		 MigrateNotesVO migrateNotesVO;
		 
		 
	        if (null != this.getMinorHeadingNotesFlagMap() && null != this.getMinorHeadingValueMap()) {
	            // get the key set from the map
	            Set notesFlagKeySet = this.getMinorHeadingNotesFlagMap().keySet();
	            Set valueKeySet = this.getMinorHeadingValueMap().keySet();
	            Set idKeySet = this.getMinorHeadingIdMap().keySet();
	            
	            // iterate the key set
	            Iterator idIterator = idKeySet.iterator();
	            
	            Object idKey;
	            String flag,id;
	            // iterate the seqNoKeySet
	            while (idIterator.hasNext()) {
	                idKey = idIterator.next();
	                id = this.getMinorHeadingValueMap().get(idKey).toString();
	                flag = this.getMinorHeadingNotesFlagMap().get(idKey).toString();
	                if(!(id.equals("") && (!(flag.equals(WebConstants.FLAG_N)||flag.equals("") || flag.equals(WebConstants.FALSE))))){
		                migrateNotesVO = new MigrateNotesVO();
		                migrateNotesVO.setMinorheadingId(this.getMinorHeadingValueMap().get(idKey).toString());
		                //migrateNotesVO.setMigrateNotesFlag(this.getMinorHeadingNotesFlagMap().get(idKey).toString());
		                if((this.getMinorHeadingNotesFlagMap().get(idKey)).toString().equals(WebConstants.TRUE) || (this.getMinorHeadingNotesFlagMap().get(idKey)).toString().equals(WebConstants.FLAG_Y)){
		                	 migrateNotesVO.setMigrateNotesFlag(WebConstants.FLAG_Y);
		                }else{
		                	migrateNotesVO.setMigrateNotesFlag(WebConstants.FLAG_N);
		                }
		                
		                migrateNotesVO.setBenefitId(new Integer(idKey.toString()).intValue());
		                migrateNotesVO.setBenefitComponentId(this.getMigrationContractSession().getMigrationContract().getBenefitComponentId());
		                migrateNotesVO.setMigratedDateSegmentId(this.getMigrationContractSession().getDateSegmentId());
		                migrateNotesVO.setIdentifer(WebConstants.BENEFIT);
		                updatedNotesList.add(migrateNotesVO);
	                }
	            }
	        }
	        if(!(this.getMajorheadingId().equals("") && (!(this.getBenefitComponentNotesFlag().equals(WebConstants.FLAG_N) || this.getBenefitComponentNotesFlag().equals(""))))){
	        	migrateNotesVO = new MigrateNotesVO();
	        	migrateNotesVO.setMajorheadingId(this.majorheadingId);
	        	migrateNotesVO.setMigrateNotesFlag(this.benefitComponentNotesFlag);
	        	migrateNotesVO.setBenefitComponentId(this.getMigrationContractSession().getMigrationContract().getBenefitComponentId());
	        	migrateNotesVO.setMigratedDateSegmentId(this.getMigrationContractSession().getDateSegmentId());
	        	migrateNotesVO.setIdentifer(WebConstants.BENEFITCOMPONENT);
	        	updatedNotesList.add(migrateNotesVO);
			}
	        if(!StringUtil.isEmpty(this.contractNotesFlag)){
	        	migrateNotesVO = new MigrateNotesVO();
	        	migrateNotesVO.setContractNotesFlag(this.contractNotesFlag);
	        	migrateNotesVO.setIdentifer(WebConstants.CONTRACT_NEW);
	        	migrateNotesVO.setMigratedDateSegmentId(this.getMigrationContractSession().getDateSegmentId());
	        	updatedNotesList.add(migrateNotesVO);
	        }
	        
		return updatedNotesList;
	}
	/**

	 * @return Returns the benefitCompName.
	 */
	public String getBenefitCompName() {
		
		if(null == this.benefitComponentNote){
			RetrieveBenefitDetailsRequest benefitDetailsRequest = getRetrieveBenefitDetailsRequest();
			getBenefitComponentNotesInfo(benefitDetailsRequest);	
		
		}
		
		return benefitCompName  = this.getMigrationContractSession().getMigrationContract().getBenefitCompName();
		
	}
	/**
	 * @param benefitCompName The benefitCompName to set.
	 */
	public void setBenefitCompName(String benefitCompName) {
		this.benefitCompName = benefitCompName;
	}
	
	
	/**
	 * @return Returns the benefitComponentNotesFlag.
	 */
	public String getBenefitComponentNotesFlag() {
		
		return benefitComponentNotesFlag;
	}
	/**
	 * @param benefitComponentNotesFlag The benefitComponentNotesFlag to set.
	 */
	public void setBenefitComponentNotesFlag(String benefitComponentNotesFlag) {
		this.benefitComponentNotesFlag = benefitComponentNotesFlag;
	}

	
	/**
	 * @return Returns the minorHeadingIdMap.
	 */
	public HashMap getMinorHeadingIdMap() {
		return minorHeadingIdMap;
	}
	/**
	 * @param minorHeadingIdMap The minorHeadingIdMap to set.
	 */
	public void setMinorHeadingIdMap(HashMap minorHeadingIdMap) {
		this.minorHeadingIdMap = minorHeadingIdMap;
	}

	/**
	 * @param benefitDetailsRequest
	 * @return Returns the contractNotesFlag.
	 */
	private void getContractInfo(RetrieveBenefitDetailsRequest detailsRequest){
		
		detailsRequest.setAction(WebConstants.CONTRACT_NEW);
	
		RetrieveBenefitDetailsResponse retrieveBenefitDetailsResponse = (RetrieveBenefitDetailsResponse) this
    			.executeService(detailsRequest);
		List list = retrieveBenefitDetailsResponse.getList();
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			MigrationDateSegment dateSegment = (MigrationDateSegment)iterator.next();
			if(null!=dateSegment.getContractNoteMigrateFlag()){
				this.contractNotesFlag = dateSegment.getContractNoteMigrateFlag();
			}else{
				this.contractNotesFlag = WebConstants.FLAG_N;
			}
		}
	}
	
	/**
	 * @return Returns the contractNotesFlag.
	 */
	public String getContractNotesFlag() {
		return contractNotesFlag;
	}
	/**
	 * @param contractNotesFlag The contractNotesFlag to set.
	 */
	public void setContractNotesFlag(String contractNotesFlag) {
		this.contractNotesFlag = contractNotesFlag;
	}
	/**
	 * @return Returns the benefitList.
	 */
	public List getBenefitList() {
		return benefitList;
	}
	/**
	 * @param benefitList The benefitList to set.
	 */
	public void setBenefitList(List benefitList) {
		this.benefitList = benefitList;
	}
	/**
	 * @return Returns the benefitComponentNote.
	 */
	public BenefitComponentNote getBenefitComponentNote() {
		return benefitComponentNote;
	}
	/**
	 * @param benefitComponentNote The benefitComponentNote to set.
	 */
	public void setBenefitComponentNote(
			BenefitComponentNote benefitComponentNote) {
		this.benefitComponentNote = benefitComponentNote;
	}
}
