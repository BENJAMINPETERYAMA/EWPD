/*
 *	BenefitMandatesBackingBean.java
 *	
 *	Revision History :
 * 	
 * 	Date               Name              Description
 * 	-----------------------------------------------------
 *  Feb 19,2007		US Technology	  Initial Version
 * 
 */

package com.wellpoint.wpd.web.benefitcomponent;

import java.util.ArrayList;
import java.util.List;

import com.wellpoint.wpd.common.benefitcomponent.request.LocateNonAdjBenefitMandateRequest;
import com.wellpoint.wpd.common.benefitcomponent.response.LocateNonAdjBenefitMandateResponse;
import com.wellpoint.wpd.web.framework.WPDBackingBean;
import com.wellpoint.wpd.web.framework.service.ServiceManager;
import com.wellpoint.wpd.web.util.WebConstants;
/**
 * Bean to create Benefit Mandate
 * This bean will bind with the jsp pages.
 * BenefitMandatesBackingBean contains the getters and setters of the 
 * variables and respective functions
 * 
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class BenefitMandatesBackingBean extends WPDBackingBean{
	 private String effectiveDate = null;
	 private String expiryDate = null;
	 private String mandate ="";
	 private String optionalIdentifier = null;
	 
	List associatedbenefitMandatesList = null;
	
	

	public BenefitMandatesBackingBean() {		
		String mode = getBenefitComponentSessionObject().getBenefitComponentMode();
		if(mode.equals(WebConstants.BENEFIT_COMPONENT_View)){
			String name = getBenefitComponentSessionObject().getBenefitComponentName();
			this.setBreadCrumbText(WebConstants.BENEFIT_COMPONENT_BREADCRUMB + " (" + name + ") >> View");
		}
		
	}
	
	public String loadNonAdjBenefitMandate(){
		
	return WebConstants.BENEFIT_COMPONENT_NON_ADJ_BNFT_MNDT;	
	}
	/**
	 * @return Returns the associatedbenefitMandatesList.
	 * setting the BenefitSystemId to LocateNonAdjBenefitMandateRequest
	 * and returning the associatedbenefitMandatesList.
	 */
	public List getAssociatedbenefitMandatesList() {
		associatedbenefitMandatesList = new ArrayList(1);
		LocateNonAdjBenefitMandateRequest locateNonAdjBenefitMandateRequest	=(LocateNonAdjBenefitMandateRequest)this.getServiceRequest(ServiceManager.LOCATE_NON_ADJ_BENEFIT_MANDATE);
		locateNonAdjBenefitMandateRequest.setBenefitSystemId(Integer.parseInt(getSession().getAttribute(WebConstants.BENEFIT_COMPONENT_ID).toString()));
		LocateNonAdjBenefitMandateResponse locateNonAdjBenefitMandateResponse =
			(LocateNonAdjBenefitMandateResponse)executeService(locateNonAdjBenefitMandateRequest);		
		return locateNonAdjBenefitMandateResponse.getBenefitMandateList();
		
	}
	
	/**
	 * 
	 * @return BenefitComponentSessionObject
	 */
	protected BenefitComponentSessionObject getBenefitComponentSessionObject(){
		BenefitComponentSessionObject benefitComponentSessionObject = (BenefitComponentSessionObject)getSession().getAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY);
		
		if(benefitComponentSessionObject == null) {
			benefitComponentSessionObject = new BenefitComponentSessionObject();
			getSession().setAttribute(WebConstants.BENEFIT_COMPONENT_SESSION_KEY,benefitComponentSessionObject);
		}
		return benefitComponentSessionObject;
	}
	
	
	
	
	/**
	 * @param associatedbenefitMandatesList
	 *            The associatedbenefitMandatesList to set.
	 */
	public void setAssociatedbenefitMandatesList(
			List associatedbenefitMandatesList) {
		this.associatedbenefitMandatesList = associatedbenefitMandatesList;
	}

	public String loadNonAdjBenefitMandateView(){
		return WebConstants.BNFT_CMPNT_NON_ADJ_BNFT_MNDT_VIEW ;
	}
	
	/**
	 * @return Returns the effectiveDate.
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/**
	 * @param effectiveDate The effectiveDate to set.
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	/**
	 * @return Returns the expiryDate.
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate The expiryDate to set.
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * @return Returns the mandate.
	 */
	public String getMandate() {
		return mandate;
	}
	/**
	 * @param mandate The mandate to set.
	 */
	public void setMandate(String mandate) {
		this.mandate = mandate;
	}
	/**
	 * @return Returns the optionalIdentifier.
	 */
	public String getOptionalIdentifier() {
		return optionalIdentifier;
	}
	/**
	 * @param optionalIdentifier The optionalIdentifier to set.
	 */
	public void setOptionalIdentifier(String optionalIdentifier) {
		this.optionalIdentifier = optionalIdentifier;
	}
}