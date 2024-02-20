/*
 * SearchBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.search.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.search.adapter.RetrieveAdapterManager;
import com.wellpoint.wpd.business.search.adapter.SearchAdapterManager;
import com.wellpoint.wpd.business.search.command.Command;
import com.wellpoint.wpd.business.search.command.DetailCommand;
import com.wellpoint.wpd.business.search.command.SummaryCommand;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.search.criteria.AdvancedAttribute;
import com.wellpoint.wpd.common.search.criteria.Attribute;
import com.wellpoint.wpd.common.search.criteria.LimitedTo;
import com.wellpoint.wpd.common.search.result.BenefitComponentDetail;
import com.wellpoint.wpd.common.search.result.BenefitComponentIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitComponentItemDetail;
import com.wellpoint.wpd.common.search.result.BenefitDetail;
import com.wellpoint.wpd.common.search.result.BenefitIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitItemDetail;
import com.wellpoint.wpd.common.search.result.BenefitLevelDetail;
import com.wellpoint.wpd.common.search.result.BenefitLevelIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitLevelItemDetail;
import com.wellpoint.wpd.common.search.result.BenefitLineDetail;
import com.wellpoint.wpd.common.search.result.BenefitLineIdentifier;
import com.wellpoint.wpd.common.search.result.ContractDetail;
import com.wellpoint.wpd.common.search.result.ContractDomainItemDetail;
import com.wellpoint.wpd.common.search.result.ContractIdentifier;
import com.wellpoint.wpd.common.search.result.ContractItemDetail;
import com.wellpoint.wpd.common.search.result.NoteItemDetail;
import com.wellpoint.wpd.common.search.result.NotesDetail;
import com.wellpoint.wpd.common.search.result.NotesDomainDetail;
import com.wellpoint.wpd.common.search.result.NotesIdentifier;
import com.wellpoint.wpd.common.search.result.ObjectIdentifier;
import com.wellpoint.wpd.common.search.result.ProductDetail;
import com.wellpoint.wpd.common.search.result.ProductIdentifier;
import com.wellpoint.wpd.common.search.result.ProductItemDetail;
import com.wellpoint.wpd.common.search.result.ProductStructureDetail;
import com.wellpoint.wpd.common.search.result.ProductStructureIdentifier;
import com.wellpoint.wpd.common.search.result.ProductStructureItemDetail;
import com.wellpoint.wpd.common.search.result.SearchResult;
import com.wellpoint.wpd.common.search.result.SearchResultDetail;
import com.wellpoint.wpd.common.search.result.SearchResultSummary;
import com.wellpoint.wpd.common.search.util.SearchConstants;
import com.wellpoint.wpd.web.search.util.SearchUtil;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SearchBuilder.java 53026 2009-06-22 05:39:25Z U17810 $
 */
public class SearchBuilder {
    /**
     * 
     * @param 
     * 
     */
    public SearchBuilder() {

    }
    /**
     * 
     * @param command
     * @return
     */
    public boolean search(Command command) {
        return false;
    }
    /**
     * 
     * @param command
     * @return
     * @throws SevereException
     */
    public SearchResult search(SummaryCommand command) throws SevereException {
        switch (command.getSearchType()) {
        case SearchConstants.BASIC_SEARCH:
            return basicSearch(command);

        case SearchConstants.ADVANCED_SEARCH:
            return advancedSearch(command);

        default:
            //TODO --> if we need any error handling
            break;
        }
        return null;
    }
   /**
    * 
    * @param command
    * @return
    * @throws SevereException
    */
    private SearchResult basicSearch(SummaryCommand command)
            throws SevereException {
        SearchAdapterManager manager = new SearchAdapterManager();
        SearchResultSummary resultSummary = new SearchResultSummary();
        String objectType = command.getObjectType();

        LimitedTo limitedTo = command.getLimitedTo();

        if ((null != limitedTo || command.getAttributes() != null)
                && command.getAttributes().size() == 1) {
        	String query = null;
        	Attribute attribute = (Attribute) command.getAttributes().get(0);
        	 if(attribute.getQuery()==null || "".equalsIgnoreCase(attribute.getQuery().trim())){
        	 	query = " COLUMN_NAME <> '-1' ";
        	 }else{	
        		query = attribute.getQuery();
        	 }
            if (SearchConstants.BENEFIT.equals(objectType)) {
                SearchResults sr = manager.benefitsBasicSearch(limitedTo,
                        query, command.getResultCountLimit());
                resultSummary.setObjectIdentifiers(sr.getSearchResults());
                resultSummary.setType(SearchConstants.BENEFIT);
                resultSummary.setResultCount(sr.getSearchResultCount());
                return resultSummary;
            } else if (SearchConstants.BENEFIT_COMPONENTS.equals(objectType)) {
                SearchResults sr = manager.benefitComponentsBasicSearch(
                        limitedTo, query, command.getResultCountLimit());
                resultSummary.setObjectIdentifiers(sr.getSearchResults());
                resultSummary.setType(SearchConstants.BENEFIT_COMPONENTS);
                resultSummary.setResultCount(sr.getSearchResultCount());
                return resultSummary;
            } else if (SearchConstants.BENEFIT_LEVEL.equals(objectType)) {
//            	List queryStrings = new ArrayList();
//            	for(int i=0;i<queries.size();i++){
//            		if(isOperator((String)queries.get(i))){
//            			queryStrings.add(queries.get(i));
//            		}else{
//            			 List termList = getBenefitRefItemDetails(
//                                SearchConstants.TERM_CATALOG_ID, (String)queries.get(i), 300);
//            			 String alternateQuery =  SearchUtil.createQueryStringForTerm(termList);
//            			 queryStrings.add(alternateQuery);
//            		}
//            	}
//
//                StringBuffer buffer = new StringBuffer();
//                for(int i=0;i<queryStrings.size();i++){
//                	buffer.append( queryStrings.get(i) );
//                }
                SearchResults sr = manager.benefitLevelsBasicSearch(limitedTo,
                        query, command.getResultCountLimit());
                resultSummary.setObjectIdentifiers(sr.getSearchResults());
                resultSummary.setType(SearchConstants.BENEFIT_LEVEL);
                resultSummary.setResultCount(sr.getSearchResultCount());
                return resultSummary;
            } else if (SearchConstants.CONTRACT.equals(objectType)) {
                SearchResults sr = manager.contractsBasicSearch(limitedTo,
                        query, command.getResultCountLimit());
                resultSummary.setObjectIdentifiers(sr.getSearchResults());
                resultSummary.setType(SearchConstants.CONTRACT);
                resultSummary.setResultCount(sr.getSearchResultCount());
                return resultSummary;
            } else if (SearchConstants.NOTES.equals(objectType)) {
                SearchResults sr = manager.notesBasicSearch(limitedTo, query,
                        command.getResultCountLimit());
                resultSummary.setObjectIdentifiers(sr.getSearchResults());
                resultSummary.setType(SearchConstants.NOTES);
                resultSummary.setResultCount(sr.getSearchResultCount());
                return resultSummary;
            } else if (SearchConstants.PRODUCT.equals(objectType)) {
                SearchResults sr = manager.productsBasicSearch(limitedTo,
                        query, command.getResultCountLimit());
                resultSummary.setObjectIdentifiers(sr.getSearchResults());
                resultSummary.setType(SearchConstants.PRODUCT);
                resultSummary.setResultCount(sr.getSearchResultCount());
                return resultSummary;
            } else if (SearchConstants.PRODUCT_STRUCTURES.equals(objectType)) {
                SearchResults sr = manager.productStructuresBasicSearch(
                        limitedTo, query, command.getResultCountLimit());
                resultSummary.setObjectIdentifiers(sr.getSearchResults());
                resultSummary.setType(SearchConstants.PRODUCT_STRUCTURES);
                resultSummary.setResultCount(sr.getSearchResultCount());
                return resultSummary;
            }
        	
        }
        return null;
    }
    /**
     * 
     * @param command
     * @return
     * @throws SevereException
     */
    private SearchResult advancedSearch(SummaryCommand command)
            throws SevereException {
    	boolean equalSign = false;boolean attribflag=true;
        SearchAdapterManager manager = new SearchAdapterManager();
        SearchResultSummary resultSummary = new SearchResultSummary();
        String objectType = command.getObjectType();
        Map specialWithEqualSign = new HashMap();
        LimitedTo limitedTo = command.getLimitedTo();
        if (null != limitedTo || command.getAttributes() != null) {
            List attributes = command.getAttributes();
            Map queryMap = new HashMap();
            
            for (int i = 0; i < attributes.size(); i++) {
                AdvancedAttribute advancedAttribute = (AdvancedAttribute) attributes
                        .get(i);

                if (advancedAttribute.isChecked()){
                	Object value = null;attribflag=false;
                    if(advancedAttribute.isSpecial()){
                    	value = advancedAttribute.getQueryList();
                    	if("=".equals(advancedAttribute.getSign())){
                    		specialWithEqualSign.put(StringUtil.lowerCaseFirstLetter(StringUtil
                                    .removeSpace(advancedAttribute.getName())),advancedAttribute);
                    	}
                    }else{
                    	value = advancedAttribute.getQuery();
                    }
                	queryMap.put(StringUtil.lowerCaseFirstLetter(StringUtil
                            .removeSpace(advancedAttribute.getName())),
                            value);
                }
            }
             if(attribflag)
              queryMap.put("invalidValue"," COLUMN_NAME <> '-1' " );
            if (SearchConstants.BENEFIT.equals(objectType)) {
                if (queryMap.get("term") != null) {
                    String searchWord = queryMap.get("term").toString();
                    List termList = getBenefitRefItemDetails(
                            SearchConstants.TERM_CATALOG_ID, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("term", SearchUtil.createQueryString(termList,
                            searchWord));
                    queryMap.put("emptyTerm","term");
                }
                if (queryMap.get("qualifier") != null) {
                    
                    List searchWords = (List)queryMap.get("qualifier");
                    equalSign = specialWithEqualSign.get("qualifier") == null ? false : true;
                    List searchWords1 = new ArrayList();
                    for(int i=0;i<searchWords.size();i++){
                    	String word = (String)searchWords.get(i);
                    	if(!isOperator(word)){
                            List termList = getBenefitRefItemDetails(
                                    SearchConstants.QUALIFIER_CATALOG_ID, word, 300);
                            String query = SearchUtil.createQueryStringForTerm(
                                    termList,equalSign);
                            searchWords1.add(query);
                    	}else{
                    		searchWords1.add(word);
                    	}
                    	
                    }
                    StringBuffer buffer = new StringBuffer();
                    for(int i=0;i<searchWords1.size();i++){
                    	buffer.append( searchWords1.get(i) );
                    }
                    queryMap.put("qualifier", buffer.toString());
                    queryMap.put("emptyQualifier","qualifier");
                    
                    
//                    String searchWord = queryMap.get("qualifier").toString();
//                    List qualifierList = getBenefitRefItemDetails(
//                            SearchConstants.QUALIFIER_CATALOG_ID, SearchUtil
//                                    .removeRelational(searchWord), 300);
//                    queryMap.put("qualifier", SearchUtil.createQueryString(
//                            qualifierList, searchWord));
//                    queryMap.put("emptyQualifier","qualifier");
                }
                if (queryMap.get("providerArrangement") != null) {
                    String searchWord = queryMap.get("providerArrangement")
                            .toString();
                    List providerList = getBenefitRefItemDetails(
                            SearchConstants.PROVIDER_ARNGEMNT_CATALOG_ID,
                            SearchUtil.removeRelational(searchWord), 300);
                    queryMap.put("providerArrangement", SearchUtil
                            .createQueryString(providerList, searchWord));
                    queryMap.put("emptyProviderArrangement","provider arrangement");
                    
                }
                if (queryMap.get("dataType") != null) {
                    String searchWord = queryMap.get("dataType").toString();
                    List dataTypeList = getBenefitRefItemDetails(
                            SearchConstants.DATA_TYPE_CATALOG_ID, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("dataType", SearchUtil.createQueryString(
                            dataTypeList, searchWord));
                    queryMap.put("emptyDataType","data type");
                }
                if (queryMap.get("jurisdiction") != null) {
                    String searchWord = queryMap.get("jurisdiction").toString();
                    List dataTypeList = getBenefitRefItemDetails(
                            SearchConstants.JURISDICTION, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("jurisdiction", SearchUtil.createQueryString(
                            dataTypeList, searchWord));
                }
                if (queryMap.get("benefitFundingArrangement") != null) {
                    String searchWord = queryMap.get(
                            "benefitFundingArrangement").toString();
                    List dataTypeList = getBenefitRefItemDetails(
                            SearchConstants.FUD_ARG, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("benefitFundingArrangement", SearchUtil
                            .createQueryString(dataTypeList, searchWord));
                }
                if (queryMap.get("effectiveness") != null) {
                    String searchWord = queryMap.get("effectiveness")
                            .toString();
                    List dataTypeList = getBenefitRefItemDetails(
                            SearchConstants.EFFEC_CODE, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("effectiveness", SearchUtil.createQueryString(
                            dataTypeList, searchWord));
                }
                if (queryMap.get("mandateCategory") != null) {
                    String searchWord = queryMap.get("mandateCategory")
                            .toString();
                    List dataTypeList = getBenefitRefItemDetails(
                            SearchConstants.MAN_CAT_CODE, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("mandateCategory", SearchUtil
                            .createQueryString(dataTypeList, searchWord));
                }
                SearchResults sr = manager.benefitsAdvancedSearch(limitedTo,
                        queryMap, command.getResultCountLimit());
                resultSummary.setObjectIdentifiers(sr.getSearchResults());
                resultSummary.setType(SearchConstants.BENEFIT);
                resultSummary.setResultCount(sr.getSearchResultCount());
                return resultSummary;
            } else if (SearchConstants.BENEFIT_COMPONENTS.equals(objectType)) {
                if (queryMap.get("state") != null) {
                    String searchWord = queryMap.get("state").toString();
                    List termList = getBenefitComponentRefItemDetails(
                            SearchConstants.STATE_CODE, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("state", SearchUtil.createQueryString(
                            termList, searchWord));
                }
                if (queryMap.get("mandateType") != null) {
                    String searchWord = queryMap.get("mandateType").toString();
                    List termList = getBenefitComponentRefItemDetails(
                            SearchConstants.MANDATE_TYPE, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("mandateType", SearchUtil.createQueryString(
                            termList, searchWord));
                }
                SearchResults sr = manager.benefitComponentsAdvancedSearch(
                        limitedTo, queryMap, command.getResultCountLimit());
                resultSummary.setObjectIdentifiers(sr.getSearchResults());
                resultSummary.setType(SearchConstants.BENEFIT_COMPONENTS);
                resultSummary.setResultCount(sr.getSearchResultCount());
                return resultSummary;
            } else if (SearchConstants.BENEFIT_LEVEL.equals(objectType)) {
                if (queryMap.get("term") != null) {
                    List searchWords = (List)queryMap.get("term");
                    equalSign = specialWithEqualSign.get("term") == null ? false : true;
                    List searchWords1 = new ArrayList();
                    for(int i=0;i<searchWords.size();i++){
                    	String word = (String)searchWords.get(i);
                    	if(!isOperator(word)){
                            List termList = getBenefitRefItemDetails(
                                    SearchConstants.TERM_CATALOG_ID, word, 300);
                            String query = SearchUtil.createQueryStringForTerm(
                                    termList,equalSign);
                            searchWords1.add(query);
                    	}else{
                    		searchWords1.add(word);
                    	}
                    	
                    }
                    StringBuffer buffer = new StringBuffer();
                    for(int i=0;i<searchWords1.size();i++){
                    	buffer.append( searchWords1.get(i) );
                    }
                    queryMap.put("term", buffer.toString());
                }
                if (queryMap.get("qualifier") != null) {
//                    String searchWord = queryMap.get("qualifier").toString();
//                    List qualifierList = getBenefitRefItemDetails(
//                            SearchConstants.QUALIFIER_CATALOG_ID, SearchUtil
//                                    .removeRelational(searchWord), 300);
//                    queryMap.put("qualifier", SearchUtil.createQueryString(
//                            qualifierList, searchWord));

                    List searchWords = (List)queryMap.get("qualifier");
                    equalSign = specialWithEqualSign.get("qualifier") == null ? false : true;
                    List searchWords1 = new ArrayList();
                    for(int i=0;i<searchWords.size();i++){
                    	String word = (String)searchWords.get(i);
                    	if(!isOperator(word)){
                            List termList = getBenefitRefItemDetails(
                            		SearchConstants.QUALIFIER_CATALOG_ID, word, 300);
                            String query = SearchUtil.createQueryStringForTerm(
                                    termList, equalSign);
                            searchWords1.add(query);
                    	}else{
                    		searchWords1.add(word);
                    	}
                    	
                    }
                    StringBuffer buffer = new StringBuffer();
                    for(int i=0;i<searchWords1.size();i++){
                    	buffer.append( searchWords1.get(i) );
                    }
                    queryMap.put("qualifier", buffer.toString());
                }
                if (queryMap.get("pVA") != null) {
                    String searchWord = queryMap.get("pVA").toString();
                    List qualifierList = getBenefitRefItemDetails(
                            SearchConstants.PROVIDER_ARNGEMNT_CATALOG_ID,
                            SearchUtil.removeRelational(searchWord), 300);
                    queryMap.put("pVA", SearchUtil.createQueryString(
                            qualifierList, searchWord));
                }
                if (queryMap.get("dataType") != null) {
                    String searchWord = queryMap.get("dataType").toString();
                    List dataTypeList = getBenefitRefItemDetails(
                            SearchConstants.DATA_TYPE_CATALOG_ID, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("dataType", SearchUtil.createQueryString(
                            dataTypeList, searchWord));
                }
                SearchResults sr = manager.benefitLevelsAdvancedSearch(
                        limitedTo, queryMap, command.getResultCountLimit());
                resultSummary.setObjectIdentifiers(sr.getSearchResults());
                resultSummary.setType(SearchConstants.BENEFIT_LEVEL);
                resultSummary.setResultCount(sr.getSearchResultCount());
                return resultSummary;
            } else if (SearchConstants.CONTRACT.equals(objectType)) {
                if (queryMap.get("groupSize") != null) {
                    String searchWord = queryMap.get("groupSize").toString();
                    List termList = getContractRefItemDetails(
                            SearchConstants.GROUP_SIZE_CATALOG_ID, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("groupSize", SearchUtil.createQueryString(
                            termList, searchWord));
                }
                if (queryMap.get("productCode") != null) {
                    String searchWord = queryMap.get("productCode").toString();
                    List termList = getContractRefItemDetails(
                            SearchConstants.PRODUCT_CD_CATALOG_ID, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("productCode", SearchUtil.createQueryString(
                            termList, searchWord));
                }
                if (queryMap.get("standardPlanCode") != null) {
                    String searchWord = queryMap.get("standardPlanCode")
                            .toString();
                    List termList = getContractRefItemDetails(
                            SearchConstants.STD_PLAN_CD_CATALOG_ID, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("standardPlanCode", SearchUtil
                            .createQueryString(termList, searchWord));
                }
                if (queryMap.get("productFamily") != null) {
                    String searchWord = queryMap.get("productFamily")
                            .toString();
                    List termList = getContractRefItemDetails(
                            SearchConstants.PROD_FAM_CATALOG_ID, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("productFamily", SearchUtil.createQueryString(
                            termList, searchWord));
                }
                if (queryMap.get("corporatePlanCode") != null) {
                    String searchWord = queryMap.get("corporatePlanCode")
                            .toString();
                    List termList = getContractRefItemDetails(
                            SearchConstants.CORP_PLAN_CD_CATALOG_ID, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("corporatePlanCode", SearchUtil
                            .createQueryString(termList, searchWord));
                }
                if (queryMap.get("contractType") != null) {
                    String searchWord = queryMap.get("contractType").toString();
                    List termList = getContractRefItemDetails(
                            SearchConstants.CON_TYP_CATALOG_ID, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("contractType", SearchUtil.createQueryString(
                            termList, searchWord));
                }
                if (queryMap.get("mandateType") != null) {
                    String searchWord = queryMap.get("mandateType").toString();
                    List termList = getContractRefItemDetails(
                            SearchConstants.MANDATE_TYPE, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("mandateType", SearchUtil.createQueryString(
                            termList, searchWord));
                }
                if (queryMap.get("headQuartersState") != null) {
                    String searchWord = queryMap.get("headQuartersState")
                            .toString();
                    List termList = getContractRefItemDetails(
                            SearchConstants.STATE_CODE, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("headQuartersState", SearchUtil
                            .createQueryString(termList, searchWord));
                }
                if (queryMap.get("fundingarrangementvalue") != null) {
                    String searchWord = queryMap.get("fundingarrangementvalue")
                            .toString();
                    List termList = getContractRefItemDetails(
                            SearchConstants.FUN_ARG_CATALOG_ID, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("fundingarrangementvalue", SearchUtil
                            .createQueryString(termList, searchWord));
                }
                if (queryMap.get("mBUvalue") != null) {
                    String searchWord = queryMap.get("mBUvalue").toString();
                    List termList = getContractRefItemDetails(
                            SearchConstants.MBU_CODE, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("mBUvalue", SearchUtil.createQueryString(
                            termList, searchWord));
                }
                if (queryMap.get("caseHeadQuarterState") != null) {
                    String searchWord = queryMap.get("caseHeadQuarterState")
                            .toString();
                    List termList = getContractRefItemDetails(
                            SearchConstants.STATE_CODE, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("caseHeadQuarterState", SearchUtil
                            .createQueryString(termList, searchWord));
                }
                //start changes for september release
                if (queryMap.get("regulatoryAgency") != null) {
                    String searchWord = queryMap.get("regulatoryAgency")
                            .toString();
                    List termList = getContractRefItemDetails(
                            SearchConstants.REGULATORY_AGENCY, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("regulatoryAgency", SearchUtil
                            .createQueryString(termList, searchWord));
                }
                if (queryMap.get("complianceStatus") != null) {
                    String searchWord = queryMap.get("complianceStatus")
                            .toString();
                    List termList = getContractRefItemDetails(
                            SearchConstants.COMPLIANCE_STTS, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("complianceStatus", SearchUtil
                            .createQueryString(termList, searchWord));
                }
                if (queryMap.get("projectNameCode") != null) {
                    String searchWord = queryMap.get("projectNameCode")
                            .toString();
                    List termList = getContractRefItemDetails(
                            SearchConstants.PROJECT_NAME_CODE, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("projectNameCode", SearchUtil
                            .createQueryString(termList, searchWord));
                }
              
                //end changes for september release
                SearchResults sr = manager.contractsAdvancedSearch(limitedTo,
                        queryMap, command.getResultCountLimit());
                resultSummary.setObjectIdentifiers(sr.getSearchResults());
                resultSummary.setType(SearchConstants.CONTRACT);
                resultSummary.setResultCount(sr.getSearchResultCount());
                return resultSummary;
            } else if (SearchConstants.NOTES.equals(objectType)) {
                if (queryMap.get("domainedProduct") != null) {
                    String searchWord = queryMap.get("domainedProduct")
                            .toString();
                    List termList = getNotesRefItemDetails("domainedProduct",
                            SearchUtil.removeRelational(searchWord), 300);
                    queryMap.put("domainedProduct", SearchUtil
                            .createQueryString(termList, searchWord));
                }
                if (queryMap.get("domainedBenefitComponent") != null) {
                    String searchWord = queryMap
                            .get("domainedBenefitComponent").toString();
                    List termList = getNotesRefItemDetails(
                            "domainedBenefitComponent", SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("domainedBenefitComponent", SearchUtil
                            .createQueryString(termList, searchWord));
                }
                if (queryMap.get("domainedBenefit") != null) {
                    String searchWord = queryMap.get("domainedBenefit")
                            .toString();
                    List termList = getNotesRefItemDetails("domainedBenefit",
                            SearchUtil.removeRelational(searchWord), 300);
                    queryMap.put("domainedBenefit", SearchUtil
                            .createQueryString(termList, searchWord));
                }
                if (queryMap.get("domainedQuestion") != null) {
                    String searchWord = queryMap.get("domainedQuestion")
                            .toString();
                    List termList = getNotesRefItemDetails("domainedQuestion",
                            SearchUtil.removeRelational(searchWord), 300);
                    queryMap.put("domainedQuestion", SearchUtil
                            .createQueryString(termList, searchWord));
                }
                if (queryMap.get("domainedTerm") != null) {
                    String searchWord = queryMap.get("domainedTerm").toString();
                    List termList = getNotesRefItemDetails("domainedTerm",
                            SearchUtil.removeRelational(searchWord), 300);
                    queryMap.put("domainedTerm", SearchUtil.createQueryString(
                            termList, searchWord));
                }
                if (queryMap.get("domainedQualifier") != null) {
                    String searchWord = queryMap.get("domainedQualifier")
                            .toString();
                    List termList = getNotesRefItemDetails("domainedQualifier",
                            SearchUtil.removeRelational(searchWord), 300);
                    queryMap.put("domainedQualifier", SearchUtil
                            .createQueryString(termList, searchWord));
                }
                if (queryMap.get("targetSystems") != null) {
                    String searchWord = queryMap.get("targetSystems")
                            .toString();
                    List termList = getNotesRefItemDetails("targetSystems",
                            SearchUtil.removeRelational(searchWord), 300);
                    queryMap.put("targetSystems", SearchUtil.createQueryString(
                            termList, searchWord));
                }
                if (queryMap.get("type") != null) {
                    String searchWord = queryMap.get("type").toString();
                    List termList = getNotesRefItemDetails("type", SearchUtil
                            .removeRelational(searchWord), 300);
                    queryMap.put("type", SearchUtil.createQueryString(termList,
                            searchWord));
                }
                SearchResults sr = manager.notesAdvancedSearch(limitedTo,
                        queryMap, command.getResultCountLimit());
                resultSummary.setObjectIdentifiers(sr.getSearchResults());
                resultSummary.setType(SearchConstants.NOTES);
                resultSummary.setResultCount(sr.getSearchResultCount());
                return resultSummary;
            } else if (SearchConstants.PRODUCT.equals(objectType)) {
                if (queryMap.get("state") != null) {
                    String searchWord = queryMap.get("state").toString();
                    List termList = getProductRefItemDetails(
                            SearchConstants.STATE_CODE, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("state", SearchUtil.createQueryString(
                            termList, searchWord));
                }
                if (queryMap.get("mandateType") != null) {
                    String searchWord = queryMap.get("mandateType").toString();
                    List termList = getProductRefItemDetails(
                            SearchConstants.MANDATE_TYPE, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("mandateType", SearchUtil.createQueryString(
                            termList, searchWord));
                }
                SearchResults sr = manager.productsAdvancedSearch(limitedTo,
                        queryMap, command.getResultCountLimit());
                resultSummary.setObjectIdentifiers(sr.getSearchResults());
                resultSummary.setType(SearchConstants.PRODUCT);
                resultSummary.setResultCount(sr.getSearchResultCount());
                return resultSummary;

            } else if (SearchConstants.PRODUCT_STRUCTURES.equals(objectType)) {
                if (queryMap.get("state") != null) {
                    String searchWord = queryMap.get("state").toString();
                    List termList = getProductStructureRefItemDetails(
                            SearchConstants.STATE_CODE, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("state", SearchUtil.createQueryString(
                            termList, searchWord));
                }
                if (queryMap.get("mandateType") != null) {
                    String searchWord = queryMap.get("mandateType").toString();
                    List termList = getProductStructureRefItemDetails(
                            SearchConstants.MANDATE_TYPE, SearchUtil
                                    .removeRelational(searchWord), 300);
                    queryMap.put("mandateType", SearchUtil.createQueryString(
                            termList, searchWord));
                }
                SearchResults sr = manager.productStructuresAdvancedSearch(
                        limitedTo, queryMap, command.getResultCountLimit());
                resultSummary.setObjectIdentifiers(sr.getSearchResults());
                resultSummary.setType(SearchConstants.PRODUCT_STRUCTURES);
                resultSummary.setResultCount(sr.getSearchResultCount());
                return resultSummary;
            }

        }
        return null;
    }
    /**
	 * @param word
	 * @return
	 */
	private boolean isOperator(String word) {
		if(null != word && !"".equals(word)){
			String word1 = word.trim();
			if("AND".equalsIgnoreCase(word1) || "OR".equalsIgnoreCase(word1) || "(".equals(word1) || ")".equals(word1)){
				return true;
			}
		}
		return false;
	}
	/**
     * 
     * @param command
     * @return
     */
    public boolean retrieve(Command command) {
        return false;
    }
    /**
     * 
     * @param command
     * @return
     * @throws SevereException
     */
    public SearchResult retrieve(DetailCommand command) throws SevereException {
        RetrieveAdapterManager manager = new RetrieveAdapterManager();
        String objectType = command.getObjectType();

        List objectIdentifiers = command.getObjectIdentifiers();

        SearchResultDetail resultDetail = new SearchResultDetail();

        Iterator iterator = objectIdentifiers.iterator();
        List objectDetails = new ArrayList();
        resultDetail.setObjectDetails(objectDetails);
        while (iterator.hasNext()) {
            ObjectIdentifier objectIdentifier = (ObjectIdentifier) iterator
                    .next();
            if (SearchConstants.BENEFIT.equals(objectType)) {
                BenefitDetail benefitDetail = manager
                        .benefitRetrieve((BenefitIdentifier) objectIdentifier);
                BenefitIdentifier id = new BenefitIdentifier();
                id.setIdentifier(((BenefitIdentifier) objectIdentifier)
                        .getIdentifier());
                id.setSystemId(((BenefitIdentifier) objectIdentifier)
                        .getSystemId());
                benefitDetail.setIdentifier(id);
                objectDetails.add(benefitDetail);
            } else if (SearchConstants.BENEFIT_COMPONENTS.equals(objectType)) {
                BenefitComponentDetail benefitComponentDetail = manager
                        .benefitComponentRetrieve((BenefitComponentIdentifier) objectIdentifier);
                BenefitComponentIdentifier id = new BenefitComponentIdentifier();
                id
                        .setIdentifier(((BenefitComponentIdentifier) objectIdentifier)
                                .getIdentifier());
                id.setSystemId(((BenefitComponentIdentifier) objectIdentifier)
                        .getSystemId());
                benefitComponentDetail.setIdentifier(id);
                benefitComponentDetail.setIdentifier(objectIdentifier);
                objectDetails.add(benefitComponentDetail);
            } else if (SearchConstants.BENEFIT_LEVEL.equals(objectType)) {
                BenefitLevelDetail benefitLevelDetail = manager
                        .benefitLevelRetrieve((BenefitLevelIdentifier) objectIdentifier);
                BenefitLevelIdentifier id = new BenefitLevelIdentifier();
                id.setIdentifier(((BenefitLevelIdentifier) objectIdentifier)
                        .getIdentifier());
                id.setSystemId(((BenefitLevelIdentifier) objectIdentifier)
                        .getSystemId());
                benefitLevelDetail.setIdentifier(id);
                objectDetails.add(benefitLevelDetail);
            } else if (SearchConstants.BENEFIT_LINE.equals(objectType)) {
                BenefitLineDetail benefitLevelDetail = manager
                        .benefitLineRetrieve((BenefitLineIdentifier) objectIdentifier);
                BenefitLineIdentifier id = new BenefitLineIdentifier();
                id.setIdentifier(((BenefitLineIdentifier) objectIdentifier)
                        .getIdentifier());
                id.setSystemId(((BenefitLineIdentifier) objectIdentifier)
                        .getSystemId());
                id
                        .setBenefitLineIdentifier(((BenefitLineIdentifier) objectIdentifier)
                                .getBenefitLineIdentifier());
                benefitLevelDetail.setIdentifier(id);
                objectDetails.add(benefitLevelDetail);
            } else if (SearchConstants.CONTRACT.equals(objectType)) {
                ContractDetail contractDetail = manager
                        .contractRetrieve((ContractIdentifier) objectIdentifier);
                ContractIdentifier id = new ContractIdentifier();
                id.setIdentifier(((ContractIdentifier) objectIdentifier)
                        .getIdentifier());
                id.setDateSegIdentifier(((ContractIdentifier) objectIdentifier)
                        .getDateSegIdentifier());
                id.setSystemId(((ContractIdentifier) objectIdentifier)
                        .getSystemId());
                contractDetail.setIdentifier(id);
                objectDetails.add(contractDetail);
            } else if (SearchConstants.NOTES.equals(objectType)) {
                NotesDetail noteDetail = manager
                        .notesRetrieve((NotesIdentifier) objectIdentifier);
                NotesIdentifier id = new NotesIdentifier();
                id.setIdentifier(((NotesIdentifier) objectIdentifier)
                        .getIdentifier());
                id.setSystemId(((NotesIdentifier) objectIdentifier)
                        .getSystemId());
                noteDetail.setIdentifier(id);
                objectDetails.add(noteDetail);
            } else if (SearchConstants.PRODUCT.equals(objectType)) {
                ProductDetail productDetail = manager
                        .productRetrieve((ProductIdentifier) objectIdentifier);
                ProductIdentifier id = new ProductIdentifier();
                id.setIdentifier(((ProductIdentifier) objectIdentifier)
                        .getIdentifier());
                id.setSystemId(((ProductIdentifier) objectIdentifier)
                        .getSystemId());
                productDetail.setIdentifier(id);
                objectDetails.add(productDetail);
            } else if (SearchConstants.PRODUCT_STRUCTURES.equals(objectType)) {
                ProductStructureDetail productStructureDetail = manager
                        .productStructureRetrieve((ProductStructureIdentifier) objectIdentifier);
                ProductStructureIdentifier id = new ProductStructureIdentifier();
                id
                        .setIdentifier(((ProductStructureIdentifier) objectIdentifier)
                                .getIdentifier());
                id.setSystemId(((ProductStructureIdentifier) objectIdentifier)
                        .getSystemId());
                productStructureDetail.setIdentifier(id);
                objectDetails.add(productStructureDetail);
            }
        }
        return resultDetail;
    }
    /**
     * 
     * @param objectIdentifiers
     * @param columnName
     * @param orderDirection
     * @param objectType
     * @return
     * @throws SevereException
     */
    public SearchResult sort(List objectIdentifiers, String columnName,
            String orderDirection, String objectType) throws SevereException {
        RetrieveAdapterManager manager = new RetrieveAdapterManager();
        SearchResultSummary resultSummary = new SearchResultSummary();

        if (SearchConstants.BENEFIT.equals(objectType)) {
            resultSummary.setObjectIdentifiers(manager.benefitSort(
                    objectIdentifiers, columnName, orderDirection));
        } else if (SearchConstants.BENEFIT_COMPONENTS.equals(objectType)) {
            resultSummary.setObjectIdentifiers(manager.benefitComponentSort(
                    objectIdentifiers, columnName, orderDirection));
        } else if (SearchConstants.BENEFIT_LEVEL.equals(objectType)) {
            resultSummary.setObjectIdentifiers(manager.benefitLevelSort(
                    objectIdentifiers, columnName, orderDirection));
        } else if (SearchConstants.BENEFIT_LINE.equals(objectType)) {
            resultSummary.setObjectIdentifiers(manager.benefitLineSort(
                    objectIdentifiers, columnName, orderDirection));
        } else if (SearchConstants.CONTRACT.equals(objectType)) {
            resultSummary.setObjectIdentifiers(manager.contractSort(
                    objectIdentifiers, columnName, orderDirection));
        } else if (SearchConstants.NOTES.equals(objectType)) {
            resultSummary.setObjectIdentifiers(manager.notesSort(
                    objectIdentifiers, columnName, orderDirection));
        } else if (SearchConstants.PRODUCT.equals(objectType)) {
            resultSummary.setObjectIdentifiers(manager.productSort(
                    objectIdentifiers, columnName, orderDirection));
        } else if (SearchConstants.PRODUCT_STRUCTURES.equals(objectType)) {
            resultSummary.setObjectIdentifiers(manager.productStructureSort(
                    objectIdentifiers, columnName, orderDirection));
        }
        resultSummary.setType(objectType);
        return resultSummary;
    }
    /**
     * 
     * @param command
     * @return
     * @throws SevereException
     */
    public SearchResult retrieveAttachmentForNotes(DetailCommand command)
            throws SevereException {
        SearchResults srs = null;
        List objectIdentifiers = command.getObjectIdentifiers();
        int recordCountLimit = command.getResultCountLimit();
        String objectType = command.getObjectType();
        if (null == objectIdentifiers || objectIdentifiers.size() == 0) {
            throw new SevereException(
                    "retrieveAttachmentForNotes in SearchBuilder. objectIdentifiers is null or empty",
                    null, null);
        }
        NotesIdentifier id = (NotesIdentifier) objectIdentifiers.get(0);
        if (SearchConstants.BENEFIT.equals(objectType)) {
            srs = new RetrieveAdapterManager().getBenefitsForNote(id
                    .getIdentifier(), id.getVersion(), recordCountLimit);
        } else if (SearchConstants.BENEFIT_COMPONENTS.equals(objectType)) {
            srs = new RetrieveAdapterManager().getBenefitComponentForNote(id
                    .getIdentifier(), id.getVersion(), recordCountLimit);
        } else if (SearchConstants.BENEFIT_LEVEL.equals(objectType)) {
            srs = new RetrieveAdapterManager().getBenefitLevelForNote(id
                    .getIdentifier(), id.getVersion(), recordCountLimit);
            objectType = SearchConstants.BENEFIT_LINE;
        } else if (SearchConstants.CONTRACT.equals(objectType)) {
            srs = new RetrieveAdapterManager().getContractForNote(id
                    .getIdentifier(), id.getVersion(), recordCountLimit);
        } else if (SearchConstants.PRODUCT.equals(objectType)) {
            srs = new RetrieveAdapterManager().getProductsForNote(id
                    .getIdentifier(), id.getVersion(), recordCountLimit);
        }
        return processResults(srs, objectType, id);
    }

    /**
     * Retrieves Contracts associated to a Product.
     * 
     * @param pi
     * @param recordCountLimit
     * @return SearchResultSummary that has a list of ContractIdentifiers.
     */
    public SearchResult retrieveAssociationsForProduct(ProductIdentifier pi,
            int recordCountLimit) throws SevereException {
        if (pi == null) {
            throw new IllegalArgumentException(
                    "retrieveAssociationsForProduct method in SearchBuilder.  Parameter pi is null");
        }
        SearchResults srs = new RetrieveAdapterManager()
                .retrieveAssociationsForProduct(pi, recordCountLimit);
        return processResults(srs, SearchConstants.CONTRACT, pi);
    }

    /**
     * Retrieves Products associated to a ProductStructure
     * 
     * @param psi
     * @return SearchResultSummary that has a list of ProductIdentifiers
     */
    public SearchResult retrieveAssociationsForProductStructure(
            ProductStructureIdentifier psi, int recordCountLimit)
            throws SevereException {
        if (psi == null) {
            throw new IllegalArgumentException(
                    "retrieveAssociationsForProductStructure in SearchBuilder.  Parameter psi is null");
        }
        SearchResults srs = new RetrieveAdapterManager()
                .retrieveAssociationsForProductStructure(psi, recordCountLimit);
        return processResults(srs, SearchConstants.PRODUCT, psi);
    }

    /**
     * Retrieves ProductStructures associated to a BenefitComponent.
     * 
     * @param bci
     * @return SearchResultSummary that has a list of
     *         ProductStructureIdentifiers
     */
    public SearchResult retrieveAssociationForBenefitComponent(
            BenefitComponentIdentifier bci, int recordCountLimit)
            throws SevereException {
        if (bci == null) {
            throw new IllegalArgumentException(
                    "retrieveAssociationForBenefitComponent in SearchBuilder.  Parameter bci is null");
        }
        SearchResults srs = new RetrieveAdapterManager()
                .retrieveAssociationForBenefitComponent(bci, recordCountLimit);
        return processResults(srs, SearchConstants.PRODUCT_STRUCTURES, bci);
    }

    /**
     * Retrieves BenefitComponents associated to a Benefit.
     * 
     * @param bi
     * @return SearchResultSummary that has a list of
     *         BenefitComponentIdentifiers
     */
    public SearchResult retrieveAssociationForBenefit(BenefitIdentifier bi,
            int recordCountLimit) throws SevereException {
        if (bi == null) {
            throw new IllegalArgumentException(
                    "retrieveAssociationForBenefit in SearchBuilder.  Parameter bi is null");
        }
        SearchResults srs = new RetrieveAdapterManager()
                .retrieveAssociationForBenefit(bi, recordCountLimit);
        return processResults(srs, SearchConstants.BENEFIT_COMPONENTS, bi);
    }

    /**
     * Retrieves Benefits associated to a BenefitLevel
     * 
     * @param bli
     * @return SearchResultSummary that has a list of BenefitIdentifiers.
     */
    public SearchResult retrieveAssociationForBenefitLevel(
            BenefitLevelIdentifier bli, int recordCountLimit)
            throws SevereException {
        if (bli == null) {
            throw new IllegalArgumentException(
                    "retrieveAssociationForBenefitLevel in SearchBuilder.  Parameter bli is null");
        }
        SearchResults srs = new RetrieveAdapterManager()
                .retrieveAssociationForBenefitLevel(bli, recordCountLimit);
        return processResults(srs, SearchConstants.BENEFIT, bli);
    }

    /**
     * 
     * @param sr
     * @param type
     * @param oi
     * @return
     * @throws SevereException
     */
    protected SearchResult processResults(SearchResults sr, String type,
            ObjectIdentifier oi) throws SevereException {
        if (sr == null) {
            List l = new ArrayList();
            l.add(oi);
            throw new SevereException(
                    "Problem executing search in Adapter.  SearchResults is null",
                    l, null);
        }
        SearchResultSummary srs = new SearchResultSummary();
        srs.setType(type);
        srs.setObjectIdentifiers(sr.getSearchResults());
        srs.setResultCount(sr.getSearchResultCount());
        return srs;
    }
    /**
     * 
     * @param catalogId
     * @param description
     * @param recordCountLimit
     * @return
     * @throws SevereException
     */
    private List getBenefitRefItemDetails(int catalogId, String description,
            int recordCountLimit) throws SevereException {
        SearchAdapterManager manager = new SearchAdapterManager();
        SearchResults searchResults;
        if (catalogId != -1)
            searchResults = manager.getBenefitRefItemDetails(catalogId,
                    description, recordCountLimit);
        else
            searchResults = manager.getBenefitDataTypeDetails(description,
                    recordCountLimit);
        List itemList = new ArrayList();
        List searchList = searchResults.getSearchResults();
        for (int resultCnt = 0; resultCnt < searchList.size(); resultCnt++) {
            BenefitItemDetail benefitItemDetail = (BenefitItemDetail) searchList
                    .get(resultCnt);
            itemList.add(benefitItemDetail.getCode());
        }
        return itemList;
    }
    /**
     * 
     * @param type
     * @param description
     * @param recordCountLimit
     * @return
     * @throws SevereException
     */
    private List getNotesRefItemDetails(String type, String description,
            int recordCountLimit) throws SevereException {
        SearchAdapterManager manager = new SearchAdapterManager();
        SearchResults searchResults = null;
        if ("domainedProduct".equalsIgnoreCase(type)) {
            searchResults = manager.getNotesRefItemDetails("searchProductItem",
                    description, recordCountLimit);
        } else if ("domainedBenefitComponent".equalsIgnoreCase(type)) {
            searchResults = manager
                    .getNotesRefItemDetails("searchBenefitComponentItem",
                            description, recordCountLimit);
        } else if ("domainedBenefit".equalsIgnoreCase(type)) {
            searchResults = manager.getNotesRefItemDetails("searchBenefitItem",
                    description, recordCountLimit);
        }else if ("domainedQuestion".equalsIgnoreCase(type)) {
            searchResults = manager.getNotesRefItemDetails("searchQuestionItem",
                    description, recordCountLimit);
        }  
        else if ("domainedTerm".equalsIgnoreCase(type)) {
            searchResults = manager.getNotesRefItemDetails(
                    SearchConstants.TERM_CATALOG_ID, description,
                    recordCountLimit);
        } else if ("domainedQualifier".equalsIgnoreCase(type)) {
            searchResults = manager.getNotesRefItemDetails(
                    SearchConstants.QUALIFIER_CATALOG_ID, description,
                    recordCountLimit);
        } else if ("targetSystems".equalsIgnoreCase(type)) {
            searchResults = manager.getNotesRefItemDetails(
                    SearchConstants.TARGET_SYSTEM_CATALOG_ID, description,
                    recordCountLimit);
        } else if ("type".equalsIgnoreCase(type)) {
            searchResults = manager.getNotesRefItemDetails(
                    SearchConstants.NOTE_TYPE_CATALOG_ID, description,
                    recordCountLimit);
        }
        List itemList = new ArrayList();
        List searchList = searchResults.getSearchResults();
        if ("domainedTerm".equalsIgnoreCase(type)
                || "domainedQualifier".equalsIgnoreCase(type)
                || "targetSystems".equalsIgnoreCase(type)
                || "type".equalsIgnoreCase(type)) {
            for (int resultCnt = 0; resultCnt < searchList.size(); resultCnt++) {
                NoteItemDetail noteItemDetail = (NoteItemDetail) searchList
                        .get(resultCnt);
                itemList.add(String.valueOf(noteItemDetail.getCode()));
            }
            return itemList;
        }
        for (int resultCnt = 0; resultCnt < searchList.size(); resultCnt++) {
            NotesDomainDetail notesDomainDetail = (NotesDomainDetail) searchList
                    .get(resultCnt);
            itemList.add(String.valueOf(notesDomainDetail.getCode()));
        }
        return itemList;

    }
    /**
     * 
     * @param catalogId
     * @param description
     * @param recordCountLimit
     * @return
     * @throws SevereException
     */
    private List getContractRefItemDetails(int catalogId, String description,
            int recordCountLimit) throws SevereException {
        SearchAdapterManager manager = new SearchAdapterManager();
        SearchResults searchResults;
        if (catalogId == 1937)
            searchResults = manager.getContractDomainItemDetails(catalogId,
                    description, recordCountLimit);
        else
            searchResults = manager.getContractRefItemDetails(catalogId,
                    description, recordCountLimit);
        List itemList = new ArrayList();
        List searchList = searchResults.getSearchResults();
        if (catalogId == 1937) {
            for (int resultCnt = 0; resultCnt < searchList.size(); resultCnt++) {

                ContractDomainItemDetail contractDomainItemDetail = (ContractDomainItemDetail) searchList
                        .get(resultCnt);
                itemList
                        .add(String.valueOf(contractDomainItemDetail.getCode()));
            }

        } else {
            for (int resultCnt = 0; resultCnt < searchList.size(); resultCnt++) {

                ContractItemDetail contractItemDetail = (ContractItemDetail) searchList
                        .get(resultCnt);
                itemList.add(contractItemDetail.getCode());
            }
        }
        return itemList;
    }
    /**
     * 
     * @param catalogId
     * @param description
     * @param recordCountLimit
     * @return
     * @throws SevereException
     */
    private List getProductRefItemDetails(int catalogId, String description,
            int recordCountLimit) throws SevereException {
        SearchAdapterManager manager = new SearchAdapterManager();
        SearchResults searchResults;
        searchResults = manager.getProductRefItemDetails(catalogId,
                description, recordCountLimit);
        List itemList = new ArrayList();
        List searchList = searchResults.getSearchResults();

        for (int resultCnt = 0; resultCnt < searchList.size(); resultCnt++) {

            ProductItemDetail productItemDetail = (ProductItemDetail) searchList
                    .get(resultCnt);
            itemList.add(productItemDetail.getCode());
        }

        return itemList;
    }
    /**
     * 
     * @param catalogId
     * @param description
     * @param recordCountLimit
     * @return
     * @throws SevereException
     */
    private List getProductStructureRefItemDetails(int catalogId,
            String description, int recordCountLimit) throws SevereException {
        SearchAdapterManager manager = new SearchAdapterManager();
        SearchResults searchResults;
        searchResults = manager.getProductStructureRefItemDetails(catalogId,
                description, recordCountLimit);
        List itemList = new ArrayList();
        List searchList = searchResults.getSearchResults();

        for (int resultCnt = 0; resultCnt < searchList.size(); resultCnt++) {

            ProductStructureItemDetail productStructureItemDetail = (ProductStructureItemDetail) searchList
                    .get(resultCnt);
            itemList.add(productStructureItemDetail.getCode());
        }

        return itemList;
    }
    /**
     * 
     * @param catalogId
     * @param description
     * @param recordCountLimit
     * @return
     * @throws SevereException
     */
    private List getBenefitComponentRefItemDetails(int catalogId,
            String description, int recordCountLimit) throws SevereException {
        SearchAdapterManager manager = new SearchAdapterManager();
        SearchResults searchResults;
        searchResults = manager.getBenefitComponentRefItemDetails(catalogId,
                description, recordCountLimit);
        List itemList = new ArrayList();
        List searchList = searchResults.getSearchResults();

        for (int resultCnt = 0; resultCnt < searchList.size(); resultCnt++) {

            BenefitComponentItemDetail benefitComponentItemDetail = (BenefitComponentItemDetail) searchList
                    .get(resultCnt);
            itemList.add(benefitComponentItemDetail.getCode());
        }

        return itemList;
    }
    /**
     * 
     * @param catalogId
     * @param description
     * @param recordCountLimit
     * @return
     * @throws SevereException
     */
    private List getBenefitLevelRefItemDetails(int catalogId,
            String description, int recordCountLimit) throws SevereException {
        SearchAdapterManager manager = new SearchAdapterManager();
        SearchResults searchResults;
        searchResults = manager.getBenefitLevelRefItemDetails(catalogId,
                description, recordCountLimit);
        List itemList = new ArrayList();
        List searchList = searchResults.getSearchResults();

        for (int resultCnt = 0; resultCnt < searchList.size(); resultCnt++) {

            BenefitLevelItemDetail benefitLevelItemDetail = (BenefitLevelItemDetail) searchList
                    .get(resultCnt);
            itemList.add(benefitLevelItemDetail.getCode());
        }

        return itemList;
    }
    /**
     * 
     * @param benefitSysId
     * @return
     */
    public SearchResult getBenefitViewObject(int benefitSysId) {
        RetrieveAdapterManager adapterManager = new RetrieveAdapterManager();
        List viewList = null;
        try {
            SearchResults results = adapterManager
                    .getBenefitViewObject(benefitSysId);
            viewList = results.getSearchResults();

        } catch (Exception e) {

        }
        SearchResultDetail resultDetail = new SearchResultDetail();
        resultDetail.setObjectDetails(viewList);
        return resultDetail;

    }
    /**
     * 
     * @param productKey
     * @return
     */
    public SearchResult getProductViewObject(int productKey) {
        RetrieveAdapterManager adapterManager = new RetrieveAdapterManager();
        List viewList = null;
        try {
            SearchResults results = adapterManager
                    .getProductViewObject(productKey);
            viewList = results.getSearchResults();

        } catch (Exception e) {

        }
        SearchResultDetail resultDetail = new SearchResultDetail();
        resultDetail.setObjectDetails(viewList);
        return resultDetail;

    }
    /**
     * 
     * @param benefitComponentSysId
     * @return
     */
    public SearchResult getBenefitComponentViewObject(int benefitComponentSysId) {
        RetrieveAdapterManager adapterManager = new RetrieveAdapterManager();
        List viewList = null;
        try {
            SearchResults results = adapterManager
                    .getBenefitComponentViewObject(benefitComponentSysId);
            viewList = results.getSearchResults();

        } catch (Exception e) {

        }
        SearchResultDetail resultDetail = new SearchResultDetail();
        resultDetail.setObjectDetails(viewList);
        return resultDetail;

    }
    /**
     * 
     * @param productStructureSysId
     * @return
     */
    public SearchResult getProductStructureViewObject(int productStructureSysId) {
        RetrieveAdapterManager adapterManager = new RetrieveAdapterManager();
        List viewList = null;
        try {
            SearchResults results = adapterManager
                    .getProductStructureViewObject(productStructureSysId);
            viewList = results.getSearchResults();

        } catch (Exception e) {

        }
        SearchResultDetail resultDetail = new SearchResultDetail();
        resultDetail.setObjectDetails(viewList);
        return resultDetail;

    }
    /**
     * 
     * @param benefitSysId
     * @param dataSegKey
     * @return
     */
    public SearchResult getContractViewObject(int benefitSysId, int dataSegKey) {
        RetrieveAdapterManager adapterManager = new RetrieveAdapterManager();
        List viewList = null;
        try {
            SearchResults results = adapterManager.getContractViewObject(
                    benefitSysId, dataSegKey);
            viewList = results.getSearchResults();

        } catch (Exception e) {

        }
        SearchResultDetail resultDetail = new SearchResultDetail();
        resultDetail.setObjectDetails(viewList);
        return resultDetail;

    }

    /**
     * 
     * @param identifier
     * @return
     * @throws SevereException
     */

    public List getLineOfBusiness(ObjectIdentifier identifier)
            throws SevereException {
        List lob = new ArrayList();
        if (identifier instanceof BenefitIdentifier) {
            BenefitIdentifier benefitIdentifier = (BenefitIdentifier) identifier;
            lob = DomainUtil.getLineOfBusiness("stdbenefit", benefitIdentifier
                    .getIdentifier());
        } else if (identifier instanceof ProductIdentifier) {
            ProductIdentifier productIdentifier = (ProductIdentifier) identifier;
            lob = DomainUtil.getLineOfBusiness("product", productIdentifier
                    .getIdentifier());
        } else if (identifier instanceof ProductStructureIdentifier) {
            ProductStructureIdentifier productStructureIdentifier = (ProductStructureIdentifier) identifier;
            lob = DomainUtil.getLineOfBusiness("ProdStructure",
                    productStructureIdentifier.getIdentifier());
        } else if (identifier instanceof BenefitComponentIdentifier) {
            BenefitComponentIdentifier benefitComponentIdentifier = (BenefitComponentIdentifier) identifier;
            lob = DomainUtil.getLineOfBusiness("benefitcomp",
                    benefitComponentIdentifier.getIdentifier());
        } else if (identifier instanceof ContractIdentifier) {
            ContractIdentifier contractIdentifier = (ContractIdentifier) identifier;
            lob = DomainUtil.getLineOfBusiness("contract", contractIdentifier
                    .getIdentifier());
        }
        return lob;
    }
    /**
     * 
     * @param identifier
     * @return
     * @throws SevereException
     */
    public List getBusinessEntity(ObjectIdentifier identifier)
            throws SevereException {
        List be = new ArrayList();
        if (identifier instanceof BenefitIdentifier) {
            BenefitIdentifier benefitIdentifier = (BenefitIdentifier) identifier;
            be = DomainUtil.getBusinessEntity("stdbenefit", benefitIdentifier
                    .getIdentifier());
        } else if (identifier instanceof ProductIdentifier) {
            ProductIdentifier productIdentifier = (ProductIdentifier) identifier;
            be = DomainUtil.getBusinessEntity("product", productIdentifier
                    .getIdentifier());
        } else if (identifier instanceof ProductStructureIdentifier) {
            ProductStructureIdentifier productStructureIdentifier = (ProductStructureIdentifier) identifier;
            be = DomainUtil.getBusinessEntity("ProdStructure",
                    productStructureIdentifier.getIdentifier());
        } else if (identifier instanceof BenefitComponentIdentifier) {
            BenefitComponentIdentifier benefitComponentIdentifier = (BenefitComponentIdentifier) identifier;
            be = DomainUtil.getBusinessEntity("benefitcomp",
                    benefitComponentIdentifier.getIdentifier());
        } else if (identifier instanceof ContractIdentifier) {
            ContractIdentifier contractIdentifier = (ContractIdentifier) identifier;
            be = DomainUtil.getBusinessEntity("contract", contractIdentifier
                    .getIdentifier());
        }
        return be;
    }
    /**
     * 
     * @param identifier
     * @return
     * @throws SevereException
     */
    public List getBusinessGroup(ObjectIdentifier identifier)
            throws SevereException {
        List bg = new ArrayList();
        if (identifier instanceof BenefitIdentifier) {
            BenefitIdentifier benefitIdentifier = (BenefitIdentifier) identifier;
            bg = DomainUtil.getBusinessGroup("stdbenefit", benefitIdentifier
                    .getIdentifier());
        } else if (identifier instanceof ProductIdentifier) {
            ProductIdentifier productIdentifier = (ProductIdentifier) identifier;
            bg = DomainUtil.getBusinessGroup("product", productIdentifier
                    .getIdentifier());
        } else if (identifier instanceof ProductStructureIdentifier) {
            ProductStructureIdentifier productStructureIdentifier = (ProductStructureIdentifier) identifier;
            bg = DomainUtil.getBusinessGroup("ProdStructure",
                    productStructureIdentifier.getIdentifier());
        } else if (identifier instanceof BenefitComponentIdentifier) {
            BenefitComponentIdentifier benefitComponentIdentifier = (BenefitComponentIdentifier) identifier;
            bg = DomainUtil.getBusinessGroup("benefitcomp",
                    benefitComponentIdentifier.getIdentifier());
        } else if (identifier instanceof ContractIdentifier) {
            ContractIdentifier contractIdentifier = (ContractIdentifier) identifier;
            bg = DomainUtil.getBusinessGroup("contract", contractIdentifier
                    .getIdentifier());
        }
        return bg;
    }
    /**
     * @param identifier
     * @param resultCountLimit
     * @return
     */
    public SearchResult retrieveAssociationForNote(NotesIdentifier identifier, int resultCountLimit) {
        // TODO Auto-generated method stub
        return null;
    }

}