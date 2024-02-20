package com.wellpoint.ets.bx.mapping.domain.entity;

public class ExtendedMessageMapping implements Comparable{
	
	private Long extendedMsgValueSysId;
	
	private String segmentName;
	private String value;
	private String extndMsg1;
	private String extndMsg2;
	private String extndMsg3;	
	private String changeInd;
	private Long seq_num;
	private String networkInd;
	
	public Long getExtendedMsgValueSysId() {
		return extendedMsgValueSysId;
	}

	public void setExtendedMsgValueSysId(Long extendedMsgValueSysId) {
		this.extendedMsgValueSysId = extendedMsgValueSysId;
	}
	
	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getExtndMsg1() {
		return extndMsg1;
	}

	public void setExtndMsg1(String extndMsg1) {
		this.extndMsg1 = extndMsg1;
	}

	public String getExtndMsg2() {
		return extndMsg2;
	}

	public void setExtndMsg2(String extndMsg2) {
		this.extndMsg2 = extndMsg2;
	}

	public String getExtndMsg3() {
		return extndMsg3;
	}

	public void setExtndMsg3(String extndMsg3) {
		this.extndMsg3 = extndMsg3;
	}

	public String getChangeInd() {
		return changeInd;
	}

	public void setChangeInd(String changeInd) {
		this.changeInd = changeInd;
	}

	public Long getSeq_num() {
		return seq_num;
	}

	public void setSeq_num(Long seq_num) {
		this.seq_num = seq_num;
	}
	
	public String getNetworkInd() {
		return networkInd;
	}

	public void setNetworkInd(String networkInd) {
		this.networkInd = networkInd;
	}
	
	public int compareTo(Object obj1) {
		int compare = 0;
		if(obj1 instanceof ExtendedMessageMapping){
			ExtendedMessageMapping extendedMessageMappingObj = (ExtendedMessageMapping) obj1;
			if (null != this.getValue() && null != extendedMessageMappingObj && null != extendedMessageMappingObj.getValue()) {
				compare = this.getValue().compareToIgnoreCase(extendedMessageMappingObj.getValue());
			}
		}
		return compare;
	}
	//Do not remove any variable from toSting() method.
	public String toString() { 
	    return "{ extendedMsgValueSysId: " + extendedMsgValueSysId + ", segmentName: '" + segmentName + "', value: '" + value +
	    		"', extndMsg1: \"" + extndMsg1 + "\", extndMsg2: \"" + extndMsg2 + "\", extndMsg3: \"" + extndMsg3 +
	    		"\", changeInd: '" + changeInd + "', seq_num: " + seq_num + ", networkInd: '" + networkInd +"' }" ;
	} 
	
}
