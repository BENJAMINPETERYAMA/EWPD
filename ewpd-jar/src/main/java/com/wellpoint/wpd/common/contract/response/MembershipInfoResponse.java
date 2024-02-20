/*
 * Created on Sep 20, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.List;

/**
 * @author U14609
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MembershipInfoResponse extends ContractResponse{
	private List membershipList;
	
	

	/**
	 * @return Returns the membershipList.
	 */
	public List getMembershipList() {
		return membershipList;
	}
	/**
	 * @param membershipList The membershipList to set.
	 */
	public void setMembershipList(List membershipList) {
		this.membershipList = membershipList;
	}
}
