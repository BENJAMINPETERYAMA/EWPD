/*
 * Created on Nov 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.response;


import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author u19142
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveExpiredContractIdsResponse extends WPDResponse{

	public List expiredContractIdList;
	
	/**
	 * @return Returns the expiredContractIdList.
	 */
	public List getExpiredContractIdList() {
		return expiredContractIdList;
	}
	/**
	 * @param expiredContractIdList The expiredContractIdList to set.
	 */
	public void setExpiredContractIdList(List expiredContractIdList) {
		this.expiredContractIdList = expiredContractIdList;
	}
}
