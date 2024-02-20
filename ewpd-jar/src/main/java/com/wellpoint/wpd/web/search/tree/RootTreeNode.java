/*
 * RootTreeNode.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RootTreeNode extends BaseTreeNode {

    public RootTreeNode() {
        super();
    }

    /**
     * overloading the constructor
     * @param  type
     * @param  identifier
     * @param  name
     * @param  leaf
     */
    public RootTreeNode(String type, String identifier, String name,
            boolean leaf) {
        super(type, identifier, name, leaf);
    }

    /**
     * overloading the constructor
     * @param  treeModel
     * @param  parent
     * @param  type
     * @param  identifier
     * @param  name
     * @param  leaf
     */
    public RootTreeNode(TreeModel treeModel, BaseTreeNode parent, String type,
            String identifier, String name, boolean leaf) {
    	super(treeModel,parent,type,identifier,name,leaf);
    }
	public List loadChildren() {
		children = new ArrayList();
		String id = getIdentifier();
		List childList = getChildren(id);
		for(int i=0;i<childList.size();i++){
			children.add(new SubRootTreeNode(getModel(),this,"sub-root",
				((TreeNodeData)childList.get(i)).getId(),
				((TreeNodeData)childList.get(i)).getDescription(),
				((TreeNodeData)childList.get(i)).getId().equals("SearchCriteria")?true:false));
		}
		return children;
    }
	
	private List getChildren(String id){
		List children = new ArrayList();
		TreeNodeData treeNodeData = new TreeNodeData();
		treeNodeData.setDescription("Search Criteria");
		treeNodeData.setId("SearchCriteria");
		children.add(treeNodeData);
		treeNodeData = new TreeNodeData();
		treeNodeData.setDescription("Search Results");
		treeNodeData.setId("SearchResults");
		children.add(treeNodeData);
		return children;		
	}
}
