/*
 * LookupBusinessObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.lookup.builder;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.lookup.adapter.LookupAdapterManager;
import com.wellpoint.wpd.business.lookup.locateCriteria.LookupAdminQuestionLocateCriteria;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.security.domain.User;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class LookupBusinessObjectBuilder {
	
	public LookupBusinessObjectBuilder(){
		
	}
	
	public LocateResults locate(LookupAdminQuestionLocateCriteria lookupAdminQuestionLocateCriteria, User user) throws SevereException {
			LocateResults questionResults = null;
			LookupAdapterManager lookupAdapterManager= new LookupAdapterManager();
			try{
				questionResults = lookupAdapterManager.locateAdminQuestion(lookupAdminQuestionLocateCriteria,user);
			}catch(SevereException ex){
				List errorParams = new ArrayList();
		 	    String obj = ex.getClass().getName();
		 	    errorParams.add(obj);
		 	    errorParams.add(obj.getClass().getName());
		 	    throw new SevereException("Exception occured in locate() method in LookupBusinessObjectBuilder", null, ex);	
			}catch(AdapterException ex){
				List errorParams = new ArrayList();
		 	    String obj = ex.getClass().getName();
		 	    errorParams.add(obj);
		 	    errorParams.add(obj.getClass().getName());
		 	    throw new SevereException("Exception occured in locate() method in LookupBusinessObjectBuilder", null, ex);	
			}catch(Exception ex){
				List errorParams = new ArrayList();
		 	    String obj = ex.getClass().getName();
		 	    errorParams.add(obj);
		 	    errorParams.add(obj.getClass().getName());
		 	    throw new SevereException("Exception occured in locate() method in LookupBusinessObjectBuilder", null, ex);	
			}
		   	return questionResults;
	}
}
