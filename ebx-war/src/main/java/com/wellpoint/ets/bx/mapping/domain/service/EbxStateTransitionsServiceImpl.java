package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;
import com.wellpoint.ets.bx.mapping.repository.EbxStateTransitionRepository;
import com.wellpoint.ets.bx.mapping.repository.MappingRepository;
import com.wellpoint.ets.bx.mapping.repository.StateTransitionRepository;
import com.wellpoint.ets.bx.mapping.repository.UmRuleMappingRepository;
import com.wellpoint.ets.ebx.referencedata.vo.SpiderUMRuleMappingVO;

public class EbxStateTransitionsServiceImpl implements EbxStateTransitionsService 
{
	
	private UmRuleMappingRepository umRuleMappingRepository;
	private EbxStateTransitionRepository ebxStateTransitionRepository;
	private static Logger log = Logger.getLogger(EbxStateTransitionsServiceImpl.class);
	
	
	
    public UmRuleMappingRepository getUmRuleMappingRepository() {
		return umRuleMappingRepository;
	}

	public void setUmRuleMappingRepository(UmRuleMappingRepository umRuleMappingRepository) {
		this.umRuleMappingRepository = umRuleMappingRepository;
	}

	public EbxStateTransitionRepository getEbxStateTransitionRepository() {
		return ebxStateTransitionRepository;
	}

	public void setEbxStateTransitionRepository(EbxStateTransitionRepository ebxStateTransitionRepository) {
		this.ebxStateTransitionRepository = ebxStateTransitionRepository;
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		EbxStateTransitionsServiceImpl.log = log;
	}



    public int transformToScheduledToTest(SpiderUMRuleMapping spiderUMRuleMapping){
		
		int status = 0;
		
		spiderUMRuleMapping.setUmStatus(DomainConstants.STATUS_SCHEDULED_TO_TEST);
		status = ebxStateTransitionRepository.updateStatusInMstr(spiderUMRuleMapping);
		String id  = (spiderUMRuleMapping.getUmRuleIdSystemId()).toString();
		Long sysId= Long.valueOf(id);
		umRuleMappingRepository.persistNewLogForMapping(spiderUMRuleMapping, sysId);
		return status;
	}
    
    public int transformToScheduledToProd(SpiderUMRuleMapping spiderUMRuleMapping){
		
		int status = 0;
		
		spiderUMRuleMapping.setUmStatus(DomainConstants.STATUS_SCHEDULED_TO_PRODUCTION);
		status = ebxStateTransitionRepository.updateStatusInMstr(spiderUMRuleMapping);
		String id  = (spiderUMRuleMapping.getUmRuleIdSystemId()).toString();
		Long sysId= Long.valueOf(id);
		umRuleMappingRepository.persistNewLogForMapping(spiderUMRuleMapping, sysId);
	
		return status;
	}
    
    public int notApplicable(SpiderUMRuleMapping spiderUMRuleMapping) {
		int status = 0;
		
		spiderUMRuleMapping.setUmStatus(DomainConstants.STATUS_NOT_APPLICABLE);
		status = ebxStateTransitionRepository.updateStatusInMstr(spiderUMRuleMapping);
		String id  = (spiderUMRuleMapping.getUmRuleIdSystemId()).toString();
		Long sysId= Long.valueOf(id);
		umRuleMappingRepository.persistNewLogForMapping(spiderUMRuleMapping, sysId);
		//ebxStateTransitionRepository.updateBeingModifiedFlag(spiderUMRuleMapping);
		
	 return status;
    }
  
    public boolean transformToEditableState(SpiderUMRuleMapping spiderUMRuleMapping){
		
		boolean mappingResult = true;
		int mapping = 0;
		String ruleId = spiderUMRuleMapping.getUmRuleId();
		if (ebxStateTransitionRepository.isMappingBeingModified(ruleId)) {
			spiderUMRuleMapping.setUmStatus(DomainConstants.STATUS_BEING_MODIFIED);
			mapping = ebxStateTransitionRepository.updateStatusInMstr(spiderUMRuleMapping);			
		}		
		else{	
			spiderUMRuleMapping.setUmStatus(DomainConstants.STATUS_BUILDING);
			mapping = ebxStateTransitionRepository.updateStatusInMstr(spiderUMRuleMapping);					
		}
		if(mapping==1)
		{
		  mappingResult=true;
		}else {
			mappingResult=false;
		}
		
        return mappingResult;
    }
    
    public int transformToPreviousPublishedMapping(SpiderUMRuleMapping spiderUMRuleMapping){
		int status = 0;
		
		spiderUMRuleMapping.setUmStatus("PUBLISHED");
		ebxStateTransitionRepository.updateStatusInMstr(spiderUMRuleMapping);
		
		status = 1;
		return status;
		
	}
    
    public int transformToApplicable(SpiderUMRuleMapping spiderUMRuleMapping) {
		int status = 0;
		
		status = ebxStateTransitionRepository.updateStatusInMstr(spiderUMRuleMapping);
		String id  = (spiderUMRuleMapping.getUmRuleIdSystemId()).toString();
		Long sysId= Long.valueOf(id);
		umRuleMappingRepository.persistNewLogForMapping(spiderUMRuleMapping, sysId);
		
	    return status;
    }
}
