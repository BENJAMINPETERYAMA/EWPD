package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;



public class GE implements X12Segment{
	public enum GESegmentMetaDataEnum implements SegmentMetaData{		
		
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		GE01("01",1),
		GE02("02",1);
		
		
		int repeatCount;
		String position;
		
		GESegmentMetaDataEnum (String position, int repeatCount) {
			this.repeatCount = repeatCount;
			this.position = position;
		}
		@Override
		public int getRepeatCont() {
			return repeatCount;
		}
		// Create Segment Instance is not Applicable for Data elements
		@Override
		public X12Segment createSegmentInstance() {			
			return null;
		}
		@Override
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
	private String ge01;
	private String ge02;
	
	
	private String expression;
	
	public GE() {
		
	}
	
	public String getGe01() {
		return ge01;
	}
	public String getGe02() {
		return ge02;
	}

	
	
	public String getExpression() {
		return expression;
	}
	public SegmentMetaData[] getSegmentMetaData() {		
		return X12ParserUtil.getPositionSortedSegments(GESegmentMetaDataEnum.values());
	}
	public void setGe01(String ge01) {
		this.ge01 = ge01;
	}
	public void setGe02(String ge02) {
		this.ge02 = ge02;
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
	}
	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData,
			T value) {
		if (GESegmentMetaDataEnum.GE01.equals(segmentMetaData)
				&& value instanceof String) {
			setGe01((String) value);
		}
		if (GESegmentMetaDataEnum.GE02.equals(segmentMetaData)
				&& value instanceof String) {
			setGe02((String) value);
		}
		
	}

	

}
