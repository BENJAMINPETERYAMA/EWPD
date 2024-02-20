/*
 * BenefitLevelNotesTreeBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.standardbenefit;

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
import com.wellpoint.wpd.web.standardbenefit.tree.BenefitLevelNotesMainTreeNodeBase;
import com.wellpoint.wpd.web.tree.BaseTreeNode;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Backing bean for benefit level notes tree.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLevelNotesTreeBackingBean extends BenefitLevelNotesAttachmentBackingBean{
    
    //instance variables
    private BenefitLevelNotesMainTreeNodeBase root;

    private TreeModelBase treeDataModel;

    private TreeState treeState;
    
    private String scrollTop;
    
    /**
     * Here the tree state is set
     *  
     */
    public BenefitLevelNotesTreeBackingBean() {

        Logger.logInfo("entered constructor of BenefitLevelNotesTreeBackingBean");
        
        
        //sets the tree state of contract to session if tree state is null
        if(getSession().getAttribute("SESSION_TREE_STATE_BENEFIT_LINE_NOTES")!= null)
			treeState = (TreeState)getSession().getAttribute("SESSION_TREE_STATE_BENEFIT_LINE_NOTES");
		else{
			treeState = new TreeStateBase();
	        treeState.setTransient(true);
	        getSession().setAttribute("SESSION_TREE_STATE_BENEFIT_LINE_NOTES",treeState);
		}
        
        
          
        
    }
    
    /**
     * This method returns the treeDataModel.
     * @return TreeModel treeDataModel.
     */
    public TreeModel getTreeDataModel() {
        
        Logger.logInfo("entered method getTreeDataModel");
        //TODO
        //gets the contract Id from session
        String identifier = null;
        String benefitLineId = null;
        String benefitDefinitionId = null;
        if(null != getSession().getAttribute("benefitLineIdForTree"))
            benefitLineId = getSession().getAttribute("benefitLineIdForTree").toString();
        
        if(null != getSession().getAttribute("SESSION_BNFT_DEFN_ID").toString())
        	benefitDefinitionId = getSession().getAttribute("SESSION_BNFT_DEFN_ID").toString().toString();
        
        identifier = benefitLineId + "~"+ benefitDefinitionId;
        
        
        if (null != benefitLineId) {
        	if(root ==null){
        		root = new BenefitLevelNotesMainTreeNodeBase(WebConstants.ROOT, identifier,  
                    "Benefit Line", false);
        	}
        } else {
        	if(root ==null){
        		root = new BenefitLevelNotesMainTreeNodeBase(WebConstants.ROOT,WebConstants.ZERO_STRING, "BenefitLine", false);
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
    public void processActionForBenefitLevel(ActionEvent event)
    throws AbortProcessingException {
        
        Logger.logInfo("entered method processActionForContract");
        
    	UIComponent component = (UIComponent) event.getSource();
    	while (!(component != null && component instanceof HtmlTree)) {
    		component = component.getParent();

    	}
    	if (component != null) {
    		HtmlTree tree = (HtmlTree) component;
    		tree.setNodeSelected(event);
    	}
    }
    
    /**
     * This method processes action for clicking the notes facet in the tree
     */
    public void processActionForNotes(ActionEvent event)
    throws AbortProcessingException {
        
        Logger.logInfo("entered method processActionForProduct");
        
    	UIComponent component = (UIComponent) event.getSource();
    	while (!(component != null && component instanceof HtmlTree)) {
    		component = component.getParent();

    	}
    	if (component != null) {
    		HtmlTree tree = (HtmlTree) component;
    		TreeNodeBase node = (BaseTreeNode) tree.getNode();
    		tree.setNodeSelected(event);
    		String key = ((BaseTreeNode) node).getIdentifier();
    		getSession().setAttribute("noteIdFromTree", key);
    		String desc = ((BaseTreeNode) node).getDescription();
    		getSession().setAttribute("noteNameFromTree", desc);
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
