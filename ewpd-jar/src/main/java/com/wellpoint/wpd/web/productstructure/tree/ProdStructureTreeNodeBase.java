/*
 * ProdStructureTreeNodeBase.java
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
import com.wellpoint.wpd.common.productstructure.tree.bo.ProdStructureTreeBenefitCmpnts;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProdStructureTreeNodeBase extends BaseTreeNode {

    public ProdStructureTreeNodeBase() {
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
    public ProdStructureTreeNodeBase(TreeModel treeModel, BaseTreeNode parent,
            String type, String identifier, String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }


    /**
     * Constructor
     * 
     * @param type
     * @param identifier
     * @param name
     * @param leaf
     */
    public ProdStructureTreeNodeBase(String type, String identifier,
            String name, boolean leaf) {
        super(type, identifier, name, leaf);

    }


    /**
     * Loads the benefit Components for the productstructure when the product
     * structure is clicked. return benefitComponentList
     */
    public List loadChildren() {
        children = new ArrayList(0);
        ProductStructureBusinessObjectBuilder productStructureBusinessObjectBuilder = new ProductStructureBusinessObjectBuilder();
        List benefitComponentList = new ArrayList(0);
        leafNodeId = this.getIdentifier();
        ProdStructureTreeBenefitCmpnts prodStructureTreeBenefitCmpnts = new ProdStructureTreeBenefitCmpnts();
        prodStructureTreeBenefitCmpnts.setProductStructureId((Integer
                .parseInt(leafNodeId)));
        prodStructureTreeBenefitCmpnts.setBenefitCmpntDesc(null);

        try {
            benefitComponentList = productStructureBusinessObjectBuilder
                    .getBenefitComponents(prodStructureTreeBenefitCmpnts);
        } catch (WPDException e) {
            //TODO Auto-generated catch block
        	Logger.logError(e);
        }
        if (benefitComponentList.size() == 0) {
            return null;
        } else {
            Iterator benfitCmpntIter = benefitComponentList.iterator();
            children = new ArrayList(benefitComponentList == null ? 0: benefitComponentList.size());
            while (benfitCmpntIter.hasNext()) {
                prodStructureTreeBenefitCmpnts = (ProdStructureTreeBenefitCmpnts) benfitCmpntIter
                        .next();
                leafNodeId = new Integer(prodStructureTreeBenefitCmpnts
                        .getBenefitCmpntId()).toString();
                children.add(new ProdStructureBenefitCmpntTreeNodeBase(
                        getModel(), this, "BenefitComponent", Integer
                                .toString(prodStructureTreeBenefitCmpnts
                                        .getBenefitCmpntId()),
                        prodStructureTreeBenefitCmpnts.getBenefitCmpntDesc(),
                        false));
            }

        }

        return children;
    }

}