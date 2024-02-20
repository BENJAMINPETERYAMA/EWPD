/*
 * IndicativeMappingBusinessObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.indicativemapping.builder;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.indicativemapping.adapter.IndicativeMappingAdapterManager;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.indicativemapping.bo.IndicativeDetailBO;
import com.wellpoint.wpd.common.indicativemapping.bo.IndicativeMappingBO;
import com.wellpoint.wpd.common.referencemapping.bo.ReferenceMappingBO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class IndicativeMappingBusinessObjectBuilder {

	public boolean createIndicativeMapping(
			IndicativeMappingBO indicativeMappingBO) throws SevereException {
		IndicativeMappingAdapterManager indicativeMappingAdapterManager = new IndicativeMappingAdapterManager();
		boolean success = false;
		try {
			success = indicativeMappingAdapterManager
					.createIndicativeMapping(indicativeMappingBO);
		} catch (AdapterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}

	public boolean editIndicativeMapping(IndicativeMappingBO indicativeMappingBO)
			throws SevereException {
		boolean success = false;
		IndicativeMappingAdapterManager indicativeMappingAdapterManager = new IndicativeMappingAdapterManager();
		try {
			success = indicativeMappingAdapterManager.editIndicativeMapping(indicativeMappingBO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return success;
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
	public IndicativeMappingBO retrieveIndicativeMapping(
			IndicativeMappingBO indicativeMappingBO) throws SevereException {
		IndicativeMappingAdapterManager indicativeMappingAdapterManager = new IndicativeMappingAdapterManager();
		List searchResults = indicativeMappingAdapterManager
				.retrieveIndicativeMapping(indicativeMappingBO);
		IndicativeMappingBO mappingBO = (IndicativeMappingBO) searchResults
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
	public List searchIndicativeMapping(IndicativeMappingBO indicativeMappingBO)
			throws SevereException {
		IndicativeMappingAdapterManager indicativeMappingAdapterManager = new IndicativeMappingAdapterManager();
		List searchResults = indicativeMappingAdapterManager
				.searchIndicativeMapping(indicativeMappingBO);

		if (null != searchResults && searchResults.size() > 0) {
			for (int i = 0; i < searchResults.size(); i++) {
				IndicativeMappingBO mappingBO = (IndicativeMappingBO) searchResults
						.get(i);
				if (null != mappingBO.getIndSegDesc()
						&& mappingBO.getIndSegDesc().length() > 15) {
					mappingBO.setIndSegDesc((mappingBO.getIndSegDesc())
							.substring(0, 15)
							+ "...");
				}
			}
		}
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
	public boolean deleteIndicativeMapping(
			IndicativeMappingBO indicativeMappingBO, User user)
			throws SevereException {
		IndicativeMappingAdapterManager indicativeMappingAdapterManager = new IndicativeMappingAdapterManager();
		boolean success = true;
		try {
			indicativeMappingAdapterManager.deleteIndicativeMapping(
					indicativeMappingBO, user);
		}catch (Exception e) {		
			success = false;
		}
		return success;
	}

	/**
	 * This method return the indicative segment mapping corresponding to each
	 * indicative segment.
	 * 
	 * @return indicativeSegmentMappingList
	 * @throws SevereException
	 */
	public List retrieveIndicativeMappingsForDatafeed(String region) throws SevereException {

		IndicativeMappingAdapterManager indicativeMappingAdapterManager = new IndicativeMappingAdapterManager();
		return indicativeMappingAdapterManager
				.retrieveIndicativeMappingsForDatafeed(region);
	}
	/**
	 * This method return the indicative segment mapping corresponding to each
	 * indicative segment.
	 * 
	 * @return indicativeSegmentMappingList
	 * @throws SevereException
	 */
	public List<IndicativeMappingBO> retrieveIndicativeMappingsConfigurationForDatafeed(String region) throws SevereException {

		IndicativeMappingAdapterManager indicativeMappingAdapterManager = new IndicativeMappingAdapterManager();
		return indicativeMappingAdapterManager
				.retrieveIndicativeMappingsConfigurationForDatafeed(region);
	}

	// Added for Indicative Long Term Solution
	/**
	 * This method is added for Indicative Long Term Solution
	 * 
	 * @param indicativeMappingBO
	 * @return List - IndicativeMappings
	 * @throws SevereException
	 */
	public List<IndicativeDetailBO> exportIndicativeDetail(
			IndicativeDetailBO indicativeMappingBO) throws SevereException {
		IndicativeMappingAdapterManager indicativeMappingAdapterManager = new IndicativeMappingAdapterManager();
		return indicativeMappingAdapterManager
				.exportIndicativeDetail(indicativeMappingBO);

	}

	/**
	 * This method will process the data from excel file for import or test data for copy to prod
	 * 
	 * @param deletedIndicativeCodes
	 *            - List of indicative segment codes which are removed in the
	 *            uploaded excel sheet
	 * @param modifiedIndicativeCodes
	 *            - List of indicative segment codes which are modified in the
	 *            uploaded excel sheet
	 * @param newIndicativeCodes
	 *            - List of new indicative segment codes added in the uploaded
	 *            excel sheet
	 * @param newSequenceIndicativeCodes
	 *            - List of indicative segment sequence which need to be
	 *            modified
	 * @param user
	 *            - User
	 * @param segmentNumber
	 *            - Indicative Segment Number
	 * @param region
	 *            - Region which can be Test or Producation
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean processUploadIndicativeMapping(
			List<IndicativeDetailBO> deletedIndicativeCodes,
			List<IndicativeDetailBO> modifiedIndicativeCodes,
			List<IndicativeDetailBO> newIndicativeCodes,User user,
			String segmentNumber, String region) throws SevereException {	
		boolean isSuccess = true;
		long transactionId = -1;
		AdapterServicesAccess serviceAccessEWPDB = null;
		try {
			List list = beginTransaction();
			transactionId = (Long) list.get(0);
			serviceAccessEWPDB = (AdapterServicesAccess) list.get(1);
			isSuccess = backUpIndicativeSegment(user,segmentNumber,region);
			 if(isSuccess){
				 isSuccess = processDeletedIndicativeSegments(segmentNumber, region,
						 deletedIndicativeCodes, user);
				 if(isSuccess){
					 isSuccess = processModifiedIndicativeSegments(modifiedIndicativeCodes, user, region);
					 if(isSuccess){
							 isSuccess = processNewIndicativeSegments(newIndicativeCodes,user, region);
					 }
				 }
			 }
			if(!isSuccess){
				abortTransaction(serviceAccessEWPDB, transactionId);				
			}
			endTransaction(serviceAccessEWPDB, transactionId);
			return isSuccess;
		} catch (SevereException severeException) {
			severeException.printStackTrace();
			isSuccess = false;
			abortTransaction(serviceAccessEWPDB, transactionId);
			Logger.logError(severeException);
		}catch(Exception exception) {
			exception.printStackTrace();
			isSuccess = false;
			abortTransaction(serviceAccessEWPDB, transactionId);
			Logger.logError(exception);
		}
		return isSuccess;
	}

	/**
	 * It will backup the indicative segment in the defined backup table
	 * 
	 * @param user
	 *            - User
	 * @param segmentNumber
	 *            - Indicative Segment Number
	 * @param region
	 *            - Region
	 * @throws SevereException 
	 */
	private boolean backUpIndicativeSegment(User user, String segmentNumber, String region) throws SevereException {
		IndicativeMappingAdapterManager indicativeMappingAdapterManager = new IndicativeMappingAdapterManager();
		boolean isSuccess = true;
		isSuccess = indicativeMappingAdapterManager.backUpIndicativeSegment(user, segmentNumber, region);
		return isSuccess;
	}

	/**
	 * It will delete all the segments as per import/copytoprod segments
	 * @param indicativeSegment
	 * @param region
	 * @param deletedIndiSegments
	 * @param user
	 * @throws SevereException
	 */
	private boolean processDeletedIndicativeSegments(String indicativeSegment,
			String region, List<IndicativeDetailBO> deletedIndiSegments,
			User user) throws SevereException {
		boolean isSuccess = true;
		IndicativeMappingAdapterManager indicativeMappingAdapterManager = new IndicativeMappingAdapterManager();
		if (deletedIndiSegments != null && deletedIndiSegments.size() > 0) {			
			for (IndicativeDetailBO indiMappingBO : deletedIndiSegments) {
				indiMappingBO.setIndicativeRegion(region);
				if(isSuccess){
					isSuccess = indicativeMappingAdapterManager.deleteIndicativeDetail(indiMappingBO, user);
				}else{
					break;
				}
			}
		}
		return isSuccess;
	}

	/**
	 * It will modify all the segments as per import/copytoprod segments
	 * @param modifiedIndiSegsFrmExcel
	 * @return boolean
	 * @throws SevereException
	 * @throws AdapterException 
	 */
	private boolean processModifiedIndicativeSegments(
			List<IndicativeDetailBO> modifiedIndiSegsFrmExcel, User user, String region)
			throws SevereException, AdapterException {
		boolean isSuccess = true;
		IndicativeMappingAdapterManager indicativeMappingAdapterManager = new IndicativeMappingAdapterManager();
		if (modifiedIndiSegsFrmExcel != null
				&& modifiedIndiSegsFrmExcel.size() > 0) {			
			for (IndicativeDetailBO indiMappingBO : modifiedIndiSegsFrmExcel) {
				indiMappingBO.setIndicativeRegion(region);
				if(isSuccess){					
					indiMappingBO.setCreatedUser(user.getUserId());
					indiMappingBO.setLastChangeUser(user.getUserId());
					isSuccess = indicativeMappingAdapterManager.editIndicativeDetail(indiMappingBO);
				}else{
					break;
				}
			}
		}
		return isSuccess;
	}
/**
 * It will add all the new segments as per import/copytoprod segments
 * @param newIndiSegsFrmExcel
 * @return boolean 
 * @throws SevereException
 * @throws AdapterException 
 */
	private boolean processNewIndicativeSegments(List<IndicativeDetailBO> newIndiSegsFrmExcel,
			User user, String region)
			throws SevereException, AdapterException {
		boolean isSuccess = true;
		IndicativeMappingAdapterManager indicativeMappingAdapterManager = new IndicativeMappingAdapterManager();
		if (newIndiSegsFrmExcel != null && newIndiSegsFrmExcel.size() > 0) {			
			for (IndicativeDetailBO indiMappingBO : newIndiSegsFrmExcel) {
				indiMappingBO.setIndicativeRegion(region);
				if(isSuccess){
					indiMappingBO.setCreatedUser(user.getUserId());
					indiMappingBO.setLastChangeUser(user.getUserId());
					isSuccess = indicativeMappingAdapterManager.createIndicativeDetail(indiMappingBO);
				}else{
					break;
				}
			}
		}return isSuccess;
	}

	public List beginTransaction() throws SevereException {
		AdapterServicesAccess serviceAccessEWPDB = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		AdapterUtil.beginTransaction(serviceAccessEWPDB);
		AdapterUtil
				.logBeginTranstn(transactionId, this, "IndicativeDetailBO ");
		List list = new ArrayList();
		list.add(transactionId);
		list.add(serviceAccessEWPDB);
		return list;
	}

	public void endTransaction(AdapterServicesAccess serviceAccessEWPDB,
			long transactionId) throws SevereException {
		AdapterUtil.endTransaction(serviceAccessEWPDB);
		AdapterUtil.logTheEndTransaction(transactionId, this,
				"IndicativeDetailBO");
	}

	public void abortTransaction(AdapterServicesAccess serviceAccessEWPDB,
			long transactionId) {
		try {
			AdapterUtil.abortTransaction(serviceAccessEWPDB);
			AdapterUtil.logTheEndTransaction(transactionId, this,
					"IndicativeDetailBO");
		} catch (Exception e) {

		}
	}
	
	public List<ReferenceMappingBO> getIndicativeCodeDescriptionFromItem(String ctlgId) throws SevereException{
		IndicativeMappingAdapterManager indicativeMappingAdapterManager = new IndicativeMappingAdapterManager();
		List<ReferenceMappingBO> resultList = indicativeMappingAdapterManager.getDescriptionFromItem(ctlgId);
		return resultList;
	}

	// End for Indicative Long Term Solution

}