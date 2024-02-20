/*
 * Created on Aug 03, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.product.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class UpdateProductBenefitGeneralInformationResponse extends WPDResponse{
    
   private boolean resultOfUpdate;
    

/**
 * @return Returns the resultOfUpdate.
 */
public boolean isResultOfUpdate() {
    return resultOfUpdate;
}
/**
 * @param resultOfUpdate The resultOfUpdate to set.
 */
public void setResultOfUpdate(boolean resultOfUpdate) {
    this.resultOfUpdate = resultOfUpdate;
}
}