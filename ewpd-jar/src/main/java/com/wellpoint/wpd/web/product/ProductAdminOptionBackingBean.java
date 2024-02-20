/*
 * ProductAddQuestionBackingBean.java
 * 
 * Created on Feb 20, 2007
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.web.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.model.SelectItem;

//import com.ibm.wsspi.sib.exitpoint.ra.HashMap;
import com.wellpoint.wpd.common.benefitcomponent.request.RetrieveBenefitComponentQuestionnairRequest;
import com.wellpoint.wpd.common.benefitcomponent.response.LocateComponentsBenefitAdministrationResponse;
import com.wellpoint.wpd.common.benefitcomponent.response.UpdateComponentsBenefitAdministrationResponse;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.request.ProductAONotesToQuestionAttachmentRequest;
import com.wellpoint.wpd.common.notes.request.QuestionNotesPopUpRequest;
import com.wellpoint.wpd.common.notes.response.ProductAONotesToQuestionAttachmentResponse;
import com.wellpoint.wpd.common.notes.response.QuestionNotesPopUpResponse;
import com.wellpoint.wpd.common.notes.vo.NotesToQuestionAttachmentVO;
import com.wellpoint.wpd.common.product.bo.EntityProductAdministration;
import com.wellpoint.wpd.common.product.request.RetrieveProductAdminOptionRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductQuestionnairRequest;
import com.wellpoint.wpd.common.product.request.UpdateProductAdministrationRequest;
import com.wellpoint.wpd.common.product.response.RetrieveProductAdminOptionResponse;
import com.wellpoint.wpd.common.productstructure.vo.BenefitAdministrationOverrideVO;
import com.wellpoint.wpd.common.question.bo.PossibleAnswerBO;
import com.wellpoint.wpd.web.benefitcomponent.BenefitComponentSessionObject;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**

 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductAdminOptionBackingBean extends ProductBackingBean{
	
	private HtmlPanelGrid headerPanel = new HtmlPanelGrid();

	private HtmlPanelGrid panel = new HtmlPanelGrid();
	
	private String loadPurpose;
	
	private List messages;
	
	private List productAdminOptionList;
	
	private Map datafieldMap = new LinkedHashMap();
    
    private Map datafieldMapForQuestionId = new LinkedHashMap();
    
    private Map datafieldMapForAnswerId = new LinkedHashMap(); 
	
    private HtmlPanelGrid viewPanel = null;
    
    private String printValue;
    
    private String printBreadCrumbText;
    
    private String answerState = null;
    
    private List quesitionnaireList;
    
    public List viewQuestionnaireList;
    
    private String productSysId;
    
    private String adminOptionId;
    
    private String records;

	private List questionNotes;

	int i = 0;

	private String searchString;

	private String questionId;

	private String primaryEntityID;

	private String primaryType;

	private String prevNoteIdSelected;

	private String previousNoteVersion;

	private String noteStatus;

	private String noteId;

	private String noteVersion;

	private String noteName;

	private String version;

	private String newNoteId;
	
	private String tildaNoteStatus;
	
	private String noteAttached;
    
    
	/**
	 * @return Returns the viewQuestionnaireList.
	 */
	public List getViewQuestionnaireList() {
		return viewQuestionnaireList;
	}
	/**
	 * @param viewQuestionnaireList The viewQuestionnaireList to set.
	 */
	public void setViewQuestionnaireList(List viewQuestionnaireList) {
		this.viewQuestionnaireList = viewQuestionnaireList;
	}
    public ProductAdminOptionBackingBean() {
    	
        //checks if it is a view mode or edit mode
        if(isViewMode()){
    		this.setBreadCumbTextForView();
    	}else{
    		this.setBreadCumbTextForEdit();
    	}
    }
    /**
     * To get the list of benefit administration values from the db.
     * 
     * @return List benefitAdministrationList
     */
    private List getProductAdminOptionValues() {
        Logger.logInfo("Entering the method for getting benefit " 
        		+ "administration values");
        RetrieveProductAdminOptionRequest
			retrieveProductAdminOptionRequest = 
				(RetrieveProductAdminOptionRequest) getRetrieveBenefitAdminOptionRequest();
        // Call the executeService() to get the response
        RetrieveProductAdminOptionResponse 
			retrieveProductAdminOptionResponse =
				(RetrieveProductAdminOptionResponse) this
					.executeService(retrieveProductAdminOptionRequest);
        List benefitAdministrationList = null;
        if (null != retrieveProductAdminOptionResponse
                .getBenefitAdministrationList()
                && retrieveProductAdminOptionResponse.getBenefitAdministrationList()
                        .size() != 0) {
            // Get the list of benefit administration values from the response
            benefitAdministrationList = retrieveProductAdminOptionResponse
                    .getBenefitAdministrationList();
        }
        Logger.logInfo("Returning the method for getting benefit " 
        		+ "administration values");
        // Return the list to the calling method
        return benefitAdministrationList;
    }
  

    /**
     * To get the request object for retrieving the benefit administration list
     * after setting all the values in it.
     * 
     * @return RetrieveProductStructureBenefitAdministrationRequest
     */
    private RetrieveProductAdminOptionRequest 
		getRetrieveBenefitAdminOptionRequest() {
        // Create the session object
        int productId = this.getProductKeyFromSession();
        int adminOptionId = Integer.parseInt(this.getSession().getAttribute("ADMIN_KEY").toString());
        // Get the request object from the getServiceRequest()
        RetrieveProductAdminOptionRequest
			retrieveProductAdminOptionRequest = 
				(RetrieveProductAdminOptionRequest) this
                	.getServiceRequest(ServiceManager
                			.PRODUCT_BENEFIT_ADMIN_OPTION);

        retrieveProductAdminOptionRequest.setAdminOptSysId(adminOptionId);
        retrieveProductAdminOptionRequest.setProductId(productId);
        // Return the request object
        return retrieveProductAdminOptionRequest;
    }
    /**
     * Returns the headerPanel.
     * 
     * @return HtmlPanelGrid headerPanel.
     */
    public HtmlPanelGrid getHeaderPanel() {
        headerPanel = new HtmlPanelGrid();
       
        headerPanel.setWidth("100%");
        headerPanel.setColumns(4);
        headerPanel.setBorder(0);
        headerPanel.setCellpadding("3");
        headerPanel.setCellspacing("1");
        headerPanel.setBgcolor("#cccccc");
        headerPanel.setStyleClass("dataTableHeader");

        HtmlOutputText otxtType1 = new HtmlOutputText();
        HtmlOutputText otxtType2 = new HtmlOutputText();
        HtmlOutputText otxtType3 = new HtmlOutputText();
        HtmlOutputText otxtType4 = new HtmlOutputText();
        HtmlOutputText otxtType5 = new HtmlOutputText();
        otxtType1.setValue("Question");
        otxtType1.setId("Question");

        
        otxtType3.setValue("Answer");
        otxtType3.setId("Answer");

        otxtType4.setValue("Reference");
        otxtType4.setId("Reference");
        
        otxtType5.setValue("Notes");
        otxtType5.setId("Notes");
        
        headerPanel.getChildren().add(otxtType1);
        headerPanel.getChildren().add(otxtType3);
        headerPanel.getChildren().add(otxtType4);
        headerPanel.getChildren().add(otxtType5);

        return headerPanel;
    }
    private List getQuesitionnaireList(){
		 // Get the request object from the getServiceRequest()
    	RetrieveProductQuestionnairRequest retrieveProductQuestionnairRequest = getRetrieveProducttQuestionnairRequest();
		
    	retrieveProductQuestionnairRequest.setAction(RetrieveBenefitComponentQuestionnairRequest.LOAD_QUESTIONNARE_LIST);
       // Call the executeService() to get the response
       LocateComponentsBenefitAdministrationResponse benefitAdministrationResponse = 
       	(LocateComponentsBenefitAdministrationResponse) this
               .executeService(retrieveProductQuestionnairRequest);
       
		quesitionnaireList = benefitAdministrationResponse.getBenefitAdministrationList();
		 BenefitComponentSessionObject benefitComponentSessionObject = 
       	(BenefitComponentSessionObject) getSession().
				getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
		 benefitComponentSessionObject.setQuestionareList(quesitionnaireList);
		return quesitionnaireList;
	}
    /**
     * To get the request object for retrieving the Questionnare list
     * 
     * 
     * @return RetrieveBenefitComponentQuestionnairRequest
     */
    private RetrieveProductQuestionnairRequest getRetrieveProducttQuestionnairRequest() {

    	int productId = this.getProductKeyFromSession();
        int adminOptionId = Integer.parseInt(this.getSession().getAttribute("ADMIN_KEY").toString());
        
        // Get the request object from the getServiceRequest()
        RetrieveProductQuestionnairRequest retrieveProductQuestionnairRequest = new RetrieveProductQuestionnairRequest(); 

        // Set the administration id to the request
        retrieveProductQuestionnairRequest.setAdminOptSysId(adminOptionId);
        retrieveProductQuestionnairRequest.setProductId(productId);
        

        // Return the request object
        return retrieveProductQuestionnairRequest;
    }
    /**
     * To go to the Contract BenefitAdministration page when navigating
     * through the tree.
     * 
     * @return String forward String sunil
     */
    public String displayProductAdminOption() {

    //	productAdminOptionList = getProductAdminOptionValues();
        if(isViewMode())
        {
            return "displayProductAdminOptionView";   
        }else{
        	RetrieveProductQuestionnairRequest retrieveProductQuestionnairRequest = getRetrieveProducttQuestionnairRequest();
        	retrieveProductQuestionnairRequest.setAction(RetrieveBenefitComponentQuestionnairRequest.LOAD_QUESTIONNARE_LIST);
        	LocateComponentsBenefitAdministrationResponse benefitAdministrationResponse = 
               	(LocateComponentsBenefitAdministrationResponse) this
                       .executeService(retrieveProductQuestionnairRequest);
        	
            if(null!=benefitAdministrationResponse){
        		quesitionnaireList = benefitAdministrationResponse.getQuestionnareList();
        		ProductSessionObject productSessionObject = 
                								(ProductSessionObject) getSession().
    											 getAttribute(WebConstants.PROD_TYPE);
        		if(null!=quesitionnaireList&&quesitionnaireList.size()>0){
        		productSessionObject.setQuestionareList(quesitionnaireList);
        		/*	benefitComponentSessionObject.setAdminLevelOptionSysId(((BenefitComponentQuesitionnaireBO)quesitionnaireList.get(0)).getAdminLevelOptionSysId());
        	*/	
        		}
        		this.answerState=null;
        		preparePanel(quesitionnaireList);
                }
            
        	
            return "displayProductAdminOption";
        }
    }
    public String getLoadQuestionForView() {
		 // Get the request object from the getServiceRequest()
    	RetrieveProductQuestionnairRequest retrieveProductQuestionnairRequest = getRetrieveProducttQuestionnairRequest();
    	retrieveProductQuestionnairRequest.setAction(RetrieveBenefitComponentQuestionnairRequest.LOAD_QUESTIONNARE_VIEW);
		// Call the executeService() to get the response
		LocateComponentsBenefitAdministrationResponse response = (LocateComponentsBenefitAdministrationResponse) this.executeService(retrieveProductQuestionnairRequest);
		if (null != response
				&& null != response.getQuestionnareList()) {
			this.viewQuestionnaireList = response
					.getQuestionnareList();
			for (int i = 0; i < viewQuestionnaireList.size(); i++) {
				EntityProductAdministration questionnaireBO = (EntityProductAdministration) viewQuestionnaireList.get(i);
				String questionName = questionnaireBO.getQuestionDesc();
				questionName = getLevelPrefix(questionnaireBO.getLevel())+ questionName;
				questionnaireBO.setQuestionDesc(questionName);
			}
			setValuesToHidden();
		}	
		return "";
 }
    private void setValuesToHidden() {

    	ProductSessionObject productSessionObject = 
        	(ProductSessionObject) getSession().
				getAttribute(WebConstants.PROD_TYPE);

		
		this.setPrimaryEntityID(Integer.toString(productSessionObject.getProductKeyObject().getProductId()));
		
	}
    
	/**
	 * @return Returns the answerId.
	 */
	public int getAnswerId() {
		return answerId;
	}
	/**
	 * @param answerId The answerId to set.
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	/**
	 * @return Returns the rowNum.
	 */
	public int getRowNum() {
		return rowNum;
	}
	/**
	 * @param rowNum The rowNum to set.
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
    private int rowNum;
    
    private int answerId;
    
    public String  selectNewQuestionnreList(){
        
        String []  tildaArray = null;
		if(null!=this.tildaNoteStatus && !("").equals(this.tildaNoteStatus)){
		tildaArray =this.tildaNoteStatus.split("~");
		}
        
    	RetrieveProductQuestionnairRequest retrieveProductQuestionnairRequest = 
    		getRetrieveProducttQuestionnairRequest();
		int rowNum = this.rowNum;
		int answerId =this.answerId;
		ProductSessionObject productSessionObject = 
        	(ProductSessionObject) getSession().
				getAttribute(WebConstants.PROD_TYPE);
		quesitionnaireList = (List)productSessionObject.getQuestionareList();
		
		if(null!=tildaArray && tildaArray.length>0){
			processQuestionaireList(quesitionnaireList,tildaArray);
			}
			tildaArray =null;
			this.tildaNoteStatus =null;
		
		EntityProductAdministration entityProductAdministration=(EntityProductAdministration)quesitionnaireList.get(rowNum);
		//benefitComponentSessionObject.setAdminLevelOptionSysId(benefitComponentQuesitionnaireBO.getAdminLevelOptionSysId());
		
		retrieveProductQuestionnairRequest.setQuestionareListIndex(rowNum);
		retrieveProductQuestionnairRequest.setEntityProductAdministrationBO(entityProductAdministration);
		retrieveProductQuestionnairRequest.setSelectedAnswerId(answerId);
		retrieveProductQuestionnairRequest.setAction(RetrieveBenefitComponentQuestionnairRequest.LOAD_SELECTED_CHILD);
		retrieveProductQuestionnairRequest.setQuestionnareList(quesitionnaireList);
		retrieveProductQuestionnairRequest.setProductPrntSysId(productSessionObject.getProductKeyObject().getParentId());
		LocateComponentsBenefitAdministrationResponse benefitAdministrationResponse = 
        	(LocateComponentsBenefitAdministrationResponse) this
                .executeService(retrieveProductQuestionnairRequest);
		if(null!=benefitAdministrationResponse){
		 this.quesitionnaireList = benefitAdministrationResponse.getQuestionnareList();
		  productSessionObject.setQuestionareList(quesitionnaireList);
		 //benefitComponentSessionObject.setAdminLevelOptionSysId(((BenefitComponentQuesitionnaireBO)quesitionnaireList.get(0)).getAdminLevelOptionSysId());
		 preparePanel(quesitionnaireList);
		}
		return "displayProductAdminOption";
	}
    
    /** 
	 * @param questionaireList
	 * @param tildaArray  
	 */
	private void processQuestionaireList(List questionaireList,String[] tildaArray){
		
		int size = questionaireList.size();
		
		for(int i=0;i<size;i++){
			
			for (int j=0;j<tildaArray.length;j++){
				if((new Integer(i).toString()+"Y").equals(tildaArray[j])){
					((EntityProductAdministration) questionaireList
					.get(i)).setNotesExists("Y");
					break;
				}else if((new Integer(i).toString()+"N").equals(tildaArray[j])){
					((EntityProductAdministration) questionaireList
							.get(i)).setNotesExists("N");
							break;
				}
			}
		}		
	}
    
    /**
     * @return Returns the panel.
     */
    public void preparePanel(List quesitionnaireList) {
    StringBuffer line =null;
  	String finalline = null;
  	String productKey =null;
  	String adminOptionid =null;
  	panel = new HtmlPanelGrid();
  	panel.setWidth("100%");
  	panel.setColumns(4);	
  	panel.setBorder(0);
  	panel.setStyleClass("outputText");
  	panel.setCellpadding("3");
  	panel.setCellspacing("1");
  	panel.setBgcolor("#cccccc");
      StringBuffer rowClass = new StringBuffer();
      if (quesitionnaireList != null) {
          HtmlSelectOneMenu answerSelectOneMenu = null;
          HtmlOutputText questionOutputText = null;
          HtmlOutputText referenceOutputText = null;
          HtmlInputHidden childCountHidden = null;
          HtmlPanelGroup referenceGroup = null;
          HtmlGraphicImage notesAttachmentImage = null;
          HtmlOutputText notesHidden = null;
          if(this.answerState==null ||"".equalsIgnoreCase(this.answerState))
        	storeAnswerStates(quesitionnaireList);
          for (int i = 0; i < quesitionnaireList.size(); i++) {
          	EntityProductAdministration benefitComponentQuesitionnaireBO=(EntityProductAdministration)quesitionnaireList.get(i);
          	questionOutputText = new HtmlOutputText();
          	referenceOutputText = new HtmlOutputText();
          	childCountHidden =new HtmlInputHidden();
          	referenceGroup = new HtmlPanelGroup();
          	notesAttachmentImage = new HtmlGraphicImage();
          	notesHidden = new HtmlOutputText();
        	notesHidden.setId("notesHidden"+i); 
          	if(i>0){
          		rowClass.append(",");	
          	}
          	int level= benefitComponentQuesitionnaireBO.getLevel();
          	if(level>1){
          	finalline = getLevelPrefix(level);
          	questionOutputText.setValue(finalline+benefitComponentQuesitionnaireBO.getQuestionDesc());
          	rowClass.append("dataTableOddRow");
          	}else{
          		questionOutputText.setValue(benefitComponentQuesitionnaireBO.getQuestionDesc());
          		rowClass.append("dataTableEvenRow");
          	}
          	 childCountHidden.setId("childCount"+i);
          	 childCountHidden.setValue(new Integer(benefitComponentQuesitionnaireBO.getChildCount()));
          	 List answerList =  benefitComponentQuesitionnaireBO.getPossibleAnswerList();
          	 List possibleAnswersList = (List)getPossibleAnswersListForAQuestion(answerList);
          	 
          	 answerSelectOneMenu = new HtmlSelectOneMenu();
          	 answerSelectOneMenu.setId("selectitem" + i);
               UISelectItems uis = new UISelectItems();
               uis.setValue(possibleAnswersList);

               answerSelectOneMenu.setValue(new Integer(
               		benefitComponentQuesitionnaireBO.getSelectedAnswerid()).toString());
               answerSelectOneMenu.setStyleClass("formInputList");
               answerSelectOneMenu
                       .setStyleClass("formInputFieldBenefitStructure");
               answerSelectOneMenu.getChildren().add(uis);
               
               answerSelectOneMenu.setOnchange("return loadNewChild(this)");
               
               referenceOutputText.setId("refere"+i);
               referenceOutputText.setValue(benefitComponentQuesitionnaireBO.getReferenceDesc());
               
         	  
               if (benefitComponentQuesitionnaireBO.getNotesExists().equals("Y")) {
					notesAttachmentImage.setUrl("../../images/notes_exist.gif");
				} else {
					notesAttachmentImage.setUrl("../../images/page.gif");
				}
				String primaryType = "ATTACHPRODUCT";
				ProductSessionObject productSessionObject = 
		        	(ProductSessionObject) getSession().
						getAttribute(WebConstants.PROD_TYPE);

				notesAttachmentImage.setStyle("cursor:hand;");
				notesAttachmentImage.setId("notesAttachmentImage" + i);
				
			this.setProductSysId(Integer.toString(benefitComponentQuesitionnaireBO.getEntityId()));
			this.setAdminOptionId(Integer.toString(benefitComponentQuesitionnaireBO.getAdminOptSysId()));
			
			
			productKey=new Integer(productSessionObject.getProductKeyObject().getProductId()).toString();
			if(i==0){
				int adminOptionId=benefitComponentQuesitionnaireBO.getAdminOptSysId();
				if(0!=adminOptionId){
				getSession().setAttribute("ADMINOPTIONID",new Integer(adminOptionId).toString());
				}
			}
			adminOptionid = getSession().getAttribute("ADMINOPTIONID").toString();
				notesAttachmentImage
							.setOnclick("loadNotesPopup('../popups/productAdminQuestionNotesPopup.jsp','"
									+ benefitComponentQuesitionnaireBO
											.getQuestionNumber()
									+ "','"
									+ productKey
									+ "','"
									+ primaryType
									+ "','"
									+ null
									+ "','"
									+ null
									+ "','"
									+ adminOptionid
									+ "','"
									+"notesAttachmentImage" +i+"','"+i
									+ "');return false;");
               
               referenceGroup.getChildren().add(referenceOutputText);
               referenceGroup.getChildren().add(childCountHidden);
               panel.getChildren().add(questionOutputText);
               panel.getChildren().add(answerSelectOneMenu); 
               panel.getChildren().add(referenceGroup); 
               
               if ("Y".equals(benefitComponentQuesitionnaireBO
					.getValidDomainToAttach())) {
               	panel.getChildren().add(notesAttachmentImage);
			} else {
				panel.getChildren().add(notesHidden);
			}
               
               //panel.getChildren().add(notesAttachmentImage); 
          	
          }
      }
      panel.setRowClasses(rowClass.toString());
        
    }
    /**
	 * @param benefitAdminList2
	 */
	private void storeAnswerStates(List list) {
		if(null != list && list.size() > 0){
			StringBuffer buffer = new StringBuffer();
			for(Iterator i=list.iterator();i.hasNext();){
				EntityProductAdministration entityBO = (EntityProductAdministration)i.next();
				buffer.append(entityBO.getQuestionNumber());
				buffer.append("~");
				buffer.append(entityBO.getSelectedAnswerid());
				if(i.hasNext())
					buffer.append(":");
			}
			answerState = buffer.toString();
		}
	}
	
    public String getLevelPrefix(int level) {

        StringBuffer buffer = new StringBuffer("");

        for(int i=1; i<level; i++) {

                    buffer.append(" - ");

        }

        return buffer.toString();
    }
	/**
	 * @return Returns the panel.
	 */
	public HtmlPanelGrid getPanel() {
		return panel;
	}
    /**
	 * @param answerStates
	 */
	private void storeAnswerStates(Map answerStates) {
		if(null != answerStates){
			StringBuffer buffer = new StringBuffer();
			Set keys = answerStates.keySet();
			Iterator iterator = keys.iterator();
			while(iterator.hasNext()){
				Object key = iterator.next();
				Object value = answerStates.get(key);
				
				buffer.append(key);
				buffer.append("~");
				buffer.append(value);
				if(iterator.hasNext())
					buffer.append(":");
			}
			answerState = buffer.toString();
		}
		
	}

	/**
     * To group the answer descriptions from the list to possibleAnswerBOs.
     * 
     * @param benefitAdministrationList
     */
    private List getPossibleAnswersListForAQuestion(List answersList) {
        Logger.logInfo("Entering the method for getting possible answer " 
        		+ "list for a question");
        // Create a list
        List answerDescriptionList = null;
        
         // answerDescriptionList.add(0, new SelectItem(""));
        // Check whether the answerList is null or empty
        if (null != answersList || !answersList.isEmpty()) {
        	answerDescriptionList = new ArrayList( answersList.size());
            // Iterate thorugh the answersList
            for (int i = 0; i < answersList.size(); i++) {

                // Get the individual possible answerBO
                PossibleAnswerBO answerBO = (PossibleAnswerBO) answersList
                        .get(i);

                // Get the answer description from the bo
                String description = answerBO.getPossibleAnswerDesc();

                // Get the answer id from the bo
                int id = answerBO.getPossibleAnswerId();

                // Convert id to string
                String stringId = new Integer(id).toString();

                // Create a selectItem instance
                SelectItem selectItem = new SelectItem();

                // Set the description to the selectItem
                selectItem.setLabel(description);

                // Set the value to the selectItem
                selectItem.setValue(stringId);

                // Set the description to the new list
                answerDescriptionList.add(selectItem);
            }
        }
        Logger.logInfo("Returning the method for getting possible answer " 
        		+ "list for a question");
        // Return the list
        return answerDescriptionList;

    }
    
	 /**
     * To save the over ridden administration values to the db
     * 
     * @return String
     */
    public String saveBenefitAdministration() {
         
        // Create the request object from the getServiceRequest()
    	String []  tildaArray = null;
		if(null!=this.tildaNoteStatus && !("").equals(this.tildaNoteStatus)){
		tildaArray =this.tildaNoteStatus.split("~");
		}
		
    	
    	 int adminOptionId = Integer.parseInt(this.getSession().getAttribute("ADMIN_KEY").toString());
    	UpdateProductAdministrationRequest administrationRequest = new UpdateProductAdministrationRequest();
        ProductSessionObject productSessionObject = 
        	(ProductSessionObject) getSession().
				getAttribute(WebConstants.PROD_TYPE);
        
        quesitionnaireList = (List)productSessionObject.getQuestionareList();
        if(null!=tildaArray && tildaArray.length>0){
        	processQuestionaireList(quesitionnaireList,tildaArray);
        }
        productSessionObject.setQuestionareList(quesitionnaireList);
        tildaArray =null;
        this.tildaNoteStatus =null;
        
         int productId = productSessionObject.getProductKeyObject().getProductId();   
         administrationRequest.setQuestionnareList(productSessionObject.getQuestionareList());
         administrationRequest.setEntityId(productId);
         administrationRequest.setAdminlevelOptionSysId(adminOptionId);
         administrationRequest.setMainObjectIdentifier(productSessionObject.getProductKeyObject().getProductName());
         administrationRequest.setVersionNumber(productSessionObject.getProductKeyObject().getVersion());
         administrationRequest.setDomainList(productSessionObject.getProductKeyObject().getBusinessDomains());
         administrationRequest.setBCompName((String)getSession().getAttribute("BENEFIT_COMP_NAME"));
         //administrationRequest.setBenefitComponentId(Integer.parseInt((getSession().getAttribute("BNFT_CMPNT_KEY").toString())));
         administrationRequest.setBenefitComponentId(productSessionObject.getBenefitComponentId());
         administrationRequest.setBenefitId(productSessionObject.getBenefitId());
         administrationRequest.setBenefitAdminId(productSessionObject.getBenefitAdminId());
       //UpdateComponentsBenefitAdministrationResponse administrationResponse = (UpdateComponentsBenefitAdministrationResponse)
         setChangedAnswersForValidation(administrationRequest);
         UpdateComponentsBenefitAdministrationResponse response = 
         	(UpdateComponentsBenefitAdministrationResponse)this.executeService(administrationRequest);
         storeAnswerStates(administrationRequest.getQuestionnareList());
         if(null!=response){
         	getRequest().setAttribute("RETAIN_Value","");
         	preparePanel(quesitionnaireList);       	
         	return "displayProductAdminOption";
         } 
         return "";
    }
   
    
    /**
	 * @param benefitAdministrationList
	 * @param saveProductAdministrationRequest
	 */
	private void setChangedAnswersForValidation(UpdateProductAdministrationRequest request) {
		if(null != request &&
				request.getQuestionnareList().size() > 0){
			boolean qstnsChanged = false;
			List changeIds = null;
			Map oldStates = getOldState();
			List quesList = request.getQuestionnareList();
			if(null!=quesList){
				changeIds = new ArrayList(quesList.size());
			}
			Iterator iterator = quesList.iterator();
			while(iterator.hasNext()){
				EntityProductAdministration entityBO = (EntityProductAdministration)iterator.next();
				String qstnKey = entityBO.getQuestionNumber() + "";
				String[] values = (String[])oldStates.get(qstnKey);
				if(values != null){
					
					if(!values[0].equals(entityBO.getSelectedAnswerid()+"")){
						qstnsChanged = true;
						changeIds.add(qstnKey);
					}
				}else{
					qstnsChanged = true;
					changeIds.add(qstnKey);
				}
			}
			if(oldStates!=null){
				Set set=oldStates.keySet();
				Iterator iter=set.iterator();
				while(iter.hasNext()){
					boolean contains=false;
					String queskey=""+iter.next();
					if(quesList!=null&& quesList.size()>0){
						for(int i=0;i<quesList.size()&&!contains;i++){
							EntityProductAdministration entityBO = (EntityProductAdministration)quesList.get(i);
							if((queskey.equalsIgnoreCase(entityBO.getQuestionNumber() + "")))
								contains=true;	
						}		
					}
					if(!contains){
						qstnsChanged = true;
						changeIds.add(queskey);
					}
					
				}
			}
			request.setQstnsChanged(qstnsChanged);
			request.setChangedIds(changeIds);
		}
		
	}
	
	/**
	 * @return
	 */
	private Map getOldState() {
		Map oldState = new HashMap();
		if(null != answerState && !"".equals(answerState.trim())){
			String[] changedValues = answerState.split(":");
			for(int i =0;i<changedValues.length;i++){
				String changedValue = changedValues[i];
				String[] values = changedValue.split("~");
				if(values.length == 2){
					String qstnKey = values[0];
					String aswerId = values[1];
//					String hideFlag = values[2];
					oldState.put(qstnKey,new String[]{aswerId});						
				}
			}
		}
		return oldState;
	}
	

	/**
	 * @return
	 */
	private Map getStoredAnswerState() {
		Map asnwerStates = new HashMap();
		if(null != answerState && !"".equals(answerState.trim())){
			String[] keyValuePairs =  answerState.split(":");
			for(int i=0;i<keyValuePairs.length;i++){
				String keyValuePair = keyValuePairs[i];
				String[] keyAndValue = keyValuePair.split("~");
				if(keyAndValue.length == 2)
					asnwerStates.put(new Integer(keyAndValue[0]),new Integer(keyAndValue[1]));
			}
		}
		return asnwerStates;
	}

	/**
     * To get the list of over ridden values by taking it from the hidden.
     * hashMaps return List
     */
    private List getBenefitAdministrationOverriddenList() {

        // Create a list
        List productAdministrationList = null;

        // Get the question id from the hidden key set of the HashMap
        Set idSet = datafieldMapForQuestionId.keySet();
        
        // Create the iterator for the questionId
        Iterator questionIdIter = idSet.iterator();

        // Get the answers key set from the HashMap
        Set answerSet = datafieldMapForAnswerId.keySet();
        // Create the iterator for the answerSet
        Iterator answerIterator = answerSet.iterator();
        
        if(null!=idSet && null!=answerSet){
        	productAdministrationList = new ArrayList(idSet.size());
        }
        int productkey = this.getProductKeyFromSession();
        

        // Iterate through the HaspMap and get the individual question and
        // answer values and set them to the list
        while (questionIdIter.hasNext() && answerIterator.hasNext()) {

            // Get the long value
            Long iterationId = (Long) questionIdIter.next();

            // Create an instance of the VO
            BenefitAdministrationOverrideVO administrationOverrideVO = 
            	new BenefitAdministrationOverrideVO();

            // Get the value of id form the map
            String questionId = (String) datafieldMapForQuestionId
                    .get(iterationId);

            // Set the value of the question id
            administrationOverrideVO.setQuestionId((new Integer(questionId))
                    .intValue());

            // Get the value of id form the map
            String answerId = (String) datafieldMapForAnswerId.get(iterationId);
               administrationOverrideVO.setEntityId(productkey);
               // Set the value of the question id
            if(!(datafieldMap.get(iterationId).equals("")))
                administrationOverrideVO.setAnswerId(new Integer(
                    ((String) datafieldMap.get(iterationId))).intValue());

            // Add the VO to the list
            productAdministrationList.add(administrationOverrideVO);
        }

        // Return the list
        return productAdministrationList;

    }
    public HtmlPanelGrid getViewPanel() {
        panel = new HtmlPanelGrid();
        panel.setWidth("100%");
        panel.setColumns(4);
        panel.setBorder(0);
        panel.setStyleClass("outputText");
        panel.setCellpadding("3");
        panel.setCellspacing("1");
        panel.setBgcolor("#cccccc");
        
        // Get the list of benefit administration from the database
        productAdminOptionList = getProductAdminOptionValues();

        if (productAdminOptionList != null) {

            for (int i = 0; i < productAdminOptionList.size(); i++) { 
                EntityProductAdministration benefitAdministration = (EntityProductAdministration) productAdminOptionList
                        .get(i);

                HtmlOutputText htmlOutputText1 = new HtmlOutputText();
                htmlOutputText1.setStyleClass("mandatoryNormal");
                htmlOutputText1.setId("question" + i);
                htmlOutputText1.setValue(benefitAdministration.getQuestionDesc());
               
                HtmlOutputText htmlOutputText2 = new HtmlOutputText();
                htmlOutputText2.setStyleClass("mandatoryNormal");
                htmlOutputText2.setId("dataTypeLGND" + i);
                htmlOutputText2.setValue(benefitAdministration.getDataTypeLegend());
            
                List items = benefitAdministration.getAnswers();
                String answerDescription = getSelectedAnswerDescFromPossibleAnswersList(items,benefitAdministration.getAnswerId());
                HtmlOutputText htmlOutputText3 = new HtmlOutputText();
                htmlOutputText3.setId("selectitem" + i);

                if(null != answerDescription){
                	htmlOutputText3.setValue(answerDescription);
                }
                htmlOutputText3.setStyleClass("mandatoryNormal");             

                HtmlOutputText htmlOutputText = new HtmlOutputText();
                htmlOutputText.setId("reference" + i);
                htmlOutputText.setValue(benefitAdministration
                        .getReferenceDesc());
                htmlOutputText.setStyleClass("mandatoryNormal");

                panel.getChildren().add(htmlOutputText1);
                panel.getChildren().add(htmlOutputText2);
                panel.getChildren().add(htmlOutputText3);
                panel.getChildren().add(htmlOutputText);
            }
        }
        return panel;
    }
    private String getSelectedAnswerDescFromPossibleAnswersList(List items, int answerId) {
		// Iterate through the answers list 
		for(int i = 0 ; i < items.size() ; i++){
			
			// Get individual AnswerBO from the list
			PossibleAnswerBO possibleAnswerBO = (PossibleAnswerBO) items.get(i);
			// Get the answer id from the list
			int individualAnswerId = possibleAnswerBO.getPossibleAnswerId();
			
			// Get the answer description from the list
			String individualAnswerDesc = possibleAnswerBO.getPossibleAnswerDesc(); 
			
			// Check if the answer id matches the required answer id
			if(answerId == individualAnswerId){
				
				// Return the answer description
				return individualAnswerDesc;
			}
		}
		return null;
	}
	/**
	 * @return Returns the productAdminOptionList.
	 */
	public List getProductAdminOptionList() {
		return productAdminOptionList;
	}
	/**
	 * @param productAdminOptionList The productAdminOptionList to set.
	 */
	public void setProductAdminOptionList(List productAdminOptionList) {
		this.productAdminOptionList = productAdminOptionList;
	}
	/**
	 * @param headerPanel The headerPanel to set.
	 */
	public void setHeaderPanel(HtmlPanelGrid headerPanel) {
		this.headerPanel = headerPanel;
	}
	/**
	 * @return Returns the loadPurpose.
	 */
	public String getLoadPurpose() {
			return loadPurpose;
	}
	/**
	 * @param loadPurpose The loadPurpose to set.
	 */
	public void setLoadPurpose(String loadPurpose) {
		this.loadPurpose = loadPurpose;
	}
	/**
	 * @return Returns the messages.
	 */
	public List getMessages() {
		return messages;
	}
	/**
	 * @param messages The messages to set.
	 */
	public void setMessages(List messages) {
		this.messages = messages;
	}
	/**
	 * @param panel The panel to set.
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
	}
	
    /**
     * Returns the datafieldMap
     * @return Map datafieldMap.
     */
    public Map getDatafieldMap() {
        return datafieldMap;
    }
    /**
     * Sets the datafieldMap
     * @param datafieldMap.
     */
    public void setDatafieldMap(Map datafieldMap) {
        this.datafieldMap = datafieldMap;
    }
    /**
     * Returns the datafieldMapForAnswerId
     * @return Map datafieldMapForAnswerId.
     */
    public Map getDatafieldMapForAnswerId() {
        return datafieldMapForAnswerId;
    }
    /**
     * Sets the datafieldMapForAnswerId
     * @param datafieldMapForAnswerId.
     */
    public void setDatafieldMapForAnswerId(Map datafieldMapForAnswerId) {
        this.datafieldMapForAnswerId = datafieldMapForAnswerId;
    }
    /**
     * Returns the datafieldMapForQuestionId
     * @return Map datafieldMapForQuestionId.
     */
    public Map getDatafieldMapForQuestionId() {
        return datafieldMapForQuestionId;
    }
    /**
     * Sets the datafieldMapForQuestionId
     * @param datafieldMapForQuestionId.
     */
    public void setDatafieldMapForQuestionId(Map datafieldMapForQuestionId) {
        this.datafieldMapForQuestionId = datafieldMapForQuestionId;
    }
    
    /**
     * Sets the viewPanel
     * @param viewPanel.
     */
    public void setViewPanel(HtmlPanelGrid viewPanel) {
        this.viewPanel = viewPanel;
    }
    /**
     * @return printValue
     * 
     * Returns the printValue.
     */
    public String getPrintValue() {
        String requestForPrint = getRequest().getAttribute("printValueForAdminQuestion").toString();
        if(null != requestForPrint && !requestForPrint.equals("")){
            printValue = requestForPrint;
        }else{
            printValue = "";
        }
        return printValue;
    }
    /**
     * @param printValue
     * 
     * Sets the printValue.
     */
    public void setPrintValue(String printValue) {
        this.printValue = printValue;
    }
    /**
     * @return printBreadCrumbText
     * 
     * Returns the printBreadCrumbText.
     */
    public String getPrintBreadCrumbText() {
        printBreadCrumbText = "Product Configuration >> Product ("+getProductSessionObject().getProductKeyObject().getProductName()+")>> Print";
        return printBreadCrumbText;
    }
    /**
     * @param printBreadCrumbText
     * 
     * Sets the printBreadCrumbText.
     */
    public void setPrintBreadCrumbText(String printBreadCrumbText) {
        this.printBreadCrumbText = printBreadCrumbText;
    }
	/**
	 * @return Returns the answerState.
	 */
	public String getAnswerState() {
		return answerState;
	}
	/**
	 * @param answerState The answerState to set.
	 */
	public void setAnswerState(String answerState) {
		this.answerState = answerState;
	}
	public String getProductSysId() {
		return productSysId;
	}
	public void setProductSysId(String productSysId) {
		this.productSysId = productSysId;
	}
	public String getAdminOptionId() {
		return adminOptionId;
	}
	public void setAdminOptionId(String adminOptionId) {
		this.adminOptionId = adminOptionId;
	}
	
	public String getNewNoteId() {
		return newNoteId;
	}
	public void setNewNoteId(String newNoteId) {
		this.newNoteId = newNoteId;
	}
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public String getNoteStatus() {
		return noteStatus;
	}
	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}
	public String getNoteVersion() {
		return noteVersion;
	}
	public void setNoteVersion(String noteVersion) {
		this.noteVersion = noteVersion;
	}
	public String getPreviousNoteVersion() {
		return previousNoteVersion;
	}
	public void setPreviousNoteVersion(String previousNoteVersion) {
		this.previousNoteVersion = previousNoteVersion;
	}
	public String getPrevNoteIdSelected() {
		return prevNoteIdSelected;
	}
	public void setPrevNoteIdSelected(String prevNoteIdSelected) {
		this.prevNoteIdSelected = prevNoteIdSelected;
	}
	public String getPrimaryEntityID() {
		return primaryEntityID;
	}
	public void setPrimaryEntityID(String primaryEntityID) {
		this.primaryEntityID = primaryEntityID;
	}
	public String getPrimaryType() {
		return primaryType;
	}
	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public List getQuestionNotes() {
		i++;
		if(i==1){
			String searchString  = this.getRequest().getParameter("searchString");
			if(null!=searchString){
				this.searchString =searchString;
				QuestionNotesPopUpRequest request = getQuestionNotesPopUpRequest();
				QuestionNotesPopUpResponse response = (QuestionNotesPopUpResponse) this
				.executeService(request);
				if(null!=response){
					setValuesToBackinBean(response);
				}
			}
		}
		return questionNotes;
	}
	private void setValuesToBackinBean(QuestionNotesPopUpResponse response){
		
		this.setQuestionNotes(response.getNotesList());
		if(null!= questionNotes && questionNotes.size()>0){
			for(int i=0;i<questionNotes.size();i++){	
				NotesAttachmentOverrideBO overridebo= (NotesAttachmentOverrideBO)questionNotes.get(i);
				
				if(overridebo.getOverrideStatus().equals("Y")){
					this.noteAttached="Y";
					break;
				}else{
					this.noteAttached="N";	
				}
			}
			}
	}
	public void setQuestionNotes(List questionNotes) {
		this.questionNotes = questionNotes;
	}
	public String getRecords() {

		if(null!=this.questionNotes) return records;
		QuestionNotesPopUpRequest request = getQuestionNotesPopUpRequest();
			QuestionNotesPopUpResponse response = (QuestionNotesPopUpResponse) this
			.executeService(request);
			if(null!=response){
				setValuesToBackinBean(response);
			}
	return new String();
		
	}
	
	private QuestionNotesPopUpRequest getQuestionNotesPopUpRequest(){
		QuestionNotesPopUpRequest request = (QuestionNotesPopUpRequest) this
		.getServiceRequest(ServiceManager.QUESTION_NOTES_POPUP_REQUEST);
		
		if (null != getRequest().getParameter("questionId")
				&& !("").equals(getRequest().getParameter("questionId"))) {
			if(null!=getRequest().getParameter("questionId") && getRequest().getParameter("questionId").matches("^[0-9a-zA-Z_]+$")){
				this.questionId = getRequest().getParameter("questionId");
				this.getSession().setAttribute("questionId", questionId);
				}
		}
		if (null != getRequest().getParameter("primaryentityId")
				&& !("").equals(getRequest().getParameter("primaryentityId"))) {
			if(null!=getRequest().getParameter("primaryentityId") && getRequest().getParameter("primaryentityId").matches("^[0-9a-zA-Z_]+$")){
				this.primaryEntityID = getRequest().getParameter("primaryentityId");
				this.getSession().setAttribute("primaryEntityID", primaryEntityID);
				}
		}
		if (null != getRequest().getParameter("primaryEntytyType")
				&& !("").equals(getRequest().getParameter("primaryEntytyType"))) {
			if(null!=getRequest().getParameter("primaryEntytyType") && getRequest().getParameter("primaryEntytyType").matches("^[0-9a-zA-Z_]+$")){
				this.primaryType = getRequest().getParameter("primaryEntytyType");
				this.getSession().setAttribute("primaryType", primaryType);
				}
		}
		if (null != getRequest().getParameter("adminOptionId")
				&& !("").equals(getRequest().getParameter("adminOptionId"))) {
			if(null!=getRequest().getParameter("adminOptionId")  && getRequest().getParameter("adminOptionId").matches("^[0-9a-zA-Z_]+$")){
				this.adminOptionId = getRequest().getParameter("adminOptionId");
			this.getSession().setAttribute("adminOptionId", adminOptionId);
			}
		}
		request.setPrimaryEntityID((this.getSession().getAttribute("primaryEntityID")).toString());
		request.setPrimaryType(this.getSession().getAttribute("primaryType").toString());
		if(null!= this.getSession().getAttribute("adminOptionId"))
		request.setSecondaryId(this.getSession().getAttribute("adminOptionId").toString());

		if(null!=this.getSession().getAttribute("questionId").toString() && !("").equals(this.getSession().getAttribute("questionId").toString())&& !("null").equals(this.getSession().getAttribute("questionId").toString())){
		request.setQuestionId(Integer.parseInt(this.getSession().getAttribute("questionId").toString()));
		}
	
		if(null!=searchString && !("").equals(searchString)){
			String 	newSearchString = WPDStringUtil.escapeString(searchString);
			request.setSearchString("%"+newSearchString.trim().toUpperCase()+"%");
			request.setSearchAction(2);
		}else{
			request.setSearchAction(1);
		}
		request.setSecondaryEntityType("ATTACHADMNQUEST");
		return request;
	}
	
	public void setRecords(String records) {
		this.records = records;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setQuesitionnaireList(List quesitionnaireList) {
		this.quesitionnaireList = quesitionnaireList;
	}
	public String attachNotesToQuestion(){	
		
		ProductAONotesToQuestionAttachmentRequest notesToQuestionAttachmentRequest = (ProductAONotesToQuestionAttachmentRequest) this
			.getServiceRequest(ServiceManager.PROD_AO_NOTES_TO_QUESTION_ATTACHMENT_REQUEST);	
	
		notesToQuestionAttachmentRequest.setNotesToQuestionAttachmentVO(setValuesToNotesToQuestionAttachmentVO());

	ProductAONotesToQuestionAttachmentResponse attachmentResponse= (ProductAONotesToQuestionAttachmentResponse) this
	.executeService(notesToQuestionAttachmentRequest);
	
	List messageList = attachmentResponse.getMessages();
	
	refreshQuestionNote(messageList);
	
	return null;
	}
	
	/**
	 * Methods to set value to the request.
	 * @return
	 */
	private NotesToQuestionAttachmentVO setValuesToNotesToQuestionAttachmentVO() {
		NotesToQuestionAttachmentVO notesToQuestionAttachmentVO = new NotesToQuestionAttachmentVO();
		
		notesToQuestionAttachmentVO.setQuestionId(Integer.parseInt(this
				.getQuestionId()));
		notesToQuestionAttachmentVO.setNoteId(this.getNoteId());

		notesToQuestionAttachmentVO.setPrimaryId(Integer.parseInt(this
				.getPrimaryEntityID()));
		notesToQuestionAttachmentVO.setPrimaryEntityType("ATTACHPRODUCT");
		notesToQuestionAttachmentVO.setSecondaryId(Integer.parseInt(this
				.getAdminOptionId()));
		
		notesToQuestionAttachmentVO.setNoteOverrideStatus("F");

		notesToQuestionAttachmentVO.setNoteVersionNumber(Integer.parseInt(this
				.getNoteVersion()));

		notesToQuestionAttachmentVO.setSecondaryEntityType("ATTACHADMNQUEST");
		
		ProductSessionObject productSessionObject = 
        	(ProductSessionObject) getSession().
				getAttribute(WebConstants.PROD_TYPE);


		notesToQuestionAttachmentVO.setProdName(productSessionObject.getProductKeyObject().getProductName());
		notesToQuestionAttachmentVO
				.setProdVersion(productSessionObject.getProductKeyObject().getVersion());
		notesToQuestionAttachmentVO.setBusinessDomainList(productSessionObject.getProductKeyObject().getBusinessDomains());
		

		if (this.noteStatus.equals("update"))
			notesToQuestionAttachmentVO.setUpdateRequest(true);
		if (this.noteStatus.equals("delete"))
			notesToQuestionAttachmentVO.setDeleteRequest(true);
		if (this.noteStatus.equals("insert"))
			notesToQuestionAttachmentVO.setInsertRequest(true);

		return notesToQuestionAttachmentVO;
	}

	private void refreshQuestionNote(List message) {

		QuestionNotesPopUpRequest request = getQuestionNotesPopUpRequest();

		QuestionNotesPopUpResponse response = (QuestionNotesPopUpResponse) this
				.executeService(request);
		if (null != response) {
			setValuesToBackinBean(response);
		}
		addAllMessagesToRequest(message);

	}
    /**
     * @return Returns the tildaNoteStatus.
     */
    public String getTildaNoteStatus() {
        return tildaNoteStatus;
    }
    /**
     * @param tildaNoteStatus The tildaNoteStatus to set.
     */
    public void setTildaNoteStatus(String tildaNoteStatus) {
        this.tildaNoteStatus = tildaNoteStatus;
    }
	/**
	 * @return Returns the noteAttached.
	 */
	public String getNoteAttached() {
		return noteAttached;
	}
	/**
	 * @param noteAttached The noteAttached to set.
	 */
	public void setNoteAttached(String noteAttached) {
		this.noteAttached = noteAttached;
	}
}
