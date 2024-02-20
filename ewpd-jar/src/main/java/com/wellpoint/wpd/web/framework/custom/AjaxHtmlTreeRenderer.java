/*
 * AjaxHtmlTreeRenderer.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework.custom;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import javax.servlet.http.Cookie;

import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.HtmlTreeRenderer;
import org.apache.myfaces.custom.tree2.ToggleExpandedEvent;
import org.apache.myfaces.custom.tree2.TreeState;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;
import org.apache.myfaces.shared_tomahawk.renderkit.html.HTML;

/**
 * Copied from tomahawk HtmlTreeRenderer. </br>
 * Overriden the decode method to create ToogleEvent only for Node Expand/Collapse. 
 * As the page is not refreshing, for any action on the UI a ToogleExpand Event is fired.
 * To overcome this, this new renderer is created.
 * @author U19826
 *
 */
public class AjaxHtmlTreeRenderer extends Renderer {

    
	protected static final String TOGGLE_SPAN = "org.apache.myfaces.tree.TOGGLE_SPAN";
    protected static final String ROOT_NODE_ID = "0";

    private static final String NAV_COMMAND = "org.apache.myfaces.tree.NAV_COMMAND";
    private static final String ENCODING = "UTF-8";
    private static final String ATTRIB_DELIM = ";";
    private static final String ATTRIB_KEYVAL = "=";
    private static final String NODE_STATE_EXPANDED = "x";
    private static final String NODE_STATE_CLOSED = "c";
    private static final String SEPARATOR = String.valueOf(NamingContainer.SEPARATOR_CHAR);
    

    // see superclass for documentation
    public boolean getRendersChildren()
    {
        return true;
    }

    private void restoreStateFromCookies(FacesContext context, UIComponent component) {
        String nodeId = null;
        HtmlTree tree = (HtmlTree)component;
        TreeState state = tree.getDataModel().getTreeState();

        Map cookieMap = context.getExternalContext().getRequestCookieMap();
        Cookie treeCookie = (Cookie)cookieMap.get(component.getId());
        if (treeCookie == null || treeCookie.getValue() == null)
        {
            return;
        }

        String nodeState = null;
        Map attrMap = getCookieAttr(treeCookie);
        Iterator i = attrMap.keySet().iterator();
        while (i.hasNext())
        {
            nodeId = (String)i.next();
            nodeState = (String)attrMap.get(nodeId);

            if (NODE_STATE_EXPANDED.equals(nodeState))
            {
                if (!state.isNodeExpanded(nodeId))
                {
                    state.toggleExpanded(nodeId);
                }
            }
            else if (NODE_STATE_CLOSED.equals(nodeState))
            {
                if (state.isNodeExpanded(nodeId))
                {
                    state.toggleExpanded(nodeId);
                }
            }
        }
    }

    /**
     * Method overriden to create ToggleEvent only on Node expand/collapse.
     */
    public void decode(FacesContext context, UIComponent component)
    {
        super.decode(context, component);

        // see if one of the nav nodes was clicked, if so, then toggle appropriate node
        String nodeId = null;
        String nodeClicked = null;
        HtmlTree tree = (HtmlTree)component;

        if (tree.isClientSideToggle() && tree.isPreserveToggle())
        {
            restoreStateFromCookies(context, component);
        }
        else
        {
            nodeId = (String)context.getExternalContext().getRequestParameterMap().get(tree.getId() + SEPARATOR + NAV_COMMAND);
            nodeClicked = (String)context.getExternalContext().getRequestParameterMap().get("__LINK_TARGET__");
             
            if (nodeId == null || nodeId.equals(""))
            {
                return;
            }
            // WAS 6.0 Migration Changes  --Commented the below code Since WAS 7.0 returns __LINK_TARGET__ as null value
           /*
             if(nodeClicked != null && nodeClicked.substring(nodeClicked.lastIndexOf(":")).equals(":t2g"))
            	component.queueEvent(new ToggleExpandedEvent(component, nodeId));
            */
            component.queueEvent(new ToggleExpandedEvent(component, nodeId));
        } 
            
    }

    public void encodeBegin(FacesContext context, UIComponent component) throws IOException
    {
        // Fixes TOMAHAWK-437
        //HtmlTree tree = (HtmlTree)component;
        //if (!tree.getDataModel().getTreeState().isTransient()
        //        && tree.isClientSideToggle()
        //        && tree.isPreserveToggle())
        //{
        //    restoreStateFromCookies(context, component);
        //}

        // write javascript functions
        encodeJavascript(context, component);
    }

    /**
     * Renders the whole tree.  It generates a <code>&lt;span></code> element with an <code>id</code>
     * attribute if the component has been given an explicit ID.  The model nodes are rendered
     * recursively by the private <code>encodeNodes</code> method.
     *
     * @param context FacesContext
     * @param component The component whose children are to be rendered
     * @throws IOException
     */
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException
    {
    	Renderer baseRenderer=context.getRenderKit().getRenderer("org.apache.myfaces.HtmlTree2", "org.apache.myfaces.HtmlTree2");
    	baseRenderer.encodeChildren(context, component);
        
    }

    
   

    protected void beforeNodeEncode(FacesContext context, ResponseWriter out, HtmlTree tree)
        throws IOException
    {
        out.startElement(HTML.TABLE_ELEM, tree);
        out.writeAttribute(HTML.CELLPADDING_ATTR, "0", null);
        out.writeAttribute(HTML.CELLSPACING_ATTR, "0", null);
        out.writeAttribute(HTML.BORDER_ATTR, "0", null);
        out.startElement(HTML.TR_ELEM, tree);
    }

    protected void afterNodeEncode(FacesContext context, ResponseWriter out)
        throws IOException
    {
        out.endElement(HTML.TR_ELEM);
        out.endElement(HTML.TABLE_ELEM);
    }

    

    /**
     * Encodes any stand-alone javascript functions that are needed.  Uses either the extension filter, or a
     * user-supplied location for the javascript files.
     *
     * @param context FacesContext
     * @param component UIComponent
     * @throws IOException
     */
    private void encodeJavascript(FacesContext context, UIComponent component) throws IOException
    {
        // render javascript function for client-side toggle (it won't be used if user has opted for server-side toggle)
        String javascriptLocation = ((HtmlTree)component).getJavascriptLocation();
        AddResource addResource = AddResourceFactory.getInstance(context);
        if (javascriptLocation == null)
        {
            addResource.addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, HtmlTreeRenderer.class, "javascript/tree.js");
            addResource.addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, HtmlTreeRenderer.class, "javascript/cookielib.js");
        }
        else
        {
            addResource.addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, javascriptLocation + "/tree.js");
            addResource.addJavaScriptAtPosition(context, AddResource.HEADER_BEGIN, javascriptLocation + "/cookielib.js");
        }
    }

    /**
     * Get the appropriate image src location.  Uses the extension filter mechanism for images if no image
     * location has been specified.
     *
     * @param context The {@link FacesContext}.  NOTE: If <code>null</code> then context path information
     *    will not be used with extensions filter (assuming no imageLocation specified.)
     * @param component UIComponent
     * @param imageName The name of the image file to use.
     * @return The image src information.
     */
    private String getImageSrc(FacesContext context, UIComponent component, String imageName, boolean withContextPath)
    {
        String imageLocation = ((HtmlTree)component).getImageLocation();
        AddResource addResource = AddResourceFactory.getInstance(context);
        if (imageLocation == null)
        {
            return addResource.getResourceUri(context, HtmlTreeRenderer.class,
                                              "images/" + imageName, withContextPath);
        }
        else
        {
            return addResource.getResourceUri(context, imageLocation + "/" + imageName, withContextPath);
        }
    }

    /**
     * Helper method for getting the boolean value of an attribute.  If the attribute is not specified,
     * then return the default value.
     *
     * @param component The component for which the attributes are to be checked.
     * @param attributeName The name of the boolean attribute.
     * @param defaultValue The default value of the attribute (to be returned if no value found).
     * @return boolean
     */
    protected boolean getBoolean(UIComponent component, String attributeName, boolean defaultValue)
    {
        Boolean booleanAttr = (Boolean)component.getAttributes().get(attributeName);

        if (booleanAttr == null)
        {
            return defaultValue;
        }
        else
        {
            return booleanAttr.booleanValue();
        }
    }

    private Map getCookieAttr(Cookie cookie)
    {
        Map attribMap = new HashMap();
        try
        {
            String cookieValue = URLDecoder.decode(cookie.getValue(),ENCODING);
            String[] attribArray = cookieValue.split(ATTRIB_DELIM);
            for (int j = 0; j < attribArray.length; j++)
            {
                int index = attribArray[j].indexOf(ATTRIB_KEYVAL);
                String name = attribArray[j].substring(0, index);
                String value = attribArray[j].substring(index + 1);
                attribMap.put(name, value);
            }
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("Error parsing tree cookies", e);
        }
        return attribMap;
    }
}