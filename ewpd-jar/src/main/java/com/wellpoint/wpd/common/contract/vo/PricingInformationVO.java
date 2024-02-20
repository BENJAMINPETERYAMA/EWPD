/*
 * Created on May 8, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.vo;

 

/**
 * @author U15236
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PricingInformationVO implements java.io.Serializable {
    
	private java.util.List pricingInfoList;

    private PricingInfoItemVO pricingInfoItemVO;

    
	/**
	 * @return Returns the pricingInfoItemVO.
	 */
	public PricingInfoItemVO getPricingInfoItemVO() {
		return pricingInfoItemVO;
	}
	/**
	 * @param pricingInfoItemVO The pricingInfoItemVO to set.
	 */
	public void setPricingInfoItemVO(PricingInfoItemVO pricingInfoItemVO) {
		this.pricingInfoItemVO = pricingInfoItemVO;
	}
	/**
	 * @return Returns the pricingInfoList.
	 */
	public java.util.List getPricingInfoList() {
		return pricingInfoList;
	}
	/**
	 * @param pricingInfoList The pricingInfoList to set.
	 */
	public void setPricingInfoList(java.util.List pricingInfoList) {
		this.pricingInfoList = pricingInfoList;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("pricingInfoList = ").append(pricingInfoList).append(" ");
        buffer.append("pricingInfoItemVO = ").append(pricingInfoItemVO.toString()).append(" ");
        return buffer.toString();
	}
}
