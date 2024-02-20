/*
 * FirstTreeNode.java
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
public class FirstTreeNode extends BaseTreeNode {
	int count;
    public FirstTreeNode() {
        super();
    }

    /**
     * overloading the constructor
     * @param String type
     * @param String identifier
     * @param String name
     * @param boolean leaf
     */
    public FirstTreeNode(String type, String identifier, String name,
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
    public FirstTreeNode(TreeModel treeModel, BaseTreeNode parent, String type,
            String identifier, String name, boolean leaf) {
    	super(treeModel,parent,type,identifier,name,leaf);
    }
	public List loadChildren() {
		children = new ArrayList();
		String id = getIdentifier();
		String parentId = getParent().getIdentifier();
		List childList = getChildren(id);
		children = new ArrayList();
		for(int i=0;i<childList.size();i++){
			children.add(new BaseTreeNode(getModel(),this,"leaf-node",
					    ((TreeNodeData)childList.get(i)).getId(),
					    ((TreeNodeData)childList.get(i)).getDescription(),true));
		}
        return children;
	}
	public List getChildren(String id){
		if("1-1".equals(id)){
			TreeNodeData treeNodeData = new TreeNodeData();
			treeNodeData.setId("1-1-1");
			treeNodeData.setDescription("1-1-first");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("1-1-2");
			treeNodeData.setDescription("1-1-Second");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("1-1-3");
			treeNodeData.setDescription("1-1-third");
			children.add(treeNodeData);
		}else if("1-2".equals(id)){
			TreeNodeData treeNodeData = new TreeNodeData();
			treeNodeData.setId("1-2-1");
			treeNodeData.setDescription("1-2-first");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("1-2-2");
			treeNodeData.setDescription("1-2-Second");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("1-2-3");
			treeNodeData.setDescription("1-2-third");
			children.add(treeNodeData);
		}else if("1-3".equals(id)){
			TreeNodeData treeNodeData = new TreeNodeData();
			treeNodeData.setId("1-3-1");
			treeNodeData.setDescription("1-3-first");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("1-3-2");
			treeNodeData.setDescription("1-3-Second");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("1-3-3");
			treeNodeData.setDescription("1-3-third");
			children.add(treeNodeData);
		}
		else if("2-1".equals(id)){
			TreeNodeData treeNodeData = new TreeNodeData();
			treeNodeData.setId("2-1-1");
			treeNodeData.setDescription("2-1-first");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("2-1-2");
			treeNodeData.setDescription("2-1-Second");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("2-1-3");
			treeNodeData.setDescription("2-1-third");
			children.add(treeNodeData);
		}else if("2-2".equals(id)){
			TreeNodeData treeNodeData = new TreeNodeData();
			treeNodeData.setId("2-2-1");
			treeNodeData.setDescription("2-2-first");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("2-2-2");
			treeNodeData.setDescription("2-2-Second");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("2-2-3");
			treeNodeData.setDescription("2-2-third");
			children.add(treeNodeData);
		}else if("2-3".equals(id)){
			TreeNodeData treeNodeData = new TreeNodeData();
			treeNodeData.setId("2-3-1");
			treeNodeData.setDescription("2-3-first");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("2-3-2");
			treeNodeData.setDescription("2-3-Second");
			children.add(treeNodeData);
			treeNodeData = new TreeNodeData();
			treeNodeData.setId("2-3-3");
			treeNodeData.setDescription("2-3-third");
			children.add(treeNodeData);
		}
		return children;
    }
}
