/*
 * ReferenceDataDao.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.db;

import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceDataSetImpl;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ReferenceDataDao.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public class ReferenceDataDao extends JdbcDaoSupport {

    private String referenceDataSetSql;
    private String referenceDataSql;
    private ReferenceDataSetMapper referenceDataSetMapper;
    private ReferenceDataMapper referenceDataMapper;
    /**
     * 
     */
    public ReferenceDataDao() {
        super();
    }
    
    public void initDao(){
        referenceDataSetMapper = new ReferenceDataSetMapper(getDataSource(),referenceDataSetSql);
        referenceDataMapper = new ReferenceDataMapper(getDataSource(),referenceDataSql);
    }
    
    /**
     * @param referenceDataSetSql The referenceDataSetSql to set.
     */
    public void setReferenceDataSetSql(String referenceDataSetSql) {
        this.referenceDataSetSql = referenceDataSetSql;
    }
    
    /**
     * @param referenceDataSql The referenceDataSql to set.
     */
    public void setReferenceDataSql(String referenceDataSql) {
        this.referenceDataSql = referenceDataSql;
    }
    
    /**
     * Returns a ReferenceDataSetImpl object for the specified id.  If the query returns more than one row
     * the first record will be returned.  
     * @param id  
     * @return ReferenceDataSetImpl for the specified id or null if no records are found. 
     */
    public ReferenceDataSetImpl getReferenceDataSet(int id){
        List result = referenceDataSetMapper.execute(new Object[]{new Integer(id)});
        if(result == null || result.size() == 0){
            return null;
        }
        return (ReferenceDataSetImpl)result.get(0);
    }
    /**
     * Returns a list of ReferenceData objects for the specified id.
     * @param id
     * @return List of ReferenceData objects for the specified id.  
     */
    public List getReferenceData(int id){
        return referenceDataMapper.execute(new Object[]{new Integer(id)});
    }
}
