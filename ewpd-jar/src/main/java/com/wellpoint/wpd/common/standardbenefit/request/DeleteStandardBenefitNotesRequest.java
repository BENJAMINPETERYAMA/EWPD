/*
 * DeleteStandardBenefitNotesRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteStandardBenefitNotesRequest extends WPDRequest {
	
	 private String standardBenefitNoteId;
	    private int entityId;
	    private int version;
	    private String status;
	    private List businessDomains;
	    private String standardBenefitName;
	    private String entityType;
	    //added 28thNov
	    private String benefitDefinitionKey;
	    private List notedIds;

    /**
     * @return standardBenefitName
     * 
     * Returns the standardBenefitName.
     */
    public String getStandardBenefitName() {
        return standardBenefitName;
    }
    /**
     * @param standardBenefitName
     * 
     * Sets the standardBenefitName.
     */
    public void setStandardBenefitName(String standardBenefitName) {
        this.standardBenefitName = standardBenefitName;
    }
    /**
     * @return standardBenefitNoteId
     * 
     * Returns the standardBenefitNoteId.
     */
    public String getStandardBenefitNoteId() {
        return standardBenefitNoteId;
    }
    /**
     * @param standardBenefitNoteId
     * 
     * Sets the standardBenefitNoteId.
     */
    public void setStandardBenefitNoteId(String standardBenefitNoteId) {
        this.standardBenefitNoteId = standardBenefitNoteId;
    }
   
    
    /**
     * @return businessDomains
     * 
     * Returns the businessDomains.
     */
    public List getBusinessDomains() {
        return businessDomains;
    }
    /**
     * @param businessDomains
     * 
     * Sets the businessDomains.
     */
    public void setBusinessDomains(List businessDomains) {
        this.businessDomains = businessDomains;
    }
    /**
     * @return status
     * 
     * Returns the status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status
     * 
     * Sets the status.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return version
     * 
     * Returns the version.
     */
    public int getVersion() {
        return version;
    }
    /**
     * @param version
     * 
     * Sets the version.
     */
    public void setVersion(int version) {
        this.version = version;
    }
    
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
   /* public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }
*/
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
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub

    }
        /**
         * Returns the entityType
         * @return String entityType.
         */

        public String getEntityType() {
            return entityType;
        }
        /**
         * Sets the entityType
         * @param entityType.
         */

        public void setEntityType(String entityType) {
            this.entityType = entityType;
        }
		/**
		 * @return Returns the benefitDefinitionKey.
		 */
		public String getBenefitDefinitionKey() {
			return benefitDefinitionKey;
		}
		/**
		 * @param benefitDefinitionKey The benefitDefinitionKey to set.
		 */
		public void setBenefitDefinitionKey(String benefitDefinitionKey) {
			this.benefitDefinitionKey = benefitDefinitionKey;
		}
		/**
		 * @return Returns the notedIds.
		 */
		public List getNotedIds() {
			return notedIds;
		}
		/**
		 * @param notedIds The notedIds to set.
		 */
		public void setNotedIds(List notedIds) {
			this.notedIds = notedIds;
		}
  }


