/*
 * Created on Aug 11, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.webtesttool.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.webtesttool.vo.TestCaseVO;

/**
 * @author U20628
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestCaseEditRequest extends WPDRequest{
	
    private TestCaseVO testCaseVO;

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
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

}
