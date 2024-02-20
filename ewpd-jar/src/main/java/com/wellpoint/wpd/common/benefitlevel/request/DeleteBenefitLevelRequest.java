/*
 * Created on Mar 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.benefitlevel.request;

import com.wellpoint.wpd.common.benefitlevel.vo.BenefitLevelVO;
import com.wellpoint.wpd.common.benefitlevel.vo.BenefitWrapperVO;
import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author U12322
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteBenefitLevelRequest extends WPDRequest{
	private BenefitWrapperVO benefitWrapperVO;
	private BenefitLevelVO benefitLevelVO;
	private String benefitLevels = null;
	private String benefitLines = null;
	private int benefitDefnId = 0;
	private String selectedTerm = null;
	/**
	 * @return Returns the benefitLevelVO.
	 */
	public BenefitLevelVO getBenefitLevelVO() {
		return benefitLevelVO;
	}
	/**
	 * @param benefitLevelVO The benefitLevelVO to set.
	 */
	public void setBenefitLevelVO(BenefitLevelVO benefitLevelVO) {
		this.benefitLevelVO = benefitLevelVO;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return Returns the benefitWrapperVO.
	 */
	public BenefitWrapperVO getBenefitWrapperVO() {
		return benefitWrapperVO;
	}
	/**
	 * @param benefitWrapperVO The benefitWrapperVO to set.
	 */
	public void setBenefitWrapperVO(BenefitWrapperVO benefitWrapperVO) {
		this.benefitWrapperVO = benefitWrapperVO;
	}
	/**
	 * @return Returns the benefitDefnId.
	 */
	public int getBenefitDefnId() {
		return benefitDefnId;
	}
	/**
	 * @param benefitDefnId The benefitDefnId to set.
	 */
	public void setBenefitDefnId(int benefitDefnId) {
		this.benefitDefnId = benefitDefnId;
	}
	/**
	 * @return Returns the benefitLevels.
	 */
	public String getBenefitLevels() {
		return benefitLevels;
	}
	/**
	 * @param benefitLevels The benefitLevels to set.
	 */
	public void setBenefitLevels(String benefitLevels) {
		this.benefitLevels = benefitLevels;
	}
	/**
	 * @return Returns the benefitLines.
	 */
	public String getBenefitLines() {
		return benefitLines;
	}
	/**
	 * @param benefitLines The benefitLines to set.
	 */
	public void setBenefitLines(String benefitLines) {
		this.benefitLines = benefitLines;
	}
	/**
	 * @return Returns the selectedTerm.
	 */
	public String getSelectedTerm() {
		return selectedTerm;
	}
	/**
	 * @param selectedTerm The selectedTerm to set.
	 */
	public void setSelectedTerm(String selectedTerm) {
		this.selectedTerm = selectedTerm;
	}
}
