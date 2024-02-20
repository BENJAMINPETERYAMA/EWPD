/*
 * Created on Jul 20, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.wellpoint.wpd.common.referencemapping.response;

import com.wellpoint.wpd.common.framework.response.WPDResponse;

/**
 * @author U14659
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveReferenceMappingResponse extends WPDResponse{

	private String referenceCriteria;
	private String description;
	private String typeCriteria;
	private String termCriteria;
	private String qualifierCriteria;
	private String pvaCriteria;
	private String dataTypeCriteria;
	
	
	
	/**
	 * @return Returns the referenceCriteria.
	 */
	public String getReferenceCriteria() {
		return referenceCriteria;
	}
	/**
	 * @param referenceCriteria The referenceCriteria to set.
	 */
	public void setReferenceCriteria(String referenceCriteria) {
		this.referenceCriteria = referenceCriteria;
	}
	/**
	 * @return Returns the dataTypeCriteria.
	 */
	public String getDataTypeCriteria() {
		return dataTypeCriteria;
	}
	/**
	 * @param dataTypeCriteria The dataTypeCriteria to set.
	 */
	public void setDataTypeCriteria(String dataTypeCriteria) {
		this.dataTypeCriteria = dataTypeCriteria;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return Returns the pvaCriteria.
	 */
	public String getPvaCriteria() {
		return pvaCriteria;
	}
	/**
	 * @param pvaCriteria The pvaCriteria to set.
	 */
	public void setPvaCriteria(String pvaCriteria) {
		this.pvaCriteria = pvaCriteria;
	}
	/**
	 * @return Returns the qualifierCriteria.
	 */
	public String getQualifierCriteria() {
		return qualifierCriteria;
	}
	/**
	 * @param qualifierCriteria The qualifierCriteria to set.
	 */
	public void setQualifierCriteria(String qualifierCriteria) {
		this.qualifierCriteria = qualifierCriteria;
	}
	/**
	 * @return Returns the termCriteria.
	 */
	public String getTermCriteria() {
		return termCriteria;
	}
	/**
	 * @param termCriteria The termCriteria to set.
	 */
	public void setTermCriteria(String termCriteria) {
		this.termCriteria = termCriteria;
	}
	/**
	 * @return Returns the typeCriteria.
	 */
	public String getTypeCriteria() {
		return typeCriteria;
	}
	/**
	 * @param typeCriteria The typeCriteria to set.
	 */
	public void setTypeCriteria(String typeCriteria) {
		this.typeCriteria = typeCriteria;
	}
}
