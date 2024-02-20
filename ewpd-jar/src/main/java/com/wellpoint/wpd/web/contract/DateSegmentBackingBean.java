/*
 * DateSegmentBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import org.owasp.esapi.ESAPI;

import com.wellpoint.wpd.business.contract.locateresult.ContractLocateResult;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.contract.bo.DateSegment;
import com.wellpoint.wpd.common.contract.request.CheckCopyLegacyNoteRequest;
import com.wellpoint.wpd.common.contract.request.CreateDateSegmentRequest;
import com.wellpoint.wpd.common.contract.request.DateSegmentCheckoutRequest;
import com.wellpoint.wpd.common.contract.request.RetrieveDateSegmentsRequest;
import com.wellpoint.wpd.common.contract.request.SaveContractBasicInfoRequest;
import com.wellpoint.wpd.common.contract.response.CheckCopyLegacyNoteResponse;
import com.wellpoint.wpd.common.contract.response.CreateDateSegmentResponse;
import com.wellpoint.wpd.common.contract.response.DateSegmentCheckoutResponse;
import com.wellpoint.wpd.common.contract.response.RetrieveDateSegmentsResponse;
import com.wellpoint.wpd.common.contract.response.SaveContractBasicInfoResponse;
import com.wellpoint.wpd.common.contract.vo.ContractVO;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DateSegmentBackingBean extends ContractBackingBean {

	
	private String dsType=WebConstants.FLAG_N;
	private String contractIdDS;
	private String versionDS;
	private String  statusDS;
	private String selectedContractSysId;
	private String selectedContractId;
	private String comments;
	private String loadDateSegment;
	private DateSegment dateSegment;
    private String dateEntered;
    private String startDate;
    private String endDate;
    private String dateSegmentId;
    private List validationMessages = new ArrayList();
    private List resultList = new ArrayList();
    private List newDSList = new ArrayList();
    private boolean contractLocked;
    private String reasonError;
    private boolean requiredStartDate = false;
    private boolean requiredComments = false;
    private String selectedVersion;
    private String selectedStatus;
    private String pageId;
    private String productStatus;
    private String noteStatus;
    private String selectedProductSysIdFromSearch;
    private String breadCrumb;
    private String breadCrumbPrint;
    private boolean legacyNoteCopyOptionRequired;
    private boolean copyLegacyNotes;
    private int sourceDateSegmentForCreate;
    //Added for SSCR 21516
    private List olderVersionResultList = new ArrayList();
    private String breadCrumbForOlderVersion;

	/**
     * 
     *
     */
    public DateSegmentBackingBean(){
     super();
    }
	 
	    
	    /**
	     * This method is used to clear the validation messages.
	     */
	    public void clear() {
	        validationMessages.clear();
	        this.setRequiredComments(false);
	        if (this.getMessages() != null)
	            this.getMessages().clear();
	    }
	    
	 
	    /**
	     * 
	     * @return
	     */
	    public String cancelAction(){
	    	return "cancelSuccess";
	    }
	    
	    
	    /**
		 * @return Returns the resultList.
		 */
		public List getResultList() {
			
	        RetrieveDateSegmentsRequest retrieveDateSegmentsRequest = (RetrieveDateSegmentsRequest) this
			.getServiceRequest(ServiceManager.RETRIEVE__DATE_SEGMENTS);
	        String sysId= ESAPI.encoder().encodeForHTML(getRequest().getParameter(WebConstants.CONTRACT_KEY));
	        if(null!=sysId  && sysId.matches("^[0-9a-zA-Z_]+$")){
	        	sysId = sysId;
	       }else{
	    	   sysId=null;
	       }
	        String contId = getRequest().getParameter(WebConstants.CONTRACT_ID);
	        if(null!=contId  && contId.matches("^[0-9a-zA-Z_]+$")){
	        	contId = contId;
	       }else{
	    	   contId=null;
	       }
	        String version = getRequest().getParameter(WebConstants.CONTRACT_VERSION);
	        if(null!=version  && version.matches("^[0-9a-zA-Z_]+$")){
	        	version = version;
	       }else{
	    	   version=null;
	       }
	        String status = getRequest().getParameter(WebConstants.STATUS_DESC);
	        if(null!=status  && status.matches("^[0-9a-zA-Z_]+$")){
	        	status = status;
	       }else{
	    	   status=null;
	       }
	        if(null !=  sysId && !sysId.trim().equals(WebConstants.EMPTY_STRING)){
	        	  getContractSession().setContractSysId(Integer.parseInt(sysId));
	        	  getRequest().setAttribute(WebConstants.CONTRACT_SYSTEM_ID,sysId);
	        }
	        if(null !=  contId && !contId.trim().equals(WebConstants.EMPTY_STRING)){
	        	  getContractSession().setContractId(contId);
	        	  getRequest().setAttribute(WebConstants.CONT_ID,contId);
	        }
	        if(null !=  version && !version.trim().equals(WebConstants.EMPTY_STRING)){
	        	  getContractSession().setVersion(Integer.parseInt(version));
	        	  getRequest().setAttribute(WebConstants.CONTRACT_VERSION,version);
	        }
	        if(null !=  status && !status.trim().equals(WebConstants.EMPTY_STRING)){
	        	  getContractSession().setStatus(status);
	        	  getRequest().setAttribute(WebConstants.STATUS_DESC,status);
	        }
	        if( (null == sysId || sysId.trim().equals(WebConstants.EMPTY_STRING))){
	        	sysId = this.selectedContractSysId ;
	        	
	        	if( null != getRequest().getParameter(WebConstants.PAGE) ){
	        	    //means this request comes for the print of view ds from view ds
	        	    if(null != getContractSession()){
	        	        sysId = String.valueOf(getContractSession().getContractSysId());
	        	    }
	        	}
	        	
	        }
	        else{
	        	this.selectedContractSysId = sysId;
	        }
	        if( null != sysId && !sysId.trim().equals(WebConstants.EMPTY_STRING)){
		        int contractSysId = Integer.parseInt(sysId);
		        retrieveDateSegmentsRequest.setContractSysId(contractSysId);
		        RetrieveDateSegmentsResponse searchResponse = (RetrieveDateSegmentsResponse) executeService(
	                retrieveDateSegmentsRequest);
		        if(null != searchResponse ){
		        	 List regularList = new ArrayList();
		        	 List testList = new ArrayList();
		        	 getListsOfRegularAndTest(searchResponse.getSearchResultList(),regularList,testList);
		        	 regularList.addAll(testList);
		        	 this.setResultList(regularList);
		        	 if( null == getRequest().getParameter(WebConstants.PAGE_ID) ){
		        	 	//enters here only if the request is from create DS
					        if("PUBLISHED".equals(getContractSession().getStatus())
					        	|| "TEST_SUCCESSFUL".equals(getContractSession().getStatus())
								|| "TEST_FAILED".equals(getContractSession().getStatus())
								|| "REJECTED".equals(getContractSession().getStatus()) 
								|| "CHECKED_IN".equals(getContractSession().getStatus())
								|| "SCHEDULED_FOR_TEST".equals(getContractSession().getStatus())
								|| "SCHEDULED_FOR_APPROVAL".equals(getContractSession().getStatus())
								){
					        	//to get user a feeling that the contracts have been checked out from published.
	//				        	List list = searchResponse.getSearchResultList();
					        	List targetList = new ArrayList(); 
					        	//to see the results separately.
					        	//HERE the regular list contains both the regular and test
					        	if(regularList.size() !=0){
						        	for(int i=0; i< regularList.size();i++){
						        		ContractLocateResult locResult=(ContractLocateResult) regularList.get(i);
						        		locResult.setStatus("CHECKED_OUT");
						        		targetList.add(locResult);
						        	}
					        	}
					        	 this.setResultList(targetList);
					        }
		        	 }
			        getContractSession().setDateSegmentList(searchResponse.getSearchResultList());
			        contractLocked = false;
		        }
	        }			
			return resultList;
		}
		/**
		 * This method separates the retrieved list according to the regular and test list
		 * @param totalList
		 * @param regualrList
		 * @param testList
		 */
		private void  getListsOfRegularAndTest(List totalList,List regularList,List testList){
			for(int i=0; i<totalList.size(); i++){
				ContractLocateResult locResult = (ContractLocateResult)totalList.get(i);
				if(locResult.getTestIndicator().equals(WebConstants.FLAG_N)){
					regularList.add(locResult);
				}
				else{
					testList.add(locResult);
				}
			}
			
		}
		/**
		 * 
		 * @return
		 */
		public String loadNewList(){
			//requires validation for datenew valid or not?
			String dateNewOne = this.getDateEntered();
			if(null == dateNewOne || dateNewOne.trim().equals(WebConstants.EMPTY_STRING)){
				requiredStartDate = true;
	    		validationMessages.add(new ErrorMessage(WebConstants.MANDATORY_FIELDS_REQUIRED));
                addAllMessagesToRequest(validationMessages);
	    		return WebConstants.EMPTY_STRING;
	    	}
			if(!WPDStringUtil.isValidDate(dateNewOne)){
				requiredStartDate = true;
				ErrorMessage errorMessage = new ErrorMessage(
				        WebConstants.CONTRACT_DATEINVALID);
		        errorMessage.setParameters(new String[] { WebConstants.STARTDATE });
		        validationMessages.add(errorMessage);
		        addAllMessagesToRequest(validationMessages);
	    		return WebConstants.EMPTY_STRING;
			}
			if(dateNewOne.trim().equals(WebConstants.DEFAULT_EXP_DATE)){
				//the date entered is not inside the ranges. so setting the same list
				requiredStartDate = true;
				validationMessages.add(new ErrorMessage(WebConstants.ENTER_DATE_INSIDE));
				addAllMessagesToRequest(validationMessages);
	    		return WebConstants.EMPTY_STRING;
			}
			Date minEffectiveDate = new Date("01/01/1900");

			int lesserDate  = minEffectiveDate.compareTo(WPDStringUtil.getDateFromString(dateNewOne));
			if(lesserDate == 1){
			    requiredStartDate = true;
	    		validationMessages.add(new ErrorMessage(BusinessConstants.EFFECTIVE_DATE_LESS));
                addAllMessagesToRequest(validationMessages);
	    		return WebConstants.EMPTY_STRING;
			}
			
			
			
			Date dateNew = WPDStringUtil.getDateFromString(dateNewOne);
			List oldList = getContractSession().getDateSegmentList();
			if(oldList == null || oldList.size() == 0){
				oldList= this.getResultList();
			}
	       	List regularList = new ArrayList();
	    	List testList = new ArrayList();
			getListsOfRegularAndTest(oldList,regularList,testList );
			if(dsType.equals(WebConstants.FLAG_Y)){
				//Means it is test date seg
				getSession().setAttribute("CHECKOUT_DATESEGMENT_INDICATOR","TEST");
				
				if(testList.size()==0){
					//means going to create new test ds
					ContractLocateResult newTestDS =  new ContractLocateResult();
					newTestDS.setStartDate( dateNew );
					newTestDS.setEndDate(WPDStringUtil.getDateFromString(WebConstants.DEFAULT_EXP_DATE));
					//set all the values required to show in the page
					ContractLocateResult sampleDS =(ContractLocateResult) oldList.get(0);
					newTestDS.setContractId(sampleDS.getContractId());
					newTestDS.setStatus(sampleDS.getStatus());
					newTestDS.setColor(WebConstants.COLOR_RED);
					newTestDS.setTestIndicator(WebConstants.FLAG_Y);
					testList.add(newTestDS);
					regularList.addAll(testList);
					newDSList= regularList;
					return "loadedNew";
				}
				else{
					//we need to insert the entry to the existing test ds
					String returned = createDateSegmentLists(testList,dateNew,true);
					if("outOfRangeDate".equals( returned )){
						//sets the old list
						regularList.addAll(testList);
						newDSList= regularList;
						return WebConstants.EMPTY_STRING;
					}else if("updatedDSList".equals(returned)){
						//test list is already present in the newDSList. need to add regularList
						this.newDSList.addAll(0,regularList);
						return "loadedNew";
					}
				}
			}
			else if( dsType.equals(WebConstants.FLAG_N) ){
				// The type entered is regular
			    if(regularList.size()==0){
					//means going to create new regular ds
					ContractLocateResult newRegularDS =  new ContractLocateResult();
					newRegularDS.setStartDate( dateNew );
					newRegularDS.setEndDate(WPDStringUtil.getDateFromString(WebConstants.DEFAULT_EXP_DATE));
					//set all the values required to show in the page
					ContractLocateResult sampleDS =(ContractLocateResult) oldList.get(0);
					newRegularDS.setContractId(sampleDS.getContractId());
					newRegularDS.setStatus(sampleDS.getStatus());
					newRegularDS.setColor(WebConstants.COLOR_RED);
					newRegularDS.setTestIndicator(WebConstants.FLAG_N);
					regularList.add(newRegularDS);
					regularList.addAll(testList);
					newDSList= regularList;
					// for validation
					checkCopyLegacyNoteAllowed(oldList);
					return "loadedNew";
				}
				else{
					String returned = createDateSegmentLists(regularList,dateNew,false);
					if("outOfRangeDate".equals( returned )){
						//sets the old list
						this.newDSList.add(regularList);
						this.newDSList.addAll(testList);
						return WebConstants.EMPTY_STRING;
						
					}else if("updatedDSList".equals(returned)){
						//regular list is already present in the newDSList. need to add testList
						this.newDSList.addAll(testList);
						checkCopyLegacyNoteAllowed(oldList);
						return "loadedNew";
						
					}else{
						//regular list size is 0
						this.newDSList.add(regularList);
						this.newDSList.addAll(testList);
						return WebConstants.EMPTY_STRING;
					}
				}
			}
			return WebConstants.EMPTY_STRING;
		}
		
		/**
		 * Thia method is used for creating the new date segment list
		 * @param oldList
		 * @param dateNew
		 * @return
		 */
		public String createDateSegmentLists(List oldList,Date dateNew,boolean testBool ){
			List duplicateOldList =  oldList;
			int iFlag = -1;
			if(null != oldList && oldList.size() >0){
				for(int i=0; i< oldList.size(); i++){
					ContractLocateResult resultOld=(ContractLocateResult)oldList.get(i);
					Date dateOldStart =resultOld.getStartDate();
					if(dateNew.compareTo(dateOldStart) < 0  || dateNew.compareTo(dateOldStart) == 0){
						iFlag=1;
						break;
					}
					iFlag=0;
					Date dateOldEnd = resultOld.getEndDate();
					ContractLocateResult resultNew =  new ContractLocateResult(); ;
					if(dateNew.compareTo(dateOldEnd) < 0){
						//adds initial part
						resultNew.setContractId(resultOld.getContractId());
						resultNew.setStartDate(resultOld.getStartDate());
						resultNew.setEndDate(dateNew);
						resultNew.setStatus(resultOld.getStatus());
						resultNew.setColor(WebConstants.COLOR_RED);
						this.sourceDateSegmentForCreate = resultOld.getDateSegmentId();
						getSession().setAttribute("CHECKOUT_DATESEGMENT_ID",new Integer(resultOld.getDateSegmentId()));
						getSession().setAttribute("CHECKOUT_EFFECTIVE_DATE",resultOld.getStartDate());
						if(testBool == true){
							//The entry is test DS
							resultNew.setTestIndicator(WebConstants.FLAG_Y);
							resultNew.setDescription(WebConstants.TEST);
						}else{
							//The entry is regular DS
							resultNew.setTestIndicator(WebConstants.FLAG_N);
							resultNew.setDescription(WebConstants.REGULAR);
						}
						newDSList.add(resultNew);
						//adds second part
						ContractLocateResult resultNew2 = new ContractLocateResult();
						resultNew2.setContractId(resultOld.getContractId());
						resultNew2.setStartDate(dateNew);
						resultNew2.setEndDate(resultOld.getEndDate());
						resultNew2.setStatus(resultOld.getStatus());
						resultNew2.setColor(WebConstants.COLOR_RED);
						if(testBool == true){
							//The entry is test DS
							resultNew2.setTestIndicator(WebConstants.FLAG_Y);
							resultNew.setDescription(WebConstants.TEST);
						}else{
							//The entry is regular DS
							resultNew2.setTestIndicator(WebConstants.FLAG_N);
							resultNew.setDescription(WebConstants.REGULAR);
						}
						newDSList.add(resultNew2);
						//gets the remaining from the old list
						List listTemp = duplicateOldList.subList(i+1,oldList.size() );
						newDSList.addAll( listTemp );
						break;
					}
					else{
						//the date entered is not inside this row.
						//so the same data is entered to the newlist without modification
						resultNew.setContractId(resultOld.getContractId());
						resultNew.setStartDate(resultOld.getStartDate());
						resultNew.setEndDate(resultOld.getEndDate());
						resultNew.setStatus(resultOld.getStatus());
						resultNew.setColor(resultOld.getColor());
						if(testBool == true){
							//The entry is test DS
							resultNew.setTestIndicator(WebConstants.FLAG_Y);
							resultNew.setDescription(WebConstants.TEST);
						}else{
							//The entry is regular DS
							resultNew.setTestIndicator(WebConstants.FLAG_N);
							resultNew.setDescription(WebConstants.REGULAR);
						}
						newDSList.add(resultNew);
						continue;
					}
				}
				if(iFlag ==1){
					//the date entered is not inside the ranges. so setting the same list
					// oldlist contains the list from session or the resultlist
					requiredStartDate = true;
					validationMessages.add(new ErrorMessage(WebConstants.ENTER_DATE_INSIDE));
					addAllMessagesToRequest(validationMessages);
					return "outOfRangeDate";
				}
				addAllMessagesToRequest(validationMessages);
				if(iFlag != 1){
					return "updatedDSList";
				}
			}
			return WebConstants.EMPTY_STRING;
		}
	   /**
	    * 
	    * @return
	    */
	    public String createDateSegmentOne(){
	    	if(this.comments.length() > 4000){
	    		this.validationMessages.add(new ErrorMessage(WebConstants.TEXT_EXCEEDS));
	    		this.comments =WebConstants.EMPTY_STRING;
                loadNewList();
                return "loadedNew";
	    	}
	    	if(null == this.comments || this.comments.trim().equals(WebConstants.EMPTY_STRING)){
	    		requiredComments = true;
	    		this.validationMessages.add(new ErrorMessage(WebConstants.MANDATORY_FIELDS_REQUIRED));
	    		this.comments =WebConstants.EMPTY_STRING;
                loadNewList();
                return "loadedNew";
	    	}
	    	return WebConstants.EMPTY_STRING;
	    }
	
	    /**
	     * This method is used to create a DateSegment.
	     */
	    public String createDateSegment() {
	    
	    	int sysId= getContractSession().getContractSysId();
	        String contId = getContractSession().getContractId();
	        int version = getContractSession().getVersion();
	        String status = getContractSession().getStatus();
	        getContractSession().setDateSegmentList(null);
	        ContractVO contractVO = new ContractVO();
	        contractVO.setContractId(contId);
	        contractVO.setContractSysId(sysId);
	        contractVO.setVersion(version);
	        contractVO.setStatus(status);
	        String dateNew = this.getDateEntered();
	        contractVO.setDateEntered(WPDStringUtil.getDateFromString(dateNew));
	        CreateDateSegmentRequest createDateSegmentsRequest = (CreateDateSegmentRequest) this
			.getServiceRequest(ServiceManager.CREATE__DATE_SEGMENTS);
	        createDateSegmentsRequest.setComments(this.comments);
	        Integer dateSegmentOld =(Integer) getSession().getAttribute("CHECKOUT_DATESEGMENT_ID");
	        int dateSegmentOldInt = 0;
	        if(null != dateSegmentOld){
	        	dateSegmentOldInt  = dateSegmentOld.intValue();
	        }
	        contractVO.setDateSegmentSysId(dateSegmentOldInt);
	        if(null != this.selectedContractSysId || !this.selectedContractSysId.trim().equals(WebConstants.EMPTY_STRING)){
		        int contractSysId = Integer.parseInt(this.selectedContractSysId);
		        createDateSegmentsRequest.setContractSysId(contractSysId);
		        createDateSegmentsRequest.setDateEntered(this.getDateEntered());
		        createDateSegmentsRequest.setComments(this.comments);
		        createDateSegmentsRequest.setDsType(this.getDsType());
		        contractVO.setProductStatus(this.productStatus);
				 String noteArray[] = noteStatus.split(WebConstants.TILDA);
				 int size = noteArray.length;    
				 List dateSegmentList = new ArrayList();
				 for(int i=1;i<size;i++){
				     String effectiveDate = noteArray[i];
				     if(WPDStringUtil.isValidDate(effectiveDate)){
				         DateSegment dateSegment = new DateSegment();
				         dateSegment.setEffectiveDate(WPDStringUtil.getDateFromString(noteArray[i]));
				         dateSegmentList.add(dateSegment);
				         contractVO.setNoteStatus(WebConstants.REPLACE_NOTE);
				     }
				 }
				 contractVO.setDateSegmentList(dateSegmentList);
				 createDateSegmentsRequest.setContractVO(contractVO);
				 createDateSegmentsRequest.setCopyLegacyNotes(this.copyLegacyNotes);
				 CreateDateSegmentResponse createResponse = (CreateDateSegmentResponse) executeService(
		        		createDateSegmentsRequest);
				updateTreeStructure();
		        Application application =
					FacesContext.getCurrentInstance().getApplication();
		        if(null != createResponse ){
		        	if(createResponse.isSuccess()== false || createResponse.isLockAcquired() == false){
		        		 List messageList = createResponse.getMessages();
			       		 ContractSearchBackingBean contractSearchBackingBean=(ContractSearchBackingBean)getRequest().getAttribute("contractSearchBackingBean");
			       		 contractSearchBackingBean.performLocate();
			       		 addAllMessagesToRequest(messageList);
		       		 	return WebConstants.EMPTY_STRING;
		        	}
		        	 super.setContractToSession(createResponse.getContract());
		             DateSegment dateSegment =(DateSegment)createResponse.getContract().getDateSegmentList().get(0);
		             String type = dateSegment.getDateSegmentType();
		             if(type.equals(WebConstants.FLAG_Y)){
		             	getContractSession().setInitialTest(WebConstants.FLAG_Y);
		             }
					ContractBasicInfoBackingBean backingBean =
	        		((ContractBasicInfoBackingBean)application.createValueBinding("#{contractBasicInfoBackingBean}").
	        		getValue(FacesContext.getCurrentInstance()));
					super.setEditMode();
			        getSession().setAttribute("MESSAGE_LIST",createResponse.getMessages());
	        		return backingBean.getBasicInfo();
		        }
	        }
	       return WebConstants.EMPTY_STRING;
	    }
	    
	 /**
	  * 
	  * @return
	  */
	public String editAction(){
		ContractKeyObject keyObject = new ContractKeyObject();
		keyObject.setContractSysId(Integer.parseInt(this.getSelectedContractSysId()));
		keyObject.setContractId(this.getContractIdDS());
		keyObject.setDateSegmentId(Integer.parseInt(this.getDateSegmentId()));
		keyObject.setVersion(Integer.parseInt(this.getVersionDS()));
		keyObject.setStatus(this.getStatusDS());
		this.getContractSession().setContractKeyObject(keyObject);
		DateSegmentCheckoutRequest request = (DateSegmentCheckoutRequest)this.getServiceRequest(ServiceManager.DATESEGMENT_CHECKOUT_REQUEST);
		DateSegmentCheckoutResponse response = (DateSegmentCheckoutResponse)this.executeService(request);
		if(null != response && response.isSuccess()){
			keyObject.setDateSegmentId(response.getDateSegmentId());
		}
		request.setContractKeyObject(keyObject);
		super.setEditMode();
		Application application =
			FacesContext.getCurrentInstance().getApplication();
		
		ContractBasicInfoBackingBean contractBasicInfoBackingBean =
		((ContractBasicInfoBackingBean) application.createValueBinding("#{contractBasicInfoBackingBean}").
		getValue(FacesContext.getCurrentInstance()));
		if (null != getSession().getAttribute("SESSION_TREE_STATE_CONTRACT"))
            getSession().removeAttribute("SESSION_TREE_STATE_CONTRACT");
		updateTreeStructure();
		return contractBasicInfoBackingBean.getBasicInfo();
	}
	
	
	/**
	 * @return Returns the selectedContractId.
	 */
	public String getSelectedContractId() {
		return selectedContractId;
	}
	/**
	 * @param selectedContractId The selectedContractId to set.
	 */
	public void setSelectedContractId(String selectedContractId) {
		this.selectedContractId = selectedContractId;
	}
	/**
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return Returns the dateSegment.
	 */
	public DateSegment getDateSegment() {
		return dateSegment;
	}
	/**
	 * @param dateSegment The dateSegment to set.
	 */
	public void setDateSegment(DateSegment dateSegment) {
		this.dateSegment = dateSegment;
	}
	/**
	 * @param loadDateSegment The loadDateSegment to set.
	 */
	public void setLoadDateSegment(String loadDateSegment) {
		this.loadDateSegment = loadDateSegment;
	}
	/**
	 * @return Returns the dateEntered.
	 */
	public String getDateEntered() {
		return dateEntered;
	}
	/**
	 * @param dateEntered The dateEntered to set.
	 */
	public void setDateEntered(String dateEntered) {
		this.dateEntered = dateEntered;
	}
	/**
	 * @return Returns the endDate.
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the startDate.
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return Returns the requiredComments.
	 */
	public boolean isRequiredComments() {
		return requiredComments;
	}
	/**
	 * @param requiredComments The requiredComments to set.
	 */
	public void setRequiredComments(boolean requiredComments) {
		this.requiredComments = requiredComments;
	}
	/**
	 * @return Returns the requiredStartDate.
	 */
	public boolean isRequiredStartDate() {
		return requiredStartDate;
	}
	/**
	 * @param requiredStartDate The requiredStartDate to set.
	 */
	public void setRequiredStartDate(boolean requiredStartDate) {
		this.requiredStartDate = requiredStartDate;
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
	 * @param resultList The resultList to set.
	 */
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	/**
	 * @return Returns the contractLocked.
	 */
	public boolean isContractLocked() {
		return contractLocked;
	}
	/**
	 * @param contractLocked The contractLocked to set.
	 */
	public void setContractLocked(boolean contractLocked) {
		this.contractLocked = contractLocked;
	}
	/**
	 * @return Returns the reasonError.
	 */
	public String getReasonError() {
		return reasonError;
	}
	/**
	 * @param reasonError The reasonError to set.
	 */
	public void setReasonError(String reasonError) {
		this.reasonError = reasonError;
	}
	
    
	/**
	 * @return Returns the selectedContractSysId.
	 */
	public String getSelectedContractSysId() {
		return selectedContractSysId;
	}
	/**
	 * @param selectedContractSysId The selectedContractSysId to set.
	 */
	public void setSelectedContractSysId(String selectedContractSysId) {
		this.selectedContractSysId = selectedContractSysId;
	}
	/**
	 * @return Returns the newDSList.
	 */
	public List getNewDSList() {
		return newDSList;
	}
	/**
	 * @param newDSList The newDSList to set.
	 */
	public void setNewDSList(List newDSList) {
		this.newDSList = newDSList;
	}
	/**
	 * @return Returns the contractIdDS.
	 */
	public String getContractIdDS() {
		return contractIdDS;
	}
	/**
	 * @param contractIdDS The contractIdDS to set.
	 */
	public void setContractIdDS(String contractIdDS) {
		this.contractIdDS = contractIdDS;
	}
	/**
	 * @return Returns the statusDS.
	 */
	public String getStatusDS() {
		return statusDS;
	}
	/**
	 * @param statusDS The statusDS to set.
	 */
	public void setStatusDS(String statusDS) {
		this.statusDS = statusDS;
	}
	/**
	 * @return Returns the versionDS.
	 */
	public String getVersionDS() {
		return versionDS;
	}
	/**
	 * @param versionDS The versionDS to set.
	 */
	public void setVersionDS(String versionDS) {
		this.versionDS = versionDS;
	}
	/**
	 * Returns the dateSegmentId
	 * @return String dateSegmentId.
	 */
	public String getDateSegmentId() {
		return dateSegmentId;
	}
	/**
	 * Sets the dateSegmentId
	 * @param dateSegmentId.
	 */
	public void setDateSegmentId(String dateSegmentId) {
		this.dateSegmentId = dateSegmentId;
	}
	/**
	 * @return Returns the selectedStatus.
	 */
	public String getSelectedStatus() {
		return selectedStatus;
	}
	/**
	 * @param selectedStatus The selectedStatus to set.
	 */
	public void setSelectedStatus(String selectedStatus) {
		this.selectedStatus = selectedStatus;
	}
	/**
	 * @return Returns the selectedVersion.
	 */
	public String getSelectedVersion() {
		return selectedVersion;
	}
	/**
	 * @param selectedVersion The selectedVersion to set.
	 */
	public void setSelectedVersion(String selectedVersion) {
		this.selectedVersion = selectedVersion;
	}
	/**
	 * @return Returns the pageId.
	 */
	public String getPageId() {
		return pageId;
	}
	/**
	 * @param pageId The pageId to set.
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	/**
	 * @return Returns the dsType.
	 */
	public String getDsType() {
		return dsType;
	}
	/**
	 * @param dsType The dsType to set.
	 */
	public void setDsType(String dsType) {
		this.dsType = dsType;
	}
	/**
	 * Returns the noteStatus
	 * @return String noteStatus.
	 */
	public String getNoteStatus() {
		return noteStatus;
	}
	/**
	 * Sets the noteStatus
	 * @param noteStatus.
	 */
	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}
	/**
	 * Returns the productStatus
	 * @return String productStatus.
	 */
	public String getProductStatus() {
		return productStatus;
	}
	/**
	 * Sets the productStatus
	 * @param productStatus.
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	/**
	 * Returns the selectedProductSysIdFromSearch
	 * @return String selectedProductSysIdFromSearch.
	 */
	public String getSelectedProductSysIdFromSearch() {
		return selectedProductSysIdFromSearch;
	}
	/**
	 * Sets the selectedProductSysIdFromSearch
	 * @param selectedProductSysIdFromSearch.
	 */
	public void setSelectedProductSysIdFromSearch(
			String selectedProductSysIdFromSearch) {
		this.selectedProductSysIdFromSearch = selectedProductSysIdFromSearch;
	}
	/**
     * Returns the breadCrumb
     * @return String breadCrumb.
     */
    public String getBreadCrumb() {
    	String contractId = (String) ESAPI.encoder().encodeForHTML(getRequest().getParameter("contractId"));
    	if(null!=contractId  && contractId.matches("^[0-9a-zA-Z_]+$")){
    		contractId = contractId;
    	}else{
    		contractId=null;
    	}
    	if(contractId == null){
    	   contractId = this.getContractSession().getContractId();
    	}
        breadCrumb = "Locate >> Contract ("+contractId+")>> View All Date Segments";
        this.setBreadCrumbText(breadCrumb);
        return breadCrumb;
    }
    /**
     * Sets the breadCrumb
     * @param breadCrumb.
     */
    public void setBreadCrumb(String breadCrumb) {
        this.breadCrumb = breadCrumb;
    }
    /**
     * Returns the breadCrumbPrint
     * @return String breadCrumbPrint.
     */
    public String getBreadCrumbPrint() {

        String contractId = this.getContractSession().getContractId();
        breadCrumbPrint = "Locate >> Contract ("+contractId+")>> View All Date Segments >> Print";
        
        return breadCrumbPrint;
    }
    /**
     * Sets the breadCrumbPrint
     * @param breadCrumbPrint.
     */
    public void setBreadCrumbPrint(String breadCrumbPrint) {
        this.breadCrumbPrint = breadCrumbPrint;
    }
    
    
	/**
	 * @return Returns the copyLegacyNotes.
	 */
	public boolean isCopyLegacyNotes() {
		return copyLegacyNotes;
	}
	/**
	 * @param copyLegacyNotes The copyLegacyNotes to set.
	 */
	public void setCopyLegacyNotes(boolean copyLegacyNotes) {
		this.copyLegacyNotes = copyLegacyNotes;
	}
	/**
	 * @return Returns the legacyNoteCopyOptionRequired.
	 */
	public boolean isLegacyNoteCopyOptionRequired() {
		return legacyNoteCopyOptionRequired;
	}
	/**
	 * @param legacyNoteCopyOptionRequired The legacyNoteCopyOptionRequired to set.
	 */
	public void setLegacyNoteCopyOptionRequired(
			boolean legacyNoteCopyOptionRequired) {
		this.legacyNoteCopyOptionRequired = legacyNoteCopyOptionRequired;
	}
	/**
	 * This method will check the validations.
	 * 1. Whether copyLegacyNote indicator is on or off
	 * 2. Legacy notes exists or not.
	 * Based on this validation system promts the user with copy legacy note checkbox.
	 * @param regularDSList
	 */
	private void checkCopyLegacyNoteAllowed(List regularDSList){
		
		//ContractLocateResult regularDS =(ContractLocateResult) regularDSList.get(0);
		int dateSegmentID = this.sourceDateSegmentForCreate;
		CheckCopyLegacyNoteRequest request = (CheckCopyLegacyNoteRequest) this.getServiceRequest(ServiceManager.CHECK_COPY_LEGACY_NOTE);
		request.setDateSegmentId(dateSegmentID);
		CheckCopyLegacyNoteResponse response= (CheckCopyLegacyNoteResponse) executeService(request);
		
		if(response.isLegacyNotesExist() && response.isCopyLegacyNoteIndcatorOn()){
			legacyNoteCopyOptionRequired = true;
		}
		
	}
	/**
	 * @return Returns the sourceDateSegmentForCreate.
	 */
	public int getSourceDateSegmentForCreate() {
		return sourceDateSegmentForCreate;
	}
	/**
	 * @param sourceDateSegmentForCreate The sourceDateSegmentForCreate to set.
	 */
	public void setSourceDateSegmentForCreate(int sourceDateSegmentForCreate) {
		this.sourceDateSegmentForCreate = sourceDateSegmentForCreate;
	}
	
	//Added to view Older Versions of a Date Segment-- As part of SSCR 21516
	
    /**
	 * @return Returns the olderVersiuonResultList.
	 */
	public List getOlderVersionResultList() {
		
        RetrieveDateSegmentsRequest retrieveDateSegmentsRequest = (RetrieveDateSegmentsRequest) this
		.getServiceRequest(ServiceManager.RETRIEVE__DATE_SEGMENTS);
        retrieveDateSegmentsRequest.setAction(RetrieveDateSegmentsRequest.FETCH_OLDER_VERSIONS);
        String contId = getRequest().getParameter("contractID");
        if(null!=contId  && contId.matches("^[0-9a-zA-Z_]+$")){
        	contId = contId;
       }else{
    	   contId=null;
       }
        String version = getRequest().getParameter(WebConstants.CONTRACT_VERSION);
        if(null!=version  && version.matches("^[0-9a-zA-Z_]+$")){
        	version = version;
       }else{
    	   version=null;
       }
        String effectiveDate = getRequest().getParameter("contractEffDate");
       // String expiryDate = getRequest().getParameter("contractExpDate");

        if(null !=  contId && !contId.trim().equals(WebConstants.EMPTY_STRING)){
        	  getContractSession().setContractId(contId);
        	  getRequest().setAttribute(WebConstants.CONT_ID,contId);
        }
        if(null !=  version && !version.trim().equals(WebConstants.EMPTY_STRING)){
        	  getContractSession().setVersion(Integer.parseInt(version));
        	  getRequest().setAttribute(WebConstants.CONTRACT_VERSION,version);
        }
      
        if( null != contId && !contId.trim().equals(WebConstants.EMPTY_STRING)){
	        retrieveDateSegmentsRequest.setContractId(contId);
	        retrieveDateSegmentsRequest.setEffectiveDate(WPDStringUtil.getDateFromString(effectiveDate));
	        //retrieveDateSegmentsRequest.setExpiryDate(WPDStringUtil.getDateFromString(expiryDate));
	        if(null != version && !version.trim().equals(WebConstants.EMPTY_STRING)){
	        	int versionNum = Integer.parseInt(version);
	        	retrieveDateSegmentsRequest.setVersion(versionNum);
	        }
	        RetrieveDateSegmentsResponse searchResponse = (RetrieveDateSegmentsResponse) executeService(
                retrieveDateSegmentsRequest);
	        if(null != searchResponse ){
	        	olderVersionResultList = searchResponse.getSearchResultList();
	        }
        }			
		return olderVersionResultList;
	}

	public void setOlderVersionResultList(List olderVersionResultList) {
		this.olderVersionResultList = olderVersionResultList;
	}

	public String getBreadCrumbForOlderVersion() {

    	String contractId = (String) ESAPI.encoder().encodeForHTML(getRequest().getParameter("contractId"));
    	if(null!=contractId  && contractId.matches("^[0-9a-zA-Z_]+$")){
    		contractId = contractId.toString();
    	}else{
    		contractId=null;
    	}
    	if(contractId == null){
    	   contractId = this.getContractSession().getContractId();
    	}
    	breadCrumbForOlderVersion = "Locate >> Contract ("+contractId+")>> View Older Versions";
        this.setBreadCrumbText(breadCrumbForOlderVersion);
        return breadCrumbForOlderVersion;
	}

	public void setBreadCrumbForOlderVersion(String breadCrumbForOlderVersion) {
		this.breadCrumbForOlderVersion = breadCrumbForOlderVersion;
	}
	
}
