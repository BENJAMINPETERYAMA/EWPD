/*
 * Created on May 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.catalog.vo;

/**
 * @author US Technology Resources - www.ustri.com <br />
 * @version $Id: $
 */
public class CatalogLocateCriteriaVO {
    private String catalogName;

    private String catalogDescription;
    
    private String srdaFlag;

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
