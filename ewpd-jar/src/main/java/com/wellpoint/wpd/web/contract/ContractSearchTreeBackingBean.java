/*
 * ContractSearchTreeBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.contract;

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
import com.wellpoint.wpd.web.contract.tree.ContractTreeNodeBase;
import com.wellpoint.wpd.web.tree.BaseTreeNode;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractSearchTreeBackingBean extends ContractBackingBean {

    
    //instance variables
    private ContractTreeNodeBase root;

    private TreeModelBase treeDataModel;

    private TreeState treeState;
    
    private String scrollTop;
    
    
    /**
     * Constructor
     */
 	public ContractSearchTreeBackingBean() {
 	
 		if(getSession().getAttribute("SESSION_TREE_STATE_CONTRACT_SEARCH")!= null){
 			treeState = (TreeState)getSession().getAttribute("SESSION_TREE_STATE_CONTRACT_SEARCH");

 		}
 			
 		else{
 			treeState = new TreeStateBase();
 	        treeState.setTransient(true);
 	        getSession().setAttribute("SESSION_TREE_STATE_CONTRACT_SEARCH",treeState);
 		}
 	}
 	
 	
 	
 	/**
     * This method returns the treeDataModel.
     * @return TreeModel treeDataModel.
     */
    public TreeModel getTreeDataModel() {        
        Logger.logInfo("entered method getTreeDataModel");      
        //gets the product Id from session
        String productId=Integer.toString(getContractSearchSession().getProductId());       
      
        //gets the product Name from session
        String productName = getContractSearchSession().getProductName();
    
        if ("0" != productId) {        	 
        	root = new ContractTreeNodeBase(WebConstants.ROOT, productId,productName, false);        	 
        } else {        	 
            root = new ContractTreeNodeBase(WebConstants.ROOT,WebConstants.ZERO_STRING, "Product", false);        	 
        }
        treeDataModel = new TreeModelBase(root);
        root.setModel(treeDataModel);
        treeDataModel.setTreeState(treeState);
        return treeDataModel;
    }  
        
    	//This method processes action for Standard Benefit click in the tree.
	    public void processActionForStandardBenefit(ActionEvent event)
	    throws AbortProcessingException {
	        
	        Logger.logInfo("entered method processActionForStandardBenefit");
	        
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
			    String benefitComponentId=((BaseTreeNode) node).getParent().getIdentifier();
			    getContractSearchSession().setBenefitId(Integer.parseInt(key));
			    getContractSearchSession().setBenefitComponentId(Integer.parseInt(benefitComponentId));
			    getSession().setAttribute("STANDARD_BNFT_KEY", key);
			    getSession().setAttribute("PRODUCT_NODE_TYPE",type);
			
			}
		}   
    
    //This method performs the action when a leaf is clicked
    public String refresh() {
        
        Logger.logInfo("entered method refresh");
        
        String linkClicked=(String)getSession().getAttribute("PRODUCT_NODE_TYPE");
        
		
		//checks if the link clicked was for Standard Benefit	
		if(linkClicked.equals("Standard-Benefit")){
		    //checks for view mode
//		    if(!isViewMode())
		        return "standardBenefit";
//		    else
//		        return "standardBenefitView";    
		}
		
		return "";
		
	}
    
    
    //This method processes action for Product click in the tree.
    public void processActionForProduct(ActionEvent event)
    throws AbortProcessingException {
        
        Logger.logInfo("entered method processActionForProduct");
        
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
