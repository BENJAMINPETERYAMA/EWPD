/*
 * PricingInfoBackingBean.java
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

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.ErrorMessage;
import com.wellpoint.wpd.common.migration.request.MigrationPricingInfoRequest;
import com.wellpoint.wpd.common.migration.response.MigrationPricingInfoResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationPricingInfoBackingBean extends MigrationBaseBackingBean {

    private String coverage;

    private String pricing;

    private String network;

    private boolean coverageInvalid = false;

    private boolean pricingInvalid = false;

    private boolean networkInvalid = false;

    private String selectedMigratedDSSysID;

    private String selectedCoverageID;

    private String selectedPricingID;

    private String selectedNetworkID;

    private List validationMessages;

    private List pricingInformationList = null;

    private boolean renderFlag = false;

    //For populating referencelookuplist
    private String migrationSysId;

    private static String BREAD_CRUMB_TEXT_STEP4 = "Administration >> Contract Migration Wizard >> Pricing Information (Step 4)";


    /**
     * Default Constructor.
     *  
     */
    public MigrationPricingInfoBackingBean() {
        super();
        this.setBreadCrumbTextLeft(BREAD_CRUMB_TEXT_STEP4);
        String contractId = getMigrationContractSession()
                .getMigrationContract().getContractId();
        String startDate = WPDStringUtil.convertDateToString(this
                .getMigrationContractSession().getStartDateEwpd());
        String endDate = WPDStringUtil.convertDateToString(this
                .getMigrationContractSession().getEndDate());
        //      For reference lookuplist
        this.setMigrationSysId(this.getMigrationContractSession()
                .getMigrationContract().getMigrationSystemId());
        this.setBreadCrumbTextRight(WebConstants.BREAD_CRUMB_CONTRACT
                + contractId + " (" + startDate + " - " + endDate + ")");
    }


    /**
     * Function Load Pricing Information from the repository and display in the
     * screen.
     * 
     * @param
     * @param
     * @param
     * @return String
     * @throws
     */
    public String showPricingInfoPage() {

        Logger
                .logInfo("Entering the method for displaying Contract Pricing Information Tab");

        this.pricingInformationList = this.retrivePricingInformationList();
        List list = (List) getSession().getAttribute(
                WebConstants.MESSAGE_LIST_STEP3);

        if (null != list && list.size() > 0) {
            addAllMessagesToRequest(list);
        }
        if (null != getSession().getAttribute(WebConstants.MESSAGE_LIST_STEP3))
            getSession().removeAttribute(WebConstants.MESSAGE_LIST_STEP3);

        return WebConstants.MIG_CONTRACT_STEP4;
    }


    /**
     * Function to retrieve Pricing information from the repository..
     * 
     * @param
     * @param
     * @param
     * @return a List of Pricing Information
     * @throws SevereException,
     *             AdapterException
     */
    public List retrivePricingInformationList() {
        //obtain request
        MigrationPricingInfoRequest migrationPricingInfoRequest = (MigrationPricingInfoRequest) this
                .getServiceRequest(ServiceManager.MIGRATION_PRICING_INFO_REQUEST);
        // Retrieve if action = 2
        migrationPricingInfoRequest
                .setAction(BusinessConstants.MIGRATION_RETRIEVE_PRICING_INFO);
        this.getMigrationContractSession().getNavigationInfo()
                .setUpdateLastAccessedPageOnly(true);
        //Taking from session
        //FIXME can use the session onject from request in service
        this.setToRequest(migrationPricingInfoRequest);
        String migrationDateSegmentId = this.getMigrationContractSession()
                .getDateSegmentId();

        migrationPricingInfoRequest.setMigratedDSSysID(Integer
                .parseInt(migrationDateSegmentId));
        // obtain the response
        MigrationPricingInfoResponse migrationPricingInfoResponse = (MigrationPricingInfoResponse) executeService(migrationPricingInfoRequest);

        if (null != migrationPricingInfoResponse
                && migrationPricingInfoResponse.isSuccess()) {
            pricingInformationList = migrationPricingInfoResponse
                    .getPricingInformationList();
            if (pricingInformationList != null
                    && pricingInformationList.size() > 0) {
                this.renderFlag = true;
            }
        }

        return pricingInformationList;
    }


    /**
     * Function to persist the Pricing information to the repository.
     * 
     * @param
     * @param
     * @param
     * @return String
     * @throws
     */
    public String savePricingInfo() {
        MigrationPricingInfoRequest migrationPricingInfoRequest = null;
        MigrationPricingInfoResponse migrationPricingInfoResponse = null;
        //check if mandatory fields are populated.
        if (isMandatoryFieldsValid()) {
            //obtain request
            migrationPricingInfoRequest = (MigrationPricingInfoRequest) this
                    .getServiceRequest(ServiceManager.MIGRATION_PRICING_INFO_REQUEST);
            //Taking from session

            this.setToRequest(migrationPricingInfoRequest);
            String migrationDateSegmentId = this.getMigrationContractSession()
                    .getDateSegmentId();
            migrationPricingInfoRequest.setMigratedDSSysID(Integer
                    .parseInt(migrationDateSegmentId));
            // Persist if action = 1
            migrationPricingInfoRequest
                    .setAction(BusinessConstants.MIGRATION_SAVE_ADD_PRICING_INFO);
            // bind values to request
            migrationPricingInfoRequest = setValuesToRequest(migrationPricingInfoRequest);
            //calls the service
            migrationPricingInfoResponse = (MigrationPricingInfoResponse) this
                    .executeService(migrationPricingInfoRequest);
            this.setToSession(migrationPricingInfoResponse);
            if (null != migrationPricingInfoResponse
                    && migrationPricingInfoResponse.isSuccess()) {
                this.coverage = WebConstants.EMPTY_STRING;
                this.pricing = WebConstants.EMPTY_STRING;
                this.network = WebConstants.EMPTY_STRING;
            }
            if (null != migrationPricingInfoResponse) {
                this.setValidationMessages(migrationPricingInfoResponse
                        .getMessages());
            }
        }
        pricingInformationList = this.retrivePricingInformationList();

        if (this.validationMessages != null) {

            getSession().setAttribute(WebConstants.MESSAGE_LIST_STEP3,
                    this.validationMessages);
            getRequest().setAttribute("RETAIN_Value", "");
            return WebConstants.MIG_CONTRACT_STEP4;
        }
      
        return goToNextPage(WebConstants.MIG_CONTRACT_STEP4);
    }


    /**
     * Function to validate mandatory fields in the page.
     * 
     * @param
     * @param
     * @param
     * @return boolean
     * @throws
     */
    private boolean isMandatoryFieldsValid() {

        validationMessages = new ArrayList();
        boolean invalidField = false;

        if (null == this.getCoverage()
                || WebConstants.EMPTY_STRING.equals(this.getCoverage().trim())) {
            coverageInvalid = true;
            invalidField = true;
        }
        if (null == this.getNetwork()
                || WebConstants.EMPTY_STRING.equals(this.getNetwork().trim())) {
            networkInvalid = true;
            invalidField = true;
        }
        if (null == this.getPricing()
                || WebConstants.EMPTY_STRING.equals(this.getPricing().trim())) {
            pricingInvalid = true;
            invalidField = true;
        }

        if (invalidField) {
            validationMessages.add(new ErrorMessage(
                    WebConstants.MANDATORY_FIELDS_REQUIRED));
            return false;
        }
        return true;

    }


    /**
     * Function to populate the values from the page to the request.
     * 
     * @param migrationPricingInfoRequest
     * @param
     * @param
     * @return MigrationPricingInfoRequest
     * @throws
     */
    private MigrationPricingInfoRequest setValuesToRequest(
            MigrationPricingInfoRequest migrationPricingInfoRequest) {
        List coverageList;
        List pricingList;
        List networkList;

        coverageList = WPDStringUtil.getListFromTildaString(this.getCoverage(),
                2, 2, 2);
        pricingList = WPDStringUtil.getListFromTildaString(this.getPricing(),
                2, 2, 2);
        networkList = WPDStringUtil.getListFromTildaString(this.getNetwork(),
                2, 2, 2);

        migrationPricingInfoRequest.setCoverage(coverageList.get(0).toString());
        migrationPricingInfoRequest.setPricing(pricingList.get(0).toString());
        migrationPricingInfoRequest.setNetwork(networkList.get(0).toString());

        return migrationPricingInfoRequest;
    }


    /**
     * Function to remove a Pricing Information from the repository.
     * 
     * @param
     * @param
     * @param
     * @return String
     * @throws
     */
    public String removePricingInfo() {
        MigrationPricingInfoRequest migrationPricingInfoRequest = (MigrationPricingInfoRequest) this
                .getServiceRequest(ServiceManager.MIGRATION_PRICING_INFO_REQUEST);
        // Delete if action = 3
        migrationPricingInfoRequest
                .setAction(BusinessConstants.MIGRATION_DELETE_PRICING_INFO);
        // bind values to request
        //FIXME Can use the session object from
        this.setToRequest(migrationPricingInfoRequest);
        migrationPricingInfoRequest.setMigratedDSSysID(Integer.parseInt(this
                .getMigrationContractSession().getDateSegmentId()));
        migrationPricingInfoRequest.setCoverage(this.getSelectedCoverageID());
        migrationPricingInfoRequest.setPricing(this.getSelectedPricingID());
        migrationPricingInfoRequest.setNetwork(this.getSelectedNetworkID());
        MigrationPricingInfoResponse migrationPricingInfoResponse = (MigrationPricingInfoResponse) executeService(migrationPricingInfoRequest);
        if (null != migrationPricingInfoResponse
                && migrationPricingInfoResponse.isSuccess()) {
            // set save message
            this.setValidationMessages(migrationPricingInfoResponse
                    .getMessages());
            this.setToSession(migrationPricingInfoResponse);
        }
        this.pricingInformationList = this.retrivePricingInformationList();
        getSession().setAttribute(WebConstants.MESSAGE_LIST_STEP3,
                this.validationMessages);
        getRequest().setAttribute("RETAIN_Value", "");
        return WebConstants.EMPTY_STRING;
    }


    /**
     * @return String
     */
    public String back() {
        updateNavigationInfo(BusinessConstants.MIGRATION_NAVIGATION_FLAG_TRUE,true);
        return goToNextPage(WebConstants.MIG_CONTRACT_STEP3);
    }


    /**
     * @param navigationFlag
     */
    public void updateNavigationInfo(boolean navigationFlag, boolean updateStepFlag) {
        MigrationPricingInfoRequest migrationPricingInfoRequest = null;
        MigrationPricingInfoResponse migrationPricingInfoResponse = null;
        migrationPricingInfoRequest = (MigrationPricingInfoRequest) this
                .getServiceRequest(ServiceManager.MIGRATION_PRICING_INFO_REQUEST);
        if (updateStepFlag)
            this.getMigrationContractSession().getNavigationInfo()
                    .setUpdateLastAccessedPageOnly(true);
                
        this.getMigrationContractSession().getNavigationInfo()
                .setNavigationFlag(navigationFlag);
        this.setToRequest(migrationPricingInfoRequest);
        migrationPricingInfoRequest
                .setAction(BusinessConstants.UPDATE_STEP_COMPLETED);
        migrationPricingInfoResponse = (MigrationPricingInfoResponse) this
                .executeService(migrationPricingInfoRequest);

        if (null != migrationPricingInfoResponse
                && migrationPricingInfoResponse.isSuccess()) {
            this.setToSession(migrationPricingInfoResponse);
        }
    }


    /**
     * @return String
     */
    public String next() {
        updateNavigationInfo(BusinessConstants.MIGRATION_NAVIGATION_FLAG_FALSE, false);
        return goToNextPage(WebConstants.MIG_CONTRACT_STEP5);
    }


    /**
     * @return String
     */
    public String done() {
        validationMessages = new ArrayList();
        if (getMigrationContractSession().getNavigationInfo()
                .getStepCompleted() >= BusinessConstants.STEP7) {
            return goToNextPage(WebConstants.MIG_CONTRACT_STEP9);
        } else {
        	
            this.validationMessages = validateStepNumber();
            getSession().setAttribute(WebConstants.MESSAGE_LIST_STEP3,
                    this.validationMessages);
            getRequest().setAttribute("RETAIN_Value", "");
        }
        this.pricingInformationList = this.retrivePricingInformationList();
        return WebConstants.MIG_CONTRACT_STEP4;
    }


    /**
     * Returns the coverage
     * 
     * @return String coverage.
     */
    public String getCoverage() {
        return coverage;
    }


    /**
     * Sets the coverage
     * 
     * @param coverage.
     */
    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }


    /**
     * Returns the network
     * 
     * @return String network.
     */
    public String getNetwork() {
        return network;
    }


    /**
     * Sets the network
     * 
     * @param network.
     */
    public void setNetwork(String network) {
        this.network = network;
    }


    /**
     * Returns the pricing
     * 
     * @return String pricing.
     */
    public String getPricing() {
        return pricing;
    }


    /**
     * Sets the pricing
     * 
     * @param pricing.
     */
    public void setPricing(String pricing) {
        this.pricing = pricing;
    }


    /**
     * Returns the selectedCoverageID
     * 
     * @return String selectedCoverageID.
     */
    public String getSelectedCoverageID() {
        return selectedCoverageID;
    }


    /**
     * Sets the selectedCoverageID
     * 
     * @param selectedCoverageID.
     */
    public void setSelectedCoverageID(String selectedCoverageID) {
        this.selectedCoverageID = selectedCoverageID;
    }


    /**
     * Returns the selectedMigratedDSSysID
     * 
     * @return String selectedMigratedDSSysID.
     */
    public String getSelectedMigratedDSSysID() {
        return selectedMigratedDSSysID;
    }


    /**
     * Sets the selectedMigratedDSSysID
     * 
     * @param selectedMigratedDSSysID.
     */
    public void setSelectedMigratedDSSysID(String selectedMigratedDSSysID) {
        this.selectedMigratedDSSysID = selectedMigratedDSSysID;
    }


    /**
     * Returns the selectedNetworkID
     * 
     * @return String selectedNetworkID.
     */
    public String getSelectedNetworkID() {
        return selectedNetworkID;
    }


    /**
     * Sets the selectedNetworkID
     * 
     * @param selectedNetworkID.
     */
    public void setSelectedNetworkID(String selectedNetworkID) {
        this.selectedNetworkID = selectedNetworkID;
    }


    /**
     * Returns the selectedPricingID
     * 
     * @return String selectedPricingID.
     */
    public String getSelectedPricingID() {
        return selectedPricingID;
    }


    /**
     * Sets the selectedPricingID
     * 
     * @param selectedPricingID.
     */
    public void setSelectedPricingID(String selectedPricingID) {
        this.selectedPricingID = selectedPricingID;
    }


    /**
     * Returns the renderFlag
     * 
     * @return boolean renderFlag.
     */
    public boolean isRenderFlag() {
        return renderFlag;
    }


    /**
     * Sets the renderFlag
     * 
     * @param renderFlag.
     */
    public void setRenderFlag(boolean renderFlag) {
        this.renderFlag = renderFlag;
    }	


    /**
     * Returns the pricingInformationList
     * 
     * @return List pricingInformationList.
     */
    public List getPricingInformationList() {
        pricingInformationList = retrivePricingInformationList();
        List list = (List) getSession().getAttribute(
                WebConstants.MESSAGE_LIST_STEP3);
        if (null != getSession().getAttribute("VALIDATION_MESSAGE")){
        	if(null == list){
        		list = (List)getSession().getAttribute("VALIDATION_MESSAGE");
        	}else{
        		list.addAll((List)getSession().getAttribute("VALIDATION_MESSAGE"));
        	}
        }
        if (null != list && list.size() > 0) {
            addAllMessagesToRequest(list);
        }
        if (null != getSession().getAttribute(WebConstants.MESSAGE_LIST_STEP3))
            getSession().removeAttribute(WebConstants.MESSAGE_LIST_STEP3);
        
        if (null != getSession().getAttribute("VALIDATION_MESSAGE"))
            getSession().removeAttribute("VALIDATION_MESSAGE");
        
        return pricingInformationList;
    }


    /**
     * Sets the pricingInformationList
     * 
     * @param pricingInformationList.
     */
    public void setPricingInformationList(List pricingInformationList) {
        this.pricingInformationList = pricingInformationList;
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
     * Returns the coverageInvalid
     * 
     * @return boolean coverageInvalid.
     */
    public boolean isCoverageInvalid() {
        return coverageInvalid;
    }


    /**
     * Sets the coverageInvalid
     * 
     * @param coverageInvalid.
     */
    public void setCoverageInvalid(boolean coverageInvalid) {
        this.coverageInvalid = coverageInvalid;
    }


    /**
     * Returns the networkInvalid
     * 
     * @return boolean networkInvalid.
     */
    public boolean isNetworkInvalid() {
        return networkInvalid;
    }


    /**
     * Sets the networkInvalid
     * 
     * @param networkInvalid.
     */
    public void setNetworkInvalid(boolean networkInvalid) {
        this.networkInvalid = networkInvalid;
    }


    /**
     * Returns the pricingInvalid
     * 
     * @return boolean pricingInvalid.
     */
    public boolean isPricingInvalid() {
        return pricingInvalid;
    }


    /**
     * Sets the pricingInvalid
     * 
     * @param pricingInvalid.
     */
    public void setPricingInvalid(boolean pricingInvalid) {
        this.pricingInvalid = pricingInvalid;
    }


    /**
     * @return Returns the migrationSysId.
     */
    public String getMigrationSysId() {
        return migrationSysId;
    }


    /**
     * @param migrationSysId
     *            The migrationSysId to set.
     */
    public void setMigrationSysId(String migrationSysId) {
        this.migrationSysId = migrationSysId;
    }
}