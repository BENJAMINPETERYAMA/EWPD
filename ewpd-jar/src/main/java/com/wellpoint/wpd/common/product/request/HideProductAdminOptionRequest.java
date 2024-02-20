/*
 * Created on Nov 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.request;

/**
 * @author u12573
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HideProductAdminOptionRequest extends ProductRequest{

	private int entityId;
	private String entityType;
    private int adminLevelOptionAssnId;
	private int benefitComponentId;
	/**
	 * @return Returns the adminLevelOptionAssnId.
	 */
	public int getAdminLevelOptionAssnId() {
		return adminLevelOptionAssnId;
	}
	/**
	 * @param adminLevelOptionAssnId The adminLevelOptionAssnId to set.
	 */
	public void setAdminLevelOptionAssnId(int adminLevelOptionAssnId) {
		this.adminLevelOptionAssnId = adminLevelOptionAssnId;
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
}
