/*
 * Created on May 24, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.blueexchange.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author U19103
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DummyVarMappingBO extends BusinessObject{
	
	
	private String contractVaiable;
	
	private String status;
	
	private String updateStatus;
	
	private String mappingsysid;
	
	private String message;
	
	private String msgTypeInd;
	
	private String senBnftInd;
	
	//private String noteTypeCde;
	
	private String varMppngSttsCd;
	
	private String createdUser;
	
	private Date createdTimestamp;
	
	private String lastUpdatedUser;
	
	private Date lastUpdatedTimestamp;
	
	private String dataElementID;
	
	private String dataElementVal;
	
	private List dataElementIDList;
	
	private List dataElementValList;

	private String contrctDesc;
	
	private String mappngReq;
	
	private String seqNum;
	
	private int rownum;

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	/**
	 * @return Returns the lastUpdatedTimestamp.
	 */
	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	/**
	 * @param lastUpdatedTimestamp The lastUpdatedTimestamp to set.
	 */
	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}

	/**
	 * @return Returns the contractVaiable.
	 */
	public String getContractVaiable() {
		return contractVaiable;
	}
	/**
	 * @param contractVaiable The contractVaiable to set.
	 */
	public void setContractVaiable(String contractVaiable) {
		this.contractVaiable = contractVaiable;
	}
	/**
	 * @return Returns the contrctDesc.
	 */
	public String getContrctDesc() {
		return contrctDesc;
	}
	/**
	 * @param contrctDesc The contrctDesc to set.
	 */
	public void setContrctDesc(String contrctDesc) {
		this.contrctDesc = contrctDesc;
	}
	/**
	 * @return Returns the createdTimestamp.
	 */
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	/**
	 * @param createdTimestamp The createdTimestamp to set.
	 */
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	/**
	 * @return Returns the createdUser.
	 */
	public String getCreatedUser() {
		return createdUser;
	}
	/**
	 * @param createdUser The createdUser to set.
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * @return Returns the dataElementID.
	 */
	public String getDataElementID() {
		return dataElementID;
	}
	/**
	 * @param dataElementID The dataElementID to set.
	 */
	public void setDataElementID(String dataElementID) {
		this.dataElementID = dataElementID;
	}
	/**
	 * @return Returns the dataElementIDList.
	 */
	public List getDataElementIDList() {
		return dataElementIDList;
	}
	/**
	 * @param dataElementIDList The dataElementIDList to set.
	 */
	public void setDataElementIDList(List dataElementIDList) {
		this.dataElementIDList = dataElementIDList;
	}
	/**
	 * @return Returns the dataElementVal.
	 */
	public String getDataElementVal() {
		return dataElementVal;
	}
	/**
	 * @param dataElementVal The dataElementVal to set.
	 */
	public void setDataElementVal(String dataElementVal) {
		this.dataElementVal = dataElementVal;
	}
	/**
	 * @return Returns the dataElementValList.
	 */
	public List getDataElementValList() {
		return dataElementValList;
	}
	/**
	 * @param dataElementValList The dataElementValList to set.
	 */
	public void setDataElementValList(List dataElementValList) {
		this.dataElementValList = dataElementValList;
	}
	/**
	 * @return Returns the lastUpdatedUser.
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}
	/**
	 * @param lastUpdatedUser The lastUpdatedUser to set.
	 */
	public void setLastUpdatedUser(String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}
	/**
	 * @return Returns the mappingsysid.
	 */
	public String getMappingsysid() {
		return mappingsysid;
	}
	/**
	 * @param mappingsysid The mappingsysid to set.
	 */
	public void setMappingsysid(String mappingsysid) {
		this.mappingsysid = mappingsysid;
	}
	/**
	 * @return Returns the mappngReq.
	 */
	public String getMappngReq() {
		return mappngReq;
	}
	/**
	 * @param mappngReq The mappngReq to set.
	 */
	public void setMappngReq(String mappngReq) {
		this.mappngReq = mappngReq;
	}
	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return Returns the msgTypeInd.
	 */
	public String getMsgTypeInd() {
		return msgTypeInd;
	}
	/**
	 * @param msgTypeInd The msgTypeInd to set.
	 */
	public void setMsgTypeInd(String msgTypeInd) {
		this.msgTypeInd = msgTypeInd;
	}
	/**
	 * @return Returns the rownum.
	 */
	public int getRownum() {
		return rownum;
	}
	/**
	 * @param rownum The rownum to set.
	 */
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	/**
	 * @return Returns the senBnftInd.
	 */
	public String getSenBnftInd() {
		return senBnftInd;
	}
	/**
	 * @param senBnftInd The senBnftInd to set.
	 */
	public void setSenBnftInd(String senBnftInd) {
		this.senBnftInd = senBnftInd;
	}
	/**
	 * @return Returns the seqNum.
	 */
	public String getSeqNum() {
		return seqNum;
	}
	/**
	 * @param seqNum The seqNum to set.
	 */
	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
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
	 * @return Returns the updateStatus.
	 */
	public String getUpdateStatus() {
		return updateStatus;
	}
	/**
	 * @param updateStatus The updateStatus to set.
	 */
	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}
	/**
	 * @return Returns the varMppngSttsCd.
	 */
	public String getVarMppngSttsCd() {
		return varMppngSttsCd;
	}
	/**
	 * @param varMppngSttsCd The varMppngSttsCd to set.
	 */
	public void setVarMppngSttsCd(String varMppngSttsCd) {
		this.varMppngSttsCd = varMppngSttsCd;
	}
}
