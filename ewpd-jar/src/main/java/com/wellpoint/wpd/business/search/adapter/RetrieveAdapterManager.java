/*
 * RetrieveAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.search.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.engine.AdapterServicesController;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.search.result.BenefitComponentDetail;
import com.wellpoint.wpd.common.search.result.BenefitComponentIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitDetail;
import com.wellpoint.wpd.common.search.result.BenefitIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitLevelDetail;
import com.wellpoint.wpd.common.search.result.BenefitLevelIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitLineDetail;
import com.wellpoint.wpd.common.search.result.BenefitLineIdentifier;
import com.wellpoint.wpd.common.search.result.ContractDetail;
import com.wellpoint.wpd.common.search.result.ContractIdentifier;
import com.wellpoint.wpd.common.search.result.NotesDetail;
import com.wellpoint.wpd.common.search.result.NotesIdentifier;
import com.wellpoint.wpd.common.search.result.ProductDetail;
import com.wellpoint.wpd.common.search.result.ProductIdentifier;
import com.wellpoint.wpd.common.search.result.ProductStructureDetail;
import com.wellpoint.wpd.common.search.result.ProductStructureIdentifier;
import com.wellpoint.wpd.common.search.result.TermIdDetail;
import com.wellpoint.wpd.common.search.util.SearchConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: RetrieveAdapterManager.java 42077 2008-01-25 11:52:19Z u13547 $
 */
public class RetrieveAdapterManager {
    /**
     * 
     * @param productIdentifier
     * @return ProductDetail
     * @throws SevereException
     */
    public ProductDetail productRetrieve(ProductIdentifier productIdentifier)
            throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("identifier", new Integer(productIdentifier
                .getIdentifier()));
        SearchResults searchResult = searchForDetail(
                "com.wellpoint.wpd.common.search.result.ProductDetail",
                "medical", "retrieveProductDetailUsingIdentifier",
                referenceValueSet);
        if(searchResult.getSearchResultCount() == 0){
        	return new ProductDetail();
        }
        return (ProductDetail) searchResult.getSearchResults().get(0);
    }

    /**
     * 
     * @param contractIdentifier
     * @return
     * @throws SevereException
     */
    public ContractDetail contractRetrieve(ContractIdentifier contractIdentifier)
            throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("identifier", new Integer(contractIdentifier
                .getIdentifier()));
        referenceValueSet.put("dateSegIdentifier", new Integer(
                contractIdentifier.getDateSegIdentifier()));
        SearchResults searchResult = searchForDetail(
                "com.wellpoint.wpd.common.search.result.ContractDetail",
                "medical", "retrieveContractDetailUsingIdentifier",
                referenceValueSet);
        if(searchResult.getSearchResultCount() == 0){
        	return new ContractDetail();
        }
        return (ContractDetail) searchResult.getSearchResults().get(0);
    }

    /**
     * 
     * @param productStructureIdentifier
     * @return ProductStructureDetail
     * @throws SevereException
     */
    public ProductStructureDetail productStructureRetrieve(
            ProductStructureIdentifier productStructureIdentifier)
            throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("identifier", new Integer(
                productStructureIdentifier.getIdentifier()));
        SearchResults searchResult = searchForDetail(
                "com.wellpoint.wpd.common.search.result.ProductStructureDetail",
                "medical", "retrieveProductStructureDetailUsingIdentifier",
                referenceValueSet);
        if(searchResult.getSearchResultCount() == 0){
        	return new ProductStructureDetail();
        }
        return (ProductStructureDetail) searchResult.getSearchResults().get(0);
    }

    /**
     * 
     * @param benefitComponentIdentifier
     * @return BenefitComponentDetail
     * @throws SevereException
     */
    public BenefitComponentDetail benefitComponentRetrieve(
            BenefitComponentIdentifier benefitComponentIdentifier)
            throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("identifier", new Integer(
                benefitComponentIdentifier.getIdentifier()));
        SearchResults searchResult = searchForDetail(
                "com.wellpoint.wpd.common.search.result.BenefitComponentDetail",
                "medical", "retrieveBenefitComponentDetailUsingIdentifier",
                referenceValueSet);
        if(searchResult.getSearchResultCount() == 0){
        	return new BenefitComponentDetail();
        }
        return (BenefitComponentDetail) searchResult.getSearchResults().get(0);
    }

    /**
     * 
     * @param benefitIdentifier
     * @return BenefitDetail
     * @throws SevereException
     */
    public BenefitDetail benefitRetrieve(BenefitIdentifier benefitIdentifier)
            throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("identifier", new Integer(benefitIdentifier
                .getIdentifier()));
        SearchResults searchResult = searchForDetail(
                "com.wellpoint.wpd.common.search.result.BenefitDetail",
                "medical", "retrieveBenefitDetailUsingIdentifier",
                referenceValueSet);
        if(searchResult.getSearchResultCount() == 0){
        	return new BenefitDetail();
        }
        return (BenefitDetail) searchResult.getSearchResults().get(0);
    }

    /**
     * 
     * @param benefitLineIdentifier
     * @return BenefitLineDetail
     * @throws SevereException
     */
    public BenefitLineDetail benefitLineRetrieve(
            BenefitLineIdentifier benefitLineIdentifier) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitLineId", new Integer(
                benefitLineIdentifier.getBenefitLineIdentifier()));
        SearchResults searchResult = searchForDetail(
                "com.wellpoint.wpd.common.search.result.BenefitLineDetail",
                "medical", "retrieveBenefitLineDetailUsingIdentifier",
                referenceValueSet);
        if(searchResult.getSearchResultCount() == 0){
        	return new BenefitLineDetail();
        }
        BenefitLineDetail detail = (BenefitLineDetail) searchResult
                .getSearchResults().get(0);
        setTerms(detail);
        return detail;
    }

    /**
     * @param detail
     * @throws SevereException
     */
	private void setQualifiers(BenefitLevelDetail detail) throws SevereException {
        String appendedTerms = detail.getQualifier();        

        if (null != appendedTerms) {
            detail.setQualifier(getAppendedItemTableData(appendedTerms,1935));
        }		
	}

	/**
     * @param detail
     * @throws SevereException
     */
    private void setTerms(BenefitLevelDetail detail) throws SevereException {
        String appendedTerms = detail.getTerm();        

        if (null != appendedTerms) {
            detail.setTerm(getAppendedItemTableData(appendedTerms,1934));
        }
    }
    
    
    private String getAppendedItemTableData(String appendedIds, int id) throws SevereException {
        HashMap referenceValueSet;
        SearchResults searchResult = null;
        TermIdDetail idDetail = null;
        StringBuffer termDescription = new StringBuffer();
        if (null != appendedIds) {
            List termIds = getListOfTermIds(appendedIds);
            
            referenceValueSet = new HashMap();
            referenceValueSet.put("appendedIds", termIds);
            referenceValueSet.put("id", new Integer(id));
            searchResult = searchForTermDetail(
                    "com.wellpoint.wpd.common.search.result.TermIdDetail",
                    "medical", "retrieveDescriptionForTermId",
                    referenceValueSet);
            if (searchResult.getSearchResultCount() != 0) {
                for (int i = 0; i < searchResult.getSearchResults().size(); i++) {
                    idDetail = (TermIdDetail) searchResult.getSearchResults()
                            .get(i);
                    List list = idDetail.getDescription();
                    if (null != list && list.size() > 0) {
                        if (i > 0)
                            termDescription.append(",");
                        termDescription.append(((String) list.get(0)));
                    }
                }
            }
        }
        return termDescription.toString();
    }

    /**
     * 
     * @param benefitLevelIdentifier
     * @return BenefitLevelDetail
     * @throws SevereException
     */
    public BenefitLevelDetail benefitLevelRetrieve(
            BenefitLevelIdentifier benefitLevelIdentifier)
            throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("identifier", new Integer(benefitLevelIdentifier
                .getIdentifier()));
        referenceValueSet.put("benDefIdentifier", new Integer(benefitLevelIdentifier
                .getBenDefIdentifier()));
        SearchResults searchResult = searchForDetail(
                "com.wellpoint.wpd.common.search.result.BenefitLevelDetail",
                "medical", "retrieveBenefitLevelDetailUsingIdentifier",
                referenceValueSet);
        if(searchResult.getSearchResultCount() == 0){
        	return new BenefitLevelDetail();
        }
        BenefitLevelDetail detail = (BenefitLevelDetail) searchResult
                .getSearchResults().get(0);
        setTerms(detail);
        setQualifiers(detail);
        return detail;
    }

    /**
     * @param appendedTerms
     * @return
     */
    private List getListOfTermIds(String appendedTerms) {
        if (null != appendedTerms && !"".equals(appendedTerms)) {
            String[] terms = appendedTerms.split(",");
            return Arrays.asList(terms);
        }
        return null;
    }

    /**
     * 
     * @param noteIdentifier
     * @return NotesDetail
     * @throws SevereException
     */
    public NotesDetail notesRetrieve(NotesIdentifier noteIdentifier)
            throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("identifier", noteIdentifier.getIdentifier());
        referenceValueSet.put("version", new Integer(noteIdentifier
                .getVersion()));
        SearchResults searchResult = searchForDetail(
                "com.wellpoint.wpd.common.search.result.NotesDetail",
                "medical", "retrieveNotesDetailUsingIdentifier",
                referenceValueSet);
        if(searchResult.getSearchResultCount() == 0){
        	return new NotesDetail();
        }
        return (NotesDetail) searchResult.getSearchResults().get(0);
    }

    /**
     * 
     * @param productIdentifiers
     * @param columnName
     * @param orderDirection
     * @return
     * @throws SevereException
     */

    public List productSort(List productIdentifiers, String columnName,
            String orderDirection) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("productIds", productIdentifiers);
        if (SearchConstants.PRODUCT_EFFECTIVE_DATE_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByEffectiveDate", orderDirection);
        } else if (SearchConstants.PRODUCT_EXPIRY_DATE_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByExpiryDate", orderDirection);
        } else if (SearchConstants.PRODUCT_NAME_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByName", orderDirection);
        } else if (SearchConstants.PRODUCT_STATUS_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByStatus", orderDirection);
        } else if (SearchConstants.PRODUCT_VERSION_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByVersion", orderDirection);
        }
        SearchResults searchResult = sortIdentifiers(
                "com.wellpoint.wpd.common.search.result.ProductIdentifier",
                "medical", "sortProductIdentifiers", referenceValueSet);
        return searchResult.getSearchResults();
    }

    /**
     * 
     * @param contractIdentifiers
     * @param columnName
     * @param orderDirection
     * @return
     * @throws SevereException
     */
    public List contractSort(List contractIdentifiers, String columnName,
            String orderDirection) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        List contractIds = getAsList(contractIdentifiers, 0);
        List dateSegIds = getAsList(contractIdentifiers, 1);
        referenceValueSet.put("contractIds", contractIds);
        referenceValueSet.put("dateSegIds", dateSegIds);

        if (SearchConstants.CONTRACT_EFFECTIVE_DATE_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByEffectiveDate", orderDirection);
        } else if (SearchConstants.CONTRACT_EXPIRY_DATE_ORDER
                .equals(columnName)) {
            referenceValueSet.put("orderByExpiryDate", orderDirection);
        } else if (SearchConstants.CONTRACT_STATUS_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByStatus", orderDirection);
        } else if (SearchConstants.CONTRACT_TYPE_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByType", orderDirection);
        } else if (SearchConstants.CONTRACT_TYPE_VERSION.equals(columnName)) {
            referenceValueSet.put("orderByVersion", orderDirection);
        } else if (SearchConstants.CONTRACT_ID_ORDER.equals(columnName)) {
            referenceValueSet.put("orderById", orderDirection);
        }
        SearchResults searchResult = sortIdentifiers(
                "com.wellpoint.wpd.common.search.result.ContractIdentifier",
                "medical", "sortContractIdentifiers", referenceValueSet);
        return searchResult.getSearchResults();

    }

    /**
     * 
     * @param productIdentifiers
     * @param columnName
     * @param orderDirection
     * @return
     * @throws SevereException
     */
    public List productStructureSort(List productIdentifiers,
            String columnName, String orderDirection) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("productStructureIds", productIdentifiers);
        if (SearchConstants.PRODUCT_STRUCTUES_EFFECTIVE_DATE_ORDER
                .equals(columnName)) {
            referenceValueSet.put("orderByEffectiveDate", orderDirection);
        } else if (SearchConstants.PRODUCT_STRUCTUES_EXPIRY_DATE_ORDER
                .equals(columnName)) {
            referenceValueSet.put("orderByExpiryDate", orderDirection);
        } else if (SearchConstants.PRODUCT_STRUCTUES_NAME_ORDER
                .equals(columnName)) {
            referenceValueSet.put("orderByName", orderDirection);
        } else if (SearchConstants.PRODUCT_STRUCTUES_STATUS_ORDER
                .equals(columnName)) {
            referenceValueSet.put("orderByStatus", orderDirection);
        } else if (SearchConstants.PRODUCT_STRUCTUES_VERSION_ORDER
                .equals(columnName)) {
            referenceValueSet.put("orderByVersion", orderDirection);
        }
        SearchResults searchResult = sortIdentifiers(
                "com.wellpoint.wpd.common.search.result.ProductStructureIdentifier",
                "medical", "sortProductStructureIdentifiers", referenceValueSet);
        return searchResult.getSearchResults();
    }

    /**
     * 
     * @param productIdentifiers
     * @param columnName
     * @param orderDirection
     * @return
     * @throws SevereException
     */
    public List benefitComponentSort(List productIdentifiers,
            String columnName, String orderDirection) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitComponentIds", productIdentifiers);
        if (SearchConstants.BENEFIT_COMPONENTS_EFFECTIVE_DATE_ORDER
                .equals(columnName)) {
            referenceValueSet.put("orderByEffectiveDate", orderDirection);
        } else if (SearchConstants.BENEFIT_COMPONENTS_EXPIRY_DATE_ORDER
                .equals(columnName)) {
            referenceValueSet.put("orderByExpiryDate", orderDirection);
        } else if (SearchConstants.BENEFIT_COMPONENTS_NAME_ORDER
                .equals(columnName)) {
            referenceValueSet.put("orderByName", orderDirection);
        } else if (SearchConstants.BENEFIT_COMPONENTS_STATUS_ORDER
                .equals(columnName)) {
            referenceValueSet.put("orderByStatus", orderDirection);
        } else if (SearchConstants.BENEFIT_COMPONENTS_VERSION_ORDER
                .equals(columnName)) {
            referenceValueSet.put("orderByVersion", orderDirection);
        }
        SearchResults searchResult = sortIdentifiers(
                "com.wellpoint.wpd.common.search.result.BenefitComponentIdentifier",
                "medical", "sortBenefitComponentIdentifiers", referenceValueSet);
        return searchResult.getSearchResults();
    }

    /**
     * 
     * @param productIdentifiers
     * @param columnName
     * @param orderDirection
     * @return
     * @throws SevereException
     */
    public List benefitSort(List productIdentifiers, String columnName,
            String orderDirection) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitIds", productIdentifiers);
        if (SearchConstants.BENEFIT_NAME_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByName", orderDirection);
        } else if (SearchConstants.BENEFIT_STATUS_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByStatus", orderDirection);
        } else if (SearchConstants.BENEFIT_VERSION_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByVersion", orderDirection);
        } else if (SearchConstants.BENEFIT_DESCRIPTION_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByDescription", orderDirection);
        }
        SearchResults searchResult = sortIdentifiers(
                "com.wellpoint.wpd.common.search.result.BenefitIdentifier",
                "medical", "sortBenefitIdentifiers", referenceValueSet);
        return searchResult.getSearchResults();
    }

    /**
     * 
     * @param benefitLevelIdentifiers
     * @param columnName
     * @param orderDirection
     * @return
     * @throws SevereException
     */
    public List benefitLevelSort(List benefitLevelIdentifiers,
            String columnName, String orderDirection) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        
        referenceValueSet.put("blvl_bnft_lvl_dummy", getCombinedQueryList(benefitLevelIdentifiers));
        if (SearchConstants.BENEFIT_LEVEL_DESCRIPTION_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByDescription", orderDirection);
        } else if (SearchConstants.BENEFIT_LEVEL_QUALIFIER_ORDER
                .equals(columnName)) {
            referenceValueSet.put("orderByQualifier", orderDirection);
        }
        SearchResults searchResult = sortIdentifiers(
                "com.wellpoint.wpd.common.search.result.BenefitLevelIdentifier",
                "medical", "sortBenefitLevelIdentifiers", referenceValueSet);
        return searchResult.getSearchResults();
    }
    /**
	 * @param benefitLevelIdentifiers
	 * @return
	 */
	private Object getCombinedQueryList(List benefitLevelIdentifiers) {
		StringBuffer buffer = new StringBuffer();
		
		Object[] objects = null;
		if(benefitLevelIdentifiers != null && benefitLevelIdentifiers.size() > 0){
			buffer.append("(");
			for(int i =0 ; i < benefitLevelIdentifiers.size() ; i++){
				objects = (Object[])benefitLevelIdentifiers.get(i);
				if(i > 0){
					buffer.append(",");
				}
				buffer.append( " (" + objects[0] + "," + objects[1] + ") " );
			}
			buffer.append(")");
		}		
		return buffer;
//		if(benefitLevelIdentifiers != null && benefitLevelIdentifiers.size() > 0){
//			for(int i =0 ; i < benefitLevelIdentifiers.size() ; i++){
//				objects = (Object[])benefitLevelIdentifiers.get(i);
//				list.add("("+objects[0]+","+objects[1]+")");
//			}
//		}
//		return list;
	}

	/**
     * 
     * @param benefitLineIdentifiers
     * @param columnName
     * @param orderDirection
     * @return
     * @throws SevereException
     */
    public List benefitLineSort(List benefitLineIdentifiers, String columnName,
            String orderDirection) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitLineIdentifiers", benefitLineIdentifiers);
        if (SearchConstants.BENEFIT_LEVEL_DESCRIPTION_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByDesc", orderDirection);
        } else if (SearchConstants.BENEFIT_LEVEL_QUALIFIER_ORDER
                .equals(columnName)) {
            referenceValueSet.put("orderByQualifier", orderDirection);
        } else if (SearchConstants.BENEFIT_LEVEL_PVA_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByPva", orderDirection);
        }
        SearchResults searchResult = sortIdentifiers(
                "com.wellpoint.wpd.common.search.result.BenefitLineIdentifier",
                "medical", "sortBenefitLineIdentifiers", referenceValueSet);
        return searchResult.getSearchResults();
    }

    /**
     * 
     * @param notesIdentifiers
     * @param columnName
     * @param orderDirection
     * @return
     * @throws SevereException
     */
    public List notesSort(List notesIdentifiers, String columnName,
            String orderDirection) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        List noteIds = getAsList(notesIdentifiers, 0);
        List versions = getAsList(notesIdentifiers, 1);
        referenceValueSet.put("noteIds", noteIds);
        referenceValueSet.put("versions", versions);
        if (SearchConstants.NOTE_NAME_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByName", orderDirection);
        } else if (SearchConstants.NOTE_VERSION_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByVersion", orderDirection);
        } else if (SearchConstants.NOTE_TEXT_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByText", orderDirection);
        } else if (SearchConstants.NOTE_STATUS_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByStatus", orderDirection);
        } else if (SearchConstants.NOTE_TYPE_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByNoteType", orderDirection);
        } else if (SearchConstants.NOTE_ID_ORDER.equals(columnName)) {
            referenceValueSet.put("orderByNoteId", orderDirection);
        }
        SearchResults searchResult = sortIdentifiers(
                "com.wellpoint.wpd.common.search.result.NotesIdentifier",
                "medical", "sortNotesIdentifiers", referenceValueSet);
        return searchResult.getSearchResults();
    }

    /**
     * @param identifiers
     * @return
     */
    private List getAsList(List identifiers, int index) {
        List list = new ArrayList();
        Object[] objects = null;
        for (int i = 0; i < identifiers.size(); i++) {
            objects = (Object[]) identifiers.get(i);
            list.add(objects[index]);
        }
        return list;
    }

    /**
     * 
     * @param businessObjectName
     * @param domain
     * @param queryName
     * @param referenceValueSet
     * @return SearchResults
     * @throws SevereException
     */
    private SearchResults searchForDetail(String businessObjectName,
            String domain, String queryName, HashMap referenceValueSet)
            throws SevereException {
        SearchResults searchResults = search(businessObjectName, domain,
                queryName, referenceValueSet, 1);

//        if (searchResults.getSearchResultCount() == 0) {
//
//            List dummy = new ArrayList();
//            dummy.add(new NotesDetail());
//            searchResults.setSearchResults(dummy);
//        }

        return searchResults;
    }

    /**
     * 
     * @param businessObjectName
     * @param domain
     * @param queryName
     * @param referenceValueSet
     * @return SearchResults
     * @throws SevereException
     */
    private SearchResults searchForTermDetail(String businessObjectName,
            String domain, String queryName, HashMap referenceValueSet)
            throws SevereException {
        SearchResults searchResults = search(businessObjectName, domain,
                queryName, referenceValueSet, 500);
        return searchResults;
    }

    /**
     * 
     * @param businessObjectName
     * @param domain
     * @param queryName
     * @param referenceValueSet
     * @return SearchResults
     * @throws SevereException
     */
    private SearchResults sortIdentifiers(String businessObjectName,
            String domain, String queryName, HashMap referenceValueSet)
            throws SevereException {
        SearchResults searchResults = sort(businessObjectName, domain,
                queryName, referenceValueSet);
        if (searchResults.getSearchResultCount() == 0) {
            List errorParams = new ArrayList();
            errorParams.add(businessObjectName);
            errorParams.add(domain);
            errorParams.add(queryName);
            throw new SevereException("Sort Failed", errorParams, null);
        }
        return searchResults;
    }

    /**
     * Retrieves Contracts associated to a Product.
     * 
     * @param pi
     * @return ContractObjectDetail List.
     * @throws SevereException
     */
    public SearchResults retrieveAssociationsForProduct(ProductIdentifier pi,
            int recordCountLimit) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("productId", new Integer(pi.getIdentifier()));
        return search(
                "com.wellpoint.wpd.common.search.result.ContractIdentifier",
                "medical", "searchContractIdentifiersWithProductIdentifier",
                referenceValueSet, recordCountLimit);
    }

    /**
     * Retrieves Products associated to a ProductStructure
     * 
     * @param psi
     * @return ProductObjectDetail List.
     * @throws SevereException
     */
    public SearchResults retrieveAssociationsForProductStructure(
            ProductStructureIdentifier psi, int recordCountLimit)
            throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("productStructureId", new Integer(psi
                .getIdentifier()));
        return search(
                "com.wellpoint.wpd.common.search.result.ProductIdentifier",
                "medical", "searchProductIdentifiersWithStructureIdentifier",
                referenceValueSet, recordCountLimit);
    }

    /**
     * Retrieves ProductStructures associated to a BenefitComponent.
     * 
     * @param bci
     * @return ProductStructureObjectDetail List.
     * @throws SevereException
     */
    public SearchResults retrieveAssociationForBenefitComponent(
            BenefitComponentIdentifier bci, int recordCountLimit)
            throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitComponentId", new Integer(bci
                .getIdentifier()));
        return search(
                "com.wellpoint.wpd.common.search.result.ProductStructureIdentifier",
                "medical",
                "searchProductStructureIdentifiersWithBenefitComponentIdentifier",
                referenceValueSet, recordCountLimit);
    }

    /**
     * Retrieves BenefitComponents associated to a Benefit.
     * 
     * @param bi
     * @return BenefitComponentObjectDetail List.
     * @throws SevereException
     */
    public SearchResults retrieveAssociationForBenefit(BenefitIdentifier bi,
            int recordCountLimit) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitId", new Integer(bi.getIdentifier()));
        return search(
                "com.wellpoint.wpd.common.search.result.BenefitComponentIdentifier",
                "medical",
                "searchBenefitComponentIdentifiersWithBenefitIdentifier",
                referenceValueSet, recordCountLimit);
    }

    /**
     * Retrieves Benefits associated to a BenefitLevel
     * 
     * @param bli
     * @return BenefitObjectDetail List.
     * @throws SevereException
     */
    public SearchResults retrieveAssociationForBenefitLevel(
            BenefitLevelIdentifier bli, int recordCountLimit)
            throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitLevelId",
                new Integer(bli.getIdentifier()));
        return search(
                "com.wellpoint.wpd.common.search.result.BenefitIdentifier",
                "medical",
                "searchBenefitIdentifiersWithBenefitLevelIdentifier",
                referenceValueSet, recordCountLimit);
    }

    /**
     * 
     * @param noteId
     * @param version
     * @param recordCountLimit
     * @return
     * @throws SevereException
     */
    public SearchResults getProductsForNote(String noteId, int version,
            int recordCountLimit) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("noteId", noteId);
        referenceValueSet.put("noteVersion", new Integer(version));
        return search(
                "com.wellpoint.wpd.common.search.result.ProductIdentifier",
                "medical", "searchProductIdentifiersWithNotesIdentifier",
                referenceValueSet, recordCountLimit);

    }

    /**
     * 
     * @param noteId
     * @param version
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getBenefitComponentForNote(String noteId, int version,
            int recordCountLimit) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("noteId", noteId);
        referenceValueSet.put("noteVersion", new Integer(version));
        return search(
                "com.wellpoint.wpd.common.search.result.BenefitComponentIdentifier",
                "medical",
                "searchBenefitComponentIdentifiersWithNotesIdentifier",
                referenceValueSet, recordCountLimit);

    }

    /**
     * 
     * @param noteId
     * @param version
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getContractForNote(String noteId, int version,
            int recordCountLimit) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("noteId", noteId);
        referenceValueSet.put("noteVersion", new Integer(version));
        return search(
                "com.wellpoint.wpd.common.search.result.ContractIdentifier",
                "medical", "searchContractIdentifiersWithNotesIdentifier",
                referenceValueSet, recordCountLimit);

    }

    /**
     * 
     * @param noteId
     * @param version
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getBenefitsForNote(String noteId, int version,
            int recordCountLimit) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("noteId", noteId);
        referenceValueSet.put("noteVersion", new Integer(version));
        return search(
                "com.wellpoint.wpd.common.search.result.BenefitIdentifier",
                "medical", "searchBenefitIdentifiersWithNotesIdentifier",
                referenceValueSet, recordCountLimit);

    }

    /**
     * 
     * @param noteId
     * @param version
     * @param recordCountLimit
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getBenefitLevelForNote(String noteId, int version,
            int recordCountLimit) throws SevereException {
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("noteId", noteId);
        referenceValueSet.put("noteVersion", new Integer(version));
        return search(
                "com.wellpoint.wpd.common.search.result.BenefitLineIdentifier",
                "medical", "searchBenefitLineIdentifiersWithNotesIdentifier",
                referenceValueSet, recordCountLimit);

    }

    /**
     * 
     * @param businessObjectName
     * @param domain
     * @param queryName
     * @param referenceValueSet
     * @param maxSearchResultSize
     * @return SearchResults
     * @throws SevereException
     */
    private SearchResults search(String businessObjectName, String domain,
            String queryName, HashMap referenceValueSet, int maxSearchResultSize)
            throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domain);
        searchCriteria.setSearchQueryName(queryName);
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria.setMaxSearchResultSize(maxSearchResultSize);
        try {
            SearchResults searchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
            return searchResults;
        } catch (AdapterException adapterException) {
            List errorParams = new ArrayList();
            errorParams.add(businessObjectName);
            errorParams.add(domain);
            errorParams.add(queryName);
            throw new SevereException("Adapter Exception occured", errorParams,
                    adapterException);
        }
    }

    /**
     * 
     * @param businessObjectName
     * @param domain
     * @param queryName
     * @param referenceValueSet
     * @return SearchResults
     * @throws SevereException
     */
    private SearchResults sort(String businessObjectName, String domain,
            String queryName, HashMap referenceValueSet) throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setBusinessObjectName(businessObjectName);
        searchCriteria.setSearchDomain(domain);
        searchCriteria.setSearchQueryName(queryName);
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria.setMaxSearchResultSize(500);
        try {
            SearchResults searchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
            return searchResults;
        } catch (AdapterException adapterException) {
        	Logger.logError(adapterException);
            List errorParams = new ArrayList();
            errorParams.add(businessObjectName);
            errorParams.add(domain);
            errorParams.add(queryName);
            throw new SevereException("Adapter Exception occured", errorParams,
                    adapterException);
        }
    }

    /**
     * 
     * @param benefitSysId
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getBenefitViewObject(int benefitSysId)
            throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.search.result.BenefitViewObject");
        searchCriteria.setSearchDomain("medical");
        searchCriteria.setSearchQueryName("viewBenefitDetails");
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitKey", new Integer(benefitSysId));
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria.setMaxSearchResultSize(500);
        try {
            SearchResults searchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
            return searchResults;
        } catch (AdapterException adapterException) {
        	Logger.logError(adapterException);
            List errorParams = new ArrayList();
            errorParams.add("");
            errorParams.add("");
            errorParams.add("");
            throw new SevereException("Adapter Exception occured", errorParams,
                    adapterException);
        }
    }

    /**
     * 
     * @param productKey
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getProductViewObject(int productKey)
            throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.search.result.ProductViewObject");
        searchCriteria.setSearchDomain("medical");
        searchCriteria.setSearchQueryName("viewProductDetails");
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("productKey", new Integer(productKey));
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria.setMaxSearchResultSize(500);
        try {
            SearchResults searchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
            return searchResults;
        } catch (AdapterException adapterException) {
        	Logger.logError(adapterException);
            List errorParams = new ArrayList();
            errorParams.add("");
            errorParams.add("");
            errorParams.add("");
            throw new SevereException("Adapter Exception occured", errorParams,
                    adapterException);
        }
    }

    /**
     * 
     * @param benefitComponentSysId
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getBenefitComponentViewObject(int benefitComponentSysId)
            throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.search.result.BenefitComponentViewObject");
        searchCriteria.setSearchDomain("medical");
        searchCriteria.setSearchQueryName("viewBenefitComponentDetails");
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitComponentKey", new Integer(
                benefitComponentSysId));
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria.setMaxSearchResultSize(500);
        try {
            SearchResults searchResults =  adapterServicesAccess
                    .searchObject(searchCriteria);
            return searchResults;
        } catch (AdapterException adapterException) {
        	Logger.logError(adapterException);
            List errorParams = new ArrayList();
            errorParams.add("");
            errorParams.add("");
            errorParams.add("");
            throw new SevereException("Adapter Exception occured", errorParams,
                    adapterException);
        }
    }

    /**
     * 
     * @param productStructureSysId
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getProductStructureViewObject(int productStructureSysId)
            throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.search.result.ProductStructureViewObject");
        searchCriteria.setSearchDomain("medical");
        searchCriteria.setSearchQueryName("viewProductStructureDetails");
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("productStructureId", new Integer(
                productStructureSysId));
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria.setMaxSearchResultSize(500);
        try {
            SearchResults searchResults =  adapterServicesAccess
                    .searchObject(searchCriteria);
            return searchResults;
        } catch (AdapterException adapterException) {
        	Logger.logError(adapterException);
            List errorParams = new ArrayList();
            errorParams.add("");
            errorParams.add("");
            errorParams.add("");
            throw new SevereException("Adapter Exception occured", errorParams,
                    adapterException);
        }
    }

    /**
     * 
     * @param contractKey
     * @param dataSegKey
     * @return SearchResults
     * @throws SevereException
     */
    public SearchResults getContractViewObject(int contractKey, int dataSegKey)
            throws SevereException {
        AdapterServicesAccess adapterServicesAccess = new AdapterServicesController();
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.search.result.ContractViewObject");
        searchCriteria.setSearchDomain("medical");
        searchCriteria.setSearchQueryName("viewContractDetails");
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("contractKey", new Integer(contractKey));
        referenceValueSet.put("dataSegKey", new Integer(dataSegKey));
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria.setMaxSearchResultSize(500);
        try {
            SearchResults searchResults =  adapterServicesAccess
                    .searchObject(searchCriteria);
            return searchResults;
        } catch (AdapterException adapterException) {
        	Logger.logError(adapterException);
            List errorParams = new ArrayList();
            errorParams.add("");
            errorParams.add("");
            errorParams.add("");
            throw new SevereException("Adapter Exception occured", errorParams,
                    adapterException);
        }
    }

}