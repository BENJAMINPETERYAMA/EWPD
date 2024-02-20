/*
 * BenefitHierarchyTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.benefitcomponent.tree;

import com.wellpoint.wpd.business.benefitcomponent.builder.BenefitComponentTreeBuilder;
import com.wellpoint.wpd.common.benefitcomponent.tree.bo.TreeBenefitAdministration;
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
public class ComponentAdministrationHeadTreeNodeBase extends BaseTreeNode {
	TreeBenefitAdministration treeDataObject;
	/**
	 * Constructor
	 */	
	 public ComponentAdministrationHeadTreeNodeBase() {
       super();
	 }

	 /**
	  * Constructor
	  * @param treeModel,parent,type,identifier,name,leaf
	  */
	 public ComponentAdministrationHeadTreeNodeBase(TreeModel treeModel,
	  		BaseTreeNode parent, String type, String identifier,
             String name, boolean leaf) {
       
	 		super(treeModel, parent, type, identifier, name, leaf);

	  }
	 public  List loadChildren() {
        children = null;
        List resultSet = new ArrayList();
        BenefitComponentTreeBuilder treeBuilder = new BenefitComponentTreeBuilder();
        treeDataObject = new TreeBenefitAdministration();
        String standardBenefitIdStr = this.getParent().getIdentifier();
        String benCompIdStr = this.getParent().getParent().getIdentifier();
        int benCompId = Integer.parseInt(benCompIdStr);
        int standardBenefitId = Integer.parseInt(standardBenefitIdStr);
        treeDataObject.setStandardBenefitId(standardBenefitId);
        treeDataObject.setBenefitComponentId(benCompId);
        int size;
        if (null == standardBenefitIdStr || "".equals(standardBenefitIdStr)) {
			return children;
		} 
        children = new ArrayList();
        try{
        	resultSet = treeBuilder.getAdminData(treeDataObject);
        	size = resultSet.size();
        	for (int benefitCount = 0; benefitCount < size; benefitCount++) {
        		treeDataObject = (TreeBenefitAdministration) resultSet.get(benefitCount);
        		int AdminId = treeDataObject.getBenefitAdministrationId();
        		String effectiveDate = treeDataObject.getEffectiveDate();
        		String expiryDate = treeDataObject.getExpiryDate();
        		String AdminName = effectiveDate +"-" + expiryDate; 
        		children.add(new ComponentAdministrationTreeNodeBase(getModel(),
        				this, "Benefit-Administration", Integer.toString(AdminId),
        				AdminName, false));
        	}
        }
        catch (ServiceException se) {
			Logger.logError(se);
		}
        return children;
    }
	 

}
