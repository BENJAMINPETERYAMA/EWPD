package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.vo.OOAMessageMappingVO;

public interface BXOutboundDataFeedHelperService {
	public List retriveNotApplicalbeMappingforBXOutboundDF(String status,
			String MappingType);

	public boolean manageDeltaNotApplicableDetails(Mapping existingMapping,
			Mapping updatedMapping, String itemIdentifier);

	public List<OOAMessageMappingVO> getOOAMessageMappings(String status,
			String entityName, String recordType);

	public boolean updateOOAMappingStatus(String status, String entityName,
			String recordType, List<Integer> OOAMappingSysIds);
}
