/*
 * DeleteContractComponentNoteResponse.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class DeleteContractComponentNoteResponse extends  ContractResponse  {
    
    private List productNotetList = new ArrayList();

	/**
	 * @return Returns the productNotetList.
	 */
	public List getProductNotetList() {
		return productNotetList;
	}
	/**
	 * @param productNotetList The productNotetList to set.
	 */
	public void setProductNotetList(List productNotetList) {
		this.productNotetList = productNotetList;
	}

}
