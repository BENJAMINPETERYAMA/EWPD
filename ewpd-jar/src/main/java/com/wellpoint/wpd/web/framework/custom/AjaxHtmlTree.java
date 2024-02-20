package com.wellpoint.wpd.web.framework.custom;

/**
 * @author u20622
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.ServletRequest;

import org.ajaxanywhere.AAUtils;
import org.apache.myfaces.custom.tree2.HtmlTree;

/**
 * MyFaces Tree2 with support added for AjaxAnywhere
 *
 */
public class AjaxHtmlTree extends HtmlTree {

	public final static String COMPONENT_TYPE = "com.wellpoint.wpd.web.framework.custom.AjaxTree";

	public final static String COMPONENT_FAMILY = "org.ajaxanywhere.AjaxAnywhere";

	private String ajaxZone;
	
	/**
	 * @see org.apache.myfaces.custom.tree2.UITreeData#getFamily()
	 */
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	public AjaxHtmlTree() {
		setRendererType("AjaxHtmlTreeRenderer");
	}

	/**
	 * @return Returns the ajaxZone.
	 */
	public String getAjaxZone() {
		if (ajaxZone != null)
			return ajaxZone;
		ValueBinding vb = getValueBinding("ajaxZone");
		return (vb == null) ? null : (String) vb.getValue(getFacesContext());
	}

	/**
	 * @param ajaxZone The ajaxZone to set.
	 */
	public void setAjaxZone(String ajaxZone) {
		this.ajaxZone = ajaxZone;
	}

	/**
	 * @see org.apache.myfaces.custom.tree2.UITreeData#toggleExpanded()
	 * 
	 */
	public void toggleExpanded() {
		super.toggleExpanded();
		String ajaxZone = getAjaxZone();
		if (ajaxZone != null)
			AAUtils.addZonesToRefresh((ServletRequest) getFacesContext()
					.getExternalContext().getRequest(), ajaxZone);
	}

	/**
	 * @see org.apache.myfaces.custom.tree2.HtmlTree#saveState(javax.faces.context.FacesContext)
	 */
	public Object saveState(FacesContext context) {
		return new Object[] { super.saveState(context), ajaxZone };
	}

	/**
	 * @see org.apache.myfaces.custom.tree2.HtmlTree#restoreState(javax.faces.context.FacesContext, java.lang.Object)
	 */
	public void restoreState(FacesContext context, Object state) {
		Object[] array = (Object[]) state;
		int index = -1;
		super.restoreState(context, array[++index]);
		ajaxZone = (String) array[++index];
	}
}
