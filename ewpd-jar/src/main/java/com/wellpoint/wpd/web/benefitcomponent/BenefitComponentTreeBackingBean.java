/*
 * BenefitComponentTreeBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.benefitcomponent;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeModelBase;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.apache.myfaces.custom.tree2.TreeState;
import org.apache.myfaces.custom.tree2.TreeStateBase;

import com.wellpoint.wpd.web.benefitcomponent.tree.AdminLevelTreeNodeBase;
import com.wellpoint.wpd.web.benefitcomponent.tree.BenefitComponentTreeNodeBase;
import com.wellpoint.wpd.web.tree.BaseTreeNode;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * Bean for the tree of the  Benefit Component
 * This bean will bind with the jsp pages.
 * BenefitComponentTreeBackingBean contains the getters and setters of the 
 * variables and respective functions
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentTreeBackingBean extends BenefitComponentBackingBean{

	private BaseTreeNode root;

	private TreeModelBase treeDataModel;

	private TreeState treeState;
	
	private String scrollTop;
	
	
   /**
	* Constructor
	*/	
	public BenefitComponentTreeBackingBean(){
		if(getSession().getAttribute("SESSION_TREE_STATE_BC")!= null)
			treeState = (TreeState)getSession().getAttribute("SESSION_TREE_STATE_BC");
		else{
			//Code change for benefit component tree rendering optimization
			updateTreeStructure();
			treeState = new TreeStateBase();
	        treeState.setTransient(true);
	        getSession().setAttribute("SESSION_TREE_STATE_BC",treeState);
		}
		
	}
	
	/**
     * Method to return TreeModel
     * 
     * @return TreeModel
     */
	public TreeModel getTreeDataModel() {
		
		//Code change for benefit component tree rendering optimization
    	boolean treeReloadRequired = "Y".equals(getRequest().getParameter("reloadTree")) ? true : false; 
    	
    	// Function isTreeStructureUpdated will return true if any updates in the page
    	// affects the tree structure like benefit hide & unhide, Add/Remove Admin Option
    	treeDataModel = null;
    	if(super.getSession() != null) {
    		treeDataModel = getBenefitComponentSessionObject().getBenefitCmpntTree();
    		if(!treeReloadRequired && treeDataModel != null && !isTreeStructureUpdated())
            	return treeDataModel;
    	}
			
		String benefitComponentId = Integer.toString(getBenefitComponentSessionObject().getBenefitComponentId());
		String benefitComponentName = getBenefitComponentSessionObject().getBenefitComponentName();
		benefitComponentName = (benefitComponentName!=null) ? benefitComponentName:"Benefit Component";
		benefitComponentId = (benefitComponentId!=null) ? benefitComponentId:"";
		root = new BenefitComponentTreeNodeBase("root",benefitComponentId,benefitComponentName, false);
		treeDataModel = new TreeModelBase(root);
		root.setModel(treeDataModel);
		treeDataModel.setTreeState(treeState);
		
		//Code change for benefit component tree rendering optimization
        setTreeStructureUpdated(false);
        getBenefitComponentSessionObject().setBenefitCmpntTree(treeDataModel);
        
		return treeDataModel;
	}
	
	/**
	 * Method to process action for Benefit Component
	 * @param event
	 */
	public void processActionForBenefitComponent(ActionEvent event){
		UIComponent component = (UIComponent) event.getSource();
	     while (!(component != null && component instanceof HtmlTree)) {
	         component = component.getParent();

	      }
	      if (component != null) {
	          HtmlTree tree = (HtmlTree) component;
	          TreeNodeBase node = (BaseTreeNode) tree.getNode();
	          tree.setNodeSelected(event);
	          String type = ((BaseTreeNode) node).getType();
	          getSession().setAttribute("SESSION_NODE_TYPE_COMP",type);
	          getRequest().setAttribute("RETAIN_Value", "");
	        }
		
	}
	
	/**
	 * Method to process action for Benefit Hierarchy
	 * @param event
	 */
	public void processActionForBenefitHierarchy(ActionEvent event){
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
	          String keyDesc = ((BaseTreeNode) node).getDescription();
	          getSession().setAttribute("SESSION_NODE_TYPE_COMP",type);
	          getSession().setAttribute("SESSION_BNFT_ID",key);
	          getSession().setAttribute("SESSION_BNFT_NM",keyDesc);
	          getRequest().setAttribute("RETAIN_Value", "");
	            
	        }
	}
	
	/**
	 * Method to process action for Benefit Hierarchy
	 * @param event
	 */
	public void processActionForBenefitAdministration(ActionEvent event){
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
	            String description =  ((BaseTreeNode) node).getDescription();
	            String benefitDefinitionId = ((BaseTreeNode) node).getParent().getIdentifier();
	            String benefitCompName = ((BaseTreeNode) node).getParent().getParent().getDescription();
	            getSession().setAttribute("BENEFIT_COMP_NAME",benefitCompName);  
	            getSession().setAttribute("SESSION_BNFT_ID",benefitDefinitionId);
	            getSession().setAttribute("SESSION_BNFT_ADMIN_ID",key);
	            getSession().setAttribute("SESSION_BNFT_ADMIN_DESC",description);
	            getSession().setAttribute("SESSION_NODE_TYPE_COMP",type);
	            getSession().setAttribute("SESSION_BNFT_DEFN_ID",benefitDefinitionId);
	            getRequest().setAttribute("RETAIN_Value", "");
	        }
	}

	/**
	 * Method to process action for Admin Option
	 * @param event
	 */
	public void processActionForAdminOption(ActionEvent event){
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
	            String adminId = ((BaseTreeNode) node).getParent().getParent().getIdentifier();
	            String adminOptionLevelAssnId = String.valueOf(((AdminLevelTreeNodeBase)node).getAdminOptionAssnId());
	            String benefitId = ((BaseTreeNode) node).getParent().getParent().getParent().getIdentifier();
	            String benefitCompName = ((BaseTreeNode) node).getParent().getParent().getDescription();
	            getSession().setAttribute("BENEFIT_COMP_NAME",benefitCompName);   
	            getSession().setAttribute("SESSION_BNFT_ID",benefitId);
	            getSession().setAttribute("SESSION_NODE_TYPE_COMP",type);
	            getSession().setAttribute("SESSION_ADMIN_ID",key);
	            getSession().setAttribute("SESSION_ADMINISTRATION_ID",adminId);
	            getSession().setAttribute("SESSION_ADMIN_OPTION_ASSN_ID",adminOptionLevelAssnId);
	            getRequest().setAttribute("RETAIN_Value", "");
	           
	        }
		
	}
	
	/**
	 * Method for Refresh
	 * @return
	 */
	public String refresh(){
		String nodeClicked =(String)getSession().getAttribute("SESSION_NODE_TYPE_COMP");
		
		String mode = getBenefitComponentSessionObject().getBenefitComponentMode();
		
		Application application =
			FacesContext.getCurrentInstance().getApplication();
		
		//ComponentBenefitDefinitionsBackingBean componentBenefitDefinitionsBackingBean =
		//((ComponentBenefitDefinitionsBackingBean) 
		application.createValueBinding("#{ComponentBenefitDefinitionsBackingBean}").
		getValue(FacesContext.getCurrentInstance());
		
		BenefitComponentCreateBackingBean benefitComponentCreateBackingBean = ((BenefitComponentCreateBackingBean)application.createValueBinding(
				"#{benefitComponentCreateBackingBean}")
				.getValue(FacesContext.getCurrentInstance()));
		
		if(nodeClicked.equals("root") && mode.equals("View")){
			return "benefitComponentView";
		}
		else if(nodeClicked.equals("root")){
			return benefitComponentCreateBackingBean.loadUpdateTab();
		}
		if (nodeClicked.equals("Benefit-Hierarchy") && mode.equals("View")){
			this.setBreadCrumbText("Product Configuration >> Benefit Component " + "(" + getBenefitComponentSessionObject().getBenefitComponentName() + ") >> View");
			return "loadStandardDefinitionView";
		}
		else if(nodeClicked.equals("Benefit-Hierarchy")){
			return "standardBenefit";
		}
		if (nodeClicked.equals("Admin-Option") && mode.equals("View")){
			this.setBreadCrumbText("Product Configuration >> Benefit Component " + "(" + getBenefitComponentSessionObject().getBenefitComponentName() + ") >> View");
			return "adminOptionView";
		}
		else if(nodeClicked.equals("Admin-Option")){
			return "adminOption";
		}

		
		return "";
	}
	
	/**
	 * method to get the benefitComponentSessionObject.
	 * @return benefitComponentSessionObject.
	 */
    protected BenefitComponentSessionObject getBenefitComponentSessionObject(){
    	BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject)getSession().getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
		
		if(benefitComponentSessionObject == null) {
			benefitComponentSessionObject = new BenefitComponentSessionObject();
			getSession().setAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY,benefitComponentSessionObject);
		}
		return benefitComponentSessionObject;
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
