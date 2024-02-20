/*
 * Created on Mar 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.product;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.product.request.RetrieveProductBenefitRequest;
import com.wellpoint.wpd.common.product.response.RetrieveProductBenefitResponse;
import com.wellpoint.wpd.web.framework.service.ServiceManager;

/**
 * @author u13541
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ProductNonAdjBenefitMandatesBackingBean extends ProductBackingBean {

    private List locateResultsList =null;

    private String optionalIdentifier = null;

    private Date effectiveDate = null;

    private Date expiryDate = null;

    private String mandate = null;

    private boolean locateResultsReturned;

    private String printValue;

    private String dummyVar;

    private String productType = "";


    /**
     * @return Returns the printValue.
     */
    public String getPrintValue() {
        String requestForPrint = (String) getRequest().getAttribute(
                "printValueForNonAdj");
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


    public ProductNonAdjBenefitMandatesBackingBean() {
        if (getProductSessionObject().getMode() == ProductSessionObject.VIEW_MODE) {
            this.setBreadCumbTextForView();
        } else {
            this.setBreadCumbTextForEdit();
        }
    }


    public String getNonAdjBenefitMandates() {
        productType = super.getProductTypeFromSession();
        if (getProductSessionObject().getMode() == ProductSessionObject.VIEW_MODE) {
            return "productnonadjmandateview";
        } else {
            return "productnonadjmandate";
        }
    }


    /**
     * @return Returns the locateResultsList.
     */
    public List getLocateResultsList() {
        if (!locateResultsReturned) {
            RetrieveProductBenefitRequest retrieveProductBenefitRequest = (RetrieveProductBenefitRequest) this
                    .getServiceRequest(ServiceManager.RETRIEVE_PRODUCT_BENEFIT);
            retrieveProductBenefitRequest
                    .setAction(RetrieveProductBenefitRequest.RETRIEVE_NONADJ_BENEFIT_MANDATES);
            retrieveProductBenefitRequest
                    .setBenefitMasterSystemId(getProductSessionObject()
                            .getBenefitId());
            RetrieveProductBenefitResponse retrieveProductBenefitResponse = (RetrieveProductBenefitResponse) executeService(retrieveProductBenefitRequest);
            if (retrieveProductBenefitResponse != null) {

                this.locateResultsList = retrieveProductBenefitResponse
                        .getNonAdjMandateList();
                if (locateResultsList.size() == 0) {
                    this.locateResultsList = null;
                }
                //if(locateResults.getLocateResults()!= null &&
                // locateResults.getLocateResults().size()!=0 ){
                locateResultsReturned = true;
                //}
            }

        }
        return locateResultsList;

    }


    /**
     * Returns the effectiveDate
     * 
     * @return Date effectiveDate.
     */

    public Date getEffectiveDate() {
        return effectiveDate;
    }


    /**
     * Sets the effectiveDate
     * 
     * @param effectiveDate.
     */

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }


    /**
     * Returns the expiryDate
     * 
     * @return Date expiryDate.
     */

    public Date getExpiryDate() {
        return expiryDate;
    }


    /**
     * Sets the expiryDate
     * 
     * @param expiryDate.
     */

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


    /**
     * Returns the mandate
     * 
     * @return String mandate.
     */

    public String getMandate() {
        return mandate;
    }


    /**
     * Sets the mandate
     * 
     * @param mandate.
     */

    public void setMandate(String mandate) {
        this.mandate = mandate;
    }


    /**
     * Returns the optionalIdentifier
     * 
     * @return String optionalIdentifier.
     */

    public String getOptionalIdentifier() {
        return optionalIdentifier;
    }


    /**
     * Sets the optionalIdentifier
     * 
     * @param optionalIdentifier.
     */

    public void setOptionalIdentifier(String optionalIdentifier) {
        this.optionalIdentifier = optionalIdentifier;
    }


    /**
     * Sets the locateResultsList
     * 
     * @param locateResultsList.
     */

    public void setLocateResultsList(List locateResultsList) {
        this.locateResultsList = locateResultsList;
    }


    /**
     * Returns the dummyVar
     * 
     * @return String dummyVar.
     */

    public String getDummyVar() {
        return dummyVar;
    }


    /**
     * Sets the dummyVar
     * 
     * @param dummyVar.
     */

    public void setDummyVar(String dummyVar) {
        this.dummyVar = dummyVar;
    }


    /**
     * @return Returns the productType.
     */
    public String getProductType() {
        return productType;
    }


    /**
     * @param productType
     *            The productType to set.
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }
}