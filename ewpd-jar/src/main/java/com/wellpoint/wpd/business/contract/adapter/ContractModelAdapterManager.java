/*
 * Created on Jul 1, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.contract.adapter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.common.contract.model.ContractModel;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.web.util.WPDStringUtil;


/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractModelAdapterManager {
	
    /**
     * 
     * Method to get the initial information of the Contract
     * 
     * @param contractSysId
     * @param effectiveDate
     * @return
     * @throws SevereException
     */
	public List getContractIntialInformation(int contractSysId, Date effectiveDate)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("contractId", new Integer(
				contractSysId));
		referenceValueSet.put("effectiveDate", 
				WPDStringUtil
				.convertDateToString(effectiveDate));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractIntialInformation", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}
	
	/**
	 * Method to get the Basic and the Specific 
	 * information of the Contract
	 * 
	 * @param dateSegmentId
	 * @return
	 * @throws SevereException
	 */
    public List getContractBasicAndSpecificInfomation(int dateSegmentId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractBasicAndSpecificInformation", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
    	
    }
    
    /**
	 * 
	 * Method to get the Pricing 
	 * information of the Contract
	 * 
     * @param dateSegmentId
     * @return
     * @throws SevereException
     */
    public List getContractPricingInformation(int dateSegmentId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractPricing", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
    	
    }
    
    /**
     * 
	 * Method to get the Contract Level 
	 * Admin Option of the Contract
	 * 
     * @param dateSegmentId
     * @return
     * @throws SevereException
     */
    public List getContractAdminOptions(int dateSegmentId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractAdminOptions", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
    	
    }
    
    /**
     * 
	 * Method to get the Rules 
	 * of the Contract
     * 
     * @param dateSegmentId
     * @return
     * @throws SevereException
     */
    public List getContractRules(int dateSegmentId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractRules", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
    	
    }
    /**
     * 
	 * Method to get the Product 
	 * of the Contract
     * 
     * @param prodId
     * @return
     * @throws SevereException
     */
	public List getContractProductInfo(int prodId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("prodId", new Integer(
				prodId));

		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractProduct", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}
	
	/**
	 * 
	 * 
	 * Method to get the Benefit Lines 
	 * information of Contract
	 * 
	 * @param dateSegmentId
	 * @param benefitComponentId
	 * @param benefitId
	 * @return
	 * @throws SevereException
	 */
	public List getContractBenefitLineInfo(int dateSegmentId, int benefitComponentId,int benefitId)throws SevereException{

		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		referenceValueSet.put("benefitComponentId", new Integer(
				benefitComponentId));
		referenceValueSet.put("benefitId", new Integer(benefitId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractBenefitLines", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}
	
	/**
	 * 
	 * Method to get the Benefit Admin Options 
	 * of Contract
	 * 
	 * 
	 * @param dateSegmentId
	 * @param benefitComponentId
	 * @param benefitDefId
	 * @return
	 * @throws SevereException
	 */
	public List getContractBenefitAdminOptionsInfo(int dateSegmentId, int benefitComponentId,int benefitDefId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		referenceValueSet.put("benefitComponentId", new Integer(
				benefitComponentId));
		referenceValueSet.put("benefitDefId", new Integer(benefitDefId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractBenefitAdminOptions", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}
	
	/**
	 * 
	 * Method to get the Benefit AdminMethods 
	 * in Contract
	 * 
	 * @param dateSegmentId
	 * @param benefitComponentId
	 * @param benefitDefId
	 * @return
	 * @throws SevereException
	 */
	public List getContractBenefitAdminMethodsInfo(int dateSegmentId, int benefitComponentId,int benefitDefId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		referenceValueSet.put("benefitComponentId", new Integer(
				benefitComponentId));
		referenceValueSet.put("benefitDefId", new Integer(benefitDefId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractAdminMethods", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}
	

	/**
	 * 
	 * Method to get the Benefit Component
	 * of the Contract
	 * 
	 * 
	 * @param dateSegmentId
	 * @param prodId
	 * @return
	 * @throws SevereException
	 */
	public List getContractBenefitComponentInfo(int dateSegmentId, int prodId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		referenceValueSet.put("prodId", new Integer(
				prodId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractBenefitComponents", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}

	/**
	 * 
	 * Method to get the Benefits of the
	 * Contract
	 * 
	 * 
	 * @param dateSegmentId
	 * @param benefitComponentId
	 * @return
	 * @throws SevereException
	 */
	public List getContractBenefits(int dateSegmentId, int benefitComponentId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		referenceValueSet.put("benefitComponentId", new Integer(
				benefitComponentId));

		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractBenefits", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
		
	}
	
	
	/**
	 * 
	 * Method to get the Benefit Component Notes of the
	 * Contract
	 * 
	 * @param dateSegmentId
	 * @param benefitComponentId
	 * @return
	 * @throws SevereException
	 */
	public List getContractBenefitComponentNotes(int dateSegmentId, int benefitComponentId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		referenceValueSet.put("benefitComponentId", new Integer(
				benefitComponentId));

		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractBenefitComponentNotes", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
		
	}
	
	
	/**
	 * 
	 * 
	 * Method to get the Benefit Notes of the Contract
	 * 
	 * 
	 * @param dateSegmentId
	 * @param benefitComponentId
	 * @param benefitId
	 * @return
	 * @throws SevereException
	 */
	public List getContractBenefitNotes(int dateSegmentId, int benefitComponentId, int benefitId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		referenceValueSet.put("benefitComponentId", new Integer(
				benefitComponentId));
		referenceValueSet.put("benefitId", new Integer(
				benefitId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractBenefitNotes", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
		
	}
	
	
	/**
	 * 
	 * Method to get the Benefit details of the Contract
	 * 
	 * 
	 * @param benefitKey
	 * @return
	 * @throws SevereException
	 */
	public List getContractBenefitDetails(int benefitKey)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("benefitId", new Integer(
				benefitKey));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractBenefitBenefitLevelScope", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * Method to get the Contract Benefit 
	 * domain details
	 * 
	 * @param benefitKey
	 * @return
	 * @throws SevereException
	 */
	public List getContractBenefitDomainDetails(int benefitKey)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("benefitId", new Integer(
				benefitKey));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractBenefitDomains", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}
	
	
	
	/**
	 * 
	 * 
	 * Method to get the Contract Benefit Rule details
	 * 
	 * 
	 * @param dateSegmentId
	 * @param benefitComponentId
	 * @param benefitKey
	 * @return
	 * @throws SevereException
	 */
	public List getContractBenefitRuleDetails(int dateSegmentId, int benefitComponentId,int benefitKey)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		referenceValueSet.put("benefitComponentId", new Integer(
				benefitComponentId));
		referenceValueSet.put("benefitId", new Integer(
				benefitKey));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractBenefitRules", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}

	/**
	 * 
	 * 
	 * 
	 * Method to get the Contract Domain details
	 * 
	 * @param contractParentSysId
	 * @return
	 * @throws SevereException
	 */
	public List getContractDomain(int contractParentSysId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("contractParentSysId", new Integer(
				contractParentSysId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractDomain", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}
	
	
	/**
	 * 
	 * 
	 * Method to get the Contract 
	 * Product domain
	 * 
	 * 
	 * 
	 * @param prodId
	 * @return
	 * @throws SevereException
	 */
	public List getContractProductDomain(int prodId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("prodId", new Integer(
				prodId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractProductDomain", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * Method to get the Contract Benefit Component Domain
	 * 
	 * @param benefitComponentId
	 * @return
	 * @throws SevereException
	 */
	public List getContractBenefitComponentDomain(int benefitComponentId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("benefitComponentId", new Integer(
				benefitComponentId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractbenefitComponentDomain", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}
	
	/**
	 * 
	 * 
	 * Method to get the Contract Membership
	 * information
	 * 
	 * @param contractSysId
	 * @return
	 * @throws SevereException
	 */
	public List getContractMembership(int contractSysId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("contractId", new Integer(
				contractSysId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractMembership", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}
	
	
	/**
	 * 
	 * Method to get the Contract Adapted 
	 * information
	 * 
	 * 
	 * @param dateSegmentId
	 * @return
	 * @throws SevereException
	 */
	public List getContractAdapted(int dateSegmentId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractAdapted", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}
	/**
	 * Method to get the Contract Notes
	 * 
	 * @param dateSegmentId
	 * @return
	 * @throws SevereException
	 */
	
	public List getContractNotes(int dateSegmentId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractNotes", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}
	
	public List getContractTiers(int dateSegmentId,int benefitComponentId,int benefitId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		referenceValueSet.put("benefitComponentId", new Integer(
				benefitComponentId));
		referenceValueSet.put("benefitId", new Integer(
				benefitId));
		
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractTiers", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}

	/**
	 * @param id
	 * @return
	 * @throws SevereException
	 */
	public List getContractTierBenefitLines(int dateSegmentId,int benefitComponentId,int benefitId, int tierId) throws SevereException {
		
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		referenceValueSet.put("benefitComponentId", new Integer(
				benefitComponentId));
		referenceValueSet.put("benefitId", new Integer(
				benefitId));
		referenceValueSet.put("tierId", new Integer(
				tierId));

		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractTierBenefitLines", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		
		return searchResultList;
	}
	
	public List getContractBenefitTierAdminOptionsInfo(int dateSegmentId, int benefitComponentId,int benefitDefId, int tierId)throws SevereException{
		HashMap referenceValueSet = new HashMap();
		SearchCriteria adapterSearchCriteria = null;
		referenceValueSet.put("dateSegmentId", new Integer(
				dateSegmentId));
		referenceValueSet.put("benefitComponentId", new Integer(
				benefitComponentId));
		referenceValueSet.put("benefitDefId", new Integer(benefitDefId));
		referenceValueSet.put("tierId", new Integer(
				tierId));
		
		adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
				ContractModel.class.getName(),
				"getContractBenefitTierAdminOptions", referenceValueSet);
		List searchResultList = AdapterUtil
				.performSearch(adapterSearchCriteria).getSearchResults();
		return searchResultList;
	}
	

}
