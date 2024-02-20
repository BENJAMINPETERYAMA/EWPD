/*
 * Created on Mar 8, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.adminmethod.tree;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.wellpoint.wpd.business.adminmethod.builder.AdminMethodBusinessObjectBuilder;
import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodValidationBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.web.adminmethod.AdminMethodTreeNode;

/**
 * @author U12238
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodDateSegmentCodedSPSTreeNodeBase extends
		AdminMethodTreeNode {

	public AdminMethodDateSegmentCodedSPSTreeNodeBase(String type,
			String identifier, String name, boolean leaf) {
		super(type, identifier, name, leaf);
	}

	/**
	 * Loads the date segments when its node is clicked
	 * 
	 * @return List
	 */
	protected List loadChildren() {

		Logger
				.logInfo("entered method loadChildren of ProductBenefitComponentTreeNodeBase");

		children = null;
		productId = this.getIdentifier();

		List dateSegmentList = new ArrayList();

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);

		String entityType = "contract";

		AdminMethodBusinessObjectBuilder adminMethodBusinessObjectBuilder = new AdminMethodBusinessObjectBuilder();
		// an instance of Standard Benefit BO is created
		AdminMethodValidationBO adminMethodValidationBO = new AdminMethodValidationBO();

		//checks if the identifier is null
		if (null == productId || "0".equals(productId))
			return children;

		// change for new enhancement -- Start
		adminMethodValidationBO.setEntitySysId(Integer.parseInt(productId));

		adminMethodValidationBO.setEntityType(entityType);
		// change for new enhancement -- End

		try {
			//fetches the dateSegmentList
			dateSegmentList = adminMethodBusinessObjectBuilder
					.getDateSegmentsForCodedSPS(adminMethodValidationBO);

		} catch (SevereException e) {
			Logger.logError(e);
		}

		children = new ArrayList();
		//the dateSegmentList is iterated to get the dateSegment details
		for (int i = 0; i < dateSegmentList.size(); i++) {
			adminMethodValidationBO = (AdminMethodValidationBO) dateSegmentList
					.get(i);
			String dateSegmentId = Integer.toString(adminMethodValidationBO
					.getEntitySysId())
					+ "~"
					+ Integer.toString(adminMethodValidationBO.getProductId());
			String dateSegmentName = adminMethodValidationBO.getEffectiveDate()
					+ "-" + adminMethodValidationBO.getExpiryDate();

			//dateSegmentList added as the children of Contract
			children.add(new AdminMethodCodedSPSTreeNodeBase(getModel(), this,
					"DateSegement", dateSegmentId, dateSegmentName, false));

		}
		return children;

	}

}