/*
 * Created on Jun 15, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.item.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.item.vo.ItemVO;

/**
 * @author u15434
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveItemRequest extends WPDRequest{
   
    private ItemVO itemVO;
    private String srdaFlag;
    private String hcsCode;
    
    /*
     * validate
     */
    public void validate() throws ValidationException {
    }
    
    /**
     * @return Returns the itemVO.
     */
    public ItemVO getItemVO() {
        return itemVO;
    }
    /**
     * @param itemVO The itemVO to set.
     */
    public void setItemVO(ItemVO itemVO) {
        this.itemVO = itemVO;
    }

	/**
	 * @return the srdaFlag
	 */
	public String getSrdaFlag() {
		return srdaFlag;
	}

	/**
	 * @param srdaFlag the srdaFlag to set
	 */
	public void setSrdaFlag(String srdaFlag) {
		this.srdaFlag = srdaFlag;
	}

	/**
	 * @return the hcsCode
	 */
	public String getHcsCode() {
		return hcsCode;
	}

	/**
	 * @param hcsCode the hcsCode to set
	 */
	public void setHcsCode(String hcsCode) {
		this.hcsCode = hcsCode;
	}
    
}
