package com.wellpoint.ets.bx.mapping.repository;

import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;

public interface EbxStateTransitionRepository 
{

	int updateStatusInMstr(SpiderUMRuleMapping spiderUMRuleMapping);

	boolean isMappingBeingModified(String ruleId);

	boolean updateBeingModified(SpiderUMRuleMapping spiderUMRuleMapping);

}
