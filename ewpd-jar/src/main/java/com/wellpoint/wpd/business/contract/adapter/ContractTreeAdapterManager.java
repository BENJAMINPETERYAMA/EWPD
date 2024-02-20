/*
 * ContractTreeAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.contract.adapter;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.common.contract.tree.bo.ContractTreeProducts;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeBenefitComponents;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeStandardBenefits;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ContractTreeAdapterManager {
    
    /**
     * This method for retrieving the Product Details for the corresponding Contract.
     * @param businessObject
     * @return
     * @throws SevereException
     */
    public Object retrieveProducts(ContractTreeProducts businessObject) throws SevereException{
        businessObject = (ContractTreeProducts)AdapterUtil.performRetrieve(businessObject);
		return businessObject;
    }
    
    //method to load benefitcomponent for the contract tree
    public List getContractTreeBenefitComponents(
            ProductTreeBenefitComponents details) throws SevereException {        
        List benefitComponents = null;
        HashMap referenceValueSet = new HashMap();
        if (null != details) {
            int productId = details.getProductId();
            int dateSegmentId = details.getDateSegmentId();
            if (0 != productId && 0 != dateSegmentId) {
                referenceValueSet.put("productId", new Integer(details
                        .getProductId()));
                referenceValueSet.put("dateSegmentId",new Integer(details.getDateSegmentId()));
                
            }
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductTreeBenefitComponents.class.getName(),
                "searchContractBenefitComponents", referenceValueSet);
        benefitComponents = AdapterUtil.performSearch(searchCriteria)
                .getSearchResults();
        return benefitComponents;     
               

    }
    // method to load standard benefits for the contract tree
    public List getContractTreeStandardBenefits(
            ProductTreeStandardBenefits details) throws SevereException {
        List standardBenefits = null;
        HashMap referenceValueSet = new HashMap();
        if (null != details) {
            int standardBnftId = details.getBenefitComponentId();
            int dateSegmentId =  details.getDateSegmentId();
            if (0 != standardBnftId) {
                referenceValueSet.put("benefitComponentId", new Integer(details
                        .getBenefitComponentId()));
                // change for new enhancement -- Start
                referenceValueSet.put("dateSegmentId",new Integer(details.getDateSegmentId()));
                // change for new enhancement -- End
            }
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductTreeStandardBenefits.class.getName(),
                "searchContractStandardBenefits", referenceValueSet);
            standardBenefits =AdapterUtil.performSearch(searchCriteria).getSearchResults();
     
        return standardBenefits;

    }

	/**
	 * @param benefitComponentDetails
	 * @return
	 * @throws SevereException
	 */
	public List getContractTreeBenefitComponentsForMigration(ProductTreeBenefitComponents benefitComponentDetails) throws SevereException {
		List benefitComponents = null;
        HashMap referenceValueSet = new HashMap();
        if (null != benefitComponentDetails) {
            int productId = benefitComponentDetails.getProductId();
            if (0 != productId) {
                referenceValueSet.put("productId", new Integer(benefitComponentDetails
                        .getProductId()));                
            }
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductTreeBenefitComponents.class.getName(),
                "searchBenefitComponentsForMigration", referenceValueSet);
        benefitComponents = AdapterUtil.performSearch(searchCriteria)
                .getSearchResults();
        return benefitComponents; 
	}
	/**
	 * @param benefitComponentDetails
	 * @return
	 * @throws SevereException
	 */
	public List getContractTreeBenefitComponentsForMigration(ProductTreeBenefitComponents benefitComponentDetails, int baseDateSegId) throws SevereException {
		List benefitComponents = null;
        HashMap referenceValueSet = new HashMap();
        if (null != benefitComponentDetails) {
            int productId = benefitComponentDetails.getProductId();
            if (0 != productId) {
                referenceValueSet.put("productId", new Integer(benefitComponentDetails
                        .getProductId()));
                referenceValueSet.put("dateSegmentId",new Integer(baseDateSegId));
            }
        }
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                ProductTreeBenefitComponents.class.getName(),
                "searchContractBenefitComponents", referenceValueSet);
        benefitComponents = AdapterUtil.performSearch(searchCriteria)
                .getSearchResults();
        return benefitComponents; 
	}
}
