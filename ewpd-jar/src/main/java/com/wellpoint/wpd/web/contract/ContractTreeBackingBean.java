/*
 * ContractTreeBackingBean.java
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
import javax.servlet.http.HttpServletRequest;

import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeModelBase;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.apache.myfaces.custom.tree2.TreeState;
import org.apache.myfaces.custom.tree2.TreeStateBase;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.web.contract.tree.ContractAdminOptionsMainTreeNodeBase;
import com.wellpoint.wpd.web.contract.tree.ContractMainTreeNodeBase;
import com.wellpoint.wpd.web.tree.BaseTreeNode;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractTreeBackingBean extends ContractBackingBean {
    
    //instance variables
    private ContractMainTreeNodeBase root;

    private TreeModelBase treeDataModel;

    private TreeState treeState;
    
    private String scrollTop;
    
    /**
     * Here the tree state is set
     *  
     */
    public ContractTreeBackingBean() {
    	Logger.logInfo("entered constructor of ContractTreeBackingBean");
        
        //sets the tree state of contract to session if tree state is null
        if(getSession().getAttribute("SESSION_TREE_STATE_CONTRACT")!= null)
			treeState = (TreeState)getSession().getAttribute("SESSION_TREE_STATE_CONTRACT");
		else{
			updateTreeStructure();
			treeState = new TreeStateBase();
	        treeState.setTransient(true);
	        getSession().setAttribute("SESSION_TREE_STATE_CONTRACT",treeState);
		}        
    }
    
    /**
     * This method returns the treeDataModel.
     * @return TreeModel treeDataModel.
     */
    public TreeModel getTreeDataModel() {
        
    	
    	boolean treeReloadRequired = "Y".equals(getRequest().getParameter("reloadTree")) ? true : false; 
    	
    	// Function isTreeStructureUpdated will return true if any updates in the page
    	// affects the tree structure like benefit/Component hide & unhide, Attach Product and Add/Remove Admin Option
    	treeDataModel = null;
    	if(getContractSession() != null) {
    		treeDataModel = getContractSession().getContractTree();
    		
        	if(!treeReloadRequired && treeDataModel != null && !isTreeStructureUpdated())
            	return treeDataModel;
    	}

        
    	boolean ajaxRequest = false;
    	HttpServletRequest request = getRequest();
        String aaAjaxRequest = request.getHeader("aa-ajax-request");
                
        if("true".equals(aaAjaxRequest)) {
        	ajaxRequest = true;
        }
        
        Logger.logInfo("entered method getTreeDataModel");
        //TODO
        //gets the contract Id from session
        String contractId = Integer.toString(getContractKeyFromSession());
        String dateSegmentId = Integer.toString(getContractSession().getContractKeyObject().getDateSegmentId());
        //gets the product Name from session
        String contractName = super.getContractNameFromSession();
    
        if (null != contractId) {
        	
        	if((ajaxRequest && root ==null) || !ajaxRequest ){
        		root = new ContractMainTreeNodeBase(WebConstants.ROOT, contractId,
                    contractName,dateSegmentId, false);
        	}       	
        } else {
        	if((ajaxRequest && root ==null) || !ajaxRequest){
        		root = new ContractMainTreeNodeBase(WebConstants.ROOT,
                    WebConstants.ZERO_STRING,"","Contract", false);
        	}
        }
        
        treeDataModel = new TreeModelBase(root);
        root.setModel(treeDataModel);
        treeDataModel.setTreeState(treeState);
        
        setTreeStructureUpdated(false);
        getContractSession().setContractTree(treeDataModel);
        return treeDataModel;

    }
    
    /**
     *  This method processes action for Contract click in the tree.
     */
    public void processActionForContract(ActionEvent event)
    throws AbortProcessingException {
        
        Logger.logInfo("entered method processActionForContract");
        
    	UIComponent component = (UIComponent) event.getSource();
    	while (!(component != null && component instanceof HtmlTree)) {
    		component = component.getParent();

    	}
    	if (component != null) {
    		HtmlTree tree = (HtmlTree) component;
    		tree.setNodeSelected(event);
    		getRequest().setAttribute("RETAIN_Value", "");
    	}
    }
    
    /**
     * This method processes action for product click in the tree
     */
    public void processActionForProduct(ActionEvent event)
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
    		getContractSession().setProductId(Integer.parseInt(key));
    		getRequest().setAttribute("RETAIN_Value", "");

    	}
    }
    
    /**
     * This method processes action for product Administration click in the tree
     */
    public void processActionForProductAdmin(ActionEvent event)
    throws AbortProcessingException {
        
        Logger.logInfo("entered method processActionForProductAdmin");
        
    	UIComponent component = (UIComponent) event.getSource();
    	while (!(component != null && component instanceof HtmlTree)) {
    		component = component.getParent();

    	}
    	if (component != null) {
    		HtmlTree tree = (HtmlTree) component;
    		TreeNodeBase node = (BaseTreeNode) tree.getNode();
    		tree.setNodeSelected(event);
    		String key = ((BaseTreeNode) node).getIdentifier();    		
    		getContractSession().setProductAdmin(Integer.parseInt(key));
    		
    		String keyForBenefitId = ((BaseTreeNode) node).getIdentifier();
		    String benefitComponentId=((BaseTreeNode) node).getParent().getIdentifier();
		    getContractSession().setBenefitId(Integer.parseInt(keyForBenefitId));
		    getContractSession().setBenefitComponentId(Integer.parseInt(benefitComponentId));
    		getRequest().setAttribute("RETAIN_Value", "");
    	}
    }
    
    
    //This method processes action for Benefit Component click in the tree.
    public void processActionForBenefitComponent(ActionEvent event)
            throws AbortProcessingException {
        
        Logger.logInfo("entered method processActionForBenefitComponent");
        
        UIComponent component = (UIComponent) event.getSource();
        while (!(component != null && component instanceof HtmlTree)) {
            component = component.getParent();

        }
        if (component != null) {
            HtmlTree tree = (HtmlTree) component;
            TreeNodeBase node = (BaseTreeNode) tree.getNode();
            tree.setNodeSelected(event);
            String key = ((BaseTreeNode) node).getIdentifier();
            String description = node
                    .getDescription();
            getContractSession().setBenefitComponentId(Integer.parseInt(key));
            getContractSession().setBenefitComponentDesc(description);
            getRequest().setAttribute("RETAIN_Value", "");
        }
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
			    String key = ((BaseTreeNode) node).getIdentifier();
			    String benefitComponentId=((BaseTreeNode) node).getParent().getIdentifier();
			    String benefitComponentdesc=((BaseTreeNode) node).getParent().getDescription();
			    
			    getContractSession().setBenefitId(Integer.parseInt(key));
			    getContractSession().setBenefitComponentId(Integer.parseInt(benefitComponentId));
			    getContractSession().setBenefitComponentDesc(benefitComponentdesc);
			    getRequest().setAttribute("RETAIN_Value", "");
			
			}
	    }
	    
	    
//	  This method processes action for admin click in the tree
	    public void processActionForAdministration(ActionEvent event)
	    throws AbortProcessingException {
	        
	        Logger.logInfo("entered method processActionForAdministration");
	        
	    	UIComponent component = (UIComponent) event.getSource();
	    	while (!(component != null && component instanceof HtmlTree)) {
	    		component = component.getParent();

	    	}
	    	if (component != null) {
	    		HtmlTree tree = (HtmlTree) component;
	    		TreeNodeBase node = (BaseTreeNode) tree.getNode();
	    		tree.setNodeSelected(event);
	    		String key = ((BaseTreeNode) node).getIdentifier();
	    		String benefitAdminId = ((BaseTreeNode)node).getParent().getParent().getIdentifier();
	    		String adminOptionLevelAssnId = String.valueOf(((ContractAdminOptionsMainTreeNodeBase)node).getAdminLevelOptionAssnId());
	    		String adminName = ((BaseTreeNode) node).getDescription();
	    		getSession().setAttribute("SESSION_BNFT_ADMIN_DESC",adminName);
	    		//sets the benefit administration Id to session
	    		getContractSession().setBenefitAdminId(Integer.parseInt(benefitAdminId));
	    		String bnftComponentId = ((BaseTreeNode)node).getParent().getParent().getParent().getParent().getIdentifier();
	    		String stdBnftId = ((BaseTreeNode)node).getParent().getParent().getParent().getIdentifier();
	    		String bcompDesc = ((BaseTreeNode)node).getParent().getParent().getParent().getParent().getDescription();
	    		
	    		//sets the benefit Component Id to session
	    		getContractSession().setBenefitComponentId(Integer.parseInt(bnftComponentId));
	    		getContractSession().setAdminOptionId(Integer.parseInt(key));
	    		getContractSession().setBenefitId(Integer.parseInt(stdBnftId));
	    		getContractSession().setBenefitComponentDesc(bcompDesc);
	    		
	    		getContractSession().setAdminLevelOptionAssnId(Integer.parseInt(adminOptionLevelAssnId));
	    		getRequest().setAttribute("RETAIN_Value", "");
	    		
	    	}
	    }
	    
	    public void processActionForContractAdminOption(ActionEvent event)
	    throws AbortProcessingException {
	        
	        Logger.logInfo("entered method processActionForContract");
	        
	    	UIComponent component = (UIComponent) event.getSource();
	    	while (!(component != null && component instanceof HtmlTree)) {
	    		component = component.getParent();

	    	}
	    	if (component != null) {
	    		HtmlTree tree = (HtmlTree) component;
	    		TreeNodeBase node = (BaseTreeNode) tree.getNode();
	    		tree.setNodeSelected(event);
	    		
	    		String key = ((BaseTreeNode) node).getIdentifier();
	    		getContractSession().setBenefitAdminId(Integer.parseInt(key));
	    		
	    		String description = ((BaseTreeNode) node).getDescription(); 
	    		
	    		String bnftComponentId = ((BaseTreeNode)node).getParent().getParent().getIdentifier();
	    		String stdbnftId = ((BaseTreeNode)node).getParent().getIdentifier();
	    		String BenCompName =  ((BaseTreeNode)node).getParent().getParent().getDescription();
	    		String productId = ((BaseTreeNode)node).getParent().getParent().getParent().getIdentifier();
	    		getSession().setAttribute("SESSION_BNFT_ADMIN_DESC",description);
	    		getSession().setAttribute("SESSION_BNFT_DEFN_ID",stdbnftId);
	    		getSession().setAttribute("BENEFIT_COMP_NAME",BenCompName);
	    		getContractSession().setBenefitComponentId(Integer.parseInt(bnftComponentId));
	    		getContractSession().setProductId(Integer.parseInt(productId));
	    		getContractSession().setBenefitId(Integer.parseInt(stdbnftId));
	    		
	    			    		//getSession().setAttribute(WebConstants.SESSION_BNFT_ADMIN_ID,bnftComponentId);
	    		getRequest().setAttribute("RETAIN_Value", "");
	    		
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
