/*
 * Created on Feb 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.adminmethod.tree;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeModelBase;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.apache.myfaces.custom.tree2.TreeState;
import org.apache.myfaces.custom.tree2.TreeStateBase;

import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodValidationBO;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodContractTreeBackingBean extends WPDBackingBean {

	private TreeState treeState;

	private TreeModelBase treeDataModeltemp;

	private TreeModelBase treeDataModel;

	private AdminMethodDateSegmentTreeNodeBase root;

	private boolean withChildren;

	private String scrollTop;

	public AdminMethodContractTreeBackingBean() {
		Logger
				.logInfo("entered constructor of AdminMethodContractTreeBackingBean");

		treeState = new TreeStateBase();
		treeState.setTransient(true);
		getSession().setAttribute("ADMINMETHOD_SESSION_TREE_STATE", treeState);
	}

	/**
	 * This method returns the treeDataModel.
	 * @return TreeModel treeDataModel.
	 */
	public TreeModel getTreeDataModel() {

		Logger.logInfo("entered method getTreeDataModel");
		/*   WLPRD00444546  changes starts */ 
		
		//String initial = getRequest().getParameter("initial"); //has value if popup loaded for first time (from check in page)
		String entityId = "";
		//String entityType = "";
/*   WLPRD00444546  changes ends */ 

		String entityName = "";
		String contractId = "";

		if (null != getSession().getAttribute("AM_ENTITY_ID"))
			entityId = getSession().getAttribute("AM_ENTITY_ID").toString();
/*   WLPRD00444546  changes starts */ 

	/*	if (null != getSession().getAttribute("AM_ENTITY_TYPE"))
			entityType = getSession().getAttribute("AM_ENTITY_TYPE").toString();
*/
		/*   WLPRD00444546  changes starts */ 
		
		if (null != getSession().getAttribute("AM_ENTITY_NAME"))
			entityName = getSession().getAttribute("AM_ENTITY_NAME").toString();
		contractId = (String) getSession().getAttribute("AM_CONTRACT_ID");
/*   WLPRD00444546  changes starts */ 

		List datasegmants = null;
	/*   WLPRD00444546  changes Ends */ 
	
		//gets the product Name from session

		boolean rootFlag = false;

		if (null != entityId) {

			 
				root = new AdminMethodDateSegmentTreeNodeBase(
						WebConstants.ROOT, contractId, entityName, false);
			 
			rootFlag = true;
		} else {

			 
				root = new AdminMethodDateSegmentTreeNodeBase(
						WebConstants.ROOT, WebConstants.ZERO_STRING,
						"contract", false);
			 
		}

		treeDataModel = new TreeModelBase(root);
		root.setModel(treeDataModel);
		treeDataModel.setTreeState(treeState);
/*   WLPRD00444546  changes starts */ 

	//	int m = -1;
		if (rootFlag) {
			//getSession().setAttribute("AM_ENTITY_KEY", entityId + "");
			String expandPath[] = new String[4];
			expandPath[0] = root.getNodePath();
			datasegmants = root.loadChildren();
			if (!datasegmants.isEmpty()) {
				String selectNode = WebConstants.EMPTY_STRING;
				//
				 AdminMethodTreeNodeBase dateSegmentRoot = null;
				boolean check = false; 
				//AdminMethodValidationBO adminMethodValidationBO = null;
	        	int i=0;
	        	while (i<datasegmants.size()){
		        // 	adminMethodValidationBO=(AdminMethodValidationBO)datasegmants.get(i);
	        		 dateSegmentRoot = (AdminMethodTreeNodeBase) datasegmants.get(i);
		             if (entityId.equals(dateSegmentRoot.getIdentifier().split("~")[0])){
		                check = true;
		                 break;
		             }
		             i++;
	        	}
        		if (!check){
        			dateSegmentRoot = (AdminMethodTreeNodeBase) datasegmants.get(0);;
        		}
	                
/*   WLPRD00444546  changes ends */ 

				if (null == getSession().getAttribute("DIRECT_CLICK")) {
					getSession().setAttribute("AM_ENTITY_KEY",
							dateSegmentRoot.getIdentifier().split("~")[0] + "");
				}
				expandPath[1] = dateSegmentRoot.getNodePath();
				AdminMethodBenefitComponentTreeNodeBase subRootNode = (AdminMethodBenefitComponentTreeNodeBase) dateSegmentRoot
						.loadChildren().get(0);
				expandPath[2] = subRootNode.getNodePath();
				List listObjectNodesProduct = subRootNode.loadChildren();
				if (null == getSession().getAttribute("DIRECT_CLICK")) {
					getSession().setAttribute("AM_BC_KEY",
							subRootNode.getIdentifier().split("~")[0] + "");
					getSession().setAttribute("AM_BENEFIT_COMP_NAME",
							subRootNode.getDescription() + "");
				}
				//Added For AdminMethodPrtint popup
				getSession().setAttribute(WebConstants.BENEFIT_COMP_NAME,
						subRootNode.getDescription() + "");

				AdminMethodStandardBenefitTreeNodeBase objectNodeProduct = (AdminMethodStandardBenefitTreeNodeBase) listObjectNodesProduct
						.get(0);
				expandPath[3] = objectNodeProduct.getNodePath();
				if (null == getSession().getAttribute("DIRECT_CLICK")) {
					getSession().setAttribute(
							"AM_BENEFIT",
							objectNodeProduct.getIdentifier().split("~")[0]
									+ "");
					//Added For AdminMethodPrint popup
					getSession().setAttribute(
							WebConstants.SESSION_BNFT_DEFN_ID,
							objectNodeProduct.getIdentifier().split("~")[0]
									+ "");
					selectNode = (objectNodeProduct).getNodePath();
					treeState.expandPath(expandPath);
					treeState.setSelected(selectNode);
				}
				/*   WLPRD00444546  changes starts */ 
				
			//	String productName = objectNodeProduct.getDescription();
			//	m = 1;
			/*   WLPRD00444546  changes ends */ 
			
			}

		}

		List list = (List) getSession().getAttribute("treeChildren");
		if (null == list || list.isEmpty()) {
		/*   WLPRD00444546  changes starts */ 
		
		//	list = root.loadChildren();
			list =datasegmants;
			getSession().setAttribute("treeChildren", list);
			/*   WLPRD00444546  changes ends */ 
			
		}
		if (null == list || list.isEmpty()) {
			withChildren = false;
		} else {
			withChildren = true;
		}

		return treeDataModel;

	}

	//This method processes action for Standard Benefit click in the tree.
	public void processActionForBenefit(ActionEvent event)
			throws AbortProcessingException {

		Logger.logInfo("entered method AdminMethodContractTreeBackingBean");

		UIComponent component = (UIComponent) event.getSource();
		while (!(component != null && component instanceof HtmlTree)) {
			component = component.getParent();

		}
		if (component != null) {
			HtmlTree tree = (HtmlTree) component;
			TreeNodeBase node = (AdminMethodStandardBenefitTreeNodeBase) tree
					.getNode();
			tree.setNodeSelected(event);
			String type = node.getType();
			String[] benefitkey = node.getIdentifier().split("~");
			String key = benefitkey[0];
			String benftSqncNum = benefitkey[1];

			String[] benefitComponentDet = ((AdminMethodStandardBenefitTreeNodeBase) node)
					.getParent().getIdentifier().split("~");
			String benefitComponentId = benefitComponentDet[0];
			String bcSqncNum = benefitComponentDet[1];

			String benefitComponentName = ((AdminMethodStandardBenefitTreeNodeBase) node)
					.getParent().getDescription();
			/*String description = node
			 .getDescription();*/
			String entityId = ((AdminMethodStandardBenefitTreeNodeBase) node)
					.getParent().getParent().getIdentifier();

			String benefitCompId = ((AdminMethodStandardBenefitTreeNodeBase) node)
					.getParent().getIdentifier();

			getSession().setAttribute("AM_ENTITY_KEY", entityId);
			getSession().setAttribute("AM_BC_KEY", benefitComponentId);
			getSession().setAttribute("AM_BENEFIT", key);
			getSession().setAttribute("AM_BC_SQNC", bcSqncNum);
			getSession().setAttribute("AM_BEN_SQNC", benftSqncNum);
			getSession().setAttribute("AM_BENEFIT_COMP_NAME",
					benefitComponentName);
			getSession().setAttribute("DIRECT_CLICK", "Y");
			/*   WLPRD00444546  changes starts */ 
			
			getSession().setAttribute("AM_ENTITY_ID", entityId);
			/*   WLPRD00444546  changes ends */ 
			
		}
	}

	/**
	 * @return Returns the withChildren.
	 */
	public boolean isWithChildren() {
		return withChildren;
	}

	/**
	 * @param withChildren The withChildren to set.
	 */
	public void setWithChildren(boolean withChildren) {
		this.withChildren = withChildren;
	}

	/**
	 * @return Returns the scrollTop.
	 */
	public String getScrollTop() {
		Object clearScrollTop = getRequest().getAttribute("clearScrollTop");  
    	
    	if(clearScrollTop !=null && clearScrollTop.toString().equals("true")) {
    		scrollTop = null;    		
    	}
		return scrollTop;
	}

	/**
	 * @param scrollTop The scrollTop to set.
	 */
	public void setScrollTop(String scrollTop) {
		this.scrollTop = scrollTop;
	}
}