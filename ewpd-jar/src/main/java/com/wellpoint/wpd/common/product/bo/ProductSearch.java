/*
 * Created on Feb 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.bo;

/**
 * @author u13531
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ProductSearch {

    private String productName;

    private String productDescription;

    private String productStructure;

    private String productFamily;

    private String businessGroup;

    private String businessEntity;

    private int productVersion;
    
    private String effectiveDate;
    
    private String expiryDate;
    
    private int productKey;

    /**

     * Returns the productKey

     * @return int productKey.

     */

    public int getProductKey() {
        return productKey;
    }
    /**

     * Sets the productKey

     * @param productKey.

     */

    public void setProductKey(int productKey) {
        this.productKey = productKey;
    }
    /**

     * Returns the effectiveDate

     * @return String effectiveDate.

     */

    public String getEffectiveDate() {
        return effectiveDate;
    }
    /**

     * Sets the effectiveDate

     * @param effectiveDate.

     */

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    /**

     * Returns the expiryDate

     * @return String expiryDate.

     */

    public String getExpiryDate() {
        return expiryDate;
    }
    /**

     * Sets the expiryDate

     * @param expiryDate.

     */

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    /**
     * @return Returns the businessEntity.
     */
    public String getBusinessEntity() {
        return businessEntity;
    }

    /**
     * @param businessEntity The businessEntity to set.
     */
    public void setBusinessEntity(String businessEntity) {
        this.businessEntity = businessEntity;
    }

    /**
     * @return Returns the businessGroup.
     */
    public String getBusinessGroup() {
        return businessGroup;
    }

    /**
     * @param businessGroup The businessGroup to set.
     */
    public void setBusinessGroup(String businessGroup) {
        this.businessGroup = businessGroup;
    }

    /**
     * @return Returns the productDescription.
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * @param productDescription The productDescription to set.
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * @return Returns the productFamily.
     */
    public String getProductFamily() {
        return productFamily;
    }

    /**
     * @param productFamily The productFamily to set.
     */
    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }

    /**
     * @return Returns the productName.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName The productName to set.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return Returns the productStructure.
     */
    public String getProductStructure() {
        return productStructure;
    }

    /**
     * @param productStructure The productStructure to set.
     */
    public void setProductStructure(String productStructure) {
        this.productStructure = productStructure;
    }

 

    public void setProductVersion(int productVersion) {
        this.productVersion = productVersion;
    }
    /**

     * Returns the productVersion

     * @return int productVersion.

     */

    public int getProductVersion() {
        return productVersion;
    }
}