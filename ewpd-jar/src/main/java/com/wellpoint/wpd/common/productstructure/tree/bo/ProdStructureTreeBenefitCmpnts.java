/*
 * ProdStructureTreeBenefitCmpnts.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.productstructure.tree.bo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProdStructureTreeBenefitCmpnts {

    private int productStructureId;

    private int benefitCmpntId;

    private String benefitCmpntDesc;

    private int seqNum;
    
    
    
	/**
	 * @return Returns the seqNum.
	 */
	public int getSeqNum() {
		return seqNum;
	}
	/**
	 * @param seqNum The seqNum to set.
	 */
	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}
    /**
     * @return Returns the benefitCmpntDesc.
     */
    public String getBenefitCmpntDesc() {
        return benefitCmpntDesc;
    }


    /**
     * @param benefitCmpntDesc
     *            The benefitCmpntDesc to set.
     */
    public void setBenefitCmpntDesc(String benefitCmpntDesc) {
        this.benefitCmpntDesc = benefitCmpntDesc;
    }


    /**
     * @return Returns the benefitCmpntId.
     */
    public int getBenefitCmpntId() {
        return benefitCmpntId;
    }


    /**
     * @param benefitCmpntId
     *            The benefitCmpntId to set.
     */
    public void setBenefitCmpntId(int benefitCmpntId) {
        this.benefitCmpntId = benefitCmpntId;
    }


    /**
     * @return Returns the productStructureId.
     */
    public int getProductStructureId() {
        return productStructureId;
    }


    /**
     * @param productStructureId
     *            The productStructureId to set.
     */
    public void setProductStructureId(int productStructureId) {
        this.productStructureId = productStructureId;
    }

}