package com.wellpoint.wpd.web.framework.custom;

/**
 * @author u20622
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

import javax.faces.component.UIComponent;
import org.apache.myfaces.custom.tree2.TreeTag;

public class AjaxTreeTag extends TreeTag {
	private String ajaxZone;

	  public String getComponentType()
	  {
	    return AjaxHtmlTree.COMPONENT_TYPE;
	  }
	  
	  public String getRendererType() {
		return ("AjaxHtmlTreeRenderer");
	}
	  
	/**
	 * @param ajaxZone
	 *            The ajaxZone to set.
	 */
	public void setAjaxZone(String ajaxZone) {
		this.ajaxZone = ajaxZone;
	}

	/**
	 * @see org.apache.myfaces.custom.tree2.TreeTag#setProperties(javax.faces.component.UIComponent)
	 */

	protected void setProperties(UIComponent component) {
		super.setProperties(component);
		if (ajaxZone != null)
			setStringProperty(component, "ajaxZone", ajaxZone);
	}
}
