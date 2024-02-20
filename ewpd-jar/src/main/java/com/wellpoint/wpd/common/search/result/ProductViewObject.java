/*
 * Created on Aug 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.search.result;

import java.util.Date;

/**
 * @author u14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductViewObject {
	private int productKey;
	private String  productName;
	
	private Date effectiveDate;
	private Date expiryDate;
	
	private int version;
	
    private String lineOfBusinessCode;
    
    private String businessEntityCode;
    
    private String businessGroupCode;

	
	private int parentKey;
	

	/**
	 * @return Returns the businessEntityCode.
	 */
	public String getBusinessEntityCode() {
		return businessEntityCode;
	}
	/**
	 * @param businessEntityCode The businessEntityCode to set.
	 */
	public void setBusinessEntityCode(String businessEntityCode) {
		this.businessEntityCode = businessEntityCode;
	}
	/**
	 * @return Returns the businessGroupCode.
	 */
	public String getBusinessGroupCode() {
		return businessGroupCode;
	}
	/**
	 * @param businessGroupCode The businessGroupCode to set.
	 */
	public void setBusinessGroupCode(String businessGroupCode) {
		this.businessGroupCode = businessGroupCode;
	}
	/**
	 * @return Returns the lineOfBusinessCode.
	 */
	public String getLineOfBusinessCode() {
		return lineOfBusinessCode;
	}
	/**
	 * @param lineOfBusinessCode The lineOfBusinessCode to set.
	 */
	public void setLineOfBusinessCode(String lineOfBusinessCode) {
		this.lineOfBusinessCode = lineOfBusinessCode;
	}
	/**
	 * @return Returns the effectiveDate.
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @return Returns the expiryDate.
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	/**
	 * @return Returns the parentKey.
	 */
	public int getParentKey() {
		return parentKey;
	}
	/**
	 * @param parentKey The parentKey to set.
	 */
	public void setParentKey(int parentKey) {
		this.parentKey = parentKey;
	}
	/**
	 * @return Returns the productKey.
	 */
	public int getProductKey() {
		return productKey;
	}
	/**
	 * @param productKey The productKey to set.
	 */
	public void setProductKey(int productKey) {
		this.productKey = productKey;
	}
	/**
	 * @return Returns the productName.
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName The productName to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	
	
}
