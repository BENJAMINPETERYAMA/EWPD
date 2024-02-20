/*
 * ProdStructureBenefitDateTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.productstructure.tree;

import com.wellpoint.wpd.web.tree.BaseTreeNode;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProdStructureBenefitDateTreeNodeBase extends BaseTreeNode {

    public ProdStructureBenefitDateTreeNodeBase() {
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
    public ProdStructureBenefitDateTreeNodeBase(TreeModel treeModel,
            BaseTreeNode parent, String type, String identifier, String name,
            boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }


    /**
     * Loads Benefit Level
     *  
     */
    public List loadChildren() {
        children = new ArrayList(2);
        children.add(new ProdStructureBenefitLevelTreeNodeBase(getModel(),
                this, "BenefitLevel", "Benefit", "Benefit", false));
        children.add(new ProdStructureBenefitLevelTreeNodeBase(getModel(),
                this, "BenefitLevel", "Benefit Level", "Benefit Level", false));
        return children;
    }

}