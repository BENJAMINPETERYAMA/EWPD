/*
 * Created on Jul 20, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.referencemapping.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.referencemapping.vo.ReferencemappingVO;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreateReferenceMappingRequest extends WPDRequest{
	
	private ReferencemappingVO referencemappingVO;
	
	

	/**
	 * @return Returns the referencemappingVO.
	 */
	public ReferencemappingVO getReferencemappingVO() {
		return referencemappingVO;
	}
	/**
	 * @param referencemappingVO The referencemappingVO to set.
	 */
	public void setReferencemappingVO(ReferencemappingVO referencemappingVO) {
		this.referencemappingVO = referencemappingVO;
	}
	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub
		
	}

}
