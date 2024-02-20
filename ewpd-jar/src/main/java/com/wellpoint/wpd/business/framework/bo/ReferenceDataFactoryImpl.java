/*
 * ReferenceDataFactoryImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.business.framework.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceData;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceDataSet;
import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceDataSetImpl;
import com.wellpoint.wpd.db.ReferenceDataDao;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ReferenceDataFactoryImpl.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public class ReferenceDataFactoryImpl extends ObjectFactory implements
        ReferenceDataFactory {

    private ReferenceDataDao referenceDataDao;
    /**
     * 
     */
    protected ReferenceDataFactoryImpl() {
        super();
    }
    
    /**
     * @param referenceDataDao The referenceDataDao to set.
     */
    public void setReferenceDataDao(ReferenceDataDao referenceDataDao) {
        this.referenceDataDao = referenceDataDao;
    }
    
    public ReferenceDataSet getReferenceData(int type){
        ReferenceDataSetImpl rdsi = referenceDataDao.getReferenceDataSet(type);
        if(rdsi != null){
           rdsi.setReferenceData(referenceDataDao.getReferenceData(type));
        }
        return rdsi;
    }

    public List getReferenceData(int type, List codeList) {

    	List referenceDataList = new ArrayList();
    	ReferenceData refData = null;
    	String code = null;

    	ReferenceDataSet referenceDataSet = getReferenceData(type);
    	
    	for (Iterator codeIter = codeList.iterator(); codeIter.hasNext();) {
			code = (String) codeIter.next();
			
	    	for (Iterator iter = referenceDataSet.getReferenceData().iterator(); iter.hasNext();) {
				 refData = (ReferenceData) iter.next();
				 if(code.equals(refData.getCode())) {
				 		referenceDataList.add(refData);
				 		break;
				 }
			}
		}
    	return referenceDataList;
    }
    
    public ReferenceData getReferenceData(int type, String code) {
    	List list = new ArrayList();
    	list.add(code);
    	return (ReferenceData)getReferenceData(type,list).get(0);
    }
}
