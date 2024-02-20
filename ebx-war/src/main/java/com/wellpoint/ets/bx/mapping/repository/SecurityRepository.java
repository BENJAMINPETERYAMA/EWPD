/*
 * Created on Jun 1, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.repository;

import java.util.List;

import com.wellpoint.ets.bx.mapping.application.security.RoleBO;




/**
 * @author u20622
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface SecurityRepository {

	/**
	 * @param id
	 * @return
	 */
	List getModuleBO(long id);

	/**
	 * @param moduleId
	 * @param roleId
	 * @return
	 */
	List getTaskBO(long moduleId, long roleId);

	/**
	 * @param roleBO
	 */
	RoleBO getRole(RoleBO roleBO);
	
	boolean isDataFeedRunning();
}
