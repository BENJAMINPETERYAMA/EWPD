/*
 * ProductBenefitAdministrationBackingBean.java
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;

//import com.ibm.wsspi.sib.exitpoint.ra.HashMap;
import com.wellpoint.wpd.common.contract.bo.ContractQuesitionnaireBO;
import com.wellpoint.wpd.common.contract.request.RetrieveAllPossibleAnswerRequest;
import com.wellpoint.wpd.common.contract.response.RetrieveAllPossibleAnswerResponse;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierCriteria;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.override.benefit.bo.EntityBenefitAdministration;
import com.wellpoint.wpd.common.product.bo.ProductQuestionareBO;
import com.wellpoint.wpd.common.product.request.QuestionDeleteRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductBenefitAdminRequest;
import com.wellpoint.wpd.common.product.request.RetrieveProductQuestionareRequest;
import com.wellpoint.wpd.common.product.request.UpdateProductQuestionareRequest;
import com.wellpoint.wpd.common.product.response.QuestionDeleteResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductBenefitAdminResponse;
import com.wellpoint.wpd.common.product.response.RetrieveProductQuestionareResponse;
import com.wellpoint.wpd.common.product.response.UpdateProductQuestionareResponse;
import com.wellpoint.wpd.common.product.vo.ProductBenefitAdminOverrideVO;
import com.wellpoint.wpd.common.question.bo.PossibleAnswerBO;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBenefitAdministrationBackingBean extends ProductBackingBean {

	private EntityBenefitAdministration psbaObject1;

	private EntityBenefitAdministration psbaObject2;

	private EntityBenefitAdministration psbaObject3;

	private List answerList;

	private List typeList; 

	private String viewType;

	private String reference;

	private List baList;

	private List resultList;

	HtmlPanelGrid headerPanel = null;

	HtmlPanelGrid panel = null;

	HtmlPanelGrid viewPanel;

	private Map datafieldMap = new LinkedHashMap();

	private Map datafieldMapForQuestionId = new LinkedHashMap();

	private Map datafieldMapForAnswerId = new LinkedHashMap();

	private Map datafieldmapForQuestionHideFlag = new LinkedHashMap();

	private Map datafieldmapForAOHideFlag = new LinkedHashMap();

	private Map datafieldMapForAdminAsscId = new LinkedHashMap();

	private boolean viewBool = false;

	private String hiddenInit;

	private String printAdmin;

	private String printBreadCrumbText;

	private int rowId = 0;

	private List validationMessages = null;

	//private boolean showHiddenSelected= false;
	private boolean showHiddenSelected;

	private String QstnHideFlag;

	private String adminOptionHideFlag;

	private boolean answerOverrideFlag = false;

	private String questionsStates;
	
	private String tieredQuestionsStates;

	private boolean hideStatusFlag = false;

	private List questionareList;
	
	private List tierList = null;
	
	//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module     
	private List orgQuestionnaireList;
	
	private List orgTierList = null;
	
	private List newQuestions             = null;
	
	private List modifiedQuestions        = null;
	
	private List removedQuestions         = null;
	
	private List newTieredQuestions       = null;
	
	private List modifiedTieredQuestions  = null;
	
	private List removedTieredQuestions   = null;
	
	java.util.HashMap possibleAnswerMap;

	private HtmlPanelGrid questionarePanel;

	private int rowNum;

	private int answerId;
	//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
	private String answerDesc="";

	private String bCompName;
	
	private String primaryEntityID;
	private String questionId;
	private String benefitComponentId;
	private String adminLvlOptionAssnSysId;
	
	private String tildaNoteStatus;
	
	private int benefitDefnId;
	
	private int benefitComponentID;
	
	private HtmlPanelGrid tierQuestionarePanel;
	
	private HtmlPanelGrid tierHeaderPanel;
	
	private String panelTierSysId;
	
	private int adminLevelAssociationId;
	
	private String tildaTierNoteStatus;
	
	private boolean printMode = false;

	
	
	public ProductBenefitAdministrationBackingBean() {
		super();
		if (!super.isViewMode()) {
			this.setBreadCumbTextForEdit();
		} else {
			this.setBreadCumbTextForView();
		}
		psbaObject1 = new EntityBenefitAdministration();
		psbaObject2 = new EntityBenefitAdministration();
		psbaObject3 = new EntityBenefitAdministration();

	}

	/*
	 * Retrieves the Questionare for a particular admin option
	 */
	public String loadQuestionare() {
		
		RetrieveProductQuestionareRequest retrieveProductQuestionareRequest = (RetrieveProductQuestionareRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_QUESTIONNARE_REQUEST);
		
		ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
		.getAttribute(WebConstants.PROD_TYPE);
		retrieveProductQuestionareRequest = getRetrieveProductQuestionareRequest();
		//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
		possibleAnswerMap = getAllPossibleAnswersForAdminOption(); 
		productSessionObject.setAllPossibleAnswerMap(possibleAnswerMap);
		retrieveProductQuestionareRequest
				.setAction(RetrieveProductQuestionareRequest.LOAD_QUESTIONNARE_LIST);
		retrieveProductQuestionareRequest.setAllPossibleAnswerMap(possibleAnswerMap);
		String returnString = null;

		//calls the service
		RetrieveProductQuestionareResponse retrieveProductQuestionareResponse = (RetrieveProductQuestionareResponse) executeService(retrieveProductQuestionareRequest);

		if (null != retrieveProductQuestionareResponse && null !=retrieveProductQuestionareResponse
				.getQuestionareList()) {
			if(retrieveProductQuestionareResponse
					.getQuestionareList().size() > 0){
				questionareList = (List)retrieveProductQuestionareResponse
				.getQuestionareList().get(0);
			}else{
				questionareList = new ArrayList();
			}
			
			if (null != questionareList && questionareList.size() > 0) {
				productSessionObject.setQuestionareList(questionareList);
				//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
				orgQuestionnaireList = new ArrayList();
	        	for(int i=0;i<questionareList.size();i++){
	        		ProductQuestionareBO ptQueBO = new ProductQuestionareBO();
	        		ProductQuestionareBO ptQueBO1 = (ProductQuestionareBO)questionareList.get(i);
	        		ptQueBO.setQuestionnaireId( ptQueBO1.getQuestionnaireId()  );
	        		ptQueBO.setParentQuestionnaireId( ptQueBO1.getParentQuestionnaireId()  );
	        		ptQueBO.setSelectedAnswerid( ptQueBO1.getSelectedAnswerid()  );
	        		ptQueBO.setAdminLevelOptionSysId( ptQueBO1.getAdminLevelOptionSysId()  );
	        		ptQueBO.setProductSysId( ptQueBO1.getProductSysId()  );
	        		ptQueBO.setBenefitComponentId( ptQueBO1.getBenefitComponentId()  );
	        		ptQueBO.setSelectedAnswerDesc( ptQueBO1.getSelectedAnswerDesc() );
	        		orgQuestionnaireList.add(ptQueBO); 
	        	}	 
	        	productSessionObject.setOrgQuestionnaireList(removeNotAnsweredQuestions(orgQuestionnaireList));
	        	
				productSessionObject
						.setAdminLevelOptionAssnId(((ProductQuestionareBO) questionareList
								.get(0)).getAdmnLvlAsscId());
				this.adminLevelAssociationId = ((ProductQuestionareBO) questionareList
				.get(0)).getAdmnLvlAsscId();
				if(retrieveProductQuestionareResponse
						.getQuestionareList().size() > 1){
					tierList=(List)retrieveProductQuestionareResponse
					.getQuestionareList().get(1);
				}else{
					tierList = null;
				}
				productSessionObject.setTierQuestionnaireList(tierList);
				//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
				orgTierList = new ArrayList();
	        	if(null != tierList){
	        		
	            		for(int j =0;j<tierList.size();j++){
	    					BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(j); // iterating tier definitions
	    					List benefitTierList = defnBo.getBenefitTiers();
	    					for (int k =0; k<benefitTierList.size();k++){
	    						BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
	    						List questionList = tierBo.getQuestionnaireList();
	    						for(int l=0;l<questionList.size();l++){
	    							ProductQuestionareBO productQuestionareBO = new ProductQuestionareBO();
	    							ProductQuestionareBO productQuestionareBO1 = new ProductQuestionareBO();
	    							productQuestionareBO1 = (ProductQuestionareBO)questionList.get(l);
	    							productQuestionareBO.setQuestionnaireId( productQuestionareBO1.getQuestionnaireId()  );
	    							productQuestionareBO.setParentQuestionnaireId( productQuestionareBO1.getParentQuestionnaireId()  );
	    							productQuestionareBO.setSelectedAnswerid( productQuestionareBO1.getSelectedAnswerid()  );
	    							productQuestionareBO.setTierSysId( productQuestionareBO1.getTierSysId()  );
	    							productQuestionareBO.setAdminLevelOptionSysId( productQuestionareBO1.getAdminLevelOptionSysId()  );
	    							productQuestionareBO.setProductSysId( productQuestionareBO1.getProductSysId()  );
	    							productQuestionareBO.setBenefitComponentId( productQuestionareBO1.getBenefitComponentId()  );
	    							productQuestionareBO.setSelectedAnswerDesc( productQuestionareBO1.getSelectedAnswerDesc() );
	    							orgTierList.add(productQuestionareBO);   
	    						}
	    					}
	    				}
	            	
	        	}
	        	productSessionObject.setOrgTierQuestionnaireList(removeNotAnsweredQuestions(orgTierList));
	        	
			}
			
		}
		if (super.isViewMode()) {
			prepareList(this.questionareList);
			prepareTierPanel(tierList);
			return "productQuestionnaireView";
		} else {
			this.questionsStates = null;
			preparePanel(questionareList);
			prepareTierPanel(tierList);
			return "productQuestionnaireEdit";
		}

	}
	public java.util.HashMap getAllPossibleAnswersForAdminOption(){
    	
    		//14 jan 2011 - Code optimization for - get possible answers
    	RetrieveAllPossibleAnswerRequest retrieveAllPossibleAnswerRequest = getRetrieveAllPossibleAnswerRequest();
	        // Call the executeService() to get the response
    	RetrieveAllPossibleAnswerResponse 
				retrieveAllPossibleAnswerResponse =
					(RetrieveAllPossibleAnswerResponse) this
						.executeService(retrieveAllPossibleAnswerRequest);
    	java.util.HashMap allPossibleAnswerMap = new java.util.HashMap();
	        List allPossibleAnswerList = null;
	        if(null!=retrieveAllPossibleAnswerResponse && null !=retrieveAllPossibleAnswerResponse.getAllPossibleAnswerList() && 
	        		retrieveAllPossibleAnswerResponse.getAllPossibleAnswerList().size() >0){
	        	
	        	allPossibleAnswerList = (List)retrieveAllPossibleAnswerResponse.getAllPossibleAnswerList();
	        	Iterator it1 = allPossibleAnswerList.iterator();
	        	int count =0;
	        		while(it1.hasNext()){
	        			PossibleAnswerBO possibleAnswerBO = (PossibleAnswerBO)it1.next();
	        			int questionNumber = possibleAnswerBO.getQuestionNumber();
	        			ArrayList possibleAnswerList = allPossibleAnswerMap.get(new Integer(questionNumber))!=null?
	        					(ArrayList)allPossibleAnswerMap.get(new Integer(questionNumber)):
	        						new ArrayList();
	        			possibleAnswerList.add(possibleAnswerBO);
	        			allPossibleAnswerMap.put(new Integer(questionNumber),possibleAnswerList);
	        			        			
	        		}
	        	
	        }
	        return allPossibleAnswerMap;
    }
	 /**
     * To get the request object for retrieving the possible answer list
     * after setting all the values in it.
     * 
     * @return RetrieveAllPossibleAnswerRequest
     */
    private RetrieveAllPossibleAnswerRequest 
		getRetrieveAllPossibleAnswerRequest() {
        // Create the session object
    	ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
		.getAttribute(WebConstants.PROD_TYPE);
        int adminOptionId = new Integer(productSessionObject.getAdminOptionId()).intValue();
        
        RetrieveAllPossibleAnswerRequest
			retrieveAllPossibleAnswerRequest = 
				(RetrieveAllPossibleAnswerRequest) this
                	.getServiceRequest(ServiceManager
                			.RETRIEVE_ALL_POSSIBLE_ANSWER_LIST);

        retrieveAllPossibleAnswerRequest.setAdminOptSysId(adminOptionId);
        // Return the request object
        return retrieveAllPossibleAnswerRequest;
    }
	/**
	 * Method to change the list, so that the child questionnaire description
	 * will be appended with '-' for indentation
	 * 
	 * @param questionareList
	 */
	private void prepareList(List questionareList) {

		if (null != questionareList && questionareList.size() > 0
				&& !questionareList.isEmpty()) {
			ProductQuestionareBO productQuestionareBO = null;
			StringBuffer rowClass = new StringBuffer();
			String finalline = null;
			for (int i = 0; i < questionareList.size(); i++) {
				productQuestionareBO = (ProductQuestionareBO) questionareList
						.get(i);
				if (i > 0) {
					rowClass.append(",");
				}
				int level = productQuestionareBO.getLevel();
				if (level > 1) {
					finalline = getLevelPrefix(level);
					productQuestionareBO.setQuestionName(finalline
							+ productQuestionareBO.getQuestionName());
				}
			}
		}

	}

	/* for printing questionaire */
	public String getLoadQuestionForPrint() {
		RetrieveProductQuestionareRequest retrieveProductQuestionareRequest = (RetrieveProductQuestionareRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_QUESTIONNARE_REQUEST);
		retrieveProductQuestionareRequest = getRetrieveProductQuestionareRequest();
		retrieveProductQuestionareRequest
				.setAction(RetrieveProductQuestionareRequest.LOAD_QUESTIONNARE_LIST);
		String returnString = null;
		//calls the service
		RetrieveProductQuestionareResponse retrieveProductQuestionareResponse = (RetrieveProductQuestionareResponse) executeService(retrieveProductQuestionareRequest);
		if (null != retrieveProductQuestionareResponse) {
			questionareList = (List)retrieveProductQuestionareResponse
					.getQuestionareList().get(0);
			this.prepareList(questionareList);
			
			if(retrieveProductQuestionareResponse
					.getQuestionareList().size() > 1){
				tierList=(List)retrieveProductQuestionareResponse
				.getQuestionareList().get(1);
				setPrintMode(true);
				this.prepareTierPanel(tierList);
			}else{
				tierList = null;
			}			
			
		}
		return " ";
	}

	/*
	 * This method for retrieving Questionnare while chenging an answer of a
	 * Question node.
	 * 
	 * This save new questionnare list to session. this call business service
	 * method to retrieve Questionnare list.
	 *  
	 */
	public String selectNewQuestionnreList() {
	    
	    String []  tildaArray = null;
		if(null!=this.tildaNoteStatus && !("").equals(this.tildaNoteStatus)){
		tildaArray =this.tildaNoteStatus.split("~");
		}	
	    String []  tildaTierArray = null;
		if(null!=this.tildaTierNoteStatus && !("").equals(this.tildaTierNoteStatus)){
			tildaTierArray =this.tildaTierNoteStatus.split("~");
		}
	    
		RetrieveProductQuestionareRequest retrieveProductQuestionareRequest = getRetrieveProductQuestionareRequest();
		int rowNum = this.rowNum;
		int answerId = this.answerId;
		String answerDesc = this.answerDesc;
		retrieveProductQuestionareRequest.setSelectedAnswerDesc(this.answerDesc);
		//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
		ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
				.getAttribute(WebConstants.PROD_TYPE);

		questionareList = (List) productSessionObject.getQuestionareList();
		tierList = (List )productSessionObject.getTierQuestionnaireList();
		if(null!=tildaArray && tildaArray.length>0){
			processQuestionaireList(questionareList,tildaArray);
		}
		tildaArray =null;
		this.tildaNoteStatus =null;
		//			start -- code for saving notes status to the questionnare list
		if(null!=tildaTierArray && tildaTierArray.length>0){
			processTierQuestionaireList(tierList,tildaTierArray);
		}
		tildaTierArray =null;
		this.tildaTierNoteStatus =null;
		//quesitionnaireList =
		// (List)getSession().getAttribute("QUESTONNARE_LIST");
		ProductQuestionareBO productQuestionareBO = (ProductQuestionareBO) questionareList
				.get(rowNum);
		retrieveProductQuestionareRequest.setBenefitComponentSysId(productSessionObject.getBenefitComponentId());
		productSessionObject.setAdminLevelOptionAssnId(productQuestionareBO
				.getAdminLevelOptionSysId());
		retrieveProductQuestionareRequest.setAdmnLvlOptionAsscId(adminLevelAssociationId);
		retrieveProductQuestionareRequest.setQuestionareListIndex(rowNum);
		retrieveProductQuestionareRequest
				.setProductQuestionareBO(productQuestionareBO);
		retrieveProductQuestionareRequest.setSelectedAnswerId(answerId);
		retrieveProductQuestionareRequest.setSelectedAnswerDesc(answerDesc);
		retrieveProductQuestionareRequest
				.setAction(RetrieveProductQuestionareRequest.LOAD_SELECTED_CHILD);
		retrieveProductQuestionareRequest.setQuestionnareList(questionareList);
		retrieveProductQuestionareRequest
				.setProductPrntSysId(productSessionObject.getProductKeyObject()
						.getParentId());
		RetrieveProductQuestionareResponse retrieveProductQuestionareResponse = (RetrieveProductQuestionareResponse) this
				.executeService(retrieveProductQuestionareRequest);
		if (null != retrieveProductQuestionareResponse) {
			this.questionareList = retrieveProductQuestionareResponse
					.getQuestionareList();
			productSessionObject.setQuestionareList(questionareList);
			productSessionObject
					.setAdminLevelOptionAssnId(((ProductQuestionareBO) questionareList
							.get(0)).getAdmnLvlAsscId());
			preparePanel(questionareList);
			prepareTierPanel(tierList);
		}
		return "productQuestionnaireEdit";
	}
	
	/*
	 * This method for retrieving Questionnare while chenging an answer of a
	 * Question node in a tiered question.
	 * 
	 * This save new questionnare list to session. this call business service
	 * method to retrieve Questionnare list.
	 *  
	 */
	public String selectNewTierQuestionnreList() {
	    //this tildaArray will contain the information for showing note staus for unsaved questions.
	    String []  tildaTierArray = null;
		if(null!=this.tildaTierNoteStatus && !("").equals(this.tildaTierNoteStatus)){
			tildaTierArray =this.tildaTierNoteStatus.split("~");
		}	
	    
		String []  tildaArray = null;
		if(null!=this.tildaNoteStatus && !("").equals(this.tildaNoteStatus)){
		tildaArray =this.tildaNoteStatus.split("~");
		}
		RetrieveProductQuestionareRequest retrieveProductQuestionareRequest = getRetrieveProductQuestionareRequest();
		int rowNum = this.rowNum;
		int answerId = this.answerId;
		String answerDesc = this.answerDesc;
		retrieveProductQuestionareRequest.setSelectedAnswerDesc(this.answerDesc);
		
		ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
				.getAttribute(WebConstants.PROD_TYPE);
		questionareList = (List) productSessionObject.getQuestionareList();
		tierList = (List) productSessionObject.getTierQuestionnaireList();
		List tierQuestionList =getTierQuestionList (tierList);
		//start -- code for saving notes status to the questionnare list
		if(null!=tildaTierArray && tildaTierArray.length>0){
			processTierQuestionaireList(tierList,tildaTierArray);
			}
			tildaTierArray =null;
			this.tildaTierNoteStatus =null;
			
			if(null!=tildaArray && tildaArray.length>0){
				processQuestionaireList(questionareList,tildaArray);
				}
			tildaArray =null;
			this.tildaNoteStatus =null;
		//end 
		//quesitionnaireList =
		// (List)getSession().getAttribute("QUESTONNARE_LIST");
		
		if(null != tierQuestionList)	{
		ProductQuestionareBO productQuestionareBO = (ProductQuestionareBO) tierQuestionList
				.get(rowNum);
		retrieveProductQuestionareRequest.setBenefitComponentSysId(productSessionObject.getBenefitComponentId());
		productSessionObject.setAdminLevelOptionAssnId(productQuestionareBO
				.getAdminLevelOptionSysId());
		retrieveProductQuestionareRequest.setAdmnLvlOptionAsscId(adminLevelAssociationId);
		retrieveProductQuestionareRequest.setQuestionareListIndex(rowNum);
		retrieveProductQuestionareRequest
				.setProductQuestionareBO(productQuestionareBO);
		retrieveProductQuestionareRequest.setSelectedAnswerId(answerId);
		retrieveProductQuestionareRequest.setSelectedAnswerDesc(answerDesc);
		retrieveProductQuestionareRequest
				.setAction(RetrieveProductQuestionareRequest.LOAD_SELECTED_CHILD_TIER);
		retrieveProductQuestionareRequest.setQuestionnareList(tierQuestionList);
		retrieveProductQuestionareRequest
				.setProductPrntSysId(productSessionObject.getProductKeyObject()
						.getParentId());
		RetrieveProductQuestionareResponse retrieveProductQuestionareResponse = (RetrieveProductQuestionareResponse) this
				.executeService(retrieveProductQuestionareRequest);
		if (null != retrieveProductQuestionareResponse) {
			this.tierList = setTierQuestionList(tierList,retrieveProductQuestionareResponse
					.getQuestionareList());
			productSessionObject.setQuestionareList(questionareList);
			productSessionObject.setTierQuestionnaireList(tierList);
			productSessionObject
					.setAdminLevelOptionAssnId(((ProductQuestionareBO) questionareList
							.get(0)).getAdmnLvlAsscId());
			
		}
		}
		preparePanel(questionareList);
		prepareTierPanel(tierList);
		return "productQuestionnaireEdit";
	}
	/*
	 * Method to get the questionnarielIst corresponding to the selected tier 
	 */
	private List getTierQuestionList(List tierList){
		for(int i =0;i<tierList.size();i++){
			BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(i); // iterating tier definitions
			List benefitTierList = defnBo.getBenefitTiers();
			for (int k =0; k<benefitTierList.size();k++){
				BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
				if(tierBo.getBenefitTierSysId() == Integer.parseInt(panelTierSysId)){
					return tierBo.getQuestionnaireList();
				}
			}
		}
		return null;
	}
	/*
	 * Method for setting a questionnaire List to a tier definition List
	 */
	private List setTierQuestionList(List tierList, List questionList){
		for(int i =0;i<tierList.size();i++){
			BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(i); // iterating tier definitions
			List benefitTierList = defnBo.getBenefitTiers();
			for (int k =0; k<benefitTierList.size();k++){
				BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
				if(tierBo.getBenefitTierSysId() == Integer.parseInt(panelTierSysId)){
					tierBo.setQuestionnaireList(questionList);
				}
			}
		}
		return tierList;
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
					((ProductQuestionareBO) questionaireList
					.get(i)).setNotes_exists("Y");
					break;
				}else if((new Integer(i).toString()+"N").equals(tildaArray[j])){
					((ProductQuestionareBO) questionaireList
							.get(i)).setNotes_exists("N");
							break;
				}
			}
		}		
	}
	/** 
	 * @param questionaireList
	 * @param tildaArray
	 */
	private void processTierQuestionaireList(List tierList,String[] tildaArray){
		
		for(int i =0;i<tierList.size();i++){
			BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(i); // iterating tier definitions
			List benefitTierList = defnBo.getBenefitTiers();
			for (int k =0; k<benefitTierList.size();k++){
				BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
				List questionnarelist = tierBo.getQuestionnaireList();
				int size = questionnarelist.size();
				for(int j=0;j<size;j++){
					for (int m=0;m<tildaArray.length;m++){
						if((new Integer(tierBo.getBenefitTierSysId()).toString()+new Integer(j).toString()+"Y").equals(tildaArray[m])){
							((ProductQuestionareBO) questionnarelist.get(j)).setNotes_exists("Y");
							break;
						}else if((new Integer(tierBo.getBenefitTierSysId()).toString()+new Integer(j).toString()+"N").equals(tildaArray[m])){
							((ProductQuestionareBO) questionnarelist.get(j)).setNotes_exists("N");
							break;
						}
						
					}
					
				}
			}
		}
		
	}

	/**
	 * To save the over ridden administration values to the db
	 * 
	 * @return String
	 */
	public String saveBenefitAdministration() {
		
		// Create the request object from the getServiceRequest()
		 //this tildaArray will contain the information for showing note staus for unsaved questions.
	    String []  tildaTierArray = null;
		if(null!=this.tildaTierNoteStatus && !("").equals(this.tildaTierNoteStatus)){
			tildaTierArray =this.tildaTierNoteStatus.split("~");
		}
		String []  tildaArray = null;
		if(null!=this.tildaNoteStatus && !("").equals(this.tildaNoteStatus)){
		tildaArray =this.tildaNoteStatus.split("~");
		}
		UpdateProductQuestionareRequest administrationRequest = (UpdateProductQuestionareRequest) this
				.getServiceRequest(ServiceManager.UPDATE_PRODUCT_QUESTIONAIRE_REQUEST);
		int admnLvlAsscId = Integer.parseInt((String) getSession()
				.getAttribute("ADMN_LVL_ASSC_ID"));

		ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
				.getAttribute(WebConstants.PROD_TYPE);
		
		questionareList = (List)productSessionObject.getQuestionareList();
		tierList =productSessionObject.getTierQuestionnaireList();
		//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
		orgQuestionnaireList = (List)productSessionObject.getOrgQuestionnaireList();     
        orgTierList =(List)productSessionObject.getOrgTierQuestionnaireList();
        
        this.filterQuestionsForUpdate();
        
		if(null!=tildaArray && tildaArray.length>0){
			processQuestionaireList(questionareList,tildaArray);
		}
		productSessionObject.setQuestionareList(questionareList);
		tildaArray =null;
		this.tildaNoteStatus =null;
		//	      start -- code for saving notes status to the questionnare list
		if(null!=tildaTierArray && tildaTierArray.length>0){
			processTierQuestionaireList(tierList,tildaTierArray);
		}
		tildaTierArray =null;
		this.tildaTierNoteStatus =null;    
	        
		int productPrntId = productSessionObject.getProductKeyObject()
				.getParentId();
		administrationRequest.setBenefitComponentId(productSessionObject
				.getBenefitComponentId());
		administrationRequest.setQuestionnareList(productSessionObject
				.getQuestionareList());
		administrationRequest.setTierList(tierList);
		administrationRequest.setEntityId(productSessionObject
				.getProductKeyObject().getProductId());
		administrationRequest.setAdminlevelOptionSysId(admnLvlAsscId);
		administrationRequest.setMainObjectIdentifier(productSessionObject
				.getProductKeyObject().getProductName());
		administrationRequest.setVersionNumber(productSessionObject
				.getProductKeyObject().getVersion());
		administrationRequest.setDomainList(productSessionObject
				.getProductKeyObject().getBusinessDomains());
		administrationRequest.setProductPrntSysId(productSessionObject
				.getProductKeyObject().getProductId());
		administrationRequest.setBCompName((String) getSession().getAttribute(
				"BENEFIT_COMP_NAME"));
		administrationRequest.setBenefitId(productSessionObject.getBenefitId());

		//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
		administrationRequest.setNewQuestions(this.newQuestions);
        administrationRequest.setModifiedQuestions(this.modifiedQuestions);
        administrationRequest.setRemovedQuestions(this.removedQuestions);
        administrationRequest.setNewTieredQuestions(this.newTieredQuestions);
        administrationRequest.setModifiedTieredQuestions(this.modifiedTieredQuestions);
        administrationRequest.setRemovedTieredQuestions(this.removedTieredQuestions);
		
		//         administrationRequest.setBenefitId(productSessionObject.getBenefitId());
		setValuesForAdminOptionValidation(administrationRequest);
		//UpdateComponentsBenefitAdministrationResponse administrationResponse
		// = (UpdateComponentsBenefitAdministrationResponse)
		UpdateProductQuestionareResponse response = (UpdateProductQuestionareResponse) this
				.executeService(administrationRequest);
		/*
		 * UpdateProductQuestionareResponse =
		 * (UpdateProductQuestionareResponse)this.executeService(administrationRequest);
		 */
		if(administrationRequest!= null)
		storeAnswerStates(administrationRequest.getQuestionnareList());
		storeTieredAnswerStates(tierList);
		if (null != response) {
			getRequest().setAttribute("RETAIN_Value", "");
			preparePanel(questionareList);
			prepareTierPanel(tierList);
			//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
			orgQuestionnaireList = new ArrayList();
        	for(int i=0;i<questionareList.size();i++){
        		ProductQuestionareBO ptQueBO = new ProductQuestionareBO();
        		ProductQuestionareBO ptQueBO1 = (ProductQuestionareBO)questionareList.get(i);
        		ptQueBO.setQuestionnaireId( ptQueBO1.getQuestionnaireId()  );
        		ptQueBO.setParentQuestionnaireId( ptQueBO1.getParentQuestionnaireId()  );
        		ptQueBO.setSelectedAnswerid( ptQueBO1.getSelectedAnswerid()  );
        		ptQueBO.setAdminLevelOptionSysId( ptQueBO1.getAdminLevelOptionSysId()  );
        		ptQueBO.setProductSysId( ptQueBO1.getProductSysId()  );
        		ptQueBO.setBenefitComponentId( ptQueBO1.getBenefitComponentId()  );
        		ptQueBO.setSelectedAnswerDesc(  ptQueBO1.getSelectedAnswerDesc()  );
        		orgQuestionnaireList.add(ptQueBO); 
        	}	 
        	// remove not answered questions from list, before setting to session.
        	productSessionObject.setOrgQuestionnaireList(removeNotAnsweredQuestions(orgQuestionnaireList));
        	//   Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
        	orgTierList = new ArrayList();
        	if(null != tierList){
        		
        		for(int j =0;j<tierList.size();j++){
					BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(j); // iterating tier definitions
					List benefitTierList = defnBo.getBenefitTiers();
					for (int k =0; k<benefitTierList.size();k++){
						BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
						List questionList = tierBo.getQuestionnaireList();
						for(int l=0;l<questionList.size();l++){
							ProductQuestionareBO productQuestionareBO = new ProductQuestionareBO();
							ProductQuestionareBO productQuestionareBO1 = new ProductQuestionareBO();
							productQuestionareBO1 = (ProductQuestionareBO)questionList.get(l);
							productQuestionareBO.setQuestionnaireId( productQuestionareBO1.getQuestionnaireId()  );
							productQuestionareBO.setParentQuestionnaireId( productQuestionareBO1.getParentQuestionnaireId()  );
							productQuestionareBO.setSelectedAnswerid( productQuestionareBO1.getSelectedAnswerid()  );
							productQuestionareBO.setTierSysId( productQuestionareBO1.getTierSysId()  );
							productQuestionareBO.setAdminLevelOptionSysId( productQuestionareBO1.getAdminLevelOptionSysId()  );
							productQuestionareBO.setProductSysId( productQuestionareBO1.getProductSysId()  );
							productQuestionareBO.setBenefitComponentId( productQuestionareBO1.getBenefitComponentId()  );
							productQuestionareBO.setSelectedAnswerDesc( productQuestionareBO1.getSelectedAnswerDesc() );
							orgTierList.add(productQuestionareBO);   
						}
					}
				}
        	
        	}
        	productSessionObject.setOrgTierQuestionnaireList(removeNotAnsweredQuestions(orgTierList));       	
        	return "productQuestionnaireEdit";
		}
		return "";
	}
	
	/**
     * Method to compare the original questionnaire & modified one.
     * 6 questionnaire lists will be prepared
     * 
     */
    
    private void filterQuestionsForUpdate(){ 
    	
    	Logger.logDebug("inside filterQuestionsForUpdate()");
    	//	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
    	ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
		.getAttribute(WebConstants.PROD_TYPE);
    	questionareList = (List)productSessionObject.getQuestionareList();	      
	    orgQuestionnaireList = (List)productSessionObject.getOrgQuestionnaireList(); 
	    
	    newQuestions = new ArrayList(); 
	    modifiedQuestions = new ArrayList();
	    removedQuestions = new ArrayList();
	    
	    //Logic for non-tiered admin options
	    //for(int i=0;i<questionnaireList.size();i++){
	    Iterator it = questionareList.iterator();
	    while(it.hasNext()){
	    		ProductQuestionareBO productQuestionareBO = (ProductQuestionareBO)it.next();
	    		if(!"Not Answered".equals(productQuestionareBO.getSelectedAnswerDesc().trim() ) && 
		    			!"".equals( productQuestionareBO.getSelectedAnswerDesc().trim() )){
		    	int a=0;
		    	
		    	Iterator it1 = orgQuestionnaireList.iterator();
		    	while(it1.hasNext()){
		    		ProductQuestionareBO productQuestionareBO1 = 
			    		(ProductQuestionareBO)it1.next(); 
		    		    		if(productQuestionareBO.getQuestionnaireId() == productQuestionareBO1.getQuestionnaireId()){
						    		a++; 
						    		it1.remove();
						    		if(productQuestionareBO.getSelectedAnswerid() != productQuestionareBO1.getSelectedAnswerid()){
							    		modifiedQuestions.add(productQuestionareBO);
							    	}
					    		} 
		    	}
		    	
		    	if(a == 0){ 
		    		newQuestions.add(productQuestionareBO);
		    	}
		    	}//no need to compare not answered questions 
	    }
	    
	      removedQuestions = orgQuestionnaireList;
	    
	    tierList    = (List)productSessionObject.getTierQuestionnaireList();
	    orgTierList = (List)productSessionObject.getOrgTierQuestionnaireList();
	    
	    newTieredQuestions      = new ArrayList();
	    modifiedTieredQuestions = new ArrayList();
	    removedTieredQuestions  = new ArrayList();
	    
	    //Prepare Tiered questionnaireList to filter
	    ArrayList tierListToFilter = new ArrayList();
    	if(null != tierList){
    		
        		for(int j =0;j<tierList.size();j++){
					BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(j); // iterating tier definitions
					List benefitTierList = defnBo.getBenefitTiers();
					for (int k =0; k<benefitTierList.size();k++){
						BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
						List questionList = tierBo.getQuestionnaireList();
						for(int l=0;l<questionList.size();l++){
							ProductQuestionareBO productQuestionareBO = new ProductQuestionareBO();
							ProductQuestionareBO bo1 = new ProductQuestionareBO();
							bo1 = (ProductQuestionareBO)questionList.get(l);
							productQuestionareBO.setQuestionnaireId( bo1.getQuestionnaireId()  );
							productQuestionareBO.setQuestionId( bo1.getQuestionId()  );
							productQuestionareBO.setAdminLevelOptionSysId( bo1.getAdminLevelOptionSysId()  );
							productQuestionareBO.setProductSysId( bo1.getProductSysId()  );
							productQuestionareBO.setBenefitComponentId( bo1.getBenefitComponentId()  );
							productQuestionareBO.setParentQuestionnaireId( bo1.getParentQuestionnaireId()  );
							productQuestionareBO.setSelectedAnswerid( bo1.getSelectedAnswerid()  );
							productQuestionareBO.setSelectedAnswerDesc( bo1.getSelectedAnswerDesc()  );
							productQuestionareBO.setTierSysId( bo1.getTierSysId()  );
														
							productQuestionareBO.setBenefitId( bo1.getBenefitId());
							productQuestionareBO.setBenefitName(bo1.getBenefitName());
							productQuestionareBO.setBenefitDefenitionId(bo1.getBenefitDefenitionId());
							productQuestionareBO.setChildCount(bo1.getChildCount());
							productQuestionareBO.setLevel(bo1.getLevel());
							productQuestionareBO.setNotes_exists(bo1.getNotes_exists());				
							productQuestionareBO.setPossibleAnswerList(bo1.getPossibleAnswerList());
							productQuestionareBO.setQuestionName(bo1.getQuestionName());
							productQuestionareBO.setQuestionOrder(bo1.getQuestionOrder());
							productQuestionareBO.setReferenceDesc(bo1.getReferenceDesc());
							productQuestionareBO.setReferenceId(bo1.getReferenceId());
							productQuestionareBO.setValidDomainToAttach(bo1.getValidDomainToAttach());
							tierListToFilter.add(productQuestionareBO); 
						}
					}
				}
        	
    	}
    	//  End : Prepare Tiered questionnaireList to filter
    	
	    //	  Logic for tiered admin options
	    for(int m=0;m<tierListToFilter.size();m++){
	    	ProductQuestionareBO productQuestionareBO4 = new ProductQuestionareBO();
	    	productQuestionareBO4 = (ProductQuestionareBO)tierListToFilter.get(m); 
	    	if(!"Not Answered".equals(productQuestionareBO4.getSelectedAnswerDesc().trim() ) && 
		    			!"".equals( productQuestionareBO4.getSelectedAnswerDesc().trim() )){
			    	int c=0;
			    				    	
			    	Iterator it2 = orgTierList.iterator();
			    	while(it2.hasNext()){
			    		ProductQuestionareBO productQuestionareBO5 = 
				    		(ProductQuestionareBO)it2.next(); 
			    		if((productQuestionareBO4.getQuestionnaireId() == productQuestionareBO5.getQuestionnaireId()) && 
				    			(productQuestionareBO4.getTierSysId() == productQuestionareBO5.getTierSysId())  ){
			    			c++;
				    		it2.remove();
				    		if(productQuestionareBO4.getSelectedAnswerid() != productQuestionareBO5.getSelectedAnswerid()){
					    		modifiedTieredQuestions.add(productQuestionareBO4);
					    	}
				    	}
			    	}
			    	
			    	if(c == 0){
			    		newTieredQuestions.add(productQuestionareBO4);
			    	}
		    	}// if not ansrd
	    }
	    removedTieredQuestions = orgTierList;
	    
	}
    
    /**
     * Method to remove not-answered questions from the given list
     * @param req
     */
    public List removeNotAnsweredQuestions(List questionnaireList){
    	if(null != questionnaireList){
    		Iterator it = questionnaireList.iterator();
    		while(it.hasNext()){
    			ProductQuestionareBO bo1 = (ProductQuestionareBO)it.next();
    			if( "Not Answered".equals( bo1.getSelectedAnswerDesc().trim() ) || 
    					"".equals( bo1.getSelectedAnswerDesc().trim() ) ){
		    		it.remove();
		    	} 
    		}
    		
    	}
    	
    	return questionnaireList;
    }
    
	private RetrieveProductQuestionareRequest getRetrieveProductQuestionareRequest() {

		RetrieveProductQuestionareRequest retrieveProductQuestionareRequest = new RetrieveProductQuestionareRequest();
		ProductSessionObject productSessionObject = (ProductSessionObject) getSession()
				.getAttribute(WebConstants.PROD_TYPE);

		retrieveProductQuestionareRequest.setProductSysId(productSessionObject
				.getProductKeyObject().getProductId());
		retrieveProductQuestionareRequest
				.setBenefitComponentSysId(productSessionObject
						.getBenefitComponentId());
		retrieveProductQuestionareRequest
		.setBenefitId(productSessionObject
				.getBenefitId());
		retrieveProductQuestionareRequest
				.setAdmnLvlOptionAsscId(productSessionObject
						.getAdminLevelOptionAssnId());
		retrieveProductQuestionareRequest
				.setAdminOptionSysId(productSessionObject.getAdminOptionId());
		//		 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
		retrieveProductQuestionareRequest
				.setBenftAdminSysId(productSessionObject.getBenefitAdminId());
		retrieveProductQuestionareRequest
				.setProductPrntSysId(productSessionObject.getProductKeyObject().getParentId());
		return retrieveProductQuestionareRequest;
	}

	/**
	 * @return Returns the panel.
	 */
	public void preparePanel(List questionnareList) {
		StringBuffer line = null;
		String finalline = null;
		questionarePanel = new HtmlPanelGrid();
		questionarePanel.setWidth("100%");
		questionarePanel.setColumns(4);
		questionarePanel.setBorder(0);
		questionarePanel.setStyleClass("outputText");
		questionarePanel.setCellpadding("3");
		questionarePanel.setCellspacing("1");
		questionarePanel.setBgcolor("#cccccc");
		StringBuffer rowClass = new StringBuffer();
		if (questionnareList != null) {
			HtmlSelectOneMenu answerSelectOneMenu = null;
			HtmlOutputText questionOutputText = null;
			HtmlOutputText referenceOutputText = null;
			HtmlInputHidden childCountHidden = null;
			HtmlPanelGroup referenceGroup = null;
			HtmlGraphicImage notesAttachmentImage =null;
			 HtmlOutputText notesHidden = null;
			if (this.questionsStates == null
					|| "".equalsIgnoreCase(this.questionsStates))
				storeAnswerStates(questionnareList);
			for (int i = 0; i < questionnareList.size(); i++) {
				ProductQuestionareBO productQuestionareBO = (ProductQuestionareBO) questionnareList
						.get(i);
				questionOutputText = new HtmlOutputText();
				referenceOutputText = new HtmlOutputText();
				childCountHidden = new HtmlInputHidden();
				referenceGroup = new HtmlPanelGroup();
				notesAttachmentImage = new HtmlGraphicImage();
				notesHidden = new HtmlOutputText();
            	notesHidden.setId("notesHidden"+i); 
				if (i > 0) {
					rowClass.append(",");
				}
				int level = productQuestionareBO.getLevel();
				if (level > 1) {
					finalline = getLevelPrefix(level);
					questionOutputText.setValue(finalline
							+ productQuestionareBO.getQuestionName());
					rowClass.append("dataTableOddRow");
				} else {
					questionOutputText.setValue(productQuestionareBO
							.getQuestionName());
					rowClass.append("dataTableEvenRow");
				}
				childCountHidden.setId("childCount" + i);
				childCountHidden.setValue(new Integer(productQuestionareBO
						.getChildCount()));
				List answerList = productQuestionareBO.getPossibleAnswerList();
				List possibleAnswersList = (List) getPossibleAnswersListForAQuestion(answerList);

				answerSelectOneMenu = new HtmlSelectOneMenu();
				answerSelectOneMenu.setId("selectitem" + i);
				UISelectItems uis = new UISelectItems();
				uis.setValue(possibleAnswersList);

				answerSelectOneMenu.setValue(new Integer(productQuestionareBO
						.getSelectedAnswerid()).toString());
				answerSelectOneMenu.setStyleClass("formInputList");
				answerSelectOneMenu
						.setStyleClass("formInputFieldBenefitStructure");
				answerSelectOneMenu.getChildren().add(uis);

				answerSelectOneMenu.setOnchange("return loadNewChild(this)");
				answerSelectOneMenu.setOnfocus("return setCurrentValue(this)");
				referenceOutputText.setId("refere" + i);
				referenceOutputText.setValue(productQuestionareBO
						.getReferenceDesc());
				referenceGroup.getChildren().add(referenceOutputText);
				referenceGroup.getChildren().add(childCountHidden);
				 
//				start notes
				ProductSessionObject productSessionObject = (ProductSessionObject) getSession().getAttribute(WebConstants.PROD_TYPE);
				int adminOptionLevelSysId = productSessionObject.getAdminLevelOptionAssnId();
				int productKey = getProductKeyFromSession();
				int bcId = getBenefitComponentIdFromSession();
				String imageid= new Integer(i).toString();
				if(i==0){
				this.benefitDefnId=productQuestionareBO.getBenefitDefenitionId();
				this.benefitComponentID=bcId;
				}
				String primaryType= "ATTACHPRODUCT";
				if(productQuestionareBO.getNotes_exists().equals("Y")){
				notesAttachmentImage.setUrl("../../images/notes_exist.gif");
				}else{
					notesAttachmentImage.setUrl("../../images/page.gif");
				} 
				notesAttachmentImage.setId("notesAttachmentImage"+ i);
				notesAttachmentImage.setStyle("cursor:hand;");
                notesAttachmentImage.setOnclick("loadNotesPopup('../product/productQuestionNotesPopup.jsp','"
                                                    + productQuestionareBO.getQuestionId()
                                                    + "','"
                                                    + productKey
                                                    + "','"
                                                    + primaryType
                                                    + "','"
													+bcId
													+ "','"
													+benefitDefnId
													+ "','"
													+adminOptionLevelSysId
													+"','"
													+"notesAttachmentImage" +i+"','"+i
            										 +"');return false;");                            
                                            
				//end of notes
				questionarePanel.getChildren().add(questionOutputText);
				questionarePanel.getChildren().add(answerSelectOneMenu);
				questionarePanel.getChildren().add(referenceGroup);
				
				if ("Y".equals(productQuestionareBO
						.getValidDomainToAttach())) {
					questionarePanel.getChildren().add(notesAttachmentImage);
				} else {
					questionarePanel.getChildren().add(notesHidden);
				}

				//questionarePanel.getChildren().add(notesAttachmentImage);

			}
		}
		questionarePanel.setRowClasses(rowClass.toString());

	}
	
	public void prepareTierPanel(List tierList){
		if(null != tierList && tierList.size()>0){
			
			sortTiers(tierList);
			tierQuestionarePanel = new HtmlPanelGrid();
			tierQuestionarePanel.setColumns(1);
			tierQuestionarePanel.setWidth("100%");
			tierQuestionarePanel.setBorder(0);
			tierQuestionarePanel.setCellpadding("0");
			tierQuestionarePanel.setCellspacing("0");
			HtmlPanelGrid tierDefPanel = null;
			HtmlPanelGrid questionpanel = null;
			// iterating definition  
			for (int i=0; i<tierList.size();i++){
				BenefitTierDefinition tierDefinition = (BenefitTierDefinition)tierList.get(i);
				tierDefPanel = new HtmlPanelGrid();
				tierDefPanel.setColumns(1);
				tierDefPanel.setWidth("100%");
				tierDefPanel.setBorder(0);
				tierDefPanel.setCellpadding("3");
				tierDefPanel.setCellspacing("1");
				if(!isPrintMode()){
				    tierDefPanel.setBgcolor("#cccccc");
				}
				
				HtmlOutputLabel defLabel = new HtmlOutputLabel();
				defLabel.setId("defLabel"+i);
				HtmlOutputText tierDef = new HtmlOutputText();
				tierDef.setValue(tierDefinition.getBenefitTierDefinitionName());
				if(!isPrintMode()){
				    tierDef.setStyle("color:blue");
				}				
				defLabel.getChildren().add(tierDef);
				tierDefPanel.getChildren().add(defLabel);
				tierQuestionarePanel.getChildren().add(tierDefPanel);
				List benefitTierList = tierDefinition.getBenefitTiers();
				// iterating the tiers
				if(null !=benefitTierList){
					if (this.tieredQuestionsStates == null
							|| "".equalsIgnoreCase(this.tieredQuestionsStates))
						storeTieredAnswerStates(tierList);
					for(int j=0;j<benefitTierList.size();j++){
						BenefitTier tierBO=(BenefitTier)benefitTierList.get(j);
						
						tierDefPanel = new HtmlPanelGrid();
						tierDefPanel.setColumns(1);
						tierDefPanel.setWidth("100%");
						tierDefPanel.setBorder(0);
						tierDefPanel.setCellpadding("3");
						tierDefPanel.setCellspacing("1");
						if(!isPrintMode()){
						    tierDefPanel.setBgcolor("#ffffff");
						}
						List criteriaList = tierBO.getBenefitTierCriteriaList();
						HtmlOutputLabel criteriaLabel = new HtmlOutputLabel();
						criteriaLabel.setId("criteriaLabel"+i+j);
						HtmlOutputText criteriaValues = new HtmlOutputText();
						StringBuffer criteriaStr = new StringBuffer();
						if(null!=criteriaList){
							// iterating the criteria
							for(int k=0;k<criteriaList.size();k++){
								BenefitTierCriteria criteriaBO = (BenefitTierCriteria)criteriaList.get(k);
								criteriaStr =criteriaStr.append(criteriaBO.getBenefitTierCriteriaName()).append(" : ").append(criteriaBO.getBenefitTierCriteriaValue()).append("  ");
							}
						}
						criteriaValues.setValue(criteriaStr.toString());
						if(!isPrintMode()){
						    criteriaValues.setStyle("color:blue");
						}
						criteriaLabel.getChildren().add(criteriaValues);
						tierDefPanel.getChildren().add(criteriaLabel);
						tierQuestionarePanel.getChildren().add(tierDefPanel);
						
						List tierQuestionList =tierBO.getQuestionnaireList();
						String finalline = null;
						questionpanel = new HtmlPanelGrid();
						questionpanel.setWidth("100%");		
						
						if(isPrintMode()){
						    questionpanel.setColumns(3); 
						    questionpanel.setColumnClasses("gridColumn40,gridColumn20,gridColumn40");							    	
						}else{
						    questionpanel.setColumns(4); 
						    questionpanel.setColumnClasses("gridColumn40,gridColumn20,gridColumn30,gridColumn10");
						}																	
						questionpanel.setBorder(0);
						questionpanel.setStyleClass("outputText");
						questionpanel.setCellpadding("3");
						questionpanel.setCellspacing("1");
						if(!isPrintMode()){
						    questionpanel.setBgcolor("#cccccc");
						}
						StringBuffer rowClass = new StringBuffer();
						if(null != tierQuestionList){							
							HtmlSelectOneMenu answerSelectOneMenu = null;
							HtmlOutputText questionOutputText = null;
							HtmlOutputText referenceOutputText = null;
							HtmlOutputText answerOutputText = null;
							HtmlInputHidden childCountHidden = null;
							HtmlPanelGroup referenceGroup = null;
							HtmlGraphicImage notesAttachmentImage =null;
							HtmlOutputText notesHidden = null;
							
							// iterating questions in tier
							for(int q=0;q<tierQuestionList.size();q++){
								ProductQuestionareBO productQuestionareBO = (ProductQuestionareBO) tierQuestionList
								.get(q);
								questionOutputText = new HtmlOutputText();
								referenceOutputText = new HtmlOutputText();
								childCountHidden = new HtmlInputHidden();
								referenceGroup = new HtmlPanelGroup();
								notesAttachmentImage = new HtmlGraphicImage();
								notesHidden = new HtmlOutputText();
				            	notesHidden.setId("notesHidden"+q+"tier"+productQuestionareBO.getTierSysId()); 
								if (q > 0) {
									rowClass.append(",");
								}
								int level = productQuestionareBO.getLevel();
								if (level > 1) {
									finalline = getLevelPrefix(level);
									questionOutputText.setValue(finalline
											+ productQuestionareBO.getQuestionName());
									rowClass.append("dataTableOddRow");
								} else {
									questionOutputText.setValue(productQuestionareBO
											.getQuestionName());
									if(!isPrintMode()){
									    rowClass.append("dataTableEvenRow");
									}
									
								}
								
								childCountHidden.setId("childCount" + q+"tier"+productQuestionareBO.getTierSysId());
								childCountHidden.setValue(new Integer(productQuestionareBO
										.getChildCount()));
								List answerList = productQuestionareBO.getPossibleAnswerList();
								List possibleAnswersList = (List) getPossibleAnswersListForAQuestion(answerList);
								String selectedAnswer = productQuestionareBO.getSelectedAnswerDesc();								
								if(super.isViewMode() || isPrintMode()){
									answerOutputText = new HtmlOutputText();
									answerOutputText.setValue(selectedAnswer);			
								}else{
									answerSelectOneMenu = new HtmlSelectOneMenu();
									answerSelectOneMenu.setId("selectitem" + q+"tier"+productQuestionareBO.getTierSysId());						
									UISelectItems uis = new UISelectItems();
									uis.setValue(possibleAnswersList);
									answerSelectOneMenu.getChildren().add(uis);		
									answerSelectOneMenu.setValue(new Integer(productQuestionareBO
											.getSelectedAnswerid()).toString());									
									answerSelectOneMenu.setStyleClass("formInputList");									
									answerSelectOneMenu
											.setStyleClass("formInputFieldBenefitStructure");
									answerSelectOneMenu.setOnfocus("return setCurrentValue(this)");
									answerSelectOneMenu.setOnchange("return loadNewChildTier(this)");									
								}			
								referenceOutputText.setId("refere" + q+"tier"+productQuestionareBO.getTierSysId());
								referenceOutputText.setValue(productQuestionareBO
										.getReferenceDesc());
								referenceGroup.getChildren().add(referenceOutputText);
								referenceGroup.getChildren().add(childCountHidden);
								 
		//						start notes
								ProductSessionObject productSessionObject = (ProductSessionObject) getSession().getAttribute(WebConstants.PROD_TYPE);
								int adminOptionLevelSysId = productSessionObject.getAdminLevelOptionAssnId();
								int productKey = getProductKeyFromSession();
								int bcId = getBenefitComponentIdFromSession();
								String imageid= new Integer(i).toString();
								if(q==0){
								this.benefitDefnId=productQuestionareBO.getBenefitDefenitionId();
								this.benefitComponentID=bcId;
								}
							//	int benefitdfnId = productQuestionareBO.getBenefitDefenitionId();
								String primaryType= "ATTACHPRODUCT";
								if(productQuestionareBO.getNotes_exists().equals("Y")){
								notesAttachmentImage.setUrl("../../images/notes_exist.gif");
								}else{
									notesAttachmentImage.setUrl("../../images/page.gif");
								} 
								notesAttachmentImage.setId("notesAttachmentImage"+ q+"tier"+productQuestionareBO.getTierSysId());
								notesAttachmentImage.setStyle("cursor:hand;");
								if(super.isViewMode()){
									 notesAttachmentImage.setOnclick("loadTierNotes('../product/productQuestionTierNotesViewPopup.jsp','"
        									+productQuestionareBO.getTierSysId()
											+ "','"
                                            + productQuestionareBO.getQuestionId()
                                            + "','"
                                            + productKey
                                            + "','"
                                            + primaryType
                                            + "','"
											+bcId
											+ "','"
											+benefitDefnId
											+ "','"
											+adminOptionLevelSysId
											+"','"
											+"notesAttachmentImage" +q+"','"+q
    										 +"');return false;");       			
								}else{
				                notesAttachmentImage.setOnclick("loadTierNotes('../product/productQuestionTierNotesPopup.jsp','"
				                									+productQuestionareBO.getTierSysId()
																	+ "','"
				                                                    + productQuestionareBO.getQuestionId()
				                                                    + "','"
				                                                    + productKey
				                                                    + "','"
				                                                    + primaryType
				                                                    + "','"
																	+bcId
																	+ "','"
																	+benefitDefnId
																	+ "','"
																	+adminOptionLevelSysId
																	+"','"
																	+"notesAttachmentImage" +q+"','"+q
				            										 +"');return false;");                            
								}                            
								//end of notes
				                questionpanel.getChildren().add(questionOutputText);
				                if(super.isViewMode() || isPrintMode()){
				                	questionpanel.getChildren().add(answerOutputText);
				                }else{
				                	questionpanel.getChildren().add(answerSelectOneMenu);
				                }
				                questionpanel.getChildren().add(referenceGroup);
								
								if ("Y".equals(productQuestionareBO
										.getValidDomainToAttach()) && !isPrintMode()) {
									questionpanel.getChildren().add(notesAttachmentImage);
								}else if (!"Y".equals(productQuestionareBO
										.getValidDomainToAttach()) && !isPrintMode()) {
								    questionpanel.getChildren().add(notesHidden);
								}	
							}
							questionpanel.setRowClasses(rowClass.toString());
							tierQuestionarePanel.getChildren().add(questionpanel);
						}
					}
				}
			}
		}else{
			tierQuestionarePanel= null;
		}
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
		   //		 removing the tiers which does not have any questions 
		   for (Iterator iterDef = benefitDefinitonsList.iterator(); iterDef.hasNext();) {
			BenefitTierDefinition tierDef = (BenefitTierDefinition) iterDef.next();
			if(null !=tierDef){
				 List tierList = tierDef.getBenefitTiers();
				 for (int i=0; i<tierList.size();i++){
				 	BenefitTier tierBo = (BenefitTier)tierList.get(i);
				 	if (null !=tierBo &&( null ==tierBo.getQuestionnaireList() || tierBo.getQuestionnaireList().size() ==0)){
				 		tierList.remove(tierBo);
				 		i--;
				 	}
				 }
			}	
			
		   }
		   //removing the tierDefinitions which doesnothave any tiers
		   for (int i=0;i<benefitDefinitonsList.size();i++) {
			BenefitTierDefinition tierDef = (BenefitTierDefinition) benefitDefinitonsList.get(i);
				if(null !=tierDef && (null ==tierDef.getBenefitTiers() || tierDef.getBenefitTiers().size() ==0)){
					benefitDefinitonsList.remove(tierDef);
					i--;
				}
		   }
		   
		}
	}
	/**
	 * @param benefitAdminList2
	 */
	private void storeAnswerStates(List list) {
		if (null != list && list.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			for (Iterator i = list.iterator(); i.hasNext();) {
				ProductQuestionareBO productQuestionareBO = (ProductQuestionareBO) i
						.next();
				buffer.append(productQuestionareBO.getQuestionId());
				buffer.append("~");
				buffer.append(productQuestionareBO.getSelectedAnswerid());
				if (i.hasNext())
					buffer.append(":");
			}
			questionsStates = buffer.toString();
		}
	}
	
	private void storeTieredAnswerStates(List tierList) {
		if(null!=tierList && tierList.size() >0){
			for(int i =0;i<tierList.size();i++){
				BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(i); // iterating tier definitions
				List benefitTierList = defnBo.getBenefitTiers();
				for (int k =0; k<benefitTierList.size();k++){
					BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
					List list = tierBo.getQuestionnaireList();
					if (null != list && list.size() > 0) {
						StringBuffer buffer = new StringBuffer();
						for (Iterator l = list.iterator(); l.hasNext();) {
							ProductQuestionareBO productQuestionareBO = (ProductQuestionareBO) l
									.next();
							buffer.append(productQuestionareBO.getTierSysId());
							buffer.append("-");
							buffer.append(productQuestionareBO.getQuestionId());
							buffer.append("~");
							buffer.append(productQuestionareBO.getSelectedAnswerid());
							if (l.hasNext())
								buffer.append(":");
						}
						tieredQuestionsStates = buffer.toString();
					}
				}
			}
		}
	}
	
	
	private Map loadAnswerStates() {
		Map map = new HashMap();
		if (null != questionsStates && !"".equals(questionsStates.trim())) {
			String[] qstnAsnwers = questionsStates.split(":");
			for (int i = 0; i < qstnAsnwers.length; i++) {
				String[] qstnAnswer = qstnAsnwers[i].split("~");
				map.put(qstnAnswer[0], qstnAnswer[1]);
			}
		}
		return map;
	}

//	/**
//	 * @param benefitAdminList2
//	 */
//	private void storeTierAnswerStates(List list) {
//		if (null != list && list.size() > 0) {
//			StringBuffer buffer = new StringBuffer();
//			for (Iterator i = list.iterator(); i.hasNext();) {
//				ProductQuestionareBO productQuestionareBO = (ProductQuestionareBO) i
//						.next();
//				buffer.append(productQuestionareBO.getQuestionId());
//				buffer.append("~");
//				buffer.append(productQuestionareBO.getSelectedAnswerid());
//				if (i.hasNext())
//					buffer.append(":");
//			}
//			questionsStates = buffer.toString();
//		}
//	}
//
//	private Map loadTierAnswerStates() {
//		Map map = new HashMap();
//		if (null != questionsStates && !"".equals(questionsStates.trim())) {
//			String[] qstnAsnwers = questionsStates.split(":");
//			for (int i = 0; i < qstnAsnwers.length; i++) {
//				String[] qstnAnswer = qstnAsnwers[i].split("~");
//				map.put(qstnAnswer[0], qstnAnswer[1]);
//			}
//		}
//		return map;
//	}
	/*
	 * 
	 * this method for creating level prefixx
	 *  
	 */
	public String getLevelPrefix(int level) {

		StringBuffer buffer = new StringBuffer("");

		for (int i = 1; i < level; i++) {

			buffer.append(" - ");

		}

		return buffer.toString();
	}

	/**
	 * @return Returns the headerPanel.
	 */
	public HtmlPanelGrid getHeaderPanel() {

		//String mode =
		// getBenefitComponentSessionObject().getBenefitComponentMode();

		headerPanel = new HtmlPanelGrid();
		headerPanel.setWidth("100%");
		headerPanel.setColumns(4);
		headerPanel.setColumnClasses("gridColumn40,gridColumn20,gridColumn30,gridColumn10");	
		headerPanel.setBorder(0);
		headerPanel.setCellpadding("3");
		headerPanel.setCellspacing("1");
		headerPanel.setBgcolor("#cccccc");
		headerPanel.setStyleClass("dataTableHeader");

		HtmlOutputText questionText = new HtmlOutputText();
		HtmlOutputText answerText = new HtmlOutputText();
		HtmlOutputText referenceTest = new HtmlOutputText();
		HtmlOutputText noteColumn = new HtmlOutputText();

		questionText.setValue("Question");
		questionText.setId("QuestionTier");

		answerText.setValue("Answer");
		answerText.setId("AnswerTier");

		referenceTest.setValue("Reference");
		referenceTest.setId("ReferenceTier");
		
		noteColumn.setValue("Notes");
		noteColumn.setId("noteIdTier");

		headerPanel.getChildren().add(questionText);
		headerPanel.getChildren().add(answerText);
		headerPanel.getChildren().add(referenceTest);
		headerPanel.getChildren().add(noteColumn);

		return headerPanel;
	}

	
	/**
	 * @return Returns the teirHeaderPanel.
	 */
	public HtmlPanelGrid getTierHeaderPanel() {
		
		tierHeaderPanel = new HtmlPanelGrid();
		tierHeaderPanel.setWidth("100%");
		tierHeaderPanel.setColumns(4);	
		tierHeaderPanel.setColumnClasses("gridColumn40,gridColumn20,gridColumn30,gridColumn10");	
		tierHeaderPanel.setBorder(0);
		tierHeaderPanel.setCellpadding("3");
		tierHeaderPanel.setCellspacing("1");
		tierHeaderPanel.setBgcolor("#cccccc");
		tierHeaderPanel.setStyleClass("dataTableHeader");

		HtmlOutputText questionText = new HtmlOutputText();		
		HtmlOutputText answerText = new HtmlOutputText();		
		HtmlOutputText referenceTest = new HtmlOutputText();		
		HtmlOutputText noteColumn = new HtmlOutputText();
 
		questionText.setValue("Question");
		questionText.setId("Question");

		answerText.setValue("Answer");
		answerText.setId("Answer");

		referenceTest.setValue("Reference");
		referenceTest.setId("Reference");
		
		noteColumn.setValue("Notes");
		noteColumn.setId("noteId");

		tierHeaderPanel.getChildren().add(questionText);
		tierHeaderPanel.getChildren().add(answerText);
		tierHeaderPanel.getChildren().add(referenceTest);		
		tierHeaderPanel.getChildren().add(noteColumn);			
		return tierHeaderPanel;
	}
	/**
	 * @param teirHeaderPanel The teirHeaderPanel to set.
	 */
	public void setTierHeaderPanel(HtmlPanelGrid tierHeaderPanel) {
		this.tierHeaderPanel = tierHeaderPanel;
	}
	/**
	 * this method returns the panel when we give a list of benfit admin
	 * 
	 * @param benefitAdminList
	 * @param viewBool
	 * @return
	 */

	public HtmlPanelGrid getPanelFromList(List benefitAdminList,
			boolean viewBool) {
		//final String DELETE_IMAGE_PATH = "../../images/delete.gif";
		String benefitComName = (String) getSession().getAttribute(
				"BENEFIT_COMP_NAME");
		panel = new HtmlPanelGrid();
		storeAsnwerStates(benefitAdminList);
		StringBuffer rowClass = new StringBuffer();
		panel.setWidth(WebConstants.PANEL_WIDTH);
		if (!viewBool)
			panel.setColumns(5);
		else {
			panel.setColumns(4);
			panel.setWidth("875");
		}
		panel.setBorder(0);
		panel.setStyleClass("outputText");
		panel.setCellpadding("3");
		panel.setCellspacing("1");
		panel.setBgcolor("#cccccc");

		if (benefitAdminList != null) {
			HtmlOutputText htmlOutputAnswer = null;
			HtmlSelectOneMenu htmlSelectOneMenu = null;
			for (int i = 0; i < benefitAdminList.size(); i++) {

				EntityBenefitAdministration benefitAdministration = (EntityBenefitAdministration) benefitAdminList
						.get(i);
				//sets question description
				HtmlOutputText htmlOutputText1 = new HtmlOutputText();
				//               htmlOutputText1.setStyleClass("mandatoryNormal");
				htmlOutputText1.setId("question" + i);
				htmlOutputText1.setValue(benefitAdministration
						.getQuestionDesc());

				HtmlInputHidden inputHiddenForAdminAssc = new HtmlInputHidden();
				inputHiddenForAdminAssc.setValue(new Integer(
						benefitAdministration.getAnsOvrdCustmzdSysId()));
				inputHiddenForAdminAssc.setId("inputHiddenForAdminAssc" + i);
				ValueBinding adminAssc = FacesContext.getCurrentInstance()
						.getApplication().createValueBinding(
								"#{productBenefitAdministrationBackingBean.datafieldMapForAdminAsscId["
										+ i + "]}");
				inputHiddenForAdminAssc.setValueBinding("value", adminAssc);

				HtmlInputHidden htmlInputHiddenForQuestionId = new HtmlInputHidden();
				htmlInputHiddenForQuestionId.setValue(new Integer(
						benefitAdministration.getQuestionNumber()));
				htmlInputHiddenForQuestionId
						.setId("htmlInputHiddenForQuestionId" + i);
				ValueBinding quesItem = FacesContext.getCurrentInstance()
						.getApplication().createValueBinding(
								"#{productBenefitAdministrationBackingBean.datafieldMapForQuestionId["
										+ i + "]}");
				htmlInputHiddenForQuestionId.setValueBinding("value", quesItem);

				//sets the data type
				HtmlOutputText htmlOutputText2 = new HtmlOutputText();
				//               htmlOutputText2.setStyleClass("mandatoryNormal");
				htmlOutputText2.setId("type" + i);
				//htmlOutputText2.setValue(benefitAdministration.getDataTypeDesc());
				htmlOutputText2.setValue(benefitAdministration
						.getDataTypeLegend());

				HtmlInputHidden htmlInputHiddenForAnswerId = new HtmlInputHidden();
				htmlInputHiddenForAnswerId.setValue(new Integer(
						benefitAdministration.getAnswerId()));
				htmlInputHiddenForAnswerId.setId("htmlInputHiddenForAnswerId"
						+ i);
				ValueBinding answerItem = FacesContext.getCurrentInstance()
						.getApplication().createValueBinding(
								"#{productBenefitAdministrationBackingBean.datafieldMapForAnswerId["
										+ i + "]}");
				htmlInputHiddenForAnswerId.setValueBinding("value", answerItem);

				List items = benefitAdministration.getAnswers();

				//checks whether the view mode
				if (viewBool) {
					String answerDescription = (String) getSelectedAnswerDescFromPossibleAnswersList(
							items, benefitAdministration.getAnswerId());

					htmlOutputAnswer = new HtmlOutputText();
					//                    htmlOutputAnswer.setStyleClass("mandatoryNormal");
					htmlOutputAnswer.setId("ansdesc" + i);
					if (null != answerDescription)
						htmlOutputAnswer.setValue(answerDescription);
				} else {

					// Group the answers if any
					List possibleAnswersList = (List) getPossibleAnswersListForAQuestion(items);

					htmlSelectOneMenu = new HtmlSelectOneMenu();
					htmlSelectOneMenu.setId("selectitem" + i);
					UISelectItems uis = new UISelectItems();
					uis.setValue(possibleAnswersList);

					htmlSelectOneMenu.setValue(new Integer(
							benefitAdministration.getAnswerId()).toString());
					htmlSelectOneMenu.setStyleClass("formInputList");
					htmlSelectOneMenu
							.setStyleClass("formInputFieldBenefitStructure");
					htmlSelectOneMenu.getChildren().add(uis);
					ValueBinding ansItem = FacesContext.getCurrentInstance()
							.getApplication().createValueBinding(
									"#{productBenefitAdministrationBackingBean.datafieldMap["
											+ i + "]}");
					htmlSelectOneMenu.setValueBinding("value", ansItem);
				}
				HtmlOutputText htmlOutputText = new HtmlOutputText();
				htmlOutputText.setId("reference" + i);
				htmlOutputText.setValue(benefitAdministration
						.getReferenceDesc());
				//               htmlOutputText.setStyleClass("mandatoryNormal");
				HtmlOutputLabel htmlOutputLabel = new HtmlOutputLabel();
				htmlOutputLabel.setFor("selectitem" + i);
				htmlOutputLabel.setId("label" + i);
				//check box for hiddenng questions
				HtmlOutputLabel lblForCheckBox = new HtmlOutputLabel();
				lblForCheckBox.setId("labelForCheckBox" + i);

				HtmlSelectBooleanCheckbox hideCheckBoxForEachRow = new HtmlSelectBooleanCheckbox();
				hideCheckBoxForEachRow.setId("showHidden" + i);
				//if the hide check box is selected
				if (benefitAdministration.getQstnHideFlag().equals("T")) {
					if (i % 2 == 0) {
						if (i < benefitAdminList.size() - 1) {
							rowClass.append("hiddenFieldLevelDisplay,");
						} else {
							rowClass.append("hiddenFieldLevelDisplay");
						}
					} else {
						if (i < benefitAdminList.size() - 1) {
							rowClass.append("hiddenFieldDisplay,");
						} else {
							rowClass.append("hiddenFieldDisplay");
						}
					}
					hideCheckBoxForEachRow.setSelected(true);
				} else {
					if (i % 2 == 0) {
						if (i < benefitAdminList.size() - 1) {
							rowClass.append("dataTableEvenRow,");
						} else {
							rowClass.append("dataTableEvenRow");
						}
					} else {
						if (i < benefitAdminList.size() - 1) {
							rowClass.append("dataTableOddRow,");
						} else {
							rowClass.append("dataTableOddRow");
						}
					}
					hideCheckBoxForEachRow.setSelected(false);
				}
				ValueBinding hiddenFlag = FacesContext.getCurrentInstance()
						.getApplication().createValueBinding(
								"#{productBenefitAdministrationBackingBean.datafieldmapForQuestionHideFlag["
										+ i + "]}");

				hideCheckBoxForEachRow.setValueBinding("value", hiddenFlag);

				//input hidden for admin option hide flag

				HtmlInputHidden inputHiddenForAOHideFlag = new HtmlInputHidden();
				inputHiddenForAOHideFlag.setValue(benefitAdministration
						.getAdminOptionHideFlag());
				inputHiddenForAOHideFlag.setId("htmlInputHiddenForAOHideFlag"
						+ i);
				ValueBinding adminOptionHideFlagVal = FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{productBenefitAdministrationBackingBean"
										+ ".datafieldmapForAOHideFlag[" + i
										+ "]}");
				inputHiddenForAOHideFlag.setValueBinding("value",
						adminOptionHideFlagVal);

				ProductSessionObject session = (ProductSessionObject) getSession()
						.getAttribute(WebConstants.PROD_TYPE);
				String type = (String) session.getProductKeyObject()
						.getProductType();

				htmlOutputText.getChildren().add(inputHiddenForAOHideFlag);
				htmlOutputLabel.getChildren().add(htmlOutputText);
				htmlOutputLabel.getChildren().add(htmlInputHiddenForQuestionId);
				htmlOutputLabel.getChildren().add(inputHiddenForAdminAssc);
				htmlOutputLabel.getChildren().add(htmlInputHiddenForAnswerId);

				panel.getChildren().add(htmlOutputText1);
				panel.getChildren().add(htmlOutputText2);
				//panel.getChildren().add(inputHiddenForAOHideFlag);
				if (viewBool) {
					panel.getChildren().add(htmlOutputAnswer);
				} else {
					panel.getChildren().add(htmlSelectOneMenu);
				}
				panel.getChildren().add(htmlOutputLabel);
				if (!type.equalsIgnoreCase("MANDATE") && !viewBool) {
					panel.getChildren().add(hideCheckBoxForEachRow);
				}
			}
		}
		panel.setRowClasses(rowClass.toString());
		return panel;
	}

	/**
	 * This method returns the anserdescription for answer id
	 * 
	 * @param items
	 * @param answerId
	 * @return
	 */
	private String getSelectedAnswerDescFromPossibleAnswersList(List items,
			int answerId) {
		// Iterate through the answers list
		for (int i = 0; i < items.size(); i++) {

			// Get individual AnswerBO from the list
			PossibleAnswerBO possibleAnswerBO = (PossibleAnswerBO) items.get(i);
			// Get the answer id from the list
			int individualAnswerId = possibleAnswerBO.getPossibleAnswerId();

			// Get the answer description from the list
			String individualAnswerDesc = possibleAnswerBO
					.getPossibleAnswerDesc();

			// Check if the answer id matches the required answer id
			if (answerId == individualAnswerId) {

				// Return the answer description
				return individualAnswerDesc;
			}
		}
		return null;
	}

	/**
	 * Returns the panel
	 * 
	 * @return HtmlPanelGrid panel.
	 */
	public HtmlPanelGrid getPanel() {
		return panel;
	}

	/*
	 * method to invoke when the show Hidden Checkbox is selected.
	 */
	public String loadWithHiddenValues() {

		this.setShowHiddenSelected(true);
		// call the method to load values
		loadBenefitAdministrationValues();
		return "loadBenefitAdmin";
	}

	/*
	 * method to invoke when the show Hidden Checkbox is unselected.
	 */
	public String loadWithoutHiddenValues() {

		this.setShowHiddenSelected(false);
		// call the method to load values
		loadBenefitAdministrationValues();
		return "loadBenefitAdmin";
	}

	/**
	 * This function is used as the action reffered function
	 * 
	 * @return
	 */
	public String loadBenefitAdministrationValues() {
		//gets the request
		RetrieveProductBenefitAdminRequest retrieveProductBenefitAdminRequest = (RetrieveProductBenefitAdminRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_BENEFIT_ADMIN);

		retrieveProductBenefitAdminRequest.setProductBenefitAdminId(this
				.getProductSessionObject().getBenefitAdminId());
		retrieveProductBenefitAdminRequest.setProductAdminOptionId(this
				.getProductSessionObject().getAdminOptionId());
		retrieveProductBenefitAdminRequest.setBenefitComponentId(this
				.getProductSessionObject().getBenefitComponentId());
		String returnString = null;
		if (!isViewMode()) {
			viewBool = false;
			returnString = "loadBenefitAdmin";
		} else {
			viewBool = true;
			returnString = "benefitAdminViewPage";
		}
		//calls the service
		RetrieveProductBenefitAdminResponse retrieveProductBenefitAdminResponse = (RetrieveProductBenefitAdminResponse) executeService(retrieveProductBenefitAdminRequest);

		// Get the list of benefit administration values from the response

		if (null != retrieveProductBenefitAdminResponse) {
			//list of all questions
			List benefitAdministrationList = retrieveProductBenefitAdminResponse
					.getBenefitAdministrationList();
			/* Enhancement starts here */

			//to show questions which are not hidden
			if (null != benefitAdministrationList
					&& benefitAdministrationList.size() > 0) {
				if (!showHiddenSelected) {
					HtmlPanelGrid panel = getPanelFromList(
							listToDisplay(benefitAdministrationList), viewBool);
				} else {
					//to show all questions.ie. if show hidden is selected,
					//then it will display all the questions no matter whether
					// they are hidden or not
					HtmlPanelGrid panel = getPanelFromList(
							listToDisplay(benefitAdministrationList), viewBool);
				}
			}

			/* Enhancement ends */
			//HtmlPanelGrid panel = getPanelFromList(
			// benefitAdministrationList,viewBool );
			this.setPanel(panel);
		}

		return returnString;
	}

	/**
	 * method to populate list according to the checkbox
	 * 
	 * @param benefitAdministrationList
	 * @return
	 */
	public List listToDisplay(List benefitAdministrationList) {
		if (!showHiddenSelected) {
			List listWithoutHiddenQuestions = null;
			if (null != benefitAdministrationList) {
				listWithoutHiddenQuestions = new ArrayList(
						benefitAdministrationList.size());

				EntityBenefitAdministration entityBenefitAdministration = new EntityBenefitAdministration();
				for (int i = 0; i < benefitAdministrationList.size(); i++) {
					entityBenefitAdministration = (EntityBenefitAdministration) benefitAdministrationList
							.get(i);
					//if the questin is not hidden then add it to another list
					if (entityBenefitAdministration.getQstnHideFlag().equals(
							"F")) {
						listWithoutHiddenQuestions
								.add(entityBenefitAdministration);
					}
				}
			}
			return listWithoutHiddenQuestions;
		} else {
			return benefitAdministrationList;
		}

	}

	/**
	 * This function is used for loading the benfit values for printing
	 * 
	 * @return
	 */
	public String loadBenefitAdministrationValuesForPrint() {
		//gets request
		RetrieveProductBenefitAdminRequest retrieveProductBenefitAdminRequest = (RetrieveProductBenefitAdminRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_BENEFIT_ADMIN);

		retrieveProductBenefitAdminRequest.setProductBenefitAdminId(this
				.getProductSessionObject().getBenefitAdminId());
		retrieveProductBenefitAdminRequest.setProductAdminOptionId(this
				.getProductSessionObject().getAdminOptionId());
		retrieveProductBenefitAdminRequest.setBenefitComponentId(this
				.getProductSessionObject().getBenefitComponentId());
		String returnString = null;

		viewBool = true;
		returnString = "benefitAdminViewPage";
		//calls the service
		RetrieveProductBenefitAdminResponse retrieveProductBenefitAdminResponse = (RetrieveProductBenefitAdminResponse) executeService(retrieveProductBenefitAdminRequest);

		// Get the list of benefit administration values from the response

		if (null != retrieveProductBenefitAdminResponse) {
			List benefitAdministrationList = retrieveProductBenefitAdminResponse
					.getBenefitAdministrationList();
			HtmlPanelGrid panel = getPanelFromList(benefitAdministrationList,
					viewBool);
			this.setPanel(panel);
		}

		return returnString;
	}

	/**
	 * This function is to get the answer list for a question
	 * 
	 * @param answersList
	 * @return
	 */
	private List getPossibleAnswersListForAQuestion(List answersList) {

		// Create a list
		List answerDescriptionList = null;

		// Check whether the answerList is null or empty
		if (null != answersList || !answersList.isEmpty()) {
			answerDescriptionList = new ArrayList(answersList.size());
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
		// Return the list
		return answerDescriptionList;

	}

	/**
	 * This function is for loading product benefit admin values
	 * 
	 * @return
	 * @throws WPDException
	 */
	public List loadBenefitAdminValues() {

		RetrieveProductBenefitAdminRequest retrieveProductBenefitAdminRequest = (RetrieveProductBenefitAdminRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_BENEFIT_ADMIN);

		retrieveProductBenefitAdminRequest.setProductBenefitAdminId(this
				.getProductSessionObject().getBenefitAdminId());
		retrieveProductBenefitAdminRequest.setProductAdminOptionId(this
				.getProductSessionObject().getAdminOptionId());
		retrieveProductBenefitAdminRequest.setBenefitComponentId(this
				.getProductSessionObject().getBenefitComponentId());

		//calls the service
		RetrieveProductBenefitAdminResponse retrieveProductBenefitAdminResponse = (RetrieveProductBenefitAdminResponse) executeService(retrieveProductBenefitAdminRequest);

		// Get the list of benefit administration values from the response

		if (null != retrieveProductBenefitAdminResponse) {
			return retrieveProductBenefitAdminResponse
					.getBenefitAdministrationList();
		}
		return null;

	}

	/**
	 * 
	 * @param benefitAdminValueList
	 * @param benefitAdministrationList
	 * @return
	 */
	private List listToUpdate(List benefitAdminValueList,
			List benefitAdministrationList) {

		List listToUpdate = null;
		boolean answerOverRideFlag = false;
		boolean questionHideFlag = false;

		Iterator dbListIter = benefitAdminValueList.iterator();
		Iterator pageListIter = benefitAdministrationList.iterator();
		if (null != benefitAdministrationList) {
			listToUpdate = new ArrayList(benefitAdministrationList.size());
		}
		EntityBenefitAdministration entityBenefitAdministration = null;
		ProductBenefitAdminOverrideVO voObject = null;

		while (dbListIter.hasNext()) {

			entityBenefitAdministration = (EntityBenefitAdministration) dbListIter
					.next();

			while (pageListIter.hasNext()) {

				voObject = (ProductBenefitAdminOverrideVO) pageListIter.next();

				if (entityBenefitAdministration.getAnsOvrdCustmzdSysId() == voObject
						.getAnswrOvrdId()) {

					if ((entityBenefitAdministration.getAnswerId() != voObject
							.getAnswerId())) {
						answerOverRideFlag = true;
						this.setAnswerOverrideFlag(true);
						//listToUpdate.add(voObject);
					}
					if (!entityBenefitAdministration.getQstnHideFlag().equals(
							voObject.getQuestionHideFlag())) {
						questionHideFlag = true;
						this.setHideStatusFlag(true);

					}

					if (answerOverRideFlag || questionHideFlag) {
						listToUpdate.add(voObject);
						answerOverRideFlag = false;
						questionHideFlag = false;
					}
				}
			}

			pageListIter = benefitAdministrationList.iterator();
		}
		return listToUpdate;
	}

	/**
	 * @param administrationRequest
	 * @param benefitAdministrationList
	 */
	private void setValuesForAdminOptionValidation(
			UpdateProductQuestionareRequest administrationRequest) {
		// for regular questionnaire
		boolean qstnsChanged = false;
		List changeIds = new ArrayList();
		//CARS start
		List changedTierQuesIds = null;//CARS change
		List changedProdTierIds = null;//CARS change
		//CARS end
		if (null != administrationRequest
				&& null != administrationRequest.getQuestionnareList()
				&& administrationRequest.getQuestionnareList().size() > 0) {
			Map oldStates = getOldState(questionsStates);
			List quesList = administrationRequest.getQuestionnareList();
			Iterator iterator = quesList.iterator();
			while (iterator.hasNext()) {
				ProductQuestionareBO quesBO = (ProductQuestionareBO) iterator
						.next();
				String qstnKey = quesBO.getQuestionId() +"";
				String[] values = (String[]) oldStates.get(qstnKey);
				if (values != null) {

					if (!values[0].equals(quesBO.getSelectedAnswerid() + "")) {
						qstnsChanged = true;
						changeIds.add(qstnKey);
					}
				} else {
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
							ProductQuestionareBO entityBO = (ProductQuestionareBO)quesList.get(i);
							if((queskey.equalsIgnoreCase(entityBO.getQuestionId() +"")))
								contains=true;	
						}		
					}
					if(!contains){
						qstnsChanged = true;
						changeIds.add(queskey);
					}
					
				}
			}
			
		}
		// for tiered quesionnares
		if (null != administrationRequest
				&& null != administrationRequest.getTierList()
				&& administrationRequest.getTierList().size() > 0) {
			
			//CARS start
			changedTierQuesIds = new ArrayList();			
			changedProdTierIds = new ArrayList();
			//CARS end
			
			Map oldStates = getOldState(tieredQuestionsStates);
			for(int i =0;i<tierList.size();i++){
				BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(i); // iterating tier definitions
				List benefitTierList = defnBo.getBenefitTiers();
				for (int k =0; k<benefitTierList.size();k++){
					BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
						
						List tieredquesList = tierBo.getQuestionnaireList();
						if (null != tieredquesList) {	//CARS change						
						
						Iterator iterator = tieredquesList.iterator();
						while (iterator.hasNext()) {
							ProductQuestionareBO quesBO = (ProductQuestionareBO) iterator
									.next();
							String qstnKey = quesBO.getTierSysId()+"-"+quesBO.getQuestionId() +"";
							String[] values = (String[]) oldStates.get(qstnKey);
							if (values != null) {
			
								if (!values[0].equals(quesBO.getSelectedAnswerid() + "")) {
									qstnsChanged = true;
									
									//CARS start
									changedTierQuesIds.add(getQuestionIdFromKey(qstnKey));//CARS change
									changedProdTierIds.add(new Integer(tierBo.getBenefitTierSysId()));
									//CARS end
								}
							} else {
								qstnsChanged = true;
								//CARS start
								changedTierQuesIds.add(getQuestionIdFromKey(qstnKey));//CARS change
								changedProdTierIds.add(new Integer(tierBo.getBenefitTierSysId()));
								
								//CARS end
							}
						}
						}
						if(oldStates!=null){
							Set set=oldStates.keySet();
							Iterator iter=set.iterator();
							while(iter.hasNext()){
								boolean contains=false;
								String queskey=""+iter.next();
								if(tieredquesList!=null&& tieredquesList.size()>0){
									for(int s=0;s<tieredquesList.size()&&!contains;s++){
										ProductQuestionareBO entityBO = (ProductQuestionareBO)tieredquesList.get(s);
										if((queskey.equalsIgnoreCase(entityBO.getTierSysId()+"-"+entityBO.getQuestionId() +"")))
											contains=true;	
									}		
								}
								if(!contains){
									qstnsChanged = true;
									//CARS start
									changedTierQuesIds.add(getQuestionIdFromKey(queskey));
									changedProdTierIds.add(new Integer(tierBo.getBenefitTierSysId()));									
									//CARS end
								}
								
							}
						}
				}	
			}
			
			
			
		}
		if(null!=changeIds && changeIds.size()>0)
		{
		   changeIds=new ArrayList(new HashSet(changeIds));	
		}
		//CARS start
		if(null!=changedTierQuesIds && changedTierQuesIds.size()>0){
			changedTierQuesIds=new ArrayList(new HashSet(changedTierQuesIds));	
		}
		if(null!=changedProdTierIds && changedProdTierIds.size()>0){
			changedProdTierIds=new ArrayList(new HashSet(changedProdTierIds));	
		}
		//CARS end
		if(true== qstnsChanged)
		administrationRequest.setQstnsChanged(qstnsChanged);
		administrationRequest.setChangedIds(changeIds);
		//CARS start
		administrationRequest.setChangedTierQuesIds(changedTierQuesIds); //CARS change
		administrationRequest.setChangedProdTierIds(changedProdTierIds); //CARS change
		//CARS end
	}
	/**
	 * @return
	 */
	private String getQuestionIdFromKey(String key){
		String[] values = key.split("-");
		if(values.length ==2){
			return values[1];
		}
		return "";
	}

	/**
	 * @return
	 */
	private Map getOldState(String states) {
		Map oldState = new HashMap();
		if (null != states && !"".equals(states.trim())) {
			String[] changedValues = states.split(":");
			for (int i = 0; i < changedValues.length; i++) {
				String changedValue = changedValues[i];
				String[] values = changedValue.split("~");
				if (values.length == 2) {
					String qstnKey = values[0];
					String aswerId = values[1];
//					String hideFlag = values[2];
					oldState.put(qstnKey, new String[] { aswerId});
				}
			}
		}
		return oldState;
	}

	/**
	 * @param toDisplay
	 */
	private void storeAsnwerStates(List toDisplay) {
		if (null != toDisplay && toDisplay.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			Iterator iterator = toDisplay.iterator();
			while (iterator.hasNext()) {
				EntityBenefitAdministration administration = (EntityBenefitAdministration) iterator
						.next();
				buffer.append(administration.getQuestionNumber());
				buffer.append("~");
				buffer.append(administration.getAnswerId());
				buffer.append("~");
				buffer.append(administration.getQstnHideFlag());
				if (iterator.hasNext())
					buffer.append(":");
			}
			questionsStates = buffer.toString();
		}
	}

	/**
	 * This function is to get the over ridden list
	 * 
	 * @return
	 */
	private List getBenefitAdministrationOverriddenList() {

		// Create a list
		List benefitAdministrationList = null;

		// Get the question id from the hidden key set of the HashMap
		Set idSet = datafieldMapForQuestionId.keySet();

		// Create the iterator for the questionId
		Iterator questionIdIter = idSet.iterator();

		// Get the answers key set from the HashMap
		Set answerSet = datafieldMapForAnswerId.keySet();

		// Create the iterator for the answerSet
		Iterator answerIterator = answerSet.iterator();

		// Get the Hidden Flag set from the HashMap
		Set hideFlagSet = datafieldmapForQuestionHideFlag.keySet();

		// Create the iterator for the answerSet
		Iterator hideFlagIterator = hideFlagSet.iterator();

		Set adminAsscSet = datafieldMapForAdminAsscId.keySet();

		Iterator adminIter = adminAsscSet.iterator();

		if (null != idSet && null != answerSet) {
			benefitAdministrationList = new ArrayList(idSet.size());
		}

		// Iterate through the HaspMap and get the individual question and
		// answer values and set them to the list
		//while(questionIdIter.hasNext() && answerIterator.hasNext() &&
		// hideFlagIterator.hasNext()){
		while (questionIdIter.hasNext() && answerIterator.hasNext()) {
			// Get the long value
			Long iterationId = (Long) questionIdIter.next();

			// Create an instance of the VO
			ProductBenefitAdminOverrideVO administrationOverrideVO = new ProductBenefitAdminOverrideVO();

			String adminAsscId = (String) datafieldMapForAdminAsscId
					.get(iterationId);

			administrationOverrideVO.setAnswrOvrdId(new Integer(adminAsscId)
					.intValue());

			// Get the value of id form the map
			String questionId = (String) datafieldMapForQuestionId
					.get(iterationId);

			// Set the value of the question id
			administrationOverrideVO.setQuestionId((new Integer(questionId))
					.intValue());

			//Get the value of id form the map
			String answerId = (String) datafieldMap.get(iterationId);

			// Set the value of the question id
			administrationOverrideVO.setAnswerId((new Integer(answerId))
					.intValue());

			// Get the value of hiddenFlag form the map
			String hiddenFlag = "F";
			ProductSessionObject session = (ProductSessionObject) getSession()
					.getAttribute(WebConstants.PROD_TYPE);
			String type = (String) session.getProductKeyObject()
					.getProductType();

			String benefitComName = (String) getSession().getAttribute(
					"BENEFIT_COMP_NAME");
			if (!type.equalsIgnoreCase("MANDATE")) {
				Boolean hideStatus = (Boolean) datafieldmapForQuestionHideFlag
						.get(iterationId);
				if ("true".equals(hideStatus.toString())) {
					hiddenFlag = "T";
				} else {
					hiddenFlag = "F";
				}
			}

			String hiddenFlagForAO = (String) datafieldmapForAOHideFlag
					.get(iterationId);
			administrationOverrideVO.setAdminOptionHideFlag(hiddenFlagForAO);
			//String adminOptionHideFlagVal =
			// (String)datafieldmapForAOHideFlag.get(iterationId);
			//administrationOverrideVO.setAdminOptionHideFlag(
			// adminOptionHideFlagVal);

			// Set the value of the question id
			administrationOverrideVO.setQuestionHideFlag(hiddenFlag);

			// Set the answer
			//administrationOverrideVO.setAnswerDesc((String)datafieldMap.get(iterationId));

			// Add the VO to the list
			benefitAdministrationList.add(administrationOverrideVO);
		}

		// Return the list
		return benefitAdministrationList;

	}

	/**
	 * @param panel
	 *            The panel to set.
	 */
	public void setPanel(HtmlPanelGrid panel) {
		this.panel = panel;
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
	 * Returns the answerlist
	 * 
	 * @return List answerlist.
	 */

	public List getAnswerList() {
		answerList = new ArrayList(2);
		SelectItem yes = new SelectItem("Yes", "Yes");
		SelectItem no = new SelectItem("No", "No");
		answerList.add(yes);
		answerList.add(no);
		return answerList;
	}

	/**
	 * Sets the answerList
	 * 
	 * @param answerList.
	 */
	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}

	/**
	 * Returns the baList
	 * 
	 * @return List baList.
	 */
	public List getBaList() {
		return baList;
	}

	/**
	 * Sets the baList
	 * 
	 * @param baList.
	 */
	public void setBaList(List baList) {
		this.baList = baList;
	}

	/**
	 * Returns the psbaObject1
	 * 
	 * @return EntityBenefitAdministration psbaObject1.
	 */
	public EntityBenefitAdministration getPsbaObject1() {
		return psbaObject1;
	}

	/**
	 * Sets the psbaObject1
	 * 
	 * @param psbaObject1.
	 */
	public void setPsbaObject1(EntityBenefitAdministration psbaObject1) {
		this.psbaObject1 = psbaObject1;
	}

	/**
	 * Returns the psbaObject2
	 * 
	 * @return EntityBenefitAdministration psbaObject2.
	 */
	public EntityBenefitAdministration getPsbaObject2() {
		return psbaObject2;
	}

	/**
	 * Sets the psbaObject2
	 * 
	 * @param psbaObject2.
	 */
	public void setPsbaObject2(EntityBenefitAdministration psbaObject2) {
		this.psbaObject2 = psbaObject2;
	}

	/**
	 * Returns the psbaObject3
	 * 
	 * @return EntityBenefitAdministration psbaObject3.
	 */
	public EntityBenefitAdministration getPsbaObject3() {
		return psbaObject3;
	}

	/**
	 * Sets the psbaObject3
	 * 
	 * @param psbaObject3.
	 */
	public void setPsbaObject3(EntityBenefitAdministration psbaObject3) {
		this.psbaObject3 = psbaObject3;
	}

	/**
	 * Returns the reference
	 * 
	 * @return String reference.
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the reference
	 * 
	 * @param reference.
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Returns the resultList
	 * 
	 * @return List resultList.
	 */
	public List getResultList() {
		return resultList;
	}

	/**
	 * Sets the resultList
	 * 
	 * @param resultList.
	 */
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	/**
	 * Returns the typeList
	 * 
	 * @return List typeList.
	 */

	public List getTypeList() {
		typeList = new ArrayList(2);
		SelectItem accum = new SelectItem("ACCUM");
		SelectItem eob = new SelectItem("EOB");
		typeList.add(accum);
		typeList.add(eob);
		return typeList;
	}

	/**
	 * Sets the typeList
	 * 
	 * @param typeList.
	 */
	public void setTypeList(List typeList) {
		this.typeList = typeList;
	}

	/**
	 * Returns the viewPanel
	 * 
	 * @return HtmlPanelGrid viewPanel.
	 */
	public HtmlPanelGrid getViewPanel() {
		return panel;
	}

	/**
	 * Sets the viewPanel
	 * 
	 * @param viewPanel.
	 */
	public void setViewPanel(HtmlPanelGrid viewPanel) {
		this.viewPanel = viewPanel;
	}

	/**
	 * Returns the viewType
	 * 
	 * @return String viewType.
	 */
	public String getViewType() {
		return viewType;
	}

	/**
	 * Sets the viewType
	 * 
	 * @param viewType.
	 */
	public void setViewType(String viewType) {
		this.viewType = viewType;
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
	 * Returns the datafieldMapForAnswerId
	 * 
	 * @return Map datafieldMapForAnswerId.
	 */
	public Map getDatafieldMapForAnswerId() {
		return datafieldMapForAnswerId;
	}

	/**
	 * Sets the datafieldMapForAnswerId
	 * 
	 * @param datafieldMapForAnswerId.
	 */
	public void setDatafieldMapForAnswerId(Map datafieldMapForAnswerId) {
		this.datafieldMapForAnswerId = datafieldMapForAnswerId;
	}

	/**
	 * Returns the datafieldMapForQuestionId
	 * 
	 * @return Map datafieldMapForQuestionId.
	 */
	public Map getDatafieldMapForQuestionId() {
		return datafieldMapForQuestionId;
	}

	/**
	 * Sets the datafieldMapForQuestionId
	 * 
	 * @param datafieldMapForQuestionId.
	 */
	public void setDatafieldMapForQuestionId(Map datafieldMapForQuestionId) {
		this.datafieldMapForQuestionId = datafieldMapForQuestionId;
	}

	public String loadForView() {
		return "benefitAdminViewPage";
	}

	/**
	 * Returns the hiddenInit
	 * 
	 * @return String hiddenInit.
	 */
	public String getHiddenInit() {
		return hiddenInit;
	}

	/**
	 * Sets the hiddenInit
	 * 
	 * @param hiddenInit.
	 */
	public void setHiddenInit(String hiddenInit) {
		this.hiddenInit = hiddenInit;
	}

	/**
	 * Returns the printAdmin
	 * 
	 * @return String printAdmin.
	 */
	public String getPrintAdmin() {
		this.loadBenefitAdministrationValuesForPrint();
		return printAdmin;
	}

	/**
	 * Sets the printAdmin
	 * 
	 * @param printAdmin.
	 */
	public void setPrintAdmin(String printAdmin) {
		this.printAdmin = printAdmin;
	}

	/**
	 * @return printBreadCrumbText
	 * 
	 * Returns the printBreadCrumbText.
	 */
	public String getPrintBreadCrumbText() {
		printBreadCrumbText = "Product Configuration >> Product ("
				+ getProductSessionObject().getProductKeyObject()
						.getProductName() + ") >> Print";
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
	 * @return rowId
	 * 
	 * Returns the rowId.
	 */
	public int getRowId() {
		return rowId;
	}

	/**
	 * @param rowId
	 * 
	 * Sets the rowId.
	 */
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	/**
	 * @return validationMessages
	 * 
	 * Returns the validationMessages.
	 */
	public List getValidationMessages() {
		return validationMessages;
	}

	/**
	 * @param validationMessages
	 * 
	 * Sets the validationMessages.
	 */
	public void setValidationMessages(List validationMessages) {
		this.validationMessages = validationMessages;
	}

	public String deleteQuestion() {
		int rowId = getRowId();
		QuestionDeleteRequest request = null;

		request = this.getQuestionDeleteRequest();
		QuestionDeleteResponse deleteQuestionResponse = (QuestionDeleteResponse) executeService(request);

		loadBenefitAdminValAfterDelete();
		// validationMessages = new ArrayList();

		// this.validationMessages.add(deleteQuestionResponse.getMessages());

		addAllMessagesToRequest(deleteQuestionResponse.getMessages());
		return "deleteBenefitAdminQuestion";
	}

	private void loadBenefitAdminValAfterDelete() {

		RetrieveProductBenefitAdminRequest retrieveProductBenefitAdminRequest = (RetrieveProductBenefitAdminRequest) this
				.getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_BENEFIT_ADMIN);

		retrieveProductBenefitAdminRequest.setProductBenefitAdminId(this
				.getProductSessionObject().getBenefitAdminId());
		retrieveProductBenefitAdminRequest.setProductAdminOptionId(this
				.getProductSessionObject().getAdminOptionId());
		retrieveProductBenefitAdminRequest.setBenefitComponentId(this
				.getProductSessionObject().getBenefitComponentId());
		String returnString = null;

		//calls the service
		RetrieveProductBenefitAdminResponse retrieveProductBenefitAdminResponse = (RetrieveProductBenefitAdminResponse) executeService(retrieveProductBenefitAdminRequest);

		// Get the list of benefit administration values from the response

		if (null != retrieveProductBenefitAdminResponse) {
			List benefitAdministrationList = retrieveProductBenefitAdminResponse
					.getBenefitAdministrationList();
			HtmlPanelGrid panel = getPanelFromList(benefitAdministrationList,
					viewBool);
			this.setPanel(panel);
		}
	}

	public QuestionDeleteRequest getQuestionDeleteRequest() {

		ProductSessionObject object = (ProductSessionObject) getSession()
				.getAttribute(WebConstants.PROD_TYPE);
		int productId = super.getProductKeyFromSession();
		String questionKey = (String) datafieldMapForQuestionId.get(new Long(
				rowId));

		QuestionDeleteRequest request = (QuestionDeleteRequest) this
				.getServiceRequest(ServiceManager.QUESTION_DELETE);

		request.setAdminLevelOptionAssnId(object.getAdminLevelOptionAssnId());
		request.setBenefitComponentId(object.getBenefitComponentId());
		request.setQuestionID(questionKey);

		return request;
	}

	/**
	 * @return Returns the datafieldmapForQuestionHideFlag.
	 */
	public Map getDatafieldmapForQuestionHideFlag() {
		return datafieldmapForQuestionHideFlag;
	}

	/**
	 * @param datafieldmapForQuestionHideFlag
	 *            The datafieldmapForQuestionHideFlag to set.
	 */
	public void setDatafieldmapForQuestionHideFlag(
			Map datafieldmapForQuestionHideFlag) {
		this.datafieldmapForQuestionHideFlag = datafieldmapForQuestionHideFlag;
	}

	/**
	 * @return Returns the qstnHideFlag.
	 */
	public String getQstnHideFlag() {
		return QstnHideFlag;
	}

	/**
	 * @param qstnHideFlag
	 *            The qstnHideFlag to set.
	 */
	public void setQstnHideFlag(String qstnHideFlag) {
		QstnHideFlag = qstnHideFlag;
	}

	/**
	 * @return Returns the showHiddenSelected.
	 */
	public boolean getShowHiddenSelected() {
		return showHiddenSelected;
	}

	/**
	 * @param isShowHiddenSelected
	 *            The isShowHiddenSelected to set.
	 */
	public void setShowHiddenSelected(boolean showHiddenSelected) {
		this.showHiddenSelected = showHiddenSelected;
	}

	/**
	 * @return Returns the adminOptionHideFlag.
	 */
	public String getAdminOptionHideFlag() {
		return adminOptionHideFlag;
	}

	/**
	 * @param adminOptionHideFlag
	 *            The adminOptionHideFlag to set.
	 */
	public void setAdminOptionHideFlag(String adminOptionHideFlag) {
		this.adminOptionHideFlag = adminOptionHideFlag;
	}

	/**
	 * @return Returns the datafieldmapForAOHideFlag.
	 */
	public Map getDatafieldmapForAOHideFlag() {
		return datafieldmapForAOHideFlag;
	}

	/**
	 * @param datafieldmapForAOHideFlag
	 *            The datafieldmapForAOHideFlag to set.
	 */
	public void setDatafieldmapForAOHideFlag(Map datafieldmapForAOHideFlag) {
		this.datafieldmapForAOHideFlag = datafieldmapForAOHideFlag;
	}

	/**
	 * @return Returns the answerOverrideFlag.
	 */
	public boolean getAnswerOverrideFlag() {
		return answerOverrideFlag;
	}

	/**
	 * @param answerOverrideFlag
	 *            The answerOverrideFlag to set.
	 */
	public void setAnswerOverrideFlag(boolean answerOverrideFlag) {
		this.answerOverrideFlag = answerOverrideFlag;
	}

	/**
	 * @return Returns the hideStatusFlag.
	 */
	public boolean getHideStatusFlag() {
		return hideStatusFlag;
	}

	/**
	 * @param hideStatusFlag
	 *            The hideStatusFlag to set.
	 */
	public void setHideStatusFlag(boolean hideStatusFlag) {
		this.hideStatusFlag = hideStatusFlag;
	}

	/**
	 * @return Returns the datafieldMapForAdminAsscId.
	 */
	public Map getDatafieldMapForAdminAsscId() {
		return datafieldMapForAdminAsscId;
	}

	/**
	 * @param datafieldMapForAdminAsscId
	 *            The datafieldMapForAdminAsscId to set.
	 */
	public void setDatafieldMapForAdminAsscId(Map datafieldMapForAdminAsscId) {
		this.datafieldMapForAdminAsscId = datafieldMapForAdminAsscId;
	}

	/**
	 * @return Returns the questionsStates.
	 */
	public String getQuestionsStates() {
		return questionsStates;
	}

	/**
	 * @param questionsStates
	 *            The questionsStates to set.
	 */
	public void setQuestionsStates(String questionsStates) {
		this.questionsStates = questionsStates;
	}

	/**
	 * @return Returns the questionareList.
	 */
	public List getQuestionareList() {
		if (null == questionareList)
			loadQuestionare();
			setValuesToHidden();
		return questionareList;
	}
	private void setValuesToHidden(){
		
		ProductSessionObject productSessionObject = (ProductSessionObject) getSession().getAttribute(WebConstants.PROD_TYPE);
		
		this.setBenefitComponentId(Integer.toString(this.getProductSessionObject().getBenefitComponentId()));
		this.setPrimaryEntityID(Integer.toString(getProductKeyFromSession()));
		//this.setAdminLvlOptionAssnSysId(Integer.toString(productSessionObject.getAdminLevelOptionAssnId()));
		
	}
	/**
	 * @param questionareList
	 *            The questionareList to set.
	 */
	public void setQuestionareList(List questionareList) {
		this.questionareList = questionareList;
	}

	/**
	 * @return Returns the questionarePanel.
	 */
	public HtmlPanelGrid getQuestionarePanel() {
		
		return questionarePanel;
	}

	/**
	 * @param questionarePanel
	 *            The questionarePanel to set.
	 */
	public void setQuestionarePanel(HtmlPanelGrid questionarePanel) {
		this.questionarePanel = questionarePanel;
	}

	/**
	 * @return Returns the answerId.
	 */
	public int getAnswerId() {
		return answerId;
	}

	/**
	 * @param answerId
	 *            The answerId to set.
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
	 * @param rowNum
	 *            The rowNum to set.
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * @return Returns the bCompName.
	 */
	public String getBCompName() {
		return bCompName;
	}

	/**
	 * @param compName
	 *            The bCompName to set.
	 */
	public void setBCompName(String compName) {
		bCompName = compName;
	}
	public String getAdminLvlOptionAssnSysId() {
		return adminLvlOptionAssnSysId;
	}
	public void setAdminLvlOptionAssnSysId(String adminLvlOptionAssnSysId) {
		this.adminLvlOptionAssnSysId = adminLvlOptionAssnSysId;
	}
	public String getBenefitComponentId() {
		return benefitComponentId;
	}
	public void setBenefitComponentId(String benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	public String getPrimaryEntityID() {
		return primaryEntityID;
	}
	public void setPrimaryEntityID(String primaryEntityID) {
		this.primaryEntityID = primaryEntityID;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
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
	 * @return Returns the benefitDefnId.
	 */
	public int getBenefitDefnId() {
		return benefitDefnId;
	}
	/**
	 * @param benefitDefnId The benefitDefnId to set.
	 */
	public void setBenefitDefnId(int benefitDefnId) {
		this.benefitDefnId = benefitDefnId;
	}
	/**
	 * @return Returns the benefitComponentID.
	 */
	public int getBenefitComponentID() {
		return benefitComponentID;
	}
	/**
	 * @param benefitComponentID The benefitComponentID to set.
	 */
	public void setBenefitComponentID(int benefitComponentID) {
		this.benefitComponentID = benefitComponentID;
	}
	/**
	 * @return Returns the tierList.
	 */
	public List getTierList() {
		return tierList;
	}
	/**
	 * @param tierList The tierList to set.
	 */
	public void setTierList(List tierList) {
		this.tierList = tierList;
	}
	/**
	 * @return Returns the tierQuestionarePanel.
	 */
	public HtmlPanelGrid getTierQuestionarePanel() {
		
		return tierQuestionarePanel;
	}
	/**
	 * @param tierQuestionarePanel The tierQuestionarePanel to set.
	 */
	public void setTierQuestionarePanel(HtmlPanelGrid tierQuestionarePanel) {
		this.tierQuestionarePanel = tierQuestionarePanel;
	}
	
	/**
	 * @return Returns the panelTierSysId.
	 */
	public String getPanelTierSysId() {
		return panelTierSysId;
	}
	/**
	 * @param panelTierSysId The panelTierSysId to set.
	 */
	public void setPanelTierSysId(String panelTierSysId) {
		this.panelTierSysId = panelTierSysId;
	}
	/**
	 * @return Returns the tieredQuestionsStates.
	 */
	public String getTieredQuestionsStates() {
		return tieredQuestionsStates;
	}
	/**
	 * @param tieredQuestionsStates The tieredQuestionsStates to set.
	 */
	public void setTieredQuestionsStates(String tieredQuestionsStates) {
		this.tieredQuestionsStates = tieredQuestionsStates;
	}
	/**
	 * @return Returns the adminLevelAssociationId.
	 */
	public int getAdminLevelAssociationId() {
		return adminLevelAssociationId;
	}
	/**
	 * @param adminLevelAssociationId The adminLevelAssociationId to set.
	 */
	public void setAdminLevelAssociationId(int adminLevelAssociationId) {
		this.adminLevelAssociationId = adminLevelAssociationId;
	}
	/**
	 * @return Returns the tildaTierNoteStatus.
	 */
	public String getTildaTierNoteStatus() {
		return tildaTierNoteStatus;
	}
	/**
	 * @param tildaTierNoteStatus The tildaTierNoteStatus to set.
	 */
	public void setTildaTierNoteStatus(String tildaTierNoteStatus) {
		this.tildaTierNoteStatus = tildaTierNoteStatus;
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
    
    public boolean getEditMode(){
       if(isEditMode()){
           return true;
       }else{
           return false;
       }
    }
    
   
	/**
	 * @return Returns the answerDesc.
	 */
	public String getAnswerDesc() {
		return answerDesc;
	}
	/**
	 * @param answerDesc The answerDesc to set.
	 */
	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc;
	}
}