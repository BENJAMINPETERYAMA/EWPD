/*
 * ProductTreeAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.product.adapter;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeAdminOptions;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitAdministration;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitComponents;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeStandardBenefits;
import com.wellpoint.wpd.util.TimeHandler;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductTreeAdapterManager {
    //method to get the major heading data that is the benefit components list
    // from the backend.
	
	
	
	

	
	 /**
     * @param details
     * @return
     * @throws SevereException
     */
    public List getContractProductBenefitComponentsHideUnhide(
            ProductTreeBenefitComponents details) throws SevereException {
        // TODO Auto-generated method stub
        
        List benefitComponents = null;
        HashMap referenceValueSet = new HashMap();
        if (null != details) {
            int productId = details.getProductId();
            if (0 != productId) {
                referenceValueSet.put("productId", new Integer(details
                        .getProductId()));
                referenceValueSet.put("dateSegmentId", new Integer(details
                        .getDateSegmentId()));
            }
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductTreeBenefitComponents.class.getName(),
                "searchBenefitComponentsHideUnhide", referenceValueSet);
        benefitComponents = AdapterUtil.performSearch(searchCriteria)
                .getSearchResults();
        return benefitComponents;

    }
    /**
     * @param details
     * @return
     * @throws SevereException
     */
    public List getProductTreeBenefitComponents(
            ProductTreeBenefitComponents details) throws SevereException {
        // TODO Auto-generated method stub
        List benefitComponents = null;
        HashMap referenceValueSet = new HashMap();
        if (null != details) {
            int productId = details.getProductId();
            if (0 != productId) {
                referenceValueSet.put("productId", new Integer(details
                        .getProductId()));
            }
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductTreeBenefitComponents.class.getName(),
                "searchBenefitComponents", referenceValueSet);
        benefitComponents = AdapterUtil.performSearch(searchCriteria)
                .getSearchResults();
        return benefitComponents;

    }
    
    /**
     * @param details
     * @return
     */
    public List getProductTreeBenefitAdministrations(
            ProductTreeBenefitAdministration details) throws SevereException {
        // TODO Auto-generated method stub

        List benefitAdministrations = null;
        HashMap referenceValueSet = new HashMap();
        if (null != details) {
            int bnftId = details.getBenefitDefinitionId();
            if (0 != bnftId) {
                referenceValueSet.put("benefitId", new Integer(details
                        .getBenefitDefinitionId()));
                //TODO hard coded value to be modified
                referenceValueSet.put("benefitComponentId", new Integer(details.getBenefitComponentId()));
            }
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductTreeBenefitAdministration.class.getName(),
                "searchBenefitAdministrationDates", referenceValueSet);
        benefitAdministrations = AdapterUtil.performSearch(searchCriteria)
                .getSearchResults();
        

        return benefitAdministrations;

    }

    /**
     * @param adminOptionsDetails
     * @return
     */
    public List getProductTreeAdminOptions(
            ProductTreeAdminOptions adminOptionsDetails)
            throws SevereException {
        // TODO Auto-generated method stub

        List adminOptions = null;
        HashMap referenceValueSet = new HashMap();
        if (null != adminOptionsDetails) {
            int bnftAdminId = adminOptionsDetails.getBenefitAdministrationId();
            if (0 != bnftAdminId) {
                referenceValueSet.put("benefitAdministrationId", new Integer(
                        adminOptionsDetails.getBenefitAdministrationId()));
                referenceValueSet.put("adminLevelType", new Integer(
                        adminOptionsDetails.getAdminLevelType()));
                referenceValueSet.put("productSysId", new Integer(
                        adminOptionsDetails.getEntityId()));
                referenceValueSet.put("benefitComponentId", new Integer(
                        adminOptionsDetails.getBenefitComponentId()));
               
            }
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductTreeAdminOptions.class.getName(),
                "searchAdminOptions", referenceValueSet);
      
      
            adminOptions = AdapterUtil.performSearch(searchCriteria).getSearchResults();
      
        return adminOptions;

    }
    
    /**
     * @param details
     * @return
     */
    public List getProductTreeStandardBenefits(
            ProductTreeStandardBenefits details) throws SevereException {
        // TODO Auto-generated method stub

        List standardBenefits = null;
        HashMap referenceValueSet = new HashMap();
        if (null != details) {
            int standardBnftId = details.getBenefitComponentId();
            if (0 != standardBnftId) {
                referenceValueSet.put("benefitComponentId", new Integer(details
                        .getBenefitComponentId()));
                // change for new enhancement -- Start
                referenceValueSet.put("productId",new Integer(details.getProductId()));
                // change for new enhancement -- End
            }
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductTreeStandardBenefits.class.getName(),
                "searchStandardBenefits", referenceValueSet);
            standardBenefits =AdapterUtil.performSearch(searchCriteria).getSearchResults();
     
        return standardBenefits;

    }

    /**
     * @param details
     * @return
     */
    public List searchBenefitsForMigrationTree(
            ProductTreeStandardBenefits details) throws SevereException {
        // TODO Auto-generated method stub

        List standardBenefits = null;
        HashMap referenceValueSet = new HashMap();
        if (null != details) {
            int standardBnftId = details.getBenefitComponentId();
            if (0 != standardBnftId) {
                referenceValueSet.put("benefitComponentId", new Integer(details
                        .getBenefitComponentId()));

            }
            int contractSysId = details.getMigratedContractSysId();
            if (0 != contractSysId) {
                referenceValueSet.put("migratedContractSysId", new Integer(details
                        .getMigratedContractSysId()));

            }
            int contractDatesegmentId = details.getMgrtdDatesegmentId();
            if (0 != contractDatesegmentId) {
                referenceValueSet.put("mgrtdDatesegmentId", new Integer(details.getMgrtdDatesegmentId()));
            }                       
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductTreeStandardBenefits.class.getName(),
                "searchBenefitsForMigrationTree", referenceValueSet);
            standardBenefits =AdapterUtil.performSearch(searchCriteria).getSearchResults();
     
        return standardBenefits;

    }
    
//  method to load standard benefits for the contract tree
    public List searchBenefitsForMigrationTree(
            ProductTreeStandardBenefits details, int baseDateSegId)  throws SevereException {
        List standardBenefits = null;
        HashMap referenceValueSet = new HashMap();
        if (null != details) {
            int standardBnftId = details.getBenefitComponentId();
            int dateSegmentId =  details.getDateSegmentId();
            int contractDatesegmentId = details.getMgrtdDatesegmentId();
            if (0 != standardBnftId) {
                referenceValueSet.put("benefitComponentId", new Integer(details
                        .getBenefitComponentId()));
                int contractSysId = details.getMigratedContractSysId();
                if (0 != contractSysId) {
                    referenceValueSet.put("migratedContractSysId", new Integer(details
                            .getMigratedContractSysId()));

                }
                if (0 != contractDatesegmentId) {
                    referenceValueSet.put("mgrtdDatesegmentId", new Integer(details.getMgrtdDatesegmentId()));
                }
                // change for new enhancement -- Start
                referenceValueSet.put("dateSegmentId",new Integer(baseDateSegId));
                // change for new enhancement -- End
            }
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductTreeStandardBenefits.class.getName(),
                "searchBenefitsForMigrationTreeWithBaseContract", referenceValueSet);
            standardBenefits =AdapterUtil.performSearch(searchCriteria).getSearchResults();
     
        return standardBenefits;

    }
}
