/*
 * Created on May 3, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author u15409
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AdminBO extends BusinessObject {

    private String adminId;

    private int adminParentId;

    private String adminName;

    private String adminText;

    private String adminType;

    private String adminTypeDesc;

    private boolean action;

    private String adminAction = "admin";


    /*
     * (non-Javadoc)
     * 
     * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
     */
    public boolean equals(BusinessObject object) {
        // TODO Auto-generated method stub
        return false;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }


    /**
     * @return Returns the action.
     */
    public boolean isAction() {
        return action;
    }


    /**
     * @param action
     *            The action to set.
     */
    public void setAction(boolean action) {
        this.action = action;
    }


    /**
     * @return Returns the adminAction.
     */
    public String getAdminAction() {
        return adminAction;
    }


    /**
     * @param adminAction
     *            The adminAction to set.
     */
    public void setAdminAction(String adminAction) {
        this.adminAction = adminAction;
    }


    /**
     * @return Returns the adminId.
     */
    public String getAdminId() {
        return adminId;
    }


    /**
     * @param adminId
     *            The adminId to set.
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }


    /**
     * @return Returns the adminName.
     */
    public String getAdminName() {
        return adminName;
    }


    /**
     * @param adminName
     *            The adminName to set.
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }


    /**
     * @return Returns the adminText.
     */
    public String getAdminText() {
        return adminText;
    }


    /**
     * @param adminText
     *            The adminText to set.
     */
    public void setAdminText(String adminText) {
        this.adminText = adminText;
    }


    /**
     * @return Returns the adminType.
     */
    public String getAdminType() {
        return adminType;
    }


    /**
     * @param adminType
     *            The adminType to set.
     */
    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }


    /**
     * @return Returns the adminTypeDesc.
     */
    public String getAdminTypeDesc() {
        return adminTypeDesc;
    }


    /**
     * @param adminTypeDesc
     *            The adminTypeDesc to set.
     */
    public void setAdminTypeDesc(String adminTypeDesc) {
        this.adminTypeDesc = adminTypeDesc;
    }


    /**
     * @return Returns the adminParentId.
     */
    public int getAdminParentId() {
        return adminParentId;
    }


    /**
     * @param adminParentId The adminParentId to set.
     */
    public void setAdminParentId(int adminParentId) {
        this.adminParentId = adminParentId;
    }
}