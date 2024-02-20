/*
 * Created on Jun 4, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.repository;

import java.util.List;

import com.wellpoint.ets.bx.mapping.application.security.Lock;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;

/**
 * @author u20622
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface LockRepository {

	boolean insertLock(Lock lockObject);
	boolean updateLock(Lock lockObject);
	boolean deleteLock(Mapping mapping);
	List retrieveLock(String className, String keyId);
	
	List retrieveLock(String className, String keyId, String userName);
	/**
	 * @param mapping
	 * @return
	 */	
	}
