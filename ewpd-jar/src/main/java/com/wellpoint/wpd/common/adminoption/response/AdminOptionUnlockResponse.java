/*
 * Created on Jan 15, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminoption.response;

import com.wellpoint.wpd.common.adminoption.vo.AdminOptionVO;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U14631
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminOptionUnlockResponse extends WPDResponse{
	
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
