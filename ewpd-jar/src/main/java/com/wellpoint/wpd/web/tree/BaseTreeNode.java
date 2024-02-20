/*
 * BaseTreeNode.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */

package com.wellpoint.wpd.web.tree;

import java.util.Collections;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeNodeBase;


/**
 * class for BaseTreeNode which has extended from TreeNodeBase.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version Id:
 */
public class BaseTreeNode extends TreeNodeBase {

    protected BaseTreeNode parent;

    protected List children;

    protected TreeModel model;

    private boolean leaf;

    protected String leafNodeId;

    /**
     * Constructor
     */
    public BaseTreeNode() {
        super();
    }

    /**
     * overloading the constructor
     * @param String type
     * @param String identifier
     * @param String name
     * @param boolean leaf
     */
    public BaseTreeNode(String type, String identifier, String name,
            boolean leaf) {
        super(type, name, identifier, leaf);
        this.leaf = leaf;
        if (leaf) {
            children = Collections.EMPTY_LIST;
        }
    }

    /**
     * overloading the constructor
     * @param TreeModel treeModel
     * @param BaseTreeNode parent
     * @param String type
     * @param String identifier
     * @param String name
     * @param boolean leaf
     */
    public BaseTreeNode(TreeModel treeModel, BaseTreeNode parent, String type,
            String identifier, String name, boolean leaf) {
        super(type, name, identifier, leaf);
        this.parent = parent;
        this.model = treeModel;
        this.leaf = leaf;
        if (leaf) {
            children = Collections.EMPTY_LIST;
        }
    }

    /**
     * Checks for tree leaf
     * @return boolean
     */
    public boolean isLeaf() {
        return getChildCount() == 0;
    }

    /**
     * Gets the children of a node
     * @return List
     */
    public List getChildren() {
        return children;
    }

    /**
     * Sets the children of a node
     * @param List children
     */
    public void setChildren(List children) {
        this.children = children;
    }

    /**
     * Gets the model
     * @return TreeModel model
     */
    public TreeModel getModel() {
        return model;
    }

    /**
     * Sets the model
     * @param TreeModel model
     */
    public void setModel(TreeModel model) {
        this.model = model;
    }

    /**
     * Gets the parent
     * @return BaseTreeNode parent
     */
    public BaseTreeNode getParent() {
        return parent;
    }

    /**
     * Sets the parent
     * @param BaseTreeNode parent
     */
    public void setParent(BaseTreeNode parent) {
        this.parent = parent;
    }

    /**
     * Gets the child count
     * @return int childCount
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
     * Checks if expanded or not and returns a boolean value
     * @return boolean
     */
    public boolean isExpanded() {
        if (model == null)
            return true;
        return model.getTreeState().isNodeExpanded(getNodePath());
    }

    /**
     * Gets the node path
     * @return String
     */
    public String getNodePath() {
        StringBuffer sb = new StringBuffer(Integer.toString(getIndex()));
        for (BaseTreeNode node = getParent(); node != null; node = node
                .getParent())
            sb.insert(0, TreeModel.SEPARATOR).insert(0, node.getIndex());
        return sb.toString();
    }

    /**
     * Gets the index of the node
     * @return int
     */
    public int getIndex() {
        return (parent == null) ? 0 : parent.getChildren().indexOf(this);
    }

    /**
     * For loading the children
     * @return List
     */
    public List loadChildren() {
        return null;
    }
}

