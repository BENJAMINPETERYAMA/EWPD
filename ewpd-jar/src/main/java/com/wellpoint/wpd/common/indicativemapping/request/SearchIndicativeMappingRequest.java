/*
 * Created on Jun 3, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.indicativemapping.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.indicativemapping.vo.IndicativeMappingVO;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SearchIndicativeMappingRequest extends WPDRequest {
	
	
	private List indicativeList;
	
	private List spsList;
	
	private List benefitList;
	
	private IndicativeMappingVO indicativeMappingVO;
	
	private int action;
	
	private String region;
	
	/**
	 * @return Returns the benefitList.
	 */
	public List getBenefitList() {
		return benefitList;
	}
	/**
	 * @param benefitList The benefitList to set.
	 */
	public void setBenefitList(List benefitList) {
		this.benefitList = benefitList;
	}
	/**
	 * @return Returns the indicativeList.
	 */
	public List getIndicativeList() {
		return indicativeList;
	}
	/**
	 * @param indicativeList The indicativeList to set.
	 */
	public void setIndicativeList(List indicativeList) {
		this.indicativeList = indicativeList;
	}
	/**
	 * @return Returns the spsList.
	 */
	public List getSpsList() {
		return spsList;
	}
	/**
	 * @param spsList The spsList to set.
	 */
	public void setSpsList(List spsList) {
		this.spsList = spsList;
	}
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

	/**
	 * @return Returns the action.
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(int action) {
		this.action = action;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
}
