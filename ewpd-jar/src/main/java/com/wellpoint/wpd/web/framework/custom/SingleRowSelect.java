/*
 * SingleRowSelect.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework.custom;

import java.io.IOException;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SingleRowSelect extends UIInput {
    private String groupName;

    public static final String COMPONENT_TYPE = "proj.kontrol.SingleRowSelect";

    public static final String COMPONENT_FAMILY = "proj.kontrol.SingleRowSelect";

    public SingleRowSelect() {
        setRendererType(null);

    }

    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void restoreState(FacesContext context, Object state) {

        Object[] values = (Object[]) state;
        super.restoreState(context, values[0]);
        groupName = (String) values[1];

    }

    public Object saveState(FacesContext context) {
        Object[] values = new Object[2];
        values[0] = super.saveState(context);
        values[1] = groupName;
        return values;
    }

    public void encodeBegin(FacesContext context) throws IOException {

        if (!isRendered()) {
            return;
        }

//        Object value = getAttributes().get("value");

        ResponseWriter writer = context.getResponseWriter();

        writer
                .write("<input class=\"selectOneRadio\" type=\"radio\" ondblclick=\"javascript:clearRadioButton(this);\" name=\"");
        writer.write(getGroupName());
        writer.write("\"");

        writer.write(" id=\"");
        String clientId = getClientId(context);
        writer.write(clientId);
        writer.write("\"");

        writer.write(" value=\"");
        writer.write(clientId);
        writer.write("\"");

        if (isRowSelected(this)) {
            writer.write(" checked");
        }

        writer.write(" \\>");

    }

    private boolean isRowSelected(UIComponent component) {
        UIInput input = (UIInput) component;
        Object value = input.getValue();

        int currentRowIndex = getCurrentRowIndex(input);

        return (value != null)
                && (currentRowIndex == ((Integer) value).intValue());

    }

    private int getCurrentRowIndex(UIComponent uicomponent) {
        UIData uidata = findUIData(this);
        if (uidata == null)
            return -1;
        else
            return uidata.getRowIndex();
    }

    protected UIData findUIData(UIComponent uicomponent) {
        if (uicomponent == null)
            return null;
        if (uicomponent instanceof UIData)
            return (UIData) uicomponent;
        else
            return findUIData(uicomponent.getParent());
    }

    public void decode(FacesContext context) {

        if (!isRendered()) {
            return;
        }

        Map requestMap = context.getExternalContext().getRequestParameterMap();
        String postedValue = null;

        if (requestMap.containsKey(getGroupName())) {
            postedValue = (String) requestMap.get(getGroupName());
            String clientId = getClientId(context);
            if (clientId.equals(postedValue)) {

                String[] postedValueArray = postedValue.split(":");
                String rowIndex = postedValueArray[postedValueArray.length - 2];

                Integer newValue = Integer.valueOf(rowIndex);
                //the value to go in conversion&validation
                setSubmittedValue(newValue);
                setValid(true);
            }
        }
    }

}