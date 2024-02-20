/*
 * QuestionBusinessObjectBuilder.java
 *  © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.business.question.builder;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.business.common.adapter.AdapterUtil;
import com.wellpoint.wpd.business.question.adapter.QuestionAdapterManager;
import com.wellpoint.wpd.business.question.locateCriteria.QuestionLocateCriteria;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.question.bo.QuestionBO;

/**
 * Builder class for Questions.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionBusinessObjectBuilder {

    /**
     * Constructor
     */
    public QuestionBusinessObjectBuilder() {

    }


    /**
     * Method to insert/update question
     * 
     * @param questionBO
     * @param audit
     * @param insertFlag
     * @return boolean
     * @throws SevereException
     */
    public boolean persist(QuestionBO questionBO, Audit audit,
            boolean insertFlag) throws SevereException {
    	
    	AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    	long transactionId = AdapterUtil.getTransactionId();
    	try{
    	 AdapterUtil.beginTransaction(adapterServicesAccess);
    	 AdapterUtil.logBeginTranstn(transactionId,this,"persist(QuestionBO questionBO, Audit audit,boolean insertFlag)");
	        if (insertFlag) {
	            // insert logic
	            this.getAdapterManager().persistQuestion(questionBO, audit, adapterServicesAccess);
	        } else if (!insertFlag) {
	            // update logic
	            this.getAdapterManager().updateQuestion(questionBO, audit ,adapterServicesAccess);
	        }
        AdapterUtil.endTransaction(adapterServicesAccess);
        AdapterUtil.logTheEndTransaction(transactionId,this,"persist(QuestionBO questionBO, Audit audit,boolean insertFlag)");
    	}catch(SevereException ex){
 	    	AdapterUtil.abortTransaction(adapterServicesAccess);
 	    	AdapterUtil.logAbortTxn(transactionId,this,"persist(QuestionBO questionBO, Audit audit,boolean insertFlag)");
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in persist method for persisting the QuestionBO in persist",
                    errorParams, ex);	
 	    } catch(AdapterException ex){
 	    	AdapterUtil.abortTransaction(adapterServicesAccess);
 	    	AdapterUtil.logAbortTxn(transactionId,this,"persist(QuestionBO questionBO, Audit audit,boolean insertFlag)");
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in persist method for persisting the QuestionBO in persist",
                    errorParams, ex);	
 	    }catch (Exception e){
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId,this,"persist(QuestionBO questionBO, Audit audit,boolean insertFlag)");
            throw new SevereException("Unhandled exception is caught",null,e);

        }
        return true;
    }


    /**
     * Method to locate question
     * 
     * @param questionLocateCriteria
     * @param user
     * @return LocateResults
     * @throws SevereException
     */
    public LocateResults locate(QuestionLocateCriteria questionLocateCriteria,
            User user) throws SevereException {
        LocateResults questionResults = null;
        if ("viewAll".equals(questionLocateCriteria.getAction())) {
            questionResults = this.getAdapterManager().viewAllVersions(
                    questionLocateCriteria);
        } else if ("view".equals(questionLocateCriteria.getAction())) {
            questionResults = this.getAdapterManager().view(
                    questionLocateCriteria);
            questionResults.setLatestVersion(true);
        } else {
            questionResults = this.getAdapterManager().searchQuestion(
                    questionLocateCriteria);
        }
        return questionResults;
    }


    /**
     * Method to retrieve question
     * 
     * @param questionBO
     * @return QuestionBO
     * @throws SevereException
     */
    public QuestionBO retrieve(QuestionBO questionBO) throws SevereException {
        return this.getAdapterManager().retrieve(questionBO);
    }


    /**
     * Method to delete question
     * 
     * @param questionBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean delete(QuestionBO questionBO, Audit audit)
            throws SevereException {
    	
    	AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    	long transactionId = AdapterUtil.getTransactionId();
    	try{
    	//Start of Managed Transaction.	
    	
    	AdapterUtil.beginTransaction(adapterServicesAccess);
    	AdapterUtil.logBeginTranstn(transactionId,this,"delete(QuestionBO questionBO, Audit audit)");
    	
        this.getAdapterManager().deleteQuestion(questionBO, audit,adapterServicesAccess);
        
        //End of Managed transaction.
        AdapterUtil.endTransaction(adapterServicesAccess);
        AdapterUtil.logTheEndTransaction(transactionId,this,"delete(QuestionBO questionBO, Audit audit)");
        
    	}catch(SevereException ex){
 	    	AdapterUtil.abortTransaction(adapterServicesAccess);
 	    	AdapterUtil.logAbortTxn(transactionId,this,"delete(QuestionBO questionBO, Audit audit)");
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in persist method for persisting the NotesAttachmentOverrideBO in QuestionBusinessObjectBuilder",
                    errorParams, ex);	
 	    } catch(AdapterException ex){
 	    	AdapterUtil.abortTransaction(adapterServicesAccess);
 	    	AdapterUtil.logAbortTxn(transactionId,this,"delete(QuestionBO questionBO, Audit audit)");
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in persist method for persisting the QuestionBO in QuestionBusinessObjectBuilder",
                    errorParams, ex);	
 	    }catch (Exception e){
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId,this,"delete(QuestionBO questionBO, Audit audit)");
            throw new SevereException("Unhandled exception is caught",null,e);

        }
    	return true;
    }


    /**
     * Method to delete latest version
     * 
     * @param questionBO
     * @param audit
     * @return boolean
     * @throws SevereException
     */
    public boolean deleteLatestVersion(QuestionBO questionBO, Audit audit)
            throws SevereException {
    	AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
    	long transactionId = AdapterUtil.getTransactionId();
    	try{
   		//Start of Managed Transaction.		
    	
    	AdapterUtil.beginTransaction(adapterServicesAccess);
    	AdapterUtil.logBeginTranstn(transactionId,this,"deleteLatestVersion(QuestionBO questionBO, Audit audit)");
    	
		this.getAdapterManager().deleteQuestion(questionBO, audit, adapterServicesAccess );
		
		//End of Managed transaction.
		AdapterUtil.endTransaction(adapterServicesAccess);
        AdapterUtil.logTheEndTransaction(transactionId,this,"deleteLatestVersion(QuestionBO questionBO, Audit audit)");
        
    	}catch(SevereException ex){
 	    	AdapterUtil.abortTransaction(adapterServicesAccess);
 	    	AdapterUtil.logAbortTxn(transactionId,this,"deleteLatestVersion(QuestionBO questionBO, Audit audit)");
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in persist method for persisting the QuestionBO in NotesAttachmentBusinessObjectBuilder",
                    errorParams, ex);	
 	    } catch(AdapterException ex){
 	    	AdapterUtil.abortTransaction(adapterServicesAccess);
 	    	AdapterUtil.logAbortTxn(transactionId,this,"deleteLatestVersion(QuestionBO questionBO, Audit audit)");
            List errorParams = new ArrayList(3);
            String obj = ex.getClass().getName();
            errorParams.add(obj);
            errorParams.add(obj.getClass().getName());
            throw new SevereException(
                    "Exception occured in persist method for persisting the NotesAttachmentOverrideBO in NotesAttachmentBusinessObjectBuilder",
                    errorParams, ex);	
 	    }catch (Exception e){
            AdapterUtil.abortTransaction(adapterServicesAccess);
            AdapterUtil.logAbortTxn(transactionId,this,"deleteLatestVersion(QuestionBO questionBO, Audit audit)");
            throw new SevereException("Unhandled exception is caught",null,e);

        }
		
		 return true;
    }


    /**
     * Method to retrieve the question details by question description
     * 
     * @param questionBO
     * @return BusinessObject
     * @throws SevereException
     */
    public BusinessObject retrieveByQuestionDesc(QuestionBO questionBO)
            throws SevereException {
        return this.getAdapterManager().retrieveByQuestionDesc(questionBO);
    }


    /**
     * Method to retrieve Latest Version
     * 
     * @param questionBO
     * @return BusinessObject
     * @throws SevereException
     */
    public BusinessObject retrieveLatestVersion(QuestionBO questionBO)
            throws SevereException {
        return this.getAdapterManager().retrieveLatestVersion(questionBO);
    }


    /**
     * Method to retrieve Latest Version Number
     * 
     * @param questionBO
     * @return int
     * @throws SevereException
     */

    public int retrieveLatestVersionNumber(QuestionBO questionBO)
            throws SevereException {
        return this.getAdapterManager().retrieveLatestVersionNumber(questionBO);
    }


    /**
     * Method to copy Question Information
     * 
     * @param srcBO
     * @param trgtBO
     * @param audit
     * @return boolean
     *  
     */
    public boolean copy(QuestionBO srcBO, QuestionBO trgtBO, Audit audit)
            throws SevereException {
        return this.getAdapterManager().copy(srcBO, trgtBO, audit);
    }


    /**
     * Method to copy Question Information during checkout process
     * 
     * @param srcBO
     * @param trgtBO
     * @param audit
     * @return boolean
     *  
     */
    public boolean copyForCheckOut(QuestionBO srcBO, QuestionBO trgtBO,
            Audit audit) throws SevereException {
        return this.getAdapterManager().copy(srcBO, trgtBO, audit);
    }


    /**
     * Method to check whether the question is in use
     * 
     * @param questionBO
     * @return boolean
     *  
     */
    public boolean isQuestionInUse(QuestionBO questionBO)
            throws SevereException {
        return this.getAdapterManager().isQuestionInUse(questionBO);
    }


    /**
     * Method to check whether the question is valid
     * 
     * @param questionBO
     * @return boolean
     *  
     */
    public boolean isValidQuestion(QuestionBO questionBO)
            throws SevereException {
        return this.getAdapterManager().isValidQuestion(questionBO);
    }


    /**
     * Method to return Adapter Manager
     * 
     * @return QuestionAdapterManager
     *  
     */
    public QuestionAdapterManager getAdapterManager() {
        return new QuestionAdapterManager();
    }

    /**
     * Method to update time stamp
     * 
     * @return boolean
     */
	 public boolean persistTimeStamp(QuestionBO questionBO, Audit audit) throws SevereException {
	 	QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();
	 	AdapterServicesAccess adapterServicesAccess = AdapterUtil.getAdapterService();
		try {
			
			questionBO.setLastUpdatedUser(audit.getUser());
			questionBO.setLastUpdatedTimestamp(audit.getTime());				
			
			questionAdapterManager.updateQuestion(
					questionBO,audit,adapterServicesAccess);
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
}