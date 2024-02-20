/*
 * SequenceAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.common.adapter;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.sequence.bo.Sequence;

import java.util.HashMap;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SequenceAdapterManager {

   /**
    * This method returns next sequence for product key
    * @return
    * @throws SevereException
    */
	public int getNextProductRuleSequence()throws SevereException{
        return getNextSequence("productRuleSystemId");
	}
	
	public int getNextCntrctBnftCstmzdSysId()throws SevereException{
        return getNextSequence("cntrctBnftCstmzdSysId");
	}
	public int getNextContractSysIdSequence() throws SevereException{
        return getNextSequence("contractSysIdSeq");
    }
	public int getNextServTypeMappingSysId()throws SevereException{
        return getNextSequence("servTypeMappingSysId");
	}
    public int getNextProductSequence() throws SevereException{
        return getNextSequence("productSystemId");
    }
    
    public int getNextStandardBenefitSequence() throws SevereException{
        return getNextSequence("standardBenefitSystemId");
    }
    
    public int getNextSecurityRoleSequence() throws SevereException{
        return getNextSequence("securityRoleSystemId");
    }
    
    public int getNextRoleModuleAssociationSequence() throws SevereException{
        return getNextSequence("securityRoleModuleAssociationSysId");
    }
    
    public int getNextRoleTaskAssociationSequence() throws SevereException{
        return getNextSequence("securityRoleTaskAssociationSysId");
    }
      
    public int getNextSecuritySubTaskSequence() throws SevereException{
        return getNextSequence("securitySubTaskSystemId");
    }
    
    public int getNextBenefitAdministrationSequence() throws SevereException{
        return getNextSequence("benefitAdministrationSystemId");
    }
    
    public int getNextCatalogSequence() throws SevereException {
		return getNextSequence("catalogSystemId");
	}
    
    public int getNextSubCatalogSequence() throws SevereException{
        return getNextSequence("subCatalogSysId");
    }

    public int getNextProductStructureSequence() throws SevereException{
        return getNextSequence("productStructureSystemId");
    }
    
    public int getNextProductStructureBenefitComponentSequence() throws SevereException{
        return getNextSequence("productStructureBenefitCmpnt");
    }
    
    public int getNextBenefitDefinitionSequence() throws SevereException{
        return getNextSequence("benefitDefinitionMasterId");
    }
    
    public int getNextAdminOptionSequence() throws SevereException{
        return getNextSequence("adminOptionId");
    }
    
    public int getNextMandateSequence() throws SevereException{
        return getNextSequence("mandateId");
    }    

    public int getNextBenefitLevelSequence() throws SevereException{
        return getNextSequence("benefitLevelId");
    }
    
    public int getNextBenefitLineSequence() throws SevereException{
        return getNextSequence("benefitLineId");
    }
    
    public int getNextQuestionNumberSequence() throws SevereException{
        return getNextSequence("questionNbrSeq");
    }
    
    public int getNextAnswerIdSequence() throws SevereException{
        return getNextSequence("answerIdSeq");
    }

    public int getNextAdminAssoQuestionSequence() throws SevereException{
        return getNextSequence("adminAssoQuestion");
    }
    
    public int getNextNotesSequence() throws SevereException{
    	return getNextSequence("noteId");
    }
    
    public int getNextItemSequence() throws SevereException{
        return getNextSequence("itemSystemId");
    }
    
    public int getNextTaskSequence() throws SevereException{
        return getNextSequence("taskId");
    }
    
    public int getNextModuleSequence() throws SevereException{
        return getNextSequence("moduleSystemId");
    }
    
    public int getNextDomainIdSequence(AdapterServicesAccess adapterServicesAccess) throws SevereException{
        return getNextSequence("domainSystemId", adapterServicesAccess);
    }

    public int getNextBenefitSequence() throws SevereException{
        return getNextSequence("benefitLevelSeq");
    }
    public int getNextNonAdjBenefitMandateSequence() throws SevereException{
        return getNextSequence("nonAdjBenfitMandateId");
    }
    public int getNextBenefitAdminOptionSequence() throws SevereException{
        return getNextSequence("benefitAdminOptionId");
    }
    public int getNextOpenQuestionSequence() throws SevereException{
        return getNextSequence("openQuestionSequenceNo");
    }  
    public int getNextBenefitComponentSequence() throws SevereException{
        return getNextSequence("benefitComponentId");
    }
    public int getNextBenefitHierarchySequence() throws SevereException{
        return getNextSequence("benefitHierarchyId");
    }
    public int getNextBenefitHierarchyAssociationSequence() throws SevereException{
        return getNextSequence("benefitHierarchyAssociationId");
    }
    public int getNextContractIdSequence() throws SevereException{
        return getNextSequence("contractIdSeq");
    }
    public int getCurrentContractIdSequence() throws SevereException{
        return getNextSequence("contractIdCurrSeq");
    }
    public int getNextDtSegmentSysIdSequence() throws SevereException{
        return getNextSequence("dateSegmentSysIdSeq");
    }
    public int getNextCommentIdSequence() throws SevereException{
        return getNextSequence("commentIdSeq");
    }
    public int getNextReserevedContractSysIdSequence() throws SevereException{
        return getNextSequence("reserveContractSysIdSeq");
    }
    public int getMigratedContractSysIdSequence(AdapterServicesAccess adapterServicesAccess) throws SevereException{
        return getNextSequence("migratedContractSysIdSeq", adapterServicesAccess);
    }
    public int getMigratedDateSegSysIdSequence() throws SevereException{
        return getNextSequence("migratedDateSegSysIdSeq");
    }
    public int getMigratedProdStructureMappingSysID() throws SevereException{
        return getNextSequence("migratedProdStructureMappingSysID");
    }
    
    public int getNextMigratedNoteSequence() throws SevereException {
    	return getNextSequence("migratedNoteIdSequence");
    }
    public int getNextQuestionnaireHierarchySequence() throws SevereException {
    	return getNextSequence("questionnaireHierarchySequence");
    }
    
    public int getMasterTableRefIdIdSequence() throws SevereException{
        return getNextSequence("masterTableRefIdIdSequence");
    }
    public int getReserveContractPoolSysIdSequence() throws SevereException {
    	return getNextSequence("reserveContractPoolSysIdSequence");
    }
    public int getNextTestSuiteSequence() throws SevereException{
    	return getNextSequence("testSuiteId");
    }
    public int getNextTestCaseSequence() throws SevereException{
    	return getNextSequence("testCaseId");
    }
    public int getNextClaimLineSequence() throws SevereException{
    	return getNextSequence("claimLineId");
    }   
    public int getNextAdminMethodSysIDSequence() throws SevereException{
    	return getNextSequence("adminMethodSysID");
    }  
    public int getNextFilterCriteriaIDSequence() throws SevereException{
    	return getNextSequence("filterCriteria");
    } 
    
    
    public int getNextAdminMethodGroupIDSequence() throws SevereException{
    	return getNextSequence("adminMethodGroupID");
    } 
    
    
    
    
    public int getNextSequence(String searchQueryName) throws SevereException{
        HashMap criteriaMap = new HashMap();
        SearchResults searchResults = null;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(Sequence.class.getName(),searchQueryName,criteriaMap);
        
        searchResults = AdapterUtil.performSearch(searchCriteria);
        if(searchResults.getSearchResultCount() == 0)
            throw new ServiceException("Couldnt find next sequence Number for query "+ searchQueryName,null,null);
        Sequence sequence = (Sequence)searchResults.getSearchResults().get(0);
        return sequence.getNextSequenceNumber();
    }
    
    public int getNextSequence(String searchQueryName, AdapterServicesAccess adapterServicesAccess) throws SevereException{
        HashMap criteriaMap = new HashMap();
        SearchResults searchResults = null;
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(Sequence.class.getName(),searchQueryName,criteriaMap);
        
        searchResults = AdapterUtil.performSearch(searchCriteria,adapterServicesAccess);
        if(searchResults.getSearchResultCount() == 0)
            throw new ServiceException("Couldnt find next sequence Number for query "+ searchQueryName,null,null);
        Sequence sequence = (Sequence)searchResults.getSearchResults().get(0);
        return sequence.getNextSequenceNumber();
    }
}
