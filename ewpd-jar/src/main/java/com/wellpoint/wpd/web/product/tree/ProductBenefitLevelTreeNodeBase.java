/*
 * ProductBenefitLevelTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product.tree;

import com.wellpoint.wpd.business.product.builder.ProductTreeBuilder;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeAdminOptions;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBenefitLevelTreeNodeBase extends ProductTreeNode{

    
    public ProductBenefitLevelTreeNodeBase(){
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
    public ProductBenefitLevelTreeNodeBase(TreeModel treeModel,
            ProductTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }
    
    
    /**
     * This method fetches the admin options.
     * 
     *
     * @return List
     */
    
    protected List loadChildren()
    {
        Logger.logInfo("entered method loadChildren of ProductBenefitLevelTreeNodeBase");
        children = null;
		List adminOptionsList=new ArrayList();
		
		ProductTreeBuilder productTreeBuilder=new ProductTreeBuilder();
		ProductTreeAdminOptions adminOptionsDetails=new ProductTreeAdminOptions();
		
		//gets the benefit Administration Id
		String benefitAdmnIdStr = this.getParent().getIdentifier();
		String entityId = this.getParent().getParent().getParent().getParent().getIdentifier();
		String benefitComponentId = this.getParent().getParent().getParent().getIdentifier(); 
		//checks if the benefit Administration Id is null 
		if(null==benefitAdmnIdStr||"0".equals(benefitAdmnIdStr))
		    return children;
		//checks if the entity Id is null 
		if(null==entityId||"0".equals(entityId))
		    return children;
		//checks if the benefitComponentId Id is null 
		if(null==benefitComponentId||"0".equals(benefitComponentId))
		    return children;
		int benefitAdministrationId = Integer.parseInt(benefitAdmnIdStr);
	    
		//sets the benefit administration Id to the business object
		adminOptionsDetails.setBenefitAdministrationId(benefitAdministrationId);
		
	    
	    int type = (this.getIdentifier().equals("Benefit")) ? 2 : 1;
		adminOptionsDetails.setAdminLevelType(type);
		adminOptionsDetails.setEntityId(Integer.parseInt(entityId));
		adminOptionsDetails.setBenefitComponentId(Integer.parseInt(benefitComponentId));
		adminOptionsDetails.setEntityType(BusinessConstants.ENTITY_TYPE_PRODUCT);
		//adminOptionsDetails.setEntityType(BusinessConstants.ENTITY_TYPE_PRODUCT);

		//fetches the admin option list
		try {
			adminOptionsList = productTreeBuilder.getAdminOptions(adminOptionsDetails);
		}
		catch(WPDException e)
		{
			Logger.logError(e);
		}
			

        children = new ArrayList();
		//iterates the admin option list to get the admin options 
		for (int i = 0;i<adminOptionsList.size();i++) {
		    adminOptionsDetails = (ProductTreeAdminOptions) adminOptionsList
						.get(i);
				int adminLvlId = adminOptionsDetails.getAdminOptionId();
				String adminLevelIdStr = Integer.toString(adminLvlId);
				String adminOptionDesc=adminOptionsDetails.getAdminOptionDesc();
				int adminLevelOptionAssnId=adminOptionsDetails.getAdminLevelOptionAssnId();
				
				//adds the individual admin options to the Admin-Option Node
				children.add(new ProductAdminOptionsTreeNodeBase(getModel(),this,"Admin-Option", adminLevelIdStr,adminOptionDesc, true,adminLevelOptionAssnId));
		
		}
		
	
		
		
		return children;
        
    }
    
    
}
