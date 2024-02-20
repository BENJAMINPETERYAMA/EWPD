/*
 * BenefitComponenetTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */

package com.wellpoint.wpd.web.benefitcomponent.tree;

import com.wellpoint.wpd.business.benefitcomponent.builder.BenefitComponentTreeBuilder;
import com.wellpoint.wpd.common.benefitcomponent.tree.bo.TreeBenefitComponent;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class BenefitComponentTreeNodeBase extends BaseTreeNode{
	TreeBenefitComponent treeDataObject;
	/**
     * Constructor
     */
	public BenefitComponentTreeNodeBase() {
        super();
    }
	
    /**
     * Constructor
     * @param treeModel,parent,type,identifier,name,leaf
     */	 
	 public BenefitComponentTreeNodeBase(TreeModel treeModel,
	 		BaseTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }
	 
	/**
	 * Constructor
	 * @param type,identifier,name,leaf
	 */
	public BenefitComponentTreeNodeBase(String type, String identifier,
            String name, boolean leaf) {
        super(type, identifier, name, leaf);
    }
	/**
     * Method is used to load the Benefit Definition Header
     * 
     * @return List
     */
	public List loadChildren() {
        children = null;
        List resultSet = new ArrayList();
        BenefitComponentTreeBuilder treeBuilder = new BenefitComponentTreeBuilder();
        treeDataObject = new TreeBenefitComponent();
        String benefitComponentIdStr = this.getIdentifier();
        int benefitComponentId = Integer.parseInt(benefitComponentIdStr);
        treeDataObject.setBenefitComponentId(benefitComponentId);
        int size;
        if (null == benefitComponentIdStr || "".equals(benefitComponentIdStr)) {
			return children;
		} 
        children = new ArrayList();
        try{
        	resultSet = treeBuilder.getBenefitData(treeDataObject);
        	size = resultSet.size();
        	for (int benefitCount = 0; benefitCount < size; benefitCount++) {
        		treeDataObject = (TreeBenefitComponent) resultSet.get(benefitCount);
        		int benefitId = treeDataObject.getBenefitId();
        		String benefitName =treeDataObject.getBenefitName();
        		children.add(new BenefitHierarchyTreeNodeBase(getModel(),
        				this, "Benefit-Hierarchy", Integer.toString(benefitId),
						benefitName, false));
        	}
        }
        catch (ServiceException se) {
			Logger.logError(se);
		}
        return children;
    }

}
