/*
 * Created on Aug 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.search.result;


/**
 * @author U15427
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductStructureViewObject {
	private int productStructureId;
     private String productStructureName;
     private int parent_Id;
     private int version;
     private String state;
     private String status;
    
     private String structureType;
     private String mandateType;
     
     private String lineOfBusinessCode;

 	private String businessEntityCode;

 	private String businessGroupCode;
    
	
	/**
	 * @return Returns the businessEntityCode.
	 */
	public String getBusinessEntityCode() {
		return businessEntityCode;
	}
	/**
	 * @param businessEntityCode The businessEntityCode to set.
	 */
	public void setBusinessEntityCode(String businessEntityCode) {
		this.businessEntityCode = businessEntityCode;
	}
	/**
	 * @return Returns the businessGroupCode.
	 */
	public String getBusinessGroupCode() {
		return businessGroupCode;
	}
	/**
	 * @param businessGroupCode The businessGroupCode to set.
	 */
	public void setBusinessGroupCode(String businessGroupCode) {
		this.businessGroupCode = businessGroupCode;
	}
	/**
	 * @return Returns the lineOfBusinessCode.
	 */
	public String getLineOfBusinessCode() {
		return lineOfBusinessCode;
	}
	/**
	 * @param lineOfBusinessCode The lineOfBusinessCode to set.
	 */
	public void setLineOfBusinessCode(String lineOfBusinessCode) {
		this.lineOfBusinessCode = lineOfBusinessCode;
	}
	/**
	 * @return Returns the mandateType.
	 */
	public String getMandateType() {
		return mandateType;
	}
	/**
	 * @param mandateType The mandateType to set.
	 */
	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}
	/**
	 * @return Returns the parent_Id.
	 */
	public int getParent_Id() {
		return parent_Id;
	}
	/**
	 * @param parent_Id The parent_Id to set.
	 */
	public void setParent_Id(int parent_Id) {
		this.parent_Id = parent_Id;
	}
	/**
	 * @return Returns the productStructureId.
	 */
	public int getProductStructureId() {
		return productStructureId;
	}
	/**
	 * @param productStructureId The productStructureId to set.
	 */
	public void setProductStructureId(int productStructureId) {
		this.productStructureId = productStructureId;
	}
	/**
	 * @return Returns the productStructureName.
	 */
	public String getProductStructureName() {
		return productStructureName;
	}
	/**
	 * @param productStructureName The productStructureName to set.
	 */
	public void setProductStructureName(String productStructureName) {
		this.productStructureName = productStructureName;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the structureType.
	 */
	public String getStructureType() {
		return structureType;
	}
	/**
	 * @param structureType The structureType to set.
	 */
	public void setStructureType(String structureType) {
		this.structureType = structureType;
	}
	/**
	 * @return Returns the version.
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version The version to set.
	 */
	public void setVersion(int version) {
		this.version = version;
	}
}
