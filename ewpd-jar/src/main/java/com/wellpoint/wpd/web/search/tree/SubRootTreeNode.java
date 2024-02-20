/*
 * FirstTreeNode.java
 * 
 * © 2006 - 2007 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.myfaces.custom.tree2.TreeModel;

import com.wellpoint.wpd.web.search.pagination.MultipageSearchResult;
import com.wellpoint.wpd.web.search.util.SearchUtil;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SubRootTreeNode extends BaseTreeNode {
    public SubRootTreeNode() {
        super();
    }

    /**
     * overloading the constructor
     * @param  type
     * @param  identifier
     * @param  name
     * @param  leaf
     */
    public SubRootTreeNode(String type, String identifier, String name,
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
    public SubRootTreeNode(TreeModel treeModel, BaseTreeNode parent, String type,
            String identifier, String name, boolean leaf) {
    	super(treeModel,parent,type,identifier,name,leaf);
    }
    
	public List loadChildren() {
		children = new ArrayList();
		String id = getIdentifier();
		
		List childList = getChildren(id);
		if( null != childList ){
			for(int i=0;i<childList.size();i++){
				children.add(new ObjectTreeNode(getModel(),this,"object-node",
						    ((TreeNodeData)childList.get(i)).getId(),
						    ((TreeNodeData)childList.get(i)).getDescription(),false));
			}
		}
        return children;
	}
	
	public List getChildren(String id){
		List children = new ArrayList();
		if("SearchResults".equals(id)){
			SearchUtil util = new SearchUtil();
			List keys = util.getSearchResultKeys();
			Map searchResults = util.getSearchResults();
			if(!searchResults.isEmpty()){
				
				Iterator iterator = keys.iterator();
				String key = null;
				TreeNodeData treeNodeData = null;
				while (iterator.hasNext()) {
					key = (String) iterator.next();
					MultipageSearchResult result = (MultipageSearchResult)searchResults.get(key);
					if(null != result){
						treeNodeData = new TreeNodeData();
						treeNodeData.setId(key);
						treeNodeData.setDescription(util.getMenuNameForObjectType(key) +"("+result.getTotalNumberOfResults()+")");
						children.add(treeNodeData);
					}
				}
			}
		}
		return children;
    }
	
	
}
