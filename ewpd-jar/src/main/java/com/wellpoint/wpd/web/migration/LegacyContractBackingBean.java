/*
 * LegacyContractBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.migration;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.wellpoint.wpd.business.migration.util.MigrationContractUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.legacycontract.bo.CP2000Contract;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContract;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContractMajorHeading;
import com.wellpoint.wpd.common.legacycontract.bo.LegacyContractNotes;
import com.wellpoint.wpd.common.legacycontract.request.RetrieveLegacyContarctNotesRequest;
import com.wellpoint.wpd.common.legacycontract.request.RetrieveLegacyContractRequest;
import com.wellpoint.wpd.common.legacycontract.response.RetrieveLegacyContractNotesResponse;
import com.wellpoint.wpd.common.legacycontract.response.RetrieveLegacyContractResponse;
import com.wellpoint.wpd.common.legacycontract.vo.LegacyContractNotesVO;
import com.wellpoint.wpd.common.legacycontract.vo.LegacyContractVO;
import com.wellpoint.wpd.common.migration.bo.MigrationDateSegment;
import com.wellpoint.wpd.common.migration.request.SaveLastAccessPageInfoRequest;
import com.wellpoint.wpd.common.migration.request.SaveLegacyContractRequest;
import com.wellpoint.wpd.common.migration.response.SaveLegacyContractResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LegacyContractBackingBean extends MigrationBaseBackingBean {

	private String MIGRATION = WebConstants.MIGRATION;

	private String RENEW = WebConstants.RENEW;
	
	private String FROM_DATE = WebConstants.FROM_DATE;
	
	private String fromDate;
	
	private String numberOfYears;

	private String LATEST_DATE_SEGMENT = WebConstants.LATEST_DATE_SEGMENT;

	private String ALL_DATE_SEGMENT = WebConstants.ALL_DATE_SEGMENT;

	private String RENEW_EXISTING_DATE_SEGMENT = WebConstants.RENEW_EXISTING_DATE_SEGMENT;
	
	private String LATEST_TWO_DATE_SEGMENT = WebConstants.LATEST_TWO_DATE_SEGMENT;

    private String contractId;

    private String sourceSystem;

    private List legacySystem;

    private String option = "3";

    private List validationMessages = null;

    private boolean requiredContractId;

    private List cp2000ContractList;

    private int latestDateSegmentCount = 0;

    private String selectedDateSegmentFromStep2;

    private int cp2000ContractListSize = 0;

    private boolean radioReadOnly = false;
    
    private boolean isLocked = false;

    private boolean isMigCompleted = false;

    private LegacyContract legacyContract;

    private boolean requiredContractID;

    private boolean requiredSourceSystem;

    private boolean requiredOption;

    private String newDate = "";

    private int selectedContractKeyFromSearch;

    private String selectedContractTypeFromSearch;

    private int selectedDateSegKeyFromSearch;

    private int selectedVerionFromSearch;

    private String selectedStatusFromSearch;

    private boolean requiredNewDate;

    private String selectedContract;
    
    private boolean afterMigrationDateSegmentExist = false;

    private boolean userConfirmToAddNewDateSegment = false;
    
    private boolean effectiveDateInvalid = false;
    
    private boolean anyDSCompletedStep8;
    
    private static String BREAD_CRUMB_TEXT_STEP1 = WebConstants.CONTRACT_BREADCRUMB;

    private static String BREAD_CRUMB_TEXT_STEP2 = WebConstants.MIGRATION_BREAD_CRUMB_STEP2;
    
    private List contractNotes;
    
    private List majorHeadingList = null;
    
    private List minorHeadingList = null;
    
    private List majorNotesList = null;
    
    private List minorNotesList = null;
    
    private List majorHeadingDescList = null;
    
    private String majorHeadingId;
    
    private String cntrctNotesCheckBox =WebConstants.FLAG_N;
    
    private String majorHeadingHidden;
    
    private String minorHeadingHidden;
    
    private String majorNotesFlag = WebConstants.FLAG_N;
    
    private String minorNotesFlag =WebConstants.FLAG_N;

    private String minorHeadingFromScreen ;
    private List messageList;
    
    private String selectedDS;
    
    private String page;
    
    private String pageBreadcrumb;
	
    /**
     * cONSTRUCTOR
     */
    public LegacyContractBackingBean() {
        this.setBreadCrumbText(BREAD_CRUMB_TEXT_STEP1);
    }


    /**
     * @return String
     */
    public String goToStep2FromStep3() {
        this.option = super.getMigrationContractSession()
                .getMigrationContract().getOption();
        if (this.option != null
                && ((this.option.equals(BusinessConstants.OPT_MIGRATE_ALL_DS)) || (this.option
                        .equals(BusinessConstants.OPT_MIGRATE_DS)))) {
            this.contractId = super.getMigrationContractSession()
                    .getMigrationContract().getContractId();
            this.sourceSystem = super.getMigrationContractSession()
                    .getMigrationContract().getSystem();
            SaveLastAccessPageInfoRequest saveLastAccessPageInfoRequest = (SaveLastAccessPageInfoRequest) this
										.getServiceRequest(ServiceManager.RETRIEVE_LAST_ACCESS_PAGE_REQUEST);
            saveLastAccessPageInfoRequest.setMigrationContractSession(super.getMigrationContractSession());
            super.executeService(saveLastAccessPageInfoRequest);
            return this.step2AllDS();
        } else {
            return this.goToNextPage(WebConstants.MIG_CONTRACT_STEP1);

        }
    }

    /**
     * @return String
     */
    private String step2AllDS() {
        RetrieveLegacyContractRequest request;
        RetrieveLegacyContractResponse response;
        request = this.getRetrieveLegacyContractRequest();
        request.setUserConfirmToAddNewDS(false);
        response = (RetrieveLegacyContractResponse) executeService(request);
        if (response != null && response.isSuccess()) {
            this.setToSession(response);

            this.cp2000ContractList = response.getMigrationdateSegmentList();
            this.contractId = this.getMigrationContractSession()
                    .getMigrationContract().getContractId();

            this.setBreadCrumbText(BREAD_CRUMB_TEXT_STEP2);
            return WebConstants.MIG_CONTRACT_STEP_ALL_DS;
        }
        return WebConstants.EMPTY_STRING;
    }


    /**
     * @return String
     */
    public String goToStep2() {
        RetrieveLegacyContractRequest request;
        RetrieveLegacyContractResponse response;
    	List reverseList = null;
    	
        String navigation = null;

        if (!validateRequiredFields()) {
            addAllMessagesToRequest(validationMessages);
            this.setBreadCrumbText(BREAD_CRUMB_TEXT_STEP1);
            return WebConstants.EMPTY_STRING;
        }
        request = this.getRetrieveLegacyContractRequest();
        response = (RetrieveLegacyContractResponse) executeService(request);
        
        if (response != null && response.isSuccess()) {
        	
            this.afterMigrationDateSegmentExist = response.isAfterMigrationDateSegmentExist();
            
//            if (response.isNewMigration() || request.isUserConfirmToAddNewDS()) {
            if (response.isNewMigration() || this.afterMigrationDateSegmentExist) {
            	
                this.cp2000ContractList = response.getDateSegmentList();
                this.cp2000ContractListSize = cp2000ContractList.size();
                this.option = response.getOption();
                
                setDisplayFlag(cp2000ContractList, this.option);
                // 	To display the image in front end
                if (BusinessConstants.OPT_MIGRATE_DS
                                .equals(this.option))
                    navigation = WebConstants.DATE_SEGMENT;
                else if (BusinessConstants.OPT_RENEW_DS.equals(option))
                    navigation = WebConstants.RENEW_DATE_SEGMENT;
                this.setBreadCrumbText(BREAD_CRUMB_TEXT_STEP2);
                return navigation;
            } else if(!this.afterMigrationDateSegmentExist){
                this.setToSession(response);
                if (this.option != null
                        && (this
                                .getOption()
                                .equals(BusinessConstants.OPT_MIGRATE_DS))) {
                    this.setBreadCrumbText(BREAD_CRUMB_TEXT_STEP2);
					if(null!=response.getMigrationContractSession()
					        .getNavigationInfo()){
						 if (response.getMigrationContractSession()
	                            .getNavigationInfo().getLastAccessedPage().equals(
	                                    WebConstants.MIG_CONTRACT_STEP2)) {
	                        this.cp2000ContractList = response
	                                .getMigrationdateSegmentList();
	                        this.contractId = this.getMigrationContractSession()
	                                .getMigrationContract().getContractId();
	                        return WebConstants.MIG_CONTRACT_STEP_ALL_DS;
	                    }
						
					}
                   

                }
                return getLastAccessedPage(response);

            }
        }
        this.setBreadCrumbText(BREAD_CRUMB_TEXT_STEP1);
        return WebConstants.EMPTY_STRING;
    }


    /**
     * @param dsList
     * @param option
     */
    private void setDisplayFlag(List dsList, String option) {
        if (dsList == null)
            return;
        CP2000Contract contract;
        int listSize = dsList.size();

        if (BusinessConstants.OPT_MIGRATE_LATEST_DS.equals(option)
                && listSize > 0) {
            contract = (CP2000Contract) dsList.get(listSize - 1);
            contract.setDisplayFlag(true);
        } else if (BusinessConstants.OPT_MIGRATE_DS.equals(option)
                && listSize > 1) {
            contract = (CP2000Contract) dsList.get(listSize - 1);
            contract.setDisplayFlag(true);
            contract = (CP2000Contract) dsList.get(listSize - 2);
            contract.setDisplayFlag(true);
        } else if (BusinessConstants.OPT_MIGRATE_ALL_DS.equals(option)) {
            for (java.util.Iterator iter = dsList.iterator(); iter.hasNext();) {
                contract = (CP2000Contract) iter.next();
                contract.setDisplayFlag(true);
            }
        }
    }


    /**
     * @return RetrieveLegacyContractRequest
     */
    public RetrieveLegacyContractRequest getRetrieveLegacyContractRequest() {

        RetrieveLegacyContractRequest retrieveLegacyContractRequest = (RetrieveLegacyContractRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_LEGACY_CONTRACT_REQUEST);
        LegacyContractVO legacyContractVO = new LegacyContractVO();
        legacyContractVO.setContractId(this.getContractId().trim()
                .toUpperCase());
        legacyContractVO.setSourceSystem(this.getSourceSystem());
        legacyContractVO.setOption(this.getOption());
        retrieveLegacyContractRequest.setLegacyContractVO(legacyContractVO);
        retrieveLegacyContractRequest.setUserConfirmToAddNewDS(this.userConfirmToAddNewDateSegment);
        return retrieveLegacyContractRequest;
    }
    
    
    /**
     * @return boolean
     */
    public boolean validateRequiredFields() {
        validationMessages = new ArrayList();
        boolean requiredField = false;
        this.requiredContractId = false;
        this.requiredSourceSystem = false;
        this.requiredOption = false;
        if (this.contractId == null || WebConstants.EMPTY_STRING.equals(this.contractId.trim())) {

            this.requiredContractId = true;
            requiredField = true;
        }

        if (this.sourceSystem == null || WebConstants.EMPTY_STRING.equals(this.sourceSystem)) {
            this.requiredSourceSystem = true;
            requiredField = true;
        }

        if (this.option == null || WebConstants.EMPTY_STRING.equals(this.option)) {
            this.requiredOption = true;
            requiredField = true;
        }

        if (requiredField) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }

        if (this.option == null) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }

        return true;
    }


    /**
     * @return boolean
     */
    public boolean validateRequiredFieldsForStep3() {
        validationMessages = new ArrayList();

        if (this.newDate == null || WebConstants.EMPTY_STRING
                .equals(this.newDate.trim())) {
        	this.effectiveDateInvalid = true;
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            addAllMessagesToRequest(validationMessages);
            return false;
        }
        
        if (!WPDStringUtil.isValidDate(this.getNewDate())) {
            ErrorMessage errorMessage = new ErrorMessage(
                    WebConstants.INPUT_FORMAT_INVALID);
            errorMessage
                    .setParameters(new String[] { WebConstants.EFFECTIVE_DATE });
            validationMessages.add(errorMessage);
            addAllMessagesToRequest(validationMessages);
            return false;
        }

        return true;
    }


    /**
     * @return String
     */
    public String goToStep3() {

        SaveLegacyContractRequest saveLegacyContractRequest = null;
        SaveLegacyContractResponse saveLegacyContractResponse = null;
        String lastAccessedPage = null;
        saveLegacyContractRequest = this.setSaveLegacyContractRequest();
        validationMessages = new ArrayList();
        
        if (option != null
                &&  ((this.option)
                        .equals(BusinessConstants.OPT_MIGRATE_DS))) {
            this.setToRequest(saveLegacyContractRequest);
            saveLegacyContractResponse = (SaveLegacyContractResponse) this
                    .executeService(saveLegacyContractRequest);
            
            this.setToSession(saveLegacyContractResponse);
            if(super.getMigrationContractSession()
            		.getMigrationContract()
					.getDoneFlag()
					.equals(MigrationContractUtil.DONE_FLAG_SECOND)){
			    super.getMigrationContractSession()
					 .getMigrationContract()
					 .setOption(BusinessConstants.OPT_MIGRATE_DS);			
            }
            lastAccessedPage = getLastAccessedPage(saveLegacyContractResponse);
        }

        if (option != null
                && ((this.option).equals(BusinessConstants.OPT_RENEW_DS))) {

            if (this.getSelectedDateSegmentFromStep2() == null
                    || this.getSelectedDateSegmentFromStep2().equals(WebConstants.FALSE)) {
                validationMessages.add(new ErrorMessage(
                        WebConstants.SELECT_DATE_SEGMENT));
                addAllMessagesToRequest(validationMessages);
                lastAccessedPage = WebConstants.EMPTY_STRING;
            } else {

                Date selectedDate = WPDStringUtil.getDateFromString(this
                        .getNewDate());
                List contractList = this.getCp2000ContractList();
                LegacyContract contract = (LegacyContract) contractList
                        .get(0);
                Date startDate = WPDStringUtil.getDateFromString(contract
                        .getStartDateString());
                Date endDate = WPDStringUtil.getDateFromString(contract
                        .getEndDateString());
                if (validateRequiredFieldsForStep3()) {
                    if ((!selectedDate.after(startDate))
                            || (!selectedDate.before(endDate))) {
                        validationMessages.add(new ErrorMessage(
                                WebConstants.INVALID_DATE_SEGMENT_ENTRY));
                        addAllMessagesToRequest(validationMessages);
                        lastAccessedPage = WebConstants.EMPTY_STRING;
                    } else {
                        this.setToRequest(saveLegacyContractRequest);
                        saveLegacyContractResponse = (SaveLegacyContractResponse) this
                                .executeService(saveLegacyContractRequest);
                        this.setToSession(saveLegacyContractResponse);
                        lastAccessedPage = getLastAccessedPage(saveLegacyContractResponse);
                    }
                }
            }
        }

        return lastAccessedPage;

    }


    /**
     * @return SaveLegacyContractRequest
     */
    private SaveLegacyContractRequest setSaveLegacyContractRequest() {

        SaveLegacyContractRequest saveLegacyContractRequest = (SaveLegacyContractRequest) this
                .getServiceRequest(ServiceManager.SAVE_LEGACY_CONTRACT_REQUEST);
        if(null == this.page || "".equals(this.page)){
        	// Renew Option is selected
        	saveLegacyContractRequest.setAllDateSegments(this
                    .getCp2000ContractList());
        }else{
	        if("multiplePage".equals(this.page)){
	        	// from multiple date segment page
		        saveLegacyContractRequest.setAllDateSegments(this
		                .getDateSegmentList());
	        }else{ 
	        	// from singe date segment page
	        	saveLegacyContractRequest.setAllDateSegments(this
		                .getSingleDateSegmentList());
	        }
        }
        this.page =WebConstants.EMPTY_STRING;	
        saveLegacyContractRequest.setOption(this.option);
        saveLegacyContractRequest
                .setSelectedDateSegmentForMigration(this.selectedDateSegmentFromStep2);
        saveLegacyContractRequest.setSelectedNewDate(this.newDate);
        saveLegacyContractRequest.setAfterMigrationAddNewDateSegment(
        		this.afterMigrationDateSegmentExist); 
//				&& this.userConfirmToAddNewDateSegment);
        return saveLegacyContractRequest;
    }
    
    /**
     * @return List
     */
    private List getDateSegmentList(){
    	List dateSegmentList =new ArrayList();
    	String[] array = this.selectedDS.split("~");
    	for(int i= 0; i<array.length; i++){
    		for(int j=0;j<this.cp2000ContractList.size();j++){
    			LegacyContract contract = ((LegacyContract)cp2000ContractList.get(j));
    			String startdatePage = array[i];
    			String Startdate = WPDStringUtil.getStringDate(contract.getStartDate());
    			if(startdatePage.equals(Startdate)){
    				dateSegmentList.add(contract);
    			}
    		}
    	}
    	
    	return dateSegmentList;
    }
    
    /**
     * @return List
     */
    private List getSingleDateSegmentList(){
    	List dateSegmentList =new ArrayList();
    	String[] array = this.selectedDS.split("~");
    	for(int i= 0; i<array.length; i++){
    		for(int j=0;j<this.cp2000ContractList.size();j++){
    			MigrationDateSegment contract = ((MigrationDateSegment)cp2000ContractList.get(j));
    			String startdatePage = array[i];
    			String Startdate = WPDStringUtil.getStringDate(contract.getEffectiveDate());
    			if(startdatePage.equals(Startdate)){
    				dateSegmentList.add(contract);
    			}
    		}
    	}
    	
    	return dateSegmentList;
    }


    /**
     * @return String
     */
/*    
    public String cancelMigration() {
        CancelMigrationRequest cancelRequest = (CancelMigrationRequest) this
                .getServiceRequest(ServiceManager.CANCEL_MIG_REQUEST);
        cancelRequest.setMigContractSysId(this.getMigrationContractSession()
                .getNavigationInfo().getMigContSysId());
        cancelRequest.setMigDateSegmentSysId(this.getMigrationContractSession()
                .getNavigationInfo().getDateSegmentSysId());
        cancelRequest.setMigContractId(this.contractId);
        cancelRequest.setSystem(getMigrationContractSession()
                .getMigrationContract().getSystem());
        this.executeService(cancelRequest);
        return this.goToNextPage(WebConstants.MIG_CONTRACT_STEP1);

    }
*/
   
    public String goToStep1(){
    	this.setBreadCrumbText(BREAD_CRUMB_TEXT_STEP1);
        return WebConstants.MIG_CONTRACT_STEP1;
    }
    
//	/**
//	 * Returns the disableField
//	 * @return boolean disableField.
//	 */
//	public boolean isDisableField() {
//		return disableField;
//	}
//	/**
//	 * Sets the disableField
//	 * @param disableField.
//	 */
//	public void setDisableField(boolean disableField) {
//		this.disableField = disableField;
//	}
    /**
     * @return Returns the newDate.
     */
    public String getNewDate() {
        return newDate;
    }


    /**
     * @param newDate
     *            The newDate to set.
     */
    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }


    /**
     * @param legacySystem
     *            The legacySystem to set.
     */
    public void setLegacySystem(List legacySystem) {
        this.legacySystem = legacySystem;
    }


    /**
     * @return Returns the legacySystem.
     */
    public List getLegacySystem() {
        legacySystem = new ArrayList();
        SelectItem select = new SelectItem(WebConstants.CP2000);
        legacySystem.add(select);
        return legacySystem;
    }


    /**
     * @param legacySystem
     *            The legacySystem to set.
     * 
     * public void setLegacySystem(List legacySystem) { this.legacySystem =
     * legacySystem; }
     */
    /**
     * @return Returns the contractId.
     */
    public String getContractId() {
        return contractId;
    }


    /**
     * @param contractId
     *            The contractId to set.
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }


    /**
     * @return requiredContractId
     * 
     * Returns the requiredContractId.
     */
    public boolean isRequiredContractId() {
        return requiredContractId;
    }


    /**
     * @param requiredContractId
     * 
     * Sets the requiredContractId.
     */
    public void setRequiredContractId(boolean requiredContractId) {
        this.requiredContractId = requiredContractId;
    }


    /**
     * @return cp2000ContractList
     * 
     * Returns the cp2000ContractList.
     */
    public List getCp2000ContractList() {
    	List reverseList = new ArrayList();
    
    	int count = cp2000ContractList.size();
    	for(int i=count-1 ,j=0; i>=0 ; i=i-1, j=j+1){
        		reverseList.add(j, cp2000ContractList.get(i));
    	}
            return reverseList;
    }


    /**
     * @param cp2000ContractList
     * 
     * Sets the cp2000ContractList.
     */
    public void setCp2000ContractList(List cp2000ContractList) {
 	        this.cp2000ContractList = cp2000ContractList;
    }


    /**
     * @return latestDateSegmentCount
     * 
     * Returns the latestDateSegmentCount.
     */
    public int getLatestDateSegmentCount() {
        latestDateSegmentCount++;
        return latestDateSegmentCount;
    }


    /**
     * @param latestDateSegmentCount
     * 
     * Sets the latestDateSegmentCount.
     */
    public void setLatestDateSegmentCount(int latestDateSegmentCount) {
        this.latestDateSegmentCount = latestDateSegmentCount;
    }


    /**
     * @return cp2000ContractListSize
     * 
     * Returns the cp2000ContractListSize.
     */
    public int getCp2000ContractListSize() {
        return cp2000ContractListSize;
    }


    /**
     * @param cp2000ContractListSize
     * 
     * Sets the cp2000ContractListSize.
     */
    public void setCp2000ContractListSize(int cp2000ContractListSize) {
        this.cp2000ContractListSize = cp2000ContractListSize;
    }


    /**
     * @return radioReadOnly
     * 
     * Returns the radioReadOnly.
     */
    public boolean getRadioReadOnly() {
        if (option.equals(WebConstants.MSG_WARN_ID)) {
            if (cp2000ContractListSize == latestDateSegmentCount) {
                return true;
            }
        }
        return false;
    }


    /**
     * @param radioReadOnly
     * 
     * Sets the radioReadOnly.
     */
    public void setRadioReadOnly(boolean radioReadOnly) {
        this.radioReadOnly = radioReadOnly;
    }


    /**
     * @return isMigCompleted
     * 
     * Returns the isMigCompleted.
     */
    public boolean isMigCompleted() {
        return isMigCompleted;
    }


    /**
     * @param isMigCompleted
     * 
     * Sets the isMigCompleted.
     */
    public void setMigCompleted(boolean isMigCompleted) {
        this.isMigCompleted = isMigCompleted;
    }


    /**
     * @param all_date_segment
     * 
     * Sets the aLL_DATE_SEGMENT.
     */
    public void setALL_DATE_SEGMENT(String all_date_segment) {
        ALL_DATE_SEGMENT = all_date_segment;
    }


    /**
     * @param latest_date_segment
     * 
     * Sets the lATEST_DATE_SEGMENT.
     */
    public void setLATEST_DATE_SEGMENT(String latest_date_segment) {
        LATEST_DATE_SEGMENT = latest_date_segment;
    }


    /**
     * @param renew_existing_date_segment
     * 
     * Sets the rENEW_EXISTING_DATE_SEGMENT.
     */
    public void setRENEW_EXISTING_DATE_SEGMENT(
            String renew_existing_date_segment) {
        RENEW_EXISTING_DATE_SEGMENT = renew_existing_date_segment;
    }


    /**
     * @return aLL_DATE_SEGMENT
     * 
     * Returns the aLL_DATE_SEGMENT.
     */
    public String getALL_DATE_SEGMENT() {
        return ALL_DATE_SEGMENT;
    }


    /**
     * @return lATEST_DATE_SEGMENT
     * 
     * Returns the lATEST_DATE_SEGMENT.
     */
    public String getLATEST_DATE_SEGMENT() {
        return LATEST_DATE_SEGMENT;
    }


    /**
     * @return rENEW_EXISTING_DATE_SEGMENT
     * 
     * Returns the rENEW_EXISTING_DATE_SEGMENT.
     */
    public String getRENEW_EXISTING_DATE_SEGMENT() {
        return RENEW_EXISTING_DATE_SEGMENT;
    }


    /**
     * @return Returns the lATEST_TWO_DATE_SEGMENT.
     */
    public String getLATEST_TWO_DATE_SEGMENT() {
        return LATEST_TWO_DATE_SEGMENT;
    }


    /**
     * @param latest_two_date_segment
     *            The lATEST_TWO_DATE_SEGMENT to set.
     */
    public void setLATEST_TWO_DATE_SEGMENT(String latest_two_date_segment) {
        LATEST_TWO_DATE_SEGMENT = latest_two_date_segment;
    }


    /**
     * @return isLocked
     * 
     * Returns the isLocked.
     */
    public boolean isLocked() {
        return isLocked;
    }


    /**
     * @param isLocked
     * 
     * Sets the isLocked.
     */
    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }


    /**
     * @return system
     * 
     * Returns the system.
     */
    public String getSourceSystem() {
        return sourceSystem;
    }


    /**
     * Sets the system.
     * @param sourceSystem
     * 
     */
    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }


    /**
     * @return Returns the option.
     */
    public String getOption() {
        return option;
    }


    /**
     * The option to set.
     * @param option
     */
    public void setOption(String option) {
        this.option = option;
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
     * @return legacyContract
     * 
     * Returns the legacyContract.
     */
    public LegacyContract getLegacyContract() {
        return legacyContract;
    }


    /**
     * @param legacyContract
     * 
     * Sets the legacyContract.
     */
    public void setLegacyContract(LegacyContract legacyContract) {
        this.legacyContract = legacyContract;
    }


    /**
     * @return Returns the requiredContractID.
     */
    public boolean isRequiredContractID() {
        return requiredContractID;
    }


    /**
     * @param requiredContractID
     *            The requiredContractID to set.
     */
    public void setRequiredContractID(boolean requiredContractID) {
        this.requiredContractID = requiredContractID;
    }


    /**
     * @return Returns the requiredOption.
     */
    public boolean isRequiredOption() {
        return requiredOption;
    }


    /**
     * @param requiredOption
     *            The requiredOption to set.
     */
    public void setRequiredOption(boolean requiredOption) {
        this.requiredOption = requiredOption;
    }


    /**
     * @return Returns the requiredSourceSystem.
     */
    public boolean isRequiredSourceSystem() {
        return requiredSourceSystem;
    }


    /**
     * @param requiredSourceSystem
     *            The requiredSourceSystem to set.
     */
    public void setRequiredSourceSystem(boolean requiredSourceSystem) {
        this.requiredSourceSystem = requiredSourceSystem;
    }


    /**
     * Returns the selectedDateSegmentFromStep2
     * 
     * @return String selectedDateSegmentFromStep2.
     */
    public String getSelectedDateSegmentFromStep2() {
        return selectedDateSegmentFromStep2;
    }


    /**
     * Sets the selectedDateSegmentFromStep2
     * 
     * @param selectedDateSegmentFromStep2.
     */
    public void setSelectedDateSegmentFromStep2(
            String selectedDateSegmentFromStep2) {
        this.selectedDateSegmentFromStep2 = selectedDateSegmentFromStep2;
    }


    /**
     * Returns the requiredNewDate
     * 
     * @return boolean requiredNewDate.
     */
    public boolean isRequiredNewDate() {
        return requiredNewDate;
    }


    /**
     * Sets the requiredNewDate
     * 
     * @param requiredNewDate.
     */
    public void setRequiredNewDate(boolean requiredNewDate) {
        this.requiredNewDate = requiredNewDate;
    }


    /**
     * Returns the selectedContract
     * 
     * @return String selectedContract.
     */
    public String getSelectedContract() {
        return selectedContract;
    }


    /**
     * Sets the selectedContract
     * 
     * @param selectedContract.
     */
    public void setSelectedContract(String selectedContract) {
        this.selectedContract = selectedContract;
    }


    /**
     * Returns the selectedContractKeyFromSearch
     * 
     * @return int selectedContractKeyFromSearch.
     */
    public int getSelectedContractKeyFromSearch() {
        return selectedContractKeyFromSearch;
    }


    /**
     * Sets the selectedContractKeyFromSearch
     * 
     * @param selectedContractKeyFromSearch.
     */
    public void setSelectedContractKeyFromSearch(
            int selectedContractKeyFromSearch) {
        this.selectedContractKeyFromSearch = selectedContractKeyFromSearch;
    }


    /**
     * Returns the selectedContractTypeFromSearch
     * 
     * @return String selectedContractTypeFromSearch.
     */
    public String getSelectedContractTypeFromSearch() {
        return selectedContractTypeFromSearch;
    }


    /**
     * Sets the selectedContractTypeFromSearch
     * 
     * @param selectedContractTypeFromSearch.
     */
    public void setSelectedContractTypeFromSearch(
            String selectedContractTypeFromSearch) {
        this.selectedContractTypeFromSearch = selectedContractTypeFromSearch;
    }


    /**
     * Returns the selectedDateSegKeyFromSearch
     * 
     * @return int selectedDateSegKeyFromSearch.
     */
    public int getSelectedDateSegKeyFromSearch() {
        return selectedDateSegKeyFromSearch;
    }


    /**
     * Sets the selectedDateSegKeyFromSearch
     * 
     * @param selectedDateSegKeyFromSearch.
     */
    public void setSelectedDateSegKeyFromSearch(int selectedDateSegKeyFromSearch) {
        this.selectedDateSegKeyFromSearch = selectedDateSegKeyFromSearch;
    }


    /**
     * Returns the selectedStatusFromSearch
     * 
     * @return String selectedStatusFromSearch.
     */
    public String getSelectedStatusFromSearch() {
        return selectedStatusFromSearch;
    }


    /**
     * Sets the selectedStatusFromSearch
     * 
     * @param selectedStatusFromSearch.
     */
    public void setSelectedStatusFromSearch(String selectedStatusFromSearch) {
        this.selectedStatusFromSearch = selectedStatusFromSearch;
    }


    /**
     * Returns the selectedVerionFromSearch
     * 
     * @return int selectedVerionFromSearch.
     */
    public int getSelectedVerionFromSearch() {
        return selectedVerionFromSearch;
    }


    /**
     * Sets the selectedVerionFromSearch
     * 
     * @param selectedVerionFromSearch.
     */
    public void setSelectedVerionFromSearch(int selectedVerionFromSearch) {
        this.selectedVerionFromSearch = selectedVerionFromSearch;
    }
	/**
	 * Returns the afterMigrationDateSegmentExist
	 * @return boolean afterMigrationDateSegmentExist.
	 */
	public boolean isAfterMigrationDateSegmentExist() {
		return afterMigrationDateSegmentExist;
	}
	/**
	 * Sets the afterMigrationDateSegmentExist
	 * @param afterMigrationDateSegmentExist.
	 */
	public void setAfterMigrationDateSegmentExist(
			boolean afterMigrationDateSegmentExist) {
		this.afterMigrationDateSegmentExist = afterMigrationDateSegmentExist;
	}
	/**
	 * Returns the userConfirmToAddNewDateSegment
	 * @return boolean userConfirmToAddNewDateSegment.
	 */
	public boolean isUserConfirmToAddNewDateSegment() {
		return userConfirmToAddNewDateSegment;
	}
	/**
	 * Sets the userConfirmToAddNewDateSegment
	 * @param userConfirmToAddNewDateSegment.
	 */
	public void setUserConfirmToAddNewDateSegment(
			boolean userConfirmToAddNewDateSegment) {
		this.userConfirmToAddNewDateSegment = userConfirmToAddNewDateSegment;
	}	
	
	/**
	 * @return Returns the contractNotes.
	 */
	public List getContractNotes() {
		contractNotes = retrieveContractNotesList();
		return contractNotes;
	}
	/**
	 * @param contractNotes The contractNotes to set.
	 */
	public void setContractNotes(List contractNotes) {
		this.contractNotes = contractNotes;
	}
	/**
	 * Returns the anyDSCompletedStep8
	 * @return boolean anyDSCompletedStep8.
	 */
	public boolean isAnyDSCompletedStep8() {
		if(null != this.cp2000ContractList 
				&&!this.cp2000ContractList.isEmpty()){
			if(this.cp2000ContractList.get(0) instanceof MigrationDateSegment){
				MigrationDateSegment dateSegment;
				for(java.util.Iterator cp2000ContractListItr = cp2000ContractList.iterator();
				cp2000ContractListItr.hasNext();){
					dateSegment = (MigrationDateSegment)cp2000ContractListItr.next();
					if(dateSegment.getStepCompleted().equals("7") 
						||dateSegment.getStepCompleted().equals("8")){
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * Sets the anyDSCompletedStep8
	 * @param anyDSCompletedStep8.
	 */
	public void setAnyDSCompletedStep8(boolean anyDSCompletedStep8) {
		this.anyDSCompletedStep8 = anyDSCompletedStep8;
	}
	
	public List retrieveContractNotesList(){
		
		String noteCheckBoxFlag = getRequest().getParameter(WebConstants.NOTES_SELECT_BUTTON);
		RetrieveLegacyContarctNotesRequest contractNotesRequest;
		RetrieveLegacyContractNotesResponse contractNotesResponse;
		contractNotesRequest = this.getRetrieveLegacyContractNotesRequest();
		contractNotesRequest.setAction(RetrieveLegacyContarctNotesRequest.LEGACY_RETRIEVE_CONTRACT_NOTES);
		contractNotesResponse = (RetrieveLegacyContractNotesResponse) executeService(contractNotesRequest);
		this.setCntrctNotesCheckBox(noteCheckBoxFlag);
		
		if (null != contractNotesResponse
                && contractNotesResponse.isSuccess()) {
			contractNotes = contractNotesResponse.getContractNotes();
			LegacyContractNotes legacyContarctNote =(LegacyContractNotes) contractNotes.get(0);
			if(legacyContarctNote.getNotes()==null){
				
				contractNotes = null;
				messageList = new ArrayList();
				messageList.add(new InformationalMessage(BusinessConstants.MGS_NO_NOTES_AVAILABLE));
				addAllMessagesToRequest(messageList);
			}
                 
		}
		return contractNotes;
		
	}
	
	public List retrieveMajorHeading(){
		
		RetrieveLegacyContarctNotesRequest retrieveMajorHeadingRequest;
		RetrieveLegacyContractNotesResponse retrieveMajorHeadingResponse;
		retrieveMajorHeadingRequest = this.getRetrieveLegacyContractNotesRequest();
		retrieveMajorHeadingRequest.setAction(RetrieveLegacyContarctNotesRequest.LEGACY_RETRIEVE_MAJOR_HEADING);
		retrieveMajorHeadingResponse = (RetrieveLegacyContractNotesResponse) executeService(retrieveMajorHeadingRequest);
		if (null != retrieveMajorHeadingResponse
                && retrieveMajorHeadingResponse.isSuccess()) {
			 majorHeadingList = retrieveMajorHeadingResponse.getMajorHeading();
		}
		return majorHeadingList;
	}
	
	public List retrieveMinorHeading(){
		
		RetrieveLegacyContarctNotesRequest retrieveMinorHeadingRequest;
		RetrieveLegacyContractNotesResponse retrieveMinorHeadingResponse;
		retrieveMinorHeadingRequest = this.getRetrieveLegacyContractNotesRequest();
		retrieveMinorHeadingRequest.getLegacyContractNotesVO().setMajorHeadingId(this.majorHeadingId);
		if(null!=getRequest().getParameter(WebConstants.MINOR_HEADINGID)  && getRequest().getParameter(WebConstants.MINOR_HEADINGID).matches("^[0-9a-zA-Z_]+$")){
			String minorHeadingFromScreen = getRequest().getParameter(WebConstants.MINOR_HEADINGID);
       }
		if(null != minorHeadingFromScreen && !"".equals(minorHeadingFromScreen))
			getRequest().getSession().setAttribute(WebConstants.MINOR_HEADINGID_FROM_SCREEN,minorHeadingFromScreen);
		if(!StringUtil.isEmpty(getRequest().getParameter(WebConstants.MINOR_HEADINGID))){
			if(!getRequest().getParameter(WebConstants.MINOR_HEADINGID).equalsIgnoreCase(WebConstants.NULL)){
				retrieveMinorHeadingRequest.getLegacyContractNotesVO().setMinorHeadingId(getRequest().getParameter(WebConstants.MINOR_HEADINGID));
				retrieveMinorHeadingRequest.setAction(RetrieveLegacyContarctNotesRequest.LEGACY_OLD_MINOR_HEADING);
				
			}
			else{
				retrieveMinorHeadingRequest.setAction(RetrieveLegacyContarctNotesRequest.LEGACY_RETRIEVE_MINOR_HEADING);			
			}
			
		}else{
			retrieveMinorHeadingRequest.setAction(RetrieveLegacyContarctNotesRequest.LEGACY_RETRIEVE_MINOR_HEADING);			
		}
		retrieveMinorHeadingResponse = (RetrieveLegacyContractNotesResponse) executeService(retrieveMinorHeadingRequest);
		if (null != retrieveMinorHeadingResponse
                && retrieveMinorHeadingResponse.isSuccess()) {
			if(retrieveMinorHeadingResponse.getMinorHeading()!= null 
					&& retrieveMinorHeadingResponse.getMinorHeading().size()!=0){
			 minorHeadingList = retrieveMinorHeadingResponse.getMinorHeading();
			}
			else{
				minorHeadingList = null;
			}
		}
		this.minorHeadingFromScreen = (String)getRequest().getSession().getAttribute(WebConstants.MINOR_HEADINGID_FROM_SCREEN);
//		this.minorHeadingList = minorHeadingList;
		return minorHeadingList;
	}
	
	public List retrieveMajorNotes(){
		
		String noteCheckBoxFlag = getRequest().getParameter(WebConstants.COMPONENT_NOTES);
		this.setMajorNotesFlag(noteCheckBoxFlag);
		String majorHeadingId = getRequest().getParameter(WebConstants.MAJOR_HEADINGID);
		RetrieveLegacyContarctNotesRequest retrieveMajorNotesRequest;
		RetrieveLegacyContractNotesResponse retrieveMajorNotesResponse;
		retrieveMajorNotesRequest = this.getRetrieveLegacyContractNotesRequest();
		retrieveMajorNotesRequest.getLegacyContractNotesVO().setMajorHeadingId(majorHeadingId);
		retrieveMajorNotesRequest.setAction(RetrieveLegacyContarctNotesRequest.LEGACY_RETRIEVE_MAJOR_NOTES);
		retrieveMajorNotesResponse = (RetrieveLegacyContractNotesResponse) executeService(retrieveMajorNotesRequest);
		if(null !=retrieveMajorNotesResponse
				&& retrieveMajorNotesResponse.isSuccess())	{
			
			majorNotesList = retrieveMajorNotesResponse.getMajorNotes();
			if(null==majorNotesList || majorNotesList.size()==0){
				
				majorNotesList = null;
				messageList = new ArrayList();
				messageList.add(new InformationalMessage(BusinessConstants.MGS_NO_NOTES_AVAILABLE));
				addAllMessagesToRequest(messageList);
			}
			
			
		}
		
		return majorNotesList;
	}
	
	public List retrieveMinorNotes(){
		
		String noteCheckBoxFlag = getRequest().getParameter(WebConstants.NOTE_CHECKBOX);
		String majorHeadingId = getRequest().getParameter(WebConstants.MINOR_HEADINGID);
		this.setMinorNotesFlag(noteCheckBoxFlag);
		RetrieveLegacyContarctNotesRequest retrieveMinorNotesRequest;
		RetrieveLegacyContractNotesResponse retrieveMinorNotesResponse;
		retrieveMinorNotesRequest = this.getRetrieveLegacyContractNotesRequest();
		retrieveMinorNotesRequest.getLegacyContractNotesVO().setMinorHeadingId(majorHeadingId);
		//retrieveMinorNotesRequest.getLegacyContractNotesVO().setMinorHeadingId("N0000307");
		retrieveMinorNotesRequest.setAction(RetrieveLegacyContarctNotesRequest.LEGACY_RETRIEVE_MINOR_NOTES);
		retrieveMinorNotesResponse = (RetrieveLegacyContractNotesResponse) executeService(retrieveMinorNotesRequest);
		if(null !=retrieveMinorNotesResponse
				&& retrieveMinorNotesResponse.isSuccess())	{
			minorNotesList = retrieveMinorNotesResponse.getMinorNotes();
			if(null==minorNotesList || minorNotesList.size()==0){
				
				minorNotesList = null;
				messageList = new ArrayList();
				messageList.add(new InformationalMessage(BusinessConstants.MGS_NO_NOTES_AVAILABLE));
				addAllMessagesToRequest(messageList);
			}
			
			
		}
		
		return minorNotesList;
		
		
	}
	
	private RetrieveLegacyContarctNotesRequest getRetrieveLegacyContractNotesRequest() {

	  	RetrieveLegacyContarctNotesRequest retrieveLegacyContarctNotesRequest = (RetrieveLegacyContarctNotesRequest) this
                .getServiceRequest(ServiceManager.RETRIEVE_LEGACY_CONTRACT_NOTES_REQUEST);
	  	LegacyContractNotesVO legacyContractNotesVO = new LegacyContractNotesVO();
//		legacyContractNotesVO.setContractId("2658");
//		legacyContractNotesVO.setStartDate("01/01/1900");
	 	legacyContractNotesVO.setContractId(this.getMigrationContractSession().getMigrationContract().getContractId());
	  	String startDate = WPDStringUtil.convertDateToString(this.getMigrationContractSession().getStartDateLegacy());
	  	legacyContractNotesVO.setStartDate(startDate);
	  	retrieveLegacyContarctNotesRequest.setLegacyContractNotesVO(legacyContractNotesVO);
        return retrieveLegacyContarctNotesRequest;
    }
	  
	/**
	 * @return Returns the majorHeadingList.
	 */
	public List getMajorHeadingList() {
		majorHeadingList = retrieveMajorHeading();
		return majorHeadingList;
	}
	/**
	 * @param majorHeadingList The majorHeadingList to set.
	 */
	public void setMajorHeadingList(List majorHeadingList) {
		this.majorHeadingList = majorHeadingList;
	}
	/**
	 * @return Returns the minorHeadingList.
	 */
	public List getMinorHeadingList() {
		minorHeadingList = retrieveMinorHeading();
		return minorHeadingList;
	}
	/**
	 * @param minorHeadingList The minorHeadingList to set.
	 */
	public void setMinorHeadingList(List minorHeadingList) {
		this.minorHeadingList = minorHeadingList;
	}
	/**
	 * @return Returns the majorNotesList.
	 */
	public List getMajorNotesList() {
		majorNotesList = retrieveMajorNotes();
		return majorNotesList;
	}
	/**
	 * @param majorNotesList The majorNotesList to set.
	 */
	public void setMajorNotesList(List majorNotesList) {
		this.majorNotesList = majorNotesList;
	}
	/**
	 * @return Returns the minorNotesList.
	 */
	public List getMinorNotesList() {
		minorNotesList = retrieveMinorNotes();
		return minorNotesList;
	}
	/**
	 * @param minorNotesList The minorNotesList to set.
	 */
	public void setMinorNotesList(List minorNotesList) {
		this.minorNotesList = minorNotesList;
	}
	
	/**
	 * @return Returns the majorHeadingDescList.
	 */
	public List getMajorHeadingDescList() {
		
		RetrieveLegacyContarctNotesRequest retrieveMajorHeadingRequest;
		RetrieveLegacyContractNotesResponse retrieveMajorHeadingResponse;
		retrieveMajorHeadingRequest = this.getRetrieveLegacyContractNotesRequest();
		retrieveMajorHeadingRequest.setAction(RetrieveLegacyContarctNotesRequest.LEGACY_RETRIEVE_ALL_MAJOR_HEADING);
		retrieveMajorHeadingResponse = (RetrieveLegacyContractNotesResponse) executeService(retrieveMajorHeadingRequest);
		majorHeadingDescList = new ArrayList();
		if (null != retrieveMajorHeadingResponse
                && retrieveMajorHeadingResponse.isSuccess()) {
			 majorHeadingList = retrieveMajorHeadingResponse.getMajorHeading();
		}
	   Iterator itr = majorHeadingList.iterator();
	   majorHeadingDescList.add(new SelectItem("",""));
       while (itr.hasNext()) {
       	LegacyContractMajorHeading legacyMajorHeading = (LegacyContractMajorHeading) itr
                   .next();
       	majorHeadingDescList.add(new SelectItem(legacyMajorHeading.getMajorHeadingId().trim(),legacyMajorHeading.getMajorHeadingDesc().trim()));
       }
		return majorHeadingDescList;
	}
	/**
	 * @param majorHeadingDescList The majorHeadingDescList to set.
	 */
	public void setMajorHeadingDescList(List majorHeadingDescList) {
		this.majorHeadingDescList = majorHeadingDescList;
	}
		
	/**
	 * @return Returns the majorHeadingId.
	 */
	public String getMajorHeadingId() {
		return majorHeadingId;
	}
	/**
	 * @param majorHeadingId The majorHeadingId to set.
	 */
	public void setMajorHeadingId(String majorHeadingId) {
		this.majorHeadingId = majorHeadingId;
	}
	
	 public String majorHeadingSelectAction() {
	 	this.setMinorHeadingFromScreen(this.minorHeadingFromScreen);
	    return "";
    }
	 
	
	/**
	 * @return Returns the cntrctNotesCheckBox.
	 */
	public String getCntrctNotesCheckBox() {
		return cntrctNotesCheckBox;
	}
	/**
	 * @param cntrctNotesCheckBox The cntrctNotesCheckBox to set.
	 */
	public void setCntrctNotesCheckBox(String cntrctNotesCheckBox) {
		this.cntrctNotesCheckBox = cntrctNotesCheckBox;
	}
	
	
	/**
	 * @return Returns the majorHeadingHidden.
	 */
	public String getMajorHeadingHidden() {
		return majorHeadingHidden;
	}
	/**
	 * @param majorHeadingHidden The majorHeadingHidden to set.
	 */
	public void setMajorHeadingHidden(String majorHeadingHidden) {
		this.majorHeadingHidden = majorHeadingHidden;
	}
	
	/**
	 * @return Returns the minorHeadingHidden.
	 */
	public String getMinorHeadingHidden() {
		return minorHeadingHidden;
	}
	/**
	 * @param minorHeadingHidden The minorHeadingHidden to set.
	 */
	public void setMinorHeadingHidden(String minorHeadingHidden) {
		this.minorHeadingHidden = minorHeadingHidden;
	}

	/**
	 * @return Returns the majorNotesFlag.
	 */
	public String getMajorNotesFlag() {
		return majorNotesFlag;
	}
	/**
	 * @param majorNotesFlag The majorNotesFlag to set.
	 */
	public void setMajorNotesFlag(String majorNotesFlag) {
		this.majorNotesFlag = majorNotesFlag;
	}
	
	/**
	 * @return Returns the minorNotesFlag.
	 */
	public String getMinorNotesFlag() {
		return minorNotesFlag;
	}
	/**
	 * @param minorNotesFlag The minorNotesFlag to set.
	 */
	public void setMinorNotesFlag(String minorNotesFlag) {
		this.minorNotesFlag = minorNotesFlag;
	}
	/**
	 * @return Returns the minorHeadingFromScreen.
	 */
	public String getMinorHeadingFromScreen() {
		return minorHeadingFromScreen;
	}
	/**
	 * @param minorHeadingFromScreen The minorHeadingFromScreen to set.
	 */
	public void setMinorHeadingFromScreen(String minorHeadingFromScreen) {
		this.minorHeadingFromScreen = minorHeadingFromScreen;
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
	/**
	 * @return Returns the mIGRATION.
	 */
	public String getMIGRATION() {
		return MIGRATION;
	}
	/**
	 * @param migration The mIGRATION to set.
	 */
	public void setMIGRATION(String migration) {
		MIGRATION = migration;
	}
	/**
	 * @return Returns the rENEW.
	 */
	public String getRENEW() {
		return RENEW;
	}
	/**
	 * @param renew The rENEW to set.
	 */
	public void setRENEW(String renew) {
		RENEW = renew;
	}
	
	/**
	 * @return Returns the fROM_DATE.
	 */
	public String getFROM_DATE() {
		return FROM_DATE;
	}
	/**
	 * @param from_date The fROM_DATE to set.
	 */
	public void setFROM_DATE(String from_date) {
		FROM_DATE = from_date;
	}
	
	/**
	 * @return Returns the fromDate.
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate The fromDate to set.
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return Returns the numberOfYears.
	 */
	public String getNumberOfYears() {
		return numberOfYears;
	}
	/**
	 * @param numberOfYears The numberOfYears to set.
	 */
	public void setNumberOfYears(String numberOfYears) {
		this.numberOfYears = numberOfYears;
	}

	/**
	 * @return Returns the selectedDS.
	 */
	public String getSelectedDS() {
		return selectedDS;
	}
	/**
	 * @param selectedDS The selectedDS to set.
	 */
	public void setSelectedDS(String selectedDS) {
		this.selectedDS = selectedDS;
	}
	/**
	 * @return Returns the page.
	 */
	public String getPage() {
		return page;
	}
	/**
	 * @param page The page to set.
	 */
	public void setPage(String page) {
		this.page = page;
	}
	/**
	 * @return Returns the pageBreadcrumb.
	 */
	public String getPageBreadcrumb() {
		return pageBreadcrumb;
	}
	/**
	 * @param pageBreadcrumb The pageBreadcrumb to set.
	 */
	public void setPageBreadcrumb(String pageBreadcrumb) {
		this.setBreadCrumbText(WebConstants.MIGRATION_BREAD_CRUMB_STEP2);
	}

	/**
	 * @return Returns the effectiveDateInvalid.
	 */
	public boolean isEffectiveDateInvalid() {
		return effectiveDateInvalid;
	}
	/**
	 * @param effectiveDateInvalid The effectiveDateInvalid to set.
	 */
	public void setEffectiveDateInvalid(boolean effectiveDateInvalid) {
		this.effectiveDateInvalid = effectiveDateInvalid;
	}
}