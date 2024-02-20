/*
 * QuestionAdapterManager.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.benefit.adapter;

import com.wellpoint.adapter.access.AdapterAccessFactory;
import com.wellpoint.adapter.access.AdapterServicesAccess;
import com.wellpoint.adapter.access.BusinessTransactionContext;
import com.wellpoint.adapter.access.BusinessTransactionContextImpl;
import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.util.BusinessConstants;
import com.wellpoint.wpd.common.benefit.bo.Question;
import com.wellpoint.wpd.common.bo.Audit;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionAdapterManager {

    /**
     * Default Constructor
     *  
     */
    public QuestionAdapterManager() {
    }


    /**
     * Inserts question to DB
     * 
     * @param question
     * @param audit
     * @return boolean
     * @throws AdapterException
     */
    public boolean persistQuestion(Question question, Audit audit) throws AdapterException {

      
            BusinessTransactionContext btc = new BusinessTransactionContextImpl();
            btc
                    .setBusinessTransactionType(BusinessTransactionContext.BUSINESS_TRANSACTION_CONTEXT_CREATE);
            btc.setBusinessTransactionUser(BusinessConstants.TESTUSER);
            AdapterServicesAccess adapterServicesAccess = AdapterAccessFactory
                    .getAdapterServicesAccess();
            adapterServicesAccess.persistObject(question,
                    "com.wellpoint.wpd.common.benefit.bo.Question", btc);
       
        return true;
    }
}