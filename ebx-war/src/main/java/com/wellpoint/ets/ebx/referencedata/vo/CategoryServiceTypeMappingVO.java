/*
 * <CategoryServiceTypeMappingVO.java>
 *
 * © 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */
package com.wellpoint.ets.ebx.referencedata.vo;

import java.util.List;

import com.wellpoint.ets.bx.mapping.domain.entity.Mapping;

/**
 * @author UST Global - www.ust-global.com <br />
 * @version $Id: $
 */
public class CategoryServiceTypeMappingVO {

	/**
	 * Comment for <code>categoryCode</code>
	 */
	private String categoryCode = null;
	/**
	 * Comment for <code>system</code>
	 */
	private String system = null;
	/**
	 * Comment for <code>serviceType</code>
	 */
	private String serviceType = null;
	/**
	 * Comment for <code>serviceTypeDesc</code>
	 */
	private String serviceTypeDesc = null;
	/**
	 * Comment for <code>description</code>
	 */
	private String description = null;
	/**
	 * Comment for <code>mapping</code>
	 */
	private Mapping mapping = null;
	/**
	 * Comment for <code>categoryDesc</code>
	 */
	private String categoryDesc = null;

	/**
	 * @return
	 */
	public String getCategoryDesc() {
		return categoryDesc;
	}

	/**
	 * @param categoryDesc
	 */
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	/**
	 * @return
	 */
	public String getServiceTypeDesc() {
		return serviceTypeDesc;
	}

	/**
	 * @param serviceTypeDesc
	 */
	public void setServiceTypeDesc(String serviceTypeDesc) {
		this.serviceTypeDesc = serviceTypeDesc;
	}

	/**
	 * @return
	 */
	public Mapping getMapping() {
		return mapping;
	}

	/**
	 * @param mapping
	 */
	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}

	/**
	 * @return
	 */
	public List getServiceTypelist() {
		return serviceTypelist;
	}

	/**
	 * @param serviceTypelist
	 */
	public void setServiceTypelist(List serviceTypelist) {
		this.serviceTypelist = serviceTypelist;
	}

	private List serviceTypelist = null;

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	private String newMappingflag = null;

	/**
	 * @return
	 */
	public String getNewMappingflag() {
		return newMappingflag;
	}

	/**
	 * @param newMappingflag
	 */
	public void setNewMappingflag(String newMappingflag) {
		this.newMappingflag = newMappingflag;
	}

	/**
	 * @return
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/**
	 * @param categoryCode
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * @return
	 */
	public String getSystem() {
		return system;
	}

	/**
	 * @param system
	 */
	public void setSystem(String system) {
		this.system = system;
	}

	/**
	 * @return
	 */
	public String getServiceType() {
		return serviceType;
	}

	/**
	 * @param serviceType
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
}