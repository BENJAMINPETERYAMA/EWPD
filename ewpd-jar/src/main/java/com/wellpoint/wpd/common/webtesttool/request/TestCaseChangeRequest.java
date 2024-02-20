/*
 * Created on Aug 11, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.webtesttool.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.webtesttool.vo.ClaimHeaderVO;
import com.wellpoint.wpd.common.webtesttool.vo.ClaimLineVO;
import com.wellpoint.wpd.common.webtesttool.vo.TestCaseVO;

/**
 * @author U20628
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestCaseChangeRequest extends WPDRequest{

	private TestCaseVO testCaseVO;
	private ClaimHeaderVO claimHeaderVO;
	private List claimLineList;
	private ClaimLineVO claimLineVO;
	private boolean isEdit;
	
	/**
	 * @return Returns the isEdit.
	 */
	public boolean isEdit() {
		return isEdit;
	}
	/**
	 * @param isEdit The isEdit to set.
	 */
	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return Returns the testCaseVO.
	 */
	public TestCaseVO getTestCaseVO() {
		return testCaseVO;
	}
	/**
	 * @param testCaseVO The testCaseVO to set.
	 */
	public void setTestCaseVO(TestCaseVO testCaseVO) {
		this.testCaseVO = testCaseVO;
	}
	/**
	 * @return Returns the claimHeaderVO.
	 */
	public ClaimHeaderVO getClaimHeaderVO() {
		return claimHeaderVO;
	}
	/**
	 * @param claimHeaderVO The claimHeaderVO to set.
	 */
	public void setClaimHeaderVO(ClaimHeaderVO claimHeaderVO) {
		this.claimHeaderVO = claimHeaderVO;
	}
	/**
	 * @return Returns the claimLineList.
	 */
	public List getClaimLineList() {
		return claimLineList;
	}
	/**
	 * @param claimLineList The claimLineList to set.
	 */
	public void setClaimLineList(List claimLineList) {
		this.claimLineList = claimLineList;
	}
	/**
	 * @return Returns the claimLineVO.
	 */
	public ClaimLineVO getClaimLineVO() {
		return claimLineVO;
	}
	/**
	 * @param claimLineVO The claimLineVO to set.
	 */
	public void setClaimLineVO(ClaimLineVO claimLineVO) {
		this.claimLineVO = claimLineVO;
	}
}
