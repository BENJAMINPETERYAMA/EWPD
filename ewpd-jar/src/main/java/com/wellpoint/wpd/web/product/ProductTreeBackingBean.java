	/*
 * ProductTreeBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.product;

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
import com.wellpoint.wpd.web.product.tree.ProductAdminOptionsTreeNodeBase;
import com.wellpoint.wpd.web.product.tree.ProductTreeNode;
import com.wellpoint.wpd.web.product.tree.ProductTreeNodeBase;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductTreeBackingBean extends ProductBackingBean {

    
    //instance variables
    private ProductTreeNodeBase root;

    private TreeModelBase treeDataModel;

    private TreeState treeState;
    
    private String scrollTop;

    /**
     * Here the tree state is set
     *  
     */
    public ProductTreeBackingBean() {

        Logger.logInfo("entered constructor of ProductTreeBackingBean");

        treeState = new TreeStateBase();
        treeState.setTransient(true);
        getSession().setAttribute("PRODUCT_SESSION_TREE_STATE", treeState);  
        //Code change for product tree rendering optimization
        updateTreeStructure();
    }

    /**
     * This method returns the treeDataModel.
     * @return TreeModel treeDataModel.
     */
    public TreeModel getTreeDataModel() {
    	 //Code change for product tree rendering optimization
    	boolean treeReloadRequired = "Y".equals(getRequest().getParameter("reloadTree")) ? true : false; 
    	
    	// Function isTreeStructureUpdated will return true if any updates in the page
    	// affects the tree structure like benefit/Component hide & unhide, Attach Product and Add/Remove Admin Option
    	treeDataModel = null;
    	if(super.getSession() != null) {
    		treeDataModel = getProductSessionObject().getProductTree();
    		
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
        //gets the product Id from session
        String productId = Integer.toString(getProductKeyFromSession());
        
        //gets the product Name from session
        String productName = super.getProductNameFromSession();
        
        String ptoductType = super.getProductTypeFromSession();
        super.getSession().setAttribute("ptoductType",ptoductType);
    
        if (null != productId) {
        	 
        		root = new ProductTreeNodeBase(WebConstants.ROOT, productId,
                    productName, false);
        	
        } else {
        	
        		root = new ProductTreeNodeBase(WebConstants.ROOT,
                    WebConstants.ZERO_STRING, "Product", false);
        	
        }
        treeDataModel = new TreeModelBase(root);
        root.setModel(treeDataModel);
        treeDataModel.setTreeState(treeState);
        
        //Code change for product tree rendering optimization
        setTreeStructureUpdated(false);
        getProductSessionObject().setProductTree(treeDataModel);
        
        return treeDataModel;

    }

    //This method processes action for Benefit Component click in the tree.
    public void processActionForBenefitComponent(ActionEvent event)
            throws AbortProcessingException {
        
        Logger.logInfo("entered method processActionForBenefitComponent");
        getRequest().setAttribute("RETAIN_Value", "");
        UIComponent component = (UIComponent) event.getSource();
        while (!(component != null && component instanceof HtmlTree)) {
            component = component.getParent();

        }
        if (component != null) {
            HtmlTree tree = (HtmlTree) component;
            TreeNodeBase node = (ProductTreeNode) tree.getNode();
            tree.setNodeSelected(event);
            String type = node.getType();
            String key = node.getIdentifier();
            /*String description = node
                    .getDescription();*/
            String componentName = node.getDescription();
            getSession().setAttribute("BENEFIT_COMP_NAME",componentName);
            getProductSessionObject().setBenefitComponentId(Integer.parseInt(key));
            getSession().setAttribute("BNFT_CMPNT_KEY", key);
            getSession().setAttribute("PRODUCT_NODE_TYPE",type);

        }
    }
    
    public void processActionForAdminOption(ActionEvent event) throws AbortProcessingException {
    	Logger.logInfo("entered method processActionForAdminOption");
    	getRequest().setAttribute("RETAIN_Value", "");
        UIComponent component = (UIComponent) event.getSource();
        while (!(component != null && component instanceof HtmlTree)) {
            component = component.getParent();

        }
        if (component != null) {
            HtmlTree tree = (HtmlTree) component;
            TreeNodeBase node = (ProductTreeNode) tree.getNode();
            tree.setNodeSelected(event);
            String type = node.getType();
            String description = node.getDescription(); 
            if(!("root".equals(((ProductTreeNode) node).getParent().getParent().getType()))){
            String componentName = ((ProductTreeNode) node).getParent().getParent().getDescription();
            getSession().setAttribute("BENEFIT_COMP_NAME",componentName);
            }
            String key = node.getIdentifier();
            if(type.equals("Benefit-Administration")){
                String benefitComponentId = ((ProductTreeNode) node).getParent().getParent().getIdentifier();
                getSession().setAttribute("BNFT_CMPNT_KEY",benefitComponentId);
                getProductSessionObject().setBenefitComponentId(Integer.parseInt(benefitComponentId));

            }
           String stdBenefitId = ((ProductTreeNode) node).getParent().getIdentifier();
           getProductSessionObject().setBenefitId(Integer.parseInt(stdBenefitId));
            getSession().setAttribute("SESSION_BNFT_DEFN_ID",stdBenefitId);
            getSession().setAttribute("SESSION_BNFT_ID",stdBenefitId);
            getSession().setAttribute("ADMIN_KEY", key);
            getSession().setAttribute("PRODUCT_NODE_TYPE",type);
            getSession().setAttribute("SESSION_BNFT_ADMIN_DESC",description);
    		getSession().setAttribute("ADMIN_KEY_FOR_QSTNR", key);
            
        }
    	
    }
    
    
    	//This method processes action for Standard Benefit click in the tree.
	    public void processActionForStandardBenefit(ActionEvent event)
	    throws AbortProcessingException {
	        
	        Logger.logInfo("entered method processActionForStandardBenefit");
	        getRequest().setAttribute("RETAIN_Value", "");
	        UIComponent component = (UIComponent) event.getSource();
	        while (!(component != null && component instanceof HtmlTree)) {
	            component = component.getParent();
		
	        }
			if (component != null) {
			    HtmlTree tree = (HtmlTree) component;
			    TreeNodeBase node = (ProductTreeNode) tree.getNode();
			    tree.setNodeSelected(event);
			    String type = node.getType();
			    String key = node.getIdentifier();
			    String benefitComponentId=((ProductTreeNode) node).getParent().getIdentifier();
			    /*String description = node
			            .getDescription();*/
			    getProductSessionObject().setBenefitId(Integer.parseInt(key));
	    		String benefitComponentName = ((ProductTreeNode) node).getParent().getDescription();
			    getProductSessionObject().setBenefitComponentId(Integer.parseInt(benefitComponentId));
			    getSession().setAttribute("STANDARD_BNFT_KEY", key);
			    getSession().setAttribute("PRODUCT_NODE_TYPE",type);
			    getSession().setAttribute("BENEFIT_COMP_NAME",benefitComponentName);
			
			}
		}
    
    
    
    
    //This method performs the action when a leaf is clicked
    public String refresh() {
        
        Logger.logInfo("entered method refresh");
        
        String linkClicked=(String)getSession().getAttribute("PRODUCT_NODE_TYPE");
        
		
		//checks if the link clicked was for Standard Benefit	
		if(linkClicked.equals("Standard-Benefit")){
		    //checks for view mode
		    if(!isViewMode())
		        return "standardBenefit";
		    else
		        return "standardBenefitView";    
		}
		
		return "";
		
	}
    
    
    //This method processes action for Product click in the tree.
    public void processActionForProduct(ActionEvent event)
    throws AbortProcessingException {
        
        Logger.logInfo("entered method processActionForProduct");
        getRequest().setAttribute("RETAIN_Value", "");
    	UIComponent component = (UIComponent) event.getSource();
    	while (!(component != null && component instanceof HtmlTree)) {
    		component = component.getParent();

    	}
    	if (component != null) {
    		HtmlTree tree = (HtmlTree) component;
    		//TreeNodeBase node = (ProductTreeNode) tree.getNode();
    		tree.setNodeSelected(event);
    	}
    }
    
    
    //This method processes action for admin click in the tree
    public void processActionForAdministration(ActionEvent event)
    throws AbortProcessingException {
        
        Logger.logInfo("entered method processActionForAdministration");
        getRequest().setAttribute("RETAIN_Value", "");
    	UIComponent component = (UIComponent) event.getSource();
    	while (!(component != null && component instanceof HtmlTree)) {
    		component = component.getParent();

    	}
    	if (component != null) {
    		HtmlTree tree = (HtmlTree) component;
    		TreeNodeBase node = (ProductTreeNode) tree.getNode();
    		tree.setNodeSelected(event);
    		String key = node.getIdentifier();
    		//to get the value of date range
    		String parentKey = ((ProductTreeNode) node).getParent().getParent().getIdentifier();
    		String benefitId = ((ProductTreeNode) node).getParent().getParent().getParent().getIdentifier();
    		this.getProductSessionObject().setBenefitId(Integer.parseInt(benefitId));
    		getSession().setAttribute("ADMIN_KEY", parentKey);
    		getSession().setAttribute("ADMIN_KEY_FOR_QSTNR", key);
    		String type = ((ProductTreeNode) node).getParent().getParent().getType();
    		String benefitAdminId = ((ProductTreeNode)node).getParent().getParent().getIdentifier();
    		String adminOptionLevelAssnId = String.valueOf(((ProductAdminOptionsTreeNodeBase)node).getAdminLevelOptionAssnId());
    		String benefitComponentName = ((ProductTreeNode) node).getParent().getParent().getParent().getParent().getDescription();
    		
    		
    		//sets the benefit administration Id to session
    		setBenefitAdminIdToSession(Integer.parseInt(benefitAdminId));
    		String bnftComponentId = ((ProductTreeNode)node).getParent().getParent().getParent().getParent().getIdentifier();
    		getSession().setAttribute("BNFT_CMPNT_KEY",bnftComponentId);
    		//sets the benefit Component Id to session
    		setBenefitComponentIdToSession(Integer.parseInt(bnftComponentId));
    		getProductSessionObject().setAdminOptionId(Integer.parseInt(key));
    		getSession().setAttribute("ADMN_LVL_ASSC_ID",adminOptionLevelAssnId);
    		getProductSessionObject().setAdminLevelOptionAssnId(Integer.parseInt(adminOptionLevelAssnId));
    		getSession().setAttribute("BENEFIT_COMP_NAME",benefitComponentName);
    		getSession().setAttribute("PRODUCT_NODE_TYPE",type);
    		String stdBenefitId = ((ProductTreeNode) node).getParent().getParent().getParent().getIdentifier();
    		 getSession().setAttribute("SESSION_BNFT_DEFN_ID",stdBenefitId);
    		String adminName = ((ProductTreeNode) node).getDescription();
    		getSession().setAttribute("SESSION_BNFT_ADMIN_DESC",adminName);
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
