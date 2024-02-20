/*
 * Created on Mar 29, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.web.benefitcomponent;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import com.wellpoint.wpd.web.framework.WPDBackingBean;


/**
 * Listener class for benefit hierarchy
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitHierarchyListener extends WPDBackingBean implements javax.faces.event.ActionListener{

	/* (non-Javadoc)
	 * @see javax.faces.event.ActionListener#processAction(javax.faces.event.ActionEvent)
	 */
	public void processAction(ActionEvent event) throws AbortProcessingException {
		 UIComponent component = (UIComponent) event.getSource();
		 HtmlCommandButton button = (HtmlCommandButton)component;
         String buttonValue =(String)button.getValue();
         this.getSession().setAttribute("HierarchyToBeDeleted",buttonValue);
	}
	/**
	* Constructor
	*/	
	public BenefitHierarchyListener(){
	}
}
