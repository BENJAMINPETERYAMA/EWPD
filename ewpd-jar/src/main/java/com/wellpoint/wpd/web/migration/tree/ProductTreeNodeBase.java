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
import com.wellpoint.wpd.business.contract.builder.ContractTreeBuilder;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitComponents;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductTreeNodeBase extends BaseTreeNode {
    private String contractSysId;
    private String typeOfNode;
    private int baseId;

    private static final String BENEFIT_COMP_HEAD = "Benefit-Components-Head";
    private static final String PRODUCT_HEAD_MIGRATE_NOTES = "Benefit-Components-Notes-Head";
    
    private String dateSegmentId;

    /**
     * Constructor
     */
    public ProductTreeNodeBase() {
        super();
    }
    public ProductTreeNodeBase(TreeModel treeModel, BaseTreeNode parent,
            String type, String identifier, String name, boolean leaf,
            String contractSysId,String typeOfNode) {
        super(treeModel, parent, type, identifier, name, leaf);
        this.contractSysId = contractSysId;
        this.typeOfNode = typeOfNode;
    }

    
    public ProductTreeNodeBase(TreeModel treeModel, BaseTreeNode parent,
            String type, String identifier, String name, boolean leaf,
            String contractSysId,String typeOfNode,int baseId, String migratedDateSegmentId) {
        super(treeModel, parent, type, identifier, name, leaf);
        this.contractSysId = contractSysId;
        this.typeOfNode = typeOfNode;
        this.baseId = baseId;
        this.dateSegmentId = migratedDateSegmentId;
    }

    /**
     * Constructor
     * 
     * @param TreeModel,parent,type,identifier,name,leaf
     */
    public ProductTreeNodeBase(TreeModel treeModel, BaseTreeNode parent,
            String type, String identifier, String name, boolean leaf,
            String contractSysId) {
        super(treeModel, parent, type, identifier, name, leaf);
        this.contractSysId = contractSysId;
    }


    /**
     * Method is used to load the Benefit Definitions
     * 
     * @return List
     */
    public List loadChildren() {
        children = new ArrayList();

        String productId = this.getIdentifier();
        List benefitComponents = new ArrayList();
        String name = this.typeOfNode;
        int baseId = this.getBaseId(); 
        
        ContractTreeBuilder contractTreeBuilder = new ContractTreeBuilder();

        //an instance of benefitComponent BO is created
        ProductTreeBenefitComponents benefitComponentDetails = new ProductTreeBenefitComponents();

        //checks if the identifier i.e(productId) is null
        if (null == productId || "0".equals(productId))
            return null;

        benefitComponentDetails.setProductId(Integer.parseInt(productId));

        try {
        	if(baseId ==0){
	            //fetches the benefit component details
	            benefitComponents = contractTreeBuilder
	                    .getBenefitComponentsForMigration(benefitComponentDetails);
        	}
        	else{
        		benefitComponents = contractTreeBuilder
                	.getBenefitComponentsForMigration(benefitComponentDetails,baseId);
        	}
        } catch (WPDException e) {
            Logger
                    .logInfo("ProductTreeNodeBase- Exception in Getting benefit components");
        }

        //the benefitComponent List is iterated to get the benefit Components
        for (int i = 0; i < benefitComponents.size(); i++) {
            benefitComponentDetails = (ProductTreeBenefitComponents) benefitComponents
                    .get(i);
            //benefit Components added as the children of Product
            if(!name.equals("Benefit-Components-Notes-Head")){
	            children.add(new BenefitTreeNodeBase(getModel(), this,
	                    BENEFIT_COMP_HEAD, Integer.toString(benefitComponentDetails
	                            .getBenefitComponentId()), benefitComponentDetails
	                            .getBenefitComponentDesc(), false,
	                    this.contractSysId,baseId, dateSegmentId));
            }
            else{
            	children.add(new BenefitTreeNodeBase(getModel(), this,
            			PRODUCT_HEAD_MIGRATE_NOTES, Integer.toString(benefitComponentDetails
	                            .getBenefitComponentId()), benefitComponentDetails
	                            .getBenefitComponentDesc(), true,
	                    this.contractSysId,baseId, dateSegmentId));
            }
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
	 * @return Returns the typeOfNode.
	 */
	public String getTypeOfNode() {
		return typeOfNode;
	}
	/**
	 * @param typeOfNode The typeOfNode to set.
	 */
	public void setTypeOfNode(String typeOfNode) {
		this.typeOfNode = typeOfNode;
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