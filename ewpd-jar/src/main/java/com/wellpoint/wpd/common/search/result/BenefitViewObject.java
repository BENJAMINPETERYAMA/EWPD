/*
 * Created on Jul 30, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.search.result;


/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitViewObject {

	private int identifier;
	private int parent_id;
    private String status;
    private int versionNo;
    private String benefitName;
	private String lineOfBusinessCode;

	private String businessEntityCode;

	private String businessGroupCode;
	
	
	
	/**
	 * @return Returns the identifier.
	 */
	public int getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier The identifier to set.
	 */
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	
	/**
	 * @return Returns the parent_id.
	 */
	public int getParent_id() {
		return parent_id;
	}
	/**
	 * @param parent_id The parent_id to set.
	 */
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the versionNo.
	 */
	public int getVersionNo() {
		return versionNo;
	}
	/**
	 * @param versionNo The versionNo to set.
	 */
	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}
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
	 * @return Returns the benefitName.
	 */
	public String getBenefitName() {
		return benefitName;
	}
	/**
	 * @param benefitName The benefitName to set.
	 */
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
}
