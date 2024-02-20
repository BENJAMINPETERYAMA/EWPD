package com.wellpoint.ets.bx.mapping.domain.service;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.entity.SpiderUMRuleMapping;

public interface EbxStateTransitionsService {
	
	int transformToScheduledToTest(SpiderUMRuleMapping spiderUMRuleMapping);

	int notApplicable(SpiderUMRuleMapping spiderUMRuleMapping);

	boolean transformToEditableState(SpiderUMRuleMapping spiderUMRuleMapping);

	int transformToScheduledToProd(SpiderUMRuleMapping spiderUMRuleMapping);

	int transformToPreviousPublishedMapping(SpiderUMRuleMapping spiderUMRuleMapping);

	int transformToApplicable(SpiderUMRuleMapping spiderUMRuleMapping);
	
}
