/*
 * PopupBuisinessObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.popup.builder;

import java.util.List;

import com.wellpoint.wpd.business.popup.adapter.PopupAdapterManager;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.popup.bo.PopupFilterBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class PopupBuisinessObjectBuilder {
	/*
	 * This method call adpater manager and returns a list of result for pop up load 
	 * @ param PopupFilterBO
	 * @ return popupSearchlist -List
	 * @throws SevereException
	 */
	public List locatePopupResult(PopupFilterBO popupBO) throws SevereException{
		 Logger.logInfo("PopupBuisinessObjectBuilder - Entering method locatePopupResult(PopupFilterBO)");
		PopupAdapterManager adapterManager = new PopupAdapterManager();
		List popupSearchlist = adapterManager.getSearchResults(popupBO);
		return popupSearchlist;
	}

}
