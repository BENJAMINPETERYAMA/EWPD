package com.wellpoint.ets.bx.mapping.domain.entity;

public class HippaCodeValue implements Comparable{

	private String value;
	
	private String description; 

	
	private Long hippaCodeValueSysId;
	
	private Long seq_num;
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
	public Long getHippaCodeValueSysId() {
		return hippaCodeValueSysId;
	}
	/**
	 * @param hippaCodeValueSysId The hippaCodeValueSysId to set.
	 */
	public void setHippaCodeValueSysId(Long hippaCodeValueSysId) {
		this.hippaCodeValueSysId = hippaCodeValueSysId;
	}
	/**
	 * @return Returns the seq_num.
	 */
	public Long getSeq_num() {
		return seq_num;
	}
	/**
	 * @param seq_num The seq_num to set.
	 */
	public void setSeq_num(Long seq_num) {
		this.seq_num = seq_num;
	}
	
	public int compareTo(Object obj1) {
		int compare = 0;
		if(obj1 instanceof HippaCodeValue){
			HippaCodeValue hippaCodeObj1 = (HippaCodeValue) obj1;
			if (null != this.getValue() && null != hippaCodeObj1 && null != hippaCodeObj1.getValue()) {
				compare = this.getValue().compareToIgnoreCase(hippaCodeObj1.getValue());
			}
		}
		return compare;
	}
	
}
