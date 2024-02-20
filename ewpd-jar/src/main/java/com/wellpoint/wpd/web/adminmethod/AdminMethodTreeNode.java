/*
 * Created on Feb 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.adminmethod;

import java.util.Collections;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeNodeBase;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodTreeNode  extends TreeNodeBase {
	
	private AdminMethodTreeNode parent;
	
	protected List children;

	protected TreeModel model;

	private boolean leaf;

	protected String productId;

	protected String majorHeadId;

	protected String minorHeadId;
	
	public AdminMethodTreeNode() {
        super();
    }
    
    /**
     * 
     * @param type
     * @param identifier
     * @param name
     * @param leaf
     */
    public AdminMethodTreeNode(String type, String identifier, String name,
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
    public AdminMethodTreeNode(TreeModel treeModel, AdminMethodTreeNode parent,
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
    public AdminMethodTreeNode getParent() {
        return parent;
    }
    
    /**
     * Sets the parent
     * @param parent
     */
    public void setParent(AdminMethodTreeNode parent) {
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
        for (AdminMethodTreeNode node = getParent(); node != null; node = node
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
