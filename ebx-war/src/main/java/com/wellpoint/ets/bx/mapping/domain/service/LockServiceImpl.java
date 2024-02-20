/*
 * Created on May 12, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.application.security.Lock;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.MappingLockedByAnotherUserException;
import com.wellpoint.ets.bx.mapping.repository.LockRepository;

/**
 * @author u23641
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LockServiceImpl implements LockService {

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.application.security.LockDao#insertLock(com.wellpoint.ets.bx.mapping.application.security.Lock)
	 */
	private LockRepository lockRepository;
	
	private boolean insertLock(Lock lockObject) {
		return lockRepository.insertLock(lockObject);
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.application.security.LockDao#updateLock(com.wellpoint.ets.bx.mapping.application.security.Lock)
	 */
	private boolean updateLock(Lock lockObject) {
		return lockRepository.updateLock(lockObject);
	}


	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.LockService#retrieveLock(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public boolean aquireLock(Mapping mapping)  {
		List lockList = lockRepository.retrieveLock(Mapping.class.getName(), mapping.getVariable().getVariableId());
		Lock lockObj = null;
		String lockedByUser = "";
		if(null != lockList && !lockList.isEmpty()){
			lockObj = (Lock)lockList.get(0);
			lockedByUser = lockObj.getLockUserId();
		}
		String createdUser = "";
		if (lockList == null || lockList.size()==0) {
			createdUser = mapping.getUser().getLastUpdatedUserName();
			if(createdUser.equals("")){
				createdUser = mapping.getUser().getCreatedUserName();
			}
			Lock lock = new Lock(Mapping.class.getName(), mapping.getVariable().getVariableId(),createdUser);
			insertLock(lock);
		}else {
			throw new MappingLockedByAnotherUserException("The variable ID "+mapping.getVariable().getVariableId()+" is locked by the user '" +lockedByUser.toUpperCase()+ "' for editing.");
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.LockService#deleteLock(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public boolean deleteLock(Mapping mapping)  {
		return lockRepository.deleteLock(mapping);
	}

	public LockRepository getLockRepository() {
		return lockRepository;
	}

	public void setLockRepository(LockRepository lockRepository) {
		this.lockRepository = lockRepository;
	}

	public boolean validateUserLock(Mapping mapping) {
		List lockedByLoggedInUser=lockRepository.retrieveLock(Mapping.class.getName(), mapping.getVariable().getVariableId(), mapping.getUser().getLastUpdatedUserName());
		
		if (lockedByLoggedInUser != null && lockedByLoggedInUser.size()>0) {				
			Lock lock = new Lock(this.getClass().getName(),mapping.getVariable().getVariableId(),mapping.getUser().getLastUpdatedUserName());
			updateLock(lock);
		} else {
			aquireLock(mapping);
		}
		return true;
	}
	
	
}
