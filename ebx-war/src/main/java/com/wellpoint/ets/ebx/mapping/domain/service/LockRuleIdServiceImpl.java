package com.wellpoint.ets.ebx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.application.security.Lock;
import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.exception.MappingLockedByAnotherUserException;
import com.wellpoint.ets.bx.mapping.domain.service.LockService;
import com.wellpoint.ets.bx.mapping.repository.LockRepository;

public class LockRuleIdServiceImpl implements LockService {
	

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.application.security.LockDao#insertLock(com.wellpoint.ets.bx.mapping.application.security.Lock)
	 */
	private LockRepository lockRuleIdRepository;
	
	private boolean insertLock(Lock lockObject) {
		return lockRuleIdRepository.insertLock(lockObject);
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.application.security.LockDao#updateLock(com.wellpoint.ets.bx.mapping.application.security.Lock)
	 */
	private boolean updateLock(Lock lockObject) {
		return lockRuleIdRepository.updateLock(lockObject);
	}


	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.LockService#retrieveLock(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public boolean aquireLock(Mapping mapping)  {
		List lockList = lockRuleIdRepository.retrieveLock(Mapping.class.getName(), mapping.getRule().getHeaderRuleId());
		String lockedByUser = "";
		if(null != lockList && !lockList.isEmpty()){
			Lock lockObj = (Lock)lockList.get(0);
			lockedByUser = lockObj.getLockUserId();
		}
		String createdUser = "";
		if (lockList == null || lockList.size()==0) {
			createdUser = mapping.getUser().getLastUpdatedUserName();
			if(createdUser.equals("")){
				createdUser = mapping.getUser().getCreatedUserName();
			}
			Lock lock = new Lock(Mapping.class.getName(), mapping.getRule().getHeaderRuleId(),createdUser);
			insertLock(lock);
		}else {
			throw new MappingLockedByAnotherUserException("The mapping with Rule ID "+mapping.getRule().getHeaderRuleId()+" is locked by the user '" +lockedByUser.toUpperCase()+ "' for editing.");
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.ets.bx.mapping.domain.service.LockService#deleteLock(com.wellpoint.ets.bx.mapping.domain.entity.Mapping)
	 */
	public boolean deleteLock(Mapping mapping)  {
		return lockRuleIdRepository.deleteLock(mapping);
	}

	public boolean validateUserLock(Mapping mapping) {
		List lockedByLoggedInUser=lockRuleIdRepository.retrieveLock(Mapping.class.getName(), mapping.getRule().getHeaderRuleId(), mapping.getUser().getLastUpdatedUserName());
		
		if (lockedByLoggedInUser != null && lockedByLoggedInUser.size()>0) {				
			Lock lock = new Lock(this.getClass().getName(),mapping.getRule().getHeaderRuleId(),mapping.getUser().getLastUpdatedUserName());
			updateLock(lock);
		} else {
			aquireLock(mapping);
		}
		return true;
	}

	public LockRepository getLockRuleIdRepository() {
		return lockRuleIdRepository;
	}

	public void setLockRuleIdRepository(LockRepository lockRuleIdRepository) {
		this.lockRuleIdRepository = lockRuleIdRepository;
	}
	
}
