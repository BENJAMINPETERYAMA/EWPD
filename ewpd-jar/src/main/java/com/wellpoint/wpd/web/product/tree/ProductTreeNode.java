/*
 * ProductTreeNode.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product.tree;

import com.wellpoint.wpd.business.product.builder.ProductBusinessObjectBuilder;

import java.util.Collections;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeNodeBase;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductTreeNode extends TreeNodeBase {

    private ProductTreeNode parent;

    protected List children;

    protected TreeModel model;

    private boolean leaf;

    protected String productId;

    protected String majorHeadId;

    protected String minorHeadId;
    
    ProductBusinessObjectBuilder productTreeBuilder=new ProductBusinessObjectBuilder();

    public ProductTreeNode() {
        super();
    }
    
    /**
     * 
     * @param type
     * @param identifier
     * @param name
     * @param leaf
     */
    public ProductTreeNode(String type, String identifier, String name,
            boolean leaf) {
        super(type, name, identifier, leaf);
        this.leaf = leaf;
        if (leaf) {
            children = Collections.EMPTY_LIST;
        }
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
    public ProductTreeNode(TreeModel treeModel, ProductTreeNode parent,
            String type, String identifier, String name, boolean leaf) {
        super(type, name, identifier, leaf);
        this.parent = parent;
        this.model = treeModel;
        this.leaf = leaf;
        if (leaf) {
            children = Collections.EMPTY_LIST;
        }
    }
    
    
    /**
     * 
     * @see org.apache.myfaces.custom.tree2.TreeNode#isLeaf()
     * @return
     */
    public boolean isLeaf() {
        return getChildCount() == 0;
    }
    

    
    /**
     * Returns the children
     * @return List children.
     */
    public List getChildren() {
        if (children == null) {
            children = loadChildren();
        }
        return children;
    }
    
    /**
     * Sets the children
     * @param children.
     */
    public void setChildren(List children) {
        this.children = children;
    }
    

    /**
     * Returns the model
     * @return TreeModel model.
     */
    public TreeModel getModel() {
        return model;
    }
    
    /**
     * Sets the model
     * @param model.
     */
    public void setModel(TreeModel model) {
        this.model = model;
    }
    
    /**
     * Returns the parent
     * @return ProductTreeNode parent
     */
    public ProductTreeNode getParent() {
        return parent;
    }
    
    /**
     * Sets the parent
     * @param parent
     */
    public void setParent(ProductTreeNode parent) {
        this.parent = parent;
    }
    
    /**
     * 
     * @see org.apache.myfaces.custom.tree2.TreeNode#getChildCount()
     * @return
     */
    public int getChildCount() {
        if (children == null && !isExpanded())
            return 1;
        if (children == null)
            children = loadChildren();
        if (children == null)
            return 0;
        return children.size();
    }

    /**
     * 
     * @return
     */
    public boolean isExpanded() {
        if (model == null)
            return true;
        return model.getTreeState().isNodeExpanded(getNodePath());
    }

    /**
     * 
     * @return
     */
    public String getNodePath() {
        StringBuffer sb = new StringBuffer(Integer.toString(getIndex()));
        for (ProductTreeNode node = getParent(); node != null; node = node
                .getParent())
            sb.insert(0, TreeModel.SEPARATOR).insert(0, node.getIndex());
        return sb.toString();
    }

    /**
     * 
     * @return
     */
    public int getIndex() {
        return (parent == null) ? 0 : parent.getChildren().indexOf(this);
    }

    /**
     * 
     * @return
     */
    protected List loadChildren() {
    	List dummy = null;
        return dummy;
    }

}
