/*
 * SearchController.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.search.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.business.framework.bo.CommandFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.search.builder.SearchBuilder;
import com.wellpoint.wpd.business.search.command.Command;
import com.wellpoint.wpd.business.search.command.CommandProcessor;
import com.wellpoint.wpd.business.search.criteria.SearchCriteriaAnalyzer;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.search.criteria.AdvancedSearchCriteria;
import com.wellpoint.wpd.common.search.criteria.BasicSearchCriteria;
import com.wellpoint.wpd.common.search.exception.SearchCriteriaValidationException;
import com.wellpoint.wpd.common.search.result.BenefitComponentIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitLevelIdentifier;
import com.wellpoint.wpd.common.search.result.BenefitLineIdentifier;
import com.wellpoint.wpd.common.search.result.ContractIdentifier;
import com.wellpoint.wpd.common.search.result.NotesIdentifier;
import com.wellpoint.wpd.common.search.result.ObjectIdentifier;
import com.wellpoint.wpd.common.search.result.ProductIdentifier;
import com.wellpoint.wpd.common.search.result.ProductStructureIdentifier;
import com.wellpoint.wpd.common.search.result.SearchResult;
import com.wellpoint.wpd.common.search.result.SearchResultDetail;
import com.wellpoint.wpd.common.search.result.SearchResultSummary;
import com.wellpoint.wpd.common.search.util.SearchConstants;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SearchController.java 42077 2008-01-25 11:52:19Z u13547 $
 */
public class SearchController {

    /**
     * 
     * @param basicSearchCriteria
     * @return
     * @throws SearchCriteriaValidationException
     * @throws SevereException
     */
    public List basicSearch(BasicSearchCriteria basicSearchCriteria)
            throws SearchCriteriaValidationException, SevereException {
        SearchCriteriaAnalyzer analyzer = new SearchCriteriaAnalyzer();
        boolean criteriaTestSuccess = true;
        try {
            criteriaTestSuccess = analyzer.analyze(basicSearchCriteria);
        } catch (SearchCriteriaValidationException e) {
            throw e;
        }
        if (criteriaTestSuccess) {
            CommandFactory factory = (CommandFactory) ObjectFactory
                    .getFactory(ObjectFactory.COMMAND_FACTORY);
            Command[] commands = factory.getCommands(basicSearchCriteria);
            if (null != commands) {
                CommandProcessor processor = new CommandProcessor();
                processor.execute(commands);
            }
            return getAllSearchResults(commands);
        } else {
            List parameters = new ArrayList();
            parameters.add(basicSearchCriteria);
            throw new SevereException(
                    "search criteria string validation error", parameters, null);
        }
    }

    /**
     * @param commands
     * @return
     */
    private List getAllSearchResults(Command[] commands) {
        Command command = null;
        List searchResults = new ArrayList();
        for (int index = 0; index < commands.length; index++) {
            command = commands[index];
            SearchResult result = null;
            if (command.hasErrors()) {
                result = new SearchResultSummary();
                result.setExceptions(command.getExceptions());
                searchResults.add(result);
            } else {
                result = command.getSearchResults();
                if (result.getNumberOfResults() != 0) {
                    searchResults.add(command.getSearchResults());
                }
            }
        }
        return searchResults;
    }

    /**
     * @param commands
     * @return
     */
    private boolean hasResults(Command[] commands) {
        Command command = null;
        boolean results = false;
        for (int index = 0; index < commands.length; index++) {
            command = commands[index];
            SearchResult result = null;
            if (!command.hasErrors()) {
                result = command.getSearchResults();
                boolean flag = result.getResultCount() == 0;
                if (!flag) {
                    results = true;
                }
            }
        }
        return results;
    }

    /**
     * 
     * @param advancedSearchCriteria
     * @return
     * @throws SevereException
     */     
     
    public List advancedSearch(AdvancedSearchCriteria advancedSearchCriteria)
            throws SevereException {
        SearchCriteriaAnalyzer analyzer = new SearchCriteriaAnalyzer();
        boolean criteriaTestSuccess = true;
        try {
            criteriaTestSuccess = analyzer.analyze(advancedSearchCriteria);
        } catch (SearchCriteriaValidationException e) {
            throw e;
        }
        if (criteriaTestSuccess) {
            CommandFactory factory = (CommandFactory) ObjectFactory
                    .getFactory(ObjectFactory.COMMAND_FACTORY);
            Command[] commands = factory.getCommands(advancedSearchCriteria);
            if (null != commands) {
                CommandProcessor processor = new CommandProcessor();
                processor.execute(commands);
            }
            return getAllSearchResults(commands);
        } else {
            List parameters = new ArrayList();
            parameters.add(advancedSearchCriteria);
            throw new SevereException(
                    "search criteria string validation error", parameters, null);
        }
    }

    /**
     * 
     * @param objectIdentifiers
     * @return
     * @throws SevereException
     */
     
    public SearchResult retrieveObjects(List objectIdentifiers)
            throws SevereException {

        CommandFactory factory = (CommandFactory) ObjectFactory
                .getFactory(ObjectFactory.COMMAND_FACTORY);
        Command[] commands = factory.getCommands(objectIdentifiers);
        SearchResult result = null;

        if (null != commands && commands.length > 0) {
            CommandProcessor processor = new CommandProcessor();
            processor.execute(commands);

            Command command = commands[0];
            if (command.hasErrors()) {
                result = new SearchResultDetail();
                result.setExceptions(command.getExceptions());
            } else {
                result = command.getSearchResults();
            }
        }
        return result;
    }

    /**
     * Retrieves associations for a business object.
     * 
     * @param oi
     * @return
     */
    public SearchResult getAssociationForIdentifier(ObjectIdentifier oi,
            int resultCountLimit) throws SevereException {
        if (oi == null) {
            throw new IllegalArgumentException(
                    "getAssociationForIdentifier in SearchController.  Parameter oi is null");
        }
        SearchBuilder sb = new SearchBuilder();
        if (oi instanceof ProductIdentifier) {
            return sb.retrieveAssociationsForProduct((ProductIdentifier) oi,
                    resultCountLimit);
        }
        if (oi instanceof ProductStructureIdentifier) {
            return sb.retrieveAssociationsForProductStructure(
                    (ProductStructureIdentifier) oi, resultCountLimit);
        }
        if (oi instanceof BenefitComponentIdentifier) {
            return sb.retrieveAssociationForBenefitComponent(
                    (BenefitComponentIdentifier) oi, resultCountLimit);
        }
        if (oi instanceof BenefitIdentifier) {
            return sb.retrieveAssociationForBenefit((BenefitIdentifier) oi,
                    resultCountLimit);
        }
        if (oi instanceof BenefitLevelIdentifier) {
            return sb.retrieveAssociationForBenefitLevel(
                    (BenefitLevelIdentifier) oi, resultCountLimit);
        }
        if (oi instanceof NotesIdentifier) {
            return sb.retrieveAssociationForNote((NotesIdentifier) oi,
                    resultCountLimit);
        }
        return null;
    }

    /**
     * @param objectIdentifiers
     * @param objectType
     * @param fieldToSort
     * @param sortOrder
     * @return
     * @throws SevereException
     */
    public SearchResultSummary sort(List objectIdentifiers, String objectType,
            String fieldToSort, String sortOrder) throws SevereException {
        if (null == objectIdentifiers || objectIdentifiers.size() == 0) {
            throw new IllegalArgumentException(
                    "sort in Search Controller. Parameter identifiers is null or empty");
        }
        if (null == objectType || "".equals(objectType)) {
            throw new IllegalArgumentException(
                    "sort in Search Controller. Object Type passed is invalid");
        }
        if (null == fieldToSort || "".equals(fieldToSort)) {
            throw new IllegalArgumentException(
                    "sort in Search Controller. Field to Sort passed is invalid");
        }
        if (!(SearchConstants.ASCENDING.equals(sortOrder) || SearchConstants.DESCENDING
                .equals(sortOrder))) {
            throw new IllegalArgumentException(
                    "sort in Search Controller. sortOrder passed is invalid. Should be "
                            + SearchConstants.ASCENDING + " or "
                            + SearchConstants.DESCENDING);
        }

        SearchBuilder builder = new SearchBuilder();
        return (SearchResultSummary) builder.sort(
                getIdentifiersAlone(objectIdentifiers), fieldToSort, sortOrder,
                objectType);
    }
    /**
     * 
     * @param objectIdentifiers
     * @return
     */
    private List getIdentifiersAlone(List objectIdentifiers) {
        ObjectIdentifier identifier = null;
        Object[] objects = null;
        Iterator iterator = objectIdentifiers.iterator();
        List identifiers = new ArrayList();
        while (iterator.hasNext()) {
            identifier = (ObjectIdentifier) iterator.next();

            if (identifier instanceof ContractIdentifier) {
                objects = new Object[2];
                objects[0] = new Integer(((ContractIdentifier) identifier)
                        .getIdentifier());
                objects[1] = new Integer(((ContractIdentifier) identifier)
                        .getDateSegIdentifier());
                identifiers.add(objects);
            } else if (identifier instanceof ProductIdentifier) {
                identifiers.add(new Integer(((ProductIdentifier) identifier)
                        .getIdentifier()));
            } else if (identifier instanceof ProductStructureIdentifier) {
                identifiers.add(new Integer(
                        ((ProductStructureIdentifier) identifier)
                                .getIdentifier()));
            } else if (identifier instanceof BenefitComponentIdentifier) {
                identifiers.add(new Integer(
                        ((BenefitComponentIdentifier) identifier)
                                .getIdentifier()));
            } else if (identifier instanceof BenefitIdentifier) {
                identifiers.add(new Integer(((BenefitIdentifier) identifier)
                        .getIdentifier()));
            } else if (identifier instanceof BenefitLineIdentifier) {
                identifiers.add(new Integer(
                        ((BenefitLineIdentifier) identifier)
                                .getBenefitLineIdentifier()));
            } else if (identifier instanceof BenefitLevelIdentifier) {
            	objects = new Object[2];
            	objects[0] = new Integer(((BenefitLevelIdentifier) identifier).getIdentifier());
            	objects[1] = new Integer(((BenefitLevelIdentifier) identifier).getBenDefIdentifier());
                identifiers.add(objects);
            } else if (identifier instanceof NotesIdentifier) {
                objects = new Object[2];
                objects[0] = ((NotesIdentifier) identifier).getIdentifier();
                objects[1] = new Integer(((NotesIdentifier) identifier)
                        .getVersion());
                identifiers.add(objects);
            }
        }
        return identifiers;
    }

    /**
     * @param identifier
     * @param countLimit
     * @return
     */
    public List retrieveAttachments(NotesIdentifier identifier,
            List authModules, int countLimit) {
        CommandFactory factory = (CommandFactory) ObjectFactory
                .getFactory(ObjectFactory.COMMAND_FACTORY);
        Command[] commands = factory.getCommands(identifier, authModules);
        for (int i = 0; i < commands.length; i++) {
            commands[i].setResultCountLimit(countLimit);
        }
        if (null != commands) {
            CommandProcessor processor = new CommandProcessor();
            processor.execute(commands);
        }
        if (!hasResults(commands)) {
            return null;
        }
        return getAllSearchResults(commands);
    }
    /**
     * 
     * @param benefitSysId
     * @return
     */
    public SearchResult getBenefitViewDetails(int benefitSysId) {
        SearchBuilder searchBuilder = new SearchBuilder();
        return searchBuilder.getBenefitViewObject(benefitSysId);

    }
    /**
     * 
     * @param productKey
     * @return
     */
    public SearchResult getproductViewDetails(int productKey) {
        SearchBuilder searchBuilder = new SearchBuilder();
        return searchBuilder.getProductViewObject(productKey);

    }
    /**
     * 
     * @param benefitComponentSysId
     * @return
     */
    public SearchResult getBenefitComponentViewDetails(int benefitComponentSysId) {
        SearchBuilder searchBuilder = new SearchBuilder();
        return searchBuilder
                .getBenefitComponentViewObject(benefitComponentSysId);

    }
    /**
     * 
     * @param productStructureSysId
     * @return
     */
    public SearchResult getProductStructureViewDetails(int productStructureSysId) {
        SearchBuilder searchBuilder = new SearchBuilder();
        return searchBuilder
                .getProductStructureViewObject(productStructureSysId);

    }
    /**
     * 
     * @param contractKey
     * @param dataSegKey
     * @return
     */
    public SearchResult getContractViewDetails(int contractKey, int dataSegKey) {
        SearchBuilder searchBuilder = new SearchBuilder();
        return searchBuilder.getContractViewObject(contractKey, dataSegKey);

    }

}