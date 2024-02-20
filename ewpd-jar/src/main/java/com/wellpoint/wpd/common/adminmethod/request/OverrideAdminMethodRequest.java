/*
 * Created on Sep 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author u13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OverrideAdminMethodRequest extends WPDRequest{

	public void validate()throws ValidationException{
		
	}
	 private  List adminMethodsId;
	 
	 private  List spsId;
	 
	 private String entityType;
	 
	 private int entitySysId;
	 
	 private int benefitCompSysId;
	 
	 private int benefitAdminId;
	 
	 private int contractSysId;
	 
	 private boolean spsChanged;
	 
	 private List changedIds;
	 
	 private String benefitCompName;
	 
	 private int stdBenId;
	 
	 private int productId;
	 
	 private int adminBcompId;
	 
	 private  List TieredAdminMethods ;//CARS:AM2

	 private  List adminMethodListForDB;//CARS:AM2
	 
	 private int benefitSysId;
	 
	 private Map changedAmValTierIdMap=new HashMap();
	 
	 private  List adminMethodsList;
	 
	
	/**
	 * @return Returns the productId.
	 */ //CARS:AM2:START
	public List getAdminMethodListForDB() {
		return adminMethodListForDB;
	}
	/**
	 * @param adminMethodListForDB The adminMethodListForDB to set.
	 */
	public void setAdminMethodListForDB(List adminMethodListForDB) {
		this.adminMethodListForDB = adminMethodListForDB;
	}
	/**
	 * @return Returns the tieredAdminMethods.
	 */
	public List getTieredAdminMethods() {
		return TieredAdminMethods;
	}
	/**
	 * @param tieredAdminMethods The tieredAdminMethods to set.
	 */
	public void setTieredAdminMethods(List tieredAdminMethods) {
		TieredAdminMethods = tieredAdminMethods;
	}
	//CARS:AM2:END
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
     * Returns the stdBenId
     * @return int stdBenId.
     */

    public int getStdBenId() {
        return stdBenId;
    }
    /**
     * Sets the stdBenId
     * @param stdBenId.
     */

    public void setStdBenId(int stdBenId) {
        this.stdBenId = stdBenId;
    }
	/**
	 * @return Returns the benefitAdminId.
	 */
	public int getBenefitAdminId() {
		return benefitAdminId;
	}
	/**
	 * @param benefitAdminId The benefitAdminId to set.
	 */
	public void setBenefitAdminId(int benefitAdminId) {
		this.benefitAdminId = benefitAdminId;
	}
	/**
	 * @return Returns the benefitCompSysId.
	 */
	public int getBenefitCompSysId() {
		return benefitCompSysId;
	}
	/**
	 * @param benefitCompSysId The benefitCompSysId to set.
	 */
	public void setBenefitCompSysId(int benefitCompSysId) {
		this.benefitCompSysId = benefitCompSysId;
	}
	/**
	 * @return Returns the entitySysId.
	 */
	public int getEntitySysId() {
		return entitySysId;
	}
	/**
	 * @param entitySysId The entitySysId to set.
	 */
	public void setEntitySysId(int entitySysId) {
		this.entitySysId = entitySysId;
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
	 * @return Returns the adminMethodsId.
	 */
	public List getAdminMethodsId() {
		return adminMethodsId;
	}
	/**
	 * @param adminMethodsId The adminMethodsId to set.
	 */
	public void setAdminMethodsId(List adminMethodsId) {
		this.adminMethodsId = adminMethodsId;
	}
	/**
	 * @return Returns the spsId.
	 */
	public List getSpsId() {
		return spsId;
	}
	/**
	 * @param spsId The spsId to set.
	 */
	public void setSpsId(List spsId) {
		this.spsId = spsId;
	}
    /**
     * Returns the contractSysId
     * @return int contractSysId.
     */

    public int getContractSysId() {
        return contractSysId;
    }
    /**
     * Sets the contractSysId
     * @param contractSysId.
     */

    public void setContractSysId(int contractSysId) {
        this.contractSysId = contractSysId;
    }
	/**
	 * @return Returns the spsChanged.
	 */
	public boolean isSpsChanged() {
		return spsChanged;
	}
	/**
	 * @param spsChanged The spsChanged to set.
	 */
	public void setSpsChanged(boolean spsChanged) {
		this.spsChanged = spsChanged;
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
	 * @return Returns the adminBcompId.
	 */
	public int getAdminBcompId() {
		return adminBcompId;
	}
	/**
	 * @param adminBcompId The adminBcompId to set.
	 */
	public void setAdminBcompId(int adminBcompId) {
		this.adminBcompId = adminBcompId;
	}
	/**
	 * @return Returns the benefitSysId.
	 */
	public int getBenefitSysId() {
		return benefitSysId;
	}
	/**
	 * @param benefitSysId The benefitSysId to set.
	 */
	public void setBenefitSysId(int benefitSysId) {
		this.benefitSysId = benefitSysId;
	}
	public Map getChangedAmValTierIdMap() {
		return changedAmValTierIdMap;
	}
	public void setChangedAmValTierIdMap(Map changedAmValTierIdMap) {
		this.changedAmValTierIdMap = changedAmValTierIdMap;
	}
	public List getAdminMethodsList() {
		return adminMethodsList;
	}
	public void setAdminMethodsList(List adminMethodsList) {
		this.adminMethodsList = adminMethodsList;
	}
	
	
}
