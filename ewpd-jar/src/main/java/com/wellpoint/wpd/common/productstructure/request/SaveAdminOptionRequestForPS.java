/*
 * Created on Dec 2, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.productstructure.request;

import java.util.List;

import com.wellpoint.wpd.common.productstructure.vo.ProductStructureVO;

/**
 * @author U16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveAdminOptionRequestForPS extends ProductStructureRequest{
	
	private List adminList;

	private int benefitComponentId;
	private int adminLvlAsscId;
	private String entityType;
	private List adminOveriddenList;
	private int benefitAdminId;
	private ProductStructureVO productStructureVO;
	private boolean isPSorProductorBenefit;
	private boolean isChanged = false;
	private List changedIds;
	private String bCompName;
	private int benefitSysId;
	
	
	
	

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
	/**
	 * @return Returns the adminList.
	 */
	public List getAdminList() {
		return adminList;
	}
	/**
	 * @param adminList The adminList to set.
	 */
	public void setAdminList(List adminList) {
		this.adminList = adminList;
	}
	/**
	 * @return Returns the adminLvlAsscId.
	 */
	public int getAdminLvlAsscId() {
		return adminLvlAsscId;
	}
	/**
	 * @param adminLvlAsscId The adminLvlAsscId to set.
	 */
	public void setAdminLvlAsscId(int adminLvlAsscId) {
		this.adminLvlAsscId = adminLvlAsscId;
	}
	/**
	 * @return Returns the adminOveriddenList.
	 */
	public List getAdminOveriddenList() {
		return adminOveriddenList;
	}
	/**
	 * @param adminOveriddenList The adminOveriddenList to set.
	 */
	public void setAdminOveriddenList(List adminOveriddenList) {
		this.adminOveriddenList = adminOveriddenList;
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
	 * @return Returns the productStructureVO.
	 */
	public ProductStructureVO getProductStructureVO() {
		return productStructureVO;
	}
	/**
	 * @param productStructureVO The productStructureVO to set.
	 */
	public void setProductStructureVO(ProductStructureVO productStructureVO) {
		this.productStructureVO = productStructureVO;
	}
    /**
     * @return isPSorProductorBenefit
     * 
     * Returns the isPSorProductorBenefit.
     */
    public boolean getPSorProductorBenefit() {
        return isPSorProductorBenefit;
    }
    /**
     * @param isPSorProductorBenefit
     * 
     * Sets the isPSorProductorBenefit.
     */
    public void setPSorProductorBenefit(boolean isPSorProductorBenefit) {
        this.isPSorProductorBenefit = isPSorProductorBenefit;
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
	 * @return Returns the isChanged.
	 */
	public boolean isChanged() {
		return isChanged;
	}
	/**
	 * @param isChanged The isChanged to set.
	 */
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	/**
	 * @return Returns the bCompName.
	 */
	public String getBCompName() {
		return bCompName;
	}
	/**
	 * @param compName The bCompName to set.
	 */
	public void setBCompName(String compName) {
		bCompName = compName;
	}
}
