/*
 * ReferenceDataFactory.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import java.util.List;

import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceData;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceDataSet;

/**
 * Implementor of this interface will be responsible to provide reference data. 
 * The constants defined should match CDCI_CD_ITM_ID in RCMS_UDCV_USRDFND_CDVAL
 * and RCMS_CDIR_CD_ITM_RGSTN.
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ReferenceDataFactory.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public interface ReferenceDataFactory {
    int STATE_CODE = 1626;
    int LEGAL_ENTITY = 1715;
    int SALES_PLAN = 1908;
    ReferenceDataSet getReferenceData(int type);
    public List getReferenceData(int type, List codeList);
    public ReferenceData getReferenceData(int type, String code);

    
}
