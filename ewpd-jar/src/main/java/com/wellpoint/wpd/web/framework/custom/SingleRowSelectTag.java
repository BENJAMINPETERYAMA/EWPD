/*
 * SingleRowSelectTag.java
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
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SingleRowSelectTag extends UIComponentTag {

    private String groupName;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    public String getComponentType() {

        return "singleRow";
    }

    public String getRendererType() {
        return null;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void release() {
        super.release();
        groupName = null;
    }

    protected void setProperties(UIComponent component) {

        super.setProperties(component);
        UIInput singleInputRowSelect = (UIInput) component;
        singleInputRowSelect.getAttributes().put("groupName", groupName);

        if (getValue() != null) {

            if (isValueReference(getValue())) {
                FacesContext context = FacesContext.getCurrentInstance();
                Application app = context.getApplication();
                ValueBinding binding = app.createValueBinding(getValue());
                singleInputRowSelect.setValueBinding("value", binding);

            }

        }

    }

}