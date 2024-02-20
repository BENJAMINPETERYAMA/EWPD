/*
 * Created on Jul 20, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.referencemapping.builder;

import java.util.List;

import com.wellpoint.wpd.business.referencemapping.adapter.ReferenceMappingAdapterManager;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.referencemapping.bo.ReferenceMappingBO;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class ReferenceMappingBusinessObjectBuilder {

	public boolean createReferenceMapping(ReferenceMappingBO referenceMappingBO)
			throws SevereException {

		ReferenceMappingAdapterManager referenceMappingAdapterManager = new ReferenceMappingAdapterManager();
		boolean status = false;
		try {
			status = referenceMappingAdapterManager
					.createReferenceMapping(referenceMappingBO);

		} catch (SevereException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	public boolean editReferenceMapping(ReferenceMappingBO referenceMappingBO)
			throws SevereException {

		ReferenceMappingAdapterManager referenceMappingAdapterManager = new ReferenceMappingAdapterManager();
		boolean status = false;
		try {
			status = referenceMappingAdapterManager
					.editReferenceMapping(referenceMappingBO);

		} catch (SevereException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;

	}

	/**
	 * 
	 * This method will invoke the Adapter Manager to retrieve the Indicative
	 * Mapping
	 * 
	 * @param indicativeMappingBO
	 * @return
	 * @throws SevereException
	 */
	public ReferenceMappingBO retrieveReferenceMapping(
			ReferenceMappingBO referenceMappingBO) throws SevereException {

		ReferenceMappingAdapterManager referenceMappingAdapterManager = new ReferenceMappingAdapterManager();
		List searchResults = referenceMappingAdapterManager
				.retrieveReferenceMapping(referenceMappingBO);

		ReferenceMappingBO mappingBO = (ReferenceMappingBO) searchResults
				.get(0);
		return mappingBO;

	}

	/**
	 * 
	 * This method will invoke the Adapter Manager to search the Indicative
	 * Mapping
	 * 
	 * 
	 * @param indicativeMappingBO
	 * @return
	 * @throws SevereException
	 */
	public List searchReferenceMapping(ReferenceMappingBO referenceMappingBO)
			throws SevereException {
		ReferenceMappingAdapterManager referenceMappingAdapterManager = new ReferenceMappingAdapterManager();
		List searchResults = referenceMappingAdapterManager
				.searchReferenceMapping(referenceMappingBO);

		return searchResults;

	}

	/**
	 * This method will invoke the Adapter Manager to delete the Indicative
	 * Mapping
	 * 
	 * 
	 * @param indicativeMappingBO
	 * @param user
	 * @throws SevereException
	 */
	public void deleteReferenceMapping(ReferenceMappingBO referenceMappingBO,
			User user) throws SevereException {

		ReferenceMappingAdapterManager referenceMappingAdapterManager = new ReferenceMappingAdapterManager();
		referenceMappingAdapterManager.deleteReferenceMapping(
				referenceMappingBO, user);

	}

}