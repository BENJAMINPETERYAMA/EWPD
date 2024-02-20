/*
 * Created on Mar 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.benefitlevel.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.BusinessTransactionContext;
import com.wellpoint.adapter.access.BusinessTransactionContextImpl;
import com.wellpoint.adapter.access.SearchCriteria;
import com.wellpoint.adapter.access.SearchCriteriaImpl;
import com.wellpoint.adapter.access.SearchResults;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLevelLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLevelTermLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLineLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenfitLevelLineDeleteLocateCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelPopUpBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitLineBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitQualifierBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitTermBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitWrapperBO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.util.StringUtil;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.popup.bo.PopupFilterBO;
import com.wellpoint.wpd.common.standardbenefit.bo.AdminOptionBenefitLevelBO;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * @author u12322
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class BenefitlevelAdapterManager {

    public final String USER_NAME = BusinessConstants.TESTUSER;

    public final String CREATE = WebConstants.CREATE_STRUCTURE;

    public final String EDIT = WebConstants.EDIT_STRUCTURE;

    public final String DELETE = WebConstants.DELETE_STRUCTURE;

    public final String EWPDB_DOMAIN = "ewpd";

    private AdapterServicesAccess getAdapterService(){
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory.getAdapterServicesAccess("ewpd");
        return adapterServicesAccess;
    }

    /**
     * @param questionBO
     * @param audit
     * @throws SevereException
     */
    // **Change**
    //public boolean persistBenefit(BenefitWrapperBO benefitWrapperBO,String
    // user,boolean insertFlag) throws SevereException {
    public boolean persistBenefit(BenefitWrapperBO benefitWrapperBO,
            Audit audit, boolean insertFlag, AdapterServicesAccess adapterServicesAccess) 
    		throws SevereException, AdapterException {
        // **End**

    	TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23057 : Save Bnefit Coverage", "BenefitLevelAdapterManager","persistBenefit"));
        List benefitLevels = benefitWrapperBO.getBenefitLevelsList();
      
            if (null != benefitLevels) {
                for (int i = 0; i < benefitLevels.size(); i++) {
                    BenefitLevelBO benefitLevelBO = (BenefitLevelBO) benefitLevels
                            .get(i);
                    benefitLevelBO.setBenefitDefinitionId(benefitWrapperBO
                            .getBenefitDefinitionId());
                    // **Chnage**  // 
                    List benefitTerm = benefitLevelBO.getBenefitTerms();
                    String termCodes = "";
                    if (null != benefitTerm) {
                        for (int k = 0; k < benefitTerm.size(); k++) {
                            BenefitTermBO benefitTermBO = (BenefitTermBO) benefitTerm
                                    .get(k);
                            termCodes = termCodes
                                    + benefitTermBO.getBenefitTermCode();
                            if (k < (benefitTerm.size() - 1)) {
                                termCodes = termCodes + ",";
                            }
                        }
                    }
                    benefitLevelBO.setBenefitTermCode(termCodes);
                    
                    //** Change 11/15/07
                    List benefitQualifiers = benefitLevelBO.getBenefitQualifiers();
                    String qualifierCodes = "";
                    if(null != benefitQualifiers){
                    	for(int l=0; l<benefitQualifiers.size();l++){
                    		BenefitQualifierBO benefitQualBO = (BenefitQualifierBO)benefitQualifiers.get(l);
                    		qualifierCodes = qualifierCodes + benefitQualBO.getBenefitQualifierCode();
                    		if (l < (benefitQualifiers.size() - 1)) {
                    			qualifierCodes = qualifierCodes + ",";
                            }
                    	}
                    		
                    }
                    benefitLevelBO.setBenefitQualifierCode(qualifierCodes);
                    //**End 11/15/07
                    if(insertFlag || benefitLevelBO.isModified()){
                    	this.persistBenefitLevel(benefitLevelBO, audit,
                            adapterServicesAccess, insertFlag);
                    }
                    // **End**
                    List benefitLines = benefitLevelBO.getBenefitLines();
                    if (null != benefitLines) {
                        for (int j = 0; j < benefitLines.size(); j++) {
                            BenefitLineBO benefitLineBO = (BenefitLineBO) benefitLines
                                    .get(j);
                            // **Change**
                            if(insertFlag || benefitLineBO.isModified()) {
                            	this.persistBenefitLine(benefitLineBO,
                                    benefitLevelBO, audit,
                                    adapterServicesAccess, insertFlag);
                            }
                            // **End**
                        }
                    }
                    // **Change**
                    /*
                     * List benefitTerms = benefitLevelBO.getBenefitTerms();
                     * if(null != benefitTerms){ for(int k = 0; k <
                     * benefitTerms.size(); k++){ BenefitTermBO benefitTermBO =
                     * (BenefitTermBO)benefitTerms.get(k);
                     * this.persistBenefitTerm(benefitTermBO,benefitLevelBO,
                     * audit, adapterServicesAccess,insertFlag); } }
                     */
                    // **End**
                }
            }
           Logger.logInfo(th.endPerfLogging()); 
        
        return true;

    }
    
    /**
     * @param benefitWrapperBO
     * @param audit
     * @param adapterServicesAccess
     * @throws AdapterException
     * @throws SevereException
     */
    public boolean insertBenefitLevels(BenefitWrapperBO benefitWrapperBO,
            Audit audit,AdapterServicesAccess adapterServicesAccess) 
    		throws SevereException, AdapterException {

    	List termList = new ArrayList();
    	List qualifierList = new ArrayList();
    	List pvaList = new ArrayList();   
    	String terms ="";
    	String qualifiers ="";
    	String pvas ="";   
    	boolean termCombined = false;
    	boolean qualCombined = false;
    	BenefitLine benefitLine = new BenefitLine();        
        benefitLine.setBenefitDefinitionId(benefitWrapperBO.getBenefitDefinitionId());
        
        List benefitLevels = benefitWrapperBO.getBenefitLevelsList();      
            if (null != benefitLevels) {
                for (int i = 0; i < benefitLevels.size(); i++) {
                    BenefitLevelBO benefitLevelBO = (BenefitLevelBO) benefitLevels
                            .get(i);
                    benefitLevelBO.setBenefitDefinitionId(benefitWrapperBO
                            .getBenefitDefinitionId());                    
                    List benefitTerm = benefitLevelBO.getBenefitTerms();                                        	
                    String termCodes = "";
                    if (null != benefitTerm) {
                        for (int k = 0; k < benefitTerm.size(); k++) {
                            BenefitTermBO benefitTermBO = (BenefitTermBO) benefitTerm
                                    .get(k);
                            termCodes = termCodes
                                    + benefitTermBO.getBenefitTermCode();
                            if (k < (benefitTerm.size() - 1)) {
                            	termCombined =true;
                                termCodes = termCodes + ",";
                            }
                        }
                    }
                    benefitLevelBO.setBenefitTermCode(termCodes);
                    termList.add(termCodes);
                    
                    List benefitQualifiers = benefitLevelBO.getBenefitQualifiers();
                    String qualifierCodes = "";
                    if(null != benefitQualifiers){
                    	for(int l=0; l<benefitQualifiers.size();l++){
                    		BenefitQualifierBO benefitQualBO = (BenefitQualifierBO)benefitQualifiers.get(l);
                    		qualifierCodes = qualifierCodes + benefitQualBO.getBenefitQualifierCode();
                    		if (l < (benefitQualifiers.size() - 1)) {
                    			qualCombined = true;
                    			qualifierCodes = qualifierCodes + ",";
                            }
                    	}
                    		
                    }
                    benefitLevelBO.setBenefitQualifierCode(qualifierCodes);
                    qualifierList.add(qualifierCodes);
                    List benefitLines = benefitLevelBO.getBenefitLines();
                    String pvaCodes = "";
                    if (null != benefitLines) { 
                        for (int j = 0; j < benefitLines.size(); j++) {
                            BenefitLineBO benefitLineBO = (BenefitLineBO) benefitLines
                                    .get(j);
                            pvaCodes = benefitLineBO.getPvaCode();
                            pvaList.add(pvaCodes);
                        }
                    }
                }
            }        
			Set termSet = new TreeSet(termList);	
			Set qualSet = new TreeSet(qualifierList);				
			Set pvaSet = new TreeSet(pvaList);	
            Iterator iterator = termSet.iterator();
            Iterator iterator1 = qualSet.iterator();
            Iterator iterator2 = pvaSet.iterator();
            while(iterator.hasNext()){            	            	
            	terms = terms+String.valueOf(iterator.next())+"~";
            }		
            while(iterator1.hasNext()){            	
            	qualifiers = qualifiers+String.valueOf(iterator1.next())+"~";
            }			            
            while(iterator2.hasNext()){            	
            	pvas = pvas+String.valueOf(iterator2.next())+"~";
            }
            terms = terms.substring(0,(terms.length()-1));
            qualifiers = qualifiers.substring(0,(qualifiers.length()-1));
            pvas = pvas.substring(0,(pvas.length()-1));    
            if(qualifiers.equals(""))
            	qualifiers=null;
            
            SearchResults benefitSearchResults = null;            
            HashMap referenceValueSet = new HashMap();
            referenceValueSet.put("benefitDefinitionId", new Integer(benefitLine.getBenefitDefinitionId()));
            referenceValueSet.put("terms",terms);
            referenceValueSet.put("qualifiers",qualifiers);
            referenceValueSet.put("pvas",pvas);
            referenceValueSet.put("user",audit.getUser());
            referenceValueSet.put("combinedTerm",String.valueOf(termCombined));
            referenceValueSet.put("combinedQual",String.valueOf(qualCombined));
            SearchCriteria searchCriteria = new SearchCriteriaImpl();
            searchCriteria.setReferenceValueSet(referenceValueSet);
            searchCriteria
                    .setBusinessObjectName("com.wellpoint.wpd.common.override.benefit.bo.BenefitLine");

            searchCriteria.setSearchQueryName("persistBenefitLevel");
            searchCriteria.setMaxSearchResultSize(99999);
            searchCriteria.setSearchDomain("medical");
            try {
                benefitSearchResults = adapterServicesAccess
                        .searchObject(searchCriteria);
            } catch (AdapterException adapterException) {
                logAdapterException(null, adapterException);
            }
        return true;

    }


    /**
     * @param benefitLineBO
     * @param user
     * @param adapterServicesAccess
     * @throws SevereException
     */
    public void persistBenefitLine(BenefitLineBO benefitLineBO,
            BenefitLevelBO benefitLevelBO, Audit audit,
            AdapterServicesAccess adapterServicesAccess, boolean insertFlag)
            throws SevereException {
        try {
        	TimeHandler th = new TimeHandler();
        	Logger.logInfo(th.startPerfLogging("U23057 :  save benefit coverage", "BenefitLevelAdapterManager", "persistBenefitLevel"));
            SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
            BusinessTransactionContext btc = new BusinessTransactionContextImpl();
            btc.setBusinessTransactionUser(this.USER_NAME);
            if (insertFlag) {
                btc.setBusinessTransactionType(this.CREATE);
                benefitLineBO.setBenefitLevelId(benefitLevelBO
                        .getBenefitLevelId());
                benefitLineBO.setBenefitLineId(sequenceAdapterManager
                        .getNextBenefitLineSequence());
                benefitLineBO.setBenefitDefenitionId(benefitLevelBO.getBenefitDefinitionId());
                       	
                // **Change**
                benefitLineBO.setCreatedUser(audit.getUser());
                benefitLineBO.setCreatedTimestamp(audit.getTime());
                benefitLineBO.setLastUpdatedUser(audit.getUser());
                benefitLineBO.setLastUpdatedTimestamp(audit.getTime());
                //**End**
                adapterServicesAccess
                        .persistObject(
                                benefitLineBO,
                                "com.wellpoint.wpd.common.benefitlevel.bo.BenefitLineBO",
                                btc);
            } else {
                btc.setBusinessTransactionType(this.EDIT);
                // **Change**
                benefitLineBO.setLastUpdatedUser(audit.getUser());
                benefitLineBO.setLastUpdatedTimestamp(audit.getTime());
                //modified to include def id on 12th Dec 2007
                benefitLineBO.setBenefitDefenitionId(benefitLevelBO.getBenefitDefinitionId());
                // **End**
                //change for performance
                if(benefitLineBO.isModified()){
                	HashMap map=new HashMap();
                	map.put("spsType","LINE");
                	map.put("term",benefitLevelBO.getBenefitTermCode());
                	if(benefitLevelBO.getBenefitQualifierCode() != null && !"".equalsIgnoreCase(benefitLevelBO.getBenefitQualifierCode()))
                	map.put("qualifier",benefitLevelBO.getBenefitQualifierCode());
                	else
                	map.put("qualifier","null");
                	
                	map.put("pva",benefitLineBO.getPvaCode());
                	if(benefitLineBO.getDataTypeId() != 0)
                	map.put("dataType",new Integer(benefitLineBO.getDataTypeId()));
                	SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
            				PopupFilterBO.class.getName(),
            				"searchSPSForMapping1", map);
            		SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
                	if(searchResults != null && searchResults.getSearchResults()!=null ){
                		if(searchResults.getSearchResults().size() == 1){
                			PopupFilterBO popupFilterBO= (PopupFilterBO)searchResults.getSearchResults().get(0);
                			benefitLineBO.setReference(popupFilterBO.getDescription());
                			benefitLineBO.setReferenceCode(popupFilterBO.getSpsId());
                			
                		}else if(searchResults.getSearchResults().size() > 1){
                			boolean sel=false;
                			for(int i=0;i<searchResults.getSearchResults().size();i++){
                				PopupFilterBO popupFilterBO= (PopupFilterBO)searchResults.getSearchResults().get(i);
                				if(popupFilterBO.getDescription().equalsIgnoreCase(benefitLineBO.getReference())){
                					sel=true;break;
                				}
                			}
                			if(!sel){
                    			benefitLineBO.setReference(null);
                    			benefitLineBO.setReferenceCode(null);
                			}else{
                    			benefitLineBO.setReferenceCode(benefitLineBO.getReferenceCode());
                			}
                		}else{
                			benefitLineBO.setReferenceCode(benefitLineBO.getReferenceCode());
                		}
                			
                	}
            		
                	
                //end
	                adapterServicesAccess
	                        .persistObject(
	                                benefitLineBO,
	                                "com.wellpoint.wpd.common.benefitlevel.bo.BenefitLineBO",
	                                btc);
                }
            }

            Logger.logInfo(th.endPerfLogging());
        } catch (AdapterException adapterException) {
            logAdapterException(benefitLineBO, adapterException);
        }
        
    }


    // **Change**
    /**
     * @param benefitLineBO,BenefitTermBO
     * @param user
     * @param adapterServicesAccess
     * @throws SevereException
     */
    public void persistBenefitTerm(BenefitTermBO benefitTermBO,
            BenefitLevelBO benefitLevelBO, Audit audit,
            AdapterServicesAccess adapterServicesAccess, boolean insertFlag)
            throws SevereException {
        try {
            BusinessTransactionContext btc = new BusinessTransactionContextImpl();
            btc.setBusinessTransactionUser(this.USER_NAME);
            if (insertFlag) {
                if (null != benefitLevelBO.getBenefitTerm()) {
                    btc.setBusinessTransactionType(this.CREATE);
                    benefitTermBO.setBenefitLevelId(benefitLevelBO
                            .getBenefitLevelId());
                    adapterServicesAccess
                            .persistObject(
                                    benefitTermBO,
                                    "com.wellpoint.wpd.common.benefitlevel.bo.BenefitTermBO",
                                    btc);
                }
            } else {
                btc.setBusinessTransactionType(this.EDIT);
                adapterServicesAccess
                        .persistObject(
                                benefitTermBO,
                                "com.wellpoint.wpd.common.benefitlevel.bo.BenefitTermBO",
                                btc);
            }

        } catch (AdapterException adapterException) {
            logAdapterException(benefitTermBO, adapterException);
        }
    }


    // **End**
    /**
     * @param benefitLevelBO
     * @param audit
     * @param adapterServicesAccess
     * @throws SevereException
     */
    public boolean persistBenefitLevel(BenefitLevelBO benefitLevelBO,
            Audit audit, AdapterServicesAccess adapterServicesAccess,
            boolean insertFlag) throws SevereException {
    	TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23057 : Save Benefit Coverage", "BenefitLevelAdapterManager", "persistBenefitLevel"));
        try {
            SequenceAdapterManager sequenceAdapterManager = new SequenceAdapterManager();
            BusinessTransactionContext btc = new BusinessTransactionContextImpl();
            btc.setBusinessTransactionUser(this.USER_NAME);
            if (insertFlag) {
                if (null != benefitLevelBO.getBenefitTerm()) {

                    btc.setBusinessTransactionType(this.CREATE);
                    benefitLevelBO.setBenefitLevelId(sequenceAdapterManager
                            .getNextBenefitLevelSequence());
                    benefitLevelBO
                            .setBenefitLevelSeq(getNextSequenceForBenefitLevel(benefitLevelBO
                                    .getBenefitDefinitionId()));
                    // **Change**
                    benefitLevelBO.setCreatedUser(audit.getUser());
                    benefitLevelBO.setCreatedTimestamp(audit.getTime());
                    benefitLevelBO.setLastUpdatedUser(audit.getUser());
                    benefitLevelBO.setLastUpdatedTimestamp(audit.getTime());
                    // **End**
                    adapterServicesAccess
                            .persistObject(
                                    benefitLevelBO,
                                    "com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelBO",
                                    btc);
                }
            } else {
                btc.setBusinessTransactionType(this.EDIT);
                // **Change**
                benefitLevelBO.setLastUpdatedUser(audit.getUser());
                benefitLevelBO.setLastUpdatedTimestamp(audit.getTime());
                // **End**
                //change for performance
                if(benefitLevelBO.isModified())
                //end
	                adapterServicesAccess
	                        .persistObject(
	                                benefitLevelBO,
	                                "com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelBO",
	                                btc);
            }

        } catch (AdapterException adapterException) {
            logAdapterException(benefitLevelBO, adapterException);
        }
        Logger.logInfo(th.endPerfLogging());
        return true;
    }


    /**
     * Method to retrieve the latest version.
     * 
     * @param questionBO
     * @return QuestionBO
     * @throws ServiceException
     */
    public BenefitLevelBO retrieveBenefitLevel(BenefitLevelBO benefitLevelBO)
            throws SevereException {

        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess(this.EWPDB_DOMAIN);
        //AdapterUtil.beginTransaction(adapterServicesAccess);
        try {
            benefitLevelBO = (BenefitLevelBO) adapterServicesAccess
                    .retrieveObject(benefitLevelBO,
                            "com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelBO");
        } catch (AdapterException adapterException) {
            logAdapterException(benefitLevelBO, adapterException);
        }
        //AdapterUtil.endTransaction(adapterServicesAccess);
        return benefitLevelBO;
    }


    /**
     * Method to retrieve the latest version.
     * 
     * @param questionBO
     * @return QuestionBO
     * @throws ServiceException
     */
    public BenefitLineBO retrieveBenefitLine(BenefitLineBO benefitLineBO)
            throws SevereException {

        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess(this.EWPDB_DOMAIN);
        //AdapterUtil.beginTransaction(adapterServicesAccess);
        try {
            benefitLineBO = (BenefitLineBO) adapterServicesAccess
                    .retrieveObject(benefitLineBO,
                            "com.wellpoint.wpd.common.benefitlevel.bo.BenefitLineBO");
        } catch (AdapterException adapterException) {
            logAdapterException(benefitLineBO, adapterException);
        }
        //AdapterUtil.endTransaction(adapterServicesAccess);
        return benefitLineBO;
    }


    /**
     * Method to throw the exception.
     * 
     * @param obj,adapterException
     * @throws ServiceException
     */
    private void logAdapterException(Object obj,
            AdapterException adapterException) throws ServiceException {
        List errorParams = new ArrayList();
        errorParams.add(obj);
        throw new ServiceException("Adapter Exception occured", errorParams,
                adapterException);

    }
    /**
     * 
     * @param benefitDefinitionId
     * @return
     * @throws SevereException
     */
	public List getBenefitValue(int benefitDefinitionId)throws SevereException{
	    HashMap referenceValueSet = new HashMap();
	    referenceValueSet.put("benefitDefinitionId", new Integer(benefitDefinitionId));
	    SearchCriteria adapterSearchCriteria = null;
	    adapterSearchCriteria = AdapterUtil.getAdapterSearchCriteria(
									    		BenefitLevelBO.class.getName(), 
												"benefitValue",
									            referenceValueSet);
	    return AdapterUtil.performSearch(adapterSearchCriteria).getSearchResults();
	}
    /**
     * @param benefitWrapperBO
     * @return
     * @throws ServiceException
     */
    public LocateResults locateBenefitLevels(
            BenefitLocateCriteria benefitLocateCriteria) throws SevereException {
    	TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23057 : Save/Load Benefit Coverage", "CebefitLevelAdapterManager", "locateBenefitLevels"));
        SearchResults benefitSearchResults = null;
        List benefitLevels = null;
        LocateResults locateResults = new LocateResults();
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitDefinitionId", new Integer(
                benefitLocateCriteria.getBenefitDefinitionId()));
        referenceValueSet.put("termDesc",benefitLocateCriteria.getSelectedBenefitTerm());
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.override.benefit.bo.BenefitLine");

        searchCriteria.setSearchQueryName("benefitLevelandLineSearch");
        searchCriteria.setMaxSearchResultSize(99999);
        searchCriteria.setSearchDomain("medical");
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        try {
            benefitSearchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterException(null, adapterException);
        }
        List searchResults=benefitSearchResults.getSearchResults();
        if (null != searchResults) {
        	benefitLevels = convertTOLevelBOsList(searchResults);
            searchBenefitTermsDescForCorrespondingBenefitLevel(benefitLevels);
            searchBenefitQualifiersDescForCorrespondingBenefitLevel(benefitLevels);
        }
        locateResults.setLocateResults(benefitLevels);
        Logger.logInfo(th.endPerfLogging());
        return locateResults;
    }
    private List convertTOLevelBOsList(List searchResults) {
        List benefitLevelsList = new ArrayList();
        List levelIdsList = new ArrayList();
        List benefitLinesList = null;
        for(int i = 0; i < searchResults.size(); i++){
            BenefitLine benefitLine1 = (BenefitLine)searchResults.get(i);
            if(null != benefitLine1){
                BenefitLevelBO benefitLevelBO = new BenefitLevelBO();
                benefitLevelBO.setBenefitLevelId(benefitLine1.getLevelSysId());
                benefitLevelBO.setBenefitDefinitionId(benefitLine1.getBenefitDefinitionId());
                benefitLevelBO.setBenefitLevelDesc(benefitLine1.getLevelDesc());
                benefitLevelBO.setBenefitQualifier(benefitLine1.getQualifierDesc());
                benefitLevelBO.setBenefitQualifierCode(benefitLine1.getQualifierCode());
                benefitLevelBO.setBenefitTerm(benefitLine1.getTermDesc());
                benefitLevelBO.setBenefitTermCode(benefitLine1.getTermCode());
                benefitLevelBO.setBenefitLevelSeq(benefitLine1.getSequenceId());
                // Setting the Frequency value from the BenefitLine BO to BenefitLevelBO
                benefitLevelBO.setBenefitFrequency(benefitLine1.getFrequencyValue());
                benefitLinesList = new ArrayList();
                for(int j = 0; j < searchResults.size(); j++){
                    BenefitLine benefitLine2 = (BenefitLine)searchResults.get(j);
                    if(benefitLine1.getLevelSysId() == benefitLine2.getLevelSysId()){
                        BenefitLineBO benefitLineBO = new BenefitLineBO();
                        benefitLineBO.setBenefitLevelId(benefitLine2.getLevelSysId());
                        benefitLineBO.setBenefitLineId(benefitLine2.getLineSysId());
                        benefitLineBO.setBenefitDefenitionId(benefitLine2.getBenefitDefinitionId());
                        benefitLineBO.setBenefitValue(benefitLine2.getLineValue());
                        benefitLineBO.setBnftLineNotesExist(benefitLine2.getBnftLineNotesExist());
                        if(null != benefitLine2.getDataTypeDesc() && !benefitLine2.getDataTypeDesc().equals(""))
                            benefitLineBO.setDataTypeId(Integer.parseInt(benefitLine2.getDataTypeDesc()));
                        benefitLineBO.setDataTypeName(benefitLine2.getDataTypeLegend());
                        benefitLineBO.setPVA(benefitLine2.getProviderArrangementDesc());
                        benefitLineBO.setPvaCode(benefitLine2.getProviderArrangementId());
                        benefitLineBO.setReference(benefitLine2.getReferenceDesc());
                        benefitLineBO.setReferenceCode(benefitLine2.getReferenceCode());
                        benefitLinesList.add(benefitLineBO);
                    }
                }
                benefitLevelBO.setBenefitLines(benefitLinesList);
                if(!levelIdsList.contains(new Integer(benefitLevelBO.getBenefitLevelId()))){
                    levelIdsList.add(new Integer(benefitLevelBO.getBenefitLevelId()));
                    benefitLevelsList.add(benefitLevelBO);
                }
            }
        }
         return benefitLevelsList; 
      }

    
    public LocateResults locateBenefitLevelTerm(
    		BenefitLevelTermLocateCriteria benefitLevelTermLocateCriteria) throws ServiceException {
    	LocateResults locateResults = new LocateResults();
    	SearchResults benefitLevelTermSearchResults = null;
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitDefinitionId", new Integer(
        		benefitLevelTermLocateCriteria.getBenefitDefinitionId()));
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelBO");
        searchCriteria.setSearchQueryName("benefitTermSearch");
        searchCriteria.setMaxSearchResultSize(99999);
        searchCriteria.setSearchDomain("medical");
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
        		.getAdapterServicesAccess();        
        try {
        	benefitLevelTermSearchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterException(null, adapterException);
        }        
        locateResults.setLocateResults(benefitLevelTermSearchResults.getSearchResults());
    	return locateResults;
    }


    /**
     * @param benefitLevelsList
     * @throws ServiceException
     */
    private void searchBenefitLinesForCorrespondingBenefitLevel(
            List benefitLevelsList) throws SevereException {
        List benefitLinesSearchResultsList = null;
        LocateResults searchResults = null;
        if (null != benefitLevelsList) {
            for (int i = 0; i < benefitLevelsList.size(); i++) {
                BenefitLevelBO benefitLevelBO = (BenefitLevelBO) benefitLevelsList
                        .get(i);
                BenefitLevelLocateCriteria benefitLevelLocateCriteria = new BenefitLevelLocateCriteria();
                benefitLevelLocateCriteria.setBenefitLevelId(benefitLevelBO
                        .getBenefitLevelId());
                 //modified to include def id on 12th Dec 2007
                benefitLevelLocateCriteria.setBenefitDefinitionId(benefitLevelBO.getBenefitDefinitionId());
                try {
                    searchResults = this
                            .locateBenefitLines(benefitLevelLocateCriteria);
                } catch (Exception ex) {
                	Logger.logError(ex);
                    List logParameters = new ArrayList();
                    String logMessage = "Failed while processing searchBenefitLine";
                    throw new ServiceException(logMessage, logParameters, ex);
                }
                int searchResultCount = searchResults.getTotalResultsCount();
                benefitLinesSearchResultsList = searchResults
                        .getLocateResults();
                if (benefitLinesSearchResultsList.size() > 0) {
                    benefitLevelBO
                            .setBenefitLines(benefitLinesSearchResultsList);

                } else if (benefitLinesSearchResultsList.size() == 0
                        && searchResultCount == 0) {
                    benefitLevelBO
                            .setBenefitLines(benefitLinesSearchResultsList);
                }
            }
        }
    }


    /**
     * @param benefitLocateCriteria
     * @return
     * @throws ServiceException
     */
    public LocateResults locateBenefitLines(
            BenefitLevelLocateCriteria benefitLevelLocateCriteria)
            throws SevereException {
    	TimeHandler th = new TimeHandler();
    	Logger.logInfo(th.startPerfLogging("U23057 : Save/Load benefit Coverage", "BenefitLevelAdapterManager", "locateBenefitLines()"));
        SearchResults benefitLinesSearchResults = null;
        LocateResults locateResults = new LocateResults();
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitLevelId", new Integer(
                benefitLevelLocateCriteria.getBenefitLevelId()));
         //modified to include def id on 12th Dec 2007
        referenceValueSet.put("benefitDefinitionId", new Integer(
                benefitLevelLocateCriteria.getBenefitDefinitionId()));
        
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.benefitlevel.bo.BenefitLineBO");

        searchCriteria.setSearchQueryName("benefitLineSearch");
        searchCriteria.setMaxSearchResultSize(99999);
        searchCriteria.setSearchDomain("medical");
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        // AdapterUtil.beginTransaction(adapterServicesAccess);
        try {
            benefitLinesSearchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterException(null, adapterException);
        }
        if (null != benefitLinesSearchResults.getSearchResults())
            locateResults.setTotalResultsCount(benefitLinesSearchResults
                    .getSearchResults().size());
        locateResults.setLocateResults(benefitLinesSearchResults
                .getSearchResults());
        //AdapterUtil.endTransaction(adapterServicesAccess);
        Logger.logInfo(th.endPerfLogging());
        return locateResults;
    }


    


    /**
     * @param benefitLevelLocateCriteria
     * @return
     * @throws ServiceException
     */
    public LocateResults locateBenefitTerms(
            BenefitLevelLocateCriteria benefitLevelLocateCriteria)
            throws SevereException {
        SearchResults benefitTermsSearchResults = null;
        LocateResults locateResults = new LocateResults();
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitLevelId", new Integer(
                benefitLevelLocateCriteria.getBenefitLevelId()));
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.benefitlevel.bo.BenefitTermBO");

        searchCriteria.setSearchQueryName("benefitTermSearch");
        searchCriteria.setMaxSearchResultSize(99999);
        searchCriteria.setSearchDomain("medical");
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        // AdapterUtil.beginTransaction(adapterServicesAccess);
        try {
            benefitTermsSearchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterException(null, adapterException);
        }
        if (null != benefitTermsSearchResults.getSearchResults())
            locateResults.setTotalResultsCount(benefitTermsSearchResults
                    .getSearchResults().size());
        locateResults.setLocateResults(benefitTermsSearchResults
                .getSearchResults());
        //AdapterUtil.endTransaction(adapterServicesAccess);
        return locateResults;
    }


    /**
     * @param benefitLevelsList
     * @throws ServiceException
     */
    private void searchBenefitTermsDescForCorrespondingBenefitLevel(
            List benefitLevelsList) throws SevereException {
        List benefitTermsSearchResultsList = null;
        BenefitTermBO benefitTermBO = null;
        if (null != benefitLevelsList) {
            for (int i = 0; i < benefitLevelsList.size(); i++) {
                BenefitLevelBO benefitLevelBO = (BenefitLevelBO) benefitLevelsList.get(i);
                benefitTermsSearchResultsList = new ArrayList();
                List benefitTermCodes = StringUtil.convertToList(benefitLevelBO.getBenefitTermCode());
                List benefitTerms =  StringUtil.convertToList(benefitLevelBO.getBenefitTerm());
                if(benefitTermCodes.size() > 1){
                	for(int j = 0; j < benefitTermCodes.size(); j++) {
                		 benefitTermBO = new BenefitTermBO();
                         benefitTermBO.setBenefitTerm((String)benefitTerms.get(j));
                         benefitTermBO.setBenefitTermCode((String)benefitTermCodes.get(j));
                         benefitTermsSearchResultsList.add(benefitTermBO);
                	}
                
                }else if(benefitTermCodes.size() == 1){
                    benefitTermBO = new BenefitTermBO();
                    benefitTermBO.setBenefitLevelId(benefitLevelBO.getBenefitLevelId());
                    benefitTermBO.setBenefitTerm(benefitLevelBO.getBenefitTerm());
                    benefitTermBO.setBenefitTermCode(benefitLevelBO.getBenefitTermCode());
                    benefitTermsSearchResultsList.add(benefitTermBO);
                }
                benefitLevelBO.setBenefitTerms(benefitTermsSearchResultsList);
            }
        }
    }
    
    
    

    /**
     * @param benefitLevelLocateCriteria
     * @return
     * @throws ServiceException
     */
    public LocateResults locateBenefitTermsDesc(
            BenefitLevelLocateCriteria benefitLevelLocateCriteria)
            throws SevereException {
        SearchResults benefitTermsSearchResults = null;
        LocateResults locateResults = new LocateResults();
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitLevelId", new Integer(
                benefitLevelLocateCriteria.getBenefitLevelId()));
        if (null != benefitLevelLocateCriteria.getBenefitTerm()
                && !"".equals(benefitLevelLocateCriteria.getBenefitTerm())) {
            referenceValueSet.put("benefitTermId", benefitLevelLocateCriteria
                    .getBenefitTerm());
        }
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.benefitlevel.bo.BenefitTermBO");

        searchCriteria.setSearchQueryName("benefitTermDescSearch");
        searchCriteria.setMaxSearchResultSize(99999);
        searchCriteria.setSearchDomain("medical");
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        // AdapterUtil.beginTransaction(adapterServicesAccess);
        try {
            benefitTermsSearchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterException(null, adapterException);
        }
        if (null != benefitTermsSearchResults.getSearchResults())
            locateResults.setTotalResultsCount(benefitTermsSearchResults
                    .getSearchResults().size());
        locateResults.setLocateResults(benefitTermsSearchResults
                .getSearchResults());
        //AdapterUtil.endTransaction(adapterServicesAccess);
        return locateResults;
    }


    // **End**
    
    
//  **Change 11/19/07
    /**
     * @param benefitLevelsList
     * @throws ServiceException
     */
    private void searchBenefitQualifiersDescForCorrespondingBenefitLevel(
            List benefitLevelsList) throws SevereException {
        List benefitQualifierSearchResultsList = null;
        BenefitQualifierBO benefitQualifierBO = null;
        if (null != benefitLevelsList) {
            for (int i = 0; i < benefitLevelsList.size(); i++) {
                BenefitLevelBO benefitLevelBO = (BenefitLevelBO) benefitLevelsList
                        .get(i);
                benefitQualifierSearchResultsList = new ArrayList();
                List benefitQualCodes = StringUtil.convertToList(benefitLevelBO.getBenefitQualifierCode());
                List benefitQuals = StringUtil.convertToList(benefitLevelBO.getBenefitQualifier());
                if(benefitQualCodes.size() > 1){
                    for (int j = 0; j < benefitQualCodes.size(); j++) {
                    	benefitQualifierBO = new BenefitQualifierBO();
                    	benefitQualifierBO.setBenefitQualifier((String)benefitQuals.get(j));
 	                    benefitQualifierBO.setBenefitQualifierCode((String)benefitQualCodes.get(j));
                    	benefitQualifierSearchResultsList.add(benefitQualifierBO);
                    }
                }else if(benefitQualCodes.size() == 1){
                    benefitQualifierBO = new BenefitQualifierBO();
                    benefitQualifierBO.setBenefitLevelId(benefitLevelBO.getBenefitLevelId());
                    benefitQualifierBO.setBenefitQualifier(benefitLevelBO.getBenefitQualifier());
                    benefitQualifierBO.setBenefitQualifierCode(benefitLevelBO.getBenefitQualifierCode());
                    benefitQualifierSearchResultsList.add(benefitQualifierBO);
                }
                benefitLevelBO.setBenefitQualifiers(benefitQualifierSearchResultsList);
            }
        }
    }

    /**
     * @param benefitLevelLocateCriteria
     * @return
     * @throws ServiceException
     */
    public LocateResults locateBenefitQualifiersDesc(
            BenefitLevelLocateCriteria benefitLevelLocateCriteria)
            throws SevereException {
        SearchResults benefitQualifiersSearchResults = null;
        LocateResults locateResults = new LocateResults();
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("benefitLevelId", new Integer(
                benefitLevelLocateCriteria.getBenefitLevelId()));
        if (null != benefitLevelLocateCriteria.getBenefitQualifier()
                && !"".equals(benefitLevelLocateCriteria.getBenefitQualifier())) {
            referenceValueSet.put("benefitQualifierCode", benefitLevelLocateCriteria
                    .getBenefitQualifier());
        }
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.benefitlevel.bo.BenefitQualifierBO");

        searchCriteria.setSearchQueryName("benefitQualifierDescSearch");
        searchCriteria.setMaxSearchResultSize(99999);
        searchCriteria.setSearchDomain("medical");
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        // AdapterUtil.beginTransaction(adapterServicesAccess);
        try {
        	benefitQualifiersSearchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterException(null, adapterException);
        }
        if (null != benefitQualifiersSearchResults.getSearchResults())
            locateResults.setTotalResultsCount(benefitQualifiersSearchResults
                    .getSearchResults().size());
        locateResults.setLocateResults(benefitQualifiersSearchResults
                .getSearchResults());
        //AdapterUtil.endTransaction(adapterServicesAccess);
        return locateResults;
    }
//  ** End
    
    /**    
     * 
     * @param benefitLevelBO
     * @param adapterServicesAccess
     * @return
     * @throws ServiceException
     * @throws AdapterException
     */
    public boolean removeBenefit(BenefitLevelBO benefitLevelBO, AdapterServicesAccess adapterServicesAccess)
            throws SevereException, AdapterException {

        long transactionId = AdapterUtil.getTransactionId();
        try {
            AdapterUtil.beginTransaction(adapterServicesAccess);
            AdapterUtil.logBeginTranstn(transactionId , this ,"removeBenefit(BenefitLevelBO benefitLevelBO, AdapterServicesAccess adapterServicesAccess)");
            BusinessTransactionContext btc = new BusinessTransactionContextImpl();
            btc.setBusinessTransactionType(this.DELETE);
            btc.setBusinessTransactionUser(this.USER_NAME);
            adapterServicesAccess.removeObject(benefitLevelBO,
                    "com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelBO",
                    btc);
            AdapterUtil.endTransaction(adapterServicesAccess);
            AdapterUtil.logTheEndTransaction(transactionId , this ,"removeBenefit(BenefitLevelBO benefitLevelBO, AdapterServicesAccess adapterServicesAccess)");
        } catch (AdapterException adapterException) {
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this ,"removeBenefit(BenefitLevelBO benefitLevelBO, AdapterServicesAccess adapterServicesAccess)");
           logAdapterException(benefitLevelBO, adapterException);
        } catch (Exception e) {
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId , this ,"removeBenefit(BenefitLevelBO benefitLevelBO, AdapterServicesAccess adapterServicesAccess)");
            throw new SevereException("Unhandled exception occured", null, e);
        }
        return true;
    }


    /**
     * @param benefitLevelBO
     * @param adapterServicesAccess
     * @return
     * @throws ServiceException
     * @throws AdapterException
     */
    public boolean removeQuestions(BenefitLevelBO benefitLevelBO, AdapterServicesAccess adapterServicesAccess)
            throws SevereException,AdapterException {
        
        AdminOptionBenefitLevelBO adminOptionBenefitLevelBO = new AdminOptionBenefitLevelBO();
        adminOptionBenefitLevelBO.setBenefitLevelId(benefitLevelBO.getBenefitLevelId());
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        searchCriteria
        			.setBusinessObjectName("com.wellpoint.wpd.common.standardbenefit.bo.AdminOptionBenefitLevelBO");
		searchCriteria.setMaxSearchResultSize(100);
		searchCriteria.setSearchQueryName("adminAssociationIdSearch");
		searchCriteria.setSearchDomain("medical");
		HashMap refValSet = new HashMap();
		BusinessTransactionContext btc = new BusinessTransactionContextImpl();
		
		refValSet.put("benefitLevelId", new Integer(adminOptionBenefitLevelBO.getBenefitLevelId()));
		refValSet.put("benefitDefenitionId", new Integer(benefitLevelBO.getBenefitDefinitionId()));

		searchCriteria.setReferenceValueSet(refValSet);
		
		
		    searchResults = adapterServicesAccess.searchObject(searchCriteria);
		
		locateResults.setLocateResults(searchResults.getSearchResults());
		
		List list = locateResults.getLocateResults();
		Iterator iterator = list.iterator();

		
		while(iterator.hasNext()){
		    AdminOptionBenefitLevelBO levelBO = (AdminOptionBenefitLevelBO)iterator.next();		    
		        btc = new BusinessTransactionContextImpl();
			    btc.setBusinessTransactionType(this.DELETE);
			    btc.setBusinessTransactionUser(this.USER_NAME);			    
		        adapterServicesAccess.removeObject(levelBO,
		                levelBO.getClass().getName(), btc);
		       		  
		}
        return true;
    }
    public boolean removeBenefitLevel(BenefitLevelBO benefitLevelBO, AdapterServicesAccess adapterServicesAccess)
    	throws SevereException,AdapterException {
    	
        SearchResults searchResults = null;
        LocateResults locateResults = new LocateResults();
        HashMap refValSet = new HashMap();
        refValSet.put("benefitDefinitionId",new Integer(benefitLevelBO.getBenefitDefinitionId()));
        refValSet.put("benefitLevelId",new Integer(benefitLevelBO.getBenefitLevelId()));
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setBusinessObjectName("com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelBO");
        searchCriteria.setMaxSearchResultSize(100);
        searchCriteria.setSearchQueryName("deleteBenefitLevel");
        searchCriteria.setSearchDomain("medical");
        searchCriteria.setReferenceValueSet(refValSet);
        try{
        	searchResults = AdapterUtil.performSearch(searchCriteria);
        }catch(Exception ex){
        	throw new AdapterException ("Exception occured while adapter call",ex);
        }
        return true;    	
    }
    
    public boolean removeBenefitLine(BenefitLineBO benefitLineBO, AdapterServicesAccess adapterServicesAccess)
	throws SevereException,AdapterException {
	
    SearchResults searchResults = null;
    LocateResults locateResults = new LocateResults();
    HashMap refValSet = new HashMap();
    refValSet.put("benefitDefinitionId",new Integer(benefitLineBO.getBenefitDefenitionId()));
    refValSet.put("benefitLineId",new Integer(benefitLineBO.getBenefitLineId()));
    SearchCriteria searchCriteria = new SearchCriteriaImpl();
    searchCriteria.setBusinessObjectName("com.wellpoint.wpd.common.benefitlevel.bo.BenefitLineBO");
    searchCriteria.setMaxSearchResultSize(100);
    searchCriteria.setSearchQueryName("deleteBenefitLine");
    searchCriteria.setSearchDomain("medical");
    searchCriteria.setReferenceValueSet(refValSet);
    try{
    	searchResults = AdapterUtil.performSearch(searchCriteria);
    }catch(Exception ex){
    	throw new AdapterException ("Exception occured while adapter call",ex);
    }
    return true;    	
}
    
    
    /**
     * @param benefitLineBO
     * @param adapterServicesAccess
     * @return
     * @throws ServiceException
     * @throws AdapterException
     */
    public boolean removeBenefit(BenefitLineBO benefitLineBO)
            throws SevereException,AdapterException {
        try {
            BusinessTransactionContext btc = new BusinessTransactionContextImpl();
            btc.setBusinessTransactionType(this.DELETE);
            btc.setBusinessTransactionUser(this.USER_NAME);
            this.getAdapterService().removeObject(benefitLineBO,
                    "com.wellpoint.wpd.common.benefitlevel.bo.BenefitLineBO",
                    btc);
        } catch (AdapterException ex) {
        	 List errorParams = new ArrayList();
             String obj = ex.getClass().getName();
             errorParams.add(obj);
             errorParams.add(obj.getClass().getName());
             throw new SevereException(
                     "Persist failed for BenefitLineBO delete in BenefitLevelAdapterManager",
                     errorParams, ex);
        }
        return true;
    }


    /**
     * @param benefitLevelPopUpBO
     * @return
     * @throws ServiceException
     */
    public LocateResults locate(BenefitLevelPopUpBO benefitLevelPopUpBO)
            throws SevereException {
        SearchResults benefitSearchResults = null;
        LocateResults locateResults = new LocateResults();
        HashMap referenceValueSet = new HashMap();
        referenceValueSet.put("standardBenefitId", new Integer(
                benefitLevelPopUpBO.getStandardBenefitKey()));
        referenceValueSet.put("selectedType", benefitLevelPopUpBO
                .getSelectedType());
        if(benefitLevelPopUpBO.getCatalogId() != 0)
        referenceValueSet.put("catalogId",new Integer(benefitLevelPopUpBO.getCatalogId()));
        SearchCriteria searchCriteria = new SearchCriteriaImpl();
        searchCriteria.setReferenceValueSet(referenceValueSet);
        searchCriteria
                .setBusinessObjectName("com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelPopUpBO");

        searchCriteria.setSearchQueryName("benefitPopUpSearch");
        searchCriteria.setMaxSearchResultSize(9999);
        searchCriteria.setSearchDomain("medical");
        AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                .getAdapterServicesAccess();
        //AdapterUtil.beginTransaction(adapterServicesAccess);
        try {
            benefitSearchResults = adapterServicesAccess
                    .searchObject(searchCriteria);
        } catch (AdapterException adapterException) {
            logAdapterException(null, adapterException);
        }
        if (null != benefitSearchResults.getSearchResults())
            locateResults.setTotalResultsCount(benefitSearchResults
                    .getSearchResults().size());
        locateResults.setLocateResults(benefitSearchResults.getSearchResults());
        //AdapterUtil.endTransaction(adapterServicesAccess);
        return locateResults;
    }


    /**
     * 
     * @param standardbenefitKey
     * @return
     * @throws SevereException
     */
    public int getNextSequenceForBenefitLevel(int standardbenefitKey)
            throws SevereException {
        HashMap hashMap = new HashMap();
        hashMap.put("benefitDefinitionId", new Integer(standardbenefitKey));
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BenefitLevelBO.class.getName(), "getNextSequence", hashMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        BenefitLevelBO benefitLevelBO = new BenefitLevelBO();
        if (null != searchResults) {
            if (searchResults.getSearchResultCount() == 0)
                return 1;
            benefitLevelBO = (BenefitLevelBO) searchResults.getSearchResults()
                    .get(0);
        }
        return benefitLevelBO.getBenefitLevelSeq();
    }


    /**
     * @param benefitLineLocateCriteria
     * @return
     * @throws SevereException
     */
    public boolean locateDependantBenefitLines(
            BenefitLineLocateCriteria benefitLineLocateCriteria)
            throws SevereException {
        boolean dependancyFlag = false;
        HashMap hashMap = new HashMap();
        hashMap.put("benefitLineId", new Integer(benefitLineLocateCriteria
                .getBenefitLineId()));
        BenefitLineBO benefitLineBO = new BenefitLineBO();
        benefitLineBO.setBenefitLineId(benefitLineLocateCriteria
                .getBenefitLineId());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BenefitLineBO.class.getName(), "benefitLineDependancySearch",
                hashMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        BenefitLineBO benefitLineBO2 = new BenefitLineBO();
        if (null != searchResults) {
            if (searchResults.getSearchResultCount() == 0)
                dependancyFlag = false;
            else {
                benefitLineBO2 = (BenefitLineBO) searchResults
                        .getSearchResults().get(0);
                dependancyFlag = true;
            }
        }
        return dependancyFlag;
    }


    /**
     * @param locateCriteria
     * @return
     * @throws SevereException
     */
    public boolean benefitLevelDependancySearch(
            BenefitLevelLocateCriteria locateCriteria) throws SevereException {
        boolean dependancyFlag = false;
        HashMap hashMap = new HashMap();
        hashMap.put("benefitLevelId", new Integer(locateCriteria
                .getBenefitLevelId()));
        BenefitLevelBO benefitLevelBO = new BenefitLevelBO();
        benefitLevelBO.setBenefitLevelId(locateCriteria.getBenefitLevelId());
        SearchCriteria searchCriteria = AdapterUtil.getAdapterSearchCriteria(
                BenefitLevelBO.class.getName(), "benefitLevelDependancySearch",
                hashMap);
        SearchResults searchResults = AdapterUtil.performSearch(searchCriteria);
        BenefitLevelBO benefitLevelBO2 = new BenefitLevelBO();
        if (null != searchResults) {
            if (searchResults.getSearchResultCount() == 0)
                dependancyFlag = false;
            else {
                benefitLevelBO2 = (BenefitLevelBO) searchResults
                        .getSearchResults().get(0);
                dependancyFlag = true;
            }
        }
        return dependancyFlag;
    }

    /**
     * Method to delete the benefit levels and lines.
     * @param locateCriteria
     * @param userId
     * @return
     * @throws SevereException
     * @throws AdapterException
     */
    public LocateResults deleteBenefitLevelAndLine
			(BenfitLevelLineDeleteLocateCriteria locateCriteria, String userId)	
					throws SevereException,AdapterException {
	// Variable Decalarations.
    SearchResults searchResults = null;
    LocateResults locateResults = null;
    HashMap refValSet = null;
    
    refValSet = new HashMap();
    refValSet.put("benefitDefinitionId",
    		new Integer(locateCriteria.getBenefitDefnId()));
    refValSet.put("benefitLevelIds",
    		locateCriteria.getBenefitLevels());
    refValSet.put("benefitLineIds",
    		locateCriteria.getBenefitLines());
    SearchCriteria searchCriteria = new SearchCriteriaImpl();
    searchCriteria.setBusinessObjectName("com.wellpoint.wpd.common.benefitlevel.bo.BenefitLevelBO");
    searchCriteria.setMaxSearchResultSize(100);
    searchCriteria.setSearchQueryName("deleteLevelsLines");
    searchCriteria.setSearchDomain("medical");
    searchCriteria.setReferenceValueSet(refValSet);
    try{
    	searchResults = AdapterUtil.performSearch(searchCriteria);
    }catch(Exception ex){
    	throw new AdapterException ("Exception occured while adapter call",ex);
    }    
	locateResults = new LocateResults();
	locateResults.setLocateResults
					(searchResults.getSearchResults());
	locateResults.setTotalResultsCount
					(searchResults.getSearchResultCount());
    return locateResults;    	
}
}