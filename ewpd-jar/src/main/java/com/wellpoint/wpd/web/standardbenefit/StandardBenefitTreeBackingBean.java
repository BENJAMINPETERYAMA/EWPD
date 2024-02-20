/*
 * StandardBenefitTreeBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.standardbenefit;

import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.standardbenefit.tree.AdminOptionsTreeNodeBase;
import com.wellpoint.wpd.web.standardbenefit.tree.StandardBenefitTreeNodeBase;
import com.wellpoint.wpd.web.tree.BaseTreeNode;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeModelBase;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.apache.myfaces.custom.tree2.TreeState;
import org.apache.myfaces.custom.tree2.TreeStateBase;


/**
 * Backing bean for standard benefit tree
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */

public class StandardBenefitTreeBackingBean extends WPDBackingBean {

	private BaseTreeNode root;

	private TreeModelBase treeDataModel;

	private TreeState treeState;
	
	private String scrollTop;
	
   /**
    * Constructor
    */
	public StandardBenefitTreeBackingBean() {
	
		if(getSession().getAttribute("SESSION_TREE_STATE_SB")!= null)
			treeState = (TreeState)getSession().getAttribute("SESSION_TREE_STATE_SB");
		else{
			treeState = new TreeStateBase();
	        treeState.setTransient(true);
	        getSession().setAttribute("SESSION_TREE_STATE_SB",treeState);
		}
	}
	
	/**
     * Method to return TreeModel
     * 
     * @return TreeModel
     */
	public TreeModel getTreeDataModel() {
		
		String benefitId = Integer.toString(getStandardBenefitSessionObject().getStandardBenefitKey());
		String benefitName = getStandardBenefitSessionObject().getStandardBenefitName();
		benefitName = (benefitName!=null) ? benefitName:"Standard Benefit";
		benefitId = (benefitId!=null) ? benefitId:"";
		root = new StandardBenefitTreeNodeBase("root",benefitId,benefitName, false);

		treeDataModel = new TreeModelBase(root);
		root.setModel(treeDataModel);
		treeDataModel.setTreeState(treeState);
		return treeDataModel;
	}
	
	public void processActionForStandardDefinition(ActionEvent event)
	throws AbortProcessingException {
	    UIComponent component = (UIComponent) event.getSource();
        while (!(component != null && component instanceof HtmlTree)) {
            component = component.getParent();

        }
        if (component != null) {
            HtmlTree tree = (HtmlTree) component;
            TreeNodeBase node = (BaseTreeNode) tree.getNode();
            tree.setNodeSelected(event);
            String type = ((BaseTreeNode) node).getType();
            getSession().setAttribute("SESSION_NODE_TYPE",type);
            getRequest().setAttribute("RETAIN_Value", "");
        }
	}
	
	/**
     * Action Listener to process the action of selecting a BenefitDefenition Header
     * 
     * @param event
     * @return void
     * @throws AbortProcessingException
     */
	public void processActionForBenefitDefinitionHead(ActionEvent event)
			throws AbortProcessingException {
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
            getSession().setAttribute("SESSION_NODE_TYPE",type);
            getSession().setAttribute("SESSION_BNFT_DEFN_ID",key);
            getRequest().setAttribute("RETAIN_Value", "");
            
        }
        
        	
  
	}

	/**
     * Action Listener to process the action of selecting a BenefitDefenition
     * 
     * @param event
     * @return void
     * @throws AbortProcessingException
     */
	public void processActionForBenefitDefinition(ActionEvent event)
			throws AbortProcessingException {
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
            String description = ((BaseTreeNode) node).getDescription();
            getSession().setAttribute("SESSION_BNFT_DEFN_ID",key);
            getSession().setAttribute("SESSION_BNFT_DEFN_DESC",description);
            getSession().setAttribute("SESSION_NODE_TYPE",type);
            getRequest().setAttribute("RETAIN_Value", "");
            
        }
	}

	/**
     * Action Listener to process the action of selecting a BenefitAdministration Header
     * 
     * @param event
     * @return void
     * @throws AbortProcessingException
     */
	public void processActionForBenefitAdministrationHead(ActionEvent event)
			throws AbortProcessingException {
	    UIComponent component = (UIComponent) event.getSource();
        while (!(component != null && component instanceof HtmlTree)) {
            component = component.getParent();
        }
        if (component != null) {
            HtmlTree tree = (HtmlTree) component;
            TreeNodeBase node = (BaseTreeNode) tree.getNode();
            tree.setNodeSelected(event);
            String type = ((BaseTreeNode) node).getType();
            String key = ((BaseTreeNode) node).getParent().getIdentifier();
            String description = ((BaseTreeNode) node).getParent().getDescription();
            getSession().setAttribute("SESSION_BNFT_DEFN_ID",key);
            getSession().setAttribute("SESSION_NODE_TYPE",type);
            getSession().setAttribute("SESSION_BNFT_DEFN_DESC",description);
            getRequest().setAttribute("RETAIN_Value", "");
        }
	}

	/**
     * Action Listener to process the action of selecting a Administration
     * 
     * @param event
     * @return void
     * @throws AbortProcessingException
     */
	public void processActionForBenefitAdministration(ActionEvent event)
			throws AbortProcessingException {
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
            String description =  ((BaseTreeNode) node).getParent().getDescription();
            String benefitDefinitionId = ((BaseTreeNode) node).getParent().getIdentifier();
            getSession().setAttribute("SESSION_BNFT_ADMIN_ID",key);
            getSession().setAttribute("SESSION_BNFT_ADMIN_DESC",description);
            getSession().setAttribute("SESSION_NODE_TYPE",type);
            getSession().setAttribute("SESSION_BNFT_DEFN_ID",benefitDefinitionId);
            getRequest().setAttribute("RETAIN_Value", "");
        }
		
	}

	/**
     * Action Listener to process the action of selecting a Benefit Level
     * 
     * @param event
     * @return void
     * @throws AbortProcessingException
     */
	public void processActionForBenefitLevel(ActionEvent event)
			throws AbortProcessingException {
		
		  getRequest().setAttribute("RETAIN_Value", "");

	}

	/**
     * Action Listener to process the action of selecting an Admin Option 
     * 
     * @param event
     * @return void
     * @throws AbortProcessingException
     */
	public void processActionForAdminOption(ActionEvent event)
			throws AbortProcessingException {
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
            String assnId = String.valueOf(((AdminOptionsTreeNodeBase)node).getAssnId());
            String description =  ((BaseTreeNode) node).getDescription();
            getSession().setAttribute("SESSION_ADMIN_OPTN_ID",key);
            getSession().setAttribute("SESSION_NODE_TYPE",type);
            getSession().setAttribute("SESSION_ADMIN_OPTN_ASSN",assnId);
            getSession().setAttribute("SESSION_BNFT_ADMIN_DESC",description);
            getRequest().setAttribute("RETAIN_Value", "");
        }

	}
	
	/**
     * Method to return the current session
     * 
     * @return HttpSession
     */
    protected HttpSession getSession() {
        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();
        return (HttpSession) context.getSession(true);
    }
    
    /**
     * Method to process the action of clicking a node 
     * 
     * @return String
     */
	public String refresh() {
		String nodeClicked =(String)getSession().getAttribute("SESSION_NODE_TYPE");
		String nodeId =(String) getSession().getAttribute("SESSION_BNFT_DEFN_ID");
		String mode = getStandardBenefitSessionObject().getStandardBenefitMode();
		
		Application application =
			FacesContext.getCurrentInstance().getApplication();
		
		BenefitLevelBackingBean benefitLevelBackingBean =
		((BenefitLevelBackingBean) application.createValueBinding("#{benefitLevelBackingBean}").
		getValue(FacesContext.getCurrentInstance()));
		
		StandardBenefitBackingBean standardBenefitBackingBean =
		((StandardBenefitBackingBean)application.createValueBinding("#{standardBenefitBackingBean}").
		getValue(FacesContext.getCurrentInstance()));
		

		if("root".equals(nodeClicked) && "View".equals(mode)){
			return standardBenefitBackingBean.loadStandardBenefitView();
		}
		else if ("root".equals(nodeClicked)){
		    return standardBenefitBackingBean.loadStandardBenefit();
		}
		if("Benefit-Definition".equals(nodeClicked) && "View".equals(mode)){
			this.setBreadCrumbText("Product Configuration >> Benefit" + " (" + this.getStandardBenefitSessionObject().getStandardBenefitName() + ") >> View");
			return benefitLevelBackingBean.searchBenefitLevels("benefitLevelView","tree");
		}
		else if("Benefit-Definition".equals(nodeClicked)){
			this.setBreadCrumbText("Product Configuration >> Benefit" + " (" + this.getStandardBenefitSessionObject().getStandardBenefitName() + ") >> Edit");
		    return benefitLevelBackingBean.dummySearchBenefitLevels("benefitLevel","tree");
		}
		if("Benefit-Definition-Head".equals(nodeClicked) && "Standard".equals(nodeId) && "View".equals(mode)){
			this.setBreadCrumbText("Product Configuration >> Benefit" + " (" + this.getStandardBenefitSessionObject().getStandardBenefitName() + ") >> View");
			return "standardDefinitionView";
		}
		else if("Benefit-Definition-Head".equals(nodeClicked) && "Standard".equals(nodeId)){
			this.setBreadCrumbText("Product Configuration >> Benefit" + " (" + this.getStandardBenefitSessionObject().getStandardBenefitName() + ") >> Edit");
			return "standardDefinition";
		}
		if("Benefit-Administration-Head".equals(nodeClicked) && "View".equals(mode)){
			this.setBreadCrumbText("Product Configuration >> Benefit" + " (" + this.getStandardBenefitSessionObject().getStandardBenefitName() + ") >> View");
            return "benefitAdministrationView";
        }
		else if("Benefit-Administration-Head".equals(nodeClicked)){
			this.setBreadCrumbText("Product Configuration >> Benefit" + " (" + this.getStandardBenefitSessionObject().getStandardBenefitName() + ") >> Edit");
			return "benefitLevel";
		}
		 if("Benefit-Administration".equals(nodeClicked) && "View".equals(mode)) {
		 	this.setBreadCrumbText("Product Configuration >> Benefit" + " (" + this.getStandardBenefitSessionObject().getStandardBenefitName() + ") >> View");
            return "adminOptionView";
		 }
		else if("Benefit-Administration".equals(nodeClicked)){
			this.setBreadCrumbText("Product Configuration >> Benefit" + " (" + this.getStandardBenefitSessionObject().getStandardBenefitName() + ") >> Edit");
			return "adminOption";
		}
		 if("Admin-Option".equals(nodeClicked) && "View".equals(mode)) {
		 	this.setBreadCrumbText("Product Configuration >> Benefit" + " (" + this.getStandardBenefitSessionObject().getStandardBenefitName() + ") >> View");
            return "questionView";
	     }
		else if("Admin-Option".equals(nodeClicked)){
			this.setBreadCrumbText("Product Configuration >> Benefit" + " (" + this.getStandardBenefitSessionObject().getStandardBenefitName() + ") >> Edit");
			return "question";
		}
		 

		return "";
		
	}
	 protected StandardBenefitSessionObject getStandardBenefitSessionObject(){
    	StandardBenefitSessionObject standardBenefitSessionObject = (StandardBenefitSessionObject)getSession().getAttribute("standard");
    		if(standardBenefitSessionObject == null) {
    			standardBenefitSessionObject = new StandardBenefitSessionObject();getSession().setAttribute("standard",standardBenefitSessionObject);
    		}
        return standardBenefitSessionObject;

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

