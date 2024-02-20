/*
 * Created on Sep 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.request;

import java.util.List;

import com.wellpoint.wpd.common.adminmethod.vo.AdminMethodVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * Request for admin Method Save
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SaveAdminMethodRequest extends WPDRequest{

	public void validate()throws ValidationException{
		
	}
	List adminMethods;
	
	//AdminMethodLocateCriteria adminMethodLocateCriteria = new AdminMethodLocateCriteria();
	
	AdminMethodVO adminMethodVO = new AdminMethodVO();
	
	private int administrationId;
	
	private int stdBenId;
	
	List origAdminMethodsList;
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
    /**
     * Returns the administrationId
     * @return int administrationId.
     */

    public int getAdministrationId() {
        return administrationId;
    }
    /**
     * Sets the administrationId
     * @param administrationId.
     */

    public void setAdministrationId(int administrationId) {
        this.administrationId = administrationId;
    }

	/**
	 * @return Returns the adminMethodVO.
	 */
	public AdminMethodVO getAdminMethodVO() {
		return adminMethodVO;
	}
	/**
	 * @param adminMethodVO The adminMethodVO to set.
	 */
	public void setAdminMethodVO(AdminMethodVO adminMethodVO) {
		this.adminMethodVO = adminMethodVO;
	}
	/**
	 * @return Returns the stdBenId.
	 */
	public int getStdBenId() {
		return stdBenId;
	}
	/**
	 * @param stdBenId The stdBenId to set.
	 */
	public void setStdBenId(int stdBenId) {
		this.stdBenId = stdBenId;
	}
	public List getOrigAdminMethodsList() {
		return origAdminMethodsList;
	}
	public void setOrigAdminMethodsList(List origAdminMethodsList) {
		this.origAdminMethodsList = origAdminMethodsList;
	}
	
	
}
