/*
 * WPDMessageTag.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework.custom;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: WPDMessageTag.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public class WPDMessageTag extends UIComponentTag {

    private String value;
    private static final String ERROR_MSG_COMP_TYPE = "ERROR_MSG_OUTPUT";
    private static final String ERROR_MSG_RENDER_TYPE = "ERROR_MSG_RENDERER";
    
    /**
     *  
     */
    public WPDMessageTag() {
        super();
    }

    public String getComponentType() {
        return ERROR_MSG_COMP_TYPE;
    }

    public String getRendererType() {
        return ERROR_MSG_RENDER_TYPE;
    }

    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        if (value != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Application app = context.getApplication();
            ValueBinding vb = app.createValueBinding(value);
            component.setValueBinding("value", vb);
        }
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

}