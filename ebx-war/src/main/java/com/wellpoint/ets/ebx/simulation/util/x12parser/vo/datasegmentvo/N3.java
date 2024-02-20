package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;


public class N3 implements X12Segment{

	private String n301;
	private String n302;
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(N3SegmentMetaDataEnum.values());
	}

	
	public enum N3SegmentMetaDataEnum implements SegmentMetaData{
	// Syntax - Seg Id ( Pos, Max Use) 
	// Pos will be used for sorting the segments.
		N301("01",1),
		N302("02",1);	
		
		int repeatCount;
		String position;
		
		N3SegmentMetaDataEnum (String position, int repeatCount) {
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
		if (N3SegmentMetaDataEnum.N301.equals(segmentMetaData)
				&& value instanceof String) {
			setN301((String) value);
		}
		if (N3SegmentMetaDataEnum.N302.equals(segmentMetaData)
				&& value instanceof String) {
			setN302((String) value);
		}
	}
	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public String getN301() {
		return n301;
	}
	public void setN301(String n301) {
		this.n301 = n301;
	}
	public String getN302() {
		return n302;
	}
	public void setN302(String n302) {
		this.n302 = n302;
	}

	
}
