/*
 * Created on May 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.item.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.item.vo.ItemLocateCriteriaVO;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class SearchItemRequest extends WPDRequest{
    
    private ItemLocateCriteriaVO criteriaVO;

    /**
     * Returns the criteriaVO
     * @return ItemLocateCriteriaVO criteriaVO.
     */

    public ItemLocateCriteriaVO getCriteriaVO() {
        return criteriaVO;
    }
    /**
     * Sets the criteriaVO
     * @param criteriaVO.
     */

    public void setCriteriaVO(ItemLocateCriteriaVO criteriaVO) {
        this.criteriaVO = criteriaVO;
    }
    /* (non-Javadoc)
     * @see com.wellpoint.wpd.common.framework.request.WPDRequest#validate()
     */
    public void validate() throws ValidationException {
        // TODO Auto-generated method stub
        
    }
}
