/*
 * RoleTaskMainTreeNodeBase.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.role.tree;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RoleTaskMainTreeNodeBase extends BaseTreeNode {

    public RoleTaskMainTreeNodeBase() {
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
    public RoleTaskMainTreeNodeBase(TreeModel treeModel, BaseTreeNode parent,
            String type, String identifier, String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }

    /**
     * 
     * @param type
     * @param identifier
     * @param name
     * @param leaf
     */
    public RoleTaskMainTreeNodeBase(String type, String identifier,
            String name, boolean leaf) {
        super(type, identifier, name, leaf);
    }

   
}