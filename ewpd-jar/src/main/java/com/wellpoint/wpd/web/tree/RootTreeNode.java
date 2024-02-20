/*
 * RootTreeNode.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RootTreeNode extends BaseTreeNode {
	private int count =0;
    public RootTreeNode() {
        super();
    }

    /**
     * overloading the constructor
     * @param String type
     * @param String identifier
     * @param String name
     * @param boolean leaf
     */
    public RootTreeNode(String type, String identifier, String name,
            boolean leaf) {
        super(type, identifier, name, leaf);
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
    public RootTreeNode(TreeModel treeModel, BaseTreeNode parent, String type,
            String identifier, String name, boolean leaf) {
    	super(treeModel,parent,type,identifier,name,leaf);
    }
	public List loadChildren() {
		children = new ArrayList();
		String id = getIdentifier();
		List childList = getChildren(id);
		children = new ArrayList();
		for(int i=0;i<childList.size();i++){
			children.add(new FirstTreeNode(getModel(),this,"first-level",
					    ((TreeNodeData)childList.get(i)).getId(),
					    ((TreeNodeData)childList.get(i)).getDescription(),false));
		}
		return children;
    }
	
	private List getChildren(String id){
		List children = new ArrayList();
		if("1".equals(id)){
			TreeNodeData treeNodeData = new TreeNodeData();
			treeNodeData.setId("1-1");
			treeNodeData.setDescription("1-first");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("1-2");
			treeNodeData.setDescription("1-Second");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("1-3");
			treeNodeData.setDescription("1-third");
			children.add(treeNodeData);
		}else if("2".equals(id)){
			TreeNodeData treeNodeData = new TreeNodeData();
			treeNodeData.setId("2-1");
			treeNodeData.setDescription("2-first");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("2-2");
			treeNodeData.setDescription("2-Second");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("2-3");
			treeNodeData.setDescription("2-third");
			children.add(treeNodeData);
		}
		return children;		
	}
}
