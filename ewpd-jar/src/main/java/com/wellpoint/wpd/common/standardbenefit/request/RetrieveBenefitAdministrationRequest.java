/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.standardbenefit.vo.BenefitAdministrationVO;


/**
 * @author U14647
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveBenefitAdministrationRequest extends WPDRequest {

    private BenefitAdministrationVO BenefitAdministrationVO;
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
    }
    /**
	 * @return Returns the benefitAdministrationVO.
	 */
	public BenefitAdministrationVO getBenefitAdministrationVO() {
		return BenefitAdministrationVO;
	}
	/**
	 * @param benefitAdministrationVO The benefitAdministrationVO to set.
	 */
	public void setBenefitAdministrationVO(
			BenefitAdministrationVO benefitAdministrationVO) {
		BenefitAdministrationVO = benefitAdministrationVO;
	}
}
