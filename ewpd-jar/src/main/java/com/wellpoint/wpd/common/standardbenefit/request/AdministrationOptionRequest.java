/*
 * Created on Mar 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.vo.AdministrationOptionVO;


/**
 * @author u14617
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdministrationOptionRequest extends WPDRequest {
	
	private AdministrationOptionVO administrationOptionVO;
	
	private boolean actionForUpdateSequence ;
	
	private int benefitAdminSysId;
	
	private int benefitDefiniitonKey;
	
	private List adminList;

    /**
     * @return benefitAdminSysId
     * 
     * Returns the benefitAdminSysId.
     */
    public int getBenefitAdminSysId() {
        return benefitAdminSysId;
    }
    /**
     * @param benefitAdminSysId
     * 
     * Sets the benefitAdminSysId.
     */
    public void setBenefitAdminSysId(int benefitAdminSysId) {
        this.benefitAdminSysId = benefitAdminSysId;
    }
    /**
     * @return benefitDefiniitonKey
     * 
     * Returns the benefitDefiniitonKey.
     */
    public int getBenefitDefiniitonKey() {
        return benefitDefiniitonKey;
    }
    /**
     * @param benefitDefiniitonKey
     * 
     * Sets the benefitDefiniitonKey.
     */
    public void setBenefitDefiniitonKey(int benefitDefiniitonKey) {
        this.benefitDefiniitonKey = benefitDefiniitonKey;
    }
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return Returns the administrationOptionVO.
	 */
	public AdministrationOptionVO getAdministrationOptionVO() {
		return administrationOptionVO;
	}
	/**
	 * @param administrationOptionVO The administrationOptionVO to set.
	 */
	public void setAdministrationOptionVO(
			AdministrationOptionVO administrationOptionVO) {
		this.administrationOptionVO = administrationOptionVO;
	}
	
	/**
	 * @return Returns the actionForUpdateSequence.
	 */
	public boolean isActionForUpdateSequence() {
		return actionForUpdateSequence;
	}
	/**
	 * @param actionForUpdateSequence The actionForUpdateSequence to set.
	 */
	public void setActionForUpdateSequence(boolean actionForUpdateSequence) {
		this.actionForUpdateSequence = actionForUpdateSequence;
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
}
