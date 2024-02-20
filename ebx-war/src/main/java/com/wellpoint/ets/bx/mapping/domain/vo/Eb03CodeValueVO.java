package com.wellpoint.ets.bx.mapping.domain.vo;

public class Eb03CodeValueVO implements Comparable{

	private String value;
	
	private String description; 

	private String eb03Default;
	
	
	
	
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
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return Returns the hippaCodeValueSysId.
	 */
	
	
	public int compareTo(Object obj1) {
		int compare = 0;
		if(obj1 instanceof Eb03CodeValueVO){
			Eb03CodeValueVO hippaCodeObj1 = (Eb03CodeValueVO) obj1;
			if (null != this.getValue() && null != hippaCodeObj1 && null != hippaCodeObj1.getValue()) {
				compare = this.getValue().compareToIgnoreCase(hippaCodeObj1.getValue());
			}
		}
		return compare;
	}
	public String getEb03Default() {
		return eb03Default;
	}
	public void setEb03Default(String eb03Default) {
		this.eb03Default = eb03Default;
	}
	
}
