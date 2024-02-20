/*
 * <EB03Association.java>
 *
 * � 2010 - 2011 WellPoint, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of WellPoint Inc.
 * ("Confidential Information").  You shall not disclose or use Confidential Information
 * without the express written agreement of WellPoint Inc.
 */

package com.wellpoint.ets.bx.mapping.domain.entity;

import java.util.Date;

/**
 * @author u23708
 *
 */
public class EB03DefaultMapping implements Comparable<Object> {

	
	private Integer eb03SysId;
	private String eb03;
	private String eb03Default;
	
	private Date createdDate;
	private String createdBy;
	
	
	

	public String getEb03() {
		return eb03;
	}

	public void setEb03(String eb03) {
		this.eb03 = eb03;
	}

	

	
	
	
	public String getEb03Default() {
		return eb03Default;
	}

	public void setEb03Default(String eb03Default) {
		this.eb03Default = eb03Default;
	}

	public Integer getEb03SysId() {
		return eb03SysId;
	}

	public void setEb03SysId(Integer eb03SysId) {
		this.eb03SysId = eb03SysId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public EB03DefaultMapping(String eb03, String eb03Default) {
		super();
		this.eb03 = eb03;
		this.eb03Default = eb03Default;
	}

	public EB03DefaultMapping() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
