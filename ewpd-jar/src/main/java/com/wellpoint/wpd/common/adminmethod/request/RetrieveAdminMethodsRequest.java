/*
 * Created on Sep 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.adminmethod.request;

import com.wellpoint.wpd.common.adminmethod.vo.AdminMethodsPopupVO;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author u13541
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
    
public class RetrieveAdminMethodsRequest extends WPDRequest{
    public void validate(){
        
    }
     private int action;
     
     private int spsId;

     private AdminMethodsPopupVO adminMethodsPopupVO;
     
     private int entityId;
     
     private String entityType;
     
     private int stdBenftId;
     
     private int contractDateSgmntId;
     
     private int stdDefid;
     
     private int benefitCompId;
     
     private int prodStructId;
     
     private int prodId;
     
     private int contractId;
     
     private int adminId;
     
     private String bcName;
     
     private String entityTypeForValidation;
     //CARS AM1 START
     private int benefitTierSysId;
     
     
	/**
	 * @return Returns the benefitTierSysId.
	 */
	public int getBenefitTierSysId() {
		return benefitTierSysId;
	}
	/**
	 * @param benefitTierSysId The benefitTierSysId to set.
	 */
	public void setBenefitTierSysId(int benefitTierSysId) {
		this.benefitTierSysId = benefitTierSysId;
	}
	//CARS AM1 END
	/**
	 * @return Returns the entityTypeForValidation.
	 */
	public String getEntityTypeForValidation() {
		return entityTypeForValidation;
	}
	/**
	 * @param entityTypeForValidation The entityTypeForValidation to set.
	 */
	public void setEntityTypeForValidation(String entityTypeForValidation) {
		this.entityTypeForValidation = entityTypeForValidation;
	}
	/**
	 * @return Returns the bcName.
	 */
	public String getBcName() {
		return bcName;
	}
	/**
	 * @param bcName The bcName to set.
	 */
	public void setBcName(String bcName) {
		this.bcName = bcName;
	}
	/**
	 * @return Returns the stdBenftId.
	 */
	public int getStdBenftId() {
		return stdBenftId;
	}
	/**
	 * @param stdBenftId The stdBenftId to set.
	 */
	public void setStdBenftId(int stdBenftId) {
		this.stdBenftId = stdBenftId;
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
     * @return spsId
     * 
     * Returns the spsId.
     */
    public int getSpsId() {
        return spsId;
    }
    /**
     * @param spsId
     * 
     * Sets the spsId.
     */
    public void setSpsId(int spsId) {
        this.spsId = spsId;
    }
    /**
     * @return Returns the action.
     */
    public int getAction() {
        return action;
    }
    /**
     * @param action The action to set.
     */
    public void setAction(int action) {
        this.action = action;
    }
    
    /**
     * @return Returns the adminMethodsPopupVO.
     */
    public AdminMethodsPopupVO getAdminMethodsPopupVO() {
        return adminMethodsPopupVO;
    }
    /**
     * @param adminMethodsPopupVO The adminMethodsPopupVO to set.
     */
    public void setAdminMethodsPopupVO(AdminMethodsPopupVO adminMethodsPopupVO) {
        this.adminMethodsPopupVO = adminMethodsPopupVO;
    }
    /**
     * Returns the contractDateSgmntId
     * @return int contractDateSgmntId.
     */

    public int getContractDateSgmntId() {
        return contractDateSgmntId;
    }
    /**
     * Sets the contractDateSgmntId
     * @param contractDateSgmntId.
     */

    public void setContractDateSgmntId(int contractDateSgmntId) {
        this.contractDateSgmntId = contractDateSgmntId;
    }
    
    /**
     * Returns the stdDefid
     * @return int stdDefid.
     */
    public int getStdDefid() {
        return stdDefid;
    }
    /**
     * Sets the stdDefid
     * @param stdDefid.
     */
    public void setStdDefid(int stdDefid) {
        this.stdDefid = stdDefid;
    }
    
    /**
     * Returns the benefitCompId
     * @return int benefitCompId.
     */
    public int getBenefitCompId() {
        return benefitCompId;
    }
    /**
     * Sets the benefitCompId
     * @param benefitCompId.
     */
    public void setBenefitCompId(int benefitCompId) {
        this.benefitCompId = benefitCompId;
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
    /**
     * Returns the prodId
     * @return int prodId.
     */
    public int getProdId() {
        return prodId;
    }
    /**
     * Sets the prodId
     * @param prodId.
     */
    public void setProdId(int prodId) {
        this.prodId = prodId;
    }
    /**
     * Returns the prodStructId
     * @return int prodStructId.
     */
    public int getProdStructId() {
        return prodStructId;
    }
    /**
     * Sets the prodStructId
     * @param prodStructId.
     */
    public void setProdStructId(int prodStructId) {
        this.prodStructId = prodStructId;
    }


    /**
     * Returns the adminId
     * @return int adminId.
     */

    public int getAdminId() {
        return adminId;
    }
    /**
     * Sets the adminId
     * @param adminId.
     */

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
