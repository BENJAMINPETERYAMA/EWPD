/*
 * SPSMappingBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.blueexchange.bo;

import java.util.Date;
import java.util.List;

import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SPSMappingBO extends BusinessObject {

	private String spsParameter;
	private String eb01Value;
	private String eb02Value;
	private String eb06Value;
	private String eb09Value;
	private String hsd1Value;
	private String hsd2Value;
	private String hsd3Value;
	private String hsd4Value;
	private String hsd5Value;
	private String hsd6Value;
	private String hsd7Value;
	private String hsd8Value;
	private String spsParameterDesc;
	private String eb01ValueDesc;
	private String eb02ValueDesc;
	private String eb06ValueDesc;
	private String eb09ValueDesc;
	private String hsd1ValueDesc;
	private String hsd2ValueDesc;
	private String hsd3ValueDesc;
	private String hsd4ValueDesc;
	private String hsd5ValueDesc;
	private String hsd6ValueDesc;
	private String createdUser;
	private Date createdTimestamp;
	private String lastUpdatedUser;
	private Date lastUpdatedTimestamp;
	private String accumulatorSPSID;
	private String accummulatorSPSDesc;
	private List accummulatorSPSIDList; 
	private List spsParameterList;
	private List eb01ValueList;
	private List eb02ValueList;
	private List eb06ValueList;
	private List eb09ValueList;
	private List hsd1ValueList;
	private List hsd2ValueList;
	private List hsd3ValueList;
	private List hsd4ValueList;
	private List hsd5ValueList;
	private List hsd6ValueList; 
	private String statusCd;

	
	/**
	 * @return Returns the eb01Value.
	 */
	public String getEb01Value() {
		return eb01Value;
	}
	/**
	 * @param eb01Value The eb01Value to set.
	 */
	public void setEb01Value(String eb01Value) {
		this.eb01Value = eb01Value;
	}
	/**
	 * @return Returns the eb01ValueDesc.
	 */
	public String getEb01ValueDesc() {
		return eb01ValueDesc;
	}
	/**
	 * @param eb01ValueDesc The eb01ValueDesc to set.
	 */
	public void setEb01ValueDesc(String eb01ValueDesc) {
		this.eb01ValueDesc = eb01ValueDesc;
	}
	/**
	 * @return Returns the eb02Value.
	 */
	public String getEb02Value() {
		return eb02Value;
	}
	/**
	 * @param eb02Value The eb02Value to set.
	 */
	public void setEb02Value(String eb02Value) {
		this.eb02Value = eb02Value;
	}
	/**
	 * @return Returns the eb02ValueDesc.
	 */
	public String getEb02ValueDesc() {
		return eb02ValueDesc;
	}
	/**
	 * @param eb02ValueDesc The eb02ValueDesc to set.
	 */
	public void setEb02ValueDesc(String eb02ValueDesc) {
		this.eb02ValueDesc = eb02ValueDesc;
	}
	/**
	 * @return Returns the eb06Value.
	 */
	public String getEb06Value() {
		return eb06Value;
	}
	/**
	 * @param eb06Value The eb06Value to set.
	 */
	public void setEb06Value(String eb06Value) {
		this.eb06Value = eb06Value;
	}
	/**
	 * @return Returns the eb06ValueDesc.
	 */
	public String getEb06ValueDesc() {
		return eb06ValueDesc;
	}
	/**
	 * @param eb06ValueDesc The eb06ValueDesc to set.
	 */
	public void setEb06ValueDesc(String eb06ValueDesc) {
		this.eb06ValueDesc = eb06ValueDesc;
	}
	/**
	 * @return Returns the eb09Value.
	 */
	public String getEb09Value() {
		return eb09Value;
	}
	/**
	 * @param eb09Value The eb09Value to set.
	 */
	public void setEb09Value(String eb09Value) {
		this.eb09Value = eb09Value;
	}
	/**
	 * @return Returns the eb09ValueDesc.
	 */
	public String getEb09ValueDesc() {
		return eb09ValueDesc;
	}
	/**
	 * @param eb09ValueDesc The eb09ValueDesc to set.
	 */
	public void setEb09ValueDesc(String eb09ValueDesc) {
		this.eb09ValueDesc = eb09ValueDesc;
	}
	/**
	 * @return Returns the hsd1Value.
	 */
	public String getHsd1Value() {
		return hsd1Value;
	}
	/**
	 * @param hsd1Value The hsd1Value to set.
	 */
	public void setHsd1Value(String hsd1Value) {
		this.hsd1Value = hsd1Value;
	}
	/**
	 * @return Returns the hsd1ValueDesc.
	 */
	public String getHsd1ValueDesc() {
		return hsd1ValueDesc;
	}
	/**
	 * @param hsd1ValueDesc The hsd1ValueDesc to set.
	 */
	public void setHsd1ValueDesc(String hsd1ValueDesc) {
		this.hsd1ValueDesc = hsd1ValueDesc;
	}
	/**
	 * @return Returns the hsd2Value.
	 */
	public String getHsd2Value() {
		return hsd2Value;
	}
	/**
	 * @param hsd2Value The hsd2Value to set.
	 */
	public void setHsd2Value(String hsd2Value) {
		this.hsd2Value = hsd2Value;
	}
	/**
	 * @return Returns the hsd2ValueDesc.
	 */
	public String getHsd2ValueDesc() {
		return hsd2ValueDesc;
	}
	/**
	 * @param hsd2ValueDesc The hsd2ValueDesc to set.
	 */
	public void setHsd2ValueDesc(String hsd2ValueDesc) {
		this.hsd2ValueDesc = hsd2ValueDesc;
	}
	/**
	 * @return Returns the hsd3Value.
	 */
	public String getHsd3Value() {
		return hsd3Value;
	}
	/**
	 * @param hsd3Value The hsd3Value to set.
	 */
	public void setHsd3Value(String hsd3Value) {
		this.hsd3Value = hsd3Value;
	}
	/**
	 * @return Returns the hsd3ValueDesc.
	 */
	public String getHsd3ValueDesc() {
		return hsd3ValueDesc;
	}
	/**
	 * @param hsd3ValueDesc The hsd3ValueDesc to set.
	 */
	public void setHsd3ValueDesc(String hsd3ValueDesc) {
		this.hsd3ValueDesc = hsd3ValueDesc;
	}
	/**
	 * @return Returns the hsd4Value.
	 */
	public String getHsd4Value() {
		return hsd4Value;
	}
	/**
	 * @param hsd4Value The hsd4Value to set.
	 */
	public void setHsd4Value(String hsd4Value) {
		this.hsd4Value = hsd4Value;
	}
	/**
	 * @return Returns the hsd4ValueDesc.
	 */
	public String getHsd4ValueDesc() {
		return hsd4ValueDesc;
	}
	/**
	 * @param hsd4ValueDesc The hsd4ValueDesc to set.
	 */
	public void setHsd4ValueDesc(String hsd4ValueDesc) {
		this.hsd4ValueDesc = hsd4ValueDesc;
	}
	/**
	 * @return Returns the hsd5Value.
	 */
	public String getHsd5Value() {
		return hsd5Value;
	}
	/**
	 * @param hsd5Value The hsd5Value to set.
	 */
	public void setHsd5Value(String hsd5Value) {
		this.hsd5Value = hsd5Value;
	}
	/**
	 * @return Returns the hsd5ValueDesc.
	 */
	public String getHsd5ValueDesc() {
		return hsd5ValueDesc;
	}
	/**
	 * @param hsd5ValueDesc The hsd5ValueDesc to set.
	 */
	public void setHsd5ValueDesc(String hsd5ValueDesc) {
		this.hsd5ValueDesc = hsd5ValueDesc;
	}
	/**
	 * @return Returns the hsd6Value.
	 */
	public String getHsd6Value() {
		return hsd6Value;
	}
	/**
	 * @param hsd6Value The hsd6Value to set.
	 */
	public void setHsd6Value(String hsd6Value) {
		this.hsd6Value = hsd6Value;
	}
	/**
	 * @return Returns the hsd6ValueDesc.
	 */
	public String getHsd6ValueDesc() {
		return hsd6ValueDesc;
	}
	/**
	 * @param hsd6ValueDesc The hsd6ValueDesc to set.
	 */
	public void setHsd6ValueDesc(String hsd6ValueDesc) {
		this.hsd6ValueDesc = hsd6ValueDesc;
	}
		/**
	 * @return Returns the spsParameter.
	 */
	public String getSpsParameter() {
		return spsParameter;
	}
	/**
	 * @param spsParameter The spsParameter to set.
	 */
	public void setSpsParameter(String spsParameter) {
		this.spsParameter = spsParameter;
	}
	/**
	 * @return Returns the spsParameterDesc.
	 */
	public String getSpsParameterDesc() {
		return spsParameterDesc;
	}
	/**
	 * @param spsParameterDesc The spsParameterDesc to set.
	 */
	public void setSpsParameterDesc(String spsParameterDesc) {
		this.spsParameterDesc = spsParameterDesc;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#equals(com.wellpoint.wpd.common.bo.BusinessObject)
	 */
	public boolean equals(BusinessObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wellpoint.wpd.common.bo.BusinessObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the eb01ValueList.
	 */
	public List getEb01ValueList() {
		return eb01ValueList;
	}
	/**
	 * @param eb01ValueList The eb01ValueList to set.
	 */
	public void setEb01ValueList(List eb01ValueList) {
		this.eb01ValueList = eb01ValueList;
	}
	/**
	 * @return Returns the eb02ValueList.
	 */
	public List getEb02ValueList() {
		return eb02ValueList;
	}
	/**
	 * @param eb02ValueList The eb02ValueList to set.
	 */
	public void setEb02ValueList(List eb02ValueList) {
		this.eb02ValueList = eb02ValueList;
	}
	/**
	 * @return Returns the eb06ValueList.
	 */
	public List getEb06ValueList() {
		return eb06ValueList;
	}
	/**
	 * @param eb06ValueList The eb06ValueList to set.
	 */
	public void setEb06ValueList(List eb06ValueList) {
		this.eb06ValueList = eb06ValueList;
	}
	/**
	 * @return Returns the eb09ValueList.
	 */
	public List getEb09ValueList() {
		return eb09ValueList;
	}
	/**
	 * @param eb09ValueList The eb09ValueList to set.
	 */
	public void setEb09ValueList(List eb09ValueList) {
		this.eb09ValueList = eb09ValueList;
	}
	/**
	 * @return Returns the hsd1ValueList.
	 */
	public List getHsd1ValueList() {
		return hsd1ValueList;
	}
	/**
	 * @param hsd1ValueList The hsd1ValueList to set.
	 */
	public void setHsd1ValueList(List hsd1ValueList) {
		this.hsd1ValueList = hsd1ValueList;
	}
	/**
	 * @return Returns the hsd2ValueList.
	 */
	public List getHsd2ValueList() {
		return hsd2ValueList;
	}
	/**
	 * @param hsd2ValueList The hsd2ValueList to set.
	 */
	public void setHsd2ValueList(List hsd2ValueList) {
		this.hsd2ValueList = hsd2ValueList;
	}
	/**
	 * @return Returns the hsd3ValueList.
	 */
	public List getHsd3ValueList() {
		return hsd3ValueList;
	}
	/**
	 * @param hsd3ValueList The hsd3ValueList to set.
	 */
	public void setHsd3ValueList(List hsd3ValueList) {
		this.hsd3ValueList = hsd3ValueList;
	}
	/**
	 * @return Returns the hsd4ValueList.
	 */
	public List getHsd4ValueList() {
		return hsd4ValueList;
	}
	/**
	 * @param hsd4ValueList The hsd4ValueList to set.
	 */
	public void setHsd4ValueList(List hsd4ValueList) {
		this.hsd4ValueList = hsd4ValueList;
	}
	/**
	 * @return Returns the hsd5ValueList.
	 */
	public List getHsd5ValueList() {
		return hsd5ValueList;
	}
	/**
	 * @param hsd5ValueList The hsd5ValueList to set.
	 */
	public void setHsd5ValueList(List hsd5ValueList) {
		this.hsd5ValueList = hsd5ValueList;
	}
	/**
	 * @return Returns the hsd6ValueList.
	 */
	public List getHsd6ValueList() {
		return hsd6ValueList;
	}
	/**
	 * @param hsd6ValueList The hsd6ValueList to set.
	 */
	public void setHsd6ValueList(List hsd6ValueList) {
		this.hsd6ValueList = hsd6ValueList;
	}
	/**
	 * @return Returns the spsParameterList.
	 */
	public List getSpsParameterList() {
		return spsParameterList;
	}
	/**
	 * @param spsParameterList The spsParameterList to set.
	 */
	public void setSpsParameterList(List spsParameterList) {
		this.spsParameterList = spsParameterList;
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
	 * @return Returns the accumulatorSPSID.
	 */
	public String getAccumulatorSPSID() {
		return accumulatorSPSID;
	}
	/**
	 * @param accumulatorSPSID The accumulatorSPSID to set.
	 */
	public void setAccumulatorSPSID(String accumulatorSPSID) {
		this.accumulatorSPSID = accumulatorSPSID;
	}
	/**
	 * @return Returns the accummulatorSPSDesc.
	 */
	public String getAccummulatorSPSDesc() {
		return accummulatorSPSDesc;
	}
	/**
	 * @param accummulatorSPSDesc The accummulatorSPSDesc to set.
	 */
	public void setAccummulatorSPSDesc(String accummulatorSPSDesc) {
		this.accummulatorSPSDesc = accummulatorSPSDesc;
	}
	/**
	 * @return Returns the accummulatorSPSIDList.
	 */
	public List getAccummulatorSPSIDList() {
		return accummulatorSPSIDList;
	}
	/**
	 * @param accummulatorSPSIDList The accummulatorSPSIDList to set.
	 */
	public void setAccummulatorSPSIDList(List accummulatorSPSIDList) {
		this.accummulatorSPSIDList = accummulatorSPSIDList;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getHsd7Value() {
		return hsd7Value;
	}
	public void setHsd7Value(String hsd7Value) {
		this.hsd7Value = hsd7Value;
	}
	public String getHsd8Value() {
		return hsd8Value;
	}
	public void setHsd8Value(String hsd8Value) {
		this.hsd8Value = hsd8Value;
	}
}