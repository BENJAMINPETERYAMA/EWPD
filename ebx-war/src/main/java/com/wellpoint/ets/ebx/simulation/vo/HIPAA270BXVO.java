/*
 * Created on Nov 26, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.ets.ebx.simulation.vo;

import com.wellpoint.ets.ebx.systemconfiguration.vo.SystemConfigurationVO;


/**
 * @author U23665
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class HIPAA270BXVO {

    private MemberInfoVO  memberInfo ;

    private String startDate;
    
    private String endDate;

    private String serviceTypeCode;
    
    private SystemConfigurationVO systemConfigurationVO;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public MemberInfoVO getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfoVO memberInfo) {
        this.memberInfo = memberInfo;
    }

    public String getServiceTypeCode() {
        return serviceTypeCode;
    }

    public void setServiceTypeCode(String serviceTypeCode) {
        this.serviceTypeCode = serviceTypeCode;
    }

    /**
     * This function return a SystemConfigurationVO
     * @return
     */
	public SystemConfigurationVO getSystemConfigurationVO() {
		return systemConfigurationVO;
	}

	/**
	 * This function sets a SystemConfigurationVO
	 * @param systemConfigurationVO
	 */
	public void setSystemConfigurationVO(SystemConfigurationVO systemConfigurationVO) {
		this.systemConfigurationVO = systemConfigurationVO;
	}
    
    
}
