/*
 * Created on Feb 26, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * 
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodSynchronousValidationRequest extends WPDRequest {
	
	public static final int SAVE_ADMIN_OPTIONS_IN_PRODUCT = 1;
	public static final int DELETE_ADMIN_OPTIONS_IN_PRODUCT = 2;
	public static final int PRDCT_ADMIN_OPTION_QSTNS_SAVE = 3;
	//public static final int BENEFIT_ASSOCIATED_BNFT_LINES = 4;
	public static final int ADMIN_OPTION_SAVE_IN_BENEFIT_ADMIN = 5;
	//public static final int QSTN_OVERRIDE_IN_BENEFIT = 6;
	public static final int GENERAL_BENEFIT_IN_PRODUCT = 7;
	public static final int BC_LIST_INPRODUCT = 20;
	public static final int GENERAL_BENEFIT_ADMIN_OPTION_CHANGE_PRODUCT = 8;
	public static final int BENEFITS_CHANGE_IN_ENTITY = 21;
	
	public static final int GENERAL_BENEFIT_IN_PRODUCT_STRUCTURE = 9;
	//public static final int BENEFITS_IN_BC_PROD_STRUCTURE = 10;
	public static final int BENEFITLEVELS_IN_ENTITY = 11;
	//public static final int QSTN_OVERRIDE_IN_BENEFIT_PS = 12;
	public static final int BNFT_ADMIN_QUESTION = 13;
	
	public static final int ADMN_OPTIONS_SAVE_IN_CONTRACT_ADMNSTRTION = 14;
	public static final int ADMN_OPTIONS_DELETE_IN_CONTRACT_ADMNSTRTION = 15;
	public static final int CONTRACT_PRDCT_ADMIN = 16;
	public static final int BENEFITS_IN_BC_IN_CONTRACT = 17;
	public static final int QSTN_OVRRIDE_BNFT_ADMIN_CONTRCT = 18;
	public static final int BC_LIST_CHANGE_IN_CNTRCT = 19;
	
	public static final int BENEFITLEVELS_IN_CONTRACT =22 ;
	public static final int BENEFIT_VALIDATION =23 ;
	
//	Nagesh
	public static final int DELETE_ADMIN_OPTIONS_TIER_IN_PRODUCT_VALIDATION = 24;
	public static final int DELETE_ADMIN_OPTIONS_TIER_IN_CONTRACT_VALIDATION = 25;
	//Nagesh

	public static final String TYPE_PRODUCT = "PRODUCT";
	public static final String TYPE_PRODUCT_STRUCTURE = "PRODUCT STRUCTURE";
	public static final String TYPE_CONTRACT = "CONTRACT";
	
	private String entityType;
	
	private int entityId;
	
	private int adminSysId;
	
	private int benefitComponentId;
	
	private int benefitAdministrationId;
	
	private int benefitId;
	
	private List changedIds;
	
	//CARS start
	private List changedTiers;
	
	private List changedTierSysIds;
	//CARS end
	
	private int level;
	
	private String benefitCompName;
	
	private List affectedBenCompList;
	
	private int productId;
	
	private int contractId;
	
	//private String tierSysLineIds;
	
	

	/**
	 * @return Returns the benefitComponentId.
	 */
	public int getBenefitComponentId() {
		return benefitComponentId;
	}
	/**
	 * @param benefitComponentId The benefitComponentId to set.
	 */
	public void setBenefitComponentId(int benefitComponentId) {
		this.benefitComponentId = benefitComponentId;
	}
	/**
	 * @return Returns the benefitId.
	 */
	public int getBenefitId() {
		return benefitId;
	}
	/**
	 * @param benefitId The benefitId to set.
	 */
	public void setBenefitId(int benefitId) {
		this.benefitId = benefitId;
	}
	/**
	 * @return Returns the changedIds.
	 */
	public List getChangedIds() {
		return changedIds;
	}
	/**
	 * @param changedIds The changedIds to set.
	 */
	public void setChangedIds(List changedIds) {
		this.changedIds = changedIds;
	}
	/**
	 * @return Returns the entityId.
	 */
	public int getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId The entityId to set.
	 */
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	/**
	 * @return Returns the entityType.
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType The entityType to set.
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	/**
	 * @return Returns the level.
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level The level to set.
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}	
	/**
	 * @return Returns the adminSysId.
	 */
	public int getAdminSysId() {
		return adminSysId;
	}
	/**
	 * @param adminSysId The adminSysId to set.
	 */
	public void setAdminSysId(int adminSysId) {
		this.adminSysId = adminSysId;
	}
	/**
	 * @return Returns the benefitAdministrationId.
	 */
	public int getBenefitAdministrationId() {
		return benefitAdministrationId;
	}
	/**
	 * @param benefitAdministrationId The benefitAdministrationId to set.
	 */
	public void setBenefitAdministrationId(int benefitAdministrationId) {
		this.benefitAdministrationId = benefitAdministrationId;
	}
	/**
	 * @return Returns the benefitCompName.
	 */
	public String getBenefitCompName() {
		return benefitCompName;
	}
	/**
	 * @param benefitCompName The benefitCompName to set.
	 */
	public void setBenefitCompName(String benefitCompName) {
		this.benefitCompName = benefitCompName;
	}
    /**
     * Returns the affectedBenCompList
     * @return List affectedBenCompList.
     */

    public List getAffectedBenCompList() {
        return affectedBenCompList;
    }
    /**
     * Sets the affectedBenCompList
     * @param affectedBenCompList.
     */

    public void setAffectedBenCompList(List affectedBenCompList) {
        this.affectedBenCompList = affectedBenCompList;
    }
	/**
	 * @return Returns the productId.
	 */
	public int getProductId() {
		return productId;
	}
	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
    /**
     * Returns the contractId
     * @return int contractId.
     */

    public int getContractId() {
        return contractId;
    }
    /**
     * Sets the contractId
     * @param contractId.
     */

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }
    //CARS start
    /**
     * 
     * @return
     */
	public List getChangedTiers() {
		return changedTiers;
	}
	
	/**
	 * 
	 * @param changedTiers
	 */
	public void setChangedTiers(List changedTiers) {
		this.changedTiers = changedTiers;
	}
	
	/**
	 * 
	 * @return
	 */
	public List getChangedTierSysIds() {
		return changedTierSysIds;
	}
	
	/**
	 * 
	 * @param changedTierSysIds
	 */
	public void setChangedTierSysIds(List changedTierSysIds) {
		this.changedTierSysIds = changedTierSysIds;
	}
	//CARS end
	
	
	
}
