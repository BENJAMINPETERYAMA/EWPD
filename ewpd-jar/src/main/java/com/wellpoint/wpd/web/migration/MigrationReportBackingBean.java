/*
 * MigrationReportBackingBean.java
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
import java.util.List;

import com.wellpoint.wpd.business.migration.util.MigrationContractUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.migration.bo.BenefitComponentBenefit;
import com.wellpoint.wpd.common.migration.bo.MigrationContract;
import com.wellpoint.wpd.common.migration.request.MigrationGenerateReportRequest;
import com.wellpoint.wpd.common.migration.response.MigrationGenerateReportResponse;
import com.wellpoint.wpd.common.migration.vo.MigrationContractSession;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationReportBackingBean extends MigrationBaseBackingBean{

	List unmappedVariableList; 
	boolean renderFlag = false;
	private String legacyContractId;
	private String majorHeading;
	private String minorHeading;
	private String contractVariable;
	private String contractVariableText;
	private String providerArrangement;
	private String contractVariableFormat;
	private String contractVariableValue;
	private Date startDate;
	private boolean reportRetrieved = false;
	private String printBreadCrumbText = BREAD_CRUMB_TEXT_STEP8+ " >> Print";
	private List conflictingLines;

	
	
	private static String BREAD_CRUMB_TEXT_STEP8 = "Administration >> Contract Migration Wizard >> Report Generation (Step 9)";
	
	public MigrationReportBackingBean(){
	    super();
	    this.setBreadCrumbTextLeft(BREAD_CRUMB_TEXT_STEP8);
	    String contractId = this.getMigrationContractSession().getMigrationContract().getContractId();
        String startDate = WPDStringUtil.convertDateToString(this.getMigrationContractSession().getStartDateEwpd());
        String endDate = WPDStringUtil.convertDateToString(this.getMigrationContractSession().getEndDate());
        this.setBreadCrumbTextRight(WebConstants.BREAD_CRUMB_CONTRACT+contractId+" ("+startDate+" - "+endDate+")");
	}
	
	/**
	 * Function Load Unmapped variables from the repository
	 * and display in the screen. 
	 * @param 
	 * @param 
	 * @param 
	 * @return String 
	 * @throws 
	 */
	public String showReportGenerationPage(){
		
		Logger.logInfo("Entering the method for displaying Unmapped Variable report Tab");
		if(!reportRetrieved) {
			this.unmappedVariableList = this.retrieveUnmappedVariableList();
		}
        
		return WebConstants.MIG_CONTRACT_STEP9;
	}
	
	/**
	 * Function to retrieve Unmapped variables from the repository.. 
	 * @param 
	 * @param 
	 * @param 
	 * @return a List of Unmapped variables
	 * @throws 
	 */
	public List retrieveUnmappedVariableList() {
		if(!reportRetrieved) {
			//obtain request
			MigrationGenerateReportRequest migrationGenerateReportRequest = (MigrationGenerateReportRequest)
			this.getServiceRequest(ServiceManager.MIGRATION_GENERATE_REPORT_REQUEST);
			//FIXME can use the contract session from request to get these ids
			this.setToRequest(migrationGenerateReportRequest);
			MigrationContract migrationContract = getMigrationContractSession().getMigrationContract();
			migrationGenerateReportRequest.setContractId(migrationContract.getContractId());
			migrationGenerateReportRequest.setDateSegmentId(this.getMigrationContractSession().getDateSegmentId());			
			migrationGenerateReportRequest.setAction(BusinessConstants.MIGRATION_RETRIEVE_UNMAPPED_VARIABLES);
			this.getMigrationContractSession().getNavigationInfo().setUpdateLastAccessedPageOnly(true);
	   	   	// obtain the response
			MigrationGenerateReportResponse migrationGenerateReportResponse = 
			(MigrationGenerateReportResponse)executeService(migrationGenerateReportRequest);
			
	       	if(null != migrationGenerateReportResponse && migrationGenerateReportResponse.isSuccess())
	       	{
	       		unmappedVariableList = migrationGenerateReportResponse.getUnmappedVariableList();		
				if(unmappedVariableList != null && unmappedVariableList.size()>0)
				{
					this.renderFlag = true;  
				} 
				if(migrationGenerateReportResponse.getConflictingLines().size() > 0)
					this.conflictingLines = migrationGenerateReportResponse.getConflictingLines();
	       	}       
	       	this.reportRetrieved = true;
		}
		return unmappedVariableList;
	}
	
	/**
	 * @return String
	 */
	public String back(){
		updateNavigationInfo(BusinessConstants.MIGRATION_NAVIGATION_FLAG_TRUE, true)	;
		
		MigrationContractUtil util = new MigrationContractUtil();
		MigrationContractSession  migrationContractSession =getMigrationContractSession();
		
		MigrationContract migrationContract = migrationContractSession.getMigrationContract();
		int compId =  0;
		int stdId = 0;
		
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
		int i =3;
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
			//may be modified to the tree of step8
			//replace the following code with loading step 8 page.
		    getSession().setAttribute("MIGRATION_STEP8_BACK",benefitComponentBenefit);
		    getSession().setAttribute("MIGRATION_TREE_BACK",null);
		    //settin next comp and ben ids
		    migrationContract.setBenefitComponentId(benefitComponentBenefit.getBenefitComponentId());
		    migrationContract.setStdBenefitId(benefitComponentBenefit.getBenefitId());
		    migrationContract.setBenefitCompName(benefitComponentBenefit.getBenefitComponentDesc());
		    migrationContract.setStdBenefitName(benefitComponentBenefit.getBenefitDesc());
		    
		    this.getMigrationContractSession().setMigrationContract(migrationContract);
		    return goToNextPage(WebConstants.MIG_CONTRACT_STEP8);
		}
		else{
			//only part with step6.
		    
		    getSession().setAttribute("MIGRATION_TREE_BACK",null);
		    getSession().setAttribute("MIGRATION_STEP8_BACK",null);
		    return goToNextPage(WebConstants.MIG_CONTRACT_STEP6);
		}
		
	}
	
	/**
	 * @return String
	 */
	public String next(){
		updateNavigationInfo(BusinessConstants.MIGRATION_NAVIGATION_FLAG_FALSE, false)	;
		return goToNextPage(WebConstants.MIG_CONTRACT_STEP10);
	}	
	/**
	 * @param navigationFlag
	 */
	public void updateNavigationInfo(boolean navigationFlag, boolean updateStepFlag){
		MigrationGenerateReportRequest migrationGenerateReportRequest = (MigrationGenerateReportRequest)
		this.getServiceRequest(ServiceManager.MIGRATION_GENERATE_REPORT_REQUEST);
		
		if(null!=this.getMigrationContractSession().getNavigationInfo()){
		    this.getMigrationContractSession().getNavigationInfo().setNavigationFlag(navigationFlag);
		}
		
		if (updateStepFlag)
            this.getMigrationContractSession().getNavigationInfo()
                    .setUpdateLastAccessedPageOnly(true);
		
		this.setToRequest(migrationGenerateReportRequest);
		migrationGenerateReportRequest.setAction(BusinessConstants.REPORT_UPDATE_STEP_COMPLETED);
		
		MigrationGenerateReportResponse migrationGenerateReportResponse = 
			(MigrationGenerateReportResponse)executeService(migrationGenerateReportRequest);
		 
		if(null != migrationGenerateReportResponse && migrationGenerateReportResponse.isSuccess()){				
				this.setToSession(migrationGenerateReportResponse);
		} 		
	}
	/**
	 * Returns the contractVariable
	 * @return String contractVariable.
	 */
	public String getContractVariable() {
		return contractVariable;
	}
	/**
	 * Sets the contractVariable
	 * @param contractVariable.
	 */
	public void setContractVariable(String contractVariable) {
		this.contractVariable = contractVariable;
	}
	/**
	 * Returns the contractVariableFormat
	 * @return String contractVariableFormat.
	 */
	public String getContractVariableFormat() {
		return contractVariableFormat;
	}
	/**
	 * Sets the contractVariableFormat
	 * @param contractVariableFormat.
	 */
	public void setContractVariableFormat(String contractVariableFormat) {
		this.contractVariableFormat = contractVariableFormat;
	}
	/**
	 * Returns the contractVariableText
	 * @return String contractVariableText.
	 */
	public String getContractVariableText() {
		return contractVariableText;
	}
	/**
	 * Sets the contractVariableText
	 * @param contractVariableText.
	 */
	public void setContractVariableText(String contractVariableText) {
		this.contractVariableText = contractVariableText;
	}
	/**
	 * Returns the contractVariableValue
	 * @return String contractVariableValue.
	 */
	public String getContractVariableValue() {
		return contractVariableValue;
	}
	/**
	 * Sets the contractVariableValue
	 * @param contractVariableValue.
	 */
	public void setContractVariableValue(String contractVariableValue) {
		this.contractVariableValue = contractVariableValue;
	}
	/**
	 * Returns the legacyContractId
	 * @return String legacyContractId.
	 */
	public String getLegacyContractId() {
		return legacyContractId;
	}
	/**
	 * Sets the legacyContractId
	 * @param legacyContractId.
	 */
	public void setLegacyContractId(String legacyContractId) {
		this.legacyContractId = legacyContractId;
	}
	/**
	 * Returns the majorHeading
	 * @return String majorHeading.
	 */
	public String getMajorHeading() {
		return majorHeading;
	}
	/**
	 * Sets the majorHeading
	 * @param majorHeading.
	 */
	public void setMajorHeading(String majorHeading) {
		this.majorHeading = majorHeading;
	}
	/**
	 * Returns the minorHeading
	 * @return String minorHeading.
	 */
	public String getMinorHeading() {
		return minorHeading;
	}
	/**
	 * Sets the minorHeading
	 * @param minorHeading.
	 */
	public void setMinorHeading(String minorHeading) {
		this.minorHeading = minorHeading;
	}
	/**
	 * Returns the providerArrangement
	 * @return String providerArrangement.
	 */
	public String getProviderArrangement() {
		return providerArrangement;
	}
	/**
	 * Sets the providerArrangement
	 * @param providerArrangement.
	 */
	public void setProviderArrangement(String providerArrangement) {
		this.providerArrangement = providerArrangement;
	}
	/**
	 * Returns the renderFlag
	 * @return boolean renderFlag.
	 */
	public boolean isRenderFlag() {
		return renderFlag;
	}
	/**
	 * Sets the renderFlag
	 * @param renderFlag.
	 */
	public void setRenderFlag(boolean renderFlag) {
		this.renderFlag = renderFlag;
	}
	/**
	 * Returns the startDate
	 * @return Date startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * Sets the startDate
	 * @param startDate.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * Returns the unmappedVariableList
	 * @return List unmappedVariableList.
	 */
	public List getUnmappedVariableList() {
		showReportGenerationPage();
	    unmappedVariableList = this.retrieveUnmappedVariableList();
	    List list =(List) getSession().getAttribute(WebConstants.MESSAGE_LIST_STEP3);
        if(null != list && list.size()>0){
            addAllMessagesToRequest(list);
        }
        if (null != getSession().getAttribute(WebConstants.MESSAGE_LIST_STEP3))
            getSession().removeAttribute(WebConstants.MESSAGE_LIST_STEP3);
		return unmappedVariableList;
	}
	/**
	 * Sets the unmappedVariableList
	 * @param unmappedVariableList.
	 */
	public void setUnmappedVariableList(List unmappedVariableList) {
		this.unmappedVariableList = unmappedVariableList;
	}
	/**
	 * Returns the conflictingLines.
	 * @return List conflictingLines.
	 */
	public List getConflictingLines() {
		return conflictingLines;
	}
	/**
	 * Sets the conflictingLines.
	 * @param conflictingLines.
	 */

	public void setConflictingLines(List conflictingLines) {
		this.conflictingLines = conflictingLines;
	}
	/**
	 * @return Returns the printBreadCrumbText.
	 */
	public String getPrintBreadCrumbText() {
		return printBreadCrumbText;
	}
	/**
	 * @param printBreadCrumbText The printBreadCrumbText to set.
	 */
	public void setPrintBreadCrumbText(String printBreadCrumbText) {
		this.printBreadCrumbText = printBreadCrumbText;
	}
}
