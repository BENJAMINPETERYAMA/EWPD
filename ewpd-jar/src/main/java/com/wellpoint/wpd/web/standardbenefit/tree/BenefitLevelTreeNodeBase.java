/*
 * BenefitLevelTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.standardbenefit.tree;

import com.wellpoint.wpd.business.standardbenefit.builder.StandardBenefitTreeBuilder;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.standardbenefit.tree.bo.TreeAdminOptions;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLevelTreeNodeBase extends BaseTreeNode {
	private TreeAdminOptions treeDataObject = null;

	/**
	 * Constructor
	 */
	public BenefitLevelTreeNodeBase() {
		super();
	}

	/**
	 * Method for benefit level tree node
	 * 
	 * @param TreeModel,parent,type,identifier,name,leaf
	 */
	public BenefitLevelTreeNodeBase(TreeModel treeModel,
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
		List resultSet;
		StandardBenefitTreeBuilder treeBuilder = new StandardBenefitTreeBuilder();
		treeDataObject = new TreeAdminOptions();
		String bnftAdmnIdStr = this.getParent().getIdentifier();
		int benefitAdministrationId = Integer.parseInt(bnftAdmnIdStr);
		treeDataObject.setBenefitAdministrationId(benefitAdministrationId);
		int type = (this.getIdentifier().equals("Benefit")) ? 2 : 1;

		treeDataObject.setAdminLevelType(type);

		String adminLevelIdStr;
		int size;
		if (null == bnftAdmnIdStr || "".equals(bnftAdmnIdStr)) {
			return null;
		}
		try {
			resultSet = treeBuilder.getAdminOptionData(treeDataObject);
			size = resultSet.size();
			for (int adminOptnCount = 0; adminOptnCount < size; adminOptnCount++) {
				treeDataObject = (TreeAdminOptions) resultSet
						.get(adminOptnCount);
				int adminLevelId = treeDataObject.getAdminOptionId();
				adminLevelIdStr = Integer.toString(adminLevelId);
				String adminDesc = treeDataObject.getAdminOptionDesc();
				int AdminAssnId =treeDataObject.getAdminOptionAssnId();
				children
						.add(new AdminOptionsTreeNodeBase(getModel(), this,
								"Admin-Option", adminLevelIdStr,
								adminDesc, true,AdminAssnId));
			}
		} catch (ServiceException se) {
        	Logger.logError(se);
		}
		return children;
	}

}