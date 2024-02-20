/*
 * Created on Mar 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.datatype.builder;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.datatype.adapter.DataTypeAdapterManager;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.framework.exception.SevereException;
import com.wellpoint.wpd.common.framework.security.domain.User;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DataTypeObjectBuilder {

	DataTypeAdapterManager dataTypeAdapterManager;
    public DataTypeObjectBuilder() {
    	dataTypeAdapterManager =  new DataTypeAdapterManager();
    }
   
   public LocateResults search(User user) throws SevereException {
   		LocateResults dataTypeResults = null;
   		try{
   			dataTypeResults = dataTypeAdapterManager.searchDataType(user);
   		}catch(SevereException ex){
   			List errorParams = new ArrayList();
     	    String obj = ex.getClass().getName();
     	    errorParams.add(obj);
     	    errorParams.add(obj.getClass().getName());
     	    throw new SevereException("Exception occured in search() method in DataTypeObjectBuilder", null, ex);
   		}catch(AdapterException ex){
   			List errorParams = new ArrayList();
     	    String obj = ex.getClass().getName();
     	    errorParams.add(obj);
     	    errorParams.add(obj.getClass().getName());
     	    throw new SevereException("Exception occured in search() method in DataTypeObjectBuilder", null, ex);
   		}catch (Exception ex){
   	      List errorParams = new ArrayList();
   	      String obj = ex.getClass().getName();
   	      errorParams.add(obj);
   	      errorParams.add(obj.getClass().getName());
   	      throw new SevereException("Exception occured in search() method in DataTypeObjectBuilder", null, ex);
   	    }
       	return dataTypeResults;
   }

}
