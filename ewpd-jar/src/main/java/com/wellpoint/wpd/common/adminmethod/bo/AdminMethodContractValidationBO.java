/*
 * Created on Mar 10, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.bo;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U16012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminMethodContractValidationBO  extends BusinessObject{
	
	   private int benefitSysId;
	   private int entitySysId;
	   private int benefitComSysId;
	   private int spsId;
	   private int benefitAdminSysId; 
	   
	   private int tierSysId; //CARS:AM2
	   
	   //CARS:AM2:START
	   public int getTierSysId() {
		return tierSysId;
	}
	/**
	 * @param tierSysId The tierSysId to set.
	 */
	public void setTierSysId(int tierSysId) {
		this.tierSysId = tierSysId;
	}
	   //CARS:AM2:END
	   
	   
	/**
	 * @return Returns the benefitAdminSysId.
	 */
	public int getBenefitAdminSysId() {
		return benefitAdminSysId;
	}
	/**
	 * @param benefitAdminSysId The benefitAdminSysId to set.
	 */
	public void setBenefitAdminSysId(int benefitAdminSysId) {
		this.benefitAdminSysId = benefitAdminSysId;
	}
	/**
	 * @return Returns the benefitComSysId.
	 */
	public int getBenefitComSysId() {
		return benefitComSysId;
	}
	/**
	 * @param benefitComSysId The benefitComSysId to set.
	 */
	public void setBenefitComSysId(int benefitComSysId) {
		this.benefitComSysId = benefitComSysId;
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
	 * @return Returns the spsId.
	 */
	public int getSpsId() {
		return spsId;
	}
	/**
	 * @param spsId The spsId to set.
	 */
	public void setSpsId(int spsId) {
		this.spsId = spsId;
	}
	   /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
     */
    public boolean equals(BusinessObject object) {
        // TODO Auto-generated method stub
        return false;
    }
    /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
     */
    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }
    /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
     */
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }


}
