/*
 * RoleImpl.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.ets.bx.mapping.application.security;

import java.util.HashMap;
import java.util.Map;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RoleImpl implements Role {

    private long id;

    private String name;

    private String description;

    private Map modules;

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return Returns the modules.
     */
    public Map getModules() {
        return modules;
    }

    /**
     * @param modules The modules to set.
     */
    public void setModules(Map modules) {
        this.modules = modules;
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
     *  
     */
    public RoleImpl() {
        super();
    }

    public void addModule(Module module) {
        if (module == null)
            return;
        if (modules == null) {
            modules = new HashMap();
        }
        modules.put(module.getName().toUpperCase(), module);
    }

    protected boolean isAuthorized(String module) {
        if (modules == null)
            return false;
        return modules.containsKey(module.toUpperCase());
    }

    protected boolean isAuthorized(String module, String task) {
        if(task == null){
            return isAuthorized(module);
        }
        if (modules == null)
            return false;
        ModuleImpl mod = (ModuleImpl) modules.get(module.toUpperCase());
        return module == null ? false :( mod==null?false:mod.isAuthorized(task));
    }
    
    public boolean isAuthorized(String module, String task, String subtask){
        if(module == null || module.trim().length() == 0){
            throw new IllegalArgumentException("isAuthorized method in Role:  module is null or empty. Value=" + module);
        }
        if(subtask == null){
            return isAuthorized(module,task);
        }
        if(isAuthorized(module,task)){
            ModuleImpl mod = (ModuleImpl) modules.get(module);
            return mod==null?false:mod.isAuthorized(task,subtask);
        }
        return false;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("id=").append(id).append(",");
        if (name != null) {
            sb.append("name=").append(name).append(",");
        }
        if (description != null) {
            sb.append("description=").append(description).append(",");
        }
        if (modules != null) {
            sb.append("modules=").append(modules).append(",");
        }
        return sb.toString();
    }
}