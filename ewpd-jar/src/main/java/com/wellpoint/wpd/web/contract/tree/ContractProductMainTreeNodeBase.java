/*
 * ContractProductTreeNodeBase.java
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
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitComponents;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractProductMainTreeNodeBase extends BaseTreeNode {
	private String dateSegmentId;
    public ContractProductMainTreeNodeBase() {
        super();
    }

    /**
     * 
     * 
     * @param treeModel
     * @param parent
     * @param type
     * @param identifier
     * @param name
     * @param leaf
     */
    public ContractProductMainTreeNodeBase(TreeModel treeModel,
            BaseTreeNode parent, String type, String identifier,
            String name, boolean leaf, String dateSegmentId) {
        super(treeModel, parent, type, identifier, name, leaf);
        this.dateSegmentId =dateSegmentId;
    }

    /**
     * Loads the benefit Components for the product when the product node is clicked
     * 
     * 
     * @return List
     */
    public List loadChildren(){
        
        Logger.logInfo("entered method loadChildren of ContractProductMainTreeNodeBase");
        
        children = null;
        String productId = this.getIdentifier();
        List benefitComponents = new ArrayList();
        int dateId = Integer.parseInt(this.dateSegmentId);
        
        ContractTreeBuilder contractTreeBuilder=new ContractTreeBuilder();
        
        //an instance of benefitComponent BO is created
        ProductTreeBenefitComponents benefitComponentDetails = new ProductTreeBenefitComponents();
        
        //checks if the identifier i.e(productId) is null
        if(null == productId ||"0".equals(productId) && 0 != dateId)
            return children;
         
        benefitComponentDetails.setProductId(Integer.parseInt(productId));
        benefitComponentDetails.setDateSegmentId(Integer.parseInt(this.dateSegmentId));
        children = new ArrayList();
        try {
            //fetches the benefit component details
            benefitComponents = contractTreeBuilder.getBenefitComponents( benefitComponentDetails);
        } catch (WPDException e) {
            // TODO Auto-generated catch block
        	Logger.logError(e);
        }
        
        //the benefitComponent List is iterated to get the benefit Components      
        for(int i=0;i<benefitComponents.size();i++)
        {
            benefitComponentDetails = (ProductTreeBenefitComponents)benefitComponents.get(i);
            //benefit Components added as the children of Product
            children.add(new ContractBenefitComponentMainTreeNodeBase(getModel(),
                    this, "Benefit-Component",Integer.toString(benefitComponentDetails.getBenefitComponentId()),benefitComponentDetails.getBenefitComponentDesc(), false,dateSegmentId));
        }
    	return children;
    }
}
