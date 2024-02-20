package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.vo.OOAMessageMappingVO;

public interface BXOutboundDataFeedHelperRepository {

	public List retrieveDeltaNotApplicableMappingforDatafeed(String status,
			String itemIdentifier);

	public boolean updateDeltaNotApplicableMappingDetails(List mappingsList,
			String itemIdentifier, String status);

	public boolean deleteDeltaNotApplicableMapping(List mappingsList,
			String itemIdentifier);

	public boolean insertDeltaNotApplicableMapping(Mapping mapping,
			String itemIdentifier);

	public boolean deleteProcessedDeltaMappings(String itemIdentifier);

	public List<OOAMessageMappingVO> getOOAMessageMappings(String status,
			String entityName, String recordType);

	public boolean updateOOAMappingStatus(String status, String entityName,
			String recordType, List<Integer> OOAMappingSysIds);
}
