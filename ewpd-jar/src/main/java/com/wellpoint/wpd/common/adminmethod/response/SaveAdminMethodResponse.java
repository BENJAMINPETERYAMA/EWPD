/*
 * Created on Sep 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.response;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.adminmethod.bo.AdminMethodBO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveAdminMethodResponse extends WPDResponse{

	private boolean success;
	
	AdminMethodBO adminMethodBO = new AdminMethodBO(); 
	
	private List resultList = new ArrayList(); 
	/**
	 * @return Returns the success.
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success The success to set.
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
    /**
     * Returns the adminMethodBO
     * @return AdminMethodBO adminMethodBO.
     */

    public AdminMethodBO getAdminMethodBO() {
        return adminMethodBO;
    }
    /**
     * Sets the adminMethodBO
     * @param adminMethodBO.
     */

    public void setAdminMethodBO(AdminMethodBO adminMethodBO) {
        this.adminMethodBO = adminMethodBO;
    }
    /**
     * Returns the resultList
     * @return List resultList.
     */

    public List getResultList() {
        return resultList;
    }
    /**
     * Sets the resultList
     * @param resultList.
     */

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }
}
