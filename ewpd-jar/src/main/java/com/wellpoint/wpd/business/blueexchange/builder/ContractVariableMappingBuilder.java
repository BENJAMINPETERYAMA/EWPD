/*
 * Created on May 10, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.blueexchange.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.blueexchange.adapter.ContractVariableMappingAdapterManager;
import com.wellpoint.wpd.common.blueexchange.bo.ContractVariableMappingBO;
import com.wellpoint.wpd.common.blueexchange.bo.VariableMappingBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractVariableMappingBuilder {
	
	
	/**
	 * method for retriving the contract variable mapping
	 * @param contractVariableMappingBO
	 * @return
	 * @throws SevereException
	 */

	public List retriveContractVariableMapping(ContractVariableMappingBO contractVariableMappingBO) throws SevereException {

		ContractVariableMappingAdapterManager contractVariableMappingAdapterManager =new ContractVariableMappingAdapterManager();
		try {
			List searchList = new ArrayList();
			searchList = contractVariableMappingAdapterManager.retrieveContractVariableMappingForDataFeed(contractVariableMappingBO);
			return searchList;
		} catch (AdapterException excp) {
			List errorParams = new ArrayList();
			String obj = excp.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieving SPSMapping Values in ContractVariableMappingBuilder",
					errorParams, excp);
		}
	
	}
	/**
	 * method for retriving the Not applicable contract variable mapping
	 * @param contractVariableMappingBO
	 * @return
	 * @throws SevereException
	 */
	public List retriveNAContractVariableMapping(ContractVariableMappingBO contractVariableMappingBO) throws SevereException{
		ContractVariableMappingAdapterManager contractVariableMappingAdapterManager =new ContractVariableMappingAdapterManager();
		try {
			List searchList = new ArrayList();
			searchList = contractVariableMappingAdapterManager.retrievenotApplicableContractVariableMapping(contractVariableMappingBO);
			return searchList;
		} catch (AdapterException excp) {
			List errorParams = new ArrayList();
			String obj = excp.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieving SPSMapping Values in ContractVariableMappingBuilder",
					errorParams, excp);
		}
	}
	
	/**
	 * method for retriving theupdating the status contract variable mapping
	 * @param contractVariableMappingBO
	 * @return
	 * @throws SevereException
	 */
	public boolean updateDataFeedStatus( ContractVariableMappingBO contractVariableMappingBO, String user) throws SevereException{
		ContractVariableMappingAdapterManager contractVariableMappingAdapterManager =new ContractVariableMappingAdapterManager();
		try {
						
				return contractVariableMappingAdapterManager.updateStausForDataFeed(contractVariableMappingBO,user);
			}
		catch (AdapterException excp) {
				List errorParams = new ArrayList();
				String obj = excp.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());
				throw new SevereException(
						"Exception occured in retrieving SPSMapping Values in ContractVariableMappingBuilder",
						errorParams, excp);
			}
		
			
		}

			
	}
