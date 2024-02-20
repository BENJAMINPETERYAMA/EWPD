package com.wellpoint.ewpd.rest.model;

import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang3.StringUtils;


/**
 * @author AF37766
 *
 */
public class Domain {
	
	
	List<String> lineOfBusiness = new ArrayList<>();
	private String businessEntity; 
    private String marketSegment;
    private String businessUnit;
	/**
	 * @return the lineOfBusiness
	 */
	public String getLineOfBusiness() {
		return StringUtils.join(lineOfBusiness, ",");
	}
	/**
	 * @param lineOfBusiness the lineOfBusiness to set
	 */
	public void setLineOfBusiness(List<String> lineOfBusiness) {
		this.lineOfBusiness = lineOfBusiness;
	}
	/**
	 * @return the businessEntity
	 */
	public String getBusinessEntity() {
		return businessEntity;
	}
	/**
	 * @param businessEntity the businessEntity to set
	 */
	public void setBusinessEntity(String businessEntity) {
		this.businessEntity = businessEntity.trim().toUpperCase();
	}
	/**
	 * @return the marketSegment
	 */
	public String getMarketSegment() {
		return marketSegment;
	}
	/**
	 * @param marketSegment the marketSegment to set
	 */
	public void setMarketSegment(String marketSegment) {
		this.marketSegment = marketSegment.trim().toUpperCase();
	}
	/**
	 * @return the businessUnit
	 */
	public String getBusinessUnit() {
		return businessUnit;
	}
	/**
	 * @param businessUnit the businessUnit to set
	 */
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit.trim().toUpperCase();
	}
    
    
}
