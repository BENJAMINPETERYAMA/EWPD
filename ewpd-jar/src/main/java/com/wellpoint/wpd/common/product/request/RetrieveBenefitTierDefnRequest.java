/*
 * Created on Aug 12, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.request;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveBenefitTierDefnRequest extends ProductRequest{

	private int benefitDefintionId;
	private int benefitComponentId;
	private int productId;
	private int benefitId;
	private int productStructureId;
	
	
	
    /**
     * @return Returns the benefitComponentId.
     */
    public int getBenefitComponentId() {
        return benefitComponentId;
    }
    /**
     * @param benefitComponentId The benefitComponentId to set.
     */
    public void setBenefitComponentId(int benefitComponentId) {
        this.benefitComponentId = benefitComponentId;
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
     * @return Returns the benefitDefintionId.
     */
    public int getBenefitDefintionId() {
        return benefitDefintionId;
    }
    /**
     * @param benefitDefintionId The benefitDefintionId to set.
     */
    public void setBenefitDefintionId(int benefitDefintionId) {
        this.benefitDefintionId = benefitDefintionId;
    }
    /**
     * @return Returns the benefitId.
     */
    public int getBenefitId() {
        return benefitId;
    }
    /**
     * @param benefitId The benefitId to set.
     */
    public void setBenefitId(int benefitId) {
        this.benefitId = benefitId;
    }
    /**
     * @return Returns the productStructureId.
     */
    public int getProductStructureId() {
        return productStructureId;
    }
    /**
     * @param productStructureId The productStructureId to set.
     */
    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
    }
}
