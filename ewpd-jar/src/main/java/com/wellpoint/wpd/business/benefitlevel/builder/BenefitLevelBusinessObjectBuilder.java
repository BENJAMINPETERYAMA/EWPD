/*
 * Created on Mar 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.benefitlevel.builder;

import com.wellpoint.wpd.business.benefitlevel.adapter.BenefitlevelAdapterManager;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLevelLocateCriteria;
import com.wellpoint.wpd.business.benefitlevel.locatecriteria.BenefitLocateCriteria;
import com.wellpoint.wpd.common.benefitlevel.bo.BenefitWrapperBO;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.exception.WPDException;
import com.wellpoint.wpd.common.framework.security.domain.User;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitLevelBusinessObjectBuilder {
	
	BenefitlevelAdapterManager benefitlevelAdapterManager;
	
    /**
     * Constructor
    */
    public BenefitLevelBusinessObjectBuilder() {
    	benefitlevelAdapterManager =  new BenefitlevelAdapterManager();
    }
	
	/**
	 *  Method to insert/update question
	 * 
	 * @param benefitWrapperBO
	 * @param user
	 * @param insertFlag
	 * @return
	 * @throws WPDException
	 */
	 public boolean persist(BenefitWrapperBO benefitWrapperBO, User user, boolean insertFlag) throws WPDException{
	      // if(insertFlag) {
	           // insert logic
	       //	benefitlevelAdapterManager.persistBenefit(benefitWrapperBO,user);
	      /* }
	       else  if(!insertFlag) {
	           // update logic
	       	benefitlevelAdapterManager.updateQuestion(questionBO,audit);
	       }*/
	       return true;
	 }
	 
	 /**
	     * Method to retrieve Latest Version
	     * 
	     * @param questionBO
	     * @return BusinessObject
	     * @throws WPDException
	     */
//	    public BusinessObject retrieveLatestVersion(BenefitLevelBO benefitLevelBO) throws WPDException {
//	        return benefitlevelAdapterManager.retrieveLatestVersion(benefitLevelBO);
//	    }

	/**
	 * @param benefitLocateCriteria
	 * @param user
	 * @param b
	 * @throws SevereException
	 */
	public LocateResults searchBenefitLevels(BenefitLocateCriteria benefitLocateCriteria, User user, boolean b) throws SevereException {
		 return benefitlevelAdapterManager.locateBenefitLevels(benefitLocateCriteria);
		
	}

	/**
	 * @param benefitLocateCriteria
	 * @param user
	 * @param b
	 * @return
	 * @throws SevereException
	 */
	public LocateResults searchBenefitLines(BenefitLevelLocateCriteria benefitLocateCriteria, User user, boolean b) throws SevereException {
		return benefitlevelAdapterManager.locateBenefitLines(benefitLocateCriteria);
	}

	

}
