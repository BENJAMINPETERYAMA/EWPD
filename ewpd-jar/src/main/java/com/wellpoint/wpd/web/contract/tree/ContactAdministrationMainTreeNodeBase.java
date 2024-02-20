/*
 * ContactAdministrationTreeNodeBase.java
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
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitAdministration;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContactAdministrationMainTreeNodeBase extends BaseTreeNode {


    public ContactAdministrationMainTreeNodeBase(){
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
    public ContactAdministrationMainTreeNodeBase(TreeModel treeModel,
            BaseTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);

    }
    
    
    
    /**
     * Loads the Benefit Administrations
     */
    public List loadChildren()
    {
        Logger.logInfo("entered method loadChildren of ContactAdministrationMainTreeNodeBase");
        
        children = null;
        List benefitAdministrationList = new ArrayList();
        
        //gets the standard benefit id
        String benefitDefinitionId = this.getParent().getIdentifier();
        //gets the benefit component id
        String benefitComponentId = this.getParent().getParent().getIdentifier();
        
        ContractTreeBuilder contractTreeBuilder = new ContractTreeBuilder();
        ProductTreeBenefitAdministration bnftAdminDetails = new ProductTreeBenefitAdministration();
        
        //checks if the benefit Definition id is null
        if(null==benefitDefinitionId || "0".equals(benefitDefinitionId))
            return children;
        
       int bnftDfnId=Integer.parseInt(benefitDefinitionId);
       
       //setting the required details to the Business Object
        bnftAdminDetails.setBenefitDefinitionId(bnftDfnId);
        bnftAdminDetails.setBenefitComponentId(Integer.parseInt(benefitComponentId));
        children = new ArrayList();
        try 
        {
            //fetches the benefit Administration List
            benefitAdministrationList = contractTreeBuilder.getBenefitAdminstrations(bnftAdminDetails);
        }
        catch(WPDException e)
        {
        	Logger.logError(e);
        }
        
        //iterates the benefitAdministration List to get the individual benefit administrations
        for(int i = 0; i < benefitAdministrationList.size(); i++)
        {
            bnftAdminDetails=(ProductTreeBenefitAdministration)benefitAdministrationList.get(i);
            String benefitAdminId = Integer.toString(bnftAdminDetails.getBenefitAdministrationId());
            String effectiveDate = bnftAdminDetails.getEffectiveDate();
            String expiryDate = bnftAdminDetails.getExpiryDate();
            String bnftAdminName = effectiveDate + "-" + expiryDate;
            //adds the individual benefit administrations to the Benefit-Administration node
            children.add(new ContractBenefitAdministrationMainTreeNodeBase(getModel(),this,"Benefit-Administration",benefitAdminId,bnftAdminName,false));
        }
		return children;
    }
}
