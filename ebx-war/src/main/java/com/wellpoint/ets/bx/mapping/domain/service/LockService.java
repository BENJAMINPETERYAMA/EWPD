/*
 * Created on May 12, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface LockService {
	
    boolean deleteLock(Mapping mapping) ;
    boolean aquireLock(Mapping mapping);
    boolean validateUserLock(Mapping mapping);
}
