/*
 * Created on Nov 26, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminoption.request;

import java.util.List;

import com.wellpoint.wpd.common.adminoption.vo.AdminOptionHideVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.product.request.ProductRequest;

/**
 * @author u16223
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveAdminOptionRequest extends ProductRequest{
	
	private List adminList;
	private int productSysId;
	private int benefitComponentId;
	private int adminLvlAsscId;
	private String productType;
	private List adminOveriddenList;
	private int benefitAdminId;
	private boolean adminOptionsChanged;
	private List changedAdminOptions;
	private String benefitCompName;
	private int benefitSysId;
	
	
	private AdminOptionHideVO hideVO;
	
	private boolean isPSorProductorBenefit;
	
	
	
	/**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {

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
	 * @return Returns the productSysId.
	 */
	public int getProductSysId() {
		return productSysId;
	}
	/**
	 * @param productSysId The productSysId to set.
	 */
	public void setProductSysId(int productSysId) {
		this.productSysId = productSysId;
	}
	/**
	 * @return Returns the productType.
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * @param productType The productType to set.
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	
	/**
	 * @return Returns the hideVO.
	 */
	public AdminOptionHideVO getHideVO() {
		return hideVO;
	}
	/**
	 * @param hideVO The hideVO to set.
	 */
	public void setHideVO(AdminOptionHideVO hideVO) {
		this.hideVO = hideVO;
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
	 * @return Returns the adminOptionsChanged.
	 */
	public boolean isAdminOptionsChanged() {
		return adminOptionsChanged;
	}
	/**
	 * @param adminOptionsChanged The adminOptionsChanged to set.
	 */
	public void setAdminOptionsChanged(boolean adminOptionsChanged) {
		this.adminOptionsChanged = adminOptionsChanged;
	}
	/**
	 * @return Returns the changedAdminOptions.
	 */
	public List getChangedAdminOptions() {
		return changedAdminOptions;
	}
	/**
	 * @param changedAdminOptions The changedAdminOptions to set.
	 */
	public void setChangedAdminOptions(List changedAdminOptions) {
		this.changedAdminOptions = changedAdminOptions;
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
     * Returns the benefitSysId
     * @return int benefitSysId.
     */

    public int getBenefitSysId() {
        return benefitSysId;
    }
    /**
     * Sets the benefitSysId
     * @param benefitSysId.
     */

    public void setBenefitSysId(int benefitSysId) {
        this.benefitSysId = benefitSysId;
    }
}
