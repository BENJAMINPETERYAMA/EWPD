/*
 * ContractBenefitLevelTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.wpd.business.contract.builder.ContractTreeBuilder;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeAdminOptions;
import com.wellpoint.wpd.web.tree.BaseTreeNode;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractBenefitLevelMainTreeNodeBase extends BaseTreeNode{

    
    public ContractBenefitLevelMainTreeNodeBase(){
        super();
    }
    
    
    /**
     * 
     * @param treeModel
     * @param parent
     * @param type
     * @param identifier
     * @param name
     * @param leaf
     */
    public ContractBenefitLevelMainTreeNodeBase(TreeModel treeModel,
            BaseTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }
    
    
    /**
     * This method fetches the admin options.
     * @return List
     */
    
    public List loadChildren()
    {
        Logger.logInfo("entered method loadChildren of ContractBenefitLevelMainTreeNodeBase");
        
        children = null;
		List adminOptionsList = new ArrayList();
		
		ContractTreeBuilder contractTreeBuilder = new ContractTreeBuilder();
		ProductTreeAdminOptions adminOptionsDetails=new ProductTreeAdminOptions();
		
		//gets the benefit Administration Id
		String benefitAdmnIdStr = this.getParent().getIdentifier();
		 String entityId = this.getParent().getParent().getParent().getParent().getIdentifier();
	
		String benefitComponentId = this.getParent().getParent().getParent().getIdentifier(); 
		//checks if the benefit Administration Id is null 
		if(null == benefitAdmnIdStr||"0".equals(benefitAdmnIdStr))
		    return children;
//		checks if the entity Id is null 
		if(null==entityId||"0".equals(entityId))
		    return children;
		//checks if the benefitComponentId Id is null 
		if(null==benefitComponentId||"0".equals(benefitComponentId))
		    return children;
		int benefitAdministrationId = Integer.parseInt(benefitAdmnIdStr);
	    
		//sets the benefit administration Id to the business object
		adminOptionsDetails.setBenefitAdministrationId(benefitAdministrationId);
		adminOptionsDetails.setEntityId(Integer.parseInt(entityId));
		adminOptionsDetails.setBenefitComponentId(Integer.parseInt(benefitComponentId));
		adminOptionsDetails.setEntityType(BusinessConstants.ENTITY_TYPE_PRODUCT);
		
	    int type = (this.getIdentifier().equals("Benefit")) ? 2 : 1;
		adminOptionsDetails.setAdminLevelType(type);
		children = new ArrayList();
		//fetches the admin option list
		try {
			adminOptionsList = contractTreeBuilder.getAdminOptions(adminOptionsDetails);
		}
		catch(WPDException e)
		{
        	Logger.logError(e);
		}
		
		//iterates the admin option list to get the admin options 
		for (int i = 0;i<adminOptionsList.size();i++) {
		    adminOptionsDetails = (ProductTreeAdminOptions) adminOptionsList
						.get(i);
			int adminLvlId = adminOptionsDetails.getAdminOptionId();
			String adminLevelIdStr = Integer.toString(adminLvlId);
			String adminOptionDesc=adminOptionsDetails.getAdminOptionDesc();
			int adminLevelOptionAssnId=adminOptionsDetails.getAdminLevelOptionAssnId();
			
			//adds the individual admin options to the Admin-Option Node
			children.add(new ContractAdminOptionsMainTreeNodeBase(getModel(),this,"Admin-Option", adminLevelIdStr,adminOptionDesc, true,adminLevelOptionAssnId));
		}
		return children;
    }
}
