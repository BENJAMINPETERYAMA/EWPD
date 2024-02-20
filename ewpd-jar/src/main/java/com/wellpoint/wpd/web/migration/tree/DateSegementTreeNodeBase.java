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

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.web.tree.BaseTreeNode;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DateSegementTreeNodeBase extends BaseTreeNode {
    private String productName;

    private String contractSysId;

    private int baseId ;

    private static final String PRODUCT_HEAD = "Product-Head";
    private static final String PRODUCT_HEAD_MIGRATE_NOTES = "Benefit-Components-Notes-Head";
    
    private String dateSegmentId;


    /**
     * Constructor
     */
    public DateSegementTreeNodeBase() {
        super();
    }


    /**
     * Constructor
     * 
     * @param TreeModel,parent,type,identifier,name,leaf
     */
    public DateSegementTreeNodeBase(TreeModel treeModel, BaseTreeNode parent,
            String type, String identifier, String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);

    }


    /**
     * Constructor
     * 
     * @param TreeModel,parent,type,identifier,name,leaf
     */
    public DateSegementTreeNodeBase(TreeModel treeModel, BaseTreeNode parent,
            String type, String identifier, String name, boolean leaf,
            String prodName, String contractSysId) {
        super(treeModel, parent, type, identifier, name, leaf);
        this.productName = prodName;
        this.contractSysId = contractSysId;        
    }
    
    
    /**
     * Constructor
     * 
     * @param TreeModel,parent,type,identifier,name,leaf
     */
    public DateSegementTreeNodeBase(TreeModel treeModel, BaseTreeNode parent,
            String type, String identifier, String name, boolean leaf,
            String prodName, String contractSysId,int baseId, String migratedDateSegmentId) {
        super(treeModel, parent, type, identifier, name, leaf);
        this.productName = prodName;
        this.contractSysId = contractSysId;
        this.baseId = baseId;
        this.dateSegmentId = migratedDateSegmentId;
    }


    /**
     * Method is used to load the Benefit Definitions
     * 
     * @return List
     */
    public List loadChildren() {
		children = new ArrayList();
		//geting prod id from session

		String prodId = this.getIdentifier();
		if (!StringUtil.isEmpty(prodId)) {
			children = new ArrayList();
			//geting prod id from session
			String name = this.getDescription();

			int baseId = this.getBaseId();
			if (name.equals(WebConstants.STEP_PROD_MAPPING)) {
				children
						.add(new ProductTreeNodeBase(getModel(), this,
								PRODUCT_HEAD, prodId, this.getProductName(),
								false, this.contractSysId, PRODUCT_HEAD,
								baseId, dateSegmentId));
			}

			if (name.equals(WebConstants.STEP_MIGRATE_NOTES)) {
				children.add(new ProductTreeNodeBase(getModel(), this,
						PRODUCT_HEAD, prodId, this.getProductName(), false,
						this.contractSysId, PRODUCT_HEAD_MIGRATE_NOTES, baseId,
						dateSegmentId));
			}
		}

		return children;
	}


    /**
	 * @return Returns the productName.
	 */
    public String getProductName() {
        return productName;
    }


    /**
	 * @param productName
	 *            The productName to set.
	 */
    public void setProductName(String productName) {
        this.productName = productName;
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