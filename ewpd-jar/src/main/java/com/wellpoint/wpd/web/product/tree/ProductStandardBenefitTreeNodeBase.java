/*
 * ProductStandardBenefitTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.wpd.business.product.builder.ProductTreeBuilder;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitAdministration;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStandardBenefitTreeNodeBase extends ProductTreeNode {
    
    public ProductStandardBenefitTreeNodeBase(){
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
    public ProductStandardBenefitTreeNodeBase(TreeModel treeModel,
            ProductTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);

    }
    
    /**
     * Loads the administartion folder when a StandardBenefit node is clicked 
     * 
     * 
     * @return List
     */
    protected List loadChildren()
    {
    	Logger.logInfo("entered method loadChildren of ProductAdministrationTreeNodeBase");
        
        children=null;
        List benefitAdministrationList=new ArrayList();
        
        //gets the standard benefit id
        String benefitDefinitionId=this.getIdentifier();
        //gets the benefit component id
        String benefitComponentId=this.getParent().getIdentifier();
      
        
        ProductTreeBuilder productTreeBuilder=new ProductTreeBuilder();
        ProductTreeBenefitAdministration bnftAdminDetails=new ProductTreeBenefitAdministration();
        
        
        //checks if the benefit Definition id is null
        if(null==benefitDefinitionId || "0".equals(benefitDefinitionId))
            return children;
        
        
       int bnftDfnId=Integer.parseInt(benefitDefinitionId);
        
       
       //setting the required details to the Business Object
        bnftAdminDetails.setBenefitDefinitionId(bnftDfnId);
        bnftAdminDetails.setBenefitComponentId(Integer.parseInt(benefitComponentId));
       
        try 
        {
            //fetches the benefit Administration List
            benefitAdministrationList=productTreeBuilder.getBenefitAdminstrations(bnftAdminDetails);
        }
        catch(WPDException e)
        {
			Logger.logError(e);
        }
        
        children=new ArrayList();
        //iterates the benefitAdministration List to get the individual benefit administrations
        for(int i=0;i<benefitAdministrationList.size();i++)
        {
            bnftAdminDetails=(ProductTreeBenefitAdministration)benefitAdministrationList.get(i);
         
            String benefitAdminId=Integer.toString(bnftAdminDetails.getBenefitAdministrationId());
            String effectiveDate=bnftAdminDetails.getEffectiveDate();
            String expiryDate=bnftAdminDetails.getExpiryDate();
            String bnftAdminName=effectiveDate+"-"+expiryDate;
            
            //adds the individual benefit administrations to the Benefit-Administration node
            children.add(new ProductBenefitAdministrationTreeNodeBase(getModel(),this,"Benefit-Administration",benefitAdminId,"Administration",false));
            
        }
		return children;
    }
}

