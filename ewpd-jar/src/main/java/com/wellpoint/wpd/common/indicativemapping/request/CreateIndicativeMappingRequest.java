/*
 * Created on Jun 3, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.indicativemapping.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.indicativemapping.vo.IndicativeMappingVO;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateIndicativeMappingRequest extends WPDRequest{
	
	private IndicativeMappingVO indicativeMappingVO;

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return Returns the indicativeMappingVO.
	 */
	public IndicativeMappingVO getIndicativeMappingVO() {
		return indicativeMappingVO;
	}
	/**
	 * @param indicativeMappingVO The indicativeMappingVO to set.
	 */
	public void setIndicativeMappingVO(IndicativeMappingVO indicativeMappingVO) {
		this.indicativeMappingVO = indicativeMappingVO;
	}
}
