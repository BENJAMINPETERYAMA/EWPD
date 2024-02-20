/*
 * Created on Jul 27, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.pva.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.product.builder.ProductBusinessObjectBuilder;
import com.wellpoint.wpd.business.productstructure.builder.ProductStructureBusinessObjectBuilder;
import com.wellpoint.wpd.business.pva.adapter.PVAAdapterManager;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.contract.model.Messages;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.product.bo.ProductComponentBO;
import com.wellpoint.wpd.common.product.bo.ProductQuestionareBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureAssociatedBenefitComponent;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureQuestionnaireBO;
import com.wellpoint.wpd.common.pva.InvalidLineInfo;
import com.wellpoint.wpd.common.pva.PVAMapping;
import com.wellpoint.wpd.common.pva.PVAvalidationResult;
import com.wellpoint.wpd.util.TimeHandler;

/**
 * @author u14768
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PVABusinessObjectBuilder   {
	/**
	 * Method to perform pvalidation validation for lines and questions in product structure.
	 * 
	 * @param ProductStructureBO
	 * @param benefitComponentsList
	 * @param user
	 * @throws SevereException
	 */
	
	public PVAvalidationResult doPVAvalidation(ProductStructureBO productStructure,List benefitComponentsList,User user,boolean validateQuestion)throws SevereException{

		AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		PVAAdapterManager adapterManager = new PVAAdapterManager();
		PVAvalidationResult validationResult= new PVAvalidationResult();
		try
		{
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"doPVAvalidation(ProductStructureBO productStructure,List benefitComponentsList,User user)");
			if(adapterManager.isPvaValidationNeeded(productStructure.getProductStructureId(),"struture")){
			
			ProductStructureBusinessObjectBuilder builder = new ProductStructureBusinessObjectBuilder();
			
			List invalidBcList = hideInvalidLines(benefitComponentsList,productStructure,user);
    		if(invalidBcList.size()>0){
    			AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
    			Audit audit = auditFactory.getAudit(user);
				validationResult.setDletedComponentList(invalidBcList);
    			for(int i=0;i<invalidBcList.size();i++){
    				ProductStructureAssociatedBenefitComponent  associatedBenefitComponent = (ProductStructureAssociatedBenefitComponent)invalidBcList.get(i);
    				List bcList = new ArrayList();
    				bcList.add(new Integer(associatedBenefitComponent.getBenefitComponentId()));
    				associatedBenefitComponent.setBenefitComponentDeleteList(bcList);
    				builder.deleteComponent(associatedBenefitComponent,productStructure,audit,adapterServicesAccess);
    			}
    		}
    	//	if(adapterManager.isPvaValidationNeeded(productStructure.getProductStructureId(),"struture")){
//    		if(validateQuestion)
//    		validateQuestions(productStructure,user);
    		}else{
    			
    			//unhiding all the lines 
    			adapterManager.unhideLinesInProductStructure(productStructure.getProductStructureId());
    			
    		}
    		AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil.logTheEndTransaction(transactionId,this,"doPVAvalidation(ProductStructureBO productStructure,List benefitComponentsList,User user");
		}
		catch (SevereException ex)
		{
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil.logAbortTxn(transactionId,this,"doPVAvalidation(ProductStructureBO productStructure,List benefitComponentsList,User user");
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException("Exception occured in validatePVAs(ProductBO product, List benefitComponentsList, User user),PVABusinessObjectBuilder", errorParams, ex);
		}
		catch (Exception e)
		{
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil.logAbortTxn(transactionId,this,"doPVAvalidation(ProductStructureBO productStructure,List benefitComponentsList,User user");
			throw new SevereException("Unhandled exception is caught", null, e);
		}
		return validationResult;
	}
	
	
	/**
	 * Method to perform pvalidation validation for lines and questions in product.
	 * 
	 * @param product
	 * @param benefitComponentsList
	 * @param user
	 * @throws SevereException
	 */
	
	public PVAvalidationResult doPVAvalidation(ProductBO product,List benefitComponentsList,User user,boolean validateQuestion)throws SevereException{
		AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		PVAAdapterManager adapterManager = new PVAAdapterManager();
		PVAvalidationResult validationResult= new PVAvalidationResult();
		try
		{
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
			.logBeginTranstn(
					transactionId,
					this,
					"doPVAvalidation(ProductBO product,List benefitComponentsList,User user)");
			/*
			 * pva validation for all the question in remaining valid benefit components
			 */
			if(adapterManager.isPvaValidationNeeded(product.getProductKey(),"product")){
				
			ProductBusinessObjectBuilder builder = new ProductBusinessObjectBuilder();
			//validate benefit component lines,hide invaalid lines and -
			//returns invalid benefit component list.
			List invalidBcList = hideInvalidLines(
					benefitComponentsList, product, user);
			if (invalidBcList.size() > 0) {
				//creating audit object 
				AuditFactory auditFactory = (AuditFactory) ObjectFactory
				.getFactory(ObjectFactory.AUDIT);
    			Audit audit = auditFactory.getAudit(user);
				validationResult.setDletedComponentList(invalidBcList);
				//looping invalid bc list and deleting 
				for(int i=0;i<invalidBcList.size();i++){
					ProductComponentBO componentBO = (ProductComponentBO)invalidBcList.get(i);
					List bcList = new ArrayList();
					bcList.add(new Integer(componentBO.getComponentKey()));
					componentBO.setBenefitComponentDeleteList(bcList);
					//delete call for benefit component
					boolean benefitComponentDeleted =builder.deleteComponent(componentBO, product,audit,adapterServicesAccess);
				}
			}
//			if(validateQuestion)
//			validateQuestions(product,user);
			}else{
				adapterManager.unhideLinesInProduct(product.getProductKey());
			}
			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil.logTheEndTransaction(transactionId,this,"doPVAvalidation(ProductBO product,List benefitComponentsList,User user)");
		}
		catch (SevereException ex)
		{
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil.logAbortTxn(transactionId,this,"doPVAvalidation(ProductBO product,List benefitComponentsList,User user)");
			List errorParams = new ArrayList();
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException("Exception occured in doPVAvalidation(ProductBO product,List benefitComponentsList,User user),PVABusinessObjectBuilder", errorParams, ex);
		}
		catch (Exception e)
		{
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil.logAbortTxn(transactionId,this,"doPVAvalidation(ProductBO product,List benefitComponentsList,User user)");
			throw new SevereException("Unhandled exception is caught", null, e);
		}
		return validationResult;
	}
	
	public List retrievePVAs(String productFamily, List businessEntity) throws SevereException{
		List matchingPVAs;
		PVAAdapterManager adapterManager= new PVAAdapterManager();
		
		try{
			matchingPVAs=adapterManager.retrievePVAs(productFamily,businessEntity);
		}catch (SevereException exception){
			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);
			throw new SevereException(
					"Exception occured in retrivePVA method",
					errorParams, exception);
		}		
    	return matchingPVAs;
	}
	
	/*
	 * 
	 * 
	 */
	public List getInvalidBenefitLines(List benefitLines, Map applicablePVAMap) {
		List invalidLines = new ArrayList();

		if (null != benefitLines) {
			int lineSize = benefitLines.size();
			for (int j = 0; j < lineSize; j++) {
				
				if ((((BenefitLine) benefitLines.get(j))
						.getProviderArrangementId()) != null
						&& !applicablePVAMap
								.containsKey(((BenefitLine) benefitLines.get(j))
										.getProviderArrangementId())) {
					//This line's PVA is not in the ApplicablePVA set
					invalidLines.add(benefitLines.get(j));
				}
			
			}
		}

		return invalidLines;

	}
	
	public List getValidBenefitLines(List benefitLines, Map applicablePVAMap) {
		List validLines = new ArrayList();

		if (null != benefitLines) {
			int lineSize = benefitLines.size();
			for (int j = 0; j < lineSize; j++) {
				if ((((BenefitLine) benefitLines.get(j))
						.getProviderArrangementId()) != null
						&& applicablePVAMap
								.containsKey(((BenefitLine) benefitLines.get(j))
										.getProviderArrangementId())) {
					//This line's PVA is not in the ApplicablePVA set
					validLines.add(benefitLines.get(j));
				}
			}
		}
		return validLines;

	}
	
	
	/**
	 * 
	 * @param benefitComponentId
	 * @param productStructureBO
	 * @return
	 * @throws SevereException
	 */
	
	private List retrieveBenefitLines(int benefitComponentId, ProductStructureBO productStructureBO) throws SevereException{

		PVAAdapterManager adapterManager= new PVAAdapterManager();
		List lines = adapterManager.retrieveBenefitLines(benefitComponentId,productStructureBO);
		return lines;
	}
	
	/**
	 * 
	 * @param benefitComponentId
	 * @param productBO
	 * @return
	 * @throws SevereException
	 */
	private List retrieveBenefitTieredLines(int benefitComponentId, ProductBO productBO)throws SevereException{
		PVAAdapterManager adapterManager= new PVAAdapterManager();
		List lines = adapterManager.retrieveBenefitTieredLines(benefitComponentId,productBO);
		return lines;
	
	}
	
	/**
	 * 
	 * @param benefitComponentId
	 * @param productBO
	 * @return
	 * @throws SevereException
	 */
	private List retrieveBenefitLines(int benefitComponentId, ProductBO productBO)throws SevereException{
		PVAAdapterManager adapterManager= new PVAAdapterManager();
		List lines = adapterManager.retrieveBenefitLines(benefitComponentId,productBO);
		return lines;
	
	}
	
	public List hideInvalidLines(List benefitComponents,
			ProductStructureBO productStructureBO,User user) throws SevereException {
		
		List businessEntity = new ArrayList();
		List lines = null;
		List applicablePVAs = null;
		List invalidLines = null;
		List validlines = null;
		Set validBenefits=null;
		Set validLevels=null;
		List invalidBC = new ArrayList();
		PVAAdapterManager adapterManager= new PVAAdapterManager();
		
		try {
			applicablePVAs = retrievePVAs(productStructureBO
					.getProductFamilyId(), businessEntity);
		} catch (SevereException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	if(applicablePVAs!=null && applicablePVAs.size()>0){// validation is needed only if applicablePVAs not null 
		
		Map applicablePVAMap = new HashMap();
		int totalPVACount = applicablePVAs.size();

		//putting all PVAs to a map
		for (int i = 0; i < totalPVACount; i++) {
			applicablePVAMap.put(((PVAMapping) applicablePVAs.get(i))
					.getPVAId(), ((PVAMapping) applicablePVAs.get(i))
					.getPVAId());
		}
		
		//***************************Benefit hiding section begins here***************************//
		int benefitComponentSize = benefitComponents.size();
		if (null != benefitComponents) {
			for (int i = 0; i < benefitComponentSize; i++) {
				int benefitComponentId = ((ProductStructureAssociatedBenefitComponent) benefitComponents
						.get(i)).getBenefitComponentId();
				try {
					
					lines = retrieveBenefitLines(benefitComponentId,
							productStructureBO);
					
				} catch (SevereException e) {
					e.printStackTrace();
				}
				
				invalidLines = getInvalidBenefitLines(lines, applicablePVAMap);
				
				validlines = getValidBenefitLines(lines, applicablePVAMap);
				
				List invalidLineIds = new ArrayList();
				
				for(int v=0;v< invalidLines.size();v++){
					invalidLineIds.add(new Integer((((BenefitLine)invalidLines.get(v)).getLineSysId())));
				}
				
				
				/**
				 * valid benefits and levels are collected so that we can 
				 * delete all invalid benefits and hide all invalid levels
				 */
				
				validBenefits = new HashSet(); 
				validLevels = new HashSet();
				
				//Filter out duplicates
				for(int j=0;j<validlines.size();j++){
					validBenefits.add(new Integer(((BenefitLine)validlines.get(j)).getBenefitSysId()));
					validLevels.add(new Integer(((BenefitLine)validlines.get(j)).getLevelSysId()));
				}
				
				List validBenefitList = new ArrayList();
				List validLevelList = new ArrayList();
				
				validBenefitList.addAll(validBenefits);
				
				validLevelList.addAll(validLevels);
				
				if(lines.size()==invalidLines.size()){
					invalidBC.add(benefitComponents.get(i));
					
				}else{
					
					InvalidLineInfo invalidLineInfo;
					List invalidLineInfoList = new ArrayList();
					
					invalidLineInfo= new InvalidLineInfo();
					invalidLineInfo.setEntitySysId(productStructureBO.getProductStructureId());
					invalidLineInfo.setBenefitComponentId(benefitComponentId);
					invalidLineInfo.setBenefitSysId(StringUtil.commaSeperate(validBenefitList));
					invalidLineInfo.setLevelSysId("na");
					invalidLineInfo.setLineSysId("na");
					invalidLineInfoList.add(invalidLineInfo);
					
					
					/*
					 * 
					 */
					
					
					List validLevelIdSet= new ArrayList();;
					
					int firstLevelBatchNos = validLevelList.size() - (validLevelList.size() % 200);
					
					for(int y=1;y <= firstLevelBatchNos;y++){
						
						if((y % 200)!=0)
							validLevelIdSet.add(validLevelList.get(y-1));
						else{
							validLevelIdSet.add(validLevelList.get(y-1));
							
							invalidLineInfo= new InvalidLineInfo();
							invalidLineInfo.setEntitySysId(productStructureBO.getProductStructureId());
							invalidLineInfo.setBenefitComponentId(benefitComponentId);
							invalidLineInfo.setBenefitSysId("na");
							invalidLineInfo.setLevelSysId(StringUtil.commaSeperate(validLevelIdSet));
							invalidLineInfo.setLineSysId("na");
							
							invalidLineInfoList.add(invalidLineInfo);
							
							validLevelIdSet= new ArrayList();
						}
							
					}
					if((validLevelList.size() % 200 ) > 0){
						validLevelIdSet= new ArrayList();
						
						for(int k =firstLevelBatchNos; k<validLevelList.size(); k++)
							validLevelIdSet.add(validLevelList.get(k));
						
						invalidLineInfo= new InvalidLineInfo();
						invalidLineInfo.setEntitySysId(productStructureBO.getProductStructureId());
						invalidLineInfo.setBenefitComponentId(benefitComponentId);
						invalidLineInfo.setBenefitSysId("na");
						invalidLineInfo.setLevelSysId(StringUtil.commaSeperate(validLevelIdSet));
						invalidLineInfo.setLineSysId("na");
						
						invalidLineInfoList.add(invalidLineInfo);
					
					}
					/*
					 * 
					*/
					List invalidLineIdSet= new ArrayList();;
					
					int firstBatchNos = invalidLineIds.size() - (invalidLineIds.size() % 200);
					
					for(int y=1;y <= firstBatchNos;y++){
						
						if((y % 200)!=0)
							invalidLineIdSet.add(invalidLineIds.get(y-1));
						else{
							invalidLineIdSet.add(invalidLineIds.get(y-1));
							
							invalidLineInfo= new InvalidLineInfo();
							invalidLineInfo.setEntitySysId(productStructureBO.getProductStructureId());
							invalidLineInfo.setBenefitComponentId(benefitComponentId);
							invalidLineInfo.setBenefitSysId("na");
							invalidLineInfo.setLevelSysId("na");
							invalidLineInfo.setLineSysId(StringUtil.commaSeperate(invalidLineIdSet));
							
							invalidLineInfoList.add(invalidLineInfo);
							
							invalidLineIdSet= new ArrayList();
						}
							
					}
					
					if((invalidLineIds.size() % 200 ) > 0){
						invalidLineIdSet= new ArrayList();
						
						for(int k =firstBatchNos; k<invalidLineIds.size(); k++)
							invalidLineIdSet.add(invalidLineIds.get(k));
						
						invalidLineInfo= new InvalidLineInfo();
						invalidLineInfo.setEntitySysId(productStructureBO.getProductStructureId());
						invalidLineInfo.setBenefitComponentId(benefitComponentId);
						invalidLineInfo.setBenefitSysId("na");
						invalidLineInfo.setLevelSysId("na");
						invalidLineInfo.setLineSysId(StringUtil.commaSeperate(invalidLineIdSet));
						
						invalidLineInfoList.add(invalidLineInfo);
					
					}
					
					adapterManager.updateProductStructureInavlidMappingInfo(invalidLineInfoList);
				}
				
				

			}
		}
	}
		return invalidBC;
	}
	/**
		 * In this method pva validation willhappen for each line in the benefit component.
		 * If the line is invalid,it will be hide.
	 * @param benefitComponents
	 * @param productBO
	 * @param user
	 * @return
	 * @throws SevereException
	 * @throws AdapterException
	 */
	
	
	public List hideInvalidLines(List benefitComponents,
			ProductBO productBO,User user) throws SevereException, AdapterException {	
		List lines = null;
		List tieredLines = null;
		List applicablePVAs = null;
		List invalidLines = null;
		List validlines = null;
		Set validBenefits=null;
		Set validLevels=null;
		List invalidBC = new ArrayList();
		List businessEntities = new ArrayList();
		PVAAdapterManager adapterManager= new PVAAdapterManager();		
		for(int i=0;i<productBO.getBusinessDomains().size();i++){
			businessEntities.add(((Domain)productBO.getBusinessDomains().get(i)).getBusinessEntity());
		}		
		try {
			// retrieving applicable pva using product family and business entity from db 
			applicablePVAs = retrievePVAs(productBO.getProductFamilyId(), businessEntities);
		} catch (SevereException e1) {
			
			e1.printStackTrace();
			throw e1;
		}
		// validation is needed only if applicablePVAs not null 
        if(applicablePVAs!=null && applicablePVAs.size()>0){
		Map applicablePVAMap = new HashMap();
		int totalPVACount = applicablePVAs.size();
		
		//putting all PVAs to a map
		for (int i = 0; i < totalPVACount; i++) {
			applicablePVAMap.put(((PVAMapping) applicablePVAs.get(i))
					.getPVAId(), null);
		}
		int benefitComponentSize = benefitComponents.size();
		if (null != benefitComponents) {
			for (int i = 0; i < benefitComponentSize; i++) {
				int benefitComponentId = ((ProductComponentBO) benefitComponents
						.get(i)).getComponentKey();
				try {
					
					lines = retrieveBenefitLines(benefitComponentId,
							productBO);
			
				} catch (SevereException e) {
					throw e;
				}
				
				invalidLines = getInvalidBenefitLines(lines, applicablePVAMap);
				
				
				validlines = getValidBenefitLines(lines, applicablePVAMap);
				
				
				List invalidLineIds = new ArrayList();
				
				
				
				for(int v=0;v< invalidLines.size();v++){
					invalidLineIds.add(new Integer((((BenefitLine)invalidLines.get(v)).getLineSysId())));
				}
			
				
				/**
				 * valid benefits and levels are collected so that we can 
				 * delete all invalid benefits and hide all invalid levels
				 */
				
				validBenefits = new HashSet(); 
				validLevels = new HashSet();
								
				//Filter out duplicates
				for(int j=0;j<validlines.size();j++){
					validBenefits.add(new Integer(((BenefitLine)validlines.get(j)).getBenefitSysId()));
					validLevels.add(new Integer(((BenefitLine)validlines.get(j)).getLevelSysId()));
				}
				
				List validBenefitList = new ArrayList();
				List validLevelList = new ArrayList();
				
				validBenefitList.addAll(validBenefits);
				validLevelList.addAll(validLevels);
				
				if(lines.size()==invalidLines.size()){
					invalidBC.add(benefitComponents.get(i));
					
				}else{
					
					InvalidLineInfo invalidLineInfo;
					List invalidLineInfoList = new ArrayList();
					invalidLineInfo= new InvalidLineInfo();
					invalidLineInfo.setEntitySysId(productBO.getProductKey());
					invalidLineInfo.setBenefitComponentId(benefitComponentId);
					invalidLineInfo.setBenefitSysId(StringUtil.commaSeperate(validBenefitList));
					invalidLineInfo.setLevelSysId("na");
					invalidLineInfo.setLineSysId("na");
					invalidLineInfoList.add(invalidLineInfo);
					
					
					
					/*
					 * 
					 */
					
					
					List validLevelIdSet= new ArrayList();;
					
					int firstLevelBatchNos = validLevelList.size() - (validLevelList.size() % 200);
					
					for(int y=1;y <= firstLevelBatchNos;y++){
						
						if((y % 200)!=0)
							validLevelIdSet.add(validLevelList.get(y-1));
						else{
							validLevelIdSet.add(validLevelList.get(y-1));
							
							invalidLineInfo= new InvalidLineInfo();
							invalidLineInfo.setEntitySysId(productBO.getProductKey());
							invalidLineInfo.setBenefitComponentId(benefitComponentId);
							invalidLineInfo.setBenefitSysId("na");
							invalidLineInfo.setLevelSysId(StringUtil.commaSeperate(validLevelIdSet));
							invalidLineInfo.setLineSysId("na");
							
							invalidLineInfoList.add(invalidLineInfo);
							
							validLevelIdSet= new ArrayList();
						}
							
					}
					
					if((validLevelList.size() % 200 ) > 0){
						validLevelIdSet= new ArrayList();
						
						for(int k =firstLevelBatchNos; k<validLevelList.size(); k++)
							validLevelIdSet.add(validLevelList.get(k));
						
						invalidLineInfo= new InvalidLineInfo();
						invalidLineInfo.setEntitySysId(productBO.getProductKey());
						invalidLineInfo.setBenefitComponentId(benefitComponentId);
						invalidLineInfo.setBenefitSysId("na");
						invalidLineInfo.setLevelSysId(StringUtil.commaSeperate(validLevelIdSet));
						invalidLineInfo.setLineSysId("na");
						
						invalidLineInfoList.add(invalidLineInfo);
					
					}
					/*
					 * 
					*/
					
					
					
					
				
					List invalidLineIdSet= new ArrayList();;
					
					int firstBatchNos = invalidLineIds.size() - (invalidLineIds.size() % 200);
					
					for(int y=1;y <= firstBatchNos;y++){
						
						if((y % 200)!=0)
							invalidLineIdSet.add(invalidLineIds.get(y-1));
						else{
							invalidLineIdSet.add(invalidLineIds.get(y-1));
							
							invalidLineInfo= new InvalidLineInfo();
							invalidLineInfo.setEntitySysId(productBO.getProductKey());
							invalidLineInfo.setBenefitComponentId(benefitComponentId);
							invalidLineInfo.setBenefitSysId("na");
							invalidLineInfo.setLevelSysId("na");
							invalidLineInfo.setLineSysId(StringUtil.commaSeperate(invalidLineIdSet));
							
							invalidLineInfoList.add(invalidLineInfo);
							
							invalidLineIdSet= new ArrayList();
						}
							
					}
					
					
					if((invalidLineIds.size() % 200 ) > 0){
						invalidLineIdSet= new ArrayList();
						
						for(int k =firstBatchNos; k<invalidLineIds.size(); k++)
							invalidLineIdSet.add(invalidLineIds.get(k));
						
						invalidLineInfo= new InvalidLineInfo();
						invalidLineInfo.setEntitySysId(productBO.getProductKey());
						invalidLineInfo.setBenefitComponentId(benefitComponentId);
						invalidLineInfo.setBenefitSysId("na");
						invalidLineInfo.setLevelSysId("na");
						invalidLineInfo.setLineSysId(StringUtil.commaSeperate(invalidLineIdSet));
						
						invalidLineInfoList.add(invalidLineInfo);
					
					}
					
					try{
						adapterManager.updateProductInavlidMappingInfo(invalidLineInfoList); 
					} catch (Exception e)
					{
						throw new SevereException("Exception occures in PVABusinessObjectBuilder ", null, e);
					}
				}
	// end of normal lines and start of tiered Lines 
			
				processTier(benefitComponentId,productBO,applicablePVAMap,user);
				
			}
			
		}
        }
		return invalidBC;		
	}
	
	
	private List retrieveQuestions(int benefitComponentId, ProductBO productBO)throws SevereException{
		PVAAdapterManager adapterManager= new PVAAdapterManager();		
		List questions = adapterManager.retrieveQuestions(benefitComponentId,productBO);
		return questions;
	}

	/**
	 * 
	 * @param applicablePVAs
	 * @param benefitComponents
	 * @param productStructureBO
	 * @return
	 * @throws SevereException
	 */
	public Messages removeInvalidQuestions(List applicablePVAs,List benefitComponents,ProductStructureBO productStructureBO) throws SevereException{
		
		List questions = null;
		Map applicablePVAMap = new HashMap();
		int totalPVACount=applicablePVAs.size();
		PVAAdapterManager adapterManager= new PVAAdapterManager();
		
		Messages messages = new Messages();
		
		//putting all PVAs to a map
		for (int i=0;i<totalPVACount;i++){
			applicablePVAMap.put(((PVAMapping)applicablePVAs.get(i)).getPVAId(),null);
		}
		
		
		if(null!=benefitComponents && benefitComponents.size()>0){
			for(int i=0;i<benefitComponents.size();i++){
				int benefitComponentId =((ProductStructureAssociatedBenefitComponent)benefitComponents.get(i)).getBenefitComponentId();
				try {
					questions=retrieveQuestions(benefitComponentId,productStructureBO);
					if(null!=questions){
						int questionSize = questions.size();
						for(int j=0;j<questionSize;j++){
							if ((((ProductStructureQuestionnaireBO)questions.get(j)).getQuestionPVA())!=null && !applicablePVAMap.containsKey(((ProductStructureQuestionnaireBO)questions.get(j)).getQuestionPVA())){
								//This question's PVA is not in the ApplicablePVA set
								adapterManager.removeQuestion(benefitComponentId,((ProductStructureQuestionnaireBO)questions.get(j)).getQuestionId(),productStructureBO);
							}
						}
				}
		        } catch (SevereException ex) {
		            List errorParams = new ArrayList();
		            String obj = ex.getClass().getName();
		            errorParams.add(obj);
		            errorParams.add(obj.getClass().getName());
		            throw new SevereException(
		                    "Exception occured in removeInvalidQuestions method, in PVABusinessObjectBuilder",
		                    errorParams, ex);
		        } catch (Exception e) {
		            throw new SevereException("Unhandled exception is caught", null, e);
		        }
			}
		}
		
		return messages;
	}
	
	private List retrieveQuestions(int benefitComponentId, ProductStructureBO productStructureBO)throws SevereException{
		PVAAdapterManager adapterManager= new PVAAdapterManager();
		List questions = adapterManager.retrieveQuestions(benefitComponentId,productStructureBO);
		return questions;
	}
	
	public Messages removeInvalidQuestions(List applicablePVAs,List benefitComponets,ProductBO productBO) throws SevereException{
		
		List questions = null;
		Map applicablePVAMap = new HashMap();
		int totalPVACount=applicablePVAs.size();
		PVAAdapterManager adapterManager= new PVAAdapterManager();
		
		Messages messages = new Messages();
		
		//putting all PVAs to a map
		for (int i=0;i<totalPVACount;i++){
			applicablePVAMap.put(((PVAMapping)applicablePVAs.get(i)).getPVAId(),null);
		}
		
		
		if(null!=benefitComponets && benefitComponets.size()>0){
			for(int i=0;i<benefitComponets.size();i++){
				int benefitComponentId = Integer.parseInt(((ProductComponentBO)benefitComponets.get(i)).getComponentId());
				try {
					questions=retrieveQuestions(benefitComponentId,productBO);
					if(null!=questions){
						int questionSize = questions.size();
						for(int j=0;j<questionSize;j++){
							if ((((ProductQuestionareBO)questions.get(j)).getQuestionPVA())!=null && !applicablePVAMap.containsKey(((ProductQuestionareBO)questions.get(j)).getQuestionPVA())){
								//This question's PVA is not in the ApplicablePVA set
								adapterManager.removeQuestion(benefitComponentId,((ProductStructureQuestionnaireBO)questions.get(j)).getQuestionId(),productBO);
							}
						}
				}
		        } catch (SevereException ex) {
		            List errorParams = new ArrayList();
		            String obj = ex.getClass().getName();
		            errorParams.add(obj);
		            errorParams.add(obj.getClass().getName());
		            throw new SevereException(
		                    "Exception occured in removeInvalidQuestions method, in PVABusinessObjectBuilder",
		                    errorParams, ex);
		        } catch (Exception e) {
		            throw new SevereException("Unhandled exception is caught", null, e);
		        }
			}
		}
		//Remove
		/*if(null!=benefitComponets && benefitComponets.size()>0){
			for(int i=0;i<benefitComponets.size();i++){
				int benefitComponentId =((ProductComponentBO)benefitComponets.get(i)).getComponentKey();
				try {
					questions=retrieveQuestions(benefitComponentId,productBO);
					
				} catch (SevereException e) {
					e.printStackTrace();
				}
			}
		}
		*/
		return messages;
	}
	public void validateQuestions(ProductStructureBO productStructureBO,User user)throws SevereException{
		PVAAdapterManager adapterManager= new PVAAdapterManager();
		adapterManager.updateInvalidQuestionInSaveforPS(productStructureBO,user);
	}
	/**
	 * This method is for validating and removing question after pva validation
	 * @param productBO
	 * @param user
	 * @throws SevereException
	 */
//	public void validateQuestions(ProductBO productBO,User user)throws SevereException{
//		PVAAdapterManager adapterManager= new PVAAdapterManager();
//		adapterManager.updateInvalidQuestionInSaveforProduct(productBO,user);
//		adapterManager.updateInvalidTierQuestionInProduct(productBO,user);
//	}
	
	public void processTier(int benefitComponentId,ProductBO productBO,Map applicablePVAMap,User user) throws SevereException{
		List tieredLines = null;
		List invalidTieredLines = null;
		List validTieredLines = null;
		Set validTieredBenefits = null;
		Set validTieredLevels = null;
		PVAAdapterManager adapterManager= new PVAAdapterManager();	
		
		try {
// 			selecting all tier lines using componentId and product id 
			tieredLines  = retrieveBenefitTieredLines (benefitComponentId,productBO);
//			 selecting invalid tiered line using tieredLines and applicable pvas.
			invalidTieredLines = getInvalidBenefitLines(tieredLines, applicablePVAMap);
//			selecting valid tiered line using tieredLines and applicable pvas.
			validTieredLines = getInvalidBenefitLines(tieredLines, applicablePVAMap);
			List invalidTieredLineIds = new ArrayList();
			// forming a list of invalid tieredLineids using invalidTieredLines
			for(int i=0;i< invalidTieredLines.size();i++){
				invalidTieredLineIds.add(new Integer((((BenefitLine)invalidTieredLines.get(i)).getLineSysId())));
			}
			/**
			 * valid benefits and levels are collected so that we can 
			 * delete all invalid benefits and hide all invalid levels
			 */
			
			validTieredBenefits = new HashSet(); 
			validTieredLevels = new HashSet();
							
			//Filter out duplicates
			for(int j=0;j<validTieredLines.size();j++){
				validTieredBenefits.add(new Integer(((BenefitLine)validTieredLines.get(j)).getBenefitSysId()));
				validTieredLevels.add(new Integer(((BenefitLine)validTieredLines.get(j)).getLevelSysId()));
			}
			
			List validTieredBenefitList = new ArrayList();
			List validTieredLevelList = new ArrayList();
			
			validTieredBenefitList.addAll(validTieredBenefits);
			validTieredLevelList.addAll(validTieredLevels);
			
				
				InvalidLineInfo invalidLineInfo;
				List invalidLineInfoList = new ArrayList();
				
				invalidLineInfo= new InvalidLineInfo();
				invalidLineInfo.setEntitySysId(productBO.getProductKey());
				invalidLineInfo.setBenefitComponentId(benefitComponentId);
				invalidLineInfo.setBenefitSysId(StringUtil.commaSeperate(validTieredBenefitList));
				invalidLineInfo.setLevelSysId(StringUtil.commaSeperate(validTieredLevelList));
				invalidLineInfo.setLineSysId("na");
				
				invalidLineInfoList.add(invalidLineInfo);
			
				List invalidLineIdSet= new ArrayList();;
				
				int firstBatchNos = invalidTieredLineIds.size() - (invalidTieredLineIds.size() % 200);
				
				for(int y=1;y <= firstBatchNos;y++){
					
					if((y % 200)!=0)
						invalidLineIdSet.add(invalidTieredLineIds.get(y-1));
					else{
						invalidLineIdSet.add(invalidTieredLineIds.get(y-1));
						
						invalidLineInfo= new InvalidLineInfo();
						invalidLineInfo.setEntitySysId(productBO.getProductKey());
						invalidLineInfo.setBenefitComponentId(benefitComponentId);
						invalidLineInfo.setBenefitSysId("na");
						invalidLineInfo.setLevelSysId("na");
						invalidLineInfo.setLineSysId(StringUtil.commaSeperate(invalidLineIdSet));
						
						invalidLineInfoList.add(invalidLineInfo);
						
						invalidLineIdSet= new ArrayList();
					}
						
				}
				
				
				if((invalidTieredLineIds.size() % 200 ) > 0){
					invalidLineIdSet= new ArrayList();
					
					for(int k =firstBatchNos; k<invalidTieredLineIds.size(); k++)
						invalidLineIdSet.add(invalidTieredLineIds.get(k));
					
					invalidLineInfo= new InvalidLineInfo();
					invalidLineInfo.setEntitySysId(productBO.getProductKey());
					invalidLineInfo.setBenefitComponentId(benefitComponentId);
					invalidLineInfo.setBenefitSysId("na");
					invalidLineInfo.setLevelSysId("na");
					invalidLineInfo.setLineSysId(StringUtil.commaSeperate(invalidLineIdSet));
					
					invalidLineInfoList.add(invalidLineInfo);
				
				}
				
				
				adapterManager.updateProductTierInavlidMappingInfo(invalidLineInfoList);
				adapterManager.updateNewTierLineMapping(productBO.getProductKey(),benefitComponentId,user);
			
		} catch (SevereException e) {
			throw e;
		}
	}
}
