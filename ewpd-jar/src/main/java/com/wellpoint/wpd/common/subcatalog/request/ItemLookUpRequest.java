/*
 * Created on Jul 9, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.subcatalog.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.subcatalog.vo.ItemLocateCriteriaVO;

public class ItemLookUpRequest extends WPDRequest{
   ItemLocateCriteriaVO criteriaVO;
    
    public void validate() throws ValidationException{}

    
    
/**
 * @return Returns the criteriaVO.
 * @return ItemLocateCriteriaVO criteriaVO
 */
public ItemLocateCriteriaVO getCriteriaVO() {
    return criteriaVO;
}
/**
 * Sets the criteriaVO
 * @param criteriaVO
 */
public void setCriteriaVO(ItemLocateCriteriaVO criteriaVO) {
    this.criteriaVO = criteriaVO;
}
}
