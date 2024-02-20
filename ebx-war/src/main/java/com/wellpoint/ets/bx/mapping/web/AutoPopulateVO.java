package com.wellpoint.ets.bx.mapping.web;

import java.io.Serializable;

public class AutoPopulateVO implements Serializable{

	private static final long serialVersionUID = 4632762458960867978L;

	String id;
	
	String label;
	
	String value;

	
	public AutoPopulateVO(String id, String label, String value) {
		super();
		this.id = id;
		this.label = label;
		this.value = value;
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
	
	
}
