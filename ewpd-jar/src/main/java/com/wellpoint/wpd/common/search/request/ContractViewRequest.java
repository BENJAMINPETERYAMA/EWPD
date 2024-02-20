/*
 * Created on Jul 31, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.search.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;

/**
 * @author u14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractViewRequest extends WPDRequest {

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 */
	
	  private int contractKey;
	  private int dataSegIdentifier;
	  

	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}
	/**
	 * @return Returns the contractKey.
	 */
	public int getContractKey() {
		return contractKey;
	}
	/**
	 * @param contractKey The contractKey to set.
	 */
	public void setContractKey(int contractKey) {
		this.contractKey = contractKey;
	}
	
	
	/**
	 * @return Returns the dataSegIdentifier.
	 */
	public int getDataSegIdentifier() {
		return dataSegIdentifier;
	}
	/**
	 * @param dataSegIdentifier The dataSegIdentifier to set.
	 */
	public void setDataSegIdentifier(int dataSegIdentifier) {
		this.dataSegIdentifier = dataSegIdentifier;
	}
}
