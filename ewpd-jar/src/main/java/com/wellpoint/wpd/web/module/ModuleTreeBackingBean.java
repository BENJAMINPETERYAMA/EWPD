/*
 * ModuleTreeBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.module;

import javax.faces.component.UIComponent;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeModelBase;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.apache.myfaces.custom.tree2.TreeState;
import org.apache.myfaces.custom.tree2.TreeStateBase;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.web.module.tree.ModuleMainTreeNodeBase;
import com.wellpoint.wpd.web.tree.BaseTreeNode;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ModuleTreeBackingBean extends ModuleBackingBean {

//  instance variables
    private ModuleMainTreeNodeBase root;

    private TreeModelBase treeDataModel;

    private TreeState treeState;
    
    private String scrollTop;
    
    /**
     * Here the tree state is set
     *  
     */
    public ModuleTreeBackingBean() {

        Logger.logInfo("entered constructor of ModuleTreeBackingBean");
        
        
        //sets the tree state of contract to session if tree state is null
        if(getSession().getAttribute("SESSION_TREE_STATE_MODULE")!= null)
			treeState = (TreeState)getSession().getAttribute("SESSION_TREE_STATE_MODULE");
		else{
			treeState = new TreeStateBase();
	        treeState.setTransient(true);
	        getSession().setAttribute("SESSION_TREE_STATE_MODULE",treeState);
		}
        
        
          
        
    }
    
    /**
     * This method returns the treeDataModel.
     * @return TreeModel treeDataModel.
     */
    public TreeModel getTreeDataModel() {
        
        Logger.logInfo("entered method getTreeDataModel");
       
        //gets the module Id and name from session
        String moduleId = null;
        if(null != getSession().getAttribute("SESSION_MOD_SYS_ID"))
            moduleId = getSession().getAttribute("SESSION_MOD_SYS_ID").toString();
        
        String moduleName = null;
        if(null != getSession().getAttribute("SESSION_MOD_NAME"))
            moduleName = getSession().getAttribute("SESSION_MOD_NAME").toString();
           
        if (null != moduleId) {
        	if(root ==null) {
        		root = new ModuleMainTreeNodeBase(WebConstants.ROOT, moduleId,
                    moduleName, false);
        	}
        } else {
        	if(root ==null) {
        		root = new ModuleMainTreeNodeBase(WebConstants.ROOT,
                    WebConstants.ZERO_STRING, "Module", false);
        	}
        }
        treeDataModel = new TreeModelBase(root);
        root.setModel(treeDataModel);
        treeDataModel.setTreeState(treeState);
        return treeDataModel;

    }
    
    /**
     *  This method processes action for clicking the root node in the tree.
     */
    public void processActionForModuleMainPage(ActionEvent event)
    throws AbortProcessingException {
        
        Logger.logInfo("entered method processActionForModuleMainPage");
        getRequest().setAttribute("RETAIN_Value", "");
    	UIComponent component = (UIComponent) event.getSource();
    	while (!(null!= component&& component instanceof HtmlTree)) {
    		component = component.getParent();

    	}
    	if (null!= component) {
    		HtmlTree tree = (HtmlTree) component;
    		TreeNodeBase node = (BaseTreeNode) tree.getNode();
    		tree.setNodeSelected(event);
    		String key = ((BaseTreeNode) node).getIdentifier();
            getSession().setAttribute("SESSION_MOD_SYS_ID",key);
    	}
    }
    
    /**
     * This method processes action for clicking the task facet in the tree
     */
    public void processActionForTask(ActionEvent event)
    throws AbortProcessingException {
        
        Logger.logInfo("entered method processActionForTask");
        getRequest().setAttribute("RETAIN_Value", "");
    	UIComponent component = (UIComponent) event.getSource();
    	while (!(null!= component && component instanceof HtmlTree)) {
    		component = component.getParent();

    	}
    	if (null!= component) {
    		HtmlTree tree = (HtmlTree) component;
    		TreeNodeBase node = (BaseTreeNode) tree.getNode();
    		tree.setNodeSelected(event);
    		String key = ((BaseTreeNode) node).getIdentifier();
    		getSession().setAttribute("taskIdFromTree", key);
    	}
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
