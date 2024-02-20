/*
 * IndicativeMappingAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.indicativemapping.adapter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.indicativemapping.bo.IndicativeDetailBO;
import com.wellpoint.wpd.common.indicativemapping.bo.IndicativeDetailBackUpBO;
import com.wellpoint.wpd.common.indicativemapping.bo.IndicativeMappingBO;
import com.wellpoint.wpd.common.referencemapping.bo.ReferenceMappingBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class IndicativeMappingAdapterManager {

	public boolean createIndicativeMapping(
			IndicativeMappingBO indicativeMappingBO) throws AdapterException {

		boolean success = false;
		try {			
			indicativeMappingBO.setCreatedTimestamp(new Date());			
			indicativeMappingBO.setLastUpdatedTimestamp(new Date());
			AdapterUtil.performInsert(indicativeMappingBO, indicativeMappingBO.getCreatedUser());
			success = true;
		} catch (SevereException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}

	public boolean editIndicativeMapping(IndicativeMappingBO indicativeMappingBO)
			throws AdapterException {

		boolean success = false;
		try {
			AdapterUtil.performUpdate(indicativeMappingBO, indicativeMappingBO
					.getLastUpdatedUser());
			success = true;

		} catch (SevereException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;

	}

	/**
	 * This method will retrieve the Indicative Mapping.
	 * 
	 * 
	 * @param indicativeMappingBO
	 * @return
	 * @throws SevereException
	 */
	public List retrieveIndicativeMapping(
			IndicativeMappingBO indicativeMappingBO) throws SevereException {
		SearchResults searchResults = null;
		List retrieveResultsList = null;
		HashMap refValSet = new HashMap();
		refValSet.put("indicativeSegmentCode", indicativeMappingBO
				.getIndicativeSegmentCode());
		refValSet.put("spsParameterCode", indicativeMappingBO
				.getSpsParameterCode());
		refValSet.put("benefit", indicativeMappingBO.getBenefit());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				IndicativeMappingBO.class.getName(),
				"retrieveIndicativeMapping", refValSet,
				BusinessConstants.RESULT_SET_SIZE);

		searchResults = AdapterUtil.performSearch(searchCriteria);

		retrieveResultsList = searchResults.getSearchResults();
		return retrieveResultsList;
	}

	/**
	 * 
	 * This method will delete the Indicative Mapping
	 * 
	 * @param indicativeMappingBO
	 * @param user
	 * @throws SevereException
	 */

	public void deleteIndicativeMapping(
			IndicativeMappingBO indicativeMappingBO, User user)
			throws SevereException {

		AdapterUtil.performRemove(indicativeMappingBO, user.getUserId());
	}

	/**
	 * 
	 * This method will search the Indicative Mappings based on the criteria
	 * such as "Indicative" , "SPS Parameter", "Benefit Name"
	 * 
	 * @param indicativeMappingBO
	 * @return
	 * @throws SevereException
	 */

	public List searchIndicativeMapping(IndicativeMappingBO indicativeMappingBO)
			throws SevereException {

		SearchResults searchResults = null;
		List locateResultsList = null;
		HashMap refValSet = new HashMap();
		if (indicativeMappingBO.getIndicativeList() != null)
			refValSet.put("indicativeList", indicativeMappingBO
					.getIndicativeList());
		if (indicativeMappingBO.getSpsList() != null)
			refValSet.put("spsList", indicativeMappingBO.getSpsList());
		if (indicativeMappingBO.getBenefitList() != null)
			refValSet.put("benefitList", indicativeMappingBO.getBenefitList());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				IndicativeMappingBO.class.getName(), "searchIndicativeMapping",
				refValSet, BusinessConstants.RESULT_SET_SIZE);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		locateResultsList = searchResults.getSearchResults();
		return locateResultsList;
	}

	/**
	 * This method return the indicative segment mapping corresponding to each
	 * indicative segment.
	 * 
	 * @return indicativeSegmentMappingList
	 * @throws SevereException
	 */
	public List<IndicativeMappingBO> retrieveIndicativeMappingsForDatafeed(String region) throws SevereException {
		SearchResults searchResults = null;
		HashMap<String, String> refValSet = new HashMap<String, String>();
		refValSet.put("indicativeRegion",region);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				IndicativeMappingBO.class.getName(),
				"retrieveIndicativeMappingsForDatafeed", refValSet);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
	
	/**
	 * This method return the indicative segment configuration for Datafeed corresponding to each
	 * indicative segment.
	 * 
	 * @return indicativeSegmentMappingList
	 * @throws SevereException
	 */
	public List<IndicativeMappingBO> retrieveIndicativeMappingsConfigurationForDatafeed(String region) throws SevereException {
		SearchResults searchResults = null;
		HashMap<String, String> refValSet = new HashMap<String, String>();
		refValSet.put("indicativeRegion",region);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				IndicativeMappingBO.class.getName(),
				"indicativeMappingFileFormatConfiguration", refValSet);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * This method is added for Indicative Long Term Solution
	 * 
	 * @param indicativeMappingBO
	 * @return IndicativeSegmentList
	 * @throws SevereException
	 */

	public List<IndicativeDetailBO> exportIndicativeDetail(
			IndicativeDetailBO indicativeMappingBO) throws SevereException {
		SearchResults searchResults = null;
		HashMap refValSet = new HashMap();
		refValSet.put("indicativeSegment",
				indicativeMappingBO.getIndicativeSegment());
		refValSet.put("indicativeRegion",
				indicativeMappingBO.getIndicativeRegion());
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				IndicativeDetailBO.class.getName(),
				"exportIndicativeDetails", refValSet);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * This method is added for backup indicative segment codes
	 * 
	 * @param user
	 * @param segmentNumber
	 * @param region
	 * @throws SevereException
	 */
	public boolean backUpIndicativeSegment(User user, String segmentNumber,String region) throws SevereException {
		boolean success = false;
		IndicativeDetailBackUpBO indicativeDetailBackUpBO = new IndicativeDetailBackUpBO();
		indicativeDetailBackUpBO.setBackUpUser(user.getUserId());
		indicativeDetailBackUpBO.setIndicativeSegmentNumber(segmentNumber);
		indicativeDetailBackUpBO.setIndicativeRegion(region.toUpperCase());
		AdapterUtil.performInsert(indicativeDetailBackUpBO, indicativeDetailBackUpBO.getBackUpUser());
		success = true;
		return success;
	}
	
	public boolean createIndicativeDetail(
			IndicativeDetailBO indicativeDetailBO) throws AdapterException, SevereException {

		boolean success = false;
			AdapterUtil.performInsert(indicativeDetailBO, indicativeDetailBO.getCreatedUser());
			success = true;
		return success;
	}

	public boolean editIndicativeDetail(IndicativeDetailBO indicativeDetailBO)
			throws AdapterException, SevereException {
		boolean success = false;
		AdapterUtil.performUpdate(indicativeDetailBO, indicativeDetailBO
				.getLastChangeUser());
		success = true;
		return success;
	}
	
	/**
	 * 
	 * This method will delete the Indicative Mapping
	 * 
	 * @param indicativeMappingBO
	 * @param user
	 * @throws SevereException
	 */

	public boolean deleteIndicativeDetail(
			IndicativeDetailBO indicativeDetailBO, User user)
			throws SevereException {
		boolean success = false;
		AdapterUtil.performRemove(indicativeDetailBO, user.getUserId());
		success = true;
		return success;
	}
	
	public List<ReferenceMappingBO> getDescriptionFromItem(String ctlgId) throws SevereException{
		SearchResults searchResults = null;
		HashMap refValSet = new HashMap();
		refValSet.put("catalogId",
				ctlgId);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ReferenceMappingBO.class.getName(),
				"getItemCodes", refValSet);
		searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
		}
	}