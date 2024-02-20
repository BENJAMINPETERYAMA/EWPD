/*
 * ProdStructureBenefitCmpntTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.productstructure.tree;

import com.wellpoint.wpd.business.productstructure.builder.ProductStructureBusinessObjectBuilder;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.productstructure.tree.bo.ProdStructureTreeStandardBenefits;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProdStructureBenefitCmpntTreeNodeBase extends BaseTreeNode {

    public ProdStructureBenefitCmpntTreeNodeBase() {
        super();
    }


    /**
     * Constructor
     * 
     * @param treeModel
     * @param parent
     * @param type
     * @param identifier
     * @param name
     * @param leaf
     */
    public ProdStructureBenefitCmpntTreeNodeBase(TreeModel treeModel,
            BaseTreeNode parent, String type, String identifier, String name,
            boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }


    /**
     * Loads children while clicking on benefit component. return
     * standardBenefitList
     *  
     */
    public List loadChildren() {
        children = new ArrayList(0);
        List standardBenefitList = new ArrayList(0);
        leafNodeId = this.getIdentifier();
        ProdStructureTreeStandardBenefits treeStandardBenefits = new ProdStructureTreeStandardBenefits();
        ProductStructureBusinessObjectBuilder productStructureBusinessObjectBuilder = new ProductStructureBusinessObjectBuilder();
        treeStandardBenefits.setBenefitCmpntId(Integer.parseInt(leafNodeId));
        String productStId =  this.getParent().getIdentifier();
        treeStandardBenefits.setProductStructure(Integer.parseInt(productStId));
        try {
            standardBenefitList = productStructureBusinessObjectBuilder
                    .getStandardBenefits(treeStandardBenefits);
        } catch (WPDException e) {
            //TODO Auto-generated catch block
        	Logger.logError(e);
        }
        if (standardBenefitList.size() == 0) {
            return null;
        } else {
            Iterator benefitIter = standardBenefitList.iterator();
            children = new ArrayList(standardBenefitList == null ? 0:standardBenefitList.size());
            while (benefitIter.hasNext()) {
                treeStandardBenefits = (ProdStructureTreeStandardBenefits) benefitIter
                        .next();
                String standardBenefitId = new Integer(treeStandardBenefits
                        .getStandardBenefitId()).toString();
                // Get a StringBuffer object to concatenate standardBenefitId
                // and benefitComponentId(leafNodeId)
                StringBuffer standardBenefitAndBenefitComponentIdConcatenated = new StringBuffer(
                        standardBenefitId);

                // Append the benefitComponentId to this object
                // separated from standardBenefitId by the '~'
                standardBenefitAndBenefitComponentIdConcatenated.append("~")
                        .append(leafNodeId);

                children.add(new ProdStructureStandardBenefitTreeNodeBase(
                        getModel(), this, "StandardBenefits",
                        standardBenefitAndBenefitComponentIdConcatenated
                                .toString(), treeStandardBenefits
                                .getStandardBenefitDesc(), false));
            }
        }
        return children;
    }

}