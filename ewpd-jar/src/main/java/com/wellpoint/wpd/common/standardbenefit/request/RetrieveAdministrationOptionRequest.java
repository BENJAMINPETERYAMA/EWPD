/*
 * RetrieveAdministrationOptionRequest.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.standardbenefit.request;

import java.util.List;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class RetrieveAdministrationOptionRequest extends WPDRequest {
	
	private int adminLevelOptionAssnSystemId;
	private int mainObjKey;
	private int mainObjVersion;
	private String mainObjIdentifier;
	private String itemCode;
	private int standardBenefitParentKey;
	
	private String standardBenefitStatus;
	
	private List businessDomains;
	/**
	 * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
		// TODO Auto-generated method stub

	}

	/**
	 * Returns the adminLevelOptionAssnSystemId
	 * @return int adminLevelOptionAssnSystemId.
	 */
	public int getAdminLevelOptionAssnSystemId() {
		return adminLevelOptionAssnSystemId;
	}
	/**
	 * Sets the adminLevelOptionAssnSystemId
	 * @param adminLevelOptionAssnSystemId.
	 */
	public void setAdminLevelOptionAssnSystemId(int adminLevelOptionAssnSystemId) {
		this.adminLevelOptionAssnSystemId = adminLevelOptionAssnSystemId;
	}
	/**
	 * Returns the mainObjKey
	 * @return int mainObjKey.
	 */
	public int getMainObjKey() {
		return mainObjKey;
	}
	/**
	 * Sets the mainObjKey
	 * @param mainObjKey.
	 */
	public void setMainObjKey(int mainObjKey) {
		this.mainObjKey = mainObjKey;
	}
	/**
	 * Returns the mainObjVersion
	 * @return int mainObjVersion.
	 */
	public int getMainObjVersion() {
		return mainObjVersion;
	}
	/**
	 * Sets the mainObjVersion
	 * @param mainObjVersion.
	 */
	public void setMainObjVersion(int mainObjVersion) {
		this.mainObjVersion = mainObjVersion;
	}
	/**
	 * Returns the mainObjIdentifier
	 * @return String mainObjIdentifier.
	 */
	public String getMainObjIdentifier() {
		return mainObjIdentifier;
	}
	/**
	 * Sets the mainObjIdentifier
	 * @param mainObjIdentifier.
	 */
	public void setMainObjIdentifier(String mainObjIdentifier) {
		this.mainObjIdentifier = mainObjIdentifier;
	}
	
	/**
	 * Returns the itemCode
	 * @return String itemCode.
	 */
	public String getItemCode() {
		return itemCode;
	}
	/**
	 * Sets the itemCode
	 * @param itemCode.
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	/**
	 * Returns the businessDomains
	 * @return List businessDomains.
	 */
	public List getBusinessDomains() {
		return businessDomains;
	}
	/**
	 * Sets the businessDomains
	 * @param businessDomains.
	 */
	public void setBusinessDomains(List businessDomains) {
		this.businessDomains = businessDomains;
	}
	/**
	 * Returns the standardBenefitParentKey
	 * @return int standardBenefitParentKey.
	 */
	public int getStandardBenefitParentKey() {
		return standardBenefitParentKey;
	}
	/**
	 * Sets the standardBenefitParentKey
	 * @param standardBenefitParentKey.
	 */
	public void setStandardBenefitParentKey(int standardBenefitParentKey) {
		this.standardBenefitParentKey = standardBenefitParentKey;
	}
	/**
	 * Returns the standardBenefitStatus
	 * @return String standardBenefitStatus.
	 */
	public String getStandardBenefitStatus() {
		return standardBenefitStatus;
	}
	/**
	 * Sets the standardBenefitStatus
	 * @param standardBenefitStatus.
	 */
	public void setStandardBenefitStatus(String standardBenefitStatus) {
		this.standardBenefitStatus = standardBenefitStatus;
	}
}
