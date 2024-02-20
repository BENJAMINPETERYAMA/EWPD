/*
 * ContrMigratProductMappingBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.migration;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.business.contract.locateresult.ContractLocateResult;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.contract.request.ContractSearchRequest;
import com.wellpoint.wpd.common.contract.response.ContractSearchResponse;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.migration.request.MigrationContractRequest;
import com.wellpoint.wpd.common.migration.response.MigrationContractResponse;
import com.wellpoint.wpd.common.migration.vo.MigrationContractSession;
import com.wellpoint.wpd.common.product.request.SearchProductRequest;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public abstract class MigrationBaseBackingBean extends WPDBackingBean {

    /**
     * @param migrationContractResponse
     */
    protected void setToSession(
            MigrationContractResponse migrationContractResponse) {
        HttpSession session = getSession();
        if (migrationContractResponse != null) {
            if (migrationContractResponse.getMigrationContractSession() != null)
                session
                        .setAttribute(
                                BusinessConstants.SESSION_MIGRATION_CONTRACT_SESSION_OBJECT,
                                migrationContractResponse
                                        .getMigrationContractSession());
        }
    }


    /**
     * @return
     */
    protected MigrationContractSession getMigrationContractSession() {
        HttpSession session = getSession();
        return (MigrationContractSession) session
                .getAttribute(BusinessConstants.SESSION_MIGRATION_CONTRACT_SESSION_OBJECT);

    }


    /**
     * @param migrationContractRequest
     */
    protected void setToRequest(
            MigrationContractRequest migrationContractRequest) {
        HttpSession session = getSession();
        migrationContractRequest
                .setMigrationContractSession((MigrationContractSession) session
                        .getAttribute(BusinessConstants.SESSION_MIGRATION_CONTRACT_SESSION_OBJECT));
    }


    /**
     * @param saveLegacyContractResponse
     * @return
     */
    protected String getLastAccessedPage(
            MigrationContractResponse saveLegacyContractResponse) {
        setToSession(saveLegacyContractResponse);
        
        String lastAccessedPage = WebConstants.EMPTY_STRING;
        	
        if(null!=saveLegacyContractResponse
                .getMigrationContractSession().getNavigationInfo())	{
        	lastAccessedPage =	saveLegacyContractResponse
            	.getMigrationContractSession().getNavigationInfo()
						.getLastAccessedPage();
        }
        	
        return goToNextPage(lastAccessedPage);
    }


    /**
     * @param nextPage
     * @return
     */
    protected String goToNextPage(String nextPage) {
        if (nextPage.equals(WebConstants.MIG_CONTRACT_STEP1)) {
            String contractID = this.getMigrationContractSession()
                    .getMigrationContract().getContractId();
            this.getSession().removeAttribute("legacyContractBackingBean");
            LegacyContractBackingBean legacyContractBackingBean = (LegacyContractBackingBean) FacesContext
                    .getCurrentInstance().getApplication().createValueBinding(
                            "#{legacyContractBackingBean}").getValue(
                            FacesContext.getCurrentInstance());

            if (null != super.messages) {
                if (!super.messages.isEmpty()) {
                    Object obj = super.messages.get(0);
                    if (obj instanceof InformationalMessage) {
                        InformationalMessage informationalMessage = (InformationalMessage) obj;
                        if (informationalMessage.getId().equals(
                                BusinessConstants.MIGRATION_ENDED_FOR_CONTRACT) || informationalMessage.getId().equals("migration.ended.BYCYConflict.contract")) {
                            List messagesOld = new ArrayList(super.messages);
                            ContractLocateResult contractLocateResult = new ContractLocateResult();
                            
					    	ContractSearchRequest contractSearchRequest = (ContractSearchRequest) this
					    														.getServiceRequest(ServiceManager.SEARCH_CONTRACT_REQUEST);
					    	contractSearchRequest.setContractId(contractID);
					        contractSearchRequest.setAction(SearchProductRequest.SEARCH_ALL_VERSION);					  
					        ContractSearchResponse contractSearchResponse = (ContractSearchResponse) executeService(contractSearchRequest);
					        java.util.List versionList;
					        if (null != contractSearchResponse) {
					            versionList = contractSearchResponse
					                    .getSearchResultList();
					        	if(null != versionList && !versionList.isEmpty()){
					        		  contractLocateResult = (ContractLocateResult) versionList.get(0);
			                            legacyContractBackingBean.setMigCompleted(true);
			                            legacyContractBackingBean
			                                    .setSelectedContract(contractLocateResult
			                                            .getContractId());
			                            legacyContractBackingBean
			                                    .setSelectedContractKeyFromSearch(contractLocateResult
			                                            .getContractKey());
			                            legacyContractBackingBean
			                                    .setSelectedContractTypeFromSearch(contractLocateResult
			                                            .getContractType());
			                            legacyContractBackingBean
			                                    .setSelectedDateSegKeyFromSearch(contractLocateResult
			                                            .getDateSegmentId());
			                            legacyContractBackingBean
			                                    .setSelectedStatusFromSearch(contractLocateResult
			                                            .getStatus());
			                            legacyContractBackingBean
			                                    .setSelectedVerionFromSearch(contractLocateResult
			                                            .getVersion());
			                            super.addAllMessagesToRequest(messagesOld);
					        	}//End if: versionList null check
					        }//End if: contractSearchResponse null check
                        }// end if: end of migration process
                    }//end if: messages type check
                }
            }
            return WebConstants.MIG_CONTRACT_STEP1;
        } else if (nextPage.equals(WebConstants.MIG_CONTRACT_STEP2)
                || nextPage.equals(WebConstants.MIG_CONTRACT_STEP3)) {
            getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
            getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
            MigrationGeneralInfoBackingBean migrationGeneralInfoBackingBean = (MigrationGeneralInfoBackingBean) FacesContext
                    .getCurrentInstance().getApplication().createValueBinding(
                            "#{migrationGeneralInfoBackingBean}").getValue(
                            FacesContext.getCurrentInstance());
            return migrationGeneralInfoBackingBean.retrieveMigContractDetails();
        } else if (nextPage.equals(WebConstants.MIG_CONTRACT_STEP_ALL_DS)) {
            getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
            getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
            return WebConstants.MIG_CONTRACT_STEP_ALL_DS;
        } else if (nextPage.equals(WebConstants.MIG_CONTRACT_STEP4)) {
            getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
            getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
            MigrationPricingInfoBackingBean migrationPricingInfoBackingBean = (MigrationPricingInfoBackingBean) FacesContext
                    .getCurrentInstance().getApplication().createValueBinding(
                            "#{migrationPricingInfoBackingBean}").getValue(
                            FacesContext.getCurrentInstance());
            return migrationPricingInfoBackingBean.showPricingInfoPage();

        }
 
        else if (nextPage.equals(WebConstants.MIG_CONTRACT_STEP5)) {
            getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
            getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
            MigrationProductInfoBackingBean migrationProductInfoBackingBean = (MigrationProductInfoBackingBean) FacesContext
                    .getCurrentInstance().getApplication().createValueBinding(
                            "#{migrationProductInfoBackingBean}").getValue(
                            FacesContext.getCurrentInstance());
            return migrationProductInfoBackingBean.showProductInfo();
        } else if (nextPage.equals(WebConstants.MIG_CONTRACT_STEP6)) {
            getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
            getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
            MigrationContractRulesBackingBean migrationContractRulesBackingBean = (MigrationContractRulesBackingBean) FacesContext
                    .getCurrentInstance().getApplication().createValueBinding(
                            "#{migrationContractRulesBackingBean}").getValue(
                            FacesContext.getCurrentInstance());
            return migrationContractRulesBackingBean
                    .displayMigrationContractRules();
        }
        else if (nextPage.equals(WebConstants.MIG_CONTRACT_STEP7)) {

            if(null == getSession().getAttribute("MIGRATION_TREE_NEXT") &&
                    null == getSession().getAttribute("MIGRATION_TREE_BACK")){
                getSession().setAttribute(WebConstants.TREE_EXP_FLAG,"1");
            }
            ContrMigratProductMappingBackingBean  contrMigratProductMappingBackingBean = (ContrMigratProductMappingBackingBean) FacesContext
            .getCurrentInstance().getApplication().createValueBinding(
                    "#{ContrMigratProductMappingBackingBean}").getValue(
                    FacesContext.getCurrentInstance());
            return contrMigratProductMappingBackingBean.getStandardBenefitMapping();
            
            
        } else if (nextPage.equals(WebConstants.MIG_CONTRACT_STEP8)) {        
            if(null == getSession().getAttribute("MIGRATION_STEP8_NEXT") &&
                    null == getSession().getAttribute("MIGRATION_STEP8_BACK")){
            	getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG,"1");
            }
        	MigrateNotesBackingBean migrateNotesBackingBean = (MigrateNotesBackingBean) FacesContext
			.getCurrentInstance().getApplication().createValueBinding(
					"#{migrateNotesBackingBean}").getValue(FacesContext.getCurrentInstance());
        	return migrateNotesBackingBean.loadMigrationNotesPage();
        }
/******************************************************/
        /**********CHANGE THE BELOW*******************/
        else if (nextPage.equals(WebConstants.MIG_CONTRACT_STEP9)) {
        	getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
        	 getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
		    getSession().setAttribute("MIGRATION_TREE_NEXT",null);
		    getSession().setAttribute("MIGRATION_STEP8_NEXT",null);
		    getSession().setAttribute("MIGRATION_TREE_BACK",null);
		    getSession().setAttribute("MIGRATION_STEP8_BACK",null);
            MigrationReportBackingBean migrationReportBackingBean = (MigrationReportBackingBean) FacesContext
                    .getCurrentInstance().getApplication().createValueBinding(
                            "#{migrationReportBackingBean}").getValue(
                            FacesContext.getCurrentInstance());

            return migrationReportBackingBean.showReportGenerationPage();
        }
        else if (nextPage.equals(WebConstants.MIG_CONTRACT_STEP10)) {
            getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
            getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
		    getSession().setAttribute("MIGRATION_TREE_NEXT",null);
		    getSession().setAttribute("MIGRATION_STEP8_NEXT",null);
		    getSession().setAttribute("MIGRATION_TREE_BACK",null);
		    getSession().setAttribute("MIGRATION_STEP8_BACK",null);
            return WebConstants.MIG_CONTRACT_STEP10;
        }
        return null;
    }


    /**
     * @param breadCrumbTextLeft
     */
    public void setBreadCrumbTextLeft(String breadCrumbTextLeft) {
        getRequest().setAttribute("breadCrumbTextLeft", breadCrumbTextLeft);
    }


    /**
     * @param breadCrumbTextRight
     */
    public void setBreadCrumbTextRight(String breadCrumbTextRight) {
        getRequest().setAttribute("breadCrumbTextRight", breadCrumbTextRight);
    }



    /**
     * @return List
     */

	public List validateStepNumber(){
    	
    	List validationMessages = new ArrayList();
    	if(null!= getMigrationContractSession().getNavigationInfo()){
    		if(getMigrationContractSession().getNavigationInfo().getStepCompleted()<3){
    			validationMessages.add(new InformationalMessage(
                        WebConstants.STEP_THREE_SHOULD_PROCESS));			
        	}else if(getMigrationContractSession().getNavigationInfo().getStepCompleted()<4){
    			validationMessages.add(new InformationalMessage(
                        WebConstants.STEP_FOUR_SHOULD_PROCESS));			
        	}else if(getMigrationContractSession().getNavigationInfo().getStepCompleted()<5){
    			validationMessages.add(new InformationalMessage(
                        WebConstants.STEP_FIVE_SHOULD_PROCESS));			
        	}else if(getMigrationContractSession().getNavigationInfo().getStepCompleted()<6){
    			validationMessages.add(new InformationalMessage(
                        WebConstants.STEP_SIX_SHOULD_PROCESS));			
        	}else if(getMigrationContractSession().getNavigationInfo().getStepCompleted()<7){
    			validationMessages.add(new InformationalMessage(
                        WebConstants.STEP_SEVEN_SHOULD_PROCESS));			
        	}else if(getMigrationContractSession().getNavigationInfo().getStepCompleted()<8){
    			validationMessages.add(new InformationalMessage(
                        WebConstants.STEP_EIGHT_SHOULD_PROCESS));			
        	}    	
    	}
    	
    return validationMessages;

    }
}