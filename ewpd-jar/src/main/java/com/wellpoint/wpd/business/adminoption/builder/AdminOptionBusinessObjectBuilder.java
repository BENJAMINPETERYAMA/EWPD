/*
 * AdminOptionBusinessObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.adminoption.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.adminoption.adapter.AdminOptionAdapterManager;
import com.wellpoint.wpd.business.adminoption.locatecriteria.AdminOptionLocateCriteria;
import com.wellpoint.wpd.business.adminoption.locatecriteria.AdminOptionQuestionLocateCriteria;
import com.wellpoint.wpd.business.adminoption.locatecriteria.ChildQuestionnaireLocateCriteria;
import com.wellpoint.wpd.business.adminoption.locatecriteria.RootQuestionnaireLocateCriteria;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.common.adapter.SequenceAdapterManager;
import com.wellpoint.wpd.business.common.util.DomainUtil;
import com.wellpoint.wpd.business.standardbenefit.adapter.AddQuestionAdapterManager;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.adminoption.bo.AdminOptionBO;
import com.wellpoint.wpd.common.adminoption.bo.AdminOptionTempBO;
import com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionBO;
import com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionnaireBO;
import com.wellpoint.wpd.common.adminoption.bo.AssociatedQuestionnaireCpyBO;
import com.wellpoint.wpd.common.adminoption.bo.ChildQuestionnaireBO;
import com.wellpoint.wpd.common.adminoption.bo.RootQuestionnaireBO;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.domain.bo.DomainDetail;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.web.util.WebConstants;

/**
 * BusinessObjectBuilder class for Admin Option.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class AdminOptionBusinessObjectBuilder {

	
    private static final String ADMINOPTION_VIEW_ALL = WebConstants.VIEW_ALL;

    /**
     * Constructor for the Admin Option BO Builder
     *  
     */
    public AdminOptionBusinessObjectBuilder() {
    }


    /**
     * Method to persist Admin Option details.
     * 
     * @param adminOptionBO
     * @param audit
     * @param insertFlag
     * @return boolean
     * @throws SevereException
     */

    public boolean persist(AdminOptionBO adminOptionBO, Audit audit,
            boolean insertFlag) throws SevereException {
    	try{
	        if (insertFlag) {
	            // insert logic
	        	/*if(adminOptionBO.getVersion() <=1){
	        		adminOptionBO.setAdminOptionParentSysId(adminOptionBO.getAdminOptionParentSysId());
	        	}*/
	            this.getAdminOptionAdapterManager().persistAdminOption(
	                    adminOptionBO, audit);
	        } else if (!insertFlag) {
	        	if(adminOptionBO.getVersion() ==1 && adminOptionBO.getStatus().equals(BusinessConstants.MSG_CHECKED_IN)){
	        		AdminOptionTempBO adminOptionTempBO = new AdminOptionTempBO();
	        		adminOptionTempBO.setAdminOptionId(adminOptionBO.getAdminOptionId());
	        		adminOptionTempBO.setLastUpdatedTimestamp(adminOptionBO.getLastUpdatedTimestamp());
	        		adminOptionTempBO.setLastUpdatedUser(adminOptionBO.getLastUpdatedUser());
	        		adminOptionTempBO.setTermId(adminOptionBO.getTermId());
	        		adminOptionTempBO.setQualifierId(adminOptionBO.getQualifierId());
	        		adminOptionTempBO.setVersion(adminOptionBO.getVersion());
	        		adminOptionTempBO.setStatus(adminOptionBO.getStatus());
		            this.getAdminOptionAdapterManager().updateAdminOption(
		            		adminOptionTempBO, audit);
		            updateQuestionnareSeqlvl(adminOptionBO,audit);
	        	}
	        	else{
		            // update logic
		            this.getAdminOptionAdapterManager().updateAdminOption(
		                    adminOptionBO, audit);
		            updateQuestionnareSeqlvl(adminOptionBO,audit);
	        	}
	        }
    	}catch(AdapterException ex){
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured while persisting Admin Option details in AdminOptionBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e){
        	throw new SevereException("Unhandled exception is caught",null,e);
        }
        return true;
    }
    /**
     * Method to retrieve associated questions  having duplicate reference in an Admin Option
     * 
     * @param adminOptionId
     * @return List
     * @throws SevereException,AdapterException
     */
    public List getduplicateReferenceQuesations(int adminOptionId)throws SevereException,AdapterException{
    	return  this.getAdminOptionAdapterManager().getduplicateReferenceQuesations(adminOptionId);
    }

    /**
     * Method to persist Associated Questions for Admin Option.
     * 
     * @param associatedQuestionBO
     * @param adminOptionBO
     * @param audit
     * @param insertFlag
     * @return boolean
     * @throws SevereException
     */
    public boolean persist(AssociatedQuestionBO associatedQuestionBO,
            AdminOptionBO adminOptionBO, Audit audit, boolean insertFlag)
            throws SevereException {
    	try{
	        if (insertFlag) {
	            // insert logic
	            this.getAdminOptionAdapterManager().persistAdminOptionQuestion(
	                    associatedQuestionBO, adminOptionBO, audit);
	        } else if (!insertFlag) {
	            // update logic
	            this.getAdminOptionAdapterManager().updateAdminOptionQuestion(
	                    associatedQuestionBO, adminOptionBO, audit);
	        }
    	}catch(AdapterException ex){
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured while persisting Associated Questions for Admin Option in AdminOptionBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e){
        	throw new SevereException("Unhandled exception is caught",null,e);
        }
        return true;
    }
    
    /**
     * Method to Search Associated Questionnaire for Admin Option.
     * 
     * @param associatedQuestionBO
     * @param adminOptionBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public List getQuestionnaire(int adminOptionId) throws SevereException{
        try{
        return this.getAdminOptionAdapterManager().getQuestionnaire(adminOptionId);
        }catch(AdapterException ex){
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured while retreiving the associated Questionnaire for Admin Option in AdminOptionBusinessObjectBuilder",
                    errorParams, ex);
        }catch (Exception e){
        	throw new SevereException("Unhandled exception is caught",null,e);
        }
       
    }
    /**
     * 
     * @param questionnaireIds
     * @return
     * @throws SevereException
     */
    public List getQuestionnaireForPR(List questionnaireIds) throws SevereException{
        try{
        return this.getAdminOptionAdapterManager().getChildQuestionnaireForPRUpdate(questionnaireIds);
        }catch(AdapterException ex){
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured while retreiving the associated Questionnaire for PR Update in AdminOptionBusinessObjectBuilder",
                    errorParams, ex);
        }catch (Exception e){
        	throw new SevereException("Unhandled exception is caught",null,e);
        }
       
    }
    /**
     * Method to Search Associated Questionnaire for Admin Option.
     * 
     * @param associatedQuestionBO
     * @param adminOptionBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public List getProductViewQuestionnaire(int adminOptionId, int productId) throws SevereException{
        try{
        return this.getAdminOptionAdapterManager().getProductViewQuestionnaire(adminOptionId,productId);
        }catch(AdapterException ex){
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured while retreiving the associated Questionnaire for Admin Option in AdminOptionBusinessObjectBuilder",
                    errorParams, ex);
        }catch (Exception e){
        	throw new SevereException("Unhandled exception is caught",null,e);
        }
       
    }
    /**
     * Method to Search Associated Questionnaire for Admin Option.
     * 
     * @param associatedQuestionBO
     * @param adminOptionBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public List getContractViewQuestionnaire(int adminOptionId, int contractSysId) throws SevereException{
        try{
        return this.getAdminOptionAdapterManager().getContractViewQuestionnaire(adminOptionId,contractSysId);
        }catch(AdapterException ex){
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured while retreiving the associated Questionnaire for Admin Option in AdminOptionBusinessObjectBuilder",
                    errorParams, ex);
        }catch (Exception e){
        	throw new SevereException("Unhandled exception is caught",null,e);
        }
       
    }
    
    
    /**
	 * Persists the ProductStructureBO
	 * 
	 * @param productStructureBO
	 * @param audit
	 * @param insertFlag
	 * @return boolean
	 * @throws SevereException,AdapterException
	 * @throws AdapterException
	 */
	public boolean persist(RootQuestionnaireBO rootQuestionnaireBO, AdminOptionBO adminOptionBO, Audit audit,
			boolean insertFlag) throws SevereException {
		AdminOptionAdapterManager adminOptionAdapterManager = new AdminOptionAdapterManager();
		AdapterServicesAccess adapterServicesAccess = AdapterUtil
				.getAdapterService();
		long transactionId = AdapterUtil.getTransactionId();
		DomainDetail domainDetail = null;
		List questionnaireList = null;
		try {
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil
					.logBeginTranstn(
							transactionId,
							this,
							"persist(RootQuestionnaireBO rootQuestionnaireBO, Audit audit,boolean insertFlag)");
			questionnaireList = rootQuestionnaireBO.getRootQuestionnaires();
			RootQuestionnaireBO rootQuestionnaireBO2 = null;
			int maxSeq = 0;
			if(insertFlag){
				List locateResults = adminOptionAdapterManager.getMaxVersionForAdminOption(adminOptionBO.getAdminOptionId());
				if(null != locateResults && !locateResults.isEmpty() && locateResults.size()>0){
					RootQuestionnaireBO rootQuestionnaireBO3 = (RootQuestionnaireBO) locateResults.get(0);
					maxSeq = rootQuestionnaireBO3.getSequenceNumber();
				}
			}
			
			if(null != questionnaireList && !questionnaireList.isEmpty()){
				for(int i = 0; i < questionnaireList.size(); i++){
					rootQuestionnaireBO2 = (RootQuestionnaireBO)questionnaireList.get(i);					
					rootQuestionnaireBO2.setLastUpdatedUser(audit.getUser());
					rootQuestionnaireBO2.setLastUpdatedTimestamp(audit.getTime());
					if(insertFlag){
						SequenceAdapterManager sequenceManager = new SequenceAdapterManager();
						rootQuestionnaireBO2.setSequenceNumber(maxSeq + 1);
						maxSeq = maxSeq + 1;
						rootQuestionnaireBO2.setQuestionnaireHierachySystemId(sequenceManager
								.getNextQuestionnaireHierarchySequence());
	
						rootQuestionnaireBO2.setCreatedUser(audit.getUser());
						rootQuestionnaireBO2.setCreatedTimestamp(audit.getTime());
	
						
						rootQuestionnaireBO2
									.setParentHierachySystemId(rootQuestionnaireBO2
											.getQuestionnaireHierachySystemId());
						
						adminOptionAdapterManager.addRootQuestion(
								rootQuestionnaireBO2, adminOptionBO, adapterServicesAccess);
	//					 insert the domain information.
				        domainDetail = createDomainDetail(rootQuestionnaireBO2);
				        DomainUtil.persistQuestionnaireAssociatedDomains
											(domainDetail, adapterServicesAccess);
					}else{					
						adminOptionAdapterManager.updateRootQuestion(
							rootQuestionnaireBO2, adminOptionBO, adapterServicesAccess);
					}
				}
			}
	        	// call the delete procedure.
			if(!insertFlag){
	        	if(null != rootQuestionnaireBO.getQuestionnairesToDelete()){
	        		if(null == adminOptionAdapterManager){
	        			adminOptionAdapterManager = new AdminOptionAdapterManager();
	        		}
	        		adminOptionAdapterManager.deleteAssociatedQuestionnaire
						(rootQuestionnaireBO.getQuestionnairesToDelete(),
								adminOptionBO.getAdminOptionId(),adapterServicesAccess);
	        	}
			}			
			

			AdapterUtil.endTransaction(adapterServicesAccess);
			AdapterUtil
					.logTheEndTransaction(
							transactionId,
							this,
							"persist(RootQuestionnaireBO rootQuestionnaireBO, Audit audit,boolean insertFlag)");
		}  catch (AdapterException ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(RootQuestionnaireBO rootQuestionnaireBO, Audit audit,boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject RootQuestionnaireBO, in AdminOptionBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			AdapterUtil.abortTransaction(adapterServicesAccess);
			AdapterUtil
					.logAbortTxn(
							transactionId,
							this,
							"persist(RootQuestionnaireBO rootQuestionnaireBO, Audit audit,boolean insertFlag)");
			List errorParams = new ArrayList(2);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persist method , for persisting the BusinessObject RootQuestionnaireBO, in AdminOptionBusinessObjectBuilder",
					errorParams, ex);
		} 

		return true;
	}

    /**
     * Method to delete Associated Questions for Admin Option.
     * 
     * @param associatedQuestionBO
     * @param adminOptionBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean delete(AssociatedQuestionBO associatedQuestionBO,
            AdminOptionBO adminOptionBO, Audit audit) throws SevereException {
    	try{
	        if (this.getAdminOptionAdapterManager().deleteAdminOptionQuestion(
	                associatedQuestionBO, adminOptionBO, audit)) {
	            List updatedAdminQuestionList = this.getAdminOptionAdapterManager()
	                    .getAdminOptionQuestion(adminOptionBO.getAdminOptionId());
	            List orderedAdminQuestionList = null;
	            
	            //updates the sequence number of the admin option questions
	            if(null!=updatedAdminQuestionList){
	            	orderedAdminQuestionList = new ArrayList(updatedAdminQuestionList.size());
	            	if(null != orderedAdminQuestionList){
	            		int size = updatedAdminQuestionList.size();
			            AssociatedQuestionBO tempAssociatedQuestionBO = new AssociatedQuestionBO();
			            for (int i = 0; i < size; i++) {
			                AssociatedQuestionBO associatedAdminQuestionBO = (AssociatedQuestionBO) updatedAdminQuestionList
			                        .get(i);
			                associatedAdminQuestionBO.setSeqNumber(i + 1);
			                orderedAdminQuestionList.add(associatedAdminQuestionBO);
			            }
			            tempAssociatedQuestionBO
			                    .setAssociatedQuestionList(orderedAdminQuestionList);
			            this.getAdminOptionAdapterManager().adminOptionQuestionUpdate(
			                    tempAssociatedQuestionBO, adminOptionBO, audit);
	            	}
	            }
	        }
    	}catch(AdapterException ex){
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in delete method for deleting Associated Questions for Admin Option in AdminOptionBusinessObjectBuilder",
                    errorParams, ex);
        } catch (Exception e){
        	throw new SevereException("Unhandled exception is caught",null,e);
        }
        return true;
    }


    /**
     * Method to retrieve the Admin Option details
     * 
     * @param adminOptionBO
     * @return AdminOptionBO
     */
    public AdminOptionBO retrieve(AdminOptionBO adminOptionBO)
            throws SevereException {
        return (AdminOptionBO) this.getAdminOptionAdapterManager().retrieve(
                adminOptionBO);
    }


    /**
     * Method to retrieve Latest Version
     * 
     * @param adminOptionBO
     * @return BusinessObject
     * @throws SevereException
     */
    public BusinessObject retrieveLatestVersion(AdminOptionBO adminOptionBO)
            throws SevereException {
        return  this.getAdminOptionAdapterManager()
                .retrieveLatestVersion(adminOptionBO);
    }


    /**
     * Method to retrieve Latest Version Number
     * 
     * @param adminOptionBO
     * @return int
     * @throws SevereException
     */
    public int retrieveLatestVersionNumber(AdminOptionBO adminOptionBO)
            throws SevereException {
        return this.getAdminOptionAdapterManager().retrieveLatestVersionNumber(
                adminOptionBO);

    }


    /**
     * Method to retrieve the Admin Option details using adminName.
     * 
     * @param adminOptionBO
     * @param user
     * @return AdminOptionBO
     * @throws SevereException
     */
    public AdminOptionBO retrieveAdminName(AdminOptionBO adminOptionBO,
            User user) throws SevereException {
        return this.getAdminOptionAdapterManager()
                .retrieveByName(adminOptionBO);
    }


    /**
     * Method to locate admin Option question
     * 
     * @param adminOptionQuestionLocateCriteria
     * @param user
     * @return adminOptionQuestionResults
     * @throws SevereException
     */
    public LocateResults locate(
            AdminOptionQuestionLocateCriteria adminOptionQuestionLocateCriteria,
            User user) throws SevereException {
        LocateResults adminOptionQuestionResults = null;
        if(null != adminOptionQuestionLocateCriteria.getAction() && 
        		adminOptionQuestionLocateCriteria.getAction().equals(BusinessConstants.LOCATE_ROOT_QUESTIONS)){
        	adminOptionQuestionResults = this.getAdminOptionAdapterManager()
            	.locateAdminOptionRootQuestion(adminOptionQuestionLocateCriteria);
        }else{
        
        adminOptionQuestionResults = this.getAdminOptionAdapterManager()
                .searchAdminOptionQuestion(adminOptionQuestionLocateCriteria);
        }
        return adminOptionQuestionResults;
    }


    /**
     * Method to locate admin option
     * 
     * @param adminOptionLocateCriteria
     * @param user
     * @return locateResults
     * @throws SevereException
     */
    public LocateResults locate(
            AdminOptionLocateCriteria adminOptionLocateCriteria, User user)
            throws SevereException {
        LocateResults locateResults = null;
        //if the action is view all..
        if (ADMINOPTION_VIEW_ALL.equals(adminOptionLocateCriteria.getAction())) {
            locateResults = this.getAdminOptionAdapterManager()
                    .viewAllAdminOption(adminOptionLocateCriteria.getAdminNameCriteria());
        } else {
            locateResults = this.getAdminOptionAdapterManager()
                    .locateAdminOption(adminOptionLocateCriteria, user);

            locateResults.setLatestVersion(true);
            
        }
        return locateResults;
    }


    /**
     * Method to delete an Admin Option.
     * 
     * @param adminOptionBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean delete(AdminOptionBO adminOptionBO, Audit audit)
            throws SevereException ,AdapterException{
        return this.getAdminOptionAdapterManager().deleteAdminOption(
                adminOptionBO, audit);
    }


    /**
     * Method to delete latest version.
     * 
     * @param adminOptionBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean deleteLatestVersion(AdminOptionBO adminOptionBO, Audit audit)
            throws SevereException,AdapterException {
        return this.getAdminOptionAdapterManager().deleteAdminOption(
                adminOptionBO, audit);
    }


    /**
     * Method to check duplicate Admin Option Question.
     * 
     * @param associatedQuestionBO
     * @return AssociatedQuestionBO
     * @throws SevereException
     */
    public AssociatedQuestionBO checkDuplicateAdminOptionQuestion(
            AssociatedQuestionBO associatedQuestionBO) throws SevereException {
        return this.getAdminOptionAdapterManager()
                .checkDuplicateAdminOptionQuestion(associatedQuestionBO);
    }


    /**
     * Method to copy associated questions of Admin Option
     * 
     * @param srcBO
     * @param trgtBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean copy(AdminOptionBO srcBO, AdminOptionBO trgtBO, Audit audit)
            throws SevereException,AdapterException {
        return this.getAdminOptionAdapterManager().copyAssocaitedQuestions(
                srcBO, trgtBO, audit);
    }


    /**
     * Method to copy associated questions of Admin Option for Check Out option.
     * 
     * @param srcBO
     * @param trgtBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean copyForCheckOut(AdminOptionBO srcBO, AdminOptionBO trgtBO,
            Audit audit) throws SevereException {
        this.getAdminOptionAdapterManager()
                .copyForCheckout(srcBO, trgtBO, audit);
        return true;
    }


    /**
     * Method to check whether the admin option is in use
     * 
     * @param adminOptionBO
     * @return boolean
     * @throws SevereException
     */
    public boolean isAdminOptionInUse(AdminOptionBO adminOptionBO)
            throws SevereException {
        return this.getAdminOptionAdapterManager().isAdminOptionInUse(
                adminOptionBO);

    }


    /**
     * Method to check whether the admin option is in use
     * 
     * @param associatedQuestionBO
     * @return boolean
     * @throws SevereException
     */
    public boolean isAdminOptionQuestionInUse(
            AssociatedQuestionBO associatedQuestionBO) throws SevereException {
        return this.getAdminOptionAdapterManager().isAdminOptionQuestionInUse(
                associatedQuestionBO);

    }


    /**
     * Method to return AdminOptionAdapterManager instance
     * 
     * @return AdminOptionAdapterManager
     */
    private AdminOptionAdapterManager getAdminOptionAdapterManager() {
        return new AdminOptionAdapterManager();
    }
 
    
    /**
     * Method to update time stamp
     * 
     * @return boolean
     */
	 public boolean persistTimeStamp(AdminOptionBO adminOptionBO, Audit audit) throws SevereException {
	 	AdminOptionAdapterManager adminOptionAdapterManager = new AdminOptionAdapterManager();
	 	AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
		try {			
			adminOptionBO.setLastUpdatedUser(audit.getUser());
			adminOptionBO.setLastUpdatedTimestamp(audit.getTime());				
			
			adminOptionAdapterManager.updateAdminOption(
					adminOptionBO,audit);
		} catch (AdapterException ex) {
			List errorParams = new ArrayList(3);
			String obj = ex.getClass().getName();
			errorParams.add(obj);
			errorParams.add(obj.getClass().getName());
			throw new SevereException(
					"Exception occured in persistTimeStamp method , for persisting the BusinessObject ProductStructureBO, in ProductStructureBusinessObjectBuilder",
					errorParams, ex);
		} catch (Exception ex) {
			throw new SevereException("Unhandled exception occured", null, ex);
		}

		return true;
	}
	 
	 public List getRemainingQuestions(String quesnrId,boolean isRootQuestion)throws SevereException,AdapterException{
	     return this.getAdminOptionAdapterManager().getRemainingQuestions(quesnrId,isRootQuestion);
	 }
	 
	 public void updateReOrderedQuestions(AssociatedQuestionnaireBO bo, String user)throws SevereException{
	     this.getAdminOptionAdapterManager().updateReOrderedQuestions(bo,user);
	 }
	 

	 
	    /**
	     * Method to locate all the versions of an admin option
	     * 
	     * @param adminOptionBO
	     * @return locateResults
	     * @throws SevereException
	     */
	    public LocateResults retrieveAllVersions(AdminOptionBO adminOptionBO)throws SevereException {
	        LocateResults locateResults = null;
	        
	        locateResults = this.getAdminOptionAdapterManager()
	                    .viewAllAdminOption(adminOptionBO.getAdminName());
	        return locateResults;
	    }


		/** Method to delete a list of questions from the questionnaire of adminoption.
		 * @param associatedQuestionnaireBO
		 * @param user
		 * @throws WPDException
		 */
		public boolean delete(AssociatedQuestionnaireBO associatedQuestionnaireBO,AdminOptionBO adminOptionBO, Audit audit) throws WPDException {
			AdapterServicesAccess adapterServicesAccess = AdapterUtil
			.getAdapterService();
			LocateResults locateResults = null;
			boolean flag = false;
			try{
		        List questionnaireList = associatedQuestionnaireBO.getQuestionnaireList();
		        String questionnaieTildaString = "";
		        if(null != questionnaireList){
			        Iterator itr = questionnaireList.iterator();
			        while(itr.hasNext()){
			            questionnaieTildaString = questionnaieTildaString + itr.next() +"~";
			        }
		        }
		        locateResults = this.getAdminOptionAdapterManager()
		                    .deleteAssociatedQuestionnaire(questionnaieTildaString,associatedQuestionnaireBO.getAdminOptionId(),adapterServicesAccess);
		        if(null != locateResults && null != locateResults.getLocateResults()){
	        		if(locateResults.getTotalResultsCount() > 0)
	        			flag = true;
		        }
			}
			catch(AdapterException e){
				List errorParams = new ArrayList();
				String obj = e.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());
				throw new WPDException(
						"Exception occured in delete(AssociatedQuestionnaireBO) method , " +
						"for deleting a list of questions from the questionnaire of adminoption., in AdminOptionBusinessObjectBuilder",
						errorParams, e);
			}
			return flag;
		}
		
		 /**
	     * Method to locate root questionnaire details.
	     * 
	     * @param locateCriteria
	     * @param user
	     * @return locateResults
	     * @throws SevereException
	     */
	    public LocateResults locate(
	            RootQuestionnaireLocateCriteria locateCriteria, User user)
	            throws SevereException {
	        LocateResults rootQuestionnaireResults = null;
	        List rootQuestionnaireList = null;
	        RootQuestionnaireBO rootQuestionnaireBO = null;
	        AddQuestionAdapterManager addQuestionAdapterManager = null;
	        int questionNumber = 0;
	        LocateResults possibleAnswersResults = null;
	        DomainDetail domainDetailsForQuestionnaire = null;
	        try {
	        	if(null != locateCriteria){
		        	// get the root Questionnaire locateResults
		        	rootQuestionnaireResults = this.getAdminOptionAdapterManager()
					            .locateRootQuestionnaireDetails
								(locateCriteria.getQuestionnaireHierarchySystemId());
					if(null != rootQuestionnaireResults){
						rootQuestionnaireList = rootQuestionnaireResults.getLocateResults();
						if(null != rootQuestionnaireList && 
								!rootQuestionnaireList.isEmpty()){
							// get the root questionnaire bo and set the answers and domains.
							rootQuestionnaireBO = 
								(RootQuestionnaireBO) rootQuestionnaireList.get(0);
					        if(null != rootQuestionnaireBO){
					        	questionNumber = rootQuestionnaireBO.getQuestionNumber();
						        addQuestionAdapterManager = new AddQuestionAdapterManager();
					        	possibleAnswersResults = addQuestionAdapterManager
												.locatePossibleAnswers(questionNumber);
					        	// set the possible answers list
					        	if(null != possibleAnswersResults)
						        	rootQuestionnaireBO.setPossibleAnswerList
											(possibleAnswersResults.getLocateResults());
					        	// set the domain details.
					        	domainDetailsForQuestionnaire = 
					        		DomainUtil.retrieveQuestionnaireDomainDetailInfo
									(rootQuestionnaireBO.getQuestionnaireHierachySystemId());
					        	if(null != domainDetailsForQuestionnaire){
					        		rootQuestionnaireBO.setLob(
					        				domainDetailsForQuestionnaire.getLineOfBusiness());
					        		rootQuestionnaireBO.setBusinessEntity(
					        				domainDetailsForQuestionnaire.getBusinessEntity());
					        		rootQuestionnaireBO.setBusinessGroup(
					        				domainDetailsForQuestionnaire.getBusinessGroup());
					        		rootQuestionnaireBO.setMarketBusinessUnit(domainDetailsForQuestionnaire.getMarketBusinessUnit());
					        		
					        	}
					        }
						}
					}
	        	}
			} catch (AdapterException e) {
				List errorParams = new ArrayList();
				String obj = e.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());
				throw new SevereException(
						"Exception occured in locate(RootQuestionnaireLocateCriteria locateCriteria, User user) method , " +
						"for locating a Root questionnaire of an adminoption., in AdminOptionBusinessObjectBuilder",
						errorParams, e);
			}
	        return rootQuestionnaireResults;
	    }

	    /**
	     * Method to locate child questionnaires.
	     * 
	     * @param locateCriteria
	     * @param user
	     * @return locateResults
	     * @throws SevereException
	     */
	    public LocateResults locate(
	            ChildQuestionnaireLocateCriteria locateCriteria, User user)
	            throws SevereException {
	    	AdapterServicesAccess adapterServicesAccess = AdapterUtil
			.getAdapterService();
	        LocateResults childQuestionnaireResults = null;
	        List childQuestionnaireList = null;
	        ChildQuestionnaireBO childQuestionnaireBO = null;
	        DomainDetail domainDetailsForQuestionnaire = null;
	        String questionnairesToDeleted = null;
	        try {
	        	if(null != locateCriteria){
	        		// get the child Questionnaire locateResults
	        		childQuestionnaireResults = this.getAdminOptionAdapterManager()
					            .locateChildQuestionnaires
								(locateCriteria.getParentQuestionnaireSysId(), 
										locateCriteria.getAction());
	        		if(null != childQuestionnaireResults){
	        			childQuestionnaireList = childQuestionnaireResults.getLocateResults();
						if(null != childQuestionnaireList && 
								!childQuestionnaireList.isEmpty()){
							// get the child questionnaire bo and set the domains.
							for(int i =0; i < childQuestionnaireList.size(); i++){
								childQuestionnaireBO = 
									(ChildQuestionnaireBO) childQuestionnaireList.get(i);
						        if(null != childQuestionnaireBO){
						        	if(locateCriteria.getAction().equals("ON_LOAD")){									
							        	// set the domain details.
							        	domainDetailsForQuestionnaire = 
							        		DomainUtil.retrieveQuestionnaireDomainDetailInfo
											(childQuestionnaireBO.getQuestionnaireHierarchySystemId());
							        	if(null != domainDetailsForQuestionnaire){
							        		childQuestionnaireBO.setLob(
							        				domainDetailsForQuestionnaire.getLineOfBusiness());
							        		childQuestionnaireBO.setBusinessEntity(
							        				domainDetailsForQuestionnaire.getBusinessEntity());
							        		childQuestionnaireBO.setBusinessGroup(
							        				domainDetailsForQuestionnaire.getBusinessGroup());
							        		childQuestionnaireBO.setMarketBusinessUnit(domainDetailsForQuestionnaire.getMarketBusinessUnit());
							        	}
						        	}else if(locateCriteria.getAction().equals("ON_CLOSE")){

						        	}
						        }
							}
						}
	        		}
	        	}
			} catch (AdapterException e) {
				List errorParams = new ArrayList();
				String obj = e.getClass().getName();
				errorParams.add(obj);
				errorParams.add(obj.getClass().getName());
				throw new SevereException(
						"Exception occured in locate(RootQuestionnaireLocateCriteria locateCriteria, User user) method , " +
						"for locating a Root questionnaire of an adminoption., in AdminOptionBusinessObjectBuilder",
						errorParams, e);
			}
	        return childQuestionnaireResults;
	    }
	    
	    /**
	     * Method to persist Child Questionnaires.
	     * @param childQuestionnaireBO
	     * @param adminOptionBO
	     * @param audit
	     * @param insertFlag
	     * @return
	     * @throws SevereException
	     */
	    public boolean persist(ChildQuestionnaireBO childQuestionnaireBO,
	            AdminOptionBO adminOptionBO, Audit audit, boolean insertFlag)
	            throws SevereException {
	    	List childQuestionnaires = null;
	    	ChildQuestionnaireBO childQuestionnaire = null;
	    	SequenceAdapterManager sequenceAdapterManager = null;
	    	AdapterServicesAccess adapterServicesAccess = null;
	    	long transactionId;
	    	DomainDetail domainDetail = null;
	    	AdminOptionAdapterManager adminOptionAdapterManager = null;
	    	
			adapterServicesAccess = AdapterUtil.getAdapterService();
			transactionId = AdapterUtil.getTransactionId();
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil.logBeginTranstn(transactionId, 
					this, "persist(ChildQuestionnaireBO " +
							"childQuestionnaireBO, AdminOptionBO " +
							"adminOptionBO, Audit audit, " +
							"boolean insertFlag)");
			
	    	try{
	        	if(null != childQuestionnaireBO){
	        		childQuestionnaires = childQuestionnaireBO.getChildQuestionnaires();
	        		if(null != childQuestionnaires && !childQuestionnaires.isEmpty()){
	        			for(int i = 0; i < childQuestionnaires.size(); i++){
	        				childQuestionnaire = (ChildQuestionnaireBO) childQuestionnaires.get(i);
	        				if(null != childQuestionnaire){
	        					childQuestionnaire.setLastChangedTimeStamp(audit.getTime());
	        					childQuestionnaire.setLastChangedUser(audit.getUser());
	        					if(insertFlag){
	        						childQuestionnaire.setCreatedTimeStamp(audit.getTime());
	        						childQuestionnaire.setCreatedUser(audit.getUser());
	        						if(null == sequenceAdapterManager){
	        							sequenceAdapterManager = new SequenceAdapterManager();
	        						}
	        						childQuestionnaire.setQuestionnaireHierarchySystemId(
	        								sequenceAdapterManager.getNextQuestionnaireHierarchySequence());
	        					}
			    		        this.getAdminOptionAdapterManager().persistChildQuestionnaires(
			    		            		childQuestionnaire, adapterServicesAccess, 
											audit.getUser(), insertFlag);
			    		        if(!childQuestionnaireBO.isDeleteFlag()){
				    		        // insert the domain information.
				    		        domainDetail = createDomainDetail(childQuestionnaire);
				    		        DomainUtil.persistQuestionnaireAssociatedDomains
														(domainDetail, adapterServicesAccess);
				    		        // if question is changed, delete all the associated child questions.
				    		        if(!insertFlag){
				    		        	if(childQuestionnaire.getDeleteChildFlag() == 'T'){
				    		        		// call the procedure
				    		        		if(null == adminOptionAdapterManager){
				    		        			adminOptionAdapterManager = new AdminOptionAdapterManager();
				    		        		}
				    		        		adminOptionAdapterManager.deleteAssociatedQuestionnaire
												(String.valueOf(childQuestionnaire.getQuestionnaireHierarchySystemId()),
														-1,adapterServicesAccess);
				    		        	}
				    		        }
			    		        }
	        				}
	        			}
	        			if(!childQuestionnaireBO.isDeleteFlag()){
				        	// call the procedure to refresh the domains of the childs.
				        	if(null == adminOptionAdapterManager){
				        		adminOptionAdapterManager = new AdminOptionAdapterManager();
				        	}
				        	adminOptionAdapterManager.updateQuestionnaireDomain(
				        			childQuestionnaireBO.getParentQuestionnaireId(),audit.getUser(),adapterServicesAccess);
	        			}
	        		}
	        		if(childQuestionnaireBO.isDeleteFlag()){
			        	// call the delete procedure.
			        	if(null != childQuestionnaireBO.getChildsToDeleted()){
			        		if(null == adminOptionAdapterManager){
			        			adminOptionAdapterManager = new AdminOptionAdapterManager();
			        		}
			        		adminOptionAdapterManager.deleteAssociatedQuestionnaire
								(childQuestionnaireBO.getChildsToDeleted(),
										adminOptionBO.getAdminOptionId(),adapterServicesAccess);
			        	}
	        		}
	        	}
	        	AdapterUtil.endTransaction(adapterServicesAccess);
	        	AdapterUtil.logTheEndTransaction(transactionId, this, 
	        				"persist(ChildQuestionnaireBO " +
							"childQuestionnaireBO, AdminOptionBO " +
							"adminOptionBO, Audit audit, " +
							"boolean insertFlag)");
	    	}catch(AdapterException ex){
	    		AdapterUtil.abortTransaction(adapterServicesAccess);
	    		AdapterUtil.logAbortTxn(transactionId, this, 
	        				"persist(ChildQuestionnaireBO " +
							"childQuestionnaireBO, AdminOptionBO " +
							"adminOptionBO, Audit audit, " +
							"boolean insertFlag)");
	            List errorParams = new ArrayList(3);
	            String obj = ex.getClass().getName();
	            errorParams.add(obj);
	            errorParams.add(obj.getClass().getName());
	            throw new SevereException(
	                    "Exception occured while persisting Associated Questions for Admin Option in AdminOptionBusinessObjectBuilder",
	                    errorParams, ex);
	        } catch (SevereException e){
	    		AdapterUtil.abortTransaction(adapterServicesAccess);
	    		AdapterUtil.logAbortTxn(transactionId, this, 
	        				"persist(ChildQuestionnaireBO " +
							"childQuestionnaireBO, AdminOptionBO " +
							"adminOptionBO, Audit audit, " +
							"boolean insertFlag)");
	        	throw new SevereException("Unhandled exception is caught",null,e);
	        }catch (Exception e){
	    		AdapterUtil.abortTransaction(adapterServicesAccess);
	    		AdapterUtil.logAbortTxn(transactionId, this, 
	        				"persist(ChildQuestionnaireBO " +
							"childQuestionnaireBO, AdminOptionBO " +
							"adminOptionBO, Audit audit, " +
							"boolean insertFlag)");
	        	throw new SevereException("Unhandled exception is caught",null,e);
	        }
	        
	        return true;
	    }
	    
	    /**
	     * 
	     * @param childQuestionnaireBO
	     * @param adminOptionBO
	     * @param audit
	     * @param insertFlag
	     * @return
	     * @throws SevereException
	     */
	    public boolean persistParentRequired(ChildQuestionnaireBO childQuestionnaireBO,
	            AdminOptionBO adminOptionBO, Audit audit, boolean insertFlag)
	            throws SevereException {
	    	List childQuestionnaires = null;
	    	ChildQuestionnaireBO childQuestionnaire = null;	    	
	    	AdapterServicesAccess adapterServicesAccess = null;
	    	long transactionId;	    	
	    	AdminOptionAdapterManager adminOptionAdapterManager = null;	    	
			adapterServicesAccess = AdapterUtil.getAdapterService();
			transactionId = AdapterUtil.getTransactionId();
			AdapterUtil.beginTransaction(adapterServicesAccess);
			AdapterUtil.logBeginTranstn(transactionId, 
					this, "persistParentRequired(ChildQuestionnaireBO " +
							"childQuestionnaireBO, AdminOptionBO " +
							"adminOptionBO, Audit audit, " +
							"boolean insertFlag)");
			
	    	try{
	        	if(null != childQuestionnaireBO){
	        		childQuestionnaires = childQuestionnaireBO.getChildQuestionnaires();
	        		if(null != childQuestionnaires && !childQuestionnaires.isEmpty()){
	        			for(int i = 0; i < childQuestionnaires.size(); i++){
	        				childQuestionnaire = (ChildQuestionnaireBO) childQuestionnaires.get(i);
	        				if(null != childQuestionnaire){
	        					childQuestionnaire.setLastChangedTimeStamp(audit.getTime());
	        					childQuestionnaire.setLastChangedUser(audit.getUser());
	        					
			    		        this.getAdminOptionAdapterManager().persistChildQuestionnaires(
			    		            		childQuestionnaire, adapterServicesAccess, 
											audit.getUser(), insertFlag);
			    		        
	        				}
	        			}	        			
	        		}   
	        	}
	        	AdapterUtil.endTransaction(adapterServicesAccess);
	        	AdapterUtil.logTheEndTransaction(transactionId, this, 
	        				"persistParentRequired(ChildQuestionnaireBO " +
							"childQuestionnaireBO, AdminOptionBO " +
							"adminOptionBO, Audit audit, " +
							"boolean insertFlag)");
	    	}catch(AdapterException ex){
	    		AdapterUtil.abortTransaction(adapterServicesAccess);
	    		AdapterUtil.logAbortTxn(transactionId, this, 
	        				"persistParentRequired(ChildQuestionnaireBO " +
							"childQuestionnaireBO, AdminOptionBO " +
							"adminOptionBO, Audit audit, " +
							"boolean insertFlag)");
	            List errorParams = new ArrayList(3);
	            String obj = ex.getClass().getName();
	            errorParams.add(obj);
	            errorParams.add(obj.getClass().getName());
	            throw new SevereException(
	                    "Exception occured while persisting Aparent required status for associated Questions for Admin Option in AdminOptionBusinessObjectBuilder",
	                    errorParams, ex);
	        } catch (SevereException e){
	    		AdapterUtil.abortTransaction(adapterServicesAccess);
	    		AdapterUtil.logAbortTxn(transactionId, this, 
	        				"persistParentRequired(ChildQuestionnaireBO " +
							"childQuestionnaireBO, AdminOptionBO " +
							"adminOptionBO, Audit audit, " +
							"boolean insertFlag)");
	        	throw new SevereException("Unhandled exception is caught",null,e);
	        }catch (Exception e){
	    		AdapterUtil.abortTransaction(adapterServicesAccess);
	    		AdapterUtil.logAbortTxn(transactionId, this, 
	        				"persistParentRequired(ChildQuestionnaireBO " +
							"childQuestionnaireBO, AdminOptionBO " +
							"adminOptionBO, Audit audit, " +
							"boolean insertFlag)");
	        	throw new SevereException("Unhandled exception is caught",null,e);
	        }	        
	        return true;
	    }
	    
	    /**
	     * Method to generate the domain detail bo for the questionnaire.
	     * @param childQuestionnaireBO
	     * @return
	     */
	    private DomainDetail createDomainDetail(ChildQuestionnaireBO childQuestionnaireBO){
	    	DomainDetail domainDetail = new DomainDetail();
	    	domainDetail.setEntityParentId(childQuestionnaireBO.
	    						getQuestionnaireHierarchySystemId());
	    	domainDetail.setDomainList(BusinessUtil.convertToDomains(
				    			childQuestionnaireBO.getLob(), 
								childQuestionnaireBO.getBusinessEntity(), 
								childQuestionnaireBO.getBusinessGroup(),
								childQuestionnaireBO.getMarketBusinessUnit()));
	    	domainDetail.setLastUpdatedTimeStamp(
	    			childQuestionnaireBO.getLastChangedTimeStamp());
	    	domainDetail.setLastUpdatedUser(
	    			childQuestionnaireBO.getLastChangedUser());
	    	return domainDetail;
	    }
	    /**
	     * Method to generate the domain detail bo for the questionnaire.
	     * @param rootQuestionnaireBO
	     * @return
	     */
	    private DomainDetail createDomainDetail(RootQuestionnaireBO rootQuestionnaireBO){
	    	DomainDetail domainDetail = new DomainDetail();
	    	domainDetail.setEntityParentId(rootQuestionnaireBO.
	    						getQuestionnaireHierachySystemId());
	    	domainDetail.setDomainList(BusinessUtil.convertToDomains(
	    			rootQuestionnaireBO.getLob(), 
	    			rootQuestionnaireBO.getBusinessEntity(), 
	    			rootQuestionnaireBO.getBusinessGroup(),
					rootQuestionnaireBO.getMarketBusinessUnit()));
	    	domainDetail.setLastUpdatedTimeStamp(
	    			rootQuestionnaireBO.getLastUpdatedTimestamp());
	    	domainDetail.setLastUpdatedUser(
	    			rootQuestionnaireBO.getLastUpdatedUser());
	    	domainDetail.setCreatedTimeStamp(rootQuestionnaireBO.getCreatedTimestamp());
	    	domainDetail.setCreatedUser(rootQuestionnaireBO.getCreatedUser());
	    	return domainDetail;
	    }
	    
	    private void updateQuestionnareSeqlvl(AdminOptionBO adminOptionBO,Audit audit) throws SevereException{
	    	List questionnaireList =null;
	    	 questionnaireList = BusinessUtil.getQuestionareHierarchy(getQuestionnaire(adminOptionBO.getAdminOptionId()));
	    	 int dispCount =1;
	           for(int i=0;i<questionnaireList.size();i++){
	           	AssociatedQuestionnaireBO associatedQuestionnaireBO = (AssociatedQuestionnaireBO)questionnaireList.get(i);
	           	AssociatedQuestionnaireCpyBO associatedQuestionnaireCpyBO =new AssociatedQuestionnaireCpyBO();
	           	associatedQuestionnaireCpyBO.setQuestionnaireId(associatedQuestionnaireBO.getQuestionnaireId());
	           	associatedQuestionnaireCpyBO.setQustnrDsplySeqNum(dispCount);
	           	associatedQuestionnaireCpyBO.setQstnrLvl(associatedQuestionnaireBO.getLevel());
	            this.getAdminOptionAdapterManager().updateSeqLvelQuestions(associatedQuestionnaireCpyBO,audit);
	            dispCount++;
	           }
	    }
}