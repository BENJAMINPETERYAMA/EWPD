/*
 * ComponentLevelTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.benefitcomponent.tree;

import com.wellpoint.wpd.business.benefitcomponent.builder.BenefitComponentTreeBuilder;
import com.wellpoint.wpd.common.benefitcomponent.tree.bo.TreeAdminLevels;
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
public class ComponentLevelTreeNodeBase extends BaseTreeNode{
	TreeAdminLevels treeDataObject;
	/**
	 * Constructor
	 */	
	 public ComponentLevelTreeNodeBase() {
       super();
	 }

	 /**
	  * Constructor
	  * @param treeModel,parent,type,identifier,name,leaf
	  */
	 public ComponentLevelTreeNodeBase(TreeModel treeModel,
	  		BaseTreeNode parent, String type, String identifier,
             String name, boolean leaf) {
       
	 		super(treeModel, parent, type, identifier, name, leaf);

	  }
	 
	 public  List loadChildren() {
	 children = null;
		List resultSet;
		BenefitComponentTreeBuilder treeBuilder = new BenefitComponentTreeBuilder();
		treeDataObject = new TreeAdminLevels();
		String bnftAdmnIdStr = this.getParent().getIdentifier();
		int benefitAdministrationId = Integer.parseInt(bnftAdmnIdStr);
		String benefit = this.getParent().getIdentifier();
	//	int benefitId = Integer.parseInt(benefit);
		String benefitComponent = this.getParent().getParent().getParent().getIdentifier();
		int benefitComponentId =Integer.parseInt(benefitComponent);
		treeDataObject.setBenefitAdministrationId(benefitAdministrationId);
		treeDataObject.setBenefitComponentId(benefitComponentId);
		treeDataObject.setEntitySysId(benefitComponentId);
		
		int type = (this.getIdentifier().equals("Benefit")) ? 2 : 1;

		treeDataObject.setAdminLevelType(type);

		String adminLevelIdStr;
//		String adminLevelAssnIdStr;
		int size;
		if (null == bnftAdmnIdStr || "".equals(bnftAdmnIdStr)) {
			return children;
		}
		children = new ArrayList();
		try {
			resultSet = treeBuilder.getAdminOptionData(treeDataObject);
			size = resultSet.size();
			for (int adminOptnCount = 0; adminOptnCount < size; adminOptnCount++) {
				treeDataObject = (TreeAdminLevels) resultSet
						.get(adminOptnCount);
				int adminLevelId = treeDataObject.getAdminOptionId();
				adminLevelIdStr = Integer.toString(adminLevelId);
				String adminLevelDesc = treeDataObject.getAdminOptionDesc();
				int adminLevelOptionAssnId = treeDataObject.getAdminOptionAssnId();
				
				children
						.add(new AdminLevelTreeNodeBase(getModel(), this,
								"Admin-Option", adminLevelIdStr,
								adminLevelDesc,true,adminLevelOptionAssnId));
			}
		} catch (ServiceException se) {
			Logger.logError(se);
		}
		return children;
	}
	 

}
