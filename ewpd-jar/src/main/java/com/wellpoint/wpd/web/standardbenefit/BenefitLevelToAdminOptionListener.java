/*
 * Created on Sep 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.standardbenefit;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import com.wellpoint.wpd.web.framework.WPDBackingBean;

/**
 * Listener class for benefit level to admin option.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitLevelToAdminOptionListener extends WPDBackingBean implements javax.faces.event.ActionListener{

    
	/** Method for process action
	 * @param event
	 * @throws AbortProcessingException
	 */
	public void processAction(ActionEvent event) throws AbortProcessingException {
		   UIComponent component = (UIComponent) event.getSource();
			 HtmlCommandButton button = (HtmlCommandButton)component;
	        String buttonValue =button.getValue().toString();
	        this.getSession().setAttribute("selectedAdminOption",buttonValue);
		
	}

}
