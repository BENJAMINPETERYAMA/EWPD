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

import org.apache.myfaces.custom.tree2.TreeModel;
import com.wellpoint.wpd.web.tree.BaseTreeNode;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardTreeNodeBase extends
		BaseTreeNode {
	
    public boolean allLinesMapped;
	/**
	 * Constructor
	 */
	public StandardTreeNodeBase() {
		super();
	}

	/**
	 * Constructor
	 * @param TreeModel,parent,type,identifier,name,leaf
	 */
	public StandardTreeNodeBase(TreeModel treeModel,
			BaseTreeNode parent, String type, String identifier,
			String name, boolean leaf,int count) {
		super(treeModel, parent, type, identifier, name, leaf);
		
		allLinesMapped = (count == 0) ? true :false;
	}
	

	/**
	 * Returns the allLinesMapped.
	 * @return boolean allLinesMapped.
	 */
	public boolean isAllLinesMapped() {
		return allLinesMapped;
	}
	/**
	 * Sets the allLinesMapped.
	 * @param allLinesMapped.
	 */

	public void setAllLinesMapped(boolean allLinesMapped) {
		this.allLinesMapped = allLinesMapped;
	}
}