/*
 * Created on Jul 27, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.pva.adapter;
import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponent;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.product.bo.ProductQuestionareBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureQuestionnaireBO;
import com.wellpoint.wpd.common.pva.InvalidLineInfo;
import com.wellpoint.wpd.common.pva.PVAMapping;
import com.wellpoint.wpd.common.pva.ProductLineUpdationBO;
import com.wellpoint.wpd.common.pva.ProductStructureLineUpdationBO;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * This class contains methods to handle pva validation in product and methods.
 */
public class PVAAdapterManager {
	/**
	 * Method retrieves all the pva from data base according to the inputs 
	 * @param productFamily,buisinessEntity 
	 * @rturn List of pva.
	 */
	
	public List retrievePVAs(String productFamily, List buisinessEntity)
	throws SevereException{
		
		//Table: PROD_FMLY_BUSS_ENTY_PVA_MAP
		
		Logger.logInfo("Entering the method for getting PVAs from mapping");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("productFamilyId", productFamily);
		criteriaMap.put("entityId", buisinessEntity);
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				PVAMapping.class.getName(),
				"getPVAs", criteriaMap);		   
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo("Returning the method for getting PVAs from mapping");		   
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;	
	}

	/**
	 * This method retrievs all the unhidden lines in the benefitComponent from database
	 *  that is attached to a product structure.
	 * @param benefitComponentId
	 * @param productStructureBO
	 * @return
	 */
	
	public List retrieveBenefitLines(int benefitComponentId, ProductStructureBO productStructureBO)
	throws SevereException{
		

		Logger.logInfo("Entering the method for getting PVAs from mapping");
		HashMap criteriaMap = new HashMap();
		criteriaMap.put("entitySysId",new Integer(productStructureBO.getProductStructureId()));
		criteriaMap.put("benefitComponentId", new Integer(benefitComponentId));
		SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
				BenefitLine.class.getName(),
				"getAllUnHiddenLines", criteriaMap);		   
		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
		Logger.logInfo("Returning the method for getting PVAs from mapping");		   
		if (null != searchResults)
			return searchResults.getSearchResults();
		else
			return null;	
		
	}
	
	/**
	 * This method retrievs all the unhidden lines in the benefitComponent from database
	 * that is attached to a product .
	 * @param benefitComponentId
	 * @param productBO
	 * @return
	 */

		public List retrieveBenefitLines(String benefitComponentId, ProductBO productBO)
		throws SevereException{
			
			Logger.logInfo("Entering the method for getting PVAs from mapping");
			HashMap criteriaMap = new HashMap();
			criteriaMap.put("productId",new Integer(productBO.getProductKey()));
			criteriaMap.put("benefitComponentId", new Integer(benefitComponentId));
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					BenefitLine.class.getName(),
					"getAllUnHiddenLines", criteriaMap);		   
			SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
			Logger.logInfo("Returning the method for getting PVAs from mapping");		   
			if (null != searchResults)
				return searchResults.getSearchResults();
			else
				return null;	
		}
		
		/**
		 * 
		 * @param benefitComponentId
		 * @param lineId
		 * @param productStructureBO
		 * @throws SevereException
		 */
		public void hideBenefitLine(int benefitComponentId, int lineId, ProductStructureBO productStructureBO) throws SevereException{			
			
			Logger.logInfo("Entering the method for hiding a Benefit line in a Benefit component in a Product Structure");
			HashMap criteriaMap = new HashMap();
			criteriaMap.put("benefitComponentId", new Integer(benefitComponentId));
			criteriaMap.put("lineId", new Integer(lineId));
			criteriaMap.put("productStructureId", new Integer(productStructureBO.getProductStructureId()));
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					BenefitLine.class.getName(),
					"hideProductStructureBenefitLine", criteriaMap);		   
			SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
			Logger.logInfo("Returning the method for hiding a Benefit line in a Benefit component in a Product Structure");		   
			
		}
		
		/**
		 * 
		 * @param benefitComponentId
		 * @param lineId
		 * @param productBO
		 * @throws SevereException
		 */
		public void hideBenefitLine(int benefitComponentId, int lineId, ProductBO productBO) throws SevereException{			
			
			Logger.logInfo("Entering the method for hiding a Benefit line in a Benefit component in a Product");
			HashMap criteriaMap = new HashMap();
			criteriaMap.put("benefitComponentId", new Integer(benefitComponentId));
			criteriaMap.put("lineId", new Integer(lineId));
			criteriaMap.put("productId", new Integer(productBO.getProductKey()));
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					BenefitLine.class.getName(),
					"hideProductBenefitLine", criteriaMap);		   
			SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
			Logger.logInfo("Returning the method for hiding a Benefit line in a Benefit component in a Product");		   
			
		}
		
		/**
		 * 
		 * @param benefitComponentId
		 * @param productStructureBO
		 * @throws SevereException
		 */
		public void removeBenefitComponent(int benefitComponentId, ProductStructureBO productStructureBO) throws SevereException{
			Logger.logInfo("Entering the method for removing a Benefit component in a Product Structure");
			HashMap criteriaMap = new HashMap();
			criteriaMap.put("benefitComponentId", new Integer(benefitComponentId));
			criteriaMap.put("productStructureId", new Integer(productStructureBO.getProductStructureId()));
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					BenefitComponent.class.getName(),
					"removeBenefitComponent", criteriaMap);		   
			SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
			Logger.logInfo("Returning the method for removing a Benefit component in a Product Structure");				
			
		}
				
		/**
		 * 
		 * @param benefitComponentId
		 * @param productStructureId
		 * @return
		 */
		
		public List retrieveQuestions(int benefitComponentId, ProductStructureBO productStructureBO)
		throws SevereException{
			
			
			Logger.logInfo("Entering the method for getting Questions and PVA");
			HashMap criteriaMap = new HashMap();
			criteriaMap.put("productStructureId",new Integer(productStructureBO.getProductStructureId()));
			criteriaMap.put("benefitComponentId", new Integer(benefitComponentId));
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					ProductStructureQuestionnaireBO.class.getName(),
					"getAllQuestionsandPVA", criteriaMap);		   
			SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
			Logger.logInfo("Returning the method for getting Questions and PVA");		   
			if (null != searchResults)
				return searchResults.getSearchResults();
			else
				return null;	
			
		}
		
		/**
		 * 
		 * @param benefitComponentId
		 * @param productStructureId
		 * @return
		 */
		
		public List retrieveBenefitLines(int benefitComponentId, ProductBO productBO)
		throws SevereException{
			
			
			Logger.logInfo("Entering the method for getting PVAs from mapping");
			HashMap criteriaMap = new HashMap();
			criteriaMap.put("entitySysId",new Integer(productBO.getProductKey()));
			criteriaMap.put("benefitComponentId", new Integer(benefitComponentId));
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					BenefitLine.class.getName(),
					"getAllUnHiddenLinesProduct", criteriaMap);		   
			SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
			Logger.logInfo("Returning the method for getting PVAs from mapping");		   
			if (null != searchResults)
				return searchResults.getSearchResults();
			else
				return null;	
			
		}
		
		/**
		 * 
		 * @param benefitComponentId
		 * @param productStructureId
		 * @return
		 */
		
		public List retrieveQuestions(int benefitComponentId, ProductBO productBO)
		throws SevereException{
			
			
			Logger.logInfo("Entering the method for getting Questions and PVA");
			HashMap criteriaMap = new HashMap();
			criteriaMap.put("entitySysId",new Integer(productBO.getProductKey()));
			criteriaMap.put("benefitComponentId", new Integer(benefitComponentId));
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					ProductQuestionareBO.class.getName(),
					"getAllQuestionsandPVA", criteriaMap);		   
			SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
			Logger.logInfo("Returning the method for getting Questions and PVA");		   
			if (null != searchResults)
				return searchResults.getSearchResults();
			else
				return null;	
			
		}
		
		public void removeQuestion(int benefitComponentId,int questionId, ProductStructureBO productStructureBO){
			
			//work recursively to remove all child questions and finally delete the parent question
		}
		
		public void removeQuestion(int benefitComponentId,int questionId, ProductBO productBO){
			
			//work recursively to remove all child questions and finally delete the parent question
		}
		 /*
	     * this method deletes benfit component
	     */
	    public boolean deleteBenefit(InvalidLineInfo invalidLineInfo,User user,AdapterServicesAccess adapterServicesAccess)
	            throws AdapterException {
	    	try{
	        AdapterUtil.performRemove(invalidLineInfo,user.getUserId(),adapterServicesAccess);
	    	}catch(Exception ex){
	    		throw new AdapterException("Exception occured while adapter call",ex);
	    	}
	        return true;
	    }
	    
	    /**
	     * updates the product admin
	     * 
	     * @param adminBO
	     * @throws SevereException
	     */
	    public void updateProductAdmin(InvalidLineInfo invalidLineInfo,User user,AdapterServicesAccess adapterServicesAccess)
	            throws AdapterException {
	    	try{
	        AdapterUtil.performUpdate(invalidLineInfo, user.getUserId(),adapterServicesAccess);
	    	}catch(Exception e){
	    		throw new AdapterException("Exception occured while adapter call",e);
	    	}
	    }
	    
	    /*
	     * 
	     * @author u14768
	     *
	     * TODO To change the template for this generated type comment go to
	     * Window - Preferences - Java - Code Style - Code Templates
	     */
	    
	    public void updateProductStructureInavlidMappingInfo(List invalidLineInfoList)throws SevereException{

			Logger.logInfo("Entering the method for getting Questions and PVA");
			HashMap criteriaMap;
			InvalidLineInfo invalidLineInfo;
			SearchResults searchResults;
			SearchCriteria searchCriteria;
			
			//AdapterUtil.beginTransaction()
			
			//reset first
			invalidLineInfo=(InvalidLineInfo)invalidLineInfoList.get(0);
			criteriaMap= new HashMap();
			criteriaMap.put("entitySysId",new Integer(invalidLineInfo.getEntitySysId()));
			criteriaMap.put("benefitComponentId", new Integer(invalidLineInfo.getBenefitComponentId()));
			criteriaMap.put("benefitSysId","");
			criteriaMap.put("levelSysId","");
			criteriaMap.put("lineSysId","");
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					InvalidLineInfo.class.getName(),
					"resetInvalidMappingInfo", criteriaMap);		   
			searchResults = AdapterUtil.performSearch(searchCriteria);
			
			
			for(int i=0; i< invalidLineInfoList.size();i++){
			
				invalidLineInfo=(InvalidLineInfo)invalidLineInfoList.get(i);
				criteriaMap= new HashMap();
				criteriaMap.put("entitySysId",new Integer(invalidLineInfo.getEntitySysId()));
				criteriaMap.put("benefitComponentId", new Integer(invalidLineInfo.getBenefitComponentId()));
				criteriaMap.put("benefitSysId",invalidLineInfo.getBenefitSysId());
				criteriaMap.put("levelSysId",invalidLineInfo.getLevelSysId());
				criteriaMap.put("lineSysId","");
				searchCriteria= AdapterUtil.getAdapterSearchCriteria(
						InvalidLineInfo.class.getName(),
						"updateInvalidMappingInfo", criteriaMap);		   
				searchResults = AdapterUtil.performSearch(searchCriteria);
			}
		}
	    public void updateProductInavlidMappingInfo(List invalidLineInfoList)throws SevereException, AdapterException{
			
			Logger.logInfo("Entering the method for getting Questions and PVA");
			
			HashMap criteriaMap;
			InvalidLineInfo invalidLineInfo;
			SearchResults searchResults;
			SearchCriteria searchCriteria;
			//reset first
			invalidLineInfo=(InvalidLineInfo)invalidLineInfoList.get(0);
			criteriaMap= new HashMap();
			criteriaMap.put("entitySysId",new Integer(invalidLineInfo.getEntitySysId()));
			criteriaMap.put("benefitComponentId", new Integer(invalidLineInfo.getBenefitComponentId()));
			criteriaMap.put("benefitSysId","");
			criteriaMap.put("levelSysId","");
			criteriaMap.put("lineSysId","");
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					InvalidLineInfo.class.getName(),
					"resetProductInvalidMappingInfo", criteriaMap);		   
			searchResults = AdapterUtil.performSearch(searchCriteria);
			try{
			for(int i=0; i< invalidLineInfoList.size();i++){
				invalidLineInfo=(InvalidLineInfo)invalidLineInfoList.get(i);
				criteriaMap = new HashMap();
				criteriaMap.put("entitySysId",new Integer(invalidLineInfo.getEntitySysId()));
				criteriaMap.put("benefitComponentId", new Integer(invalidLineInfo.getBenefitComponentId()));
				criteriaMap.put("benefitSysId",invalidLineInfo.getBenefitSysId());
				criteriaMap.put("levelSysId",invalidLineInfo.getLevelSysId());
				criteriaMap.put("lineSysId",invalidLineInfo.getLineSysId());
				 searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						InvalidLineInfo.class.getName(),
						"updateProductInvalidMappingInfo", criteriaMap);		   
				searchResults = AdapterUtil.performSearch(searchCriteria);
			}
			}catch(Exception ex){
	    		throw new AdapterException("Exception occured while adapter call",ex);
	    	}
		}
	    
	    public void updateInvalidQuestionInSaveforPS(ProductStructureBO productStructureBO,User user)throws SevereException{
	    	
	    	Logger.logInfo("Entering the method for getting Questions and PVA");
			HashMap criteriaMap = new HashMap();
			criteriaMap.put("entitySysId",new Integer(productStructureBO.getProductStructureId()));
			criteriaMap.put("lastUpdatedUser",user.getUserId());
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					InvalidLineInfo.class.getName(),
					"updateProductStructureInvalidQuestionsInfo", criteriaMap);		   
			SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
	    	
	    }
	    /**
	     * This method will call a procedure and do the pva validations.
	     * The procedure name is updateProductInvalidQuestionsInfo
	     * @param productBO
	     * @param user
	     * @throws SevereException
	     */
	    		
	    public void updateInvalidQuestionInSaveforProduct(ProductBO productBO,User user)throws SevereException{
	    	
	    	Logger.logInfo("Entering the method for getting Questions and PVA");
			HashMap criteriaMap = new HashMap();
			criteriaMap.put("entitySysId",new Integer(productBO.getProductKey()));
			criteriaMap.put("lastUpdatedUser",user.getUserId());
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					InvalidLineInfo.class.getName(),
					"updateProductInvalidQuestionsInfo", criteriaMap);		   
			SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
	    	
	    }
	    
	    /**
		 * 
		 * @param benefitComponentId
		 * @param productStructureId
		 * @return
		 */
		
		public List retrieveBenefitTieredLines(int benefitComponentId, ProductBO productBO)
		throws SevereException{
			
			
			Logger.logInfo("Entering the method for getting benefit tiered Lines ");
			HashMap criteriaMap = new HashMap();
			criteriaMap.put("entitySysId",new Integer(productBO.getProductKey()));
			criteriaMap.put("benefitComponentId", new Integer(benefitComponentId));
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					BenefitLine.class.getName(),
					"getAllTieredLinesProduct", criteriaMap);		   
			SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
			Logger.logInfo("Returning the method for getting benefit tiered Lines");		   
			if (null != searchResults)
				return searchResults.getSearchResults();
			else
				return null;	
			
		}
		
		public void updateProductTierInavlidMappingInfo(List invalidLineInfoList)throws SevereException{
			Logger.logInfo("Entering the method for getting Questions and PVA");
			HashMap criteriaMap;
			InvalidLineInfo invalidLineInfo;
			SearchResults searchResults;
			SearchCriteria searchCriteria;
			
			//reset first
			invalidLineInfo=(InvalidLineInfo)invalidLineInfoList.get(0);
			criteriaMap= new HashMap();
			criteriaMap.put("entitySysId",new Integer(invalidLineInfo.getEntitySysId()));
			criteriaMap.put("benefitComponentId", new Integer(invalidLineInfo.getBenefitComponentId()));
			criteriaMap.put("benefitSysId","");
			criteriaMap.put("levelSysId","");
			criteriaMap.put("lineSysId","");
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					InvalidLineInfo.class.getName(),
					"resetProductTierInvalidMappingInfo", criteriaMap);		   
			searchResults = AdapterUtil.performSearch(searchCriteria);
			
			for(int i=0; i< invalidLineInfoList.size();i++){
				invalidLineInfo=(InvalidLineInfo)invalidLineInfoList.get(i);
				criteriaMap = new HashMap();
				criteriaMap.put("entitySysId",new Integer(invalidLineInfo.getEntitySysId()));
				criteriaMap.put("benefitComponentId", new Integer(invalidLineInfo.getBenefitComponentId()));
				criteriaMap.put("benefitSysId",invalidLineInfo.getBenefitSysId());
				criteriaMap.put("levelSysId",invalidLineInfo.getLevelSysId());
				criteriaMap.put("lineSysId",invalidLineInfo.getLineSysId());
				searchCriteria = AdapterUtil.getAdapterSearchCriteria(
						InvalidLineInfo.class.getName(),
						"updateProductTierInvalidMappingInfo", criteriaMap);		   
				searchResults = AdapterUtil.performSearch(searchCriteria);
			}
				
		}
		public void updateNewTierLineMapping(int productid,int benefitcomponentid,User user)throws SevereException{
			Logger.logInfo("Entering the method for getting Questions and PVA");
			
			HashMap criteriaMap;
			SearchResults searchResults;
			SearchCriteria searchCriteria;
			criteriaMap= new HashMap();
			criteriaMap.put("entitySysId",new Integer(productid));
			criteriaMap.put("benefitComponentId", new Integer(benefitcomponentid));
			criteriaMap.put("lastUpdatedUser",user.getUserId());
			searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					InvalidLineInfo.class.getName(),
					"updateNewTierLineMapping", criteriaMap);		   
			searchResults = AdapterUtil.performSearch(searchCriteria);
		}
		
		public void updateInvalidTierQuestionInProduct(ProductBO productBO,User user)throws SevereException{
	    	
	    	Logger.logInfo("Entering the method updateInvalidTierQuestionInSaveforProduct");
			HashMap criteriaMap = new HashMap();
			criteriaMap.put("entitySysId",new Integer(productBO.getProductKey()));
			criteriaMap.put("lastUpdatedUser",user.getUserId());
			SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
					InvalidLineInfo.class.getName(),
					"updateProductInvalidTierQuestionsInfo", criteriaMap);		   
			SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
	    	
	    }
		/**
		 * @param entitySysId
		 * @return
		 * @throws SevereException
		 */
		public boolean isPvaValidationNeeded(int entitySysId,String type) throws SevereException {
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

		/**
		 * @param productStructureId
		 * 
		 * @throws AdapterException
		 */
		public void unhideLinesInProductStructure(int productStructureId) throws AdapterException {
			 AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
			
				try{
					ProductStructureLineUpdationBO lineupdationbo = new ProductStructureLineUpdationBO();
					lineupdationbo.setProductStrucureId(productStructureId);
			    	AdapterUtil
			                .performUpdate(lineupdationbo, WebConstants.USER,adapterServicesAccess);
			    	}catch(Exception ex){
			    		throw new AdapterException("Exception occured while adapter call",ex);
			    	}
		
			
		}

		/**
		 * @param productKey
		 * @throws AdapterException
		 */
		public void unhideLinesInProduct(int productKey) throws AdapterException {
			AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();

				try{
					ProductLineUpdationBO lineupdationbo = new ProductLineUpdationBO();
					lineupdationbo.setProductId(productKey);
			    	AdapterUtil
			                .performUpdate(lineupdationbo, WebConstants.USER,adapterServicesAccess);
			    	}catch(Exception ex){
			    		throw new AdapterException("Exception occured while adapter call",ex);
			    	}
		}
		
}
