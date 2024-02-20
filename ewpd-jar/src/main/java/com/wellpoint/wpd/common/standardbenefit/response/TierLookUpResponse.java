/*
 * Created on July 31, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.standardbenefit.response;

import java.util.List;

import com.wellpoint.wpd.common.framework.response.WPDResponse;


/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class TierLookUpResponse extends WPDResponse{
    private List tierList;

    /**
     * @return Returns the tierList.
     */
    public List getTierList() {
        return tierList;
    }
    /**
     * @param tierList The tierList to set.
     */
    public void setTierList(List tierList) {
        this.tierList = tierList;
    }
}
