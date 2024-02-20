/*
 * ContractBenefitComponentTreeNodeBase.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
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
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeStandardBenefits;
import com.wellpoint.wpd.web.tree.BaseTreeNode;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractBenefitComponentMainTreeNodeBase extends BaseTreeNode{

    
	private String dateSegmentId;
    public ContractBenefitComponentMainTreeNodeBase() {
        super();
    }

    /**
     * @param treeModel
     * @param parent
     * @param type
     * @param identifier
     * @param name
     * @param leaf
     */
    public ContractBenefitComponentMainTreeNodeBase(TreeModel treeModel,
            BaseTreeNode parent, String type, String identifier,
            String name, boolean leaf,String dateSegmentId) {
        super(treeModel, parent, type, identifier, name, leaf);
        this.dateSegmentId = dateSegmentId;
    }

    /**
     * Loads the standard benefits for a Benefit Component when its node is clicked 
     * @return List
     */
    public List loadChildren() {
        
        Logger.logInfo("entered method loadChildren of ContractBenefitComponentMainTreeNodeBase");
        
        children = null;
//        String productId = this.getParent().getIdentifier();
       
        //gets the benefit component id
        String benefitComponentId = this.getIdentifier();
        
        int dateId = Integer.parseInt(this.dateSegmentId);
       
        List standardBenefitList = new ArrayList();
        
        ContractTreeBuilder contractTreeBuilder = new ContractTreeBuilder();
        
        // an instance of Standard Benefit BO is created
        ProductTreeStandardBenefits standardBenefitsDetails = new ProductTreeStandardBenefits();
       
        //checks if the identifier is null
        if(null==benefitComponentId ||"0".equals(benefitComponentId) && 0 != dateId)
            return children;
        
        //sets the benefit component id to the BO
      
        standardBenefitsDetails.setBenefitComponentId(Integer.parseInt(benefitComponentId));
        standardBenefitsDetails.setDateSegmentId(Integer.parseInt(this.dateSegmentId));
        children = new ArrayList();
        try {
            //fteches the standard benefit details
            standardBenefitList = contractTreeBuilder.getStandardBenefits(standardBenefitsDetails);
        } catch (WPDException e) {
        	Logger.logError(e);
        }
        //the standard benefit list is iterated to get the standard benefits
        for(int i=0;i< standardBenefitList.size();i++)
        {
            standardBenefitsDetails = (ProductTreeStandardBenefits)standardBenefitList.get(i);
            String standardBenefitId = Integer.toString(standardBenefitsDetails.getStandardBenefitId());
            String standardBnftName = standardBenefitsDetails.getStandardBenefitDesc();
            
            //standard benefits added as the children of Benefit Component	
            children.add(new ContractStandardBenefitMainTreeNodeBase(getModel(),
                    this, "Standard-Benefit",standardBenefitId,standardBnftName, false));
            
            
        }
        return children;
     }

}
