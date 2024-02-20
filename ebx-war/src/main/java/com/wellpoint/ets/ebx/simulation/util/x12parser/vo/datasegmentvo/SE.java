package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;


public class SE implements X12Segment{
	private String se01;
	private String se02; 
	 

	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(SESegmentMetaDataEnum.values());
	}

	
	public enum SESegmentMetaDataEnum implements SegmentMetaData{		
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		SE01("01",1),
		SE02("02",1);	
		
		int repeatCount;
		String position;
		SESegmentMetaDataEnum (String position, int repeatCount) {
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
		if (SESegmentMetaDataEnum.SE01.equals(segmentMetaData)
				&& value instanceof String) {
			setSe01((String) value);
		}
		if (SESegmentMetaDataEnum.SE02.equals(segmentMetaData)
				&& value instanceof String) {
			setSe02((String) value);
		}
	}

	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	
	public String getSe01() {
		return se01;
	}
	public void setSe01(String se01) {
		this.se01 = se01;
	}
	public String getSe02() {
		return se02;
	}
	public void setSe02(String se02) {
		this.se02 = se02;
	}
	
	
}
