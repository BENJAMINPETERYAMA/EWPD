package com.wellpoint.wpd.common.security.bo;

import java.util.Date;
import java.util.List;

public class RoleConfigSrdaBO {

    
    private int roleModuleId;
    
    private int roleId;
    
    private int moduleId;
    
    private String moduleName;
    
    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;
    
    private List moduleIdLIst;

    /**
     * @return Returns the createdUser.
     * @return String createdUser
     */
    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * Sets the createdUser
     * @param createdUser
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
 
    /**
     * @return Returns the lastUpdatedUser.
     * @return String lastUpdatedUser
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * Sets the lastUpdatedUser
     * @param lastUpdatedUser
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
    /**
     * @return Returns the moduleId.
     * @return int moduleId
     */
    public int getModuleId() {
        return moduleId;
    }
    /**
     * Sets the moduleId
     * @param moduleId
     */
    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }
    /**
     * @return Returns the roleId.
     * @return int roleId
     */
    public int getRoleId() {
        return roleId;
    }
    /**
     * Sets the roleId
     * @param roleId
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    /**
     * @return Returns the roleModuleId.
     * @return int roleModuleId
     */
    public int getRoleModuleId() {
        return roleModuleId;
    }
    /**
     * Sets the roleModuleId
     * @param roleModuleId
     */
    public void setRoleModuleId(int roleModuleId) {
        this.roleModuleId = roleModuleId;
    }
    /**
     * @return Returns the moduleName.
     * @return String moduleName
     */
    public String getModuleName() {
        return moduleName;
    }
    /**
     * Sets the moduleName
     * @param moduleName
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    /**
     * @return Returns the moduleIdLIst.
     * @return List moduleIdLIst
     */
    public List getModuleIdLIst() {
        return moduleIdLIst;
    }
    /**
     * Sets the moduleIdLIst
     * @param moduleIdLIst
     */
    public void setModuleIdLIst(List moduleIdLIst) {
        this.moduleIdLIst = moduleIdLIst;
    }
    /**
     * Sets the createdTimestamp
     * @param createdTimestamp
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * Sets the lastUpdatedTimestamp
     * @param lastUpdatedTimestamp
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
    /**
     * @return Returns the createdTimestamp.
     * @return Date createdTimestamp
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * @return Returns the lastUpdatedTimestamp.
     * @return Date lastUpdatedTimestamp
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }


}
