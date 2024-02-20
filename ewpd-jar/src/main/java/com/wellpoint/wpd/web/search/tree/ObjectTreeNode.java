/*
 * ObjectTreeNode.java
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
import java.util.Map;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.wpd.web.search.pagination.MultipageSearchResult;
import com.wellpoint.wpd.web.search.pagination.Page;
import com.wellpoint.wpd.web.search.util.SearchUtil;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ObjectTreeNode extends BaseTreeNode {
    public ObjectTreeNode() {
        super();
    }

    /**
     * overloading the constructor
     * @param  type
     * @param  identifier
     * @param  name
     * @param  leaf
     */
    public ObjectTreeNode(String type, String identifier, String name,
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
    public ObjectTreeNode(TreeModel treeModel, BaseTreeNode parent, String type,
            String identifier, String name, boolean leaf) {
    	super(treeModel,parent,type,identifier,name,leaf);
    }
    public List loadChildren() {
		children = new ArrayList();
		String id = getIdentifier();
		
		List childList = getChildren(id);
		for(int i=0;i<childList.size();i++){
			children.add(new BaseTreeNode(getModel(),this,"page-node",
					    ((TreeNodeData)childList.get(i)).getId(),
					    ((TreeNodeData)childList.get(i)).getDescription(),true));
		}
        return children;
	}
	

	
	public List getChildren(String id){
		List children = new ArrayList();
		SearchUtil util = new SearchUtil();
		Map searchResults = util.getSearchResults();
		MultipageSearchResult searchResult = (MultipageSearchResult)searchResults.get(id);
		Page[] pages =  searchResult.getPages();
		TreeNodeData treeNodeData = null;
		for(int i=0;i< pages.length;i++){
			treeNodeData = new TreeNodeData();
			treeNodeData.setId(id+"-"+(i+1));
			treeNodeData.setDescription("Page "+(i+1)+"("+pages[i].getNumberOfRecords()+")");
			children.add(treeNodeData);
		}
		return children;
    }
}
