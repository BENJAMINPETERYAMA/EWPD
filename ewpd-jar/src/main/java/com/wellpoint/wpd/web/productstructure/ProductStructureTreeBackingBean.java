/*
 * ProductStructureTreeBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the express 
 * written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.productstructure;

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
import com.wellpoint.wpd.web.productstructure.tree.ProdStructureAdminOptionsTreeNodeBase;
import com.wellpoint.wpd.web.productstructure.tree.ProdStructureTreeNodeBase;
import com.wellpoint.wpd.web.tree.BaseTreeNode;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing bean for product structure tree.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductStructureTreeBackingBean extends
        ProductStructureBackingBean {

    /**
     * Root of the product structure tree.
     */
    private ProdStructureTreeNodeBase root;

    /**
     * Holder for treestate.
     */
    private TreeState treeState;

    /**
     * Model class for the tree.
     */
    private TreeModelBase treeDataModel;
    
    private String scrollTop;


    /**
     * Sets the tree state.
     *  
     */
    public ProductStructureTreeBackingBean() {

        if (getSession().getAttribute("PRODUCT_STRUCTURE_SESSION_TREE_STATE") != null) {
            treeState = (TreeState) getSession().getAttribute(
                    "PRODUCT_STRUCTURE_SESSION_TREE_STATE");
        } else {
        	//Code change for product structure tree rendering optimization
            updateTreeStructure();
            treeState = new TreeStateBase();
            treeState.setTransient(true);
            getSession().setAttribute("PRODUCT_STRUCTURE_SESSION_TREE_STATE",treeState);
        }
        
    }


    /**
     * Method for returnig tree model.
     * 
     * @return TreeModel
     *  
     */
    public TreeModel getTreeDataModel() {
    	// Code change for product structure tree rendering optimization
    	boolean treeReloadRequired = "Y".equals(getRequest().getParameter("reloadTree")) ? true : false;
    	
    	//Function isTreeStructureUpdated will return true if any updates in the page
    	//affects the tree structure like benefit/Component hide & unhide, Attach Product and Add/Remove Admin Option
    	treeDataModel = null;
    	
    	if(super.getSession() != null) {
    		treeDataModel = getProductStructureSessionObject().getProductStructureTree();
    		    		
        	if(!treeReloadRequired && treeDataModel != null && !isTreeStructureUpdated())
            	return treeDataModel;
    	}
    	
        String productStructureId = Integer.toString(getIdFromSession());
        String productStructureName = getNameFromSession();
        if (null != productStructureId) {
        	
        		root = new ProdStructureTreeNodeBase(WebConstants.ROOT,
                    productStructureId, productStructureName, false);
        	
        } else {
        	
        		root = new ProdStructureTreeNodeBase(WebConstants.ROOT,
                    WebConstants.ZERO_STRING, WebConstants.PRODUCT_STRUCTURE,
                    false);
        	
        }
        treeDataModel = new TreeModelBase(root);
        
        //Code change for product structure tree rendering optimization
        setTreeStructureUpdated(false);
        getProductStructureSessionObject().setProductStructureTree(treeDataModel);
        
        root.setModel(treeDataModel);
        treeDataModel.setTreeState(treeState);
        return treeDataModel;

    }


    /**
     * Action while clicking product structure.
     * 
     * @param event
     * @throws AbortProcessingException
     */
    public void prodStructureAction(ActionEvent event)
            throws AbortProcessingException {
    	getRequest().setAttribute("RETAIN_Value", "");  
        Logger.logInfo("Entering the method being called when "
                + "product structure is clicked");
        UIComponent component = (UIComponent) event.getSource();
        while (!(component != null && component instanceof HtmlTree)) {
            component = component.getParent();
        }
        if (component != null) {
            HtmlTree tree = (HtmlTree) component;
            tree.setNodeSelected(event);
        }
    }
    
    /* modification for admin process starts here */
    
    /**
	 * Method to process action for Benefit Hierarchy
	 * @param event
	 */
	public void processActionForBenefitAdministration(ActionEvent event){

		getRequest().setAttribute("RETAIN_Value", "");  
		UIComponent component = (UIComponent) event.getSource();
	     while (!(component != null && component instanceof HtmlTree)) {
	         component = component.getParent();

	      }
	        if (component != null) {
	            HtmlTree tree = (HtmlTree) component;
	            TreeNodeBase node = (BaseTreeNode) tree.getNode();
	            tree.setNodeSelected(event);
	            String type = ((BaseTreeNode) node).getType();
	            String key = ((BaseTreeNode) node).getIdentifier();
	            String benKey = ((BaseTreeNode) node).getParent().getIdentifier();
	            String[] benefitKey = benKey.split("~");
	            String stdBenKey = benefitKey[0];
	            String description =  ((BaseTreeNode) node).getDescription();
	            String benefitComponentId = ((BaseTreeNode) node).getParent().getParent().getIdentifier();
	            String benefitDefinitionId = ((BaseTreeNode) node).getParent().getIdentifier();
	            String benefitCompName = ((BaseTreeNode) node).getParent().getParent().getDescription();
	            getSession().setAttribute("SESSION_BNFT_ADMIN_ID",key);
	            getSession().setAttribute("SESSION_BNFT_ADMIN_DESC",description);
	            getSession().setAttribute("SESSION_NODE_TYPE_COMP",type);
	            getSession().setAttribute("BNFT_CMPNT_KEY",benefitComponentId);
	            getSession().setAttribute("SESSION_BNFT_DEFN_ID",benefitDefinitionId);
	            getSession().setAttribute("STANDARD_BNFT_KEY",stdBenKey);
	            getSession().setAttribute("BENEFIT_COMP_NAME",benefitCompName);
	        }
	}
	 /* modification for admin process ends here */

    /**
     * Action while clicking standard benefit.
     * 
     * @param event
     * @throws AbortProcessingException
     */
    public void standardBenefitAction(ActionEvent event)
            throws AbortProcessingException {

    	getRequest().setAttribute("RETAIN_Value", "");  
        Logger.logInfo("Entering the method being called when"
                + " standard benefit is clicked");
        UIComponent component = (UIComponent) event.getSource();
        while (!(component != null && component instanceof HtmlTree)) {
            component = component.getParent();
        }
        if (component != null) {
            HtmlTree tree = (HtmlTree) component;
            TreeNodeBase node = (BaseTreeNode) tree.getNode();
            tree.setNodeSelected(event);
            String key = ((BaseTreeNode) node).getIdentifier();
            String type = ((BaseTreeNode) node).getType();
            // Get the benefitComponentId and the standardBenefitId
            String[] idArray = key.split("~");
            //To get the name of the benefit Component name
            String benefitCompName = ((BaseTreeNode) node).getParent().getDescription();            
            getSession().setAttribute("BENEFIT_COMP_NAME",benefitCompName);
            // Set the ids in the session
            getSession().setAttribute("STANDARD_BNFT_KEY", idArray[0]);
            getSession().setAttribute("BNFT_CMPNT_KEY", idArray[1]);
            getSession().setAttribute("SESSION_NODE_TYPE_COMP", type);
            if (getActionFromSession() != null
                    && getActionFromSession().equals("VIEW")) {

                getSession().setAttribute("ACTION", "viewStandardBenefits");
            } else {
                getSession().setAttribute("ACTION", "createStandardBenefits");
            }
        }
    }


    /**
     * Action while clicking benefit component.
     * 
     * @param event
     * @throws AbortProcessingException
     */
    public void benefitCmpntAction(ActionEvent event)
            throws AbortProcessingException {
    	getRequest().setAttribute("RETAIN_Value", "");
    	
        Logger.logInfo("Entering the method being called when"
                + " benefit component is clicked");
        UIComponent component = (UIComponent) event.getSource();
        while (!(component != null && component instanceof HtmlTree)) {
            component = component.getParent();
        }
        if (component != null) {
            HtmlTree tree = (HtmlTree) component;
            TreeNodeBase node = (BaseTreeNode) tree.getNode();
            tree.setNodeSelected(event);
            String key = ((BaseTreeNode) node).getIdentifier();
            String description = node.getDescription();
            String benefitComponentName = ((BaseTreeNode) node).getDescription();            
            getSession().setAttribute("BENEFIT_COMP_ID", key);
            getSession().setAttribute("BENEFIT_COMP_NAME",benefitComponentName);
            if (getActionFromSession() != null
                    && getActionFromSession().equals("VIEW")) {

                getSession().setAttribute("ACTION", "viewBenefitComponents");
            } else {
                getSession().setAttribute("ACTION", "createBenefitComponents");
            }
        }
    }


    /**
     * Action while clicking admin options.
     * 
     * @param event
     * @throws AbortProcessingException
     */
    public void benefitAdmntAction(ActionEvent event)
            throws AbortProcessingException {

    	getRequest().setAttribute("RETAIN_Value", "");  
        Logger.logInfo("Entering the method being "
                + "called when benefit administration is clicked");
        UIComponent component = (UIComponent) event.getSource();
        while (!(component != null && component instanceof HtmlTree)) {
            component = component.getParent();
        }
        if (component != null) {
            HtmlTree tree = (HtmlTree) component;
            TreeNodeBase node = (BaseTreeNode) tree.getNode();
            tree.setNodeSelected(event);
            String key = ((BaseTreeNode) node).getIdentifier();
            //for getting benefit administration value
            String bnftAdmnKey = ((BaseTreeNode) node).getParent().getParent().getIdentifier();            
            String type = ((BaseTreeNode) node).getParent().getParent().getType();
            String benKey = ((BaseTreeNode) node).getParent().getParent().getParent().getIdentifier();
            String benefitComponentName = ((BaseTreeNode) node).getParent().getParent().getParent().getParent().getDescription();
            String description = node.getDescription();
            String[] optionAndAdminId = key.split("~");
            String optionId = optionAndAdminId[0];
            String adminId = optionAndAdminId[1];
            String benefitComponentId = optionAndAdminId[2];
            String[] benefitKey = benKey.split("~");
            String stdBenKey = benefitKey[0];
            String adminOptionLevelAssnId = String.valueOf(((ProdStructureAdminOptionsTreeNodeBase)node).getAdminLevelOptionAssnId());
            getSession().setAttribute("OPTION_ID", optionId);
            getSession().setAttribute("ADMIN_ID", adminId);
            getSession().setAttribute("BNFT_CMPNT_KEY", benefitComponentId);
            getSession().setAttribute("ADMIN_OPTION_ASSN_ID",adminOptionLevelAssnId);
            getSession().setAttribute("STANDARD_BNFT_KEY",stdBenKey);
            getSession().setAttribute("BENEFIT_COMP_NAME",benefitComponentName);
            getSession().setAttribute("SESSION_NODE_TYPE_COMP",type);
            getSession().setAttribute("SESSION_BNFT_ADMIN_ID",bnftAdmnKey);
            if (getActionFromSession() != null
                    && getActionFromSession().equals("VIEW")) {

                getSession().setAttribute("ACTION", "viewBenefitAdmn");
            } else {
                getSession().setAttribute("ACTION", "createBenefitAdmn");
            }
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