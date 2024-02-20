/*
 * Created on Mar 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.bo;

import java.util.Date;


/**
 * @author u13274
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UniverseBO {
    
    private int standardBenefitKey;
    private String universeType;	//denotes the type(whether term, qualifier, pro arrngmts or datatype)
    private String universeCode;
    
    private String createdUser;
    private Date createdTimestamp;
    private String lastUpdatedUser;
    private Date lastUpdatedTimestamp;
    
    private int catalogId;
    private String secondaryCode;
    private String codeDescText;
    private String catDescText;
    
    public UniverseBO() {
        
    }
    /**
     * Returns the createdTimestamp
     * @return Date createdTimestamp.
     */

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }
    /**
     * Sets the createdTimestamp
     * @param createdTimestamp.
     */

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }
    /**
     * Returns the createdUser
     * @return String createdUser.
     */

    public String getCreatedUser() {
        return createdUser;
    }
    /**
     * Sets the createdUser
     * @param createdUser.
     */

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
    /**
     * Returns the lastUpdatedTimestamp
     * @return Date lastUpdatedTimestamp.
     */

    public Date getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }
    /**
     * Sets the lastUpdatedTimestamp
     * @param lastUpdatedTimestamp.
     */

    public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }
    /**
     * Returns the lastUpdatedUser
     * @return String lastUpdatedUser.
     */

    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }
    /**
     * Sets the lastUpdatedUser
     * @param lastUpdatedUser.
     */

    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }
    /**
     * Returns the standardBenefitKey
     * @return int standardBenefitKey.
     */

    public int getStandardBenefitKey() {
        return standardBenefitKey;
    }
    /**
     * Sets the standardBenefitKey
     * @param standardBenefitKey.
     */

    public void setStandardBenefitKey(int standardBenefitKey) {
        this.standardBenefitKey = standardBenefitKey;
    }
    /**
     * Returns the universeCode
     * @return String universeCode.
     */

    public String getUniverseCode() {
        return universeCode;
    }
    /**
     * Sets the universeCode
     * @param universeCode.
     */

    public void setUniverseCode(String universeCode) {
        this.universeCode = universeCode;
    }
    /**
     * Returns the universeType
     * @return String universeType.
     */

    public String getUniverseType() {
        return universeType;
    }
    /**
     * Sets the universeType
     * @param universeType.
     */

    public void setUniverseType(String universeType) {
        this.universeType = universeType;
    }

    
    /**
     * Returns the catalogId
     * @return int catalogId.
     */
    public int getCatalogId() {
        return catalogId;
    }
    /**
     * Sets the catalogId
     * @param catalogId.
     */
    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }
    /**
     * Returns the codeDescText
     * @return String codeDescText.
     */
    public String getCodeDescText() {
        return codeDescText;
    }
    /**
     * Sets the codeDescText
     * @param codeDescText.
     */
    public void setCodeDescText(String codeDescText) {
        this.codeDescText = codeDescText;
    }
    /**
     * Returns the secondaryCode
     * @return String secondaryCode.
     */
    public String getSecondaryCode() {
        return secondaryCode;
    }
    /**
     * Sets the secondaryCode
     * @param secondaryCode.
     */
    public void setSecondaryCode(String secondaryCode) {
        this.secondaryCode = secondaryCode;
    }
    
    /**
     * Returns the catDescText
     * @return String catDescText.
     */

    public String getCatDescText() {
        return catDescText;
    }
    /**
     * Sets the catDescText
     * @param catDescText.
     */

    public void setCatDescText(String catDescText) {
        this.catDescText = catDescText;
    }
    
   
}
