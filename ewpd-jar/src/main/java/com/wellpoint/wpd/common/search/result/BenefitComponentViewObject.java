/*
 * Created on Jul 31, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.search.result;

/**
 * @author U15427
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BenefitComponentViewObject {
	
	private int identifier;
	
	private int parent_id;
	
	private String type;
	
    private String benefitComponentStatus;
    
    private int benefitComponentVersion;
    
    private String benefitComponentName;
    
	private String lineOfBusinessCode;

	private String businessEntityCode;

	private String businessGroupCode;
	/**
	 * @return Returns the benefitComponentName.
	 */
	public String getBenefitComponentName() {
		return benefitComponentName;
	}
	/**
	 * @param benefitComponentName The benefitComponentName to set.
	 */
	public void setBenefitComponentName(String benefitComponentName) {
		this.benefitComponentName = benefitComponentName;
	}
	/**
	 * @return Returns the benefitComponentStatus.
	 */
	public String getBenefitComponentStatus() {
		return benefitComponentStatus;
	}
	/**
	 * @param benefitComponentStatus The benefitComponentStatus to set.
	 */
	public void setBenefitComponentStatus(String benefitComponentStatus) {
		this.benefitComponentStatus = benefitComponentStatus;
	}
	/**
	 * @return Returns the benefitComponentVersion.
	 */
	public int getBenefitComponentVersion() {
		return benefitComponentVersion;
	}
	/**
	 * @param benefitComponentVersion The benefitComponentVersion to set.
	 */
	public void setBenefitComponentVersion(int benefitComponentVersion) {
		this.benefitComponentVersion = benefitComponentVersion;
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
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
}
