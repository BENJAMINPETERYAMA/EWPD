/*
 * BenefitComponentGeneralInformationBO.java
 * 
 * ©2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint
 * Inc. ("Confidential Information"). You shall not disclose or use Confidential
 * Information without the express written agreement of Wellpoint Inc.
 */
package com.wellpoint.wpd.common.benefitcomponent.bo;

import java.util.Date;
import java.util.List;

/**
 * BO for BenefitComponentGeneralInformation
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitComponentGeneralInformationBO {

    //Refers to fields in table BMST_BNFT_MSTR
    private int benefitSysId;

    private String benefitIdentifier;

    private String description;

    private List lobList = null;

    private List businessEntityList = null;

    private List businessGroupList = null;

    private List termList = null;

    private List qualifierList = null;

    private List PVAList = null;

    private List dataTypeList = null;

    private String createdUser;

    private Date createdTimestamp;

    private String lastUpdatedUser;

    private Date lastUpdatedTimestamp;


    /**
     * @return Returns the benefitIdentifier.
     */
    public String getBenefitIdentifier() {
        return benefitIdentifier;
    }


    /**
     * @param benefitIdentifier
     *            The benefitIdentifier to set.
     */
    public void setBenefitIdentifier(String benefitIdentifier) {
        this.benefitIdentifier = benefitIdentifier;
    }


    /**
     * @return Returns the businessEntityList.
     */
    public List getBusinessEntityList() {
        return businessEntityList;
    }


    /**
     * @param businessEntityList
     *            The businessEntityList to set.
     */
    public void setBusinessEntityList(List businessEntityList) {
        this.businessEntityList = businessEntityList;
    }


    /**
     * @return Returns the businessGroupList.
     */
    public List getBusinessGroupList() {
        return businessGroupList;
    }


    /**
     * @param businessGroupList
     *            The businessGroupList to set.
     */
    public void setBusinessGroupList(List businessGroupList) {
        this.businessGroupList = businessGroupList;
    }


    /**
     * @return Returns the createdTimestamp.
     */
    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }


    /**
     * @param createdTimestamp
     *            The createdTimestamp to set.
     */
    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }


    /**
     * @return Returns the createdUser.
     */
    public String getCreatedUser() {
        return createdUser;
    }


    /**
     * @param createdUser
     *            The createdUser to set.
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }


    /**
     * @return Returns the dataTypeList.
     */
    public List getDataTypeList() {
        return dataTypeList;
    }


    /**
     * @param dataTypeList
     *            The dataTypeList to set.
     */
    public void setDataTypeList(List dataTypeList) {
        this.dataTypeList = dataTypeList;
    }


    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * @param description
     *            The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return Returns the lastUpdatedTimestamp.
     */
    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }


    /**
     * @param lastUpdatedTimestamp
     *            The lastUpdatedTimestamp to set.
     */
    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }


    /**
     * @return Returns the lastUpdatedUser.
     */
    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }


    /**
     * @param lastUpdatedUser
     *            The lastUpdatedUser to set.
     */
    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }


    /**
     * @return Returns the lobList.
     */
    public List getLobList() {
        return lobList;
    }


    /**
     * @param lobList
     *            The lobList to set.
     */
    public void setLobList(List lobList) {
        this.lobList = lobList;
    }


    /**
     * @return Returns the pVAList.
     */
    public List getPVAList() {
        return PVAList;
    }


    /**
     * @param list
     *            The pVAList to set.
     */
    public void setPVAList(List list) {
        PVAList = list;
    }


    /**
     * @return Returns the qualifierList.
     */
    public List getQualifierList() {
        return qualifierList;
    }


    /**
     * @param qualifierList
     *            The qualifierList to set.
     */
    public void setQualifierList(List qualifierList) {
        this.qualifierList = qualifierList;
    }


    /**
     * @return Returns the termList.
     */
    public List getTermList() {
        return termList;
    }


    /**
     * @param termList
     *            The termList to set.
     */
    public void setTermList(List termList) {
        this.termList = termList;
    }


    /**
     * @return Returns the benefitSysId.
     */
    public int getBenefitSysId() {
        return benefitSysId;
    }


    /**
     * @param benefitSysId
     *            The benefitSysId to set.
     */
    public void setBenefitSysId(int benefitSysId) {
        this.benefitSysId = benefitSysId;
    }
}