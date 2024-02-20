/*
 * QuestionBusinessObjectBuilder.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.benefit.builder;

import com.wellpoint.adapter.exception.AdapterException;
import com.wellpoint.wpd.business.benefit.adapter.QuestionAdapterManager;
import com.wellpoint.wpd.common.benefit.bo.Question;
import com.wellpoint.wpd.common.bo.Audit;

/**
 * Builder class for QuestionBO
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class QuestionBusinessObjectBuilder {
    QuestionAdapterManager questionAdapterManager = new QuestionAdapterManager();


    /**
     * Default Constructor
     *  
     */
    public QuestionBusinessObjectBuilder() {
    }


    public boolean persist(Question question, Audit audit, boolean insertFlag) throws AdapterException {
        if (insertFlag) {
            questionAdapterManager.persistQuestion(question, audit);
        }
        return true;
    }
}