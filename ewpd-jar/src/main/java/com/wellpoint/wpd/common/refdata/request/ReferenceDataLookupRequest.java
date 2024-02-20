
package com.wellpoint.wpd.common.refdata.request;

import com.wellpoint.wpd.common.framework.exception.ValidationException;
import com.wellpoint.wpd.common.framework.request.WPDRequest;
import com.wellpoint.wpd.common.subcatalog.vo.SubCatalogVO;

/**
 * @author u13832
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReferenceDataLookupRequest extends WPDRequest{
	private SubCatalogVO subCatalogVO;
	
	private int action;
	
	private int entityId;
	
	private String entityType;
	
	private String entityName;
	
	private int selectaction;
	
	private String searchString;
	
	private String headerRuleId;
	 public void validate() throws ValidationException{}
	 
	/**
	 * @return Returns the subCatalogVO.
	 */
	public SubCatalogVO getSubCatalogVO() {
		return subCatalogVO;
	}
	/**
	 * @param subCatalogVO The subCatalogVO to set.
	 */
	public void setSubCatalogVO(SubCatalogVO subCatalogVO) {
		this.subCatalogVO = subCatalogVO;
	}
	
    /**
     * @return Returns the action.
     * @return int action
     */
    public int getAction() {
        return action;
    }
    /**
     * Sets the action
     * @param action
     */
    public void setAction(int action) {
        this.action = action;
    }
    /**
     * @return Returns the entityId.
     * @return int entityId
     */
    public int getEntityId() {
        return entityId;
    }
    /**
     * Sets the entityId
     * @param entityId
     */
    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
    /**
     * @return Returns the entityType.
     * @return String entityType
     */
    public String getEntityType() {
        return entityType;
    }
    /**
     * Sets the entityType
     * @param entityType
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    
    /**
     * @return Returns the entityName.
     * @return String entityName
     */
    public String getEntityName() {
        return entityName;
    }
    /**
     * Sets the entityName
     * @param entityName
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
	/**
	 * @return Returns the selectaction.
	 */
	public int getSelectaction() {
		return selectaction;
	}
	/**
	 * @param selectaction The selectaction to set.
	 */
	public void setSelectaction(int selectaction) {
		this.selectaction = selectaction;
	}
	/**
	 * @return Returns the headerRuleId.
	 */
	public String getHeaderRuleId() {
		return headerRuleId;
	}
	/**
	 * @param headerRuleId The headerRuleId to set.
	 */
	public void setHeaderRuleId(String headerRuleId) {
		this.headerRuleId = headerRuleId;
	}
	/**
	 * @return Returns the searchString.
	 */
	public String getSearchString() {
		return searchString;
	}
	/**
	 * @param searchString The searchString to set.
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
}