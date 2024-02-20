/*
 * BenefitDefinitionHeadTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.migration.tree;

import java.util.ArrayList;
import java.util.List;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;

import org.apache.myfaces.custom.tree2.TreeModel;
import com.wellpoint.wpd.business.product.builder.ProductTreeBuilder;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeStandardBenefits;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitTreeNodeBase extends BaseTreeNode {
    private String contractSysId;
    private int baseId;

    private static final String STD_BENEFIT_HEAD = "Standard-Benefits-Head";

    private String dateSegmentId;
    /**
     * Constructor
     */
    public BenefitTreeNodeBase() {
        super();
    }


    /**
     * Constructor
     * 
     * @param TreeModel,parent,type,identifier,name,leaf
     */
    public BenefitTreeNodeBase(TreeModel treeModel, BaseTreeNode parent,
            String type, String identifier, String name, boolean leaf,
            String contractSysId,int baseId, String migratedDateSegmentId) {
        super(treeModel, parent, type, identifier, name, leaf);
        this.contractSysId = contractSysId;
        this.baseId =baseId;
        this.dateSegmentId = migratedDateSegmentId;
    }


    /**
     * Method is used to load the Benefit Definitions
     * 
     * @return List
     */
    public List loadChildren() {
        children = new ArrayList();

        String productId = this.getParent().getIdentifier();

        String contId = this.contractSysId;
        int baseId= this.getBaseId();
        //gets the benefit component id
        String benefitComponentId = this.getIdentifier();

        List standardBenefitList = new ArrayList();

        ProductTreeBuilder productTreeBuilder = new ProductTreeBuilder();

        // an instance of Standard Benefit BO is created
        ProductTreeStandardBenefits standardBenefitsDetails = new ProductTreeStandardBenefits();

        //checks if the identifier is null
        if (null == benefitComponentId || "0".equals(benefitComponentId))
            return null;

        //sets the benefit component id to the BO
        standardBenefitsDetails.setBenefitComponentId(Integer
                .parseInt(benefitComponentId));
        standardBenefitsDetails.setMigratedContractSysId(Integer
                .parseInt(contId));
        standardBenefitsDetails.setMgrtdDatesegmentId(Integer
                .parseInt(dateSegmentId));
        try {
        	if(baseId ==0){
            //fteches the standard benefit details
            standardBenefitList = productTreeBuilder
                    .searchBenefitsForMigrationTree(standardBenefitsDetails);
        	}else{
        		 standardBenefitList = productTreeBuilder
                 		.searchBenefitsForMigrationTree(standardBenefitsDetails,baseId);
        	}
        } catch (WPDException e) {
			Logger.logError(e);
        }
        //the standard benefit list is iterated to get the standard benefits
        int count = -1;
        for (int i = 0; i < standardBenefitList.size(); i++) {
            standardBenefitsDetails = (ProductTreeStandardBenefits) standardBenefitList
                    .get(i);
            String standardBenefitId = Integer.toString(standardBenefitsDetails
                    .getStandardBenefitId());
            String standardBnftName = standardBenefitsDetails
                    .getStandardBenefitDesc();
            count = standardBenefitsDetails.getCount();
            //standard benefits added as the children of Benefit Component
            StandardTreeNodeBase nodeBase = new StandardTreeNodeBase(
                    getModel(), this, STD_BENEFIT_HEAD, standardBenefitId,
                    standardBnftName, true, count);

            children.add(nodeBase);
        }

        return children;
    }


    /**
     * Returns the contractSysId
     * 
     * @return String contractSysId.
     */
    public String getContractSysId() {
        return contractSysId;
    }


    /**
     * Sets the contractSysId
     * 
     * @param contractSysId.
     */
    public void setContractSysId(String contractSysId) {
        this.contractSysId = contractSysId;
    }
	/**
	 * @return Returns the baseId.
	 */
	public int getBaseId() {
		return baseId;
	}
	/**
	 * @param baseId The baseId to set.
	 */
	public void setBaseId(int baseId) {
		this.baseId = baseId;
	}
}