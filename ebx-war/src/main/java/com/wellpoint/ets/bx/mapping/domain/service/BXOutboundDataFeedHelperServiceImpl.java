package com.wellpoint.ets.bx.mapping.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;
import com.wellpoint.ets.bx.mapping.domain.vo.OOAMessageMappingVO;

public class BXOutboundDataFeedHelperServiceImpl implements
		BXOutboundDataFeedHelperService {

	private BXOutboundDataFeedHelperRepository bxoutboundDataFeedHelperRepository;
	private static Logger logger = Logger
			.getLogger(BXOutboundDataFeedHelperServiceImpl.class);

	public boolean manageDeltaNotApplicableDetails(Mapping existingMapping,
			Mapping updatedMapping, String itemIdentifier) {
		boolean isDeltaTableUpdated = false;
		if (null == existingMapping && null != updatedMapping) {
			if (null != updatedMapping.getVariableMappingStatus()
					&& DomainConstants.STATUS_NOT_APPLICABLE
							.equals(updatedMapping.getVariableMappingStatus())) {
				// Case 1: Unmapped is made as Not Applicable
				// Insert into Delta Table
				isDeltaTableUpdated = bxoutboundDataFeedHelperRepository
						.insertDeltaNotApplicableMapping(updatedMapping,
								itemIdentifier);
			}
		}
		if (null != existingMapping && null != updatedMapping) {

			if (DomainConstants.STATUS_NOT_APPLICABLE.equals(updatedMapping
					.getVariableMappingStatus())
					&& !DomainConstants.STATUS_NOT_APPLICABLE
							.equals(existingMapping.getVariableMappingStatus())) {
				// Case 2: When a Normal Mapping is made as Not-Applicable
				// Insert into Delta Table

				isDeltaTableUpdated = bxoutboundDataFeedHelperRepository
						.insertDeltaNotApplicableMapping(updatedMapping,
								itemIdentifier);

			}
			if (DomainConstants.STATUS_NOT_APPLICABLE.equals(existingMapping
					.getVariableMappingStatus())
					&& !DomainConstants.STATUS_NOT_APPLICABLE
							.equals(updatedMapping.getVariableMappingStatus())) {
				// System.out.println("Case 3: When a Not Applicable Mapping is Updated");
				// System.out.println("Delete from Delta Table");
				logger.info("Case 3: When a Not Applicable Mapping is Updated");
				logger.info("Delete from Delta Table");
				List deleteList = new ArrayList();
				deleteList.add(updatedMapping);
				isDeltaTableUpdated = bxoutboundDataFeedHelperRepository
						.deleteDeltaNotApplicableMapping(deleteList,
								itemIdentifier);

			}
		}
		return isDeltaTableUpdated;
	}

	public BXOutboundDataFeedHelperRepository getBxoutboundDataFeedHelperRepository() {
		return bxoutboundDataFeedHelperRepository;
	}

	public void setBxoutboundDataFeedHelperRepository(
			BXOutboundDataFeedHelperRepository bxoutboundDataFeedHelperRepository) {
		this.bxoutboundDataFeedHelperRepository = bxoutboundDataFeedHelperRepository;
	}

	public List retriveNotApplicalbeMappingforBXOutboundDF(String status,
			String MappingType) {
		List deltaNAMappingIds = bxoutboundDataFeedHelperRepository
				.retrieveDeltaNotApplicableMappingforDatafeed(status,
						MappingType);
		bxoutboundDataFeedHelperRepository
				.updateDeltaNotApplicableMappingDetails(deltaNAMappingIds,
						MappingType, status);
		bxoutboundDataFeedHelperRepository
				.deleteProcessedDeltaMappings(MappingType);
		return deltaNAMappingIds;
	}

	public List<OOAMessageMappingVO> getOOAMessageMappings(String status,
			String entityName, String recordType) {
		List<OOAMessageMappingVO> deletedOOAMessages = bxoutboundDataFeedHelperRepository
				.getOOAMessageMappings(status, entityName, recordType);
		return deletedOOAMessages;
	}

	public boolean updateOOAMappingStatus(String status, String entityName,
			String recordType, List<Integer> OOAMappingSysIds) {
		return bxoutboundDataFeedHelperRepository.updateOOAMappingStatus(
				status, entityName, recordType, OOAMappingSysIds);
	}
}
