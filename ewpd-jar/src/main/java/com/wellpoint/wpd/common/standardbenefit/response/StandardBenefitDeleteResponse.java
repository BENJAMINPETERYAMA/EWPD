/*
 * Created on Mar 26, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;
import com.wellpoint.wpd.common.standardbenefit.bo.StandardBenefitBO;

import java.util.List;


/**
 * @author U14647
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitDeleteResponse extends WPDResponse{
	private StandardBenefitBO standardBenefitBO;
	
    //private StandardBenefitSearchVO standardBenefitSearchVO;
    
    private List searchResultList;
    
	private boolean success;

	/**
	 * @return Returns the standardBenefitBO.
	 */
	public StandardBenefitBO getStandardBenefitBO() {
		return standardBenefitBO;
	}
	/**
	 * @param standardBenefitBO The standardBenefitBO to set.
	 */
	public void setStandardBenefitBO(StandardBenefitBO standardBenefitBO) {
		this.standardBenefitBO = standardBenefitBO;
	}
    /**
     * @return Returns the success.
     */
    public boolean isSuccess() {
        return success;
    }
    /**
     * @param success The success to set.
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }
    /**
     * @return Returns the standardBenefitSearchVO.
     */
//    public StandardBenefitSearchVO getStandardBenefitSearchVO() {
//        return standardBenefitSearchVO;
//    }
    /**
     * @param standardBenefitSearchVO The standardBenefitSearchVO to set.
     */
//    public void setStandardBenefitSearchVO(
//            StandardBenefitSearchVO standardBenefitSearchVO) {
//        this.standardBenefitSearchVO = standardBenefitSearchVO;
//    }
    /**
     * @return Returns the searchResultList.
     */
    public List getSearchResultList() {
        return searchResultList;
    }
    /**
     * @param searchResultList The searchResultList to set.
     */
    public void setSearchResultList(List searchResultList) {
        this.searchResultList = searchResultList;
    }
}
