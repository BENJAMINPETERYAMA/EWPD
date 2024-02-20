/*
 * ModuleImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.framework.security.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: ModuleImpl.java 16454 2007-03-30 20:52:06Z U10567 $
 */
public class ModuleImpl implements Module {
    private long id;

    private String name;

    private Map tasks;

    /**
     *  
     */
    public ModuleImpl() {
        super();
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The description to set.
     */
    public void setName(String name) {
        this.name = name;
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
     * @return Returns the tasks.
     */
    public Map getTasks() {
        return tasks;
    }

    /**
     * @param tasks The tasks to set.
     */
    public void setTasks(Map tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        if (task == null)
            return;
        if (tasks == null) {
            tasks = new HashMap();
        }
        tasks.put(task.getName().toUpperCase(), task);
    }
    /**
     * 
     * @see com.wellpoint.wpd.common.framework.security.domain.Module#isAuthorized(java.lang.String)
     * @param task
     * @return
     */
    public boolean isAuthorized(String task) {
        return tasks == null ? false : tasks.containsKey(task.toUpperCase());
    }
    /**
     * 
     * @see com.wellpoint.wpd.common.framework.security.domain.Module#isAuthorized(java.lang.String, java.lang.String)
     * @param task
     * @param subtask
     * @return
     */
    public boolean isAuthorized(String task, String subtask){
        if(task == null || task.trim().length() == 0){
            throw new IllegalArgumentException("isAuthorized in Module.  task is null or empty. Value=" + task);
        }
        Task tsk = (Task)tasks.get(task.toUpperCase());
        return tsk == null ? false : tsk.isAuthorized(subtask);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("id=").append(id).append(",");
        if (name != null) {
            sb.append("name=").append(name).append(",");
        }
        if (tasks != null) {
            sb.append("tasks=").append(tasks).append(",");
        }
        return sb.toString();
    }
}