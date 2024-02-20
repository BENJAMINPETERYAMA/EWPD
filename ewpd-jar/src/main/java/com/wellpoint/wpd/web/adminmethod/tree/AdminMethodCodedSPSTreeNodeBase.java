
package com.wellpoint.wpd.web.adminmethod.tree;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.myfaces.custom.tree2.TreeModel;


import com.wellpoint.wpd.business.adminmethod.builder.AdminMethodBusinessObjectBuilder;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodValidationBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.web.adminmethod.AdminMethodTreeNode;

/**
 * @author U16012
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodCodedSPSTreeNodeBase extends AdminMethodTreeNode {

	private AdminMethodCodedSPSTreeNodeBase() {

	}

	/**
	 * 
	 * @param treeModel
	 * @param parent
	 * @param type
	 * @param identifier
	 * @param name
	 * @param leaf
	 */
	public AdminMethodCodedSPSTreeNodeBase(TreeModel treeModel,
			AdminMethodTreeNode parent, String type, String identifier,
			String name, boolean leaf) {
		super(treeModel, parent, type, identifier, name, leaf);
	}

	/**
	 * 
	 * @param type
	 * @param identifier
	 * @param name
	 * @param leaf
	 */
	public AdminMethodCodedSPSTreeNodeBase(String type, String identifier,
			String name, boolean leaf) {
		super(type, identifier, name, leaf);
	}
	/**
	 * Loads the Benefit Component when its node is
	 * clicked
	 * @return List
	 */
	protected List loadChildren() {

		Logger.logInfo("entered method loadChildren of ProductTreeNodeBase");

		children = new ArrayList();
		productId = this.getIdentifier().split("~")[0];
		List benefitComponents = null;

		AdminMethodValidationBO adminMethodValidationBO = new AdminMethodValidationBO();
		//an instance of benefitComponent BO is created

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);


		String entityType = "contract";


		if (null == productId || "0".equals(productId)) {
			return benefitComponents;
		}

		adminMethodValidationBO.setEntitySysId(Integer.parseInt(productId));
		adminMethodValidationBO.setEntityType(entityType);
		AdminMethodBusinessObjectBuilder adminMethodBusinessObjectBuilder = new AdminMethodBusinessObjectBuilder();

		try {

			//fetches the benefit component details
			benefitComponents = adminMethodBusinessObjectBuilder
					.getBenefitComponentsCodedSPS(adminMethodValidationBO);

		} catch (SevereException e) {
			Logger.logError(e);
		}

		if (null != benefitComponents) {
			for (int i = 0; i < benefitComponents.size(); i++) {
				adminMethodValidationBO = (AdminMethodValidationBO) benefitComponents
						.get(i);

				String benefitComponentId = Integer
						.toString(adminMethodValidationBO.getBenefitComSysId());

				benefitComponentId = benefitComponentId + "~"
						+ adminMethodValidationBO.getAdminMethodId() + "~"
						+ adminMethodValidationBO.getBenefitAdminSysId();

				children
						.add(new AdminMethodBenefitComponentCodedSPSTreeNodeBase(
								getModel(), this, "Benefit-Component",
								benefitComponentId, adminMethodValidationBO
										.getBenefitComName(), false));

			}
		}

		//the benefitComponent List is iterated to get the benefit Components

		return children;
	}
}