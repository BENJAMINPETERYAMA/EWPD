package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;

public class BHT implements X12Segment {
	public enum BHTSegmentMetaDataEnum implements SegmentMetaData{		
		
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		BHT01("1",1),BHT02("2",1),BHT03("3",1),BHT04("4",1),BHT05("5",1),BHT06("6",1);			
		int repeatCount;
		String position;
		BHTSegmentMetaDataEnum (String position, int repeatCount) {
			this.repeatCount = repeatCount;
			this.position = position;
		}
		public int getRepeatCont() {
			return repeatCount;
		}
		public X12Segment createSegmentInstance() {			
			return null;
		}
		public String getSementIdentifier() {
			return this.name();	
		}
		@Override
		public String getSegmentPosition() {
			return position;
		}
		@Override
		public X12SegmentTypeEnum getSegmentType() {
			return X12SegmentTypeEnum.DATA_ELEMENT;
		}
	}
	private String bht01;
	private String bht02;
	private String bht03;
	private String bht04;
	private String bht05;
	
	private String bht06;

	private String expression;

	
	public String getBht01() {
		return bht01;
	}
	public String getBht02() {
		return bht02;
	}
	
	public String getBht03() {
		return bht03;
	}
	public String getBht04() {
		return bht04;
	}
	public String getBht05() {
		return bht05;
	}
	public String getBht06() {
		return bht06;
	}
	public String getExpression() {
		return expression;
	}
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(BHTSegmentMetaDataEnum.values());
	}
	public void setBht01(String bht01) {
		this.bht01 = bht01;
	}
	public void setBht02(String bht02) {
		this.bht02 = bht02;
	}
	public void setBht03(String bht03) {
		this.bht03 = bht03;
	}
	public void setBht04(String bht04) {
		this.bht04 = bht04;
	}
	public void setBht05(String bht05) {
		this.bht05 = bht05;
	}
	public void setBht06(String bht06) {
		this.bht06 = bht06;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData,
			T value) {
		if (BHTSegmentMetaDataEnum.BHT01.equals(segmentMetaData)
				&& value instanceof String) {
			setBht01((String) value);
		}
		if (BHTSegmentMetaDataEnum.BHT02.equals(segmentMetaData)
				&& value instanceof String) {
			setBht02((String) value);
		}
		if (BHTSegmentMetaDataEnum.BHT03.equals(segmentMetaData)
				&& value instanceof String) {
			setBht03((String) value);
		}
		if (BHTSegmentMetaDataEnum.BHT04.equals(segmentMetaData)
				&& value instanceof String) {
			setBht04((String) value);
		}
		if (BHTSegmentMetaDataEnum.BHT05.equals(segmentMetaData)
				&& value instanceof String) {
			setBht03((String) value);
		}
		if (BHTSegmentMetaDataEnum.BHT06.equals(segmentMetaData)
				&& value instanceof String) {
			setBht04((String) value);
		}
	}

}
