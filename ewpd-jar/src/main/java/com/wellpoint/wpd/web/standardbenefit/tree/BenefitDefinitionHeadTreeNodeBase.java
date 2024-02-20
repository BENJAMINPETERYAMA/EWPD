/*
 * BenefitDefinitionHeadTreeNodeBase.java
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

import com.wellpoint.wpd.business.standardbenefit.builder.StandardBenefitTreeBuilder;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.standardbenefit.tree.bo.TreeBenefitDefinition;
import com.wellpoint.wpd.web.tree.BaseTreeNode;


/**
 * Backing bean for benefit definition tree node
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitDefinitionHeadTreeNodeBase extends
		BaseTreeNode {
	private TreeBenefitDefinition treeDataObject = null;

	/**
	 * Constructor
	 */
	public BenefitDefinitionHeadTreeNodeBase() {
		super();
	}

	/**
	 * Method for benefit definition tree node
	 * 
	 * @param TreeModel,parent,type,identifier,name,leaf
	 */
	public BenefitDefinitionHeadTreeNodeBase(TreeModel treeModel,
			BaseTreeNode parent, String type, String identifier,
			String name, boolean leaf) {
		super(treeModel, parent, type, identifier, name, leaf);

	}

	/**
	 * Method is used to load the Benefit Definitions
	 * 
	 * @return List
	 */
	public List loadChildren() {
		children = new ArrayList();
		List resultSet = new ArrayList();
		StandardBenefitTreeBuilder treeBuilder = new StandardBenefitTreeBuilder();
		treeDataObject = new TreeBenefitDefinition();
		String stdBnftIdStr = this.getParent().getIdentifier();
		int standardBenefitId = Integer.parseInt(stdBnftIdStr);
		treeDataObject.setStandardBenefitId(standardBenefitId);
		treeDataObject.setBenefitDefinitionType(this.getIdentifier());
		int size;
		
		String benefitDefinitionName;
		if (null == stdBnftIdStr || "".equals(stdBnftIdStr)) {
			return null;
		}
		try {
			resultSet = treeBuilder.getStandardDefinitionData(treeDataObject);
			size = resultSet.size();
			for (int benftDefCount = 0; benftDefCount < size; benftDefCount++) {
				treeDataObject = (TreeBenefitDefinition) resultSet
						.get(benftDefCount);
				int benefitDefinitionId = treeDataObject.getBenefitDefinitionId();

				String effectiveDate = treeDataObject.getEffectiveDate();
				String expiryDate = treeDataObject.getExpiryDate();
				benefitDefinitionName = effectiveDate + "-" + expiryDate;
				children.add(new BenefitDefinitionTreeNodeBase(getModel(),
						this, "Benefit-Definition", Integer
								.toString(benefitDefinitionId),
						benefitDefinitionName, false));
			}
		} catch (ServiceException se) {
        	Logger.logError(se);
		}
		return children;
	}

	/**
	 * Returns the treeDataObject
	 * @return TreeBenefitDefinition treeDataObject.
	 */
	public TreeBenefitDefinition getTreeDataObject() {
		return treeDataObject;
	}

	/**
	 * Sets the treeDataObject
	 * @param treeDataObject.
	 */
	public void setTreeDataObject(TreeBenefitDefinition treeDataObject) {
		this.treeDataObject = treeDataObject;
	}
}