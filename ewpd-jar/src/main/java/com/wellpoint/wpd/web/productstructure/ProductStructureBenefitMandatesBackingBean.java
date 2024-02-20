/*
 * BenefitMandatesBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.productstructure;

import java.util.List;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.productstructure.request.RetrieveBenefitMandatesRequest;
import com.wellpoint.wpd.common.productstructure.response.RetrieveBenefitMandatesResponse;
import com.wellpoint.wpd.common.productstructure.vo.MandatesVO;
import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;
import com.wellpoint.wpd.common.standardbenefit.bo.CitationNumberBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StateBO;

/**
 * Backing bean for benfit mandates tab.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureBenefitMandatesBackingBean extends
        ProductStructureBackingBean {

    ProductStructureVO productStructure;

    private String productStructureName;

    private List mandatesList;

    private String optionalIdentifier;

    private String effectiveDate;

    private String expiryDate;

    private String benefitName;

    private String mandateCategory;

    private String citationNumber;

    private String fundingArrangement;

    private String effectiveness;

    private String text;

    private List citationNumberList;

    private String fundingArrangementId;

    private String fundingArrangementDesc;

    private String mandateCategoryId;

    private String mandateCategoryDesc;

    private String stateCode;

    private String printMandateInformation;


    /**
     *  
     */
    public ProductStructureBenefitMandatesBackingBean() {
        super();
        this.setBreadCrumbTextForPS();
    }


    /**
     * To get amndates from db.
     * 
     * @return String.
     */
    public String retrieveMandates() {
        Logger.logInfo("Entering the method for retrieving mandates");
        RetrieveBenefitMandatesResponse retrieveBenefitMandatesResponse = null;
        RetrieveBenefitMandatesRequest retrieveBenefitMandatesRequest = new RetrieveBenefitMandatesRequest();
        String standardKey = (String) getSession().getAttribute(
                "STANDARD_BNFT_KEY");
        int benefitId = Integer.parseInt(standardKey);
        productStructureName = getNameFromSession();
        MandatesVO mandatesVO = new MandatesVO();
        mandatesVO.setBenefitId(benefitId);
        retrieveBenefitMandatesRequest.setMandatesVO(mandatesVO);
        retrieveBenefitMandatesResponse = (RetrieveBenefitMandatesResponse) this
                .executeService(retrieveBenefitMandatesRequest);
        Logger.logInfo("Returning the method for retrieving mandates");
        /*DomainDetail domainDetail = retrieveBenefitMandatesResponse
                .getDomainDetail();*/

        if (null != retrieveBenefitMandatesResponse
                && !retrieveBenefitMandatesResponse.isErrorMessageInList()) {
            this.setMandateCategory(retrieveBenefitMandatesResponse
                    .getBenefitMandateBO().getMandateCategoryDesc());
            this
                    .setCitationNumber(getTildaStringForCitationNumber(retrieveBenefitMandatesResponse
                            .getBenefitMandateBO().getCitationNumberList()));
            if (null != retrieveBenefitMandatesResponse.getBenefitMandateBO()
                    .getFundingArrangementDesc())
                this.setFundingArrangement(retrieveBenefitMandatesResponse
                        .getBenefitMandateBO().getFundingArrangement()
                        + "~"
                        + retrieveBenefitMandatesResponse.getBenefitMandateBO()
                                .getFundingArrangementDesc());
            this.setEffectiveness(retrieveBenefitMandatesResponse
                    .getBenefitMandateBO().getMandateEffectivenessDesc());
            this.setText(retrieveBenefitMandatesResponse.getBenefitMandateBO()
                    .getText());
            if ("1".equals(retrieveBenefitMandatesResponse
                    .getBenefitMandateBO().getMandateType())) {
                this.setStateCode("ALL~ALL");
            } else {
                if (null != retrieveBenefitMandatesResponse
                        .getBenefitMandateBO().getBenefitStateCodeList())
                    this
                            .setStateCode(getTildaStringForStateCode(retrieveBenefitMandatesResponse
                                    .getBenefitMandateBO()
                                    .getBenefitStateCodeList()));

            }

        } else if (retrieveBenefitMandatesResponse != null
                && retrieveBenefitMandatesResponse.isErrorMessageInList()) {
            this.setMandateCategory("");
            this.setCitationNumber("");
            this.setFundingArrangement("");
            this.setEffectiveness("");
            this.setText("");

        }
        if (getActionFromSession() != null
                && getActionFromSession().equals("VIEW")) {
            this.setBreadCrumbTextForView();
            return "viewMandates";
        } else {
            this.setBreadCrumbTextForEdit();
            return "editMandates";
        }

    }


    /**
     * To convert List to tilda separated String.
     * 
     * @return String.
     */
    public static String getTildaStringForCitationNumber(List domainItems) {

        if (domainItems == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < domainItems.size(); i++) {
            CitationNumberBO element = (CitationNumberBO) domainItems.get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getCitationNumber());
            buffer.append("~");
            buffer.append(element.getCitationNumber());

        }
        return buffer.toString();
    }


    /**
     * To convert List to tilda separated String.
     * 
     * @return String.
     */
    public static String getTildaStringForStateCode(List domainItems) {

        if (domainItems == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < domainItems.size(); i++) {
            StateBO element = (StateBO) domainItems.get(i);
            if (i != 0) {
                buffer.append("~");
            }
            if (element.getBenefitStateCode().equals("ALL")) {
                buffer.append(element.getBenefitStateCode());
                buffer.append("~");
                buffer.append("ALL");
            } else {
                buffer.append(element.getBenefitStateCode());
                buffer.append("~");
                buffer.append(element.getBenefitStateDesc());
            }

        }
        return buffer.toString();
    }


    /**
     * Returns the benefitName.
     * 
     * @return String benefitName.
     */

    public String getBenefitName() {
        return benefitName;
    }


    /**
     * Sets the benefitName.
     * 
     * @param benefitName.
     */

    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }


    /**
     * Returns the effectiveDate.
     * 
     * @return String effectiveDate.
     */

    public String getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * Sets the effectiveDate.
     * 
     * @param effectiveDate.
     */

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * Returns the expiryDate.
     * 
     * @return String expiryDate.
     */

    public String getExpiryDate() {
        return expiryDate;
    }


    /**
     * Sets the expiryDate.
     * 
     * @param expiryDate.
     */

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }


    /**
     * Returns the mandatesList.
     * 
     * @return List mandatesList.
     */

    public List getMandatesList() {
        Logger.logInfo("Entering the method for getting mandates list");
        RetrieveBenefitMandatesResponse retrieveBenefitMandatesResponse = null;
        RetrieveBenefitMandatesRequest retrieveBenefitMandatesRequest = new RetrieveBenefitMandatesRequest();
        String standardKey = (String) getSession().getAttribute(
                "STANDARD_BNFT_KEY");
        int benefitId = Integer.parseInt(standardKey);
        productStructureName = getNameFromSession();
        MandatesVO mandatesVO = new MandatesVO();
        mandatesVO.setBenefitId(benefitId);
        retrieveBenefitMandatesRequest.setMandatesVO(mandatesVO);
        retrieveBenefitMandatesResponse = (RetrieveBenefitMandatesResponse) this
                .executeService(retrieveBenefitMandatesRequest);
        Logger.logInfo("Returning the method for getting mandates list");
        if (null != retrieveBenefitMandatesResponse.getMandateList()
                && retrieveBenefitMandatesResponse.getMandateList().size() != 0) {
            this.mandatesList = retrieveBenefitMandatesResponse
                    .getMandateList();
        } else {
            this.mandatesList = null;
        }
        return mandatesList;
    }


    /**
     * Sets the mandatesList.
     * 
     * @param mandatesList.
     */

    public void setMandatesList(List mandatesList) {
        this.mandatesList = mandatesList;
    }


    /**
     * Returns the optionalIdentifier.
     * 
     * @return String optionalIdentifier.
     */

    public String getOptionalIdentifier() {
        return optionalIdentifier;
    }


    /**
     * Sets the optionalIdentifier.
     * 
     * @param optionalIdentifier.
     */

    public void setOptionalIdentifier(String optionalIdentifier) {
        this.optionalIdentifier = optionalIdentifier;
    }


    /**
     * @return citationNumber
     * 
     * Returns the citationNumber.
     */
    public String getCitationNumber() {
        return citationNumber;
    }


    /**
     * @param citationNumber
     * 
     * Sets the citationNumber.
     */
    public void setCitationNumber(String citationNumber) {
        this.citationNumber = citationNumber;
    }


    /**
     * @return citationNumberList
     * 
     * Returns the citationNumberList.
     */
    public List getCitationNumberList() {
        return citationNumberList;
    }


    /**
     * @param citationNumberList
     * 
     * Sets the citationNumberList.
     */
    public void setCitationNumberList(List citationNumberList) {
        this.citationNumberList = citationNumberList;
    }


    /**
     * @return effectiveness
     * 
     * Returns the effectiveness.
     */
    public String getEffectiveness() {
        return effectiveness;
    }


    /**
     * @param effectiveness
     * 
     * Sets the effectiveness.
     */
    public void setEffectiveness(String effectiveness) {
        this.effectiveness = effectiveness;
    }


    /**
     * @return fundingArrangement
     * 
     * Returns the fundingArrangement.
     */
    public String getFundingArrangement() {
        return fundingArrangement;
    }


    /**
     * @param fundingArrangement
     * 
     * Sets the fundingArrangement.
     */
    public void setFundingArrangement(String fundingArrangement) {
        this.fundingArrangement = fundingArrangement;
    }


    /**
     * @return fundingArrangementDesc
     * 
     * Returns the fundingArrangementDesc.
     */
    public String getFundingArrangementDesc() {
        return fundingArrangementDesc;
    }


    /**
     * @param fundingArrangementDesc
     * 
     * Sets the fundingArrangementDesc.
     */
    public void setFundingArrangementDesc(String fundingArrangementDesc) {
        this.fundingArrangementDesc = fundingArrangementDesc;
    }


    /**
     * @return fundingArrangementId
     * 
     * Returns the fundingArrangementId.
     */
    public String getFundingArrangementId() {
        return fundingArrangementId;
    }


    /**
     * @param fundingArrangementId
     * 
     * Sets the fundingArrangementId.
     */
    public void setFundingArrangementId(String fundingArrangementId) {
        this.fundingArrangementId = fundingArrangementId;
    }


    /**
     * @return mandateCategory
     * 
     * Returns the mandateCategory.
     */
    public String getMandateCategory() {
        return mandateCategory;
    }


    /**
     * @param mandateCategory
     * 
     * Sets the mandateCategory.
     */
    public void setMandateCategory(String mandateCategory) {
        this.mandateCategory = mandateCategory;
    }


    /**
     * @return mandateCategoryDesc
     * 
     * Returns the mandateCategoryDesc.
     */
    public String getMandateCategoryDesc() {
        return mandateCategoryDesc;
    }


    /**
     * @param mandateCategoryDesc
     * 
     * Sets the mandateCategoryDesc.
     */
    public void setMandateCategoryDesc(String mandateCategoryDesc) {
        this.mandateCategoryDesc = mandateCategoryDesc;
    }


    /**
     * @return mandateCategoryId
     * 
     * Returns the mandateCategoryId.
     */
    public String getMandateCategoryId() {
        return mandateCategoryId;
    }


    /**
     * @param mandateCategoryId
     * 
     * Sets the mandateCategoryId.
     */
    public void setMandateCategoryId(String mandateCategoryId) {
        this.mandateCategoryId = mandateCategoryId;
    }


    /**
     * @return text
     * 
     * Returns the text.
     */
    public String getText() {
        return text;
    }


    /**
     * @param text
     * 
     * Sets the text.
     */
    public void setText(String text) {
        this.text = text;
    }


    /**
     * @return stateCode
     * 
     * Returns the stateCode.
     */
    public String getStateCode() {
        return stateCode;
    }


    /**
     * @param stateCode
     * 
     * Sets the stateCode.
     */
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }


    /**
     * @return Returns the printMandateInformation.
     */
    public String getPrintMandateInformation() {
        retrieveMandates();
        return printMandateInformation;
    }


    /**
     * @param printMandateInformation
     *            The printMandateInformation to set.
     */
    public void setPrintMandateInformation(String printMandateInformation) {
        this.printMandateInformation = printMandateInformation;
    }

}