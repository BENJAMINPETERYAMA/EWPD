/*
 * ProductBusinessObjectBuilder.java
 *
 * © 2006 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Wellpoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.product.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.commons.lang.SerializationUtils;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.benefitcomponent.adapter.BenefitComponentAdapterManager;
import com.wellpoint.wpd.business.benefitcomponent.locatecriteria.ComponentsBenefitAdministrationLocateCriteria;
import com.wellpoint.wpd.business.benefitdefinition.adapter.NonAdjBenefitMandateAdapterManager;
import com.wellpoint.wpd.business.benefitlevel.adapter.BenefitlevelAdapterManager;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLevelLocateCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.BenefitAdapterManager;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.contract.adapter.ContractAdapterManager;
import com.wellpoint.wpd.business.contract.locatecriteria.RuleLocateCriteria;
import com.wellpoint.wpd.business.framework.bo.AuditFactory;
import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.notes.adapter.NotesAdapterManager;
import com.wellpoint.wpd.business.notes.adapter.NotesAttachmentAdapterManager;
import com.wellpoint.wpd.business.product.adapter.ProductAdapterManager;
import com.wellpoint.wpd.business.product.locatecriteria.AdminLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ComponentLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductAdminOptionLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductAdministrationLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductBenefitAdminLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductBenefitDefinitionLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductBenefitLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductComponentNotesLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductLocatePreviousVersionsCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductNotesLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductRuleRefDataLocateCriteria;
import com.wellpoint.wpd.business.product.locatecriteria.ProductStructureLocateCriteria;
import com.wellpoint.wpd.business.productstructure.adapter.ProductStructureAdapterManager;
import com.wellpoint.wpd.business.question.adapter.QuestionAdapterManager;
import com.wellpoint.wpd.business.refdata.adapter.RefdataAdapterManager;
import com.wellpoint.wpd.business.refdata.bo.AssignedRuleIdBO;
import com.wellpoint.wpd.business.standardbenefit.adapter.StandardBenefitAdapterManager;
import com.wellpoint.wpd.business.standardbenefit.builder.StandardBenefitBusinessObjectBuilder;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.BenefitMandateLocateCriteria;
import com.wellpoint.wpd.business.standardbenefit.locatecriteria.StateLocateCriteria;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitQualifierBO;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitTermBO;
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
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.notes.bo.AttachedNotesBO;
import com.wellpoint.wpd.common.notes.bo.ContractProductAONotesAttachmentBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentDomainOverrideBO;
import com.wellpoint.wpd.common.notes.bo.NotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBO;
import com.wellpoint.wpd.common.notes.bo.NotesToQuestionAttachmentBenefitBO;
import com.wellpoint.wpd.common.notes.bo.ProductNotes;
import com.wellpoint.wpd.common.notes.bo.TierNotesAttachmentOverrideBO;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitLine;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTier;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierBindingObject;
import com.wellpoint.wpd.common.override.benefit.bo.BenefitTierDefinition;
import com.wellpoint.wpd.common.override.benefit.bo.EntityBenefitAdministration;
import com.wellpoint.wpd.common.override.benefit.bo.ProductBenefitCustomizedBO;
import com.wellpoint.wpd.common.override.benefit.bo.TierDefinitionBO;
import com.wellpoint.wpd.common.override.benefit.bo.TierNoteOverRide;
import com.wellpoint.wpd.common.product.bo.EntityBenefitAdministrations;
import com.wellpoint.wpd.common.product.bo.EntityProductAdministration;
import com.wellpoint.wpd.common.product.bo.EntityProductBenefitAdministration;
import com.wellpoint.wpd.common.product.bo.HideAdminOptionBO;
import com.wellpoint.wpd.common.product.bo.ProductAdmin;
import com.wellpoint.wpd.common.product.bo.ProductAdminBO;
import com.wellpoint.wpd.common.product.bo.ProductBO;
import com.wellpoint.wpd.common.product.bo.ProductBenefitDefinitions;
import com.wellpoint.wpd.common.product.bo.ProductBenefitGeneralInfoBO;
import com.wellpoint.wpd.common.product.bo.ProductCheckBO;
import com.wellpoint.wpd.common.product.bo.ProductComponentBO;
import com.wellpoint.wpd.common.product.bo.ProductComponentRule;
import com.wellpoint.wpd.common.product.bo.ProductComponents;
import com.wellpoint.wpd.common.product.bo.ProductEntityBenefitAdmin;
import com.wellpoint.wpd.common.product.bo.ProductQuestionareBO;
import com.wellpoint.wpd.common.product.bo.ProductQuestionnaireAssnBO;
import com.wellpoint.wpd.common.product.bo.ProductRuleAssociation;
import com.wellpoint.wpd.common.product.bo.ProductRuleAssociationBO;
import com.wellpoint.wpd.common.product.bo.ProductRuleBO;
import com.wellpoint.wpd.common.product.bo.ProductRuleIdBO;
import com.wellpoint.wpd.common.product.bo.ProductSearchResult;
import com.wellpoint.wpd.common.product.bo.ProductTierDefnOverrideBO;
import com.wellpoint.wpd.common.product.bo.RuleDetailBO;
import com.wellpoint.wpd.common.product.bo.SaveProductQuestionareBO;
import com.wellpoint.wpd.common.product.tree.bo.ProductTreeStandardBenefits;
import com.wellpoint.wpd.common.product.vo.ProductBenefitGeneralInformationVO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBO;
import com.wellpoint.wpd.common.productstructure.bo.ProductStructureBenefitAdministration;
import com.wellpoint.wpd.common.question.bo.PossibleAnswerBO;
import com.wellpoint.wpd.common.question.bo.QuestionBO;
import com.wellpoint.wpd.common.report.bo.RuleReportBO;
import com.wellpoint.wpd.common.standardbenefit.bo.BenefitMandateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.CitationNumberBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitDatatypeBO;
import com.wellpoint.wpd.common.standardbenefit.bo.StateBO;
import com.wellpoint.wpd.common.standardbenefit.bo.UniverseBO;
import com.wellpoint.wpd.common.util.BenefitTierUtil;
import com.wellpoint.wpd.util.RuleReportSort;
import com.wellpoint.wpd.util.TimeHandler;
import com.wellpoint.wpd.web.util.WPDStringUtil;
import com.wellpoint.wpd.web.util.WebConstants;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ProductBusinessObjectBuilder
{
private ProductAdapterManager adapterManager;
  
  private SequenceAdapterManager sequenceManager;
  
  private BenefitAdapterManager benefitAdapterManager;
  
  public ProductBusinessObjectBuilder(){
  	
  adapterManager = new ProductAdapterManager();

  sequenceManager = new SequenceAdapterManager();
  
  benefitAdapterManager = new BenefitAdapterManager();
 }

  /**
   * Return Product Name.
   * @param productSystemId
   * @return
   * @throws SevereException
   */
  public List getProductName(String productSystemId) throws SevereException
  {
    HashMap map = new HashMap();
    map.put(WebConstants.PRODUCT_KEY, new Integer(productSystemId));
    List dateSegmentDetails = adapterManager.retrieveProductName(map);
    return dateSegmentDetails;

  }
  
  
  /**
   * @param migrationSystemId
   * @throws SevereException
   */
  public LocateResults locate(ProductBenefitLocateCriteria productBenefitLocateCriteria,User user) throws SevereException {
        return this.adapterManager.retrieveProductAssociatedBenefitDetails(productBenefitLocateCriteria);
  }
  
  


  /**
   * 
   * @param productTreeStandardBenefits
   * @return
   * @throws SevereException
   */
  public boolean persist(ProductTreeStandardBenefits productTreeStandardBenefits,ProductBO productBO,Audit audit,boolean insertFlag) throws SevereException
  {
	long transactionId = AdapterUtil.getTransactionId();
    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    try
    {
		AdapterUtil.beginTransaction(adapterServicesAccess);	
		AdapterUtil.logBeginTranstn(transactionId, this, BusinessConstants.UPDATE_BENEFIT_DETAILS);
		adapterManager.updateAllBenefitDetails(productTreeStandardBenefits, adapterServicesAccess, audit.getUser());
		AdapterUtil.endTransaction(adapterServicesAccess);
		AdapterUtil.logTheEndTransaction(transactionId, this, BusinessConstants.UPDATE_BENEFIT_DETAILS);
    }
    catch (SevereException e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId, this, BusinessConstants.UPDATE_BENEFIT_DETAILS);
      List errorParams = new ArrayList(2);
      String obj = e.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException(BusinessConstants.EXCEPTION_OCCURED_UPDATE_BENEFITDETAILS, errorParams, e);
    }
    catch (AdapterException e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId, this, BusinessConstants.UPDATE_BENEFIT_DETAILS);
      List errorParams = new ArrayList(2);
      String obj = e.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException(BusinessConstants.EXCEPTION_OCCURED_UPDATE_BENEFITDETAILS, errorParams, e);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId, this, BusinessConstants.UPDATE_BENEFIT_DETAILS);
      throw new SevereException("Unhandled exception is caught", null, e);
    }
    return true;

  }


  /**
   * Method for persisting product admin override values.
   * 
   * @param subObject
   * @param mainObject
   * @param audit
   * @param insertFlag
   * @return
   * @throws SevereException
   */
  public boolean persist(HideAdminOptionBO subObject, ProductBO mainObject, Audit audit, boolean insertFlag) throws SevereException
  {
  	long transactionId = AdapterUtil.getTransactionId();
    Logger.logInfo("ProductBusinessObjectBuilder - Entering persist(HideAdminOptionBO subObject=" + subObject + ":ProductBO mainObject=" + mainObject + ":Audit audit=" + audit + 
                   ":boolean insertFlag=" + insertFlag);


    List adminList = subObject.getAdminBOList();

    


    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    try
    {
      AdapterUtil.beginTransaction(adapterServicesAccess); 
      AdapterUtil.logBeginTranstn(transactionId,this, BusinessConstants.PERSIST_METHODE);
      if(null != adminList){
      for (int i = 0; i < adminList.size(); i++)
      {

        HideAdminOptionBO administration = (HideAdminOptionBO) adminList.get(i);

        administration.setLastUpdatedUser(audit.getUser());
        administration.setCreatedUser(audit.getUser());
        administration.setLastUpdatedTimestamp(audit.getTime());
        administration.setCreatedTimestamp(audit.getTime());
        this.benefitAdapterManager.updateAdminOptionValues(administration, audit.getUser(), adapterServicesAccess);
      }
    }
      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId,this, "persist(HideAdminOptionBO subObject,  ProductBO mainObject, Audit audit, boolean insertFlag)");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess); 
      AdapterUtil.logAbortTxn(transactionId,this, "persist(HideAdminOptionBO subObject,  ProductBO mainObject, Audit audit, boolean insertFlag)");
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method , for persisting product admin override values, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      List errorParams = new ArrayList(2);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(HideAdminOptionBO subObject,  ProductBO mainObject, Audit audit, boolean insertFlag)");
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method , for persisting product admin override values, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(HideAdminOptionBO subObject,  ProductBO mainObject, Audit audit, boolean insertFlag)");
      throw new SevereException("Unhandled exception is caught", null, e);
    }
    return true;
  }


  /**
   * Method for persisting benefit admin override values.
   * 
   * @param subObject
   * @param mainObject
   * @param audit
   * @param insertFlag
   * @return
   * @throws SevereException
   */
  public boolean persist(EntityBenefitAdministrations subObject, ProductBO mainObject, Audit audit, boolean insertFlag) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering persist(EntityBenefitAdministrations subObject=" + subObject + ":ProductBO mainObject=" + mainObject + ":Audit audit=" + audit + 
                   ":boolean insertFlag=" + insertFlag);

    // Get the BOList from the BO
    List benefitAdministrationList = subObject.getBenefitAdministrationList();
    // Create an instance of the adapter manager
    BenefitAdapterManager adapterManager = new BenefitAdapterManager();
    // Iterate through the List
    long transactionId = AdapterUtil.getTransactionId();
    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    try
    {
      AdapterUtil.beginTransaction(adapterServicesAccess); //FIXME Remove hardcoded values
      AdapterUtil.logBeginTranstn(transactionId,this, "persist(EntityBenefitAdministrations subObject,  ProductBO mainObject, Audit audit, boolean insertFlag)");
      if(null != benefitAdministrationList){
      for (int i = 0; i < benefitAdministrationList.size(); i++)
      {

        // Get the individual BOs from the list
        EntityBenefitAdministration administration = (EntityBenefitAdministration) benefitAdministrationList.get(i);
        administration.setLastUpdatedTimestamp(audit.getTime());
        administration.setLastUpdatedUser(audit.getUser());

        // Call the persist() of the adapterManager to update the
        // administration
        adapterManager.updateBenefitAdministrationValues(administration, audit.getUser(), adapterServicesAccess);
      }
    }
      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId,this, "persist(EntityBenefitAdministrations subObject,  ProductBO mainObject, Audit audit, boolean insertFlag)");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(EntityBenefitAdministrations subObject,  ProductBO mainObject, Audit audit, boolean insertFlag)");
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method , for persisting benefit admin override values, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(EntityBenefitAdministrations subObject,  ProductBO mainObject, Audit audit, boolean insertFlag)");
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method , for persisting benefit admin override values, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(EntityBenefitAdministrations subObject,  ProductBO mainObject, Audit audit, boolean insertFlag)");
      throw new SevereException("Unhandled exception is caught", null, e);
    }
    return true;
  }
/*
 * @ProductStructureBenefitAdministration,ProductStructureBO
 * @ return boolean 
 * 
 */
  public boolean persist(ProductStructureBenefitAdministration subObject, ProductStructureBO mainObject, Audit audit, boolean insertFlag) throws SevereException
  {

    try
    {

      BenefitAdapterManager adapterManager = new BenefitAdapterManager();
      // Call the persist() of the adapterManager to update the
      // administration
      adapterManager.updateBenefitAdministrationValuesForHidingAdminOption(subObject, audit.getUser());


    }
    catch (SevereException ex)
    {

      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method , for persisting ProductStructureBenefitAdministration values, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {

      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method , for persisting ProductStructureBenefitAdministration values, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {

      throw new SevereException("Unhandled exception is caught", null, e);
    }
    return true;
  }


  public boolean persist(ProductStructureBenefitAdministration subObject, ProductBO mainObject, Audit audit, boolean insertFlag) throws SevereException
  {

    try
    {

    
      // Call the persist() of the adapterManager to update the
      // administration
      this.benefitAdapterManager.updateBenefitAdministrationValuesForHidingAdminOption(subObject, audit.getUser());

    }
    catch (SevereException ex)
    {

      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method , for persisting ProductStructureBenefitAdministration values, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {

      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method , for persisting ProductStructureBenefitAdministration values, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {

      throw new SevereException("Unhandled exception is caught", null, e);
    }
    return true;
  }

  //enhancement

  /**
   * Method prepareUniverseDisplayList
   * 
   * @param searchList
   * @param standardBenefitBO
   * @return
   */
  private StandardBenefitBO prepareUniverseDisplayList(List searchList, StandardBenefitBO standardBenefitBO)
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method prepareUniverseDisplayList()");

    Iterator searchListIterator = searchList.iterator();
    List termList = new ArrayList(3);
    List qualifierList = new ArrayList(3);
    List pvaList = new ArrayList(3);   
    if(null != searchListIterator){
    while (searchListIterator.hasNext())
    {
      UniverseBO universeBO = (UniverseBO) searchListIterator.next();
      if ((WebConstants.TERM).equals(universeBO.getCatDescText()))
      {
        termList.add(universeBO);
      }
      else if ((WebConstants.QUALIFIER).equals(universeBO.getCatDescText()))
      {
        qualifierList.add(universeBO);
      }
      else if ((WebConstants.PROVIDER_ARRANGEMENT).equals(universeBO.getCatDescText()))
      {
        pvaList.add(universeBO);
      }

    }
  }
    standardBenefitBO.setTermList(termList);
    standardBenefitBO.setQualifierList(qualifierList);
    standardBenefitBO.setPVAList(pvaList);
    return standardBenefitBO;
  }


  /**
   * Method to retrieve Overrrided admin values for product.
   * 
   * @param productBenefitLocateCriteria
   * @param user
   * @return
   * @throws WPDException
   */
  public LocateResults locate(ProductBenefitAdminLocateCriteria productBenefitLocateCriteria, User user) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering locate(ProductBenefitAdminLocateCriteria productBenefitLocateCriteria=" + productBenefitLocateCriteria + ":User user=" + user);

    BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
    
    AuditFactory auditFactory = (AuditFactory) ObjectFactory.getFactory(ObjectFactory.AUDIT);

	Audit audit = auditFactory.getAudit(user);
    try
    {
      List locateResultList = 
        benefitAdapterManager.getBenefitAdministrationValues(BusinessConstants.ENTITY_TYPE_PRODUCT, productBenefitLocateCriteria.getProductId(), productBenefitLocateCriteria.getAdminOptionId(), 
                                                             productBenefitLocateCriteria.getBenefitAdminId(), productBenefitLocateCriteria.getBenefitComponentId());

      //		 Check whether the locateResultList is null or empty
      if (null != locateResultList || !locateResultList.isEmpty())
      {

        // Create an instance of the QuestionAdapterManager
        QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();

        // Create the Adapter Service Access
        AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();

        // Iterate through the list to get the possible answers for the
        // question
        for (int i = 0; i < locateResultList.size(); i++)
        {
          // Get the individual entitybenefitAdministration objects from
          // the list
          EntityBenefitAdministration benefitAdministration = (EntityBenefitAdministration) locateResultList.get(i);
          benefitAdministration.setLastUpdatedTimestamp(audit.getTime());
          benefitAdministration.setLastUpdatedUser(audit.getUser());
          
          // Get the question number from the above object
          int questionNumber = benefitAdministration.getQuestionNumber();

          // Create an instance of the QuestionBO
          QuestionBO questionBO = new QuestionBO();

          // Set the question number to the questionBO
          questionBO.setQuestionNumber(questionNumber);

          // Call the getPossibleAnswers() of the question adaptermanager
          List possibleAnswerList = questionAdapterManager.getPossibleAnswer(questionBO, adapterServicesAccess);

          // Set the possible answers list to the
          // entityBenefitAdministration
          benefitAdministration.setAnswers(possibleAnswerList);
        }
      }
      LocateResults locateResults = new LocateResults();
      locateResults.setLocateResults(locateResultList);
      return locateResults;
    }
    catch (SevereException se)
    {
      List errorParams = new ArrayList(2);
      String obj = se.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in locate ProductBenefitAdminLocateCriteria method in ProductBusinessObjectBuilder", errorParams, se);
    }
    catch (AdapterException ae)
    {
      List errorParams = new ArrayList(2);
      String obj = ae.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in locate ProductBenefitAdminLocateCriteria method in ProductBusinessObjectBuilder", errorParams, ae);
    }
    catch (Exception ex)
    {
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in locate ProductBenefitAdminLocateCriteria method in ProductBusinessObjectBuilder", null, ex);
    }

  }


  /**
   * Method to retrieve product Structures.
   * 
   * @param locateCriteria
   * @param user
   * @return
   * @throws SevereException
   */
  public LocateResults locate(ProductStructureLocateCriteria locateCriteria, User user) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering locate(ProductStructureLocateCriteria locateCriteria=" + locateCriteria + ":User user=" + user);

    ProductStructureAdapterManager adapterManager = new ProductStructureAdapterManager();
    List prodStrList = 
      adapterManager.getValidProductStructures(locateCriteria.getLineOfBusiness(), locateCriteria.getBusinessEntity(), locateCriteria.getBusinessGroup(), locateCriteria.getEffectiveDate(), 
                                               locateCriteria.getExpiryDate(), locateCriteria.getStructureType(), locateCriteria.getMandateType(), locateCriteria.getStateId(), locateCriteria.getMarketBusinessUnit());
    LocateResults locateResults = new LocateResults();
    locateResults.setLocateResults(prodStrList);
    return locateResults;
  }


  /**
   * Method to Delete latest version of product.
   * 
   * @param product
   * @param audit
   * @return
   * @throws SevereException
   */
  public boolean deleteLatestVersion(ProductBO product, Audit audit) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method deleteLatestVersion(ProductBO product=" + product + ":Audit audit=" + audit);

 
    try
    {

      product.setLastUpdatedUser(audit.getUser());
      this.adapterManager.deleteProductTier(product);
      this.adapterManager.deleteProduct(product);

    }
    catch (SevereException ex)
    {
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in deleteLatestVersion method in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in deleteLatestVersion method in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      throw new SevereException("Unhandled exception is caught", null, e);
    }

    return true;
  }


  /**
   * Method to handle the identity change of product.
   * 
   * @param oldProduct
   * @param product
   * @param audit
   * @return
   * @throws SevereException
   */
  public boolean changeIdentity(ProductBO oldProduct, ProductBO product, Audit audit) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method changeIdentity(ProductBO oldProduct=" + product + ":ProductBO product=" + product + ":Audit audit=" + audit);

    DomainDetail businessDomain = new DomainDetail();
    product.setLastUpdatedTimestamp(audit.getTime());
    product.setLastUpdatedUser(audit.getUser());

    setValuesToDomain(product, businessDomain);
    // Updating domain table and product main table using transaction
    // support.
    AdapterServicesAccess access = AdapterUtil.getAdapterService();
    long transactionId = AdapterUtil.getTransactionId();
    try
    {
      AdapterUtil.beginTransaction(access);
      AdapterUtil.logBeginTranstn(transactionId,this, "changeIdentity(ProductBO oldProduct, ProductBO product, Audit audit)");
      DomainUtil.persistAssociatedDomains(businessDomain, access);
      adapterManager.updateProduct(product, access);
      adapterManager.refreshQuestionnaire(product, access);
      AdapterUtil.endTransaction(access);
      AdapterUtil.logTheEndTransaction(transactionId,this, "changeIdentity(ProductBO oldProduct, ProductBO product, Audit audit)");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(access);
      AdapterUtil.logAbortTxn(transactionId, this, "changeIdentity(ProductBO oldProduct, ProductBO product, Audit audit)");
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in changeIdentity method in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(access);
      AdapterUtil.logAbortTxn(transactionId, this, "changeIdentity(ProductBO oldProduct, ProductBO product, Audit audit)");
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in changeIdentity method in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(access);
      AdapterUtil.logAbortTxn(transactionId, this, "changeIdentity(ProductBO oldProduct, ProductBO product, Audit audit)");
      throw new SevereException("Unhandled exception is caught", null, e);
    }
    return true;
  }


  /**
   * Method for product locate.
   * 
   * @param productLocateCriteria
   * @param user
   * @return
   * @throws WPDException
   */
  public LocateResults locate(ProductLocateCriteria productLocateCriteria, User user) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering locate(ProductLocateCriteria productLocateCriteria=" + productLocateCriteria + ":User user=" + user);

    LocateResults locateResults = this.adapterManager.locate(productLocateCriteria);
    locateResults.setLatestVersion(true);
    List searchResulList = locateResults.getLocateResults();
    ProductSearchResult productSearchResult;
    if(null != searchResulList){
    for (Iterator iter = searchResulList.iterator(); iter.hasNext(); )
    {
      productSearchResult = (ProductSearchResult) iter.next();
      String description = "";
      if(null != productSearchResult.getProductDescription()){
       if (productSearchResult.getProductDescription().length() > 25) {
          description = productSearchResult.getProductDescription();
          description = description.substring(0, 25)
                  .concat("...");
          productSearchResult.setProductDescription(description);
       }
      }
      productSearchResult.setBusinessDomains(DomainUtil.retrieveAssociatedDomains(BusinessConstants.ENTITY_TYPE_PRODUCT, productSearchResult.getParentKey()));
      List lobList = getDomainDetail(productSearchResult.getBusinessDomains()).getLineOfBusiness();
	  String lobDesc = WPDStringUtil.getTildaString(lobList);
	  productSearchResult.setLineOfBusiness(lobDesc);
    }
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
	DomainDetail domainDetail=new DomainDetail();
	domainDetail.setLineOfBusiness(new ArrayList());
	domainDetail.setBusinessEntity(new ArrayList());
	domainDetail.setBusinessGroup(new ArrayList());
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
	}
	return domainDetail;
}
  
  
  /**
   * Method to persist the BusinessObject ProductBO
   * 
   * @param product
   *            ProductBO
   * @param audit
   *            AuditImpl object from BOM.
   * @param insertFlag
   *            true - insert; false - update.
   * @return
   * @throws WPDException
   */
  public boolean persist(ProductBO product, Audit audit, boolean insertFlag) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering persist(ProductBO product=" + product + ":Audit audit=" + audit + ":boolean insertFlag=" + insertFlag);

    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    long transactionId = AdapterUtil.getTransactionId();
    try
    {
      AdapterUtil.beginTransaction(adapterServicesAccess);
      AdapterUtil.logBeginTranstn(transactionId,this,"persist(ProductBO product, Audit audit, boolean insertFlag)");

      //          Setting values from Audit
      product.setLastUpdatedUser(audit.getUser());
      product.setCreatedUser(audit.getUser());
      product.setCreatedTimestamp(audit.getTime());
      product.setLastUpdatedTimestamp(audit.getTime());

      if (insertFlag)
      {
        // Create new Product
        if (product.getVersion() < BusinessConstants.TWO)
        {
          product.setProductKey(sequenceManager.getNextProductSequence());
          product.setParentProductKey(product.getProductKey());

          this.adapterManager.createProduct(product, adapterServicesAccess);
          DomainDetail businessDomain = new DomainDetail();
          setValuesToDomain(product, businessDomain);
          DomainUtil.persistAssociatedDomains(businessDomain, adapterServicesAccess);

        }
        else
        {

          if (BusinessConstants.CHECKED_OUT.equals(product.getStatus()))
          {
            ProductStructureBO productStructureBO = null;
            int latestValidProductStructureId = 0;
            int latestProductStructureId = 0;
            ProductStructureAdapterManager productStructureAdapterManager = new ProductStructureAdapterManager();
            ProductStructureBO validProductStructureBO = productStructureAdapterManager.validDatedLatestPublishedVersion(product.getProductKey());
            if (null != product.getProductStructureKey())
            {
              productStructureBO = productStructureAdapterManager.retrieveLatestPublishedVersion(Integer.parseInt(product.getProductStructureKey()));
            }
            else
            {
              latestProductStructureId = 0;
            }
            if (null != validProductStructureBO)
            {
              latestValidProductStructureId = validProductStructureBO.getProductStructureId();
            }
            if (null != productStructureBO)
            {
              latestProductStructureId = productStructureBO.getProductStructureId();
            }
            if (latestProductStructureId != 0)
            {
              if (latestProductStructureId == latestValidProductStructureId)
              {
                if (Integer.parseInt(product.getProductStructureKey()) == latestValidProductStructureId)
                {
                  product.setProductKey(sequenceManager.getNextProductSequence());
                  this.adapterManager.createProduct(product, adapterServicesAccess);
                }
                else if ((Integer.parseInt(product.getProductStructureKey()) != latestValidProductStructureId) && latestValidProductStructureId != 0)
                {
                  product.setProductStructureKey(new Integer(latestValidProductStructureId).toString());
                  product.setProductKey(sequenceManager.getNextProductSequence());
                  this.adapterManager.createProduct(product, adapterServicesAccess);
                }
                else if (latestValidProductStructureId == 0)
                {
                  product.setProductStructureKey(null);
                  product.setProductKey(sequenceManager.getNextProductSequence());
                  this.adapterManager.createProduct(product, adapterServicesAccess);
                }

              }
              else
              {
                product.setProductStructureKey(null);
                product.setProductKey(sequenceManager.getNextProductSequence());
                this.adapterManager.createProduct(product, adapterServicesAccess);

              }
            }
            else
            {
              product.setProductStructureKey(null);
              product.setProductKey(sequenceManager.getNextProductSequence());
              this.adapterManager.createProduct(product, adapterServicesAccess);
            }
          }
          else
          {
            product.setProductKey(sequenceManager.getNextProductSequence());
            this.adapterManager.createProduct(product, adapterServicesAccess);
          }
        }
      }
      else
      {
        // Updates Product.
        if ((WebConstants.MNDT_TYPE).equals(product.getProductType()) && (WebConstants.STD_TYPE).equals(product.getHiddenProductType()))
        {
          List adminList = null;
          this.adapterManager.updateProduct(product, adapterServicesAccess);
          adminList = this.adapterManager.getAdminList(product.getProductKey());
          if(null!=adminList){
          Iterator iterAdmin = adminList.iterator();
          while (iterAdmin.hasNext())
          {

            ProductAdminBO productAdminBO = new ProductAdminBO();
            productAdminBO = (ProductAdminBO) iterAdmin.next();
            this.adapterManager.deleteAdmin(productAdminBO, audit.getUser(), adapterServicesAccess);

          }
        }
        }
        else
        {
          this.adapterManager.updateProduct(product, adapterServicesAccess);
        }
      }

      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId,this, "persist(ProductBO product, Audit audit, boolean insertFlag)");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(ProductBO product, Audit audit, boolean insertFlag)");
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method , for persisting the BusinessObject ProductBO, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId, this, "persist(ProductBO product, Audit audit, boolean insertFlag)");
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method , for persisting the BusinessObject ProductBO, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId, this, "persist(ProductBO product, Audit audit, boolean insertFlag)");
      throw new SevereException("Unhandled exception is caught", null, e);
    }


    return true;
  }

	public boolean persistTimeStamp(ProductBO productBO, Audit audit) throws SevereException {
		ProductAdapterManager adapterManager = new ProductAdapterManager();
		try {
			
			productBO.setLastUpdatedUser(audit.getUser());
			productBO.setLastUpdatedTimestamp(audit.getTime());				
			
			this.adapterManager.updateProduct(productBO);
	
		} catch (SevereException ex) {
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persistTimeStamp method , for persisting the BusinessObject productBO, in ProductBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			throw new SevereException("Unhandled exception occured", null, ex);
		}

		return true;
	}

  /**
   * Method to copy while checkout operation.
   * 
   * @param productSource
   * @param productDestn
   * @param audit
   * @return
   * @throws WPDException
   */
  public boolean copyForCheckOut(ProductBO productSource, ProductBO productDestn, Audit audit) throws WPDException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method copyForCheckOut(ProductBO productSource=" + productSource + ":ProductBO productDestn=" + productDestn + ":Audit audit=" + 
                   audit);
    // Change for transactions
    // Create the adapter service access
    AdapterServicesAccess adapterServicesAccess 
					= AdapterUtil.getAdapterService();
    // get the transaction id
    long transactionId = AdapterUtil.getTransactionId();
    // begin the transaction
    
    try {
    	AdapterUtil.beginTransaction(adapterServicesAccess);
        // log the begin transaction
        AdapterUtil.logBeginTranstn
    		(transactionId, this, "copyForCheckOut" +
    				"(ProductBO productSource, " +
    				"ProductBO productDestn, Audit audit)");
/*		this.adapterManager.copyAssociatedComponents
						(productSource.getProductKey(), 
								productDestn.getProductKey(), 
								audit.getUser(), 
								WebConstants.STATUS_CHECK_OUT,
								adapterServicesAccess);*/
		this.adapterManager.checkOutProduct
						(productSource.getProductKey(), 
								productDestn.getProductKey(), 
								audit.getUser(),
								adapterServicesAccess);
	    // end the transaction
	    AdapterUtil.endTransaction(adapterServicesAccess);
	    // log the end transaction
	    AdapterUtil.logTheEndTransaction
					(transactionId, this, 
							"copyForCheckOut" +
							"(ProductBO productSource, " +
							"ProductBO productDestn, Audit audit)");
	    //this.adapterManager.copyAssociatedComponents(productSource.getProductKey(), productDestn.getProductKey(), audit.getUser(), WebConstants.STATUS_CHECK_OUT);
	    // End
		
	} catch (SevereException severeException) {
	      AdapterUtil.abortTransaction(adapterServicesAccess);
	      AdapterUtil.logAbortTxn
	      	(transactionId, this, "copyForCheckOut" +
				"(ProductBO productSource, " +
				"ProductBO productDestn, Audit audit)");
	      List errorParams = new ArrayList(2);
	      String obj = severeException.getClass().getName();
	      errorParams.add(obj);
	      errorParams.add(obj.getClass().getName());
	      throw new SevereException
		  				("Exception occured in copyForCheckOut method , " +
		  						"in ProductBusinessObjectBuilder", 
								errorParams, severeException);
	} catch (AdapterException adapterException) {
		AdapterUtil.abortTransaction(adapterServicesAccess);
	      AdapterUtil.logAbortTxn
	      	(transactionId, this, "copyForCheckOut" +
				"(ProductBO productSource, " +
				"ProductBO productDestn, Audit audit)");
	      List errorParams = new ArrayList(2);
	      String obj = adapterException.getClass().getName();
	      errorParams.add(obj);
	      errorParams.add(obj.getClass().getName());
	      throw new SevereException
		  				("Exception occured in copyForCheckOut method , " +
		  						"in ProductBusinessObjectBuilder", 
								errorParams, adapterException);
	}

    return false;
  }


  /**
   * Copy method for product.
   * 
   * @param productSource
   * @param productDestn
   * @param audit
   * @return
   * @throws WPDException
   */
  public boolean copy(ProductBO productSource, ProductBO productDestn, Audit audit) throws WPDException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method copy(ProductBO productSource=" + productSource + ":ProductBO productDestn=" + productDestn + ":Audit audit=" + audit);
    // Change for transactions
    // Create the adapter service access
    AdapterServicesAccess adapterServicesAccess 
					= AdapterUtil.getAdapterService();
    // get the transaction id
    long transactionId = AdapterUtil.getTransactionId();
    // begin the transaction
    AdapterUtil.beginTransaction(adapterServicesAccess);
    // log the begin transaction
    AdapterUtil.logBeginTranstn
		(transactionId, this, "copy" +
				"(ProductBO productSource, " +
				"ProductBO productDestn, Audit audit)");
    try {
		this.adapterManager.copyAssociatedComponents
						(productSource.getProductKey(), 
								productDestn.getProductKey(), 
								audit.getUser(), 
								WebConstants.STATUS_CHECK_IN,
								adapterServicesAccess);

	    // end the transaction
	    AdapterUtil.endTransaction(adapterServicesAccess);
	    // log the end transaction
	    AdapterUtil.logTheEndTransaction
					(transactionId, this, 
							"copy" +
							"(ProductBO productSource, " +
							"ProductBO productDestn, Audit audit)");
	    //this.adapterManager.copyAssociatedComponents(productSource.getProductKey(), productDestn.getProductKey(), audit.getUser(), WebConstants.STATUS_CHECK_IN);
	    // End		
		
	} catch (SevereException severeException) {
		// abort the transaction
		AdapterUtil.abortTransaction(adapterServicesAccess);
		// log the abort transaction
	    AdapterUtil.logAbortTxn
	      	(transactionId, this, "copy" +
				"(ProductBO productSource, " +
				"ProductBO productDestn, Audit audit)");
	    List errorParams = new ArrayList(2);
	    String obj = severeException.getClass().getName();
	    errorParams.add(obj);
	    errorParams.add(obj.getClass().getName());
	    // throw the severe exception
	    throw new SevereException
		 				("Exception occured in copy method , " +
		  						"in ProductBusinessObjectBuilder", 
								errorParams, severeException);
	} catch (AdapterException adapterException) {
		// abort the transaction
		AdapterUtil.abortTransaction(adapterServicesAccess);
		// log the abort transaction
	    AdapterUtil.logAbortTxn
	      	(transactionId, this, "copy" +
				"(ProductBO productSource, " +
				"ProductBO productDestn, Audit audit)");
	    List errorParams = new ArrayList(2);
	    String obj = adapterException.getClass().getName();
	    errorParams.add(obj);
	    errorParams.add(obj.getClass().getName());
	    // throw the severe exception
	    throw new SevereException
		  				("Exception occured in copy method , " +
		  						"in ProductBusinessObjectBuilder", 
								errorParams, adapterException);
	}

    return false;
  }


  /**
   * Copy method for product.
   * 
   * @param productSource
   * @param productDestn
   * @param audit
   * @return
   * @throws WPDException
   */
  public boolean copy(ProductBO productSource, ProductBO productDestn, Audit audit, HashMap hashMap) throws WPDException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method copy(ProductBO productSource=" + productSource + ":ProductBO productDestn=" + productDestn + ":Audit audit=" + audit);
    // Change for transactions
    // Create the adapter service access
    AdapterServicesAccess adapterServicesAccess 
					= AdapterUtil.getAdapterService();
    // get the transaction id
    long transactionId = AdapterUtil.getTransactionId();
    // begin the transaction
    AdapterUtil.beginTransaction(adapterServicesAccess);
    // log the begin transaction
    AdapterUtil.logBeginTranstn
		(transactionId, this, "copy" +
				"(ProductBO productSource, " +
				"ProductBO productDestn, Audit audit, HashMap hashMap)");
    try {
/*		this.adapterManager.copyAssociatedComponents
						(productSource.getProductKey(), 
								productDestn.getProductKey(), 
								audit.getUser(), 
								WebConstants.STATUS_COPY,
								adapterServicesAccess);*/
		this.adapterManager.copyProduct
						(productSource.getProductKey(), 
								productDestn.getProductKey(), 
								audit.getUser(), 
								adapterServicesAccess);
	    // end the transaction
	    AdapterUtil.endTransaction(adapterServicesAccess);
	    // log the end transaction
	    AdapterUtil.logTheEndTransaction
					(transactionId, this, 
							"copy" +
							"(ProductBO productSource, " +
							"ProductBO productDestn, Audit audit, HashMap hashMap)");
	    //this.adapterManager.copyAssociatedComponents(productSource.getProductKey(), productDestn.getProductKey(), audit.getUser(), WebConstants.STATUS_COPY);
	    // End
		
	} catch (SevereException severeException) {
		// abort the transaction
		AdapterUtil.abortTransaction(adapterServicesAccess);
		// log the abort transaction
	    AdapterUtil.logAbortTxn
	      	(transactionId, this, "copy" +
				"(ProductBO productSource, " +
				"ProductBO productDestn, Audit audit, HashMap hashMap)");
	    List errorParams = new ArrayList(2);
	    String obj = severeException.getClass().getName();
	    errorParams.add(obj);
	    errorParams.add(obj.getClass().getName());
	    // throw the severe exception
	    throw new SevereException
		 				("Exception occured in copy method , " +
		  						"in ProductBusinessObjectBuilder", 
								errorParams, severeException);
	} catch (AdapterException adapterException) {
		// abort the transaction
		AdapterUtil.abortTransaction(adapterServicesAccess);
		// log the abort transaction
	    AdapterUtil.logAbortTxn
	      	(transactionId, this, "copy" +
				"(ProductBO productSource, " +
				"ProductBO productDestn, Audit audit, HashMap hashMap)");
	    List errorParams = new ArrayList(2);
	    String obj = adapterException.getClass().getName();
	    errorParams.add(obj);
	    errorParams.add(obj.getClass().getName());
	    // throw the severe exception
	    throw new SevereException
		  				("Exception occured in copy method , " +
		  						"in ProductBusinessObjectBuilder", 
								errorParams, adapterException);
	}

    return false;
  }


  /**
   * Method to delete product.
   * 
   * @param product
   * @param audit
   * @return
   * @throws SevereException
   */
  public boolean delete(ProductBO product, Audit audit) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method delete(ProductBO product=" + product + ":Audit audit=" + audit);


    try
    {
      product.setLastUpdatedUser(audit.getUser());
      this.adapterManager.deleteProductTier(product);
      this.adapterManager.deleteProduct(product);

    }
    catch (SevereException ex)
    {
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method , for deleting product, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method , for deleting product, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      throw new SevereException("Unhandled exception is caught", null, e);
    }

    return true;
  }


  /**
   * Method to set values from product to domain detail.
   * 
   * @param product
   * @param businessDomain
   */
  private void setValuesToDomain(ProductBO product, DomainDetail businessDomain)
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method setValuesToDomain(ProductBO product=" + product + ":DomainDetail businessDomain=" + businessDomain);

    businessDomain.setEntityType(BusinessConstants.ENTITY_TYPE_PRODUCT);
    businessDomain.setEntityName(product.getProductName());
    businessDomain.setEntitySystemId(product.getProductKey());
    businessDomain.setCreatedUser(product.getCreatedUser());
    businessDomain.setLastUpdatedUser(product.getLastUpdatedUser());
    businessDomain.setCreatedTimeStamp(product.getCreatedTimestamp());
    businessDomain.setLastUpdatedTimeStamp(product.getLastUpdatedTimestamp());
    businessDomain.setEntityParentId(product.getProductKey());
    businessDomain.setDomainList(product.getBusinessDomains());
  }


  /**
   * Persist method for Components.
   * 
   * @param subObject
   * @param mainObject
   * @param audit
   * @param insertFlag
   * @return
   * @throws WPDException
   */
  public boolean persist(ProductComponents subObject, ProductBO mainObject, Audit audit, boolean insertFlag) throws SevereException
  {
    Logger.logInfo("ProductBusinessObjectBuilder - Entering method persist(ProductComponents subObject=" + subObject + ":Audit audit=" + audit + ":boolean insertFlag=" + insertFlag);

    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    long transactionId = AdapterUtil.getTransactionId();
    try
    {
      AdapterUtil.beginTransaction(adapterServicesAccess);
      AdapterUtil.logBeginTranstn(transactionId,this, "persist(ProductComponents subObject, ProductBO mainObject, Audit audit, boolean insertFlag)");
      if (insertFlag)
      {
        int componentId;
        ProductComponentBO productComponentBO = null;
        List newComponentList = subObject.getComponentList();
        // Persisting new Components
        if(null!=newComponentList){
	      for (int i = 0; i < newComponentList.size(); i++) 
	        {
	        
	          productComponentBO = (ProductComponentBO) newComponentList.get(i);
	          componentId = productComponentBO.getComponentKey();
	          productComponentBO.setLastUpdatedUser(audit.getUser());
	          productComponentBO.setCreatedUser(audit.getUser());
	          productComponentBO.setCreatedTimestamp(audit.getTime());
	          productComponentBO.setLastUpdatedTimestamp(audit.getTime());
	          this.adapterManager.addBenefitComponentProc(productComponentBO, audit, adapterServicesAccess);
	          
	        }
      }
      }
      else
      {
        List compList = subObject.getComponentList();
        ProductComponentBO productComponentBO = null;
        if(null!=compList){
        for (int i = 0; i < compList.size(); i++)	
        {

          productComponentBO = (ProductComponentBO) compList.get(i);
          //    			going to update
          productComponentBO.setLastUpdatedUser(audit.getUser());
          productComponentBO.setLastUpdatedTimestamp(audit.getTime());
          this.adapterManager.updateProductBenefitComponent(productComponentBO, adapterServicesAccess);
        }
      }
      }

      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId,this, "persist(ProductComponents subObject, ProductBO mainObject, Audit audit, boolean insertFlag)");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(ProductComponents subObject, ProductBO mainObject, Audit audit, boolean insertFlag)");
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method  for components in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(ProductComponents subObject, ProductBO mainObject, Audit audit, boolean insertFlag)");
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method  for components in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(ProductComponents subObject, ProductBO mainObject, Audit audit, boolean insertFlag)");
      throw new SevereException("Unhandled exception is caught", null, e);
    }
    return true;
  }


  /**
   * This is used to delete every components associated with the product. This
   * will be called when product Structre association changes.
   * 
   * @param productComponents
   * @param productBO
   * @param audit
   * @return
   * @throws SevereException
   */
  public boolean delete(ProductComponents productComponents, ProductBO productBO, Audit audit) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method delete(ProductComponents productComponents=" + productComponents + "ProductBO productBO=" + productBO + ":Audit audit=" + 
                   audit);

    List compList = adapterManager.getBenefitComponentsList(productBO.getProductKey(),false);
    ProductComponentBO productComponentBO = new ProductComponentBO();
    ;

    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    
    long transactionId = AdapterUtil.getTransactionId();
    try
    {
      AdapterUtil.beginTransaction(adapterServicesAccess);
      AdapterUtil.logBeginTranstn(transactionId,this,"delete(ProductComponents productComponents, ProductBO productBO, Audit audit)");
      //          deleteing all the product component from
      if(null!= compList){
      for (int i = 0; i < compList.size(); i++)	
      {
        productComponentBO = (ProductComponentBO) compList.get(i);
        productComponentBO.setProductStructureKey(Integer.parseInt(productBO.getProductStructureKey()));
        productComponentBO.setLastUpdatedUser(audit.getUser());
        adapterManager.deleteBenefitComponent(productComponentBO, adapterServicesAccess);
      }
    }
      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId,this,"delete(ProductComponents productComponents, ProductBO productBO, Audit audit)");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this,"delete(ProductComponents productComponents, ProductBO productBO, Audit audit)");
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method  for deleting every components associated with the product in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this,"delete(ProductComponents productComponents, ProductBO productBO, Audit audit)");
      List errorParams = new ArrayList(2);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method  for deleting every components associated with the product in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this,"delete(ProductComponents productComponents, ProductBO productBO, Audit audit)");
      throw new SevereException("Unhandled exception is caught", null, e);
    }

    return true;
  }


  /**
   * Retrieve method for product.
   * 
   * @param productBusinessObject
   * @return
   * @throws WPDException
   */
  public BusinessObject retrieve(ProductBO productBusinessObject) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method retrieve(ProductBO productBusinessObject=" + productBusinessObject);

    ProductBO productBO = adapterManager.retrieve(productBusinessObject);
    if (null != productBO)
    {
      DomainDetail businessDomain = new DomainDetail();
      setValuesToDomain(productBusinessObject, businessDomain);
      productBO.setBusinessDomains(DomainUtil.retrieveAssociatedDomains(BusinessConstants.ENTITY_TYPE_PRODUCT, productBusinessObject.getParentProductKey()));
    }
    return productBO;
  }


  /**
   * Method to retrieve latest version of product.
   * 
   * @param product
   * @return
   * @throws WPDException
   */
  public BusinessObject retrieveLatestVersion(ProductBO product) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method retrieveLatestVersion(ProductBO product=" + product);

    ProductBO latestVersionProduct = adapterManager.retrieveProductLatestVersion(product);
    if(null!=latestVersionProduct){
    latestVersionProduct.setBusinessDomains(DomainUtil.retrieveAssociatedDomains(BusinessConstants.ENTITY_TYPE_PRODUCT, latestVersionProduct.getParentProductKey()));
    }
 return latestVersionProduct;
  }


  /**
   * Method to retrieve latest version number of product.
   * 
   * @param businessObject
   * @return
   * @throws WPDException
   */
  public int retrieveLatestVersionNumber(ProductBO businessObject) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method retrieveLatestVersionNumber(ProductBO businessObject=" + businessObject);

    return adapterManager.retrieveProductLatestVersionNumber(businessObject);
  }


  /**
   * Method to retrieve Component associated with product.
   * 
   * @param productBusinessObject
   * @return
   * @throws SevereException
   */
  public ProductComponentBO retrieve(ProductComponentBO productBusinessObject) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method retrieve(ProductComponentBO productBusinessObject=" + productBusinessObject);

    ProductComponentBO productComponentBO = adapterManager.retrieve(productBusinessObject);
    return productComponentBO;
  }


  /**
   * Method to retrieve max sequence number of component associated with
   * product.
   * 
   * @param productKey
   * @return
   * @throws SevereException
   */
  public int getNextSequenceForComponent(int productKey) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method getNextSequenceForComponent(int productKey=" + productKey);

    return adapterManager.getNextSequenceForComponent(productKey);
  }


  /**
   * Method to retrieve components associated with product.
   * 
   * @param locateCriteria
   * @param user
   * @return
   * @throws SevereException
   */
  public LocateResults locate(ComponentLocateCriteria locateCriteria, User user) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering locate(ComponentLocateCriteria locateCriteria=" + locateCriteria + ":User user=" + user);

    List list = adapterManager.getBenefitComponentsList(locateCriteria.getProductKey(),locateCriteria.isFlag());
    LocateResults locateResults = new LocateResults();
    if(null!=list)
    locateResults.setLocateResults(list);
    return locateResults;
  }


  public StandardBenefitBO getBenefitGeneralInfo(ProductBenefitGeneralInformationVO productBenefitGeneralInformationVO) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering getBenefitGeneralInfo");

    StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
    StandardBenefitBO standardBenefitBO = new StandardBenefitBO();
    standardBenefitBO.setStandardBenefitKey(productBenefitGeneralInformationVO.getBenefitId());
    standardBenefitBO = (StandardBenefitBO) adapterManager.retrieveSB(standardBenefitBO);
    List domainList = DomainUtil.retrieveAssociatedDomains("stdbenefit", standardBenefitBO.getParentSystemId());//FIXME Remove hardcoded
    standardBenefitBO.setBusinessDomains(domainList);
    UniverseBO universeBO = new UniverseBO();
    universeBO.setStandardBenefitKey(productBenefitGeneralInformationVO.getBenefitId());
    List searchList = adapterManager.searchUniverse(universeBO);
    if (null != searchList)
    {
      if (searchList.size() > 0)
      {
        standardBenefitBO = this.prepareUniverseDisplayList(searchList, standardBenefitBO);
      }
    }
    StandardBenefitDatatypeBO standardBenefitDatatypeBO = new StandardBenefitDatatypeBO();
    standardBenefitDatatypeBO.setStandardBenefitKey(productBenefitGeneralInformationVO.getBenefitId());
    List dataTypeResultList = adapterManager.searchDatatype(standardBenefitDatatypeBO);
    
    ProductRuleIdBO productRuleIdBO = new ProductRuleIdBO(); 
    productRuleIdBO.setBenefitId(productBenefitGeneralInformationVO.getBenefitId());
    productRuleIdBO.setBenefitComponentId(productBenefitGeneralInformationVO.getBenefitComponentId());
    productRuleIdBO.setProductId(productBenefitGeneralInformationVO.getProductId());
    List ruleIdList = getRuleList(productRuleIdBO);
    standardBenefitBO.setRuleIdList(ruleIdList);
    standardBenefitBO.setRuleNameList(productRuleIdBO.getRuleNameList());
    standardBenefitBO.setDataTypeList(dataTypeResultList);
    return standardBenefitBO;
  }


  public LocateResults getNonAdjBenefitMandate(int benefitMasterSystemId, User user) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering getNonAdjBenefitMandate(int benefitMasterSystemId=" + benefitMasterSystemId + ":User user=" + user);

    BenefitMandateLocateCriteria benefitMandateLocateCriteria = new BenefitMandateLocateCriteria();
    benefitMandateLocateCriteria.setBenefitMasterSystemId(benefitMasterSystemId);
    StandardBenefitBusinessObjectBuilder standardBenefitBusinessObjectBuilder = new StandardBenefitBusinessObjectBuilder();
    LocateResults locateResults = standardBenefitBusinessObjectBuilder.locate(benefitMandateLocateCriteria, user);
    return locateResults;
  }

  /**
   * Method to delete Component associated with product.
   * 
   * @param productComponentBO
   * @param productBO
   * @param audit
   * @return
   * @throws SevereException
   */
  public boolean deleteComponent(ProductComponentBO productComponentBO, ProductBO productBO, Audit audit,AdapterServicesAccess adapterServicesAccess) throws SevereException
  {
  	Logger.logInfo("ProductBusinessObjectBuilder - Entering deleteComponent(ProductComponentBO productComponentBO=" + productComponentBO + ":ProductBO productBO=" + productBO + ":Audit audit=" + 
  			audit);
  	boolean isGenProvision = false;
  	int sequenceNum =1;
  	try
	{
   		// gets the product key  
  		int productKey = productComponentBO.getProductKey();
  		//deletes the benefit component
  		productComponentBO.setLastUpdatedUser(audit.getUser());
  		//modified for multiple benefit component Id delete 
  		List benefitComponentDeleteList = productComponentBO.getBenefitComponentDeleteList();
  		if(null!=benefitComponentDeleteList && benefitComponentDeleteList.size()>0){
  			Iterator benefitComponentDeleteListIt = benefitComponentDeleteList.iterator();
  			while(benefitComponentDeleteListIt.hasNext()) {
  				productComponentBO.setComponentKey(Integer.parseInt(benefitComponentDeleteListIt.next().toString()));
  				adapterManager.deleteBenefitComponent(productComponentBO, adapterServicesAccess);
  				adapterManager.deleteBenefitComponentTier(productComponentBO);
  			}
  		}
  		//gets the benefit component list after the deletion of one benefit
  		// component
  		List updatedBenefitCoponentsList = adapterManager.getBenefitComponentsList(productKey,false); 
  		//updates the sequence number of the new benefit componenet list
  		if(null!=updatedBenefitCoponentsList){
  			for (int i = 0; i < updatedBenefitCoponentsList.size(); i++)  { 
  				ProductComponentBO productUpdatedComponentBO = (ProductComponentBO) updatedBenefitCoponentsList.get(i);
  				
  				productUpdatedComponentBO.setSequence(i+1);
  				productUpdatedComponentBO.setLastUpdatedTimestamp(audit
  						.getTime());
  				productUpdatedComponentBO.setLastUpdatedUser(audit
  						.getUser());
  				adapterManager.updateProductBenefitComponent(productUpdatedComponentBO, adapterServicesAccess);
  			}
  		}
	}
  	catch (SevereException ex)
	{
  	 	List errorParams = new ArrayList(3);
  		String obj = ex.getClass().getName();
  		errorParams.add(obj);
  		errorParams.add(obj.getClass().getName());
  		throw new SevereException("Exception occured in delete method for deleting Component associated with product in ProductBusinessObjectBuilder", errorParams, ex);
	}
  	catch (AdapterException ex)
	{
  		List errorParams = new ArrayList(3);
  		String obj = ex.getClass().getName();
  		errorParams.add(obj);
  		errorParams.add(obj.getClass().getName());
  		throw new SevereException("Exception occured in delete method for deleting Component associated with product in ProductBusinessObjectBuilder", errorParams, ex);
	}
  	catch (Exception e)
	{
  	 		throw new SevereException("Unhandled exception is caught", null, e);
	}
  	return true;
  }
    
  
  

  /**
   * Method to delete Component associated with product.
   * 
   * @param productComponentBO
   * @param productBO
   * @param audit
   * @return
   * @throws SevereException
   */
  public boolean delete(ProductComponentBO productComponentBO, ProductBO productBO, Audit audit) throws SevereException
  {
    Logger.logInfo("ProductBusinessObjectBuilder - Entering delete(ProductComponentBO productComponentBO=" + productComponentBO + ":ProductBO productBO=" + productBO + ":Audit audit=" + 
                   audit);

    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    long transactionId = AdapterUtil.getTransactionId();
//    boolean isGenProvision = false;
//    int sequenceNum =1;
    try
    {
      AdapterUtil.beginTransaction(adapterServicesAccess);
      AdapterUtil.logBeginTranstn(transactionId,this,"delete(ProductComponentBO productComponentBO, ProductBO productBO, Audit audit)");
   
      deleteComponent(productComponentBO,productBO,audit,adapterServicesAccess);
     
      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId,this,"delete(ProductComponentBO productComponentBO, ProductBO productBO, Audit audit)");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this,"delete(ProductComponentBO productComponentBO, ProductBO productBO, Audit audit)");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method for deleting Component associated with product in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this,"delete(ProductComponentBO productComponentBO, ProductBO productBO, Audit audit)");
      throw new SevereException("Unhandled exception is caught", null, e);
    }

    return true;
  }


  /**
   * Method to retrieve Overrided Benefit Defenitions.
   * 
   * @param productBenefitDefinitionLocateCriteria
   * @param user
   * @return
   * @throws WPDException
   */
  public LocateResults locate(ProductBenefitDefinitionLocateCriteria productBenefitDefinitionLocateCriteria, User user) throws SevereException
  {
  	
  	

    Logger.logInfo("ProductBusinessObjectBuilder - Entering locate(ProductBenefitDefinitionLocateCriteria productBenefitDefinitionLocateCriteria=" + productBenefitDefinitionLocateCriteria + 
                   ":User user=" + user);
    LocateResults locateResults = new LocateResults();
    List searchResultsList = null;
    try
    {
      if (WebConstants.MANDATE_INFO_VIEW.equals(productBenefitDefinitionLocateCriteria.getType()))
      {
        List benefitMandateBOImplList = new ArrayList();
        List stateList = null;
        List state = null;
        //Create an instance of adapterManager
        NonAdjBenefitMandateAdapterManager nonAdjBenefitMandateAdapterManager = new NonAdjBenefitMandateAdapterManager();
        BenefitMandateBO benefitMandateBOImpl = new BenefitMandateBO();

        benefitMandateBOImpl.setBenefitSystemId(productBenefitDefinitionLocateCriteria.getBenefitId());
        nonAdjBenefitMandateAdapterManager.retrieve(benefitMandateBOImpl);
        productBenefitDefinitionLocateCriteria.setBenefitMandateId(benefitMandateBOImpl.getBenefitMandateId());
        
        BenefitMandateLocateCriteria benefitMandateLocateCriteria = new BenefitMandateLocateCriteria();
        StateLocateCriteria stateLocateCriteria = new StateLocateCriteria();
        benefitMandateLocateCriteria.setBenefitMandateId(benefitMandateBOImpl.getBenefitMandateId());
        LocateResults citationResults = nonAdjBenefitMandateAdapterManager.locateCitationNumber(benefitMandateLocateCriteria);
        List list = citationResults.getLocateResults();
        List citationList = null;
        if(null!=list){
        	citationList = new ArrayList(list.size());
        Iterator iterator = list.iterator();
        while (iterator.hasNext())	
        { 
          CitationNumberBO citationNumberBO = (CitationNumberBO) iterator.next();
          citationList.add(citationNumberBO);
        }
        }
        stateLocateCriteria.setBenefitMandateId(benefitMandateBOImpl.getBenefitMandateId());
        LocateResults stateResults = nonAdjBenefitMandateAdapterManager.locateStateObject(stateLocateCriteria);
        if(null!=stateResults.getLocateResults()){
        stateList = stateResults.getLocateResults();
        }
        if(null != stateList){
        	state = new ArrayList(stateList.size());
        Iterator stateIterator = stateList.iterator();
        while (stateIterator.hasNext())
        {
          StateBO stateBO = (StateBO) stateIterator.next();
          state.add(stateBO);
        }
        }
        benefitMandateBOImpl.setCitationNumberList(citationList);
        benefitMandateBOImpl.setBenefitStateCodeList(state);
        benefitMandateBOImplList.add(0, benefitMandateBOImpl);
        locateResults.setLocateResults(benefitMandateBOImplList);
      }
      else if("Criteria".equals(productBenefitDefinitionLocateCriteria.getType())){
      	BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
        searchResultsList =benefitAdapterManager.getAllOverridedBenefitLines("Criteria", productBenefitDefinitionLocateCriteria.getProductId(), 
                productBenefitDefinitionLocateCriteria.getBenefitId(), 
                productBenefitDefinitionLocateCriteria.getBenefitComponentId(), 
                productBenefitDefinitionLocateCriteria.getBenefitLevelHideFlag(), 
                productBenefitDefinitionLocateCriteria.getBenefitLineHideFlag());
        //searchBenefitTermsQualifierDescForCorrespondingBenefitLevels(searchResultsList);
        if(null!=searchResultsList)
        locateResults.setLocateResults(searchResultsList);
      	
      }else if("Level".equals(productBenefitDefinitionLocateCriteria.getType())){
      	BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
        searchResultsList =benefitAdapterManager.getBenefitLinesAndLines(productBenefitDefinitionLocateCriteria.getTierSysIdList(),
        		productBenefitDefinitionLocateCriteria.getProductId(), productBenefitDefinitionLocateCriteria.getBenefitComponentId(), 
				productBenefitDefinitionLocateCriteria.getType());
//        searchBenefitTermsQualifierDescForCorrespondingBenefitLevels(searchResultsList);
        if(null!=searchResultsList)
        locateResults.setLocateResults(searchResultsList);
      }
      else
      {
        BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
        searchResultsList =benefitAdapterManager.getAllOverridedBenefitLines("product", productBenefitDefinitionLocateCriteria.getProductId(), 
                productBenefitDefinitionLocateCriteria.getBenefitId(), 
                productBenefitDefinitionLocateCriteria.getBenefitComponentId(), 
                productBenefitDefinitionLocateCriteria.getBenefitLevelHideFlag(), 
                productBenefitDefinitionLocateCriteria.getBenefitLineHideFlag());
        //-- Commented for stabilization performance improvement --
        //searchBenefitTermsQualifierDescForCorrespondingBenefitLevels(searchResultsList);
        if(null!=searchResultsList)
        locateResults.setLocateResults(searchResultsList);
       

      }											
    }
    catch (SevereException se)
    {
      List errorParams = new ArrayList(3);
      String obj = se.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in locate ProductBenefitDefinitionLocateCriteria method in ProductBusinessObjectBuilder", errorParams, se);
    }
    catch (AdapterException ae)
    {
      List errorParams = new ArrayList(3);
      String obj = ae.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in locate ProductBenefitDefinitionLocateCriteria method in ProductBusinessObjectBuilder", errorParams, ae);
    }
    catch (Exception ex)
    {
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in locate ProductBenefitDefinitionLocateCriteria method in ProductBusinessObjectBuilder", null, ex);
    }
    
    return locateResults;

  }
  private void searchBenefitTermsQualifierDescForCorrespondingBenefitLevels(

        List searchResultsList) throws SevereException {
  	
  	/*TimeHandler th = new TimeHandler();
    Logger.logInfo(th.startPerfLogging("U23914_Product Coverage","ProductBusinessObjectBuilder","searchBenefitTermsQualifierDescForCorrespondingBenefitLevels"));*/


    List benefitTermsSearchResultsList = null;

    LocateResults searchResults = null;

    BenefitlevelAdapterManager adapterManager = new BenefitlevelAdapterManager();

    if (null != searchResultsList) {

        for (int i = 0; i < searchResultsList.size(); i++) {

                    BenefitLine benefitLine = (BenefitLine) searchResultsList

                    .get(i);

            BenefitLevelLocateCriteria benefitLevelLocateCriteria = new BenefitLevelLocateCriteria();

            benefitLevelLocateCriteria.setBenefitLevelId(benefitLine.getLevelSysId());

            benefitTermsSearchResultsList = new ArrayList();

            try {

                // get the benefitTermCodes for the benefit level

                String benefitTermCodes = benefitLine.getTermCode();

                if(null != benefitTermCodes){

                            StringTokenizer benefitTerms = new StringTokenizer(

                                    benefitTermCodes, ",");

                            int noOfTokens = benefitTerms.countTokens();

                            if(noOfTokens > 1){

                                StringBuffer strBuffer = new StringBuffer();

                                        for (int j = 0; j < noOfTokens; j++) {

                                            if (benefitTerms.hasMoreTokens()) {

                                                        

                                                String benefitTerm = benefitTerms.nextToken();

                                                benefitLevelLocateCriteria

                                                        .setBenefitTerm(benefitTerm);

                                                searchResults = adapterManager

                                                        .locateBenefitTermsDesc(benefitLevelLocateCriteria);

                                                List benefitTermDesc = searchResults

                                                        .getLocateResults();

                                                

                                                if (null != benefitTermDesc

                                                        && benefitTermDesc.size() > 0) {

                                                        if(j != 0){

                                                                                strBuffer.append(",\n");

                                                                    }
                                                        BenefitTermBO benefitTermBO =(BenefitTermBO) benefitTermDesc.get(0);
                                                    strBuffer.append(benefitTermBO.getBenefitTerm());

                                                }

                                            }

                                        }

                                        benefitLine.setTermDesc(strBuffer.toString());

                            }else if(noOfTokens == 1){

                               

                            }

                }

                

                String benefitQualCodes = benefitLine.getQualifierCode();

                                if(null != benefitQualCodes && !"0".equals(benefitQualCodes)){

                                    StringTokenizer benefitQualifiers = new StringTokenizer(

                                                        benefitQualCodes, ",");

                                    int noOfTokens = benefitQualifiers.countTokens();

                                    if(noOfTokens > 1){

                                            StringBuffer strBufferQual = new StringBuffer();

                                        for (int k = 0; k < noOfTokens; k++) {

                                            if (benefitQualifiers.hasMoreTokens()) {

                                                String benefitQual = benefitQualifiers.nextToken();

                                                benefitLevelLocateCriteria

                                                        .setBenefitQualifier(benefitQual);

                                                searchResults = adapterManager

                                                        .locateBenefitQualifiersDesc(benefitLevelLocateCriteria);

                                                List benefitQualDesc = searchResults

                                                        .getLocateResults();

                                                if (null != benefitQualDesc

                                                        && benefitQualDesc.size() > 0) {

                                                        if(k != 0){

                                                                    strBufferQual.append(",\n");

                                                                    }
                                                        BenefitQualifierBO benefitQualifierBO = (BenefitQualifierBO)benefitQualDesc.get(0);
                                                        strBufferQual.append(benefitQualifierBO.getBenefitQualifier());

                                                }

                                            }

                                        }

                                        benefitLine.setQualifierDesc(strBufferQual.toString());

                                    }else if(noOfTokens == 1){

                                      

                                    }

                                }

                

            } catch (Exception ex) {

                    Logger.logError(ex);

                List logParameters = new ArrayList(3);

                String logMessage = "Failed while processing searchBenefitTermDesc";

                throw new ServiceException(logMessage, logParameters, ex);

            }

            

        }//for loop end
    //Logger.logInfo(th.endPerfLogging());
    }

}
  
  
  private void searchBenefitTermsQualifierDescForCorrespondingBenefitLevelsForTier(

        List searchResultsList) throws SevereException {

    List benefitTermsSearchResultsList = null;

    LocateResults searchResults = null;

    BenefitlevelAdapterManager adapterManager = new BenefitlevelAdapterManager();

    if (null != searchResultsList) {

        for (int i = 0; i < searchResultsList.size(); i++) {

        	TierDefinitionBO benefitLine = (TierDefinitionBO) searchResultsList

                    .get(i);

            BenefitLevelLocateCriteria benefitLevelLocateCriteria = new BenefitLevelLocateCriteria();

            benefitLevelLocateCriteria.setBenefitLevelId(benefitLine.getLevelSysId());

            benefitTermsSearchResultsList = new ArrayList();

            try {

                // get the benefitTermCodes for the benefit level

                String benefitTermCodes = benefitLine.getBnftLvlTerm();

                if(null != benefitTermCodes){

                            StringTokenizer benefitTerms = new StringTokenizer(

                                    benefitTermCodes, ",");

                            int noOfTokens = benefitTerms.countTokens();

                            if(noOfTokens > 1){

                                StringBuffer strBuffer = new StringBuffer();

                                        for (int j = 0; j < noOfTokens; j++) {

                                            if (benefitTerms.hasMoreTokens()) {

                                                        

                                                String benefitTerm = benefitTerms.nextToken();

                                                benefitLevelLocateCriteria

                                                        .setBenefitTerm(benefitTerm);

                                                searchResults = adapterManager

                                                        .locateBenefitTermsDesc(benefitLevelLocateCriteria);

                                                List benefitTermDesc = searchResults

                                                        .getLocateResults();

                                                

                                                if (null != benefitTermDesc

                                                        && benefitTermDesc.size() > 0) {

                                                        if(j != 0){

                                                                                strBuffer.append(",\n");

                                                                    }
                                                        BenefitTermBO benefitTermBO =(BenefitTermBO) benefitTermDesc.get(0);
                                                    strBuffer.append(benefitTermBO.getBenefitTerm());

                                                }

                                            }

                                        }

                                        benefitLine.setLvlTermDesc(strBuffer.toString());

                            }else if(noOfTokens == 1){

                               

                            }

                }

                

                String benefitQualCodes = benefitLine.getBnftLvlQual();

                                if(null != benefitQualCodes && !"0".equals(benefitQualCodes)){

                                    StringTokenizer benefitQualifiers = new StringTokenizer(

                                                        benefitQualCodes, ",");

                                    int noOfTokens = benefitQualifiers.countTokens();

                                    if(noOfTokens > 1){

                                            StringBuffer strBufferQual = new StringBuffer();

                                        for (int k = 0; k < noOfTokens; k++) {

                                            if (benefitQualifiers.hasMoreTokens()) {

                                                String benefitQual = benefitQualifiers.nextToken();

                                                benefitLevelLocateCriteria

                                                        .setBenefitQualifier(benefitQual);

                                                searchResults = adapterManager

                                                        .locateBenefitQualifiersDesc(benefitLevelLocateCriteria);

                                                List benefitQualDesc = searchResults

                                                        .getLocateResults();

                                                if (null != benefitQualDesc

                                                        && benefitQualDesc.size() > 0) {

                                                        if(k != 0){

                                                                    strBufferQual.append(",\n");

                                                                    }
                                                        BenefitQualifierBO benefitQualifierBO = (BenefitQualifierBO)benefitQualDesc.get(0);
                                                        strBufferQual.append(benefitQualifierBO.getBenefitQualifier());

                                                }

                                            }

                                        }

                                        benefitLine.setLevelQualDesc(strBufferQual.toString());

                                    }else if(noOfTokens == 1){

                                      

                                    }

                                }

                

            } catch (Exception ex) {

                    Logger.logError(ex);

                List logParameters = new ArrayList(3);

                String logMessage = "Failed while processing searchBenefitTermDesc";

                throw new ServiceException(logMessage, logParameters, ex);

            }

            

        }//for loop end

    }

}


  /**
   * This method updates the benefitline values
   * 
   * @param subObject
   * @param mainObject
   * @param audit
   * @param insertFlag
   * @return
   * @throws WPDException
   */
  public  boolean persist(ProductBenefitDefinitions subObject, ProductBO mainObject, Audit audit, boolean insertFlag) throws SevereException
  {
  	TimeHandler th = new TimeHandler();
    Logger.logInfo(th.startPerfLogging("U23914_Product Coverage","ProductBusinessObjectBuilder","persist"));


    Logger.logInfo("ProductBusinessObjectBuilder - Entering persist(ProductBenefitDefinitions subObject=" + subObject + ":ProductBO mainObject=" + mainObject + ":Audit audit=" + audit + 
                   ":boolean insertFlag=" + insertFlag);

    BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
    List updatedBenefitLineList = subObject.getUpdatedBenefitLines();
    ProductBenefitCustomizedBO productCustomizedB0; 
    int bnftComponentId = subObject.getBenefitComponentId();

    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    long transactionId = AdapterUtil.getTransactionId();
    try
    {
      AdapterUtil.beginTransaction(adapterServicesAccess);
      AdapterUtil.logBeginTranstn(transactionId,this, "persist(ProductBenefitDefinitions subObject,ProductBO mainObject, Audit audit, boolean insertFlag)");
      if (updatedBenefitLineList.size() != 0) 
      {
        for (int i = 0; i < updatedBenefitLineList.size(); i++)
        {

          productCustomizedB0 = (ProductBenefitCustomizedBO) updatedBenefitLineList.get(i);
          productCustomizedB0.setLastUpdatedUser(audit.getUser());
          productCustomizedB0.setCreatedUser(audit.getUser());
          productCustomizedB0.setLastUpdatedTimestamp(audit.getTime());
          productCustomizedB0.setProductSysId(mainObject.getProductKey());
          productCustomizedB0.setBenefitComponentSysId(bnftComponentId);
          productCustomizedB0.setBenefitSysId(subObject.getBenefitId());
          //passes each individual benefit line for updation
          benefitAdapterManager.updateDefenitoinOverridenValue(productCustomizedB0, adapterServicesAccess);
          if (productCustomizedB0.getBenefitHideFlag().equals("T")) //FIXME Remove hardcoded
          {
            BenefitLine benefitLine = new BenefitLine();
            benefitLine.setLastUpdatedUser(audit.getUser());
            benefitLine.setLastUpdatedTimestamp(audit.getTime());
            benefitLine.setBenefitComponentId(bnftComponentId);
            benefitLine.setBenefitSysId(subObject.getBenefitId());
            benefitLine.setEntitySysId(mainObject.getProductKey());
            benefitLine.setBenefitHide(productCustomizedB0.getBenefitHideFlag());
            benefitLine.setCustomizedSysId(productCustomizedB0.getProductBenefitCustomizedSysId());
            benefitAdapterManager.updateDefenitoinOverridenValue(benefitLine, adapterServicesAccess);
          }

        }
        //ProductAdapterManager adapterManager = new ProductAdapterManager();
        //adapterManager.deleteHiddenTierLinesAndLevels(mainObject.getProductKey(),bnftComponentId,subObject.getBenefitId(),adapterServicesAccess);     
      }

     /* if(null != subObject.getBenefitTierDefinitionList()){
    	  BenefitTierBindingObject tierObject = null;
    	  for (Iterator defIterator = subObject.getBenefitTierDefinitionList().iterator(); defIterator.hasNext();) {
    		  
      		BenefitTierDefinition tierDefinition = (BenefitTierDefinition) defIterator.next();
      		for (Iterator tierBoIterator = tierDefinition.getBenefitTiers().iterator(); tierBoIterator.hasNext();) {
  				BenefitTier benefitTier = (BenefitTier) tierBoIterator.next();
  				for (Iterator criteriaIterator = benefitTier.getBenefitTierCriteriaList().iterator(); criteriaIterator
  						.hasNext();) {
  					
  					BenefitTierCriteria tierCriteria = (BenefitTierCriteria) criteriaIterator.next();
  					tierObject = new BenefitTierBindingObject();
  					tierObject.setTierDefinitionId(tierDefinition.getBenefitTierDefinitionSysId());
  					tierObject.setBenefitTierId(benefitTier.getBenefitTierSysId());
  					tierObject.setTierCriteriaId(tierCriteria.getBenefitTierCriteriaSysId());
  					tierObject.setCriteriaValue(tierCriteria.getBenefitTierCriteriaValue());
  					tierObject.setLastUpdatedTimestamp(audit.getTime());
  					tierObject.setLastUpdatedUser(audit.getUser());
  					System.out.println("Tier criteria executing ------");
  					adapterManager.updateBenefitTierDetail(tierObject, audit, adapterServicesAccess);
  					System.out.println("After Tier criteria executing ------");
  				}				
  			}			
  		}       	
      	
      }*/
      /*if(null != subObject.getBenefitTierLevelList()){
    	  BenefitTierBindingObject tierObject = null;
    	  for (Iterator benLevelItr = subObject.getBenefitTierLevelList().iterator(); benLevelItr.hasNext();) {
    			BenefitLevel benefitLevel = (BenefitLevel) benLevelItr.next();
    			for (Iterator benLineItr = benefitLevel.getBenefitLines().iterator(); benLineItr.hasNext();) {
    				BenefitLine benefitLine = (BenefitLine) benLineItr.next();
    				tierObject = new BenefitTierBindingObject();
    				tierObject.setBenefitLineId(benefitLine.getLineSysId());
    				tierObject.setBenefitLevelId(benefitLevel.getLevelId());
    				tierObject.setLineValue(benefitLine.getLineValue());
    				tierObject.setBenefitTierId(benefitLevel.getTierSysId());
    				tierObject.setLastUpdatedTimestamp(audit.getTime());
  					tierObject.setLastUpdatedUser(audit.getUser());
  					//CARS START
  					//Setting frequency value to the tier object
  					tierObject.setFrequencyValue(benefitLevel.getFrequencyId());
  					//Setting level description value to the tier object
  					tierObject.setLevelDescription(benefitLevel.getLevelDesc());
  					//CARS END
  					System.out.println("Tier level executing ------");
    				adapterManager.updateBenefitTierDetail(tierObject, audit, adapterServicesAccess);
    				System.out.println("After Tier level executing ------");
    			}
    		} 	
    	  adapterManager.deleteHiddenTierLinesAndLevels(mainObject.getProductKey(),bnftComponentId,subObject.getBenefitId(),adapterServicesAccess);
      }*/
      if(null != subObject.getBenefitTierDefinitionList()){
      	int size = subObject.getBenefitTierDefinitionList().size();
      	BenefitTierBindingObject tierObject = null;
      	
      	for(int i=0;i<size;i++){
      		tierObject = (BenefitTierBindingObject) subObject.getBenefitTierDefinitionList().get(i);
      		tierObject.setLastUpdatedTimestamp(audit.getTime());
      		tierObject.setLastUpdatedUser(audit.getUser());
      		adapterManager.updateBenefitTierDetail(tierObject, audit, adapterServicesAccess);      		
      	}
      }
      
      if(null != subObject.getBenefitTierLevelList()){
      	int size = subObject.getBenefitTierLevelList().size();
      	BenefitTierBindingObject tierObject = null;
      	
      	for(int i=0;i<size;i++){
      		tierObject = (BenefitTierBindingObject) subObject.getBenefitTierLevelList().get(i);
      		tierObject.setLastUpdatedTimestamp(audit.getTime());
      		tierObject.setLastUpdatedUser(audit.getUser());
      		adapterManager.updateBenefitTierDetail(tierObject, audit, adapterServicesAccess);
      		
      	}	
      	adapterManager.deleteHiddenTierLinesAndLevels(mainObject.getProductKey(),bnftComponentId,subObject.getBenefitId(),adapterServicesAccess);
      }
      
      
      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId,this, "persist(ProductBenefitDefinitions subObject,ProductBO mainObject, Audit audit, boolean insertFlag)");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(ProductBenefitDefinitions subObject,ProductBO mainObject, Audit audit, boolean insertFlag)");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method for updating the benefitline values in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(ProductBenefitDefinitions subObject,ProductBO mainObject, Audit audit, boolean insertFlag)");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method for updating the benefitline values in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(ProductBenefitDefinitions subObject,ProductBO mainObject, Audit audit, boolean insertFlag)");
      throw new SevereException("Unhandled exception is caught", null, e);
    }
    
    Logger.logInfo(th.endPerfLogging());
    return true;
  }


  /**
   * Method to delete benefit levels.
   * 
   * @param subObject
   * @param mainObject
   * @param audit
   * @return
   * @throws SevereException
   * @throws AdapterException
   */
  public boolean delete(ProductBenefitDefinitions subObject, ProductBO mainObject, Audit audit) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering delete(ProductBenefitDefinitions subObject=" + subObject + ":ProductBO mainObject=" + mainObject + ":Audit audit=" + audit);

    BenefitAdapterManager benefitAdapterManager = new BenefitAdapterManager();
    List benefitLevelsToBeDeleted = subObject.getDeletedBenefitLevels();
    int bnftComponentId = subObject.getBenefitComponentId();
    BenefitLine benefitLineBO = null;
    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    long transactionId = AdapterUtil.getTransactionId();
    try
    {
      AdapterUtil.beginTransaction(adapterServicesAccess);
      AdapterUtil.logBeginTranstn(transactionId,this,"delete(ProductBenefitDefinitions subObject,ProductBO mainObject, Audit audit)");
      for (int i = 0; i < benefitLevelsToBeDeleted.size(); i++)
      {
        benefitLineBO = (BenefitLine) benefitLevelsToBeDeleted.get(i);
        benefitLineBO.setEntityType(BusinessConstants.ENTITY_TYPE_PRODUCT);
        benefitLineBO.setEntitySysId(mainObject.getProductKey());
        benefitLineBO.setBenefitComponentId(bnftComponentId);
        benefitAdapterManager.deleteBenefitDefinitionLevel(benefitLineBO, audit.getUser(), adapterServicesAccess);

      }
      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId,this,"delete(ProductBenefitDefinitions subObject,ProductBO mainObject, Audit audit)");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this,"delete(ProductBenefitDefinitions subObject,ProductBO mainObject, Audit audit)");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method for deleting benefit levels in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this,"delete(ProductBenefitDefinitions subObject,ProductBO mainObject, Audit audit)");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method for deleting benefit levels in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this,"delete(ProductBenefitDefinitions subObject,ProductBO mainObject, Audit audit)");
      throw new SevereException("Unhandled exception is caught", null, e);
    }
    return true;
  }


  /**
   * Method to retrieve all versions of product.
   * 
   * @param productLocatePreviousVersionsCriteria
   * @param user
   * @return
   * @throws WPDException
   */
  public LocateResults locate(ProductLocatePreviousVersionsCriteria productLocatePreviousVersionsCriteria, User user) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering locate(ProductLocatePreviousVersionsCriteria productLocatePreviousVersionsCriteria=" + productLocatePreviousVersionsCriteria + 
                   ":User user=" + user);

    LocateResults locateResultsAllVersions = new LocateResults();
    locateResultsAllVersions.setLocateResults(adapterManager.getAllProductVersions(productLocatePreviousVersionsCriteria.getProductId()));
    List searchResulList = locateResultsAllVersions.getLocateResults();
    ProductSearchResult productSearchResult;
    if(null!=searchResulList){
    for (Iterator iter = searchResulList.iterator(); iter.hasNext(); ) 
    {
      productSearchResult = (ProductSearchResult) iter.next();
      String description = "";
      if(null != productSearchResult.getProductDescription()){
       if (productSearchResult.getProductDescription().length() > 25) {
          description = productSearchResult.getProductDescription();
          description = description.substring(0, 25)
                  .concat("...");
          productSearchResult.setProductDescription(description);
       }
      }
      List domainList = DomainUtil.retrieveAssociatedDomains(BusinessConstants.ENTITY_TYPE_PRODUCT, productSearchResult.getParentKey());
      productSearchResult.setBusinessDomains(domainList);
      List lobList = DomainUtil.getLineOfBusiness(BusinessConstants.ENTITY_TYPE_PRODUCT, productSearchResult.getParentKey());
      String lobDesc = WPDStringUtil.getTildaString(lobList);
      productSearchResult.setLineOfBusiness(lobDesc);
    }
    }
    return locateResultsAllVersions;
  }


  /**
   * This method fetches the benefitComponentBO and adds it to the ProductBO
   * 
   * @param productBO
   * @param params
   * @return
   * @throws WPDException
   */
  public ProductBO retrieve(ProductBO productBO, Map params) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering retrieve(ProductBO productBO=" + productBO + ":Map params=" + params);
    try
    {
      if (params.get(WebConstants.BENEFIT_COMP_ID) != null)
      {
        int id = ((Integer) params.get(WebConstants.BENEFIT_COMP_ID)).intValue();
        BenefitComponentAdapterManager adapterManger = new BenefitComponentAdapterManager();
        BenefitComponentBO benefitComponentBO = new BenefitComponentBO();
        benefitComponentBO.setBenefitComponentId(id);
        benefitComponentBO = (BenefitComponentBO) adapterManger.retrieveBenefitComponent(benefitComponentBO);
        getRule(id,productBO.getProductKey(),benefitComponentBO);
        //calling methode to get rule id
        //getReferenceList(benefitComponentBO);
        List benefitComponentList = new ArrayList(2);
        benefitComponentList.add(benefitComponentBO);
        productBO.setComponentList(benefitComponentList);
      }
      if (null != params.get("action") && params.get("action").equals("retrieveDenialAndExclusion"))//FIXME Remove hardcoded
      {
        this.adapterManager.getProductRulesList(productBO, params.get(WebConstants.RULE_TYPE).toString(),(Integer)params.get(WebConstants.PAGE_NO));
      }
    }
    catch (SevereException se)
    {
      List errorParams = new ArrayList(3);
      String obj = se.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in retrieve ProductBO method in ProductBusinessObjectBuilder", errorParams, se);
    }
    catch (Exception ex)
    {
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in retrieve ProductBO method in ProductBusinessObjectBuilder", null, ex);
    }
    return productBO;
  }


  /**
   * To locate the attached Notes for the Product.
   * 
   * @param locateCriteria
   * @return LocateResults
   * @throws WPDException
   */
  public LocateResults locate(ProductNotesLocateCriteria locateCriteria, User user) throws SevereException
  {
    NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();
    int entityId = locateCriteria.getEntityId();
    String entityType = locateCriteria.getEntityType();
    try
    {
      return notesAttachmentAdapterManager.locateAttachedNotes(entityId, entityType, BusinessConstants.BENEFIT_SYS_ID);
    }
    catch (SevereException se)
    {
      List errorParams = new ArrayList(3);
      String obj = se.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in locate ProductNotesLocateCriteria method in ProductBusinessObjectBuilder", errorParams, se);
    }
    catch (AdapterException ae)
    {
      List errorParams = new ArrayList(3);
      String obj = ae.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in locate ProductNotesLocateCriteria method in ProductBusinessObjectBuilder", errorParams, ae);
    }
    catch (Exception ex)
    {
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in locate ProductNotesLocateCriteria method in ProductBusinessObjectBuilder", null, ex);
    }
  }


  /**
   * To attach Note for a Product.
   * 
   * @param subObject
   * @param mainObject
   * @param audit
   * @param insertFlag
   * @return
   * @throws WPDException
   */
  public boolean persist(ProductNotes subObject, ProductBO mainObject, Audit audit, boolean insertFlag) throws SevereException
  {
    NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();
    int action = subObject.getAction();
    Logger.logInfo("ProductBusinessObjectBuilder - Entering method persist(ProductComponents subObject=" + subObject + ":Audit audit=" + audit + ":boolean insertFlag=" + insertFlag);

    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    long transactionId = AdapterUtil.getTransactionId();
    try
    {
      List noteList = subObject.getNoteList();
      if (noteList == null || noteList.size() == 0)
        return true;  
      AdapterUtil.beginTransaction(adapterServicesAccess);
      AdapterUtil.logBeginTranstn(transactionId,this, "persist(ProductNotes subObject, ProductBO mainObject, Audit audit, boolean insertFlag"); //REMOVE Hardcoded
      if (insertFlag)
      {
        if (action == 1)
        {
          AttachedNotesBO attachedNotesBO = new AttachedNotesBO();
      
          // Persisting new Note
          for (int i = 0; i < noteList.size(); i++)
          {
            attachedNotesBO = (AttachedNotesBO) noteList.get(i);
            notesAttachmentAdapterManager.attachNotesForEntity(attachedNotesBO, audit, true, adapterServicesAccess);
          }
        }
        else
        {
          NotesAttachmentOverrideBO notesAttachmentOverrideBO = new NotesAttachmentOverrideBO();
          // Persisting new Note
          for (int i = 0; i < noteList.size(); i++)
          {

            notesAttachmentOverrideBO = (NotesAttachmentOverrideBO) noteList.get(i);
            notesAttachmentAdapterManager.attachNotesForOverrideEntity(notesAttachmentOverrideBO, audit, false, adapterServicesAccess);

          }
        }
      }

      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId,this, "persist(ProductNotes subObject, ProductBO mainObject, Audit audit, boolean insertFlag");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(ProductNotes subObject, ProductBO mainObject, Audit audit, boolean insertFlag");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method for attaching notes to a product in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(ProductNotes subObject, ProductBO mainObject, Audit audit, boolean insertFlag");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method for attaching notes to a product in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(ProductNotes subObject, ProductBO mainObject, Audit audit, boolean insertFlag");
      throw new SevereException("Unhandled exception is caught", null, e);
    }

    return true;

  }
/*
 * this metode for deleting note attached to the product
 * @ inputs AttachedNotesBO
 * return boolean
 */
  public boolean delete(AttachedNotesBO subObject, ProductBO mainObject, Audit audit) throws SevereException
  {
    NotesAttachmentAdapterManager attachedNotesAdapterManager = new NotesAttachmentAdapterManager();


    try
    {

      attachedNotesAdapterManager.deleteNotesForEntity(subObject, audit);


    }
    catch (SevereException ex)
    {

      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Delete failed for AttachedNotesBO delete in ProductBuisnessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {

      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Delete failed for AttachedNotesBO delete in ProductBuisnessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {

      throw new SevereException("Unhandled exception is caught", null, e);
    }

    return true;
  }

  /**
   * To locate the overided notes of benefit component for the Product.
   * @param productComponentNotesLocateCriteria
   * @param user
   * @return
   * @throws SevereException
   */
  public LocateResults locate(ProductComponentNotesLocateCriteria productComponentNotesLocateCriteria, User user) throws SevereException
  {

    NotesAttachmentAdapterManager notesAttachmentAdapterManager = new NotesAttachmentAdapterManager();
    try
    {
      int primaryEntityId = productComponentNotesLocateCriteria.getPrimaryEntityId();
      String primaryEntityType = productComponentNotesLocateCriteria.getPrimaryEntityType();
      int secondaryEntityId = productComponentNotesLocateCriteria.getSecondaryEntityId();
      String secondaryEntityType = productComponentNotesLocateCriteria.getSecondaryEntityType();
      int benefitComponentId = productComponentNotesLocateCriteria.getBenefitComponentId();
      int tierSysId = productComponentNotesLocateCriteria.getTierSysId();
      return notesAttachmentAdapterManager.locateAttachedNotesForOverride(primaryEntityId, primaryEntityType, secondaryEntityId, secondaryEntityType, benefitComponentId,tierSysId);
    }
    catch (SevereException se)
    {
      List errorParams = new ArrayList(3);
      String obj = se.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in locate ProductComponentNotesLocateCriteria method in ProductBusinessObjectBuilder", errorParams, se);
    }
    catch (AdapterException ae)
    {
      List errorParams = new ArrayList(3);
      String obj = ae.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in locate ProductComponentNotesLocateCriteria method in ProductBusinessObjectBuilder", errorParams, ae);
    }
    catch (Exception ex)
    {
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in locate ProductComponentNotesLocateCriteria method in ProductBusinessObjectBuilder", null, ex);
    }

  }


  /**
   * To unattach the overided notes of benefit component for the Product.
   * 
   * @throws WPDException
   */
  public boolean delete(NotesAttachmentOverrideBO subObject, ProductBO mainObject, Audit audit) throws SevereException
  {
    NotesAttachmentAdapterManager attachedNotesAdapterManager = new NotesAttachmentAdapterManager();

    try
    {
      attachedNotesAdapterManager.deleteNotesForOverriddenEntity(subObject, audit);

    }
    catch (SevereException ex)
    {
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method , for unattaching the overided notes of benefit component for the Product, in ProductBusinessObjectBuilder", errorParams, 
                                ex);
    }
    catch (AdapterException ex)
    {
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method , for unattaching the overided notes of benefit component for the Product, in ProductBusinessObjectBuilder", errorParams, 
                                ex);
    }
    catch (Exception e)
    {
      throw new SevereException("Unhandled exception is caught", null, e);
    }

    return true;
  }


  /**
   * To delte the notes associated to a benefitcomponent and benefit while
   * deleting a benefitcomponent associated to a product.
   * 
   * @throws WPDException
   */
  public boolean delete(NotesAttachmentDomainOverrideBO subObject, ProductBO mainObject, Audit audit) throws SevereException
  {
    NotesAttachmentAdapterManager attachedNotesAdapterManager = new NotesAttachmentAdapterManager();

    /*AdapterServicesAccess is used here because the same adapter method is used by a different bulder
        which involves multiple transactions*/
    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    long transactionId = AdapterUtil.getTransactionId();
    try
    {
      AdapterUtil.beginTransaction(adapterServicesAccess);
      AdapterUtil.logBeginTranstn(transactionId ,this, "delete(NotesAttachmentDomainOverrideBO subObject, ProductBO mainObject, Audit audit");

      attachedNotesAdapterManager.deleteComponentNotesOverridden(subObject, audit, adapterServicesAccess);

      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId ,this, "delete(NotesAttachmentDomainOverrideBO subObject, ProductBO mainObject, Audit audit");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId ,this, "delete(NotesAttachmentDomainOverrideBO subObject, ProductBO mainObject, Audit audit");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method , for deleting the notes associated to a benefit component and a benefit, in ProductBusinessObjectBuilder", errorParams, 
                                ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId ,this, "delete(NotesAttachmentDomainOverrideBO subObject, ProductBO mainObject, Audit audit");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method , for deleting the notes associated to a benefit component and a benefit, in ProductBusinessObjectBuilder", errorParams, 
                                ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId ,this, "delete(NotesAttachmentDomainOverrideBO subObject, ProductBO mainObject, Audit audit");
      throw new SevereException("Unhandled exception is caught", null, e);
    }

    return true;
  }


  /**
   * Persist method for Admin.
   * 
   * @param productAdminOptions
   * @param product
   * @param audit
   * @param insertFlag
   * @return
   * @throws WPDException
   */
  public boolean persist(ProductAdmin productAdminOptions, ProductBO product, Audit audit, boolean insertFlag) throws SevereException {
        Logger.logInfo("ProductBusinessObjectBuilder - Entering method persist(ProductComponents subObject=" + productAdminOptions + ":Audit audit=" + audit + ":boolean insertFlag=" + insertFlag);
        
        List newAdminList = productAdminOptions.getAdminList();
        if (newAdminList == null || newAdminList.size() == 0) {
            return true;
        }
        
        AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
        long transactionId = AdapterUtil.getTransactionId();
        try {
            AdapterUtil.beginTransaction(adapterServicesAccess);
            AdapterUtil.logBeginTranstn(transactionId, this, "persist(ProductAdmin subObject, ProductBO mainObject,Audit audit, boolean insertFlag");

            if (insertFlag) {
                int adminId;
                ProductAdminBO productAdminBO = null;
                // Persisting new Admin
                for (int i = 0; i < newAdminList.size(); i++) {
                    productAdminBO = (ProductAdminBO) newAdminList.get(i);
                    productAdminBO.setCreatedUser(audit.getUser());
                    productAdminBO.setLastUpdatedUser(audit.getUser());
                    productAdminBO.setCreatedTimestamp(audit.getTime());
                    productAdminBO.setLastUpdatedTimestamp(audit.getTime());
                    this.adapterManager.attachAdminOption(productAdminBO, adapterServicesAccess);
                }
            } else {
                List adminList = productAdminOptions.getAdminList();
                ProductAdminBO productAdminBO = null;
                if (null != adminList) {
                    for (int i = 0; i < adminList.size(); i++) {

                        productAdminBO = (ProductAdminBO) adminList.get(i);
                        //    			going to update
                        productAdminBO.setLastUpdatedUser(audit.getUser());
                        productAdminBO.setLastUpdatedTimestamp(audit.getTime());
                        this.adapterManager.updateProductAdmin(productAdminBO, adapterServicesAccess);
                    }
                }
          
          ProductQuestionnaireAssnBO productQuestionnaireAssnBO = new ProductQuestionnaireAssnBO();
          
          productQuestionnaireAssnBO.setAdminOptionSysId(productAdminBO.getAdminKey());
          List productAdminBOList = new ArrayList();
            }
            AdapterUtil.endTransaction(adapterServicesAccess);
            AdapterUtil.logTheEndTransaction(transactionId, this, "persist(ProductAdmin subObject, ProductBO mainObject,Audit audit, boolean insertFlag");
        }  catch (Exception ex) {
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId, this, "persist(ProductAdmin subObject, ProductBO mainObject,Audit audit, boolean insertFlag");
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException("Exception occured in persist method for admin in ProductBusinessObjectBuilder", errorParams, ex);
        }
        return true;

    }


  /**
   * Method to retrieve admin associated with product.
   * 
   * @param productBusinessObject
   * @return
   * @throws SevereException
   */
  public ProductAdminBO retrieve(ProductAdminBO productBusinessObject) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering method retrieve(ProductAdminBO productBusinessObject=" + productBusinessObject);

    ProductAdminBO productAdminBO = adapterManager.retrieve(productBusinessObject);
    return productAdminBO;
  }


  /**
   * Method to delete Admin associated with product.
   * 
   * @param productAdminBO
   * @param productBO
   * @param audit
   * @return
   * @throws SevereException
   */
  public boolean delete(ProductAdminBO productAdminBO, ProductBO productBO, Audit audit) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering delete(ProductAdminBO productAdminBO=" + productAdminBO + ":ProductBO productBO=" + productBO + ":Audit audit=" + audit);

    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    long transactionId = AdapterUtil.getTransactionId();
    try
    {
      AdapterUtil.beginTransaction(adapterServicesAccess);
      AdapterUtil.logBeginTranstn(transactionId,this, "delete(ProductAdminBO productAdminBO, ProductBO productBO, Audit audit");
      productAdminBO.setAdminDesc(WebConstants.PRODUCT);

      productAdminBO.setLastUpdatedUser(audit.getUser());
      adapterManager.deleteAdmin(productAdminBO, audit.getUser(), adapterServicesAccess);
      int productKey = productAdminBO.getProductKey();
      List updatedAdminList = adapterManager.getAdminList(productKey);
      //updates the sequence number of the new Admin list
      if(null!=updatedAdminList){
      for (int i = 0; i < updatedAdminList.size(); i++) 
      {
        ProductAdminBO productUpdatedAdminBO = (ProductAdminBO) updatedAdminList.get(i);
        productUpdatedAdminBO.setSequence(i + 1);
        productUpdatedAdminBO.setLastUpdatedUser(audit.getUser());
        productUpdatedAdminBO.setLastUpdatedTimestamp(audit.getTime());
        adapterManager.updateProductAdmin(productUpdatedAdminBO, adapterServicesAccess);
      }
      }
      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId,this, "delete(ProductAdminBO productAdminBO, ProductBO productBO, Audit audit");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "delete(ProductAdminBO productAdminBO, ProductBO productBO, Audit audit");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method for deleting Admin associated with product in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "delete(ProductAdminBO productAdminBO, ProductBO productBO, Audit audit");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method for deleting Admin associated with product in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "delete(ProductAdminBO productAdminBO, ProductBO productBO, Audit audit");
      throw new SevereException("Unhandled exception is caught", null, e);
    }

    return true;
  }


  /**
   * Method to retrieve admin associated with product.
   * 
   * @param locateCriteria
   * @param user
   * @return
   * @throws SevereException
   */
  public LocateResults locate(AdminLocateCriteria locateCriteria, User user) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering locate(AdminLocateCriteria locateCriteria=" + locateCriteria + ":User user=" + user);

    List list = adapterManager.getAdminList(locateCriteria.getProductKey());
    LocateResults locateResults = new LocateResults();
    locateResults.setLocateResults(list);
    return locateResults;
  }


  public boolean persist(NotesAttachmentOverrideBO overrideBO, ProductBO product, Audit audit, boolean insertflag) throws SevereException
  {

    // Create an instance of the adapter manager
    NotesAttachmentAdapterManager adapterManager = new NotesAttachmentAdapterManager();

    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    long transactionId = AdapterUtil.getTransactionId();
    try
    {
      AdapterUtil.beginTransaction(adapterServicesAccess);
      AdapterUtil.logBeginTranstn(transactionId ,this, "persist(NotesAttachmentOverrideBO overrideBO, ProductBO product, Audit audit, boolean insertflag)");
      List persistNotesList = overrideBO.getNotesList();

      if (null != persistNotesList && !persistNotesList.isEmpty())
      {
        for (int i = 0; i < persistNotesList.size(); i++)
        {
          NotesAttachmentOverrideBO overrideNotesBO = (NotesAttachmentOverrideBO) persistNotesList.get(i);

          overrideNotesBO.setPrimaryEntityId(overrideBO.getPrimaryEntityId());
          overrideNotesBO.setPrimaryEntityType(overrideBO.getPrimaryEntityType());
          overrideNotesBO.setSecondaryEntityId(overrideBO.getSecondaryEntityId());
          overrideNotesBO.setSecondaryEntityType(overrideBO.getSecondaryEntityType());
          overrideNotesBO.setBenefitComponentId(overrideBO.getBenefitComponentId());
          if(0!=overrideBO.getTierSysId()){
          	overrideNotesBO.setTierSysId(overrideBO.getTierSysId());
          }
          adapterManager.attachNotesForOverrideEntity(overrideNotesBO, audit, false, adapterServicesAccess);
        }
      }

      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId ,this, "persist(NotesAttachmentOverrideBO overrideBO, ProductBO product, Audit audit, boolean insertflag)");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId ,this, "persist(NotesAttachmentOverrideBO overrideBO, ProductBO product, Audit audit, boolean insertflag)");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method for notes in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId ,this, "persist(NotesAttachmentOverrideBO overrideBO, ProductBO product, Audit audit, boolean insertflag)");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method for notes in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId ,this, "persist(NotesAttachmentOverrideBO overrideBO, ProductBO product, Audit audit, boolean insertflag)");
      throw new SevereException("Unhandled exception is caught", null, e);
    }


    return true;
  }
  
  
  public boolean persist(TierNoteOverRide overrideBO, ProductBO product, Audit audit, boolean insertflag) throws SevereException
  {

    // Create an instance of the adapter manager
    NotesAttachmentAdapterManager adapterManager = new NotesAttachmentAdapterManager();

    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    long transactionId = AdapterUtil.getTransactionId();
    try
    {
      AdapterUtil.beginTransaction(adapterServicesAccess);
      AdapterUtil.logBeginTranstn(transactionId ,this, "persist(NotesAttachmentOverrideBO overrideBO, ProductBO product, Audit audit, boolean insertflag)");
      List persistNotesList = overrideBO.getNotesList();

      if (null != persistNotesList && !persistNotesList.isEmpty())
      {
        for (int i = 0; i < persistNotesList.size(); i++)
        {
          NotesAttachmentOverrideBO overrideNotesBO = (NotesAttachmentOverrideBO) persistNotesList.get(i);

          overrideBO.setNoteId(overrideNotesBO.getNoteId());
          overrideBO.setVersion(overrideNotesBO.getVersion());
          overrideBO.setStatus(overrideNotesBO.getOverrideStatus());	
                   
          adapterManager.attachNotesForOverrideEntityForTier(overrideBO, audit, false, adapterServicesAccess);
        }
      }

      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId ,this, "persist(NotesAttachmentOverrideBO overrideBO, ProductBO product, Audit audit, boolean insertflag)");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId ,this, "persist(NotesAttachmentOverrideBO overrideBO, ProductBO product, Audit audit, boolean insertflag)");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method for notes in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId ,this, "persist(NotesAttachmentOverrideBO overrideBO, ProductBO product, Audit audit, boolean insertflag)");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method for notes in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId ,this, "persist(NotesAttachmentOverrideBO overrideBO, ProductBO product, Audit audit, boolean insertflag)");
      throw new SevereException("Unhandled exception is caught", null, e);
    }


    return true;
  }


  /*
     * methode for getting rule id enhancement
     *
     */

  private String getReferenceList(BenefitComponentBO benefitComponentBO)
  {
    AssignedRuleIdBO ruleIdBO = new AssignedRuleIdBO();
    RefdataAdapterManager rfmanager = new RefdataAdapterManager();
    try
    {
      ruleIdBO.setEntitySystemId(benefitComponentBO.getBenefitComponentId());
      ruleIdBO.setEntityType(WebConstants.BENEFIT_COMP);
      List refList = rfmanager.searchRuleId(ruleIdBO);
      List refNameList = null;
      List refIdList = null;
      if (null != refList && 0 != refList.size())
      {
    	int refListSize = refList.size();
    	refNameList = new ArrayList(refListSize);
    	refIdList = new ArrayList(refListSize);
        for (int i = 0; i < refListSize; i++)
        {
          ruleIdBO = (AssignedRuleIdBO) refList.get(i);
          refNameList.add(ruleIdBO.getCodeDescText());
          refIdList.add(ruleIdBO.getPrimaryCode());
        }
      }
      benefitComponentBO.setRuleNameList(refNameList);
      benefitComponentBO.setRuleList(refIdList);
    }
    catch (Exception e)
    {
      Logger.logError(e);
    }

    return "";
  }

  /**
   * The method gets rule id for standard enhancement
   * @param productRuleIdBO
   * @return
   */
  private List getRuleList (ProductRuleIdBO productRuleIdBO){   
    ProductAdapterManager productAdapterManager = new ProductAdapterManager();
    List ruleIdList = null;
    try{
    	ruleIdList = productAdapterManager.getProductBenefitRule(productRuleIdBO); 
    }
    catch (SevereException e){
      Logger.logError(e);
    }
    return ruleIdList;
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
  	
  	LocateResults locateResults = new LocateResults();
  	List finalList = null;
  	int action = administrationLocateCriteria.getAction();
      switch(action){
  	
      case ComponentsBenefitAdministrationLocateCriteria.LOAD_QUESTIONNARE_LIST:
		        int entityId = administrationLocateCriteria.getEntityId();
		        String entityType = administrationLocateCriteria.getEntiityType();
		        int adminOptSysId = administrationLocateCriteria.getAdminOptSysId();
		        List locateResultList = adapterManager.getBenefitAdministrationValues("PRODUCT",entityId,adminOptSysId);
		        if(null!=locateResultList && 0<locateResultList.size()){
		        getPossibleAnswerList(locateResultList);
		        }
		        finalList=BusinessUtil.getQuestionareHierarchy(locateResultList);
		        locateResults.setLocateResults(finalList);
      break;
      case ComponentsBenefitAdministrationLocateCriteria.LOAD_SELECTED_CHILD:	
		int answerId = administrationLocateCriteria.getAnswerId();
      	EntityProductAdministration productQuesitionnaireBO = 
			(EntityProductAdministration)administrationLocateCriteria.getEntityProductAdministrationBO(); 
		int questionnaireId = productQuesitionnaireBO.getQuestionnaireId();
		int productParentId = administrationLocateCriteria.getEntityId();
		int productId = administrationLocateCriteria.getProductId();
		List oldQuestionnareList = administrationLocateCriteria.getQuestionnareList();
		List oldListForUnsavedQuestion = new ArrayList(administrationLocateCriteria.getQuestionnareList());
		int reaArrangedQuestIndex = administrationLocateCriteria.getQuestionareListIndex();
		((EntityProductAdministration)oldQuestionnareList.get(reaArrangedQuestIndex)).setSelectedAnswerid(answerId);
	    List childList=adapterManager.getChildQuestionnaireValues(answerId,questionnaireId,productParentId,productId,administrationLocateCriteria.getAdminOptSysId());
	    if(null!=childList){
	    getPossibleAnswerList(childList);
	    }
	    finalList=BusinessUtil.getRearrangedQuestionnareList(childList,oldQuestionnareList,reaArrangedQuestIndex);
	    List notesToDelete = getQuestionareNoteListToDelete(finalList,oldListForUnsavedQuestion);
		deleteUnsavedQuestionNote(notesToDelete,productId,administrationLocateCriteria.getAdminOptSysId(),user);
		locateResults.setLocateResults(finalList);
		break;
      case ComponentsBenefitAdministrationLocateCriteria.LOAD_QUESTIONNARE_VIEW:
        adminOptSysId = administrationLocateCriteria.getAdminOptSysId();
        locateResultList = adapterManager.getBenefitAdministrationValues("PRODUCT",administrationLocateCriteria.getEntityId(),adminOptSysId);
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
			EntityProductAdministration oldQuestionnareBo=(EntityProductAdministration)oldListForUnsavedQuestion.get(i);
			
			for (int j=0;j<newQuestionnareList.size();j++){
				
				EntityProductAdministration newQuestionnareBo = (EntityProductAdministration)newQuestionnareList.get(j);
				if(newQuestionnareBo.compareTo(oldQuestionnareBo)){
					newQuestionnareBo.setUnsavedData(false);
					break;
				}
			}
			listToDelete.add(oldQuestionnareBo);
		}
		
		return newQuestionnareList;
		
	}
	
	
	/* this method perfotm delete the unsaved question note deatils while the questionnare answer changes
	 * @noteDetailList,benefitId,adminLevelOptionId,user
	 * 
	 * 
	 */
	private void deleteUnsavedQuestionNote(List noteDetailList,int productId,int adminOptionId,User user)
	throws SevereException{
		StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
		
		for(int i=0;i<noteDetailList.size();i++){
			Audit audit = new AuditImpl();
			audit.setUser(user.getUserId());
			EntityProductAdministration questionnareBo = (EntityProductAdministration)noteDetailList.get(i);
			if(questionnareBo.isUnsavedData()){
				questionnareBo.setNotesExists("N");
				int questionId = questionnareBo.getQuestionNumber();
				NotesToQuestionAttachmentBenefitBO attachmentBo = new NotesToQuestionAttachmentBenefitBO();
				attachmentBo.setPrimaryId(productId);
				attachmentBo.setSecondaryId(adminOptionId);
				attachmentBo.setQuestionId(questionId);
				attachmentBo.setPrimaryEntityType("ATTACHPRODUCT");
				attachmentBo.setSecondaryEntityType("ATTACHADMNQUEST");
				attachmentBo.setNoteOverrideStatus("F");
				NotesToQuestionAttachmentBenefitBO newattachmentBo=getNoteInfo(attachmentBo);
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
	private NotesToQuestionAttachmentBenefitBO getNoteInfo(NotesToQuestionAttachmentBenefitBO attachmentBo){
	 	NotesToQuestionAttachmentBenefitBO newAttachmentBo = null;
		StandardBenefitAdapterManager adapterManager = new StandardBenefitAdapterManager();
		List resultList;
		try {
			resultList = adapterManager.getNoteInfo(attachmentBo);
			if(null!=resultList && resultList.size()>0){
			newAttachmentBo = (NotesToQuestionAttachmentBenefitBO)resultList.get(0);
		 	}
		} catch (AdapterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newAttachmentBo;
	 }
	
	private TierNotesAttachmentOverrideBO getNoteInfo(TierNotesAttachmentOverrideBO attachmentBo){
		TierNotesAttachmentOverrideBO newAttachmentBo = null;
	 	NotesAdapterManager adapterManager = new NotesAdapterManager();
		List resultList;
		try {
			resultList = adapterManager.getNoteInfo(attachmentBo);
			if(null!=resultList && resultList.size()>0){
			newAttachmentBo = (TierNotesAttachmentOverrideBO)resultList.get(0);
		 	}
		} catch (AdapterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newAttachmentBo;
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

  public boolean persist(ProductQuestionnaireAssnBO administrationBO,
          ProductBO  productBO, Audit audit,
          boolean insertFlag) throws  SevereException {
      AdapterServicesAccess adapterServicesAccess = AdapterUtil
              .getAdapterService();
      long transactionId = AdapterUtil.getTransactionId();
      try {
      	
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
  /*
   * this method is for getting possible answer list.
   * @param locateResultList
   *         this list contains  questions needed for possible answer.
   * @ returns answer list
   */
  public void getPossibleAnswerList(List locateResultList) throws SevereException{
  	  if (null != locateResultList || !locateResultList.isEmpty()) {

          QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();

          // Create the Adapter Service Access
          AdapterServicesAccess adapterServicesAccess = AdapterUtil
                  .getAdapterService();

          // Iterate through the list to get the possible answers for the
          // question
          for (int i = 0; i < locateResultList.size(); i++) {
              // Get the individual entitybenefitAdministration objects from
              // the list
          	EntityProductAdministration benefitComponentQuesitionnaireBO = (EntityProductAdministration) locateResultList
                      .get(i);
              int questionNumber = benefitComponentQuesitionnaireBO.getQuestionNumber();

              QuestionBO questionBO = new QuestionBO();

              questionBO.setQuestionNumber(questionNumber);

              // Call the getPossibleAnswers() of the question adaptermanager
              List possibleAnswerList = questionAdapterManager
                      .getPossibleAnswer(questionBO, adapterServicesAccess);
              List arrangedPossibleAnswerList = BusinessUtil.getRearrangedPossibleAnswerList(possibleAnswerList);
              // Set the possible answers list to the
              // entityBenefitAdministration
              benefitComponentQuesitionnaireBO.setPossibleAnswerList(arrangedPossibleAnswerList);
          }
      }
  }
  /**
   * Retrieves the ProductStructureBO by ProductStructureId.
   * @param productAdminOptionLocateCriteria
   * @param user
   * @return
   * @throws SevereException
   */
  public LocateResults locate(ProductAdminOptionLocateCriteria productAdminOptionLocateCriteria, User user) throws SevereException
  {

    // Create an instance of the adapter manager

    // Get the entityId from the locateCruteria
    int entityId = productAdminOptionLocateCriteria.getEntityId();

    // Get the entityType from the locateCriteria
    String entityType = productAdminOptionLocateCriteria.getEntiityType();

    // Get the adminSysId from the locateCriteria
    int adminOptSysId = productAdminOptionLocateCriteria.getAdminOptSysId();

    LocateResults locateResults = new LocateResults();

    // Get the List of benefitAdministration values with the question
    // number,
    //description and default answer
    List locateResultList = adapterManager.getBenefitAdministrationValues(entityType, entityId, adminOptSysId);

    // Check whether the locateResultList is null or empty
    if (null != locateResultList || !locateResultList.isEmpty())
    {

      // Create an instance of the QuestionAdapterManager
      QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();

      // Create the Adapter Service Access
      AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();

      // Iterate through the list to get the possible answers for the
      // question
      for (int i = 0; i < locateResultList.size(); i++)
      {
        // Get the individual entitybenefitAdministration objects from
        // the list
        EntityProductAdministration benefitAdministration = (EntityProductAdministration) locateResultList.get(i);

        // Get the question number from the above object
        int questionNumber = benefitAdministration.getQuestionNumber();

        // Create an instance of the QuestionBO
        QuestionBO questionBO = new QuestionBO();

        // Set the question number to the questionBO
        questionBO.setQuestionNumber(questionNumber);

        // Call the getPossibleAnswers() of the question adaptermanager
        List possibleAnswerList = questionAdapterManager.getPossibleAnswer(questionBO, adapterServicesAccess);
        //Code added by datafeed team for getting the answer
        // description at the service layer - start.
        if (null != possibleAnswerList && !possibleAnswerList.isEmpty())
        {
          for (int j = 0; j < possibleAnswerList.size(); j++)
          {
            // Get individual AnswerBO from the list
            PossibleAnswerBO possibleAnswerBO = (PossibleAnswerBO) possibleAnswerList.get(j);
            // Get the answer id from the list
            int individualAnswerId = possibleAnswerBO.getPossibleAnswerId();
            // Get the answer description from the list
            String individualAnswerDesc = possibleAnswerBO.getPossibleAnswerDesc();
            // Check if the answer id matches the required answer id
            if (benefitAdministration.getAnswerId() == individualAnswerId)
            {
              benefitAdministration.setAnswerDesc(individualAnswerDesc);
            }
          }
        }
        //Code added by datafeed team for getting the answer
        // description at the service layer - end.

        // Set the possible answers list to the
        // entityBenefitAdministration
        benefitAdministration.setAnswers(possibleAnswerList);
      }
    }

    locateResults.setLocateResults(locateResultList);
    locateResults.setTotalResultsCount(locateResultList.size());
    // return the locate results to the business service
    return locateResults;
  }


  /**
   * Persists the subObject
   * 
   * @param subObject
   * @param mainObject
   * @param audit
   * @param insertFlag
   * @return boolean
   * @throws SevereException
   * @throws AdapterException
   */
  public boolean persist(EntityProductBenefitAdministration subObject, ProductBO mainObject, Audit audit, boolean insertFlag) throws SevereException
  {

    // Get the BOList from the BO
    List benefitAdministrationList = (List) subObject.getBenefitAdministrationList();

    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    long transactionId = AdapterUtil.getTransactionId();
    try
    {
      AdapterUtil.beginTransaction(adapterServicesAccess);
      AdapterUtil.logBeginTranstn(transactionId,this, "persist(EntityProductBenefitAdministration subObject,ProductBO mainObject, Audit audit, boolean insertFlag");

      // Iterate through the List
      if(null != benefitAdministrationList){
      for (int i = 0; i < benefitAdministrationList.size(); i++)
      {

        // Get the individual BOs from the list
        EntityProductAdministration administration = (EntityProductAdministration) benefitAdministrationList.get(i);
        administration.setLastUpdatedTimeStamp(audit.getTime());
        administration.setLastUpdatedUser(audit.getUser());
        // Create an instance of the adapter manager

        // Call the persist() of the adapterManager to update the
        // administration
        adapterManager.updateProductAdministrationValues(administration, audit.getUser(), adapterServicesAccess);
      }
      }
      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId,this, "persist(EntityProductBenefitAdministration subObject,ProductBO mainObject, Audit audit, boolean insertFlag");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(EntityProductBenefitAdministration subObject,ProductBO mainObject, Audit audit, boolean insertFlag");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method , for persisting EntityProductBenefitAdministration, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(EntityProductBenefitAdministration subObject,ProductBO mainObject, Audit audit, boolean insertFlag");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method , for persisting EntityProductBenefitAdministration, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId,this, "persist(EntityProductBenefitAdministration subObject,ProductBO mainObject, Audit audit, boolean insertFlag");
      throw new SevereException("Exception occured in persist method , for persisting EntityProductBenefitAdministration, in ProductBusinessObjectBuilder", null, e);
    }
    return true;
  }


  public BusinessObject retrieveValidProductDomain(ProductBO product) throws SevereException
  {
  	product = this.adapterManager.retrieveByProductId(product);
    List domainList = DomainUtil.retrieveAssociatedDomains(WebConstants.PROD_TYPE, product.getProductKey());
    product.setBusinessDomains(domainList);
    return product;
  }


  /**
   * Method to retrieve admin associated with product.
   * 
   * @param locateCriteria
   * @param user
   * @return
   * @throws SevereException
   */
  public LocateResults locate(ProductRuleRefDataLocateCriteria locateCriteria, User user) throws SevereException
  {

    List list = adapterManager.getRulesList(locateCriteria.getQueryName(), locateCriteria.getRuleType());
    LocateResults locateResults = new LocateResults();
    locateResults.setLocateResults(list);
    return locateResults;
  }

  /**
   *  This method retrieves the product rule parameter codes, types and description
   *  and then it returns a map having the keys as the rule parameter type and values 
   *  as another Map having rule parameter code and description pair.
   * 
   * @return
   * @throws SevereException
   */
  public Map retrieveProductRuleCodeMap() throws SevereException{
  	List ruleCodes=this.adapterManager.retreiveProductRuleCodes();
  	Map ruleMap=new HashMap();
  	for(int i=0;i<ruleCodes.size();i++){
  		ProductRuleBO productRuleBO=(ProductRuleBO)ruleCodes.get(i);
  		if(null== ruleMap.get(productRuleBO.getRuleParamType())){
  			ruleMap.put(productRuleBO.getRuleParamType(),new HashMap());
  		}
  		Map ruleParamMap=(Map)ruleMap.get(productRuleBO.getRuleParamType());
  		ruleParamMap.put(productRuleBO.getRuleParamId(),productRuleBO.getRuleParamCode());
  	}
  	return ruleMap;
  }
  


  /**
   * Persist method for Admin.
   * 
   * @param productRuleAssociation
   * @param productBO
   * @param audit
   * @param insertFlag
   * @return
   * @throws WPDException
   */
  public boolean persist(ProductRuleAssociation productRuleAssociation, ProductBO productBO, Audit audit, boolean insertFlag) throws SevereException
  {

    List newRulesList = productRuleAssociation.getRulesList();
    ProductRuleAssociationBO productRuleAssociationBO = null;

    AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    long transactionId = AdapterUtil.getTransactionId();
    try
    {
      if (newRulesList == null || newRulesList.size() == 0)
         return true; 
      
      AdapterUtil.beginTransaction(adapterServicesAccess);
      AdapterUtil.logBeginTranstn(transactionId, this, "persist(ProductRuleAssociation productRuleAssociation, ProductBO productBO,Audit audit, boolean insertFlag)");
      if (insertFlag)
      {
        int productKey = productBO.getProductKey();
        String user = audit.getUser();
        java.util.Date time = audit.getTime();

        // Persisting new Rule
        for (int i = 0; i < newRulesList.size(); i++)
        {
          productRuleAssociationBO = (ProductRuleAssociationBO) newRulesList.get(i);
          productRuleAssociationBO.setProductRuleSysID(sequenceManager.getNextProductRuleSequence());
          productRuleAssociationBO.setProductKey(productKey);
          productRuleAssociationBO.setLastUpdatedUser(user);
          productRuleAssociationBO.setCreatedUser(user);
          productRuleAssociationBO.setCreatedTimestamp(time);
          productRuleAssociationBO.setLastUpdatedTimestamp(time);
          productRuleAssociationBO.setRuleType(WebConstants.TO_MAKE_NOT_EMPTY);
          this.adapterManager.createProductRules(productRuleAssociationBO, user, adapterServicesAccess);
        }
      }
      else
      {
        String user = audit.getUser();
        java.util.Date time = audit.getTime();
        // Updateing Rules
        for (int i = 0; i < newRulesList.size(); i++)
        {
          productRuleAssociationBO = (ProductRuleAssociationBO) newRulesList.get(i);
          productRuleAssociationBO.setLastUpdatedUser(user);
          productRuleAssociationBO.setLastUpdatedTimestamp(time);
          productRuleAssociationBO.setGenRuleID(WebConstants.TO_MAKE_NOT_EMPTY);
          productRuleAssociationBO.setRuleType(WebConstants.TO_MAKE_NOT_EMPTY);
          this.adapterManager.updateProductRule(productRuleAssociationBO, user, adapterServicesAccess);
        }
      }
      AdapterUtil.endTransaction(adapterServicesAccess);
      AdapterUtil.logTheEndTransaction(transactionId, this, "persist(ProductRuleAssociation productRuleAssociation, ProductBO productBO,Audit audit, boolean insertFlag)");
    }
    catch (SevereException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId, this, "persist(ProductRuleAssociation productRuleAssociation, ProductBO productBO,Audit audit, boolean insertFlag)");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method , for persisting ProductRuleAssociation, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId, this, "persist(ProductRuleAssociation productRuleAssociation, ProductBO productBO,Audit audit, boolean insertFlag)");
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in persist method , for persisting ProductRuleAssociation, in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      AdapterUtil.abortTransaction(adapterServicesAccess);
      AdapterUtil.logAbortTxn(transactionId, this, "persist(ProductRuleAssociation productRuleAssociation, ProductBO productBO,Audit audit, boolean insertFlag)");
      throw new SevereException("Unhandled exception is caught", null, e);
    }

    return true;
  }


  /**
   * Method to delete Rules associated with product.
   * 
   * @param productRuleAssociationBO
   * @param productBO
   * @param audit
   * @return
   * @throws SevereException
   */
  public boolean delete(ProductRuleAssociationBO productRuleAssociationBO, ProductBO productBO, Audit audit) throws SevereException
  {
    productRuleAssociationBO.setGenRuleID(WebConstants.TO_MAKE_NOT_EMPTY);
    productRuleAssociationBO.setRuleType(WebConstants.TO_MAKE_NOT_EMPTY);

    try
    {
      adapterManager.deleteProductRule(productRuleAssociationBO, audit.getUser());
    }
    catch (SevereException ex)
    {
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method for rules in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method for rules in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      throw new SevereException("Unhandled exception is caught", null, e);
    }

    return true;
  }


  /**
   * Method to retrieve the state code and product family list for reference
   * data valiadation
   * 
   * @param productBO
   * @return
   * @throws SevereException
   */
  public List retrieveProductForReferenceValidation(ProductBO productBO) throws SevereException
  {
    return this.adapterManager.retrieveProductForValidation(productBO);
  }

  /**
   * 
   * @param productSysId
   * @param benCompId
   * @param defnId
   * @param tierDefId
   * @param levelId
   * @param saveStr
   * @param isExists
   * @return
 * @throws AdapterException
 * @throws SevereException
 * @throws 
   */
  public boolean saveProductTierDefinition(int productSysId, int benCompId, int defnId , int tierDefId, int levelId, String saveStr,
  			String isExists,Audit audit) throws SevereException, AdapterException{
  	return this.adapterManager.addTierToProduct(productSysId,benCompId,defnId,tierDefId,levelId,saveStr,isExists,audit);
  }
  /**
   * 
   * @param tierDefBO
   * @return
   * @throws SevereException
   */
  public List retrieveTierDefinitions(int productSysId, int benCompId, int defnId) throws SevereException{
  	
  	return this.adapterManager.getTierDefinition(productSysId,benCompId,defnId);
  }
  
  /**
   * Method to retrieve the provider arrangement list for reference data
   * valiadation
   * 
   * @param productBO
   * @return
   * @throws SevereException
   */
  public List retrievePvaForReferenceValidation(ProductBO productBO) throws SevereException
  {
    return this.adapterManager.retrievePvaForValidation(productBO);
  }

  /**
   * Method to delete Component associated with product.
   * 
   * @param entityBenefitAdminBO
   * @param productBO
   * @param audit
   * @return
   * @throws SevereException
   */
  public boolean delete(EntityBenefitAdministration entityBenefitAdminBO, ProductBO productBO, Audit audit) throws SevereException
  {

    Logger.logInfo("ProductBusinessObjectBuilder - Entering delete(EntityBenefitAdministration entityBenefitAdminBO=" + entityBenefitAdminBO + ":ProductBO productBO=" + productBO + 
                   ":Audit audit=" + audit);

    try
    {
    	
      entityBenefitAdminBO.setLastUpdatedTimestamp(audit.getTime());
      entityBenefitAdminBO.setLastUpdatedUser(audit.getUser());
      
      adapterManager.deleteQuestion(entityBenefitAdminBO, productBO.getLastUpdatedUser());
    }
    catch (SevereException ex)
    {
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method for benefit component in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method for benefit component in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      throw new SevereException("Unhandled exception is caught", null, e);
    }
    return true;
  }

  /**
   * Method to hide benefit/benefit level.
   * @param productEntityBenefitAdmin
   * @param productBO
   * @param audit
   * @return
   * @throws SevereException
   */
  public boolean delete(ProductEntityBenefitAdmin productEntityBenefitAdmin, ProductBO productBO, Audit audit) throws SevereException
  {
    Logger.logInfo("ProductBusinessObjectBuilder - Entering method delete(ProductEntityBenefitAdmin productEntityBenefitAdmin=" + productEntityBenefitAdmin + ":Audit audit=" + audit);
    try
    {
    	this.adapterManager.hideProductAdminOption(productEntityBenefitAdmin, audit.getUser());
    }
    catch (SevereException ex)
    {
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method for hide benefit/benefit level in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (AdapterException ex)
    {
      List errorParams = new ArrayList(3);
      String obj = ex.getClass().getName();
      errorParams.add(obj);
      errorParams.add(obj.getClass().getName());
      throw new SevereException("Exception occured in delete method for hide benefit/benefit level in ProductBusinessObjectBuilder", errorParams, ex);
    }
    catch (Exception e)
    {
      throw new SevereException("Unhandled exception is caught", null, e);
    }

    return true;
  }

  public List checkOutMessages(int oldProductKey, int newProdcutKey) throws ServiceException
  {
    List messageList = new ArrayList();
    boolean flag = false;
    List newProductvalues = null;
    List oldProductvalues = null;
    List modifiedBCList =null;
    List modifiedBnftList =null;
    List removedBnftList = new ArrayList();
    List removedBCList = new ArrayList();
    StringBuffer modifiedBC = new StringBuffer(WebConstants.EMPTY_STRING);
    StringBuffer modifiedbenefit = new StringBuffer(WebConstants.EMPTY_STRING);
    StringBuffer removedBC = new StringBuffer(WebConstants.EMPTY_STRING);
    StringBuffer removedbenefit = new StringBuffer(WebConstants.EMPTY_STRING);
    String finalMessage1 = null;
    String finalMessage2 = null;
    String finalMessage3 = null;
    String finalMessage4 = null;
    int bnftCompntParentId;
    int bnftCompntId;
    int bnftCompntNewParentId;
    int bnftCompntNewId;
    int bnftId;
    int bnftNewId;
    int bnftParentId;
    int bnftNewParentId;
    int oldPrductStructId;
    int newPrductStructId;
    ProductCheckBO productCheckBO = null;
    ProductCheckBO ProductCheckNewBO = null;
    try
    {
//      FIXME Remove the line science it’s already declare and initialized 
//      FIXME Use global variable (this.adapterManager) 
      oldProductvalues = this.adapterManager.getProductValues(oldProductKey);
      int oldProductvaluesSize = oldProductvalues.size();
      newProductvalues = this.adapterManager.getProductValues(newProdcutKey);
      int newProductvaluesSize = newProductvalues.size();

      for (int i = 0; i < oldProductvaluesSize; i++) //FIXME Null check
      {
        productCheckBO = (ProductCheckBO) oldProductvalues.get(i);
        bnftCompntParentId = productCheckBO.getBenefitComponentParentId();
        bnftCompntId = productCheckBO.getBenefitComponentId();
        bnftParentId = productCheckBO.getBenefitSysParentId();
        bnftId = productCheckBO.getBenefitSysId();
        if (flag == false)
        {
          for (int j = 0; j < newProductvaluesSize; j++)//FIXME Null check
          {
            ProductCheckNewBO = (ProductCheckBO) newProductvalues.get(j);
            bnftCompntNewParentId = ProductCheckNewBO.getBenefitComponentParentId();
            bnftCompntNewId = ProductCheckNewBO.getBenefitComponentId();
            bnftNewParentId = ProductCheckNewBO.getBenefitSysParentId();
            bnftNewId = ProductCheckNewBO.getBenefitSysId();
            if (bnftCompntParentId == bnftCompntNewParentId)
            {
              if (bnftCompntId == bnftCompntNewId)
              {
                flag = true;
                break;
              }
              else
              {
                if (bnftParentId == bnftNewParentId)
                {
                modifiedBCList = new ArrayList();
                  if (bnftId == bnftNewId)
                  {
                    if (null != modifiedBCList && modifiedBCList.size() > 0)
                    {

                      List modifiedBcCount = new ArrayList();
                      int modifiedBCid;
                      for (int p = 0; p < modifiedBCList.size(); p++)
                      {
                        ProductCheckBO productremovedBO = (ProductCheckBO) modifiedBCList.get(p);
                        modifiedBCid = productremovedBO.getBenefitComponentId();
                        modifiedBcCount.add(p, new Integer(modifiedBCid));
                      }
                      if (modifiedBcCount.contains(new Integer(bnftCompntId)))
                      {
                        break;
                      }
                      else
                      {
                        modifiedBCList.add(productCheckBO);
                        break;
                      }
                    }
                    else
                    {
                      modifiedBCList.add(productCheckBO);
                      break;
                    }
                  }
                  else
                  {
                  	modifiedBnftList = new ArrayList();
                    modifiedBnftList.add(productCheckBO);
                    if (null != modifiedBCList && modifiedBCList.size() > 0)
                    {

                      List modifiedBcCount = new ArrayList();
                      int modifiedBCid;
                      for (int p = 0; p < modifiedBCList.size(); p++)
                      {
                        ProductCheckBO productremovedBO = (ProductCheckBO) modifiedBCList.get(p);
                        modifiedBCid = productremovedBO.getBenefitComponentId();
                        modifiedBcCount.add(p, new Integer(modifiedBCid));
                      }
                      if (modifiedBcCount.contains(new Integer(bnftCompntId)))
                      {
                        break;
                      }
                      else
                      {
                        modifiedBCList.add(productCheckBO);
                        break;
                      }
                    }
                    else
                    {
                      modifiedBCList.add(productCheckBO);
                    }
                    break;
                  }
                }
                else
                {
                  if (j == (newProductvaluesSize - 1))
                  {
                    removedBnftList.add(productCheckBO);
                    break;
                  }
                }
              }

            }
            else
            {

              if (j == (newProductvaluesSize - 1))
              {

                if (null != removedBCList && removedBCList.size() > 0)
                {
                  List removecount = new ArrayList();
                  int removedbcid;
                  for (int p = 0; p < removedBCList.size(); p++)
                  {
                    ProductCheckBO productremovedBO = (ProductCheckBO) removedBCList.get(p);
                    removedbcid = productremovedBO.getBenefitComponentId();
                    removecount.add(p, new Integer(removedbcid));
                  }
                  if (removecount.contains(new Integer(bnftCompntId)))
                  {
                    break;
                  }
                  else
                  {
                    removedBCList.add(productCheckBO);
                    break;
                  }

                }
                else
                {
                  removedBCList.add(productCheckBO);
                  break;
                }
              }

            }

          }
        }
        flag = false;
      }
      if(null!=oldProductvalues && null!= newProductvalues){
      	if(newProductvalues.isEmpty()){
      		InformationalMessage finalCheckMessage = new InformationalMessage(BusinessConstants.CHECKOUT_FINAL_MESSAGE_6);
 			 messageList.add(finalCheckMessage);
      	}else{
      			oldPrductStructId = ((ProductCheckBO)oldProductvalues.get(0)).getProductStructId();
      			newPrductStructId = ((ProductCheckBO)newProductvalues.get(0)).getProductStructId();
      		if(oldPrductStructId != newPrductStructId){
      			InformationalMessage finalCheckMessage = new InformationalMessage(BusinessConstants.CHECKOUT_FINAL_MESSAGE_5);
      			 messageList.add(finalCheckMessage);
      		}
      	}
      }
      if (null != modifiedBCList && modifiedBCList.size() > 0)
      {
        modifiedBC.append(" Modified BenefitComponents ("); //FIXME Remove hardcoded
        for (int i = 0; i < modifiedBCList.size(); i++)
        {
          ProductCheckBO newStringBO = (ProductCheckBO) modifiedBCList.get(i);
          modifiedBC.append(newStringBO.getBenefitComponentName());
          if ((modifiedBCList.size() > 1 && i < (modifiedBCList.size() - 1)))
            modifiedBC.append(",");
        }
        modifiedBC.append(")");
        finalMessage1 = (modifiedBC.toString());
        InformationalMessage finalCheckMessage = new InformationalMessage(BusinessConstants.CHECKOUT_FINAL_MESSAGE_1);
        finalCheckMessage.setParameters(new String[]
            { finalMessage1 });
        messageList.add(finalCheckMessage);

      }
      if (null != modifiedBnftList && modifiedBnftList.size() > 0)
      {
        for (int i = 0; i < modifiedBnftList.size(); i++)
        {
          modifiedbenefit.append(" benefit (");//FIXME Remove hardcoded
          ProductCheckBO newStringBO = (ProductCheckBO) modifiedBnftList.get(i);
          modifiedbenefit.append(newStringBO.getBenefitSysName());
          modifiedbenefit.append(")");
          modifiedbenefit.append(" modified in BenefitComponent(");//FIXME Remove hardcoded
          modifiedbenefit.append(newStringBO.getBenefitComponentName());
          modifiedbenefit.append(")");
          if ((removedBnftList.size() > 1 && i < (removedBnftList.size() - 1)))
            modifiedbenefit.append(",");
        }
        finalMessage2 = (modifiedbenefit.toString());
        InformationalMessage finalCheckMessage = new InformationalMessage(BusinessConstants.CHECKOUT_FINAL_MESSAGE_2);
        finalCheckMessage.setParameters(new String[]
            { finalMessage2 });
        messageList.add(finalCheckMessage);


      }

      if (null != modifiedBCList && modifiedBCList.size() > 0)
      {
        for (int i = 0; i < modifiedBCList.size(); i++)
        {
          ProductCheckBO newStringBO = (ProductCheckBO) modifiedBCList.get(i);
          if (null != removedBCList && removedBCList.size() > 0)
          {
            for (int j = 0; j < removedBCList.size(); j++)
            {
              ProductCheckBO newStringBO1 = (ProductCheckBO) removedBCList.get(j);
              int modifiedBcId = newStringBO.getBenefitComponentId();
              int removeddBcId = newStringBO1.getBenefitComponentId();
              if (modifiedBcId == removeddBcId)
                removedBCList.remove(j);

            }

          }
        }
      }
      if (null != removedBCList && removedBCList.size() > 0)
      {
        for (int j = 0; j < removedBCList.size(); j++)
        {
          ProductCheckBO newStringBO1 = (ProductCheckBO) removedBCList.get(j);
          List bclist = this.adapterManager.getBenefitComponentsList(newProdcutKey,false);
          if (null != bclist && bclist.size() > 0)
          {
            for (int i = 0; i < bclist.size(); i++)
            {
              ProductComponentBO productComponentBO = (ProductComponentBO) bclist.get(i);
              int BcId = productComponentBO.getComponentKey();
              int removeddBcId = newStringBO1.getBenefitComponentId();
              if (removeddBcId == BcId)
                removedBCList.remove(j);
            }
          }
        }

      }

      if (null != removedBCList && removedBCList.size() > 0)
      {
        removedBC.append(" Removed BenefitComponents (");//FIXME Remove hardcoded
        for (int i = 0; i < removedBCList.size(); i++)
        {
          ProductCheckBO newStringBO = (ProductCheckBO) removedBCList.get(i);
          removedBC.append(newStringBO.getBenefitComponentName());
          if(null!=removedBCList){
          if ((removedBCList.size() > 1 && i < (removedBCList.size() - 1)))
            removedBC.append(",");
          }
        }
        removedBC.append(")");

        finalMessage3 = (removedBC.toString());
        InformationalMessage finalCheckMessage = new InformationalMessage(BusinessConstants.CHECKOUT_FINAL_MESSAGE_3);
        finalCheckMessage.setParameters(new String[]
            { finalMessage3 });
        messageList.add(finalCheckMessage);
      }
      if (null != removedBnftList && removedBnftList.size() > 0)
      {
        for (int i = 0; i < removedBnftList.size(); i++)
        {
          removedbenefit.append(" benefit (");//FIXME Remove hardcoded
          ProductCheckBO newStringBO = (ProductCheckBO) removedBnftList.get(i);
          removedbenefit.append(newStringBO.getBenefitSysName());
          removedbenefit.append(")");
          removedbenefit.append(" removed in BenefitComponent(");//FIXME Remove hardcoded
          removedbenefit.append(newStringBO.getBenefitComponentName());
          removedbenefit.append(")");
          if ((removedBnftList.size() > 1 && i < (removedBnftList.size() - 1)))
            removedbenefit.append(",");
        }

        finalMessage4 = (removedbenefit.toString());
        InformationalMessage finalCheckMessage = new InformationalMessage(BusinessConstants.CHECKOUT_FINAL_MESSAGE_4);
        finalCheckMessage.setParameters(new String[]
            { finalMessage4 });
        messageList.add(finalCheckMessage);
      }
    }
    catch (WPDException ex)
    {
      throw new ServiceException("Exception occured while BOM call", null, ex);
    }
    return messageList;
  }
  /*
   * 
   * @ input -product key
   * @ returns List contains duplicate reference in benefit line and Questions
   */
  public List getAllDuplicateReference(int productKey) throws SevereException{
  	List allDuplicateRef = new ArrayList();
  	List allDuplicateBenefitRefList;
  	List allDuplicateQuestRefList;
  	 try{
  		allDuplicateBenefitRefList = this.adapterManager.getDuplicateAllBenefitRef(productKey);
  		if(null!=allDuplicateBenefitRefList){
  			allDuplicateRef.add(0,allDuplicateBenefitRefList);
  		}
  		allDuplicateQuestRefList = this.adapterManager.getDuplicateAllQuestionRef(productKey);
  		if(null!=allDuplicateQuestRefList){
  			allDuplicateRef.add(1,allDuplicateQuestRefList);
  		}
  	}catch(WPDException ex){
  		
  		throw new SevereException("Unhandled exception is caught", null, ex);
  	}
  	return allDuplicateRef;
  }
  /**
   * This method for formatting the definition lite to get a list of tier system id.
   * 
   * @param ComponentsBenefitAdministrationLocateCriteria.
   * @param User.
   * @return locateResults.
   *         This list contain list of questionnare objects.
   * @throws SevereException
   */
  private List getTierList(List criteriaListFrmDB){
	
	List tierSysIdList = new ArrayList();
	
	TierDefinitionBO oldTierDef = (TierDefinitionBO)criteriaListFrmDB.get(0);
	int oldCntrctTierId= oldTierDef.getTierSysId();
	
	tierSysIdList.add(new Integer(oldCntrctTierId));
	
	for(int i=1;i<criteriaListFrmDB.size();i++){
		
    	TierDefinitionBO newTierDef = (TierDefinitionBO)criteriaListFrmDB.get(i);
    	int newCntrctTierId = newTierDef.getTierSysId();
    	
    	if(oldCntrctTierId != newCntrctTierId){
    		
    		tierSysIdList.add(new Integer(newCntrctTierId));
    	}
    	oldCntrctTierId = newCntrctTierId;
	}
	
	return tierSysIdList;
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
  		ProductAdministrationLocateCriteria administrationLocateCriteria,
          User user) throws SevereException {

  	 
      // Create an instance of the adapter manager
  	ProductAdapterManager adapterManager = new ProductAdapterManager();
  	LocateResults locateResults = new LocateResults();
      // Get the entityId from the locateCruteria
  	List finalList = null;
  	int action = administrationLocateCriteria.getAction();
      switch(action){
  	
      case ProductAdministrationLocateCriteria.LOAD_QUESTIONNARE_LIST:
		        int entitySysId = administrationLocateCriteria.getEntityId();
		        String entityType = administrationLocateCriteria.getEntiityType();
		        int adminOptSysId = administrationLocateCriteria.getAdminOptSysId();
		        int admnLvlAsscId = administrationLocateCriteria.getAdmnLvlAsscId();
		        int benefitComponentId = administrationLocateCriteria.getBenefitComponentId();
		        int benefitId = administrationLocateCriteria.getBenefitId();
		        // 	 Code change by minu : 27-01-2011 : eWPD System Stabilization - product module
		        int benftAdminSysId = administrationLocateCriteria.getBenefitAdminSysId();
		        int productPrntSysId = administrationLocateCriteria.getProductPrntSysId();
		        HashMap allPossibleAnswerMap = administrationLocateCriteria.getAllPossibleAnswerMap();
		        List locateResultList = adapterManager.getQuestionnaireValuesForProduct(admnLvlAsscId,benefitComponentId,entitySysId,adminOptSysId,benftAdminSysId,productPrntSysId);
		        if(null!=locateResultList && 0<locateResultList.size()){
		        getPossibleAnswerListForProduct(locateResultList,allPossibleAnswerMap);
		        }
		        finalList=BusinessUtil.getQuestionareHierarchy(locateResultList);
		        if(null != finalList && !finalList.isEmpty()){ //if questionnaire exist
		        List tierandNontierList = new ArrayList();
		        tierandNontierList.add(finalList);
		        ContractAdapterManager contractAdapterManager = new ContractAdapterManager();
		        boolean isAdminOptionExcluded = contractAdapterManager.isAdminOptionExcluded(adminOptSysId); 
				if(!isAdminOptionExcluded){ 
		        List tierCriteriaList  = adapterManager.getTierCriteriaForProduct(entitySysId,benefitComponentId,benefitId);
			        if(null != tierCriteriaList && !tierCriteriaList.isEmpty()){       // if the admin option is tiered
			        	 	List benefitTierList =BenefitTierUtil.getTieredList(tierCriteriaList); // The tier objects are created from the values returned by the query
			        	 	List tieredQuestionnaireList = adapterManager.getTieredQuestionnaireValuesForProduct(admnLvlAsscId,benefitComponentId,entitySysId,getTierList(tierCriteriaList)
			        	 			,adminOptSysId,benftAdminSysId,productPrntSysId);
			        	 	// iterate 	benefitTierList and put the questionaires corresponding to each tier in the tier object
			        	 	for(int i=0;i<benefitTierList.size();i++){
			        	 		BenefitTierDefinition definitionBO = (BenefitTierDefinition)benefitTierList.get(i);
			        	 		List tierList = definitionBO.getBenefitTiers();
								for (int k=0;k<tierList.size();k++){
									BenefitTier tierBo = (BenefitTier)tierList.get(k);
									List tierQuestionnaireList = new ArrayList();
									for(int q=0; q<tieredQuestionnaireList.size();q++){
										ProductQuestionareBO questionnaireBO = (ProductQuestionareBO)tieredQuestionnaireList.get(q);
										if(tierBo.getBenefitTierSysId() == questionnaireBO.getTierSysId()){
											tierQuestionnaireList.add(questionnaireBO);
										}
									}
									if(null!=tierQuestionnaireList && !tierQuestionnaireList.isEmpty())
									getPossibleAnswerListForProduct(tierQuestionnaireList,allPossibleAnswerMap);
									finalList=BusinessUtil.getQuestionareHierarchy(tierQuestionnaireList);
									tierBo.setQuestionnaireList(finalList);
								}
			        	 	}
			        	 	tierandNontierList.add(benefitTierList);	
		        	}
		        }
			    locateResults.setLocateResults(tierandNontierList);   
		        }else{
		        	locateResults.setLocateResults(finalList);
		        }
		       
      break;
  	
  	case ProductAdministrationLocateCriteria.LOAD_SELECTED_CHILD:	
	    		int answerId = administrationLocateCriteria.getAnswerId();
  		ProductQuestionareBO productQuestionareBO = 
	    			(ProductQuestionareBO)administrationLocateCriteria.getProductQuestionareBO(); 
	    		int questionnaireId = productQuestionareBO.getQuestionnaireId();
				int productParentSysId = administrationLocateCriteria.getProductPrntSysId();
				List oldQuestionnareList = administrationLocateCriteria.getQuestionnareList();
				String answerDesc = administrationLocateCriteria.getAnswerDesc();
				List oldListForUnsavedQuestion = new ArrayList(administrationLocateCriteria.getQuestionnareList());
				int benefitComponentID =administrationLocateCriteria.getBenefitComponentId();
				int reaArrangedQuestIndex = administrationLocateCriteria.getQuestionareListIndex();
				int productSysId = administrationLocateCriteria.getEntityId();
				int benefitDefnId = ((ProductQuestionareBO)oldListForUnsavedQuestion.get(0)).getBenefitDefenitionId();
				allPossibleAnswerMap = administrationLocateCriteria.getAllPossibleAnswerMap();
				((ProductQuestionareBO)oldQuestionnareList.get(reaArrangedQuestIndex)).setSelectedAnswerid(answerId);
				((ProductQuestionareBO)oldQuestionnareList.get(reaArrangedQuestIndex)).setSelectedAnswerDesc(answerDesc);
	    	    List childList = adapterManager
					.getChildQuestionnaireValuesForProduct(answerId,
							questionnaireId, productParentSysId,
							administrationLocateCriteria.getAdmnLvlAsscId(),benefitComponentID,productQuestionareBO.getBenefitDefenitionId(),productSysId);
			if(null!=childList){
	    	    getPossibleAnswerListForProduct(childList,allPossibleAnswerMap);
	    	    }
	    	    finalList=BusinessUtil.getRearrangedQuestionnareList(childList,oldQuestionnareList,reaArrangedQuestIndex);
	    	    List notesToDelete = getBenefitQuestionareNoteListToDelete(finalList,oldListForUnsavedQuestion);
	    	    deleteUnsavedBenefitQuestionNote(notesToDelete,productSysId,benefitComponentID,administrationLocateCriteria.getAdmnLvlAsscId(),productQuestionareBO.getBenefitDefenitionId(),user);
	    		locateResults.setLocateResults(finalList);
  	break;
  	
  	case ProductAdministrationLocateCriteria.LOAD_SELECTED_CHILD_TIER:
  		int tierAnswerId = administrationLocateCriteria.getAnswerId();
		ProductQuestionareBO tierProductQuestionareBO = 
    			(ProductQuestionareBO)administrationLocateCriteria.getProductQuestionareBO(); 
    		int tierQuestionnaireId = tierProductQuestionareBO.getQuestionnaireId();
			int tierProductParentSysId = administrationLocateCriteria.getProductPrntSysId();
			List tierOldQuestionnareList = administrationLocateCriteria.getQuestionnareList();
			List tierOldListForUnsavedQuestion = new ArrayList(administrationLocateCriteria.getQuestionnareList());
			int tierBenefitComponentID =administrationLocateCriteria.getBenefitComponentId();
			int tierReaArrangedQuestIndex = administrationLocateCriteria.getQuestionareListIndex();
			int tierProductSysId = administrationLocateCriteria.getEntityId();
			String tierAnswerDesc = administrationLocateCriteria.getAnswerDesc();
			allPossibleAnswerMap = administrationLocateCriteria.getAllPossibleAnswerMap();
			((ProductQuestionareBO)tierOldQuestionnareList.get(tierReaArrangedQuestIndex)).setSelectedAnswerid(tierAnswerId);
			((ProductQuestionareBO)tierOldQuestionnareList.get(tierReaArrangedQuestIndex)).setSelectedAnswerDesc(tierAnswerDesc);
    	    List tierChildList = adapterManager
				.getChildTierQuestionnaireValuesForProduct(tierAnswerId,
						tierQuestionnaireId, tierProductParentSysId,
						administrationLocateCriteria.getAdmnLvlAsscId(),tierBenefitComponentID,tierProductQuestionareBO.getBenefitDefenitionId(),tierProductSysId,tierProductQuestionareBO.getTierSysId());
		if(null!=tierChildList){
    	    getPossibleAnswerListForProduct(tierChildList,allPossibleAnswerMap);
    	    }
    	    finalList=BusinessUtil.getRearrangedQuestionnareList(tierChildList,tierOldQuestionnareList,tierReaArrangedQuestIndex);
    	    List tierNotesToDelete = getBenefitQuestionareNoteListToDelete(finalList,tierOldListForUnsavedQuestion);
    	    deleteUnsavedBenefitTierQuestionNote(tierNotesToDelete,tierProductSysId,tierBenefitComponentID,administrationLocateCriteria.getAdmnLvlAsscId(),tierProductQuestionareBO.getBenefitDefenitionId(),user,tierProductQuestionareBO.getTierSysId());
    		locateResults.setLocateResults(finalList);
  		
  	/*case ComponentsBenefitAdministrationLocateCriteria.LOAD_QUESTIONNARE_VIEW:
		        benefitComponentSysId = administrationLocateCriteria.getEntityId();
		        adminOptSysId = administrationLocateCriteria.getAdminOptSysId();
		        beneftAdminSysId = administrationLocateCriteria
		                .getBenefitAdminSysId();
		        locateResultList = adapterManager.getQuestionnaireValues(adminOptSysId,beneftAdminSysId,benefitComponentSysId);
		        finalList=BusinessUtil.getQuestionareHierarchy(locateResultList);
		        locateResults.setLocateResults(finalList);
      break;*/
      }
      // return the locate results to the business service 
      return locateResults;
  }
  
  
  /*
	 * this method filter the questionareLiat and flag the question notes to be deleted
	 * 
	 * 
	 */
	public static List getBenefitQuestionareNoteListToDelete(List newQuestionnareList,List oldListForUnsavedQuestion){
		
		List listToDelete= new ArrayList();
		
		for(int i=0;i<oldListForUnsavedQuestion.size();i++){
			
			ProductQuestionareBO oldQuestionnareBo = (ProductQuestionareBO)oldListForUnsavedQuestion.get(i);
			
			for (int j=0;j<newQuestionnareList.size();j++){
				
				ProductQuestionareBO newQuestionnareBo = (ProductQuestionareBO)newQuestionnareList.get(j);
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
	private void deleteUnsavedBenefitQuestionNote(List noteDetailList,int productId,int beneftComponentId,int adminLevelOptionId,int benefitDefId,User user)
	throws SevereException{
		NotesAdapterManager adapterManager = new NotesAdapterManager();
		
		for(int i=0;i<noteDetailList.size();i++){
			Audit audit = new AuditImpl();
			audit.setUser(user.getUserId());
			ProductQuestionareBO questionnareBo = (ProductQuestionareBO)noteDetailList.get(i);
			if(questionnareBo.isUnsavedData()){
				questionnareBo.setNotes_exists("N");
				int questionId = questionnareBo.getQuestionId();
				NotesToQuestionAttachmentBO attachmentBo = new NotesToQuestionAttachmentBO();
				attachmentBo.setPrimaryId(productId);
				attachmentBo.setSecondaryId(adminLevelOptionId);
				attachmentBo.setQuestionId(questionId);
				attachmentBo.setPrimaryEntityType("ATTACHPRODUCT");
				attachmentBo.setSecondaryEntityType("ATTACHQUESTION");
				attachmentBo.setNoteOverrideStatus("F");
				attachmentBo.setBnftDefId(new Integer(benefitDefId).toString());
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
	/* this method perfotm delete the unsaved question note deatils while the tiered questionnare answer changes
	 * @noteDetailList,benefitId,adminLevelOptionId,user
	 * 
	 * 
	 */
	private void deleteUnsavedBenefitTierQuestionNote(List noteDetailList,int productId,int beneftComponentId,int adminLevelOptionId,int benefitDefId,User user, int tierSysId){
		NotesAdapterManager adapterManager = new NotesAdapterManager();
		
		for(int i=0;i<noteDetailList.size();i++){
			Audit audit = new AuditImpl();
			audit.setUser(user.getUserId());
			ProductQuestionareBO questionnareBo = (ProductQuestionareBO)noteDetailList.get(i);
			if(questionnareBo.isUnsavedData()){
				questionnareBo.setNotes_exists("N");
				int questionId = questionnareBo.getQuestionId();
				TierNotesAttachmentOverrideBO attachmentBo = new TierNotesAttachmentOverrideBO();
				attachmentBo.setPrimaryEntityId(productId);
				attachmentBo.setSecondaryEntityId(adminLevelOptionId);
				attachmentBo.setQuestionNumber(questionId);
				attachmentBo.setPrimaryEntityType("ATTACHPRODUCT");
				attachmentBo.setSecondaryEntityType("ATTACHQUESTION");
				attachmentBo.setNote_status("F");
				attachmentBo.setBnftDefnIdString(new Integer(benefitDefId).toString());
				attachmentBo.setBenefitComponentId(beneftComponentId);
				attachmentBo.setTierSysId(tierSysId);
				TierNotesAttachmentOverrideBO newattachmentBo=getNoteInfo(attachmentBo);
				if(null!=newattachmentBo && null!=newattachmentBo.getNoteId() && !("").equals(newattachmentBo.getNoteId())){
					attachmentBo.setNoteId(newattachmentBo.getNoteId());
					attachmentBo.setVersion(newattachmentBo.getVersion());
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
  public void getPossibleAnswerListForProduct(List locateResultList) throws SevereException{
  	  if (null != locateResultList || !locateResultList.isEmpty()) {

          QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();

          // Create the Adapter Service Access
          AdapterServicesAccess adapterServicesAccess = AdapterUtil
                  .getAdapterService();

          // Iterate through the list to get the possible answers for the
          // question
          for (int i = 0; i < locateResultList.size(); i++) {
              // Get the individual entitybenefitAdministration objects from
              // the list
          	ProductQuestionareBO productQuestionareBO = (ProductQuestionareBO) locateResultList
                      .get(i);
              int questionNumber = productQuestionareBO.getQuestionId();

              QuestionBO questionBO = new QuestionBO();

              questionBO.setQuestionNumber(questionNumber);

              // Call the getPossibleAnswers() of the question adaptermanager
              List possibleAnswerList = questionAdapterManager
                      .getPossibleAnswer(questionBO, adapterServicesAccess);
              List arrangedPossibleAnswerList = BusinessUtil.getRearrangedPossibleAnswerList(possibleAnswerList);
              // Set the possible answers list to the
              // entityBenefitAdministration
              productQuestionareBO.setPossibleAnswerList(arrangedPossibleAnswerList);
          }
      }
  }
  /*
   * this method is for getting possible answer list.
   * @param locateResultList
   *         this list contains  questions needed for possible answer.
   * @ returns answer list
   */
  public void getPossibleAnswerListForProduct(List locateResultList, HashMap allPossibleAnswerMap) 
  				throws SevereException{
  	
  	if (null != locateResultList || !locateResultList.isEmpty()) {

          QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();

          // Create the Adapter Service Access
          AdapterServicesAccess adapterServicesAccess = AdapterUtil
                  .getAdapterService();

          // Iterate through the list to get the possible answers for the
          // question
          for (int i = 0; i < locateResultList.size(); i++) {
              // Get the individual entitybenefitAdministration objects from
              // the list
          	ProductQuestionareBO productQuestionareBO = (ProductQuestionareBO) locateResultList
                      .get(i);
              int questionNumber = productQuestionareBO.getQuestionId();

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
			  List arrangedPossibleAnswerList = BusinessUtil.getRearrangedPossibleAnswerList(possibleAnswerList);
				
              // Set the possible answers list to the
              // entityBenefitAdministration
              productQuestionareBO.setPossibleAnswerList(arrangedPossibleAnswerList);
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

  public boolean persist(SaveProductQuestionareBO administrationBO,
          ProductBO productBO, Audit audit,
          boolean insertFlag) throws  SevereException {
      AdapterServicesAccess adapterServicesAccess = AdapterUtil
              .getAdapterService();
      long transactionId = AdapterUtil.getTransactionId();
      try {
      	ProductAdapterManager adapterManager = new ProductAdapterManager();
          AdapterUtil.beginTransaction(adapterServicesAccess);
          AdapterUtil.logBeginTranstn(transactionId , this ,"persist(ComponentsBenefitAdministrationBO administrationBO,BenefitComponentBO benefitComponentBO, Audit audit,boolean insertFlag)");
          adapterManager.saveQuestionnareForProduct(administrationBO, audit, adapterServicesAccess);
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
   * This method is for retrieving  the rule values ,that is overrided in the product component level.
   * @param benefitComponentId
   * @param productId
   * @param benefitComponentBO
   * @throws SevereException
   */
  public void getRule(int benefitComponentId,int productId,BenefitComponentBO benefitComponentBO) throws SevereException
  {
  	ProductComponentRule benefitComponentDetails=new ProductComponentRule();
  	
  	ProductAdapterManager adapterManager = new ProductAdapterManager();
  	
  	benefitComponentDetails.setBenefitComponentId(benefitComponentId);
  	benefitComponentDetails.setProductId(productId);
  	List rulelist = (ArrayList)adapterManager.getRuleForproductBenefitComponent(benefitComponentDetails);
  	if(null!=rulelist && !rulelist.isEmpty()){
  		int rulesize = rulelist.size();
  		for(int i=0;i<rulesize;i++){
  			ProductComponentRule ruleDet = (ProductComponentRule)rulelist.get(i);
  			benefitComponentBO.setRuleId(ruleDet.getRuleId());
  			benefitComponentBO.setRuleDesc(ruleDet.getRuleDesc());
			// Did the code change for Adjudication Rules i.e RuleType is added
 			benefitComponentBO.setRuleType(ruleDet.getStrRuleType());
  		}
  	}
  	
  }
  /**
   * this method used for call adapter operations to save overriden rule values .
   * 
   * @param benefitComponents
   * @param user
   * @return
   * @throws SevereException
   */
  
  public boolean saveRule(ProductComponentRule benefitComponents, String user)
  throws SevereException {
  	
  	AdapterServicesAccess serviceAccess = AdapterUtil.getAdapterService();
  	long transactionId = AdapterUtil.getTransactionId();
  	try {
  		AdapterUtil.beginTransaction(serviceAccess);
  		AdapterUtil.logBeginTranstn(transactionId, this,
  		"persist(ProductComponentRule benefitComponents, String user)");
  		adapterManager.saveRuleInfo(benefitComponents,user, serviceAccess);
  		AdapterUtil.endTransaction(serviceAccess);
  		AdapterUtil.logTheEndTransaction(transactionId, this,
  		"persist(ProductComponentRule benefitComponents, String user)");
  	} catch (SevereException ex) {
  		AdapterUtil.abortTransaction(serviceAccess);
  		AdapterUtil.logAbortTxn(transactionId, this,
  		"persist(ProductComponentRule benefitComponents, String user)");
  		List errorParams = new ArrayList(3);
  		String obj = ex.getClass().getName();
  		errorParams.add(obj);
  		errorParams.add(obj.getClass().getName());
  		throw new SevereException(
  				"Exception in persist ProductComponentRule benefitComponents in prodcutBusinessObjectBuilder",
				errorParams, ex);
  	} catch (Exception ex) {
  		AdapterUtil.abortTransaction(serviceAccess);
  		AdapterUtil.logAbortTxn(transactionId, this,
  		"persist(ProductComponentRule benefitComponents benefitComponents, String user)");
  		throw new SevereException("Unhandled Exception occured", null, ex);
  	}
  	return true;
  }
  public List retrieveQuestionNotes(NotesAttachmentOverrideBO overrideBO)throws SevereException{
 	List noteList = null;
 	NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
 	noteList=notesAdapterManager.getQuestionNote(overrideBO);
 	return noteList;
 	
 }
  public boolean persist(
  		NotesToQuestionAttachmentBO attachmentBO,
		ProductBO productBO, Audit audit, boolean insertFlag)
		throws SevereException {
	AdapterServicesAccess adapterServicesAccess = AdapterUtil
			.getAdapterService();
	long transactionId = AdapterUtil.getTransactionId();
	NotesAdapterManager adapterManager = new NotesAdapterManager();
	attachmentBO.setCreatedUser(audit.getUser());
	attachmentBO.setCreatedTimestamp(audit.getTime());
	attachmentBO.setLastUpdatedUser(audit.getUser());
	attachmentBO.setLastUpdatedTimestamp(audit.getTime());

	try {
		AdapterUtil.beginTransaction(adapterServicesAccess);
		AdapterUtil
				.logBeginTranstn(transactionId, this,
						"persist(NotesToQuestionAttachmentBO,ProductBO, Audit,boolean)");
	
		if(insertFlag){
			adapterManager.insertNotesAttachedToQuestion(
					attachmentBO, audit	);
		}else{
			adapterManager.updateNotesAttachedToQuestion(
					attachmentBO, audit	);
		}
		AdapterUtil.endTransaction(adapterServicesAccess);
		AdapterUtil
				.logTheEndTransaction(transactionId, this,
						"persist(NotesToQuestionAttachmentBO,ProductBO, Audit,boolean)");
	} catch (AdapterException ex) {
		AdapterUtil.abortTransaction(adapterServicesAccess);
		AdapterUtil
				.logAbortTxn(transactionId, this,
						"persist(NotesToQuestionAttachmentBO,ProductBO, Audit ,boolean)");
		throw new SevereException(
				"Persist for NotesToQuestionAttachmentBO failed.Unknown exception is caught",
				null, ex);
	}
	return true;
}


/**
 * @return
 * @throws SevereException
 */

public boolean delete(NotesToQuestionAttachmentBO attachmentBO,
		ProductBO productBO, Audit audit) throws SevereException,AdapterException {
	AdapterServicesAccess access = AdapterUtil.getAdapterService();
	NotesAdapterManager adapterManager = new NotesAdapterManager();
	long transactionId = AdapterUtil.getTransactionId();
    try {
    	access.beginTransaction();
    	AdapterUtil.logBeginTranstn(transactionId,this,"delete(NotesToQuestionAttachmentBO subObject,ProductBO mainObject, Audit audit)");
    	adapterManager.deleteNotesAttachedToQuestion(
				attachmentBO, audit	);
    	access.endTransaction();
    	AdapterUtil.logTheEndTransaction(transactionId,this,"delete(NotesToQuestionAttachmentBO subObject,ProductBO mainObject, Audit audit)");
    }catch (Exception se) {
    	access.abortTransaction();
    	AdapterUtil.logAbortTxn(transactionId,this,"delete(NotesToQuestionAttachmentBO subObject,ProductBO mainObject, Audit audit)");
        List errorParams = new ArrayList(2);
        String obj = se.getClass().getName();
        errorParams.add(obj);
        errorParams.add(obj.getClass().getName());
        throw new SevereException(
                "Exception occured in delete method in ProductBenefitBusinessObjectBuilder",
                errorParams, se);
    } 
    return true;
}
	/**
	 * Method used to insert/update the attached notes in an Admin Option at
	 * Product level
	 * 
	 * @param contractProductAONotesAttachmentBO
	 * @param contract
	 * @param audit
	 * @param insertFlag
	 * @throws SevereException
	 */
	public void persist(
			ContractProductAONotesAttachmentBO contractProductAONotesAttachmentBO,
			ProductBO productBO, Audit audit, boolean insertFlag)
			throws SevereException {

		NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
		
		contractProductAONotesAttachmentBO.setCreatedTimestamp(productBO
				.getCreatedTimestamp());
		contractProductAONotesAttachmentBO.setLastUpdatedTimestamp(productBO
				.getLastUpdatedTimestamp());
		contractProductAONotesAttachmentBO.setLastUpdatedUser(productBO
				.getLastUpdatedUser());
		contractProductAONotesAttachmentBO.setCreatedUser(productBO
				.getCreatedUser());

		try {

			if (insertFlag)
				notesAdapterManager.insertNotesAttachedToQuestion(
						contractProductAONotesAttachmentBO, audit);

		} catch (AdapterException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject ContractProductAONotesAttachmentBO, in ProductBusinessObjectBuilder",
					errorParams, exception);

		}
		try {

			if (!insertFlag)
				notesAdapterManager.updateNotesAttachedToQuestion(
						contractProductAONotesAttachmentBO, audit);

		} catch (AdapterException exception) {

			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new SevereException(
					"Exception occured in persist method , for updating the BusinessObject ContractProductAONotesAttachmentBO, in ProductBusinessObjectBuilder",
					errorParams, exception);

		}
	}

	/**
	 * 
	 * @param productBenefitTier
	 * @param productBO
	 * @param audit
	 * @return
	 * @throws SevereException
	 */
	  public boolean delete( BenefitTier productBenefitTier, ProductBO productBO, Audit audit) throws SevereException{
		  AdapterServicesAccess adapterServicesAccess = AdapterUtil
			.getAdapterService();
		  long transactionId = AdapterUtil.getTransactionId();
	    try {
	    	AdapterUtil.beginTransaction(adapterServicesAccess);
	    	AdapterUtil
			.logBeginTranstn(
					transactionId,
					this,
					"delete( BenefitTier productBenefitTier, ProductBO productBO, Audit audit)");
	      adapterManager.deleteProductTier(productBenefitTier, audit.getUser());
	      AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
					"delete( BenefitTier productBenefitTier, ProductBO productBO, Audit audit)");
	    } catch (SevereException ex)  {
	    	AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"delete( BenefitTier productBenefitTier, ProductBO productBO, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete method , for deleting the BusinessObject BenefitTier, in ProductBusinessObjectBuilder",
					errorParams, ex);
	    } catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
					"delete( BenefitTier productBenefitTier, ProductBO productBO, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete method , for deleting the BusinessObject BenefitTier, in ProductBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
					"delete( BenefitTier productBenefitTier, ProductBO productBO, Audit audit)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}

	    return true;
	  }
	
	/**
	 * Mehtod used to delete the attached notes in an Admin Option at Product
	 * level
	 * 
	 * @param contractProductAONotesAttachmentBO
	 * @param contract
	 * @param audit
	 * @throws SevereException
	 */
	public void delete(
			ContractProductAONotesAttachmentBO contractProductAONotesAttachmentBO,
			ProductBO productBO, Audit audit) throws SevereException {
		NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
		try {
			notesAdapterManager.deleteNotesAttachedToQuestion(
					contractProductAONotesAttachmentBO, audit);
		} catch (AdapterException exception) {
			List errorParams = new ArrayList();
			String obj = exception.getClass().getName();
			errorParams.add(obj);

			throw new SevereException(
					"Exception occured in delete method , for deleting the BusinessObject ContractProductAONotesAttachmentBO, in ProductBusinessObjectBuilder",
					errorParams, exception);
		}

	}
	/*
	 * @ param productStructureId
	 *
	 * @return proudctFamily
	 * @throws SevereException
	 */
	 public String getProductFamily(int productStructureId)throws SevereException{
	 	ProductStructureAdapterManager adaptermanger = new ProductStructureAdapterManager();
	  	String proudctFamily = null;
	  	proudctFamily = adaptermanger.getProductFamily(productStructureId);
	  	return proudctFamily;
	  }
	 
	 /*public List getTierDefnsForProduct(ProductBenefitDefinitionLocateCriteria 
     pdktBenefitDefinitionLocateCriteria)throws SevereException{
	ProductAdapterManager productAdapterManager = new ProductAdapterManager();		  
	List tierDefnsForProduct = productAdapterManager.getTierDefnsForProduct
	(pdktBenefitDefinitionLocateCriteria);
	return tierDefnsForProduct;
	}*/

	/**
	* The method will fetch the Tier Definitions for the Product
	* @param pdktBenefitDefinitionLocateCriteria
	* @param user
	* @return
	* @throws SevereException
	*/
	public List getTierDescAndDefIdForProductBenefit(ProductBenefitDefinitionLocateCriteria 
	     pdktBenefitDefinitionLocateCriteria,User user)throws SevereException{
		ProductAdapterManager productAdapterManager = new ProductAdapterManager();		  
		List resultList = productAdapterManager
						.getTierDefnsForProduct(pdktBenefitDefinitionLocateCriteria);	 	
		return resultList;//contains ProductTierDefnOverrideBO objects         	
	}
	
			
	 /***
	  * Deletes a Level from all Tiers
	  * @param tierDefinitionBO
	  * @param productBO
	  * @param audit
	  * @throws SevereException
	  */
	 
	  public void delete( TierDefinitionBO tierDefinitionBO, ProductBO productBO, Audit audit) throws SevereException{
		  AdapterServicesAccess adapterServicesAccess = AdapterUtil
			.getAdapterService();
		  long transactionId = AdapterUtil.getTransactionId();
	    try {
	    	AdapterUtil.beginTransaction(adapterServicesAccess);
	    	AdapterUtil
			.logBeginTranstn(
					transactionId,
					this,
					"delete( TierDefinitionBO tierDefinitionBO, ProductBO productBO, Audit audit)");
	      adapterManager.deleteLevelFromTier(tierDefinitionBO, audit.getUser());
	      AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
					"delete( TierDefinitionBO tierDefinitionBO, ProductBO productBO, Audit audit)");
	    } catch (SevereException ex)  {
	    	AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
					"delete( TierDefinitionBO tierDefinitionBO, ProductBO productBO, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete method , for deleting the Level from Tier (TierDefinitionBO), in ProductBusinessObjectBuilder",
					errorParams, ex);
	    } catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
					"delete( TierDefinitionBO tierDefinitionBO, ProductBO productBO, Audit audit)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in delete method , for deleting the Level from Tier (TierDefinitionBO), in ProductBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
					"delete( TierDefinitionBO tierDefinitionBO, ProductBO productBO, Audit audit)");
			throw new SevereException("Unhandled exception occured", null, ex);
		}

	  }
	  
	  /**
		 * The method will fetch the Tier definition ids of the
		 * Tiers added in product
		 * @param pdktBenefitDefinitionLocateCriteria
		 * @param user
		 * @return
		 * @throws SevereException
		 */
		public List getTiersAddedInProduct(ProductBenefitDefinitionLocateCriteria 
			     pdktBenefitDefinitionLocateCriteria,User user)throws SevereException{
			ProductAdapterManager productAdapterManager = new ProductAdapterManager();		  
			List resultList = productAdapterManager
							.getTiersAddedInProduct(pdktBenefitDefinitionLocateCriteria);	 	
			return resultList;//contains ProductTierDefnOverrideBO objects         	
		}
	 
		 public List retrieveQuestionTiernNotes(TierNotesAttachmentOverrideBO overrideBO)throws SevereException{
		 	List noteList = null;
		 	NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
		 	noteList=notesAdapterManager.getQuestionTierNote(overrideBO);
		 	return noteList;
		 	
		 }
		 public List retrieveQuestionTiernNotesForView(TierNotesAttachmentOverrideBO overrideBO)throws SevereException{
		 	List noteList = null;
		 	NotesAdapterManager notesAdapterManager = new NotesAdapterManager();
		 	noteList=notesAdapterManager.getOverridedQuestionTierNote(overrideBO);
		 	if(null!=noteList && noteList.size()>0 ){
		 		 ((TierNotesAttachmentOverrideBO)noteList.get(0)).setOverrideStatus("Y");	
			}
		 	return noteList;
		 	
		 }
		 
		 
		 public boolean persist(
		 		TierNotesAttachmentOverrideBO attachmentBO,
				ProductBO productBO, Audit audit, boolean insertFlag)
				throws SevereException {
			AdapterServicesAccess adapterServicesAccess = AdapterUtil
					.getAdapterService();
			long transactionId = AdapterUtil.getTransactionId();
			NotesAdapterManager adapterManager = new NotesAdapterManager();
			attachmentBO.setCreatedUser(audit.getUser());
			attachmentBO.setCreatedTimestamp(audit.getTime());
			attachmentBO.setLastUpdatedUser(audit.getUser());
			attachmentBO.setLastUpdatedTimestamp(audit.getTime());

			try {
				AdapterUtil.beginTransaction(adapterServicesAccess);
				AdapterUtil
						.logBeginTranstn(transactionId, this,
								"persist(NotesToQuestionAttachmentBO,ProductBO, Audit,boolean)");
			
				if(insertFlag){
					adapterManager.insertNotesAttachedToQuestion(
							attachmentBO, audit	);
				}else{
					adapterManager.updateNotesAttachedToQuestion(
							attachmentBO, audit	);
				}
				AdapterUtil.endTransaction(adapterServicesAccess);
				AdapterUtil
						.logTheEndTransaction(transactionId, this,
								"persist(NotesToQuestionAttachmentBO,ProductBO, Audit,boolean)");
			} catch (AdapterException ex) {
				AdapterUtil.abortTransaction(adapterServicesAccess);
				AdapterUtil
						.logAbortTxn(transactionId, this,
								"persist(NotesToQuestionAttachmentBO,ProductBO, Audit ,boolean)");
				throw new SevereException(
						"Persist for NotesToQuestionAttachmentBO failed.Unknown exception is caught",
						null, ex);
			}
			return true;
		}
		 
		 /**
		  * @return
		  * @throws SevereException
		  */

		 public boolean delete(TierNotesAttachmentOverrideBO attachmentBO,
		 		ProductBO productBO, Audit audit) throws SevereException,AdapterException {
		 	AdapterServicesAccess access = AdapterUtil.getAdapterService();
		 	NotesAdapterManager adapterManager = new NotesAdapterManager();
		 	long transactionId = AdapterUtil.getTransactionId();
		     try {
		     	access.beginTransaction();
		     	AdapterUtil.logBeginTranstn(transactionId,this,"delete(TierNotesAttachmentOverrideBO subObject,ProductBO mainObject, Audit audit)");
		     	adapterManager.deleteNotesAttachedToQuestion(
		 				attachmentBO, audit	);
		     	access.endTransaction();
		     	AdapterUtil.logTheEndTransaction(transactionId,this,"delete(TierNotesAttachmentOverrideBO subObject,ProductBO mainObject, Audit audit)");
		     }catch (Exception se) {
		     	access.abortTransaction();
		     	AdapterUtil.logAbortTxn(transactionId,this,"delete(TierNotesAttachmentOverrideBO subObject,ProductBO mainObject, Audit audit)");
		         List errorParams = new ArrayList(2);
		         String obj = se.getClass().getName();
		         errorParams.add(obj);
		         errorParams.add(obj.getClass().getName());
		         throw new SevereException(
		                 "Exception occured in delete method in ProductBenefitBusinessObjectBuilder",
		                 errorParams, se);
		     } 
		     return true;
		 }		 
		 
		 /**
		  * Retreives the rule details for Product.
		  * @param ruleDetailBO
		  */
		 public List getRuleListForProduct(RuleDetailBO ruleDetailBO) throws SevereException{
		 	
		 	Logger.logDebug("ProductBusinessObjectBuilder - Entering getRuleListForProduct(RuleDetailBO ruleDetailBO)");
		 	ProductAdapterManager productAdapterManager = new ProductAdapterManager();
		 	List ruleList = new ArrayList();
		 	List bcRuleList = null;
		 	List benefitRuleList = null;
		 	
		 	try {
				bcRuleList = productAdapterManager.getRuleListBC(ruleDetailBO.getProductId());
				if(null!=bcRuleList && !bcRuleList.isEmpty()){
					ruleList.add(bcRuleList);
				}
				
				benefitRuleList = productAdapterManager.getRuleListBenefit((ruleDetailBO.getProductId()));
				if(null!=benefitRuleList && !benefitRuleList.isEmpty()){
					ruleList.add(benefitRuleList);
				}
			} catch (AdapterException e) {
				Logger.logDebug("Adapter Exception occurred-getRuleListForProduct(RuleDetailBO ruleDetailBO)");
				throw new SevereException("Adapter Exception occurred-getRuleListForProduct(RuleDetailBO ruleDetailBO)",null,e);
			}
		 	
		 	return ruleList;
		 }
		 
		 /*
		  * mrthod to get product rule count.
		  */
		 public int getProductRuleCount(int productKey,String ruleType)throws SevereException{
		 	List ruleCountList = null;
		 	int rulecount =0;
		 	ProductAdapterManager productAdapterManager = new ProductAdapterManager();
		 	
		 	ruleCountList = productAdapterManager.getProductRuleCount(productKey,ruleType);
		 	
		 	if(ruleCountList!= null && ruleCountList.size()>0){
		 		rulecount = ((ProductRuleAssociationBO)ruleCountList.get(0)).getRuleCount();
		 	}
		 	return rulecount;
		 }	
		public void deleteBnftTierDetails(int productId, int benCompSysId, List benefitList){
			
			ProductAdapterManager productAdapterManager = new ProductAdapterManager();
			
			productAdapterManager.deleteBnftTierDetails(productId,benCompSysId,benefitList);
			
		}
		public void deleteBnftTierDetails(int productId, int benCompSysId, int benefitId){
			
			ProductAdapterManager productAdapterManager = new ProductAdapterManager();
			
			productAdapterManager.deleteBnftTierDetails(productId,benCompSysId,benefitId);
			
		}
		 
		 /**
		 * The method saves the Product Benefit General Information. 
		 * @param productBenefitGeneralInfoBO
		 * @param productBO
		 * @param audit
		 * @param insertFlag
		 * @return
		 * @throws SevereException
		 */
		public boolean persist(
		        ProductBenefitGeneralInfoBO productBenefitGeneralInfoBO,
		        ProductBO productBO, Audit audit, boolean insertFlag)
					throws SevereException {
			AdapterServicesAccess adapterServicesAccess = AdapterUtil
					.getAdapterService();
			long transactionId = AdapterUtil.getTransactionId();
			ProductAdapterManager productAdapterManager = 
			    new ProductAdapterManager();
			
			ProductTierDefnOverrideBO productTierDefnOverrideBO = 
					productBenefitGeneralInfoBO.getProductTierDefnOverrideBO();			
			List producTierDefinitionsList = productTierDefnOverrideBO
					.getProductTierDefinitionsList();
			try {
				AdapterUtil.beginTransaction(adapterServicesAccess);
				AdapterUtil
						.logBeginTranstn(transactionId, this,
								"persist(ProductBenefitGeneralInfoBO," +
								"ProductBO,Audit,boolean)");
				//Updating TIER
				//To update the Tier Definitions,first deleting the
				//existing Tier Definitions for the particular Product
				if(!insertFlag){ 
				    ProductTierDefnOverrideBO pdktTierDefnOverrideBO = 
				         new ProductTierDefnOverrideBO();
				    pdktTierDefnOverrideBO.setProductId(productTierDefnOverrideBO.getProductId());
				    pdktTierDefnOverrideBO.setBenefitComponentId(productTierDefnOverrideBO
				            .getBenefitComponentId());
				    pdktTierDefnOverrideBO.setBenefitDefinitionId(productTierDefnOverrideBO
				            .getBenefitDefinitionId());
				    productAdapterManager.deleteProductTierDefinition
						(pdktTierDefnOverrideBO,audit);
				}	
				//insert the Tier Definitions
				int listSize = producTierDefinitionsList.size();
				for (int i = 0; i < listSize; i++) {
				    ProductTierDefnOverrideBO prodTierDefnOverrideBO = 
				        (ProductTierDefnOverrideBO) producTierDefinitionsList.get(i);				
				    prodTierDefnOverrideBO.setCreatedUser(audit.getUser());
				    prodTierDefnOverrideBO.setCreatedTimestamp(audit.getTime());
				    prodTierDefnOverrideBO.setLastUpdatedUser(audit.getUser());
				    prodTierDefnOverrideBO.setLastUpdatedTimestamp(audit.getTime());	
				    
				    productAdapterManager.persistProductTierDefinition
						(prodTierDefnOverrideBO,audit);//upating PROD_TIER_DEFN_OVRD table	   		    		    
				}				
				// Updating RULE
				ProductRuleIdBO productRuleIdBO = 
					productBenefitGeneralInfoBO.getProductRuleIdBO();
				productRuleIdBO.setCreatedUser(audit.getUser());
			 	productRuleIdBO.setCreatedTimestamp(audit.getTime());
			 	productRuleIdBO.setLastUpdatedUser(audit.getUser());
			 	productRuleIdBO.setLastUpdatedTimestamp(audit.getTime());	
			 	
			 	if(!insertFlag){//updating PROD_BNFT_RULE_OVRD table
		 			adapterManager.persistProductBenefitRuleInfo(
		 					productRuleIdBO, audit);
		 		}		
				AdapterUtil.endTransaction(adapterServicesAccess);
				AdapterUtil
						.logTheEndTransaction(transactionId, this,
								"persist(ProductBenefitGeneralInfoBO," +
								"ProductBO,Audit,boolean)");
			} catch (AdapterException ex) {
				AdapterUtil.abortTransaction(adapterServicesAccess);
				AdapterUtil
						.logAbortTxn(transactionId, this,
								"persist(ProductBenefitGeneralInfoBO," +
								"ProductBO,Audit ,boolean)");
				throw new SevereException(
						"Persist for ProductBenefitGeneralInfoBO failed." +
						"Unknown exception is caught",
						null, ex);
			}
			return true;
		}
		
		private List splitComponentList(List componentList){
			List splitList1 = new ArrayList();
			List splitList2 = new ArrayList();
			List splitList3 = new ArrayList();
			List splitList4 = new ArrayList();
			for(int i=0;i<componentList.size();i++){
				if(i%4 == 0){
					splitList1.add(componentList.get(i));
				} else  if(i % 4 == 1) {
					splitList2.add(componentList.get(i));
				} else if(i % 4 == 2) {
					splitList3.add(componentList.get(i));
				} else if(i % 4 == 3) {
					splitList4.add(componentList.get(i));
				}
			}
			List splitListCollection = new ArrayList(4);
			splitListCollection.add(splitList1);
			splitListCollection.add(splitList2);
			splitListCollection.add(splitList3);
			splitListCollection.add(splitList4);
			return splitListCollection;
		}
		
		/**
		 * Main method for returning the details of rule details associated to a product
		 * @param reportCriteria
		 * @return A map containing two lists
		 * @throws SevereException
		 */
		public Map getProductRuleReport(RuleLocateCriteria reportCriteria) throws SevereException {
			
			Map listMap = new LinkedHashMap();//Map which holds the rule list and group rule.
			Map gropuRuleIdMap = new LinkedHashMap();//Map which holds the group rule ids asscoaited to benefit and benefit component
			
			List resultList = new ArrayList(); //Main List which holds the final result
			List tempResultList = null; // Temp list which holds individual query result
			List groupRuleIds = new ArrayList();//List of group rule ids assocaited to exclusion rule,denial rule,um rule.
			
			List tempResultListWithGroup = null;// List holds the group rule details for UM rule,Denail Rule,Exclusion Rule
			List tempListwithoutHeader = new ArrayList();//List holds all the rule details in tempResultListWithGroup list except the header rules
			List tempListwithtHeader = new ArrayList();
			
			List finalListWithGroup = new ArrayList();//Final list which holds the values from tempResultListWithGroup and tempResultListWithGroupForHeader
			List groupWithBnftandBnftCmpntList = new ArrayList();//List of benefit and benefit component associated to group rule
			
			/**Exclusion/Denial/UM Rule Extract **/
			if((WebConstants.TRUE).equalsIgnoreCase(reportCriteria.getExtractExclusionChecked())
					|| (WebConstants.TRUE).equalsIgnoreCase(reportCriteria.getExtractDenialChecked())
					|| (WebConstants.TRUE).equalsIgnoreCase(reportCriteria.getExtractUMChecked())){
				
				List gnrtdRuleIDCondition = new ArrayList();	
				
				if((WebConstants.TRUE).equalsIgnoreCase(reportCriteria.getExtractExclusionChecked())){
					gnrtdRuleIDCondition.add("&");
				}
				if((WebConstants.TRUE).equalsIgnoreCase(reportCriteria.getExtractDenialChecked())){
					gnrtdRuleIDCondition.add("*");
				}
				if((WebConstants.TRUE).equalsIgnoreCase(reportCriteria.getExtractUMChecked())){
					gnrtdRuleIDCondition.add("#");
				}
				
				tempResultList = new ProductAdapterManager().getUmDenailAndExclusionDetailsForProduct(reportCriteria.getProductSysId(),gnrtdRuleIDCondition); 
				/*-- for separating rules and group rules -- */
				List tempResultListNoGroup = new ArrayList();	
				
				for(Iterator iterator =tempResultList.iterator();iterator.hasNext();){
					RuleReportBO reportBO = (RuleReportBO)iterator.next();
					if(null != reportBO.getRuleGrpInd() && reportBO.getRuleGrpInd().equals("N")){
						tempResultListNoGroup.add(reportBO);
					}else{
						BusinessUtil.populateGroupRuleList(reportBO.getRuleID(),groupRuleIds);
					}			
				}
				resultList.addAll(tempResultListNoGroup);
			}
			
			/*---------    Header Rule  ---------*/
			if((WebConstants.TRUE).equalsIgnoreCase(reportCriteria.getExtractHeaderChecked())){
				
				tempResultList = new ProductAdapterManager().getHeaderRuleDetailsForProduct(reportCriteria.getProductSysId());
				/*-- for separating rules and group rules -- */
				List tempResultListNoGroup = new ArrayList();		
				
				for(Iterator iterator =tempResultList.iterator();iterator.hasNext();){
					RuleReportBO reportBO = (RuleReportBO)iterator.next();
					if(null != reportBO.getRuleGrpInd() && reportBO.getRuleGrpInd().equals("N")){
						tempResultListNoGroup.add(reportBO);
					}else{
						if(null != reportBO.getRuleID()){
							
							BusinessUtil.populateGroupRuleList(reportBO.getRuleID(),groupRuleIds);
							
							List benifitUtil = (ArrayList) gropuRuleIdMap.get(reportBO.getRuleID());
							if(null == benifitUtil){
								benifitUtil = new ArrayList();
								benifitUtil.add(reportBO.getBenefitComponent()+"`"+reportBO.getBenefit());
								gropuRuleIdMap.put(reportBO.getRuleID(),benifitUtil);
							}else{
								benifitUtil.add(reportBO.getBenefitComponent()+"`"+reportBO.getBenefit());
							}
						}
					}			
				}
				/*--------------------------------------------*/
				
				if(null != tempResultListNoGroup && !tempResultListNoGroup.isEmpty()){
					Collections.sort(tempResultListNoGroup, new RuleReportSort());
				}
				resultList.addAll(tempResultListNoGroup);
				Logger.logInfo("Main list size after fetching the Header rule: "+resultList.size());
			}
			
			/*----- Fetching Rules Associated to a group for UM/Denial/Exclusion/Header Rules --------*/
			if(null != groupRuleIds && !groupRuleIds.isEmpty()){
				
				tempResultListWithGroup = new ProductAdapterManager().getGroupRuleDetailsForProduct(groupRuleIds);
				/**
				 * Iterate through the tempResultListWithGroup list to get a seperate list
				 * without header rules.
				 */
				for(Iterator iterator = tempResultListWithGroup.iterator();iterator.hasNext();){
					RuleReportBO reportBO = (RuleReportBO)iterator.next();
					if(null != reportBO.getRuleTypeDesc() && !reportBO.getRuleTypeDesc().equals(WebConstants.HEADER_RULE_DESC)){
						tempListwithoutHeader.add(reportBO);
					}else{
						tempListwithtHeader.add(reportBO);
					}
				}
				finalListWithGroup.addAll(tempListwithoutHeader);
			}
			/** ------ CODE FOR SETTING BENEFT AND BENEFIT COMPONENET NAME TO RULE OBJECT FOR HEADER RULE ------ **/
			
			if(null != tempListwithtHeader && !tempListwithtHeader.isEmpty()){
				
				for(Iterator iterator = tempListwithtHeader.iterator();iterator.hasNext();){
					RuleReportBO reportBO = (RuleReportBO)iterator.next();
					if(reportBO.getRuleTypeDesc().equals(WebConstants.HEADER_RULE_DESC)){
						Set groupRuleIdMapKeySet = gropuRuleIdMap.keySet();
						Iterator groupRuleIdMapKeySetIterator = groupRuleIdMapKeySet.iterator();
						/**
						 * Iterate through the map to check wheather the map key and group rule id key are equal.If its equal get the list 
						 * associated to the group rule from map.
						 */
						while(groupRuleIdMapKeySetIterator.hasNext()){
							
							Object groupRuleIdKey = groupRuleIdMapKeySetIterator.next();                
							String groupRuleId = groupRuleIdKey.toString();
							
							if(groupRuleId.equals(reportBO.getGrpRuleId())){
								List bnftAndbnftCmpnts = (ArrayList) gropuRuleIdMap.get(groupRuleIdKey);
								/**
								 * Iterate through the list of benefit and benefit component and set the values to the object 
								 * by creating a copy of the RuleReportBO object.
								 */
								for(Iterator bnftIterator = bnftAndbnftCmpnts.iterator();bnftIterator.hasNext();){
									/** Creating a copy of the RuleReportBO object */
									RuleReportBO ruleReportBOCopy = (RuleReportBO)SerializationUtils.clone(reportBO);
									
									String bnftWithCmpnt = (String) bnftIterator.next();
									String array[] = bnftWithCmpnt.split("`");
									
									ruleReportBOCopy.setBenefitComponent(array[0]);
									if(null == array[1] || WebConstants.NULL.equals(array[1])){
										ruleReportBOCopy.setBenefit("");
									}
									else{
										ruleReportBOCopy.setBenefit(array[1]);
									}
									
									groupWithBnftandBnftCmpntList.add(ruleReportBOCopy);
									
								}
							}
						}
					}
				}
			}
			
			if(null != groupWithBnftandBnftCmpntList && !groupWithBnftandBnftCmpntList.isEmpty()){
				finalListWithGroup.addAll(groupWithBnftandBnftCmpntList);
			}
			
			if(null!= resultList && !resultList.isEmpty()){
				listMap.put(WebConstants.RULES_LIST_KEY_NAME,resultList);
			}
			if(null!= finalListWithGroup && !finalListWithGroup.isEmpty()){
				listMap.put(WebConstants.GROUP_RULES_LIST_KEY_NAME,finalListWithGroup);
			}
			
			return listMap;
		}

}
