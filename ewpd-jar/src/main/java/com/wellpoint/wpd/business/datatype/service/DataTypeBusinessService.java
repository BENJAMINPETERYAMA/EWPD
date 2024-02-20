/*
 * Created on Mar 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.datatype.service;

import com.wellpoint.wpd.business.datatype.builder.DataTypeObjectBuilder;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.common.bo.LocateResults;
import com.wellpoint.wpd.common.datatype.request.DataTypeRequest;
import com.wellpoint.wpd.common.datatype.response.DataTypeResponse;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.logging.Logger;
import com.wellpoint.wpd.common.framework.messages.InformationalMessage;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;

import java.util.ArrayList;
import java.util.List;


/**
 * @author u12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DataTypeBusinessService extends WPDService{

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 */
	public WPDResponse execute(WPDRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public WPDResponse execute(DataTypeRequest dataTypeRequest)throws ServiceException {
        DataTypeResponse dataTypeResponse = new DataTypeResponse();
        try {
        	List dataTypeSearchResultsList = null;
        	List errorList = new ArrayList();
        	LocateResults searchResults = null;
            DataTypeObjectBuilder dataTypeObjectBuilder = new DataTypeObjectBuilder();
            searchResults = dataTypeObjectBuilder.search(dataTypeRequest.getUser()); 
            int searchResultCount = searchResults.getTotalResultsCount();
            dataTypeSearchResultsList = searchResults.getLocateResults();
 	        if (dataTypeSearchResultsList.size() > 0) {
 	        	dataTypeResponse.setDataTypesList(dataTypeSearchResultsList);

 	        } else if (dataTypeSearchResultsList.size() == 0 && searchResultCount == 0) {
 	            errorList.add(new InformationalMessage(
 	                    "mandate.question.searchresult.notfound"));
 	           dataTypeResponse.setMessages(errorList);
 	        }

        } catch (Exception ex) {
        	Logger.logError(ex);
            List logParameters = new ArrayList();
            logParameters.add(dataTypeRequest);
            String logMessage = "Failed while processing CreateBenefitRequest";
            throw new ServiceException(logMessage, logParameters, ex);
        }

        return dataTypeResponse;
    }
}
