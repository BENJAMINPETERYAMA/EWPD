/*
 * BenefitComponentBusinessObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.benefitcomponent.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.benefitcomponent.adapter.BenefitComponentAdapterManager;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitComponentDeleteLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitComponentNotesLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitHierarchyLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.ComponentsBenefitAdministrationLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.ComponentsBenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.NonAdjBenefitMandateLocateCriteria;
import com.wellpoint.wpd.business.benefitcomponent.locateresult.BenefitComponentLocateResult;
import com.wellpoint.wpd.business.benefitdefinition.adapter.NonAdjBenefitMandateAdapterManager;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.BenefitAdapterManager;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.notes.adapter.NotesAdapterManager;
import com.wellpoint.wpd.business.notes.adapter.NotesAttachmentAdapterManager;
import com.wellpoint.wpd.business.question.adapter.QuestionAdapterManager;
import com.wellpoint.wpd.business.refdata.adapter.RefdataAdapterManager;
import com.wellpoint.wpd.business.refdata.bo.AssignedRuleIdBO;
import com.wellpoint.wpd.business.standardbenefit.adapter.StandardBenefitAdapterManager;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitMandateLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StateLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponent;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentQuesitionnaireBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitHierarchyAssociationBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitHierarchyBO;
import com.wellpoint.wpd.common.benefitcomponent.bo.ComponentsBenefitAdministrationBO;
import com.wellpoint.wpd.common.benefitcomponent.locatecriteria.BenefitComponentLocateCriteria;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.AuditImpl;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.Domain;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.domain.bo.DomainItem;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;
import com.wellpoint.wpd.common.notes.bo.NoteDomainAssnBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentDomainOverrideBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBO;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLevel;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.question.bo.QuestionBO;
import com.wellpoint.wpd.common.standardbenefit.bo.AdminOptionHideBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.CitationNumberBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.UniverseBO;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * Business Object Builder class for Benifit Component.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentBusinessObjectBuilder {

    /**
     * 
     * @param benefitComponentBO
     * @return
     * @throws WPDException
     */
	List testList =new ArrayList();	
    public BenefitComponentBO retrieve(BenefitComponentBO benefitComponentBO,
            Map params) throws WPDException {
        //get the subObjectName from the map
        String subObjectName = (String) params.get("subObjectName");
        int keyForRetrieve = ((Integer) params.get("keyForRetrieve"))
                .intValue();
        // Create an object for adapterManager and BO to retrieve the reference
        // values
        RefdataAdapterManager rfmanager = new RefdataAdapterManager();
        AssignedRuleIdBO ruleIdBO = new AssignedRuleIdBO();
        //Create an instance of adapterManager
        NonAdjBenefitMandateAdapterManager nonAdjAdapterManager = new NonAdjBenefitMandateAdapterManager();
        
        try{
	        if (null != subObjectName && !"".equals(subObjectName)) {
	            if (subObjectName.equals("StandardBenefitBO")) {
	                StandardBenefitAdapterManager standardBenefitAdapterManager = new StandardBenefitAdapterManager();
	                StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
	                standardBenefitBO.setStandardBenefitKey(keyForRetrieve);
	                // Enhancement
	                if ("MANDATE_INFO_VIEW".equals(benefitComponentBO.getAction())) {
	                    // create the object for the subOjectName
	                    BenefitMandateBO benefitMandateBOImpl = new BenefitMandateBO();
	                    // get the keyForAttribute from the map and set it to the BO
	                    benefitMandateBOImpl.setBenefitSystemId(standardBenefitBO
	                            .getStandardBenefitKey());
	                    // call the retrieve method in the adapter manager
	                    nonAdjAdapterManager.retrieve(benefitMandateBOImpl);
	
	                    // Set the retrieved values to the benefitComponentBO
	                    benefitComponentBO
	                            .setBenefitMandateBO(benefitMandateBOImpl);
	
	                    BenefitMandateLocateCriteria benefitMandateLocateCriteria = new BenefitMandateLocateCriteria();
	                    benefitMandateLocateCriteria
	                            .setBenefitMandateId(benefitComponentBO
	                                    .getBenefitMandateBO()
	                                    .getBenefitMandateId());
	
	                    StateLocateCriteria locateCriteria = new StateLocateCriteria();
	                    locateCriteria.setBenefitMandateId(benefitComponentBO
	                            .getBenefitMandateBO().getBenefitMandateId());
	
	                    LocateResults locateResults = locate(benefitMandateLocateCriteria);
	                    List list = locateResults.getLocateResults();
	                    List citationList = new ArrayList(list==null?0:list.size());
	                    Iterator iterator = list.iterator();
	                    while (iterator.hasNext()) {
	                        CitationNumberBO citationNumberBO = (CitationNumberBO) iterator
	                                .next();
	                        citationList.add(citationNumberBO);
	                    }
	                    benefitComponentBO.getBenefitMandateBO()
	                            .setCitationNumberList(citationList);
	
	                    // To get the stateId and description corresponding to the
	                    // benefit selected
	
	                    locateResults = locate(locateCriteria);
	                    List stateList = locateResults.getLocateResults();
	                    List state = new ArrayList(stateList==null?0:stateList.size());
	                    //FIXME Check for null values
	                    Iterator stateIterator = stateList.iterator();
	                    while (stateIterator.hasNext()) {
	                        StateBO stateBO = (StateBO) stateIterator.next();
	                        state.add(stateBO);
	                    }
	                    benefitComponentBO.getBenefitMandateBO()
	                            .setBenefitStateCodeList(state);
	
	                    // End - enhancement
	                }
	                // To Retrieve the values MinorHeading and Description
	                standardBenefitBO = (StandardBenefitBO) standardBenefitAdapterManager
	                        .retrieveSB(standardBenefitBO);
	
	                // To retrieve the reference Values
	                ruleIdBO.setEntitySystemId(keyForRetrieve);
	                ruleIdBO.setEntityType("BENEFIT");
	
	                List refList = rfmanager.searchRuleId(ruleIdBO);
	                List refNameList = new ArrayList(refList==null?0:refList.size());
	                List refIdList = new ArrayList(refList==null?0:refList.size());
	                List refTypeList = new ArrayList(refList==null?0:refList.size());
	                if (null != refList && 0 != refList.size()) {
	                    for (int i = 0; i < refList.size(); i++) {
	                        ruleIdBO = (AssignedRuleIdBO) refList.get(i);
	                        refNameList.add(ruleIdBO.getCodeDescText());
	                        refTypeList.add(ruleIdBO.getEntityType());
	                        refIdList.add(ruleIdBO.getPrimaryCode());
	                    }
	                }
	                standardBenefitBO.setRuleNameList(refNameList);
	                standardBenefitBO.setRuleIdList(refIdList);
	                standardBenefitBO.setRuleTyepLst(refTypeList);
	
	                //  change
	                List domainList = DomainUtil.retrieveAssociatedDomains(
	                        "stdbenefit", standardBenefitBO.getParentSystemId());
	                standardBenefitBO.setBusinessDomains(domainList);
	                //changes end
	
	                // To Retrieve the BenefitValues
	                /*
	                 * DomainDetail businessDomain =
	                 * getBusinessDomainValues(standardBenefitBO); businessDomain =
	                 * DomainUtil .retrieveBusinessDomain(businessDomain);
	                 * 
	                 * if (null != businessDomain) {
	                 * standardBenefitBO.setLobList(businessDomain
	                 * .getLineOfBusiness());
	                 * standardBenefitBO.setBusinessEntityList(businessDomain
	                 * .getBusinessEntity());
	                 * standardBenefitBO.setBusinessGroupList(businessDomain
	                 * .getBusinessGroup()); }
	                 */
	
	                //To Retrieve the Universe Values
	                UniverseBO universeBO = new UniverseBO();
	                // Refers to BenefitSysId in BMST_BNFT_MSTR
	                universeBO.setStandardBenefitKey(benefitComponentBO
	                        .getStandardBenefitBO().getStandardBenefitKey());
	                List searchList = standardBenefitAdapterManager
	                        .searchUniverse(universeBO);
	
	                //Checking for the size of the universe list retrieved from
	                // database.
	
	                if (null != searchList) {
	                    if (searchList.size() > 0) {
	
	                        // Calling the method to set the list vaues in th BO
	
	                        standardBenefitBO = this.prepareUniverseDisplayList(
	                                searchList, standardBenefitBO);
	                    }
	                }
	
	                // To Retrieve the dataType values
	                StandardBenefitDatatypeBO standardBenefitDatatypeBO = new StandardBenefitDatatypeBO();
	                standardBenefitDatatypeBO
	                        .setStandardBenefitKey(standardBenefitBO
	                                .getStandardBenefitKey());
	
	                /*
	                 * Calling the method to search for the data type values
	                 */
	                List dataTypeResultList = standardBenefitAdapterManager
	                        .searchDatatype(standardBenefitDatatypeBO);
	                standardBenefitBO.setDataTypeList(dataTypeResultList);
	
	                // Setting all the values in the sub Object
	                // benefitComponentGeneralInformation to the main object
	                // benefitComponentBO
	                benefitComponentBO.setStandardBenefitBO(standardBenefitBO);
	            }
	        }
        } catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieve BenefitComponentBO method in benefitComponentBuilder",
					errorParams, se);
		} 
//        catch (AdapterException ae) {
//			List errorParams = new ArrayList();
//			String obj = ae.getClass().getName();
//			errorParams.add(obj);
//			errorParams.add(obj.getClass().getName());
//			throw new SevereException(
//					"Exception occured in retrieve BenefitComponentBO method in benefitComponentBuilder",
//					errorParams, ae);
//		}
        catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieve BenefitComponentBO method in benefitComponentBuilder",
					null, ex);
		}
        return benefitComponentBO;
    }


    /**
     * Method to get Business Domain
     * 
     * @param standardBenefitBO
     * @return businessDomain
     */
    /*
     * private DomainDetail getBusinessDomainValues( StandardBenefitBO
     * standardBenefitBO) { DomainDetail businessDomain = new DomainDetail();
     * //TODO -- Consult with people doing create and change the string as //
     * required businessDomain.setEntityType("stdbenefit");
     * businessDomain.setEntityName(standardBenefitBO.getBenefitIdentifier());
     * businessDomain.setEntitySystemId(standardBenefitBO
     * .getStandardBenefitKey()); businessDomain.setCreatedUser("USER");
     * 
     * return businessDomain; }
     */

    /**
     * Function to get the values from the universe list and set them to BO
     * 
     * @param searchList
     * @param standardBenefitBO
     */
    private StandardBenefitBO prepareUniverseDisplayList(List searchList,
            StandardBenefitBO standardBenefitBO) {
    	
    	//FIXME Check for null values
        Iterator searchListIterator = searchList.iterator();
        List termList = new ArrayList(searchList==null?0:searchList.size());
        List qualifierList = new ArrayList(searchList==null?0:searchList.size());
        List pvaList = new ArrayList(searchList==null?0:searchList.size());

        while (searchListIterator.hasNext()) {
            UniverseBO universeBO = (UniverseBO) searchListIterator.next();
            //FIXME Replace hard coded values with constants
            if ("term".equals(universeBO.getCatDescText())) {
                termList.add(universeBO);
            } else if ("qualifier".equals(universeBO.getCatDescText())) {
                qualifierList.add(universeBO);
            } else if ("provider arrangement".equals(universeBO
                    .getCatDescText())) {
                pvaList.add(universeBO);
            }

        }
        standardBenefitBO.setTermList(termList);
        standardBenefitBO.setQualifierList(qualifierList);
        standardBenefitBO.setPVAList(pvaList);
        return standardBenefitBO;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#retrieve(com.wellpoint.wpd.common.bo.BusinessObject)
     * @param benefitComponentBO
     * @return
     * @throws WPDException
     */
    public BusinessObject retrieve(BenefitComponentBO benefitComponentBO)
            throws SevereException {
        BenefitComponentAdapterManager benefitComponentAdapterManager = new BenefitComponentAdapterManager();
        //        benefitComponentBO = benefitComponentAdapterManager
        //                .retrieveBenefitComponentById(benefitComponentBO);
        try{
        benefitComponentBO = (BenefitComponentBO) benefitComponentAdapterManager
                .retrieveBenefitComponent(benefitComponentBO);
        getReferenceList(benefitComponentBO);
        DomainDetail businessDomain = this
                .createBusinessDomain(benefitComponentBO);
        List domainList = DomainUtil.retrieveAssociatedDomains("BENEFITCOMP",
                benefitComponentBO.getBenefitComponentParentId());
        businessDomain = DomainUtil.retrieveBusinessDomain(businessDomain);
        benefitComponentBO
                .setBusinessEntity(businessDomain.getBusinessEntity());
        benefitComponentBO.setBusinessGroup(businessDomain.getBusinessGroup());
        benefitComponentBO.setMarketBusinessUnit(businessDomain.getMarketBusinessUnit());
        benefitComponentBO
                .setLineOfBusiness(businessDomain.getLineOfBusiness());
        benefitComponentBO.setBusinessDomainList(domainList);
        } catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieve BenefitComponentBO method in benefitComponentBuilder",
					errorParams, se);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieve BenefitComponentBO method in benefitComponentBuilder",
					null, ex);
		}
        return benefitComponentBO;
    }


    private String getReferenceList(BenefitComponentBO benefitComponentBO)
            throws SevereException {
        //UniverseBO universebo = new UniverseBO();
        AssignedRuleIdBO ruleIdBO = new AssignedRuleIdBO();
        RefdataAdapterManager rfmanager = new RefdataAdapterManager();
        //BenefitComponentAdapterManager bcmanager = new
        // BenefitComponentAdapterManager();
        try {
            ruleIdBO.setEntitySystemId(benefitComponentBO
                    .getBenefitComponentId());
            ruleIdBO.setEntityType("BENEFITCOMP");

            //    	 universebo.setStandardBenefitKey(benefitComponentBO.getBenefitComponentId());
            //		    universebo.setUniverseType("BCREFERENCE");
            //		    universebo.setCodeDescText("codedesc");
            List refList = rfmanager.searchRuleId(ruleIdBO);
            List refNameList = new ArrayList(refList==null?0:refList.size());
            List refIdList = new ArrayList(refList==null?0:refList.size());
            
            /*
             * Code Changed for Adjudication rules Project i.t RuleType is added.
             */
            List refTypeList = new ArrayList(refList==null?0:refList.size());
            if (null != refList && 0 != refList.size()) {
                for (int i = 0; i < refList.size(); i++) {
                    ruleIdBO = (AssignedRuleIdBO) refList.get(i);
                    
                    refNameList.add(ruleIdBO.getCodeDescText());
                    refIdList.add(ruleIdBO.getPrimaryCode());
                    refTypeList.add(ruleIdBO.getEntityType());
                    
                                       
                }
                
                
                
            }
            benefitComponentBO.setRuleNameList(refNameList); 
            benefitComponentBO.setRuleList(refIdList);
            benefitComponentBO.setRuleTypeList(refTypeList);
            
            
            
            
        } catch (SevereException e) {
            List errorParams = new ArrayList(2);
            String obj = e.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in BenefitComponentBuilder in getReferenceList() method",
                    errorParams, e);
        }

        return "";
    }


    /**
     * @param benefitComponentBO
     * @return businessDomain
     */
    private DomainDetail createBusinessDomain(
            BenefitComponentBO benefitComponentBO) {
        DomainDetail businessDomain = new DomainDetail();
        businessDomain.setEntityName(benefitComponentBO.getName());
        businessDomain.setEntitySystemId(benefitComponentBO
                .getBenefitComponentParentId());
        businessDomain.setEntityType("BENEFITCOMP");
        businessDomain
                .setLineOfBusiness(benefitComponentBO.getLineOfBusiness());
        businessDomain
                .setBusinessEntity(benefitComponentBO.getBusinessEntity());
        businessDomain.setBusinessGroup(benefitComponentBO.getBusinessGroup());
        /*START CARS*/
        businessDomain.setMarketBusinessUnit(benefitComponentBO.getMarketBusinessUnit());
        /*END CARS*/
        businessDomain.setCreatedTimeStamp(benefitComponentBO
                .getCreatedTimestamp());
        businessDomain.setCreatedUser(benefitComponentBO.getCreatedUser());
        businessDomain.setLastUpdatedTimeStamp(benefitComponentBO
                .getLastUpdatedTimestamp());
        businessDomain.setLastUpdatedUser(benefitComponentBO
                .getLastUpdatedUser());
        return businessDomain;
    }


    /**
     * Method to change Identity if the Domain value is edited
     * 
     * @param oldBenefitComponentBO
     * @param benefitComponentBO
     * @param audit
     * @return boolean
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean changeIdentity(BenefitComponentBO oldBenefitComponentBO,
            BenefitComponentBO benefitComponentBO, Audit audit)
            throws SevereException, AdapterException {
        BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
        persist(benefitComponentBO, audit, false);
        adapterManager.RefereshQuestionare(benefitComponentBO.getBenefitComponentId(),audit.getUser());
        return true;

    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#retrieve(com.wellpoint.wpd.common.bo.BusinessObject,
     *      java.util.Map)
     * @param object
     * @param params
     * @return
     * @throws WPDException
     */
    public BusinessObject retrieve(BusinessObject object, Map params)
            throws WPDException {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * retrieves the BO with the latest version number
     * 
     * @param businessObject
     * @return BusinessObject
     * @throws WPDException
     */
    public BusinessObject retrieveLatestVersion(
            BenefitComponentBO businessObject) throws WPDException,SevereException {
        DomainDetail businessDomain = null;
        BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
        BenefitComponentBO benefitComponentBO =  businessObject;
        try{
	        
	        List domainList = benefitComponentBO.getBusinessDomainList();
	        List lobList = BusinessUtil.getLobList(domainList);
	        List businessGroupList = BusinessUtil.getBusinessGroupList(domainList);
	        List marketBusinessUnitList = BusinessUtil.getMarketBusinessUnitList(domainList);
	        List businessEntityList = BusinessUtil
	                .getbusinessEntityList(domainList);
	        benefitComponentBO.setLineOfBusiness(lobList);
	        benefitComponentBO.setBusinessGroup(businessGroupList);
	        benefitComponentBO.setBusinessEntity(businessEntityList);
	        benefitComponentBO.setMarketBusinessUnit(marketBusinessUnitList);
	        benefitComponentBO = adapterManager
	                .retrieveBenefitComponentLatestVersion(benefitComponentBO);
	        getReferenceList(benefitComponentBO);
	        businessDomain = getBusinessDomainValues(benefitComponentBO);
	        businessDomain = DomainUtil.retrieveDomainDetailInfo("BENEFITCOMP",
	                benefitComponentBO.getBenefitComponentParentId());
	        //businessDomain = DomainUtil.retrieveBusinessDomain(businessDomain);
	        if (null != businessDomain) {
	            benefitComponentBO.setLineOfBusiness(businessDomain
	                    .getLineOfBusiness());
	            benefitComponentBO.setBusinessEntity(businessDomain
	                    .getBusinessEntity());
	            benefitComponentBO.setBusinessGroup(businessDomain
	                    .getBusinessGroup());
	            benefitComponentBO.setMarketBusinessUnit(businessDomain
	            		.getMarketBusinessUnit());
	            benefitComponentBO.setBusinessDomainList(businessDomain
	                    .getDomainList());
	        }
        } catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieveLatestVersion BenefitComponentBO method in benefitComponentBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieveLatestVersion BenefitComponentBO method in benefitComponentBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieveLatestVersion BenefitComponentBO method in benefitComponentBuilder",
					null, ex);
		}
        return benefitComponentBO;
    }


    /*
     * public int retrieveLatestVersionNumber( BusinessObject businessObject)
     * throws WPDException { BenefitComponentAdapterManager adapterManager = new
     * BenefitComponentAdapterManager(); BenefitComponentBO benefitComponentBO =
     * (BenefitComponentBO) businessObject; return adapterManager
     * .retrieveLatestVersionNumber(benefitComponentBO);
     *  }
     */

    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#retrieveLatestVersionNumber(com.wellpoint.wpd.common.bo.BusinessObject)
     * @param benefitComponentBO
     * @return
     * @throws WPDException
     */
    public int retrieveLatestVersionNumber(BenefitComponentBO benefitComponentBO)
            throws WPDException {
        BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
        	try {
				return adapterManager.retrieveLatestVersionNumber(benefitComponentBO);
			}  catch (SevereException se) {			
				List errorParams = new ArrayList(2);
				String obj = se.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());
				throw new SevereException(
						"Exception occured in retrieveLatestVersionNumber BenefitComponentBO method in benefitComponentBuilder",
						errorParams, se);
			} catch (AdapterException ae) {
				List errorParams = new ArrayList(2);
				String obj = ae.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());
				throw new SevereException(
						"Exception occured in retrieveLatestVersionNumber BenefitComponentBO method in benefitComponentBuilder",
						errorParams, ae);
			} catch (Exception ex) {
				List errorParams = new ArrayList(2);
				String obj = ex.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());
				throw new SevereException(
						"Exception occured in retrieveLatestVersionNumber BenefitComponentBO method in benefitComponentBuilder",
						null, ex);
			}
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#persist(com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit, boolean)
     * @param BusinessObject
     * @param audit
     * @param insertFlag
     * @return
     * @throws WPDException
     */
    public boolean persist(BusinessObject BusinessObject, Audit audit,
            boolean insertFlag) throws WPDException {
        // TODO Auto-generated method stub
        return false;
    }


    /**
     * Function to persist a BenefitComponentBO. Flag is used to differentiate
     * whether an insertion or updation is done.
     * 
     * @param audit
     * @param insertFlag
     * @return boolean
     * @throws WPDException
     */
    public boolean persist(BenefitComponentBO benefitComponentBO, Audit audit,
            boolean insertFlag) throws SevereException {

        BenefitComponentAdapterManager benefitComponentAdapterManager = new BenefitComponentAdapterManager();
        DomainDetail businessDomain;
        AdapterServicesAccess benefitComponentAdapterServiceAccess = benefitComponentAdapterManager
                .getAdapterService();       
        RefdataAdapterManager rfmanager = new RefdataAdapterManager();
        AssignedRuleIdBO ruleIdBO = new AssignedRuleIdBO();
        long transactionId = AdapterUtil.getTransactionId();
        try {
        	AdapterUtil.beginTransaction(benefitComponentAdapterServiceAccess);
        	AdapterUtil.logBeginTranstn(transactionId , this , "persist(BenefitComponentBO benefitComponentBO, Audit audit, boolean insertFlag)");
            if (insertFlag) {     
            	
            	benefitComponentBO.setCreatedUser(audit.getUser());
                benefitComponentBO.setCreatedTimestamp(audit.getTime());
                benefitComponentBO.setLastUpdatedUser(audit.getUser());
                benefitComponentBO.setLastUpdatedTimestamp(audit.getTime());
                
                // insert
                if (benefitComponentAdapterManager.createBenefitComponent(
                        benefitComponentBO, audit, insertFlag,
                        benefitComponentAdapterServiceAccess)) {
                    businessDomain = getBusinessDomainValues(benefitComponentBO);
                    DomainUtil.persistAssociatedDomains(businessDomain,
                            benefitComponentAdapterServiceAccess);
                    // Get the reference Values
                    List ruleIdList = benefitComponentBO.getRuleList();
                    if (ruleIdList != null) {
                        for (Iterator iter = ruleIdList.iterator(); iter
                                .hasNext();) {
                            String ruleId = (String) iter.next();
                            benefitComponentBO.setRuleId(ruleId);
                            ruleIdBO.setEntitySystemId(benefitComponentBO
                                    .getBenefitComponentId());
                            ruleIdBO.setEntityType("BENEFITCOMP");
                            ruleIdBO.setPrimaryCode(ruleId);
                            // catalogId for Reference
                            //ruleIdBO.setCatalogId(1938);
                            ruleIdBO.setCreatedUserId(audit.getUser());
                            ruleIdBO.setCreatedTimeStamp(audit.getTime());
                            ruleIdBO.setLastChangesTimeStamp(audit.getTime());
                            ruleIdBO.setLastchangesUser(audit.getUser());
                            //		        			universebo.setStandardBenefitKey(benefitComponentBO.getBenefitComponentId());
                            //		        			universebo.setUniverseType("BCREFERENCE");
                            //		        			universebo.setUniverseCode(ruleId);
                            //		        			universebo.setCodeDescText("codedesc");
                            //		        			universebo.setCreatedTimestamp(audit.getTime());
                            //		    				universebo.setCreatedUser(audit.getUser());
                            //		    				universebo.setLastUpdatedTimestamp(audit.getTime());
                            //		    				universebo.setLastUpdatedUser(audit.getUser());
                            // insert
                            rfmanager
                                    .createRuleId(
                                            ruleIdBO,
                                            benefitComponentAdapterServiceAccess,
                                            audit);

                        }
                    }

                }
            } else {
            	
            	benefitComponentBO.setLastUpdatedUser(audit.getUser());
                benefitComponentBO.setLastUpdatedTimestamp(audit.getTime());
            	
                // Edit
                if (benefitComponentAdapterManager.updateBenefitComponent(
                        benefitComponentBO, audit, insertFlag,
                        benefitComponentAdapterServiceAccess)) {
                    businessDomain = getBusinessDomainValues(benefitComponentBO);
                    DomainUtil.persistAssociatedDomains(businessDomain,
                            benefitComponentAdapterServiceAccess);
                    ruleIdBO.setEntitySystemId(benefitComponentBO
                            .getBenefitComponentId());
                    ruleIdBO.setEntityType("BENEFITCOMP");
                    // catalogId for Reference
                    ruleIdBO.setCatalogId(1938);
                    ruleIdBO.setCreatedUserId(audit.getUser());
                    ruleIdBO.setCreatedTimeStamp(audit.getTime());
                    ruleIdBO.setLastChangesTimeStamp(audit.getTime());
                    ruleIdBO.setLastchangesUser(audit.getUser());
                    //		                universebo.setStandardBenefitKey(benefitComponentBO.getBenefitComponentId());
                    //            			universebo.setUniverseType("BCREFERENCE");
                    //            			universebo.setCodeDescText("codedesc");
                    //            			universebo.setCreatedTimestamp(audit.getTime());
                    //        				universebo.setCreatedUser(audit.getUser());
                    //        				universebo.setLastUpdatedTimestamp(audit.getTime());
                    //        				universebo.setLastUpdatedUser(audit.getUser());
                    // Get the reference Values

                    //        				for(int i =0 ; i < ruleIdList.size();i++){
                    //        					String universeCode = (String)ruleIdList.get(i);
                    //        					ruleIdBO.setPrimaryCode(universeCode);
                    //        					rfmanager.deleteRuleId(ruleIdBO,benefitComponentAdapterServiceAccess,audit);
                    //        				}
                    List ruleIdList = benefitComponentBO.getRuleList();
                    // To satisfy the key value condition
                    ruleIdBO.setPrimaryCode("TEST");
                    // Delete & insert
                    rfmanager.deleteRuleId(ruleIdBO,
                            benefitComponentAdapterServiceAccess, audit);
                    if (ruleIdList != null) {
                        for (Iterator iter = ruleIdList.iterator(); iter
                                .hasNext();) {
                            String ruleId = (String) iter.next();
                            benefitComponentBO.setRuleId(ruleId);
                            ruleIdBO.setPrimaryCode(ruleId);
                            //universebo.setUniverseCode(ruleId);
                            rfmanager
                                    .createRuleId(
                                            ruleIdBO,
                                            benefitComponentAdapterServiceAccess,
                                            audit);
                        }
                    }

                }
            }

            // Edit
            //        	if(!insertFlag){
            //        		List refIdsInDB =
            // benefitComponentAdapterManager.getAllReferenceValues(benefitComponentBO.getBenefitComponentId(),"BCREFERENCE");
            //        		if(0 != ruleIdList.size()){
            //	        		for(int i =0 ; i < ruleIdList.size();i++){
            //	        			String ruleId = (String)ruleIdList.get(i);
            //	        			if(0 != refIdsInDB.size()){
            //		        			for(int j=0; j<refIdsInDB.size() ; j++){
            //		        				String refValInDb = refIdsInDB.get(j).toString();
            //		        				if(ruleId == refValInDb){
            //		        					sbmanager.updateUniverse(universebo,audit,benefitComponentAdapterServiceAccess);
            //		        				}
            //		        			}
            //	        			}
            //	        		}
            //        		}
            //        		// Retrieve all the values of the reference
            //        		// if the value already
            //        	}

            benefitComponentBO = (BenefitComponentBO) benefitComponentAdapterManager
                    .retrieveBenefitComponent(benefitComponentBO);

            // Method to get the reference value list
            //		    universebo.setStandardBenefitKey(benefitComponentBO.getBenefitComponentId());
            //		    universebo.setUniverseType("BCREFERENCE");
            //		    universebo.setCodeDescText("codedesc");
            ruleIdBO.setEntitySystemId(benefitComponentBO
                    .getBenefitComponentId());
            ruleIdBO.setEntityType("BENEFITCOMP");

            // List refList = benefitComponentBO.getRuleList();
            List refList = rfmanager.searchRuleId(ruleIdBO);
            List refNameList = new ArrayList(refList==null?0:refList.size());
            List refIdList = new ArrayList(refList==null?0:refList.size());
            if (null != refList && 0 != refList.size()) {
                for (int i = 0; i < refList.size(); i++) {
                    ruleIdBO = (AssignedRuleIdBO) refList.get(i);
                    refNameList.add(ruleIdBO.getCodeDescText());
                    refIdList.add(ruleIdBO.getPrimaryCode());
                }
            }
            benefitComponentBO.setRuleNameList(refNameList);
            benefitComponentBO.setRuleList(refIdList);

            businessDomain = getBusinessDomainValues(benefitComponentBO);
            businessDomain = DomainUtil.retrieveDomainDetailInfo("BENEFITCOMP",
                    benefitComponentBO.getBenefitComponentParentId());
            //businessDomain =
            // DomainUtil.retrieveBusinessDomain(businessDomain);
            if (null != businessDomain) {
                benefitComponentBO.setLineOfBusiness(businessDomain
                        .getLineOfBusiness());
                benefitComponentBO.setBusinessEntity(businessDomain
                        .getBusinessEntity());
                benefitComponentBO.setBusinessGroup(businessDomain
                        .getBusinessGroup());
                benefitComponentBO.setMarketBusinessUnit(businessDomain
                		.getMarketBusinessUnit());
                benefitComponentBO.setBusinessDomainList(businessDomain
                        .getDomainList());
            }
            AdapterUtil.endTransaction(benefitComponentAdapterServiceAccess);
        	AdapterUtil.logTheEndTransaction(transactionId , this , "persist(BenefitComponentBO benefitComponentBO, Audit audit, boolean insertFlag)");
        } catch (SevereException se) {
            AdapterUtil.abortTransaction(benefitComponentAdapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId , this , "persist(BenefitComponentBO benefitComponentBO, Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist BenefitComponentBO method in benefitComponentBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
		    AdapterUtil.abortTransaction(benefitComponentAdapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId , this , "persist(BenefitComponentBO benefitComponentBO, Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist BenefitComponentBO method in benefitComponentBuilder",
					errorParams, ae);
		} catch (Exception ex) {
		    AdapterUtil.abortTransaction(benefitComponentAdapterServiceAccess);
        	AdapterUtil.logAbortTxn(transactionId , this , "persist(BenefitComponentBO benefitComponentBO, Audit audit, boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist BenefitComponentBO method in benefitComponentBuilder",
					null, ex);
		}
        return true;
    }


    /**
     * Method to get the business domain values from BenefitComponentBO
     * 
     * @param BenefitComponentBO
     * @return BusinessDomain
     */
    private DomainDetail getBusinessDomainValues(
            BenefitComponentBO benefitComponentBO) {
        DomainDetail businessDomain = new DomainDetail();
        businessDomain.setEntityType("BENEFITCOMP");
        businessDomain.setEntityName(benefitComponentBO.getName());
        businessDomain.setEntityParentId(benefitComponentBO
                .getBenefitComponentParentId());
        //Enhancement
        businessDomain.setEntityId(benefitComponentBO.getBenefitComponentId());
        //End - Enhancement

        //        businessDomain
        //                .setLineOfBusiness(benefitComponentBO.getLineOfBusiness());
        //        businessDomain
        //                .setBusinessEntity(benefitComponentBO.getBusinessEntity());
        //        businessDomain.setBusinessGroup(benefitComponentBO.getBusinessGroup());
        businessDomain
                .setDomainList(benefitComponentBO.getBusinessDomainList());
        businessDomain.setCreatedUser(benefitComponentBO.getCreatedUser());
        businessDomain.setCreatedTimeStamp(benefitComponentBO
                .getCreatedTimestamp());
        businessDomain.setLastUpdatedUser(benefitComponentBO
                .getLastUpdatedUser());
        businessDomain.setLastUpdatedTimeStamp(benefitComponentBO
                .getLastUpdatedTimestamp());
        return businessDomain;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#persist(java.lang.Object,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit, boolean)
     * @param subObject
     * @param mainObject
     * @param audit
     * @param insertFlag
     * @return
     * @throws WPDException
     */
    public boolean persist(Object subObject, BusinessObject mainObject,
            Audit audit, boolean insertFlag) throws WPDException {
        // TODO Auto-generated method stub
        return false;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#delete(com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit)
     * @param BusinessObject
     * @param audit
     * @return
     * @throws WPDException
     */
    public boolean delete(BusinessObject BusinessObject, Audit audit)
            throws WPDException {
        // TODO Auto-generated method stub
        return false;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#delete(java.lang.Object,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit)
     * @param subObject
     * @param mainObject
     * @param audit
     * @return
     * @throws WPDException
     */
    public boolean delete(Object subObject, BusinessObject mainObject,
            Audit audit) throws WPDException {
        // TODO Auto-generated method stub
        return false;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#deleteLatestVersion(com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit)
     * @param BusinessObject
     * @param audit
     * @return
     * @throws WPDException
     */
    public boolean deleteLatestVersion(BusinessObject BusinessObject,
            Audit audit) throws WPDException {
        // TODO Auto-generated method stub
        return false;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#copy(com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit)
     * @param srcBO
     * @param trgtBO
     * @param User
     * @return
     * @throws SevereException
     */
    public boolean copy(BenefitComponentBO srcBO, BenefitComponentBO trgtBO,
            Audit audit) throws SevereException{
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory.
        getAdapterServicesAccess(BusinessConstants.EWPD);
        BenefitComponentAdapterManager benefitComponentAdapterManager = new BenefitComponentAdapterManager();
        srcBO.setCreatedUser(audit.getUser());
        //Get the Transaction Id
        long transactionId = AdapterUtil.getTransactionId();
        try {
            //Begin the Transaction
            AdapterUtil.beginTransaction(adapterServicesAccess);
            //logs the beginTransaction
            AdapterUtil.logBeginTranstn(transactionId,this,"CheckIn(BenefitComponentBO srcBO, BenefitComponentBO trgtBO," +
            		"Audit audit)");
            //Checkin the benefitComponent
            benefitComponentAdapterManager.copyBenefitComponent(srcBO, trgtBO,WebConstants.STATUS_CHECK_IN,adapterServicesAccess);
            // End the Transaction
            AdapterUtil.endTransaction(adapterServicesAccess);
            // log the endTransaction
            AdapterUtil.logTheEndTransaction(transactionId,this,"CheckIn(BenefitComponentBO srcBO, BenefitComponentBO trgtBO," +
    		"Audit audit)");
        }
        catch (AdapterException ae) {
            //Abort the Transaction
            AdapterUtil.abortTransaction(adapterServicesAccess);
            //logs the abortTransaction
            AdapterUtil.logAbortTxn(transactionId,this, "copy(BenefitComponentBO srcBO, BenefitComponentBO trgtBO," +
    		"Audit audit)");
            List errorParams = new ArrayList(2);
            String obj = ae.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "CheckIn failed for BenefitComponentBO in BenefitComponentBuisnessObjectBuilder",
                    errorParams, ae);
        }catch (Exception e) {
            //Abort the Transaction
            AdapterUtil.abortTransaction(adapterServicesAccess);
            //logs the abortTransaction
            AdapterUtil.logAbortTxn(transactionId,this, "copy(BenefitComponentBO srcBO, BenefitComponentBO trgtBO," +
    		"Audit audit)");
            List errorParams = new ArrayList(2);
            String obj = e.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "CheckIn failed for BenefitComponentBO in BenefitComponentBuisnessObjectBuilder",
                    errorParams, e);
        }
        return false;
    }
    /**
     * Method to copy the benefit component.
     * @param srcBO
     * @param trgtBO
     * @param audit
     * @param map
     * @return
     * @throws SevereException
     */

    public boolean copy(BenefitComponentBO srcBO, BenefitComponentBO trgtBO,
            Audit audit, HashMap map) throws SevereException {

        BenefitComponentAdapterManager benefitComponentAdapterManager = new BenefitComponentAdapterManager();
        srcBO.setCreatedUser(audit.getUser());
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory.
        getAdapterServicesAccess(BusinessConstants.EWPD);
        long transactionId = AdapterUtil.getTransactionId();
        try {
            //Begin the Transaction
            AdapterUtil.beginTransaction(adapterServicesAccess);
            //logs the beginTransaction
            AdapterUtil.logBeginTranstn(transactionId,this,"copy(BenefitComponentBO srcBO, BenefitComponentBO trgtBO," +
            		"Audit audit)");
            //Copy the benefitComponent
            benefitComponentAdapterManager.copyBenefitComponent(srcBO, trgtBO,WebConstants.STATUS_COPY,adapterServicesAccess);
            //End the Transaction
            AdapterUtil.endTransaction(adapterServicesAccess);
            // log the endTransaction
            AdapterUtil.logTheEndTransaction(transactionId,this,"copy(BenefitComponentBO srcBO, BenefitComponentBO trgtBO," +
    		"Audit audit)");
        } 
        catch (AdapterException ae) {
            //Abort the Transaction
            AdapterUtil.abortTransaction(adapterServicesAccess);
            //logs the abortTransaction
            AdapterUtil.logAbortTxn(transactionId,this, "copy(BenefitComponentBO srcBO, BenefitComponentBO trgtBO," +
    		"Audit audit)");
            List errorParams = new ArrayList(2);
            String obj = ae.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Copy failed for BenefitComponentBO in BenefitComponentBuisnessObjectBuilder",
                    errorParams, ae);
        }catch (Exception e) {
            //Abort the Transaction
            AdapterUtil.abortTransaction(adapterServicesAccess);
            //logs the abortTransaction
            AdapterUtil.logAbortTxn(transactionId,this, "copy(BenefitComponentBO srcBO, BenefitComponentBO trgtBO," +
    		"Audit audit)");
            List errorParams = new ArrayList(2);
            String obj = e.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Copy failed for BenefitComponentBO in BenefitComponentBuisnessObjectBuilder",
                    errorParams, e);
        }
        return false;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#copy(com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit, java.util.Map)
     * @param srcBO
     * @param trgtBO
     * @param audit
     * @param parameters
     * @return
     * @throws WPDException
     */
    public boolean copy(BusinessObject srcBO, BusinessObject trgtBO,
            Audit audit, Map parameters) throws WPDException {
        // TODO Auto-generated method stub
        return false;
    }


    /**
     * @see com.wellpoint.wpd.business.framework.bo.manager.BusinessObjectBuilder#copyForCheckOut(com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.BusinessObject,
     *      com.wellpoint.wpd.common.bo.Audit)
     * @param srcBO
     * @param trgtBO
     * @param User
     * @return
     * @throws SevereException
     */
    public boolean copyForCheckOut(BenefitComponentBO srcBO,
            BenefitComponentBO trgtBO, Audit audit) throws SevereException {
        //TODO This is a duplicate of copy method, used as tempory. This
        // implementation has to change, when new
        //code is get ready
        BenefitComponentAdapterManager benefitComponentAdapterManager = new BenefitComponentAdapterManager();
        srcBO.setCreatedUser(audit.getUser());
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory.
        getAdapterServicesAccess(BusinessConstants.EWPD);
        long transactionId = AdapterUtil.getTransactionId();
        try {
            //Begin the Transaction
            AdapterUtil.beginTransaction(adapterServicesAccess);
            //logs the beginTransaction
            AdapterUtil.logBeginTranstn(transactionId,this,"copyForCheckOut(BenefitComponentBO srcBO, BenefitComponentBO trgtBO," +
            		"Audit audit)");
            //Copy the benefitComponent
            benefitComponentAdapterManager.copyBenefitComponentForCheckOut(srcBO,
                    trgtBO,adapterServicesAccess);
            //End the Transaction
            AdapterUtil.endTransaction(adapterServicesAccess);
            // log the endTransaction
            AdapterUtil.logTheEndTransaction(transactionId,this,"copyForCheckOut(BenefitComponentBO srcBO, BenefitComponentBO trgtBO," +
    		"Audit audit)");
        } 
        catch (AdapterException ae) {
            //Abort the Transaction
            AdapterUtil.abortTransaction(adapterServicesAccess);
            //logs the abortTransaction
            AdapterUtil.logAbortTxn(transactionId,this, "copyForCheckOut(BenefitComponentBO srcBO, BenefitComponentBO trgtBO," +
    		"Audit audit)");
            List errorParams = new ArrayList(2);
            String obj = ae.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Checkout failed for BenefitComponentBO in BenefitComponentBuisnessObjectBuilder",
                    errorParams, ae);
        }catch (Exception e) {
            //Abort the Transaction
            AdapterUtil.abortTransaction(adapterServicesAccess);
            //logs the abortTransaction
            AdapterUtil.logAbortTxn(transactionId,this, "copy(BenefitComponentBO srcBO, BenefitComponentBO trgtBO," +
    		"Audit audit)");
            List errorParams = new ArrayList(2);
            String obj = e.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Checkout failed for BenefitComponentBO in BenefitComponentBuisnessObjectBuilder",
                    errorParams, e);
        }       
        return false;
    }


    /**
     * Method to check duplicate Benefit Component.
     * 
     * @param BenefitComponentBO
     * @return BenefitComponentBO
     * @throws SevereException
     */
    public List checkDuplicateBenefitComponent(
    		String entityName, List domainList,
            int entityParentId) throws SevereException {
        return this.getBenefitComponentAdapterManager()
                .checkDuplicateBenefitComponent(entityName,domainList,entityParentId);
    }

    /**
     * Method for checking if Benefit component has name same as any benefits.
     * 
     * @param BenefitComponentBO
     * @return BenefitComponentBO
     * @throws SevereException
     */
    public List checkUniqueBenefitComponentName(
            BenefitComponentBO benefitComponentBO) throws SevereException {
        return this.getBenefitComponentAdapterManager()
                .checkUniqueBenefitComponentName(benefitComponentBO);
    }
    /**
     * Method to return Benefit Component Adapter Manager instance
     * 
     * @param void
     * @return BenefitComponentAdapterManager
     */
    public BenefitComponentAdapterManager getBenefitComponentAdapterManager() {
        return new BenefitComponentAdapterManager();
    }


    /**
     * To locate the Valid BenefitComponents for the ProductStructure
     * 
     * @param locateCriteria
     * @return LocateResults
     * @throws WPDException
     */
    public LocateResults locate(BenefitComponentLocateCriteria locateCriteria,
            User user) throws WPDException {

        // Get the object of the AdapterManager
        BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
        LocateResults locateResults = null;
        try{
        // VIEW_ALL ACTION
        if (locateCriteria.getAction() == 1) {
            if (locateCriteria.isViewAllAction()) {
                locateResults = adapterManager
                        .viewAllBenefitComponent(locateCriteria);
            } else {
                locateResults = adapterManager
                        .locateBenefitComponent(locateCriteria);
                //TODO done
                locateResults.setLatestVersion(true);
            }
            List locateResultList = locateResults.getLocateResults();
            //List locateResultsWithDomain = new ArrayList();
            Iterator locateResultListIter = locateResultList.iterator();
            while (locateResultListIter.hasNext()) {
                BenefitComponentBO benefitComponentBO = (BenefitComponentBO) locateResultListIter
                        .next();
                List domainList = DomainUtil.retrieveAssociatedDomains(
                        "BENEFITCOMP", benefitComponentBO
                                .getBenefitComponentParentId());
                //added for lob enhancement               
                benefitComponentBO.setDomainDetail(getDomainDetail(domainList));
                benefitComponentBO.setBusinessDomainList(domainList);
                getReferenceList(benefitComponentBO);
                //locateResultsWithDomain.add(benefitComponentBO);
            }

        } else if (locateCriteria.getAction() == 2) {

        }
        } catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitComponentLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitComponentLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitComponentLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					null, ex);
		}
        return locateResults;
    }
    /**
     * 
     * @param domains
     * @return
     */
    private DomainDetail getDomainDetail(List domains){
    	Map lobMap=new HashMap();
    	Map beMap=new HashMap();
    	Map bgMap=new HashMap();
    	Map mbuMap = new HashMap();
    	DomainDetail domainDetail=new DomainDetail();
    	domainDetail.setLineOfBusiness(new ArrayList(domains==null?0:domains.size()));
    	domainDetail.setBusinessEntity(new ArrayList(domains==null?0:domains.size()));
    	domainDetail.setBusinessGroup(new ArrayList(domains==null?0:domains.size()));
    	/*START CARS*/
    	domainDetail.setMarketBusinessUnit(new ArrayList(domains==null?0:domains.size()));
    	/*END cARS*/
    	domainDetail.setDomainList(domains);
    	for(int i=0;i<domains.size();i++){
    		Domain domain=(Domain)domains.get(i);
    		if(lobMap.get(domain.getLineOfBusiness())==null){
    			DomainItem domainItem=new DomainItem();
    			domainItem.setItemId(domain.getLineOfBusiness());
    			domainItem.setItemDesc(domain.getLineOfBusinessDesc());
    			lobMap.put(domainItem.getItemId(),domainItem);
    			domainDetail.getLineOfBusiness().add(domainItem);
    		}
    		if(beMap.get(domain.getBusinessEntity())==null){
    			DomainItem domainItem=new DomainItem();
    			domainItem.setItemId(domain.getBusinessEntity());
    			domainItem.setItemDesc(domain.getBusinessEntityDesc());
    			beMap.put(domainItem.getItemId(),domainItem);
    			domainDetail.getBusinessEntity().add(domainItem);
    		}
    		if(bgMap.get(domain.getBusinessGroup())==null){
    			DomainItem domainItem=new DomainItem();
    			domainItem.setItemId(domain.getBusinessGroup());
    			domainItem.setItemDesc(domain.getBusinessGroupDesc());
    			bgMap.put(domainItem.getItemId(),domainItem);
    			domainDetail.getBusinessGroup().add(domainItem);
    		}
    		if(mbuMap.get(domain.getMarketBusinessUnit())==null){
    			DomainItem domainItem=new DomainItem();
    			domainItem.setItemId(domain.getMarketBusinessUnit());
    			domainItem.setItemDesc(domain.getMarketBusinessUnitDesc());
    			mbuMap.put(domainItem.getItemId(),domainItem);
    			domainDetail.getMarketBusinessUnit().add(domainItem);
    		}
    	}
    	return domainDetail;
    }

    /**
     * Method to search NonAdjBenefitMandate
     * 
     * @param locateCriteria
     * @param user
     * @return LocateResults
     * @throws WPDException
     *             calling the locate method of
     *             NonAdjBenefitMandateAdapterManager.
     */
    public LocateResults locate(
            NonAdjBenefitMandateLocateCriteria locateCriteria, User user)
            throws WPDException {
        BenefitMandateLocateCriteria benefitMandateLocateCriteria = new BenefitMandateLocateCriteria();
        benefitMandateLocateCriteria.setBenefitMasterSystemId(locateCriteria
                .getBenefitSystemId());
        NonAdjBenefitMandateAdapterManager nonAdjBenefitMandateAdapterManager = new NonAdjBenefitMandateAdapterManager();
        try{
        return nonAdjBenefitMandateAdapterManager
                .locate(benefitMandateLocateCriteria);
        }catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate NonAdjBenefitMandateLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate NonAdjBenefitMandateLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate NonAdjBenefitMandateLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					null, ex);
		}
    }


    /**
     * To locate the BenefitDefinitions
     * 
     * @param locateCriteria
     * @return LocateResults
     * @throws WPDException
     */
    public LocateResults locate(
            ComponentsBenefitDefinitionLocateCriteria locateCriteria, User user)
            throws WPDException {
        // Get the object of the AdapterManager
        BenefitAdapterManager adapterManager = new BenefitAdapterManager();
        LocateResults locateResults = new LocateResults();
        try{
        //** Benefit level/line hide
        if(locateCriteria.isShowHidden()){
        	locateResults.setLocateResults(adapterManager
                    .getAllHiddenBenefitLines("BENEFITCOMP", locateCriteria
                            .getBenefitComponentId(),
                            locateCriteria.getBenefitId(), locateCriteria
                                    .getBenefitComponentId()));
        }else
        //** end
        	locateResults.setLocateResults(adapterManager
                .getAllOverridedBenefitLines("BENEFITCOMP", locateCriteria
                        .getBenefitComponentId(),
                        locateCriteria.getBenefitId(), locateCriteria
                                .getBenefitComponentId(),"",""));
        }catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ComponentsBenefitDefinitionLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ComponentsBenefitDefinitionLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate ComponentsBenefitDefinitionLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					null, ex);
		}
        return locateResults;
    }


    /**
     * This method is to update the benefitLines
     * 
     * @param benefitLevel
     * @param benefitComponentBO
     * @param audit
     * @param insertFlag
     * @return
     * @throws WPDException
     */
    public boolean persist(BenefitLevel benefitLevel,
            BenefitComponentBO benefitComponentBO, Audit audit,
            boolean insertFlag) throws SevereException {
        /*
         * public boolean persist(BenefitLevel benefitLevel) throws
         * SevereException,AdapterException{
         */
        // get the object of the adapter manager
        BenefitAdapterManager adapterManager = new BenefitAdapterManager();
        // get the updated list
        List updatedBenefitValues = benefitLevel.getBenefitLines();
        AdapterServicesAccess adapterServicesAccess = AdapterUtil
                .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try {
            AdapterUtil.beginTransaction(adapterServicesAccess);
        	AdapterUtil.logBeginTranstn(transactionId , this , "persist(BenefitLevel benefitLevel,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
            for (int i = 0; i < updatedBenefitValues.size(); i++) {
                // get the benefitline to be updated from the list
                BenefitLine benefitLine = (BenefitLine) updatedBenefitValues
                        .get(i);
                // TODO get from the audit
                // modified for performance issue on 10th Dec 2007
                if(benefitLine.isModified()){
                    benefitLine.setLastUpdatedUser(audit.getUser());
                    benefitLine.setLastUpdatedTimestamp(audit.getTime());
                    benefitLine.setEntityType("BENEFITCOMP");
                    benefitLine.setBenefitComponentId(benefitLine.getEntitySysId());
                    adapterManager.updateDefenitoinOverridenValue(benefitLine,
                            adapterServicesAccess);
                }
                // end
            }
            AdapterUtil.endTransaction(adapterServicesAccess);
        	AdapterUtil.logTheEndTransaction(transactionId , this , "persist(BenefitLevel benefitLevel,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
        } catch (SevereException se) {
            AdapterUtil.abortTransaction(adapterServicesAccess);
        	AdapterUtil.logAbortTxn(transactionId , this , "persist(BenefitLevel benefitLevel,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist BenefitLevel method in benefitComponentBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
		    AdapterUtil.abortTransaction(adapterServicesAccess);
        	AdapterUtil.logAbortTxn(transactionId , this , "persist(BenefitLevel benefitLevel,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist BenefitLevel method in benefitComponentBuilder",
					errorParams, ae);
		} catch (Exception ex) {
		    AdapterUtil.abortTransaction(adapterServicesAccess);
        	AdapterUtil.logAbortTxn(transactionId , this , "persist(BenefitLevel benefitLevel,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Persist failed for persist BenefitLevel in benefitComponentBuilder",
					errorParams, ex);
		}
        return true;
    }


    /**
     * This method is to delete the benefitLevels
     * 
     * @param subObject
     * @param mainObject
     * @param audit
     * @return
     * @throws ServiceException
     */
    public boolean delete(BenefitLevel subObject,
            BenefitComponentBO mainObject, Audit audit) throws SevereException{
        /*
         * public boolean delete(BenefitLevel subObject, BenefitComponentBO
         * mainObject) throws SevereException,AdapterException{
         */
        // get the object of the adapter manager
        BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
        // get the list of levels to be deleted
        List benefitLevelsToBeDeleted = subObject.getBenefitLevels();
        BenefitLine benefitLineBO = null;
        AdapterServicesAccess adapterServicesAccess = AdapterUtil
                .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try {
        	AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil.logBeginTranstn(transactionId , this ,"delete(BenefitLevel subObject, BenefitComponentBO mainObject, Audit audit)");
            for (int i = 0; i < benefitLevelsToBeDeleted.size(); i++) {
                benefitLineBO = (BenefitLine) benefitLevelsToBeDeleted.get(i);
                benefitLineBO.setEntityType("BENEFITCOMP");
                benefitLineBO
                        .setEntitySysId(mainObject.getBenefitComponentId());
                benefitLineBO.setBenefitComponentId(mainObject
                        .getBenefitComponentId());
                benefitAdapterManager.deleteBenefitDefinitionLevel(
                        benefitLineBO, audit.getUser(), adapterServicesAccess);
            }
        	AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil.logTheEndTransaction(transactionId , this ,"delete(BenefitLevel subObject, BenefitComponentBO mainObject, Audit audit)");
		} catch (SevereException se) {
		    AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil.logAbortTxn(transactionId , this ,"delete(BenefitLevel subObject, BenefitComponentBO mainObject, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete BenefitLevel method in benefitComponentBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
		    AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil.logAbortTxn(transactionId , this ,"delete(BenefitLevel subObject, BenefitComponentBO mainObject, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete BenefitLevel method in benefitComponentBuilder",
					errorParams, ae);
		} catch (Exception ex) {
		    AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil.logAbortTxn(transactionId , this ,"delete(BenefitLevel subObject, BenefitComponentBO mainObject, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete BenefitLevel method in benefitComponentBuilder",
					null, ex);
		}
        return true;
    }


    /**
     * To locate the Valid BenefitComponents for the ProductStructure
     * 
     * @param locateCriteria
     * @return LocateResults
     * @throws WPDException
     */
    public LocateResults locate(BenefitLocateCriteria locateCriteria, User user)
            throws WPDException {

        // Get the object of the AdapterManager
        BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
        try{
        // Call the locate() of the AdapterManager to get the benefitcomponents
        // and return it 
        return adapterManager.locateBenefits(locateCriteria);
        }catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					null, ex);
		}
    }
    /**
     * 
     * @param locateCriteria
     * @return
     * @throws SevereException
     */
	public List invalidBenefitList(BenefitLocateCriteria locateCriteria)throws SevereException {
        // Get the object of the AdapterManager
        BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
        // Call the locate() of the AdapterManager to get the invalid benefit (benefit id) 
        // and return it 
        return adapterManager.invalidBenefitList(locateCriteria);
	}
	/**
	 * 
	 * @param locateCriteria
	 * @return
	 * @throws SevereException
	 */
	public List locateBenefitHieararchies(BenefitLocateCriteria locateCriteria)throws SevereException {
        // Get the object of the AdapterManager
        BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
        // Call the locate() of the AdapterManager to get the attached benefit 
        // and return it 
        try{
        return adapterManager.locateBenefitHieararchies(locateCriteria);
        }catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locateBenefitHieararchies BenefitLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locateBenefitHieararchies BenefitLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locateBenefitHieararchies BenefitLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					null, ex);
		}
	}


    /**
     * Method to insert/update question
     * 
     * @param questionBO,audit,insertFlag
     * @return boolean
     * @throws WPDException
     */
    public boolean persist(BenefitHierarchyBO benefitHierarchyBO,
            BenefitComponentBO benefitComponentBO, Audit audit,
            boolean insertFlag) throws WPDException, SevereException {
        BenefitComponentAdapterManager benefitComponentAdapterManager = new BenefitComponentAdapterManager();
        // Modification - After removing the hard coded values in
        // benefitHierarchy.xml
        // Set audit values to the BO
        benefitComponentBO.setCreatedUser(audit.getUser());
        benefitComponentBO.setLastUpdatedUser(audit.getUser());
        benefitComponentBO.setCreatedTimestamp(audit.getTime());
        benefitComponentBO.setLastUpdatedTimestamp(audit.getTime());
        // End - Modification
        
        AdapterServicesAccess adapterServicesAccess = AdapterUtil
		.getAdapterService();

        long transactionId = AdapterUtil.getTransactionId();
        try{
            AdapterUtil.beginTransaction(adapterServicesAccess);
            AdapterUtil.logBeginTranstn(transactionId , this ,"persist(BenefitHierarchyBO benefitHierarchyBO,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
            if (insertFlag) {
                int benefitID = 0;
                String benefitIds = "";
                //The BenefitComponentId
                int benefitComponentId = benefitComponentBO.getBenefitComponentId();
                //The max size upto which the list is to be iterated
                int size = benefitHierarchyBO.getBenefitHierarchiesList().size();
                //Iterating the list
                for (int i = 0; i < size; i++) {
                    BenefitHierarchyAssociationBO newBO = (BenefitHierarchyAssociationBO) benefitHierarchyBO
                            .getBenefitHierarchiesList().get(i);
                    // Get the StandardBenefitId
                    benefitID = newBO.getBenefitId();
                    if("".equals(benefitIds))
                            benefitIds = (new Integer(benefitID)).toString();
                    else
                            benefitIds = benefitIds + "~" + (new Integer(benefitID)).toString();
                }
                benefitComponentAdapterManager.persistBenefitHierarchies_proc(
                         benefitComponentId, benefitIds, audit,adapterServicesAccess);
            }
            else if (!insertFlag) {

            // Modification - to remove hardcoding in benefitHierarchy.xml
            //        	benefitHierarchyBO.setCreatedTimestamp()
            //			benefitHierarchyBO.setLastUpdatedTimestamp()
            //			benefitHierarchyBO.setCreatedUser()
            //			benefitHierarchyBO.setLastUpdatedUser()
            // End - Modification
            // update logic
            benefitComponentAdapterManager.persistBenefitHierarchies(
                    benefitHierarchyBO, audit.getUser(), insertFlag, adapterServicesAccess);
        }
        AdapterUtil.endTransaction(adapterServicesAccess);
        AdapterUtil.logTheEndTransaction(transactionId , this ,"persist(BenefitHierarchyBO benefitHierarchyBO,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");

        
    } catch (SevereException se) {
        AdapterUtil.abortTransaction(adapterServicesAccess);
        AdapterUtil.logAbortTxn(transactionId , this ,"persist(BenefitHierarchyBO benefitHierarchyBO,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
        List errorParams = new ArrayList(2);
		String obj = se.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in persist BenefitHierarchyBO method in benefitComponentBuilder",
				errorParams, se);
	} catch (AdapterException ae) {
	    AdapterUtil.abortTransaction(adapterServicesAccess);
	    AdapterUtil.logAbortTxn(transactionId , this ,"persist(BenefitHierarchyBO benefitHierarchyBO,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
	    List errorParams = new ArrayList(2);
		String obj = ae.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in persist BenefitHierarchyBO method in benefitComponentBuilder",
				errorParams, ae);
	} catch (Exception ex) {
	    AdapterUtil.abortTransaction(adapterServicesAccess);
	    AdapterUtil.logAbortTxn(transactionId , this ,"persist(BenefitHierarchyBO benefitHierarchyBO,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
	    List errorParams = new ArrayList(2);
		String obj = ex.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in persist BenefitHierarchyBO method in benefitComponentBuilder",
				null, ex);
	}
        return true;
    }

    /**
     * Method to attach notes for BenefitComponent
     */
    public boolean persist(ArrayList noteIdList, BenefitComponentBO mainobject,
            Audit audit, boolean insertFlag) throws WPDException, SevereException {
        // Create an object of adapter manager
        NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();
        // insert logic - to insert the selected notes - iterate thorough them
        AdapterServicesAccess adapterServicesAccess = AdapterUtil
		.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try{
            AdapterUtil.beginTransaction(adapterServicesAccess);
            AdapterUtil.logBeginTranstn(transactionId , this ,"persist(ArrayList noteIdList, BenefitComponentBO mainobject,Audit audit, boolean insertFlag)");
        if (noteIdList.size() != 0) {
            for (int i = 0; i < noteIdList.size(); i++) {
                if (insertFlag == true) {
                    // Set values to bo in each iteration
                    AttachedNotesBO attachedNotesBO = (AttachedNotesBO) noteIdList
                            .get(i);
                    // call the attach method in the adapter manager - for
                    // benefitComponentNotesAttach
                    notesAttachmentAdapterManager.attachNotesForEntity(
                            attachedNotesBO, audit, insertFlag, adapterServicesAccess);
                    // For override StdBenefitNotes
                } else if (insertFlag == false) {
                    // Set values to bo in each iteration
                    NotesAttachmentOverrideBO overridebo = (NotesAttachmentOverrideBO) noteIdList
                            .get(i);
                    overridebo.setOverrideStatus("F");
                    // call method in the adapter manager
                    notesAttachmentAdapterManager.attachNotesForOverrideEntity(
                            overridebo, audit, false, adapterServicesAccess);
                }
            }
        }
        AdapterUtil.endTransaction(adapterServicesAccess);
        AdapterUtil.logTheEndTransaction(transactionId , this , "persist(ArrayList noteIdList, BenefitComponentBO mainobject,Audit audit, boolean insertFlag)");
        } catch (SevereException se) {
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this ,"persist(ArrayList noteIdList, BenefitComponentBO mainobject,Audit audit, boolean insertFlag)");
		List errorParams = new ArrayList(2);
		String obj = se.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in persist noteIdList method in benefitComponentBuilder",
				errorParams, se);
	} catch (AdapterException ae) {
	    AdapterUtil.abortTransaction(adapterServicesAccess);
        AdapterUtil.logAbortTxn(transactionId , this ,"persist(ArrayList noteIdList, BenefitComponentBO mainobject,Audit audit, boolean insertFlag)");
		List errorParams = new ArrayList(2);
		String obj = ae.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in persist noteIdList method in benefitComponentBuilder",
				errorParams, ae);
	} catch (Exception ex) {
	    AdapterUtil.abortTransaction(adapterServicesAccess);
        AdapterUtil.logAbortTxn(transactionId , this ,"persist(ArrayList noteIdList, BenefitComponentBO mainobject,Audit audit, boolean insertFlag)");
		List errorParams = new ArrayList(2);
		String obj = ex.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in persist noteIdList method in benefitComponentBuilder",
				null, ex);
	}
        return true;
    }


    /**
     * 
     * @param criteria
     * @return locateResultsList
     * @throws SevereException
     */
    public List searchForBenefits(BenefitHierarchyLocateCriteria criteria)
            throws SevereException {
        BenefitComponentAdapterManager benefitComponentAdapterManager = new BenefitComponentAdapterManager();
        SearchResults searchResults = null;
        List locateResultsList = null;
        try {
            searchResults = benefitComponentAdapterManager
                    .searchForBenefits(criteria);

        } catch (SevereException severeException) {
            List errorParams = new ArrayList(2);
            String obj = criteria.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in benefitComponentBuilder in the method searchForBenefits",
                    errorParams, severeException);
        }
        locateResultsList = searchResults.getSearchResults();
        return locateResultsList;
    }


    /**
     * To locate the Valid BenefitComponents for the ProductStructure
     * 
     * @param locateCriteria
     * @return LocateResults
     * @throws WPDException
     */
    public LocateResults locate(BenefitHierarchyLocateCriteria locateCriteria,
            User user) throws SevereException {
        // Get the object of the AdapterManager
        BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
        // Call the locate() of the AdapterManager to get the benefitcomponents
        // and return it
        try{
        if (locateCriteria.isValidationFlag()){
            return adapterManager
                    .locateValidBenefitHieararchies(locateCriteria);}
        else{
            return adapterManager.locateBenefitHieararchies(locateCriteria);}
        }catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitHierarchyLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitHierarchyLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitHierarchyLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					null, ex);
		}
    }
    
    /**
     * To locate the Valid BenefitComponents for the ProductStructure
     * 
     * @param locateCriteria
     * @return LocateResults
     * @throws WPDException
     */
    public LocateResults locate(com.wellpoint.wpd.business.benefitcomponent.locatecriteria.BenefitComponentLocateCriteria locateCriteria,
            User user) throws SevereException {
        // Get the object of the AdapterManager
        BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
        // Call the locate() of the AdapterManager to get the benefitcomponents
        // and return it
        try{
        
            return adapterManager.locateBenefitHieararchiesForPrintandView(locateCriteria);
        }catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitHierarchyLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitHierarchyLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitHierarchyLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					null, ex);
		}
    }
    

    //added on 1/4/2008
    /**
     * To locate the Valid BenefitComponents for the ProductStructure
     * 
     * @param locateCriteria
     * @return LocateResults
     * @throws WPDException
     */
    public List getLevelHiddenCount(BenefitLine benefitLine)throws SevereException {
    	BenefitComponentAdapterManager benefitComponentAdapterManager = new BenefitComponentAdapterManager();
    	List list = null;
    	try{
    		list = benefitComponentAdapterManager.getLevelHiddenCount(benefitLine);
    	}catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate getLevelHiddenCount method in BenefitComponentBusinessObjectBuilder",
					errorParams, ae);
		} 
    	return list;
    }
    //ends
    /**
     * Method to insert/update question
     * 
     * @param questionBO,audit,insertFlag
     * @return boolean
     * @throws SevereException
     * @throws WPDException
     */
    public boolean delete(BenefitHierarchyBO benefitHierarchyBO,
            BenefitComponentBO benefitComponentBO, Audit audit)
            throws SevereException{
        BenefitComponentAdapterManager benefitComponentAdapterManager = new BenefitComponentAdapterManager();
        AdapterServicesAccess adapterServicesAccess = AdapterUtil
		.getAdapterService();
        try{       
            benefitComponentAdapterManager.removeBenefitHierarchy(
	                benefitHierarchyBO, adapterServicesAccess,audit.getUser());
	    
        } catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete BenefitHierarchyBO method in benefitComponentBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete BenefitHierarchyBO method in benefitComponentBuilder",
					null, ex);
		}
        return true;
    }


    /**
     * Method called from BCBusinessValidationSrevice to verify if any BC entry
     * exists before deletion
     * 
     * @param BenefitComponentDeleteLocateCriteria
     * @throws WPDException
     * @return locateResultsList
     */
    public List searchIfBenefitComponentExists(
            BenefitComponentDeleteLocateCriteria benefitComponentDeleteLocateCriteria)
            throws WPDException {
        // Create an instance of BenefitComponentAdapterManager
        BenefitComponentAdapterManager benefitComponentAdapterManager = new BenefitComponentAdapterManager();

        SearchResults searchResults = null;
        List locateResultsList = null;

        try {
			// Call searchIfBenefitComponentExists of BenefitComponentAdapterManager
			searchResults = benefitComponentAdapterManager
			        .searchIfBenefitComponentExists(benefitComponentDeleteLocateCriteria);
		} catch (SevereException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AdapterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //SearchResults returns a list of objects.Iterator generates a series
        // of those objects, one at a time.
        Iterator searchResultIterator = searchResults.getSearchResults()
                .iterator();
        //searchResultIterator.hasNext() Returns true if the iteration has more
        // elements
        while (searchResultIterator.hasNext()) {
            // searchResultIterator.next()Returns the next element in the
            // iteration.Set the value to the BenefitComponentBO
            BenefitComponentBO benefitComponentBO = (BenefitComponentBO) searchResultIterator
                    .next();
            // Ceate a new instance of BenefitComponentLocateResult
            BenefitComponentLocateResult bcLocateResult = new BenefitComponentLocateResult();
            //Set the BCId of the current object to the locateResult
            bcLocateResult.setBenefitComponentKey(benefitComponentBO
                    .getBenefitComponentId());
            // Add the updated locateResults to a list
            locateResultsList.add(bcLocateResult);
        }
        // return the list
        return locateResultsList;
    }


    /**
     * To delete BC from various tables
     * 
     * @param BenefitComponentBO
     * @throws WPDException
     * @return boolean
     */
    public boolean delete(BenefitComponentBO benefitComponentBO, Audit audit)
            throws WPDException,SevereException{
    	
    	deleteLatestVersion(benefitComponentBO, audit);    	
        return true;

    }


    /**
     * Method to delete latest Benefit Component
     * 
     * @param benefitComponentBO
     * @param audit
     * @return boolean
     * @throws WPDException
     */
    public boolean deleteLatestVersion(BenefitComponentBO benefitComponentBO,
            Audit audit) throws WPDException, SevereException {
        // Create an instance of the BenefitComponentAdapterManager
        BenefitComponentAdapterManager manager = new BenefitComponentAdapterManager();
        // Create an instance of NotesAttachmentAdapterManager
        NotesAttachmentAdapterManager attachedNotesAdapterManager = new NotesAttachmentAdapterManager();
        AdapterServicesAccess adapterServicesAccess = AdapterUtil
		.getAdapterService();


        long transactionId = AdapterUtil.getTransactionId();
        try {
            AdapterUtil.beginTransaction(adapterServicesAccess);
            AdapterUtil.logBeginTranstn(transactionId , this ,"deleteLatestVersion(BenefitComponentBO benefitComponentBO,Audit audit)"); 
	        benefitComponentBO.setCreatedTimestamp(audit.getTime());
	        benefitComponentBO.setCreatedUser(audit.getUser());
	        //Create a new instance of the BO
	        NoteDomainAssnBO noteDomainAssnBO = new NoteDomainAssnBO();
	        NotesAttachmentDomainOverrideBO notesAttachmentDomainOverrideBO = new NotesAttachmentDomainOverrideBO();
	        //Set values to the BO
	        noteDomainAssnBO
	                .setEntityId(benefitComponentBO.getBenefitComponentId());
	        noteDomainAssnBO.setEntityType(WebConstants.ATTACH_BENEFIT_COMP);
	        notesAttachmentDomainOverrideBO.setPrimaryEntityId(benefitComponentBO
	                .getBenefitComponentId());
	        notesAttachmentDomainOverrideBO
	                .setPrimaryEntityType(WebConstants.ATTACH_BENEFIT_COMP);
	        notesAttachmentDomainOverrideBO
	                .setBenefitComponentId(benefitComponentBO
	                        .getBenefitComponentId());
	
	        // Call the removeBenefitComponent method in the
	        // BenefitComponentAdapterManager
	                
	        boolean retValue = manager.removeBenefitComponent(benefitComponentBO,
	                audit,adapterServicesAccess);
	        if (retValue) {
	            // Delete notes which are directly attached to a BC from NOTE_DOMN_ASSN
	            attachedNotesAdapterManager.deleteAllNoteDomainAssnInfo(
	                    noteDomainAssnBO, audit,adapterServicesAccess);
	            //Delete notes which are domained to a BC from NOTE_DOMN_ASSN
	            noteDomainAssnBO.setEntityType(WebConstants.AVAIL_BENEFIT_COMP);
	            attachedNotesAdapterManager.deleteAllNoteDomainAssnInfo(
	                    noteDomainAssnBO, audit,adapterServicesAccess);
	            // Delete notes from NOTE_DOMN_OVRD_VAL
//	            attachedNotesAdapterManager.deleteComponentNotesOverridden(
//	                    notesAttachmentDomainOverrideBO, audit, adapterServicesAccess);
	        }
	        AdapterUtil.endTransaction(adapterServicesAccess);
	        AdapterUtil.logTheEndTransaction(transactionId , this ,"deleteLatestVersion(BenefitComponentBO benefitComponentBO,Audit audit)");
        } catch (SevereException se) {
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this ,"deleteLatestVersion(BenefitComponentBO benefitComponentBO,Audit audit)");
            List errorParams = new ArrayList(2);
            String obj = se.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in deleteLatestVersion method in benefitComponentBuilder",
                    errorParams, se);
        } catch (AdapterException ae) {
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this ,"deleteLatestVersion(BenefitComponentBO benefitComponentBO,Audit audit)");
            List errorParams = new ArrayList(2);
            String obj = ae.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in deleteLatestVersion method in benefitComponentBuilder",
                    errorParams, ae);
		}catch (Exception ex) {
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this ,"deleteLatestVersion(BenefitComponentBO benefitComponentBO,Audit audit)");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
        	   throw new SevereException(
                    "Exception occured in deleteLatestVersion method in benefitComponentBuilder",
                    null, ex);
        }
        return true;
    }


    /**
     * This method for retrieving questionnare list.
     * 
     * @param ComponentsBenefitAdministrationLocateCriteria.
     * @param User.
     * @return locateResults.
     *         This list contain list of questionnare objects.
     * @throws SevereException
     */
    public LocateResults locate(
            ComponentsBenefitAdministrationLocateCriteria administrationLocateCriteria,
            User user) throws SevereException {
    	
        // Create an instance of the adapter manager
    	BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
    	LocateResults locateResults = new LocateResults();
        // Get the entityId from the locateCruteria
    	List finalList = null;
    	int action = administrationLocateCriteria.getAction();
        switch(action){
    	
        case ComponentsBenefitAdministrationLocateCriteria.LOAD_QUESTIONNARE_LIST:
		        int benefitComponentSysId = administrationLocateCriteria.getEntityId();
		        String entityType = administrationLocateCriteria.getEntiityType();
		        int adminOptSysId = administrationLocateCriteria.getAdminOptSysId();
		        int beneftAdminSysId = administrationLocateCriteria
		                .getBenefitAdminSysId();
		        HashMap allPossibleAnswerMap = administrationLocateCriteria.getAllPossibleAnswerMap();
		        int adminLvlOptSystemId = administrationLocateCriteria.getAdminLvlOptSystemId();
		        int beneftCompParentId = administrationLocateCriteria.getBeneftCompParentId();
		        
		        List locateResultList = adapterManager.getQuestionnaireValues(adminOptSysId, 
					beneftAdminSysId, benefitComponentSysId, adminLvlOptSystemId, beneftCompParentId);
		        
			if (null != locateResultList && 0 < locateResultList.size()) {
				getPossibleAnswerList(locateResultList,allPossibleAnswerMap);
				}
		        finalList=BusinessUtil.getQuestionareHierarchy(locateResultList);
		        locateResults.setLocateResults(finalList);
        break;
    	case ComponentsBenefitAdministrationLocateCriteria.LOAD_SELECTED_CHILD:	
	    		int answerId = administrationLocateCriteria.getAnswerId();
    			String answerDesc = administrationLocateCriteria.getSelectedAnswerDesc();
    			BenefitComponentQuesitionnaireBO benefitComponentQuesitionnaireBO = 
	    			(BenefitComponentQuesitionnaireBO)administrationLocateCriteria.getBenefitComponentQuesitionnaireBO(); 
	    		int questionnaireId = benefitComponentQuesitionnaireBO.getQuestionnaireId();
				int beneftComponentId = administrationLocateCriteria.getEntityId();
				allPossibleAnswerMap = administrationLocateCriteria.getAllPossibleAnswerMap();
				adminLvlOptSystemId = administrationLocateCriteria.getAdminLvlOptSystemId();
		        beneftCompParentId = administrationLocateCriteria.getBeneftCompParentId();
		        
				List oldQuestionnareList = administrationLocateCriteria.getQuestionnareList();
				List oldListForUnsavedQuestion = new ArrayList(administrationLocateCriteria.getQuestionnareList());
				int reaArrangedQuestIndex = administrationLocateCriteria.getQuestionareListIndex();
				((BenefitComponentQuesitionnaireBO)oldQuestionnareList.get(reaArrangedQuestIndex)).setSelectedAnswerid(answerId);
				((BenefitComponentQuesitionnaireBO)oldQuestionnareList.get(reaArrangedQuestIndex)).setSelectedAnswerDesc(answerDesc);
	    	    List childList = adapterManager.getChildQuestionnaireValues(
					answerId, questionnaireId, beneftComponentId,
					administrationLocateCriteria
							.getBenefitComponentQuesitionnaireBO()
							.getAdminLevelOptionSysId(), Integer
							.parseInt(administrationLocateCriteria
									.getBenefitComponentQuesitionnaireBO()
									.getBnftDefId()));
			if(null!=childList){
	    	    getPossibleAnswerList(childList,allPossibleAnswerMap);
	    	    }
	    	    finalList=BusinessUtil.getRearrangedQuestionnareList(childList,oldQuestionnareList,reaArrangedQuestIndex);
	    		locateResults.setLocateResults(finalList);
	    		List notesToDelete = getQuestionareNoteListToDelete(finalList,oldListForUnsavedQuestion);
	    		int adminLevelOptionId = administrationLocateCriteria.getBenefitComponentQuesitionnaireBO()
										.getAdminLevelOptionSysId();
	    		String benefitDefId= benefitComponentQuesitionnaireBO.getBnftDefId();
	    		deleteUnsavedQuestionNote(notesToDelete,beneftComponentId,adminLevelOptionId,benefitDefId,user);
    	break;
    	case ComponentsBenefitAdministrationLocateCriteria.LOAD_QUESTIONNARE_VIEW:
		        benefitComponentSysId = administrationLocateCriteria.getEntityId();
		        adminOptSysId = administrationLocateCriteria.getAdminOptSysId();
		        beneftAdminSysId = administrationLocateCriteria
		                .getBenefitAdminSysId();
		        allPossibleAnswerMap = administrationLocateCriteria.getAllPossibleAnswerMap();
		        adminLvlOptSystemId = administrationLocateCriteria.getAdminLvlOptSystemId();
		        beneftCompParentId = administrationLocateCriteria.getBeneftCompParentId();
		        
		        locateResultList = adapterManager.getQuestionnaireValues(adminOptSysId, 
					beneftAdminSysId, benefitComponentSysId,adminLvlOptSystemId,beneftCompParentId);
		        finalList=BusinessUtil.getQuestionareHierarchy(locateResultList);
		        locateResults.setLocateResults(finalList);
        break;
        }
        // return the locate results to the business service 
        return locateResults;
    }
    
    /*
	 * this method filter the questionareLiat and flag the question notes to be deleted
	 * 
	 * 
	 */
	public static List getQuestionareNoteListToDelete(List newQuestionnareList,List oldListForUnsavedQuestion){
		
		List listToDelete= new ArrayList();
		
		for(int i=0;i<oldListForUnsavedQuestion.size();i++){
			
			BenefitComponentQuesitionnaireBO oldQuestionnareBo = (BenefitComponentQuesitionnaireBO)oldListForUnsavedQuestion.get(i);
			
			for (int j=0;j<newQuestionnareList.size();j++){
				
				BenefitComponentQuesitionnaireBO newQuestionnareBo = (BenefitComponentQuesitionnaireBO)newQuestionnareList.get(j);
				if(newQuestionnareBo.compareTo(oldQuestionnareBo)){
					newQuestionnareBo.setUnsavedData(false);
					break;
				}
			}
			//listToDelete.add(oldQuestionnareBo);
		}
		
		return newQuestionnareList;
		
	}
	
	/* this method perfotm delete the unsaved question note deatils while the questionnare answer changes
	 * @noteDetailList,benefitId,adminLevelOptionId,user
	 * 
	 * 
	 */
	private void deleteUnsavedQuestionNote(List noteDetailList,int beneftComponentId,int adminLevelOptionId,String benefitDefId,User user)
	throws SevereException{
		NotesAdapterManager adapterManager = new NotesAdapterManager();
		
		for(int i=0;i<noteDetailList.size();i++){
			Audit audit = new AuditImpl();
			audit.setUser(user.getUserId());
			BenefitComponentQuesitionnaireBO questionnareBo = (BenefitComponentQuesitionnaireBO)noteDetailList.get(i);
			if(questionnareBo.isUnsavedData()){
				questionnareBo.setNotesExists("N");
				int questionId = questionnareBo.getQuestionId();
				NotesToQuestionAttachmentBO attachmentBo = new NotesToQuestionAttachmentBO();
				attachmentBo.setPrimaryId(beneftComponentId);
				attachmentBo.setSecondaryId(adminLevelOptionId);
				attachmentBo.setQuestionId(questionId);
				attachmentBo.setPrimaryEntityType("ATTACHCOMP");
				attachmentBo.setSecondaryEntityType("ATTACHQUESTION");
				attachmentBo.setNoteOverrideStatus("F");
				attachmentBo.setBnftDefId(benefitDefId);
				attachmentBo.setBenefitCompId(beneftComponentId);
				
				NotesToQuestionAttachmentBO newattachmentBo=getNoteInfo(attachmentBo);
				if(null!=newattachmentBo && null!=newattachmentBo.getNoteId() && !("").equals(newattachmentBo.getNoteId())){
					attachmentBo.setNoteId(newattachmentBo.getNoteId());
					attachmentBo.setNoteVersionNumber(newattachmentBo.getNoteVersionNumber());
					try {
						adapterManager.deleteNotesAttachedToQuestion(attachmentBo,audit);
					} catch (AdapterException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	 private NotesToQuestionAttachmentBO getNoteInfo(NotesToQuestionAttachmentBO attachmentBo){
	 	NotesToQuestionAttachmentBO newAttachmentBo = null;
		BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
		List resultList;
		try {
			resultList = adapterManager.getNoteInfo(attachmentBo);
			if(null!=resultList && resultList.size()>0){
			newAttachmentBo = (NotesToQuestionAttachmentBO)resultList.get(0);
		 	}
		} catch (AdapterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newAttachmentBo;
	 }
    /*
     * this method is for getting possible answer list.
     * @param locateResultList
     *         this list contains  questions needed for possible answer.
     * @ returns answer list
     */
    public void getPossibleAnswerList(List locateResultList) throws SevereException{
    	  if(locateResultList != null && !locateResultList.isEmpty()){	
            QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();

            // Create the Adapter Service Access
            AdapterServicesAccess adapterServicesAccess = AdapterUtil
                    .getAdapterService();

            // Iterate through the list to get the possible answers for the
            // question
            for (int i = 0; i < locateResultList.size(); i++) {
                // Get the individual entitybenefitAdministration objects from
                // the list
            	BenefitComponentQuesitionnaireBO benefitComponentQuesitionnaireBO = (BenefitComponentQuesitionnaireBO) locateResultList
                        .get(i);
                int questionNumber = benefitComponentQuesitionnaireBO.getQuestionId();

                QuestionBO questionBO = new QuestionBO();

                questionBO.setQuestionNumber(questionNumber);

                // Call the getPossibleAnswers() of the question adaptermanager
                List possibleAnswerList = questionAdapterManager
                        .getPossibleAnswer(questionBO, adapterServicesAccess);
                //arrnge answer list 
                List arrangedPossibleAnswerList = BusinessUtil.getRearrangedPossibleAnswerList(possibleAnswerList);
                
                // Set the possible answers list to the
                // entityBenefitAdministration
                benefitComponentQuesitionnaireBO.setPossibleAnswerList(arrangedPossibleAnswerList);
            }
        }
    }
    /*
     * this method is for getting possible answer list.
     * @param locateResultList
     *         this list contains  questions needed for possible answer.
     * @ returns answer list
     */
    public void getPossibleAnswerList(List locateResultList, HashMap allPossibleAnswerMap)
    throws SevereException{
    	  if(locateResultList != null && !locateResultList.isEmpty()){	
            QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();

            // Create the Adapter Service Access
            AdapterServicesAccess adapterServicesAccess = AdapterUtil
                    .getAdapterService();

            // Iterate through the list to get the possible answers for the
            // question
            for (int i = 0; i < locateResultList.size(); i++) {
                // Get the individual entitybenefitAdministration objects from
                // the list
            	BenefitComponentQuesitionnaireBO benefitComponentQuesitionnaireBO = (BenefitComponentQuesitionnaireBO) locateResultList
                        .get(i);
                int questionNumber = benefitComponentQuesitionnaireBO.getQuestionId();

                QuestionBO questionBO = new QuestionBO();

                questionBO.setQuestionNumber(questionNumber);
                
                List possibleAnswerList;
				if(null != allPossibleAnswerMap && allPossibleAnswerMap.containsKey(new Integer(questionNumber))){
					possibleAnswerList = (ArrayList)allPossibleAnswerMap.get(new Integer(questionNumber)); 
					Collections.sort(possibleAnswerList);
					
				}else{
                // Call the getPossibleAnswers() of the question adaptermanager
                    possibleAnswerList = questionAdapterManager
                        .getPossibleAnswer(questionBO, adapterServicesAccess);
				}
				
                //arrnge answer list 
                List arrangedPossibleAnswerList = BusinessUtil.getRearrangedPossibleAnswerList(possibleAnswerList);
                
                // Set the possible answers list to the
                // entityBenefitAdministration
                benefitComponentQuesitionnaireBO.setPossibleAnswerList(arrangedPossibleAnswerList);
            }
        }
    }
    /**
     * This method is save a new Questionnare list
     * 
     * @param ComponentsBenefitAdministrationBO
     * @param BenefitComponentBO
     * @param audit
     * @param insertFlag
     * @return
     * @throws WPDException
     */

    public boolean persist(ComponentsBenefitAdministrationBO administrationBO,
            BenefitComponentBO benefitComponentBO, Audit audit,
            boolean insertFlag) throws  SevereException {
    	
        AdapterServicesAccess adapterServicesAccess = AdapterUtil
                .getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try {
        	BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
            AdapterUtil.beginTransaction(adapterServicesAccess);
            AdapterUtil.logBeginTranstn(transactionId , this ,"persist(ComponentsBenefitAdministrationBO administrationBO,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
            adapterManager.saveQuestionnare(administrationBO, audit, adapterServicesAccess);
            AdapterUtil.endTransaction(adapterServicesAccess);
            AdapterUtil.logTheEndTransaction(transactionId , this ,"persist(ComponentsBenefitAdministrationBO administrationBO,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
        }catch(Exception ex){
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this ,"persist(ComponentsBenefitAdministrationBO administrationBO,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
        	 throw new SevereException(
                    "Persist for ComponentsBenefitAdministrationBO failed.Unknown exception is caught",
                    null, ex);	
        }
        return true;
    }


    /**
     * @param locateCriteria
     * @return
     * @throws ServiceException
     */
    public LocateResults locateBenefitHieararchiesToBeRemoved(
            BenefitHierarchyLocateCriteria locateCriteria)
            throws SevereException {
        BenefitComponentAdapterManager benefitComponentAdapterManager = new BenefitComponentAdapterManager();
        try{
        return benefitComponentAdapterManager
                .locateBenefitHieararchiesToBeRemoved(locateCriteria);
        }catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locateBenefitHieararchiesToBeRemoved BenefitHierarchyLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locateBenefitHieararchiesToBeRemoved BenefitHierarchyLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locateBenefitHieararchiesToBeRemoved BenefitHierarchyLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					null, ex);
		}
    }


    // Locate For notes attached to benefitComponent
    public LocateResults locate(
            BenefitComponentNotesLocateCriteria locateCriteria, User user)
            throws SevereException {
        LocateResults locateResults = null;
        // 	 create an object of adapter manager
        NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();
        try{
        // Set values from locate criteria to the corresponding variables
        int entityId = locateCriteria.getEntityId();
        String entityType = locateCriteria.getEntityType();
        int action = locateCriteria.getAction();
//Change - Nov 30 2007
        //int benefitDefinitionKey = 0;
//End - Change           
       
        if (action == 1) {
      	
            // call the locate method in the adapter manager
            locateResults = notesAttachmentAdapterManager.locateAttachedNotes(
                    entityId, entityType, null);
        } else if (action == 2) {
            int secEntityId = locateCriteria.getSecEntityId();
            String secEntityType = locateCriteria.getSecEntityType();
            int bcId = locateCriteria.getBenefitComponentId();
            // call the locate method in the adapter manager
            locateResults = notesAttachmentAdapterManager
                    .locateAttachedNotesForOverride(entityId, entityType,
                            secEntityId, secEntityType, bcId);
        }
        }catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitComponentNotesLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitComponentNotesLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate BenefitComponentNotesLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					null, ex);
		}
        return locateResults;
    }


    // Delete Attached notes for benefitComponent
    public boolean delete(AttachedNotesBO subObject,
            BenefitComponentBO mainObject, Audit audit) throws SevereException {
        // create an object of adapter manager
        NotesAttachmentAdapterManager attachedNotesAdapterManager = new NotesAttachmentAdapterManager();
        try{
//        	 call the method to delete notes in the adapter manager
            attachedNotesAdapterManager.deleteNotesForEntity(subObject, audit);
        } catch (SevereException se) {
            List errorParams = new ArrayList(2);
            String obj = se.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in delete AttachedNotesBO method in benefitComponentBuilder",
                    errorParams, se);
        } catch (AdapterException ae) {
            List errorParams = new ArrayList(2);
            String obj = ae.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in delete AttachedNotesBO method in benefitComponentBuilder",
                    errorParams, ae);
		}catch (Exception ex) {
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
        	   throw new SevereException(
                    "Exception occured in delete AttachedNotesBO method in benefitComponentBuilder",
                    null, ex);
        }
        
        return true;
    }


    //  Delete Overridden notes for standardBenefit
    public boolean delete(NotesAttachmentOverrideBO subObject,
            BenefitComponentBO mainObject, Audit audit) throws SevereException {
        // create an object of adapter manager    	
    	
    	try{
    	
	        NotesAttachmentAdapterManager attachedNotesAdapterManager = new NotesAttachmentAdapterManager();
	        // call the method to delete overridden notes in the adapter manager
	        attachedNotesAdapterManager.deleteNotesForOverriddenEntity(subObject,
	                audit);
	    
    	}catch (SevereException se) {
            List errorParams = new ArrayList(2);
            String obj = se.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in delete NotesAttachmentOverrideBO method  in benefitComponentBuilder",
                    errorParams, se);
        } catch (AdapterException ae) {
            List errorParams = new ArrayList(2);
            String obj = ae.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in delete NotesAttachmentOverrideBO method in benefitComponentBuilder",
                    errorParams, ae);
		}catch (Exception ex) {
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
        	   throw new SevereException(
                    "Exception occured in delete NotesAttachmentOverrideBO method in benefitComponentBuilder",
                    null, ex);
        }

        return true;
    }


    /**
     * Method to delete the notes attached to a benefit level of the associated
     * standard benefit for overriding
     * 
     * @param overrideBO
     * @param componentBO
     * @param audit
     * @return
     * @throws SevereException
     */
    public boolean delete(NotesAttachmentOverrideBO overrideBO,
            BenefitComponentBO componentBO, User user) throws SevereException{

        // Create an instance of the adapter manager
        NotesAttachmentAdapterManager adapterManager = new NotesAttachmentAdapterManager();

        AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
        List deleteNotesList = overrideBO.getNotesList();

        long transactionId = AdapterUtil.getTransactionId();
        try{
            AdapterUtil.beginTransaction(adapterServicesAccess);
            AdapterUtil.logBeginTranstn(transactionId , this ,"delete(NotesAttachmentOverrideBO overrideBO,BenefitComponentBO componentBO, User user)");
	        if (null != deleteNotesList && !deleteNotesList.isEmpty()) {
	            for (int i = 0; i < deleteNotesList.size(); i++) {
	                NotesAttachmentOverrideBO attachedNotesBO = (NotesAttachmentOverrideBO) deleteNotesList
	                        .get(i);
	
	                if (!attachedNotesBO.isAttachNote()) {
	                    attachedNotesBO.setPrimaryEntityId(overrideBO
	                            .getPrimaryEntityId());
	                    attachedNotesBO.setPrimaryEntityType("ATTACHCOMP");
	                    attachedNotesBO.setSecondaryEntityId(overrideBO
	                            .getSecondaryEntityId());
	                    attachedNotesBO.setSecondaryEntityType("ATTACHLEVEL");
	                    attachedNotesBO.setBenefitComponentId(0);
	                    adapterManager.delete(attachedNotesBO,adapterServicesAccess);
	                }
	            }
	        }
	        AdapterUtil.endTransaction(adapterServicesAccess);
	        AdapterUtil.logTheEndTransaction(transactionId , this ,"delete(NotesAttachmentOverrideBO overrideBO,BenefitComponentBO componentBO, User user)");
		} catch (SevereException se) {
		    AdapterUtil.abortTransaction(adapterServicesAccess);
		    AdapterUtil.logAbortTxn(transactionId , this ,"delete(NotesAttachmentOverrideBO overrideBO,BenefitComponentBO componentBO, User user)");
            List errorParams = new ArrayList(2);
            String obj = se.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in delete method in benefitComponentBuilder",
                    errorParams, se);
        } catch (AdapterException ae) {
		    AdapterUtil.abortTransaction(adapterServicesAccess);
		    AdapterUtil.logAbortTxn(transactionId , this ,"delete(NotesAttachmentOverrideBO overrideBO,BenefitComponentBO componentBO, User user)");
            List errorParams = new ArrayList(2);
            String obj = ae.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in delete method in benefitComponentBuilder",
                    errorParams, ae);
		}catch (Exception ex) {
		    AdapterUtil.abortTransaction(adapterServicesAccess);
		    AdapterUtil.logAbortTxn(transactionId , this ,"delete(NotesAttachmentOverrideBO overrideBO,BenefitComponentBO componentBO, User user)");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
        	   throw new SevereException(
                    "Exception occured in delete method in benefitComponentBuilder",
                    null, ex);
        }
        return true;
    }


    /**
     * To delete the notes from NOTE_DOMN_OVRD_VAL Inputs : EntityID and
     * EntityType
     *  
     */
    public boolean delete(NotesAttachmentDomainOverrideBO subObject,
            BenefitComponentBO mainObject, Audit audit) throws SevereException {
        NotesAttachmentAdapterManager attachedNotesAdapterManager = new NotesAttachmentAdapterManager();
        AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try{
            AdapterUtil.beginTransaction(adapterServicesAccess);
            AdapterUtil.logBeginTranstn(transactionId , this ,"delete(NotesAttachmentDomainOverrideBO subObject,BenefitComponentBO mainObject, Audit audit)");
        	attachedNotesAdapterManager.deleteComponentNotesOverridden(subObject,
                    audit, adapterServicesAccess);
        	AdapterUtil.endTransaction(adapterServicesAccess);
        	AdapterUtil.logTheEndTransaction(transactionId , this ,"delete(NotesAttachmentDomainOverrideBO subObject,BenefitComponentBO mainObject, Audit audit)");

        }catch (SevereException se) {
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this ,"delete(NotesAttachmentDomainOverrideBO subObject,BenefitComponentBO mainObject, Audit audit)");
            List errorParams = new ArrayList(2);
            String obj = se.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in delete method in benefitComponentBuilder",
                    errorParams, se);
        } catch (AdapterException ae) {
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this ,"delete(NotesAttachmentDomainOverrideBO subObject,BenefitComponentBO mainObject, Audit audit)");
            List errorParams = new ArrayList(2);
            String obj = ae.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in delete method in benefitComponentBuilder",
                    errorParams, ae);
		}catch (Exception ex) {
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this ,"delete(NotesAttachmentDomainOverrideBO subObject,BenefitComponentBO mainObject, Audit audit)");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
        	   throw new SevereException(
                    "Exception occured in delete method in benefitComponentBuilder",
                    null, ex);
        }
        
        return true;
    }


    /**
     * To delete the entries corresponding to the benefit that do not match the
     * componentType and mandateType entered
     */
    public boolean delete(BenefitComponent subObj, BenefitComponentBO mainObj,
            Audit audit) throws SevereException {
        BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
        subObj.setCreatedTimestamp(audit.getTime());
        subObj.setCreatedUser(audit.getUser());
        AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        
        try {    
        	AdapterUtil.beginTransaction(adapterServicesAccess);
            AdapterUtil.logBeginTranstn(transactionId , this ,"delete(BenefitComponent subObj, BenefitComponentBO mainObj, Audit audit)");
            adapterManager.deleteBenefit(subObj, audit.getUser(), adapterServicesAccess);
            AdapterUtil.endTransaction(adapterServicesAccess);
	        AdapterUtil.logTheEndTransaction(transactionId , this ,"delete(BenefitComponent subObj, BenefitComponentBO mainObj, Audit audit)");
           
        }catch (SevereException se) {
        	AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this ,"delete(BenefitComponent subObj, BenefitComponentBO mainObj, Audit audit)");
            List errorParams = new ArrayList(2);
            String obj = se.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in delete method in benefitComponentBuilder",
                    errorParams, se);
        } catch (AdapterException ae) {
        	AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this ,"delete(BenefitComponent subObj, BenefitComponentBO mainObj, Audit audit)");
        	List errorParams = new ArrayList(2);
            String obj = ae.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in delete method in benefitComponentBuilder",
                    errorParams, ae);
    	}catch (Exception ex) {
    		AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this ,"delete(BenefitComponent subObj, BenefitComponentBO mainObj, Audit audit)");
        	List errorParams = new ArrayList(2);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
        	   throw new SevereException(
                    "Exception occured in delete method in benefitComponentBuilder",
                    null, ex);
        }

        
        return false;
    }


    /**
     * Method to persist the overridden notes
     * 
     * @param overrideBO
     * @param componentBO
     * @param audit
     * @return
     * @throws SevereException
     */
    public boolean persist(NotesAttachmentOverrideBO overrideBO,
            BenefitComponentBO mainObj, Audit audit, boolean insertflag)
            throws SevereException {

        // Create an instance of the adapter manager
        NotesAttachmentAdapterManager adapterManager = new NotesAttachmentAdapterManager();
        AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try{
            AdapterUtil.beginTransaction(adapterServicesAccess);
            AdapterUtil.logBeginTranstn(transactionId , this ,"persist(NotesAttachmentOverrideBO overrideBO,BenefitComponentBO mainObj, Audit audit, boolean insertflag)");
        if ("BENEFITLINENOTESOVERRIDE".equals(overrideBO.getAction())) {
            List persistNotesList = overrideBO.getNotesList();

            if (null != persistNotesList && !persistNotesList.isEmpty()) {
                for (int i = 0; i < persistNotesList.size(); i++) {
                    NotesAttachmentOverrideBO overrideNotesBO = (NotesAttachmentOverrideBO) persistNotesList
                            .get(i);

                    overrideNotesBO.setPrimaryEntityId(overrideBO
                            .getPrimaryEntityId());
                    overrideNotesBO.setPrimaryEntityType(overrideBO
                            .getPrimaryEntityType());
                    overrideNotesBO.setSecondaryEntityId(overrideBO
                            .getSecondaryEntityId());
                    overrideNotesBO.setSecondaryEntityType(overrideBO
                            .getSecondaryEntityType());
                    overrideNotesBO.setBenefitComponentId(overrideBO
                            .getBenefitComponentId());
                    adapterManager.attachNotesForOverrideEntity(
                            overrideNotesBO, audit, false, adapterServicesAccess);
                }

            }
        } else {
            overrideBO.setOverrideStatus("T");
            // Update status to true while standard BenefitOverride
            adapterManager.attachNotesForOverrideEntity(overrideBO, audit,
                    false, adapterServicesAccess);

        }
        AdapterUtil.endTransaction(adapterServicesAccess);
        AdapterUtil.logTheEndTransaction(transactionId , this ,"persist(NotesAttachmentOverrideBO overrideBO,BenefitComponentBO mainObj, Audit audit, boolean insertflag)");
	} catch (SevereException se) {
	    AdapterUtil.abortTransaction(adapterServicesAccess);
	    AdapterUtil.logAbortTxn(transactionId , this ,"persist(NotesAttachmentOverrideBO overrideBO,BenefitComponentBO mainObj, Audit audit, boolean insertflag)");
		List errorParams = new ArrayList(2);
		String obj = se.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in persist NotesAttachmentOverrideBO method in benefitComponentBuilder",
				errorParams, se);
	} catch (AdapterException ae) {
	    AdapterUtil.abortTransaction(adapterServicesAccess);
	    AdapterUtil.logAbortTxn(transactionId , this ,"persist(NotesAttachmentOverrideBO overrideBO,BenefitComponentBO mainObj, Audit audit, boolean insertflag)");
		List errorParams = new ArrayList(2);
		String obj = ae.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in persist NotesAttachmentOverrideBO method in benefitComponentBuilder",
				errorParams, ae);
	} catch (Exception ex) {
	    AdapterUtil.abortTransaction(adapterServicesAccess);
	    AdapterUtil.logAbortTxn(transactionId , this ,"persist(NotesAttachmentOverrideBO overrideBO,BenefitComponentBO mainObj, Audit audit, boolean insertflag)");
		List errorParams = new ArrayList(2);
		String obj = ex.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in persist NotesAttachmentOverrideBO method in benefitComponentBuilder",
				null, ex);
	}
        return true;
    }


    public LocateResults locate(BenefitMandateLocateCriteria locateCriteria)
            throws SevereException {
        NonAdjBenefitMandateAdapterManager nonAdjBenefitMandateAdapterManager = new NonAdjBenefitMandateAdapterManager();
       try{
        return nonAdjBenefitMandateAdapterManager
                .locateCitationNumber(locateCriteria);
       }catch (SevereException se) {			
		List errorParams = new ArrayList(2);
		String obj = se.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in locate BenefitMandateLocateCriteria method in BenefitComponentBusinessObjectBuilder",
				errorParams, se);
	} catch (AdapterException ae) {
		List errorParams = new ArrayList(2);
		String obj = ae.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in locate BenefitMandateLocateCriteria method in BenefitComponentBusinessObjectBuilder",
				errorParams, ae);
	} catch (Exception ex) {
		List errorParams = new ArrayList(2);
		String obj = ex.getClass().getName();
		errorParams.add(obj);
		errorParams.add(obj.getClass().getName());
		throw new SevereException(
				"Exception occured in locate BenefitMandateLocateCriteria method in BenefitComponentBusinessObjectBuilder",
				null, ex);
	}
    }


    public LocateResults locate(StateLocateCriteria locateCriteria)
            throws SevereException {
        NonAdjBenefitMandateAdapterManager nonAdjBenefitMandateAdapterManager = new NonAdjBenefitMandateAdapterManager();
        try{
        return nonAdjBenefitMandateAdapterManager
                .locateStateObject(locateCriteria);
        }catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate StateLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate StateLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in locate StateLocateCriteria method in BenefitComponentBusinessObjectBuilder",
					null, ex);
		}
    }
    
    public BenefitComponentBO retrieveBenefitComponentById(BenefitComponentBO benefitComponentBO) throws SevereException{
        BenefitComponentAdapterManager adapter = new BenefitComponentAdapterManager();
        try{
        	return adapter.retrieveBenefitComponentById(benefitComponentBO);
        } catch (SevereException se) {			
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieveBenefitComponentById BenefitComponentBO method in benefitComponentBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieveBenefitComponentById BenefitComponentBO method in benefitComponentBuilder",
					errorParams, ae);
		} catch (Exception ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in retrieveBenefitComponentById BenefitComponentBO method in benefitComponentBuilder",
					null, ex);
		}
    }
    /**
     * Method to persist the hide/unhide of admin option in benefit component
     * @param adminOptionHideBO
     * @param benefitComponentBO
     * @param user
     * @param insertFlag
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public boolean persist(AdminOptionHideBO adminOptionHideBO,BenefitComponentBO benefitComponentBO,Audit audit,boolean insertFlag) throws SevereException {
    	List adminOptionList =null;
    	BenefitComponentAdapterManager benefitComponentAdapterManager = new BenefitComponentAdapterManager();
     //   AuditFactory auditFactory = (AuditFactory) ObjectFactory
      //  .getFactory(ObjectFactory.AUDIT);
       // Audit audit = auditFactory.getAudit(user);
        AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
        adminOptionHideBO.setLastUpdatedTimestamp(audit.getTime());
        adminOptionHideBO.setLastUpdatedUser(audit.getUser());
        List adminList=adminOptionHideBO.getAdminList();
        long transactionId = AdapterUtil.getTransactionId();
        try {
            AdapterUtil.beginTransaction(adapterServicesAccess);
            AdapterUtil.logBeginTranstn(transactionId , this ,"persist(AdminOptionHideBO adminOptionHideBO,BenefitComponentBO benefitComponentBO,Audit audit,boolean insertFlag)");
            
            for(int i=0;i<adminList.size();i++){
        	AdminOptionHideBO administrationOptionHideBO=(AdminOptionHideBO)adminList.get(i);
        	administrationOptionHideBO.setLastUpdatedTimestamp(audit.getTime());
        	administrationOptionHideBO.setLastUpdatedUser(audit.getUser());
       
            benefitComponentAdapterManager.hideAdminOptions(administrationOptionHideBO,audit.getUser(),adapterServicesAccess);
	       }
            AdapterUtil.endTransaction(adapterServicesAccess);
            AdapterUtil.logTheEndTransaction(transactionId , this ,"persist(AdminOptionHideBO adminOptionHideBO,BenefitComponentBO benefitComponentBO,Audit audit,boolean insertFlag)");
		} catch (SevereException se) {
		    AdapterUtil.abortTransaction(adapterServicesAccess);
		    AdapterUtil.logAbortTxn(transactionId , this ,"persist(AdminOptionHideBO adminOptionHideBO,BenefitComponentBO benefitComponentBO,Audit audit,boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist AdminOptionHideBO method  in benefitComponentBuilder",
					errorParams, se);
		} catch (AdapterException ae) {
		    AdapterUtil.abortTransaction(adapterServicesAccess);
		    AdapterUtil.logAbortTxn(transactionId , this ,"persist(AdminOptionHideBO adminOptionHideBO,BenefitComponentBO benefitComponentBO,Audit audit,boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ae.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist AdminOptionHideBO method in benefitComponentBuilder",
					errorParams, ae);
		} catch (Exception ex) {
		    AdapterUtil.abortTransaction(adapterServicesAccess);
		    AdapterUtil.logAbortTxn(transactionId , this ,"persist(AdminOptionHideBO adminOptionHideBO,BenefitComponentBO benefitComponentBO,Audit audit,boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist AdminOptionHideBO method in benefitComponentBuilder",
					null, ex);
		}
        
       	return true;
    }
    
    /**
     * Created on 6th march 2008
     * To persist the benefitComponentBO
     * 
     * @param benefitComponentBO
     * @return boolean
     * @throws SevereException
     */
    public boolean persistTimeStamp(BenefitComponentBO benefitComponentBO, Audit audit) throws SevereException {
		BenefitComponentAdapterManager benefitComponentAdapterManager = new BenefitComponentAdapterManager();
		try {
			
			benefitComponentBO.setLastUpdatedUser(audit.getUser());
			benefitComponentBO.setLastUpdatedTimestamp(audit.getTime());				
			
			benefitComponentAdapterManager.updateBenefitComponent(
					benefitComponentBO);
	
		} catch (AdapterException ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persistTimeStamp method , for persisting the BusinessObject BenefitComponentBO, in benefitComponentBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			throw new SevereException("Unhandled exception occured", null, ex);
		}

		return true;
	}
    
    
    public LocateResults getBenefitCount(
			BenefitHierarchyLocateCriteria locateCriteria)
			throws SevereException {
		BenefitComponentAdapterManager adapterManager = new BenefitComponentAdapterManager();
		try {
			return adapterManager.getBenefitCount(locateCriteria);
		} catch (SevereException se) {
			List errorParams = new ArrayList(2);
			String obj = se.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in getBenefitCount method in BenefitComponentBusinessObjectBuilder",
					errorParams, se);
		}
	}
    /**
     * Mehtod useed to insert/update the attached notes in an Admin Option
     * @param notesToQuestionAttachmentBO
     * @param benefitCompBO
     * @param audit
     * @param insertFlag
     * @throws SevereException
     */
    public void persist(
			NotesToQuestionAttachmentBO notesToQuestionAttachmentBO,
			BenefitComponentBO benefitCompBO, Audit audit, boolean insertFlag)
			throws SevereException {

		NotesAdapterManager notesAdapterManager = new NotesAdapterManager();

		notesToQuestionAttachmentBO.setCreatedTimestamp(benefitCompBO
				.getCreatedTimestamp());
		notesToQuestionAttachmentBO.setLastUpdatedTimestamp(benefitCompBO
				.getLastUpdatedTimestamp());
		notesToQuestionAttachmentBO.setLastUpdatedUser(benefitCompBO
				.getLastUpdatedUser());
		notesToQuestionAttachmentBO.setCreatedUser(benefitCompBO
				.getCreatedUser());

		try {

			if (insertFlag)
				notesAdapterManager.insertNotesAttachedToQuestion(
						notesToQuestionAttachmentBO, audit);

		} catch (AdapterException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject NotesToQuestionAttachmentBO, in BCBusinessObjectBuilder",
					errorParams, exception);

		}
		try {

			if (!insertFlag)
				notesAdapterManager.updateNotesAttachedToQuestion(
						notesToQuestionAttachmentBO, audit);

		} catch (AdapterException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new SevereException(
					"Exception occured in persist method , for updating the BusinessObject NotesToQuestionAttachmentBO, in BCBusinessObjectBuilder",
					errorParams, exception);

		}
	}
    
	/**
	 * Mehtod useed to delete the attached notes in an Admin Option
	 * @param notesToQuestionAttachmentBO
	 * @param benefitCompBO
	 * @param audit
	 * @throws SevereException
	 */
	public void delete(NotesToQuestionAttachmentBO notesToQuestionAttachmentBO,
			BenefitComponentBO benefitCompBO, Audit audit)
			throws SevereException {
		NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
		try {
			notesAdapterManager.deleteNotesAttachedToQuestion(
					notesToQuestionAttachmentBO, audit);
		} catch (AdapterException exception) {
			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new SevereException(
					"Exception occured in delete method , for deleting the BusinessObject NotesToQuestionAttachmentBO, in BCBusinessObjectBuilder",
					errorParams, exception);
		}
	}
}

