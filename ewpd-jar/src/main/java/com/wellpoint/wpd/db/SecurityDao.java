/*
 * SecurityDao.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.db;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.wellpoint.wpd.common.framework.security.domain.ModuleImpl;
import com.wellpoint.wpd.common.framework.security.domain.RoleImpl;
import com.wellpoint.wpd.common.framework.security.domain.TaskImpl;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: SecurityDao.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class SecurityDao extends JdbcDaoSupport {

    private String sql;
    /**
     * 
     */
    public SecurityDao() {
        super();
    }
    /**
     * 
     * @see org.springframework.dao.support.DaoSupport#initDao()
     *
     */
    public void initDao(){
        //securityDataMapper = new SecurityDataMapper(getDataSource(),sql);
    }
    /**
     * @param sql The sql to set.
     */
    public void setSql(String sql) {
        this.sql = sql;
    }
    
    public RoleImpl getRole(String roleName){
        if(roleName == null || roleName.trim().length()==0){
            return null;
        }
        JdbcTemplate jt = new JdbcTemplate(getDataSource());
        List result = jt.queryForList(sql, new Object[]{roleName.trim()});
        if(result == null || result.size() == 0){
            return null;
        }
        return createRole(result);
    }
    
    protected RoleImpl createRole(List results){
        RoleImpl ri = null;
        Iterator resultIter = results.iterator();
        Map modules = null;
        Map tasks = null;
        while(resultIter.hasNext()){
            Map row = (Map)resultIter.next();
            if(ri == null){
                ri = new RoleImpl();
	            ri.setId(((BigDecimal)row.get("ACSR_ACCSR_ID")).longValue());
	            ri.setName((String)row.get("ACSR_ROLE_NM"));
	            ri.setDescription((String)row.get("ACSR_ROLE_DESC_TXT"));
            }
            Long moduleId = new Long(((BigDecimal)row.get("MODULEID")).longValue());
            if(modules == null){
                modules = new HashMap();
            }
            ModuleImpl mi = (ModuleImpl)modules.get(moduleId);
            if(mi == null){
                mi = new ModuleImpl();
                mi.setId(moduleId.longValue());
                mi.setName((String)row.get("MODULENAME"));
                modules.put(moduleId,mi);
            }
            TaskImpl ti = new TaskImpl();
            ti.setId(((BigDecimal)row.get("TASKID")).longValue());
            ti.setName((String)row.get("TASKNAME"));
            mi.addTask(ti);
        }
        ri.setModules(modules);
        return ri;
    }
}
