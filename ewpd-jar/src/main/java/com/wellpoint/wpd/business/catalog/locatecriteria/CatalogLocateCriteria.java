/*
 * Created on May 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.business.catalog.locatecriteria;

import com.wellpoint.wpd.common.bo.LocateCriteria;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CatalogLocateCriteria extends LocateCriteria {
    private String catalogName;

    private String catalogDescription;
    
    private String srdaFlag;

    /**
     * Returns the catalogDescription
     * @return String catalogDescription.
     */

    public String getCatalogDescription() {
        return catalogDescription;
    }
    /**
     * Sets the catalogDescription
     * @param catalogDescription.
     */

    public void setCatalogDescription(String catalogDescription) {
        this.catalogDescription = catalogDescription;
    }
    /**
     * Returns the catalogName
     * @return String catalogName.
     */

    public String getCatalogName() {
        return catalogName;
    }
    /**
     * Sets the catalogName
     * @param catalogName.
     */

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
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
    
    
}