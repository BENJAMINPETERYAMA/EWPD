/*
 * RoleTreeBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.role;

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
import com.wellpoint.wpd.web.role.tree.RoleMainTreeNodeBase;
import com.wellpoint.wpd.web.tree.BaseTreeNode;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RoleTreeBackingBean extends RoleBackingBean {

    //instance variables
    private RoleMainTreeNodeBase root;

    private TreeModelBase treeDataModel;

    private TreeState treeState;
    
    private String scrollTop;

    /**
     * Here the tree state is set
     *  
     */
    public RoleTreeBackingBean() {

        Logger.logInfo("entered constructor of RoleTreeBackingBean");

        //sets the tree state of contract to session if tree state is null
        if (getSession().getAttribute("SESSION_TREE_STATE_ROLE") != null)
            treeState = (TreeState) getSession().getAttribute(
                    "SESSION_TREE_STATE_ROLE");
        else {
            treeState = new TreeStateBase();
            treeState.setTransient(true);
            getSession().setAttribute("SESSION_TREE_STATE_ROLE", treeState);
        }

    }

    /**
     * This method returns the treeDataModel.
     * 
     * @return TreeModel treeDataModel.
     */
    public TreeModel getTreeDataModel() {

        Logger.logInfo("entered method getTreeDataModel");

        //gets the role Id and name from session
        String roleId = null, roleName = "";
        if (null != getSession().getAttribute("SESSION_ROLE_SYS_ID")) {
            roleId = getSession().getAttribute("SESSION_ROLE_SYS_ID")
                    .toString();
        }
        if (null != getSession().getAttribute("SESSION_ROLE_NAME")) {
            roleName = (String) getSession().getAttribute("SESSION_ROLE_NAME");
        }
        if (null != roleId && null != roleName) {
        	if(root ==null){
        		root = new RoleMainTreeNodeBase(WebConstants.ROOT, roleId,
                    roleName, false);
        	}
        } else {
        	if(root ==null){
        		root = new RoleMainTreeNodeBase(WebConstants.ROOT,
                    WebConstants.ZERO_STRING, "Role", false);
        	}
        }
        treeDataModel = new TreeModelBase(root);
        root.setModel(treeDataModel);
        treeDataModel.setTreeState(treeState);
        return treeDataModel;

    }

    /**
     * This method processes action for clicking the root node in the tree.
     */
    public void processActionForMainPage(ActionEvent event)
            throws AbortProcessingException {

        Logger.logInfo("entered method processActionForMainPage");
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
            getSession().setAttribute("SESSION_ROLE_SYS_ID", key);
        }
    }

    /**
     * This method processes action for clicking the module facet in the tree.
     */
    public void processActionForModule(ActionEvent event)
            throws AbortProcessingException {

        Logger.logInfo("entered method processActionForModule");
        getRequest().setAttribute("RETAIN_Value", "");
        UIComponent component = (UIComponent) event.getSource();
        while (!(null!=component && component instanceof HtmlTree)) {
            component = component.getParent();

        }
        if (null!= component) {
            HtmlTree tree = (HtmlTree) component;
            TreeNodeBase node = (BaseTreeNode) tree.getNode();
            tree.setNodeSelected(event);
            String key = ((BaseTreeNode) node).getIdentifier();
            getSession().setAttribute("moduleIdFromTree", key);
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
            String parentKey = ((BaseTreeNode) node).getParent()
                    .getIdentifier();
            getSession().setAttribute("moduleIdFromTree", parentKey);

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