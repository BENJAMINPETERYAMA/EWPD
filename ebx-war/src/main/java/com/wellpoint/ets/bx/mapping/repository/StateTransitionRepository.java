/*
 * Created on May 24, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.bx.mapping.repository;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;

/**
 * @author u22093
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface StateTransitionRepository {

	void updateMstrToBeingModified(Mapping mapping);
	int updateStatusInMstr(Mapping mapping);
	int updateStatusInTemp(Mapping mapping);
	boolean rollBackTempMapping(Mapping mapping);
	boolean rollBackTempMapping(int batchSize, List<Mapping> mappingList);
	boolean rollBackMstrMapping(Mapping mapping);
	void updateBeingModifiedFlag(Mapping mapping);
	int updateStatusMstrForDatafeed();
	int updateStatusTempForDatafeed();
	int updateStatusMstrForProdDatafeed();
	int updateStatusTempForProdDatafeed();
	int updateStatusMstrForDatafeed(Mapping mapping);
	int updateStatusTempForDatafeed(Mapping mapping);
}
