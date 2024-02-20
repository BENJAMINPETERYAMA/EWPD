package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;


public class NM1 implements X12Segment{
	private String nm101;
	private String nm102;
	private String nm103;
	private String nm104;
	private String nm105;
	private String nm106;
	private String nm107;
	private String nm108;
	private String nm109;
	private String nm110;
	private String nm111;
	private String nm112;
	
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(NM1SegmentMetaDataEnum.values());
	}

	
	public enum NM1SegmentMetaDataEnum implements SegmentMetaData{	
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		NM101("01",1),
		NM102("02",1),
		NM103("03",1),
		NM104("04",1),
		NM105("05",1),
		NM106("06",1),
		NM107("07",1),
		NM108("08",1),
		NM109("09",1),
		NM110("10",1),
		NM111("11",1),
		NM112("12",1);	
		
		int repeatCount;
		String position;
		
		NM1SegmentMetaDataEnum (String position, int repeatCount) {
			this.repeatCount = repeatCount;
			this.position = position;
		}
		public String getSementIdentifier() {
			return this.name();	
		}
		public int getRepeatCont() {
			return repeatCount;
		}
		public X12Segment createSegmentInstance() {			
			return null;
		}
		@Override
		public String getSegmentPosition() {
			return this.position;
		}
		@Override
		public X12SegmentTypeEnum getSegmentType() {
			return X12SegmentTypeEnum.DATA_ELEMENT;
		}
	}
	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData, T value) {
		if (NM1SegmentMetaDataEnum.NM101.equals(segmentMetaData)
				&& value instanceof String) {
			setNm101((String) value);
		}
		if (NM1SegmentMetaDataEnum.NM102.equals(segmentMetaData)
				&& value instanceof String) {
			setNm102((String) value);
		}
		if (NM1SegmentMetaDataEnum.NM103.equals(segmentMetaData)
				&& value instanceof String) {
			setNm103((String) value);
		}
		if (NM1SegmentMetaDataEnum.NM104.equals(segmentMetaData)
				&& value instanceof String) {
			setNm104((String) value);
		}
		if (NM1SegmentMetaDataEnum.NM105.equals(segmentMetaData)
				&& value instanceof String) {
			setNm105((String) value);
		}
		if (NM1SegmentMetaDataEnum.NM106.equals(segmentMetaData)
				&& value instanceof String) {
			setNm106((String) value);
		}
		if (NM1SegmentMetaDataEnum.NM107.equals(segmentMetaData)
				&& value instanceof String) {
			setNm107((String) value);
		}
		if (NM1SegmentMetaDataEnum.NM108.equals(segmentMetaData)
				&& value instanceof String) {
			setNm108((String) value);
		}
		if (NM1SegmentMetaDataEnum.NM109.equals(segmentMetaData)
				&& value instanceof String) {
			setNm109((String) value);
		}
		if (NM1SegmentMetaDataEnum.NM110.equals(segmentMetaData)
				&& value instanceof String) {
			setNm110((String) value);
		}
		if (NM1SegmentMetaDataEnum.NM111.equals(segmentMetaData)
				&& value instanceof String) {
			setNm111((String) value);
		}
		if (NM1SegmentMetaDataEnum.NM112.equals(segmentMetaData)
				&& value instanceof String) {
			setNm112((String) value);
		}
	}

	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	
	
	public String getNm101() {
		return nm101;
	}
	public void setNm101(String nm101) {
		this.nm101 = nm101;
	}
	public String getNm102() {
		return nm102;
	}
	public void setNm102(String nm102) {
		this.nm102 = nm102;
	}
	public String getNm103() {
		return nm103;
	}
	public void setNm103(String nm103) {
		this.nm103 = nm103;
	}
	public String getNm104() {
		return nm104;
	}
	public void setNm104(String nm104) {
		this.nm104 = nm104;
	}
	public String getNm105() {
		return nm105;
	}
	public void setNm105(String nm105) {
		this.nm105 = nm105;
	}
	public String getNm106() {
		return nm106;
	}
	public void setNm106(String nm106) {
		this.nm106 = nm106;
	}
	public String getNm107() {
		return nm107;
	}
	public void setNm107(String nm107) {
		this.nm107 = nm107;
	}
	public String getNm108() {
		return nm108;
	}
	public void setNm108(String nm108) {
		this.nm108 = nm108;
	}
	public String getNm109() {
		return nm109;
	}
	public void setNm109(String nm109) {
		this.nm109 = nm109;
	}
	public String getNm110() {
		return nm110;
	}
	public void setNm110(String nm110) {
		this.nm110 = nm110;
	}
	public String getNm111() {
		return nm111;
	}
	public void setNm111(String nm111) {
		this.nm111 = nm111;
	}
	public String getNm112() {
		return nm112;
	}
	public void setNm112(String nm112) {
		this.nm112 = nm112;
	}
	
}
