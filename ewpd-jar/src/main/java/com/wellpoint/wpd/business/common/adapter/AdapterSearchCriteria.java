/*
 * AdapterSearchCriteria.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */

package com.wellpoint.wpd.business.common.adapter;

import java.util.HashMap;
import java.util.List;

import com.wellpoint.adapter.access.SearchCriteria;

/**
 * This class implement SearchCriteria interfaces methods and through this class
 * can call adapter SearchService.
 *  
 */
public class AdapterSearchCriteria implements SearchCriteria {

    /**
     * The possible constant values for the search domain
     */
    String businessObjectName;

    int maxSearchResultSize;

    String referenceValueMatchCriteria;

    String referenceValueMatchPattern;

    String referenceValueRel;

    HashMap referenceValueSet;

    List resultSetOrderingAttbSet;

    String resultSetOrderingPattern;

    String searchDomain;

    String searchQueryName;

    /**
     * Returns the businessDomainObjectName.
     * @return String businessObjectName.
     */
    public String getBusinessObjectName() {
        return businessObjectName;
    }

    /**
     * Returns the maxSearchResultSize.
     * @return int maxSearchResultSize.
     */
    public int getMaxSearchResultSize() {
        return maxSearchResultSize;
    }

    /**
     * Returns the referenceValueMatchCriteria.
     * @return String referenceValueMatchCriteria.
     */
    public String getReferenceValueMatchCriteria() {
        return referenceValueMatchCriteria;
    }

    /**
     * Returns the referenceValueMatchPattern.
     * @return String referenceValueMatchPattern.
     */
    public String getReferenceValueMatchPattern() {
        return referenceValueMatchPattern;
    }

    /**
     * Returns the referenceValueRel.
     * @return String referenceValueRel.
     */
    public String getReferenceValueRel() {
        return referenceValueRel;
    }

    /**
     * Returns the referenceValueSet.
     * @return HashMap referenceValueSet.
     */
    public HashMap getReferenceValueSet() {
        return referenceValueSet;
    }

    /**
     * Returns the resultSetOrderingAttbSet.
     * @return List resultSetOrderingAttbSet.
     */
    public List getResultSetOrderingAttbSet() {
        return resultSetOrderingAttbSet;
    }

    /**
     * Returns the resultSetOrderingPattern.
     * @return String resultSetOrderingPattern.
     */
    public String getResultSetOrderingPattern() {
        return resultSetOrderingPattern;
    }

    /**
     * Returns the searchDomain.
     * @param String searchDomain
     */
    public String getSearchDomain() {
        return searchDomain;
    }

    /**
     * Sets the searchQueryName.
     * @param searchQueryName
     */
    public String getSearchQueryName() {
        return searchQueryName;
    }

    /**
     * Sets the businessObjectName.
     * @param businessObjectName
     */
    public void setBusinessObjectName(String businessObjectName) {
        this.businessObjectName = businessObjectName;
    }

    /**
     * Sets the maxSearchResultSize.
     * @param maxSearchResultSize
     */
    public void setMaxSearchResultSize(int maxSearchResultSize) {
        this.maxSearchResultSize = maxSearchResultSize;
    }

    /**
     * Sets the referenceValueMatchCriteria.
     * @param referenceValueMatchCriteria
     */
    public void setReferenceValueMatchCriteria(
            String referenceValueMatchCriteria) {
        this.referenceValueMatchCriteria = referenceValueMatchCriteria;
    }

    /**
     * Sets the referenceValueMatchPattern.
     * @param referenceValueMatchPattern
     */
    public void setReferenceValueMatchPattern(String referenceValueMatchPattern) {
        this.referenceValueMatchPattern = referenceValueMatchPattern;
    }

    /**
     * Sets the referenceValueRel.
     * @param referenceValueRel
     */
    public void setReferenceValueRel(String referenceValueRel) {
        this.referenceValueRel = referenceValueRel;
    }

    /**
     * Sets the referenceValueSet.
     * @param referenceValueSet
     */
    public void setReferenceValueSet(HashMap referenceValueSet) {
        this.referenceValueSet = referenceValueSet;
    }

    /**
     * Sets the resultSetOrderingAttbSet.
     * @param resultSetOrderingAttbSet
     */
    public void setResultSetOrderingAttbSet(List resultSetOrderingAttbSet) {
        this.resultSetOrderingAttbSet = resultSetOrderingAttbSet;
    }

    /**
     * Sets the resultSetOrderingPattern.
     * @param resultSetOrderingPattern
     */
    public void setResultSetOrderingPattern(String resultSetOrderingPattern) {
        this.resultSetOrderingPattern = resultSetOrderingPattern;
    }

    /**
     * Sets the searchDomain.
     * @param searchDomain
     */
    public void setSearchDomain(String searchDomain) {
        this.searchDomain = searchDomain;
    }

    /**
     * Sets the searchQueryName.
     * @param searchQueryName
     */
    public void setSearchQueryName(String searchQueryName) {
        this.searchQueryName = searchQueryName;
    }

    /**
     * Returns the boolean
     * @return boolean
     */
    public boolean validateSearchCriteria() {
        // TODO Auto-generated method stub
        return true;
    }
}