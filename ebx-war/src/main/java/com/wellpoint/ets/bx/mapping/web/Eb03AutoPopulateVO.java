package com.wellpoint.ets.bx.mapping.web;

import java.io.Serializable;

public class Eb03AutoPopulateVO implements Serializable{

	private static final long serialVersionUID = 4632762458960867978L;

	String id;
	
	String label;
	
	String value;
	
	String eb03DefaultValue;

	
	public Eb03AutoPopulateVO(String id, String label, String value) {
		super();
		this.id = id;
		this.label = label;
		this.value = value;
	}

	
	
	
	public Eb03AutoPopulateVO(String id, String label, String value, String eb03DefaultValue) {
		super();
		this.id = id;
		this.label = label;
		this.value = value;
		this.eb03DefaultValue = eb03DefaultValue;
	}




	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getEb03DefaultValue() {
		return eb03DefaultValue;
	}

	public void setEb03DefaultValue(String eb03DefaultValue) {
		this.eb03DefaultValue = eb03DefaultValue;
	}
	
	
}
