package com.wellpoint.ets.ebx.referencedata.web;

import java.io.Serializable;

public class ReferanceDataPopUpVO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7424279575606866940L;

	String value;
	
	String desc;



	public ReferanceDataPopUpVO(String value, String desc) {
		super();
		this.value = value;
		this.desc = desc;
	}


	
	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}


}
