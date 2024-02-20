/*
 * Created on Sep 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveAdminMethodsResponse extends WPDResponse{

    private List adminMethods;

    /**
     * @return Returns the adminMethods.
     */
    public List getAdminMethods() {
        return adminMethods;
    }
    /**
     * @param adminMethods The adminMethods to set.
     */
    public void setAdminMethods(List adminMethods) {
        this.adminMethods = adminMethods;
    }
}
