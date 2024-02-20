package com.wellpoint.ets.ebx.simulation.util.x12parser.vo.datasegmentvo;

import com.wellpoint.ets.ebx.simulation.util.x12parser.util.X12ParserUtil;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.SegmentMetaData;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12Segment;
import com.wellpoint.ets.ebx.simulation.util.x12parser.vo.X12SegmentTypeEnum;


public class MSG implements X12Segment{
	private String msg01;
	private String msg02;
	private String msg03;
	
	private String expression;
	public SegmentMetaData[] getSegmentMetaData() {
		return  X12ParserUtil.getPositionSortedSegments(MSGSegmentMetaDataEnum.values());
	}

	
	public enum MSGSegmentMetaDataEnum implements SegmentMetaData{		
	// Syntax - Seg Id ( Pos, Max Use) 
	// Pos will be used for sorting the segments.
	
		MSG01("01",1),
		MSG02("02",1),
		MSG03("03",1);	
		
		int repeatCount;
		String position;
		
		MSGSegmentMetaDataEnum (String position, int repeatCount) {
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
		if (MSGSegmentMetaDataEnum.MSG01.equals(segmentMetaData)
				&& value instanceof String) {
			setMsg01((String) value);
		}
		if (MSGSegmentMetaDataEnum.MSG02.equals(segmentMetaData)
				&& value instanceof String) {
			setMsg02((String) value);
		}
		if (MSGSegmentMetaDataEnum.MSG03.equals(segmentMetaData)
				&& value instanceof String) {
			setMsg03((String) value);
		}
	}
	public String getExpression() {
		return expression;
	}


	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public String getMsg01() {
		return msg01;
	}
	public void setMsg01(String msg01) {
		this.msg01 = msg01;
	}
	public String getMsg02() {
		return msg02;
	}
	public void setMsg02(String msg02) {
		this.msg02 = msg02;
	}
	public String getMsg03() {
		return msg03;
	}
	public void setMsg03(String msg03) {
		this.msg03 = msg03;
	}
	
	
	
}
