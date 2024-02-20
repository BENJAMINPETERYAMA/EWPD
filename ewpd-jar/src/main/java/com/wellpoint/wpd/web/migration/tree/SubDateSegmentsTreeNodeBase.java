/*
 * BenefitDefinitionHeadTreeNodeBase.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.migration.tree;

import org.apache.myfaces.custom.tree2.TreeModel;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SubDateSegmentsTreeNodeBase extends BaseTreeNode {

    /**
     * Constructor
     */
    public SubDateSegmentsTreeNodeBase() {
        super();
    }


    /**
     * Constructor
     * 
     * @param TreeModel,parent,type,identifier,name,leaf
     */
    public SubDateSegmentsTreeNodeBase(TreeModel treeModel,
            BaseTreeNode parent, String type, String identifier, String name,
            boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);

    }


    /**
     * Constructor
     * 
     * @param TreeModel,parent,type,identifier,name,leaf
     */
    public SubDateSegmentsTreeNodeBase(TreeModel treeModel,
            BaseTreeNode parent, String type, String identifier, String name,
            boolean leaf, String effDate, String exDate) {
        super(treeModel, parent, type, identifier, name, leaf);

    }

}