/*
 * Created on Jul 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.item.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.item.vo.ItemVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class ItemSoftDeleteRequest extends WPDRequest {

    private ItemVO itemVO;
    
    /**
     * @return Returns the itemVO.
     * @return ItemVO itemVO
     */
    public ItemVO getItemVO() {
        return itemVO;
    }
    /**
     * Sets the itemVO
     * @param itemVO
     */
    public void setItemVO(ItemVO itemVO) {
        this.itemVO = itemVO;
    }
 
    public void validate() throws ValidationException {
        // Validate all the required input fields in the VO
         if (this.itemVO.getPrimaryCode() == null || "".equals(this.itemVO.getPrimaryCode())) {
         	
         	throw new ValidationException("PrimaryCode is Missing", null, null);
         }
         if (new Integer(this.itemVO.getCatalogId()).intValue() ==0) {
         	
         	throw new ValidationException("Catalog Id is Missing", null, null);
         }
         if (this.itemVO.getStatus() == null || "".equals(this.itemVO.getStatus())) {
         	
         	throw new ValidationException("Status is Missing", null, null);
         }
         
    }
 
}
         
         

