/*
 * Created on Jul 20, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.referencemapping.adapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.referencemapping.bo.ReferenceMappingBO;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReferenceMappingAdapterManager {

	HashMap itemSet;

	HashMap termSet;

	HashMap qualifierSet;

	/*
	 * Create a mapping 
	 */
	public boolean createReferenceMapping(ReferenceMappingBO referenceMappingBO)
			throws SevereException {
		boolean suceess = false;
		try {

			AdapterUtil.performInsert(referenceMappingBO, referenceMappingBO
					.getCreatedUser());

			suceess = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return suceess;
	}

	/*
	 * Reference mapping edit
	 */
	public boolean editReferenceMapping(ReferenceMappingBO referenceMappingBO)
			throws SevereException {

		boolean suceess = false;
		try {

			AdapterUtil.performUpdate(referenceMappingBO, referenceMappingBO
					.getCreatedUser());

			suceess = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return suceess;
	}

	/**
	 * This method will retrieve the Indicative Mapping.
	 * 
	 * 
	 * @param indicativeMappingBO
	 * @return
	 * @throws SevereException
	 */
	public List retrieveReferenceMapping(ReferenceMappingBO referenceMappingBO)
			throws SevereException {
		SearchResults searchResults = null;
		List retrieveResultsList = null;
		HashMap refValSet = new HashMap();

		refValSet.put("referenceId", referenceMappingBO.getReferenceId());
		refValSet.put("type", referenceMappingBO.getType());
		refValSet.put("term", referenceMappingBO.getTerm());
		refValSet.put("qualifier", referenceMappingBO.getQualifier());
		refValSet.put("pva", referenceMappingBO.getPva());
		refValSet.put("datatype", Integer.toString(referenceMappingBO
				.getDatatype()));

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ReferenceMappingBO.class.getName(),
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

	public void deleteReferenceMapping(ReferenceMappingBO referenceMappingBO,
			User user) throws SevereException {

	  AdapterUtil.performRemove(referenceMappingBO, user.getUserId());
		
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

	public List searchReferenceMapping(ReferenceMappingBO referenceMappingBO)
			throws SevereException {

		SearchResults searchResults = null;
		List locateResultsList = null;

		HashMap refValSet = new HashMap();

		if (referenceMappingBO.getReferenceList() != null)
			refValSet.put("referenceList", referenceMappingBO
					.getReferenceList());
		if (referenceMappingBO.getTermList() != null)
			refValSet.put("termList", referenceMappingBO.getTermList());

		if (referenceMappingBO.getTypeList() != null)
			refValSet.put("typeList", referenceMappingBO.getTypeList());

		if (referenceMappingBO.getQualifierList() != null)
			refValSet.put("qualifierList", referenceMappingBO
					.getQualifierList());
		if (referenceMappingBO.getPvaList() != null)
			refValSet.put("pvaList", referenceMappingBO.getPvaList());

		if (referenceMappingBO.getDataTypeList() != null)
			refValSet.put("dataTypeList", referenceMappingBO.getDataTypeList());

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ReferenceMappingBO.class.getName(), "SearchReferenceMapping",
				refValSet, BusinessConstants.RESULT_SET_SIZE);

		searchResults = AdapterUtil.performSearch(searchCriteria);
		locateResultsList = searchResults.getSearchResults();

		ReferenceMappingBO bo;
		for (Iterator itr = locateResultsList.iterator(); itr.hasNext();) {

			bo = (ReferenceMappingBO) itr.next();

			if ((null != bo.getTerm()) && (bo.getTerm() != "")
					&& (bo.getTerm().length() > 0)
					&& (bo.getTerm().indexOf(',') > 0)) {
				if (termSet == null) {
					termSet = getItemMap(1934);
				}
				populateDscrTerm(bo);

			}

			if ((null != bo.getQualifier()) && (bo.getQualifier() != "")
					&& (bo.getQualifier().length() > 0)
					&& (bo.getQualifier().indexOf(',') > 0)) {

				if (qualifierSet == null) {
					qualifierSet = getItemMap(1935);
				}
				populateDscrQlfr(bo);

			}

		}

		return locateResultsList;

	}

	/**
	 * @param bo
	 * For populating the description for aggregate Terms
	 */
	private void populateDscrTerm(ReferenceMappingBO bo) {

		String[] term = bo.getTerm().split(",");
		StringBuffer termDesc = new StringBuffer();

		for (int i = 0; i < term.length; i++) {
			termDesc.append(termSet.get(term[i])).append(',');

		}
		termDesc.deleteCharAt(termDesc.length() - 1);

		bo.setTermDesc(termDesc.toString());
	}

	/**
	 * @param bo
	 * For populating the description for aggregate Qualifiers
	 */

	private void populateDscrQlfr(ReferenceMappingBO bo) {
		// TODO Auto-generated method stub

		String[] term = bo.getQualifier().split(",");
		StringBuffer qlfrDesc = new StringBuffer();

		for (int i = 0; i < term.length; i++) {

			qlfrDesc.append(qualifierSet.get(term[i])).append(',');
		}
		qlfrDesc.deleteCharAt(qlfrDesc.length() - 1);

		bo.setQualDesc(qlfrDesc.toString());
	}

	/**
	 * @return
	 */
	private HashMap getItemMap(int catalogId) {
		// TODO Auto-generated method stub

		itemSet = new HashMap();

		HashMap criteria = new HashMap();
		criteria.put("catalogId", new Integer(catalogId));

		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ReferenceMappingBO.class.getName(), "getItemCodes", criteria,
				BusinessConstants.RESULT_SET_SIZE);
		SearchResults searchResults = null;
		try {
			searchResults = AdapterUtil.performSearch(searchCriteria);
		} catch (SevereException e) {

			e.printStackTrace();
		}
		;

		List locateList = searchResults.getSearchResults();

		ReferenceMappingBO bo;

		for (Iterator itr = locateList.iterator(); itr.hasNext();) {

			bo = (ReferenceMappingBO) itr.next();
			itemSet.put(bo.getReferenceId(), bo.getDescription());

		}

		return itemSet;
	}

}