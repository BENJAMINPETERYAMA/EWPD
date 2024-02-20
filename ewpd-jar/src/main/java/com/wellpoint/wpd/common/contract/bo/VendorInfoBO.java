


/*
 * QuestionBO.java
 * 
 * © 2006 WellPoint, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wellpoint Inc. 
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of Wellpoint Inc. 
 */
package com.wellpoint.wpd.common.contract.bo;

import java.util.List;
import com.wellpoint.wpd.common.bo.BusinessObject;

/**
 * Business object for questions
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class VendorInfoBO   {
		
    
    public String getBenefitCompName() {
		return benefitCompName;
	}
	public void setBenefitCompName(String benefitCompName) {
		this.benefitCompName = benefitCompName;
	}
	public String getBenefitName() {
		return benefitName;
	}
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
	public String getVendorRef() {
		return vendorRef;
	}
	public void setVendorRef(String vendorRef) {
		this.vendorRef = vendorRef;
	}
	public String getVendorRefDesc() {
		return vendorRefDesc;
	}
	public void setVendorRefDesc(String vendorRefDesc) {
		this.vendorRefDesc = vendorRefDesc;
	}
	private String benefitCompName;
    private String benefitName;
    private String vendorRef;
    private String vendorRefDesc;
    private List lstNotAnswerd ;
    
	public List getLstNotAnswerd() {
		return lstNotAnswerd;
	}
	public void setLstNotAnswerd(List lstNotAnswerd) {
		this.lstNotAnswerd = lstNotAnswerd;
	}
	private String carvedoutDate;

	public String getCarvedoutDate() {
		return carvedoutDate;
	}
	public void setCarvedoutDate(String carvedoutDate) {
		this.carvedoutDate = carvedoutDate;
	}

  
	
}
