/*
 * MandateBusinessObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.mandate.builder;

import com.wellpoint.wpd.business.mandate.adapter.MandateAdapterManager;
import com.wellpoint.wpd.business.mandate.locateCriteria.MandateLocateCriteria;
import com.wellpoint.wpd.common.bo.Audit;
import com.wellpoint.wpd.common.bo.BusinessObject;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.security.domain.User;
import com.wellpoint.wpd.common.mandate.bo.MandateBO;


/**
 * 
 * BusinessObjectBuilder class for Mandate.
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class MandateBusinessObjectBuilder {

    MandateAdapterManager mandateAdapterManager;


    /**
     * Constructor
     *  
     */
    public MandateBusinessObjectBuilder() {
        mandateAdapterManager = new MandateAdapterManager();
    }


    /**
     * Method to persist Mandate details.
     * 
     * @param mandateBO MandateBO
     * @param audit Audit 
     * @param insertFlag boolean
     * @return boolean
     * @throws WPDException
     */
    public boolean persist(MandateBO mandateBO, Audit audit, boolean insertFlag)
            throws SevereException  {
        if (insertFlag) {
            // insert logic
            this.getMandateAdapterManager().persistMandate(mandateBO, audit);
        } else if (!insertFlag) {
            // update logic
            this.getMandateAdapterManager().updateMandate(mandateBO, audit);
        }
        return true;
    }


    /**
     * Method to retrieve the Mandate details.
     * 
     * @param mandateBO MandateBO
     * @return mandateBO MandateBO
     * @throws WPDException
     * @throws SevereException
     */
    public MandateBO retrieve(MandateBO mandateBO) throws WPDException,SevereException  {
        return (MandateBO) this.getMandateAdapterManager().retrieve(mandateBO);
    }


    /**
     * Method to retrieve Latest Version
     * 
     * @param mandateBO MandateBO
     * @return BusinessObject
     * @throws SevereException
     */
    public BusinessObject retrieveLatestVersion(MandateBO mandateBO)
            throws SevereException  {
        return this.getMandateAdapterManager()
                .retrieveLatestVersionByMandateName(mandateBO);
    }
    
    /**
     * Method to retrieve Latest Version
     * 
     * @param mandateBO MandateBO
     * @return int latest version number
     * @throws WPDException
     */
    public int retrieveLatestVersionNumber(MandateBO mandateBO)
            throws SevereException  {
    	return this.getMandateAdapterManager()
        .retrieveLatestVersionNumber(mandateBO);
    }


    /**
     * Method to retrieve the Mandate details using mandateName.
     * 
     * @param mandateBO MandateBO
     * @param user User
     * @return mandateBO MandateBO
     */
    public MandateBO retrieveMandateName(MandateBO mandateBO, User user)
            throws SevereException  {
        return this.getMandateAdapterManager().retrieveByMandateName(mandateBO);
    }


    /**
     * Method to locate the Mandate
     * 
     * @param mandateLocateCriteria MandateLocateCriteria
     * @param user User
     * @return locateResults LocateResults
     * @throws WPDException
     * @throws SevereException
     */
    public LocateResults locate(MandateLocateCriteria mandateLocateCriteria,
            User user) throws WPDException, SevereException  {
        LocateResults locateResults = null;
        if("copy".equals(mandateLocateCriteria.getAction())){
        	locateResults = mandateAdapterManager.locateForCopy(
                    mandateLocateCriteria, user);
        }
        else if(("viewAll").equals(mandateLocateCriteria.getAction())){
	        locateResults = mandateAdapterManager.viewAllMandate(
                mandateLocateCriteria, user);	
        }
        else{
        	locateResults = mandateAdapterManager.locateMandate(
                    mandateLocateCriteria, user);
        }
        
        return locateResults;
    }
    


    /**
     * Method to delete Mandate.
     * 
     * @param mandateBO MandateBO
     * @param audit Audit
     * @return boolean
     * @throws SevereException
     */
    public boolean deleteMandate(MandateBO mandateBO, Audit audit)
            throws SevereException {
        return mandateAdapterManager.deleteMandate(mandateBO, audit);
    }

    /**
     * Method to delete latest version
     * 
     * @param mandateBO MandateBO
     * @param audit Audit
     * @return boolean
     * @throws SevereException
     */
    public boolean deleteLatestVersion(MandateBO mandateBO, Audit audit)
            throws SevereException  {
        return this.getMandateAdapterManager().deleteMandate(mandateBO, audit);
    }

    /**
     * Method to check the mandate is in use
     * 
     * @param mandateBO MandateBO
     * @param user User
     * @return boolean
     * @throws WPDException
     */
    public boolean isMandateInUse(MandateBO mandateBO, User user)
            throws SevereException  {
        return this.getMandateAdapterManager().isMandateInUse(mandateBO, user);
    }

    
    /**
     * Method to return MandateAdapterManager instance
     * 
     * @return MandateAdapterManager
     */
    public MandateAdapterManager getMandateAdapterManager() {
        return new MandateAdapterManager();
    }
    
	/**
	 * This is a dummy method which returns true.
	 * 
	 * @param mandateBO1 MandateBO
	 * @param mandateBO MandateBO
	 * @param audit Audit
	 * @return boolean
	 */
	public boolean copy(MandateBO mandateBO1, MandateBO mandateBO, Audit audit){
		//Do not have anything to copy
		return true;
	}
	
	/**
	 * Method to delete mandate.
	 * @param mandateBO MandateBO
	 * @param audit Audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean delete(MandateBO mandateBO, Audit audit) throws SevereException {
		 return this.getMandateAdapterManager().deleteMandate(mandateBO, audit);
	}
	
	/**
	 * This method copies the mandate for the checkout action.
	 * 
	 * @param mandateBO1 MandateBO
	 * @param mandateBO MandateBO
	 * @param audit Audit
	 * @return boolean
	 * @throws SevereException
	 */
	public boolean copyForCheckOut(MandateBO mandateBO1, MandateBO mandateBO, Audit audit)throws SevereException {

		return copy(mandateBO1, mandateBO, audit);
	}
}