/*
 * Created on Mar 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.refdata.service;

import com.wellpoint.wpd.business.framework.bo.ObjectFactory;
import com.wellpoint.wpd.business.framework.bo.ReferenceDataFactory;
import com.wellpoint.wpd.business.framework.service.WPDService;
import com.wellpoint.wpd.common.framework.exception.ServiceException;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceData;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceDataSet;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.refdata.request.RefDataRequest;
import com.wellpoint.wpd.common.refdata.response.RefDataResponse;
import com.wellpoint.wpd.db.ReferenceDataDao;


/**
 * @author u13154
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RefDataBusinessService extends WPDService{

    ReferenceDataDao referenceDataDao;

    ReferenceData referenceData;

	/**
	 * 
	 */
	public RefDataBusinessService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.business.framework.service.WPDService#execute(com.wellpoint.wpd.common.framework.request.WPDRequest)
	 */
	public WPDResponse execute(WPDRequest request) throws ServiceException {
		
        RefDataResponse refDataResponse = new RefDataResponse();
        RefDataRequest refDataRequest = (RefDataRequest) request;
        ReferenceDataFactory referenceDataFactory = (ReferenceDataFactory) ObjectFactory.getFactory("referenceData");
        int CDCI_CD_ITM_ID = refDataRequest.getPopupId();
        ReferenceDataSet referenceDataSet = null;
        referenceDataSet = referenceDataFactory.getReferenceData(CDCI_CD_ITM_ID);
        refDataResponse.setList(referenceDataSet.getReferenceData());
		return refDataResponse;
	}

}
