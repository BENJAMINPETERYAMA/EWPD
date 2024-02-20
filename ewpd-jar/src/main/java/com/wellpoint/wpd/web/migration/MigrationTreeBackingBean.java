/*
 * StandardBenefitTreeBackingBean.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.migration;

import java.util.ArrayList;
import java.util.List;

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

import com.wellpoint.wpd.business.product.builder.ProductBusinessObjectBuilder;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.migration.bo.BenefitComponentBenefit;
import com.wellpoint.wpd.common.migration.bo.MigrationContract;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.web.migration.tree.MigrationTreeNodeBase;
import com.wellpoint.wpd.web.tree.BaseTreeNode;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MigrationTreeBackingBean extends MigrationBaseBackingBean {

	private BaseTreeNode root;

	private TreeModelBase treeDataModel;

	private TreeState treeState;

	private List messageList = new ArrayList();

	private boolean countMap;

	private boolean countMapDelete;

	/**
	 * Constructor
	 */
	public MigrationTreeBackingBean() {

		if (getSession().getAttribute(WebConstants.SESSION_TREE_STATE) != null)
			treeState = (TreeState) getSession().getAttribute(
					WebConstants.SESSION_TREE_STATE);
		else {
			treeState = new TreeStateBase();
			treeState.setTransient(true);
			getSession().setAttribute(WebConstants.SESSION_TREE_STATE,
					treeState);
		}
	}

	/**
	 * Method to return TreeModel
	 * 
	 * @return TreeModel
	 */
	public TreeModel getTreeDataModel(){
		MigrationContract migrationContract = this
				.getMigrationContractSession().getMigrationContract();
		String prodId = migrationContract.getEwpdProductSystemId();
		
		String mgrtdDateSegmentId = this.getMigrationContractSession().getDateSegmentId();

		String baseDateSegmentId  = migrationContract.getBaseDateSegmentId();
		int intBaseId = 0;
		if(null != baseDateSegmentId){
			intBaseId = Integer.parseInt(baseDateSegmentId);
		}
		ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
		List prodList = new ArrayList();
		try {
			if (!StringUtil.isEmpty(prodId)) {
				prodList = builder.getProductName(prodId);
			}

		} catch (SevereException ex) {
		   Logger.logError(ex);
		}
		String prodName = null;
		if (null != prodList && prodList.size() != 0) {

			ProductBO prodBO = (ProductBO) prodList.get(0);
			prodName = prodBO.getProductName();
		}
		String contractSysId = migrationContract.getMigrationSystemId();
		String option = this.getMigrationContractSession()
		.getMigrationContract().getOption();
		
		
		root = new MigrationTreeNodeBase(WebConstants.ROOT, prodId,
				WebConstants.STEP_CONTRACT, false, prodName, contractSysId,option,intBaseId, mgrtdDateSegmentId);
		treeDataModel = new TreeModelBase(root);
		root.setModel(treeDataModel);
		treeDataModel.setTreeState(treeState);
		MigrationContract migrationContract1 = this
				.getMigrationContractSession().getMigrationContract();

		int m = -1;
		if (null != getSession().getAttribute(WebConstants.TREE_EXP_FLAG)) {
			String expandPath[] = new String[4];
			expandPath[0] = root.getNodePath();
			BaseTreeNode subRootNode = (BaseTreeNode) root.loadChildren()
					.get(5);
			expandPath[1] = subRootNode.getNodePath();
			List listObjectNodesProduct = subRootNode.loadChildren();
			String selectNode = WebConstants.EMPTY_STRING;

			for (int i = 0; i < listObjectNodesProduct.size(); i++) {
				BaseTreeNode objectNodeProduct = (BaseTreeNode) listObjectNodesProduct
						.get(i);
				expandPath[2] = objectNodeProduct.getNodePath();

				List listBenefitComponents = objectNodeProduct.loadChildren();
				String productName = objectNodeProduct.getDescription();
				migrationContract1.setProductName(productName);
				if (null != listBenefitComponents
						&& listBenefitComponents.size() > 0) {
					BaseTreeNode objectNodeBenComponent = (BaseTreeNode) listBenefitComponents
							.get(0);
					String benCompId = objectNodeBenComponent.getIdentifier();
					String benName = objectNodeBenComponent.getDescription();
					int benComId = 0;
					if (null != benCompId) {
						benComId = Integer.parseInt(benCompId);
						migrationContract1.setBenefitComponentId(benComId);
						migrationContract1.setBenefitCompName(benName);
					}

					expandPath[3] = objectNodeBenComponent.getNodePath();
					List listBenefits = objectNodeBenComponent.loadChildren();

					if (null != listBenefits && listBenefits.size() > 0) {
						BaseTreeNode objectNodeStdBenefits = (BaseTreeNode) listBenefits
								.get(0);

						String stdId = objectNodeStdBenefits.getIdentifier();
						String stdName = objectNodeStdBenefits.getDescription();
						if (null != stdId) {
							int stdBenId = Integer.parseInt(stdId);
							migrationContract1.setStdBenefitId(stdBenId);
							migrationContract1.setStdBenefitName(stdName);
						}
						selectNode = (objectNodeStdBenefits).getNodePath();
					}
				}
				break;
			}
			treeState.expandPath(expandPath);
			treeState.setSelected(selectNode);
			m = 1;
		}
		if (null != getSession().getAttribute(WebConstants.STEP8_TREE_EXP_FLAG)) {
			String expandPath[] = new String[4];
			expandPath[0] = root.getNodePath();
			BaseTreeNode subRootNode = (BaseTreeNode) root.loadChildren()
					.get(6);
			expandPath[1] = subRootNode.getNodePath();
			List listObjectNodesProduct = subRootNode.loadChildren();
			String selectNode = WebConstants.EMPTY_STRING;

			for (int i = 0; i < listObjectNodesProduct.size(); i++) {
				BaseTreeNode objectNodeProduct = (BaseTreeNode) listObjectNodesProduct
						.get(i);
				expandPath[2] = objectNodeProduct.getNodePath();

				List listBenefitComponents = objectNodeProduct.loadChildren();
				String productName = objectNodeProduct.getDescription();
				migrationContract1.setProductName(productName);
				if (null != listBenefitComponents
						&& listBenefitComponents.size() > 0) {
					BaseTreeNode objectNodeBenComponent = (BaseTreeNode) listBenefitComponents
							.get(0);
					String benCompId = objectNodeBenComponent.getIdentifier();
					String benName = objectNodeBenComponent.getDescription();
					int benComId = 0;
					if (null != benCompId) {
						benComId = Integer.parseInt(benCompId);
						migrationContract1.setBenefitComponentId(benComId);
						migrationContract1.setBenefitCompName(benName);
					}

					expandPath[3] = objectNodeBenComponent.getNodePath();
					selectNode = (objectNodeBenComponent).getNodePath();
				}
				break;
			}
			treeState.expandPath(expandPath);
			treeState.setSelected(selectNode);
			m = 1;
		}
		if (null != getSession().getAttribute(WebConstants.MIGRATION_TREE_NEXT)) {
			BenefitComponentBenefit benefitComponentBenefit = (BenefitComponentBenefit) getSession()
					.getAttribute(WebConstants.MIGRATION_TREE_NEXT);
			int componentIdNext = 0;
			int benefitIdNext = 0;
			if (null != benefitComponentBenefit) {
				componentIdNext = benefitComponentBenefit
						.getBenefitComponentId();
				benefitIdNext = benefitComponentBenefit.getBenefitId();

			}

			String expandPath[] = new String[4];
			expandPath[0] = root.getNodePath();
			BaseTreeNode subRootNode = (BaseTreeNode) root.loadChildren()
					.get(5);
			expandPath[1] = subRootNode.getNodePath();
			List listObjectNodesProduct = subRootNode.loadChildren();
			String selectNode = WebConstants.EMPTY_STRING;

			for (int i = 0; i < listObjectNodesProduct.size(); i++) {
				//expected only one product here
				BaseTreeNode objectNodeProduct = (BaseTreeNode) listObjectNodesProduct
						.get(i);
				expandPath[2] = objectNodeProduct.getNodePath();

				List listBenefitComponents = objectNodeProduct.loadChildren();
				String productName = objectNodeProduct.getDescription();
				migrationContract1.setProductName(productName);
				BaseTreeNode objectNodeBenComponent = new BaseTreeNode();
				if (null != listBenefitComponents
						&& listBenefitComponents.size() > 0) {

					for (int k = 0; k < listBenefitComponents.size(); k++) {
						objectNodeBenComponent = (BaseTreeNode) listBenefitComponents
								.get(k);
						String benCompId = objectNodeBenComponent
								.getIdentifier();
						int benCompInt = 0;
						if (null != benCompId) {
							benCompInt = Integer.parseInt(benCompId);
							if (benCompInt == componentIdNext) {
								expandPath[3] = objectNodeBenComponent
										.getNodePath();
								break;
							}

						}
					}
				}//end of if
				List listBenefits = objectNodeBenComponent.loadChildren();
				if (null != listBenefits && listBenefits.size() > 0) {

					for (int k = 0; k < listBenefits.size(); k++) {
						BaseTreeNode objectNodeStdBenefits = (BaseTreeNode) listBenefits
								.get(k);
						String stdId = objectNodeStdBenefits.getIdentifier();
						if (null != stdId) {
							int stdBenId = Integer.parseInt(stdId);
							if (stdBenId == benefitIdNext) {
								selectNode = (objectNodeStdBenefits)
										.getNodePath();
								break;
							}
						}
					}//end of for
				} // end of if
			}//end of for
			treeState.expandPath(expandPath);
			treeState.setSelected(selectNode);
			m = 1;
		}//end of if
		else if (null != getSession().getAttribute(
				WebConstants.MIGRATION_TREE_BACK)) {
			BenefitComponentBenefit benefitComponentBenefit = (BenefitComponentBenefit) getSession()
					.getAttribute(WebConstants.MIGRATION_TREE_BACK);
			int componentIdPrevious = 0;
			int benefitIdPrevious = 0;
			if (null != benefitComponentBenefit) {
				componentIdPrevious = benefitComponentBenefit
						.getBenefitComponentId();
				benefitIdPrevious = benefitComponentBenefit.getBenefitId();

			}

			String expandPath[] = new String[4];
			expandPath[0] = root.getNodePath();
			BaseTreeNode subRootNode = (BaseTreeNode) root.loadChildren()
					.get(5);
			expandPath[1] = subRootNode.getNodePath();
			List listObjectNodesProduct = subRootNode.loadChildren();
			String selectNode = WebConstants.EMPTY_STRING;

			for (int i = 0; i < listObjectNodesProduct.size(); i++) {
				//expected only one product here
				BaseTreeNode objectNodeProduct = (BaseTreeNode) listObjectNodesProduct
						.get(i);
				expandPath[2] = objectNodeProduct.getNodePath();

				List listBenefitComponents = objectNodeProduct.loadChildren();
				String productName = objectNodeProduct.getDescription();
				migrationContract1.setProductName(productName);
				BaseTreeNode objectNodeBenComponent = new BaseTreeNode();
				if (null != listBenefitComponents
						&& listBenefitComponents.size() > 0) {

					for (int k = 0; k < listBenefitComponents.size(); k++) {
						objectNodeBenComponent = (BaseTreeNode) listBenefitComponents
								.get(k);
						String benCompId = objectNodeBenComponent
								.getIdentifier();
						int benCompInt = 0;
						if (null != benCompId) {
							benCompInt = Integer.parseInt(benCompId);
							if (benCompInt == componentIdPrevious) {
								expandPath[3] = objectNodeBenComponent
										.getNodePath();
								break;
							}

						}
					}
				}//end of if
				List listBenefits = objectNodeBenComponent.loadChildren();
				if (null != listBenefits && listBenefits.size() > 0) {

					for (int k = 0; k < listBenefits.size(); k++) {
						BaseTreeNode objectNodeStdBenefits = (BaseTreeNode) listBenefits
								.get(k);
						String stdId = objectNodeStdBenefits.getIdentifier();
						if (null != stdId) {
							int stdBenId = Integer.parseInt(stdId);
							if (stdBenId == benefitIdPrevious) {
								selectNode = (objectNodeStdBenefits)
										.getNodePath();
								break;
							}
						}
					}//end of for
				} // end of if
			}//end of for
			treeState.expandPath(expandPath);
			treeState.setSelected(selectNode);
			m = 1;
		} 
		else if (null != getSession().getAttribute(WebConstants.MIGRATION_STEP8_NEXT)) {
			BenefitComponentBenefit benefitComponentBenefit = (BenefitComponentBenefit) getSession()
					.getAttribute(WebConstants.MIGRATION_STEP8_NEXT);
			int componentIdNext = 0;
			int benefitIdNext = 0;
			if (null != benefitComponentBenefit) {
				componentIdNext = benefitComponentBenefit
						.getBenefitComponentId();
				benefitIdNext = benefitComponentBenefit.getBenefitId();

			}

			String expandPath[] = new String[4];
			expandPath[0] = root.getNodePath();
			BaseTreeNode subRootNode = (BaseTreeNode) root.loadChildren()
					.get(6);
			expandPath[1] = subRootNode.getNodePath();
			List listObjectNodesProduct = subRootNode.loadChildren();
			String selectNode = WebConstants.EMPTY_STRING;

			for (int i = 0; i < listObjectNodesProduct.size(); i++) {
				//expected only one product here
				BaseTreeNode objectNodeProduct = (BaseTreeNode) listObjectNodesProduct
						.get(i);
				expandPath[2] = objectNodeProduct.getNodePath();

				List listBenefitComponents = objectNodeProduct.loadChildren();
				String productName = objectNodeProduct.getDescription();
				migrationContract1.setProductName(productName);
				BaseTreeNode objectNodeBenComponent = new BaseTreeNode();
				if (null != listBenefitComponents
						&& listBenefitComponents.size() > 0) {

					for (int k = 0; k < listBenefitComponents.size(); k++) {
						objectNodeBenComponent = (BaseTreeNode) listBenefitComponents
								.get(k);
						String benCompId = objectNodeBenComponent
								.getIdentifier();
						int benCompInt = 0;
						if (null != benCompId) {
							benCompInt = Integer.parseInt(benCompId);
							if (benCompInt == componentIdNext) {
								expandPath[3] = objectNodeBenComponent
										.getNodePath();
								selectNode = (objectNodeBenComponent)
								.getNodePath();
								break;
							}

						}
					}
				}//end of if
			}//end of for
			treeState.expandPath(expandPath);
			treeState.setSelected(selectNode);
			m = 1;
		}
		else if (null != getSession().getAttribute(
				WebConstants.MIGRATION_STEP8_BACK)) {
			BenefitComponentBenefit benefitComponentBenefit = (BenefitComponentBenefit) getSession()
					.getAttribute(WebConstants.MIGRATION_STEP8_BACK);
			int componentIdPrevious = 0;
			int benefitIdPrevious = 0;
			if (null != benefitComponentBenefit) {
				componentIdPrevious = benefitComponentBenefit
						.getBenefitComponentId();
				benefitIdPrevious = benefitComponentBenefit.getBenefitId();

			}

			String expandPath[] = new String[4];
			expandPath[0] = root.getNodePath();
			BaseTreeNode subRootNode = (BaseTreeNode) root.loadChildren()
					.get(6);
			expandPath[1] = subRootNode.getNodePath();
			List listObjectNodesProduct = subRootNode.loadChildren();
			String selectNode = WebConstants.EMPTY_STRING;

			for (int i = 0; i < listObjectNodesProduct.size(); i++) {
				//expected only one product here
				BaseTreeNode objectNodeProduct = (BaseTreeNode) listObjectNodesProduct
						.get(i);
				expandPath[2] = objectNodeProduct.getNodePath();

				List listBenefitComponents = objectNodeProduct.loadChildren();
				String productName = objectNodeProduct.getDescription();
				migrationContract1.setProductName(productName);
				BaseTreeNode objectNodeBenComponent = new BaseTreeNode();
				if (null != listBenefitComponents
						&& listBenefitComponents.size() > 0) {

					for (int k = 0; k < listBenefitComponents.size(); k++) {
						objectNodeBenComponent = (BaseTreeNode) listBenefitComponents
								.get(k);
						String benCompId = objectNodeBenComponent
								.getIdentifier();
						int benCompInt = 0;
						if (null != benCompId) {
							benCompInt = Integer.parseInt(benCompId);
							if (benCompInt == componentIdPrevious) {
								expandPath[3] = objectNodeBenComponent
										.getNodePath();
								selectNode = (objectNodeBenComponent)
								.getNodePath();
								break;
							}

						}
					}
				}//end of if
			}//end of for
			treeState.expandPath(expandPath);
			treeState.setSelected(selectNode);
			m = 1;
		} 
		else if (m != 1) {
			List list = root.loadChildren();
			String toBeselectedNode = WebConstants.EMPTY_STRING;
			String lastAccessPage = WebConstants.EMPTY_STRING;
			if (null != this.getMigrationContractSession().getNavigationInfo()) {
				lastAccessPage = this.getMigrationContractSession()
						.getNavigationInfo().getLastAccessedPage();
			}
			String expandPath[] = new String[4];
			expandPath[0] = root.getNodePath();

			if (null != list && list.size() > 0) {
				BaseTreeNode baseTreeNode = null;
				if (WebConstants.MIG_CONTRACT_STEP2.equals(lastAccessPage)) {
					baseTreeNode = (BaseTreeNode) list.get(0);
					toBeselectedNode = (baseTreeNode).getNodePath();
					treeState.setSelected(toBeselectedNode);
					BaseTreeNode subRootNode = (BaseTreeNode) root
							.loadChildren().get(0);
					expandPath[1] = subRootNode.getNodePath();
				} else if (WebConstants.MIG_CONTRACT_STEP3
						.equals(lastAccessPage)) {
					baseTreeNode = (BaseTreeNode) list.get(1);
					toBeselectedNode = (baseTreeNode).getNodePath();
					treeState.setSelected(toBeselectedNode);
					BaseTreeNode subRootNode = (BaseTreeNode) root
							.loadChildren().get(1);
					expandPath[1] = subRootNode.getNodePath();
				} else if (WebConstants.MIG_CONTRACT_STEP4
						.equals(lastAccessPage)) {
					baseTreeNode = (BaseTreeNode) list.get(2);
					toBeselectedNode = (baseTreeNode).getNodePath();
					treeState.setSelected(toBeselectedNode);
					BaseTreeNode subRootNode = (BaseTreeNode) root
							.loadChildren().get(2);
					expandPath[1] = subRootNode.getNodePath();
				} else if (WebConstants.MIG_CONTRACT_STEP5
						.equals(lastAccessPage)) {
					baseTreeNode = (BaseTreeNode) list.get(3);
					toBeselectedNode = (baseTreeNode).getNodePath();
					treeState.setSelected(toBeselectedNode);
					BaseTreeNode subRootNode = (BaseTreeNode) root
							.loadChildren().get(3);
					expandPath[1] = subRootNode.getNodePath();
				} else if (WebConstants.MIG_CONTRACT_STEP6
						.equals(lastAccessPage)) {
					baseTreeNode = (BaseTreeNode) list.get(4);
					toBeselectedNode = (baseTreeNode).getNodePath();
					treeState.setSelected(toBeselectedNode);
					BaseTreeNode subRootNode = (BaseTreeNode) root
							.loadChildren().get(4);
					expandPath[1] = subRootNode.getNodePath();
				} else if (WebConstants.MIG_CONTRACT_STEP9
						.equals(lastAccessPage)) {
					baseTreeNode = (BaseTreeNode) list.get(7);
					toBeselectedNode = (baseTreeNode).getNodePath();
					treeState.setSelected(toBeselectedNode);
					BaseTreeNode subRootNode = (BaseTreeNode) root
							.loadChildren().get(7);
					expandPath[1] = subRootNode.getNodePath();
				} else if (WebConstants.MIG_CONTRACT_STEP10
						.equals(lastAccessPage)) {
					baseTreeNode = (BaseTreeNode) list.get(8);
					toBeselectedNode = (baseTreeNode).getNodePath();
					treeState.setSelected(toBeselectedNode);
					BaseTreeNode subRootNode = (BaseTreeNode) root
							.loadChildren().get(8);
					expandPath[1] = subRootNode.getNodePath();
				}

				treeState.expandPath(expandPath);
			}
		}
		return treeDataModel;
	}

	
	



	/**
	 * Action Listener to process the action of selecting a BenefitDefenition
	 * Header
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
		}//end while
		if (component != null) {
			HtmlTree tree = (HtmlTree) component;
			TreeNodeBase node = (BaseTreeNode) tree.getNode();
			tree.setNodeSelected(event);
			String type = ((BaseTreeNode) node).getType();
			String key = ((BaseTreeNode) node).getIdentifier();
			String name = ((BaseTreeNode) node).getDescription();
			getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
			getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
			getSession().setAttribute(WebConstants.MIGRATION_STEP8_NEXT, null);
			getSession().setAttribute(WebConstants.MIGRATION_STEP8_BACK, null);

			getSession().setAttribute(WebConstants.SESSION_NODE_TYPE, type);
			getSession().setAttribute(WebConstants.SESSION_BNFT_DEFN_ID, key);
			getSession().setAttribute(WebConstants.SESSION_BNFT_NAME, name);
			getRequest().setAttribute("RETAIN_Value", "");
		}//end if
	}

	//This method processes action for Standard Benefit click in the tree.
	public void processActionForStandardBenefit(ActionEvent event)
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
			String benefitComponentId = ((BaseTreeNode) node).getParent()
					.getIdentifier();
			String name = ((BaseTreeNode) node).getDescription();
			getSession().setAttribute(WebConstants.SESSION_NODE_TYPE, type);
			getSession().setAttribute(WebConstants.SESSION_BNFT_DEFN_ID, key);
			//note this .again setting type to name
			getSession().setAttribute(WebConstants.SESSION_BNFT_NAME, type);

			HttpSession session = getSession();

			getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
			getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
			getSession().setAttribute(WebConstants.MIGRATION_STEP8_NEXT, null);
			getSession().setAttribute(WebConstants.MIGRATION_STEP8_BACK, null);
			String standardBenName = ((BaseTreeNode) node).getDescription();
			String benefitComponentName = ((BaseTreeNode) node).getParent()
					.getDescription();
			String productName = ((BaseTreeNode) node).getParent().getParent()
					.getDescription();

			String description = node.getDescription();

			MigrationContract migrationContract = this
					.getMigrationContractSession().getMigrationContract();
			migrationContract.setBenefitCompName(benefitComponentName);
			migrationContract.setStdBenefitName(standardBenName);
			migrationContract.setProductName(productName);
			migrationContract.setStdBenefitId(Integer.parseInt(key));
			migrationContract.setBenefitComponentId(Integer
					.parseInt(benefitComponentId));
			this.getMigrationContractSession().setMigrationContract(
					migrationContract);
			getRequest().setAttribute("RETAIN_Value", "");
		}
	}

	
//	This method processes action for Standard Benefit click in the tree.
	public void processActionForBenefitComponentNotes(ActionEvent event)
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
			String benefitComponentId = ((BaseTreeNode) node).getParent()
					.getIdentifier();
			String name = ((BaseTreeNode) node).getDescription();
			getSession().setAttribute(WebConstants.SESSION_NODE_TYPE, type);
			getSession().setAttribute(WebConstants.SESSION_BNFT_DEFN_ID, key);
			//note this .again setting type to name
			getSession().setAttribute(WebConstants.SESSION_BNFT_NAME, type);

			HttpSession session = getSession();

			getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
			getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
			//String standardBenName = ((BaseTreeNode) node).getDescription();
			String benefitComponentName = ((BaseTreeNode) node).getDescription();
			String productName = ((BaseTreeNode) node).getParent()
					.getDescription();

			String description = node.getDescription();

			MigrationContract migrationContract = this
					.getMigrationContractSession().getMigrationContract();
			migrationContract.setBenefitCompName(benefitComponentName);
			//migrationContract.setStdBenefitName(standardBenName);
			migrationContract.setProductName(productName);
			//migrationContract.setStdBenefitId(Integer.parseInt(key));
			migrationContract.setBenefitComponentId(Integer
					.parseInt(key));
			this.getMigrationContractSession().setMigrationContract(
					migrationContract);
			getRequest().setAttribute("RETAIN_Value", "");
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getPages() {

		getSession().setAttribute(WebConstants.MESSAGE_LIST_STEP3, null);
		String type = (String) getSession().getAttribute(
				WebConstants.SESSION_NODE_TYPE);
		String key = (String) getSession().getAttribute(
				WebConstants.SESSION_BNFT_DEFN_ID);
		String name = (String) getSession().getAttribute(
				WebConstants.SESSION_BNFT_NAME);

		if (name.equals(WebConstants.STEP_GENERAL_INFO)) {
			getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
			getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
			MigrationGeneralInfoBackingBean migrationGeneralInfoBackingBean = (MigrationGeneralInfoBackingBean) FacesContext
					.getCurrentInstance().getApplication().createValueBinding(
							"#{migrationGeneralInfoBackingBean}").getValue(
							FacesContext.getCurrentInstance());
			return migrationGeneralInfoBackingBean.retrieveMigContractDetails();
		} else if (name.equals(WebConstants.STEP_DATESEGMENT)) {
			getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
			getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
			int stepCompleteNo = this.getMigrationContractSession()
					.getNavigationInfo().getStepCompleted();
			String option = this.getMigrationContractSession()
					.getMigrationContract().getOption();
			if (null != option && (option.equals("1") || option.equals("3"))) {
				this
						.setBreadCrumbTextLeft(WebConstants.MIGRATION_BREAD_CRUMB_STEP2);
				LegacyContractBackingBean legacyContractBackingBean = (LegacyContractBackingBean) getSession()
						.getAttribute(WebConstants.LEGACY_CONTRACT_BACKING_BEAN);
				return legacyContractBackingBean.goToStep2FromStep3();
			} else if (null != option
					&& (option.equals("0") || option.equals("2"))) {
				return WebConstants.EMPTY_STRING;
			} else {
				return WebConstants.EMPTY_STRING;
			}

		} else if (name.equals(WebConstants.STEP_PRICING_INFO)) {
			getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
			getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
			int stepCompleteNo = this.getMigrationContractSession()
					.getNavigationInfo().getStepCompleted();
			if (stepCompleteNo >= 3) {
				MigrationPricingInfoBackingBean migrationPricingInfoBackingBean = (MigrationPricingInfoBackingBean) FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{migrationPricingInfoBackingBean}").getValue(
								FacesContext.getCurrentInstance());
				return migrationPricingInfoBackingBean.showPricingInfoPage();
			} else {
				//put a message
				int numberInMessage = 0;
				numberInMessage = stepCompleteNo + 1;
				InformationalMessage inMessage = new InformationalMessage(
						WebConstants.NO_GENERALINFO_FILLED);
				inMessage.setParameters(new String[] { String
						.valueOf(numberInMessage) });
				messageList.add(inMessage);
				addAllMessagesToRequest(messageList);
				return WebConstants.EMPTY_STRING;
			}
		} else if (name.equals(WebConstants.STEP_PRODUCTS)) {
			getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
			getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
			int stepCompleteNo = this.getMigrationContractSession()
					.getNavigationInfo().getStepCompleted();
			if (stepCompleteNo >= 4) {
				MigrationProductInfoBackingBean migrationProductInfoBackingBean = (MigrationProductInfoBackingBean) FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{migrationProductInfoBackingBean}").getValue(
								FacesContext.getCurrentInstance());
				return migrationProductInfoBackingBean.showProductInfo();
			} else {
				//put a message
				int numberInMessage = 0;
				numberInMessage = stepCompleteNo + 1;
				InformationalMessage inMessage = new InformationalMessage(
						WebConstants.NO_GENERALINFO_FILLED);
				inMessage.setParameters(new String[] { String
						.valueOf(numberInMessage) });
				messageList.add(inMessage);
				addAllMessagesToRequest(messageList);
				getSession().setAttribute(WebConstants.MESSAGE_LIST_STEP3,
						messageList);
				return WebConstants.EMPTY_STRING;
			}
		} else if (name.equals(WebConstants.STEP_DENIAL_EXCL)) {
			getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
			getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
			int stepCompleteNo = this.getMigrationContractSession()
					.getNavigationInfo().getStepCompleted();
			if (stepCompleteNo >= 5) {
				MigrationContractRulesBackingBean migrationContractRulesBackingBean = (MigrationContractRulesBackingBean) FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{migrationContractRulesBackingBean}")
						.getValue(FacesContext.getCurrentInstance());
				return migrationContractRulesBackingBean
						.displayMigrationContractRules();
			} else {
				//put a message
				int numberInMessage = 0;
				numberInMessage = stepCompleteNo + 1;
				InformationalMessage inMessage = new InformationalMessage(
						WebConstants.NO_GENERALINFO_FILLED);
				inMessage.setParameters(new String[] { String
						.valueOf(numberInMessage) });
				messageList.add(inMessage);
				addAllMessagesToRequest(messageList);
				getSession().setAttribute(WebConstants.MESSAGE_LIST_STEP3,
						messageList);
				return WebConstants.EMPTY_STRING;
			}
		}

		//last condition
		else if (name.equals(WebConstants.STANDARD_BENEFITS_HEAD)) {
			int stepCompleteNo = this.getMigrationContractSession()
					.getNavigationInfo().getStepCompleted();
			if (stepCompleteNo >= 6) {
				ContrMigratProductMappingBackingBean contrMigratProductMappingBackingBean = (ContrMigratProductMappingBackingBean) FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{ContrMigratProductMappingBackingBean}")
						.getValue(FacesContext.getCurrentInstance());
				return contrMigratProductMappingBackingBean
						.getStandardBenefitMapping();
			} else {
				//put a message
				int numberInMessage = 0;
				numberInMessage = stepCompleteNo + 1;
				InformationalMessage inMessage = new InformationalMessage(
						WebConstants.NO_GENERALINFO_FILLED);
				inMessage.setParameters(new String[] { String
						.valueOf(numberInMessage) });
				messageList.add(inMessage);
				addAllMessagesToRequest(messageList);
				getSession().setAttribute(WebConstants.MESSAGE_LIST_STEP3,
						messageList);
				return WebConstants.EMPTY_STRING;
			}
		}
		else if (name.equals(WebConstants.STEP8_BENEFITS_HEAD)) {
			int stepCompleteNo = this.getMigrationContractSession()
					.getNavigationInfo().getStepCompleted();
			if (stepCompleteNo >= 7) {
				MigrateNotesBackingBean migrateNotesBackingBean = (MigrateNotesBackingBean) FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding(
								"#{migrateNotesBackingBean}")
						.getValue(FacesContext.getCurrentInstance());
				return migrateNotesBackingBean
						.loadMigrationNotesPage();
				//return "sample";
			} else {
				//put a message
				int numberInMessage = 0;
				numberInMessage = stepCompleteNo + 1;
				InformationalMessage inMessage = new InformationalMessage(
						WebConstants.NO_GENERALINFO_FILLED);
				inMessage.setParameters(new String[] { String
						.valueOf(numberInMessage) });
				messageList.add(inMessage);
				addAllMessagesToRequest(messageList);
				getSession().setAttribute(WebConstants.MESSAGE_LIST_STEP3,
						messageList);
				return WebConstants.EMPTY_STRING;
			}
		}
		else if (name.equals(WebConstants.STEP_REPORT_GEN)) {
			getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
			getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
			int stepCompleteNo = this.getMigrationContractSession()
					.getNavigationInfo().getStepCompleted();
			if (stepCompleteNo >= 8) {
				MigrationReportBackingBean migrationReportBackingBean = (MigrationReportBackingBean) FacesContext
						.getCurrentInstance().getApplication()
						.createValueBinding("#{migrationReportBackingBean}")
						.getValue(FacesContext.getCurrentInstance());
				return migrationReportBackingBean.showReportGenerationPage();
			} else {
				//put a message
				int numberInMessage = 0;
				numberInMessage = stepCompleteNo + 1;
				InformationalMessage inMessage = new InformationalMessage(
						WebConstants.NO_GENERALINFO_FILLED);
				inMessage.setParameters(new String[] { String
						.valueOf(numberInMessage) });
				messageList.add(inMessage);
				addAllMessagesToRequest(messageList);

				getSession().setAttribute(WebConstants.MESSAGE_LIST_STEP3,
						messageList);

				return WebConstants.EMPTY_STRING;
			}
		} else if (name.equals(WebConstants.STEP_CONFIRM_MIG)) {
			getSession().setAttribute(WebConstants.TREE_EXP_FLAG, null);
			getSession().setAttribute(WebConstants.STEP8_TREE_EXP_FLAG, null);
			int stepCompleteNo = this.getMigrationContractSession()
					.getNavigationInfo().getStepCompleted();
			if (stepCompleteNo >= 9) {
				return WebConstants.MIG_CONTRACT_STEP10;
			} else {
				//put a message
				int numberInMessage = 0;
				numberInMessage = stepCompleteNo + 1;
				InformationalMessage inMessage = new InformationalMessage(
						WebConstants.NO_GENERALINFO_FILLED);
				inMessage.setParameters(new String[] { String
						.valueOf(numberInMessage) });
				messageList.add(inMessage);
				addAllMessagesToRequest(messageList);
				getSession().setAttribute(WebConstants.MESSAGE_LIST_STEP3,
						messageList);
				return WebConstants.EMPTY_STRING;
			}
		} else {
			//for step 7 product mapping click
			return WebConstants.EMPTY_STRING;
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
		return WebConstants.EMPTY_STRING;
	}

	/**
	 * Returns the countMap
	 * 
	 * @return boolean countMap.
	 */
	public boolean isCountMap() {
		return countMap;
	}

	/**
	 * Sets the countMap
	 * 
	 * @param countMap.
	 */
	public void setCountMap(boolean countMap) {
		this.countMap = countMap;
	}

	/**
	 * Returns the countMapDelete
	 * 
	 * @return boolean countMapDelete.
	 */
	public boolean isCountMapDelete() {
		return countMapDelete;
	}

	/**
	 * Sets the countMapDelete
	 * 
	 * @param countMapDelete.
	 */
	public void setCountMapDelete(boolean countMapDelete) {
		this.countMapDelete = countMapDelete;
	}
}

