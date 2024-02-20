/*
 * Created on Aug 03, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class GetBenefitTierDefinitionResponse extends WPDResponse{
    
   private String tier;
    

/**
 * @return Returns the tier.
 */
public String getTier() {
    return tier;
}
/**
 * @param tier The tier to set.
 */
public void setTier(String tier) {
    this.tier = tier;
}
}