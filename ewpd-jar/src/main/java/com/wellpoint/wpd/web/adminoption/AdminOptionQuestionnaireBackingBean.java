/*
 * AdminOptionQuestionnaireBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.adminoption;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionnaireBO;
import com.wellpoint.wpd.common.adminoption.request.CheckInAdminOptionRequest;
import com.wellpoint.wpd.common.adminoption.request.DeleteQuestionnaireRequest;
import com.wellpoint.wpd.common.adminoption.request.RetrieveAdminOptionQuestionRequest;
import com.wellpoint.wpd.common.adminoption.request.SaveAdminOptionQuestionnaireRequest;
import com.wellpoint.wpd.common.adminoption.request.SearchDuplicateReferenceRequest;
import com.wellpoint.wpd.common.adminoption.response.CheckInAdminOptionResponse;
import com.wellpoint.wpd.common.adminoption.response.DeleteQuestionnaireResponse;
import com.wellpoint.wpd.common.adminoption.response.RetrieveAdminOptionQuestionResponse;
import com.wellpoint.wpd.common.adminoption.response.SaveAdminOptionQuestionnaireResponse;
import com.wellpoint.wpd.common.adminoption.response.SearchDuplicateReferenceResponse;
import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.util.SessionCleanUp;
import com.wellpoint.wpd.web.contract.ContractSession;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.product.ProductSessionObject;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionQuestionnaireBackingBean extends WPDBackingBean{
	
	
	
    
    private String adminName;

    private int adminId = -1;
    
    private String state;

    private String status;

    private int version = -1;
    
    private boolean checkInOpted = false;
    
    private String questionnaire;
    
    private List associatedquestionnaire;
    
    private String selectedQuestnrId;
    
    private String selectedParentQuestnrId;
    
    private String printQuestionnaireList;
    
    private String displayAdminQuestionPrintTab="";
    
    private List duplicateQuestionRefList;

	private static final String ADMIN_OPTION_CREATE_STRING = "adminOptionCreatePage";

	private static final String ADMIN_OPTION_QUESTION_STRING = "adminOptionQuestionPage";

	private static final String ADMIN_OPTION_CREATE_BACKINGBEAN = "createAdminOptionBackingBean";

	private static final String ADMIN_OPTION_QUESTION_VIEW_STRING = "adminOptionQuestionViewPage";
    
    private String viewQuestionnaire;
    
    private String viewProductQuestionnaire;
    
    private String viewContractQuestionnaire;
    
    private int adminLevelOptSysId = -1;
    
    private int action = 0;
    
    private int productId;
    
    private int contractSysId;
    
    ProductSessionObject productSessionObject = null;
    
    ContractSession contractSessionObject = null;
    
    private String loadDuplicateReference;
    
    private String totalTime;
    
    private String parentRequiredString;
    
    private String functionalDomainDesc;
    
    private List functionalDomainList;
    
    private boolean searchChilds = false;
    
    private int paretContractSysId;
    

	/**
	 * @return Returns the questionnaireIdPRMap.
	 */
	public Map getQuestionnaireIdPRMap() {
		return questionnaireIdPRMap;
	}
	/**
	 * @param questionnaireIdPRMap The questionnaireIdPRMap to set.
	 */
	public void setQuestionnaireIdPRMap(Map questionnaireIdPRMap) {
		this.questionnaireIdPRMap = questionnaireIdPRMap;
	}
    private Map questionnaireIdPRMap;
    
   
  /* public void displayQuestionnaireTab(){
   	Logger.logDebug("In Questionnaire");
   	this.action = 5;
   }*/
	/**
	 * @param displayAdminQuestionPrintTab The displayAdminQuestionPrintTab to set.
	 */
	public void setDisplayAdminQuestionPrintTab(
			String displayAdminQuestionPrintTab) {
		this.displayAdminQuestionPrintTab = displayAdminQuestionPrintTab;
	}
	/**
     * @return Returns the displayAdminQuestionPrintTab.
     */
    public String getDisplayAdminQuestionPrintTab(){
    	this.displayAdminQuestionPrintTab = "Administration >> Administration Option "
                + "(" + this.adminName + ") >> Print";
        return displayAdminQuestionPrintTab;
    }

    /**
     * Method for Checking in the Admin Option
     * @return String.
     */
    public String checkInAdminOption(){
        CheckInAdminOptionRequest checkInAdminOptionRequest = null;
        CheckInAdminOptionResponse checkInAdminOptionResponse = null;

        //Creating request object
        checkInAdminOptionRequest = (CheckInAdminOptionRequest) this
                .getServiceRequest(ServiceManager.ADMIN_OPTION_CHECKIN_REQUEST);
        checkInAdminOptionRequest.getAdminOptionVO().setAdminOptionId(
                this.adminId);
        checkInAdminOptionRequest.getAdminOptionVO().setAdminName(
                this.adminName);
        checkInAdminOptionRequest.getAdminOptionVO().setVersion(this.version);
        checkInAdminOptionRequest.getAdminOptionVO().setStatus(this.status);
        checkInAdminOptionRequest.getAdminOptionVO().setStateValue(this.state);

        checkInAdminOptionRequest.setCheckInOpted(this.checkInOpted);

        //Creating the response object
        checkInAdminOptionResponse = (CheckInAdminOptionResponse) this
                .executeService(checkInAdminOptionRequest);
        if (null != checkInAdminOptionResponse) {

            addAllMessagesToRequest(checkInAdminOptionResponse.getMessages());
            this.version = checkInAdminOptionResponse.getAdminOptionVO()
                    .getVersion();
            this.status = checkInAdminOptionResponse.getAdminOptionVO()
                    .getStatus();

            if (null != checkInAdminOptionResponse.getAdminOptionVO()
                    .getStateObject()) {
                this.state = checkInAdminOptionResponse.getAdminOptionVO()
                        .getStateObject().getState();
            } else {
                this.state = checkInAdminOptionResponse.getAdminOptionVO()
                        .getStateValue();
            }
            if (!checkInAdminOptionResponse.isErrorFlag()) {
                SessionCleanUp
                        .removeManagedBean(ADMIN_OPTION_CREATE_BACKINGBEAN);
                return ADMIN_OPTION_CREATE_STRING;
            }
            else{
            	this.getRequest().getSession().setAttribute("AdminId",new Integer(this.adminId));
                this.getRequest().getSession().setAttribute("AdminName",this.adminName);
                this.getRequest().getSession().setAttribute("AdminVersion",new Integer(this.version));
                return ADMIN_OPTION_QUESTION_STRING;
            }
        }
       
       return ADMIN_OPTION_QUESTION_STRING;
    }
    
    /**
     * Method to display Admin Questionnaire Tab
     * 
     * @return String returnValue
     */
    public String displayAdminQuestionTab() {

        setBreadCrumbText("Administration >> Administration Option "
                + "(" + this.adminName + ") >> Edit");
        return ADMIN_OPTION_QUESTION_STRING;
    }
    
    public String displayAdminQuestionViewTab(){
    	setBreadCrumbText("Administration >> Administration Option "
                + "(" + this.adminName + ") >> View");
        return ADMIN_OPTION_QUESTION_VIEW_STRING;
    }
  
    /**
     * @return Returns the adminId.
     */
    public int getAdminId() {
        return adminId;
    }
    /**
     * @param adminId The adminId to set.
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    /**
     * @return Returns the adminName.
     */
    public String getAdminName() {
        return adminName;
    }
    /**
     * @param adminName The adminName to set.
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    /**
     * @return Returns the state.
     */
    public String getState() {
        return state;
    }
    /**
     * @param state The state to set.
     */
    public void setState(String state) {
        this.state = state;
    }
    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return Returns the version.
     */
    public int getVersion() {
        return version;
    }
    /**
     * @param version The version to set.
     */
    public void setVersion(int version) {
        this.version = version;
    }
    /**
     * @return Returns the checkInOpted.
     */
    public boolean isCheckInOpted() {
        return checkInOpted;
    }
    /**
     * @param checkInOpted The checkInOpted to set.
     */
    public void setCheckInOpted(boolean checkInOpted) {
        this.checkInOpted = checkInOpted;
    }
    /**
     * Method for deleting a question from the questionnaire.
     * @return
     */
    public String deleteQuestion(){
        DeleteQuestionnaireRequest deleteQuestionnaireRequest =(DeleteQuestionnaireRequest) this
        .getServiceRequest(ServiceManager.DELETE_QUESTIONNAIRE_REQUEST) ;
        List questionList = new ArrayList(1);
        questionList.add(this.selectedQuestnrId);
        deleteQuestionnaireRequest.setQuestionnaireIds(questionList);
        if(this.selectedQuestnrId.equals(this.selectedParentQuestnrId))
        	deleteQuestionnaireRequest.setRootQuestion(true);
        deleteQuestionnaireRequest.setAdminoptionId(this.adminId);
        deleteQuestionnaireRequest.setAdminName(this.adminName);
        deleteQuestionnaireRequest.setVersion(this.version);
        DeleteQuestionnaireResponse deleteQuestionnaireResponse = null;
        deleteQuestionnaireResponse = (DeleteQuestionnaireResponse)this.executeService(deleteQuestionnaireRequest);
        if(null != deleteQuestionnaireResponse){
            return "adminOptionQuestionPage";
        }
        return WebConstants.EMPTY_STRING;
    }
    /**Method to get the list of questions in the questionnaire.
     * @return Returns the questionnaire.
     */
    public String getQuestionnaire() {
        RetrieveAdminOptionQuestionRequest retrieveAdminOptionQuestionRequest = (RetrieveAdminOptionQuestionRequest) this
        .getServiceRequest(ServiceManager.RETRIEVE_ADMIN_OPTION_QUESTION_REQUEST);
        if(-1 != this.adminId){
            RetrieveAdminOptionQuestionResponse retrieveAdminOptionQuestionResponse = null;
            retrieveAdminOptionQuestionRequest.setAdminOptionId(this.adminId);
            if(this.action == 1){
            	retrieveAdminOptionQuestionRequest.setAction(this.action);
            	retrieveAdminOptionQuestionRequest.setProductId(this.productId);            	
            }
            if(this.action == 2){
            	retrieveAdminOptionQuestionRequest.setAction(this.action);
            	retrieveAdminOptionQuestionRequest.setContractSysId(this.paretContractSysId);            	
            }
            // Call the service method
            retrieveAdminOptionQuestionResponse = (RetrieveAdminOptionQuestionResponse) this
                    .executeService(retrieveAdminOptionQuestionRequest);
            if(null != retrieveAdminOptionQuestionResponse){
	            this.associatedquestionnaire = retrieveAdminOptionQuestionResponse.getAssociatedQuestionList();
	            Iterator itr = associatedquestionnaire.iterator();
	            while(itr.hasNext()){
	                AssociatedQuestionnaireBO bo = (AssociatedQuestionnaireBO)itr.next();
	                if("Y".equals(bo.getParentRequired())){
	                	bo.setParentRequiredChecked(true);
	                }else{
	                	bo.setParentRequiredChecked(false);
	                }
	                if(bo.getParentQuestionnaireId()== bo.getQuestionnaireId()){  
	                	bo.setRoot(true);	//not to display the checkbox for  root               	
	                }else{	        
	                	bo.setRoot(false);	// to display the checkbox for  root                  	
	                }	                
	                String ReferenceDesc = null;
	                	String levelInd = getLevelPrefix(bo.getLevel());
	                	bo.setLevelIndicator(levelInd);
	                	if(null == bo.getAnswerDesc()){
	                	    bo.setAnswerDesc(null);
	                	}else{
	                	    String answerDesc =" ["+bo.getAnswerDesc()+ "] " ;
	                	    bo.setAnswerDesc(answerDesc);
	                	}
	                	
	                	ReferenceDesc = " [" + bo.getReferenceDesc() + "] ";
					if (null == bo.getReferenceDesc())
						bo.setReferenceDesc(null);
					else
						bo.setReferenceDesc(ReferenceDesc);
	                    List lobList = bo.getDomainDetail().getLineOfBusiness();
	                    if(null != lobList && !lobList.isEmpty()){
		                    Iterator itrlob = lobList.iterator();
		                    String lob = ((DomainItem)itrlob.next()).getItemId();
		                    while(itrlob.hasNext()){
		                        lob = lob + ", "+((DomainItem)itrlob.next()).getItemId();
		                    }
		                    bo.setLobString(lob);
	                    }
	                    List beList = bo.getDomainDetail().getBusinessEntity();
	                    if(null != beList && !beList.isEmpty()){
		                    Iterator itrbe = beList.iterator();
		                    String be = (String)((DomainItem)itrbe.next()).getItemId();
		                    while(itrbe.hasNext()){
		                        be = be + ", "+((DomainItem)itrbe.next()).getItemId();
		                    }
		                    bo.setBeString(be);
	                    }
	                    List bgList = bo.getDomainDetail().getBusinessGroup();
	                    if(null != bgList && !bgList.isEmpty()){
		                    Iterator itrbg = bgList.iterator();
		                    String bg = ((DomainItem)itrbg.next()).getItemId();
		                    while(itrbg.hasNext()){
		                        bg = bg + ", "+((DomainItem)itrbg.next()).getItemId();
		                    }
		                    bo.setBgString(bg);
	                    }
	                    List buList = bo.getDomainDetail().getMarketBusinessUnit();
	                    if(null != buList && !buList.isEmpty()){
		                    Iterator itrbu = buList.iterator();
		                    String bu = ((DomainItem)itrbu.next()).getItemId();
		                    while(itrbu.hasNext()){
		                        bu = bu + ", "+((DomainItem)itrbu.next()).getItemId();
		                    }
		                    bo.setBuString(bu);
	                    }	                    
	                    
	                    
	                    List funcDomainList = bo.getDomainDetail().getFunctionalDomainList();	                   
	                    if(null != funcDomainList && !funcDomainList.isEmpty()){ 
		                    Iterator itrDomain = funcDomainList.iterator();
		                    String functionalDomain = ((DomainItem) itrDomain.next()).getFunctionalDomain();
		                    while(itrDomain.hasNext()){
		                    	functionalDomain = functionalDomain + ", "+((DomainItem)itrDomain.next()).getFunctionalDomain();
		                    }
		                    bo.setFunctionalDomainDesc(functionalDomain);
	                    }	                    
	            }          
            }
        }     
        return questionnaire;
    }
    /**
     * Method for refreshing the main page which contains the quetsionnaire details.
     * @return
     */
    public String refresh(){
    	   setBreadCrumbText("Administration >> Administration Option "
                   + "(" + this.adminName + ") >> Edit");
    	return "";
    }
    
    public String getLoadDuplicateReference(){
    	if(null != this.getRequest().getSession().getAttribute("AdminId")){
	    	this.adminId = ((Integer)this.getRequest().getSession().getAttribute("AdminId")).intValue();
	    	this.version = ((Integer)this.getRequest().getSession().getAttribute("AdminVersion")).intValue();
	    	this.adminName = this.getRequest().getSession().getAttribute("AdminName").toString();
    	}
    	SearchDuplicateReferenceRequest request = (SearchDuplicateReferenceRequest)this.getServiceRequest(ServiceManager.DUPLICATE_REFERENCE_REQUEST);
    	request.setAdminOptionId(this.getAdminId());
    	SearchDuplicateReferenceResponse searchDuplicateReferenceResponse = null;
    	searchDuplicateReferenceResponse =(SearchDuplicateReferenceResponse)this.executeService(request);
    	if(null != searchDuplicateReferenceResponse){
    		List questionRefList = searchDuplicateReferenceResponse.getResultList();
    		if(null !=questionRefList && !(questionRefList.isEmpty())){
    			Iterator itr = questionRefList.iterator();
    			String refrence ="";
    			while(itr.hasNext()){
    				AssociatedQuestionnaireBO bo = (AssociatedQuestionnaireBO)itr.next();
    				if(!(refrence.equals(bo.getReferenceDesc()))){
    					refrence = bo.getReferenceDesc();
    				}else{
    					bo.setReferenceDesc("");
    				}
    			}
    		}
    		this.setDuplicateQuestionRefList(questionRefList);
    	
    		this.addMessageToRequest(new ErrorMessage( BusinessConstants.ADMIN_OPTION_REFERENCE_UNIQUE));
    	}
    	return WebConstants.EMPTY_STRING;
    }
    
  
    /**
     * Method to manage the tree in the quetsionnaire.
     * @param level
     * @return
     */
    public String getLevelPrefix(int level) {

        StringBuffer buffer = new StringBuffer("    ");
        
        for(int i=1; i<level; i++) {

                    buffer.append("  - ");

        }

        return buffer.toString();
    }
    /**
     * @param questionnaire The questionnaire to set.
     */
    public void setQuestionnaire(String questionnaire) {
        this.questionnaire = questionnaire;
    }
   
    /**Method to get the list of questions in the questionnaire.
     * @return Returns the associatedquestionnaire.
     */
    public List getAssociatedquestionnaire() {
        if (null != this.associatedquestionnaire
                && this.associatedquestionnaire.size() == 0)
            this.associatedquestionnaire = null;
        return associatedquestionnaire;
    }
    /**
     * @param associatedquestionnaire The associatedquestionnaire to set.
     */
    public void setAssociatedquestionnaire(List associatedquestionnaire) {
        this.associatedquestionnaire = associatedquestionnaire;
    }
    /**
     * @return Returns the selectedQuestnrId.
     */
    public String getSelectedQuestnrId() {
        return selectedQuestnrId;
    }
    /**
     * @param selectedQuestnrId The selectedQuestnrId to set.
     */
    public void setSelectedQuestnrId(String selectedQuestnrId) {
        this.selectedQuestnrId = selectedQuestnrId;
    }

	/**Method used for loading the print page for the questionnaire.
	 * @return Returns the printQuestionnaireList.
	 */
	public String getPrintQuestionnaireList() {
		
		if(this.adminId == 0 || this.adminId == -1){
			this.adminId = Integer.parseInt((String)getSession().getAttribute("adminId"));
			this.adminName = (String)getSession().getAttribute("adminname");
			setBreadCrumbText("Administration >> Administration Option "
	                + "(" + this.adminName + ") >> Print");
		}
		
		this.questionnaire = getQuestionnaire();
		
		return printQuestionnaireList;
	}
	/**
	 * @param printQuestionnaireList The printQuestionnaireList to set.
	 */
	public void setPrintQuestionnaireList(String printQuestionnaireList) {
		this.printQuestionnaireList = printQuestionnaireList;
	}
	
		/**
	 * @return Returns the viewQuestionnaire.
	 */
	public String getViewQuestionnaire() {
		this.adminId = new Integer(getSession().getAttribute(
				WebConstants.SESSION_ADMIN_OPTN_ID).toString()).intValue();			
		this.adminName = getSession().getAttribute(WebConstants.SESSION_BNFT_ADMIN_DESC).toString();
		setBreadCrumbText("Administration >> Administration Option "+ "(" + adminName + ") >> View");

		this.questionnaire = getQuestionnaire();		
		return viewQuestionnaire;
	}
	/**
	 * @param viewQuestionnaire The viewQuestionnaire to set.
	 */
	public void setViewQuestionnaire(String viewQuestionnaire) {
		this.viewQuestionnaire = viewQuestionnaire;
	}

	/**
	 * Method for view Questionnaire in product.
	 * 
	 * @return Returns the viewProductQuestionnaire.
	 */
	public String getViewProductQuestionnaire() {		
		productSessionObject = 
        	(ProductSessionObject) getSession().
				getAttribute(WebConstants.PROD_TYPE); 
		if(null != productSessionObject){
			this.productId = productSessionObject.getProductKeyObject().getProductId();
		}
        if(null != getSession().getAttribute(WebConstants.ADMIN_KEY_FOR_QSTNR)) {
        	this.adminId = Integer.parseInt(getSession().getAttribute(WebConstants.ADMIN_KEY_FOR_QSTNR).toString());
        }		 
		this.action = 1;
		this.questionnaire = getQuestionnaire();	
		if(null!= getSession().getAttribute(WebConstants.SESSION_BNFT_ADMIN_DESC)){
			this.adminName = getSession().getAttribute(WebConstants.SESSION_BNFT_ADMIN_DESC).toString();
		}
		setBreadCrumbText("Administration >> Administration Option "+ "(" + adminName + ") >> View");
		return viewProductQuestionnaire;
	}
	
	/**
	 * @return Returns the viewContractQuestionnaire.
	 */
	public String getViewContractQuestionnaire() {
		
		contractSessionObject = 
        	(ContractSession) getSession().
				getAttribute(WebConstants.CONTRACT); 
		if(null != contractSessionObject){
			//setting parent id -this code change added for fixing bug .
			//Before here we passed contratc id instead of parent contract id 
			this.paretContractSysId = contractSessionObject.getContractKeyObject().getContractParentSysId();
		}
		if(-1!= contractSessionObject.getAdminOptionId()){
			this.adminId = contractSessionObject.getAdminOptionId();
		}
/*		if(null!= contractSessionObject.getBenefitComponentDesc()){
			this.adminName = contractSessionObject.getBenefitComponentDesc();
		}*/
     /* if(null != getSession().getAttribute(WebConstants.ADMIN_KEY_FOR_QSTNR)) {
        	this.adminId = Integer.parseInt(getSession().getAttribute(WebConstants.ADMIN_KEY_FOR_QSTNR).toString());
        }	 */
		this.action = 2;
		this.questionnaire = getQuestionnaire();	
		if(null!= getSession().getAttribute(WebConstants.SESSION_BNFT_ADMIN_DESC)){
			this.adminName = getSession().getAttribute(WebConstants.SESSION_BNFT_ADMIN_DESC).toString();
		}
		setBreadCrumbText("Administration >> Administration Option "+ "(" + adminName + ") >> View");
		return viewContractQuestionnaire;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String saveParentReqdStatus(){				
		SaveAdminOptionQuestionnaireRequest saveAdminOptionQuestionnaireRequest = null;
		SaveAdminOptionQuestionnaireResponse saveAdminOptionQuestionnaireResponse = null;
		saveAdminOptionQuestionnaireRequest = 
    		(SaveAdminOptionQuestionnaireRequest) this
                .getServiceRequest(ServiceManager.SAVE_ADM_OPT_PR_QUESTIONNAIRE_REQUEST); 
				
		saveAdminOptionQuestionnaireRequest.setAdminOptionId(this.adminId);
		saveAdminOptionQuestionnaireRequest.setAdminOptionName(this.adminName);
		saveAdminOptionQuestionnaireRequest.setAdminOptionVersion(this.version);
		buildQuestionIdPRStatusMap(getParentRequiredString());
		saveAdminOptionQuestionnaireRequest.setQuestionnaireIdPRMap(getQuestionnaireIdPRMap());
		
	    // Call the service method
		saveAdminOptionQuestionnaireResponse = (SaveAdminOptionQuestionnaireResponse) this
                    .executeService(saveAdminOptionQuestionnaireRequest); 	
        return refresh(); 
	}	
	
	/**
	 * 
	 * @param childQuestions
	 * @param parentRequired
	 * @return
	 */
	private void buildQuestionIdPRStatusMap(String parentRequired){
		Map questionnaireIdPRMap = new HashMap();			
		if(null!=parentRequired && !"".equals(parentRequired)){
			String[] arrayComma = parentRequired.split(",");
	        int countOfQuestions = arrayComma.length; 
	        for (int i = 0; i < countOfQuestions; i++) { 	        	
	            String[] arrayTilda = arrayComma[i].split("~");	            
	            int questionnaireHierarchySystemId = new Integer(arrayTilda[1]).intValue();  
	            
	            String parentRequiredStatus =arrayTilda[0];	
	            questionnaireIdPRMap.put(new Integer(questionnaireHierarchySystemId),parentRequiredStatus);	                       
	        }
	        this.setQuestionnaireIdPRMap(questionnaireIdPRMap);        
         }	
	}
	
	

	/**
	 * Method to set the breadcrumb for view Questionnaire print
	 * @return breadcrumb String
	 */
	public String getPrintBreadCrumb() {	
		return "Administration >> Administration Option "
                + "(" +this.adminName+ ") >> Print";
	}
	
	/**
	 * @param viewProductQuestionnaire The viewProductQuestionnaire to set.
	 */
	public void setViewProductQuestionnaire(String viewProductQuestionnaire) {
		this.viewProductQuestionnaire = viewProductQuestionnaire;
	}
	/**
	 * @return Returns the adminLevelOptSysId.
	 */
	public int getAdminLevelOptSysId() {
		return adminLevelOptSysId;
	}
	/**
	 * @param adminLevelOptSysId The adminLevelOptSysId to set.
	 */
	public void setAdminLevelOptSysId(int adminLevelOptSysId) {
		this.adminLevelOptSysId = adminLevelOptSysId;
	}
	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	/**
	 * @return Returns the selectedParentQuestnrId.
	 */
	public String getSelectedParentQuestnrId() {
		return selectedParentQuestnrId;
	}
	/**
	 * @param selectedParentQuestnrId The selectedParentQuestnrId to set.
	 */
	public void setSelectedParentQuestnrId(String selectedParentQuestnrId) {
		this.selectedParentQuestnrId = selectedParentQuestnrId;
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
	 * @param loadDuplicateReference The loadDuplicateReference to set.
	 */
	public void setLoadDuplicateReference(String loadDuplicateReference) {
		this.loadDuplicateReference = loadDuplicateReference;
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
	 * @return Returns the parentRequiredString.
	 */
	public String getParentRequiredString() {
		return parentRequiredString;
	}
	/**
	 * @param parentRequiredString The parentRequiredString to set.
	 */
	public void setParentRequiredString(String parentRequiredString) {
		this.parentRequiredString = parentRequiredString;
	}

	
	/**
	 * @return Returns the functionalDomainDesc.
	 */
	public String getFunctionalDomainDesc() {
		return functionalDomainDesc;
	}
	/**
	 * @param functionalDomainDesc The functionalDomainDesc to set.
	 */
	public void setFunctionalDomainDesc(String functionalDomainDesc) {
		this.functionalDomainDesc = functionalDomainDesc;
	}
	/**
	 * @return Returns the functionalDomainList.
	 */
	public List getFunctionalDomainList() {
		return functionalDomainList;
	}
	/**
	 * @param functionalDomainList The functionalDomainList to set.
	 */
	public void setFunctionalDomainList(List functionalDomainList) {
		this.functionalDomainList = functionalDomainList;
	}
	/**
	 * @return Returns the searchChilds.
	 */
	public boolean isSearchChilds() {
		return searchChilds;
	}
	/**
	 * @param searchChilds The searchChilds to set.
	 */
	public void setSearchChilds(boolean searchChilds) {
		this.searchChilds = searchChilds;
	}

	/**
	 * @param viewContractQuestionnaire The viewContractQuestionnaire to set.
	 */
	public void setViewContractQuestionnaire(String viewContractQuestionnaire) {
		this.viewContractQuestionnaire = viewContractQuestionnaire;
	}

	/**
	 * @return Returns the contractSysId.
	 */
	public int getContractSysId() {
		return contractSysId;
	}
	/**
	 * @param contractSysId The contractSysId to set.
	 */
	public void setContractSysId(int contractSysId) {
		this.contractSysId = contractSysId;
	}
	/**
	 * @return Returns the paretContractSysId.
	 */
	public int getParetContractSysId() {
		return paretContractSysId;
	}
	/**
	 * @param paretContractSysId The paretContractSysId to set.
	 */
	public void setParetContractSysId(int paretContractSysId) {
		this.paretContractSysId = paretContractSysId;
	}
}
