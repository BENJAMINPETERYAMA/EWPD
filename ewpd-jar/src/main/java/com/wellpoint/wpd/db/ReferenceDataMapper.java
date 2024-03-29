/*
 * ReferenceDataMapper.java
 * 
 * � 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.db;

import com.wellpoint.wpd.common.framework.refdata.domain.ReferenceDataImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ReferenceDataMapper.java 2458 2006-08-21 19:33:16Z U10347 $
 */
public class ReferenceDataMapper extends MappingSqlQuery {

    /**
     * 
     */
    public ReferenceDataMapper() {
        super();
    }

    /**
     * @param arg0
     * @param arg1
     */
    public ReferenceDataMapper(DataSource dataSource, String sql) {
        super(dataSource, sql);
        declareParameter(new SqlParameter(Types.INTEGER));

    }

    /**
     * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet, int)
     * @param arg0
     * @param arg1
     * @return
     * @throws java.sql.SQLException
     */
    protected Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReferenceDataImpl rdi = new ReferenceDataImpl();
        rdi.setId(rs.getInt(1));
        rdi.setCode(rs.getString(2));
        rdi.setName(rs.getString(3));
        rdi.setDescription(rs.getString(4));
        return rdi;
    }

}
