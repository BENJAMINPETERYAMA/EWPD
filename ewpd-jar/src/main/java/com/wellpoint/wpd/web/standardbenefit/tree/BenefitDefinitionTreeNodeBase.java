/*
 * BenefitDefinitionTreeNodeBase.java
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
import com.wellpoint.wpd.common.standardbenefit.tree.bo.TreeBenefitAdministration;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitDefinitionTreeNodeBase extends BaseTreeNode {
	private TreeBenefitAdministration treeDataObject = null;
	/**
	 * Constructor
	 */
	public BenefitDefinitionTreeNodeBase() {
        super();
    }
	
    /**
     * Method for benefit definition tree node
     * 
     * @param TreeModel,parent,type,identifier,name,leaf
     */	
	 public BenefitDefinitionTreeNodeBase(TreeModel treeModel,
	  		BaseTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }
	 
	/**
	 * Method is used to load the Benefit Administration Header
	 * 
	 * @return List
	 */
	 /*
	public List loadChildren() {
		
        children = new ArrayList();
       
        children.add(new BenefitAdministrationHeadTreeNodeBase(getModel(), this,
                    "Benefit-Administration-Head", "Benefit Administration", "Administration",false));	
       
        return children;
    }
*/
	
      
	 public List loadChildren() {
		children = new ArrayList();
		List resultSet = new ArrayList();
		StandardBenefitTreeBuilder treeBuilder = new StandardBenefitTreeBuilder();
		treeDataObject = new TreeBenefitAdministration();
		String bnftDefnIdStr = this.getIdentifier();
		int benefitDefinitionId = Integer.parseInt(bnftDefnIdStr);
		treeDataObject.setBenefitDefinitionId(benefitDefinitionId);
		int size;
		String benefitAdminName;
		if (null == bnftDefnIdStr || "".equals(bnftDefnIdStr)) {
			return null;
		}
		try {
			resultSet = treeBuilder.getBenefitAdminData(treeDataObject);
			size = resultSet.size();
			for (int benftAdminCount = 0; benftAdminCount < size; benftAdminCount++) {
				treeDataObject = (TreeBenefitAdministration) resultSet
						.get(benftAdminCount);
				int benefitAdministrationId = treeDataObject
						.getBenefitAdministrationId();
				
				String effectiveDate = treeDataObject.getEffectiveDate();
				String expiryDate = treeDataObject.getExpiryDate();
				benefitAdminName ="Administration";// effectiveDate + "-" + expiryDate;
				children.add(new BenefitAdministrationTreeNodeBase(getModel(),
						this, "Benefit-Administration", Integer
								.toString(benefitAdministrationId),
						benefitAdminName, false));

			}

		} catch (ServiceException se) {
        	Logger.logError(se);
		}
		return children;
	}
	 
	 
}
