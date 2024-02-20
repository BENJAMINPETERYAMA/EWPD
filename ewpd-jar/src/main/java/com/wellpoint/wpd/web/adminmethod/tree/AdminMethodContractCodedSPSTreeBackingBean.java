/*
 * Created on Feb 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.adminmethod.tree;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeModelBase;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.apache.myfaces.custom.tree2.TreeState;
import org.apache.myfaces.custom.tree2.TreeStateBase;

import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author U16012
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodContractCodedSPSTreeBackingBean extends WPDBackingBean {

	private TreeState treeState;

	private TreeModelBase treeDataModeltemp;

	private TreeModelBase treeDataModel;

	private AdminMethodDateSegmentCodedSPSTreeNodeBase root;

	private boolean withChildren;

	private String scrollTop;
	
	private HtmlInputHidden loadTree;

	public AdminMethodContractCodedSPSTreeBackingBean() {
		Logger
				.logInfo("entered constructor of AdminMethodContractTreeBackingBean");

		treeState = new TreeStateBase();
	}

	/**
	 * This method returns the treeDataModel.
	 * 
	 * @return TreeModel treeDataModel.
	 */
	public TreeModel getTreeDataModel() {

		Logger.logInfo("entered method getTreeDataModel");
		String initial = getRequest().getParameter("initial"); //has value if
		// popup loaded
		// for first time
		// (from check in
		// page)
		String entityId = "";
		String entityType = "";
		String entityName = "";
		String contractId = "";

		if (initial != null) {
			getSession().removeAttribute("DIRECT_CLICK");
		}

		if (null != getSession().getAttribute("AM_ENTITY_ID"))
			entityId = getSession().getAttribute("AM_ENTITY_ID").toString();

		if (null != getSession().getAttribute("AM_ENTITY_TYPE"))
			entityType = getSession().getAttribute("AM_ENTITY_TYPE").toString();

		if (null != getSession().getAttribute("AM_ENTITY_NAME"))
			entityName = getSession().getAttribute("AM_ENTITY_NAME").toString();
		contractId = (String) getSession().getAttribute("AM_CONTRACT_ID");

		List benefitComponents = null;

		boolean rootFlag = false;

		if (null != entityId) {
			
				root = new AdminMethodDateSegmentCodedSPSTreeNodeBase(
						WebConstants.ROOT, contractId, entityName, false);
			
			rootFlag = true;
		} else {
			
				root = new AdminMethodDateSegmentCodedSPSTreeNodeBase(
						WebConstants.ROOT, WebConstants.ZERO_STRING,
						"contract", false);
			
		}
		treeDataModel = new TreeModelBase(root);
		root.setModel(treeDataModel);
		treeDataModel.setTreeState(treeState);

		int m = -1;
		if (rootFlag) {

			String expandPath[] = new String[4];
			expandPath[0] = root.getNodePath();
			if (!root.loadChildren().isEmpty()) {
				String selectNode = WebConstants.EMPTY_STRING;
				AdminMethodCodedSPSTreeNodeBase dateSegmentRoot = (AdminMethodCodedSPSTreeNodeBase) root
						.loadChildren().get(0);
				if (null == getSession().getAttribute("DIRECT_CLICK")) {
					getSession().setAttribute("AM_ENTITY_KEY",
							dateSegmentRoot.getIdentifier().split("~")[0] + "");
					getSession().setAttribute("AM_PRODUCT_ID",
							dateSegmentRoot.getIdentifier().split("~")[1] + "");
				}
				expandPath[1] = dateSegmentRoot.getNodePath();
				List list = dateSegmentRoot.loadChildren();

				AdminMethodBenefitComponentCodedSPSTreeNodeBase subRootNode = (AdminMethodBenefitComponentCodedSPSTreeNodeBase) list
						.get(0);
				expandPath[2] = subRootNode.getNodePath();
				List listObjectNodesProduct = subRootNode.loadChildren();
				if (null == getSession().getAttribute("DIRECT_CLICK")) {
					getSession().setAttribute("AM_BC_KEY",
							subRootNode.getIdentifier().split("~")[0] + "");
					getSession().setAttribute("AM_BENEFIT_COMP_NAME",
							subRootNode.getDescription() + "");
					getSession().setAttribute("AM_ADMIN_ID",
							subRootNode.getIdentifier().split("~")[1] + "");
					getSession().setAttribute("AM_BEN_ADMIN_ID",
							subRootNode.getIdentifier().split("~")[2] + "");
				}
				//Added For AdminMethodPrtint popup
				getSession().setAttribute(WebConstants.BENEFIT_COMP_NAME,
						subRootNode.getDescription() + "");

				AdminMethodStandardBenefitCodedSPSTreeNodeBase objectNodeProduct = (AdminMethodStandardBenefitCodedSPSTreeNodeBase) listObjectNodesProduct
						.get(0);
				expandPath[3] = objectNodeProduct.getNodePath();
				if (null == getSession().getAttribute("DIRECT_CLICK")) {
					getSession().setAttribute(
							"AM_BENEFIT",
							objectNodeProduct.getIdentifier().split("~")[0]
									+ "");
					getSession().setAttribute(
							"AM_BEN_ADMIN_ID",
							objectNodeProduct.getIdentifier().split("~")[2]
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
				String productName = objectNodeProduct.getDescription();
				m = 1;
			}

		}

		List list = (List) getSession().getAttribute("treeChildren");
		if (null == list || list.isEmpty()) {
			list = root.loadChildren();
			getSession().setAttribute("treeChildren", list);
		}
		if (null == list || list.isEmpty()) {
			withChildren = false;
		} else {
			withChildren = true;
		}

		return treeDataModel;

	}

	/**
	 * This method processes action for Standard Benefit click in the tree.
	 * 
	 * @param ActionEvent event.
	 */
	public void processActionForBenefit(ActionEvent event)
			throws AbortProcessingException {

		Logger
				.logInfo("entered method AdminMethodContractCodedSPSTreeBackingBean");

		UIComponent component = (UIComponent) event.getSource();
		while (!(component != null && component instanceof HtmlTree)) {
			component = component.getParent();

		}
		if (component != null) {
			HtmlTree tree = (HtmlTree) component;
			TreeNodeBase node = (AdminMethodStandardBenefitCodedSPSTreeNodeBase) tree
					.getNode();
			tree.setNodeSelected(event);
			String type = node.getType();
			String[] benefitkey = node.getIdentifier().split("~");
			String key = benefitkey[0];
			String benftSqncNum = benefitkey[1];

			String[] benefitComponentDet = ((AdminMethodStandardBenefitCodedSPSTreeNodeBase) node)
					.getParent().getIdentifier().split("~");
			String benefitComponentId = benefitComponentDet[0];
			String bcSqncNum = benefitComponentDet[1];

			String benefitComponentName = ((AdminMethodStandardBenefitCodedSPSTreeNodeBase) node)
					.getParent().getDescription();
			/*
			 * String description = node .getDescription();
			 */
			String entityId = ((AdminMethodStandardBenefitCodedSPSTreeNodeBase) node)
					.getParent().getParent().getIdentifier();

			String benefitCompId = ((AdminMethodStandardBenefitCodedSPSTreeNodeBase) node)
					.getParent().getIdentifier();

			String productId = ((AdminMethodStandardBenefitCodedSPSTreeNodeBase) node)
					.getParent().getParent().getIdentifier().split("~")[1];

			String adminId = ((AdminMethodStandardBenefitCodedSPSTreeNodeBase) node)
					.getIdentifier().split("~")[2];

			getSession().setAttribute("AM_ENTITY_KEY", entityId.split("~")[0]);
			getSession().setAttribute("AM_BC_KEY", benefitComponentId);
			getSession().setAttribute("AM_BENEFIT", key);
			getSession().setAttribute("AM_BC_SQNC", bcSqncNum);
			getSession().setAttribute("AM_BEN_SQNC", benftSqncNum);
			getSession().setAttribute("AM_BENEFIT_COMP_NAME",
					benefitComponentName);
			getSession().setAttribute("AM_PRODUCT_ID", productId);
			getSession().setAttribute("AM_BEN_ADMIN_ID", adminId);
			getSession().setAttribute("DIRECT_CLICK", "Y");
		}
	}

	/**
	 * @return Returns the withChildren.
	 */
	public boolean isWithChildren() {
		return withChildren;
	}

	/**
	 * @param withChildren
	 *            The withChildren to set.
	 */
	public void setWithChildren(boolean withChildren) {
		this.withChildren = withChildren;
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

	//Add a new attribute to load the tree, due to component load order change on JSF 1.2.
	public HtmlInputHidden getLoadTree() {
		getTreeDataModel();
		return loadTree;
	}

	public void setLoadTree(HtmlInputHidden loadTree) {
		this.loadTree = loadTree;
	}
		
}