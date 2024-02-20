/*
 * Created on Jul 31, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.search.result;

/**
 * @author u14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ContractViewObject {
	
	String contractId =null;
	int contractSegmentId ;
	int contractDateSegmentSysId;
	String status =null;
	int version =0;
	
	/**
	 * @return Returns the contractDateSegmentSysId.
	 */
	public int getContractDateSegmentSysId() {
		return contractDateSegmentSysId;
	}
	/**
	 * @param contractDateSegmentSysId The contractDateSegmentSysId to set.
	 */
	public void setContractDateSegmentSysId(int contractDateSegmentSysId) {
		this.contractDateSegmentSysId = contractDateSegmentSysId;
	}
	
	/**
	 * @return Returns the contractId.
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * @param contractId The contractId to set.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return Returns the contractSegmentId.
	 */
	public int getContractSegmentId() {
		return contractSegmentId;
	}
	/**
	 * @param contractSegmentId The contractSegmentId to set.
	 */
	public void setContractSegmentId(int contractSegmentId) {
		this.contractSegmentId = contractSegmentId;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
}
