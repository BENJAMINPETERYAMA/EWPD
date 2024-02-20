package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;


public class ST implements X12Segment {

	public enum STSegmentMetaDataEnum implements SegmentMetaData{		
		// Syntax - Seg Id ( Pos, Max Use) 
		// Pos will be used for sorting the segments.
		ST01("01",1),
		ST02("02",1),
		ST03("03",1);			

		int repeatCount;
		String position;
		
		STSegmentMetaDataEnum (String position, int repeatCount) {
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
			return this.position;
		}
		@Override
		public X12SegmentTypeEnum getSegmentType() {
			return X12SegmentTypeEnum.DATA_ELEMENT;
		}
	}
	private String expression;
	private String st01;
	private String st02;
	
	private String st03;
	public ST() {
		
	}
	
	public ST (String expression) {
		//this.expression = expression; 
		//this.parse(expression);
	}
	public String getExpression() {
		return expression;
	}

	
	public SegmentMetaData[] getSegmentMetaData() {
		return X12ParserUtil.getPositionSortedSegments(STSegmentMetaDataEnum.values());
	}

	
	public String getSt01() {
		return st01;
	}


	public String getSt02() {
		return st02;
	}
	public String getSt03() {
		return st03;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	@Override
	public <T> void setFieldValue(SegmentMetaData segmentMetaData,
			T value) {
		if (STSegmentMetaDataEnum.ST01.equals(segmentMetaData)
				&& value instanceof String) {
			setSt01((String) value);
		}
		if (STSegmentMetaDataEnum.ST02.equals(segmentMetaData)
				&& value instanceof String) {
			setSt02((String) value);
		}
		if (STSegmentMetaDataEnum.ST03.equals(segmentMetaData)
				&& value instanceof String) {
			setSt03((String) value);
		}
	}
	public void setSt01(String st01) {
		this.st01 = st01;
	}
	public void setSt02(String st02) {
		this.st02 = st02;
	}
	public void setSt03(String st03) {
		this.st03 = st03;
	}
	
}
