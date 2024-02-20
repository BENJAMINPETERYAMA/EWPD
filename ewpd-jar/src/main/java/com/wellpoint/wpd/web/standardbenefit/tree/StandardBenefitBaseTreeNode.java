/*
 * StandardBenefitBaseTreeNode.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.standardbenefit.tree;

import java.util.Collections;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeNodeBase;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class StandardBenefitBaseTreeNode  extends TreeNodeBase{
	
	private StandardBenefitBaseTreeNode parent;

    protected List children;

    protected TreeModel model;

    private boolean leaf;
    
    protected int standardBenefitId;
    
    protected int benefitDefinitionId;
    
    protected int benefitAdministrationId;
    
    protected int adminLevelId;
    
    protected int adminLevelAssnId;
    
    protected String effectiveDate;
    
    protected String expiryDate; 

    
    /**
     * Constructor
     */
    public StandardBenefitBaseTreeNode(){
    	super();
    }
    
    /**
     * Constructor
     * @param type,identifier,name,leaf
     */
    public StandardBenefitBaseTreeNode(String type, String identifier, String name,
            boolean leaf){
    	 super(type, name, identifier, leaf);
         this.leaf = leaf;
         if (leaf) {
             children = Collections.EMPTY_LIST;
         }
    }
    /**
     * Method for standard benefit tree node
     * 
     * @param TreeModel,parent,type,identifier,name,leaf
     */
    public StandardBenefitBaseTreeNode(TreeModel treeModel,
    		StandardBenefitBaseTreeNode parent, String type, String identifier,
            String name, boolean leaf) {
        super (type, name, identifier, leaf);
        this.parent = parent;
        this.model = treeModel;
        this.leaf = leaf;
        if (leaf) {
            children = Collections.EMPTY_LIST;
        }
    }
    
    /**
     * Returns whether the node is a leaf.
     * 
     * @return boolean
     */
    public boolean isLeaf() {
        return getChildCount() == 0;
    }

    /**
     * Returns children.
     * 
     * @return List
     */
    public List getChildren() {
        if (children == null) {
            children = loadChildren();
        }
        return children;
    }

    /**
     * Sets children.
     * 
     * @param List
     */
    public void setChildren(List children) {
        this.children = children;
    }

    /**
     * Returns tree model.
     * 
     * @return TreeModel
     */
    public TreeModel getModel() {
        return model;
    }

    /**
     * Sets model.
     * 
     * @param TreeModel
     */
    public void setModel(TreeModel model) {
        this.model = model;
    }

    /**
     * Returns parent.
     * 
     * @return ContractBaseTreeNode
     */
    public StandardBenefitBaseTreeNode getParent() {
        return parent;
    }

    /**
     * Sets parent.
     * 
     * @param parent
     */
    public void setParent(StandardBenefitBaseTreeNode parent) {
        this.parent = parent;
    }

    /**
     * Returns Child Count.
     * 
     * @return int
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
     * @return boolean
     */
    public boolean isExpanded() {
        if (model == null)
            return true;
        return model.getTreeState().isNodeExpanded(getNodePath());
    }

    /**
     * Returns Node Path.
     * 
     * @return String
     */
    public String getNodePath() {
        StringBuffer sb = new StringBuffer(Integer.toString(getIndex()));
        for (StandardBenefitBaseTreeNode node = getParent(); node != null; node = node
                .getParent())
            sb.insert(0, TreeModel.SEPARATOR).insert(0, node.getIndex());
        return sb.toString();
    }

    /**
     * 
     * @return int
     */
    public int getIndex() {
        return (parent == null) ? 0 : parent.getChildren().indexOf(this);
    }

    /**
     * 
     * @return List
     */
    protected List loadChildren() {
        return null;
    }


}
