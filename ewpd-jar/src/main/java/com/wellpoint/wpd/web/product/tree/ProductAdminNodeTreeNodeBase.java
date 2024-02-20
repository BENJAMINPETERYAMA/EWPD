/*
 * Created on Aug 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.product.tree;

import org.apache.myfaces.custom.tree2.TreeModel;


/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductAdminNodeTreeNodeBase extends ProductTreeNode {
	  public ProductAdminNodeTreeNodeBase() {
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
    public ProductAdminNodeTreeNodeBase(TreeModel treeModel,
            ProductTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super(treeModel, parent, type, identifier, name, leaf);
    }

}
