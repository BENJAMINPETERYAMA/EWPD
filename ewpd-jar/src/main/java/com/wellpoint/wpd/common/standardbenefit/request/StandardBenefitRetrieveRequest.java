/*
 * Created on Mar 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.vo.StandardBenefitVO;


/**
 * @author U14647
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandardBenefitRetrieveRequest extends WPDRequest  {

    private StandardBenefitVO standardBenefitVO;
    
    private boolean edit;
    /**
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     * @throws ValidationException
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
    }
    
    /**
     * @return Returns the standardBenefitVO.
     */
    public StandardBenefitVO getStandardBenefitVO() {
        return standardBenefitVO;
    }
    /**
     * @param standardBenefitVO The standardBenefitVO to set.
     */
    public void setStandardBenefitVO(StandardBenefitVO standardBenefitVO) {
        this.standardBenefitVO = standardBenefitVO;
    }
    
    /**
	 * @return Returns the edit.
	 */
	public boolean isEdit() {
		return edit;
	}
	/**
	 * @param edit The edit to set.
	 */
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	
}
