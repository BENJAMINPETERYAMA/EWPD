/*
 * Created on Nov 5, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.app.config;

import java.util.List;
import java.util.StringTokenizer;

import com.wellpoint.wpd.business.util.BusinessUtil;
import com.wellpoint.wpd.common.benefitcomponent.bo.BenefitComponentBO;
import com.wellpoint.wpd.common.framework.exception.SevereException;

/**
 *This class having methods to create instance of classes that provide the propertyvalues
 *The methods in the class wiill help to create instnces using the input keys.
 */
public class ApplicationConfigManager {
	/*
	 * This method will create a an instance of AppPropertiesFromDataStore according to 
	 * the String that we are passing tomethod createInstance.
	 * This will call anothermethod getProperties and pass the key values to get the configartion properties
	 * if legacyNoteCopyProp value is '0'
	 * it will return true else return false.
	 * 
	 */
	
	public static boolean isContractLegacyNotesCopyAllowed() throws SevereException {
		ApplicationProperties properties = 
			ApplicationProperties.createInstance(ApplicationProperties.PROPERTIES_DB);
		String legacyNoteCopyProp = properties.getProperties("copylegacynote");
		if(legacyNoteCopyProp != null && legacyNoteCopyProp.equals("0")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method will return true if the specified line of business is in the property
	 * @param benefitComponentBO
	 * @return
	 * @throws SevereException
	 */
	public static boolean isDuplicateBenefitCheckWaived(BenefitComponentBO benefitComponentBO) throws SevereException {
		
		ApplicationProperties properties = ApplicationProperties
		.createInstance(ApplicationProperties.PROPERTIES_DB);
		List businessDomainList = benefitComponentBO.getBusinessDomainList();
		List lobList = BusinessUtil.getLobList(businessDomainList);
		String waivedLOB = properties
		.getProperties("BYPASS_DUPLICATE_CHECK_LOB");
		if (null != waivedLOB && waivedLOB != "") {
			StringTokenizer st = new StringTokenizer(waivedLOB, "~");
			
			if (null != lobList && !lobList.isEmpty()) {
				while (st.hasMoreTokens()) {
					String lineOfBusiness = st.nextToken();
					if (lobList.contains(lineOfBusiness)) {
						return true;
					}
				}
			}
		}
		return false;
	}	
}
