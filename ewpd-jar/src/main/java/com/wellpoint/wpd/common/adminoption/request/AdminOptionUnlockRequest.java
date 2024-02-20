/*
 * Created on Jan 15, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminoption.request;

import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author U14631
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminOptionUnlockRequest extends WPDRequest{
	
	
	 /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {

    }
    private AdminOptionVO adminOptionVO;

	/**
	 * @return Returns the adminOptionVO.
	 */
	public AdminOptionVO getAdminOptionVO() {
		return adminOptionVO;
	}
	/**
	 * @param adminOptionVO The adminOptionVO to set.
	 */
	public void setAdminOptionVO(AdminOptionVO adminOptionVO) {
		this.adminOptionVO = adminOptionVO;
	}
}
