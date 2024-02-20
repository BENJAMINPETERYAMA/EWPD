/*
 * BenefitAdministrationTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.standardbenefit.tree;

import com.wellpoint.wpd.web.tree.BaseTreeNode;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;


/**
 * Backing bean for benefit administration tree node
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitAdministrationTreeNodeBase extends BaseTreeNode{
	
	/**
	 * Constructor
	 */
	public BenefitAdministrationTreeNodeBase() {
        super();
    }

	/**
	 * Method for benefit administration tree node.
	 * 
	 * @param TreeModel,parent,type,identifier,name,leaf
	 */
	public BenefitAdministrationTreeNodeBase(TreeModel treeModel,
	  		BaseTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);

    }
	
	/**
	 * Method is used to load the Benefit Admin Option Headers 
	 * 
	 * @return List
	 */
	 public List loadChildren() {
        children = new ArrayList();
       
        children.add(new BenefitLevelTreeNodeBase(getModel(), this,
                    "Benefit-Level", "Benefit", "Benefit", false));	
       
        children.add(new BenefitLevelTreeNodeBase(getModel(), this,
                "Benefit-Level", "Benefit Level", "Benefit Level", false));	
        return children;
    }

}
