/*
 * StandardBenefitTreeNodeBase.java
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

import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.web.tree.BaseTreeNode;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationTreeNodeBase extends BaseTreeNode {

    private String productName;

    private String contactSysId;
    
    private String option;
    
    private int baseId ;
    
    private String dateSegmentId;


    /**
     * Constructor
     */
    public MigrationTreeNodeBase() {
        super();
    }


    /**
     * Constructor
     * 
     * @param TreeModel,parent,type,identifier,name,leaf
     */
    public MigrationTreeNodeBase(TreeModel treeModel, BaseTreeNode parent,
            String type, String identifier, String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }


    /**
     * Constructor
     * 
     * @param type,identifier,name,leaf
     */
    public MigrationTreeNodeBase(String type, String identifier, String name,
            boolean leaf, String prodName, String contSysId,String option,int baseId, String migratedDateSegmentId) {
        super(type, identifier, name, leaf);
        this.productName = prodName;
        this.contactSysId = contSysId;
        this.option = option;
        this.baseId = baseId;
        this.dateSegmentId = migratedDateSegmentId;
    }


    /**
     * Method is used to load the Benefit Definition Header
     * 
     * @return List
     */
    public List loadChildren() {
        children = new ArrayList();

        String productName = this.getProductName();
        String contractSysId = this.getContactSysId();
        
        String option =  this.getOption();
        
        int baseDateSegmentId = this.getBaseId();
        if (null != option && (option.equals(BusinessConstants.OPT_MIGRATE_LATEST_DS) || option.equals(BusinessConstants.OPT_RENEW_DS))) {
        	 children.add(new DateSegementTreeNodeBase(getModel(), this,
	                "Benefit-Definition-Head-Two", "Standard02",
	                WebConstants.STEP_DATESEGMENT, true));
		}else{
			//0 and 2 the link can be clicked.
	        children.add(new DateSegementTreeNodeBase(getModel(), this,
	                "Benefit-Definition-Head", "Standard02",
	                WebConstants.STEP_DATESEGMENT, true));
		}
        children.add(new DateSegementTreeNodeBase(getModel(), this,
                "Benefit-Definition-Head", contractSysId,
                WebConstants.STEP_GENERAL_INFO, true));
        children.add(new DateSegementTreeNodeBase(getModel(), this,
                "Benefit-Definition-Head", contractSysId,
                WebConstants.STEP_PRICING_INFO, true));
        children.add(new DateSegementTreeNodeBase(getModel(), this,
                "Benefit-Definition-Head", "Standard05",
                WebConstants.STEP_PRODUCTS, true));
        children.add(new DateSegementTreeNodeBase(getModel(), this,
                "Benefit-Definition-Head", contractSysId,
                WebConstants.STEP_DENIAL_EXCL, true));
        children.add(new DateSegementTreeNodeBase(getModel(), this,
                "Benefit-Definition-Head-Three", this.getIdentifier(),
                WebConstants.STEP_PROD_MAPPING, false, productName,
                contractSysId,baseDateSegmentId, dateSegmentId));
        children.add(new DateSegementTreeNodeBase(getModel(), this,
                "Benefit-Definition-Head-Four", this.getIdentifier(),
                WebConstants.STEP_MIGRATE_NOTES, false, productName,
                contractSysId,baseDateSegmentId, dateSegmentId));
        children.add(new DateSegementTreeNodeBase(getModel(), this,
                "Benefit-Definition-Head", contractSysId,
                WebConstants.STEP_REPORT_GEN, true));
        children.add(new DateSegementTreeNodeBase(getModel(), this,
                "Benefit-Definition-Head", "Standard09",
                WebConstants.STEP_CONFIRM_MIG, true));
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
     * @return Returns the contactSysId.
     */
    public String getContactSysId() {
        return contactSysId;
    }


    /**
     * @param contactSysId
     *            The contactSysId to set.
     */
    public void setContactSysId(String contactSysId) {
        this.contactSysId = contactSysId;
    }
	/**
	 * @return Returns the option.
	 */
	public String getOption() {
		return option;
	}
	/**
	 * @param option The option to set.
	 */
	public void setOption(String option) {
		this.option = option;
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