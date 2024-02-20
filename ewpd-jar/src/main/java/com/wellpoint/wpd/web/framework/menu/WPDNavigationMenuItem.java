/*
 * WPDNavigationMenuItem.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary 
 * information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose 
 * or use Confidential Information without the
 * express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.web.framework.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.myfaces.custom.navmenu.NavigationMenuItem;
import com.wellpoint.wpd.common.framework.security.domain.User;

/**
 * The WPDNavigationMenuItem represents various Menu Items.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: WPDNavigationMenuItem.java 41505 2008-01-09 14:23:12Z U15427 $
 */
public class WPDNavigationMenuItem extends NavigationMenuItem {

    private boolean isAuthorized = false;

    private List children = null;

    private WPDNavigationMenuItem parent;

    /**
     * 
     * @param label
     * @param action
     * @param user
     * @param module
     * @param task
     */
    public WPDNavigationMenuItem(String label, String action, User user,
            String module, String task) {
        super(label, action);
        if (null != user && module != null) {
            isAuthorized = user.isAuthorized(module, task);
        }
        if (module == null) {
            isAuthorized = true;
        }

    }

    /**
     * Takes a list of WPDNavigationMenuItems as children.
     * 
     * @param items
     */
    public void setNavigationMenuItems(List items) {
        if (null != items && items.size() != 0) {
            List tempItems = new ArrayList();
            WPDNavigationMenuItem item = null;
            Iterator iterator = items.iterator();
            while (iterator.hasNext()) {
                item = (WPDNavigationMenuItem) iterator.next();
                if (item.isAuthorized()) {
                    item.parent = this;
                    tempItems.add(item);
                }
            }
            if (tempItems.size() > 0) {
                super.setNavigationMenuItems(tempItems);
                children = tempItems;
            } else {
                if (this.parent != null && this.parent.children != null) {
                    List siblings = this.parent.children;
                    siblings.remove(this);
                    this.parent.setNavigationMenuItems(siblings);
                }
                isAuthorized = false;
            }
        } else {
            isAuthorized = false;
        }
    }

    /**
     * @return Returns the isAuthorized.
     */
    public boolean isAuthorized() {
        return isAuthorized;
    }

    /**
     * @param isAuthorized
     *            The isAuthorized to set.
     */
    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }
}