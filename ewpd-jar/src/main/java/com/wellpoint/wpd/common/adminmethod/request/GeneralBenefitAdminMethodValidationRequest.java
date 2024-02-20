/*
 * Created on Sep 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.request;

import java.util.List;

import com.wellpoint.wpd.business.adminmethod.locatecriteria.AdminMethodLocateCriteria;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class GeneralBenefitAdminMethodValidationRequest extends WPDRequest{

    public AdminMethodLocateCriteria adminMethodLocateCriteria;
    
    private int entityId;
    
    private String entityType;
    
    private int dateSegmentId;
    
    private int contractId;
    
    private String benefitName;
    
    
    private String benefitComponentName;
    
    
    
    
    /**
     * @return benefitComponentName
     * 
     * Returns the benefitComponentName.
     */
    public String getBenefitComponentName() {
        return benefitComponentName;
    }
    /**
     * @param benefitComponentName
     * 
     * Sets the benefitComponentName.
     */
    public void setBenefitComponentName(String benefitComponentName) {
        this.benefitComponentName = benefitComponentName;
    }
    /**
     * @return benefitName
     * 
     * Returns the benefitName.
     */
    public String getBenefitName() {
        return benefitName;
    }
    /**
     * @param benefitName
     * 
     * Sets the benefitName.
     */
    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }
    /**
     * @return contractId
     * 
     * Returns the contractId.
     */
    public int getContractId() {
        return contractId;
    }
    /**
     * @param contractId
     * 
     * Sets the contractId.
     */
    public void setContractId(int contractId) {
        this.contractId = contractId;
    }
    /**
     * @return dateSegmentId
     * 
     * Returns the dateSegmentId.
     */
    public int getDateSegmentId() {
        return dateSegmentId;
    }
    /**
     * @param dateSegmentId
     * 
     * Sets the dateSegmentId.
     */
    public void setDateSegmentId(int dateSegmentId) {
        this.dateSegmentId = dateSegmentId;
    }
    /**
     * @return entityType
     * 
     * Returns the entityType.
     */
    public String getEntityType() {
        return entityType;
    }
    /**
     * @param entityType
     * 
     * Sets the entityType.
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    List adminMethods;
    /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }
    
    
    /**
     * @return entityId
     * 
     * Returns the entityId.
     */
    public int getEntityId() {
        return entityId;
    }
    /**
     * @param entityId
     * 
     * Sets the entityId.
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
    /**
     * Returns the adminMethodLocateCriteria
     * @return AdminMethodLocateCriteria adminMethodLocateCriteria.
     */

    public AdminMethodLocateCriteria getAdminMethodLocateCriteria() {
        return adminMethodLocateCriteria;
    }
    /**
     * Sets the adminMethodLocateCriteria
     * @param adminMethodLocateCriteria.
     */

    public void setAdminMethodLocateCriteria(
            AdminMethodLocateCriteria adminMethodLocateCriteria) {
        this.adminMethodLocateCriteria = adminMethodLocateCriteria;
    }
    /**
     * Returns the adminMethods
     * @return List adminMethods.
     */

    public List getAdminMethods() {
        return adminMethods;
    }
    /**
     * Sets the adminMethods
     * @param adminMethods.
     */

    public void setAdminMethods(List adminMethods) {
        this.adminMethods = adminMethods;
    }
}
