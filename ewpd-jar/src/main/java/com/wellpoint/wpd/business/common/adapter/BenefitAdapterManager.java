/*
 * BenefitAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.common.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.benefitlevel.adapter.BenefitlevelAdapterManager;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLevelLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitQualifierBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitTermBO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.contract.bo.ContractBenefitHeadings;
import com.wellpoint.wpd.common.contract.bo.ContractQuesitionnaireBO;
import com.wellpoint.wpd.common.contract.bo.ContractTierQuesitionnaireBO;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.override.benefit.bo.EntityBenefitAdministration;
import com.wellpoint.wpd.common.override.benefit.bo.ProductBenefitCustomizedBO;
import com.wellpoint.wpd.common.override.benefit.bo.ProductStructureBenefitCustomizedBO;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;
import com.wellpoint.wpd.common.product.bo.HideAdminOptionBO;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.productstructure.bo.EntityBenefitAdministrationPSBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBenefitAdministration;
import com.wellpoint.wpd.util.TimeHandler;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitAdapterManager {

	//**Benefit level/line hide
	public List getAllHiddenBenefitLines(String entityType, int entitySysId,
			int benefitId, int benefitComponentId) throws SevereException,
			AdapterException {	
		
		TimeHandler th = new TimeHandler();
	    Logger.logInfo(th.startPerfLogging("U23914_Benefit_Component_Coverage","BenefitAdapterManager","getAllHiddenBenefitLines"));
	    		
		SearchResults searchResults = null;
		HashMap map = new HashMap();
		
		try {
			if (entitySysId <= 0 || benefitId <= 0 || benefitComponentId <= 0)
				throw new IllegalArgumentException(
						"One of the parameters [entitySysId, benefitId, benefitComponentId] is invalid");

			if (entityType.equalsIgnoreCase("BENEFITCOMP")) {
				// map.put("entityType",entityType);
				map.put("entitySysId", new Integer(entitySysId));
				map.put("benefitSysId", new Integer(benefitId));
				map.put("benefitComponentId", new Integer(benefitComponentId));
				// map.put("primaryEntityType","ATTACHCOMP");
				SearchCriteria searchCriteria = AdapterUtil
						.getAdapterSearchCriteria(BenefitLine.class.getName(),
								"getAllHiddenBenefitLines", map);
				searchResults = AdapterUtil.performSearch(searchCriteria);
				//Fix for Aggregate Qualifier start
				//searchBenefitTermsDescForCorrespondingBenefitLevel(searchResults.getSearchResults());
				//searchBenefitQualifiersDescForCorrespondingBenefitLevel(searchResults.getSearchResults());
				//Fix for Aggregate Qualifier End
			}
		} catch (Exception ex) {

			throw new AdapterException(
					"Exception occured in getAllHiddenBenefitLines entityType method in BenefitAdapterManager",
					ex);
		}
		Logger.logInfo(th.endPerfLogging());
		return searchResults.getSearchResults();
	}

	//**End

	public List getAllOverridedBenefitLines(String entityType, int entitySysId,
			int benefitId, int benefitComponentId, String benefitLevelHideFlag,
			String benefitLineHideFlag) throws SevereException,
			AdapterException {
		
		TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23914_Benefit_Component_Coverage","BenefitAdapterManager","getAllOverridedBenefitLines"));
				
		SearchResults searchResults = null;

	    HashMap map = new HashMap();
		try{
		if (entitySysId <= 0 || benefitId <= 0 || benefitComponentId <= 0)
			throw new IllegalArgumentException(
					"One of the parameters [entitySysId, benefitId, benefitComponentId] is invalid");
		
		//**Benefit level/line hide
		if (entityType.equalsIgnoreCase("BENEFITCOMP")) {
			// map.put("entityType",entityType);
			map.put("entitySysId", new Integer(entitySysId));
			map.put("benefitSysId", new Integer(benefitId));
			map.put("benefitComponentId", new Integer(benefitComponentId));
			map.put("primaryEntityType", "ATTACHCOMP");
			SearchCriteria searchCriteria = AdapterUtil
					.getAdapterSearchCriteria(BenefitLine.class.getName(),
							"getVisibleBenefitLines", map);
			
			searchResults = AdapterUtil.performSearch(searchCriteria);
			
			//Fix for Aggregate Qualifier start
			//searchBenefitTermsDescForCorrespondingBenefitLevel(searchResults.getSearchResults());
			//searchBenefitQualifiersDescForCorrespondingBenefitLevel(searchResults.getSearchResults());
			//Fix for Aggregate Qualifier End
		}
		//**End
		else if (entityType.equalsIgnoreCase("product")) {
			map.put("entityType", entityType);
			map.put("entitySysId", new Integer(entitySysId));
			map.put("benefitSysId", new Integer(benefitId));
			map.put("benefitComponentId", new Integer(benefitComponentId));
			map.put("primaryEntityType", "ATTACHPRODUCT");
			if (null != benefitLevelHideFlag
					&& !"".equals(benefitLevelHideFlag))
				map.put("levelHideFlag", benefitLevelHideFlag);
			else
				map.put("levelHideFlag", null);
			if (null != benefitLineHideFlag && !"".equals(benefitLineHideFlag))
				map.put("lineHideFlag", benefitLineHideFlag);
			else
				map.put("lineHideFlag", null);
			SearchCriteria searchCriteria = null;
				if(isPvaValidationNeeded (entitySysId,"product")){
					searchCriteria = AdapterUtil
							.getAdapterSearchCriteria(BenefitLine.class.getName(),
									"getAllBenefitLines", map);
					}else{
						
						searchCriteria = AdapterUtil
						.getAdapterSearchCriteria(BenefitLine.class.getName(),
								"getAllBenefitLinesWithoutPvaValidation", map);
					}

			searchResults = AdapterUtil.performSearch(searchCriteria);
			
		}else if (entityType.equalsIgnoreCase("Criteria")) {
			map.put("entityType", entityType);
			map.put("prodSysId", new Integer(entitySysId));
			map.put("benefitSysId", new Integer(benefitId));
			map.put("benefitComponentId", new Integer(benefitComponentId));
			map.put("primaryEntityType", "ATTACHPRODUCT");
			if (null != benefitLevelHideFlag
					&& !"".equals(benefitLevelHideFlag))
				map.put("levelHideFlag", benefitLevelHideFlag);
			else
				map.put("levelHideFlag", null);
			if (null != benefitLineHideFlag && !"".equals(benefitLineHideFlag))
				map.put("lineHideFlag", benefitLineHideFlag);
			else
				map.put("lineHideFlag", null);
			SearchCriteria searchCriteria = AdapterUtil
					.getAdapterSearchCriteria(TierDefinitionBO.class.getName(),
							"getTierCriteriaForProduct", map);
			searchResults = AdapterUtil.performSearch(searchCriteria);
			
		}else if (entityType.equalsIgnoreCase("Level")) {
			map.put("entityType", entityType);
			map.put("prodSysId", new Integer(entitySysId));
			map.put("benefitSysId", new Integer(benefitId));
			map.put("benefitComponentId", new Integer(benefitComponentId));
			map.put("primaryEntityType", "ATTACHPRODUCT");
			if (null != benefitLevelHideFlag
					&& !"".equals(benefitLevelHideFlag))
				map.put("levelHideFlag", benefitLevelHideFlag);
			else
				map.put("levelHideFlag", null);
			if (null != benefitLineHideFlag && !"".equals(benefitLineHideFlag))
				map.put("lineHideFlag", benefitLineHideFlag);
			else
				map.put("lineHideFlag", null);
			SearchCriteria searchCriteria = AdapterUtil
					.getAdapterSearchCriteria(BenefitLine.class.getName(),
							"getAllBenefitLines", map);
			
			searchResults = AdapterUtil.performSearch(searchCriteria);
			
		}
		else {
			SearchCriteria searchCriteria =null;
			map.put("entityType", entityType);
			map.put("entitySysId", new Integer(entitySysId));
			map.put("benefitSysId", new Integer(benefitId));
			map.put("benefitComponentId", new Integer(benefitComponentId));
			map.put("primaryEntityType", "ATTACHCOMP");
			if (null != benefitLevelHideFlag
					&& !"".equals(benefitLevelHideFlag))
				map.put("levelHideFlag", benefitLevelHideFlag);
			else
				map.put("levelHideFlag", null);
			if (null != benefitLineHideFlag && !"".equals(benefitLineHideFlag))
				map.put("lineHideFlag", benefitLineHideFlag);
			else
				map.put("lineHideFlag", null);
			if(isPvaValidationNeeded (entitySysId,"struture")){
			searchCriteria = AdapterUtil
					.getAdapterSearchCriteria(BenefitLine.class.getName(),
							"getAllProductStructureBenefitLines", map);
			}else{
			 searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(BenefitLine.class.getName(),
						"getAllProductStructureBenefitLinesWithoutPVAValidation", map);
			
			}
			
		    
			searchResults = AdapterUtil.performSearch(searchCriteria);
			
			//This method would set the term in to the list.
			//searchBenefitTermsDescForCorrespondingBenefitLevel(searchResults.getSearchResults());//Commented as part of performance fix
			//This method would set the qualifer in to the list.
			//searchBenefitQualifiersDescForCorrespondingBenefitLevel(searchResults.getSearchResults());//Commented as part of performance fix
		}
		}catch (Exception ex) {
			
			throw new AdapterException(
					"Exception occured in getAllOverridedBenefitLines entityType method in BenefitAdapterManager",
					ex);
		}	
		Logger.logInfo(th.endPerfLogging());
		return searchResults.getSearchResults();
	}

	/**
		 * @param entitySysId
		 * @return
		 * @throws SevereException
		 */
		private boolean isPvaValidationNeeded(int entitySysId,String type) throws SevereException {
			boolean validationNeeded = false;
			HashMap map = new HashMap();
			SearchResults searchResults = null;
			SearchCriteria searchCriteria =null;
			if(type .equals("product")){
			map.put("productKey", new Integer(entitySysId));
			searchCriteria = AdapterUtil
			.getAdapterSearchCriteria(ProductBO.class.getName(),
					"getPva", map);
			}else{
				map.put("productStructureId", new Integer(entitySysId));
				searchCriteria = AdapterUtil
				.getAdapterSearchCriteria(ProductStructureBO.class.getName(),
						"getPva", map);
			}
			searchResults = AdapterUtil.performSearch(searchCriteria);
			List searchResult = searchResults.getSearchResults();
			if(null!=searchResults && searchResult.size()>0){
				validationNeeded =true;
			}
			return validationNeeded;
		}
	public List getBenefitLinesAndLines(List tierSysIdList,int entitySysId, int bnftCompId, String entityType) throws SevereException,
			AdapterException {

				
		SearchResults searchResults = null;		

		HashMap map = new HashMap();
		try{
		  if (tierSysIdList == null || tierSysIdList.isEmpty())   
			throw new IllegalArgumentException(
					"One of the parameters [entitySysId, benefitId, benefitComponentId] is invalid");
		
		
			
			map.put("tierSysIdList", tierSysIdList);
			map.put("prodSysId", new Integer(entitySysId));
			map.put("benefitComponentId", new Integer(bnftCompId));
		
			SearchCriteria searchCriteria = AdapterUtil
					.getAdapterSearchCriteria(TierDefinitionBO.class.getName(),
							"getBenefitLvlLinesForProduct", map);
			searchResults = AdapterUtil.performSearch(searchCriteria);
			//This method would set the term in to the list.
			//Commented as part of stabilization release this logic handled in the query using a function
			//searchBenefitTermsDescForCorrespondingBenefitLevel(searchResults.getSearchResults());
			//This method would set the qualifer in to the list.
			//searchBenefitQualifiersDescForCorrespondingBenefitLevel(searchResults.getSearchResults());
		}
		catch (Exception ex) {
			
			throw new AdapterException(
					"Exception occured in getAllOverridedBenefitLines entityType method in BenefitAdapterManager",
					ex);
		}
		
		return searchResults.getSearchResults();
	}
	/*
	 * public List getAllOverridedBenefitLines(int entitySysId, int benefitId,
	 * int benefitComponentId,String benefitLevelHideFlag,String
	 * benefitLineHideFlag) throws SevereException{ if(entitySysId <=0 ||
	 * benefitId <= 0 || benefitComponentId <=0 ) throw new
	 * IllegalArgumentException("One of the parameters [entitySysId, benefitId,
	 * benefitComponentId] is invalid"); SearchResults searchResults=null;
	 * HashMap map = new HashMap();
	 * 
	 * map.put("entitySysId",new Integer(entitySysId));
	 * map.put("benefitSysId",new Integer(benefitId));
	 * map.put("benefitComponentId",new Integer(benefitComponentId));
	 * map.put("primaryEntityType","ATTACHPRODUCT"); if(null!=
	 * benefitLevelHideFlag &&!"".equals(benefitLevelHideFlag))
	 * map.put("benefitLevelHideFlag",benefitLevelHideFlag); else
	 * map.put("benefitLevelHideFlag",null); if(null!= benefitLineHideFlag
	 * &&!"".equals(benefitLineHideFlag))
	 * map.put("benefitLineHideFlag",benefitLineHideFlag); else
	 * map.put("benefitLineHideFlag",null);
	 * 
	 * SearchCriteria searchCriteria =
	 * AdapterUtil.getAdapterSearchCriteria(BenefitLine.class.getName(),"getAllBenefitLines",map);
	 * searchResults= AdapterUtil.performSearch(searchCriteria); return
	 * searchResults.getSearchResults(); }
	 */
	public List getAllOverridedBenefitLinesSearch(String entityType,
			int entitySysId, int benefitId, int benefitComponentId)
			throws SevereException {
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","BenefitAdapterManager","getAllOverridedBenefitLinesSearch()"));

		if (entitySysId <= 0 || benefitId <= 0 || benefitComponentId <= 0)
			throw new IllegalArgumentException(
					"One of the parameters [entitySysId, benefitId, benefitComponentId] is invalid");

		HashMap map = new HashMap();
		map.put("entityType", entityType);
		map.put("entitySysId", new Integer(entitySysId));
		map.put("benefitSysId", new Integer(benefitId));
		map.put("benefitComponentId", new Integer(benefitComponentId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BenefitLine.class.getName(), "getAllBenefitLinesSearch", map);
		
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		
		Logger.logInfo(th.endPerfLogging());
		
		return searchResults.getSearchResults();
	}
	
	
	public List getTierCriteriaList(String entityType,
			int entitySysId, int benefitId, int benefitComponentId)
	throws SevereException {
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","BenefitAdapterManager","getTierCriteriaList()"));
		
		if (entitySysId <= 0 || benefitId <= 0 || benefitComponentId <= 0)
			throw new IllegalArgumentException(
			"One of the parameters [entitySysId, benefitId, benefitComponentId] is invalid");
		
		HashMap map = new HashMap();
		map.put("entityType", entityType);
		map.put("entitySysId", new Integer(entitySysId));
		map.put("benefitSysId", new Integer(benefitId));
		map.put("benefitComponentId", new Integer(benefitComponentId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				TierDefinitionBO.class.getName(), "getTierCriteriaForContract", map);
		
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo(th.endPerfLogging());

		return searchResults.getSearchResults();
	}
    /*CARS AM2 START*/
	public List getTierIdsInContractBenefit(String entityType,
			int entitySysId, int benefitId, int benefitComponentId)
			throws SevereException {

		if (entitySysId <= 0 || benefitId <= 0 || benefitComponentId <= 0)
			throw new IllegalArgumentException(
					"One of the parameters [entitySysId, benefitId, benefitComponentId] is invalid");

		HashMap map = new HashMap();
		map.put("entityType", entityType);
		map.put("entitySysId", new Integer(entitySysId));
		map.put("benefitSysId", new Integer(benefitId));
		map.put("benefitComponentId", new Integer(benefitComponentId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				TierDefinitionBO.class.getName(), "getTierIdsInContractBenefit", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}
	/*CARS 19.10.2010 START*/
	public List getBenefitTierTermsAndPVAs(String entityType,int entitySysId,int bnftCompId,List tierSysIdList)
	throws SevereException {
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","BenefitAdapterManager","getBenefitTierTermsAndPVAs()"));

			if (null == tierSysIdList || tierSysIdList.isEmpty())
				throw new IllegalArgumentException(
						"One of the parameters [entitySysId, benefitId, benefitComponentId] is invalid");
			
			HashMap map = new HashMap();
			map.put("entityType", entityType);
			map.put("tierSysIdList", tierSysIdList);
			map.put("entitySysId", new Integer(entitySysId));
			map.put("benefitComponentId", new Integer(bnftCompId));
			
			SearchCriteria searchCriteriaForTierCount = AdapterUtil.getAdapterSearchCriteria(
					TierDefinitionBO.class.getName(), "getBenefitLvlLinesForTierProduct", map);
			SearchResults tierSearchResults = AdapterUtil.performSearch(searchCriteriaForTierCount);
			SearchCriteria searchCriteria = null;
			if(null != tierSearchResults.getSearchResults() && tierSearchResults.getSearchResultCount()>0){
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						TierDefinitionBO.class.getName(), "getBenefitTierTermsAndPVAsForContract", map);
			}else{
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						TierDefinitionBO.class.getName(), "getProdLvlLinesForContract", map);
			}
			SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
			
			Logger.logInfo(th.endPerfLogging());
			return searchResults.getSearchResults();
}
	/*CARS 19.10.2010 END*/
	/*CARS AM2 END*/
	
	public List getBenefitLvlLineList(String entityType,int entitySysId,int bnftCompId,List tierSysIdList)
			throws SevereException {
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","BenefitAdapterManager","getBenefitLvlLineList()"));

		if (null == tierSysIdList || tierSysIdList.isEmpty())
			throw new IllegalArgumentException(
					"One of the parameters [entitySysId, benefitId, benefitComponentId] is invalid");

		HashMap map = new HashMap();
		map.put("entityType", entityType);
		map.put("tierSysIdList", tierSysIdList);
		map.put("entitySysId", new Integer(entitySysId));
		map.put("benefitComponentId", new Integer(bnftCompId));
		
		SearchCriteria searchCriteriaForTierCount = AdapterUtil.getAdapterSearchCriteria(
				TierDefinitionBO.class.getName(), "getBenefitLvlLinesForTierProduct", map);
		SearchResults tierSearchResults = AdapterUtil.performSearch(searchCriteriaForTierCount);
		SearchCriteria searchCriteria = null;
		if(null != tierSearchResults.getSearchResults() && tierSearchResults.getSearchResultCount()>0){
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					TierDefinitionBO.class.getName(), "getBenefitLvlLinesForContract", map);
		}else{
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					TierDefinitionBO.class.getName(), "getProdLvlLinesForContract", map);
		}
		
		
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);		
		Logger.logInfo(th.endPerfLogging());
		return searchResults.getSearchResults();
	}
	/**
	 * Method for updating the benefit lines of Product
	 * 
	 * @param benefitLine
	 * @param access
	 * @throws SevereException
	 */
	public void updateDefenitoinOverridenValue(
			ProductBenefitCustomizedBO benefitLine, AdapterServicesAccess access)
			throws AdapterException {
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Product_Coverage","BenefitAdapterManager","updateDefenitoinOverridenValue()"));
		
		if (benefitLine.getBenefitLineId() <= 0
				|| benefitLine.getProductSysId() <= 0
				|| benefitLine.getBenefitComponentSysId() <= 0
				|| benefitLine.getProductBenefitCustomizedSysId() <= 0)
			throw new IllegalArgumentException(
					"One of the parameters [entitySysId, LineId, benefitComponentId,customizedSysId] is invalid");
		try {
			AdapterUtil.performUpdate(benefitLine, benefitLine
					.getLastUpdatedUser(), access);
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}
		Logger.logInfo(th.endPerfLogging());
	}

	public void updateOverridenValueForContractBC(
			ContractBenefitHeadings contractBenefitHeadings,
			AdapterServicesAccess access) throws SevereException {
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","BenefitAdapterManager","updateOverridenValueForContractBC()"));
		/*
		 * if(benefitLine.getBenefitLineId() <= 0 ||
		 * benefitLine.getProductSysId() <=0 ||
		 * benefitLine.getBenefitComponentSysId() <= 0 ||
		 * benefitLine.getProductBenefitCustomizedSysId() <= 0) throw new
		 * IllegalArgumentException("One of the parameters [entitySysId, LineId,
		 * benefitComponentId,customizedSysId] is invalid");
		 */

		AdapterUtil.performUpdate(contractBenefitHeadings,
				contractBenefitHeadings.getLastUpdatedUser(), access);
		Logger.logInfo(th.endPerfLogging());
	}
	public void saveNewlyCodedLinesForContractBC(
			ContractBenefitHeadings contractBenefitHeadings,
			AdapterServicesAccess access) throws SevereException {
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","BenefitAdapterManager","saveNewlyCodedLinesForContractBC()"));
		/*
		 * if(benefitLine.getBenefitLineId() <= 0 ||
		 * benefitLine.getProductSysId() <=0 ||
		 * benefitLine.getBenefitComponentSysId() <= 0 ||
		 * benefitLine.getProductBenefitCustomizedSysId() <= 0) throw new
		 * IllegalArgumentException("One of the parameters [entitySysId, LineId,
		 * benefitComponentId,customizedSysId] is invalid");
		 */
		
		AdapterUtil.performInsert(contractBenefitHeadings,
				contractBenefitHeadings.getLastUpdatedUser());
		Logger.logInfo(th.endPerfLogging());
	}
	public void deleteUnCodedLinesForContractBC(
			ContractBenefitHeadings contractBenefitHeadings,
			AdapterServicesAccess access) throws SevereException {
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","BenefitAdapterManager","deleteUnCodedLinesForContractBC()"));
		/*
		 * if(benefitLine.getBenefitLineId() <= 0 ||
		 * benefitLine.getProductSysId() <=0 ||
		 * benefitLine.getBenefitComponentSysId() <= 0 ||
		 * benefitLine.getProductBenefitCustomizedSysId() <= 0) throw new
		 * IllegalArgumentException("One of the parameters [entitySysId, LineId,
		 * benefitComponentId,customizedSysId] is invalid");
		 */
		
		AdapterUtil.performRemove(contractBenefitHeadings,
				contractBenefitHeadings.getLastUpdatedUser(), access);
		Logger.logInfo(th.endPerfLogging());
	}

	/**
	 * Method for updating the benefit lines of product structure
	 * 
	 * @param benefitLine
	 * @param access
	 * @throws SevereException
	 */
	public void updateDefenitionOverridenValue(
			ProductStructureBenefitCustomizedBO benefitLine,
			AdapterServicesAccess access) throws AdapterException {
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Product_Structre_Coverage","BenefitAdapterManager","updateDefenitionOverridenValue"));
		
		if (benefitLine.getProductStructureSysId() <= 0
				|| benefitLine.getBenefitComponentSysId() <= 0
				|| benefitLine.getBenefitLineId() <= 0
				|| benefitLine.getBenefitCustomizedSysId() <= 0)
			throw new IllegalArgumentException(
					"One of the parameters [entitySysId, LineId, benefitComponentId, customizedSysId] is invalid");
		try {
			/*AdapterUtil.performUpdate(benefitLine, benefitLine
					.getLastUpdatedUser(), access);*/
			HashMap hideUnhideMap = new HashMap();
	          hideUnhideMap.put("benefitHideFlag", benefitLine.getBenefitHideFlag());
	          hideUnhideMap.put("lineHideFlag", benefitLine.getLineHideFlag());
	          hideUnhideMap.put("levelHideFlag", benefitLine.getLevelHideFlag());
	          hideUnhideMap.put("lastUpdatedUser", benefitLine.getCreatedUser());
	          hideUnhideMap.put("productStructureSysId",""+benefitLine.getProductStructureSysId());
  	          hideUnhideMap.put("benefitComponentSysId",""+benefitLine.getBenefitComponentSysId());
	          hideUnhideMap.put("benefitLineId",""+benefitLine.getBenefitLineId());
	          hideUnhideMap.put("benefitCustomizedSysId",""+benefitLine.getBenefitCustomizedSysId());      
	          
	          SearchCriteria criteria = AdapterUtil.getAdapterSearchCriteria(ProductStructureBenefitCustomizedBO.class.getName(), "PS_hideunhidebenefitlines", hideUnhideMap);
	          AdapterUtil.performSearch(criteria, access); 

			Logger.logInfo(th.endPerfLogging());
		} catch (Exception ex) {
			throw new AdapterException("Exception occured while adapter call",
					ex);
		}

	}

	public void updateDefenitoinOverridenValue(BenefitLine benefitLine,
			AdapterServicesAccess access) throws AdapterException {
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Product_Coverage","BenefitAdapterManager","updateDefenitoinOverridenValue(BenefitLine benefitLine,)"));
		
		
		if (benefitLine.getEntitySysId() <= 0
				|| benefitLine.getBenefitComponentId() <= 0)
			throw new IllegalArgumentException(
					"One of the parameters [entitySysId, benefitComponentId] is invalid");
		try {
			AdapterUtil.performUpdate(benefitLine, benefitLine
					.getLastUpdatedUser(), access);
			Logger.logInfo(th.endPerfLogging());
			
		} catch (Exception ex) {
			throw new AdapterException(
					"Exception occured while updateDefenitoinOverridenValue adapter call",
					ex);
		}
	}
	/**
	 * To get the  child Questionnare List while chenging answer
	 *  
	 * @param selectedAnswerid
	 * @param questionnaireId
	 * @param beneftComponentId
	 * @return List
	 * 		   this list contain child questionnare.
	 * @throws SevereException
	 */
	public List getChildQuestionnaireValuesForContract(int selectedAnswerid,
			int questionnaireId, int contractSysId, int dateSegmntId,
			int adminLvlOptAssSysId,int bcSysId,int beneftAdminSysId) throws SevereException {
		
	    
		HashMap map = new HashMap();
		// Set the searchCriteria values in a map
		map.put("selectedAnswerid", new Integer(selectedAnswerid));
		map.put("questionnaireId", new Integer(questionnaireId));
		map.put("contractSysId", new Integer(contractSysId));
		map.put("dateSegmentId", new Integer(dateSegmntId));
		map.put("adminLvlOptSystemId", new Integer(adminLvlOptAssSysId));
		map.put("beneftComponentId", new Integer(bcSysId));
		map.put("benftAdminSysId", new Integer(beneftAdminSysId));  
		
		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractQuesitionnaireBO.class.getName(),
				"getChildQuestionnaireContract", map);
		// Get the locate Result after the search operation in the db
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		
		// Return the resulting list to the builder
		return searchResults.getSearchResults();
		
	}
	/**
	 * To get the list of BenefitAdministration objects from the db for
	 * ProductStructure
	 * 
	 * @param productStructureBO
	 * @param benefitAdministrationSubObject
	 * @return List
	 * @throws SevereException
	 */
	public List getBenefitAdministrationValuesForContract(int entityId,
			int adminOptionSysId, int benefitAdminSysId, int beneftComponentId,
			int cntrctParentSysId, int adminLevelOptionSysId) throws SevereException, AdapterException {

		// Create a hashMap object
		HashMap map = new HashMap();
		
		// Set the searchCriteria values in a map
		map.put("benftAdminSysId", new Integer(benefitAdminSysId));
		map.put("dateSegmentId", new Integer(entityId));
		map.put("adminOptSysId", new Integer(adminOptionSysId));
		map.put("beneftComponentId", new Integer(beneftComponentId));
		//	  Code change by minu : 5-1-2011 : eWPD System Stabilization
		map.put("adminLvlOptSystemId", new Integer(adminLevelOptionSysId)); 
		map.put("cntrctParntSysId", new Integer(cntrctParentSysId) );


		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractQuesitionnaireBO.class.getName(),
				"getQuestionnaireContract", map);
		try{
			// Get the locate Result after the search operation in the db
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		//		  Code change by minu : 5-1-2011 : eWPD System Stabilization
		ArrayList searchResultsList = (ArrayList)searchResults.getSearchResults();
		for(int i=0;i<searchResultsList.size();i++){
			ContractQuesitionnaireBO contractQuesitionnaireBO = (ContractQuesitionnaireBO)searchResultsList.get(i);
			contractQuesitionnaireBO.setAdminLevelOptionSysId(adminLevelOptionSysId);
			contractQuesitionnaireBO.setDateSegmentId(entityId);
		}
		// Return the resulting list to the builder
		return searchResultsList;
		} catch (Exception ex) {
			
			throw new AdapterException(
					"Exception occured in getBenefitAdministrationValues entityType method in BenefitAdapterManager",
					ex);
		}
	}
	
	/**
	 * To get the list of BenefitAdministration objects from the db for
	 * ProductStructure
	 * 
	 * @param productStructureBO
	 * @param benefitAdministrationSubObject
	 * @return List
	 * @throws SevereException
	 */
	public List getBenefitAdministrationValues(String entityType, int entityId,
			int adminOptionSysId, int benefitAdminSysId, int beneftComponentId)
			throws SevereException,AdapterException {

		// Create a hashMap object
		HashMap map = new HashMap();
		
		// Set the searchCriteria values in a map
		map.put("entitySysId", new Integer(entityId));
		map.put("entityType", entityType);
		map.put("adminOptSysId", new Integer(adminOptionSysId));
		map.put("benftAdminSysId", new Integer(benefitAdminSysId));
		map.put("beneftComponentId", new Integer(beneftComponentId));

		// Get the searchCriteria object
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				EntityBenefitAdministration.class.getName(),
				"locateEntityBenefitAdministration", map);
		try{
		// Get the locate Result after the search operation in the db
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		

		// Return the resulting list to the builder
		return searchResults.getSearchResults();
		} catch (Exception ex) {
			
			throw new AdapterException(
					"Exception occured in getBenefitAdministrationValues entityType method in BenefitAdapterManager",
					ex);
		}
	}
	/*
	 * This method is for persist new questionnare 
	 * 
	 * @param ComponentsBenefitAdministrationBO
	 * @param Audit,AdapterServicesAccess
	 * 
	 * This method delete all the existing questionare and persist a new questionnare
	 */
	public void saveQuestionnareForContract(EntityBenefitAdministrationPSBO administrationBO,Audit audit,
			AdapterServicesAccess access) throws SevereException{
		
			
			List questionnareList = administrationBO.getQuestionnareList();
			List tierList =administrationBO.getTierList();
			int benefitComponentId = administrationBO.getBenefitComponentid();
			ContractQuesitionnaireBO contractQuesitionnaireBO = new ContractQuesitionnaireBO();
			contractQuesitionnaireBO.setBenefitComponentId(benefitComponentId);
			contractQuesitionnaireBO.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
			contractQuesitionnaireBO.setDateSegmentId(administrationBO.getDateSegmentId());
			AdapterUtil.performRemove(contractQuesitionnaireBO, audit.getUser(),
					access);
			for(int i=0;i<questionnareList.size();i++){
				ContractQuesitionnaireBO quesitionnaireBO = (ContractQuesitionnaireBO)questionnareList.get(i);
				quesitionnaireBO.setBenefitComponentId(benefitComponentId);
				quesitionnaireBO.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
				quesitionnaireBO.setDateSegmentId(administrationBO.getDateSegmentId());
				quesitionnaireBO.setCreatedUser(audit.getUser());
				quesitionnaireBO.setCreatedTimestamp(audit.getTime());
				quesitionnaireBO.setLastUpdatedUser(audit.getUser());
				quesitionnaireBO.setLastUpdatedTimestamp(audit.getTime());
				AdapterUtil.performInsert(quesitionnaireBO, audit.getUser(),access);
			}
			if(null != tierList && tierList.size()>0){ // if tiered questions exist
				ContractTierQuesitionnaireBO contractTierQuesitionnaireBO = new ContractTierQuesitionnaireBO();
				contractTierQuesitionnaireBO.setBenefitComponentId(benefitComponentId);
				contractTierQuesitionnaireBO.setDateSegmentId(administrationBO.getDateSegmentId());
				contractTierQuesitionnaireBO.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
				AdapterUtil.performRemove(contractTierQuesitionnaireBO, audit.getUser(),
						access);
				for(int j =0;j<tierList.size();j++){
					BenefitTierDefinition defnBo = (BenefitTierDefinition)tierList.get(j); // iterating tier definitions
					List benefitTierList = defnBo.getBenefitTiers();
					for (int k =0; k<benefitTierList.size();k++){
						BenefitTier tierBo =(BenefitTier)benefitTierList.get(k);
						List questionList = tierBo.getQuestionnaireList();
						for(int l=0;l<questionList.size();l++){
							ContractTierQuesitionnaireBO contracttierQuestionareBOForSave = new ContractTierQuesitionnaireBO();
							ContractQuesitionnaireBO bo = (ContractQuesitionnaireBO)questionList.get(l);
							contracttierQuestionareBOForSave.setQuestionnaireId(bo.getQuestionnaireId());
							contracttierQuestionareBOForSave.setSelectedAnswerid(bo.getSelectedAnswerid());
							contracttierQuestionareBOForSave.setDateSegmentId(administrationBO.getDateSegmentId());
							contracttierQuestionareBOForSave.setBenefitComponentId(administrationBO.getBenefitComponentid());
							contracttierQuestionareBOForSave.setAdminLevelOptionSysId(administrationBO.getAdminLevelOptionSysId());
							contracttierQuestionareBOForSave.setTierSysId(tierBo.getBenefitTierSysId());
							contracttierQuestionareBOForSave.setCreatedUser(audit.getUser());
							contracttierQuestionareBOForSave.setCreatedTimestamp(audit.getTime());
							contracttierQuestionareBOForSave.setLastUpdatedUser(audit.getUser());
							contracttierQuestionareBOForSave.setLastUpdatedTimestamp(audit.getTime());
							AdapterUtil.performInsert(contracttierQuestionareBOForSave, audit.getUser(),access);
						}
					}
				}
			}
	}
	
	/**
	 * To update the overridded values of the administration option questions
	 * 
	 * @param productStructureBO
	 * @param benefitAdministration
	 * @throws SevereException
	 */
	public void updateBenefitAdministrationValues(
			EntityBenefitAdministration benefitAdministration, String userId,
			AdapterServicesAccess access) throws AdapterException {
		try {
			// Call the update method
			AdapterUtil.performUpdate(benefitAdministration, userId, access);
		} catch (Exception e) {
			throw new AdapterException(
					"Exception occured while updateBenefitAdministrationValues adapter call",
					e);
		}
	}

	//enhancement
	public void updateBenefitAdministrationValuesForHidingAdminOption(
			ProductStructureBenefitAdministration productStructureBenefitAdministration,
			String userId) throws AdapterException, SevereException {
		try {
			// Call the update method
			AdapterUtil.performUpdate(productStructureBenefitAdministration,
					userId);
		} catch (Exception e) {
			throw new AdapterException("Exception occured while adapter call",
					e);
		}
	}

	public boolean deleteBenefitDefinitionLevel(
			BenefitLine productBenefitComponentDeleteBO, String User,
			AdapterServicesAccess access) throws AdapterException {
		try {
			AdapterUtil.performRemove(productBenefitComponentDeleteBO, User,
					access);
		} catch (Exception ex) {
			throw new AdapterException(
					"Exception occured in deleteBenefitDefinitionLevel while BenefitAdapterManager call",
					ex);
		}
		return true;

	}

	public List getBenefitHeadings(int dateSegmentId, int productId)
			throws SevereException {

		//        if(entitySysId <=0 || benefitId <= 0 || benefitComponentId <=0 )
		//            throw new IllegalArgumentException("One of the parameters
		// [entitySysId, benefitId, benefitComponentId] is invalid");
		//        
		HashMap map = new HashMap();
		map.put("dateSegmentId", new Integer(dateSegmentId));
		map.put("productId", new Integer(productId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractBenefitHeadings.class.getName(), "getBenefitHeadings",
				map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	public List getBenefitLines(int dateSegmentId, int standardBenefitId,
			int benefitComponentId) throws SevereException {

		HashMap map = new HashMap();
		map.put("dateSegmentId", new Integer(dateSegmentId));
		map.put("standardBenefitId", new Integer(standardBenefitId));
		map.put("benefitComponentId", new Integer(benefitComponentId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractBenefitHeadings.class.getName(),
				"getBenefitHeadingLines", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	//This method returns the list of coded and noncoded benefit headings
	public List getCodedNonCodedBenefitHeadings(int dateSegmentId, int productId)
			throws SevereException {

		//        if(entitySysId <=0 || benefitId <= 0 || benefitComponentId <=0 )
		//            throw new IllegalArgumentException("One of the parameters
		// [entitySysId, benefitId, benefitComponentId] is invalid");
		//        
		HashMap map = new HashMap();
		map.put("dateSegmentId", new Integer(dateSegmentId));
		map.put("productId", new Integer(productId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractBenefitHeadings.class.getName(),
				"getCodedNonCodedBenefitHeadings", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		return searchResults.getSearchResults();
	}

	/**
	 * Method for getting benefit lines having benefit values
	 * 
	 * @param entityType
	 * @param entitySysId
	 * @param benefitId
	 * @param benefitComponentId
	 * @return
	 * @throws SevereException
	 */
	public List getOverridedBenefitLinesForDatafeed(String entityType,
			int entitySysId, int benefitId, int benefitComponentId)
			throws SevereException {
		
		TimeHandler th = new TimeHandler();
		Logger.logInfo(th.startPerfLogging("U23914_Coverage","BenefitAdapterManager","getOverridedBenefitLinesForDatafeed()"));


		if (entitySysId <= 0 || benefitId <= 0 || benefitComponentId <= 0)
			throw new IllegalArgumentException(
					"One of the parameters [entitySysId, benefitId, benefitComponentId] is invalid");

		HashMap map = new HashMap();
		map.put("entityType", entityType);
		map.put("entitySysId", new Integer(entitySysId));
		map.put("benefitSysId", new Integer(benefitId));
		map.put("benefitComponentId", new Integer(benefitComponentId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BenefitLine.class.getName(), "getBenefitLinesDatafeed", map);
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo(th.endPerfLogging());
		return searchResults.getSearchResults();
	}

	/**
	 * To update the overridded values of the administration option questions
	 * 
	 * @param productStructureBO
	 * @param benefitAdministration
	 * @throws SevereException
	 */
	public void updateAdminOptionValues(HideAdminOptionBO adminOption,
			String userId, AdapterServicesAccess access)
			throws AdapterException {
		try {
			// Call the update method
			adminOption.setProductSysId(adminOption.getEntityId());
			AdapterUtil.performUpdate(adminOption, userId, access);
		} catch (Exception e) {
			throw new AdapterException("Exception occured while adapter call",
					e);
		}
	}
//	CARS START
	   /**
		 * This method would set the term value in the list
		 * This is made to fix the aggregate qualifier 
	     * @param benefitLevelsList
	     * @throws ServiceException
	     */
	    private void searchBenefitTermsDescForCorrespondingBenefitLevel(
	            List benefitLevelsList) throws SevereException {
	    	
	    	TimeHandler th = new TimeHandler();
		    Logger.logInfo(th.startPerfLogging("U23914_Benefit_Component_Coverage","BenefitAdapterManager","searchBenefitTermsDescForCorrespondingBenefitLevel"));
		    
	        List benefitTermsSearchResultsList = null;
	        LocateResults searchResults = null;
	        BenefitlevelAdapterManager benefitlevelAdapterManager = new BenefitlevelAdapterManager();
	        //Checks if the benefit level list is null
	        if (null != benefitLevelsList) {
	            for (int i = 0; i < benefitLevelsList.size(); i++) {
	            	int LevelId;
	            	String benefitTermCodes = null;
	            	BenefitLine benefitLineBO = null;
	            	TierDefinitionBO tierDefinitionBO = null;
	            	boolean objectFlag = true;
	            	try{
	                benefitLineBO = (BenefitLine) benefitLevelsList
	                        .get(i);
	                LevelId = benefitLineBO.getLevelSysId();
	                benefitTermCodes = benefitLineBO.getTermCode();
	            	}catch(Exception e){
	            		tierDefinitionBO = (TierDefinitionBO) benefitLevelsList.get(i);
	                    LevelId = tierDefinitionBO.getLevelSysId();
	                    benefitTermCodes = tierDefinitionBO.getBnftLvlTerm();
	                    objectFlag = false;
	            	}                
	                BenefitLevelLocateCriteria benefitLevelLocateCriteria = new BenefitLevelLocateCriteria();
	                benefitLevelLocateCriteria.setBenefitLevelId(LevelId);
	                benefitTermsSearchResultsList = new ArrayList();
	                try {
	                    // get the benefitTermCodes for the benefit level
	                    //benefitTermCodes = benefitLineBO
	                    //        .getTermCode();
	                    //Null checks the benefitTermCode
	                    if(null != benefitTermCodes){
		                    StringTokenizer benefitTerms = new StringTokenizer(
		                            benefitTermCodes, BusinessConstants.COMMA);
		                    int noOfTokens = benefitTerms.countTokens();
		                    String termValue = null;
		                    String termCodeValue = null;
		                    //Checks if the the noOfTokens > 1
		                    if(noOfTokens > 1){
		                    	boolean flag = true;
			                    for (int j = 0; j < noOfTokens; j++) {
			                        if (benefitTerms.hasMoreTokens()) {
			                            String benefitTerm = benefitTerms.nextToken();
			                            benefitLevelLocateCriteria
			                                    .setBenefitTerm(benefitTerm);
			                            //Retrives the term description for the term code
			                            searchResults = benefitlevelAdapterManager
			                                    .locateBenefitTermsDesc(benefitLevelLocateCriteria);
			                            List benefitTermDesc = searchResults
			                                    .getLocateResults();
			                            if (null != benefitTermDesc
			                                    && benefitTermDesc.size() > 0) {
			                            	BenefitTermBO benefitTermBO = new BenefitTermBO();
			                            	benefitTermBO = (BenefitTermBO) benefitTermDesc.get(0);
			                            	if(flag == true){
			                            		termValue = benefitTermBO.getBenefitTerm();
			                            		termCodeValue = benefitTermBO.getBenefitTermCode();
			                            		//Flag is set false to restrict the entry for the second time
			                            		flag = false;
			                            	}else{
			                            		//Setting the term description value after the comma seperation
			                            		termValue = termValue+BusinessConstants.COMMA+ benefitTermBO.getBenefitTerm();
			                            		//Setting the term code value after the comma seperation
			                            		termCodeValue = termCodeValue+BusinessConstants.COMMA+ benefitTermBO.getBenefitTermCode();
			                            	}			                            	
			                            }
			                        }
			                    }
			                    if(objectFlag){
				                   //Setting the term description value to the BO
				                    benefitLineBO.setTermDesc(termValue);
				                   //Setting the term code value to the BO
				                    benefitLineBO.setTermCode(termCodeValue);
			                    }else{
			                    	 //Setting the qualifier description value to the BO
			                    	tierDefinitionBO.setLvlTermDesc(termValue);
			                    	//Setting the qualifier code value to the BO
			                    	tierDefinitionBO.setBnftLvlTerm(termCodeValue);
			                    }
		                    }
	                    }
	                } catch (Exception ex) {
	                	Logger.logError(ex);
	                    List logParameters = new ArrayList();
	                    String logMessage = "Failed while processing searchBenefitTermDesc";
	                    throw new ServiceException(logMessage, logParameters, ex);
	                }
	            }
	        }
	        Logger.logInfo(th.endPerfLogging());
	    }
		
	   /**
		 * This method would set the qualifier value in the list
	     * @param benefitLevelsList
	     * @throws ServiceException
	     */
	    private void searchBenefitQualifiersDescForCorrespondingBenefitLevel(
	            List benefitLevelsList) throws SevereException {
	    	
	    	TimeHandler th = new TimeHandler();
		    Logger.logInfo(th.startPerfLogging("U23914_Benefit_Component_Coverage","BenefitAdapterManager","searchBenefitQualifiersDescForCorrespondingBenefitLevel"));
		    
	    	
	        List benefitQualifierSearchResultsList = null;
	        LocateResults searchResults = null;
	        BenefitlevelAdapterManager benefitlevelAdapterManager = new BenefitlevelAdapterManager();
	        if (null != benefitLevelsList) {
	            for (int i = 0; i < benefitLevelsList.size(); i++) {
	            	int LevelId;
	            	String benefitQualCodes = null;
	            	BenefitLine benefitLineBO = null;
	            	TierDefinitionBO tierDefinitionBO = null;
	            	boolean objectFlag = true;
	            	try{
	                benefitLineBO = (BenefitLine) benefitLevelsList
	                        .get(i);
	                LevelId = benefitLineBO.getLevelSysId();
	                benefitQualCodes = benefitLineBO.getQualifierCode();
	            	}catch(Exception e){
	            		tierDefinitionBO = (TierDefinitionBO) benefitLevelsList.get(i);
	                    LevelId = tierDefinitionBO.getLevelSysId();
	                    benefitQualCodes = tierDefinitionBO.getBnftLvlQual();
	                    objectFlag = false;
	            	}
	                BenefitLevelLocateCriteria benefitLevelLocateCriteria = new BenefitLevelLocateCriteria();
	                benefitLevelLocateCriteria.setBenefitLevelId(LevelId);
	                benefitQualifierSearchResultsList = new ArrayList();
	                try {
	                    // get the benefitQualifierCodes for the benefit level
	                    //String benefitQualCodes = benefitLineBO
	                    //        .getQualifierCode();
	                    if(null != benefitQualCodes && !"0".equals(benefitQualCodes)){
		                    StringTokenizer benefitQualifiers = new StringTokenizer(
		                    		benefitQualCodes, BusinessConstants.COMMA);
		                    int noOfTokens = benefitQualifiers.countTokens();
		                    String qualifierValue = null;
		                    String qualifierCodeValue = null;
		                    if(noOfTokens > 1){
		                    	boolean flag = true;	
			                    for (int j = 0; j < noOfTokens; j++) {
			                        if (benefitQualifiers.hasMoreTokens()) {
			                        	 String benefitQual = benefitQualifiers.nextToken();
				                            benefitLevelLocateCriteria
				                                    .setBenefitQualifier(benefitQual);
				                            //Retrieves the term description for the term code.
				                            searchResults = benefitlevelAdapterManager
				                                    .locateBenefitQualifiersDesc(benefitLevelLocateCriteria);
				                            List benefitQualDesc = searchResults
				                                    .getLocateResults();
				                            if (null != benefitQualDesc
				                                    && benefitQualDesc.size() > 0) {
					                            BenefitQualifierBO benefitQualifierBO = new BenefitQualifierBO();			                            		
			                            		benefitQualifierBO = (BenefitQualifierBO) benefitQualDesc.get(0);
				                            	if(flag == true){
				                            		qualifierValue = benefitQualifierBO.getBenefitQualifier();
				                            		qualifierCodeValue = benefitQualifierBO.getBenefitQualifierCode();
				                            		//Flag is set false to restrict the entry for the second time
				                            		flag = false;
				                            	}else{
				                            		//Setting the qualifier description value after the comma seperation
				                            		qualifierValue = qualifierValue+BusinessConstants.COMMA+ benefitQualifierBO.getBenefitQualifier();
				                            		//Setting the qualifier code value after the comma seperation
				                            		qualifierCodeValue = qualifierCodeValue+BusinessConstants.COMMA+ benefitQualifierBO.getBenefitQualifierCode();
				                            	}			                            	
			                            }
			                        }
			                    }
			                   
			                    if(objectFlag){
			                    	 //Setting the qualifier description value to the BO
			                    	benefitLineBO.setQualifierDesc(qualifierValue);
				                    //Setting the qualifier code value to the BO
				                    benefitLineBO.setQualifierCode(qualifierCodeValue);
			                    }else{
			                    	 //Setting the qualifier description value to the BO
			                    	tierDefinitionBO.setLevelQualDesc(qualifierValue);
			                    	//Setting the qualifier code value to the BO
			                    	tierDefinitionBO.setBnftLvlQual(qualifierCodeValue);
			                    }
		                    }
	                    }
	                } catch (Exception ex) {
	                	Logger.logError(ex);
	                    List logParameters = new ArrayList();
	                    String logMessage = "Failed while processing searchBenefitTermDesc";
	                    throw new ServiceException(logMessage, logParameters, ex);
	                }
	                //benefitLevelBO.setBenefitQualifiers(benefitQualifierSearchResultsList);
	            }
	        }
	        Logger.logInfo(th.endPerfLogging());
	    }
//	CARS END	
}