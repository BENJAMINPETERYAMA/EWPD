/*
 * TaskImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.ets.bx.mapping.application.security;

import java.util.Map;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TaskImpl implements Task {
    private long id;
    private String name;
    private Map subtasks;

    /**
     *  
     */
    public TaskImpl() {
        super();
    }

    /**
     * @return Returns the id.
     */
    public long getId() {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Returns the subtasks.
     */
    public Map getSubtasks() {
        return subtasks;
    }
    /**
     * @param subtasks The subtasks to set.
     */
    public void setSubtasks(Map subtasks) {
        this.subtasks = subtasks;
    }
   
    /**
     * 
     * @see com.wellpoint.wpd.common.framework.security.domain.Task#isAuthorized(java.lang.String)
     * @param subtask
     * @return
     */
    public boolean isAuthorized(String subtask){
        return subtasks == null ? false : subtasks.containsKey(subtask.toUpperCase());
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("id=").append(id).append(",");
        if (name != null) {
            sb.append("name=").append(name);
        }
        return sb.toString();
    }
}