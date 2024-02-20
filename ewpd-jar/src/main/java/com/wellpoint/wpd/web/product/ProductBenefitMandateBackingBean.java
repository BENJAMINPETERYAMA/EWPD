/*
 * Created on Feb 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.product;

import java.util.List;

import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.product.request.ProductBenefitMandateRetrieveRequest;
import com.wellpoint.wpd.common.product.response.ProductBenefitMndateRetrieveResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.CitationNumberBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StateBO;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u13592
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ProductBenefitMandateBackingBean extends ProductBackingBean {

    //enhancement

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

    private List stateDescList;

    private String stateCode;

    private String stateDesc;

    private String printValue;

    private String loadViewForPrint;
    
    private String dummyVar;
/*
 * methode for mandate details
 */

    public String loadMandateInfo() {
        ProductBenefitMandateRetrieveRequest productBenefitMandateRetrieveRequest = (ProductBenefitMandateRetrieveRequest) this
                .getServiceRequest(ServiceManager.PRODUCT_BENEFIT_MANDATE_RETRIEVE_REQUEST);
        productBenefitMandateRetrieveRequest.setBenefitComponentId(super
                .getBenefitComponentIdFromSession());
        productBenefitMandateRetrieveRequest.setBenefitId(super
                .getBenefitIdFromSession());
        productBenefitMandateRetrieveRequest.setProductId(super
                .getProductKeyFromSession());
        ProductBenefitMndateRetrieveResponse productBenefitMndateRetrieveResponse = (ProductBenefitMndateRetrieveResponse) executeService(productBenefitMandateRetrieveRequest);
        //changes
        DomainDetail domainDetail = productBenefitMndateRetrieveResponse
                .getDomainDetail();

        if (null != productBenefitMndateRetrieveResponse
                && !productBenefitMndateRetrieveResponse.isErrorMessageInList()) {
            this.setMandateCategory(productBenefitMndateRetrieveResponse
                    .getBenefitMandateBO().getMandateCategoryDesc());
            this
                    .setCitationNumber(getTildaStringForCitationNumber(productBenefitMndateRetrieveResponse
                            .getBenefitMandateBO().getCitationNumberList()));
            if (null != productBenefitMndateRetrieveResponse
                    .getBenefitMandateBO().getFundingArrangementDesc())
                this.setFundingArrangement(productBenefitMndateRetrieveResponse
                        .getBenefitMandateBO().getFundingArrangement()
                        + "~"
                        + productBenefitMndateRetrieveResponse
                                .getBenefitMandateBO()
                                .getFundingArrangementDesc());
            this.setEffectiveness(productBenefitMndateRetrieveResponse
                    .getBenefitMandateBO().getMandateEffectivenessDesc());
            this.setText(productBenefitMndateRetrieveResponse
                    .getBenefitMandateBO().getText());
            if ("1".equals(productBenefitMndateRetrieveResponse
                    .getBenefitMandateBO().getMandateType())) {
                this.setStateCode(WebConstants.ALL_99);
            } else {
                if (null != productBenefitMndateRetrieveResponse
                        .getBenefitMandateBO().getBenefitStateCodeList())
                    this
                            .setStateCode(getTildaStringForStateCode(productBenefitMndateRetrieveResponse
                                    .getBenefitMandateBO()
                                    .getBenefitStateCodeList()));
            }
        } else if (productBenefitMndateRetrieveResponse != null
                && productBenefitMndateRetrieveResponse.isErrorMessageInList()) {
            this.setMandateCategory("");
            this.setCitationNumber("");
            this.setFundingArrangement("");
            this.setEffectiveness("");
            this.setText("");

        }

        if (getProductSessionObject().getMode() == ProductSessionObject.VIEW_MODE) {
            return "productnonadjmandateview";
        } else {
            return "productnonadjmandate";
        }
    }


    public String ah() {

        return "";

    }
/*
 * 
 * to separate the tilada separated Strings
 * 
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


    public static String getTildaStringForStateCode(List domainItems) {

        if (domainItems == null)
            return "";

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < domainItems.size(); i++) {
            StateBO element = (StateBO) domainItems.get(i);
            if (i != 0) {
                buffer.append("~");
            }
            buffer.append(element.getBenefitStateCode());
            buffer.append("~");
            if ("ALL".equals(element.getBenefitStateCode())) {
                buffer.append("ALL");
            } else {
                buffer.append(element.getBenefitStateDesc());
            }
        }
        return buffer.toString();
    }


    /**
     * @return Returns the citationNumber.
     */
    public String getCitationNumber() {
        return citationNumber;
    }


    /**
     * @param citationNumber
     *            The citationNumber to set.
     */
    public void setCitationNumber(String citationNumber) {
        this.citationNumber = citationNumber;
    }


    /**
     * @return Returns the citationNumberList.
     */
    public List getCitationNumberList() {
        return citationNumberList;
    }


    /**
     * @param citationNumberList
     *            The citationNumberList to set.
     */
    public void setCitationNumberList(List citationNumberList) {
        this.citationNumberList = citationNumberList;
    }


    /**
     * @return Returns the effectiveness.
     */
    public String getEffectiveness() {
        return effectiveness;
    }


    /**
     * @param effectiveness
     *            The effectiveness to set.
     */
    public void setEffectiveness(String effectiveness) {
        this.effectiveness = effectiveness;
    }


    /**
     * @return Returns the fundingArrangement.
     */
    public String getFundingArrangement() {
        return fundingArrangement;
    }


    /**
     * @param fundingArrangement
     *            The fundingArrangement to set.
     */
    public void setFundingArrangement(String fundingArrangement) {
        this.fundingArrangement = fundingArrangement;
    }


    /**
     * @return Returns the fundingArrangementDesc.
     */
    public String getFundingArrangementDesc() {
        return fundingArrangementDesc;
    }


    /**
     * @param fundingArrangementDesc
     *            The fundingArrangementDesc to set.
     */
    public void setFundingArrangementDesc(String fundingArrangementDesc) {
        this.fundingArrangementDesc = fundingArrangementDesc;
    }


    /**
     * @return Returns the fundingArrangementId.
     */
    public String getFundingArrangementId() {
        return fundingArrangementId;
    }


    /**
     * @param fundingArrangementId
     *            The fundingArrangementId to set.
     */
    public void setFundingArrangementId(String fundingArrangementId) {
        this.fundingArrangementId = fundingArrangementId;
    }


    /**
     * @return Returns the mandateCategory.
     */
    public String getMandateCategory() {
        return mandateCategory;
    }


    /**
     * @param mandateCategory
     *            The mandateCategory to set.
     */
    public void setMandateCategory(String mandateCategory) {
        this.mandateCategory = mandateCategory;
    }


    /**
     * @return Returns the mandateCategoryDesc.
     */
    public String getMandateCategoryDesc() {
        return mandateCategoryDesc;
    }


    /**
     * @param mandateCategoryDesc
     *            The mandateCategoryDesc to set.
     */
    public void setMandateCategoryDesc(String mandateCategoryDesc) {
        this.mandateCategoryDesc = mandateCategoryDesc;
    }


    /**
     * @return Returns the mandateCategoryId.
     */
    public String getMandateCategoryId() {
        return mandateCategoryId;
    }


    /**
     * @param mandateCategoryId
     *            The mandateCategoryId to set.
     */
    public void setMandateCategoryId(String mandateCategoryId) {
        this.mandateCategoryId = mandateCategoryId;
    }


    /**
     * @return Returns the stateCode.
     */
    public String getStateCode() {
        return stateCode;
    }


    /**
     * @param stateCode
     *            The stateCode to set.
     */
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }


    /**
     * @return Returns the stateDesc.
     */
    public String getStateDesc() {
        return stateDesc;
    }


    /**
     * @param stateDesc
     *            The stateDesc to set.
     */
    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc;
    }


    /**
     * @return Returns the stateDescList.
     */
    public List getStateDescList() {
        return stateDescList;
    }


    /**
     * @param stateDescList
     *            The stateDescList to set.
     */
    public void setStateDescList(List stateDescList) {
        this.stateDescList = stateDescList;
    }


    /**
     * @return Returns the text.
     */
    public String getText() {
        return text;
    }


    /**
     * @param text
     *            The text to set.
     */
    public void setText(String text) {
        this.text = text;
    }


    /**
     * @return Returns the printValue.
     */
    public String getPrintValue() {
        String requestForPrint = getRequest().getAttribute(
                "printValueForManInfo").toString();
        if (null != requestForPrint && !requestForPrint.equals("")) {
            printValue = requestForPrint;
        } else {
            printValue = "";
        }
        return printValue;
    }


    /**
     * @param printValue
     *            The printValue to set.
     */
    public void setPrintValue(String printValue) {
        this.printValue = printValue;
    }


    /**
     * @return Returns the loadViewForPrint.
     */
    public String getLoadViewForPrint() {
        loadMandateInfo();
        return null;
    }


    /**
     * @param loadViewForPrint
     *            The loadViewForPrint to set.
     */
    public void setLoadViewForPrint(String loadViewForPrint) {
        this.loadViewForPrint = loadViewForPrint;
    }
	/**
	 * @return Returns the dummyVar.
	 */
	public String getDummyVar() {
		loadMandateInfo();
		return dummyVar;
	}
	/**
	 * @param dummyVar The dummyVar to set.
	 */
	public void setDummyVar(String dummyVar) {
		this.dummyVar = dummyVar;
	}
}