/*
 * StandardBenefitTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.standardbenefit.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.wpd.web.standardbenefit.StandardBenefitBackingBean;
import com.wellpoint.wpd.web.tree.BaseTreeNode;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitTreeNodeBase extends BaseTreeNode{
	
	/**
     * Constructor
     */
	public StandardBenefitTreeNodeBase() {
        super();
    }
	
    /**
     * Constructor
     * @param TreeModel,parent,type,identifier,name,leaf
     */	 
	 public StandardBenefitTreeNodeBase(TreeModel treeModel,
	 		BaseTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }
	 
	/**
	 * Constructor
	 * @param type,identifier,name,leaf
	 */
	public StandardBenefitTreeNodeBase(String type, String identifier,
            String name, boolean leaf) {
        super(type, identifier, name, leaf);
    }
	
	
	/**
     * Method is used to load the Benefit Definition Header
     * 
     * @return List
     */
	public List loadChildren() {
        children = new ArrayList();
        StandardBenefitBackingBean standardBenefitBackingBean = new StandardBenefitBackingBean();
        if(standardBenefitBackingBean.getBenefitTypeTab().equals("Standard Definition"))
            children.add(new BenefitDefinitionHeadTreeNodeBase(getModel(), this,
                    "Benefit-Definition-Head", "Standard", "Standard Definition", false));	
        else
            children.add(new BenefitDefinitionHeadTreeNodeBase(getModel(), this,
                	"Benefit-Definition-Head", "Standard", "Mandate Definition", false));	
        
        return children;
    }

}
