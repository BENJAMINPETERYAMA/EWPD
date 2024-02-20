/*
 * Created on May 20, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;

/**
 * @author u22093
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface StateTransitionService {

		boolean transformToBeingModified(Mapping mapping);
		
		int transformToScheduledToTest(Mapping mapping);
		
		int notApplicable(Mapping mapping);
		
		int approve(Mapping mapping);
		
		int transformToPreviousPublishedMapping(Mapping mapping);

		Mapping publishMapping(Mapping mapping,String variableId);
		
		List<Mapping> publishMapping(int inClauseSize, int batchSize,List<String> variableList,List<Mapping> mapping);
		
		int transferMappingToTestRegion();
		int transferMappingToTestRegion(Mapping mapping); 
		
		boolean transformToEditableState(Mapping mapping, Mapping retrieveMapping);
}
