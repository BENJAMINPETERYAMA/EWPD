/*
 * MigrationContractUtil.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.migration.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.business.migration.builder.MigrationBusinessObjectBuilder;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.domain.bo.MigrationDomain;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.legacycontract.response.RetrieveLegacyContractResponse;
import com.wellpoint.wpd.common.migration.bo.BenefitComponentBenefit;
import com.wellpoint.wpd.common.migration.bo.BenefitComponentNote;
import com.wellpoint.wpd.common.migration.bo.BenefitNote;
import com.wellpoint.wpd.common.migration.bo.ConfirmMigrationContract;
import com.wellpoint.wpd.common.migration.bo.ConfirmMigrationContractForSave;
import com.wellpoint.wpd.common.migration.bo.MigrationContract;
import com.wellpoint.wpd.common.migration.bo.MigrationDateSegment;
import com.wellpoint.wpd.common.migration.bo.MigrationDomainInfo;
import com.wellpoint.wpd.common.migration.bo.MigrationPricing;
import com.wellpoint.wpd.common.migration.bo.MigrationVariable;
import com.wellpoint.wpd.common.migration.bo.NavigationInfo;
import com.wellpoint.wpd.common.migration.request.MigrationContractRequest;
import com.wellpoint.wpd.common.migration.request.MigrationPricingInfoRequest;
import com.wellpoint.wpd.common.migration.request.RetrieveMigVariableMappingRequest;
import com.wellpoint.wpd.common.migration.request.SaveMigGeneralInfoRequest;
import com.wellpoint.wpd.common.migration.response.MigrationContractResponse;
import com.wellpoint.wpd.common.migration.vo.MigProductMappingVO;
import com.wellpoint.wpd.common.migration.vo.MigrateNotesVO;
import com.wellpoint.wpd.common.migration.vo.MigrationContractSession;
import com.wellpoint.wpd.web.util.WPDStringUtil;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id:
 */
public class MigrationContractUtil {
    
	public static final String DONE_FLAG_SECOND = "S";
	public static final String DONE_FLAG_NO = "N";
	public static final String DONE_FLAG_YES = "Y";
	public static final String ACCUM_IND_FLAG_NO = "N";
	public static final String ACCUM_IND_YES = "Y";
	public static final String ACCUM_IND_BY = "BY";
	public static final String ACCUM_IND_CY = "CY";
	
    public static List convertToMigDomains(List lineOfBusiness, List businessEntity, List businessGroup, List marketBusinessUnitCodeList) 
    {
    	return convertToMigDomains( lineOfBusiness,  businessEntity,  businessGroup, marketBusinessUnitCodeList, true); 
    }
    public static List convertToMigDomains(List lineOfBusiness, List businessEntity, List businessGroup, List marketBusinessUnitCodeList, boolean checkUniversal) {

        Iterator iter1, iter2, iter3;
        String lineOfBusinessId, businessEntityId, businessGroupId;
		/*START CARS*/
        Iterator iter4;
        String MarketBusinessUnitId;
		/*END CARS*/
        List domainList = new ArrayList();
        MigrationDomainInfo domainBO;
        /*START CARS*/
        if(lineOfBusiness == null || businessEntity == null || businessGroup == null || marketBusinessUnitCodeList == null)
            return domainList;
        /*END CARS*/
        modifyDomainValues(lineOfBusiness, businessEntity, businessGroup, checkUniversal);
        
        for (iter1 = lineOfBusiness.iterator(); iter1.hasNext();) {
            lineOfBusinessId = (String) iter1.next();
            for (iter2 = businessEntity.iterator(); iter2.hasNext();) {
                businessEntityId = (String) iter2.next();
                for (iter3 = businessGroup.iterator(); iter3.hasNext();) {
                    businessGroupId = (String) iter3.next();
            		/*START CARS*/
                    for (iter4 = marketBusinessUnitCodeList.iterator(); iter4.hasNext();) {
                    	MarketBusinessUnitId = (String) iter4.next();
	                    domainBO = new MigrationDomainInfo();
	                    domainBO.setLobId(lineOfBusinessId);
	                    domainBO.setBusinessEntityId(businessEntityId);
	                    domainBO.setBusinessGroupId(businessGroupId);
	                    domainBO.setMarketBusinessUnitId(MarketBusinessUnitId);
	                    domainList.add(domainBO);
                    }
            		/*END CARS*/
                }               
            }            
        }
        Collections.sort(domainList);
        return domainList;
    }

    private static void modifyDomainValues(List lineOfBusiness, List businessEntity, List businessGroup, boolean checkUniversal) {
		Iterator iter = null;
		String item = null;
		for (iter = lineOfBusiness.iterator(); iter.hasNext();) {
			item = (String) iter.next();
			if (checkUniversal && BusinessConstants.UNIVERSAL_DOMAIN.equals(item)) {
				lineOfBusiness.clear();
				lineOfBusiness.add(BusinessConstants.UNIVERSAL_DOMAIN);
				break;
			}
		}
		for (iter = businessEntity.iterator(); iter.hasNext();) {
			item = (String) iter.next();
			if (checkUniversal && BusinessConstants.UNIVERSAL_DOMAIN.equals(item)) {
				businessEntity.clear();
				businessEntity.add(BusinessConstants.UNIVERSAL_DOMAIN);
				break;
			}
		}
		for (iter = businessGroup.iterator(); iter.hasNext();) {
			item = (String) iter.next();
			if (checkUniversal && BusinessConstants.UNIVERSAL_DOMAIN.equals(item)) {
				businessGroup.clear();
				businessGroup.add(BusinessConstants.UNIVERSAL_DOMAIN);
				break;
			}
		}
	}
	/**
	 * @param productMappingVO
	 * @param request
	 * @return
	 */
	public static Map getDetailsInMap(MigProductMappingVO productMappingVO, RetrieveMigVariableMappingRequest request) {
        HashMap map = new HashMap();
        map.put("bftSysId", new Integer(productMappingVO.getStdBenefitId()));
        map.put("bftCompSysId", new Integer(productMappingVO
                .getBenefitComponentId()));
        map.put("bftComponentId", new Integer(productMappingVO
                .getBenefitComponentId()));
        map.put("bftComponentSysId", new Integer(productMappingVO
                .getBenefitComponentId()));
        map.put("dateSegmentId", new Integer(productMappingVO
                .getDateSegmentId()));
        MigrationContractSession migrationContractSession = request.getMigrationContractSession();
        MigrationContract migrationContract =migrationContractSession.getMigrationContract();
        if(!StringUtil.isEmpty(migrationContract.getMigratedProdStructureMappingSysID())){
            map.put("mappingSysId", new Integer(migrationContract.getMigratedProdStructureMappingSysID()));
            map.put("prodStrmappingSysId", new Integer(migrationContract.getMigratedProdStructureMappingSysID()));
        }
        if(!StringUtil.isEmpty(migrationContract.getMigrationSystemId())){
            map.put("migContractSysId", new Integer(migrationContract.getMigrationSystemId()));
        }
        map.put("overridebftCompId", new Integer(productMappingVO
                .getBenefitComponentId()));
        if(!StringUtil.isEmpty(migrationContract.getEwpdProductSystemId())){
            map.put("productId", new Integer(migrationContract.getEwpdProductSystemId()));
        }
        return map;
    }
	
	/**
     * @param benefitList
     * @return
     */
	public static List getValueObjectBenefitList(List benefitList) {

        List valueObjectList = null;

        if (null != benefitList && !benefitList.isEmpty()) {
            valueObjectList = new ArrayList();
            for (int i = 0; i < benefitList.size(); i++) {

                MigrationVariable migrationVariable = (MigrationVariable) benefitList
                        .get(i);

                MigProductMappingVO migProductMappingVO = new MigProductMappingVO();
                if(null!=migrationVariable.getBftLineDescription()){
	                migProductMappingVO.setBftLineDescription(migrationVariable
	                        .getBftLineDescription());
                }else{
                	 migProductMappingVO.setBftLineDescription("");
                }
                if(null!=migrationVariable.getBftPva()){
                	migProductMappingVO.setBftPva(migrationVariable.getBftPva());
                }else{
                	migProductMappingVO.setBftPva("");
                }
                if(null!=migrationVariable.getBftReference()){
	                migProductMappingVO.setBftReference(migrationVariable
	                        .getBftReference());
                }else{
                	migProductMappingVO.setBftReference("");              
                }
                migProductMappingVO.setBftLineId(migrationVariable.getBftLineId());               
               	migProductMappingVO.setValue(migrationVariable.getVarValue());
                migProductMappingVO.setVariableId(migrationVariable.getVariableId());
                migProductMappingVO.setFlag(migrationVariable.getFlag());
                migProductMappingVO.setFormat(migrationVariable.getFormat());
                migProductMappingVO.setPva(migrationVariable.getPva());
                if(null!=migrationVariable.getVarDetails()){
                	migProductMappingVO.setVarDetails(migrationVariable.getVarDetails());
                }else{
                	migProductMappingVO.setVarDetails("");
                }
                migProductMappingVO.setNotesFlag(migrationVariable.getMigrateNotesFlag());
                migProductMappingVO.setVarNoteFlag(migrationVariable.getVarNoteFlag());
                valueObjectList.add(migProductMappingVO);
            }
        }
        return valueObjectList;
    }
	
    /**
     * 
     * @param migDS
     * @param saveRequest
     */
    public static void setValuesToMigDS(MigrationDateSegment migDS,SaveMigGeneralInfoRequest saveRequest){
        migDS.setSystemId(saveRequest.getDateSegmentSysId());
        migDS.setContractSysId(saveRequest.getContractSysId());
        migDS.setBenefitPlan(saveRequest.getBenefitPlan());
        migDS.setBrandName(saveRequest.getBrandName());
        migDS.setContractType(saveRequest.getContractType());
        migDS.setCorporatePlanCode(saveRequest.getCorporatePlanCode());
        migDS.setCreatedTimeStamp(new Date());
        migDS.setCreatedUser(saveRequest.getUser().getUserId());
        migDS.setEffectiveDate(saveRequest.getStartDate());
        migDS.setExpiryDate(saveRequest.getEndDate());
        migDS.setFundingArrangement(saveRequest.getFundingArrangement());
        migDS.setGroupSize(saveRequest.getGroupSize());
        migDS.setHeadQuartersState(saveRequest.getHeadQuartersState());
        migDS.setLastUpdatedTimeStamp(new Date());
        migDS.setLastUpdatedUser(saveRequest.getUser().getUserId());
        migDS.setProductCode(saveRequest.getProductCode());
        migDS.setStandardPlanCode(saveRequest.getStandardPlanCode());
        migDS.setBaseContractId(saveRequest.getBaseContractId());
        migDS.setBaseDateSegmentSysId(saveRequest.getBaseDateSegmentSysId());
        migDS.setLegacyStartDate(saveRequest.getLegacyStartDate());
        migDS.setCobAdjudInd(saveRequest.getCobAdjudInd());
        migDS.setItsHomeAdjInd(saveRequest.getItsHomeAdjInd());
        migDS.setMedicareAdjudInd(saveRequest.getMedicareAdjudInd()); 
        migDS.setRegulatoryAgency(saveRequest.getRegulatoryAgency());
		migDS.setComplianceStatus(saveRequest.getComplianceStatus());
		migDS.setProdProjNameCode(saveRequest.getProdProjNameCode());
		migDS.setContractTermDate(WPDStringUtil.getDateFromString(saveRequest.getContractTermDate()));
		migDS.setMultiplanIndicator(saveRequest.getMultiplanIndicator());
		migDS.setPerformanceGuarantee(saveRequest.getPerformanceGuarantee());
		migDS.setSalesMarketDate(WPDStringUtil.getDateFromString(saveRequest.getSalesMarketDate()));
		migDS.setEwpdProductSystemId(saveRequest.getBaseContractProductSysId());
       }
    /**
     * 
     * @param migDI
     * @param saveRequest
     */
    public static void setValuesToMigDI(MigrationDomain migDI,SaveMigGeneralInfoRequest saveRequest ){
        migDI.setMigContractSystemId(saveRequest.getContractSysId());
        migDI.setDomainList(saveRequest.getDomainList());
        
    }
    /**
     * Function to populate values from request to MigrationPricing Object.
     * 
     * @param MigrationPricingInfoRequest
     * @param MigrationPricing
     * @param
     * @return void
     * @throws
     */
    public static void populateMigrationPricing(MigrationPricingInfoRequest request,
            MigrationPricing businessObject) {
        businessObject
                .setContractDateSegmentSysId(request.getMigratedDSSysID());
        businessObject.setCoverage(request.getCoverage());
        businessObject.setPricing(request.getPricing());
        businessObject.setNetwork(request.getNetwork());
    }
    
    /**
     * @param legecyContract
     * @return
     */
    /*
    public static MigrationContract copyCP2000ContractToMigrationContract(
            LegacyContract legecyContract) throws SevereException {
        MigrationContract migrationContract = new MigrationContract();

        migrationContract.setContractId(legecyContract.getContractId());
        migrationContract.setContractType(legecyContract.getSystem());
        migrationContract.setCreatedTimeStamp(legecyContract
                .getCreatedTimestamp());
        migrationContract.setCreatedUser(legecyContract.getCreatedUser());
        migrationContract.setDoneFlag(MigrationContractUtil.DONE_FLAG_NO); // add it to constant for not
        // done
        migrationContract.setEwpdProductSystemId(null);
       
        migrationContract.setLastUpdatedTimeStamp(legecyContract
                .getLastUpdatedTimestamp());
        migrationContract.setLastUpdatedUser(legecyContract
                .getLastUpdatedUser());

        SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
        int sysId = sequenceAdapterManager.getMigratedContractSysIdSequence();
        migrationContract.setMigrationSystemId(new Integer(sysId).toString());
        migrationContract.setOption("0"); // get for request and set
        migrationContract.setProductFamily(null);
       
        
        if(null != legecyContract.getBenefitStructure())
            migrationContract.setStructreProductMappingId(legecyContract
                .getBenefitStructure());
        else
            migrationContract.setStructreProductMappingId("");
        migrationContract.setSystem(legecyContract.getSystem());

        return migrationContract;
    }
    */
    /**
	 * @param confirmMigDataList
	 */
	public static List prepareDataForConfirmMigrationSave(List confirmMigDataList) {
		ConfirmMigrationContract confirmMigrationContract = null;
		ConfirmMigrationContractForSave confirmMigrationContractForSave = null;
		List confirmMigSaveDataList = null;
		if(null!=confirmMigDataList ){
    		
    		if(confirmMigDataList.size()>0){
    			confirmMigSaveDataList = new ArrayList();
    			for (int i = 0; i < confirmMigDataList.size(); i++) {
    				confirmMigrationContract = (ConfirmMigrationContract)confirmMigDataList.get(i);
    				confirmMigrationContractForSave = new ConfirmMigrationContractForSave();
    				confirmMigrationContractForSave.setBftCompSysId(confirmMigrationContract.getBftCompSysId());
    				confirmMigrationContractForSave.setBftLineId(confirmMigrationContract.getBftLineId());
    				confirmMigrationContractForSave.setVariableId(confirmMigrationContract.getVariableId());
    				confirmMigrationContractForSave.setVarDetails(confirmMigrationContract.getVarDetails());
    				confirmMigrationContractForSave.setMappingSysId(confirmMigrationContract.getMappingSysId());
    				confirmMigrationContractForSave.setMigContractSysId(confirmMigrationContract.getMigContractSysId());
    				confirmMigSaveDataList.add(confirmMigrationContractForSave);
    			}
    		}
    	}
		return confirmMigSaveDataList;
		
	}
	
	/**
	 * 
	 * @param migrationContract
	 * @param retrieveLegacyContractResponse
	 * @throws SevereException
	 */
	public static void setMigrationContractSessionToRetrieveResponse(			
			MigrationContract migrationContract,
			RetrieveLegacyContractResponse retrieveLegacyContractResponse
			)throws SevereException
	{
		MigrationContractSession migrationContractSession = new MigrationContractSession();
		migrationContractSession.setMigrationContract(migrationContract);
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		List dateSegmentDetails = migrationBusinessObjectBuilder.getDateSegmentDetails(migrationContract.getMigrationSystemId());
		retrieveLegacyContractResponse.setMigrationdateSegmentList(dateSegmentDetails);
		MigrationDateSegment migrationDateSegment = null;
		if ((dateSegmentDetails != null) && (!dateSegmentDetails.isEmpty()))
		{
			int lastAccessMigDateSegmentSysID = migrationContract.getLastAccessMigDateSegmentSysID();
			for(Iterator dateSegmentListItr = dateSegmentDetails.iterator();
				dateSegmentListItr.hasNext();){
				migrationDateSegment = (MigrationDateSegment)dateSegmentListItr.next();
				if(migrationDateSegment.getSystemId()==lastAccessMigDateSegmentSysID)
					break;
			}
			if(null !=migrationDateSegment){
				setDateSegmentToResponse(migrationContractSession, migrationDateSegment, retrieveLegacyContractResponse);
			}else{
				migrationDateSegment = (MigrationDateSegment) dateSegmentDetails.get(0);			
			}
		}
	}
		
	
	/**
	 * 
	 * @param migrationContract
	 * @param dateSegmentId
	 * @param audit
	 * @param migContractResponse
	 * @throws SevereException
	 */
	public static void setMigrationContractSessionToResponseDateSegment(			
			MigrationContract migrationContract,
			int dateSegmentId,
			Audit audit,
			MigrationContractResponse migContractResponse
			)throws SevereException
	{
		MigrationContractSession migrationContractSession = new MigrationContractSession();
		migrationContractSession.setMigrationContract(migrationContract);
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		MigrationDateSegment migrationDateSegment = new MigrationDateSegment ();
		migrationDateSegment.setSystemId(dateSegmentId);
		migrationDateSegment = migrationBusinessObjectBuilder.retrieveMigGenInfo(migrationDateSegment);

		migrationBusinessObjectBuilder.updateLastAccessDateSegment(dateSegmentId, audit);
		
		setDateSegmentToResponse(migrationContractSession, migrationDateSegment, migContractResponse);
	
	}
	/**
	 * 
	 * @param migrationContract
	 * @param audit
	 * @param migContractResponse
	 * @throws SevereException
	 */
	public static void setCreateMigrationContractSessionToResponse(			
			MigrationContract migrationContract,
			Audit audit,
			MigrationContractResponse migContractResponse
			)throws SevereException
	{
		MigrationContractSession migrationContractSession = new MigrationContractSession();
		migrationContractSession.setMigrationContract(migrationContract);
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		List dateSegmentDetails = migrationBusinessObjectBuilder.getDateSegmentDetails(migrationContract.getMigrationSystemId());

		if ((dateSegmentDetails != null) && (!dateSegmentDetails.isEmpty()))
		{
			MigrationDateSegment migrationDateSegment = (MigrationDateSegment) dateSegmentDetails
					.get(0);
			
			migrationBusinessObjectBuilder.updateLastAccessDateSegment(migrationDateSegment.getSystemId(), audit);

			setDateSegmentToResponse(migrationContractSession, migrationDateSegment, migContractResponse);
		}
	}
	public static MigrationContractSession setCreateMigrationContractSessionToResponse(			
			MigrationContract migrationContract,
			List dateSegmentDetails,
			Audit audit			
			)throws SevereException
	{
		MigrationContractSession migrationContractSession = new MigrationContractSession();
		migrationContractSession.setMigrationContract(migrationContract);
		MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
//		List dateSegmentDetails = migrationBusinessObjectBuilder.getDateSegmentDetails(migrationContract.getMigrationSystemId());

		if ((dateSegmentDetails != null) && (!dateSegmentDetails.isEmpty()))
		{
			MigrationDateSegment migrationDateSegment = (MigrationDateSegment) dateSegmentDetails
					.get(0);
			
			migrationBusinessObjectBuilder.updateLastAccessDateSegment(migrationDateSegment.getSystemId(), audit);

		 	setDateSegmentToResponse(migrationContractSession, migrationDateSegment);
		}
		return migrationContractSession;
	}
	
	/**
	 * 
	 * @param migrationContractSession
	 * @param migrationDateSegment
	 * @param migContractResponse
	 */
	private static void setDateSegmentToResponse(MigrationContractSession migrationContractSession ,MigrationDateSegment migrationDateSegment, MigrationContractResponse migContractResponse)
	{
		NavigationInfo navigationInfo =  new NavigationInfo();
		navigationInfo.setDateSegmentSysId(migrationDateSegment.getSystemId());
		navigationInfo.setLastAccessedPage(migrationDateSegment.getLastAccessedPage());
		navigationInfo.setMigContSysId(migrationDateSegment.getContractSysId());
		navigationInfo.setStepCompleted(Integer.parseInt(migrationDateSegment.getStepCompleted()));
		migrationContractSession.setNavigationInfo(navigationInfo);
		
		migrationContractSession.setDateSegmentId(String.valueOf(migrationDateSegment.getSystemId()));
		migrationContractSession.setStartDateEwpd(migrationDateSegment.getEffectiveDate());
		migrationContractSession.setStartDateLegacy(migrationDateSegment.getLegacyStartDate());
		migrationContractSession.setEndDate(migrationDateSegment.getExpiryDate());
		
		migrationContractSession.getMigrationContract().setEwpdProductSystemId(
				String.valueOf(migrationDateSegment.getEwpdProductSystemId()));
		migrationContractSession.getMigrationContract().setMigratedProdStructureMappingSysID(
				String.valueOf(migrationDateSegment.getMgrtdProductStructureMappingSysId()));
		migrationContractSession.getMigrationContract().setBaseDateSegmentId(
				String.valueOf(migrationDateSegment.getBaseDateSegmentSysId()));


		migContractResponse.setMigrationContractSession(migrationContractSession);
	}
	
	private static void setDateSegmentToResponse(MigrationContractSession migrationContractSession, MigrationDateSegment migrationDateSegment)
	{
//		MigrationContractSession migrationContractSession = new MigrationContractSession();
		NavigationInfo navigationInfo =  new NavigationInfo();
		navigationInfo.setDateSegmentSysId(migrationDateSegment.getSystemId());
		navigationInfo.setLastAccessedPage(migrationDateSegment.getLastAccessedPage());
		navigationInfo.setMigContSysId(migrationDateSegment.getContractSysId());
		navigationInfo.setStepCompleted(Integer.parseInt(migrationDateSegment.getStepCompleted()));
		migrationContractSession.setNavigationInfo(navigationInfo);
		
		migrationContractSession.setDateSegmentId(String.valueOf(migrationDateSegment.getSystemId()));
		migrationContractSession.setStartDateEwpd(migrationDateSegment.getEffectiveDate());
		migrationContractSession.setStartDateLegacy(migrationDateSegment.getLegacyStartDate());
		migrationContractSession.setEndDate(migrationDateSegment.getExpiryDate());
//		return migrationContractSession;
	}
	
	/**
	 * 
	 * @param migrationContractRequest
	 * @param migContractResponse
	 * @throws SevereException
	 */
	public static void setMigrationContractSessionToResponse(			
			MigrationContractRequest migrationContractRequest,
			MigrationContractResponse migContractResponse
			)throws SevereException
	{
		migContractResponse.setMigrationContractSession(migrationContractRequest.getMigrationContractSession());
	}
	/**
	 * 
	 * @param migrationContract
	 * @param migrationContractRequest
	 * @param migContractResponse
	 * @throws SevereException
	 */
	public static void setMigrationContractSessionToResponse(			
			MigrationContract migrationContract, 
			MigrationContractRequest migrationContractRequest,
			MigrationContractResponse migContractResponse
		
			)throws SevereException
	{
		MigrationContractSession migrationContractSession = migrationContractRequest.getMigrationContractSession();
		if (migrationContract == null)
			migrationContract = migrationContractSession.getMigrationContract();
		migrationContractSession.setMigrationContract(migrationContract);
		NavigationInfo navigationInfo = migrationContractSession.getNavigationInfo();
		migrationContractSession.setNavigationInfo(navigationInfo);
		migContractResponse.setMigrationContractSession(migrationContractSession);
	}
	public static BenefitComponentBenefit getInfoForTree(int productSysId, int presentCmpnntId, int presentBenefitId, int i, MigrationContractSession migrationContractSession)throws SevereException {
	    
	    MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
	    int baseDateSegId = 0;
	    List list = null;
	    if(null!=migrationContractSession.getMigrationContract()){
	    	if(null!=migrationContractSession.getMigrationContract().getBaseDateSegmentId()){
	    		baseDateSegId = Integer.parseInt(migrationContractSession.getMigrationContract().getBaseDateSegmentId());
	    	}
	    }
	    if(baseDateSegId!=0){
	    	list = migrationBusinessObjectBuilder.getInfoForTree(productSysId,baseDateSegId);
	    }else{
	    	list = migrationBusinessObjectBuilder.getInfoForTree(productSysId);
	    }
	    BenefitComponentBenefit benefitComponentBenefit = null;
	    if(i ==1){
	        benefitComponentBenefit = getNextBenefitComponentBenefit(list,presentCmpnntId,presentBenefitId);
	    }
	    else if(i ==2){
	        benefitComponentBenefit = getPreviousBenefitComponentBenefit(list,presentCmpnntId,presentBenefitId);
	    }
	    else if(i==3){
	        benefitComponentBenefit = getLastBenefitComponentBenefit(list);
	    }
	    return benefitComponentBenefit;
	}
	/**
	 * Return the next BenefitComponentBenefit object, if no next then returns null
	 * @param list - List of BenefitComponentBenefit of a product.
	 * @param benefitComponentId - current benefitComponentId
	 * @param benefitId - current benefit id
	 */
	    public static BenefitComponentBenefit getNextBenefitComponentBenefit(List list, int benefitComponentId, int benefitId)
	    {
	        if(list == null || list.isEmpty())
	        return null;
	       
	        BenefitComponentBenefit bcb ;
	        boolean next = false;
	        for (int i = 0, n = list.size(); i < n ; i++) {
	            bcb = (BenefitComponentBenefit)list.get(i);
	            if(next)
	                return bcb;
	            if(bcb.getBenefitComponentId() == benefitComponentId)
	            {
	                if(bcb.getBenefitId() == benefitId)
	                {
	                    next=true;
	                }   
	            }
	        }
	        return null;        
	    }
	    
	    /**
		 * Return the previous BenefitComponentBenefit object, if no previous then returns null
		 * @param list - List of BenefitComponentBenefit of a product.
		 * @param benefitComponentId - current benefitComponentId
		 * @param benefitId - current benefit id
		 */
		    public static BenefitComponentBenefit getPreviousBenefitComponentBenefit(List list, int benefitComponentId, int benefitId)
		    {
		        if(list == null || list.isEmpty())
		        return null;
		       
		        BenefitComponentBenefit bcb ;
		        BenefitComponentBenefit bcbPrevious = null;
		        boolean next = false;
		        for (int i = 0, n = list.size(); i < n ; i++) {
		            bcb = (BenefitComponentBenefit)list.get(i);
		            if(bcb.getBenefitComponentId() == benefitComponentId)
		            {
		                if(bcb.getBenefitId() == benefitId)
		                {
		                    next=true;
		                    if(i == 0) 
		                    	return null;
		                    else
		                    	return bcbPrevious;
		                }   
		            }
		            bcbPrevious = bcb;
		        }
		        return null;        
		    }
		    
		    /**
			 * Return the Last BenefitComponentBenefit object, if no last record then returns null
			 * @param list - List of BenefitComponentBenefit of a product.
			 * @param benefitComponentId - current benefitComponentId
			 * @param benefitId - current benefit id
			 */
			    public static BenefitComponentBenefit getLastBenefitComponentBenefit(List list)
			    {
			        if(list == null || list.isEmpty())
			        return null;			       
			        return (BenefitComponentBenefit)list.get(list.size()-1);			        
			    }
			/**
			 * @param notesMigrationList
			 * @return benefitComponentNote
			 */
			public static BenefitComponentNote getBenefitComponentNote(List notesMigrationList) {
				BenefitComponentNote benefitComponentNote = null;
				
				if (null!=notesMigrationList && notesMigrationList.size()>0){
					
					Iterator notesMigrationIter = notesMigrationList.iterator();
					
					while (notesMigrationIter.hasNext()){
					
						MigrateNotesVO migrateNotesVO = (MigrateNotesVO)notesMigrationIter.next();
						
						if("BenefitComponent".equalsIgnoreCase(migrateNotesVO.getIdentifer())){
							benefitComponentNote = new BenefitComponentNote();
							
							benefitComponentNote.setBenefitCompSysId(migrateNotesVO.getBenefitComponentId());
							benefitComponentNote.setComponentNoteMigrateFlag(migrateNotesVO.getMigrateNotesFlag());
							if(!StringUtil.isEmpty(migrateNotesVO.getMigratedDateSegmentId())){
								benefitComponentNote.setMigDateSegSysId(Integer.parseInt(migrateNotesVO.getMigratedDateSegmentId()));
							}
							benefitComponentNote.setMajorHeadingId(migrateNotesVO.getMajorheadingId());
						}
					}
				}				
				
				return benefitComponentNote;
			}
			/**
			 * @param notesMigrationList
			 * @return 
			 */
			public static List getBenefitNoteList(List notesMigrationList) {

				List benefitNoteList = new ArrayList();
				if (null!=notesMigrationList && notesMigrationList.size()>0){
					
					Iterator notesMigrationIter = notesMigrationList.iterator();
					
					while (notesMigrationIter.hasNext()){
					
						MigrateNotesVO migrateNotesVO = (MigrateNotesVO)notesMigrationIter.next();
						
						if("Benefit".equalsIgnoreCase(migrateNotesVO.getIdentifer())){
							BenefitNote benefitNote = new BenefitNote();
							benefitNote.setBenefitSysId(migrateNotesVO.getBenefitId());
							benefitNote.setBenefitCompSysId(migrateNotesVO.getBenefitComponentId());
							benefitNote.setBenefitNoteMigrateFlag(migrateNotesVO.getMigrateNotesFlag());
							if(!StringUtil.isEmpty(migrateNotesVO.getMigratedDateSegmentId())){
								benefitNote.setMigDateSegSysId(Integer.parseInt(migrateNotesVO.getMigratedDateSegmentId()));
							}
							benefitNote.setMinorHeadingId(migrateNotesVO.getMinorheadingId());
							benefitNoteList.add(benefitNote);
						}
					}
				}	
				return benefitNoteList;
			}
			/**
			 * @param dateSegmentist
			 * @param notesMigrationList
			 * @return
			 */
			public static MigrationDateSegment getContractNote(List dateSegmentList, List notesMigrationList) {
				MigrationDateSegment migrationDateSegment = null;
				if (null!=dateSegmentList && dateSegmentList.size()>0){
					
					Iterator dateSegmentIter = dateSegmentList.iterator();
					
					while (dateSegmentIter.hasNext()){
						migrationDateSegment = (MigrationDateSegment)dateSegmentIter.next();
					}
				}
				
				if (null!=notesMigrationList && notesMigrationList.size()>0){
					
					Iterator notesMigrationIter = notesMigrationList.iterator();
					
					while (notesMigrationIter.hasNext()){
					
						MigrateNotesVO migrateNotesVO = (MigrateNotesVO)notesMigrationIter.next();
						
						if("Contract".equalsIgnoreCase(migrateNotesVO.getIdentifer())){
							migrationDateSegment.setContractNoteMigrateFlag(migrateNotesVO.getContractNotesFlag());
							}
						}
					}
				
				return migrationDateSegment;
			}
			    
			    
			    
		public static BenefitComponentBenefit getInfoForTreeForStep8(int productSysId, int presentCmpnntId, int presentBenefitId, int i, MigrationContract migrationContract)throws SevereException {
		    
		    MigrationBusinessObjectBuilder migrationBusinessObjectBuilder = new MigrationBusinessObjectBuilder();
		    
		    int baseDateSegId = 0;
		    List list = null;
		    if(null!=migrationContract){
		    	if(null!=migrationContract.getBaseDateSegmentId()){
		    		baseDateSegId = Integer.parseInt(migrationContract.getBaseDateSegmentId());
		    	}
		    }
		    if(baseDateSegId!=0){
		    	list = migrationBusinessObjectBuilder.getInfoForTree(productSysId,baseDateSegId);
		    }else{
		    	list = migrationBusinessObjectBuilder.getInfoForTree(productSysId);
		    }	    
		    
		    BenefitComponentBenefit benefitComponentBenefit = null;
		    if(i ==1){
		        benefitComponentBenefit = getNextBenefitComponent(list,presentCmpnntId);
		    }
		    else if(i ==2){
		        benefitComponentBenefit = getPreviousBenefitComponent(list,presentCmpnntId);
		    }
		    else if(i==3){
		        benefitComponentBenefit = getLastBenefitComponent(list);
		    }
		    return benefitComponentBenefit;
		}
			    
		public static BenefitComponentBenefit getNextBenefitComponent(List list, int benefitComponentId)
	    {
	        if(list == null || list.isEmpty())
	        return null;
	       
	        BenefitComponentBenefit bcb ;
	        boolean next = false;
	        BenefitComponentBenefit bcbTemp ;
	        
	        for (int i = 0, n = list.size(); i < n ; i++) {
	            bcb = (BenefitComponentBenefit)list.get(i);
	            if(next)
	                return bcb;
	            if(bcb.getBenefitComponentId() == benefitComponentId)
	            {
	               if(i+1 < list.size() ){
	               		bcbTemp = (BenefitComponentBenefit)list.get(i+1);
	               		if(bcbTemp.getBenefitComponentId() == benefitComponentId){
	               			next = false;
	               		}
	               		else{
	               			next=true;
	               		}
	               }
	               else{
	               		return null;
	               }
	               	
	                   // next=true;
	                  
	            }
	        }
	        return null;        
	    }
			
		
		 public static BenefitComponentBenefit getPreviousBenefitComponent(List list, int benefitComponentId)
		    {
		        if(list == null || list.isEmpty())
		        return null;
		       
		        BenefitComponentBenefit bcb ;
		        BenefitComponentBenefit bcbPrevious = null;
		        boolean next = false;
		        for (int i = 0, n = list.size(); i < n ; i++) {
		            bcb = (BenefitComponentBenefit)list.get(i);
		            if(bcb.getBenefitComponentId() == benefitComponentId)
		            {
	                    next=true;
	                    if(i == 0) 
	                    	return null;
	                    else
	                    	return bcbPrevious;
		            }
		            bcbPrevious = bcb;
		        }
		        return null;        
		    }
		 
		 public static BenefitComponentBenefit getLastBenefitComponent(List list)
		    {
		        if(list == null || list.isEmpty())
		        return null;			       
		        return (BenefitComponentBenefit)list.get(list.size()-1);			        
		    }
}
