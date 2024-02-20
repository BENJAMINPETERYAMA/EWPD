/*
 * SearchResultTreeBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.search;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeModelBase;
import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.custom.tree2.TreeState;
import org.apache.myfaces.custom.tree2.TreeStateBase;

import com.wellpoint.wpd.common.search.util.SearchConstants;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.search.tree.RootTreeNode;
import com.wellpoint.wpd.web.search.util.SearchUtil;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchTreeBackingBean extends WPDBackingBean {
	
	private TreeState treeState;
	private TreeModelBase treeDataModel;
	
	private String subRootClicked="" ;
	private String pageClicked ="";
	private String objectTypeAndPageNumber ="";
	
	private String scrollTop;
	
	public SearchTreeBackingBean(){
		if(getSession().getAttribute(SearchConstants.SEARCH_TREE_STATE)!= null)
			treeState = (TreeState)getSession().getAttribute(SearchConstants.SEARCH_TREE_STATE);
		else{
			treeState = new TreeStateBase();
	        treeState.setTransient(true);
	        getSession().setAttribute(SearchConstants.SEARCH_TREE_STATE,treeState);
		}	
	}
    public TreeModel getTreeDataModel() {
 	 
   		BaseTreeNode root = new RootTreeNode("root","Search","Search",false); 	 
    	treeDataModel = new TreeModelBase(root);
		root.setModel(treeDataModel);
    	treeDataModel.setTreeState(treeState);
    	
    	String objectype = (String)getSession().getAttribute(SearchConstants.OBJECT_TYPE);
    	String searhSummaryPage = (String)getRequest().getAttribute(SearchConstants.SEARCH_PERFORMED);
    	
    	if(searhSummaryPage != null){
    		String rootPath[] = new String[1];
    		rootPath[0] =  root.getNodePath();
    		treeState.expandPath(rootPath);
    	}
    	if(getRequest().getAttribute(SearchConstants.FROM_SUMMARY_PAGE)!= null ||
           getRequest().getAttribute(SearchConstants.SORT_DONE)!= null){
    		String expandPath[] = new String[3];
    		expandPath[0] = root.getNodePath();
    		BaseTreeNode subRootNode = (BaseTreeNode)root.loadChildren().get(1);
    		expandPath[1] = subRootNode.getNodePath();
    		List objectNodes = subRootNode.loadChildren();
    		String selectNode ="";
    		for(int i = 0;i<objectNodes.size();i++){
    			BaseTreeNode objectNode = (BaseTreeNode)objectNodes.get(i);
    			if(objectNode.getIdentifier().equals(objectype)){
    				expandPath[2] = objectNode.getNodePath();
    				selectNode =((BaseTreeNode)objectNode.loadChildren().get(0)).getNodePath();
    				break;
    			}
    		}
    		treeState.expandPath(expandPath);
    		treeState.setSelected(selectNode);
    	}
        return treeDataModel;
    }
	
	public String processSubRootAction(ActionEvent event){
    	 UIComponent component = (UIComponent) event.getSource();
    	 while (!(component != null && component instanceof HtmlTree)) {
            component = component.getParent();
         }
    	 HtmlTree tree = (HtmlTree) component;
    	 tree.setNodeSelected(event);
    	 TreeNode node = tree.getNode();
    	 String searchType=String.valueOf(getSession().getAttribute("SEARCH_TYPE"));
    	
    	 if("SearchCriteria".equals(node.getIdentifier())){
    	 	if("2".equalsIgnoreCase(searchType))
    	 		subRootClicked = "AdvancedSearchCriteria";
    	 	else
    	 		subRootClicked = "BasicSearchCriteriaTree";	
    	 }	
    	 else{
    	 	subRootClicked= "SearchSummary";
    	 }
    	 return subRootClicked;
    }
	
	public String getSubRootAction(){
		if("BasicSearchCriteriaTree".equals(subRootClicked)){
			setBreadCrumbText("Search >> Basic Search Criteria");			
		}else if("AdvancedSearchCriteria".equals(subRootClicked)){
			setBreadCrumbText("Search >> Advanced Search Criteria");
		}else if("SearchSummary".equals(subRootClicked)){
			setBreadCrumbText("Search >> Search Summary");
		}
		getSession().removeAttribute(SearchConstants.OBJECT_TYPE);
		getSession().removeAttribute(SearchConstants.VIEW_ASSOCIATION);
		SearchUtil util = new SearchUtil();
		util.clearAllAssociations();
		util.clearAllAttachments();
		
		return subRootClicked;
	}
	
	public String processPageAction(ActionEvent event){
   	 	UIComponent component = (UIComponent) event.getSource();
   	 	while (!(component != null && component instanceof HtmlTree)) {
           component = component.getParent();
        }
   	 	HtmlTree tree = (HtmlTree) component;
   	
   	 	tree.setNodeSelected(event);
   	 	BaseTreeNode node = (BaseTreeNode)tree.getNode();
   	 	pageClicked = node.getIdentifier();
   	 	objectTypeAndPageNumber = node.getParent().getIdentifier();
   	 	return pageClicked;
   }
	
	public String getPageAction(){
		String[] array = null;
		if( null != pageClicked){
			array = pageClicked.split("-");
		}
		
		String objectType = array[0];
		String pageNumber = array[1];
		
		getSession().setAttribute(SearchConstants.PAGE_NUMBER, pageNumber);
		getSession().removeAttribute(SearchConstants.VIEW_ASSOCIATION);
		getSession().setAttribute(SearchConstants.OBJECT_TYPE, objectType);
		
		SearchUtil util = new SearchUtil();
		util.clearAllAssociations();
		util.clearAllAttachments();
		util.clearAllDetails();
		setBreadCrumbText("Search >> Search Results >> "+util.getBreadCrumbForObjectType(objectType));	
		return objectType;	
	}
	/**
	 * @return Returns the treeState.
	 */
	public TreeState getTreeState() {
		return treeState;
	}
	/**
	 * @param treeState The treeState to set.
	 */
	public void setTreeState(TreeState treeState) {
		this.treeState = treeState;
	}
	/**
	 * @return Returns the scrollTop.
	 */
	public String getScrollTop() {
		return scrollTop;
	}
	/**
	 * @param scrollTop The scrollTop to set.
	 */
	public void setScrollTop(String scrollTop) {
		this.scrollTop = scrollTop;
	}
}
