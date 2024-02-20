/*
 * Created on July 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u12573
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LoadContractBenefitNoteResponse extends WPDResponse {
	
	private List contractNotetList = new ArrayList();
	
	/**
	 * Returns the contractNotetList
	 * @return List contractNotetList.
	 */
	public List getContractNotetList() {
		return contractNotetList;
	}
	/**
	 * Sets the contractNotetList
	 * @param contractNotetList.
	 */
	public void setContractNotetList(List contractNotetList) {
		this.contractNotetList = contractNotetList;
	}
}
